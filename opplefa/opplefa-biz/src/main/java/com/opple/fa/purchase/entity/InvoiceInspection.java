package com.opple.fa.purchase.entity;

import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class InvoiceInspection extends WorkFlowCommon {

    /**
     *
     */
    private static final long serialVersionUID = -8512431205169857126L;
    //删除列标识（0可用1删除）
    private String deletedFlag;
    //付款单据主键
    private String paymentOrderDocument;
    //发票日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    //记账日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date accountingDate;
    //发票金额
    private String invoiceMoney;
    //发票税额
    private String invoiceTax;
    //发票净价
    private String netPrice;
    //发票文本（发票号）
    private String invoiceText;
    //是否手工凭证
    private String isManualCertificate;
    //采购订单号
    private String purchaseOrderNumber;
    //SAP采购订单号
    private String sapPurchaseOrderNumber;
    //是否传入SAP
    private String isComingSAP;
}
