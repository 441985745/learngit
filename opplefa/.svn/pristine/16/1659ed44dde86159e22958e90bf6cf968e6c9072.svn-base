package com.opple.fa.purchase.service.impl;

import com.opple.fa.config.dao.AssetsBudgetDAO;
import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.config.entity.Department;
import com.opple.fa.purchase.dao.ApplyDemandMappingDAO;
import com.opple.fa.purchase.dao.ApplyOrderDAO;
import com.opple.fa.purchase.dao.ApplyOrderDetailDAO;
import com.opple.fa.purchase.dao.PaymentTermDAO;
import com.opple.fa.purchase.dao.PurchaseApplyMappingDAO;
import com.opple.fa.purchase.dao.PurchaseOrderDAO;
import com.opple.fa.purchase.dao.PurchaseOrderDetailDAO;
import com.opple.fa.purchase.entity.ApplyDemandMapping;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.OutBudgetException;
import com.opple.fa.purchase.entity.PaymentTerm;
import com.opple.fa.purchase.entity.PurchaseApplyMapping;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.model.ApplyDemandMappingModel;
import com.opple.fa.purchase.model.PurchaseApplyMappingModel;
import com.opple.fa.purchase.model.PurchaseOrderDetailModel;
import com.opple.fa.purchase.model.PurchaseOrderModel;
import com.opple.fa.purchase.service.PurchaseOrderService;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyOrderServiceImpl.class);
    @Getter
    @Setter
    private PurchaseOrderDAO purchaseOrderDAO;
    @Getter
    @Setter
    private PurchaseOrderDetailDAO purchaseOrderDetailDAO;
    @Getter
    @Setter
    private PurchaseApplyMappingDAO purchaseApplyMappingDAO;
    @Getter
    @Setter
    private PaymentTermDAO paymentTermDAO;
    @Getter
    @Setter
    private ApplyDemandMappingDAO applyDemandMappingDAO;
    @Getter
    @Setter
    private ApplyOrderDetailDAO applyOrderDetailDAO;
    @Getter
    @Setter
    private ApplyOrderDAO applyOrderDAO;
    @Getter
    @Setter
    private AssetsBudgetDAO assetsBudgetDAO;

    private final TransactionTemplate transactionTemplate;

    public PurchaseOrderServiceImpl(PlatformTransactionManager transactionManager) {
        Assert.notNull(transactionManager, "The 'transactionManager' argument must not be null.");
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    public Pager<PurchaseOrder> searchPurchaseOrder(Pager<PurchaseOrder> pager, PurchaseOrderModel purchaseOrderModel) {

        List<PurchaseOrder> searchPurchaseOrderList = purchaseOrderDAO.searchPurchaseOrder(pager, purchaseOrderModel);
        Long searchPurchaseOrderCount = purchaseOrderDAO.searchPurchaseOrderCount(purchaseOrderModel);

        return Pager.cloneFromPager(pager, searchPurchaseOrderCount, searchPurchaseOrderList);
    }

    @Override
    public List<PurchaseOrder> searchPurchaseOrderList(PurchaseOrder purchaseOrder) {
        return purchaseOrderDAO.searchPurchaseOrderList(purchaseOrder);
    }

    @Override
    public PurchaseOrder get(PurchaseOrder purchaseOrder) {
        return purchaseOrderDAO.get(purchaseOrder);
    }

    @Override
    public int searchNextSequence() {
        return purchaseOrderDAO.searchNextSequence();
    }

    @Override
    public ExecuteResult<PurchaseOrder> applyComplaint(PurchaseOrder purchaseOrder) {

        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();
        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
            WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

            // -----------
            createNextFlowStepParam.setBillCode(purchaseOrder.getCdocument());
            createNextFlowStepParam.setBillTableName(Env.getProperty("purchase.info.table"));
            createNextFlowStepParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            createNextFlowStepParam.setDeptCode(purchaseOrder.getBudgetDepartmentCode());
            createNextFlowStepParam.setDeptName(purchaseOrder.getBudgetDepartmentName());
            createNextFlowStepParam.setCreatorCode(purchaseOrder.getCreateByCode());
            createNextFlowStepParam.setCreatorName(purchaseOrder.getCreateBy());
            Calendar instance = Calendar.getInstance();
            instance.setTime(purchaseOrder.getApplyDate());
            createNextFlowStepParam.setCreateDate(instance);
            createNextFlowStepParam.setCApprovalId(WorkFlowCommon.generateUUID());
            createNextFlowStepParam.setTypeCode(Env.getProperty("purchase.info.type.id"));
            createNextFlowStepParam.setIsEmail(purchaseOrder.getIsMail());
            createNextFlowStepParam.setIsPhoneMessage(purchaseOrder.getIsPhoneMessage());
            createNextFlowStepParam.setIdea(purchaseOrder.getIdea());

            // requried
            createNextFlowStepParam.setMoney(purchaseOrder.getOrderMoney() + "");
            createNextFlowStep.setParam(createNextFlowStepParam);
            createNextFlowStep.setUserCode(purchaseOrder.getApplyUserCode());
            createNextFlowStep.setUserName(purchaseOrder.getApplyUser());

            createNextFlowStepParam.setFeeItemCode("");
            createNextFlowStepParam.setCCommitActivityId("");
            createNextFlowStepParam.setCCostCenterCode("");

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
    public ExecuteResult<PurchaseOrder> agreeWorkFlow(PurchaseOrder purchaseOrderOnly) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(purchaseOrderOnly.getCdocument());
            agreeFlowParam.setBillTableName(Env.getProperty("purchase.info.table"));
            agreeFlowParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            agreeFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());
            agreeFlowParam.setDeptCode(purchaseOrderOnly.getBudgetDepartmentCode());
            agreeFlowParam.setDeptName(purchaseOrderOnly.getBudgetDepartmentName());
            agreeFlowParam.setCreatorCode(purchaseOrderOnly.getCreateByCode());
            agreeFlowParam.setCreatorName(purchaseOrderOnly.getCreateBy());
            if (null == purchaseOrderOnly.getCreateDate()) {
                executeResult.addErrorMessage("创建日期不能为空");
                return executeResult;
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime(purchaseOrderOnly.getCreateDate());
            agreeFlowParam.setCreateDate(instance);
            agreeFlowParam.setCUserCode(purchaseOrderOnly.getCaUserCode());
            agreeFlowParam.setCUserName(purchaseOrderOnly.getCaUserName());
            agreeFlowParam.setUserDepCode(purchaseOrderOnly.getUserDepCode());

            agreeFlowParam.setAMoney(purchaseOrderOnly.getOrderMoney() + "");
            agreeFlowParam.setTypeCode(Env.getProperty("purchase.info.type.id"));
            agreeFlowParam.setMoney(purchaseOrderOnly.getOrderMoney() + "");
            agreeFlowParam.setIdea(purchaseOrderOnly.getIdea());
            agreeFlowParam.setIsEmail(purchaseOrderOnly.getIsMail());
            agreeFlowParam.setIsPhoneMessage(purchaseOrderOnly.getIsPhoneMessage());
            agreeFlowParam.setCCommitActivityId(purchaseOrderOnly.getActivityId());

            agree.setUserCode(purchaseOrderOnly.getCaUserCode());
            agree.setUserName(purchaseOrderOnly.getCaUserName());
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
    public ExecuteResult<PurchaseOrder> denyWorkFlow(PurchaseOrder purchaseOrder) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();

            backFlowParam.setBillCode(purchaseOrder.getCdocument());
            backFlowParam.setBillTableName(Env.getProperty("purchase.info.table"));
            backFlowParam.setCreatorCode(purchaseOrder.getCreateByCode());
            backFlowParam.setCreatorName(purchaseOrder.getCreateBy());
            backFlowParam.setIdea(purchaseOrder.getIdea());
            backFlowParam.setCUserCode(purchaseOrder.getCaUserCode());
            backFlowParam.setCUserName(purchaseOrder.getCaUserName());
            backFlowParam.setUserDepCode(purchaseOrder.getUserDepCode());
            backFlowParam.setTypeCode(Env.getProperty("purchase.info.type.id"));
            backFlowParam.setActivityCode(purchaseOrder.getActivityId());
            backFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());

            backFlowParam.setIsEmail(purchaseOrder.getIsMail());
            backFlowParam.setIsPhoneMessage(purchaseOrder.getIsPhoneMessage());

            back.setParam(backFlowParam);

            back.setUserCode(purchaseOrder.getCaUserCode());
            back.setUserName(purchaseOrder.getCaUserName());

            WorkFlowStub.BackResponse backResponse = stub.back(back);
            if (StringUtils.isNotBlank(backResponse.getErrMessage())) {
                ifRollback = true;
                executeResult.addErrorMessage(backResponse.getErrMessage());
                LOGGER.error("====" + backResponse.getErrMessage() + "====");
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
        executeResult.setSuccessMessage("调用退回审批流成功");

        return executeResult;

    }

    @Override
    public ExecuteResult<PurchaseOrder> dropWorkFlow(PurchaseOrder purchaseOrderOnly) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();

            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(purchaseOrderOnly.getCdocument());
            unApprovalFlowParam.setBillTableName(Env.getProperty("purchase.info.table"));
            unApprovalFlowParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            unApprovalFlowParam.setCreatorCode(purchaseOrderOnly.getCreateByCode());
            unApprovalFlowParam.setCreatorName(purchaseOrderOnly.getCreateBy());
            unApprovalFlowParam.setIdea(purchaseOrderOnly.getIdea());
            unApprovalFlowParam.setCUserCode(purchaseOrderOnly.getCaUserCode());
            unApprovalFlowParam.setCUserName(purchaseOrderOnly.getCaUserName());
            unApprovalFlowParam.setUserDepCode(purchaseOrderOnly.getUserDepCode());
            unApprovalFlowParam.setTypeCode(Env.getProperty("purchase.info.type.id"));

            unApproval.setParam(unApprovalFlowParam);

            unApproval.setUserCode(purchaseOrderOnly.getCaUserCode());
            unApproval.setUserName(purchaseOrderOnly.getCaUserName());

            WorkFlowStub.UnApprovalResponse unApprovalResponse = stub.unApproval(unApproval);

            if (StringUtils.isNotBlank(unApprovalResponse.getErrMessage())) {
                executeResult.addErrorMessage(unApprovalResponse.getErrMessage());
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

        executeResult.setSuccessMessage("调用弃审审批流成功");

        return executeResult;
    }

    /**
     * 根据订单ID和合并号查询资产明细(采购订单选择明细)
     *
     * @param applyOrderDetail
     * @return
     */
    @Override
    public List<ApplyDemandMappingModel> searchForPurchaseOrder(ApplyOrderDetail applyOrderDetail) {
        return applyDemandMappingDAO.searchForPurchaseOrder(applyOrderDetail);
    }


    @Override
    public ExecuteResult<PurchaseOrder> addSavePurchase(PurchaseOrder purchaseOrder,
                                                        List<PurchaseOrderDetail> purchaseOrderDetails,
                                                        List<PurchaseApplyMapping> purchaseApplyMappings, List<PaymentTerm> paymentTerms) throws OutBudgetException {

        ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        boolean ifRollback = false;
        // 根据订单ID查询该订单是否已存在
        PurchaseOrder pOrder = new PurchaseOrder();
        pOrder.setCdocument(purchaseOrder.getCdocument());
        PurchaseOrder searchPurchaseOrderByOrderId = searchPurchaseOrderByOrderId(pOrder);

        if (searchPurchaseOrderByOrderId != null) {
            throw new OutBudgetException("新增订单失败,订单号已存在!");
        } else {
            // 保存表头信息
            purchaseOrderDAO.save(purchaseOrder);
        }
        return result;

    }


    /**
     * 新增保存订单
     */
   /* @Override
    public ExecuteResult<PurchaseOrder> addSavePurchase(final PurchaseOrder purchaseOrder,
                                                        final List<PurchaseOrderDetail> purchaseOrderDetails,
                                                        final List<PurchaseApplyMapping> purchaseApplyMappings, final List<PaymentTerm> paymentTerms) {
        final ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        return transactionTemplate.execute(new TransactionCallback<ExecuteResult<PurchaseOrder>>() {
            @Override
            public ExecuteResult<PurchaseOrder> doInTransaction(TransactionStatus transactionStatus) {
                boolean ifRollback = false;
                try {
                    // 根据订单ID查询该订单是否已存在
                    PurchaseOrder pOrder = new PurchaseOrder();
                    pOrder.setCdocument(purchaseOrder.getCdocument());
                    PurchaseOrder searchPurchaseOrderByOrderId = searchPurchaseOrderByOrderId(pOrder);

                    if (searchPurchaseOrderByOrderId != null) {
                        result.addErrorMessage("新增订单失败,订单号已存在!");
                    } else {
                        // 保存表头信息
                        purchaseOrderDAO.save(purchaseOrder);
                    }

                } catch (Exception e) {
                    ifRollback = true;
                    result.addErrorMessage("保存单据失败！");
                    LOGGER.error("采购订单保存", e);
                }
                if (ifRollback) {
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
    }
*/
    public ExecuteResult<PurchaseOrder> addSavePurchaseDetail(PurchaseOrder purchaseOrder,
                                                              List<PurchaseOrderDetail> purchaseOrderDetails,
                                                              List<PurchaseApplyMapping> purchaseApplyMappings, List<PaymentTerm> paymentTerms) throws OutBudgetException {
        ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        boolean ifRollback = false;
        // 保存表体信息
        for (PurchaseOrderDetail pod : purchaseOrderDetails) {
            purchaseOrderDetailDAO.save(pod);
        }
        // 保存资产明细
        for (PurchaseApplyMapping pam : purchaseApplyMappings) {
            purchaseApplyMappingDAO.save(pam);
        }
        // 保存付款条件
        for (PaymentTerm pt : paymentTerms) {
            paymentTermDAO.save(pt);
        }
        // 回写--采购申请明细 --采购申请明细的明细 --预算
        if ("N".equals(purchaseOrder.getDraft())) {
            // 保存表体信息 回写数量
            for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                applyOrderDetailDAO.writeBack(pod);
            }
            // 保存资产明细 回写数量
            for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                applyDemandMappingDAO.writeBack(pam);

                // 回写预算
                BigDecimal inquiryUnitPrice = BigDecimal.ZERO;
                BigDecimal purchasePriceLocal = BigDecimal.ZERO;
                BigDecimal purchaseUnitPriceLocal = BigDecimal.ZERO;
                for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                    if (pod.getApplyDetailId().equals(pam.getApplyDetailId())) {
                        // 获得询价单价
                        inquiryUnitPrice = pod.getInquiryUnitPrice();
                        // 获得采购总价
                        purchasePriceLocal = pod.getPurchasePrice();
                        //获得采购单价
                        purchaseUnitPriceLocal = pod.getPurchaseUnitPrice().multiply(purchaseOrder.getExchangeRate());
                    }
                }
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(pam.getProjectCode());
                assetsBudget.setBudgetYear(pam.getBudgetYear());
                assetsBudget.setUsedAmount(0);
                //（订单单价-申请单单价）*订单数量
                assetsBudget.setUsedSumMoney((purchaseUnitPriceLocal
                        .subtract(inquiryUnitPrice)).multiply(new BigDecimal(pam.getApplyCount())));
                assetsBudgetDAO.writeBack(assetsBudget);
            }
        }

        // 验证
        for (PurchaseOrderDetail pod : purchaseOrderDetails) {
            ApplyOrderDetail applyOrderDetail = new ApplyOrderDetail();
            applyOrderDetail.setId(pod.getApplyDetailId());
            ApplyOrderDetail aod = applyOrderDetailDAO.searchApplyOrderDetailById(applyOrderDetail);
            if (aod.getNotApplyCount() < 0) {
                throw new OutBudgetException("采购数量超出,保存单据失败！");
            }
        }
        if (!ifRollback) {
            for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                ApplyDemandMapping applyDemandMapping = new ApplyDemandMapping();
                applyDemandMapping.setId(pam.getApplyDemandId());
                ApplyDemandMapping adm = applyDemandMappingDAO
                        .searchApplyDemandMappingById(applyDemandMapping);
                if (adm.getNotApplyCount() < 0) {
                    ifRollback = true;
                    throw new OutBudgetException("采购数量超出,保存单据失败！");
                }
                if ("N".equals(purchaseOrder.getDraft())) {
                    AssetsBudget assetsBudget = new AssetsBudget();
                    assetsBudget.setProjectCode(pam.getProjectCode());
                    assetsBudget.setBudgetYear(pam.getBudgetYear());
                    AssetsBudget ab = assetsBudgetDAO.getAssetsBudgetbyProjectCodeAndYear(pam.getBudgetYear(), pam.getProjectCode());
                    if (ab == null) {
                        ifRollback = true;
                        throw new OutBudgetException("项目编码:" + assetsBudget.getProjectCode() + " 无效或已被删除，请修改！");
                    }
                    if (ab.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) {
                        ifRollback = true;
                        throw new OutBudgetException("项目编码:" + assetsBudget.getProjectCode() + "预算超出,保存单据失败！");
                    }
                }

            }
        }
        return result;

    }
    /**
     * 新增保存订单
     */
    /*@Override
    public ExecuteResult<PurchaseOrder> addSavePurchaseDetail(final PurchaseOrder purchaseOrder,
                                                              final List<PurchaseOrderDetail> purchaseOrderDetails,
                                                              final List<PurchaseApplyMapping> purchaseApplyMappings, final List<PaymentTerm> paymentTerms) {
        final ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        return transactionTemplate.execute(new TransactionCallback<ExecuteResult<PurchaseOrder>>() {
            @Override
            public ExecuteResult<PurchaseOrder> doInTransaction(TransactionStatus transactionStatus) {
                boolean ifRollback = false;
                try {
                    // 保存表体信息
                    for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                        purchaseOrderDetailDAO.save(pod);
                    }
                    // 保存资产明细
                    for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                        purchaseApplyMappingDAO.save(pam);
                    }
                    // 保存付款条件
                    for (PaymentTerm pt : paymentTerms) {
                        paymentTermDAO.save(pt);
                    }
                    // 回写--采购申请明细 --采购申请明细的明细 --预算
                    if ("N".equals(purchaseOrder.getDraft())) {
                        // 保存表体信息 回写数量
                        for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                            applyOrderDetailDAO.writeBack(pod);
                        }
                        // 保存资产明细 回写数量
                        for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                            applyDemandMappingDAO.writeBack(pam);

                            // 回写预算
                            BigDecimal inquiryUnitPrice = BigDecimal.ZERO;
                            BigDecimal purchasePriceLocal = BigDecimal.ZERO;
                            BigDecimal purchaseUnitPriceLocal = BigDecimal.ZERO;
                            for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                                if (pod.getApplyDetailId().equals(pam.getApplyDetailId())) {
                                    // 获得询价单价
                                    inquiryUnitPrice = pod.getInquiryUnitPrice();
                                    // 获得采购总价
                                    purchasePriceLocal = pod.getPurchasePrice();
                                    //获得采购单价
                                    purchaseUnitPriceLocal = pod.getPurchaseUnitPrice().multiply(purchaseOrder.getExchangeRate());
                                }
                            }
                            AssetsBudget assetsBudget = new AssetsBudget();
                            assetsBudget.setProjectCode(pam.getProjectCode());
                            assetsBudget.setBudgetYear(pam.getBudgetYear());
                            assetsBudget.setUsedAmount(0);
                            //（订单单价-申请单单价）*订单数量
                            assetsBudget.setUsedSumMoney((purchaseUnitPriceLocal
                                    .subtract(inquiryUnitPrice)).multiply(new BigDecimal(pam.getApplyCount())));
                            assetsBudgetDAO.writeBack(assetsBudget);
                        }
                    }

                    // 验证
                    for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                        ApplyOrderDetail applyOrderDetail = new ApplyOrderDetail();
                        applyOrderDetail.setId(pod.getApplyDetailId());
                        ApplyOrderDetail aod = applyOrderDetailDAO.searchApplyOrderDetailById(applyOrderDetail);
                        if (aod.getNotApplyCount() < 0) {
                            ifRollback = true;
                            result.addErrorMessage("采购数量超出,保存单据失败！");
                            break;
                        }
                    }
                    if (ifRollback) {
                        transactionStatus.setRollbackOnly();
                    }
                    if (!ifRollback) {
                        for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                            ApplyDemandMapping applyDemandMapping = new ApplyDemandMapping();
                            applyDemandMapping.setId(pam.getApplyDemandId());
                            ApplyDemandMapping adm = applyDemandMappingDAO
                                    .searchApplyDemandMappingById(applyDemandMapping);
                            if (adm.getNotApplyCount() < 0) {
                                ifRollback = true;
                                result.addErrorMessage("采购数量超出,保存单据失败！");
                                break;
                            }
                            AssetsBudget assetsBudget = new AssetsBudget();
                            assetsBudget.setProjectCode(pam.getProjectCode());
                            assetsBudget.setBudgetYear(pam.getBudgetYear());
                            AssetsBudget ab = assetsBudgetDAO.getAssetsBudgetbyProjectCodeAndYear(pam.getBudgetYear(), pam.getProjectCode());
                            if (ab == null) {
                                ifRollback = true;
                                result.addErrorMessage("项目编码:" + assetsBudget.getProjectCode() + " 无效或已被删除，请修改！");
                                break;
                            }
                            if (ab.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) {
                                ifRollback = true;
                                result.addErrorMessage("项目编码:" + assetsBudget.getProjectCode() + "预算超出,保存单据失败！");
                                break;
                            }
                        }
                    }
                    *//*if (ifRollback) {
                        transactionStatus.setRollbackOnly();
                    }
*//*
                } catch (Exception e) {
                    ifRollback = true;
                    result.addErrorMessage("保存单据明细失败！");
                    LOGGER.error("采购订单明细保存", e);
                }
                if (ifRollback) {
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
    }
*/

    /**
     * 修改保存订单
     */
    @Override
    public ExecuteResult<PurchaseOrder> addUpdatePurchase(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchaseOrderDetails,
                                                          List<PurchaseApplyMapping> purchaseApplyMappings, List<PaymentTerm> paymentTerms, PurchaseOrder oldPurchaseOrder) throws OutBudgetException, RemoteException {
        ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        boolean ifRollback = false;
        // 根据订单ID查询修改前订单
        PurchaseOrder pOrder = new PurchaseOrder();
        pOrder.setCdocument(purchaseOrder.getCdocument());

        List<PurchaseOrderDetailModel> oldPurchaseOrderDetailModels = purchaseOrderDetailDAO.searchPurchaseOrderDetailByOrderId(pOrder);
        List<PurchaseApplyMappingModel> oldPurchaseApplyMappingModels = purchaseApplyMappingDAO.searchPurchaseApplyMappingByOrderId(pOrder);
        List<PaymentTerm> oldPaymentTerms = paymentTermDAO.searchPaymentTermByOrderId(pOrder);
        // 回写采购申请信息(释放) 修改前不是草稿
        if ("N".equals(oldPurchaseOrder.getDraft())) {
            // 保存表体信息 回写数量(释放)
            for (PurchaseOrderDetail pod : oldPurchaseOrderDetailModels) {
                pod.setApplyCount(0 - pod.getApplyCount());
                applyOrderDetailDAO.writeBack(pod);
            }
            // 保存资产明细 回写数量(释放)
            for (PurchaseApplyMapping pam : oldPurchaseApplyMappingModels) {
                pam.setApplyCount(0 - pam.getApplyCount());
                applyDemandMappingDAO.writeBack(pam);
                // 回写预算(释放)
                BigDecimal inquiryUnitPrice = BigDecimal.ZERO;
                BigDecimal purchasePriceLocal = BigDecimal.ZERO;
                BigDecimal purchaseUnitPrice = BigDecimal.ZERO;
                for (PurchaseOrderDetail pod : oldPurchaseOrderDetailModels) {
                    if (pod.getApplyDetailId().equals(pam.getApplyDetailId())) {
                        // 获得询价单价
                        inquiryUnitPrice = pod.getInquiryUnitPrice();
                        //获得订单单价
                        if (purchaseOrder.getExchangeRate() == null) {
                            throw new OutBudgetException("汇率为空");
                        }
                        purchaseUnitPrice = pod.getPurchaseUnitPrice().multiply(purchaseOrder.getExchangeRate());
                        // 获得采购总价(本币)
                        purchasePriceLocal = pod.getPurchasePriceLocal();
                        break;
                    }
                }
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(pam.getProjectCode());
                assetsBudget.setBudgetYear(pam.getBudgetYear());
                assetsBudget.setUsedAmount(0);
                // 此处不用0减，因为数量是负数
                assetsBudget.setUsedSumMoney(purchaseUnitPrice.subtract(inquiryUnitPrice).multiply(new
                        BigDecimal(pam.getApplyCount())));
                //assetsBudget.setUsedSumMoney(BigDecimal.ZERO.subtract(purchasePriceLocal));
                assetsBudgetDAO.writeBack(assetsBudget);
            }
        }
        // 删除数据
        paymentTermDAO.deletePaymentTermByPurchaseId(pOrder);
        purchaseApplyMappingDAO.deletePurchaseApplyMappingByPurchaseId(pOrder);
        purchaseOrderDetailDAO.deletePurchaseOrderDetailByPurchaseId(pOrder);
        // purchaseOrderDAO.deletePurchaseOrderByPurchaseId(pOrder);
        // 保存表头信息
        // purchaseOrderDAO.update(purchaseOrder);
        // 保存表体信息
        for (PurchaseOrderDetail pod : purchaseOrderDetails) {
            purchaseOrderDetailDAO.save(pod);
        }
        // 保存资产明细
        for (PurchaseApplyMapping pam : purchaseApplyMappings) {
            purchaseApplyMappingDAO.save(pam);
        }
        // 保存付款条件
        for (PaymentTerm pt : paymentTerms) {
            paymentTermDAO.save(pt);
        }
        // 回写--采购申请明细 --采购申请明细的明细 --预算
        if ("N".equals(purchaseOrder.getDraft())) {
            // 保存表体信息 回写数量
            for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                applyOrderDetailDAO.writeBack(pod);
            }
            // 保存资产明细 回写数量
            for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                applyDemandMappingDAO.writeBack(pam);
                // 回写预算
                BigDecimal inquiryUnitPrice = BigDecimal.ZERO;
                BigDecimal purchasePriceLocal = BigDecimal.ZERO;
                BigDecimal purchaseUnitPrice = BigDecimal.ZERO;
                for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                    if (pod.getApplyDetailId().equals(pam.getApplyDetailId())) {
                        // 获得询价单价
                        inquiryUnitPrice = pod.getInquiryUnitPrice();
                        //获得订单单价
                        purchaseUnitPrice = pod.getPurchaseUnitPrice().multiply(purchaseOrder.getExchangeRate());
                        // 获得采购总价(本币)
                        purchasePriceLocal = pod.getPurchasePriceLocal();
                    }
                }
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(pam.getProjectCode());
                assetsBudget.setBudgetYear(pam.getBudgetYear());
                assetsBudget.setUsedAmount(0);
                // 采购总价(本币) - (询价单价*采购数量)
                //修改前是草稿
                if ("Y".equals(oldPurchaseOrder.getDraft())) {
                    assetsBudget.setUsedSumMoney(purchaseUnitPrice.subtract(inquiryUnitPrice).multiply(new BigDecimal(pam.getApplyCount())));
                } else {  //修改前不是草稿
                    // 采购总价(本币)
                    assetsBudget.setUsedSumMoney(purchaseUnitPrice.subtract(inquiryUnitPrice).multiply(new BigDecimal(pam.getApplyCount())));
                }
                assetsBudgetDAO.writeBack(assetsBudget);
            }
        }
        // 验证
        for (PurchaseOrderDetail pod : purchaseOrderDetails) {
            ApplyOrderDetail applyOrderDetail = new ApplyOrderDetail();
            applyOrderDetail.setId(pod.getApplyDetailId());
            ApplyOrderDetail aod = applyOrderDetailDAO.searchApplyOrderDetailById(applyOrderDetail);
            if (aod.getNotApplyCount() < 0) {
                ifRollback = true;
                throw new OutBudgetException("采购数量超出,保存单据失败！");
            }
        }
        if (!ifRollback) {
            for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                ApplyDemandMapping applyDemandMapping = new ApplyDemandMapping();
                applyDemandMapping.setId(pam.getApplyDemandId());
                ApplyDemandMapping adm = applyDemandMappingDAO
                        .searchApplyDemandMappingById(applyDemandMapping);
                if (adm.getNotApplyCount() < 0) {
                    ifRollback = true;
                    throw new OutBudgetException("采购数量超出,保存单据失败！");
                }
                if ("N".equals(purchaseOrder.getDraft())) {
                    AssetsBudget assetsBudget = new AssetsBudget();
                    assetsBudget.setProjectCode(pam.getProjectCode());
                    assetsBudget.setBudgetYear(pam.getBudgetYear());
                    AssetsBudget ab = assetsBudgetDAO.getAssetsBudgetbyProjectCodeAndYear(pam.getBudgetYear(), pam.getProjectCode());
                    if (ab == null) {
                        ifRollback = true;
                        throw new OutBudgetException("项目编码:" + assetsBudget.getProjectCode() + " 无效或已被删除，请修改！");
                    }
                    if (ab.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) {
                        ifRollback = true;
                        throw new OutBudgetException("项目编码:" + assetsBudget.getProjectCode() + "预算超出,保存单据失败！");
                    }
                }

            }
        }
        if ("Y".equals(purchaseOrder.getDraft())) {
            return result;
        }
        WorkFlowStub stub = new WorkFlowStub();

        WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
        WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

        createNextFlowStepParam.setBillCode(purchaseOrder.getCdocument());
        createNextFlowStepParam.setBillTableName(Env.getProperty("purchase.info.table"));
        createNextFlowStepParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
        createNextFlowStepParam.setDeptCode(purchaseOrder.getBudgetDepartmentCode());
        createNextFlowStepParam.setDeptName(purchaseOrder.getBudgetDepartmentName());
        createNextFlowStepParam.setCreatorCode(purchaseOrder.getCreateByCode());
        createNextFlowStepParam.setCreatorName(purchaseOrder.getCreateBy());
        Calendar instance = Calendar.getInstance();
        instance.setTime(purchaseOrder.getApplyDate());
        createNextFlowStepParam.setCreateDate(instance);
        createNextFlowStepParam.setCApprovalId(WorkFlowCommon.generateUUID());
        createNextFlowStepParam.setTypeCode(Env.getProperty("purchase.info.type.id"));
        createNextFlowStepParam.setIsEmail(purchaseOrder.getIsMail());
        createNextFlowStepParam.setIsPhoneMessage(purchaseOrder.getIsPhoneMessage());
        createNextFlowStepParam.setIdea(purchaseOrder.getIdea());

        createNextFlowStepParam.setMoney(purchaseOrder.getOrderMoney() + "");
        createNextFlowStep.setParam(createNextFlowStepParam);
        createNextFlowStep.setUserCode(purchaseOrder.getApplyUserCode());
        createNextFlowStep.setUserName(purchaseOrder.getApplyUser());

        createNextFlowStepParam.setFeeItemCode("");
        createNextFlowStepParam.setCCommitActivityId(purchaseOrder.getActivityId());
        createNextFlowStepParam.setCCostCenterCode("");

        WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);

        if (StringUtils.isNotBlank(response.getErrMessage())) {
            ifRollback = true;
            result.addErrorMessage(response.getErrMessage());
            LOGGER.error("====" + response.getErrMessage() + "====");
        }
        return result;

    }


    /**
     * 修改保存订单
     */
    /*@Override
    public ExecuteResult<PurchaseOrder> addUpdatePurchase(final PurchaseOrder purchaseOrder, final List<PurchaseOrderDetail> purchaseOrderDetails, final List<PurchaseApplyMapping> purchaseApplyMappings, final List<PaymentTerm> paymentTerms, final PurchaseOrder oldPurchaseOrder) {
        final ExecuteResult<PurchaseOrder> result = new ExecuteResult<PurchaseOrder>();
        return transactionTemplate.execute(new TransactionCallback<ExecuteResult<PurchaseOrder>>() {
            @Override
            public ExecuteResult<PurchaseOrder> doInTransaction(TransactionStatus transactionStatus) {
                boolean ifRollback = false;
                try { // 根据订单ID查询修改前订单
                    PurchaseOrder pOrder = new PurchaseOrder();
                    pOrder.setCdocument(purchaseOrder.getCdocument());

                    List<PurchaseOrderDetailModel> oldPurchaseOrderDetailModels = purchaseOrderDetailDAO.searchPurchaseOrderDetailByOrderId(pOrder);
                    List<PurchaseApplyMappingModel> oldPurchaseApplyMappingModels = purchaseApplyMappingDAO.searchPurchaseApplyMappingByOrderId(pOrder);
                    List<PaymentTerm> oldPaymentTerms = paymentTermDAO.searchPaymentTermByOrderId(pOrder);
                    // 回写采购申请信息(释放) 修改前不是草稿
                    if ("N".equals(oldPurchaseOrder.getDraft())) {
                        // 保存表体信息 回写数量(释放)
                        for (PurchaseOrderDetail pod : oldPurchaseOrderDetailModels) {
                            pod.setApplyCount(0 - pod.getApplyCount());
                            applyOrderDetailDAO.writeBack(pod);
                        }
                        // 保存资产明细 回写数量(释放)
                        for (PurchaseApplyMapping pam : oldPurchaseApplyMappingModels) {
                            pam.setApplyCount(0 - pam.getApplyCount());
                            applyDemandMappingDAO.writeBack(pam);
                            // 回写预算(释放)
                            BigDecimal inquiryUnitPrice = BigDecimal.ZERO;
                            BigDecimal purchasePriceLocal = BigDecimal.ZERO;
                            BigDecimal purchaseUnitPrice = BigDecimal.ZERO;
                            for (PurchaseOrderDetail pod : oldPurchaseOrderDetailModels) {
                                if (pod.getApplyDetailId().equals(pam.getApplyDetailId())) {
                                    // 获得询价单价
                                    inquiryUnitPrice = pod.getInquiryUnitPrice();
                                    //获得订单单价
                                    purchaseUnitPrice = pod.getPurchaseUnitPrice().multiply(purchaseOrder.getExchangeRate());
                                    // 获得采购总价(本币)
                                    purchasePriceLocal = pod.getPurchasePriceLocal();
                                    break;
                                }
                            }
                            AssetsBudget assetsBudget = new AssetsBudget();
                            assetsBudget.setProjectCode(pam.getProjectCode());
                            assetsBudget.setBudgetYear(pam.getBudgetYear());
                            assetsBudget.setUsedAmount(0);
                            // 此处不用0减，因为数量是负数
                            assetsBudget.setUsedSumMoney(purchaseUnitPrice.subtract(inquiryUnitPrice).multiply(new
                                    BigDecimal(pam.getApplyCount())));
                            //assetsBudget.setUsedSumMoney(BigDecimal.ZERO.subtract(purchasePriceLocal));
                            assetsBudgetDAO.writeBack(assetsBudget);
                        }
                    }
                    // 删除数据
                    paymentTermDAO.deletePaymentTermByPurchaseId(pOrder);
                    purchaseApplyMappingDAO.deletePurchaseApplyMappingByPurchaseId(pOrder);
                    purchaseOrderDetailDAO.deletePurchaseOrderDetailByPurchaseId(pOrder);
                    // purchaseOrderDAO.deletePurchaseOrderByPurchaseId(pOrder);
                    // 保存表头信息
                    // purchaseOrderDAO.update(purchaseOrder);
                    // 保存表体信息
                    for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                        purchaseOrderDetailDAO.save(pod);
                    }
                    // 保存资产明细
                    for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                        purchaseApplyMappingDAO.save(pam);
                    }
                    // 保存付款条件
                    for (PaymentTerm pt : paymentTerms) {
                        paymentTermDAO.save(pt);
                    }
                    // 回写--采购申请明细 --采购申请明细的明细 --预算
                    if ("N".equals(purchaseOrder.getDraft())) {
                        // 保存表体信息 回写数量
                        for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                            applyOrderDetailDAO.writeBack(pod);
                        }
                        // 保存资产明细 回写数量
                        for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                            applyDemandMappingDAO.writeBack(pam);
                            // 回写预算
                            BigDecimal inquiryUnitPrice = BigDecimal.ZERO;
                            BigDecimal purchasePriceLocal = BigDecimal.ZERO;
                            BigDecimal purchaseUnitPrice = BigDecimal.ZERO;
                            for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                                if (pod.getApplyDetailId().equals(pam.getApplyDetailId())) {
                                    // 获得询价单价
                                    inquiryUnitPrice = pod.getInquiryUnitPrice();
                                    //获得订单单价
                                    purchaseUnitPrice = pod.getPurchaseUnitPrice().multiply(purchaseOrder.getExchangeRate());
                                    // 获得采购总价(本币)
                                    purchasePriceLocal = pod.getPurchasePriceLocal();
                                }
                            }
                            AssetsBudget assetsBudget = new AssetsBudget();
                            assetsBudget.setProjectCode(pam.getProjectCode());
                            assetsBudget.setBudgetYear(pam.getBudgetYear());
                            assetsBudget.setUsedAmount(0);
                            // 采购总价(本币) - (询价单价*采购数量)
                            //修改前是草稿
                            if ("Y".equals(oldPurchaseOrder.getDraft())) {
                                assetsBudget.setUsedSumMoney((purchaseUnitPrice.subtract(inquiryUnitPrice).multiply(new BigDecimal(pam.getApplyCount()))));
                            } else {  //修改前不是草稿
                                // 采购总价(本币)
                                assetsBudget.setUsedSumMoney((purchaseUnitPrice.subtract(inquiryUnitPrice).multiply(new BigDecimal(pam.getApplyCount()))));
                            }
                            assetsBudgetDAO.writeBack(assetsBudget);
                        }
                    }
                    // 验证
                    for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                        ApplyOrderDetail applyOrderDetail = new ApplyOrderDetail();
                        applyOrderDetail.setId(pod.getApplyDetailId());
                        ApplyOrderDetail aod = applyOrderDetailDAO.searchApplyOrderDetailById(applyOrderDetail);
                        if (aod.getNotApplyCount() < 0) {
                            ifRollback = true;
                            result.addErrorMessage("采购数量超出,保存单据失败！");
                            break;
                        }
                    }
                    if (!ifRollback) {
                        for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                            ApplyDemandMapping applyDemandMapping = new ApplyDemandMapping();
                            applyDemandMapping.setId(pam.getApplyDemandId());
                            ApplyDemandMapping adm = applyDemandMappingDAO
                                    .searchApplyDemandMappingById(applyDemandMapping);
                            if (adm.getNotApplyCount() < 0) {
                                ifRollback = true;
                                result.addErrorMessage("采购数量超出,保存单据失败！");
                                break;
                            }
                            AssetsBudget assetsBudget = new AssetsBudget();
                            assetsBudget.setProjectCode(pam.getProjectCode());
                            assetsBudget.setBudgetYear(pam.getBudgetYear());
                            AssetsBudget ab = assetsBudgetDAO.getAssetsBudgetbyProjectCodeAndYear(pam.getBudgetYear(), pam.getProjectCode());
                            if (ab == null) {
                                ifRollback = true;
                                result.addErrorMessage("项目编码:" + assetsBudget.getProjectCode() + " 无效或已被删除，请修改！");
                                break;
                            }
                            if (ab.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) {
                                ifRollback = true;
                                result.addErrorMessage("预算超出,保存单据失败！");
                                break;
                            }
                        }
                    }
                    if (ifRollback) {
                        transactionStatus.setRollbackOnly();
                    }
                } catch (Exception e) {
                    ifRollback = true;
                    result.addErrorMessage("修改单据失败！");
                    LOGGER.error("采购订单修改", e);
                }
                if (ifRollback) {
                    transactionStatus.setRollbackOnly();
                    return result;
                }
                if ("Y".equals(purchaseOrder.getDraft())) {
                    return result;
                }
                try {
                    WorkFlowStub stub = new WorkFlowStub();

                    WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
                    WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

                    createNextFlowStepParam.setBillCode(purchaseOrder.getCdocument());
                    createNextFlowStepParam.setBillTableName(Env.getProperty("purchase.info.table"));
                    createNextFlowStepParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
                    createNextFlowStepParam.setDeptCode(purchaseOrder.getBudgetDepartmentCode());
                    createNextFlowStepParam.setDeptName(purchaseOrder.getBudgetDepartmentName());
                    createNextFlowStepParam.setCreatorCode(purchaseOrder.getCreateByCode());
                    createNextFlowStepParam.setCreatorName(purchaseOrder.getCreateBy());
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(purchaseOrder.getApplyDate());
                    createNextFlowStepParam.setCreateDate(instance);
                    createNextFlowStepParam.setCApprovalId(WorkFlowCommon.generateUUID());
                    createNextFlowStepParam.setTypeCode(Env.getProperty("purchase.info.type.id"));
                    createNextFlowStepParam.setIsEmail(purchaseOrder.getIsMail());
                    createNextFlowStepParam.setIsPhoneMessage(purchaseOrder.getIsPhoneMessage());
                    createNextFlowStepParam.setIdea(purchaseOrder.getIdea());

                    createNextFlowStepParam.setMoney(purchaseOrder.getOrderMoney() + "");
                    createNextFlowStep.setParam(createNextFlowStepParam);
                    createNextFlowStep.setUserCode(purchaseOrder.getApplyUserCode());
                    createNextFlowStep.setUserName(purchaseOrder.getApplyUser());

                    createNextFlowStepParam.setFeeItemCode("");
                    createNextFlowStepParam.setCCommitActivityId(purchaseOrder.getActivityId());
                    createNextFlowStepParam.setCCostCenterCode("");

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
                    return result;
                }
                return result;
            }
        });
    }
*/
    @Override
    public void update(PurchaseOrder purchaseOrder) {
        purchaseOrderDAO.update(purchaseOrder);
    }

    @Override
    public PurchaseOrder searchPurchaseOrderByOrderId(PurchaseOrder purchaseOrder) {
        return purchaseOrderDAO.searchPurchaseOrderByOrderId(purchaseOrder);
    }

    @Override
    public PurchaseOrder searchPurchaseOrderByOrderIdOnly(PurchaseOrder purchaseOrder) {
        return purchaseOrderDAO.searchPurchaseOrderByOrderId(purchaseOrder);
    }

    @Override
    public List<PurchaseApplyMappingModel> searchPurchaseApplyMappingByOrderId(PurchaseOrder purchaseOrder) {
        return purchaseApplyMappingDAO.searchPurchaseApplyMappingByOrderId(purchaseOrder);
    }

    @Override
    public ExecuteResult<PurchaseOrder> deletePurchaseOrderByPurchaseId(PurchaseOrder purchaseOrderOnly) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();
        purchaseOrderDAO.deletePurchaseOrderByPurchaseId(purchaseOrderOnly);
        return executeResult;
    }

    @Override
    public List<Department> searchDepartmentByUserCode(PurchaseOrderModel purchaseOrder) {
        return purchaseOrderDAO.searchDepartmentByUserCode(purchaseOrder);
    }

    @Override
    public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderDAO.updatePurchaseOrder(purchaseOrder);
    }

    @Override
    public ExecuteResult<PurchaseOrder> deletePurchaseOrder(PurchaseOrder purchaseOrder,
                                                            List<PurchaseOrderDetail> purchaseOrderDetails, List<PurchaseApplyMappingModel> purchaseApplyMappings) throws OutBudgetException {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();
        // 回写采购申请信息(释放) 修改前不是草稿
        if ("N".equals(purchaseOrder.getDraft())) {

            // 表体信息 回写数量(释放)
            ApplyOrder ao = new ApplyOrder();
            for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                pod.setApplyCount(0 - pod.getApplyCount()); //以下订单数量
                ao.setCdocument(pod.getApplyOrderId());
                ao.setOrderStatus("未关闭");
                applyOrderDetailDAO.writeBack(pod);
                //德邦确定：只要订单删除，对应的申请单状态就改为未关闭
                applyOrderDAO.updateOrderStatus(ao);
            }
            // 资产明细 回写数量(释放)
            for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                //为了会写数量
                pam.setApplyCount(0 - pam.getApplyCount());
                applyDemandMappingDAO.writeBack(pam);
                //改回来，为了后边计算预算释放
                pam.setApplyCount(0 - pam.getApplyCount());
                // 回写预算(释放)-------待确定，应该是不需要释放预算，因为在需求占过预算了！！！！！！！！！！！！
                BigDecimal inquiryUnitPrice = BigDecimal.ZERO;
//                BigDecimal purchasePriceLocal = BigDecimal.ZERO;
                BigDecimal purchaseUnitPrice = BigDecimal.ZERO;
                for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                    if (pod.getApplyDetailId().equals(pam.getApplyDetailId())) {
                        // 获得询价单价
                        inquiryUnitPrice = pod.getInquiryUnitPrice();
                        // 获得采购总价(本币)
//                        purchasePriceLocal = pod.getPurchasePriceLocal();
                        purchaseUnitPrice = pod.getPurchaseUnitPrice();  //采购单价

                        break;
                    }
                }
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(pam.getProjectCode());
                assetsBudget.setBudgetYear(pam.getBudgetYear());
                assetsBudget.setUsedAmount(0);
                //0-(采购单价-询价单价)*订单数量，将这个差价回写
                assetsBudget.setUsedSumMoney(BigDecimal.ZERO.subtract(purchaseUnitPrice.subtract(inquiryUnitPrice).multiply(BigDecimal.valueOf(pam.getApplyCount()))));
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
        // 删除数据
        List<PaymentTerm> pts = paymentTermDAO.searchPaymentTermByOrderId1(purchaseOrder);
        for (PaymentTerm pt : pts) {
            paymentTermDAO.saveDeleteBranch(pt);
        }
        paymentTermDAO.deletePaymentTermByPurchaseId(purchaseOrder);
        List<PurchaseApplyMappingModel> pam = purchaseApplyMappingDAO.searchPurchaseApplyMappingByOrderId1(purchaseOrder);
        for (PurchaseApplyMappingModel pam1 : pam) {

            purchaseApplyMappingDAO.saveDeleteBranch(pam1);
        }
        purchaseApplyMappingDAO.deletePurchaseApplyMappingByPurchaseId(purchaseOrder);
        List<PurchaseOrderDetail> list = purchaseOrderDetailDAO.searchPurchaseOrderDetailByPid(purchaseOrder);
        for (PurchaseOrderDetail pod : list) {

            purchaseOrderDetailDAO.saveDeleteBranch(pod);
        }
        purchaseOrderDetailDAO.deletePurchaseOrderDetailByPurchaseId(purchaseOrder);
        PurchaseOrder po = purchaseOrderDAO.searchPurchaseOrderByOrderIdOnly(purchaseOrder);
        purchaseOrderDAO.saveDelete(po);
        purchaseOrderDAO.deletePurchaseOrderByPurchaseId(purchaseOrder);
        return executeResult;
    }

    @Override
    public ExecuteResult<PurchaseOrder> updateClosePurchaseOrder(PurchaseOrder purchaseOrder,
                                                                 List<PurchaseOrderDetail> purchaseOrderDetails, List<PurchaseApplyMappingModel> purchaseApplyMappings) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();
        // 回写采购申请信息(释放) 修改前不是草稿
        /*
         * if ("N".equals(purchaseOrder.getDraft())) {
		 * 
		 * // 表体信息 回写数量(释放) for (PurchaseOrderDetail pod : purchaseOrderDetails)
		 * { pod.setApplyCount(0 - pod.getApplyCount());
		 * applyOrderDetailDAO.writeBack(pod); } // 资产明细 回写数量(释放) for
		 * (PurchaseApplyMapping pam : purchaseApplyMappings) {
		 * pam.setApplyCount(0 - pam.getApplyCount());
		 * applyDemandMappingDAO.writeBack(pam);
		 * 
		 * //回写预算(释放) BigDecimal inquiryUnitPrice = BigDecimal.ZERO; BigDecimal
		 * purchasePriceLocal = BigDecimal.ZERO; for (PurchaseOrderDetail pod :
		 * purchaseOrderDetails) { if
		 * (pod.getApplyDetailId().equals(pam.getApplyDetailId())) { //获得询价单价
		 * inquiryUnitPrice = pod.getInquiryUnitPrice(); //获得采购总价(本币)
		 * purchasePriceLocal = pod.getPurchasePriceLocal(); break; } }
		 * AssetsBudget assetsBudget = new AssetsBudget();
		 * assetsBudget.setProjectCode(pam.getProjectCode());
		 * assetsBudget.setUsedAmount(0); // 0-(采购总价(本币))
		 * //assetsBudget.setUsedSumMoney(BigDecimal.ZERO.subtract(
		 * purchasePriceLocal.subtract(inquiryUnitPrice.multiply(new
		 * BigDecimal(pam.getApplyCount())))));
		 * assetsBudget.setUsedSumMoney(BigDecimal.ZERO.subtract(
		 * purchasePriceLocal)); assetsBudgetDAO.writeBack(assetsBudget); } }
		 */
        if ("N".equals(purchaseOrder.getDraft())) {
            // 资产明细 数量*采购单价(释放)
            // 把行明细的采购单价 放入资产明细采购单价.
            for (PurchaseApplyMapping pam : purchaseApplyMappings) {
                for (PurchaseOrderDetail pod : purchaseOrderDetails) {
                    if (pam.getApplyDetailId().equals(pod.getApplyDetailId())) {
                        pam.setPurchaseUnitPrice(pod.getPurchaseUnitPrice());
                        pam.setApplyUnitPrice(pod.getInquiryUnitPrice());
                        continue;
                    }
                }
            }
            List<AssetsBudget> assetsBudgets = new ArrayList<AssetsBudget>();
            for (PurchaseApplyMappingModel pam : purchaseApplyMappings) {
                boolean flag = false;
                int applyCount = 0;
                int checkCount = 0;
                try {
                    applyCount = pam.getApplyCount();
                } catch (Exception e) {
                    applyCount = 0;
                }
                try {
                    checkCount = pam.getGoodsCount();
                } catch (Exception e) {
                    checkCount = 0;
                }
                int backAmount = applyCount - checkCount;
                if (assetsBudgets.size() == 0) {
                    AssetsBudget assetsBudget = new AssetsBudget();
                    assetsBudget.setProjectCode(pam.getProjectCode());
                    assetsBudget.setBudgetYear(pam.getBudgetYear());
                    assetsBudget.setUsedAmount(backAmount);
                    assetsBudget.setUsedSumMoney(pam.getPurchaseUnitPrice().multiply(new BigDecimal(backAmount)));
                    assetsBudgets.add(assetsBudget);
                } else {
                    for (int i = 0; i < assetsBudgets.size(); i++) {
                        if (flag) {
                            break;
                        }
                        // 如果相同，则累加
                        if (assetsBudgets.get(i).getProjectCode().equals(pam.getProjectCode())) {
                            assetsBudgets.get(i).setUsedAmount(assetsBudgets.get(i).getUsedAmount() + backAmount);
                            assetsBudgets.get(i).setUsedSumMoney(assetsBudgets.get(i).getUsedSumMoney()
                                    .add(pam.getPurchaseUnitPrice().multiply(new BigDecimal(backAmount))));
                            flag = true;
                            break;
                        }
                        // 最后一次，不相同，则新增
                        if (assetsBudgets.size() - 1 == i) {
                            if (!assetsBudgets.get(i).getProjectCode().equals(pam.getProjectCode())) {
                                AssetsBudget assetsBudget = new AssetsBudget();
                                assetsBudget.setProjectCode(pam.getProjectCode());
                                assetsBudget.setBudgetYear(pam.getBudgetYear());
                                assetsBudget.setUsedAmount(backAmount);
                                assetsBudget.setUsedSumMoney(
                                        pam.getPurchaseUnitPrice().multiply(new BigDecimal(backAmount)));
                                assetsBudgets.add(assetsBudget);
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
            for (AssetsBudget assetsBudget : assetsBudgets) {
                assetsBudget.setUsedSumMoney(BigDecimal.ZERO.subtract(assetsBudget.getUsedSumMoney()));
                assetsBudget.setUsedAmount(-assetsBudget.getUsedAmount());
                assetsBudgetDAO.writeBack(assetsBudget);
            }
        }
        //return executeResult;
        // 修改订单状态="已关闭"
        purchaseOrder.setOrderStatus("已关闭");
        purchaseOrderDAO.updateOrderStatus(purchaseOrder);

        return executeResult;
    }

    @Override
    public ExecuteResult<PurchaseOrder> updateClosePurchaseOrderForPayment(PurchaseOrder purchaseOrder) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();

        // 修改订单付款关闭状态="已关闭"
        purchaseOrderDAO.updateOrderStatusForPayment(purchaseOrder);

        return executeResult;
    }

    @Override
    public void updateOrderIssap(PurchaseOrder purchaseOrder) {
        purchaseOrderDAO.updateOrderIssap(purchaseOrder);
    }

    @Override
    public Long searchNextReceiveCount(PurchaseOrder purchaseOrder) {
        return purchaseOrderDAO.searchNextReceiveCount(purchaseOrder);
    }

    @Override
    public ExecuteResult<PurchaseOrder> updateSearchCloseApply(PurchaseOrder purchaseOrder) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();
        List<ApplyOrder> applyOrders = applyOrderDAO.searchApplyByPurchaseId(purchaseOrder);
        for (ApplyOrder applyOrder : applyOrders) {
            Long searchNotApplyCountByApplyId = applyOrderDAO.searchNotApplyCountByApplyId(applyOrder);
            if (searchNotApplyCountByApplyId == 0) {
                ApplyOrder aOrder = new ApplyOrder();
                aOrder.setCdocument(applyOrder.getCdocument());
                aOrder.setOrderStatus("已关闭");
                applyOrderDAO.updateOrderStatus(aOrder);
            }
        }
        return executeResult;
    }

    /**
     * 导出
     */
    @Override
    public List<PurchaseOrder> exportPurchaseOrder(PurchaseOrderModel purchaseOrderModel) {
        return purchaseOrderDAO.exportPurchaseOrder(purchaseOrderModel);
    }

    @Override
    public boolean getIsLastApprovalStep(PurchaseOrderModel purchaseOrderModel) {
        boolean bool = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.GetIsLastApprovalStep isLast = new WorkFlowStub.GetIsLastApprovalStep();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(purchaseOrderModel.getCdocument());
            agreeFlowParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            agreeFlowParam.setBillTableName(Env.getProperty("purchase.info.table"));
            agreeFlowParam.setCUserCode(purchaseOrderModel.getLoginUserCode());
            agreeFlowParam.setCUserName(purchaseOrderModel.getLoginUserName());
            agreeFlowParam.setIdea("");
            agreeFlowParam.setAMoney("0");
            agreeFlowParam.setUserDepCode(purchaseOrderModel.getLoginUserDepCode());
            agreeFlowParam.setTypeCode(Env.getProperty("purchase.info.type.id"));

            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            agreeFlowParam.setCreateDate(instance);

            isLast.setParam(agreeFlowParam);

            WorkFlowStub.GetIsLastApprovalStepResponse isLastResponse = stub.getIsLastApprovalStep(isLast);

            bool = isLastResponse.getGetIsLastApprovalStepResult();
        } catch (AxisFault axisFault) {
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            LOGGER.error("RemoteException", e);
        }
        return bool;
    }

    @Override
    public ExecuteResult<PurchaseOrder> updateSecondary(PurchaseOrder purchaseOrder) {
        ExecuteResult<PurchaseOrder> executeResult = new ExecuteResult<PurchaseOrder>();
        purchaseOrderDAO.updateApplyNextSecondarycoding(purchaseOrder);
        purchaseOrderDAO.updatePurchaseSecondary(purchaseOrder);
        return executeResult;
    }

    @Override
    public void updatePrint(PurchaseOrder purchaseOrder) {
        purchaseOrderDAO.updatePrint(purchaseOrder);
    }

    @Override
    public String searchApplyOrderStatus(String applyOrderId) {
        return purchaseOrderDAO.searchApplyOrderStatus(applyOrderId);
    }

}
