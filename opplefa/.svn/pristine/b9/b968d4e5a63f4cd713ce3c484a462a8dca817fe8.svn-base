package com.opple.fa.purchase.service.impl;



import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.purchase.dao.PurchaseCommonDAO;
import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.service.PurchaseCommonService;

/** 
 *<p>@Description: 资产公共表 （收货   验收  用） 
 *<p>@author lipengju
 *<p>@date 2016年12月8日 上午10:27:51
 */
public class PurchaseCommonServiceImpl implements PurchaseCommonService {
	@Getter @Setter
	private PurchaseCommonDAO purchaseCommonDAO;
	
//	查询sap资产明细
	@Override
	public List<PurchaseCommon> searchPurchaseCommonByReceiveGoodsId(String receiveGoodsId) {
		return purchaseCommonDAO.searchPurchaseCommonByReceiveGoodsId(receiveGoodsId);
	}

	@Override
	public List<PurchaseCommon> getPurchaseCommonbyPurchaseId(
			PaymentOrder paymentOrder) {
		return purchaseCommonDAO.getPurchaseCommonbyPurchaseId(paymentOrder);
	}

	@Override
	public PurchaseCommon getPurchaseCommonOnly(String cardId) {
		return purchaseCommonDAO.getPurchaseCommonOnly(cardId);
	}

	@Override
	public PurchaseCommon getPurchaseCommonById(Long id) {
		return purchaseCommonDAO.getPurchaseCommonById(id);
	}

	@Override
	public void updatePurchaseCommon(PurchaseCommon purchaseCommon) {
		purchaseCommonDAO.updatePurchaseCommon(purchaseCommon);
		
	}

	@Override
	public List<PurchaseCommon> getPurchaseCommonByCheckAcceptanceDetailId(List<String> list) {
		return purchaseCommonDAO.getPurchaseCommonByCheckAcceptanceDetailId(list);
	}


}