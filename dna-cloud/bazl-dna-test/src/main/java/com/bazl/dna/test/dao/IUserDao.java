package com.bazl.dna.test.dao;

import java.util.List;

import com.bazl.dna.test.entity.User;

/**
 * 用户接口
 * @author zhaoyong
 */
public interface IUserDao {

	/**
	 * 查询用户信息
	 * @param id 主键
	 * @return User
	 */
	User findUserById(Long id);

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

}
