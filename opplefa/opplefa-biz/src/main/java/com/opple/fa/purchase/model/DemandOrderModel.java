package com.opple.fa.purchase.model;

import java.util.Date;
import java.util.List;

import com.opple.fa.purchase.entity.DemandOrder;

import lombok.Data;

@Data
public class DemandOrderModel extends DemandOrder {
	
	private String update; //用来表示是从update进入的方法
	//申请日期开始
	private Date applyDateStart;
	//申请日期结束
	private Date applyDateEnd;
	
	//项目类别
	private List<String> projectTypes;
	
	//当前登录人编码
	private String loginUserCode;
	//当前登录人姓名
	private String loginUserName;
	//当前登录人部门编码
	private String userDepCode;
	//采购经理
	private String purchasingManagerName;
	//采购经理编码
	private String purchasingManagerCode;
	//归口部门总监编码
	private String attachDepartMajordomoCode;
	//归口部门总监
	private String attachDepartMajordomoName;
	//归口部门副总裁编码
	private String attachDepartVpresidentCode;
	//归口部门副总裁
	private String attachDepartVpresidentName;
	
	//本次是否修改调拨数量
	private String isAllocationCount;
	
}
