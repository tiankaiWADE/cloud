package com.bazl.dna.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Cloud Eureka服务
 * @author zhaoyong
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaApplication extends WebSecurityConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaApplication.class, args);
	}

	/**
	 * Spring Security 默认开启了所有 CSRF 攻击防御，需要禁用 /eureka 的防御
	 * @param http HttpSecurity
	 * @throws Exception 异常
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
