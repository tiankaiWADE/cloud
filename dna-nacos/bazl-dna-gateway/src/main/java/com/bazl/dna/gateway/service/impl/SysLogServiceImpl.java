package com.bazl.dna.gateway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bazl.dna.gateway.dao.ISysLogDao;
import com.bazl.dna.gateway.entity.SysLog;
import com.bazl.dna.gateway.service.SysLogService;

/**
 * 系统日志监听实现类
 * @author zhaoyong
 */
@Service
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired
	private ISysLogDao dao;
	
	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void save(SysLog sysLog) {
		dao.save(sysLog);
	}

}
