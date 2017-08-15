package com.opple.fa.purchase.service;

import java.rmi.RemoteException;
import java.util.List;

import com.opple.fa.config.entity.Department;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.OutBudgetException;
import com.opple.fa.purchase.entity.PaymentTerm;
import com.opple.fa.purchase.entity.PurchaseApplyMapping;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.model.ApplyDemandMappingModel;
import com.opple.fa.purchase.model.PurchaseApplyMappingModel;
import com.opple.fa.purchase.model.PurchaseOrderModel;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

/**
 * @Description:採購訂單
 */
public interface PurchaseOrderService {

    /**
     * 分页查询--采购订单--list
     *
     * @param pager
     * @param purchaseOrderModel
     * @return
     */
    Pager<PurchaseOrder> searchPurchaseOrder(Pager<PurchaseOrder> pager, PurchaseOrderModel purchaseOrderModel);

    List<PurchaseOrder> searchPurchaseOrderList(PurchaseOrder purchaseOrder);

    PurchaseOrder get(PurchaseOrder purchaseOrder);

    /**
     * 添加保存订单
     *
     * @param purchaseOrder
     * @param purchaseOrderDetails
     * @param purchaseApplyMappings
     * @param paymentTerms
     * @return
     */
    ExecuteResult<PurchaseOrder> addSavePurchase(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchaseOrderDetails, List<PurchaseApplyMapping> purchaseApplyMappings, List<PaymentTerm> paymentTerms) throws OutBudgetException;

    /**
     * 修改保存订单
     *
     * @param purchaseOrder
     * @param purchaseOrderDetails
     * @param purchaseApplyMappings
     * @param paymentTerms
     * @return
     */
    ExecuteResult<PurchaseOrder> addUpdatePurchase(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchaseOrderDetails, List<PurchaseApplyMapping> purchaseApplyMappings, List<PaymentTerm> paymentTerms, PurchaseOrder oldPurchaseOrder) throws OutBudgetException, RemoteException;

    /**
     * 根据订单号查询订单
     *
     * @param purchaseOrder
     * @return
     */
    PurchaseOrder searchPurchaseOrderByOrderId(PurchaseOrder purchaseOrder);

    PurchaseOrder searchPurchaseOrderByOrderIdOnly(PurchaseOrder purchaseOrder);

    /**
     * 根据订单ID查询Mapping
     *
     * @param purchaseOrder
     * @return
     */
    List<PurchaseApplyMappingModel> searchPurchaseApplyMappingByOrderId(PurchaseOrder purchaseOrder);

    /**
     * 根据订单ID和合并号查询资产明细(采购订单选择明细)
     *
     * @param applyOrderDetail
     * @return
     */
    List<ApplyDemandMappingModel> searchForPurchaseOrder(ApplyOrderDetail applyOrderDetail);

    /**
     * 查询下个序列值
     *
     * @return
     */
    int searchNextSequence();

    /**
     * 绑定审批流
     *
     * @param purchaseOrder
     * @return
     */
    ExecuteResult<PurchaseOrder> applyComplaint(PurchaseOrder purchaseOrder);

    /**
     * 审批流 同意
     *
     * @param applyOrderOnly
     * @return
     */
    ExecuteResult<PurchaseOrder> agreeWorkFlow(PurchaseOrder applyOrderOnly);

    /**
     * 审批流 退回
     *
     * @param purchaseOrder
     * @return
     */
    ExecuteResult<PurchaseOrder> denyWorkFlow(PurchaseOrder purchaseOrder);

    /**
     * 审批流 弃审
     *
     * @param purchaseOrderOnly
     * @return
     */
    ExecuteResult<PurchaseOrder> dropWorkFlow(PurchaseOrder purchaseOrderOnly);

    /**
     * del
     *
     * @param purchaseOrderOnly
     * @return
     */
    ExecuteResult<PurchaseOrder> deletePurchaseOrderByPurchaseId(PurchaseOrder purchaseOrderOnly);

    /**
     * 根据登录人筛选部门
     *
     * @return
     */
    List<Department> searchDepartmentByUserCode(PurchaseOrderModel purchaseOrder);

    /**
     * 更新PurchaseOrder
     *
     * @param purchaseOrder
     */
    void updatePurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 保存明细行
     *
     * @param purchaseOrder
     * @param purchaseOrderDetails
     * @param purchaseApplyMappings
     * @param paymentTerms
     * @return
     */
    ExecuteResult<PurchaseOrder> addSavePurchaseDetail(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchaseOrderDetails, List<PurchaseApplyMapping> purchaseApplyMappings, List<PaymentTerm> paymentTerms) throws OutBudgetException;


    ExecuteResult<PurchaseOrder> deletePurchaseOrder(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchaseOrderDetails, List<PurchaseApplyMappingModel> purchaseApplyMappings) throws OutBudgetException;


    /**
     * 关闭收货
     *
     * @param purchaseOrder
     * @param purchaseOrderDetails
     * @param purchaseApplyMappings
     * @return
     */
    ExecuteResult<PurchaseOrder> updateClosePurchaseOrder(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchaseOrderDetails, List<PurchaseApplyMappingModel> purchaseApplyMappings);

    /**
     * 关闭付款
     *
     * @param purchaseOrder
     * @return
     */
    ExecuteResult<PurchaseOrder> updateClosePurchaseOrderForPayment(PurchaseOrder purchaseOrder);

    /**
     * 修改是否传SAP
     *
     * @param purchaseOrder
     */
    void updateOrderIssap(PurchaseOrder purchaseOrder);

    /**
     * 弃审时查询收货单使用采购订单的数量
     *
     * @param purchaseOrder
     * @return
     */
    Long searchNextReceiveCount(PurchaseOrder purchaseOrder);

    /**
     * 查询并修改申请单状态(关闭)
     *
     * @param applyOrder
     * @return
     */
    ExecuteResult<PurchaseOrder> updateSearchCloseApply(PurchaseOrder purchaseOrder);

    /**
     * 导出
     *
     * @param purchaseOrderModel
     * @return
     */
    List<PurchaseOrder> exportPurchaseOrder(PurchaseOrderModel purchaseOrderModel);

    /**
     * 是否是最后一步审批人
     */
    boolean getIsLastApprovalStep(PurchaseOrderModel purchaseOrderModel);

    void update(PurchaseOrder purchaseOrder);

    /**
     * 传SAP失败后，把申请单的‘下次次级’恢复，把采购订单的次级清空
     *
     * @param purchaseOrder
     * @return
     */
    ExecuteResult<PurchaseOrder> updateSecondary(PurchaseOrder purchaseOrder);

    /**
     * 修改打印状态'Y'
     *
     * @param purchaseOrder
     */
    void updatePrint(PurchaseOrder purchaseOrder);


    String searchApplyOrderStatus(String applyOrderId);
}
