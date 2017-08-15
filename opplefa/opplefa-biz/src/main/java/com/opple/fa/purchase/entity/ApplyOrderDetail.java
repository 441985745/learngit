package com.opple.fa.purchase.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class ApplyOrderDetail {
	//id
	private Long id;
	//订单可用预算
	private BigDecimal purchaseAvaMoney;
	//采购申请订单编号(外键)
	private String applyOrderId;
	// 合并号
	private String mergeNumber;
	//申请人
	private String applyUser;
	//资产名称
	private String assetsName;
	//需求数量
	private int demandCount;
	//已申请数量
	private int applyCount;
	//未申请数量
	private int notApplyCount;
	//创建人
	private String createBy;
	//创建时间
	private Date createDate;
	//更新人
	private String updateBy;
	//更新时间
	private Date updateDate;
	//创建人编码
	private String createByCode;
	//更新人编码
	private String updateByCode;
	//规格参数
	private String specificationParameter;
	//功能
	private String functions;
	//验收标准
	private String acceptanceCriteria;
	//选配件
	private String optionalAccessories;
	//单位
	private String units;
	//询价单价
	private BigDecimal inquiryUnitPrice;
	//询价总价
	private BigDecimal inquiryPrice;
	//要求到位日期
	private Date requirementsDate;
	//采购单价
	private BigDecimal purchaseUnitPrice;
	//采购总价
	private BigDecimal purchasePrice;
	//采购回复交期
	private String procurementReturnPeriod;
	//资产分类
	private String assetClassification;
	//折旧年限(月)
	private String depreciation;
	//编码方式
	private String codingMode;
	//主资产编码
	private String mainAssetCode;
	//次级
	private String secondary;
	//总账科目
	private String ledgerAccount;
	//成本中心
	private String costCenter;
	// 成本中心编码
	private String costCenterCode;
	//申请人编码
	private String applyUserCode;
	//净残值率
	private String netSalvage;

	//币种
	private String currencyCode;
	//汇率
	private Double exchangeRate;
	//采购总价(本币)
	private Double purchasePriceLocal;
	//下回次级编码
	private String nextSecondaryCoding;
}
