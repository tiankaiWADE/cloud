package com.bazl.dna.test.controller.monitor;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.BodyInserters;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.test.controller.TestBaseController;

/**
 * Test SysTaskController
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysTaskController extends TestBaseController {

	private static final String PATH = "/monitor/task";

	@Test
	@RepeatedTest(2)
	public void list() {
		String url = PATH + "/list";
		JSONObject paramJson = new JSONObject();
		Object result = post(url, BodyInserters.fromValue(paramJson));
		LOGGER.info("list:[{}] -> {}", url, result);
		assertNotNull(result);
	}

}
