package com.bazl.dna.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bazl.dna.sys.entity.SysMessage;

/**
 * 消息接口
 * @author zhaoyong
 */
public interface ISysMessageDao extends JpaRepository<SysMessage, String>, JpaSpecificationExecutor<SysMessage> {

    /**
     * 查询列表
     * @param messageType 查询参数
     * @return List
     */
	@Query(value = "from SysMessage where messageType=?1")
    List<SysMessage> findList(String messageType);

}
