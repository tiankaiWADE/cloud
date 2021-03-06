package com.bazl.dna.sys.controller.system;

import java.util.ArrayList;
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
import com.bazl.dna.common.auth.AuthUser;
import com.bazl.dna.common.auth.annotation.CurrentUser;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.entity.SysMenu;
import com.bazl.dna.sys.entity.SysMenuOperation;
import com.bazl.dna.sys.entity.TreeSelect;
import com.bazl.dna.sys.service.ISysMenuOperationService;
import com.bazl.dna.sys.service.ISysMenuService;
import com.google.common.collect.Maps;

/**
 * 系统菜单
 * @author zhaoyong
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {

	@Autowired
	public ISysMenuService menuService;

	@Autowired
	public ISysMenuOperationService operationService;

	/**
	 * 保存菜单
	 *
	 * @param paramJson 菜单对象
	 * @return Map<String, Object>
	 */
	@PutMapping("/save")
	public Map<String, Object> save(@RequestBody JSONObject paramJson) {
		SysMenu sysMenu = JSON.toJavaObject(paramJson, SysMenu.class);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.ERROR_CODE);
		if (sysMenu != null) {
			SysMenu entity = menuService.save(sysMenu);

			result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
			result.put(PublicConstants.MSG, entity);
		}
		return result;
	}

	/**
	 * 获取菜单
	 *
	 * @param id 编号
	 * @return Map<String, Object>
	 */
	@GetMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable String id) {
		Map<String, Object> result = Maps.newHashMap();
		result.put("sysMenu", menuService.getById(id));
		List<String> menuOperList = operationService.selectByMenuId(id);
		result.put("menuOperList", menuOperList);
		List<SysMenuOperation> operationList = operationService.findAll();
		result.put("operationList", operationList);
		return result;

	}

	/**
	 * 菜单列表
	 *
	 * @param paramJson 菜单对象
	 * @return List<SysMenu>
	 */
	@PostMapping("/list")
	public List<SysMenu> list(@RequestBody JSONObject paramJson) {
		SysMenu sysMenu = JSON.toJavaObject(paramJson, SysMenu.class);
		if (sysMenu != null) {
			return menuService.selectList(sysMenu);
		}
		return new ArrayList<>();
	}

	/**
	 * 删除
	 * @param ids 编号
	 * @return Map<String, Object>
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(@RequestBody String... ids) {
		int code = 0;
		for (String id : ids) {
			code = menuService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return result;
	}

	/**
	 * 修改菜单状态
	 *
	 * @param paramJson 参数
	 * @return Map<String, Object>
	 */
	@PostMapping("/editStatusById")
	public Map<String, Object> editStatusById(@RequestBody JSONObject paramJson) {
		String status = paramJson.getString(SysConstants.STATUS);
		String id = paramJson.getString(SysConstants.ID);
		int code = menuService.editStatusById(id, status);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return result;
	}

	/**
	 * 获取菜单下拉树列表
	 *
	 * @param authUser 登录对象
	 * @param paramJson 菜单对象
	 * @return List<TreeSelect>
	 */
	@PostMapping("/treeselect")
	public List<TreeSelect> treeselect(@RequestBody JSONObject paramJson,
			@CurrentUser AuthUser authUser) {
		SysMenu menu = JSON.toJavaObject(paramJson, SysMenu.class);
		List<SysMenu> menus;
		if (SysConstants.DEFAULT_ROLE.equals(authUser.getIsAdmin())) {
			menus = menuService.selectList(menu);
		} else {
			menu.setUserId(authUser.getId());
			menus = menuService.selectListByUserId(menu);
		}
		return menuService.buildTreeSelect(menus);
	}

	/**
	 * 加载对应角色菜单列表树
	 * 
	 * @param authUser 登录对象
	 * @param paramJson 菜单对象
	 * @param role String
	 * @return Map<String, Object>
	 */
	@PostMapping("/roleMenuTreeselect/{roleId}")
	public Map<String, Object> roleMenuTreeselect(@RequestBody JSONObject paramJson,
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
		return result;
	}

}
