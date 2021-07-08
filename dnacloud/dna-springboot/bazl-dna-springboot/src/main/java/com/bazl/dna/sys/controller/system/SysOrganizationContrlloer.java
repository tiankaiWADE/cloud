package com.bazl.dna.sys.controller.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bazl.dna.common.auth.AuthUser;
import com.bazl.dna.common.auth.annotation.CurrentUser;
import com.bazl.dna.common.constants.ErrorCodes;
import com.bazl.dna.common.constants.ErrorInfo;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.entity.SysOrganization;
import com.bazl.dna.sys.service.ISysOrganizationService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * 机构管理
 * @author zhaoyong
 */
@RestController
@RequestMapping("/system/org")
public class SysOrganizationContrlloer extends BaseController {

	@Autowired
	private ISysOrganizationService sysOrgService;

	/**
	 * 机构信息保存
	 *
	 * @param paramJson 机构对象
	 * @param authUser  登录对象
	 * @return ResponseData
	 */
	@PutMapping("/save")
	public ResponseData save(@RequestBody JSONObject paramJson,
			@CurrentUser AuthUser authUser) {
		SysOrganization sysOrganization = JSON.toJavaObject(paramJson, SysOrganization.class);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.ERROR_CODE);
		if (sysOrganization != null) {
			sysOrganization.setCreateUser(authUser.getId());
			SysOrganization entity = sysOrgService.save(sysOrganization);

			result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
			result.put(PublicConstants.MSG, entity);
		}
		return new ResponseData(result);
	}

	/**
	 * id查询
	 *
	 * @param id 编号
	 * @return ResponseData
	 */
	@GetMapping("/get/{id}")
	public ResponseData get(@PathVariable String id) {
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		SysOrganization entity = sysOrgService.getById(id);
		return new ResponseData(entity);
	}

	/**
	 * 直接测试数据源是否连接
	 *
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/isConnection")
	public ResponseData isConnection(@RequestBody JSONObject paramJson) {
		Map<String, Object> result = sysOrgService.isConnection(paramJson);
		return new ResponseData(result);
	}

	/**
	 * 判断保存后数据源是否连接
	 *
	 * @param id 变
	 * @return ResponseData
	 */
	@GetMapping("/checkConnection/{id}")
	public ResponseData checkConnection(@PathVariable String id) {
		Map<String, Object> result = sysOrgService.checkConnection(id);
		return new ResponseData(result);
	}

	/**
	 * 查询列表
	 *
	 * @param paramJson 机构对象
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData findList(@RequestBody JSONObject paramJson) {
		SysOrganization sysOrganization = JSON.toJavaObject(paramJson, SysOrganization.class);
		if (sysOrganization != null) {
			List<SysOrganization> list = sysOrgService.selectList(sysOrganization);
			return new ResponseData(list);
		}
		return new ResponseData();
	}

	/**
	 * 按父级菜单查询
	 *
	 * @param parentId 父级编号
	 * @return ResponseData
	 */
	@GetMapping("/findListByParentId")
	public ResponseData findListByParentId(@RequestParam("parentId") String parentId) {
		List<SysOrganization> list = sysOrgService.findListByParentId(parentId);
		return new ResponseData(list);
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
			code = sysOrgService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 判断机构编号是否存在
	 *
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/checkCode")
	public ResponseData checkCode(@RequestBody JSONObject paramJson) {
		String type = paramJson.getString("type");
		if (Strings.isNullOrEmpty(type)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		String code = paramJson.getString("code");
		if (Strings.isNullOrEmpty(code)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		String orgId = paramJson.getString("orgId");
		long result = sysOrgService.checkCode(orgId, code, type);

		if (result > 0) {
			return new ResponseData(ErrorCodes.ERR_IS_EXISTS, ErrorInfo.ERR_IS_EXISTS);
		}
		return new ResponseData();
	}

	/**
	 * 获取菜单下拉树列表
	 *
	 * @param paramJson 机构对象
	 * @param authUser  登录对象
	 * @return ResponseData
	 */
	@PostMapping("/treeselect")
	public ResponseData treeselect(@RequestBody JSONObject paramJson, @CurrentUser AuthUser authUser) {
		List<SysOrganization> list = sysOrgService.selectList(JSON.toJavaObject(paramJson, SysOrganization.class));
		return new ResponseData(sysOrgService.buildTreeSelect(list));
	}

	/**
	 * 加载对应角色菜单列表树
	 *
	 * @param paramJson 机构对象
	 * @param userId    用户id
	 * @param authUser  登录对象
	 * @return ResponseData
	 */
	@PostMapping("/userTreeselect/{userId}")
	public ResponseData userTreeselect(@RequestBody JSONObject paramJson, @PathVariable("userId") String userId,
			@CurrentUser AuthUser authUser) {
		SysOrganization sysOrganization = JSON.toJavaObject(paramJson, SysOrganization.class);

		List<SysOrganization> list = sysOrgService.selectList(sysOrganization);
		Map<String, Object> result = Maps.newHashMap();
		result.put("checkedKeys", sysOrgService.selectListByUserId(userId));
		result.put("orgList", sysOrgService.buildTreeSelect(list));
		return new ResponseData(result);
	}

	/**
	 * 修改状态
	 *
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/changeStatus")
	public ResponseData changeStatus(@RequestBody JSONObject paramJson) {
		String status = paramJson.getString(SysConstants.STATUS);
		if (Strings.isNullOrEmpty(status)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		String id = paramJson.getString(SysConstants.ID);
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		Integer code = sysOrgService.changeStatus(id, status);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

}
