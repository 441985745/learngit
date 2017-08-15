package com.opple.fa.purchase.webapp.controller;

import com.opple.fa.assetcard.service.GeneralAssetService;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.entity.UserRole;
import com.opple.fa.config.model.STATUS;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.config.service.UserRoleService;
import com.opple.fa.purchase.entity.CheckAcceptMapping;
import com.opple.fa.purchase.entity.CheckAcceptance;
import com.opple.fa.purchase.entity.CheckAcceptanceDetail;
import com.opple.fa.purchase.entity.CheckMachine;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.entity.ReceiveGoods;
import com.opple.fa.purchase.entity.WARNSTATE;
import com.opple.fa.purchase.model.ApproveHistoryCheckPrint;
import com.opple.fa.purchase.model.CheckAcceptanceModel;
import com.opple.fa.purchase.model.CheckAcceptanceWrapper;
import com.opple.fa.purchase.service.CheckAcceptMappingService;
import com.opple.fa.purchase.service.CheckAcceptanceDetailService;
import com.opple.fa.purchase.service.CheckAcceptanceService;
import com.opple.fa.purchase.service.PurchaseCommonService;
import com.opple.fa.purchase.service.PurchaseOrderDetailService;
import com.opple.fa.purchase.service.PurchaseOrderService;
import com.opple.fa.purchase.service.ReceiveGoodsService;
import com.opple.fa.sap.model.IpHistoryModel;
import com.opple.fa.sap.model.IpInputModel;
import com.opple.fa.sap.model.IpZmm0010Model;
import com.opple.fa.sap.model.ItZmm0011Model;
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
import com.opple.util.ExportExcle;
import com.opple.util.Pager;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/checkAccept")
public class CheckAcceptanceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckAcceptanceController.class);
    @Resource
    private FileUploadService fileUploadService;
    @Resource
    private PurchaseOrderDetailService purchaseOrderDetailService;
    @Resource
    private CheckAcceptanceService checkAcceptanceService;
    @Resource
    private CheckAcceptMappingService checkAcceptMappingService;
    @Resource
    private CheckAcceptanceDetailService checkAcceptanceDetailService;
    @Resource
    private WorkFlowService workflowService;
    @Resource
    private UserService userService;
    @Resource
    private RemindedService remindedService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private SapUtilService sapUtilService;
    @Resource
    private PurchaseCommonService purchaseCommonService;
    @Resource
    private PurchaseOrderService purchaseOrderService;
    @Resource
    private AttchConfigService attchConfigService;
    @Resource
    private ReceiveGoodsService receiveGoodsService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private GeneralAssetService generalAssetService;

    /**
     * 采购收货list主页面 search方法
     */
    @RequestMapping("/searchCheckAcceptMapping")
    public String searchCheckAcceptMapping(Model model, HttpServletRequest req, Long offset, Long pageSize, CheckAcceptMapping checkAcceptMapping) {
        /*if ("".equals(checkAcceptMapping.getDoProcess()) || (null == (checkAcceptMapping.getDoProcess()))) {
            checkAcceptMapping.setDoProcess("Y");
		} else if ("ALL".equals(checkAcceptMapping.getDoProcess())) {
			checkAcceptMapping.setDoProcess("");
		}*/
        checkAcceptMapping.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));


        String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        checkAcceptMapping.setLoginUserName(loginUserName);
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        checkAcceptMapping.setLoginUserCode(loginUserCode);
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUserCode);
        List<UserInfo> searchCauthorizerCode = userInfoService.searchCauthorizerCode(userInfo);
        Pager<CheckAcceptMapping> pager = new Pager<CheckAcceptMapping>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<CheckAcceptMapping> checkMappingPager = new Pager<CheckAcceptMapping>();
        checkAcceptMapping.setSupplierName(null == checkAcceptMapping.getSupplierName() ? "" : checkAcceptMapping.getSupplierName().trim());
        checkAcceptMapping.setPurchaseSapNo(null == checkAcceptMapping.getPurchaseSapNo() ? "" : checkAcceptMapping.getPurchaseSapNo().trim());
        checkAcceptMapping.setEpMaterialdocu(null == checkAcceptMapping.getEpMaterialdocu() ? "" : checkAcceptMapping.getEpMaterialdocu().trim());
        checkAcceptMapping.setReceiveGoodsId(null == checkAcceptMapping.getReceiveGoodsId() ? "" : checkAcceptMapping.getReceiveGoodsId().trim());
        checkAcceptMapping.setCheckAcceptId(null == checkAcceptMapping.getCheckAcceptId() ? "" : checkAcceptMapping.getCheckAcceptId().trim());
        checkAcceptMapping.setSupplierName(null == checkAcceptMapping.getSupplierName() ? "" : checkAcceptMapping.getSupplierName().trim());
        checkAcceptMapping.setEpBelnr(null == checkAcceptMapping.getEpBelnr() ? "" : checkAcceptMapping.getEpBelnr().trim());
        checkAcceptMapping.setPurchaseId(null == checkAcceptMapping.getPurchaseId() ? "" : checkAcceptMapping.getPurchaseId().trim());
        checkAcceptMapping.setAssetsType(null == checkAcceptMapping.getAssetsType() ? "" : checkAcceptMapping.getAssetsType().trim());
        checkAcceptMapping.setSapAssetsCode(null == checkAcceptMapping.getSapAssetsCode() ? "" : checkAcceptMapping.getSapAssetsCode().trim());
        checkAcceptMapping.setAssetsName(null == checkAcceptMapping.getAssetsName() ? "" : checkAcceptMapping.getAssetsName().trim());
        checkAcceptMapping.setCostCenterCode(null == checkAcceptMapping.getCostCenterCode() ? "" : checkAcceptMapping.getCostCenterCode().trim());
        if ("".equals(checkAcceptMapping.getDoProcess()) || (null == (checkAcceptMapping.getDoProcess())) || "ALL".equals(checkAcceptMapping.getDoProcess()) || "Y".equals(checkAcceptMapping.getDoProcess())) { //待验收
            checkAcceptMapping.setDoProcess("Y");
            checkMappingPager = checkAcceptMappingService.searchCheckAcceptMapping(pager, checkAcceptMapping);
        } else if ("N".equals(checkAcceptMapping.getDoProcess())) { //已验收
            checkAcceptMapping.setDoProcess("");
            checkMappingPager = checkAcceptMappingService.searchCheckAcceptMappingAll(pager, checkAcceptMapping);
        }
        for (CheckAcceptMapping cam : checkMappingPager.getRecords()) {
            if (loginUserCode.equals(cam.getCheckApplyCode())) {
                cam.setIsAuthorize("Y");
                continue;
            }
            for (UserInfo userInfo2 : searchCauthorizerCode) {
                if (null != userInfo2.getCauthorizerCode() && userInfo2.getCauthorizerCode().equals(cam.getCheckApplyCode())) {
                    cam.setIsAuthorize("Y");
                }
            }
        }
        checkMappingPager.setUri(req.getRequestURI());
//		model.addAttribute("checkMappingPager", checkMappingPager);`
        model.addAttribute("checkAcceptMappingPager", checkMappingPager);
        List<UserInfo> companys = userInfoService.searchAllCompany();
        model.addAttribute("companys", companys);
        return "purchase/check_acceptance_list";
    }


    /**
     * 导出功能
     */
    @RequestMapping(value = "api/exportCheck", method = RequestMethod.GET)
    @ResponseBody
    public void exportReceive(Model model, HttpServletRequest req, HttpServletResponse response, CheckAcceptMapping checkAcceptMapping) {
        if ("".equals(checkAcceptMapping.getDoProcess()) || (null == (checkAcceptMapping.getDoProcess()))) { //待验收
            checkAcceptMapping.setDoProcess("Y");
        } else if ("ALL".equals(checkAcceptMapping.getDoProcess())) { //已验收
            checkAcceptMapping.setDoProcess("");
        }
        checkAcceptMapping.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        String loginUserName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        checkAcceptMapping.setLoginUserName(loginUserName);
        String loginUserCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        checkAcceptMapping.setLoginUserCode(loginUserCode);
        List<CheckAcceptMapping> list = new ArrayList<CheckAcceptMapping>();
        try {
            if ("".equals(checkAcceptMapping.getDoProcess()) || (null == (checkAcceptMapping.getDoProcess())) || "ALL".equals(checkAcceptMapping.getDoProcess()) || "Y".equals(checkAcceptMapping.getDoProcess())) { //待验收
                checkAcceptMapping.setDoProcess("Y");
                list = checkAcceptMappingService.exportCheck(checkAcceptMapping);
            } else if ("N".equals(checkAcceptMapping.getDoProcess())) { //已验收
                checkAcceptMapping.setDoProcess("");
                list = checkAcceptMappingService.exportCheckHaveChecked(checkAcceptMapping);
            }

        } catch (Exception e) {
            LOGGER.error("e", e);
        }
        String fileName = "验收详情.xlsx";
        String sheetName = "验收详情";
        int[] columnWidths = new int[]{10, 10, 10};
        Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
        data.put("1", new Object[]{"资产类型", "SAP订单号", "订单编号", "收货单据号", "验收单据号", "物料凭证号", "会计凭证号", "收货数量", "验收数量", "本次验收数量",
                "采购员", "供应商编码", "供应商", "公司名称", "公司编码", "验收日期", "审批状态", "验收申请人", "下级处理人", "是否已打印"});
        int dataIdx = 2;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        StringBuilder assetsType = null;
        StringBuilder sapPurchaseNo = null;
        StringBuilder purchaseId = null;
        StringBuilder receiveGoodsId = null;
        StringBuilder checkAcceptId = null;
        StringBuilder epMaterialdocu = null;
        StringBuilder epBelnr = null;
        StringBuilder goodsCountAll = null;
        StringBuilder checkCountAll = null;
        StringBuilder thisCheckCount = null;
        StringBuilder buyerName = null;
        StringBuilder supplierCode = null;
        StringBuilder supplierName = null;
        StringBuilder computerName = null;
        StringBuilder computerCode = null;
        StringBuilder checkDate = new StringBuilder("");
        StringBuilder capprovalstate = null;
        StringBuilder applyCheckUserName = null;
        StringBuilder cnexthandlername = null;
        StringBuilder isprint = null;
        for (CheckAcceptMapping checkAcceptMappings : list) {
            assetsType = new StringBuilder(checkAcceptMappings.getAssetsType() == null ? "" : checkAcceptMappings.getAssetsType());
            sapPurchaseNo = new StringBuilder(checkAcceptMappings.getPurchaseSapNo() == null ? "" : checkAcceptMappings.getPurchaseSapNo());
            purchaseId = new StringBuilder(checkAcceptMappings.getPurchaseId() == null ? "" : checkAcceptMappings.getPurchaseId());
            receiveGoodsId = new StringBuilder(checkAcceptMappings.getReceiveGoodsId() == null ? "" : checkAcceptMappings.getReceiveGoodsId());
            checkAcceptId = new StringBuilder(checkAcceptMappings.getCheckAcceptId() == null ? "" : checkAcceptMappings.getCheckAcceptId());
            epMaterialdocu = new StringBuilder(checkAcceptMappings.getEpMaterialdocu() == null ? "" : checkAcceptMappings.getEpMaterialdocu());
            epBelnr = new StringBuilder(checkAcceptMappings.getEpBelnr() == null ? "" : checkAcceptMappings.getEpBelnr());
            goodsCountAll = new StringBuilder(checkAcceptMappings.getGoodsCountAll() == null ? "" : checkAcceptMappings.getGoodsCountAll().toString());
            checkCountAll = new StringBuilder(checkAcceptMappings.getCheckCountAll() == null ? "" : checkAcceptMappings.getCheckCountAll().toString());
            thisCheckCount = new StringBuilder(checkAcceptMappings.getThisCheckCount() == null ? "" : checkAcceptMappings.getThisCheckCount().toString());
            buyerName = new StringBuilder(checkAcceptMappings.getBuyerName() == null ? "" : checkAcceptMappings.getBuyerName());
            supplierCode = new StringBuilder(checkAcceptMappings.getSupplierCode() == null ? "" : checkAcceptMappings.getSupplierCode());
            supplierName = new StringBuilder(checkAcceptMappings.getSupplierName() == null ? "" : checkAcceptMappings.getSupplierName());
            computerName = new StringBuilder(checkAcceptMappings.getComputerName() == null ? "" : checkAcceptMappings.getComputerName());
            computerCode = new StringBuilder(checkAcceptMappings.getComputerCode() == null ? "" : checkAcceptMappings.getComputerCode());
            if (null != checkAcceptMappings.getCheckDate() && !"".equals(checkAcceptMappings.getCheckDate())) {
                checkDate = new StringBuilder(sdf.format(checkAcceptMappings.getCheckDate()));
            }
            capprovalstate = new StringBuilder(checkAcceptMappings.getCapprovalstate() == null ? "" : checkAcceptMappings.getCapprovalstate());
            applyCheckUserName = new StringBuilder(checkAcceptMappings.getApplyCheckUserName() == null ? "" : checkAcceptMappings.getApplyCheckUserName());
            cnexthandlername = new StringBuilder(checkAcceptMappings.getCnexthandlername() == null ? "" : checkAcceptMappings.getCnexthandlername());
            isprint = new StringBuilder(checkAcceptMappings.getIsprint() == "Y" ? "是" : "否");
            Object[] newRow = new Object[]{
                    assetsType.toString(),
                    sapPurchaseNo.toString(),
                    purchaseId.toString(),
                    receiveGoodsId.toString(),
                    checkAcceptId.toString(),
                    epMaterialdocu.toString(),
                    epBelnr.toString(),
                    goodsCountAll.toString(),
                    checkCountAll.toString(),
                    thisCheckCount.toString(),
                    buyerName.toString(),
                    supplierCode.toString(),
                    supplierName.toString(),
                    computerName.toString(),
                    computerCode.toString(),
                    capprovalstate.toString(),
                    checkDate.toString(),
                    applyCheckUserName.toString(),
                    cnexthandlername.toString(),
                    isprint.toString()
            };
            data.put("" + dataIdx++, newRow);
        }
        try {
            ExportExcle.generateExcel(response, data, fileName, sheetName, columnWidths);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
    }

    /**
     * (待选择界面) 验收 头 行
     *
     * @param model
     * @param receiveGoodsId
     * @return
     */
    @RequestMapping("/searchTempReceiveGoodsByReceiveGoodsId")
    public String searchTempReceiveGoodsByReceiveGoodsId(Model model, String receiveGoodsId, HttpServletRequest request) {
        CheckAcceptMapping receiveGoodsTemp = checkAcceptMappingService.searchTempReceiveGoodsByReceiveGoodsId(receiveGoodsId);
        String userCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        List<CheckAcceptMapping> receiveGoodsDetailTemp = checkAcceptMappingService.searchTempReceiveGoodsDetailByReceiveGoodsId(receiveGoodsId, userCode);
        //判断当前登录人是不是归口管理员(归口部门工程师)
        String isManager = "";
        if (userCode.equals(receiveGoodsTemp.getAttachDepartAdminCode())) {
            isManager = "Y"; //是归口管理工程师
        } else {
            isManager = "N"; //不是归口管理工程师
        }
        model.addAttribute("receiveGoodsTemp", receiveGoodsTemp);
        model.addAttribute("receiveGoodsDetailTemp", receiveGoodsDetailTemp);
        model.addAttribute("isManager", isManager);
        String loginUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        model.addAttribute("loginUserName", loginUserName);
        return "purchase/check_acceptance_add";
    }

    /**
     * (待选择界面) 需求明细 所有的收货待验收的(checkMapping表)
     *
     * @param receiveGoodsId
     * @return
     */
    @RequestMapping("/api/searchAllDemandDetailIdByReceiveGoodsId")
    @ResponseBody
    public List<CheckAcceptMapping> searchAllDemandDetailIdByReceiveGoodsId(String receiveGoodsId, String receiveGoodsDetailId, HttpServletRequest request) {
        String userCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        List<CheckAcceptMapping> purchaseApplyMappinglist = checkAcceptMappingService.searchAllDemandDetailIdByReceiveGoodsId(receiveGoodsId, receiveGoodsDetailId, userCode);
        return purchaseApplyMappinglist;
    }

    /**
     * 新增验收单弹框(查询得到的验收单的头)
     * page1点击确定按钮后打开page3
     * @param receiveGoodsId
     * @param receiveGoodsDetailId
     * @return
     */
    @RequestMapping("/api/addReceiveGoods")
    @ResponseBody
    public List<Object> addReceiveGoods(String receiveGoodsId, String receiveGoodsDetailId, String applyDetailId) {
        // 查询 得到验收单的 头
        CheckAcceptMapping checkAcceptanceMapping = checkAcceptMappingService.getCheckAcceptanceByReceiveGoodsDetailId(receiveGoodsId, receiveGoodsDetailId);
        List<CheckAcceptMapping> checkAcceptanceDetaillist = checkAcceptMappingService.getCheckAcceptanceDetailByPurchaseDetailId(receiveGoodsId, applyDetailId);
        for (int i = 0; i < checkAcceptanceDetaillist.size(); i++) {
            checkAcceptanceDetaillist.get(i).setStorageLocation(checkAcceptanceDetaillist.get(i).getStorageLocation() == null ? "" : checkAcceptanceDetaillist.get(i).getStorageLocation());

            if (STATUS.YES.getStatus().equals(checkAcceptanceDetaillist.get(i).getIsBuildingContruction())) {
                checkAcceptanceDetaillist.get(i).setIsBuildingContruction("是");
            }
            if (STATUS.NO.getStatus().equals(checkAcceptanceDetaillist.get(i).getIsBuildingContruction())) {
                checkAcceptanceDetaillist.get(i).setIsBuildingContruction("否");
            }
        }
        // 生成验收编码用
        String str = "CGYS";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        str += sdf.format(date);
        Long searchNextSequence = checkAcceptanceService.searchNextSequence();
        // 0 代表前面补充0 4 代表长度为4 d 代表参数为正数型
        str += String.format("%04d", searchNextSequence);
        checkAcceptanceMapping.setCheckAcceptId(str);
        List<Object> resultlist = new ArrayList<Object>();
        resultlist.add(checkAcceptanceMapping);
        resultlist.add(checkAcceptanceDetaillist);
        return resultlist;
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/api/saveCheckAccept")
    @ResponseBody
    public ExecuteResult<CheckAcceptance> saveCheckAccept(@RequestBody CheckAcceptanceWrapper checkAcceptanceWrapper, HttpServletRequest request) {
        ExecuteResult<CheckAcceptance> executeResult = new ExecuteResult<CheckAcceptance>();
        checkAcceptanceWrapper.getCheckAcceptance().setAssetPerson(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim());
        checkAcceptanceWrapper.getCheckAcceptance().setAssetPersonCode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        CheckAcceptance checkAcceptance = checkAcceptanceWrapper.getCheckAcceptance();
        CheckMachine checkMachine = checkAcceptanceWrapper.getCheckMachine();
        if ("机器设备".equals(checkAcceptance.getAssetsType())) {
            checkMachine.setCheckId(checkAcceptance.getCheckAcceptId());
        }
        if (WARNSTATE.YES.getWarnstate().equals(checkAcceptance.getIsMail())) {
            checkAcceptance.setIsMail("Y");
        } else {
            checkAcceptance.setIsMail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(checkAcceptance.getIsPhoneMessage())) {
            checkAcceptance.setIsPhoneMessage("Y");
        } else {
            checkAcceptance.setIsPhoneMessage("N");
        }
        checkAcceptance.setApplyCheckUserCode(checkAcceptance.getAssetPersonCode());
        checkAcceptance.setApplyCheckUserName(checkAcceptance.getAssetPerson());
        PurchaseOrder purchaseOrder1 = new PurchaseOrder();
        purchaseOrder1.setCdocument(checkAcceptance.getPurchaseId());
        PurchaseOrder purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderIdOnly(purchaseOrder1);
        /**
         * 根据资产类型和成本中心从配置表中查询唯一信息，然后根据归口管理经理编码查询申请部门
         */
        AttchConfig attchConfig = new AttchConfig();
        attchConfig.setCostcenterCode(checkAcceptance.getCostCenterCode());
        attchConfig.setAssetType(checkAcceptance.getAssetsType());
        AttchConfig attchConfig1 = attchConfigService.searchByCostcenterCodeAndAssetType(attchConfig);
        checkAcceptance.setAttachDepartManagerCode(attchConfig1.getAttachDepartManagerCode());
        checkAcceptance.setAttachDepartManagerName(attchConfig1.getAttachDepartManagerName());
        List<CheckAcceptanceDetail> checkAcceptanceDetails = checkAcceptanceWrapper.getCheckAcceptanceDetails();
        if ("机器设备".equals(checkAcceptance.getAssetsType())) {
            for (CheckAcceptanceDetail cd : checkAcceptanceDetails) {
                List<FileUpload> listFile = fileUploadService.getFilesByDocument(cd.getSysteManagementCode());
                if (listFile.isEmpty()) {
                    executeResult.addErrorMessage("必须上传资产图片");
                    return executeResult;
                }

            }
        }
        List<CheckAcceptMapping> checkAcceptMappings = checkAcceptanceWrapper.getCheckAcceptMappings();
        Long isSave = 0L;
        try {
            isSave = checkAcceptMappingService.saveCheckAcceptance(checkAcceptance);
        } catch (Exception e) {
            LOGGER.error("e", e);
        }
        String deptCode = checkAcceptMappingService.searchDeptCode(attchConfig1.getAttachDepartManagerCode());
        CheckAcceptance ca1 = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptance.getCheckAcceptId());
        // 绑定审批流
        if (1 == isSave) {
            boolean ifRollback = false;
            try {
                WorkFlowStub stub = new WorkFlowStub();
                WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
                WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();
                createNextFlowStepParam.setBillCode(checkAcceptance.getCheckAcceptId());
                createNextFlowStepParam.setBillTableName(Env.getProperty("check.info.table"));
                createNextFlowStepParam.setBillCodeColumnName("CDOCUMENT");
                createNextFlowStepParam.setDeptCode(purchaseOrder.getBudgetDepartmentCode()); //(checkAcceptance.getApplyDepartmentCode()); // 采购申请部门
                createNextFlowStepParam.setDeptName(purchaseOrder.getBudgetDepartmentName());
                createNextFlowStepParam.setCreatorCode(checkAcceptance.getAssetPersonCode()); // 当前登录人（就是采购员）
                createNextFlowStepParam.setCreatorName(checkAcceptance.getAssetPerson());
                Calendar instance = Calendar.getInstance();
                instance.setTime(ca1.getCreateDate());
                createNextFlowStepParam.setCreateDate(instance);
                createNextFlowStepParam.setMoney("0"); // 单据金额
                createNextFlowStepParam.setTypeCode(Env.getProperty("check.info.type.id"));
                createNextFlowStepParam.setFeeItemCode("");
                createNextFlowStepParam.setIsEmail(checkAcceptance.getIsMail()); // 是否发送邮件提醒
                createNextFlowStepParam.setIsPhoneMessage(checkAcceptance.getIsPhoneMessage()); // 是否发送短信提醒
                createNextFlowStepParam.setCApprovalId(UUID.randomUUID().toString());
                createNextFlowStepParam.setIdea(checkAcceptance.getIdea());
                createNextFlowStepParam.setCCostCenterCode(checkAcceptance.getCostCenterCode()); // 成本中心编码
                createNextFlowStep.setParam(createNextFlowStepParam);
                createNextFlowStep.setUserCode(checkAcceptance.getAssetPersonCode());
                createNextFlowStep.setUserName(checkAcceptance.getAssetPerson());
                WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);
                if (StringUtils.isNotBlank(response.getErrMessage())) {
                    ifRollback = true;
                    executeResult.addErrorMessage(response.getErrMessage());
                    LOGGER.error("====" + response.getErrMessage() + "====");
                }
            } catch (AxisFault axisFault) {
                ifRollback = true;
                LOGGER.error("axisFault", axisFault);
            } catch (RemoteException e) {
                ifRollback = true;
                LOGGER.error("RemoteException", e);
            }
            if (ifRollback) {
                executeResult.addErrorMessage("调用审批流失败");
                // 调用审批流失败删除收货单表信息
                checkAcceptanceService.delCheckAcceptanceByCheckAcceptId(checkAcceptance.getCheckAcceptId());
                return executeResult;
            }

            executeResult.setSuccessMessage("绑定成功");
            boolean flag = false;
            Long isSaveDetail = 0L;
            try {
                isSaveDetail = checkAcceptMappingService.save(checkAcceptance, checkAcceptanceDetails, checkAcceptMappings, checkMachine);
            } catch (Exception e) {
                LOGGER.error("e", e);
                flag = true;
            }

            if (flag) { // 审批流绑定成功，但是单据保存失败
                executeResult.addErrorMessage("程序调用失败，请重新操作");
                checkAcceptanceService.delCheckAcceptanceByCheckAcceptId(checkAcceptance.getCheckAcceptId());
                workflowService.deleteGarbegeWorkFlowData(checkAcceptance.getCheckAcceptId(), Env.getProperty("check.info.type.id"));
            } else {
                try {
                    CheckAcceptance ca = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptance.getCheckAcceptId());
                    ExecuteResult<Employee> er = remindedService.BellReminder(
                            ca.getCnexthandlercode(),
                            ca.getCdocument(),
                            checkAcceptance.getIsPhoneMessage(),
                            checkAcceptance.getIsMail());
                    //executeResult.setSuccessMessage(er.getSuccessMessage());
                    if (!er.isSuccess()) {
                        executeResult.addErrorMessage("保存成功，但是邮件或短信发送失败");
                        for (String str : er.getErrorMessages()) {
                            executeResult.addErrorMessage(str);
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("e", e);
                }
            }
        }

        return executeResult;
    }

    /**
     * 删除方法，可以根据主表中LAST_CHECK_DATE（实际验收日期）是否为空来判断货物是是否走完审批流，有则走完，没则有两种情况：1正在走，2没绑定审批流
     * 可以通过是否有下一步处理人判断是否绑定审批流了，若没绑定，则可删除
     */
    @RequestMapping(value = "/api/deleteCheck")
    @ResponseBody
    public ExecuteResult<CheckAcceptance> deleteCheck(@RequestParam(value = "list[]", required = false) List<String> list) {
        ExecuteResult<CheckAcceptance> executeResult = new ExecuteResult<CheckAcceptance>();
        List<String> list2 = new ArrayList<String>(); //fileUuid集合
        List<FileUpload> fileUploads = new ArrayList<FileUpload>();
        List<CheckAcceptanceDetail> list1 = new ArrayList<CheckAcceptanceDetail>();
        for (String id : list) {
            list1 = checkAcceptanceDetailService.getCheckAcceptanceDetailByDocument(id);
        }
        try {
            checkAcceptMappingService.deleteCheck(list);

        } catch (Exception e) {
            LOGGER.error("e", e);
            executeResult.addErrorMessage("删除失败");
            return executeResult;
        }
        for (String li : list) {
            workflowService.deleteGarbegeWorkFlowData(li, Env.getProperty("check.info.type.id"));
        }
        for (CheckAcceptanceDetail cad : list1) {
            String document = cad.getSysteManagementCode();
            fileUploads = fileUploadService.getFilesByDocument(document);
            for (FileUpload fu : fileUploads) {
                list2.add(fu.getFileuuid());
            }
        }
        fileUploadService.deleteFile(list2);

        return executeResult;
    }

    /**
     * 审批页面 查询
     */
    @RequestMapping("/bindingCheckAcceptByCheckAcceptId")
    public String bindingCheckAcceptByCheckAcceptId(Model model, HttpServletRequest req, Long offset, Long pageSize, String checkAccpetId, String typeId) {
        CheckAcceptance checkAcceptance = checkAcceptanceService.getCheckAcceptanceByDocument(checkAccpetId); //基本信息
        CheckMachine checkMachine = checkAcceptanceService.getCheckMachineByCheckId(checkAccpetId);
        List<CheckAcceptanceDetail> checkAcceptanceDetaillist = checkAcceptanceDetailService.getCheckAcceptanceDetailByDocument(checkAccpetId); //行明细
        List<CheckAcceptanceDetail> list = new ArrayList<CheckAcceptanceDetail>();
        for (CheckAcceptanceDetail cam : checkAcceptanceDetaillist) { //遍历根据cardId查询资产卡片表中存的信息
            CheckAcceptanceDetail cam1 = checkAcceptMappingService.searchAssetFormation(cam);
            list.add(cam1);
        }
        /**
         * 获取审批流流程的历史记录
         */
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<ApproveHistory> appHistoryPager = workflowService.showApproveHistory(checkAccpetId, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());
        //获得权限
        UserRole userRole = new UserRole();
        userRole.setCusercode(req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        //判断当前登录人是不是归口管理员(归口部门工程师)
        String isManager = "";
        userRole.setCrolecode(Env.getProperty("role.putMangerPerson.id"));
        if (userRoleService.searchUserRole(userRole, checkAccpetId, Env.getProperty("check.info.type.id")) > 0) {
            isManager = "Y";
        } else {
            isManager = "N";
        }
        //资产会计角色ID
        userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
        if (userRoleService.searchUserRole(userRole, checkAccpetId, Env.getProperty("check.info.type.id")) > 0) { //如果大于0,则代表为资产会计
            if ("N".equals(checkAcceptance.getIsSap())) { //N代表没有sap同步过,同步以后这个字段会改为Y,以后就不能看见这个按钮了,只能同步一次
                model.addAttribute("assetAccounting", "N"); //是资产会计并且没有同步过sap
            } else {
                model.addAttribute("assetAccounting", "Y"); //是资产会计并且同步过sap
            }
            model.addAttribute("isassetAccounting", "Y"); //是资产会计
        } else {
            model.addAttribute("isassetAccounting", "N"); //不是资产会计
        }

        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("checkAcceptance", checkAcceptance);
        model.addAttribute("checkMachine", checkMachine);
        model.addAttribute("checkAcceptanceDetaillist", list);
        model.addAttribute("isManager", isManager);
        model.addAttribute("isUpdateAssetsFrom", checkAcceptance.getIsUpdateAssetsFrom() == null ? "" : checkAcceptance.getIsUpdateAssetsFrom());
        return "purchase/check_acceptance_examine";
    }

    /**
     * 审批同意
     * @param checkAcceptanceWrapper
     * @param request
     * @return
     */
    @RequestMapping("/api/agreeCheckAcceptance")
    @ResponseBody
    public ExecuteResult<CheckAcceptance> agreeCheckAcceptance(@RequestBody CheckAcceptanceWrapper checkAcceptanceWrapper, HttpServletRequest request) {
        ExecuteResult<CheckAcceptance> executeResult = new ExecuteResult<CheckAcceptance>();
        CheckAcceptance checkAcceptance = checkAcceptanceWrapper.getCheckAcceptance();
//        String isManager = checkAcceptance.getIsManager(); //是否归口管理工程师
        String isManager = "";
        String isAssetAccounting = "";

        UserRole userRole = new UserRole();
        userRole.setCusercode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        userRole.setCrolecode(Env.getProperty("role.putMangerPerson.id"));

        if (userRoleService.searchUserRole(userRole, checkAcceptance.getCdocument(), Env.getProperty("check.info.type.id")) > 0) {
            isManager = "Y";
        } else {
            isManager = "N";
        }
        userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
        if (userRoleService.searchUserRole(userRole, checkAcceptance.getCdocument(), Env.getProperty("check.info.type.id")) > 0) { //如果大于0,则代表为资产会计
            isAssetAccounting = "Y";
        } else {
            isAssetAccounting = "N";
        }
        List<CheckAcceptanceDetail> list = checkAcceptanceWrapper.getCheckAcceptanceDetails();
        CheckAcceptance ca = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptance.getCdocument());
        PurchaseOrder purchaseOrder1 = new PurchaseOrder();
        purchaseOrder1.setCdocument(ca.getPurchaseId());
        PurchaseOrder purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderIdOnly(purchaseOrder1);
        CheckAcceptanceModel cam = new CheckAcceptanceModel();
        cam.setReceiveGoodsId(checkAcceptance.getReceiveGoodsId());
        cam.setCreateDate(ca.getCreateDate()); // 单据创建日期
        cam.setCostCenterCode(ca.getCostCenterCode()); // 成本中心编码
        cam.setCdocument(checkAcceptance.getCdocument());
        cam.setIdea(checkAcceptance.getIdea());
        cam.setIsEmail(checkAcceptance.getIsEmail());
        cam.setIsPhoneMessage(checkAcceptance.getIsPhoneMessage());
        cam.setActivityId(checkAcceptance.getActivityId());
        //
        // 获得当前登录人 部门
        String applyUsername = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        cam.setCuserName(applyUsername);
        cam.setApplyCheckUserCode(ca.getApplyCheckUserCode()); // 验收单起草人
        String applyUsernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        cam.setCuserCode(applyUsernameCode); // 验收单起草人
        cam.setApplyCheckUserName(ca.getApplyCheckUserName());
        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        if (userDeptInfo == null) {
            executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
        }
        //
        cam.setUserDepCode(userDeptInfo.getDepartmentCode()); // 当前操作人所属部门编码
        //cam.setApplyDepartment(ca.getApplyDepartment()); // 单据所属部门编码
        //cam.setApplyDepartmentCode(ca.getApplyDepartmentCode()); // 单据所属部门编码
        cam.setApplyDepartment(purchaseOrder.getBudgetDepartmentName()); // 单据所属部门编码
        cam.setApplyDepartmentCode(purchaseOrder.getBudgetDepartmentCode()); // 单据所属部门编码

        //
        //
        if ("on".equals(cam.getIsEmail())) {
            ca.setIsEmail("Y");
            cam.setIsEmail("Y");
        } else {
            ca.setIsEmail("N");
            cam.setIsEmail("N");
        }
        if ("on".equals(cam.getIsPhoneMessage())) {
            ca.setIsPhoneMessage("Y");
            cam.setIsPhoneMessage("Y");
        } else {
            ca.setIsPhoneMessage("N");
            cam.setIsPhoneMessage("N");
        }
        /**
         * 调用审批流同意接口后,会对验收主表的字段进行操作,修改下一步处理人和审批流当前状态
         * 保存实际验收日期
         */
        try {
            executeResult = checkAcceptMappingService.agreeWorkFlow(cam, isManager, isAssetAccounting, list);
            CheckAcceptance ca1 = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptance.getCdocument());
            if (StringUtils.isNotBlank(ca1.getCnexthandlercode())) {
                ExecuteResult<Employee> er = remindedService.BellReminder(
                        ca1.getCnexthandlercode(),
                        ca.getCdocument(),
                        ca.getIsPhoneMessage(),
                        ca.getIsEmail());
                //executeResult.setSuccessMessage(er.getSuccessMessage());
                if (!er.isSuccess()) {
                    executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                    for (String str : er.getErrorMessages()) {
                        executeResult.addErrorMessage(str);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("e", e);
        }
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        return executeResult;
    }

    /**
     * 退回
     *
     * @param checkAcceptance
     * @param request
     * @return
     */
    @RequestMapping("/api/denyCheckAcceptance")
    @ResponseBody
    public ExecuteResult<CheckAcceptance> denyCheckAcceptance(
            @RequestBody CheckAcceptance checkAcceptance,
            HttpServletRequest request) {
        ExecuteResult<CheckAcceptance> executeResult = new ExecuteResult<CheckAcceptance>();

        if (StringUtils.isBlank(checkAcceptance.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }
        CheckAcceptance ca = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptance.getCdocument());
        /*if (APPROVESTATE.WAITING.getApproveState().equals(ca.getCapprovalstate())) { //等待审批
            executeResult.addErrorMessage(ca.getCapprovalstate() + "状态的单据不允许退回");
			return executeResult;
		}*/
        if (APPROVESTATE.DONE.getApproveState().equals(ca.getCapprovalstate())) {
            executeResult.addErrorMessage("已完成的单据不可以退回");
            return executeResult;
        }
        CheckAcceptanceModel cam = new CheckAcceptanceModel();
        cam.setIdea(checkAcceptance.getIdea());
        cam.setIsEmail(checkAcceptance.getIsEmail());
        cam.setIsPhoneMessage(checkAcceptance.getIsPhoneMessage());
        cam.setCostCenterCode(ca.getCostCenterCode()); // 成本中心编码
        cam.setCreateDate(ca.getCreateDate()); // 单据创建日期
        cam.setCdocument(checkAcceptance.getCdocument());
        cam.setActivityId(checkAcceptance.getActivityId());
        // 获得当前登录人 部门
        String applyUsername = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        cam.setCuserName(applyUsername); // 当前操作人编码
        String applyUsernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        cam.setCuserCode(applyUsernameCode); // 当前操作人编码
        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        if (userDeptInfo == null) {
            executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
        }
        cam.setUserDepCode(userDeptInfo.getDepartmentCode()); // 当前操作人所属部门编码
        cam.setApplyDepartment(ca.getApplyDepartment()); // 单据所属部门编码
        cam.setApplyDepartmentCode(ca.getApplyDepartmentCode()); // 单据所属部门编码
        CheckAcceptance checkAcceptanceByDocument = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptance.getCdocument());
        if ("on".equals(cam.getIsEmail())) {
            checkAcceptanceByDocument.setIsEmail("Y");
            cam.setIsEmail("Y");
        }
        if ("on".equals(cam.getIsPhoneMessage())) {
            checkAcceptanceByDocument.setIsPhoneMessage("Y");
            cam.setIsPhoneMessage("Y");
        }
        executeResult = checkAcceptMappingService.denyWorkFlow(cam);
        CheckAcceptance ca1 = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptance.getCdocument());
        ExecuteResult<Employee> er = remindedService.BellReminder(
                ca1.getCnexthandlercode(),
                checkAcceptanceByDocument.getCdocument(),
                checkAcceptanceByDocument.getIsPhoneMessage(),
                checkAcceptanceByDocument.getIsEmail());
        //executeResult.setSuccessMessage(er.getSuccessMessage());
        if (!er.isSuccess()) {
            executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
            for (String str : er.getErrorMessages()) {
                executeResult.addErrorMessage(str);
            }
        }

        return executeResult;
    }

    /**
     * 弃审
     *
     * @param checkAcceptance
     * @param request
     * @return
     */
    @RequestMapping("/api/dropCheckAcceptance")
    @ResponseBody
    public ExecuteResult<CheckAcceptance> dropCheckAcceptance(
            @RequestBody CheckAcceptance checkAcceptance,
            HttpServletRequest request) {
        ExecuteResult<CheckAcceptance> executeResult = new ExecuteResult<CheckAcceptance>();

        if (StringUtils.isBlank(checkAcceptance.getIdea())) {
            executeResult.addErrorMessage("意见不可为空");
            return executeResult;
        }
        CheckAcceptance ca = checkAcceptanceService
                .getCheckAcceptanceByDocument(checkAcceptance.getCdocument());

        CheckAcceptanceModel cam = new CheckAcceptanceModel();
        cam.setCreateDate(ca.getCreateDate()); // 单据创建日期
        cam.setCostCenterCode(ca.getCostCenterCode()); // 成本中心编码
        cam.setCdocument(checkAcceptance.getCdocument());
        cam.setIdea(checkAcceptance.getIdea());
        cam.setIsEmail(checkAcceptance.getIsEmail());
        cam.setIsPhoneMessage(checkAcceptance.getIsPhoneMessage());

        // 获得当前登录人 部门
        String applyUsername = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        cam.setCuserName(applyUsername); // 当前操作人编码

        String applyUsernameCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        cam.setCuserCode(applyUsernameCode); // 当前操作人编码

        User userDeptInfo = userService.getUserDeptInfo(applyUsernameCode);
        if (userDeptInfo == null) {
            executeResult.addErrorMessage("找不到当前登录人所属部门");
            return executeResult;
        }
        cam.setUserDepCode(userDeptInfo.getDepartmentCode()); // 当前操作人所属部门编码
        cam.setApplyDepartment(ca.getApplyDepartment()); // 单据所属部门编码
        cam.setApplyDepartmentCode(ca.getApplyDepartmentCode()); // 单据所属部门编码
        if ("on".equals(cam.getIsEmail())) {
            ca.setIsEmail("Y");
            cam.setIsEmail("Y");
        } else {
            ca.setIsEmail("N");
            cam.setIsEmail("N");
        }
        if ("on".equals(cam.getIsPhoneMessage())) {
            ca.setIsPhoneMessage("Y");
            cam.setIsPhoneMessage("Y");
        } else {
            ca.setIsPhoneMessage("N");
            cam.setIsPhoneMessage("N");
        }
        try {
            executeResult = checkAcceptMappingService.dropWorkFlow(cam);
            CheckAcceptance ca1 = checkAcceptanceService
                    .getCheckAcceptanceByDocument(checkAcceptance.getCdocument());
            ExecuteResult<Employee> er = remindedService.BellReminder(
                    ca1.getCnexthandlercode(),
                    ca.getCdocument(),
                    ca.getIsPhoneMessage(),
                    ca.getIsEmail());
            //executeResult.setSuccessMessage(er.getSuccessMessage());
            if (!er.isSuccess()) {
                executeResult.addErrorMessage("审批成功，但是邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    executeResult.addErrorMessage(str);
                }
            }
        } catch (Exception e) {
            LOGGER.error("e", e);
        }
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        // remindedService.BellReminder(demandOrderModel.getCnexthandlercode(),
        // demandOrderModel.getCdocument(), demandOrderModel.getIsMail(),
        // demandOrderModel.getIsPhoneMessage());
        return executeResult;
    }

    /**
     * 修改页面
     *
     * @param checkAcceptId
     * @param model
     * @return
     */
    @RequestMapping("/updateCheckAcceptance")
    public String updateCheckAcceptance(String checkAcceptId, Model model) {
        CheckAcceptance checkAcceptance = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptId);
        CheckMachine checkMachine = checkAcceptanceService.getCheckMachineByCheckId(checkAcceptId);
        model.addAttribute("checkAcceptance", checkAcceptance);
        model.addAttribute("checkMachine", checkMachine);
        return "purchase/check_acceptance_update";
    }

    /**
     * 修改 查询验收单对应的验收行明细
     *
     * @param checkAcceptId
     * @return
     */
    @RequestMapping("/api/getCheckAcceptanceDetailByDocument")
    @ResponseBody
    public List<CheckAcceptMapping> getCheckAcceptanceDetailByDocument(String checkAcceptId) {
        List<CheckAcceptMapping> receiveCheckMapping = checkAcceptMappingService.searchAllDemandDetailIdByCheckAcceptId(checkAcceptId);

        return receiveCheckMapping;
    }

    /**
     * 修改 查询 验收单的行明细
     *
     * @param checkAcceptId
     * @return
     */
    @RequestMapping("/api/searchCheckAcceptDetailByCheckAcceptId")
    @ResponseBody
    public List<CheckAcceptanceDetail> searchCheckAcceptDetailByCheckAcceptId(String checkAcceptId) {
        List<CheckAcceptanceDetail> checkAcceptanceDetaillist1 = checkAcceptanceDetailService.getCheckAcceptanceDetailByDocument(checkAcceptId);
        List<CheckAcceptanceDetail> list = new ArrayList<CheckAcceptanceDetail>();
        for (CheckAcceptanceDetail cam : checkAcceptanceDetaillist1) { //遍历根据cardId查询资产卡片表中存的信息
            CheckAcceptanceDetail cam1 = checkAcceptMappingService.searchAssetFormation(cam);
            cam1.setEpBelnr(cam1.getEpBelnr() == null ? "" : cam1.getEpBelnr());
            cam1.setEpMatdocumentyear(cam1.getEpMatdocumentyear() == null ? "" : cam1.getEpMatdocumentyear());
            cam1.setEpMaterialdocu(cam1.getEpMaterialdocu() == null ? "" : cam1.getEpMaterialdocu());
            cam1.setStorageLocation(cam1.getStorageLocation() == null ? "" : cam1.getStorageLocation());
            cam1.setRecWarrantyPeriod(cam1.getRecWarrantyPeriod() == null ? "" : cam1.getRecWarrantyPeriod());
            list.add(cam1);
        }
        return list;
    }

    /**
     * 修改 保存 验收单
     *
     * @param checkAcceptanceWrapper
     * @return
     */
    @RequestMapping(value = "/api/updateSaveCheckAccept")
    @ResponseBody
    public ExecuteResult<CheckAcceptance> updateSaveCheckAccept(@RequestBody CheckAcceptanceWrapper checkAcceptanceWrapper, HttpServletRequest request) {

        ExecuteResult<CheckAcceptance> executeResult = new ExecuteResult<CheckAcceptance>();
        checkAcceptanceWrapper.getCheckAcceptance().setAssetPerson(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim());
        checkAcceptanceWrapper.getCheckAcceptance().setAssetPersonCode(request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim());
        CheckAcceptance checkAcceptance = checkAcceptanceWrapper.getCheckAcceptance();
        checkAcceptance.setApplyCheckUserCode(checkAcceptance.getAssetPersonCode());
        checkAcceptance.setApplyCheckUserName(checkAcceptance.getAssetPerson());
        List<CheckAcceptanceDetail> checkAcceptanceDetails = checkAcceptanceWrapper.getCheckAcceptanceDetails();
        List<CheckAcceptMapping> checkAcceptMappings = checkAcceptanceWrapper.getCheckAcceptMappings();
        CheckMachine checkMachine = checkAcceptanceWrapper.getCheckMachine();
        if ("机器设备".equals(checkAcceptance.getAssetsType())) {
            checkMachine.setCheckId(checkAcceptance.getCheckAcceptId());
        }
        if (WARNSTATE.YES.getWarnstate().equals(checkAcceptance.getIsEmail())) {
            checkAcceptance.setIsEmail("Y");
        } else {
            checkAcceptance.setIsEmail("N");
        }
        if (WARNSTATE.YES.getWarnstate().equals(checkAcceptance.getIsPhoneMessage())) {
            checkAcceptance.setIsPhoneMessage("Y");
        } else {
            checkAcceptance.setIsPhoneMessage("N");
        }
        /**
         * 根据资产类型和成本中心从配置表中查询唯一信息，然后根据归口管理经理编码查询申请部门
         */
        AttchConfig attchConfig = new AttchConfig();
        attchConfig.setCostcenterCode(checkAcceptance.getCostCenterCode());
        attchConfig.setAssetType(checkAcceptance.getAssetsType());
        AttchConfig attchConfig1 = attchConfigService.searchByCostcenterCodeAndAssetType(attchConfig);
        String deptCode = checkAcceptMappingService.searchDeptCode(attchConfig1.getAttachDepartManagerCode());
//		if (1 == isSave) {
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
            WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();
            createNextFlowStepParam.setBillCode(checkAcceptance.getCdocument());
            CheckAcceptance ca = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptance.getCdocument());
            createNextFlowStepParam.setBillTableName(Env.getProperty("check.info.table"));
            createNextFlowStepParam.setBillCodeColumnName("CDOCUMENT");
            createNextFlowStepParam.setDeptCode(deptCode); //(checkAcceptance.getApplyDepartmentCode()); // 采购申请部门
            createNextFlowStepParam.setDeptName(checkAcceptance.getApplyDepartment());
            createNextFlowStepParam.setCreatorCode(checkAcceptance.getAssetPersonCode()); // 当前登录人（就是采购员）
            createNextFlowStepParam.setCreatorName(checkAcceptance.getAssetPerson());
            Calendar instance = Calendar.getInstance();
            instance.setTime(ca.getCreateDate());
            createNextFlowStepParam.setCreateDate(instance);
            createNextFlowStepParam.setMoney("0"); // 单据金额
            createNextFlowStepParam.setTypeCode(Env.getProperty("check.info.type.id"));
            createNextFlowStepParam.setFeeItemCode("");
            createNextFlowStepParam.setIsEmail(checkAcceptance.getIsMail()); // 是否发送邮件提醒
            createNextFlowStepParam.setIsPhoneMessage(checkAcceptance.getIsPhoneMessage()); // 是否发送短信提醒
            createNextFlowStepParam.setCApprovalId(UUID.randomUUID().toString());
            createNextFlowStepParam.setIdea(checkAcceptance.getIdea());
            createNextFlowStepParam.setCCostCenterCode(checkAcceptance.getCostCenterCode()); // 成本中心编码
            createNextFlowStepParam.setCCommitActivityId(null == checkAcceptance.getActivityId() ? "" : checkAcceptance.getActivityId());
            createNextFlowStep.setParam(createNextFlowStepParam);
            createNextFlowStep.setUserCode(checkAcceptance.getAssetPersonCode());
            createNextFlowStep.setUserName(checkAcceptance.getAssetPerson());
            /**
             * 修改单据绑定审批流时，先将新增时绑定的审批流垃圾数据删除。
             */
            workflowService.deleteGarbegeWorkFlowData(checkAcceptance.getCdocument(), Env.getProperty("check.info.type.id"));
            WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);
            if (StringUtils.isNotBlank(response.getErrMessage())) {
                ifRollback = true;
                executeResult.addErrorMessage(response.getErrMessage());
                LOGGER.error("====" + response.getErrMessage() + "====");
            }
        } catch (AxisFault axisFault) {
            ifRollback = true;
            LOGGER.error("axisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            LOGGER.error("RemoteException", e);
        }
        if (ifRollback) {
            executeResult.addErrorMessage("调用审批流失败");
            // 调用审批流失败删除收货单表信息
            //checkAcceptanceService.delCheckAcceptanceByCheckAcceptId(checkAcceptance.getCheckAcceptId());
            return executeResult;
        }
        executeResult.setSuccessMessage("绑定成功");

        boolean flag = true;
        Long isSave = null;
        try {
            isSave = checkAcceptMappingService.saveUpdate(
                    checkAcceptance, checkAcceptanceDetails, checkAcceptMappings, checkMachine);

        } catch (ParseException e) {
            LOGGER.error("ParseException", e);
            flag = false;
        }
        CheckAcceptance ca = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptance.getCdocument());

        ExecuteResult<Employee> er = remindedService.BellReminder(ca.getCnexthandlercode(), ca.getCdocument(), checkAcceptance.getIsPhoneMessage(), checkAcceptance.getIsMail());
        //executeResult.setSuccessMessage(er.getSuccessMessage());
        if (!er.isSuccess()) {
            executeResult.addErrorMessage("修改成功，但是邮件或短信发送失败");
            for (String str : er.getErrorMessages()) {
                executeResult.addErrorMessage(str);
            }
        }

        if (flag) {
            executeResult.setSuccessMessage("修改成功！");
        } else {
            executeResult.addErrorMessage("修改错误，请检查数据");
            workflowService.deleteGarbegeWorkFlowData(checkAcceptance.getCdocument(), Env.getProperty("check.info.type.id"));
        }

//		}
        return executeResult;
    }

    /**查看
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param typeId
     * @return
     */
    @RequestMapping("/showCheckAcceptance")
    public String showCheckAcceptance(Model model, HttpServletRequest req,
                                      Long offset, Long pageSize, String typeId) {
        // // 单据号
        String checkAcceptId = req.getParameter("checkAcceptId");
        CheckAcceptance checkAcceptance = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptId);
        CheckMachine checkMachine = checkAcceptanceService.getCheckMachineByCheckId(checkAcceptId);
        //List<CheckAcceptanceDetail> checkAcceptanceDetaillist = checkAcceptanceDetailService.getCheckAcceptanceDetailByDocument(checkAcceptId);
        /**
         * 修改页面展示的字段
         */
        List<CheckAcceptanceDetail> checkAcceptanceDetaillist1 = checkAcceptanceDetailService.getCheckAcceptanceDetailByDocument(checkAcceptId);
        List<CheckAcceptanceDetail> list = new ArrayList<CheckAcceptanceDetail>();
        for (CheckAcceptanceDetail cam : checkAcceptanceDetaillist1) { //遍历根据cardId查询资产卡片表中存的信息
            CheckAcceptanceDetail cam1 = checkAcceptMappingService.searchAssetFormation(cam);
            list.add(cam1);
        }
        /**
         * 获取审批流流程的历史记录
         */
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workflowService
                .showApproveHistory(checkAcceptId, typeId, pager);
        appHistoryPager.setUri(req.getRequestURI());
        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("checkAcceptance", checkAcceptance);
        model.addAttribute("checkAcceptanceDetaillist",
                list); //checkAcceptanceDetaillist);
        model.addAttribute("checkMachine", checkMachine);
        return "purchase/check_acceptance_show";
    }

    /**打印
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param typeId
     * @return
     */
    @RequestMapping("/showStamp")
    public String showStamp(Model model, HttpServletRequest req,
                            Long offset, Long pageSize, String typeId) {
        // // 单据号
        String checkAcceptId = req.getParameter("id");

        CheckAcceptance checkAcceptance = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptId);
        String receiveGoodsId = checkAcceptance.getReceiveGoodsId();
        ReceiveGoods receiveGoods = receiveGoodsService.getReceiveGoodsByDocument(receiveGoodsId);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCdocument(receiveGoods.getPurchaseId());
        PurchaseOrder purchaseOrder1 = purchaseOrderService.searchPurchaseOrderByOrderIdOnly(purchaseOrder);
        CheckMachine checkMachine = checkAcceptanceService.getCheckMachineByCheckId(checkAcceptId);
        //List<CheckAcceptanceDetail> checkAcceptanceDetaillist = checkAcceptanceDetailService.getCheckAcceptanceDetailByDocument(checkAcceptId);
        /**
         * 修改页面展示的字段
         */
        List<CheckAcceptanceDetail> checkAcceptanceDetaillist1 = checkAcceptanceDetailService.getCheckAcceptanceDetailByDocument(checkAcceptId);
        List<CheckAcceptanceDetail> list = new ArrayList<CheckAcceptanceDetail>();
        for (CheckAcceptanceDetail cam : checkAcceptanceDetaillist1) { //遍历根据cardId查询资产卡片表中存的信息
            CheckAcceptanceDetail cam1 = checkAcceptMappingService.searchAssetFormation(cam);
            list.add(cam1);
        }
        /**
         * 获取审批流流程的历史记录
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        checkAcceptance.setDateStr(sdf.format(checkAcceptance.getCreateDate()));

        List<ApproveHistoryCheckPrint> appHistoryPager = checkAcceptanceService.getApproveHistory(checkAcceptId, typeId);
        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("spaPurchaseId", req.getParameter("id"));
        model.addAttribute("budgetDepartmentName", purchaseOrder1.getBudgetDepartmentName());
        model.addAttribute("checkAcceptance", checkAcceptance);
        model.addAttribute("checkAcceptanceDetaillist", list);
        model.addAttribute("checkMachine", checkMachine);
        return "purchase/check_acceptance_stamp";
    }

    /**
     *
     * @param list
     * @param req
     * @return
     */
    @RequestMapping("/api/updateAssetsForm")
    @ResponseBody
    public ExecuteResult<String> updateAssetsForm(@RequestParam(required = false, value = "list[]") List<String> list, HttpServletRequest req) {
        String document = req.getParameter("cdocument");
        ExecuteResult<String> executeResult = new ExecuteResult<String>();
        String userCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        CheckAcceptance checkAcceptance = checkAcceptanceService.getCheckAcceptanceByDocument(document);
        List<CheckAcceptanceDetail> checkAcceptanceDetails = checkAcceptanceDetailService.getCheckAcceptanceDetailByDocument(document);
        if ("IT资产".equals(checkAcceptance.getAssetsType()) && "ZAB".equals(checkAcceptance.getOrderType())) {
            List<PurchaseCommon> purchaseCommonList = purchaseCommonService.getPurchaseCommonByCheckAcceptanceDetailId(list);
            for (PurchaseCommon purchaseCommon : purchaseCommonList) {
                String sapMainAssetCode = purchaseCommon.getSapMainAssetCode();
                String sapSecAssetCode = purchaseCommon.getSapSecAssetCode();
                IpHistoryModel ipHistoryModel1 = new IpHistoryModel();
                IpInputModel ipInputModel = new IpInputModel();
                ipInputModel.setCompanycode(purchaseCommon.getCompanyCode()); //公司代码
                ipInputModel.setSubnumber(sapSecAssetCode); //设置次级编码
                ipInputModel.setCostcenter(purchaseCommon.getCostCenterCode()); //成本中心编码
                ipHistoryModel1.setOaid("1"); //OAID
                ipHistoryModel1.setOano("1"); //OANO
                ipHistoryModel1.setOaname("1"); //OANAME
                ipHistoryModel1.setOamodule("1"); //OAMODUL
                ipHistoryModel1.setSapid("1"); //SAP  ID 写死
                JSONObject obj = null; //0是成功
                try {
                    obj = sapUtilService.updateAssetsFromSAP(ipHistoryModel1, ipInputModel, sapMainAssetCode);
                    if (obj.has("error")) {
                        executeResult.addErrorMessage("更新成本中心失败!");
                        return executeResult;
                        //checkAcceptanceDetailService.updateCheckAcceptanceDetailSapById(checkAcceptanceDetail);
                    }

                } catch (Exception e) {
                    LOGGER.error("e", e);
                    executeResult.addErrorMessage("同步sap成功，但更新成本中心失败!请点击更新成本中心按钮");
                    return executeResult;
                }
            }
            try {
                checkAcceptanceService.updateCheckAcceptanceByUpdateSap(document); //做一个sql，增加一个是否更新成本中心成功的字段，用于前台页面是否显示更新按钮
            } catch (Exception e) {
                LOGGER.error("更新验收单是否更新过成本中心的标志失败，不影响业务逻辑!", e);
                executeResult.addErrorMessage("更新成本中心成功!");
                return executeResult;
            }
        } else {
            executeResult.addErrorMessage("只有资本化的IT资产需要更改成本中心");
            return executeResult;
        }
        if ("ZAB".equals(checkAcceptance.getOrderType())) {
            try {
                for (CheckAcceptanceDetail cad : checkAcceptanceDetails) {
                    generalAssetService.updateAssetFromSap(Integer.parseInt(cad.getCardId()), userCode);
                }
            } catch (NumberFormatException e) {
                executeResult.addErrorMessage("同步sap成功，更新资产卡片失败!");
                return executeResult;
            } catch (Exception e) {
                executeResult.addErrorMessage("同步sap成功，更新资产卡片失败!");
                return executeResult;
            }
        }
        executeResult.setSuccessMessage("成功");
        return executeResult;
    }

    /**
     * 同步sap资产编码
     * @return
     */
    @RequestMapping("/api/synchronousSAPAssets")
    @ResponseBody
    public ExecuteResult<CheckAcceptanceDetail> synchronousSAPAssets(@RequestParam(required = false, value = "list[]") List<String> list, HttpServletRequest req) {
        ExecuteResult<CheckAcceptanceDetail> executeResult = new ExecuteResult<CheckAcceptanceDetail>();
        String document = req.getParameter("cdocument");
        CheckAcceptanceDetail checkAcceptanceDetail = new CheckAcceptanceDetail();
        try {
            CheckAcceptance checkAcceptance = checkAcceptanceService.getCheckAcceptanceByDocument(document);
            List<CheckAcceptanceDetail> checkAcceptanceDetails = checkAcceptanceDetailService.getCheckAcceptanceDetailByDocument(document);
            String userCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
            String userName = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
//		/*调用sap接口（李凯,暂时注释，订单接口通了后，这里解开）*/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String nowdateString = sdf.format(new Date()); //当前系统时间
            List<CheckAcceptMapping> ebeln = checkAcceptMappingService.getEbeln(checkAcceptance.getPurchaseId());
            IpHistoryModel ipHistoryModel = new IpHistoryModel();
            ipHistoryModel.setOaid(userCode); //当前登陆人id,不用传此参数
            ipHistoryModel.setOano(userCode); //当前登陆人id
            ipHistoryModel.setOaname(userName); //当前登陆人名字
            ipHistoryModel.setOamodule("mrms固定资产管理系统"); //oa模块名,传'mrms固定资产管理系统'
            ipHistoryModel.setDept(checkAcceptance.getApplyDepartment()); //业务部门描述,
            ipHistoryModel.setRemark(null); //说明
            ipHistoryModel.setCompany(checkAcceptance.getCompanyName()); //公司名称
            //ipHistoryModel.setMblnr(ebeln.get(0).getItemNo()); //采购凭证的项目编号
            IpZmm0010Model ipZmm0010Model = new IpZmm0010Model();
            ipZmm0010Model.setPstngDate(nowdateString); // 凭证中的记帐日期  当前系统时间
            ipZmm0010Model.setDocDate(nowdateString); // 凭证中的凭证日期  当前系统时间
            ipZmm0010Model.setRefDocNo(checkAcceptance.getPurchaseId()); //采购订单号
            ipZmm0010Model.setPrUname(userName); // 用户名
            List<ItZmm0011Model> itZmm0011Models = new ArrayList<ItZmm0011Model>();
            for (int s = 0; checkAcceptanceDetails.size() > s; s++) {
                //资产卡片主键
                String cardId = checkAcceptanceDetails.get(s).getCardId();
                PurchaseCommon purchaseCommon = purchaseCommonService.getPurchaseCommonOnly(cardId);
                purchaseCommon.setCompanyCode(checkAcceptance.getCompanyCode());
                //List<CheckAcceptMapping> list = checkAcceptMappingService.getEbeln(checkAcceptanceDetails.get(s).getPurchaseId());
                CheckAcceptMapping checkAcceptMapping = checkAcceptMappingService.getEbelnOnly(purchaseCommon);
                ItZmm0011Model itZmm0011Model = new ItZmm0011Model();
                itZmm0011Model.setPlant(checkAcceptance.getCompanyCode()); //工厂 公司编码
                itZmm0011Model.setMoveType("101"); // 移动类型(库存管理)  默认当前写死101
                itZmm0011Model.setCostcenter(checkAcceptance.getCostCenterCode()); //成本中心编码
                itZmm0011Model.setPoItem(checkAcceptMapping.getItemNo() + ""); //采购凭证的项目编号
                String purchaseDetailId = checkAcceptanceDetails.get(s).getPurchaseDetailId();
                PurchaseOrderDetail purchaseOrderDetail = purchaseOrderDetailService.searchPurchaseOrderDetailById(purchaseDetailId);
                itZmm0011Model.setGlAccount(purchaseOrderDetail.getLedgerAccount()); //总账科目
                PurchaseOrder purchaseOrder1 = new PurchaseOrder();
                String purchaseOrderId = checkAcceptanceDetails.get(s).getPurchaseId();
                purchaseOrder1.setCdocument(purchaseOrderId);
                PurchaseOrder purchaseOrder = purchaseOrderService.searchPurchaseOrderByOrderIdOnly(purchaseOrder1);
                itZmm0011Model.setMvtInd("B"); // 移动标识
                itZmm0011Model.setPoNumber(checkAcceptMapping.getPurchaseSapNo()); //采购订单凭证号(返回的)
                itZmm0011Model.setEntryUom(purchaseOrder.getMeins());
                itZmm0011Model.setEntryQnt(new BigDecimal(1));
                itZmm0011Models.add(itZmm0011Model);
            }
            JSONObject mm = sapUtilService.createPurchaseOrderReceiveGoods(ipZmm0010Model, ipHistoryModel, itZmm0011Models);
            List<String> strlist = new ArrayList<String>();
            if ("0".equals(mm.getString("result"))) {
                if ("".equals(mm.getString("epMaterialdocu")) || null == mm.getString("epMaterialdocu")) {
                    strlist.add("SAP返回物料凭证号为空值");
                }
                if ("".equals(mm.getString("epBelnr")) || null == mm.getString("epBelnr")) {
                    strlist.add("SAP返回会计凭证号为空值");
                }
                if ("".equals(mm.getString("epMatdocumentyear")) || null == mm.getString("epMatdocumentyear")) {
                    strlist.add("SAP返回物料凭证年度为空值");
                }
                String str = "";
                for (String str1 : strlist) {
                    str += "," + str1;
                }
                if (strlist.size() > 0) {
                    executeResult.addErrorMessage(str);
                    return executeResult;
                }
                CheckAcceptance checkAcceptance1 = new CheckAcceptance();
                checkAcceptance1.setCdocument(document);
                checkAcceptance1.setEpMaterialdocu(mm.getString("epMaterialdocu"));
                checkAcceptance1.setEpBelnr(mm.getString("epBelnr"));
                checkAcceptance1.setEpMatdocumentyear(mm.getString("epMatdocumentyear"));
                checkAcceptanceDetail = new CheckAcceptanceDetail();
                checkAcceptanceDetail.setEpMaterialdocu(mm.getString("epMaterialdocu")); //物料凭证号
                checkAcceptanceDetail.setEpBelnr(mm.getString("epBelnr")); //会计凭证号
                checkAcceptanceDetail.setEpMatdocumentyear(mm.getString("epMatdocumentyear"));
                checkAcceptanceDetail.setIsSap("Y");
                checkAcceptanceService.updateCheckAcceptanceBySap(checkAcceptance1);
                for (String li : list) {
                    Long id = Long.parseLong(li); //收货详情表主键
                    checkAcceptanceDetail.setId(id);
                    Long num = checkAcceptanceDetailService.updateCheckAcceptanceDetailSapById(checkAcceptanceDetail);
                    if (num != 1) {
                        executeResult.addErrorMessage("验收行明细中保存sap信息失败!");
                        return executeResult;
                    }
                }
                if ("IT资产".equals(checkAcceptance.getAssetsType()) && "ZAB".equals(checkAcceptance.getOrderType())) {
                    List<PurchaseCommon> purchaseCommonList = purchaseCommonService.getPurchaseCommonByCheckAcceptanceDetailId(list);
                    for (PurchaseCommon purchaseCommon : purchaseCommonList) {
                        String sapMainAssetCode = purchaseCommon.getSapMainAssetCode();
                        String sapSecAssetCode = purchaseCommon.getSapSecAssetCode();
                        IpHistoryModel ipHistoryModel1 = new IpHistoryModel();
                        IpInputModel ipInputModel = new IpInputModel();
                        //ipInputModel.setAssetclass(generalAsset.getAssetClassification());//资产分类
                        ipInputModel.setCompanycode(purchaseCommon.getCompanyCode()); //公司代码
                        ipInputModel.setSubnumber(sapSecAssetCode); //设置次级编码
                        ipInputModel.setCostcenter(purchaseCommon.getCostCenterCode()); //成本中心编码
                        ipHistoryModel1.setOaid("1"); //OAID
                        ipHistoryModel1.setOano("1"); //OANO
                        ipHistoryModel1.setOaname("1"); //OANAME
                        ipHistoryModel1.setOamodule("1"); //OAMODUL
                        ipHistoryModel1.setSapid("1"); //SAP  ID 写死
                        JSONObject obj = null; //0是成功
                        try {
                            obj = sapUtilService.updateAssetsFromSAP(ipHistoryModel1, ipInputModel, sapMainAssetCode);
                            if (obj.has("error")) {
                                executeResult.addErrorMessage("同步sap成功，但更新成本中心失败!请点击更新成本中心按钮");
                                executeResult.setResult(checkAcceptanceDetail);
                                return executeResult;
                                //checkAcceptanceDetailService.updateCheckAcceptanceDetailSapById(checkAcceptanceDetail);
                            }

                        } catch (Exception e) {
                            LOGGER.error("e", e);
                            executeResult.addErrorMessage("同步sap成功，但更新成本中心失败!请点击更新成本中心按钮");
                            executeResult.setResult(checkAcceptanceDetail);
                            return executeResult;
                        }
                    }
                    try {
                        checkAcceptanceService.updateCheckAcceptanceByUpdateSap(document); //做一个sql，增加一个是否更新成本中心成功的字段，用于前台页面是否显示更新按钮
                    } catch (Exception e) {
                        LOGGER.error("更新验收单是否更新过成本中心的标志失败，不影响业务逻辑!", e);
                    }
                }
                if ("ZAB".equals(checkAcceptance.getOrderType())) {
                    try {
                        for (CheckAcceptanceDetail cad : checkAcceptanceDetails) {
                            generalAssetService.updateAssetFromSap(Integer.parseInt(cad.getCardId()), userCode);
                        }
                    } catch (NumberFormatException e) {
                        executeResult.addErrorMessage("同步sap成功，更新资产卡片失败!");
                        return executeResult;
                    } catch (Exception e) {
                        executeResult.addErrorMessage("同步sap成功，更新资产卡片失败!");
                        return executeResult;
                    }
                }
            } else {
                if ("".equals(mm.getString("epMaterialdocu")) || null == mm.getString("epMaterialdocu")) {
                    strlist.add("SAP返回物料凭证号为空值");
                }
                if ("".equals(mm.getString("epBelnr")) || null == mm.getString("epBelnr")) {
                    strlist.add("SAP返回会计凭证号为空值");
                }
                if ("".equals(mm.getString("epMatdocumentyear")) || null == mm.getString("epMatdocumentyear")) {
                    strlist.add("SAP返回物料凭证年度为空值");
                }
                String str = "";
                for (String str1 : strlist) {
                    str += "," + str1;
                }
                executeResult.addErrorMessage("同步SAP接口调用失败!");
                return executeResult;
            }
        } catch (Exception e) {
            LOGGER.error("e", e);
            executeResult.addErrorMessage("同步SAP接口调用失败!");
            return executeResult;
        }
        executeResult.setResult(checkAcceptanceDetail);
        executeResult.setSuccessMessage("成功");
        return executeResult;
    }

    //根据验收单号查询所有的mmAssertsCode，从而删除相对应的文件 /api/getMmAssertsCode
    @RequestMapping("/api/getMmAssertsCode")
    @ResponseBody
    public JSONObject getMmAssertsCode(String checkAcceptId) {
        List<String> list = null;
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            List<CheckAcceptanceDetail> list1 = checkAcceptanceDetailService.getCheckAcceptanceDetailByDocument(checkAcceptId);
            list = new ArrayList<String>();
            for (CheckAcceptanceDetail cad : list1) {
                list.add(cad.getMmAssetsCode());
            }
            jsonMap.put("list", list);
            jsonMap.put("success", true);
        } catch (Exception e) {
            LOGGER.error("e", e);
            jsonMap.put("success", false);
        }

        return JSONObject.fromObject(jsonMap);
    }


    @RequestMapping("/api/searchIsSap")
    @ResponseBody
    public JSONObject searchIsSap(String id) {
        CheckAcceptanceDetail checkAcceptanceDetail = checkAcceptanceDetailService.getCheckAcceptanceDetailById(Long.parseLong(id));
        if (null == checkAcceptanceDetail.getIsSap() || "".equals(checkAcceptanceDetail.getIsSap())) {
            checkAcceptanceDetail.setIsSap("N");
        }
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("isSap", checkAcceptanceDetail.getIsSap());
        return JSONObject.fromObject(jsonMap);
    }

    @RequestMapping("/api/canUpdate")
    @ResponseBody
    public JSONObject canUpdate(HttpServletRequest req) {
        String checkAcceptId = req.getParameter("checkAcceptId");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        String canUpdate = "N";
        String userCode = req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        try {
            CheckAcceptance checkAcceptance = checkAcceptanceService.getCheckAcceptanceByDocument(checkAcceptId);
            List<String> list = checkAcceptanceService.getUserCode(userCode); //查出对当前登录人进行授权的人，看看此条单据的申请者有没有给他授权
            if (null == checkAcceptance.getApplyCheckUserCode() || "".equals(checkAcceptance.getApplyCheckUserCode())) {
                jsonMap.put("canUpdate", "N");
                jsonMap.put("errorMessage", "验收主表没存验收起草人信息");
                return JSONObject.fromObject(jsonMap);
            }
            if (userCode.equals(checkAcceptance.getApplyCheckUserCode())) { //如果当前登录人就是起草人，可以修改、删除
                canUpdate = "Y";
            } else { //如果当前登录人不是起草人，那么判断是否被起草人授权，授权则可以修改、删除
                for (String id : list) {
                    if (checkAcceptance.getApplyCheckUserCode().equals(id)) {
                        canUpdate = "Y";
                        break;
                    }
                }
            }

        } catch (NullPointerException e) {
            LOGGER.error("e", e);
            jsonMap.put("canUpdate", "N");
            jsonMap.put("errorMessage", "验收主表中可能没存验收起草人信息");
            return JSONObject.fromObject(jsonMap);
        } catch (Exception e) {
            LOGGER.error("e", e);
            jsonMap.put("canUpdate", "N");
            return JSONObject.fromObject(jsonMap);
        }
        jsonMap.put("canUpdate", canUpdate);
        return JSONObject.fromObject(jsonMap);
    }

    @RequestMapping("/api/updateIsprint")
    @ResponseBody
    public void updateIsprint(String checkId) {
        checkAcceptanceService.updateIsprint(checkId);
    }
}