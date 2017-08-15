package com.opple.fa.sap.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.sap.entity.EtZmm0012;
public interface PurchaseOrderSapDAO {

	/**
	 * 保存订单结果
	 * @param applyOrderModel
	 * @return
	 */
	void save(@Param("etZmm0012")EtZmm0012 etZmm0012);
	
	/**根据采购订单号查询最近已经存在的采购凭证号
	 * @param itZmm0011Model
	 * @return
	 */
	String selectExists(String purchaseId);
	/**
	 * 删除sap订单信息
	 * @param etZmm0012
	 * @return
	 */
	void delete(@Param("etZmm0012")EtZmm0012 etZmm0012);
}
