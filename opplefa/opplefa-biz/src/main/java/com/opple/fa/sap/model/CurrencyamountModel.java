package com.opple.fa.sap.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CurrencyamountModel { // 是否必输

	private Long itemnoAcc; // 会计凭证行项目编号 是
	private String currType; // 货币和评估视图
	private String currency; // 货币代码 是
	private String currencyIso; // iso代码货币
	private BigDecimal amtDoccur; // 凭证货币金额 是
	private BigDecimal exchRate; // 汇率
	private BigDecimal exchRateV; // 间接引用的汇率
	private BigDecimal amtBase; // 用凭证货币表示的税收基础金额
	private BigDecimal discBase; // 可用来计算现金折扣的符合条件金额(以凭证货币形式)
	private BigDecimal discAmt; // 以货币类型货币计的现金折扣金额

}
