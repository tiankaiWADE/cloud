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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.constants.ErrorCodes;
import com.bazl.dna.common.constants.ErrorInfo;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.entity.SysLog;
import com.bazl.dna.sys.service.ISysLogService;
import com.bazl.dna.util.DateUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * 系统日志控制器
 * @author zhaoyong
 */
@RestController
@RequestMapping("/log")
public class SysLogController extends BaseController {

	@Autowired
	private ISysLogService logService;

	/**
	 * 保存
	 * @param paramJson 菜单类型对象
	 * @return ResponseData
	 */
	@PutMapping("/save")
    public ResponseData save(@RequestBody JSONObject paramJson){
		SysLog sysLog = JSON.toJavaObject(paramJson, SysLog.class);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.ERROR_CODE);
		if (sysLog != null) {
			SysLog entity = logService.save(sysLog);

			result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
			result.put(PublicConstants.MSG, entity);
		}
		return new ResponseData(result);
    }

	/**
	 * 获取信息
	 * @param id 编号
	 * @return ResponseData
	 */
	@GetMapping("/get/{id}")
    public ResponseData get(@PathVariable String id) {
		if(Strings.isNullOrEmpty(id)){
			return new ResponseData(ErrorCodes.ERR_PARAM,ErrorInfo.ERR_PARAM);
		}
        SysLog result = logService.getById(id);
        return new ResponseData(result);
    }

	/**
	 * 列表
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData list(@RequestBody JSONObject paramJson){
	    Page<SysLog> page = logService.pageList(paramJson);
	    return new ResponseData(page);
	}

	/**
	 * 删除
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
			code = logService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 按日期删除日志
	 * @param date 日期
	 * @return ResponseData
	 */
	@DeleteMapping("/deleteLog")
	public ResponseData deleteLog(@RequestParam("date") String date) {
		int code = logService.deleteLog(DateUtil.stringToDate(date, DateUtil.FULL_DATE_FORMAT));
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 导出日志
	 * @param paramJson 参数
	 * @return ResponseEntity<byte[]>
	 */
	@PostMapping("/export")
	public ResponseEntity<byte[]> exportItem(@RequestBody JSONObject paramJson){
	    List<SysLog> list = logService.findList(paramJson);

		String[] titleKeys = new String[]{"编号","请求IP","请求方法","服务名称","请求地址","请求内容","创建时间"};
		String[] columnNames = {"id","operationIp","methodName","operationName","operationPath","operationContext","createTime"};

		String fileName = SysLog.class.getSimpleName() + System.currentTimeMillis();
		return exportExcel(fileName, titleKeys, columnNames, list);
	}

}
