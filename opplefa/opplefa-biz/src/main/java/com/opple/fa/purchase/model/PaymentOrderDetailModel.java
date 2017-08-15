package com.opple.fa.purchase.model;

import java.math.BigDecimal;

import com.opple.fa.purchase.entity.PaymentOrderDetail;

import lombok.Data;

@Data
public class PaymentOrderDetailModel extends PaymentOrderDetail {
	//付款选择时 所需的字段
	
	//订单数量
	private int applyCount;
	//订单单价(含税)
	private BigDecimal purchaseUnitPrice;
	//订单总价(含税)
	private BigDecimal purchasePrice;
	//已付总金额
	private BigDecimal paidMoney;
	//已付数量
	private int paidCount;
	//已验收数量
	private int checkCount;
	//上次付款数量总和
	private int lastCount;
	//已冲减数量总和
	private int verificationCount;
	//已冲减金额总和
	private int verificationPrice;
	
	//采购订单明细 ID  多个
	private String ids;
}
