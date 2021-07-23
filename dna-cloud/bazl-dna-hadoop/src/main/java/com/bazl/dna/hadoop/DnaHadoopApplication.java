package com.bazl.dna.hadoop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hadoop服务
 * @author zhaoyong
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@ComponentScan({ "com.bazl.dna.hadoop" })
public class DnaHadoopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnaHadoopApplication.class, args);
	}

}
