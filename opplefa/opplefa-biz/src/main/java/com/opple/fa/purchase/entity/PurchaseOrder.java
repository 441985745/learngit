package com.opple.fa.purchase.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 采购订单
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrder extends WorkFlowCommon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8664789200020617363L;
	//采购订单号
	private String cdocument;
	//采购订单号(SAP)
	private String purchaseOrderSapId;
	//订单金额
	private Double orderMoney;
	//申请人
	private String applyUser;
	//申请日期
	private Date applyDate;
	//审批状态
	private String capprovalstate;
	//订单状态
	private String orderStatus;
	//付款关闭状态
	private String isCloseForPayment;
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
	//申请人编码
	private String applyUserCode;
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

	//采购员名称
	private String buyerName;
	//采购员编码
	private String buyerCode;
	//币种
	private String currencyCode;
	//币种名称
	private String currencyName;
	//汇率
	private BigDecimal exchangeRate;
	//税率
	private Double taxRate;
	//预算所述部门编码
	private String budgetDepartmentCode;
	//预算所述部门名称
	private String budgetDepartmentName;
	//公司名称
	private String companyName;
	//公司编码
	private String companyCode;
	//平台
	private String platform;
	//资产类型
	private String assetType;
	//采购组
	private String purchasingGroup;
	//采购组织
	private String procurementOrganization;
	//订单类别
	private String orderType;
	//供应商名称
	private String supplierName;
	//供应商编码
	private String supplierCode;
	//付款方式名称
	private String paymentTypeName;
	//付款方式编码
	private String paymentTypeCode;
	//开户行名称
	private String bankName;
	//开户行编码
	private String bankCode;
	//银行账号
	private String bankNumber;
	//联行号
	private String contactNumber;
	//联系人
	private String contacts;
	//电话
	private String telephone;
	//成本中心
	private String costCenter;
	//成本中心编码
	private String costCenterCode;
	//工厂代码
	private String factoryCode;
	//公司地址
	private String companyAddress;
	//公司具体名称
	private String companySpecificName;
	//国家
	private String country;
	//城市
	private String city;
	//邮编
	private String zipCode;
	//采购员备注
	private String buyerRemark;
	//抬头文本说明
	private String headerTextDescription;
	//未收货数量
	private int unreceivedCount;
	
	//税码
	private String taxRateCode;
	//	是否打印
	private String print;
	//办公地点
	private String officeLocation;
	
	//是否传SAP
	private String issap;

	//采购凭证的项目编号
	private String purchaseOrderSapProjectId;
	//物料号
	private String matnr;
	//总账科目
	private String ledgerAccount;
	//订单单位
	private String meins;

	//采购经理
	private String purchasingManagerName;
	//采购经理编码
	private String purchasingManagerCode;
	//归口部门经理
	private String attachDepartManagerName;
	//归口部门经理编码
	private String attachDepartManagerCode;
	//归口部门管理员
	private String attachDepartAdminName;
	//归口部门管理员编码
	private String attachDepartAdminCode;
	//内部订单号
	private String cinternalordercode;
	
	//审批意见
	private String idea;
	//是否发送邮件提醒
	private String isMail;
	//是否发送短信提醒
	private String isPhoneMessage;
	//提交到
	private String activityId;
	//是否授权(查询时赋值登录人是否有授权)
	private String isAuthorize;
	//送货地址
	private String deliveryAddress;
	
	//资产管理员角色ID
	private String assetAdminId;
}
