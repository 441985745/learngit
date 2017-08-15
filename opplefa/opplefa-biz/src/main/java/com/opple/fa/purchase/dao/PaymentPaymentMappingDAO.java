package com.opple.fa.purchase.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PaymentPaymentMapping;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.purchase.model.PaymentPaymentMappingModel;

public interface PaymentPaymentMappingDAO {


    /**
     * 付款新增页面  预付冲销明细(根据采购订单单号)
     *
     * @param paymentOrderDetail
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
     * 新增冲销信息
     *
     * @param paymentPaymentMapping
     * @return
     */
    void save(PaymentPaymentMapping paymentPaymentMapping);

    /**
     * 删除冲销信息
     *
     * @param paymentPaymentMapping
     * @return
     */
    void delete(PaymentOrder paymentOrder);

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
     * 付款新增页面  预付冲销明细(根据供应商编码)
     *
     * @param supplierCode
     * @return
     */
    List<PaymentPaymentMapping> searchPaymentPaymentMappingByCode(@Param("supplierCode") String supplierCode, @Param("currencyCode") String currencyCode);
}
