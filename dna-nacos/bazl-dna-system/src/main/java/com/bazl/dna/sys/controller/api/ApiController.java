package com.bazl.dna.sys.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazl.dna.controller.BaseController;

/**
 * API接口
 * @author zhaoyong
 */
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {
	
	/**
	 * 测试
	 * @param data 数据
	 * @return String
	 */
	@GetMapping("/test")
	public String test(String data) {
		return data;
	}
	
}
