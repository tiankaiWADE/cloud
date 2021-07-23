package com.bazl.dna.monitor.rabbitmq;

import javax.net.ssl.SSLException;

import org.quartz.SchedulerException;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.bazl.dna.api.constants.ScheduleTaskConstants;
import com.bazl.dna.api.rabbitmq.dto.SysTaskLogDto;
import com.bazl.dna.api.rabbitmq.dto.UpdateTaskExecuteSatusDto;
import com.bazl.dna.monitor.exception.TaskException;


/**
 * 定时任务队列
 * @author liutao
 *
 */
@Component
public interface IScheduleSendExecuteService {

	/**
	 * 更新任务执行状态
	 * @param updateTask 任务对象
	 * @throws SSLException SSLException
	 * @throws SchedulerException SchedulerException
	 * @throws TaskException TaskException
	 */
	@RabbitListener(
			bindings = @QueueBinding(
					value = @Queue(value = ScheduleTaskConstants.TASK_UPDATE_STAUTS_QUEUE_NAME, autoDelete = "false"), 
					exchange = @Exchange(value = ScheduleTaskConstants.TASK_UPDATE_STAUTS_QUEUE_TYPE, type = ExchangeTypes.FANOUT), 
					key = ScheduleTaskConstants.TASK_UPDATE_STAUTS_QUEUE_KEY
			)
		)
	void updateTaskExecuteStatus(UpdateTaskExecuteSatusDto updateTask) throws SSLException, SchedulerException, TaskException ;
	
	
	/**
	 * 保存任务执行日志
	 * @param taskLog 任务对象
	 * @throws SSLException SSLException
	 */
	@RabbitListener(
			bindings = @QueueBinding(
					value = @Queue(value = ScheduleTaskConstants.SAVE_TASK_LOG_QUEUE_NAME, autoDelete = "false"), 
					exchange = @Exchange(value = ScheduleTaskConstants.SAVE_TASK_LOG_QUEUE_TYPE, type = ExchangeTypes.FANOUT), 
					key = ScheduleTaskConstants.SAVE_TASK_LOG_QUEUE_KEY
			)
		)
	void saveExecuteTaskLog(SysTaskLogDto taskLog)throws SSLException;
}
