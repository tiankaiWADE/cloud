package com.bazl.dna.test.service.monitor;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.monitor.entity.SysTask;
import com.bazl.dna.monitor.service.ISysTaskService;
import com.bazl.dna.util.DataUtils;

/**
 * Test SysTaskService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysTaskService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSysTaskService.class);

	@Autowired
	private ISysTaskService service;


	@Test
	@RepeatedTest(value = 2)
	public void pageList() {
		JSONObject paramJson = new JSONObject();
		Page<SysTask> list = service.pageList(paramJson);
		LOGGER.info("pageList:{}", list.getSize());
		assertNotEquals(0, list.getSize());
	}

	@Test
	public void selectTaskById() {
		JSONObject paramJson = new JSONObject();
		Page<SysTask> list = service.pageList(paramJson);
		if (!DataUtils.isEmpty(list) && !list.getContent().isEmpty()) {
			SysTask entity = service.selectTaskById(list.getContent().get(0).getId());
			LOGGER.info("selectTaskById:{}", JSON.toJSONString(entity));
			assertNotNull(entity);
		}
	}

}
