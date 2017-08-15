package com.opple.fa.assetcard.entity;

import com.opple.fa.allocation.entity.AssetAllocation;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.discardsell.entity.DiscardSell;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.security.entity.FileUpload;
import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class GeneralAsset extends WorkFlowCommon {
	/**
	 * 资产卡片主表 (包含行政资产和非电脑类以及各类的公共字段)
	 */
	private static final long serialVersionUID = 1L;

	private String createBy; // 创建人
	private String createByCode; // 创建人编码

	private Date createDate; // 创建时间

	private String updateBy; // 修改人

	private Date updateDate; // 修改时间

	private String assetCode; // 资产编码

	private String oldAssetCode; // 原资产编码

	private String purchaseOrderCode; // 申购单编号

	private String systeManagementCode; // 系统管理编码

	private String assetType; // 资产类型

	private String assetName; // 资产名称

	private String putUnderManageCode; // 归口管理编码

	private String isExpenseAssets; // 是否费用化资产

	private String assetPerson; // 管理责任人
	private String assetPersonCode; // 管理责任人编码

	private String specificationModel; // 规格型号

	private String persentDepartment; // 部门

	private String officeLocation; // 办公地点

	private String isDraft; // 是否为草稿

	private String useState; // 使用状态

	private String assetState; // 资产状态

	private String approveState; // 审批状态

	private String assetOriginalValue; // 资产原值

	private String assetNetValue; // 资产净值

	private Date postingDate; // 入账日期

	private String persentStorageLocation; // 现存放位置

	private Integer assetDetailId; // 资产明细ID

	private int deletedFlag; // 删除列标识（1删除0可用）

	private String periodOfDepreciation; // 折旧年限

	private String supplierName; // 供应商名称

	private String supplierCode; // 供应商编码

	private String manufacturer; // 制造商

	private String usePeople; // 使用人

	private String useDepartment; // 使用部门
	private String useDepartmentCode; // 使用部门的编码
	private String location; // 存放位置区域

	private String realQuantity; // 实际数量

	private String countedQuantity; // 盘点数量

	private ComputerAsset computerAsset = new ComputerAsset(); // It资产

	private MachineryEquipment machineryEquipment = new MachineryEquipment(); // 机械设备

	private MeasuringInstruments measuringInstruments = new MeasuringInstruments(); // 计量设备

	private AdministrationAsset administrationAsset = new AdministrationAsset(); // 行政资产

	private String remark; // 备注

	private String serialNumber; // 序列号

	private String costCenterCode; // 成本中心编码

	private List<AssetAllocation> assetAllocation; // 资产调拨明细表

	private List<DiscardSell> listDiscardSells; // 报废变卖的明细表

	private String isUpdateSap; // 是否更新了 sap 1为 是 0 为 否
	private String purchaseId; // 采购订单：PURCHASE_ID
	private String checkAcceptId; // 验收单：CHECK_ACCEPT_ID
	private String differentiatePurchase; // 区分是页面新增卡片还是收货后新增卡片

	private Date dateOfAcceptance; // 计划验收日期
	private String warrantyPeriod; // 质保周期
	private Date expirationDate; // 质保到期日
	private String supplierLinkman; // 供应闪联系人
	private String supplierPhone; // 供应商联系电话
	private Date capitalizationDate; // 资本化日期
	private String platform; // 平台
	private String document; // 流程单据号
	private String demandId; // 采购需求单据号
	private String applyId; // 采购申请单据号

	private Date purchaseArrivalDate; // 采购到货日期
	private Date actualDateAcceptance; // 实际验收日期
	private String assetPicId;
	private List<FileUpload> listFile;
	private PurchaseOrder purchaseOrder;
	private ApplyOrder applyOrder;
	private DemandOrder demandOrder;

	private String oldHomeCompany; // 原归属公司

	private String monthCountingDepreciation; // 月折旧额

	private String currentYearDepreciation; // 本年已计提累计折旧
	private String selectAccessories; // 选配件
	private String addDepreciation; // 累计折旧
	private String salvageValue; // 处理价值
	private String acceptanceCycle; // 验收周期
	private Date depreciationStartDate; // 折旧开始日期
	private String remarkOne; // 备注一
	private String remarkTwo; // 备注二
	private String remarkThree; // 备注三
	private String assetClassification; // 资产分类
	private BigDecimal discardMoney; // 报废金额
	private String discardPrecent; // 报废百分比
	private String unit; // 单位名称
	private String unitCode; // 单位编码
	private String newAssetCode; // 新资产编码
	private String scrapCertificate; // 报废凭证号
	private List<AttchConfig> listAttchConfig = new ArrayList<AttchConfig>();
	private String assetClassificationName; // 资产分类名称
	private String isAllocationOccupancy; // 是否被调拨占用
	private String isDiscardOccupancy; // 是否被报废变卖占用
	private String loginUseCode;
	private String assetAdminId; // 资产管理员id
	private String isAssembleOccupancy; // 是否被组合类资产占用
	private String ruleId; // 角色id

}
