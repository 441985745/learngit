package com.opple.fa.assetcard.entity;

public enum ISEXPENSEASSETS {
	// 是否为一次性费用化资产
	FONTYES("是"), FONTNO("否"), YES("1"), NO("0");
	private String isExpenseassets;

	ISEXPENSEASSETS(String isExpenseassets) {
		this.isExpenseassets = isExpenseassets;
	}

	public String getIsExpenseassets() {
		return isExpenseassets;
	}
}
