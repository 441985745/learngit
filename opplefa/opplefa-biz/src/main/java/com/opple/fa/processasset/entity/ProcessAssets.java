package com.opple.fa.processasset.entity;

import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.security.entity.FileUpload;
import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ProcessAssets extends WorkFlowCommon {

    /**
     * 流程类资产业务主表
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String createBy; // 创建人
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate; // 创建时间
    private String updateBy; // 更新人
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate; // 更新时间
    private String processAssetsCode;  // 流程类资产编码
    private String processAssetsName; // 流程类资产名称
    private String applyPeople; // 申请人
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applicationDate; // 申请日期
    private String homeCompany; // 资产责任人归属公司
    private String homeCompanyCode; //资产责任人归属公司编码
    private String assetOwner; // 资产责任人
    private String assetOwnerCode; //资产责任人编码
    private String costCenter; // 成本中心
    private String platform; // 平台
    private String storagePlace; // 存放位置
    private String assetType; // 资产类型
    private String officeLocation;  // 办公地点
    private String workshopType;  // 车间类型
    private List<GeneralAsset> generalAssetList; // 资产信息
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;  //临时数据不在数据库  投诉开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;   //临时数据不在数据库 投诉结束时间
    private String costcenterName; //申请人成本中心名称
    private String assetCostcenterCode; //组合资产责任人成本中心编码
    private String assetCostcenterName; //组合资产责任人成本中心名称
    private String assetDepartmentCode; //组合资产责任人部门编码
    private String assetDepartmentName; //组合资产责任人部门名称
    private Double originalValueSum; //原值合计
    private Double assetNetValueSum; //净值合计
    private String cause; //原因说明
    private List<FileUpload> listFile; //文件集合
    private String loginUserCode; //当前登录人编码
    private String loginUserName; //当前登录人名称
    private String assetCode; //资产编码

}
