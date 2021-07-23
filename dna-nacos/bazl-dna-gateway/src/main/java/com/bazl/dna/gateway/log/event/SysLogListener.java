package com.bazl.dna.gateway.log.event;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import com.bazl.dna.gateway.service.SysLogService;

/**
 * 系统日志监听
 * @author zhaoyong
 */
public class SysLogListener {

	private final SysLogService sysLogService;
	
	public SysLogListener(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	@Async
	@Order
	@EventListener({ SysLogEvent.class })
	public void saveSysLog(SysLogEvent event) {
		this.sysLogService.save(event.getSysLog());
	}
}
