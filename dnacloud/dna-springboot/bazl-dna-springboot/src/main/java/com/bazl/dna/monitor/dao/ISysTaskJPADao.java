package com.bazl.dna.monitor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bazl.dna.monitor.entity.SysTask;

/**
 * 系统日志接口
 * @author zhaoyong
 */
public interface ISysTaskJPADao extends JpaRepository<SysTask, String>,JpaSpecificationExecutor<SysTask>{
	
}
