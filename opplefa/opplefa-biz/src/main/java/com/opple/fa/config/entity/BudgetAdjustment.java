package com.opple.fa.config.entity;

import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wangkaixuan on 2017/3/21.
 */
@Data
public class BudgetAdjustment extends WorkFlowCommon {
    private String budgetId;
    private Date applicationDate; //申请日期
    private Double iamoney; //金额
    private String assetsBudgetType; //预算调整类型

    private String callInDepartmentCode; //调入部门编码
    private String callInDepartmentName; //调入部门名称
    private String callOutDepartmentCode; //调出部门编码
    private String callOutDepartmentName; //调出部门名称
    private String twoDepartmentHeadCode; //二级部门负责人编码
    private String twoDepartmentHeadName; //二级部门负责人名称
    private String outTwoDepartmentHeadCode; //调出部门二级部门负责人编码
    private String outTwoDepartmentHeadName; //调出部门二级部门负责人名称
    private String costCenterCode; //成本中心编码
    private String costCenterName; //成本中心名称
    private String draft; //是否草稿 y是n否
    private String adjustReason; //调整原因


    private List<BudgetAdjustmentDetail> budgetAdjustmentDetailList = new ArrayList<BudgetAdjustmentDetail>(); //详细表信息（子表）

    private String loginUserCode; //登陆人编码
    private String assetAdminId; //管理员角色编码
    private String startDate; //开始日期（查询条件申请日期）
    private String endDate; //结束日期（查询条件申请日期）
    private String budgetYear; //年度
    private String projectCode; //项目编码

}
