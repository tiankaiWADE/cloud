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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.sys.entity.SysDictItemType;
import com.bazl.dna.sys.service.ISysDictItemTypeService;

/**
 * Test SysDictItemTypeService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysDictItemTypeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSysDictItemTypeService.class);

	@Autowired
	private ISysDictItemTypeService service;

	@Test
	@RepeatedTest(value = 2)
	public void findList() {
		JSONObject paramJson = new JSONObject();
		List<SysDictItemType> list = service.findList(paramJson);
		LOGGER.info("findList:{}", list.size());
		assertNotEquals(0, list.size());
	}

	@Test
	public void findListByCodes() {
		JSONObject paramJson = new JSONObject();
		JSONArray paramArray = new JSONArray();
		paramArray.add("sys_task_group");
		paramArray.add("sys_task_status");
		paramJson.put("codes", paramArray);
		List<SysDictItemType> list = service.findListByCodes(paramJson);
		LOGGER.info("findListByCodes:{}", list.size());
		assertNotEquals(0, list.size());
	}

	@Test
	public void pageList() {
		JSONObject paramJson = new JSONObject();
		Page<SysDictItemType> list = service.pageList(paramJson);
		LOGGER.info("pageList:{}", list.getSize());
		assertNotEquals(0, list.getSize());
	}

	@Test
	public void getById() {
		String id = "sys_task_group";
		SysDictItemType entity = service.getById(id);
		LOGGER.info("getById:{}", JSON.toJSONString(entity));
		assertNotNull(entity);
	}

}
