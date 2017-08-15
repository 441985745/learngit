package com.opple.fa.sap.model;


import lombok.Data;

@Data
public class ItInputModel { // 是否必输

	private String anln1; // 主资产号 是
	private String anln2; // 资产次级编号 是
	private Integer count; //产品数量--model补充字段

}
