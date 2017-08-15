package com.opple.fa.excel;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * 将数据导出到excel接口定义
 * 
 * @author WangXuzheng
 *
 */
public interface ExcelExportTemplate<T> {
	/**
	 * 将数据导出为excel
	 * 
	 * @param fileName
	 *            文件输出流
	 * @param response
	 *            参数
	 */
	void doExport(HttpServletResponse response, String fileName, List<T> ts) throws Exception;

	/**
	 * 要创建的excel文件的sheet名称
	 * 
	 * @return
	 */
	String[] getSheetNames();

	/**
	 * 要创建的excel表格中的表头内容. list中存放的是多个sheet的表头内容
	 * 
	 * @return
	 */
	String[][] getTitles();

	/**
	 * 要创建的excel表格的每个sheet的表头
	 * 
	 * @return
	 */
	String[] getCaptions();

	/**
	 * 控制文件在内存中最多占用多少条
	 * 
	 * @return
	 */
	int getRowAccessWindowSize();
}