package com.bazl.dna.dubbo.test.service;

import static org.junit.Assert.assertNotNull;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.bazl.dna.dubbo.api.service.IEchoService;
import com.bazl.dna.dubbo.consumer.DnaDubboConsumerApplication;

/**
 * 单元测试 - dubbo
 * 
 * @author zhaoyong
 *
 */
@SpringBootTest(classes = DnaDubboConsumerApplication.class)
public class EchoServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(EchoServiceTest.class);

	@DubboReference
	private IEchoService service;

	@Test
	public void test() {
		String message = "dubbo";
		String result = service.echo(message);
		LOGGER.info("test: {}", result);
		assertNotNull(result);
	}

}
