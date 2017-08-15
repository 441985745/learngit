package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.BudgetAssetstypeConfig;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.BudgetAssetstypeConfigService;
import com.opple.fa.utils.StringDataCas;
import com.opple.security.SessionSecurityConstants;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 预算资产类型
 */
@Controller
@RequestMapping("/config/budgetAssetstypeConfig")
public class BudgetAssetstypeConfigController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetAssetstypeConfigController.class);
    //private static final Map<String, String> COLUMN_MAP = new HashMap<String, String>();
    @Resource
    private BudgetAssetstypeConfigService budgetAssetstypeConfigService;

    /*重复性判断*/
    public static StringBuilder arrayUnique(List<String> a, List<String> list, int index) {
        // array_unique
        StringBuilder sb = new StringBuilder("第");
        StringBuilder sbc = new StringBuilder("第");
        for (int i = 0; i < a.size(); i++) {
            sbc.append(StringDataCas.notEmpty(a.get(i)) ? "" : sbc.append(i + 1).append(","));    //不是空就继续，是空就记录下行数来，
            if (list.contains(a.get(i))) {
                sb.append(i + 1 + index).append(",");    //如果重复输入就记录下行数来
            } else {
                list.add(a.get(i));
            }
        }
        if ("第".equals(sb.toString())) {
            sb = null;
        } else {
            return sb.append("行编码在输入数据中为重复输入的值.").append(sbc == null ? "" : sbc.toString());
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
        return "config/budgetAssetstypeConfig/budget_assetstype_config_add";

    }


    private ExecuteResult<List<BudgetAssetstypeConfig>> batchAdd(ExecuteResult<List<BudgetAssetstypeConfig>> result, List<String> codes, HttpServletRequest request) {
        List<BudgetAssetstypeConfig> budgetAssetstypeConfigList = new ArrayList<BudgetAssetstypeConfig>();
        List<String> list = new LinkedList<String>();
        String loginUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        if (codes.size() == 0) {
            result.setSuccessMessage("请至少输入一行资产编码不为空的数据!");
            return result;
        }
        StringBuilder sb = arrayUnique(codes, list, 0); //去掉相同budgetAssetsType
        if (sb != null) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        List<String> sameCodes = budgetAssetstypeConfigService.selectExistCodes(list);
        if (sameCodes.size() > 0) {
            result.setSuccessMessage("数据操作不允许，操作未执行！以下编码在数据库已经存在：" + org.apache.commons.lang.StringUtils.join(list.toArray(), ","));
            return result;
        }
        String[] budgetAssetsTypeAssetsTypes = request.getParameterValues("budgetAssetsType");
        String[] assetsTypes = request.getParameterValues("assetsType");
        String[] statuss = request.getParameterValues("status");
        String[] memos = request.getParameterValues("memo");
        sb = new StringBuilder("");
        for (int i = 0; i < budgetAssetsTypeAssetsTypes.length; i++) {
            String budgetAssetsType = null == budgetAssetsTypeAssetsTypes[i] ? null : budgetAssetsTypeAssetsTypes[i].trim();
            String assetsType = null == assetsTypes[i] ? null : assetsTypes[i].trim();
            String status = null == statuss[i] ? null : statuss[i].trim();
            String memo = null == memos[i] ? null : memos[i].trim();

            BudgetAssetstypeConfig budgetAssetstypeConfig = new BudgetAssetstypeConfig();
            budgetAssetstypeConfig.setBudgetAssetsType(budgetAssetsType);
            budgetAssetstypeConfig.setAssetsType(assetsType);
            budgetAssetstypeConfig.setMemo(memo);
            budgetAssetstypeConfig.setStatus(status);
            budgetAssetstypeConfig.setCreateBy(loginUserName);
            budgetAssetstypeConfig.setCreateByCode(loginUserCode);
            budgetAssetstypeConfigList.add(budgetAssetstypeConfig);
            sb = StringDataCas.checkFieldCount(budgetAssetstypeConfig, sb);
        }
        if (StringDataCas.notEmpty(sb)) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        try {
            result = budgetAssetstypeConfigService.saveBatch(budgetAssetstypeConfigList);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            result.setSuccessMessage("添加失败!");
        }
        return result;
    }

    /**
     * 添加
     *
     * @param request
     * @return
     */

    @RequestMapping("/add")
    @ResponseBody
    public ExecuteResult<List<BudgetAssetstypeConfig>> add(HttpServletRequest request) {
        ExecuteResult<List<BudgetAssetstypeConfig>> result = new ExecuteResult<List<BudgetAssetstypeConfig>>();
        result.setSuccessMessage("操作失败");
        String[] budgetAssetsTypes = request.getParameterValues("budgetAssetsType");
        String[] assetsTypes = request.getParameterValues("assetsType");
        if (budgetAssetsTypes.length == 0 || !StringDataCas.notEmpty(budgetAssetsTypes[0])) {
            result.setSuccessMessage("资产编码不能为空");
            return result;
        }
        if (assetsTypes.length == 0 || !StringDataCas.notEmpty(assetsTypes[0])) {
            result.setSuccessMessage("资产编码不能为空");
            return result;
        }
        ArrayList<String> codes = new ArrayList<String>();
        for (int i = 0; i < budgetAssetsTypes.length; i++) {
            codes.add(budgetAssetsTypes[i] + assetsTypes[i]);
        }
        result = batchAdd(result, codes, request);
        return result;

    }

    /**
     * 删除
     *
     * @param ids
     * @param req
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ExecuteResult<BudgetAssetstypeConfig> delete(@RequestParam(value = "ids[]", required = false) List<String> ids, HttpServletRequest req) {
        ExecuteResult<BudgetAssetstypeConfig> result = new ExecuteResult<BudgetAssetstypeConfig>();
        if (null == ids) {
            result.setSuccessMessage("请选择要删除的对象");
            return result;
        }
        BudgetAssetstypeConfig budgetAssetstypeConfig = new BudgetAssetstypeConfig();
        String updateBy = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String updateByCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        budgetAssetstypeConfig.setUpdateBy(updateBy);
        budgetAssetstypeConfig.setUpdateByCode(updateByCode);
        budgetAssetstypeConfig.setIds(ids);
        budgetAssetstypeConfig.setStatus(STATUS.NO.getStatus());
        result = budgetAssetstypeConfigService.delete(budgetAssetstypeConfig);
        return result;

    }

    /**
     * 修改前显示
     *
     * @param id
     * @param model
     * @param flag
     * @return
     */
    @RequestMapping("/updateReady")
    public String update(String id, Model model, boolean flag) {
        BudgetAssetstypeConfig budgetAssetstypeConfig = budgetAssetstypeConfigService.get(id);
        model.addAttribute("budgetAssetstypeConfig", budgetAssetstypeConfig);
        return flag ? "config/budgetAssetstypeConfig/budget_assetstype_config_update" : "config/budgetAssetstypeConfig/budget_assetstype_config_show";

    }

    /**
     * 修改
     *
     * @param budgetAssetstypeConfig
     * @param req
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ExecuteResult<BudgetAssetstypeConfig> update(BudgetAssetstypeConfig budgetAssetstypeConfig, HttpServletRequest req) {

        String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        budgetAssetstypeConfig.setUpdateBy(loginUserName);
        budgetAssetstypeConfig.setUpdateByCode(loginUserCode);
        ExecuteResult<BudgetAssetstypeConfig> result = null;
        try {
            result = budgetAssetstypeConfigService.update(budgetAssetstypeConfig);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            result.setSuccessMessage("更新失败!");
        }
        return result;
    }

    @RequestMapping("/get")
    @ResponseBody
    public BudgetAssetstypeConfig get(String id) {
        BudgetAssetstypeConfig budgetAssetstypeConfig = budgetAssetstypeConfigService.get(id);
        return budgetAssetstypeConfig;
    }

    /**
     * 分页查询
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param budgetAssetstypeConfig
     * @return
     */
    @RequestMapping("/searchPagerList")
    public String searchStandCompConfList(Model model, HttpServletRequest req, Long offset, Long pageSize, BudgetAssetstypeConfig budgetAssetstypeConfig) {
        Pager<BudgetAssetstypeConfig> pager = new Pager<BudgetAssetstypeConfig>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<BudgetAssetstypeConfig> budgetAssetstypeConfigPager = budgetAssetstypeConfigService.searchPagerList(pager, budgetAssetstypeConfig);
        budgetAssetstypeConfigPager.setUri(req.getRequestURI());
        model.addAttribute("budgetAssetstypeConfigPager", budgetAssetstypeConfigPager);
        model.addAttribute("param", budgetAssetstypeConfig);
        return "config/budgetAssetstypeConfig/budget_assetstype_config_list";
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

        List<BudgetAssetstypeConfig> addList = new ArrayList<BudgetAssetstypeConfig>();
        List<BudgetAssetstypeConfig> updateList = new ArrayList<BudgetAssetstypeConfig>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            BudgetAssetstypeConfig budgetAssetstypeConfig = new BudgetAssetstypeConfig();

            //预算资产类型
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                budgetAssetstypeConfig.setBudgetAssetsType(row.getCell(0).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的预算资产类型为空 !\n";
            }
            //资产类型
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                budgetAssetstypeConfig.setAssetsType(row.getCell(1).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的资产类型为空 !\n";
            }

            //备注
            if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                budgetAssetstypeConfig.setMemo(row.getCell(2).getStringCellValue().trim());
            }

            // 数据状态
            if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                String status = row.getCell(3).getStringCellValue().trim();
                if (STATUS.EFFECTIVE.getStatus().equals(status)) {
                    budgetAssetstypeConfig.setStatus(STATUS.YES.getStatus());
                } else if (STATUS.INVALID.getStatus().equals(status)) {
                    budgetAssetstypeConfig.setStatus(STATUS.NO.getStatus());
                } else {
                    errorNull = errorNull + "第" + i + "行的状态不合法 !\n";
                }
            } else {
                budgetAssetstypeConfig.setStatus(STATUS.YES.getStatus());
            }

            budgetAssetstypeConfig.setUpdateByCode(userCode);
            budgetAssetstypeConfig.setUpdateBy(userName);
            budgetAssetstypeConfig.setUpdateDate(new Date());

            if (errorNull == "") {
                if (budgetAssetstypeConfigService.checkBudgetAssetType(budgetAssetstypeConfig) > 0) {
                    updateList.add(budgetAssetstypeConfig);
                } else {
                    budgetAssetstypeConfig.setCreateByCode(userCode);
                    budgetAssetstypeConfig.setCreateBy(userName);
                    budgetAssetstypeConfig.setCreateDate(new Date());
                    addList.add(budgetAssetstypeConfig);
                }
            }
        }
        if (updateList.size() == 0 && addList.size() == 0) {
            errorNull = errorNull + "表中无数据 !\n";
        }
        if (StringUtils.isBlank(errorNull)) {
            String result = budgetAssetstypeConfigService.addOrUpdateBudgetAssetType(addList, updateList);
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

    /**
     * 导出
     *
     * @param request
     * @param response
     * @param budgetAssetstypeConfig
     * @throws Exception
     */
    @RequestMapping(value = "/exportFile", method = RequestMethod.GET)
    @ResponseBody
    public void exportFile(HttpServletRequest request,
                           HttpServletResponse response, BudgetAssetstypeConfig budgetAssetstypeConfig) throws Exception {

        byte[] fileNameByte = "预算资产类型.xlsx".getBytes("GBK");
        String filename = new String(fileNameByte, "ISO8859-1");

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("预算资产类型");
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
		HSSFSheet sheet = wb.createSheet("预算资产类型");
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

        String[] title = {"预算资产类型", "资产类型", "创建人", "创建时间", "最后一次更新人", "最后一次更新时间", "备注", "状态"};
        List<String> excelHead = Arrays.asList(title);

//		HSSFCell cell = null;
        XSSFCell cell = null;
        // excel头
        for (int i = 0; i < excelHead.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHead.get(i));
            cell.setCellStyle(style);
        }

        List<BudgetAssetstypeConfig> result = budgetAssetstypeConfigService.getList(budgetAssetstypeConfig);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String status = "";
        for (int i = 0; i < result.size(); i++) {
            row = sheet.createRow((int) i + 1);
            BudgetAssetstypeConfig data = result.get(i);
            // 创建单元格，并设置值
            int j = 0;
            insertCellx(row, j++, data.getBudgetAssetsType() == null ? "" : data.getBudgetAssetsType());
            insertCellx(row, j++, data.getAssetsType() == null ? "" : data.getAssetsType());
            insertCellx(row, j++, data.getCreateBy() == null ? "" : data.getCreateBy());
            insertCellx(row, j++, data.getCreateDate() == null ? "" : sdf.format(data.getCreateDate()));
            insertCellx(row, j++, data.getUpdateBy() == null ? "" : data.getUpdateBy());
            insertCellx(row, j++, data.getUpdateDate() == null ? "" : sdf.format(data.getUpdateDate()));
            insertCellx(row, j++, data.getMemo() == null ? "" : data.getMemo());
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

//    private void insertCell(HSSFRow row, int i, Object object) {
//        if (object == null) {
//            row.createCell(i).setCellValue("");
//        } else {
//            row.createCell(i).setCellValue(object.toString());
//        }
//
//    }

    private void insertCellx(XSSFRow row, int i, Object object) {
        if (object == null) {
            row.createCell(i).setCellValue("");
        } else {
            row.createCell(i).setCellValue(object.toString());
        }

    }


    /**
     * 下载模板
     * @param request
     * @param response
     * @param model
     * @throws Exception
     */
    /*@RequestMapping( value="/downloadModel",method = RequestMethod.GET)
    public void downloadModel(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String path="/excelModel/config/预算资产类型导入模板.xlsx";
		response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        String ctxPath = Env.getProperty(Env.KEY_DYNAMIC_URL);
        String downLoadPath = ctxPath + path;
        String realName=path.substring(path.lastIndexOf("/")+1,path.length());
        long fileLength = new File(downLoadPath).length();  
        response.setContentType("application/octet-stream");  
        response.setHeader("Content-disposition", "attachment; filename=" 
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));  
        response.setHeader("Content-Length", String.valueOf(fileLength));  
  
        bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
        
        bos = new BufferedOutputStream(response.getOutputStream());  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        bis.close();  
        bos.close(); 
	}*/


}