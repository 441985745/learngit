package com.opple.fa.purchase.service;

import java.util.List;

import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PaymentPaymentMapping;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.purchase.model.PaymentPaymentMappingModel;

public interface PaymentPaymentMappingService {


    /**
     * 付款新增页面  预付冲销明细(根据采购订单单号)
     *
     * @param paymentOrderModel
     * @return
     */
    List<PaymentPaymentMapping> searchPaymentPaymentMappingByOrderId(PaymentOrderModel paymentOrderModel);

    /**
     * 付款新增页面  预付冲销明细(根非标流程)
     *
     * @param paymentOrderModel
     * @return
     */
    List<PaymentPaymentMappingModel> searchPaymentPaymentMappingForNotPo(PaymentOrder paymentOrder);

    /**
     * 付款查看页面  预付冲销明细 (根据付款单单号)
     *
     * @param paymentOrder
     * @return
     */
    List<PaymentPaymentMapping> searchPaymentPaymentMappingById(PaymentOrder paymentOrder);

    /**
     * 查询下个序列值
     *
     * @return
     */
    int searchNextSequence();

    /**
     * 根据凭证号，年度，公司代码查询是否存在
     *
     * @param paymentPaymentMapping
     * @return Long
     */
    Long getCount(PaymentPaymentMapping paymentPaymentMapping);

    /**
     * 根据凭证号，年度，公司代码查询单条冲销信息
     *
     * @param paymentPaymentMapping
     * @return Long
     */
    PaymentPaymentMapping getPaymentPaymentMapping(PaymentPaymentMapping paymentPaymentMapping);

    /**
     * 删除预付冲减明细
     *
     * @param paymentPaymentMapping
     */
    void deletePaymentPaymentMapping(PaymentOrder paymentOrder);

    /**
     * 付款新增页面  预付冲销明细(根据供应商编码)
     *
     * @param supplierCode
     * @return
     */
    List<PaymentPaymentMapping> searchPaymentPaymentMappingByCode(String supplierCode, String currencyCode);
}
