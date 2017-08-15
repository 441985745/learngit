package com.opple.fa.purchase.service.impl;

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
import com.opple.fa.purchase.dao.CheckAcceptMappingDAO;
import com.opple.fa.purchase.dao.CheckAcceptanceDAO;
import com.opple.fa.purchase.dao.CheckAcceptanceDetailDAO;
import com.opple.fa.purchase.dao.DemandOrderDetailDAO;
import com.opple.fa.purchase.dao.PurchaseCommonDAO;
import com.opple.fa.purchase.dao.PurchaseOrderDAO;
import com.opple.fa.purchase.dao.PurchaseOrderDetailDAO;
import com.opple.fa.purchase.entity.CheckAcceptMapping;
import com.opple.fa.purchase.entity.CheckAcceptance;
import com.opple.fa.purchase.entity.CheckAcceptanceDetail;
import com.opple.fa.purchase.entity.CheckMachine;
import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.model.CheckAcceptanceModel;
import com.opple.fa.purchase.service.CheckAcceptMappingService;
import com.opple.fa.sap.service.SapUtilService;
import com.opple.util.Env;
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

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class CheckAcceptMappingServiceImpl implements CheckAcceptMappingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveGoodsMappingServiceImpl.class);
    @Getter
    @Setter
    private PurchaseOrderDAO purchaseOrderDAO;
    @Getter
    @Setter
    private DemandOrderDetailDAO demandOrderDetailDAO;
    @Getter
    @Setter
    private CheckAcceptanceDAO checkAcceptanceDAO;
    @Getter
    @Setter
    private CheckAcceptanceDetailDAO checkAcceptanceDetailDAO;
    @Getter
    @Setter
    private CheckAcceptMappingDAO checkAcceptMappingDAO;
    @Getter
    @Setter
    private PurchaseCommonDAO purchaseCommonDAO;

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
    private SapUtilService sapUtilService;
    @Getter
    @Setter
    private AdministrationAssetDAO administrationAssetDAO;
    @Getter
    @Setter
    private PurchaseOrderDetailDAO purchaseOrderDetailDAO;

    //	分页查询--验收单主界面
    @Override
    public Pager<CheckAcceptMapping> searchCheckAcceptMapping(Pager<CheckAcceptMapping> pager, CheckAcceptMapping checkAcceptMapping) {
        List<CheckAcceptMapping> searchCheckMapping = checkAcceptMappingDAO.searchCheckAcceptMapping(pager, checkAcceptMapping);
        Long searchCheckMappingCount = checkAcceptMappingDAO.searchCheckAcceptMappingCount(checkAcceptMapping);
        return Pager.cloneFromPager(pager, searchCheckMappingCount, searchCheckMapping);
    }

    @Override
    public List<CheckAcceptMapping> exportCheck(CheckAcceptMapping checkAcceptMapping) {
        return checkAcceptMappingDAO.exportCheck(checkAcceptMapping);
    }

    //	(待选择界面)  验收 头
    @Override
    public CheckAcceptMapping searchTempReceiveGoodsByReceiveGoodsId(String receiveGoodsId) {
        return checkAcceptMappingDAO.searchTempReceiveGoodsByReceiveGoodsId(receiveGoodsId);
    }

    //	(待选择界面)  验收 行
    @Override
    public List<CheckAcceptMapping> searchTempReceiveGoodsDetailByReceiveGoodsId(String receiveGoodsId, String userCode) {
        return checkAcceptMappingDAO.searchTempReceiveGoodsDetailByReceiveGoodsId(receiveGoodsId, userCode);
    }

    //	(待选择界面) 需求明细 所有的收货待验收的
    @Override
    public List<CheckAcceptMapping> searchAllDemandDetailIdByReceiveGoodsId(String receiveGoodsId, String receiveGoodsDetailId, String userCode) {
        return checkAcceptMappingDAO.searchAllDemandDetailIdByReceiveGoodsId(receiveGoodsId, receiveGoodsDetailId, userCode);
    }

    //	查询 得到验收单的 头
    @Override
    public CheckAcceptMapping getCheckAcceptanceByReceiveGoodsDetailId(String receiveGoodsId, String receiveGoodsDetailId) {
        return checkAcceptMappingDAO.getCheckAcceptanceByReceiveGoodsDetailId(receiveGoodsId, receiveGoodsDetailId);
    }

    //	查询 得到验收单的 行
    @Override
    public List<CheckAcceptMapping> getCheckAcceptanceDetailByPurchaseDetailId(String receiveGoodsId, String applyDetailId) {
        return checkAcceptMappingDAO.getCheckAcceptanceDetailByPurchaseDetailId(receiveGoodsId, applyDetailId);
    }

    //	保存验收单的头，保存绑定审批流用
    @Override
    public Long saveCheckAcceptance(CheckAcceptance checkAcceptance) {
        Long isSave = checkAcceptanceDAO.saveCheckAcceptance(checkAcceptance);
        return isSave;
    }

    //	保存验收单
    @Override
    public Long save(CheckAcceptance checkAcceptance, List<CheckAcceptanceDetail> checkAcceptanceDetaillist, List<CheckAcceptMapping> checkAcceptMappinglist, CheckMachine checkMachine) {
        Long isSave = null;
        String purchaseDetailIdTemp = null; // 标记是选定的收货行
        if ("机器设备".equals(checkAcceptance.getAssetsType())) {
            checkAcceptanceDAO.saveCheckMachine(checkMachine);
        }
    /* 验收 头  ：这里不保存头了，在绑定审批流的时候，已经保存头了，这里只保存其他的*/

	/* 验收  行 */
		/*for (CheckAcceptanceDetail cad : checkAcceptanceDetaillist) {
			 isSave = checkAcceptanceDetailDAO.saveCheckAcceptanceDetail(cad);
			purchaseDetailIdTemp = cad.getPurchaseDetailId();
		}*/
        for (int i = 0; i < checkAcceptanceDetaillist.size(); i++) {
            isSave = checkAcceptanceDetailDAO.saveCheckAcceptanceDetail(checkAcceptanceDetaillist.get(i));
            //checkAcceptanceDetaillist.get(i).setId(isSave);
            purchaseDetailIdTemp = checkAcceptanceDetaillist.get(i).getPurchaseDetailId();
        }

	/*page2中信息 回写收货验收映射表tb_fa_receive_check_mapping.check_count_demand  验收数量修改*/
        for (CheckAcceptMapping cam : checkAcceptMappinglist) { //通过controller中searchAllDemandDetailIdByReceiveGoodsId方法获得
            if ((cam.getPurchaseDetailId()).equals(purchaseDetailIdTemp)) {
                checkAcceptMappingDAO.updateReceiveCheckMappingByDemandDetailId(cam);
            }
        }

	/*  回写订单申请映射表 的验收数量  （回写tb_fa_purchase_apply_mapping.check_count）*/
        for (CheckAcceptMapping cam : checkAcceptMappinglist) {
            if ((cam.getPurchaseDetailId()).equals(purchaseDetailIdTemp)) {
                PurchaseCommon purchaseCommon = new PurchaseCommon();
                purchaseCommon.setPurchaseId(cam.getPurchaseId());
                purchaseCommon.setApplyDetailId(cam.getApplyDetailId());
                purchaseCommon.setDemandDetailId(cam.getDemandDetailId());
                purchaseCommon.setThisCheckCountDemand(cam.getThisCheckCountDemand());
                purchaseCommonDAO.updatePurchaseApplyMappingCheckCountByDemandDetailId(purchaseCommon);
            }
        }

	/* 回写sap资产公共表(tb_fa_purchase_common.check_status),收完货后更改验收状态，更改后的数据在查询时，将不再显示 */
        for (CheckAcceptanceDetail cad : checkAcceptanceDetaillist) {
            PurchaseCommon pcommom = new PurchaseCommon();
            pcommom.setCardId(cad.getCardId());
            pcommom.setReceiveGoodsId(cad.getReceiveGoodsId());
            pcommom.setApplyDetailId(cad.getApplyDetailId());
            pcommom.setDemandDetailId(cad.getDemandDetailId());
            pcommom.setSapAssetsCode(cad.getSapAssetsCode());
            //可能需要在验收审批流的最后一步保存验收人code
            pcommom.setBrand(checkAcceptance.getBrand());
            pcommom.setCheckAcceptId(checkAcceptance.getCheckAcceptId()); //验收单编码
            pcommom.setCheckAcceptDetailId(cad.getId().toString());
            pcommom.setStorageLocation(cad.getStorageLocation()); //更新存放位置
            purchaseCommonDAO.updatePurchaseCommonBySapAssetsCode(pcommom);
        }



	/*  回写收货行已经验收数量( tb_fa_receive_goods_detail.check_count)*/
        checkAcceptMappingDAO.updateReceiveGoodsDetailByReceiveGoodsDetailId(checkAcceptance);
        //checkAcceptMappingDAO.updateReceiveOrderStatus(checkAcceptance.getReceiveGoodsId());

        String assetsType = checkAcceptance.getAssetsType();
        String purchaseId = checkAcceptance.getPurchaseId();
        String checkAcceptId = checkAcceptance.getCheckAcceptId();
        String brand = checkAcceptance.getBrand();
        String useInfo = checkAcceptance.getUseInfo();
        //String useStatus = checkAcceptance.getUseStatus();
        String assetPerson = checkAcceptance.getAssetPerson();
        String computerCode = checkAcceptance.getComputerCode();
        String verificationCycle = checkAcceptance.getCheckCycle();
        for (int i = 0; checkAcceptanceDetaillist.size() > i; i++) {
            CheckAcceptanceDetail cad = checkAcceptanceDetaillist.get(i);
            String cardId = cad.getCardId();
            String assetDetailId = cad.getAssetDetailId();
            String demandId = cad.getDemandId();
            String applyId = cad.getApplyId();
            String mmAssetsCode = cad.getMmAssetsCode();
		/*回写资产卡片的表（tb_fa_computer_asset--IT资产（电脑类）*/
//		String assetsType = checkAcceptance.getAssetsType();
//		computerAsset.setCompanyCode(computerCode_);
//		computerAsset.setCostCenterCode();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*行政资产和非电脑类 资产卡片主表 保存一些公共的字段  */
            GeneralAsset generalAsset = new GeneralAsset();
            generalAsset.setId(Long.valueOf(cardId));
            generalAsset.setLocation(cad.getStorageLocation());
            //根据主键得到在收货时存在资产卡片中的信息
            GeneralAsset ga = generalAssetDAO.getGeneralAsset(Integer.parseInt(cardId));
            /**
             * 取出收货时存的质保周期，加上当前日期，计算出质保到期日,这一块应该在审批流最后一步同意的时候做
             */
            String warrantyPeriod = ga.getWarrantyPeriod(); //取出收货时存的质保周期，单位是月
            Date dt = new Date(); //当前时间
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MONTH, +Integer.parseInt(warrantyPeriod));
            Date dt1 = rightNow.getTime();
            String reStr = sdf.format(dt1);
            Date date = null;
            try {
                date = sdf.parse(reStr);
                generalAsset.setExpirationDate(date); //质保到期日
                generalAsset.setActualDateAcceptance(sdf.parse(sdf.format(dt))); //实际验收日期
            } catch (ParseException e) {
                e.printStackTrace();
            }

            /**
             * 下载文件对应的值
             * 当资产类型为计量器具时，需要上传图片和检验证书，对应的参数为mmAssetsCode
             */
            generalAsset.setDocument(mmAssetsCode);
            generalAsset.setAssetDetailId(Integer.valueOf(assetDetailId));
            generalAsset.setCheckAcceptId(checkAcceptId);
            generalAsset.setSpecificationModel(brand);
            //generalAsset.setUseState(useInfo);
            //generalAsset.setAssetState(useStatus);
            generalAsset.setPersentStorageLocation(cad.getStorageLocation());
            generalAsset.setUpdateBy(assetPerson);
            generalAsset.setUpdateDate(new Date());
            generalAsset.setUnit(checkAcceptance.getUnits()); //单位，更新
//			generalAsset.setSupplierCode(supplierCode);
//			generalAsset.setSupplierName(supplierName);
//			generalAsset.setPurchaseId(purchaseId);
//			generalAsset.setAssetCode(mmAssetsCode);
//			generalAsset.setAssetType(assetsType);
//			generalAsset.setCompanyName(companyName);
//			generalAsset.setCompanyCode(companyCode);
//			generalAsset.setAssetPerson(assetPerson);
//			generalAsset.setDemandId(demandId);
//			generalAsset.setApplyId(applyId);
//			generalAsset.setCreateBy(assetPerson);
//			generalAsset.setCreateDate(new Date());
//			generalAsset.setCompanyCode(computerCode);
//			generalAsset.setCostCenterCode(costCenterCode);


            if ("IT资产".equals(assetsType)) { //0
				/*IT资产  资产卡片电脑类 */
                ComputerAsset computerAsset = new ComputerAsset();
                computerAsset.setId(Long.valueOf(assetDetailId));
                computerAsset.setPurchaseId(purchaseId);
                computerAsset.setCheckAcceptId(checkAcceptId);
                computerAsset.setDemandId(demandId);
                computerAsset.setApplyId(applyId);
                computerAsset.setCreateBy(assetPerson);
                computerAsset.setCreateDate(new Date());
                computerAsset.setUpdateBy(assetPerson);
                computerAsset.setUpdateDate(new Date());
                computerAsset.setSerialNumber(cad.getSerialNumber()); //序号
                computerAsset.setPutUnderManageCode(cad.getPutUnderManageCode()); //归口管理编码
                computerAsset.setCpu(cad.getCpu()); //cpu
                computerAsset.setInternalMemory(cad.getInternalMemory()); //内存
                computerAsset.setHardpan(cad.getHardpan()); //硬盘
                computerAsset.setIpAddress(cad.getIpAddress()); //IP地址
                computerAsset.setJoinDomain(cad.getJoinDomain()); //加入域
                computerAsset.setDescription(cad.getDescription()); //描述
                computerAsset.setUtilitySystem(cad.getUtilitySystem()); //应用系统
                computerAsset.setDataBase(cad.getDataBase()); //数据库
                computerAsset.setOperatingSystem(cad.getOperatingSystem()); //操作系统
                generalAsset.setAssetType("0");
                computerAssetDAO.updateComputerAssetCard1(computerAsset);
                generalAssetDAO.updateGeneralAssetCard(generalAsset);
            }

            if ("行政资产".equals(assetsType)) { //2

                AdministrationAsset administrationAsset = new AdministrationAsset();
                administrationAsset.setId(Long.valueOf(assetDetailId));
                administrationAsset.setSerialNumber(cad.getSerialNumber()); //出厂编号/序列号
                administrationAsset.setManagementCode(cad.getPutUnderManageCode()); //管理编码
                administrationAsset.setCreateDate(new Date());
                generalAsset.setAssetType("1");
                administrationAssetDAO.updateAdministrationAsset1(administrationAsset);
                generalAssetDAO.updateGeneralAssetCard(generalAsset);
            }

            if ("计量器具".equals(assetsType)) { //3
				/*计量器具*/
                MeasuringInstruments measuringInstruments = new MeasuringInstruments();
                measuringInstruments.setId(Long.valueOf(assetDetailId));
                measuringInstruments.setPurchaseId(purchaseId);
                measuringInstruments.setCheckAcceptId(checkAcceptId);
                measuringInstruments.setDemandId(demandId);
                measuringInstruments.setApplyId(applyId);
                measuringInstruments.setCreateBy(assetPerson);
                measuringInstruments.setCreateDate(new Date());
                measuringInstruments.setUpdateBy(assetPerson);
                measuringInstruments.setUpdateDate(new Date());
                measuringInstruments.setCompanyCode(computerCode);
                measuringInstruments.setVerificationCycle(verificationCycle);
                measuringInstruments.setCheckAcceptId(checkAcceptId);
                measuringInstruments.setInspectionDate(new Date());
                measuringInstruments.setFacrotyNumber(cad.getSerialNumber()); //出厂编号
                measuringInstruments.setManagementCode(cad.getPutUnderManageCode()); //管理编号
                measuringInstruments.setManufactureFactory(cad.getManufactureFactory()); //生产厂家
                measuringInstruments.setManagementLevel(cad.getManagementLevel()); //管理级别
                measuringInstruments.setAccuracyClass(cad.getAccuracyClass()); //精度等级
                measuringInstruments.setVerificationCycle(cad.getVerificationCycle()); //检定周期

                try {
                    measuringInstruments.setCalibrationDate(sdf.parse(cad.getCalibrationDate())); //校准日期
                    measuringInstruments.setNextCalibrationDate(sdf.parse(cad.getNextCalibrationDate())); //下次校准日期
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                measuringInstruments.setCalibrationUnit(cad.getCalibrationUnit()); //校准单位
                measuringInstruments.setIsThirdReport(cad.getIsThirdReport()); //是否有第三方校验报告
                generalAsset.setAssetType("2");
                measuringInstrumentsDAO.updateMeasuringInstrumentsCard1(measuringInstruments);
                generalAssetDAO.updateGeneralAssetCard(generalAsset);
            }

            if ("机器设备".equals(assetsType)) { //4
				/*机器设备*/
                MachineryEquipment machineryEquipment = new MachineryEquipment();
                machineryEquipment.setId(Long.valueOf(assetDetailId));
                machineryEquipment.setPurchaseId(purchaseId);
                machineryEquipment.setCheckAcceptId(checkAcceptId);
                machineryEquipment.setCreateBy(assetPerson);
                machineryEquipment.setDemandId(demandId);
                machineryEquipment.setApplyId(applyId);
                machineryEquipment.setCreateDate(new Date());
                machineryEquipment.setUpdateBy(assetPerson);
                machineryEquipment.setUpdateDate(new Date());
                machineryEquipment.setCompanyCode(computerCode);
                machineryEquipment.setMachineCode(cad.getSerialNumber()); //机器编号(序列号,出厂编号)
                machineryEquipment.setShopType(cad.getShopType()); //车间类型
                machineryEquipment.setPutUnderManageCode(cad.getPutUnderManageCode()); //归口管理编码


                generalAsset.setAssetType("3");
                machineryEquipmentDAO.updateMachineryEquipmentCard1(machineryEquipment);
                generalAssetDAO.updateGeneralAssetCard(generalAsset);
            }

        }
        return isSave;
    }

    //	同意审批
    @Override
    public ExecuteResult<CheckAcceptance> agreeWorkFlow(CheckAcceptanceModel checkAcceptanceModel, String isManager, String isAssetAccounting, List<CheckAcceptanceDetail> list) throws ParseException {
        ExecuteResult<CheckAcceptance> executeResult = new ExecuteResult<CheckAcceptance>();
        boolean ifRollback = false;
        boolean flag = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();
            WorkFlowStub.GetIsLastApprovalStep getIsLastApprovalStep = new WorkFlowStub.GetIsLastApprovalStep();
            agreeFlowParam.setBillCode(checkAcceptanceModel.getCdocument());
            agreeFlowParam.setBillTableName("TB_FA_CHECK_ACCEPTANCE");
            agreeFlowParam.setBillCodeColumnName("CDOCUMENT");
            agreeFlowParam.setCApprovalId(UUID.randomUUID().toString());
            agreeFlowParam.setDeptCode(checkAcceptanceModel.getApplyDepartmentCode()); //单据所属部门编码
            agreeFlowParam.setDeptName(checkAcceptanceModel.getApplyDepartment()); //单据所属部门名称
            agreeFlowParam.setCreatorCode(checkAcceptanceModel.getApplyCheckUserCode()); //单据创建人编码
            agreeFlowParam.setCreatorName(checkAcceptanceModel.getApplyCheckUserName());
            if (null == checkAcceptanceModel.getCreateDate()) {
                executeResult.addErrorMessage("创建日期不能为空");
                return executeResult;
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime(checkAcceptanceModel.getCreateDate());
            agreeFlowParam.setCreateDate(instance);
            agreeFlowParam.setCUserCode(checkAcceptanceModel.getCuserCode()); //当前操作人编码
            agreeFlowParam.setCUserName(checkAcceptanceModel.getCuserName());
            agreeFlowParam.setUserDepCode(checkAcceptanceModel.getUserDepCode()); //当前操作人所属部门编码
            agreeFlowParam.setAMoney("0");
            agreeFlowParam.setTypeCode(Env.getProperty("check.info.type.id"));
            agreeFlowParam.setMoney("0");
            agreeFlowParam.setIdea(checkAcceptanceModel.getIdea());
            agreeFlowParam.setIsEmail(checkAcceptanceModel.getIsEmail());
            agreeFlowParam.setIsPhoneMessage(checkAcceptanceModel.getIsPhoneMessage());
            agreeFlowParam.setFeeItemCode("");
            agreeFlowParam.setCCostCenterCode(checkAcceptanceModel.getCostCenterCode());
            agreeFlowParam.setCCommitActivityId(null == checkAcceptanceModel.getActivityId() ? "" : checkAcceptanceModel.getActivityId());
            agree.setUserCode(checkAcceptanceModel.getCuserCode());

            agree.setUserName(checkAcceptanceModel.getCuserName());
            agree.setParam(agreeFlowParam);

            getIsLastApprovalStep.setParam(agreeFlowParam);
            //判断是否最后一步
            WorkFlowStub.GetIsLastApprovalStepResponse rs = stub.getIsLastApprovalStep(getIsLastApprovalStep);
            WorkFlowStub.AgreeResponse agreeResponse = stub.agree(agree);
            flag = rs.getGetIsLastApprovalStepResult();
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
        /**
         * 判断审批流走完之后的单据的审批流状态
         */
        CheckAcceptance ca = checkAcceptanceDAO.getCheckAcceptanceByDocument(checkAcceptanceModel.getCdocument());
        /**
         * 更新实际验收日期
         */
        checkAcceptanceDAO.updateLastCheckDate(checkAcceptanceModel.getCdocument());
        /**
         * 判单是否为归口管理工程师，若是，则进行更新操作
         */
        //  完成审批流之后走的方法,给资产卡片更新postingDate入账日期
        String assetType = ca.getAssetsType(); //资产类型(IT资产\行政资产\计量器具\机器设备)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if ("Y".equals(isAssetAccounting)) { //如果是资产会计提交，说明同步过sap了，将金额回写到预算表中
            //根据需求行id查询项目编码，预算年度
            //根据订单行id查询对应单价，如果订单行id相同，累加
            List<CheckAcceptanceDetail> list1 = new ArrayList<CheckAcceptanceDetail>();
            DemandOrderDetail demandOrderDetail = new DemandOrderDetail();
            DemandOrderDetail dod = new DemandOrderDetail();
            PurchaseOrderDetail pod = new PurchaseOrderDetail();
            for (CheckAcceptanceDetail cad : list) {
                demandOrderDetail.setId(Long.parseLong(cad.getDemandDetailId()));
                dod = demandOrderDetailDAO.searchDemandOrderDetailById(demandOrderDetail);
                cad.setBudgetYear(dod.getBudgetYear());
                cad.setProjectCode(dod.getProjectCode());
                cad.setNum(1);
                pod = purchaseOrderDetailDAO.searchPurchaseOrderDetailById(cad.getPurchaseDetailId());
                cad.setPurchaseUnitRice(pod.getPurchaseUnitPrice());
                if (list1.size() == 0) {
                    list1.add(cad);
                    continue;
                }
                for (CheckAcceptanceDetail o : list1) {
                    if (o.getProjectCode().equals(cad.getProjectCode()) && o.getBudgetYear().equals(cad.getBudgetYear()) && o.getPurchaseDetailId().equals(cad.getPurchaseDetailId())) {
                        o.setNum(o.getNum() + 1);
                        break;
                    } else {
                        list1.add(cad);
                        break;
                    }
                }
            }
            for (CheckAcceptanceDetail cad : list1) {
                cad.setCheckMoney(cad.getPurchaseUnitRice().multiply(BigDecimal.valueOf(cad.getNum())));
                checkAcceptanceDetailDAO.updateAssetsBudget(cad);
            }

        }
        if ("Y".equals(isManager)) {
            for (CheckAcceptanceDetail cad : list) {
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setId(Long.valueOf(cad.getCardId()));
                generalAsset.setLocation(cad.getStorageLocation());
                /**
                 * 取出收货时存的质保周期，加上当前日期，计算出质保到期日,这一块应该在审批流最后一步同意的时候做
                 */
                GeneralAsset ga = generalAssetDAO.getGeneralAsset(Integer.parseInt(cad.getCardId()));
                String assetDetailId = ga.getAssetDetailId().toString();
				/*String warrantyPeriod = ga.getWarrantyPeriod(); //取出收货时存的质保周期，单位是月
				Date dt = new Date(); //当前时间
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(dt);
				rightNow.add(Calendar.MONTH, +Integer.parseInt(warrantyPeriod));
				Date dt1 = rightNow.getTime();
				String reStr = sdf.format(dt1);
				Date date = null;
				try {
					date = sdf.parse(reStr);
					generalAsset.setExpirationDate(date); //质保到期日
					generalAsset.setActualDateAcceptance(sdf.parse(sdf.format(dt))); //实际验收日期
				} catch (ParseException e) {
					e.printStackTrace();
				}*/
                if ("IT资产".equals(assetType)) { //0
				/*IT资产  资产卡片电脑类 */
                    ComputerAsset computerAsset = new ComputerAsset();
                    computerAsset.setId(Long.valueOf(assetDetailId));
                    computerAsset.setSerialNumber(cad.getSerialNumber()); //序号
                    computerAsset.setPutUnderManageCode(cad.getPutUnderManageCode()); //归口管理编码
                    computerAsset.setCpu(cad.getCpu()); //cpu
                    computerAsset.setInternalMemory(cad.getInternalMemory()); //内存
                    computerAsset.setHardpan(cad.getHardpan()); //硬盘
                    computerAsset.setIpAddress(cad.getIpAddress()); //IP地址
                    computerAsset.setJoinDomain(cad.getJoinDomain()); //加入域
                    computerAsset.setDescription(cad.getDescription()); //描述
                    computerAsset.setUtilitySystem(cad.getUtilitySystem()); //应用系统
                    computerAsset.setDataBase(cad.getDataBase()); //数据库
                    computerAsset.setOperatingSystem(cad.getOperatingSystem()); //操作系统
                    generalAsset.setAssetType("0");
                    computerAssetDAO.updateComputerAssetCard1(computerAsset);
                    generalAssetDAO.updateGeneralAssetCard(generalAsset);
                }
                if ("行政资产".equals(assetType)) { //2

                    AdministrationAsset administrationAsset = new AdministrationAsset();
                    administrationAsset.setId(Long.valueOf(assetDetailId));
                    administrationAsset.setSerialNumber(cad.getSerialNumber()); //出厂编号/序列号
                    administrationAsset.setManagementCode(cad.getPutUnderManageCode()); //管理编码
                    generalAsset.setAssetType("1");
                    administrationAssetDAO.updateAdministrationAsset1(administrationAsset);
                    generalAssetDAO.updateGeneralAssetCard(generalAsset);
                }
                if ("计量器具".equals(assetType)) { //3
				/*计量器具*/
                    MeasuringInstruments measuringInstruments = new MeasuringInstruments();
                    measuringInstruments.setId(Long.valueOf(assetDetailId));
                    measuringInstruments.setSerialNumber(cad.getSerialNumber());
                    measuringInstruments.setFacrotyNumber(cad.getSerialNumber()); //出厂编号
                    measuringInstruments.setManagementCode(cad.getPutUnderManageCode()); //管理编号
                    measuringInstruments.setManufactureFactory(cad.getManufactureFactory()); //生产厂家
                    measuringInstruments.setManagementLevel(cad.getManagementLevel()); //管理级别
                    measuringInstruments.setAccuracyClass(cad.getAccuracyClass()); //精度等级
                    measuringInstruments.setVerificationCycle(cad.getVerificationCycle()); //检定周期
                    measuringInstruments.setCalibrationDate(sdf.parse(cad.getCalibrationDate())); //校准日期
                    measuringInstruments.setNextCalibrationDate(sdf.parse(cad.getNextCalibrationDate())); //下次校准日期
                    measuringInstruments.setCalibrationUnit(cad.getCalibrationUnit()); //校准单位
                    measuringInstruments.setIsThirdReport(cad.getIsThirdReport()); //是否有第三方校验报告
                    generalAsset.setAssetType("2");
                    measuringInstrumentsDAO.updateMeasuringInstrumentsCard1(measuringInstruments);
                    generalAssetDAO.updateGeneralAssetCard(generalAsset);
                }
                if ("机器设备".equals(assetType)) { //4
				/*机器设备*/
                    MachineryEquipment machineryEquipment = new MachineryEquipment();
                    machineryEquipment.setId(Long.valueOf(assetDetailId));
                    machineryEquipment.setMachineCode(cad.getSerialNumber()); //机器编号(序列号,出厂编号)
                    machineryEquipment.setShopType(cad.getShopType()); //车间类型
                    machineryEquipment.setPutUnderManageCode(cad.getPutUnderManageCode()); //归口管理编码
                    generalAsset.setAssetType("3");
                    if (null != machineryEquipment.getShopType() || null != machineryEquipment.getMachineCode() || null != machineryEquipment.getPutUnderManageCode()) {
                        machineryEquipmentDAO.updateMachineryEquipmentCard1(machineryEquipment);
                    }
                    generalAssetDAO.updateGeneralAssetCard(generalAsset);
                }
            }
        }
        /**
         * 判断当前审批流状态为已完成
         */
        //if (APPROVESTATE.DONE.getApproveState().trim().equals(ca.getApprovalState())) {
        if (flag) {
            /**
             * 更改收货单关闭状态
             */
            checkAcceptMappingDAO.updateReceiveOrderStatus(checkAcceptanceModel.getReceiveGoodsId());
			/*	回写订单明细表 的验收数量	（回写tb_fa_purchase_order_detail.check_count）*/
            PurchaseCommon pc = new PurchaseCommon();
            pc.setThisCheckCount(ca.getThisCheckCount());
            pc.setPurchaseId(ca.getPurchaseId());
            pc.setPurchaseDetailId(ca.getPurchaseDetailId());
            purchaseCommonDAO.updatePurchaseOrderDetailCheckCountByPurchaseDetailId(pc);
            //更细采购订单主表状态
            purchaseOrderDAO.updateOrderStatusByGoodsCount(ca.getPurchaseId());
            //验收表主键
            List<CheckAcceptanceDetail> checkAcceptanceDetaillist = checkAcceptanceDetailDAO.getCheckAcceptanceDetailByDocument(ca.getCdocument()); //行明细
            for (CheckAcceptanceDetail cat : checkAcceptanceDetaillist) {
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setId(Long.parseLong(cat.getCardId()));
                //数据库中写死
                //generalAsset.setAssetState("1");
                //generalAsset.setUseState("0");
                //generalAsset.setPostingDate(new Date());
                generalAssetDAO.updatePostingDate(generalAsset);
            }
            for (CheckAcceptanceDetail cad : list) {
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setId(Long.valueOf(cad.getCardId()));
                generalAsset.setLocation(cad.getStorageLocation());
                /**
                 * 取出收货时存的质保周期，加上当前日期，计算出质保到期日,这一块应该在审批流最后一步同意的时候做
                 */
                GeneralAsset ga = generalAssetDAO.getGeneralAsset(Integer.parseInt(cad.getCardId()));
//                String assetDetailId = ga.getAssetDetailId().toString();
                String warrantyPeriod = ga.getWarrantyPeriod(); //取出收货时存的质保周期，单位是月
                Date dt = new Date(); //当前时间
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(dt);
                rightNow.add(Calendar.MONTH, +Integer.parseInt(warrantyPeriod));
                Date dt1 = rightNow.getTime();
                String reStr = sdf.format(dt1);
                Date date = null;
                date = sdf.parse(reStr);
                generalAsset.setExpirationDate(date); //质保到期日
                generalAsset.setActualDateAcceptance(sdf.parse(sdf.format(dt))); //实际验收日期
                generalAssetDAO.updateGeneralAssetCard(generalAsset);
            }
        }
        return executeResult;
    }

    //	退回
    @Override
    public ExecuteResult<CheckAcceptance> denyWorkFlow(CheckAcceptanceModel checkAcceptanceModel) {
        ExecuteResult<CheckAcceptance> executeResult = new ExecuteResult<CheckAcceptance>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();
            backFlowParam.setBillCode(checkAcceptanceModel.getCdocument());
            backFlowParam.setBillTableName("TB_FA_CHECK_ACCEPTANCE");
            backFlowParam.setCreatorCode(checkAcceptanceModel.getCuserCode()); //单据创建人编码
            backFlowParam.setCreatorName(checkAcceptanceModel.getCuserName());
            backFlowParam.setIdea(checkAcceptanceModel.getIdea());
            backFlowParam.setCUserCode(checkAcceptanceModel.getCuserCode()); //当前操作人编码
            backFlowParam.setCUserName(checkAcceptanceModel.getCuserName());
            backFlowParam.setUserDepCode(checkAcceptanceModel.getUserDepCode()); //当前操作人所属部门编码
            backFlowParam.setTypeCode("77078");
            backFlowParam.setActivityCode(checkAcceptanceModel.getActivityId());
            backFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());
            backFlowParam.setIsEmail(checkAcceptanceModel.getIsEmail());
            backFlowParam.setIsPhoneMessage(checkAcceptanceModel.getIsPhoneMessage());
            back.setParam(backFlowParam);
            back.setUserCode(checkAcceptanceModel.getCuserCode());
            back.setUserName(checkAcceptanceModel.getCuserName());
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
    public ExecuteResult<CheckAcceptance> dropWorkFlow(
            CheckAcceptanceModel checkAcceptanceModel) {
        ExecuteResult<CheckAcceptance> executeResult = new ExecuteResult<CheckAcceptance>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();
            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(checkAcceptanceModel.getCdocument());
            unApprovalFlowParam.setBillTableName("TB_FA_CHECK_ACCEPTANCE");
            unApprovalFlowParam.setBillCodeColumnName("CDOCUMENT");
            unApprovalFlowParam.setCreatorCode(checkAcceptanceModel.getCuserCode());
            unApprovalFlowParam.setCreatorName(checkAcceptanceModel.getCuserName());
            unApprovalFlowParam.setIdea(checkAcceptanceModel.getIdea());
            unApprovalFlowParam.setCUserCode(checkAcceptanceModel.getCuserCode()); //单据创建人编码
            unApprovalFlowParam.setCUserName(checkAcceptanceModel.getCuserName());
            unApprovalFlowParam.setUserDepCode(checkAcceptanceModel.getUserDepCode()); //当前操作人所属部门编码
            unApprovalFlowParam.setTypeCode("77078");
            unApproval.setParam(unApprovalFlowParam);
            unApproval.setUserCode(checkAcceptanceModel.getCuserCode());
            unApproval.setUserName(checkAcceptanceModel.getCuserName());
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

    //	(验收单 修改) 查询待验收的需求明细
    @Override
    public List<CheckAcceptMapping> searchAllDemandDetailIdByCheckAcceptId(
            String checkAcceptId) {
        return checkAcceptMappingDAO.searchAllDemandDetailIdByCheckAcceptId(checkAcceptId);
    }

    //	(验收单 修改) 查询 得到验收单的 行
    @Override
    public List<CheckAcceptMapping> getCheckAcceptanceDetailByCheckAcceptId(
            String checkAcceptId) {
        return checkAcceptMappingDAO.getCheckAcceptanceDetailByCheckAcceptId(checkAcceptId);
    }

    //	(验收单 修改 ) 保存
    @Override
    public Long saveUpdate(CheckAcceptance checkAcceptance, List<CheckAcceptanceDetail> checkAcceptanceDetaillist, List<CheckAcceptMapping> checkAcceptMappinglist, CheckMachine checkMachine) throws ParseException {
        Long isSave = null;
		/*头*/
        isSave = checkAcceptanceDAO.updateCheckAcceptance(checkAcceptance);
        if ("机器设备".equals(checkAcceptance.getAssetsType())) {
            checkAcceptanceDAO.saveCheckMachine(checkMachine);
        }
		/*行  修改行信息，先删除，在保存*/
        //checkAcceptanceDetailDAO.delCheckAcceptanceByCheckAcceptId(checkAcceptance.getCdocument());
        for (CheckAcceptanceDetail cad : checkAcceptanceDetaillist) {
            checkAcceptanceDetailDAO.updateCheckAcceptanceDetailById(cad);
        }
		/*回写收货验收映射表tb_fa_receive_check_mapping.check_count_demand*/
        /**
         * 修改的时候，不可以修改数量！！！
         * 只可以修改信息
         */
        /**
         * 回写PurchaseCommon表中存放位置
         */
        for (CheckAcceptanceDetail cad : checkAcceptanceDetaillist) {
            PurchaseCommon pcommom = new PurchaseCommon();
            pcommom.setCardId(cad.getCardId());
            pcommom.setStorageLocation(cad.getStorageLocation());
            pcommom.setReceiveGoodsId(cad.getReceiveGoodsId());
            pcommom.setApplyDetailId(cad.getApplyDetailId());
            pcommom.setDemandDetailId(cad.getDemandDetailId());
            pcommom.setSapAssetsCode(cad.getSapAssetsCode());
            purchaseCommonDAO.updatePurchaseCommonBySapAssetsCodeChange(pcommom);
        }
		/*for (CheckAcceptMapping cam : checkAcceptMappinglist) {
				checkAcceptMappingDAO.updateReceiveCheckMappingByDemandDetailIdChange(cam);
		}
		*//*  回写订单申请映射表 的验收数量  （回写tb_fa_purchase_apply_mapping.check_count）*//*
		for (CheckAcceptMapping cam : checkAcceptMappinglist) {
			PurchaseCommon purchaseCommon = new PurchaseCommon();
			purchaseCommon.setPurchaseId(cam.getPurchaseId());
			purchaseCommon.setApplyDetailId(cam.getApplyDetailId());
			purchaseCommon.setDemandDetailId(cam.getDemandDetailId());			
			purchaseCommon.setCheckCountDemand(cam.getCheckCountDemand());
			purchaseCommonDAO.updatePurchaseApplyMappingCheckCountByDemandDetailIdChange(purchaseCommon);
		}
		*//* 回写sap资产公共表(tb_fa_purchase_common.check_status),验收后将receiveStatus 为N ，checkStatus为 Y，修改时，先将receiveStatus 改为Y，在将要修改的checkStatus改为Y *//*
		purchaseCommonDAO.updatePurchaseCommonByReceiveGoodsId(checkAcceptance.getReceiveGoodsId());
		for (CheckAcceptanceDetail cad : checkAcceptanceDetaillist) {
			PurchaseCommon pcommom = new PurchaseCommon();
			pcommom.setReceiveGoodsId(cad.getReceiveGoodsId());
			pcommom.setApplyDetailId(cad.getApplyDetailId());
			pcommom.setDemandDetailId(cad.getDemandDetailId());
			pcommom.setSapAssetsCode(cad.getSapAssetsCode());
			purchaseCommonDAO.updatePurchaseCommonBySapAssetsCodeChange(pcommom);
		}
		*//*	回写订单明细表 的验收数量	（回写tb_fa_purchase_order_detail.check_count）*//*
		PurchaseCommon pc = new PurchaseCommon();
		pc.setCheckCount(checkAcceptance.getCheckCount());
		pc.setPurchaseId(checkAcceptance.getPurchaseId());
		pc.setPurchaseDetailId(checkAcceptance.getPurchaseDetailId());
		purchaseCommonDAO.updatePurchaseOrderDetailCheckCountByPurchaseDetailIdChange(pc);
		*//*  回写收货行已经收货数量( tb_fa_receive_goods_detail.check_count)*//*
		checkAcceptMappingDAO.updateReceiveGoodsDetailByReceiveGoodsDetailIdChange(checkAcceptance);*/

		 /*回写资产卡片的表（tb_fa_computer_asset--IT资产（电脑类）*/

        String assetsType = checkAcceptance.getAssetsType();
        String purchaseId = checkAcceptance.getPurchaseId();
        String checkAcceptId = checkAcceptance.getCheckAcceptId();
        String brand = checkAcceptance.getBrand();
        //String storageLocation = checkAcceptance.getStorageLocation();
        String useInfo = checkAcceptance.getUseInfo();
        String useStatus = checkAcceptance.getUseStatus();
        String assetPerson = checkAcceptance.getAssetPerson();
        String computerCode = checkAcceptance.getComputerCode();
        String verificationCycle = checkAcceptance.getCheckCycle();
        for (int i = 0; checkAcceptanceDetaillist.size() > i; i++) {
            CheckAcceptanceDetail cad = checkAcceptanceDetaillist.get(i);
            String cardId = cad.getCardId();
//			String assetDetailId = cad.getAssetDetailId();
//			String demandId = cad.getDemandId();
//			String applyId = cad.getApplyId();
//			String mmAssetsCode = cad.getMmAssetsCode();
		/*回写资产卡片的表（tb_fa_computer_asset--IT资产（电脑类）*/
//		String assetsType = checkAcceptance.getAssetsType();
//		computerAsset.setCompanyCode(computerCode_);
//		computerAsset.setCostCenterCode();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*行政资产和非电脑类 资产卡片主表 保存一些公共的字段  */
            GeneralAsset generalAsset = new GeneralAsset();
            generalAsset.setId(Long.valueOf(cardId));
            //根据主键得到在收货时存在资产卡片中的信息
            GeneralAsset ga = generalAssetDAO.getGeneralAsset(Integer.parseInt(cardId));
            String assetDetailId = ga.getAssetDetailId().toString();
            /**
             * 取出收货时存的质保周期，加上当前日期，计算出质保到期日,这一块应该在审批流最后一步同意的时候做
             */
            String warrantyPeriod = ga.getWarrantyPeriod(); //取出收货时存的质保周期，单位是月
            Date dt = new Date(); //当前时间
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MONTH, +Integer.parseInt(warrantyPeriod));
            Date dt1 = rightNow.getTime();
            String reStr = sdf.format(dt1);
            Date date = null;
            try {
                date = sdf.parse(reStr);
                generalAsset.setExpirationDate(date); //质保到期日
                generalAsset.setActualDateAcceptance(sdf.parse(sdf.format(dt))); //实际验收日期
            } catch (ParseException e) {
                e.printStackTrace();
            }
            /**
             * 下载文件对应的值
             * 当资产类型为计量器具时，需要上传图片和检验证书，对应的参数为mmAssetsCode
             */
            generalAsset.setSpecificationModel(brand);
            //generalAsset.setUseState(useInfo);
            // generalAsset.setAssetState(useStatus);
            generalAsset.setPersentStorageLocation(cad.getStorageLocation());
            generalAsset.setUpdateBy(assetPerson);
            generalAsset.setUpdateDate(new Date());
            generalAsset.setLocation(cad.getStorageLocation());
            if ("IT资产".equals(assetsType)) { //0
				/*IT资产  资产卡片电脑类 */
                ComputerAsset computerAsset = new ComputerAsset();
                computerAsset.setId(Long.valueOf(assetDetailId));
                computerAsset.setPurchaseId(purchaseId);
                computerAsset.setCheckAcceptId(checkAcceptId);
//				computerAsset.setDemandId(demandId);
//				computerAsset.setApplyId(applyId);
                computerAsset.setCreateBy(assetPerson);
                computerAsset.setCreateDate(new Date());
                computerAsset.setUpdateBy(assetPerson);
                computerAsset.setUpdateDate(new Date());
                computerAsset.setSerialNumber(cad.getSerialNumber()); //序号
                computerAsset.setPutUnderManageCode(cad.getPutUnderManageCode()); //归口管理编码
                computerAsset.setCpu(cad.getCpu()); //cpu
                computerAsset.setInternalMemory(cad.getInternalMemory()); //内存
                computerAsset.setHardpan(cad.getHardpan()); //硬盘
                computerAsset.setIpAddress(cad.getIpAddress()); //IP地址
                computerAsset.setJoinDomain(cad.getJoinDomain()); //加入域
                computerAsset.setDescription(cad.getDescription()); //描述
                computerAsset.setUtilitySystem(cad.getUtilitySystem()); //应用系统
                computerAsset.setDataBase(cad.getDataBase()); //数据库
                computerAsset.setOperatingSystem(cad.getOperatingSystem()); //操作系统
                generalAsset.setAssetType("0");
                computerAssetDAO.updateComputerAssetCard(computerAsset);
                generalAssetDAO.updateGeneralAssetCard_(generalAsset);
            }
            if ("行政资产".equals(assetsType)) { //2
                AdministrationAsset administrationAsset = new AdministrationAsset();
                administrationAsset.setId(Long.valueOf(assetDetailId));
                administrationAsset.setSerialNumber(cad.getSerialNumber()); //出厂编号/序列号
                administrationAsset.setManagementCode(cad.getPutUnderManageCode()); //管理编码
                generalAsset.setAssetType("1");
                administrationAssetDAO.updateAdministrationAsset(administrationAsset);
                generalAssetDAO.updateGeneralAssetCard_(generalAsset);
            }
            if ("计量器具".equals(assetsType)) { //3
				/*计量器具*/
                MeasuringInstruments measuringInstruments = new MeasuringInstruments();
                measuringInstruments.setId(Long.valueOf(assetDetailId));
                measuringInstruments.setPurchaseId(purchaseId);
                measuringInstruments.setCheckAcceptId(checkAcceptId);
//				measuringInstruments.setDemandId(demandId);
//				measuringInstruments.setApplyId(applyId);
                measuringInstruments.setCreateBy(assetPerson);
                measuringInstruments.setCreateDate(new Date());
                measuringInstruments.setUpdateBy(assetPerson);
                measuringInstruments.setUpdateDate(new Date());
                measuringInstruments.setCompanyCode(computerCode);
                measuringInstruments.setVerificationCycle(verificationCycle);
                measuringInstruments.setCheckAcceptId(checkAcceptId);
                measuringInstruments.setInspectionDate(new Date());
                measuringInstruments.setFacrotyNumber(cad.getSerialNumber()); //出厂编号
                measuringInstruments.setManagementCode(cad.getPutUnderManageCode()); //管理编号
                measuringInstruments.setManufactureFactory(cad.getManufactureFactory()); //生产厂家
                measuringInstruments.setManagementLevel(cad.getManagementLevel()); //管理级别
                measuringInstruments.setAccuracyClass(cad.getAccuracyClass()); //精度等级
                measuringInstruments.setVerificationCycle(cad.getVerificationCycle()); //检定周期
                try {
                    measuringInstruments.setCalibrationDate(sdf.parse(cad.getCalibrationDate())); //校准日期
                    measuringInstruments.setNextCalibrationDate(sdf.parse(cad.getNextCalibrationDate())); //下次校准日期
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                measuringInstruments.setCalibrationUnit(cad.getCalibrationUnit()); //校准单位
                measuringInstruments.setIsThirdReport(cad.getIsThirdReport()); //是否有第三方校验报告
                generalAsset.setAssetType("2");
                measuringInstrumentsDAO.updateMeasuringInstrumentsCard(measuringInstruments);
                generalAssetDAO.updateGeneralAssetCard_(generalAsset);
            }
            if ("机器设备".equals(assetsType)) { //4
				/*机器设备*/
                MachineryEquipment machineryEquipment = new MachineryEquipment();
                machineryEquipment.setId(Long.valueOf(assetDetailId));
                machineryEquipment.setPurchaseId(purchaseId);
                machineryEquipment.setCheckAcceptId(checkAcceptId);
                machineryEquipment.setCreateBy(assetPerson);
//				machineryEquipment.setDemandId(demandId);
//				machineryEquipment.setApplyId(applyId);
                machineryEquipment.setCreateDate(new Date());
                machineryEquipment.setUpdateBy(assetPerson);
                machineryEquipment.setUpdateDate(new Date());
                machineryEquipment.setCompanyCode(computerCode);
                machineryEquipment.setMachineCode(cad.getSerialNumber()); //机器编号(序列号,出厂编号)
                machineryEquipment.setShopType(cad.getShopType()); //车间类型
                machineryEquipment.setPutUnderManageCode(cad.getPutUnderManageCode()); //归口管理编码
                generalAsset.setAssetType("3");
                machineryEquipmentDAO.updateMachineryEquipmentCard(machineryEquipment);
                generalAssetDAO.updateGeneralAssetCard_(generalAsset);
            }
        }
        return isSave;

    }

    @Override
    public List<CheckAcceptMapping> getEbeln(String purchaseId) {
        return checkAcceptMappingDAO.getEbeln(purchaseId);
    }

    @Override
    public CheckAcceptMapping getEbelnOnly(PurchaseCommon purchaseCommon) {
        return checkAcceptMappingDAO.getEbelnOnly(purchaseCommon);
    }

    /**
     * checkAcceptMapping实体类中应添加资产卡片中的属性,用于前台展示.
     *
     * @param cam
     * @return
     */
    @Override
    public CheckAcceptanceDetail searchAssetFormation(CheckAcceptanceDetail cam) {
        GeneralAsset ga = generalAssetDAO.getGeneralAsset(Integer.parseInt(cam.getCardId())); //根据主键查询资产卡片主表信息
        Integer zibiaoid = ga.getAssetDetailId();
        String assetsType = ga.getAssetType();
        if ("0".equals(assetsType)) { //IT资产
            ComputerAsset computerAsset1 = new ComputerAsset();
            computerAsset1.setId(Long.parseLong(zibiaoid.toString()));
            ComputerAsset computerAsset = computerAssetDAO.searchComputerAsset(computerAsset1);
            cam.setManageapplyDepartmentCode(computerAsset.getPutUnderManageCode() == null ? "" : computerAsset.getPutUnderManageCode()); //归口管理编码
            cam.setPutUnderManageCode(computerAsset.getPutUnderManageCode() == null ? "" : computerAsset.getPutUnderManageCode()); //归口管理编码
            cam.setSerialNumber(computerAsset.getSerialNumber() == null ? "" : computerAsset.getSerialNumber()); //序号
            cam.setCpu(computerAsset.getCpu() == null ? "" : computerAsset.getCpu()); //cpu
            cam.setInternalMemory(computerAsset.getInternalMemory() == null ? "" : computerAsset.getInternalMemory()); //内存
            cam.setContent(computerAsset.getContent() == null ? "" : computerAsset.getContent());
            cam.setHardpan(computerAsset.getHardpan() == null ? "" : computerAsset.getHardpan()); //硬盘
            cam.setIpAddress(computerAsset.getIpAddress() == null ? "" : computerAsset.getIpAddress()); //ip地址
            cam.setJoinDomain(computerAsset.getJoinDomain() == null ? "" : computerAsset.getJoinDomain()); //	private String joinDomain; //加入域：JOIN_DOMAIN
            cam.setOperatingSystem(computerAsset.getOperatingSystem() == null ? "" : computerAsset.getOperatingSystem()); //	private String operatingSystem; //操作系统：OPERATING_SYSTEM
            cam.setUtilitySystem(computerAsset.getUtilitySystem() == null ? "" : computerAsset.getUtilitySystem()); //	private String utilitySystem; //应用系统：UTILITY_SYSTEM
            cam.setDescription(computerAsset.getDescription() == null ? "" : computerAsset.getDescription()); //	private String description; //功能描述：DESCRIPTION
            cam.setDataBase(computerAsset.getDataBase() == null ? "" : computerAsset.getDataBase()); //	private String dataBase; //数据库：DATABASE
        }
        if ("1".equals(assetsType)) { //行政资产
            AdministrationAsset administrationAsset = new AdministrationAsset();
            administrationAsset.setId(Long.parseLong(zibiaoid.toString()));
            AdministrationAsset administrationAsset1 = administrationAssetDAO.getAdministrationAsset(administrationAsset);
            cam.setManageapplyDepartmentCode(administrationAsset1.getManagementCode() == null ? "" : administrationAsset1.getManagementCode()); //归口管理编码
            cam.setPutUnderManageCode(administrationAsset1.getManagementCode() == null ? "" : administrationAsset1.getManagementCode());
            cam.setSerialNumber(administrationAsset1.getSerialNumber() == null ? "" : administrationAsset1.getSerialNumber()); //序号
            cam.setIsBuildingContruction(administrationAsset1.getIsBuildingContruction() == null ? "" : administrationAsset1.getIsBuildingContruction());
            if ("Y".equals(cam.getIsBuildingContruction())) {
                cam.setIsBuildingContruction("是");
            }
            if ("N".equals(cam.getIsBuildingContruction())) {
                cam.setIsBuildingContruction("否");
            }
        }
        if ("2".equals(assetsType)) { //计量器具
				/*计量器具*/
            MeasuringInstruments measuringInstruments = new MeasuringInstruments();
            measuringInstruments.setId(Long.parseLong(zibiaoid.toString()));
            MeasuringInstruments measuringInstruments1 = measuringInstrumentsDAO.searchMeasuringInstruments(measuringInstruments);
            cam.setManageapplyDepartmentCode(measuringInstruments1.getManagementCode() == null ? "" : measuringInstruments1.getManagementCode()); //归口管理编码
            cam.setPutUnderManageCode(measuringInstruments1.getManagementCode() == null ? "" : measuringInstruments1.getManagementCode());
            cam.setSerialNumber(measuringInstruments1.getFacrotyNumber() == null ? "" : measuringInstruments1.getFacrotyNumber()); //序号
            cam.setManufactureFactory(measuringInstruments1.getManufactureFactory() == null ? "" : measuringInstruments1.getManufactureFactory()); //生产厂家
            cam.setManagementLevel(measuringInstruments1.getManagementLevel() == null ? "" : measuringInstruments1.getManagementLevel()); //管理级别
            cam.setAccuracyClass(measuringInstruments1.getAccuracyClass() == null ? "" : measuringInstruments1.getAccuracyClass()); //精度等级
            cam.setVerificationCycle(measuringInstruments1.getVerificationCycle() == null ? "" : measuringInstruments1.getVerificationCycle()); //检定周期
            cam.setIsThirdReport(measuringInstruments1.getIsThirdReport() == null ? "" : measuringInstruments1.getIsThirdReport()); //是否有第三方检验报告
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cam.setCalibrationDate(null == measuringInstruments1.getCalibrationDate() ? "" : sdf.format(measuringInstruments1.getCalibrationDate())); //校准日期
            cam.setNextCalibrationDate(null == measuringInstruments1.getNextCalibrationDate() ? "" : sdf.format(measuringInstruments1.getNextCalibrationDate())); //下次校准日期
            cam.setCalibrationUnit(measuringInstruments1.getCalibrationUnit() == null ? "" : measuringInstruments1.getCalibrationUnit()); //校准单位
        }
        if ("3".equals(assetsType)) { //机器设备
				/*机器设备*/
            MachineryEquipment machineryEquipment = new MachineryEquipment();
            machineryEquipment.setId(Long.parseLong(zibiaoid.toString()));
            MachineryEquipment machineryEquipment1 = machineryEquipmentDAO.searchMachineryEquipment(machineryEquipment);
            cam.setManageapplyDepartmentCode(machineryEquipment1.getPutUnderManageCode() == null ? "" : machineryEquipment1.getPutUnderManageCode()); //归口管理编码
            cam.setPutUnderManageCode(machineryEquipment1.getPutUnderManageCode() == null ? "" : machineryEquipment1.getPutUnderManageCode());
            cam.setSerialNumber(machineryEquipment1.getMachineCode() == null ? "" : machineryEquipment1.getMachineCode()); //序号
            cam.setShopType(machineryEquipment1.getShopType() == null ? "" : machineryEquipment1.getShopType()); //车间类型
        }
        return cam;
    }

    @Override
    public String searchDeptCode(String attachDepartManagerCode) {
        return checkAcceptMappingDAO.searchDeptCode(attachDepartManagerCode);
    }

    @Override
    public Long getCountCheckAcceptanceByReceiveGoodsId(String receiveGoodsId) {
        return checkAcceptMappingDAO.getCountCheckAcceptanceByReceiveGoodsId(receiveGoodsId);
    }

    @Override
    public void deleteCheck(List<String> list1) {
        for (String acceptId : list1) {
            CheckAcceptance checkAcceptance = checkAcceptanceDAO.getCheckAcceptanceByDocument(acceptId);
            checkAcceptanceDAO.saveDelete(checkAcceptance);
            List<CheckAcceptanceDetail> checkAcceptanceDetailList = checkAcceptanceDetailDAO.getCheckAcceptanceDetailByDocument(acceptId);
            checkAcceptanceDetailDAO.saveDeleteBranch(checkAcceptanceDetailList);
            List<CheckAcceptMapping> checkAcceptMappingList = checkAcceptMappingDAO.searchAllDemandDetailIdByCheckAcceptId(acceptId);

            List<CheckAcceptMapping> list = checkAcceptMappingDAO.getCheckAcceptMapping(acceptId);
            //删除头信息
            checkAcceptanceDAO.delCheckAcceptanceByCheckAcceptId(acceptId);
            //删除行信息
            checkAcceptanceDetailDAO.delCheckAcceptanceByCheckAcceptId(acceptId);
            //回写收货验收映射表tb_fa_receive_check_mapping.check_count_demand
            for (CheckAcceptMapping cam : list) {
                checkAcceptMappingDAO.updateReceiveCheckMappingByDemandDetailId(cam);
            }
		/*  回写订单申请映射表 的验收数量  （回写tb_fa_purchase_apply_mapping.check_count）*/
            for (CheckAcceptMapping cam : list) {
                PurchaseCommon purchaseCommon = new PurchaseCommon();
                purchaseCommon.setPurchaseId(cam.getPurchaseId());
                purchaseCommon.setApplyDetailId(cam.getApplyDetailId());
                purchaseCommon.setDemandDetailId(cam.getDemandDetailId());
                purchaseCommon.setThisCheckCountDemand(cam.getThisCheckCountDemand());
                purchaseCommonDAO.updatePurchaseApplyMappingCheckCountByDemandDetailId(purchaseCommon);
            }

		/* 回写sap资产公共表(tb_fa_purchase_common.check_status),收完货后更改验收状态，更改后的数据在查询时，将不再显示 */
            for (CheckAcceptanceDetail cad : checkAcceptanceDetailList) {
                PurchaseCommon pcommom = new PurchaseCommon();

                pcommom.setReceiveGoodsId(cad.getReceiveGoodsId());
                pcommom.setApplyDetailId(cad.getApplyDetailId());
                pcommom.setDemandDetailId(cad.getDemandDetailId());
                pcommom.setSapAssetsCode(cad.getSapAssetsCode());
                //可能需要在验收审批流的最后一步保存验收人code
                pcommom.setBrand(checkAcceptance.getBrand());
                //pcommom.setCheckAcceptId(checkAcceptance.getCheckAcceptId()); //验收单编码
                purchaseCommonDAO.updatePurchaseCommonBySapAssetsCode1(pcommom);
            }

		/*  回写收货行已经收货数量( tb_fa_receive_goods_detail.check_count)*/
            CheckAcceptance cat = checkAcceptance;
            cat.setThisCheckCount(-checkAcceptance.getThisCheckCount());
            checkAcceptMappingDAO.updateReceiveGoodsDetailByReceiveGoodsDetailId(checkAcceptance);


            String assetsType = checkAcceptance.getAssetsType();
            String useInfo = checkAcceptance.getUseInfo();
            for (int i = 0; checkAcceptanceDetailList.size() > i; i++) {
                CheckAcceptanceDetail cad = checkAcceptanceDetailList.get(i);
                String cardId = cad.getCardId();
			/*String demandId = cad.getDemandId();
			String applyId = cad.getApplyId();
			String mmAssetsCode = cad.getMmAssetsCode();*/
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*行政资产和非电脑类 资产卡片主表 保存一些公共的字段  */
                GeneralAsset generalAsset = new GeneralAsset();
                GeneralAsset ga = generalAssetDAO.getGeneralAsset(Integer.parseInt(cardId));
                String assetDetailId = ga.getAssetDetailId().toString();
                generalAsset.setId(Long.valueOf(cardId));
                //根据主键得到在收货时存在资产卡片中的信息
                generalAsset.setExpirationDate(null); //质保到期日
                generalAsset.setActualDateAcceptance(null); //实际验收日期
                /**
                 * 下载文件对应的值
                 * 当资产类型为计量器具时，需要上传图片和检验证书，对应的参数为mmAssetsCode
                 */
                generalAsset.setDocument("");
                generalAsset.setCheckAcceptId("");
                generalAsset.setUseState(useInfo);
                generalAsset.setPersentStorageLocation("");
                generalAsset.setUpdateBy("");
                generalAsset.setUpdateDate(null);
                generalAsset.setUnit(""); //单位，以前没存，这里要存


                if ("IT资产".equals(assetsType)) { //0
				/*IT资产  资产卡片电脑类 */
                    ComputerAsset computerAsset = new ComputerAsset();
                    computerAsset.setId(Long.valueOf(assetDetailId));
                    computerAsset.setSerialNumber(""); //序号
                    computerAsset.setPutUnderManageCode(""); //归口管理编码
                    computerAsset.setCpu(""); //cpu
                    computerAsset.setInternalMemory(""); //内存
                    computerAsset.setHardpan(""); //硬盘
                    computerAsset.setIpAddress(""); //IP地址
                    computerAsset.setJoinDomain(""); //加入域
                    computerAsset.setDescription(""); //描述
                    computerAsset.setUtilitySystem(""); //应用系统
                    computerAsset.setDataBase(""); //数据库
                    computerAsset.setOperatingSystem(""); //操作系统
//				generalAsset.setAssetType("0");
                    computerAssetDAO.updateComputerAssetCard2(computerAsset);
                    generalAssetDAO.updateGeneralAssetCard2(generalAsset);
                }

                if ("行政资产".equals(assetsType)) { //2
                    AdministrationAsset administrationAsset = new AdministrationAsset();
                    administrationAsset.setId(Long.valueOf(assetDetailId));
                    administrationAsset.setSerialNumber(""); //出厂编号/序列号
                    administrationAsset.setManagementCode(""); //管理编码
//				generalAsset.setAssetType("1");
                    administrationAssetDAO.updateAdministrationAsset2(administrationAsset);
                    generalAssetDAO.updateGeneralAssetCard2(generalAsset);
                }
                if ("计量器具".equals(assetsType)) { //3
				/*计量器具*/
                    MeasuringInstruments measuringInstruments = new MeasuringInstruments();
                    measuringInstruments.setId(Long.valueOf(assetDetailId));
                    measuringInstruments.setPurchaseId("");
                    measuringInstruments.setCheckAcceptId("");
                    measuringInstruments.setDemandId("");
                    measuringInstruments.setApplyId("");
                    measuringInstruments.setCreateBy("");
                    measuringInstruments.setCreateDate(null);
                    measuringInstruments.setUpdateBy("");
                    measuringInstruments.setUpdateDate(null);
                    measuringInstruments.setCompanyCode("");
                    measuringInstruments.setVerificationCycle("");
                    measuringInstruments.setCheckAcceptId("");
                    measuringInstruments.setInspectionDate(null);
                    measuringInstruments.setFacrotyNumber(""); //出厂编号
                    measuringInstruments.setManagementCode(""); //管理编号
                    measuringInstruments.setManufactureFactory(""); //生产厂家
                    measuringInstruments.setManagementLevel(""); //管理级别
                    measuringInstruments.setAccuracyClass(""); //精度等级
                    measuringInstruments.setVerificationCycle(""); //检定周期
                    measuringInstruments.setCalibrationDate(null); //校准日期
                    measuringInstruments.setNextCalibrationDate(null); //下次校准日期
                    measuringInstruments.setCalibrationUnit(""); //校准单位
                    measuringInstruments.setIsThirdReport(""); //是否有第三方校验报告
//				generalAsset.setAssetType("2");
                    measuringInstrumentsDAO.updateMeasuringInstrumentsCard2(measuringInstruments);
                    generalAssetDAO.updateGeneralAssetCard2(generalAsset);
                }
                if ("机器设备".equals(assetsType)) { //4
				/*机器设备*/
                    MachineryEquipment machineryEquipment = new MachineryEquipment();
                    machineryEquipment.setId(Long.valueOf(assetDetailId));
                    machineryEquipment.setPurchaseId("");
                    machineryEquipment.setCheckAcceptId("");
                    machineryEquipment.setCreateBy("");
                    machineryEquipment.setDemandId("");
                    machineryEquipment.setApplyId("");
                    machineryEquipment.setCreateDate(null);
                    machineryEquipment.setUpdateBy("");
                    machineryEquipment.setUpdateDate(null);
                    machineryEquipment.setCompanyCode("");
                    machineryEquipment.setMachineCode(""); //机器编号(序列号,出厂编号)
                    machineryEquipment.setShopType(""); //车间类型
                    machineryEquipment.setPutUnderManageCode(""); //归口管理编码
//				generalAsset.setAssetType("3");
                    machineryEquipmentDAO.updateMachineryEquipmentCard2(machineryEquipment);
                    generalAssetDAO.updateGeneralAssetCard2(generalAsset);
                }

            }
        }
    }

    @Override
    public Pager<CheckAcceptMapping> searchCheckAcceptMappingAll(Pager<CheckAcceptMapping> pager, CheckAcceptMapping checkAcceptMapping) {
        List<CheckAcceptMapping> searchCheckMapping = checkAcceptMappingDAO.searchCheckAcceptMappingAll(pager, checkAcceptMapping);
        Long searchCheckMappingCount = checkAcceptMappingDAO.searchCheckAcceptMappingAllCount(checkAcceptMapping);
        return Pager.cloneFromPager(pager, searchCheckMappingCount, searchCheckMapping);
    }

    @Override
    public List<CheckAcceptMapping> exportCheckHaveChecked(CheckAcceptMapping checkAcceptMapping) {
        return checkAcceptMappingDAO.exportCheckHaveChecked(checkAcceptMapping);
    }


}
