package com.bazl.dna.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Cloud Gateway网关服务
 * @author zhaoyong
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({ "com.bazl.dna.gateway" })
public class SpringCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayApplication.class, args);
	}

}
