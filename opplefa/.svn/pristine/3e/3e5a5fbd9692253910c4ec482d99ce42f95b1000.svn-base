package com.opple.fa.config.webapp.controller;

import com.opple.fa.config.entity.AssetsBudgetFromBUD;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.BudgetAdjustment;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.model.ADJUSTTPYE;
import com.opple.fa.config.model.ASSETBUDGETTYPE;
import com.opple.fa.config.model.BudgetAdjustmentDetailArray;
import com.opple.fa.config.model.BudgetAdjustmentRequest;
import com.opple.fa.config.model.DRAFT;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AssetsBudgetService;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.BudgetAdjustmentDetailService;
import com.opple.fa.config.service.BudgetAdjustmentService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.entity.Employee;
import com.opple.security.entity.User;
import com.opple.security.service.RemindedService;
import com.opple.security.service.UserService;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.ExportExcle;
import com.opple.util.Pager;
import com.opple.workflow.entity.ApproveHistory;
import com.opple.workflow.service.WorkFlowService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wangkaixuan on 2017/3/21.
 */
@Controller
@RequestMapping("/budgetAdjustment")
public class BudgetAdjustmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetAdjustmentController.class);
    @Resource
    private BudgetAdjustmentService budgetAdjustmentService;
    @Resource
    private BudgetAdjustmentDetailService budgetAdjustmentDetailService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private UserService userService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private RemindedService remindedService;
    @Resource
    private AssetsBudgetService assetsBudgetService;
    @Resource
    private AttchConfigService attchConfigService;

    /**
     * 预算调整 模糊查询
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param budgetAdjustment
     * @return
     */
    @RequestMapping("/getBudgetAdjustmentList")
    public String adjustAssetsBudgets(Model model, HttpServletRequest req,
                                      Long offset, Long pageSize, BudgetAdjustment budgetAdjustment) {
        String userCode = (String) req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        budgetAdjustment.setLoginUserCode(userCode);

        budgetAdjustment = getQueryCriteria(budgetAdjustment);
        budgetAdjustment.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));

        Pager<BudgetAdjustment> pager = new Pager<BudgetAdjustment>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<BudgetAdjustment> budgetAdjustmentPager = budgetAdjustmentService.getBudgetAdjustmentList(pager, budgetAdjustment);
        budgetAdjustmentPager.setUri(req.getRequestURI());
        model.addAttribute("budgetAdjustmentPager", budgetAdjustmentPager);
        return "purchase/budget_adjustment_list";
    }

    /**
     * 处理查询条件
     * @param budgetAdjustment
     * @return
     */
    private BudgetAdjustment getQueryCriteria(BudgetAdjustment budgetAdjustment) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        budgetAdjustment.setDocument(null == budgetAdjustment.getDocument() ? null : budgetAdjustment.getDocument().trim());
        budgetAdjustment.setUserCode(null == budgetAdjustment.getUserName() ? null : budgetAdjustment.getUserName().trim());
        budgetAdjustment.setBudgetYear(null == budgetAdjustment.getBudgetYear() ? null : budgetAdjustment.getBudgetYear().trim());
        budgetAdjustment.setProjectCode(null == budgetAdjustment.getProjectCode() ? null : budgetAdjustment.getProjectCode().trim());
        if (StringUtils.isNotBlank(budgetAdjustment.getEndDate())) {
            try {
                Date date = sdf.parse(budgetAdjustment.getEndDate());
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DATE, 1);
                budgetAdjustment.setEndDate(sdf.format(cal.getTime()));
            } catch (ParseException e) {
                LOGGER.error("ParseException", e);
            }
        }
        return budgetAdjustment;
    }

    /**
     * 导出
     * @param model
     * @param request
     * @param response
     * @param budgetAdjustment
     * @return
     */
    @RequestMapping(value = "/exportBudgetAdjustment", method = RequestMethod.GET)
    @ResponseBody
    public void exportBudgetAdjustment(ModelMap model, HttpServletRequest request, HttpServletResponse response, BudgetAdjustment budgetAdjustment) {
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        budgetAdjustment.setLoginUserCode(userCode);
        budgetAdjustment = getQueryCriteria(budgetAdjustment);
        budgetAdjustment.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));

        List<BudgetAdjustment> budgetAdjustments = budgetAdjustmentService.exportBudgetAdjustmentList(budgetAdjustment);


        String fileName = "预算调整数据.xlsx";
        String sheetName = "预算调整数据";
        int[] columnWidths = new int[]{10, 10, 10};
        Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
        data.put(STATUS.NO_ONE.getStatus(), new Object[]{"是否草稿", "单据号", "审批状态", "申请人", "申请日期", "预算调整类型", "当前审批人", "维护人", "维护时间"});
        int dataIdx = 2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder document = null;
        StringBuilder approvalState = null;
        StringBuilder userName = null;
        StringBuilder applicationDate = null;
        StringBuilder assetsBudgetType = null;
        StringBuilder nextHandlerName = null;
        StringBuilder updateUserName = null;
        StringBuilder updateDate = null;
        StringBuilder draft = null;
        for (BudgetAdjustment budgetAdjustment1 : budgetAdjustments) {
            document = new StringBuilder(budgetAdjustment1.getDocument() == null ? "" : budgetAdjustment1.getDocument());
            approvalState = new StringBuilder(budgetAdjustment1.getApprovalState() == null ? "" : budgetAdjustment1.getApprovalState());
            userName = new StringBuilder(budgetAdjustment1.getUserName() == null ? "" : budgetAdjustment1.getUserName());
            applicationDate = new StringBuilder(budgetAdjustment1.getApplicationDate() == null ? "" : sdf.format(budgetAdjustment1.getApplicationDate()));
            assetsBudgetType = new StringBuilder(budgetAdjustment1.getAssetsBudgetType() == null ? "" : budgetAdjustment1.getAssetsBudgetType());
            nextHandlerName = new StringBuilder(budgetAdjustment1.getNextHandlerName() == null ? "" : budgetAdjustment1.getNextHandlerName());
            updateUserName = new StringBuilder(budgetAdjustment1.getUpdateUserName() == null ? "" : budgetAdjustment1.getUpdateUserName());
            updateDate = new StringBuilder(budgetAdjustment1.getUpdateDate() == null ? "" : sdf.format(budgetAdjustment1.getUpdateDate()));

            if (StringUtils.isNotBlank(budgetAdjustment1.getDraft())) {
                if (DRAFT.YES.getDraft().equals(budgetAdjustment1.getDraft())) {
                    draft = new StringBuilder("是");
                } else {
                    draft = new StringBuilder("否");
                }
            } else {
                draft = new StringBuilder("");
            }

            Object[] newRow = new Object[]{
                    draft.toString(),
                    document.toString(),
                    approvalState.toString(),
                    userName.toString(),
                    applicationDate.toString(),
                    assetsBudgetType.toString(),
                    nextHandlerName.toString(),
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


/*
        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportBudgetAdjustmentTemplate exportBudgetAdjustmentTemplate = new ExportBudgetAdjustmentTemplate();
            exportBudgetAdjustmentTemplate.doExport(response, fileName, budgetAdjustments);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }*/
    }

    /**
     * 修改页面中转
     * @param model
     * @param document
     * @return
     */
    @RequestMapping("/getBudgetAdjustmentInfoForUpdate")
    public String getBudgetAdjustmentInfoForUpdate(Model model, String document) {
        BudgetAdjustment budgetAdjustment = budgetAdjustmentService.getBudgetAdjustmentByDocument(document);
        model.addAttribute("budgetAdjustment", budgetAdjustment);
        return "purchase/budget_adjustment_update";
    }
    /**
     * 查看页面中转
     * @param model
     * @param request
     * @param document
     * @return
     */
    @RequestMapping("/getBudgetAdjustmentInfoForShow")
    public String getBudgetAdjustmentInfoForShow(Model model, HttpServletRequest request, Long offset, Long pageSize, String document) {
        BudgetAdjustment budgetAdjustment = budgetAdjustmentService.getBudgetAdjustmentByDocument(document);

        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        String typeId = "";
        if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())
                || ASSETBUDGETTYPE.ADDORREDUCTION.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
            typeId = "77047";  //这是个魔法字符
        } else {
            typeId = "77048"; //这是个魔法字符
        }

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(document, typeId, pager);
        appHistoryPager.setUri(request.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("budgetAdjustment", budgetAdjustment);
        return "purchase/budget_adjustment_show";
    }

    /**
     * 修改
     * @param model
     * @param request
     * @param budgetAdjustmentRequest
     * @return
     */
    @RequestMapping("/api/updateBudgetAdjustment")
    @ResponseBody
    public ExecuteResult<BudgetAdjustment> updateBudgetAdjustment(Model model, HttpServletRequest request,
                                                   @RequestBody BudgetAdjustmentRequest budgetAdjustmentRequest) {
        ExecuteResult<BudgetAdjustment> result = new ExecuteResult<BudgetAdjustment>();
        String userCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        String userName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();

        Map<String, Object> map = new HashMap<String, Object>();
        BudgetAdjustment budgetAdjustment = budgetAdjustmentRequest.getBudgetAdjustment();
        budgetAdjustment.setStatus(STATUS.YES_ZERO.getStatus());
        budgetAdjustment.setUpdateBy(userCode);
        budgetAdjustment.setUpdateUserName(userName);
        budgetAdjustment.setUpdateDate(new Date());
        //获取子表信息
        List<BudgetAdjustmentDetail> budgetAdjustmentDetails = getBudgetAdjustmentDetails(budgetAdjustment, budgetAdjustmentRequest);
        if (DRAFT.NO.getDraft().equals(budgetAdjustment.getDraft())) {
            budgetAdjustment.setCreateBy(userCode);
            budgetAdjustment.setCreateUserName(userName);
            budgetAdjustment.setCreateDate(new Date());
            budgetAdjustment.setApplicationDate(new Date());
        }
        //处理邮件和短信发送信息
        if ("on".equals(budgetAdjustment.getIsMail())) {
            budgetAdjustment.setIsMail(STATUS.YES.getStatus());
        } else {
            budgetAdjustment.setIsMail(STATUS.NO.getStatus());
        }
        if ("on".equals(budgetAdjustment.getIsPhoneMessage())) {
            budgetAdjustment.setIsPhoneMessage(STATUS.YES.getStatus());
        } else {
            budgetAdjustment.setIsPhoneMessage(STATUS.NO.getStatus());
        }

        //根据成本中心编码获取成本中心名称、部门名称、部门编码
        AssetsBudgetFromBUD assetsBudgetFromBUD = new AssetsBudgetFromBUD();
        if (ASSETBUDGETTYPE.ADJUST.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
            BudgetAdjustmentDetail detailIn = new BudgetAdjustmentDetail();
            BudgetAdjustmentDetail detailOut = new BudgetAdjustmentDetail();
            for (BudgetAdjustmentDetail detail : budgetAdjustmentDetails) {
                if (StringUtils.isNotBlank(detailIn.getCostCenter()) && StringUtils.isNotBlank(detailOut.getCostCenter())) {
                    break;
                }
                if (ADJUSTTPYE.IN.getAdjustType().equals(detail.getSpecialAdjustMark()) && StringUtils.isBlank(detailIn.getCostCenter())) {
                    detailIn = detail;
                    continue;
                }
                if (ADJUSTTPYE.OUT.getAdjustType().equals(detail.getSpecialAdjustMark()) && StringUtils.isBlank(detailOut.getCostCenter())) {
                    detailOut = detail;
                    continue;
                }
            }
            //调入
            UserInfo userInfoIn = userInfoService.searchDepInfoByCostCenter(detailIn);
            if (null != userInfoIn) {
                budgetAdjustment.setCallInDepartmentCode(userInfoIn.getCdepCode());
                budgetAdjustment.setCallInDepartmentName(userInfoIn.getCdepName());
            }
            assetsBudgetFromBUD.setCostCenter(detailIn.getCostCenter());
            AttchConfig attchConfigIn = attchConfigService.searchByCostcenterCode(assetsBudgetFromBUD);
            if (null != attchConfigIn) {
                budgetAdjustment.setTwoDepartmentHeadCode(attchConfigIn.getTwoDepartmentHeadCode());
                budgetAdjustment.setTwoDepartmentHeadName(attchConfigIn.getTwoDepartmentHeadName());
            }

            //调出
            UserInfo userInfoOut = userInfoService.searchDepInfoByCostCenter(detailOut);
            if (null != userInfoOut) {
                budgetAdjustment.setCallOutDepartmentCode(userInfoOut.getCdepCode());
                budgetAdjustment.setCallOutDepartmentName(userInfoOut.getCdepName());
            }
            assetsBudgetFromBUD.setCostCenter(detailOut.getCostCenter());
            AttchConfig attchConfigOut = attchConfigService.searchByCostcenterCode(assetsBudgetFromBUD);
            if (null != attchConfigOut) {
                budgetAdjustment.setOutTwoDepartmentHeadCode(attchConfigOut.getTwoDepartmentHeadCode());
                budgetAdjustment.setOutTwoDepartmentHeadName(attchConfigOut.getTwoDepartmentHeadName());
            }
        } else { //新增或增减
            assetsBudgetFromBUD.setCostCenter(budgetAdjustmentDetails.get(0).getCostCenter());
            UserInfo userInfo = userInfoService.searchDepInfoByCostCenter(budgetAdjustmentDetails.get(0));
            if (null != userInfo) {
                budgetAdjustment.setCallInDepartmentCode(userInfo.getCdepCode());
                budgetAdjustment.setCallInDepartmentName(userInfo.getCdepName());
            }
            //根据成本中心编码获取平台、二级部门负责人
            AttchConfig attchConfig = attchConfigService.searchByCostcenterCode(assetsBudgetFromBUD);
            if (null != attchConfig) {
                budgetAdjustment.setTwoDepartmentHeadCode(attchConfig.getTwoDepartmentHeadCode());
                budgetAdjustment.setTwoDepartmentHeadName(attchConfig.getTwoDepartmentHeadName());
            }
        }

        try {
            result = budgetAdjustmentService.updateBudgetAdjustmentByDocument(budgetAdjustment, budgetAdjustmentDetails);
//            budgetAdjustmentDetailService.updateBudgetAdjustmentDetail(budgetAdjustment, budgetAdjustmentDetails);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            result.addErrorMessage("调整数据修改失败");
            return result;
        }

        if (result.isSuccess() && DRAFT.NO.getDraft().equals(budgetAdjustment.getDraft())) {
            result = budgetAdjustmentService.bindApplyWorkFlow(budgetAdjustment);
        }
        if (!result.isSuccess()) {
            budgetAdjustmentService.updateBudgetAdjustmentToDraftByDocument(budgetAdjustment.getDocument());
//            workFlowService.deleteGarbegeWorkFlowData(budgetAdjustment.getDocument(),"77047");
            return result;
        }

        BudgetAdjustment budgetAdjustment1 = budgetAdjustmentService.getBudgetAdjustmentByDocument(budgetAdjustment.getDocument());
//        remindedService.BellReminder(budgetAdjustment1.getNextHandlerCode(), budgetAdjustment1.getDocument(), budgetAdjustment.getIsPhoneMessage(), budgetAdjustment.getIsMail());
        if (StringUtils.isNotBlank(budgetAdjustment1.getNextHandlerCode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(budgetAdjustment1.getNextHandlerCode(), budgetAdjustment1.getDocument(), budgetAdjustment.getIsPhoneMessage(), budgetAdjustment.getIsMail());

//            result.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                result.addErrorMessage("修改成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        return result;
    }

    /**
     * 删除
     * @param model
     * @param request
     * @param budgetAdjustment
     * @return
     */
    @RequestMapping("/api/deleteBudgetAdjustment")
    @ResponseBody
    public JSONObject deleteBudgetAdjustments(Model model, HttpServletRequest request, BudgetAdjustment budgetAdjustment) {
        Map<String, Object> map = new HashMap<String, Object>();
        ExecuteResult<BudgetAdjustment> result = new ExecuteResult<BudgetAdjustment>();
        String userName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String userCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        //BudgetAdjustment budgetAdjustment = new BudgetAdjustment();
        //budgetAdjustment.setBudgetId(budgetId);
        budgetAdjustment.setStatus(STATUS.NO_ONE.getStatus());
        budgetAdjustment.setUpdateBy(userCode);
        budgetAdjustment.setUpdateUserName(userName);
        budgetAdjustment.setUpdateDate(new Date());
        try {
            budgetAdjustmentService.deleteBudgetAdjustmentById(budgetAdjustment);
            String typeid = "";
            if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())
                    || ASSETBUDGETTYPE.ADDORREDUCTION.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                typeid = "77047";
            } else {
                typeid = "77048";
            }
            workFlowService.deleteGarbegeWorkFlowData(budgetAdjustment.getDocument(), typeid);
        } catch (Exception e) {
            LOGGER.error("deleteBudgetAdjustmentsException", e);
            map.put("result", "删除失败!");
            return JSONObject.fromObject(map);
        }
        map.put("result", "删除成功!");
        return JSONObject.fromObject(map);
    }

    /**
     * 添加预算调整中转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/getBudgetAdjustmentInfoForAdd")
    public String getBudgetAdjustmentInfoForAdd(Model model, HttpServletRequest request) {
        BudgetAdjustment budgetAdjustment = new BudgetAdjustment();
        Date date = new Date();
        budgetAdjustment.setApplicationDate(date);
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        String userName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        budgetAdjustment.setDocument(workFlowService.getDocumentByType("FA_BGAJ"));
        setUserBeanProperties(userCode, budgetAdjustment);
        budgetAdjustment.setUserCode(userCode);
        budgetAdjustment.setUserName(userName);
        model.addAttribute("budgetAdjustment", budgetAdjustment);

        /*List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        model.addAttribute("addresslist",addresslist);
        UserInfo userInfo=new UserInfo();
        List<UserInfo> platformlist=userInfoService.searchPlatformInfo(userInfo);
        model.addAttribute("platformlist",platformlist);*/


       /* Pager<AssetsBudget> pager = new Pager<AssetsBudget>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<AssetsBudget> assetsBudgetsPager = assetsBudgetService.searchAssetsBudgets(pager, assetsBudget);
        assetsBudgetsPager.setUri(req.getRequestURI());
        model.addAttribute("assetsBudgetsPager", assetsBudgetsPager);*/
        return "purchase/budget_adjustment_add";
    }

    /**
     * 获取登录人信息
     * @param userCode
     * @param budgetAdjustment
     */
    private void setUserBeanProperties(String userCode, BudgetAdjustment budgetAdjustment) {
        User userDeptInfo = userService.getUserDeptInfo(userCode);
        //budgetAdjustment.setUserCode(userCode);
        if (null != userDeptInfo) {
            //budgetAdjustment.setUserName(userDeptInfo.getUserName());
            budgetAdjustment.setDepartmentCode(userDeptInfo.getDepartmentCode());
            budgetAdjustment.setDepartmentName(userDeptInfo.getDepartmentName());
            budgetAdjustment.setCompanyCode(userDeptInfo.getCompanyCode());
            budgetAdjustment.setCompanyName(userDeptInfo.getCompanyName());
            budgetAdjustment.setCreateBy(userDeptInfo.getUserCode());
            budgetAdjustment.setCreateUserName(userDeptInfo.getUserName());
            budgetAdjustment.setUpdateBy(userDeptInfo.getUserCode());
            budgetAdjustment.setUpdateUserName(userDeptInfo.getUserName());
        }
    }

    /**
     * 添加预算调整，绑定审批流
     * @param budgetAdjustmentRequest
     * @return
     */
    @RequestMapping("/api/addBudgetAdjustment")
    @ResponseBody
    public ExecuteResult<BudgetAdjustment> addBudgetAdjustment(@RequestBody BudgetAdjustmentRequest budgetAdjustmentRequest) {
        ExecuteResult<BudgetAdjustment> result = new ExecuteResult<BudgetAdjustment>();

        Map<String, Object> map = new HashMap<String, Object>();
        BudgetAdjustment budgetAdjustment = budgetAdjustmentRequest.getBudgetAdjustment();
        budgetAdjustment.setStatus(STATUS.YES_ZERO.getStatus());
        budgetAdjustment.setApplicationDate(new Date());
        budgetAdjustment.setCreateDate(new Date());
        budgetAdjustment.setUpdateDate(new Date());
        List<BudgetAdjustmentDetail> budgetAdjustmentDetails = getBudgetAdjustmentDetails(budgetAdjustment, budgetAdjustmentRequest);

        if ("on".equals(budgetAdjustment.getIsMail())) {
            budgetAdjustment.setIsMail(STATUS.YES.getStatus());
        } else {
            budgetAdjustment.setIsMail(STATUS.NO.getStatus());
        }
        if ("on".equals(budgetAdjustment.getIsPhoneMessage())) {
            budgetAdjustment.setIsPhoneMessage(STATUS.YES.getStatus());
        } else {
            budgetAdjustment.setIsPhoneMessage(STATUS.NO.getStatus());
        }

        BudgetAdjustment budget = budgetAdjustmentService.getBudgetAdjustmentByDocument(budgetAdjustment.getDocument());
        if (null != budget) {
            result.addErrorMessage("单据号已存在，请确认是否已保存过");
            return result;
        }

        try {
            result = budgetAdjustmentService.addBudgetAdjustment(budgetAdjustment, budgetAdjustmentDetails);
        } catch (Exception e) {
            LOGGER.error("addBudgetAdjustmentException", e);
            result.addErrorMessage("调整数据添加失败");
            return result;
        }
        if (result.isSuccess() && DRAFT.NO.getDraft().equals(budgetAdjustment.getDraft())) {
            result = budgetAdjustmentService.bindApplyWorkFlow(budgetAdjustment);
        }
        if (!result.isSuccess()) {
            budgetAdjustmentService.updateBudgetAdjustmentToDraftByDocument(budgetAdjustment.getDocument());
//            budgetAdjustmentService.deleteBudgetAdjustmentByDocument(budgetAdjustment.getDocument());
//            workFlowService.deleteGarbegeWorkFlowData(budgetAdjustment.getDocument(),"77047");
            return result;
        }

        BudgetAdjustment budgetAdjustment1 = budgetAdjustmentService.getBudgetAdjustmentByDocument(budgetAdjustment.getDocument());
        if (StringUtils.isNotBlank(budgetAdjustment1.getNextHandlerCode())) {
//        remindedService.BellReminder(budgetAdjustment1.getNextHandlerCode(), budgetAdjustment1.getDocument(), budgetAdjustment.getIsPhoneMessage(), budgetAdjustment.getIsMail());
            ExecuteResult<Employee> er = remindedService.BellReminder(budgetAdjustment1.getNextHandlerCode(), budgetAdjustment1.getDocument(), budgetAdjustment.getIsPhoneMessage(), budgetAdjustment.getIsMail());

//            result.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                result.addErrorMessage("添加成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        return result;
    }
    /**
     * 获取预算调整详细信息集合
     * @param budgetAdjustmentRequest
     * @return
     */
    private List<BudgetAdjustmentDetail> getBudgetAdjustmentDetails(BudgetAdjustment budgetAdjustment, BudgetAdjustmentRequest budgetAdjustmentRequest) {
        List<BudgetAdjustmentDetail> budgetAdjustmentDetails = new ArrayList<BudgetAdjustmentDetail>();

        //向budgetAdjustmentDetails中添加数据
        BudgetAdjustmentDetailArray budgetAdjustmentDetailArray = budgetAdjustmentRequest.getBudgetAdjustmentDetailArray();
        //判断json传过来的array是否为空
        if (budgetAdjustmentDetailArray != null && budgetAdjustmentDetailArray.getBudgetYear() != null
                && budgetAdjustmentDetailArray.getBudgetYear().length > 0) {
            String[] budgetYear = budgetAdjustmentDetailArray.getBudgetYear();
            for (int i = 0; i < budgetAdjustmentDetailArray.getBudgetYear().length; i++) {
                BudgetAdjustmentDetail budgetAdjustmentDetail = new BudgetAdjustmentDetail();
                budgetAdjustmentDetail.setBudgetYear(budgetAdjustmentDetailArray.getBudgetYear()[i]);
                budgetAdjustmentDetail.setCostCenter(budgetAdjustmentDetailArray.getCostCenter()[i]);
                budgetAdjustmentDetail.setCostCenterName(budgetAdjustmentDetailArray.getCostCenterName()[i]);
                budgetAdjustmentDetail.setPlatform(budgetAdjustmentDetailArray.getPlatform()[i]);
                budgetAdjustmentDetail.setProjectName(budgetAdjustmentDetailArray.getProjectName()[i]);
                budgetAdjustmentDetail.setAssetsName(budgetAdjustmentDetailArray.getAssetsName()[i]);
                budgetAdjustmentDetail.setAssetsType(budgetAdjustmentDetailArray.getAssetsType()[i]);
                budgetAdjustmentDetail.setAssetsModel(budgetAdjustmentDetailArray.getAssetsModel()[i]);
                budgetAdjustmentDetail.setUnit(budgetAdjustmentDetailArray.getUnit()[i]);
                budgetAdjustmentDetail.setBeforeAdjustCount(budgetAdjustmentDetailArray.getBeforeAdjustCount()[i]);
                budgetAdjustmentDetail.setBeforeUnitPrice(budgetAdjustmentDetailArray.getBeforeUnitPrice()[i]);
                budgetAdjustmentDetail.setBeforeAdjustMoney(budgetAdjustmentDetailArray.getBeforeAdjustMoney()[i]);
                //判断是否是新增调整 以便排除
                if (!ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                    budgetAdjustmentDetail.setProjectCode(budgetAdjustmentDetailArray.getProjectCode()[i]);
                    budgetAdjustmentDetail.setAdjustCount(budgetAdjustmentDetailArray.getAdjustCount()[i]);
                    budgetAdjustmentDetail.setUsedAmount(budgetAdjustmentDetailArray.getUsedAmount()[i]);
                    budgetAdjustmentDetail.setAfterAdjustCount(budgetAdjustmentDetailArray.getAfterAdjustCount()[i]);
                    budgetAdjustmentDetail.setAfterUnitPrice(budgetAdjustmentDetailArray.getAfterUnitPrice()[i]);
                    budgetAdjustmentDetail.setAdjustMoney(budgetAdjustmentDetailArray.getAdjustMoney()[i]);
                    budgetAdjustmentDetail.setUsedSumMoney(budgetAdjustmentDetailArray.getUsedSumMoney()[i]);
                    budgetAdjustmentDetail.setAfterAdjustMoney(budgetAdjustmentDetailArray.getAfterAdjustMoney()[i]);
                } else {
                    budgetAdjustmentDetail.setBudgetBasedOn(budgetAdjustmentDetailArray.getBudgetBasedOn()[i]);
                }
                //判断是否是项目间调整 以便添加标记
                if (ASSETBUDGETTYPE.ADJUST.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                    budgetAdjustmentDetail.setSpecialAdjustMark(budgetAdjustmentDetailArray.getSpecialAdjustMark()[i]);
                }
                budgetAdjustmentDetail.setStatus(STATUS.YES_ZERO.getStatus());
                budgetAdjustmentDetail.setCreateBy(budgetAdjustment.getCreateBy());
                budgetAdjustmentDetail.setCreateUserName(budgetAdjustment.getCreateUserName());
                budgetAdjustmentDetail.setCreateDate(new Date());
                budgetAdjustmentDetail.setUpdateBy(budgetAdjustment.getUpdateBy());
                budgetAdjustmentDetail.setUpdateUserName(budgetAdjustment.getUpdateUserName());
                budgetAdjustmentDetail.setUpdateDate(new Date());
                budgetAdjustmentDetails.add(budgetAdjustmentDetail);
            }
        }

        BudgetAdjustmentDetail budgetAdjustmentDetail = budgetAdjustmentRequest.getBudgetAdjustmentDetail();
        //判断json传过来的jsonTaskCenter是否为空
        if (budgetAdjustmentDetail != null && budgetAdjustmentDetail.getBudgetYear() != null && budgetAdjustmentDetail.getBudgetYear() != "") {
            budgetAdjustmentDetail.setCreateBy(budgetAdjustment.getCreateBy());
            budgetAdjustmentDetail.setCreateUserName(budgetAdjustment.getCreateUserName());
            budgetAdjustmentDetail.setCreateDate(new Date());
            budgetAdjustmentDetail.setUpdateBy(budgetAdjustment.getUpdateBy());
            budgetAdjustmentDetail.setUpdateUserName(budgetAdjustment.getUpdateUserName());
            budgetAdjustmentDetail.setUpdateDate(new Date());
            budgetAdjustmentDetails.add(budgetAdjustmentDetail);
        }
        return budgetAdjustmentDetails;
    }

    /**
     * 跳审批页面
     * @param model
     * @param document
     * @return
     */
    @RequestMapping("/budgetAdjustmentApprove")
    public String budgetAdjustmentApprove(Model model, HttpServletRequest request, Long offset, Long pageSize, String document) {
        BudgetAdjustment budgetAdjustment = budgetAdjustmentService.getBudgetAdjustmentByDocument(document);

        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        String typeId = "";
        if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())
                || ASSETBUDGETTYPE.ADDORREDUCTION.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
            typeId = "77047"; //魔法字符
        } else {
            typeId = "77048"; //魔法字符
        }

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(document, typeId, pager);
        appHistoryPager.setUri(request.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("budgetAdjustment", budgetAdjustment);
        return "purchase/budget_adjustment_approve";
    }

    @RequestMapping("/showAgreeDialog")
    public String showAgreeDialog() {

        return "workflow/agree_dialog";
    }
    @RequestMapping("/showDenyDialog")
    public String showDenyDialog() {

        return "workflow/deny_dialog";
    }
    @RequestMapping("/showDropDialog")
    public String showDropDialog() {

        return "workflow/drop_dialog";
    }

    /**
     * 同意
     *
     * @param budgetAdjust
     * @param request
     * @return
     */
    @RequestMapping("/api/agreeBudgetAdjustment")
    @ResponseBody
    public ExecuteResult<BudgetAdjustment> agreeBudgetAdjustment(@RequestBody BudgetAdjustment budgetAdjust, HttpServletRequest request) {
        String caUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String caUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        ExecuteResult<BudgetAdjustment> result = new ExecuteResult<BudgetAdjustment>();
        BudgetAdjustment budgetAdjustment = budgetAdjustmentService.getBudgetAdjustmentByDocument(budgetAdjust.getDocument());
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);

        budgetAdjustment.setActivityId(budgetAdjust.getActivityId());
        budgetAdjustment.setIdea(budgetAdjust.getIdea());
//        budgetAdjustment.setIsMail(budgetAdjust.getIsMail());
//        budgetAdjustment.setIsPhoneMessage(budgetAdjust.getIsPhoneMessage());
        if ("on".equals(budgetAdjust.getIsMail())) {
            budgetAdjustment.setIsMail(STATUS.YES.getStatus());
        } else {
            budgetAdjustment.setIsMail(STATUS.NO.getStatus());
        }
        if ("on".equals(budgetAdjust.getIsPhoneMessage())) {
            budgetAdjustment.setIsPhoneMessage(STATUS.YES.getStatus());
        } else {
            budgetAdjustment.setIsPhoneMessage(STATUS.NO.getStatus());
        }
        budgetAdjustment.setCaUserCode(caUserCode);
        budgetAdjustment.setCaUserName(caUserName);
        budgetAdjustment.setUserDepCode(userDeptInfo.getDepartmentCode());
        //  budgetAdjustment.setCreateDate(new Date());
        budgetAdjustment.setUpdateDate(new Date());
        // setUserBeanProperties(caUserCode, budgetAdjustment);

        if (!ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
            String msg = checkAssetsBudgetInfo(budgetAdjustment);
            if (STATUS.YES_ZERO.getStatus().equals(msg)) { //魔法字符
                result.addErrorMessage("存在预算已用金额大于调整后总额的预算信息，请退回流程重新调整");
                return result;
            } else if (!STATUS.NO_ONE.getStatus().equals(msg)) {
                result.addErrorMessage(msg);
                return result;
            }
        }

        result = budgetAdjustmentService.agreeWorkFlow(budgetAdjustment);
        if (!result.isSuccess()) {
            return result;
        }

        //获取调整单据
        BudgetAdjustment budgetAdjustment1 = budgetAdjustmentService.getBudgetAdjustmentByDocument(budgetAdjustment.getDocument());
        String approve = budgetAdjustment1.getApprovalState();

        if ("已完成".equals(approve.trim())) { //魔法字符
            try {
                //新增时插入详细
                if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment1.getAssetsBudgetType())) {
                    List<BudgetAdjustmentDetail> budgetAdjustmentDetails = budgetAdjustmentDetailService.getBudgetAdjustmentDetailsByBudgetId(budgetAdjustment1.getBudgetId());
                    assetsBudgetService.addAssetsBudgetAfterApprove(budgetAdjustmentDetails);
                } else { //非新增时更新详细
                    List<BudgetAdjustmentDetail> budgetAdjustmentDetails = budgetAdjustmentDetailService.getBudgetAdjustmentDetailsByBudgetId(budgetAdjustment1.getBudgetId());
                    assetsBudgetService.updateAssetsBudgetAfterApprove(budgetAdjustmentDetails);
                }
            } catch (Exception e) {
                LOGGER.error("agreeBudgetAdjustmentException", e);
                result.addErrorMessage("审批成功！更新预算时出现错误");
                return result;
            }
        }
        /*if ("on".equals(budgetAdjustment.getIsMail())) {
            budgetAdjustment1.setIsMail("Y");
        }
        if ("on".equals(budgetAdjustment.getIsPhoneMessage())) {
            budgetAdjustment1.setIsPhoneMessage("Y");
        }*/
        if (StringUtils.isNotBlank(budgetAdjustment1.getNextHandlerCode())) {
//        remindedService.BellReminder(budgetAdjustment1.getNextHandlerCode(), budgetAdjustment1.getDocument(), budgetAdjustment.getIsPhoneMessage(), budgetAdjustment.getIsMail());

            ExecuteResult<Employee> er = remindedService.BellReminder(budgetAdjustment1.getNextHandlerCode(), budgetAdjustment1.getDocument(), budgetAdjustment.getIsPhoneMessage(), budgetAdjustment.getIsMail());

//            result.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                result.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        return result;
    }

    /**
     * 退回
     * @param budgetAdjust
     * @param request
     * @return
     */
    @RequestMapping("/api/denyBudgetAdjustment")
    @ResponseBody
    public ExecuteResult<BudgetAdjustment> denyDiscardSell(@RequestBody BudgetAdjustment budgetAdjust, HttpServletRequest request) {
        String caUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String caUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        ExecuteResult<BudgetAdjustment> result = new ExecuteResult<BudgetAdjustment>();
        BudgetAdjustment budgetAdjustment = budgetAdjustmentService.getBudgetAdjustmentByDocument(budgetAdjust.getDocument());
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);

        budgetAdjustment.setActivityId(budgetAdjust.getActivityId());
        budgetAdjustment.setIdea(budgetAdjust.getIdea());
//        budgetAdjustment.setIsMail(budgetAdjust.getIsMail());
//        budgetAdjustment.setIsPhoneMessage(budgetAdjust.getIsPhoneMessage());
        if ("on".equals(budgetAdjust.getIsMail())) {
            budgetAdjustment.setIsMail(STATUS.YES.getStatus());
        } else {
            budgetAdjustment.setIsMail(STATUS.NO.getStatus());
        }
        if ("on".equals(budgetAdjust.getIsPhoneMessage())) {
            budgetAdjustment.setIsPhoneMessage(STATUS.YES.getStatus());
        } else {
            budgetAdjustment.setIsPhoneMessage(STATUS.NO.getStatus());
        }
        budgetAdjustment.setCaUserCode(caUserCode);
        budgetAdjustment.setCaUserName(caUserName);
        budgetAdjustment.setUserDepCode(userDeptInfo.getDepartmentCode());
        budgetAdjustment.setUpdateDate(new Date());
        //   setUserBeanProperties(caUserCode, budgetAdjustment);
        if (StringUtils.isBlank(budgetAdjustment.getIdea())) {
            result.addErrorMessage("意见不可为空");
            return result;
        }

        result = budgetAdjustmentService.denyWorkFlow(budgetAdjustment);
        if (!result.isSuccess()) {
            return result;
        }

        BudgetAdjustment budgetAdjustment1 = budgetAdjustmentService.getBudgetAdjustmentByDocument(budgetAdjustment.getDocument());
        /*if ("on".equals(budgetAdjustment.getIsMail())) {
            budgetAdjustment1.setIsMail(STATUS.YES.getStatus());
        }
        if ("on".equals(budgetAdjustment.getIsPhoneMessage())) {
            budgetAdjustment1.setIsPhoneMessage(STATUS.YES.getStatus());
        }*/
        if (StringUtils.isNotBlank(budgetAdjustment1.getNextHandlerCode())) {
//        remindedService.BellReminder(budgetAdjustment1.getNextHandlerCode(), budgetAdjustment1.getDocument(), budgetAdjustment.getIsPhoneMessage(), budgetAdjustment.getIsMail());
            ExecuteResult<Employee> er = remindedService.BellReminder(budgetAdjustment1.getNextHandlerCode(), budgetAdjustment1.getDocument(), budgetAdjustment.getIsPhoneMessage(), budgetAdjustment.getIsMail());

//            result.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                result.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        return result;
    }

    /**
     * 弃审
     * @param budgetAdjust
     * @param request
     * @return
     */
    @RequestMapping("/api/dropBudgetAdjustment")
    @ResponseBody
    public ExecuteResult<BudgetAdjustment> dropDiscardSell(BudgetAdjustment budgetAdjust, HttpServletRequest request) {
        String caUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String caUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        ExecuteResult<BudgetAdjustment> result = new ExecuteResult<BudgetAdjustment>();
        BudgetAdjustment budgetAdjustment = budgetAdjustmentService.getBudgetAdjustmentByDocument(budgetAdjust.getDocument());
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);

        budgetAdjustment.setIdea(budgetAdjust.getIdea());
//        budgetAdjustment.setIsMail(budgetAdjust.getIsMail());
//        budgetAdjustment.setIsPhoneMessage(budgetAdjust.getIsPhoneMessage());
        if ("on".equals(budgetAdjust.getIsMail())) {
            budgetAdjustment.setIsMail(STATUS.YES.getStatus()); //魔法字符
        } else {
            budgetAdjustment.setIsMail(STATUS.NO.getStatus());
        }
        if ("on".equals(budgetAdjust.getIsPhoneMessage())) {
            budgetAdjustment.setIsPhoneMessage(STATUS.YES.getStatus());
        } else {
            budgetAdjustment.setIsPhoneMessage(STATUS.NO.getStatus());
        }
        budgetAdjustment.setCaUserCode(caUserCode);
        budgetAdjustment.setCaUserName(caUserName);
        budgetAdjustment.setUserDepCode(userDeptInfo.getDepartmentCode());
        budgetAdjustment.setUpdateDate(new Date());
        //  setUserBeanProperties(caUserCode, budgetAdjustment);
        if (StringUtils.isBlank(budgetAdjustment.getIdea())) {
            result.addErrorMessage("意见不可为空");
            return result;
        }

        //弃审时判断是否审批完成，若审批完成，则将更新的预算表撤回
        BudgetAdjustment adjustment = budgetAdjustmentService.getBudgetAdjustmentByDocument(budgetAdjustment.getDocument());
        if (STATUS.YES.getStatus().equals(adjustment.getApprovalEnd())) {
            try {
                List<BudgetAdjustmentDetail> budgetAdjustmentDetails = budgetAdjustmentDetailService.getBudgetAdjustmentDetailsByBudgetId(adjustment.getBudgetId());
                if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                    assetsBudgetService.deleteAssetsBudgetAfterDrop(budgetAdjustmentDetails);
                } else {
                    assetsBudgetService.updateAssetsBudgetAfterDrop(budgetAdjustmentDetails);
                }
            } catch (Exception e) {
                LOGGER.error("dropDiscardSellException", e);
                result.addErrorMessage("审批成功！更新预算时出现错误");
                return result;
            }
        }
        //弃审
        result = budgetAdjustmentService.dropWorkFlow(budgetAdjustment);
        if (!result.isSuccess()) {
            return result;
        }
        BudgetAdjustment budgetAdjustment1 = budgetAdjustmentService.getBudgetAdjustmentByDocument(budgetAdjustment.getDocument());
        /*if ("on".equals(budgetAdjustment.getIsMail())) {
            budgetAdjustment.setIsMail(STATUS.YES.getStatus());
        }
        if ("on".equals(budgetAdjustment.getIsPhoneMessage())) {
            budgetAdjustment.setIsPhoneMessage(STATUS.YES.getStatus());
        }*/


        if (StringUtils.isNotBlank(budgetAdjustment1.getNextHandlerCode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(budgetAdjustment1.getNextHandlerCode(), budgetAdjustment1.getDocument(), budgetAdjustment.getIsPhoneMessage(), budgetAdjustment.getIsMail());

//            result.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                result.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        return result;
    }

    /**
     * 审批历史
     * @param model
     * @param offset
     * @param pageSize
     * @param document
     * @param typeId
     * @param request
     * @return
     */
    @RequestMapping("/showApproveHistory")
    public String showApproveHistory(Model model, Long offset, Long pageSize, String document, String typeId, HttpServletRequest request) {
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(document, typeId, pager);
        appHistoryPager.setUri(request.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);

        return "workflow/approve_history";
    }

    /**
     * 判断调整后总额是否小于已用金额
     * @param budgetAdjustment
     * @return
     */
    private String checkAssetsBudgetInfo(BudgetAdjustment budgetAdjustment) {
        List<BudgetAdjustmentDetail> details = budgetAdjustmentDetailService.getBudgetAdjustmentDetailsByDocument(budgetAdjustment.getDocument());
        for (BudgetAdjustmentDetail detail : details) {
            BudgetAdjustmentDetail assetsBudget = assetsBudgetService.getBudgetDetailsbyProjectCodeAndYear(detail.getBudgetYear(), detail.getProjectCode());
            if (null == assetsBudget) {
                return "项目不存在";
            }
            if (detail.getAfterAdjustMoney() < assetsBudget.getUsedSumMoney()) {
                return STATUS.YES_ZERO.getStatus(); //魔法字符
            }
        }
        return STATUS.NO_ONE.getStatus(); //魔法字符
    }

}
