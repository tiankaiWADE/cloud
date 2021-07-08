package com.bazl.dna.sys.dao;

import java.util.List;
import java.util.Map;

import com.bazl.dna.common.exception.DnaRuntimeException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bazl.dna.sys.entity.SysRoleMenuOper;

/**
 * 菜单操作权限接口
 * @author zhaoyong
 */
public interface ISysRoleMenuOperJPADao extends JpaRepository<SysRoleMenuOper, String>,JpaSpecificationExecutor<SysRoleMenuOper>{

	/**
	 * 查询操作权限列表
	 * @param roleId 角色编号
	 * @param menuTypeId 菜单类型编号
	 * @return List
	 */
	@Query(value = "select menu_id as menuId,operation_id as operationId from nt_sys_role_menu_oper where role_id=?1 and menu_type_id=?2",nativeQuery = true)
    List<Map<String,String>> findMenuOperListByRoleId(String roleId, String menuTypeId);

	/**
	 * 删除操作权限
	 * @param roleId 角色编号
	 */
	@Modifying(clearAutomatically = true)
	@Transactional(rollbackFor = DnaRuntimeException.class)
	@Query(value = "delete from nt_sys_role_menu_oper where role_id=?1",nativeQuery = true)
    void deleteRoleMenuOperByRoleId(String roleId);

	/**
	 * 查询操作权限列表
	 * @param roles 角色
	 * @param menuId 菜单编号
	 * @return List
	 */
	@Query(value = "select DISTINCT b.id as operId,b.operation_name as operName,b.operation_type as operType from nt_sys_role_menu_oper a,nt_sys_operation b where a.role_id in (:roles) and a.operation_id=b.id and a.menu_id=:menuId",nativeQuery = true)
    List<Map<String, String>> findMenuOperByRoles(@Param("roles") List<String> roles, @Param("menuId") String menuId);

}
