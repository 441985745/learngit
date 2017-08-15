package com.opple.fa.config.model;

/**
 * Created by Wangkaixuan on 2017/3/22.
 */
public enum DRAFT {
    YES("Y"),
    NO("N");

    private String draft;

    DRAFT(String draft) {
        this.draft = draft;
    }

    public String getDraft() {
        return draft;
    }
}
