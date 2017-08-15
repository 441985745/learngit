package com.opple.fa.discardsell.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.opple.workflow.entity.WorkFlowCommon;

import lombok.Data;

@Data
public class DiscardSellDetail extends WorkFlowCommon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String createBy; // 创建人
	private Date createDate; // 创建时间
	private String updateBy; // 修改人
	private Date updateDate; // 修改时间
	private String deletedFlag; // 删除标识
	private long generalAssetId; // 资产信息表的id
	private long discardSellId; // 变卖报废表的id
	private String discardPercent; // 报废百分比
	private BigDecimal discardMoney; // 报废金额
	private String scrapCertificate; // 报废凭证
	private BigDecimal 	assetOriginalValue; //资产原值
	private BigDecimal assetNetValue; //资产净值
	private String companyName; //公司名称
	private String companyCode; //公司编码
	private String costCenterCode; //成本中心编码
	private String costCenterName; //成本中心名称
	private String assetCode; //资产编码
	private String assetName; //资产名称
	private String assetPerson; //资产责任人
	private String assetPersonCode; //资产责任人编码
	private String location; //存放位置
	private String assetType; //资产类型
	private Date depStartDate; //折旧开始日期
}
