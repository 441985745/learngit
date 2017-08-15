package com.opple.fa.purchase.dao;

import com.opple.fa.purchase.entity.PurchaseCommonLogger;

public interface PurchaseCommonLoggerDAO {
	/**
	 * 保存日志
	 * @param purchaseCommLogger
	 */
	void savePurchaseCommLogger(PurchaseCommonLogger purchaseCommLogger);
}
