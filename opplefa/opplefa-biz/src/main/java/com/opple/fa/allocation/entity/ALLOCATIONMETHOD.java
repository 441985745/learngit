package com.opple.fa.allocation.entity;

public enum ALLOCATIONMETHOD {
	INTERCOMPANY("公司间调拨"), INTERDEPARTMENTAL("部门间调拨"), BETWEENINDIVIDUALS(
			"个人间调拨");
	private String allocationMethod;

	ALLOCATIONMETHOD(String allocationMethod) {
		this.allocationMethod = allocationMethod;
	}
	
	public String getAllocationMethod() {
		return allocationMethod;
	}
}
