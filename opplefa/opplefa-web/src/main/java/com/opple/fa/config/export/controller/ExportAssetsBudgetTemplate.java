package com.opple.fa.config.export.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import com.opple.fa.config.model.STATUS;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.excel.AbstractExcelExportTemplate;

public class ExportAssetsBudgetTemplate extends
        AbstractExcelExportTemplate<AssetsBudget> {

    @Override
    public String[] getSheetNames() {
        // TODO Auto-generated method stub
        return new String[]{"预算数据"};
    }

    @Override
    public String[][] getTitles() {
        // TODO Auto-generated method stub
        return new String[][]{{"年度", "成本中心名称", "成本中心号", "平台", "项目编码", "项目名称", "资产名称", "资产类型", "类型", "单位",
                 "预算数量", "预算单价", "已使用金额", "可用金额", "验收金额", "年度预算总额", "是否确认", "维护人", "维护时间"}};
    }


    @Override
    protected void buildBody(int sheetIndex, List<AssetsBudget> ts) {

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
        sheet.setDefaultColumnStyle(15, getStringCellStyle());
        sheet.setDefaultColumnStyle(16, getStringCellStyle());
        sheet.setDefaultColumnStyle(17, getStringCellStyle());
        sheet.setDefaultColumnStyle(18, getStringCellStyle());

        for (int i = 0; i < ts.size(); i++) {

            Row row = sheet.createRow(i + startIndex);
            row.setHeight((short) 300);
            int index = 0;
            if (!"".equals(ts.get(i).getBudgetYear()) && null != ts.get(i).getBudgetYear()) {
                createStyledCell(row, index++, ts.get(i).getBudgetYear(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCostCenterName()) && null != ts.get(i).getCostCenterName()) {

                createStyledCell(row, index++, ts.get(i).getCostCenterName(), getStringCellStyle());

            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCostCenter()) && null != ts.get(i).getCostCenter()) {

                createStyledCell(row, index++, ts.get(i).getCostCenter(), getStringCellStyle());

            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getPlatform()) && null != ts.get(i).getPlatform()) {

                createStyledCell(row, index++, ts.get(i).getPlatform(), getStringCellStyle());

            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getProjectCode()) && null != ts.get(i).getProjectCode()) {
                createStyledCell(row, index++, ts.get(i).getProjectCode(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getProjectName()) && null != ts.get(i).getProjectName()) {
                createStyledCell(row, index++, ts.get(i).getProjectName(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getAssetsName()) && null != ts.get(i).getAssetsName()) {
                createStyledCell(row, index++, ts.get(i).getAssetsName(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getAssetsType()) && null != ts.get(i).getAssetsType()) {
                createStyledCell(row, index++, ts.get(i).getAssetsType(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getAssetsModel()) && null != ts.get(i).getAssetsModel()) {
                createStyledCell(row, index++, ts.get(i).getAssetsModel(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getUnit()) && null != ts.get(i).getUnit()) {

                createStyledCell(row, index++, ts.get(i).getUnit(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getAmount()) && null != ts.get(i).getAmount()) {
                createStyledCell(row, index++, String.valueOf(ts.get(i).getAmount()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getUnitPrice()) && null != ts.get(i).getUnitPrice()) {
                createStyledCell(row, index++, ts.get(i).getUnitPrice().toString(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }


            if (!"".equals(ts.get(i).getUsedSumMoney()) && null != ts.get(i).getUsedSumMoney()) {
                createStyledCell(row, index++, ts.get(i).getUsedSumMoney().toString(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getAvaliableSumMoney()) && null != ts.get(i).getAvaliableSumMoney()) {
                createStyledCell(row, index++, ts.get(i).getAvaliableSumMoney().toString(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCheckMoney()) && null != ts.get(i).getCheckMoney()) {
                createStyledCell(row, index++, ts.get(i).getCheckMoney().toString(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getYearBudgetTotal()) && null != ts.get(i).getYearBudgetTotal()) {
                createStyledCell(row, index++, ts.get(i).getYearBudgetTotal().toString(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getIsSure()) && null != ts.get(i).getIsSure()) {
                createStyledCell(row, index++, ts.get(i).getIsSure().toString().equals(STATUS.YES.getStatus()) ? "是" : "否",
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCreateBy()) && null != ts.get(i).getCreateBy()) {
                createStyledCell(row, index++, ts.get(i).getCreateBy(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCreateDate()) && null != ts.get(i).getCreateDate()) {
                createStyledCell(row, index++, formatter.format(ts.get(i).getCreateDate()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
        }


    }

}
