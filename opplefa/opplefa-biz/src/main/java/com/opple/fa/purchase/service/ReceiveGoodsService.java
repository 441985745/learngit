package com.opple.fa.purchase.service;

import com.opple.fa.purchase.entity.ReceiveGoods;

import java.util.List;


public interface ReceiveGoodsService {


	/**生成收货单编码用
	 * @return
	 */
	Long searchNextSequence();
	
	/**查询收货单的头
	 * @param receiveGoodsId
	 * @return
	 */
	ReceiveGoods searchReceiveGoodsByReceiveGoodsId(String receiveGoodsId);

	/**保存收货单的头
	 * @param receiveGoodsId
	 * @return
	 */
	Long saveReceiveGoods(ReceiveGoods receiveGoods);
	
	/**删除收货单的头
	 * @param receiveGoodsId
	 */
	void delReceiveGoodsByReceiveGoodsId(String receiveGoodsId);
	
	ReceiveGoods getReceiveGoodsByDocument(String receiveGoodsId);


    void deleteReceiveGoods(List<String> list1);

    void updateCloseReceiveGoods(List<String> receiveGoodsId);

    List<ReceiveGoods> getUserByDepartList(String userCode, String userName, String budgetDepartmentCode, String searchName, String searchCode);

    List<ReceiveGoods> searchReceiveGoodsByPurchaseId(String purchaseId);

    String searchPurchaseOrderStatus(String purchaseId);
}
