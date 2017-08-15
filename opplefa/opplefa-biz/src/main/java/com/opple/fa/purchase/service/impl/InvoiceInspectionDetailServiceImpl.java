package com.opple.fa.purchase.service.impl;

import com.opple.fa.purchase.dao.InvoiceInspectionDetailDAO;
import com.opple.fa.purchase.entity.InvoiceInspection;
import com.opple.fa.purchase.entity.InvoiceInspectionDetail;
import com.opple.fa.purchase.service.InvoiceInspectionDetailService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class InvoiceInspectionDetailServiceImpl implements
        InvoiceInspectionDetailService {
    @Getter
    @Setter
    private InvoiceInspectionDetailDAO invoiceInspectionDetailDAO;

    @Override
    public List<InvoiceInspectionDetail> searchInvoiceInspectionDetail(
            InvoiceInspection invoiceInspection) {
        return invoiceInspectionDetailDAO.searchInvoiceInspectionDetail(invoiceInspection);
    }

}
