package com.bazl.dna.test.controller.sys;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.BodyInserters;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.test.controller.TestBaseController;

/**
 * Test SysUserController
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysUserController extends TestBaseController {

	private static final String PATH = "/system/user";

	@Test
	public void get() {
		String url = PATH + "/get/1";
		Object result = get(url);
		LOGGER.info("get:[{}] -> {}", url, result);
		assertNotNull(result);
	}

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
