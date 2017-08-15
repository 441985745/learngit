package com.opple.fa.allocation.entity;

public enum CONFIRMSTATUS {
    //等待确认
    WAITINGCONFIRMATION("0"),
    //确认中
    INCONFIRMATION("1"),
    //确认完成
    CONFIRMCOMPLETION("2"),
    //退回
    DENY("3");
    private String confirmStatus;

    CONFIRMSTATUS(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }
}
