package com.bazl.dna.util.excel;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Excel数据对象
 * @author lrm
 */
public class ExcelData implements Serializable {

	private static final long serialVersionUID = 1L;

	/***
	 * Sheet名称
	 */
	private String sheetName;

	/***
	 * 表格列名
	 */
	private String[] columnNames = new String[0];

	/***
	 * 列表数据
	 */
	private List<Object[]> dataList = Lists.newArrayList();

	public ExcelData(){
		super();
	}

	public ExcelData(String sheetName){
		this.sheetName = sheetName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public List<Object[]> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object[]> dataList) {
		this.dataList = dataList;
	}

}
