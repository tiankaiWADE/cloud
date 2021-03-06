package com.bazl.dna.monitor.controller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.deploy.DeployServer;

/**
 * 服务监控
 *
 * @author zhaoyong
 */
@RestController
@RequestMapping("/monitor/service")
public class ServiceController extends BaseController {
	
	private static final String HOST = "localhost";
	
	@Value("${spring.application.name}")
	private String serviceId;
	@Value("${server.port}")
	private int port;
	@Value("${spring.profiles.active}")
	private String scheme;

	/**
	 * 基本信息
	 * @return List<List<Map<String, Object>>>
	 */
	@GetMapping("/index")
	public List<List<Map<String, Object>>> index() {
		List<List<Map<String, Object>>> serviceList = new ArrayList<>();
        List<Map<String, Object>> serviceInstances = new ArrayList<>();
        
        // 获取服务中的实例列表
        Map<String, Object> map = new HashMap<>();
        map.put("serviceId", serviceId);
        map.put("host", HOST);
        map.put("port", port);
        map.put("uri", "http://" + HOST + ":" + port);
        map.put("instanceId", serviceId);
        map.put("scheme", scheme);
		serviceInstances.add(map);
        serviceList.add(serviceInstances);
		return serviceList;
	}

	/**
	 * 动态刷新配置
	 * @return String
	 */
	@GetMapping("/refreshConfig")
	public String refreshConfig() {
		return PublicConstants.SUCCESS;
	}

	/**
	 * 获取信息
	 * @param paramJson 属性
	 * @return JSONObject
	 */
	@PostMapping("/getActuatorEnv")
	public JSONObject getActuatorEnv(@RequestBody JSONObject paramJson) {
		JSONObject result = new JSONObject();
		JSONArray profiles = new JSONArray();
		profiles.add(scheme);
		result.put("activeProfiles", profiles);
 		result.put("propertySources", null);
		return result;
	}

	/**
	 * 上传服务
	 *
	 * @param multipartFile 文件
	 * @return String
	 */
	@PostMapping("/uploadService")
	public String uploadService(@RequestParam("multipartFile") MultipartFile multipartFile,
			String ip, int port, String serviceName) {
		return PublicConstants.SUCCESS;
	}

	/**
	 * 重启服务
	 * @param deployServer 对象
	 * @return String
	 */
	@PostMapping("/resetService")
	public String resetService(@RequestBody DeployServer deployServer) {
		return PublicConstants.SUCCESS;
	}

}
