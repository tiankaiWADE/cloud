package com.bazl.dna.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.test.entity.User;
import com.bazl.dna.test.service.IUserService;

/**
 * jdbc实现控制器
 * @author zhaoyong
 */
@RestController
@RefreshScope
public class UserMysqlJdbcTransactionalController {
	@Autowired
	private IUserService userService;

	@GetMapping("/getUserById/{id}")
	public User getUserById(@PathVariable Long id) {
		return this.userService.getUserById(id);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseData login(@RequestBody String json) {
		return userService.login(json);
	}

	@GetMapping("/getTypeById/{id}")
	public ResponseData getTypeById(@PathVariable String id) {
		return userService.getTypeById(id);
	}
	
	@PostMapping("/findTypeList")
	public ResponseData findTypeList(@RequestBody JSONObject paramJson) {
		return userService.findTypeList(paramJson);
	}

}
