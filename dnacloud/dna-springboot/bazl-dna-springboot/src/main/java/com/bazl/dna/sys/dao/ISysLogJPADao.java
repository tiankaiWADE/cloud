package com.bazl.dna.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bazl.dna.sys.entity.SysLog;

/**
 * 日志接口
 * @author zhaoyong
 */
public interface ISysLogJPADao extends JpaRepository<SysLog, String>, JpaSpecificationExecutor<SysLog> {

}
