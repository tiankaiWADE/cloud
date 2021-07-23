package com.bazl.dna.test.controller.auth;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bazl.dna.test.controller.TestBaseController;

/**
 * Test AuthController
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestAuthController extends TestBaseController {

	private static final String PATH = "/system/auth";

	@Test
	public void authLogin() {
		LOGGER.info("authLogin:[{}] -> {}", PATH + "/login", token);
		assertNotNull(token);
	}

	@Test
	public void captchaImage() {
		String url = PATH + "/captchaImage";
		Object result = get(url);
		LOGGER.info("captchaImage:[{}] -> {}", url, result);
		assertNotNull(result);
	}

	@Test
	@RepeatedTest(2)
	public void findMenuByRoles() {
		String url = PATH + "/menu/1";
		ArrayList<?> result = (ArrayList<?>) get(url);
		LOGGER.info("findMenuByRoles:[{}] -> {}", url, result);
		assertTrue(result.size() != 0);
	}

	@Test
	@RepeatedTest(2)
	public void findMenuOperByRoles() {
		String url = PATH + "/menu/operation/101";
		ArrayList<?> result = (ArrayList<?>) get(url);
		LOGGER.info("findMenuOperByRoles:[{}] -> {}", url, result);
		assertTrue(result.size() != 0);
	}
}
