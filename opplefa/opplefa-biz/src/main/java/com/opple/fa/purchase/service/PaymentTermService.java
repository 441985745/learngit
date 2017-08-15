package com.opple.fa.purchase.service;

import java.util.List;

import com.opple.fa.purchase.entity.PaymentTerm;
import com.opple.fa.purchase.entity.PurchaseOrder;


public interface PaymentTermService {

	/**
	 * 根据订单ID查询付款方式
	 * @param purchaseOrder
	 * @return
	 */
	List<PaymentTerm> searchPaymentTermByOrderId(PurchaseOrder purchaseOrder);
	/**
	 * 根据订单编号 及 付款期数 获得当前付款比例、尾款付款比例总和
	 * @param paymentTerm
	 * @return
	 */
	List<PaymentTerm> searchPaymentRatioByOrderId(PaymentTerm paymentTerm);
	/**
	 * 根据采购订单号查询最后一期期数
	 * @param purchaseOrder
	 * @return
	 */
	PaymentTerm searchMaxTermByOrderId(PurchaseOrder purchaseOrder);
}
