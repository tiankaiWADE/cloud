package com.bazl.dna.api.rabbitmq.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazl.dna.api.constants.ScheduleTaskConstants;
import com.bazl.dna.api.rabbitmq.IScheduleExecuteMQService;
import com.bazl.dna.api.rabbitmq.dto.SysTaskLogDto;
import com.bazl.dna.api.rabbitmq.dto.UpdateTaskExecuteSatusDto;

/**
 * ScheduleExecute实现类
 * @author zhaoyong
 */
@Service
public class ScheduleExecuteMQServiceImpl implements IScheduleExecuteMQService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleExecuteMQServiceImpl.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Override
    public void updateTaskExecuteStatus(UpdateTaskExecuteSatusDto updateTaskExecuteSatusDto) {
        LOGGER.info("MQ updateTaskExecuteStatus push: {}", updateTaskExecuteSatusDto);
        rabbitTemplate.convertAndSend(ScheduleTaskConstants.TASK_UPDATE_STAUTS_QUEUE_TYPE, ScheduleTaskConstants.TASK_UPDATE_STAUTS_QUEUE_KEY, updateTaskExecuteSatusDto);
    }


    @Override
    public void saveExecuteTaskLog(SysTaskLogDto sysTaskLogDto) {
        LOGGER.info("MQ updateTaskExecuteStatus push: {}", sysTaskLogDto);
        rabbitTemplate.convertAndSend(ScheduleTaskConstants.SAVE_TASK_LOG_QUEUE_TYPE, ScheduleTaskConstants.SAVE_TASK_LOG_QUEUE_KEY, sysTaskLogDto);
    }


}
