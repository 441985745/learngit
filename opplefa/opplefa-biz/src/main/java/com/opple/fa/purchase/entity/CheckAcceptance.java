package com.opple.fa.purchase.entity;

import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;

import java.util.Date;

/**
 * @author lipengju
 * @Description: 验收表（收货单保存时传递的数据，已收货未验收的）
 * @date 2016年11月3日 下午2:59:49
 */
@SuppressWarnings("serial")
@Data
public class CheckAcceptance extends WorkFlowCommon {

    private String checkAcceptId; // 验收单据号
    private String cdocument; // 验收单据号
    private String purchaseId; //
    private String purchaseDetailId; //
    private String applyDetailId; //
    private String applyDepartment; //
    private String applyDepartmentCode; //
    private String budgetDepartmentName; //
    private String budgetDepartmentCode; //
    private String officeLocation; //
    private String costCenter; //
    private String costCenterCode; //
    private String computerCode; //
    private String computerName; //


    private String approvalCode; // 批准文号
    private String assetsName; // 资产名称
    private String assetsType; // 资产名称
    private String brand; //
    private String buyerCode; // 采购员code
    private String checkActiveDate; // 核定有效年月
    private String buyerName; // 采购员
    private Long checkCount; // 验收数量（订单的行）
    private String checkCycle; // 检定周期
    private String contractNo; // 合同号
    private String depreciation; // 使用年限（申请行里，折旧年限(月)
    private Date enableDate; // 启用日期
    private Long goodsCount; // 收货数量
    private String ledgerAccount; // 总账科目
    private String recWarrantyPeriod; // 保固期
    private Date receiveDate; // 到货日期（实际收货日期）
    private Date checkDate;
    private String receiveGoodsId; //
    private String receiveGoodsDetailId; //
    private String storageLocation; // 存放位置
    private String supplierCode; // 供应商code
    private String supplierName; // 供应商
    private Long thisCheckCount; //
    private String units; // 单位
    private String useInfo; // 使用信息
    private String useStatus; // 使用状态
    private String companyCode; // 公司code
    private String companyName; // 公司

    private String isMail;
    private String activityId;
    private String idea; //
    private String checkDocStatus; // 单据状态（未关闭，已关闭）
    private String checkAcceptStatus; // 验收状态（Y：已经收货，并验收，N：已经
    private String draft; // 是否为草稿
    private String isEmail; //
    private String isPhoneMessage; //
    private String cnexthandlercode; //
    private String cnexthandlername; //
    private String capprovalstate; // 审批状态
    private String epMaterialdocu; // 物料凭证号(sap收货接口返回值
    private String epBelnr; // 会计凭证号(sap收货接口返回值)
    private String epMatdocumentyear; //物料凭证年度
    private String isSap; //是否同步sap
    private String lastCheckDate; //验收审批流最后一步同意时间
    private String attachDepartAdminName; //归口部门工程师
    private String attachDepartAdminCode; //归口部门工程师编码
    private String isManager; //是否归口部门工程师
    private String assetPerson; //当前验收人
    private String assetPersonCode; //当前验收人编码
    //归口部门经理
    private String attachDepartManagerName;
    //归口部门经理编码
    private String attachDepartManagerCode;

    private String applyCheckUserName; //验收单起草人（发起人）APPLY_CHECK_USER_NAME
    private String applyCheckUserCode; //APPLY_CHECK_USER_CODE

    private String isUpdateAssetsFrom; //IT资产是否全部更新成本中心成功
    private String orderType; //资产类型
    private String isprint; //是否已经打印

    private String dateStr;
}
