package com.opple.fa.purchase.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PaymentOrderDetail {
	//id
	private Long id;
	//付款单编号(外键)
	private String paymentOrderId;
	//采购订单明细ID
	private Long purchaseDetailId;
	//付款期数
	private int nper;
	//付款类型
	private String paymentType;
	//付款数量
	private int payCount;
	//付款金额
	private BigDecimal payPrice;
	//付款金额(本币)
	private BigDecimal payPriceLocal;
	//采购订单编号(外键)
	private String purchaseOrderId;
	//下次付款时间
	private Date nextPayment;
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
	//未付尾款金额
	private BigDecimal finalPrice;
	//资产主编码
	private String mainAssetCode;
	//已付预付款
	private BigDecimal advancePrice;
	//应付金额
	private BigDecimal payablePrice;
	//资产名称
	private String assetsName;
	//凭证号
	private String voucherId;
	//行项目编码
	private Long itemnoAcc;
	//总账科目
	private String ledgerAccount;
	//成本中心号
	private String costCenterCode;
	//成本中心名称
	private String costCenter;
	//是否最后一次付款
	private String isLastTime;
}
