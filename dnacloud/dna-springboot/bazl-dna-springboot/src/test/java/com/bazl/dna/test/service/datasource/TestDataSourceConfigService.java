package com.bazl.dna.test.service.datasource;

import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.sys.entity.DataSourceConfig;
import com.bazl.dna.sys.service.IDataSourceConfigService;

/**
 * Test DataSourceConfigService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestDataSourceConfigService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestDataSourceConfigService.class);

	@Autowired
	private IDataSourceConfigService service;


	@Test
	@RepeatedTest(value = 2)
	public void pageList() {
		JSONObject paramJson = new JSONObject();
		Page<DataSourceConfig> list = service.pageList(paramJson);
		LOGGER.info("pageList:{}", list.getSize());
		assertNotEquals(0, list.getSize());
	}

}
