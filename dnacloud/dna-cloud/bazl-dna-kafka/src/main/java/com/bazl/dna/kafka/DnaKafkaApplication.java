package com.bazl.dna.kafka;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Kafka服务
 * @author zhaoyong
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@MapperScan({ "com.bazl.dna.kafka.mapper" })
@ComponentScan({ "com.bazl.dna.common", "com.bazl.dna.kafka" })
public class DnaKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnaKafkaApplication.class, args);
	}

}
