package com.opple.fa.assetcard.entity;

public enum ISUSABLE {
	// 可用
	AVAILABLE("0"),
	// 不可用
	UNAVAILABLE("1");
	private String isUsable;

	ISUSABLE(String isUsable) {
		this.isUsable = isUsable;
	}

	public String isUsable() {
		return isUsable;
	}
}
