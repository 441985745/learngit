package com.opple.fa.assetcard.entity;

import lombok.Data;

import com.opple.workflow.entity.WorkFlowCommon;

@Data
public class AdministrationAsset extends WorkFlowCommon {
	private static final long serialVersionUID = 1L;
	private Long id; // 主键
	private String serialNumber; // 出厂编号/序列号
	private String managementCode; // 管理编码
	private String isBuildingContruction; // 是否为房屋建筑
	private String deletedFlag; //删除标志
}
