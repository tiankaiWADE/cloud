package com.bazl.dna.monitor.controller.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.monitor.entity.SysTaskLog;
import com.bazl.dna.monitor.service.ISysTaskLogService;
import com.google.common.collect.Maps;

/**
 * 调度日志操作处理
 * 
 * @author zhaoyong
 */
@RestController
@RequestMapping("/monitor/taskLog")
public class SysTaskLogController extends BaseController {
	
	@Autowired
	private ISysTaskLogService taskLogService;

	/**
	 * 查询定时任务调度日志列表
	 * 
	 * @param paramJson 查询参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData list(@RequestBody JSONObject paramJson) {
		Page<SysTaskLog> list = taskLogService.pageList(paramJson);
		return new ResponseData(list);
	}

	/**
	 * 导出定时任务调度日志列表
	 * 
	 * @param paramJson 查询参数
	 * @return ResponseData
	 */
	@GetMapping("/export")
	public ResponseData export(@RequestBody JSONObject paramJson) {
		return new ResponseData();
	}

	/**
	 * 根据调度编号获取详细信息
	 * 
	 * @param id 主键
	 * @return ResponseData
	 */
	@GetMapping("/{id}")
	public ResponseData getInfo(@PathVariable String id) {
		return new ResponseData(taskLogService.selectTaskLogById(id));
	}

	/**
	 * 删除定时任务调度日志
	 * 
	 * @param ids 主键
	 * @return ResponseData
	 */
	@DeleteMapping("/{ids}")
	public ResponseData remove(@PathVariable String[] ids) {
		int code = taskLogService.deleteTaskLogByIds(ids);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 清空定时任务调度日志
	 * 
	 * @return ResponseData
	 */
	@DeleteMapping("/clean")
	public ResponseData clean() {
		taskLogService.cleanTaskLog();
		return new ResponseData();
	}
}
