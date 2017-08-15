package com.opple.fa.purchase.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.InvoiceInspection;

public interface InvoiceInspectionDAO {

	/**
	 * 保存发票检验信息
	 * @param invoiceInspection
	 * @return
	 */
	void saveInvoiceInspection(InvoiceInspection invoiceInspection);
	/**
	 * 根据付款单号查询单条发票校验信息
	 * @param invoiceInspection
	 * @return InvoiceInspection
	 */
	InvoiceInspection getInvoiceInspection(@Param("invoiceInspection")InvoiceInspection invoiceInspection);
}
