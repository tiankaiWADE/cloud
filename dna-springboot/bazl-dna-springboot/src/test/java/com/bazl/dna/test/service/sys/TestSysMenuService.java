package com.bazl.dna.test.service.sys;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSON;
import com.bazl.dna.sys.entity.SysMenu;
import com.bazl.dna.sys.service.ISysMenuService;

/**
 * Test SysMenuService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysMenuService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSysMenuService.class);

	@Autowired
	private ISysMenuService service;

	@Test
	public void getById() {
		String id = "101";
		SysMenu entity = service.getById(id);
		LOGGER.info("getById:{}", JSON.toJSONString(entity));
		assertNotNull(entity);
	}

	@Test
	public void findMenuByTypeCode() {
		String typeCode = "nt_sys";
		List<SysMenu> list = service.findMenuByTypeCode(typeCode);
		LOGGER.info("findMenuByTypeCode:{}", list.size());
		assertNotEquals(0, list.size());
	}

	@Test
	public void selectMenuByRoles() {
		List<String> roles = new ArrayList<>();
		roles.add("1");
		List<SysMenu> list = service.selectMenuByRoles(roles, "1", "-1");
		LOGGER.info("selectMenuByRoles:{}", list.size());
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void findMenu() {
		List<SysMenu> list = service.findMenu("1", "-1");
		LOGGER.info("findMenu:{}", list.size());
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void selectListByRoleId() {
		List<Integer> list = service.selectListByRoleId("1", "1");
		LOGGER.info("selectListByRoleId:{}", list.size());
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void selectList() {
		// cache error
		SysMenu menu = new SysMenu();
		menu.setMenuTypeId("1");
		List<SysMenu> list = service.selectList(menu);
		LOGGER.info("selectList:{}", list.size());
		assertNotEquals(0, list.size());
	}

	@Test
	public void selectListByUserId() {
		SysMenu menu = new SysMenu();
		menu.setMenuTypeId("1");
		menu.setUserId("1");
		List<SysMenu> list = service.selectListByUserId(menu);
		LOGGER.info("selectListByUserId:{}", list.size());
		assertNotEquals(0, list.size());
	}

}
