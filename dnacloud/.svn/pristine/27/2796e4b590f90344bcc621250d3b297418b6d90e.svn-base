package com.bazl.dna.test.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.bazl.dna.common.cache.RedisCache;
import com.bazl.dna.datasource.config.DataSourceConfig;
import com.bazl.dna.datasource.constants.DataSourceConstants;
import com.bazl.dna.datasource.util.DataSourceUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 数据源信息
 * @author zhaoyong
 */
@Configuration
public class DynamicDataSourceConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceConfig.class);
	
	@Bean(name = "primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid")
	public DataSource primaryDataSource() {
		return new DruidDataSource();
	}
	
	@Primary
	@Bean(name = "dynamicDataSource")
	public DataSource dynamicDataSource() {
		// 配置默认数据源
		DataSource ds = primaryDataSource();

		// 配置多数据源
		HashMap<Object, Object> dataSourceMap = Maps.newHashMap();
		dataSourceMap.put(DataSourceConstants.DataSourceType.PRIMARY.name(), primaryDataSource());
		
		// 从默认数据库中加载数据源 如属性文件请自行添加
		List<Map<String, String>> list = Lists.newArrayList();
		try {
			String sql = "select `connect_name`, `db_name`, `ip_address`, `url`, `user_name`, `password`, `port`, `driver_name`, `ds_type` from nt_data_source_config";
			list = DataSourceUtil.execute(ds.getConnection(), sql);
		} catch (Exception e) {
			LOGGER.error("dynamicDataSource error:", e);
		}
		
		for (Map<String, String> map : list) {
			if (DataSourceConstants.DS_TYPE_DB.equals(map.get("ds_type"))) {
				// 动态数据源
				builder(map);
			} else if (DataSourceConstants.DS_TYPE_CACHE.equals(map.get("ds_type"))) {
				// 动态redis缓存
				RedisCache.builder(map);
			}
		}
		return ds;
	}
	
	public static DruidDataSource builder(Map<String, String> map) {
		try (DruidDataSource datasource = new DruidDataSource();) {
			datasource.setUrl(map.get("url"));
			datasource.setUsername(map.get("user_name"));
			datasource.setPassword(map.get("password"));
			datasource.setDriverClassName(map.get("driver_name"));
			datasource.setDbType("com.alibaba.druid.pool.DruidDataSource");

			datasource.setInitialSize(30);
			datasource.setMinIdle(50);
			datasource.setMaxActive(200);
			datasource.setMaxWait(60000);
			datasource.setTimeBetweenEvictionRunsMillis(60000);
			datasource.setMinEvictableIdleTimeMillis(300000);
			datasource.setValidationQuery("SELECT 1 FROM nt_sys_sequence");
			datasource.setTestWhileIdle(true);
			datasource.setTestOnBorrow(false);
			datasource.setTestOnReturn(false);
			DataSourceConfig.setDataSourceMap(map.get("connect_name"), datasource);
			return datasource;
		}
	}
	
}
