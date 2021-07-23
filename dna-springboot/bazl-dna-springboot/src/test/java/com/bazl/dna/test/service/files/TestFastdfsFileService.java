package com.bazl.dna.test.service.files;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.files.entity.FastdfsFile;
import com.bazl.dna.files.service.IFastdfsFileService;

/**
 * Test FastdfsFileService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestFastdfsFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestFastdfsFileService.class);

	@Autowired
	private IFastdfsFileService service;

	@Test
	@RepeatedTest(value = 2)
	public void findList() {
		JSONObject paramJson = new JSONObject();
		List<FastdfsFile> list = service.findList(paramJson);
		LOGGER.info("findList:{}", list.size());
		assertNotEquals(0, list.size());
	}

	@Test
	public void pageList() {
		JSONObject paramJson = new JSONObject();
		Page<FastdfsFile> list = service.pageList(paramJson);
		LOGGER.info("pageList:{}", list.getSize());
		assertNotEquals(0, list.getSize());
	}

	@Test
	public void getById() {
		JSONObject paramJson = new JSONObject();
		List<FastdfsFile> list = service.findList(paramJson);
		if (!list.isEmpty()) {
			FastdfsFile entity = service.getById(list.get(0).getId());
			LOGGER.info("getById:{}", JSON.toJSONString(entity));
			assertNotNull(entity);
		}
	}

}
