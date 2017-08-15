package com.opple.fa.purchase.webapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.AxisFault;
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

import com.opple.barservice.BarServiceStub;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.ScanLocation;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.ScanLocationService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.purchase.entity.CheckAcceptanceDetail;
import com.opple.fa.purchase.entity.InvoiceInspection;
import com.opple.fa.purchase.entity.InvoiceInspectionDetail;
import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PaymentOrderDetail;
import com.opple.fa.purchase.entity.PaymentPaymentMapping;
import com.opple.fa.purchase.entity.PaymentTerm;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.WARNSTATE;
import com.opple.fa.purchase.export.controller.ExportPaymentOrderTemplate;
import com.opple.fa.purchase.model.ApproveHistoryCheckPrint;
import com.opple.fa.purchase.model.InvoiceInspectionModel;
import com.opple.fa.purchase.model.PaymentOrderDetailModel;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.purchase.model.PaymentOrderWrapper;
import com.opple.fa.purchase.model.PaymentPaymentMappingModel;
import com.opple.fa.purchase.service.CheckAcceptanceDetailService;
import com.opple.fa.purchase.service.CheckAcceptanceService;
import com.opple.fa.purchase.service.InvoiceInspectionDetailService;
import com.opple.fa.purchase.service.InvoiceInspectionService;
import com.opple.fa.purchase.service.PaymentOrderDetailService;
import com.opple.fa.purchase.service.PaymentOrderService;
import com.opple.fa.purchase.service.PaymentPaymentMappingService;
import com.opple.fa.purchase.service.PaymentTermService;
import com.opple.fa.purchase.service.PurchaseCommonService;
import com.opple.fa.purchase.service.PurchaseOrderService;
import com.opple.fa.sap.entity.Eaddressdata;
import com.opple.fa.sap.model.ItDataModel;
import com.opple.fa.sap.service.SapUtilService;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.entity.Employee;
import com.opple.security.entity.FileUpload;
import com.opple.security.entity.User;
import com.opple.security.service.FileUploadService;
import com.opple.security.service.RemindedService;
import com.opple.security.service.UserService;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.voucherService.VoucherServiceStub;
import com.opple.workflow.WorkFlowStub;
import com.opple.workflow.entity.APPROVESTATE;
import com.opple.workflow.entity.ApproveHistory;
import com.opple.workflow.service.WorkFlowService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PaymentController.class);
    @Resource
    private PaymentOrderService paymentOrderService;
    @Resource
    private PaymentOrderDetailService paymentOrderDetailService;
    @Resource
    private PaymentPaymentMappingService paymentPaymentMappingService;
    @Resource
    private PurchaseOrderService purchaseOrderService;
    @Resource
    private PaymentTermService paymentTermService;
    @Resource
    private ScanLocationService scanLocationService;

    @Resource
    private SapUtilService sapUtilService;

    @Resource
    private UserService userService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private RemindedService remindedService;
    @Resource
    private PurchaseCommonService purchaseCommonService;
    @Resource
    private InvoiceInspectionService invoiceInspectionService;
    @Resource
    private CheckAcceptanceDetailService checkAcceptanceDetailService;
    @Resource
    private CheckAcceptanceService checkAcceptanceService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private AttchConfigService attchConfigService;
    @Resource
    private InvoiceInspectionDetailService invoiceInspectionDetailService;
    @Resource
    private FileUploadService fileUploadServicel;

    /**
     * 列表页面
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @return
     */
    @RequestMapping("/searchPayment")
    public String searchPayment(Model model, HttpServletRequest req, Long offset, Long pageSize, PaymentOrderModel paymentOrderModel) {
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        //设置当前登录人编码
        paymentOrderModel.setLoginUserCode(req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        paymentOrderModel.setAdminCode(Env.getProperty("role.assetAdmin.id"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 付款单开始时间
            paymentOrderModel.setApplyDateStart(sdf.parse(req.getParameter("txtApplyDateStart")));
        } catch (Exception e) {
            paymentOrderModel.setApplyDateStart(null);
        }
        try {
            // 付款单结束时间
            paymentOrderModel.setApplyDateEnd(sdf.parse(req.getParameter("txtApplyDateEnd")));
        } catch (Exception e) {
            paymentOrderModel.setApplyDateEnd(null);
        }
        try {
            // 采购订单开始时间
            paymentOrderModel.setPurchaseApplyDateStart(sdf.parse(req.getParameter("txtPurchaseApplyDateStart")));
        } catch (Exception e) {
            paymentOrderModel.setPurchaseApplyDateStart(null);
        }
        try {
            // 采购订单结束时间
            paymentOrderModel.setPurchaseApplyDateEnd(sdf.parse(req.getParameter("txtPurchaseApplyDateEnd")));
        } catch (Exception e) {
            paymentOrderModel.setPurchaseApplyDateEnd(null);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUserCode);
        List<UserInfo> searchCauthorizerCode = userInfoService.searchCauthorizerCode(userInfo);

        Pager<PaymentOrderModel> pager = new Pager<PaymentOrderModel>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        paymentOrderModel.setDraft(null == paymentOrderModel.getDraft() ? "N" : paymentOrderModel.getDraft());
        paymentOrderModel.setPending(null == paymentOrderModel.getPending() ? "Y" : paymentOrderModel.getPending());

        Pager<PaymentOrderModel> paymentPager = paymentOrderService.searchPaymentOrder(pager, paymentOrderModel);

        for (PaymentOrderModel rgm : paymentPager.getRecords()) {
            if (loginUserCode.equals(rgm.getBuyerCode())) {
                rgm.setIsAuth("Y");
                continue;
            }
            for (UserInfo userInfo2 : searchCauthorizerCode) {
                if (rgm.getBuyerCode().equals(userInfo2.getCauthorizerCode())) {
                    rgm.setIsAuth("Y");
                }
            }
        }
        paymentPager.setUri(req.getRequestURI());
        model.addAttribute("paymentPager", paymentPager);
        model.addAttribute("loginUserCode", loginUserCode);
        return "purchase/payment_order_list";
    }

    /**
     * 选择页面
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @return
     */
    @RequestMapping("/checkPaymentOrder")
    public String checkPaymentOrder(Model model, HttpServletRequest req, Long offset, Long pageSize) {
        String strId = req.getParameter("id");
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCdocument(strId);
        List<PaymentTerm> paymentTerms = paymentTermService.searchPaymentTermByOrderId(purchaseOrder);

        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("paymentTerms", paymentTerms);
        return "purchase/payment_order_check";
    }

    @RequestMapping("/api/changePaymentType")
    @ResponseBody
    public List<PaymentOrderDetailModel> changePaymentType(@RequestBody PaymentTerm paymentTerm, HttpServletRequest req) {
        List<PaymentOrderDetailModel> result = new ArrayList<PaymentOrderDetailModel>();
        result = paymentOrderDetailService.searchPurchaseByOrderId(paymentTerm);
        return result;
    }

    /**
     * 添加页面
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @return
     */
    @RequestMapping("/addPaymentOrder")
    public String addPaymentOrder(Model model, HttpServletRequest req) {

        String purchaseId = req.getParameter("purchaseId");
        String nper = req.getParameter("nper");
        String ids = req.getParameter("ids");

        PurchaseOrder pOrder = new PurchaseOrder();
        pOrder.setCdocument(purchaseId);
        PurchaseOrder purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(pOrder);

        //获得所有付款条件
        List<PaymentTerm> allPaymentTerms = paymentTermService.searchPaymentTermByOrderId(pOrder);

        //获得本次付款比例 及 预付款付款比例
        PaymentTerm paymentTerm = new PaymentTerm();
        paymentTerm.setPurchaseId(purchaseId);
        paymentTerm.setNper(Integer.parseInt(nper));
        List<PaymentTerm> paymentTerms = paymentTermService.searchPaymentRatioByOrderId(paymentTerm);


        PaymentOrder paymentOrder = new PaymentOrder();
        // 生产付款单编号
        String str = "CGFK";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        str += sdf.format(date);
        int searchNextSequence = paymentOrderService.searchNextSequence();
        // 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
        str += String.format("%04d", searchNextSequence);
        paymentOrder.setCdocument(str);
        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        paymentOrder.setApplyUser(applyUsername);

        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        //根据申请人 查询 所属部门
        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);

        model.addAttribute("userDeptInfo", userDeptInfo);

        //查询此采购订单最大付款期数
        PaymentTerm paymentMaxTerm = paymentTermService.searchMaxTermByOrderId(pOrder);
        if (paymentMaxTerm.getNper() == Integer.parseInt(nper)) {
            paymentOrder.setIsLastTerm("Y");
        }

        //获得行明细
        PaymentOrderDetailModel paymentOrderDetailModel = new PaymentOrderDetailModel();
        paymentOrderDetailModel.setIds(ids);
        paymentOrderDetailModel.setPurchaseOrderId(purchaseId);
        paymentOrderDetailModel.setNper(Integer.parseInt(nper));
        List<PaymentOrderDetailModel> paymentOrderDetailModels = paymentOrderDetailService.searchPurchaseByOrderIdNper(paymentOrderDetailModel);


        //如果本次是验收款，则查询所有预付款明细    获得预付冲减明细
        if ("验收款".equals(paymentTerms.get(0).getPaymentType())) {
            PaymentOrderModel paymentOrderModel = new PaymentOrderModel();
            paymentOrderModel.setPurchaseOrderId(purchaseOrder.getCdocument());
            List<PaymentPaymentMapping> allPaymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingByOrderId(paymentOrderModel);
            model.addAttribute("allPaymentPaymentMappingModels", allPaymentPaymentMappingModels);
        }

        //获得扫描地
        List<ScanLocation> scanLocations = scanLocationService.findAll();

        model.addAttribute("paymentOrderDetailModels", paymentOrderDetailModels);
        model.addAttribute("paymentOrder", paymentOrder);

        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("paymentTerms", paymentTerms);
        model.addAttribute("allPaymentTerms", allPaymentTerms);
        model.addAttribute("scanLocations", scanLocations);

        return "purchase/payment_order_add";
    }

    @RequestMapping("/api/savePayment")
    @ResponseBody
    public ExecuteResult<PaymentOrder> savePayment(@RequestBody PaymentOrderWrapper paymentOrderWrapper, HttpServletRequest req) {
        ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();
        PaymentOrder paymentOrder = paymentOrderWrapper.getPaymentOrder();
        List<PaymentOrderDetail> paymentOrderDetails = paymentOrderWrapper.getPaymentOrderDetails();
        List<PaymentPaymentMappingModel> paymentPaymentMappingModels = paymentOrderWrapper.getPaymentPaymentMappingModels();

        // 验证明细行必须大于0
        if (paymentOrderDetails.size() == 0) {
            result.addErrorMessage("明细不能少于1行！");
            return result;
        }

        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        //订单表头
        if ("N".equals(paymentOrder.getDraft())) {
            paymentOrder.setCapprovalstate("等待审批");
        }
        paymentOrder.setOrderStatus("未关闭");
        paymentOrder.setPrint("N");
        paymentOrder.setStatus("Y");
        paymentOrder.setApplyUser(applyUsername);
        paymentOrder.setCreateBy(applyUsername);
        paymentOrder.setUpdateBy(applyUsername);
        paymentOrder.setApplyUserCode(applyUsernameCode);
        paymentOrder.setCreateByCode(applyUsernameCode);
        paymentOrder.setUpdateByCode(applyUsernameCode);
        paymentOrder.setApplyDate(new Date());
        paymentOrder.setCreateDate(new Date());
        paymentOrder.setUpdateDate(new Date());

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCdocument(paymentOrder.getPurchaseOrderId());
        PurchaseOrder searchPurchaseOrderByOrderId = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);
        String profitCenter = paymentOrderService.searchProfitCenter(searchPurchaseOrderByOrderId.getCostCenterCode());
        paymentOrder.setCostCenter(searchPurchaseOrderByOrderId.getCostCenter());
        paymentOrder.setCostCenterCode(searchPurchaseOrderByOrderId.getCostCenterCode());
        paymentOrder.setProfitCenter(profitCenter);

        int i = 1;
        //订单表体明细
        for (PaymentOrderDetail pod : paymentOrderDetails) {
            pod.setNper(paymentOrder.getNper());
            pod.setPaymentType(paymentOrder.getPaymentType());
            pod.setPaymentOrderId(paymentOrder.getCdocument());
            pod.setPurchaseOrderId(paymentOrder.getPurchaseOrderId());
            pod.setCreateBy(applyUsername);
            pod.setUpdateBy(applyUsername);
            pod.setCreateByCode(applyUsernameCode);
            pod.setUpdateByCode(applyUsernameCode);
            pod.setCreateDate(new Date());
            pod.setUpdateDate(new Date());
            //获得总账科目
            String ledgerAccount = paymentOrderDetailService.searchLedgerAccountByPurchaseDetailId(pod.getPurchaseDetailId());
            pod.setLedgerAccount(ledgerAccount);
            //设置行项目编码
            pod.setItemnoAcc(i * 10L);
            i++;
        }

        //如果本次是验收款，则查询所有预付款明细    获得预付冲减明细
        if ("验收款".equals(paymentOrder.getPaymentType())) {
/*			if (paymentPaymentMappingModels.size() == 0) {
                result.addErrorMessage("预付冲减明细不能少于1行！");
				return result;
			}*/
            for (PaymentPaymentMappingModel ppm : paymentPaymentMappingModels) {
                if (ppm.getPayPrice().compareTo(new BigDecimal(ppm.getIamount())) == 1) {
                    result.addErrorMessage("预付冲减金额超出！[" + ppm.getPayPrice() + "]");
                    return result;
                }
            }
        }
        AttchConfig attchConfig = attchConfigService.searchPurchaseManager(applyUsernameCode);
        paymentOrder.setPurchaseManagerCode(attchConfig.getPurchasingManagerCode());
        paymentOrder.setPurchaseManagerName(attchConfig.getPurchasingManagerName());
        ExecuteResult<PaymentOrder> extRst = paymentOrderService.savePayment(paymentOrder);
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();

        if (!extRst.isSuccess()) {
            return extRst;
        }
        result = paymentOrderService.addSavePaymentOrder(paymentOrder, paymentOrderDetails, paymentPaymentMappingModels);
        //如果保存失败  删除表头信息 并且删除审批流
        if (!result.isSuccess()) {
            //删除审批流
            workFlowService.deleteGarbegeWorkFlowData(paymentOrder.getCdocument(), Env.getProperty("payment.info.type.id"));
            paymentOrderService.delPaymentOrderByCdocument(paymentOrder);
            result.addErrorMessage("添加失败");
            return result;
        }
        //查询此采购订单最大付款期数
        PaymentTerm paymentMaxTerm = paymentTermService.searchMaxTermByOrderId(purchaseOrder);
        //判断如果最后一期所有明细都选择最后一次付款则系统自动关闭订单
        if (paymentMaxTerm.getNper() == paymentOrder.getNper() && "N".equals(paymentOrder.getDraft())) {
            if (paymentOrderDetailService.completePayment(paymentOrder) == 0) {
                purchaseOrder.setIsCloseForPayment("已关闭");
                purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
            }
        }
        result.setSuccessMessage("添加成功");
        PaymentOrder searchPaymentOrderByOrderId = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
        if (WARNSTATE.YES.getWarnstate().equals(paymentOrder.getIsMail())) {
            searchPaymentOrderByOrderId.setIsMail("Y");
        } else {
            searchPaymentOrderByOrderId.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(paymentOrder.getIsPhoneMessage())) {
            searchPaymentOrderByOrderId.setIsPhoneMessage("Y");
        } else {
            searchPaymentOrderByOrderId.setIsPhoneMessage("N");
        }
        if (StringUtils.isNotBlank(searchPaymentOrderByOrderId.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPaymentOrderByOrderId.getCnexthandlercode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            if (!er.isSuccess()) {
                result.addErrorMessage("添加成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        if (WARNSTATE.YES.getWarnstate().equals(paymentOrder.getIsPortrait())) {
            executeResult = paymentOrderService.saveBar(paymentOrder);
            if (!executeResult.isSuccess() && executeResult.getErrorMessages() != null) {
                result.setSuccessMessage("添加成功," + executeResult.getErrorMessages().get(0));
            } else {
                result.setSuccessMessage("添加成功," + executeResult.getSuccessMessage());
                paymentOrderService.updatePaymentOrderByCdocument(paymentOrder);
            }
        }
        return result;
    }

    @RequestMapping("/api/updatePayment")
    @ResponseBody
    public ExecuteResult<PaymentOrder> updatePayment(@RequestBody PaymentOrderWrapper paymentOrderWrapper, HttpServletRequest req) {
        ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();
        PaymentOrder paymentOrder = paymentOrderWrapper.getPaymentOrder();
        List<PaymentOrderDetail> paymentOrderDetails = paymentOrderWrapper.getPaymentOrderDetails();
        List<PaymentPaymentMappingModel> paymentPaymentMappingModels = paymentOrderWrapper.getPaymentPaymentMappingModels();

        // 验证明细行必须大于0
        if (paymentOrderDetails.size() == 0) {
            result.addErrorMessage("明细不能少于1行！");
            return result;
        }

        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        //订单表头
        if ("N".equals(paymentOrder.getDraft())) {
            paymentOrder.setCapprovalstate("等待审批");
        }
        paymentOrder.setOrderStatus("未关闭");
        paymentOrder.setPrint("N");
        paymentOrder.setStatus("Y");
        paymentOrder.setApplyUser(applyUsername);
        paymentOrder.setCreateBy(applyUsername);
        paymentOrder.setUpdateBy(applyUsername);
        paymentOrder.setApplyUserCode(applyUsernameCode);
        paymentOrder.setCreateByCode(applyUsernameCode);
        paymentOrder.setUpdateByCode(applyUsernameCode);
        paymentOrder.setApplyDate(new Date());
        paymentOrder.setCreateDate(new Date());
        paymentOrder.setUpdateDate(new Date());

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCdocument(paymentOrder.getPurchaseOrderId());
        PurchaseOrder searchPurchaseOrderByOrderId = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);
        String profitCenter = paymentOrderService.searchProfitCenter(searchPurchaseOrderByOrderId.getCostCenterCode());
        paymentOrder.setCostCenter(searchPurchaseOrderByOrderId.getCostCenter());
        paymentOrder.setCostCenterCode(searchPurchaseOrderByOrderId.getCostCenterCode());
        paymentOrder.setProfitCenter(profitCenter);
        paymentOrder.setIsComingSAP("N");

        int i = 1;
        //订单表体明细
        for (PaymentOrderDetail pod : paymentOrderDetails) {
            pod.setNper(paymentOrder.getNper());
            pod.setPaymentType(paymentOrder.getPaymentType());
            pod.setPaymentOrderId(paymentOrder.getCdocument());
            pod.setPurchaseOrderId(paymentOrder.getPurchaseOrderId());
            pod.setCreateBy(applyUsername);
            pod.setUpdateBy(applyUsername);
            pod.setCreateByCode(applyUsernameCode);
            pod.setUpdateByCode(applyUsernameCode);
            pod.setCreateDate(new Date());
            pod.setUpdateDate(new Date());
            //获得总账科目
            String ledgerAccount = paymentOrderDetailService.searchLedgerAccountByPurchaseDetailId(pod.getPurchaseDetailId());
            pod.setLedgerAccount(ledgerAccount);
            //设置行项目编码
            pod.setItemnoAcc(i * 10L);
            i++;
        }

        //如果本次是验收款，则查询所有预付款明细    获得预付冲减明细
        if ("验收款".equals(paymentOrder.getPaymentType())) {
        /*	if (paymentPaymentMappingModels.size() == 0) {
                result.addErrorMessage("预付冲减明细不能少于1行！");
				return result;
			}*/
            for (PaymentPaymentMappingModel ppm : paymentPaymentMappingModels) {
                if (ppm.getPayPrice().compareTo(new BigDecimal(ppm.getIamount())) == 1) {
                    result.addErrorMessage("预付冲减金额超出！[" + ppm.getPayPrice() + "]");
                    return result;
                }
            }
        }
        ExecuteResult<PaymentOrder> extRst = paymentOrderService.updatePaymentOrder(paymentOrder);

        if (!extRst.isSuccess()) {
            return extRst;
        }


        result = paymentOrderService.updateSavePaymentOrder(paymentOrder, paymentOrderDetails, paymentPaymentMappingModels);
        //如果保存失败  删除表头信息 并且删除审批流
        if (!result.isSuccess()) {
            //删除审批流
            workFlowService.deleteGarbegeWorkFlowData(paymentOrder.getCdocument(), Env.getProperty("payment.info.type.id"));
        }
        PaymentOrder searchPaymentOrderByOrderId = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
        if (WARNSTATE.YES.getWarnstate().equals(paymentOrder.getIsMail())) {
            searchPaymentOrderByOrderId.setIsMail("Y");
        } else {
            searchPaymentOrderByOrderId.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(paymentOrder.getIsPhoneMessage())) {
            searchPaymentOrderByOrderId.setIsPhoneMessage("Y");
        } else {
            searchPaymentOrderByOrderId.setIsPhoneMessage("N");
        }
        if (StringUtils.isNotBlank(searchPaymentOrderByOrderId.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPaymentOrderByOrderId.getCnexthandlercode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            if (!er.isSuccess()) {
                result.addErrorMessage("修改成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        //查询此采购订单最大付款期数
        PaymentTerm paymentMaxTerm = paymentTermService.searchMaxTermByOrderId(purchaseOrder);
        //判断如果最后一期所有明细都选择最后一次付款则系统自动关闭订单
        if (paymentMaxTerm.getNper() == paymentOrder.getNper() && "N".equals(paymentOrder.getDraft())) {
            if (paymentOrderDetailService.completePayment(paymentOrder) == 0) {
                purchaseOrder.setIsCloseForPayment("已关闭");
                purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
            } else {
                purchaseOrder.setIsCloseForPayment("未关闭");
                purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
            }
        }
        paymentOrderService.saveBar(paymentOrder);
        return result;
    }

    @RequestMapping("/showPaymentOrder")
    public String showPaymentOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {

        String cdocument = req.getParameter("id");
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setCdocument(cdocument);
        paymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);

        List<PaymentOrderDetailModel> paymentOrderDetailModels = paymentOrderDetailService.searchPaymentDetailByOrderId(paymentOrder);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCdocument(paymentOrder.getPurchaseOrderId());
        purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);
        /*String ids="";
        for (PaymentOrderDetailModel podm : podms) {
			ids += podm.getPurchaseDetailId()+",";
		}
		ids = ids.substring(0,ids.length()-1);
		
		PaymentOrderDetailModel paymentOrderDetailModel = new PaymentOrderDetailModel();
		paymentOrderDetailModel.setIds(ids);
		paymentOrderDetailModel.setPurchaseOrderId(paymentOrder.getPurchaseOrderId());
		paymentOrderDetailModel.setNper(paymentOrder.getNper());
		List<PaymentOrderDetailModel> paymentOrderDetailModels = paymentOrderDetailService.searchPurchaseByOrderIdNper(paymentOrderDetailModel);*/

        List<PaymentPaymentMapping> paymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingById(paymentOrder);
        /**
         * 获取此付款单发票检验明细信息
         */
        InvoiceInspection invoiceInspection = new InvoiceInspection();
        invoiceInspection.setPaymentOrderDocument(cdocument);
        invoiceInspection = invoiceInspectionService.getInvoiceInspection(invoiceInspection);
        List<InvoiceInspectionDetail> invoiceInspectionDetails = invoiceInspectionDetailService.searchInvoiceInspectionDetail(invoiceInspection);
        /**
         * 获取审批流流程的历史记录
         */
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(cdocument, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());
        //获取上传的文件列表
        List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
        paymentOrder.setListFile(listFile);
        model.addAttribute("appHistoryPager", appHistoryPager);

        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("paymentOrder", paymentOrder);
        model.addAttribute("paymentOrderDetailModels", paymentOrderDetailModels);
        model.addAttribute("paymentPaymentMappingModels", paymentPaymentMappingModels);
        model.addAttribute("invoiceInspection", invoiceInspection);
        model.addAttribute("invoiceInspectionDetails", invoiceInspectionDetails);
        return "purchase/payment_order_detail";
    }

    @RequestMapping("/examinePaymentOrder")
    public String examinePaymentOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {


        String cdocument = req.getParameter("id");
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setCdocument(cdocument);
        paymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);

        List<PaymentOrderDetailModel> paymentOrderDetailModels = paymentOrderDetailService.searchPaymentDetailByOrderId(paymentOrder);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        List<PurchaseCommon> purchaseCommons = purchaseCommonService.getPurchaseCommonbyPurchaseId(paymentOrder);
        purchaseOrder.setCdocument(paymentOrder.getPurchaseOrderId());
        purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);
        /*String ids="";
        for (PaymentOrderDetailModel podm : podms) {
			ids += podm.getPurchaseDetailId()+",";
		}
		ids = ids.substring(0,ids.length()-1);
		
		PaymentOrderDetailModel paymentOrderDetailModel = new PaymentOrderDetailModel();
		paymentOrderDetailModel.setIds(ids);
		paymentOrderDetailModel.setPurchaseOrderId(paymentOrder.getPurchaseOrderId());
		paymentOrderDetailModel.setNper(paymentOrder.getNper());
		List<PaymentOrderDetailModel> paymentOrderDetailModels = paymentOrderDetailService.searchPurchaseByOrderIdNper(paymentOrderDetailModel);*/

        List<PaymentPaymentMapping> paymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingById(paymentOrder);
        InvoiceInspection invoiceInspection = new InvoiceInspection();
        //获取上传的文件列表
        List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
        paymentOrder.setListFile(listFile);
        invoiceInspection.setPaymentOrderDocument(cdocument);
        invoiceInspection = invoiceInspectionService.getInvoiceInspection(invoiceInspection);
        List<InvoiceInspectionDetail> invoiceInspectionDetails = invoiceInspectionDetailService.searchInvoiceInspectionDetail(invoiceInspection);
        /**
         * 获取审批流流程的历史记录
         */
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(cdocument, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());
        String mainAssetCode = "";
        for (PaymentOrderDetailModel paymentOrderDetailModel : paymentOrderDetailModels) {
            for (int i = 0; i < paymentOrderDetailModel.getPayCount(); i++) {
                mainAssetCode = mainAssetCode + StringUtils.leftPad(paymentOrderDetailModel.getMainAssetCode(), 12, "0") + ",";
            }
        }
        if (mainAssetCode.length() > 1) {
            mainAssetCode = mainAssetCode.substring(0, mainAssetCode.length() - 1);
        }
        model.addAttribute("mainAssetCode", mainAssetCode);
        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("purchaseCommons", purchaseCommons);
        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("paymentOrder", paymentOrder);
        model.addAttribute("paymentOrderDetailModels", paymentOrderDetailModels);
        model.addAttribute("paymentPaymentMappingModels", paymentPaymentMappingModels);
        model.addAttribute("invoiceInspection", invoiceInspection);
        model.addAttribute("invoiceInspectionDetails", invoiceInspectionDetails);

        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
/*		if(userDeptInfo==null){
            executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
		}*/
        paymentOrder.setUserDepCode(userDeptInfo.getDepartmentCode());
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.GetIsLastApprovalStep getIsLastApprovalStep = new WorkFlowStub.GetIsLastApprovalStep();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();
            agreeFlowParam.setBillCode(paymentOrder.getCdocument());
            agreeFlowParam.setBillTableName(Env.getProperty("payment.info.table"));
            agreeFlowParam.setBillCodeColumnName("CDOCUMENT");
            agreeFlowParam.setCUserCode(applyUsernameCode);
            agreeFlowParam.setCUserName(applyUsername);
            agreeFlowParam.setAMoney(paymentOrder.getOrderMoney().toString());
            agreeFlowParam.setTypeCode(Env.getProperty("payment.info.type.id"));
            agreeFlowParam.setIdea(paymentOrder.getIdea());
            agreeFlowParam.setUserDepCode(paymentOrder.getUserDepCode());
            agreeFlowParam.setCreateDate(Calendar.getInstance());
            getIsLastApprovalStep.setParam(agreeFlowParam);
            WorkFlowStub.GetIsLastApprovalStepResponse response = stub.getIsLastApprovalStep(getIsLastApprovalStep);
            if (response.getGetIsLastApprovalStepResult()) {
                return "purchase/payment_order_examine_update";
            }
        } catch (AxisFault axisFault) {
            ifRollback = true;
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            LOGGER.error("Exception", e);
        }

        return "purchase/payment_order_examine";
    }

    @RequestMapping("/api/agreePayment")
    @ResponseBody
    public ExecuteResult<PaymentOrder> agreePayment(@RequestBody PaymentOrder paymentOrder, HttpServletRequest request) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        /*if (StringUtils.isBlank(paymentOrder.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }*/
        //修改前的订单
        PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);

        searchPaymentOrder.setCdocument(paymentOrder.getCdocument());
        searchPaymentOrder.setIdea(paymentOrder.getIdea());
        searchPaymentOrder.setIsMail(paymentOrder.getIsMail());
        searchPaymentOrder.setIsPhoneMessage(paymentOrder.getIsPhoneMessage());

        // 获得当前登录人  部门 
        String applyUsername = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        searchPaymentOrder.setLoginUserName(applyUsername);
        String applyUsernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        searchPaymentOrder.setLoginUserCode(applyUsernameCode);

        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        if (userDeptInfo == null) {
            executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
        }
        searchPaymentOrder.setUserDepCode(userDeptInfo.getDepartmentCode());

        executeResult = paymentOrderService.agreeWorkFlow(searchPaymentOrder);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        PaymentOrder searchPaymentOrderByOrderId = paymentOrderService.searchPaymentOrderByOrderId(searchPaymentOrder);
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrder.getIsMail())) {
            searchPaymentOrderByOrderId.setIsMail("Y");
        } else {
            searchPaymentOrderByOrderId.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrder.getIsPhoneMessage())) {
            searchPaymentOrderByOrderId.setIsPhoneMessage("Y");
        } else {
            searchPaymentOrderByOrderId.setIsPhoneMessage("N");
        }
        if (StringUtils.isNotBlank(searchPaymentOrderByOrderId.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPaymentOrderByOrderId.getCnexthandlercode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        }
/*        if ("OP006614".equals(applyUsernameCode)) {
            ExecuteResult<PaymentOrder> executResult = paymentOrderService.updatePaymentOrder(paymentOrder);
        	if (!executResult.isSuccess()) {
    			return executResult;
    		}
        }*/
        //同意审批流绑定成功之后获得的订单 
        PaymentOrderModel paymentOrderSAP = paymentOrderService.searchPaymentOrderByOrderId(searchPaymentOrder);
        List<PaymentOrderDetailModel> paymentOrderDetailsSAP = paymentOrderDetailService.searchPaymentDetailByOrderId(searchPaymentOrder);

        //如果订单审批状态是"已完成"，则传sap付款凭证
        //if (APPROVESTATE.DONE.getApproveState().trim().equals(paymentOrderSAP.getCapprovalstate())) {
        //传sap付款凭证(预付、验收、尾款)
/*			executeResult = sapOrder(paymentOrderSAP,paymentOrderDetailsSAP);*/
        //}
        return executeResult;
    }

    /**
     * 生成预付款凭证
     *
     * @param paymentOrder
     * @param request
     * @return executeResult
     */
    @RequestMapping("/api/createPrePayment")
    @ResponseBody
    public ExecuteResult<PaymentOrder> createPrepayment(@RequestBody PaymentOrder paymentOrder, HttpServletRequest request) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        String applyUsernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
        PaymentOrderModel paymentOrderSAP = paymentOrderService.searchPaymentOrderByOrderId(searchPaymentOrder);
        List<PaymentOrderDetailModel> paymentOrderDetailsSAP = paymentOrderDetailService.searchPaymentDetailByOrderId(searchPaymentOrder);
        paymentOrderSAP.setDocType("SA");
        UserInfo userInfo = new UserInfo();

        userInfo.setCemployeecode(searchPaymentOrder.getApplyUserCode());


        UserInfo searchSupplierCode = userInfoService.searchSupplierCode(userInfo);
        if (searchSupplierCode == null) {
            executeResult.addErrorMessage("没有找到[" + searchPaymentOrder.getApplyUserCode() + "]对应得供应商信息！");
            return executeResult;
        }

        paymentOrderSAP.setAllocNumber(searchSupplierCode.getCsupplierCode());


        paymentOrderSAP.setLoginUserCode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim());

        JSONObject prePaymentVoucherFromSAP = sapUtilService.createPrePaymentVoucherFromSAP(paymentOrderSAP, paymentOrderDetailsSAP);
        if (!prePaymentVoucherFromSAP.has("prePaySapNo")) {
            String error = prePaymentVoucherFromSAP.getString("error");
            executeResult.addErrorMessage(error);
            return executeResult;
        }
        String prepaymentDocumentNumber = prePaymentVoucherFromSAP.getString("prePaySapNo");
        if ("" != prepaymentDocumentNumber) {
            PaymentOrder paymentOrderBack = new PaymentOrder();
            paymentOrderBack.setPrepaymentDocumentNumber(prepaymentDocumentNumber);
            searchPaymentOrder.setPrepaymentDocumentNumber(prepaymentDocumentNumber);
            searchPaymentOrder.setIsManualPrepaymentDocument("N");
            searchPaymentOrder.setIsComingSAP("Y");
            paymentOrderService.updatePaymentOrder(searchPaymentOrder);
            paymentOrderDetailService.updatePaymentOrderDetail(searchPaymentOrder);
            executeResult.setResult(paymentOrderBack);
        } else {
            executeResult.addErrorMessage(prePaymentVoucherFromSAP.getString("error"));
        }
        return executeResult;
    }

    /**
     * 发票检验
     *
     * @param paymentOrder
     * @param request
     * @return executeResult
     */
    @RequestMapping("/api/createInvoiceInspection")
    @ResponseBody
    public ExecuteResult<PaymentOrder> createInvoiceInspection(@RequestBody InvoiceInspectionModel invoiceInspectionModel, HttpServletRequest request) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        String loginUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(invoiceInspectionModel.getPaymentOrder());
        PaymentOrderModel paymentOrderSAP = paymentOrderService.searchPaymentOrderByOrderId(searchPaymentOrder);
        List<PaymentOrderDetailModel> paymentOrderDetailsSAP = paymentOrderDetailService.searchPaymentDetailByOrderId(searchPaymentOrder);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCdocument(searchPaymentOrder.getPurchaseOrderId());
        purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);

        List<ItDataModel> itDataModels = new ArrayList<ItDataModel>();
        Eaddressdata eaddressdata = new Eaddressdata();
        eaddressdata.setAcc1time(purchaseOrder.getSupplierCode());
        eaddressdata.setCity(purchaseOrder.getCity());
        eaddressdata.setName(purchaseOrder.getSupplierName());
        eaddressdata.setLangu("CH");
        eaddressdata.setCountry(purchaseOrder.getCountry());
        String[] details = invoiceInspectionModel.getCheckids().split(",");
        for (int i = 0; i < details.length; i++) {
            ItDataModel itDataModel = new ItDataModel();
            PurchaseCommon purchaseCommon = purchaseCommonService.getPurchaseCommonById(Long.parseLong(details[i]));
            CheckAcceptanceDetail checkAcceptanceDetail = checkAcceptanceDetailService.getCheckAcceptanceDetailById(Long.parseLong(purchaseCommon.getCheckAcceptDetailId()));
            itDataModel.setEbeln(purchaseOrder.getPurchaseOrderSapId());
            itDataModel.setEbelp(StringUtils.leftPad(String.valueOf(purchaseCommon.getItemNo()), 5, "0"));
            itDataModel.setMjahr(checkAcceptanceDetail.getEpMatdocumentyear());
            itDataModel.setMblnr(checkAcceptanceDetail.getEpMaterialdocu());
            itDataModel.setNetwj(purchaseCommon.getOrderNetMoney());
            itDataModel.setNetwr(purchaseCommon.getOrderMoney());
            itDataModel.setNetwz(purchaseCommon.getOrderMoney().subtract(purchaseCommon.getOrderNetMoney()));
            itDataModel.setMwskz(searchPaymentOrder.getTaxRateCode());
            itDataModel.setAnln1(purchaseCommon.getSapMainAssetCode());
            itDataModel.setAnln2(purchaseCommon.getSapSecAssetCode());
            itDataModel.setCurrency(purchaseOrder.getCurrencyCode());
/*            itDataModel.setBktxt(loginUserCode+"-"+searchPaymentOrder.getApplyUser()+"-"+searchPaymentOrder.getApplyFor());
            itDataModel.setXblnr(searchPaymentOrder.getCdocument());
            itDataModel.setSgtxt(searchPaymentOrder.getInvoiceNumber()+searchPaymentOrder.getApplyFor()+purchaseOrder.getPurchaseOrderSapId()+"-固");*/
/*			itDataModel.setNetwj(invoiceInspection.get);*/
            itDataModels.add(itDataModel);
        }
        JSONObject billsCheck = sapUtilService.createBillsCheck(itDataModels, eaddressdata);
        String paymentVoucherNumber = billsCheck.getString("eBelnr");
        String invoiceNo = billsCheck.getString("ecBelnr");
        String result = billsCheck.getString("result");

        if ("" != paymentVoucherNumber && "S".equals(result)) {
            //保存发票检验信息
            invoiceInspectionService.saveInvoiceInspection(invoiceInspectionModel.getInvoiceInspection(), invoiceInspectionModel.getCheckids());
            PaymentOrder paymentOrderBack = new PaymentOrder();
            paymentOrderBack.setPaymentVoucherNumber(paymentVoucherNumber);
            paymentOrderBack.setInvoiceNo(invoiceNo);
            searchPaymentOrder.setPaymentVoucherNumber(paymentVoucherNumber);
            searchPaymentOrder.setInvoiceNo(invoiceNo);
            searchPaymentOrder.setIsManualPrepaymentDocument("N");
/*			searchPaymentOrder.setIsComingSAP("Y");*/
            String[] splitsublist = invoiceInspectionModel.getCheckids().split(",");
            paymentOrderService.updatePaymentOrder(searchPaymentOrder);
            for (int i = 0; i < splitsublist.length; i++) {
                Long commonId = Long.parseLong(splitsublist[i]);
                PurchaseCommon purchaseCommon = new PurchaseCommon();
                purchaseCommon.setId(commonId);
                purchaseCommonService.updatePurchaseCommon(purchaseCommon);
            }
            executeResult.setResult(paymentOrderBack);
        } else {
            executeResult.addErrorMessage("发票检验失败！");
        }
        return executeResult;
    }

    /**
     * 生成验收款凭证
     *
     * @param paymentOrder
     * @param request
     * @return executeResult
     */
    @RequestMapping("/api/createReceiptVoucher")
    @ResponseBody
    public ExecuteResult<PaymentOrder> createReceiptVoucher(@RequestBody PaymentOrder paymentOrder, HttpServletRequest request) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
        String username = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String usernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        boolean ifRollback = false;
        try {
            VoucherServiceStub stub = new VoucherServiceStub();
            VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_Pay pay = new VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_Pay();
            pay.setBillcode(paymentOrder.getCdocument());
            pay.setCusercode(usernameCode);
            pay.setCusername(username);

            VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_PayResponse payResponse = stub.linkSAP_BAPI_ToVouchInfo_Pay(pay);
            if (StringUtils.isNotBlank(payResponse.getLinkSAP_BAPI_ToVouchInfo_PayResult())) {
                executeResult.setSuccessMessage(payResponse.getLinkSAP_BAPI_ToVouchInfo_PayResult());
            }
        } catch (AxisFault axisFault) {
            ifRollback = true;
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            LOGGER.error("Exception", e);
        }
        if (ifRollback) {
            executeResult.addErrorMessage("生成验收款凭证失败");
            return executeResult;
        }
        searchPaymentOrder.setIsManualReceiptVoucher("N");
        searchPaymentOrder.setIsComingSAP("Y");
        paymentOrderService.updatePaymentOrder(searchPaymentOrder);
        PaymentOrder backPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
        executeResult.setResult(backPaymentOrder);
        return executeResult;
    }

    /**
     * 再次清账
     *
     * @param id
     * @param request
     * @return executeResult
     */
    @RequestMapping("/api/clearVouch")
    @ResponseBody
    public ExecuteResult<PaymentOrder> clearVouch(String id, HttpServletRequest request) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setCdocument(id);
        PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
        String username = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String usernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        boolean ifRollback = false;
        try {
            VoucherServiceStub stub = new VoucherServiceStub();
            VoucherServiceStub.ClearVouch_New clearVouch = new VoucherServiceStub.ClearVouch_New();

            clearVouch.setVouchcode(searchPaymentOrder.getVoucherId());
            clearVouch.setBillcode(paymentOrder.getCdocument());
            clearVouch.setCusercode(usernameCode);
            clearVouch.setCusername(username);

            VoucherServiceStub.ClearVouch_NewResponse clearVouchResponse = stub.clearVouch_New(clearVouch);
            if (StringUtils.isNotBlank(clearVouchResponse.getClearVouch_NewResult())) {
                executeResult.setSuccessMessage(clearVouchResponse.getClearVouch_NewResult());
            }
        } catch (AxisFault axisFault) {
            ifRollback = true;
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            LOGGER.error("Exception", e);
        }
        if (ifRollback) {
            executeResult.addErrorMessage("清账失败");
            return executeResult;
        }
        return executeResult;
    }

    /*	//传sap付款凭证(预付、验收、尾款)
        private ExecuteResult<PaymentOrder> sapOrder(PaymentOrderModel paymentOrderSAP, List<PaymentOrderDetailModel> paymentOrderDetailsSAP){
            ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();

            //预付款
            JSONObject prePaymentVoucherFromSAP = null;
            if(paymentOrderSAP.getPaymentType().equals(PAYMENTTYPE.ADVANCE.getPaymentType().trim())){
                paymentOrderSAP.setDocType("SA");
                prePaymentVoucherFromSAP = sapUtilService.createPrePaymentVoucherFromSAP(paymentOrderSAP, paymentOrderDetailsSAP);
            }
            if(paymentOrderSAP.getPaymentType().equals(PAYMENTTYPE.CHECK.getPaymentType().trim())){
                paymentOrderSAP.setDocType("RE");
                prePaymentVoucherFromSAP = sapUtilService.createPrePaymentVoucherFromSAP(paymentOrderSAP, paymentOrderDetailsSAP);
            }
            if (null == prePaymentVoucherFromSAP) {
                executeResult.addErrorMessage("sap付款凭证失败");
                return executeResult;
            }

            System.out.println("采购订单生成 接口 测试结束--返回结果!!!-----:"+prePaymentVoucherFromSAP);
            return executeResult;
        }*/
    @RequestMapping("/api/denyPayment")
    @ResponseBody
    public ExecuteResult<PaymentOrder> denyPayment(@RequestBody PaymentOrderModel paymentOrder, HttpServletRequest request) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        
        /*if (StringUtils.isBlank(paymentOrder.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }*/

        //修改前的订单
        PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);

        if (APPROVESTATE.WAITING.getApproveState().equals(searchPaymentOrder.getCapprovalstate())) {
            executeResult.addErrorMessage(searchPaymentOrder.getCapprovalstate() + "状态的单据不允许退回");
            return executeResult;
        }
        if (APPROVESTATE.DONE.getApproveState().equals(searchPaymentOrder.getCapprovalstate())) {
            executeResult.addErrorMessage("已完成的单据不可以退回");
            return executeResult;
        }

        searchPaymentOrder.setIdea(paymentOrder.getIdea());
        searchPaymentOrder.setIsMail(paymentOrder.getIsMail());
        searchPaymentOrder.setIsPhoneMessage(paymentOrder.getIsPhoneMessage());
        searchPaymentOrder.setActivityId(paymentOrder.getActivityId());
        // 获得当前登录人  部门 
        String applyUsername = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        searchPaymentOrder.setLoginUserName(applyUsername);
        String applyUsernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        searchPaymentOrder.setLoginUserCode(applyUsernameCode);

        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        if (userDeptInfo == null) {
            executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
        }
        searchPaymentOrder.setUserDepCode(userDeptInfo.getDepartmentCode());

        executeResult = paymentOrderService.denyWorkFlow(searchPaymentOrder);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        PaymentOrder searchPaymentOrderByOrderId = paymentOrderService.searchPaymentOrderByOrderId(searchPaymentOrder);
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrder.getIsMail())) {
            searchPaymentOrderByOrderId.setIsMail("Y");
        } else {
            searchPaymentOrderByOrderId.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrder.getIsPhoneMessage())) {
            searchPaymentOrderByOrderId.setIsPhoneMessage("Y");
        } else {
            searchPaymentOrderByOrderId.setIsPhoneMessage("N");
        }
        if (StringUtils.isNotBlank(searchPaymentOrderByOrderId.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPaymentOrderByOrderId.getCnexthandlercode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            if (!er.isSuccess()) {
                executeResult.setSuccessMessage("审批成功，但是邮件或短信发送失败");
            }
        }
        ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();
        if (WARNSTATE.YES.getWarnstate().equals(paymentOrder.getIsPortrait())) {
            result = paymentOrderService.backBar(searchPaymentOrder);
            if (!result.isSuccess() && result.getErrorMessages() != null) {
                executeResult.setSuccessMessage(executeResult.getSuccessMessage() + "," + result.getErrorMessages().get(0));
            } else {
                executeResult.setSuccessMessage(executeResult.getSuccessMessage() + "," + result.getSuccessMessage());
                paymentOrderService.updatePaymentOrderByCdocument(paymentOrder);
            }
        }
        return executeResult;
    }

    @RequestMapping("/api/dropPayment")
    @ResponseBody
    public ExecuteResult<PaymentOrder> dropPayment(@RequestBody PaymentOrderModel paymentOrder, HttpServletRequest request) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        
        /*if (StringUtils.isBlank(paymentOrder.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }*/
        PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);

        searchPaymentOrder.setIdea(paymentOrder.getIdea());
        searchPaymentOrder.setIsMail(paymentOrder.getIsMail());
        searchPaymentOrder.setIsPhoneMessage(paymentOrder.getIsPhoneMessage());

        // 获得当前登录人  部门 
        String applyUsername = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        searchPaymentOrder.setLoginUserName(applyUsername);
        String applyUsernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        searchPaymentOrder.setLoginUserCode(applyUsernameCode);

        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        if (userDeptInfo == null) {
            executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
        }
        searchPaymentOrder.setUserDepCode(userDeptInfo.getDepartmentCode());

        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        PaymentOrder searchPaymentOrderByOrderId = paymentOrderService.searchPaymentOrderByOrderId(searchPaymentOrder);
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrder.getIsMail())) {
            searchPaymentOrderByOrderId.setIsMail("Y");
        } else {
            searchPaymentOrderByOrderId.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrder.getIsPhoneMessage())) {
            searchPaymentOrderByOrderId.setIsPhoneMessage("Y");
        } else {
            searchPaymentOrderByOrderId.setIsPhoneMessage("N");
        }
        if (StringUtils.isNotBlank(searchPaymentOrderByOrderId.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPaymentOrderByOrderId.getCnexthandlercode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        }

        return executeResult;
    }

    @RequestMapping("/updatePaymentOrder")
    public String updatePaymentOrder(Model model, HttpServletRequest req) {

        String cdocument = req.getParameter("id");
        String ids = "";
        int nper = 0;
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setCdocument(cdocument);
        paymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
        //查询新增所选的明细
        List<PaymentOrderDetailModel> paymentOrderDetailModels = paymentOrderDetailService.searchPaymentDetailByOrderId(paymentOrder);
        for (PaymentOrderDetailModel paymentOrderDetailModel : paymentOrderDetailModels) {
            ids = ids + paymentOrderDetailModel.getPurchaseDetailId() + ",";
            nper = paymentOrderDetailModel.getNper();
        }
        ids = ids.substring(0, ids.length() - 1);
        PaymentOrderDetailModel paymentOrderDetailModel = new PaymentOrderDetailModel();
        paymentOrderDetailModel.setIds(ids);
        paymentOrderDetailModel.setPurchaseOrderId(paymentOrder.getPurchaseOrderId());
        paymentOrderDetailModel.setNper(nper);
        paymentOrderDetailModel.setPaymentOrderId(cdocument);
        List<PaymentOrderDetailModel> paymentOrderDetailModels1 = paymentOrderDetailService.searchPurchaseForUpdate(paymentOrderDetailModel);

        PaymentOrderModel paymentOrderModel = new PaymentOrderModel();
        paymentOrderModel.setPurchaseOrderId(paymentOrder.getPurchaseOrderId());
        paymentOrderModel.setCdocument(cdocument);
        //查询待冲销的明细
        List<PaymentPaymentMapping> paymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingByOrderId(paymentOrderModel);
        //查询保存的冲销明细
        List<PaymentPaymentMapping> paymentPaymentMappingModels1 = paymentPaymentMappingService.searchPaymentPaymentMappingById(paymentOrderModel);
        //通过修改标识列的值，将新增时勾选的冲销明细选中
        for (PaymentPaymentMapping paymentPaymentMapping : paymentPaymentMappingModels1) {
            paymentPaymentMapping.setIsChecked("Y");
            paymentPaymentMappingModels.add(paymentPaymentMapping);
        }

        //获得扫描地
        List<ScanLocation> scanLocations = scanLocationService.findAll();
        //获得本次付款比例 及 预付款付款比例
        PaymentTerm paymentTerm = new PaymentTerm();
        paymentTerm.setPurchaseId(paymentOrder.getPurchaseOrderId());
        paymentTerm.setNper(paymentOrder.getNper());
        List<PaymentTerm> paymentTerms = paymentTermService.searchPaymentRatioByOrderId(paymentTerm);
        List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
        paymentOrder.setListFile(listFile);
        model.addAttribute("paymentOrder", paymentOrder);
        model.addAttribute("paymentOrderDetailModels", paymentOrderDetailModels1);
        model.addAttribute("paymentPaymentMappingModels", paymentPaymentMappingModels);
        model.addAttribute("scanLocations", scanLocations);
        model.addAttribute("paymentTerms", paymentTerms);
        return "purchase/payment_order_update";
    }

    /**
     * 导出
     *
     * @param model
     * @param request
     * @param response
     * @param paymentOrderModel
     * @return
     */
    @RequestMapping(value = "/Export", method = RequestMethod.GET)
    @ResponseBody
    public void export(ModelMap model, HttpServletRequest request,
                       HttpServletResponse response, PaymentOrderModel paymentOrderModel) {

        String fileName = "付款单据.xlsx";
        //设置当前登录人编码
        paymentOrderModel.setLoginUserCode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 付款单开始时间
            paymentOrderModel.setApplyDateStart(sdf.parse(request.getParameter("txtApplyDateStart")));
        } catch (Exception e) {
            paymentOrderModel.setApplyDateStart(null);
        }
        try {
            // 付款单结束时间
            paymentOrderModel.setApplyDateEnd(sdf.parse(request.getParameter("txtApplyDateEnd")));
        } catch (Exception e) {
            paymentOrderModel.setApplyDateEnd(null);
        }
        try {
            // 采购订单开始时间
            paymentOrderModel.setPurchaseApplyDateStart(sdf.parse(request.getParameter("txtPurchaseApplyDateStart")));
        } catch (Exception e) {
            paymentOrderModel.setPurchaseApplyDateStart(null);
        }
        try {
            // 采购订单结束时间
            paymentOrderModel.setPurchaseApplyDateEnd(sdf.parse(request.getParameter("txtPurchaseApplyDateEnd")));
        } catch (Exception e) {
            paymentOrderModel.setPurchaseApplyDateEnd(null);
        }
        paymentOrderModel.setDraft(null == paymentOrderModel.getDraft() ? "N" : paymentOrderModel.getDraft());
        paymentOrderModel.setPending(null == paymentOrderModel.getPending() ? "Y" : paymentOrderModel.getPending());
        paymentOrderModel.setAdminCode(Env.getProperty("role.assetAdmin.id"));
        List<PaymentOrderModel> paymentOrderModels = paymentOrderService.exportPaymentOrders(paymentOrderModel);
        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportPaymentOrderTemplate epat = new ExportPaymentOrderTemplate();
            epat.doExport(response, fileName, paymentOrderModels);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }

    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/api/deletePayment")
    @ResponseBody
    public ExecuteResult<PaymentOrder> deletePaymentOrder(HttpServletRequest request, String id) {
        ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setCdocument(id);
        paymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCdocument(paymentOrder.getPurchaseOrderId());
        if (!"等待审批".equals(paymentOrder.getCapprovalstate()) && !"退回".equals(paymentOrder.getCapprovalstate()) && "N".equals(paymentOrder.getDraft())) {
            result.addErrorMessage("此单据已审批，无法删除！");
            return result;
        }
/*		String usernameCode =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        if (!usernameCode.equals(paymentOrder.getApplyUserCode())) {
			result.addErrorMessage("您不是单据申请人，无法删除！");
			return result;
		}*/
        paymentOrderService.delPaymentOrderByCdocument(paymentOrder);
        //删除预付冲减明细
        paymentPaymentMappingService.deletePaymentPaymentMapping(paymentOrder);
        //删除审批流
        workFlowService.deleteGarbegeWorkFlowData(paymentOrder.getCdocument(), Env.getProperty("payment.info.type.id"));
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        executeResult = paymentOrderService.deleteBar(paymentOrder);
        if (!executeResult.isSuccess() && executeResult.getErrorMessages() != null) {
            result.setSuccessMessage("删除成功," + executeResult.getErrorMessages().get(0));
        } else {
            result.setSuccessMessage("删除成功," + executeResult.getSuccessMessage());
        }
        //查询此采购订单最大付款期数
        PaymentTerm paymentMaxTerm = paymentTermService.searchMaxTermByOrderId(purchaseOrder);
        //判断如果最后一期所有明细都选择最后一次付款则系统自动关闭订单
        if (paymentMaxTerm.getNper() == paymentOrder.getNper() && "N".equals(paymentOrder.getDraft())) {
            if (paymentOrderDetailService.completePayment(paymentOrder) == 0) {
                purchaseOrder.setIsCloseForPayment("已关闭");
                purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
            } else {
                purchaseOrder.setIsCloseForPayment("未关闭");
                purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
            }
        }
        return result;
    }

    /**
     * 影像调阅
     *
     * @param cdocument
     * @return
     */
    @RequestMapping("/api/checkBar")
    @ResponseBody
    public ExecuteResult<PaymentOrder> checkBar(String cdocument) {
        ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setCdocument(cdocument);
        result = paymentOrderService.checkBar(paymentOrder);
        return result;
    }

    /**
     * 历史审批记录
     *
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
     * 打印页面
     *
     * @param model
     * @param request
     * @param typeId
     * @return
     */
    @RequestMapping("/api/printOrder")
    public String printOrder(Model model, HttpServletRequest request, String typeId) {

        String cdocument = request.getParameter("id");
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setCdocument(cdocument);
        paymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCdocument(paymentOrder.getPurchaseOrderId());
        purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrder);
        List<PaymentOrderDetailModel> paymentOrderDetailModels = paymentOrderDetailService.searchPaymentDetailByOrderId(paymentOrder);
        boolean ifRollback = false;
        String url = "";
        try {
            BarServiceStub stub = new BarServiceStub();
            BarServiceStub.CreateBarCode createBarCode = new BarServiceStub.CreateBarCode();

            createBarCode.setMrmcode(paymentOrder.getCdocument());

            BarServiceStub.CreateBarCodeResponse createBarCodeResponse = stub.createBarCode(createBarCode);
            if (!"".equals(createBarCodeResponse.getCreateBarCodeResult())) {
                /*url = createBarCodeResponse.getCreateBarCodeResult().substring(1, createBarCodeResponse.getCreateBarCodeResult().length());*/
                url = createBarCodeResponse.getCreateBarCodeResult();
            }

        /*	if (!"0;".equals(deleteBarResponse.getErrMessage())) {

            }*/

        } catch (AxisFault axisFault) {
            ifRollback = true;
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            LOGGER.error("Exception", e);
        }
         
/*		List<PaymentPaymentMapping> paymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingById(paymentOrder);
        *//**
         * 获取此付款单发票检验明细信息
         *//*
        InvoiceInspection invoiceInspection = new InvoiceInspection();
		invoiceInspection.setPaymentOrderDocument(cdocument);
		invoiceInspection = invoiceInspectionService.getInvoiceInspection(invoiceInspection);
		List<InvoiceInspectionDetail> invoiceInspectionDetails = invoiceInspectionDetailService.searchInvoiceInspectionDetail(invoiceInspection);*/
        /**
         * 获取审批流流程的历史记录
         */
        List<ApproveHistoryCheckPrint> approveHistoryCheckPrints = checkAcceptanceService.getApproveHistory(cdocument, typeId);
/*        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(cdocument, typeId, pager);
        appHistoryPager.setUri(request.getRequestURI());*/
        //获取上传的文件列表
        List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
        paymentOrder.setListFile(listFile);
        model.addAttribute("approveHistoryCheckPrints", approveHistoryCheckPrints);

        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("url", url);
        model.addAttribute("paymentOrder", paymentOrder);
        model.addAttribute("paymentOrderDetailModels", paymentOrderDetailModels);
/*		model.addAttribute("paymentPaymentMappingModels",paymentPaymentMappingModels);
        model.addAttribute("invoiceInspection",invoiceInspection);
		model.addAttribute("invoiceInspectionDetails",invoiceInspectionDetails);*/
        return "purchase/payment_order_stamp";
    }
}
