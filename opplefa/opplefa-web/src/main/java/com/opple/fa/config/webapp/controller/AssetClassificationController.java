package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.AssetClassification;
import com.opple.fa.config.model.ASSTEENABLE;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AssetClassificationService;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.webapp.controller.BaseOpenApiController;
import com.opple.security.SessionSecurityConstants;
import com.opple.util.ExportExcle;
import com.opple.util.Pager;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ykz on 17/1/10.
 */
@Controller
@RequestMapping("/assetClassification")
public class AssetClassificationController extends BaseOpenApiController {
    private static final Logger LOOGER = LoggerFactory.getLogger(AssetClassificationController.class);
    @Resource
    private AssetClassificationService assetClassificationService;

    @RequestMapping(value = "/api/searchAssetClassification", method = RequestMethod.POST)
    @ResponseBody
    public List<AssetClassification> searchAssetClassification(@RequestBody ApplyOrder applyOrder) {

        if (ASSTEENABLE.YES.getAssteEnable().equals(applyOrder.getExpensing())) {
            applyOrder.setExpensing("ZFB");
        } else if (ASSTEENABLE.NO.getAssteEnable().equals(applyOrder.getExpensing())) {
            applyOrder.setExpensing("ZAB");
        }
        return assetClassificationService.searchAssetClassification(applyOrder);
    }

    @RequestMapping(value = "/api/searchAssetClassificationForDemand", method = RequestMethod.POST)
    @ResponseBody
    public List<AssetClassification> searchAssetClassificationForDemand() {
        ApplyOrder applyOrder = new ApplyOrder();
        return assetClassificationService.searchAssetClassification(applyOrder);
    }


    /**
     * 模糊查询
     *
     * @param model
     * @param request
     * @param assetClassification
     * @return
     */
    @RequestMapping("/searchAssetClassificationList")
    public String searchAssetClassificationList(Model model, HttpServletRequest request, Long offset, Long pageSize, AssetClassification assetClassification) {
        assetClassification = getQueryCriteria(assetClassification);

        Pager<AssetClassification> pager = new Pager<AssetClassification>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<AssetClassification> assetClassificationPager = assetClassificationService.searchAssetClassificationList(pager, assetClassification);


        assetClassificationPager.setUri(request.getRequestURI());
        model.addAttribute("assetClassificationPager", assetClassificationPager);
        return "config/assetClassification_list";
    }

    /**
     * 处理查询条件
     *
     * @param assetClassification
     * @return
     */
    private AssetClassification getQueryCriteria(AssetClassification assetClassification) {
        assetClassification.setAssetClassification(null == assetClassification.getAssetClassification() ? null : assetClassification.getAssetClassification().trim());
        assetClassification.setGeneralLedgerAccount(null == assetClassification.getGeneralLedgerAccount() ? null : assetClassification.getGeneralLedgerAccount().trim());
        assetClassification.setAssetsName(null == assetClassification.getAssetsName() ? null : assetClassification.getAssetsName().trim());
        return assetClassification;
    }

    /**
     * 添加页面跳转
     *
     * @return
     */
    @RequestMapping("/openDialogForAdd")
    public String openDialogForAdd() {
        return "config/assetClassification_add";
    }

    /**
     * 添加
     *
     * @param request
     * @param assetClassification
     * @return
     */
    @RequestMapping("/api/addAssetClassification")
    @ResponseBody
    public JSONObject addArea(HttpServletRequest request, @RequestBody AssetClassification assetClassification) {
        Map<String, Object> map = new HashMap<String, Object>();

        /*Integer checkNum= assetClassificationService.getAreasNumByAreaCode(areas.getAreaCode());
        if (checkNum > 0) {
            map.put("error", "区域编码已存在，换一个吧！");
            return JSONObject.fromObject(map);
        }*/
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        assetClassification.setCreateBy(userCode);
        assetClassification.setCreateUserName(userName);
        assetClassification.setUpdateBy(userCode);
        assetClassification.setUpdateUserName(userName);
        Integer exist = assetClassificationService.checkExisted(assetClassification);
        if (exist > 0) {
            map.put("error", "资产分类数据在数据库已存在!");
        } else {
            try {
                assetClassificationService.addAssetClassification(assetClassification);
            } catch (Exception e) {
                LOOGER.error("Exception", e);
                map.put("error", "添加失败");
                return JSONObject.fromObject(map);
            }
            map.put("success", true);
        }

        return JSONObject.fromObject(map);
    }

    /**
     * 修改页面跳转
     *
     * @return
     */
    @RequestMapping("/openDialogForUpdate")
    public String openDialogForUpdate(Model model, String id) {
        AssetClassification assetClassification = assetClassificationService.getAssetClassificationByID(id);
        model.addAttribute("assetClassification", assetClassification);
        return "config/assetClassification_update";
    }

    /**
     * 修改
     *
     * @param request
     * @param assetClassification
     * @return
     */
    @RequestMapping("/api/updateAssetClassification")
    @ResponseBody
    public JSONObject updateAssetClassification(HttpServletRequest request, @RequestBody AssetClassification assetClassification) {
        Map<String, Object> map = new HashMap<String, Object>();
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        assetClassification.setUpdateBy(userCode);
        assetClassification.setUpdateUserName(userName);
        try {
            assetClassificationService.updateAssetClassification(assetClassification);
        } catch (Exception e) {
            LOOGER.error("Exception", e);
            map.put("error", "修改失败");
            return JSONObject.fromObject(map);
        }
        map.put("success", true);
        return JSONObject.fromObject(map);
    }

    /**
     * 查看
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/showAssetClassification")
    public String showArea(Model model, String id) {
        AssetClassification assetClassification = assetClassificationService.getAssetClassificationByID(id);
        model.addAttribute("assetClassification", assetClassification);
        return "config/assetClassification_show";
    }

    @RequestMapping("/api/deleteAssetClassification")
    @ResponseBody
    public JSONObject deleteArea(HttpServletRequest request,
                                 @RequestParam(required = false, value = "ids[]") List<String> ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<AssetClassification> assetClassifications = new ArrayList<AssetClassification>();
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        for (String id : ids) {
            AssetClassification assetClassification = new AssetClassification();
            assetClassification.setId(id);
            assetClassification.setStatus(STATUS.NO_ONE.getStatus());
            assetClassification.setUpdateBy(userCode);
            assetClassification.setUpdateUserName(userName);
            assetClassifications.add(assetClassification);
        }
        try {
            assetClassificationService.updateStatusOfAssetClassifications(assetClassifications);
        } catch (Exception e) {
            LOOGER.error("Exception", e);
            map.put("error", "删除失败!");
            return JSONObject.fromObject(map);
        }
        map.put("success", true);
        return JSONObject.fromObject(map);
    }

    @RequestMapping("/api/importExcel")
    @ResponseBody
    public JSONObject importExcel(MultipartHttpServletRequest request, HttpServletResponse response) {
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        DecimalFormat df = new DecimalFormat("######0.00");
        //错误信息
        String errorNull = "";
        // 获得文件上传的文件
        MultipartFile myfile = request.getFile("uploadFile");

        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(myfile.getInputStream());
        } catch (IOException e) {
            LOOGER.error(e.toString());
        }
        Sheet sheet = wb.getSheetAt(0);
        Row row1 = sheet.getRow(0);

        List<AssetClassification> addAssetClassifications = new ArrayList<AssetClassification>();
        List<AssetClassification> updateAssetClassifications = new ArrayList<AssetClassification>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            AssetClassification assetClassification = new AssetClassification();

            //资产分类
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                assetClassification.setAssetClassification(row.getCell(0).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的资产分类为空 !\n";
            }
            //资产编码
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                assetClassification.setAssetsName(row.getCell(1).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的资产编码为空 !\n";
            }
            //资产分类描述
            if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                assetClassification.setAssetstypeInfo(row.getCell(2).getStringCellValue().trim());
            }
            //折旧年限（月）
            if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                assetClassification.setDepreciationPeriod(row.getCell(3).getStringCellValue().trim());
            }
            //净残值率
            if (null != row.getCell(4) && !"".equals(row.getCell(4).toString().trim())) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_NUMERIC);
                double rate = row.getCell(4).getNumericCellValue() * 100;
                assetClassification.setNetResidualRate(df.format(rate) + "%");
            }
            //归口管理部门
            if (null != row.getCell(5) && !"".equals(row.getCell(5).toString().trim())) {
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                assetClassification.setManagementDepartment(row.getCell(5).getStringCellValue().trim());
            }
            //总账科目
            if (null != row.getCell(6) && !"".equals(row.getCell(6).toString().trim())) {
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                assetClassification.setGeneralLedgerAccount(row.getCell(6).getStringCellValue().trim());
            }
            //科目描述
            if (null != row.getCell(7) && !"".equals(row.getCell(7).toString().trim())) {
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                assetClassification.setAccountDescription(row.getCell(7).getStringCellValue().trim());
            }
            // 费用类别
            if (null != row.getCell(8) && !"".equals(row.getCell(8).toString().trim())) {
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                String status = row.getCell(8).getStringCellValue().trim();
                if (ASSTEENABLE.CAPITALIZATION.getAssteEnable().equals(status)) {
                    assetClassification.setAssteEnable(ASSTEENABLE.ZAB.getAssteEnable());
                } else if (ASSTEENABLE.EXPENSING.getAssteEnable().equals(status)) {
                    assetClassification.setAssteEnable(ASSTEENABLE.ZFB.getAssteEnable());
                } else {
                    errorNull = errorNull + "第" + i + "行的费用类别不合法 !\n";
                }
            }

            assetClassification.setStatus(STATUS.YES_ZERO.getStatus());
            assetClassification.setUpdateBy(userCode);
            assetClassification.setUpdateUserName(userName);

            if (errorNull == "") {
                if (assetClassificationService.getAssetClassificationNumByAssetClassification(assetClassification.getAssetClassification()) > 0) {
                    updateAssetClassifications.add(assetClassification);
                } else {
                    assetClassification.setCreateBy(userCode);
                    assetClassification.setCreateUserName(userName);
                    addAssetClassifications.add(assetClassification);
                }
            }
        }
        if (updateAssetClassifications.size() == 0 && addAssetClassifications.size() == 0) {
            errorNull = errorNull + "表中无数据 !\n";
        }
        if (StringUtils.isBlank(errorNull)) {
            String result = assetClassificationService.addOrUpdateAssetClassifications(addAssetClassifications, updateAssetClassifications);
            if (StringUtils.isBlank(result)) {
                jsonMap.put("success", true);
            } else {
                jsonMap.put("errorNull", result);
            }
        } else {
            jsonMap.put("errorNull", errorNull);
        }
        try {
            response.setContentType("text/html; charset=utf-8");
//            response.getWriter().write(JSONObject.fromObject(jsonMap).toString());
            response.getWriter().write(JSONObject.fromObject(jsonMap).toString() + "<script>document.domain='opple.com'</script>");
            response.getWriter().flush();
        } catch (IOException e1) {
            LOOGER.error("IOException", e1);
        }
        return null;
    }


    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, AssetClassification assetClassification) {
        assetClassification = getQueryCriteria(assetClassification);
        List<AssetClassification> assetClassifications = assetClassificationService.exportAssetClassificationList(assetClassification);

        String fileName = "资产分类配置表.xlsx";
        String sheetName = "资产分类配置表";
        int[] columnWidths = new int[]{10, 10, 10};
        Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
        data.put(STATUS.NO_ONE.getStatus(), new Object[]{"资产分类", "资产编码", "资产分类描述", "折旧年限（年）", "净残值率", "归口管理部门", "总账科目", "科目描述", "费用类别", "数据状态"});
        int dataIdx = 2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder asset = null;
        StringBuilder assetsName = null;
        StringBuilder assetstypeInfo = null;
        StringBuilder depreciationPeriod = null;
        StringBuilder netResidualRate = null;
        StringBuilder managementDepartment = null;
        StringBuilder generalLedgerAccount = null;
        StringBuilder accountDescription = null;
        StringBuilder assetEnable = null;
        StringBuilder status = null;

        for (AssetClassification classification : assetClassifications) {
            asset = new StringBuilder(classification.getAssetClassification() == null ? "" : classification.getAssetClassification());
            assetsName = new StringBuilder(classification.getAssetsName() == null ? "" : classification.getAssetsName());
            assetstypeInfo = new StringBuilder(classification.getAssetstypeInfo() == null ? "" : classification.getAssetstypeInfo());
            depreciationPeriod = new StringBuilder(classification.getDepreciationPeriod() == null ? "" : classification.getDepreciationPeriod());
            netResidualRate = new StringBuilder(classification.getNetResidualRate() == null ? "" : classification.getNetResidualRate());
            managementDepartment = new StringBuilder(classification.getManagementDepartment() == null ? "" : classification.getManagementDepartment());
            generalLedgerAccount = new StringBuilder(classification.getGeneralLedgerAccount() == null ? "" : classification.getGeneralLedgerAccount());
            accountDescription = new StringBuilder(classification.getAccountDescription() == null ? "" : classification.getAccountDescription());

            if (StringUtils.isNotBlank(classification.getAssteEnable())) {
                if (ASSTEENABLE.ZAB.getAssteEnable().equals(classification.getAssteEnable())) {
                    assetEnable = new StringBuilder(ASSTEENABLE.CAPITALIZATION.getAssteEnable());
                } else if (ASSTEENABLE.ZFB.getAssteEnable().equals(classification.getAssteEnable())) {
                    assetEnable = new StringBuilder(ASSTEENABLE.EXPENSING.getAssteEnable());
                } else {
                    assetEnable = new StringBuilder(classification.getAssteEnable());
                }
            } else {
                assetEnable = new StringBuilder("");
            }
            if (StringUtils.isNotBlank(classification.getStatus())) {
                if (STATUS.YES_ZERO.getStatus().equals(classification.getStatus())) {
                    status = new StringBuilder(STATUS.EFFECTIVE.getStatus());
                } else if (STATUS.NO_ONE.getStatus().equals(classification.getStatus())) {
                    status = new StringBuilder(STATUS.INVALID.getStatus());
                } else {
                    status = new StringBuilder(classification.getStatus());
                }
            } else {
                status = new StringBuilder("");
            }

            Object[] newRow = new Object[]{
                    asset.toString(),
                    assetsName.toString(),
                    assetstypeInfo.toString(),
                    depreciationPeriod.toString(),
                    netResidualRate.toString(),
                    managementDepartment.toString(),
                    generalLedgerAccount.toString(),
                    accountDescription.toString(),
                    assetEnable.toString(),
                    status.toString()
            };
            data.put("" + dataIdx++, newRow);
        }
        try {
            ExportExcle.generateExcel(response, data, fileName, sheetName, columnWidths);
        } catch (IOException e) {
            LOOGER.error("IOException", e);
        }

        /*try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportAssetClassificationTemplate exportAssetClassificationTemplate = new ExportAssetClassificationTemplate();
            exportAssetClassificationTemplate.doExport(response, fileName, assetClassifications);

        } catch (UnsupportedEncodingException e) {
            LOOGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOOGER.error("IOException", e);
        }*/
    }
}