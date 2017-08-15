package com.opple.fa.security.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.opple.fa.excel.AbstractExcelExportTemplate;
import com.opple.fa.purchase.entity.PurchaseOrder;

public class ExportPurchaseTemplate extends AbstractExcelExportTemplate<PurchaseOrder> {

	@Override
	public String[] getSheetNames() {
		return new String[] {"采购申请" };
	}

	@Override
	public String[][] getTitles() {
		return new String[][] {{"单据号", "采购订单号(SAP)", "采购员", "申请日期", "供应商", "审批状态", "订单状态", "是否草稿", "是否打印", "公司", "付款状态", "下级处理人" } };
	}

	@Override
	protected void buildBody(int sheetIndex, List<PurchaseOrder> ts) {
		Date date = new Date();
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
		for (int i = 0; i < ts.size(); i++) {
			Row row = sheet.createRow(i + startIndex);
			row.setHeight((short) 300);
			int index = 0;

			if (!"".equals(ts.get(i).getCdocument()) && null != ts.get(i).getCdocument()) {
				createStyledCell(row, index++, ts.get(i).getCdocument(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			if (!"".equals(ts.get(i).getPurchaseOrderSapId()) && null != ts.get(i).getPurchaseOrderSapId()) {
				createStyledCell(row, index++, ts.get(i).getPurchaseOrderSapId(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			if (!"".equals(ts.get(i).getBuyerName()) && null != ts.get(i).getBuyerName()) {
				createStyledCell(row, index++, ts.get(i).getBuyerName(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			if (!"".equals(ts.get(i).getCreateDate()) && null != ts.get(i).getCreateDate()) {
				createStyledCell(row, index++, formatter.format(ts.get(i).getCreateDate()), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			if (!"".equals(ts.get(i).getSupplierName()) && null != ts.get(i).getSupplierName()) {
				createStyledCell(row, index++, ts.get(i).getSupplierName(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			if (!"".equals(ts.get(i).getCapprovalstate()) && null != ts.get(i).getCapprovalstate()) {
				createStyledCell(row, index++, ts.get(i).getCapprovalstate(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			if (!"".equals(ts.get(i).getOrderStatus()) && null != ts.get(i).getOrderStatus()) {
				createStyledCell(row, index++, ts.get(i).getOrderStatus(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			if (!"".equals(ts.get(i).getDraft()) && null != ts.get(i).getDraft()) {
				createStyledCell(row, index++, "N".equals(ts.get(i).getDraft()) ? "否" : "是", getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			if (!"".equals(ts.get(i).getPrint()) && null != ts.get(i).getPrint()) {
				createStyledCell(row, index++, "N".equals(ts.get(i).getPrint()) ? "否" : "是", getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}

			if (!"".equals(ts.get(i).getCompanyName()) && null != ts.get(i).getCompanyName()) {
				createStyledCell(row, index++, ts.get(i).getCompanyName(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			if (!"".equals(ts.get(i).getIsCloseForPayment()) && null != ts.get(i).getIsCloseForPayment()) {
				createStyledCell(row, index++, ts.get(i).getIsCloseForPayment(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			if (!"".equals(ts.get(i).getCnexthandlername()) && null != ts.get(i).getCnexthandlername()) {
				createStyledCell(row, index++, ts.get(i).getCnexthandlername(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
		}

	}

}
