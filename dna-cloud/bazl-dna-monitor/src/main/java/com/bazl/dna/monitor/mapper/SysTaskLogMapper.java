package com.bazl.dna.monitor.mapper;

import java.util.List;

import com.bazl.dna.monitor.entity.SysTaskLog;

/**
 * 调度任务日志信息 数据层
 * 
 * @author zhayong
 */
public interface SysTaskLogMapper {
	/**
	 * 获取quartz调度器日志的计划任务
	 * 
	 * @param taskLog 调度日志信息
	 * @return 调度任务日志集合
	 */
	public List<SysTaskLog> selectTaskLogList(SysTaskLog taskLog);

	/**
	 * 查询所有调度任务日志
	 *
	 * @return 调度任务日志列表
	 */
	public List<SysTaskLog> selectTaskLogAll();

	/**
	 * 通过调度任务日志ID查询调度信息
	 * 
	 * @param id 调度任务日志ID
	 * @return 调度任务日志对象信息
	 */
	public SysTaskLog selectTaskLogById(Long id);

	/**
	 * 新增任务日志
	 * 
	 * @param taskLog 调度日志信息
	 * @return 结果
	 */
	public int insertTaskLog(SysTaskLog taskLog);

	/**
	 * 批量删除调度日志信息
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTaskLogByIds(Long[] ids);

	/**
	 * 删除任务日志
	 * 
	 * @param id 调度日志ID
	 * @return 结果
	 */
	public int deleteTaskLogById(Long id);

	/**
	 * 清空任务日志
	 */
	public void cleanTaskLog();
}
