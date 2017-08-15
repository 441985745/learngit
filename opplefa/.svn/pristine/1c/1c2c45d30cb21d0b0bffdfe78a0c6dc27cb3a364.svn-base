package com.opple.fa.purchase.dao;

import java.util.List;

import com.opple.fa.purchase.entity.ApplyDemandMapping;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.DemandApplyBudget;
import com.opple.fa.purchase.entity.PurchaseApplyMapping;
import com.opple.fa.purchase.model.ApplyDemandMappingModel;

public interface ApplyDemandMappingDAO {

	/**
	 * 保存
	 * @param applyDemandMapping
	 */
	void save(ApplyDemandMapping applyDemandMapping);
	
	/**
	 * 根据applyId关联查询资产明细(也就是采购需求明细)
	 * @param applyDemandMappingModel (applyId)
	 * @return
	 */
	List<ApplyDemandMappingModel> searchAssetsDetail(ApplyDemandMappingModel applyDemandMappingModel);
	
	/**
	 * 根据订单ID和合并号查询资产明细(采购订单选择明细)
	 * @param applyOrderDetail
	 * @return
	 */
	List<ApplyDemandMappingModel> searchForPurchaseOrder(ApplyOrderDetail applyOrderDetail);
	/**
	 * 根据id 删除
	 * @param applyDemandMapping
	 */
	void deleteApplyDemandMappingByApplyId(ApplyDemandMapping applyDemandMapping);
	
	/**
	 * 回写数量
	 */
	void writeBack(PurchaseApplyMapping purchaseApplyMapping);
	
	/**
	 * 根据ID查询
	 * @param applyDemandMapping
	 * @return
	 */
	ApplyDemandMapping searchApplyDemandMappingById(ApplyDemandMapping applyDemandMapping);

	List<DemandApplyBudget> searchDemandApplyBudget(String cdocument);

    List<ApplyDemandMapping> searchApplyDemandMappingByApplyId(ApplyDemandMapping delADM);

	void saveDeleteBatch(ApplyDemandMapping list);
}
