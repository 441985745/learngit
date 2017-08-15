package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.Factory;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.FactoryService;
import com.opple.fa.utils.StringDataCas;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.service.CompanyService;
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
 * 工厂
 */
@Controller
@RequestMapping("/config/factory")
public class FactoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FactoryController.class);
    private static final Map<String, String> COLUMN_MAP = new HashMap<String, String>();
    @Resource
    private FactoryService factoryService;
    @Resource
    private CompanyService companyService;

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
     * @return
     */
    @RequestMapping("/addReady")
    public String addReady() {
        return "config/factory_add";

    }

    /**
     * 导入前准备
     *
     * @return
     */
    @RequestMapping("/importReady")
    public String importReady() {
        return "config/factory_import";

    }

    private ExecuteResult<List<Factory>> batchAdd(ExecuteResult<List<Factory>> result, List<String> codes, HttpServletRequest request) {
        List<Factory> factoryList = new ArrayList<Factory>();
        List<String> list = new LinkedList<String>();
        String loginUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        if (codes.size() == 0) {
            result.setSuccessMessage("请至少输入一行工厂编码不为空的数据!");
            return result;
        }
        StringBuilder sb = arrayUnique(codes, list, 0); //去掉相同cfactoryCode
        if (sb != null) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        List<String> sameCodes = factoryService.selectExistCodes(list);
        if (sameCodes.size() > 0) {
            result.setSuccessMessage("数据操作不允许，操作未执行！以下编码在数据库已经存在：" + org.apache.commons.lang.StringUtils.join(list.toArray(), ","));
            return result;
        }
        String[] cfactoryCodes = request.getParameterValues("cfactoryCode");
        String[] factoryNames = request.getParameterValues("cfactoryName");
        String[] ccompanyCodes = request.getParameterValues("ccompanyCode");
        String[] ccompanyNames = request.getParameterValues("ccompanyName");
        String[] statuss = request.getParameterValues("cstatus");
        String[] cmemos = request.getParameterValues("cmemo");
        sb = new StringBuilder("");
        for (int i = 0; i < cfactoryCodes.length; i++) {
            String cfactoryCode = null == cfactoryCodes[i] ? null : cfactoryCodes[i].trim();
            String cfactoryName = null == factoryNames[i] ? null : factoryNames[i].trim();
            String ccompanyCode = null == ccompanyCodes[i] ? null : ccompanyCodes[i].trim();
            String ccompanyName = null == ccompanyNames[i] ? null : ccompanyNames[i].trim();
            String cstatus = null == statuss[i] ? null : statuss[i].trim();
            String cmemo = null == cmemos[i] ? null : cmemos[i].trim();

            Factory factory = new Factory();
            factory.setCfactoryCode(cfactoryCode);
            factory.setCfactoryName(cfactoryName);
            factory.setCcompanyCode(ccompanyCode);
            factory.setCcompanyName(ccompanyName);
            factory.setCmemo(cmemo);
            factory.setCstatus(cstatus);
            factory.setCcreateBy(loginUserName);
            factory.setCcreateByCode(loginUserCode);
            factoryList.add(factory);
            sb = StringDataCas.checkFieldCount(factory, sb);
        }
        if (StringDataCas.notEmpty(sb)) {
            result.setSuccessMessage(sb.toString());
            return result;
        }

        result = factoryService.saveBatch(factoryList);
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
    public ExecuteResult<List<Factory>> add(HttpServletRequest request) {
        ExecuteResult<List<Factory>> result = new ExecuteResult<List<Factory>>();
        result.setSuccessMessage("操作失败");
        String[] cfactoryCodes = request.getParameterValues("cfactoryCode");
        if (cfactoryCodes.length == 0 || !StringDataCas.notEmpty(cfactoryCodes[0])) {
            result.setSuccessMessage("工厂编码不能为空");
            return result;
        }
        ArrayList<String> codes = new ArrayList<String>();
        for (int i = 0; i < cfactoryCodes.length; i++) {
            codes.add(cfactoryCodes[i]);
        }
        result = batchAdd(result, codes, request);
        return result;

    }

    /**
     * 删除
     *
     * @param cfactoryCodes
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ExecuteResult<Factory> delete(@RequestParam(value = "cfactoryCodes[]", required = false) List<String> cfactoryCodes, HttpServletRequest req) {
        ExecuteResult<Factory> result = new ExecuteResult<Factory>();
        if (null == cfactoryCodes) {
            result.setSuccessMessage("请选择要删除的对象");
            return result;
        }
        Factory factory = new Factory();
        String updateBy = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String updateByCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        factory.setCupdateBy(updateBy);
        factory.setCupdateByCode(updateByCode);
        factory.setCfactoryCodes(cfactoryCodes);
        factory.setCstatus(STATUS.NO.getStatus());
        result = factoryService.delete(factory);
        return result;

    }

    /**
     * 修改前显示
     * @param cfactoryCode
     * @param model
     * @param flag
     * @return
     */
    @RequestMapping("/updateReady")
    public String update(String cfactoryCode, Model model, boolean flag) {
        Factory factory = factoryService.get(cfactoryCode);
        model.addAttribute("factory", factory);
        return flag ? "config/factory_update" : "config/factory_show";

    }

    /**
     * 修改
     *
     * @param factory
     * @param req
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ExecuteResult<Factory> update(Factory factory, HttpServletRequest req) {
        ExecuteResult<Factory> result = new ExecuteResult<Factory>();
        String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        factory.setCupdateBy(loginUserName);
        factory.setCupdateByCode(loginUserCode);
        StringBuilder sb = new StringBuilder("");
        sb = StringDataCas.checkFieldCount(factory, sb);
        if (StringDataCas.notEmpty(sb)) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        try {
            result = factoryService.update(factory);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            result.setSuccessMessage("更新失败!");
        }
        return result;

    }
    @RequestMapping("/get")
    @ResponseBody
    public Factory get(String cfactoryCode) {
        Factory factory = factoryService.get(cfactoryCode);
        return factory;
    }

    /**
     * 分页查询
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param factory
     * @return
     */
    @RequestMapping("/searchPagerList")
    public String searchStandCompConfList(Model model, HttpServletRequest req, Long offset, Long pageSize, Factory factory) {
        Pager<Factory> pager = new Pager<Factory>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<Factory> factoryPager = factoryService.searchPagerList(pager, factory);
        factoryPager.setUri(req.getRequestURI());
        model.addAttribute("factoryPager", factoryPager);
        model.addAttribute("param", factory);
        return "config/factory_list";
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

        List<Factory> addList = new ArrayList<Factory>();
        List<Factory> updateList = new ArrayList<Factory>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            Factory factory = new Factory();

            //工厂编码
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                factory.setCfactoryCode(row.getCell(0).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的工厂编码为空 !\n";
            }
            //工厂名称
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                factory.setCfactoryName(row.getCell(1).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的工厂名称为空 !\n";
            }
            //公司编码
            if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                factory.setCcompanyCode(row.getCell(2).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的公司编码为空 !\n";
            }
            //公司名称
            if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                factory.setCcompanyName(row.getCell(3).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的公司名称为空 !\n";
            }

            //备注
            if (null != row.getCell(4) && !"".equals(row.getCell(4).toString().trim())) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                factory.setCmemo(row.getCell(4).getStringCellValue().trim());
            }

            // 数据状态
            if (null != row.getCell(5) && !"".equals(row.getCell(5).toString().trim())) {
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                String status = row.getCell(5).getStringCellValue().trim();
                if (STATUS.EFFECTIVE.getStatus().equals(status)) {
                    factory.setCstatus(STATUS.YES.getStatus());
                } else if (STATUS.INVALID.getStatus().equals(status)) {
                    factory.setCstatus(STATUS.NO.getStatus());
                } else {
                    errorNull = errorNull + "第" + i + "行的状态不合法 !\n";
                }
            } else {
                factory.setCstatus(STATUS.YES.getStatus());
            }

            factory.setCupdateByCode(userCode);
            factory.setCupdateBy(userName);
            factory.setDupdateDate(new Date());

            if (StringUtils.isBlank(errorNull)) {
                if (companyService.countCompanyByCodeAndName(factory.getCcompanyCode(), factory.getCcompanyName()) == 0) {
                    errorNull = errorNull + "第" + i + "行的公司不存在 !\n";
                }

            }

            if (errorNull == "") {
                if (factoryService.checkFactoryCode(factory.getCfactoryCode()) > 0) {
                    updateList.add(factory);
                } else {
                    factory.setCcreateByCode(userCode);
                    factory.setCcreateBy(userName);
                    factory.setDcreateDate(new Date());
                    addList.add(factory);
                }
            }
        }
        if (updateList.size() == 0 && addList.size() == 0) {
            errorNull = errorNull + "表中无数据 !\n";
        }
        if (StringUtils.isBlank(errorNull)) {
            try {
                factoryService.addOrUpdateFactory(addList, updateList);
                jsonMap.put("success", true);
            } catch (Exception e) {
                jsonMap.put("errorNull", "数据保存失败");
                LOGGER.error("Exception", e);
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


    @RequestMapping(value = "/exportFile", method = RequestMethod.GET)
    @ResponseBody
    public void exportFile(HttpServletResponse response, Factory factory) throws Exception {
        byte[] fileNameByte = "工厂.xlsx".getBytes("GBK");
        String filename = new String(fileNameByte, "ISO8859-1");

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("工厂");
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
		HSSFSheet sheet = wb.createSheet("工厂");
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

        String[] title = {"工厂编码", "工厂名称", "公司编码", "公司名称", "创建人", "创建时间", "最后一次更新人", "最后一次更新时间", "备注", "状态"};
        List<String> excelHead = Arrays.asList(title);

//		HSSFCell cell = null;
        XSSFCell cell = null;
        // excel头
        for (int i = 0; i < excelHead.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHead.get(i));
            cell.setCellStyle(style);
        }

        List<Factory> result = factoryService.getList(factory);
        String status = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (int i = 0; i < result.size(); i++) {
            row = sheet.createRow((int) i + 1);
            Factory data = result.get(i);
            // 创建单元格，并设置值
            int j = 0;
            insertCellx(row, j++, data.getCfactoryCode() == null ? "" : data.getCfactoryCode());
            insertCellx(row, j++, data.getCfactoryName() == null ? "" : data.getCfactoryName());
            insertCellx(row, j++, data.getCcompanyCode() == null ? "" : data.getCcompanyCode());
            insertCellx(row, j++, data.getCcompanyCode() == null ? "" : data.getCcompanyName());
            insertCellx(row, j++, data.getCcreateBy() == null ? "" : data.getCcreateBy());
            insertCellx(row, j++, data.getDcreateDate() == null ? "" : sdf.format(data.getDcreateDate()));
            insertCellx(row, j++, data.getCupdateBy() == null ? "" : data.getCupdateBy());
            insertCellx(row, j++, data.getDupdateDate() == null ? "" : sdf.format(data.getDupdateDate()));
            insertCellx(row, j++, data.getCmemo() == null ? "" : data.getCmemo());
            status = "";
            if (StringUtils.isNotBlank(data.getCstatus())) {
                if (STATUS.YES.getStatus().equals(data.getCstatus())) {
                    status = STATUS.EFFECTIVE.getStatus();
                } else if (STATUS.NO.getStatus().equals(data.getCstatus())) {
                    status = STATUS.INVALID.getStatus();
                } else {
                    status = data.getCstatus();
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
     * 下载模板
     * @param request
     * @param response
     * @param model
     * @throws Exception
     */
    /*@RequestMapping( value="/downloadModel",method = RequestMethod.GET)
    public void downloadModel(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String path="/excelModel/config/工厂导入模板.xlsx";
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