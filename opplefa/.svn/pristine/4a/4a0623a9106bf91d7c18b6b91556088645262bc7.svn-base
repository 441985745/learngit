package com.opple.fa.purchase.service;


import java.rmi.RemoteException;
import java.util.List;

import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.entity.OutBudgetException;
import com.opple.fa.purchase.model.DemandOrderDetailModel;
import com.opple.fa.purchase.model.DemandOrderExamineWrapper;
import com.opple.fa.purchase.model.DemandOrderModel;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

public interface DemandOrderService {
	
	/**
	 * 分页查询采购需求订单
	 * @param pager
	 * @param demandOrderModel
	 * @return
	 */
	Pager<DemandOrder> searchDemandOrder(Pager<DemandOrder> pager, DemandOrderModel demandOrderModel);
	
	/**
	 * 根据id获得采购需求订单
	 * @param id或OrderId
	 * @return
	 */
	DemandOrder searchDemandOrderByOrderId(DemandOrder demandOrder);
	
	/**
	 * 根据id获得采购需求订单
	 * @param id或OrderId
	 * @return
	 */
	DemandOrderModel searchDemandOrderModelByOrderId(DemandOrderModel demandOrderModel);
	
	/**
	 * 添加需求订单表头信息  为了绑定审批流
	 * @param demandOrder
	 * @param demandOrderDetail
	 * @return
	 */
	ExecuteResult<DemandOrder> saveDemandOrder(DemandOrder demandOrder);
	
	/**
	 * 添加需求订单
	 * @param demandOrder
	 * @param demandOrderDetail
	 * @return
	 */
	ExecuteResult<DemandOrder> addSaveDemand(DemandOrder demandOrder, List<DemandOrderDetail> demandOrderDetail) throws OutBudgetException, RemoteException;
	/**
	 * 保存需求订单修改页面
	 * @param demandOrder
	 * @param demandOrderDetail
	 * @return
	 */
	ExecuteResult<DemandOrder> addSaveUpdateDemand(DemandOrder demandOrder, List<DemandOrderDetail> demandOrderDetail, DemandOrder oldDemandOrder) throws OutBudgetException, RemoteException;
	
	/**
	 * 删除表头信息  并且 删除审批流
	 * @param demandOrder
	 * @return
	 */
	ExecuteResult<DemandOrder> delDemandAndWorkFlow(DemandOrder demandOrder);
	

	/**
	 * 查询下个序列值
	 * @return
	 */
	int searchNextSequence();
	/**
	 * 保存修改订单
	 * @param demandOrderModel
	 * @return
	 */
	void update(DemandOrder demandOrder);
	/**
	 * 保存修改订单(审批)
	 * @param demandOrderModel
	 * @return
	 */
	void updateExamine(DemandOrder demandOrder);
	
	/**
	 * 导出
	 * @param demandOrder
	 * @return
	 */
	List<DemandOrder> exportDemandOrder(DemandOrderModel demandOrderModel);
	
	/**
	 * 根据需求订单查询归口部门及经理
	 * @param demandOrder
	 * @return
	 */
	DemandOrder searchAttachDepartByOrder(DemandOrder demandOrder);
	
	/**
	 * 同意
	 * @param demandOrder
	 * @param isPurchasingManager
     * @return
	 */
    ExecuteResult<DemandOrder> agreeWorkFlow(DemandOrderExamineWrapper demandOrderExamineWrapper, boolean isPurchasingManager);

    /**
     * 退回
     * @param demandOrder
     * @return
     */
    ExecuteResult<DemandOrder> denyWorkFlow(DemandOrderModel demandOrderModel);

    /**
     * 弃审
     * @param demandOrder
     * @return
     */
    ExecuteResult<DemandOrder> dropWorkFlow(DemandOrderModel demandOrderModel);
    
    /**
     * 根据需求单号删除需求单、需求单明细；并回写释放预算
     * @param demandOrder
     * @param demandOrderDetails
     * @return
     */
	ExecuteResult<DemandOrder> deleteDemandOrder(DemandOrder demandOrder, List<DemandOrderDetailModel> demandOrderDetails) throws OutBudgetException;
	
	/**
	 * 关闭需求单，释放剩余预算，修改订单状态='已关闭'
	 * @param demandOrder
	 * @param demandOrderDetails
	 * @return
	 */
	ExecuteResult<DemandOrder> updateCloseDemandOrder(DemandOrder demandOrder, List<DemandOrderDetailModel> demandOrderDetails);
	
	/**
	 * 弃审时查询采购申请使用需求单的数量 
	 * @param demandOrderModel
	 * @return
	 */
	Long searchNextApplyCount(DemandOrder demandOrder);
	
	/**
	 * 查询并且关闭未申请数量全部为0的订单
	 * @param demandOrder
	 */
	void updateSearchCloseDemand(DemandOrder demandOrder);
	/**
     * 修改打印状态'Y'
     * @param demandOrder
     */
	void updatePrint(DemandOrder demandOrder);
}
