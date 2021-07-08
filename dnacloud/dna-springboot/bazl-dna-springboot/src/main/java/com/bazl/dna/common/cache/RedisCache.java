package com.bazl.dna.common.cache;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.util.DataUtils;
import com.google.common.collect.Maps;

/**
 * spring redis 工具类
 *
 * @author zhaoyong
 **/
@Component
@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class RedisCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);

	private static final String LOCK_KEY = "Lock:";

	@Autowired
	public RedisTemplate redisTemplate;

	protected static final Map<String, LettuceConnectionFactory> DATA_SOURCE_MAP = Maps.newHashMap();

	private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

	public static void setRedis(String dbType) {
		LOGGER.info("切换到 [{}] Redis", dbType);
		HOLDER.set(dbType);
	}

	public static String getRedis() {
		return HOLDER.get();
	}

	public static void clearRedis() {
		HOLDER.remove();
	}

	public void dynamicRedisConfig(String dataType) {
		RedisCache.setRedis(dataType);
		LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();

		LettuceConnectionFactory factory = DATA_SOURCE_MAP.get(dataType);
		if (factory != null) {
			// 关闭连接池
			connectionFactory.destroy();
			getFactory(connectionFactory, factory.getHostName(), factory.getPassword(), factory.getPort(),
					factory.getDatabase());

			// 重新创建连接池
			connectionFactory.afterPropertiesSet();
			connectionFactory.resetConnection();
			redisTemplate.setConnectionFactory(connectionFactory);
		} else {
			LOGGER.error("dynamicRedisConfig error: {}", dataType);
		}
	}

	public static void builder(Map<String, String> map) {
		LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
		getFactory(connectionFactory, map.get("ip_address"), map.get("password"), Integer.parseInt(map.get("port")),
				Integer.parseInt(map.get("db_name")));
		RedisCache.DATA_SOURCE_MAP.put(map.get("connect_name"), connectionFactory);
	}

	@SuppressWarnings("deprecation")
	private static void getFactory(LettuceConnectionFactory connectionFactory, String hostName, String password,
			int port, int database) {
		connectionFactory.setHostName(hostName);
		if (!DataUtils.isEmpty(password)) {
			connectionFactory.setPassword(password);
		}
		connectionFactory.setPort(port);
		connectionFactory.setDatabase(database);
	}

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 *
	 * @param key   缓存的键值
	 * @param value 缓存的值
	 */
	public <T> void setCacheObject(final String key, final T value) {
		redisTemplate.opsForValue().set(key, value, PublicConstants.EXPIRE_TIME, TimeUnit.MINUTES);
	}

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 *
	 * @param key      缓存的键值
	 * @param value    缓存的值
	 * @param timeout  时间
	 * @param timeUnit 时间颗粒度
	 */
	public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}

	/**
	 * 设置有效时间
	 *
	 * @param key     Redis键
	 * @param timeout 超时时间
	 * @return true=设置成功；false=设置失败
	 */
	public boolean expire(final String key, final long timeout) {
		return expire(key, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 设置有效时间
	 *
	 * @param key     Redis键
	 * @param timeout 超时时间
	 * @param unit    时间单位
	 * @return true=设置成功；false=设置失败
	 */
	public boolean expire(final String key, final long timeout, final TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	/**
	 * 获得缓存的基本对象。
	 *
	 * @param key 缓存键值
	 * @return 缓存键值对应的数据
	 */
	public <T> T getCacheObject(final String key) {
		ValueOperations<String, T> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}

	/**
	 * 删除单个对象
	 *
	 * @param key 键
	 */
	public boolean deleteObject(final String key) {
		return redisTemplate.delete(key);
	}

	/**
	 * 删除集合对象
	 *
	 * @param collection 多个对象
	 * @return long
	 */
	public long deleteObject(final Collection<String> collection) {
		return redisTemplate.delete(collection);
	}

	/**
	 * 缓存List数据
	 *
	 * @param key      缓存的键值
	 * @param dataList 待缓存的List数据
	 * @return 缓存的对象
	 */
	public <T> long setCacheList(final String key, final List<T> dataList) {
		Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
		return count == null ? 0 : count;
	}

	/**
	 * 获得缓存的list对象
	 *
	 * @param key 缓存的键值
	 * @return 缓存键值对应的数据
	 */
	public <T> List<T> getCacheList(final String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	/**
	 * 缓存Set
	 *
	 * @param key     缓存键值
	 * @param dataSet 缓存的数据
	 * @return 缓存数据的对象
	 */
	public <T> long setCacheSet(final String key, final Set<T> dataSet) {
		Long count = redisTemplate.opsForSet().add(key, dataSet);
		return count == null ? 0 : count;
	}

	/**
	 * 获得缓存的set
	 *
	 * @param key 键
	 * @return Set
	 */
	public <T> Set<T> getCacheSet(final String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 缓存Map
	 *
	 * @param key     键
	 * @param dataMap 值
	 */
	public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
		if (dataMap != null) {
			redisTemplate.opsForHash().putAll(key, dataMap);
		}
	}

	/**
	 * 获得缓存的Map
	 *
	 * @param key 键
	 * @return Map
	 */
	public <T> Map<String, T> getCacheMap(final String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 往Hash中存入数据
	 *
	 * @param key   Redis键
	 * @param hKey  Hash键
	 * @param value 值
	 */
	public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
		redisTemplate.opsForHash().put(key, hKey, value);
	}

	/**
	 * 获取Hash中的数据
	 *
	 * @param key  Redis键
	 * @param hKey Hash键
	 * @return Hash中的对象
	 */
	public <T> T getCacheMapValue(final String key, final String hKey) {
		HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
		return opsForHash.get(key, hKey);
	}

	/**
	 * 获取多个Hash中的数据
	 *
	 * @param key   Redis键
	 * @param hKeys Hash键集合
	 * @return Hash对象集合
	 */
	public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
		return redisTemplate.opsForHash().multiGet(key, hKeys);
	}

	/**
	 * 获得缓存的基本对象列表
	 *
	 * @param pattern 字符串前缀
	 * @return 对象列表
	 */
	public Collection<String> keys(final String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * 获取客户端信息
	 * 
	 * @return List
	 */
	public List<String> getClientList() {
		return redisTemplate.getClientList();
	}

	/**
	 * 分布式锁
	 *
	 * @param key        键值
	 * @param expireTime 过期时间
	 * @return boolean
	 */
	public boolean lock(String key, long expireTime) {
		String lock = LOCK_KEY + key;

		return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
			long expireAt = System.currentTimeMillis() + expireTime + 1;
			boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());

			if (acquire) {
				return true;
			} else {
				byte[] value = connection.get(lock.getBytes());
				if (!DataUtils.isEmpty(value)) {
					long time = Long.parseLong(new String(value));
					if (time < System.currentTimeMillis()) {
						// 重新加锁，防止死锁
						byte[] oldValue = connection.getSet(lock.getBytes(),
								String.valueOf(System.currentTimeMillis() + expireTime + 1).getBytes());
						return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
					}
				}
			}
			return false;
		});
	}

}