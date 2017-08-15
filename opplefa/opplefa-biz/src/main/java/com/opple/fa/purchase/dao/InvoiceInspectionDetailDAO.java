package com.opple.fa.purchase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.InvoiceInspection;
import com.opple.fa.purchase.entity.InvoiceInspectionDetail;

public interface InvoiceInspectionDetailDAO {

	/**
	 * 保存发票检验明细信息
	 * @param invoiceInspection
	 * @return
	 */
	void saveInvoiceInspectionDetail(InvoiceInspectionDetail invoiceInspectionDetail);
	/**
	 * 根据发票检验主表查询发票校验明细信息
	 * @param invoiceInspection
	 * @return InvoiceInspection
	 */
	List<InvoiceInspectionDetail> searchInvoiceInspectionDetail(@Param("invoiceInspection")InvoiceInspection invoiceInspection);
}
