package com.opple.fa.purchase.service.impl;

import java.util.List;

import com.opple.fa.purchase.dao.PaymentTermDAO;
import com.opple.fa.purchase.entity.PaymentTerm;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.service.PaymentTermService;

import lombok.Getter;
import lombok.Setter;

public class PaymentTermServiceImpl implements PaymentTermService {

	@Getter @Setter
	private PaymentTermDAO paymentTermDAO;
	
	@Override
	public List<PaymentTerm> searchPaymentTermByOrderId(PurchaseOrder purchaseOrder) {
		return paymentTermDAO.searchPaymentTermByOrderId(purchaseOrder);
	}

	@Override
	public List<PaymentTerm> searchPaymentRatioByOrderId(PaymentTerm paymentTerm) {
		return paymentTermDAO.searchPaymentRatioByOrderId(paymentTerm);
	}

	@Override
	public PaymentTerm searchMaxTermByOrderId(PurchaseOrder purchaseOrder) {
		return paymentTermDAO.searchMaxTermByOrderId(purchaseOrder);
	}

}
