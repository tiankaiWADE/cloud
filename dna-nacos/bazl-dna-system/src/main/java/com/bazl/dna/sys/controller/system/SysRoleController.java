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
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.entity.SysRole;
import com.bazl.dna.sys.service.ISysRoleService;
import com.google.common.collect.Maps;

/**
 * 角色管理
 * @author zhaoyong
 */
@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController {

	@Autowired
	private ISysRoleService roleService;

	/**
	 * 获取信息
	 * @param id 编号
	 * @return SysRole
	 */
	@GetMapping("/get/{id}")
    public SysRole get(@PathVariable String id) {
        return roleService.getById(id);
    }

	/**
	 * 列表
	 * @param paramJson 参数
	 * @return Page<SysRole>
	 */
	@PostMapping("/list")
	public Page<SysRole> list(@RequestBody JSONObject paramJson){
	    return roleService.pageList(paramJson);
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
			code = roleService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return result;
	}

	/**
	 * 修改状态
	 *
	 * @param paramJson 参数
	 * @return Map<String, Object>
	 */
	@PostMapping("/editStatus")
	public Map<String, Object> editStatus(@RequestBody JSONObject paramJson) {
		String status = paramJson.getString(SysConstants.STATUS);
		String id = paramJson.getString(SysConstants.ID);
		Integer code = roleService.editStatus(id, status);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return result;
	}

	/**
	 * 判断是否存在
	 * @param paramJson 参数
	 * @return Map<String, Object>
	 */
	@PostMapping("/checkRoleName")
    public Map<String, Object> checkRoleName(@RequestBody JSONObject paramJson) {
		String id = paramJson.getString(SysConstants.ID);
		String roleName = paramJson.getString(SysConstants.ROLE_NAME);
        long code = roleService.checkRoleName(id,roleName);

        Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return result;
    }

	/**
	 * 角色权限保存
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PutMapping("/save")
	public Map<String, Object> save(@RequestBody JSONObject paramJson) {
		// 保存角色
		SysRole sysRole = JSON.toJavaObject(paramJson, SysRole.class);
		SysRole entity = roleService.save(sysRole);

		// 保存权限
		JSONArray menuIds = paramJson.getJSONArray("menuIds");
		int code = roleService.saveAuthority(paramJson.getString(SysConstants.MENU_TYPE), menuIds, entity.getId());

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		result.put(PublicConstants.MSG, entity);
		return result;
	}

	/**
	 * 导出Excel
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/export")
	public ResponseEntity<byte[]> export(@RequestBody JSONObject paramJson){
	    List<SysRole> list = roleService.findList(paramJson);

		String[] titleKeys = new String[]{"编号","角色标识","角色名称","角色描述","状态","创建时间","更新时间"};
		String[] columnNames = {"id","roleCode","roleName","roleDes","status","createTime","updateTime"};

		String fileName = SysRole.class.getSimpleName() + System.currentTimeMillis();
		return exportExcel(fileName, titleKeys, columnNames, list);
	}

}
