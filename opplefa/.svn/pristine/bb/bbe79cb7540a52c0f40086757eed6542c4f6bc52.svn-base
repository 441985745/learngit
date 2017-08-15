package com.opple.fa.config.entity;

import com.opple.domain.BaseDomain;
import lombok.Data;

/**
 * Created by Wangkaixuan on 2017/3/21.
 */
@Data
public class BudgetAdjustmentDetail extends BaseDomain<String> {
    private String status; //删除列标识（0可用1删除）
    private String budgetYear; //预算年度
    private String costCenter; //成本中心编码
    private String costCenterName; //成本中心
    private String platform; //平台
    private String projectCode; //项目编码
    private String projectName; //项目名称
    private String assetsName; //资产名称
    private String assetsType; //资产类型
    private String assetsModel; //类型
    private String unit; //单位
    private Integer beforeAdjustCount; //调整前数量
    private Integer adjustCount; //调整数量
    private Integer afterAdjustCount; //调整后数量
    private Double beforeUnitPrice; //调整前单价
    private Double afterUnitPrice; //调整后单价
    private Double beforeAdjustMoney; //调整前总额
    private Double adjustMoney; //调整金额
    private Double afterAdjustMoney; //调整后总额
    private String budgetAdjustId; //业务主表ID（外键）
    private String specialAdjustMark; //项目间调整标记 in调入 out调出
    private String budgetBasedOn; //预算依据（简单说明背景、目的、计算过程等）

    private Double usedSumMoney; //已用金额
    private Integer usedAmount;    //已用数量

    private double useableMoney; //可用金额
    private double useableCount; //可用数量

    private String callInDepartmentCode; //调入部门编码
    private String callInDepartmentName; //调入部门名称
    private String twoDepartmentHeadCode; //二级部门负责人编码
    private String twoDepartmentHeadName; //二级部门负责人名称

}
