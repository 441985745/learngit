package com.opple.fa.config.export.controller;

import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.excel.AbstractExcelExportTemplate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExportAttchConfigTemplate extends AbstractExcelExportTemplate<AttchConfig> {

    @Override
    public String[] getSheetNames() {
        return new String[]{"归口负责人信息"};
    }

    @Override
    public String[][] getTitles() {
        return new String[][]{{"公司编码", "公司名称", "成本中心编码", "成本中心", "平台", "资产类型", "车间类型", "归口部门管理员编码",
                "归口部门管理员", "归口部门经理编码", "归口部门经理", "归口部门总监编码", "归口部门总监", "归口部门副总裁编码",
                "归口部门副总裁", "采购员编码1", "采购员名称1", "采购员编码2", "采购员名称2", "采购员编码3", "采购员名称3",
                "采购员编码4", "采购员名称4", "采购员编码5", "采购员名称5", "采购员编码6", "采购员名称6", "采购员编码7", "采购员名称7",
                "采购员编码8", "采购员名称8", "采购经理编码", "采购经理", "二级部门负责人编码", "二级部门负责人名称", "数据状态"}};
    }

    @Override
    protected void buildBody(int sheetIndex, List<AttchConfig> attachConfigList) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Sheet sheet = getSheet(sheetIndex);
        int startIndex = this.getBodyStartIndex(sheetIndex);

        for (int i = 0; i < attachConfigList.size(); i++) {
            Row row = sheet.createRow(i + startIndex);
            row.setHeight((short) 300);
            int index = 0;
            if (null != attachConfigList.get(i).getCompanyCode() && !"".equals(attachConfigList.get(i).getCompanyCode())) {
                createStyledCell(row, index++, attachConfigList.get(i).getCompanyCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getCompanyName() && !"".equals(attachConfigList.get(i).getCompanyName())) {
                createStyledCell(row, index++, attachConfigList.get(i).getCompanyName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getCostcenterCode() && !"".equals(attachConfigList.get(i).getCostcenterCode())) {
                createStyledCell(row, index++, attachConfigList.get(i).getCostcenterCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getCostcenterName() && !"".equals(attachConfigList.get(i).getCostcenterName())) {
                createStyledCell(row, index++, attachConfigList.get(i).getCostcenterName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getPlatform() && !"".equals(attachConfigList.get(i).getPlatform())) {
                createStyledCell(row, index++, attachConfigList.get(i).getPlatform(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getAssetType() && !"".equals(attachConfigList.get(i).getAssetType())) {
                createStyledCell(row, index++, attachConfigList.get(i).getAssetType(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getWorkShopType() && !"".equals(attachConfigList.get(i).getWorkShopType())) {
                createStyledCell(row, index++, attachConfigList.get(i).getWorkShopType(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getAttachDepartAdminCode() && !"".equals(attachConfigList.get(i).getAttachDepartAdminCode())) {
                createStyledCell(row, index++, attachConfigList.get(i).getAttachDepartAdminCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getAttachDepartAdminName() && !"".equals(attachConfigList.get(i).getAttachDepartAdminName())) {
                createStyledCell(row, index++, attachConfigList.get(i).getAttachDepartAdminName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getAttachDepartManagerCode() && !"".equals(attachConfigList.get(i).getAttachDepartManagerCode())) {
                createStyledCell(row, index++, attachConfigList.get(i).getAttachDepartManagerCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getAttachDepartManagerName() && !"".equals(attachConfigList.get(i).getAttachDepartManagerName())) {
                createStyledCell(row, index++, attachConfigList.get(i).getAttachDepartManagerName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getAttachDepartMajordomoCode() && !"".equals(attachConfigList.get(i).getAttachDepartMajordomoCode())) {
                createStyledCell(row, index++, attachConfigList.get(i).getAttachDepartMajordomoCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getAttachDepartMajordomoName() && !"".equals(attachConfigList.get(i).getAttachDepartMajordomoName())) {
                createStyledCell(row, index++, attachConfigList.get(i).getAttachDepartMajordomoName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getAttachDepartVpresidentCode() && !"".equals(attachConfigList.get(i).getAttachDepartVpresidentCode())) {
                createStyledCell(row, index++, attachConfigList.get(i).getAttachDepartVpresidentCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getAttachDepartVpresidentName() && !"".equals(attachConfigList.get(i).getAttachDepartVpresidentName())) {
                createStyledCell(row, index++, attachConfigList.get(i).getAttachDepartVpresidentName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getBuyerCodea() && !"".equals(attachConfigList.get(i).getBuyerCodea())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerCodea(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getBuyerNamea() && !"".equals(attachConfigList.get(i).getBuyerNamea())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerNamea(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getBuyerCodeb() && !"".equals(attachConfigList.get(i).getBuyerCodeb())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerCodeb(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getBuyerNameb() && !"".equals(attachConfigList.get(i).getBuyerNameb())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerNameb(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getBuyerCodec() && !"".equals(attachConfigList.get(i).getBuyerCodec())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerCodec(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getBuyerNamec() && !"".equals(attachConfigList.get(i).getBuyerNamec())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerNamec(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getBuyerCoded() && !"".equals(attachConfigList.get(i).getBuyerCoded())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerCoded(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getBuyerNamed() && !"".equals(attachConfigList.get(i).getBuyerNamed())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerNamed(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getBuyerCodee() && !"".equals(attachConfigList.get(i).getBuyerCodee())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerCodee(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getBuyerNamee() && !"".equals(attachConfigList.get(i).getBuyerNamee())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerNamee(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getBuyerCodef() && !"".equals(attachConfigList.get(i).getBuyerCodef())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerCodef(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getBuyerNamef() && !"".equals(attachConfigList.get(i).getBuyerNamef())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerNamef(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getBuyerCodeg() && !"".equals(attachConfigList.get(i).getBuyerCodeg())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerCodeg(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getBuyerNameg() && !"".equals(attachConfigList.get(i).getBuyerNameg())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerNameg(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getBuyerCodeh() && !"".equals(attachConfigList.get(i).getBuyerCodeh())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerCodeh(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getBuyerNameh() && !"".equals(attachConfigList.get(i).getBuyerNameh())) {
                createStyledCell(row, index++, attachConfigList.get(i).getBuyerNameh(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getPurchasingManagerCode() && !"".equals(attachConfigList.get(i).getPurchasingManagerCode())) {
                createStyledCell(row, index++, attachConfigList.get(i).getPurchasingManagerCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getPurchasingManagerName() && !"".equals(attachConfigList.get(i).getPurchasingManagerName())) {
                createStyledCell(row, index++, attachConfigList.get(i).getPurchasingManagerName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            if (null != attachConfigList.get(i).getTwoDepartmentHeadCode() && !"".equals(attachConfigList.get(i).getTwoDepartmentHeadCode())) {
                createStyledCell(row, index++, attachConfigList.get(i).getTwoDepartmentHeadCode(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
            if (null != attachConfigList.get(i).getTwoDepartmentHeadName() && !"".equals(attachConfigList.get(i).getTwoDepartmentHeadName())) {
                createStyledCell(row, index++, attachConfigList.get(i).getTwoDepartmentHeadName(), getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }

            //数据状态
            if (null != attachConfigList.get(i).getStatus() && !"".equals(attachConfigList.get(i).getStatus())) {
                String status = "";
                if (STATUS.YES_ZERO.getStatus().equals(attachConfigList.get(i).getStatus())) {
                    status = STATUS.EFFECTIVE.getStatus();
                } else if (STATUS.NO_ONE.getStatus().equals(attachConfigList.get(i).getStatus())) {
                    status = STATUS.INVALID.getStatus();
                } else {
                    status = attachConfigList.get(i).getStatus();
                }
                createStyledCell(row, index++, status, getStringCellStyle());
            } else {
                createStyledCell(row, index++, "", getStringCellStyle());
            }
        }

    }

}
