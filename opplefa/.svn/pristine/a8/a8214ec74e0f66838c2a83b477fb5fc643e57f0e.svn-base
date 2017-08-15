package com.opple.fa.config.webapp.controller;


import com.opple.fa.config.entity.DeptCostCenter;
import com.opple.fa.config.model.DeptCostCenterModel;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.DeptCostCenterService;
import com.opple.fa.utils.StringDataCas;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.entity.Department;
import com.opple.security.service.CompanyService;
import com.opple.security.service.CostCenterService;
import com.opple.security.service.DepartmentService;
import com.opple.util.ExecuteResult;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/config/deptCostCenter")
public class DeptCostCenterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeptCostCenter.class);

    //private static final Map<String, String> COLUMN_MAP = new HashMap<String, String>();

    @Resource
    private DeptCostCenterService deptCostCenterService;
    @Resource
    private CompanyService companyService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private CostCenterService costCenterService;

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


    private ExecuteResult<List<DeptCostCenter>> batchAdd(ExecuteResult<List<DeptCostCenter>> result, List<String> codes, HttpServletRequest request) {
        List<DeptCostCenter> deptCostCenterList = new ArrayList<DeptCostCenter>();
        List<String> list = new LinkedList<String>();
        String loginUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        if (codes.size() == 0) {
            result.setSuccessMessage("请至少输入一行存放位置编码不为空的数据!");
            return result;
        }
        StringBuilder sb = arrayUnique(codes, list, 0); //去掉相同deptCode
        if (sb != null) {
            result.setSuccessMessage(sb.toString());
            return result;
        }
//        List<String> sameCodes = deptCostCenterService.selectExistCodes(list);
//        if (sameCodes.size() > 0) {
//            result.setSuccessMessage("以下编码" + org.apache.commons.lang.StringUtils.join(list.toArray(), ",") + "在数据库已经存在,数据操作不允许，操作未执行！");
//            return result;
//        }
        String[] deptCodes = request.getParameterValues("deptCode");
        String[] deptNames = request.getParameterValues("deptName");
        String[] costCenterCodes = request.getParameterValues("costCenterCode");
        String[] costCenterNamees = request.getParameterValues("costCenterName");
        String[] companyCodes = request.getParameterValues("companyCode");
        String[] companyNames = request.getParameterValues("companyName");
        String[] statuss = request.getParameterValues("status");
        String[] memos = request.getParameterValues("memo");
        sb = new StringBuilder("");
        for (int i = 0; i < deptCodes.length; i++) {
            String deptCode = null == deptCodes[i] ? null : deptCodes[i].trim();
            String deptName = null == deptNames[i] ? null : deptNames[i].trim();
            String costCenterCode = null == costCenterCodes[i] ? null : costCenterCodes[i].trim();
            String costCenterName = null == costCenterNamees[i] ? null : costCenterNamees[i].trim();
            String companyCode = null == companyCodes[i] ? null : companyCodes[i].trim();
            String companyName = null == companyNames[i] ? null : companyNames[i].trim();
            String status = null == statuss[i] ? null : statuss[i].trim();
            String memo = null == memos[i] ? null : memos[i].trim();

            DeptCostCenter deptCostCenter = new DeptCostCenter();
            deptCostCenter.setDeptCode(deptCode);
            deptCostCenter.setDeptName(deptName);
            deptCostCenter.setCostCenterName(costCenterName);
            deptCostCenter.setCostCenterCode(costCenterCode);
            deptCostCenter.setCompanyCode(companyCode);
            deptCostCenter.setCompanyName(companyName);
            deptCostCenter.setMemo(memo);
            deptCostCenter.setStatus(status);
            deptCostCenter.setCreateBy(loginUserName);
            deptCostCenter.setCreateByCode(loginUserCode);
            deptCostCenter.setUpdateBy(loginUserName);
            deptCostCenter.setUpdateByCode(loginUserCode);
            deptCostCenterList.add(deptCostCenter);
            sb = StringDataCas.checkFieldCount(deptCostCenter, sb);
        }
        if (StringDataCas.notEmpty(sb)) {
            result.setSuccessMessage(sb.toString());
            return result;
        }


        try {
            result = deptCostCenterService.saveBatch(deptCostCenterList);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            result.setSuccessMessage("添加失败!");
        }
        return result;
    }

    /**
     * 查询
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param deptCostCenter
     * @return
     */
    @RequestMapping("/searchPagerList")
    public String searchStandCompConfList(Model model, HttpServletRequest req, Long offset, Long pageSize, DeptCostCenter deptCostCenter) {
        Pager<DeptCostCenter> pager = new Pager<DeptCostCenter>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<DeptCostCenter> deptCostCenterPager = deptCostCenterService.searchPagerList(pager, deptCostCenter);
        deptCostCenterPager.setUri(req.getRequestURI());
        model.addAttribute("deptCostCenterPager", deptCostCenterPager);
        model.addAttribute("param", deptCostCenter);
        return "config/deptCostCenter_list";
    }


    @RequestMapping("/add")
    @ResponseBody
    public ExecuteResult<List<DeptCostCenter>> add(HttpServletRequest request) {
        ExecuteResult<List<DeptCostCenter>> result = new ExecuteResult<List<DeptCostCenter>>();
        DeptCostCenter deptCostCenter = new DeptCostCenter();
        String[] deptCodes = request.getParameterValues("deptCode");
        if (deptCodes.length == 0 || !StringDataCas.notEmpty(deptCodes[0])) {
            result.setSuccessMessage("预算体编码不能为空");
            return result;
        }
        ArrayList<String> codes = new ArrayList<String>();
        for (int i = 0; i < deptCodes.length; i++) {
            codes.add(deptCodes[i]);
        }
        result = batchAdd(result, codes, request);
        return result;

    }

    @RequestMapping("/addDeptCostCenter")
    @ResponseBody
    public ExecuteResult<DeptCostCenter> addDeptCostCenter(HttpServletRequest request, @RequestBody DeptCostCenter deptCostCenter) {

        String loginUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        ExecuteResult<DeptCostCenter> result = new ExecuteResult<DeptCostCenter>();
        deptCostCenter.setCreateBy(loginUserName);
        deptCostCenter.setCreateByCode(loginUserCode);
        deptCostCenter.setUpdateByCode(loginUserCode);
        deptCostCenter.setUpdateBy(loginUserName);
        Integer exist = deptCostCenterService.selectExistCodes(deptCostCenter);
        if (exist > 0) {
            result.setSuccessMessage("公司与预算体已存在，请重新选择!");
            return result;
        } else {
            try {
                deptCostCenterService.save(deptCostCenter);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
                result.setSuccessMessage("添加失败!");

            }
            result.setSuccessMessage("添加成功!");
            return result;
        }


    }


    @RequestMapping("/addReady")
    public String addReady() {
        return "config/deptCostCenter_add";

    }

    /**
     * 删除
     *
     * @return
     * @param// DeptCostCenterModel
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ExecuteResult<DeptCostCenter> delete(@RequestBody DeptCostCenterModel deptCostCenterModel, HttpServletRequest req) {
        ExecuteResult<DeptCostCenter> result = new ExecuteResult<DeptCostCenter>();
        String updateBy = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String updateByCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        List<DeptCostCenter> deptCostCenterList = new ArrayList<DeptCostCenter>();
        if (deptCostCenterModel.getDeptCodes().length > 0) {
            for (int i = 0; i < deptCostCenterModel.getDeptCodes().length; i++) {
                DeptCostCenter deptCostCenter = new DeptCostCenter();
                deptCostCenter.setDeptCode(deptCostCenterModel.getDeptCodes()[i]);
                deptCostCenter.setCompanyCode(deptCostCenterModel.getCompanyCodes()[i]);
                deptCostCenter.setUpdateBy(updateBy);
                deptCostCenter.setUpdateByCode(updateByCode);
                deptCostCenter.setStatus(STATUS.NO.getStatus());
                deptCostCenterList.add(deptCostCenter);
            }
        }

        result = deptCostCenterService.delete(deptCostCenterList);
        return result;

    }

    /**
     * 修改前显示
     *
     * @param
     * @return
     */
    @RequestMapping("/updateReady")
    public String update(DeptCostCenter deptCostCenter, Model model, boolean flag) {
        DeptCostCenter dept = deptCostCenterService.get(deptCostCenter);
        model.addAttribute("deptCostCenter", dept);
        return flag ? "config/deptCostCenter_update" : "config/deptCostCenter_show";

    }

    /**
     * 修改
     *
     * @param deptCostCenter
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ExecuteResult<DeptCostCenter> update(@RequestBody DeptCostCenter deptCostCenter, HttpServletRequest req) {
        String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        deptCostCenter.setUpdateBy(loginUserName);
        deptCostCenter.setUpdateByCode(loginUserCode);
        ExecuteResult<DeptCostCenter> result = new ExecuteResult<DeptCostCenter>();
        Integer exist = deptCostCenterService.selectExistCodes(deptCostCenter);
        if (exist > 0) {
            result.setSuccessMessage("公司与预算体已存在，请重新选择!");
            return result;
        } else {
            try {
                result = deptCostCenterService.update(deptCostCenter);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
                result.setSuccessMessage("更新失败!");
            }
            return result;
        }


    }

    @RequestMapping("/get")
    @ResponseBody
    public DeptCostCenter get(DeptCostCenter deptCostCenter) {
        DeptCostCenter dept = deptCostCenterService.get(deptCostCenter);
        return dept;
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
        DecimalFormat df = new DecimalFormat("######0.00");
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
        //Row row1 = sheet.getRow(0);

        List<DeptCostCenter> addList = new ArrayList<DeptCostCenter>();
        List<DeptCostCenter> updateList = new ArrayList<DeptCostCenter>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            DeptCostCenter deptCostCenter = new DeptCostCenter();

            //公司编码
            if (null != row.getCell(0) && !"".equals(row.getCell(0).toString().trim())) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                deptCostCenter.setCompanyCode(row.getCell(0).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的公司编码为空 !\n";
            }
            //公司名称
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                deptCostCenter.setCompanyName(row.getCell(1).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的公司名称为空 !\n";
            }
            //预算体编码
            if (null != row.getCell(2) && !"".equals(row.getCell(2).toString().trim())) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                deptCostCenter.setDeptCode(row.getCell(2).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的预算体编码为空 !\n";
            }
            //预算体名称
            if (null != row.getCell(3) && !"".equals(row.getCell(3).toString().trim())) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                deptCostCenter.setDeptName(row.getCell(3).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的预算体名称为空 !\n";
            }
            //成本中心编码
            if (null != row.getCell(4) && !"".equals(row.getCell(4).toString().trim())) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);

                String regex = ".*[a-zA-Z]+.*";
                Matcher m = Pattern.compile(regex).matcher(row.getCell(4).getStringCellValue().trim());
                if (!m.matches()) {
                    deptCostCenter.setCostCenterCode(StringUtils.leftPad(row.getCell(4).getStringCellValue().trim(), 10, STATUS.YES_ZERO.getStatus()));
                } else {
                    deptCostCenter.setCostCenterCode(row.getCell(4).getStringCellValue().trim());
                }
            } else {
                errorNull = errorNull + "第" + i + "行的成本中心编码为空 !\n";
            }
            //成本中心
            if (null != row.getCell(5) && !"".equals(row.getCell(5).toString().trim())) {
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                deptCostCenter.setCostCenterName(row.getCell(5).getStringCellValue().trim());
            } else {
                errorNull = errorNull + "第" + i + "行的成本中心为空 !\n";
            }
            //备注
            if (null != row.getCell(6) && !"".equals(row.getCell(6).toString().trim())) {
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                deptCostCenter.setMemo(row.getCell(6).getStringCellValue().trim());
            }
            //状态
            if (null != row.getCell(7) && !"".equals(row.getCell(7).toString().trim())) {
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                deptCostCenter.setStatus(row.getCell(7).getStringCellValue().trim());
            } else {
                deptCostCenter.setStatus(STATUS.YES.getStatus());
            }
            deptCostCenter.setUpdateByCode(userCode);
            deptCostCenter.setUpdateBy(userName);

            if (StringUtils.isBlank(errorNull)) {
                if (companyService.countCompanyByCodeAndName(deptCostCenter.getCompanyCode(), deptCostCenter.getCompanyName()) == 0) {
                    errorNull = errorNull + "第" + i + "行的公司不存在 !\n";
                }
                if (costCenterService.countCostCenterByCodeAndName(deptCostCenter.getCostCenterCode(), deptCostCenter.getCostCenterName()) == 0) {
                    errorNull = errorNull + "第" + i + "行的成本中心不存在 !\n";
                }
                List<Department> depts = departmentService.countDeptByCodeAndName(deptCostCenter.getDeptCode());
                if (depts.size() == 0) {
                    errorNull = errorNull + "第" + i + "行的预算体不存在 !\n";
                } else {
                    deptCostCenter.setDeptName(depts.get(0).getBudgetDepNameNavi());
                }
            }

            if (errorNull == "") {
                if (deptCostCenterService.checkDeptCostCenterByEntity(deptCostCenter) > 0) {
                    updateList.add(deptCostCenter);
                } else {
                    deptCostCenter.setCreateByCode(userCode);
                    deptCostCenter.setCreateBy(userName);
                    addList.add(deptCostCenter);
                }
            }
        }
        if (StringUtils.isBlank(errorNull)) {
            String result = deptCostCenterService.addOrUpdateDeptCostCenter(addList, updateList);
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
//			response.getWriter().write(JSONObject.fromObject(jsonMap).toString());
            response.getWriter().write(JSONObject.fromObject(jsonMap).toString() + "<script>document.domain='opple.com'</script>");
            response.getWriter().flush();
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return null;
    }


    /**
     * 导出
     *
     * @param request
     * @param response
     * @param deptCostCenter
     * @throws Exception
     */
    @RequestMapping(value = "/exportFile", method = RequestMethod.GET)
    @ResponseBody
    public void exportFile(HttpServletRequest request,
                           HttpServletResponse response, DeptCostCenter deptCostCenter) throws Exception {
        // deptCostCenter = getQueryCriteria(deptCostCenter);
        List<DeptCostCenter> deptCostCenters = deptCostCenterService.getList(deptCostCenter);

        String fileName = "预算体-成本中心对应表.xlsx";
        String sheetName = "预算体-成本中心对应表";
        int[] columnWidths = new int[]{10, 10, 10};
        Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
        data.put(STATUS.NO_ONE.getStatus(), new Object[]{"所属公司编码", "预算体编码", "预算体名称", "成本中心编码", "成本中心名称", "创建人", "创建时间", "最后维护人", "最后维护时间", "备注", "状态"});
        int dataIdx = 2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder companyCode = null;
        StringBuilder deptCode = null;
        StringBuilder deptName = null;
        StringBuilder costCenterCode = null;
        StringBuilder costCenterName = null;
        StringBuilder createBy = null;
        StringBuilder createDate = null;
        StringBuilder updateBy = null;
        StringBuilder lastDate = null;
        StringBuilder memo = null;
        StringBuilder status = null;

        for (DeptCostCenter deptCostCenter1 : deptCostCenters) {
            companyCode = new StringBuilder(deptCostCenter1.getCompanyCode() == null ? "" : deptCostCenter1.getCompanyCode());
            deptCode = new StringBuilder(deptCostCenter1.getDeptCode() == null ? "" : deptCostCenter1.getDeptCode());
            deptName = new StringBuilder(deptCostCenter1.getDeptName() == null ? "" : deptCostCenter1.getDeptName());
            costCenterCode = new StringBuilder(deptCostCenter1.getCostCenterCode() == null ? "" : deptCostCenter1.getCostCenterCode());
            costCenterName = new StringBuilder(deptCostCenter1.getCostCenterName() == null ? "" : deptCostCenter1.getCostCenterName());
            createBy = new StringBuilder(deptCostCenter1.getCreateBy() == null ? "" : deptCostCenter1.getCreateBy());
            createDate = new StringBuilder(deptCostCenter1.getCreateDate() == null ? "" : sdf.format(deptCostCenter1.getCreateDate()));
            updateBy = new StringBuilder(deptCostCenter1.getUpdateBy() == null ? "" : deptCostCenter1.getUpdateBy());
            lastDate = new StringBuilder(deptCostCenter1.getLastDate() == null ? "" : sdf.format(deptCostCenter1.getLastDate()));
            memo = new StringBuilder(deptCostCenter1.getMemo() == null ? "" : deptCostCenter1.getMemo());

            if (StringUtils.isNotBlank(deptCostCenter1.getStatus())) {
                if (STATUS.YES.getStatus().equals(deptCostCenter1.getStatus())) {
                    status = new StringBuilder(STATUS.EFFECTIVE.getStatus());
                } else if (STATUS.NO.getStatus().equals(deptCostCenter1.getStatus())) {
                    status = new StringBuilder(STATUS.INVALID.getStatus());
                } else {
                    status = new StringBuilder(deptCostCenter1.getStatus());
                }
            } else {
                status = new StringBuilder("");
            }

            Object[] newRow = new Object[]{
                    companyCode.toString(),
                    deptCode.toString(),
                    deptName.toString(),
                    costCenterCode.toString(),
                    costCenterName.toString(),
                    createBy.toString(),
                    createDate.toString(),
                    updateBy.toString(),
                    lastDate.toString(),
                    memo.toString(),
                    status.toString()
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
            ExportDeptCostCenterTemplate exportDeptCostCenterTemplate = new ExportDeptCostCenterTemplate();
            exportDeptCostCenterTemplate.doExport(response, fileName, deptCostCenters);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }*/

    }

}
