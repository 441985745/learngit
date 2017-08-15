package com.opple.fa.purchase.service;

import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.entity.ReceiveGoods;
import com.opple.fa.purchase.entity.ReceiveGoodsDetail;
import com.opple.fa.purchase.entity.ReceiveGoodsMapping;
import com.opple.fa.purchase.model.ReceiveGoodsModel;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

import java.util.List;
/**
 *<p>@Description: 收货单 
 *<p>@author lipengju
 *<p>@date 2016年11月25日 上午9:17:44
 */
public interface ReceiveGoodsMappingService {
	
	/**	分页查询
	 * @param pager
	 * @param receiveGoodsMapping
	 * @return
	 */
	Pager<ReceiveGoodsMapping> searchReceiveGoodsMapping(Pager<ReceiveGoodsMapping> pager, ReceiveGoodsMapping receiveGoodsMapping); 
	Pager<ReceiveGoodsMapping> searchAllReceiveGoodsMapping(Pager<ReceiveGoodsMapping> pager, ReceiveGoodsMapping receiveGoodsMapping);

	/**收货单头  查询 
	 * @param receiveGoodsModel
	 * @return
	 */
	ReceiveGoodsMapping searchPurchaseOrderMappingByPurchaseId(String purchaseId);
	
	/**收货单行 查询 
	 * @param receiveGoodsModel
	 * @return
	 */
	List<ReceiveGoodsMapping> searchPurchaseOrderDetailMappingByPurchaseId(String purchaseId);
	
	/**更改收货数量    需求明细   查询 
	 * @param receiveGoodsModel
	 * @return
	 */
	List<ReceiveGoodsMapping> searchPurchaseApplyMappingByPurchaseId(String purchaseId);
	

	/**查新公共表里的sap次级编码
	 * @param purchaseId
	 * @return
	 */
	List<ReceiveGoodsMapping> searchPurchaseCommonSapAssetsCodeByPurchaseId(String purchaseId);

	
	
	/**资产编码 sap次级拆分后的查询 
	 * @param receiveGoodsModel
	 * @return
	 */
	List<ReceiveGoodsModel> searchSapAssetsCodeByPurchaseId(String purchaseId);

	/**	 存放位置弹框 
	 * @param storageLocation
	 * @return
	 */
	List<ReceiveGoodsMapping> searchStorageLocationByOfficeLocation(ReceiveGoodsMapping receiveGoodsMapping);
	
	
	/**原来没绑定审批流的保存方法
	 * @param receiveGoods
	 * @param receiveGoodsDetails
	 * @param receiveGoodsMappings
	 * @param purchaseCommons
	 * @return
	 */
	Long  save(ReceiveGoods receiveGoods, List<ReceiveGoodsDetail> receiveGoodsDetails, List<ReceiveGoodsMapping> receiveGoodsMappings, List<PurchaseCommon> purchaseCommons);
	
    /**同意审批
     * @param receiveGoodsModel
     * @return
     */
	ExecuteResult<ReceiveGoods> agreeWorkFlow(ReceiveGoodsModel receiveGoodsModel);
	
	/**退回
	 * @param receiveGoodsModel
	 * @return
	 */
	ExecuteResult<ReceiveGoods> denyWorkFlow(ReceiveGoodsModel receiveGoodsModel);
	 /**
     * 弃审
     * @param demandOrder
     * @return
     */
    ExecuteResult<ReceiveGoods> dropWorkFlow(ReceiveGoodsModel receiveGoodsModel);

    Long  updateSaveReceiveGoods(ReceiveGoods receiveGoods,
    		List<ReceiveGoodsDetail> receiveGoodsDetails,
    		List<ReceiveGoodsMapping> receiveGoodsMappings,
    		List<PurchaseCommon> purchaseCommons);
	/**
	 * (收货单) 根据receiveGoodsId 删除TB_FA_PURCHASE_COMMON
	 */
	void delPurchaseCommonByReceiveGoodsId(String receiveGoodsId);

	List<ReceiveGoodsMapping> exportReceive(ReceiveGoodsMapping receiveGoodsMapping);

    Long updateSaveReceiveGoods1(ReceiveGoods receiveGoods, List<ReceiveGoodsDetail> receiveGoodsDetails, List<PurchaseCommon> purchaseCommons, List<ReceiveGoodsMapping> purchaseApplyMappingList);

    List<ReceiveGoodsMapping> exportReceive1(ReceiveGoodsMapping receiveGoodsMapping);

    Pager<ReceiveGoodsMapping> searchReceiveGoodsMappingForReceive(Pager<ReceiveGoodsMapping> pager, ReceiveGoodsMapping receiveGoodsMapping);

	Pager<ReceiveGoodsMapping> searchReceiveGoodsMappingForReceiveAll(Pager<ReceiveGoodsMapping> pager, ReceiveGoodsMapping receiveGoodsMapping);

    List<ReceiveGoodsMapping> exportReceiveHavingReceived(ReceiveGoodsMapping receiveGoodsMapping);

    List<ReceiveGoodsMapping> export1ReceiveHavingReceived(ReceiveGoodsMapping receiveGoodsMapping);
}
