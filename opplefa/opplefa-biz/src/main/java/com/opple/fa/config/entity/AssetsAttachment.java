package com.opple.fa.config.entity;

import java.util.Date;

import lombok.Data;

@Data
public class AssetsAttachment {
	//id
	private int id;
	//公司编码
	private String companyCode;
	//配置方式
	private String configMethod;
	//办公地点
	private String workAddress;
	//资产类型
	private String assetsType;
	//资产类型编码
	private String assetsTypeCode;
	//车间类型
	private String departType;
	//归口部门总监
	private int attachDepartDirector;
	//归口部门总监编码
	private String attachDepartDirectorCode;
	//归口资产管理员
	private String attachAssetsAdmin;
	//归口资产管理员编码
	private String attachAssetsAdminCode;
	//归口部门经理
	private String attachDepartManager;
	//归口部门经理编码
	private String attachDepartManagerCode;
	//归口管理副总裁
	private String attachManageVPresident;
	//归口管理副总裁编码
	private String attachManageVPresidentCode;
	//车间类型编码
	private String departTypeCode;
	//创建人
	private String createBy;
	//创建时间
	private Date createDate;
	//更新人
	private String updateBy;
	//更新时间
	private Date updateDate;
}
