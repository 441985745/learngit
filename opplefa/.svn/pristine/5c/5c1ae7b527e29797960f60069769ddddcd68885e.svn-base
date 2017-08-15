package com.opple.fa.config.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.config.entity.PurchaseOrderFile;
import com.opple.util.Pager;

/**
 * @Description:采购订单
 *
 */
public interface PurchaseOrderFileDAO {
	
	/**
	 * 保存
	 * 
	 * @param purchaseOrderFile
	 * @return
	 */
	Long save(@Param("purchaseOrderFile") PurchaseOrderFile purchaseOrderFile);
	/**
	 * 删除
	 * 
	 * @param purchaseOrderFile
	 * @return
	 */
	int delete(@Param("id") String id);


	/**
	 * 分頁查詢--採購訂單--list
	 * 
	 * @param pager
	 * @param purchaseOrderModel
	 * @return
	 */
	List<PurchaseOrderFile> searchPurchaseOrderFiles(@Param("pager") Pager pager,
			@Param("purchaseOrderFile") PurchaseOrderFile purchaseOrderFile);

	/**
	 * 分頁查詢--採購訂單--count
	 * 
	 * @param applyOrderModel
	 * @return
	 */
	Long searchPurchaseOrderFilesCount(@Param("purchaseOrderFile") PurchaseOrderFile purchaseOrderFile);
	
	/**
	 * 分頁查詢--採購訂單--list
	 * 
	 * @param pager
	 * @param purchaseOrderModel
	 * @return
	 */
	List<PurchaseOrderFile> searchPurchaseOrderFilesList(@Param("purchaseOrderFile") PurchaseOrderFile purchaseOrderFile);
	int deleteByIds(String id);
}
