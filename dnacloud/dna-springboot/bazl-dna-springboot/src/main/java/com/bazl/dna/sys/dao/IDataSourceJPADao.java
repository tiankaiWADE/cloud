package com.bazl.dna.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bazl.dna.sys.entity.DataSourceConfig;

/**
 * 数据源接口
 * @author zhaoyong
 */
public interface IDataSourceJPADao
		extends JpaRepository<DataSourceConfig, String>, JpaSpecificationExecutor<DataSourceConfig> {

}
