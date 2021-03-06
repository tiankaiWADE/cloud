package com.bazl.dna.gateway.query;

import java.io.Serializable;

/**
 * 查询条件设置
 * 
 * @author zhaoyong
 */
public class QueryCriteriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/***
	 * 页码
	 */
	private Integer pageIndex = 1;

	/***
	 * 页大小
	 */
	private Integer pageSize = 15;

	public QueryCriteriaBean() {
		super();
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
