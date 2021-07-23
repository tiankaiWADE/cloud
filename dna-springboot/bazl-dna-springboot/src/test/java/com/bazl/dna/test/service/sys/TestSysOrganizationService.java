package com.bazl.dna.test.service.sys;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSON;
import com.bazl.dna.sys.entity.SysOrganization;
import com.bazl.dna.sys.service.ISysOrganizationService;

/**
 * Test SysOrganizationService
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysOrganizationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSysOrganizationService.class);

	@Autowired
	private ISysOrganizationService service;

	@Test
	public void getById() {
		String id = "110101";
		SysOrganization entity = service.getById(id);
		LOGGER.info("getById:{}", JSON.toJSONString(entity));
		assertNotNull(entity);
	}
	
	@Test
	@RepeatedTest(value = 2)
	public void findListByParentId() {
		List<SysOrganization> list = service.findListByParentId("-1");
		LOGGER.info("findListByParentId:{}", list.size());
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void getByCode() {
		String code = "110101000000";
		SysOrganization entity = service.getByCode(code);
		LOGGER.info("getByCode:{}", JSON.toJSONString(entity));
		assertNotNull(entity);
	}
	
	@Test
	public void selectList() {
		SysOrganization sysOrg = new SysOrganization();
		// cache error
		List<SysOrganization> list = service.selectList(sysOrg);
		LOGGER.info("selectList:{}", list.size());
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void selectListByUserId() {
		String userId = "1";
		List<String> list = service.selectListByUserId(userId);
		LOGGER.info("selectListByUserId:{}", list.size());
		assertNotEquals(0, list.size());
	}

}
