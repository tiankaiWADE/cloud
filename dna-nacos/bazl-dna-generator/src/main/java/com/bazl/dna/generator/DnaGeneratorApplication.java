package com.bazl.dna.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 生成代码服务
 * @author zhaoyong
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({ "com.bazl.dna.generator.mapper" })
@ComponentScan({ "com.bazl.dna.common", "com.bazl.dna.generator" })
public class DnaGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnaGeneratorApplication.class, args);
	}

}
