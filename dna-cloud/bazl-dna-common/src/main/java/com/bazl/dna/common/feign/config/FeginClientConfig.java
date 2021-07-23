package com.bazl.dna.common.feign.config;

import com.bazl.dna.util.RequestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;

/**
 * 请求拦截器
 * @author zhaoyong
 */
@Component
public class FeginClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeginClientConfig.class);

    @Bean
    public RequestInterceptor headerInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            try {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, RequestUtils.getToken());
            } catch (Exception e) {
                LOGGER.error("Interceptor:", e);
            }
        };
    }

}
