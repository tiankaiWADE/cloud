package com.bazl.dna.dubbo.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Dubbo 消费者服务
 * @author zhaoyong
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDubbo
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.bazl.dna.dubbo" })
public class DnaDubboConsumerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DnaDubboConsumerApplication.class, args);
	}

}
