package com.opple.fa.processasset.webapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.service.GeneralAssetService;
import com.opple.fa.config.entity.UserInfo;
import com.opple.fa.config.service.UserInfoService;
import com.opple.fa.discardsell.webapp.controller.DiscardSellController;
import com.opple.fa.processasset.entity.ProcessAssets;
import com.opple.fa.processasset.entity.ProcessAssetsDetall;
import com.opple.fa.processasset.export.controller.ExportProcessAssetsTemplate;
import com.opple.fa.processasset.service.ProcessAssetsService;
import com.opple.fa.webapp.controller.BaseOpenApiController;
import com.opple.security.SessionSecurityConstants;
import com.opple.security.entity.FileUpload;
import com.opple.security.entity.User;
import com.opple.security.service.FileUploadService;
import com.opple.security.service.RemindedService;
import com.opple.security.service.UserService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.workflow.entity.ApproveHistory;
import com.opple.workflow.entity.COMMITTYPE;
import com.opple.workflow.service.WorkFlowService;

/**
 * @author zhuang
 */
@Controller
@RequestMapping("/processAssets")
public class ProcessAssetsController extends BaseOpenApiController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DiscardSellController.class);
    @Resource
    private ProcessAssetsService processAssetsService;
    @Resource
    private WorkFlowService workFlowService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserService userService;
    @Resource
    private RemindedService remindedService;
    @Resource
    private GeneralAssetService generalAssetService;
    @Resource
    private FileUploadService fileUploadService;

    /**
     * 主页列表展示，模糊查询
     *
     * @param model
     * @param req
     * @param offset
     * @param pageSize
     * @param processAssets
     * @return
     */
    @RequestMapping("/searchProcessAssets")
    public String searchProcessAssets(Model model, HttpServletRequest req,
                                      Long offset, Long pageSize, ProcessAssets processAssets) {

        processAssets.setUserName(StringUtils.isEmpty(processAssets.getUserName()) ? processAssets.getUserName()
                : processAssets.getUserName().trim());
        processAssets.setAssetOwner(StringUtils.isEmpty(processAssets.getAssetOwner()) ? processAssets.getAssetOwner()
                : processAssets.getAssetOwner().trim());
        processAssets.setDocument(StringUtils.isEmpty(processAssets.getDocument()) ? processAssets.getDocument()
                : processAssets.getDocument().trim());
        processAssets.setProcessAssetsName(StringUtils.isEmpty(processAssets.getProcessAssetsName()) ? processAssets.getProcessAssetsName()
                : processAssets.getProcessAssetsName().trim());
        processAssets.setHomeCompany(StringUtils.isEmpty(processAssets.getHomeCompany()) ? processAssets.getHomeCompany()
                : processAssets.getHomeCompany().trim());
        processAssets.setCostcenterName(StringUtils.isEmpty(processAssets.getCostcenterName()) ? processAssets.getCostcenterName()
                : processAssets.getCostcenterName().trim());

        Pager<ProcessAssets> pager = new Pager<ProcessAssets>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ProcessAssets> processAssetsPager = processAssetsService.searchProcessAssets(pager, processAssets);
        processAssetsPager.setUri(req.getRequestURI());
        model.addAttribute("processAssetsList", processAssetsPager);
        model.addAttribute("processAsset", processAssets);
        return "processasset/process_asset_list";
    }

    /**
     * 点击申请，获得登录人名称
     *
     * @param model
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/processAssetApplication")
    public String assetAllocationApplication(Model model,
                                             HttpServletRequest request) throws ParseException {

        ProcessAssets processAssets = new ProcessAssets();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        processAssets.setApplicationDate(sqlDate);
        String userCode = (String) request.getSession().getAttribute(
                SessionSecurityConstants.KEY_USER_NAME);
        processAssets.setDocument(workFlowService.getDocumentByType("FA_ASPS"));
        setUserBeanProperties(userCode, processAssets);
        model.addAttribute("processAssets", processAssets);

        List<UserInfo> addresslist = userInfoService.searchAddressInfo();
        model.addAttribute("addresslist", addresslist);

	/*	UserInfo userInfo=new UserInfo();
        List<UserInfo> platformlist=userInfoService.searchPlatformInfo(userInfo);
		
		model.addAttribute("platformlist",platformlist);*/

        return "processasset/process_asset_apply";
    }

    private void setUserBeanProperties(String userCode, ProcessAssets processAssets) {
        User userDeptInfo = userService.getUserDeptInfo(userCode);

        processAssets.setUserCode(userCode);

        if (null != userDeptInfo) {
            processAssets.setUserName(userDeptInfo.getUserName());
            processAssets.setDepartmentCode(userDeptInfo.getDepartmentCode());
            processAssets.setDepartmentName(userDeptInfo.getDepartmentName());
            processAssets.setCompanyCode(userDeptInfo.getCompanyCode());
            processAssets.setCompanyName(userDeptInfo.getCompanyName());
            processAssets.setCreateBy(userDeptInfo.getUserName());
            processAssets.setUpdateBy(userDeptInfo.getUserName());
        }

    }

    /**
     * 查看
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/processAssetView")
    public String assetAllocationView(Model model, HttpServletRequest request) {
        String number = request.getParameter("id");
        ProcessAssets processAssets = new ProcessAssets();
        processAssets.setId(Long.parseLong(number));
        ProcessAssets processAssetsList = processAssetsService.examineProcessAssetsList(processAssets);
        if (!"".equals(processAssetsList
                .getApplicationDate()) && null != processAssetsList.getApplicationDate()) {
            java.sql.Date sqlDate = new java.sql.Date(processAssetsList
                    .getApplicationDate().getTime());
            processAssetsList.setApplicationDate(sqlDate);
            List<FileUpload> listFile = fileUploadService
                    .getFilesByDocument(processAssetsList.getDocument());
            processAssetsList.setListFile(listFile);
        }

        model.addAttribute("processAssets", processAssetsList);
        return "processasset/process_asset_detail";
    }

    @RequestMapping("/examineProcessAssetsList")
    public String examineProcessAssetsList(Model model, HttpServletRequest req, ProcessAssets processAssets) {

        ProcessAssets processAssetsList = processAssetsService.examineProcessAssetsList(processAssets);
        req.setAttribute("processAssetsList", processAssetsList);

        return null;
    }

    /**
     * 保存提交申请
     *
     * @param processAssets
     * @param processAssetsDetall
     * @param date
     * @return
     * @throws ParseException
     */
    @RequestMapping("/api/saveProcessAssets")
    @ResponseBody
    public ExecuteResult<ProcessAssets> saveProcessAssets(ProcessAssets processAssets, String processAssetsDetall, String date, String originalValueSum, String assetNetValueSum, String cause) throws ParseException {

        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        processAssets.setApplicationDate(sdf.parse(date));

        if (COMMITTYPE.SUBMIT.getCommitType().equals(processAssets.getCommitType())) {
            if (processAssetsDetall == null || "".equals(processAssetsDetall)) {
                result.addErrorMessage("资产不能为空！");
                return result;
            }
            if (cause == null || "".equals(cause)
                    || processAssets.getHomeCompany() == null
                    || "".equals(processAssets.getHomeCompany())
                    || processAssets.getAssetDepartmentCode() == null
                    || "".equals(processAssets.getAssetDepartmentCode())
                    || null == processAssets.getOfficeLocation()
                    || "".equals(processAssets.getOfficeLocation())
                    || processAssets.getProcessAssetsName() == null
                    || "".equals(processAssets.getProcessAssetsName())) {
                result.addErrorMessage("必填项不能为空！");
                return result;
            }
        }
        result = processAssetsService.saveProcessAssets(processAssets, processAssetsDetall);

        if ("0".equals(processAssets.getCommitType())) {
            return result;
        }
        //result = processAssetsService.bindApplyWorkFlow(processAssets);
        if (!result.isSuccess()) {
            processAssetsService.deleteProcessAssetsByDocument(processAssets.getDocument());
            //workFlowService.deleteGarbegeWorkFlowData(processAssets.getDocument(),"77087");
            return result;
        }
        return result;
    }

    /**
     * 是否允许修改
     *
     * @param document
     * @return
     */
    @RequestMapping("/ifUpdatePermitted")
    @ResponseBody
    public ExecuteResult<ProcessAssets> ifUpdatePermitted(String id, HttpServletRequest req) {
        ExecuteResult<ProcessAssets> executeResult = ifDocumentExists(id, req);
        if (!executeResult.isSuccess()) {
            return executeResult;
        }
/*
        if (!APPROVESTATE.WAITING.getApproveState().equals(executeResult.getResult().getApprovalState())
                && !APPROVESTATE.DENY.getApproveState().equals(executeResult.getResult().getApprovalState())
                && COMMITTYPE.SUBMIT.getCommitType().equals(executeResult.getResult().getCommitType())) {
            executeResult.addErrorMessage(executeResult.getResult().getApprovalState() + "状态的单据不允许修改");
            return executeResult;
        }*/

        return executeResult;
    }

    private ExecuteResult<ProcessAssets> ifDocumentExists(String id, HttpServletRequest request) {
        ExecuteResult<ProcessAssets> executeResult = new ExecuteResult<ProcessAssets>();
        ProcessAssets processAssets = new ProcessAssets();

        if (StringUtils.isBlank(id)) {
            executeResult.addErrorMessage("请选择数据");
            return executeResult;
        }
        processAssets.setId(Long.parseLong(String.valueOf(id)));
        ProcessAssets processAssetsList = processAssetsService.examineProcessAssetsList(processAssets);
        if (null == processAssetsList) {
            executeResult.addErrorMessage("所选数据不存在");
            return executeResult;
        }
        String loginUsername = request.getSession()
                .getAttribute(SessionSecurityConstants.KEY_USER_NAME)
                .toString().trim();
        if (!loginUsername.equals(processAssetsList.getUserCode())) {
            executeResult.addErrorMessage("您未授权或不是申请人！无法修改！");
            return executeResult;
        }
        executeResult.setResult(processAssetsList);

        return executeResult;
    }

    /**
     * 修改
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/processAssetUpdate")
    public String updateProcessAsset(Model model, HttpServletRequest request) {
        String number = request.getParameter("id");
        ProcessAssets processAssets = new ProcessAssets();
        processAssets.setId(Long.parseLong(number));
        ProcessAssets processAssetsList = processAssetsService.examineProcessAssetsList(processAssets);
        if (!"".equals(processAssetsList
                .getApplicationDate()) && null != processAssetsList.getApplicationDate()) {
            java.sql.Date sqlDate = new java.sql.Date(processAssetsList
                    .getApplicationDate().getTime());
            processAssetsList.setApplicationDate(sqlDate);
            UserInfo userInfo = new UserInfo();
            List<UserInfo> addresslist = userInfoService.searchAddressInfo();
            model.addAttribute("addresslist", addresslist);
            List<FileUpload> listFile = fileUploadService
                    .getFilesByDocument(processAssetsList.getDocument());
            processAssetsList.setListFile(listFile);
/*			

			List<UserInfo> platformlist=userInfoService.searchPlatformInfo(userInfo);
			
			model.addAttribute("platformlist",platformlist);*/
        }


        model.addAttribute("processAssets", processAssetsList);

        return "processasset/process_asset_update";
    }

    /**
     * 修改
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/api/updateProcessAsset")
    @ResponseBody
    public ExecuteResult<ProcessAssets> updateProcessAsset(ProcessAssets processAssets, String ids, String originalValueSum, String assetNetValueSum, String cause) {
        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();
        String errorMessage = "";
        ProcessAssetsDetall processAssetsDetall = new ProcessAssetsDetall();
        processAssetsDetall.setProcessAssetsId(processAssets.getId());
        List<String> listProDetalls = processAssetsService.searchProcessAssetsDetallById(processAssetsDetall);
        List<String> listString = new ArrayList<String>();
        List<String> listUpdate = new ArrayList<String>();
        if (COMMITTYPE.SUBMIT.getCommitType().equals(processAssets.getCommitType())) {
            if (ids == null || "".equals(ids)) {
                result.addErrorMessage("资产不能为空！");
                return result;
            } else {

                String[] generalIds = ids.split(",");
                for (String string : generalIds) {
                    listString.add(string);

                }
                //判断传进来的id  与  已保存的id 比较
                for (String string : listString) {
                    if (!listProDetalls.contains(string)) {
                        listUpdate.add(string);
                    }
                }
                if (listUpdate.size() != 0 && listUpdate != null) {
                    for (String string2 : listUpdate) {
                        GeneralAsset generalAsset = generalAssetService.getGeneralAsset(string2);
                        if ("1".equals(generalAsset.getIsAssembleOccupancy())) {
                            if ("".equals(errorMessage)) {
                                errorMessage = generalAsset.getAssetCode() + "[" + generalAsset.getCompanyCode() + "]";
                            } else {
                                errorMessage = errorMessage + "," + generalAsset.getAssetCode() + "[" + generalAsset.getCompanyCode() + "]";
                            }
                        }
                    }

                }
            }
            if (!"".equals(errorMessage)) {
                result.addErrorMessage("编码公司为" + errorMessage + "的资产已被组合");
                return result;
            }
            if (cause == null || "".equals(cause)
                    || processAssets.getHomeCompany() == null
                    || "".equals(processAssets.getHomeCompany())
                    || processAssets.getAssetDepartmentCode() == null
                    || "".equals(processAssets.getAssetDepartmentCode())
                    || null == processAssets.getOfficeLocation()
                    || "".equals(processAssets.getOfficeLocation())
                    || processAssets.getProcessAssetsName() == null
                    || "".equals(processAssets.getProcessAssetsName())) {
                result.addErrorMessage("必填项不能为空！");
                return result;
            }

        }

        if (originalValueSum != null && !"".equals(originalValueSum)) {
            processAssets.setOriginalValueSum(Double.parseDouble(originalValueSum));
        }
        if (assetNetValueSum != null && !"".equals(assetNetValueSum)) {
            processAssets.setAssetNetValueSum(Double.parseDouble(assetNetValueSum));
        }
        processAssets.setCause(cause);
        processAssetsService.updateProcessAssets(processAssets, ids);

        result.setResult(processAssets);


        // 保存为草稿
        if ("0".equals(processAssets.getCommitType())) {
            return result;
        }

        // result = processAssetsService.bindApplyWorkFlow(processAssets);
        if (!result.isSuccess()) {
            processAssetsService.deleteProcessAssetsByDocument(processAssets.getDocument());
            //workFlowService.deleteGarbegeWorkFlowData(processAssets.getDocument(),"77087");
            return result;
        }
        return result;
    }
    /*@RequestMapping("/api/updateProcessAssets")
    @ResponseBody
	public String updateProcessAssets(ProcessAssets processAssets,String processAssetsDetall) {
		
		if(!"".equals(processAssetsDetall)&&null!=processAssetsDetall){
			processAssetsService.updateProcessAssets(processAssets, processAssetsDetall);
		}else{
			
			return "资产不能为空";
		}
		
		
		return null;
	}*/

    @RequestMapping("/api/deleteProcessAssets")
    @ResponseBody
    public ExecuteResult<ProcessAssets> deleteProcessAssets(ProcessAssets processAssets) {

        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();

        if (!"".equals(processAssets.getId())) {

            result = processAssetsService.deleteProcessAssets(processAssets);

        } else {
            result.addErrorMessage("请选择一条数据");
        }


        return result;
    }

    /**
     * 导出
     *
     * @param model
     * @param request
     * @param response
     * @param processAssets
     * @return
     */
    @RequestMapping(value = "/Export", method = RequestMethod.GET)
    @ResponseBody
    public void export(ModelMap model, HttpServletRequest request,
                       HttpServletResponse response, ProcessAssets processAssets) {

        String fileName = "组合资产.xlsx";
        List<ProcessAssets> processAssetsList = processAssetsService.export(processAssets);
        try {
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            ExportProcessAssetsTemplate epat = new ExportProcessAssetsTemplate();
            epat.doExport(response, fileName, processAssetsList);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }

    }

    /**
     * 审批
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/processAssetApprove")
    public String assetAllocationApprove(Model model, HttpServletRequest request) {
        String number = request.getParameter("id");
        ProcessAssets processAssets = new ProcessAssets();
        processAssets.setId(Long.parseLong(number));
        ProcessAssets processAssetsList = processAssetsService.examineProcessAssetsList(processAssets);
        if (!"".equals(processAssetsList
                .getApplicationDate()) && null != processAssetsList.getApplicationDate()) {
            java.sql.Date sqlDate = new java.sql.Date(processAssetsList
                    .getApplicationDate().getTime());
            processAssetsList.setApplicationDate(sqlDate);
            List<FileUpload> listFile = fileUploadService
                    .getFilesByDocument(processAssetsList.getDocument());
            processAssetsList.setListFile(listFile);
        }

        model.addAttribute("processAssets", processAssetsList);
        return "processasset/process_asset_approve";
    }

    @RequestMapping("/showApproveHistory")
    public String showApproveHistory(Model model, Long offset, Long pageSize, String document, String typeId, HttpServletRequest request) {
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(document, typeId, pager);
        appHistoryPager.setUri(request.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);

        return "processasset/approve_history";
    }

    //同意
    @RequestMapping("/showAgreeDialog")
    public String showAgreeDialog(Model model) {

        return "processasset/agree_dialog";
    }

    //退回
    @RequestMapping("/showDenyDialog")
    public String showDenyDialog(Model model) {

        return "processasset/deny_dialog";
    }

    //弃审
    @RequestMapping("/showDropDialog")
    public String showDropDialog(Model model) {

        return "processasset/drop_dialog";
    }

    @RequestMapping("/agreeProcessAsset")
    @ResponseBody
    public ExecuteResult<ProcessAssets> agreeProcessAsset(ProcessAssets processAssets, HttpServletRequest request) {
        String caUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String caUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();
            /*String document=assetAllocation.getDocument();
	    	GeneralAsset generalAsset=new GeneralAsset();
	    	generalAsset.setDocument(document);
	    	generalAsset.getApprovalState();*/
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);
        processAssets.setCaUserCode(caUserCode);
        processAssets.setCaUserName(caUserName);
        processAssets.setUserDepCode(userDeptInfo.getDepartmentCode());
        setUserBeanProperties(caUserCode, processAssets);
        if (StringUtils.isBlank(processAssets.getIdea())) {
            result.addErrorMessage("意见不可为空");
            return result;
        }
        result = processAssetsService.agreeWorkFlow(processAssets);
        if (!result.isSuccess()) {
            return result;
        }

        ProcessAssets processAssetsByDocument = processAssetsService.getProcessAssetsByDocument(processAssets.getDocument());

        if ("on".equals(processAssets.getIsMail())) {
            processAssets.setIsMail("Y");
        }

        if ("on".equals(processAssets.getIsPhoneMessage())) {
            processAssets.setIsPhoneMessage("Y");
        }

        remindedService.BellReminder(processAssetsByDocument.getNextHandlerCode(), processAssetsByDocument.getDocument(), processAssetsByDocument.getIsMail(), processAssetsByDocument.getIsPhoneMessage());
        return result;
    }

    @RequestMapping("/denyProcessAsset")
    @ResponseBody
    public ExecuteResult<ProcessAssets> denyProcessAsset(ProcessAssets processAssets, HttpServletRequest request) {
        String caUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String caUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);
        processAssets.setCaUserCode(caUserCode);
        processAssets.setCaUserName(caUserName);
        processAssets.setUserDepCode(userDeptInfo.getDepartmentCode());
        setUserBeanProperties(caUserCode, processAssets);
        result = processAssetsService.denyWorkFlow(processAssets);
        if (!result.isSuccess()) {
            return result;
        }

        ProcessAssets processAssetsByDocument = processAssetsService.getProcessAssetsByDocument(processAssets.getDocument());

        if ("on".equals(processAssets.getIsMail())) {
            processAssets.setIsMail("Y");
        }

        if ("on".equals(processAssets.getIsPhoneMessage())) {
            processAssets.setIsPhoneMessage("Y");
        }

        remindedService.BellReminder(processAssetsByDocument.getNextHandlerCode(), processAssetsByDocument.getDocument(), processAssetsByDocument.getIsMail(), processAssetsByDocument.getIsPhoneMessage());
        return result;
    }

    @RequestMapping("/dropProcessAsset")
    @ResponseBody
    public ExecuteResult<ProcessAssets> dropProcessAsset(ProcessAssets processAssets, HttpServletRequest request) {
        String caUserName = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME).toString().trim();
        String caUserCode = request.getSession().getAttribute(SessionSecurityConstants.KEY_USER_NAME).toString().trim();
        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();
        User userDeptInfo = userService.getUserDeptInfo(caUserCode);
        processAssets.setCaUserCode(caUserCode);
        processAssets.setCaUserName(caUserName);
        processAssets.setUserDepCode(userDeptInfo.getDepartmentCode());
        setUserBeanProperties(caUserCode, processAssets);
        result = processAssetsService.dropWorkFlow(processAssets);
        if (!result.isSuccess()) {
            return result;
        }
        ProcessAssets processAssetsnByDocument = processAssetsService.getProcessAssetsByDocument(processAssets.getDocument());

        if ("on".equals(processAssets.getIsMail())) {
            processAssets.setIsMail("Y");
        }

        if ("on".equals(processAssets.getIsPhoneMessage())) {
            processAssets.setIsPhoneMessage("Y");
        }

        remindedService.BellReminder(processAssetsnByDocument.getNextHandlerCode(), processAssetsnByDocument.getDocument(), processAssetsnByDocument.getIsMail(), processAssetsnByDocument.getIsPhoneMessage());
        return result;
    }

    @RequestMapping("api/updatIsAssembleOccupancy")
    public ExecuteResult<ProcessAssets> updatIsAssembleOccupancy(String ids) {
        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();
        if (!"".equals(ids)) {
            String[] assetIds = ids.split(",");
            for (String string : assetIds) {
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setId(Long.parseLong(string));
                generalAsset.setIsAssembleOccupancy("0");
                generalAssetService.updateAssembleOccupancy(generalAsset);
            }
        }
        return result;
    }
/*	@RequestMapping("/api/printOrder")
	public String printOrder(Model model, HttpServletRequest request){
		
		String number = request.getParameter("id");
		AssetAllocation assetAllocationList = assetAllocationService
				.searchAssetAllocationById(Integer.parseInt(number));
		if(!"".equals(assetAllocationList
				.getApplicationDate())&&null!=assetAllocationList.getApplicationDate()){
			java.sql.Date sqlDate = new java.sql.Date(assetAllocationList
					.getApplicationDate().getTime());
			assetAllocationList.setApplicationDate(sqlDate);
		}
	
		model.addAttribute("assetAllocation", assetAllocationList);
		return "assetallocation/asset_allocation_stamp";
	}*/
}
  