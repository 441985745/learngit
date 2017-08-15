package com.opple.fa.assetcheck.entity;

import com.opple.fa.assetcard.entity.GeneralAsset;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class AssetCheckBusiness {
   //主键
   private Long id;
   //创建人
   private String createBy;
   //创建时间
   private Date createDate;
   //更新人
   private String updateBy;
   //更新时间
   private Date updateDate;
   //删除列标识
   private String deletedFlag;
   //单据号
   private String documentNumber;
   //盘点年度
   private String inventoryYear;
   //盘点月
   private String inventoryMonth;
   //盘点人
   private String inventoryPeople;
   //盘点时间
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date inventoryDate;
   //申请人
   private String applyPeople;
   //资产类型
   private String assetType;
   //盘点部门
   private String inventoryDepartment;
   //平台
   private String platform;
   //成本中心
   private String costCenter;
   //盘点申请日期
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date inventoryApplyDate;
   //盘点状态
   private String inventoryStatus;
   //盘点完结日期
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date inventoryEndDate;
   //申请原因
   private String applyReason;
   //草稿状态(0为草稿1不是草稿)
   private String isDraft;
   //审批状态
   private String approveState;
   //盘点层级
   private String inventoryLevels;
   //盘点公司
   private String inventoryCompany;
   
   private List<GeneralAsset> generalAssetList;
}
