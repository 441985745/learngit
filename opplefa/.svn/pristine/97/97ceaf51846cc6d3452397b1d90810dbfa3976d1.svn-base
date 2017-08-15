package com.opple.fa.purchase.service.impl;

import com.opple.barservice.BarServiceStub;
import com.opple.fa.purchase.dao.PaymentOrderDAO;
import com.opple.fa.purchase.dao.PaymentOrderDetailDAO;
import com.opple.fa.purchase.dao.PaymentPaymentMappingDAO;
import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PaymentOrderDetail;
import com.opple.fa.purchase.model.DemandOrderModel;
import com.opple.fa.purchase.model.PaymentOrderDetailModel;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.fa.purchase.model.PaymentPaymentMappingModel;
import com.opple.fa.purchase.service.PaymentOrderService;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.workflow.WorkFlowStub;
import lombok.Getter;
import lombok.Setter;
import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class PaymentOrderServiceImpl implements PaymentOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentOrderServiceImpl.class);
    @Getter
    @Setter
    private PaymentOrderDAO paymentOrderDAO;
    @Getter
    @Setter
    private PaymentOrderDetailDAO paymentOrderDetailDAO;
    @Getter
    @Setter
    private PaymentPaymentMappingDAO paymentPaymentMappingDAO;

    private final TransactionTemplate transactionTemplate;

    public PaymentOrderServiceImpl(PlatformTransactionManager transactionManager) {
        Assert.notNull(transactionManager, "The 'transactionManager' argument must not be null.");
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    public Pager<PaymentOrderModel> searchPaymentOrder(Pager<PaymentOrderModel> pager,
                                                       PaymentOrderModel paymentOrderModel) {

        List<PaymentOrderModel> searchPaymentOrderList = paymentOrderDAO.searchPaymentOrder(pager, paymentOrderModel);
        Long searchPaymentOrderCount = paymentOrderDAO.searchPaymentOrderCount(paymentOrderModel);

        return Pager.cloneFromPager(pager, searchPaymentOrderCount, searchPaymentOrderList);
    }

    @Override
    public List<PaymentOrderModel> searchPaymentOrderList(PaymentOrderModel paymentOrderModel) {
        return null;
    }

    @Override
    public PaymentOrderModel searchPaymentOrderByOrderId(PaymentOrder paymentOrder) {
        return paymentOrderDAO.searchPaymentOrderByOrderId(paymentOrder);
    }

    @Override
    public int searchNextSequence() {
        return paymentOrderDAO.searchNextSequence();
    }

    @Override
    public ExecuteResult<PaymentOrder> addSavePaymentOrder(final PaymentOrder paymentOrder, final List<PaymentOrderDetail> paymentOrderDetails, final List<PaymentPaymentMappingModel> paymentPaymentMappingModels) {
        final ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();

        return transactionTemplate.execute(new TransactionCallback<ExecuteResult<PaymentOrder>>() {
            @Override
            public ExecuteResult<PaymentOrder> doInTransaction(TransactionStatus transactionStatus) {
                boolean ifRollback = false;
                try {
                    for (PaymentOrderDetail pod : paymentOrderDetails) {
                        pod.setIsLastTime(paymentOrder.getIsLastTime());
                        paymentOrderDetailDAO.save(pod);
                    }
                    if (null != paymentPaymentMappingModels) {
                        for (PaymentPaymentMappingModel ppm : paymentPaymentMappingModels) {
                            /*ppm.setItemnoAcc(ppm.getItemnoAcc().substring(0, ppm.getItemnoAcc().length()-1));*/
                            paymentPaymentMappingDAO.save(ppm);
                        }
                    }
                } catch (Exception e) {
                    ifRollback = true;
                    result.addErrorMessage("保存单据失败！");
                    LOGGER.error("采购付款单据保存", e);
                }
                if (ifRollback) {
                    transactionStatus.setRollbackOnly();
                    //回滚后 删除表头信息
                    paymentOrderDAO.delPaymentOrderByCdocument(paymentOrder);
                } else {
                    if ("Y".equals(paymentOrder.getDraft())) {
                        return result;
                    }
                    //单据保存成功之后，绑定审批流
                    try {
                        WorkFlowStub stub = new WorkFlowStub();
                        WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
                        WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

                        createNextFlowStepParam.setBillCode(paymentOrder.getCdocument());
                        createNextFlowStepParam.setBillTableName(Env.getProperty("payment.info.table"));
                        createNextFlowStepParam.setBillCodeColumnName("CDOCUMENT");
                        createNextFlowStepParam.setDeptCode(paymentOrder.getApplyDepartmentCode());
                        createNextFlowStepParam.setDeptName(paymentOrder.getApplyDepartment());
                        createNextFlowStepParam.setCreatorCode(paymentOrder.getApplyUserCode());
                        createNextFlowStepParam.setCreatorName(paymentOrder.getApplyUser());
                        Calendar instance = Calendar.getInstance();
                        instance.setTime(paymentOrder.getApplyDate());
                        createNextFlowStepParam.setCreateDate(instance);
                        createNextFlowStepParam.setMoney(paymentOrder.getOrderMoney().toString());
                        createNextFlowStepParam.setTypeCode(Env.getProperty("payment.info.type.id"));
                        createNextFlowStepParam.setFeeItemCode("");
                        createNextFlowStepParam.setIsEmail(paymentOrder.getIsMail());
                        createNextFlowStepParam.setIsPhoneMessage(paymentOrder.getIsPhoneMessage());
                        createNextFlowStepParam.setCApprovalId(UUID.randomUUID().toString());
                        createNextFlowStepParam.setIdea(paymentOrder.getIdea());
                        createNextFlowStepParam.setCCommitActivityId("");
                        createNextFlowStepParam.setCCostCenterCode("");
                        if (!"".equals(paymentOrder.getActivityId())) {
                            createNextFlowStepParam.setCCommitActivityId(paymentOrder.getActivityId());
                        }

                        createNextFlowStep.setParam(createNextFlowStepParam);

                        createNextFlowStep.setUserCode(paymentOrder.getApplyUserCode());
                        createNextFlowStep.setUserName(paymentOrder.getApplyUser());
                        WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);
                        if (StringUtils.isNotBlank(response.getErrMessage())) {
                            ifRollback = true;
                            result.addErrorMessage(response.getErrMessage());
                            LOGGER.error("====" + response.getErrMessage() + "====");
                        }
                    } catch (AxisFault axisFault) {
                        ifRollback = true;
                        result.addErrorMessage("调用审批流失败");
                        LOGGER.error("axisFault", axisFault);
                    } catch (RemoteException e) {
                        ifRollback = true;
                        result.addErrorMessage("调用审批流失败");
                        LOGGER.error("RemoteException", e);
                    } catch (Exception e) {
                        ifRollback = true;
                        result.addErrorMessage("调用审批流失败");
                        LOGGER.error("Exception", e);
                    }
                    if (ifRollback) {
                        transactionStatus.setRollbackOnly();
                    }
                }
                return result;
            }
        });
    }

    @Override
    public ExecuteResult<PaymentOrder> updateSavePaymentOrder(final PaymentOrder paymentOrder, final List<PaymentOrderDetail> paymentOrderDetails, final List<PaymentPaymentMappingModel> paymentPaymentMappingModels) {
        final ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();

        return transactionTemplate.execute(new TransactionCallback<ExecuteResult<PaymentOrder>>() {
            @Override
            public ExecuteResult<PaymentOrder> doInTransaction(TransactionStatus transactionStatus) {
                boolean ifRollback = false;
                try {
                    for (PaymentOrderDetail pod : paymentOrderDetails) {
                        pod.setIsLastTime(paymentOrder.getIsLastTime());
                        paymentOrderDetailDAO.updatePaymentOrderDetailById(pod);
                    }
                    if (null != paymentPaymentMappingModels) {
                        paymentPaymentMappingDAO.delete(paymentOrder);
                        for (PaymentPaymentMappingModel ppm : paymentPaymentMappingModels) {
                            /*ppm.setItemnoAcc(ppm.getItemnoAcc().substring(0, ppm.getItemnoAcc().length()-1));*/
                            paymentPaymentMappingDAO.save(ppm);
                        }
                    }
                } catch (Exception e) {
                    ifRollback = true;
                    result.addErrorMessage("修改单据失败！");
                    LOGGER.error("采购付款单据修改", e);
                }
                if (ifRollback) {
                    transactionStatus.setRollbackOnly();

                } else {
                    if ("Y".equals(paymentOrder.getDraft())) {
                        return result;
                    }
                    //单据保存成功之后，绑定审批流
                    try {
                        WorkFlowStub stub = new WorkFlowStub();
                        WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
                        WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

                        createNextFlowStepParam.setBillCode(paymentOrder.getCdocument());
                        createNextFlowStepParam.setBillTableName(Env.getProperty("payment.info.table"));
                        createNextFlowStepParam.setBillCodeColumnName("CDOCUMENT");
                        createNextFlowStepParam.setDeptCode(paymentOrder.getApplyDepartmentCode());
                        createNextFlowStepParam.setDeptName(paymentOrder.getApplyDepartment());
                        createNextFlowStepParam.setCreatorCode(paymentOrder.getApplyUserCode());
                        createNextFlowStepParam.setCreatorName(paymentOrder.getApplyUser());
                        Calendar instance = Calendar.getInstance();
                        instance.setTime(paymentOrder.getApplyDate());
                        createNextFlowStepParam.setCreateDate(instance);
                        createNextFlowStepParam.setMoney(paymentOrder.getOrderMoney().toString());
                        createNextFlowStepParam.setTypeCode(Env.getProperty("payment.info.type.id"));
                        createNextFlowStepParam.setFeeItemCode("");
                        createNextFlowStepParam.setIsEmail(paymentOrder.getIsMail());
                        createNextFlowStepParam.setIsPhoneMessage(paymentOrder.getIsPhoneMessage());
                        createNextFlowStepParam.setCApprovalId(UUID.randomUUID().toString());
                        createNextFlowStepParam.setIdea(paymentOrder.getIdea());
                        createNextFlowStepParam.setCCommitActivityId(null == paymentOrder.getActivityId() ? "" : paymentOrder.getActivityId());
                        createNextFlowStepParam.setCCostCenterCode("");

                        createNextFlowStep.setParam(createNextFlowStepParam);

                        createNextFlowStep.setUserCode(paymentOrder.getApplyUserCode());
                        createNextFlowStep.setUserName(paymentOrder.getApplyUser());
                        WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);
                        if (StringUtils.isNotBlank(response.getErrMessage())) {
                            ifRollback = true;
                            result.addErrorMessage(response.getErrMessage());
                            LOGGER.error("====" + response.getErrMessage() + "====");
                        }
                    } catch (AxisFault axisFault) {
                        ifRollback = true;
                        result.addErrorMessage("调用审批流失败");
                        LOGGER.error("axisFault", axisFault);
                    } catch (RemoteException e) {
                        ifRollback = true;
                        result.addErrorMessage("调用审批流失败");
                        LOGGER.error("RemoteException", e);
                    } catch (Exception e) {
                        ifRollback = true;
                        result.addErrorMessage("调用审批流失败");
                        LOGGER.error("Exception", e);
                    }
                    if (ifRollback) {
                        transactionStatus.setRollbackOnly();
                    }
                }
                return result;
            }
        });
    }

    @Override
    public ExecuteResult<PaymentOrder> savePayment(PaymentOrder paymentOrder) {
        ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();

        // 根据订单ID查询该订单是否已存在
        DemandOrderModel dOrder = new DemandOrderModel();
        dOrder.setCdocument(paymentOrder.getCdocument());

        PaymentOrder searchPaymentOrderByOrderId = paymentOrderDAO.searchPaymentOrderByOrderId(paymentOrder);

        if (searchPaymentOrderByOrderId != null) {
            result.addErrorMessage("新增订单失败,该订单号已存在！");
        } else {
            paymentOrderDAO.save(paymentOrder);
        }
        return result;
    }

    @Override
    public void delPaymentOrderByCdocument(PaymentOrder paymentOrder) {
        paymentOrderDAO.delPaymentOrderByCdocument(paymentOrder);
        List<PaymentOrderDetailModel> paymentOrderDetails = paymentOrderDetailDAO.searchPaymentDetailByOrderId(paymentOrder);
        for (PaymentOrderDetailModel paymentOrderDetailModel : paymentOrderDetails) {
            Long id = paymentOrderDetailModel.getId();
            paymentOrderDetailDAO.delPaymentOrderDetailById(id);
        }
    }

    @Override
    public ExecuteResult<PaymentOrder> agreeWorkFlow(PaymentOrder paymentOrder) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            
            /*Options options = stub._getServiceClient().getOptions();
            options.setTimeOutInMilliSeconds(120000);//设置超时(单位是毫秒)
            stub._getServiceClient().setOptions(options);*/


            WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(paymentOrder.getCdocument());
            agreeFlowParam.setBillTableName(Env.getProperty("payment.info.table"));
            agreeFlowParam.setBillCodeColumnName("CDOCUMENT");
            agreeFlowParam.setCApprovalId(UUID.randomUUID().toString());
            agreeFlowParam.setDeptCode(paymentOrder.getApplyDepartmentCode());
            agreeFlowParam.setDeptName(paymentOrder.getApplyDepartment());
            agreeFlowParam.setCreatorCode(paymentOrder.getApplyUserCode());
            agreeFlowParam.setCreatorName(paymentOrder.getApplyUser());
           /* if (null == paymentOrder.getCreateDate()) {
                executeResult.addErrorMessage("创建日期不能为空");
                return executeResult;
            }*/
            Calendar instance = Calendar.getInstance();
            instance.setTime(paymentOrder.getCreateDate());
            agreeFlowParam.setCreateDate(instance);
            agreeFlowParam.setCUserCode(paymentOrder.getLoginUserCode());
            agreeFlowParam.setCUserName(paymentOrder.getLoginUserName());
            agreeFlowParam.setUserDepCode(paymentOrder.getUserDepCode());

            agreeFlowParam.setAMoney(paymentOrder.getOrderMoney().toString());
            agreeFlowParam.setTypeCode(Env.getProperty("payment.info.type.id"));
            agreeFlowParam.setMoney(paymentOrder.getOrderMoney().toString());
            agreeFlowParam.setIdea(paymentOrder.getIdea());
            agreeFlowParam.setIsEmail(paymentOrder.getIsMail());
            agreeFlowParam.setIsPhoneMessage(paymentOrder.getIsPhoneMessage());
            agreeFlowParam.setFeeItemCode("");
            agreeFlowParam.setCCostCenterCode("");
            agreeFlowParam.setCCommitActivityId(paymentOrder.getActivityId());

            agree.setUserCode(paymentOrder.getLoginUserCode());
            agree.setUserName(paymentOrder.getLoginUserName());
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

    @Override
    public ExecuteResult<PaymentOrder> denyWorkFlow(PaymentOrder paymentOrder) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();

            backFlowParam.setBillCode(paymentOrder.getCdocument());
            backFlowParam.setBillTableName(Env.getProperty("payment.info.table"));
            backFlowParam.setCreatorCode(paymentOrder.getApplyUserCode());
            backFlowParam.setCreatorName(paymentOrder.getApplyUser());
            backFlowParam.setIdea(paymentOrder.getIdea());
            backFlowParam.setCUserCode(paymentOrder.getLoginUserCode());
            backFlowParam.setCUserName(paymentOrder.getLoginUserName());
            backFlowParam.setUserDepCode(paymentOrder.getUserDepCode());
            backFlowParam.setTypeCode(Env.getProperty("payment.info.type.id"));
            backFlowParam.setCApprovalId(UUID.randomUUID().toString());
            backFlowParam.setActivityCode(paymentOrder.getActivityId());

            backFlowParam.setIsEmail(paymentOrder.getIsMail());
            backFlowParam.setIsPhoneMessage(paymentOrder.getIsPhoneMessage());

            back.setParam(backFlowParam);

            back.setUserCode(paymentOrder.getLoginUserCode());
            back.setUserName(paymentOrder.getLoginUserName());

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
        } catch (Exception e) {
            ifRollback = true;
            LOGGER.error("Exception", e);
        }

        if (ifRollback) {
            executeResult.addErrorMessage("调用审批流失败");
            return executeResult;
        }
        executeResult.setSuccessMessage("调用退回审批流成功");
        return executeResult;
    }

    @Override
    public ExecuteResult<PaymentOrder> dropWorkFlow(PaymentOrder paymentOrder) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();

            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(paymentOrder.getCdocument());
            unApprovalFlowParam.setBillTableName(Env.getProperty("payment.info.table"));
            unApprovalFlowParam.setBillCodeColumnName("CDOCUMENT");
            unApprovalFlowParam.setCreatorCode(paymentOrder.getApplyUserCode());
            unApprovalFlowParam.setCreatorName(paymentOrder.getApplyUser());
            unApprovalFlowParam.setIdea(paymentOrder.getIdea());
            unApprovalFlowParam.setCUserCode(paymentOrder.getLoginUserCode());
            unApprovalFlowParam.setCUserName(paymentOrder.getLoginUserName());
            unApprovalFlowParam.setUserDepCode(paymentOrder.getUserDepCode());
            unApprovalFlowParam.setTypeCode(Env.getProperty("payment.info.type.id"));

            unApproval.setParam(unApprovalFlowParam);

            unApproval.setUserCode(paymentOrder.getLoginUserCode());
            unApproval.setUserName(paymentOrder.getLoginUserName());

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

    @Override
    public String searchProfitCenter(String costCenterCode) {
        return paymentOrderDAO.searchProfitCenter(costCenterCode);
    }

    @Override
    public ExecuteResult<PaymentOrder> updatePaymentOrder(
            PaymentOrder paymentOrder) {
        ExecuteResult<PaymentOrder> result = new ExecuteResult<PaymentOrder>();
        if (paymentOrder == null) {
            result.addErrorMessage("付款订单不存在，请核实页面内容");
            return result;
        }
        paymentOrderDAO.updatePaymentOrderByDocument(paymentOrder);
        return result;
    }

    @Override
    public List<PaymentOrderModel> exportPaymentOrders(PaymentOrderModel paymentOrderModel) {
        return paymentOrderDAO.exportPaymentOrders(paymentOrderModel);
    }

    @Override
    public ExecuteResult<PaymentOrder> saveBar(PaymentOrder paymentOrder) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();

        boolean ifRollback = false;

        try {
            BarServiceStub stub = new BarServiceStub();
            BarServiceStub.SaveBar saveBar = new BarServiceStub.SaveBar();
            BarServiceStub.TMrmsVision tmrmsVision = new BarServiceStub.TMrmsVision();
            tmrmsVision.setCMrmCode(paymentOrder.getCdocument());
            tmrmsVision.setCBarCode("MRMS" + paymentOrder.getCdocument());
            tmrmsVision.setCTypeId(Env.getProperty("payment.info.type.id"));
            tmrmsVision.setCBranchCode(paymentOrder.getCompanyCode());
            tmrmsVision.setCBranchName(paymentOrder.getCompanyName());
            tmrmsVision.setIAmount(paymentOrder.getOrderMoney());
            tmrmsVision.setCRemark(paymentOrder.getApplyFor());
            tmrmsVision.setDBarDate(Calendar.getInstance());
            tmrmsVision.setDBillDate(Calendar.getInstance());
            tmrmsVision.setDApplytime(Calendar.getInstance());

            saveBar.setTMrmsVision(tmrmsVision);
            saveBar.setUserCode(paymentOrder.getApplyUserCode());
            saveBar.setUserName(paymentOrder.getApplyUser());

            BarServiceStub.SaveBarResponse saveBarResponse = stub.saveBar(saveBar);

            if (!"0;".equals(saveBarResponse.getErrMessage())) {
                executeResult.addErrorMessage(saveBarResponse.getErrMessage());
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

        executeResult.setSuccessMessage("创建影像成功");
        return executeResult;
    }

    @Override
    public ExecuteResult<PaymentOrder> deleteBar(PaymentOrder paymentOrder) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();

        boolean ifRollback = false;

        try {
            BarServiceStub stub = new BarServiceStub();
            BarServiceStub.DeleteBar deleteBar = new BarServiceStub.DeleteBar();

            deleteBar.setMrmcode(paymentOrder.getCdocument());
            deleteBar.setDesc("");
            deleteBar.setUserCode(paymentOrder.getApplyUserCode());
            deleteBar.setUserName(paymentOrder.getApplyUser());

            BarServiceStub.DeleteBarResponse deleteBarResponse = stub.deleteBar(deleteBar);

            if (!"0;".equals(deleteBarResponse.getErrMessage())) {
                executeResult.addErrorMessage("删除影像失败");
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

        executeResult.setSuccessMessage("删除影像成功");
        return executeResult;
    }

    @Override
    public ExecuteResult<PaymentOrder> backBar(PaymentOrder paymentOrder) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();

        boolean ifRollback = false;

        try {
            BarServiceStub stub = new BarServiceStub();
            BarServiceStub.BackBar backBar = new BarServiceStub.BackBar();

            backBar.setMrmcode(paymentOrder.getCdocument());
            backBar.setDesc(paymentOrder.getIdea());
            backBar.setBackUserCode(paymentOrder.getLoginUserCode());
            backBar.setBackUserName(paymentOrder.getLoginUserName());

            BarServiceStub.BackBarResponse backBarResponse = stub.backBar(backBar);

            if (!"0;".equals(backBarResponse.getErrMessage())) {
                executeResult.addErrorMessage("退回影像失败");
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

        executeResult.setSuccessMessage("退回影像成功");
        return executeResult;
    }

    @Override
    public ExecuteResult<PaymentOrder> checkBar(PaymentOrder paymentOrder) {
        ExecuteResult<PaymentOrder> executeResult = new ExecuteResult<PaymentOrder>();

        boolean ifRollback = false;
        String backMessage = "";
        try {
            BarServiceStub stub = new BarServiceStub();
            BarServiceStub.BarRead barRead = new BarServiceStub.BarRead();

            barRead.setMrmcode(paymentOrder.getCdocument());
            barRead.setEvaluation("1");

            BarServiceStub.BarReadResponse barReadResponse = stub.barRead(barRead);

            if ("".equals(barReadResponse.getBarReadResult())) {
                executeResult.addErrorMessage("影像调阅失败");
                return executeResult;
            } else {
                backMessage = barReadResponse.getBarReadResult();
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

        executeResult.setSuccessMessage(backMessage);
        return executeResult;
    }

    @Override
    public void updatePaymentOrderByCdocument(PaymentOrder paymentOrder) {
        paymentOrderDAO.updatePaymentOrderByCdocument(paymentOrder);

    }


}
