package com.bazl.dna.elasticsearch.service.impl;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.query.AttributeBean;
import com.bazl.dna.common.query.QueryCriteriaBean;
import com.bazl.dna.common.query.QueryUtils;
import com.bazl.dna.elasticsearch.dao.ISampleGeneElasticsearchDao;
import com.bazl.dna.elasticsearch.entity.SampleGene;
import com.bazl.dna.elasticsearch.service.ISampleGeneElasticsearchService;
import com.bazl.dna.util.DataUtils;

/**
 * SampleGeneElasticsearchService实现类
 * @author zhaoyong
 */
@Service
public class SampleGeneElasticsearchServiceImpl implements ISampleGeneElasticsearchService {

	@Autowired
	private ISampleGeneElasticsearchDao dao;
	
	@SuppressWarnings("deprecation")
	@Override
	public Iterable<SampleGene> findList(JSONObject paramJson) {
		QueryCriteriaBean data = JSON.toJavaObject(paramJson, QueryCriteriaBean.class);
		Pageable pageable = QueryUtils.buildPageRequest(data);
		
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if (!data.getWhereList().isEmpty()) {
			for (AttributeBean entity : data.getWhereList()) {
				if (!DataUtils.isEmpty(entity.getVal())) {
					queryBuilder.filter(QueryBuilders.termQuery(entity.getKey(), entity.getVal()));
				}
			}
		}
		return dao.search(queryBuilder, pageable);
	}

	@Override
	public Iterable<SampleGene> saveAll(List<SampleGene> list) {
		return dao.saveAll(list);
	}

	@Override
	public SampleGene save(SampleGene entity) {
		return dao.save(entity);
	}

	@Override
	public SampleGene findById(String id) {
		Optional<SampleGene> optional = dao.findById(id);
		return optional.orElse(null);
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

}
