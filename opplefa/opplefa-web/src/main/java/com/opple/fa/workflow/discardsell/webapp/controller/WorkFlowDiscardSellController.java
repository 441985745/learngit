package com.opple.fa.workflow.discardsell.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.opple.util.Pager;
import com.opple.workflow.entity.ApproveHistory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.service.GeneralAssetService;
import com.opple.fa.assetcard.service.ThingsPersonalService;
import com.opple.fa.config.entity.UserRole;
import com.opple.fa.config.service.UserRoleService;
import com.opple.fa.discardsell.entity.DISCARDORSELL;
import com.opple.fa.discardsell.entity.DiscardSell;
import com.opple.fa.discardsell.entity.DiscardSellDetail;
import com.opple.fa.discardsell.service.DiscardSellDetailService;
import com.opple.fa.discardsell.service.DiscardSellService;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.entity.Employee;
import com.opple.security.entity.User;
import com.opple.security.service.RemindedService;
import com.opple.security.service.UserService;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.workflow.service.WorkFlowService;

@Controller
@RequestMapping("/workflowDiscardSell")
public class WorkFlowDiscardSellController {
    @Resource
    private RemindedService remindedService;
    @Resource
    private DiscardSellService discardSellService;
    @Resource
    private ThingsPersonalService thingsPersonalService;
    @Resource
    private UserService userService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private DiscardSellDetailService discardSellDetailService;
    @Resource
    private GeneralAssetService generalAssetService;
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping("/discardSellApprove")
    public String discardSellApprove(Model model, String id,
                                     HttpServletRequest req, Long offset, Long pageSize) {
        DiscardSell discardSell = new DiscardSell();
        discardSell.setDocument(id);
        String caUserName = req.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME)
                .toString().trim();
        String caUserCode = req.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim();
        discardSell = discardSellService.getDiscardSell(discardSell);
        String generalIds = discardSell.getGeneralAssetIds();
        DiscardSellDetail discardSellDetail = new DiscardSellDetail();
        GeneralAsset generalAsset = new GeneralAsset();
        List<GeneralAsset> listGneral = new ArrayList<GeneralAsset>();

        String ids[] = generalIds.split(",");
        for (int i = 0; i < ids.length; i++) {

            generalAsset = thingsPersonalService.searchDetails(Long
                    .parseLong(ids[i]));
            discardSellDetail.setGeneralAssetId(Long.parseLong(ids[i]));
            discardSellDetail.setDiscardSellId(Long.parseLong(String
                    .valueOf(discardSell.getId())));
            DiscardSellDetail discardSellDetail2 = discardSellDetailService
                    .getDiscardSellDetailByAssetId(discardSellDetail);
            generalAsset.setDiscardPrecent(discardSellDetail2
                    .getDiscardPercent());
            generalAsset.setDiscardMoney(discardSellDetail2.getDiscardMoney());
            generalAsset.setScrapCertificate(discardSellDetail2
                    .getScrapCertificate());
            listGneral.add(generalAsset);
        }
        // 获得权限
        UserRole userRole = new UserRole();
        //判断是不是归口管理员
        String isManagerPerson = "";
        userRole.setCrolecode(Env.getProperty("role.putMangerPerson.id"));
        userRole.setCusercode(req.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim());
        if (userRoleService.searchUserRole(userRole, discardSell.getDocument(), Env.getProperty("discard.info.type.id")) > 0) {
            isManagerPerson = "Y";
        } else {
            isManagerPerson = "N";
        }
        // 判断当前登录人是不是归口经理
        String isManager = "";
        userRole.setCrolecode(Env.getProperty("role.attachManager.id"));
        userRole.setCusercode(req.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim());
        if (userRoleService.searchUserRole(userRole, discardSell.getDocument(), Env.getProperty("discard.info.type.id")) > 0) {
            isManager = "Y";
        } else {
            isManager = "N";
        }
        // 判断当前登录人是不是平台财务经理
        String isPlatformFinance = "";
        userRole.setCrolecode(Env.getProperty("role.platformFinanceManager.id"));
        if (userRoleService.searchUserRole(userRole, discardSell.getDocument(), Env.getProperty("discard.info.type.id")) > 0) {
            isPlatformFinance = "Y";
        } else {
            isPlatformFinance = "N";
        }
        // 判断是不是采购员
        String isPurchasingPerson = "";
        userRole.setCrolecode(Env.getProperty("role.purchasingPerson.id"));
        if (userRoleService.searchUserRole(userRole, discardSell.getDocument(), Env.getProperty("discard.info.type.id")) > 0) {
            isPurchasingPerson = "Y";
        } else {
            isPurchasingPerson = "N";
        }
        // 判断当前登录人是不是采购经理
        String isPurchasingManage = "";
        userRole.setCrolecode(Env.getProperty("role.purchasingManager.id"));
        if (userRoleService.searchUserRole(userRole, discardSell.getDocument(), Env.getProperty("discard.info.type.id")) > 0) {
            isPurchasingManage = "Y";
        } else {
            isPurchasingManage = "N";
        }
        // 判断当前登录人是不是资产会计
        String isAssetAccounting = "";
        userRole.setCrolecode(Env.getProperty("role.assetAccounting.id"));
        userRole.setCusercode(caUserCode);
        if (userRoleService.searchUserRole(userRole, discardSell.getDocument(), Env.getProperty("discard.info.type.id")) > 0) {
            isAssetAccounting = "Y";
        } else {
            isAssetAccounting = "N";
        }
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);
        discardSell.setLoginUseCode(caUserCode);
        discardSell.setLoginUseName(caUserName);
        discardSell.setLoginDepCode(userDeptInfo.getDepartmentCode());
        boolean lastApprove = discardSellService
                .getIsLastApprovalStep(discardSell);
        model.addAttribute("lastApprove", lastApprove);
        model.addAttribute("isAssetAccounting", isAssetAccounting);
        model.addAttribute("isManagerPerson", isManagerPerson);
        model.addAttribute("isManager", isManager);
        model.addAttribute("isPlatformFinance", isPlatformFinance);
        model.addAttribute("isPurchasingManage", isPurchasingManage);
        model.addAttribute("isPurchasingPerson", isPurchasingPerson);
        discardSell.setListGneralAssets(listGneral);
        model.addAttribute("discardSell", discardSell);
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService
                .showApproveHistory(discardSell.getDocument(), Env.getProperty("discard.info.type.id"), pager);
        appHistoryPager.setUri(req.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);

        return "workflowdiscardsell/discard_sell_approve";
    }

    @RequestMapping("/agreeDiscardSell")
    @ResponseBody
    public ExecuteResult<DiscardSell> agreeDiscardSell(DiscardSell discardSell,
                                                       HttpServletRequest request) {
        String caUserName = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME)
                .toString().trim();
        String caUserCode = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim();
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);
        // 获得权限
        UserRole userRole = new UserRole();
        UserRole userRoleTwo = new UserRole();
        userRoleTwo.setCusercode(request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim());
        userRoleTwo.setCrolecode(Env.getProperty("role.attachManager.id"));
        // 判断当前登录人是不是归口管理员
        userRole.setCrolecode(Env.getProperty("role.putMangerPerson.id"));
        userRole.setCusercode(request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim());
        if (userRoleService.searchUserRole(userRole, discardSell.getDocument(), Env.getProperty("discard.info.type.id")) > 0 || userRoleService.searchUserRole(userRoleTwo, discardSell.getDocument(), Env.getProperty("discard.info.type.id")) > 0) {
            if (discardSell.getDiscardOrSell() == null
                    || "".equals(discardSell.getDiscardOrSell())
                    || null == discardSell.getPutmanageIdea()
                    || "".equals(discardSell.getPutmanageIdea())
                    || null == discardSell.getJudgmentBasis()
                    || "".equals(discardSell.getJudgmentBasis())) {
                result.addErrorMessage("归口必填项不可为空！");
                return result;

            } else {
                if (discardSell.getPutmanageIdea().length() > 15 || discardSell.getJudgmentBasis().length() > 15) {
                    result.addErrorMessage("您输入长度不要超过15个字符！");
                    return result;
                } else {
                    discardSellService.updateDiscardByApprove(discardSell);
                }

            }
        }
        // 判断当前登录人是不是平台财务经理
/*        userRole.setCrolecode(Env.getProperty("role.platformFinanceManager.id"));
        if (userRoleService.searchUserRole(userRole,discardSell.getDocument(),Env.getProperty("discard.info.type.id")) > 0) {
            if (null == discardSell.getDiscardTypeFinance()
                    || "".equals(discardSell.getDiscardTypeFinance())) {
                result.addErrorMessage("平台财务必填项不可为空！");

                return result;
            } else {
                discardSellService.updateDiscardByApprove(discardSell);
            }
        }*/
        // 判断是不是采购员
        userRole.setCrolecode(Env.getProperty("role.purchasingPerson.id"));
        if (userRoleService.searchUserRole(userRole, discardSell.getDocument(), Env.getProperty("discard.info.type.id")) > 0) {
            if (null == discardSell.getSupplierName()
                    || "".equals(discardSell.getSupplierName())
                    || null == discardSell.getSellPattern()
                    || "".equals(discardSell.getSellPattern())
                    || null == discardSell.getActualDisposalPrice()
                    || "".equals(discardSell.getActualDisposalPrice())) {
                result.addErrorMessage("采购员必填项不可为空！");
                return result;
            } else {
                if (discardSell.getSellPattern().length() > 15 || discardSell.getActualDisposalPrice().length() > 15 || discardSell.getSupplierName().length() > 15) {
                    result.addErrorMessage("您输入长度不要超过15个字符！");
                    return result;
                } else {
                    discardSellService.updateDiscardByApprove(discardSell);
                }
            }
        }
        // 判断当前登录人是不是采购经理
        userRole.setCrolecode(Env.getProperty("role.purchasingManager.id"));
        if (userRoleService.searchUserRole(userRole, discardSell.getDocument(), Env.getProperty("discard.info.type.id")) > 0) {
            if ((null == discardSell.getBasePrice() || "".equals(discardSell.getBasePrice())) && DISCARDORSELL.SELL.getDiscardOrSell().equals(discardSell.getDiscardOrSell())) {
                result.addErrorMessage("采购经理必填项不可为空！");
                return result;
            } else {
                if (discardSell.getBasePrice().length() > 15 || discardSell.getPurchasingJudgmentBasis().length() > 15) {
                    result.addErrorMessage("您输入长度不要超过15个字符！");
                    return result;
                } else {
                    discardSellService.updateDiscardByApprove(discardSell);
                }
            }

        }
        // 申请完成更新员工表
        DiscardSell discardSellByDocument = discardSellService
                .getDiscardSellByDocument(discardSell.getDocument());
        discardSellByDocument.setCaUserCode(caUserCode);
        discardSellByDocument.setCaUserName(caUserName);
        discardSellByDocument.setUserDepCode(userDeptInfo.getDepartmentCode());
        if ("on".equals(discardSell.getIsPhoneMessage())) {
            discardSellByDocument.setIsPhoneMessage("Y");
        }
        if ("off".equals(discardSell.getIsPhoneMessage())) {
            discardSellByDocument.setIsPhoneMessage("N");
        }
        if ("on".equals(discardSell.getIsMail())) {
            discardSellByDocument.setIsMail("Y");
        }
        if ("off".equals(discardSell.getIsMail())) {
            discardSellByDocument.setIsMail("N");
        }
        discardSellByDocument.setIdea(discardSell.getIdea());
        //setUserBeanProperties(caUserCode, discardSellByDocument);
        discardSellByDocument.setActivityId(discardSell.getActivityId());
        result = discardSellService.agreeWorkFlow(discardSellByDocument);
        if (!result.isSuccess()) {
            return result;
        }

        // 流程完成后更新数据表

        discardSellService.updateDiscardByApprove(discardSellByDocument);
        DiscardSellDetail discardSellDetail = new DiscardSellDetail();

        discardSellDetail.setDiscardSellId(Long.parseLong(String
                .valueOf(discardSellByDocument.getId())));

        /*
         * if ("on".equals(discardSell.getIsMail())) {
         * discardSell.setIsMail("Y"); }
         * 
         * if ("on".equals(discardSell.getIsPhoneMessage())) {
         * discardSell.setIsPhoneMessage("Y"); }
         */
        discardSell = discardSellService.getDiscardSell(discardSellByDocument);
        if (null != discardSell.getNextHandlerCode() && !"".equals(discardSell.getNextHandlerCode())) {
            ExecuteResult<Employee> er = remindedService.BellReminder(
                    discardSell.getNextHandlerCode(),
                    discardSell.getDocument(),
                    discardSellByDocument.getIsPhoneMessage(),
                    discardSellByDocument.getIsMail()
            );
            if (!er.isSuccess()) {
                result.addErrorMessage("审批成功，但邮件或短信发送失败");
                for (String str : er.getErrorMessages()) {
                    result.addErrorMessage(str);
                }
            }
        }
        return result;
    }

    @RequestMapping("/denyDiscardSell")
    @ResponseBody
    public ExecuteResult<DiscardSell> denyDiscardSell(DiscardSell discardSell, HttpServletRequest request) {
        String caUserName = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME)
                .toString().trim();
        String caUserCode = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim();
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);

        // 获得权限
  /*      UserRole userRole = new UserRole();
        // 判断当前登录人是不是归口管理员
        userRole.setCrolecode(Env.getProperty("role.attachManager.id"));
        userRole.setCusercode(request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim());
        if (userRoleService.searchUserRole(userRole,discardSell.getDocument(),Env.getProperty("discard.info.type.id")) > 0) {
            if (discardSell.getDiscardType() == null
                    || "".equals(discardSell.getDiscardType())
                    || null == discardSell.getPutmanageIdea()
                    || "".equals(discardSell.getPutmanageIdea())
                    || null == discardSell.getJudgmentBasis()
                    || "".equals(discardSell.getJudgmentBasis())) {
                result.addErrorMessage("归口管理员必填项不可为空！");

                return result;

            } else {
                discardSellService.updateDiscardByApprove(discardSell);
            }
        }
        // 判断当前登录人是不是平台财务经理
        userRole.setCrolecode(Env.getProperty("role.platformFinanceManager.id"));
        if (userRoleService.searchUserRole(userRole,discardSell.getDocument(),Env.getProperty("discard.info.type.id")) > 0) {
            if (null == discardSell.getDiscardTypeFinance()
                    || "".equals(discardSell.getDiscardTypeFinance())) {
                result.addErrorMessage("平台财务必填项不可为空！");

                return result;
            } else {
                discardSellService.updateDiscardByApprove(discardSell);
            }
        }
        // 判断是不是采购员
        userRole.setCrolecode(Env.getProperty("role.purchasingPerson.id"));
        if (userRoleService.searchUserRole(userRole,discardSell.getDocument(),Env.getProperty("discard.info.type.id")) > 0) {
            if (null == discardSell.getSupplierName()
                    || "".equals(discardSell.getSupplierName())
                    || null == discardSell.getSellPattern()
                    || "".equals(discardSell.getSellPattern())
                    || null == discardSell.getActualDisposalPrice()
                    || "".equals(discardSell.getActualDisposalPrice())) {
                result.addErrorMessage("采购员必填项不可为空！");
                return result;
            } else {
                discardSellService.updateDiscardByApprove(discardSell);
            }
        }
        // 判断当前登录人是不是采购经理
        userRole.setCrolecode(Env.getProperty("role.purchasingManager.id"));
        if (userRoleService.searchUserRole(userRole,discardSell.getDocument(),Env.getProperty("discard.info.type.id")) > 0) {
            if (null == discardSell.getDiscardOrSell()
                    || "".equals(discardSell.getDiscardOrSell())
                    || null == discardSell.getBasePrice()
                    || "".equals(discardSell.getBasePrice())
                    || null == discardSell.getPurchasingJudgmentBasis()
                    || "".equals(discardSell.getPurchasingJudgmentBasis())) {
                result.addErrorMessage("采购经理必填项不可为空！");
                return result;
            } else {
                discardSellService.updateDiscardByApprove(discardSell);
            }
        }*/
        if (StringUtils.isBlank(discardSell.getIdea())) {
            result.addErrorMessage("意见不可为空");
            return result;
        }
        DiscardSell discardSellByDocument = discardSellService
                .getDiscardSellByDocument(discardSell.getDocument());

        discardSellByDocument.setCaUserCode(caUserCode);
        discardSellByDocument.setCaUserName(caUserName);
        discardSellByDocument.setUserDepCode(userDeptInfo.getDepartmentCode());
        //setUserBeanProperties(caUserCode, discardSellByDocument);
        if ("on".equals(discardSell.getIsPhoneMessage())) {
            discardSellByDocument.setIsPhoneMessage("Y");
        }
        if ("off".equals(discardSell.getIsPhoneMessage())) {
            discardSellByDocument.setIsPhoneMessage("N");
        }
        if ("on".equals(discardSell.getIsMail())) {
            discardSellByDocument.setIsMail("Y");
        }
        if ("off".equals(discardSell.getIsMail())) {
            discardSellByDocument.setIsMail("N");
        }
        discardSellByDocument.setIdea(discardSell.getIdea());
        discardSellByDocument.setActivityId(discardSell.getActivityId());
        result = discardSellService.denyWorkFlow(discardSellByDocument);
        if (!result.isSuccess()) {
            return result;
        }
        // 流程完成后更新数据表
        // discardSellService.updateDiscardellByDocument(discardSell);

        discardSellService.updateDiscardByApprove(discardSellByDocument);
        /*
         * if ("on".equals(discardSell.getIsMail())) {
         * discardSell.setIsMail("Y"); }
         * 
         * if ("on".equals(discardSell.getIsPhoneMessage())) {
         * discardSell.setIsPhoneMessage("Y"); }
         */

        discardSell = discardSellService.getDiscardSell(discardSellByDocument);
        ExecuteResult<Employee> er = remindedService.BellReminder(
                discardSell.getNextHandlerCode(),
                discardSell.getDocument(),
                discardSellByDocument.getIsPhoneMessage(),
                discardSellByDocument.getIsMail()
        );
        if (!er.isSuccess()) {
            result.addErrorMessage("审批成功，但邮件或短信发送失败");
            for (String str : er.getErrorMessages()) {
                result.addErrorMessage(str);
            }
        }
        return result;
    }

    @RequestMapping("/dropDiscardSell")
    @ResponseBody
    public ExecuteResult<DiscardSell> dropDiscardSell(DiscardSell discardSell,
                                                      HttpServletRequest request) {
        String caUserName = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME)
                .toString().trim();
        String caUserCode = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim();
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);
        discardSell.setCaUserCode(caUserCode);
        discardSell.setCaUserName(caUserName);
        discardSell.setUserDepCode(userDeptInfo.getDepartmentCode());
        //setUserBeanProperties(caUserCode, discardSell);
        if (StringUtils.isBlank(discardSell.getIdea())) {
            result.addErrorMessage("意见不可为空");
            return result;
        }
        DiscardSell discardSellByDocument = discardSellService
                .getDiscardSellByDocument(discardSell.getDocument());
        discardSellByDocument.setCaUserCode(caUserCode);
        discardSellByDocument.setCaUserName(caUserName);
        discardSellByDocument.setUserDepCode(userDeptInfo.getDepartmentCode());
        if ("on".equals(discardSell.getIsPhoneMessage())) {
            discardSellByDocument.setIsPhoneMessage("Y");
        }
        if ("off".equals(discardSell.getIsPhoneMessage())) {
            discardSellByDocument.setIsPhoneMessage("N");
        }
        if ("on".equals(discardSell.getIsMail())) {
            discardSellByDocument.setIsMail("Y");
        }
        if ("off".equals(discardSell.getIsMail())) {
            discardSellByDocument.setIsMail("N");
        }

        //setUserBeanProperties(caUserCode, discardSellByDocument);
        discardSellByDocument.setIdea(discardSell.getIdea());
        result = discardSellService.dropWorkFlow(discardSellByDocument);
        if (!result.isSuccess()) {
            return result;
        }
        // 流程完成后更新数据表
        // discardSellService.updateDiscardellByDocument(discardSell);

        discardSellService.updateDiscardByApprove(discardSellByDocument);
        /*
         * if ("on".equals(discardSell.getIsMail())) {
         * discardSell.setIsMail("Y"); }
         * 
         * if ("on".equals(discardSell.getIsPhoneMessage())) {
         * discardSell.setIsPhoneMessage("Y"); }
         */

        // 弃审，当前登录人发送邮件
        discardSell = discardSellService.getDiscardSell(discardSellByDocument);
        ExecuteResult<Employee> er = remindedService.BellReminder(
                discardSell.getNextHandlerCode(),
                discardSell.getDocument(),
                discardSellByDocument.getIsPhoneMessage(),
                discardSellByDocument.getIsMail()
        );
        if (!er.isSuccess()) {
            result.addErrorMessage("审批成功，但邮件或短信发送失败");
            for (String str : er.getErrorMessages()) {
                result.addErrorMessage(str);
            }
        }
        return result;
    }

    @RequestMapping("/showApproveHistory")
    public String showApproveHistory(Model model, Long offset, Long pageSize,
                                     String document, String typeId, HttpServletRequest request) {
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService
                .showApproveHistory(document, typeId, pager);
        appHistoryPager.setUri(request.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);

        return "workflowdiscardsell/approve_history";
    }

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

    @RequestMapping("/showAgreeDialog")
    public String showAgreeDialog(Model model) {

        return "workflowdiscardsell/agree_dialog";
    }

    @RequestMapping("/showDenyDialog")
    public String showDenyDialog(Model model) {

        return "workflowdiscardsell/deny_dialog";
    }

    @RequestMapping("/showDropDialog")
    public String showDropDialog(Model model) {

        return "workflowdiscardsell/drop_dialog";
    }
}
