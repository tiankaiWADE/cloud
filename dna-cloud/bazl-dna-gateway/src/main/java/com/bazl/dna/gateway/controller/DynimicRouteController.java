package com.bazl.dna.gateway.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.gateway.constants.GatewayConstants;
import com.bazl.dna.gateway.constants.ResponseData;
import com.bazl.dna.gateway.entity.GatewayRoute;
import com.bazl.dna.gateway.service.DynamicRouteService;
import com.google.common.collect.Maps;

/**
 * 动态路由控制
 *
 * @author liutao
 *
 */
@RestController
@RequestMapping("/route")
public class DynimicRouteController {

	@Autowired
	private DynamicRouteService dynamicRouteService;

	/**
	 * 路由列表
	 *
	 * @param paramJson 查询参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData list(@RequestBody JSONObject paramJson) {
		Page<GatewayRoute> page = dynamicRouteService.pageList(paramJson);
		return new ResponseData(page);
	}

	/**
	 * 增加路由
	 *
	 * @param paramJson 路由对象
	 * @return ResponseData
	 */
	@PostMapping("/add")
	public ResponseData add(@RequestBody JSONObject paramJson) {
		GatewayRoute gatewayRoute = JSON.toJavaObject(paramJson, GatewayRoute.class);
		String msg = dynamicRouteService.save(gatewayRoute);

		Map<String, Object> result = Maps.newHashMap();
		result.put(GatewayConstants.CODE, GatewayConstants.SUCCESS_CODE);
		result.put(GatewayConstants.MSG, msg);
		return new ResponseData(result);
	}

	/**
	 * 获取路由
	 *
	 * @param id 编号
	 * @return ResponseData
	 */
	@GetMapping("/get/{id}")
	public ResponseData get(@PathVariable String id) {
		return new ResponseData(dynamicRouteService.getById(id));
	}

	/**
	 * 删除路由
	 *
	 * @param ids 编号
	 * @return ResponseData
	 */
	@DeleteMapping("/delete")
	public ResponseData delete(@RequestBody String... ids) {
		for (String id : ids) {
			dynamicRouteService.delete(id);
		}

		Map<String, Object> result = Maps.newHashMap();
		result.put(GatewayConstants.CODE, GatewayConstants.SUCCESS_CODE);
		return new ResponseData(result);
	}

	/**
	 * 更新路由
	 *
	 * @param paramJson 路由对象
	 * @return ResponseData
	 */
	@PostMapping("/update")
	public ResponseData update(@RequestBody JSONObject paramJson) {
		GatewayRoute gatewayRoute = JSON.toJavaObject(paramJson, GatewayRoute.class);
		String msg = dynamicRouteService.update(gatewayRoute);

		Map<String, Object> result = Maps.newHashMap();
		result.put(GatewayConstants.CODE, GatewayConstants.SUCCESS_CODE);
		result.put(GatewayConstants.MSG, msg);
		return new ResponseData(result);
	}

	/**
	 * 刷新路由配置
	 *
	 * @return ResponseData
	 */
	@GetMapping("/refresh")
	public ResponseData refresh() {
		return new ResponseData(dynamicRouteService.refreshRoute());
	}

	/**
	 * 更新路由状态
	 *
	 * @param id 编号
	 * @param status 状态
	 * @return ResponseData
	 */
	@GetMapping("/updateRouteStatus/{id}/{status}")
	public ResponseData updateRouteStatus(@PathVariable String id, @PathVariable String status) {
		String msg = dynamicRouteService.updateRouteStatus(id, status);

		Map<String, Object> result = Maps.newHashMap();
		result.put(GatewayConstants.CODE, GatewayConstants.SUCCESS_CODE);
		result.put(GatewayConstants.MSG, msg);
		return new ResponseData(result);
	}

}
