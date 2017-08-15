package com.opple.fa.allocation.entity;

import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.security.entity.FileUpload;
import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class AssetAllocation extends WorkFlowCommon {

	/**
	 * 
	 */
	private static final long serialVersionUID = -949767138642320610L;
	private Long id;
	private String createBy; // 创建人
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate; // 创建时间
	private String updateBy; // 更新人
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDate; // 更新时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date applicationDate; // 申请日期
	private String originalCompany; // 原归属公司
	private String originalPeople; // 原责任人
	private String postAllocationPeople; // 调拨后责任人
	private String departmentName; //
	private String postAllocationDepartment; // 调拨后责任人部门
	private String postAllocationCostCenter; // 调拨后成本中心
	private String postAllocationCompany; // 调拨后所属公司
	private String postAllocationStoragePlace; // 调拨后存放位置
	private String isExpenseAssets; // 是否费用化资产(0非费用化1费用化)
	private String assetType; // 资产类型
	private String workshopType; // 车间类型
	private String allocationMethod; // 调配方式
	private String officeLocation; // 办公地点
	private String platform; // 平台
	
	private BigDecimal allocationAssetsValueSum; // 调拨资产净值合计
	private String allocationReason; // 原因说明
	private BigDecimal originalValueSum; // 资产原值合计
	private List<AssetAllocationDetail> assetAllocationDetail; // 子表数据
	private List<GeneralAsset> generalAsset; // 卡片表数据
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start; // 临时数据不在数据库 投诉开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end; // 临时数据不在数据库 投诉结束时间
	private String generalAssetIds; // 资产信息的id
	private List<AssetAllocationDetail> listAssetAllocationDetail; // 子表 信息
	private String postCompanyCode; // 调拨后公司编码
	private String postCostcenterCode; // 调拨后成本中心编码
	private List<FileUpload> listFile;
	private String postDepartmentCode; // 调拨后部门编码
	private String postPeopleCode; // 调拨后责任人编码
	private String confirmStatus; // 确认状态
	private String putManageCode; // 归口经理编码
	private String loginUseCode; // 当前登录人编码
	private String loginUseName; // 当前登录人名称
	private String loginDepCode; // 当前登录人部门
	private String putManagePersonCode; // 归口管理员编码
	private String activityId; // 审批流参数
	private String confirmPerson; // 确认人
	private String isUpdateSap; // 是否更新了sap
	private String isGetSapCode; // 是否获得sapbianam
	private String assetAdminId; //资产管理员id
	private String isStamp; //是否打印
	private String oldCommitType; //订单原状态
	private String backOpinion; //退回意见


	private String assetCode; //资产编码
	private String assetName; //资产名称
	private String beforeAllocationCostCenter; //调拨前成本中心
	private String beforeAllocationCompany; //调拨前归属公司
}
