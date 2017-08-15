package com.opple.fa.assetcard.entity;

public enum USESTATE {
	// 在用
	INUSE("0"), FONTINUSE("在用"),
	// 闲置中
	IDLEIN("1"), FONTIDLEIN("闲置中"),
	// 大修理中
	MAJORREPAIR("2"), FONTMAJORREPAIR("大修理中"),
	// 改造中
	INTRANSFORMATION("3"), FONTINTRANSFORMATION("改造中"),
	// 报废申请中
	DISCARDAPPLY("4"), FONTDISCARDAPPLY("报废申请中"),
	// 已报废
	SCRAPPED("5"), FONTSCRAPPED("已报废"),
	// 变卖申请中
	SELLAPPLY("6"), FONTSELLAPPLY("变卖申请中"),
	// 已变卖
	ALREADYSOLD("7"), FONTALREADYSOLD("已变卖");
	private String useState;

	USESTATE(String useState) {
		this.useState = useState;
	}

	public String getUseState() {
		return useState;
	}
}
