package com.opple.fa.sap.service;

import java.util.List;

import net.sf.json.JSONObject;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.model.PaymentOrderDetailModel;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.sap.entity.Eaddressdata;
import com.opple.fa.sap.model.IpHistoryModel;
import com.opple.fa.sap.model.IpInputModel;
import com.opple.fa.sap.model.IpZmm0010Model;
import com.opple.fa.sap.model.ItDataModel;
import com.opple.fa.sap.model.ItZmm0011Model;
import com.opple.fa.sap.model.ReadAssetsCodeModel;

/**
 * @Description: 
 * @author likai
 * @date 2016年12月08日 上午10:51:01
 */
public interface SapUtilService {
	
    /**
     * 创建资产编码
     * @param ipInputModel
     * @param ipHistoryModel
     * @return
     */
	JSONObject createAssetsCodeFromSAP(IpInputModel ipInputModel, IpHistoryModel ipHistoryModel);
   
	/**读取资产编码-追加使用
	 * @param ipInputModel
	 * @param ipHistoryModel
	 * @return
	 */
	JSONObject createAssetsCodeForAppendFromSAP(IpInputModel ipInputModel, IpHistoryModel ipHistoryModel);
	
	 
	/**读取资产编码存在性
	 * @param ipInputModel
	 * @param ipHistoryModel
	 * @return
	 */
	JSONObject existenceOfAssetsCodeFromSAP(IpInputModel ipInputModel, IpHistoryModel ipHistoryModel);

    /**
     * ,读取资产卡片信息-只能查单条
     * @param readAssetsCodeModel
     * @param itInputModel
     * @return
     */
	JSONObject readAssetsCodeFromSAP(ReadAssetsCodeModel readAssetsCodeModel);
    
	/**
	 * 获得采购订单接口
	 * @param purchaseOrder
	 * @param purchaseOrderDetails
	 * @return
	 */
	JSONObject createPurchaseOrderFromSAP(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchaseOrderDetails);
	  
	/**
	 * 获取预付凭证凭证接口
	 * @param paymentOrderSAP
	 * @param paymentOrderDetailsSAP
	 * @return
	 */
	JSONObject createPrePaymentVoucherFromSAP(PaymentOrderModel paymentOrderSAP, List<PaymentOrderDetailModel> paymentOrderDetailsSAP);
	 /**  
     * 修改固定资产和固定资产调拨接口
     * @param readAssetsCodeModel
     * @param itInputModel
     * @param itInputModelipHistoryModel
     * @param ipAsset
     * @return
     */
	JSONObject updateAssetsFromSAP(IpHistoryModel ipHistoryModel, IpInputModel ipInputModel, String ipAsset);
	
    /**采购订单收货接口
     * @param ipZmm0010Model
     * @param ipHistoryModel
     * @param itZmm0011Model
     */
	JSONObject createPurchaseOrderReceiveGoods(IpZmm0010Model ipZmm0010Model, IpHistoryModel ipHistoryModel, 
    		List<ItZmm0011Model> itZmm0011Models);
	
	/**发票校验
	 * @param itDatas
	 * @param part
	 * @return
	 */
	JSONObject createBillsCheck(List<ItDataModel> itDatas, Eaddressdata eaddressdata);
	
}
