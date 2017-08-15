package com.opple.fa.purchase.service.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.opple.barservice.BarServiceStub;
import com.opple.common.dao.WorkFlowMapper;
import com.opple.fa.assetcard.dao.GeneralAssetDAO;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.config.dao.AssetsBudgetDAO;
import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.purchase.entity.NotPoPaymentOrderDetail;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.purchase.dao.NotPoPaymentOrderDAO;
import com.opple.fa.purchase.dao.NotPoPaymentOrderDetailDAO;
import com.opple.fa.purchase.dao.PaymentPaymentMappingDAO;
import com.opple.fa.purchase.entity.NotPoPaymentOrder;
import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.model.PaymentPaymentMappingModel;
import com.opple.fa.purchase.service.NotPoPaymentOrderService;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.workflow.WorkFlowStub;

public class NotPoPaymentOrderServiceImpl implements NotPoPaymentOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotPoPaymentOrderServiceImpl.class);
    @Getter
    @Setter
    private NotPoPaymentOrderDAO notPoPaymentOrderDAO;
    @Getter
    @Setter
    private NotPoPaymentOrderDetailDAO notPoPaymentOrderDetailDAO;
    @Getter
    @Setter
    private PaymentPaymentMappingDAO paymentPaymentMappingDAO;
    @Getter
    @Setter
    private AssetsBudgetDAO assetsBudgetDAO;
    @Getter
    @Setter
    private GeneralAssetDAO generalAssetDAO;
    @Getter
    @Setter
    private WorkFlowMapper workFlowMapper;

    /*	private final TransactionTemplate transactionTemplate;

        public NotPoPaymentOrderServiceImpl(PlatformTransactionManager transactionManager) {
            Assert.notNull(transactionManager, "The 'transactionManager' argument must not be null.");
            transactionTemplate = new TransactionTemplate(transactionManager);
        }*/
    @Override
    public Pager<NotPoPaymentOrder> searchPaymentOrder(
            Pager<NotPoPaymentOrder> pager, NotPoPaymentOrder notPoPaymentOrder) {
        List<NotPoPaymentOrder> searchPaymentOrderList = notPoPaymentOrderDAO.searchPaymentOrder(pager, notPoPaymentOrder);
        Long searchPaymentOrderCount = notPoPaymentOrderDAO.searchPaymentOrderCount(notPoPaymentOrder);

        return Pager.cloneFromPager(pager, searchPaymentOrderCount, searchPaymentOrderList);
    }

    @Override
    public List<NotPoPaymentOrder> searchPaymentOrderList(
            NotPoPaymentOrder notPoPaymentOrder) {
        return null;
    }

    @Override
    public NotPoPaymentOrder searchPaymentOrderByOrderId(
            NotPoPaymentOrder notPoPaymentOrder) {
        return notPoPaymentOrderDAO.searchPaymentOrderByOrderId(notPoPaymentOrder);
    }

    @Override
    public int searchNextSequence() {
        return 0;
    }

    @Override
    public ExecuteResult<NotPoPaymentOrder> savePayment(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();
        NotPoPaymentOrder notPoPaymentOrder1 = notPoPaymentOrderDAO.searchPaymentOrderByOrderId(notPoPaymentOrder);

        if (notPoPaymentOrder1 != null) {
            result.addErrorMessage("新增订单失败,该订单号已存在！");
        } else {
            notPoPaymentOrderDAO.save(notPoPaymentOrder);
        }
        return result;
    }

    @Override
    public void delPaymentOrderByCdocument(NotPoPaymentOrder notPoPaymentOrder) {
        notPoPaymentOrderDAO.delPaymentOrderByCdocument(notPoPaymentOrder);

    }

    @Override
    public ExecuteResult<NotPoPaymentOrder> agreeWorkFlow(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            
            /*Options options = stub._getServiceClient().getOptions();
            options.setTimeOutInMilliSeconds(120000);//设置超时(单位是毫秒)
            stub._getServiceClient().setOptions(options);*/


            WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(notPoPaymentOrder.getCdocument());
            agreeFlowParam.setBillTableName(Env.getProperty("notpayment.info.table"));
            agreeFlowParam.setBillCodeColumnName("CDOCUMENT");
            agreeFlowParam.setCApprovalId(UUID.randomUUID().toString());
            agreeFlowParam.setDeptCode(notPoPaymentOrder.getDepartmentCode());
            agreeFlowParam.setDeptName(notPoPaymentOrder.getDepartmentName());
            agreeFlowParam.setCreatorCode(notPoPaymentOrder.getApplyUserCode());
            agreeFlowParam.setCreatorName(notPoPaymentOrder.getApplyUser());
           /* if (null == paymentOrder.getCreateDate()) {
                executeResult.addErrorMessage("创建日期不能为空");
                return executeResult;
            }*/
            Calendar instance = Calendar.getInstance();
            instance.setTime(notPoPaymentOrder.getCreateDate());
            agreeFlowParam.setCreateDate(instance);
            agreeFlowParam.setCUserCode(notPoPaymentOrder.getLoginUserCode());
            agreeFlowParam.setCUserName(notPoPaymentOrder.getLoginUserName());
            agreeFlowParam.setUserDepCode(notPoPaymentOrder.getUserDepCode());

            agreeFlowParam.setAMoney(notPoPaymentOrder.getApplyMoney().toString());
            agreeFlowParam.setTypeCode(Env.getProperty("notpayment.info.type.id"));
            agreeFlowParam.setMoney(notPoPaymentOrder.getApplyMoney().toString());
            agreeFlowParam.setIdea(notPoPaymentOrder.getIdea());
            agreeFlowParam.setIsEmail(notPoPaymentOrder.getIsMail());
            agreeFlowParam.setIsPhoneMessage(notPoPaymentOrder.getIsPhoneMessage());
            agreeFlowParam.setFeeItemCode("");
            agreeFlowParam.setCCostCenterCode("");
            agreeFlowParam.setCCommitActivityId(notPoPaymentOrder.getActivityId());

            agree.setUserCode(notPoPaymentOrder.getLoginUserCode());
            agree.setUserName(notPoPaymentOrder.getLoginUserName());
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
    public ExecuteResult<NotPoPaymentOrder> denyWorkFlow(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();

            backFlowParam.setBillCode(notPoPaymentOrder.getCdocument());
            backFlowParam.setBillTableName(Env.getProperty("notpayment.info.table"));
            backFlowParam.setCreatorCode(notPoPaymentOrder.getApplyUserCode());
            backFlowParam.setCreatorName(notPoPaymentOrder.getApplyUser());
            backFlowParam.setIdea(notPoPaymentOrder.getIdea());
            backFlowParam.setCUserCode(notPoPaymentOrder.getLoginUserCode());
            backFlowParam.setCUserName(notPoPaymentOrder.getLoginUserName());
            backFlowParam.setUserDepCode(notPoPaymentOrder.getUserDepCode());
            backFlowParam.setTypeCode(Env.getProperty("notpayment.info.type.id"));
            backFlowParam.setCApprovalId(UUID.randomUUID().toString());
            backFlowParam.setActivityCode(notPoPaymentOrder.getActivityId());

            backFlowParam.setIsEmail(notPoPaymentOrder.getIsMail());
            backFlowParam.setIsPhoneMessage(notPoPaymentOrder.getIsPhoneMessage());

            back.setParam(backFlowParam);

            back.setUserCode(notPoPaymentOrder.getLoginUserCode());
            back.setUserName(notPoPaymentOrder.getLoginUserName());

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
    public ExecuteResult<NotPoPaymentOrder> dropWorkFlow(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();

            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(notPoPaymentOrder.getCdocument());
            unApprovalFlowParam.setBillTableName(Env.getProperty("notpayment.info.table"));
            unApprovalFlowParam.setBillCodeColumnName("CDOCUMENT");
            unApprovalFlowParam.setCreatorCode(notPoPaymentOrder.getApplyUserCode());
            unApprovalFlowParam.setCreatorName(notPoPaymentOrder.getApplyUser());
            unApprovalFlowParam.setIdea(notPoPaymentOrder.getIdea());
            unApprovalFlowParam.setCUserCode(notPoPaymentOrder.getLoginUserCode());
            unApprovalFlowParam.setCUserName(notPoPaymentOrder.getLoginUserName());
            unApprovalFlowParam.setUserDepCode(notPoPaymentOrder.getUserDepCode());
            unApprovalFlowParam.setTypeCode(Env.getProperty("notpayment.info.type.id"));

            unApproval.setParam(unApprovalFlowParam);

            unApproval.setUserCode(notPoPaymentOrder.getLoginUserCode());
            unApproval.setUserName(notPoPaymentOrder.getLoginUserName());

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<NotPoPaymentOrder> exportNotPoPaymentOrders(
            NotPoPaymentOrder notPoPaymentOrder) {
        return notPoPaymentOrderDAO.exportNotPoPaymentOrders(notPoPaymentOrder);
    }

    @Override
    public ExecuteResult<NotPoPaymentOrder> saveBar(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();

        boolean ifRollback = false;

        try {
            BarServiceStub stub = new BarServiceStub();
            BarServiceStub.SaveBar saveBar = new BarServiceStub.SaveBar();
            BarServiceStub.TMrmsVision tmrmsVision = new BarServiceStub.TMrmsVision();
            tmrmsVision.setCMrmCode(notPoPaymentOrder.getCdocument());
            tmrmsVision.setCBarCode("MRMS" + notPoPaymentOrder.getCdocument());
            tmrmsVision.setCTypeId(Env.getProperty("notpayment.info.type.id"));
            tmrmsVision.setCBranchCode(notPoPaymentOrder.getCompanyCode());
            tmrmsVision.setCBranchName(notPoPaymentOrder.getCompanyName());
            tmrmsVision.setIAmount(notPoPaymentOrder.getApplyMoney());
            tmrmsVision.setCRemark(notPoPaymentOrder.getApplyFor());
            tmrmsVision.setDBarDate(Calendar.getInstance());
            tmrmsVision.setDBillDate(Calendar.getInstance());
            tmrmsVision.setDApplytime(Calendar.getInstance());

            saveBar.setTMrmsVision(tmrmsVision);
            saveBar.setUserCode(notPoPaymentOrder.getApplyUserCode());
            saveBar.setUserName(notPoPaymentOrder.getApplyUser());

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
    public ExecuteResult<NotPoPaymentOrder> deleteBar(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();
        boolean ifRollback = false;

        try {
            BarServiceStub stub = new BarServiceStub();
            BarServiceStub.DeleteBar deleteBar = new BarServiceStub.DeleteBar();

            deleteBar.setMrmcode(notPoPaymentOrder.getCdocument());
            deleteBar.setDesc("");
            deleteBar.setUserCode(notPoPaymentOrder.getApplyUserCode());
            deleteBar.setUserName(notPoPaymentOrder.getApplyUser());

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
    public ExecuteResult<NotPoPaymentOrder> backBar(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();

        boolean ifRollback = false;

        try {
            BarServiceStub stub = new BarServiceStub();
            BarServiceStub.BackBar backBar = new BarServiceStub.BackBar();

            backBar.setMrmcode(notPoPaymentOrder.getCdocument());
            backBar.setDesc(notPoPaymentOrder.getIdea());
            backBar.setBackUserCode(notPoPaymentOrder.getLoginUserCode());
            backBar.setBackUserName(notPoPaymentOrder.getLoginUserName());

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
    public ExecuteResult<NotPoPaymentOrder> checkBar(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> executeResult = new ExecuteResult<NotPoPaymentOrder>();

        boolean ifRollback = false;
        String backMessage = "";
        try {
            BarServiceStub stub = new BarServiceStub();
            BarServiceStub.BarRead barRead = new BarServiceStub.BarRead();

            barRead.setMrmcode(notPoPaymentOrder.getCdocument());
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
    public void updatePaymentOrderByCdocument(
            NotPoPaymentOrder notPoPaymentOrder) {
        notPoPaymentOrderDAO.updatePaymentOrderByCdocument(notPoPaymentOrder);

    }

    @Override
    public void deleteNoPoPaymentOrder(NotPoPaymentOrder notPoPaymentOrder) {

        if ("N".equals(notPoPaymentOrder.getCommitType())) {
            NotPoPaymentOrderDetail notPoPaymentOrderDetail = new NotPoPaymentOrderDetail();
            notPoPaymentOrderDetail.setNotPoPaymentOrderId(notPoPaymentOrder.getCdocument());
            List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails = notPoPaymentOrderDetailDAO.searchPaymentOrderByDocument(notPoPaymentOrderDetail);
            for (NotPoPaymentOrderDetail dod : notPoPaymentOrderDetails) {
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(dod.getProjectCode());
                assetsBudget.setBudgetYear(dod.getBudgetYear());

                BigDecimal sumMoney = new BigDecimal("0");
                sumMoney = sumMoney.subtract(dod.getTaxTotalPrice().add(new BigDecimal(dod.getAssetsIncreaseValue())));
                assetsBudget.setUsedSumMoney(sumMoney);
                assetsBudgetDAO.writeBack(assetsBudget);
            }
        }
        notPoPaymentOrderDAO.deleteNoPoPaymentOrder(notPoPaymentOrder);
    }


    @Override
    public ExecuteResult<NotPoPaymentOrder> updateNotPoPaymentOrder(NotPoPaymentOrder notPoPaymentOrder,
                                                                    List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails, List<PaymentPaymentMappingModel> paymentPaymentMappingModels) {
        ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();
        notPoPaymentOrderDAO.updateNotPoPaymentOrder(notPoPaymentOrder);
        if ("验收款".equals(notPoPaymentOrder.getPaymentType())) {
            if ("N".equals(notPoPaymentOrder.getCommitType())) {
                NotPoPaymentOrderDetail notPoPaymentOrderDetail = new NotPoPaymentOrderDetail();
                notPoPaymentOrderDetail.setNotPoPaymentOrderId(notPoPaymentOrder.getCdocument());
                List<NotPoPaymentOrderDetail> oldNotPoPaymentOrderDetails = notPoPaymentOrderDetailDAO.searchPaymentOrderByDocument(notPoPaymentOrderDetail);
                for (NotPoPaymentOrderDetail dod : oldNotPoPaymentOrderDetails) {
                    AssetsBudget assetsBudget = new AssetsBudget();
                    assetsBudget.setProjectCode(dod.getProjectCode());
                    assetsBudget.setBudgetYear(dod.getBudgetYear());

                    BigDecimal sumMoney = new BigDecimal("0");
                    sumMoney = sumMoney.subtract(dod.getTaxTotalPrice().add(new BigDecimal(dod.getAssetsIncreaseValue())));
                    assetsBudget.setUsedSumMoney(sumMoney);
                    assetsBudgetDAO.writeBack(assetsBudget);
                }
            }
            NotPoPaymentOrderDetail notPoPaymentOrderDetail = new NotPoPaymentOrderDetail();
            notPoPaymentOrderDetail.setNotPoPaymentOrderId(notPoPaymentOrder.getCdocument());
            notPoPaymentOrderDetailDAO.delPaymentOrderDetailById(notPoPaymentOrderDetail);
            boolean ifRollback = false;
            try {
                List<AssetsBudget> assetsBudgets = new ArrayList<AssetsBudget>();
                for (NotPoPaymentOrderDetail dod : notPoPaymentOrderDetails) {
                    boolean flag = false;
                    dod.setNotPoPaymentOrderId(notPoPaymentOrder.getCdocument());
                    notPoPaymentOrderDetailDAO.save(dod);
                    if ("N".equals(notPoPaymentOrder.getCommitType())) {
                        // 如果不是草稿 则统计预算
                        if (assetsBudgets.size() == 0) {
                            AssetsBudget assetsBudget = new AssetsBudget();
                            assetsBudget.setProjectCode(dod.getProjectCode());
                            assetsBudget.setBudgetYear(dod.getBudgetYear());
                            assetsBudget.setUsedSumMoney(
                                    dod.getTaxTotalPrice().add(new BigDecimal(dod.getAssetsIncreaseValue())));
                            assetsBudgets.add(assetsBudget);
                        } else {
                            for (int i = 0; i < assetsBudgets.size(); i++) {
                                if (flag) {
                                    break;
                                }
                                // 如果相同，则累加
                                if (assetsBudgets.get(i).getProjectCode().equals(dod.getProjectCode())) {
                                    assetsBudgets.get(i).setUsedSumMoney(
                                            assetsBudgets.get(i).getUsedSumMoney().add(
                                                    dod.getTaxTotalPrice().add(new BigDecimal(dod.getAssetsIncreaseValue()))));
                                    flag = true;
                                    break;
                                }
                                // 最后一次，不相同，则新增
                                if (assetsBudgets.size() - 1 == i) {
                                    if (!assetsBudgets.get(i).getProjectCode().equals(dod.getProjectCode())) {
                                        AssetsBudget assetsBudget = new AssetsBudget();
                                        assetsBudget.setProjectCode(dod.getProjectCode());
                                        assetsBudget.setBudgetYear(dod.getBudgetYear());
                                        assetsBudget.setUsedSumMoney(
                                                dod.getTaxTotalPrice().add(new BigDecimal(dod.getAssetsIncreaseValue())));
                                        assetsBudgets.add(assetsBudget);
                                        flag = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                // 如果不是草稿，回写预算信息
                if ("N".equals(notPoPaymentOrder.getCommitType())) {
                    for (AssetsBudget assetsBudget : assetsBudgets) {
                        assetsBudgetDAO.writeBack(assetsBudget);
                    }
                }
                // 判断预算是否超出
                for (NotPoPaymentOrderDetail pod : notPoPaymentOrderDetails) {
                    AssetsBudget assetsBudget = new AssetsBudget();
                    assetsBudget.setProjectCode(pod.getProjectCode());
                    assetsBudget.setBudgetYear(pod.getBudgetYear());
                    AssetsBudget searchAssetsBudgetsByProjectCode = assetsBudgetDAO
                            .getAssetsBudgetbyProjectCodeAndYear(pod.getBudgetYear(), pod.getProjectCode());
                    // 如果预算可用金额小于零 抛出异常
                    if (searchAssetsBudgetsByProjectCode == null) {
                        ifRollback = true;
                        result.addErrorMessage("项目编码:" + pod.getProjectCode() + " 无效或已被删除，请修改！");
                        break;
                    }
                    if (searchAssetsBudgetsByProjectCode.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) { //若可用预算小于0
                        ifRollback = true;
                        result.addErrorMessage("项目编码:" + pod.getProjectCode() + " 预算超出，请修改！");
                        break;
                    }
                }
                if (null != paymentPaymentMappingModels) {
                    PaymentOrder paymentOrder = new PaymentOrder();
                    paymentOrder.setCdocument(notPoPaymentOrder.getCdocument());
                    paymentPaymentMappingDAO.delete(paymentOrder);
                    for (PaymentPaymentMappingModel ppm : paymentPaymentMappingModels) {
                        paymentPaymentMappingDAO.save(ppm);
                    }
                }
            } catch (Exception e) {
                ifRollback = true;
                result.addErrorMessage("保存单据失败！");
                LOGGER.error("采购付款单据保存", e);
            }
            if (ifRollback) {
                //释放预算
                for (NotPoPaymentOrderDetail dod : notPoPaymentOrderDetails) {
                    AssetsBudget assetsBudget = new AssetsBudget();
                    assetsBudget.setProjectCode(dod.getProjectCode());
                    assetsBudget.setBudgetYear(dod.getBudgetYear());

                    BigDecimal sumMoney = new BigDecimal("0");
                    sumMoney = sumMoney.subtract(dod.getTaxTotalPrice().add(new BigDecimal(dod.getAssetsIncreaseValue())));
                    assetsBudget.setUsedSumMoney(sumMoney);
                    assetsBudgetDAO.writeBack(assetsBudget);
                }
                //回滚后 删除表头信息
                notPoPaymentOrderDAO.deleteNoPoPaymentOrder(notPoPaymentOrder);
            }
        }
        return result;
    }

    @Override
    public ExecuteResult<NotPoPaymentOrder> binDingFlow(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();
        boolean ifRollback = false;
        //单据保存成功之后，绑定审批流
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
            WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

            createNextFlowStepParam.setBillCode(notPoPaymentOrder.getCdocument());
            createNextFlowStepParam.setBillTableName(Env.getProperty("notpayment.info.table"));
            createNextFlowStepParam.setBillCodeColumnName("CDOCUMENT");
            createNextFlowStepParam.setDeptCode(notPoPaymentOrder.getDepartmentCode());
            createNextFlowStepParam.setDeptName(notPoPaymentOrder.getDepartmentName());
            createNextFlowStepParam.setCreatorCode(notPoPaymentOrder.getApplyUserCode());
            createNextFlowStepParam.setCreatorName(notPoPaymentOrder.getApplyUser());
            Calendar instance = Calendar.getInstance();
            instance.setTime(notPoPaymentOrder.getApplyDate());
            createNextFlowStepParam.setCreateDate(instance);
            createNextFlowStepParam.setMoney(notPoPaymentOrder.getApplyMoney().toString());
            createNextFlowStepParam.setTypeCode(Env.getProperty("notpayment.info.type.id"));
            createNextFlowStepParam.setFeeItemCode("");
            createNextFlowStepParam.setIsEmail(notPoPaymentOrder.getIsMail());
            createNextFlowStepParam.setIsPhoneMessage(notPoPaymentOrder.getIsPhoneMessage());
            createNextFlowStepParam.setCApprovalId(UUID.randomUUID().toString());
            createNextFlowStepParam.setIdea(notPoPaymentOrder.getIdea());
            createNextFlowStepParam.setCCommitActivityId("");
            createNextFlowStepParam.setCCostCenterCode("");
            if (!"".equals(notPoPaymentOrder.getActivityId())) {
                createNextFlowStepParam.setCCommitActivityId(notPoPaymentOrder.getActivityId());
            }

            createNextFlowStep.setParam(createNextFlowStepParam);

            createNextFlowStep.setUserCode(notPoPaymentOrder.getApplyUserCode());
            createNextFlowStep.setUserName(notPoPaymentOrder.getApplyUser());
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
        return result;
    }

    @Override
    public ExecuteResult<NotPoPaymentOrder> savePaymentForCheck(
            NotPoPaymentOrder notPoPaymentOrder,
            List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails,
            List<PaymentPaymentMappingModel> paymentPaymentMappingModels) {
        ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();
        boolean ifRollback = false;
        try {
            List<AssetsBudget> assetsBudgets = new ArrayList<AssetsBudget>();
            for (NotPoPaymentOrderDetail dod : notPoPaymentOrderDetails) {
                boolean flag = false;
                dod.setNotPoPaymentOrderId(notPoPaymentOrder.getCdocument());
                notPoPaymentOrderDetailDAO.save(dod);
                if ("N".equals(notPoPaymentOrder.getCommitType())) {
                    // 如果不是草稿 则统计预算
                    if (assetsBudgets.size() == 0) {
                        AssetsBudget assetsBudget = new AssetsBudget();
                        assetsBudget.setProjectCode(dod.getProjectCode());
                        assetsBudget.setBudgetYear(dod.getBudgetYear());
                        assetsBudget.setUsedSumMoney(
                                dod.getTaxTotalPrice().add(new BigDecimal(dod.getAssetsIncreaseValue())));
                        assetsBudgets.add(assetsBudget);
                    } else {
                        for (int i = 0; i < assetsBudgets.size(); i++) {
                            if (flag) {
                                break;
                            }
                            // 如果相同，则累加
                            if (assetsBudgets.get(i).getProjectCode().equals(dod.getProjectCode())) {
                                assetsBudgets.get(i).setUsedSumMoney(
                                        assetsBudgets.get(i).getUsedSumMoney().add(
                                                dod.getTaxTotalPrice().add(new BigDecimal(dod.getAssetsIncreaseValue()))));
                                flag = true;
                                break;
                            }
                            // 最后一次，不相同，则新增
                            if (assetsBudgets.size() - 1 == i) {
                                if (!assetsBudgets.get(i).getProjectCode().equals(dod.getProjectCode())) {
                                    AssetsBudget assetsBudget = new AssetsBudget();
                                    assetsBudget.setProjectCode(dod.getProjectCode());
                                    assetsBudget.setBudgetYear(dod.getBudgetYear());
                                    assetsBudget.setUsedSumMoney(
                                            dod.getTaxTotalPrice().add(new BigDecimal(dod.getAssetsIncreaseValue())));
                                    assetsBudgets.add(assetsBudget);
                                    flag = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            // 如果不是草稿，回写预算信息
            if ("N".equals(notPoPaymentOrder.getCommitType())) {
                for (AssetsBudget assetsBudget : assetsBudgets) {
                    assetsBudgetDAO.writeBack(assetsBudget);
                }
            }
            // 判断预算是否超出
            for (NotPoPaymentOrderDetail pod : notPoPaymentOrderDetails) {
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(pod.getProjectCode());
                assetsBudget.setBudgetYear(pod.getBudgetYear());
                AssetsBudget searchAssetsBudgetsByProjectCode = assetsBudgetDAO
                        .getAssetsBudgetbyProjectCodeAndYear(pod.getBudgetYear(), pod.getProjectCode());
                // 如果预算可用金额小于零 抛出异常
                if (searchAssetsBudgetsByProjectCode == null) {
                    ifRollback = true;
                    result.addErrorMessage("项目编码:" + pod.getProjectCode() + " 无效或已被删除，请修改！");
                    break;
                }
                if (searchAssetsBudgetsByProjectCode.getAvaliableSumMoney().compareTo(BigDecimal.ZERO) == -1) { //若可用预算小于0
                    ifRollback = true;
                    result.addErrorMessage("项目编码:" + pod.getProjectCode() + " 预算超出，请修改！");
                    break;
                }
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
            //释放预算
            for (NotPoPaymentOrderDetail dod : notPoPaymentOrderDetails) {
                AssetsBudget assetsBudget = new AssetsBudget();
                assetsBudget.setProjectCode(dod.getProjectCode());
                assetsBudget.setBudgetYear(dod.getBudgetYear());

                BigDecimal sumMoney = new BigDecimal("0");
                sumMoney = sumMoney.subtract(dod.getTaxTotalPrice().add(new BigDecimal(dod.getAssetsIncreaseValue())));
                assetsBudget.setUsedSumMoney(sumMoney);
                assetsBudgetDAO.writeBack(assetsBudget);
            }
            //回滚后 删除表头信息
            notPoPaymentOrderDAO.deleteNoPoPaymentOrder(notPoPaymentOrder);
        }
        return result;
    }

    @Override
    public ExecuteResult<NotPoPaymentOrder> updatePaymentOrderByDocument(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();
        notPoPaymentOrderDAO.updatePaymentOrderByDocument(notPoPaymentOrder);
        return result;
    }

    @Override
    public void getMainAssetCodePro(NotPoPaymentOrder notPoPaymentOrder) {
        notPoPaymentOrderDAO.getMainAssetCodePro(notPoPaymentOrder);
    }

    @Override
    public ExecuteResult<NotPoPaymentOrder> updateIsGetCodeByDocument(
            NotPoPaymentOrder notPoPaymentOrder) {
        ExecuteResult<NotPoPaymentOrder> result = new ExecuteResult<NotPoPaymentOrder>();
        notPoPaymentOrderDAO.updateIsGetCodeByDocument(notPoPaymentOrder);
        NotPoPaymentOrderDetail notPoPaymentOrderDetail = new NotPoPaymentOrderDetail();
        notPoPaymentOrderDetail.setNotPoPaymentOrderId(notPoPaymentOrder.getCdocument());
        List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails1 = notPoPaymentOrderDetailDAO.searchPaymentOrderByDocument(notPoPaymentOrderDetail);
        for (NotPoPaymentOrderDetail rgd : notPoPaymentOrderDetails1) {
            String[] subNumber = rgd.getSecondary().split("-");
            int subNum = Integer.parseInt(subNumber[0]);
            for (int i = 0; rgd.getPayCount() > i; i++) {


                String unit = rgd.getUnits();
                String assetsType = rgd.getAssetsType();
                String assetsName = rgd.getAssetsName();
                    /*行政资产和非电脑类 资产卡片主表 保存一些公共的字段  */
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setAssetType(assetsType); //资产类型
                generalAsset.setAssetState("1"); //资产状态 预验收 0    已验收1
                generalAsset.setAssetCode(rgd.getMainAssetCode() + "-" + String.valueOf(subNum)); //主资产编码 - 次级编码（sap编码）
                generalAsset.setAssetClassification(rgd.getAssetClassification());  //资产分类
                if ("Y".equals(notPoPaymentOrder.getIsExpenseAssets())) {
                    //					系统管理编号 (是)
                    generalAsset.setSysteManagementCode(workFlowMapper.getDocumentByType("FA_MMFY"));
                } else if ("N".equals(notPoPaymentOrder.getIsExpenseAssets())) {
                    //					系统管理编号 (否)
                    generalAsset.setSysteManagementCode(workFlowMapper.getDocumentByType("FA_MMZB"));
                }
                generalAsset.setIsExpenseAssets(notPoPaymentOrder.getIsExpenseAssets()); //是否为一次性费用化资产
                //rgd.getPurchaseDetailId()//订单详情ID（订单详情表主键）
                generalAsset.setSpecificationModel(rgd.getSpecificationParameter()); //品牌/规格/型号
                generalAsset.setUseState("0"); //资产状态（0在用）
                //js中默认验收周期计量器具45天，非计量器具30天
                //generalAsset.setPlatform(platform); //平台
                generalAsset.setCompanyName(notPoPaymentOrder.getCompanyName()); //所属公司
                generalAsset.setCompanyCode(notPoPaymentOrder.getCompanyCode()); //所属公司编码
                generalAsset.setPersentDepartment(notPoPaymentOrder.getCostCenter()); //现部门和成本中心
                generalAsset.setUseDepartment(rgd.getSubordinateDepartment()); //使用部门*
                //					generalAsset.setAssetPerson(ao.getApplyUser()); //管理责任人*
                //					generalAsset.setAssetPersonCode(ao.getApplyUserCode()); //管理责任人編碼*
                generalAsset.setAssetPerson(rgd.getAssetsUserName());
                generalAsset.setAssetPersonCode(rgd.getAssetsUserCode());
                generalAsset.setSupplierName(notPoPaymentOrder.getSupplierName()); //供应商名称
                generalAsset.setSupplierCode(notPoPaymentOrder.getSupplierCode());  //供应商编码
                generalAsset.setIsUpdateSap("0");  //是否更新了 sap  1为 是  0 为  否
                generalAsset.setDifferentiatePurchase("2");  //区分是页面新增卡片还是收货后新增卡片
                generalAsset.setCostCenterCode(notPoPaymentOrder.getCostCenterCode());
                generalAsset.setUnit(unit); //单位
                generalAsset.setOfficeLocation(rgd.getOfficeLocation()); //办公地点
                generalAsset.setAssetType(rgd.getAssetsType()); //资产类型
                generalAsset.setAssetNetValue("0.00"); //资产净值assetNetValue
                generalAsset.setAssetOriginalValue("0.00"); //资产原值assetOriginalValue
                generalAsset.setPeriodOfDepreciation("0"); //折旧年限periodOfDepreciation
                generalAsset.setMonthCountingDepreciation("0.00"); //月折旧额monthCountingDepreciation
                generalAsset.setCurrentYearDepreciation("0.00"); //本年已计提累计折旧currentYearDepreciation
                generalAsset.setAddDepreciation("0.00"); //累计折旧addDepreciation
                /**
                 * 获取相对应的采购订单主表行,进而读取供应商信息
                 */
                generalAsset.setAssetName(assetsName); //资产名称
                //generalAsset.setCreateBy(createBy); //创建人
                generalAsset.setCreateDate(new Date()); //创建时间
                Long counts = generalAssetDAO.assetCodeCount(generalAsset);
                GeneralAsset generalAsset1 = new GeneralAsset();
                if ("forAppoint".equals(rgd.getCodingMode()) && counts > 0) {
                    //当指定类型的资产，在资产表中有信息时，不进行新增
                    generalAsset1 = generalAssetDAO.searchGeneralAssetOnly(generalAsset);
                } else {
                    generalAssetDAO.insertGeneralAsset(generalAsset);
                }
            }
            subNum++;
        }
        return result;
    }

    @Override
    public List<NotPoPaymentOrder> searchPoPaymentOrders(
            NotPoPaymentOrder notPoPaymentOrder) {
        return notPoPaymentOrderDAO.searchPoPaymentOrders(notPoPaymentOrder);
    }

    @Override
    public String searchSumPriceByCdocument(NotPoPaymentOrder notPoPaymentOrder) {
        return notPoPaymentOrderDAO.searchSumPriceByCdocument(notPoPaymentOrder);
    }

    @Override
    public void updateCvoucherId(NotPoPaymentOrder notPoPaymentOrder) {
        notPoPaymentOrderDAO.updateCvoucherId(notPoPaymentOrder);

    }

}
