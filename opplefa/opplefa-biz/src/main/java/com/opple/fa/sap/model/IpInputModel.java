package com.opple.fa.sap.model;

import java.util.Date;

import lombok.Data;

@Data
public class IpInputModel { // 是否必输

	private String assetclass; // 资产分类 
	private String companycode; // 公司代码 
	private String subnumber; // 资产次级编号 
	private String descript; // 资产描述 
	private String descript2; // 附加资产描述
	private String acctDetrm; // 科目定位码
	private String serialNo; // 序列号
	private String inventNo; // 库存号 
	private Long quantity; // 数量 
	private String baseUom; // 基本计量单位 
	private String history; // 资产被历史性管理
	private String mainDescript; // 资产主号说明
	private Date date; // 最后库存日
	private String note; // 补充库存说明
	private String includeInList; // 库存标记
	private String deactDate; // 不激活的日期
	private String busArea; // 业务范围
	private String costcenter; // 成本中心 
	private String location; // 资产地点
	private String room; // 房间
	private String evalgroup1; // 评估组1
	private String anln1;	//主资产号
	private String zFlag;	//操作方式-A 创建  B 扩展  C 查询
	private Date odepStartDate; //折旧开始计算日期
	private String area; //折旧范围

}
