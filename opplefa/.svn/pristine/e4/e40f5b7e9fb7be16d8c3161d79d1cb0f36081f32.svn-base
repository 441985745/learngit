package com.opple.fa.purchase.service.impl;

import com.opple.fa.purchase.dao.PaymentOrderDetailDAO;
import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PaymentOrderDetail;
import com.opple.fa.purchase.entity.PaymentTerm;
import com.opple.fa.purchase.model.PaymentOrderDetailModel;
import com.opple.fa.purchase.service.PaymentOrderDetailService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PaymentOrderDetailServiceImpl implements PaymentOrderDetailService {
    @Getter
    @Setter
    private PaymentOrderDetailDAO paymentOrderDetailDAO;

    @Override
    public List<PaymentOrderDetailModel> searchPurchaseByOrderId(PaymentTerm paymentTerm) {
        return paymentOrderDetailDAO.searchPurchaseByOrderId(paymentTerm);
    }

    @Override
    public int searchNextSequence() {
        return paymentOrderDetailDAO.searchNextSequence();
    }

    @Override
    public List<PaymentOrderDetailModel> searchPurchaseByOrderIdNper(PaymentOrderDetailModel paymentOrderDetailModel) {
        return paymentOrderDetailDAO.searchPurchaseByOrderIdNper(paymentOrderDetailModel);
    }

	/*@Override
    public List<PaymentOrderDetailModel> searchPaymentOrderDetailByOrderIdNper(PaymentOrderDetailModel paymentOrderDetailModel) {
		return paymentOrderDetailDAO.searchPaymentOrderDetailByOrderIdNper(paymentOrderDetailModel);
	}*/

    @Override
    public void save(PaymentOrderDetail paymentOrderDetail) {
        paymentOrderDetailDAO.save(paymentOrderDetail);
    }

    @Override
    public List<PaymentOrderDetailModel> searchPaymentDetailByOrderId(PaymentOrder paymentOrder) {
        return paymentOrderDetailDAO.searchPaymentDetailByOrderId(paymentOrder);
    }

    @Override
    public String searchLedgerAccountByPurchaseDetailId(Long id) {
        return paymentOrderDetailDAO.searchLedgerAccountByPurchaseDetailId(id);
    }

    @Override
    public void updatePaymentOrderDetail(
            PaymentOrder paymentOrder) {
        paymentOrderDetailDAO.updatePaymentOrderDetail(paymentOrder);

    }

    @Override
    public List<PaymentOrderDetailModel> searchPurchaseForUpdate(
            PaymentOrderDetailModel paymentOrderDetailModel) {
        return paymentOrderDetailDAO.searchPurchaseForUpdate(paymentOrderDetailModel);
    }

    @Override
    public Long completePayment(PaymentOrder paymentOrder) {
        return paymentOrderDetailDAO.completePayment(paymentOrder);
    }

}
