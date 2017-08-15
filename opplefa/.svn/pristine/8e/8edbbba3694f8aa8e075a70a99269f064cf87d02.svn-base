package com.opple.fa.processasset.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.processasset.entity.ProcessAssetsDetall;

public interface ProcessAssetsDetallDAO {
	
	/**
	 * 查看
	 * @param processAssetsDetall
	 * @return
	 */
	List<ProcessAssetsDetall> getProcessAssetsDetall(@Param("processAssetsDetall")ProcessAssetsDetall processAssetsDetall);
	
	/**
	 * 添加保存
	 * @param processAssetsDetall
	 */
	void saveProcessAssetsDetall(@Param("processAssetsDetall")ProcessAssetsDetall processAssetsDetall);
	
	/**
	 * 逻辑删除旧数据
	 * @param status
	 */
	void updateStatus(@Param("processAssetsId")long processAssetsId);
	/**
	 * 获得所有的子表明细
	 * @param status
	 */
	List<ProcessAssetsDetall> searchProcessAssetsDetall(ProcessAssetsDetall processAssetsDetall);
	
	/**
	 * 获得所有的子表明细 
	 * @param status
	 */
	List<String> searchProcessAssetsDetallById(ProcessAssetsDetall processAssetsDetall);

}
