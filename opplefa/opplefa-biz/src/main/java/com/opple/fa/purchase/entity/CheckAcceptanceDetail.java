package com.opple.fa.purchase.entity;

import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;

import java.math.BigDecimal;

@SuppressWarnings("serial")
@Data
public class CheckAcceptanceDetail extends WorkFlowCommon {
	private Integer num; //同步sap为了计算回写金额
	private BigDecimal purchaseUnitRice;
	private String projectCode;
	private String budgetYear;
	private BigDecimal checkMoney;
	private Long id;
	private Long checkAcceptDetailId;
	private String receiveGoodsId; // 收货单号
	private String receiveGoodsDetailId; //
	private String purchaseId; //
	private String purchaseDetailId; //
	private String demandId; // 
	private String applyId; //
	private String demandDetailId; //
	private String applyDetailId; // 
	private String checkAcceptId; // 
	private String assetsName; // 
	private String assetsType; // 资产分类
	private String mmAssetsCode; //固定资产(字段编码)MM+sap主编码+sap次级编码
	private String sapAssetsCode; // SAP资产编码
	private String sapMainAssetCode; // SAP资产编码
	private String sapSecAssetCode; // SAP资产次级编码
	private String manageapplyDepartmentCode; // 归口管理部门编码
	private String serialNumber; // 序列号
	private String pasteAssetsTag; // 是否有粘贴资产标签
	private String assetsPhoto; // 资产照片
	private String otherCalibrationReport; //是否有第三方校验报告
	//资产卡片信息的明细id
	private String assetDetailId;
	//资产卡片信息主表的Id
	private String cardId;

	//计量器具子表字段
	private String calibrationDate;	//校准日期
	private String nextCalibrationDate;	//下次校准日期
	private String calibrationUnit;	//校准单位
	private String accuracyClass;	//精度等级
	private String manufactureFactory;	//生产厂家
	private String managementLevel;	//管理级别
	private String verificationCycle;	//检定周期
	private String isThirdReport;	//是否有第三方检验报告 0是 1否
	private String reportUpload;	//第三方检验报告上传
	private String epMaterialdocu; // 物料凭证号(sap收货接口返回值
	private String epBelnr; // 会计凭证号(sap收货接口返回值)
	private String epMatdocumentyear; //物料凭证年度
	private String isSap; //是否同步sap

	//IT资产子表字段
	private String putUnderManageCode;	//归口管理编码
//	private String serialNumber;	//序列号：SERIAL_NUMBER
	private String cpu;	//CPU
	private String internalMemory;	//内存：internalMemory
	private String hardpan;	//硬盘：HARDPAN
	private String ipAddress;	//IP地址：IP_ADDRESS
	private String joinDomain;	//加入域：JOIN_DOMAIN
	private String description;	//功能描述：DESCRIPTION
	private String utilitySystem;	//应用系统：UTILITY_SYSTEM
	private String dataBase;	//数据库：DATABASE
	private String operatingSystem;	//操作系统：OPERATING_SYSTEM
	private String content;

	//机器设备子表字段
	//private String machineCode; //机器编码
	//private String putUnderManageCode; //归口管理编码
	private String shopType; //车间类型

	//行政资产子表字段
	//private String serialNumber; //出厂编号/序列号
	//private String managementCode; //管理编码
	private String isBuildingContruction; //是否为房屋建筑
	private String recWarrantyPeriod; //质保周期
	private String storageLocation; //存放位置

	private String systeManagementCode; //上传图片主键

}
