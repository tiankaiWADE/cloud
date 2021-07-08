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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.constants.ErrorCodes;
import com.bazl.dna.common.constants.ErrorInfo;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.entity.SysDictItem;
import com.bazl.dna.sys.entity.SysDictItemType;
import com.bazl.dna.sys.service.ISysDictItemService;
import com.bazl.dna.sys.service.ISysDictItemTypeService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * 字典信息
 * @author zhaoyong
 */
@RestController
@RequestMapping("/system/dict")
public class SysDictItemContrlloer extends BaseController {

	@Autowired
	private ISysDictItemTypeService sysDictItemTypeService;

	@Autowired
	private ISysDictItemService sysDictItemService;

	/**
	 * 字典类别保存
	 *
	 * @param paramJson 字典类别对象
	 * @return ResponseData
	 */
	@PutMapping("/saveDictItemType")
	public ResponseData saveDictItemType(@RequestBody JSONObject paramJson) {
		SysDictItemType sysDictItemType = JSON.toJavaObject(paramJson, SysDictItemType.class);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.ERROR_CODE);
		if (sysDictItemType != null) {
			SysDictItemType entity = sysDictItemTypeService.save(sysDictItemType);
			result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
			result.put(PublicConstants.MSG, entity);
		}
		return new ResponseData(result);
	}

	/**
	 * 判断类别id 是否存在
	 *
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/checkTypeId")
	public ResponseData checkTypeId(@RequestBody JSONObject paramJson) {
		String id = paramJson.getString(SysConstants.ID);
		String code = paramJson.getString(PublicConstants.CODE);
		if (Strings.isNullOrEmpty(code)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		int check = sysDictItemTypeService.checkTypeId(id, code);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, check);
		return new ResponseData(result);
	}

	/**
	 * 获取类别
	 *
	 * @param id 编号
	 * @return ResponseData
	 */
	@GetMapping("/getTypeById/{id}")
	public ResponseData getTypeById(@PathVariable String id) {
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		SysDictItemType sysDictItemType = sysDictItemTypeService.getById(id);
		return new ResponseData(sysDictItemType);
	}

	/**
	 * 获取类别中字典列表
	 *
	 * @param typeId 类型id
	 * @return ResponseData
	 */
	@GetMapping("/findTypeInfoById/{typeId}")
	public ResponseData findTypeInfoById(@PathVariable String typeId) {
		if (Strings.isNullOrEmpty(typeId)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		JSONObject paramJson = new JSONObject();
		paramJson.put(SysConstants.TYPE_ID, typeId);
		List<SysDictItem> result = sysDictItemService.findList(paramJson);
		return new ResponseData(result);
	}

	/**
	 * 获取类别字典信息
	 *
	 * @param paramJson 字典参数
	 * @return ResponseData
	 */
	@PostMapping("/findTypeInfoByCodes")
	public ResponseData findTypeInfoByCodes(@RequestBody JSONObject paramJson) {
		JSONArray array = paramJson.getJSONArray("codes");
		if (array.isEmpty()) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		Map<String, Object> result = Maps.newHashMap();
		array.forEach(obj -> {
			JSONObject json = new JSONObject();
			json.put(SysConstants.TYPE_ID, obj);
			List<SysDictItem> itemList = sysDictItemService.findList(json);
			result.put((String)obj, itemList);
		});
		return new ResponseData(result);
	}

	/**
	 * 获取类别列表
	 *
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/findTypeList")
	public ResponseData findTypeList(@RequestBody JSONObject paramJson) {
		Page<SysDictItemType> page = sysDictItemTypeService.pageList(paramJson);
		return new ResponseData(page);
	}

	/**
	 * 修改状态
	 *
	 * @param json 参数
	 * @return ResponseData
	 */
	@PostMapping("/editTypeStatus")
	public ResponseData editTypeStatus(@RequestBody JSONObject json) {
		String status = json.getString(SysConstants.STATUS);
		if (Strings.isNullOrEmpty(status)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		String id = json.getString(SysConstants.ID);
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		Integer code = sysDictItemTypeService.editStatus(id, status);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 修改状态
	 *
	 * @param json 参数
	 * @return ResponseData
	 */
	@PostMapping("/editItemStatus")
	public ResponseData editItemStatus(@RequestBody JSONObject json) {
		String status = json.getString(SysConstants.STATUS);
		if (Strings.isNullOrEmpty(status)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		String id = json.getString(SysConstants.ID);
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		Integer code = sysDictItemService.editStatus(id, status);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 获取字典列表
	 *
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/findItemList")
	public ResponseData findItemList(@RequestBody JSONObject paramJson) {
		Page<SysDictItem> page = sysDictItemService.pageList(paramJson);
		return new ResponseData(page);
	}

	/**
	 * 保存字典信息
	 *
	 * @param paramJson 字典对象
	 * @return ResponseData
	 */
	@PutMapping("/saveDictItem")
	public ResponseData saveDictItem(@RequestBody JSONObject paramJson) {
		SysDictItem sysDictItem = JSON.toJavaObject(paramJson, SysDictItem.class);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.ERROR_CODE);
		if (sysDictItem != null) {
			SysDictItem entity = sysDictItemService.save(sysDictItem);

			result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
			result.put(PublicConstants.MSG, entity);
		}
		return new ResponseData(result);
	}

	/**
	 * 获取字典信息
	 *
	 * @param id 字典编号
	 * @return ResponseData
	 */
	@GetMapping("/getItemById/{id}")
	public ResponseData getItemById(@PathVariable String id) {
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		SysDictItem sysDictItem = sysDictItemService.getById(id);
		return new ResponseData(sysDictItem);
	}

	/**
	 * 删除字典信息
	 *
	 * @param ids 编号
	 * @return ResponseData
	 */
	@DeleteMapping("/deleteItem")
	public ResponseData deleteItem(@RequestBody String... ids) {
		if (ids == null) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		int code = 0;
		for (String id : ids) {
			code = sysDictItemService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 删除字典类别
	 *
	 * @param ids 字典编号
	 * @return ResponseData
	 */
	@DeleteMapping("/deleteItemType")
	public ResponseData deleteItemType(@RequestBody String... ids) {
		if (ids == null) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		int code = 0;
		for (String id : ids) {
			code = sysDictItemTypeService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 获取类别列表
	 *
	 * @param json 参数
	 * @return ResponseData
	 */
	@PostMapping("/findListByCodes")
	public ResponseData findListByCode(@RequestBody JSONObject json) {
		List<SysDictItemType> list = sysDictItemTypeService.findListByCodes(json);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.PARAM_LIST, list);
		return new ResponseData(result);
	}

	/**
	 * 导出字典类型Excel
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/exportItemType")
	public ResponseEntity<byte[]> exportItemType(@RequestBody JSONObject paramJson){
	    List<SysDictItemType> list = sysDictItemTypeService.findList(paramJson);

		String[] titleKeys = new String[]{"编号","类型名称","类型标识","描述","排序","状态"};
		String[] columnNames = {"id","typeName","typeCode","typeDes","typeOrder","status"};

		String fileName = SysDictItemType.class.getSimpleName() + System.currentTimeMillis();
		return exportExcel(fileName, titleKeys, columnNames, list);
	}

	/**
	 * 导出字典Excel
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/exportItem")
	public ResponseEntity<byte[]> exportItem(@RequestBody JSONObject paramJson){
	    List<SysDictItem> list = sysDictItemService.findList(paramJson);

		String[] titleKeys = new String[]{"编号","类型标识","字典名称","字典标识","排序","状态"};
		String[] columnNames = {"id","typeId","itemName","itemCode","itemOrder","status"};

		String fileName = SysDictItem.class.getSimpleName() + System.currentTimeMillis();
		return exportExcel(fileName, titleKeys, columnNames, list);
	}
}
