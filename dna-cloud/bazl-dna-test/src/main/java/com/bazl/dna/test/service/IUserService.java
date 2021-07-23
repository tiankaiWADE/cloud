package com.bazl.dna.test.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.test.entity.User;

/**
 * 系统用户接口
 * @author zhaoyong
 */
public interface IUserService {

	/**
	 * 查询列表
	 * @return List
	 */
	List<User> findAllUsers();

	/**
	 * 添加用户
	 * @param user 对象
	 * @return int
	 */
	int insertUser(User user);

	/**
	 * 查询对象
	 * @param id 主键
	 * @return User
	 */
	User getUserById(Long id);

	/**
	 * 登录
	 * @param json 登录参数
	 * @return ResponseData
	 */
	ResponseData login(String json);

	/**
	 * 根据类型获取信息
	 * @param id 类型id
	 * @return ResponseData
	 */
	ResponseData getTypeById(String id);

	/**
	 * 根据类型获取列表
	 * @param paramJson 查询参数
	 * @return ResponseData
	 */
	ResponseData findTypeList(@RequestBody JSONObject paramJson);
}
