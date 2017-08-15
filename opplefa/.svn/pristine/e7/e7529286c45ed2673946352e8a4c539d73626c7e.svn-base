package com.opple.fa.sap.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BapiAccDocumentPost extends SapDataEntity<BapiAccDocumentPost> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8486226903818232755L;
	/**
	 * 
	 */

	private String cdocuments; // 核销流程单号
	private String epBelnr; // 凭证号
	
	private String busAct;	//业务事务
	private String userName;	//用户名
	private String companyCode;	//公司代码
	private String docDate;	//凭证中的凭证日期
	private String pstngDate;	//凭证中的记帐日期
	private String docType;	//凭证类型
	private String refDocNo;	//参考凭证编号
	private String accDocNo;	//会计凭证号码
	private Long itemNoAcc;	//会计凭证行项目编号
	private String vendorNo;	//供应商或债权人的帐号
	private String busArea;	//业务范围
	private String blineDate;	//到期日计算的基限日期
	private String allocNmbr;	//定位号--挂账人(申请人)--根据申请人的编码去供应商表查询员工的供应商编码
	private String spGlInd;	//特殊总分类帐标志--当预付场景(一条付款数据生成两行数据29和39)同时特别总账标识为A
	private String acpiUmskz;	//当预付场景(一条付款数据生成两行数据29和39)同时特别总账标识为A
	private String currency;	//货币代码
	private BigDecimal amtDoccur;	//凭证货币金额--行项目的金额
	private String structure;	//BAPI 表扩展的结构名称;--ZRSTRG	固定
	private String valuepart1;	//BAPI 扩展参数的数据部分
	private String actFlag;		//预付验收清账标记(0：预付；1：验收；2：清账）
	private String glAccount;	//总账科目编号
	private String costCenterCode;	//成本中心编码
	private String itemText;	//项目文本
	private String profitCenter;	//利润中心
	private BigDecimal exchRate;	//汇率
	private Integer fisYear;	//会计年度
	private Integer fisPeriod;	//会计期间（月份）
	
	private String billType; 
	private String doneFlag;	//是否成功（0-失败，1-成功）
	private Date startDate;	//开始时间
	private Date endDate;	//结束时间
	private String resultMessage;	//结果信息
	
}
