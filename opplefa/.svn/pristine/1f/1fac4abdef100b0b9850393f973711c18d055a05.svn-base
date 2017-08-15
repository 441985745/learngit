package com.opple.fa.purchase.service;

import java.util.List;

import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PurchaseCommon;


public interface PurchaseCommonService {
	
	/**查询sap资产明细
	 * @param recieiveGoodsId
	 * @return
	 */
	List<PurchaseCommon> searchPurchaseCommonByReceiveGoodsId(String receiveGoodsId);
	/**
	 * 根据采购订单查询已验收未开发票的明细信息
	 * @param generalAsset
	 */
    List<PurchaseCommon> getPurchaseCommonbyPurchaseId(PaymentOrder paymentOrder);
	/**
	 * 根据cardId查询purchase Common
	 * @param cardId
	 * @return
	 */
    PurchaseCommon getPurchaseCommonOnly(String cardId);
    /**
	 * 根据id查询purchase common
	 * @param cardId
	 * @return
	 */
	PurchaseCommon getPurchaseCommonById(Long id);
	/**
	 * 修改发票检验标识列
	 * @param cardId
	 * @return
	 */
    void updatePurchaseCommon(PurchaseCommon purchaseCommon);

    List<PurchaseCommon> getPurchaseCommonByCheckAcceptanceDetailId(List<String> list);
}
