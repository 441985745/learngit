package com.opple.fa.config.export.controller;

import com.opple.fa.config.entity.BudgetAdjustment;
import com.opple.fa.excel.AbstractExcelExportTemplate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExportBudgetAdjustmentTemplate extends AbstractExcelExportTemplate<BudgetAdjustment> {

    @Override
    public String[] getSheetNames() {
        return new String[]{"预算调整数据"};
    }

    @Override
    public String[][] getTitles() {
        return new String[][]{{"单据号", "审批状态", "申请人", "申请日期", "预算调整类型", "当前审批人", "维护人", "维护时间"}};
    }

    @Override
    protected void buildBody(int sheetIndex, List<BudgetAdjustment> budgetAdjustments) {

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

        for (int i = 0; i < budgetAdjustments.size(); i++) {

            Row row = sheet.createRow(i + startIndex);
            row.setHeight((short) 300);
            int index = 0;
            if (!"".equals(budgetAdjustments.get(i).getDocument()) && null != budgetAdjustments.get(i).getDocument()) {
                createStyledCell(row, index++, budgetAdjustments.get(i).getDocument(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(budgetAdjustments.get(i).getApprovalState()) && null != budgetAdjustments.get(i).getApprovalState()) {
                createStyledCell(row, index++, budgetAdjustments.get(i).getApprovalState(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(budgetAdjustments.get(i).getCaUserName()) && null != budgetAdjustments.get(i).getUserName()) {
                createStyledCell(row, index++, budgetAdjustments.get(i).getUserName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(budgetAdjustments.get(i).getApplicationDate()) && null != budgetAdjustments.get(i).getApplicationDate()) {
                createStyledCell(row, index++, formatter.format(budgetAdjustments.get(i).getApplicationDate()), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(budgetAdjustments.get(i).getAssetsBudgetType()) && null != budgetAdjustments.get(i).getAssetsBudgetType()) {
                createStyledCell(row, index++, budgetAdjustments.get(i).getAssetsBudgetType(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(budgetAdjustments.get(i).getNextHandlerName()) && null != budgetAdjustments.get(i).getNextHandlerName()) {
                createStyledCell(row, index++, budgetAdjustments.get(i).getNextHandlerName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(budgetAdjustments.get(i).getUpdateUserName()) && null != budgetAdjustments.get(i).getUpdateUserName()) {
                createStyledCell(row, index++, budgetAdjustments.get(i).getUpdateUserName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(budgetAdjustments.get(i).getUpdateDate()) && null != budgetAdjustments.get(i).getUpdateDate()) {
                createStyledCell(row, index++, formatter.format(budgetAdjustments.get(i).getUpdateDate()), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
        }

    }

}
