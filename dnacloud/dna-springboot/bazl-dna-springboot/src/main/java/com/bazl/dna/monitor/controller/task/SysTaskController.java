package com.bazl.dna.monitor.controller.task;

import java.util.Map;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.auth.AuthUser;
import com.bazl.dna.common.auth.annotation.CurrentUser;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.monitor.entity.SysTask;
import com.bazl.dna.monitor.exception.TaskException;
import com.bazl.dna.monitor.service.ISysTaskService;
import com.bazl.dna.monitor.util.CronUtils;
import com.google.common.collect.Maps;

/**
 * 调度任务信息操作处理
 *
 * @author zhaoyong
 */
@RestController
@RequestMapping("/monitor/task")
public class SysTaskController extends BaseController {

	@Autowired
	private ISysTaskService taskService;

	/**
	 * 查询定时任务列表
	 *
	 * @param paramJson 查询参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData list(@RequestBody JSONObject paramJson) {
		Page<SysTask> list = taskService.pageList(paramJson);
		return new ResponseData(list);
	}

	/**
	 * 导出定时任务列表
	 *
	 * @param paramJson 查询参数
	 * @return ResponseData
	 */
	@GetMapping("/export")
	public ResponseData export(@RequestBody JSONObject paramJson) {
		return new ResponseData();
	}

	/**
	 * 获取定时任务详细信息
	 *
	 * @param id 主键
	 * @return ResponseData
	 */
	@GetMapping("/{id}")
	public ResponseData getInfo(@PathVariable String id) {
		return new ResponseData(taskService.selectTaskById(id));
	}

	/**
	 * 新增定时任务
	 *
	 * @param paramJson 参数
	 * @param authUser 登录对象
	 * @return ResponseData
	 * @throws SchedulerException, TaskException 异常
	 */
	@PostMapping
	public ResponseData add(@RequestBody JSONObject paramJson, @CurrentUser AuthUser authUser)
			throws SchedulerException, TaskException {
		SysTask sysTask = JSON.toJavaObject(paramJson, SysTask.class);
		if (sysTask == null || !CronUtils.isValid(sysTask.getCronExpression())) {
			return new ResponseData("cron表达式不正确");
		}
		sysTask.setCreateUser(authUser.getUserName());

		int code = taskService.save(sysTask);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 修改定时任务
	 *
	 * @param paramJson 参数
	 * @param authUser 登录对象
	 * @return ResponseData
	 * @throws SchedulerException, TaskException 异常
	 */
	@PutMapping
	public ResponseData edit(@RequestBody JSONObject paramJson, @CurrentUser AuthUser authUser)
			throws SchedulerException, TaskException {
		SysTask sysTask = JSON.toJavaObject(paramJson, SysTask.class);
		if (sysTask == null || !CronUtils.isValid(sysTask.getCronExpression())) {
			return new ResponseData("cron表达式不正确");
		}
		int code = taskService.updateTask(sysTask);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 定时任务状态修改
	 *
	 * @param paramJson 参数
	 * @return ResponseData
	 * @throws SchedulerException, TaskException 异常
	 */
	@PutMapping("/changeStatus")
	public ResponseData changeStatus(@RequestBody JSONObject paramJson) throws SchedulerException, TaskException {
		SysTask sysTask = JSON.toJavaObject(paramJson, SysTask.class);
		if (sysTask != null) {
			SysTask newJob = taskService.selectTaskById(sysTask.getId());
			newJob.setStatus(sysTask.getStatus());
			return new ResponseData(taskService.changeStatus(newJob));
		}
		return new ResponseData();
	}

	/**
	 * 定时任务立即执行一次
	 *
	 * @param paramJson 参数
	 * @return ResponseData
	 * @throws SchedulerException, TaskException 异常
	 */
	@PutMapping("/run")
	public ResponseData run(@RequestBody JSONObject paramJson) throws SchedulerException, TaskException {
		SysTask sysTask = JSON.toJavaObject(paramJson, SysTask.class);
		if (sysTask != null) {
			taskService.run(sysTask);
		}
		return new ResponseData();
	}

	/**
	 * 删除定时任务
	 *
	 * @param ids 主键
	 * @return ResponseData
	 * @throws SchedulerException, TaskException 异常
	 */
	@DeleteMapping("/{ids}")
	public ResponseData remove(@PathVariable String[] ids) throws SchedulerException, TaskException {
		taskService.deleteTaskByIds(ids);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
		return new ResponseData(result);
	}
}
