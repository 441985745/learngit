package com.opple.fa.purchase.service;

import com.opple.fa.purchase.entity.InvoiceInspection;
import com.opple.fa.purchase.entity.InvoiceInspectionDetail;

import java.util.List;

public interface InvoiceInspectionDetailService {

    /**
     * 根据发票检验主表查询发票校验明细信息
     *
     * @param invoiceInspection
     * @return List<InvoiceInspectionDetail>
     */
    List<InvoiceInspectionDetail> searchInvoiceInspectionDetail(InvoiceInspection invoiceInspection);
}
