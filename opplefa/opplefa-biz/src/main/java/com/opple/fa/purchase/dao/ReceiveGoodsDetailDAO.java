package com.opple.fa.purchase.dao;

import java.util.List;


import com.opple.fa.purchase.entity.ReceiveGoodsDetail;

public interface ReceiveGoodsDetailDAO {

	/**保存收货行
	 * @param receiveGoodsDetail
	 * @return
	 */
	Long saveReceiveGoodsDetail(ReceiveGoodsDetail receiveGoodsDetail);
	
	/**修改收货单的行
	 * @param receiveGoodsDetail
	 * @return
	 */
	Long updateReceiveGoodsDetail(ReceiveGoodsDetail receiveGoodsDetail);
	
	/**查询收货单的行  
	 * @param receiveGoodsId
	 * @return
	 */
	List<ReceiveGoodsDetail> searchReceiveGoodsDetailByReceiveGoodsId(String receiveGoodsId);

	void deleteReceiveGoodsDetail(List<Integer> list);

    void updateReceiveGoodsDetail1(ReceiveGoodsDetail receiveGoodsDetail);

    void saveDeleteBranch(List<ReceiveGoodsDetail> receiveGoodsDetailList);
}
