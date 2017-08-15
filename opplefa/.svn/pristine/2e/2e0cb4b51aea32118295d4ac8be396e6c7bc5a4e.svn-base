package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.Position;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.PositionService;
import com.opple.fa.config.service.WorkAddressService;
import com.opple.fa.utils.StringDataCas;
import com.opple.security.SessionSecurityConstants;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
 * 区域
 */
@Controller
@RequestMapping("/config/position")
public class PositionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionController.class);
    private static final Map<String, String> COLUMN_MAP = new HashMap<String, String>();
    @Resource
    private PositionService positionService;
    @Resource
    private WorkAddressService workAddressService;

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
        return "config/position_add";

    }

    /**
     * 导入前准备
     *
     * @return
     */
    @RequestMapping("/importReady")
    public String importReady() {
        return "config/position_import";

    }

    private ExecuteResult<List<Position>> batchAdd(ExecuteResult<List<Position>> result, List<String> codes, HttpServletRequest request) {
        List<Position> positionList = new ArrayList<Position>();
        List<String> list = new LinkedList<String>();
        String loginUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        if (codes.size() == 0) {
            result.setSuccessMessage("请至少输入一行存放位置编码不为空的数据!");
            return result;
        }
        StringBuilder sb = arrayUnique(codes, list, 0); //去掉相同cpositionCode
        if (sb != null) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        List<String> sameCodes = positionService.selectExistCodes(list);
        if (sameCodes.size() > 0) {
            result.setSuccessMessage("数据操作不允许，操作未执行！以下编码在数据库已经存在：" + org.apache.commons.lang.StringUtils.join(list.toArray(), ","));
            return result;
        }
        String[] cpositionCodes = request.getParameterValues("cpositionCode");
        String[] cpositions = request.getParameterValues("cposition");
        String[] workAddressCodes = request.getParameterValues("workAddressCode");
        String[] workAddresses = request.getParameterValues("workAddress");
        String[] statuss = request.getParameterValues("cstatus");
        String[] cmemos = request.getParameterValues("cmemo");

        sb = new StringBuilder("");
        for (int i = 0; i < cpositionCodes.length; i++) {
            String cpositionCode = null == cpositionCodes[i] ? null : cpositionCodes[i].trim();
            String cposition = null == cpositions[i] ? null : cpositions[i].trim();
            String workAddressCode = null == workAddressCodes[i] ? null : workAddressCodes[i].trim();
            String workAddress = null == workAddresses[i] ? null : workAddresses[i].trim();
            String cstatus = null == statuss[i] ? null : statuss[i].trim();
            String cmemo = null == cmemos[i] ? null : cmemos[i].trim();

            Position position = new Position();
            position.setCpositionCode(cpositionCode);
            position.setCposition(cposition);
            position.setWorkAddress(workAddress);
            position.setWorkAddressCode(workAddressCode);
            position.setCmemo(cmemo);
            position.setCstatus(cstatus);
            position.setCcreateBy(loginUserName);
            position.setCcreateByCode(loginUserCode);
            positionList.add(position);
            sb = StringDataCas.checkFieldCount(position, sb);
        }
        if (StringDataCas.notEmpty(sb)) {
            result.setSuccessMessage(sb.toString());
            return result;
        }

        Integer exist = positionService.checkPositionName(positionList.get(0));
        if (exist > 0) {
            result.setSuccessMessage("存放位置已存在，操作未执行!");
            return result;
        } else {
            try {
                result = positionService.saveBatch(positionList);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
                result.setSuccessMessage("添加失败!");
            }
            return result;
        }

    }

    /**
     * 添加
     *
     * @param request
     * @return
     */

    @RequestMapping("/add")
    @ResponseBody
    public ExecuteResult<List<Position>> add(HttpServletRequest request) {
        ExecuteResult<List<Position>> result = new ExecuteResult<List<Position>>();
        result.setSuccessMessage("操作失败");
        String[] cpositionCodes = request.getParameterValues("cpositionCode");
        if (cpositionCodes.length == 0 || !StringDataCas.notEmpty(cpositionCodes[0])) {
            result.setSuccessMessage("存放位置编码不能为空");
            return result;
        }
        ArrayList<String> codes = new ArrayList<String>();
        for (int i = 0; i < cpositionCodes.length; i++) {
            codes.add(cpositionCodes[i]);
        }
        result = batchAdd(result, codes, request);
        return result;

    }

    /**
     * 删除
     *
     * @param cpositionCodes
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ExecuteResult<Position> delete(@RequestParam(value = "cpositionCodes[]", required = false) List<String> cpositionCodes, HttpServletRequest req) {
        ExecuteResult<Position> result = new ExecuteResult<Position>();
        if (null == cpositionCodes) {
            result.isSuccess();
            result.setSuccessMessage("请选择要删除的对象");
            return result;
        }
        Position position = new Position();
        String updateBy = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String updateByCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        position.setCupdateBy(updateBy);
        position.setCupdateByCode(updateByCode);
        position.setCpositionCodes(cpositionCodes);
        position.setCstatus(STATUS.NO.getStatus());
        result = positionService.delete(position);
        return result;

    }

    /**
     * 修改前显示
     *
     * @param cpositionCode
     * @return
     */
    @RequestMapping("/updateReady")
    public String update(String cpositionCode, Model model, boolean flag) {
        Position position = positionService.get(cpositionCode);
        model.addAttribute("position", position);
        return flag ? "config/position_update" : "config/position_show";

    }

    /**
     * 修改
     *
     * @param position
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ExecuteResult<Position> update(Position position, HttpServletRequest req) {
        String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        position.setCupdateBy(loginUserName);
        position.setCupdateByCode(loginUserCode);
        ExecuteResult<Position> result = new ExecuteResult<Position>();
        Integer exist = positionService.checkPositionName(position);
        if (exist > 0) {
            result.setSuccessMessage("存放位置已存在，操作未执行!");

        } else {
            try {
                result = positionService.update(position);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
                result.setSuccessMessage("更新失败!");
            } finally {
                return result;
            }

        }
        return result;
    }

    @RequestMapping("/get")
    @ResponseBody
    public Position get(String cpositionCode) {
        Position position = positionService.get(cpositionCode);
        return position;
    }

    /**
     * 分页查询
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param position
     * @return
     */
    @RequestMapping("/searchPagerList")
    public String searchStandCompConfList(Model model, HttpServletRequest req, Long offset, Long pageSize, Position position) {
        Pager<Position> pager = new Pager<Position>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<Position> positionPager = null;
        try {
            positionPager = positionService.searchPagerList(pager, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        positionPager.setUri(req.getRequestURI());
        model.addAttribute("positionPager", positionPager);
        model.addAttribute("param", position);
        return "config/position_list";
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

        List<Position> addList = new ArrayList<Position>();
        List<Position> updateList = new ArrayList<Position>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            Position position = new Position();

            //存放位置编码
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                position.setCpositionCode(row.getCell(0).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的工厂编码为空 !\n";
            }
            //存放位置名称
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                position.setCposition(row.getCell(1).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的工厂名称为空 !\n";
            }
            //办公地点编码
            if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                position.setWorkAddressCode(row.getCell(2).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的办公地点编码为空 !\n";
            }
            //办公地点名称
            if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                position.setWorkAddress(row.getCell(3).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的办公地点名称为空 !\n";
            }
            Long count = null;
            if ("".equals(errorNull)) {
                count = workAddressService.searchWorkAddress(position.getWorkAddressCode(), position.getWorkAddress());
                if (count == 0) {
                    errorNull = errorNull + "第" + i + "行的办公地点信息错误 !\n";
                }
            }
            //备注
            if (null != row.getCell(4) && !"".equals(row.getCell(4).toString().trim())) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                position.setCmemo(row.getCell(4).getStringCellValue().trim());
            }

            // 数据状态
            if (null != row.getCell(5) && !"".equals(row.getCell(5).toString().trim())) {
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                String status = row.getCell(5).getStringCellValue().trim();
                if (STATUS.EFFECTIVE.getStatus().equals(status)) {
                    position.setCstatus(STATUS.YES.getStatus());
                } else if (STATUS.INVALID.getStatus().equals(status)) {
                    position.setCstatus(STATUS.NO.getStatus());
                } else {
                    errorNull = errorNull + "第" + i + "行的状态不合法 !\n";
                }
            } else {
                position.setCstatus(STATUS.YES.getStatus());
            }

            position.setCupdateByCode(userCode);
            position.setCupdateBy(userName);
            position.setDupdateDate(new Date());

            if (errorNull == "") {
                if (positionService.checkPositionCode(position.getCpositionCode()) > 0) {
                    updateList.add(position);
                } else {
                    position.setCcreateByCode(userCode);
                    position.setCcreateBy(userName);
                    position.setDcreateDate(new Date());
                    addList.add(position);
                }
            }
        }
        if (updateList.size() == 0 && addList.size() == 0) {
            errorNull = errorNull + "表中无数据 !\n";
        }
        if (StringUtils.isBlank(errorNull)) {
            try {
                positionService.addOrUpdatePosition(addList, updateList);
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

    /**
     * 导出
     *
     * @param response
     * @param position
     * @throws Exception
     */
    @RequestMapping(value = "/exportFile", method = RequestMethod.GET)
    @ResponseBody
    public void exportFile(HttpServletResponse response, Position position) throws Exception {
        byte[] fileNameByte = "存放位置.xls".getBytes("GBK");
        String filename = new String(fileNameByte, "ISO8859-1");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("存放位置");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setFillBackgroundColor(HSSFColor.GOLD.index);
        style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setDataFormat(wb.createDataFormat().getFormat(
                "yyyy-MM-dd hh:mm:ss"));

        String[] title = {"存放位置编码", "存放位置名称", "办公地点编码", "办公地点名称", "创建人", "创建时间", "最后一次更新人", "最后一次更新时间", "备注", "状态"};
        List<String> excelHead = Arrays.asList(title);

        HSSFCell cell = null;
        // excel头
        for (int i = 0; i < excelHead.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHead.get(i));
            cell.setCellStyle(style);
        }


        List<Position> result = positionService.getList(position);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String status = "";
        for (int i = 0; i < result.size(); i++) {
            row = sheet.createRow((int) i + 1);
            Position data = result.get(i);
            // 创建单元格，并设置值
            int j = 0;
            insertCell(row, j++, data.getCpositionCode() == null ? "" : data.getCpositionCode());
            insertCell(row, j++, data.getCposition() == null ? "" : data.getCposition());
            insertCell(row, j++, data.getWorkAddressCode() == null ? "" : data.getWorkAddressCode());
            insertCell(row, j++, data.getWorkAddress() == null ? "" : data.getWorkAddress());
            insertCell(row, j++, data.getCcreateBy() == null ? "" : data.getCcreateBy());
            insertCell(row, j++, data.getDcreateDate() == null ? "" : sdf.format(data.getDcreateDate()));
            insertCell(row, j++, data.getCupdateBy() == null ? "" : data.getCupdateBy());
            insertCell(row, j++, data.getDupdateDate() == null ? "" : sdf.format(data.getDupdateDate()));
            insertCell(row, j++, data.getCmemo() == null ? "" : data.getCmemo());
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
            insertCell(row, j++, status);
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

}