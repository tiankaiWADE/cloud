package com.bazl.dna.test.service.sys;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.sys.entity.SysMenuType;
import com.bazl.dna.sys.service.ISysMenuTypeService;

/**
 * Test SysMenuTypeService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysMenuTypeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSysMenuTypeService.class);

	@Autowired
	private ISysMenuTypeService service;

	@Test
	@RepeatedTest(value = 2)
	public void findList() {
		JSONObject paramJson = new JSONObject();
		List<SysMenuType> list = service.findList(paramJson);
		LOGGER.info("findList:{}", list.size());
		assertNotEquals(0, list.size());
	}

	@Test
	public void pageList() {
		JSONObject paramJson = new JSONObject();
		Page<SysMenuType> list = service.pageList(paramJson);
		LOGGER.info("pageList:{}", list.getSize());
		assertNotEquals(0, list.getSize());
	}

	@Test
	public void getById() {
		String id = "1";
		SysMenuType entity = service.getById(id);
		LOGGER.info("getById:{}", JSON.toJSONString(entity));
		assertNotNull(entity);
	}
	
	@Test
	public void getMenuTypeByTypeCode() {
		String code = "nt_sys";
		SysMenuType entity = service.getMenuTypeByTypeCode(code);
		LOGGER.info("getMenuTypeByTypeCode:{}", JSON.toJSONString(entity));
		assertNotNull(entity);
	}

}
