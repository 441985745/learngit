package com.opple.fa.sap.model;


import lombok.Data;

@Data
public class IpPoheader1Model { // 是否必输

	private String compCode; // 公司代码 是
	private String docType; // 采购凭证类型 是
	private String vendor; // 供应商帐户号 是
	private String purchOrg; // 采购组织 是
	private String purGroup; // 采购组 是
	private String pmnttrms; // 收付条件代码 是
	private String headrText; // 表头文本 是
	private String name1; // 名称 1 是
	private String streetLng; // 街道 是
	private String houseNo; // 门牌号
	private String postlCod1; // 城市邮政编码
	private String city; // 城市
	private String country; // 国家代码 是
	private String tel1Numbr; // 第一个电话号码: 拨号 + 编号
	private String tel1Ext; // 第一个电话号码:分机号
	private String telephone; // 供应商电话号码
	private String salesPers; // 供应商办公室的责任销售员
	private String currency; // 货币代码 是
	private String unsez; // 我方参考 是
	private String collectNo; // 汇总号

}
