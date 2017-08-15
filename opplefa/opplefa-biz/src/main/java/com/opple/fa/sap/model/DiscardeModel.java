package com.opple.fa.sap.model;


import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class DiscardeModel {

	private boolean retireType;	//报废类型 true-部分报废 ， false-全部报废
	private String userName; //用户名
	private String docType; //凭证类型
	private String dateCurrent;
	private Date docDate; //凭证中的凭证日期
	private Date pstngDate; //凭证中的记账日期
	private Date assetPriceDate; //资产价值日
	private Integer fisPeriod; //会计期间
	private String compCode; //公司代码
	private String assetMaino; //主资产号
	private String assetSubno; //资产次级编号
	private String assetTrtyp; //资产事务类型
	private String complRet; //是否全部报废标记
	private Integer quantity; //数量
	private BigDecimal percRate; //报废百分比: 完全报废就是100，不完全报废的话就不能填100;  最大长度为5,小数位不超过2 ；
	private BigDecimal amount; //报废金额 
	private BigDecimal revOnRet;	//资产销售中的收益
	private BigDecimal retCosts;	//资产报废成本
}
