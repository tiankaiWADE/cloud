package com.bazl.dna.test.controller.sys;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bazl.dna.test.controller.TestBaseController;

/**
 * Test SysMessageController
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest
public class TestSysMessageController extends TestBaseController {

	private static final String PATH = "/system/message";

	@Test
	public void refresh() {
		String url = PATH + "/refresh?messageType=test";
		Object result = get(url);
		LOGGER.info("refresh:[{}] -> {}", url, result);
		assertNotNull(result);
	}

}
