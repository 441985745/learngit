package com.opple.fa.sap.model;

import com.opple.fa.sap.entity.SapDataEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItDataModel extends SapDataEntity<ItDataModel> { // 是否必输

    /**
     *
     */
    private static final long serialVersionUID = 8075033135788218142L;
    private String ebeln; // 采购凭证号
    private String ebelp; // 采购凭证的项目编号
    private String matnr; // 物料号
    private String mjahr; // 物料凭证年度
    private String mblnr; // 物料凭证编号
    private BigDecimal erfmg; // 条目单位的数量
    private BigDecimal netwr; // 凭证货币计量的净价值-开票总金额
    private String mwskz; // 销售/购买税代码
    private BigDecimal netwj; // 凭证货币计量的净价值-开票净价
    private BigDecimal netwz; // 凭证货币计量的净价值-开票税额
    private String anln1; // 主资产编号
    private String anln2; // 次级资产编号
    private BigDecimal condPUnt;    //条目定价单位
    private String ecBelnr;    //发票凭证
    private String ebelnr;    //会计凭证号码
    private String ecMessage;    //返回消息文本
    private String ecSubrc;    // 返回消息类型：S（成功）E(失败)
    private String currency;    // 货币代码
    /*private String blart; // 凭证类型
	private String xblnr; // 参考凭证号
	private String sgtxt; // 项目文本
*/
}
