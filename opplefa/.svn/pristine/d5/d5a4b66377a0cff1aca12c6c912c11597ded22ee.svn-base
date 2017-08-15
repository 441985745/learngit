package com.opple.fa.sap.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opple.fa.purchase.entity.PaymentPaymentMapping;
import com.opple.fa.purchase.model.PaymentOrderDetailModel;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.sap.dao.AssetChangeCompanyISapDAO;
import com.opple.fa.sap.dao.AssetChangeCompanyOSapDAO;
import com.opple.fa.sap.dao.AssetRetireGSapDAO;
import com.opple.fa.sap.dao.AssetRetireMainSapDAO;
import com.opple.fa.sap.dao.AssetRetireRSapDAO;
import com.opple.fa.sap.dao.PayDocumentSapDAO;
import com.opple.fa.sap.dao.PayHeaderDocumentSapDAO;
import com.opple.fa.sap.entity.AssetChangeCompOut;
import com.opple.fa.sap.entity.AssetRetireMain;
import com.opple.fa.sap.entity.BapiAccDocumentPost;
import com.opple.fa.sap.entity.ClearVouch;
import com.opple.fa.sap.entity.Generalpostingdata;
import com.opple.fa.sap.entity.IAmtran;
import com.opple.fa.sap.entity.Retirementdata;
import com.opple.fa.sap.model.ReadAssetsCodeModel;
import com.opple.fa.sap.service.SapUtilService;
import com.opple.fa.sap.service.SapUtilTailService;
import com.opple.sap.util.SAPConn;
import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

/**
 * Created by lk on 03/21/17.
 */
public class SapUtilTailServiceImpl implements SapUtilTailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SapUtilService.class);


    @Getter
    @Setter
    private PayDocumentSapDAO payDocumentSapDAO;    //付款凭证
    @Getter
    @Setter
    private PayHeaderDocumentSapDAO payHeaderDocumentSapDAO;    //
    @Getter
    @Setter
    private AssetRetireGSapDAO assetRetireGSapDAO;    //资产报废表G..
    @Getter
    @Setter
    private AssetRetireRSapDAO assetRetireRSapDAO;    //资产报废表R..
    @Getter
    @Setter
    private AssetRetireMainSapDAO assetRetireMainSapDAO;    //资产报废主表
    @Getter
    @Setter
    private AssetChangeCompanyISapDAO assetChangeCompanyISapDAO;    //公司间资产调拨-传入参数
    @Getter
    @Setter
    private AssetChangeCompanyOSapDAO assetChangeCompanyOSapDAO;    //公司间资产调拨 -输出结果

    private JCoDestination destination;

    SapUtilTailServiceImpl() {
        destination = SAPConn.connect();
    }

    private boolean notEmpty(Object o) {
        if (null == o || "".equals(o.toString().trim())) {
            return false;
        }
        return true;
    }

    private String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateString = sdf.format(date);
        return dateString;
    }

    private String isNull(Object s, String r) {
        if (s == null || "".equals(s.toString().trim())) {
            return "|" + r;
        }
        return "";
    }


    /* 验收款凭证接口，先验收，有预付款的需要清账
     * @see com.opple.fa.sap.service.SapUtilService#getCheckPaymentVoucherFromSAP(com.opple.fa.purchase.model.PaymentOrderModel, java.util.List, java.util.List)
     */
    @Override
    public JSONObject getCheckPaymentVoucherFromSAP(
            PaymentOrderModel paymentOrderSAP,
            List<PaymentOrderDetailModel> paymentOrderDetailsSAP,
            List<PaymentPaymentMapping> paymentPaymentMappings) {
        JSONObject jsonMap = new JSONObject();
        if (paymentOrderDetailsSAP.size() == 0) {
            jsonMap.put("error", "行项目为空数据");
            return jsonMap;
        }

        JCoFunction functionV1;
        StringBuilder s1 = new StringBuilder();    //结果说明
        List<BapiAccDocumentPost> bapiAccDocumentPosts = new ArrayList<BapiAccDocumentPost>();    //创建参数list;
        try {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            String date = this.dateToString(new Date());
            functionV1 = destination.getRepository().getFunction("BAPI_ACC_DOCUMENT_POST");
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
            JCoParameterList importParamList = functionV1.getImportParameterList();
            JCoParameterList tableParamList = functionV1.getTableParameterList();
            JCoStructure documentHeader = importParamList.getStructure("DOCUMENTHEADER");

            //总账表
            JCoTable bapiAccountgl = tableParamList.getTable("ACCOUNTGL");
            //个人借支还款日期用这个表
//            JCoTable bapiAccountpayable = tableParamList.getTable("ACCOUNTPAYABLE");
            JCoTable currencyamount = tableParamList.getTable("CURRENCYAMOUNT");
            JCoTable extension2 = tableParamList.getTable("EXTENSION2");

            int size = paymentOrderDetailsSAP.size();
            BigDecimal pricePay = new BigDecimal(0);
            String valuePart1 = "";
            PaymentOrderDetailModel paymentOrderDetailSAP;
//            paymentOrderDetailsSAP.add(paymentOrderDetailsSAP.get(size-1));
            BigDecimal price;
            Long itemNo = 10L;    //行项目初始化
            String sag = "A", rc = "";
//            paymentOrderSAP.get\
            BigDecimal advancePriceTot = new BigDecimal(0);
            BigDecimal finalPriceTot = new BigDecimal(0);
            BigDecimal currentPriceTot = new BigDecimal(0);
            BigDecimal priceTot = new BigDecimal(0);
            BigDecimal advancePrice = new BigDecimal(0);
            BigDecimal finalPrice = new BigDecimal(0);
            BigDecimal currentPrice = new BigDecimal(0);
            int count = 3;
            for (int i = 0; i < paymentOrderDetailsSAP.size(); i++) {
                /*for (int j = 0; j < count; j++) {

	            	if (i == size) {
	            		paymentOrderDetailSAP = paymentOrderDetailsSAP.get(size - 1);
	            		itemNo = itemNo + 10;
	            		pricePay = priceTot.multiply(new BigDecimal(-1));
	            		price = pricePay;
	            		rc = "25";
	            		sag = " ";
	            		count = 1;	//取总和    一行
	            	} else {*/
                paymentOrderDetailSAP = paymentOrderDetailsSAP.get(i);
                advancePrice = paymentOrderDetailSAP.getAdvancePrice();
                advancePrice = advancePrice.multiply(new BigDecimal(-1));
                advancePriceTot = advancePriceTot.add(advancePrice);
                currentPrice = paymentOrderDetailSAP.getPayPrice();
                currentPrice = currentPrice.multiply(new BigDecimal(-1));
                currentPriceTot = currentPriceTot.add(currentPrice);
                finalPrice = paymentOrderDetailSAP.getFinalPrice();
                finalPrice = finalPrice.multiply(new BigDecimal(-1));
                finalPriceTot = finalPriceTot.add(finalPrice);
//		            	itemNo = 10 * 3 * i + 10 * j + 10L;     //paymentOrderDetailSAP.getItemnoAcc();
                        /*price = paymentOrderDetailSAP.getPayPrice();
		            	pricePay = pricePay.add(price);	//计算预付总金额
	*/
                priceTot = currentPriceTot.add(currentPriceTot).add(advancePriceTot).add(finalPriceTot);
//						rc = j == 2 ?	"35" : "39";
//	            	}
                //保存结果
                BapiAccDocumentPost bapiAccDocumentPost = new BapiAccDocumentPost();
                bapiAccDocumentPost.setCdocuments(paymentOrderSAP.getCdocument());
                //	            bapiAccDocumentPost.setEpBelnr(vouchcode);
                //	            bapiAccDocumentPost.setEtLog("".equals(vouchcode) ? s1.toString() : null);	//只保存无法生成凭证号时候的错误信息
                //	            bapiAccDocumentPost.setDoneFlag(d);
                //            	bapiAccDocumentPost.setRefDocNo(paymentOrderDetailSAP.getdo)//SAP回传的--会计凭证号码
                //	            payDocumentSapDAO.save(bapiAccDocumentPost);
                bapiAccDocumentPost.setOperateDate(new Date());
                bapiAccDocumentPost.setOperateUser(paymentOrderSAP.getApplyUserCode());
                bapiAccDocumentPost.setGlAccount(paymentOrderDetailSAP.getLedgerAccount());
                bapiAccDocumentPost.setUserName(paymentOrderSAP.getApplyUser());
                bapiAccDocumentPost.setCompanyCode(paymentOrderSAP.getCompanyCode());
                bapiAccDocumentPost.setDocDate(date);
                bapiAccDocumentPost.setPstngDate(date);
                bapiAccDocumentPost.setDocType(paymentOrderSAP.getDocType());
                bapiAccDocumentPost.setItemNoAcc(i * 10 + 10L);
                bapiAccDocumentPost.setVendorNo(paymentOrderSAP.getSupplierCode());
                bapiAccDocumentPost.setBusArea("6003");    //paymentOrderSAP.getProfitCenter());业务范围 动态！！！
                bapiAccDocumentPost.setBlineDate(date);
                bapiAccDocumentPost.setPstngDate(date);
                bapiAccDocumentPost.setAllocNmbr(paymentOrderSAP.getApplyUserCode());
//	            	sag = j == 0 ? "K" : j == 1 ? "A" : " ";
                bapiAccDocumentPost.setSpGlInd(sag);
                bapiAccDocumentPost.setCurrency(paymentOrderSAP.getCurrencyCode());
                bapiAccDocumentPost.setAmtDoccur(paymentOrderDetailSAP.getPayPrice());    //j == 0 ? finalPrice : (j == 1 ? advancePrice : currentPrice));	//凭证货币金额
                bapiAccDocumentPost.setStructure("ZRSTRG");
                String[] valuePart2 = {"000010K39", "000020A39", "000030 35", "000040 25"};    //= StringUtils.leftPad(String.valueOf(itemNo), 6, "0") + sag + rc;
                bapiAccDocumentPost.setValuepart1(valuePart2[i]);
                bapiAccDocumentPost.setActFlag("1");
                bapiAccDocumentPost.setFisYear(year);
                bapiAccDocumentPost.setFisPeriod(month);
                bapiAccDocumentPost.setCostCenterCode(paymentOrderSAP.getCostCenterCode());
                bapiAccDocumentPost.setItemText(paymentOrderSAP.getApplyFor());    //不能为空
                bapiAccDocumentPost.setProfitCenter("000000" + paymentOrderSAP.getProfitCenter());
                bapiAccDocumentPost.setExchRate(paymentOrderSAP.getExchangeRate());
                bapiAccDocumentPost.setBusAct("RFBU");    //固定（业务事务）
                bapiAccDocumentPosts.add(bapiAccDocumentPost);

                bapiAccountgl.appendRow();
                bapiAccountgl.setRow(i);    //* count + j);
                bapiAccountgl.setValue("COSTCENTER", bapiAccDocumentPost.getCostCenterCode());
                bapiAccountgl.setValue("GL_ACCOUNT", bapiAccDocumentPost.getGlAccount());
                bapiAccountgl.setValue("ACCT_TYPE", "K");
                bapiAccountgl.setValue("VENDOR_NO", bapiAccDocumentPost.getVendorNo());
                bapiAccountgl.setValue("COMP_CODE", bapiAccDocumentPost.getCompanyCode());
                bapiAccountgl.setValue("PSTNG_DATE", bapiAccDocumentPost.getPstngDate());
                bapiAccountgl.setValue("ITEM_TEXT", bapiAccDocumentPost.getItemText());    //项目文本
                //              bapiAccountgl.setValue("ORDERID", paymentOrderDetailSAP.getOrderid());
                bapiAccountgl.setValue("ALLOC_NMBR", bapiAccDocumentPost.getAllocNmbr());
                bapiAccountgl.setValue("ITEMNO_ACC", bapiAccDocumentPost.getItemNoAcc());
                bapiAccountgl.setValue("PROFIT_CTR", bapiAccDocumentPost.getProfitCenter());   //利润中心
                bapiAccountgl.setValue("BUS_AREA", bapiAccDocumentPost.getBusArea());   //业务范围

                currencyamount.appendRow();
                currencyamount.setRow(i);    // * count + j);
                currencyamount.setValue("ITEMNO_ACC", bapiAccDocumentPost.getItemNoAcc());    //会计凭证行项目编号	是
                currencyamount.setValue("CURRENCY", bapiAccDocumentPost.getCurrency());    //货币代码	是
                currencyamount.setValue("AMT_DOCCUR", bapiAccDocumentPost.getAmtDoccur());    //凭证货币金额	是
                currencyamount.setValue("EXCH_RATE", bapiAccDocumentPost.getExchRate());    //汇率

                extension2.appendRow();
                extension2.setRow(i);    // * count + j);
                extension2.setValue("STRUCTURE", bapiAccDocumentPost.getStructure());        //BAPI 表扩展的结构名称	是
                extension2.setValue("VALUEPART1", bapiAccDocumentPost.getValuepart1());        //BAPI 扩展参数的数据部分	是

//	            }
            }

            documentHeader.setValue("DOC_TYPE", "SA");        //凭证类型	是
            documentHeader.setValue("USERNAME", bapiAccDocumentPosts.get(0).getUserName());        //用户名	是
            documentHeader.setValue("BUS_ACT", bapiAccDocumentPosts.get(0).getBusAct());        //业务事务	是
            documentHeader.setValue("COMP_CODE", paymentOrderSAP.getCompanyCode());        //公司代码	是
            documentHeader.setValue("FISC_YEAR", bapiAccDocumentPosts.get(0).getFisYear());        //会计年度
            documentHeader.setValue("DOC_DATE", bapiAccDocumentPosts.get(0).getDocDate());        //凭证中的凭证日期	是
            documentHeader.setValue("PSTNG_DATE", bapiAccDocumentPosts.get(0).getPstngDate());        //凭证中的记帐日期	是
            documentHeader.setValue("FIS_PERIOD", bapiAccDocumentPosts.get(0).getFisPeriod());        //会计期间
//        	documentHeader.setValue("REF_DOC_NO", bapiAccDocumentPosts.get(0).getRefDocNo());		//参考凭证编号	是
//        	documentHeader.setValue("HEADER_TXT", bapiAccDocumentPosts.get(0));		//凭证抬头文本	

            JCoContext.begin(destination);
            functionV1.execute(destination);  //提交调用BAPI_ACC_DOCUMENT_POST 写入凭证信息
            functionC.execute(destination);    //提交调用BAPI_TRANSACTION_COMMIT 进行COMMIT一下
            JCoContext.end(destination);
            String vouchcode = "";
            JCoTable etLog = functionV1.getTableParameterList().getTable("RETURN");
            for (int i = 0; i < etLog.getNumRows(); i++) {
                etLog.setRow(i);
                s1.append(etLog.getValue("MESSAGE").toString());
                System.out.println(etLog.getValue("MESSAGE").toString());
                System.out.println(etLog.getValue("ROW").toString());
                System.out.println(etLog.getValue("FIELD").toString());
                System.out.println(etLog.getValue("PARAMETER").toString());
                System.out.println(etLog.getValue("TYPE").toString() + etLog.getValue("MESSAGE").toString());

                if ("S".equals(etLog.getValue("TYPE").toString()) && "RW".equals(etLog.getValue("ID").toString()) && "605".equals(etLog.getValue("NUMBER").toString())) {
                    s1.append(etLog.getValue("MESSAGE").toString());
                    //取开始位置 到 结束位置减开始位置长度的字符

                    vouchcode = s1.substring(s1.indexOf("BKPFF") + 6, s1.indexOf(String.valueOf(paymentOrderSAP.getCompanyCode()) + String.valueOf(year)));
                }
            }


            jsonMap.put("epBelnr", vouchcode);    //as:{"epBelnr":"0100037398"}
            for (int i = 0; i < bapiAccDocumentPosts.size(); i++) {
                BapiAccDocumentPost bapiAccDocumentPost = bapiAccDocumentPosts.get(i);
                bapiAccDocumentPost.setEpBelnr(vouchcode);
                boolean bool = "".equals("");
                bapiAccDocumentPost.setEtLog(bool ? s1.toString() : "过账成功");    //只保存无法生成凭证号时候的错误信息
                bapiAccDocumentPost.setDoneFlag(bool ? "0" : "1");    //1-success 0-fail
                bapiAccDocumentPost.setRefDocNo("");    //SAP回传的--会计凭证号码
                payDocumentSapDAO.save(bapiAccDocumentPost);

            }
            if ("SA".equals(paymentOrderSAP.getDocType())) {        //SA-预付，RE-验收
                jsonMap.put("result", s1.toString());
                return jsonMap;
            } else if ("RE".equals(paymentOrderSAP.getDocType())) {
                String billCode = paymentOrderSAP.getCdocument();    //核销流程单号
                return clearVouch(paymentOrderSAP, paymentOrderDetailsSAP);
            }
        } catch (JCoException e) {
            e.printStackTrace();
            LOGGER.error("JCoException", e);
            for (int i = 0; i < bapiAccDocumentPosts.size(); i++) {
                BapiAccDocumentPost bapiAccDocumentPost = bapiAccDocumentPosts.get(i);
                bapiAccDocumentPost.setEpBelnr("");
                boolean bool = "".equals("");
                bapiAccDocumentPost.setEtLog("过账失败：" + e.getMessage().substring(0, 1990));        //只保存无法生成凭证号时候的错误信息
                bapiAccDocumentPost.setDoneFlag(bool ? "0" : "1");    //1-success 0-fail
                bapiAccDocumentPost.setRefDocNo("");    //SAP回传的--会计凭证号码
                payDocumentSapDAO.save(bapiAccDocumentPost);

            }
        }
        return null;
    }

    //清除凭证
    public JSONObject clearVouch(PaymentOrderModel paymentOrderSAP, List<PaymentOrderDetailModel> paymentOrderDetailsSAP) {

        StringBuilder s1 = new StringBuilder();
//    	String vouchcode = paymentOrderSAP.getVoucherId();
        String billCode = paymentOrderSAP.getCdocument();
        JSONObject jsonMap = new JSONObject();
        JCoFunction myfun = null;
        JCoFunction functionC = null;
        try {
            myfun = destination.getRepository().getFunction("ZPOSTING_INTERFACE_CLEARING");
            functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
        } catch (JCoException e) {
            e.printStackTrace();
        }
        JCoParameterList importParams = myfun.getImportParameterList();
        //传入参数赋值
        importParams.setValue("I_AUGLV", "UMBUCHNG");
        importParams.setValue("I_TCODE", "FB05");
        importParams.setValue("I_SGFUNCT", "C");

        //获得函数中所需传入的表
        JCoTable bapiTFtclear = myfun.getTableParameterList().getTable("T_FTCLEAR");
        JCoTable bapiTFtpost = myfun.getTableParameterList().getTable("T_FTPOST");

        PaymentOrderDetailModel paymentOrderDetailSAP = null;
        String date = dateToString(new Date());
        for (int i1 = 0; i1 < paymentOrderDetailsSAP.size(); i1++) {
            ClearVouch clearVouch = new ClearVouch();
//        	if (i1 < paymentOrderDetailsSAP.size()) {
            paymentOrderDetailSAP = paymentOrderDetailsSAP.get(i1);
//        	} else {
//        		paymentOrderDetailSAP = paymentOrderDetailsSAP.get(i1 - 1);
//        	}
            bapiTFtclear.appendRow();
//            bapiTFtclear.insertRow(1);
            bapiTFtclear.setRow(i1);
            bapiTFtclear.setValue("AGKOA", "K");
            bapiTFtclear.setValue("AGKON", paymentOrderSAP.getSupplierCode());    // 供应商
            bapiTFtclear.setValue("AGBUK", paymentOrderSAP.getCompanyCode());
            bapiTFtclear.setValue("AGUMS", "A");    //paymentOrderDetailSAP.get); // 特别总账标志
            bapiTFtclear.setValue("XNOPS", "X");
            bapiTFtclear.setValue("SELFD", "BELNR");
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            String vouchcode = paymentOrderDetailSAP.getVoucherId();
            String para = StringUtils.leftPad(vouchcode, 10, "0") + String.valueOf(year) + "00" + String.valueOf(paymentOrderDetailSAP.getItemnoAcc());
            bapiTFtclear.setValue("SELVON", para);     //StringUtils.leftPad((paymentOrderDetailSAP.getItemnoAcc() + i1),3, "0"));	//凭证号+会计年度+三位行项目（001,002）
            clearVouch.setAgkon(paymentOrderSAP.getSupplierCode());
            clearVouch.setAgbuk(paymentOrderSAP.getCompanyCode());
            clearVouch.setSelvon(para);
            clearVouch.setDate(date);
//            clearVouch.setOperateUser(operateUser)
//            clearVouch.setOperateDate(new Date());
        }
        bapiTFtpost.appendRow();
        bapiTFtpost.setRow(0);
        bapiTFtpost.setValue("STYPE", "K");
        bapiTFtpost.setValue("COUNT", 1);
        bapiTFtpost.setValue("FNAM", "BKPF-BUDAT");
        bapiTFtpost.setValue("FVAL", date);

        bapiTFtpost.appendRow();
        bapiTFtpost.setRow(1);
        bapiTFtpost.setValue("STYPE", "K");
        bapiTFtpost.setValue("COUNT", 1);
        bapiTFtpost.setValue("FNAM", "BKPF-BLART");
        bapiTFtpost.setValue("FVAL", "AB");

        bapiTFtpost.appendRow();
        bapiTFtpost.setRow(2);
        bapiTFtpost.setValue("STYPE", "K");
        bapiTFtpost.setValue("COUNT", 1);
        bapiTFtpost.setValue("FNAM", "BKPF-BLDAT");
        bapiTFtpost.setValue("FVAL", date);

        bapiTFtpost.appendRow();
        bapiTFtpost.setRow(3);
        bapiTFtpost.setValue("STYPE", "K");
        bapiTFtpost.setValue("COUNT", 1);
        bapiTFtpost.setValue("FNAM", "BKPF-WAERS");
        bapiTFtpost.setValue("FVAL", "CNY");

        bapiTFtpost.appendRow();
        bapiTFtpost.setRow(4);
        bapiTFtpost.setValue("STYPE", "K");
        bapiTFtpost.setValue("COUNT", 1);
        bapiTFtpost.setValue("FNAM", "BKPF-BUKRS");
        bapiTFtpost.setValue("FVAL", paymentOrderSAP.getCompanyCode());


        bapiTFtpost.appendRow();
        bapiTFtpost.setRow(5);
        bapiTFtpost.setValue("STYPE", "K");
        bapiTFtpost.setValue("COUNT", 1);
        bapiTFtpost.setValue("FNAM", "BKPF-XBLNR");
        bapiTFtpost.setValue("FVAL", billCode);


        bapiTFtpost.appendRow();
        bapiTFtpost.setRow(6);
        bapiTFtpost.setValue("STYPE", "K");
        bapiTFtpost.setValue("COUNT", 1);
        bapiTFtpost.setValue("FNAM", "BKPF-BRNCH");
        bapiTFtpost.setValue("FVAL", "Z");
        try {
            JCoContext.begin(destination);
            myfun.execute(destination);
            //提交事务
            functionC.execute(destination);
            JCoContext.end(destination);
        } catch (JCoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        JCoParameterList ls = myfun.getExportParameterList();
//        myfun.getExportParameterList().getTable(arg0)
        String em = myfun.getExportParameterList().getString("E_MSGTY");
        String es = myfun.getExportParameterList().getString("E_SUBRC");
        if ("S".equals(em) && "0".equals(es)) {
            //成功
            String clearcode = myfun.getExportParameterList().getString("E_MSGV1");
            jsonMap.put("clearCode", clearcode);
            s1.append("清帐凭证号：").append(clearcode).append(";");
            System.out.println(s1);
        } else {
            s1.append("清帐失败");
            String eMsgid = myfun.getExportParameterList().getString("E_MSGID");
            String eMsgno = myfun.getExportParameterList().getString("E_MSGNO");
            String eMsgty = myfun.getExportParameterList().getString("E_MSGTY");
            String eMsgv1 = myfun.getExportParameterList().getString("E_MSGV1");
            String eMsgv2 = myfun.getExportParameterList().getString("E_MSGV2");
            String eMsgv3 = myfun.getExportParameterList().getString("E_MSGV3");
            String eMsgv4 = myfun.getExportParameterList().getString("E_MSGV4");
            String eSubrc = myfun.getExportParameterList().getString("E_SUBRC");
            System.out.println("eMsgid:" + eMsgid + ";eMsgno:" + eMsgno + ";eMsgty:"
                    + eMsgty + ";eMsgv1:" + eMsgv1 + ";eMsgv2:" + eMsgv2 + ";eMsgv3:" + eMsgv3
                    + ";eMsgv4:" + eMsgv4 + ";eSburc:" + eSubrc);
        }
        jsonMap.put("result", s1.toString());
        System.out.println(s1);
        return jsonMap;
    }


    /* (non-Javadoc)资产报废(或变卖)
     * @see com.opple.fa.sap.service.SapUtilService#creatScrapCertificateFromSAP(com.opple.fa.sap.model.DiscardeModel)
     */
    @Override
    public JSONObject creatScrapCertificateFromSAP(Generalpostingdata gData, Retirementdata rData) {

        AssetRetireMain assetRetireMain = new AssetRetireMain();
        JSONObject jsonMap = new JSONObject();
        jsonMap.put("result", "E");
        try {
            JCoFunction functionRetire = destination.getRepository().getFunction("BAPI_ASSET_RETIREMENT_POST");
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
            JCoParameterList createImportParam = functionRetire.getImportParameterList();
            JCoStructure generalPostingData = createImportParam.getStructure("GENERALPOSTINGDATA");
            String curDate = this.dateToString(new Date());
            Calendar cal = Calendar.getInstance();
//            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
//			generalPostingData.setValue("USERNAME", discardeModel.getUserName());
            generalPostingData.setValue("DOC_TYPE", "AA"); //凭证类型
            generalPostingData.setValue("DOC_DATE", curDate);
            generalPostingData.setValue("PSTNG_DATE", curDate);
            generalPostingData.setValue("FIS_PERIOD", StringUtils.leftPad(String.valueOf(month), 2, "0"));
            generalPostingData.setValue("TRANS_DATE", curDate);
            generalPostingData.setValue("COMP_CODE", gData.getCompCode());
            generalPostingData.setValue("ASSETMAINO", StringUtils.leftPad(gData.getAssetMaino(), 12, "0"));
            generalPostingData.setValue("ASSETSUBNO", StringUtils.leftPad(gData.getAssetSubno(), 4, "0"));

            JCoStructure retirementdata = createImportParam.getStructure("RETIREMENTDATA");
            retirementdata.setValue("VALUEDATE", curDate);

            String[] notNull = {"COMP_CODE", "ASSETMAINO", "ASSETSUBNO"};
            String[] warn = {"公司代码", "主资产编码", "次级资产编码"};
            StringBuilder error = new StringBuilder("");
            for (int i = 0; i < notNull.length; i++) {
                error.append(this.isNull(generalPostingData.getString(notNull[i]), warn[i]));
            }

//			部分报废下面需要传
            if (rData.isNotAll()) {    //数量、比率、金额只能任选其一
                if (rData.getQuantity() != null && rData.getQuantity() > 0) {
                    retirementdata.setValue("COMPL_RET", "X");
                    retirementdata.setValue("QUANTITY", rData.getQuantity());
                } else if (rData.getPercRate() != null && rData.getPercRate().compareTo(new BigDecimal(0)) == 1) {
                    retirementdata.setValue("PERC_RATE", rData.getPercRate());
                    retirementdata.setValue("CURRENCY", rData.getCurrency());

                } else if (rData.getAmount().compareTo(new BigDecimal(0)) == 1) {
                    retirementdata.setValue("AMOUNT", rData.getAmount());    //23,4 decimal 报废金额
                    retirementdata.setValue("REV_ON_RET", rData.getRevOnRet());    //资产销售中的收益
                    retirementdata.setValue("RET_COSTS", rData.getRetCosts());    //资产报废成本
                    retirementdata.setValue("CURRENCY", rData.getCurrency());    //货币
                    retirementdata.setValue("AREA_NBV", "00");

                    String[] notNull3 = {"AMOUNT", "REV_ON_RET", "RET_COSTS", "CURRENCY"};
                    String[] warn3 = {"公司代码", "资产销售中的收益", "资产报废成本", "货币"};
                    for (int i = 0; i < notNull3.length; i++) {
                        error.append(this.isNull(generalPostingData.getString(notNull3[i]), warn3[i]));
                    }
					
/*					retirementdata.setValue("QUANTITY", new BigDecimal(0.000));
					retirementdata.setValue("PERC_RATE", new BigDecimal(0.00));*/
                }
            }
//			部分报废上面需要传
            if (!"".equals(error.toString())) {
                jsonMap.put("error", error.append("不能为空").toString());
                return jsonMap;
            }

//			JCoTable retirementdata = createImportParam.getTable("RETIREMENTDATA");
            JCoContext.begin(destination);
            functionRetire.execute(destination);  //提交调用BAPI_ACC_DOCUMENT_POST 写入凭证信息
            functionC.execute(destination);    //提交调用BAPI_TRANSACTION_COMMIT 进行COMMIT一下
            JCoContext.end(destination);
            JCoParameterList exportList = functionRetire.getExportParameterList();
            JCoStructure retu = exportList.getStructure("RETURN");
            JCoStructure docu = exportList.getStructure("DOCUMENTREFERENCE");
            String type = retu.getString("TYPE");
            String message = retu.getString("MESSAGE");
            String messageV1 = retu.getString("MESSAGE_V1");
            String messageV2 = retu.getString("MESSAGE_V2");
            String messageV3 = retu.getString("MESSAGE_V3");
            String messageV4 = retu.getString("MESSAGE_V4");
//			String sapNo = message.substring(beginIndex)
            String parameter = retu.getString("PARAMETER");
            String field = retu.getString("FIELD");

            String objType = docu.getString("OBJ_TYPE");
            String objKey = docu.getString("OBJ_KEY");
            String objSys = docu.getString("OBJ_SYS");

            jsonMap.put("parameter", parameter);    //参数名称(问题参数)
            jsonMap.put("field", field);    //参数中的字段(问题字段）
            jsonMap.put("result", type);    //消息类型: S 成功,E 错误,W 警告,I 信息,A 中断
            jsonMap.put("message", message);    //错误消息
            jsonMap.put("messageV2", messageV2);    //凭证号
            jsonMap.put("messageV1", messageV1);
            jsonMap.put("messageV3", messageV3);
            jsonMap.put("messageV3", messageV4);
            jsonMap.put("objType", objType);
            jsonMap.put("objKey", objKey);
            jsonMap.put("objSys", objSys);

            try {
                assetRetireMain.setCompCode(gData.getCompCode());
//			assetRetireMain.setDocno(message);
                assetRetireMain.setOperateDate(new Date());
                assetRetireMain.setOperateUser(gData.getUserName());
                assetRetireMain.setEtLog(message);
                assetRetireMainSapDAO.save(assetRetireMain);
                gData.setFId(assetRetireMain.getId());
                rData.setFId(assetRetireMain.getId());
                assetRetireGSapDAO.save(gData);
                assetRetireRSapDAO.save(rData);
                StringBuilder sb = new StringBuilder();
                assetRetireMain.setEtLog(sb.append(type).append(field).append(parameter).append(message).toString());
                assetRetireMainSapDAO.save(assetRetireMain);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                LOGGER.error("creatScrapCertificateFromSAPException", e.getMessage());
            }
//				jsonMap.put("type, value)

        } catch (JCoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            jsonMap.put("error", e.getMessage());    //错误信息

        }
        return jsonMap;
    }

    /* 获取验收款凭证接口
   * @see com.opple.fa.sap.service.SapUtilService#getPrePaymentVoucherFromSAP(com.opple.fa.purchase.model.PaymentOrderModel, java.util.List)
   */
    @Override
    public JSONObject getCheckPaymentVoucherFromSAP2(PaymentOrderModel paymentOrderSAP, List<PaymentOrderDetailModel> paymentOrderDetailsSAP) {
        JSONObject jsonMap = new JSONObject();
        if (paymentOrderDetailsSAP.size() == 0) {
            jsonMap.put("error", "所传付款项目不能为空");
            return jsonMap;
        }

        JCoFunction functionV1;
        StringBuilder s1 = new StringBuilder();    //结果说明
        try {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            String date = this.dateToString(new Date());
            functionV1 = destination.getRepository().getFunction("BAPI_ACC_DOCUMENT_POST");
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
            JCoParameterList importParamList = functionV1.getImportParameterList();
            JCoParameterList tableParamList = functionV1.getTableParameterList();
            JCoStructure documentHeader = importParamList.getStructure("DOCUMENTHEADER");
//			documentHeader.setValue("OBJ_TYPE", paymentOrderSAP.getObjType());		//参考过程	
//			documentHeader.setValue("OBJ_KEY", paymentOrderSAP.getObjKey());		//对象关键字	
//			documentHeader.setValue("OBJ_SYS", paymentOrderSAP.getObjSys());		//源凭证的逻辑系统	
//			documentHeader.setValue("TRANS_DATE", paymentOrderSAP.getTransDate() == null || "".equals(paymentOrderSAP.getTransDate()) ? "" : paymentOrderSAP.getTransDate().replaceAll("-", ""));		//换算日期	
//			documentHeader.setValue("AC_DOC_NO", paymentOrderSAP.getAcDocNo());		//会计凭证号码	
//			documentHeader.setValue("OBJ_KEY_R", paymentOrderSAP.getObjKeyR());		//取消: 对象码 (AWREF_REV and AWORG_REV)	
//			documentHeader.setValue("REASON_REV", paymentOrderSAP.getReasonRev());		//冲销原因	
//			documentHeader.setValue("COMPO_ACC", paymentOrderSAP.getCompoAcc());		//ACC 界面中的组件	
//			documentHeader.setValue("REF_DOC_NO_LONG", paymentOrderSAP.getRefDocNoLong());		//	参考凭证号(对相关性参看长文本)	
//			documentHeader.setValue("ACC_PRINCIPLE", paymentOrderSAP.getAccPrinciple());		//会计原则	
//			documentHeader.setValue("NEG_POSTNG", paymentOrderSAP.getNegPostng());		//标识: 反记帐	
            //总账表
            JCoTable bapiAccountgl = tableParamList.getTable("ACCOUNTGL");
            JCoTable currencyamount = tableParamList.getTable("CURRENCYAMOUNT");
            JCoTable extension2 = tableParamList.getTable("EXTENSION2");
            int size = paymentOrderDetailsSAP.size();
            BigDecimal pricePay = new BigDecimal(0);
            String valuePart1 = "";
            String rc = "25";
            PaymentOrderDetailModel paymentOrderDetailSAP;
//            paymentOrderDetailsSAP.add(paymentOrderDetailsSAP.get(size-1));
            BigDecimal price;
            Long itemNo = 10L;    //行项目初始化
            String sag = "RE".equals(paymentOrderSAP.getDocType()) ? " " : " ";
            List<BapiAccDocumentPost> bapiAccDocumentPosts = new ArrayList<BapiAccDocumentPost>();    //创建参数list;
            for (int i = 0; i <= paymentOrderDetailsSAP.size(); i++) {
                if (i == size) {
                    paymentOrderDetailSAP = paymentOrderDetailsSAP.get(size - 1);
                    itemNo = itemNo + 10;
                    pricePay = pricePay.multiply(new BigDecimal(-1));
                    price = pricePay;
                    rc = "35";
                } else {
                    paymentOrderDetailSAP = paymentOrderDetailsSAP.get(i);
                    itemNo = paymentOrderDetailSAP.getItemnoAcc();
                    price = paymentOrderDetailSAP.getPayPrice();
                    pricePay = pricePay.add(price);    //计算预付总金额
                }
                //保存结果
                BapiAccDocumentPost bapiAccDocumentPost = new BapiAccDocumentPost();
                bapiAccDocumentPost.setCdocuments(paymentOrderSAP.getCdocument());
//	            bapiAccDocumentPost.setEpBelnr(vouchcode);
//	            bapiAccDocumentPost.setEtLog("".equals(vouchcode) ? s1.toString() : null);	//只保存无法生成凭证号时候的错误信息
//	            bapiAccDocumentPost.setDoneFlag(d);
//            	bapiAccDocumentPost.setRefDocNo(paymentOrderDetailSAP.getdo)//SAP回传的--会计凭证号码
//	            payDocumentSapDAO.save(bapiAccDocumentPost);
                bapiAccDocumentPost.setOperateDate(new Date());
                bapiAccDocumentPost.setOperateUser(paymentOrderSAP.getApplyUserCode());
                bapiAccDocumentPost.setGlAccount(paymentOrderDetailSAP.getLedgerAccount());
                bapiAccDocumentPost.setUserName(paymentOrderSAP.getApplyUser());
                bapiAccDocumentPost.setCompanyCode(paymentOrderSAP.getCompanyCode());
                bapiAccDocumentPost.setDocDate(date);
                bapiAccDocumentPost.setPstngDate(date);
                bapiAccDocumentPost.setDocType(paymentOrderSAP.getDocType());
                bapiAccDocumentPost.setItemNoAcc(itemNo);
                bapiAccDocumentPost.setVendorNo(paymentOrderSAP.getSupplierCode());
                bapiAccDocumentPost.setBusArea(paymentOrderSAP.getProfitCenter());
                bapiAccDocumentPost.setBlineDate(date);
                bapiAccDocumentPost.setPstngDate(date);
                bapiAccDocumentPost.setAllocNmbr(paymentOrderSAP.getApplyUserCode());
                bapiAccDocumentPost.setSpGlInd("RE".equals(paymentOrderSAP.getDocType()) ? " " : "");
                bapiAccDocumentPost.setCurrency(paymentOrderSAP.getCurrencyCode());
                bapiAccDocumentPost.setAmtDoccur(price);    //凭证货币金额
                bapiAccDocumentPost.setStructure("ZRSTRG");
                valuePart1 = StringUtils.leftPad(String.valueOf(itemNo), 6, "0") + sag + rc;
                bapiAccDocumentPost.setValuepart1(valuePart1);
                bapiAccDocumentPost.setActFlag("0");
                bapiAccDocumentPost.setFisYear(year);
                bapiAccDocumentPost.setFisPeriod(month);
                bapiAccDocumentPost.setCostCenterCode(paymentOrderSAP.getCostCenterCode());
                bapiAccDocumentPost.setItemText(paymentOrderSAP.getApplyFor());    //不能为空
                bapiAccDocumentPost.setProfitCenter("000000" + paymentOrderSAP.getProfitCenter());
                bapiAccDocumentPost.setExchRate(paymentOrderSAP.getExchangeRate());
                bapiAccDocumentPost.setBusAct("RFBU");
                bapiAccDocumentPosts.add(bapiAccDocumentPost);
                bapiAccountgl.appendRow();
                bapiAccountgl.setRow(i);
                bapiAccountgl.setValue("COSTCENTER", bapiAccDocumentPost.getCostCenterCode());
                bapiAccountgl.setValue("GL_ACCOUNT", bapiAccDocumentPost.getGlAccount());
//            	bapiAccountgl.setValue("ACCT_TYPE", paymentOrderDetailSAP.getAcctType());
                bapiAccountgl.setValue("VENDOR_NO", bapiAccDocumentPost.getVendorNo());
                bapiAccountgl.setValue("COMP_CODE", bapiAccDocumentPost.getCompanyCode());
                bapiAccountgl.setValue("PSTNG_DATE", bapiAccDocumentPost.getPstngDate());
                bapiAccountgl.setValue("ITEM_TEXT", bapiAccDocumentPost.getItemText());    //项目文本
//              bapiAccountgl.setValue("ORDERID", paymentOrderDetailSAP.getOrderid());
                bapiAccountgl.setValue("ALLOC_NMBR", bapiAccDocumentPost.getAllocNmbr());
                bapiAccountgl.setValue("ITEMNO_ACC", bapiAccDocumentPost.getItemNoAcc());
                bapiAccountgl.setValue("PROFIT_CTR", bapiAccDocumentPost.getProfitCenter());   //利润中心
                bapiAccountgl.setValue("BUS_AREA", bapiAccDocumentPost.getBusArea());   //业务范围
                currencyamount.appendRow();
                currencyamount.setRow(i);
                currencyamount.setValue("ITEMNO_ACC", bapiAccDocumentPost.getItemNoAcc());    //会计凭证行项目编号	是
                currencyamount.setValue("CURRENCY", bapiAccDocumentPost.getCurrency());    //货币代码	是
                currencyamount.setValue("AMT_DOCCUR", bapiAccDocumentPost.getAmtDoccur());    //凭证货币金额	是
                currencyamount.setValue("EXCH_RATE", bapiAccDocumentPost.getExchRate());    //汇率
                extension2.appendRow();
                extension2.setRow(i);
                extension2.setValue("STRUCTURE", bapiAccDocumentPost.getStructure());        //BAPI 表扩展的结构名称	是
                extension2.setValue("VALUEPART1", bapiAccDocumentPost.getValuepart1());        //BAPI 扩展参数的数据部分	是

            }

            documentHeader.setValue("DOC_TYPE", bapiAccDocumentPosts.get(0).getDocType());        //凭证类型	是
            documentHeader.setValue("USERNAME", bapiAccDocumentPosts.get(0).getUserName());        //用户名	是
            documentHeader.setValue("BUS_ACT", bapiAccDocumentPosts.get(0).getBusAct());        //业务事务	是
            documentHeader.setValue("COMP_CODE", paymentOrderSAP.getCompanyCode());        //公司代码	是
            documentHeader.setValue("FISC_YEAR", bapiAccDocumentPosts.get(0).getFisYear());        //会计年度
            documentHeader.setValue("DOC_DATE", bapiAccDocumentPosts.get(0).getDocDate());        //凭证中的凭证日期	是
            documentHeader.setValue("PSTNG_DATE", bapiAccDocumentPosts.get(0).getPstngDate());        //凭证中的记帐日期	是
            documentHeader.setValue("FIS_PERIOD", bapiAccDocumentPosts.get(0).getFisPeriod());        //会计期间
            documentHeader.setValue("REF_DOC_NO", bapiAccDocumentPosts.get(0).getRefDocNo());        //参考凭证编号	是
//        	documentHeader.setValue("HEADER_TXT", bapiAccDocumentPosts.get(0));		//凭证抬头文本	
            JCoContext.begin(destination);
            functionV1.execute(destination);  //提交调用BAPI_ACC_DOCUMENT_POST 写入凭证信息
            functionC.execute(destination);    //提交调用BAPI_TRANSACTION_COMMIT 进行COMMIT一下
            JCoContext.end(destination);
            String vouchcode = "";
            JCoTable etLog = functionV1.getTableParameterList().getTable("RETURN");
            for (int i = 0; i < etLog.getNumRows(); i++) {
                etLog.setRow(i);
                s1.append(etLog.getValue("MESSAGE").toString());
                System.out.println(etLog.getValue("MESSAGE").toString());
                System.out.println(etLog.getValue("ROW").toString());
                System.out.println(etLog.getValue("FIELD").toString());
                System.out.println(etLog.getValue("PARAMETER").toString());
                System.out.println(etLog.getValue("TYPE").toString() + etLog.getValue("MESSAGE").toString());

                if ("S".equals(etLog.getValue("TYPE").toString()) && "RW".equals(etLog.getValue("ID").toString()) && "605".equals(etLog.getValue("NUMBER").toString())) {
                    s1.append(etLog.getValue("MESSAGE").toString());
                    //取开始位置 到 结束位置减开始位置长度的字符

                    vouchcode = s1.substring(s1.indexOf("BKPFF") + 6, s1.indexOf(String.valueOf(paymentOrderSAP.getCompanyCode()) + String.valueOf(year)));
                    System.out.println("验收凭证号：" + vouchcode);
                }
            }

            for (int i = 0; i < bapiAccDocumentPosts.size(); i++) {
                BapiAccDocumentPost bapiAccDocumentPost = bapiAccDocumentPosts.get(i);
                bapiAccDocumentPost.setEpBelnr(vouchcode);
                boolean bool = "".equals(vouchcode);
                bapiAccDocumentPost.setEtLog(bool ? s1.toString() : "过账成功");    //只保存无法生成凭证号时候的错误信息
                bapiAccDocumentPost.setDoneFlag(bool ? "0" : "1");    //1-success 0-fail
                bapiAccDocumentPost.setRefDocNo(vouchcode);    //SAP回传的--会计凭证号码
                payDocumentSapDAO.save(bapiAccDocumentPost);

            }
            jsonMap.put("epBelnr", vouchcode);    //as:{"epBelnr":"0100037398"}
            if ("SA".equals(paymentOrderSAP.getDocType())) {        //SA-预付，RE-验收
                jsonMap.put("result", s1.toString());
                return jsonMap;
            } else if ("RE".equals(paymentOrderSAP.getDocType())) {
                String billCode = paymentOrderSAP.getCdocument();    //核销流程单号
                return clearVouch(paymentOrderSAP, paymentOrderDetailsSAP);
            }
        } catch (JCoException e) {
            e.printStackTrace();
            LOGGER.error("JCoException", e);
        }
        return jsonMap;
    }


    /**
     * ,读取资产编码-(变更时调用),次级编码为区间时返回多条以供选择，一次返回的只能是某个公司下的资产
     *
     * @param readAssetsCodeModel
     * @param itInputModel
     * @return
     */
    public JSONArray readAssetsCodeFromSAPOld(ReadAssetsCodeModel readAssetsCodeModel) {

        JSONArray jsonArr = new JSONArray();
        /*读取资产编码接口*/
        JCoFunction functionDisplay;
        JCoFunction monthlyDepreciation;
        try {
            functionDisplay = destination.getRepository().getFunction("BAPI_FIXEDASSET_GETLIST");    //ZRFC_AM_ASSET_DISPLAY");
            JCoParameterList createImportParam = functionDisplay.getImportParameterList(); //.setValue("BF_ANLKL", "-1");
            JCoStructure requested = functionDisplay.getImportParameterList().getStructure("REQUESTEDTABLESX");
            createImportParam.setValue("COMPANYCODE", readAssetsCodeModel.getCompanyCode());    //公司代码	是
            createImportParam.setValue("EVALUATIONDATE", dateToString(readAssetsCodeModel.getEvaluationDate()));    //评估日期
            createImportParam.setValue("DEPRECIATIONAREA", readAssetsCodeModel.getDepreciationArea());    //折旧范围
            createImportParam.setValue("MAXENTRIES", readAssetsCodeModel.getMaxEntries());        //命中的最大行数
//            createImportParam.setValue("QUANTITY", readAssetsCodeModel.getQuantity());		//数量
            requested.setValue("GENERALDATA", "X");        //
            requested.setValue("INVENTORY", "X");
            requested.setValue("POSTINGINFORMATION", "X");
            requested.setValue("TIMEDEPENDENTDATA", "X");
            requested.setValue("ALLOCATIONS", "X");
            requested.setValue("ORIGIN", "X");
            requested.setValue("INVESTACCTASSIGNMNT", "X");
            requested.setValue("NETWORTHVALUATION", "X");
            requested.setValue("REALESTATE", "X");
            requested.setValue("INSURANCE", "X");
            requested.setValue("LEASING", "X");
            requested.setValue("DEPRECIATIONAREAS", "X");
            requested.setValue("DEPRECIATIONAREAVALS", "X");
            JCoParameterList tl = functionDisplay.getTableParameterList();
            JCoTable itInput = tl.getTable("SELECTIONCRITERIA");
            JCoTable depreciationareavals = tl.getTable("DEPRECIATIONAREAVALS");
            for (int i = 0; i < readAssetsCodeModel.getOption().length; i++) {
                depreciationareavals.appendRow();
                depreciationareavals.setRow(i);
                itInput.appendRow();
                itInput.setRow(i);
                //以下为必输的值
                itInput.setValue("PARAMETER", this.notEmpty(readAssetsCodeModel.getParameter()) && this.notEmpty(readAssetsCodeModel.getParameter()[i]) ? readAssetsCodeModel.getParameter()[i] : "GENERALDATA");    //应应用选择条件的参数
                itInput.setValue("FIELD", readAssetsCodeModel.getField()[i]);        //应应用选择条件的字段
                itInput.setValue("SIGN", this.notEmpty(readAssetsCodeModel.getSign()) && this.notEmpty(readAssetsCodeModel.getSign()[i]) ? readAssetsCodeModel.getSign()[i] : "I");    //为范围表包括/排除标准 SIGN
                itInput.setValue("OPTION", readAssetsCodeModel.getOption()[i]);     //选择操作符 OPTION 用于范围表(BT是区间 ，后面需要high,EQ是等于，EQ时后面不需要high值)
                itInput.setValue("LOW", readAssetsCodeModel.getFieldValueLow()[i]);    //GETLIST的选择:值范围的下限
                itInput.setValue("HIGH", "EQ".equals(readAssetsCodeModel.getOption()[i]) ? "" : readAssetsCodeModel.getFieldValueHigh()[i]);    //GETLIST的选择:值范围的上限
            }
            functionDisplay.execute(destination);
            JCoParameterList createOutputParam = functionDisplay.getExportParameterList();
            JCoStructure etOutput = createOutputParam.getStructure("RETURN");
            String type = etOutput.getString("TYPE");
            String field = etOutput.getString("FIELD");
            if ("E".equals(type)) {
                JSONObject jsonMap = new JSONObject();
                String message = etOutput.getString("MESSAGE");
                jsonMap.put("error", field + message);
                jsonArr.add(jsonMap);
                return jsonArr;
            } else if ("A".equals(type)) {
                JSONObject jsonMap = new JSONObject();
                String message = etOutput.getString("MESSAGE");
                jsonMap.put("warn", field + message);
                jsonArr.add(jsonMap);
            }
            JCoTable generaldata = tl.getTable("GENERALDATA");
            JCoTable postinginformation = tl.getTable("POSTINGINFORMATION");
            JCoTable depreCiationAreavals = tl.getTable("DEPRECIATIONAREAVALS");
            JCoTable timeDependentData = tl.getTable("TIMEDEPENDENTDATA");
            int d = depreCiationAreavals.getNumRows();
            if (d == 0) {
                JSONObject jsonMap = new JSONObject();
                jsonMap.put("error", "查询不到符合条件的结果");
                jsonArr.add(jsonMap);
                return jsonArr;
            }
            int r = generaldata.getNumRows();
            BigDecimal currentApc = null;
            BigDecimal currentNbv = null;
            BigDecimal accumDepr = null;
            BigDecimal deprCurrYear = null;
            for (int i = 0; i < d; i++) {
                depreCiationAreavals.setRow(i);
                currentApc = depreCiationAreavals.getBigDecimal("CURRENT_APC");
                currentNbv = depreCiationAreavals.getBigDecimal("CURRENT_NBV");
                accumDepr = depreCiationAreavals.getBigDecimal("ACCUM_DEPR");
                deprCurrYear = depreCiationAreavals.getBigDecimal("DEPR_CURR_YEAR");
//		    	System.out.println(""+currentApc + currentNbv + accumDepr+deprCurrYear);
            }
            monthlyDepreciation = destination.getRepository().getFunction("ZRFC_AM_DEPRECIATION_LIST");    //获取月折旧额接口
            JCoParameterList importParam = monthlyDepreciation.getImportParameterList();
            for (int i = 0; i < r; i++) {
                generaldata.setRow(i);
                JSONObject jsonMap = new JSONObject();
                String asset = generaldata.getString("ASSET");            //主资产号
                String subNumber = generaldata.getString("SUBNUMBER");            //资产次级编号
                String assetClass = generaldata.getString("ASSETCLASS");        //资产分类
                String descript = generaldata.getString("DESCRIPT");            //资产描述
                String descript2 = generaldata.getString("DESCRIPT2");          //附加资产描述(选配件)
                String serialNo = generaldata.getString("SERIAL_NO");          //序列号
                String inventNo = generaldata.getString("INVENT_NO");          //库存号
/*			    BigDecimal quantity = generaldata.getBigDecimal("QUANTITY");          //数量
			    String baseUom = generaldata.getString("BASE_UOM");          //基本计量单位
			    String history = generaldata.getString("HISTORY");          //资产被历史性管理
			    String mainDescript = generaldata.getString("MAIN_DESCRIPT");          //资产主号说明
*/
                jsonMap.put("asset", asset);
                jsonMap.put("subNumber", subNumber);
                jsonMap.put("descript", descript);
                jsonMap.put("assetClass", assetClass.replaceFirst("^0*", ""));
                jsonMap.put("descript2", descript2);
                jsonMap.put("serialNo", serialNo);
                jsonMap.put("inventNo", inventNo);


                jsonArr.add(jsonMap);

//				System.out.println(asset + subNumber + descript);
            }
            postinginformation.setRow(0);
            Date capDate = postinginformation.getDate("CAP_DATE");  //购置日期
            String costcenter = timeDependentData.getString("COSTCENTER");  //成本中心
            String anln2 = etOutput.getString("TYPE");    //S-SUCCESS,E-ERROR
            String anlkl = etOutput.getString("MESSAGE");
            String FIELD = etOutput.getString("FIELD");
            String ROW = etOutput.getString("ROW");
            String A = etOutput.getString("PARAMETER");
            String B = etOutput.getString("NUMBER");


            JSONObject jsonMap = new JSONObject();
//			String	bukrs = etOutput.getString("BUKRS");	//公司代码
//			String	anln1 = etOutput.getString("ANLN1");	//主资产号
//			jsonMap.put("bukrs", bukrs);
//			jsonMap.put("anln1", anln1);
//			jsonArr.add(jsonMap);
            jsonMap.put("currentApc", currentApc); //资产原值
            jsonMap.put("currentNbv", currentNbv); //资产净值
            jsonMap.put("accumDepr", accumDepr); //累计折旧
            jsonMap.put("deprCurrYear", deprCurrYear); //本年已计提累计折旧
            jsonMap.put("capDate", capDate);
            jsonMap.put("costcenter", costcenter);
            jsonArr.add(jsonMap);
//			System.out.println(jsonArr);
        } catch (JCoException e) {
            e.printStackTrace();
            LOGGER.error("JCoException", e);
        }
        return jsonArr;
    }


    /**
     * 修改固定资产和固定资产调拨接口(公司间调拨)
     *
     * @param readAssetsCodeModel
     * @param itInputModel
     * @param itInputModelipHistoryModel
     * @param ipAsset
     * @return
     */
    public JSONObject updateAssetCompanyFromSAP(IAmtran iAmtran) {
        
        /*读取资产编码接口*/
        JCoFunction functionDisplay;
        JSONObject jsonMap = new JSONObject();
        StringBuilder sb = new StringBuilder("");
        try {
            functionDisplay = destination.getRepository().getFunction("ZAMDP_BAPI_CALL_ON_TRANSACTION");
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
            JCoParameterList createImportParam = functionDisplay.getImportParameterList(); //.setValue("BF_ANLKL", "-1");
//	            JCoParameterList importTable = functionDisplay.getTableParameterList();
            JCoStructure iAtn = createImportParam.getStructure("I_AMTRAN");
            String date = this.dateToString(new Date());
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            iAtn.setValue("BUKRS", iAmtran.getCompCode());        //公司代码
            iAtn.setValue("ANLN1", StringUtils.leftPad(iAmtran.getMainAssetNo(), 12, "0"));        //主资产号
            iAtn.setValue("ANLN2", StringUtils.leftPad(iAmtran.getAssetSubNo(), 4, "0"));        //资产次级编号
            iAtn.setValue("GJAHR", year);        //会计年度
            iAtn.setValue("MONAT", month);        //会计期间
            iAtn.setValue("PBUKR", iAmtran.getParCompCode());        //伙伴公司代码
            iAtn.setValue("PANL1", StringUtils.leftPad(iAmtran.getParAssetNo(), 12, "0"));        //合伙人资产主号（公司内部往来）
            iAtn.setValue("PANL2", StringUtils.leftPad(iAmtran.getParAssetSubNo(), 4, "0"));        //合伙人资产子编号（公司内部往来）
            iAtn.setValue("TRAVA", "0001");    //iAmtran.getTrava());		//公司内部资产往来变式
            iAtn.setValue("WAERS", "CNY");    //iAmtran.getCurrency());		//货币代码
            iAtn.setValue("BLART", "AA");    //iAmtran.getDocType());		//凭证类型
            iAtn.setValue("BUDAT", date);        //凭证中的记帐日期
            iAmtran.setMainAssetNo(iAtn.getString("ANLN1"));
            iAmtran.setAssetSubNo(iAtn.getString("ANLN2"));
            iAmtran.setParAssetNo(iAtn.getString("PANL1"));
            iAmtran.setParAssetSubNo(iAtn.getString("PANL2"));
            iAmtran.setTrava(iAtn.getString("TRAVA"));
            iAmtran.setCurrency(iAtn.getString("WAERS"));
            iAmtran.setDocType(iAtn.getString("BLART"));
            iAmtran.setYear(year);
            iAmtran.setMonthPeriod(month);
            iAmtran.setDocDate(date);
            String[] notNull = {"BUKRS", "ANLN1", "ANLN2", "GJAHR", "MONAT", "PBUKR", "PANL1", "PANL2"};
            String[] warn = {"公司代码", "主资产号", "资产次级编号", "会计年度", "会计期间", "伙伴公司代码", "合伙人资产主号（公司内部往来）", "合伙人资产子编号（公司内部往来）"};
            for (int i = 0; i < notNull.length; i++) {
                sb.append(this.isNull(iAtn.getString(notNull[i]), warn[i]));
            }
            if (!"".equals(sb.toString())) {
                jsonMap.put("error", sb.append("不能为空").toString());
                return jsonMap;
            }
            Long id = iAmtran.getId();
            JCoContext.begin(destination);
            //执行函数
            functionDisplay.execute(destination);
            //提交事务
            functionC.execute(destination);
            JCoContext.end(destination);
            JCoTable tReturn = functionDisplay.getTableParameterList().getTable("T_RETURN");
            StringBuilder res = new StringBuilder("");
            for (int i = 0; i < tReturn.getNumRows(); i++) {
                tReturn.setRow(i);
                String type = tReturn.getString("TYPE");
                System.out.println(tReturn.getString("ROW"));    //参数中的行
                String field = tReturn.getString("FIELD");
                System.out.println(field);    //参数中的字段
                String param = tReturn.getString("PARAMETER");
                System.out.println(param);    //参数名称
                System.out.println(tReturn.getString("TYPE").toString() + tReturn.getString("MESSAGE").toString());    //消息类型: S 成功,E 错误,W 警告,I 信息,A 中断
                String message = tReturn.getString("MESSAGE");
                res.append(message);    //消息文本
                if ("E".equals(type)) {
                    jsonMap.put("error", sb.append(field).append(param).append(message).toString());
                }
            }
            JCoParameterList createOutputParam = functionDisplay.getExportParameterList();
            String sapNo = createOutputParam.getString("T_BELNR");    //会计凭证号码
            String sapYear = createOutputParam.getString("T_GJAHR");    //会计年度
            String sapCompCode = createOutputParam.getString("T_BUKRS");    //公司代码
            String sapParNo = createOutputParam.getString("T_PELNR");    //伙伴公司会计凭证号码
            Integer sapParYear = createOutputParam.getInt("T_PJAHR");    //伙伴公司会计年度
            String sapParCompCode = createOutputParam.getString("T_PBUKR");    //伙伴公司代码
				/*if(!this.notEmpty(sapNo) || !this.notEmpty(sapParNo)) {
					jsonMap.put("error", sb.append("资产可能无法使用");
					
				}*/
            jsonMap.put("sapNo", sapNo);    //会计凭证号码
            jsonMap.put("sapYear", sapYear);    //会计年度
            jsonMap.put("sapCompCode", sapCompCode);    //公司代码
            jsonMap.put("sapParNo", sapParNo);    //伙伴公司会计凭证号码
            jsonMap.put("sapParYear", sapParYear);    //伙伴公司会计年度
            jsonMap.put("sapParCompCode", sapParCompCode);    //伙伴公司代码
            try {

                AssetChangeCompOut out = new AssetChangeCompOut();
                out.setFId(id);
                out.setSapNo(sapNo);
                out.setYear(year);
                out.setCompCode(sapCompCode);
                out.setParSapNo(sapParNo);
                out.setParYear(sapParYear);
                out.setParCompCode(sapParCompCode);
                out.setEtLog(sb.toString());

                assetChangeCompanyISapDAO.save(iAmtran);
                out.setFId(iAmtran.getId());
                assetChangeCompanyOSapDAO.save(out);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                LOGGER.error("createBillsCheckException", e.getMessage());
            }
				/*	
				ZrfcAmAssetChange zrfcAmAssetChange = new ZrfcAmAssetChange();
				zrfcAmAssetChange.setIpAsset(ipAsset);
				zrfcAmAssetChange.setSubnumber(ipInputModel.getSubnumber());
				zrfcAmAssetChange.setEpSubrc(result);
				zrfcAmAssetChange.setEtLog(res.toString());
//				zrfcAmAssetChange.setOperateUser(user);
				zrfcAmAssetChange.setOperateDate(new Date());
				//zrfcAmAssetChangeService.save(zrfcAmAssetChange);
				assetChangeSapDAO.save(zrfcAmAssetChange);*/

        } catch (JCoException e) {
            jsonMap.put("error", sb.append(e.getMessage()).toString());
            e.printStackTrace();
            LOGGER.error("JCoException", e);
        }
        return jsonMap;
    }


}


