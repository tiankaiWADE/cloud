package com.bazl.dna.monitor.rabbitmq.impl;

import javax.net.ssl.SSLException;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bazl.dna.api.rabbitmq.dto.SysTaskLogDto;
import com.bazl.dna.api.rabbitmq.dto.UpdateTaskExecuteSatusDto;
import com.bazl.dna.monitor.entity.SysTask;
import com.bazl.dna.monitor.entity.SysTaskLog;
import com.bazl.dna.monitor.exception.TaskException;
import com.bazl.dna.monitor.rabbitmq.IScheduleExecuteService;
import com.bazl.dna.monitor.service.ISysTaskLogService;
import com.bazl.dna.monitor.service.ISysTaskService;

/**
 * IScheduleExecuteService实现类
 * @author zhaoyong
 */
@Service
public class ScheduleExecuteServiceImpl implements IScheduleExecuteService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleExecuteServiceImpl.class);
	
	@Autowired
	private ISysTaskLogService sysTaskLogService;
	
	@Autowired
	private ISysTaskService sysTaskService;


	@Override
	public void updateTaskExecuteStatus(UpdateTaskExecuteSatusDto updateTask) throws SSLException, SchedulerException, TaskException {
		LOGGER.info("updateTaskExecuteStatus :{}", updateTask);
		SysTask sysTask = sysTaskService.selectTaskById(updateTask.getTaskId());
		if(sysTask != null){
			sysTask.setExecuteStatus(updateTask.getExecuteStatus());
			sysTaskService.save(sysTask);
		}
		
	}

	@Override
	public void saveExecuteTaskLog(SysTaskLogDto taskLog) throws SSLException {
		LOGGER.info("saveExecuteTaskLog :{}", taskLog);
		SysTask sysTask = sysTaskService.selectTaskById(taskLog.getTaskId());
		SysTaskLog sysTaskLog = JSON.toJavaObject(JSON.parseObject(JSON.toJSONString(taskLog)), SysTaskLog.class);
		if(sysTask != null){
			sysTaskLog.setTaskGroup(sysTask.getTaskGroup());
			sysTaskLog.setTaskName(sysTask.getTaskName());
			sysTaskLog.setInvokeTarget(sysTask.getInvokeTarget());
			sysTaskLogService.addTaskLog(sysTaskLog);
		}
	}
	
	
	
	

	
    
}
