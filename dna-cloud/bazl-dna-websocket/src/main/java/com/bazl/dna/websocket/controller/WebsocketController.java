package com.bazl.dna.websocket.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazl.dna.websocket.config.ResponseData;

/**
 * 测试ws
 * @author liutao
 */
@RefreshScope
@RestController
@RequestMapping("/websocket2")
public class WebsocketController {


	/**
	 * 测试接收ws数据
	 * @param token token
	 * @return ResponseData
	 */
	@GetMapping
	public ResponseData test(String token) {
		System.err.println(token);
		return new ResponseData();
	}
}
