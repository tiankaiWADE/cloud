package com.bazl.dna.files.service.impl;

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
import com.bazl.dna.files.dao.IFastdfsFileDao;
import com.bazl.dna.files.entity.FastdfsFile;
import com.bazl.dna.files.service.IFastdfsFileService;
import com.bazl.dna.sys.constants.SysConstants;

/**
 * fastdfs工具实现类
 *
 * @author zhaoyong
 */
@Service
public class FastdfsFileServiceImpl implements IFastdfsFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastdfsFileServiceImpl.class);

    private static final String CACHE_NAME = "FastdfsFile";

    @Autowired
    private IFastdfsFileDao dao;

    @Override
    @Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
    public Page<FastdfsFile> pageList(JSONObject paramJson) {
        try {
            QueryCriteriaBean data = JSON.toJavaObject(paramJson, QueryCriteriaBean.class);
            Pageable pageable = QueryUtils.buildPageRequest(data);
            return dao.findAll(getCriteria(paramJson), pageable);
        } catch (Exception e) {
            LOGGER.error("Error pageList: ", e);
        }
        return null;
    }

    private Criteria<FastdfsFile> getCriteria(JSONObject paramJson) {
        Criteria<FastdfsFile> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("displayName", paramJson.getString("displayName")));
        return criteria;
    }

    @Override
    @Cacheable(value = CACHE_NAME, keyGenerator = PublicConstants.KEY_GENERATOR)
    public List<FastdfsFile> findList(JSONObject paramJson) {
        Criteria<FastdfsFile> criteria = getCriteria(paramJson);
        return dao.findAll(criteria, Sort.by(SysConstants.CREATE_TIME));
    }

    @Override
    @RedisCacheAble(value = CACHE_NAME, key = PublicConstants.KEY_ID)
    public FastdfsFile getById(String id) {
        try {
            Optional<FastdfsFile> optional = dao.findById(id);
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
    public FastdfsFile save(FastdfsFile entity) {
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
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
