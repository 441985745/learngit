package com.opple.fa.config.model;

/**
 * Created by Wangkaixuan on 2017/3/22.
 */
public enum ASSETBUDGETTYPE {
    ADD("新增"),
    ADDORREDUCTION("追加/调减"),
    ADJUST("项目间调整");

    private String assetBudgetType;

    ASSETBUDGETTYPE(String assetBudgetType) {
        this.assetBudgetType = assetBudgetType;
    }

   public String getAssetBudgetType() {
       return assetBudgetType;
   }
}
