package com.bazl.dna.sys.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.bazl.dna.common.query.Restrictions;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.dao.ISysDictItemJPADao;
import com.bazl.dna.sys.entity.SysDictItem;
import com.bazl.dna.sys.service.ISysDictItemService;

/**
 * ISysDictItemService 实现类
 * @author zhaoyong
 */
@Service
public class SysDictItemServiceImpl implements ISysDictItemService {

	private static final String CACHE_NAME = "SysDictItem";
	private static final String CACHE_TYPE_NAME = "SysDictItemType";

	@Autowired
	private ISysDictItemJPADao dao;

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@RedisCachePut(value = CACHE_NAME, key = PublicConstants.KEY_ID)
	@Caching(evict = {
		@CacheEvict(value = CACHE_NAME, allEntries = true),
		@CacheEvict(value = CACHE_TYPE_NAME, allEntries = true)
	})
	public SysDictItem save(SysDictItem item) {
		return dao.save(item);
	}

	@Override
	@RedisCacheAble(value = CACHE_NAME, key = PublicConstants.KEY_ID)
	public SysDictItem getById(String id) {
		Optional<SysDictItem> optional = dao.findById(id);
		return optional.orElse(null);
	}

	@Override
	@Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
	public Page<SysDictItem> pageList(JSONObject paramJson) {
		QueryCriteriaBean data = JSON.toJavaObject(paramJson, QueryCriteriaBean.class);
		Pageable pageable = QueryUtils.buildPageRequest(data);
		return dao.findAll(getCriteria(paramJson), pageable);
	}
	
	@Override
	@Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
	public List<SysDictItem> findList(JSONObject paramJson) {
		Criteria<SysDictItem> criteria = getCriteria(paramJson);
		criteria.add(Restrictions.eq(SysConstants.STATUS, PublicConstants.STATUS_YES));
		return dao.findAll(criteria, Sort.by(SysConstants.ITEM_ORDER));
	}
	
	private Criteria<SysDictItem> getCriteria(JSONObject paramJson) {
		Criteria<SysDictItem> criteria = new Criteria<>();
		criteria.add(Restrictions.eq(SysConstants.TYPE_ID, paramJson.getString(SysConstants.TYPE_ID)));
		criteria.add(Restrictions.like(SysConstants.ITEM_NAME, paramJson.getString(SysConstants.ITEM_NAME)));
		criteria.add(Restrictions.like(SysConstants.ITEM_CODE, paramJson.getString(SysConstants.ITEM_CODE)));
		criteria.add(Restrictions.eq(SysConstants.STATUS, paramJson.getString(SysConstants.STATUS)));
		return criteria;
	}

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@Caching(evict = {
		@CacheEvict(value = CACHE_NAME, allEntries = true),
		@CacheEvict(value = CACHE_TYPE_NAME, allEntries = true)
	})
	public int deleteById(String id) {
		dao.deleteById(id);
		return 1;
	}
	
	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@Caching(evict = {
		@CacheEvict(value = CACHE_NAME, allEntries = true),
		@CacheEvict(value = CACHE_TYPE_NAME, allEntries = true)
	})
	public Integer editStatus(String id, String status) {
		SysDictItem entity = this.getById(id);
		entity.setStatus(status);
		this.save(entity);
		return 1;
	}

}
