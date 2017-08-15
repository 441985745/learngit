package com.opple.fa.sap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PayHeaderDocument extends SapDataEntity<PayHeaderDocument> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9021247702375452742L;
	/**
	 * 
	 */

	private Long id; // 主键
	private String objType; // 参考过程
	private String objKey; // 对象关键字
	private String objSys; // 源凭证的逻辑系统
	private String busAct; // 业务事务
	private String username; // 用户名 是 操作人名
	private String headerTxt; // 凭证抬头文本 抬头文本说明
	private String compCode; // 公司代码 是
	private String docDate; // 凭证中的凭证日期 是 传sap时间
	private String pstngDate; // 凭证中的记帐日期 是 传sap时间
	private String transDate; // 换算日期
	private int fiscYear; // 会计年度
	private int fisPeriod; // 会计期间
	private String docType; // 凭证类型 是 kz
	private String refDocNo; // 参考凭证编号 是 付款单据编号
	private String acDocNo; // 会计凭证号码 sap回传的
	private String objKeyR; // 取消: 对象码 (awref_rev and aworg_rev)
	private String reasonRev; // 冲销原因
	private String compoAcc; // acc 界面中的组件
	private String refDocNoLong; // 参考凭证号(对相关性参看长文本)
	private String accPrinciple; // 会计原则
	private String negPostng; // 标识: 反记帐

}
