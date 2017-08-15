package com.opple.fa.purchase.webapp.controller;

import com.opple.fa.config.entity.AssetUnit;
import com.opple.fa.config.entity.BudgetAssetstypeConfig;
import com.opple.fa.config.entity.StandardComputerConfig;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.entity.UserRole;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AssetUnitService;
import com.opple.fa.config.service.BudgetAssetstypeConfigService;
import com.opple.fa.config.service.StandardComputerConfigService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.config.service.UserRoleService;
import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.entity.OutBudgetException;
import com.opple.fa.purchase.entity.WARNSTATE;
import com.opple.fa.purchase.model.ApproveHistoryCheckPrint;
import com.opple.fa.purchase.model.DemandOrderDetailModel;
import com.opple.fa.purchase.model.DemandOrderExamineWrapper;
import com.opple.fa.purchase.model.DemandOrderModel;
import com.opple.fa.purchase.model.DemandOrderWrapper;
import com.opple.fa.purchase.service.ApplyOrderService;
import com.opple.fa.purchase.service.CheckAcceptanceService;
import com.opple.fa.purchase.service.DemandOrderDetailService;
import com.opple.fa.purchase.service.DemandOrderService;
import com.opple.fa.security.service.impl.ExportDemandTemplate;
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
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/demand")
public class DemandController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemandController.class);
    @Resource
    private DemandOrderService demandOrderService;

    @Resource
    private DemandOrderDetailService demandOrderDetailService;
    @Resource
    private StandardComputerConfigService standardComputerConfigService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private ApplyOrderService applyOrderService;

    @Resource
    private UserService userService;
    @Resource
    private AssetUnitService assetUnitService;

    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private RemindedService remindedService;
    @Resource
    private BudgetAssetstypeConfigService budgetAssetstypeConfigService;
    @Resource
    private CheckAcceptanceService checkAcceptanceService;


    @RequestMapping("/searchDemand")
    public String searchDemand(Model model, HttpServletRequest req, Long offset, Long pageSize, DemandOrder demandOrder) {
        DemandOrderModel demandOrderModel = new DemandOrderModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 单据号
        String strDemandOrderId = req.getParameter("txtDemandOrderId");
        try {
            // 开始时间
            demandOrderModel.setApplyDateStart(sdf.parse(req.getParameter("txtBeginDate")));
        } catch (Exception e) {
            demandOrderModel.setApplyDateStart(null);
        }
        try {
            // 结束时间
            demandOrderModel.setApplyDateEnd(sdf.parse(req.getParameter("txtEndDate")));
        } catch (Exception e) {
            demandOrderModel.setApplyDateEnd(null);
        }
        String strApplyUser = req.getParameter("txtApplyUser");
        // 资产类型
        String strAssetType = req.getParameter("txtAssetType");
        String strCapprovalstates = req.getParameter("ckCapprovalstate");
        String strDraft = req.getParameter("ckDraft");
        String strOrderStatus = req.getParameter("ckOrderStatus");
        // 申请部门
        String strBudgetDepartmentCode = req.getParameter("budgetDepartmentCode");
        String companyCode = req.getParameter("companyCode");
        demandOrderModel.setCompanyCode(null == companyCode ? null : companyCode.trim());
        demandOrderModel.setCdocument(null == strDemandOrderId ? null : strDemandOrderId.trim());
        demandOrderModel.setApplyUser(null == strApplyUser ? null : strApplyUser.trim());
        demandOrderModel.setAssetType(null == strAssetType ? null : strAssetType.trim());
        demandOrderModel.setCapprovalstate(null == strCapprovalstates ? null : strCapprovalstates.trim());
        demandOrderModel.setDraft(null == strDraft ? "N" : strDraft.trim());
        demandOrderModel.setOrderStatus(null == strOrderStatus ? null : strOrderStatus.trim());
        demandOrderModel.setBudgetDepartmentCode(strBudgetDepartmentCode);
        demandOrderModel.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        //设置当前登录人编码
        demandOrderModel.setLoginUserCode(loginUserCode);
        Pager<DemandOrder> pager = new Pager<DemandOrder>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<DemandOrder> demandOrderPager = demandOrderService.searchDemandOrder(pager, demandOrderModel);
        demandOrderPager.setUri(req.getRequestURI());
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUserCode);
        List<UserInfo> searchCauthorizerCode = userInfoService.searchCauthorizerCode(userInfo); //查询当前登录人是否被单据创建人授权
        List<DemandOrder> records = demandOrderPager.getRecords();
        //遍历 如果有授权，则把明细是否授权字段变为Y
        for (DemandOrder demandOrder2 : records) {
            for (UserInfo userInfo2 : searchCauthorizerCode) {
                if (demandOrder2.getApplyUserCode().equals(userInfo2.getCauthorizerCode())) {
                    demandOrder2.setIsAuthorize("Y");
                }
            }
        }
        demandOrderPager.setRecords(records);
        model.addAttribute("demandOrderPager", demandOrderPager);
        model.addAttribute("loginUserCode", demandOrderModel.getLoginUserCode());
        List<UserInfo> companys = userInfoService.searchAllCompany();
        //model.addAttribute("empdeptlist", empdeptlist);
        //model.addAttribute("currencylist", currencylist);
        model.addAttribute("companys", companys);
        return "purchase/demand_order_list";
    }


    /**
     * 数据新增
     *
     * @param model
     * @param req
     * @return
     */
    @RequestMapping("/addDemandOrder")
    public String addDemandOrder(Model model, HttpServletRequest req) {
        DemandOrder demandOrder = new DemandOrder();
        // 生产订单编号
        String str = "CGXQ";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        str += sdf.format(date);

        int searchNextSequence = demandOrderService.searchNextSequence();
        // 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
        str += String.format("%04d", searchNextSequence);
        demandOrder.setCdocument(str);
        demandOrder.setApplyDate(date);

        //----------lipengju写的开始
        //		申请人--lipengju
        String applyUsername = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        demandOrder.setApplyUser(applyUsername);
        String applyUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        demandOrder.setApplyUserCode(applyUsernameCode);
        //		办公地点
        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        //		币种
        //List<UserInfo> currencylist = userInfoService.searchCurrencyInfo();
        UserInfo userInfo = new UserInfo();
        userInfo.setCemployeecode(applyUsernameCode);
        //		根据申请人 查询 所属部门
        List<UserInfo> empdeptlist = userInfoService.searchOpdeptInfo(userInfo);
        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        //大区总监角色ID
        UserRole userRole = new UserRole();
        userRole.setCrolecode(Env.getProperty("role.regionalDirector.id"));
        if (userRoleService.searchUserRole(userRole) > 0) {
            model.addAttribute("regionalDirector", "Y");
        } else {
            model.addAttribute("regionalDirector", "N");
        }
        List<AssetUnit> assetUnitList = assetUnitService.getAssetUnitList();
        model.addAttribute("assetUnitList", assetUnitList);
        model.addAttribute("userDeptInfo", userDeptInfo);

        model.addAttribute("empdeptlist", empdeptlist);
        //model.addAttribute("currencylist", currencylist);
        model.addAttribute("addresslist", addresslist);
        //----------lipengju写的结束

        model.addAttribute("demandOrder", demandOrder);

        return "purchase/demand_order_add";
    }

    /**
     * 查询所有标准电脑 及规格参数
     *
     * @return
     */
    @RequestMapping("/api/findComputer")
    @ResponseBody
    public List<StandardComputerConfig> findComputer() {
        return standardComputerConfigService.findAll();
    }

    /**
     * 申请新增时，选择行明细，增行
     *
     * @param map
     * @param req
     * @return
     */
    @RequestMapping("/api/searchDemandDetail")
    @ResponseBody
    public List<DemandOrderDetail> searchDemandDetail(@RequestBody Map<String, Object> map, HttpServletRequest req) {
        DemandOrderDetailModel demandOrderDetailModel = new DemandOrderDetailModel();

        String strAssetsName = map.get("txtAssetsName").toString();
        String strBeginDate = map.get("txtBeginDate").toString();
        String strEndDate = map.get("txtEndDate").toString();
        String strApplyUser = map.get("txtApplyUser").toString();

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        try {
            beginDate = sd.parse(strBeginDate);
        } catch (Exception e) {
            beginDate = null;
        }
        try {
            endDate = sd.parse(strEndDate);
        } catch (Exception e) {
            endDate = null;
        }

        demandOrderDetailModel.setAssetsName(strAssetsName);
        demandOrderDetailModel.setApplyDateStart(beginDate);
        demandOrderDetailModel.setApplyDateEnd(endDate);
        demandOrderDetailModel.setApplyUser(strApplyUser);
        //设置修改页面的Id
        demandOrderDetailModel.setIds(map.get("ids") != null ? map.get("ids").toString() : "");
        // 预算所属部门编码
        demandOrderDetailModel.setBudgetDepartmentCode(map.get("budgetDepartmentCode") != null ? map.get("budgetDepartmentCode").toString() : "");
        // 公司编码
        demandOrderDetailModel.setCompanyCode(map.get("companyCode") != null ? map.get("companyCode").toString() : "");
        // 资产类型
        demandOrderDetailModel.setAssetType(map.get("assetsType") != null ? map.get("assetsType").toString() : "");
        // 平台
        demandOrderDetailModel.setPlatform(map.get("platform") != null ? map.get("platform").toString() : "");
        // 区域
        demandOrderDetailModel.setRegion(map.get("region") != null ? map.get("region").toString() : "");
        //当前登录人
        demandOrderDetailModel.setApplyUserCode(req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());

        List<DemandOrderDetail> searchDemandOrderDetail = demandOrderDetailService.searchDemandOrderDetail(demandOrderDetailModel);
        for (int i = 0; i < searchDemandOrderDetail.size(); i++) {
            if (searchDemandOrderDetail.get(i).getSpecificationParameter() == null) {
                searchDemandOrderDetail.get(i).setSpecificationParameter("");
            }
            if (searchDemandOrderDetail.get(i).getFunctions() == null) {
                searchDemandOrderDetail.get(i).setFunctions("");
            }
            if (searchDemandOrderDetail.get(i).getOptionalAccessories() == null) {
                searchDemandOrderDetail.get(i).setOptionalAccessories("");
            }
            if (searchDemandOrderDetail.get(i).getAcceptanceCriteria() == null) {
                searchDemandOrderDetail.get(i).setAcceptanceCriteria("");
            }
            if (searchDemandOrderDetail.get(i).getUseDescription() == null) {
                searchDemandOrderDetail.get(i).setUseDescription("");
            }
            if (searchDemandOrderDetail.get(i).getUnits() == null) {
                searchDemandOrderDetail.get(i).setUnits("");
            }
            if (searchDemandOrderDetail.get(i).getInquiryUnitPrice() == null) {
                searchDemandOrderDetail.get(i).setInquiryUnitPrice(BigDecimal.ZERO);
            }
        }
        return searchDemandOrderDetail;
    }

    @RequestMapping("/showDemandOrder")
    public String showDemandOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {
        // 单据号
        String strId = req.getParameter("id");
        DemandOrderModel dOrder = new DemandOrderModel();

        dOrder.setCdocument(strId);

        DemandOrder demandOrder = new DemandOrder();
        demandOrder = demandOrderService.searchDemandOrderByOrderId(dOrder);

        List<DemandOrderDetailModel> demandOrderDetailes = new ArrayList<DemandOrderDetailModel>();
        demandOrderDetailes = demandOrderDetailService.searchDemandOrderDetailByOrderId(demandOrder);


        /**
         * 获取审批流流程的历史记录
         */
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(strId, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);


        model.addAttribute("demandOrder", demandOrder);
        model.addAttribute("demandOrderDetailes", demandOrderDetailes);

        return "purchase/demand_order_detail";
    }

    /**
     * 显示审批页面
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param typeId
     * @return
     */
    @RequestMapping("/examineDemandOrder")
    public String examineDemandOrder(Model model, HttpServletRequest req, Long offset, Long pageSize, String typeId) {
        // 单据号
        String strId = req.getParameter("id");
        DemandOrderModel dOrder = new DemandOrderModel();

        dOrder.setCdocument(strId);

        DemandOrder demandOrder = new DemandOrder();
        demandOrder = demandOrderService.searchDemandOrderByOrderId(dOrder);

        List<DemandOrderDetailModel> demandOrderDetailes = new ArrayList<DemandOrderDetailModel>();
        demandOrderDetailes = demandOrderDetailService.searchDemandOrderDetailByOrderId(demandOrder);
        for (DemandOrderDetailModel dod : demandOrderDetailes) {
            if (dod.getAllocationCount() == null) {
                dod.setAllocationCount(0);
            }
        }

        //获取审批流流程的历史记录
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(strId, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());

        //获得权限
        UserRole userRole = new UserRole();
        userRole.setCusercode(req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        //采购经理角色ID  --改价格
        userRole.setCrolecode(Env.getProperty("role.purchasingManager.id"));
        if (userRoleService.searchUserRole(userRole, strId, Env.getProperty("demand.info.type.id")) > 0) {
            model.addAttribute("purchasingManager", "Y");
        } else {
            model.addAttribute("purchasingManager", "N");
        }
        // 归口经理角色ID --改调拨数量
        userRole.setCrolecode(Env.getProperty("role.attachManager.id"));
        if (userRoleService.searchUserRole(userRole, strId, Env.getProperty("demand.info.type.id")) > 0) {
            model.addAttribute("attachManager", "Y");
        } else {
            model.addAttribute("attachManager", "N");
        }
        //平台财务经理角色ID --改资产分类
        userRole.setCrolecode(Env.getProperty("role.platformFinanceManager.id"));
        if (userRoleService.searchUserRole(userRole, strId, Env.getProperty("demand.info.type.id")) > 0) {
            model.addAttribute("platformFinanceManager", "Y");
        } else {
            model.addAttribute("platformFinanceManager", "N");
        }

        model.addAttribute("appHistoryPager", appHistoryPager);


        model.addAttribute("demandOrder", demandOrder);
        model.addAttribute("demandOrderDetailes", demandOrderDetailes);

        return "purchase/demand_order_examine";
    }

    /**
     * 展示修改页面
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @return
     */
    @RequestMapping("/updateDemandOrder")
    public String updateDemandOrder(Model model, HttpServletRequest req, Long offset, Long pageSize) {
        // 单据号
        String strId = req.getParameter("id");
        DemandOrderModel dOrder = new DemandOrderModel();

        dOrder.setCdocument(strId);

        DemandOrder demandOrder = new DemandOrder();
        demandOrder = demandOrderService.searchDemandOrderByOrderId(dOrder);

        String loginUsernameCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();

        //当登录人不是申请人时 也不是授权
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUsernameCode);
        List<UserInfo> searchCauthorizerCode = userInfoService.searchCauthorizerCode(userInfo);
        for (UserInfo userInfo2 : searchCauthorizerCode) {
            if (demandOrder.getApplyUserCode().equals(userInfo2.getCauthorizerCode())) {
                demandOrder.setIsAuthorize("Y");
            }
        }
        if (!loginUsernameCode.equals(demandOrder.getApplyUserCode()) && !"Y".equals(demandOrder.getIsAuthorize())) {
            model.addAttribute("updateErrorMessage", "当前登录人不是申请人，不可修改订单！");
        }
        //单据正在审批或者已完成时不可以修改单据
        if (APPROVESTATE.PROCESSING.getApproveState().equals(demandOrder.getCapprovalstate()) || APPROVESTATE.DONE.getApproveState().equals(demandOrder.getCapprovalstate())) {
            model.addAttribute("updateErrorMessage", "审批状态单据不可修改！");
        }
        //关闭订单不可修改
        if (STATUS.YES_STATUS.getStatus().equals(demandOrder.getOrderStatus())) {
            model.addAttribute("updateErrorMessage", "已关闭订单不可修改！");
        }
        List<DemandOrderDetailModel> demandOrderDetailes = new ArrayList<DemandOrderDetailModel>();
        demandOrderDetailes = demandOrderDetailService.searchDemandOrderDetailByOrderId(demandOrder);
        for (DemandOrderDetail dod : demandOrderDetailes) {
            if (dod.getAllocationCount() == null) {
                dod.setAllocationCount(0);
            }
            if (dod.getInquiryPrice() != null) { //通过这个判断是不是从审批过程中退回的 如果这个不能判断，用审批状态判断
                dod.setReferenceUnitPrice(dod.getInquiryUnitPrice());
                dod.setReferencePrice(dod.getInquiryPrice());
            }
        }

        List<AssetUnit> assetUnitList = assetUnitService.getAssetUnitList();

        model.addAttribute("assetUnitList", assetUnitList);

        model.addAttribute("demandOrder", demandOrder);
        model.addAttribute("demandOrderDetailes", demandOrderDetailes);

        //准备基础列表数据
        //办公地点
        List<UserInfo> addresslist = userInfoService.searchAddressInfo();

        //UserInfo userInfo = new UserInfo();
        userInfo.setCemployeecode(loginUsernameCode);
        //根据登录人 查询 所属部门
        List<UserInfo> empdeptlist = userInfoService.searchOpdeptInfo(userInfo);

        //大区总监角色ID
        UserRole userRole = new UserRole();
        userRole.setCusercode(req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        userRole.setCrolecode(Env.getProperty("role.regionalDirector.id"));
        if (userRoleService.searchUserRole(userRole, strId, Env.getProperty("demand.info.type.id")) > 0) {
            model.addAttribute("regionalDirector", "Y");
        } else {
            model.addAttribute("regionalDirector", "N");
        }

        model.addAttribute("empdeptlist", empdeptlist);
        model.addAttribute("addresslist", addresslist);

        return "purchase/demand_order_update";
    }

    /**
     * 导出
     *
     * @param model
     * @param req
     * @param response
     * @param demandOrder
     */
    @RequestMapping(value = "/api/exportDemand", method = RequestMethod.GET)
    @ResponseBody
    public void exportDemand(ModelMap model, HttpServletRequest req, HttpServletResponse response,
                             DemandOrder demandOrder) {
        DemandOrderModel demandOrderModel = new DemandOrderModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 单据号
        String strDemandOrderId = req.getParameter("txtDemandOrderId");
        try {
            // 开始时间
            demandOrderModel.setApplyDateStart(sdf.parse(req.getParameter("txtBeginDate")));
        } catch (Exception e) {
            demandOrderModel.setApplyDateStart(null);
        }
        try {
            // 结束时间
            demandOrderModel.setApplyDateEnd(sdf.parse(req.getParameter("txtEndDate")));
        } catch (Exception e) {
            demandOrderModel.setApplyDateEnd(null);
        }

        String strApplyUser = req.getParameter("txtApplyUser");


        // 资产类型
        String strAssetType = req.getParameter("txtAssetType");
        String strCapprovalstates = req.getParameter("ckCapprovalstate");
        String strDraft = req.getParameter("ckDraft");
        String strOrderStatus = req.getParameter("ckOrderStatus");
        // 申请部门
        String strBudgetDepartmentCode = req.getParameter("budgetDepartmentCode");

        demandOrderModel.setCdocument(null == strDemandOrderId ? null : strDemandOrderId.trim());
        demandOrderModel.setApplyUser(null == strApplyUser ? null : strApplyUser.trim());
        demandOrderModel.setAssetType(null == strAssetType ? null : strAssetType.trim());
        demandOrderModel.setCapprovalstate(null == strCapprovalstates ? null : strCapprovalstates.trim());
        demandOrderModel.setDraft(null == strDraft ? "N" : strDraft.trim());
        demandOrderModel.setOrderStatus(null == strOrderStatus ? null : strOrderStatus.trim());
        demandOrderModel.setBudgetDepartmentCode(strBudgetDepartmentCode);

        demandOrderModel.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));

        //设置当前登录人编码
        demandOrderModel.setLoginUserCode(req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());

        String fileName = "采购需求列表.xlsx";
        demandOrderModel.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        List<DemandOrder> tsList = demandOrderService.exportDemandOrder(demandOrderModel);
        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportDemandTemplate exportDemandTemplate = new ExportDemandTemplate();
            exportDemandTemplate.doExport(response, fileName, tsList);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }

    }

    /**
     * 保存 新增
     *
     * @param demandOrderWrapper
     * @return
     */
    @RequestMapping("/api/saveDemand")
    @ResponseBody
    public ExecuteResult<DemandOrder> saveDemand(@RequestBody DemandOrderWrapper demandOrderWrapper) {
        ExecuteResult<DemandOrder> executeResult = new ExecuteResult<DemandOrder>();
        //表头信息
        DemandOrder demandOrder = demandOrderWrapper.getDemandOrder();
        //明细信息
        List<DemandOrderDetail> demandOrderDetails = demandOrderWrapper.getDemandOrderDetails();
        //验证是否选择项目编码
        // 验证明细行必须大于0
        if (demandOrderDetails.size() == 0) {
            executeResult.addErrorMessage("明细不能少于1行！");
            return executeResult;
        }
        for (DemandOrderDetail dod : demandOrderDetails) {
            if ("".equals(dod.getProjectCode())) {
                executeResult.addErrorMessage("项目编码不能为空！");
                break;
            }
            if (dod.getDemandCount() < 1) {
                executeResult.addErrorMessage("需求数量不能小于1！");
                break;
            }
            dod.setInquiryPrice(dod.getReferencePrice());
            dod.setInquiryUnitPrice(dod.getReferenceUnitPrice());
        }
        demandOrderWrapper.setDemandOrderDetails(demandOrderDetails);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        //验证是否选择项目编码  要求到位日期必须大于当前日期
        Date date = new Date();
        for (DemandOrderDetail dod : demandOrderDetails) {
            if ("".equals(dod.getProjectCode())) {
                executeResult.addErrorMessage("项目编码不能为空！");
                break;
            }
            if (date.after(dod.getRequirementsDate())) {
                executeResult.addErrorMessage("要求到位日期必须大于当前日期！");
                break;
            }
        }
        if (!executeResult.isSuccess()) {
            return executeResult;
        }

        //根据公司编码、资产类型、车类类型 获得归口部门及归口部门经理
        /*DemandOrder searchAttachDepartByOrder = demandOrderService.searchAttachDepartByOrder(demandOrder);
        demandOrder.setAttachDepartManagerCode(searchAttachDepartByOrder.getAttachDepartManagerCode());
		demandOrder.setAttachDepartManagerName(searchAttachDepartByOrder.getAttachDepartManagerName());
		demandOrder.setAttachDepartCode(searchAttachDepartByOrder.getAttachDepartCode());
		demandOrder.setAttachDepartName(searchAttachDepartByOrder.getAttachDepartName());*/

        if (STATUS.NO.getStatus().equals(demandOrder.getDraft())) {
            demandOrder.setCapprovalstate("等待审批");
        }
        demandOrder.setOrderStatus("未关闭");
        demandOrder.setPrint("N");
        demandOrder.setCreateBy(demandOrder.getApplyUser());
        demandOrder.setCreateByCode(demandOrder.getApplyUserCode());
        demandOrder.setUpdateBy(demandOrder.getApplyUser());
        demandOrder.setUpdateByCode(demandOrder.getApplyUserCode());
        demandOrder.setCreateDate(new Date());
        demandOrder.setUpdateDate(new Date());
        demandOrder.setIamoney(demandOrder.getApplyBudgetMoney());
        //给订单明细赋值
        for (DemandOrderDetail demandOrderDetail : demandOrderDetails) {
            //设置订单编号
            demandOrderDetail.setDemandOrderId(demandOrder.getCdocument());
            //设置需求部门
            demandOrderDetail.setApplyDepartment(demandOrder.getBudgetDepartmentName());
            demandOrderDetail.setApplyDepartmentCode(demandOrder.getBudgetDepartmentCode());
            //设置未申请数量(申请时未申请数量等于需求数量)
            demandOrderDetail.setNotApplyCount(demandOrderDetail.getDemandCount());
            //设置成本中心
            demandOrderDetail.setCostCenter(demandOrder.getCostCenter());
            demandOrderDetail.setCostCenterCode(demandOrder.getCostCenterCode());
            //设置资产类型
            demandOrderDetail.setAssetType(demandOrder.getAssetType());
            //设置时间和维护人
            demandOrderDetail.setApplyUser(demandOrder.getApplyUser());
            demandOrderDetail.setApplyUserCode(demandOrder.getApplyUserCode());
            demandOrderDetail.setCreateBy(demandOrder.getCreateBy());
            demandOrderDetail.setCreateByCode(demandOrder.getCreateByCode());
            demandOrderDetail.setUpdateByCode(demandOrder.getUpdateByCode());
            demandOrderDetail.setUpdateBy(demandOrder.getUpdateBy());
            demandOrderDetail.setCreateDate(demandOrder.getCreateDate());
            demandOrderDetail.setUpdateDate(demandOrder.getUpdateDate());

        }
        //为了绑定审批流 先把表头信息保存
        ExecuteResult<DemandOrder> extRst = demandOrderService.saveDemandOrder(demandOrder);
        if (!extRst.isSuccess()) {
            return extRst;
        }
        if (WARNSTATE.YES.getWarnstate().equals(demandOrder.getIsMail())) {
            demandOrder.setIsMail("Y");
        } else {
            demandOrder.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(demandOrder.getIsPhoneMessage())) {
            demandOrder.setIsPhoneMessage("Y");
        } else {
            demandOrder.setIsPhoneMessage("N");
        }
        //保存表单明细   并且绑定审批流
        try {
            executeResult = demandOrderService.addSaveDemand(demandOrder, demandOrderDetails);
        } catch (OutBudgetException e) {
            LOGGER.error("e", e);
            workFlowService.deleteGarbegeWorkFlowData(demandOrder.getCdocument(), Env.getProperty("demand.info.type.id"));
            demandOrderService.delDemandAndWorkFlow(demandOrder);
            executeResult.addErrorMessage(e.getMessage());
            return executeResult;
        } catch (RemoteException e) {
            LOGGER.error("e", e);
            workFlowService.deleteGarbegeWorkFlowData(demandOrder.getCdocument(), Env.getProperty("demand.info.type.id"));
            demandOrderService.delDemandAndWorkFlow(demandOrder);
            executeResult.addErrorMessage("保存行明细失败");
            return executeResult;
        } catch (Exception e) {
            LOGGER.error("e", e);
            workFlowService.deleteGarbegeWorkFlowData(demandOrder.getCdocument(), Env.getProperty("demand.info.type.id"));
            demandOrderService.delDemandAndWorkFlow(demandOrder);
            executeResult.addErrorMessage("保存行明细失败");
            return executeResult;
        }
        //如果保存失败  删除表头信息 并且删除审批流
        if (!executeResult.isSuccess()) {
            //删除审批流
            workFlowService.deleteGarbegeWorkFlowData(demandOrder.getCdocument(), Env.getProperty("demand.info.type.id"));
            demandOrderService.delDemandAndWorkFlow(demandOrder);
        } else {
            if ("N".equals(demandOrder.getDraft())) {
                DemandOrder searchDemandOrderByOrderId = demandOrderService.searchDemandOrderByOrderId(demandOrder);
                ExecuteResult<Employee> er = remindedService.BellReminder(searchDemandOrderByOrderId.getCnexthandlercode(), searchDemandOrderByOrderId.getCdocument(), demandOrder.getIsPhoneMessage(), demandOrder.getIsMail());
                //executeResult.setSuccessMessage(er.getSuccessMessage());
                if (!er.isSuccess()) {
                    executeResult.addErrorMessage("保存成功，但是邮件或短信发送失败");
                    for (String str : er.getErrorMessages()) {
                        executeResult.addErrorMessage(str);
                    }
                }
            }
        }
        return executeResult;
    }


    /**
     * 保存修改页面
     *
     * @param demandOrderWrapper
     * @return
     */
    @RequestMapping("/api/saveUpdateDemand")
    @ResponseBody
    public ExecuteResult<DemandOrder> saveUpdateDemand(@RequestBody DemandOrderWrapper demandOrderWrapper) {
        ExecuteResult<DemandOrder> executeResult = new ExecuteResult<DemandOrder>();
        //表头信息
        DemandOrder demandOrder = demandOrderWrapper.getDemandOrder();
        //明细信息
        List<DemandOrderDetail> demandOrderDetails = demandOrderWrapper.getDemandOrderDetails();
        // 验证明细行必须大于0
        if (demandOrderDetails.size() == 0) {
            executeResult.addErrorMessage("明细不能少于1行！");
            return executeResult;
        }
        //验证是否选择项目编码
        for (DemandOrderDetail dod : demandOrderDetails) {
            if ("".equals(dod.getProjectCode())) {
                executeResult.addErrorMessage("项目编码不能为空！");
                break;
            }
            if (dod.getDemandCount() < 1) {
                executeResult.addErrorMessage("需求数量不能小于1！");
                break;
            }
            dod.setInquiryPrice(dod.getReferencePrice());
            dod.setInquiryUnitPrice(dod.getReferenceUnitPrice());
        }
        demandOrderWrapper.setDemandOrderDetails(demandOrderDetails);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        //根据公司编码、资产类型、车类类型 获得归口部门及归口部门经理
        /*DemandOrder searchAttachDepartByOrder = demandOrderService.searchAttachDepartByOrder(demandOrder);
        demandOrder.setAttachDepartManagerCode(searchAttachDepartByOrder.getAttachDepartManagerCode());
		demandOrder.setAttachDepartManagerName(searchAttachDepartByOrder.getAttachDepartManagerName());
		demandOrder.setAttachDepartCode(searchAttachDepartByOrder.getAttachDepartCode());
		demandOrder.setAttachDepartName(searchAttachDepartByOrder.getAttachDepartName());*/

        if ("N".equals(demandOrder.getDraft())) {
            demandOrder.setCapprovalstate("等待审批");
            //如果是要提交正式，判断是否跨不提交是否跨过资产会计（资产会计可以改资产分类，获取资产编码）
            String billCode = demandOrder.getCdocument();
            String typeId = Env.getProperty("demand.info.type.id");
            String roleId = Env.getProperty("role.platformFinanceManager.id");
            String activityid = demandOrder.getActivityId();
            String roleids = applyOrderService.searchIsCrossAssetAccounting(billCode, typeId, roleId, activityid);
            if (StringUtils.isNotBlank(roleids)) {
                executeResult.addErrorMessage("不能跨过平台财务提交！");
                return executeResult;
            }
        }
        demandOrder.setOrderStatus("未关闭");
        demandOrder.setCreateBy(demandOrder.getApplyUser());
        demandOrder.setCreateByCode(demandOrder.getApplyUserCode());
        demandOrder.setUpdateBy(demandOrder.getApplyUser());
        demandOrder.setUpdateByCode(demandOrder.getApplyUserCode());
        //demandOrder.setCreateDate(new Date());
        demandOrder.setUpdateDate(new Date());
        demandOrder.setIamoney(demandOrder.getApplyBudgetMoney());
        //给订单明细赋值
        for (DemandOrderDetail demandOrderDetail : demandOrderDetails) {
            //设置订单编号
            demandOrderDetail.setDemandOrderId(demandOrder.getCdocument());
            //设置未申请数量(申请时未申请数量等于需求数量)
            demandOrderDetail.setNotApplyCount(demandOrderDetail.getDemandCount());
            //设置需求部门
            demandOrderDetail.setApplyDepartment(demandOrder.getBudgetDepartmentName());
            demandOrderDetail.setApplyDepartmentCode(demandOrder.getBudgetDepartmentCode());
            //设置成本中心
            demandOrderDetail.setCostCenter(demandOrder.getCostCenter());
            demandOrderDetail.setCostCenterCode(demandOrder.getCostCenterCode());
            //设置资产类型
            demandOrderDetail.setAssetType(demandOrder.getAssetType());
            //设置时间和维护人

            demandOrderDetail.setApplyUser(demandOrder.getApplyUser());
            demandOrderDetail.setApplyUserCode(demandOrder.getApplyUserCode());
            demandOrderDetail.setCreateBy(demandOrder.getCreateBy());
            demandOrderDetail.setCreateByCode(demandOrder.getCreateByCode());
            demandOrderDetail.setUpdateByCode(demandOrder.getUpdateByCode());
            demandOrderDetail.setUpdateBy(demandOrder.getUpdateBy());
            demandOrderDetail.setCreateDate(demandOrder.getCreateDate());
            demandOrderDetail.setUpdateDate(demandOrder.getUpdateDate());

        }
        //修改前的订单
        DemandOrder oldDemandOrder = demandOrderService.searchDemandOrderByOrderId(demandOrder);
        //修改订单
        demandOrderService.update(demandOrder);
        if (WARNSTATE.YES.getWarnstate().equals(demandOrder.getIsMail())) {
            demandOrder.setIsMail("Y");
        } else {
            demandOrder.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(demandOrder.getIsPhoneMessage())) {
            demandOrder.setIsPhoneMessage("Y");
        } else {
            demandOrder.setIsPhoneMessage("N");
        }
        try {
            executeResult = demandOrderService.addSaveUpdateDemand(demandOrder, demandOrderDetails, oldDemandOrder);
        } catch (OutBudgetException e) {
            LOGGER.error("e", e);
            demandOrderService.update(oldDemandOrder);
            executeResult.addErrorMessage(e.getMessage());
            return executeResult;
        } catch (RemoteException e) {
            LOGGER.error("e", e);
            demandOrderService.update(oldDemandOrder);
            executeResult.addErrorMessage(e.getMessage());
            return executeResult;
        } catch (Exception e) {
            LOGGER.error("e", e);
            demandOrderService.update(oldDemandOrder);
            executeResult.addErrorMessage("保存行明细失败");
            return executeResult;
        }
        // 如果保存失败 恢复订单
        if (!executeResult.isSuccess()) {
            demandOrderService.update(oldDemandOrder);
        } else {
            if ("N".equals(demandOrder.getDraft())) {
                DemandOrder searchDemandOrderByOrderId = demandOrderService.searchDemandOrderByOrderId(demandOrder);
                ExecuteResult<Employee> er = remindedService.BellReminder(searchDemandOrderByOrderId.getCnexthandlercode(), searchDemandOrderByOrderId.getCdocument(), demandOrder.getIsPhoneMessage(), demandOrder.getIsMail());
                //executeResult.setSuccessMessage(er.getSuccessMessage());
                if (!er.isSuccess()) {
                    executeResult.addErrorMessage("修改成功，但是邮件或短信发送失败");
                    for (String str : er.getErrorMessages()) {
                        executeResult.addErrorMessage(str);
                    }
                }
            }
        }
        return executeResult;
    }

    @RequestMapping("/api/agreeDemand")
    @ResponseBody
    public ExecuteResult<DemandOrder> agreeDemand(@RequestBody DemandOrderExamineWrapper demandOrderExamineWrapper, HttpServletRequest request) {
        ExecuteResult<DemandOrder> executeResult = new ExecuteResult<DemandOrder>();
        DemandOrderModel demandOrderModelReq = demandOrderExamineWrapper.getDemandOrderModel();
        List<DemandOrderDetail> demandOrderDetails = demandOrderExamineWrapper.getDemandOrderDetails();
        /*if (StringUtils.isBlank(demandOrderModelReq.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }*/

        //获得权限
        UserRole userRole = new UserRole();
        userRole.setCusercode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        userRole.setCrolecode(Env.getProperty("role.platformFinanceManager.id"));
        if (userRoleService.searchUserRole(userRole, demandOrderModelReq.getCdocument(), Env.getProperty("demand.info.type.id")) > 0) {
            for (DemandOrderDetail demandOrderDetail : demandOrderDetails) {
                if (demandOrderDetail.getAssetClassification() == null
                        || "".equals(demandOrderDetail.getAssetClassification())) {
                    executeResult.addErrorMessage("资产分类不能为空！");
                    return executeResult;
                }
            }
        }

        userRole.setCusercode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        //采购经理角色ID  --改价格
        userRole.setCrolecode(Env.getProperty("role.purchasingManager.id"));
        boolean isPurchasingManager = false;
        if (userRoleService.searchUserRole(userRole, demandOrderModelReq.getCdocument(), Env.getProperty("demand.info.type.id")) > 0) {
            isPurchasingManager = true;
        }
        //修改前的订单
        DemandOrder oldDemandOrder = demandOrderService.searchDemandOrderByOrderId(demandOrderModelReq);
        if (!request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim().equals(oldDemandOrder.getCnexthandlercode())) {
            executeResult.addErrorMessage("单据已被审批，或没有审批权限！");
            return executeResult;
        }
        //获得调拨数量
        boolean flag = false;
        BigDecimal sumMoney = BigDecimal.ZERO;
        if (demandOrderDetails != null) {
            for (DemandOrderDetail demandOrderDetail : demandOrderDetails) {
                if (demandOrderDetail.getInquiryPrice() == null) {
                    break;
                }
                sumMoney = sumMoney.add(demandOrderDetail.getInquiryPrice()); //扣除调拨数量*单价后的总价
                if (demandOrderDetail.getAllocationCount() > 0) {
                    flag = true;
                    // 设置标记本次是修改了调拨数量
                    demandOrderModelReq.setIsAllocationCount("Y");
                    demandOrderDetail.setIsAllocationCount("Y");
                }
            }
        }
        if (flag) {
            demandOrderModelReq.setIamoney(sumMoney);
        }

        // 修改订单 如果有预算金额说明是需要修改预算金额的，如果没有预算金额就把修改前预算金额传进同意审批流里
        if (demandOrderModelReq.getIamoney() != null/* && demandOrderModelReq.getIamoney() != BigDecimal.ZERO*/) {
            demandOrderService.updateExamine(demandOrderModelReq);
        } else {
            demandOrderModelReq.setIamoney(oldDemandOrder.getIamoney());
        }

        DemandOrderModel demandOrderModel = demandOrderService.searchDemandOrderModelByOrderId(demandOrderModelReq);
        demandOrderModel.setCdocument(demandOrderModelReq.getCdocument());
        demandOrderModel.setIdea(demandOrderModelReq.getIdea());
        demandOrderModel.setIsMail(demandOrderModelReq.getIsMail());
        demandOrderModel.setIsPhoneMessage(demandOrderModelReq.getIsPhoneMessage());
        demandOrderModel.setActivityId(demandOrderModelReq.getActivityId());
        demandOrderModel.setIsAllocationCount(demandOrderModelReq.getIsAllocationCount());

        // 获得当前登录人  部门 
        String applyUsername = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        demandOrderModel.setLoginUserName(applyUsername);
        String applyUsernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        demandOrderModel.setLoginUserCode(applyUsernameCode);

        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        if (userDeptInfo == null) {
            executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
        }
        demandOrderModel.setUserDepCode(userDeptInfo.getDepartmentCode());

        if (WARNSTATE.YES.getWarnstate().equals(demandOrderModel.getIsMail())) {
            demandOrderModel.setIsMail("Y");
        } else {
            demandOrderModel.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(demandOrderModel.getIsPhoneMessage())) {
            demandOrderModel.setIsPhoneMessage("Y");
        } else {
            demandOrderModel.setIsPhoneMessage("N");
        }

        demandOrderExamineWrapper.setDemandOrderModel(demandOrderModel);
        executeResult = demandOrderService.agreeWorkFlow(demandOrderExamineWrapper, isPurchasingManager);
        //如果保存失败  恢复订单
        DemandOrder searchDemandOrderByOrderId = demandOrderService.searchDemandOrderByOrderId(demandOrderModel);
        if (!executeResult.isSuccess()) {
            demandOrderService.updateExamine(oldDemandOrder);
        } else {
            if (StringUtils.isNotBlank(searchDemandOrderByOrderId.getCnexthandlercode())) {
                ExecuteResult<Employee> er = remindedService.BellReminder(searchDemandOrderByOrderId.getCnexthandlercode(), searchDemandOrderByOrderId.getCdocument(), demandOrderModel.getIsPhoneMessage(), demandOrderModel.getIsMail());
                //executeResult.setSuccessMessage(er.getSuccessMessage());
                if (!er.isSuccess()) {
                    executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                    for (String str : er.getErrorMessages()) {
                        executeResult.addErrorMessage(str);
                    }
                }
            }
        }

        //单据已完成状态 进行判断需求单进行关闭
        if (APPROVESTATE.DONE.getApproveState().equals(searchDemandOrderByOrderId.getCapprovalstate())) {
            demandOrderService.updateSearchCloseDemand(searchDemandOrderByOrderId);
        }
        return executeResult;
    }

    @RequestMapping("/api/denyDemand")
    @ResponseBody
    public ExecuteResult<DemandOrder> denyDemand(@RequestBody DemandOrderModel demandOrder, HttpServletRequest request) {
        ExecuteResult<DemandOrder> executeResult = new ExecuteResult<DemandOrder>();
        
        /*if (StringUtils.isBlank(demandOrder.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }*/

        DemandOrderModel demandOrderModel = demandOrderService.searchDemandOrderModelByOrderId(demandOrder);
        

        /*if (APPROVESTATE.WAITING.getApproveState().equals(demandOrderModel.getCapprovalstate())) {
            executeResult.addErrorMessage(demandOrderModel.getCapprovalstate() + "状态的单据不允许退回");
            return executeResult;
        }
        if (APPROVESTATE.DONE.getApproveState().equals(demandOrderModel.getCapprovalstate())) {
            executeResult.addErrorMessage("已完成的单据不可以退回");
            return executeResult;
        }*/

        demandOrderModel.setIdea(demandOrder.getIdea());
        demandOrderModel.setIsMail(demandOrder.getIsMail());
        demandOrderModel.setIsPhoneMessage(demandOrder.getIsPhoneMessage());
        demandOrderModel.setActivityId(demandOrder.getActivityId());

        // 获得当前登录人  部门 
        String applyUsername = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        demandOrderModel.setLoginUserName(applyUsername);
        String applyUsernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        demandOrderModel.setLoginUserCode(applyUsernameCode);

        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        if (userDeptInfo == null) {
            executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
        }
        demandOrderModel.setUserDepCode(userDeptInfo.getDepartmentCode());

        if (WARNSTATE.YES.getWarnstate().equals(demandOrderModel.getIsMail())) {
            demandOrderModel.setIsMail("Y");
        } else {
            demandOrderModel.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(demandOrderModel.getIsPhoneMessage())) {
            demandOrderModel.setIsPhoneMessage("Y");
        } else {
            demandOrderModel.setIsPhoneMessage("N");
        }
        executeResult = demandOrderService.denyWorkFlow(demandOrderModel);
        if (!executeResult.isSuccess()) {
            return executeResult;
        } else {
            DemandOrder searchDemandOrderByOrderId = demandOrderService.searchDemandOrderByOrderId(demandOrderModel);
            if (StringUtils.isNotBlank(searchDemandOrderByOrderId.getCnexthandlercode())) {
                ExecuteResult<Employee> er = remindedService.BellReminder(searchDemandOrderByOrderId.getCnexthandlercode(), searchDemandOrderByOrderId.getCdocument(), demandOrderModel.getIsPhoneMessage(), demandOrderModel.getIsMail());
                //executeResult.setSuccessMessage(er.getSuccessMessage());
                if (!er.isSuccess()) {
                    executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                    for (String str : er.getErrorMessages()) {
                        executeResult.addErrorMessage(str);
                    }
                }
            }
        }
        return executeResult;
    }

    @RequestMapping("/api/dropDemand")
    @ResponseBody
    public ExecuteResult<DemandOrder> dropDemand(@RequestBody DemandOrderModel demandOrder, HttpServletRequest request) {
        ExecuteResult<DemandOrder> executeResult = new ExecuteResult<DemandOrder>();
        DemandOrder searchDemandOrderByOrderId = demandOrderService.searchDemandOrderByOrderId(demandOrder);
        if (STATUS.YES_STATUS.getStatus().equals(searchDemandOrderByOrderId.getOrderStatus())) {
            executeResult.addErrorMessage("已关闭的单据不可弃审!");
            return executeResult;
        }
        Long searchNextApplyCount = demandOrderService.searchNextApplyCount(demandOrder);
        if (searchNextApplyCount > 0) {
            executeResult.addErrorMessage("已生成采购申请单的单据不可弃审!");
            return executeResult;
        }
        /*if (StringUtils.isBlank(demandOrder.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }*/
        DemandOrderModel demandOrderModel = demandOrderService.searchDemandOrderModelByOrderId(demandOrder);

        demandOrderModel.setIdea(demandOrder.getIdea());
        demandOrderModel.setIsMail(demandOrder.getIsMail());
        demandOrderModel.setIsPhoneMessage(demandOrder.getIsPhoneMessage());

        // 获得当前登录人  部门 
        String applyUsername = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        demandOrderModel.setLoginUserName(applyUsername);
        String applyUsernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        demandOrderModel.setLoginUserCode(applyUsernameCode);

        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        if (userDeptInfo == null) {
            executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
        }
        demandOrderModel.setUserDepCode(userDeptInfo.getDepartmentCode());

        if (WARNSTATE.YES.getWarnstate().equals(demandOrderModel.getIsMail())) {
            demandOrderModel.setIsMail("Y");
        } else {
            demandOrderModel.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(demandOrderModel.getIsPhoneMessage())) {
            demandOrderModel.setIsPhoneMessage("Y");
        } else {
            demandOrderModel.setIsPhoneMessage("N");
        }
        executeResult = demandOrderService.dropWorkFlow(demandOrderModel);
        if (!executeResult.isSuccess()) {
            return executeResult;
        } else {
            DemandOrder searchDemandOrder = demandOrderService.searchDemandOrderByOrderId(demandOrderModel);
            if (StringUtils.isNotBlank(searchDemandOrder.getCnexthandlercode())) {
                ExecuteResult<Employee> er = remindedService.BellReminder(searchDemandOrder.getCnexthandlercode(), searchDemandOrder.getCdocument(),
                        demandOrderModel.getIsPhoneMessage(), demandOrderModel.getIsMail());
                //executeResult.setSuccessMessage(er.getSuccessMessage());
                if (!er.isSuccess()) {
                    executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                    for (String str : er.getErrorMessages()) {
                        executeResult.addErrorMessage(str);
                    }
                }
            }
        }
        return executeResult;
    }

    @RequestMapping("/api/delDemand")
    @ResponseBody
    public ExecuteResult<DemandOrder> delDemand(String ids) {
        ExecuteResult<DemandOrder> result = new ExecuteResult<DemandOrder>();
        String[] demandIds = ids.split(",");

        DemandOrder demandOrder = new DemandOrder();
        if (demandIds != null && demandIds.length != '0') {
            for (String string : demandIds) {
                demandOrder.setCdocument(string);
                try {
                    DemandOrder searchDemandOrderByOrderId = demandOrderService.searchDemandOrderByOrderId(demandOrder);
                    List<DemandOrderDetailModel> demandOrderDetail = demandOrderDetailService
                            .searchDemandOrderDetailByOrderId(demandOrder);

                    demandOrderService.deleteDemandOrder(searchDemandOrderByOrderId, demandOrderDetail);
                } catch (OutBudgetException e) {
                    LOGGER.error("e", e);
                    result.addErrorMessage(e.getMessage());
                    return result;
                } catch (Exception e) {
                    LOGGER.error("e", e);
                    result.addErrorMessage(e.getMessage());
                    return result;
                }

                // 删除审批流
                workFlowService.deleteGarbegeWorkFlowData(string, Env.getProperty("demand.info.type.id"));
            }
        } else {
            result.addErrorMessage("请至少选择一条数据");
        }

        return result;
    }

    @RequestMapping("/api/closeDemand")
    @ResponseBody
    public ExecuteResult<DemandOrder> closeDemand(String ids) {
        ExecuteResult<DemandOrder> result = new ExecuteResult<DemandOrder>();
        String[] demandIds = ids.split(",");

        DemandOrder demandOrder = new DemandOrder();
        if (demandIds != null && demandIds.length != '0') {
            for (String string : demandIds) {
                demandOrder.setCdocument(string);
                DemandOrder searchDemandOrderByOrderId = demandOrderService.searchDemandOrderByOrderId(demandOrder);
                List<DemandOrderDetailModel> demandOrderDetail = demandOrderDetailService
                        .searchDemandOrderDetailByOrderId(demandOrder);
                demandOrderService.updateCloseDemandOrder(searchDemandOrderByOrderId, demandOrderDetail);
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
        DemandOrderModel dOrder = new DemandOrderModel();
        dOrder.setCdocument(cdocument);

        DemandOrder demandOrder = new DemandOrder();
        demandOrder = demandOrderService.searchDemandOrderByOrderId(dOrder);

        List<DemandOrderDetailModel> demandOrderDetailes = new ArrayList<DemandOrderDetailModel>();
        demandOrderDetailes = demandOrderDetailService.searchDemandOrderDetailByOrderId(demandOrder);

        demandOrderService.updatePrint(demandOrder);
        /**
         * 获取审批流流程的历史记录
         */
        List<ApproveHistoryCheckPrint> appHistoryPager = checkAcceptanceService.getApproveHistory(cdocument, typeId);
        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("demandOrder", demandOrder);
        model.addAttribute("demandOrderDetailes", demandOrderDetailes);
        return "purchase/demand_order_stamp";
    }

    @RequestMapping("/api/searchAssetsType")
    @ResponseBody
    public BudgetAssetstypeConfig searchAssetsType(BudgetAssetstypeConfig budgetAssetstypeConfig,
                                                   HttpServletRequest request) {
        BudgetAssetstypeConfig searchByBudgetAssetstype = budgetAssetstypeConfigService
                .searchByBudgetAssetstype(budgetAssetstypeConfig);
        return searchByBudgetAssetstype;
    }
}
