package com.opple.fa.sap.model;

import lombok.Data;

@Data
public class IpHistoryModel { // “是” 为必输
	private String mandt; // 客户端	
	private String oano; // oa unid 是
	private String sapno; // sap操作对象(销售订单/交货订单/物料主数据/客户主数据/供应商主
	private String mblnr; // 写入sap产生的凭证
	private String zdate; // 日期
	private String ztime; // 时间
	private String oaname; // oa操作人姓名 是
	private String oamodule; // oa模块名 是
	private String rfc; // sap函数名
	private String oaid; // oa操作人id 是
	private String sapid; // sap操作人id
	private String company; // 公司名称 是
	private String dept; // 业务部门描述 是
	private String remark; // 说明 是

}
