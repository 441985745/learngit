package com.opple.fa.discardsell.export.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.opple.fa.discardsell.entity.DiscardSell;
import com.opple.fa.excel.AbstractExcelExportTemplate;

public class ExportDiscardSellTemplate extends
        AbstractExcelExportTemplate<DiscardSell> {

    @Override
    public String[] getSheetNames() {

        return new String[]{"报废变卖列表"};
    }

    @Override
    public String[][] getTitles() {
        return new String[][]{{"单据号", "下一处理人", "申请人", "申请日期 ", "归属公司", "所属部门",
                "资产类型", "办公地点", "原值合计", "净值合计", "审批状态",
                "是否为草稿", "是否打印"}};
    }

    @Override
    protected void buildBody(int sheetIndex, List<DiscardSell> ts) {
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

        for (int i = 0; i < ts.size(); i++) {
            Row row = sheet.createRow(i + startIndex);
            row.setHeight((short) 300);
            int index = 0;
            if (!"".equals(ts.get(i).getDocument())
                    && null != ts.get(i).getDocument()) {
                createStyledCell(row, index++, ts.get(i).getDocument(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getNextHandlerName())
                    && null != ts.get(i).getNextHandlerName()) {
                createStyledCell(row, index++, ts.get(i).getNextHandlerName(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getCreateBy())
                    && null != ts.get(i).getCreateBy()) {
                createStyledCell(row, index++, ts.get(i).getCreateBy(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCreateDate())
                    && null != ts.get(i).getCreateDate()) {
                createStyledCell(row, index++,
                        formatter.format(ts.get(i).getCreateDate()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getAdscriptionCompanyCode())
                    && null != ts.get(i).getAdscriptionCompanyCode()) {
                createStyledCell(row, index++, ts.get(i)
                        .getAdscriptionCompanyCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getSubordinateDepartment())
                    && null != ts.get(i).getSubordinateDepartment()) {
                createStyledCell(row, index++, ts.get(i)
                        .getSubordinateDepartment(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getAssetType())
                    && null != ts.get(i).getAssetType()) {
                if ("0".equals(ts.get(i).getAssetType())) {
                    createStyledCell(row, index++, "IT资产", getStringCellStyle());
                }

                if ("1".equals(ts.get(i).getAssetType())) {
                    createStyledCell(row, index++, "行政资产", getStringCellStyle());
                }
                if ("2".equals(ts.get(i).getAssetType())) {
                    createStyledCell(row, index++, "计量器具", getStringCellStyle());
                }
                if ("3".equals(ts.get(i).getAssetType())) {
                    createStyledCell(row, index++, "机器设备", getStringCellStyle());
                }

            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getOfficeLocation())
                    && null != ts.get(i).getOfficeLocation()) {
                createStyledCell(row, index++, ts.get(i).getOfficeLocation(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getAssetOriginalValueSum())) {
                createStyledCell(row, index++, String.valueOf(ts.get(i).getAssetOriginalValueSum()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getAssetNetValueSum())) {
                createStyledCell(row, index++, String.valueOf(ts.get(i).getAssetNetValueSum()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

        /*    if (!"".equals(ts.get(i).getUpdateBy())
                    && null != ts.get(i).getUpdateBy()) {
                createStyledCell(row, index++, ts.get(i).getUpdateBy(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getUpdateDate())
                    && null != ts.get(i).getUpdateDate()) {
                createStyledCell(row, index++,
                        formatter.format(ts.get(i).getUpdateDate()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }*/
            if (!"".equals(ts.get(i).getApprovalState())
                    && null != ts.get(i).getApprovalState()) {
                createStyledCell(row, index++, ts.get(i).getApprovalState(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getCommitType())
                    && null != ts.get(i).getCommitType()) {
                if ("0".equals(ts.get(i).getCommitType())) {
                    createStyledCell(row, index++, "是", getStringCellStyle());
                }
                if ("1".equals(ts.get(i).getCommitType())) {
                    createStyledCell(row, index++, "否", getStringCellStyle());
                }
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getIsStamp())
                    && null != ts.get(i).getIsStamp()) {
                if ("Y".equals(ts.get(i).getIsStamp())) {
                    createStyledCell(row, index++, "是", getStringCellStyle());
                }
                if ("N".equals(ts.get(i).getIsStamp())) {
                    createStyledCell(row, index++, "否", getStringCellStyle());
                }
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
        }
    }

}
