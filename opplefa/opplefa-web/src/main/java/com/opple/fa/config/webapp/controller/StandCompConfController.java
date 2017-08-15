package com.opple.fa.config.webapp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opple.fa.config.model.STATUS;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opple.fa.config.entity.StandardComputerConfig;
import com.opple.fa.config.service.StandardComputerConfigService;
import com.opple.fa.utils.StringDataCas;
import com.opple.security.SessionSecurityConstants;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

/**
 * 标准电脑配置
 */
@Controller
@RequestMapping("/config/standCompConf")
public class StandCompConfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandCompConfController.class);
    private static final Map<String, String> COLUMN_MAP = new HashMap<String, String>();
    @Resource
    private StandardComputerConfigService standardComputerConfigService;

    /*重复性判断*/
    public static StringBuilder arrayUnique(List<String> a, List<String> list, int index) {
        // array_unique
        StringBuilder sb = new StringBuilder("第");
        StringBuilder sbc = new StringBuilder("第");
        for (int i = 0; i < a.size(); i++) {
            sbc.append(StringDataCas.notEmpty(a.get(i)) ? "" : sbc.append(i + 1).append(","));
            if (list.contains(a.get(i))) {
                sb.append(i + 1 + index).append(",");
            } else {
                list.add(a.get(i));
            }
        }
        if ("第".equals(sb.toString())) {
            sb = null;
        } else {
            return sb.append("行编码在导入的excel输入数据中为重复输入的值.").append(sbc == null ? "" : sbc.toString());
        }
        if ("第".equals(sbc.toString())) {
            sbc = null;
        } else {
            return sbc.append("行编码不允许为空").append(sb == null ? "" : sb.toString());
        }
        return null;
//	    return (String[])list.toArray(new String[list.size()]);  
    }

    /**
     * 添加前准备
     *
     * @return
     */
    @RequestMapping("/addReady")
    public String addReady() {
        return "config/stand_comp_conf_add";

    }

    /**
     * 导入前准备
     *
     * @return
     */
    @RequestMapping("/importReady")
    public String importReady() {
        return "config/stand_comp_conf_import";

    }

    private ExecuteResult<List<StandardComputerConfig>> batchAdd(ExecuteResult<List<StandardComputerConfig>> result, List<String> codes, HttpServletRequest request) {
        List<StandardComputerConfig> standardComputerConfigList = new ArrayList<StandardComputerConfig>();
        List<String> list = new LinkedList<String>();
        String loginUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        if (codes.size() == 0) {
            result.setSuccessMessage("请至少输入一行资产编码不为空的数据!");
            return result;
        }
        StringBuilder sb = arrayUnique(codes, list, 0); //去掉相同assetsCode
        if (sb != null) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        List<String> sameCodes = standardComputerConfigService.selectExistCodes(list);
        if (sameCodes.size() > 0) {
            result.setSuccessMessage("数据操作不允许，操作未执行！以下编码在数据库已经存在：" + org.apache.commons.lang.StringUtils.join(list.toArray(), ","));
            return result;
        }
        String[] assetsCodes = request.getParameterValues("assetsCode");
        String[] assetsNames = request.getParameterValues("assetsName");
        String[] specificationParameters = request.getParameterValues("specificationParameter");
        String[] statuss = request.getParameterValues("status");
        String[] cmemos = request.getParameterValues("cmemo");
        sb = new StringBuilder("");
        for (int i = 0; i < assetsCodes.length; i++) {
            String assetsCode = null == assetsCodes[i] ? null : assetsCodes[i].trim();
            String assetsName = null == assetsNames[i] ? null : assetsNames[i].trim();
            String specificationParameter = null == specificationParameters[i] ? null : specificationParameters[i].trim();
            String status = null == statuss[i] ? null : statuss[i].trim();
            String cmemo = null == cmemos[i] ? null : cmemos[i].trim();

            StandardComputerConfig standardComputerConfig = new StandardComputerConfig();
            standardComputerConfig.setAssetsCode(assetsCode);
            standardComputerConfig.setAssetsName(assetsName);
            standardComputerConfig.setSpecificationParameter(specificationParameter);
            standardComputerConfig.setCmemo(cmemo);
            standardComputerConfig.setStatus(status);
            standardComputerConfig.setCreateBy(loginUserName);
            standardComputerConfig.setCreateByCode(loginUserCode);
            standardComputerConfigList.add(standardComputerConfig);
            sb = StringDataCas.checkFieldCount(standardComputerConfig, sb);
        }
        if (StringDataCas.notEmpty(sb)) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        try {
            result = standardComputerConfigService.saveBatch(standardComputerConfigList);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            result.setSuccessMessage("添加失败!");
        }
        return result;
    }

    /**
     * 添加
     *
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ExecuteResult<List<StandardComputerConfig>> add(HttpServletRequest request) {
        ExecuteResult<List<StandardComputerConfig>> result = new ExecuteResult<List<StandardComputerConfig>>();
        result.setSuccessMessage("操作失败");
        String[] assetsCodes = request.getParameterValues("assetsCode");
        if (assetsCodes.length == 0 || !StringDataCas.notEmpty(assetsCodes[0])) {
            result.setSuccessMessage("资产编码不能为空");
            return result;
        }
        ArrayList<String> codes = new ArrayList<String>();
        for (int i = 0; i < assetsCodes.length; i++) {
            codes.add(assetsCodes[i]);
        }
        result = batchAdd(result, codes, request);
        return result;

    }

    /**
     * 删除
     *
     * @param assetsCodes
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ExecuteResult<StandardComputerConfig> delete(@RequestParam(value = "assetsCodes[]", required = false) List<String> assetsCodes, HttpServletRequest req) {
        ExecuteResult<StandardComputerConfig> result = new ExecuteResult<StandardComputerConfig>();
        if (null == assetsCodes) {
            result.setSuccessMessage("请选择要删除的对象");
            return result;
        }
        StandardComputerConfig standardComputerConfig = new StandardComputerConfig();
        String updateBy = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String updateByCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        standardComputerConfig.setUpdateBy(updateBy);
        standardComputerConfig.setUpdateByCode(updateByCode);
        standardComputerConfig.setAssetsCodes(assetsCodes);
        standardComputerConfig.setStatus(STATUS.NO.getStatus());
        result = standardComputerConfigService.delete(standardComputerConfig);
        return result;

    }

    /**
     * 修改前显示
     *
     * @return
     */
    @RequestMapping("/updateReady")
    public String update(String assetsCode, Model model, boolean flag) {
        StandardComputerConfig assets = standardComputerConfigService.get(assetsCode);
        model.addAttribute("assets", assets);
        return flag ? "config/stand_comp_conf_update" : "config/stand_comp_conf_show";

    }

    /**
     * 修改
     *
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ExecuteResult<StandardComputerConfig> update(StandardComputerConfig standardComputerConfig, HttpServletRequest req) {
        String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        standardComputerConfig.setUpdateBy(loginUserName);
        standardComputerConfig.setUpdateByCode(loginUserCode);
        ExecuteResult<StandardComputerConfig> result = null;
        try {
            result = standardComputerConfigService.update(standardComputerConfig);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            result.setSuccessMessage("更新失败!");
        }
        return result;

    }

    @RequestMapping("/get")
    @ResponseBody
    public StandardComputerConfig get(String assetsCode) {
        StandardComputerConfig standardComputerConfig = standardComputerConfigService.get(assetsCode);
        return standardComputerConfig;
    }

    /**
     * 分页查询
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @return
     */
    @RequestMapping("/searchPagerList")
    public String searchStandCompConfList(Model model, HttpServletRequest req, Long offset, Long pageSize, StandardComputerConfig standardComputerConfig) {
        Pager<StandardComputerConfig> pager = new Pager<StandardComputerConfig>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<StandardComputerConfig> standardComputerConfigPager = standardComputerConfigService.searchPagerList(pager, standardComputerConfig);
        standardComputerConfigPager.setUri(req.getRequestURI());
        model.addAttribute("standardComputerConfigPager", standardComputerConfigPager);
        model.addAttribute("param", standardComputerConfig);
        return "config/stand_comp_conf_list";
    }


    @RequestMapping(value = "/exportFile", method = RequestMethod.GET)
    @ResponseBody
    public void exportFile(HttpServletRequest request,
                           HttpServletResponse response, StandardComputerConfig standardComputerConfig) throws Exception {
        byte[] fileNameByte = ("标准电脑配置.xlsx").getBytes("GBK");
        String filename = new String(fileNameByte, "ISO8859-1");

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("标准电脑配置");
        XSSFRow row = sheet.createRow((int) 0);
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setFillBackgroundColor(HSSFColor.GOLD.index);
        style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setDataFormat(wb.createDataFormat().getFormat(
                "yyyy-MM-dd hh:mm:ss"));
        /*// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("标准电脑配置");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setFillBackgroundColor(HSSFColor.GOLD.index);
		style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setDataFormat(wb.createDataFormat().getFormat(
                "yyyy-MM-dd hh:mm:ss"));*/

        String[] title = {"资产编码", "资产名称", "参数信息", "创建人", "创建时间", "最后一次更新人", "最后一次更新时间", "备注", "状态"};
        List<String> excelHead = Arrays.asList(title);

//		HSSFCell cell = null;
        XSSFCell cell = null;
        // excel头
        for (int i = 0; i < excelHead.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHead.get(i));
            cell.setCellStyle(style);
        }

        List<StandardComputerConfig> result = standardComputerConfigService.getList(standardComputerConfig);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String status = "";
        for (int i = 0; i < result.size(); i++) {
            row = sheet.createRow((int) i + 1);
            StandardComputerConfig data = result.get(i);
            // 创建单元格，并设置值
            int j = 0;
            insertCellx(row, j++, data.getAssetsCode() == null ? "" : data.getAssetsCode());
            insertCellx(row, j++, data.getAssetsName() == null ? "" : data.getAssetsName());
            insertCellx(row, j++, data.getSpecificationParameter() == null ? "" : data.getSpecificationParameter());
            insertCellx(row, j++, data.getCreateBy() == null ? "" : data.getCreateBy());
            insertCellx(row, j++, data.getCreateDate() == null ? "" : sdf.format(data.getCreateDate()));
            insertCellx(row, j++, data.getUpdateBy() == null ? "" : data.getUpdateBy());
            insertCellx(row, j++, data.getUpdateDate() == null ? "" : sdf.format(data.getUpdateDate()));
            insertCellx(row, j++, data.getCmemo() == null ? "" : data.getCmemo());
            status = "";
            if (StringUtils.isNotBlank(data.getStatus())) {
                if (STATUS.YES.getStatus().equals(data.getStatus())) {
                    status = STATUS.EFFECTIVE.getStatus();
                } else if (STATUS.NO.getStatus().equals(data.getStatus())) {
                    status = STATUS.INVALID.getStatus();
                } else {
                    status = data.getStatus();
                }
            }
            insertCellx(row, j++, status);
        }

        wb.write(out);
        byte[] bytes = out.toByteArray();

        response.setContentType("application/x-msdownload");
        response.setContentLength(bytes.length);
        response.setHeader("Content-Disposition", "attachment;filename="
                + filename);
        response.getOutputStream().write(bytes);
    }


    private void insertCellx(XSSFRow row, int i, Object object) {
        if (object == null) {
            row.createCell(i).setCellValue("");
        } else {
            row.createCell(i).setCellValue(object.toString());
        }

    }

    /**
     * 导入
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/importExcel")
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

        List<StandardComputerConfig> addList = new ArrayList<StandardComputerConfig>();
        List<StandardComputerConfig> updateList = new ArrayList<StandardComputerConfig>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            StandardComputerConfig standardComputerConfig = new StandardComputerConfig();

            //资产编码
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                standardComputerConfig.setAssetsCode(row.getCell(0).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的资产编码为空 !\n";
            }
            //资产名称
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                standardComputerConfig.setAssetsName(row.getCell(1).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的资产名称为空 !\n";
            }

            //参数信息
            if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                standardComputerConfig.setSpecificationParameter(row.getCell(2).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的参数信息为空 !\n";
            }

            //备注
            if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                standardComputerConfig.setCmemo(row.getCell(3).getStringCellValue().trim());
            }

            // 数据状态
            if (null != row.getCell(4) && !"".equals(row.getCell(4).toString().trim())) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                String status = row.getCell(4).getStringCellValue().trim();
                if (STATUS.EFFECTIVE.getStatus().equals(status)) {
                    standardComputerConfig.setStatus(STATUS.YES.getStatus());
                } else if (STATUS.INVALID.getStatus().equals(status)) {
                    standardComputerConfig.setStatus(STATUS.NO.getStatus());
                } else {
                    errorNull = errorNull + "第" + i + "行的状态不合法 !\n";
                }
            } else {
                standardComputerConfig.setStatus(STATUS.YES.getStatus());
            }

            standardComputerConfig.setUpdateByCode(userCode);
            standardComputerConfig.setUpdateBy(userName);
            standardComputerConfig.setUpdateDate(new Date());

            if (errorNull == "") {
                if (standardComputerConfigService.checkUnitCode(standardComputerConfig.getAssetsCode()) > 0) {
                    updateList.add(standardComputerConfig);
                } else {
                    standardComputerConfig.setCreateByCode(userCode);
                    standardComputerConfig.setCreateBy(userName);
                    standardComputerConfig.setCreateDate(new Date());
                    addList.add(standardComputerConfig);
                }
            }
        }
        if (updateList.size() == 0 && addList.size() == 0) {
            errorNull = errorNull + "表中无数据 !\n";
        }
        if (StringUtils.isBlank(errorNull)) {
            String result = standardComputerConfigService.addOrUpdateUnit(addList, updateList);
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
            response.getWriter().write(JSONObject.fromObject(jsonMap).toString() + "<script>document.domain='opple.com'</script>");
            response.getWriter().flush();
        } catch (IOException e1) {
            LOGGER.error("IOException", e1);
        }
        return null;
    }

}