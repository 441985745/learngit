package com.opple.fa.purchase.dao;

import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.model.PurchaseOrderDetailModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @Description:采购订单
 *
 */
public interface PurchaseOrderDetailDAO {

	/*
	List<PurchaseOrderDetailModel> searchPurchaseOrderDetailList( PurchaseOrderDetailModel purchaseOrderDetail);

	PurchaseOrderDetailModel get(PurchaseOrderDetailModel purchaseOrderDetailModel);*/

	void save(PurchaseOrderDetail purchaseOrderDetail);
	
	/**
	 * 根据订单ID查询订单
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseOrderDetailModel> searchPurchaseOrderDetailByOrderId(PurchaseOrder purchaseOrder);
	
	/**
	 * 删除
	 * @param purchaseOrder
	 */
	void deletePurchaseOrderDetailByPurchaseId(PurchaseOrder purchaseOrder);

	/**
	 * 更新拆分后次级编码和主编
	 * @param purchaseOrderDetail
	 */
    void updateCode(@Param("purchaseOrderDetail") PurchaseOrderDetailModel purchaseOrderDetail);

	/**
	 * 根据订单详情表主键查询订单详情明细
	 * @param id
	 * @return
	 */
    PurchaseOrderDetail searchPurchaseOrderDetailById(@Param("id") String id);
	/**
	 * 根据采购订单单据号获取明细
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseOrderDetail> searchPurchaseOrderDetailByDocument(@Param("purchaseOrder") PurchaseOrder purchaseOrder);

    List<PurchaseOrderDetail> searchPurchaseOrderDetailByPid(@Param("purchaseOrder") PurchaseOrder purchaseOrder);

	void saveDeleteBranch(PurchaseOrderDetail purchaseOrderDetail);
}
