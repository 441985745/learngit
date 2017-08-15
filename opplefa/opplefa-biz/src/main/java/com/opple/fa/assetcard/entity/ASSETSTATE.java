package com.opple.fa.assetcard.entity;

public enum ASSETSTATE {

	FONTPREACCEPTANCE("预验收"), FONTACCEPTED("已验收"),

	// 预验收
	PREACCEPTANCE("0"),
	// 已验收
	ACCEPTED("1");
	private String assetState;

	ASSETSTATE(String assetState) {
		this.assetState = assetState;
	}

	public String getAssetState() {
		return assetState;
	}
}
