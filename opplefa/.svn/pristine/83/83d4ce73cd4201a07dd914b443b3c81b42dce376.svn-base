package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.BudgetAdjustmentDAO;
import com.opple.fa.config.dao.BudgetAdjustmentDetailDAO;
import com.opple.fa.config.entity.BudgetAdjustment;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.fa.config.model.ASSETBUDGETTYPE;
import com.opple.fa.config.model.DRAFT;
import com.opple.fa.config.service.BudgetAdjustmentService;
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
import java.util.Calendar;
import java.util.List;

/**
 * Created by Wangkaixuan on 2017/3/21.
 */
public class BudgetAdjustmentServiceImpl implements BudgetAdjustmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetAdjustmentServiceImpl.class);
    @Getter
    @Setter
    private BudgetAdjustmentDAO budgetAdjustmentDAO;
    @Getter
    @Setter
    private BudgetAdjustmentDetailDAO budgetAdjustmentDetailDAO;

    @Override
    public String getDocumentByProjectCodeAndYear(BudgetAdjustmentDetail budgetAdjustmentDetail) {
        return budgetAdjustmentDAO.getDocumentByProjectCodeAndYear(budgetAdjustmentDetail);
    }

    @Override
    public ExecuteResult<BudgetAdjustment> updateBudgetAdjustmentByDocument(BudgetAdjustment budgetAdjustment, List<BudgetAdjustmentDetail> budgetAdjustmentDetails) {
        ExecuteResult<BudgetAdjustment> result = new ExecuteResult<BudgetAdjustment>();
        if (budgetAdjustmentDetails.size() > 0) {
            /*新增*/
            if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                budgetAdjustmentDAO.updateBudgetAdjustmentByDocument(budgetAdjustment);
                budgetAdjustmentDetailDAO.deleteBudgetAdjustmentDetailByDocument(budgetAdjustment.getDocument());
                for (BudgetAdjustmentDetail budgetAdjustmentDetail : budgetAdjustmentDetails) {
                    budgetAdjustmentDetail.setStatus("0");
                    budgetAdjustmentDetail.setBudgetAdjustId(budgetAdjustment.getBudgetId());
                    budgetAdjustmentDetailDAO.addBudgetAdjustmentDetail(budgetAdjustmentDetail);
                }
            } else {
                /*增减或项目间*/
                //先判断是否草稿，再判断是否存在生在审批中的项目
                if (DRAFT.NO.getDraft().equals(budgetAdjustment.getDraft())) {
                    for (BudgetAdjustmentDetail budgetAdjustmentDetail : budgetAdjustmentDetails) {
                        String document = getDocumentByProjectCodeAndYear(budgetAdjustmentDetail);
                        if (StringUtils.isNotBlank(document) && !document.equals(budgetAdjustment.getDocument())) {
                            result.addErrorMessage("项目编码及对应的年度存在未完成审批流程【" + document + "】，请对应流程审批完成后再进行调整！\n");
                        }
                    }
                }
                //不存在时更新项目 否则不更新
                if (result.isSuccess()) {
                    budgetAdjustmentDAO.updateBudgetAdjustmentByDocument(budgetAdjustment);
                    budgetAdjustmentDetailDAO.deleteBudgetAdjustmentDetailByDocument(budgetAdjustment.getDocument());
                    for (BudgetAdjustmentDetail budgetAdjustmentDetail : budgetAdjustmentDetails) {
                        budgetAdjustmentDetail.setStatus("0");
                        budgetAdjustmentDetail.setBudgetAdjustId(budgetAdjustment.getBudgetId());
                        BigDecimal df = new BigDecimal(budgetAdjustmentDetail.getAfterUnitPrice().toString());
                        budgetAdjustmentDetail.setAfterUnitPrice(df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                        budgetAdjustmentDetailDAO.addBudgetAdjustmentDetail(budgetAdjustmentDetail);
                    }
                }
            }
        } else {
            result.addErrorMessage("预算信息不能为空\n");
        }

        return result;
    }

    @Override
    public void updateBudgetAdjustmentToDraftByDocument(String document) {
        budgetAdjustmentDAO.updateBudgetAdjustmentToDraftByDocument(document);
    }

    @Override
    public BudgetAdjustment getBudgetAdjustmentByDocument(String doucument) {
        BudgetAdjustment budgetAdjustment = budgetAdjustmentDAO.getBudgetAdjustmentByDocument(doucument);
        if (null != budgetAdjustment) {
            List<BudgetAdjustmentDetail> budgetAdjustmentDetails = budgetAdjustmentDetailDAO.getBudgetAdjustmentDetailsByDocument(doucument);
            budgetAdjustment.getBudgetAdjustmentDetailList().addAll(budgetAdjustmentDetails);
        }
        return budgetAdjustment;
    }

    @Override
    public BudgetAdjustment getBudgetAdjustment(String document) {
        BudgetAdjustment budgetAdjustment = budgetAdjustmentDAO.getBudgetAdjustmentByDocument(document);
        List<BudgetAdjustmentDetail> budgetAdjustmentDetails = budgetAdjustmentDetailDAO.getBudgetAdjustmentDetailsByBudgetId(budgetAdjustment.getBudgetId());
        budgetAdjustment.getBudgetAdjustmentDetailList().addAll(budgetAdjustmentDetails);
        return budgetAdjustment;
    }

    @Override
    public Pager<BudgetAdjustment> getBudgetAdjustmentList(Pager<BudgetAdjustment> pager, BudgetAdjustment budgetAdjustment) {
        List<BudgetAdjustment> budgetAdjustments = budgetAdjustmentDAO.getBudgetAdjustmentList(pager, budgetAdjustment);
        Long budgetAdjustmentsCount = budgetAdjustmentDAO.getBudgetAdjustmentListCount(budgetAdjustment);
        return Pager.cloneFromPager(pager, budgetAdjustmentsCount, budgetAdjustments);
    }

    @Override
    public ExecuteResult<BudgetAdjustment> addBudgetAdjustment(BudgetAdjustment budgetAdjustment, List<BudgetAdjustmentDetail> budgetAdjustmentDetails) {
        ExecuteResult<BudgetAdjustment> result = new ExecuteResult<BudgetAdjustment>();
        if (budgetAdjustmentDetails.size() > 0) {
            if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                budgetAdjustmentDAO.addBudgetAdjustment(budgetAdjustment);
                for (BudgetAdjustmentDetail budgetAdjustmentDetail : budgetAdjustmentDetails) {
                    budgetAdjustmentDetail.setBudgetAdjustId(budgetAdjustment.getBudgetId());
                    budgetAdjustmentDetail.setStatus("0");
                    budgetAdjustmentDetailDAO.addBudgetAdjustmentDetail(budgetAdjustmentDetail);
                }
            } else {
                //先判断是否存在生在审批中的项目
                if (DRAFT.NO.getDraft().equals(budgetAdjustment.getDraft())) {
                    for (BudgetAdjustmentDetail budgetAdjustmentDetail : budgetAdjustmentDetails) {
                        String document = getDocumentByProjectCodeAndYear(budgetAdjustmentDetail);
                        if (StringUtils.isNotBlank(document)) {
                            result.addErrorMessage("项目编码及对应的年度存在未完成审批流程【" + document + "】，请对应流程审批完成后再进行调整！\n");
                        }
                    }
                }
                //不存在时更新项目 否则不更新
                if (result.isSuccess()) {
                    budgetAdjustmentDAO.addBudgetAdjustment(budgetAdjustment);
                    for (BudgetAdjustmentDetail budgetAdjustmentDetail : budgetAdjustmentDetails) {
                        budgetAdjustmentDetail.setBudgetAdjustId(budgetAdjustment.getBudgetId());
                        budgetAdjustmentDetail.setStatus("0");
                        BigDecimal df = new BigDecimal(budgetAdjustmentDetail.getAfterUnitPrice().toString());
                        budgetAdjustmentDetail.setAfterUnitPrice(df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        budgetAdjustmentDetailDAO.addBudgetAdjustmentDetail(budgetAdjustmentDetail);
                    }
                }

            }
        }
        return result;
    }

    @Override
    public List<BudgetAdjustment> exportBudgetAdjustmentList(BudgetAdjustment budgetAdjustment) {
        return budgetAdjustmentDAO.exportBudgetAdjustmentList(budgetAdjustment);
    }

    @Override
    public void deleteBudgetAdjustmentById(BudgetAdjustment budgetAdjustment) {
        budgetAdjustmentDAO.updateBudgetAdjustmentById(budgetAdjustment);
        budgetAdjustmentDetailDAO.updateBudgetAdjustmentDetailsById(budgetAdjustment);
    }

    @Override
    public ExecuteResult<BudgetAdjustment> bindApplyWorkFlow(BudgetAdjustment budgetAdjustment) {
        ExecuteResult<BudgetAdjustment> result = new ExecuteResult<BudgetAdjustment>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
            WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

            createNextFlowStepParam.setBillCode(budgetAdjustment.getDocument());
            createNextFlowStepParam.setBillTableName("TB_FA_BUDGET_ADJUST");
            createNextFlowStepParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            createNextFlowStepParam.setCreatorCode(budgetAdjustment.getUserCode());
            createNextFlowStepParam.setCreatorName(budgetAdjustment.getUserName());

            Calendar instance = Calendar.getInstance();
            instance.setTime(budgetAdjustment.getApplicationDate());
            createNextFlowStepParam.setCreateDate(instance);
            createNextFlowStepParam.setCApprovalId(WorkFlowCommon.generateUUID());
            createNextFlowStepParam.setIsEmail(budgetAdjustment.getIsMail());
            createNextFlowStepParam.setIsPhoneMessage(budgetAdjustment.getIsPhoneMessage());
            createNextFlowStepParam.setIdea(budgetAdjustment.getIdea());
            createNextFlowStepParam.setCCommitActivityId(null == budgetAdjustment.getActivityId() ? "" : budgetAdjustment.getActivityId());

            if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())
                    || ASSETBUDGETTYPE.ADDORREDUCTION.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                createNextFlowStepParam.setTypeCode("77047");
                createNextFlowStepParam.setCCostCenterCode(budgetAdjustment.getCostCenterCode());
                createNextFlowStepParam.setDeptCode(budgetAdjustment.getCallInDepartmentCode());
                createNextFlowStepParam.setDeptName(budgetAdjustment.getCallInDepartmentName());
            } else {
                createNextFlowStepParam.setTypeCode("77048");
                createNextFlowStepParam.setCCostCenterCode("");
                createNextFlowStepParam.setDeptCode(budgetAdjustment.getCallOutDepartmentCode());
                createNextFlowStepParam.setDeptName(budgetAdjustment.getCallOutDepartmentName());
            }

            createNextFlowStepParam.setFeeItemCode("");

            createNextFlowStepParam.setMoney(budgetAdjustment.getIamoney().toString());

            createNextFlowStep.setParam(createNextFlowStepParam);

            createNextFlowStep.setUserCode(budgetAdjustment.getUpdateBy());
            createNextFlowStep.setUserName(budgetAdjustment.getUpdateUserName());

            WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);

            if (StringUtils.isNotBlank(response.getErrMessage())) {
                result.addErrorMessage(response.getErrMessage());
                LOGGER.error("====" + response.getErrMessage() + "====");
                return result;
            }
        } catch (AxisFault axisFault) {
            ifRollback = true;
            result.addErrorMessage(axisFault.getMessage());
            LOGGER.error("axisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            result.addErrorMessage(e.getMessage());
            LOGGER.error("RemoteException", e);
        }
        if (ifRollback) {
            //result.addErrorMessage("调用审批流失败");
            return result;
        }

        result.setSuccessMessage("绑定成功");
        return result;
    }

    @Override
    public ExecuteResult<BudgetAdjustment> agreeWorkFlow(BudgetAdjustment budgetAdjustment) {
        ExecuteResult<BudgetAdjustment> result = new ExecuteResult<BudgetAdjustment>();
        boolean ifRollback = false;
        try {

            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(budgetAdjustment.getDocument());
            agreeFlowParam.setBillTableName("TB_FA_BUDGET_ADJUST");
            agreeFlowParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            agreeFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());
            //agreeFlowParam.setDeptCode(budgetAdjustment.getDepartmentCode());
            //agreeFlowParam.setDeptName(budgetAdjustment.getDepartmentName());
            agreeFlowParam.setCreatorCode(budgetAdjustment.getUserCode());
            agreeFlowParam.setCreatorName(budgetAdjustment.getUserName());

            if (null == budgetAdjustment.getCreateDate()) {
                result.addErrorMessage("创建时间不能为空");
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime(budgetAdjustment.getApplicationDate());
            agreeFlowParam.setCreateDate(instance);
            agreeFlowParam.setCUserCode(budgetAdjustment.getCaUserCode());
            agreeFlowParam.setCUserName(budgetAdjustment.getCaUserName());
            agreeFlowParam.setUserDepCode(budgetAdjustment.getUserDepCode());
            agreeFlowParam.setAMoney(budgetAdjustment.getIamoney().toString());
            // agreeFlowParam.setTypeCode("77047");
            agreeFlowParam.setMoney(budgetAdjustment.getIamoney().toString());
            agreeFlowParam.setIdea(budgetAdjustment.getIdea());
            agreeFlowParam.setIsEmail(budgetAdjustment.getIsMail());
            agreeFlowParam.setIsPhoneMessage(budgetAdjustment.getIsPhoneMessage());
            agreeFlowParam.setCCommitActivityId(null == budgetAdjustment.getActivityId() ? "" : budgetAdjustment.getActivityId());

            if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())
                    || ASSETBUDGETTYPE.ADDORREDUCTION.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                agreeFlowParam.setCCostCenterCode(budgetAdjustment.getCostCenterCode());
                agreeFlowParam.setDeptCode(budgetAdjustment.getCallInDepartmentCode());
                agreeFlowParam.setDeptName(budgetAdjustment.getCallInDepartmentName());
                agreeFlowParam.setTypeCode("77047");
            } else {
                agreeFlowParam.setCCostCenterCode("");
                agreeFlowParam.setDeptCode(budgetAdjustment.getCallOutDepartmentCode());
                agreeFlowParam.setDeptName(budgetAdjustment.getCallOutDepartmentName());
                agreeFlowParam.setTypeCode("77048");
            }

            agree.setUserCode(budgetAdjustment.getCaUserCode());
            agree.setUserName(budgetAdjustment.getCaUserName());

            agree.setParam(agreeFlowParam);

            WorkFlowStub.AgreeResponse agreeResponse = stub.agree(agree);
            if (StringUtils.isNotBlank(agreeResponse.getErrMessage())) {
                result.addErrorMessage(agreeResponse.getErrMessage());
                return result;
            }
        } catch (AxisFault axisFault) {
            ifRollback = true;
            LOGGER.error("axisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            LOGGER.error("RemoteException", e);
        }
        if (ifRollback) {
            result.addErrorMessage("调用审批流失败！");
            return result;
        }
        //// TODO: 2017/3/23 同意更新表
        /*budgetAdjustment=budgetAdjustmentDAO.getBudgetAdjustmentDocument(budgetAdjustment.getDocument());
        List<DiscardSellDetail> listDetail =budgetAdjustmentDAO.serachBudgetAdjustmentDetail(Integer.parseInt(String.valueOf(discardSell.getId())));
        if(APPROVESTATE.DONE.getApproveState().equals(budgetAdjustment.getApprovalState())){
            for (DiscardSellDetail discardSellDetail : listDetail) {
                long id=discardSellDetail.getGeneralAssetId();
                budgetAdjustmentDAO.updateCardStateById(id);
            }
        }*/
        result.setSuccessMessage("调用审批流成功！");
        return result;
    }

    @Override
    public ExecuteResult<BudgetAdjustment> denyWorkFlow(BudgetAdjustment budgetAdjustment) {
        ExecuteResult<BudgetAdjustment> executeResult = new ExecuteResult<BudgetAdjustment>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();

            backFlowParam.setBillCode(budgetAdjustment.getDocument());
            backFlowParam.setBillTableName("TB_FA_BUDGET_ADJUST");
            backFlowParam.setCreatorCode(budgetAdjustment.getUserCode());
            backFlowParam.setCreatorName(budgetAdjustment.getUserName());
            backFlowParam.setIdea(budgetAdjustment.getIdea());
            backFlowParam.setCUserCode(budgetAdjustment.getCaUserCode());
            backFlowParam.setCUserName(budgetAdjustment.getCaUserName());
            backFlowParam.setUserDepCode(budgetAdjustment.getUserDepCode());
            //backFlowParam.setTypeCode("77047");
            //backFlowParam.setActivityCode("");
            backFlowParam.setActivityCode(null == budgetAdjustment.getActivityId() ? "" : budgetAdjustment.getActivityId());

            if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())
                    || ASSETBUDGETTYPE.ADDORREDUCTION.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                backFlowParam.setTypeCode("77047");
            } else {
                backFlowParam.setTypeCode("77048");
            }

            backFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());

            backFlowParam.setIsEmail(budgetAdjustment.getIsMail());
            backFlowParam.setIsPhoneMessage(budgetAdjustment.getIsPhoneMessage());

            back.setParam(backFlowParam);

            back.setUserCode(budgetAdjustment.getCaUserCode());
            back.setUserName(budgetAdjustment.getCaUserName());

            WorkFlowStub.BackResponse backResponse = stub.back(back);
            if (StringUtils.isNotBlank(backResponse.getErrMessage())) {
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
    public ExecuteResult<BudgetAdjustment> dropWorkFlow(BudgetAdjustment budgetAdjustment) {

        ExecuteResult<BudgetAdjustment> executeResult = new ExecuteResult<BudgetAdjustment>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();

            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(budgetAdjustment.getDocument());
            unApprovalFlowParam.setBillTableName("TB_FA_BUDGET_ADJUST");
            unApprovalFlowParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            unApprovalFlowParam.setCreatorCode(budgetAdjustment.getUserCode());
            unApprovalFlowParam.setCreatorName(budgetAdjustment.getUserName());
            unApprovalFlowParam.setIdea(budgetAdjustment.getIdea());
            unApprovalFlowParam.setCUserCode(budgetAdjustment.getCaUserCode());
            unApprovalFlowParam.setCUserName(budgetAdjustment.getCaUserName());
            unApprovalFlowParam.setUserDepCode(budgetAdjustment.getUserDepCode());
            //unApprovalFlowParam.setTypeCode("77047");

            if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())
                    || ASSETBUDGETTYPE.ADDORREDUCTION.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                unApprovalFlowParam.setTypeCode("77047");
            } else {
                unApprovalFlowParam.setTypeCode("77048");
            }

            unApproval.setParam(unApprovalFlowParam);

            unApproval.setUserCode(budgetAdjustment.getCaUserCode());
            unApproval.setUserName(budgetAdjustment.getCaUserName());

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
        }

        if (ifRollback) {
            executeResult.addErrorMessage("调用审批流失败");
            return executeResult;
        }

        executeResult.setSuccessMessage("调用弃审审批流成功");

        return executeResult;
    }

    @Override
    public void deleteBudgetAdjustmentByDocument(String document) {
        budgetAdjustmentDetailDAO.deleteBudgetAdjustmentDetailByDocument(document);
        budgetAdjustmentDAO.deleteBudgetAdjustmentByDocument(document);
    }
}

