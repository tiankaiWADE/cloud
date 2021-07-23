package com.bazl.dna.sys.rabbitmq.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazl.dna.sys.constants.SysMessageConstants;
import com.bazl.dna.sys.rabbitmq.ISendService;

/**
 * 消息发送服务实现类
 * @author zhaoyong
 */
@Service
public class SendServiceImpl implements ISendService {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	/**
	 * 对应消息监听 @Exchange（ value = "" key = "")
	 */
	@Override
	public void send(String message) {
		rabbitTemplate.convertAndSend(SysMessageConstants.QUEUE_TYPE, SysMessageConstants.QUEUE_KEY, message);
	}
	
}
