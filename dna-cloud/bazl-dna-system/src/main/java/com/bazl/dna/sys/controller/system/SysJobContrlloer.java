package com.bazl.dna.sys.controller.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
import com.bazl.dna.common.constants.ErrorCodes;
import com.bazl.dna.common.constants.ErrorInfo;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.entity.SysJob;
import com.bazl.dna.sys.service.ISysJobService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * 用户职位信息
 * @author zhaoyong
 */
@RestController
@RequestMapping("/job")
public class SysJobContrlloer extends BaseController {

	@Autowired
	private ISysJobService sysJobService;

	/**
	 * 用户职位保存
	 * @param paramJson 职位对象
	 * @return ResponseData
	 */
	@PutMapping("/save")
	public ResponseData save(@RequestBody JSONObject paramJson) {
		SysJob sysJob = JSON.toJavaObject(paramJson, SysJob.class);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.ERROR_CODE);
		if (sysJob != null) {
			SysJob entity = sysJobService.saveJob(sysJob);

			result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
			result.put(PublicConstants.MSG, entity);
		}
		return new ResponseData(result);
	}

	/**
	 * 获取用户职位
	 * @param id 编号
	 * @return ResponseData
	 */
	@GetMapping("/get/{id}")
	public ResponseData get(@PathVariable String id) {
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		SysJob sysJob = sysJobService.getById(id);
		return new ResponseData(sysJob);
	}

	/**
	 * 用户职位列表
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData list(@RequestBody JSONObject paramJson) {
		Page<SysJob> page = sysJobService.pageList(paramJson);
		return new ResponseData(page);
	}

	/**
	 * 删除用户职位
	 * @param ids 编号
	 * @return ResponseData
	 */
	@DeleteMapping("/delete")
	public ResponseData delete(@RequestBody String... ids) {
		if (ids == null) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		int code = 0;
		for (String id : ids) {
			code = sysJobService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 修改用户状态
	 *
	 * @param json 参数
	 * @return ResponseData
	 */
	@PostMapping("/editStatus")
	public ResponseData editStatus(@RequestBody JSONObject json) {
		String status = json.getString(SysConstants.STATUS);
		if (Strings.isNullOrEmpty(status)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		String id = json.getString(SysConstants.ID);
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		Integer code = sysJobService.editStatus(id, status);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 导出Excel
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/export")
	public ResponseEntity<byte[]> export(@RequestBody JSONObject paramJson){
	    List<SysJob> list = sysJobService.findList(paramJson);

		String[] titleKeys = new String[]{"编号","职位名称","职位描述","排序","状态","创建时间","更新时间"};
		String[] columnNames = {"id","jobName","jobInfo","jobOrder","status","createTime","updateTime"};

		String fileName = SysJob.class.getSimpleName() + System.currentTimeMillis();
		return exportExcel(fileName, titleKeys, columnNames, list);
	}

}
