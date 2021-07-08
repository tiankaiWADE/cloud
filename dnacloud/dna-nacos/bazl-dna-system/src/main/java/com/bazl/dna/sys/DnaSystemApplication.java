package com.bazl.dna.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统服务
 * @author zhaoyong
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan({ "com.bazl.dna.sys.mapper" })
@ComponentScan({ "com.bazl.dna.common", "com.bazl.dna.sys","com.bazl.dna.api" })
public class DnaSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnaSystemApplication.class, args);
	}
	
}
