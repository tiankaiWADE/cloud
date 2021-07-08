package com.bazl.dna.common.config;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bazl.dna.common.resolver.CurrentUserMethodArgumentResolver;
import com.bazl.dna.interceptor.TokenInterceptor;

/**
 * 构造拦截器
 * 
 * @author zhaoyong
 *
 */
@Configuration
public class CurrentUserConfigurer implements WebMvcConfigurer {
	
	private final ApplicationEventPublisher publisher;
	
	public CurrentUserConfigurer(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**");
	}

	/**
	 * token 拦截器
	 * 
	 * @return TokenInterceptor
	 */
	@Bean
	public TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor(publisher);
	}

	/**
	 * 注册参数
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(currentUserMethodArgumentResolver());
	}

	/**
	 * CurrentUser 参数注解
	 * 
	 * @return CurrentUserMethodArgumentResolver
	 */
	@Bean
	public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
		return new CurrentUserMethodArgumentResolver();
	}
}
