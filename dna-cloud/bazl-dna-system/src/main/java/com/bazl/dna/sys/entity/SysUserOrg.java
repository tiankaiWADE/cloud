package com.bazl.dna.sys.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 基础权限--用户部门nt_sys_user_org
 * 
 * @author liutao
 */
@Entity
@Table(name = "nt_sys_user_org")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class SysUserOrg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号 id
	 */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid")
	private String id;

	/**
	 * 部门 org_id
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "org_id", nullable = true)
	private SysOrganization sysOrganization;

	/**
	 * 用户 user_id
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", nullable = true)
	private SysUser sysUser;

	/**
	 * 排序 position_order
	 */
	@Column(name = "position_order", nullable = false, length = 10)
	private Integer positionOrder;

	/**
	 * 状态 status
	 */
	@Column(name = "status", nullable = false, length = 32)
	private String status;

	/**
	 * 创建时间 create_time
	 */
	@Column(name = "create_time", insertable = true, updatable = false)
	private Timestamp createTime;

	/**
	 * 更新时间 update_time
	 */
	@Column(name = "update_time", insertable = false, updatable = true)
	private Timestamp updateTime;

	/**
	 * Generate constructor
	 */
	public SysUserOrg() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public SysOrganization getSysOrganization() {
		return sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Integer getPositionOrder() {
		return positionOrder;
	}

	public void setPositionOrder(Integer positionOrder) {
		this.positionOrder = positionOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
}
