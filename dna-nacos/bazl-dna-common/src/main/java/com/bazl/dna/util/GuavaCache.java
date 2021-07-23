package com.bazl.dna.util;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * Guava cache
 * 
 * @author zhaoyong
 *
 */
public class GuavaCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(GuavaCache.class);

	/**
	 * 设置并发级别，并发级别是指可以同时写缓存的线程数
	 */
	private static final int CONCURRENCY_LEVEL = 200;

	/**
	 * 设置缓存容器的初始容量
	 */
	private static final int INITIAL_CAPACITY = 1000;

	/**
	 * 设置缓存最大容量为1000，超过1000之后就会按照LRU最近最少使用算法来移除缓存项
	 */
	private static final int MAXIMUM_SIZE = 100000;

	/**
	 * 设置写缓存过期时间 (天)
	 */
	private static final int EXPIRE_AFTER_WRITE = 1;

	/**
	 * 缓存对象
	 */
	private static LoadingCache<Object, Set<String>> cache;

	private GuavaCache() {
	}

	/**
	 * @return the cache
	 */
	public static LoadingCache<Object, Set<String>> getCache() {
		return cache;
	}

	/**
	 * @param cache the cache to set
	 */
	public static void setCache(LoadingCache<Object, Set<String>> cache) {
		GuavaCache.cache = cache;
	}

	/**
	 * 清除缓存
	 */
	public static void clear() {
		if (getCache() != null) {
			getCache().invalidateAll();
		}
	}

	/**
	 * 创建Cache
	 * 
	 * @param key
	 * @param entity
	 * @return T
	 */
	public static <T> LoadingCache<Object, T> build(final Object key, final T entity) {
		// build
		return CacheBuilder.newBuilder().concurrencyLevel(CONCURRENCY_LEVEL)
				.expireAfterWrite(EXPIRE_AFTER_WRITE, TimeUnit.DAYS).initialCapacity(INITIAL_CAPACITY)
				.maximumSize(MAXIMUM_SIZE).recordStats().removalListener(new RemovalListener<Object, Object>() {
					@Override
					public void onRemoval(RemovalNotification<Object, Object> notification) {
						LOGGER.debug("{} was removed, cause is {}", key, notification.getCause());
					}
				}).build(new CacheLoader<Object, T>() {
					@Override
					public T load(Object key) throws Exception {
						return entity;
					}
				});
	}
	
}
