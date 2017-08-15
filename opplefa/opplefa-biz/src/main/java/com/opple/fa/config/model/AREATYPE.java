package com.opple.fa.config.model;

/**
 * Created by Wangkaixuan on 2017/3/22.
 */
public enum AREATYPE {

    HOME("家居"),
    HOME_USE("家用"),
    COMMERCIAL("商用"),
    HOME_ZERO("0"),
    COMMERCIAL_ONE("1");

    private String areaType;

    AREATYPE(String areaType) {
        this.areaType = areaType;
    }

    public String getAreaType() {
        return areaType;
    }
}
