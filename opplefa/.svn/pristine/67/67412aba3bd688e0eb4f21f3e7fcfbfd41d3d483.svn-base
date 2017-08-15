package com.opple.fa.purchase.webapp.controller;

import com.opple.fa.config.entity.AssetUnit;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.DeptCostCenter;
import com.opple.fa.config.entity.ScanLocation;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.service.AssetUnitService;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.DeptCostCenterService;
import com.opple.fa.config.service.ScanLocationService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.purchase.entity.NotPoPaymentOrder;
import com.opple.fa.purchase.entity.NotPoPaymentOrderDetail;
import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PaymentPaymentMapping;
import com.opple.fa.purchase.entity.WARNSTATE;
import com.opple.fa.purchase.export.controller.ExportNotPoPaymentOrderTemplate;
import com.opple.fa.purchase.model.NotPaymentOrderWrapper;
import com.opple.fa.purchase.model.PaymentPaymentMappingModel;
import com.opple.fa.purchase.service.CheckAcceptanceDetailService;
import com.opple.fa.purchase.service.CheckAcceptanceService;
import com.opple.fa.purchase.service.InvoiceInspectionService;
import com.opple.fa.purchase.service.NotPoPaymentOrderDetailService;
import com.opple.fa.purchase.service.NotPoPaymentOrderService;
import com.opple.fa.purchase.service.PaymentOrderService;
import com.opple.fa.purchase.service.PaymentPaymentMappingService;
import com.opple.fa.purchase.service.PurchaseCommonService;
import com.opple.fa.sap.model.IpHistoryModel;
import com.opple.fa.sap.model.IpInputModel;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/notPoPayment")
public class NotPoPaymentController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PaymentController.class);
    @Resource
    private PaymentOrderService paymentOrderService;
    @Resource
    private NotPoPaymentOrderDetailService notPoPaymentOrderDetailService;
    @Resource
    private PaymentPaymentMappingService paymentPaymentMappingService;
    @Resource
    private NotPoPaymentOrderService notPoPaymentOrderService;
    @Resource
    private AssetUnitService assetUnitService;
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
    private FileUploadService fileUploadServicel;
    @Resource
    private DeptCostCenterService deptCostCenterService;

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
    public String searchPayment(Model model, HttpServletRequest req, Long offset, Long pageSize, NotPoPaymentOrder notPoPaymentOrder) {
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        //设置当前登录人编码
        notPoPaymentOrder.setLoginUserCode(req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        notPoPaymentOrder.setAdminCode(Env.getProperty("role.assetAdmin.id"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 付款单开始时间
            notPoPaymentOrder.setApplyDateStart(sdf.parse(req.getParameter("applyDateStart")));
        } catch (Exception e) {
            notPoPaymentOrder.setApplyDateStart(null);
        }
        try {
            // 付款单结束时间
            notPoPaymentOrder.setApplyDateEnd(sdf.parse(req.getParameter("applyDateEnd")));
        } catch (Exception e) {
            notPoPaymentOrder.setApplyDateEnd(null);
        }
        Pager<NotPoPaymentOrder> pager = new Pager<NotPoPaymentOrder>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        notPoPaymentOrder.setCommitType(null == notPoPaymentOrder.getCommitType() ? "N" : notPoPaymentOrder.getCommitType());
        notPoPaymentOrder.setDocument(null == notPoPaymentOrder.getDocument() ? null : notPoPaymentOrder.getDocument().trim());
        notPoPaymentOrder.setApplyUser(null == notPoPaymentOrder.getApplyUser() ? null : notPoPaymentOrder.getApplyUser().trim());

        Pager<NotPoPaymentOrder> paymentPager = notPoPaymentOrderService.searchPaymentOrder(pager, notPoPaymentOrder);
        paymentPager.setUri(req.getRequestURI());
        model.addAttribute("paymentPager", paymentPager);
        model.addAttribute("loginUserCode", loginUserCode);
        return "purchase/notpo_payment_order_list";
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
    @RequestMapping("/checkNotPoPaymentOrder")
    public String checkPaymentOrder(Model model, HttpServletRequest req, Long offset, Long pageSize) {

        return "purchase/notpo_payment_order_check";
    }

    /**
     * 添加页面
     *
     * @param model
     * @param req
     * @return
     */
    @RequestMapping("/addNotPoPaymentOrder")
    public String addPaymentOrder(Model model, HttpServletRequest req) {

        String paymentType = req.getParameter("paymentType");
        if ("prepay".equals(paymentType)) {
            paymentType = "预付款";
        }
        if ("check".equals(paymentType)) {
            paymentType = "验收款";
        }
        if ("final".equals(paymentType)) {
            paymentType = "尾款";
        }
        NotPoPaymentOrder notPoPaymentOrder = new NotPoPaymentOrder();
        // 生产付款单编号
        String str = "SPFK";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        str += sdf.format(date);
        int searchNextSequence = paymentOrderService.searchNextSequence();
        // 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
        str += String.format("%04d", searchNextSequence);
        notPoPaymentOrder.setCdocument(str);
        notPoPaymentOrder.setApplyDate(date);
        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        notPoPaymentOrder.setApplyUser(applyUsername);
        notPoPaymentOrder.setApplyUserCode(applyUsernameCode);

        //根据申请人 查询 所属部门
        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);

        model.addAttribute("userDeptInfo", userDeptInfo);
        //单位
        List<AssetUnit> assetUnitList = assetUnitService.getAssetUnitList();
        model.addAttribute("assetUnitList", assetUnitList);
        // 币种
        List<UserInfo> currencylist = userInfoService.searchCurrencyInfo();
        model.addAttribute("currencylist", currencylist);
        //获得扫描地
        List<ScanLocation> scanLocations = scanLocationService.findAll();
        notPoPaymentOrder.setPaymentType(paymentType);
        //获得办公地点
        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        model.addAttribute("addresslist", addresslist);
        //尾款查询所有待付的验收单
        List<NotPoPaymentOrder> acceptOrderNumbers = notPoPaymentOrderService.searchPoPaymentOrders(notPoPaymentOrder);
        model.addAttribute("notPoPaymentOrder", notPoPaymentOrder);
        model.addAttribute("acceptOrderNumbers", acceptOrderNumbers);

        model.addAttribute("scanLocations", scanLocations);
        if ("验收款".equals(paymentType)) {
            return "purchase/notpo_payment_forcheck_add";
        }
        return "purchase/notpo_payment_forprepay_add";
    }

    @RequestMapping("/api/savePayment")
    @ResponseBody
    public ExecuteResult<NotPoPaymentOrder> savePayment(@RequestBody NotPaymentOrderWrapper notPaymentOrderWrapper, HttpServletRequest req) {
        ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();

        NotPoPaymentOrder notPoPaymentOrder = notPaymentOrderWrapper.getNotPoPaymentOrder();
        List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails = notPaymentOrderWrapper.getNotPoPaymentOrderDetails();
        List<PaymentPaymentMappingModel> paymentPaymentMappingModels = notPaymentOrderWrapper.getPaymentPaymentMappingModels();
        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        //订单表头
        if ("N".equals(notPoPaymentOrder.getCommitType())) {
            notPoPaymentOrder.setApprovalState("等待审批");
            notPoPaymentOrder.setCommitType("N");
        }
        notPoPaymentOrder.setCompanyCode(notPoPaymentOrder.getCompany().split("-")[0]);
        notPoPaymentOrder.setCompanyName(notPoPaymentOrder.getCompany().split("-")[1]);
        notPoPaymentOrder.setStatus("Y");
        notPoPaymentOrder.setApplyUser(applyUsername);
        notPoPaymentOrder.setCreateUserName(applyUsername);
        notPoPaymentOrder.setUpdateUserName(applyUsername);
        notPoPaymentOrder.setApplyUserCode(applyUsernameCode);
        notPoPaymentOrder.setCreateBy(applyUsernameCode);
        notPoPaymentOrder.setUpdateBy(applyUsernameCode);
        notPoPaymentOrder.setApplyDate(new Date());
        notPoPaymentOrder.setCreateDate(new Date());
        notPoPaymentOrder.setUpdateDate(new Date());

/*		String profitCenter = paymentOrderService.searchProfitCenter(searchPurchaseOrderByOrderId.getCostCenterCode());*/

        AttchConfig attchConfig = attchConfigService.searchPurchaseManager(applyUsernameCode);
        notPoPaymentOrder.setPurchaseManagerCode(attchConfig.getPurchasingManagerCode());
        notPoPaymentOrder.setPurchaseManagerName(attchConfig.getPurchasingManagerName());
        UserInfo userInfo = new UserInfo();
        if (!"验收款".equals(notPoPaymentOrder.getPaymentType())) {
            userInfo.setCemployeecode(notPoPaymentOrder.getPaymentPeopleCode());


            UserInfo searchSupplierCode = userInfoService.searchSupplierCode(userInfo);
            if (searchSupplierCode == null) {
                result.addErrorMessage("没有找到[" + notPoPaymentOrder.getPaymentPeopleCode() + "]对应得供应商信息！");
                return result;
            }

            notPoPaymentOrder.setPaymentPeopleCode(searchSupplierCode.getCsupplierCode());
        }
        result = notPoPaymentOrderService.savePayment(notPoPaymentOrder);
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();

        //如果本次是验收款，则查询所有预付款明细    获得预付冲减明细
        if ("验收款".equals(notPoPaymentOrder.getPaymentType())) {
            //订单表体明细
            for (NotPoPaymentOrderDetail pod : notPoPaymentOrderDetails) {
                pod.setCreateBy(applyUsername);
                pod.setUpdateBy(applyUsername);
                pod.setCreateByCode(applyUsernameCode);
                pod.setUpdateByCode(applyUsernameCode);
                pod.setCreateDate(new Date());
                pod.setUpdateDate(new Date());
            }
            for (PaymentPaymentMappingModel ppm : paymentPaymentMappingModels) {
                if (ppm.getPayPrice().compareTo(new BigDecimal(ppm.getIamount())) == 1) {
                    result.addErrorMessage("预付冲减金额超出！[" + ppm.getPayPrice() + "]");
                    return result;
                }
            }
        }
        if ("验收款".equals(notPoPaymentOrder.getPaymentType())) {
            result = notPoPaymentOrderService.savePaymentForCheck(notPoPaymentOrder, notPoPaymentOrderDetails, paymentPaymentMappingModels);
        }
        if (!result.isSuccess()) {
            return result;
        }
        if ("N".equals(notPoPaymentOrder.getCommitType())) {
            result = notPoPaymentOrderService.binDingFlow(notPoPaymentOrder);
        }
        //如果保存失败  删除表头信息 并且删除审批流
        if (!result.isSuccess()) {
            //删除审批流
            workFlowService.deleteGarbegeWorkFlowData(notPoPaymentOrder.getCdocument(), Env.getProperty("notpayment.info.type.id"));
            notPoPaymentOrderService.delPaymentOrderByCdocument(notPoPaymentOrder);
            result.addErrorMessage("添加失败");
            return result;
        }
        result.setSuccessMessage("添加成功");
        NotPoPaymentOrder searchPaymentOrderByOrderId = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsMail())) {
            searchPaymentOrderByOrderId.setIsMail("Y");
        } else {
            searchPaymentOrderByOrderId.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsPhoneMessage())) {
            searchPaymentOrderByOrderId.setIsPhoneMessage("Y");
        } else {
            searchPaymentOrderByOrderId.setIsPhoneMessage("N");
        }
        if (StringUtils.isNotBlank(searchPaymentOrderByOrderId.getNextHandlerCode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPaymentOrderByOrderId.getNextHandlerCode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            if (!er.isSuccess()) {
                result.addErrorMessage("添加成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsPortrait())) {
            executeResult = notPoPaymentOrderService.saveBar(notPoPaymentOrder);
            if (!executeResult.isSuccess() && executeResult.getErrorMessages() != null) {
                result.setSuccessMessage("添加成功," + executeResult.getErrorMessages().get(0));
            } else {
                result.setSuccessMessage("添加成功," + executeResult.getSuccessMessage());
                notPoPaymentOrderService.updatePaymentOrderByCdocument(notPoPaymentOrder);
            }
        }
        return result;
    }

    /**
     * 查询利润中心
     *
     * @param costcentercode
     * @return
     */
    @RequestMapping("/api/searchProfitCenter")
    @ResponseBody
    public String searchProfitCenter(String costcentercode) {
        String code = paymentOrderService.searchProfitCenter(costcentercode);
        return code;
    }

    /**
     * 查询尾款金额
     *
     * @param costcentercode
     * @return
     */
    @RequestMapping("/api/searchSumPriceByCdocument")
    @ResponseBody
    public String searchSumPriceByCdocument(String cdocument) {
        NotPoPaymentOrder notPoPaymentOrder = new NotPoPaymentOrder();
        notPoPaymentOrder.setCdocument(cdocument);
        String price = notPoPaymentOrderService.searchSumPriceByCdocument(notPoPaymentOrder);
        return price;
    }

    @RequestMapping("/showPaymentOrder")
    public String showPaymentOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {

        String cdocument = req.getParameter("id");
        NotPoPaymentOrder notPoPaymentOrder = new NotPoPaymentOrder();
        notPoPaymentOrder.setCdocument(cdocument);
        notPoPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);

        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(cdocument, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());
        //获取上传的文件列表
        List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
        notPoPaymentOrder.setListFile(listFile);
        model.addAttribute("appHistoryPager", appHistoryPager);

        model.addAttribute("notPoPaymentOrder", notPoPaymentOrder);
        if ("验收款".equals(notPoPaymentOrder.getPaymentType())) {
            NotPoPaymentOrderDetail notPoPaymentOrderDetail = new NotPoPaymentOrderDetail();
            notPoPaymentOrderDetail.setNotPoPaymentOrderId(notPoPaymentOrder.getCdocument());
            List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails = notPoPaymentOrderDetailService.searchPaymentOrderByDocument(notPoPaymentOrderDetail);
            model.addAttribute("notPoPaymentOrderDetails", notPoPaymentOrderDetails);
            PaymentOrder paymentOrder = new PaymentOrder();
            paymentOrder.setCdocument(cdocument);
            List<PaymentPaymentMapping> paymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingById(paymentOrder);
            model.addAttribute("paymentPaymentMappingModels", paymentPaymentMappingModels);
            return "purchase/notpo_payment_forcheck_detail";
        }

        return "purchase/notpo_payment_forprepay_detail";
    }

    /**
     * 打印
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param typeId
     * @return
     */
    @RequestMapping("/api/printOrder")
    public String printOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {

        String cdocument = req.getParameter("id");
        NotPoPaymentOrder notPoPaymentOrder = new NotPoPaymentOrder();
        notPoPaymentOrder.setCdocument(cdocument);
        notPoPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);

        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(cdocument, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());
        //获取上传的文件列表
        List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
        notPoPaymentOrder.setListFile(listFile);
        model.addAttribute("appHistoryPager", appHistoryPager);

        model.addAttribute("notPoPaymentOrder", notPoPaymentOrder);
        if ("验收款".equals(notPoPaymentOrder.getPaymentType())) {
            NotPoPaymentOrderDetail notPoPaymentOrderDetail = new NotPoPaymentOrderDetail();
            notPoPaymentOrderDetail.setNotPoPaymentOrderId(notPoPaymentOrder.getCdocument());
            List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails = notPoPaymentOrderDetailService.searchPaymentOrderByDocument(notPoPaymentOrderDetail);
            model.addAttribute("notPoPaymentOrderDetails", notPoPaymentOrderDetails);
            return "purchase/notpo_payment_forcheck_stamp";
        }

        return "purchase/notpo_payment_forprepay_stamp";
    }

    /*	@RequestMapping("/api/updatePayment")
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

            String applyUsername =	req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
            String applyUsernameCode =	req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
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

            int i=1;
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
                pod.setItemnoAcc(i*10L);
                i++;
            }

            //如果本次是验收款，则查询所有预付款明细    获得预付冲减明细
            if ("验收款".equals(paymentOrder.getPaymentType())) {
                if (paymentPaymentMappingModels.size() == 0) {
                    result.addErrorMessage("预付冲减明细不能少于1行！");
                    return result;
                }
                for (PaymentPaymentMappingModel ppm : paymentPaymentMappingModels) {
                    if(ppm.getPayPrice().compareTo(new BigDecimal(ppm.getIamount()))==1){
                        result.addErrorMessage("预付冲减金额超出！["+ppm.getPayPrice()+"]");
                        return result;
                    }
                }
            }
            ExecuteResult<PaymentOrder> extRst = paymentOrderService.updatePaymentOrder(paymentOrder);

            if (!extRst.isSuccess()) {
                return extRst;
            }
            if (WARNSTATE.YES.getWarnstate().equals(paymentOrder.getIsMail())) {
                paymentOrder.setIsMail("Y");
            } else{
                paymentOrder.setIsMail("N");
            }
            if (WARNSTATE.YES.getWarnstate().equals(paymentOrder.getIsPhoneMessage())) {
                paymentOrder.setIsPhoneMessage("Y");
            } else{
                paymentOrder.setIsPhoneMessage("N");
            }

            result = paymentOrderService.updateSavePaymentOrder(paymentOrder, paymentOrderDetails,paymentPaymentMappingModels);
            //如果保存失败  删除表头信息 并且删除审批流
            if (!result.isSuccess()) {
                //删除审批流
                workFlowService.deleteGarbegeWorkFlowData(paymentOrder.getCdocument(), Env.getProperty("payment.info.type.id"));
            }
            PaymentOrder searchPaymentOrderByOrderId = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
            remindedService.BellReminder(searchPaymentOrderByOrderId.getCnexthandlercode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsMail(), searchPaymentOrderByOrderId.getIsPhoneMessage());
            //查询此采购订单最大付款期数
           PaymentTerm paymentMaxTerm = paymentTermService.searchMaxTermByOrderId(purchaseOrder);
           //判断如果最后一期所有明细都选择最后一次付款则系统自动关闭订单
           if (paymentMaxTerm.getNper() == paymentOrder.getNper()&&"N".equals(paymentOrder.getDraft())) {
                if (paymentOrderDetailService.completePayment(paymentOrder)==0) {
                    purchaseOrder.setIsCloseForPayment("已关闭");
                    purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
                }
                else {
                    purchaseOrder.setIsCloseForPayment("未关闭");
                    purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
                }
            }
            paymentOrderService.saveBar(paymentOrder);
            return result;
        }
    */
    @RequestMapping("/examinePaymentOrder")
    public String examinePaymentOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {


        String cdocument = req.getParameter("id");
        NotPoPaymentOrder notPoPaymentOrder = new NotPoPaymentOrder();
        notPoPaymentOrder.setCdocument(cdocument);
        notPoPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);

        List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
        notPoPaymentOrder.setListFile(listFile);
        /**
         * 获取审批流流程的历史记录
         */
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(cdocument, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());

        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        notPoPaymentOrder.setUserDepCode(userDeptInfo.getDepartmentCode());
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.GetIsLastApprovalStep getIsLastApprovalStep = new WorkFlowStub.GetIsLastApprovalStep();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();
            agreeFlowParam.setBillCode(notPoPaymentOrder.getCdocument());
            agreeFlowParam.setBillTableName(Env.getProperty("notpayment.info.table"));
            agreeFlowParam.setBillCodeColumnName("CDOCUMENT");
            agreeFlowParam.setCUserCode(applyUsernameCode);
            agreeFlowParam.setCUserName(applyUsername);
            agreeFlowParam.setAMoney(notPoPaymentOrder.getApplyMoney().toString());
            agreeFlowParam.setTypeCode(Env.getProperty("notpayment.info.type.id"));
            agreeFlowParam.setIdea(notPoPaymentOrder.getIdea());
            agreeFlowParam.setUserDepCode(notPoPaymentOrder.getUserDepCode());
            agreeFlowParam.setCreateDate(Calendar.getInstance());
            getIsLastApprovalStep.setParam(agreeFlowParam);
            WorkFlowStub.GetIsLastApprovalStepResponse response = stub.getIsLastApprovalStep(getIsLastApprovalStep);
            if (response.getGetIsLastApprovalStepResult()) {
                notPoPaymentOrder.setIsLastApprove("Y");
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
        model.addAttribute("notPoPaymentOrder", notPoPaymentOrder);
        model.addAttribute("appHistoryPager", appHistoryPager);
        if ("验收款".equals(notPoPaymentOrder.getPaymentType())) {
            NotPoPaymentOrderDetail notPoPaymentOrderDetail = new NotPoPaymentOrderDetail();
            notPoPaymentOrderDetail.setNotPoPaymentOrderId(notPoPaymentOrder.getCdocument());
            List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails = notPoPaymentOrderDetailService.searchPaymentOrderByDocument(notPoPaymentOrderDetail);
            model.addAttribute("notPoPaymentOrderDetails", notPoPaymentOrderDetails);
            PaymentOrder paymentOrder = new PaymentOrder();
            paymentOrder.setCdocument(cdocument);
            List<PaymentPaymentMapping> paymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingById(paymentOrder);
            model.addAttribute("paymentPaymentMappingModels", paymentPaymentMappingModels);
            return "purchase/notpo_payment_forcheck_examine";
        }
        return "purchase/notpo_payment_forprepay_examine";
    }

    @RequestMapping("/api/agreePayment")
    @ResponseBody
    public ExecuteResult<NotPoPaymentOrder> agreePayment(@RequestBody NotPoPaymentOrder notPoPaymentOrder, HttpServletRequest request) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();
        //修改前的订单
        NotPoPaymentOrder searchPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        if (notPoPaymentOrder.getCvoucherId() != null && !"".equals(notPoPaymentOrder.getCvoucherId())) {
            notPoPaymentOrderService.updateCvoucherId(notPoPaymentOrder);
        }
        searchPaymentOrder.setCdocument(notPoPaymentOrder.getCdocument());
        searchPaymentOrder.setIdea(notPoPaymentOrder.getIdea());
        searchPaymentOrder.setIsMail(notPoPaymentOrder.getIsMail());
        searchPaymentOrder.setIsPhoneMessage(notPoPaymentOrder.getIsPhoneMessage());

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


        executeResult = notPoPaymentOrderService.agreeWorkFlow(searchPaymentOrder);

        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        NotPoPaymentOrder searchPaymentOrderByOrderId = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsMail())) {
            searchPaymentOrderByOrderId.setIsMail("Y");
        } else {
            searchPaymentOrderByOrderId.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsPhoneMessage())) {
            searchPaymentOrderByOrderId.setIsPhoneMessage("Y");
        } else {
            searchPaymentOrderByOrderId.setIsPhoneMessage("N");
        }
        if (StringUtils.isNotBlank(searchPaymentOrderByOrderId.getNextHandlerCode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPaymentOrderByOrderId.getNextHandlerCode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        }
        return executeResult;
    }

    @RequestMapping("/api/denyPayment")
    @ResponseBody
    public ExecuteResult<NotPoPaymentOrder> denyPayment(@RequestBody NotPoPaymentOrder notPoPaymentOrder, HttpServletRequest request) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();

        if (StringUtils.isBlank(notPoPaymentOrder.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }

        //修改前的订单
        NotPoPaymentOrder searchPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);

        if (APPROVESTATE.DONE.getApproveState().equals(searchPaymentOrder.getApprovalState())) {
            executeResult.addErrorMessage("已完成的单据不可以退回");
            return executeResult;
        }

        searchPaymentOrder.setIdea(notPoPaymentOrder.getIdea());
        searchPaymentOrder.setIsMail(notPoPaymentOrder.getIsMail());
        searchPaymentOrder.setIsPhoneMessage(notPoPaymentOrder.getIsPhoneMessage());
        searchPaymentOrder.setActivityId(notPoPaymentOrder.getActivityId());
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

        executeResult = notPoPaymentOrderService.denyWorkFlow(searchPaymentOrder);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        NotPoPaymentOrder searchPaymentOrderByOrderId = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsMail())) {
            searchPaymentOrderByOrderId.setIsMail("Y");
        } else {
            searchPaymentOrderByOrderId.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsPhoneMessage())) {
            searchPaymentOrderByOrderId.setIsPhoneMessage("Y");
        } else {
            searchPaymentOrderByOrderId.setIsPhoneMessage("N");
        }
        if (StringUtils.isNotBlank(searchPaymentOrderByOrderId.getNextHandlerCode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPaymentOrderByOrderId.getNextHandlerCode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            if (!er.isSuccess()) {
                executeResult.setSuccessMessage("审批成功，但是邮件或短信发送失败");
            }
        }
        ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrderByOrderId.getIsPortrait())) {
            result = notPoPaymentOrderService.backBar(searchPaymentOrder);
            if (!result.isSuccess() && result.getErrorMessages() != null) {
                executeResult.setSuccessMessage(executeResult.getSuccessMessage() + "," + result.getErrorMessages().get(0));
            } else {
                executeResult.setSuccessMessage(executeResult.getSuccessMessage() + "," + result.getSuccessMessage());
                notPoPaymentOrderService.updatePaymentOrderByCdocument(searchPaymentOrder);
            }
        }
        return executeResult;
    }

    @RequestMapping("/api/dropPayment")
    @ResponseBody
    public ExecuteResult<NotPoPaymentOrder> dropPayment(@RequestBody NotPoPaymentOrder notPoPaymentOrder, HttpServletRequest request) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();

        if (StringUtils.isBlank(notPoPaymentOrder.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }
        NotPoPaymentOrder searchPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);

        searchPaymentOrder.setIdea(notPoPaymentOrder.getIdea());
        searchPaymentOrder.setIsMail(notPoPaymentOrder.getIsMail());
        searchPaymentOrder.setIsPhoneMessage(notPoPaymentOrder.getIsPhoneMessage());

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

        executeResult = notPoPaymentOrderService.dropWorkFlow(searchPaymentOrder);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        NotPoPaymentOrder searchPaymentOrderByOrderId = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsMail())) {
            searchPaymentOrderByOrderId.setIsMail("Y");
        } else {
            searchPaymentOrderByOrderId.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsPhoneMessage())) {
            searchPaymentOrderByOrderId.setIsPhoneMessage("Y");
        } else {
            searchPaymentOrderByOrderId.setIsPhoneMessage("N");
        }
        if (StringUtils.isNotBlank(searchPaymentOrderByOrderId.getNextHandlerCode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPaymentOrderByOrderId.getNextHandlerCode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        }
        return executeResult;
    }

    //删除
    @RequestMapping("/api/deleteNotPoPayment")
    @ResponseBody
    public ExecuteResult<NotPoPaymentOrder> deletePaymentOrder(HttpServletRequest request, String id) {
        ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();
        NotPoPaymentOrder notPoPaymentOrder = new NotPoPaymentOrder();
        notPoPaymentOrder.setCdocument(id);
        NotPoPaymentOrder searchPaymentOrderByOrderId = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        if (!"等待审批".equals(searchPaymentOrderByOrderId.getApprovalState()) && !"退回".equals(searchPaymentOrderByOrderId.getApprovalState())
                && "N".equals(searchPaymentOrderByOrderId.getCommitType())) {
            result.addErrorMessage("此单据已审批，无法删除！");
            return result;
        }
        notPoPaymentOrderService.deleteNoPoPaymentOrder(searchPaymentOrderByOrderId);
        result.setSuccessMessage("删除成功");
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();
        executeResult = notPoPaymentOrderService.deleteBar(notPoPaymentOrder);
        if (!executeResult.isSuccess() && executeResult.getErrorMessages() != null) {
            result.setSuccessMessage("删除成功," + executeResult.getErrorMessages().get(0));
        } else {
            result.setSuccessMessage("删除成功," + executeResult.getSuccessMessage());
        }
        return result;
    }

    /**
     * 导出
     *
     * @param ccompanycode
     * @param ccostcentercode
     * @return
     */
    @RequestMapping(value = "/Export", method = RequestMethod.GET)
    @ResponseBody
    public void export(ModelMap model, HttpServletRequest request,
                       HttpServletResponse response, NotPoPaymentOrder notPoPaymentOrder) {

        String fileName = "非PO付款.xlsx";
        //设置当前登录人编码
        notPoPaymentOrder.setLoginUserCode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        notPoPaymentOrder.setAdminCode(Env.getProperty("role.assetAdmin.id"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 付款单开始时间
            notPoPaymentOrder.setApplyDateStart(sdf.parse(request.getParameter("applyDateStart")));
        } catch (Exception e) {
            notPoPaymentOrder.setApplyDateStart(null);
        }
        try {
            // 付款单结束时间
            notPoPaymentOrder.setApplyDateEnd(sdf.parse(request.getParameter("applyDateEnd")));
        } catch (Exception e) {
            notPoPaymentOrder.setApplyDateEnd(null);
        }
        notPoPaymentOrder.setCommitType(null == notPoPaymentOrder.getCommitType() ? "N" : notPoPaymentOrder.getCommitType());
        notPoPaymentOrder.setDocument(null == notPoPaymentOrder.getDocument() ? null : notPoPaymentOrder.getDocument().trim());
        notPoPaymentOrder.setApplyUser(null == notPoPaymentOrder.getApplyUser() ? null : notPoPaymentOrder.getApplyUser().trim());

        List<NotPoPaymentOrder> notPaymentOrderModels = notPoPaymentOrderService.exportNotPoPaymentOrders(notPoPaymentOrder);
        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportNotPoPaymentOrderTemplate epat = new ExportNotPoPaymentOrderTemplate();
            epat.doExport(response, fileName, notPaymentOrderModels);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }

    }

    /**
     * 根据供应商编码和币种编码查询待冲销凭证
     *
     * @param ccompanycode
     * @param ccostcentercode
     * @return
     */
    @RequestMapping(value = "/api/searchPaymentPaymentMappingByCode")
    @ResponseBody
    public List<PaymentPaymentMapping> searchPaymentPaymentMappingByCode(String supplierCode, String currencyCode) {
        return paymentPaymentMappingService.searchPaymentPaymentMappingByCode(supplierCode, currencyCode);

    }

    /**
     * 影像调阅
     *
     * @param cdocument
     * @return
     */
    @RequestMapping("/api/checkBar")
    @ResponseBody
    public ExecuteResult<NotPoPaymentOrder> checkBar(String cdocument) {
        ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();
        NotPoPaymentOrder searchPaymentOrder = new NotPoPaymentOrder();
        searchPaymentOrder.setCdocument(cdocument);
        result = notPoPaymentOrderService.checkBar(searchPaymentOrder);
        return result;
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
    public ExecuteResult<NotPoPaymentOrder> createReceiptVoucher(@RequestBody NotPoPaymentOrder notPoPaymentOrder, HttpServletRequest request) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();
        NotPoPaymentOrder searchPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        String userName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String userNameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        boolean ifRollback = false;
        try {
            VoucherServiceStub stub = new VoucherServiceStub();
            VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_Tybx_rcjk rcjk = new VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_Tybx_rcjk();
            rcjk.setBillcode(notPoPaymentOrder.getCdocument());
            rcjk.setCusercode(userName);
            rcjk.setCusername(userNameCode);

            VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_Tybx_rcjkResponse payResponse = stub.linkSAP_BAPI_ToVouchInfo_Tybx_rcjk(rcjk);
            if (StringUtils.isNotBlank(payResponse.getLinkSAP_BAPI_ToVouchInfo_Tybx_rcjkResult())) {
                executeResult.setSuccessMessage(payResponse.getLinkSAP_BAPI_ToVouchInfo_Tybx_rcjkResult());
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
            executeResult.addErrorMessage("生成预付凭证失败");
            return executeResult;
        }
        searchPaymentOrder.setCinputVoucher("N");
        searchPaymentOrder.setIsComingSAP("Y");
        notPoPaymentOrderService.updatePaymentOrderByDocument(searchPaymentOrder);
        NotPoPaymentOrder backPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        executeResult.setResult(backPaymentOrder);
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
    public ExecuteResult<NotPoPaymentOrder> createPrepayment(@RequestBody NotPoPaymentOrder notPoPaymentOrder, HttpServletRequest request) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();
        NotPoPaymentOrder searchPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        String userName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String userNameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        boolean ifRollback = false;
        try {
            VoucherServiceStub stub = new VoucherServiceStub();
            VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_Tybx_Acc acc = new VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_Tybx_Acc();
            acc.setBillcode(notPoPaymentOrder.getCdocument());
            acc.setCusercode(userNameCode);
            acc.setCusername(userName);

            VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_Tybx_AccResponse payResponse = stub.linkSAP_BAPI_ToVouchInfo_Tybx_Acc(acc);
            if (StringUtils.isNotBlank(payResponse.getLinkSAP_BAPI_ToVouchInfo_Tybx_AccResult())) {
                executeResult.setSuccessMessage(payResponse.getLinkSAP_BAPI_ToVouchInfo_Tybx_AccResult());
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
            executeResult.addErrorMessage("生成验收凭证失败");
            return executeResult;
        }
        searchPaymentOrder.setCinputVoucher("N");
        searchPaymentOrder.setIsComingSAP("Y");
        notPoPaymentOrderService.updatePaymentOrderByDocument(searchPaymentOrder);
        NotPoPaymentOrder backPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        executeResult.setResult(backPaymentOrder);
        return executeResult;
    }

    /**
     * 获取资产编码
     *
     * @param notPaymentOrderWrapper
     * @param request
     * @return
     */
    @RequestMapping("/api/sapInterface")
    @ResponseBody
    public ExecuteResult<NotPoPaymentOrder> sapInterface(@RequestBody NotPaymentOrderWrapper notPaymentOrderWrapper, HttpServletRequest request) {

        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();
        ExecuteResult<NotPoPaymentOrderDetail> result = new ExecuteResult<NotPoPaymentOrderDetail>();

        //表头信息
        NotPoPaymentOrder notPoPaymentOrderByForm = notPaymentOrderWrapper.getNotPoPaymentOrder();
        NotPoPaymentOrder notPoPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrderByForm);

        //申请人姓名
        String applyUser = notPoPaymentOrder.getApplyUser();
        //申请人工号
        String applyUserCode = notPoPaymentOrder.getApplyUserCode();
        //表体信息
        List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails = notPaymentOrderWrapper.getNotPoPaymentOrderDetails();
        for (NotPoPaymentOrderDetail notPoPaymentOrderDetail : notPoPaymentOrderDetails) {
            if (notPoPaymentOrderDetail.getAssetClassification() == null || "".equals(notPoPaymentOrderDetail.getAssetClassification()) || "null".equals(notPoPaymentOrderDetail.getAssetClassification())) {
                executeResult.addErrorMessage("资产分类不能为空");
                break;
            }
            if (notPoPaymentOrderDetail.getLedgerAccount() == null || "".equals(notPoPaymentOrderDetail.getLedgerAccount())
                    || "null".equals(notPoPaymentOrderDetail.getLedgerAccount())) {
                executeResult.addErrorMessage("总账科目不能为空");
                break;
            }
        }
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        if ("Y".equals(notPoPaymentOrder.getIsExpenseAssets())) {
            for (NotPoPaymentOrderDetail notPoPaymentOrderDetail : notPoPaymentOrderDetails) {
                if (!"F".equals(notPoPaymentOrderDetail.getAssetClassification().substring(0, 1))) {
                    executeResult.addErrorMessage("资产分类必须是[F]开头");
                    break;
                }
                //生成次级
                String getSecondary = "0000-";
                // 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
                getSecondary += String.format("%04d", notPoPaymentOrderDetail.getPayCount() - 1);
                notPoPaymentOrderDetail.setSecondary(getSecondary);
                result = notPoPaymentOrderDetailService.updatePaymentOrderDetailById(notPoPaymentOrderDetail);
            }
            if (!executeResult.isSuccess()) {
                return executeResult;
            }
            //executeResult = applyOrderService.updateApplyOrderDetail(applyOrder, applyOrderDetails);
            if (!executeResult.isSuccess()) {
                return executeResult;
            }
            notPoPaymentOrderService.getMainAssetCodePro(notPoPaymentOrder);
        } else {
            try {
                for (int i = 0; notPoPaymentOrderDetails.size() > i; i++) {
                    if ("forAdd".equals(notPoPaymentOrderDetails.get(i).getCodingMode())) {
                        IpInputModel ipInputModel = new IpInputModel();
                        ipInputModel.setAssetclass(notPoPaymentOrderDetails.get(i).getAssetClassification());
                        ipInputModel.setCompanycode(notPoPaymentOrder.getCompanyCode());
//							ipInputModel.setDescript("");
                        long count = notPoPaymentOrderDetails.get(i).getPayCount();
                        ipInputModel.setSubnumber(StringUtils.leftPad(String.valueOf(count), 4, "0"));
                        ipInputModel.setQuantity(count);
                        ipInputModel.setCostcenter(notPoPaymentOrderDetails.get(i).getCostCenterCode());

                        AssetUnit assetUnit = new AssetUnit();
                        assetUnit.setUnitName(notPoPaymentOrderDetails.get(i).getUnits());
                        AssetUnit assetUnit2 = assetUnitService.getAssetUnit(assetUnit);
                        ipInputModel.setBaseUom(assetUnit2.getUnitCode());

                        ipInputModel.setInventNo(applyUserCode + "-" + applyUser);
                        ipInputModel.setDescript(notPoPaymentOrderDetails.get(i).getAssetsName());
                        ipInputModel.setMainDescript(notPoPaymentOrderDetails.get(i).getAssetsName());

                        IpHistoryModel ipHistoryModel = new IpHistoryModel();
                        ipHistoryModel.setOano("Oano");
                        ipHistoryModel.setOamodule("Oamodule");
                        ipHistoryModel.setOaname("Oaname");

                        JSONObject json = sapUtilService.createAssetsCodeFromSAP(ipInputModel, ipHistoryModel);
                        if (json.getString("error").length() > 1) {
                            executeResult.addErrorMessage(json.getString("error"));
                            return executeResult;
                        }
                        notPoPaymentOrderDetails.get(i).setMainAssetCode(json.getString("asset"));
                        notPoPaymentOrderDetails.get(i).setSecondary(json.getString("subNumber"));

                        if (!json.has("asset") || !json.has("subNumber")) {
                            executeResult.addErrorMessage("新增sap接口调用缺少返回值内容，请检查页面内容。");
                            return executeResult;
                        }

                    } else if ("forAppend".equals(notPoPaymentOrderDetails.get(i).getCodingMode())) {

                        //					ItInputModel itInputModel = new ItInputModel();
                        //					itInputModel.setAnln1(applyOrderDetails.get(i).getMainAssetCode());
                        //					itInputModel.setCount(applyOrderDetails.get(i).getDemandCount());

                        IpInputModel ipInputModel = new IpInputModel();

                        ipInputModel.setAssetclass(notPoPaymentOrderDetails.get(i).getAssetClassification());
                        ipInputModel.setCompanycode(notPoPaymentOrder.getCompanyCode());
                        //ipInputModel.setQuantity(2L);
                        ipInputModel.setAnln1(notPoPaymentOrderDetails.get(i).getMainAssetCode()); //2
                        long count = notPoPaymentOrderDetails.get(i).getPayCount();
                        ipInputModel.setSubnumber(String.valueOf(count));

                        AssetUnit assetUnit = new AssetUnit();
                        assetUnit.setUnitName(notPoPaymentOrderDetails.get(i).getUnits());
                        AssetUnit assetUnit2 = assetUnitService.getAssetUnit(assetUnit);
                        ipInputModel.setBaseUom(assetUnit2.getUnitCode());

                        ipInputModel.setCostcenter(notPoPaymentOrderDetails.get(i).getCostCenterCode());
                        ipInputModel.setInventNo(applyUserCode + "-" + applyUser);
                        ipInputModel.setDescript(notPoPaymentOrderDetails.get(i).getAssetsName());
                        ipInputModel.setMainDescript(notPoPaymentOrderDetails.get(i).getAssetsName());

                        IpHistoryModel ipHistoryModel = new IpHistoryModel();
                        ipHistoryModel.setOano("1");
                        ipHistoryModel.setOaname("1");
                        ipHistoryModel.setOamodule("1");
                        JSONObject json = sapUtilService.createAssetsCodeForAppendFromSAP(ipInputModel, ipHistoryModel);

                        if (!json.has("subNumber")) {
                            executeResult.addErrorMessage("追加sap接口调用缺少返回值内容，请检查页面内容。");
                            return executeResult;
                        }
                        notPoPaymentOrderDetails.get(i).setSecondary(json.getString("subNumber"));
                    } else if ("forAppoint".equals(notPoPaymentOrderDetails.get(i).getCodingMode())) {

                        if (null == notPoPaymentOrderDetails.get(i).getMainAssetCode() && null == notPoPaymentOrderDetails.get(i).getSecondary()) {
                            executeResult.addErrorMessage("指定的主编码和次级编码出现空值！");
                            return executeResult;
                        }

                        String[] subNumber = notPoPaymentOrderDetails.get(i).getSecondary().split("-");
                        if (subNumber.length != 2) {
                            executeResult.addErrorMessage("次级编码指定区间有问题，请检查!");
                            return executeResult;
                        }
                        int length = Integer.parseInt(subNumber[1]) - Integer.parseInt(subNumber[0]) + 1;
                        if (length != notPoPaymentOrderDetails.get(i).getPayCount()) {
                            executeResult.addErrorMessage("次级编码指定区间次级编码个数有跟申请数量个数不匹配，请检查!");
                            return executeResult;
                        }

                        IpInputModel ipInputModel = new IpInputModel();
                        IpHistoryModel ipHistoryModel = new IpHistoryModel();
                        ipInputModel.setCompanycode(notPoPaymentOrder.getCompanyCode());
                        ipInputModel.setAnln1(notPoPaymentOrderDetails.get(i).getMainAssetCode());
                        ipInputModel.setSubnumber(notPoPaymentOrderDetails.get(i).getSecondary());
                        JSONObject jsonData = sapUtilService.existenceOfAssetsCodeFromSAP(ipInputModel, ipHistoryModel);

                        if (!jsonData.has("exist")) {
                            executeResult.addErrorMessage("指定sap接口调用 失败~、");
                            return executeResult;
                        } else if ("false".equals("" + jsonData.getString("exist"))) {
                            executeResult.addErrorMessage("指定的主次级编码未能查到数据！");
                            return executeResult;
                        }
                    }
                    result = notPoPaymentOrderDetailService.updatePaymentOrderDetailById(notPoPaymentOrderDetails.get(i));
                }

                if (!executeResult.isSuccess()) {
                    return executeResult;
                }

            } catch (Exception e) {

                executeResult.addErrorMessage("获取失败，请检查页面内容!");
                return executeResult;
            }
        }
        if (!executeResult.isSuccess()) {
            return executeResult;
        }

        executeResult = notPoPaymentOrderService.updateIsGetCodeByDocument(notPoPaymentOrder);
        return executeResult;
    }

    /**
     * 根据成本中心编码与公司编码查询预算体
     *
     * @param paymentOrder
     * @param request
     * @return executeResult
     */
    @RequestMapping("/searchPagerList")
    public String searchPagerList(Model model, Long offset, Long pageSize,
                                  HttpServletRequest request, DeptCostCenter deptCostCenter,
                                  String parentWindowId) {
        Pager<DeptCostCenter> pager = new Pager<DeptCostCenter>();
        pager.setUri(request.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<DeptCostCenter> listDepCostCenterPager = deptCostCenterService.searchPagerList(pager, deptCostCenter);
        listDepCostCenterPager.setUri(request.getRequestURI());
        listDepCostCenterPager.setParentWindowId(parentWindowId);
        model.addAttribute("listDepCostCenterPager", listDepCostCenterPager);
        model.addAttribute("parentWindowId", parentWindowId);
        model.addAttribute("deptCostCenter", deptCostCenter);

        return "purchase/show_depcostcenter_list";
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
    public ExecuteResult<NotPoPaymentOrder> clearVouch(String id, HttpServletRequest request) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();
        NotPoPaymentOrder notPoPaymentOrder = new NotPoPaymentOrder();
        notPoPaymentOrder.setCdocument(id);
        NotPoPaymentOrder searchPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        String username = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String usernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        boolean ifRollback = false;
        try {
            VoucherServiceStub stub = new VoucherServiceStub();
            VoucherServiceStub.ClearVouch_New clearVouch = new VoucherServiceStub.ClearVouch_New();

            clearVouch.setVouchcode(searchPaymentOrder.getCvoucherId());
            clearVouch.setBillcode(searchPaymentOrder.getCdocument());
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
    /*	*//**
     * 生成预付款凭证
     * @param paymentOrder
     * @param request
     * @return executeResult
     *//*
    @RequestMapping("/api/createPrePayment")
    @ResponseBody
    public ExecuteResult<PaymentOrder> createPrepayment(@RequestBody PaymentOrder paymentOrder, HttpServletRequest request) {
		ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
		String applyUsernameCode =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
		PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
		PaymentOrderModel paymentOrderSAP = paymentOrderService.searchPaymentOrderByOrderId(searchPaymentOrder);
		List<PaymentOrderDetailModel> paymentOrderDetailsSAP = paymentOrderDetailService.searchPaymentDetailByOrderId(searchPaymentOrder);
		paymentOrderSAP.setDocType("SA");
		UserInfo userInfo = new UserInfo();
		
		userInfo.setCemployeecode(searchPaymentOrder.getApplyUserCode());
		
		
		
		UserInfo searchSupplierCode = userInfoService.searchSupplierCode(userInfo);
		if (searchSupplierCode==null) {
			executeResult.addErrorMessage("没有找到["+searchPaymentOrder.getApplyUserCode()+"]对应得供应商信息！");
			return executeResult;
		}
		
		paymentOrderSAP.setAllocNumber(searchSupplierCode.getCsupplierCode());
		


		paymentOrderSAP.setLoginUserCode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim());
		
		JSONObject prePaymentVoucherFromSAP = sapUtilService.createPrePaymentVoucherFromSAP(paymentOrderSAP, paymentOrderDetailsSAP);
		if(!prePaymentVoucherFromSAP.has("prePaySapNo")){
			String error = prePaymentVoucherFromSAP.getString("error");
			executeResult.addErrorMessage(error);
			return executeResult;
		}
		String prepaymentDocumentNumber = prePaymentVoucherFromSAP.getString("prePaySapNo");
		if (""!=prepaymentDocumentNumber) {
			PaymentOrder paymentOrderBack = new PaymentOrder();
			paymentOrderBack.setPrepaymentDocumentNumber(prepaymentDocumentNumber);
			searchPaymentOrder.setPrepaymentDocumentNumber(prepaymentDocumentNumber);
			searchPaymentOrder.setIsManualPrepaymentDocument("N");
			searchPaymentOrder.setIsComingSAP("Y");
			paymentOrderService.updatePaymentOrder(searchPaymentOrder);
			paymentOrderDetailService.updatePaymentOrderDetail(searchPaymentOrder);
			executeResult.setResult(paymentOrderBack);
		}
		else {
			executeResult.addErrorMessage(prePaymentVoucherFromSAP.getString("error"));
		}
		return executeResult;
	}
	*//**
     * 发票检验
     * @param paymentOrder
     * @param request
     * @return executeResult
     *//*
    @RequestMapping("/api/createInvoiceInspection")
    @ResponseBody
    public ExecuteResult<PaymentOrder> createInvoiceInspection(@RequestBody InvoiceInspectionModel invoiceInspectionModel, HttpServletRequest request) {
		ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
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
		String []details = invoiceInspectionModel.getCheckids().split(",");
		for (int i = 0; i < details.length; i++) {
			ItDataModel itDataModel  = new ItDataModel();
			PurchaseCommon purchaseCommon = purchaseCommonService.getPurchaseCommonById(Long.parseLong(details[i]));
			CheckAcceptanceDetail checkAcceptanceDetail = checkAcceptanceDetailService.getCheckAcceptanceDetailById(Long.parseLong(purchaseCommon.getCheckAcceptDetailId()));
			itDataModel.setEbeln(purchaseOrder.getPurchaseOrderSapId());
			itDataModel.setEbelp(StringUtils.leftPad(String.valueOf(purchaseCommon.getItemNo()),5,"0"));
			itDataModel.setMjahr(checkAcceptanceDetail.getEpMatdocumentyear());
			itDataModel.setMblnr(checkAcceptanceDetail.getEpMaterialdocu());
			itDataModel.setNetwj(purchaseCommon.getOrderNetMoney());
			itDataModel.setNetwr(purchaseCommon.getOrderMoney());
			itDataModel.setNetwz(purchaseCommon.getOrderMoney().subtract(purchaseCommon.getOrderNetMoney()));
			itDataModel.setMwskz(searchPaymentOrder.getTaxRateCode());
			itDataModel.setAnln1(purchaseCommon.getSapMainAssetCode());
			itDataModel.setAnln2(purchaseCommon.getSapSecAssetCode());
			itDataModel.setCurrency(purchaseOrder.getCurrencyCode());
			itDataModel.setNetwj(invoiceInspection.get);
			itDataModels.add(itDataModel);
		}
		JSONObject billsCheck = sapUtilService.createBillsCheck(itDataModels,eaddressdata);
		String paymentVoucherNumber = billsCheck.getString("eBelnr");
		String invoiceNo = billsCheck.getString("ecBelnr");
		String result = billsCheck.getString("result");
     
		if (""!=paymentVoucherNumber&&"S".equals(result)) {
			//保存发票检验信息
			invoiceInspectionService.saveInvoiceInspection(invoiceInspectionModel.getInvoiceInspection(), invoiceInspectionModel.getCheckids());
			PaymentOrder paymentOrderBack = new PaymentOrder();
			paymentOrderBack.setPaymentVoucherNumber(paymentVoucherNumber);
			paymentOrderBack.setInvoiceNo(invoiceNo);
			searchPaymentOrder.setPaymentVoucherNumber(paymentVoucherNumber);
			searchPaymentOrder.setInvoiceNo(invoiceNo);
			searchPaymentOrder.setIsManualPrepaymentDocument("N");
			searchPaymentOrder.setIsComingSAP("Y");
			String[] splitsublist = invoiceInspectionModel.getCheckids().split(",");
			paymentOrderService.updatePaymentOrder(searchPaymentOrder);
			for (int i = 0; i < splitsublist.length; i++) {
				Long commonId = Long.parseLong(splitsublist[i]);
				PurchaseCommon purchaseCommon = new PurchaseCommon();
				purchaseCommon.setId(commonId);
				purchaseCommonService.updatePurchaseCommon(purchaseCommon);
			}
			executeResult.setResult(paymentOrderBack);
		}
		else {
			executeResult.addErrorMessage("发票检验失败！");
		}
		return executeResult;
	}
	*//**
     * 生成验收款凭证
     * @param paymentOrder
     * @param request
     * @return executeResult
     *//*
    @RequestMapping("/api/createReceiptVoucher")
    @ResponseBody
    public ExecuteResult<PaymentOrder> createReceiptVoucher(@RequestBody PaymentOrder paymentOrder, HttpServletRequest request) {
		ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
		PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
	     String Username =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
		 String UsernameCode =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
		 boolean ifRollback = false;
		try {
			VoucherServiceStub stub = new VoucherServiceStub();
			VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_Pay pay = new VoucherServiceStub.LinkSAP_BAPI_ToVouchInfo_Pay();
			pay.setBillcode(paymentOrder.getCdocument());
			pay.setCusercode(UsernameCode);
			pay.setCusername(Username);
		  
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
	*//**
     * 再次清账
     * @param paymentOrder
     * @param request
     * @return executeResult
     *//*
    @RequestMapping("/api/clearVouch")
    @ResponseBody
    public ExecuteResult<PaymentOrder> clearVouch(String id, HttpServletRequest request) {
		ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setCdocument(id);
		PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
	     String Username =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
		 String UsernameCode =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
		 boolean ifRollback = false;
		try {
			VoucherServiceStub stub = new VoucherServiceStub();
			VoucherServiceStub.ClearVouch_New clearVouch = new VoucherServiceStub.ClearVouch_New();
			
			clearVouch.setVouchcode(searchPaymentOrder.getVoucherId());
			clearVouch.setBillcode(paymentOrder.getCdocument());
			clearVouch.setCusercode(UsernameCode);
			clearVouch.setCusername(Username);
		  
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
	//传sap付款凭证(预付、验收、尾款)
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
	}
	@RequestMapping("/api/denyPayment")
    @ResponseBody
    public ExecuteResult<PaymentOrder> denyPayment(@RequestBody PaymentOrderModel paymentOrder, HttpServletRequest request) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        
        if (StringUtils.isBlank(paymentOrder.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }
        
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
        String applyUsername =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        searchPaymentOrder.setLoginUserName(applyUsername);
		String applyUsernameCode =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
		searchPaymentOrder.setLoginUserCode(applyUsernameCode);
		
		User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
		if(userDeptInfo==null){
			executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
		}
		searchPaymentOrder.setUserDepCode(userDeptInfo.getDepartmentCode());
		
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrder.getIsMail())) {
        	searchPaymentOrder.setIsMail("Y");
        } else{
        	searchPaymentOrder.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrder.getIsPhoneMessage())) {
        	searchPaymentOrder.setIsPhoneMessage("Y");
        } else{
        	searchPaymentOrder.setIsPhoneMessage("N");
        }
        executeResult = paymentOrderService.denyWorkFlow(searchPaymentOrder);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        PaymentOrder searchPaymentOrderByOrderId = paymentOrderService.searchPaymentOrderByOrderId(searchPaymentOrder);
        remindedService.BellReminder(searchPaymentOrderByOrderId.getCnexthandlercode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsMail(), searchPaymentOrderByOrderId.getIsPhoneMessage());
        ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();
        executeResult.setSuccessMessage("审批成功");
		if (WARNSTATE.YES.getWarnstate().equals(paymentOrder.getIsPortrait())) {
			result = paymentOrderService.backBar(searchPaymentOrder);
        	if (!result.isSuccess()&&result.getErrorMessages()!=null) {
        		executeResult.setSuccessMessage("审批成功,"+result.getErrorMessages().get(0));
            }
            else {
            	executeResult.setSuccessMessage("审批成功,"+result.getSuccessMessage());
            	paymentOrderService.updatePaymentOrderByCdocument(paymentOrder);
            }
        } 
        return executeResult;
    }

	@RequestMapping("/api/dropPayment")
    @ResponseBody
    public ExecuteResult<PaymentOrder> dropPayment(@RequestBody PaymentOrderModel paymentOrder, HttpServletRequest request) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        
        if (StringUtils.isBlank(paymentOrder.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }
        PaymentOrder searchPaymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
        
        searchPaymentOrder.setIdea(paymentOrder.getIdea());
        searchPaymentOrder.setIsMail(paymentOrder.getIsMail());
        searchPaymentOrder.setIsPhoneMessage(paymentOrder.getIsPhoneMessage());
        
        // 获得当前登录人  部门 
        String applyUsername =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        searchPaymentOrder.setLoginUserName(applyUsername);
		String applyUsernameCode =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
		searchPaymentOrder.setLoginUserCode(applyUsernameCode);
		
		User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
		if(userDeptInfo==null){
			executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
		}
		searchPaymentOrder.setUserDepCode(userDeptInfo.getDepartmentCode());
		
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrder.getIsMail())) {
        	searchPaymentOrder.setIsMail("Y");
        } else{
        	searchPaymentOrder.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(searchPaymentOrder.getIsPhoneMessage())) {
        	searchPaymentOrder.setIsPhoneMessage("Y");
        } else{
        	searchPaymentOrder.setIsPhoneMessage("N");
        }
        executeResult = paymentOrderService.dropWorkFlow(searchPaymentOrder);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        PaymentOrder searchPaymentOrderByOrderId = paymentOrderService.searchPaymentOrderByOrderId(searchPaymentOrder);
        remindedService.BellReminder(searchPaymentOrderByOrderId.getCnexthandlercode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsMail(), searchPaymentOrderByOrderId.getIsPhoneMessage());

        return executeResult;
    }
	
	@RequestMapping("/updatePaymentOrder")
	public String updatePaymentOrder(Model model, HttpServletRequest req) {

		String cdocument = req.getParameter("id");
		String ids ="";
		int nper=0;
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setCdocument(cdocument);
		paymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
		//查询新增所选的明细
		List<PaymentOrderDetailModel> paymentOrderDetailModels = paymentOrderDetailService.searchPaymentDetailByOrderId(paymentOrder);
		for (PaymentOrderDetailModel paymentOrderDetailModel : paymentOrderDetailModels) {
			ids = ids+paymentOrderDetailModel.getPurchaseDetailId()+",";
			nper = paymentOrderDetailModel.getNper();
		}
		ids = ids.substring(0, ids.length()-1);
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
		model.addAttribute("paymentOrder",paymentOrder);
		model.addAttribute("paymentOrderDetailModels",paymentOrderDetailModels1);
		model.addAttribute("paymentPaymentMappingModels",paymentPaymentMappingModels);
		model.addAttribute("scanLocations", scanLocations);
		model.addAttribute("paymentTerms", paymentTerms);
		return "purchase/payment_order_update";
	}
	*//**
     * 导出
     *
     * @param model
     * @param request
     * @param response
     * @param paymentOrderModel
     * @return
     *//*
    @RequestMapping(value = "/Export", method = RequestMethod.GET)
	@ResponseBody
	public void export(ModelMap model, HttpServletRequest request,
			HttpServletResponse response,PaymentOrderModel paymentOrderModel){
		
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
	*//**
     * 删除
     *
     * @param id
     * @return
     *//*
    @RequestMapping("/api/deletePayment")
	@ResponseBody
	public ExecuteResult<PaymentOrder> deletePaymentOrder(HttpServletRequest request,String id) {
		ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setCdocument(id);
		paymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setCdocument(paymentOrder.getPurchaseOrderId());
		if (!"等待审批".equals(paymentOrder.getCapprovalstate())&&!"退回".equals(paymentOrder.getCapprovalstate())&&"N".equals(paymentOrder.getDraft())) {
			result.addErrorMessage("此单据已审批，无法删除！");
			return result;
		} 
		String usernameCode =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
		if (!usernameCode.equals(paymentOrder.getApplyUserCode())) {
			result.addErrorMessage("您不是单据申请人，无法删除！");
			return result;
		}
		paymentOrderService.delPaymentOrderByCdocument(paymentOrder);
		//删除预付冲减明细
		paymentPaymentMappingService.deletePaymentPaymentMapping(paymentOrder);
		//删除审批流
		workFlowService.deleteGarbegeWorkFlowData(paymentOrder.getCdocument(), Env.getProperty("payment.info.type.id"));
		ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
		executeResult = paymentOrderService.deleteBar(paymentOrder);
    	if (!executeResult.isSuccess()&&executeResult.getErrorMessages()!=null) {
        	result.setSuccessMessage("删除成功,"+executeResult.getErrorMessages().get(0));
        }
        else {
        	result.setSuccessMessage("删除成功,"+executeResult.getSuccessMessage());
        }
    	//查询此采购订单最大付款期数
        PaymentTerm paymentMaxTerm = paymentTermService.searchMaxTermByOrderId(purchaseOrder);
        //判断如果最后一期所有明细都选择最后一次付款则系统自动关闭订单
 	   if (paymentMaxTerm.getNper() == paymentOrder.getNper()&&"N".equals(paymentOrder.getDraft())) {
 			if (paymentOrderDetailService.completePayment(paymentOrder)==0) {
 				purchaseOrder.setIsCloseForPayment("已关闭");
 				purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
 			}
 			else {
 				purchaseOrder.setIsCloseForPayment("未关闭");
 				purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
 			}
 		}
		return result;
	}
	*//**
     * 影像调阅
     *
     * @param cdocument
     * @return
     *//*
    @RequestMapping("/api/checkBar")
	@ResponseBody
	public ExecuteResult<PaymentOrder> checkBar(String cdocument) {
		ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setCdocument(cdocument);
		result = paymentOrderService.checkBar(paymentOrder);
    	return result;
	}
	*//**
     * 历史审批记录
     * @param model
     * @param offset
     * @param pageSize
     * @param document
     * @param typeId
     * @param request
     * @return
     *//*
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
	*//**
     * 打印页面
     * @param model
     * @param request
     * @param typeId
     * @return
     *//*
    @RequestMapping("/api/printOrder")
	public String printOrder(Model model, HttpServletRequest request, String typeId) {

		String cdocument = request.getParameter("id");
		NotPoPaymentOrder notPoPaymentOrder = new NotPoPaymentOrder();
		notPoPaymentOrder.setCdocument(cdocument);
		notPoPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);

		//List<NotPoPaymentOrder> notPoPaymentOrders = notPoPaymentOrderService.searchNotPoPaymentByOrderId(notPoPaymentOrder);
		boolean ifRollback = false;
		String url = "";
		try {
			BarServiceStub stub = new BarServiceStub();
			BarServiceStub.CreateBarCode createBarCode = new BarServiceStub.CreateBarCode();

			createBarCode.setMrmcode(notPoPaymentOrder.getCdocument());

			BarServiceStub.CreateBarCodeResponse createBarCodeResponse = stub.createBarCode(createBarCode);
			if (!"".equals(createBarCodeResponse.getCreateBarCodeResult())) {
				url = createBarCodeResponse.getCreateBarCodeResult().substring(1, createBarCodeResponse.getCreateBarCodeResult().length());
				url = createBarCodeResponse.getCreateBarCodeResult();
			}

//			if (!"0;".equals(deleteBarResponse.getErrMessage())) {
//
//			}

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

		//List<PaymentPaymentMapping> paymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingById(notPoPaymentOrder);
		//**
//         * 获取此付款单发票检验明细信息
//				*//*
//				InvoiceInspection invoiceInspection = new InvoiceInspection();
//		invoiceInspection.setPaymentOrderDocument(cdocument);
//		invoiceInspection = invoiceInspectionService.getInvoiceInspection(invoiceInspection);
//		List<InvoiceInspectionDetail> invoiceInspectionDetails = invoiceInspectionDetailService.searchInvoiceInspectionDetail(invoiceInspection);
//		*//**
     //         * 获取审批流流程的历史记录
     //				*//*
//				List<ApproveHistoryCheckPrint> approveHistoryCheckPrints = checkAcceptanceService.getApproveHistory(cdocument, typeId);
//		Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
//		pager.setOffset(null == offset ? pager.getOffset() : offset);
//		pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
//
//		Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(cdocument, typeId, pager);
//		appHistoryPager.setUri(request.getRequestURI());
//		//获取上传的文件列表
//		List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
//		paymentOrder.setListFile(listFile);
//		model.addAttribute("approveHistoryCheckPrints", approveHistoryCheckPrints);

		model.addAttribute("url", url);
		model.addAttribute("notPoPaymentOrder", notPoPaymentOrder);
//		model.addAttribute("paymentOrderDetailModels", paymentOrderDetailModels);
//		model.addAttribute("paymentPaymentMappingModels", paymentPaymentMappingModels);
//		model.addAttribute("invoiceInspection", invoiceInspection);
//		model.addAttribute("invoiceInspectionDetails", invoiceInspectionDetails);
		return "purchase/notpo_payment_prepay_stamp";
	}
	//删除
	@RequestMapping("/api/deleteNotPoPayment")
	@ResponseBody
	public ExecuteResult<NotPoPaymentOrder> deletePaymentOrder(HttpServletRequest request, String id) {
		ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();
		NotPoPaymentOrder notPoPaymentOrder = new NotPoPaymentOrder();
		notPoPaymentOrder.setCdocument(id);
		notPoPaymentOrderService.deleteNoPoPaymentOrder(notPoPaymentOrder);
		result.setSuccessMessage("删除成功");
//
//		paymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
//		PurchaseOrder purchaseOrder = new PurchaseOrder();
//		purchaseOrder.setCdocument(paymentOrder.getPurchaseOrderId());
//		if (!"等待审批".equals(paymentOrder.getCapprovalstate())&&!"退回".equals(paymentOrder.getCapprovalstate())&&"N".equals(paymentOrder.getDraft())) {
//			result.addErrorMessage("此单据已审批，无法删除！");
//			return result;
//		}
//		String usernameCode =	request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
//		if (!usernameCode.equals(paymentOrder.getApplyUserCode())) {
//			result.addErrorMessage("您不是单据申请人，无法删除！");
//			return result;
//		}
//		paymentOrderService.delPaymentOrderByCdocument(paymentOrder);
//		//删除预付冲减明细
//		paymentPaymentMappingService.deletePaymentPaymentMapping(paymentOrder);
//		//删除审批流
//		workFlowService.deleteGarbegeWorkFlowData(paymentOrder.getCdocument(), Env.getProperty("payment.info.type.id"));
//		ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
//		executeResult = paymentOrderService.deleteBar(paymentOrder);
//		if (!executeResult.isSuccess()&&executeResult.getErrorMessages()!=null) {
//			result.setSuccessMessage("删除成功,"+executeResult.getErrorMessages().get(0));
//		}
//		else {
//			result.setSuccessMessage("删除成功,"+executeResult.getSuccessMessage());
//		}
//		//查询此采购订单最大付款期数
//		PaymentTerm paymentMaxTerm = paymentTermService.searchMaxTermByOrderId(purchaseOrder);
//		//判断如果最后一期所有明细都选择最后一次付款则系统自动关闭订单
//		if (paymentMaxTerm.getNper() == paymentOrder.getNper()&&"N".equals(paymentOrder.getDraft())) {
//			if (paymentOrderDetailService.completePayment(paymentOrder)==0) {
//				purchaseOrder.setIsCloseForPayment("已关闭");
//				purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
//			}
//			else {
//				purchaseOrder.setIsCloseForPayment("未关闭");
//				purchaseOrderService.updateClosePurchaseOrderForPayment(purchaseOrder);
//			}
//		}
		return result;
	}
	
	@RequestMapping("/showApproveHistory")
	public String showApproveHistory(Model model, Long offset, Long pageSize, String document, String typeId, HttpServletRequest request) {
		Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
		pager.setOffset(null == offset ? pager.getOffset() : offset);
		pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        	createBarCode.setMrmcode(paymentOrder.getCdocument());

        	BarServiceStub.CreateBarCodeResponse createBarCodeResponse = stub.createBarCode(createBarCode);
        	if (!"".equals(createBarCodeResponse.getCreateBarCodeResult())) {
        		url = createBarCodeResponse.getCreateBarCodeResult().substring(1, createBarCodeResponse.getCreateBarCodeResult().length());
        		url = createBarCodeResponse.getCreateBarCodeResult();
        	}

        	if (!"0;".equals(deleteBarResponse.getErrMessage())) {

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

		List<PaymentPaymentMapping> paymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingById(paymentOrder);
		*//**
     * 获取此付款单发票检验明细信息
     *//*
        InvoiceInspection invoiceInspection = new InvoiceInspection();
		invoiceInspection.setPaymentOrderDocument(cdocument);
		invoiceInspection = invoiceInspectionService.getInvoiceInspection(invoiceInspection);
		List<InvoiceInspectionDetail> invoiceInspectionDetails = invoiceInspectionDetailService.searchInvoiceInspectionDetail(invoiceInspection);
		*//**
     * 获取审批流流程的历史记录
     *//*
        List<ApproveHistoryCheckPrint> approveHistoryCheckPrints = checkAcceptanceService.getApproveHistory(cdocument, typeId);
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

		Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(document, typeId, pager);
		appHistoryPager.setUri(request.getRequestURI());

		model.addAttribute("appHistoryPager", appHistoryPager);

		return "workflow/approve_history";
	}

//	@RequestMapping("/showNotPoPaymentOrder")
//	public String showPaymentOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {
//
//		String cdocument = req.getParameter("id");
//		PaymentOrder paymentOrder = new PaymentOrder();
//		paymentOrder.setCdocument(cdocument);
//		paymentOrder = paymentOrderService.searchPaymentOrderByOrderId(paymentOrder);
//
//		List<PaymentOrderDetailModel> paymentOrderDetailModels = paymentOrderDetailService.searchPaymentDetailByOrderId(paymentOrder);
////		String ids="";
////		for (PaymentOrderDetailModel podm : podms) {
////			ids += podm.getPurchaseDetailId()+",";
////		}
////		ids = ids.substring(0,ids.length()-1);
////
////		PaymentOrderDetailModel paymentOrderDetailModel = new PaymentOrderDetailModel();
////		paymentOrderDetailModel.setIds(ids);
////		paymentOrderDetailModel.setPurchaseOrderId(paymentOrder.getPurchaseOrderId());
////		paymentOrderDetailModel.setNper(paymentOrder.getNper());
////		List<PaymentOrderDetailModel> paymentOrderDetailModels = paymentOrderDetailService.searchPurchaseByOrderIdNper(paymentOrderDetailModel);
//
//		List<PaymentPaymentMapping> paymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingById(paymentOrder);
//		*//**
     //         * 获取此付款单发票检验明细信息
     //				*//*
////				InvoiceInspection invoiceInspection = new InvoiceInspection();
////		invoiceInspection.setPaymentOrderDocument(cdocument);
////		invoiceInspection = invoiceInspectionService.getInvoiceInspection(invoiceInspection);
////		List<InvoiceInspectionDetail> invoiceInspectionDetails = invoiceInspectionDetailService.searchInvoiceInspectionDetail(invoiceInspection);
////		*//**
     ////         * 获取审批流流程的历史记录
     ////				*//*
////				Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
////		pager.setOffset(null == offset ? pager.getOffset() : offset);
////		pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
//
//		Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(cdocument, typeId, pager);
//		appHistoryPager.setUri(req.getRequestURI());
//		//获取上传的文件列表
//		List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
//		paymentOrder.setListFile(listFile);
//		model.addAttribute("appHistoryPager", appHistoryPager);
//
//
//		model.addAttribute("paymentOrder",paymentOrder);
//		model.addAttribute("paymentOrderDetailModels",paymentOrderDetailModels);
//		model.addAttribute("paymentPaymentMappingModels",paymentPaymentMappingModels);
////		model.addAttribute("invoiceInspection",invoiceInspection);
////		model.addAttribute("invoiceInspectionDetails",invoiceInspectionDetails);
//		return "purchase/payment_order_detail";
//	}
        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(cdocument, typeId, pager);
        appHistoryPager.setUri(request.getRequestURI());
        //获取上传的文件列表
  		List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
  		paymentOrder.setListFile(listFile);
        model.addAttribute("approveHistoryCheckPrints", approveHistoryCheckPrints);

        model.addAttribute("url",url);
		model.addAttribute("paymentOrder",paymentOrder);
		model.addAttribute("paymentOrderDetailModels",paymentOrderDetailModels);
		model.addAttribute("paymentPaymentMappingModels",paymentPaymentMappingModels);
		model.addAttribute("invoiceInspection",invoiceInspection);
		model.addAttribute("invoiceInspectionDetails",invoiceInspectionDetails);
		return "purchase/payment_order_stamp";
	}*/

    /**
     * 修改方法
     *
     * @param model
     * @param req
     * @return
     */
    @RequestMapping("/updatenotPaymentOrder")
    public String updatePaymentOrder(Model model, HttpServletRequest req) {
        // 币种
        List<UserInfo> currencylist = userInfoService.searchCurrencyInfo();
        model.addAttribute("currencylist", currencylist);
        //获得扫描地
        List<ScanLocation> scanLocations = scanLocationService.findAll();
        //单位
        List<AssetUnit> assetUnitList = assetUnitService.getAssetUnitList();
        model.addAttribute("assetUnitList", assetUnitList);
        //获得办公地点
        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        model.addAttribute("addresslist", addresslist);
        model.addAttribute("scanLocations", scanLocations);
        String cdocument = req.getParameter("id");
        NotPoPaymentOrder npo = new NotPoPaymentOrder();
        npo.setCdocument(cdocument);
        npo = notPoPaymentOrderService.searchPaymentOrderByOrderId(npo);

        List<FileUpload> listFile = fileUploadServicel.getFilesByDocument(cdocument);
        npo.setListFile(listFile);
        //尾款查询所有待付的验收单
        List<NotPoPaymentOrder> acceptOrderNumbers = notPoPaymentOrderService.searchPoPaymentOrders(npo);
        model.addAttribute("acceptOrderNumbers", acceptOrderNumbers);
        model.addAttribute("notPoPaymentOrder", npo);
        if ("验收款".equals(npo.getPaymentType())) {
            NotPoPaymentOrderDetail notPoPaymentOrderDetail = new NotPoPaymentOrderDetail();
            notPoPaymentOrderDetail.setNotPoPaymentOrderId(npo.getCdocument());
            List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails = notPoPaymentOrderDetailService.searchPaymentOrderByDocument(notPoPaymentOrderDetail);
            model.addAttribute("notPoPaymentOrderDetails", notPoPaymentOrderDetails);
            PaymentOrder paymentOrder = new PaymentOrder();
            paymentOrder.setCdocument(cdocument);
            List<PaymentPaymentMapping> paymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingById(paymentOrder);
            model.addAttribute("paymentPaymentMappingModels", paymentPaymentMappingModels);
            return "purchase/notpo_payment_forcheck_update";
        }
        return "purchase/notpo_payment_update_notcheck";
    }

    /**
     * 修改更新预付款、尾款
     *
     * @param notPaymentOrderWrapper
     * @param req
     * @return
     */
    @RequestMapping("/api/updatePrePayment")
    @ResponseBody
    public ExecuteResult<NotPoPaymentOrder> saveUpdateDemand(@RequestBody NotPaymentOrderWrapper notPaymentOrderWrapper, HttpServletRequest req) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();
        //表头信息
        NotPoPaymentOrder notPoPaymentOrder = notPaymentOrderWrapper.getNotPoPaymentOrder();
        List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails = notPaymentOrderWrapper.getNotPoPaymentOrderDetails();
        List<PaymentPaymentMappingModel> paymentPaymentMappingModels = notPaymentOrderWrapper.getPaymentPaymentMappingModels();
        NotPoPaymentOrderDetail notPoPaymentOrderDetail = new NotPoPaymentOrderDetail();
        notPoPaymentOrderDetail.setNotPoPaymentOrderId(notPoPaymentOrder.getCdocument());
        List<NotPoPaymentOrderDetail> oldNotPoPaymentOrderDetails = notPoPaymentOrderDetailService.searchPaymentOrderByDocument(notPoPaymentOrderDetail);
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setCdocument(notPoPaymentOrder.getCdocument());
        List<PaymentPaymentMappingModel> oldPaymentPaymentMappingModels = paymentPaymentMappingService.searchPaymentPaymentMappingForNotPo(paymentOrder);
        NotPoPaymentOrder oldNotPoPaymentOrder = new NotPoPaymentOrder();
        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        //修改前的单据
        try {
            if ("N".equals(notPoPaymentOrder.getDraft())) {
                notPoPaymentOrder.setCommitType("N");
            } else {
                notPoPaymentOrder.setCommitType("Y");
            }
            notPoPaymentOrder.setCompanyCode(notPoPaymentOrder.getCompany().split("-")[0]);
            notPoPaymentOrder.setCompanyName(notPoPaymentOrder.getCompany().split("-")[1]);
            notPoPaymentOrder.setApplyUser(applyUsername);
            notPoPaymentOrder.setCreateUserName(applyUsername);
            notPoPaymentOrder.setUpdateUserName(applyUsername);
            notPoPaymentOrder.setApplyUserCode(applyUsernameCode);
            notPoPaymentOrder.setCreateBy(applyUsernameCode);
            notPoPaymentOrder.setUpdateBy(applyUsernameCode);
            notPoPaymentOrder.setApplyDate(new Date());
            notPoPaymentOrder.setCreateDate(new Date());
            notPoPaymentOrder.setUpdateDate(new Date());
            notPoPaymentOrder.setIamoney(notPoPaymentOrder.getApplyMoney());
            oldNotPoPaymentOrder = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
            notPoPaymentOrder.setPurchaseManagerCode(oldNotPoPaymentOrder.getPurchaseManagerCode());
            notPoPaymentOrder.setPurchaseManagerName(oldNotPoPaymentOrder.getPurchaseManagerName());
            notPoPaymentOrder.setStatus(oldNotPoPaymentOrder.getStatus());
            executeResult = notPoPaymentOrderService.updateNotPoPaymentOrder(notPoPaymentOrder, notPoPaymentOrderDetails, paymentPaymentMappingModels);
            if (!executeResult.isSuccess()) {
                //如果失败回滚单据
                notPoPaymentOrderService.updateNotPoPaymentOrder(oldNotPoPaymentOrder, oldNotPoPaymentOrderDetails, oldPaymentPaymentMappingModels);
                return executeResult;
            }
            if ("N".equals(notPoPaymentOrder.getDraft())) { //如果不是草稿，保定审批流
                executeResult = notPoPaymentOrderService.binDingFlow(notPoPaymentOrder);
                //如果绑定失败
                if (!executeResult.isSuccess()) {
                    //删除审批流
                    workFlowService.deleteGarbegeWorkFlowData(notPoPaymentOrder.getCdocument(), Env.getProperty("notpayment.info.type.id"));
                    notPoPaymentOrderService.updateNotPoPaymentOrder(oldNotPoPaymentOrder, oldNotPoPaymentOrderDetails, oldPaymentPaymentMappingModels);
                    executeResult.addErrorMessage("修改失败");
                    return executeResult;
                }
                NotPoPaymentOrder searchPaymentOrderByOrderId = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder); //为了获得下一审批人
                remindedService.BellReminder(searchPaymentOrderByOrderId.getNextHandlerCode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            }
        } catch (Exception e) {
            LOGGER.error("=================NotPoPaymentController.saveUpdateDemand()异常=======================", e);
            executeResult.addErrorMessage("修改失败");
            return executeResult;
        }
        executeResult.setSuccessMessage("修改成功");

        NotPoPaymentOrder searchPaymentOrderByOrderId = notPoPaymentOrderService.searchPaymentOrderByOrderId(notPoPaymentOrder);
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsMail())) {
            searchPaymentOrderByOrderId.setIsMail("Y");
        } else {
            searchPaymentOrderByOrderId.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(notPoPaymentOrder.getIsPhoneMessage())) {
            searchPaymentOrderByOrderId.setIsPhoneMessage("Y");
        } else {
            searchPaymentOrderByOrderId.setIsPhoneMessage("N");
        }
        if (StringUtils.isNotBlank(searchPaymentOrderByOrderId.getNextHandlerCode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchPaymentOrderByOrderId.getNextHandlerCode(), searchPaymentOrderByOrderId.getCdocument(), searchPaymentOrderByOrderId.getIsPhoneMessage(), searchPaymentOrderByOrderId.getIsMail());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("修改成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        }
        notPoPaymentOrderService.saveBar(notPoPaymentOrder);
        return executeResult;
    }
}
