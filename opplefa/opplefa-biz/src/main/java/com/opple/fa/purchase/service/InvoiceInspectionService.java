package com.opple.fa.purchase.service;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.InvoiceInspection;
import com.opple.util.ExecuteResult;

public interface InvoiceInspectionService {
    /**
     * 保存发票检验信息
     *
     * @param invoiceInspection
     * @return
     */
    ExecuteResult<InvoiceInspection> saveInvoiceInspection(InvoiceInspection invoiceInspection, String ids);

    /**
     * 根据付款单号查询单条发票校验信息
     *
     * @param invoiceInspection
     * @return InvoiceInspection
     */
    InvoiceInspection getInvoiceInspection(@Param("invoiceInspection") InvoiceInspection invoiceInspection);

    /**
     * 保存发票检验明细信息
     *
     * @param invoiceInspectionDetail
     * @param ids
     * @return
     */
    void saveInvoiceInspectionDetail(Long id, String ids);
}
