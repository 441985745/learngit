package com.opple.fa.purchase.webapp.controller;

import com.opple.fa.config.entity.Department;
import com.opple.fa.config.entity.Factory;
import com.opple.fa.config.entity.Supplier;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AssetUnitService;
import com.opple.fa.config.service.FactoryService;
import com.opple.fa.config.service.SupplierService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.OutBudgetException;
import com.opple.fa.purchase.entity.PaymentTerm;
import com.opple.fa.purchase.entity.PurchaseApplyMapping;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.entity.WARNSTATE;
import com.opple.fa.purchase.model.ApproveHistoryCheckPrint;
import com.opple.fa.purchase.model.PurchaseApplyMappingModel;
import com.opple.fa.purchase.model.PurchaseOrderDetailModel;
import com.opple.fa.purchase.model.PurchaseOrderModel;
import com.opple.fa.purchase.model.PurchaseOrderWrapper;
import com.opple.fa.purchase.service.ApplyOrderDetailService;
import com.opple.fa.purchase.service.ApplyOrderService;
import com.opple.fa.purchase.service.CheckAcceptanceService;
import com.opple.fa.purchase.service.PaymentTermService;
import com.opple.fa.purchase.service.PurchaseOrderDetailService;
import com.opple.fa.purchase.service.PurchaseOrderService;
import com.opple.fa.sap.service.SapUtilService;
import com.opple.fa.security.service.impl.ExportPurchaseTemplate;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.entity.Employee;
import com.opple.security.entity.User;
import com.opple.security.service.RemindedService;
import com.opple.security.service.UserService;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.workflow.entity.APPROVESTATE;
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
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 采购订单
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);
    @Resource
    private PurchaseOrderService purchaseOrderService;
    @Resource
    private PurchaseOrderDetailService purchaseOrderDetailService;

    @Resource
    private PaymentTermService paymentTermService;

    @Resource
    private ApplyOrderService applyOrderService;

    @Resource
    private ApplyOrderDetailService applyOrderDetailService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private WorkFlowService workFlowService;

    @Resource
    private UserService userService;

    @Resource
    private SapUtilService sapUtilService;

    @Resource
    private SupplierService supplierService;

    @Resource
    private FactoryService factoryService;
    @Resource
    private AssetUnitService assetUnitService;
    @Resource
    private RemindedService remindedService;
    @Resource
    private CheckAcceptanceService checkAcceptanceService;

    /**
     * 查询采购订单
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param purchaseOrderModel
     * @return
     */
    @RequestMapping("/searchPurchase")
    public String showPurchaseOrder(Model model, HttpServletRequest req, Long offset, Long pageSize,
                                    PurchaseOrderModel purchaseOrderModel) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 开始时间
            purchaseOrderModel.setApplyDateStart(sdf.parse(req.getParameter("txtBeginDate")));
        } catch (Exception e) {
            purchaseOrderModel.setApplyDateStart(null);
        }
        try {
            // 结束时间
            purchaseOrderModel.setApplyDateEnd(sdf.parse(req.getParameter("txtEndDate")));
        } catch (Exception e) {
            purchaseOrderModel.setApplyDateEnd(null);
        }

        purchaseOrderModel.setDraft(null == purchaseOrderModel.getDraft() ? "N" : purchaseOrderModel.getDraft());
        Pager<PurchaseOrder> pager = new Pager<PurchaseOrder>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        // 设置当前登录人编码
        purchaseOrderModel.setLoginUserCode(loginUserCode);
        purchaseOrderModel.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));

        Pager<PurchaseOrder> purchasePager = purchaseOrderService.searchPurchaseOrder(pager, purchaseOrderModel);
        purchasePager.setUri(req.getRequestURI());

        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUserCode);
        List<UserInfo> searchCauthorizerCode = userInfoService.searchCauthorizerCode(userInfo);
        List<PurchaseOrder> records = purchasePager.getRecords();
        // 遍历 如果有授权，则把明细是否授权字段变为Y
        for (PurchaseOrder purchaseOrder2 : records) {
            for (UserInfo userInfo2 : searchCauthorizerCode) {
                if (purchaseOrder2.getApplyUserCode().equals(userInfo2.getCauthorizerCode())) {
                    purchaseOrder2.setIsAuthorize("Y");
                }
            }
        }
        purchasePager.setRecords(records);

        model.addAttribute("purchasePager", purchasePager);
        model.addAttribute("loginUserCode", purchaseOrderModel.getLoginUserCode());
        List<UserInfo> companys = userInfoService.searchAllCompany();
        model.addAttribute("companys", companys);
        return "purchase/purchase_order_list";
    }

    @RequestMapping("/showPurchaseOrder")
    public String showPurchaseOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {
        // 单据号
        String strId = req.getParameter("id");
        PurchaseOrder pOrder = new PurchaseOrder();
        pOrder.setCdocument(strId);
        PurchaseOrder purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(pOrder);
        List<PurchaseOrderDetailModel> purchaseOrderDetailModels = purchaseOrderDetailService
                .searchPurchaseOrderDetailByOrderId(pOrder);
        List<PurchaseApplyMappingModel> purchaseApplyMappingModels = purchaseOrderService
                .searchPurchaseApplyMappingByOrderId(pOrder);
        List<PaymentTerm> paymentTerms = paymentTermService.searchPaymentTermByOrderId(purchaseOrder);

        Pager<ApproveHistory> approveHistoryPager = new Pager<ApproveHistory>();
        approveHistoryPager.setOffset(null == offset ? approveHistoryPager.getOffset() : offset);
        approveHistoryPager.setPageSize(null == pageSize ? approveHistoryPager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(strId, typeId, approveHistoryPager);
        appHistoryPager.setUri(req.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);

        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("purchaseOrderDetailModels", purchaseOrderDetailModels);
        model.addAttribute("purchaseApplyMappingModels", purchaseApplyMappingModels);
        model.addAttribute("paymentTerms", paymentTerms);

        return "purchase/purchase_order_detail";
    }

    @RequestMapping("/examinePurchaseOrder")
    public String examinePurchaseOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {
        // 单据号
        String strId = req.getParameter("id");
        PurchaseOrder pOrder = new PurchaseOrder();
        pOrder.setCdocument(strId);
        PurchaseOrder purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(pOrder);
        List<PurchaseOrderDetailModel> purchaseOrderDetailModels = purchaseOrderDetailService
                .searchPurchaseOrderDetailByOrderId(pOrder);
        List<PurchaseApplyMappingModel> purchaseApplyMappingModels = purchaseOrderService
                .searchPurchaseApplyMappingByOrderId(pOrder);
        List<PaymentTerm> paymentTerms = paymentTermService.searchPaymentTermByOrderId(purchaseOrder);

        PurchaseOrderModel purchaseOrderModel = new PurchaseOrderModel();
        purchaseOrderModel.setCdocument(purchaseOrder.getCdocument());
        // 申请人
        String loginUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString()
                .trim();
        String loginUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString()
                .trim();
        purchaseOrderModel.setLoginUserCode(loginUsernameCode);
        purchaseOrderModel.setLoginUserName(loginUsername);

        User userDeptInfo = userService.getUserDeptInfo(loginUsernameCode);
        purchaseOrderModel.setLoginUserDepCode(userDeptInfo.getDepartmentCode());

        boolean isLastApprovalStep = purchaseOrderService.getIsLastApprovalStep(purchaseOrderModel);
        if (isLastApprovalStep) {
            model.addAttribute("isLastApprovalStep", "Y");
        } else {
            model.addAttribute("isLastApprovalStep", "N");
        }

        Pager<ApproveHistory> approveHistoryPager = new Pager<ApproveHistory>();
        approveHistoryPager.setOffset(null == offset ? approveHistoryPager.getOffset() : offset);
        approveHistoryPager.setPageSize(null == pageSize ? approveHistoryPager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(strId, typeId, approveHistoryPager);
        appHistoryPager.setUri(req.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);

        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("purchaseOrderDetailModels", purchaseOrderDetailModels);
        model.addAttribute("purchaseApplyMappingModels", purchaseApplyMappingModels);
        model.addAttribute("paymentTerms", paymentTerms);

        return "purchase/purchase_order_examine";
    }

    // 数据调整
    @RequestMapping("/dataAdjustment")
    public String dataAdjustment(Model model, HttpServletRequest req) {
        // 单据号
        String strId = req.getParameter("id");
        PurchaseOrder pOrder = new PurchaseOrder();
        pOrder.setCdocument(strId);
        PurchaseOrder purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(pOrder);

        model.addAttribute("purchaseOrder", purchaseOrder);

        return "purchase/dataAdjustment";
    }

    /**
     * 同意 审批流
     *
     * @param
     * @return
     */
    @RequestMapping("/api/agreeWorkFlow")
    @ResponseBody
    private ExecuteResult<PurchaseOrder> agreePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder,
                                                            HttpServletRequest request) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();

        // String activityId = purchaseOrder.getActivityId();

        executeResult = handleApplyRequest(purchaseOrder, request);
        PurchaseOrder applyOrderOnly = executeResult.getResult();
        applyOrderOnly.setIdea(purchaseOrder.getIdea());
        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsMail())) {
            applyOrderOnly.setIsMail("Y");
        } else {
            applyOrderOnly.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsPhoneMessage())) {
            applyOrderOnly.setIsPhoneMessage("Y");
        } else {
            applyOrderOnly.setIsPhoneMessage("N");
        }
        applyOrderOnly.setActivityId(purchaseOrder.getActivityId());
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        if (!request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim()
                .equals(applyOrderOnly.getCnexthandlercode())) {
            executeResult.addErrorMessage("单据已被审批，或没有审批权限！");
            return executeResult;
        }

        // applyOrderOnly.setActivityId(activityId);

        executeResult = purchaseOrderService.agreeWorkFlow(applyOrderOnly);

        PurchaseOrder searchPurchaseOrderByOrderId = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);

        // //调用发生短信和邮件接口
        // if
        // (!APPROVESTATE.DENY.getApproveState().trim().equals(applyOrder.getApprovalState().trim())
        // &&
        // !APPROVESTATE.DONE.getApproveState().trim().equals(applyOrder.getApprovalState().trim()))
        // {
        if (!executeResult.isSuccess()) {
            return executeResult;
        }

        if (StringUtils.isNotBlank(searchPurchaseOrderByOrderId.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPurchaseOrderByOrderId.getCnexthandlercode(),
                    searchPurchaseOrderByOrderId.getCdocument(), applyOrderOnly.getIsPhoneMessage(),
                    applyOrderOnly.getIsMail());
            //executeResult.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
            // }
        }
        // }

        // 单据已完成状态 进行判断需求单进行关闭
        if (APPROVESTATE.DONE.getApproveState().equals(searchPurchaseOrderByOrderId.getCapprovalstate())) {
            executeResult = purchaseOrderService.updateSearchCloseApply(searchPurchaseOrderByOrderId);
            if (!executeResult.isSuccess()) {
                return executeResult;
            }
        }
        return executeResult;
    }

    @RequestMapping("/api/sendSAP")
    @ResponseBody
    private ExecuteResult<PurchaseOrder> sendSAP(@RequestBody PurchaseOrder purchaseOrder, HttpServletRequest request) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();

        PurchaseOrder purchaseOrderonly = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);

        if (null == purchaseOrderonly) {
            executeResult.addErrorMessage("单据号不存在，次级编码拆分失败");
            return executeResult;
        }
        // if
        // (APPROVESTATE.DONE.getApproveState().trim().equals(purchaseOrderonly.getCapprovalstate()))
        // {

        // 拆分次级编码
        executeResult = splitSecondCode(purchaseOrderonly);

        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        // 传sap生成订单
        executeResult = sapOrder(purchaseOrderonly);

        if (!executeResult.isSuccess()) {
            // 传SAP失败后，把申请单的‘下次次级’恢复，把采购订单的次级清空
            purchaseOrderService.updateSecondary(purchaseOrder);
            return executeResult;
        }
        // }
        purchaseOrderService.updateOrderIssap(purchaseOrder);
        return executeResult;
    }

    @RequestMapping("/api/searchDepartment")
    @ResponseBody
    public List<Department> searchDepartment(HttpServletRequest req, @RequestBody Department dept) {
        String loginUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        PurchaseOrderModel purchaseOrder = new PurchaseOrderModel();
        purchaseOrder.setLoginUserCode(loginUsernameCode);
        purchaseOrder.setCompanyCode(dept.getCompanyCode());
        purchaseOrder.setCostCenter(dept.getCostCenter());
        purchaseOrder.setBudgetDepartmentName(dept.getDepartmentName());
        List<Department> departments = purchaseOrderService.searchDepartmentByUserCode(purchaseOrder);
        return departments;
    }

    /**
     * 拆分次级编码 袁凯增
     *
     * @return
     */
    private ExecuteResult<PurchaseOrder> splitSecondCode(PurchaseOrder purchaseOrder) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();

        List<ApplyOrderDetail> applyOrderDetailList = new ArrayList<ApplyOrderDetail>();
        List<PurchaseOrderDetailModel> purchaseOrderDetailModel = purchaseOrderDetailService
                .searchPurchaseOrderDetailByOrderId(purchaseOrder);
        for (int i = 0; purchaseOrderDetailModel.size() > i; i++) {

            if (null != purchaseOrderDetailModel.get(i).getSecondary()
                    && !"".equals(purchaseOrderDetailModel.get(i).getSecondary())) {
                continue;
            }
            ApplyOrderDetail applyOrderDetail = new ApplyOrderDetail();
            applyOrderDetail.setId(purchaseOrderDetailModel.get(i).getApplyDetailId());
            ApplyOrderDetail applyOrderByOrderId = applyOrderDetailService.searchApplyOrderDetailById(applyOrderDetail);

            if (null == applyOrderByOrderId.getSecondary()) {
                // 次级编码为空...无法拆分
                continue;
            }
            /*
			 * else if ("指定".equals(applyOrderByOrderId.getCodingMode().trim()))
			 * { //指定...直接跳过当前循环,无需拆分 continue; }
			 */

            // 采购数量-根据数量拆分次级编码
            Integer applyCount = purchaseOrderDetailModel.get(i).getApplyCount();

            // 拆分次级编码开始值
            String secondaryHend = "";
            // 拆分次级编码长度
            int secondarySize = 0;
            // 拆分次级编码数值
            int secondaryInt = 0;

            // 下回次级编码为null 则代表要从初始值开始
            if (StringUtils.isBlank(applyOrderByOrderId.getNextSecondaryCoding())) {
                // 获取次级编码初始值的一些参数
                secondaryHend = applyOrderByOrderId.getSecondary();
                secondaryHend = secondaryHend.substring(0, secondaryHend.indexOf("-"));
                secondarySize = secondaryHend.length();
                secondaryInt = Integer.parseInt(secondaryHend);

            } else if (StringUtils.isNotBlank(applyOrderByOrderId.getNextSecondaryCoding())) {

                // 下回次级编码 开始值
                secondaryHend = applyOrderByOrderId.getNextSecondaryCoding();
                secondarySize = secondaryHend.length();
                secondaryInt = Integer.parseInt(secondaryHend);

            }

            // 拆分次级编码开始值参数 获取完毕secondaryHend 、secondarySize 、secondaryInt
			/*
			 * if (null == applyOrderByOrderId.getNextSecondaryCoding()) {
			 * applyCount = applyCount - 1; } else { applyCount = applyCount +
			 * 1; }
			 */
            // 数量
            String secondaryTail = "" + (secondaryInt + applyCount - 1);
            int secondaryTailSize = secondaryTail.length();

            for (int st = secondaryTailSize; secondarySize > st; st++) {
                secondaryTail = "0" + secondaryTail;
            }

            // 设置下个次级开始值
            String nextSecondary = "" + (secondaryInt + applyCount);
            int nextSecondarySize = nextSecondary.length();

            for (int st = nextSecondarySize; secondarySize > st; st++) {
                nextSecondary = "0" + nextSecondary;
            }

            applyOrderByOrderId.setNextSecondaryCoding(nextSecondary);
            applyOrderDetailList.add(applyOrderByOrderId);

            // 记录主编码和次级编码
            purchaseOrderDetailModel.get(i).setMainAssetCode(applyOrderByOrderId.getMainAssetCode());
            purchaseOrderDetailModel.get(i).setSecondary(secondaryHend + "-" + secondaryTail);

        }

        applyOrderDetailService.updateNextSecondaryCoding(applyOrderDetailList);
        purchaseOrderDetailService.updateCode(purchaseOrderDetailModel);

        return executeResult;
    }

    // 传sap获取 采购凭证号 采购凭证的项目编号 物料号 采购订单数量 订单单位 总帐科目编号
    private ExecuteResult<PurchaseOrder> sapOrder(PurchaseOrder purchaseOrderOnly) {

        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();

        try {

            PurchaseOrder purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrderOnly);
            String suberr = "";
			/* 采购组织和采购组的数字编码截取 */
            try {
                String group = purchaseOrder.getPurchasingGroup();
                int a = group.lastIndexOf("(") + 1;
                int b = group.lastIndexOf(")") < 0 ? group.length() : group.lastIndexOf(")");
                group = group.substring(a, b);
                if (group != null && !"".equals(group)) {
                    purchaseOrder.setPurchasingGroup(group);
                }
                String procurementOrganization = purchaseOrder.getProcurementOrganization();
                int c = procurementOrganization.lastIndexOf("(") + 1;
                int d = procurementOrganization.lastIndexOf(")") < 0 ? procurementOrganization.length() : procurementOrganization.lastIndexOf(")");
                procurementOrganization = procurementOrganization.substring(c, d);
                if (procurementOrganization != null && !"".equals(procurementOrganization)) {
                    purchaseOrder.setProcurementOrganization(procurementOrganization);
                }
            } catch (Exception e) {
                e.printStackTrace();
                suberr = e.getMessage();
            }
			/* 采购组织和采购组的数字编码截取结束 */
            // 付款类型编码
            purchaseOrder.setPaymentTypeCode("9001");

            // 根据采购订单号查询采购订单明细
            List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailService
                    .searchPurchaseOrderDetailByDocument(purchaseOrderOnly);

            // purchaseOrder.setPurchasingGroup(
            // purchaseOrder.getPurchasingGroup().substring(
            // purchaseOrder.getPurchasingGroup().lastIndexOf("(") == -1 ? 0 :
            // purchaseOrder.getPurchasingGroup().lastIndexOf("("),
            // purchaseOrder.getPurchasingGroup().lastIndexOf(")") == -1 ?
            // purchaseOrder.getPurchasingGroup().length() :
            // purchaseOrder.getPurchasingGroup().lastIndexOf(")")));

            // purchaseOrderDetails遍历 获取单位编码
			/*
			 * for (PurchaseOrderDetail purchaseOrderDetail :
			 * purchaseOrderDetails) { AssetUnit assetUnit = new AssetUnit();
			 * assetUnit.setUnitName(purchaseOrderDetail.getUnits()); AssetUnit
			 * assetUnit2 = assetUnitService.getAssetUnit(assetUnit);
			 * purchaseOrderDetail.setUnits(assetUnit2.getUnitCode()); }
			 */

            JSONObject mm = sapUtilService.createPurchaseOrderFromSAP(purchaseOrder, purchaseOrderDetails);

            if (null == mm) {
                executeResult.addErrorMessage("sap生成采购订单失败!");
                return executeResult;
            } else if (!"".equals(mm.getString("error"))) {
                executeResult.addErrorMessage("sap生成采购订单失败!" + mm.getString("error") + suberr);
                return executeResult;
            }

            purchaseOrder.setPurchaseOrderSapId(mm.getString("ebeln")); // 采购凭证号

            purchaseOrder.setPurchaseOrderSapProjectId(mm.getString("ebelp")); // 采购凭证的项目编号
            purchaseOrder.setMatnr(mm.getString("matnr")); // 物料号
            purchaseOrder.setLedgerAccount(mm.getString("sakto")); // 总账科目
            purchaseOrder.setMeins(mm.getString("meins")); // 订单单位

            purchaseOrderService.updatePurchaseOrder(purchaseOrder);

            executeResult.setSuccessMessage("采购订单生成 接口 --返回结果!!!-----:" + mm);
            System.out.println("采购订单生成 接口 --返回结果!!!-----:" + mm);

        } catch (Exception e) {
            LOGGER.error("Exception", e);
            executeResult.addErrorMessage("sap生成采购订单失败!");
        }
        return executeResult;
    }

    /**
     * 退回
     *
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/api/denyWorkFlow")
    @ResponseBody
    public ExecuteResult<PurchaseOrder> denyPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder,
                                                          HttpServletRequest request) {

        PurchaseOrder purchaseOrderonly = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);
        purchaseOrderonly.setIdea(purchaseOrder.getIdea());
        purchaseOrderonly.setIsPhoneMessage(purchaseOrder.getIsPhoneMessage());
        purchaseOrderonly.setIsMail(purchaseOrder.getIsMail());
        ExecuteResult<PurchaseOrder> executeResult = handleApplyRequest(purchaseOrderonly, request);
        purchaseOrderonly = executeResult.getResult();
        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsMail())) {
            purchaseOrderonly.setIsMail("Y");
        } else {
            purchaseOrderonly.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsPhoneMessage())) {
            purchaseOrderonly.setIsPhoneMessage("Y");
        } else {
            purchaseOrderonly.setIsPhoneMessage("N");
        }
        purchaseOrderonly.setIsPhoneMessage(purchaseOrder.getIsPhoneMessage());
        purchaseOrderonly.setActivityId(purchaseOrder.getActivityId());

        if (!executeResult.isSuccess()) {
            return executeResult;
        }

        executeResult = purchaseOrderService.denyWorkFlow(purchaseOrderonly);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }

		/*if (purchaseOrder.isWithDrawPhoto()) {
			// TODO: 15/04/2017 如果需要退回影像，逻辑写在这里
		}*/

        PurchaseOrder searchPurchaseOrderByOrderId = purchaseOrderService
                .searchPurchaseOrderByOrderId(purchaseOrderonly);

        // //调用发生短信和邮件接口
        // if
        // (!APPROVESTATE.DENY.getApproveState().trim().equals(applyOrder.getApprovalState().trim())
        // &&
        // !APPROVESTATE.DONE.getApproveState().trim().equals(applyOrder.getApprovalState().trim()))
        // {
        if (StringUtils.isNotBlank(searchPurchaseOrderByOrderId.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPurchaseOrderByOrderId.getCnexthandlercode(),
                    searchPurchaseOrderByOrderId.getCdocument(), purchaseOrderonly.getIsPhoneMessage(),
                    purchaseOrderonly.getIsMail());
            //executeResult.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
            // }
        }
        return executeResult;
    }

    @RequestMapping("/api/dropWorkFlow")
    @ResponseBody
    public ExecuteResult<PurchaseOrder> dropPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder,
                                                          HttpServletRequest request) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();

        PurchaseOrder searchPurchaseOrderByOrderId = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);
        if (STATUS.YES_STATUS.getStatus().equals(searchPurchaseOrderByOrderId.getOrderStatus())) {
            executeResult.addErrorMessage("已关闭的单据不可弃审!");
            return executeResult;
        }
        Long searchNextReceiveCount = purchaseOrderService.searchNextReceiveCount(purchaseOrder);
        if (searchNextReceiveCount > 0) {
            executeResult.addErrorMessage("已生成收货单的单据不可弃审!");
            return executeResult;
        }
        PurchaseOrder purchaseOrderOnly = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);

        executeResult = handleApplyRequest(purchaseOrderOnly, request);
        purchaseOrderOnly = executeResult.getResult();
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        purchaseOrderOnly.setIdea(purchaseOrder.getIdea());
        purchaseOrderOnly.setIsMail(purchaseOrder.getIsMail());
        purchaseOrderOnly.setIsPhoneMessage(purchaseOrder.getIsPhoneMessage());
        purchaseOrderOnly.setActivityId(purchaseOrder.getActivityId());

        executeResult = purchaseOrderService.dropWorkFlow(purchaseOrderOnly);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        PurchaseOrder po = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrderOnly);
        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsMail())) {
            po.setIsMail(STATUS.YES.getStatus());
        } else {
            po.setIsMail(STATUS.NO.getStatus());
        }
        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsPhoneMessage())) {
            po.setIsPhoneMessage(STATUS.YES.getStatus());
        } else {
            po.setIsPhoneMessage(STATUS.NO.getStatus());
        }
        if (StringUtils.isNotBlank(po.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(po.getCnexthandlercode(), po.getCdocument(), po.getIsPhoneMessage(), po.getIsMail());
            //executeResult.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        }

        // ApplyOrder applyOrder =
        // applyOrderService.searchApplyOrderByOrderId(applyOrderOnly);
        //
        //// //调用发生短信和邮件接口
        // if
        // (!APPROVESTATE.DENY.getApproveState().trim().equals(applyOrder.getApprovalState().trim())
        // &&
        // !APPROVESTATE.DONE.getApproveState().trim().equals(applyOrder.getApprovalState().trim()))
        // {
        // remindedService.BellReminder(applyOrder.getNextHandlerCode(),
        // applyOrder.getCdocument(), applyOrderOnly.getIsPhoneMessage(),
        // applyOrderOnly.getIsMail());
        // }
        return executeResult;
    }

    public ExecuteResult<PurchaseOrder> handleApplyRequest(PurchaseOrder purchaseOrder, HttpServletRequest request) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();

        if (StringUtils.isBlank(purchaseOrder.getCdocument())) {
            executeResult.addErrorMessage("单据号为空!");
            return executeResult;
        }

        PurchaseOrder purchaseOrderDocument = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);

        if (null == purchaseOrderDocument.getCdocument()) {
            executeResult.addErrorMessage("流程号不存在");
        }

        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        User userDeptInfo = userService.getUserDeptInfo(userCode);

        if (null == userDeptInfo) {
            executeResult.addErrorMessage("当前登陆人信息查询失败");
            return executeResult;
        }

        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsMail())) {
            purchaseOrderDocument.setIsMail("Y");
        } else {
            purchaseOrderDocument.setIsMail("N");
        }

        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsPhoneMessage())) {
            purchaseOrderDocument.setIsPhoneMessage("Y");
        } else {
            purchaseOrderDocument.setIsPhoneMessage("N");
        }

        if (StringUtils.isNotBlank(purchaseOrder.getIdea())) {
            purchaseOrderDocument.setIdea(purchaseOrder.getIdea());
        } else {
            purchaseOrderDocument.setIdea("");
        }

        // 设置当前登陆人信息
        purchaseOrderDocument.setCaUserCode(userCode);
        purchaseOrderDocument.setCaUserName(userDeptInfo.getUserName());
        purchaseOrderDocument.setUserDepCode(userDeptInfo.getDepartmentCode());

        executeResult.setResult(purchaseOrderDocument);
        return executeResult;
    }

	/*
	 * @RequestMapping("/searchPurchaseOrderDetailList") public String
	 * searchPurchaseOrderList(Model model, HttpServletRequest req,
	 * PurchaseOrderDetailModel purchaseOrderDetail) {
	 * List<PurchaseOrderDetailModel> purchaseOrderDetailList =
	 * purchaseOrderDetailService.searchPurchaseOrderDetailList(
	 * purchaseOrderDetail); //PurchaseOrder
	 * purchaseOrder=purchaseOrderDetailList.get(0).getPurchaseOrder();
	 * //purchaseOrderService.get(null,); if (purchaseOrderDetailList == null ||
	 * purchaseOrderDetailList.size() == 0) {
	 * model.addAttribute("purchaseOrderList", null); } else { String
	 * purchaseOrderId = purchaseOrderDetailList.get(0).getPurchaseOrderId();
	 * PurchaseOrder purchaseOrder = new PurchaseOrder(); //修改 wangxg
	 * //purchaseOrder.setPurchaseOrderId(purchaseOrderId);
	 * purchaseOrder.setCdocument(purchaseOrderId); purchaseOrder =
	 * purchaseOrderService.get(purchaseOrder);
	 * model.addAttribute("purchaseOrder", purchaseOrder); JsonConfig config =
	 * new JsonConfig(); config.setIgnoreDefaultExcludes(false);
	 * config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); //
	 * 只要设置这个数组，指定过滤哪些字段。 config.setExcludes(new String[] {"createDate",
	 * "updateDate" }); JSONArray jsonArr = net.sf.json.JSONArray.fromObject(
	 * purchaseOrderDetailList, config); model.addAttribute("purchaseOrderList",
	 * jsonArr); } return "purchase/add_receive_goods_row"; }
	 */

    /**
     * 添加采购订单页面
     *
     * @param model
     * @param req
     * @return
     */
    @RequestMapping("/addPurchaseOrder")
    public String addApplyOrder(Model model, HttpServletRequest req) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        // 生产订单编号
        String str = "CGDD";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        str += sdf.format(date);
        int searchNextSequence = purchaseOrderService.searchNextSequence();
        // 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
        str += String.format("%04d", searchNextSequence);
        purchaseOrder.setCdocument(str);
        purchaseOrder.setApplyDate(date);

        // 申请人就是 采购员
        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString()
                .trim();
        purchaseOrder.setApplyUser(applyUsername);
        purchaseOrder.setBuyerName(applyUsername);
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString()
                .trim();
        purchaseOrder.setApplyUserCode(applyUsernameCode);
        purchaseOrder.setBuyerCode(applyUsernameCode);

        // 币种
        List<UserInfo> currencylist = userInfoService.searchCurrencyInfo();
        model.addAttribute("currencylist", currencylist);
        // 查询所有公司
        List<UserInfo> companys = userInfoService.searchAllCompany();

        model.addAttribute("companys", companys);
        model.addAttribute("purchaseOrder", purchaseOrder);
        return "purchase/purchase_order_add";
    }

    /**
     * 校验供应商
     *
     * @param purchaseOrder
     * @return
     */
    public ExecuteResult<PurchaseOrder> checkSupplier(PurchaseOrder purchaseOrder) {
        ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        String supplierCode = purchaseOrder.getSupplierCode();
        if ("1".equals(supplierCode) || "2".equals(supplierCode)) {
            if (null == purchaseOrder.getSupplierName() || "".equals(purchaseOrder.getSupplierName())) {
                result.addErrorMessage("供应商名称不能为空!");
            }
            if (null == purchaseOrder.getZipCode() || "".equals(purchaseOrder.getZipCode())) {
                result.addErrorMessage("邮编不能为空!");
            }
            if (null == purchaseOrder.getBankName() || "".equals(purchaseOrder.getBankName())) {
                result.addErrorMessage("开户行不能为空!");
            }
            if (null == purchaseOrder.getBankNumber() || "".equals(purchaseOrder.getBankNumber())) {
                result.addErrorMessage("银行账号不能为空!");
            }
            if (null == purchaseOrder.getContactNumber() || "".equals(purchaseOrder.getContactNumber())) {
                result.addErrorMessage("联行号不能为空!");
            }
            if (null == purchaseOrder.getContacts() || "".equals(purchaseOrder.getContacts())) {
                result.addErrorMessage("联系人不能为空!");
            }
            if (null == purchaseOrder.getTelephone() || "".equals(purchaseOrder.getTelephone())) {
                result.addErrorMessage("电话不能为空!");
            }
            if (null == purchaseOrder.getCompanyAddress() || "".equals(purchaseOrder.getCompanyAddress())) {
                result.addErrorMessage("公司地址不能为空!");
            }
            if (null == purchaseOrder.getCompanySpecificName() || "".equals(purchaseOrder.getCompanySpecificName())) {
                result.addErrorMessage("公司具体名称不能为空!");
            }
            if (null == purchaseOrder.getCountry() || "".equals(purchaseOrder.getCountry())) {
                result.addErrorMessage("国家不能为空!");
            }
            if (null == purchaseOrder.getCity() || "".equals(purchaseOrder.getCity())) {
                result.addErrorMessage("城市不能为空!");
            }
        } else {
            Supplier supplier = new Supplier();
            supplier.setSupplierCode(purchaseOrder.getSupplierCode());
            supplier.setSupplierName(purchaseOrder.getSupplierName());
            List<Supplier> searchSupplier = supplierService.searchSupplierByCodeAndName(supplier);
            if (searchSupplier.size() == 0) {
                result.addErrorMessage("供应商选择不正确!");
            }
        }

        return result;
    }

    @RequestMapping("/api/savePurchase")
    @ResponseBody
    public ExecuteResult<PurchaseOrder> savePurchase(@RequestBody PurchaseOrderWrapper purchaseOrderWrapper,
                                                     HttpServletRequest req) {
        ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        PurchaseOrder purchaseOrder = purchaseOrderWrapper.getPurchaseOrder();
        List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderWrapper.getPurchaseOrderDetails();
        List<PurchaseApplyMapping> purchaseApplyMappings = purchaseOrderWrapper.getPurchaseApplyMappings();
        List<PaymentTerm> paymentTerms = purchaseOrderWrapper.getPaymentTerms();
        //判断是否为草稿，若是草稿，判断资产明细（mapping表）中的需求单是否有已关闭的，若有，不能提交
        if (STATUS.YES.getStatus().equals(purchaseOrder.getDraft())) {
            boolean flag = false;
            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails) {
                String orderStatus = purchaseOrderService.searchApplyOrderStatus(purchaseOrderDetail.getApplyOrderId());
                if (STATUS.YES_STATUS.getStatus().equals(orderStatus)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                result.addErrorMessage("资产明细中有已关闭单据，不能保存！");
                return result;
            }
        }
        // 验证明细行必须大于0
        if (purchaseOrderDetails.size() == 0) {
            result.addErrorMessage("明细不能少于1行！");
            return result;
        }
        result = checkSupplier(purchaseOrder);
        if (!result.isSuccess()) {
            return result;
        }
        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString()
                .trim();
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString()
                .trim();
        // 订单表头
        if ("N".equals(purchaseOrder.getDraft())) {
            purchaseOrder.setCapprovalstate("等待审批");
        }
        purchaseOrder.setOrderStatus("未关闭");
        purchaseOrder.setPrint("N");
        purchaseOrder.setApplyUser(applyUsername);
        purchaseOrder.setCreateBy(applyUsername);
        purchaseOrder.setUpdateBy(applyUsername);
        purchaseOrder.setApplyUserCode(applyUsernameCode);
        purchaseOrder.setCreateByCode(applyUsernameCode);
        purchaseOrder.setUpdateByCode(applyUsernameCode);
        purchaseOrder.setCreateDate(new Date());
        purchaseOrder.setUpdateDate(new Date());

        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsMail())) {
            purchaseOrder.setIsMail("Y");
        } else {
            purchaseOrder.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsPhoneMessage())) {
            purchaseOrder.setIsPhoneMessage("Y");
        } else {
            purchaseOrder.setIsPhoneMessage("N");
        }

        ApplyOrder applyOrder = new ApplyOrder();
        applyOrder.setCdocument(purchaseOrderDetails.get(0).getApplyOrderId());
        ApplyOrder searchApplyOrderByOrderId = applyOrderService.searchApplyOrderByOrderId(applyOrder);
        purchaseOrder.setOfficeLocation(searchApplyOrderByOrderId.getOfficeLocation());

        // 订单表体明细
        for (PurchaseOrderDetail pod : purchaseOrderDetails) {
            pod.setPurchaseOrderId(purchaseOrder.getCdocument());
            pod.setApplyUser(applyUsername);
            pod.setCreateBy(applyUsername);
            pod.setUpdateBy(applyUsername);
            pod.setApplyUserCode(applyUsernameCode);
            pod.setCreateByCode(applyUsernameCode);
            pod.setUpdateByCode(applyUsernameCode);
            pod.setCreateDate(new Date());
            pod.setUpdateDate(new Date());
            pod.setExchangeRate(purchaseOrder.getExchangeRate());
            pod.setCurrencyCode(purchaseOrder.getCurrencyCode());
            // 税额 = 采购总价 /(1+税率)* 税率
            // BigDecimal tax = (pod.getPurchasePrice().divide(new
            // BigDecimal(1+purchaseOrder.getTaxRate()))).multiply(new
            // BigDecimal(purchaseOrder.getTaxRate()));
            // pod.setTax(tax);
        }
        // 订单资产明细
        for (PurchaseApplyMapping pam : purchaseApplyMappings) {
            pam.setPurchaseId(purchaseOrder.getCdocument());
            pam.setCreateBy(applyUsername);
            pam.setUpdateBy(applyUsername);
            pam.setCreateDate(new Date());
            pam.setUpdateDate(new Date());
        }

        // 订单付款条件
        for (PaymentTerm pt : paymentTerms) {
            pt.setPurchaseId(purchaseOrder.getCdocument());
            pt.setCreateBy(applyUsername);
            pt.setUpdateBy(applyUsername);
            pt.setCreateDate(new Date());
            pt.setUpdateDate(new Date());
        }
        try {
            result = purchaseOrderService.addSavePurchase(purchaseOrder, purchaseOrderDetails, purchaseApplyMappings,
                    paymentTerms);
        } catch (OutBudgetException e) {
            LOGGER.error("e", e);
            result.addErrorMessage(e.getMessage());
            return result;
        } catch (Exception e) {
            LOGGER.error("e", e);
            result.addErrorMessage("保存头信息失败");
            return result;
        }
        if (!result.isSuccess()) {
            return result;
        }
        if (STATUS.NO.getStatus().equals(purchaseOrder.getDraft())) {
            // 申请 绑定审批流
            result = purchaseOrderService.applyComplaint(purchaseOrder);
        }
        // 出现错误 删除数据
        if (!result.isSuccess()) {
            // 删除保存数据......
            // purchaseOrderService.delApplyOrderByCdocument(purchaseOrder);
            purchaseOrderService.deletePurchaseOrderByPurchaseId(purchaseOrder);
            workFlowService.deleteGarbegeWorkFlowData(purchaseOrder.getCdocument(),
                    Env.getProperty("purchase.info.type.id"));
            return result;
        }

        try {
            result = purchaseOrderService.addSavePurchaseDetail(purchaseOrder, purchaseOrderDetails, purchaseApplyMappings,
                    paymentTerms);
        } catch (OutBudgetException e) {
            purchaseOrderService.deletePurchaseOrderByPurchaseId(purchaseOrder);
            workFlowService.deleteGarbegeWorkFlowData(purchaseOrder.getCdocument(),
                    Env.getProperty("purchase.info.type.id"));
            LOGGER.error("e", e);
            result.addErrorMessage(e.getMessage());
            return result;
        } catch (Exception e) {
            purchaseOrderService.deletePurchaseOrderByPurchaseId(purchaseOrder);
            workFlowService.deleteGarbegeWorkFlowData(purchaseOrder.getCdocument(),
                    Env.getProperty("purchase.info.type.id"));
            result.addErrorMessage("保存失败");
            return result;
        }
        // 如果保存明细出现错误(预算超出等问题) 删除采购订单数据
        if (!result.isSuccess()) {
            // 删除保存数据......
            purchaseOrderService.deletePurchaseOrderByPurchaseId(purchaseOrder);
            workFlowService.deleteGarbegeWorkFlowData(purchaseOrder.getCdocument(),
                    Env.getProperty("purchase.info.type.id"));
            return result;
        }
        if ("N".equals(purchaseOrder.getDraft())) {
            PurchaseOrder searchPurchaseOrderByOrderId = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPurchaseOrderByOrderId.getCnexthandlercode(),
                    searchPurchaseOrderByOrderId.getCdocument(), purchaseOrder.getIsPhoneMessage(),
                    purchaseOrder.getIsMail());
            //result.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                result.addErrorMessage("保存成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }

        return result;
    }

    /**
     * 保存修改
     *
     * @param purchaseOrderWrapper
     * @param req
     * @return
     */
    @RequestMapping("/api/updatePurchase")
    @ResponseBody
    public ExecuteResult<PurchaseOrder> updatePurchase(@RequestBody PurchaseOrderWrapper purchaseOrderWrapper,
                                                       HttpServletRequest req) {
        ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        PurchaseOrder purchaseOrder = purchaseOrderWrapper.getPurchaseOrder();
        List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderWrapper.getPurchaseOrderDetails();
        List<PurchaseApplyMapping> purchaseApplyMappings = purchaseOrderWrapper.getPurchaseApplyMappings();
        List<PaymentTerm> paymentTerms = purchaseOrderWrapper.getPaymentTerms();

        // 验证明细行必须大于0

        if (purchaseOrderDetails.size() == 0) {
            result.addErrorMessage("明细不能少于1行！");
            return result;
        }
        result = checkSupplier(purchaseOrder);
        if (!result.isSuccess()) {
            return result;
        }
        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString()
                .trim();
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString()
                .trim();
        // 订单表头
        PurchaseOrder oldPurchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);
        if (STATUS.NO.getStatus().equals(purchaseOrder.getDraft()) && STATUS.YES.getStatus().equals(oldPurchaseOrder.getDraft())) {
            ApplyOrderDetail aod = new ApplyOrderDetail();
            ApplyOrderDetail aod1 = new ApplyOrderDetail();
            ApplyOrder ao = new ApplyOrder();
            ApplyOrder ao1 = new ApplyOrder();
            for (PurchaseApplyMapping pm : purchaseApplyMappings) {
                aod.setId(pm.getApplyDetailId());
                aod1 = applyOrderDetailService.searchApplyOrderDetailById(aod);
                ao.setCdocument(aod1.getApplyOrderId());
                ao1 = applyOrderService.searchApplyOrderByOrderId(ao);
                if (null == ao1 || STATUS.YES_STATUS.getStatus().equals(ao1.getOrderStatus())) {
                    result.addErrorMessage("订单行明细中有已关闭或无效的单据，不能提交！");
                    return result;
                }
            }
        }
        if (STATUS.NO.getStatus().equals(purchaseOrder.getDraft())) {
            purchaseOrder.setCapprovalstate("等待审批");

        }
        purchaseOrder.setOrderStatus("未关闭");
        purchaseOrder.setPrint("N");
        purchaseOrder.setApplyUser(applyUsername);
        purchaseOrder.setCreateBy(applyUsername);
        purchaseOrder.setUpdateBy(applyUsername);
        purchaseOrder.setApplyUserCode(applyUsernameCode);
        purchaseOrder.setCreateByCode(applyUsernameCode);
        purchaseOrder.setUpdateByCode(applyUsernameCode);
        purchaseOrder.setUpdateDate(new Date());

        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsMail())) {
            purchaseOrder.setIsMail("Y");
        } else {
            purchaseOrder.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(purchaseOrder.getIsPhoneMessage())) {
            purchaseOrder.setIsPhoneMessage("Y");
        } else {
            purchaseOrder.setIsPhoneMessage("N");
        }

        // 订单表体明细
        for (PurchaseOrderDetail pod : purchaseOrderDetails) {
            pod.setPurchaseOrderId(purchaseOrder.getCdocument());
            pod.setApplyUser(applyUsername);
            pod.setCreateBy(applyUsername);
            pod.setUpdateBy(applyUsername);
            pod.setApplyUserCode(applyUsernameCode);
            pod.setCreateByCode(applyUsernameCode);
            pod.setUpdateByCode(applyUsernameCode);
            pod.setCreateDate(new Date());
            pod.setUpdateDate(new Date());
            pod.setExchangeRate(purchaseOrder.getExchangeRate());
            pod.setCurrencyCode(purchaseOrder.getCurrencyCode());
            // 税额 = 采购总价 /(1+税率)* 税率
            // BigDecimal tax = (pod.getPurchasePrice().divide(new
            // BigDecimal(1+purchaseOrder.getTaxRate()))).multiply(new
            // BigDecimal(purchaseOrder.getTaxRate()));
            // pod.setTax(tax);
        }
        // 订单资产明细
        for (PurchaseApplyMapping pam : purchaseApplyMappings) {
            pam.setPurchaseId(purchaseOrder.getCdocument());
            pam.setCreateBy(applyUsername);
            pam.setUpdateBy(applyUsername);
            pam.setCreateDate(new Date());
            pam.setUpdateDate(new Date());
        }

        // 订单付款条件
        for (PaymentTerm pt : paymentTerms) {
            pt.setPurchaseId(purchaseOrder.getCdocument());
            pt.setCreateBy(applyUsername);
            pt.setUpdateBy(applyUsername);
            pt.setCreateDate(new Date());
            pt.setUpdateDate(new Date());
        }

        purchaseOrderService.update(purchaseOrder);
        try {
            result = purchaseOrderService.addUpdatePurchase(purchaseOrder, purchaseOrderDetails, purchaseApplyMappings,
                    paymentTerms, oldPurchaseOrder);
        } catch (OutBudgetException e) {
            LOGGER.error("e", e);
            result.addErrorMessage(e.getMessage());
            purchaseOrderService.update(oldPurchaseOrder);
            return result;
        } catch (RemoteException e) {
            LOGGER.error("e", e);
            result.addErrorMessage(e.getMessage());
            purchaseOrderService.update(oldPurchaseOrder);
            return result;
        }
        // result = purchaseOrderService.applyComplaint(purchaseOrder);
        if (!result.isSuccess()) {
            purchaseOrderService.update(oldPurchaseOrder);
            return result;
        }
        if ("N".equals(purchaseOrder.getDraft())) {
            PurchaseOrder searchPurchaseOrderByOrderId = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);

            // //调用发生短信和邮件接口
            // if
            // (!APPROVESTATE.DENY.getApproveState().trim().equals(applyOrder.getApprovalState().trim())
            // &&
            // !APPROVESTATE.DONE.getApproveState().trim().equals(applyOrder.getApprovalState().trim()))
            // {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPurchaseOrderByOrderId.getCnexthandlercode(),
                    searchPurchaseOrderByOrderId.getCdocument(), purchaseOrder.getIsPhoneMessage(),
                    purchaseOrder.getIsMail());
            // }
            //result.setSuccessMessage(er.getSuccessMessage());
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
     * 修改页面
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @return
     */
    @RequestMapping("/updatePurchaseOrder")
    public String updatePurchaseOrder(Model model, HttpServletRequest req, Long offset, Long pageSize) {
        // 单据号
        String strId = req.getParameter("id");
        PurchaseOrder pOrder = new PurchaseOrder();
        pOrder.setCdocument(strId);
        PurchaseOrder purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(pOrder);
        List<PurchaseOrderDetailModel> purchaseOrderDetailModels = purchaseOrderDetailService
                .searchPurchaseOrderDetailByOrderId(pOrder);
        List<PurchaseApplyMappingModel> purchaseApplyMappingModels = purchaseOrderService
                .searchPurchaseApplyMappingByOrderId(pOrder);
        List<PaymentTerm> paymentTerms = paymentTermService.searchPaymentTermByOrderId(purchaseOrder);

        String loginUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString()
                .trim();

        // 当登录人不是申请人时 也不是授权
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUsernameCode);
        List<UserInfo> searchCauthorizerCode = userInfoService.searchCauthorizerCode(userInfo);
        for (UserInfo userInfo2 : searchCauthorizerCode) {
            if (purchaseOrder.getApplyUserCode().equals(userInfo2.getCauthorizerCode())) {
                purchaseOrder.setIsAuthorize("Y");
            }
        }
        if (!loginUsernameCode.equals(purchaseOrder.getApplyUserCode())
                && !"Y".equals(purchaseOrder.getIsAuthorize())) {
            model.addAttribute("updateErrorMessage", "当前登录人不是申请人，不可修改订单！");
        }
        // 单据正在审批或者已完成时不可以修改单据
        if (APPROVESTATE.PROCESSING.getApproveState().equals(purchaseOrder.getCapprovalstate())
                || APPROVESTATE.DONE.getApproveState().equals(purchaseOrder.getCapprovalstate())) {
            model.addAttribute("updateErrorMessage", "审批状态单据不可修改！");
        }
        // 关闭订单不可修改
        if (STATUS.YES_STATUS.getStatus().equals(purchaseOrder.getOrderStatus())) {
            model.addAttribute("updateErrorMessage", "已关闭订单不可修改！");
        }

        // 币种
        List<UserInfo> currencylist = userInfoService.searchCurrencyInfo();
        model.addAttribute("currencylist", currencylist);
        // 查询所有公司
        List<UserInfo> companys = userInfoService.searchAllCompany();

        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("purchaseOrderDetailModels", purchaseOrderDetailModels);
        model.addAttribute("purchaseApplyMappingModels", purchaseApplyMappingModels);
        model.addAttribute("paymentTerms", paymentTerms);
        model.addAttribute("companys", companys);

        return "purchase/purchase_order_update";
    }

    @RequestMapping("/api/delPurchase")
    @ResponseBody
    public ExecuteResult<PurchaseOrder> delPurchase(String ids) {
        ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        String[] purchaseIds = ids.split(",");

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        if (purchaseIds != null && purchaseIds.length != '0') {
            for (String string : purchaseIds) {
                purchaseOrder.setCdocument(string);
                try {
                    PurchaseOrder searchPurchaseOrderByOrderId = purchaseOrderService
                            .searchPurchaseOrderByOrderId(purchaseOrder);
                    List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailService
                            .searchPurchaseOrderDetailByDocument(purchaseOrder);
                    List<PurchaseApplyMappingModel> purchaseApplyMappingModels = purchaseOrderService
                            .searchPurchaseApplyMappingByOrderId(purchaseOrder);

                    purchaseOrderService.deletePurchaseOrder(searchPurchaseOrderByOrderId, purchaseOrderDetails,
                            purchaseApplyMappingModels);
                } catch (OutBudgetException e) {
                    LOGGER.error("e", e);
                    result.addErrorMessage(e.getMessage());
                    return result;
                } catch (Exception e) {
                    LOGGER.error("e", e);
                    result.addErrorMessage(e.getMessage());
                    return result;
                }

                workFlowService.deleteGarbegeWorkFlowData(string, Env.getProperty("purchase.info.type.id"));
            }
        } else {
            result.addErrorMessage("请至少选择一条数据");
        }

        return result;
    }

    @RequestMapping("/api/closePurchase")
    @ResponseBody
    public ExecuteResult<PurchaseOrder> closePurchase(String ids) {
        ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        String[] purchaseIds = ids.split(",");

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        if (purchaseIds != null && purchaseIds.length != '0') {
            for (String string : purchaseIds) {
                purchaseOrder.setCdocument(string);
                PurchaseOrder searchPurchaseOrderByOrderId = purchaseOrderService
                        .searchPurchaseOrderByOrderId(purchaseOrder);
                if (STATUS.NO_STATUS.getStatus().equals(searchPurchaseOrderByOrderId.getOrderStatus())) {
                    List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailService
                            .searchPurchaseOrderDetailByDocument(purchaseOrder);
                    List<PurchaseApplyMappingModel> purchaseApplyMappingModels = purchaseOrderService
                            .searchPurchaseApplyMappingByOrderId(purchaseOrder);
                    purchaseOrderService.updateClosePurchaseOrder(searchPurchaseOrderByOrderId, purchaseOrderDetails,
                            purchaseApplyMappingModels);
                }
            }
        } else {
            result.addErrorMessage("请至少选择一条数据");
        }

        return result;
    }

    @RequestMapping("/api/closePurchaseForPayment")
    @ResponseBody
    public ExecuteResult<PurchaseOrder> closePurchaseForPayment(String ids) {
        ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        String[] purchaseIds = ids.split(",");

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        if (purchaseIds != null && purchaseIds.length != '0') {
            for (String string : purchaseIds) {
                purchaseOrder.setCdocument(string);
                PurchaseOrder searchPurchaseOrderByOrderId = purchaseOrderService
                        .searchPurchaseOrderByOrderId(purchaseOrder);
                searchPurchaseOrderByOrderId.setIsCloseForPayment("已关闭");
                purchaseOrderService.updateClosePurchaseOrderForPayment(searchPurchaseOrderByOrderId);
            }
        } else {
            result.addErrorMessage("请至少选择一条数据");
        }

        return result;
    }

    /**
     * 打印
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/printOrder")
    public String printOrder(Model model, HttpServletRequest request, String typeId) {

        String cdocument = request.getParameter("id");

        PurchaseOrder pOrder = new PurchaseOrder();
        pOrder.setCdocument(cdocument);
        PurchaseOrder purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(pOrder);
        List<PurchaseOrderDetailModel> purchaseOrderDetailModels = purchaseOrderDetailService
                .searchPurchaseOrderDetailByOrderId(pOrder);
        List<PurchaseApplyMappingModel> purchaseApplyMappingModels = purchaseOrderService
                .searchPurchaseApplyMappingByOrderId(pOrder);
        List<PaymentTerm> paymentTerms = paymentTermService.searchPaymentTermByOrderId(purchaseOrder);
        if (purchaseOrder.getCompanyName().endsWith("欧普照明电器中山有限公司")) {
            purchaseOrder.setCompanyName("欧普照明电器（中山）有限公司");
        }
        purchaseOrderService.updatePrint(purchaseOrder);
        /**
         * 获取审批流流程的历史记录
         */
        List<ApproveHistoryCheckPrint> appHistoryPager = checkAcceptanceService.getApproveHistory(cdocument, typeId);
        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("purchaseOrderDetailModels", purchaseOrderDetailModels);
        model.addAttribute("purchaseApplyMappingModels", purchaseApplyMappingModels);
        model.addAttribute("paymentTerms", paymentTerms);
        return "purchase/purchase_order_stamp";
    }

    @RequestMapping(value = "/api/exportPurchase", method = RequestMethod.GET)
    @ResponseBody
    public void exportPurchase(ModelMap model, HttpServletRequest req, HttpServletResponse response,
                               PurchaseOrderModel purchaseOrderModel) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 开始时间
            purchaseOrderModel.setApplyDateStart(sdf.parse(req.getParameter("txtBeginDate")));
        } catch (Exception e) {
            purchaseOrderModel.setApplyDateStart(null);
        }
        try {
            // 结束时间
            purchaseOrderModel.setApplyDateEnd(sdf.parse(req.getParameter("txtEndDate")));
        } catch (Exception e) {
            purchaseOrderModel.setApplyDateEnd(null);
        }
        purchaseOrderModel.setDraft(null == purchaseOrderModel.getDraft() ? "N" : purchaseOrderModel.getDraft());
        // 设置当前登录人编码
        purchaseOrderModel.setLoginUserCode(
                req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        purchaseOrderModel.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        List<PurchaseOrder> exportPurchaseOrder = purchaseOrderService.exportPurchaseOrder(purchaseOrderModel);

        String fileName = "采购订单列表.xlsx";
        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportPurchaseTemplate ept = new ExportPurchaseTemplate();
            ept.doExport(response, fileName, exportPurchaseOrder);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
    }

    @RequestMapping("/api/searchFactoryService")
    @ResponseBody
    public List<Factory> searchFactoryService(String ccompanyCode) {
        return factoryService.searchByCompanyCode(ccompanyCode);
    }
}
