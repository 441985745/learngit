package com.opple.fa.security.webapp.controller;

import com.opple.security.entity.Department;
import com.opple.security.service.DepartmentService;
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
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DepartmentService departmentService;


    @RequestMapping("/getCodeAndName")
    public String getCodeAndName(Model model, HttpServletRequest req, Long offset, Long pageSize, String parentWindowId, Department department) {


        Pager<Department> pager = new Pager<Department>();
        pager.setUri(req.getRequestURI());
        pager.setOffset(offset == null ? pager.getOffset() : offset);
        pager.setPageSize(pageSize == null ? pager.getPageSize() : pageSize);

        if (null != department) {
            department.setBudgetDepCode(null != department.getBudgetDepCode() ? department.getBudgetDepCode().trim() : "");
            department.setBudgetDepName(null != department.getBudgetDepName() ? department.getBudgetDepName().trim() : "");
        }


        Pager<Department> deptPager = departmentService.getCodeAndName(pager, department);
        deptPager.setUri(req.getRequestURI());
        deptPager.setParentWindowId(parentWindowId);
        model.addAttribute("deptPager", deptPager);
        //model.addAttribute("param" , department);
        //model.addAttribute("parentWindowId" , parentWindowId);
        return "config/dept_list";


    }

}
