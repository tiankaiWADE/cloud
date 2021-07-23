package com.bazl.dna.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bazl.dna.sys.entity.SysDictItem;

/**
 * 字典接口
 * @author zhaoyong
 */
public interface ISysDictItemDao extends JpaRepository<SysDictItem, String>, JpaSpecificationExecutor<SysDictItem> {

}
