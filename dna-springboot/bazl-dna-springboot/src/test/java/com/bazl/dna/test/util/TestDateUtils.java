package com.bazl.dna.test.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bazl.dna.util.DateUtil;

/**
 * 工具类测试
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestDateUtils {

	@Test
	public void test() {
		List<String> result = DateUtil.getDialectDate("2021-05-10","2021-05-21");
		assertNotNull(result);
	}
	
}
