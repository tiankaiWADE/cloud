package com.bazl.dna.test.controller;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * Junit 单元测试 Controller
 * 
 * @author zhaoyong
 *
 */
@RunWith(JUnitPlatform.class)
@SelectPackages({
	"com.bazl.dna.test.controller.auth",
	"com.bazl.dna.test.controller.datasource",
	"com.bazl.dna.test.controller.elasticsearch",
	"com.bazl.dna.test.controller.files",
	"com.bazl.dna.test.controller.gateway",
	"com.bazl.dna.test.controller.monitor",
	"com.bazl.dna.test.controller.sys"
})
public class TestSuiteController {

}
