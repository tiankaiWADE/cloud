package com.bazl.dna.test.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.util.RequestUtils;

import reactor.core.publisher.Mono;

/**
 * Base Controller
 * 
 * @author zhaoyong
 *
 */
public class TestBaseController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(TestBaseController.class);

	protected static final String URL = "http://localhost:8704";

	protected WebClient client;

	protected String token;

	public TestBaseController() {
		ClientHttpConnector httpConnector = new ReactorClientHttpConnector();
		client = WebClient.builder().clientConnector(httpConnector).baseUrl(URL).build();
		token = getToken();
	}

	/**
	 * Request Header Authorization
	 * 
	 * @return String token
	 */
	protected String getToken() {
		// auth
		String url = "/system/auth/login";
		JSONObject paramJson = new JSONObject();
		paramJson.put("userName", "admin");
		paramJson.put("password", "1");

		Mono<ResponseData> mono = client.post().uri(url)
				.body(BodyInserters.fromValue(paramJson))
				.retrieve().bodyToMono(ResponseData.class);

		@SuppressWarnings("unchecked")
		Map<String, Object> result = (Map<String, Object>) mono.block().getResult();
		return result.get(RequestUtils.ACCESS_TOKEN).toString();
	}

	/**
	 * GET request
	 * 
	 * @param url 请求地址
	 * @return Object ResponseData result
	 */
	protected Object get(String url) {
		Mono<ResponseData> mono = client.get().uri(url)
				.header(HttpHeaders.AUTHORIZATION, token)
				.retrieve().bodyToMono(ResponseData.class);
		return mono.block().getResult();
	}

	/**
	 * POST request
	 * 
	 * @param url           请求地址
	 * @param bodyInserters 请求Body
	 * @return Object ResponseData result
	 */
	protected Object post(String url, BodyInserter<?, ? super ClientHttpRequest> bodyInserters) {
		Mono<ResponseData> mono = client.post().uri(url)
				.header(HttpHeaders.AUTHORIZATION, token)
				.body(bodyInserters)
				.retrieve().bodyToMono(ResponseData.class);
		return mono.block().getResult();
	}

	/**
	 * PUT request
	 * 
	 * @param url           请求地址
	 * @param bodyInserters 请求Body
	 * @return Object ResponseData result
	 */
	protected Object put(String url, BodyInserter<?, ? super ClientHttpRequest> bodyInserters) {
		Mono<ResponseData> mono = client.put().uri(url)
				.header(HttpHeaders.AUTHORIZATION, token)
				.body(bodyInserters)
				.retrieve().bodyToMono(ResponseData.class);
		return mono.block().getResult();
	}

	/**
	 * DELETE request
	 * 
	 * @param url           请求地址
	 * @param bodyInserters 请求Body
	 * @return Object ResponseData result
	 */
	protected Object delete(String url, BodyInserter<?, ? super ClientHttpRequest> bodyInserters) {
		Mono<ResponseData> mono = client.method(HttpMethod.DELETE).uri(url)
				.header(HttpHeaders.AUTHORIZATION, token)
				.body(bodyInserters)
				.retrieve().bodyToMono(ResponseData.class);
		return mono.block().getResult();
	}
}
