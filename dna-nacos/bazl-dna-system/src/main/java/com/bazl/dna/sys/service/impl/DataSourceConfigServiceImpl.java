package com.bazl.dna.sys.service.impl;

import java.sql.Timestamp;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.cache.annotation.RedisCacheAble;
import com.bazl.dna.common.cache.annotation.RedisCachePut;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.common.exception.DnaRuntimeException;
import com.bazl.dna.common.query.Criteria;
import com.bazl.dna.common.query.QueryCriteriaBean;
import com.bazl.dna.common.query.QueryUtils;
import com.bazl.dna.datasource.constants.DataSourceConstants;
import com.bazl.dna.sys.dao.IDataSourceDao;
import com.bazl.dna.sys.entity.DataSourceConfig;
import com.bazl.dna.sys.service.IDataSourceConfigService;
import com.bazl.dna.util.DataUtils;

/**
 * IDataSourceConfigService实现类
 * @author zhaoyong
 */
@Service
public class DataSourceConfigServiceImpl implements IDataSourceConfigService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfigServiceImpl.class);
	
	private static final String CACHE_NAME = "DataSourceConfig";
	
	@Autowired
	private IDataSourceDao dao;

	@Override
	@Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
	public Page<DataSourceConfig> pageList(JSONObject paramJson) {
		try {
			QueryCriteriaBean data = JSON.toJavaObject(paramJson, QueryCriteriaBean.class);
			if (paramJson.isEmpty()) {
				data.setPageSize(Integer.MAX_VALUE);
			}
			Pageable pageable = QueryUtils.buildPageRequest(data);
			Criteria<DataSourceConfig> criteria = QueryUtils.buildCriteria(data);
			return dao.findAll(criteria, pageable);
		} catch (Exception e) {
			LOGGER.error("Error pageList: ", e);
		}
		return null;
	}

	@Override
	@RedisCacheAble(value = CACHE_NAME, key = PublicConstants.KEY_ID)
	public DataSourceConfig getById(String id) {
		try {
			Optional<DataSourceConfig> optional = dao.findById(id);
			return optional.orElse(null);
		} catch (Exception e) {
			LOGGER.error("Error getById: ", e);
		}
		return null;	
	}
	
	@Override
	@Cacheable(value = CACHE_NAME, key = "#connectName")
	public DataSourceConfig getConnectName(String connectName) {
		return dao.getConnectName(connectName);
	}

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@RedisCachePut(value = CACHE_NAME, key = PublicConstants.KEY_ID)
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public DataSourceConfig save(DataSourceConfig entity) {
		if (DataUtils.isEmpty(entity.getId())) {
			entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
		}
		String driverName = null;
		String url = null;
		if (DataSourceConstants.DB_TYPE_MYSQL.equals(entity.getDbType())) {
			driverName = DataSourceConstants.DRIVER_NAME_MYSQL;
			url = "jdbc:mysql://" + entity.getIp() + ":" + entity.getPort() + "/" + entity.getDbName();
		} else if (DataSourceConstants.DB_TYPE_ORACLE.equals(entity.getDbType())) {
			driverName = DataSourceConstants.DRIVER_NAME_ORACLE;
			url = "jdbc:oracle:thin:@" + entity.getIp() + ":" + entity.getPort() + ":" + entity.getDbName();
		}
		entity.setDriverName(driverName);
		entity.setUrl(url);
		entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		return dao.save(entity);
	}

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public int deleteById(String id) {
		dao.deleteById(id);
		return 1;
	}

}
