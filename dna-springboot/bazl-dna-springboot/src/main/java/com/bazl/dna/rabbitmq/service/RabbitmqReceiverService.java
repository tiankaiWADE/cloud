package com.bazl.dna.rabbitmq.service;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.bazl.dna.rabbitmq.constants.RabbitmqConstants;

/**
 * rabbitmq service
 * 
 * @author zhaoyong
 */
public interface RabbitmqReceiverService {

	/**
	 * 消息接口
	 * 
	 * @param message 消息
	 */
	@RabbitListener(
		bindings = @QueueBinding(
				value = @Queue(value = RabbitmqConstants.QUEUE_NAME, autoDelete = "false"),
				exchange = @Exchange(value = RabbitmqConstants.QUEUE_TYPE, type = ExchangeTypes.DIRECT),
				key = RabbitmqConstants.QUEUE_KEY
		)
	)
	void receiver(String message);

}
