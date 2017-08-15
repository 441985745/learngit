package com.opple.fa.purchase.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;

@Data
public class ApplyOrder extends WorkFlowCommon {
	//采购申请订单号
	private String cdocument;
	//申请人
	private String applyUser;
	//申请日期
	private Date applyDate;
	//审批状态
	private String capprovalstate;
	//订单状态
	private String orderStatus;
	//草稿
	private String draft;
	//创建人
	private String createBy;
	//创建时间
	private Date createDate;
	//更新人
	private String updateBy;
	//更新时间
	private Date updateDate;
	
	//资产类型
	private String assetType;
	//申请人编码
	private String applyUserCode;
	//申请部门
	private String applyDepartment;
	//申请部门编码
	private String applyDepartmentCode;
	//公司编码
	private String companyCode;
	//办公地点
	private String officeLocation;
	//平台
	private String platform;
	//成本中心
	private String costCenter;
	//区域
	private String region;
	//是否费用化
	private String expensing;
	//采购员
	private String buyer;
	//采购员编码
	private String buyerCode;
	//创建人编码
	private String createByCode;
	//更新人编码
	private String updateByCode;

	//是否审批结束
	private String capprovalend;
	//审批人编码
	private String causercode;
	//审批人名称
	private String causername;
	//审批日期
	private Date dlastadate;
	//下一处理人编码
	private String cnexthandlercode;
	//下一处理人名称
	private String cnexthandlername;

	
	//币种
	private String currencyCode;
	//汇率
	private Double exchangeRate;
	//公司名称
	private String companyName;
	//成本中心编码
	private String costCenterCode;
	
	//是否房屋建筑
	private String building;
	//是否组装
	private String assemble;
	//车间类型
	private String workShopType;
	//预算所属部门
	private String budgetDepartmentName;
	//预算所属部门编码
	private String budgetDepartmentCode;
	//	是否打印
	private String print;
	//订单金额
	private Double orderMoney;
	//审批金额
	private BigDecimal iamoney;

	//是否传SAP
	private String issap;
	
	//归口部门经理
	private String attachDepartManagerName;
	//归口部门经理编码
	private String attachDepartManagerCode;
	//归口部门管理员
	private String attachDepartAdminName;
	//归口部门管理员编码
	private String attachDepartAdminCode;
	//采购经理
	private String purchasingManagerName;
	//采购经理编码
	private String purchasingManagerCode;
	
	//调用存储过程返回结果
	//private Integer result;
	//是否授权(查询时赋值登录人是否有授权)
	private String isAuthorize;
	
	//资产管理员角色ID
	private String assetAdminId;
}
