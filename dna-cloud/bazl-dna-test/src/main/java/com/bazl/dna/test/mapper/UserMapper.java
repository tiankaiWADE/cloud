package com.bazl.dna.test.mapper;

import com.bazl.dna.test.entity.User;

/**
 * 系统用户接口
 * @author zhaoyong
 */
public interface UserMapper {

	/**
	 * 查询对象
	 * @param id 主键
	 * @return User
	 */
	User getUserById(Long id);
}
