package com.opple.fa.purchase.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NotPoPaymentOrderDetail {

	//id
	private Long id;
	//付款单编号(外键)
	private String notPoPaymentOrderId;
	//创建人
	private String createBy;
	//创建人编码
	private String createByCode;
	//更新人
	private String updateBy;
	//更新人编码
	private String updateByCode;
	//创建时间
	private Date createDate;
	//更新时间
	private Date updateDate;
	//资产名称
	private String assetsName;
	//规格参数
	private String specificationParameter;
	//资产分类
	private String assetClassification;
	//折旧年限(月)
	private String depreciation;
	//总账科目
	private String ledgerAccount;
	//资产责任人编码
	private String assetsUserCode;
	//资产责任人名称
	private String assetsUserName;
	//成本中心
	private String costCenter;
	// 成本中心编码
	private String costCenterCode;
	//内部订单号
	private String internalOrderNo;
	//单位
	private String units;
	//资产增加价值
	private String assetsIncreaseValue;
	//编码方式
	private String codingMode;
	//主资产编码
	private String mainAssetCode;
	//次级
	private String secondary;
	//含税单价
	private BigDecimal taxUnit;
	//净价
	private BigDecimal netPrice;
	//含税总价
	private BigDecimal taxTotalPrice;
	//税额
	private BigDecimal tax;
	//本次申请金额（原币）
	private BigDecimal payPrice;
	//本次申请金额（本币）
	private BigDecimal payPriceLocal;
	//本次冲减预付金额
	private BigDecimal advanceOffsetPrice;
	//尾款金额
	private BigDecimal finalPrice;
	//尾款付款时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finalPaymentDate;
	//预算项目编码
	private String projectCode;
	//预算项目名称
	private String budgetAssetsName;
	//预算年度
	private String budgetYear;
	//可用预算金额
    private BigDecimal avaliableSumMoney;
    //付款数量
  	private int payCount;
  	//文本
  	private String text;
  	//资产类型
  	private String assetsType;
  	//资产所属部门名称
  	private String subordinateDepartment;
  	//资产所属部门编码
  	private String subordinateDepartmentCode;
  	//办公地点
  	private String officeLocation;
}
