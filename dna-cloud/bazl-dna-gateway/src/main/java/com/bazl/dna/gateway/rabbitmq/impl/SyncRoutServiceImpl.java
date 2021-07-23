package com.bazl.dna.gateway.rabbitmq.impl;

import javax.net.ssl.SSLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazl.dna.gateway.rabbitmq.ISyncRouteService;
import com.bazl.dna.gateway.service.DynamicRouteService;

/**
 * ISyncRouteService实现类
 * @author zhaoyong
 */
@Service
public class SyncRoutServiceImpl implements ISyncRouteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncRoutServiceImpl.class);

	@Autowired
	private DynamicRouteService dynamicRouteService;

	@Override
	public void syncRoute(String gateweyIp) throws SSLException {
		LOGGER.info("MQ pull: {}", gateweyIp);
		dynamicRouteService.refreshRoute();
	}

}
