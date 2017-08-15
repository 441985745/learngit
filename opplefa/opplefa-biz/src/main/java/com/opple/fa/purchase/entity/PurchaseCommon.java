package com.opple.fa.purchase.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

import com.opple.domain.BaseDomain;

/**
 *<p>@Description: 资产公共表（收货验收用）
 *<p>@author lipengju
 *<p>@date 2016年11月29日 下午6:13:44
 */

@Data
public class PurchaseCommon extends BaseDomain<Serializable> {
	
	private Long id;
	private String checkAcceptDetailId;
	private String receiveGoodsId; //
	private String receiveGoodsDetailId; //
	private String purchaseId; // 订单头编码
	private String purchaseDetailId; //
	private String applyDetailId;
	private String demandId; //
	private String demandDetailId; //
	private Long demandCount; //
	private String sapAssetsCode; //
	private String sapMainAssetCode; //
	private String sapSecAssetCode; //
	private String brand; //
	private String functions; //
	private String assetsName; //
	private String checkApplyCode; //
	private String checkApplyUser; //
	private String costCenterCode; // 成本中心
	private String costCenter; // 成本中心

	private String assetsType; //
	private String projectCode; //
	private String receiveStatus; //
	private String checkStatus; //
	private Long thisGoodsCountDemand;
	private Long goodsCountDemand;
	private Long checkCountDemand;
	private Long thisCheckCountDemand;
	private Long thisCheckCount;
	private Long thisGoodsCount;
	private Long checkCount; //
	private Long goodsCount; //
	private String cardId; //保存资产卡片Id
	
	
	private String epMaterialDocument; //采购收货接口返回物料凭证号
	private String eBelnr; //采购收货接口返回会计凭证号
	private String epMatDocumentYear; //采购收货接口返回物料凭证年度
	private String companyCode;
	private String checkAcceptId; //验收单主键
	private BigDecimal orderMoney;
	private BigDecimal orderNetMoney;
	private String purchaseOrderSapNo;	//采购订单sap 编号
	private Integer itemNo;	//行项目编号
	private String recWarrantyPeriod; //质保周期（月）
	private String storageLocation; //存放位置
	private String invoiceCheck; //是否开发票
	private String budgetDepartmentName; //需求部门

	private String budgetDepartmentCode;
	private String systeManagementCode; //上传图片的document SYSTE_MANAGEMENT_CODE

}
