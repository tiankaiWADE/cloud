package com.bazl.dna.sys.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bazl.dna.common.cache.annotation.RedisCacheAble;
import com.bazl.dna.common.cache.annotation.RedisCachePut;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.common.exception.DnaRuntimeException;
import com.bazl.dna.sys.dao.ISysMessageDao;
import com.bazl.dna.sys.entity.SysMessage;
import com.bazl.dna.sys.service.ISysMessageService;
import com.google.common.collect.Lists;

/**
 * 消息接口实现类
 *
 * @author zhaoyong
 */
@Service
public class SysMessageServiceImpl implements ISysMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysMessageServiceImpl.class);

    private static final String CACHE_NAME = "SysMessage";

    @Autowired
    private ISysMessageDao sysMessageDao;

    @Override
    @Transactional(rollbackFor = DnaRuntimeException.class)
    @RedisCachePut(value = CACHE_NAME, key = PublicConstants.KEY_ID)
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public SysMessage save(SysMessage sysMessage) {
        return sysMessageDao.save(sysMessage);
    }

    @Override
    @RedisCacheAble(value = CACHE_NAME, key = PublicConstants.KEY_ID)
    public SysMessage getById(String id) {
        try {
            Optional<SysMessage> optional = sysMessageDao.findById(id);
            return optional.orElse(null);
        } catch (Exception e) {
            LOGGER.error("Error getById:", e);
        }
        return null;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "#messageType")
    public List<SysMessage> findList(String messageType) {
        try {
            return sysMessageDao.findList(messageType);
        } catch (Exception e) {
            LOGGER.error("Error findList: ", e);
        }
        return Lists.newArrayList();
    }

    @Override
    @Transactional(rollbackFor = DnaRuntimeException.class)
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public int deleteById(String id) {
        sysMessageDao.deleteById(id);
        return 1;
    }

}
