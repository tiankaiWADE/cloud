package com.bazl.dna.elasticsearch.service.impl;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository.OperationsCallback;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.query.QueryCriteriaBean;
import com.bazl.dna.common.query.QueryUtils;
import com.bazl.dna.elasticsearch.dao.ISampleGeneElasticsearchDao;
import com.bazl.dna.elasticsearch.entity.SampleGene;
import com.bazl.dna.elasticsearch.service.ISampleGeneElasticsearchService;
import com.bazl.dna.util.DataUtils;

/**
 * SampleGeneElasticsearch实现类
 * @author zhaoyong
 */
@Service
public class SampleGeneElasticsearchServiceImpl implements ISampleGeneElasticsearchService {
	
	private final ElasticsearchOperations operations;

	private final ISampleGeneElasticsearchDao dao;

	public SampleGeneElasticsearchServiceImpl(ISampleGeneElasticsearchDao dao, ElasticsearchOperations operations) {
		this.dao = dao;
		this.operations = operations;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<SampleGene> findList(JSONObject paramJson) {
		QueryCriteriaBean data = JSON.toJavaObject(paramJson, QueryCriteriaBean.class);
		Pageable pageable = QueryUtils.buildPageRequest(data);

		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		data.getWhereList().forEach(entity -> {
			if (!DataUtils.isEmpty(entity.getVal())) {
				queryBuilder.filter(QueryBuilders.termQuery(entity.getKey(), entity.getVal()));
			}
		});

		// 版本更新
		// spring-boot 2.4.7 spring-data-elasticsearch 4.1.9
		// return dao.search(queryBuilder, pageable);
		//
		// spring-boot 2.5.2 spring-data-elasticsearch 4.2.2
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
		SearchHits<SampleGene> searchHits = execute(search -> search.search(searchQuery, SampleGene.class));
		SearchPage<SampleGene> page = SearchHitSupport.searchPageFor(searchHits, searchQuery.getPageable());
		return (Page<SampleGene>) SearchHitSupport.unwrapSearchHits(page);
	}

	public <R> R execute(OperationsCallback<R> callback) {
		return callback.doWithOperations(operations);
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
