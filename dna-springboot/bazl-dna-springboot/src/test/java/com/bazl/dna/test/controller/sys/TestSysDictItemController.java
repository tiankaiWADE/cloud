package com.bazl.dna.test.controller.sys;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.BodyInserters;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.test.controller.TestBaseController;

/**
 * Test DictItemController
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysDictItemController extends TestBaseController {

	private static final String PATH = "/system/dict";

	@Test
	public void getTypeById() {
		String url = PATH + "/getTypeById/sys_task_status";
		Object result = get(url);
		LOGGER.info("getTypeById:[{}] -> {}", url, result);
		assertNotNull(result);
	}

	@Test
	public void findTypeInfoById() {
		String url = PATH + "/findTypeInfoById/sys_task_status";
		Object result = get(url);
		LOGGER.info("findTypeInfoById:[{}] -> {}", url, result);
		assertNotNull(result);
	}
	
	@Test
	@RepeatedTest(2)
	public void findTypeInfoByCodes() {
		String url = PATH + "/findTypeInfoByCodes";
		JSONObject paramJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		jsonArray.add("sys_task_status");
		paramJson.put("codes", jsonArray);
		Object result = post(url, BodyInserters.fromValue(paramJson));
		LOGGER.info("findTypeInfoByCodes:[{}] -> {}", url, result);
		assertNotNull(result);
	}
	
	@Test
	@RepeatedTest(2)
	public void findTypeList() {
		String url = PATH + "/findTypeList";
		JSONObject paramJson = new JSONObject();
		Object result = post(url, BodyInserters.fromValue(paramJson));
		LOGGER.info("findTypeList:[{}] -> {}", url, result);
		assertNotNull(result);
	}

	@Test
	@RepeatedTest(2)
	public void findItemList() {
		String url = PATH + "/findItemList";
		JSONObject paramJson = new JSONObject();
		Object result = post(url, BodyInserters.fromValue(paramJson));
		LOGGER.info("findItemList:[{}] -> {}", url, result);
		assertNotNull(result);
	}
	
	@Test
	public void getItemById() {
		String url = PATH + "/getItemById/1101";
		Object result = get(url);
		LOGGER.info("getItemById:[{}] -> {}", url, result);
		assertNotNull(result);
	}
	
	@Test
	@RepeatedTest(2)
	public void findListByCodes() {
		String url = PATH + "/findListByCodes";
		JSONObject paramJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		jsonArray.add("sys_task_status");
		paramJson.put("codes", jsonArray);
		Object result = post(url, BodyInserters.fromValue(paramJson));
		LOGGER.info("findListByCodes:[{}] -> {}", url, result);
		assertNotNull(result);
	}
}
