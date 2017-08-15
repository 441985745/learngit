package com.opple.fa.purchase.dao;

import java.util.List;

import com.opple.fa.config.entity.Department;
import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.model.PurchaseOrderModel;
import com.opple.util.Pager;

/**
 * @Description:采购订单
 *
 */
public interface PurchaseOrderDAO {

	/**
	 * 分頁查詢--採購訂單--list
	 * 
	 * @param pager
	 * @param purchaseOrderModel
	 * @return
	 */
	List<PurchaseOrder> searchPurchaseOrder(@Param("pager") Pager pager,
			@Param("purchaseOrderModel") PurchaseOrderModel purchaseOrderModel);

	/**
	 * 分頁查詢--採購訂單--count
	 * @param purchaseOrderModel
	 * @return
	 */
	Long searchPurchaseOrderCount(@Param("purchaseOrderModel") PurchaseOrderModel purchaseOrderModel);

	List<PurchaseOrder> searchPurchaseOrderList(PurchaseOrder purchaseOrder);

	PurchaseOrder get(@Param("purchaseOrder")PurchaseOrder purchaseOrder);
	
	/**
	 * 根据订单ID查询订单
	 * @param purchaseOrder
	 * @return
	 */
	PurchaseOrder searchPurchaseOrderByOrderId(PurchaseOrder purchaseOrder);

	PurchaseOrder searchPurchaseOrderByOrderIdOnly(PurchaseOrder purchaseOrder);

	/**
	 * 查询下个序列值
	 * @return 
	 */
	int searchNextSequence();

	void save(PurchaseOrder purchaseOrder);
	void update(PurchaseOrder purchaseOrder);
	/**
	 * 删除
	 * @param purchaseOrder
	 */
	void deletePurchaseOrderByPurchaseId(PurchaseOrder purchaseOrder);

	/**
	 * 预算所属部门
	 *
	 * @param purchaseOrderMode
	 * @return
	 */
	List<Department> searchDepartmentByUserCode(@Param("purchaseOrderModel") PurchaseOrderModel purchaseOrderMode);

	/**
	 * 更新purchaseOrder
	 * @param purchaseOrder
	 */
    void updatePurchaseOrder(@Param("purchaseOrder") PurchaseOrder purchaseOrder);

    /**
	 * 修改订单状态
	 * @param applyOrder
	 */
	void updateOrderStatus(PurchaseOrder purchaseOrder);
	/**
	 * 修改订单 付款关闭状态
	 * @param applyOrder
	 */
	void updateOrderStatusForPayment(PurchaseOrder purchaseOrder);
	/**
	 * 修改是否传SAP
	 * @param purchaseOrder
	 */
	void updateOrderIssap(PurchaseOrder purchaseOrder);
	
	/**
	 * 弃审时查询收货单使用采购订单的数量
	 * @param purchaseOrder
	 * @return
	 */
    Long searchNextReceiveCount(PurchaseOrder purchaseOrder);
    /**
     * 导出
     * @param purchaseOrderModel
     * @return
     */
    List<PurchaseOrder> exportPurchaseOrder(@Param("purchaseOrderModel") PurchaseOrderModel purchaseOrderModel);
    
    /**
     * 恢复采购申请单的最大值
     * @param purchaseOrder
     */
    void updateApplyNextSecondarycoding(PurchaseOrder purchaseOrder);
    
    /**
     * 采购订单次级编码置空
     */
    void updatePurchaseSecondary(PurchaseOrder purchaseOrder);
    void updateOrderStatusByGoodsCount(String purchaseId);
    /**
     * 修改打印状态'Y'
     * @param purchaseOrder
     */
	void updatePrint(PurchaseOrder purchaseOrder);

    String searchApplyOrderStatus(String applyOrderId);

	void saveDelete(PurchaseOrder po);
}
