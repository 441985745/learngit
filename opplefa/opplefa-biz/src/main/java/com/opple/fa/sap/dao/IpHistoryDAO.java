package com.opple.fa.sap.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.sap.model.IpHistoryModel;

public interface IpHistoryDAO {

	/**
	 * 保存订单
	 * @param applyOrderModel
	 * @return
	 */
	void save(@Param("ipHistory")IpHistoryModel ipHistoryModel);
}
