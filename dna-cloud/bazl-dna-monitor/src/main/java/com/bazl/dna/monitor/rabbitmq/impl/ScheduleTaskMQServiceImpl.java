package com.bazl.dna.monitor.rabbitmq.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazl.dna.api.constants.ScheduleTaskConstants;
import com.bazl.dna.api.rabbitmq.dto.InvokeParamDto;
import com.bazl.dna.monitor.rabbitmq.IScheduleTaskMQService;

/**
 * IScheduleTaskMQService实现类
 * @author zhaoyong
 */
@Service
public class ScheduleTaskMQServiceImpl implements IScheduleTaskMQService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTaskMQServiceImpl.class);

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Override
	public void pullTask(InvokeParamDto invokeParamDto) {
		LOGGER.info("MQ push: {}", invokeParamDto);
		rabbitTemplate.convertAndSend(ScheduleTaskConstants.SCHEDULETASK_QUEUE_TYPE,
				ScheduleTaskConstants.SCHEDULETASK_QUEUE_KEY, invokeParamDto);
	}

}
