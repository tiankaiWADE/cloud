package com.bazl.dna.test.service;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * Junit 单元测试服务
 * 
 * @author zhaoyong
 *
 */
@RunWith(JUnitPlatform.class)
@SelectPackages({ 
	"com.bazl.dna.test.service.datasource",
	"com.bazl.dna.test.service.elasticsearch",
	"com.bazl.dna.test.service.file", 
	"com.bazl.dna.test.service.gateway",
	"com.bazl.dna.test.service.monitor", 
	"com.bazl.dna.test.service.sys" 
})
public class TestSuiteService {

}
