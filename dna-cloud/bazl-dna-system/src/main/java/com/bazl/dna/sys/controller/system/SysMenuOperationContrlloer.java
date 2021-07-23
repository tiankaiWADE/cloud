package com.bazl.dna.sys.controller.system;

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
import com.bazl.dna.sys.entity.SysMenuOperation;
import com.bazl.dna.sys.service.ISysMenuOperationService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * 菜单操作权限
 * @author zhaoyong
 *
 */
@RestController
@RequestMapping("/menuOperation")
public class SysMenuOperationContrlloer extends BaseController {

	@Autowired
	private ISysMenuOperationService menuOperationService;

	/**
	 * 保存
	 * @param paramJson 操作对象
	 * @return ResponseData
	 */
	@PutMapping("/save")
    public ResponseData save(@RequestBody JSONObject paramJson){
		SysMenuOperation sysMenuOperation = JSON.toJavaObject(paramJson, SysMenuOperation.class);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.ERROR_CODE);
		if (sysMenuOperation != null) {
			SysMenuOperation entity = menuOperationService.save(sysMenuOperation);

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
    public ResponseData getById(@PathVariable String id) {
		if(Strings.isNullOrEmpty(id)){
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
        SysMenuOperation result = menuOperationService.getById(id);
        return new ResponseData(result);
    }

	/**
	 * 列表
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData findList(@RequestBody JSONObject paramJson){
	    Page<SysMenuOperation> page = menuOperationService.pageList(paramJson);
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
			code = menuOperationService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 判断是否存在
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/checkType")
    public ResponseData checkType(@RequestBody JSONObject paramJson) {
		String id = paramJson.getString(SysConstants.ID);
		String type = paramJson.getString(SysConstants.CODE);
		if(Strings.isNullOrEmpty(type)){
			return new ResponseData(ErrorCodes.ERR_PARAM,ErrorInfo.ERR_PARAM);
		}
        int code = menuOperationService.checkType(id,type);
        Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
    }
}
