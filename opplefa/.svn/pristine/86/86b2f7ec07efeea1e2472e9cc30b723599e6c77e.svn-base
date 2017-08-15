package com.opple.fa.purchase.service;

import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.model.PurchaseOrderDetailModel;

import java.util.List;

/**
 * @Description:採購訂單 
 *
 */
public interface PurchaseOrderDetailService {

	/**
	 * 分页查询--采购订单--list
	 * 
	 * @param pager
	 * @param purchaseOrderModel
	 * @return
	 */
	//Pager<PurchaseOrder> searchPurchaseOrder(Pager<PurchaseOrder> pager, PurchaseOrderModel purchaseOrderModel);

	/*List<PurchaseOrderDetailModel> searchPurchaseOrderDetailList(PurchaseOrderDetailModel purchaseOrderDetail);

	PurchaseOrderDetailModel get(PurchaseOrderDetailModel purchaseOrderDetailModel);*/
	
	/**
	 * 根据订单号查询订单
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseOrderDetailModel> searchPurchaseOrderDetailByOrderId(PurchaseOrder purchaseOrder);

	/**
	 * 根据采购订单号
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseOrderDetail> searchPurchaseOrderDetailByDocument(PurchaseOrder purchaseOrder);

	/**
	 * 更新主级和拆分后的次级编码
	 * @param purchaseOrderDetailModel
	 */
    void updateCode(List<PurchaseOrderDetailModel> purchaseOrderDetailModel);

	/**
	 * 根据主键查询
	 * @param purchaseDetailId
	 */
	PurchaseOrderDetail searchPurchaseOrderDetailById(String purchaseDetailId);
}
