package com.bazl.dna.test.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bazl.dna.util.VerifyCodeUtils;

/**
 * 工具类测试
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestVerifyCodeUtils {

	@Test
	public void test() {
		String result = VerifyCodeUtils.generateVerifyCode(4);
		assertNotNull(result);
	}
	
}
