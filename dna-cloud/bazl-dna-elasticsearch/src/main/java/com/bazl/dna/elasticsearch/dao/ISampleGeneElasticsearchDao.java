package com.bazl.dna.elasticsearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.bazl.dna.elasticsearch.entity.SampleGene;

/**
 * Elasticsearch Repository
 * @author zhaoyong
 *
 */
@Repository
public interface ISampleGeneElasticsearchDao extends ElasticsearchRepository<SampleGene, String> {


}
