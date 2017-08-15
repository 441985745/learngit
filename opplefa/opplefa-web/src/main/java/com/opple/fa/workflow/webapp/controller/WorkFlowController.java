package com.opple.fa.workflow.webapp.controller;

import com.opple.util.Pager;
import com.opple.workflow.entity.ApproveHistory;
import com.opple.workflow.entity.RouteFlow;
import com.opple.workflow.service.WorkFlowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by phoenix on 19/11/2016.
 */
@Controller
@RequestMapping("/workflow")
public class WorkFlowController {
    @Resource
    private WorkFlowService workFlowService;

    @RequestMapping("/showUpdateCommitDialog")
    public String showUpdateCommitDialog(Model model, String document, String typeId, String changeAmount) {
        List<RouteFlow> routeFlowToers = workFlowService.getRouteFlowToers(document, typeId, RouteFlow.OperateType.SAVE);
        model.addAttribute("routeFlowToers", routeFlowToers);
        //用于判断金额是否改变，若改变则不允许跨步提交 Y改变 N未变
        model.addAttribute("changeAmount", changeAmount);
        return "workflow/update_commit_dialog";
    }

    @RequestMapping("/showAgreeDialog")
    public String showAgreeDialog(Model model, String document, String typeId, String changeAmount) {
        List<RouteFlow> routeFlowToers = workFlowService.getRouteFlowToers(document, typeId, RouteFlow.OperateType.AGREE);
        model.addAttribute("routeFlowToers", routeFlowToers);
        //用于判断金额是否改变，若改变则不允许跨步提交 Y改变 N未变
        model.addAttribute("changeAmount", changeAmount);
        return "workflow/agree_dialog";
    }

    @RequestMapping("/showSubmitDialog")
    public String showSubmitDialog(Model model) {

    	return "workflow/submit_dialog";
    }
    
    @RequestMapping("/complaintsDialog")
    public String complaintsDialog(Model model, String document) {

        return "workflow/complaint_dialog";
    }

    @RequestMapping("/showDenyDialog")
    public String showDenyDiaglog(Model model, String document, String typeId) {
        List<RouteFlow> routeFlowToers = workFlowService.getRouteFlowToers(document, typeId, RouteFlow.OperateType.DENY);
        model.addAttribute("routeFlowToers", routeFlowToers);

        return "workflow/deny_dialog";
    }
    
    @RequestMapping("/showBarDenyDialog")
    public String showBarDenyDialog(Model model, String document, String typeId) {
    	List<RouteFlow> routeFlowToers = workFlowService.getRouteFlowToers(document, typeId, RouteFlow.OperateType.DENY);
        model.addAttribute("routeFlowToers", routeFlowToers);

        return "workflow/bardeny_dialog";
    }

    @RequestMapping("/showDropDialog")
    public String showDropDialog(Model model) {
        return "workflow/drop_dialog";
    }

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

    @RequestMapping("/searchApproveHistory")
    public String searchApproveHistory(Model model, Long offset, Long pageSize, String document, String typeId, HttpServletRequest request) {
        Pager<ApproveHistory> pager = new Pager<ApproveHistory>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<ApproveHistory> appHistoryPager = workFlowService.showApproveHistory(document, typeId, pager);
        appHistoryPager.setUri(request.getRequestURI());

        model.addAttribute("appHistoryPager", appHistoryPager);

        return "workflow/approve_history";
    }
}
