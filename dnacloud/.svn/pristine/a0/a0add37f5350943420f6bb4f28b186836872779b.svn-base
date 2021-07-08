package com.bazl.dna.test.controller.files;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.BodyInserters;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.test.controller.TestBaseController;

/**
 * Test AuthController
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestFastDfsFilesController extends TestBaseController {

	private static final String PATH = "/files/fdfs";

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
