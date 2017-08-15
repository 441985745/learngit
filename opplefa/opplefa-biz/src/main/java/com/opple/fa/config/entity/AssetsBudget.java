package com.opple.fa.config.entity;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AssetsBudget {
    //id
    private int id;
    //成本中心
    private String costCenter;
    //成本中心名
    private String costCenterName;
    //项目编码
    private String projectCode;
    //类型
    private String assetsModel;
    //资产类型
    private String assetsType;
    //资产类型编码
    private String assetsTypeCode;
    //资产名称
    private String assetsName;
    //项目名称
    private String projectName;
    //单位
    private String unit;
    //数量
    private Integer amount;
    //已用数量
    private Integer usedAmount;
    //已用金额
    private BigDecimal usedSumMoney;
    //可用数量
    private Integer avaliableAmount;
    //可用金额
    private BigDecimal avaliableSumMoney;
    //单价
    private BigDecimal unitPrice;
    //预算依据（简单说明背景、目的、计算过程等）
    private String budgetBasedOn;
    //以前年度累计立项金额
    private BigDecimal agoYearAllEstablishmentSum;
    //以前年度累计签订合同
    private String agoYearAllContracts;
    //以前年度累计支付
    private BigDecimal agoYearAllPay;
    //年度预算总额
    private BigDecimal yearBudgetTotal;
    //创建人
    private String createBy;
    //创建人编码
    private String createByCode;
    //创建时间
    private Date createDate;
    //更新人
    private String updateBy;
    //更新人编码
    private String updateByCode;
    //更新时间
    private Date updateDate;
    //公司编码
    private String company;
    //公司名
    private String companyName;
    //年度
    private String budgetYear;
    //模糊查询条件项目接收字段
    private String project;
    //模糊查询条件开始维护时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    //模糊查询条件结束维护时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
    //模糊查询条件成本中心
    private String costCenterNameOrCode;
    //是否确认（Y为确认，N为未确认）
    private String isSure;
    //删除列标志（0为可用，1为删除）
    private String deleteFlag;
    //版本号
    private String version;
    //平台
    private String platform;
  //当前登录人编码
  	private String loginUserCode;
  	//当前登录人姓名
  	private String loginUserName;
    //管理员编码
    private String adminCode;

    private String manitDate;


    //资产需求添加和修改页面需要
    private String demandOrderId;
    //验收金额
    private String checkMoney;
}
