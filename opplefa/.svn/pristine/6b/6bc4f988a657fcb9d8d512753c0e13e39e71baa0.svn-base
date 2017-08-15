package com.opple.fa.discardsell.webapp.controller;

import com.opple.fa.assetcard.entity.ASSETTYPE;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.service.GeneralAssetService;
import com.opple.fa.assetcard.service.ThingsPersonalService;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.entity.WorkAddress;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.config.service.WorkAddressService;
import com.opple.fa.discardsell.entity.DISCARDORSELL;
import com.opple.fa.discardsell.entity.DiscardSell;
import com.opple.fa.discardsell.entity.DiscardSellDetail;
import com.opple.fa.discardsell.export.controller.ExportDiscardSellTemplate;
import com.opple.fa.discardsell.service.DiscardSellDetailService;
import com.opple.fa.discardsell.service.DiscardSellService;
import com.opple.fa.purchase.model.ApproveHistoryCheckPrint;
import com.opple.fa.purchase.service.CheckAcceptanceService;
import com.opple.fa.sap.entity.Generalpostingdata;
import com.opple.fa.sap.entity.Retirementdata;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/discardSell")
public class DiscardSellController extends BaseOpenApiController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DiscardSellController.class);
    @Resource
    private DiscardSellService discardSellService;
    @Resource
    private ThingsPersonalService thingsPersonalService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private RemindedService remindedService;
    @Resource
    private UserService userService;
    @Resource
    private DiscardSellDetailService discardSellDetailService;
    @Resource
    private FileUploadService fileUploadService;
    @Resource
    private GeneralAssetService generalAssetService;
    @Resource
    private SapUtilTailService sapUtilTailService;
    @Resource
    private AttchConfigService attchConfigService;
    @Resource
    private WorkAddressService workAddressService;
    @Resource
    private CheckAcceptanceService checkAcceptanceService;

    /**
     * 进首页查询出所有数据
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @return
     */
    @RequestMapping("/searchDiscardSell")
    public String searchDiscardSell(Model model, HttpServletRequest req,
                                    Long offset, Long pageSize, DiscardSell discardSell) {
        String userCode = (String) req.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        discardSell.setLoginUseCode(userCode);
        discardSell.setCommitType(null == discardSell.getCommitType() ? "1" : discardSell.getCommitType().trim());
        discardSell.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        Pager<DiscardSell> pager = new Pager<DiscardSell>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
        Pager<DiscardSell> discardSellPager = discardSellService
                .searchDiscardSell(pager, discardSell);
        discardSellPager.setUri(req.getRequestURI());
        model.addAttribute("discardSellPager", discardSellPager);
        return "discardsell/discard_sell_list";
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/Export", method = RequestMethod.GET)
    @ResponseBody
    public void export(ModelMap model, HttpServletRequest request,
                       HttpServletResponse response, DiscardSell discardSell) {
        String fileName = "报废变卖列表.xlsx";
        String userCode = (String) request.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        discardSell.setLoginUseCode(userCode);
        discardSell.setCommitType(null == discardSell.getCommitType() ? "1" : discardSell.getCommitType().trim());
        discardSell.setAssetAdminId(Env.getProperty("role.assetAdmin.id"));
        List<DiscardSell> discardSellList = discardSellService
                .serchDiscardExport(discardSell);
        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");

            ExportDiscardSellTemplate exportDiscardSellTemplate = new ExportDiscardSellTemplate();
            exportDiscardSellTemplate.doExport(response, fileName,
                    discardSellList);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
    }

    /**
     * 报废申请
     */
    @RequestMapping("/discardSellApply")
    public String assetScrapsaleApplication(Model model,
                                            HttpServletRequest request) {
        DiscardSell discardSell = new DiscardSell();
        String userCode = (String) request.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        discardSell.setDocument(workFlowService.getDocumentByType("FA_SRSL"));
        setUserBeanProperties(userCode, discardSell);

        model.addAttribute("discardSell", discardSell);
        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        model.addAttribute("addresslist", addresslist);
        return "discardsell/discard_sell_apply";
    }

    @RequestMapping("/discardSellStamp")
    public String discardSellStamp(long id, Model model,
                                   HttpServletRequest request, String typeId) {
        DiscardSell discardSell = new DiscardSell();
        discardSell.setId(id);
        discardSell = discardSellService.getDiscardSell(discardSell);
        String generalIds = discardSell.getGeneralAssetIds();
        GeneralAsset generalAsset = new GeneralAsset();
        List<GeneralAsset> listGneral = new ArrayList<GeneralAsset>();
        String[] ids = generalIds.split(",");
        for (String string : ids) {
            generalAsset = thingsPersonalService.searchDetails(Long
                    .parseLong(string));
            listGneral.add(generalAsset);
        }
        discardSell.setListGneralAssets(listGneral);
        /**
         * 获取审批流流程的历史记录
         */
        List<ApproveHistoryCheckPrint> appHistoryPager = checkAcceptanceService.getApproveHistory(discardSell.getDocument(), typeId);
        model.addAttribute("appHistoryPager", appHistoryPager);
        model.addAttribute("discardSell", discardSell);
        return "discardsell/discard_sell_stamp";
    }

    /**
     * @param userCode
     * @param discardSell
     */
    private void setUserBeanProperties(String userCode, DiscardSell discardSell) {
        User userDeptInfo = userService.getUserDeptInfo(userCode);

        discardSell.setUserCode(userCode);

        if (null != userDeptInfo) {
            discardSell.setUserName(userDeptInfo.getUserName());
            discardSell.setDepartmentCode(userDeptInfo.getDepartmentCode());
            discardSell.setDepartmentName(userDeptInfo.getDepartmentName());
            discardSell.setCompanyCode(userDeptInfo.getCompanyCode());
            discardSell.setCompanyName(userDeptInfo.getCompanyName());
            discardSell.setCreateBy(userDeptInfo.getUserName());
            discardSell.setUpdateBy(userDeptInfo.getUserName());
        }
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
    public ExecuteResult<DiscardSell> ifUpdatePermitted(String id, HttpServletRequest request) {
        ExecuteResult<DiscardSell> executeResult = ifDocumentExists(id, request);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
        if (!APPROVESTATE.WAITING.getApproveState().equals(
                executeResult.getResult().getApprovalState())
                && !APPROVESTATE.DENY.getApproveState().equals(
                executeResult.getResult().getApprovalState())
                && COMMITTYPE.SUBMIT.getCommitType().equals(
                executeResult.getResult().getCommitType())
                && !"".equals(executeResult.getResult().getApprovalState())
                && null != executeResult.getResult().getApprovalState()) {
            executeResult.addErrorMessage(executeResult.getResult()
                    .getApprovalState() + "状态的单据不允许修改");
            return executeResult;
        }
        return executeResult;
    }

    private ExecuteResult<DiscardSell> ifDocumentExists(String id,
                                                        HttpServletRequest request) {
        ExecuteResult<DiscardSell> executeResult = new ExecuteResult<DiscardSell>();
        DiscardSell discardSell = new DiscardSell();

        if (StringUtils.isBlank(id)) {
            executeResult.addErrorMessage("请选择数据");
            return executeResult;
        }
        discardSell.setDocument(id);
        //discardSell.setId(Long.parseLong(String.valueOf(id)));
        DiscardSell discardSellByDocument = discardSellService
                .getDiscardSell(discardSell);
        if (null == discardSellByDocument) {
            executeResult.addErrorMessage("所选数据不存在");
            return executeResult;
        }
        String useCode = (String) request.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        String applyCode = discardSellByDocument.getUserCode();
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginUserCode(useCode);
        List<UserInfo> userInfos = userInfoService
                .searchCauthorizerCode(userInfo);
        Boolean flag = false;
        if (userInfos.size() != 0 && userInfos != null) {
            for (UserInfo userInfo2 : userInfos) {
                if (discardSellByDocument.getUserCode()
                        .equals(userInfo2.getCauthorizerCode())) {
                    flag = true;
                    break;
                }
            }
        }
        if (useCode.equals(applyCode)) {
            flag = true;
        }
        if (!flag) {
            executeResult.addErrorMessage("您未授权或不是申请人！无法修改！");
            return executeResult;
        }
        executeResult.setResult(discardSellByDocument);

        return executeResult;
    }

    /**
     * 报废修改
     */
    @RequestMapping("/updateDiscardSell")
    public String updateDiscardSell(Model model, HttpServletRequest request,
                                    String id, Long offset, Long pageSize, String typeId) {
        DiscardSell discardSell = new DiscardSell();
        discardSell.setDocument(id);
        discardSell = discardSellService.getDiscardSell(discardSell);
        String generalIds = discardSell.getGeneralAssetIds();
        GeneralAsset generalAsset = new GeneralAsset();
        List<GeneralAsset> listGneral = new ArrayList<GeneralAsset>();
        if (!"".equals(generalIds) && null != generalIds) {
            String[] ids = generalIds.split(",");

            for (String string : ids) {
                generalAsset = thingsPersonalService.searchDetails(Long
                        .parseLong(string));
                DiscardSellDetail discardSellDetail = new DiscardSellDetail();
                discardSellDetail.setGeneralAssetId(Long.parseLong(string));

                discardSellDetail.setDiscardSellId(Long.parseLong(String
                        .valueOf(discardSell.getId())));
                discardSellDetail = discardSellDetailService
                        .getDiscardSellDetailByAssetId(discardSellDetail);
                generalAsset.setDiscardMoney(discardSellDetail.getDiscardMoney());
                generalAsset.setDiscardPrecent(discardSellDetail
                        .getDiscardPercent());
                listGneral.add(generalAsset);
            }
        }

        String userCode = (String) request.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);

        setUserBeanProperties(userCode, discardSell);

        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService
                .showApproveHistory(discardSell.getDocument(), "77031", pager);
        appHistoryPager.setUri(request.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);
        List<FileUpload> listFile = fileUploadService
                .getFilesByDocument(discardSell.getDocument());
        discardSell.setListFile(listFile);
        discardSell.setListGneralAssets(listGneral);
        model.addAttribute("discardSell", discardSell);
        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        model.addAttribute("addresslist", addresslist);
        return "discardsell/discard_sell_update";
    }

    @RequestMapping("/discardSellDetail")
    public String discardSellDetail(Model model, HttpServletRequest request,
                                    String id, Long offset, Long pageSize) {
        DiscardSell discardSell = new DiscardSell();
        discardSell.setDocument(id);
        discardSell = discardSellService.getDiscardSell(discardSell);
        String generalIds = discardSell.getGeneralAssetIds();
        GeneralAsset generalAsset = new GeneralAsset();
        List<GeneralAsset> listGneral = new ArrayList<GeneralAsset>();
        String[] ids = generalIds.split(",");
        for (String string : ids) {
            if (!"".equals(string)) {
                generalAsset = thingsPersonalService.searchDetails(Long
                        .parseLong(string));
            }
            DiscardSellDetail discardSellDetail = new DiscardSellDetail();
            if (!"".equals(string)) {
                discardSellDetail.setGeneralAssetId(Long.parseLong(string));
                discardSellDetail.setDiscardSellId(Long.parseLong(String.valueOf(discardSell.getId())));
            }
            discardSellDetail = discardSellDetailService
                    .getDiscardSellDetailByAssetId(discardSellDetail);
            if (discardSellDetail != null) {

                generalAsset.setDiscardMoney(discardSellDetail.getDiscardMoney());
                generalAsset.setDiscardPrecent(discardSellDetail
                        .getDiscardPercent());
            }
            listGneral.add(generalAsset);
        }
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService
                .showApproveHistory(discardSell.getDocument(), "77031", pager);
        appHistoryPager.setUri(request.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);
        List<FileUpload> listFile = fileUploadService
                .getFilesByDocument(discardSell.getDocument());
        discardSell.setListFile(listFile);
        discardSell.setListGneralAssets(listGneral);
        model.addAttribute("discardSell", discardSell);
        return "discardsell/discard_sell_detail";
    }

    /**
     * 报废申请提交数据
     */
    @RequestMapping("/api/insertDiscardSell")
    @ResponseBody
    public ExecuteResult<DiscardSell> insertDiscardSell(
            HttpServletRequest request, DiscardSell discardSell,
            String assetIds, BigDecimal originalValueSum, BigDecimal assetNetValueSum,
            BigDecimal discardMoneySum, String moneyList, String percentList) {
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();

        if (COMMITTYPE.SUBMIT.getCommitType().equals(
                discardSell.getCommitType())) {
            if (null == discardSell.getSubordinateDepartment()
                    || "".equals(discardSell.getSubordinateDepartment())
                    || null == discardSell.getApplication()
                    || "".equals(discardSell.getApplication())) {
                result.addErrorMessage("必填项不能为空！");
                return result;
            }
        }
        DiscardSell discardSellByDocument = discardSellService
                .getDiscardSellByDocument(discardSell.getDocument());
        if (null != discardSellByDocument) {
            result.addErrorMessage("单据已存在，不可重复申请！");
            return result;
        }
        if (null != assetIds && !"".equals(assetIds)) {
            discardSell.setAssetOriginalValueSum(originalValueSum);
            discardSell.setAssetNetValueSum(assetNetValueSum);
            discardSell.setDiscardMoneySum(discardMoneySum);
            discardSell.setIaMoeny(discardMoneySum);
            AttchConfig attchConfig = new AttchConfig();
            attchConfig.setCostcenterCode(discardSell.getCostCenterCode());
            discardSell.setCreateDate(new Date());

            if (ASSETTYPE.ITASSET.getAssetType().equals(
                    discardSell.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTITASSET.getAssetType());
            }
            if (ASSETTYPE.ADMINISTRATIVE.getAssetType().equals(
                    discardSell.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTADMINISTRATIVE
                        .getAssetType());
            }
            if (ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType().equals(
                    discardSell.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTMEASURINGINSTRUMENTS
                        .getAssetType());
            }
            if (ASSETTYPE.MACHINERYEQUIPMENT.getAssetType().equals(
                    discardSell.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTMACHINERYEQUIPMENT
                        .getAssetType());
            }
            attchConfig = attchConfigService
                    .searchByCostcenterCodeAndAssetType(attchConfig);
            if (null != attchConfig) {
                discardSell.setPutmanagerDepCode(attchConfig
                        .getAttachDepartManagerCode());
                discardSell.setPutmanagerCode(attchConfig
                        .getAttachDepartAdminCode());
                discardSell.setTwoDepartmentHeadCode(attchConfig
                        .getTwoDepartmentHeadCode());
            }
            WorkAddress workAddress = workAddressService.get(discardSell
                    .getOfficeLocation());
            discardSell.setReceivegoodsCode(workAddress.getReceivegoodsCode());  // 仓库收货管理员编码
            discardSell.setReceivegoodsName(workAddress.getReceivegoodsName());  // 仓库收货管理员姓名

            discardSell.setRetiredgoodsCode(workAddress.getRetiredgoodsCode());  // 仓库废品管理员编码
            discardSell.setRetiredgoodsName(workAddress.getRetiredgoodsName());  // 仓库废品管理员姓名

            discardSell.setPurchasetipaccessCode(workAddress
                    .getPurchasetipaccessCode());  // 采购意见处理员编码
            discardSell.setPurchasetipaccessName(workAddress
                    .getPurchasetipaccessName());  // 采购意见处理员姓名

            discardSell.setPurchasebidCode(workAddress.getPurchasebidCode());  // 采购竞价/议价员编码
            discardSell.setPurchasebidName(workAddress.getPurchasebidName());  // 采购竞价/议价员姓名

            discardSell.setPayeeCode(workAddress.getPayeeCode());
            discardSell.setPayeeName(workAddress.getPayeeName());
            discardSell.setOfficeLocation(workAddress.getCaddressName());
            discardSell.setDiscardOrSell(DISCARDORSELL.DISCARD.getDiscardOrSell());  // 默认走变卖
            discardSellService.insertDiscardSell(discardSell, assetIds,
                    moneyList, percentList);
        } else {
            result.addErrorMessage("资产不能为空！");
        }
        // 保存为草稿
        if (COMMITTYPE.DRAFT.getCommitType()
                .equals(discardSell.getCommitType())) {
            return result;
        }
        if ("on".equals(discardSell.getIsPhoneMessage())) {
            discardSell.setIsPhoneMessage("Y");
        }
        if ("off".equals(discardSell.getIsPhoneMessage())) {
            discardSell.setIsPhoneMessage("N");
        }
        if ("on".equals(discardSell.getIsMail())) {
            discardSell.setIsMail("Y");
        }
        if ("off".equals(discardSell.getIsMail())) {
            discardSell.setIsMail("N");
        }
        DiscardSell discardSellTt = discardSellService.getDiscardSell(discardSell);
        discardSell.setCreateDate(discardSellTt.getCreateDate());
        result = discardSellService.bindApplyWorkFlow(discardSell);
        if (!result.isSuccess()) {
            /*
             * discardSellService.deleteDiscardSellByDocument(discardSell.
             * getDocument());
             * workFlowService.deleteGarbegeWorkFlowData(discardSell
             * .getDocument(),"77031");
             */
            return result;
        }
        DiscardSell discardSelltt = discardSellService.getDiscardSell(discardSell);
        if (null != discardSelltt) {

            ExecuteResult<Employee> er = remindedService.BellReminder(
                    discardSelltt.getNextHandlerCode(),
                    discardSelltt.getDocument(),
                    discardSell.getIsPhoneMessage(),
                    discardSell.getIsMail()
            );
            if (!er.isSuccess()) {
                result.addErrorMessage("提交成功，但邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }

        return result;
    }

    @RequestMapping("/api/deleteDiscardSell")
    @ResponseBody
    public ExecuteResult<DiscardSell> deleteDiscardSell(String documents,
                                                        HttpServletRequest request) {
        DiscardSell discardSell = new DiscardSell();
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();

        UserInfo userInfo = new UserInfo();
        String loginUserCode = (String) request.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        userInfo.setLoginUserCode(loginUserCode);

        List<UserInfo> userInfos = userInfoService
                .searchCauthorizerCode(userInfo);
        if (!"".equals(documents)) {
            Boolean sumFlag = true;  // 判断全部是否可以删除
            String[] documentArray = documents.split(",");
            for (String string : documentArray) {
                Boolean flag = false;  // 循环一次重新复制 判断 看是否有不符合条件 的 有 一条就直接 返回 一条没有
                // 统一删除
                discardSell.setDocument(string);
                discardSell.setId(null);
                discardSell = discardSellService.getDiscardSell(discardSell);
                if (null == discardSell.getApprovalState()
                        || APPROVESTATE.WAITING.getApproveState().equals(
                        discardSell.getApprovalState())
                        || APPROVESTATE.DENY.getApproveState().equals(
                        discardSell.getApprovalState())) {
                    if (userInfos.size() != 0 && userInfos != null) {
                        for (UserInfo userInfo2 : userInfos) {
                            if (discardSell.getUserCode().equals(
                                    userInfo2.getCauthorizerCode())) {
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (loginUserCode.equals(discardSell.getUserCode())) {
                        flag = true;
                    }
                    if (!flag) {
                        sumFlag = false;
                        result.addErrorMessage("您没有删除权限！");
                        return result;
                    }

                } else {
                    result.addErrorMessage("只能删除【草稿】或【等待审批】或【退回】状态的单据");
                    return result;
                }
            }
            if (sumFlag) {

                discardSellService.updateDiscardByListIds(documents);
                String[] documentList = documents.split(",");
                for (String string : documentList) {
                    workFlowService.deleteGarbegeWorkFlowData(string, Env.getProperty("discard.info.type.id"));
                }
            }

        } else {
            result.addErrorMessage("请选择一条数据");
            return result;
        }

        return result;
    }

    @RequestMapping("/api/updateDiscardSellIds")
    @ResponseBody
    public ExecuteResult<DiscardSell> updateDiscardSell(
            DiscardSell discardSell, HttpServletRequest req, String ids,
            BigDecimal originalValueSum, BigDecimal assetNetValueSum,
            BigDecimal discardMoneySum, String moneyList, String percentList) {
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();
        DiscardSell discardSellBack = discardSellService
                .getDiscardSellByDocument(discardSell.getDocument());
        String userCode = (String) req.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        String userName = (String) req.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NICK_NAME);
        if (COMMITTYPE.SUBMIT.getCommitType().equals(
                discardSell.getCommitType())) {
            if (null == discardSell.getSubordinateDepartment()
                    || "".equals(discardSell.getSubordinateDepartment())
                    || null == discardSell.getApplication()
                    || "".equals(discardSell.getApplication())) {
                result.addErrorMessage("必填项不能为空！");
                return result;
            }
        }
        if (null != ids && !"".equals(ids)) {
            AttchConfig attchConfig = new AttchConfig();
            attchConfig.setCostcenterCode(discardSell.getCostCenterCode());

            if (ASSETTYPE.ITASSET.getAssetType().equals(
                    discardSell.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTITASSET.getAssetType());
            }
            if (ASSETTYPE.ADMINISTRATIVE.getAssetType().equals(
                    discardSell.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTADMINISTRATIVE
                        .getAssetType());
            }
            if (ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType().equals(
                    discardSell.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTMEASURINGINSTRUMENTS
                        .getAssetType());
            }
            if (ASSETTYPE.MACHINERYEQUIPMENT.getAssetType().equals(
                    discardSell.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTMACHINERYEQUIPMENT
                        .getAssetType());
            }
            attchConfig = attchConfigService
                    .searchByCostcenterCodeAndAssetType(attchConfig);
            if (null != attchConfig) {
                discardSell.setPutmanagerDepCode(attchConfig
                        .getAttachDepartManagerCode());
                discardSell.setPutmanagerCode(attchConfig
                        .getAttachDepartAdminCode());
                discardSell.setTwoDepartmentHeadCode(attchConfig
                        .getTwoDepartmentHeadCode());
            }
            discardSell.setDiscardMoneySum(discardMoneySum);
            discardSell.setAssetOriginalValueSum(originalValueSum);
            discardSell.setAssetNetValueSum(assetNetValueSum);
            WorkAddress workAddress = workAddressService.get(discardSell
                    .getOfficeLocation());
            discardSell.setReceivegoodsCode(workAddress.getReceivegoodsCode());  // 仓库收货管理员编码
            discardSell.setReceivegoodsName(workAddress.getReceivegoodsName());  // 仓库收货管理员姓名

            discardSell.setRetiredgoodsCode(workAddress.getRetiredgoodsCode());  // 仓库废品管理员编码
            discardSell.setRetiredgoodsName(workAddress.getRetiredgoodsName());  // 仓库废品管理员姓名

            discardSell.setPurchasetipaccessCode(workAddress
                    .getPurchasetipaccessCode());  // 采购意见处理员编码
            discardSell.setPurchasetipaccessName(workAddress
                    .getPurchasetipaccessName());  // 采购意见处理员姓名

            discardSell.setPurchasebidCode(workAddress.getPurchasebidCode());  // 采购竞价/议价员编码
            discardSell.setPurchasebidName(workAddress.getPurchasebidName());  // 采购竞价/议价员姓名

            discardSell.setPayeeCode(workAddress.getPayeeCode());
            discardSell.setPayeeName(workAddress.getPayeeName());
            discardSell.setOfficeLocation(workAddress.getCaddressName());
            discardSell.setIaMoeny(discardMoneySum);
            discardSell.setLoginUseCode(userCode);
            discardSell.setLoginUseName(userName);

            discardSell.setDiscardOrSell(DISCARDORSELL.DISCARD.getDiscardOrSell());  // 默认走报废
            DiscardSell discardSellTt = discardSellService.getDiscardSell(discardSell);
            discardSell.setCreateDate(discardSellTt.getCreateDate());
            result = discardSellService.updateDiscardell(discardSell, ids, moneyList,
                    percentList);
            if (!result.isSuccess()) {
                return result;
            }
        } else {
            result.addErrorMessage("资产不能为空！");
        }
        // 修改页面 如果是草稿 不绑定审批流
        if (COMMITTYPE.DRAFT.getCommitType()
                .equals(discardSell.getCommitType())) {
            workFlowService.deleteGarbegeWorkFlowData(discardSell.getDocument(), Env.getProperty("discard.info.type.id"));
            return result;
        }
        // 修改提交类型
        discardSellService.convertCommitTypeToOfficial(discardSell
                .getDocument());
        result = discardSellService.bindApplyWorkFlow(discardSell);
//        if (!result.isSuccess()) {
        // 调用失败之后还原数据
        // discardSellService.updateDiscardellBackByDoucment(discardSellBack);
//        }
        DiscardSell discardSelltt = discardSellService.getDiscardSell(discardSell);
        if (null != discardSelltt) {
            ExecuteResult<Employee> er = remindedService.BellReminder(
                    discardSelltt.getNextHandlerCode(),
                    discardSelltt.getDocument(),
                    discardSell.getIsPhoneMessage(),
                    discardSell.getIsMail()
            );
            if (!er.isSuccess()) {
                result.addErrorMessage("修改成功，但邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        return result;
    }

    @RequestMapping("/api/getDiscardSell")
    @ResponseBody
    public ExecuteResult<DiscardSell> getDiscardSell(String ids,
                                                     ModelMap model, DiscardSell discardSell,
                                                     HttpServletRequest request, HttpServletResponse response) {
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();
        List<GeneralAsset> listGenera = new ArrayList<GeneralAsset>();
        if (!"".equals(ids)) {
            String[] id = ids.split(",");
            GeneralAsset generalAsset = new GeneralAsset();
            for (String string : id) {
                generalAsset = thingsPersonalService.searchDetails(Long
                        .parseLong(string));
                listGenera.add(generalAsset);
            }

        } else {
            result.addErrorMessage("请选择一条资产");
        }
        discardSell.setListGneralAssets(listGenera);
        result.setResult(discardSell);
        return result;
    }

    @RequestMapping("/updateSAP")
    @ResponseBody
    public ExecuteResult<DiscardSell> updateSap(Model model,
                                                HttpServletRequest request, String document) {
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();
        JSONObject obj = new JSONObject();
        DiscardSell discardSell = new DiscardSell();
        discardSell.setDocument(document);
        String errorList = "";
        discardSell = discardSellService.getDiscardSell(discardSell);
        DiscardSellDetail discardSellDetail = new DiscardSellDetail();
        discardSellDetail.setDiscardSellId(Long.parseLong(String
                .valueOf(discardSell.getId())));
        List<DiscardSellDetail> listDetail = discardSellDetailService
                .serachDiscardSellDetailBydiscardSellId(discardSellDetail);
        for (DiscardSellDetail discardSellDetail2 : listDetail) {
            GeneralAsset generalAsset = generalAssetService
                    .getGeneralAsset(String.valueOf(discardSellDetail2
                            .getGeneralAssetId()));
            String ipAsset = "";
            String secondaryAsset = "";
            if (!"".equals(generalAsset.getAssetCode())) {
                ipAsset = generalAsset.getAssetCode().split("-")[0];  // 主资产编码
                secondaryAsset = generalAsset.getAssetCode().split("-")[1];  // 次级编码
            }
            if ("0".equals(generalAsset.getIsExpenseAssets())) {
                Generalpostingdata gData = new Generalpostingdata();
                Retirementdata rData = new Retirementdata();
                gData.setCompCode(generalAsset.getCompanyCode());
                gData.setAssetMaino(ipAsset);  // 主编码
                gData.setAssetSubno(secondaryAsset);  // 次级编码
                String s = discardSellDetail2.getDiscardPercent();
                s = s.replace("%", "");
                if (!"100".equals(s)) {
                    rData.setNotAll(true);  // 部分报废
                    rData.setPercRate(new BigDecimal(Float.parseFloat(s)));
                } else {
                    rData.setNotAll(false);   // 完全报废
                }
                /*
                 * rData.setAmount(new BigDecimal(0.23)); rData.setRevOnRet(new
                 * BigDecimal(0.03)); rData.setRetCosts(new BigDecimal(0.2));
                 */
                rData.setCurrency("CNY");

                obj = sapUtilTailService.creatScrapCertificateFromSAP(gData,
                        rData);
                String message = obj.getString("message");
                String res = obj.getString("result");
                if ("E".equals(res)) {
                    if ("".equals(errorList)) {
                        errorList = message;
                    } else {
                        errorList = errorList + "," + message;
                    }
                }
                if ("S".equals(res)) {

                    String scrap = obj.getString("messageV2");
                    discardSellDetail2.setScrapCertificate(scrap);
                    discardSellDetailService
                            .updateScrapCertificate(discardSellDetail2);
                }

            }
        }
        if (!"".equals(errorList)) {
            result.addErrorMessage(errorList);
        } else {
            discardSellService.updateIsSap(discardSell);
        }
        return result;
    }

    @RequestMapping("/api/discardSellStampStatus")
    @ResponseBody
    public ExecuteResult<DiscardSell> discardSellStampStatus(String id,
                                                             Model model, HttpServletRequest request, String typeId) {
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();
        DiscardSell discardSell = new DiscardSell();
        discardSell.setId(Long.parseLong(id));
        discardSell = discardSellService.getDiscardSell(discardSell);
        if (null != discardSell) {
            if (APPROVESTATE.DONE.getApproveState().equals(
                    discardSell.getApprovalState())) {
                discardSell.setIsStamp("Y");
                discardSellService.updateStampStatus(discardSell); //修改打印状态
                discardSellStamp(Long.parseLong(String.valueOf(discardSell.getId())), model, request, typeId);
            } else {
                result.addErrorMessage("只能已完成的单据");
            }
        }
        return result;
    }

}
