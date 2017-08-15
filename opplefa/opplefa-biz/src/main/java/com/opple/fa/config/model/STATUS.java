package com.opple.fa.config.model;

/**
 * Created by Wangkaixuan on 2017/3/22.
 */
public enum STATUS {
    YES("Y"),
    NO("N"),
    YES_ZERO("0"),
    NO_ONE("1"),
    EFFECTIVE("有效"),
    YES_STATUS("已关闭"),
    NO_STATUS("未关闭"),
    INVALID("无效");



    private String status;

    STATUS(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
