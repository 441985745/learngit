package com.opple.fa.purchase.model;



import java.math.BigDecimal;
import java.util.Date;

import com.opple.fa.purchase.entity.PurchaseApplyMapping;

import lombok.Data;

@Data
public class PurchaseApplyMappingModel extends PurchaseApplyMapping {

	// 规格参数
	private String specificationParameter;
	// 功能
	private String functions;
	// 选配件
	private String optionalAccessories;
	// 验收标准
	private String acceptanceCriteria;
	// 申请部门
	private String budgetDepartmentName;
	// 申请部门编码
	private String budgetDepartmentCode;
	// 单位
	private String units;
	// 询价单价
	private BigDecimal inquiryUnitPrice;
	// 要求到位日期
	private Date requirementsDate;
	// 成本中心
	private String costCenter;
	// 成本中心编码
	private String costCenterCode;
	// 用途说明
	private String useDescription;
	// 需求单号
	private String demandOrderId;
}
