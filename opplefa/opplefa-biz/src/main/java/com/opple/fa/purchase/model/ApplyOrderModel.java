package com.opple.fa.purchase.model;

import java.util.Date;

import com.opple.fa.purchase.entity.ApplyOrder;

import lombok.Data;

@Data
public class ApplyOrderModel extends ApplyOrder {

	//需求单号
	private String demandId;
	//资产名称
	private String assetsName;
	//申请日期开始
	private Date applyDateStart;
	//申请日期结束
	private Date applyDateEnd;

	//修改页面查询多个Id
	private String ids;

	//当前登录人编码
	private String loginUserCode;
	//当前登录人姓名
	private String loginUserName;
	//当前登录人部门编码
	private String userDepCode;

	//审批意见
	private String idea;
	//是否发送邮件提醒
	private String isMail;
	//是否发送短信提醒
	private String isPhoneMessage;
	//提交到
	private String activityId;
}
