package com.opple.fa.security.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.opple.fa.excel.AbstractExcelExportTemplate;
import com.opple.fa.purchase.entity.DemandOrder;

public class ExportDemandTemplate extends AbstractExcelExportTemplate<DemandOrder> {

    @Override
    public String[] getSheetNames() {
        return new String[]{"采购需求"};
    }

    @Override
    public String[][] getTitles() {
        return new String[][]{
                {"单据号", "资产类型", "申请人", "申请部门", "申请日期", "申请预算金额", "审批状态", "订单状态", "是否草稿", "是否打印", "创建人", "更新人", "归口管理员"}};
    }

    @Override
    protected void buildBody(int sheetIndex, List<DemandOrder> ts) {

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

        for (int i = 0; i < ts.size(); i++) {
            Row row = sheet.createRow(i + startIndex);
            row.setHeight((short) 300);
            int index = 0;
            if (!"".equals(ts.get(i).getCdocument()) && null != ts.get(i).getCdocument()) {
                createStyledCell(row, index++, ts.get(i).getCdocument(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getAssetType()) && null != ts.get(i).getAssetType()) {
                createStyledCell(row, index++, ts.get(i).getAssetType(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getApplyUser()) && null != ts.get(i).getApplyUser()) {
                createStyledCell(row, index++, ts.get(i).getCreateBy(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getBudgetDepartmentName()) && null != ts.get(i).getBudgetDepartmentName()) {
                createStyledCell(row, index++, ts.get(i).getBudgetDepartmentName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getApplyDate()) && null != ts.get(i).getApplyDate()) {
                createStyledCell(row, index++, formatter.format(ts.get(i).getApplyDate()), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getApplyBudgetMoney()) && null != ts.get(i).getApplyBudgetMoney()) {
                createStyledCell(row, index++, ts.get(i).getApplyBudgetMoney().toString(), getStringCellStyle());
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
            if (!"".equals(ts.get(i).getCreateBy()) && null != ts.get(i).getCreateBy()) {
                createStyledCell(row, index++, ts.get(i).getCreateBy(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getUpdateBy()) && null != ts.get(i).getUpdateBy()) {
                createStyledCell(row, index++, ts.get(i).getUpdateBy(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getAttachDepartAdminCode()) && null != ts.get(i).getAttachDepartAdminCode()) {
                createStyledCell(row, index++, "(" + ts.get(i).getAttachDepartAdminCode() + ")" + ts.get(i).getAttachDepartAdminName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
        }

    }

}
