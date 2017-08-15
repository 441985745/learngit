package com.opple.fa.sap.model;



import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItZmm0004Model { // 是否必输

	private Long poItem; // 采购凭证的项目编号 是
	private String shortText; // 短文本 是
	private String material; // 物料号 是
	private String plant; // 工厂 是
	private String stgeLoc; // 库存地点 是
	private String preqName; // 需求者/要求者名称 是
	private String matlGroup; // 物料组 是
	private BigDecimal quantity; // 采购订单数量 是
	private String poUnit; // 采购订单单位 是
	private String itemCat; // 采购凭证中的项目类别 是
	private String acctasscat; // 科目设置类型 是
	private String taxCode; // 销售/购买税代码 是
	private String deliveryDate; // 交货日期 是
	private String glAccount; // 总帐科目编号 是
	private String costcenter; // 成本中心 是
	private String assetNo; // 主资产号 是
	private String subNumber; // 资产次级编号 是
	private String condType; // 条件类型 是
	private String condUnit; // 条件单价 是
	private String condPUunt; // 条件定价单位 是
	private String itemText; // 行项目文本 是
	private BigDecimal condValue; // 定价比率 是
	private String orderid; // 订单号
	private String bednr; // 需求跟踪号
	private String profitCtr; // 利润中心
	private String purchaseId; //订单号
	private String purchaseDetailId; //订单行号

}
