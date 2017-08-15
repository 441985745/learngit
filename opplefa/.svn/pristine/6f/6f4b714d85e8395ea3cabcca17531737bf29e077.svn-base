package com.opple.fa.purchase.export.controller;

import com.opple.fa.excel.AbstractExcelExportTemplate;
import com.opple.fa.purchase.model.PaymentOrderModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExportPaymentOrderTemplate extends
        AbstractExcelExportTemplate<PaymentOrderModel> {

    @Override
    public String[] getSheetNames() {

        return new String[]{"付款单据"};
    }

    @Override
    public String[][] getTitles() {

        return new String[][]{{"付款单据号", "采购订单号", "采购员", "所属公司", "供应商", "订单号（sap）", "付款类别",
                "付款金额", "申请人", "申请日期", "审批状态", "下级处理人", "影像状态", "是否为草稿", "是否打印"}};
    }


    @Override
    protected void buildBody(int sheetIndex, List<PaymentOrderModel> ts) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Sheet sheet = getSheet(sheetIndex);
        int startIndex = this.getBodyStartIndex(sheetIndex);

        sheet.setDefaultColumnStyle(0, getStringCellStyle());
        sheet.setDefaultColumnStyle(1, getStringCellStyle());
        sheet.setDefaultColumnStyle(2, getStringCellStyle());
        sheet.setDefaultColumnStyle(3, getStringCellStyle());
        sheet.setDefaultColumnStyle(4, getStringCellStyle());
        sheet.setDefaultColumnStyle(5, getStringCellStyle());
        sheet.setDefaultColumnStyle(6, getStringCellStyle());
        sheet.setDefaultColumnStyle(7, getStringCellStyle());
        sheet.setDefaultColumnStyle(8, getStringCellStyle());
        sheet.setDefaultColumnStyle(9, getStringCellStyle());
        sheet.setDefaultColumnStyle(10, getStringCellStyle());
        sheet.setDefaultColumnStyle(11, getStringCellStyle());
        sheet.setDefaultColumnStyle(12, getStringCellStyle());
        sheet.setDefaultColumnStyle(13, getStringCellStyle());
        sheet.setDefaultColumnStyle(14, getStringCellStyle());

        for (int i = 0; i < ts.size(); i++) {

            Row row = sheet.createRow(i + startIndex);
            row.setHeight((short) 300);
            int index = 0;
            if (!"".equals(ts.get(i).getCdocument()) && null != ts.get(i).getCdocument()) {
                createStyledCell(row, index++, ts.get(i).getCdocument(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getPurchasecdocument()) && null != ts.get(i).getPurchasecdocument()) {

                createStyledCell(row, index++, ts.get(i).getPurchasecdocument(), getStringCellStyle());

            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getBuyerName()) && null != ts.get(i).getBuyerName()) {

                createStyledCell(row, index++, ts.get(i).getBuyerName(), getStringCellStyle());

            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCompanyName()) && null != ts.get(i).getCompanyName()) {

                createStyledCell(row, index++, ts.get(i).getCompanyName(), getStringCellStyle());

            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getSupplierName()) && null != ts.get(i).getSupplierName()) {
                createStyledCell(row, index++, ts.get(i).getSupplierName(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getSapPurchaseOrderNumber()) && null != ts.get(i).getSapPurchaseOrderNumber()) {
                createStyledCell(row, index++, ts.get(i).getSapPurchaseOrderNumber(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getPaymentType()) && null != ts.get(i).getPaymentType()) {
                createStyledCell(row, index++, ts.get(i).getPaymentType(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getOrderMoney()) && null != ts.get(i).getOrderMoney()) {
                createStyledCell(row, index++, String.valueOf(ts.get(i).getOrderMoney()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getApplyUser()) && null != ts.get(i).getApplyUser()) {
                createStyledCell(row, index++, ts.get(i).getApplyUser(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getApplyDate()) && null != ts.get(i).getApplyDate()) {
                createStyledCell(row, index++, formatter.format(ts.get(i).getApplyDate()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCapprovalstate()) && null != ts.get(i).getCapprovalstate()) {

                createStyledCell(row, index++, ts.get(i).getCapprovalstate(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCnexthandlername()) && null != ts.get(i).getCnexthandlername()) {

                createStyledCell(row, index++, ts.get(i).getCnexthandlername(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCbarStatus()) && null != ts.get(i).getCbarStatus()) {

                createStyledCell(row, index++, ts.get(i).getCbarStatus(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }


            if (!"".equals(ts.get(i).getDraft()) && null != ts.get(i).getDraft()) {
                createStyledCell(row, index++, "N".equals(ts.get(i).getDraft()) ? "否" : "是",
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }


            if (!"".equals(ts.get(i).getPrint()) && null != ts.get(i).getPrint()) {
                createStyledCell(row, index++, "N".equals(ts.get(i).getPrint()) ? "否" : "是",
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
        }


    }
}
