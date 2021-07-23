package com.bazl.dna.test;

import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.util.GsonUtils;
import com.bazl.dna.util.RequestUtils;

public class BaseTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseTests.class);

	public static final String URL = "http://localhost:8704";

	public String token;

	@Autowired
	public RestTemplate restTemplate;

	@Before
	public void setUp() {
		String url = URL + "/system/auth/login";
		JSONObject paramJson = new JSONObject();
		paramJson.put("userName", "admin");
		paramJson.put("password", "1");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<JSONObject> entity = new HttpEntity<>(paramJson, headers);

		ResponseData result = restTemplate.postForObject(url, entity, ResponseData.class);
		Assert.assertNotNull(result);

		String json = GsonUtils.objectToJson(result.getResult());
		token = JSONObject.parseObject(json).getString(RequestUtils.ACCESS_TOKEN);
	}

	public HttpHeaders buildHeader() {
		Assert.assertNotNull(token);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(HttpHeaders.AUTHORIZATION, token);
		return headers;
	}

	public void print(ResponseEntity<ResponseData> result) {
		Assert.assertNotNull(result);
		LOGGER.info("result: {}" ,GsonUtils.objectToJson(result.getBody()));
	}
}
