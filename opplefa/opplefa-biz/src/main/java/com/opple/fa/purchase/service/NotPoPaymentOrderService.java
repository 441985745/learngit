package com.opple.fa.purchase.service;

import java.util.List;

import com.opple.fa.purchase.entity.NotPoPaymentOrderDetail;
import com.opple.fa.purchase.model.PaymentPaymentMappingModel;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.NotPoPaymentOrder;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

public interface NotPoPaymentOrderService {

    /**
     * 分页查询--采购订单--list
     *
     * @param pager
     * @param paymentOrderModel
     * @return
     */
    Pager<NotPoPaymentOrder> searchPaymentOrder(Pager<NotPoPaymentOrder> pager, NotPoPaymentOrder notPoPaymentOrder);

    List<NotPoPaymentOrder> searchPaymentOrderList(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 根据订单号查询订单
     *
     * @param paymentOrder
     * @return
     */
    NotPoPaymentOrder searchPaymentOrderByOrderId(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 查询下个序列值
     *
     * @return
     */
    int searchNextSequence();

    /**
     * 保存付款信息(预付款，尾款)
     *
     * @param paymentOrder
     */
    ExecuteResult<NotPoPaymentOrder> savePayment(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 保存付款信息(验收款)
     *
     * @param paymentOrder
     */
    ExecuteResult<NotPoPaymentOrder> savePaymentForCheck(NotPoPaymentOrder notPoPaymentOrder, List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails, List<PaymentPaymentMappingModel> paymentPaymentMappingModels);

    /**
     * 绑定审批流
     *
     * @param paymentOrder
     */
    ExecuteResult<NotPoPaymentOrder> binDingFlow(NotPoPaymentOrder notPoPaymentOrder);
    /**
     * 修改付款单据，绑定审批流
     * @param paymentOrder
     */
    //ExecuteResult<PaymentOrder> updateSavePaymentOrder(PaymentOrder paymentOrder, List<PaymentOrderDetail> paymentOrderDetails,List<PaymentPaymentMappingModel> paymentPaymentMappingModels);

    /**
     * 删除
     *
     * @param paymentOrder
     */
    void delPaymentOrderByCdocument(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 同意
     *
     * @param paymentOrder
     * @return
     */
    ExecuteResult<NotPoPaymentOrder> agreeWorkFlow(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 退回
     *
     * @param paymentOrder
     * @return
     */
    ExecuteResult<NotPoPaymentOrder> denyWorkFlow(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 弃审
     *
     * @param paymentOrder
     * @return
     */
    ExecuteResult<NotPoPaymentOrder> dropWorkFlow(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 根据成本中心编码 获得利润中心编码
     *
     * @param costCenterCode
     * @return
     */
    String searchProfitCenter(@Param("costCenterCode") String costCenterCode);

    /**
     * 根据付款单据号更新凭证号
     *
     * @return
     */
    ExecuteResult<NotPoPaymentOrder> updatePaymentOrderByDocument(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 导出付款单据
     *
     * @return
     */
    List<NotPoPaymentOrder> exportNotPoPaymentOrders(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 影像创建
     *
     * @return
     */
    ExecuteResult<NotPoPaymentOrder> saveBar(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 影像删除
     *
     * @return
     */
    ExecuteResult<NotPoPaymentOrder> deleteBar(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 影像退回
     *
     * @return
     */
    ExecuteResult<NotPoPaymentOrder> backBar(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 影像调阅
     *
     * @return
     */
    ExecuteResult<NotPoPaymentOrder> checkBar(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 创建单据时更新影像状态
     *
     * @param paymentOrder
     */
    void updatePaymentOrderByCdocument(NotPoPaymentOrder notPoPaymentOrder);

    void deleteNoPoPaymentOrder(NotPoPaymentOrder notPoPaymentOrder);

    //List<NotPoPaymentOrder> searchNotPoPaymentByOrderId(NotPoPaymentOrder notPoPaymentOrder);

    ExecuteResult<NotPoPaymentOrder> updateNotPoPaymentOrder(NotPoPaymentOrder notPoPaymentOrder,
                                                             List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails, List<PaymentPaymentMappingModel> paymentPaymentMappingModels);

    /**
     * 调用存贮过程更新资产主编吗
     *
     * @param notPoPaymentOrder
     * @return
     */
    void getMainAssetCodePro(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 根据付款单号更新是否生成资产编码
     *
     * @param notPoPaymentOrder
     * @return
     */
    ExecuteResult<NotPoPaymentOrder> updateIsGetCodeByDocument(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 根据当前登录人查询申请人为此人的所有已完成的验收单
     *
     * @return
     */
    List<NotPoPaymentOrder> searchPoPaymentOrders(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 根据付款单号查询剩余尾款金额
     *
     * @return
     */
    String searchSumPriceByCdocument(NotPoPaymentOrder notPoPaymentOrder);

    /**
     * 手工凭证存储凭证号
     *
     * @return
     */
    void updateCvoucherId(NotPoPaymentOrder notPoPaymentOrder);
}
