package com.opple.fa.purchase.entity;

/**
 * Created by ykz on 17/1/6.
 */
public enum WARNSTATE {
    YES("on"),
    NO("off");

    private String warnstate;

    private WARNSTATE(String warnstate) {
        this.warnstate = warnstate;
    }

    public String getWarnstate() {
        return this.warnstate;
    }

}
