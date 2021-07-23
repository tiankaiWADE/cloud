package com.bazl.dna.gateway.service;

import com.bazl.dna.gateway.entity.SysLog;

/**
 * 系统日志
 * @author zhaoyong
 *
 */
public interface SysLogService {

	/**
	 * 保存日志
	 * @param sysLog 对象
	 */
	void save(SysLog sysLog);
}
