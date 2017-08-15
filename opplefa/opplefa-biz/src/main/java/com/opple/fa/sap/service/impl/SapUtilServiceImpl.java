package com.opple.fa.sap.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opple.fa.purchase.dao.PurchaseCommonLoggerDAO;
import com.opple.fa.purchase.dao.PurchaseCommonDAO;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.entity.PurchaseCommonLogger;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.model.PaymentOrderDetailModel;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.sap.dao.AssetChangeSapDAO;
import com.opple.fa.sap.dao.BillCheckSapDAO;
import com.opple.fa.sap.dao.CreateAssetSapDAO;
import com.opple.fa.sap.dao.PayDocumentSapDAO;
import com.opple.fa.sap.dao.PurchaseOrderSapDAO;
import com.opple.fa.sap.dao.ReceiveGoodsISapDAO;
import com.opple.fa.sap.dao.ReceiveGoodsMainSapDAO;
import com.opple.fa.sap.dao.ReceiveGoodsSapDAO;
import com.opple.fa.sap.dao.VoucherpostChildSapDAO;
import com.opple.fa.sap.dao.VoucherpostSapDAO;
import com.opple.fa.sap.entity.BapiAccDocumentPost;
import com.opple.fa.sap.entity.CreateAsset;
import com.opple.fa.sap.entity.Eaddressdata;
import com.opple.fa.sap.entity.EtZmm0012;
import com.opple.fa.sap.entity.ReceiveGoodsMain;
import com.opple.fa.sap.entity.Voucherpost;
import com.opple.fa.sap.entity.VoucherpostChild;
import com.opple.fa.sap.entity.ZrfcAmAssetChange;
import com.opple.fa.sap.entity.ZrfcMmMb1a1;
import com.opple.fa.sap.model.IpHistoryModel;
import com.opple.fa.sap.model.IpInputModel;
import com.opple.fa.sap.model.IpZmm0010Model;
import com.opple.fa.sap.model.ItDataModel;
import com.opple.fa.sap.model.ItZmm0011Model;
import com.opple.fa.sap.model.ReadAssetsCodeModel;
import com.opple.fa.sap.service.SapUtilService;
import com.opple.sap.util.SAPConn;
import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

/**
 * Created by lk on 10/24/16.
 */
public class SapUtilServiceImpl implements SapUtilService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SapUtilService.class);
    private final static String GLACCOUNT = "1151000101";    //总账科目，预付帐款的时候的固定值；
    private final static String OAMODULE = "固定资产MRMS系统";    //参数：oa模块名
    private final static String ZFLAG_CREATE = "A";    //创建
    private final static String ZFLAG_APPEND = "B";    //追加
    private final static String ZFLAG_EXISTENCE = "C";    //验证存在性

    @Getter
    @Setter
    private CreateAssetSapDAO createAssetSapDAO;
    @Getter
    @Setter
    private PurchaseOrderSapDAO purchaseOrderSapDAO;
    @Getter
    @Setter
    private PayDocumentSapDAO payDocumentSapDAO;
    @Getter
    @Setter
    private VoucherpostSapDAO voucherpostSapDAO;
    @Getter
    @Setter
    private VoucherpostChildSapDAO voucherpostChildSapDAO;
    @Getter
    @Setter
    private AssetChangeSapDAO assetChangeSapDAO;
    @Getter
    @Setter
    private ReceiveGoodsSapDAO receiveGoodsSapDAO;
    @Getter
    @Setter
    private ReceiveGoodsMainSapDAO receiveGoodsMainSapDAO;
    @Getter
    @Setter
    private ReceiveGoodsISapDAO receiveGoodsISapDAO;
    @Getter
    @Setter
    private BillCheckSapDAO billCheckSapDAO;
    @Getter
    @Setter
    private PurchaseCommonDAO purchaseCommonDAO;

    @Getter
    @Setter
    private PurchaseCommonLoggerDAO purchaseCommonLoggerDAO;
    private JCoDestination destination;

    SapUtilServiceImpl() {
        destination = SAPConn.connect();
    }

    //判断是否非空
    private boolean notEmpty(Object o) {
        if (null == o || "".equals(o.toString().trim())) {
            return false;
        }
        return true;
    }

    //日期到字符串类型
    private String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateString = sdf.format(date);
        return dateString;
    }

    //时间调字符串类型
    private String timeToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String timeString = sdf.format(date);
        return timeString;
    }

    //日期时间到字符串类型
    private String dateTimeToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateTimeString = sdf.format(date);
        return dateTimeString;
    }

    private String getCurrDate() {
        String date = this.dateToString(new Date());    //当前日期string类型
        return date;

    }

    private String getCurrTime() {
        String time = this.timeToString(new Date());    //当前日期string类型
        return time;

    }

    private String isNull(Object s, String r) {
        if (s == null || "".equals(s.toString().trim())) {
            return r + "  ";
        }
        return "";
    }

    //保存创建的资产编码
    private void saveCreateAsset(CreateAsset createAsset) {
        createAssetSapDAO.save(createAsset);
    }

    /**
     * 创建资产编码	(操作类型：A)（输入数量就可以，程序会默认自动给sap的必须参数次级编码赋值）
     *
     * @param ipInputModel
     * @param ipHistoryModel
     * @return
     */
    public JSONObject createAssetsCodeFromSAP(IpInputModel ipInputModel, IpHistoryModel ipHistoryModel) {
//    	ipInputModel.setZFlag("A");
        JSONObject jsonMap = new JSONObject();
        jsonMap.put("error", "");
        CreateAsset createAsset = new CreateAsset();
        createAsset.setAssetclass(ipInputModel.getAssetclass());    //资产分类
        createAsset.setCompanycode(ipInputModel.getCompanycode());    //公司代码
        createAsset.setDescript(ipInputModel.getDescript());    //描述
//    	createAsset.setQuantity(new BigDecimal(0));
        createAsset.setBaseUom(ipInputModel.getBaseUom());    //基本计量单位，建议用单位的内码
        createAsset.setCostcenter(ipInputModel.getCostcenter());    //成本中心
        createAsset.setSubnumber(ipInputModel.getSubnumber());    //次级资产编号
        createAsset.setZflag(ZFLAG_CREATE);    //操作类型-A

        JCoFunction functionCreate;

        try {
            functionCreate = destination.getRepository().getFunction("ZRFC_AM_ASSET_CREATE");    //创建资产编码函数
            String user = destination.getUser();    //当前sap登录用户
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");    //	事务
            JCoParameterList createImportParam = functionCreate.getImportParameterList(); //.setValue("BF_ANLKL", "-1");
            JCoStructure ipInput = createImportParam.getStructure("IP_INPUT");    //结构类型参数
            //以下为必输的值(调用时传的数量是要生成的次级编码的数量，不是次级编码对应的产品数量，对应的产品数量开始要求默认是0，不用传）
            ipInput.setValue("ASSETCLASS", ipInputModel.getAssetclass());    //资产分类
            ipInput.setValue("COMPANYCODE", ipInputModel.getCompanycode());    //公司代码
//			ipInput.setValue("QUANTITY", new BigDecimal(0));	//ipInputModel.getQuantity());	//数量创建时候默认为0，不用传
            if (!"7000".equals(ipInputModel.getAssetclass())) {
                ipInput.setValue("BASE_UOM", ipInputModel.getBaseUom());    //基本计量单位--必须在sap中存在!!!看表tb_fa_sap_unit，此处传的是表里对应的单位编码
            }
            ipInput.setValue("COSTCENTER", ipInputModel.getCostcenter());    //成本中心
            //要求填写
            ipInput.setValue("DESCRIPT", ipInputModel.getDescript() == null || "".equals(ipInputModel.getDescript().trim()) ? "无" : ipInputModel.getDescript());    //资产描述
            ipInput.setValue("MAIN_DESCRIPT", ipInputModel.getMainDescript() == null || "".equals(ipInputModel.getMainDescript().trim()) ? "无" : ipInputModel.getMainDescript());    //资产附加描述
            ipInput.setValue("INVENT_NO", ipInputModel.getInventNo());    //库存号
            StringBuilder error = new StringBuilder("");
            if (ipInputModel.getQuantity() > 1000) {
                error.append("请输入1000以下的数量，当前不允许超过1000！如有问题，请联系管理员，谢谢!(友情提示：如果创建数量超过1000，可以先创建1000个次级编码，然后追加，追加可以达到次级编码为9999的数值)");
                jsonMap.put("error", error.toString());
                return jsonMap;
            }
            String subNo = StringUtils.leftPad(String.valueOf(ipInputModel.getQuantity()), 4, "0");
            ipInput.setValue("SUBNUMBER", "0000");    //生成次级编码的数量
            ipInput.setValue("ZFLAG", ZFLAG_CREATE);    //操作类型
            if (!"7000".equals(ipInputModel.getAssetclass())) {
                String[] notNull = {"ASSETCLASS", "COMPANYCODE", "DESCRIPT", "QUANTITY", "BASE_UOM", "COSTCENTER"};    //不为空的属性
                String[] warn = {"资产分类", "公司代码", "资产描述", "数量", "基本计量单位", "成本中心编码"};    //非空提示字段
                //非空判断
                for (int i = 0; i < notNull.length; i++) {
                    error.append(this.isNull(ipInput.getString(notNull[i]), warn[i]));
                }
            } else {
                String[] notNull = {"ASSETCLASS", "COMPANYCODE", "DESCRIPT", "QUANTITY", "COSTCENTER"};    //不为空的属性
                String[] warn = {"资产分类", "公司代码", "资产描述", "数量", "成本中心编码"};    //非空提示字段
                //非空判断
                for (int i = 0; i < notNull.length; i++) {
                    error.append(this.isNull(ipInput.getString(notNull[i]), warn[i]));
                }
            }

            //有为空的必填字段就提示
            if (!"".equals(error.toString())) {
                jsonMap.put("error", error.append("不能为空").toString());
                return jsonMap;
            }
            //以下为非必输的值
            ipInput.setValue("DESCRIPT2", ipInputModel.getDescript2());    //
            ipInput.setValue("ACCT_DETRM", ipInputModel.getAcctDetrm());    //
            ipInput.setValue("SERIAL_NO", ipInputModel.getSerialNo());    //序列号
            ipInput.setValue("HISTORY", ipInputModel.getHistory());
            ipInput.setValue("DATE", ipInputModel.getDate() == null || "".equals(ipInputModel.getDate()) ? "" : this.dateTimeToString(ipInputModel.getDate()));
            ipInput.setValue("NOTE", ipInputModel.getNote());
            ipInput.setValue("INCLUDE_IN_LIST", ipInputModel.getIncludeInList());
            ipInput.setValue("DEACT_DATE", ipInputModel.getDeactDate() == null || "".equals(ipInputModel.getDeactDate()) ? "" : ipInputModel.getDeactDate().replaceAll("-", ""));
            ipInput.setValue("BUS_AREA", ipInputModel.getBusArea());
            ipInput.setValue("LOCATION", ipInputModel.getLocation());
            ipInput.setValue("ROOM", ipInputModel.getRoom());
            ipInput.setValue("EVALGROUP1", ipInputModel.getEvalgroup1());
            ipInput.setValue("ANLN1", ipInputModel.getAnln1());

            //表名：IP_HISTORY ,最后“是”为是否必输
            JCoStructure ipHistory = createImportParam.getStructure("IP_HISTORY");
            ipHistory.setValue("MANDT", ipHistoryModel.getMandt());        //客户端
            ipHistory.setValue("OANO", ipHistoryModel.getOano());        //OA UNID	是
            ipHistory.setValue("SAPNO", ipHistoryModel.getSapno());        //SAP操作对象(销售订单/交货订单/物料主数据/客户主数据/供应商主
            ipHistory.setValue("MBLNR", ipHistoryModel.getMblnr());        //写入SAP产生的凭证
            ipHistory.setValue("ZDATE", ipHistoryModel.getZdate() == null || "".equals(ipHistoryModel.getZdate()) ? "" : ipHistoryModel.getZdate().replaceAll("-", ""));        //日期
            ipHistory.setValue("ZTIME", ipHistoryModel.getZtime() == null || "".equals(ipHistoryModel.getZtime()) ? "" : ipHistoryModel.getZtime().replaceAll(":", ""));        //时间
            ipHistory.setValue("OANAME", ipHistoryModel.getOaname());        //OA操作人姓名	是
            ipHistory.setValue("OAMODULE", OAMODULE);        //OA模块名	是
            ipHistory.setValue("RFC", ipHistoryModel.getRfc());        //SAP函数名
            ipHistory.setValue("OAID", user);    //ipHistoryModel.getOaid());		//OA操作人ID	是
            ipHistory.setValue("SAPID", ipHistoryModel.getSapid());        //SAP操作人ID
            ipHistory.setValue("COMPANY", ipInputModel.getCompanycode());    //ipHistoryModel.getCompany());		//公司名称	是
            ipHistory.setValue("DEPT", ipHistoryModel.getDept());        //业务部门描述	是
            ipHistory.setValue("REMARK", ipHistoryModel.getRemark());        //说明		是

            String[] notNull2 = {"OANO", "OANAME"};
            String[] warn2 = {"OA UNID", "OA操作人姓名"};
            StringBuilder error2 = new StringBuilder("");
            for (int i = 0; i < notNull2.length; i++) {
                error2.append(this.isNull(ipHistory.getString(notNull2[i]), warn2[i]));
            }
            if (!"".equals(error2.toString())) {
                jsonMap.put("error", error2.append("不能为空").toString());
                return jsonMap;
            }

            JCoContext.begin(destination);    //事务开始位置
            //执行函数
            functionCreate.execute(destination);
            //提交事务
            functionC.execute(destination);    //提交调用
            JCoContext.end(destination);    //事务结束位置
            JCoParameterList createOutputParam = functionCreate.getExportParameterList();
            //获得对象
            JCoStructure epAsset = createOutputParam.getStructure("EP_ASSET");
            JCoStructure epLog = createOutputParam.getStructure("EP_LOG");
            String r = epLog.getString("TYPE");    //返回结果问题类型
            String f = epLog.getString("FIELD");    //问题字段
            String p = epLog.getString("PARAMETER");    //问题参数
            String m = epLog.getString("MESSAGE");    //问题描述
            //对象取值
            String companyCode = epAsset.getString("COMPANYCODE");    //公司代码
            String asset = epAsset.getString("ASSET");    //主资产编码
            String toNumber = epAsset.getString("SUBNUMBER");    //次级资产编码，这个是返回的最小的次级编号
            if ("E".equals(r) || !this.notEmpty(asset) || !this.notEmpty(toNumber)) {
                jsonMap.put("error", error.append("生成资产编号失败，请检查参数是否无误").append("字段：").append(f).append("参数:").append(p).append("sap返回消息文本：").append(m).toString());
                return jsonMap;
            }
            if (ipInputModel.getQuantity() > 1) {
                ipInputModel.setSubnumber(String.valueOf(ipInputModel.getQuantity() - 1));
                ipInputModel.setAnln1(asset);
                JSONObject mm = this.createAssetsCodeForAppendFromSAP(ipInputModel, ipHistoryModel);
                String subN = mm.getString("subNumber");
                subN = subN.replaceFirst("0001", "0000");
                mm.remove("subNumber");
                mm.put("subNumber", subN);
                return mm;
            } else {
                /*int subNumberInt = 0;
                if (toNumber != null && !"".equals(toNumber)) {
					try {
						subNumberInt = Integer.parseInt(toNumber);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Long beginNum = -ipInputModel.getQuantity() + subNumberInt + 1;	//根据数量计算最大次级编号
				String bNum = beginNum.toString();
				bNum = StringUtils.leftPad(bNum, 4, "0");
				toNumber = StringUtils.leftPad(toNumber , 4, "0");
				String subNumber = bNum + "-" + toNumber;	//次级编号展示范围
	//			subNumber = StringUtils.leftPad(subNumber, 4, "0");
				if (asset == null || "".equals(asset)) {
					subNumber = "-";
				}*/
                jsonMap.put("companyCode", companyCode);    //公司代码
                jsonMap.put("asset", asset);    //主资产编号
                jsonMap.put("subNumber", "0000-0000"); //subNumber);	//次级资产编号
                try {
                    createAsset.setAssetno(asset);
                    createAsset.setSubnumber("0000-0000"); //subNumber);
                    this.saveCreateAsset(createAsset);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    LOGGER.error("createAssetsCodeFromSAPException", e.getMessage());
                    return jsonMap;
                }
            }
        } catch (JCoException e) {
            this.saveCreateAsset(createAsset);
            e.printStackTrace();
            LOGGER.error("createAssetsCodeFromSAPException", e.getMessage());
            jsonMap.put("error", e.getMessage());
            return jsonMap;
        }
        return jsonMap;
    }


    /* (non-Javadoc)读取资产编码-追加使用    B类型
     * @see com.opple.fa.sap.service.SapUtilService#createAssetsCodeForAppendFromSAP(com.opple.fa.sap.model.IpInputModel, com.opple.fa.sap.model.IpHistoryModel)
     */
    public JSONObject createAssetsCodeForAppendFromSAP(IpInputModel ipInputModel, IpHistoryModel ipHistoryModel) {
        JSONObject jsonMap = new JSONObject();
        CreateAsset createAsset = new CreateAsset();
        JCoFunction functionCreate;

//        jsonMap.put("error", "");
        try {
            functionCreate = destination.getRepository().getFunction("ZRFC_AM_ASSET_CREATE");
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
            JCoParameterList createImportParam = functionCreate.getImportParameterList(); //.setValue("BF_ANLKL", "-1");
            JCoStructure ipInput = createImportParam.getStructure("IP_INPUT");
            //以下为必输的值
            String companyCode = ipInputModel.getCompanycode();
            ipInput.setValue("COMPANYCODE", companyCode);    //公司代码
            ipInput.setValue("BASE_UOM", ipInputModel.getBaseUom());    //基本计量单位
//			ipInput.setValue("QUANTITY", new BigDecimal(ipInputModel.getQuantity()));	//数量
            ipInput.setValue("ANLN1", StringUtils.leftPad(ipInputModel.getAnln1(), 12, "0"));    //主编码
            ipInput.setValue("SUBNUMBER", StringUtils.leftPad(ipInputModel.getSubnumber(), 4, "0"));    //次级编码(要追加的数量)
            //要求填写
            ipInput.setValue("DESCRIPT", ipInputModel.getDescript() == null || "".equals(ipInputModel.getDescript().trim()) ? "无" : ipInputModel.getDescript());    //资产描述
            ipInput.setValue("MAIN_DESCRIPT", ipInputModel.getMainDescript() == null || "".equals(ipInputModel.getMainDescript().trim()) ? "无" : ipInputModel.getMainDescript());    //资产附加描述
            ipInput.setValue("INVENT_NO", ipInputModel.getInventNo());    //库存号

            ipInput.setValue("ZFLAG", ZFLAG_APPEND);    //操作类型
            String[] notNull = {"COMPANYCODE", "ANLN1", "SUBNUMBER"};
            String[] warn = {"公司代码", "主编码", "次级编码"};
            StringBuilder error = new StringBuilder("");
            for (int i = 0; i < notNull.length; i++) {
                error.append(this.isNull(ipInput.getString(notNull[i]), warn[i]));
            }
            if (!"".equals(error.toString())) {
                jsonMap.put("error", error.append("不能为空").toString());
                return jsonMap;
            }
            createAsset.setCompanycode(ipInputModel.getCompanycode());
            createAsset.setAssetno(ipInputModel.getAnln1());
            createAsset.setSubnumber(ipInputModel.getSubnumber());
            createAsset.setZflag(ZFLAG_APPEND); //ipInputModel.getZFlag());
            JCoContext.begin(destination);
            //执行函数
            functionCreate.execute(destination);
            //提交事务
            functionC.execute(destination);
            JCoContext.end(destination);
            JCoParameterList createOutputParam = functionCreate.getExportParameterList();
            //获得对象
            JCoStructure epAsset = createOutputParam.getStructure("EP_ASSET");
            JCoStructure epLog = createOutputParam.getStructure("EP_LOG");
            String r = epLog.getString("TYPE");
            System.out.println(r);
            String p = epLog.getString("PARAMETER");
            String f = epLog.getString("FIELD");
            String m = epLog.getString("MESSAGE");
            System.out.println(f);
            System.out.println(m);
            //对象取值
//			String companyCode = epAsset.getString("COMPANYCODE");
//			String asset = epAsset.getString("ASSET");
            String toNumber = epAsset.getString("SUBNUMBER");
            createAsset.setSubnumber(toNumber);    //将返回的次级编码保存

            if ("S".equals(r) && this.notEmpty(toNumber)) {
                jsonMap.put("error", "");
            } else {
                jsonMap.put("error", error.append("生成资产次级编号失败，请确认参数是否无误，谢谢！").append("(字段：[").append(f).append("]参数:[").append(p).append("]) {注意：次级编码数量超过9999也会失败，请确认不要超过这个值}").toString());
                return jsonMap;
            }
            int subNumberInt = 0;
            if (toNumber != null && !"".equals(toNumber)) {
                try {
                    subNumberInt = Integer.parseInt(toNumber);
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            int beginNum = -Integer.parseInt(ipInputModel.getSubnumber()) + subNumberInt + 1;
            String bNum = String.valueOf(beginNum);
            bNum = StringUtils.leftPad(bNum, 4, "0");
            toNumber = StringUtils.leftPad(toNumber, 4, "0");
            String subNumber = bNum + "-" + toNumber;
            subNumber = StringUtils.leftPad(subNumber, 4, "0");
            if (subNumber == null || "".equals(subNumber)) {
                subNumber = "-";
            }

            jsonMap.put("companyCode", companyCode);
            jsonMap.put("asset", ipInputModel.getAnln1());
            jsonMap.put("subNumber", subNumber);


            try {
                createAsset.setSubnumber(subNumber);    //将返回的次级编码重新赋值
                this.saveCreateAsset(createAsset);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                LOGGER.error("createAssetsCodeForAppendFromSAPException", e.getMessage());
                return jsonMap;
            }
        } catch (JCoException e) {
            this.saveCreateAsset(createAsset);
            e.printStackTrace();
            LOGGER.error("createAssetsCodeForAppendFromSAPException", e.getMessage());
            jsonMap.put("error", e);
            return jsonMap;
        }
        return jsonMap;
    }


    /**
     * 验证资产编码存在与否
     *
     * @param readAssetsCodeModel
     * @param itInputModel
     * @return
     */
    public JSONObject existenceOfAssetsCodeFromSAP(IpInputModel ipInputModel, IpHistoryModel ipHistoryModel) {

        JSONObject jsonMap = new JSONObject();
        JCoFunction functionCreate;

        try {
            functionCreate = destination.getRepository().getFunction("ZRFC_AM_ASSET_CREATE");
            JCoParameterList createImportParam = functionCreate.getImportParameterList(); //.setValue("BF_ANLKL", "-1");
            JCoStructure ipInput = createImportParam.getStructure("IP_INPUT");
            //以下为必输的值
            String companyCode = ipInputModel.getCompanycode();
            ipInput.setValue("COMPANYCODE", companyCode);    //公司代码
            ipInput.setValue("ANLN1", StringUtils.leftPad(ipInputModel.getAnln1(), 12, "0"));        //主编码
            ipInput.setValue("SUBNUMBER", StringUtils.leftPad(ipInputModel.getSubnumber(), 4, "0"));    //次级编码
            ipInput.setValue("ZFLAG", ZFLAG_EXISTENCE);    //操作类型
            String[] notNull = {"COMPANYCODE", "ANLN1", "SUBNUMBER"};
            String[] warn = {"公司代码", "主编码", "次级编码"};
            StringBuilder error = new StringBuilder("");
            for (int i = 0; i < notNull.length; i++) {
                error.append(this.isNull(ipInput.getString(notNull[i]), warn[i]));
            }
            if (!"".equals(error.toString())) {
                jsonMap.put("error", error.append("不能为空").toString());
                return jsonMap;
            }

            //执行函数
            functionCreate.execute(destination);
            JCoParameterList createOutputParam = functionCreate.getExportParameterList();
            //获得对象
            JCoStructure epLog = createOutputParam.getStructure("EP_LOG");    //结果信息的结构
            String r = epLog.getString("TYPE");
            System.out.println(r);
            String f = epLog.getString("FIELD");
            String m = epLog.getString("MESSAGE");
            System.out.println(f + m);
            //对象取值
//			String companyCode = epAsset.getString("COMPANYCODE");
//			String asset = epAsset.getString("ASSET");
            if ("S".equals(r)) {
                jsonMap.put("error", "");
                jsonMap.put("exist", true);
            } else if ("E".equals(r)) {
                jsonMap.put("error", f + m);
                jsonMap.put("exist", false);
                return jsonMap;
            }
        } catch (JCoException e) {
            e.printStackTrace();
            LOGGER.error("existenceOfAssetsCodeFromSAPException", e.getMessage());
            jsonMap.put("error", e);
            return jsonMap;
        }
        return jsonMap;
    }

    /**
     * ,读取资产编码-(变更时调用)
     *
     * @param readAssetsCodeModel
     * @param itInputModel
     * @return
     */
    /* (non-Javadoc)
     * @see com.opple.fa.sap.service.SapUtilService#readAssetsCodeFromSAP(com.opple.fa.sap.model.ReadAssetsCodeModel)
     */
    public JSONObject readAssetsCodeFromSAP(ReadAssetsCodeModel readAssetsCodeModel) {
        
        /*读取资产编码接口*/
        JCoFunction functionDisplay;
        JCoFunction monthlyDepreciation;
        JSONObject jsonMap = new JSONObject();
        jsonMap.put("error", "");
        try {
            functionDisplay = destination.getRepository().getFunction("BAPI_FIXEDASSET_GETLIST");    //ZRFC_AM_ASSET_DISPLAY");
            JCoParameterList createImportParam = functionDisplay.getImportParameterList(); //.setValue("BF_ANLKL", "-1");
            JCoStructure requested = functionDisplay.getImportParameterList().getStructure("REQUESTEDTABLESX");
            createImportParam.setValue("COMPANYCODE", readAssetsCodeModel.getCompanyCode());    //公司代码	是
            StringBuilder error = new StringBuilder("");
            error.append(this.isNull(createImportParam.getString("COMPANYCODE"), "公司代码"));
            createImportParam.setValue("EVALUATIONDATE", dateToString(this.notEmpty(readAssetsCodeModel.getEvaluationDate()) ? readAssetsCodeModel.getEvaluationDate() : new Date()));    //评估日期
            createImportParam.setValue("DEPRECIATIONAREA", this.notEmpty(readAssetsCodeModel.getDepreciationArea()) ? readAssetsCodeModel.getDepreciationArea() : "00");    //折旧范围
            createImportParam.setValue("MAXENTRIES", this.notEmpty(readAssetsCodeModel.getMaxEntries()) ? readAssetsCodeModel.getMaxEntries() : 1);        //命中的最大行数
            error.append(this.isNull(createImportParam.getString("MAXENTRIES"), "命中的最大行数"));
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
            itInput.appendRow();
            itInput.setRow(0);
            itInput.setValue("PARAMETER", "GENERALDATA");    //应应用选择条件的参数
            itInput.setValue("FIELD", "ASSET");        //应应用选择条件的字段
            itInput.setValue("SIGN", "I");    //为范围表包括/排除标准 SIGN
            itInput.setValue("OPTION", "EQ");     //选择操作符 OPTION 用于范围表(BT是区间 ，后面需要high,EQ是等于，EQ时后面不需要high值)
            itInput.setValue("LOW", StringUtils.leftPad(readAssetsCodeModel.getMasterAssetCode(), 12, "0"));    //GETLIST的选择:主资产编码
            error.append(this.isNull(itInput.getString("LOW"), "主资产编码"));
            itInput.appendRow();
            itInput.setRow(1);
            itInput.setValue("PARAMETER", "GENERALDATA");    //应应用选择条件的参数
            itInput.setValue("FIELD", "SUBNUMBER");        //应应用选择条件的字段
            itInput.setValue("SIGN", "I");    //为范围表包括/排除标准 SIGN
            itInput.setValue("OPTION", "EQ");     //选择操作符 OPTION 用于范围表(BT是区间 ，后面需要high,EQ是等于，EQ时后面不需要high值)
            itInput.setValue("LOW", StringUtils.leftPad(readAssetsCodeModel.getSecondaryAssetCode(), 4, "0"));    //GETLIST的选择:次级资产编码
            error.append(this.isNull(itInput.getString("LOW"), "次级资产编码"));
            if (!"".equals(error.toString())) {
                jsonMap.put("error", error.append("不能为空").toString());
                return jsonMap;
            }
            functionDisplay.execute(destination);
            JCoParameterList createOutputParam = functionDisplay.getExportParameterList();
            JCoStructure etOutput = createOutputParam.getStructure("RETURN");
            String type = etOutput.getString("TYPE");
            if ("E".equals(type)) {
                String field = etOutput.getString("FIELD");
                String message = etOutput.getString("MESSAGE");
                jsonMap.put("error", field + message);
                return jsonMap;
            }
            JCoTable generaldata = tl.getTable("GENERALDATA");
            if (generaldata.getNumRows() == 0) {
                jsonMap.put("error", "此类资产在sap中没有查到相关数据");
                return jsonMap;
            }
            JCoTable postinginformation = tl.getTable("POSTINGINFORMATION");
            JCoTable depreCiationAreavals = tl.getTable("DEPRECIATIONAREAVALS");
            JCoTable timeDependentData = tl.getTable("TIMEDEPENDENTDATA");
            if (timeDependentData.getNumRows() == 0) {
                timeDependentData.appendRow();
            }
            JCoTable depreciationAreas = tl.getTable("DEPRECIATIONAREAS");
            int d = depreCiationAreavals.getNumRows();

            int dANum = depreciationAreas.getNumRows();
            JCoTable origin = tl.getTable("ORIGIN");
            if (origin.getNumRows() == 0) {
                origin.appendRow();
            }
            origin.setRow(0);
            String originAsset = origin.getString("ORIG_ASSET").replaceFirst("^0*", "");    //原公司主资产编码
            String tradeId = origin.getString("TRADE_ID");    //原公司编码
            if (this.notEmpty(originAsset)) {
                String originAssetSubno = origin.getString("ORIG_ASSET_SUBNO");    //原公司次级资产编码
                originAssetSubno = "0000".equals(originAssetSubno) ? "0" : originAssetSubno.replaceFirst("^0*", "");
                //		    jsonMap.put("originAsset", originAsset);
                //		    jsonMap.put("originAssetSubno", originAssetSubno);
                originAsset = originAsset + "-" + originAssetSubno;
                originAsset = originAsset.replaceFirst("^0*", "");    //替换掉前面的零
            }
            jsonMap.put("originAssetNo", originAsset);    //原公司资产编码
            jsonMap.put("tradeId", tradeId);    //原公司编码
            String deprBeginDate = "";
            BigDecimal currentApc = new BigDecimal("0.00");
            BigDecimal currentNbv = new BigDecimal("0.00");
            BigDecimal accumDepr = new BigDecimal("0.00");
            BigDecimal deprCurrYear = new BigDecimal("0.00");
            for (int i = 0; i < d; i++) {
                depreCiationAreavals.setRow(i);
                if ("01".equals(depreCiationAreavals.getString("AREA"))) {
                    currentApc = depreCiationAreavals.getBigDecimal("CURRENT_APC");
//		    		currentNbv = depreCiationAreavals.getBigDecimal("CURRENT_NBV");
//		    		accumDepr = depreCiationAreavals.getBigDecimal("ACCUM_DEPR");
//		    		deprCurrYear = depreCiationAreavals.getBigDecimal("DEPR_CURR_YEAR");
                }
            }
            int ulifeYrs = 0;
            int ulifePrds = 0;
            if (d > 0) {
                for (int i = 0; i < dANum; i++) {
                    depreciationAreas.setRow(i);
                    if ("01".equals(depreciationAreas.getString("AREA"))) {
                        deprBeginDate = depreciationAreas.getString("ODEP_START_DATE");
                        ulifeYrs = depreciationAreas.getInt("ULIFE_YRS");
                        ulifePrds = depreciationAreas.getInt("ULIFE_PRDS");
                    }
                }
                int ulife = ulifeYrs * 12 + ulifePrds;
                jsonMap.put("ulife", ulife);    //折旧年限
                jsonMap.put("deprBeginDate", deprBeginDate);    //折旧开始日期
            } else {
                jsonMap.put("warn", "没有折旧信息");
                jsonMap.put("ulife", "0");    //折旧年限
                jsonMap.put("deprBeginDate", "");    //折旧开始日期
            }

            generaldata.setRow(0);
            String asset = generaldata.getString("ASSET");            //主资产号
            String subNumber = generaldata.getString("SUBNUMBER");            //资产次级编号
            String assetClass = generaldata.getString("ASSETCLASS");        //资产分类
            String descript = generaldata.getString("DESCRIPT");            //资产描述
            String descript2 = generaldata.getString("DESCRIPT2");          //附加资产描述(选配件)
            String serialNo = generaldata.getString("SERIAL_NO");          //序列号
            String inventNo = generaldata.getString("INVENT_NO");          //库存号
            BigDecimal quantity = new BigDecimal(generaldata.getString("QUANTITY"));          //数量
            String baseUom = generaldata.getString("BASE_UOM");          //基本计量单位
            String history = generaldata.getString("HISTORY");          //资产被历史性管理
            String mainDescript = generaldata.getString("MAIN_DESCRIPT");          //资产主号说明
            jsonMap.put("asset", asset);        //主资产号
            jsonMap.put("subNumber", subNumber);        //资产次级编号
            jsonMap.put("descript", descript);        //资产描述
            jsonMap.put("assetClass", assetClass.replaceFirst("^0*", ""));        //资产分类
            jsonMap.put("descript2", descript2);    //附加资产描述(选配件)
            jsonMap.put("serialNo", serialNo);    //序列号
            jsonMap.put("inventNo", inventNo);    //库存号---   责任人编号-姓名
            jsonMap.put("quantity", quantity);   //数量
            jsonMap.put("baseUom", baseUom);   //基本计量单位
            jsonMap.put("history", history);   //资产被历史性管理
            jsonMap.put("mainDescript", mainDescript);    //资产主号说明
            /*庄恒礼，资产折旧信息查询开始*/
            monthlyDepreciation = destination.getRepository().getFunction("ZRFC_AM_DEPRECIATION_LIST");    //获取月折旧额接口
            JCoParameterList importParam = monthlyDepreciation.getImportParameterList();
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            //马盛蕾2017-0615要求 传递上一月获取累计折旧
            if (month == 0) {
                month = 12;
                year = year - 1;
            }
            importParam.clear();
            importParam.setValue("ES_ANLN1", asset); //资产主编码
            importParam.setValue("ES_ANLN2", subNumber); //次级资产编码
            importParam.setValue("ES_BUKRS", readAssetsCodeModel.getCompanyCode()); //公司代码
            importParam.setValue("ES_GJAHR", year); //会计年度
            importParam.setValue("ES_PERAF", StringUtils.leftPad(String.valueOf(month), 3, "0")); //折旧计算期（获取月份格式为001-012）（String.valueOf(month)
            JCoParameterList t2 = monthlyDepreciation.getTableParameterList();
            monthlyDepreciation.execute(destination);
            JCoParameterList outputParam = monthlyDepreciation.getExportParameterList();
            String r1 = outputParam.getString("ES_SUBRC");
            String r2 = outputParam.getString("ES_MSG");
            if ("S".equals(r1)) {
                JCoTable etAnlp = t2.getTable("ET_ANLP");
                int n = etAnlp.getNumRows();
                if (n == 0) {
                    jsonMap.put("monthlyDepreciation1", 0);        //月折旧额
                    jsonMap.put("warn", month + "月份期间无折旧额信息!" + r2);    //没有折旧额提示消息
                    etAnlp.appendRow();
                }
                etAnlp.setRow(0);    //取折旧额表的
                BigDecimal nafaz = etAnlp.getBigDecimal("NAFAZ");     //	记帐的正常折旧
//			    BigDecimal aafag = new BigDecimal(etAnlp.getString("AAFAG"));	//	有关年的计划外折旧记帐
                BigDecimal safag = etAnlp.getBigDecimal("SAFAG");    //	在当前财会年度中的记帐的特别折旧
                BigDecimal monthlyDepreciation1 = nafaz.add(safag); //月折旧额（aafag.add
                BigDecimal invzm = etAnlp.getBigDecimal("INVZM");    //	有关此年的投资授权
                BigDecimal nafag = etAnlp.getBigDecimal("NAFAG");    //	记帐在当前年的正常折旧
//			    jsonMap.put("invzm", invzm);	//累计折旧
//			    jsonMap.put("nafag", nafag);	//本年已计提累计折旧
                jsonMap.put("accumDepr", invzm); //累计折旧-old_new
                System.out.println("累计折旧" + invzm);
                jsonMap.put("deprCurrYear", nafag); //本年已计提累计折旧--old_new
                System.out.println("本年已计提累计折旧" + nafag);
                jsonMap.put("monthlyDepreciation1", monthlyDepreciation1);    //月折旧额
                System.out.println("月折旧额" + monthlyDepreciation1);
                jsonMap.put("currentApc", currentApc == null ? new BigDecimal("0.00") : currentApc); //资产原值
                System.out.println("资产原值" + (currentApc == null ? new BigDecimal("0.00") : currentApc));
                jsonMap.put("currentNbv", currentApc == null ? new BigDecimal("0.00") : currentApc.add(invzm)); //资产净值	=	 资产原值	- 累计折旧
                System.out.println("资产净值" + jsonMap.getString("currentNbv"));
            }
            if (postinginformation.getNumRows() == 0) {
                postinginformation.appendRow();        //判断无结果的返回就添加空行，便于取数据（金额等 如：0.00
            }
            postinginformation.setRow(0);
            String initialAcq = postinginformation.getString("INITIAL_ACQ");  //实际验收日期（资本化日期）
            String capDate = postinginformation.getString("CAP_DATE");  //入账日期
            Date deactDate = postinginformation.getDate("DEACT_DATE");  //不激活的日期
            String costcenter = timeDependentData.getString("COSTCENTER");  //成本中心
//			jsonMap.put("accumDepr", accumDepr); //累计折旧--old
//			jsonMap.put("deprCurrYear", deprCurrYear); //本年已计提累计折旧--old
            jsonMap.put("initialAcq", initialAcq);    //	实际验收日期
            jsonMap.put("capDate", capDate);    //	购置日期（入账日期）
            jsonMap.put("costcenter", costcenter);    //成本中心
            jsonMap.put("deprBeginDate", deprBeginDate); //折旧开始日期
            jsonMap.put("deactDate", null == deactDate ? "" : deactDate); //不激活的日期----（报废或者转移的时候非空）
            return jsonMap;
        } catch (JCoException e) {
            e.printStackTrace();
            LOGGER.error("readAssetsCodeFromSAPException", e.getMessage());
        }
        return jsonMap;
    }


    /**
     * 获得采购订单接口
     *
     * @param code
     * @param ipPoheader1Model
     * @param ipHistoryModel
     * @param itZmm0004Model
     * @return
     */
    @Override
    public JSONObject createPurchaseOrderFromSAP(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchaseOrderDetails) {
        JSONObject jsonMap = new JSONObject();
        String sapNo = purchaseOrderSapDAO.selectExists(purchaseOrder.getCdocument());
        jsonMap.put("error", "");    //默认为空字符串
        if (this.notEmpty(sapNo)) {
            jsonMap.put("error", "此采购订单已经传sap并返回过sap采购凭证号，不能再次调用接口生成凭证号，已存在的凭证号为：" + sapNo);    //默认为空字符串
            return jsonMap;
        }

        for (int i = 0; i < purchaseOrderDetails.size(); i++) {
            PurchaseOrderDetail detail = purchaseOrderDetails.get(i);
            String[] subNumber = detail.getSecondary().split("-");
            int subNum = Integer.parseInt(subNumber[0]);
            for (int j = 0; j < detail.getApplyCount(); j++) {
                EtZmm0012 em12 = new EtZmm0012();
                em12.setAssetNo(StringUtils.leftPad(detail.getMainAssetCode(), 12, "0"));
                em12.setSubNumber(StringUtils.leftPad(String.valueOf(subNum), 4, "0"));    //资产次级编号
                em12.setPurchaseId(purchaseOrder.getCdocument());
                em12.setPurchaseDetailId(String.valueOf(detail.getId()));
                purchaseOrderSapDAO.save(em12);
                subNum++;
            }
        }

	        /*读取采购订单接口*/
        JCoFunction functionV1;
        StringBuilder sb = new StringBuilder("");
        try {
            functionV1 = destination.getRepository().getFunction("ZRFC_MM_ME21N_V1");
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT"); //事务方法
            JCoParameterList importParamList = functionV1.getImportParameterList();
            JCoStructure ipPoheader1 = importParamList.getStructure("IP_POHEADER1");
            ipPoheader1.setValue("COMP_CODE", purchaseOrder.getCompanyCode()); //公司代码	是
            ipPoheader1.setValue("DOC_TYPE", purchaseOrder.getOrderType()); //采购凭证类型	是ZAB/ZFB
            ipPoheader1.setValue("VENDOR", purchaseOrder.getSupplierCode()); //供应商编码	是
            ipPoheader1.setValue("PURCH_ORG", purchaseOrder.getProcurementOrganization()); //采购组织	是
            ipPoheader1.setValue("PUR_GROUP", purchaseOrder.getPurchasingGroup()); //采购组	是
            ipPoheader1.setValue("PMNTTRMS", purchaseOrder.getPaymentTypeCode()); //收付条件代码	是
            ipPoheader1.setValue("HEADR_TEXT", purchaseOrder.getHeaderTextDescription()); //	表头文本	是
            ipPoheader1.setValue("NAME1", purchaseOrder.getCompanyName()); //公司名称	是
            ipPoheader1.setValue("CITY", purchaseOrder.getCity()); //城市
            ipPoheader1.setValue("COUNTRY", purchaseOrder.getCountry()); //国家代码	是
                /*ipPoheader1.setValue("STREET_LNG", "敦化路"); //	街道	是
                ipPoheader1.setValue("HOUSE_NO", "1111"); //门牌号
	        	ipPoheader1.setValue("POSTL_COD1", "266000"); //	城市邮政编码	
	        	ipPoheader1.setValue("TEL1_NUMBR", ipPoheader1Model.getTel1Numbr()); //	第一个电话号码: 拨号 + 编号	
	        	ipPoheader1.setValue("TEL1_EXT", ipPoheader1Model.getTel1Ext()); //第一个电话号码:分机号	
	        	ipPoheader1.setValue("SALES_PERS", ipPoheader1Model.getSalesPers()); //	供应商办公室的责任销售员	
				ipPoheader1.setValue("UNSEZ", "0"); //我方参考	是!!??
	        	ipPoheader1.setValue("COLLECT_NO", ipPoheader1Model.getCollectNo()); //汇总号	*/
            ipPoheader1.setValue("TELEPHONE", purchaseOrder.getTelephone()); //供应商电话号码
            ipPoheader1.setValue("CURRENCY", purchaseOrder.getCurrencyCode()); //货币代码	是

            String[] notNull = {"COMP_CODE", "DOC_TYPE", "VENDOR", "PURCH_ORG", "PUR_GROUP", "PMNTTRMS", "NAME1", "COUNTRY", "CURRENCY"};
            String[] warn = {"公司代码", "采购凭证类型", "供应商帐户号", "采购组织", "采购组", "收付条件代码", "公司名称", "国家代码", "货币代码"};
            if (purchaseOrder.getSupplierCode().length() == 1) {
                sb.append(this.isNull(ipPoheader1.getString("CITY"), "城市(当前为一次性供应商，需要填写)"));
            }
            for (int i = 0; i < notNull.length; i++) {
                sb.append(this.isNull(ipPoheader1.getString(notNull[i]), warn[i]));
            }
            importParamList.setValue("IP_POHEADER1", ipPoheader1);

            JCoStructure ipHistory = importParamList.getStructure("IP_HISTORY");
            //ipHistory.setValue("MANDT", "200"); //客户端	是否必需
            //ipHistory.setValue("SAPNO", ipHistoryModel.getSapno()); //SAP操作对象(销售订单/交货订单/物料主数据/客户主数据/供应商主
            //ipHistory.setValue("MBLNR", purchaseOrder.getApplyUser()); //写入SAP产生的凭证
            //ipHistory.setValue("ZDATE", ipHistoryModel.getZdate() == null || "".equals(ipHistoryModel.getZdate()) ? "" : ipHistoryModel.getZdate().replaceAll("-", "")); //日期
            //ipHistory.setValue("ZTIME", ipHistoryModel.getZtime() == null || "".equals(ipHistoryModel.getZtime()) ? "" : ipHistoryModel.getZtime().replaceAll(":", "")); //时间
            //ipHistory.setValue("RFC", ipHistoryModel.getRfc()); //SAP函数名
            //ipHistory.setValue("SAPID", ipHistoryModel.getSapid()); //SAP操作人ID
            ipHistory.setValue("OANO", purchaseOrder.getApplyUserCode()); //OA UNID	是
            ipHistory.setValue("OANAME", purchaseOrder.getApplyUser()); //OA操作人姓名	是
            ipHistory.setValue("OAMODULE", OAMODULE); //OA模块名	是
            ipHistory.setValue("OAID", purchaseOrder.getApplyUserCode()); //OA操作人ID	是!!??
            ipHistory.setValue("COMPANY", purchaseOrder.getCompanyName()); //公司名称	是
            ipHistory.setValue("DEPT", purchaseOrder.getBudgetDepartmentName()); //业务部门描述	是
            ipHistory.setValue("REMARK", purchaseOrder.getBuyerRemark()); //说明	是??!
            //必填参数验证
            String[] notNull3 = {"OANO", "OANAME", "COMPANY", "DEPT"};
            String[] warn3 = {"申请人编码（OA UNID）", "申请人姓名(OA操作人姓名)", "公司名称", "部门名称（业务部门描述）"};
            for (int i = 0; i < notNull3.length; i++) {
                sb.append(this.isNull(ipHistory.getString(notNull3[i]), warn3[i]));
            }
            if (!"".equals(sb.toString())) {
                jsonMap.put("error", sb.append("不能为空").toString());
                return jsonMap;
            }
            importParamList.setValue("IP_HISTORY", ipHistory);
            JCoParameterList tableParamList = functionV1.getTableParameterList(); //.setValue("BF_ANLKL", "-1");

            JCoTable itZmm0004 = tableParamList.getTable("IT_ZMM0004");
            int num = 0;
            for (int i = 0; i < purchaseOrderDetails.size(); i++) {
                PurchaseOrderDetail purchaseOrderDetail = purchaseOrderDetails.get(i);
                String[] subNumber = purchaseOrderDetail.getSecondary().split("-");
                if ("ZAB".equals(purchaseOrder.getOrderType())) {    //ZAB资产类型必须传主次级编号
                    sb.append(this.isNull(purchaseOrderDetail.getMainAssetCode(), "主资产号"));
                    if (subNumber.length != 2) {
                        jsonMap.put("error", sb.append(i + 1).append("行的").append("次级编码区间有问题").toString());
                        return jsonMap;
                    }
                }
                sb.append(this.isNull(purchaseOrderDetail.getApplyCount(), "申请数量不能为空"));
                sb.append(this.isNull(purchaseOrderDetail.getPurchaseUnitPrice(), "单价不能为空"));
                if (purchaseOrderDetail.getApplyCount() <= 0) {
                    jsonMap.put("error", sb.append(i + 1).append("行的").append("申请数量应大于0").toString());
                    return jsonMap;
                }
                if (!"".equals(sb.toString())) {
                    jsonMap.put("error", sb.append("不能为空").toString());
                    return jsonMap;
                }
                int length = Integer.parseInt(subNumber[1]) - Integer.parseInt(subNumber[0]) + 1; //根据次级获取采购数量，用来判断是否对应
                if (length != purchaseOrderDetail.getApplyCount()) {
                    jsonMap.put("error", "次级编码数量和申请数量不一致");
                    return jsonMap;
                }
                int subNum = Integer.parseInt(subNumber[0]);
                for (int j = 0; j < purchaseOrderDetail.getApplyCount(); j++) {
                    itZmm0004.appendRow();
                    itZmm0004.setRow(num);
                    num++;
                    int itemNo = num;
                    itZmm0004.setValue("PO_ITEM", itemNo); //采购凭证的项目编号	是
//			            purchaseOrderDetail.setItemNo(itemNo);
                    itZmm0004.setValue("SHORT_TEXT", purchaseOrderDetail.getAssetsName()); //	短文本	是此资产明细的资产名称
                    //itZmm0004.setValue("MATERIAL", purchaseOrderDetail.getMaterial()); //物料号	是
                    itZmm0004.setValue("PLANT", purchaseOrder.getFactoryCode()); //工厂	是
                    //itZmm0004.setValue("STGE_LOC", purchaseOrderDetail.getStgeLoc()); //库存地点	是
                    itZmm0004.setValue("PREQ_NAME", purchaseOrderDetail.getApplyUser()); //需求者/要求者名称	是
                    itZmm0004.setValue("MATL_GROUP", "AM000004"); //	物料组	是 (固定值)
                    itZmm0004.setValue("QUANTITY", new BigDecimal(1)); //采购订单数量	是   已经拆为单个的 ，所以是1
                    itZmm0004.setValue("PO_UNIT", purchaseOrderDetail.getUnits()); //采购订单单位	是 ??!!	（件，套 等，必须是已有的，数字不可以
                    itZmm0004.setValue("ITEM_CAT", "ZAB".equals(purchaseOrder.getOrderType()) ? "W" : null); //采购凭证中的项目类别	是
                    itZmm0004.setValue("ACCTASSCAT", "ZAB".equals(purchaseOrder.getOrderType()) ? "A" : "K"); //	科目设置类型	是
                    itZmm0004.setValue("TAX_CODE", purchaseOrder.getTaxRateCode()); //销售/购买税代码	是
                    itZmm0004.setValue("DELIVERY_DATE", this.dateToString(purchaseOrderDetail.getRequirementsDate())); //交货日期	是
                    itZmm0004.setValue("GL_ACCOUNT", purchaseOrderDetail.getLedgerAccount());    //"5050100106"); //	总帐科目编号	  不填报错
                    itZmm0004.setValue("COSTCENTER", "ZFB".equals(purchaseOrder.getOrderType()) ? purchaseOrder.getCostCenterCode() : null); //	成本中心	是
                    itZmm0004.setValue("ASSET_NO", "ZAB".equals(purchaseOrder.getOrderType()) ? purchaseOrderDetail.getMainAssetCode() : ""); //主资产号	是 ??!!
                    itZmm0004.setValue("SUB_NUMBER", "ZAB".equals(purchaseOrder.getOrderType()) ? StringUtils.leftPad(String.valueOf(subNum), 4, "0") : ""); //	资产次级编号	是
                    itZmm0004.setValue("COND_TYPE", "PBXX"); //条件类型	是(固定值)
                    itZmm0004.setValue("COND_UNIT", "1"); //条件单价	是(固定值)
                    itZmm0004.setValue("COND_P_UNT", "ZAB".equals(purchaseOrder.getOrderType()) ? "1" : ("ZFB".equals(purchaseOrder.getOrderType()) ? "1000" : null)); //	条件定价单位	是(固定值)
//			            itZmm0004.setValue("ITEM_TEXT", ""); //行项目文本	是??!!
                    itZmm0004.setValue("COND_VALUE", purchaseOrderDetail.getNetPrice().divide(new BigDecimal(purchaseOrderDetail.getApplyCount()), 2, RoundingMode.HALF_UP).multiply(purchaseOrder.getExchangeRate())); //	定价比率（不带税的净外币单价）	是
                    itZmm0004.setValue("ORDERID", StringUtils.leftPad(purchaseOrder.getCinternalordercode(), 12, "0")); //内部订单号

                    String[] notNull2 = {"SHORT_TEXT", "PLANT", "TAX_CODE", "DELIVERY_DATE", "GL_ACCOUNT"};
                    String[] warn2 = {"资产名（短文本）", "工厂", "销售/购买税代码", "需求日期(交货日期)", "总帐科目编号"};
                    for (int i2 = 0; i2 < notNull2.length; i2++) {
                        sb.append(this.isNull(itZmm0004.getString(notNull2[i2]), warn2[i2]));
                    }
                    if (!"".equals(sb.toString())) {
                        jsonMap.put("error", sb.append("不能为空").toString());
                        return jsonMap;
                    }
                    subNum++;
                    //itZmm0004.setValue("BEDNR", itZmm0004Model.getBednr()); //需求跟踪号
                    //itZmm0004.setValue("PROFIT_CTR", itZmm0004Model.getProfitCtr()); //利润中心
                }
            }
            PurchaseCommonLogger purchaseCommonLogger = new PurchaseCommonLogger();
            purchaseCommonLogger.setUserCode(purchaseOrder.getBuyerCode());
            purchaseCommonLogger.setDocument(purchaseOrder.getCdocument());
            purchaseCommonLogger.setLogger("采购订单传sap开始");
            purchaseCommonLoggerDAO.savePurchaseCommLogger(purchaseCommonLogger);
            JCoContext.begin(destination);
            functionV1.execute(destination);
            //提交事务
            functionC.execute(destination);
            JCoContext.end(destination);
            purchaseCommonLogger.setLogger("采购订单传sap结束");
            purchaseCommonLoggerDAO.savePurchaseCommLogger(purchaseCommonLogger);
            //删除插入的订单信息
            for (int i = 0; i < purchaseOrderDetails.size(); i++) {
                PurchaseOrderDetail detail = purchaseOrderDetails.get(i);
                String[] subNumber = detail.getSecondary().split("-");
                int subNum = Integer.parseInt(subNumber[0]);
                for (int j = 0; j < detail.getApplyCount(); j++) {
                    EtZmm0012 em12 = new EtZmm0012();
                    em12.setAssetNo(StringUtils.leftPad(detail.getMainAssetCode(), 12, "0"));
                    em12.setSubNumber(StringUtils.leftPad(String.valueOf(subNum), 4, "0"));    //资产次级编号
                    em12.setPurchaseId(purchaseOrder.getCdocument());
                    em12.setPurchaseDetailId(String.valueOf(detail.getId()));
                    purchaseOrderSapDAO.delete(em12);
                    subNum++;
                }
            }
            JCoTable etLog = functionV1.getTableParameterList().getTable("ET_LOG");
            StringBuilder sbWarn = new StringBuilder("");
            for (int i = 0; i < etLog.getNumRows(); i++) {
                etLog.setRow(i);
                System.out.println(etLog.getValue("ROW").toString());
                System.out.println(etLog.getValue("FIELD").toString());
                System.out.println(etLog.getValue("PARAMETER").toString());
                System.out.println(etLog.getValue("TYPE").toString() + "||" + etLog.getValue("MESSAGE").toString());
                String type = etLog.getString("TYPE");
                if ("E".equals(type)) {
                    sb.append("参数错误：").append(etLog.getString("PARAMETER")).append(";").append(etLog.getString("FIELD")).append(".(").append(etLog.getString("MESSAGE")).append(")");
                    jsonMap.put("error", sb.toString());
                    //} //else if ("W".equals(etLog.getValue("TYPE").toString())) {
                    //以后需要返回警告可以加上代码
                } else {

                    sbWarn.append(etLog.getString("PARAMETER")).append(";").append(etLog.getString("FIELD")).append(".(").append(etLog.getString("MESSAGE")).append(")");
                    jsonMap.put("warn", sbWarn.toString());
                    //} //else if ("W".equals(etLog.getValue("TYPE").toString())) {
                    //以后需要返回警告可以加上代码
                }
            }
            JCoTable etZmm0012 = tableParamList.getTable("ET_ZMM0012");
//	            etZmm0012.appendRow();
            if (etZmm0012.getNumRows() > 0) {
                try {
                    String ebeln = etZmm0012.getString("EBELN");    //采购凭证号
                    Integer ebelp = etZmm0012.getInt("EBELP");    //	采购凭证的项目编号	只返回了最小的
                    String matnr = etZmm0012.getString("MATNR");    //	物料号
                    BigDecimal menge = etZmm0012.getBigDecimal("MENGE"); // 采购订单数量
                    String meins = etZmm0012.getString("MEINS");    //	订单单位
                    String sakto = etZmm0012.getString("SAKTO");    //	总帐科目编号
                    jsonMap.put("ebeln", ebeln); //采购凭证号
                    jsonMap.put("ebelp", ebelp); //	采购凭证的项目编号
                    jsonMap.put("matnr", matnr); //	物料号
                    jsonMap.put("menge", menge); //	采购订单数量
                    jsonMap.put("meins", meins); //	订单单位
                    jsonMap.put("sakto", sakto); //	总帐科目编号
//		            	jsonMap.put("detail", purchaseOrderDetails); //	总帐科目编号	
                    //jsonMap.put("code", code); //	编码,前台传入
                    try {
                        EtZmm0012 em12 = new EtZmm0012();
                        em12.setEbeln(ebeln); // 采购凭证号
                        em12.setEbelp(ebelp); // 采购凭证的项目编号
                        em12.setMatnr(matnr); // 物料号
                        em12.setMenge(menge); // 采购订单数量
                        em12.setMeins(meins); // 订单单位
                        em12.setSakto(sakto); // 总帐科目编号
                        //em12.setCode(code);
//						em12.setEtLog(res);
                        em12.setOperateUser(purchaseOrder.getApplyUser() + "(" + purchaseOrder.getApplyUserCode() + ")");
                        em12.setOperateDate(new Date());
                        int n = 0;
                        for (int i = 0; i < purchaseOrderDetails.size(); i++) {
                            PurchaseOrderDetail detail = purchaseOrderDetails.get(i);
                            String[] subNumber = detail.getSecondary().split("-");
                            int subNum = Integer.parseInt(subNumber[0]);
                            if (this.notEmpty(ebeln)) {
                                for (int j = 0; j < detail.getApplyCount(); j++) {
                                    //				    			itZmm0004.setRow(num);
                                    em12.setEbelp(ebelp + n * 10);
                                    em12.setAssetNo(StringUtils.leftPad(detail.getMainAssetCode(), 12, "0"));
                                    em12.setSubNumber(StringUtils.leftPad(String.valueOf(subNum), 4, "0"));    //资产次级编号
                                    em12.setPurchaseId(purchaseOrder.getCdocument());
                                    em12.setPurchaseDetailId(String.valueOf(detail.getId()));    //订单行号??!!
                                    em12.setCompCode(purchaseOrder.getCompanyCode());
                                    //				    			em12.setDocType(purchaseOrder.getOrderType());
                                    purchaseOrderSapDAO.save(em12);

                                    PurchaseCommon common = new PurchaseCommon();
                                    common.setPurchaseId(purchaseOrder.getCdocument());
                                    common.setPurchaseDetailId(String.valueOf(detail.getId()));
                                    common.setSapMainAssetCode(em12.getAssetNo());
                                    common.setSapSecAssetCode(em12.getSubNumber());
                                    common.setSapAssetsCode(em12.getAssetNo() + "-" + em12.getSubNumber());
                                    common.setAssetsName(detail.getAssetsName());
                                    common.setAssetsType(purchaseOrder.getAssetType());
                                    common.setOrderMoney(detail.getPurchaseUnitPrice());
                                    itZmm0004.setRow(n);
                                    BigDecimal oneNetP = purchaseOrderDetails.get(i).getPurchaseUnitPrice().divide(new BigDecimal(1 + purchaseOrder.getTaxRate()), 2, BigDecimal.ROUND_HALF_UP);    //不含税的净  币单价
                                    common.setOrderNetMoney(oneNetP);    //单个的净价
                                    common.setCompanyCode(purchaseOrder.getCompanyCode());
                                    common.setPurchaseOrderSapNo(ebeln);
                                    common.setItemNo(ebelp + n * 10);
                                    common.setReceiveStatus("N");
                                    purchaseCommonDAO.delPurchaseCommonBeforeSap(common);
                                    purchaseCommonDAO.savePurchaseCommon(common);

                                    n++;
                                    subNum++;
                                }
                            } else {
                                for (int j = 0; j < detail.getApplyCount(); j++) {
                                    //				    			itZmm0004.setRow(num);
                                    em12.setEbelp(ebelp + n * 10);
                                    em12.setAssetNo(StringUtils.leftPad(detail.getMainAssetCode(), 12, "0"));
                                    em12.setSubNumber(StringUtils.leftPad(String.valueOf(subNum), 4, "0"));    //资产次级编号
                                    em12.setPurchaseId(detail.getPurchaseOrderId());
                                    em12.setPurchaseDetailId(String.valueOf(detail.getId()));    //订单行号??!!
                                    em12.setCompCode(purchaseOrder.getCompanyCode());
                                    //				    			em12.setDocType(purchaseOrder.getOrderType());
                                    purchaseOrderSapDAO.save(em12);

                                    n++;
                                    subNum++;
                                }
                            }


                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        LOGGER.error("createPurchaseOrderFromSAPException", e.getMessage());
                        purchaseCommonLogger.setLogger("第一个catch" + e.getMessage());
                        purchaseCommonLoggerDAO.savePurchaseCommLogger(purchaseCommonLogger);
                        return jsonMap;

                    }
                    //etZmm0012Service.save(em12, itZmm0004Models);

                    return jsonMap;
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    jsonMap.put("error", new StringBuilder().append("出现异常：").append(e));
                    e.printStackTrace();
                    LOGGER.error("createPurchaseOrderFromSAPException", e.getMessage());
                    purchaseCommonLogger.setLogger("第二个catch" + e.getMessage());
                    purchaseCommonLoggerDAO.savePurchaseCommLogger(purchaseCommonLogger);
                }
            }
        } catch (JCoException e) {
            jsonMap.put("error", new StringBuilder("").append("出现异常：").append(e));
            LOGGER.error("createPurchaseOrderFromSAPException", e.getMessage());
            PurchaseCommonLogger purchaseCommonLogger = new PurchaseCommonLogger();
            purchaseCommonLogger.setUserCode(purchaseOrder.getBuyerCode());
            purchaseCommonLogger.setDocument(purchaseOrder.getCdocument());
            purchaseCommonLogger.setLogger("第三个catch" + e.getMessage());
            purchaseCommonLoggerDAO.savePurchaseCommLogger(purchaseCommonLogger);
        }
        return jsonMap;
    }


    /* 获取预付凭证接口
     * @see com.opple.fa.sap.service.SapUtilService#getPrePaymentVoucherFromSAP(com.opple.fa.purchase.model.PaymentOrderModel, java.util.List)
     */
    public JSONObject createPrePaymentVoucherFromSAP(PaymentOrderModel paymentOrderSAP, List<PaymentOrderDetailModel> paymentOrderDetailsSAP) {
        JSONObject jsonMap = new JSONObject();
        if (paymentOrderDetailsSAP.size() == 0) {
            jsonMap.put("error", "行项目不能为空");
            return jsonMap;
        }

        JCoFunction functionV1;
        StringBuilder s1 = new StringBuilder();    //结果说明
        Voucherpost voucherpost = new Voucherpost();

        String vouchcode = "";
        List<VoucherpostChild> voucherpostChilds = new ArrayList<VoucherpostChild>();
        List<BapiAccDocumentPost> bapiAccDocumentPosts = new ArrayList<BapiAccDocumentPost>();    //创建参数list;
        try {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);    //会计年度
            int month = cal.get(Calendar.MONTH) + 1;    //会计期间
            Date d = new Date();
            String date = this.dateToString(d);    //当前日期string类型
            functionV1 = destination.getRepository().getFunction("BAPI_ACC_DOCUMENT_POST");
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
            JCoParameterList importParamList = functionV1.getImportParameterList();
            JCoParameterList tableParamList = functionV1.getTableParameterList();
            JCoStructure documentHeader = importParamList.getStructure("DOCUMENTHEADER");
            //总账表
            //JCoTable bapiAccountgl = tableParamList.getTable("ACCOUNTGL");	//总帐科目项
            JCoTable currencyamount = tableParamList.getTable("CURRENCYAMOUNT");    //货币项目
            JCoTable extension2 = tableParamList.getTable("EXTENSION2");    //BAPI 参数 ExtensionIn/ExtensionOut 的参考结构

            JCoTable accountpayable = tableParamList.getTable("ACCOUNTPAYABLE");   /////新增

            int size = paymentOrderDetailsSAP.size();
            BigDecimal pricePay = new BigDecimal(0);
            String valuePart1 = ""; //借贷方向
            String rc = "29"; //代表正的
            PaymentOrderDetailModel paymentOrderDetailSAP;
            BigDecimal price;
            Long itemNo = 10L;    //行项目初始化
            String sag = "A"; //特别总账标记"SA".equals(paymentOrderSAP.getDocType()) ? "A" : " ";

            StringBuilder sb = new StringBuilder("");
            String itemNoString;
            for (int i = 0; i <= paymentOrderDetailsSAP.size(); i++) {
                if (i == size) {

                    paymentOrderDetailSAP = paymentOrderDetailsSAP.get(size - 1);
                    itemNo = itemNo + 10;
                    pricePay = pricePay.multiply(new BigDecimal(-1));
                    price = pricePay;
                    rc = "39";
//            		sag = " ";
                } else {
                    paymentOrderDetailSAP = paymentOrderDetailsSAP.get(i);
                    itemNo = 10L;    //paymentOrderDetailSAP.getItemnoAcc();  因为当前系统MM只有一个供应商对应，所以行项目只有10,20两个
                    price = paymentOrderDetailSAP.getPayPrice();
                    pricePay = pricePay.add(price);    //计算预付总金额
                }
                if (i >= paymentOrderDetailsSAP.size() - 1) {
                    //保存结果
                    VoucherpostChild voucherpostChild = new VoucherpostChild();
                    String cadvdocuments = paymentOrderSAP.getCdocument() + itemNo;    //预制凭证号
                    itemNoString = StringUtils.leftPad(String.valueOf(itemNo), 10, "0");
                    voucherpostChild.setCkey(this.dateTimeToString(d) + cadvdocuments);
                    voucherpostChild.setCdocuments(paymentOrderSAP.getCdocument());
                    voucherpostChild.setCadvdocuments(cadvdocuments);
                    voucherpostChild.setCtype(paymentOrderSAP.getDocType());    //凭证类型(固定值)
                    voucherpostChild.setCrowitemno(itemNoString);
                    voucherpostChild.setCdocumentsgroup("ZRSTRG");    //BAPI 表扩展的结构名称-固定此值
                    voucherpostChild.setCsubjectcode(GLACCOUNT);    //
                    voucherpostChild.setCsubjectname(GLACCOUNT);    //
                    valuePart1 = StringUtils.leftPad(String.valueOf(itemNo), 6, "0") + sag + rc;
                    voucherpostChild.setCdebitcreditpath(valuePart1);
                    voucherpostChild.setCaccountcode(rc);
                    voucherpostChild.setCspcode(sag);
                    voucherpostChild.setCrowitemtext(this.notEmpty(paymentOrderSAP.getApplyFor()) ? paymentOrderSAP.getApplyFor() : "无");    //不能为空
                    voucherpostChild.setCsuppliercode(paymentOrderSAP.getSupplierCode());
                    voucherpostChild.setCusercode(paymentOrderSAP.getAllocNumber());    //用户对应的供应商编码
                    voucherpostChild.setCusername(paymentOrderSAP.getAllocNumber());    //用户对应的供应商

                    //voucherpostChild.setCdepcode(StringUtils.leftPad(paymentOrderSAP.getCostCenterCode(), 10, "0"));

                    String regex = ".*[a-zA-Z]+.*";
                    Matcher m = Pattern.compile(regex).matcher(paymentOrderSAP.getCostCenterCode());
                    if (!m.matches()) {
                        voucherpostChild.setCdepcode(StringUtils.leftPad(paymentOrderSAP.getCostCenterCode(), 10, "0"));
                    } else {
                        voucherpostChild.setCdepcode(paymentOrderSAP.getCostCenterCode());
                    }

                    voucherpostChild.setCdepname(voucherpostChild.getCdepcode());
//		            voucherpostChild.setCordercode(paymentOrderSAP.getPurchaseOrderId());
                    voucherpostChild.setCsettiementcode(paymentOrderSAP.getPaymentType());
//		            voucherpostChild.setCsettiementname(paymentOrderSAP.getPaymentType());
                    voucherpostChild.setCompCode(paymentOrderSAP.getCompanyCode());
                    voucherpostChild.setPstngDate(d);    //凭证中的记帐日期
//	            	voucherpostChild.setCprofitcenter(paymentOrderSAP.getProfitCenter());
                    //voucherpostChild.setDrepayment(d);
                    if (i < size) {
                        voucherpostChild.setDrepayment(paymentOrderDetailsSAP.get(i).getNextPayment()); /////////------------修改下次核销日期-------------------
                    }
                    voucherpostChild.setBautoqz("N");
                    voucherpostChild.setCcurrencycode(paymentOrderSAP.getCurrencyCode());
                    voucherpostChild.setIexchangerate(paymentOrderSAP.getExchangeRate());
//	            	paymentOrderSAP.setcu
                    voucherpostChild.setIamount(pricePay);    //凭证货币金额
                    voucherpostChild.setIamountyb(pricePay);    //原币
                    voucherpostChild.setCprofitcenter(paymentOrderSAP.getProfitCenter());
                    voucherpostChild.setIyear(String.valueOf(year));
                    voucherpostChild.setCstatus("Y");
                    voucherpostChild.setCdocumentsno(String.valueOf(itemNo / 10));
                    voucherpostChild.setCisbefore("N");
                    voucherpostChild.setBqingzhang(i == size ? "0" : "1");    //是否需要清帐:0不清账，1清账，2不清账
                    /*voucherpostChild.set(String.valueOf(month));
                    voucherpostChild.setCostCenterCode(StringUtils.leftPad(paymentOrderSAP.getCostCenterCode(), 10, "0"));
	            	voucherpostChild.setProfitCenter("000000" + paymentOrderSAP.getProfitCenter());
	            	voucherpostChild.setExchRate(paymentOrderSAP.getExchangeRate());
	            	voucherpostChild.setBusAct("RFBU");
	            	voucherpostChilds.add(voucherpostChild);*/

                    voucherpostChilds.add(voucherpostChild);

                    voucherpost.setOperateDate(new Date());
                    voucherpost.setOperateUser(paymentOrderSAP.getApplyUserCode());
                    voucherpost.setCadvdocuments(paymentOrderSAP.getCdocument());
                    voucherpost.setCdocuments(paymentOrderSAP.getCdocument());
                    voucherpost.setCtype(paymentOrderSAP.getDocType());    //凭证类型
                    voucherpost.setDpassdate(d);
                    voucherpost.setIyear(year);
                    voucherpost.setCdocumentsno("无");
                    voucherpost.setCdocumentsgroup("无");
                    voucherpost.setCpassusercode(paymentOrderSAP.getApplyUserCode());
                    voucherpost.setCpassusername(paymentOrderSAP.getApplyUser());
                    voucherpost.setImonth(month);
                    voucherpost.setCvouchersysno(paymentOrderSAP.getDocType() + this.dateToString(d));
                    voucherpost.setDvoucherdate(d);
                    voucherpost.setCcompanycode(paymentOrderSAP.getCompanyCode());
                    voucherpost.setCcompanyname(paymentOrderSAP.getCompanyName());
                    voucherpost.setCvoucherclass("RFBU");    //业务事务
                    voucherpost.setCvouchertypecode(paymentOrderSAP.getDocType());
                    voucherpost.setCvouchertypename(paymentOrderSAP.getDocType());
                    voucherpost.setIcount(0);
                    voucherpost.setCvouchertitle(this.notEmpty(paymentOrderSAP.getApplyFor()) ? paymentOrderSAP.getApplyFor() : "无");    //不能为空
                    voucherpost.setCcurrencycode(paymentOrderSAP.getCurrencyCode());
//		        	voucherpost.setCcurrencycode(paymentOrderSAP.getCurrencyCode());
                    voucherpost.setCstatus("Y");
                    voucherpost.setCusercode(paymentOrderSAP.getApplyUserCode());    //用户对应的供应商编码
                    voucherpost.setCusername(paymentOrderSAP.getApplyUser());    //用户对应的供应商
                    voucherpost.setDcreatedate(d);
                    voucherpost.setClastusercode(paymentOrderSAP.getApplyUserCode());    //申请人
                    voucherpost.setClastusername(paymentOrderSAP.getApplyUser());    //申请人名称
                    voucherpost.setDlastdate(d);
                    voucherpost.setIexchangerate(paymentOrderSAP.getExchangeRate());
                    if (i < size) {
                        voucherpostChild.setCcustomercode(paymentOrderSAP.getAllocNumber());
                    }
	            	/*bapiAccountgl.appendRow();
	            	bapiAccountgl.setRow(i);
	//            	bapiAccountgl.setValue("COSTCENTER", voucherpostChild.getCostCenterCode());
	            	bapiAccountgl.setValue("GL_ACCOUNT", voucherpostChild.getCsubjectcode());
	//            	bapiAccountgl.setValue("ACCT_TYPE", paymentOrderDetailSAP.getAcctType());
	            	bapiAccountgl.setValue("VENDOR_NO", voucherpostChild.getCsuppliercode());
	                bapiAccountgl.setValue("COMP_CODE", voucherpostChild.getCompCode());
	                bapiAccountgl.setValue("PSTNG_DATE", voucherpostChild.getPstngDate()); 
	                bapiAccountgl.setValue("ITEM_TEXT", voucherpostChild.getCrowitemtext());	//项目文本
	                //bapiAccountgl.setValue("BLINE_DATE", voucherpostChild.getDrepayment());	    //付款到期日 -----------------------------------------
	                if (i < size) {
	                	bapiAccountgl.setValue("ALLOC_NMBR", voucherpostChild.getCusercode());
	                }
	                bapiAccountgl.setValue("ITEMNO_ACC",  voucherpostChild.getCrowitemno()); 
	                bapiAccountgl.setValue("PROFIT_CTR", "000000" + voucherpostChild.getCprofitcenter());   //利润中心
	                bapiAccountgl.setValue("BUS_AREA", paymentOrderSAP.getProfitCenter());   //业务范围
	                String[] notNull = {"GL_ACCOUNT", "VENDOR_NO", "COMP_CODE", "PROFIT_CTR", "BUS_AREA" };
					String[] warn = {"总账科目", "供应商代码", "公司代码", "利润中心", "业务范围" };
					for (int j = 0; j < notNull.length; j++) {
						sb.append(this.isNull(bapiAccountgl.getString(notNull[j]), warn[j]));
					}*/

                    accountpayable.appendRow();
                    accountpayable.setRow(i);
                    accountpayable.setValue("GL_ACCOUNT", voucherpostChild.getCsubjectcode());
                    accountpayable.setValue("VENDOR_NO", voucherpostChild.getCsuppliercode());
                    accountpayable.setValue("COMP_CODE", voucherpostChild.getCompCode());
                    accountpayable.setValue("ITEM_TEXT", voucherpostChild.getCrowitemtext());
                    if (i < size) {
                        accountpayable.setValue("ALLOC_NMBR", voucherpostChild.getCusercode());
                    }
                    accountpayable.setValue("ITEMNO_ACC", voucherpostChild.getCrowitemno());
                    accountpayable.setValue("BLINE_DATE", voucherpostChild.getDrepayment());
                    accountpayable.setValue("BUS_AREA", paymentOrderSAP.getProfitCenter()); //业务范围  利润中心


                    currencyamount.appendRow();
                    currencyamount.setRow(i);
                    currencyamount.setValue("ITEMNO_ACC", voucherpostChild.getCrowitemno());    //会计凭证行项目编号	是
                    currencyamount.setValue("CURRENCY", voucherpostChild.getCcurrencycode());    //货币代码	是
                    currencyamount.setValue("AMT_DOCCUR", voucherpostChild.getIamount());    //凭证货币金额	是
                    currencyamount.setValue("EXCH_RATE", voucherpostChild.getIexchangerate());    //汇率
                    String[] notNull2 = {"ITEMNO_ACC", "CURRENCY", "AMT_DOCCUR", "EXCH_RATE"};
                    String[] warn2 = {"会计凭证行项目编号", "货币代码", "凭证货币金额", "汇率"};
                    for (int i2 = 0; i2 < notNull2.length; i2++) {
                        sb.append(this.isNull(currencyamount.getString(notNull2[i2]), warn2[i2]));
                    }
                    extension2.appendRow();
                    extension2.setRow(i);
                    extension2.setValue("STRUCTURE", voucherpostChild.getCdocumentsgroup());        //BAPI 表扩展的结构名称	是
                    extension2.setValue("VALUEPART1", voucherpostChild.getCdebitcreditpath());        //BAPI 扩展参数的数据部分	是
                }
            }

            documentHeader.setValue("DOC_TYPE", voucherpost.getCtype());        //凭证类型	是
            //documentHeader.setValue("USERNAME", paymentOrderSAP.getAllocNumber());		//用户名	是
            documentHeader.setValue("USERNAME", paymentOrderSAP.getLoginUserCode());        //用户名	是
            documentHeader.setValue("BUS_ACT", voucherpost.getCvoucherclass());        //业务事务	是
            documentHeader.setValue("COMP_CODE", voucherpost.getCcompanycode());        //公司代码	是
            documentHeader.setValue("FISC_YEAR", voucherpost.getIyear());        //会计年度
            documentHeader.setValue("DOC_DATE", date);        //凭证中的凭证日期	是
            documentHeader.setValue("PSTNG_DATE", date);        //凭证中的记帐日期	是
            documentHeader.setValue("FIS_PERIOD", month);        //会计期间
            documentHeader.setValue("REF_DOC_NO", voucherpost.getCdocuments());        //参考凭证编号	是
//        	documentHeader.setValue("HEADER_TXT", voucherpost);		//凭证抬头文本	


            String[] notNull3 = {"DOC_TYPE", "USERNAME", "COMP_CODE", "REF_DOC_NO"};
            String[] warn3 = {"凭证类型", "用户名(申请人)", "公司代码", "参考凭证编号(付款单号)"};
            for (int i3 = 0; i3 < notNull3.length; i3++) {
                sb.append(this.isNull(documentHeader.getString(notNull3[i3]), warn3[i3]));
            }
            if (!"".equals(sb.toString())) {
                jsonMap.put("error", sb.append("不能为空").toString());
                return jsonMap;
            }
            JCoContext.begin(destination);
            functionV1.execute(destination);  //提交调用BAPI_ACC_DOCUMENT_POST 写入凭证信息

            JCoTable etLog = functionV1.getTableParameterList().getTable("RETURN");
            for (int i = 0; i < etLog.getNumRows(); i++) {
                etLog.setRow(i);
                s1.append(etLog.getValue("MESSAGE").toString());
                System.out.println(etLog.getValue("MESSAGE").toString());
                System.out.println(etLog.getValue("ROW").toString());
                System.out.println(etLog.getValue("FIELD").toString());
                System.out.println(etLog.getValue("PARAMETER").toString());
                System.out.println(etLog.getValue("TYPE").toString() + etLog.getValue("MESSAGE").toString());
                s1.append(etLog.getString("MESSAGE"));
                boolean boo = true;
                if (boo && "S".equals(etLog.getValue("TYPE").toString()) && "RW".equals(etLog.getValue("ID").toString()) && "605".equals(etLog.getValue("NUMBER").toString())) {
                    boo = false;
                    //取开始位置 到 结束位置减开始位置长度的字符
                    voucherpost.setCpassstatus("Y");
                    vouchcode = s1.substring(s1.indexOf("BKPFF") + 6, s1.indexOf(String.valueOf(paymentOrderSAP.getCompanyCode()) + String.valueOf(year)));
                    voucherpost.setCpassfailedcause("创建成功");
                    for (int j = 0; j < voucherpostChilds.size(); j++) {
                        VoucherpostChild vs = voucherpostChilds.get(j);
                        vs.setCpassdocuments(vouchcode);
                        voucherpostChildSapDAO.save(vs);
                    }
                    voucherpost.setCpassdocuments(vouchcode);
                    voucherpostSapDAO.save(voucherpost);
                } else {
                    voucherpost.setCpassfailedcause(s1.toString());
                    voucherpost.setCpassstatus("N");
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
            jsonMap.put("error", s1.toString());
            jsonMap.put("prePaySapNo", vouchcode);    //凭证号   as:{"epBelnr":"0100037398"}
            functionC.execute(destination);    //提交调用BAPI_TRANSACTION_COMMIT 进行COMMIT一下
            JCoContext.end(destination);
            /*if ("SA".equals(paymentOrderSAP.getDocType())) {		//SA/KZ-预付，RE-验收
            	jsonMap.put("result", s1.toString());
            	return jsonMap;
            } else if ("RE".equals(paymentOrderSAP.getDocType())) {	
            	String billCode = paymentOrderSAP.getCdocument();	//核销流程单号
            	return clearVouch(paymentOrderSAP, paymentOrderDetailsSAP);
            }*/

        } catch (JCoException e) {
            for (int i = 0; i < bapiAccDocumentPosts.size(); i++) {
                BapiAccDocumentPost bapiAccDocumentPost = bapiAccDocumentPosts.get(i);
                bapiAccDocumentPost.setEpBelnr(vouchcode);
                boolean bool = "".equals(vouchcode);
                bapiAccDocumentPost.setEtLog(bool ? s1.toString() : "过账成功");    //只保存无法生成凭证号时候的错误信息
                bapiAccDocumentPost.setDoneFlag(bool ? "0" : "1");    //1-success 0-fail
                bapiAccDocumentPost.setRefDocNo(vouchcode);    //SAP回传的--会计凭证号码
                payDocumentSapDAO.save(bapiAccDocumentPost);

            }
            e.printStackTrace();
            LOGGER.error("createPrePaymentVoucherFromSAPException", e.getMessage());
            jsonMap.put("error", e.getMessage());
        }
        return jsonMap;
    }


    /**
     * 修改固定资产和固定资产调拨接口(同一公司内部调拨)
     *
     * @param readAssetsCodeModel
     * @param itInputModel
     * @param itInputModelipHistoryModel
     * @param ipAsset
     * @return
     */
    public JSONObject updateAssetsFromSAP(IpHistoryModel ipHistoryModel, IpInputModel ipInputModel, String ipAsset) {

        String user = destination.getUser() + this.dateTimeToString(new Date());
        JSONObject jsonMap = new JSONObject();
        StringBuilder sb = new StringBuilder("");
        /*读取资产编码接口*/
        JCoFunction functionDisplay;
        StringBuilder error = new StringBuilder("");
        try {
            functionDisplay = destination.getRepository().getFunction("ZRFC_AM_ASSET_CHANGE");
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
            JCoParameterList createImportParam = functionDisplay.getImportParameterList(); //.setValue("BF_ANLKL", "-1");
            createImportParam.setValue("IP_ASSET", StringUtils.leftPad(ipAsset, 12, "0"));    //主资产编号	是
            error.append(this.isNull(ipAsset, "主资产编号"));
            JCoParameterList tl = functionDisplay.getImportParameterList();
            JCoStructure ipInput = tl.getStructure("IP_INPUT");
            // ipInput.appendRow();
            // JCoStructure ipInput = createImportParam.getStructure("IP_INPUT");

//				ipInput.setValue("ASSETCLASS", ipInputModel.getAssetclass());	//资产分类
            ipInput.setValue("COMPANYCODE", ipInputModel.getCompanycode());    //公司代码
            ipInput.setValue("SUBNUMBER", StringUtils.leftPad(ipInputModel.getSubnumber(), 4, "0"));    //资产次级编号
            ipInput.setValue("SERIAL_NO", ipInputModel.getSerialNo());
            ipInput.setValue("NOTE", ipInputModel.getNote());
            ipInput.setValue("ODEP_START_DATE", ipInputModel.getOdepStartDate());    //折旧开始计算日期
            //以下为非必输的值
            ipInput.setValue("DESCRIPT", ipInputModel.getDescript());    //资产描述
            ipInput.setValue("QUANTITY", ipInputModel.getQuantity());    //数量
            ipInput.setValue("BASE_UOM", ipInputModel.getBaseUom());    //基本计量单位
            ipInput.setValue("AREA", ipInputModel.getArea()); //折旧范围

            String regex = ".*[a-zA-Z]+.*";
            Matcher m = Pattern.compile(regex).matcher(ipInputModel.getCostcenter());
            if (!m.matches()) {
                ipInput.setValue("COSTCENTER", StringUtils.leftPad(ipInputModel.getCostcenter(), 10, "0"));    //成本中心
            } else {
                ipInput.setValue("COSTCENTER", ipInputModel.getCostcenter());    //成本中心
            }

            ipInput.setValue("INVENT_NO", ipInputModel.getInventNo());    //库存号
            ipInput.setValue("DESCRIPT2", ipInputModel.getDescript2());
            ipInput.setValue("ACCT_DETRM", ipInputModel.getAcctDetrm());
            ipInput.setValue("HISTORY", ipInputModel.getHistory());
            ipInput.setValue("MAIN_DESCRIPT", ipInputModel.getMainDescript());
            ipInput.setValue("DATE", ipInputModel.getDate() == null || "".equals(ipInputModel.getDate()) ? "" : this.dateTimeToString(ipInputModel.getDate()));
            ipInput.setValue("INCLUDE_IN_LIST", ipInputModel.getIncludeInList());
            ipInput.setValue("DEACT_DATE", ipInputModel.getDeactDate() == null || "".equals(ipInputModel.getDeactDate()) ? "" : ipInputModel.getDeactDate().replaceAll("-", ""));
            ipInput.setValue("BUS_AREA", ipInputModel.getBusArea());
            ipInput.setValue("LOCATION", ipInputModel.getLocation());
            ipInput.setValue("ROOM", ipInputModel.getRoom());
            ipInput.setValue("EVALGROUP1", ipInputModel.getEvalgroup1());
            ipInput.setValue("ZFLAG", "B");
            String[] notNull = {"COMPANYCODE", "SUBNUMBER"};
            String[] warn = {"公司代码", "次级编码"};
            for (int i = 0; i < notNull.length; i++) {
                error.append(this.isNull(ipInput.getString(notNull[i]), warn[i]));
            }
            //表名：IP_HISTORY ,最后“是”为是否必输
            JCoStructure ipHistory = createImportParam.getStructure("IP_HISTORY");
            ipHistory.setValue("MANDT", ipHistoryModel.getMandt());        //客户端
            ipHistory.setValue("OANO", UUID.randomUUID().toString());        //OA UNID	是
            ipHistory.setValue("SAPNO", ipHistoryModel.getSapno());        //SAP操作对象(销售订单/交货订单/物料主数据/客户主数据/供应商主
            ipHistory.setValue("MBLNR", ipHistoryModel.getMblnr());        //写入SAP产生的凭证
            ipHistory.setValue("ZDATE", ipHistoryModel.getZdate() == null || "".equals(ipHistoryModel.getZdate()) ? "" : ipHistoryModel.getZdate().replaceAll("-", ""));        //日期
            ipHistory.setValue("ZTIME", ipHistoryModel.getZtime() == null || "".equals(ipHistoryModel.getZtime()) ? "" : ipHistoryModel.getZtime().replaceAll(":", ""));        //时间
            ipHistory.setValue("OANAME", user);    //ipHistoryModel.getOaname());		//OA操作人姓名	是
            ipHistory.setValue("OAMODULE", OAMODULE);        //OA模块名	是  ？？！！(固定资产MRMS?)
            ipHistory.setValue("RFC", ipHistoryModel.getRfc());        //SAP函数名
//				ipHistory.setValue("OAID", user);	//ipHistoryModel.getOaid());		//OA操作人ID	是
//				ipHistory.setValue("SAPID", ipHistoryModel.getSapid());		//SAP操作人ID	
            ipHistory.setValue("COMPANY", ipHistoryModel.getCompany());        //公司名称	是
            ipHistory.setValue("DEPT", ipHistoryModel.getDept());        //业务部门描述	是
            ipHistory.setValue("REMARK", ipHistoryModel.getRemark());        //说明		是

            if (!"".equals(error.toString())) {
                jsonMap.put("error", error.append("不能为空").toString());
                return jsonMap;
            }

            JCoContext.begin(destination);
            //执行函数
            functionDisplay.execute(destination);
            //提交事务
            functionC.execute(destination);
            JCoContext.end(destination);
//				String messageV1 = "", messageV2 = "", messageV3 = "", messageV4 = "";
            JCoTable etLog = functionDisplay.getTableParameterList().getTable("ET_LOG");
            JCoParameterList createOutputParam = functionDisplay.getExportParameterList();
            String result = createOutputParam.getString("EP_SUBRC");    //0成功，其他失败
            for (int i = 0; i < etLog.getNumRows(); i++) {
                etLog.setRow(i);
                System.out.println(etLog.getValue("ROW").toString());
                String field = etLog.getValue("FIELD").toString();
                System.out.println(field);
                String param = etLog.getValue("PARAMETER").toString();
                System.out.println(param);
                String type = etLog.getValue("TYPE").toString();
                System.out.println(type);
                String message = etLog.getValue("MESSAGE").toString();
//	            	messageV1 = etLog.getValue("MESSAGE_V1").toString();
//	            	messageV2 = etLog.getValue("MESSAGE_V2").toString();
//	            	messageV3 = etLog.getValue("MESSAGE_V3").toString();
//	            	messageV4 = etLog.getValue("MESSAGE_V4").toString();
                if ("E".equals(type) && !"0".equals(result)) {
                    sb.append(field).append(param).append(message);
                } else if ("E".equals(type) && "0".equals(result)) {
                    sb.append("有警告信息，但调拨成功");
                }
            }

            if (!"0".equals(result)) {
                sb.append("[接口调用失败]");
            }
            if (this.notEmpty(sb.toString()) && !"0".equals(result)) {
                jsonMap.put("error", sb.toString());    //错误信息提示
            }
            jsonMap.put("result", result);    //0成功，其他失败
//				jsonMap.put("messageV1", messageV1);	//消息,消息变量
//				jsonMap.put("messageV2", messageV2);	//消息,消息变量
//				jsonMap.put("messageV3", messageV3);	//消息,消息变量
//				jsonMap.put("messageV4", messageV4);	//消息,消息变量
            ZrfcAmAssetChange zrfcAmAssetChange = new ZrfcAmAssetChange();
            zrfcAmAssetChange.setIpAsset(ipAsset);
            zrfcAmAssetChange.setSubnumber(ipInputModel.getSubnumber());
            zrfcAmAssetChange.setEpSubrc(result);
            zrfcAmAssetChange.setEtLog(sb.toString());
//				zrfcAmAssetChange.setOperateUser(user);
            zrfcAmAssetChange.setOperateDate(new Date());
            //zrfcAmAssetChangeService.save(zrfcAmAssetChange);
            assetChangeSapDAO.save(zrfcAmAssetChange);
            return jsonMap;

        } catch (JCoException e) {
            e.printStackTrace();
            LOGGER.error("updateAssetsFromSAPException", e.getMessage());
        }
        return null;
    }


    /**
     * 采购订单收货接口
     *
     * @param ipZmm0010Model
     * @param ipHistoryModel
     * @param itZmm0011Models
     */
    public JSONObject createPurchaseOrderReceiveGoods(IpZmm0010Model ipZmm0010Model, IpHistoryModel ipHistoryModel,
                                                      List<ItZmm0011Model> itZmm0011Models) {

        String user = destination.getUser();
      /*读取资产编码接口*/
        JCoFunction functionReceive;
        JSONObject jsonMap = new JSONObject();
        ReceiveGoodsMain receiveGoodsMain = new ReceiveGoodsMain();
        try {
            functionReceive = destination.getRepository().getFunction("ZRFC_MM_MB1A_1");
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
            //**************************************************按采购订单收货
            JCoStructure ipZmm0010 = functionReceive.getImportParameterList().getStructure("IP_ZMM0010");
            if (ipZmm0010 != null) {

                ipZmm0010.setValue("PSTNG_DATE", this.getCurrDate());    //ipZmm0010Model.getPstngDate().replaceAll("-", ""));   //记账日期
                ipZmm0010.setValue("DOC_DATE", this.getCurrDate());    //ipZmm0010Model.getDocDate().replaceAll("-", ""));	//凭证日期
                ipZmm0010.setValue("PR_UNAME", ipZmm0010Model.getPrUname());                    //
                ipZmm0010.setValue("HEADER_TXT", ipZmm0010Model.getHeaderTxt());                    //凭证抬头文本
                ipZmm0010.setValue("REF_DOC_NO", ipZmm0010Model.getRefDocNo());                    //
                receiveGoodsMain.setPstngDate(this.getCurrDate());
                receiveGoodsMain.setDocDate(this.getCurrDate());
                receiveGoodsMain.setPrUname(ipZmm0010.getString("PR_UNAME"));
                receiveGoodsMain.setHeaderTxt(ipZmm0010.getString("HEADER_TXT"));
                receiveGoodsMain.setRefDocNo(ipZmm0010.getString("REF_DOC_NO"));
            }

            //表名：IP_HISTORY ,最后“是”为是否必输
            JCoStructure ipHistory = functionReceive.getImportParameterList().getStructure("IP_HISTORY");
//				ipHistory.setValue("MANDT", ipHistoryModel.getMandt());		//客户端	
            ipHistory.setValue("OANO", ipHistoryModel.getOano());        //OA UNID	是
//				ipHistory.setValue("SAPNO", ipHistoryModel.getSapno());		//SAP操作对象(销售订单/交货订单/物料主数据/客户主数据/供应商主	
            ipHistory.setValue("MBLNR", ipHistoryModel.getMblnr());        //写入SAP产生的凭证
            ipHistory.setValue("ZDATE", this.getCurrDate());        //日期
            ipHistory.setValue("ZTIME", this.getCurrTime());        //时间
            ipHistory.setValue("OANAME", ipHistoryModel.getOaname());        //OA操作人姓名	是
            ipHistory.setValue("OAMODULE", ipHistoryModel.getOamodule());        //OA模块名	是
            ipHistory.setValue("RFC", ipHistoryModel.getRfc());        //SAP函数名
            ipHistory.setValue("OAID", ipZmm0010Model.getPrUname());    //ipHistoryModel.getOaid());		//OA操作人ID	是
            ipHistory.setValue("SAPID", ipZmm0010Model.getPrUname());    //ipHistoryModel.getSapid());		//SAP操作人ID	是
            ipHistory.setValue("COMPANY", ipHistoryModel.getCompany());        //公司名称
            ipHistory.setValue("DEPT", ipHistoryModel.getDept());        //业务部门描述
            ipHistory.setValue("REMARK", ipHistoryModel.getRemark());        //说明
            receiveGoodsMain.setOano(ipHistory.getString("OANO"));
            receiveGoodsMain.setMblnr(ipHistory.getString("MBLNR"));
            receiveGoodsMain.setZdate(ipHistory.getString("ZDATE"));
            receiveGoodsMain.setZtime(ipHistory.getString("ZTIME"));
            receiveGoodsMain.setOaname(ipHistory.getString("OANAME"));
            receiveGoodsMain.setOamodule(ipHistory.getString("OAMODULE"));
            receiveGoodsMain.setRfc(ipHistory.getString("RFC"));
            receiveGoodsMain.setOaid(ipHistory.getString("OAID"));
            receiveGoodsMain.setSapid(ipHistory.getString("SAPID"));
            receiveGoodsMain.setCompany(ipHistory.getString("COMPANY"));
            receiveGoodsMain.setRemark(ipHistory.getString("REMARK"));
            //输入表
            JCoTable itZmm0011 = functionReceive.getTableParameterList().getTable("IT_ZMM0011");
            StringBuilder itemNo = new StringBuilder("");
            for (int i = 0; i < itZmm0011Models.size(); i++) {
                ItZmm0011Model itZmm0011Model = itZmm0011Models.get(i);
                itZmm0011.appendRow();
                itZmm0011.setRow(i);
                itZmm0011.setValue("MATERIAL", itZmm0011Model.getMaterial());        //物料号
                itZmm0011.setValue("PLANT", itZmm0011Model.getPlant());        //*工厂
                itZmm0011.setValue("STGE_LOC", itZmm0011Model.getStgeLoc());    //库存地点
                itZmm0011.setValue("BATCH", itZmm0011Model.getBatch());    //批号
                itZmm0011.setValue("MOVE_TYPE", itZmm0011Model.getMoveType());    //*移动类型(库存管理)
                itZmm0011.setValue("STCK_TYPE", itZmm0011Model.getStckType());    //*库存类型
                itZmm0011.setValue("ENTRY_QNT", new BigDecimal(1));    //itZmm0011Model.getEntryQnt());  //*条目单位的数量
                itZmm0011.setValue("ENTRY_UOM", itZmm0011Model.getEntryUom());  //*条目单位
                itZmm0011.setValue("COSTCENTER", itZmm0011Model.getCostcenter()); //*成本中心
                itZmm0011.setValue("MOVE_REAS", itZmm0011Model.getMoveReas()); //*成本中心
                itZmm0011.setValue("ITEM_TEXT", itZmm0011Model.getItemText());   //项目文本
                itZmm0011.setValue("GR_RCPT", ipZmm0010Model.getPrUname());   //收货者
                itZmm0011.setValue("PO_NUMBER", itZmm0011Model.getPoNumber());  //*采购订单号
                itZmm0011.setValue("PO_ITEM", itZmm0011Model.getPoItem()); //itZmm0011Model.getPoItem());   //采购凭证的项目编号
                itZmm0011.setValue("MVT_IND", itZmm0011Model.getMvtInd());  //移动标识
                itZmm0011.setValue("ORDERID", itZmm0011Model.getOrderid());  //订单号
                itZmm0011.setValue("GL_ACCOUNT", itZmm0011Model.getGlAccount());  //总帐科目编号
                itZmm0011.setValue("PROFIT_CTR", itZmm0011Model.getProfitCtr()); //*利润中心
                itemNo.append(itZmm0011Model.getPoItem()).append(",");

            }

            receiveGoodsMain.setCompany(itZmm0011Models.get(0).getPlant());
            receiveGoodsMain.setBstnr(itZmm0011Models.get(0).getPoNumber());
            receiveGoodsMain.setEbelp(itemNo.toString().substring(0, itemNo.toString().length() - 1));
            List<ReceiveGoodsMain> exists = receiveGoodsMainSapDAO.selectExists(receiveGoodsMain);
            StringBuilder res = new StringBuilder("");
            if (itZmm0011Models.size() == exists.size()) {
                ReceiveGoodsMain exis = exists.get(0);
                String epMaterialdocu = exis.getEpMaterialdocument();
                String eBelnr = exis.getEBelnr();
                String epMatdocumentyear = exis.getEpMatdocumentyear();
                res = new StringBuilder(exis.getEtLog() == null ? "" : exis.getEtLog());
                String sc = "-1".equals(exis.getEpSubrc()) ? "-1" : "0";
                jsonMap.put("warn", "已经成功收过货。");        //会计凭证号
                jsonMap.put("epMaterialdocu", epMaterialdocu);    //物料凭证号
                jsonMap.put("epBelnr", eBelnr);        //会计凭证号
                jsonMap.put("epMatdocumentyear", epMatdocumentyear);    //会计年度
                jsonMap.put("result", sc);    //结果信息 result=0为收货成功，其它失败
                System.out.println("费用采购订单物料为已经入库的数据----结束" + epMaterialdocu + ", " + eBelnr);
                return jsonMap;
            } /*else if(itZmm0011Models.size() > exists.size()){
				res.append("此次数据同时包含未收货和已收货的行项目(不允许)");
				return jsonMap;
			}*/
			/*for (int i = 0; i < exists.size(); i++) {
				for (int j = 0; j < itZmm0011Models.size(); j++) {
					if(exists.get(i).getPoItem())
				}
			}*/

            //itZmm0011.setValue(Zer*10, "PO_ITEM");   //项目编号PO_ITEM
            //*************************************************
            JCoContext.begin(destination);
            functionReceive.execute(destination);
            //提交事务
            functionC.execute(destination);
            JCoContext.end(destination);
            JCoTable etLog = functionReceive.getTableParameterList().getTable("ET_LOG");
            res = new StringBuilder("");
            for (int i = 0; i < etLog.getNumRows(); i++) {
                etLog.setRow(i);
                System.out.println(etLog.getString("ROW"));
                System.out.println(etLog.getString("FIELD"));
                System.out.println(etLog.getString("PARAMETER"));
                String type = etLog.getString("TYPE");
                System.out.println(etLog.getString("TYPE") + etLog.getString("MESSAGE"));
                if ("E".equals(type)) {
                    res.append(etLog.getString("MESSAGE"));
                }
            }
            jsonMap.put("messageLogs", res);    //返回结果说明信息，可以作为日志和排查错误情况的说明
            JCoParameterList exportList = functionReceive.getExportParameterList();
            String epMaterialdocu = exportList.getString("EP_MATERIALDOCUMENT");    //物料凭证号
            String eBelnr = exportList.getString("E_BELNR");    //会计凭证号
            String sc = exportList.getString("EP_SUBRC");    //结果0-success
            String epMatdocumentyear = exportList.getString("EP_MATDOCUMENTYEAR");    //年度

            try {
                ZrfcMmMb1a1 zrfcMmMb1a1Model = new ZrfcMmMb1a1();
                zrfcMmMb1a1Model.setEpMaterialdocument(epMaterialdocu);
                zrfcMmMb1a1Model.setEBelnr(eBelnr);

                zrfcMmMb1a1Model.setEtLog(res.toString());
//            zrfcMmMb1a1Model.setOperateUser(user);
                zrfcMmMb1a1Model.setOperateDate(new Date());

                receiveGoodsMain.setEpMaterialdocument(epMaterialdocu);
                receiveGoodsMain.setEBelnr(eBelnr);
                receiveGoodsMain.setEpMatdocumentyear(epMatdocumentyear);
                receiveGoodsMain.setEtLog(res.toString());
                receiveGoodsMain.setOperateDate(new Date());
                receiveGoodsMainSapDAO.save(receiveGoodsMain);
                Long fId = receiveGoodsMain.getId();
//            zrfcMmMb1a1Service.save(zrfcMmMb1a1Model, itZmm0011Models);
                //```
         /* for (int i = 0; i < itZmm0011Models.size(); i++) {
					zrfcMmMb1a1Model.setPoNumber(itZmm0011Models.get(i).getPoNumber());
					zrfcMmMb1a1Model.setMaterial(itZmm0011Models.get(i).getMaterial());
					receiveGoodsSapDAO.save(zrfcMmMb1a1Model);
				}*/
                for (int i = 0; i < itZmm0011Models.size(); i++) {
                    ItZmm0011Model itZmm0011Model = itZmm0011Models.get(i);
                    itZmm0011Model.setFId(fId);
                    receiveGoodsISapDAO.save(itZmm0011Model);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                LOGGER.error("createPurchaseOrderReceiveGoodsException", e.getMessage());
            }
            jsonMap.put("epMaterialdocu", epMaterialdocu);    //物料凭证号
            jsonMap.put("epBelnr", eBelnr);        //会计凭证号
            jsonMap.put("epMatdocumentyear", epMatdocumentyear);    //会计年度
            jsonMap.put("result", sc);    //结果信息 result=0为收货成功，其它失败

            System.out.println("费用采购订单物料入库----结束" + epMaterialdocu + ", " + eBelnr);
        } catch (JCoException e) {
            e.printStackTrace();
            LOGGER.error("createPurchaseOrderReceiveGoodsException", e.getMessage());
            jsonMap.put("result", "-1");
            jsonMap.put("errorTxt", e);
        }
        jsonMap.put("attention", "result=0为收货成功，其它失败");
        return jsonMap;
    }


    /**
     * 采购发票校验
     *
     * @param ipZmm0010Model
     * @param ipHistoryModel
     * @param itZmm0011Model
     */
    public JSONObject createBillsCheck(List<ItDataModel> itDataModels, Eaddressdata eaddressdata) {
	  
      /*读取资产编码接口*/
        JCoFunction functionBills;
        JSONObject jsonMap = new JSONObject();
        if (itDataModels.size() > 490) {
            jsonMap.put("result", "-1");
            jsonMap.put("messgae", "最多行为490");
            return jsonMap;
        }
        try {
            functionBills = destination.getRepository().getFunction("ZRFC_PURCHASE_INVOICE");
            JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
            //**************************************************按采购订 收货
            JCoParameterList tablePs = functionBills.getTableParameterList();
            if ("1".equals(eaddressdata.getAcc1time())) {
                JCoStructure eAddressdata = functionBills.getImportParameterList().getStructure("E_ADDRESSDATA");
                eAddressdata.setValue("ACC_1_TIME", "X");
                eAddressdata.setValue("NAME", eaddressdata.getName());
                eAddressdata.setValue("CITY", eaddressdata.getCity());
                eAddressdata.setValue("LANGU", eaddressdata.getLangu());
                eAddressdata.setValue("COUNTRY", eaddressdata.getCountry());
            }
            JCoTable itData = tablePs.getTable("IT_DATA");
            for (int i = 0; i < itDataModels.size(); i++) {
                ItDataModel itDataModel = itDataModels.get(i);
                itDataModel.setErfmg(new BigDecimal(1));
                itData.appendRow();
                itData.setRow(i);
                itData.setValue("EBELN", StringUtils.leftPad(itDataModel.getEbeln(), 10, "0"));    //采购凭证号
                itDataModel.setEbelp(StringUtils.leftPad(itDataModel.getEbelp(), 5, "0"));

                itData.setValue("EBELP", itDataModel.getEbelp());    //采购凭证的项目编号
                itData.setValue("MATNR", itDataModel.getMatnr());    //物料号
                itDataModel.setAnln1(StringUtils.leftPad(itDataModel.getAnln1(), 12, "0"));
                itData.setValue("ANLN1", itDataModel.getAnln1());    //主资产号
                itDataModel.setAnln2(StringUtils.leftPad(itDataModel.getAnln2(), 4, "0"));
                itData.setValue("ANLN2", itDataModel.getAnln2());    //资产次级编号
                itData.setValue("MJAHR", itDataModel.getMjahr());    //物料凭证年度
                itData.setValue("MBLNR", StringUtils.leftPad(itDataModel.getMblnr(), 10, "0"));    //物料凭证编号
                itData.setValue("ERFMG", new BigDecimal(1));    //条目单位的数量
                itData.setValue("NETWR", itDataModel.getNetwr());    //凭证货币计量的净价值
                itData.setValue("MWSKZ", itDataModel.getMwskz());    //销售/购买税代码
                itData.setValue("NETWJ", itDataModel.getNetwj());    //凭证货币计量的净价值
                itData.setValue("NETWZ", itDataModel.getNetwz());    //凭证货币计量的净价值
                itData.setValue("CURRENCY", itDataModel.getCurrency());    //货币代码

            }
            JCoContext.begin(destination);
            functionBills.execute(destination);
            functionC.execute(destination); //提交事务
            JCoParameterList exportList = functionBills.getExportParameterList();
            String ecBelnr = exportList.getString("EC_BELNR");    //发票凭证
            String eBelnr = exportList.getString("E_BELNR");    //会计凭证号码
            String ecSubrc = exportList.getString("EC_SUBRC");    //返回消息类型：S（成功）E(失败)
            String ecMessage = exportList.getString("EC_MESSAGE");    //返回消息文本
//            zrfcMmMb1a1Model.setOperateUser(user);
//            zrfcMmMb1a1Service.save(zrfcMmMb1a1Model, itZmm0011Models);
            JCoContext.end(destination);
            jsonMap.put("ecBelnr", ecBelnr);    //发票凭证
            jsonMap.put("eBelnr", eBelnr);    //发票凭证
            jsonMap.put("result", ecSubrc);        //返回消息类型：S（成功）E(失败)
            jsonMap.put("ecMessage", ecMessage);    //返回消息文本
            try {
                for (int i = 0; i < itDataModels.size(); i++) {
                    ItDataModel itDataModel = itDataModels.get(i);
                    itDataModel.setEcBelnr(ecBelnr);
                    itDataModel.setEbelnr(eBelnr);
                    itDataModel.setEcSubrc(ecSubrc);
                    itDataModel.setEcMessage(ecMessage);
                    billCheckSapDAO.save(itDataModel);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                LOGGER.error("createBillsCheckException", e.getMessage());
                e.printStackTrace();
            }
            System.out.println("费用采购发票校验----结束" + jsonMap);
        } catch (JCoException e) {
            for (int i = 0; i < itDataModels.size(); i++) {
                ItDataModel itDataModel = itDataModels.get(i);
                billCheckSapDAO.save(itDataModel);
            }
            e.printStackTrace();
            System.out.println(e);
            LOGGER.error("createBillsCheckException", e.getMessage());
            jsonMap.put("result", "-1");
            jsonMap.put("message", e.getMessage());
        }
        jsonMap.put("attention", "result：S成功，E失败");
        return jsonMap;
    }

}