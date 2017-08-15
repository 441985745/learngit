package com.opple.fa.purchase.model;

import java.util.List;

import com.opple.fa.purchase.entity.ReceiveGoods;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class ReceiveGoodsModel extends ReceiveGoods {

	private String purchaseId;
	private String purchaseDetailId;
	private String applyDetailId;
	private String demandId;
	private String demandDetailId;
	private Long demandCount;
	private String sapMainAssetCode;
	private String sapSecAssetCode;
	private String sapAssetsCode;
	private String brand;
	private String functions;
	private String assetsName;
	private String checkApplyCode;
	private String checkApplyUser;
	private String cdocument; // 收货单编码
	// 项目类别
	private List<String> projectTypes;
	// 当前登录人编码
	private String cuserCode;
	// 当前登录人姓名
	private String cuserName;
	// 当前登录人部门编码
	private String userDepCode;
	// 审批意见
	private String idea;
	// 是否发送邮件提醒
	// private String isMail;
	private String isEmail;
	// 是否发送短信提醒
	private String isPhoneMessage;
	private String budgetDepartmentName;
	private String budgetDepartmentCode;


}
