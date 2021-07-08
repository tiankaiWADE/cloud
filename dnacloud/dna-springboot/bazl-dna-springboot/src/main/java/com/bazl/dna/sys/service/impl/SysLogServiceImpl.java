package com.bazl.dna.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.exception.DnaRuntimeException;
import com.bazl.dna.common.query.Criteria;
import com.bazl.dna.common.query.QueryCriteriaBean;
import com.bazl.dna.common.query.QueryUtils;
import com.bazl.dna.common.query.Restrictions;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.dao.ISysLogJPADao;
import com.bazl.dna.sys.entity.SysLog;
import com.bazl.dna.sys.service.ISysLogService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统日志接口实现类
 *
 * @author zhaoyong
 */
@Component
public class SysLogServiceImpl implements ISysLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysLogServiceImpl.class);

    @Autowired
    private ISysLogJPADao dao;

    @Override
    public Page<SysLog> pageList(JSONObject paramJson) {
        try {
            QueryCriteriaBean data = JSON.toJavaObject(paramJson, QueryCriteriaBean.class);
            Pageable pageable = QueryUtils.buildPageRequest(data);
            return dao.findAll(getCriteria(paramJson), pageable);
        } catch (Exception e) {
            LOGGER.error("Error pageList: ", e);
        }
        return null;
    }

    @Override
    public List<SysLog> findList(JSONObject paramJson) {
        return dao.findAll(getCriteria(paramJson));
    }

    private Criteria<SysLog> getCriteria(JSONObject paramJson) {
        Criteria<SysLog> criteria = new Criteria<>();
        criteria.add(Restrictions.like("methodName", paramJson.getString("methodName")));
        criteria.add(Restrictions.like("operationName", paramJson.getString("operationName")));
        criteria.add(Restrictions.like("operationIp", paramJson.getString("operationIp")));
        return criteria;
    }

    @Override
    public SysLog getById(String id) {
        try {
            Optional<SysLog> optional = dao.findById(id);
            return optional.orElse(null);
        } catch (Exception e) {
            LOGGER.error("Error getById: ", e);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = DnaRuntimeException.class)
    @Async
    public void save(SysLog sysLog) {
        dao.save(sysLog);
    }

    @Override
    @Transactional(rollbackFor = DnaRuntimeException.class)
    public int deleteById(String id) {
        dao.deleteById(id);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = DnaRuntimeException.class)
    public int deleteLog(Date date) {
        Criteria<SysLog> criteria = new Criteria<>();
        criteria.add(Restrictions.lte(SysConstants.CREATE_TIME, date));
        List<SysLog> list = dao.findAll(criteria);
        dao.deleteAll(list);
        return 1;
    }

}
