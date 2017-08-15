package com.opple.fa.sap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Extension2 extends SapDataEntity<Extension2> {

    /**
     *
     */
    private static final long serialVersionUID = 6286404388258171190L;
    private Long prId;
    private String structure;    //bapi 表扩展的结构名称	是	zrstrg
    private String valuepart1;    //bapi 扩展参数的数据部分	是	当预付场景(一条付款数据生成两行数据29和39)同时特别总账标识为a
    private String valuepart2;    //bapi 扩展参数的数据部分
    private String valuepart3;    //bapi 扩展参数的数据部分
    private String valuepart4;    //bapi 扩展参数的数据部分

}
