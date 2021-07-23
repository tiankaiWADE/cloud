package com.bazl.dna.gateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bazl.dna.gateway.entity.SysLog;

/**
 * 系统日志接口
 * @author zhaoyong
 */
public interface ISysLogJPADao extends JpaRepository<SysLog, String>, JpaSpecificationExecutor<SysLog> {

}
