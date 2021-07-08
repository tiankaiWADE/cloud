package com.bazl.dna.monitor.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.deploy.DeployServer;

import reactor.core.publisher.Mono;

/**
 * 服务监控
 *
 * @author zhaoyong
 */
@RestController
@RequestMapping("/monitor/service")
public class ServiceController extends BaseController {

	/**
	 * 基本信息
	 * @return ResponseData
	 */
	@GetMapping("/index")
	public ResponseData index() {
		List<String> serviceList = new ArrayList<>();
		return new ResponseData(serviceList);
	}

	/**
	 * 动态刷新配置
	 * @return ResponseData
	 */
	@GetMapping("/refreshConfig")
	public ResponseData refreshConfig() {
		return new ResponseData();
	}

	/**
	 * 获取信息
	 * @param paramJson 属性
	 * @return ResponseData
	 */
	@PostMapping("/getActuatorEnv")
	public ResponseData getActuatorEnv(@RequestBody JSONObject paramJson) {
		ClientHttpConnector httpConnector = new ReactorClientHttpConnector();
		WebClient client = WebClient.builder().clientConnector(httpConnector).baseUrl(paramJson.getString("uri")).build();
		
		String uri = "/actuator/env";
		Mono<JSONObject> mono = client.get().uri(uri)
				.retrieve().bodyToMono(JSONObject.class);
		return new ResponseData(mono.block());
	}

	/**
	 * 上传服务
	 *
	 * @param multipartFile 文件
	 * @return ResponseData
	 */
	@PostMapping("/uploadService")
	public ResponseData uploadService(@RequestParam("multipartFile") MultipartFile multipartFile,
			String ip, int port, String serviceName) {
		return new ResponseData();
	}

	/**
	 * 重启服务
	 * @param deployServer 对象
	 * @return ResponseData
	 */
	@PostMapping("/resetService")
	public ResponseData resetService(@RequestBody DeployServer deployServer) {
		return new ResponseData();
	}

}
