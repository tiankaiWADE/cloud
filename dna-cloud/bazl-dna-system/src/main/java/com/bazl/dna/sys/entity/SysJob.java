package com.bazl.dna.sys.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;

/**
 * 职位表 - nt_sys_job
 *
 * @author liutao
 *
 */
@Entity
@Table(name = "nt_sys_job")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class SysJob implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号 id
	 */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid")
	private String id;

	/**
	 * 职位名称 job_name
	 */
	@Column(name = "job_name", nullable = false, length = 32)
	private String jobName;

	/**
	 * 职位名称 parent_id
	 */
	@Column(name = "parent_id", nullable = false, length = 32)
	private String parentId;

	/**
	 * 职位图标 job_logo
	 */
	@Column(name = "job_logo", nullable = true, length = 32)
	private String jobLogo;

	/**
	 * 职位图标 job_logo_url
	 */
	@Column(name = "job_logo_url", nullable = true, length = 32)
	private String jobLogoUrl;

	/**
	 * 职位描述 job_info
	 */
	@Column(name = "job_info", nullable = true, length = 32)
	private String jobInfo;

	/**
	 * 职位排序 job_order
	 */
	@Column(name = "job_order", nullable = false, length = 10)
	private Integer jobOrder;

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
	 * Generate constructor user_job
	 */
	public SysJob() {
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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getJobLogo() {
		return jobLogo;
	}

	public void setJobLogo(String jobLogo) {
		this.jobLogo = jobLogo;
	}

	public String getJobLogoUrl() {
		return jobLogoUrl;
	}

	public void setJobLogoUrl(String jobLogoUrl) {
		this.jobLogoUrl = jobLogoUrl;
	}

	public String getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(String jobInfo) {
		this.jobInfo = jobInfo;
	}

	public Integer getJobOrder() {
		return jobOrder;
	}

	public void setJobOrder(Integer jobOrder) {
		this.jobOrder = jobOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * toString Method
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).toString();
	}

}
