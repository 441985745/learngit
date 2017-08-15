package com.opple.fa.purchase.dao;

import java.util.List;

import com.opple.fa.purchase.entity.PurchaseApplyMapping;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.model.PurchaseApplyMappingModel;

public interface PurchaseApplyMappingDAO {

	/**
	 * 保存
	 * @param applyDemandMapping
	 */
	void save(PurchaseApplyMapping purchaseApplyMapping);
	
	/**
	 * 根据订单ID查询订单
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseApplyMappingModel> searchPurchaseApplyMappingByOrderId(PurchaseOrder purchaseOrder);
	/**
	 * 删除
	 * @param purchaseOrder
	 */
	void deletePurchaseApplyMappingByPurchaseId(PurchaseOrder purchaseOrder);

	List<PurchaseApplyMappingModel> searchPurchaseApplyMappingByOrderId1(PurchaseOrder purchaseOrder);

	void saveDeleteBranch(PurchaseApplyMappingModel purchaseApplyMappingModel);
}
