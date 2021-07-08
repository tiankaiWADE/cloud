package com.bazl.dna.gateway.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSON;
import com.bazl.dna.gateway.constants.GatewayConstants;

import reactor.core.publisher.Mono;

/**
 * 网关统一异常处理
 *
 * @author zhaoyong
 */
@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GatewayExceptionHandler.class);

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();

		if (response.isCommitted()) {
			return Mono.error(ex);
		}

		Map<String, Object> errorAttributes = new HashMap<>(8);
        errorAttributes.put(GatewayConstants.METHOD, request.getMethod().name());
        errorAttributes.put(GatewayConstants.PATH, request.getPath().value());

		if (ex instanceof NotFoundException) {
			errorAttributes.put(GatewayConstants.CODE, 503);
			errorAttributes.put(GatewayConstants.MESSAGE, "服务未找到，请联系管理员!");
		} else if (ex instanceof ResponseStatusException) {
			ResponseStatusException responseStatusException = (ResponseStatusException) ex;
			errorAttributes.put(GatewayConstants.RESULT, responseStatusException.getMessage());
			errorAttributes.put(GatewayConstants.CODE, 500);
		} else {
			errorAttributes.put(GatewayConstants.CODE, 500);
			errorAttributes.put(GatewayConstants.MESSAGE, "服务器错误，请联系管理员!");
		}

		log.error("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), ex.getMessage());

		response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
		response.setStatusCode(HttpStatus.OK);

		return response.writeWith(Mono.fromSupplier(() -> {
			DataBufferFactory bufferFactory = response.bufferFactory();
			return bufferFactory.wrap(JSON.toJSONBytes(errorAttributes));
		}));
	}
}