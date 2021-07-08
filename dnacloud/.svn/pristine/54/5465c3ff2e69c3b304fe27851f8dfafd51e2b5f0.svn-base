package com.bazl.dna.sys.service.impl;

import java.sql.Timestamp;
import java.util.List;
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
import com.bazl.dna.sys.dao.ISysMenuOperJPADao;
import com.bazl.dna.sys.dao.ISysMenuOperationJPADao;
import com.bazl.dna.sys.entity.SysMenuOperation;
import com.bazl.dna.sys.service.ISysMenuOperationService;
import com.google.common.base.Strings;

/**
 * 菜单操作接口实现类
 *
 * @author zhaoyong
 */
@Service
public class SysMenuOperationServiceImpl implements ISysMenuOperationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysMenuOperationServiceImpl.class);

    private static final String CACHE_NAME = "SysMenuOperation";

    @Autowired
    private ISysMenuOperationJPADao menuOperationJPADao;

    @Autowired
    private ISysMenuOperJPADao menuOperJPADao;

    @Override
    @Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
    public Page<SysMenuOperation> pageList(JSONObject paramJson) {
        try {
            QueryCriteriaBean data = JSON.toJavaObject(paramJson, QueryCriteriaBean.class);
            Pageable pageable = QueryUtils.buildPageRequest(data);
            Criteria<SysMenuOperation> criteria = QueryUtils.buildCriteria(data);
            return menuOperationJPADao.findAll(criteria, pageable);
        } catch (Exception e) {
            LOGGER.error("Error pageList: ", e);
        }
        return null;
    }

    @Override
    @Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
    public List<SysMenuOperation> findAll() {
        return menuOperationJPADao.findAll();
    }

    @Override
    @RedisCacheAble(value = CACHE_NAME, key = PublicConstants.KEY_ID)
    public SysMenuOperation getById(String id) {
        try {
            Optional<SysMenuOperation> optional = menuOperationJPADao.findById(id);
            return optional.orElse(null);
        } catch (Exception e) {
            LOGGER.error("Error getById: ", e);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = DnaRuntimeException.class)
    @RedisCachePut(value = CACHE_NAME, key = PublicConstants.KEY_ID)
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public SysMenuOperation save(SysMenuOperation menuOperation) {
        if (Strings.isNullOrEmpty(menuOperation.getId())) {
            menuOperation.setStatus(PublicConstants.STATUS_YES);
            menuOperation.setCreateTime(new Timestamp(System.currentTimeMillis()));
        }
        menuOperation.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return menuOperationJPADao.save(menuOperation);
    }

    @Override
    @Transactional(rollbackFor = DnaRuntimeException.class)
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public int deleteById(String id) {
        // 删除菜单与操作关联
        menuOperJPADao.deleteByOperationId(id);
        // 删除操作与角色关联
        // 删除操作
        menuOperationJPADao.deleteById(id);
        return 1;
    }

    @Override
    @Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
    public List<String> selectByMenuId(String menuId) {
        return menuOperJPADao.selectByMenuId(menuId);
    }

    @Override
    public int checkType(String id, String type) {
        try {
            return menuOperationJPADao.countTypeById(id, type);
        } catch (Exception e) {
            LOGGER.error("Error selectByMenuId: ", e);
        }
        return 0;
    }

}
