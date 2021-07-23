package com.bazl.dna.test.common;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.util.RequestUtils;

import reactor.core.publisher.Mono;

public class ReactiveTest {
	
	public static final String URL = "http://localhost:8704";

	@Test
	public void testReactive() {
		ClientHttpConnector httpConnector = new ReactorClientHttpConnector();
		WebClient client = WebClient.builder().clientConnector(httpConnector).baseUrl(URL).build();
		
		// auth
		String url = "/system/auth/login";
		JSONObject paramJson = new JSONObject();
		paramJson.put("userName", "admin");
		paramJson.put("password", "1");
		Mono<JSONObject> mono = client.post().uri(url)
				.body(BodyInserters.fromValue(paramJson))
				.retrieve().bodyToMono(JSONObject.class);
		String token = mono.block().getJSONObject("result").getString(RequestUtils.ACCESS_TOKEN);
		System.out.println(token);
		System.out.println();
		
		// get
		String u1 = "/system/user/get/1";
		Mono<JSONObject> m1 = client.get().uri(u1)
				.header(HttpHeaders.AUTHORIZATION, token)
				.retrieve().bodyToMono(JSONObject.class);
		System.out.println(m1.block());
		System.out.println();
		
		// post
		String u2 = "/system/user/list";
		Mono<JSONObject> m2 = client.post().uri(u2)
				.header(HttpHeaders.AUTHORIZATION, token)
				.body(BodyInserters.fromValue(new JSONObject()))
				.retrieve().bodyToMono(JSONObject.class);
		System.out.println(m2.block());
		System.out.println();
	}

}
