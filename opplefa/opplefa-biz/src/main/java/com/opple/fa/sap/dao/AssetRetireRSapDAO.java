package com.opple.fa.sap.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.sap.entity.Retirementdata;

public interface AssetRetireRSapDAO {

	/**
	 * 保存
	 * @param retirementdata
	 * @return
	 */
	void save(@Param("retirementdata")Retirementdata retirementdata);
}
