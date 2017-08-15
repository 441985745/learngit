package com.opple.fa.sap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class Accountpayable extends SapDataEntity<Accountpayable> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6669045241730085288L;
	private Long prId;
	private Long itemnoAcc; // 会计凭证行项目编号 是
	private String vendorNo; // 供应商或债权人的帐号 是
	private String glAccount; // 总分类帐帐目
	private String refKey1; // 业务伙伴参考码
	private String refKey2; // 业务伙伴参考码
	private String refKey3; // 行项目的参考码
	private String compCode; // 公司代码 是
	private String busArea; // 业务范围 是
	private String pmnttrms; // 付款条件代码
	private String blineDate; // 到期日计算的基限日期 是
	private BigDecimal dsctDays1; // 第一次给予现金折扣的天数
	private BigDecimal dsctDays2; // 第二次给予现金折扣的天数
	private BigDecimal netterms; // 净条件截止期
	private BigDecimal dsctPct1; // 首次现金折扣的百分比
	private BigDecimal dsctPct2; // 二次现金折扣的百分比
	private String pymtMeth; // 收付方式
	private String pmtmthsupl; // 付款方式补充
	private String pmntBlock; // 收付冻结码
	private String scbankInd; // 州中央银行指标
	private String supcountry; // 供货国家
	private String supcountryIso; // 提供国家 iso 代码
	private String bllsrvInd; // 服务标志(国际收付)
	private String allocNmbr; // 定位号 是
	private String itemText; // 项目文本 是
	private String poSubNo; // por 订户号码
	private String poCheckdg; // por 检查数字
	private String poRefNo; // por 参考号码
	private String wTaxCode; // 预提税代码
	private String businessplace; // 存储
	private String sectioncode; // 区域编码
	private Integer instr1; // 指令码 1
	private Integer instr2; // 指令码 2
	private Integer instr3; // 指令码 3
	private Integer instr4; // 指令码 4
	private String branch; // 分支编号
	private String pymtCur; // 自动支付的货币
	private BigDecimal pymtAmt; // 以支付货币计的金额
	private String pymtCurIso; // iso代码货币
	private String spGlInd; // 特殊总分类帐标志
	private String taxCode; // 销售/购买税代码
	private String taxDate; // 同确定税率相关的日期
	private String taxjurcode; // 地区税务代码
	private String altPayee; // 代理收款人
	private String altPayeeBank; // 可替换付款人的银行类型
	private String partnerBk; // 对方开户行类型
	private String bankId; // 开户银行的简要键
	private String partnerGuid; // 公司界面: 商业伙伴 guid

}
