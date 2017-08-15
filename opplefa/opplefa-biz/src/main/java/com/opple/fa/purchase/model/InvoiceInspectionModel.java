package com.opple.fa.purchase.model;

import com.opple.fa.purchase.entity.InvoiceInspection;
import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PurchaseCommon;
import lombok.Data;

@Data
public class InvoiceInspectionModel {

    private PaymentOrder paymentOrder;
    private InvoiceInspection invoiceInspection;
    private PurchaseCommon purchaseCommon;
    private String checkids;
}
