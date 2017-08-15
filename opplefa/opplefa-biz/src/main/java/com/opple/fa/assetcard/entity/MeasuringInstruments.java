package com.opple.fa.assetcard.entity;

import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;

import java.util.Date;

/**
 * 资产卡片(计量器具)
 */
@Data
public class MeasuringInstruments extends WorkFlowCommon {


    private static final long serialVersionUID = 1L;

    private String createBy; //创建人

    private Date createDate; //创建时间

    private String updateBy; //修改人

    private Date updateDate; //修改时间

    private Integer deletedFlag; //删除列标识（1删除0可用）

    private String managementCode; //管理编号

    private String facrotyNumber; //出厂编号

    private String managementLevel; //管理级别

    private Date enableDate; //启用时间

    private Date inspectionDate; //验收时间

    private String warrantyPeriod; //保修时间

    private String verificationCycle; //检定周期

    private String contactNumaer; //联系电话

    private Date approvedEffectiveDate; //核定有效年月
    private String purchaseId;  //采购订单：PURCHASE_ID
    private String checkAcceptId; //验收单：CHECK_ACCEPT_ID
    private String assetNetValue; //资产净值
    private String assetOriginalValue; //
    private String companyName;  //公司名称
    private Date actualDateAcceptance; //实际验收日期
    private String periodOfDepreciation; //折旧年限
    private String isThirdReport; //是否有第三方检验报告
    private String reportUpload; //第三方检验报告上传
    private String location; //位置区域
    private String officeLocation;
    private String persentDepartment; //现部门和成本中心
    private String putUnderManageCode;
    private String remark;
    private String serialNumber;
    private String useDepartment;
    private String demandId; //采购需求单据号
    private String applyId; //采购申请单据号
    private String assetPerson;
    private String currentYearNoDepreciation; //当年未计提折旧
    private String monthCountingDepreciation; //月折旧额
    private String currentYearDepreciation; //本年已计提累计折旧
    private String currentYearMonths; //当年计提累计月数
    private Date calibrationDate; //校准日期
    private Date nextCalibrationDate; //下次校准日期
    private String calibrationUnit; //校准单位
    private String accuracyClass; //精度等级
    private String manufactureFactory; //生产厂家

}
