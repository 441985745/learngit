package com.opple.fa.security.webapp.controller;

import com.opple.security.entity.Company;
import com.opple.security.service.CompanyService;
import com.opple.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Wangkaixuan on 2017/5/4.
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @RequestMapping("/searchCompanies")
    public String searchCompanies(Model model,
                  HttpServletRequest request, Long offset, Long pageSize, Company company, String parentWindowId) {
        if (null != company) {
            company.setCompanyCode(null != company.getCompanyCode() ? company.getCompanyCode().trim() : null);
            company.setCompanyName(null != company.getCompanyName() ? company.getCompanyName().trim() : null);
        }

        Pager<Company> pager = new Pager<Company>();
        pager.setOffset(null == offset ? pager.getOffset() : offset);
        pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);

        Pager<Company> companyPager = companyService.searchCompanies(pager, company);
        companyPager.setUri(request.getRequestURI());
        companyPager.setParentWindowId(parentWindowId);

        model.addAttribute("companyPager", companyPager);
        model.addAttribute("parentWindowId", parentWindowId);

        return "config/company_list";
    }


}
