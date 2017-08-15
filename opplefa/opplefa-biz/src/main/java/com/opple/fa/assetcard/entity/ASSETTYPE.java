package com.opple.fa.assetcard.entity;
/**
 * Created by phoenix on 12/12/2016.
 * 资产类型
 */
public enum ASSETTYPE {
	//IT资产电脑类
	ITASSET("0"),

	//行政资产
	ADMINISTRATIVE("1"),
	//计量器具
	MEASURINGINSTRUMENTS("2"),
	//机器设备
	MACHINERYEQUIPMENT("3"),
	
	FONTITASSET("IT资产"),
	FONTADMINISTRATIVE("行政资产"),
	FONTMEASURINGINSTRUMENTS("计量器具"),
	FONTMACHINERYEQUIPMENT("机器设备");
	private String assetType;
	ASSETTYPE(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetType() {
		return assetType;
	}
}
