package com.bazl.dna.test.service.gateway;

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
import com.bazl.dna.gateway.entity.GatewayRoute;
import com.bazl.dna.gateway.service.DynamicRouteService;

/**
 * Test GatewayRouteService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestGatewayRouteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestGatewayRouteService.class);

	@Autowired
	private DynamicRouteService service;

	@Test
	@RepeatedTest(value = 2)
	public void findList() {
		List<GatewayRoute> list = service.findList();
		LOGGER.info("findList:{}", list.size());
		assertNotEquals(0, list.size());
	}

	@Test
	public void pageList() {
		JSONObject paramJson = new JSONObject();
		Page<GatewayRoute> list = service.pageList(paramJson);
		LOGGER.info("pageList:{}", list.getSize());
		assertNotEquals(0, list.getSize());
	}

	@Test
	public void getById() {
		List<GatewayRoute> list = service.findList();
		if (!list.isEmpty()) {
			GatewayRoute entity = service.getById(list.get(0).getId());
			LOGGER.info("getById:{}", JSON.toJSONString(entity));
			assertNotNull(entity);
		}
	}

}
