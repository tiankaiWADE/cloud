package com.bazl.dna.sys.dao;

import java.util.List;

import com.bazl.dna.common.exception.DnaRuntimeException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.bazl.dna.sys.entity.SysUserJob;

/**
 * 用户职位权限接口
 *
 * @author zhaoyong
 */
public interface ISysUserJobJPADao extends JpaRepository<SysUserJob, String>, JpaSpecificationExecutor<SysUserJob> {

    /**
     * 查询用户职位权限列表
     *
     * @param userId 用户编号
     * @return List
     */
    @Query(value = "select o.id from nt_sys_job o, nt_sys_user_job s where o.id=s.job_id and s.user_id=?1", nativeQuery = true)
    List<String> findUserJobByUserId(String userId);

    /**
     * 删除用户职位权限
     *
     * @param userId 用户编号
     */
    @Transactional(rollbackFor = DnaRuntimeException.class)
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from SysUserJob where sysUser.id=?1 ")
    void deleteUserJobByUserId(String userId);
}
