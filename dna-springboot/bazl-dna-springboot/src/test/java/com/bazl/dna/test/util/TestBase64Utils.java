package com.bazl.dna.test.util;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bazl.dna.util.Base64Utils;

/**
 * 工具类测试
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestBase64Utils {

	@Test
	public void test() {
		String str = "Abc";
		String encode = Base64Utils.encodeBase64String(str.getBytes());
		String decode = Base64Utils.decodeBase64String(encode);
		assertEquals("Abc", decode);
	}
	
}
