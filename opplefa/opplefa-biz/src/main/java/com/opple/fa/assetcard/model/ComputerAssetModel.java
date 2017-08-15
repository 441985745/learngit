package com.opple.fa.assetcard.model;



import lombok.Data;

@Data
public class ComputerAssetModel {
	private String id;


	
	private int deletedFlag; //删除列标识（0删除1可用）：DELETED_FLAG
	
	private String joinDomain; //加入域：JOIN_DOMAIN
	
	private String ipAddress; //IP地址：IP_ADDRESS
	
	private String description; //功能描述：DESCRIPTION
	
	private String operatingSystem; //操作系统：OPERATING_SYSTEM
	
	private String dataBase; //数据库：DATABASE
	
	private String utilitySystem; //应用系统：UTILITY_SYSTEM
	
	private String cpu; //CPU
	
	private String content; //内容：CONTENT
	
	private String hardpan; //硬盘：HARDPAN
	

	
	private String netSalvage; //净残值率：NET_SALVAGE
	
	private String netResidualValue; //净残值：NET_RESIDUAL_VALUE
	
	private String serialNumber; //序列号：SERIAL_NUMBER
	private String	purchaseId;  //采购订单：PURCHASE_ID 
	private String 	checkAcceptId; //验收单：CHECK_ACCEPT_ID
	private String internalMemory; //内存
	private String demandId; //采购需求单据号
	private String applyId; //采购申请单据号
	private String assetPerson;
	private String putUnderManageCode; //归口管理编码

	private String costCenterCode; //成本中心

}
