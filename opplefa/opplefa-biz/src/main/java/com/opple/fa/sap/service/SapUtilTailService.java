package com.opple.fa.sap.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.opple.fa.purchase.entity.PaymentPaymentMapping;
import com.opple.fa.purchase.model.PaymentOrderDetailModel;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.sap.entity.Generalpostingdata;
import com.opple.fa.sap.entity.IAmtran;
import com.opple.fa.sap.entity.Retirementdata;
import com.opple.fa.sap.model.ReadAssetsCodeModel;

/**
 *  * @Description:
 *  * @author likai
 *  * @date 2016年12月08日 上午10:51:01
 */
public interface SapUtilTailService {

    /**
     * 获取验收款凭证接口--未用
     *
     * @param paymentOrderSAP
     * @param paymentOrderDetailsSAP
     * @return
     */
    JSONObject getCheckPaymentVoucherFromSAP(PaymentOrderModel paymentOrderSAP, List<PaymentOrderDetailModel> paymentOrderDetailsSAP, List<PaymentPaymentMapping> paymentPaymentMappings);

    /**
     * 清账凭证接口--未用
     *
     * @param paymentOrderSAP
     * @param paymentOrderDetailsSAP
     * @return
     */
    JSONObject clearVouch(PaymentOrderModel paymentOrderSAP, List<PaymentOrderDetailModel> paymentOrderDetailsSAP);

    /**
     * 验收款凭证--未用
     *
     * @param paymentOrderSAP
     * @param paymentOrderDetailsSAP
     * @return
     */
    JSONObject getCheckPaymentVoucherFromSAP2(
            PaymentOrderModel paymentOrderSAP,
            List<PaymentOrderDetailModel> paymentOrderDetailsSAP);

    /**
     * 资产报废凭证
     *
     * @param gData
     * @param rData
     * @return
     */
    JSONObject creatScrapCertificateFromSAP(Generalpostingdata gData, Retirementdata rData);

    /**
     * ,读取资产卡片信息-可以查多条（备用)
     *
     * @param readAssetsCodeModel
     * @return
     */
    JSONArray readAssetsCodeFromSAPOld(ReadAssetsCodeModel readAssetsCodeModel);

    /**
     * 公司间修改固定资产和固定资产调拨接口
     *
     * @param iAmtran
     * @return
     */
    JSONObject updateAssetCompanyFromSAP(IAmtran iAmtran);

}
