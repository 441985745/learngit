package com.opple.fa.config.export.controller;

import com.opple.fa.config.entity.Areas;
import com.opple.fa.config.model.AREATYPE;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.excel.AbstractExcelExportTemplate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.SimpleDateFormat;
import java.util.List;

public class  ExportAreasTemplate extends AbstractExcelExportTemplate<Areas> {

    @Override
    public String[] getSheetNames() {
        return new String[]{"区域信息"};
    }

    @Override
    public String[][] getTitles() {
        return new String[][]{{"区域编码", "区域名称", "区域类型", "状态", "创建人编码", "创建人", "创建时间", "维护人编码", "维护人", " 维护时间"}};
    }

    @Override
    protected void buildBody(int sheetIndex, List<Areas> areas) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

        for (int i = 0; i < areas.size(); i++) {
            Row row = sheet.createRow(i + startIndex);
            row.setHeight((short) 300);
            int index = 0;
            if (!"".equals(areas.get(i).getAreaCode()) && null != areas.get(i).getAreaCode()) {
                createStyledCell(row, index++, areas.get(i).getAreaCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(areas.get(i).getAreaName()) && null != areas.get(i).getAreaName()) {
                createStyledCell(row, index++, areas.get(i).getAreaName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(areas.get(i).getAreaType()) && null != areas.get(i).getAreaType()) {
                String type = "";
                if (AREATYPE.HOME_ZERO.getAreaType().equals(areas.get(i).getAreaType())) {
                    type = AREATYPE.HOME.getAreaType();
                } else if (AREATYPE.COMMERCIAL_ONE.getAreaType().equals(areas.get(i).getAreaType())) {
                    type = AREATYPE.COMMERCIAL.getAreaType();
                }
                createStyledCell(row, index++, type, getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(areas.get(i).getStatus()) && null != areas.get(i).getStatus()) {
                String status = "";
                if (STATUS.YES_ZERO.getStatus().equals(areas.get(i).getStatus())) {
                    status = STATUS.EFFECTIVE.getStatus();
                } else if (STATUS.NO_ONE.getStatus().equals(areas.get(i).getStatus())) {
                    status = STATUS.INVALID.getStatus();
                }
                createStyledCell(row, index++, status, getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(areas.get(i).getCreateBy()) && null != areas.get(i).getCreateBy()) {
                createStyledCell(row, index++, areas.get(i).getCreateBy(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(areas.get(i).getCreateUserName()) && null != areas.get(i).getCreateUserName()) {
                createStyledCell(row, index++, areas.get(i).getCreateUserName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(areas.get(i).getCreateDate()) && null != areas.get(i).getCreateDate()) {
                createStyledCell(row, index++, sdf.format(areas.get(i).getCreateDate()), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(areas.get(i).getUpdateBy()) && null != areas.get(i).getUpdateBy()) {
                createStyledCell(row, index++, areas.get(i).getUpdateBy(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(areas.get(i).getUpdateUserName()) && null != areas.get(i).getUpdateUserName()) {
                createStyledCell(row, index++, areas.get(i).getUpdateUserName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (!"".equals(areas.get(i).getUpdateDate()) && null != areas.get(i).getUpdateDate()) {
                createStyledCell(row, index++, sdf.format(areas.get(i).getUpdateDate()), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

        }

    }

}
