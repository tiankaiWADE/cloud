package com.bazl.dna.dfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

import com.github.tobato.fastdfs.FdfsClientConfig;

/**
 * 文件模块
 * @author zhaoyong
 */
@SpringBootApplication
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({ "com.bazl.dna.common", "com.bazl.dna.dfs" })
public class DnaFilesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DnaFilesApplication.class, args);
	}

}
