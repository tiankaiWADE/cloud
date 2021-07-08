package com.bazl.dna.test.util;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bazl.dna.util.SnowFlakeUUID;

/**
 * 工具类测试
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSnowFlake {

	@Test
	public void test() {
		long result = new SnowFlakeUUID(1L, 1L).nextId();
		assertNotNull(result);
	}
	
}
