package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.WorkAddress;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.UserInfoService;
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
 * 办公地点
 */
@Controller
@RequestMapping("/config/workAddress")
public class WorkAddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkAddressController.class);
    private static final Map<String, String> COLUMN_MAP = new HashMap<String, String>();
    @Resource
    private WorkAddressService workAddressService;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("/getWorkAddressCode")
    public String getWorkAddressCode(Model model, HttpServletRequest req, Long offset, Long pageSize, String parentWindowId, WorkAddress workAddress) {
        Pager<WorkAddress> pager = new Pager<WorkAddress>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<WorkAddress> workAddressPager = workAddressService.searchPagerList(pager, workAddress);

        workAddressPager.setUri(req.getRequestURI());
        workAddressPager.setParentWindowId(parentWindowId);

        model.addAttribute("workAddressPager", workAddressPager);
        model.addAttribute("param", workAddress);
        model.addAttribute("parentWindowId", parentWindowId);

        return "config/location_list";
    }


    @RequestMapping("/getWorkAddressCodeAndName")
    public String getWorkAddressCodeAndName(Model model, HttpServletRequest req, Long offset, Long pageSize, String parentWindowId, WorkAddress workAddress) {
        Pager<WorkAddress> pager = new Pager<WorkAddress>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<WorkAddress> workAddressPager = workAddressService.searchWorkAddressCodeAndName(pager, workAddress);

        workAddressPager.setUri(req.getRequestURI());
        workAddressPager.setParentWindowId(parentWindowId);

        model.addAttribute("workAddressPager", workAddressPager);
        model.addAttribute("param", workAddress);
        model.addAttribute("parentWindowId", parentWindowId);

        return "config/location_list";
    }

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
     * @retsurn
     */
    @RequestMapping("/addReady")
    public String addReady() {
        return "config/workAddress_add";

    }

    /**
     * 导入前准备
     *
     * @return
     */
    @RequestMapping("/importReady")
    public String importReady() {
        return "config/workAddress_import";

    }

    private ExecuteResult<List<WorkAddress>> batchAdd(ExecuteResult<List<WorkAddress>> result, List<String> codes, HttpServletRequest request) {
        List<WorkAddress> workAddressList = new ArrayList<WorkAddress>();
        List<String> list = new LinkedList<String>();
        String loginUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        if (codes.size() == 0) {
            result.setSuccessMessage("请至少输入一行办公地点编码不为空的数据!");
            return result;
        }
        StringBuilder sb = arrayUnique(codes, list, 0); //去掉相同caddressCode
        if (sb != null) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        List<String> sameCodes = workAddressService.selectExistCodes(list);
        if (sameCodes.size() > 0) {
            result.setSuccessMessage("数据操作不允许，操作未执行！以下编码在数据库已经存在：" + org.apache.commons.lang.StringUtils.join(list.toArray(), ","));
            return result;
        }

        String[] caddressCodes = request.getParameterValues("caddressCode");
        String[] caddressNames = request.getParameterValues("caddressName");
        String[] cstatuss = request.getParameterValues("cstatus");
        String[] cmemos = request.getParameterValues("cmemo");
        String[] payeeCodes = request.getParameterValues("payeeCode");
        String[] payeeNames = request.getParameterValues("payeeName");
        String[] purchasebidCodes = request.getParameterValues("purchasebidCode");
        String[] purchasebidNames = request.getParameterValues("purchasebidName");
        String[] purchasetipaccessCodes = request.getParameterValues("purchasetipaccessCode");
        String[] purchasetipaccessNames = request.getParameterValues("purchasetipaccessName");
        String[] receivegoodsCodes = request.getParameterValues("receivegoodsCode");
        String[] receivegoodsNames = request.getParameterValues("receivegoodsName");
        String[] retiredgoodsCodes = request.getParameterValues("retiredgoodsCode");
        String[] retiredgoodsNames = request.getParameterValues("retiredgoodsName");

        sb = new StringBuilder("");
        for (int i = 0; i < caddressCodes.length; i++) {
            String caddressCode = null == caddressCodes[i] ? null : caddressCodes[i].trim();
            String caddressName = null == caddressNames[i] ? null : caddressNames[i].trim();
            String cstatus = null == cstatuss[i] ? null : cstatuss[i].trim();
            String cmemo = null == cmemos[i] ? null : cmemos[i].trim();

            WorkAddress workAddress = new WorkAddress();
            workAddress.setCaddressCode(caddressCode);
            workAddress.setCaddressName(caddressName);
            workAddress.setPayeeCode(payeeCodes[i]);
            workAddress.setPayeeName(payeeNames[i]);
            workAddress.setPurchasebidCode(purchasebidCodes[i]);
            workAddress.setPurchasebidName(purchasebidNames[i]);
            workAddress.setPurchasetipaccessCode(purchasetipaccessCodes[i]);
            workAddress.setPurchasetipaccessName(purchasetipaccessNames[i]);
            workAddress.setReceivegoodsCode(receivegoodsCodes[i]);
            workAddress.setReceivegoodsName(receivegoodsNames[i]);
            workAddress.setRetiredgoodsCode(retiredgoodsCodes[i]);
            workAddress.setRetiredgoodsName(retiredgoodsNames[i]);
            workAddress.setCmemo(cmemo);
            workAddress.setCstatus(cstatus);
            workAddress.setCcreateBy(loginUserName);
            workAddress.setCcreateByCode(loginUserCode);

            workAddressList.add(workAddress);
            sb = StringDataCas.checkFieldCount(workAddress, sb);
        }
        if (StringDataCas.notEmpty(sb)) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
        Integer sameNames = workAddressService.selectCodeAndName(caddressCodes[0], caddressNames[0]);
        if (sameNames > 0) {
            result.setSuccessMessage("办公地点已存在，操作未执行!");
            return result;
        }
            result = workAddressService.saveBatch(workAddressList);
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
    public ExecuteResult<List<WorkAddress>> add(HttpServletRequest request) {
        ExecuteResult<List<WorkAddress>> result = new ExecuteResult<List<WorkAddress>>();
        result.setSuccessMessage("操作失败");
        String[] caddressCodes = request.getParameterValues("caddressCode");
        if (caddressCodes.length == 0 || !StringDataCas.notEmpty(caddressCodes[0])) {
            result.setSuccessMessage("办公地点编码不能为空");
            return result;
        }
        ArrayList<String> codes = new ArrayList<String>();
        for (int i = 0; i < caddressCodes.length; i++) {
            codes.add(caddressCodes[i]);
        }
        try {
            result = batchAdd(result, codes, request);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            result.setSuccessMessage("添加失败!");
        }
        return result;

    }

    /**
     * 删除
     *
     * @param caddressCodes
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ExecuteResult<WorkAddress> delete(@RequestParam(value = "caddressCodes[]", required = false) List<String> caddressCodes, HttpServletRequest req) {
        ExecuteResult<WorkAddress> result = new ExecuteResult<WorkAddress>();
        if (null == caddressCodes) {
            result.isSuccess();
            result.setSuccessMessage("请选择要删除的对象");
            return result;
        }
        WorkAddress workAddress = new WorkAddress();
        String updateBy = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String updateByCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        workAddress.setCupdateBy(updateBy);
        workAddress.setCupdateByCode(updateByCode);
        workAddress.setCaddressCodes(caddressCodes);
        workAddress.setCstatus(STATUS.NO.getStatus());
        result = workAddressService.delete(workAddress);
        return result;

    }

    /**
     * 修改前显示
     *
     * @param caddressCode
     * @return
     */
    @RequestMapping("/updateReady")
    public String update(String caddressCode, Model model, boolean flag) {
        WorkAddress address = workAddressService.get(caddressCode);
        model.addAttribute("address", address);
        return flag ? "config/workAddress_update" : "config/workAddress_show";

    }

    /**
     * 修改
     *
     * @param workAddress
     * @param req
     * @return
     */

    @RequestMapping("/update")
    @ResponseBody
    public ExecuteResult<WorkAddress> update(@RequestBody WorkAddress workAddress, HttpServletRequest req) {
    	String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
    	String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
    	workAddress.setCupdateBy(loginUserName);
    	workAddress.setCupdateByCode(loginUserCode);
    	ExecuteResult<WorkAddress> result =  new ExecuteResult<WorkAddress>();
    	String name = workAddress.getCaddressName();
        Integer sameNames = workAddressService.selectCodeAndName(workAddress.getCaddressCode(), workAddress.getCaddressName());
        if (sameNames > 0) {
            result.setSuccessMessage("办公地点已存在，操作未执行!");
            return result;
        }
//        Long sameNames = workAddressService.selectExistNames(workAddress);
//        if (sameNames > 0) {
//            result.setSuccessMessage("办公地点已存在，操作未执行!");
//
//        }
            try {
                result = workAddressService.update(workAddress);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
                result.setSuccessMessage("更新失败!");
            }
        return result;
    
    }

    @RequestMapping("/get")
    @ResponseBody
    public WorkAddress get(String caddressCode) {
        WorkAddress workAddress = workAddressService.get(caddressCode);
        return workAddress;
    }

    /**
     * 分页查询
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param workAddress
     * @return
     */
    @RequestMapping("/searchPagerList")
    public String searchStandCompConfList(Model model, HttpServletRequest req, Long offset, Long pageSize, WorkAddress workAddress) {
        Pager<WorkAddress> pager = new Pager<WorkAddress>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<WorkAddress> workAddressPager = workAddressService.searchPagerList(pager, workAddress);
        workAddressPager.setUri(req.getRequestURI());
        model.addAttribute("workAddressPager", workAddressPager);
        model.addAttribute("param", workAddress);
        return "config/workAddress_list";
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
        List<WorkAddress> addList = new ArrayList<WorkAddress>();
        List<WorkAddress> updateList = new ArrayList<WorkAddress>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            WorkAddress workAddress = new WorkAddress();

            //办公地点编码
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setCaddressCode(row.getCell(0).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的办公地点编码为空 !\n";
            }
            //办公地点名称
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setCaddressName(row.getCell(1).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的办公地点名称为空 !\n";
            }

            //仓库收货管理员编码
            if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setReceivegoodsCode(row.getCell(2).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的仓库收货管理员编码为空 !\n";
            }

            //仓库收货管理员姓名
            if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setReceivegoodsName(row.getCell(3).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的仓库收货管理员姓名为空 !\n";
            }
            //仓库废品管理员编码
            if (null != row.getCell(4) && !"".equals(row.getCell(4).toString().trim())) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setRetiredgoodsCode(row.getCell(4).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的仓库废品管理员编码为空 !\n";
            }
            //仓库废品管理员姓名
            if (null != row.getCell(5) && !"".equals(row.getCell(5).toString().trim())) {
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setRetiredgoodsName(row.getCell(5).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的仓库废品管理员姓名为空 !\n";
            }
            //采购意见处理员编码
            if (null != row.getCell(6) && !"".equals(row.getCell(6).toString().trim())) {
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setPurchasetipaccessCode(row.getCell(6).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的采购意见处理员编码为空 !\n";
            }
            //采购意见处理员姓名
            if (null != row.getCell(7) && !"".equals(row.getCell(7).toString().trim())) {
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setPurchasetipaccessName(row.getCell(7).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的采购意见处理员姓名为空 !\n";
            }
            //采购竞价/议价员编码
            if (null != row.getCell(8) && !"".equals(row.getCell(8).toString().trim())) {
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setPurchasebidCode(row.getCell(8).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的采购竞价/议价员编码为空 !\n";
            }
            //采购竞价/议价员姓名
            if (null != row.getCell(9) && !"".equals(row.getCell(9).toString().trim())) {
                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setPurchasebidName(row.getCell(9).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的采购竞价/议价员姓名为空 !\n";
            }
            //收款确认人编码
            if (null != row.getCell(10) && !"".equals(row.getCell(10).toString().trim())) {
                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setPayeeCode(row.getCell(10).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的收款确认人编为空 !\n";
            }
            //收款确认人姓名
            if (null != row.getCell(11) && !"".equals(row.getCell(11).toString().trim())) {
                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setPayeeName(row.getCell(11).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的收款确认人姓名为空 !\n";
            }
            Integer count = null;
            Integer count1 = null;
            Integer count2 = null;
            Integer count3 = null;
            Integer count4 = null;
            if ("".equals(errorNull)) {
                count = userInfoService.countUserByCodeAndName(workAddress.getReceivegoodsCode(), workAddress.getReceivegoodsName());
                count1 = userInfoService.countUserByCodeAndName(workAddress.getRetiredgoodsCode(), workAddress.getRetiredgoodsName());
                count2 = userInfoService.countUserByCodeAndName(workAddress.getPurchasetipaccessCode(), workAddress.getPurchasetipaccessName());
                count3 = userInfoService.countUserByCodeAndName(workAddress.getPurchasebidCode(), workAddress.getPurchasebidName());
                count4 = userInfoService.countUserByCodeAndName(workAddress.getPayeeCode(), workAddress.getPayeeName());
                if (count == 0) {
                    errorNull = errorNull + "第" + i + "行的仓库收货管理员信息错误 !\n";
                }
                if (count1 == 0) {
                    errorNull = errorNull + "第" + i + "行的仓库废品管理员信息错误 !\n";
                }
                if (count2 == 0) {
                    errorNull = errorNull + "第" + i + "行的采购意见处理员信息错误 !\n";
                }
                if (count3 == 0) {
                    errorNull = errorNull + "第" + i + "行的采购竞价/议价员信息错误!\n";
                }
                if (count4 == 0) {
                    errorNull = errorNull + "第" + i + "行的收款确认人信息错误 !\n";
                }
            }

            //备注
            if (null != row.getCell(12) && !"".equals(row.getCell(12).toString().trim())) {
                row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                workAddress.setCmemo(row.getCell(12).getStringCellValue().trim());
            }
            // 数据状态
            if (null != row.getCell(13) && !"".equals(row.getCell(13).toString().trim())) {
                row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                String status = row.getCell(13).getStringCellValue().trim();
                if (STATUS.EFFECTIVE.getStatus().equals(status)) {
                    workAddress.setCstatus(STATUS.YES.getStatus());
                } else if (STATUS.INVALID.getStatus().equals(status)) {
                    workAddress.setCstatus(STATUS.NO.getStatus());
                } else {
                    errorNull = errorNull + "第" + i + "行的状态不合法 !\n";
                }
            } else {
                workAddress.setCstatus(STATUS.YES.getStatus());
            }

            workAddress.setCupdateByCode(userCode);
            workAddress.setCupdateBy(userName);
            workAddress.setDupdateDate(new Date());

            if (errorNull == "") {
                if (workAddressService.checkUnitCode(workAddress.getCaddressCode()) > 0) {
                    updateList.add(workAddress);
                } else {
                    workAddress.setCcreateByCode(userCode);
                    workAddress.setCcreateBy(userName);
                    workAddress.setDcreateDate(new Date());
                    addList.add(workAddress);
                }
            }
        }
        if (updateList.size() == 0 && addList.size() == 0) {
            errorNull = errorNull + "表中无数据 !\n";
        }
        if (StringUtils.isBlank(errorNull)) {
            String result = workAddressService.addOrUpdateUnit(addList, updateList);
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
                           HttpServletResponse response, WorkAddress workAddress) throws Exception {
        byte[] fileNameByte = "办公地点.xls".getBytes("GBK");
        String filename = new String(fileNameByte, "ISO8859-1");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("办公地点");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setFillBackgroundColor(HSSFColor.GOLD.index);
        style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        /*style.setDataFormat(wb.createDataFormat().getFormat(
                "yyyy-MM-dd hh:mm:ss"));*/

        String[] title = {"办公地点编码", "办公地点名称", "仓库收货管理员编码", "仓库收货管理员姓名", "仓库废品管理员编码", "仓库废品管理员姓名", "采购意见处理员编码", "采购意见处理员姓名", "采购竞价/议价员编码", "采购竞价/议价员姓名", "收款确认人编码", "收款确认人姓名", "创建时间", "最后一次更新时间", "备注", "状态", "创建人", "最后一次更新人"};
        List<String> excelHead = Arrays.asList(title);

        HSSFCell cell = null;
        // excel头
        for (int i = 0; i < excelHead.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(excelHead.get(i));
            cell.setCellStyle(style);
        }


        List<WorkAddress> result = workAddressService.getList(workAddress);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String status = "";
        for (int i = 0; i < result.size(); i++) {
            row = sheet.createRow((int) i + 1);
            WorkAddress data = result.get(i);
            // 创建单元格，并设置值
            int j = 0;
            insertCell(row, j++, data.getCaddressCode() == null ? "" : data.getCaddressCode());
            insertCell(row, j++, data.getCaddressName() == null ? "" : data.getCaddressName());

            insertCell(row, j++, data.getReceivegoodsCode() == null ? "" : data.getReceivegoodsCode());
            insertCell(row, j++, data.getReceivegoodsName() == null ? "" : data.getReceivegoodsName());
            insertCell(row, j++, data.getRetiredgoodsCode() == null ? "" : data.getRetiredgoodsCode());
            insertCell(row, j++, data.getRetiredgoodsName() == null ? "" : data.getRetiredgoodsName());
            insertCell(row, j++, data.getPurchasetipaccessCode() == null ? "" : data.getPurchasetipaccessCode());
            insertCell(row, j++, data.getPurchasetipaccessName() == null ? "" : data.getPurchasetipaccessName());
            insertCell(row, j++, data.getPurchasebidCode() == null ? "" : data.getPurchasebidCode());
            insertCell(row, j++, data.getPurchasebidName() == null ? "" : data.getPurchasebidName());
            insertCell(row, j++, data.getPayeeCode() == null ? "" : data.getPayeeCode());
            insertCell(row, j++, data.getPayeeName() == null ? "" : data.getPayeeName());

            insertCell(row, j++, data.getDcreateDate() == null ? "" : sdf.format(data.getDcreateDate()));
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
            insertCell(row, j++, data.getCcreateBy() == null ? "" : data.getCcreateBy());
            insertCell(row, j++, data.getCupdateBy() == null ? "" : data.getCupdateBy());
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


    /**
     * 下载模板
     * @param request
     * @param response
     * @param model
     * @throws Exception
     */
	/*@RequestMapping( value="/downloadModel",method = RequestMethod.GET)
	public void downloadModel(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String path="/excelModel/config/办公地点导入模板.xlsx";
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