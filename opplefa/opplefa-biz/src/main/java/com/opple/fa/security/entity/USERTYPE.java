package com.opple.fa.security.entity;

/**
 * Created by Wangkaixuan on 2017/3/22.
 */
public enum USERTYPE {

    EMPLOYEE("员工");

    private String userType;

    USERTYPE(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }
}
