package com.bazl.dna.api.constants;

import com.bazl.dna.common.constants.PublicConstants;

/**
 * 定时任务队列常量
 * @author zhaoyong
 */
public class ScheduleTaskConstants extends PublicConstants {
	
	/**
	 * 定时任务队列标识
	 */
	public static final String SCHEDULETASK_QUEUE_NAME = PublicConstants.NAME + "." + "scheduleTask";
	
	/**
	 * 定时任务队列类型
	 */
	public static final String SCHEDULETASK_QUEUE_TYPE= SCHEDULETASK_QUEUE_NAME + "." + PublicConstants.MQ_FAHOUT;
	
	/**
	 * 定时任务队列标识
	 */
	public static final String SCHEDULETASK_QUEUE_KEY = SCHEDULETASK_QUEUE_NAME + "." + PublicConstants.MQ_KEY;
	
	/**
	 * 更新执行状态队列
	 */
	public static final String TASK_UPDATE_STAUTS_QUEUE_NAME = PublicConstants.NAME + "." + "updateExecuteStatusTask";
	
	/**
	 * 更新执行状态队列类型
	 */
	public static final String TASK_UPDATE_STAUTS_QUEUE_TYPE = TASK_UPDATE_STAUTS_QUEUE_NAME + "." + PublicConstants.MQ_FAHOUT;
	
	/**
	 * 更新执行状态队列标识
	 */
	public static final String TASK_UPDATE_STAUTS_QUEUE_KEY = TASK_UPDATE_STAUTS_QUEUE_NAME + "." + PublicConstants.MQ_KEY;
	
	/**
	 * 保存日志队列
	 */
	public static final String SAVE_TASK_LOG_QUEUE_NAME = PublicConstants.NAME + "." + "saveTaskLogTask";
	
	/**
	 * 保存日志类型
	 */
	public static final String SAVE_TASK_LOG_QUEUE_TYPE = SAVE_TASK_LOG_QUEUE_NAME + "." + PublicConstants.MQ_FAHOUT;
	
	/**
	 * 保存日志标识
	 */
	public static final String SAVE_TASK_LOG_QUEUE_KEY = SAVE_TASK_LOG_QUEUE_NAME + "." + PublicConstants.MQ_KEY;
	
	/**
	 * Constructor
	 */
	private ScheduleTaskConstants() {
		super();
	}
}
