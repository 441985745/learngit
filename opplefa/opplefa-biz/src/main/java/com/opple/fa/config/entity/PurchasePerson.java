package com.opple.fa.config.entity;

import java.util.Date;

import lombok.Data;

@Data
public class PurchasePerson {
	//id
	private int id;
	//公司编码
	private String companyCode;
	//办公地点
	private String workAddress;
	//采购总监编码
	private String purchaseDirector;
	//采购总监
	private String purchaseDirectorCode;
	//采购经理
	private String purchaseManager;
	//采购经理编码
	private String purchaseManagerCode;
	//采购工程师
	private String purchaseEngineer;
	//采购工程师编码
	private String purchaseEngineerCode;
	//创建人
	private String createBy;
	//创建时间
	private Date createDate;
	//更新人
	private String updateBy;
	//更新时间
	private Date updateDate;
}
