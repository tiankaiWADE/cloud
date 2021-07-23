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
import com.bazl.dna.monitor.entity.SysTaskLog;
import com.bazl.dna.monitor.service.ISysTaskLogService;
import com.bazl.dna.util.DataUtils;

/**
 * Test SysTaskLogService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysTaskLogService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSysTaskLogService.class);

	@Autowired
	private ISysTaskLogService service;


	@Test
	@RepeatedTest(value = 2)
	public void pageList() {
		JSONObject paramJson = new JSONObject();
		Page<SysTaskLog> list = service.pageList(paramJson);
		LOGGER.info("pageList:{}", list.getSize());
		assertNotEquals(0, list.getSize());
	}

	@Test
	public void selectTaskLogById() {
		JSONObject paramJson = new JSONObject();
		Page<SysTaskLog> list = service.pageList(paramJson);
		if (!DataUtils.isEmpty(list) && !list.getContent().isEmpty()) {
			SysTaskLog entity = service.selectTaskLogById(list.getContent().get(0).getId());
			LOGGER.info("selectTaskLogById:{}", JSON.toJSONString(entity));
			assertNotNull(entity);
		}
	}

}
