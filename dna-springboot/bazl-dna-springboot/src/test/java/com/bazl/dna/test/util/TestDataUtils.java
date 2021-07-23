package com.bazl.dna.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bazl.dna.util.DataUtils;

/**
 * 工具类测试
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestDataUtils {

	@Test
	public void test() {
		boolean result = DataUtils.isEmpty(null);
		assertEquals(true, result);
	}
	
}
