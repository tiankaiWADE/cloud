package com.bazl.dna.monitor.util;

import org.quartz.JobExecutionContext;

import com.bazl.dna.monitor.entity.SysTask;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author zhaoyong
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob {
	@Override
	protected void doExecute(JobExecutionContext context, SysTask sysTask) {
		JobInvokeUtil.invokeMethod(sysTask);
	}
}
