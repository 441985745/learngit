package com.opple.fa.purchase.service;

import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PaymentOrderDetail;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.purchase.model.PaymentPaymentMappingModel;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PaymentOrderService {

    /**
     * 分页查询--采购订单--list
     *
     * @param pager
     * @param paymentOrderModel
     * @return
     */
    Pager<PaymentOrderModel> searchPaymentOrder(Pager<PaymentOrderModel> pager, PaymentOrderModel paymentOrderModel);

    List<PaymentOrderModel> searchPaymentOrderList(PaymentOrderModel paymentOrderModel);

    /**
     * 根据订单号查询订单
     *
     * @param paymentOrder
     * @return
     */
    PaymentOrderModel searchPaymentOrderByOrderId(PaymentOrder paymentOrder);

    /**
     * 查询下个序列值
     *
     * @return
     */
    int searchNextSequence();

    /**
     * 保存付款单表头信息
     *
     * @param paymentOrder
     */
    ExecuteResult<PaymentOrder> savePayment(PaymentOrder paymentOrder);

    /**
     * 新增付款单据，绑定审批流
     *
     * @param paymentOrder
     */
    ExecuteResult<PaymentOrder> addSavePaymentOrder(PaymentOrder paymentOrder, List<PaymentOrderDetail> paymentOrderDetails, List<PaymentPaymentMappingModel> paymentPaymentMappingModels);

    /**
     * 修改付款单据，绑定审批流
     *
     * @param paymentOrder
     */
    ExecuteResult<PaymentOrder> updateSavePaymentOrder(PaymentOrder paymentOrder, List<PaymentOrderDetail> paymentOrderDetails, List<PaymentPaymentMappingModel> paymentPaymentMappingModels);

    /**
     * 删除
     *
     * @param paymentOrder
     */
    void delPaymentOrderByCdocument(PaymentOrder paymentOrder);

    /**
     * 同意
     *
     * @param paymentOrder
     * @return
     */
    ExecuteResult<PaymentOrder> agreeWorkFlow(PaymentOrder paymentOrder);

    /**
     * 退回
     *
     * @param paymentOrder
     * @return
     */
    ExecuteResult<PaymentOrder> denyWorkFlow(PaymentOrder paymentOrder);

    /**
     * 弃审
     *
     * @param paymentOrder
     * @return
     */
    ExecuteResult<PaymentOrder> dropWorkFlow(PaymentOrder paymentOrder);

    /**
     * 根据成本中心编码 获得利润中心编码
     *
     * @param costCenterCode
     * @return
     */
    String searchProfitCenter(@Param("costCenterCode") String costCenterCode);

    /**
     * 修改付款单表头信息
     *
     * @param paymentOrder
     */
    ExecuteResult<PaymentOrder> updatePaymentOrder(PaymentOrder paymentOrder);

    /**
     * 导出付款单据
     *
     * @return
     */
    List<PaymentOrderModel> exportPaymentOrders(PaymentOrderModel paymentOrderModel);

    /**
     * 影像创建
     *
     * @return
     */
    ExecuteResult<PaymentOrder> saveBar(PaymentOrder paymentOrder);

    /**
     * 影像删除
     *
     * @return
     */
    ExecuteResult<PaymentOrder> deleteBar(PaymentOrder paymentOrder);

    /**
     * 影像退回
     *
     * @return
     */
    ExecuteResult<PaymentOrder> backBar(PaymentOrder paymentOrder);

    /**
     * 影像调阅
     *
     * @return
     */
    ExecuteResult<PaymentOrder> checkBar(PaymentOrder paymentOrder);

    /**
     * 创建单据时更新影像状态
     *
     * @param paymentOrder
     */
    void updatePaymentOrderByCdocument(PaymentOrder paymentOrder);
}
