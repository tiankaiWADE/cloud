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
import com.bazl.dna.sys.entity.SysMenu;
import com.bazl.dna.sys.entity.SysMenuOperation;
import com.bazl.dna.sys.service.ISysMenuOperationService;
import com.bazl.dna.sys.service.ISysMenuService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * 系统菜单
 * @author zhaoyong
 */
@RestController
@RequestMapping("/menu")
public class SysMenuContrlloer extends BaseController {

	@Autowired
	public ISysMenuService menuService;

	@Autowired
	public ISysMenuOperationService operationService;

	/**
	 * 保存菜单
	 *
	 * @param paramJson 菜单对象
	 * @return ResponseData
	 */
	@PutMapping("/save")
	public ResponseData save(@RequestBody JSONObject paramJson) {
		SysMenu sysMenu = JSON.toJavaObject(paramJson, SysMenu.class);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.ERROR_CODE);
		if (sysMenu != null) {
			SysMenu entity = menuService.save(sysMenu);

			result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
			result.put(PublicConstants.MSG, entity);
		}
		return new ResponseData(result);
	}

	/**
	 * 获取菜单
	 *
	 * @param id 编号
	 * @return ResponseData
	 */
	@GetMapping("/get/{id}")
	public ResponseData get(@PathVariable String id) {
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("sysMenu", menuService.getById(id));
		List<String> menuOperList = operationService.selectByMenuId(id);
		result.put("menuOperList", menuOperList);
		List<SysMenuOperation> operationList = operationService.findAll();
		result.put("operationList", operationList);
		return new ResponseData(result);

	}

	/**
	 * 菜单列表
	 *
	 * @param paramJson 菜单对象
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData list(@RequestBody JSONObject paramJson) {
		SysMenu sysMenu = JSON.toJavaObject(paramJson, SysMenu.class);
		if (sysMenu != null) {
			List<SysMenu> list = menuService.selectList(sysMenu);
			return new ResponseData(list);
		}
		return new ResponseData();
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
			code = menuService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 修改菜单状态
	 *
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/editStatusById")
	public ResponseData editStatusById(@RequestBody JSONObject paramJson) {
		String status = paramJson.getString(SysConstants.STATUS);
		if (Strings.isNullOrEmpty(status)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		String id = paramJson.getString(SysConstants.ID);
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		int code = menuService.editStatusById(id, status);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 获取菜单下拉树列表
	 *
	 * @param authUser 登录对象
	 * @param paramJson 菜单对象
	 */
	@PostMapping("/treeselect")
	public ResponseData treeselect(@RequestBody JSONObject paramJson,
			@CurrentUser AuthUser authUser) {
		SysMenu menu = JSON.toJavaObject(paramJson, SysMenu.class);
		List<SysMenu> menus;
		if (SysConstants.DEFAULT_ROLE.equals(authUser.getIsAdmin())) {
			menus = menuService.selectList(menu);
		} else {
			menu.setUserId(authUser.getId());
			menus = menuService.selectListByUserId(menu);
		}
		return new ResponseData(menuService.buildTreeSelect(menus));
	}

	/**
	 * 加载对应角色菜单列表树
	 */
	@PostMapping("/roleMenuTreeselect/{roleId}")
	public ResponseData roleMenuTreeselect(@RequestBody JSONObject paramJson,
			@PathVariable("roleId") String roleId, @CurrentUser AuthUser authUser) {
		SysMenu menu = JSON.toJavaObject(paramJson, SysMenu.class);
		List<SysMenu> menus;
		if (SysConstants.DEFAULT_ROLE.equals(authUser.getIsAdmin())) {
			menus = menuService.selectList(menu);
		} else {
			menu.setUserId(authUser.getId());
			menus = menuService.selectListByUserId(menu);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("checkedKeys", menuService.selectListByRoleId(roleId, menu.getMenuTypeId()));
		result.put("menus", menuService.buildTreeSelect(menus));
		return new ResponseData(result);
	}

}
