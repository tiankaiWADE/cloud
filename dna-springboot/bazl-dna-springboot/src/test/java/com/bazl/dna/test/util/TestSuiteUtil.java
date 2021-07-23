package com.bazl.dna.test.util;

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
	"com.bazl.dna.test.util"
})
public class TestSuiteUtil {

}
