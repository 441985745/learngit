package com.opple.fa.config.entity;

import com.opple.domain.BaseDomain;
import lombok.Data;

@Data
public class AttchConfig extends BaseDomain<String> {
	//公司编码
	private String companyCode;
	private String companyName;
	//成本中心编码
	private String costcenterCode;
	private String costcenterName;
	//办公地点
	private String officeLocation;
	//平台
	private String platform;
	//资产类型
	private String assetType;
	//车间类型
	private String workShopType;
	//归口部门管理员编码
	private String attachDepartAdminCode;
	//归口部门管理员
	private String attachDepartAdminName;
	//归口部门经理编码
	private String attachDepartManagerCode;
	//归口部门经理
	private String attachDepartManagerName;
    //归口部门总监编码
    private String attachDepartMajordomoCode;
    //归口部门总监
    private String attachDepartMajordomoName;
    //归口部门副总裁编码
    private String attachDepartVpresidentCode;
    //归口部门副总裁
    private String attachDepartVpresidentName;
    //采购员编码1
    private String buyerCodea;
    //采购员名称1
    private String buyerNamea;
    //采购员编码2
    private String buyerCodeb;
    //采购员名称2
    private String buyerNameb;
    //采购员编码3
    private String buyerCodec;
    //采购员名称3
    private String buyerNamec;
    //采购员编码4
    private String buyerCoded;
    //采购员名称4
    private String buyerNamed;
    //采购员编码5
    private String buyerCodee;
    //采购员名称5
    private String buyerNamee;
    //采购员编码6
    private String buyerCodef;
    //采购员名称6
    private String buyerNamef;
    //采购员编码7
    private String buyerCodeg;
    //采购员名称7
    private String buyerNameg;
    //采购员编码8
    private String buyerCodeh;
    //采购员名称8
    private String buyerNameh;
    //采购经理编码
    private String purchasingManagerCode;
    //采购经理
    private String purchasingManagerName;
    //二级部门负责人编码
	private String twoDepartmentHeadCode;
    //二级部门负责人名称
	private String twoDepartmentHeadName;
	//状态
	private String status;
}
