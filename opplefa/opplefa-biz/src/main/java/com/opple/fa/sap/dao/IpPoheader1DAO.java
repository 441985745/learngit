package com.opple.fa.sap.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.sap.model.IpPoheader1Model;

public interface IpPoheader1DAO {

	/**
	 * 保存订单
	 * @param applyOrderModel
	 * @return
	 */
	void save(@Param("ipPoheader1")IpPoheader1Model ipPoheader1Model);
}
