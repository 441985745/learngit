package com.opple.fa.allocation.webapp.controller;

import com.opple.fa.allocation.entity.AlloctionApprove;
import com.opple.fa.allocation.entity.AssetAllocation;
import com.opple.fa.allocation.entity.AssetAllocationDetail;
import com.opple.fa.allocation.export.controller.ExportAssetAllocationTemplate;
import com.opple.fa.allocation.service.AlloctionApproveService;
import com.opple.fa.allocation.service.AssetAllocationDetailService;
import com.opple.fa.allocation.service.AssetAllocationService;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.service.GeneralAssetService;
import com.opple.fa.config.entity.AssetUnit;
import com.opple.fa.config.entity.AssetsBudgetFromBUD;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.DeptCostCenter;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.entity.UserRole;
import com.opple.fa.config.service.AssetUnitService;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.DeptCostCenterService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.config.service.UserRoleService;
import com.opple.fa.discardsell.webapp.controller.DiscardSellController;
import com.opple.fa.purchase.model.ApproveHistoryCheckPrint;
import com.opple.fa.purchase.service.CheckAcceptanceService;
import com.opple.fa.sap.entity.IAmtran;
import com.opple.fa.sap.model.IpHistoryModel;
import com.opple.fa.sap.model.IpInputModel;
import com.opple.fa.sap.service.SapUtilService;
import com.opple.fa.sap.service.SapUtilTailService;
import com.opple.fa.webapp.controller.BaseOpenApiController;
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
import com.opple.workflow.entity.APPROVESTATE;
import com.opple.workflow.entity.ApproveHistory;
import com.opple.workflow.entity.COMMITTYPE;
import com.opple.workflow.service.WorkFlowService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/assetAllocation")
public class AssetAllocationController extends BaseOpenApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscardSellController.class);
    @Resource
    private AssetAllocationService assetAllocationService;
    @Resource
    private UserService userService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private RemindedService remindedService;
    @Resource
    private GeneralAssetService generalAssetService;
    @Resource
    private AssetAllocationDetailService assetAllocationDetailService;
    @Resource
    private FileUploadService fileUploadService;
    @Resource
    private AlloctionApproveService alloctionApproveService;
    @Resource
    private SapUtilService sapUtilService;
    @Resource
    private SapUtilTailService sapUtilTailService;
    @Resource
    private AttchConfigService attchConfigService;
    @Resource
    private AssetUnitService assetUnitService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private DeptCostCenterService deptCostCenterService;
    @Resource
    private CheckAcceptanceService checkAcceptanceService;

    @RequestMapping(value = "/Export", method = RequestMethod.GET)
    @ResponseBody
    public void export(ModelMap model, HttpServletRequest request,
                       HttpServletResponse response, AssetAllocation assetAllocation) {
        String fileName = "资产调拨列表.xlsx";
        assetAllocation.setCommitType(null == assetAllocation.getCommitType() ? "1" : assetAllocation.getCommitType().trim());
        String loginUserCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        assetAllocation.setLoginUseCode(loginUserCode);
        assetAllocation.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));

        List<AssetAllocation> assetAllocationList = assetAllocationService.export(assetAllocation);

        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportAssetAllocationTemplate eaat = new ExportAssetAllocationTemplate();
            eaat.doExport(response, fileName, assetAllocationList);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }

    }

    /**
     * 首页查询所有数据
     *
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping("/api/deleteAssetAllocation")
    @ResponseBody
    public ExecuteResult<AssetAllocation> deleteAssetAllocation(String ids,
                                                                HttpServletRequest request) {
        AssetAllocation assetAllocation = new AssetAllocation();
        List<AssetAllocation> listAllocation = new ArrayList<AssetAllocation>();

        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        String[] idArray = ids.split(",");
        Boolean sumFlag = true; // 判断全部是否可以删除
        for (String string : idArray) {
            assetAllocation.setId(Long.parseLong(string));
            AssetAllocation assetAllocationByDocument = assetAllocationService
                    .getOneAssetAllocation(assetAllocation);
            listAllocation.add(assetAllocationByDocument);
            AlloctionApprove alloctionApprove = new AlloctionApprove();
            alloctionApprove.setAllocationDocument(assetAllocationByDocument.getDocument());
            List<AlloctionApprove> listApprove = alloctionApproveService.serachAlloctionApproveByDocument(alloctionApprove);

            boolean bool = true;
            for (AlloctionApprove alloctionApprove2 : listApprove) {
                if (!"1".equals(alloctionApprove2.getIsConfirm()) && !"3".equals(assetAllocationByDocument.getConfirmStatus())) {
                    bool = false;
                }
            }
            if (!bool && !APPROVESTATE.DENY.getApproveState().equals(assetAllocationByDocument.getApprovalState())) {
                result.addErrorMessage("只能删除【草稿】或【待确认】或【退回】状态的单据");
                return result;
            } else {
                Boolean flag = false; // 循环一次重新复制 判断 看是否有不符合条件 的 有 一条就直接 返回 一条没有
                // 统一删除
                UserInfo userInfo = new UserInfo();
                String loginUserCode = (String) request.getSession()
                        .getAttribute(SessionSecurityConstants.KEY_USER_NAME);
                userInfo.setLoginUserCode(loginUserCode);
                List<UserInfo> userInfos = userInfoService
                        .searchCauthorizerCode(userInfo);
                if (userInfos.size() != 0 && userInfos != null) {
                    for (UserInfo userInfo2 : userInfos) {
                        if (assetAllocationByDocument.getUserCode().equals(userInfo2.getCauthorizerCode())) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (loginUserCode.equals(assetAllocationByDocument
                        .getUserCode())) {
                    flag = true;
                }
                if (!flag) {
                    sumFlag = false;
                    result.addErrorMessage("您没有删除权限！");
                    return result;
                }
            }
        }
        if (sumFlag) {
            try {
                assetAllocationService.deleteAssetAllocation(ids);
            } catch (Exception e) {
                LOGGER.error("deleteAssetAllocation", e);
            }

            for (AssetAllocation assetAllocationWorlk : listAllocation) {
                workFlowService.deleteGarbegeWorkFlowData(assetAllocationWorlk.getDocument(), Env.getProperty("allocation.info.type.id"));
            }
        }
        return result;
    }

    public String updateAssetAllocation(Model model, int id) {
        AssetAllocation assetAllocation = assetAllocationService.searchAssetAllocationById(id);

        List<FileUpload> listFile = fileUploadService.getFilesByDocument(assetAllocation.getDocument());

        assetAllocation.setListFile(listFile);
        model.addAttribute("assetAllocation", assetAllocation);

        return "assetallocation/asset_allocation_update";
    }

    /**
     * 首页模糊查询
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param assetAllocation
     * @return
     * @throws ParseException
     */
    @RequestMapping("/searchAssetAllocations")
    public String searchAssetAllocations(Model model, HttpServletRequest req,
                                         Long offset, Long pageSize, AssetAllocation assetAllocation) {
        assetAllocation.setCommitType(null == assetAllocation.getCommitType() ? "1" : assetAllocation.getCommitType().trim());
        assetAllocation.setCreateBy(StringUtils.isEmpty(assetAllocation.getCreateBy()) ? assetAllocation.getCreateBy() : assetAllocation.getCreateBy().trim());
        assetAllocation.setOriginalCompany(StringUtils.isEmpty(assetAllocation.getOriginalCompany()) ? assetAllocation.getOriginalCompany() : assetAllocation.getOriginalCompany().trim());
        String useCode = (String) req.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        Pager<AssetAllocation> pager = new Pager<AssetAllocation>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        assetAllocation.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        assetAllocation.setLoginUseCode(useCode);
        Pager<AssetAllocation> assetAllocationPager = assetAllocationService.searchAssetAllocations(pager, assetAllocation);
        assetAllocationPager.setUri(req.getRequestURI());
        model.addAttribute("assetAllocationPager", assetAllocationPager);
        model.addAttribute("assetAllocation", assetAllocation);

        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        model.addAttribute("addresslist", addresslist);

        AlloctionApprove alloctionApprove = new AlloctionApprove();
        alloctionApprove.setManagerCode(useCode);
        long count = alloctionApproveService.countAllocationApproveBycode(alloctionApprove);
        alloctionApprove.setCount(count);

        model.addAttribute("alloctionApprove", alloctionApprove);
        return "assetallocation/asset_allocation_list";
    }

    /**
     * 保存提交申请
     *
     * @param assetAllocation
     * @param sublist
     * @param cause
     * @param date
     * @return
     * @throws ParseException
     */

    @RequestMapping("/api/saveApply")
    @ResponseBody
    public ExecuteResult<AssetAllocation> saveApply(
            AssetAllocation assetAllocation, String sublist,
            BigDecimal originalValueSum, BigDecimal netValueSum, String cause,
            String date) throws ParseException {
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        if ("".equals(assetAllocation.getPostAllocationCompany()) || null == assetAllocation.getPostAllocationCompany()
                || "".equals(assetAllocation.getPostAllocationPeople()) || null == assetAllocation.getPostAllocationPeople()
                || "".equals(assetAllocation.getPostAllocationDepartment()) || null == assetAllocation.getPostAllocationDepartment()) {
            result.addErrorMessage("必填项不能为空！");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assetAllocation.setApplicationDate(sdf.parse(date));
        if ("on".equals(assetAllocation.getIsPhoneMessage())) {
            assetAllocation.setIsPhoneMessage("Y");
        }
        if ("off".equals(assetAllocation.getIsPhoneMessage())) {
            assetAllocation.setIsPhoneMessage("N");
        }
        if ("on".equals(assetAllocation.getIsMail())) {
            assetAllocation.setIsMail("Y");
        }
        if ("off".equals(assetAllocation.getIsMail())) {
            assetAllocation.setIsMail("N");
        }
        if (!"0".equals(assetAllocation.getCommitType())) {
            if (null != sublist && !"".equals(sublist)) {
                if ("0".equals(assetAllocation.getCommitType())) {
                    assetAllocation.setConfirmStatus("");
                } else {
                    assetAllocation.setConfirmStatus("0");
                }
                try {
                    result = assetAllocationService.saveApply(assetAllocation,
                            sublist, originalValueSum, netValueSum, cause);
                } catch (Exception e) {
                    LOGGER.error("saveApply", e);
                }

            } else {
                result.addErrorMessage("资产不能为空！");
                return result;
            }
        } else {
            try {
                result = assetAllocationService.saveApply(assetAllocation,
                        sublist, originalValueSum, netValueSum, cause);
            } catch (Exception e) {
                LOGGER.error("saveApply", e);
            }

        }

        // 保存为草稿
        if ("0".equals(assetAllocation.getCommitType())) {
            return result;
        }
        AssetAllocation assetAllocationTt = assetAllocationService
                .getAssetAllocationByDocument(assetAllocation.getDocument());

        if (null != assetAllocationTt) {
            String nextCode = assetAllocationTt.getNextHandlerCode();
            if (!"".equals(nextCode) && null != nextCode) {
                String[] nextCodeList = nextCode.split(",");
                for (String string : nextCodeList) {

                    ExecuteResult<Employee> er = remindedService.BellReminder(
                            string,
                            assetAllocationTt.getDocument(),
                            assetAllocation.getIsPhoneMessage(),
                            assetAllocation.getIsMail());
                    if (!er.isSuccess()) {
                        result.addErrorMessage("提交成功，但邮件或短信发送失败");
                        for (String str : er.getErrorMessages()) {
                            result.addErrorMessage(str);
                        }
                    }
                }
            }

        }
        return result;
    }

    /**
     * 点击申请，获得登录人名称
     *
     * @param model
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/assetAllocationApplication")
    public String assetAllocationApplication(Model model,
                                             HttpServletRequest request) throws ParseException {
        AssetAllocation assetAllocation = new AssetAllocation();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        assetAllocation.setApplicationDate(sqlDate);
        String userCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
        assetAllocation.setDocument(workFlowService.getDocumentByType("FA_ASAC"));
        setUserBeanProperties(userCode, assetAllocation);
        model.addAttribute("assetAllocation", assetAllocation);
        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        model.addAttribute("addresslist", addresslist);

        return "assetallocation/asset_allocation_apply";
    }

    private void setUserBeanProperties(String userCode, AssetAllocation assetAllocation) {
        User userDeptInfo = userService.getUserDeptInfo(userCode);
        assetAllocation.setUserCode(userCode);

        if (null != userDeptInfo) {
            assetAllocation.setUserName(userDeptInfo.getUserName());
            assetAllocation.setDepartmentCode(userDeptInfo.getDepartmentCode());
            assetAllocation.setDepartmentName(userDeptInfo.getDepartmentName());
            assetAllocation.setCompanyCode(userDeptInfo.getCompanyCode());
            assetAllocation.setCompanyName(userDeptInfo.getCompanyName());
            assetAllocation.setCreateBy(userDeptInfo.getUserName());
            assetAllocation.setUpdateBy(userDeptInfo.getUserName());
        }
    }

    /**
     * 修改
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/assetAllocationUpdate")
    public String updateAssetAllocation(Model model, HttpServletRequest request) {
        String number = request.getParameter("id");
        AssetAllocation assetAllocationTt = new AssetAllocation();
        assetAllocationTt.setDocument(number);
        AssetAllocation assetAllocation = assetAllocationService.getAssetAllocation(assetAllocationTt);
        if (!"".equals(assetAllocation.getApplicationDate()) && null != assetAllocation.getApplicationDate()) {
            java.sql.Date sqlDate = new java.sql.Date(assetAllocation.getApplicationDate().getTime());
            assetAllocation.setApplicationDate(sqlDate);
        }

        List<FileUpload> listFile = fileUploadService.getFilesByDocument(assetAllocation.getDocument());
        assetAllocation.setListFile(listFile);
        model.addAttribute("assetAllocation", assetAllocation);

        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        model.addAttribute("addresslist", addresslist);

        return "assetallocation/asset_allocation_update";
    }

    /**
     * 是否允许修改
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/ifUpdatePermitted")
    @ResponseBody
    public ExecuteResult<AssetAllocation> ifUpdatePermitted(String id,
                                                            HttpServletRequest request) {
        ExecuteResult<AssetAllocation> executeResult = ifDocumentExists(id, request);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }

        if (null != executeResult.getResult().getApprovalState()
                && !APPROVESTATE.WAITING.getApproveState().equals(executeResult.getResult().getApprovalState())
                && !APPROVESTATE.DENY.getApproveState().equals(executeResult.getResult().getApprovalState())
                && COMMITTYPE.SUBMIT.getCommitType().equals(executeResult.getResult().getCommitType())) {

            executeResult.addErrorMessage(executeResult.getResult().getApprovalState() + "状态的单据不允许修改");
            return executeResult;
        }
        String loginUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(loginUserCode);
        List<UserInfo> userInfos = userInfoService.searchCauthorizerCode(userInfo);
        Boolean flag = false;
        if (userInfos.size() != 0 && userInfos != null) {
            for (UserInfo userInfo2 : userInfos) {
                if (executeResult.getResult().getUserCode().equals(userInfo2.getCauthorizerCode())) {
                    flag = true;
                    break;
                }
            }
        }
        if (loginUserCode.equals(executeResult.getResult().getUserCode())) {
            flag = true;
        }
        if (!flag) {
            executeResult.addErrorMessage("您未授权或不是申请人！无法修改！");
            return executeResult;
        }

        return executeResult;
    }

    private ExecuteResult<AssetAllocation> ifDocumentExists(String id, HttpServletRequest request) {
        ExecuteResult<AssetAllocation> executeResult = new ExecuteResult<AssetAllocation>();
        AssetAllocation assetAllocation = new AssetAllocation();

        if (StringUtils.isBlank(id)) {
            executeResult.addErrorMessage("请选择数据");
            return executeResult;
        }
        assetAllocation.setDocument(id);
        AssetAllocation assetAllocationByDocument = assetAllocationService.getAssetAllocation(assetAllocation);
        if (null == assetAllocationByDocument) {
            executeResult.addErrorMessage("所选数据不存在");
            return executeResult;
        }

        AlloctionApprove alloctionApprove = new AlloctionApprove();
        alloctionApprove.setAllocationDocument(assetAllocationByDocument.getDocument());
        List<AlloctionApprove> listApprove = alloctionApproveService.serachAlloctionApproveByDocument(alloctionApprove);
        boolean bool = true;
        for (AlloctionApprove alloctionApprove2 : listApprove) {
            if (!"1".equals(alloctionApprove2.getIsConfirm())
                    && !"3".equals(assetAllocationByDocument.getConfirmStatus())) {
                bool = false;
            }
        }
        /*
         * if(bool==false){ executeResult.addErrorMessage("已有经理确认 ！不允许修改！");
		 * return executeResult; }
		 */
        executeResult.setResult(assetAllocationByDocument);

        return executeResult;
    }

    /**
     * 查看
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/assetAllocationView")
    public String assetAllocationView(Model model, HttpServletRequest request,
                                      Long offset, Long pageSize) {
        String number = request.getParameter("id");
        AssetAllocation assetAllocation = new AssetAllocation();
        assetAllocation.setDocument(number);
        AssetAllocation assetAllocationList = assetAllocationService.getOneAssetAllocationByMM(assetAllocation);
        if (!"".equals(assetAllocationList.getApplicationDate()) && null != assetAllocationList.getApplicationDate()) {
            java.sql.Date sqlDate = new java.sql.Date(assetAllocationList.getApplicationDate().getTime());
            assetAllocationList.setApplicationDate(sqlDate);
        }
        List<FileUpload> listFile = fileUploadService.getFilesByDocument(assetAllocationList.getDocument());
        assetAllocationList.setListFile(listFile);
        model.addAttribute("assetAllocation", assetAllocationList);
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(assetAllocationList.getDocument(), Env.getProperty("allocation.info.type.id"), pager);
        appHistoryPager.setUri(request.getRequestURI());
        AlloctionApprove alloctionApprove = new AlloctionApprove();
        alloctionApprove.setAllocationDocument(assetAllocationList.getDocument());
        List<ApproveHistory> listApproveHistory = new ArrayList<ApproveHistory>(); // 历史记录新的list
        List<AlloctionApprove> listApprove = alloctionApproveService.serachHistoryPerson(alloctionApprove);
        List<ApproveHistory> listHistory = appHistoryPager.getRecords();

        for (int i = 0; i < listHistory.size(); i++) {

            if (listApprove.size() != 0 && listApprove != null) {
                if (i == 0) {
                    ApproveHistory updatApproveHistory = listHistory.get(0);
                    updatApproveHistory.setExamineDate(assetAllocationList.getCreateDate());
                    listApproveHistory.add(updatApproveHistory);
                    for (int j = 0; j < listApprove.size(); j++) {
                        ApproveHistory approveHistory = new ApproveHistory();
                        approveHistory.setExamineDate(listApprove.get(j).getConfirmationTime());
                        approveHistory.setExamineUser(listApprove.get(j).getManagerName());
                        approveHistory.setExamineAdvice("确认");
                        listApproveHistory.add(approveHistory);
                    }
                }

            }
            if (i > 1 || i == 1) {
                listApproveHistory.add(listHistory.get(i));
            }
        }
        if (listApprove.size() != 0 && listApprove != null) {
            appHistoryPager.setRecords(listApproveHistory);
        } else {
            appHistoryPager.setRecords(listHistory);
        }

        model.addAttribute("appHistoryPager", appHistoryPager);
        return "assetallocation/asset_allocation_detail";
    }

    /**
     * 审批
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/assetAllocationApprove")
    public String assetAllocationApprove(Model model,
                                         HttpServletRequest request, Long offset, Long pageSize) {
        String number = request.getParameter("id");
        AssetAllocation assetAllocation = new AssetAllocation();
        String caUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String caUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        boolean bol = number.matches("[0-9]+");
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);
        if (bol) {
            assetAllocation.setId(Long.parseLong(number));
        } else {
            assetAllocation.setDocument(number);
        }
        AssetAllocation assetAllocationList = assetAllocationService
                .getAssetAllocationByTask(assetAllocation);
        UserRole userRole = new UserRole();
        // 判断当前登录人是不是资产会计
        String isAssetAccounting = "";
        userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
        userRole.setCusercode(caUserCode);
        if (userRoleService.searchUserRole(userRole,
                assetAllocationList.getDocument(),
                Env.getProperty("allocation.info.type.id")) > 0) {
            isAssetAccounting = "Y";
        } else {
            isAssetAccounting = "N";
        }
        if (!"".equals(assetAllocationList.getApplicationDate())
                && null != assetAllocationList.getApplicationDate()) {
            java.sql.Date sqlDate = new java.sql.Date(assetAllocationList
                    .getApplicationDate().getTime());
            assetAllocationList.setApplicationDate(sqlDate);
        }
        assetAllocationList.setLoginUseCode(caUserCode);
        assetAllocationList.setLoginUseName(caUserName);
        assetAllocationList.setLoginDepCode(userDeptInfo.getDepartmentCode());

        boolean isLastApprovalStep = assetAllocationService.getIsLastApprovalStep(assetAllocationList);
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(assetAllocationList.getDocument(),
                Env.getProperty("allocation.info.type.id"), pager);

        appHistoryPager.setUri(request.getRequestURI());
        List<FileUpload> listFile = fileUploadService.getFilesByDocument(assetAllocationList.getDocument());
        assetAllocationList.setListFile(listFile);
        AlloctionApprove alloctionApprove = new AlloctionApprove();
        alloctionApprove.setAllocationDocument(assetAllocationList.getDocument());
        List<ApproveHistory> listApproveHistory = new ArrayList<ApproveHistory>(); // 历史记录新的list
        List<AlloctionApprove> listApprove = alloctionApproveService.serachHistoryPerson(alloctionApprove);
        List<ApproveHistory> listHistory = appHistoryPager.getRecords();

        for (int i = 0; i < listHistory.size(); i++) {

            if (listApprove.size() != 0 && listApprove != null) {
                if (i == 0) {
                    ApproveHistory updatApproveHistory = listHistory.get(0);
                    updatApproveHistory.setExamineDate(assetAllocationList
                            .getCreateDate());
                    listApproveHistory.add(updatApproveHistory);
                    for (int j = 0; j < listApprove.size(); j++) {
                        ApproveHistory approveHistory = new ApproveHistory();
                        approveHistory.setExamineDate(listApprove.get(j)
                                .getConfirmationTime());
                        approveHistory.setExamineUser(listApprove.get(j)
                                .getManagerName());
                        approveHistory.setExamineAdvice("确认");
                        listApproveHistory.add(approveHistory);
                    }
                }
            }
            if (i > 1 || i == 1) {
                listApproveHistory.add(listHistory.get(i));
            }
        }
        if (listApprove.size() != 0 && listApprove != null) {
            appHistoryPager.setRecords(listApproveHistory);
        } else {
            appHistoryPager.setRecords(listHistory);
        }
        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("isLastApprovalStep", isLastApprovalStep);
        model.addAttribute("assetAllocation", assetAllocationList);
        model.addAttribute("isAssetAccounting", isAssetAccounting);

        if (!"".equals(assetAllocationList.getApprovalState())
                && null != assetAllocationList.getApprovalState()) {
            return "assetallocation/asset_allocation_approve";
        } else {
            return "assetallocation/stay_allocation_confirmed";
        }

    }

    /**
     * 修改
     *
     * @param assetAllocation
     * @param ids
     * @param originalValueSum
     * @param netValueSum
     * @param cause
     * @return
     */
    @RequestMapping("/api/updateAssetAllocation")
    @ResponseBody
    public ExecuteResult<AssetAllocation> updateAllocation(
            AssetAllocation assetAllocation, String ids,
            BigDecimal originalValueSum, BigDecimal netValueSum, String cause) {
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        if ("".equals(assetAllocation.getPostAllocationCompany())
                || null == assetAllocation.getPostAllocationCompany()
                || "".equals(assetAllocation.getPostAllocationPeople())
                || null == assetAllocation.getPostAllocationPeople()
                || "".equals(assetAllocation.getPostAllocationDepartment())
                || null == assetAllocation.getPostAllocationDepartment()) {
            result.addErrorMessage("必填项不能为空！");
            return result;
        }
        if ("on".equals(assetAllocation.getIsPhoneMessage())) {
            assetAllocation.setIsPhoneMessage("Y");
        }
        if ("off".equals(assetAllocation.getIsPhoneMessage())) {
            assetAllocation.setIsPhoneMessage("N");
        }
        if ("on".equals(assetAllocation.getIsMail())) {
            assetAllocation.setIsMail("Y");
        }
        if ("off".equals(assetAllocation.getIsMail())) {
            assetAllocation.setIsMail("N");
        }
        if (!"0".equals(assetAllocation.getCommitType())) {
            if (null != ids && !"".equals(ids)) {
                assetAllocation.setAllocationAssetsValueSum(netValueSum);
                assetAllocation.setOriginalValueSum(
                        originalValueSum);
                assetAllocation.setAllocationReason(cause);
                if ("0".equals(assetAllocation.getCommitType())) {
                    assetAllocation.setConfirmStatus("");
                } else {
                    assetAllocation.setConfirmStatus("0");
                }
                assetAllocation.setApprovalState("");
                try {
                    result = assetAllocationService.updateAssetAllocation(
                            assetAllocation, ids);
                } catch (Exception e) {
                    LOGGER.error("updateAssetAllocation", e);
                }

                workFlowService.deleteGarbegeWorkFlowData(
                        assetAllocation.getDocument(),
                        Env.getProperty("allocation.info.type.id"));
            } else {
                result.addErrorMessage("请选择一条资产！");
                return result;
            }
        } else {
            assetAllocation.setAllocationAssetsValueSum(netValueSum);
            assetAllocation.setOriginalValueSum(
                    originalValueSum);
            assetAllocation.setAllocationReason(cause);
            try {
                result = assetAllocationService.updateAssetAllocation(
                        assetAllocation, ids);
            } catch (Exception e) {
                LOGGER.error("updateAssetAllocation", e);
            }
            workFlowService.deleteGarbegeWorkFlowData(
                    assetAllocation.getDocument(),
                    Env.getProperty("allocation.info.type.id"));
        }

        result.setResult(assetAllocation);

        // 保存为草稿
        if ("0".equals(assetAllocation.getCommitType())) {
            return result;
        }
        AssetAllocation assetAllocationTt = assetAllocationService
                .getAssetAllocationByDocument(assetAllocation.getDocument());
        if (null != assetAllocationTt) {
            String nextCode = assetAllocationTt.getNextHandlerCode();
            if (!"".equals(nextCode) && null != nextCode) {
                String[] nextCodeList = nextCode.split(",");
                for (String string : nextCodeList) {

                    ExecuteResult<Employee> er = remindedService.BellReminder(
                            string,
                            assetAllocationTt.getDocument(),
                            assetAllocation.getIsPhoneMessage(),
                            assetAllocation.getIsMail());
                    if (!er.isSuccess()) {
                        result.addErrorMessage("提交成功，但邮件或短信发送失败");
                        for (String str : er.getErrorMessages()) {
                            result.addErrorMessage(str);
                        }
                    }
                }
            }
        }
        // assetAllocationService.bindApplyWorkFlow(assetAllocation);

        return result;
    }

    @RequestMapping("/showApproveHistory")
    public String showApproveHistory(Model model, Long offset, Long pageSize,
                                     String document, String typeId, HttpServletRequest request) {
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(document, typeId, pager);
        appHistoryPager.setUri(request.getRequestURI());
        AssetAllocation assetAllocation = new AssetAllocation();
        assetAllocation.setDocument(document);
        AssetAllocation assetAllocationList = assetAllocationService.getOneAssetAllocationByMM(assetAllocation);
        AlloctionApprove alloctionApprove = new AlloctionApprove();
        alloctionApprove.setAllocationDocument(assetAllocationList.getDocument());
        List<ApproveHistory> listApproveHistory = new ArrayList<ApproveHistory>(); // 历史记录新的list
        List<AlloctionApprove> listApprove = alloctionApproveService.serachHistoryPerson(alloctionApprove);
        List<ApproveHistory> listHistory = appHistoryPager.getRecords();

        for (int i = 0; i < listHistory.size(); i++) {

            if (listApprove.size() != 0 && listApprove != null) {
                if (i == 0) {
                    ApproveHistory updatApproveHistory = listHistory.get(0);
                    updatApproveHistory.setExamineDate(assetAllocationList
                            .getCreateDate());
                    listApproveHistory.add(updatApproveHistory);
                    for (int j = 0; j < listApprove.size(); j++) {
                        ApproveHistory approveHistory = new ApproveHistory();
                        approveHistory.setExamineDate(listApprove.get(j).getConfirmationTime());
                        approveHistory.setExamineUser(listApprove.get(j).getManagerName());
                        approveHistory.setExamineAdvice("确认");
                        listApproveHistory.add(approveHistory);
                    }
                }

            }
            if (i > 1 || i == 1) {
                listApproveHistory.add(listHistory.get(i));
            }
        }
        if (listApprove.size() != 0 && listApprove != null) {
            appHistoryPager.setRecords(listApproveHistory);
        } else {
            appHistoryPager.setRecords(listHistory);
        }

        model.addAttribute("appHistoryPager", appHistoryPager);

        return "assetallocation/approve_history";
    }

    // 同意
    @RequestMapping("/showAgreeDialog")
    public String showAgreeDialog(Model model) {

        return "assetallocation/agree_dialog";
    }

    // 退回
    @RequestMapping("/showDenyDialog")
    public String showDenyDialog(Model model) {

        return "assetallocation/deny_dialog";
    }

    // 弃审
    @RequestMapping("/showDropDialog")
    public String showDropDialog(Model model) {

        return "assetallocation/drop_dialog";
    }

    @RequestMapping("/agreeAssetAllocation")
    @ResponseBody
    public ExecuteResult<AssetAllocation> agreeAssetallocation(
            AssetAllocation assetAllocation, HttpServletRequest request) {

        String caUserName = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME)
                .toString().trim();
        String caUserCode = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim();
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        /*
         * String document=assetAllocation.getDocument(); GeneralAsset
		 * generalAsset=new GeneralAsset(); generalAsset.setDocument(document);
		 * generalAsset.getApprovalState();
		 */

        AssetAllocation assetAllocationByDocument = assetAllocationService
                .getAssetAllocationByDocument(assetAllocation.getDocument());
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);
        assetAllocationByDocument.setCaUserCode(caUserCode);
        assetAllocationByDocument.setCaUserName(caUserName);
        assetAllocationByDocument.setUserDepCode(userDeptInfo
                .getDepartmentCode());
        // setUserBeanProperties(caUserCode, assetAllocationByDocument);

        if ("on".equals(assetAllocation.getIsPhoneMessage())) {
            assetAllocationByDocument.setIsPhoneMessage("Y");
        }
        if ("off".equals(assetAllocation.getIsPhoneMessage())) {
            assetAllocationByDocument.setIsPhoneMessage("N");
        }
        if ("on".equals(assetAllocation.getIsMail())) {
            assetAllocationByDocument.setIsMail("Y");
        }
        if ("off".equals(assetAllocation.getIsMail())) {
            assetAllocationByDocument.setIsMail("N");
        }
        assetAllocationByDocument.setIdea(assetAllocation.getIdea());
        result = assetAllocationService
                .agreeWorkFlow(assetAllocationByDocument);
        if (!result.isSuccess()) {
            return result;
        }
        AssetAllocation assetAllocationTt = assetAllocationService
                .getAssetAllocationByDocument(assetAllocation.getDocument());
        if (null != assetAllocationTt.getNextHandlerCode() && !"".equals(assetAllocationTt.getNextHandlerCode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(
                    assetAllocationTt.getNextHandlerCode(),
                    assetAllocationTt.getDocument(),
                    assetAllocationByDocument.getIsPhoneMessage(),
                    assetAllocationByDocument.getIsMail());
            if (!er.isSuccess()) {
                result.addErrorMessage("审批成功,但邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        return result;
    }

    @RequestMapping("/denyAssetAllocation")
    @ResponseBody
    public ExecuteResult<AssetAllocation> denyAssetAllocation(
            AssetAllocation assetAllocation, HttpServletRequest request) {
        String caUserName = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME)
                .toString().trim();
        String caUserCode = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim();
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        AssetAllocation assetAllocationByDocument = assetAllocationService
                .getAssetAllocationByDocument(assetAllocation.getDocument());
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);
        assetAllocationByDocument.setCaUserCode(caUserCode);
        assetAllocationByDocument.setCaUserName(caUserName);
        assetAllocationByDocument.setUserDepCode(userDeptInfo
                .getDepartmentCode());
        // setUserBeanProperties(caUserCode, assetAllocationByDocument);
        if (StringUtils.isBlank(assetAllocation.getIdea())) {
            result.addErrorMessage("意见不可为空");
            return result;
        }

        // 流程完成后更新数据表
        // assetAllocationService.updateAssetAllocationByDocument(assetAllocation);

        if ("on".equals(assetAllocation.getIsPhoneMessage())) {
            assetAllocationByDocument.setIsPhoneMessage("Y");
        }
        if ("off".equals(assetAllocation.getIsPhoneMessage())) {
            assetAllocationByDocument.setIsPhoneMessage("N");
        }
        if ("on".equals(assetAllocation.getIsMail())) {
            assetAllocationByDocument.setIsMail("Y");
        }
        if ("off".equals(assetAllocation.getIsMail())) {
            assetAllocationByDocument.setIsMail("N");
        }
        assetAllocationByDocument.setIdea(assetAllocation.getIdea());
        result = assetAllocationService.denyWorkFlow(assetAllocationByDocument);
        if (!result.isSuccess()) {
            return result;
        }
        AssetAllocation assetAllocationTt = assetAllocationService
                .getAssetAllocationByDocument(assetAllocation.getDocument());
        ExecuteResult<Employee> er = remindedService.BellReminder(
                assetAllocationTt.getNextHandlerCode(),
                assetAllocationTt.getDocument(),
                assetAllocationByDocument.getIsPhoneMessage(),
                assetAllocationByDocument.getIsMail());
        if (!er.isSuccess()) {
            result.addErrorMessage("审批成功,但邮件或短信发送失败");
            for (String str : er.getErrorMessages()) {
                result.addErrorMessage(str);
            }
        }
        return result;
    }

    @RequestMapping("/dropAssetAllocation")
    @ResponseBody
    public ExecuteResult<AssetAllocation> dropAssetAllocation(
            AssetAllocation assetAllocation, HttpServletRequest request) {
        String caUserName = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME)
                .toString().trim();
        String caUserCode = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim();
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);
        AssetAllocation assetAllocationByDocument = assetAllocationService
                .getAssetAllocationByDocument(assetAllocation.getDocument());
        assetAllocationByDocument.setCaUserCode(caUserCode);
        assetAllocationByDocument.setCaUserName(caUserName);
        assetAllocationByDocument.setUserDepCode(userDeptInfo
                .getDepartmentCode());
        // setUserBeanProperties(caUserCode, assetAllocationByDocument);
        if (StringUtils.isBlank(assetAllocation.getIdea())) {
            result.addErrorMessage("意见不可为空");
            return result;
        }
        assetAllocationByDocument.setIdea(assetAllocation.getIdea());

        // 流程完成后更新数据表
        // assetAllocationService.updateAssetAllocationByDocument(assetAllocationByDocument);

        if ("on".equals(assetAllocation.getIsPhoneMessage())) {
            assetAllocationByDocument.setIsPhoneMessage("Y");
        }
        if ("off".equals(assetAllocation.getIsPhoneMessage())) {
            assetAllocationByDocument.setIsPhoneMessage("N");
        }
        if ("on".equals(assetAllocation.getIsMail())) {
            assetAllocationByDocument.setIsMail("Y");
        }
        if ("off".equals(assetAllocation.getIsMail())) {
            assetAllocationByDocument.setIsMail("N");
        }
        result = assetAllocationService.dropWorkFlow(assetAllocationByDocument);
        if (!result.isSuccess()) {
            return result;
        }
        AssetAllocation assetAllocationTt = assetAllocationService
                .getAssetAllocationByDocument(assetAllocation.getDocument());
        ExecuteResult<Employee> er = remindedService.BellReminder(
                assetAllocationTt.getNextHandlerCode(),
                assetAllocationTt.getDocument(),
                assetAllocationByDocument.getIsPhoneMessage(),
                assetAllocationByDocument.getIsMail());
        if (!er.isSuccess()) {
            result.addErrorMessage("审批成功,但邮件或短信发送失败");
            for (String str : er.getErrorMessages()) {
                result.addErrorMessage(str);
            }
        }
        return result;
    }

    @RequestMapping("/printOrder")
    public String printOrder(Model model, HttpServletRequest request, String id, String typeId) {

        String number = request.getParameter("id");
        AssetAllocation assetAllocationList = assetAllocationService
                .searchAssetAllocationById(Integer.parseInt(number));
        if (!"".equals(assetAllocationList.getApplicationDate())
                && null != assetAllocationList.getApplicationDate()) {
            java.sql.Date sqlDate = new java.sql.Date(assetAllocationList
                    .getApplicationDate().getTime());
            assetAllocationList.setApplicationDate(sqlDate);
        }
        /**
         * 获取审批流流程的历史记录
         */
        List<ApproveHistoryCheckPrint> appHistoryPager = checkAcceptanceService.getApproveHistory(assetAllocationList.getDocument(), typeId);
        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("assetAllocation", assetAllocationList);
        return "assetallocation/asset_allocation_stamp";
    }

    @RequestMapping("/showAllocationConfirmed")
    public String showAllocationConfirmed(Model model,
                                          HttpServletRequest request, String id) {
        /*
         * String useCode=(String)request.getSession().getAttribute(
		 * SessionSecurityConstants.KEY_USER_NAME); AlloctionApprove
		 * alloctionApprove=new AlloctionApprove();
		 */

        AssetAllocation assetAllocationList = assetAllocationService
                .searchAssetAllocationById(Integer.parseInt(id));

        if (!"".equals(assetAllocationList.getApplicationDate())
                && null != assetAllocationList.getApplicationDate()) {
            java.sql.Date sqlDate = new java.sql.Date(assetAllocationList
                    .getApplicationDate().getTime());
            assetAllocationList.setApplicationDate(sqlDate);
        }
        List<FileUpload> listFile = fileUploadService
                .getFilesByDocument(assetAllocationList.getDocument());
        assetAllocationList.setListFile(listFile);
        model.addAttribute("assetAllocation", assetAllocationList);
        return "assetallocation/stay_allocation_confirmed";
    }

    @RequestMapping("/api/confirmAllocation")
    @ResponseBody
    public ExecuteResult<AssetAllocation> confirmAllocation(Model model,
                                                            HttpServletRequest request, String document, String status, String backOpinion) {
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        AlloctionApprove alloctionApprove = new AlloctionApprove();

        alloctionApprove.setAllocationDocument(document);
        if ("0".equals(status)) {
            String useCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
            alloctionApprove.setManagerCode(useCode);
            boolean boolN = true;
            UserInfo userInfo = new UserInfo();
            userInfo.setLoginUserCode(useCode);
            List<UserInfo> searchCauthorizerCode = userInfoService
                    .searchCauthorizerCode(userInfo);
            List<AlloctionApprove> listIsConfirm = alloctionApproveService.serachAlloctionApproveByDocument(alloctionApprove);

            for (AlloctionApprove alloctionApproveN : listIsConfirm) {
                // 判断是不是确认人
                if (searchCauthorizerCode != null && searchCauthorizerCode.size() != 0) {
                    for (UserInfo userInfo2 : searchCauthorizerCode) {
                        if (alloctionApproveN.getManagerCode().equals(userInfo2.getCauthorizerCode())) {
                            boolN = false;
                        }
                    }
                }
            }
            for (AlloctionApprove alloctionApprove2 : listIsConfirm) {
                if (useCode.equals(alloctionApprove2.getManagerCode())) {
                    boolN = false;
                }
            }
            if (boolN) {
                result.addErrorMessage("您不是确认人！");
                return result;
            }
            // 判断是否重复判断问题
            AlloctionApprove getAlloctionApprove = alloctionApproveService.getApproveByDocumentAndCode(alloctionApprove);
            if ("0".equals(getAlloctionApprove.getIsConfirm())) {
                result.addErrorMessage("请勿重复确认！");
                return result;
            }
            boolean bool = true;
            // 是确认人 直接更新状态
            alloctionApprove.setIsConfirm("0"); // 确认

            alloctionApprove.setConfirmationTime(new Date()); // new
            // Date()为获取当前系统时间
            alloctionApproveService.updateIsConfirm(alloctionApprove);

            List<String> listConfirm = alloctionApproveService.serachIsConfirm(document);
            // 判断是否全部确认完成
            for (String string : listConfirm) {
                if (!"0".equals(string)) {
                    bool = false;
                }
            }
            if (!bool) {
                // 未 全部确认完成 从新拼接心的人
                List<AlloctionApprove> listApprove = alloctionApproveService.serachApproveByDocumentAndConfirm(alloctionApprove);

                if (listApprove.size() != 0) {
                    String costCode = "";
                    String costName = "";
                    for (AlloctionApprove alloctionApprove2 : listApprove) {
                        if ("".equals(costCode)) {
                            costCode = alloctionApprove2.getManagerCode();
                        } else {
                            costCode = costCode + "," + alloctionApprove2.getManagerCode();
                        }
                        if ("".equals(costName)) {
                            costName = alloctionApprove2.getManagerName();
                        } else {
                            costName = costName + "," + alloctionApprove2.getManagerName();
                        }
                    }
                    AssetAllocation assetAllocation = new AssetAllocation();
                    assetAllocation.setNextHandlerName(costName);
                    assetAllocation.setNextHandlerCode(costCode);
                    assetAllocation.setConfirmStatus("1");
                    assetAllocation.setDocument(document);
                    try {
                        assetAllocationService.updateComfirPeopleAndStatus(assetAllocation);
                    } catch (Exception e) {
                        LOGGER.error("updateComfirPeopleAndStatus", e);
                    }

                }
            }
            if (bool) {
                AssetAllocation assetAllocation = assetAllocationService.getAssetAllocationByDocument(document);

                result = assetAllocationService.bindApplyWorkFlow(assetAllocation);
                // assetAllocation.setConfirmStatus("2");
                AssetAllocation assetAllocationtt = assetAllocationService.getAssetAllocationByDocument(document);

                try {
                    assetAllocationService.updateComfirStatus(assetAllocationtt);
                } catch (Exception e) {
                    LOGGER.error("updateComfirStatus", e);
                }

                if (!result.isSuccess()) {

                    try {
                        assetAllocationService.updateComfirStatus(assetAllocation);
                    } catch (Exception e) {
                        LOGGER.error("updateComfirStatus", e);
                    }
                    // assetAllocationService.deleteAssetAllocationByDocument(assetAllocation.getDocument());
                    workFlowService.deleteGarbegeWorkFlowData(assetAllocation.getDocument(), Env.getProperty("allocation.info.type.id"));
                    return result;
                }
           /*     remindedService.BellReminder(
                        assetAllocation.getNextHandlerCode(), assetAllocation.getDocument(),
                        assetAllocation.getIsPhoneMessage(), assetAllocation.getIsMail());*/
            }
        }

        if ("1".equals(status)) {
            AssetAllocation getAssetAllocation = assetAllocationService.getAssetAllocationByDocument(document);
            String useCode = (String) request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME);
            AssetAllocation assetAllocation = new AssetAllocation();
            List<AlloctionApprove> listIsConfirm = alloctionApproveService.serachAlloctionApproveByDocument(alloctionApprove);
            UserInfo userInfo = new UserInfo();
            boolean boolN = true;
            for (AlloctionApprove alloctionApprove2 : listIsConfirm) {
                if (useCode.equals(alloctionApprove2.getManagerCode())) {
                    boolN = false;
                }
            }
            userInfo.setLoginUserCode(useCode);
            List<UserInfo> searchCauthorizerCode = userInfoService.searchCauthorizerCode(userInfo);

            for (AlloctionApprove alloctionApproveN : listIsConfirm) {
                // 判断是不是确认人
                if (searchCauthorizerCode != null && searchCauthorizerCode.size() != 0) {
                    if (searchCauthorizerCode != null && searchCauthorizerCode.size() != 0) {
                        for (UserInfo userInfo2 : searchCauthorizerCode) {
                            if (alloctionApproveN.getManagerCode().equals(userInfo2.getCauthorizerCode())) {
                                boolN = false;
                            }
                        }
                    }
                }
            }
            if (!boolN) {
                if (!"".equals(getAssetAllocation.getApprovalState()) && null != getAssetAllocation.getApprovalState()
                        && !"null".equals(getAssetAllocation.getApprovalState())
                        && !APPROVESTATE.WAITING.getApproveState().equals(getAssetAllocation.getApprovalState())) {
                    result.addErrorMessage("审批中和已完成的单据不允许退回！");
                    return result;
                }
                assetAllocation.setNextHandlerName(getAssetAllocation.getUserName());
                assetAllocation.setNextHandlerCode(getAssetAllocation.getUserCode());
                assetAllocation.setConfirmStatus("3");
                assetAllocation.setDocument(document);
                assetAllocation.setApprovalState(APPROVESTATE.DENY.getApproveState());
                workFlowService.deleteGarbegeWorkFlowData(document,
                        Env.getProperty("allocation.info.type.id"));
                // 修改下一步审批人 为申请人 并修改单据状态为退回
                assetAllocation.setBackOpinion(backOpinion);
                assetAllocationService.updateBack(assetAllocation);

            } else {
                result.addErrorMessage("您不是确认人");
                return result;
            }

        }
        return result;
    }

    @RequestMapping("/api/cancelConfirmAllocation")
    @ResponseBody
    public ExecuteResult<AssetAllocation> cancelConfirmAllocation(Model model,
                                                                  HttpServletRequest request, String document, String status) {
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        String useCode = (String) request.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        AlloctionApprove alloctionApprove = new AlloctionApprove();
        alloctionApprove.setManagerCode(useCode);
        alloctionApprove.setAllocationDocument(document);
        boolean boolN = true;
        UserInfo userInfo = new UserInfo();
        List<AlloctionApprove> listIsConfirm = alloctionApproveService
                .serachAlloctionApproveByDocument(alloctionApprove);
        userInfo.setLoginUserCode(useCode);
        List<UserInfo> searchCauthorizerCode = userInfoService
                .searchCauthorizerCode(userInfo);
        for (AlloctionApprove alloctionApproveN : listIsConfirm) {
            // 判断是不是确认人
            if (searchCauthorizerCode.size() != 0
                    && searchCauthorizerCode != null) {
                for (UserInfo userInfo2 : searchCauthorizerCode) {
                    if (alloctionApproveN.getManagerCode().equals(
                            userInfo2.getCauthorizerCode())) {
                        boolN = false;
                    }
                }
            }
        }
        for (AlloctionApprove alloctionApprove2 : listIsConfirm) {
            if (useCode.equals(alloctionApprove2.getManagerCode())) {
                boolN = false;
            }
        }
        if (boolN) {
            result.addErrorMessage("您不是确认人！无法取消确认！");
            return result;
        }
        // 判断是否重复判断问题
        AlloctionApprove getAlloctionApprove = alloctionApproveService.getApproveByDocumentAndCode(alloctionApprove);
        if ("1".equals(getAlloctionApprove.getIsConfirm())) {
            result.addErrorMessage("请勿重复取消确认！");
            return result;
        }
        AssetAllocation getAssetAllocation = assetAllocationService.getAssetAllocationByDocument(document);
        if (APPROVESTATE.PROCESSING.getApproveState().equals(getAssetAllocation.getApprovalState())
                || APPROVESTATE.DONE.getApproveState().equals(getAssetAllocation.getApprovalState())) {
            result.addErrorMessage("【审批中】或【已完成】的单据不允许取消确认！");
            return result;
        } else {
            workFlowService.deleteGarbegeWorkFlowData(document, Env.getProperty("allocation.info.type.id"));
            getAssetAllocation.setLoginUseCode(useCode);
            AssetAllocation assetAllocation = new AssetAllocation();
            alloctionApprove.setIsConfirm("1");
            alloctionApprove.setManagerCode(useCode);
            try {
                alloctionApproveService.updateIsConfirm(alloctionApprove);
            } catch (Exception e) {
                LOGGER.error("updateIsConfirm", e);
            }

            List<AlloctionApprove> listApprove = alloctionApproveService.serachApproveByDocumentAndConfirm(alloctionApprove);

            if (listApprove.size() != 0) {
                String costCode = "";
                String costName = "";
                for (AlloctionApprove alloctionApprove2 : listApprove) {
                    if ("".equals(costCode)) {
                        costCode = alloctionApprove2.getManagerCode();
                    } else {
                        costCode = costCode + "," + alloctionApprove2.getManagerCode();
                    }
                    if ("".equals(costName)) {
                        costName = alloctionApprove2.getManagerName();
                    } else {
                        costName = costName + "," + alloctionApprove2.getManagerName();
                    }
                }
                assetAllocation.setDocument(getAssetAllocation.getDocument());
                assetAllocation.setNextHandlerName(costName);
                assetAllocation.setNextHandlerCode(costCode);

                try {
                    assetAllocationService.updateComfirPeopleAndStatus(assetAllocation);
                } catch (Exception e) {
                    LOGGER.error("updateComfirPeopleAndStatus", e);
                }
            }
            getAssetAllocation.setApprovalState("");
            try {
                assetAllocationService.updateComfirStatus(getAssetAllocation);
            } catch (Exception e) {
                LOGGER.error("updateComfirStatus", e);
            }


        }
        return result;
    }

    @RequestMapping("/updateSAP")
    @ResponseBody
    public ExecuteResult<AssetAllocation> updateSap(Model model,
                                                    HttpServletRequest request, String document) {
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        JSONObject obj = new JSONObject();
        String errorList = "";
        AssetAllocation assetAllocation = assetAllocationService.getAssetAllocationByDocument(document);

        List<AssetAllocationDetail> listAllocationDetail = assetAllocationDetailService.searchAssetAllocationDetail(assetAllocation);
        for (AssetAllocationDetail assetAllocationDetail : listAllocationDetail) {

            GeneralAsset generalAsset = generalAssetService.getGeneralAsset(assetAllocationDetail.getGeneralAssetId().toString());
            String ipAsset = "";
            String secondaryAsset = "";
            if (!"".equals(generalAsset.getAssetCode())) {
                ipAsset = generalAsset.getAssetCode().split("-")[0]; // 主资产编码
                secondaryAsset = generalAsset.getAssetCode().split("-")[1]; // 次级编码
            }
            if ("0".equals(generalAsset.getIsExpenseAssets())) {
                if (generalAsset.getCompanyCode().equals(assetAllocation.getPostCompanyCode())) {

                    IpHistoryModel ipHistoryModel = new IpHistoryModel();
                    IpInputModel ipInputModel = new IpInputModel();
                    // ipInputModel.setAssetclass(generalAsset.getAssetClassification());//资产分类
                    ipInputModel.setCompanycode(generalAsset.getCompanyCode()); // 公司代码
                    ipInputModel.setSubnumber(secondaryAsset); // 设置次级编码
                    String userCode = assetAllocation.getPostPeopleCode();
                    String userName = assetAllocation.getPostAllocationPeople();
                    ipInputModel.setInventNo(userCode + "-" + userName); // 调拨后资产责任人
                    ipInputModel.setNote(assetAllocation.getPostAllocationStoragePlace()); // 调拨后存放位置
                    ipInputModel.setCostcenter(assetAllocation.getPostCostcenterCode()); // 成本中心编码
                    ipInputModel.setDescript(generalAsset.getSpecificationModel()); // 描述 规格型号

                    AssetUnit assetUnit = new AssetUnit();
                    assetUnit.setUnitName(generalAsset.getUnit());
                    assetUnit = assetUnitService.getAssetUnit(assetUnit);
                    if (null != assetUnit) {
                        ipInputModel.setBaseUom(generalAsset.getUnitCode()); // 单位编码
                    }
                    ipHistoryModel.setOaid("1"); // OAID
                    ipHistoryModel.setOano("1"); // OANO
                    ipHistoryModel.setOaname("1"); // OANAME
                    ipHistoryModel.setOamodule("1"); // OAMODUL
                    ipHistoryModel.setSapid("1"); // SAP ID 写死
                    obj = sapUtilService.updateAssetsFromSAP(ipHistoryModel, ipInputModel, ipAsset); // 0是成功
                    if (obj.has("error")) {
                        if ("".equals(errorList)) {
                            errorList = generalAsset.getAssetCode() + obj.getString("error");
                        } else {
                            errorList = errorList + "," + generalAsset.getAssetCode() + obj.getString("error");
                        }
                    }

                }

                if (!generalAsset.getCompanyCode().equals(assetAllocation.getPostCompanyCode())) {
                    IAmtran iAmtran = new IAmtran();
                    iAmtran.setCompCode(generalAsset.getCompanyCode()); // 公司编码

                    iAmtran.setMainAssetNo(ipAsset); // 主资产编码 旧资产编码
                    iAmtran.setAssetSubNo(secondaryAsset); // 次级资产编码
                    iAmtran.setParCompCode(assetAllocation.getPostCompanyCode()); // iAmtran.set
                    // //对方的公司编码
                    // 调拨后归属公司
                    iAmtran.setParAssetNo(assetAllocationDetail.getNewAssetCode().split("-")[0]); // 对面 主资产编码()
                    iAmtran.setParAssetSubNo(assetAllocationDetail.getNewAssetCode().split("-")[1]); // 资产次级编码
                    try {
                        obj = sapUtilTailService.updateAssetCompanyFromSAP(iAmtran);
                    } catch (Exception e) {
                        LOGGER.error("updateAssetCompanyFromSAP", e);
                    }

                    if (obj.has("error")) {
                        if ("".equals(errorList)) {
                            errorList = generalAsset.getAssetCode() + obj.getString("error");
                        } else {
                            errorList = errorList + "," + generalAsset.getAssetCode() + obj.getString("error");
                        }
                    } else {
                        assetAllocationDetail.setOldAccountancyVouche(obj.getString("sapNo")); // 原公司会计凭证号码
                        assetAllocationDetail.setNowAccountancyVoucher(obj.getString("sapParNo")); // 现公司会计凭证号码
                        assetAllocationDetail.setOldAccountYear(obj.getString("sapYear")); // 原公司会计年度
                        assetAllocationDetail.setNowAccountYear(obj.getString("sapParYear")); // 现公司会计年度

                        //公司间调拨成功后  调用公司内调拨 调拨开始<------->
                        String area = "";
                        for (int i = 0; i < 3; i++) {
                            if (i == 0) {
                                area = "01";
                            }
                            if (i == 1) {
                                area = "20";
                            }
                            if (i == 2) {
                                area = "30";
                            }
                            obj = updateSapFromByCard(generalAsset, assetAllocation, assetAllocationDetail, area);
                            if (!"".equals(obj.getString("errorList"))) {
                                errorList = obj.getString("errorList");
                            }
                        }

                        //调用公司内调拨结束<------->
                        try {
                            assetAllocationDetailService.updateAllocationDetailById(assetAllocationDetail);
                        } catch (Exception e) {
                            LOGGER.error("updateAllocationDetailById", e);
                        }

                    }

                }
            }
        }
        if (!"".equals(errorList)) {
            result.addErrorMessage(errorList);
        } else {
            assetAllocation.setIsUpdateSap("1");
            // assetAllocationDetailService.updateAssetCard(assetAllocation);
            assetAllocationService.updateSapStatus(assetAllocation);
        }
        return result;
    }

    public JSONObject updateSapFromByCard(GeneralAsset generalAsset, AssetAllocation assetAllocation, AssetAllocationDetail assetAllocationDetail, String area) {
        String errorList = "";
        JSONObject obj = new JSONObject();
        IpHistoryModel ipHistoryModel = new IpHistoryModel();
        IpInputModel ipInputModel = new IpInputModel();
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        // ipInputModel.setAssetclass(generalAsset.getAssetClassification());//资产分类
        ipInputModel.setCompanycode(assetAllocation.getPostCompanyCode()); // 公司代码
        ipInputModel.setSubnumber(assetAllocationDetail.getNewAssetCode().split("-")[1]); // 设置次级编码
        String mainCode = assetAllocationDetail.getNewAssetCode().split("-")[0]; //获取主资产编码
        String userCode = assetAllocation.getPostPeopleCode();
        String userName = assetAllocation.getPostAllocationPeople();
        ipInputModel.setInventNo(userCode + "-" + userName); // 调拨后资产责任人
        ipInputModel.setNote(assetAllocation.getPostAllocationStoragePlace()); // 调拨后存放位置
        ipInputModel.setCostcenter(assetAllocation.getPostCostcenterCode()); // 成本中心编码
        ipInputModel.setDescript(generalAsset.getSpecificationModel()); // 描述 规格型号
        ipInputModel.setOdepStartDate(generalAsset.getDepreciationStartDate()); //折旧开始日期
        ipInputModel.setArea(area); //折旧范围
        AssetUnit assetUnit = new AssetUnit();
        assetUnit.setUnitName(generalAsset.getUnit());
        assetUnit = assetUnitService.getAssetUnit(assetUnit);
        if (null != assetUnit) {
            ipInputModel.setBaseUom(generalAsset.getUnitCode()); // 单位编码
        }
        ipHistoryModel.setOaid("1"); // OAID
        ipHistoryModel.setOano("1"); // OANO
        ipHistoryModel.setOaname("1"); // OANAME
        ipHistoryModel.setOamodule("1"); // OAMODUL
        ipHistoryModel.setSapid("1"); // SAP ID 写死
        obj = sapUtilService.updateAssetsFromSAP(ipHistoryModel, ipInputModel, mainCode); // 0是成功
        if (obj.has("error")) {
            if ("".equals(errorList)) {
                errorList = generalAsset.getAssetCode() + obj.getString("error");
            } else {
                errorList = errorList + "," + generalAsset.getAssetCode() + obj.getString("error");
            }

        }
        obj.accumulate("errorList", errorList);
        return obj;
    }

    @RequestMapping("/api/getAssetCodeSap")
    @ResponseBody
    public ExecuteResult<AssetAllocation> getAssetCodeSap(HttpServletRequest request, String ids,
                                                          String postCostCenterCode, String postCompanyCode, String assetAllocationId) {
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        List<AssetAllocationDetail> listDetai = new ArrayList<AssetAllocationDetail>();
        AssetAllocation assetAllocation = new AssetAllocation();
        assetAllocation.setId(Long.parseLong(assetAllocationId));
        AssetAllocation getAssetAllocation = assetAllocationService.getOneAssetAllocation(assetAllocation);
        JSONObject sapCardCode = new JSONObject();
        String[] id = ids.split(",");
        for (String string : id) {
            GeneralAsset generalAsset = generalAssetService.getGeneralAsset(string);
            if (!generalAsset.getCompanyCode().equals(postCompanyCode)) {
                IpHistoryModel ipHistoryModel = new IpHistoryModel();
                IpInputModel ipInputModel = new IpInputModel();

                ipInputModel.setAssetclass(generalAsset.getAssetClassification()); // 资产分类
                ipInputModel.setCompanycode(postCompanyCode); // 公司编码
                ipInputModel.setQuantity(1L); // 数量
                ipInputModel.setDescript(generalAsset.getAssetName()); // 描述
                AssetUnit assetUnit = new AssetUnit();
                assetUnit.setUnitName(generalAsset.getUnit());
                assetUnit = assetUnitService.getAssetUnit(assetUnit);
                if (null != assetUnit) {
                    ipInputModel.setBaseUom(assetUnit.getUnitCode()); // 单位编码
                }
                ipInputModel.setCostcenter(postCostCenterCode); // 成本中心
                ipInputModel.setInventNo(getAssetAllocation.getPostPeopleCode() + "-" + getAssetAllocation.getPostAllocationPeople());
                ipInputModel.setMainDescript(generalAsset.getAssetName());
                ipHistoryModel.setOano("1");
                ipHistoryModel.setOaname("1");
                ipHistoryModel.setOamodule("1");

                try {
                    sapCardCode = sapUtilService.createAssetsCodeFromSAP(ipInputModel, ipHistoryModel);
                } catch (Exception e) {
                    LOGGER.error("createAssetsCodeFromSAP", e);
                }
                if (!"".equals(sapCardCode.getString("error"))) {
                    result.addErrorMessage(sapCardCode.getString("error"));
                    return result;
                } else {
                    String mainCode = sapCardCode.getString("asset");
                    String secondCode = sapCardCode.getString("subNumber").split("-")[0];
                    int seCode = Integer.parseInt(secondCode);
                    String newAssetCode = mainCode + "-" + seCode;
                    AssetAllocationDetail assetAllocationDetail = new AssetAllocationDetail();
                    assetAllocationDetail.setNewAssetCode(newAssetCode);
                    assetAllocationDetail.setGeneralAssetId(Integer.parseInt(String.valueOf(generalAsset.getId())));
                    assetAllocationDetail.setAssetAllocationId(Integer.parseInt(assetAllocationId));
                    listDetai.add(assetAllocationDetail);
                }

            }
        }
        for (AssetAllocationDetail assetAllocationDetail : listDetai) {
            try {
                assetAllocationDetailService.updateAllocationDetailNewCode(assetAllocationDetail);
            } catch (Exception e) {
                LOGGER.error("updateAllocationDetailNewCode", e);
            }

        }
        return result;
    }

    @RequestMapping("/api/getAttchConfig")
    @ResponseBody
    public AttchConfig getPlormat(String costCenter) {
        AssetsBudgetFromBUD assetsBudgetFromBUD = new AssetsBudgetFromBUD();
        assetsBudgetFromBUD.setCostCenter(costCenter);
        AttchConfig attchConfig = attchConfigService.searchByCostcenterCode(assetsBudgetFromBUD);
        return attchConfig;
    }

    @RequestMapping("/api/getAttchConfigWorkshop")
    @ResponseBody
    public ExecuteResult<AttchConfig> getAttchConfigWorkshop(String costCenter,
                                                             String assetType) {
        ExecuteResult<AttchConfig> result = new ExecuteResult<AttchConfig>();
        AttchConfig attchConfig = new AttchConfig();
        attchConfig.setCostcenterCode(costCenter);
        attchConfig.setAssetType("机器设备");
        AttchConfig attchConfigWork = attchConfigService.searchByCostcenterCodeAndAssetType(attchConfig);
        if (attchConfigWork == null) {
            result.addErrorMessage("该部门没有对应的车间");
            return result;
        }
        result.setResult(attchConfigWork);
        return result;
    }

    @RequestMapping("/api/getUserInfo")
    @ResponseBody
    public ExecuteResult<UserInfo> getUserInfo(String userCode) {
        ExecuteResult<UserInfo> result = new ExecuteResult<UserInfo>();
        UserInfo userInfo = new UserInfo();
        userInfo.setCemployeecode(userCode);
        userInfo = userInfoService.searchDepartment(userInfo);
        if (null != userInfo) {
            result.setResult(userInfo);
        } else {
            result.addErrorMessage("未查询到合适的记录！");
        }

        return result;
    }

    @RequestMapping("/api/getCompany")
    @ResponseBody
    public List<UserInfo> getCompany(String ckscode) {
        List<UserInfo> infoList = userInfoService.getCompany(ckscode);
        return infoList;
    }


    @RequestMapping("/api/assetAllocationStamp")
    @ResponseBody
    public ExecuteResult<AssetAllocation> assetAllocationStamp(Model model, HttpServletRequest request, String id, String typeId) {
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        AssetAllocation assetAllocation = new AssetAllocation();
        assetAllocation.setId(Long.parseLong(id));
        assetAllocation = assetAllocationService.getOneAssetAllocation(assetAllocation);
//        if (!APPROVESTATE.DONE.getApproveState().equals(assetAllocation.getApprovalState())) {
//            result.addErrorMessage("只能打印已完成的单据！");
//            return result;
//        } else {
        assetAllocation.setIsStamp("Y");
        try {
            assetAllocationService.updateIsStamp(assetAllocation);
        } catch (Exception e) {
            LOGGER.error("updateIsStamp", e);
        }

        printOrder(model, request, id, typeId);
        return result;
    }


    @RequestMapping("/api/getCostCenter")
    @ResponseBody
    public UserInfo getCostCenter(String companyCode, String depcode) {
        UserInfo userInfo = new UserInfo();
        userInfo.setCcompanycode(companyCode);
        userInfo.setCkscode(depcode);
        UserInfo getUser = userInfoService.getCostCenter(userInfo);
        return getUser;
    }


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

        return "assetallocation/show_depcostcenter_list";
    }
}
