package com.opple.fa.config.service;

import java.util.List;


import com.opple.fa.config.entity.PurchaseOrderFile;
import com.opple.util.Pager;

/**
 * Created by lk on 10.31
 */
public interface PurchaseOrderFileService {
	/**
	 * 保存
	 * 
	 * @param purchaseOrderFile
	 * @return
	 */
	Long save(PurchaseOrderFile purchaseOrderFile);

	/**
	 * 删除
	 * 
	 * @param purchaseOrderFile
	 * @return
	 */
	int delete(String id);

	/**
	 * 分頁查詢--採購訂單--list
	 * 
	 * @param pager
	 * @param purchaseOrderModel
	 * @return
	 */
	Pager<PurchaseOrderFile> searchPurchaseOrderFiles(Pager<PurchaseOrderFile> pager,
			PurchaseOrderFile purchaseOrderFile);

	List<PurchaseOrderFile> searchPurchaseOrderFilesList(PurchaseOrderFile purchaseOrderFile);

	Integer deletePurchaseOrderFilesByIds(String ids);

}
