package com.bazl.dna.gateway.config;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import com.alibaba.fastjson.JSON;
import com.bazl.dna.gateway.constants.ResponseData;
import com.bazl.dna.gateway.jwt.JwtUtils;
import com.google.common.base.Strings;

import reactor.core.publisher.Mono;

/**
 * 过滤器设置
 * @author zhaoyong
 */
public class GlobalConfiguration {

	@Value("${ACCESS_TOKEN_SECRET}")
	private String secret;

	@Value("${no_author}")
	private String requestPath;

	@Bean
	@Order(-1)
	public GlobalFilter tokenFilter() {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			Set<URI> set = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
			List<URI> urls = new ArrayList<>(set);
			String uri = urls.get(0).getPath();
			if (!Strings.isNullOrEmpty(requestPath)) {
				List<String> paths = Arrays.asList(requestPath.split(","));
				if (paths.stream().anyMatch(path -> path.equals(uri))) {
					return chain.filter(exchange.mutate().request(request).build());
				}
			}
			List<String> authList = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
			if (authList != null && !authList.isEmpty()) {
				// 判断是否有效
				boolean flag = JwtUtils.validateToken(authList.get(0), secret);
				if (flag) {
					return chain.filter(exchange.mutate().request(request).build());
				}
			}
			ServerHttpResponse response = exchange.getResponse();
			ResponseData responseData = new ResponseData(HttpStatus.SC_UNAUTHORIZED, "token验证不通过");
			DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(responseData).getBytes());
			response.setRawStatusCode(HttpStatus.SC_UNAUTHORIZED);
			response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			return response.writeWith(Mono.just(buffer));
		};

	}

}
