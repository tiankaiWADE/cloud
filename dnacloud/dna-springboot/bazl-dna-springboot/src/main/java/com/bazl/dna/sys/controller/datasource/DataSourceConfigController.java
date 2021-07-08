package com.bazl.dna.sys.controller.datasource;

import java.util.Map;

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
import com.bazl.dna.common.constants.ErrorCodes;
import com.bazl.dna.common.constants.ErrorInfo;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.entity.DataSourceConfig;
import com.bazl.dna.sys.service.IDataSourceConfigService;
import com.google.common.collect.Maps;

/**
 * 数据源
 * @author zhaoyong
 *
 */
@RestController
@RequestMapping("/system/datasource")
public class DataSourceConfigController extends BaseController {

	@Autowired
	private IDataSourceConfigService service;

	/**
	 * 列表
	 * @param json 参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData findList(@RequestBody JSONObject json) {
		Page<DataSourceConfig> page = service.pageList(json);
		return new ResponseData(page);
	}

	/**
	 * 按id获取信息
	 * @param id 编号
	 * @return ResponseData
	 */
	@GetMapping("/get/{id}")
	public ResponseData get(@PathVariable String id) {
		if (id == null) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		DataSourceConfig result = service.getById(id);
		return new ResponseData(result);
	}

	/**
	 * 按 connectName 获取信息
	 * @param connectName 连接名称
	 * @return ResponseData
	 */
	@GetMapping("/getConnectName")
	public ResponseData getConnectName(String connectName) {
		if (connectName == null) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		DataSourceConfig result = service.getConnectName(connectName);
		return new ResponseData(result);
	}

	/**
	 * 保存
	 * @param paramJson 数据源对象
	 * @return ResponseData
	 */
	@PutMapping("/save")
	public ResponseData save(@RequestBody JSONObject paramJson) {
		DataSourceConfig dataSourceConfig = JSON.toJavaObject(paramJson, DataSourceConfig.class);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.ERROR_CODE);
		if (dataSourceConfig != null) {
			DataSourceConfig entity = service.save(dataSourceConfig);
			
			result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
			result.put(PublicConstants.MSG, entity);
		}
		return new ResponseData(result);
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
			code = service.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}
}
