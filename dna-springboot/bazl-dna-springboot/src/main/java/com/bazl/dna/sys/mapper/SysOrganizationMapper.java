package com.bazl.dna.sys.mapper;

import java.util.List;

import com.bazl.dna.sys.entity.SysOrganization;

/**
 * 机构接口
 * @author zhaoyong
 */
public interface SysOrganizationMapper {

	/**
	 * 查询系统单位列表
	 *
	 * @param sysOrg 单位信息
	 * @return 单位列表
	 */
    List<SysOrganization> selectList(SysOrganization sysOrg);

	/**
	 * 根据用户查询系统单位列表
	 *
	 * @param userId 用户信息
	 * @return 单位列表
	 */
    List<String> selectListByUserId(String userId);

}
