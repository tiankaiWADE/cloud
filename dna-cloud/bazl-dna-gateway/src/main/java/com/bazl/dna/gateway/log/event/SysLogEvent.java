package com.bazl.dna.gateway.log.event;

import com.bazl.dna.gateway.entity.SysLog;

/**
 * 系统日志监听
 * @author zhaoyong
 */
public class SysLogEvent {
	
	private SysLog sysLog;

	public SysLogEvent(SysLog sysLog) {
		super();
		this.sysLog = sysLog;
	}

	/**
	 * Get SysLog
	 * @return the sysLog
	 */
	public SysLog getSysLog() {
		return sysLog;
	}
}
