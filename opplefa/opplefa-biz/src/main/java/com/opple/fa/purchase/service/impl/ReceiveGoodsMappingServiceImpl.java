package com.opple.fa.purchase.service.impl;


import com.opple.common.dao.WorkFlowMapper;
import com.opple.fa.assetcard.dao.AdministrationAssetDAO;
import com.opple.fa.assetcard.dao.ComputerAssetDAO;
import com.opple.fa.assetcard.dao.GeneralAssetDAO;
import com.opple.fa.assetcard.dao.MachineryEquipmentDAO;
import com.opple.fa.assetcard.dao.MeasuringInstrumentsDAO;
import com.opple.fa.assetcard.entity.AdministrationAsset;
import com.opple.fa.assetcard.entity.ComputerAsset;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.entity.MachineryEquipment;
import com.opple.fa.assetcard.entity.MeasuringInstruments;
import com.opple.fa.purchase.dao.ApplyOrderDAO;
import com.opple.fa.purchase.dao.ApplyOrderDetailDAO;
import com.opple.fa.purchase.dao.PurchaseCommonDAO;
import com.opple.fa.purchase.dao.PurchaseOrderDAO;
import com.opple.fa.purchase.dao.PurchaseOrderDetailDAO;
import com.opple.fa.purchase.dao.ReceiveGoodsDAO;
import com.opple.fa.purchase.dao.ReceiveGoodsDetailDAO;
import com.opple.fa.purchase.dao.ReceiveGoodsMappingDAO;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.entity.ReceiveGoods;
import com.opple.fa.purchase.entity.ReceiveGoodsDetail;
import com.opple.fa.purchase.entity.ReceiveGoodsMapping;
import com.opple.fa.purchase.model.ReceiveGoodsModel;
import com.opple.fa.purchase.service.ReceiveGoodsMappingService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.workflow.WorkFlowStub;
import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Getter;
import lombok.Setter;
import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReceiveGoodsMappingServiceImpl implements ReceiveGoodsMappingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveGoodsMappingServiceImpl.class);

    @Getter
    @Setter
    private ReceiveGoodsMappingDAO receiveGoodsMappingDAO;
    @Getter
    @Setter
    private ReceiveGoodsDAO receiveGoodsDAO;
    @Getter
    @Setter
    private ReceiveGoodsDetailDAO receiveGoodsDetailDAO;
    @Getter
    @Setter
    private PurchaseCommonDAO purchaseCommonDAO;
    @Getter
    @Setter
    private ApplyOrderDetailDAO applyOrderDetailDAO;
    @Getter
    @Setter
    private ApplyOrderDAO applyOrderDAO;
    @Getter
    @Setter
    private PurchaseOrderDetailDAO purchaseOrderDetailDAO;
    @Getter
    @Setter
    private PurchaseOrderDAO purchaseOrderDAO;
    @Getter
    @Setter
    private AdministrationAssetDAO administrationAssetDAO;

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
    private WorkFlowMapper workFlowMapper;

    /**
     * 管理人员 已收货 查询
     *
     * @param pager
     * @param receiveGoodsMapping
     * @return
     */
    @Override
    public Pager<ReceiveGoodsMapping> searchReceiveGoodsMapping(Pager<ReceiveGoodsMapping> pager, ReceiveGoodsMapping receiveGoodsMapping) {
        List<ReceiveGoodsMapping> searchReceiveGoodsMapping = receiveGoodsMappingDAO.searchReceiveGoodsMapping(pager, receiveGoodsMapping);
        Long searchReceiveGoodsMappingCount = receiveGoodsMappingDAO.searchReceiveGoodsMappingCount(receiveGoodsMapping);
        return Pager.cloneFromPager(pager, searchReceiveGoodsMappingCount, searchReceiveGoodsMapping);
    }

    @Override
    public Pager<ReceiveGoodsMapping> searchAllReceiveGoodsMapping(Pager<ReceiveGoodsMapping> pager, ReceiveGoodsMapping receiveGoodsMapping) {
        List<ReceiveGoodsMapping> searchReceiveGoodsMapping = receiveGoodsMappingDAO.searchAllReceiveGoodsMapping(pager, receiveGoodsMapping);
        Long searchReceiveGoodsMappingCount = receiveGoodsMappingDAO.searchAllReceiveGoodsMappingCount(receiveGoodsMapping);
        return Pager.cloneFromPager(pager, searchReceiveGoodsMappingCount, searchReceiveGoodsMapping);
    }

    @Override
    public ReceiveGoodsMapping searchPurchaseOrderMappingByPurchaseId(String purchaseId) {
        return receiveGoodsMappingDAO.searchPurchaseOrderMappingByPurchaseId(purchaseId);
    }

    @Override
    public List<ReceiveGoodsMapping> searchPurchaseOrderDetailMappingByPurchaseId(String purchaseId) {
        return receiveGoodsMappingDAO.searchPurchaseOrderDetailMappingByPurchaseId(purchaseId);
    }

    @Override
    public List<ReceiveGoodsMapping> searchPurchaseApplyMappingByPurchaseId(String purchaseId) {
        return receiveGoodsMappingDAO.searchPurchaseApplyMappingByPurchaseId(purchaseId);
    }

    //	查新公共表里的sap次级编码
    @Override
    public List<ReceiveGoodsMapping> searchPurchaseCommonSapAssetsCodeByPurchaseId(String purchaseId) {
        return receiveGoodsMappingDAO.searchPurchaseCommonSapAssetsCodeByPurchaseId(purchaseId);
    }

    //	资产编码 sap次级拆分后的查询
    @Override
    public List<ReceiveGoodsModel> searchSapAssetsCodeByPurchaseId(String purchaseId) {
        ReceiveGoodsModel receiveGoodsModel = null;
        ReceiveGoodsModel rgm = null;
        List<ReceiveGoodsModel> aspAssetsDemandIdList = new ArrayList<ReceiveGoodsModel>();
        List<ReceiveGoodsModel> aspAssetsDemandIdList2 = new ArrayList<ReceiveGoodsModel>();
        List<ReceiveGoodsMapping> demandIdList = receiveGoodsMappingDAO.searchDemandIdByPurchaseId(purchaseId);
        for (int i = 0; i < demandIdList.size(); i++) {
            for (int j = 0; j < demandIdList.get(i).getDemandCount(); j++) {
                receiveGoodsModel = new ReceiveGoodsModel();
                receiveGoodsModel.setPurchaseId(demandIdList.get(i).getPurchaseId());
                receiveGoodsModel.setPurchaseDetailId(demandIdList.get(i).getPurchaseDetailId());
                receiveGoodsModel.setDemandCount(demandIdList.get(i).getDemandCount());
                receiveGoodsModel.setDemandId(demandIdList.get(i).getDemandId());
                receiveGoodsModel.setDemandDetailId(demandIdList.get(i).getDemandDetailId());
                receiveGoodsModel.setApplyDetailId(demandIdList.get(i).getApplyDetailId());
                receiveGoodsModel.setAssetsName(demandIdList.get(i).getAssetsName());
                receiveGoodsModel.setBrand(demandIdList.get(i).getBrand());
                receiveGoodsModel.setFunctions(demandIdList.get(i).getFunctions());
                receiveGoodsModel.setCheckApplyCode(demandIdList.get(i).getCheckApplyCode());
                receiveGoodsModel.setCheckApplyUser(demandIdList.get(i).getCheckApplyUser());
                receiveGoodsModel.setBudgetDepartmentCode(demandIdList.get(i).getBudgetDepartmentCode());
                receiveGoodsModel.setBudgetDepartmentName(demandIdList.get(i).getBudgetDepartmentName());
                receiveGoodsModel.setCostCenter(demandIdList.get(i).getCostCenter());
                receiveGoodsModel.setCostCenterCode(demandIdList.get(i).getCostCenterCode());

                aspAssetsDemandIdList2.add(receiveGoodsModel);
            }
        }
        //List<ReceiveGoodsMapping> aspAssetsList = receiveGoodsMappingDAO.searchSapAssetsCodeByPurchaseId(purchaseId);
        List<ReceiveGoodsMapping> aspAssetsList = receiveGoodsMappingDAO.searchPurchaseCommonByPurchaseId(purchaseId);
        for (int j = 0; j < aspAssetsList.size(); j++) {
            rgm = new ReceiveGoodsModel();
            rgm.setPurchaseId(aspAssetsList.get(j).getPurchaseId());
            rgm.setPurchaseDetailId(aspAssetsList.get(j).getPurchaseDetailId());
            rgm.setSapAssetsCode(aspAssetsList.get(j).getSapAssetsCode());
            rgm.setSapMainAssetCode(aspAssetsList.get(j).getSapMainAssetCode());
            rgm.setSapSecAssetCode(aspAssetsList.get(j).getSapSecAssetCode());
            rgm.setDemandCount(aspAssetsDemandIdList2.get(j).getDemandCount());
            rgm.setDemandId(aspAssetsDemandIdList2.get(j).getDemandId());
            rgm.setDemandDetailId(aspAssetsDemandIdList2.get(j).getDemandDetailId());
            rgm.setApplyDetailId(aspAssetsDemandIdList2.get(j).getApplyDetailId());
            rgm.setAssetsName(aspAssetsDemandIdList2.get(j).getAssetsName());
            rgm.setBrand(aspAssetsDemandIdList2.get(j).getBrand());
            rgm.setFunctions(aspAssetsDemandIdList2.get(j).getFunctions());
            rgm.setCheckApplyCode(aspAssetsDemandIdList2.get(j).getCheckApplyCode());
            rgm.setCheckApplyUser(aspAssetsDemandIdList2.get(j).getCheckApplyUser());
            rgm.setBudgetDepartmentCode(aspAssetsDemandIdList2.get(j).getBudgetDepartmentCode());
            rgm.setBudgetDepartmentName(aspAssetsDemandIdList2.get(j).getBudgetDepartmentName());
            rgm.setCostCenterCode(aspAssetsDemandIdList2.get(j).getCostCenterCode());
            rgm.setCostCenter(aspAssetsDemandIdList2.get(j).getCostCenter());
            aspAssetsDemandIdList.add(rgm);
        }
        return aspAssetsDemandIdList;
    }

    //	存放位置弹框
    @Override
    public List<ReceiveGoodsMapping> searchStorageLocationByOfficeLocation(ReceiveGoodsMapping receiveGoodsMapping) {
        return receiveGoodsMappingDAO.searchStorageLocationByOfficeLocation(receiveGoodsMapping);
    }


    /**
     * 保存
     * 注意：receiveGoodsMappings 传来的是所有的行对应的需求明细行，回写时，要注意判断选定的订单行对应的需求明细行
     *
     * @param receiveGoods
     * @param receiveGoodsDetails
     * @param receiveGoodsMappings
     * @param purchaseCommons
     * @return
     */
    @Override
    public Long save(ReceiveGoods receiveGoods, List<ReceiveGoodsDetail> receiveGoodsDetails, List<ReceiveGoodsMapping> receiveGoodsMappings, List<PurchaseCommon> purchaseCommons) {
        /**
         *	头信息保存
         */
        receiveGoodsDAO.saveReceiveGoods(receiveGoods); //收货主表中保存基本信息
        PurchaseCommon pc = null;
        Long isSaveDetail = null;
        ApplyOrderDetail aod = new ApplyOrderDetail();
        ApplyOrder ao = new ApplyOrder();
        PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        /**
         *	行 保存收货行明细
         *brand是空的，要获取到
         */
        for (ReceiveGoodsDetail receiveGoodsDetail : receiveGoodsDetails) {
            receiveGoodsDetail.setBrand(purchaseCommons.get(0).getBrand());
            receiveGoodsDetail.setPurchaseId(receiveGoods.getPurchaseId());
            isSaveDetail = receiveGoodsDetailDAO.saveReceiveGoodsDetail(receiveGoodsDetail);
        }
        /**
         * 如果不是草稿，则回写数据
         * 如果是草稿，只更新purchasecommon
         */
        if ("N".equals(receiveGoods.getDraft())) {
            /*
        * 回写采购订单明细表 的收货数量	（回写tb_fa_purchase_order_detail.goods_count）
		* goods_count 收货数量（对一条订单明细进行收货，即对本行的apply_count进行收货）
		* */
            for (ReceiveGoodsDetail rgd : receiveGoodsDetails) {
                pc = new PurchaseCommon();
                pc.setPurchaseId(rgd.getPurchaseId());
                //			pc.setPurchaseDetailId(rgd.getPurchaseDetailId());
                pc.setApplyDetailId(rgd.getApplyDetailId());
                pc.setThisGoodsCount(rgd.getThisGoodsCount());
                purchaseCommonDAO.updatePurchaseOrderDetailByPurchaseDetailId(pc);
            }
            //更细采购订单主表状态
            //purchaseOrderDAO.updateOrderStatusByGoodsCount(receiveGoods.getPurchaseId());
            for (ReceiveGoodsDetail rgd : receiveGoodsDetails) {
                for (ReceiveGoodsMapping rgm : receiveGoodsMappings) {
                /*
                 * 回写订单申请映射表 的收货数量  （回写tb_fa_purchase_apply_mapping.goods_count）
				 * tb_fa_purchase_apply_mapping 采购订单明细和采购申请明细关联表
				 * goods_count 收货数量(对下订单的需求数量进行收货的数量，即对本行的Apply_count 进行收货的数量)
				* */
                    if ((rgd.getPurchaseId()).equals(rgm.getPurchaseId()) && (rgd.getApplyDetailId()).equals(rgm.getApplyDetailId())) {
                        pc = new PurchaseCommon();
                        pc.setPurchaseId(rgm.getPurchaseId());
                        //					pc.setPurchaseDetailId(rgm.getPurchaseDetailId());
                        pc.setApplyDetailId(rgm.getApplyDetailId());
                        pc.setDemandDetailId(rgm.getDemandDetailId());
                        pc.setThisGoodsCountDemand(rgm.getThisGoodsCountDemand());
                        purchaseCommonDAO.updatePurchaseApplyMappingByDemandDetailId(pc);
                    }
				/*
				*保存到需求明细表(TB_FA_RECEIVE_CHECK_MAPPING)，验收时要用
				*TB_FA_RECEIVE_CHECK_MAPPING 收货单保存收货的需求数量，验收时要用
				*/
                    if ((rgd.getPurchaseId()).equals(rgm.getPurchaseId()) && (rgd.getPurchaseDetailId()).equals(rgm.getPurchaseDetailId())) {
                        receiveGoodsMappingDAO.saveReceiveCheckMapping(rgm);
                    }
                }
            }


		/*回写资产卡片的表（tb_fa_computer_asset--IT资产（电脑类）*/
            //		String assetsType = checkAcceptance.getAssetsType();

            //		String assetsType = receiveGoods.getAssetsType();
            String createBy = receiveGoods.getCuserName();
            String purchaseId = receiveGoods.getPurchaseId();   //采购订单：PURCHASE_ID
            String officeLocation = receiveGoods.getOfficeLocation();
            String companyName = receiveGoods.getCompanyName(); //公司名称
            //String persentDepartment = receiveGoods.getBudgetDepartmentName();
            String supplierName = receiveGoods.getSupplierName();
            String supplierCode = receiveGoods.getSupplierCode();
            String platform = receiveGoods.getPlatform();
            String costCenterCode = receiveGoods.getCostCenterCode();
            ApplyOrderDetail aod3 = new ApplyOrderDetail();
            int num = 0;
            for (ReceiveGoodsDetail rgd : receiveGoodsDetails) {
                for (int i = 0; rgd.getThisGoodsCount() > i; i++) {

                    aod3.setId(Long.parseLong(rgd.getApplyDetailId()));
                    aod3 = applyOrderDetailDAO.searchApplyOrderDetailById(aod3);


                    String unit = rgd.getUnits();
                    String assetsType = rgd.getAssetsType();
                    String assetsName = rgd.getAssetsName();
                    purchaseCommons.get(num).setReceiveGoodsDetailId(rgd.getId().toString());
                    String location = purchaseCommons.get(num).getStorageLocation();  //存放位置
                    String applyId = rgd.getApplyOrderId();  //采购申请单据号
                    /**
                     * 获取需求单据号
                     */
                    String demandId = purchaseCommons.get(num).getDemandId();  //采购需求单据号
				/*行政资产和非电脑类 资产卡片主表 保存一些公共的字段  */
                    GeneralAsset generalAsset = new GeneralAsset();
                    generalAsset.setAssetType(assetsType); //资产类型
                    generalAsset.setAssetState("0"); //资产状态 预验收 0    已验收1
                    //在采购申请详情表中根据主键来获取资产分类
                    aod.setId(Long.parseLong(rgd.getApplyDetailId()));
                    aod = applyOrderDetailDAO.searchApplyOrderDetailById(aod);
                    generalAsset.setAssetCode(aod.getMainAssetCode() + "-" + Integer.parseInt(purchaseCommons.get(num).getSapSecAssetCode())); //主资产编码 - 次级编码（sap编码）
                    ao.setCdocument(applyOrderDetailDAO.searchApplyOrderDetailById(aod).getApplyOrderId());
                    ao = applyOrderDAO.searchApplyOrderByOrderId(ao);
                    generalAsset.setAssetClassification(applyOrderDetailDAO.searchApplyOrderDetailById(aod).getAssetClassification());  //资产分类
                    if ("Y".equals(ao.getExpensing())) {
                        ao.setExpensing("1");
//					系统管理编号 (是)
                        generalAsset.setSysteManagementCode(workFlowMapper.getDocumentByType("FA_MMFY"));
                    } else if ("N".equals(ao.getExpensing())) {
                        ao.setExpensing("0");
//					系统管理编号 (否)
                        generalAsset.setSysteManagementCode(workFlowMapper.getDocumentByType("FA_MMZB"));
                    }
                    purchaseCommons.get(num).setSysteManagementCode(generalAsset.getSysteManagementCode()); //上传图片的document
                    generalAsset.setIsExpenseAssets(ao.getExpensing()); //是否为一次性费用化资产
                    //rgd.getPurchaseDetailId()//订单详情ID（订单详情表主键）
                    generalAsset.setSpecificationModel(purchaseOrderDetailDAO.searchPurchaseOrderDetailById(rgd.getPurchaseDetailId()).getBrand()); //品牌/规格/型号
                    generalAsset.setSelectAccessories(applyOrderDetailDAO.searchApplyOrderDetailById(aod).getOptionalAccessories()); //选配件
                    generalAsset.setUseState("0"); //资产状态（0在用）
                    generalAsset.setPurchaseArrivalDate(rgd.getDeliveryDate()); //采购到货日期*（收货日期）
                    generalAsset.setDateOfAcceptance(rgd.getRequireCheckDate()); //计划验收日期
                    //js中默认验收周期计量器具45天，非计量器具30天
                    long betweenDays = (rgd.getRequireCheckDate().getTime() - rgd.getDeliveryDate().getTime()) / (1000 * 60 * 60 * 24);
                    generalAsset.setAcceptanceCycle(String.valueOf(betweenDays)); //验收周期
                    generalAsset.setWarrantyPeriod(rgd.getRecWarrantyPeriod()); //质保周期
                    generalAsset.setPlatform(platform); //平台
                    generalAsset.setCompanyName(companyName); //所属公司
                    generalAsset.setCompanyCode(ao.getCompanyCode()); //所属公司编码
                    generalAsset.setPersentDepartment(receiveGoods.getCostCenter()); //现部门和成本中心
                    generalAsset.setUseDepartment(ao.getBudgetDepartmentName()); //使用部门*
//					generalAsset.setAssetPerson(ao.getApplyUser()); //管理责任人*
//					generalAsset.setAssetPersonCode(ao.getApplyUserCode()); //管理责任人編碼*
                    generalAsset.setAssetPerson(purchaseCommons.get(num).getCheckApplyUser());
                    generalAsset.setAssetPersonCode(purchaseCommons.get(num).getCheckApplyCode());
                    generalAsset.setSupplierName(supplierName); //供应商名称
                    generalAsset.setSupplierCode(supplierCode);  //供应商编码
                    generalAsset.setIsUpdateSap("0");  //是否更新了 sap  1为 是  0 为  否
                    generalAsset.setDifferentiatePurchase("0");  //区分是页面新增卡片还是收货后新增卡片
                    generalAsset.setCostCenterCode(costCenterCode);
                    generalAsset.setUnit(unit); //单位
                    generalAsset.setLocation(location); //位置区域
                    generalAsset.setOfficeLocation(officeLocation); //办公地点
                    generalAsset.setAssetNetValue("0.00"); //资产净值assetNetValue
                    generalAsset.setAssetOriginalValue("0.00"); //资产原值assetOriginalValue
                    generalAsset.setPeriodOfDepreciation("0"); //折旧年限periodOfDepreciation
                    generalAsset.setMonthCountingDepreciation("0.00"); //月折旧额monthCountingDepreciation
                    generalAsset.setCurrentYearDepreciation("0.00"); //本年已计提累计折旧currentYearDepreciation
                    generalAsset.setAddDepreciation("0.00"); //累计折旧addDepreciation
                    /**
                     * 获取相对应的采购订单主表行,进而读取供应商信息
                     */
                    purchaseOrder.setCdocument(rgd.getPurchaseId());
                    purchaseOrder = purchaseOrderDAO.searchPurchaseOrderByOrderId(purchaseOrder);
                    generalAsset.setSupplierLinkman(purchaseOrder.getContacts()); //供应商联系人
                    generalAsset.setSupplierPhone(purchaseOrder.getTelephone()); //供应商电话
                    generalAsset.setDemandId(demandId); //采购需求单据号
                    generalAsset.setApplyId(applyId); //采购申请单据号
                    generalAsset.setPurchaseId(purchaseId);  //采购订单：PURCHASE_ID
                    generalAsset.setAssetName(assetsName); //资产名称
                    generalAsset.setCreateBy(createBy); //创建人
                    generalAsset.setCreateDate(new Date()); //创建时间
                    Long counts = generalAssetDAO.assetCodeCount(generalAsset);
                    GeneralAsset generalAsset1 = new GeneralAsset();
                    if ("指定".equals(aod3.getCodingMode()) && counts > 0) {
                        //当指定类型的资产，在资产表中有信息时，不进行新增
                        generalAsset1 = generalAssetDAO.searchGeneralAssetOnly(generalAsset);
                    } else {
                        if ("IT资产".equals(assetsType)) {
					/*IT资产  资产卡片电脑类 */
                            ComputerAsset computerAsset = new ComputerAsset();
                            generalAsset.setAssetType("0");
                            computerAssetDAO.insertComputerAsset(computerAsset);
                            generalAsset.setAssetDetailId(Integer.parseInt(computerAsset.getId() + ""));
                            generalAssetDAO.insertGeneralAsset(generalAsset);
                        }
                        if ("行政资产".equals(assetsType)) {
                            /**
                             * 行政资产
                             */
                            AdministrationAsset administrationAsset = new AdministrationAsset();
                            administrationAsset.setIsBuildingContruction(ao.getBuilding()); //是否为房屋建筑
                            generalAsset.setAssetType("1");
                            administrationAssetDAO.insertAdministrationAsset(administrationAsset);
                            generalAsset.setAssetDetailId(Integer.parseInt(administrationAsset.getId() + ""));
                            generalAssetDAO.insertGeneralAsset(generalAsset);
                        }
                        if ("计量器具".equals(assetsType)) {
					/*计量器具*/
                            MeasuringInstruments measuringInstruments = new MeasuringInstruments();
                            generalAsset.setAssetType("2");
                            measuringInstrumentsDAO.insertMeasuringInstruments(measuringInstruments);
                            generalAsset.setAssetDetailId(Integer.parseInt(measuringInstruments.getId() + ""));
                            generalAssetDAO.insertGeneralAsset(generalAsset);
                        }
                        if ("机器设备".equals(assetsType)) {
					/*机器设备*/
                            MachineryEquipment machineryEquipment = new MachineryEquipment();
                            machineryEquipment.setShopType(ao.getWorkShopType()); //车间类型
                            generalAsset.setAssetType("3");
                            machineryEquipmentDAO.insertMachineryEquipment(machineryEquipment);
                            generalAsset.setAssetDetailId(Integer.parseInt(machineryEquipment.getId() + ""));
                            generalAssetDAO.insertGeneralAsset(generalAsset);
                        }
                    }

//				purchaseCommons.get(i).setCardId(generalAsset.getId() + "");
                    if ("指定".equals(aod3.getCodingMode()) && counts > 0) {
                        purchaseCommons.get(num).setCardId(generalAsset1.getId() + "");
                        purchaseCommons.get(num).setRecWarrantyPeriod(generalAsset1.getWarrantyPeriod());
                    } else {
                        purchaseCommons.get(num).setCardId(generalAsset.getId() + "");
                        purchaseCommons.get(num).setRecWarrantyPeriod(generalAsset.getWarrantyPeriod());
                    }

                    num++;
                }
            }
            /**
             保存sap资产公共表(TB_FA_PURCHASE_COMMON PC)
             收货单保存时将收货单明细相应的收货信息保存，验收单保存时也要保存相应的需求单信息；
             */
            for (PurchaseCommon purchaseCommon : purchaseCommons) {
                purchaseCommon.setCompanyCode(receiveGoods.getCompanyCode());
                purchaseCommon.setPurchaseId(receiveGoods.getPurchaseId());
                //purchaseCommonDAO.savePurchaseCommon(purchaseCommon); 在订单时新增，收货时更新
                purchaseCommonDAO.updatePurchaseCommonAddCardId(purchaseCommon);
            }

        } else {
            //保存草稿时，PC表中没有保存收货详情id，会在修改时保存进去
            for (PurchaseCommon purchaseCommon : purchaseCommons) {
                purchaseCommon.setCompanyCode(receiveGoods.getCompanyCode());
                purchaseCommon.setPurchaseId(receiveGoods.getPurchaseId());
                //purchaseCommonDAO.savePurchaseCommon(purchaseCommon); 在订单时新增，收货时更新
                purchaseCommonDAO.updatePurchaseCommonDraft(purchaseCommon); //和保存的区别是没有更新收货状态
            }
        }

        return isSaveDetail;
    }


    //	同意审批
    @Override
    public ExecuteResult<ReceiveGoods> agreeWorkFlow(ReceiveGoodsModel receiveGoodsModel) {
        ExecuteResult<ReceiveGoods> executeResult = new ExecuteResult<ReceiveGoods>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();
            agreeFlowParam.setBillCode(receiveGoodsModel.getCdocument());
            agreeFlowParam.setBillTableName("TB_FA_RECEIVE_GOODS");
            agreeFlowParam.setBillCodeColumnName("CDOCUMENT");
            agreeFlowParam.setCApprovalId(UUID.randomUUID().toString());
            agreeFlowParam.setDeptCode(receiveGoodsModel.getApplyDepartmentCode()); //单据所属部门编码
            agreeFlowParam.setDeptName(receiveGoodsModel.getApplyDepartment()); //单据所属部门名称
            agreeFlowParam.setCreatorCode(receiveGoodsModel.getCuserCode()); //单据创建人编码
            agreeFlowParam.setCreatorName(receiveGoodsModel.getCuserName());
            if (null == receiveGoodsModel.getCreateDate()) {
                executeResult.addErrorMessage("创建日期不能为空");
                return executeResult;
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime(receiveGoodsModel.getCreateDate());
            agreeFlowParam.setCreateDate(instance);
            agreeFlowParam.setCUserCode(receiveGoodsModel.getCuserCode()); //当前操作人编码
            agreeFlowParam.setCUserName(receiveGoodsModel.getCuserName());
            agreeFlowParam.setUserDepCode(receiveGoodsModel.getUserDepCode()); //当前操作人所属部门编码
            agreeFlowParam.setAMoney("0");
            agreeFlowParam.setTypeCode("77077");
            agreeFlowParam.setMoney("0");
            agreeFlowParam.setIdea(receiveGoodsModel.getIdea());
            agreeFlowParam.setIsEmail(receiveGoodsModel.getIsEmail());
            agreeFlowParam.setIsPhoneMessage(receiveGoodsModel.getIsPhoneMessage());
            agreeFlowParam.setFeeItemCode("");
            agreeFlowParam.setCCostCenterCode(receiveGoodsModel.getCostCenterCode());
            agree.setUserCode(receiveGoodsModel.getCuserCode());
            agree.setUserName(receiveGoodsModel.getCuserName());
            agree.setParam(agreeFlowParam);
            WorkFlowStub.AgreeResponse agreeResponse = stub.agree(agree);
            if (StringUtils.isNotBlank(agreeResponse.getErrMessage())) {
                ifRollback = true;
                executeResult.addErrorMessage(agreeResponse.getErrMessage());
                LOGGER.error("====" + agreeResponse.getErrMessage() + "====");
                return executeResult;
            }
        } catch (AxisFault axisFault) {
            ifRollback = true;
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            LOGGER.error("Exception", e);
        }
        if (ifRollback) {
            executeResult.addErrorMessage("调用审批流失败");
            return executeResult;
        }
        executeResult.setSuccessMessage("调用同意审批流成功");
        return executeResult;
    }

    //	退回
    @Override
    public ExecuteResult<ReceiveGoods> denyWorkFlow(ReceiveGoodsModel receiveGoodsModel) {
        ExecuteResult<ReceiveGoods> executeResult = new ExecuteResult<ReceiveGoods>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();
            backFlowParam.setBillCode(receiveGoodsModel.getCdocument());
            backFlowParam.setBillTableName("TB_FA_RECEIVE_GOODS");
            backFlowParam.setCreatorCode(receiveGoodsModel.getCuserCode()); //单据创建人编码
            backFlowParam.setCreatorName(receiveGoodsModel.getCuserName());
            backFlowParam.setIdea(receiveGoodsModel.getIdea());
            backFlowParam.setCUserCode(receiveGoodsModel.getCuserCode()); //当前操作人编码
            backFlowParam.setCUserName(receiveGoodsModel.getCuserName());
            backFlowParam.setUserDepCode(receiveGoodsModel.getUserDepCode()); //当前操作人所属部门编码
            backFlowParam.setTypeCode("77077");
            backFlowParam.setActivityCode("");
            backFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());
            backFlowParam.setIsEmail(receiveGoodsModel.getIsEmail());
            backFlowParam.setIsPhoneMessage(receiveGoodsModel.getIsPhoneMessage());
            back.setParam(backFlowParam);
            back.setUserCode(receiveGoodsModel.getCuserCode());
            back.setUserName(receiveGoodsModel.getCuserName());
            WorkFlowStub.BackResponse backResponse = stub.back(back);
            if (StringUtils.isNotBlank(backResponse.getErrMessage())) {
                ifRollback = true;
                executeResult.addErrorMessage(backResponse.getErrMessage());
                LOGGER.error("====" + backResponse.getErrMessage() + "====");
                return executeResult;
            }
        } catch (AxisFault axisFault) {
            ifRollback = true;
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            LOGGER.error("RemoteException", e);
        }
        if (ifRollback) {
            executeResult.addErrorMessage("调用审批流失败");
            return executeResult;
        }
        executeResult.setSuccessMessage("调用退回审批流成功");
        return executeResult;
    }

    @Override
    public ExecuteResult<ReceiveGoods> dropWorkFlow(
            ReceiveGoodsModel receiveGoodsModel) {
        ExecuteResult<ReceiveGoods> executeResult = new ExecuteResult<ReceiveGoods>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();
            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(receiveGoodsModel.getCdocument());
            unApprovalFlowParam.setBillTableName("TB_FA_RECEIVE_GOODS");
            unApprovalFlowParam.setBillCodeColumnName("CDOCUMENT");
            unApprovalFlowParam.setCreatorCode(receiveGoodsModel.getCuserCode());
            unApprovalFlowParam.setCreatorName(receiveGoodsModel.getCuserName());
            unApprovalFlowParam.setIdea(receiveGoodsModel.getIdea());
            unApprovalFlowParam.setCUserCode(receiveGoodsModel.getCuserCode()); //单据创建人编码
            unApprovalFlowParam.setCUserName(receiveGoodsModel.getCuserName());
            unApprovalFlowParam.setUserDepCode(receiveGoodsModel.getUserDepCode()); //当前操作人所属部门编码
            unApprovalFlowParam.setTypeCode("77077");
            unApproval.setParam(unApprovalFlowParam);
            unApproval.setUserCode(receiveGoodsModel.getCuserCode());
            unApproval.setUserName(receiveGoodsModel.getCuserName());
            WorkFlowStub.UnApprovalResponse unApprovalResponse = stub.unApproval(unApproval);
            if (StringUtils.isNotBlank(unApprovalResponse.getErrMessage())) {
                executeResult.addErrorMessage(unApprovalResponse.getErrMessage());
                return executeResult;
            }
        } catch (AxisFault axisFault) {
            ifRollback = true;
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            LOGGER.error("Exception", e);
        }
        if (ifRollback) {
            executeResult.addErrorMessage("调用审批流失败");
            return executeResult;
        }
        executeResult.setSuccessMessage("调用弃审审批流成功");
        return executeResult;
    }

    //修改保存
    @Override
    public Long updateSaveReceiveGoods(ReceiveGoods receiveGoods, List<ReceiveGoodsDetail> receiveGoodsDetails, List<ReceiveGoodsMapping> receiveGoodsMappings, List<PurchaseCommon> purchaseCommons) {
        PurchaseCommon pc = null;
        Long isSave = null;
		/*头*/
        isSave = receiveGoodsDAO.updateReceiveGoods(receiveGoods);
		/*行*/
        for (ReceiveGoodsDetail receiveGoodsDetail : receiveGoodsDetails) {
            receiveGoodsDetailDAO.updateReceiveGoodsDetail(receiveGoodsDetail);
        }
		/*	回写订单明细表 的收货数量	（回写tb_fa_purchase_order_detail.goods_count）*/
        for (ReceiveGoodsDetail rgd : receiveGoodsDetails) {
            pc = new PurchaseCommon();
            pc.setPurchaseId(rgd.getPurchaseId());
            //				pc.setPurchaseDetailId(rgd.getPurchaseDetailId());
            pc.setApplyDetailId(rgd.getApplyDetailId());
            //				pc.setThisGoodsCount(rgd.getThisGoodsCount());
            pc.setGoodsCount(rgd.getGoodsCount());
            purchaseCommonDAO.updatePurchaseOrderDetailByApplyDetailId(pc);
        }
        //
		/*  回写订单申请映射表 的收货数量  （回写tb_fa_purchase_apply_mapping.goods_count）*/
        for (ReceiveGoodsDetail rgd : receiveGoodsDetails) {
            for (ReceiveGoodsMapping rgm : receiveGoodsMappings) {
                if ((rgd.getPurchaseId()).equals(rgm.getPurchaseId()) && (rgd.getApplyDetailId()).equals(rgm.getApplyDetailId())) {
                    pc = new PurchaseCommon();
                    pc.setPurchaseId(rgm.getPurchaseId());
                    //						pc.setPurchaseDetailId(rgm.getPurchaseDetailId());
                    pc.setApplyDetailId(rgm.getApplyDetailId());
                    pc.setDemandDetailId(rgm.getDemandDetailId());
                    pc.setGoodsCountDemand(rgm.getGoodsCountDemand());
                    //						pc.setThisGoodsCountDemand(rgm.getThisGoodsCountDemand());
                    purchaseCommonDAO.updatePurchaseApplyMappingByDemandDetailIdChange(pc);
                }
            }
        }
		/* 回写sap资产公共表(TB_FA_PURCHASE_COMMON PC)，先删除，在保存 */
        delPurchaseCommonByReceiveGoodsId(receiveGoods.getCdocument());
        for (PurchaseCommon purchaseCommon : purchaseCommons) {
            purchaseCommonDAO.savePurchaseCommon(purchaseCommon);
        }
		/*保存到需求明细表(TB_FA_RECEIVE_CHECK_MAPPING)，验收时要用*/
        for (ReceiveGoodsDetail rgd : receiveGoodsDetails) {
            for (ReceiveGoodsMapping rgm : receiveGoodsMappings) {
                if ((rgd.getPurchaseId()).equals(rgm.getPurchaseId()) && (rgd.getApplyDetailId()).equals(rgm.getApplyDetailId())) {
                    receiveGoodsMappingDAO.updateReceiveGoodsMapping(rgm);
                }
            }
        }
        return isSave;
    }

    //	(收货单) 根据receiveGoodsId 删除TB_FA_PURCHASE_COMMON
    @Override
    public void delPurchaseCommonByReceiveGoodsId(String receiveGoodsId) {
        purchaseCommonDAO.delPurchaseCommonByReceiveGoodsId(receiveGoodsId);
    }

    @Override
    public List<ReceiveGoodsMapping> exportReceive(ReceiveGoodsMapping receiveGoodsMapping) {
        return receiveGoodsMappingDAO.exportReceive(receiveGoodsMapping);
    }

    @Override
    public Long updateSaveReceiveGoods1(ReceiveGoods receiveGoods, List<ReceiveGoodsDetail> receiveGoodsDetails, List<PurchaseCommon> purchaseCommons, List<ReceiveGoodsMapping> purchaseApplyMappingList) {
        Long isSave = null;
		/*头*/
        isSave = receiveGoodsDAO.updateReceiveGoods1(receiveGoods);
        PurchaseCommon pc = null;
        ApplyOrderDetail aod = new ApplyOrderDetail();
        ApplyOrder ao = new ApplyOrder();
        PurchaseOrder purchaseOrder = new PurchaseOrder();
		/*行*/
		/*for (ReceiveGoodsDetail receiveGoodsDetail : receiveGoodsDetails) {
			receiveGoodsDetailDAO.updateReceiveGoodsDetail1(receiveGoodsDetail);
		}*/
        /**
         * 如果是草稿,需要回写数量，并且生产资产卡片信息
         */
        if (!"N".equals(receiveGoods.getDraft())) {
			/*	回写订单明细表 的收货数量	（回写tb_fa_purchase_order_detail.goods_count）*/
            for (ReceiveGoodsDetail rgd : receiveGoodsDetails) {
                pc = new PurchaseCommon();
                pc.setPurchaseId(receiveGoods.getPurchaseId());
                //				pc.setPurchaseDetailId(rgd.getPurchaseDetailId());
                pc.setApplyDetailId(rgd.getApplyDetailId());
                //				pc.setThisGoodsCount(rgd.getThisGoodsCount());
                pc.setGoodsCount(rgd.getGoodsCount());
                purchaseCommonDAO.updatePurchaseOrderDetailByApplyDetailId(pc);
            }
            //更细采购订单主表状态
            //purchaseOrderDAO.updateOrderStatusByGoodsCount(receiveGoods.getPurchaseId());
			/*  回写订单申请映射表 的收货数量  （回写tb_fa_purchase_apply_mapping.goods_count）*/
            List<PurchaseCommon> purchaseCommonList = purchaseCommonDAO.getCountsByReceivedId(receiveGoods.getCdocument());
            for (PurchaseCommon purchaseCommon : purchaseCommonList) {
                purchaseCommonDAO.updatePurchaseApplyMappingByDemandDetailIdChange(purchaseCommon);
            }
			/*保存到需求明细表(TB_FA_RECEIVE_CHECK_MAPPING)，验收时要用*/
            List<ReceiveGoodsMapping> receiveGoodsMappingList = purchaseCommonDAO.selectReceiveCheckMapping(receiveGoods.getCdocument());
            for (ReceiveGoodsMapping rgm : receiveGoodsMappingList) {
                rgm.setThisGoodsCountDemand(rgm.getGoodsCountDemand());
                receiveGoodsMappingDAO.saveReceiveCheckMapping(rgm);
            }
            /**
             * 保存资产卡片信息
             */
            String createBy = receiveGoods.getCuserName();
            String purchaseId = receiveGoods.getPurchaseId();   //采购订单：PURCHASE_ID
            String officeLocation = receiveGoods.getOfficeLocation();
            String companyName = receiveGoods.getCompanyName(); //公司名称
            //String persentDepartment = receiveGoods.getBudgetDepartmentName();
            String supplierName = receiveGoods.getSupplierName();
            String supplierCode = receiveGoods.getSupplierCode();
            String platform = receiveGoods.getPlatform();
            String costCenterCode = receiveGoods.getCostCenterCode();
            ApplyOrderDetail aod3 = new ApplyOrderDetail();
            int num = 0;
            for (ReceiveGoodsDetail rgd : receiveGoodsDetails) {
                for (int i = 0; rgd.getThisGoodsCount() > i; i++) {
                    aod.setId(Long.parseLong(rgd.getApplyDetailId()));
                    aod3 = applyOrderDetailDAO.searchApplyOrderDetailById(aod3);
                    String unit = rgd.getUnits();
                    String assetsType = rgd.getAssetsType();
                    String assetsName = rgd.getAssetsName();
                    purchaseCommons.get(num).setReceiveGoodsDetailId(rgd.getReceiveGoodsDetailId());
                    String location = purchaseCommons.get(num).getStorageLocation();  //存放位置
                    String applyId = rgd.getApplyOrderId();  //采购申请单据号
                    /**
                     * 获取需求单据号
                     */
                    String demandId = purchaseCommons.get(num).getDemandId();  //采购需求单据号
				/*行政资产和非电脑类 资产卡片主表 保存一些公共的字段  */
                    GeneralAsset generalAsset = new GeneralAsset();
                    generalAsset.setAssetType(assetsType); //资产类型
                    generalAsset.setAssetState("0"); //资产状态 预验收 0    已验收1
                    //在采购申请详情表中根据主键来获取资产分类
                    aod.setId(Long.parseLong(rgd.getApplyDetailId()));
                    aod = applyOrderDetailDAO.searchApplyOrderDetailById(aod);
                    generalAsset.setAssetCode(aod.getMainAssetCode() + "-" + Integer.parseInt(purchaseCommons.get(num).getSapSecAssetCode())); //主资产编码 - 次级编码（sap编码）
                    ao.setCdocument(applyOrderDetailDAO.searchApplyOrderDetailById(aod).getApplyOrderId());
                    ao = applyOrderDAO.searchApplyOrderByOrderId(ao);
                    generalAsset.setAssetClassification(applyOrderDetailDAO.searchApplyOrderDetailById(aod).getAssetClassification());  //资产分类
                    if ("Y".equals(ao.getExpensing())) {
                        ao.setExpensing("1");
//					系统管理编号 (是)
                        generalAsset.setSysteManagementCode(workFlowMapper.getDocumentByType("FA_MMFY"));
                    } else if ("N".equals(ao.getExpensing())) {
                        ao.setExpensing("0");
//					系统管理编号 (否)
                        generalAsset.setSysteManagementCode(workFlowMapper.getDocumentByType("FA_MMZB"));
                    }
                    purchaseCommons.get(num).setSysteManagementCode(generalAsset.getSysteManagementCode()); //上传图片的document
                    generalAsset.setIsExpenseAssets(ao.getExpensing()); //是否为一次性费用化资产
                    //rgd.getPurchaseDetailId()//订单详情ID（订单详情表主键）
                    generalAsset.setSpecificationModel(purchaseOrderDetailDAO.searchPurchaseOrderDetailById(rgd.getPurchaseDetailId()).getBrand()); //品牌/规格/型号
                    generalAsset.setSelectAccessories(applyOrderDetailDAO.searchApplyOrderDetailById(aod).getOptionalAccessories()); //选配件
                    generalAsset.setUseState("0"); //资产状态（0在用）
                    generalAsset.setPurchaseArrivalDate(rgd.getDeliveryDate()); //采购到货日期*（收货日期）
                    generalAsset.setDateOfAcceptance(rgd.getRequireCheckDate()); //计划验收日期
                    //js中默认验收周期计量器具45天，非计量器具30天
                    long betweenDays = (rgd.getRequireCheckDate().getTime() - rgd.getDeliveryDate().getTime()) / (1000 * 60 * 60 * 24);
                    generalAsset.setAcceptanceCycle(String.valueOf(betweenDays)); //验收周期
                    generalAsset.setWarrantyPeriod(rgd.getRecWarrantyPeriod()); //质保周期
                    generalAsset.setPlatform(platform); //平台
                    generalAsset.setCompanyName(companyName); //所属公司
                    generalAsset.setCompanyCode(ao.getCompanyCode()); //所属公司编码
                    generalAsset.setPersentDepartment(receiveGoods.getCostCenter()); //现部门和成本中心
                    generalAsset.setUseDepartment(ao.getBudgetDepartmentName()); //使用部门*
                    generalAsset.setAssetPerson(purchaseCommons.get(num).getCheckApplyUser()); //管理责任人*
                    generalAsset.setAssetPersonCode(purchaseCommons.get(num).getCheckApplyCode()); //管理责任人編碼*
                    generalAsset.setSupplierName(supplierName); //供应商名称
                    generalAsset.setSupplierCode(supplierCode);  //供应商编码
                    generalAsset.setIsUpdateSap("0");  //是否更新了 sap  1为 是  0 为  否
                    generalAsset.setDifferentiatePurchase("0");  //区分是页面新增卡片还是收货后新增卡片
                    generalAsset.setCostCenterCode(costCenterCode);
                    generalAsset.setUnit(unit); //单位
                    generalAsset.setLocation(location); //存放位置
                    generalAsset.setOfficeLocation(officeLocation); //办公地点
                    generalAsset.setAssetNetValue("0.00"); //资产净值assetNetValue
                    generalAsset.setAssetOriginalValue("0.00"); //资产原值assetOriginalValue
                    generalAsset.setPeriodOfDepreciation("0"); //折旧年限periodOfDepreciation
                    generalAsset.setMonthCountingDepreciation("0.00"); //月折旧额monthCountingDepreciation
                    generalAsset.setCurrentYearDepreciation("0.00"); //本年已计提累计折旧currentYearDepreciation
                    generalAsset.setAddDepreciation("0.00"); //累计折旧addDepreciation
                    /**
                     * 获取相对应的采购订单主表行,进而读取供应商信息
                     */
                    purchaseOrder.setCdocument(receiveGoods.getPurchaseId());
                    purchaseOrder = purchaseOrderDAO.searchPurchaseOrderByOrderId(purchaseOrder);
                    generalAsset.setSupplierLinkman(purchaseOrder.getContacts()); //供应商联系人
                    generalAsset.setSupplierPhone(purchaseOrder.getTelephone()); //供应商电话
                    generalAsset.setDemandId(demandId); //采购需求单据号
                    generalAsset.setApplyId(applyId); //采购申请单据号
                    generalAsset.setPurchaseId(purchaseId);  //采购订单：PURCHASE_ID
                    generalAsset.setAssetName(assetsName); //资产名称
                    generalAsset.setCreateBy(createBy); //创建人
                    generalAsset.setCreateDate(new Date()); //创建时间
                    Long counts = generalAssetDAO.assetCodeCount(generalAsset);
                    GeneralAsset generalAsset1 = new GeneralAsset();
                    if ("指定".equals(aod3.getCodingMode()) && counts > 0) {
                        //当指定类型的资产，在资产表中有信息时，不进行新增
                        generalAsset1 = generalAssetDAO.searchGeneralAssetOnly(generalAsset);
                    } else {
                        if ("IT资产".equals(assetsType)) {
					/*IT资产  资产卡片电脑类 */
                            ComputerAsset computerAsset = new ComputerAsset();
                            generalAsset.setAssetType("0");
                            computerAssetDAO.insertComputerAsset(computerAsset);
                            generalAsset.setAssetDetailId(Integer.parseInt(computerAsset.getId() + ""));
                            generalAssetDAO.insertGeneralAsset(generalAsset);
                        }
                        if ("行政资产".equals(assetsType)) {
                            /**
                             * 行政资产
                             */
                            AdministrationAsset administrationAsset = new AdministrationAsset();
                            administrationAsset.setIsBuildingContruction(ao.getBuilding()); //是否为房屋建筑
                            generalAsset.setAssetType("1");
                            administrationAssetDAO.insertAdministrationAsset(administrationAsset);
                            generalAsset.setAssetDetailId(Integer.parseInt(administrationAsset.getId() + ""));
                            generalAssetDAO.insertGeneralAsset(generalAsset);
                        }
                        if ("计量器具".equals(assetsType)) {
					/*计量器具*/
                            MeasuringInstruments measuringInstruments = new MeasuringInstruments();
                            generalAsset.setAssetType("2");
                            measuringInstrumentsDAO.insertMeasuringInstruments(measuringInstruments);
                            generalAsset.setAssetDetailId(Integer.parseInt(measuringInstruments.getId() + ""));
                            generalAssetDAO.insertGeneralAsset(generalAsset);
                        }
                        if ("机器设备".equals(assetsType)) {
					/*机器设备*/
                            MachineryEquipment machineryEquipment = new MachineryEquipment();
                            machineryEquipment.setShopType(ao.getWorkShopType()); //车间类型
                            generalAsset.setAssetType("3");
                            machineryEquipmentDAO.insertMachineryEquipment(machineryEquipment);
                            generalAsset.setAssetDetailId(Integer.parseInt(machineryEquipment.getId() + ""));
                            generalAssetDAO.insertGeneralAsset(generalAsset);
                        }
                    }

//				purchaseCommons.get(i).setCardId(generalAsset.getId() + "");
                    if ("指定".equals(aod3.getCodingMode()) && counts > 0) {
                        purchaseCommons.get(num).setCardId(generalAsset1.getId() + "");
                        purchaseCommons.get(num).setRecWarrantyPeriod(generalAsset1.getWarrantyPeriod());
                    } else {
                        purchaseCommons.get(num).setCardId(generalAsset.getId() + "");
                        purchaseCommons.get(num).setRecWarrantyPeriod(generalAsset.getWarrantyPeriod());
                    }
                    num++;
                }
            }
        } else {
            //更新资产卡片主表的验收人和存放位置
            for (PurchaseCommon pc1 : purchaseCommons) {
                GeneralAsset ga = new GeneralAsset();
                ga.setId(Long.parseLong(pc1.getCardId()));
                ga.setLocation(pc1.getStorageLocation());
                ga.setPersentStorageLocation(pc1.getStorageLocation());
                ga.setAssetPerson(pc1.getCheckApplyUser());
                ga.setAssetPersonCode(pc1.getCheckApplyCode());
                generalAssetDAO.updateCard(ga);
            }
        }
        for (PurchaseCommon purchaseCommon : purchaseCommons) {
            purchaseCommon.setPurchaseId(receiveGoods.getPurchaseId());
            purchaseCommonDAO.updatePurchaseCommonLocation(purchaseCommon); //同时将收货状态改为‘Y’
        }
        return isSave;
    }

    @Override
    public List<ReceiveGoodsMapping> exportReceive1(ReceiveGoodsMapping receiveGoodsMapping) {
        return receiveGoodsMappingDAO.exportReceive1(receiveGoodsMapping);
    }

    @Override
    public Pager<ReceiveGoodsMapping> searchReceiveGoodsMappingForReceive(Pager<ReceiveGoodsMapping> pager, ReceiveGoodsMapping receiveGoodsMapping) {
        List<ReceiveGoodsMapping> searchReceiveGoodsMapping = receiveGoodsMappingDAO.searchReceiveGoodsMappingForReceive(pager, receiveGoodsMapping);
        Long searchReceiveGoodsMappingCount = receiveGoodsMappingDAO.searchReceiveGoodsMappingForReceiveCount(receiveGoodsMapping);
        return Pager.cloneFromPager(pager, searchReceiveGoodsMappingCount, searchReceiveGoodsMapping);
    }

    @Override
    public Pager<ReceiveGoodsMapping> searchReceiveGoodsMappingForReceiveAll(Pager<ReceiveGoodsMapping> pager, ReceiveGoodsMapping receiveGoodsMapping) {
        List<ReceiveGoodsMapping> searchReceiveGoodsMapping = receiveGoodsMappingDAO.searchReceiveGoodsMappingForReceiveAll(pager, receiveGoodsMapping);
        Long searchReceiveGoodsMappingCount = receiveGoodsMappingDAO.searchReceiveGoodsMappingForReceiveAllCount(receiveGoodsMapping);
        return Pager.cloneFromPager(pager, searchReceiveGoodsMappingCount, searchReceiveGoodsMapping);
    }

    @Override
    public List<ReceiveGoodsMapping> exportReceiveHavingReceived(ReceiveGoodsMapping receiveGoodsMapping) {
        return receiveGoodsMappingDAO.exportReceiveHavingReceived(receiveGoodsMapping);
    }

    @Override
    public List<ReceiveGoodsMapping> export1ReceiveHavingReceived(ReceiveGoodsMapping receiveGoodsMapping) {
        return receiveGoodsMappingDAO.export1ReceiveHavingReceived(receiveGoodsMapping);
    }

}
