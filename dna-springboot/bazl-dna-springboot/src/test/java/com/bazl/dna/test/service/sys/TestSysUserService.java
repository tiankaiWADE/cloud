package com.bazl.dna.test.service.sys;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.sys.entity.SysUser;
import com.bazl.dna.sys.service.ISysUserService;

/**
 * Test SysUserService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSysUserService.class);

	@Autowired
	private ISysUserService service;

	@Test
	@RepeatedTest(value = 2)
	public void findList() {
		JSONObject paramJson = new JSONObject();
		List<SysUser> list = service.findList(paramJson);
		LOGGER.info("findList:{}", list.size());
		assertNotEquals(0, list.size());
	}

	@Test
	public void pageList() {
		JSONObject paramJson = new JSONObject();
		Page<SysUser> list = service.pageList(paramJson);
		LOGGER.info("pageList:{}", list.getSize());
		assertNotEquals(0, list.getSize());
	}

	@Test
	public void getById() {
		String id = "1";
		SysUser entity = service.getById(id);
		LOGGER.info("getById:{}", JSON.toJSONString(entity));
		assertNotNull(entity);
	}
	
	@Test
	public void login() {
		String userName = "admin";
		String password = "1";
		Map<String, Object> map = service.login(userName, password);
		LOGGER.info("login:{}", map);
		assertNotEquals(0, map.size());
	}

}
