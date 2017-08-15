package com.opple.fa.assetcheck.export.controller;

import com.opple.fa.assetcheck.entity.AssetCheckBusiness;
import com.opple.fa.excel.AbstractExcelExportTemplate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExportAssetcheckTemplate extends
        AbstractExcelExportTemplate<AssetCheckBusiness> {

    @Override
    public String[] getSheetNames() {

        return new String[]{"盘点列表"};
    }

    @Override
    public String[][] getTitles() {

        return new String[][]{{"盘点单据号", "盘点年度", "盘点月", "盘点人", "盘点申请日期", "盘点状态",
                "盘点完结日期"
        }};
    }

    @Override
    protected void buildBody(int sheetIndex, List<AssetCheckBusiness> ts) {

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


        for (int i = 0; i < ts.size(); i++) {
            Row row = sheet.createRow(i + startIndex);
            row.setHeight((short) 300);
            int index = 0;
            if (!"".equals(ts.get(i).getDocumentNumber())
                    && null != ts.get(i).getDocumentNumber()) {
                createStyledCell(row, index++, ts.get(i).getDocumentNumber(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getInventoryYear())
                    && null != ts.get(i).getInventoryYear()) {
                createStyledCell(row, index++, ts.get(i).getInventoryYear(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getInventoryMonth())
                    && null != ts.get(i).getInventoryMonth()) {
                createStyledCell(row, index++, ts.get(i)
                        .getInventoryMonth(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getInventoryPeople())
                    && null != ts.get(i).getInventoryPeople()) {
                createStyledCell(row, index++, ts.get(i)
                        .getInventoryPeople(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != ts.get(i).getInventoryApplyDate()) {
                createStyledCell(row, index++,
                        formatter.format(ts.get(i).getInventoryApplyDate()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getInventoryStatus())
                    && null != ts.get(i).getInventoryStatus()) {
                if ("0".equals(ts.get(i).getInventoryStatus())) {
                    createStyledCell(row, index++, "盘点进行中", getStringCellStyle());
                }
                if ("1".equals(ts.get(i).getInventoryStatus())) {
                    createStyledCell(row, index++, "盘点结束", getStringCellStyle());
                }

            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != ts.get(i).getInventoryEndDate()) {
                createStyledCell(row, index++,
                        formatter.format(ts.get(i).getInventoryEndDate()),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }


        }
    }
}
