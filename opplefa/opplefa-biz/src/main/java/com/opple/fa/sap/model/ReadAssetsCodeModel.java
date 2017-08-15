package com.opple.fa.sap.model;

import java.util.Date;

import lombok.Data;

@Data
public class ReadAssetsCodeModel { // 是否必填
	//!为必填
	private String[] parameter;	//应应用选择条件的参数 默认GENERALDATA 可不填
	private String[] field;		//必填！应应用选择条件的字段 可选字段ASSET或SUBNUMBER等
	private String[] sign;	//~默认I,为范围表包括/排除标准 SIGN（I包括，E排除）
	private String companyCode;	//必填!公司代码
	private Date evaluationDate; //~! 默认当前日期评估日期-传当前日期
	private String depreciationArea;  //~默认：00折旧范围(当前默认：00)
	private String maxEntries;	//~默认10，命中的最大行数  最大要取多少条，为几就查几条
	private String[] fieldValueLow; //必填！区间起始值  是
	private String[] fieldValueHigh; //~！下面的option为BT时要填 区间结束值
	private String[] option;	//必填！(BT是区间 ，后面需要high,EQ是等于，EQ时后面不需要high值) 是
	private Integer quantity; //产品数量--model补充字段
	private String masterAssetCode; //资产主编码
	private String secondaryAssetCode; //次级资产编码
	private String accountYear; //~默认当前年份，会计年度
	private String monthly; //~默认当前月份，折旧计算期（获取月份格式为001-012）

}
