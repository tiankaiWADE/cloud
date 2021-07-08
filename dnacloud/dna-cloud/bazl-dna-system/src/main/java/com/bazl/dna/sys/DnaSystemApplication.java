package com.bazl.dna.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * 系统服务
 * @author zhaoyong
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@MapperScan({ "com.bazl.dna.sys.mapper" })
@ComponentScan({ "com.bazl.dna.common", "com.bazl.dna.sys","com.bazl.dna.api" })
public class DnaSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnaSystemApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
	}

}
