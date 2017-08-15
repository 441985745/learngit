package com.opple.fa.config.entity;

import java.util.Date;

import lombok.Data;

@Data
public class AssetsProcessor {
	//id
	private int id;
	//报废类型
	private String scrapType;
	//资产类型
	private String assetsType;
	//资产类型编码
	private String assetsTypeCode;
	//资产名称
	private String assetsName;
	//项目名称
	private int projectName;
	//单位
	private String unit;
	//数量
	private String amount;
	//单价
	private String unitPrice;
	//预算依据（简单说明背景、目的、计算过程等）
	private String budgetBasedOn;
	//以前年度累计立项金额
	private String agoYearEstablishmentSum;
	//以前年度累计签订合同
	private String agoYearAllContracts;
	//以前年度累计支付
	private String agoYearAllPay;
	//年度预算总额
	private String yearBudgetTotal;
	//创建人
	private String createBy;
	//创建时间
	private Date createDate;
	//更新人
	private String updateBy;
	//更新时间
	private Date updateDate;
}
