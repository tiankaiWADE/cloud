package com.bazl.dna.api.rabbitmq;

import javax.net.ssl.SSLException;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.bazl.dna.api.constants.ScheduleTaskConstants;
import com.bazl.dna.api.rabbitmq.dto.InvokeParamDto;


/**
 * 定时任务监听
 * @author liutao
 *
 */
@Component
public interface IScheduleTaskService {

	/**
	 * 定时任务监听
	 * @param invokeParamDto 参数对象
	 * @throws SSLException SSLException
	 */
	@RabbitListener(
			bindings = @QueueBinding(
					value = @Queue(value = ScheduleTaskConstants.SCHEDULETASK_QUEUE_NAME, autoDelete = "false"),
					exchange = @Exchange(value = ScheduleTaskConstants.SCHEDULETASK_QUEUE_TYPE, type = ExchangeTypes.FANOUT),
					key = ScheduleTaskConstants.SCHEDULETASK_QUEUE_KEY
			)
		)
	void scheduleTask(InvokeParamDto invokeParamDto) throws SSLException;
}
