package com.opple.fa.purchase.service.impl;

import com.opple.fa.purchase.dao.PaymentOrderDAO;
import com.opple.fa.purchase.dao.PaymentPaymentMappingDAO;
import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PaymentPaymentMapping;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.purchase.model.PaymentPaymentMappingModel;
import com.opple.fa.purchase.service.PaymentPaymentMappingService;

import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.List;

public class PaymentPaymentMappingServiceImpl implements PaymentPaymentMappingService {
    @Getter
    @Setter
    private PaymentPaymentMappingDAO paymentPaymentMappingDAO;
    @Getter
    @Setter
    private PaymentOrderDAO paymentOrderDAO;


    @Override
    public int searchNextSequence() {
        return paymentPaymentMappingDAO.searchNextSequence();
    }


    @Override
    public List<PaymentPaymentMapping> searchPaymentPaymentMappingByOrderId(
            PaymentOrderModel paymentOrderModel) {
        List<PaymentOrder> paymentOrders = paymentOrderDAO.searchPaymentOrdersByPurchaseId(paymentOrderModel);
        String list = "";
        for (PaymentOrder paymentOrder : paymentOrders) {
            list = list + "'" + paymentOrder.getCdocument() + "'" + ",";
        }
        if (list.length() != 0) {
            list = list.substring(0, list.length() - 1);
        } else {
            list = "''";
        }
        paymentOrderModel.setList(list);
        List<PaymentPaymentMapping> paymentPaymentMappings = paymentPaymentMappingDAO.searchPaymentPaymentMappingByOrderId(paymentOrderModel);
        if (0 != paymentPaymentMappings.size()) {
            Iterator<PaymentPaymentMapping> stuIter = paymentPaymentMappings.iterator();
            while (stuIter.hasNext()) {
                PaymentPaymentMapping pc1 = stuIter.next();
                long count = paymentPaymentMappingDAO.getCount(pc1);
                if (1 == count) {
                    stuIter.remove();
                }
            }
        }
        return paymentPaymentMappings;
    }


    @Override
    public List<PaymentPaymentMapping> searchPaymentPaymentMappingById(
            PaymentOrder paymentOrder) {
        return paymentPaymentMappingDAO.searchPaymentPaymentMappingById(paymentOrder);
    }


    @Override
    public Long getCount(PaymentPaymentMapping paymentPaymentMapping) {
        return paymentPaymentMappingDAO.getCount(paymentPaymentMapping);
    }


    @Override
    public PaymentPaymentMapping getPaymentPaymentMapping(
            PaymentPaymentMapping paymentPaymentMapping) {
        return paymentPaymentMappingDAO.getPaymentPaymentMapping(paymentPaymentMapping);
    }


    @Override
    public void deletePaymentPaymentMapping(PaymentOrder paymentOrder) {
        paymentPaymentMappingDAO.delete(paymentOrder);
    }


    @Override
    public List<PaymentPaymentMapping> searchPaymentPaymentMappingByCode(
            String supplierCode, String currencyCode) {
        List<PaymentPaymentMapping> paymentPaymentMappings = paymentPaymentMappingDAO.searchPaymentPaymentMappingByCode(supplierCode, currencyCode);
        if (0 != paymentPaymentMappings.size()) {
            Iterator<PaymentPaymentMapping> stuIter = paymentPaymentMappings.iterator();
            while (stuIter.hasNext()) {
                PaymentPaymentMapping pc1 = stuIter.next();
                long count = paymentPaymentMappingDAO.getCount(pc1);
                if (1 == count) {
                    stuIter.remove();
                }
            }
        }
        return paymentPaymentMappings;
    }


    @Override
    public List<PaymentPaymentMappingModel> searchPaymentPaymentMappingForNotPo(
            PaymentOrder paymentOrder) {
        return paymentPaymentMappingDAO.searchPaymentPaymentMappingForNotPo(paymentOrder);
    }

}
