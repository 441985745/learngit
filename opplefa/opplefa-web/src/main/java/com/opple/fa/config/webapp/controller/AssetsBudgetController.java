package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.config.entity.AssetsBudgetFromBUD;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.export.controller.ExportAssetsBudgetTemplate;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AssetsBudgetFromBUDService;
import com.opple.fa.config.service.AssetsBudgetService;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.model.DemandOrderDetailModel;
import com.opple.fa.purchase.service.DemandOrderDetailService;
import com.opple.fa.purchase.service.DemandOrderService;
import com.opple.fa.webapp.controller.BaseOpenApiController;
import com.opple.security.SessionSecurityConstants;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.workflow.service.WorkFlowService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lk on 10/24/16.
 */
@Controller
@RequestMapping("/assetBudget")
public class AssetsBudgetController extends BaseOpenApiController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AssetsBudgetController.class);
    @Resource
    private AssetsBudgetService assetsBudgetService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private DemandOrderDetailService demandOrderDetailService;
    @Resource
    private DemandOrderService demandOrderService;
    @Resource
    private AssetsBudgetFromBUDService assetsBudgetFromBUDService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private AttchConfigService attchConfigService;

    // adjustAssetsBudgets
    @RequestMapping("/searchAssetsBudgets")
    public String searchAssetsBudgets(Model model, HttpServletRequest req, Long offset, Long pageSize,
                                      AssetsBudget assetsBudget) {
        Pager<AssetsBudget> pager = new Pager<AssetsBudget>();
        String version = "";
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        assetsBudget.setCostCenterNameOrCode(null == assetsBudget.getCostCenterNameOrCode() ? "" : assetsBudget.getCostCenterNameOrCode().trim());
        assetsBudget.setPlatform(null == assetsBudget.getPlatform() ? "" : assetsBudget.getPlatform().trim());
        assetsBudget.setProject(null == assetsBudget.getProject() ? "" : assetsBudget.getProject().trim());
        assetsBudget.setBudgetYear(null == assetsBudget.getBudgetYear() ? "" : assetsBudget.getBudgetYear().trim());
        assetsBudget.setCreateBy(null == assetsBudget.getCreateBy() ? "" : assetsBudget.getCreateBy().trim());
        String userCode = (String) req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        assetsBudget.setLoginUserCode(loginUserCode);
        assetsBudget.setAdminCode(Env.getProperty("role.assetAdmin.id"));
        Pager<AssetsBudget> assetsBudgetsPager = assetsBudgetService.searchAssetsBudgets(pager, assetsBudget);
        List<AssetsBudget> assetsBudgets = assetsBudgetsPager.getRecords();
        for (AssetsBudget assetsBudget2 : assetsBudgets) {
            if (!"".equals(assetsBudget2.getVersion()) || null != assetsBudget2.getVersion()) {
                version = assetsBudget2.getVersion();
            }
        }
/*		String project = assetsBudget.getProject();
        String costCenterNameOrCode = assetsBudget.getCostCenterNameOrCode();*/
        assetsBudgetsPager.setUri(req.getRequestURI());
        model.addAttribute("version", version);
        model.addAttribute("obj", assetsBudget);
/*		model.addAttribute("costCenterNameOrCode", costCenterNameOrCode);*/
        model.addAttribute("assetsBudgetsPager", assetsBudgetsPager);
        return "purchase/budget_import";
    }
    /*@RequestMapping("/adjustAssetsBudgets")
    public String adjustAssetsBudgets(Model model, HttpServletRequest req, Long offset, Long pageSize,
			AssetsBudget assetsBudget) {
		Pager<AssetsBudget> pager = new Pager<AssetsBudget>();
		pager.setUri(req.getRequestURI());
		pager.setOffset(null == offset ? pager.getOffset() : offset);
		pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

		Pager<AssetsBudget> assetsBudgetsPager = assetsBudgetService.searchAssetsBudgets(pager, assetsBudget);
		assetsBudgetsPager.setUri(req.getRequestURI());
		model.addAttribute("assetsBudgetsPager", assetsBudgetsPager);
		return "purchase/budget_adjust";
	}*/

    @RequestMapping(value = "/api/searchAssetsBudgetsList", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray searchAssetsBudgetsList(AssetsBudget assetsBudget) {

        List<AssetsBudget> assetsBudgets = assetsBudgetService.searchAssetsBudgetsList(assetsBudget);

        //把当前订单占用的预算排除
        DemandOrder demandOrder = new DemandOrder();
        demandOrder.setCdocument(assetsBudget.getDemandOrderId());

        DemandOrder searchDemandOrderByOrderId = demandOrderService.searchDemandOrderByOrderId(demandOrder);
        if (searchDemandOrderByOrderId != null && STATUS.NO.getStatus().equals(searchDemandOrderByOrderId.getDraft())) {
            List<DemandOrderDetailModel> demandOrderDetailModels = demandOrderDetailService.searchDemandOrderDetailByOrderId(demandOrder);
            if (demandOrderDetailModels.size() != 0) {
                for (DemandOrderDetailModel dod : demandOrderDetailModels) {
                    for (AssetsBudget ab : assetsBudgets) {
                        if (dod.getProjectCode().equals(ab.getProjectCode()) && dod.getBudgetYear().equals(ab.getBudgetYear())) {
                            //已用金额
                            ab.setUsedSumMoney(ab.getUsedSumMoney().subtract(dod.getReferencePrice()));
                            //可用金额
                            ab.setAvaliableSumMoney(ab.getAvaliableSumMoney().add(dod.getReferencePrice()));
                        }
                    }
                }
            }
        }
        // model.addAttribute("assetsBudgetsPager", assetsBudgetsPager);
        // return this.pagerToDataGridObject(assetsBudgetsPager);
        if (assetsBudgets == null) {
            return null;
        } else {
            JsonConfig config = new JsonConfig();
            config.setIgnoreDefaultExcludes(false);
            config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            // 只要设置这个数组，指定过滤哪些字段。
            config.setExcludes(new String[]{
                    "createDate", "updateDate", "fromDate", "toDate", "manitDate"});
            net.sf.json.JSONArray jsonArr = net.sf.json.JSONArray.fromObject(assetsBudgets, config);
            return jsonArr;
        }
    }

    @RequestMapping(value = "/api/getAssetsBudgets", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getAssetsBudgets(AssetsBudget assetsBudget) {

        List<AssetsBudget> assetsBudgets = assetsBudgetService.searchAssetsBudgetsList(assetsBudget);

        // model.addAttribute("assetsBudgetsPager", assetsBudgetsPager);
        // return this.pagerToDataGridObject(assetsBudgetsPager);
        if (assetsBudgets == null) {
            return null;
        } else {
            JsonConfig config = new JsonConfig();
            config.setIgnoreDefaultExcludes(false);
            config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            // 只要设置这个数组，指定过滤哪些字段。
            config.setExcludes(new String[]{
                    "createDate", "updateDate", "fromDate", "toDate", "manitDate"});
            net.sf.json.JSONArray jsonArr = net.sf.json.JSONArray.fromObject(assetsBudgets, config);
            return jsonArr;
        }
    }

    @RequestMapping(value = "/api/searchAssetsBudgets", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject searchAssetsBudgets(AssetsBudget assetsBudget, Pager<AssetsBudget> pager) {

        Pager<AssetsBudget> assetsBudgetsPager = assetsBudgetService.searchAssetsBudgets(pager, assetsBudget);
        // model.addAttribute("assetsBudgetsPager", assetsBudgetsPager);
        // return this.pagerToDataGridObject(assetsBudgetsPager);
        if (assetsBudgetsPager == null) {
            return null;
        } else {
            return this.pagerToDataGridObject(assetsBudgetsPager);
        }
    }

    @RequestMapping(value = "/api/Excel", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject importExcel(MultipartHttpServletRequest request, HttpSession session,
                                  HttpServletResponse response) {
        String userName = (String) session.getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
        String userId = (String) session.getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        String errorNull = "";
        // 获得文件上传的文件
        MultipartFile myfile = request.getFile("shangchuan");

        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(myfile.getInputStream());
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        Sheet sheet = wb.getSheetAt(0);

        List<AssetsBudget> listForInsert = new ArrayList<AssetsBudget>();
        List<AssetsBudget> listForUpdate = new ArrayList<AssetsBudget>();
        // 返回物理行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            // 获取第i行数据
            Row row = sheet.getRow(i);
            AssetsBudget assetsBudget = new AssetsBudget();

            if (null != row.getCell(0) && !"".equals(row.getCell(0))) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(0).getStringCellValue().trim())) { // 成本中心名称
                    assetsBudget.setCostCenterName(row.getCell(0).getStringCellValue().trim());
                }
            }
            if (null != row.getCell(1) && !"".equals(row.getCell(1))) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(1).getStringCellValue().trim())) {
                    String regex = ".*[a-zA-Z]+.*";
                    Matcher m = Pattern.compile(regex).matcher(row.getCell(1).getStringCellValue().trim());
                    if (!m.matches()) {
                        assetsBudget.setCostCenter(StringUtils.leftPad(row.getCell(1).getStringCellValue().trim(), 10, STATUS.YES_ZERO.getStatus()));
                    } else {
                        assetsBudget.setCostCenter(row.getCell(1).getStringCellValue().trim());
                    }
                }
                // 成本中心
                //assetsBudget.setCostCenter(StringUtils.leftPad(row.getCell(1).getStringCellValue().trim(),10,"0"));
            }
            if (null != row.getCell(2) && !"".equals(row.getCell(2))) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(2).getStringCellValue().trim())) { // 年度
                    assetsBudget.setBudgetYear(row.getCell(2).getStringCellValue().trim());
                }
            } else {
                errorNull = errorNull + "第" + i + "行的年度不能为空 !\n";
            }
            if (null != row.getCell(3) && !"".equals(row.getCell(3))) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(3).getStringCellValue().trim())) { // 项目编码
                    assetsBudget.setProjectCode(row.getCell(3).getStringCellValue().trim());
                }
            }
            if (null != row.getCell(4) && !"".equals(row.getCell(4))) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(4).getStringCellValue().trim())) { // 类型
                    assetsBudget.setAssetsModel(row.getCell(4).getStringCellValue().trim());
                }
            }
            if (null != row.getCell(5) && !"".equals(row.getCell(5))) {
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(5).getStringCellValue().trim())) { // 资产类型
                    assetsBudget.setAssetsType(row.getCell(5).getStringCellValue().trim());
                }
            }
            if (null != row.getCell(6) && !"".equals(row.getCell(6))) {
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(6).getStringCellValue().trim())) { // 资产名称
                    assetsBudget.setAssetsName(row.getCell(6).getStringCellValue().trim());
                }
            }
            if (null != row.getCell(7) && !"".equals(row.getCell(7))) {
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(7).getStringCellValue().trim())) { // 项目名称
                    assetsBudget.setProjectName(row.getCell(7).getStringCellValue().trim());
                }
            }
            if (null != row.getCell(8) && !"".equals(row.getCell(8))) {
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(8).getStringCellValue().trim())) { // 单位
                    assetsBudget.setUnit(row.getCell(8).getStringCellValue().trim());
                }
            }
            if (null != row.getCell(9) && !"".equals(row.getCell(9))) {
                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(9).getStringCellValue().trim())) { // 数量
                    assetsBudget.setAmount(Integer.parseInt(row.getCell(9).getStringCellValue().trim()));
                }
            }
            if (null != row.getCell(10) && !"".equals(row.getCell(10))) {
                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(10).getStringCellValue().trim())) { // 单价
                    assetsBudget.setUnitPrice(new BigDecimal(row.getCell(10).getStringCellValue().trim()));
                }
            }
            if (null != row.getCell(11) && !"".equals(row.getCell(11))) {
                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(11).getStringCellValue().trim())) { // 预算依据
                    assetsBudget.setBudgetBasedOn(row.getCell(11).getStringCellValue().trim());
                }
            }
            if (null != row.getCell(12) && !"".equals(row.getCell(12))) {
                row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                if (!"".equals(row.getCell(12).getStringCellValue().trim())) { // 年度预算总金额
                    assetsBudget.setYearBudgetTotal(new BigDecimal(row.getCell(12).getStringCellValue().trim()));
                }
            }
            AssetsBudgetFromBUD assetsBudgetFromBUD = new AssetsBudgetFromBUD();
            UserInfo userInfo = new UserInfo();
            AttchConfig attchConfig = new AttchConfig();
            if (null != assetsBudget.getCostCenter()) {
                assetsBudgetFromBUD.setCostCenter(assetsBudget.getCostCenter());
                userInfo = userInfoService.searchCostcenterName(assetsBudgetFromBUD);
                attchConfig = attchConfigService.searchByCostcenterCode(assetsBudgetFromBUD);
            }
            if (null != userInfo) {
                assetsBudget.setCostCenterName(userInfo.getCcostcentername());
            } else {
                assetsBudget.setCostCenterName("");
            }

            if (null != attchConfig) {
                assetsBudget.setPlatform(attchConfig.getPlatform());
            } else {
                assetsBudget.setPlatform("");
            }
            assetsBudget.setUsedAmount(0);
            assetsBudget.setUsedSumMoney(new BigDecimal(STATUS.YES_ZERO.getStatus()));
            assetsBudget.setAvaliableAmount(assetsBudget.getAmount());
            assetsBudget.setAvaliableSumMoney(assetsBudget.getYearBudgetTotal());
            assetsBudget.setCreateBy(userName);
            assetsBudget.setCreateByCode(userId);
            assetsBudget.setCreateDate(new Date());
            String projectCode = "";
            if (null != row.getCell(3)) {
                projectCode = row.getCell(3).getStringCellValue().trim();
            }
            //判断Exsl项目编码是否为空
            if ("".equals(projectCode)) {
                assetsBudget.setProjectCode(workFlowService.getDocumentByType("FA_ASBG"));
                listForInsert.add(assetsBudget);
            } else {
                //判断数据库中项目编码是否存在
                if (assetsBudgetService.getAssetsBudgetsbyProjectCode(assetsBudget) == 0) {
                    errorNull = errorNull + "第" + i + "行的项目编码不存在！\n";
                } else {
                    //判断项目编码对应的年度是否存在（若存在则更新，若不存在说明导入新的年度预算则新增）
                    if (assetsBudgetService.getAssetsBudgetsbyProjectCodeAndYear(assetsBudget) == 0) {
                        assetsBudget.setProjectCode(workFlowService.getDocumentByType("FA_ASBG"));
                        listForInsert.add(assetsBudget);
                    } else {
                        //AssetsBudget assetsBudget1 = new AssetsBudget();
                        BudgetAdjustmentDetail budgetAdjustmentDetail = assetsBudgetService.getBudgetDetailsbyProjectCodeAndYear(assetsBudget.getBudgetYear(), assetsBudget.getProjectCode());
                        if (new BigDecimal(budgetAdjustmentDetail.getUsedSumMoney()).compareTo(assetsBudget.getYearBudgetTotal()) == 1) {
                            errorNull = errorNull + "第" + i + "行的预算金额小于已用金额！\n";
                        } else {
                            assetsBudget.setUsedSumMoney(new BigDecimal(budgetAdjustmentDetail.getUsedSumMoney()));
                            assetsBudget.setAvaliableSumMoney(assetsBudget.getYearBudgetTotal().subtract(new BigDecimal(budgetAdjustmentDetail.getUsedSumMoney())));
                            listForUpdate.add(assetsBudget);
                        }
                    }
                }
            }
        }
        if (StringUtils.isBlank(errorNull)) {
            jsonMap.put("success", true);
            // 将处理好的数据进行导入（listForInsert为新增数据，listForUpdate为更新数据）
            assetsBudgetService.importAssetsBudgetExcel(listForInsert, listForUpdate);
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

    // excel 天数转换
    /*
     * private String getDataExcel(String data) {
	 * 
	 * Calendar c = new GregorianCalendar(1900,0,-1); Date d = c.getTime(); Date
	 * _d = DateUtils.addDays(d, Integer.parseInt(data)); //计算日期 return
	 * _d.toLocaleString(); }
	 */

    /**
     * 导出
     *
     * @param request
     * @param response
     * @param assetsBudget
     * @return
     */
    @RequestMapping(value = "/Export", method = RequestMethod.GET)
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response, AssetsBudget assetsBudget) {

        String fileName = "预算数据.xlsx";
        assetsBudget.setLoginUserCode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        assetsBudget.setAdminCode(Env.getProperty("role.assetAdmin.id"));
        List<AssetsBudget> assetsBudgetList = assetsBudgetService.exportAssetsBudget(assetsBudget);
        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportAssetsBudgetTemplate epat = new ExportAssetsBudgetTemplate();
            epat.doExport(response, fileName, assetsBudgetList);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }

    }

    /**
     * 确认
     *
     * @param ids
     * @return executeResult
     */
    @RequestMapping("/api/confirmBudget")
    @ResponseBody
    public ExecuteResult<AssetsBudget> confirmBudget(String ids) {
        ExecuteResult<AssetsBudget> result = new ExecuteResult<AssetsBudget>();
        String[] assetsBudgetIds = ids.split(",");

        AssetsBudget assetsBudget = new AssetsBudget();
        if (assetsBudgetIds != null && assetsBudgetIds.length != '0') {
            for (String string : assetsBudgetIds) {
                assetsBudget.setId(Integer.parseInt(string));
                assetsBudgetService.confirmBudget(assetsBudget);
            }
        } else {
            result.addErrorMessage("请至少选择一条数据");
        }

        return result;
    }

    /**
     * 取消确认
     *
     * @param ids
     * @return executeResult
     */
    @RequestMapping("/api/cancelConfirmBudget")
    @ResponseBody
    public ExecuteResult<AssetsBudget> cancelConfirmBudget(String ids) {
        ExecuteResult<AssetsBudget> result = new ExecuteResult<AssetsBudget>();
        String[] assetsBudgetIds = ids.split(",");

        AssetsBudget assetsBudget = new AssetsBudget();
        if (assetsBudgetIds != null && assetsBudgetIds.length != '0') {
            for (String string : assetsBudgetIds) {
                assetsBudget.setId(Integer.parseInt(string));
                if (0 != assetsBudgetService.getAssetsBudget(assetsBudget).getUsedAmount()) {
                    result.addErrorMessage("所选择预算已被占用，请重新选择！");
                    return result;
                } else {
                    assetsBudgetService.concelConfirmBudget(assetsBudget);
                }

            }
        } else {
            result.addErrorMessage("请至少选择一条数据");
        }

        return result;
    }

    /**
     * 删除预算
     *
     * @param ids
     * @return executeResult
     */
    @RequestMapping("/api/deleteBudget")
    @ResponseBody
    public ExecuteResult<AssetsBudget> deleteBudget(String ids) {
        ExecuteResult<AssetsBudget> result = new ExecuteResult<AssetsBudget>();
        String[] assetsBudgetIds = ids.split(",");

        AssetsBudget assetsBudget = new AssetsBudget();
        if (assetsBudgetIds != null && assetsBudgetIds.length != '0') {
            for (String string : assetsBudgetIds) {
                assetsBudget.setId(Integer.parseInt(string));
                if (0 != assetsBudgetService.getAssetsBudget(assetsBudget).getUsedAmount()) {
                    result.addErrorMessage("所选择预算已被占用，请重新选择！");
                    return result;
                } else {
                    assetsBudgetService.deleteBudget(assetsBudget);
                }
            }
        } else {
            result.addErrorMessage("请至少选择一条数据");
        }

        return result;
    }

    /**
     * 确认全部预算
     *
     * @return executeResult
     */
    @RequestMapping("/api/confirmAllBudget")
    @ResponseBody
    public ExecuteResult<AssetsBudget> confirmAllBudget() {
        ExecuteResult<AssetsBudget> result = new ExecuteResult<AssetsBudget>();
        assetsBudgetService.confirmAllBudget();
        return result;
    }

    @RequestMapping("/api/synchroBudgetFromBUD")
    @ResponseBody
    public ExecuteResult<AssetsBudget> synchroBudgetFromBUD(String version) {
        ExecuteResult<AssetsBudget> result = new ExecuteResult<AssetsBudget>();
        String newVersion = "";
        String errorNull = "";
        List<AssetsBudgetFromBUD> assetsBudgetFromBUDs = assetsBudgetFromBUDService.searchAllAssetsBudgetFromBUD();
        AssetsBudget assetsBudgetOld = new AssetsBudget();
        List<AssetsBudget> listForInsert = new ArrayList<AssetsBudget>();
        List<AssetsBudget> listForUpdate = new ArrayList<AssetsBudget>();
        for (AssetsBudgetFromBUD assetsBudgetFromBUD : assetsBudgetFromBUDs) {
            AssetsBudget assetsBudget = new AssetsBudget();
            if (!"".equals(assetsBudgetFromBUD.getProjectCode())) {
                assetsBudgetOld = assetsBudgetService.getAssetsBudgetbyProjectCodeAndYear(assetsBudgetFromBUD.getYear(), assetsBudgetFromBUD.getProjectCode());
            }
            String regex = ".*[a-zA-Z]+.*";
            Matcher m = Pattern.compile(regex).matcher(assetsBudgetFromBUD.getCostCenter());
            if (!m.matches()) {
                assetsBudgetFromBUD.setCostCenter(StringUtils.leftPad(assetsBudgetFromBUD.getCostCenter(), 10, STATUS.YES_ZERO.getStatus()));
            }

            assetsBudget.setBudgetYear(assetsBudgetFromBUD.getYear());
            assetsBudget.setCostCenter(assetsBudgetFromBUD.getCostCenter());
            UserInfo userInfo = userInfoService.searchCostcenterName(assetsBudgetFromBUD);
            AttchConfig attchConfig = attchConfigService.searchByCostcenterCode(assetsBudgetFromBUD);
            if (null != attchConfig) {
                assetsBudget.setPlatform(attchConfig.getPlatform());
            } else {
                assetsBudget.setPlatform("");
            }
            if (null != userInfo) {
                assetsBudget.setCostCenterName(userInfo.getCcostcentername());
            } else {
                assetsBudget.setCostCenterName("");
            }
            assetsBudget.setProjectCode(assetsBudgetFromBUD.getProjectCode());
            assetsBudget.setAssetsModel(assetsBudgetFromBUD.getType());
            assetsBudget.setAssetsType(assetsBudgetFromBUD.getAssetType());
            assetsBudget.setAssetsName(assetsBudgetFromBUD.getAssetName());
            assetsBudget.setProjectName(assetsBudgetFromBUD.getProjectName());
            assetsBudget.setUnit(assetsBudgetFromBUD.getUnit());
            assetsBudget.setAmount(assetsBudgetFromBUD.getQuantity());
            assetsBudget.setUnitPrice(assetsBudgetFromBUD.getUnitPrice());
            assetsBudget.setBudgetBasedOn(assetsBudgetFromBUD.getAccordingTo());
            assetsBudget.setYearBudgetTotal(assetsBudgetFromBUD.getBudgetPurchaseSum());
            assetsBudget.setUsedAmount(0);
            assetsBudget.setUsedSumMoney(assetsBudgetOld == null ? new BigDecimal(STATUS.YES_ZERO.getStatus()) : assetsBudgetOld.getUsedSumMoney());
            assetsBudget.setAvaliableAmount(assetsBudgetFromBUD.getQuantity());
            assetsBudget.setAvaliableSumMoney(assetsBudgetFromBUD.getBudgetPurchaseSum());
            assetsBudget.setCreateDate(new Date());
            assetsBudget.setCreateBy("Admin");
            assetsBudget.setCreateByCode("p000000001");
            newVersion = assetsBudgetFromBUD.getVersion();
            if (assetsBudgetFromBUD.getVersion().equals(version)) {
                result.addErrorMessage("已同步最新数据！");
                break;
            } else {
                if (null == assetsBudget.getProjectCode() || "" == assetsBudget.getProjectCode() || assetsBudgetService.getAssetsBudgetsbyProjectCode(assetsBudget) == 0
                        || assetsBudgetService.getAssetsBudgetsbyProjectCodeAndYear(assetsBudget) == 0) {
                    assetsBudget.setProjectCode(workFlowService.getDocumentByType("FA_ASBG"));
                    listForInsert.add(assetsBudget);
                } else {
                    if (assetsBudget.getUsedSumMoney().compareTo(assetsBudget.getYearBudgetTotal()) == 1) {
                        errorNull = errorNull + "项目编码" + assetsBudget.getProjectCode() + "的预算金额小于已用金额！\n";
                    } else {
                        AssetsBudget searchAssetsBudget = assetsBudgetService.getAssetsBudgetbyProjectCodeAndYear(assetsBudget.getBudgetYear(), assetsBudget.getProjectCode());
                        searchAssetsBudget.setAvaliableSumMoney(assetsBudgetFromBUD.getBudgetPurchaseSum().subtract(searchAssetsBudget.getUsedSumMoney()));
                        searchAssetsBudget.setYearBudgetTotal(assetsBudgetFromBUD.getBudgetPurchaseSum());
                        searchAssetsBudget.setAmount(assetsBudgetFromBUD.getQuantity());
                        searchAssetsBudget.setUnitPrice(assetsBudgetFromBUD.getUnitPrice());
                        listForUpdate.add(searchAssetsBudget);
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(errorNull)) {
            result.addErrorMessage(errorNull);
            return result;
        }
        try {
            AssetsBudget assetsBudget = new AssetsBudget();
            assetsBudget.setVersion(newVersion);
            assetsBudgetService.importAssetsBudgetExcel(listForInsert, listForUpdate);
            assetsBudgetService.updateVersion(assetsBudget);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 格式化数字为千分位显示；
     *
     * @param text
     * @return
     */
//    public String fmtMicrometer(String text) {
//        DecimalFormat df = null;
//        if (text.indexOf(".") > 0) {
//            if (text.length() - text.indexOf(".") - 1 == 0) {
//                df = new DecimalFormat("###,##0.");
//            } else if (text.length() - text.indexOf(".") - 1 == 1) {
//                df = new DecimalFormat("###,##0.0");
//            } else {
//                df = new DecimalFormat("###,##0.00");
//            }
//        } else {
//            df = new DecimalFormat("###,##0");
//        }
//        double number = 0.0;
//        try {
//            number = Double.parseDouble(text);
//        } catch (Exception e) {
//            number = 0.0;
//        }
//        return df.format(number);
//    }
    /**
     * 确认全部预算
     * @param paymentOrder
     * @param request
     * @return executeResult
     */
    /**
     * 预算调整新增
     *
     * @param model
     * @param request
     * @param response
     * @param assetsBudget
     * @return
     *//*
    @RequestMapping("/addAdjust")
	public String addAdjustAssetsBudgets(Model model, HttpServletRequest req, Long offset, Long pageSize,
			AssetsBudget assetsBudget) {
		Pager<AssetsBudget> pager = new Pager<AssetsBudget>();
		pager.setUri(req.getRequestURI());
		pager.setOffset(null == offset ? pager.getOffset() : offset);
		pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

		Pager<AssetsBudget> assetsBudgetsPager = assetsBudgetService.searchAssetsBudgets(pager, assetsBudget);
		assetsBudgetsPager.setUri(req.getRequestURI());
		model.addAttribute("assetsBudgetsPager", assetsBudgetsPager);
		return "purchase/budget_adjust_add";
	}*/
}
