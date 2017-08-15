package com.opple.fa.config.export.controller;

import com.opple.fa.config.entity.AssetClassification;
import com.opple.fa.config.model.ASSTEENABLE;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.excel.AbstractExcelExportTemplate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExportAssetClassificationTemplate extends AbstractExcelExportTemplate<AssetClassification> {

	@Override
	public String[] getSheetNames() {
		return new String[]{"资产分类信息"};
	}

	@Override
	public String[][] getTitles() {
        return new String[][]{{"资产分类", "资产编码", "资产分类描述", "折旧年限（年）", "净残值率", "归口管理部门", "总账科目", "科目描述", "费用类别", "数据状态"}};
    }

	@Override
	protected void buildBody(int sheetIndex, List<AssetClassification> assetClassifications) {
		
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

		for (int i = 0; i < assetClassifications.size(); i++) {
			Row row = sheet.createRow(i + startIndex);
			row.setHeight((short) 300);
			int index = 0;
			if (null != assetClassifications.get(i).getAssetClassification() && !"".equals(assetClassifications.get(i).getAssetClassification())) {
				createStyledCell(row, index++, assetClassifications.get(i).getAssetClassification(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}

			if (null != assetClassifications.get(i).getAssetsName() && !"".equals(assetClassifications.get(i).getAssetsName())) {
				createStyledCell(row, index++, assetClassifications.get(i).getAssetsName(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}

			if (null != assetClassifications.get(i).getAssetstypeInfo() && !"".equals(assetClassifications.get(i).getAssetstypeInfo())) {
				createStyledCell(row, index++, assetClassifications.get(i).getAssetstypeInfo(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			//折旧年限（年）
			if (null != assetClassifications.get(i).getDepreciationPeriod() && !"".equals(assetClassifications.get(i).getDepreciationPeriod())) {
				createStyledCell(row, index++, assetClassifications.get(i).getDepreciationPeriod(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			//净残值率
			if (null != assetClassifications.get(i).getNetResidualRate() && !"".equals(assetClassifications.get(i).getNetResidualRate())) {
				createStyledCell(row, index++, assetClassifications.get(i).getNetResidualRate(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			//归口管理部门
			if (null != assetClassifications.get(i).getManagementDepartment() && !"".equals(assetClassifications.get(i).getManagementDepartment())) {
				createStyledCell(row, index++, assetClassifications.get(i).getManagementDepartment(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			//总账科目
			if (null != assetClassifications.get(i).getGeneralLedgerAccount() && !"".equals(assetClassifications.get(i).getGeneralLedgerAccount())) {
				createStyledCell(row, index++, assetClassifications.get(i).getGeneralLedgerAccount(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
			//科目描述
			if (null != assetClassifications.get(i).getAccountDescription() && !"".equals(assetClassifications.get(i).getAccountDescription())) {
				createStyledCell(row, index++, assetClassifications.get(i).getAccountDescription(), getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
            //费用类别
            if (null != assetClassifications.get(i).getAssteEnable() && !"".equals(assetClassifications.get(i).getAssteEnable())) {
                String assetEnable = "";
                if (ASSTEENABLE.ZAB.getAssteEnable().equals(assetClassifications.get(i).getAssteEnable())) {
                    assetEnable = ASSTEENABLE.CAPITALIZATION.getAssteEnable();
                } else if (ASSTEENABLE.ZFB.getAssteEnable().equals(assetClassifications.get(i).getAssteEnable())) {
                    assetEnable = ASSTEENABLE.EXPENSING.getAssteEnable();
                } else {
                    assetEnable = assetClassifications.get(i).getAssteEnable();
				}
				createStyledCell(row, index++, assetEnable, getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}
            //数据状态
            if (null != assetClassifications.get(i).getStatus() && !"".equals(assetClassifications.get(i).getStatus())) {
                String status = "";
                if (STATUS.YES_ZERO.getStatus().equals(assetClassifications.get(i).getStatus())) {
                    status = STATUS.EFFECTIVE.getStatus();
                } else if (STATUS.NO.getStatus().equals(assetClassifications.get(i).getStatus())) {
                    status = STATUS.INVALID.getStatus();
                } else {
                    status = assetClassifications.get(i).getStatus();
				}
				createStyledCell(row, index++, status, getStringCellStyle());
			} else {
				createStyledCell(row, index++, "", getStringCellStyle());
			}

		}

	}

}
