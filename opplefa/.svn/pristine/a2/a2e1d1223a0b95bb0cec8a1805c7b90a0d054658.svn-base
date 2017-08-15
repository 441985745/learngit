package com.opple.fa.purchase.dao;

import java.util.List;

import com.opple.fa.assetcard.entity.GeneralAsset;

import com.opple.fa.purchase.entity.ReceiveGoodsMapping;
import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PurchaseCommon;

/**
 *<p>@Description: 
 *<p>@author lipengju
 *<p>@date 2016年12月19日 上午9:26:57
 */
/**
 *<p>@Description: 
 *<p>@author lipengju
 *<p>@date 2017年1月4日 下午7:34:37
 */
public interface PurchaseCommonDAO {
	Long searchPCommonCountByApplyDetailId(String demandDetailId);
	
	/**(收货单)保存sap资产公共表
	 * @param purchaseCommon
	 * @return
	 */
	Long savePurchaseCommon(PurchaseCommon purchaseCommon);
	/**
	 * (收货单) 根据receiveGoodsId 删除TB_FA_PURCHASE_COMMON
	 */
	void delPurchaseCommonByReceiveGoodsId(String receiveGoodsId);
//	Long updatePurchaseDetailByPurchaseDetailId(PurchaseCommon purchaseCommon);
	
	/**(收货单,保存)回写tb_fa_purchase_apply_mapping.goods_count
	 * @param purchaseCommon
	 * @return
	 */
	Long updatePurchaseApplyMappingByDemandDetailId(PurchaseCommon purchaseCommon);
	/**(收货单,修改)回写tb_fa_purchase_apply_mapping.goods_count
	 * @param purchaseCommon
	 * @return
	 */
	Long updatePurchaseApplyMappingByDemandDetailIdChange(PurchaseCommon purchaseCommon);
	
	/**(收货单,保存)回写tb_fa_purchase_order_detail.goods_count
	 * @param purchaseCommon
	 * @return
	 */
	Long updatePurchaseOrderDetailByPurchaseDetailId(PurchaseCommon purchaseCommon);
	/**(收货单,修改)回写tb_fa_purchase_order_detail.goods_count
	 * @param purchaseCommon
	 * @return
	 */
	Long updatePurchaseOrderDetailByApplyDetailId(PurchaseCommon purchaseCommon);
	
	
	/**(验收单 保存) 回写验收数量  tb_fa_purchase_order_detail.check_count 
	 * @param purchaseCommon
	 * @return
	 */
	Long updatePurchaseOrderDetailCheckCountByPurchaseDetailId(PurchaseCommon purchaseCommon);
	
	/**(验收单 修改) 回写验收数量  tb_fa_purchase_order_detail.check_count 
	 * @param purchaseCommon
	 * @return
	 */
	Long updatePurchaseOrderDetailCheckCountByPurchaseDetailIdChange(PurchaseCommon purchaseCommon);
	
	/**(验收单 保存) 回写订单申请映射表 的验收数量  （回写tb_fa_purchase_apply_mapping.check_count 
	 * @param purchaseCommon
	 * @return
	 */
	Long updatePurchaseApplyMappingCheckCountByDemandDetailId(PurchaseCommon purchaseCommon);
	
	/**(验收单 修改) 回写订单申请映射表 的验收数量  （回写tb_fa_purchase_apply_mapping.check_count 
	 * @param purchaseCommon
	 * @return
	 */
	Long updatePurchaseApplyMappingCheckCountByDemandDetailIdChange(PurchaseCommon purchaseCommon);
	
	/**(验收单  保存) 回写sap资产公共表(tb_fa_purchase_common.check_status),验收后更改验收状态
	 * @param purchaseCommon
	 * @return
	 */
	Long updatePurchaseCommonBySapAssetsCode(PurchaseCommon purchaseCommon);
	
	/**(验收单  修改) 回写sap资产公共表(tb_fa_purchase_common.check_status),验收后更改验收状态
	 * @param purchaseCommon
	 * @return
	 */
	Long updatePurchaseCommonBySapAssetsCodeChange(PurchaseCommon purchaseCommon);
	
	/**查询sap资产明细
	 * @param receiveGoodsId
	 * @return
	 */
	List<PurchaseCommon> searchPurchaseCommonByReceiveGoodsId(String receiveGoodsId);
	
	/**(验收单 修改) 回写sap资产公共表(tb_fa_purchase_common.check_status),更改为原来的状态 
	 * @param receiveGoodsId
	 * @return
	 */
	Long updatePurchaseCommonByReceiveGoodsId(String receiveGoodsId);

	/**
	 * 保存资产卡片主表ID
	 * @param generalAsset
	 */
    void updatePurchaseCommonByCardId(GeneralAsset generalAsset);
    /**
	 * 根据采购订单查询已验收未开发票的明细信息
	 * @param generalAsset
	 */
    List<PurchaseCommon> getPurchaseCommonbyPurchaseId(PaymentOrder paymentOrder);

	/**
	 * 根据cardId查询purchase common
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

	Long updatePurchaseCommonAddCardId(@Param("purchaseCommon") PurchaseCommon purchaseCommon);

    List<PurchaseCommon> getPurchaseCommonByReceiveId(@Param("receiveGoodsId") String receiveGoodsId);

    void deletePuchaseComon(PurchaseCommon purchaseCommon);
    void delPurchaseCommonBeforeSap(PurchaseCommon purchaseCommon);

    void updatePurchaseCommonLocation(PurchaseCommon pc);

    void updateDelPurchaseCommon(PurchaseCommon purchaseCommon);

    void updatePurchaseCommonBySapAssetsCode1(PurchaseCommon pcommom);
    /**
	 * 修改发票检验标识列
	 * @param cardId
	 * @return
	 */
    void updatePurchaseCommon(@Param("purchaseCommon") PurchaseCommon purchaseCommon);

	/**
	 * 收货保存草稿时调用方法
	 * @param purchaseCommon
	 */
    void updatePurchaseCommonDraft(@Param("purchaseCommon") PurchaseCommon purchaseCommon);

    List<PurchaseCommon> getCountsByReceivedId(String receiveGoodsId);

	List<ReceiveGoodsMapping> selectReceiveCheckMapping(String receiveGoodsId);

    List<PurchaseCommon> getPurchaseCommonByCheckAcceptanceDetailId(List<String> list);

    void updatePurchaseOrderStatus(String purchaseId);
}
