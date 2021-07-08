package com.bazl.dna.monitor.controller.dashboard;

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

	/**
	 * 首页信息
	 * @return ResponseData
	 */
	@GetMapping("/index")
	public ResponseData index() {
		String url = "http://localhost:9411/zipkin/dependency";
		return new ResponseData(url);
	}
}
