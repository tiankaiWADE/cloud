package com.bazl.dna.monitor.util;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

import com.bazl.dna.monitor.entity.SysTask;

/**
 * 定时任务处理（禁止并发执行）
 * 
 * @author zhaoyong
 *
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
	@Override
	protected void doExecute(JobExecutionContext context, SysTask sysTask) {
		JobInvokeUtil.invokeMethod(sysTask);
	}
}
