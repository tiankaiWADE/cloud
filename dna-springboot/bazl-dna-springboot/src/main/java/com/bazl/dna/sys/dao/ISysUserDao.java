package com.bazl.dna.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bazl.dna.sys.entity.SysUser;

/**
 * 系统用户接口
 * @author zhaoyong
 */
public interface ISysUserDao extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {

}
