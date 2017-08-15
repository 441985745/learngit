package com.opple.fa.purchase.entity;

import java.util.Date;

import lombok.Data;

import com.opple.workflow.entity.WorkFlowCommon;

@SuppressWarnings("serial")
@Data
public class ReceiveGoods extends WorkFlowCommon {
	// public class ReceiveGoods extends BaseDomain<Serializable> {
	private Long id;

	private String assetsType; // 资产类型
	private String buyerCode; // 采购员编码
	private String buyerName; // 采购员名称
	private String buyerRemark; // 采购员备注
	private String cuserCode; // 当前登录人编码
	private String cuserName; // 当前登录人姓名
	private String userDepCode; // 当前登录人部门编码
	private String companyName; // 公司名称
	private String companyCode; // 公司编码
	private String contractNo; // 合同号
	private String costCenterCode; // 成本中心
	private String costCenter; // 成本中心
	private String headerTextDescription; // 抬头文本说明
	private String platform; // 申请部门所属平台
	private String purchaseId; // 订单头ID
	private String supplierName; // 供应商名称
	private String supplierCode; // 供应商编码
	private String applyDepartment; //
	private String applyDepartmentCode; //
	private String budgetDepartmentName; //
	private String budgetDepartmentCode; //
	private String officeLocation; //
	private String capprovalstate; // 审批状态
	private String orderStatus;
	private String cdocument; // 收货单编码
	private String receiveGoodsId; // 收货单编码
	private String draft; // 草稿
	private String idea; //
	private String isEmail; //
	private String isPhoneMessage; //
	private String cnexthandlercode; //
	private String cnexthandlername; //
	private Date receiveDate;
	// 创建人
	private String createBy;
	// 创建时间
	private Date createDate;
	// 更新人
	private String updateBy;
	// 更新时间
	private Date updateDate;
	private String attachDepartAdminName; //归口部门管理员
	private String attachDepartAdminCode; //归口部门管理员编码

}
