package com.bazl.dna.sys.controller.system;

import java.util.List;
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
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.entity.SysMenuType;
import com.bazl.dna.sys.service.ISysMenuTypeService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * 菜单类型管理
 * 
 * @author zhaoyong
 *
 */
@RestController
@RequestMapping("/menuType")
public class SysMenuTypeContrlloer extends BaseController {

	@Autowired
	private ISysMenuTypeService menuTypeService;

	/**
	 * 保存
	 * 
	 * @param paramJson 菜单类型对象
	 * @return ResponseData
	 */
	@PutMapping("/save")
	public ResponseData save(@RequestBody JSONObject paramJson) {
		SysMenuType sysMenuType = JSON.toJavaObject(paramJson, SysMenuType.class);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.ERROR_CODE);
		if (sysMenuType != null) {
			SysMenuType entity = menuTypeService.save(sysMenuType);

			result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
			result.put(PublicConstants.MSG, entity);
		}
		return new ResponseData(result);
	}

	/**
	 * 获取信息
	 * 
	 * @param id 编号
	 * @return ResponseData
	 */
	@GetMapping("/get/{id}")
	public ResponseData get(@PathVariable String id) {
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		SysMenuType result = menuTypeService.getById(id);
		return new ResponseData(result);
	}

	/**
	 * 列表
	 * 
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData list(@RequestBody JSONObject paramJson) {
		Page<SysMenuType> page = menuTypeService.pageList(paramJson);
		return new ResponseData(page);
	}

	/**
	 * 列表
	 * 
	 * @return ResponseData
	 */
	@GetMapping("/findAll")
	public ResponseData findAll() {
		JSONObject paramJson = new JSONObject();
		paramJson.put(SysConstants.STATUS, PublicConstants.STATUS_YES);
		List<SysMenuType> page = menuTypeService.findList(paramJson);
		return new ResponseData(page);
	}

	/**
	 * 修改状态
	 * 
	 * @param data 参数
	 * @return ResponseData
	 */
	@PostMapping("/editStatusById")
	public ResponseData editStatusById(@RequestBody JSONObject data) {
		String status = data.getString(SysConstants.STATUS);
		if (Strings.isNullOrEmpty(status)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		String id = data.getString(SysConstants.ID);
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		int code = menuTypeService.editStatusById(id, status);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 判断是否存在
	 * 
	 * @param data 参数
	 * @return ResponseData
	 */
	@PostMapping("/checkCode")
	public ResponseData checkCode(@RequestBody JSONObject data) {
		String id = data.getString(SysConstants.ID);
		String code = data.getString(PublicConstants.CODE);
		if (Strings.isNullOrEmpty(code)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		int check = menuTypeService.checkCode(id, code);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, check);
		return new ResponseData(result);
	}

	/**
	 * 删除
	 * 
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
			code = menuTypeService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}
}
