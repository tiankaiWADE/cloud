package com.bazl.dna.common.feign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.bazl.dna.common.feign.encoder.FeignEncoder;

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
