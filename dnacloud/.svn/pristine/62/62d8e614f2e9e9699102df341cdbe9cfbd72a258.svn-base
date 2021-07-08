package com.bazl.dna.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import feign.codec.Encoder;

/**
 * Feign调用解析
 * @author zhaoyong
 */
@Configuration
public class FeignSupportConfig {

	@Bean
	@Primary
	@Scope("prototype")
	public Encoder feignFormEncoder() {
		return new FeignEncoder();
	}
}
