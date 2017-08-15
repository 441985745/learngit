package com.opple.fa.purchase.webapp.controller;

import com.opple.fa.config.entity.AssetClassification;
import com.opple.fa.config.entity.AssetUnit;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.Department;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.entity.UserRole;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AssetClassificationService;
import com.opple.fa.config.service.AssetUnitService;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.config.service.UserRoleService;
import com.opple.fa.purchase.entity.ApplyDemandMapping;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.entity.OutBudgetException;
import com.opple.fa.purchase.entity.WARNSTATE;
import com.opple.fa.purchase.model.ApplyDemandMappingModel;
import com.opple.fa.purchase.model.ApplyOrderModel;
import com.opple.fa.purchase.model.ApplyOrderWrapper;
import com.opple.fa.purchase.model.ApproveHistoryCheckPrint;
import com.opple.fa.purchase.model.DemandOrderDetailModel;
import com.opple.fa.purchase.service.ApplyOrderDetailService;
import com.opple.fa.purchase.service.ApplyOrderService;
import com.opple.fa.purchase.service.CheckAcceptanceService;
import com.opple.fa.purchase.service.DemandOrderDetailService;
import com.opple.fa.purchase.service.DemandOrderService;
import com.opple.fa.purchase.service.PurchaseOrderService;
import com.opple.fa.sap.model.IpHistoryModel;
import com.opple.fa.sap.model.IpInputModel;
import com.opple.fa.sap.service.SapUtilService;
import com.opple.fa.security.service.impl.ExportApplyTemplate;
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
import org.apache.commons.lang.StringUtils;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/apply")
public class ApplyController {
    public static final String APPLY_INFO_TYPE_ID = "77011";
    private static final Logger LOGGER = LoggerFactory.getLogger(DemandController.class);
    @Resource
    private ApplyOrderService applyOrderService;
    @Resource
    private DemandOrderDetailService demandOrderDetailService;
    @Resource
    private DemandOrderService demandOrderService;
    @Resource
    private ApplyOrderDetailService applyOrderDetailService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private PurchaseOrderService purchaseOrderService;
    @Resource
    private UserService userService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private AssetClassificationService assetClassificationService;
    @Resource
    private SapUtilService sapUtilService;
    @Resource
    private RemindedService remindedService;
    @Resource
    private AttchConfigService attchConfigService;
    @Resource
    private AssetUnitService assetUnitService;
    @Resource
    private CheckAcceptanceService checkAcceptanceService;
    /**
     * 模糊查询采购申请
     */
    @RequestMapping("/searchApply")
    public String searchApply(Model model, HttpServletRequest req, Long offset, Long pageSize, ApplyOrder applyOrder) {
        ApplyOrderModel applyOrderModel = new ApplyOrderModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 单据号
        String strApplyOrderId = req.getParameter("txtApplyOrderId");
        try {
            // 开始时间
            applyOrderModel.setApplyDateStart(sdf.parse(req.getParameter("txtBeginDate")));
        } catch (Exception e) {
            applyOrderModel.setApplyDateStart(null);
        }
        try {
            // 结束时间
            applyOrderModel.setApplyDateEnd(sdf.parse(req.getParameter("txtEndDate")));
        } catch (Exception e) {
            applyOrderModel.setApplyDateEnd(null);
        }
        String strApplyUser = req.getParameter("txtApplyUser");
        String strCapprovalstates = req.getParameter("ckCapprovalstate");
        String strDraft = req.getParameter("ckDraft");
        String strOrderStatus = req.getParameter("ckOrderStatus");
        String strAssetType = req.getParameter("txtAssetType");
        String assetsName = req.getParameter("assetsName");
        String demandId = req.getParameter("demandId");
        String companyCode = req.getParameter("companyCode");
        String costCenterCode = req.getParameter("costCenterCode");
        applyOrderModel.setCostCenterCode(null == costCenterCode ? null : costCenterCode.trim());
        applyOrderModel.setCompanyCode(null == companyCode ? null : companyCode.trim());
        applyOrderModel.setDemandId(null == demandId ? null : demandId.trim());
        applyOrderModel.setAssetsName(null == assetsName ? null : assetsName.trim());
        applyOrderModel.setCdocument(null == strApplyOrderId ? null : strApplyOrderId.trim());
        applyOrderModel.setApplyUser(null == strApplyUser ? null : strApplyUser.trim());
        applyOrderModel.setCapprovalstate(null == strCapprovalstates ? null : strCapprovalstates.trim());
        applyOrderModel.setDraft(null == strDraft ? "N" : strDraft.trim());
        applyOrderModel.setOrderStatus(null == strOrderStatus ? null : strOrderStatus.trim());
        applyOrderModel.setAssetType(strAssetType);
        applyOrderModel.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        //设置当前登录人编码
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        applyOrderModel.setLoginUserCode(loginUserCode);
        Pager<ApplyOrder> pager = new Pager<ApplyOrder>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<ApplyOrder> applyOrderPager = applyOrderService.searchApplyOrder(pager, applyOrderModel);
        applyOrderPager.setUri(req.getRequestURI());
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUserCode);
        List<UserInfo> searchCauthorizerCode = userInfoService.searchCauthorizerCode(userInfo);
        List<ApplyOrder> records = applyOrderPager.getRecords();
        //遍历 如果有授权，则把明细是否授权字段变为Y
        for (ApplyOrder applyOrder2 : records) {
            for (UserInfo userInfo2 : searchCauthorizerCode) {
                if (applyOrder2.getApplyUserCode().equals(userInfo2.getCauthorizerCode())) {
                    applyOrder2.setIsAuthorize(STATUS.YES.getStatus());
                }
            }
        }
        applyOrderPager.setRecords(records);
        model.addAttribute("applyOrderPager", applyOrderPager);
        model.addAttribute("loginUserCode", applyOrderModel.getLoginUserCode());
        List<UserInfo> companys = userInfoService.searchAllCompany();
        model.addAttribute("companys", companys);
        return "purchase/apply_order_list";
    }
    /**
     * 采购申请查看页面
     */
    @RequestMapping("/showApplyOrder")
    public String showApplyOrder(Model model, HttpServletRequest request, Long offset, Long pageSize, String typeId) {
        // 单据号
        String strId = request.getParameter("id");
        ApplyOrder dOrder = new ApplyOrder();
        dOrder.setCdocument(strId);
        // 表头
        ApplyOrder applyOrder = new ApplyOrder();
        applyOrder = applyOrderService.searchApplyOrderByOrderId(dOrder);
        // 表体
        List<ApplyOrderDetail> applyOrderDetailes = new ArrayList<ApplyOrderDetail>();
        applyOrderDetailes = applyOrderDetailService.searchApplyOrderDetailByOrderId(applyOrder);
        // 资产明细
        ApplyDemandMappingModel applyDemandMappingModel = new ApplyDemandMappingModel();
        applyDemandMappingModel.setApplyId(strId);
        List<ApplyDemandMappingModel> applyDemandMappingModels = applyOrderService
                .searchAssetsDetail(applyDemandMappingModel);
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(strId, typeId, pager);
        appHistoryPager.setUri(request.getRequestURI());
        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("applyOrder", applyOrder);
        model.addAttribute("applyOrderDetailes", applyOrderDetailes);
        model.addAttribute("applyDemandMappingModels", applyDemandMappingModels);
        return "purchase/apply_order_detail";
    }
    /**
     * 采购申请审批页面
     */
    @RequestMapping("/examineApplyOrder")
    public String examineApplyOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {
        String strId = req.getParameter("id");
        ApplyOrder dOrder = new ApplyOrder();
        dOrder.setCdocument(strId);
        ApplyOrder applyOrder = new ApplyOrder();
        applyOrder = applyOrderService.searchApplyOrderByOrderId(dOrder);
        List<ApplyOrderDetail> applyOrderDetailes = new ArrayList<ApplyOrderDetail>();
        applyOrderDetailes = applyOrderDetailService.searchApplyOrderDetailByOrderId(applyOrder);
        ApplyDemandMappingModel applyDemandMappingModel = new ApplyDemandMappingModel();
        applyDemandMappingModel.setApplyId(strId);
        List<ApplyDemandMappingModel> applyDemandMappingModels = applyOrderService
                .searchAssetsDetail(applyDemandMappingModel);
        model.addAttribute("applyOrder", applyOrder);
        model.addAttribute("applyOrderDetailes", applyOrderDetailes);
        model.addAttribute("applyDemandMappingModels", applyDemandMappingModels);
        UserRole userRole = new UserRole();
        userRole.setCusercode(req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        //资产会计角色ID
        userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
        if (userRoleService.searchUserRole(userRole, strId, Env.getProperty("apply.info.type.id")) > 0) {
            model.addAttribute("assetAccounting", "Y");
        } else {
            model.addAttribute("assetAccounting", "N");
            //model.addAttribute("assetAccounting", "N");          测试审批全部修改为Y
        }
        //采购经理角色ID
        userRole.setCrolecode(Env.getProperty("role.purchasingManager.id"));
        if (userRoleService.searchUserRole(userRole, strId, Env.getProperty("apply.info.type.id")) > 0) {
            model.addAttribute("purchasingManager", "Y");
            model.addAttribute("canStepSubmit", "Y");
        } else {
            model.addAttribute("purchasingManager", "N");
        }
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(strId, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());
        model.addAttribute("appHistoryPager", appHistoryPager);
        return "purchase/apply_order_examine";
    }
    /**
     * 添加采购申请页面
     */
    @RequestMapping("/addApplyOrder")
    public String addApplyOrder(Model model, HttpServletRequest req) {
        ApplyOrder applyOrder = new ApplyOrder();
        // 生产订单编号
        String str = "CGSQ";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        str += sdf.format(date);
        int searchNextSequence = applyOrderService.searchNextSequence();
        // 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
        str += String.format("%04d", searchNextSequence);
        applyOrder.setCdocument(str);
        applyOrder.setApplyDate(date);
        //----------lipengju写的开始
        //		申请人--lipengju
        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        applyOrder.setApplyUser(applyUsername);
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        applyOrder.setApplyUserCode(applyUsernameCode);
        //		办公地点
        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        UserInfo userInfo = new UserInfo();
        userInfo.setCemployeecode(applyUsernameCode);
        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        model.addAttribute("userDeptInfo", userDeptInfo);
        //查询所有公司
        List<UserInfo> companys = userInfoService.searchAllCompany();
        model.addAttribute("addresslist", addresslist);
        model.addAttribute("companys", companys);
        List<AttchConfig> attachConfigList = attchConfigService.getAssetTypeListByAdmin(applyUsernameCode);
        model.addAttribute("attachConfigList", attachConfigList);
        model.addAttribute("applyOrder", applyOrder);
        return "purchase/apply_order_add";
    }
    /**
     * 合并明细操作
     */
    @RequestMapping("/api/mergeDetail")
    @ResponseBody
    public ExecuteResult<List<Object>> mergeDetail(@RequestBody List<DemandOrderDetailModel> demandOrderDetailModel) {
        ExecuteResult<List<Object>> executeResult = new ExecuteResult<List<Object>>();
        List<Object> list = new ArrayList<Object>();
        List<ApplyOrderDetail> applyOrderDetails = new ArrayList<ApplyOrderDetail>();
        List<DemandOrderDetailModel> demandOrderDetailModels = new ArrayList<DemandOrderDetailModel>();
        int i = 0;
        for (DemandOrderDetailModel dod : demandOrderDetailModel) {
            i++;
            if (executeResult.getErrorMessages().size() > 0) {
                break;
            }
            if (dod.getMergeNumber() == null || "".equals(dod.getMergeNumber())) {
                continue;
            }
            // 如果本次申请数量大于 需求数量-调拨数量-已申请数量 就返回错误消息
            if (dod.getThisApplyCount() > dod.getDemandCount() - dod.getAllocationCount() - dod.getApplyCount()) {
                executeResult.addErrorMessage("第" + i + "行,本次申请数量超出范围！");
                return executeResult;
            }
            //如果输了为0
            if (dod.getThisApplyCount() <= 0) {
                executeResult.addErrorMessage("第" + i + "行,本次申请数量必须大于0！");
                return executeResult;
            }
            //资产明细赋值
            demandOrderDetailModels.add(dod);
            //合并
            // 如果是第一次写入applyOrderDetails
            if (applyOrderDetails.size() == 0) {
                ApplyOrderDetail applyOrderDetail = new ApplyOrderDetail();
                applyOrderDetail.setMergeNumber(dod.getMergeNumber());
                applyOrderDetail.setAssetsName(dod.getAssetsName().trim());
                // 申请数量
                applyOrderDetail.setDemandCount(dod.getThisApplyCount());
                applyOrderDetail.setSpecificationParameter(dod.getSpecificationParameter().trim());
                applyOrderDetail.setFunctions(dod.getFunctions().trim());
                applyOrderDetail.setOptionalAccessories(dod.getOptionalAccessories().trim());
                applyOrderDetail.setAcceptanceCriteria(dod.getAcceptanceCriteria().trim());
                applyOrderDetail.setUnits(dod.getUnits());
                applyOrderDetail.setRequirementsDate(dod.getRequirementsDate());
                applyOrderDetail.setCostCenter(dod.getCostCenter());
                applyOrderDetail.setCostCenterCode(dod.getCostCenterCode());
                applyOrderDetail.setInquiryUnitPrice(dod.getInquiryUnitPrice());
                applyOrderDetail.setAssetClassification(dod.getAssetClassification());
                applyOrderDetails.add(applyOrderDetail);
                continue;
            }
            // 标记是否找到相同合并号
            boolean flag = false;
            // 遍历结果是否存在该合并号
            for (int j = 0; j < applyOrderDetails.size(); j++) {
                if (executeResult.getErrorMessages().size() > 0) {
                    break;
                }
                // 如果结果已存在该合并号 则判断是否合并 符合就合并 不符合就返回错误消息
                if (applyOrderDetails.get(j).getMergeNumber().equals(dod.getMergeNumber())) {
                    // 在此判断合并条件
                    if (!applyOrderDetails.get(j).getAssetsName().equals(dod.getAssetsName().trim())) {
                        executeResult.addErrorMessage("第" + i + "行,资产名称不相同，数据合并失败！");
                        break;
                    }
                    if (!applyOrderDetails.get(j).getSpecificationParameter().equals(dod.getSpecificationParameter().trim())) {
                        executeResult.addErrorMessage("第" + i + "行,规格参数不相同，数据合并失败！");
                        break;
                    }
                    if (!applyOrderDetails.get(j).getFunctions().equals(dod.getFunctions().trim())) {
                        /*executeResult.addErrorMessage("第" + i + "行,功能不相同，数据合并失败！");
                        break;*/
                        applyOrderDetails.get(j).setFunctions(applyOrderDetails.get(j).getFunctions() + ";" + dod.getFunctions());
                    }
                    if (!applyOrderDetails.get(j).getOptionalAccessories().equals(dod.getOptionalAccessories().trim())) {
                        executeResult.addErrorMessage("第" + i + "行,验收标准不相同，数据合并失败！");
                        break;
                    }
                    if (!applyOrderDetails.get(j).getAcceptanceCriteria().equals(dod.getAcceptanceCriteria().trim())) {
                        executeResult.addErrorMessage("第" + i + "行,选配件不相同，数据合并失败！");
                        break;
                    }
                    // 合并数据(合并本次申请数量)
                    applyOrderDetails.get(j).setDemandCount(applyOrderDetails.get(j).getDemandCount() + dod.getThisApplyCount());
                    //设置要求到位日期为靠前(最小)的日期
                    applyOrderDetails.get(j).setRequirementsDate(applyOrderDetails.get(j).getRequirementsDate().getTime() > dod.getRequirementsDate().getTime() ? dod.getRequirementsDate() : applyOrderDetails.get(j).getRequirementsDate());
                    // 标记已找到相同合并号
                    flag = true;
                    break;
                }
                // 如果遍历到applyOrderDetails的最后一个元素时
                if (applyOrderDetails.size() == j + 1) {
                    if (flag) {
                        flag = false;
                    } else {
                        // 不存在该合并号
                        ApplyOrderDetail applyOrderDetail = new ApplyOrderDetail();
                        applyOrderDetail.setMergeNumber(dod.getMergeNumber());
                        applyOrderDetail.setAssetsName(dod.getAssetsName().trim());
                        // 申请数量
                        applyOrderDetail.setDemandCount(dod.getThisApplyCount());
                        applyOrderDetail.setSpecificationParameter(dod.getSpecificationParameter().trim());
                        applyOrderDetail.setFunctions(dod.getFunctions().trim());
                        applyOrderDetail.setOptionalAccessories(dod.getOptionalAccessories().trim());
                        applyOrderDetail.setAcceptanceCriteria(dod.getAcceptanceCriteria().trim());
                        applyOrderDetail.setUnits(dod.getUnits());
                        applyOrderDetail.setRequirementsDate(dod.getRequirementsDate());
                        applyOrderDetail.setCostCenter(dod.getCostCenter());
                        applyOrderDetail.setCostCenterCode(dod.getCostCenterCode());
                        applyOrderDetail.setInquiryUnitPrice(dod.getInquiryUnitPrice());
                        applyOrderDetail.setAssetClassification(dod.getAssetClassification());
                        applyOrderDetails.add(applyOrderDetail);
                        break;
                    }
                }
            }
        }
        list.add(applyOrderDetails);
        list.add(demandOrderDetailModels);
        executeResult.setResult(list);
        return executeResult;
    }
    /**
     * 保存 新增 绑定审批流
     */
    @RequestMapping("/api/saveApply")
    @ResponseBody
    public ExecuteResult<ApplyOrder> saveApply(@RequestBody ApplyOrderWrapper applyOrderWrapper) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();
        // 表头信息
        ApplyOrder applyOrder = applyOrderWrapper.getApplyOrder();
        if (null == applyOrder) {
            executeResult.addErrorMessage("请检查表头信息是否有误，如有疑问，请联系管理员！");
            return executeResult;
        }

        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsMail())) {
            applyOrder.setIsMail(STATUS.YES.getStatus());
        } else {
            applyOrder.setIsMail(STATUS.NO.getStatus());
        }
        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsPhoneMessage())) {
            applyOrder.setIsPhoneMessage(STATUS.YES.getStatus());
        } else {
            applyOrder.setIsPhoneMessage(STATUS.NO.getStatus());
        }
        applyOrder.setBuyer(applyOrder.getPurchasingManagerName());
        applyOrder.setBuyerCode(applyOrder.getPurchasingManagerCode());
        // 表体信息
        List<ApplyOrderDetail> applyOrderDetails = applyOrderWrapper.getApplyOrderDetails();
        // 资产明细信息
        List<ApplyDemandMapping> applyDemandMappings = applyOrderWrapper.getApplyDemandMappings();
        // 需要回写采购需求明细 信息
        List<DemandOrderDetailModel> demandOrderDetailModels = new ArrayList<DemandOrderDetailModel>();
        //判断是否为草稿，若是草稿，判断资产明细（mapping表）中的需求单是否有已关闭的，若有，不能提交
        if (STATUS.YES.getStatus().equals(applyOrder.getDraft())) {
            boolean flag = false;
            for (ApplyDemandMapping applyDemandMapping : applyDemandMappings) {
                String orderStatus = applyOrderService.searchDemandOrderStatus(applyDemandMapping.getDemandDetailId());
                if (STATUS.YES_STATUS.getStatus().equals(orderStatus)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                executeResult.addErrorMessage("资产明细中有已关闭单据，不能保存！");
                return executeResult;
            }
        }
        //获得表头信息开始
        if (STATUS.NO.getStatus().equals(applyOrder.getDraft())) {
            applyOrder.setCapprovalstate("等待审批");
        }
        applyOrder.setOrderStatus("未关闭");
        applyOrder.setPrint("N");
        applyOrder.setCreateBy(applyOrder.getApplyUser());
        applyOrder.setCreateByCode(applyOrder.getApplyUserCode());
        applyOrder.setUpdateBy(applyOrder.getApplyUser());
        applyOrder.setUpdateByCode(applyOrder.getApplyUserCode());
        applyOrder.setCreateDate(new Date());
        applyOrder.setUpdateDate(new Date());
        //获得表头信息结束

        if (applyOrderWrapper.getApplyOrderDetails().size() == 0) {
            executeResult.addErrorMessage("表单明细不能为空！");
            return executeResult;
        }
        //获得表单明细开始
        for (ApplyOrderDetail aod : applyOrderDetails) {
            //设置订单编号
            aod.setApplyOrderId(applyOrder.getCdocument());
            //设置未申请数量(申请时未申请数量等于需求数量)
            aod.setNotApplyCount(aod.getDemandCount());
            aod.setApplyCount(0);
            //设置时间和维护人
            aod.setApplyUser(applyOrder.getApplyUser());
            aod.setApplyUserCode(applyOrder.getApplyUserCode());
            aod.setCreateBy(applyOrder.getCreateBy());
            aod.setCreateByCode(applyOrder.getCreateByCode());
            aod.setUpdateByCode(applyOrder.getUpdateByCode());
            aod.setUpdateBy(applyOrder.getUpdateBy());
            aod.setCreateDate(applyOrder.getCreateDate());
            aod.setUpdateDate(applyOrder.getUpdateDate());
            //设置总账科目
            AssetClassification acf = assetClassificationService.searchAssetClassificationByAssetClassification(aod);
            if (acf != null) {
                aod.setLedgerAccount(acf.getGeneralLedgerAccount());
                //设置折旧年限
                aod.setDepreciation(acf.getDepreciationPeriod());
            }
        }
        //获得表单明细结束
        //获得资产明细信息开始
        for (ApplyDemandMapping adm : applyDemandMappings) {
            adm.setApplyId(applyOrder.getCdocument());
            //设置未申请数量(申请时未申请数量等于需求数量)
            adm.setNotApplyCount(adm.getDemandCount());
            adm.setApplyCount(0);
            // 需要回写的采购需求明细 ID和数量
            if (STATUS.NO.getStatus().equals(applyOrder.getDraft())) {
                DemandOrderDetailModel demandOrderDetailModel = new DemandOrderDetailModel();
                demandOrderDetailModel.setId(adm.getDemandDetailId());
                demandOrderDetailModel.setThisApplyCount(adm.getDemandCount());
                demandOrderDetailModels.add(demandOrderDetailModel);
            }
            /**
             * 将申请单行明细中的  询价单价  *  对应的映射表中的数量，存入映射表中
             */
        }
        // 获得资产明细信息结束
        //保存头
        try {
            executeResult = applyOrderService.addSaveApply(applyOrder, applyOrderDetails, applyDemandMappings,
                    demandOrderDetailModels);
        } catch (OutBudgetException e) {
            LOGGER.error("e", e);
            executeResult.addErrorMessage(e.getMessage());
            return executeResult;
        }
        //申请 绑定审批流
        if (STATUS.NO.getStatus().equals(applyOrder.getDraft())) {
            executeResult = applyOrderService.applyComplaint(applyOrder);
        }
        //出现错误 删除数据
        if (!executeResult.isSuccess()) {
            //删除保存数据......
            applyOrderService.delApplyOrderByCdocument(applyOrder);
            workFlowService.deleteGarbegeWorkFlowData(applyOrder.getCdocument(), Env.getProperty("apply.info.type.id"));
            return executeResult;
        }
        //保存明细
        try {
            executeResult = applyOrderService.addSaveApplyDetail(applyOrder, applyOrderDetails, applyDemandMappings, demandOrderDetailModels);
        } catch (OutBudgetException e) {
            LOGGER.error("e", e);
            executeResult.addErrorMessage(e.getMessage());
            applyOrderService.delApplyOrderByCdocument(applyOrder);
            workFlowService.deleteGarbegeWorkFlowData(applyOrder.getCdocument(), Env.getProperty("apply.info.type.id"));
            return executeResult;
        } catch (Exception e) {
            LOGGER.error("e", e);
            executeResult.addErrorMessage(e.getMessage());
            applyOrderService.delApplyOrderByCdocument(applyOrder);
            workFlowService.deleteGarbegeWorkFlowData(applyOrder.getCdocument(), Env.getProperty("apply.info.type.id"));
            return executeResult;
        }
        //出现错误 删除数据
        if (!executeResult.isSuccess()) {
            //删除保存数据......
            applyOrderService.delApplyOrderByCdocument(applyOrder);
            workFlowService.deleteGarbegeWorkFlowData(applyOrder.getCdocument(), Env.getProperty("apply.info.type.id"));
            return executeResult;
        }
        if (STATUS.NO.getStatus().equals(applyOrder.getDraft())) {
            ApplyOrder searchApplyOrderByOrderId = applyOrderService.searchApplyOrderByOrderId(applyOrder);
            ExecuteResult<Employee> er = remindedService.BellReminder(searchApplyOrderByOrderId.getCnexthandlercode(), searchApplyOrderByOrderId.getCdocument(), applyOrder.getIsPhoneMessage(), applyOrder.getIsMail());
            //executeResult.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("保存成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        }
        return executeResult;
    }

    /**
     * 同意 审批流
     */
    @RequestMapping("/api/agreeApplyOrder")
    @ResponseBody
    private ExecuteResult<ApplyOrder> agreeApplyOrder(@RequestBody ApplyOrderWrapper applyOrderWrapper, HttpServletRequest request) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();
        //表头信息
        ApplyOrder applyOrder = applyOrderWrapper.getApplyOrder();
        //表体信息
        List<ApplyOrderDetail> applyOrderDetails = applyOrderWrapper.getApplyOrderDetails();
        //获得权限
        UserRole userRole = new UserRole();
        userRole.setCusercode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        //资产会计角色ID
        userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
        if (userRoleService.searchUserRole(userRole, applyOrder.getCdocument(), Env.getProperty("apply.info.type.id")) > 0) {
            if (!"是".equals(applyOrder.getIssap())) {
                executeResult.addErrorMessage("请先获取资产编码！");
                return executeResult;
            }
        }
        ApplyOrder applyOrderOnly = applyOrderService.searchApplyOrderByOrderId(applyOrder);
        if (!request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim().equals(applyOrderOnly.getCnexthandlercode())) {
            executeResult.addErrorMessage("单据已被审批，或没有审批权限！");
            return executeResult;
        }
        //采购经理角色ID权限更改
        userRole.setCrolecode(Env.getProperty("role.purchasingManager.id"));
        if (userRoleService.searchUserRole(userRole, applyOrder.getCdocument(), Env.getProperty("apply.info.type.id")) > 0) {
            executeResult = applyOrderService.updateApplyOrder(applyOrder, applyOrderDetails);
        }
        executeResult = handleApplyRequest(applyOrderOnly, request);
        applyOrderOnly = executeResult.getResult();
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsMail())) {
            applyOrderOnly.setIsMail("Y");
        } else {
            applyOrderOnly.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsPhoneMessage())) {
            applyOrderOnly.setIsPhoneMessage("Y");
        } else {
            applyOrderOnly.setIsPhoneMessage("N");
        }
        applyOrderOnly.setIdea(applyOrder.getIdea());
        applyOrderOnly.setActivityId(applyOrder.getActivityId());
        executeResult = applyOrderService.agreeWorkFlow(applyOrderOnly);

        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        ApplyOrder searchApplyOrderByOrderId = applyOrderService.searchApplyOrderByOrderId(applyOrderOnly);

        //单据已完成状态 进行判断需求单进行关闭
        if (APPROVESTATE.DONE.getApproveState().equals(searchApplyOrderByOrderId.getCapprovalstate())) {
            /**
             * 申请行明细中增加字段（订单可用预算，初始值为该明细申请总价）
             * 假如申请单中有该行明细有数量5个，单价1000，则申请行中该字段初始值为5000
             * 订单1数量为1，单价改为4000，则该字段值变为1000，
             * 订单2数量为4，单价改为400，此时和该值比较，大于该值，1600-1000=600，拿着600，根据项目编码查找项目可用预算，并进行判断，
             * 若可用，则占预算，若不可用，返回错误消息，不能下该订单
             */
            //当订单完成时，更新申请表中字段（订单可用预算，purchase_ava_money）
            List<ApplyOrderDetail> aods = applyOrderDetailService.searchApplyOrderDetailByOrderId(searchApplyOrderByOrderId);
            for (ApplyOrderDetail aod : aods) {
                aod.setPurchaseAvaMoney(aod.getPurchasePrice()); //为了方便事物控制，将此属性更新放在updateSearchCloseDemand方法中
            }
            executeResult = applyOrderService.updateSearchCloseDemand(searchApplyOrderByOrderId, aods);
            if (!executeResult.isSuccess()) {
                return executeResult;
            }
        }
//
//		//调用发生短信和邮件接口
//		if (!APPROVESTATE.DENY.getApproveState().trim().equals(applyOrder.getApprovalState().trim()) && !APPROVESTATE.DONE.getApproveState().trim().equals(applyOrder.getApprovalState().trim())) {
        if (StringUtils.isNotBlank(searchApplyOrderByOrderId.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchApplyOrderByOrderId.getCnexthandlercode(), searchApplyOrderByOrderId.getCdocument(), applyOrderOnly.getIsPhoneMessage(), applyOrderOnly.getIsMail());
//		}
            //executeResult.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        }
        return executeResult;

    }

    /**
     * 采购申请单 单据审批 sap接口调用
     *
     * @param applyOrderWrapper
     * @param request
     * @return
     */
    @RequestMapping("/api/sapInterface")
    @ResponseBody
    public ExecuteResult<ApplyOrder> sapInterface(@RequestBody ApplyOrderWrapper applyOrderWrapper, HttpServletRequest request) {

        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();

        //表头信息
        ApplyOrder applyOrder = applyOrderWrapper.getApplyOrder();
        ApplyOrder applyOrder1 = applyOrderService.searchApplyOrderByOrderId(applyOrder);
        //申请人姓名
        String applyUser = applyOrder1.getApplyUser();
        //申请人工号
        String applyUserCode = applyOrder1.getApplyUserCode();
        //表体信息
        List<ApplyOrderDetail> applyOrderDetails = applyOrderWrapper.getApplyOrderDetails();
        for (ApplyOrderDetail applyOrderDetail : applyOrderDetails) {
            if (applyOrderDetail.getAssetClassification() == null || "".equals(applyOrderDetail.getAssetClassification()) || "null".equals(applyOrderDetail.getAssetClassification())) {
                executeResult.addErrorMessage("资产分类不能为空");
                break;
            }
            if (applyOrderDetail.getLedgerAccount() == null || "".equals(applyOrderDetail.getLedgerAccount())
                    || "null".equals(applyOrderDetail.getLedgerAccount())) {
                executeResult.addErrorMessage("总账科目不能为空");
                break;
            }
            if (applyOrderDetail.getDepreciation() == null || "".equals(applyOrderDetail.getDepreciation())
                    || "null".equals(applyOrderDetail.getDepreciation())) {
                executeResult.addErrorMessage("折旧年限不能为空");
                break;
            }
        }
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        //下面还调用了一次，发版时可以删除。 updateUnits方法
        applyOrderDetailService.updateUnits(applyOrderDetails);
        if ("是".equals(applyOrder.getExpensing())) {
            for (ApplyOrderDetail applyOrderDetail : applyOrderDetails) {
                if (!"F".equals(applyOrderDetail.getAssetClassification().substring(0, 1))) {
                    executeResult.addErrorMessage("资产分类必须是[F]开头");
                    break;
                }
                //生成次级
                String getSecondary = "0000-";
                // 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
                getSecondary += String.format("%04d", applyOrderDetail.getDemandCount() - 1);
                applyOrderDetail.setSecondary(getSecondary);
            }
            if (!executeResult.isSuccess()) {
                return executeResult;
            }
            executeResult = applyOrderService.updateApplyOrderDetail(applyOrder, applyOrderDetails);
            if (!executeResult.isSuccess()) {
                return executeResult;
            }
            applyOrderService.getMainAssetCodePro(applyOrder);
        } else {
            try {
                ApplyDemandMappingModel applyDemandMappingModel = new ApplyDemandMappingModel();
                for (int i = 0; applyOrderDetails.size() > i; i++) {
                    if ("新增".equals(applyOrderDetails.get(i).getCodingMode())) {
                        IpInputModel ipInputModel = new IpInputModel();
                        ipInputModel.setAssetclass(applyOrderDetails.get(i).getAssetClassification());
                        ipInputModel.setCompanycode(applyOrder.getCompanyCode());
//						ipInputModel.setDescript("");
                        long count = applyOrderDetails.get(i).getDemandCount();
                        ipInputModel.setSubnumber(StringUtils.leftPad(String.valueOf(count), 4, "0"));
                        ipInputModel.setQuantity(count);
                        ipInputModel.setCostcenter(applyOrder.getCostCenterCode());

                        AssetUnit assetUnit = new AssetUnit();
                        assetUnit.setUnitName(applyOrderDetails.get(i).getUnits());
                        AssetUnit assetUnit2 = assetUnitService.getAssetUnit(assetUnit);
                        ipInputModel.setBaseUom(assetUnit2.getUnitCode());
                        if ("IT资产".equals(applyOrder.getAssetType())) {
                            ipInputModel.setInventNo(applyUserCode + "-" + applyUser);
                        } else {
                            applyDemandMappingModel.setMergeNumber(applyOrderDetails.get(i).getMergeNumber());
                            applyDemandMappingModel.setApplyId(applyOrder.getCdocument());
                            DemandOrderDetail dod = applyOrderDetailService.searchApplyDemandMapping(applyDemandMappingModel);
                            ipInputModel.setInventNo(dod.getApplyUserCode() + "-" + dod.getApplyUser());
                        }
                        ipInputModel.setDescript(applyOrderDetails.get(i).getAssetsName());
                        ipInputModel.setMainDescript(applyOrderDetails.get(i).getAssetsName());

                        IpHistoryModel ipHistoryModel = new IpHistoryModel();
                        ipHistoryModel.setOano("Oano");
                        ipHistoryModel.setOamodule("Oamodule");
                        ipHistoryModel.setOaname("Oaname");

                        JSONObject json = sapUtilService.createAssetsCodeFromSAP(ipInputModel, ipHistoryModel);
                        if (json.getString("error").length() > 1) {
                            executeResult.addErrorMessage(json.getString("error"));
                            return executeResult;
                        }
                        applyOrderDetails.get(i).setMainAssetCode(json.getString("asset"));
                        applyOrderDetails.get(i).setSecondary(json.getString("subNumber"));

                        if (!json.has("asset") || !json.has("subNumber")) {
                            executeResult.addErrorMessage("新增sap接口调用缺少返回值内容，请检查页面内容。");
                            return executeResult;
                        }

                    } else if ("追加".equals(applyOrderDetails.get(i).getCodingMode())) {

                        //					ItInputModel itInputModel = new ItInputModel();
                        //					itInputModel.setAnln1(applyOrderDetails.get(i).getMainAssetCode());
                        //					itInputModel.setCount(applyOrderDetails.get(i).getDemandCount());

                        IpInputModel ipInputModel = new IpInputModel();

                        ipInputModel.setAssetclass(applyOrderDetails.get(i).getAssetClassification());
                        ipInputModel.setCompanycode(applyOrder.getCompanyCode());
                        //ipInputModel.setQuantity(2L);
                        ipInputModel.setAnln1(applyOrderDetails.get(i).getMainAssetCode()); //2
                        long count = applyOrderDetails.get(i).getDemandCount();
                        ipInputModel.setSubnumber(String.valueOf(count));

                        AssetUnit assetUnit = new AssetUnit();
                        assetUnit.setUnitName(applyOrderDetails.get(i).getUnits());
                        AssetUnit assetUnit2 = assetUnitService.getAssetUnit(assetUnit);
                        ipInputModel.setBaseUom(assetUnit2.getUnitCode());

                        ipInputModel.setCostcenter(applyOrder.getCostCenterCode());
                        if ("IT资产".equals(applyOrder.getAssetType())) {
                            ipInputModel.setInventNo(applyUserCode + "-" + applyUser);
                        } else {
                            applyDemandMappingModel.setMergeNumber(applyOrderDetails.get(i).getMergeNumber());
                            applyDemandMappingModel.setApplyId(applyOrder.getCdocument());
                            DemandOrderDetail dod = applyOrderDetailService.searchApplyDemandMapping(applyDemandMappingModel);
                            ipInputModel.setInventNo(dod.getApplyUserCode() + "-" + dod.getApplyUser());
                        }
                        ipInputModel.setDescript(applyOrderDetails.get(i).getAssetsName());
                        ipInputModel.setMainDescript(applyOrderDetails.get(i).getAssetsName());

                        IpHistoryModel ipHistoryModel = new IpHistoryModel();
                        ipHistoryModel.setOano("1");
                        ipHistoryModel.setOaname("1");
                        ipHistoryModel.setOamodule("1");
                        JSONObject json = sapUtilService.createAssetsCodeForAppendFromSAP(ipInputModel, ipHistoryModel);

                        if (!json.has("subNumber")) {
                            executeResult.addErrorMessage("追加sap接口调用缺少返回值内容，请检查页面内容。");
                            return executeResult;
                        }
                        applyOrderDetails.get(i).setSecondary(json.getString("subNumber"));
                    } else if ("指定".equals(applyOrderDetails.get(i).getCodingMode())) {

                        if (null == applyOrderDetails.get(i).getMainAssetCode() && null == applyOrderDetails.get(i).getSecondary()) {
                            executeResult.addErrorMessage("指定的主编码和次级编码出现空值！");
                            return executeResult;
                        }

                        String[] subNumber = applyOrderDetails.get(i).getSecondary().split("-");
                        if (subNumber.length != 2) {
                            executeResult.addErrorMessage("次级编码指定区间有问题，请检查!");
                            return executeResult;
                        }
                        int length = Integer.parseInt(subNumber[1]) - Integer.parseInt(subNumber[0]) + 1;
                        if (length != applyOrderDetails.get(i).getDemandCount()) {
                            executeResult.addErrorMessage("次级编码指定区间次级编码个数有跟申请数量个数不匹配，请检查!");
                            return executeResult;
                        }

                        IpInputModel ipInputModel = new IpInputModel();
                        IpHistoryModel ipHistoryModel = new IpHistoryModel();
                        ipInputModel.setCompanycode(applyOrder.getCompanyCode());
                        ipInputModel.setAnln1(applyOrderDetails.get(i).getMainAssetCode());
                        ipInputModel.setSubnumber(applyOrderDetails.get(i).getSecondary());
                        JSONObject jsonData = sapUtilService.existenceOfAssetsCodeFromSAP(ipInputModel, ipHistoryModel);

                        if (!jsonData.has("exist")) {
                            executeResult.addErrorMessage("指定sap接口调用 失败~、");
                            return executeResult;
                        } else if ("false".equals("" + jsonData.getString("exist"))) {
                            executeResult.addErrorMessage("指定的主次级编码未能查到数据！");
                            return executeResult;
                        }
                    }
                }
                executeResult = applyOrderService.updateApplyOrderDetail(applyOrder, applyOrderDetails);
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
        applyOrderDetailService.updateUnits(applyOrderDetails);
        executeResult = applyOrderService.updateApplyOrderIssap(applyOrder);
        return executeResult;
    }

    /**
     * 退回
     *
     * @param applyOrder
     * @param request
     * @return
     */
    @RequestMapping("/api/denyApplyOrder")
    @ResponseBody
    public ExecuteResult<ApplyOrder> denyApplyOrder(@RequestBody ApplyOrder applyOrder, HttpServletRequest request) {
        ApplyOrder applyOrderOnly = applyOrderService.searchApplyOrderByOrderId(applyOrder);
        applyOrderOnly.setIdea(applyOrder.getIdea());
        applyOrderOnly.setIsMail(applyOrder.getIsMail());
        applyOrderOnly.setIsPhoneMessage(applyOrder.getIsPhoneMessage());
        ExecuteResult<ApplyOrder> executeResult = handleApplyRequest(applyOrderOnly, request);

        applyOrderOnly = executeResult.getResult();
        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsMail())) {
            applyOrderOnly.setIsMail("Y");
        } else {
            applyOrderOnly.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsPhoneMessage())) {
            applyOrderOnly.setIsPhoneMessage("Y");
        } else {
            applyOrderOnly.setIsPhoneMessage("N");
        }
        applyOrderOnly.setIsPhoneMessage(applyOrder.getIsPhoneMessage());
        applyOrderOnly.setActivityId(applyOrder.getActivityId());
        if (!executeResult.isSuccess()) {
            return executeResult;
        }

        executeResult = applyOrderService.denyWorkFlow(applyOrderOnly);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }


        applyOrder = applyOrderService.searchApplyOrderByOrderId(applyOrder);

//		//调用发生短信和邮件接口
//		if (!APPROVESTATE.DENY.getApproveState().trim().equals(applyOrder.getApprovalState().trim()) && !APPROVESTATE.DONE.getApproveState().trim().equals(applyOrder.getApprovalState().trim())) {
        if (StringUtils.isNotBlank(applyOrder.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(applyOrder.getCnexthandlercode(), applyOrder.getCdocument(), applyOrderOnly.getIsPhoneMessage(), applyOrderOnly.getIsMail());
//		}
            //executeResult.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        }
        return executeResult;
    }

    @RequestMapping("/api/dropApplyOrder")
    @ResponseBody
    public ExecuteResult<ApplyOrder> dropScoresQuery(@RequestBody ApplyOrder applyOrderOnly, HttpServletRequest request) {
        ExecuteResult<ApplyOrder> executeResult = handleApplyRequest(applyOrderOnly, request);

        ApplyOrder searchApplyOrderByOrderId = applyOrderService.searchApplyOrderByOrderId(applyOrderOnly);
        if (STATUS.YES_STATUS.getStatus().equals(searchApplyOrderByOrderId.getOrderStatus())) {
            executeResult.addErrorMessage("已关闭的单据不可弃审!");
            return executeResult;
        }
        Long searchNextPurchaseCount = applyOrderService.searchNextPurchaseCount(applyOrderOnly);
        if (searchNextPurchaseCount > 0) {
            executeResult.addErrorMessage("已生成采购订单的单据不可弃审!");
            return executeResult;
        }

        applyOrderOnly = applyOrderService.searchApplyOrderByOrderId(applyOrderOnly);
        //单据已完成状态 进行判断需求单进行关闭
        if (APPROVESTATE.DONE.getApproveState().equals(applyOrderOnly.getCapprovalstate())) {
            ExecuteResult<ApplyOrder> result = applyOrderService.updateSearchOpenDemand(applyOrderOnly);
            if (!result.isSuccess()) {
                return result;
            }
        }
        applyOrderOnly = executeResult.getResult();
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        executeResult = applyOrderService.dropWorkFlow(applyOrderOnly);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        ApplyOrder applyOrder = applyOrderService.searchApplyOrderByOrderId(applyOrderOnly);
        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsMail())) {
            applyOrder.setIsMail(STATUS.YES.getStatus());
        } else {
            applyOrder.setIsMail(STATUS.NO.getStatus());
        }
        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsPhoneMessage())) {
            applyOrder.setIsPhoneMessage(STATUS.YES.getStatus());
        } else {
            applyOrder.setIsPhoneMessage(STATUS.NO.getStatus());
        }
        //调用发生短信和邮件接口
        //if (!APPROVESTATE.DENY.getApproveState().trim().equals(applyOrder.getApprovalState().trim()) && !APPROVESTATE.DONE.getApproveState().trim().equals(applyOrder.getApprovalState().trim())) {
        if (StringUtils.isNotBlank(applyOrder.getCnexthandlercode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(applyOrder.getCnexthandlercode(), applyOrder.getCdocument(), applyOrderOnly.getIsPhoneMessage(), applyOrderOnly.getIsMail());
            //executeResult.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
            //}
        }
        return executeResult;
    }

    public ExecuteResult<ApplyOrder> handleApplyRequest(ApplyOrder applyOrder, HttpServletRequest request) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();

        if (StringUtils.isBlank(applyOrder.getCdocument())) {
            executeResult.addErrorMessage("单据号为空!");
            return executeResult;
        }

        ApplyOrder applyOrderbyDocument = applyOrderService.searchApplyOrderByOrderId(applyOrder);

        if (null == applyOrderbyDocument.getCdocument()) {
            executeResult.addErrorMessage("流程号不存在");
        }

        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        User userDeptInfo = userService.getUserDeptInfo(userCode);

        if (null == userDeptInfo) {
            executeResult.addErrorMessage("当前登陆人信息查询失败");
            return executeResult;
        }

        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsMail())) {
            applyOrderbyDocument.setIsMail("Y");
        } else {
            applyOrderbyDocument.setIsMail("N");
        }

        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsPhoneMessage())) {
            applyOrderbyDocument.setIsPhoneMessage("Y");
        } else {
            applyOrderbyDocument.setIsPhoneMessage("N");
        }

        if (StringUtils.isNotBlank(applyOrder.getIdea())) {
            applyOrderbyDocument.setIdea(applyOrder.getIdea());
        } else {
            applyOrderbyDocument.setIdea("");
        }

        // 设置当前登陆人信息
        applyOrderbyDocument.setCaUserCode(userCode);
        applyOrderbyDocument.setCaUserName(userDeptInfo.getUserName());
        applyOrderbyDocument.setUserDepCode(userDeptInfo.getDepartmentCode());

        executeResult.setResult(applyOrderbyDocument);
        return executeResult;
    }

    /**
     * 保存 修改
     *
     * @param applyOrderWrapper
     * @return
     */
    @RequestMapping("/api/saveUpdateApply")
    @ResponseBody
    public ExecuteResult<ApplyOrder> saveUpdateApply(@RequestBody ApplyOrderWrapper applyOrderWrapper) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();
        // 表头信息
        ApplyOrder applyOrder = applyOrderWrapper.getApplyOrder();
        // 表体信息
        List<ApplyOrderDetail> applyOrderDetails = applyOrderWrapper.getApplyOrderDetails();
        // 资产明细信息
        List<ApplyDemandMapping> applyDemandMappings = applyOrderWrapper.getApplyDemandMappings();
        //查询修改前单据
        ApplyOrder oldApplyOrder = applyOrderService.searchApplyOrderByOrderId(applyOrder);
        //当前操作是要提交而不是保存草稿,并且当前要操作的单据之前是草稿，要先判断是否申请单资产明细中有被关闭的需求单，若有，则不能提交
        if (STATUS.NO.getStatus().equals(applyOrder.getDraft()) && STATUS.YES.getStatus().equals(oldApplyOrder.getDraft())) {
            DemandOrder demandOrder = new DemandOrder();
            DemandOrder demandOrder1 = new DemandOrder();

            for (ApplyDemandMapping adm : applyDemandMappings) {
                String demandId = demandOrderDetailService.searchDemandOrderIdByDetailId(adm.getDemandDetailId());
                demandOrder.setCdocument(demandId);
                //根据单据号并且单据状态为未关闭的查找
                demandOrder1 = demandOrderService.searchDemandOrderByOrderId(demandOrder);
                if (null == demandOrder1 || STATUS.YES_STATUS.getStatus().equals(demandOrder1.getOrderStatus())) {
                    executeResult.addErrorMessage("所申请的需求单有已关闭或无效的单据，不能提交！");
                    return executeResult;
                }
            }
        }
        // 需要回写采购需求明细 信息
        List<DemandOrderDetailModel> demandOrderDetailModels = new ArrayList<DemandOrderDetailModel>();

        //获得表头信息开始
        if ("N".equals(applyOrder.getDraft())) {
            applyOrder.setCapprovalstate("等待审批");
            //如果是要提交正式，判断是否跨不提交是否跨过资产会计（资产会计可以改资产分类，获取资产编码）
            String billCode = applyOrder.getCdocument();
            String typeId = APPLY_INFO_TYPE_ID;
            String roleId = Env.getProperty("role.assetAccounting.id");
            String activityid = applyOrder.getActivityId();
            String roleids = applyOrderService.searchIsCrossAssetAccounting(billCode, typeId, roleId, activityid);
            if (StringUtils.isNotBlank(roleids)) {
                executeResult.addErrorMessage("不能跨过资产会计提交！");
                return executeResult;
            }
        }
        applyOrder.setOrderStatus("未关闭");
        applyOrder.setCreateBy(applyOrder.getApplyUser());
        applyOrder.setCreateByCode(applyOrder.getApplyUserCode());
        applyOrder.setUpdateBy(applyOrder.getApplyUser());
        applyOrder.setUpdateByCode(applyOrder.getApplyUserCode());
        applyOrder.setCreateDate(new Date());
        applyOrder.setUpdateDate(new Date());
        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsMail())) {
            applyOrder.setIsMail("Y");
        } else {
            applyOrder.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(applyOrder.getIsPhoneMessage())) {
            applyOrder.setIsPhoneMessage("Y");
        } else {
            applyOrder.setIsPhoneMessage("N");
        }
        //获得表头信息结束

        if (applyOrderWrapper.getApplyOrderDetails().size() == 0) {
            executeResult.addErrorMessage("表单明细不能为空！");
            return executeResult;
        }
        //获得表单明细开始
        for (ApplyOrderDetail aod : applyOrderDetails) {
            //设置订单编号
            aod.setApplyOrderId(applyOrder.getCdocument());
            //设置未申请数量(申请时未申请数量等于需求数量)
            aod.setNotApplyCount(aod.getDemandCount());
            aod.setApplyCount(0);
            //设置时间和维护人
            aod.setApplyUser(applyOrder.getApplyUser());
            aod.setApplyUserCode(applyOrder.getApplyUserCode());
            aod.setCreateBy(applyOrder.getCreateBy());
            aod.setCreateByCode(applyOrder.getCreateByCode());
            aod.setUpdateByCode(applyOrder.getUpdateByCode());
            aod.setUpdateBy(applyOrder.getUpdateBy());
            aod.setCreateDate(applyOrder.getCreateDate());
            aod.setUpdateDate(applyOrder.getUpdateDate());
            //设置总账科目
            AssetClassification acf = assetClassificationService.searchAssetClassificationByAssetClassification(aod);
            if (acf != null) {
                aod.setLedgerAccount(acf.getGeneralLedgerAccount());
                //设置折旧年限
                aod.setDepreciation(acf.getDepreciationPeriod());
            }
        }
        //获得表单明细结束

        //获得资产明细信息开始
        for (ApplyDemandMapping adm : applyDemandMappings) {
            adm.setApplyId(applyOrder.getCdocument());
            //设置未申请数量(申请时未申请数量等于需求数量)
            adm.setNotApplyCount(adm.getDemandCount());
            adm.setApplyCount(0);
            // 需要回写的采购需求明细 ID和数量
            if (STATUS.NO.getStatus().equals(applyOrder.getDraft())) {
                DemandOrderDetailModel demandOrderDetailModel = new DemandOrderDetailModel();
                demandOrderDetailModel.setId(adm.getDemandDetailId());
                demandOrderDetailModel.setThisApplyCount(adm.getDemandCount());
                demandOrderDetailModels.add(demandOrderDetailModel);
            }
        }

        applyOrderService.update(applyOrder);
        // 获得资产明细信息结束
        try {
            executeResult = applyOrderService.addSaveUpdateApply(applyOrder, applyOrderDetails, applyDemandMappings,
                    demandOrderDetailModels, oldApplyOrder);
        } catch (OutBudgetException e) {
            LOGGER.error("e", e);
            executeResult.addErrorMessage(e.getMessage());
            applyOrderService.update(oldApplyOrder);
            return executeResult;
        } catch (Exception e) {
            LOGGER.error("e", e);
            executeResult.addErrorMessage("保存行明细失败");
            applyOrderService.update(oldApplyOrder);
            return executeResult;
        }
        if (!executeResult.isSuccess()) {
            applyOrderService.update(oldApplyOrder);
            return executeResult;
        }
        if ("N".equals(applyOrder.getDraft())) {
            ApplyOrder searchApplyOrderByOrderId = applyOrderService.searchApplyOrderByOrderId(applyOrder);
//
//		//调用发生短信和邮件接口
//		if (!APPROVESTATE.DENY.getApproveState().trim().equals(applyOrder.getApprovalState().trim()) && !APPROVESTATE.DONE.getApproveState().trim().equals(applyOrder.getApprovalState().trim())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(searchApplyOrderByOrderId.getCnexthandlercode(), searchApplyOrderByOrderId.getCdocument(), applyOrder.getIsPhoneMessage(), applyOrder.getIsMail());
//		}
            //executeResult.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("修改成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        }
        return executeResult;
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
    @RequestMapping("/updateApplyOrder")
    public String updateApplyOrder(Model model, HttpServletRequest req, Long offset, Long pageSize) {
        // 单据号
        String strId = req.getParameter("id");
        ApplyOrder dOrder = new ApplyOrder();

        dOrder.setCdocument(strId);
        // 表头
        ApplyOrder applyOrder = new ApplyOrder();
        applyOrder = applyOrderService.searchApplyOrderByOrderId(dOrder);

		/*String loginUsernameCode =	req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        //当登录人不是申请人时
		if (!loginUsernameCode.equals(applyOrder.getApplyUserCode())) {
			model.addAttribute("updateErrorMessage", "当前登录人不是申请人，不可修改订单！");
		}*/
        String loginUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        //当登录人不是申请人时 也不是授权
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUsernameCode);
        List<UserInfo> searchCauthorizerCode = userInfoService.searchCauthorizerCode(userInfo);
        for (UserInfo userInfo2 : searchCauthorizerCode) {
            if (applyOrder.getApplyUserCode().equals(userInfo2.getCauthorizerCode())) {
                applyOrder.setIsAuthorize("Y");
            }
        }
        //获得权限
        UserRole userRole = new UserRole();
        userRole.setCusercode(req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        //采购经理角色
        userRole.setCrolecode(Env.getProperty("role.purchasingManager.id"));
        if (userRoleService.searchUserRole(userRole, applyOrder.getCdocument(), Env.getProperty("apply.info.type.id")) > 0) {
            model.addAttribute("canStepSubmit", "Y");
        }

        if (!loginUsernameCode.equals(applyOrder.getApplyUserCode()) && !STATUS.YES.getStatus().equals(applyOrder.getIsAuthorize())) {
            model.addAttribute("updateErrorMessage", "当前登录人不是申请人，不可修改订单！");
        }
        //单据正在审批或者已完成时不可以修改单据
        if (APPROVESTATE.PROCESSING.getApproveState().equals(applyOrder.getCapprovalstate()) || APPROVESTATE.DONE.getApproveState().equals(applyOrder.getCapprovalstate())) {
            model.addAttribute("updateErrorMessage", "审批状态单据不可修改！");
        }
        //关闭订单不可修改
        if (!STATUS.NO_STATUS.getStatus().equals(applyOrder.getOrderStatus())) {
            model.addAttribute("updateErrorMessage", "已关闭订单不可修改！");
        }
        // 表体
        List<ApplyOrderDetail> applyOrderDetailes = new ArrayList<ApplyOrderDetail>();
        applyOrderDetailes = applyOrderDetailService.searchApplyOrderDetailByOrderId(applyOrder);

        // 资产明细
        ApplyDemandMappingModel applyDemandMappingModel = new ApplyDemandMappingModel();
        applyDemandMappingModel.setApplyId(strId);
        List<ApplyDemandMappingModel> applyDemandMappingModels = applyOrderService.searchAssetsDetail(applyDemandMappingModel);

        model.addAttribute("applyOrder", applyOrder);
        model.addAttribute("applyOrderDetailes", applyOrderDetailes);
        model.addAttribute("applyDemandMappingModels", applyDemandMappingModels);

        //办公地点
        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        //查询所有公司
        List<UserInfo> companys = userInfoService.searchAllCompany();
        model.addAttribute("addresslist", addresslist);
        model.addAttribute("companys", companys);
        //查询资产类型 王凯旋
        List<AttchConfig> attachConfigList = attchConfigService.getAssetTypeListByAdmin(loginUsernameCode);
        model.addAttribute("attachConfigList", attachConfigList);

        model.addAttribute("applyOrder", applyOrder);

        return "purchase/apply_order_update";
    }

    /**
     * 导出
     *
     * @param model
     * @param req
     * @param response
     * @param applyOrder
     */
    @RequestMapping(value = "/api/exportApply", method = RequestMethod.GET)
    @ResponseBody
    public void exportApply(ModelMap model, HttpServletRequest req, HttpServletResponse response,
                            ApplyOrder applyOrder) {
        ApplyOrderModel applyOrderModel = new ApplyOrderModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 单据号
        String strApplyOrderId = req.getParameter("txtApplyOrderId");
        try {
            // 开始时间
            applyOrderModel.setApplyDateStart(sdf.parse(req.getParameter("txtBeginDate")));
        } catch (Exception e) {
            applyOrderModel.setApplyDateStart(null);
        }
        try {
            // 结束时间
            applyOrderModel.setApplyDateEnd(sdf.parse(req.getParameter("txtEndDate")));
        } catch (Exception e) {
            applyOrderModel.setApplyDateEnd(null);
        }
        String strApplyUser = req.getParameter("txtApplyUser");
        String strCapprovalstates = req.getParameter("ckCapprovalstate");
        String strDraft = req.getParameter("ckDraft");
        String strOrderStatus = req.getParameter("ckOrderStatus");
        String strAssetType = req.getParameter("txtAssetType");

        applyOrderModel.setCdocument(null == strApplyOrderId ? null : strApplyOrderId.trim());
        applyOrderModel.setApplyUser(null == strApplyUser ? null : strApplyUser.trim());
        applyOrderModel.setCapprovalstate(null == strCapprovalstates ? null : strCapprovalstates.trim());
        applyOrderModel.setDraft(null == strDraft ? "N" : strDraft.trim());
        applyOrderModel.setOrderStatus(null == strOrderStatus ? null : strOrderStatus.trim());
        applyOrderModel.setAssetType(strAssetType);
        //设置当前登录人编码
        applyOrderModel.setLoginUserCode(req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());

        String fileName = "采购申请列表.xlsx";
        applyOrderModel.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        List<ApplyOrder> tsList = applyOrderService.exportApplyOrder(applyOrderModel);
        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportApplyTemplate exportApplyTemplate = new ExportApplyTemplate();
            exportApplyTemplate.doExport(response, fileName, tsList);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }

    }

    @RequestMapping("/api/searchDepartment")
    @ResponseBody
    public List<Department> searchDepartment(HttpServletRequest req, @RequestBody Department dept) {
        String loginUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        ApplyOrderModel applyOrderModel = new ApplyOrderModel();
        applyOrderModel.setLoginUserCode(loginUsernameCode);
        applyOrderModel.setCompanyCode(dept.getCompanyCode());
        applyOrderModel.setCostCenter(dept.getCostCenter());
        applyOrderModel.setBudgetDepartmentName(dept.getDepartmentName());
        List<Department> departments = applyOrderService.searchDepartmentByUserCode(applyOrderModel);
        return departments;
    }

    @RequestMapping("/api/searchApplyDetail")
    @ResponseBody
    public List<Object> searchApplyDetail(@RequestBody ApplyOrderModel applyOrderModel, HttpServletRequest req) {
        List<Object> result = new ArrayList<Object>();
        List<ApplyOrderDetail> applyOrderDetails = new ArrayList<ApplyOrderDetail>();
        List<ApplyDemandMappingModel> applyDemandMappingModels = new ArrayList<ApplyDemandMappingModel>();

        applyOrderDetails = applyOrderDetailService.searchApplyOrderDetailList(applyOrderModel);
        for (ApplyOrderDetail applyOrderDetail : applyOrderDetails) {
            //根据采购申请单号和合并号查询applyDemandMapping表
            applyDemandMappingModels.addAll(purchaseOrderService.searchForPurchaseOrder(applyOrderDetail));

            //修改Null值
            if (applyOrderDetail.getMainAssetCode() == null) {
                applyOrderDetail.setMainAssetCode("");
            }
            if (applyOrderDetail.getSecondary() == null) {
                applyOrderDetail.setSecondary("");
            }
            if (applyOrderDetail.getSpecificationParameter() == null) {
                applyOrderDetail.setSpecificationParameter("");
            }
            if (applyOrderDetail.getFunctions() == null) {
                applyOrderDetail.setFunctions("");
            }
            if (applyOrderDetail.getOptionalAccessories() == null) {
                applyOrderDetail.setOptionalAccessories("");
            }
            if (applyOrderDetail.getAcceptanceCriteria() == null) {
                applyOrderDetail.setAcceptanceCriteria("");
            }
        }
        for (ApplyDemandMappingModel admm : applyDemandMappingModels) {
            admm.setApplyCount(admm.getNotApplyCount());
            //修改Null值
            if (admm.getSpecificationParameter() == null) {
                admm.setSpecificationParameter("");
            }
            if (admm.getFunctions() == null) {
                admm.setFunctions("");
            }
            if (admm.getOptionalAccessories() == null) {
                admm.setOptionalAccessories("");
            }
            if (admm.getAcceptanceCriteria() == null) {
                admm.setAcceptanceCriteria("");
            }
            if (admm.getUnits() == null) {
                admm.setUnits("");
            }
            if (admm.getInquiryUnitPrice() == null) {
                admm.setInquiryUnitPrice(BigDecimal.ZERO);
            }
            if (admm.getUseDescription() == null) {
                admm.setUseDescription("");
            }
        }
        result.add(applyOrderDetails);
        result.add(applyDemandMappingModels);
        return result;
    }

    @RequestMapping("/api/delApply")
    @ResponseBody
    public ExecuteResult<ApplyOrder> delApply(String ids) {
        ExecuteResult<ApplyOrder> result = new ExecuteResult<ApplyOrder>();
        String[] applyIds = ids.split(",");

        ApplyOrder applyOrder = new ApplyOrder();
        if (applyIds != null && applyIds.length != '0') {
            for (String string : applyIds) {
                applyOrder.setCdocument(string);
                try {
                    ApplyOrder searchApplyOrderByOrderId = applyOrderService.searchApplyOrderByOrderId(applyOrder);
                    List<ApplyOrderDetail> applyOrderDetail = applyOrderDetailService.searchApplyOrderDetailByOrderId(applyOrder);
                    applyOrderService.deleteApplyOrder(searchApplyOrderByOrderId, applyOrderDetail);
                } catch (OutBudgetException e) {
                    LOGGER.error("e", e);
                    result.addErrorMessage(e.getMessage());
                    return result;
                } catch (Exception e) {
                    LOGGER.error("e", e);
                    result.addErrorMessage(e.getMessage());
                    return result;
                }

                workFlowService.deleteGarbegeWorkFlowData(string, Env.getProperty("apply.info.type.id"));
            }
        } else {
            result.addErrorMessage("请至少选择一条数据");
        }

        return result;
    }

    @RequestMapping("/api/closeApply")
    @ResponseBody
    public ExecuteResult<ApplyOrder> closeApply(String ids) {
        ExecuteResult<ApplyOrder> result = new ExecuteResult<ApplyOrder>();
        String[] applyIds = ids.split(",");

        ApplyOrder applyOrder = new ApplyOrder();
        if (applyIds != null && applyIds.length != '0') {
            for (String string : applyIds) {
                applyOrder.setCdocument(string);
                ApplyOrder searchApplyOrderByOrderId = applyOrderService.searchApplyOrderByOrderId(applyOrder);
                List<ApplyOrderDetail> applyOrderDetail = applyOrderDetailService.searchApplyOrderDetailByOrderId(applyOrder);
                applyOrderService.updateCloseApplyOrder(searchApplyOrderByOrderId, applyOrderDetail);
            }
        } else {
            result.addErrorMessage("请至少选择一条数据");
        }

        return result;
    }

    /**
     * 打印
     *
     */
    @RequestMapping("/printOrder")
    public String printOrder(Model model, HttpServletRequest request, String typeId) {
        String cdocument = request.getParameter("id");
        ApplyOrder dOrder = new ApplyOrder();

        dOrder.setCdocument(cdocument);
        // 表头
        ApplyOrder applyOrder = new ApplyOrder();
        applyOrder = applyOrderService.searchApplyOrderByOrderId(dOrder);
        // 表体
        List<ApplyOrderDetail> applyOrderDetailes = new ArrayList<ApplyOrderDetail>();
        applyOrderDetailes = applyOrderDetailService.searchApplyOrderDetailByOrderId(applyOrder);
        // 资产明细
        ApplyDemandMappingModel applyDemandMappingModel = new ApplyDemandMappingModel();
        applyDemandMappingModel.setApplyId(cdocument);
        List<ApplyDemandMappingModel> applyDemandMappingModels = applyOrderService.searchAssetsDetail(applyDemandMappingModel);

        applyOrderService.updatePrint(applyOrder);

        /**
         * 获取审批流流程的历史记录
         */
        List<ApproveHistoryCheckPrint> appHistoryPager = checkAcceptanceService.getApproveHistory(cdocument, typeId);
        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("applyOrder", applyOrder);
        model.addAttribute("applyOrderDetailes", applyOrderDetailes);
        model.addAttribute("applyDemandMappingModels", applyDemandMappingModels);
        return "purchase/apply_order_stamp";
    }
}
