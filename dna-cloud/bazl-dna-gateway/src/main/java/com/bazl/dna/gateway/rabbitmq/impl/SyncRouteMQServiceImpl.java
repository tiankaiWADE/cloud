package com.bazl.dna.gateway.rabbitmq.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazl.dna.gateway.constants.GatewayConstants;
import com.bazl.dna.gateway.rabbitmq.ISyncRouteMQService;

/**
 * ISyncRouteMQService实现类
 * @author zhaoyong
 */
@Service
public class SyncRouteMQServiceImpl implements ISyncRouteMQService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncRouteMQServiceImpl.class);

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Override
	public void syncRoute() {
		LOGGER.info("MQ push: {}", getLocalhost());
		rabbitTemplate.convertAndSend(GatewayConstants.QUEUE_TYPE, GatewayConstants.QUEUE_KEY, getLocalhost());
	}

	/**
	 * 获取本机ip
	 * 
	 * @return String
	 */
	public static String getLocalhost() {
		try {
			InetAddress inet = InetAddress.getLocalHost();
			return inet.getHostAddress();
		} catch (UnknownHostException e) {
			LOGGER.error("Error getLocalhost:", e);
		}
		return null;
	}

}
