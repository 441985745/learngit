package com.opple.fa.purchase.service;


import java.rmi.RemoteException;
import java.util.List;


import com.opple.fa.config.entity.Department;
import com.opple.fa.purchase.entity.ApplyDemandMapping;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.OutBudgetException;
import com.opple.fa.purchase.model.ApplyDemandMappingModel;
import com.opple.fa.purchase.model.ApplyOrderModel;
import com.opple.fa.purchase.model.DemandOrderDetailModel;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

public interface ApplyOrderService {

    /**
     * 分页查询采购申请订单
     *
     * @param pager
     * @param applyOrderModel
     * @return
     */
    Pager<ApplyOrder> searchApplyOrder(Pager<ApplyOrder> pager, ApplyOrderModel applyOrderModel);

    /**
     * 根据id获得采购申请订单
     *
     * @param applyOrder
     * @return
     */
    ApplyOrder searchApplyOrderByOrderId(ApplyOrder applyOrder);

    /**
     * 添加申请订单
     *
     * @param applyOrder
     * @param applyOrderDetail
     * @param applyDemandMapping
     * @param demandOrderDetailModel
     * @return
     */
    ExecuteResult<ApplyOrder> addSaveApply(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetail,
                                           List<ApplyDemandMapping> applyDemandMapping, List<DemandOrderDetailModel> demandOrderDetailModel) throws OutBudgetException;

    ExecuteResult<ApplyOrder> addSaveApplyDetail(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetail,
                                                 List<ApplyDemandMapping> applyDemandMapping, List<DemandOrderDetailModel> demandOrderDetailModel) throws OutBudgetException;


    /**
     * 修改申请订单
     *
     * @param applyOrder
     * @param applyOrderDetail
     * @param applyDemandMapping
     * @param demandOrderDetailModel
     * @return
     */
    ExecuteResult<ApplyOrder> addSaveUpdateApply(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetail,
                                                 List<ApplyDemandMapping> applyDemandMapping, List<DemandOrderDetailModel> demandOrderDetailModel, ApplyOrder oldApplyOrder) throws OutBudgetException, RemoteException;

    /**
     * 查询下个序列值
     *
     * @return
     */
    int searchNextSequence();

    /**
     * 根据applyId关联查询资产明细(也就是采购需求明细)
     *
     * @param applyDemandMappingModel (applyId)
     * @return
     */
    List<ApplyDemandMappingModel> searchAssetsDetail(ApplyDemandMappingModel applyDemandMappingModel);

    /**
     * 导出
     *
     * @param applyOrderModel
     * @return
     */
    List<ApplyOrder> exportApplyOrder(ApplyOrderModel applyOrderModel);

    /**
     * 申请绑定审批流 ykz
     *
     * @param applyOrder
     * @return
     */
    ExecuteResult<ApplyOrder> applyComplaint(ApplyOrder applyOrder);

    /**
     * 删除绑定失败的数据
     *
     * @param applyOrder
     */
    void delApplyOrderByCdocument(ApplyOrder applyOrder);

    /**
     * 同意审批
     *
     * @param applyOrderOnly
     * @return
     */
    ExecuteResult<ApplyOrder> agreeWorkFlow(ApplyOrder applyOrderOnly);

    /**
     * 退回审批
     *
     * @param applyOrder
     * @return
     */
    ExecuteResult<ApplyOrder> denyWorkFlow(ApplyOrder applyOrder);

    /**
     * 弃审审批
     *
     * @param applyOrderOnly
     * @return
     */
    ExecuteResult<ApplyOrder> dropWorkFlow(ApplyOrder applyOrderOnly);

    /**
     * 更新数据
     *
     * @param applyOrder
     * @param applyOrderDetails
     * @return
     */
    ExecuteResult<ApplyOrder> updateApplyOrder(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetails);

    /**
     * 根据登录人筛选部门
     *
     * @return
     */
    List<Department> searchDepartmentByUserCode(ApplyOrderModel applyOrderModel);

    /**
     * @param applyOrder
     * @param applyOrderDetails
     * @return
     */
    ExecuteResult<ApplyOrder> updateApplyOrderDetail(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetails);

    /**
     * 删除采购申请单
     *
     * @param applyOrder
     * @param applyOrderDetails
     * @return
     */
    ExecuteResult<ApplyOrder> deleteApplyOrder(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetails) throws OutBudgetException;

    /**
     * 关闭采购申请单
     *
     * @param applyOrder
     * @param applyOrderDetails
     * @return
     */
    ExecuteResult<ApplyOrder> updateCloseApplyOrder(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetails);

    /**
     * sap 已完成
     *
     * @param applyOrder
     * @return
     */
    ExecuteResult<ApplyOrder> updateApplyOrderIssap(ApplyOrder applyOrder);

    /**
     * 弃审时查询采购订单使用申请单的数量
     *
     * @param applyOrder
     * @return
     */
    Long searchNextPurchaseCount(ApplyOrder applyOrder);

    /**
     * 查询并修改需求单状态(关闭/打开)
     *
     * @param applyOrder
     * @param aods
     * @return
     */
    ExecuteResult<ApplyOrder> updateSearchCloseDemand(ApplyOrder applyOrder, List<ApplyOrderDetail> aods);

    ExecuteResult<ApplyOrder> updateSearchOpenDemand(ApplyOrder applyOrder);

    /**
     * @param applyOrder
     * @return
     */
    void getMainAssetCodePro(ApplyOrder applyOrder);

    /**
     * 修改采购申请
     *
     * @param applyOrder
     */
    void update(ApplyOrder applyOrder);

    /**
     * 修改打印状态'Y'
     *
     * @param applyOrder
     */
    void updatePrint(ApplyOrder applyOrder);

    String searchDemandOrderStatus(Long demandDetailId);

    String searchIsCrossAssetAccounting(String billCode, String typeId, String roleId, String activityid);
}
