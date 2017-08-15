package com.opple.fa.purchase.dao;

import java.util.List;

import com.opple.fa.purchase.entity.PurchaseOrder;
import org.apache.ibatis.annotations.Param;

import com.opple.fa.config.entity.Department;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.model.ApplyOrderModel;
import com.opple.util.Pager;

public interface ApplyOrderDAO {

	/**
	 * 分页查询采购申请订单
	 * @param pager
	 * @param applyOrder
	 * @return
	 */
	List<ApplyOrder> searchApplyOrder(@Param("pager") Pager pager, @Param("applyOrderModel") ApplyOrderModel applyOrderModel);
	
	/**
	 * 查询采购申请订单总数量
	 * @param applyOrder
	 * @return
	 */
	Long searchApplyOrderCount(@Param("applyOrderModel") ApplyOrderModel applyOrderModel);
	
	/**
	 * 根据id获得采购申请订单
	 * @param 订单ID
	 * @return
	 */
	ApplyOrder searchApplyOrderByOrderId(@Param("applyOrder") ApplyOrder applyOrder);

	/**
	 * 保存订单
	 * @param applyOrderModel
	 * @return
	 */
	void save(ApplyOrder applyOrder);
	
	/**
	 * 修改保存订单
	 * @param applyOrder
	 */
	void update(ApplyOrder applyOrder);

	/**
	 * 查询下个序列值
	 * @return 
	 */
	int searchNextSequence();
	
	/**
	 * 导出
	 * @param applyOrderModel
	 * @return
	 */
	List<ApplyOrder> exportApplyOrder(@Param("applyOrderModel") ApplyOrderModel applyOrderModel);

	void delApplyOrderByCdocument(ApplyOrder applyOrder);
	/**
	 *  根据登录人筛选部门 
	 * @return
	 */
	List<Department> searchDepartmentByUserCode(@Param("applyOrderModel") ApplyOrderModel applyOrderModel);

	/**
	 * 更改
	 * @param applyOrder
	 */
    void updateApplyOrder(@Param("applyOrder") ApplyOrder applyOrder);
    
    /**
	 * 修改订单状态
	 * @param applyOrder
	 */
	void updateOrderStatus(ApplyOrder applyOrder);

	/**
	 * 修改传issap状态
	 * @param applyOrder
	 */
    void updateApplyOrderIssap(@Param("applyOrder") ApplyOrder applyOrder);
    
    /**
     * 弃审时查询采购订单使用申请单的数量 
     * @param applyOrder
     * @return
     */
    Long searchNextPurchaseCount(ApplyOrder applyOrder);
    /**
	 * 根据采购订单号查询该单据对应的所有申请单号
	 * @param applyOrder
	 * @return
	 */
	List<ApplyOrder> searchApplyByPurchaseId(PurchaseOrder purchaseOrder);

	/**
	 * 根据申请单查询该单据存在未下采购订单的数量 
	 * @param demandOrder
	 * @return
	 */
	Long searchNotApplyCountByApplyId(ApplyOrder applyOrder);
	
	/**
	 * 
	 * @param java.util.Map
	 * @return
 	 */
	void getMainAssetCodePro(ApplyOrder applyOrder);
	/**
     * 修改打印状态'Y'
     * @param applyOrder
     */
	void updatePrint(ApplyOrder applyOrder);

    String searchDemandOrderStatus(Long demandDetailId);

	void updateApplyOrderIssapNo(ApplyOrder applyOrder);

    String searchIsCrossAssetAccounting(@Param("billCode")String billCode, @Param("typeId")String typeId, @Param("roleId")String roleId, @Param("activityid")String activityid);

    void saveDelete(ApplyOrder ao);
}
