package com.opple.fa.config.export.controller;

import com.opple.fa.config.entity.DeptCostCenter;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.excel.AbstractExcelExportTemplate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExportDeptCostCenterTemplate extends AbstractExcelExportTemplate<DeptCostCenter> {

    @Override
    public String[] getSheetNames() {
        return new String[]{"预算体-成本中心信息"};
    }

    @Override
    public String[][] getTitles() {
        return new String[][]{{"所属公司编码", "预算体编码", "预算体名称", "成本中心编码", "成本中心名称", "创建人", "创建时间", "最后维护人", "最后维护时间", "备注", "状态"}};
    }

    @Override
    protected void buildBody(int sheetIndex, List<DeptCostCenter> deptCostCenters) {

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
        sheet.setDefaultColumnStyle(10, getStringCellStyle());

        for (int i = 0; i < deptCostCenters.size(); i++) {
            Row row = sheet.createRow(i + startIndex);
            row.setHeight((short) 300);
            int index = 0;
            if (null != deptCostCenters.get(i).getCompanyCode() && !"".equals(deptCostCenters.get(i).getCompanyCode())) {
                createStyledCell(row, index++, deptCostCenters.get(i).getCompanyCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != deptCostCenters.get(i).getDeptCode() && !"".equals(deptCostCenters.get(i).getDeptCode())) {
                createStyledCell(row, index++, deptCostCenters.get(i).getDeptCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != deptCostCenters.get(i).getDeptName() && !"".equals(deptCostCenters.get(i).getDeptName())) {
                createStyledCell(row, index++, deptCostCenters.get(i).getDeptName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != deptCostCenters.get(i).getCostCenterCode() && !"".equals(deptCostCenters.get(i).getCostCenterCode())) {
                createStyledCell(row, index++, deptCostCenters.get(i).getCostCenterCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != deptCostCenters.get(i).getCostCenterName() && !"".equals(deptCostCenters.get(i).getCostCenterName())) {
                createStyledCell(row, index++, deptCostCenters.get(i).getCostCenterName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != deptCostCenters.get(i).getCreateBy() && !"".equals(deptCostCenters.get(i).getCreateBy())) {
                createStyledCell(row, index++, deptCostCenters.get(i).getCreateBy(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != deptCostCenters.get(i).getCreateDate() && !"".equals(deptCostCenters.get(i).getCreateDate())) {
                String date = sdf.format(deptCostCenters.get(i).getCreateDate());
                createStyledCell(row, index++, date, getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != deptCostCenters.get(i).getUpdateBy() && !"".equals(deptCostCenters.get(i).getUpdateBy())) {
                createStyledCell(row, index++, deptCostCenters.get(i).getUpdateBy(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != deptCostCenters.get(i).getLastDate() && !"".equals(deptCostCenters.get(i).getLastDate())) {
                String date = sdf.format(deptCostCenters.get(i).getLastDate());
                createStyledCell(row, index++, date, getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != deptCostCenters.get(i).getMemo() && !"".equals(deptCostCenters.get(i).getMemo())) {
                createStyledCell(row, index++, deptCostCenters.get(i).getMemo(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != deptCostCenters.get(i).getStatus() && !"".equals(deptCostCenters.get(i).getStatus())) {
                String status = "";
                if (STATUS.YES.getStatus().equals(deptCostCenters.get(i).getStatus())) {
                    status = STATUS.EFFECTIVE.getStatus();
                } else {
                    status = STATUS.INVALID.getStatus();
                }
                createStyledCell(row, index++, status, getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }


        }

    }

}
