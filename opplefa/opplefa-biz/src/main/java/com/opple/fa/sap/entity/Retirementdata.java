package com.opple.fa.sap.entity;


import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class Retirementdata extends SapDataEntity<Retirementdata> { /**
	 * 
	 */
	private static final long serialVersionUID = -3409814672685686300L;
	private Long fId;
	private String complRet; //是否全部报废标记
	private String currency; //货币
	private Integer quantity; //数量
	private BigDecimal percRate; //报废百分比: 完全报废就是100，不完全报废的话就不能填100;  最大长度为5,小数位不超过2 ；
	private BigDecimal amount; //报废金额 
	private BigDecimal revOnRet;	//资产销售中的收益
	private BigDecimal retCosts;	//资产报废成本
	private BigDecimal areaNbv;	//确定帐面净值的基本折旧范围 
	private boolean notAll;	//是否完全报废 是 -true
	

	
}
