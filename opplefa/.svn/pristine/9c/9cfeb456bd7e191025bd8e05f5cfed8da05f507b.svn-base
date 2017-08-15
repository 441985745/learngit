package com.opple.fa.sap.service.impl;



import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.opple.fa.config.entity.AssetUnit;
import com.opple.fa.config.entity.BudgetAssetstypeConfig;
import com.opple.fa.config.entity.Factory;
import com.opple.fa.config.entity.WorkAddress;
import com.opple.fa.purchase.entity.PaymentPaymentMapping;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.model.PaymentOrderDetailModel;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.purchase.service.impl.BaseSapTestCase;
import com.opple.fa.sap.entity.Eaddressdata;
import com.opple.fa.sap.entity.Generalpostingdata;
import com.opple.fa.sap.entity.IAmtran;
import com.opple.fa.sap.entity.Retirementdata;
import com.opple.fa.sap.model.AccountglModel;
import com.opple.fa.sap.model.CurrencyamountModel;
import com.opple.fa.sap.model.DocumentHeaderModel;
import com.opple.fa.sap.model.Extension2Model;
import com.opple.fa.sap.model.IpHistoryModel;
import com.opple.fa.sap.model.IpInputModel;
import com.opple.fa.sap.model.IpZmm0010Model;
import com.opple.fa.sap.model.ItDataModel;
import com.opple.fa.sap.model.ItZmm0011Model;
import com.opple.fa.sap.model.ReadAssetsCodeModel;
import com.opple.fa.util.VerifyAnnotationMyLimit;
import com.opple.sap.util.SAPConn;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;

//@IUnitDataSet//设置数据
public class SapTestImplTests extends BaseSapTestCase {
	
	IpInputModel ipInputModel;
	IpHistoryModel ipHistoryModel;
	private JCoDestination destination = SAPConn.connect();;
	
    static public void assertEquals(String message, Object expected, Object actual) {  
        if (expected== null&& actual == null)  
            return;  
         if (expected != null&& expected.equals(actual))  
            return;  
        // failNotEquals(message, expected, actual);  
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
  		 String date = this.dateToString(new Date());	//当前日期string类型
  		return date;
  		
  	}
    

	@Test
	public void tests()  throws Exception{
//		budgetAssetstypeConfigService.get("4");
//		factoryService.get("1");
		System.out.println("\n\n\nSAP单元测试开始！----------------------------------------------------------------------------------");
		Date d = new Date();
//		String sss = purchaseOrderSapDAO.selectExists("CGDD2017031512");
//		System.out.println(sss);
		//		this.testRetireFromSAP();
//				this.testExistenceAssetsCodeFromSAP1();
		this.testReadAssetsCodeFromSAP1("6000", "100411", "0000");
		for (int i = 0; i < 1; i++) {
//			this.testCreateAssetsCodeFromSAP1(3L);
//			this.testAssetsChangeFromSAP();//资产更改sap
		}
//		this.testAppendAssetsCodeFromSAP1("6000", "100254" ,"0001");
		
//		this.testReadAssetsCodeFromSAP1Old();
//		System.out.println("采购订单sap-test--begin");
//		this.testGetPurchaseOrderFromSAP1("0069020000");//0066010900");//62030401");//0068010005");//"0069020000");//采购订单sap,
//		System.out.println("采购订单、sap-test--begin");
//		this.testAssetsChangeFromSAP();//资产更改sap
//		this.testAssetsCompanyChangeFromSAP();//公司间调拨sap
//		System.out.println("预付凭证sap-test--begin");
//		this.testPrePayFromSAP1();//预付凭证sap
//		System.out.println("资产收获sap-test--begin");
//		this.testReceiveGoodsFromSAP(e, costCenter, m, p, g, u, purchaseOrderDetails, purchaseOrder, pid)
//		this.testCheckPayFromSAP1();
//		this.clearVouchFromSAP();
//		this.testRetireFromSAP();
		/*String group = "113";//固定资产采购(113)";
		String dd = group.substring(0, 0);
		int a = group.lastIndexOf("(-") + 1;
		int d = (group.lastIndexOf(")-") < 0 ? group.length() : group.lastIndexOf(")"));
		group = group.substring(a, d);
		String procurementOrganization = "欧普照明非生产性采购(1001)";
		procurementOrganization = procurementOrganization.substring(procurementOrganization.lastIndexOf("(") + 1, procurementOrganization.lastIndexOf(")"));
dd+","+group +","+procurementOrganization +*/
//		Factory factory = new Factory();
		/*List<Factory> factorys = new ArrayList<Factory>();
		factorys.add(factory);
		factory.setCfactoryCode("流口水的份");
		factory.setCstatus("sdf");
		 try {//传人Students对象进行验证  
	            VerifyAnnotationMyLimit annotationMyLimit = new VerifyAnnotationMyLimit(factory);  
	            if(annotationMyLimit.getListExceptions().size()!=0){  
	                for (Exception exception : annotationMyLimit.getListExceptions()) {  
	                    System.out.println("错误："+exception.getMessage());  
	                }  
	            }  
	        } catch (Exception e) {  
	            // TODO Auto-generated catch block  
	            System.out.println(e.getMessage());  
	            e.printStackTrace();  
	        }  
	          
	        */
//		factoryService.save(factory);
//		factoryService.saveBatch(factorys);
		
//		factoryService.delete(factory);
		
//		factoryService.update(factory);
////		Pager<Factory> pager = new Pager<Factory>();
////		factoryService.searchPagerList(pager, factory);
		/*List<Factory> fc = factoryService.getList(factory);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 0; i < fc.size(); i++) {
			Date d = fc.get(i).getDcreateDate();
			String ddd = sdf.format(d);
			System.out.println(ddd);
		}*/
/*		List<String> factoryCodes = new ArrayList<String>();
		factoryCodes.add("1169");
		factoryCodes.add("1168");
		List<String> a = factoryService.selectExistCodes(factoryCodes);
		for (String s : a) {
			System.out.println(s);
		}*/
//		List<Factory> ss = factoryService.searchByCompanyCode("6000");
//		System.out.println(ss.size());
//		 WorkAddress w = workAddressService.get("BGDD10000");
		
		/*AssetUnit assetUnit = new AssetUnit();
		assetUnit.setUnitCode("323423");
		assetUnitService.save(assetUnit);*/
		
//		BudgetAssetstypeConfig budgetAssetsTypeConfig = new BudgetAssetstypeConfig();
//		List<BudgetAssetstypeConfig> bt = budgetAssetstypeConfigService.getList(budgetAssetsTypeConfig );
//		
		Date e = new Date();
		System.out.println(d+"------------"+e);
		System.out.println("SAP单元测试结束！----------------------------------------------------------------------------------\n\n\n");
	}
	
	private String dateToString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateString = sdf.format(date);
		return dateString;
	}
	public void testCreateAssetsCodeFromSAP1(Long c)  throws Exception{
		ipInputModel = new IpInputModel();
		ipHistoryModel= new IpHistoryModel();
		ipInputModel.setAssetclass("1000");//1000");
		ipInputModel.setCompanycode("6000");
		ipInputModel.setQuantity(c);//只有這一個數量
//		ipInputModel.setAnln1("000000403187");
		ipInputModel.setSerialNo("0003000");
		ipInputModel.setDescript("testp");
		ipInputModel.setBaseUom("A11");
		ipInputModel.setCostcenter("0068010005");//0069020000");
		ipHistoryModel.setOano("1");
		ipHistoryModel.setOaname("1");
		ipHistoryModel.setOamodule("1");
		JSONObject aa = sapUtilService.createAssetsCodeFromSAP(ipInputModel, ipHistoryModel); 
		System.out.println(aa);
		String mainNo = aa.getString("asset");
//		subNumber
	}
	
	public void testReadAssetsCodeFromSAP1Old()  throws Exception{
		ReadAssetsCodeModel readAssetsCodeModel = new ReadAssetsCodeModel();
//		itInputModel.setCount(count)
		/*readAssetsCodeModel.setCompanyCode("6000");
//		readAssetsCodeModel.setMonthly("003");
		readAssetsCodeModel.setMasterAssetCode("000000300070");
		readAssetsCodeModel.setSecondaryAssetCode("0001");*/
		readAssetsCodeModel.setCompanyCode("6000");
		readAssetsCodeModel.setMonthly("003");
		String[] field = {"ASSET","SUBNUMBER"};
		String[] fieldValueHigh= {"000000500000", "1000"};
		String[] fieldValueLow= {"000000200000", "0000"};
		String[] option= {"BT", "BT"};
		readAssetsCodeModel.setField(field);
		readAssetsCodeModel.setFieldValueHigh(fieldValueHigh);
		readAssetsCodeModel.setFieldValueLow(fieldValueLow);
		readAssetsCodeModel.setMaxEntries("10");
		readAssetsCodeModel.setOption(option);
//		readAssetsCodeModel.setSubNumber("0001");
//		readAssetsCodeModel
//		readAssetsCodeModel
//		readAssetsCodeModel
/*		readAssetsCodeModel.setIpBukrs("6000");
		readAssetsCodeModel.setIpKostl("10150010");*/
//		readAssetsCodeModel.setIpFlag("");	//是否查询所有数据(1表示报废,2表示正常,空表示所有)
//		readAssetsCodeModel.setIpInvnr("");	//存货号
		
//		itInputModel.setAnln1("401901");
//		itInputModel.setAnln2("11");
		
		JSONArray mm = sapUtilTailService.readAssetsCodeFromSAPOld(readAssetsCodeModel);
		System.out.println(mm);
	}
	
	public void testReadAssetsCodeFromSAP1(String compCode, String mainCode, String subCode)  throws Exception{
		ReadAssetsCodeModel readAssetsCodeModel = new ReadAssetsCodeModel();
		readAssetsCodeModel.setCompanyCode(compCode);
//		itInputModel.setCount(count)
//		readAssetsCodeModel.setMonthly("003");
		readAssetsCodeModel.setMasterAssetCode(StringUtils.leftPad(mainCode, 12, "0"));//000000300071");//400887");//000000300070");
		readAssetsCodeModel.setSecondaryAssetCode(StringUtils.leftPad(subCode, 4, "0"));
		/*String[] field = {"ASSET","SUBNUMBER"};
		String[] fieldValueHigh= {"000000200000", "0000"};
		String[] fieldValueLow= {"000000300000", "1000"};
		String[] option= {"BT", "BT"};
		readAssetsCodeModel.setField(field);
		readAssetsCodeModel.setFieldValueHigh(fieldValueHigh);
		readAssetsCodeModel.setFieldValueLow(fieldValueLow);*/
		readAssetsCodeModel.setMaxEntries("50");	//~~~有时间进行测试null""！
//		readAssetsCodeModel.setOption(option);
//		readAssetsCodeModel.setSubNumber("0001");
//		readAssetsCodeModel
//		readAssetsCodeModel
//		readAssetsCodeModel
/*		readAssetsCodeModel.setIpBukrs("6000");
		readAssetsCodeModel.setIpKostl("10150010");*/
//		readAssetsCodeModel.setIpFlag("");	//是否查询所有数据(1表示报废,2表示正常,空表示所有)
//		readAssetsCodeModel.setIpInvnr("");	//存货号
		
//		itInputModel.setAnln1("401901");
//		itInputModel.setAnln2("11");
		
		JSONObject mm = sapUtilService.readAssetsCodeFromSAP(readAssetsCodeModel);
		System.out.println(mm);
	}
	public void testAppendAssetsCodeFromSAP1(String compcode, String assetno, String subno)  throws Exception{
			ipInputModel = new IpInputModel();
			ipHistoryModel= new IpHistoryModel();
//			ipInputModel.setAssetclass("4000");
			ipInputModel.setCompanycode(compcode);
//			ipInputModel.setQuantity(2L);
			ipInputModel.setAnln1(assetno);//2
			ipInputModel.setSubnumber(subno);
//			ipInputModel.setDescript("无");
			ipInputModel.setBaseUom("套");	//座没有
//			ipInputModel.setCostcenter("0066010900");
//			ipHistoryModel.setOano("1");
//			ipHistoryModel.setOaname("1");
//			ipHistoryModel.setOamodule("1");
			JSONObject aa = sapUtilService.createAssetsCodeForAppendFromSAP(ipInputModel, ipHistoryModel); 
			System.out.println(aa);
		
	}
	
	public void testExistenceAssetsCodeFromSAP1()  throws Exception{
		ipInputModel = new IpInputModel();
		ipHistoryModel= new IpHistoryModel();
		ipInputModel.setCompanycode("6000");
		ipInputModel.setAnln1("100200");//2
		ipInputModel.setSubnumber("0001");
		JSONObject aa = sapUtilService.existenceOfAssetsCodeFromSAP(ipInputModel, ipHistoryModel);
		System.out.println(aa);
	
	}
	public void testGetPurchaseOrderFromSAP1(String costCenter)  throws Exception{
		String pid = "CGDD0000"+this.dateTimeToString(new Date()).substring(6,12) ;
		System.out.println(pid);
		costCenter = "0068010005";//0061020100";//0068010005";//";//0063120001";有的成本中心收货提示E：请记录相关研发订单号
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setApplyDate(new Date());
		purchaseOrder.setApplyUser("李红令");
		purchaseOrder.setApplyUserCode("OP035126");
		purchaseOrder.setAssetType("IT资产");
//		purchaseOrder.setBudgetDepartmentCode("BD0068010005");
		purchaseOrder.setBudgetDepartmentName("评价及实验室(68010005)");//"财务办公室");
		purchaseOrder.setBuyerCode("OP035126");
		purchaseOrder.setBuyerName("李红令");
		purchaseOrder.setCapprovalstate("等待审批");
		purchaseOrder.setCdocument(pid);//+ this.dateToString(new Date()+StringUr));//
		purchaseOrder.setNextHandlerCode("OP000225");
		purchaseOrder.setNextHandlerName("王国孝");
		purchaseOrder.setCompanyCode("6000");
		purchaseOrder.setCompanyName("欧普照明股份有限公司");
		purchaseOrder.setCostCenter("评价及实验室");
		purchaseOrder.setCostCenterCode(costCenter);
		purchaseOrder.setCountry("CN");
		purchaseOrder.setCreateBy("李红令");
		purchaseOrder.setCreateByCode("OP035126");
		purchaseOrder.setCreateDate(new Date());
		purchaseOrder.setCurrencyCode("HKD");//CNY");
		purchaseOrder.setCurrencyName("HKD 港币");//CNY 人民币");
		purchaseOrder.setDraft("N");
		purchaseOrder.setFactoryCode("6000");
		purchaseOrder.setOfficeLocation("吴江");
		purchaseOrder.setOrderMoney(333D);
		purchaseOrder.setOrderStatus("未关闭");
		purchaseOrder.setOrderType("ZFB");
		purchaseOrder.setPaymentTypeCode("9001");
		purchaseOrder.setPlatform("研发中心");
		purchaseOrder.setPrint("N");
		purchaseOrder.setProcurementOrganization("1001");
		purchaseOrder.setPurchasingGroup("113");
		purchaseOrder.setStatus("Y");
		purchaseOrder.setSupplierCode("0000901222");//2");//2");//一次性供应商编码是1或2，必须填城市,,,0000901008当前这个编码不可以
		purchaseOrder.setSupplierName("采购中心一次性供应商");
		purchaseOrder.setTaxRateCode("J1");
		purchaseOrder.setUnreceivedCount(0);
		purchaseOrder.setUpdateBy("李红令");
		purchaseOrder.setUpdateByCode("OP035126");
		purchaseOrder.setUpdateDate(new Date());
		purchaseOrder.setCinternalordercode("002500000074");
		purchaseOrder.setCity("青岛市");
		purchaseOrder.setExchangeRate(new BigDecimal(0.8734));
		purchaseOrder.setTaxRate(0.17D);
		/*purchaseOrder.setOrderType("ZAB");
		purchaseOrder.setProcurementOrganization("1001");
		purchaseOrder.setPurchasingGroup("113");
		purchaseOrder.setPaymentTypeCode("9001");
		purchaseOrder.setTaxRateCode("J0" );	//J0无税
*///		purchaseOrder.setHeaderTextDescription("抬头");
//		purchaseOrder.setCompanySpecificName("欧普照明股份有限公司");
//		DatabaseOperation.INSERT;
		ArrayList<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<PurchaseOrderDetail>();
//		for (int i = 0; i <= 1; i++) {
			
			PurchaseOrderDetail purchaseOrderDetail =  new PurchaseOrderDetail(); 
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate1 = dateFormat1.parse("2017-12-01");
			purchaseOrderDetail.setUnits("件");
//			purchaseOrderDetail.set("002500000074");
			/*purchaseOrderDetail.setApplyCount(3);
			purchaseOrderDetail.setRequirementsDate(myDate1);
			purchaseOrderDetail.setNetPrice(new BigDecimal(22.66));
			purchaseOrderDetail.setAssetsName("台式办公电脑1");
			purchaseOrderDetail.setApplyUser("李红令");
			purchaseOrderDetail.setPurchaseOrderId(pid);
			purchaseOrderDetail.setId(184L);
//			purchaseOrderDetail.setItemNo(10);
			purchaseOrderDetail.setPurchasePrice(new BigDecimal(1000.12));
			purchaseOrderDetail.setTax(new BigDecimal(200.01));
			purchaseOrderDetail.setNetPrice(new BigDecimal(800.11));
			//purchaseOrderDetail.setApplyDetailId(1L);这个文档写的是必填的，但是随便赋值会返回结果异常，所以暂时不判断是否必填
			
			purchaseOrderDetails.add(purchaseOrderDetail);*/
			
			/*PurchaseOrderDetail purchaseOrderDetail2 =  new PurchaseOrderDetail(); 
			purchaseOrderDetail2.setApplyCount(1);
			purchaseOrderDetail2.setMainAssetCode("100105");
			purchaseOrderDetail2.setSecondary("0000-0000");
			purchaseOrderDetail2.setLedgerAccount("1501000101");//
			purchaseOrderDetail2.setRequirementsDate(myDate1);
			purchaseOrderDetail2.setNetPrice(new BigDecimal(100));
			purchaseOrderDetail2.setAssetsName("设计专用笔记本2 15寸");
			purchaseOrderDetail2.setApplyUser("李红令");
			purchaseOrderDetail2.setPurchaseOrderId(pid);
			purchaseOrderDetail2.setId(451L);
//			purchaseOrderDetail2.setItemNo(20);
			purchaseOrderDetail2.setPurchasePrice(new BigDecimal(100));
			purchaseOrderDetail2.setTax(new BigDecimal(0.0));
			purchaseOrderDetail2.setNetPrice(new BigDecimal(100));
			purchaseOrderDetails.add(purchaseOrderDetail2);
			*/
			PurchaseOrderDetail purchaseOrderDetail3 =  new PurchaseOrderDetail(); 
			purchaseOrderDetail3.setApplyCount(2);	
			purchaseOrderDetail3.setUnits("PC");
//			purchaseOrderDetail3.setApplyDetailId(462L);	
			purchaseOrderDetail3.setApplyOrderId("CGSQ2017032243");	
//			purchaseOrderDetail3.setApplyUser("李红令");	
//			purchaseOrderDetail3.setApplyUserCode("OP035126");	
			purchaseOrderDetail3.setAssetsName("总监专用笔记本（12寸）");	
			purchaseOrderDetail3.setCheckCount(0);	
//			purchaseOrderDetail3.setCreateBy("李红令");	
			purchaseOrderDetail3.setCreateByCode("OP035126");	
			purchaseOrderDetail3.setCreateDate(new Date());	
			//purchaseOrderDetail3.setCurrencyCode("HKD");
			purchaseOrderDetail3.setGoodsCount(0);	
			purchaseOrderDetail3.setId(462L);	
			//purchaseOrderDetail3.setInquiryUnitPrice(new BigDecimal(100.00));	
			purchaseOrderDetail3.setLedgerAccount("5050100106");//
			purchaseOrderDetail3.setMainAssetCode("100104");	
			//purchaseOrderDetail3.setNetPrice(new BigDecimal(100.00));	
//			purchaseOrderDetail3.setPurchaseOrderId("CGDD00000001");
			//purchaseOrderDetail3.setPurchasePriceLocal(new BigDecimal(100.00));//	//采购总价(本币)
			//purchaseOrderDetail3.setPurchasePrice(new BigDecimal(100.00));	////采购总价(含税)
			purchaseOrderDetail3.setPurchaseUnitPrice(new BigDecimal(40.00));////采购单价(含税)
			purchaseOrderDetail3.setNetPrice(new BigDecimal(33.20));
			purchaseOrderDetail3.setRequirementsDate(myDate1);	
			purchaseOrderDetail3.setSecondary("0000-0001");	
			purchaseOrderDetail3.setUpdateBy("李红令");	
			purchaseOrderDetail3.setUpdateByCode("OP035126");	
			purchaseOrderDetail3.setUpdateDate(new Date());	
			purchaseOrderDetail3.setWarrantyPeriod(20);	
			/*purchaseOrderDetail3.setApplyCount(3);
			purchaseOrderDetail3.setMainAssetCode("100106");
			purchaseOrderDetail3.setSecondary("0000-0002");
			purchaseOrderDetail3.setLedgerAccount("1501000101");//"
			purchaseOrderDetail3.setRequirementsDate(myDate1);
			purchaseOrderDetail3.setNetPrice(new BigDecimal(300));
			purchaseOrderDetail3.setAssetsName("设计专用笔记本1 14寸");
			purchaseOrderDetail3.setApplyUser("李红令");
			purchaseOrderDetail3.setPurchaseOrderId(pid);
			purchaseOrderDetail3.setId(452L);
//			purchaseOrderDetail3.setItemNo(30);
			purchaseOrderDetail3.setPurchasePrice(new BigDecimal(300));
			purchaseOrderDetail3.setTax(new BigDecimal(0.0));
			purchaseOrderDetail3.setNetPrice(new BigDecimal(300));*/
			//purchaseOrderDetail.setApplyDetailId(1L);这个文档写的是必填的，但是随便赋值会返回结果异常，所以暂时不判断是否必填
			purchaseOrderDetails.add(purchaseOrderDetail3);
			for (int j = 0; j < purchaseOrderDetails.size(); j++) {
				
				BigDecimal oneNetP = purchaseOrderDetails.get(j).getPurchaseUnitPrice().divide(new BigDecimal(1 + purchaseOrder.getTaxRate()), 2, BigDecimal.ROUND_HALF_UP);
				System.out.println(oneNetP);
			}
			
			JSONObject mm = sapUtilService.createPurchaseOrderFromSAP(purchaseOrder, purchaseOrderDetails);
			System.out.println("采购订单生成 接口 测试结束--返回结果!!!-----:"+mm);
			String e = mm.getString("ebeln");
			String m =mm.getString("matnr");
			String p = mm.getString("ebelp");
			String g = mm.getString("sakto");
			String u =mm.getString("meins");
//			List<PurchaseOrderDetail> details =mm.get("details");
			this.testReceiveGoodsFromSAP(e, costCenter, m, p, g, u, purchaseOrderDetails, purchaseOrder, pid);
			
			
			/*PurchaseOrderDetail purchaseOrderDetail4 =  new PurchaseOrderDetail(); 
			purchaseOrderDetail4.setApplyCount(1);	
//			purchaseOrderDetail4.setApplyDetailId(462L);	
			purchaseOrderDetail4.setApplyOrderId("CGSQ2017032243");	
//			purchaseOrderDetail4.setApplyUser("李红令");	
//			purchaseOrderDetail4.setApplyUserCode("OP035126");	
			purchaseOrderDetail4.setAssetsName("总监专用笔记本（12寸）");	
			purchaseOrderDetail4.setCheckCount(0);	
//			purchaseOrderDetail4.setCreateBy("李红令");	
			purchaseOrderDetail4.setCreateByCode("OP035126");	
			purchaseOrderDetail4.setCreateDate(new Date());	
			purchaseOrderDetail4.setCurrencyCode("CNY");	
			purchaseOrderDetail4.setGoodsCount(0);	
			purchaseOrderDetail4.setId(462L);	
			purchaseOrderDetail4.setInquiryUnitPrice(new BigDecimal(500.00));	
			purchaseOrderDetail4.setLedgerAccount("5050100106");//1501000101");	
			purchaseOrderDetail4.setMainAssetCode("100104");	
			purchaseOrderDetail4.setNetPrice(new BigDecimal(500.00));	
			purchaseOrderDetail4.setPurchaseOrderId("CGDD2017031340");	
			purchaseOrderDetail4.setPurchasePrice(new BigDecimal(500.00));	
			purchaseOrderDetail4.setPurchasePriceLocal(new BigDecimal(0.00));	
			purchaseOrderDetail4.setPurchaseUnitPrice(new BigDecimal(500.00));	
			purchaseOrderDetail4.setRequirementsDate(myDate1);	
			purchaseOrderDetail4.setSecondary("0000-0000");	
			purchaseOrderDetail4.setUpdateBy("李红令");	
			purchaseOrderDetail4.setUpdateByCode("OP035126");	
			purchaseOrderDetail4.setUpdateDate(new Date());	
			purchaseOrderDetail4.setWarrantyPeriod(20);	
			purchaseOrderDetails.add(purchaseOrderDetail4);*/
//		}
			/*	PurchaseOrder purchaseOrderOnly = new PurchaseOrder();
			purchaseOrderOnly.setCdocument("CGDD2017041909");
//			PurchaseOrderDetail 
		
			PurchaseOrder purchaseOr = purchaseOrderService.searchPurchaseOrderByOrderId(purchaseOrderOnly);
			//付款类型编码
			purchaseOrder.setPaymentTypeCode("9001");
			String suberr = "";
			采购组织和采购组的数字编码截取
			try {
				String group = purchaseOrder.getPurchasingGroup();
				group = group.substring(group.lastIndexOf("(") + 1, group.lastIndexOf(")"));
				purchaseOrder.setPurchasingGroup(group);
				String procurementOrganization = purchaseOrder.getProcurementOrganization();
				procurementOrganization = procurementOrganization.substring(procurementOrganization.lastIndexOf("(") + 1, procurementOrganization.lastIndexOf(")"));
				purchaseOrder.setProcurementOrganization(procurementOrganization);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				suberr = e.getMessage();
			}
			采购组织和采购组的数字编码截取结束
			//付款类型编码
			purchaseOrder.setPaymentTypeCode("9001");

			//根据采购订单号查询采购订单明细
			List<PurchaseOrderDetail> purchaseOrderDs = purchaseOrderDetailService.searchPurchaseOrderDetailByDocument(purchaseOrderOnly);
*/
		
	}
	
	

	public void testReceiveGoodsFromSAP(String e,String costCenter,String m,String p,String g,String u,List<PurchaseOrderDetail> purchaseOrderDetails,PurchaseOrder purchaseOrder,String pid)  throws Exception{
//		e="4400114725";
		IpHistoryModel ipHistoryModel = new IpHistoryModel();
		
		ipHistoryModel.setOaid("dfd");
		ipHistoryModel.setOano("sdf");
		ipHistoryModel.setOaname("df");
		ipHistoryModel.setOamodule("df");
//		ipHistoryModel.setSapid("401901");
		ipHistoryModel.setDept(null);
		ipHistoryModel.setCompany(null);
		ipHistoryModel.setRemark(null);
//		ipHistoryModel.setMblnr(e);
		
		IpZmm0010Model ipZmm0010Model = new IpZmm0010Model();
		ipZmm0010Model.setPstngDate("2016-05-09");
		ipZmm0010Model.setDocDate("2017-05-02");
		ipZmm0010Model.setRefDocNo(pid);
		
		List<ItZmm0011Model> itZmm0011Models = new ArrayList<ItZmm0011Model>();
		ItZmm0011Model itZmm0011Model = new ItZmm0011Model();
		
		for (int i = 0; i < purchaseOrderDetails.size(); i++) {
			PurchaseOrderDetail detail = purchaseOrderDetails.get(i);
			for (int j = 0; j < detail.getApplyCount(); j++) {
				itZmm0011Model.setPlant("6000");
				itZmm0011Model.setMoveType("101");	//常量，固定
				itZmm0011Model.setEntryQnt(new BigDecimal(1));
				itZmm0011Model.setCostcenter(purchaseOrder.getCostCenterCode());
				itZmm0011Model.setPoItem(String.valueOf(i*10+10));
				itZmm0011Model.setGlAccount("5050100106");//1501000401");//g
				itZmm0011Model.setMvtInd("B");
				itZmm0011Model.setPoNumber(e);//采购订单凭证号
				itZmm0011Model.setEntryUom(u);
//				itZmm0011Model.setOrderid("002500000074");
				itZmm0011Models.add(itZmm0011Model);
			}
		}
//		itZmm0011Model.setMaterial(m);
		JSONObject mm = sapUtilService.createPurchaseOrderReceiveGoods(ipZmm0010Model, ipHistoryModel, itZmm0011Models);
		System.out.println("mmmm:"+mm);
		String epBelnr = mm.getString("epBelnr");
		String epMaterialdocu = mm.getString("epMaterialdocu");
		String year = mm.getString("epMatdocumentyear");
		this.testBillsCheckFromSAP(e,year,epMaterialdocu,costCenter, m, p, g, u, purchaseOrderDetails, purchaseOrder, pid);
	}
	
	
	public void testBillsCheckFromSAP(String e,String year,String epMaterialdocu,String costCenter,String m,String p,String g,String u,List<PurchaseOrderDetail> purchaseOrderDetails,PurchaseOrder purchaseOrder,String pid)  throws Exception{
		List<ItDataModel> itDataModels = new ArrayList<ItDataModel>();
		for (int i = 0; i < purchaseOrderDetails.size(); i++) {
			PurchaseOrderDetail detail = purchaseOrderDetails.get(i);
			for (int j = 0; j < detail.getApplyCount(); j++) {
				ItDataModel itDataModel = new ItDataModel();
				itDataModel.setEbeln(e);	//采购凭证号是采购订单(SAP)号
				itDataModel.setEbelp(StringUtils.leftPad(String.valueOf(i*10+10),5,"0"));
				itDataModel.setMatnr(m);	//采购订单sap返回的MATNR-物料号
				itDataModel.setCurrency("HKD");
				itDataModel.setMjahr(year);	//收货的接口返回的年度---“epMatdocumentyear"
				itDataModel.setMblnr(epMaterialdocu);	//收货返回的物料凭证号
				itDataModel.setErfmg(new BigDecimal(1));//detail.getApplyCount()));
				itDataModel.setNetwj(new BigDecimal(0.8));//detail.getNetPrice());净单价
				itDataModel.setNetwr(new BigDecimal(0.94));//detail.getPurchasePrice());含税单价
				itDataModel.setNetwz(new BigDecimal(0.14));//detail.getTax());税额
				itDataModel.setMwskz(purchaseOrder.getTaxRateCode());
				itDataModel.setAnln1(detail.getMainAssetCode());
				itDataModel.setAnln2(detail.getSecondary());
				itDataModel.setCondPUnt("ZAB".equals(purchaseOrder.getOrderType())?new BigDecimal(1):new BigDecimal(1000));	//____ZAB是(1)  ZFB是(1000)
				itDataModels.add(itDataModel);
			}
		}
		Eaddressdata eaddressdata = new Eaddressdata();
		eaddressdata.setAcc1time("X");
		eaddressdata.setCity("苏州市");
		eaddressdata.setCountry("CN");
		eaddressdata.setLangu("CH");
		eaddressdata.setName("欧拍照明");
		JSONObject mm = sapUtilService.createBillsCheck(itDataModels,eaddressdata);
		System.out.println("mmmm:"+mm);
	}
	
	
	
	public void testPrePayFromSAP1()  throws Exception{
		
		List<AccountglModel> accountglModels = new  ArrayList<AccountglModel>();
		List<CurrencyamountModel> currencyamountModels = new ArrayList<CurrencyamountModel>();
		List<Extension2Model> extension2Models = new ArrayList<Extension2Model>();
		PaymentOrderModel paymentOrderSAP = new PaymentOrderModel();
		List<PaymentOrderDetailModel> paymentOrderDetailsSAP = new ArrayList<PaymentOrderDetailModel>();
		
		
		DocumentHeaderModel documentHeaderModel = new DocumentHeaderModel();
		paymentOrderSAP.setApplyUserCode("OP009013");//1000008");
		paymentOrderSAP.setDocType("SA");
		paymentOrderSAP.setApplyUser("马蕾");
//		paymentOrderSAP.setBusAct("RFBU");
		paymentOrderSAP.setCompanyCode("8000");
//		paymentOrderDetailSAP.setFiscYear(2017);
//		paymentOrderSAP.setDocType("RE");
//		paymentOrderDetailSAP.setDocDate("2017-01-21");
//		paymentOrderDetailSAP.setPstngDate("2017-01-21");
//		paymentOrderDetailSAP.setFisPeriod(20);
		paymentOrderSAP.setCdocument("CGFK1017030461");
//		paymentOrderSAP.setVoucherId("1500000011");//	//凭证号(sap返回的)
		paymentOrderSAP.setCostCenterCode("0066030401");//0069020000");
		paymentOrderSAP.setSupplierCode("0000900666");//0000010444");//0001001874");//VENDOR_NO	LIFNR	CHAR	10	0	供应商或债权人的帐号	是	供应商编码
		paymentOrderSAP.setProfitCenter("6000");
		paymentOrderSAP.setCurrencyCode("CNY");
		paymentOrderSAP.setApplyFor("dfdsf");
		
		paymentOrderSAP.setCompanyName("欧普照明股份有限公司");
		
//		paymentOrderSAP.setExchangeRate(new BigDecimal(0.8734));
		paymentOrderSAP.setAllocNumber("0001002076");//1000001");
		for (int i = 0; i < 2; i++) {
			PaymentOrderDetailModel paymentOrderDetailSAP = new PaymentOrderDetailModel();
			paymentOrderDetailSAP.setLedgerAccount("1151000101");//"2181000901");//1501000601");//1151000101");//5050100106");//");
//			paymentOrderDetailSAP.setHeaderTxt("1.21测试用");
			paymentOrderDetailSAP.setItemnoAcc(10L+i*10);
			BigDecimal pric = new BigDecimal(17);
			paymentOrderDetailSAP.setPayPrice(i==0 ? pric : pric);//.multiply(new BigDecimal(-1)));
			paymentOrderDetailSAP.setAdvancePrice(new BigDecimal(20));
			paymentOrderDetailSAP.setFinalPrice(pric);
			paymentOrderDetailsSAP.add(paymentOrderDetailSAP);
			/*AccountglModel accountglModel = new AccountglModel();
			CurrencyamountModel currencyamountModel = new CurrencyamountModel();
			Extension2Model extension2Model = new Extension2Model();
			
			accountglModel.setCostcenter(i == 0 ? "":(i==1?"0062030401" : ""));
			accountglModel.setGlAccount(i >= 0 ? "2121000201" : "1201000101");
			accountglModel.setAcctType(i == 0 ? "K" : "S");
			accountglModel.setVendorNo("0001001874");
			accountglModel.setCompCode("6000");
			accountglModel.setPstngDate("20170121");
			accountglModel.setItemText(i == 0 ? "工资" : "收款明细");
			accountglModel.setAllocNmbr("OP036488");
			accountglModel.setItemnoAcc((i+1)*10L);
			accountglModel.setProfitCtr("0000006001");
			accountglModel.setBusArea("6001");
			accountglModels.add(accountglModel);
			
			  
            BigDecimal decimal = new BigDecimal(2.66);
            BigDecimal decimal2 = new BigDecimal(1.33);
            BigDecimal decimal1 = decimal.multiply(new BigDecimal(-1));
            
			currencyamountModel.setItemnoAcc((i+1)*10L);
			currencyamountModel.setCurrency("CNY");
			currencyamountModel.setAmtDoccur(i == 0 ? decimal1 : decimal2);
			currencyamountModels.add(currencyamountModel);
			extension2Model.setStructure("ZRSTRG");
//			extension2Model.setValuepart1(i == 0 ? "000010 40" : "000020 35");
			extension2Models.add(extension2Model);
			*/
		}
		
		/*documentHeaderModel.setDocType("RE");
		documentHeaderModel.setUsername("赵原");
		documentHeaderModel.setBusAct("RFBU");
		documentHeaderModel.setCompCode1("6000");
		documentHeaderModel.setFiscYear(2016);
		documentHeaderModel.setDocType("RE");
		documentHeaderModel.setDocDate("2017-01-14");
		documentHeaderModel.setPstngDate("2017-01-14");
//		documentHeaderModel.setFisPeriod(20);
		documentHeaderModel.setRefDocNo("PUEG2016080111");
		documentHeaderModel.setHeaderTxt("dfs");
		for (int i = 0; i < 2; i++) {
			AccountglModel accountglModel = new AccountglModel();
			CurrencyamountModel currencyamountModel = new CurrencyamountModel();
			Extension2Model extension2Model = new Extension2Model();
			
			accountglModel.setCostcenter(i == 0 ? "0062030401" : "");
			accountglModel.setGlAccount(i == 0 ? "5050100012" : "2181000901");
			accountglModel.setAcctType(i == 0 ? "S" : "K");
			accountglModel.setVendorNo("0001001874");
			accountglModel.setCompCode("6000");
			accountglModel.setPstngDate("20161218");
			accountglModel.setItemText(i == 0 ? "工资" : "收款明细");
			accountglModel.setAllocNmbr("OP036488");
			accountglModel.setItemnoAcc(i == 0 ? 10L : 20L);
			accountglModel.setProfitCtr("0000006001");
			accountglModel.setGlAccount( i == 0 ? "5050100012" : "2181000901");
			accountglModel.setBusArea("6001");
			accountglModels.add(accountglModel);
			
			  
            BigDecimal decimal = new BigDecimal(5000.0000);
            BigDecimal decimal1 = decimal.multiply(new BigDecimal(-1));
            
			currencyamountModel.setItemnoAcc(i == 0 ? 10L : 20L);
			currencyamountModel.setCurrency("CNY");
			currencyamountModel.setAmtDoccur(i == 0 ? decimal : decimal1);
			currencyamountModels.add(currencyamountModel);
			extension2Model.setStructure("ZRSTRG");
			extension2Model.setValuepart1(i == 0 ? "000010 40" : "000020 35");
			extension2Models.add(extension2Model);
			
		}*/
		
		/*List<AccountpayableModel> accountpayableModels = new ArrayList<AccountpayableModel>();
		for (int i = 0; i < 2; i++) {
			AccountpayableModel accountpayableModel= new AccountpayableModel();
			accountpayableModel.setSpGlInd("A");
			accountpayableModel.setCompCode("6000");
			accountpayableModel.setVendorNo("0001002076");
			accountpayableModel.setItemnoAcc(002L);
			accountpayableModels.add(accountpayableModel);
		}
		String billCode = "4500002232";*/		//.clearVouch(paymentOrderSAP, paymentOrderDetailsSAP);//getCheckPaymentVoucherFromSAP(paymentOrderSAP, paymentOrderDetailsSAP, null);//
		JSONObject mm = sapUtilService.createPrePaymentVoucherFromSAP(paymentOrderSAP, paymentOrderDetailsSAP);
		System.out.println("mmmm:"+mm);
	}
	
	public void testCheckPayFromSAP1()  throws Exception{
		
		List<AccountglModel> accountglModels = new  ArrayList<AccountglModel>();
		List<CurrencyamountModel> currencyamountModels = new ArrayList<CurrencyamountModel>();
		List<Extension2Model> extension2Models = new ArrayList<Extension2Model>();
		PaymentOrderModel paymentOrderSAP = new PaymentOrderModel();
		List<PaymentOrderDetailModel> paymentOrderDetailsSAP = new ArrayList<PaymentOrderDetailModel>();
		List<PaymentPaymentMapping> paymentPaymentMappings = new ArrayList<PaymentPaymentMapping>();
		
		DocumentHeaderModel documentHeaderModel = new DocumentHeaderModel();
//		paymentOrderSAP.setBusAct("RFBU");
		paymentOrderSAP.setApplyUser("赵原");
		paymentOrderSAP.setApplyUserCode("1000008");
		paymentOrderSAP.setCompanyCode("6000");
		paymentOrderSAP.setDocType("SA");
//		paymentOrderDetailSAP.setFiscYear(2017);
//		paymentOrderSAP.setDocType("RE");
//		paymentOrderDetailSAP.setDocDate("2017-01-21");
//		paymentOrderDetailSAP.setPstngDate("2017-01-21");
//		paymentOrderDetailSAP.setFisPeriod(20);
//		paymentOrderSAP.setCdocument("CGFK2017020299");
//		paymentOrderSAP.setVoucherId("1500000032");
		paymentOrderSAP.setCostCenterCode("0062030401");
		paymentOrderSAP.setSupplierCode("0000900666");//0000200297");//
		paymentOrderSAP.setProfitCenter("6000");
		paymentOrderSAP.setCurrencyCode("CNY");
		paymentOrderSAP.setApplyFor("dfdsf");
		paymentOrderSAP.setExchangeRate(new BigDecimal(1));
//		paymentOrderSAP.setVoucherId("0100000100");
//		for (int i = 0; i < 4; i++) {
		
			PaymentOrderDetailModel paymentOrderDetailSAP = new PaymentOrderDetailModel();
			paymentOrderDetailSAP.setLedgerAccount("2181009901");//1151000101");//5050100106");//");
//			paymentOrderDetailSAP.setVoucherId("1500000032");
//			paymentOrderDetailSAP.setHeaderTxt("1.21测试用");
			paymentOrderDetailSAP.setItemnoAcc(10L);
			BigDecimal pric = new BigDecimal(40).multiply(new BigDecimal(-1));
			paymentOrderDetailSAP.setPayPrice(pric);//.multiply(new BigDecimal(-1)));
			paymentOrderDetailSAP.setAdvancePrice(new BigDecimal(20));
			paymentOrderDetailSAP.setFinalPrice(pric);
			paymentOrderDetailSAP.setPaymentType("尾款");
//			paymentOrderDetailSAP.set
			paymentOrderDetailsSAP.add(paymentOrderDetailSAP);
			
			
			PaymentOrderDetailModel paymentOrderDetailSAP2 = new PaymentOrderDetailModel();
			paymentOrderDetailSAP2.setLedgerAccount("1151000101");//1151000101");//5050100106");//");
//			paymentOrderDetailSAP2.setVoucherId("1500000032");
//			paymentOrderDetailSAP2.setHeaderTxt("1.21测试用");
			paymentOrderDetailSAP2.setItemnoAcc(20L);
			BigDecimal pric2 = new BigDecimal(17).multiply(new BigDecimal(-1));
			paymentOrderDetailSAP2.setPayPrice(pric2);//.multiply(new BigDecimal(-1)));
			paymentOrderDetailSAP2.setAdvancePrice(new BigDecimal(20));
			paymentOrderDetailSAP2.setFinalPrice(pric2);
			paymentOrderDetailSAP2.setPaymentType("预付");
//			paymentOrderDetailSAP2.set
			paymentOrderDetailsSAP.add(paymentOrderDetailSAP2);
			
			PaymentOrderDetailModel paymentOrderDetailSAP3 = new PaymentOrderDetailModel();
			paymentOrderDetailSAP3.setLedgerAccount("2121000101");//1151000101");//5050100106");//");
//			paymentOrderDetailSAP3.setVoucherId("1500000032");
//			paymentOrderDetailSAP3.setHeaderTxt("1.21测试用");
			paymentOrderDetailSAP3.setItemnoAcc(30L);
			BigDecimal pric3 = new BigDecimal(60).multiply(new BigDecimal(-1));
			paymentOrderDetailSAP3.setPayPrice(pric3);//.multiply(new BigDecimal(-1)));
			paymentOrderDetailSAP3.setAdvancePrice(new BigDecimal(20));
			paymentOrderDetailSAP3.setFinalPrice(pric3);
			paymentOrderDetailSAP3.setPaymentType("应付款-验收");
//			paymentOrderDetailSAP3.set
			paymentOrderDetailsSAP.add(paymentOrderDetailSAP3);
			
			PaymentOrderDetailModel paymentOrderDetailSAP4 = new PaymentOrderDetailModel();
			paymentOrderDetailSAP4.setLedgerAccount("2121000101");//1151000101");//5050100106");//");
//			paymentOrderDetailSAP4.setVoucherId("1500000032");
//			paymentOrderDetailSAP4.setHeaderTxt("1.21测试用");
			paymentOrderDetailSAP4.setItemnoAcc(0001L);
			BigDecimal pric4 = new BigDecimal(117);
			paymentOrderDetailSAP4.setPayPrice(pric4);//.multiply(new BigDecimal(-1)));
			paymentOrderDetailSAP4.setAdvancePrice(new BigDecimal(11700));
			paymentOrderDetailSAP4.setFinalPrice(pric4);
			paymentOrderDetailSAP4.setPaymentType("应付账款");
			paymentOrderDetailSAP4.setVoucherId("1500000033");//100000145");
			paymentOrderDetailSAP4.setIds("A");//特殊总账标记
			paymentOrderDetailsSAP.add(paymentOrderDetailSAP4);
//		}
		JSONObject mm = sapUtilTailService.getCheckPaymentVoucherFromSAP(paymentOrderSAP, paymentOrderDetailsSAP, null);////.createPrePaymentVoucherFromSAP(paymentOrderSAP, paymentOrderDetailsSAP);//.clearVouch(paymentOrderSAP, paymentOrderDetailsSAP);//
		System.out.println("mmmm:"+mm);
			
	}
	
	public void testAssetsChangeFromSAP()  throws Exception{
		
		IpHistoryModel ipHistoryModel = new IpHistoryModel();
		IpInputModel ipInputModel = new IpInputModel();
		String ipAsset = "400963";//401901";
		ipInputModel.setCompanycode("8000");
//		ipInputModel.setAssetclass("4000");
		
//		资产负责人，需要修改时传
//		ipInputModel.setSerialNo("游希锋");
//		ipInputModel.setInventNo("OP036553-游希锋");
		
		
//		ipInputModel.setDescript("扫描枪/PDA Intenic ck3-转卖6000");
//		ipInputModel.setQuantity(3L);
//		ipInputModel.setNote("件");
//		ipInputModel.setBaseUom("A10");
		ipInputModel.setCostcenter("0085040100");//0088000006");////0069020000");//66030401");65010101");//
		
//		ipHistoryModel.setOaid("dfd");
		ipHistoryModel.setOaname("df");
//		ipHistoryModel.setOamodule("df");
//		ipHistoryModel.setSapid("401901");
//		ipHistoryModel.setDept(null);
//		ipHistoryModel.setCompany(null);
		ipHistoryModel.setRemark(null);
		JCoFunction functionC = destination.getRepository().getFunction("BAPI_TRANSACTION_COMMIT");
		for (int i = 0; i < 10; i++) {
			ipHistoryModel.setOano(i+"");
//			String ipAsset = "400963";//401901";
			ipInputModel.setSubnumber(i+"");
//			 JCoContext.begin(destination);
			JSONObject mm = sapUtilService.updateAssetsFromSAP(ipHistoryModel, ipInputModel, ipAsset );
			//提交事务
//			functionC.execute(destination);
//			JCoContext.end(destination);
			System.out.println("mmmm:"+mm);
		}
		for (int i = 0; i < 10; i++) {
			ipInputModel.setSubnumber(i+"");
			this.testReadAssetsCodeFromSAP1("8000", ipAsset ,i+"");//8000", "400963", "0001");
		}
	}
	
	public void testAssetsCompanyChangeFromSAP()  throws Exception{
		
		IAmtran iAmtran = new IAmtran();
		iAmtran.setCompCode("8000");
		iAmtran.setMainAssetNo("200005");
		iAmtran.setAssetSubNo("0000");
		iAmtran.setParCompCode("6000");
		iAmtran.setParAssetNo("100233");
		iAmtran.setParAssetSubNo("0000");
//		iAmtran.setTrava("0001");
//		iAmtran.setDocType("AA");
//		iAmtran.setCurrency("CNY");
		
		JSONObject mm = sapUtilTailService.updateAssetCompanyFromSAP(iAmtran);
		System.out.println("mmmm:"+mm);
	}
	
	
/*	public void testBillsCheckFromSAP(){
		
		JSONObject mm = sapUtilService.billsCheck(null);
		System.out.println("mmmm:"+mm);
	}*/
	
	public void clearVouchFromSAP(){
		
		List<AccountglModel> accountglModels = new  ArrayList<AccountglModel>();
		List<CurrencyamountModel> currencyamountModels = new ArrayList<CurrencyamountModel>();
		List<Extension2Model> extension2Models = new ArrayList<Extension2Model>();
		PaymentOrderModel paymentOrderSAP = new PaymentOrderModel();
		List<PaymentOrderDetailModel> paymentOrderDetailsSAP = new ArrayList<PaymentOrderDetailModel>();
		List<PaymentPaymentMapping> paymentPaymentMappings = new ArrayList<PaymentPaymentMapping>();
		
		DocumentHeaderModel documentHeaderModel = new DocumentHeaderModel();
//		paymentOrderSAP.setBusAct("RFBU");
		paymentOrderSAP.setApplyUser("赵原");
		paymentOrderSAP.setApplyUserCode("1000008");
		paymentOrderSAP.setCompanyCode("6000");
		paymentOrderSAP.setDocType("SA");
//		paymentOrderDetailSAP.setFiscYear(2017);
//		paymentOrderSAP.setDocType("RE");
//		paymentOrderDetailSAP.setDocDate("2017-01-21");
//		paymentOrderDetailSAP.setPstngDate("2017-01-21");
//		paymentOrderDetailSAP.setFisPeriod(20);
//		paymentOrderSAP.setCdocument("CGFK2017020299");
//		paymentOrderSAP.setVoucherId("1500000032");
		paymentOrderSAP.setCostCenterCode("0062030401");
		paymentOrderSAP.setSupplierCode("0000900666");//200297");//0000900666");
		paymentOrderSAP.setProfitCenter("6000");
		paymentOrderSAP.setCurrencyCode("CNY");
		paymentOrderSAP.setApplyFor("dfdsf");
		paymentOrderSAP.setExchangeRate(new BigDecimal(1));
//		paymentOrderSAP.setVoucherId("0100000100");
//		for (int i = 0; i < 4; i++) {
		
		/*	PaymentOrderDetailModel paymentOrderDetailSAP = new PaymentOrderDetailModel();
			paymentOrderDetailSAP.setLedgerAccount("2181009901");//1151000101");//5050100106");//");
//			paymentOrderDetailSAP.setVoucherId("1500000032");
//			paymentOrderDetailSAP.setHeaderTxt("1.21测试用");
			paymentOrderDetailSAP.setItemnoAcc(10L);
			BigDecimal pric = new BigDecimal(4000).multiply(new BigDecimal(-1));
			paymentOrderDetailSAP.setPayPrice(pric);//.multiply(new BigDecimal(-1)));
			paymentOrderDetailSAP.setAdvancePrice(new BigDecimal(20));
			paymentOrderDetailSAP.setFinalPrice(pric);
			paymentOrderDetailSAP.setPaymentType("尾款");
//			paymentOrderDetailSAP.set
			paymentOrderDetailsSAP.add(paymentOrderDetailSAP);
			
			
			PaymentOrderDetailModel paymentOrderDetailSAP2 = new PaymentOrderDetailModel();
			paymentOrderDetailSAP2.setLedgerAccount("1151000101");//1151000101");//5050100106");//");
//			paymentOrderDetailSAP2.setVoucherId("1500000032");
//			paymentOrderDetailSAP2.setHeaderTxt("1.21测试用");
			paymentOrderDetailSAP2.setItemnoAcc(20L);
			BigDecimal pric2 = new BigDecimal(1700).multiply(new BigDecimal(-1));
			paymentOrderDetailSAP2.setPayPrice(pric2);//.multiply(new BigDecimal(-1)));
			paymentOrderDetailSAP2.setAdvancePrice(new BigDecimal(20));
			paymentOrderDetailSAP2.setFinalPrice(pric2);
			paymentOrderDetailSAP2.setPaymentType("预付");
//			paymentOrderDetailSAP2.set
			paymentOrderDetailsSAP.add(paymentOrderDetailSAP2);
			
			PaymentOrderDetailModel paymentOrderDetailSAP3 = new PaymentOrderDetailModel();
			paymentOrderDetailSAP3.setLedgerAccount("2121000101");//1151000101");//5050100106");//");
//			paymentOrderDetailSAP3.setVoucherId("1500000032");
//			paymentOrderDetailSAP3.setHeaderTxt("1.21测试用");
			paymentOrderDetailSAP3.setItemnoAcc(30L);
			BigDecimal pric3 = new BigDecimal(6000).multiply(new BigDecimal(-1));
			paymentOrderDetailSAP3.setPayPrice(pric3);//.multiply(new BigDecimal(-1)));
			paymentOrderDetailSAP3.setAdvancePrice(new BigDecimal(20));
			paymentOrderDetailSAP3.setFinalPrice(pric3);
			paymentOrderDetailSAP3.setPaymentType("应付款-验收");
//			paymentOrderDetailSAP3.set
			paymentOrderDetailsSAP.add(paymentOrderDetailSAP3);
			*/
			PaymentOrderDetailModel paymentOrderDetailSAP4 = new PaymentOrderDetailModel();
			paymentOrderDetailSAP4.setLedgerAccount("1151000101");//2121000101");//1151000101");//5050100106");//");
//			paymentOrderDetailSAP4.setVoucherId("1500000032");
//			paymentOrderDetailSAP4.setHeaderTxt("1.21测试用");
			paymentOrderDetailSAP4.setItemnoAcc(0001L);
			BigDecimal pric4 = new BigDecimal(17);
			paymentOrderDetailSAP4.setPayPrice(pric4);//.multiply(new BigDecimal(-1)));
			paymentOrderDetailSAP4.setAdvancePrice(new BigDecimal(17));
			paymentOrderDetailSAP4.setFinalPrice(pric4);
			paymentOrderDetailSAP4.setPaymentType("pre应付账款");
			paymentOrderDetailSAP4.setVoucherId("0100000290");//1500000033");//100000145");
			paymentOrderDetailSAP4.setIds("A");//特殊总账标记
			paymentOrderDetailsSAP.add(paymentOrderDetailSAP4);
//		}
		/*JSONObject mm = sapUtilService.getCheckPaymentVoucherFromSAP(paymentOrderSAP, paymentOrderDetailsSAP, null);////.createPrePaymentVoucherFromSAP(paymentOrderSAP, paymentOrderDetailsSAP);//.clearVouch(paymentOrderSAP, paymentOrderDetailsSAP);//
		System.out.println("mmmm:"+mm);*/
			
			PaymentOrderDetailModel paymentOrderDetailSAP2 = new PaymentOrderDetailModel();
			paymentOrderDetailSAP2.setLedgerAccount("2121000101");//1151000101");//1151000101");//5050100106");//");
			paymentOrderDetailSAP2.setVoucherId("0100000289");//1500000145");
//			paymentOrderDetailSAP2.setHeaderTxt("1.21测试用");
			paymentOrderDetailSAP2.setItemnoAcc(2L);
			BigDecimal pric2 = new BigDecimal(17).multiply(new BigDecimal(-1));
			paymentOrderDetailSAP2.setPayPrice(pric2);//.multiply(new BigDecimal(-1)));
			paymentOrderDetailSAP2.setAdvancePrice(new BigDecimal(20));
			paymentOrderDetailSAP2.setFinalPrice(pric2);
			paymentOrderDetailSAP2.setPaymentType("预付");
//			paymentOrderDetailSAP2.setVoucherId("1500000032");//100000148");
			paymentOrderDetailSAP2.setIds("A");//特殊总账标记
			paymentOrderDetailsSAP.add(paymentOrderDetailSAP2);
//			paymentOrderSAP.setVoucherId("1500000145");
			paymentOrderSAP.setCdocument("CGFK2017020299");
			JSONObject mm = sapUtilTailService.clearVouch(paymentOrderSAP, paymentOrderDetailsSAP);
			System.out.println("mmmm:"+mm);
	
	}
	
public void testRetireFromSAP(){
		
		Generalpostingdata gData = new Generalpostingdata();	
		Retirementdata rData = new Retirementdata();
		gData.setCompCode("8000");
		gData.setAssetMaino("000000200004");
		gData.setAssetSubno("0000");
		gData.setPstngDate(this.dateToString(new Date()));
//		gData.setAssetTrtyp(assetTrtyp)
		gData.setDocDate(this.dateToString(new Date()));
		gData.setDocType("AA");
		gData.setTransDate(this.dateToString(new Date()));
		rData.setPercRate(new BigDecimal(100));
		
		/*rData.setAmount(new BigDecimal(0.23));
		rData.setRevOnRet(new BigDecimal(0.03));
		rData.setRetCosts(new BigDecimal(0.2));*/
		rData.setCurrency("CNY");
		rData.setNotAll(true);	//部分报废
		
//		rData.setQuantity(1);
//		gData.setUserName(")
		/*DiscardeModel discardeModel = new DiscardeModel();
		discardeModel.setCompCode("6000");
		discardeModel.setAssetMaino("000000402143");
		discardeModel.setAssetSubno("0000");
		discardeModel.setPstngDate(new Date());
		discardeModel.setDocDate(new Date());
		discardeModel.setAssetPriceDate(new Date());
		discardeModel.setDateCurrent("20170316");
		discardeModel.setDocType("AA");
		discardeModel.setRetireType(true);
		discardeModel.setPercRate(new BigDecimal(0.01));
		discardeModel.setAmount(new BigDecimal(0.23));
		discardeModel.setRevOnRet(new BigDecimal(0.11));
		discardeModel.setRetCosts(new BigDecimal(0.12));*/
//		discardeModel
//		discardeModel*/
		JSONObject mm = sapUtilTailService.creatScrapCertificateFromSAP(gData,rData );
		System.out.println("mmmm:"+mm);
	}
	
	
}
