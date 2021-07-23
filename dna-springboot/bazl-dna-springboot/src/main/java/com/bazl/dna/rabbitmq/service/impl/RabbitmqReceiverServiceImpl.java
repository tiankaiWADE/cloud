package com.bazl.dna.rabbitmq.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bazl.dna.rabbitmq.service.RabbitmqReceiverService;

/**
 * 消息接收实现类
 * @author zhaoyong
 */
@Service
public class RabbitmqReceiverServiceImpl implements RabbitmqReceiverService {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqReceiverServiceImpl.class);

	@Override
	public  void receiver(String message) {
		Optional<String> optional = Optional.ofNullable(message);
		optional.ifPresent(s -> LOGGER.info("Message: {}", s));
	}

}

