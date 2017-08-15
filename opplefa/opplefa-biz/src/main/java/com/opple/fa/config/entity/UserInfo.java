package com.opple.fa.config.entity;


import lombok.Data;

/**
 *<p>@Description:  用户登录可以查看所有公司，根据公司查看成本中心
 *<p>@author lipengju
 *<p>@date 2016年10月27日 下午3:59:53
 */
@Data
public class UserInfo {
//	公司编码
	private String ccompanycode;
//	公司名称
	private String ccompanyname;
//	成本中心编码
	private String ccostcentercode;
//	成本中心姓名
	private String ccostcentername;
//	科室编码 
	private String ckscode;
//	科室
	private String cksname;
//	汇率
	private String iexchangerate;
	
	
	
	
	
	
	
//	资产类型编码
	private String assetsTypeCode;
//	资产类型
	private String assetsType;
//  办公地点编码
	private String caddressCode;
//  办公地点
	private String caddressName;
//	采购员编码
	private String purchaseEngineerCode;	
//	采购员
	private String purchaseEngineer;
//	平台编码
	private String cplatformCode;
//	平台
	private String cplatform;
//	币种
	private String ccurrencycode;
//	币种编码
	private String ccurrencyname;

//	区域编码
	private String careaCode;
//	区域类型（0：家用区域，1：商用区域）
	private String careaType;
//	区域
	private String careaName;
//	车间类型编码
	private String departTypeCode;
//	车间类型
	private String departType;	
//	申请人编码
	private String cemployeecode;
//	申请人
	private String cemployeename;
//	申请人所在的部门编码
	private String coppdepcode;
//	申请人所在的部门
	private String coppdepname;
//	用户编码（权限）
	private String cusercode;
//	用户（权限）
	private String cusername;
//	员工类型
	private String cusertype;
//	部门编码(权限表)
	private String cbudgetdepcode;
//	部门（权限表）
	private String cbudgetdepname;
	
//  存放位置	
	private String cpsition;
//  存放位置编码	
	private String cpsitionCode; 	
//  办公地点	
	private String workAddress;
//  办公地点编码	
	private String workAddressCode;

//	部门编码
	private String cdepCode;
//	部门名称
	private String cdepName;
//  供应商编码
	private String csupplierCode;
//  当前登录人编码
	private String loginUserCode;
//  授权人编码
	private String cauthorizerCode;
	
//  内部订单号
	private String internalOrderCode;
}
