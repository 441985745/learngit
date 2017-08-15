package com.opple.fa.purchase.service;


import java.util.List;

import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.model.DemandOrderDetailModel;
public interface DemandOrderDetailService {
	
	/**
	 * 根据订单编号查询明细
	 * @param demandOrderDetailModel
	 * @return
	 */
	List<DemandOrderDetailModel> searchDemandOrderDetailByOrderId(DemandOrder demandOrder);

	/**
	 * 模糊查询
	 * @param demandOrderDetailModel
	 * @return
	 */
	List<DemandOrderDetail> searchDemandOrderDetail(DemandOrderDetailModel demandOrderDetailModel);

	String searchDemandOrderIdByDetailId(Long id);

}
