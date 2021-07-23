package com.bazl.dna.api.rabbitmq;

import com.bazl.dna.api.rabbitmq.dto.SysTaskLogDto;
import com.bazl.dna.api.rabbitmq.dto.UpdateTaskExecuteSatusDto;

/**
 * 定时任务队列
 * @author liutao
 *
 */
public interface IScheduleExecuteMQService {

	/**
	 * 更新任务执行状态
	 *
	 * @param updateTaskExecuteSatusDto 对象
	 */
	void updateTaskExecuteStatus(UpdateTaskExecuteSatusDto updateTaskExecuteSatusDto);

	/**
	 * 保存任务执行日志
	 *
	 * @param sysTaskLogDto 对象
	 */
	void saveExecuteTaskLog(SysTaskLogDto sysTaskLogDto);
}
