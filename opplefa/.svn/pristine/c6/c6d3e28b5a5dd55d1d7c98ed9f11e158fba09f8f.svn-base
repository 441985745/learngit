package com.opple.fa.purchase.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.opple.security.entity.FileUpload;

import lombok.Data;

@Data
public class PaymentOrder {
	//采购付款单号
	private String cdocument;
	//订单金额
	private BigDecimal orderMoney;
	//申请人
	private String applyUser;
	//申请人编码
	private String applyUserCode;
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
	//创建人编码
	private String createByCode;
	//更新人
	private String updateBy;
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
	//审批金额
	private BigDecimal iamoney;
	//创建时间
	private Date createDate;
	//更新时间
	private Date updateDate;
	//是否打印
	private String print;
	//采购订单编号(外键)
	private String purchaseOrderId;
	//扫描地点
	private String scanLocation;
	//税率
	private Double taxRate;
	//税码
	private String taxRateCode;
	//币种编码
	private String currencyCode;
	//汇率
	private BigDecimal exchangeRate;
	//供应商名称
	private String supplierName;
	//供应商编码
	private String supplierCode;
	//公司具体名称
	private String companySpecificName;
	//银行账号
	private String bankNumber;
	//联行号
	private String contactNumber;
	//开户银行编码
	private String bankCode;
	//开户银行名称
	private String bankName;
	//付款期数
	private int nper;
	//付款类型
	private String paymentType;
	//付款比例(%)
	private Double paymentRatio;
	//是否有发票
	private String invoice;
	//发票号
	private String invoiceNumber;
	//申请事由
	private String applyFor;
	//是否有效(N无效，Y有效)
	private String status;
	//影像状态
	private String portraitStatus;
	//凭证号(sap返回的)
	private String voucherId;
	//公司名称
	private String companyName;
	//公司编码
	private String companyCode;
	//办公地点
	private String officeLocation;
	//申请部门
	private String applyDepartment;
	//申请部门编码
	private String applyDepartmentCode;
	
	//成本中心
	private String costCenter;
	//成本中心编码
	private String costCenterCode;
	//利润中心
	private String profitCenter;
	
	
	//审批意见
	private String idea;
	//是否发送邮件提醒
	private String isMail;
	//是否发送短信提醒
	private String isPhoneMessage;
	//是否创建影像
	private String isPortrait;
	
	//当前登录人编码
	private String loginUserCode;
	//当前登录人姓名
	private String loginUserName;
	//当前登录人部门编码
	private String userDepCode;
	//sap采购订单号
	private String sapPurchaseOrderNumber;
	//是否手工预付款凭证
	private String isManualPrepaymentDocument;
	//是否手工验收款凭证
	private String isManualReceiptVoucher;
	//预付款凭证号
	private String prepaymentDocumentNumber;
	//应付款凭证号
	private String paymentVoucherNumber;
	//验收款凭证号
	private String receiptVoucherNumber;
	//是否传入SAP
	private String isComingSAP;
	//影像状态
	private String cbarStatus;
	//采购经理编码
	private String purchaseManagerCode;
	//采购经理名称
	private String purchaseManagerName;
	//验收凭证号(史登礼接口返回)
	private String cvoucheId;
	
	private String activityId;
	//是否最后一期付款
	private String isLastTerm;
	//是否最后一次付款
	private String isLastTime;
	private List<FileUpload> listFile;
	//发票号码（发票检验接口返回）
	private String invoiceNo;
	//验收款是否传递成功
	private String cvstatus;
}
