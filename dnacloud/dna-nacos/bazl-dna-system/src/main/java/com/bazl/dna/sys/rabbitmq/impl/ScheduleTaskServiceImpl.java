package com.bazl.dna.sys.rabbitmq.impl;

import javax.net.ssl.SSLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.api.rabbitmq.IScheduleExecuteMQService;
import com.bazl.dna.api.rabbitmq.IScheduleTaskService;
import com.bazl.dna.api.rabbitmq.dto.InvokeParamDto;
import com.bazl.dna.api.rabbitmq.dto.SysTaskLogDto;
import com.bazl.dna.api.rabbitmq.dto.UpdateTaskExecuteSatusDto;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.sys.client.IMonitorServiceClient;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.util.DataUtils;

/**
 * IScheduleTaskService实现类
 * @author zhaoyong
 */
@Service
public class ScheduleTaskServiceImpl implements IScheduleTaskService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTaskServiceImpl.class);

	@Autowired
	private IScheduleExecuteMQService scheduleExecuteMqService;

	@Autowired
	private IMonitorServiceClient monitorServiceClient;

	@Override
	public void scheduleTask(InvokeParamDto invokeParamDto) throws SSLException {
		LOGGER.info("MQ pull : {}", invokeParamDto);
		if (SysConstants.SCHEDULE_SERVER_NAME.equals(invokeParamDto.getTaskServerName())) {
			taskExecute(invokeParamDto.getConcurrent(), invokeParamDto.getTaskId(), invokeParamDto.getTaskParam());
		}
	}

	private void taskExecute(String concurrent, String taskId, JSONObject param) {
		LOGGER.info("taskExecute concurrent:{}, taskId:{}, param:{}", concurrent, taskId, param);
		if (PublicConstants.SCHEDULE_CONCURRENT.equals(concurrent)) {
			execute(taskId);
		} else {
			ResponseData data = monitorServiceClient.getInfo(taskId);
			if (data.getCode() == PublicConstants.SUCCESS_CODE) {
				JSONObject json = JSON.parseObject(data.getResult().toString());
				if (!PublicConstants.STATUS_NO.equals(json.getString("executeStatus"))) {
					execute(taskId);
				}
			}
		}
	}

	private void execute(String taskId) {
		long time = System.currentTimeMillis();
		UpdateTaskExecuteSatusDto updateTask = new UpdateTaskExecuteSatusDto();
		updateTask.setTaskId(taskId);
		updateTask.setExecuteStatus(PublicConstants.STATUS_NO);
		scheduleExecuteMqService.updateTaskExecuteStatus(updateTask);
		LOGGER.info("taskExecute  update sysTask  executeStatus = 1");
		updateTask.setExecuteStatus(PublicConstants.STATUS_YES);
		scheduleExecuteMqService.updateTaskExecuteStatus(updateTask);
		LOGGER.info("taskExecute  saveTaskLog ");
		SysTaskLogDto log = new SysTaskLogDto();
		log.setTaskId(taskId);
		log.setExecuteIp(DataUtils.getLocalhost());
		log.setExecuteServerName(SysConstants.SCHEDULE_SERVER_NAME);
		log.setStatus(PublicConstants.STATUS_YES);
		log.setTaskMessage("执行时间：" + (System.currentTimeMillis() - time) + "毫秒");
		scheduleExecuteMqService.saveExecuteTaskLog(log);
	}

}
