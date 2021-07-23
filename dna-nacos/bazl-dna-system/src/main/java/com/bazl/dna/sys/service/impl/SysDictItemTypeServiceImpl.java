package com.bazl.dna.sys.service.impl;

import java.util.ArrayList;
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
import com.alibaba.fastjson.JSONArray;
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
import com.bazl.dna.sys.dao.ISysDictItemTypeDao;
import com.bazl.dna.sys.entity.SysDictItem;
import com.bazl.dna.sys.entity.SysDictItemType;
import com.bazl.dna.sys.service.ISysDictItemService;
import com.bazl.dna.sys.service.ISysDictItemTypeService;
import com.bazl.dna.util.DataUtils;

/**
 * ISysDictItemTypeService实现类
 * @author zhaoyong
 */
@Service
public class SysDictItemTypeServiceImpl implements ISysDictItemTypeService {

	private static final String CACHE_NAME = "SysDictItemType";
	private static final String CACHE_ITEM_NAME = "SysDictItem";

	@Autowired
	private ISysDictItemTypeDao dao;
	
	@Autowired
	private ISysDictItemService sysDictItemService;

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@RedisCachePut(value = CACHE_NAME, key = PublicConstants.KEY_ID)
	@Caching(evict = {
		@CacheEvict(value = CACHE_NAME, allEntries = true),
		@CacheEvict(value = CACHE_ITEM_NAME, allEntries = true)
	})
	public SysDictItemType save(SysDictItemType type) {
		if (DataUtils.isEmpty(type.getId())) {
			type.setId(type.getTypeCode());
		}
		return dao.save(type);
	}

	@Override
	@RedisCacheAble(value = CACHE_NAME, key = PublicConstants.KEY_ID)
	public SysDictItemType getById(String id) {
		Optional<SysDictItemType> optional = dao.findById(id);
		return optional.orElse(null);
	}

	@Override
	@Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
	public Page<SysDictItemType> pageList(JSONObject paramJson) {
		QueryCriteriaBean data = JSON.toJavaObject(paramJson, QueryCriteriaBean.class);
		Pageable pageable = QueryUtils.buildPageRequest(data);
		return dao.findAll(getCriteria(paramJson), pageable);
	}
	
	@Override
	@Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
	public List<SysDictItemType> findList(JSONObject paramJson) {
		Criteria<SysDictItemType> criteria = getCriteria(paramJson);
		criteria.add(Restrictions.eq(SysConstants.STATUS, PublicConstants.STATUS_YES));
		return dao.findAll(criteria, Sort.by(SysConstants.TYPE_ORDER));
	}
	
	private Criteria<SysDictItemType> getCriteria(JSONObject paramJson) {
		Criteria<SysDictItemType> criteria = new Criteria<>();
		criteria.add(Restrictions.like(SysConstants.TYPE_NAME, paramJson.getString(SysConstants.TYPE_NAME)));
		criteria.add(Restrictions.like(SysConstants.TYPE_CODE, paramJson.getString(SysConstants.TYPE_CODE)));
		criteria.add(Restrictions.eq(SysConstants.STATUS, paramJson.getString(SysConstants.STATUS)));
		return criteria;
	}

	@Override
	public int checkTypeId(String id, String code) {
		return dao.countTypeById(id, code);
	}

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@Caching(evict = {
		@CacheEvict(value = CACHE_NAME, allEntries = true),
		@CacheEvict(value = CACHE_ITEM_NAME, allEntries = true)
	})
	public int deleteById(String id) {
		JSONObject paramJson = new JSONObject();
		paramJson.put(SysConstants.TYPE_ID, id);
		List<SysDictItem> dictItemList = sysDictItemService.findList(paramJson);
		dictItemList.forEach(dictItem -> sysDictItemService.deleteById(dictItem.getId()));
		dao.deleteById(id);
		return 1;
	}

	@Override
	@Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
	public List<SysDictItemType> findListByCodes(JSONObject json) {
		JSONArray codeArray = json.getJSONArray("codes");
		if (!codeArray.isEmpty()) {
			List<String> codes = JSON.parseArray(codeArray.toJSONString(), String.class);
			return dao.findListByCode(codes);
		}
		return new ArrayList<>();
	}

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@Caching(evict = {
		@CacheEvict(value = CACHE_NAME, allEntries = true),
		@CacheEvict(value = CACHE_ITEM_NAME, allEntries = true)
	})
	public Integer editStatus(String id, String status) {
		SysDictItemType entity = this.getById(id);
		entity.setStatus(status);
		this.save(entity);
		return 1;
	}

}
