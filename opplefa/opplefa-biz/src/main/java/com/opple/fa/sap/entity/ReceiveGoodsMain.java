package com.opple.fa.sap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReceiveGoodsMain extends SapDataEntity<ReceiveGoodsMain> {

	private static final long serialVersionUID = -2395586403999615764L;

	private Long id; // 主键
	private String pstngDate; // 凭证中的记帐日期
	private String docDate; // 凭证中的凭证日期
	private String prUname; // 用户名
	private String headerTxt; // 凭证抬头文本
	private String refDocNo; // 外部物料单编号
	private String oano; // OA UNID
	private String mblnr; // 写入SAP产生的凭证
	private String zdate; // 日期
	private String ztime; // 时间
	private String oaname; // OA操作人姓名
	private String oamodule; // OA模块名
	private String rfc; // SAP函数名
	private String oaid; // OA操作人ID
	private String sapid; // SAP操作人ID
	private String company; // 公司名称
	private String dept; // 业务部门描述
	private String remark; // 说明
	private String epMaterialdocument; // 物料凭证号
	private String eBelnr; // 会计凭证号
	private String epMatdocumentyear;
	
	private String plant; // 工厂
	private String bstnr; // 采购订单凭证号
	private String epSubrc;	//结果
	private String ebelp;	//结果
	
}
