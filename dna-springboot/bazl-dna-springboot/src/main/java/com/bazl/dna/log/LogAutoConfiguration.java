package com.bazl.dna.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.bazl.dna.log.aspect.SysLogAspect;
import com.bazl.dna.log.event.SysLogListener;
import com.bazl.dna.sys.service.ISysLogService;

/**
 * SysLog Configuration
 *
 * @author zhaoyong
 *
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class LogAutoConfiguration {

	private final ISysLogService sysLogService;

	/**
	 * Constructor
	 *
	 * @param sysLogService ISysLogService
	 */
	public LogAutoConfiguration(ISysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	/**
	 * new SysLogListener
	 *
	 * @return SysLogListener
	 */
	@Bean
	public SysLogListener sysLogListener() {
		return new SysLogListener(this.sysLogService);
	}

	/**
	 * new SysLogAspect
	 *
	 * @param publisher ApplicationEventPublisher
	 * @return SysLogAspect
	 */
	@Bean
	public SysLogAspect sysLogAspect(ApplicationEventPublisher publisher) {
		return new SysLogAspect(publisher);
	}
}
