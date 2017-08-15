package com.opple.fa.purchase.service.impl;

import com.opple.fa.assetcard.dao.AdministrationAssetDAO;
import com.opple.fa.assetcard.dao.ComputerAssetDAO;
import com.opple.fa.assetcard.dao.GeneralAssetDAO;
import com.opple.fa.assetcard.dao.MachineryEquipmentDAO;
import com.opple.fa.assetcard.dao.MeasuringInstrumentsDAO;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.purchase.dao.ApplyOrderDetailDAO;
import com.opple.fa.purchase.dao.CheckAcceptMappingDAO;
import com.opple.fa.purchase.dao.PurchaseCommonDAO;
import com.opple.fa.purchase.dao.ReceiveGoodsDAO;
import com.opple.fa.purchase.dao.ReceiveGoodsDetailDAO;
import com.opple.fa.purchase.dao.ReceiveGoodsMappingDAO;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.entity.ReceiveGoods;
import com.opple.fa.purchase.entity.ReceiveGoodsDetail;
import com.opple.fa.purchase.entity.ReceiveGoodsMapping;
import com.opple.fa.purchase.service.ReceiveGoodsService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ReceiveGoodsServiceImpl implements ReceiveGoodsService {
    @Getter
    @Setter
    private ReceiveGoodsDAO receiveGoodsDAO;
    @Getter
    @Setter
    private ReceiveGoodsDetailDAO receiveGoodsDetailDAO;
    @Getter
    @Setter
    private ReceiveGoodsMappingDAO receiveGoodsMappingDAO;
    @Getter
    @Setter
    private PurchaseCommonDAO purchaseCommonDAO;
    @Getter
    @Setter
    private AdministrationAssetDAO administrationAssetDAO;
    @Getter
    @Setter
    private CheckAcceptMappingDAO checkAcceptMappingDAO;

    @Getter
    @Setter
    private ComputerAssetDAO computerAssetDAO;
    @Getter
    @Setter
    private GeneralAssetDAO generalAssetDAO;
    @Getter
    @Setter
    private MachineryEquipmentDAO machineryEquipmentDAO;
    @Getter
    @Setter
    private MeasuringInstrumentsDAO measuringInstrumentsDAO;
    @Getter
    @Setter
    private ApplyOrderDetailDAO applyOrderDetailDAO;

    //	生成收货单编码用
    @Override
    public Long searchNextSequence() {
        return receiveGoodsDAO.searchNextSequence();
    }

    //	查询收货单的头
    @Override
    public ReceiveGoods searchReceiveGoodsByReceiveGoodsId(String receiveGoodsId) {
        return receiveGoodsDAO.searchReceiveGoodsByReceiveGoodsId(receiveGoodsId);
    }

    //	保存收货单的头
    @Override
    public Long saveReceiveGoods(ReceiveGoods receiveGoods) {
        return receiveGoodsDAO.saveReceiveGoods(receiveGoods);
    }

    //	删除收货单的头
    @Override
    public void delReceiveGoodsByReceiveGoodsId(String receiveGoodsId) {
        receiveGoodsDAO.delReceiveGoodsByReceiveGoodsId(receiveGoodsId);
    }

    @Override
    public ReceiveGoods getReceiveGoodsByDocument(String receiveGoodsId) {
        return receiveGoodsDAO.getReceiveGoodsByDocument(receiveGoodsId);
    }

    @Override
    public void deleteReceiveGoods(List<String> list1) {

        for (String receiveId : list1) {
            //如果没有被验收，才可以删除
            Long num = checkAcceptMappingDAO.getCountCheckAcceptanceByReceiveGoodsId(receiveId);
            if (num == 0) {
                /**
                 * 判断是否为草稿，然后进行不同的删除操作
                 */
                ReceiveGoods receiveGoods = receiveGoodsDAO.getReceiveGoodsByDocument(receiveId);
                receiveGoods.setReceiveGoodsId(receiveGoods.getCdocument());
                receiveGoodsDAO.saveDelete(receiveGoods);
                String purchaseId = receiveGoods.getPurchaseId();
                purchaseCommonDAO.updatePurchaseOrderStatus(purchaseId);
                List<ReceiveGoodsDetail> receiveGoodsDetailList = receiveGoodsDetailDAO.searchReceiveGoodsDetailByReceiveGoodsId(receiveId);
                receiveGoodsDetailDAO.saveDeleteBranch(receiveGoodsDetailList);
                //根据收货单id得到所有的数据,根据cardId来删除资产卡片中信息
                List<PurchaseCommon> purchaseCommonList = purchaseCommonDAO.getPurchaseCommonByReceiveId(receiveId);
                if ("N".equals(receiveGoods.getDraft())) {
                    //相当于收货时的rgd
                    for (int i = 0; i < receiveGoodsDetailList.size(); i++) {
                        receiveGoodsDetailList.get(i).setPurchaseId(purchaseId);
                    }
                    /**
                     * 删除收货头信息
                     */

                    receiveGoodsDAO.delReceiveGoodsByReceiveGoodsId(receiveId);
                    /**
                     * 删除行信息
                     */

                    List<Integer> ids = new ArrayList<Integer>();
                    for (ReceiveGoodsDetail rgd : receiveGoodsDetailList) {
                        ids.add(Integer.parseInt(rgd.getReceiveGoodsDetailId()));
                    }
                    receiveGoodsDetailDAO.deleteReceiveGoodsDetail(ids);
                    /**
                     * 回写采购订单明细表 的收货数量	（回写tb_fa_purchase_order_detail.goods_count）
                     */
                    PurchaseCommon pc = new PurchaseCommon();
                    for (ReceiveGoodsDetail rgd : receiveGoodsDetailList) {
                        pc = new PurchaseCommon();
                        pc.setPurchaseId(rgd.getPurchaseId());
                        //			pc.setPurchaseDetailId(rgd.getPurchaseDetailId());
                        pc.setApplyDetailId(rgd.getApplyDetailId());
                        pc.setThisGoodsCount(-rgd.getThisGoodsCount());
                        purchaseCommonDAO.updatePurchaseOrderDetailByPurchaseDetailId(pc);
                    }
                    /**
                     * 回写订单申请映射表 的收货数量  （回写tb_fa_purchase_apply_mapping.goods_count）
                     * */
                    List<ReceiveGoodsMapping> receiveGoodsMapping = receiveGoodsMappingDAO.searchReceiveGoodsMappingList(receiveId);
                    PurchaseCommon pc1 = null;
                    for (ReceiveGoodsMapping rgm : receiveGoodsMapping) {
                        pc1 = new PurchaseCommon();
                        pc1.setPurchaseId(rgm.getPurchaseId());
                        //					pc.setPurchaseDetailId(rgm.getPurchaseDetailId());
                        pc1.setApplyDetailId(rgm.getApplyDetailId());
                        pc1.setDemandDetailId(rgm.getDemandDetailId());
                        pc1.setThisGoodsCountDemand(-rgm.getGoodsCountDemand());
                        purchaseCommonDAO.updatePurchaseApplyMappingByDemandDetailId(pc1);
                    }
                    /**
                     *删除(TB_FA_RECEIVE_CHECK_MAPPING)
                     *TB_FA_RECEIVE_CHECK_MAPPING 收货单保存时插入数据
                     */
                    List<ReceiveGoodsMapping> rgms = receiveGoodsMappingDAO.searchReceiveGoodsMappingList(receiveId);
                    receiveGoodsMappingDAO.saveDeleteBranch(rgms);
                    receiveGoodsMappingDAO.deleteReceiveCheck(receiveId);
                    /**
                     * 删除资产卡片中信息
                     */
                    ApplyOrderDetail aod3 = new ApplyOrderDetail();
                    for (PurchaseCommon purchaseCommon : purchaseCommonList) {
                        aod3.setId(Long.parseLong(purchaseCommon.getApplyDetailId()));
                        aod3 = applyOrderDetailDAO.searchApplyOrderDetailById(aod3);
                        if (!"指定".equals(aod3.getCodingMode())) {
                            String cardId = purchaseCommon.getCardId();
                            String assetsType = purchaseCommon.getAssetsType();
                            GeneralAsset generalAsset = generalAssetDAO.getGeneralAsset(Integer.parseInt(cardId));
                            GeneralAsset ga = new GeneralAsset();
                            ga.setId(Long.parseLong(cardId));
                            generalAssetDAO.deleteAssetCardReal(ga); //不是真删除，修改标志位
                            Integer id = generalAsset.getAssetDetailId();
                            if ("IT资产".equals(assetsType)) {
                                computerAssetDAO.deleteComputerById(id); //不是真删除，修改标志位
                            }
                            if ("行政资产".equals(assetsType)) {
                                administrationAssetDAO.deleteAdministrationAsset(id);
                            }
                            if ("计量器具".equals(assetsType)) {
                                measuringInstrumentsDAO.deleteMeasuringInstruments(id);
                            }
                            if ("机器设备".equals(assetsType)) {
                                machineryEquipmentDAO.deleteMachineryEquipment(id);
                            }

                            /**
                             * 更新sap资产公共表(TB_FA_PURCHASE_COMMON PC)
                             */
                            purchaseCommonDAO.updateDelPurchaseCommon(purchaseCommon);

                        }

                    }
                } else {
                    /**
                     * 删除收货头信息
                     */
                    receiveGoodsDAO.delReceiveGoodsByReceiveGoodsId(receiveId);
                    /**
                     * 删除行信息
                     */
                    List<Integer> ids = new ArrayList<Integer>();
                    for (ReceiveGoodsDetail rgd : receiveGoodsDetailList) {
                        ids.add(Integer.parseInt(rgd.getReceiveGoodsDetailId()));
                    }
                    receiveGoodsDetailDAO.deleteReceiveGoodsDetail(ids);
                    /**
                     * 更新sap资产公共表(TB_FA_PURCHASE_COMMON PC)
                     */
                    for (PurchaseCommon purchaseCommon : purchaseCommonList) {
                        purchaseCommonDAO.updateDelPurchaseCommon(purchaseCommon);
                    }

                }


            }

        }


    }

    @Override
    public void updateCloseReceiveGoods(List<String> receiveGoodsId) {
        receiveGoodsDAO.updateCloseReceiveGoods(receiveGoodsId);
    }

    @Override
    public List<ReceiveGoods> getUserByDepartList(String userCode, String userName, String budgetDepartmentCode, String searchName, String searchCode) {

        return receiveGoodsDAO.getUserListPager(userCode, userName, budgetDepartmentCode, searchName, searchCode);
    }

    @Override
    public List<ReceiveGoods> searchReceiveGoodsByPurchaseId(String purchaseId) {
        return receiveGoodsDAO.searchReceiveGoodsByPurchaseId(purchaseId);
    }

    @Override
    public String searchPurchaseOrderStatus(String purchaseId) {
        return receiveGoodsDAO.searchPurchaseOrderStatus(purchaseId);
    }


//	收货单新增界面--提交&保存草稿
    /*@Override
	public Long saveReceiveGoods(
			ReceiveGoods receiveGoods,
			List<ReceiveGoodsDetail> receiveGoodsDetails) { 

		PurchaseCommon purchaseCommon = null;
		PurchaseCommonModel purchaseCommonModel = null;
		Long isSaveHead = null; //判断是否保存(在controller里 )
		Long isSaveDetail = null; //判断是否保存(在controller里 )
		Long isSavePurchase = null; //判断是否保存(在controller里 )
		for(ReceiveGoodsDetail rgd : receiveGoodsDetails){
			rgd.getPurchaseId();
			rgd.getPurchaseDetailId();
//		 		正常逻辑：订单数量明细数量 >= 已收货数量 + 本次收货数量
//		 		判断逻辑：订单数量明细数量 > 已收货数量 + 本次收货数量  (说明本次收货数量在收货的详情页已经修改，这里不用追溯到需求表了)
//		 		判断逻辑：订单数量明细数量 == 已收货数量 + 本次收货数量  (说明本次收货数量为默认的，需要对公共表的收货的需求数量更改或添加)
				 if( rgd.getApplyCount() == rgd.getRecGoodsCount() + rgd.getThisGoodsCount() ){ //追溯需求表
	// 			查询订单ID订单明细ID对应的需求明细ID，以及下订单对应的需求的数量；（可能对应多个需求明细ID）
					List<PurchaseCommon> demandDetaiIdApplyCountlList = purchaseCommonDAO.searchDemandDetailByPurchaseDetailId(rgd.getPurchaseDetailId());
					for(PurchaseCommon pc : demandDetaiIdApplyCountlList){
						purchaseCommon = new PurchaseCommon();
						purchaseCommon.setDemandDetailId(pc.getDemandDetailId());
						purchaseCommon.setApplyCount(pc.getApplyCount());
						purchaseCommon.setRecGoodsCountDemand(rgd.getRecGoodsCount());
						purchaseCommon.setThisGoodsCountDemand(rgd.getThisGoodsCount());
						if(0 >= purchaseCommonDAO.searchPurchaseCommonCountByDemandDetailId(pc.getDemandDetailId())){ //公共资产表里对应的需求明细ID
							  purchaseCommonDAO.savePurchaseCommonByReceiveGoods(purchaseCommon);
						}else{ // 公共资产表里有 相应的需求明细ID，这里更改已收货的需求数量
//							注意：这里，如果是不对收货数量的文本框做修改的话，就是相当于该订单明细都要收货完成，所以可以将资产公共表里的applyCount的数量就等于recGoodsCountDemand
							purchaseCommonDAO.updatePurchaseCommonByDemandDetailId_(purchaseCommon);
						}
					}
				 }
		 对收货单进行保存,明细
		isSaveDetail = receiveGoodsDetailDAO.saveReceiveGoodsDetail(rgd);	
		回写到订单明细表里
//		isSavePurchase = receiveGoodsDetailDAO.updatePurchaseDetailByPurchaseDetailId(rgd.getPurchaseDetailId());
		}
		对收货单进行保存,头
		isSaveHead = receiveGoodsDAO.saveReceiveGoods(receiveGoods); //放在明细后保存
		Long isSave = isSaveDetail + isSaveHead;
		
		return isSave;
	}
*/


}
