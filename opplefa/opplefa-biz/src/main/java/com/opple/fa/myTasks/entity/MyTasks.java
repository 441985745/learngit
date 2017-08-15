package com.opple.fa.myTasks.entity;

import lombok.Data;

/**
 * 我的待办
 * Created by zhangshulong on 2017/2/28.
 */
@Data
public class MyTasks {
    private String documentId; //单据编号
    private String budgetDepartmentCode; //申请部门编码
    private String budgetDepartmentName; //申请部门名称
    private String applyUser; //申请人
    private String beginDate; //开始日期
    private String endDate; //结束日期
    private String applyDate; //申请日期
    private String assetType; //资产类型
    private String applyAmount; //申请金额
    private String approvalAmount; //审批金额
    private String documentStatus; //单据类型
    private String approvalStatus; //审批状态
    private String userCode; //当前登陆人编码
    private String typeId; //审批流编号
}
