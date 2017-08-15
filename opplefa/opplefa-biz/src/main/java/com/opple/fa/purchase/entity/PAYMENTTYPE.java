package com.opple.fa.purchase.entity;

public enum PAYMENTTYPE {
	ADVANCE("预付款"),
    CHECK("验收款"),
    FINAL("尾款");

    private String paymentType;

    PAYMENTTYPE(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

}
