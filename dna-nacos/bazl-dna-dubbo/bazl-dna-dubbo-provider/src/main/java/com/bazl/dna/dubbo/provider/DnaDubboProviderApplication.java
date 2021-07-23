package com.bazl.dna.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Dubbo 提供者服务
 * @author zhaoyong
 */
@EnableDubbo
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.bazl.dna.dubbo" })
public class DnaDubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnaDubboProviderApplication.class, args);
	}

}
