package com.bazl.dna.test.service.sys;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.sys.entity.SysRole;
import com.bazl.dna.sys.service.ISysRoleService;

/**
 * Test SysRoleService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysRoleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSysRoleService.class);

	@Autowired
	private ISysRoleService service;

	@Test
	@RepeatedTest(value = 2)
	public void findList() {
		JSONObject paramJson = new JSONObject();
		List<SysRole> list = service.findList(paramJson);
		LOGGER.info("findList:{}", list.size());
		assertNotEquals(0, list.size());
	}

	@Test
	public void pageList() {
		JSONObject paramJson = new JSONObject();
		Page<SysRole> list = service.pageList(paramJson);
		LOGGER.info("pageList:{}", list.getSize());
		assertNotEquals(0, list.getSize());
	}

	@Test
	public void getById() {
		String id = "1";
		SysRole entity = service.getById(id);
		LOGGER.info("getById:{}", JSON.toJSONString(entity));
		assertNotNull(entity);
	}
	
	@Test
	public void findMenuByRoles() {
		List<String> roles = new ArrayList<>();
		roles.add("1");
		String menuType = "1";
		JSONArray list = service.findMenuByRoles(roles, menuType);
		LOGGER.info("findMenuByRoles:{}", list.size());
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void findMenuByRoleId() {
		String menuType = "1";
		String roleId = "1";
		List<String> list = service.findMenuByRoleId(menuType, roleId);
		LOGGER.info("findMenuByRoleId:{}", list.size());
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void findTreeList() {
		String menuType = "1";
		List<Object> list = service.findTreeList(menuType);
		LOGGER.info("findTreeList:{}", list.size());
		assertNotEquals(0, list.size());
	}

}
