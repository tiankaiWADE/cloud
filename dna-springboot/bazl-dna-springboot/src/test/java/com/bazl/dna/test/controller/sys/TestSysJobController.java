package com.bazl.dna.test.controller.sys;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.BodyInserters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.sys.entity.SysJob;
import com.bazl.dna.test.controller.TestBaseController;

/**
 * Test AuthController
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysJobController extends TestBaseController {

	private static final String PATH = "/system/job";

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

	@Test
	@SuppressWarnings("unchecked")
	public void save() {
		String savePath = PATH + "/save";
		SysJob entity = new SysJob();
		entity.setJobName("test");
		entity.setJobInfo("test");
		entity.setJobOrder(3);
		
		Map<String, Object> map = (Map<String, Object>) put(savePath, BodyInserters.fromValue(JSON.toJSON(entity)));
		LOGGER.info("save:[{}] -> {}", savePath, map);
		assertNotNull(map);

		String deletePath = PATH + "/delete";
		Map<String, Object> sysJob = (Map<String, Object>) map.get(PublicConstants.MSG);
		String[] ids = new String[] { sysJob.get("id").toString() };
		Object deleteResult = delete(deletePath, BodyInserters.fromValue(ids));
		LOGGER.info("delete:[{}] -> {}", deletePath, deleteResult);
		assertNotNull(deleteResult);
	}

}
