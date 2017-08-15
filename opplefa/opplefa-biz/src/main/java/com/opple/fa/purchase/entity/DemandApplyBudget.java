package com.opple.fa.purchase.entity;


import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhangshulong on 2017/5/26.
 */
@Data
public class DemandApplyBudget {
    private String applyId; //申请单数量
    private String budgetYear; //预算年份
    private int demandCount; //申请单下的数量
    private int applyCount; //已下订单数量
    private String projectCode; //项目编码
    private BigDecimal demandPrice; //需求单中采购单价
    private BigDecimal applyPrice; //申请单中采购单价
    private BigDecimal budgetPrice; //需要回写的价格  申请单数量*（申请单询价单价-需求单询价单价）
}
