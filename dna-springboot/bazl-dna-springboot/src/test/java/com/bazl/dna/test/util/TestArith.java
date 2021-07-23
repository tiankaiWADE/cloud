package com.bazl.dna.test.util;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bazl.dna.util.Arith;

/**
 * 工具类测试
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestArith {

	@Test
	public void add() {
		double result = Arith.add(1d, 2d);
		assertEquals(0, 3d, result);
	}
	
	@Test
	public void sub() {
		double result = Arith.sub(1d, -2d);
		assertEquals(0, 3d, result);
	}
}
