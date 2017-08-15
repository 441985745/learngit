package com.opple.fa.purchase.service.impl;

import com.opple.fa.purchase.dao.PurchaseOrderDetailDAO;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.model.PurchaseOrderDetailModel;
import com.opple.fa.purchase.service.PurchaseOrderDetailService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PurchaseOrderDetailServiceImpl implements PurchaseOrderDetailService {
	
	@Getter
	@Setter
	private PurchaseOrderDetailDAO purchaseOrderDetailDAO;
	
	/*@Override
	public Pager<PurchaseOrderDetail> searchPurchaseOrderDetail(Pager<PurchaseOrderDetail> pager, PurchaseOrderDetailModel purchaseOrderDetailModel) {
		
		List<PurchaseOrderDetail> searchPurchaseOrderDetailList = purchaseOrderDetailDAO.searchPurchaseOrderDetail(pager, purchaseOrderDetailModel);
		Long searchPurchaseOrderDetailCount =	purchaseOrderDetailDAO.searchPurchaseOrderDetailCount(purchaseOrderDetailModel);
		
		
		return Pager.cloneFromPager(pager, searchPurchaseOrderDetailCount, searchPurchaseOrderDetailList);
	}
*/
	/*@Override
	public List<PurchaseOrderDetailModel> searchPurchaseOrderDetailList(
			PurchaseOrderDetailModel purchaseOrderDetail) {
		List<PurchaseOrderDetailModel> orderDetailList = purchaseOrderDetailDAO.searchPurchaseOrderDetailList(purchaseOrderDetail);
		return orderDetailList;
	}

	@Override
	public PurchaseOrderDetailModel get(PurchaseOrderDetailModel purchaseOrderDetailModel) {
		return purchaseOrderDetailDAO.get(purchaseOrderDetailModel);
	}
*/
	@Override
	public List<PurchaseOrderDetailModel> searchPurchaseOrderDetailByOrderId(PurchaseOrder purchaseOrder) {
		return purchaseOrderDetailDAO.searchPurchaseOrderDetailByOrderId(purchaseOrder);
	}

	@Override
	public List<PurchaseOrderDetail> searchPurchaseOrderDetailByDocument(PurchaseOrder purchaseOrder) {
		return purchaseOrderDetailDAO.searchPurchaseOrderDetailByDocument(purchaseOrder);
	}

	@Override
	public void updateCode(List<PurchaseOrderDetailModel> purchaseOrderDetailModel) {

		for (PurchaseOrderDetailModel purchaseOrderDetail: purchaseOrderDetailModel) {
			purchaseOrderDetailDAO.updateCode(purchaseOrderDetail);
		}

	}

	@Override
	public PurchaseOrderDetail searchPurchaseOrderDetailById(String purchaseDetailId) {
		return purchaseOrderDetailDAO.searchPurchaseOrderDetailById(purchaseDetailId);
	}

}
