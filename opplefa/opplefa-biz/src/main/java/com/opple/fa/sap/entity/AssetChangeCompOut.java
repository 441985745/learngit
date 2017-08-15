package com.opple.fa.sap.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class AssetChangeCompOut extends SapDataEntity<AssetChangeCompOut> { /**
	 * 
	 */
	private static final long serialVersionUID = 7577118751887375970L;
/**
	 * 
	 */
	private Long fId;	//
	private String sapNo; //会计凭证号码
	private Integer year; //会计年度
	private String compCode; //公司代码
	private String parSapNo; //伙伴公司会计凭证号码
	private Integer parYear; //伙伴公司会计年度
	private String parCompCode; //伙伴公司公司代码
	
}
