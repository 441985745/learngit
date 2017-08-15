package com.opple.fa.purchase.entity;

import com.opple.workflow.entity.WorkFlowCommon;

import lombok.Data;

@Data
public class InvoiceInspectionDetail extends WorkFlowCommon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//删除列标识（0可用1删除）
	private String deletedFlag;
	//发票检验主表主键
	private String invoiceInspectionId;
	//主资产编码
	private String sapMainCode;
	//次级资产编码
	private String sapSecCode;
	//采购收货接口返回物料凭证号
	private String epMaterialDocument; 
	//采购收货接口返回会计凭证号
	private String eBelnr; 
	//采购收货接口返回物料凭证年度
	private String epMatDocumentYear;
	public String geteBelnr() {
		return eBelnr;
	}
	public void seteBelnr(String eBelnr) {
		this.eBelnr = eBelnr;
	} 
}
