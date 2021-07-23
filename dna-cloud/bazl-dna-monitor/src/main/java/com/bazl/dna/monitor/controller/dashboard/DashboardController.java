package com.bazl.dna.monitor.controller.dashboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.controller.BaseController;

/**
 * 首页信息
 * @author zhaoyong
 *
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {

	@Value("${spring.zipkin.base-url}")
	private String hostname;

	/**
	 * 首页信息
	 * @return ResponseData
	 */
	@GetMapping("/index")
	public ResponseData index() {
		// 服务监控图
		String url = hostname + "/zipkin/dependency";
		return new ResponseData(url);
	}
}
