package com.opple.fa.processasset.export.controller;

import com.opple.fa.excel.AbstractExcelExportTemplate;
import com.opple.fa.processasset.entity.ProcessAssets;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExportProcessAssetsTemplate extends
        AbstractExcelExportTemplate<ProcessAssets> {

    @Override
    public String[] getSheetNames() {
        // TODO Auto-generated method stub
        return new String[]{"组合资产"};
    }

    @Override
    public String[][] getTitles() {
        // TODO Auto-generated method stub
        return new String[][]{{"组合资产编码", "组合资产名称", "申请人", "申请时间",
                "组合资产责任人", "成本中心", "资产责任人公司"}};
    }

    @Override
    protected void buildBody(int sheetIndex, List<ProcessAssets> ts) {

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
            if (!"".equals(ts.get(i).getDocument())
                    && null != ts.get(i).getDocument()) {
                createStyledCell(row, index++, ts.get(i).getDocument(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getProcessAssetsName())
                    && null != ts.get(i).getProcessAssetsName()) {
                createStyledCell(row, index++,
                        ts.get(i).getProcessAssetsName(), getStringCellStyle());
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


            if (!"".equals(ts.get(i).getAssetOwner())
                    && null != ts.get(i).getAssetOwner()) {
                createStyledCell(row, index++, ts.get(i).getAssetOwner(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

		/*	if (!"".equals(ts.get(i).getCreateBy())
                    && null != ts.get(i).getCreateBy()) {
				createStyledCell(row, index++, ts.get(i).getCreateBy(),
						getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}*/

			/*if (!"".equals(ts.get(i).getCreateDate())
                    && null != ts.get(i).getCreateDate()) {
				createStyledCell(row, index++,
						formatter.format(ts.get(i).getCreateDate()),
						getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}*/
            if (!"".equals(ts.get(i).getAssetCostcenterName())
                    && null != ts.get(i).getAssetCostcenterName()) {
                createStyledCell(row, index++, ts.get(i).getAssetCostcenterName(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (!"".equals(ts.get(i).getHomeCompany())
                    && null != ts.get(i).getCreateBy()) {
                createStyledCell(row, index++, ts.get(i).getHomeCompany(),
                        getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            /*if (!"".equals(ts.get(i).getCommitType())
                    && null != ts.get(i).getCommitType()) {
				if ("0".equals(ts.get(i).getCommitType())) {
					createStyledCell(row, index++, "是", getStringCellStyle());
				}
				if ("1".equals(ts.get(i).getCommitType())) {
					createStyledCell(row, index++, "否", getStringCellStyle());
				}
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}*/

        }

    }

}
