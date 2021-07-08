package com.bazl.dna.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.feign.config.FeignSupportConfig;
import com.bazl.dna.test.client.fallback.UserServiceFallback;

/**
 * 系统用户接口
 * @author zhaoyong
 */
@FeignClient(value = "bazl-dna-system", fallback = UserServiceFallback.class,
		configuration = FeignSupportConfig.class)
public interface IUserServiceClient {

	/**
	 * 登录接口
	 * @param json 登录参数
	 * @return ResponseData
	 */
	@PostMapping(value = "/auth/login")
	ResponseData login(String json);

	/**
	 * 根据类型获取对象
	 * @param id 主键
	 * @return ResponseData
	 */
	@GetMapping(value = "/dict/getTypeById/{id}")
	ResponseData getTypeById(@PathVariable("id") String id);

	/**
	 * 根据类型获取列表
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@ResponseBody
	@PostMapping(value = "/dict/findTypeList")
	ResponseData findTypeList(@RequestBody JSONObject paramJson);
}
