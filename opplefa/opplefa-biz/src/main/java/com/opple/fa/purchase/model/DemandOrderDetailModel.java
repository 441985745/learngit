package com.opple.fa.purchase.model;

import java.math.BigDecimal;
import java.util.Date;

import com.opple.fa.purchase.entity.DemandOrderDetail;

import lombok.Data;

@Data
public class DemandOrderDetailModel extends DemandOrderDetail {
	

	//本次申请数量     为了做采购申请页面的资产明细
	private int thisApplyCount;
	// 合并号      为了做采购申请页面的资产明细
	private String mergeNumber;
	//申请日期开始
	private Date applyDateStart;
	//申请日期结束
	private Date applyDateEnd;

	//为了修改页面显示可用预算
	private BigDecimal avaliableSumMoney;
	
	//预算所属部门编码
	private String budgetDepartmentCode;
	//平台
	private String platform;
	//区域
	private String region;
	//公司编码
	private String companyCode;
	
	//修改页面查询多个Id
	private String ids;
}
