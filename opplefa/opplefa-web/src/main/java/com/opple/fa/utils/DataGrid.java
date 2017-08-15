package com.opple.fa.utils;

import java.util.List;

/**
 * easyui的datagrid模型
 * 
 * @author zhangtao
 * 
 */
public class DataGrid implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6682040595154253063L;
	// 总记录数
	private Long total;
	@SuppressWarnings("rawtypes")
	// 每行记录
	private List rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}
}
