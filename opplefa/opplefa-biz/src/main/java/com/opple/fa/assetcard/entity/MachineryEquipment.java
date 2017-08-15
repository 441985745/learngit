package com.opple.fa.assetcard.entity;

import java.util.Date;

import com.opple.workflow.entity.WorkFlowCommon;

import lombok.Data;

/**
 * 资产卡片机器设备
 */
@Data
public class MachineryEquipment extends WorkFlowCommon {

	private static final long serialVersionUID = 1L;
	private String createBy; // 创建人

	private Date createDate; // 创建时间

	private String updateBy; // 修改人

	private Date updateDate; // 修改时间

	private Integer deletedFlag; // 删除列标识（1删除0可用）

	private String shopType; // 车间类型
	private String machineCode; // 机器编码
	private String purchaseId;  // 采购订单：PURCHASE_ID
	private String checkAcceptId; // 验收单：CHECK_ACCEPT_ID
	private String putUnderManageCode; // 归口管理编码
	private String demandId; // 采购需求单据号
	private String applyId; // 采购申请单据号
	private String assetPerson;
	private String currentYearNoDepreciation; // 当年未计提折旧
	private String monthCountingDepreciation; // 月折旧额
	private String currentYearDepreciation; // 本年已计提累计折旧
	private String currentYearMonths; // 当年计提累计月数

}
