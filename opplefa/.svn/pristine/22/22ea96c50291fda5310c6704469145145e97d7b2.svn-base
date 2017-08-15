package com.opple.fa.config.webapp.controller;


import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.DeptCostCenterService;
import com.opple.fa.config.service.UserInfoService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/attchConfig")
public class AttchConfigController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttchConfigController.class);
    @Resource
    private AttchConfigService attchConfigService;
    @Resource
    private DeptCostCenterService deptCostCenterService;

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "/api/searchByCostcenterCodeAndAssetType")
    @ResponseBody
    public AttchConfig searchByCostcenterCodeAndAssetType(AttchConfig attchConfig) {
        AttchConfig searchByCostcenterCodeAndAssetType = attchConfigService.searchByCostcenterCodeAndAssetType(attchConfig);
        return searchByCostcenterCodeAndAssetType;
    }


    /**
     * 模糊查询
     *
     * @param model
     * @param request
     * @param attchConfig
     * @return
     */
    @RequestMapping("/searchAttachConfigList")
    public String searchAttchConfigList(Model model, HttpServletRequest request, Long offset, Long pageSize, AttchConfig attchConfig) {
        attchConfig = getQueryCriteria(attchConfig);

        Pager<AttchConfig> pager = new Pager<AttchConfig>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<AttchConfig> attchConfigPager = attchConfigService.searchAttchConfigList(pager, attchConfig);

        attchConfigPager.setUri(request.getRequestURI());
        model.addAttribute("attachConfigPager", attchConfigPager);
        return "config/attach_config_list";
    }

    /**
     * 处理查询条件
     *
     * @param attchConfig
     * @return
     */
    private AttchConfig getQueryCriteria(AttchConfig attchConfig) {
        attchConfig.setCompanyCode(null == attchConfig.getCompanyCode() ? null : attchConfig.getCompanyCode().trim());
        attchConfig.setCostcenterCode(null == attchConfig.getCostcenterCode() ? null : attchConfig.getCostcenterCode().trim());
        attchConfig.setAssetType(null == attchConfig.getAssetType() ? null : attchConfig.getAssetType().trim());
        return attchConfig;
    }

    /**
     * 添加页面跳转
     *
     * @return
     */
    @RequestMapping("/openDialogForAdd")
    public String openDialogForAdd() {
        return "config/attach_config_add";
    }

    /**
     * 添加
     *
     * @param request
     * @param attchConfig
     * @return
     */
    @RequestMapping("/api/addAttachConfig")
    @ResponseBody
    public JSONObject addArea(HttpServletRequest request, @RequestBody AttchConfig attchConfig) {
        Map<String, Object> map = new HashMap<String, Object>();

        attchConfig = getQueryCriteria(attchConfig);
        Integer checkNum = attchConfigService.getAttchConfigNumByImport(attchConfig);
        if (checkNum > 0) {
            map.put("error", "相同的公司编码、成本中心、资产类型存在，换一个吧！");
            return JSONObject.fromObject(map);
        }
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        attchConfig.setCreateBy(userCode);
        attchConfig.setCreateUserName(userName);
        attchConfig.setUpdateBy(userCode);
        attchConfig.setUpdateUserName(userName);
        try {
            attchConfigService.addAttchConfig(attchConfig);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            map.put("error", "添加失败");
            return JSONObject.fromObject(map);
        }
        map.put("success", true);
        return JSONObject.fromObject(map);
    }

    /**
     * 修改页面跳转
     *
     * @return
     */
    @RequestMapping("/openDialogForUpdate")
    public String openDialogForUpdate(Model model, String id) {
        AttchConfig attachConfig = attchConfigService.getAttchConfigByID(id);
        model.addAttribute("attachConfig", attachConfig);
        return "config/attach_config_update";
    }

    /**
     * 修改
     *
     * @param request
     * @param attchConfig
     * @return
     */
    @RequestMapping("/api/updateAttachConfig")
    @ResponseBody
    public JSONObject updateAssetClassification(HttpServletRequest request, @RequestBody AttchConfig attchConfig) {
        Map<String, Object> map = new HashMap<String, Object>();
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        attchConfig.setUpdateBy(userCode);
        attchConfig.setUpdateUserName(userName);
        try {
            attchConfigService.updateAttchConfig(attchConfig);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
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
    @RequestMapping("/showAttachConfig")
    public String showArea(Model model, String id) {
        AttchConfig attachConfig = attchConfigService.getAttchConfigByID(id);
        model.addAttribute("attachConfig", attachConfig);
        return "config/attach_config_show";
    }

    @RequestMapping("/api/deleteAttachConfig")
    @ResponseBody
    public JSONObject deleteArea(HttpServletRequest request,
                                 @RequestParam(required = false, value = "ids[]") List<String> ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<AttchConfig> attachConfigList = new ArrayList<AttchConfig>();
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        for (String id : ids) {
            AttchConfig attachConfig = new AttchConfig();
            attachConfig.setId(id);
            attachConfig.setStatus(STATUS.NO_ONE.getStatus());
            attachConfig.setUpdateBy(userCode);
            attachConfig.setUpdateUserName(userName);
            attachConfigList.add(attachConfig);
        }
        try {
            attchConfigService.updateStatusOfAttchConfigList(attachConfigList);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
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
        StringBuilder error = new StringBuilder("");
        // 获得文件上传的文件
        MultipartFile myfile = request.getFile("uploadFile");
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(myfile.getInputStream());
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        Sheet sheet = wb.getSheetAt(0);
        Row row1 = sheet.getRow(0);
        List<AttchConfig> addAttachConfigList = new ArrayList<AttchConfig>();
        List<AttchConfig> updateAttachConfigList = new ArrayList<AttchConfig>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            AttchConfig attachConfig = new AttchConfig();
            //公司编码
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setCompanyCode(row.getCell(0).getStringCellValue().trim());
            } else {
                error.append("第" + i + "行的公司编码为空 !\n");
            }
            //公司名称
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setCompanyName(row.getCell(1).getStringCellValue().trim());
            } else {
                error.append("第" + i + "行的公司名称为空 !\n");
            }
            //成本中心编码
            if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);

                String regex = ".*[a-zA-Z]+.*";
                Matcher m = Pattern.compile(regex).matcher(row.getCell(2).getStringCellValue().trim());
                if (!m.matches()) {
                    attachConfig.setCostcenterCode(StringUtils.leftPad(row.getCell(2).getStringCellValue().trim(), 10, STATUS.YES_ZERO.getStatus()));
                } else {
                    attachConfig.setCostcenterCode(row.getCell(2).getStringCellValue().trim());
                }
            } else {
                error.append("第" + i + "行的成本中心编码为空 !\n");
            }
            //成本中心
            if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setCostcenterName(row.getCell(3).getStringCellValue().trim());
            } else {
                error.append("第" + i + "行的成本中心名称为空 !\n");
            }
            //平台
            if (null != row.getCell(4) && !"".equals(row.getCell(4).toString().trim())) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setPlatform(row.getCell(4).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的平台为空 !\n");
            }*/
            //资产类型
            if (null != row.getCell(5) && !"".equals(row.getCell(5).toString().trim())) {
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setAssetType(row.getCell(5).getStringCellValue());
            } else {
                error.append("第" + i + "行的资产类型为空 !\n");
            }
            //车间类型
            if (null != row.getCell(6) && !"".equals(row.getCell(6).toString().trim())) {
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setWorkShopType(row.getCell(6).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的车间类型为空 !\n");
            }*/
            //归口部门管理员编码
            if (null != row.getCell(7) && !"".equals(row.getCell(7).toString().trim())) {
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setAttachDepartAdminCode(row.getCell(7).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的归口部门管理员编码为空 !\n");
            }*/
            //归口部门管理员
            if (null != row.getCell(8) && !"".equals(row.getCell(8).toString().trim())) {
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setAttachDepartAdminName(row.getCell(8).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的归口部门管理员为空 !\n");
            }*/
            //归口部门经理编码
            if (null != row.getCell(9) && !"".equals(row.getCell(9).toString().trim())) {
                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setAttachDepartManagerCode(row.getCell(9).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的归口部门经理编码为空 !\n");
            }*/
            //归口部门经理
            if (null != row.getCell(10) && !"".equals(row.getCell(10).toString().trim())) {
                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setAttachDepartManagerName(row.getCell(10).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的归口部门经理为空 !\n");
            }*/
            //归口部门总监编码
            if (null != row.getCell(11) && !"".equals(row.getCell(11).toString().trim())) {
                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setAttachDepartMajordomoCode(row.getCell(11).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的归口部门总监编码为空 !\n");
            }*/
            //归口部门总监
            if (null != row.getCell(12) && !"".equals(row.getCell(12).toString().trim())) {
                row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setAttachDepartMajordomoName(row.getCell(12).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的归口部门总监为空 !\n");
            }*/
            //归口部门副总裁编码
            if (null != row.getCell(13) && !"".equals(row.getCell(13).toString().trim())) {
                row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setAttachDepartVpresidentCode(row.getCell(13).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的归口部门副总裁编码为空 !\n");
            }*/
            //归口部门副总裁
            if (null != row.getCell(14) && !"".equals(row.getCell(14).toString().trim())) {
                row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setAttachDepartVpresidentName(row.getCell(14).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的归口部门副总裁为空 !\n");
            }*/

            //采购员编码1
            if (null != row.getCell(15) && !"".equals(row.getCell(15).toString().trim())) {
                row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerCodea(row.getCell(15).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员编码1为空 !\n");
            }*/
            //采购员名称1
            if (null != row.getCell(16) && !"".equals(row.getCell(16).toString().trim())) {
                row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerNamea(row.getCell(16).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员名称1为空 !\n");
            }*/
            //采购员编码2
            if (null != row.getCell(17) && !"".equals(row.getCell(17).toString().trim())) {
                row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerCodeb(row.getCell(17).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员编码2为空 !\n");
            }*/
            //采购员名称2
            if (null != row.getCell(18) && !"".equals(row.getCell(18).toString().trim())) {
                row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerNameb(row.getCell(18).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员名称2为空 !\n");
            }*/
            //采购员编码3
            if (null != row.getCell(19) && !"".equals(row.getCell(19).toString().trim())) {
                row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerCodec(row.getCell(19).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员编码3为空 !\n");
            }*/
            //采购员名称3
            if (null != row.getCell(20) && !"".equals(row.getCell(20).toString().trim())) {
                row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerNamec(row.getCell(20).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员名称3为空 !\n");
            }*/
            //采购员编码4
            if (null != row.getCell(21) && !"".equals(row.getCell(21).toString().trim())) {
                row.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerCoded(row.getCell(21).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员编码4为空 !\n");
            }*/
            //采购员名称4
            if (null != row.getCell(22) && !"".equals(row.getCell(22).toString().trim())) {
                row.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerNamed(row.getCell(22).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员名称4为空 !\n");
            }*/
            //采购员编码5
            if (null != row.getCell(23) && !"".equals(row.getCell(23).toString().trim())) {
                row.getCell(23).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerCodee(row.getCell(23).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员编码5为空 !\n");
            }*/
            //采购员名称5
            if (null != row.getCell(24) && !"".equals(row.getCell(24).toString().trim())) {
                row.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerNamee(row.getCell(24).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员名称5为空 !\n");
            }*/
            //采购员编码6
            if (null != row.getCell(25) && !"".equals(row.getCell(25).toString().trim())) {
                row.getCell(25).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerCodef(row.getCell(25).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员编码6为空 !\n");
            }*/
            //采购员名称6
            if (null != row.getCell(26) && !"".equals(row.getCell(26).toString().trim())) {
                row.getCell(26).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerNamef(row.getCell(26).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员名称6为空 !\n");
            }*/
            //采购员编码7
            if (null != row.getCell(27) && !"".equals(row.getCell(27).toString().trim())) {
                row.getCell(27).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerCodeg(row.getCell(27).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员编码7为空 !\n");
            }*/
            //采购员名称7
            if (null != row.getCell(28) && !"".equals(row.getCell(28).toString().trim())) {
                row.getCell(28).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerNameg(row.getCell(28).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员名称7为空 !\n");
            }*/
            //采购员编码8
            if (null != row.getCell(29) && !"".equals(row.getCell(29).toString().trim())) {
                row.getCell(29).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerCodeh(row.getCell(29).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员编码8为空 !\n");
            }*/
            //采购员名称8
            if (null != row.getCell(30) && !"".equals(row.getCell(30).toString().trim())) {
                row.getCell(30).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setBuyerNameh(row.getCell(30).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购员名称8为空 !\n");
            }*/
            //采购经理编码
            if (null != row.getCell(31) && !"".equals(row.getCell(31).toString().trim())) {
                row.getCell(31).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setPurchasingManagerCode(row.getCell(31).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购经理编码为空 !\n");
            }*/
            //采购经理
            if (null != row.getCell(32) && !"".equals(row.getCell(32).toString().trim())) {
                row.getCell(32).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setPurchasingManagerName(row.getCell(32).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的采购经理为空 !\n");
            }*/
            //二级部门负责人编码
            if (null != row.getCell(33) && !"".equals(row.getCell(33).toString().trim())) {
                row.getCell(33).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setTwoDepartmentHeadCode(row.getCell(33).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的二级部门负责人编码为空 !\n");
            }*/
            //二级部门负责人
            if (null != row.getCell(34) && !"".equals(row.getCell(34).toString().trim())) {
                row.getCell(34).setCellType(Cell.CELL_TYPE_STRING);
                attachConfig.setTwoDepartmentHeadName(row.getCell(34).getStringCellValue().trim());
            } /*else {
                error.append("第" + i + "行的二级部门负责人为空 !\n");
            }*/

            attachConfig.setStatus(STATUS.YES_ZERO.getStatus());
            attachConfig.setUpdateBy(userCode);
            attachConfig.setUpdateUserName(userName);
            //判断成本中心、公司编码是否存在
            if (StringUtils.isBlank(error.toString())) {
                error = checkImportInfo(error, i, attachConfig);
            }
            if (StringUtils.isBlank(error.toString())) {
                if (attchConfigService.getAttchConfigNumByImport(attachConfig) > 0) {
                    updateAttachConfigList.add(attachConfig);
                } else {
                    attachConfig.setCreateBy(userCode);
                    attachConfig.setCreateUserName(userName);
                    addAttachConfigList.add(attachConfig);
                }
            }
        }
        if (StringUtils.isBlank(error.toString())) {
            String result = "";
            try {
                attchConfigService.addOrUpdateAttchConfig(addAttachConfigList, updateAttachConfigList);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
                result += "归口/负责人维护失败 !\n";
            }
            if (StringUtils.isBlank(result)) {
                jsonMap.put("success", true);
            } else {
                jsonMap.put("errorNull", result);
            }
        } else {
            String errorNull = error.toString();
            if (errorNull.length() > 200) {
                errorNull = errorNull.substring(0, 200);
                errorNull = errorNull.substring(0, errorNull.lastIndexOf("!") + 1);
                errorNull = errorNull + "\n......";
            }
            jsonMap.put("errorNull", errorNull);
        }
        try {
            response.setContentType("text/html; charset=utf-8");
//			response.getWriter().write(JSONObject.fromObject(jsonMap).toString());
            response.getWriter().write(JSONObject.fromObject(jsonMap).toString() + "<script>document.domain='opple.com'</script>");
            response.getWriter().flush();
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return null;
    }

    private StringBuilder checkImportInfo(StringBuilder error, int i, AttchConfig attachConfig) {
        if (deptCostCenterService.checkCompanyCode(attachConfig.getCompanyCode()) == 0) {
            error.append("第" + i + "行的公司编码不存在 !\n");
        }
        if (deptCostCenterService.checkCostCenter(attachConfig.getCostcenterCode()) == 0) {
            error.append("第" + i + "行的成本中心不存在 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getAttachDepartAdminCode()) && StringUtils.isNotBlank(attachConfig.getAttachDepartAdminName())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getAttachDepartAdminCode(), attachConfig.getAttachDepartAdminName()) == 0) {
                error.append("第" + i + "行的归口部门管理员不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getAttachDepartAdminCode()) || StringUtils.isNotBlank(attachConfig.getAttachDepartAdminName())) {
            error.append("第" + i + "行的归口部门管理员信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getAttachDepartManagerCode()) && StringUtils.isNotBlank(attachConfig.getAttachDepartManagerName())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getAttachDepartManagerCode(), attachConfig.getAttachDepartManagerName()) == 0) {
                error.append("第" + i + "行的归口部门经理不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getAttachDepartManagerCode()) || StringUtils.isNotBlank(attachConfig.getAttachDepartManagerName())) {
            error.append("第" + i + "行的归口部门经理信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getAttachDepartMajordomoCode()) && StringUtils.isNotBlank(attachConfig.getAttachDepartMajordomoName())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getAttachDepartMajordomoCode(), attachConfig.getAttachDepartMajordomoName()) == 0) {
                error.append("第" + i + "行的归口部门总监不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getAttachDepartMajordomoCode()) || StringUtils.isNotBlank(attachConfig.getAttachDepartMajordomoName())) {
            error.append("第" + i + "行的归口部门总监信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getAttachDepartVpresidentCode()) && StringUtils.isNotBlank(attachConfig.getAttachDepartVpresidentName())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getAttachDepartVpresidentCode(), attachConfig.getAttachDepartVpresidentName()) == 0) {
                error.append("第" + i + "行的归口部门副总裁不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getAttachDepartVpresidentCode()) || StringUtils.isNotBlank(attachConfig.getAttachDepartVpresidentName())) {
            error.append("第" + i + "行的归口部门副总裁信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getBuyerCodea()) && StringUtils.isNotBlank(attachConfig.getBuyerNamea())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getBuyerCodea(), attachConfig.getBuyerNamea()) == 0) {
                error.append("第" + i + "行的采购员1不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getBuyerCodea()) || StringUtils.isNotBlank(attachConfig.getBuyerNamea())) {
            error.append("第" + i + "行的采购员1信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getBuyerCodeb()) && StringUtils.isNotBlank(attachConfig.getBuyerNameb())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getBuyerCodeb(), attachConfig.getBuyerNameb()) == 0) {
                error.append("第" + i + "行的采购员2不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getBuyerCodeb()) || StringUtils.isNotBlank(attachConfig.getBuyerNameb())) {
            error.append("第" + i + "行的采购员2信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getBuyerCodec()) && StringUtils.isNotBlank(attachConfig.getBuyerNamec())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getBuyerCodec(), attachConfig.getBuyerNamec()) == 0) {
                error.append("第" + i + "行的采购员3不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getBuyerCodec()) || StringUtils.isNotBlank(attachConfig.getBuyerNamec())) {
            error.append("第" + i + "行的采购员4信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getBuyerCoded()) && StringUtils.isNotBlank(attachConfig.getBuyerNamed())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getBuyerCoded(), attachConfig.getBuyerNamed()) == 0) {
                error.append("第" + i + "行的采购员4不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getBuyerCoded()) || StringUtils.isNotBlank(attachConfig.getBuyerNamed())) {
            error.append("第" + i + "行的采购员4信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getBuyerCodee()) && StringUtils.isNotBlank(attachConfig.getBuyerNamee())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getBuyerCodee(), attachConfig.getBuyerNamee()) == 0) {
                error.append("第" + i + "行的采购员5不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getBuyerCodee()) || StringUtils.isNotBlank(attachConfig.getBuyerNamee())) {
            error.append("第" + i + "行的采购员5信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getBuyerCodef()) && StringUtils.isNotBlank(attachConfig.getBuyerNamef())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getBuyerCodef(), attachConfig.getBuyerNamef()) == 0) {
                error.append("第" + i + "行的采购员6不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getBuyerCodef()) || StringUtils.isNotBlank(attachConfig.getBuyerNamef())) {
            error.append("第" + i + "行的采购员6信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getBuyerCodeg()) && StringUtils.isNotBlank(attachConfig.getBuyerNameg())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getBuyerCodeg(), attachConfig.getBuyerNameg()) == 0) {
                error.append("第" + i + "行的采购员7不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getBuyerCodeg()) || StringUtils.isNotBlank(attachConfig.getBuyerNameg())) {
            error.append("第" + i + "行的采购员7信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getBuyerCodeh()) && StringUtils.isNotBlank(attachConfig.getBuyerNameh())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getBuyerCodeh(), attachConfig.getBuyerNameh()) == 0) {
                error.append("第" + i + "行的采购员8不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getBuyerCodeh()) || StringUtils.isNotBlank(attachConfig.getBuyerNameh())) {
            error.append("第" + i + "行的采购员8信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getPurchasingManagerCode()) && StringUtils.isNotBlank(attachConfig.getPurchasingManagerName())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getPurchasingManagerCode(), attachConfig.getPurchasingManagerName()) == 0) {
                error.append("第" + i + "行的采购经理不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getPurchasingManagerCode()) || StringUtils.isNotBlank(attachConfig.getPurchasingManagerName())) {
            error.append("第" + i + "行的采购经理信息不全 !\n");
        }

        if (StringUtils.isNotBlank(attachConfig.getTwoDepartmentHeadCode()) && StringUtils.isNotBlank(attachConfig.getTwoDepartmentHeadName())) {
            if (userInfoService.countUserByCodeAndName(attachConfig.getTwoDepartmentHeadCode(), attachConfig.getTwoDepartmentHeadName()) == 0) {
                error.append("第" + i + "行的二级部门负责人不存在 !\n");
            }
        } else if (StringUtils.isNotBlank(attachConfig.getTwoDepartmentHeadCode()) || StringUtils.isNotBlank(attachConfig.getTwoDepartmentHeadName())) {
            error.append("第" + i + "行的二级部门负责人信息不全 !\n");
        }

        return error;
    }


    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, AttchConfig attachConfig) {
        attachConfig = getQueryCriteria(attachConfig);
        List<AttchConfig> attachConfigList = attchConfigService.exportAttchConfigList(attachConfig);

        String fileName = "归口负责人信息表.xlsx";
        String sheetName = "归口负责人信息表";
        int[] columnWidths = new int[]{10, 10, 10};
        Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
        data.put(STATUS.NO_ONE.getStatus(), new Object[]{"公司编码", "公司名称", "成本中心编码", "成本中心", "平台", "资产类型", "车间类型", "归口部门管理员编码",
                "归口部门管理员", "归口部门经理编码", "归口部门经理", "归口部门总监编码", "归口部门总监", "归口部门副总裁编码",
                "归口部门副总裁", "采购员编码1", "采购员名称1", "采购员编码2", "采购员名称2", "采购员编码3", "采购员名称3",
                "采购员编码4", "采购员名称4", "采购员编码5", "采购员名称5", "采购员编码6", "采购员名称6", "采购员编码7", "采购员名称7",
                "采购员编码8", "采购员名称8", "采购经理编码", "采购经理", "二级部门负责人编码", "二级部门负责人名称", "数据状态"});
        int dataIdx = 2;
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder companyCode = null;
        StringBuilder companyName = null;
        StringBuilder costcenterCode = null;
        StringBuilder costcenterName = null;
        StringBuilder platform = null;
        StringBuilder assetType = null;
        StringBuilder workShopType = null;
        StringBuilder attachDepartAdminCode = null;
        StringBuilder attachDepartAdminName = null;
        StringBuilder attachDepartManagerCode = null;
        StringBuilder attachDepartManagerName = null;
        StringBuilder attachDepartMajordomoCode = null;
        StringBuilder attachDepartMajordomoName = null;
        StringBuilder attachDepartVpresidentCode = null;
        StringBuilder attachDepartVpresidentName = null;
        StringBuilder buyerCodea = null;
        StringBuilder buyerNamea = null;
        StringBuilder buyerCodeb = null;
        StringBuilder buyerNameb = null;
        StringBuilder buyerCodec = null;
        StringBuilder buyerNamec = null;
        StringBuilder buyerCoded = null;
        StringBuilder buyerNamed = null;
        StringBuilder buyerCodee = null;
        StringBuilder buyerNamee = null;
        StringBuilder buyerCodef = null;
        StringBuilder buyerNamef = null;
        StringBuilder buyerCodeg = null;
        StringBuilder buyerNameg = null;
        StringBuilder buyerCodeh = null;
        StringBuilder buyerNameh = null;
        StringBuilder purchasingManagerCode = null;
        StringBuilder purchasingManagerName = null;
        StringBuilder twoDepartmentHeadCode = null;
        StringBuilder twoDepartmentHeadName = null;
        StringBuilder status = null;
        for (AttchConfig attchConfig : attachConfigList) {
            companyCode = new StringBuilder(attchConfig.getCompanyCode() == null ? "" : attchConfig.getCompanyCode());
            companyName = new StringBuilder(attchConfig.getCompanyName() == null ? "" : attchConfig.getCompanyName());
            costcenterCode = new StringBuilder(attchConfig.getCostcenterCode() == null ? "" : attchConfig.getCostcenterCode());
            costcenterName = new StringBuilder(attchConfig.getCostcenterName() == null ? "" : attchConfig.getCostcenterName());
            platform = new StringBuilder(attchConfig.getPlatform() == null ? "" : attchConfig.getPlatform());
            assetType = new StringBuilder(attchConfig.getAssetType() == null ? "" : attchConfig.getAssetType());
            workShopType = new StringBuilder(attchConfig.getWorkShopType() == null ? "" : attchConfig.getWorkShopType());
            attachDepartAdminCode = new StringBuilder(attchConfig.getAttachDepartAdminCode() == null ? "" : attchConfig.getAttachDepartAdminCode());
            attachDepartAdminName = new StringBuilder(attchConfig.getAttachDepartAdminName() == null ? "" : attchConfig.getAttachDepartAdminName());
            attachDepartManagerCode = new StringBuilder(attchConfig.getAttachDepartManagerCode() == null ? "" : attchConfig.getAttachDepartManagerCode());
            attachDepartManagerName = new StringBuilder(attchConfig.getAttachDepartManagerName() == null ? "" : attchConfig.getAttachDepartManagerName());
            attachDepartMajordomoCode = new StringBuilder(attchConfig.getAttachDepartMajordomoCode() == null ? "" : attchConfig.getAttachDepartMajordomoCode());
            attachDepartMajordomoName = new StringBuilder(attchConfig.getAttachDepartMajordomoName() == null ? "" : attchConfig.getAttachDepartMajordomoName());
            attachDepartVpresidentCode = new StringBuilder(attchConfig.getAttachDepartVpresidentCode() == null ? "" : attchConfig.getAttachDepartVpresidentCode());
            attachDepartVpresidentName = new StringBuilder(attchConfig.getAttachDepartVpresidentName() == null ? "" : attchConfig.getAttachDepartVpresidentName());
            buyerCodea = new StringBuilder(attchConfig.getBuyerCodea() == null ? "" : attchConfig.getBuyerCodea());
            buyerNamea = new StringBuilder(attchConfig.getBuyerNamea() == null ? "" : attchConfig.getBuyerNamea());
            buyerCodeb = new StringBuilder(attchConfig.getBuyerCodeb() == null ? "" : attchConfig.getBuyerCodeb());
            buyerNameb = new StringBuilder(attchConfig.getBuyerNameb() == null ? "" : attchConfig.getBuyerNameb());
            buyerCodec = new StringBuilder(attchConfig.getBuyerCodec() == null ? "" : attchConfig.getBuyerCodec());
            buyerNamec = new StringBuilder(attchConfig.getBuyerNamec() == null ? "" : attchConfig.getBuyerNamec());
            buyerCoded = new StringBuilder(attchConfig.getBuyerCoded() == null ? "" : attchConfig.getBuyerCoded());
            buyerNamed = new StringBuilder(attchConfig.getBuyerNamed() == null ? "" : attchConfig.getBuyerNamed());
            buyerCodee = new StringBuilder(attchConfig.getBuyerCodee() == null ? "" : attchConfig.getBuyerCodee());
            buyerNamee = new StringBuilder(attchConfig.getBuyerNamee() == null ? "" : attchConfig.getBuyerNamee());
            buyerCodef = new StringBuilder(attchConfig.getBuyerCodef() == null ? "" : attchConfig.getBuyerCodef());
            buyerNamef = new StringBuilder(attchConfig.getBuyerNamef() == null ? "" : attchConfig.getBuyerNamef());
            buyerCodeg = new StringBuilder(attchConfig.getBuyerCodeg() == null ? "" : attchConfig.getBuyerCodeg());
            buyerNameg = new StringBuilder(attchConfig.getBuyerNameg() == null ? "" : attchConfig.getBuyerNameg());
            buyerCodeh = new StringBuilder(attchConfig.getBuyerCodeh() == null ? "" : attchConfig.getBuyerCodeh());
            buyerNameh = new StringBuilder(attchConfig.getBuyerNameh() == null ? "" : attchConfig.getBuyerNameh());
            purchasingManagerCode = new StringBuilder(attchConfig.getPurchasingManagerCode() == null ? "" : attchConfig.getPurchasingManagerCode());
            purchasingManagerName = new StringBuilder(attchConfig.getPurchasingManagerName() == null ? "" : attchConfig.getPurchasingManagerName());
            twoDepartmentHeadCode = new StringBuilder(attchConfig.getTwoDepartmentHeadCode() == null ? "" : attchConfig.getTwoDepartmentHeadCode());
            twoDepartmentHeadName = new StringBuilder(attchConfig.getTwoDepartmentHeadName() == null ? "" : attchConfig.getTwoDepartmentHeadName());
            if (StringUtils.isNotBlank(attchConfig.getStatus())) {
                if (STATUS.YES_ZERO.getStatus().equals(attchConfig.getStatus())) {
                    status = new StringBuilder(STATUS.EFFECTIVE.getStatus());
                } else if (STATUS.NO_ONE.getStatus().equals(attchConfig.getStatus())) {
                    status = new StringBuilder(STATUS.INVALID.getStatus());
                } else {
                    status = new StringBuilder(attchConfig.getStatus());
                }
            } else {
                status = new StringBuilder("");
            }

            Object[] newRow = new Object[]{
                    companyCode.toString(),
                    companyName.toString(),
                    costcenterCode.toString(),
                    costcenterName.toString(),
                    platform.toString(),
                    assetType.toString(),
                    workShopType.toString(),
                    attachDepartAdminCode.toString(),
                    attachDepartAdminName.toString(),
                    attachDepartManagerCode.toString(),
                    attachDepartManagerName.toString(),
                    attachDepartMajordomoCode.toString(),
                    attachDepartMajordomoName.toString(),
                    attachDepartVpresidentCode.toString(),
                    attachDepartVpresidentName.toString(),
                    buyerCodea.toString(),
                    buyerNamea.toString(),
                    buyerCodeb.toString(),
                    buyerNameb.toString(),
                    buyerCodec.toString(),
                    buyerNamec.toString(),
                    buyerCoded.toString(),
                    buyerNamed.toString(),
                    buyerCodee.toString(),
                    buyerNamee.toString(),
                    buyerCodef.toString(),
                    buyerNamef.toString(),
                    buyerCodeg.toString(),
                    buyerNameg.toString(),
                    buyerCodeh.toString(),
                    buyerNameh.toString(),
                    purchasingManagerCode.toString(),
                    purchasingManagerName.toString(),
                    twoDepartmentHeadCode.toString(),
                    twoDepartmentHeadName.toString(),
                    status.toString()
            };
            data.put("" + dataIdx++, newRow);
        }
        try {
            ExportExcle.generateExcel(response, data, fileName, sheetName, columnWidths);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
    }

    /**
     * 查询所有公司
     *
     * @return
     */
    @RequestMapping("/searchAllCompany")
    @ResponseBody
    public List<UserInfo> searchAllCompany() {
        List<UserInfo> userInfos = userInfoService.searchAllCompany();
        return userInfos;
    }
}
