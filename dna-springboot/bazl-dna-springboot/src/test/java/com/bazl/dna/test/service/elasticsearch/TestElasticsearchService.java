package com.bazl.dna.test.service.elasticsearch;

import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.elasticsearch.entity.SampleGene;
import com.bazl.dna.elasticsearch.service.ISampleGeneElasticsearchService;
import com.bazl.dna.test.service.files.TestFastdfsFileService;
import com.google.common.collect.Iterables;

/**
 * Test Elasticsearch service
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestElasticsearchService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestFastdfsFileService.class);

	@Autowired
	private ISampleGeneElasticsearchService service;
	
	@Test
	@RepeatedTest(value = 2)
	public void findList() {
		JSONObject paramJson = new JSONObject();
		Iterable<SampleGene> list = service.findList(paramJson);
		LOGGER.info("findList:{}", Iterables.size(list));
		assertNotEquals(0, Iterables.size(list));
	}

}
