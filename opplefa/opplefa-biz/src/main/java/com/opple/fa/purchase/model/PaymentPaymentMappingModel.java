package com.opple.fa.purchase.model;

import java.math.BigDecimal;
import java.util.Date;

import com.opple.fa.purchase.entity.PaymentPaymentMapping;

import lombok.Data;

@Data
public class PaymentPaymentMappingModel extends PaymentPaymentMapping {
	//资产主编码
	private String mainAssetCode;
	//资产明细
	private String assetsName;
	//已付数量
	private int paidCount;
	//已付金额
	private BigDecimal paidPrice;
	//已冲减金额总和
	private BigDecimal verificationPrice;
	//下次付款时间
	private Date nextPayment;
}
