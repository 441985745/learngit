package com.opple.fa.purchase.service.impl;

import com.opple.fa.config.dao.AssetsBudgetDAO;
import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.config.entity.Department;
import com.opple.fa.purchase.dao.ApplyDemandMappingDAO;
import com.opple.fa.purchase.dao.ApplyOrderDAO;
import com.opple.fa.purchase.dao.ApplyOrderDetailDAO;
import com.opple.fa.purchase.dao.DemandOrderDAO;
import com.opple.fa.purchase.dao.DemandOrderDetailDAO;
import com.opple.fa.purchase.entity.ApplyDemandMapping;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.DemandApplyBudget;
import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.entity.OutBudgetException;
import com.opple.fa.purchase.model.ApplyDemandMappingModel;
import com.opple.fa.purchase.model.ApplyOrderModel;
import com.opple.fa.purchase.model.DemandOrderDetailModel;
import com.opple.fa.purchase.service.ApplyOrderService;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

public class ApplyOrderServiceImpl implements ApplyOrderService {

    public static final String APPLY_INFO_TABLE = "TB_FA_APPLY_ORDER";

    public static final String APPLY_INFO_TYPE_ID = "77011";
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyOrderServiceImpl.class);


    @Getter
    @Setter
    private ApplyOrderDAO applyOrderDAO;
    @Getter
    @Setter
    private ApplyOrderDetailDAO applyOrderDetailDAO;
    @Getter
    @Setter
    private ApplyDemandMappingDAO applyDemandMappingDAO;
    @Getter
    @Setter
    private DemandOrderDetailDAO demandOrderDetailDAO;
    @Getter
    @Setter
    private DemandOrderDAO demandOrderDAO;
    @Getter
    @Setter
    private AssetsBudgetDAO assetsBudgetDAO;

    private final TransactionTemplate transactionTemplate;

    public ApplyOrderServiceImpl(PlatformTransactionManager transactionManager) {
        Assert.notNull(transactionManager, "The 'transactionManager' argument must not be null.");
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    public Pager<ApplyOrder> searchApplyOrder(Pager<ApplyOrder> pager, ApplyOrderModel applyOrderModel) {
        List<ApplyOrder> searchApplyOrder = applyOrderDAO.searchApplyOrder(pager, applyOrderModel);
        Long searchApplyOrderCount = applyOrderDAO.searchApplyOrderCount(applyOrderModel);
        return Pager.cloneFromPager(pager, searchApplyOrderCount, searchApplyOrder);
    }

    @Override
    public ApplyOrder searchApplyOrderByOrderId(ApplyOrder applyOrder) {

        return applyOrderDAO.searchApplyOrderByOrderId(applyOrder);
    }

    @Override
    public ExecuteResult<ApplyOrder> addSaveApply(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetail,
                                                  List<ApplyDemandMapping> applyDemandMapping, List<DemandOrderDetailModel> demandOrderDetailModel) throws OutBudgetException {

        ExecuteResult<ApplyOrder> result = new ExecuteResult<ApplyOrder>();

        boolean ifRollback = false;
        // 根据订单ID查询该订单是否已存在
        ApplyOrder aOrder = new ApplyOrder();
        aOrder.setCdocument(applyOrder.getCdocument());
        ApplyOrder searchApplyOrderByOrderId = searchApplyOrderByOrderId(aOrder);
        if (searchApplyOrderByOrderId != null) {
            throw new OutBudgetException("新增订单失败,该订单号已存在！");
        } else {
            // 保存表头信息
            applyOrder.setCompanyName(applyOrder.getCompanyName() == null ? "" : applyOrder.getCompanyName().trim());
            applyOrderDAO.save(applyOrder);
        }
        return result;
    }

    @Override
    public ExecuteResult<ApplyOrder> addSaveApplyDetail(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetail,
                                                        List<ApplyDemandMapping> applyDemandMapping, List<DemandOrderDetailModel> demandOrderDetailModel) throws OutBudgetException {
        ExecuteResult<ApplyOrder> result = new ExecuteResult<ApplyOrder>();
        boolean ifRollback = false;

        // 保存表体信息
        for (ApplyOrderDetail aod : applyOrderDetail) {
            applyOrderDetailDAO.save(aod);
        }
        // 保存资产明细
        for (ApplyDemandMapping adm : applyDemandMapping) {
            applyDemandMappingDAO.save(adm);
        }
        // 回写采购需求信息
        if ("N".equals(applyOrder.getDraft())) {
            for (DemandOrderDetailModel dod : demandOrderDetailModel) {
                demandOrderDetailDAO.writeBack(dod);
            }
            //回写预算   张淑龙
            //根据申请单id，查询mapping表中申请数量，需求行项目编码，需求行单价，申请行中的单价
            List<DemandApplyBudget> demandApplyBudgets = applyDemandMappingDAO.searchDemandApplyBudget(applyOrder.getCdocument());
            BigDecimal price = new BigDecimal(0);
            for (DemandApplyBudget demandApplyBudget : demandApplyBudgets) {
                //申请单数量*（申请单询价单价-需求单询价单价）
                demandApplyBudget.setBudgetPrice((demandApplyBudget.getApplyPrice().subtract(demandApplyBudget.getDemandPrice())).multiply(BigDecimal.valueOf(demandApplyBudget.getDemandCount())));
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setBudgetYear(demandApplyBudget.getBudgetYear());
                assetsBudget.setUsedSumMoney(demandApplyBudget.getBudgetPrice());
                assetsBudget.setProjectCode(demandApplyBudget.getProjectCode());
                //查询可用预算金额与UsedSumMoney大小关系，若小于UsedSumMoney，说明超出预算，不允许提交
                AssetsBudget ab = assetsBudgetDAO.getAssetsBudgetbyProjectCodeAndYear(demandApplyBudget.getBudgetYear(), demandApplyBudget.getProjectCode());
                if (null == ab || null == ab.getAvaliableSumMoney()) {
                    throw new OutBudgetException("项目编码为" + assetsBudget.getProjectCode() + "可用预算不存在！");
                }
                BigDecimal avaliableSumMoney = ab.getAvaliableSumMoney();
                if (avaliableSumMoney.compareTo(assetsBudget.getUsedSumMoney()) == -1) {
                    throw new OutBudgetException("项目编码为" + assetsBudget.getProjectCode() + "超出预算，不能提交！");
                }
                assetsBudgetDAO.writeBack(assetsBudget);
            }
        }
        return result;
    }


    /* @Override
     public ExecuteResult<ApplyOrder> addSaveApplyDetail(final ApplyOrder applyOrder, final List<ApplyOrderDetail> applyOrderDetail,
                                                         final List<ApplyDemandMapping> applyDemandMapping, final List<DemandOrderDetailModel> demandOrderDetailModel) {

         final ExecuteResult<ApplyOrder> result = new ExecuteResult<ApplyOrder>();
         return transactionTemplate.execute(new TransactionCallback<ExecuteResult<ApplyOrder>>() {
             @Override
             public ExecuteResult<ApplyOrder> doInTransaction(TransactionStatus transactionStatus) {
                 boolean ifRollback = false;
                 try {

                     // 保存表体信息
                     for (ApplyOrderDetail aod : applyOrderDetail) {
                         applyOrderDetailDAO.save(aod);
                     }
                     // 保存资产明细
                     for (ApplyDemandMapping adm : applyDemandMapping) {
                         applyDemandMappingDAO.save(adm);
                     }
                     // 回写采购需求信息
                     if ("N".equals(applyOrder.getDraft())) {
                         for (DemandOrderDetailModel dod : demandOrderDetailModel) {
                             demandOrderDetailDAO.writeBack(dod);
                         }
                         //回写预算   张淑龙
                         //根据申请单id，查询mapping表中申请数量，需求行项目编码，需求行单价，申请行中的单价
                         List<DemandApplyBudget> demandApplyBudgets = applyDemandMappingDAO.searchDemandApplyBudget(applyOrder.getCdocument());
                         BigDecimal price = new BigDecimal(0);
                         for (DemandApplyBudget demandApplyBudget : demandApplyBudgets) {
                             //申请单数量*（申请单询价单价-需求单询价单价）
                             demandApplyBudget.setBudgetPrice((demandApplyBudget.getApplyPrice().subtract(demandApplyBudget.getDemandPrice())).multiply(BigDecimal.valueOf(demandApplyBudget.getDemandCount())));
                             AssetsBudget assetsBudget = new AssetsBudget();
                             assetsBudget.setBudgetYear(demandApplyBudget.getBudgetYear());
                             assetsBudget.setUsedSumMoney(demandApplyBudget.getBudgetPrice());
                             assetsBudget.setProjectCode(demandApplyBudget.getProjectCode());
                             //查询可用预算金额与UsedSumMoney大小关系，若小于UsedSumMoney，说明超出预算，不允许提交
                             BigDecimal avaliableSumMoney = assetsBudgetDAO.getAssetsBudgetbyProjectCodeAndYear(demandApplyBudget.getBudgetYear(), demandApplyBudget.getProjectCode()).getAvaliableSumMoney();
                             if (avaliableSumMoney.compareTo(assetsBudget.getUsedSumMoney()) == -1) {
                                 result.addErrorMessage("项目编码为" + assetsBudget.getProjectCode() + "超出预算，不能提交！");
                                 transactionStatus.setRollbackOnly();
                                 return result;
                             }
                             assetsBudgetDAO.writeBack(assetsBudget);
                         }
                     }

                 } catch (Exception e) {
                     ifRollback = true;
                     result.addErrorMessage("保存单据失败！");
                     LOGGER.error("采购申请单据保存", e);
                 }
                 if (ifRollback) {
                     transactionStatus.setRollbackOnly();
                 }
                 return result;
             }
         });
     }
 */
    @Override
    public ExecuteResult<ApplyOrder> addSaveUpdateApply(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetail,
                                                        List<ApplyDemandMapping> applyDemandMapping, List<DemandOrderDetailModel> demandOrderDetailModel, ApplyOrder oldApplyOrder) throws OutBudgetException, RemoteException {
        ExecuteResult<ApplyOrder> result = new ExecuteResult<ApplyOrder>();
        boolean ifRollback = false;
        // 根据订单ID查询修改前订单
        ApplyOrder aOrder = new ApplyOrder();
        aOrder.setCdocument(applyOrder.getCdocument());

        //当前操作是要提交而不是保存草稿
        if ("N".equals(applyOrder.getDraft())) {
            //修改前不是草稿，回写预算（现在的询价单价-之前的询价单价）* 现在的申请数量
            if ("N".equals(oldApplyOrder.getDraft())) {
                //首先将所有预算释放
                //回写预算   张淑龙
                //根据申请单id，查询mapping表中申请数量，需求行项目编码，需求行单价，申请行中的单价
                List<DemandApplyBudget> demandApplyBudgets = applyDemandMappingDAO.searchDemandApplyBudget(applyOrder.getCdocument());
                for (DemandApplyBudget demandApplyBudget : demandApplyBudgets) {
                    //申请单数量*申请单询价单价
                    demandApplyBudget.setBudgetPrice(BigDecimal.ZERO.subtract((demandApplyBudget.getApplyPrice().subtract(demandApplyBudget.getDemandPrice())).multiply(BigDecimal.valueOf(demandApplyBudget.getDemandCount()))));
                    AssetsBudget assetsBudget = new AssetsBudget();
                    assetsBudget.setBudgetYear(demandApplyBudget.getBudgetYear());
                    assetsBudget.setUsedSumMoney(demandApplyBudget.getBudgetPrice());
                    assetsBudget.setProjectCode(demandApplyBudget.getProjectCode());
                    assetsBudgetDAO.writeBack(assetsBudget);
                }
                //查询修改前申请行明细
                //释放完后，将修改后的预算差额占用
                for (ApplyDemandMapping adm : applyDemandMapping) {
                    AssetsBudget assetsBudget = new AssetsBudget();
                    DemandOrderDetail demandOrderDetail1 = new DemandOrderDetail();
                    DemandOrderDetailModel dod = new DemandOrderDetailModel();
                    BigDecimal inquiryUnitPrice = new BigDecimal(0);
                    for (ApplyOrderDetail aod : applyOrderDetail) {

                        if (aod.getMergeNumber().equals(adm.getMergeNumber()) && aod.getApplyOrderId().equals(adm.getApplyId())) {
                            //获得现在的行明细对应的需求单价
                            inquiryUnitPrice = aod.getInquiryUnitPrice();
                            demandOrderDetail1.setId(adm.getDemandDetailId());
                            dod = demandOrderDetailDAO.searchDemandOrderDetailById(demandOrderDetail1);
                            assetsBudget.setBudgetYear(dod.getBudgetYear());
                            //申请询价单价*申请数量
                        }
                    }
                    assetsBudget.setUsedSumMoney(inquiryUnitPrice.subtract(dod.getInquiryUnitPrice()).multiply(BigDecimal.valueOf(adm.getDemandCount())));
                    assetsBudget.setProjectCode(dod.getProjectCode());
                    //查询可用预算金额与UsedSumMoney大小关系，若小于UsedSumMoney，说明超出预算，不允许提交
                    BigDecimal avaliableSumMoney = assetsBudgetDAO.searchAssetsBudgetsByProjectCode(assetsBudget).getAvaliableSumMoney();
                    if (avaliableSumMoney.compareTo(assetsBudget.getUsedSumMoney()) == -1) {
                        throw new OutBudgetException("项目编码为" + assetsBudget.getProjectCode() + "超出预算，不能提交！");
                    }
                    assetsBudgetDAO.writeBack(assetsBudget);
                }

            } else { //如果修改前是草稿，修改后提交，则和新增是一样的
                for (ApplyDemandMapping adm : applyDemandMapping) {
                    AssetsBudget assetsBudget = new AssetsBudget();
                    DemandOrderDetail demandOrderDetail1 = new DemandOrderDetail();
                    DemandOrderDetailModel dod = new DemandOrderDetailModel();
                    BigDecimal inquiryUnitPrice = new BigDecimal(0);
                    for (ApplyOrderDetail aod : applyOrderDetail) {
                        if (aod.getMergeNumber().equals(adm.getMergeNumber())) {
                            inquiryUnitPrice = aod.getInquiryUnitPrice();
                            demandOrderDetail1.setId(adm.getDemandDetailId());
                            dod = demandOrderDetailDAO.searchDemandOrderDetailById(demandOrderDetail1);
                            assetsBudget.setBudgetYear(dod.getBudgetYear());
                        }
                    }
                    //（申请询问单价-需求询问单价）*申请数量
                    assetsBudget.setUsedSumMoney(inquiryUnitPrice.subtract(dod.getInquiryUnitPrice()).multiply(BigDecimal.valueOf(adm.getDemandCount())));
                    assetsBudget.setProjectCode(dod.getProjectCode());
                    //查询可用预算金额与UsedSumMoney大小关系，若小于UsedSumMoney，说明超出预算，不允许提交
                    AssetsBudget ab = assetsBudgetDAO.getAssetsBudgetbyProjectCodeAndYear(dod.getBudgetYear(), dod.getProjectCode());
                    if (null == ab || null == ab.getAvaliableSumMoney()) {
                        throw new OutBudgetException("项目编码为" + assetsBudget.getProjectCode() + "可用预算不存在！");
                    }
                    BigDecimal avaliableSumMoney = ab.getAvaliableSumMoney();
                    if (avaliableSumMoney.compareTo(assetsBudget.getUsedSumMoney()) == -1) {
                        throw new OutBudgetException("项目编码为" + assetsBudget.getProjectCode() + "超出预算，不能提交！");
                    }
                    assetsBudgetDAO.writeBack(assetsBudget);
                }
            }

        }
        // 回写采购需求信息(释放)		修改前不是草稿
        if ("N".equals(oldApplyOrder.getDraft())) {
            //根据订单 查询修改前的订单资产明细
            ApplyDemandMappingModel applyDemandMappingModel = new ApplyDemandMappingModel();
            applyDemandMappingModel.setApplyId(aOrder.getCdocument());
            List<ApplyDemandMappingModel> applyDemandMappingModels = searchAssetsDetail(applyDemandMappingModel);
            DemandOrderDetailModel demandOrderDetailModel1 = new DemandOrderDetailModel();
            for (ApplyDemandMappingModel adm : applyDemandMappingModels) {
                //adm.setApplyId(applyOrder.getCdocument());
                // 需要回写的采购需求明细 ID和数量
                demandOrderDetailModel1.setId(adm.getDemandDetailId());
                demandOrderDetailModel1.setThisApplyCount(0 - adm.getDemandCount());
                demandOrderDetailDAO.writeBack(demandOrderDetailModel1);
            }
        }
        // 回写采购需求信息(锁定)   修改后不是草稿
        if ("N".equals(applyOrder.getDraft())) {
            //保存修改后的明细  回写
            for (DemandOrderDetailModel dod : demandOrderDetailModel) {
                demandOrderDetailDAO.writeBack(dod);
            }
        }
        //删除订单明细 及 资产明细
        ApplyDemandMapping delADM = new ApplyDemandMapping();
        delADM.setApplyId(applyOrder.getCdocument());
        applyDemandMappingDAO.deleteApplyDemandMappingByApplyId(delADM);
        ApplyOrderDetail delAOD = new ApplyOrderDetail();
        delAOD.setApplyOrderId(applyOrder.getCdocument());
        applyOrderDetailDAO.deleteOrderDetailByApplyId(delAOD);
        // 保存表头信息
        //applyOrderDAO.update(applyOrder);
        // 保存表体信息
        for (ApplyOrderDetail aod : applyOrderDetail) {
            applyOrderDetailDAO.save(aod);
        }
        // 保存资产明细
        for (ApplyDemandMapping adm : applyDemandMapping) {
            applyDemandMappingDAO.save(adm);
        }
        //验证数量是否超出
        for (ApplyDemandMapping adm : applyDemandMapping) {
            DemandOrderDetail demandOrderDetail = new DemandOrderDetail();
            demandOrderDetail.setId(adm.getDemandDetailId());
            DemandOrderDetailModel demandOrderDetailModel1 = demandOrderDetailDAO.searchDemandOrderDetailById(demandOrderDetail);
            if (demandOrderDetailModel1.getNotApplyCount() < 0) {
                ifRollback = true;
                throw new OutBudgetException("资产需求数量超出,保存单据失败！");
            }
        }

        if ("Y".equals(applyOrder.getDraft())) {
            return result;
        }
        WorkFlowStub stub = new WorkFlowStub();

        WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
        WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

        createNextFlowStepParam.setBillCode(applyOrder.getCdocument());
        createNextFlowStepParam.setBillTableName(APPLY_INFO_TABLE);
        createNextFlowStepParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
        createNextFlowStepParam.setDeptCode(applyOrder.getBudgetDepartmentCode());
        createNextFlowStepParam.setDeptName(applyOrder.getBudgetDepartmentName());
        createNextFlowStepParam.setCreatorCode(applyOrder.getCreateByCode());
        createNextFlowStepParam.setCreatorName(applyOrder.getCreateBy());
        Calendar instance = Calendar.getInstance();
        instance.setTime(applyOrder.getApplyDate());
        createNextFlowStepParam.setCreateDate(instance);
        createNextFlowStepParam.setCApprovalId(WorkFlowCommon.generateUUID());
        createNextFlowStepParam.setTypeCode(APPLY_INFO_TYPE_ID);
        createNextFlowStepParam.setIsEmail(applyOrder.getIsMail());
        createNextFlowStepParam.setIsPhoneMessage(applyOrder.getIsPhoneMessage());
        createNextFlowStepParam.setIdea(applyOrder.getIdea());

        // requried
        createNextFlowStepParam.setMoney(applyOrder.getOrderMoney() + "");
        createNextFlowStep.setParam(createNextFlowStepParam);
        createNextFlowStep.setUserCode(applyOrder.getApplyUserCode());
        createNextFlowStep.setUserName(applyOrder.getApplyUser());

        createNextFlowStepParam.setFeeItemCode("");
        createNextFlowStepParam.setCCommitActivityId(applyOrder.getActivityId());
        createNextFlowStepParam.setCCostCenterCode(applyOrder.getCostCenterCode());

        WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);

        if (StringUtils.isNotBlank(response.getErrMessage())) {
            ifRollback = true;
            LOGGER.error("====" + response.getErrMessage() + "====");
            throw new OutBudgetException(response.getErrMessage());
        }
        return result;
    }


    /* @Override
     public ExecuteResult<ApplyOrder> addSaveUpdateApply(final ApplyOrder applyOrder, final List<ApplyOrderDetail> applyOrderDetail,
                                                         final List<ApplyDemandMapping> applyDemandMapping, final List<DemandOrderDetailModel> demandOrderDetailModel, final ApplyOrder oldApplyOrder) {

         final ExecuteResult<ApplyOrder> result = new ExecuteResult<ApplyOrder>();
         return transactionTemplate.execute(new TransactionCallback<ExecuteResult<ApplyOrder>>() {
             @Override
             public ExecuteResult<ApplyOrder> doInTransaction(TransactionStatus transactionStatus) {
                 boolean ifRollback = false;
                 try {
                     // 根据订单ID查询修改前订单
                     ApplyOrder aOrder = new ApplyOrder();
                     aOrder.setCdocument(applyOrder.getCdocument());

                     //当前操作是要提交而不是保存草稿
                     if ("N".equals(applyOrder.getDraft())) {
                         //修改前不是草稿，回写预算（现在的询价单价-之前的询价单价）* 现在的申请数量
                         if ("N".equals(oldApplyOrder.getDraft())) {
                             //首先将所有预算释放
                             //回写预算   张淑龙
                             //根据申请单id，查询mapping表中申请数量，需求行项目编码，需求行单价，申请行中的单价
                             List<DemandApplyBudget> demandApplyBudgets = applyDemandMappingDAO.searchDemandApplyBudget(applyOrder.getCdocument());
                             for (DemandApplyBudget demandApplyBudget : demandApplyBudgets) {
                                 //申请单数量*申请单询价单价
                                 demandApplyBudget.setBudgetPrice(BigDecimal.ZERO.subtract((demandApplyBudget.getApplyPrice().subtract(demandApplyBudget.getDemandPrice())).multiply(BigDecimal.valueOf(demandApplyBudget.getDemandCount()))));
                                 AssetsBudget assetsBudget = new AssetsBudget();
                                 assetsBudget.setBudgetYear(demandApplyBudget.getBudgetYear());
                                 assetsBudget.setUsedSumMoney(demandApplyBudget.getBudgetPrice());
                                 assetsBudget.setProjectCode(demandApplyBudget.getProjectCode());
                                 assetsBudgetDAO.writeBack(assetsBudget);
                             }
                             //查询修改前申请行明细
                             //释放完后，将修改后的预算差额占用
                             for (ApplyDemandMapping adm : applyDemandMapping) {
                                 AssetsBudget assetsBudget = new AssetsBudget();
                                 DemandOrderDetail demandOrderDetail1 = new DemandOrderDetail();
                                 DemandOrderDetailModel dod = new DemandOrderDetailModel();
                                 BigDecimal inquiryUnitPrice = new BigDecimal(0);
                                 for (ApplyOrderDetail aod : applyOrderDetail) {

                                     if (aod.getMergeNumber().equals(adm.getMergeNumber()) && aod.getApplyOrderId().equals(adm.getApplyId())) {
                                         //获得现在的行明细对应的需求单价
                                         inquiryUnitPrice = aod.getInquiryUnitPrice();
                                         demandOrderDetail1.setId(adm.getDemandDetailId());
                                         dod = demandOrderDetailDAO.searchDemandOrderDetailById(demandOrderDetail1);
                                         assetsBudget.setBudgetYear(dod.getBudgetYear());
                                         //申请询价单价*申请数量
                                     }
                                 }
                                         assetsBudget.setUsedSumMoney(inquiryUnitPrice.subtract(dod.getInquiryUnitPrice()).multiply(BigDecimal.valueOf(adm.getDemandCount())));
                                         assetsBudget.setProjectCode(dod.getProjectCode());
                                         //查询可用预算金额与UsedSumMoney大小关系，若小于UsedSumMoney，说明超出预算，不允许提交
                                         BigDecimal avaliableSumMoney = assetsBudgetDAO.searchAssetsBudgetsByProjectCode(assetsBudget).getAvaliableSumMoney();
                                         if (avaliableSumMoney.compareTo(assetsBudget.getUsedSumMoney()) == -1) {
                                             result.addErrorMessage("项目编码为" + assetsBudget.getProjectCode() + "超出预算，不能提交！");
                                             transactionStatus.setRollbackOnly();
                                             return result;
                                         }
                                         assetsBudgetDAO.writeBack(assetsBudget);
                                     }

                         } else { //如果修改前是草稿，修改后提交，则和新增是一样的
                             for (ApplyDemandMapping adm : applyDemandMapping) {
                                 AssetsBudget assetsBudget = new AssetsBudget();
                                 DemandOrderDetail demandOrderDetail1 = new DemandOrderDetail();
                                 DemandOrderDetailModel dod = new DemandOrderDetailModel();
                                 BigDecimal inquiryUnitPrice = new BigDecimal(0);
                                 for (ApplyOrderDetail aod : applyOrderDetail) {
                                     if (aod.getMergeNumber().equals(adm.getMergeNumber())) {
                                         inquiryUnitPrice = aod.getInquiryUnitPrice();
                                         demandOrderDetail1.setId(adm.getDemandDetailId());
                                         dod = demandOrderDetailDAO.searchDemandOrderDetailById(demandOrderDetail1);
                                         assetsBudget.setBudgetYear(dod.getBudgetYear());
                                     }
                                 }
                                         //（申请询问单价-需求询问单价）*申请数量
                                         assetsBudget.setUsedSumMoney(inquiryUnitPrice.subtract(dod.getInquiryUnitPrice()).multiply(BigDecimal.valueOf(adm.getDemandCount())));
                                         assetsBudget.setProjectCode(dod.getProjectCode());
                                         //查询可用预算金额与UsedSumMoney大小关系，若小于UsedSumMoney，说明超出预算，不允许提交
                                         BigDecimal avaliableSumMoney = assetsBudgetDAO.getAssetsBudgetbyProjectCodeAndYear(dod.getBudgetYear(), dod.getProjectCode()).getAvaliableSumMoney();
                                         if (avaliableSumMoney.compareTo(assetsBudget.getUsedSumMoney()) == -1) {
                                             result.addErrorMessage("项目编码为" + assetsBudget.getProjectCode() + "超出预算，不能提交！");
                                             transactionStatus.setRollbackOnly();
                                             return result;
                                         }
                                         assetsBudgetDAO.writeBack(assetsBudget);
                                     }
                                 }

                     }
                     // 回写采购需求信息(释放)		修改前不是草稿
                     if ("N".equals(oldApplyOrder.getDraft())) {
                         //根据订单 查询修改前的订单资产明细
                         ApplyDemandMappingModel applyDemandMappingModel = new ApplyDemandMappingModel();
                         applyDemandMappingModel.setApplyId(aOrder.getCdocument());
                         List<ApplyDemandMappingModel> applyDemandMappingModels = searchAssetsDetail(applyDemandMappingModel);
                         for (ApplyDemandMappingModel adm : applyDemandMappingModels) {
                             //adm.setApplyId(applyOrder.getCdocument());
                             // 需要回写的采购需求明细 ID和数量
                             DemandOrderDetailModel demandOrderDetailModel = new DemandOrderDetailModel();
                             demandOrderDetailModel.setId(adm.getDemandDetailId());
                             demandOrderDetailModel.setThisApplyCount(0 - adm.getDemandCount());
                             demandOrderDetailDAO.writeBack(demandOrderDetailModel);
                         }
                     }
                     // 回写采购需求信息(锁定)   修改后不是草稿
                     if ("N".equals(applyOrder.getDraft())) {
                         //保存修改后的明细  回写
                         for (DemandOrderDetailModel dod : demandOrderDetailModel) {
                             demandOrderDetailDAO.writeBack(dod);
                         }
                     }
                     //删除订单明细 及 资产明细
                     ApplyDemandMapping delADM = new ApplyDemandMapping();
                     delADM.setApplyId(applyOrder.getCdocument());
                     applyDemandMappingDAO.deleteApplyDemandMappingByApplyId(delADM);
                     ApplyOrderDetail delAOD = new ApplyOrderDetail();
                     delAOD.setApplyOrderId(applyOrder.getCdocument());
                     applyOrderDetailDAO.deleteOrderDetailByApplyId(delAOD);
                     // 保存表头信息
                     //applyOrderDAO.update(applyOrder);
                     // 保存表体信息
                     for (ApplyOrderDetail aod : applyOrderDetail) {
                         applyOrderDetailDAO.save(aod);
                     }
                     // 保存资产明细
                     for (ApplyDemandMapping adm : applyDemandMapping) {
                         applyDemandMappingDAO.save(adm);
                     }
                     //验证数量是否超出
                     for (ApplyDemandMapping adm : applyDemandMapping) {
                         DemandOrderDetail demandOrderDetail = new DemandOrderDetail();
                         demandOrderDetail.setId(adm.getDemandDetailId());
                         DemandOrderDetailModel demandOrderDetailModel = demandOrderDetailDAO.searchDemandOrderDetailById(demandOrderDetail);
                         if (demandOrderDetailModel.getNotApplyCount() < 0) {
                             ifRollback = true;
                             result.addErrorMessage("资产需求数量超出,保存单据失败！");
                             break;
                         }
                     }

                 } catch (Exception e) {
                     ifRollback = true;
                     result.addErrorMessage("保存单据失败！");
                     LOGGER.error("采购申请单据保存", e);
                 }
                 if (ifRollback) {
                     transactionStatus.setRollbackOnly();
                     return result;
                 }
                 if ("Y".equals(applyOrder.getDraft())) {
                     return result;
                 }
                 try {
                     WorkFlowStub stub = new WorkFlowStub();

                     WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
                     WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

                     createNextFlowStepParam.setBillCode(applyOrder.getCdocument());
                     createNextFlowStepParam.setBillTableName(APPLY_INFO_TABLE);
                     createNextFlowStepParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
                     createNextFlowStepParam.setDeptCode(applyOrder.getBudgetDepartmentCode());
                     createNextFlowStepParam.setDeptName(applyOrder.getBudgetDepartmentName());
                     createNextFlowStepParam.setCreatorCode(applyOrder.getCreateByCode());
                     createNextFlowStepParam.setCreatorName(applyOrder.getCreateBy());
                     Calendar instance = Calendar.getInstance();
                     instance.setTime(applyOrder.getApplyDate());
                     createNextFlowStepParam.setCreateDate(instance);
                     createNextFlowStepParam.setCApprovalId(WorkFlowCommon.generateUUID());
                     createNextFlowStepParam.setTypeCode(APPLY_INFO_TYPE_ID);
                     createNextFlowStepParam.setIsEmail(applyOrder.getIsMail());
                     createNextFlowStepParam.setIsPhoneMessage(applyOrder.getIsPhoneMessage());
                     createNextFlowStepParam.setIdea(applyOrder.getIdea());

                     // requried
                     createNextFlowStepParam.setMoney(applyOrder.getOrderMoney() + "");
                     createNextFlowStep.setParam(createNextFlowStepParam);
                     createNextFlowStep.setUserCode(applyOrder.getApplyUserCode());
                     createNextFlowStep.setUserName(applyOrder.getApplyUser());

                     createNextFlowStepParam.setFeeItemCode("");
                     createNextFlowStepParam.setCCommitActivityId(applyOrder.getActivityId());
                     createNextFlowStepParam.setCCostCenterCode(applyOrder.getCostCenterCode());

                     WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);

                     if (StringUtils.isNotBlank(response.getErrMessage())) {
                         ifRollback = true;
                         result.addErrorMessage(response.getErrMessage());
                         LOGGER.error("====" + response.getErrMessage() + "====");
                     }

                 } catch (AxisFault axisFault) {
                     ifRollback = true;
                     result.addErrorMessage("调用审批流失败:" + axisFault);
                     LOGGER.error("axisFault", axisFault);
                 } catch (RemoteException e) {
                     ifRollback = true;
                     result.addErrorMessage("调用审批流失败:" + e);
                     LOGGER.error("RemoteException", e);
                 } catch (Exception e) {
                     ifRollback = true;
                     result.addErrorMessage("调用审批流失败:" + e);
                     LOGGER.error("Exception", e);
                 }

                 if (ifRollback) {
                     transactionStatus.setRollbackOnly();
                     return result;
                 }
                 return result;
             }
         });

     }
 */
    @Override
    public int searchNextSequence() {
        return applyOrderDAO.searchNextSequence();
    }

    @Override
    public List<ApplyDemandMappingModel> searchAssetsDetail(ApplyDemandMappingModel applyDemandMappingModel) {
        return applyDemandMappingDAO.searchAssetsDetail(applyDemandMappingModel);
    }

    @Override
    public List<ApplyOrder> exportApplyOrder(ApplyOrderModel applyOrderModel) {
        List<ApplyOrder> applylist = applyOrderDAO.exportApplyOrder(applyOrderModel);
        return applylist;
    }

    @Override
    public ExecuteResult<ApplyOrder> applyComplaint(ApplyOrder applyOrder) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();
        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
            WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

            createNextFlowStepParam.setBillCode(applyOrder.getCdocument());
            createNextFlowStepParam.setBillTableName(APPLY_INFO_TABLE);
            createNextFlowStepParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            createNextFlowStepParam.setDeptCode(applyOrder.getBudgetDepartmentCode());
            createNextFlowStepParam.setDeptName(applyOrder.getBudgetDepartmentName());
            createNextFlowStepParam.setCreatorCode(applyOrder.getCreateByCode());
            createNextFlowStepParam.setCreatorName(applyOrder.getCreateBy());
            Calendar instance = Calendar.getInstance();
            instance.setTime(applyOrder.getApplyDate());
            createNextFlowStepParam.setCreateDate(instance);
            createNextFlowStepParam.setCApprovalId(WorkFlowCommon.generateUUID());
            createNextFlowStepParam.setTypeCode(APPLY_INFO_TYPE_ID);
            createNextFlowStepParam.setIsEmail(applyOrder.getIsMail());
            createNextFlowStepParam.setIsPhoneMessage(applyOrder.getIsPhoneMessage());
            createNextFlowStepParam.setIdea(applyOrder.getIdea());

            // requried
            createNextFlowStepParam.setMoney(applyOrder.getOrderMoney() + "");
            createNextFlowStep.setParam(createNextFlowStepParam);
            createNextFlowStep.setUserCode(applyOrder.getApplyUserCode());
            createNextFlowStep.setUserName(applyOrder.getApplyUser());

            createNextFlowStepParam.setFeeItemCode("");
            createNextFlowStepParam.setCCommitActivityId("");
            createNextFlowStepParam.setCCostCenterCode(applyOrder.getCostCenterCode());

            WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);

            if (StringUtils.isNotBlank(response.getErrMessage())) {
                ifRollback = true;
                executeResult.addErrorMessage(response.getErrMessage());
                LOGGER.error("====" + response.getErrMessage() + "====");
            }

        } catch (AxisFault axisFault) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + axisFault);
            LOGGER.error("axisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("Exception", e);
        }

        if (ifRollback) {
            return executeResult;
        }

        return executeResult;
    }

    @Override
    public void delApplyOrderByCdocument(ApplyOrder applyOrder) {
        applyOrderDAO.delApplyOrderByCdocument(applyOrder);
    }

    @Override
    public ExecuteResult<ApplyOrder> agreeWorkFlow(ApplyOrder applyOrderOnly) {

        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(applyOrderOnly.getCdocument());
            agreeFlowParam.setBillTableName(APPLY_INFO_TABLE);
            agreeFlowParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            agreeFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());
            agreeFlowParam.setDeptCode(applyOrderOnly.getBudgetDepartmentCode());
            agreeFlowParam.setDeptName(applyOrderOnly.getBudgetDepartmentName());
            agreeFlowParam.setCreatorCode(applyOrderOnly.getCreateByCode());
            agreeFlowParam.setCreatorName(applyOrderOnly.getCreateBy());
            if (null == applyOrderOnly.getCreateDate()) {
                executeResult.addErrorMessage("创建日期不能为空");
                return executeResult;
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime(applyOrderOnly.getCreateDate());
            agreeFlowParam.setCreateDate(instance);
            agreeFlowParam.setCUserCode(applyOrderOnly.getCaUserCode());
            agreeFlowParam.setCUserName(applyOrderOnly.getCaUserName());
            agreeFlowParam.setUserDepCode(applyOrderOnly.getUserDepCode());

            agreeFlowParam.setAMoney(applyOrderOnly.getIamoney() + "");
            agreeFlowParam.setTypeCode(APPLY_INFO_TYPE_ID);
            agreeFlowParam.setMoney(applyOrderOnly.getIamoney() + "");
            agreeFlowParam.setIdea(applyOrderOnly.getIdea());
            agreeFlowParam.setIsEmail(applyOrderOnly.getIsMail());
            agreeFlowParam.setIsPhoneMessage(applyOrderOnly.getIsPhoneMessage());
            agreeFlowParam.setCCostCenterCode(applyOrderOnly.getCostCenterCode());
            agreeFlowParam.setCCommitActivityId(applyOrderOnly.getActivityId());

            agree.setUserCode(applyOrderOnly.getCaUserCode());
            agree.setUserName(applyOrderOnly.getCaUserName());
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
            executeResult.addErrorMessage("调用审批流失败:" + axisFault);
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("Exception", e);
        }
        if (ifRollback) {
            return executeResult;
        }
        executeResult.setSuccessMessage("调用同意审批流成功");

        return executeResult;

    }

    @Override
    public ExecuteResult<ApplyOrder> denyWorkFlow(ApplyOrder applyOrder) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();

            backFlowParam.setBillCode(applyOrder.getCdocument());
            backFlowParam.setBillTableName(APPLY_INFO_TABLE);
            backFlowParam.setCreatorCode(applyOrder.getCreateByCode());
            backFlowParam.setCreatorName(applyOrder.getCreateBy());
            backFlowParam.setIdea(applyOrder.getIdea());
            backFlowParam.setCUserCode(applyOrder.getCaUserCode());
            backFlowParam.setCUserName(applyOrder.getCaUserName());
            backFlowParam.setUserDepCode(applyOrder.getUserDepCode());
            backFlowParam.setTypeCode(APPLY_INFO_TYPE_ID);
            backFlowParam.setActivityCode(applyOrder.getActivityId());
            backFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());

            backFlowParam.setIsEmail(applyOrder.getIsMail());
            backFlowParam.setIsPhoneMessage(applyOrder.getIsPhoneMessage());

            back.setParam(backFlowParam);

            back.setUserCode(applyOrder.getCaUserCode());
            back.setUserName(applyOrder.getCaUserName());

            WorkFlowStub.BackResponse backResponse = stub.back(back);
            if (StringUtils.isNotBlank(backResponse.getErrMessage())) {
                ifRollback = true;
                executeResult.addErrorMessage(backResponse.getErrMessage());
                LOGGER.error("====" + backResponse.getErrMessage() + "====");
                return executeResult;
            }
            applyOrderDAO.updateApplyOrderIssapNo(applyOrder);

        } catch (AxisFault axisFault) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + axisFault);
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("Exception", e);
        }
        if (ifRollback) {
            return executeResult;
        }
        executeResult.setSuccessMessage("调用退回审批流成功");

        return executeResult;

    }

    @Override
    public ExecuteResult<ApplyOrder> dropWorkFlow(ApplyOrder applyOrderOnly) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();

            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(applyOrderOnly.getCdocument());
            unApprovalFlowParam.setBillTableName(APPLY_INFO_TABLE);
            unApprovalFlowParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            unApprovalFlowParam.setCreatorCode(applyOrderOnly.getCreateByCode());
            unApprovalFlowParam.setCreatorName(applyOrderOnly.getCreateBy());
            unApprovalFlowParam.setIdea(applyOrderOnly.getIdea());
            unApprovalFlowParam.setCUserCode(applyOrderOnly.getCaUserCode());
            unApprovalFlowParam.setCUserName(applyOrderOnly.getCaUserName());
            unApprovalFlowParam.setUserDepCode(applyOrderOnly.getUserDepCode());
            unApprovalFlowParam.setTypeCode(APPLY_INFO_TYPE_ID);

            unApproval.setParam(unApprovalFlowParam);

            unApproval.setUserCode(applyOrderOnly.getCaUserCode());
            unApproval.setUserName(applyOrderOnly.getCaUserName());

            WorkFlowStub.UnApprovalResponse unApprovalResponse = stub.unApproval(unApproval);

            if (StringUtils.isNotBlank(unApprovalResponse.getErrMessage())) {
                executeResult.addErrorMessage(unApprovalResponse.getErrMessage());
                return executeResult;
            }
            //applyOrderDAO.updateApplyOrderIssapNo(applyOrderOnly);

        } catch (AxisFault axisFault) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + axisFault);
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("RemoteException", e);
        } catch (Exception e) {
            ifRollback = true;
            executeResult.addErrorMessage("调用审批流失败:" + e);
            LOGGER.error("Exception", e);
        }

        if (ifRollback) {
            return executeResult;
        }

        executeResult.setSuccessMessage("调用弃审审批流成功");

        return executeResult;
    }

    @Override
    public ExecuteResult<ApplyOrder> updateApplyOrderDetail(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetails) {

        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();

        for (ApplyOrderDetail applyOrderDetail : applyOrderDetails) {
            applyOrderDetailDAO.updateApplyOrderDetail(applyOrder, applyOrderDetail);

        }

        return executeResult;
    }


    @Override
    public ExecuteResult<ApplyOrder> updateApplyOrder(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetails) {

        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();

        applyOrderDAO.updateApplyOrder(applyOrder);
        for (ApplyOrderDetail applyOrderDetail : applyOrderDetails) {
            applyOrderDetailDAO.updatePurchaseUnitPrice(applyOrderDetail);
        }
        return executeResult;

    }

    @Override
    public List<Department> searchDepartmentByUserCode(ApplyOrderModel applyOrderModel) {
        return applyOrderDAO.searchDepartmentByUserCode(applyOrderModel);
    }

    @Override
    public ExecuteResult<ApplyOrder> deleteApplyOrder(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetails) throws OutBudgetException {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();
        // 回写采购需求信息(释放)		不是草稿
        if ("N".equals(applyOrder.getDraft())) {
            //根据订单 查询订单资产明细
            ApplyDemandMappingModel applyDemandMappingModel = new ApplyDemandMappingModel();
            applyDemandMappingModel.setApplyId(applyOrder.getCdocument());
            List<ApplyDemandMappingModel> applyDemandMappingModels = searchAssetsDetail(applyDemandMappingModel);

            for (ApplyDemandMappingModel adm : applyDemandMappingModels) {
                //adm.setApplyId(applyOrder.getCdocument());
                // 需要回写的采购需求明细 ID和数量
                DemandOrderDetailModel demandOrderDetailModel = new DemandOrderDetailModel();
                demandOrderDetailModel.setId(adm.getDemandDetailId());
                demandOrderDetailModel.setThisApplyCount(0 - adm.getDemandCount());
                demandOrderDetailDAO.writeBack(demandOrderDetailModel);
            }
            List<DemandApplyBudget> demandApplyBudgets = applyDemandMappingDAO.searchDemandApplyBudget(applyOrder.getCdocument());
            BigDecimal price = new BigDecimal(0);
            for (DemandApplyBudget demandApplyBudget : demandApplyBudgets) {
                //申请单数量*（申请单询价单价-需求单询价单价）
                demandApplyBudget.setBudgetPrice((demandApplyBudget.getApplyPrice().subtract(demandApplyBudget.getDemandPrice())).multiply(BigDecimal.valueOf(demandApplyBudget.getDemandCount())));
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setBudgetYear(demandApplyBudget.getBudgetYear());
                assetsBudget.setUsedSumMoney(BigDecimal.ZERO.subtract(demandApplyBudget.getBudgetPrice()));
                //assetsBudget.setUsedSumMoney(demandApplyBudget.getBudgetPrice());
                assetsBudget.setProjectCode(demandApplyBudget.getProjectCode());
                assetsBudgetDAO.writeBack(assetsBudget);
                AssetsBudget searchAssetsBudgetsByProjectCode = assetsBudgetDAO
                        .getAssetsBudgetbyProjectCodeAndYear(assetsBudget.getBudgetYear(), assetsBudget.getProjectCode());
                if (searchAssetsBudgetsByProjectCode == null) {
                    throw new OutBudgetException("项目编码:" + assetsBudget.getProjectCode() + " 无效或已被删除，请修改！");
                }
                if (searchAssetsBudgetsByProjectCode.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) {
                    throw new OutBudgetException("项目编码:" + assetsBudget.getProjectCode() + " 预算超出，不可删除！");
                }
            }
        }

        //删除资产明细
        ApplyDemandMapping delADM = new ApplyDemandMapping();
        delADM.setApplyId(applyOrder.getCdocument());
        List<ApplyDemandMapping> list = applyDemandMappingDAO.searchApplyDemandMappingByApplyId(delADM);
        for (ApplyDemandMapping adm : list) {

            applyDemandMappingDAO.saveDeleteBatch(adm);
        }
        applyDemandMappingDAO.deleteApplyDemandMappingByApplyId(delADM);

        //删除申请单明细
        ApplyOrderDetail delAOD = new ApplyOrderDetail();
        delAOD.setApplyOrderId(applyOrder.getCdocument());
        List<ApplyOrderDetail> list1 = applyOrderDetailDAO.searchApplyOrderDetailByOrderId(applyOrder);
        for (ApplyOrderDetail aod : list1) {

            applyOrderDetailDAO.saveDeleteBatch(aod);
        }
        applyOrderDetailDAO.deleteOrderDetailByApplyId(delAOD);

        //删除采购申请单
        ApplyOrder ao = applyOrderDAO.searchApplyOrderByOrderId(applyOrder);
        applyOrderDAO.saveDelete(ao);
        applyOrderDAO.delApplyOrderByCdocument(applyOrder);
        return executeResult;
    }

    @Override
    public ExecuteResult<ApplyOrder> updateCloseApplyOrder(ApplyOrder applyOrder, List<ApplyOrderDetail> applyOrderDetails) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();
        // 回写采购需求信息(释放)		不是草稿
        if ("N".equals(applyOrder.getDraft())) {
            //根据订单 查询订单资产明细
            ApplyDemandMappingModel applyDemandMappingModel = new ApplyDemandMappingModel();
            applyDemandMappingModel.setApplyId(applyOrder.getCdocument());
            List<ApplyDemandMappingModel> applyDemandMappingModels = searchAssetsDetail(applyDemandMappingModel);

            for (ApplyDemandMappingModel adm : applyDemandMappingModels) {
                //adm.setApplyId(applyOrder.getCdocument());
                // 需要回写的采购需求明细 ID和未下采购订单的数量
                DemandOrderDetailModel demandOrderDetailModel = new DemandOrderDetailModel();
                demandOrderDetailModel.setId(adm.getDemandDetailId());
                demandOrderDetailModel.setThisApplyCount(0 - adm.getDemandCount());
                demandOrderDetailDAO.writeBack(demandOrderDetailModel);
            }

            //根据申请单id，查询mapping表中申请数量，需求行项目编码，需求行单价，申请行中的单价
            List<DemandApplyBudget> demandApplyBudgets = applyDemandMappingDAO.searchDemandApplyBudget(applyOrder.getCdocument());
            for (DemandApplyBudget demandApplyBudget : demandApplyBudgets) {
                //(申请单数量-已下订单数量）*（申请单询价单价-需求单询价单价）
                demandApplyBudget.setBudgetPrice((demandApplyBudget.getApplyPrice().subtract(demandApplyBudget.getDemandPrice())).multiply(BigDecimal.valueOf(demandApplyBudget.getDemandCount() - demandApplyBudget.getApplyCount())));
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setBudgetYear(demandApplyBudget.getBudgetYear());
                assetsBudget.setUsedSumMoney(BigDecimal.ZERO.subtract(demandApplyBudget.getBudgetPrice()));
                assetsBudget.setProjectCode(demandApplyBudget.getProjectCode());
                assetsBudgetDAO.writeBack(assetsBudget);
            }

        }

        //修改采购申请单 单据状态='已关闭'
        applyOrder.setOrderStatus("已关闭");
        applyOrderDAO.updateOrderStatus(applyOrder);
        return executeResult;
    }

    @Override
    public ExecuteResult<ApplyOrder> updateApplyOrderIssap(ApplyOrder applyOrder) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();
        if (null == applyOrder) {

            executeResult.addErrorMessage("采购申请基本信息内容为空");
        }
        applyOrderDAO.updateApplyOrderIssap(applyOrder);
        return executeResult;
    }

    @Override
    public Long searchNextPurchaseCount(ApplyOrder applyOrder) {
        return applyOrderDAO.searchNextPurchaseCount(applyOrder);
    }

    @Override
    public void update(ApplyOrder applyOrder) {
        applyOrderDAO.update(applyOrder);
    }

    @Override
    public ExecuteResult<ApplyOrder> updateSearchCloseDemand(ApplyOrder applyOrder, List<ApplyOrderDetail> aods) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();
        List<DemandOrder> demandOrders = demandOrderDAO.searchDemandByApplyId(applyOrder);
        for (DemandOrder demandOrder : demandOrders) {
            Long searchNotApplyCountByDemandId = demandOrderDAO.searchNotApplyCountByDemandId(demandOrder);
            if (searchNotApplyCountByDemandId == 0) {
                DemandOrder dOrder = new DemandOrder();
                dOrder.setCdocument(demandOrder.getCdocument());
                dOrder.setOrderStatus("已关闭");
                demandOrderDAO.updateOrderStatus(dOrder);
            }
        }
        for (ApplyOrderDetail aod : aods) {
            applyOrderDetailDAO.updatePurAvaMoney(aod);
        }
        return executeResult;
    }

    @Override
    public ExecuteResult<ApplyOrder> updateSearchOpenDemand(ApplyOrder applyOrder) {
        ExecuteResult<ApplyOrder> executeResult = new ExecuteResult<ApplyOrder>();
        List<DemandOrder> demandOrders = demandOrderDAO.searchDemandByApplyId(applyOrder);
        for (DemandOrder demandOrder : demandOrders) {
            Long searchNotApplyCountByDemandId = demandOrderDAO.searchNotApplyCountByDemandId(demandOrder);
            if (searchNotApplyCountByDemandId == 0) { //这里可能存在问题，如果该需求单被手工关闭了（有剩余需求行未被申请时），不会进入此判断条件，后期可能要把判断删掉
                DemandOrder dOrder = new DemandOrder();
                dOrder.setCdocument(demandOrder.getCdocument());
                dOrder.setOrderStatus("未关闭");
                demandOrderDAO.updateOrderStatus(dOrder);
            }
        }
        return executeResult;
    }

    @Override
    public void getMainAssetCodePro(ApplyOrder applyOrder) {
        applyOrderDAO.getMainAssetCodePro(applyOrder);
    }

    @Override
    public void updatePrint(ApplyOrder applyOrder) {
        applyOrderDAO.updatePrint(applyOrder);
    }

    @Override
    public String searchDemandOrderStatus(Long demandDetailId) {
        return applyOrderDAO.searchDemandOrderStatus(demandDetailId);
    }

    @Override
    public String searchIsCrossAssetAccounting(String billCode, String typeId, String roleId, String activityid) {
        return applyOrderDAO.searchIsCrossAssetAccounting(billCode, typeId, roleId, activityid);
    }


}
