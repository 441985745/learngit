package com.opple.fa.purchase.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PurchaseApplyMapping {
	// id
	private Long id;
	// 资产名称
	private String assetsName;
	// 采购申请明细的ID
	private Long applyDetailId;
	// 采购订单的ID
	private String purchaseId;
	// 本次申请数量
	private int applyCount;
	// 创建人
	private String createBy;
	// 创建时间
	private Date createDate;
	// 更新人
	private String updateBy;
	// 更新时间
	private Date updateDate;
	// 采购申请明细合并号
	private String mergeNumber;

	// 采购需求明细的ID(以此行为单元)
	private Long demandDetailId;
	// applyDemandMapping的ID
	private Long applyDemandId;
	// 项目编码(预算编码)
	private String projectCode;
	// 收货数量
	private int goodsCount;
	// 未收货数量
	private int checkCount;
	//年度
	private String budgetYear;
	//采购单价(含税)
	private BigDecimal purchaseUnitPrice;
	//询价单价(含税)即申请单中询价单价
	private BigDecimal applyUnitPrice;
}
