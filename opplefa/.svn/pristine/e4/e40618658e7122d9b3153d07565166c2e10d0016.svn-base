package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.AssetUnit;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AssetUnitService;
import com.opple.fa.utils.StringDataCas;
import com.opple.security.SessionSecurityConstants;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
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
 * 单位
 */
@Controller
@RequestMapping("/config/assetUnit")
public class AssetUnitController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssetUnitController.class);
    private static final Map<String, String> COLUMN_MAP = new HashMap<String, String>();
    @Resource
    private AssetUnitService assetUnitService;

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
        return "config/assetUnit/assetUnit_add";

    }


    private ExecuteResult<List<AssetUnit>> batchAdd(ExecuteResult<List<AssetUnit>> result, List<String> codes, HttpServletRequest request) {
        List<AssetUnit> assetUnitList = new ArrayList<AssetUnit>();
        List<String> list = new LinkedList<String>();
        String loginUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        if (codes.size() == 0) {
            result.setSuccessMessage("请至少输入一行资产编码不为空的数据!");
            return result;
        }
        StringBuilder sb = arrayUnique(codes, list, 0); //去掉相同unitCode
        if (sb != null) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        List<String> sameCodes = assetUnitService.selectExistCodes(list);
        if (sameCodes.size() > 0) {
            result.setSuccessMessage("数据操作不允许，操作未执行！以下编码在数据库已经存在：" + org.apache.commons.lang.StringUtils.join(list.toArray(), ","));
            return result;
        }
        String[] unitCodes = request.getParameterValues("unitCode");
        String[] unitNames = request.getParameterValues("unitName");
        String[] statuss = request.getParameterValues("status");
        String[] memos = request.getParameterValues("memo");
        Long sameNames = assetUnitService.selectExistNames(unitNames[0]);
        if (sameNames > 0) {
            result.setSuccessMessage("单位名称已存在，操作未执行!");
            return result;
        }
        sb = new StringBuilder("");
        for (int i = 0; i < unitCodes.length; i++) {
            String unitCode = null == unitCodes[i] ? null : unitCodes[i].trim();
            String unitName = null == unitNames[i] ? null : unitNames[i].trim();
            String status = null == statuss[i] ? null : statuss[i].trim();
            String memo = null == memos[i] ? null : memos[i].trim();

            AssetUnit assetUnit = new AssetUnit();
            assetUnit.setUnitCode(unitCode);
            assetUnit.setUnitName(unitName);
            assetUnit.setMemo(memo);
            assetUnit.setStatus(status);
            assetUnit.setCreateBy(loginUserName);
            assetUnit.setCreateByCode(loginUserCode);
            assetUnitList.add(assetUnit);
            sb = StringDataCas.checkFieldCount(assetUnit, sb);
        }
        if (StringDataCas.notEmpty(sb)) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        result = assetUnitService.saveBatch(assetUnitList);
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
    public ExecuteResult<List<AssetUnit>> add(HttpServletRequest request) {
        ExecuteResult<List<AssetUnit>> result = new ExecuteResult<List<AssetUnit>>();
        result.setSuccessMessage("操作失败");
        String[] unitCodes = request.getParameterValues("unitCode");
        if (unitCodes.length == 0 || !StringDataCas.notEmpty(unitCodes[0])) {
            result.setSuccessMessage("资产编码不能为空");
            return result;
        }
        ArrayList<String> codes = new ArrayList<String>();
        for (int i = 0; i < unitCodes.length; i++) {
            codes.add(unitCodes[i]);
        }
        result = batchAdd(result, codes, request);
        return result;

    }

    /**
     * 删除
     *
     * @param unitCodes
     * @param req
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ExecuteResult<AssetUnit> delete(@RequestParam(value = "unitCodes[]", required = false) List<String> unitCodes, HttpServletRequest req) {
        ExecuteResult<AssetUnit> result = new ExecuteResult<AssetUnit>();
        if (null == unitCodes) {
            result.setSuccessMessage("请选择要删除的对象");
            return result;
        }
        AssetUnit assetUnit = new AssetUnit();
        String updateBy = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String updateByCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        assetUnit.setUpdateBy(updateBy);
        assetUnit.setUpdateByCode(updateByCode);
        assetUnit.setUnitCodes(unitCodes);
        assetUnit.setStatus(STATUS.NO.getStatus());
        result = assetUnitService.delete(assetUnit);
        return result;

    }

    /**
     * 修改前显示
     *
     * @param unitCode
     * @param flag
     * @param model
     * @return
     */
    @RequestMapping("/updateReady")
    public String update(String unitCode, Model model, boolean flag) {
        AssetUnit assetUnit = assetUnitService.get(unitCode);
        model.addAttribute("assetUnit", assetUnit);
        return flag ? "config/assetUnit/assetUnit_update" : "config/assetUnit/assetUnit_show";

    }

    /**
     * 修改
     *
     * @param assetUnit
     * @param req
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ExecuteResult<AssetUnit> update(AssetUnit assetUnit, HttpServletRequest req) {
        String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        assetUnit.setUpdateBy(loginUserName);
        assetUnit.setUpdateByCode(loginUserCode);
        ExecuteResult<AssetUnit> result = assetUnitService.update(assetUnit);
        return result;

    }

    @RequestMapping("/get")
    @ResponseBody
    public AssetUnit get(String unitCode) {
        AssetUnit assetUnit = assetUnitService.get(unitCode);
        return assetUnit;
    }

    /**
     * 分页查询
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param assetUnit
     * @return
     */
    @RequestMapping("/searchPagerList")
    public String searchStandCompConfList(Model model, HttpServletRequest req, Long offset, Long pageSize, AssetUnit assetUnit) {
        Pager<AssetUnit> pager = new Pager<AssetUnit>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<AssetUnit> assetUnitPager = assetUnitService.searchPagerList(pager, assetUnit);
        assetUnitPager.setUri(req.getRequestURI());
        model.addAttribute("assetUnitPager", assetUnitPager);
        model.addAttribute("param", assetUnit);
        return "config/assetUnit/assetUnit_list";
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

        List<AssetUnit> addList = new ArrayList<AssetUnit>();
        List<AssetUnit> updateList = new ArrayList<AssetUnit>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            AssetUnit assetUnit = new AssetUnit();

            //单位编码
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                assetUnit.setUnitCode(row.getCell(0).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的单位编码为空 !\n";
            }
            //单位名称
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                assetUnit.setUnitName(row.getCell(1).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的单位名称为空 !\n";
            }

            //备注
            if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                assetUnit.setMemo(row.getCell(2).getStringCellValue().trim());
            }

            // 数据状态
            if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                String status = row.getCell(3).getStringCellValue().trim();
                if (STATUS.EFFECTIVE.getStatus().equals(status)) {
                    assetUnit.setStatus(STATUS.YES.getStatus());
                } else if (STATUS.INVALID.getStatus().equals(status)) {
                    assetUnit.setStatus(STATUS.NO.getStatus());
                } else {
                    errorNull = errorNull + "第" + i + "行的状态不合法 !\n";
                }
            } else {
                assetUnit.setStatus(STATUS.YES.getStatus());
            }

            assetUnit.setUpdateByCode(userCode);
            assetUnit.setUpdateBy(userName);
            assetUnit.setUpdateDate(new Date());

            if (errorNull == "") {
                if (assetUnitService.checkUnitCode(assetUnit.getUnitCode()) > 0) {
                    updateList.add(assetUnit);
                } else {
                    assetUnit.setCreateByCode(userCode);
                    assetUnit.setCreateBy(userName);
                    assetUnit.setCreateDate(new Date());
                    addList.add(assetUnit);
                }
            }
        }
        if (updateList.size() == 0 && addList.size() == 0) {
            errorNull = errorNull + "表中无数据 !\n";
        }
        if (StringUtils.isBlank(errorNull)) {
            String result = assetUnitService.addOrUpdateUnit(addList, updateList);
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


    @RequestMapping(value = "/exportFile", method = RequestMethod.GET)
    @ResponseBody
    public void exportFile(HttpServletRequest request,
                           HttpServletResponse response, AssetUnit assetUnit) throws Exception {
        byte[] fileNameByte = "单位.xlsx".getBytes("GBK");
        String filename = new String(fileNameByte, "ISO8859-1");

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("单位");
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
		HSSFSheet sheet = wb.createSheet("单位");
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

        String[] title = {"单位编码", "单位名称", "创建人", "创建时间", "最后一次更新人", "最后一次更新时间", "备注", "状态"};
        List<String> excelHead = Arrays.asList(title);

//		HSSFCell cell = null;
        XSSFCell cell = null;
        // excel头
        for (int i = 0; i < excelHead.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHead.get(i));
            cell.setCellStyle(style);
        }

        List<AssetUnit> result = assetUnitService.getList(assetUnit);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String status = "";
        for (int i = 0; i < result.size(); i++) {
            row = sheet.createRow((int) i + 1);
            AssetUnit data = result.get(i);
            // 创建单元格，并设置值
            int j = 0;
            insertCellx(row, j++, data.getUnitCode() == null ? "" : data.getUnitCode());
            insertCellx(row, j++, data.getUnitName() == null ? "" : data.getUnitName());
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

    private void insertCell(HSSFRow row, int i, Object object) {
        if (object == null) {
            row.createCell(i).setCellValue("");
        } else {
            row.createCell(i).setCellValue(object.toString());
        }

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
		String path="/excelModel/config/单位导入模板.xlsx";
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