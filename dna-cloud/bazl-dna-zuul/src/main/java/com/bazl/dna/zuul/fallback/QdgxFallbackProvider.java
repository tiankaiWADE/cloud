package com.bazl.dna.zuul.fallback;

import com.bazl.dna.zuul.util.HttpResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.exception.HystrixTimeoutException;

/**
 * 异常拦截；全局异常处理
 * @author zhaoyong
 */
@Component
public class QdgxFallbackProvider implements FallbackProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(QdgxFallbackProvider.class);

	private static final String ERROR_MESSAGE = "{\n" + "\"code\": 500,\n" + "\"message\": \"微服务故障, 请稍后再试\"\n" + "}";

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		if (cause != null && cause.getCause() != null) {
            String reason = cause.getCause().getMessage();
            LOGGER.info("Excption {}",reason);
        }
		if (cause instanceof HystrixTimeoutException) {
			return HttpResponseUtil.response(HttpStatus.GATEWAY_TIMEOUT, ERROR_MESSAGE);
		} else {
			return fallbackResponse();
		}
	}

	@Override
	public String getRoute() {
		return "*";
	}


	public ClientHttpResponse fallbackResponse() {
		return HttpResponseUtil.response(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_MESSAGE);
	}
}
