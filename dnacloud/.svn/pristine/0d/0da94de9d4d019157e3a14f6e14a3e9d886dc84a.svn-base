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

import com.alibaba.fastjson.JSON;
import com.bazl.dna.sys.entity.SysMessage;
import com.bazl.dna.sys.service.ISysMessageService;

/**
 * Test SysMessageService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysMessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSysMessageService.class);

	@Autowired
	private ISysMessageService service;

	@Test
	@RepeatedTest(value = 2)
	public void findList() {
		String messageType = "test";
		List<SysMessage> list = service.findList(messageType);
		LOGGER.info("findList:{}", list.size());
		assertNotEquals(0, list.size());
	}

	@Test
	public void getById() {
		String messageType = "test";
		List<SysMessage> list = service.findList(messageType);
		if (!list.isEmpty()) {
			SysMessage entity = service.getById(list.get(0).getId());
			LOGGER.info("getById:{}", JSON.toJSONString(entity));
			assertNotNull(entity);
		}
	}

}
