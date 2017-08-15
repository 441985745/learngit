package com.opple.fa.purchase.service;

import java.util.List;

import com.opple.fa.purchase.entity.NotPoPaymentOrderDetail;

import com.opple.util.ExecuteResult;

public interface NotPoPaymentOrderDetailService {

	/**
	 * 根据非标流程主表单据号查询付款明细信息
	 * @return 
	 */
	List<NotPoPaymentOrderDetail> searchPaymentOrderByDocument(NotPoPaymentOrderDetail notPoPaymentOrderDetail);
	/**
	 * 根据非标流程明细表ID更新资产编码
	 * @return 
	 */
	ExecuteResult<NotPoPaymentOrderDetail> updatePaymentOrderDetailById(NotPoPaymentOrderDetail notPoPaymentOrderDetail);
}
