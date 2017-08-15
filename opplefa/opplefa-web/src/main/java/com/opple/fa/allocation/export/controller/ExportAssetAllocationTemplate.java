package com.opple.fa.allocation.export.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.opple.fa.allocation.entity.AssetAllocation;
import com.opple.fa.allocation.entity.CONFIRMSTATUS;
import com.opple.fa.assetcard.entity.ASSETTYPE;
import com.opple.fa.assetcard.entity.ISEXPENSEASSETS;
import com.opple.fa.excel.AbstractExcelExportTemplate;

public class ExportAssetAllocationTemplate extends
        AbstractExcelExportTemplate<AssetAllocation> {

    @Override
    public String[] getSheetNames() {
        // TODO Auto-generated method stub
        return new String[]{"资产调拨"};
    }

    @Override
    public String[][] getTitles() {
        // TODO Auto-generated method stub
        return new String[][]{{"单据号", "审批状态", "下一处理人", "确认人", "确认状态", "申请人",
                "申请日期", "归属公司", "调配方式", "资产类型", "调拨后责任人", "是否为草稿", "是否打印"}};
    }

    @Override
    protected void buildBody(int sheetIndex, List<AssetAllocation> ts) {
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
            if (!"".equals(ts.get(i).getDocument()) && null != ts.get(i).getDocument()) {
                createStyledCell(row, index++, ts.get(i).getDocument(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getApprovalState())
                    && null != ts.get(i).getApprovalState()) {
                createStyledCell(row, index++, ts.get(i).getApprovalState(), getStringCellStyle());

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
            if (!"".equals(ts.get(i).getConfirmPerson())
                    && null != ts.get(i).getConfirmPerson()) {
                createStyledCell(row, index++, ts.get(i).getConfirmPerson(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getConfirmStatus())
                    && null != ts.get(i).getConfirmStatus()) {

                if (CONFIRMSTATUS.WAITINGCONFIRMATION.getConfirmStatus().equals(ts.get(i).getConfirmStatus())) {
                    createStyledCell(row, index++, "等待确认", getStringCellStyle());
                }
                if (CONFIRMSTATUS.INCONFIRMATION.getConfirmStatus().equals(ts.get(i).getConfirmStatus())) {
                    createStyledCell(row, index++, "确认中", getStringCellStyle());
                }
                if (CONFIRMSTATUS.CONFIRMCOMPLETION.getConfirmStatus().equals(ts.get(i).getConfirmStatus())) {
                    createStyledCell(row, index++, "确认完毕", getStringCellStyle());
                }
                if (CONFIRMSTATUS.DENY.getConfirmStatus().equals(ts.get(i).getConfirmStatus())) {
                    createStyledCell(row, index++, "退回", getStringCellStyle());
                }

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

            if (!"".equals(ts.get(i).getApplicationDate())
                    && null != ts.get(i).getApplicationDate()) {
                createStyledCell(row, index++,
                        formatter.format(ts.get(i).getApplicationDate()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getPostCompanyCode())
                    && null != ts.get(i).getPostCompanyCode()) {
                createStyledCell(row, index++, ts.get(i).getPostCompanyCode(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

			/*
             * if (!"".equals(ts.get(i).getPostAllocationStoragePlace()) && null
			 * != ts.get(i).getPostAllocationStoragePlace()) {
			 * createStyledCell(row, index++, ts.get(i)
			 * .getPostAllocationStoragePlace(), getStringCellStyle()); } else {
			 * createStyledCell(row, index++, "", getStringCellStyle()); }
			 */
            if (!"".equals(ts.get(i).getAllocationMethod())
                    && null != ts.get(i).getAllocationMethod()) {
                createStyledCell(row, index++, ts.get(i).getAllocationMethod(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getAssetType())
                    && null != ts.get(i).getAssetType()) {

                if (ASSETTYPE.ITASSET.getAssetType().equals(ts.get(i).getAssetType())) {
                    createStyledCell(row, index++, "IT资产", getStringCellStyle());
                }
                if (ASSETTYPE.ADMINISTRATIVE.getAssetType().equals(ts.get(i).getAssetType())) {
                    createStyledCell(row, index++, "行政资产", getStringCellStyle());
                }
                if (ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType().equals(ts.get(i).getAssetType())) {
                    createStyledCell(row, index++, "计量器具", getStringCellStyle());
                }
                if (ASSETTYPE.MACHINERYEQUIPMENT.getAssetType().equals(ts.get(i).getAssetType())) {
                    createStyledCell(row, index++, "机器设备", getStringCellStyle());
                }

            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getPostAllocationPeople())
                    && null != ts.get(i).getPostAllocationPeople()) {
                createStyledCell(row, index++, ts.get(i)
                        .getPostAllocationPeople(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(ts.get(i).getCommitType())
                    && null != ts.get(i).getCommitType()) {
                if (ISEXPENSEASSETS.YES.getIsExpenseassets().equals(ts.get(i).getCommitType())) {
                    createStyledCell(row, index++, "是", getStringCellStyle());
                }
                if (ISEXPENSEASSETS.NO.getIsExpenseassets().equals(ts.get(i).getCommitType())) {
                    createStyledCell(row, index++, "否", getStringCellStyle());
                }
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getIsStamp())
                    && null != ts.get(i).getIsStamp()) {
                if ("Y".equals(ts.get(i).getIsStamp())) {
                    createStyledCell(row, index++, "是", getStringCellStyle());
                } else if ("N".equals(ts.get(i).getCommitType())) {
                    createStyledCell(row, index++, "否", getStringCellStyle());
                } else {
                    createStyledCell(row, index++, "否", getStringCellStyle());
                }
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

        }

    }

}
