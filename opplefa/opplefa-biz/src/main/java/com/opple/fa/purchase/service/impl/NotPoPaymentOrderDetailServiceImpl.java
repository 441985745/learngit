package com.opple.fa.purchase.service.impl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.purchase.dao.NotPoPaymentOrderDetailDAO;
import com.opple.fa.purchase.entity.NotPoPaymentOrderDetail;
import com.opple.fa.purchase.service.NotPoPaymentOrderDetailService;
import com.opple.util.ExecuteResult;

public class NotPoPaymentOrderDetailServiceImpl implements
		NotPoPaymentOrderDetailService {
	@Getter @Setter
	private NotPoPaymentOrderDetailDAO notPoPaymentOrderDetailDAO;
	@Override
	public List<NotPoPaymentOrderDetail> searchPaymentOrderByDocument(
			NotPoPaymentOrderDetail notPoPaymentOrderDetail) {
		return notPoPaymentOrderDetailDAO.searchPaymentOrderByDocument(notPoPaymentOrderDetail);
	}
	@Override
	public ExecuteResult<NotPoPaymentOrderDetail> updatePaymentOrderDetailById(
			NotPoPaymentOrderDetail notPoPaymentOrderDetail) {
		ExecuteResult<NotPoPaymentOrderDetail> result = new ExecuteResult<NotPoPaymentOrderDetail>();
		notPoPaymentOrderDetailDAO.updateAssetsCodeById(notPoPaymentOrderDetail);
		return result;
	}

}
