package com.bazl.dna.swagger.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;

/**
 * 权限认证服务
 * @author zhaoyong
 */
@FeignClient(value = "bazl-dna-system")
public interface IAuthServiceClient {

	/**
	 * 登录
	 * @param json 登录参数
	 * @return ResponseData
	 */
	@PostMapping(value="/auth/login")
	ResponseData login(@RequestBody JSONObject json);

}
