package com.opple.fa.security.webapp.controller;

import com.opple.security.entity.CostCenter;
import com.opple.security.service.CostCenterService;
import com.opple.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Deco on 2017/5/4.
 */

@Controller
@RequestMapping("/costCenter")
public class CostCenterController {

    @Resource
    private CostCenterService costCenterService;


    @RequestMapping("/getCodeAndName")
    public String getCodeAndName(Model model, HttpServletRequest req, Long offset, Long pageSize, CostCenter costCenter, String parentWindowId) {
        if (costCenter != null) {
            costCenter.setCostCenterCode(costCenter.getCostCenterCode() != null ? costCenter.getCostCenterCode() : "");
            costCenter.setCostCenterName(costCenter.getCostCenterName() != null ? costCenter.getCostCenterName() : "");
        }

        Pager<CostCenter> pager = new Pager<CostCenter>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(offset == null ? pager.getOffset() : offset);
        pager.setPageSize(pageSize == null ? pager.getPageSize() : pageSize);
        Pager<CostCenter> costCenterPager = costCenterService.getCodeAndName(pager, costCenter);

        costCenterPager.setUri(req.getRequestURI());
        costCenterPager.setParentWindowId(parentWindowId);
        model.addAttribute("costCenterPager", costCenterPager);
        return "config/costCenter_list";


    }


}

