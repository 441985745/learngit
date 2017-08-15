package com.opple.fa.report.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class FullProcessDetail {
    private String demandId;

    private Long demandDetailId;

    private String doApplyUser;

    private Date doApplyDate;

    private String budgetDepartmentName;

    private String projectCode;

    private String projectName;

    private String assetsName;

    private String specificationParameter;

    private String functions;

    private String units;

    private String useDescription;

    private String assetClassification;

    private String ledgerAccount;

    private BigDecimal depreciation;

    private String officeLocation;

    private String companyName;

    private Long demandCount;

    private BigDecimal usedSumMoney;

    private BigDecimal avaliableSumMoneyOld;

    private BigDecimal referencePrice;

    private String projectProposal;

    private Date requirementsDate;

    private String costCenter;
    private String costCenterCode;

    private String assetType;

    private String workShopType;

    private String demandCapprovalstate; //需求单审批状态

    private String applyOrderId;

    private Long applyDetailId;

    private String aoApplyUser;

    private Date aoApplyDate;

    private String mainAssetCode;

    private String secondary;

    private Long applyCount;

    private BigDecimal inquiryUnitPrice;

    private BigDecimal inquiryPrice;

    private String applyCapprovalstate; //

    private String purchaseId;

    private Long purchaseDetailId;

    private String purchaseOrderSapId;

    private String buyerName;

    private String supplierName;

    private Long purchaseCount;

    private String currencyCode;

    private BigDecimal exchangeRate;

    private BigDecimal purchaseUnitPrice;

    private BigDecimal purchasePrice;

    private Date pdRequirementsDate;

    private Long warrantyPeriod;

    private String purchaseCapprovalstate;

    private String receiveGoodsId;

    private String receiveGoodsDetailId;

    private Date receiveDate;

    private Long receiveCount;

    private String contractNo;

    private String checkId;

    private String caCapprovalstate;

    private Long checkCount;


    private String startDate;
    private String endDate;

}