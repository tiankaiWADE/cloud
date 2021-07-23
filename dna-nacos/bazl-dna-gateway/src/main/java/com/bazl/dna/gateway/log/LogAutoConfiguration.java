package com.bazl.dna.gateway.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.bazl.dna.gateway.log.event.SysLogListener;
import com.bazl.dna.gateway.service.SysLogService;

/**
 * 日志监听
 * @author zhaoyong
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class LogAutoConfiguration {

	private final SysLogService sysLogService;

	public LogAutoConfiguration(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	@Bean
	public SysLogListener sysLogListener() {
		return new SysLogListener(this.sysLogService);
	}

}
