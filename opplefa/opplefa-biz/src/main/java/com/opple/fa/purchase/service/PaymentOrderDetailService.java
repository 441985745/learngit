package com.opple.fa.purchase.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PaymentOrderDetail;
import com.opple.fa.purchase.entity.PaymentTerm;
import com.opple.fa.purchase.model.PaymentOrderDetailModel;


public interface PaymentOrderDetailService {

    /**
     * 根据订单ID查询订单
     * @param paymentOrder
     * @return
     */
    /*List<PaymentOrderDetailModel> searchPaymentOrderDetailByOrderId(PaymentOrder paymentOrder);*/

    /**
     * 根据采购订单号和付款期数查询明细行
     *
     * @param paymentTerm
     * @return
     */
    List<PaymentOrderDetailModel> searchPurchaseByOrderId(PaymentTerm paymentTerm);

    /**
     * 根据采购订单号和付款期数查询明细行 付款新增页面
     *
     * @param paymentTerm
     * @return
     */
    List<PaymentOrderDetailModel> searchPurchaseByOrderIdNper(PaymentOrderDetailModel paymentOrderDetailModel);

    /**
     * 根据采购订单号查询明细行 付款查看页面
     *
     * @param paymentOrder
     * @return
     */
    List<PaymentOrderDetailModel> searchPaymentDetailByOrderId(PaymentOrder paymentOrder);

    /**
     * 付款新增页面  预付冲销明细
     *
     * @param paymentOrderDetail
     * @return
     */
    /*List<PaymentOrderDetailModel> searchPaymentOrderDetailByOrderIdNper(PaymentOrderDetailModel paymentOrderDetailModel);*/

    void save(PaymentOrderDetail paymentOrderDetail);

    /**
     * 查询下个序列值
     *
     * @return
     */
    int searchNextSequence();

    /**
     * 删除
     * @param paymentOrder
     */
    /*void deletePaymentOrderDetailByPaymentId(PaymentOrder paymentOrder);*/

    /**
     * 修改付款单明细（将付款单生成预付凭证号写入明细表）
     *
     * @param paymentOrderDetailModel
     */
    void updatePaymentOrderDetail(PaymentOrder paymentOrder);

    /**
     * 根据采购订单明细id获得对应的申请单中的总账科目
     *
     * @param id
     * @return
     */
    String searchLedgerAccountByPurchaseDetailId(@Param("id") Long id);

    /**
     * 根据采购订单号和付款期数和付款单号查询明细行 付款修改页面
     *
     * @param paymentOrderDetailModel
     * @return
     */
    List<PaymentOrderDetailModel> searchPurchaseForUpdate(PaymentOrderDetailModel paymentOrderDetailModel);

    /**
     * 根据采购订单单号与最后一期期数查询是否所有明细都已付款完结
     *
     * @param paymentOrder
     * @return
     */
    Long completePayment(PaymentOrder paymentOrder);
}
