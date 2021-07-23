package com.bazl.dna.test.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.cache.RedisCache;
import com.bazl.dna.datasource.config.DataSourceConfig;
import com.bazl.dna.datasource.util.DataSourceUtil;
import com.google.common.collect.Maps;

/**
 * 测试数据源
 * @author zhaoyong
 */
@RestController
@RequestMapping("/test")
public class TestDataSourceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestDataSourceController.class);
	
	@Autowired
    private RedisCache redisCache;
	
	/**
	 * 获取不同的数据源
	 */
	@GetMapping("getDatasource")
	public ResponseData getDatasource() {
		try {
			Map<String, Object> result = Maps.newHashMap();
			
			DataSource ds = DataSourceConfig.getDataSourceMap("localhost");
			Connection connection = ds.getConnection();
			String sql = "select * from nt_sys_user";
			List<Map<String, String>> list = DataSourceUtil.execute(connection, sql);
			result.put("targetSource", list);
			return new ResponseData(result);
		} catch (SQLException e) {
			LOGGER.error("SQL Error:", e);
		}
		return new ResponseData();
	}
	
	/**
	 * 获取不同的缓存
	 */
	@GetMapping("getRedis")
	public ResponseData getRedis() {
		String key = "Test:redis:";
		redisCache.setCacheObject(key, "aaa");
		Object a = redisCache.getCacheObject(key);
		LOGGER.info("a:{}", a);
		
		redisCache.dynamicRedisConfig("redis_2");
		redisCache.setCacheObject(key, "bbb");
		Object b = redisCache.getCacheObject(key);
		LOGGER.info("b:{}", b);
		
		redisCache.dynamicRedisConfig("redis_1");
		Object c = redisCache.getCacheObject(key);
		LOGGER.info("c:{}", c);
		
		return new ResponseData();
	}
}
