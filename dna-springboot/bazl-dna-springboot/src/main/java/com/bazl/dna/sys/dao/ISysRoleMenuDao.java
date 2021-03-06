package com.bazl.dna.sys.dao;

import java.util.List;

import com.bazl.dna.common.exception.DnaRuntimeException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.bazl.dna.sys.entity.SysRoleMenu;

/**
 * 菜单角色权限接口
 * @author zhaoyong
 */
public interface ISysRoleMenuDao extends JpaRepository<SysRoleMenu, String>,JpaSpecificationExecutor<SysRoleMenu>{

	/**
	 * 查询菜单角色权限列表
	 * @param roleId 角色编号
	 * @param menuTypeId 菜单类型编号
	 * @return List
	 */
	@Query(value = "select menu_id from nt_sys_role_menu where role_id=?1 and menu_type_id=?2",nativeQuery = true)
    List<String> findMenuListByRoleId(String roleId, String menuTypeId);

	/**
	 * 删除菜单角色权限
	 * @param roleId 角色编号
	 */
	@Modifying(clearAutomatically = true)
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@Query(value = "delete from nt_sys_role_menu where role_id=?1",nativeQuery = true)
    void deleteRoleMenuByRoleId(String roleId);

	/**
	 * 删除菜单角色权限
	 * @param menuId 菜单编号
	 */
	@Modifying(clearAutomatically = true)
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@Query(value = "delete from nt_sys_role_menu where menu_id=?1",nativeQuery = true)
	void deleteByMenuId(String menuId);

}
