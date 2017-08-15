package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.Areas;
import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.config.model.AREATYPE;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AreasService;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wangkaixuan on 2017/4/6.
 */
@Controller
@RequestMapping("/area")
public class AreasController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AreasController.class);
    @Resource
    private AreasService areasService;

    /**
     * 模糊查询
     *
     * @param model
     * @param request
     * @param areas
     * @return
     */
    @RequestMapping("/searchAreasList")
    public String searchAreasList(Model model, HttpServletRequest request, Long offset, Long pageSize, Areas areas) {
        areas = getQueryCriteria(areas);

        Pager<Areas> pager = new Pager<Areas>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<Areas> areasPager = areasService.searchAreasList(pager, areas);

        areasPager.setUri(request.getRequestURI());
        model.addAttribute("areasPager", areasPager);
        return "config/areas_list";
    }

    /**
     * 处理查询条件
     *
     * @param areas
     * @return
     */
    private Areas getQueryCriteria(Areas areas) {
        areas.setAreaCode(null == areas.getAreaCode() ? null : areas.getAreaCode().trim());
        areas.setAreaName(null == areas.getAreaName() ? null : areas.getAreaName().trim());
        return areas;
    }

    /**
     * 添加页面跳转
     *
     * @return
     */
    @RequestMapping("/openDialogForAdd")
    public String openDialogForAdd() {
        return "config/areas_add";
    }

    /**
     * 添加
     *
     * @param request
     * @param areas
     * @return
     */
    @RequestMapping("/api/addArea")
    @ResponseBody
    public JSONObject addArea(HttpServletRequest request, @RequestBody Areas areas) {
        Map<String, Object> map = new HashMap<String, Object>();

        Integer checkNum = areasService.getAreasNumByAreaName(areas.getAreaCode(), areas.getAreaName());
        if (checkNum > 0) {
            map.put("error", "区域编码或者区域名称已存在，换一个吧！");
            return JSONObject.fromObject(map);
        }
//        checkNum = areasService.getAreasNumByAreaName(areas.getAreaName());
//        if (checkNum > 0) {
//            map.put("error", "区域名称已存在，换一个吧！");
//            return JSONObject.fromObject(map);
//        }
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        areas.setCreateBy(userCode);
        areas.setCreateUserName(userName);
        areas.setUpdateBy(userCode);
        areas.setUpdateUserName(userName);
        try {
            areasService.addArea(areas);
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
    public String openDialogForUpdate(Model model, String areaCode) {
        Areas area = areasService.getAreaByAreaCode(areaCode);
        model.addAttribute("area", area);
        return "config/areas_update";
    }

    /**
     * 修改
     *
     * @param request
     * @param areas
     * @return
     */
    @RequestMapping("/api/updateArea")
    @ResponseBody
    public JSONObject updateArea(HttpServletRequest request, @RequestBody Areas areas) {
        Map<String, Object> map = new HashMap<String, Object>();
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        areas.setUpdateBy(userCode);
        areas.setUpdateUserName(userName);
        try {
            areasService.updateArea(areas);
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
     * @param areaCode
     * @return
     */
    @RequestMapping("/showArea")
    public String showArea(Model model, String areaCode) {
        Areas area = areasService.getAreaByAreaCode(areaCode);
        model.addAttribute("area", area);
        return "config/areas_show";
    }

    @RequestMapping("/api/deleteAreas")
    @ResponseBody
    public JSONObject deleteArea(HttpServletRequest request,
                                 @RequestParam(required = false, value = "areaCodes[]") List<String> areaCodes) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Areas> areasList = new ArrayList<Areas>();
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        for (String areaCode : areaCodes) {
            Areas areas = new Areas();
            areas.setAreaCode(areaCode);
            areas.setStatus(STATUS.NO_ONE.getStatus());
            areas.setUpdateBy(userCode);
            areas.setUpdateUserName(userName);
            areasList.add(areas);
        }
        try {
            areasService.updateStatusOfAreas(areasList);
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
        //错误信息
        String errorNull = "";
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

        List<Areas> addAreasList = new ArrayList<Areas>();
        List<Areas> updateAreasList = new ArrayList<Areas>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            Areas areas = new Areas();
            AssetsBudget assetsBudget = new AssetsBudget();

            //区域编码
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                areas.setAreaCode(row.getCell(0).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的区域编码为空 !\n";
            }
            //区域名称
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                areas.setAreaName(row.getCell(1).getStringCellValue().trim());
                assetsBudget.setProjectCode(row.getCell(1).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的区域名称为空 !\n";
            }
            // 区域类型
            if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                String areaType = row.getCell(2).getStringCellValue().trim();
                if (AREATYPE.HOME.getAreaType().equals(areaType)
                        || AREATYPE.HOME_USE.getAreaType().equals(areaType)) {
                    areas.setAreaType(AREATYPE.HOME_ZERO.getAreaType());
                } else if (AREATYPE.COMMERCIAL.getAreaType().equals(areaType)) {
                    areas.setAreaType(AREATYPE.COMMERCIAL_ONE.getAreaType());
                } else {
                    errorNull = errorNull + "第" + i + "行的区域类型不合法 !\n";
                }
            } else {
                errorNull = errorNull + "第" + i + "行的区域类型为空 !\n";
            }
            // 数据状态
            if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                String status = row.getCell(3).getStringCellValue().trim();
                if (STATUS.EFFECTIVE.getStatus().equals(status)) {
                    areas.setStatus(STATUS.YES_ZERO.getStatus());
                } else if (STATUS.INVALID.getStatus().equals(status)) {
                    areas.setStatus(STATUS.NO_ONE.getStatus());
                } else {
                    errorNull = errorNull + "第" + i + "行的状态不合法 !\n";
                }
            } else {
                areas.setStatus(STATUS.YES_ZERO.getStatus());
            }

            areas.setUpdateBy(userCode);
            areas.setUpdateUserName(userName);
            areas.setUpdateDate(new Date());

            if (errorNull == "") {
                if (areasService.getAreasNumByAreaCode(areas.getAreaCode()) > 0) {
                    updateAreasList.add(areas);
                } else {
                    areas.setCreateBy(userCode);
                    areas.setCreateUserName(userName);
                    areas.setCreateDate(new Date());
                    addAreasList.add(areas);
                }
            }
        }
        if (updateAreasList.size() == 0 && addAreasList.size() == 0) {
            errorNull = errorNull + "表中无数据 !\n";
        }
        if (StringUtils.isBlank(errorNull)) {
            String result = areasService.addOrUpdateAreas(addAreasList, updateAreasList);
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
            LOGGER.error("IOException", e1);
        }
        return null;
    }


    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, Areas areas) {
        areas = getQueryCriteria(areas);
        List<Areas> areasList = areasService.exportAreasList(areas);

        String fileName = "区域配置表.xlsx";
        String sheetName = "区域配置表";
        int[] columnWidths = new int[]{10, 10, 10};
        Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
        data.put(STATUS.NO_ONE.getStatus(), new Object[]{"区域编码", "区域名称", "区域类型", "状态", "创建人编码", "创建人", "创建时间", "维护人编码", "维护人", " 维护时间"});
        int dataIdx = 2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder areaCode = null;
        StringBuilder areaName = null;
        StringBuilder type = null;
        StringBuilder status = null;
        StringBuilder createBy = null;
        StringBuilder createUserName = null;
        StringBuilder createDate = null;
        StringBuilder updateBy = null;
        StringBuilder updateUserName = null;
        StringBuilder updateDate = null;
        for (Areas areas1 : areasList) {
            areaCode = new StringBuilder(areas1.getAreaCode() == null ? "" : areas1.getAreaCode());
            areaName = new StringBuilder(areas1.getAreaName() == null ? "" : areas1.getAreaName());

            if (StringUtils.isNotBlank(areas1.getAreaType())) {
                if (AREATYPE.HOME_ZERO.getAreaType().equals(areas1.getAreaType())) {
                    type = new StringBuilder(AREATYPE.HOME.getAreaType());
                } else if (AREATYPE.COMMERCIAL_ONE.getAreaType().equals(areas1.getAreaType())) {
                    type = new StringBuilder(AREATYPE.COMMERCIAL.getAreaType());
                } else {
                    type = new StringBuilder(areas1.getAreaType());
                }
            } else {
                type = new StringBuilder("");
            }
            if (StringUtils.isNotBlank(areas1.getStatus())) {
                if (STATUS.YES_ZERO.getStatus().equals(areas1.getStatus())) {
                    status = new StringBuilder(STATUS.EFFECTIVE.getStatus());
                } else if (STATUS.NO_ONE.getStatus().equals(areas1.getStatus())) {
                    status = new StringBuilder(STATUS.INVALID.getStatus());
                } else {
                    status = new StringBuilder(areas1.getStatus());
                }
            } else {
                status = new StringBuilder("");
            }
            createBy = new StringBuilder(areas1.getCreateBy() == null ? "" : areas1.getCreateBy());
            createUserName = new StringBuilder(areas1.getCreateUserName() == null ? "" : areas1.getCreateUserName());
            createDate = new StringBuilder(areas1.getCreateDate() == null ? "" : sdf.format(areas1.getCreateDate()));
            updateBy = new StringBuilder(areas1.getUpdateBy() == null ? "" : areas1.getUpdateBy());
            updateUserName = new StringBuilder(areas1.getUpdateUserName() == null ? "" : areas1.getUpdateUserName());
            updateDate = new StringBuilder(areas1.getUpdateDate() == null ? "" : sdf.format(areas1.getUpdateDate()));


            Object[] newRow = new Object[]{
                    areaCode.toString(),
                    areaName.toString(),
                    type.toString(),
                    status.toString(),
                    createBy.toString(),
                    createUserName.toString(),
                    createDate.toString(),
                    updateBy.toString(),
                    updateUserName.toString(),
                    updateDate.toString()
            };
            data.put("" + dataIdx++, newRow);
        }
        try {
            ExportExcle.generateExcel(response, data, fileName, sheetName, columnWidths);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }


        /*try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportAreasTemplate exportAreasTemplate = new ExportAreasTemplate();
            exportAreasTemplate.doExport(response, fileName, areasList);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }*/
    }
}
