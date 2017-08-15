package com.opple.fa.allocation.entity;

public enum ISCONFIRM {
	// 已确认
	CONFIRMED("0"),
	// 未确认
	UNCONFIRMED("1");
	private String isConfirm;

	ISCONFIRM(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String isConfirm() {
		return isConfirm;
	}
}
