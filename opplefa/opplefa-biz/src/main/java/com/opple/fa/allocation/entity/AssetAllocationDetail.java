package com.opple.fa.allocation.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

import com.opple.workflow.entity.WorkFlowCommon;

@Data
public class AssetAllocationDetail extends WorkFlowCommon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8317592224281826655L;
	private String createBy; // 创建人
	private Date createDate; // 创建时间
	private String updateBy; // 更新人
	private Date updateDate; // 更新时间
	private String deletedFlag; // 删除列标识(0已删除1可用)
	private Integer assetAllocationId; // 资产调拨业务主表ID
	private Integer generalAssetId; /* 资产信息表ID：GENERAL_ASSET_ID */
	private List<AssetAllocation> assetAllocation; // 主表数据
	private String assetCode; // 资产编码
	private String assetName; // 资产名称
	private String putUnderManageCode; // 归口管理编码
	private String assetPerson; // 现责任人
	private String companyName; // 归属公司
	private String specificationModel; // 品牌/规格/型号
	private String assetOriginalValue; // 原值
	private String assetNetValue; // 净值
	private String assetState; // 资产状态
	private String useState; // 使用状态
	private String persentDepartment; // 成本中心
	private String location; // 存放位置
	private String newAssetCode; // 新资产编码
	private String oldAccountancyVouche; // 原公司会计凭证号码
	private String nowAccountancyVoucher; // 现公司会计凭证号码
	private String oldAccountYear; // 原公司会计年度
	private String nowAccountYear; // 现公司会计年度
	private String assetType; // 资产类型
	private String companyCode;
	private String costCenterCode;
}
