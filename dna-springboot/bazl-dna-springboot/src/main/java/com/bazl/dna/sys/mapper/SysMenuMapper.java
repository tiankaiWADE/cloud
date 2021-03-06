package com.bazl.dna.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bazl.dna.sys.entity.SysMenu;

/**
 * 菜单表 数据层
 *
 * @author zhaoyong
 */
public interface SysMenuMapper {
	/**
	 * 查询系统菜单列表
	 *
	 * @param sysMenu 菜单信息
	 * @return 菜单列表
	 */
	List<SysMenu> selectList(SysMenu sysMenu);

	/**
	 * 根据用户查询系统菜单列表
	 *
	 * @param sysMenu 菜单信息
	 * @return 菜单列表
	 */
	List<SysMenu> selectListByUserId(SysMenu sysMenu);


	/**
	 * 根据角色ID查询菜单树信息
	 *
	 * @param roleId 角色ID
	 * @param menuTypeId 菜单类型id
	 * @return 选中菜单列表
	 */
	List<Integer> selectListByRoleId(@Param("roleId") String roleId, @Param("menuTypeId") String menuTypeId);
	
	/**
	 * 根据角色ID查询菜单树信息
	 * 
	 * @param roles 角色ID
	 * @param menuTypeId 菜单类型
	 * @param parentId 父级ID
	 * @return List<Map<String, String>>
	 */
	List<SysMenu> selectMenuByRoles(@Param("roles") List<String> roles,
			@Param("menuTypeId") String menuTypeId, @Param("parentId") String parentId);

}
