package com.opple.fa.purchase.service.impl;

import com.opple.fa.purchase.dao.CheckAcceptanceDetailDAO;
import com.opple.fa.purchase.dao.InvoiceInspectionDAO;
import com.opple.fa.purchase.dao.InvoiceInspectionDetailDAO;
import com.opple.fa.purchase.dao.PurchaseCommonDAO;
import com.opple.fa.purchase.entity.CheckAcceptanceDetail;
import com.opple.fa.purchase.entity.InvoiceInspection;
import com.opple.fa.purchase.entity.InvoiceInspectionDetail;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.service.InvoiceInspectionService;
import com.opple.util.ExecuteResult;
import lombok.Getter;
import lombok.Setter;

public class InvoiceInspectionServiceImpl implements InvoiceInspectionService {
    @Getter
    @Setter
    private InvoiceInspectionDAO invoiceInspectionDAO;
    @Getter
    @Setter
    private PurchaseCommonDAO purchaseCommonDAO;
    @Getter
    @Setter
    private CheckAcceptanceDetailDAO checkAcceptanceDetailDAO;
    @Getter
    @Setter
    private InvoiceInspectionDetailDAO invoiceInspectionDetailDAO;

    @Override
    public ExecuteResult<InvoiceInspection> saveInvoiceInspection(
            InvoiceInspection invoiceInspection, String ids) {
        ExecuteResult<InvoiceInspection> result = new ExecuteResult<InvoiceInspection>();
        invoiceInspectionDAO.saveInvoiceInspection(invoiceInspection);
        Long id = invoiceInspection.getId();
        saveInvoiceInspectionDetail(id, ids);
        return result;
    }

    @Override
    public InvoiceInspection getInvoiceInspection(
            InvoiceInspection invoiceInspection) {
        return invoiceInspectionDAO.getInvoiceInspection(invoiceInspection);
    }

    @Override
    public void saveInvoiceInspectionDetail(Long id, String ids) {
        String[] splitsublist = ids.split(",");
        InvoiceInspectionDetail invoiceInspectionDetail = new InvoiceInspectionDetail();
        for (int i = 0; i < splitsublist.length; i++) {
            Long commonId = Long.parseLong(splitsublist[i]);
            invoiceInspectionDetail.setInvoiceInspectionId(String.valueOf(id));
            PurchaseCommon purchaseCommon = purchaseCommonDAO.getPurchaseCommonById(commonId);
            invoiceInspectionDetail.setSapMainCode(purchaseCommon.getSapMainAssetCode());
            invoiceInspectionDetail.setSapSecCode(purchaseCommon.getSapSecAssetCode());
            CheckAcceptanceDetail checkAcceptanceDetail = checkAcceptanceDetailDAO.getCheckAcceptanceDetailById(Long.parseLong(purchaseCommon.getCheckAcceptDetailId()));
            invoiceInspectionDetail.seteBelnr(checkAcceptanceDetail.getEpBelnr());
            invoiceInspectionDetail.setEpMatDocumentYear(checkAcceptanceDetail.getEpMatdocumentyear());
            invoiceInspectionDetail.setEpMaterialDocument(checkAcceptanceDetail.getEpMaterialdocu());
            invoiceInspectionDetailDAO.saveInvoiceInspectionDetail(invoiceInspectionDetail);
        }
    }

}
