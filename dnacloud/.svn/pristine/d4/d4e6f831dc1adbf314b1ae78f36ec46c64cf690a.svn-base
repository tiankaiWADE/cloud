package com.bazl.dna.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * 测试服务
 * @author zhaoyong
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@MapperScan({ "com.bazl.dna.test.mapper" })
@ComponentScan({ "com.bazl.dna.common", "com.bazl.dna.test" })
public class DnaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnaTestApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
	}
}
