package com.bazl.dna;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.bazl.dna.log.init.ApplicationLoggerInitializer;

/**
 * 主函数启动服务
 * @author zhaoyong
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
@MapperScan({ "com.bazl.dna.**.mapper" })
@EnableElasticsearchRepositories(basePackages = "com.bazl.dna.elasticsearch.service")
public class DnaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		SpringApplication application = new SpringApplication(DnaApplication.class);
		application.addInitializers(new ApplicationLoggerInitializer());
		application.run(args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DnaApplication.class);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
