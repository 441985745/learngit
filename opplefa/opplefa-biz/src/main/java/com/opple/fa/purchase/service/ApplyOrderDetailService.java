package com.opple.fa.purchase.service;


import java.util.List;

import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.model.ApplyDemandMappingModel;
import com.opple.fa.purchase.model.ApplyOrderModel;
public interface ApplyOrderDetailService {
	
	/**
	 * 根据订单编号查询明细
	 * @param applyOrderDetailModel
	 * @return
	 */
	List<ApplyOrderDetail> searchApplyOrderDetailByOrderId(ApplyOrder applyOrder);

	ApplyOrderDetail get(String strId);
	
	/**
	 * 采购订单页面 选择明细  查询采购申请明细列表
	 * @param applyOrder
	 * @return
	 */
	List<ApplyOrderDetail> searchApplyOrderDetailList(ApplyOrderModel applyOrderModel);

	/**
	 * 根据applyOrderDetail的id获取数据
	 * @param applyOrderDetail
	 * @return
	 */
	ApplyOrderDetail searchApplyOrderDetailById(ApplyOrderDetail applyOrderDetail);

	/**
	 * 更新下次拆分次级编码 开头
	 * @param applyOrderDetailList
	 */
    void updateNextSecondaryCoding(List<ApplyOrderDetail> applyOrderDetailList);
    
    void updateUnits(List<ApplyOrderDetail> applyOrderDetailList);


	DemandOrderDetail searchApplyDemandMapping(ApplyDemandMappingModel applyDemandMappingModel);
}
