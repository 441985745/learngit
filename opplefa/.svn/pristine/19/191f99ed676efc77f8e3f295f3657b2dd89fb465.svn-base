package com.opple.fa.discardsell.service.impl;

import com.opple.fa.assetcard.dao.GeneralAssetDAO;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.entity.ISUSABLE;
import com.opple.fa.assetcard.entity.USESTATE;
import com.opple.fa.config.dao.AttchConfigDAO;
import com.opple.fa.discardsell.dao.DiscardSellDAO;
import com.opple.fa.discardsell.dao.DiscardSellDetailDAO;
import com.opple.fa.discardsell.entity.DISCARDORSELL;
import com.opple.fa.discardsell.entity.DiscardSell;
import com.opple.fa.discardsell.entity.DiscardSellDetail;
import com.opple.fa.discardsell.service.DiscardSellService;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.workflow.WorkFlowStub;
import com.opple.workflow.entity.APPROVESTATE;
import com.opple.workflow.entity.COMMITTYPE;
import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Getter;
import lombok.Setter;
import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DiscardSellServiceImpl implements DiscardSellService {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(DiscardSellServiceImpl.class);
    @Setter
    @Getter
    private DiscardSellDAO discardSellDAO;
    @Setter
    @Getter
    private DiscardSellDetailDAO discardSellDetailDAO;
    @Setter
    @Getter
    private GeneralAssetDAO generalAssetDAO;
    @Getter
    @Setter
    private AttchConfigDAO attchConfigDAO;

    @Override
    public Pager<DiscardSell> searchDiscardSell(Pager<DiscardSell> pager,
                                                DiscardSell discardSell) {
        List<DiscardSell> listDiscardSell = discardSellDAO.searchDiscardSell(
                pager, discardSell);
        Long count = discardSellDAO.searchDiscardSellCount(discardSell);
        return Pager.cloneFromPager(pager, count, listDiscardSell);
    }

    @Override
    public void insertDiscardSell(DiscardSell discardSell, String ids,
                                  String moneyList, String percentList) {

        discardSellDAO.insertDiscardSell(discardSell);
        long discardSellId = discardSell.getId();
        String[] assetCardId = ids.split(",");
        String[] money = moneyList.split(",");
        String[] percent = percentList.split(",");
        DiscardSellDetail discardSellDetail = new DiscardSellDetail();
        for (int i = 0; i < assetCardId.length; i++) {
            GeneralAsset getGeneralAsset = generalAssetDAO.getGeneralAsset(Integer.parseInt(assetCardId[i]));
            discardSellDetail.setAssetCode(getGeneralAsset.getAssetCode());
            if (null != getGeneralAsset.getAssetNetValue() && !"".equals(getGeneralAsset.getAssetNetValue())) {
                discardSellDetail.setAssetNetValue(new BigDecimal(String.valueOf(getGeneralAsset.getAssetNetValue())));
            }
            if (null != getGeneralAsset.getAssetOriginalValue() && !"".equals(getGeneralAsset.getAssetOriginalValue())) {
                discardSellDetail.setAssetOriginalValue(new BigDecimal(String.valueOf(getGeneralAsset.getAssetOriginalValue())));
            }
            discardSellDetail.setCompanyCode(getGeneralAsset.getCompanyCode());
            discardSellDetail.setCompanyName(getGeneralAsset.getCompanyName());
            discardSellDetail.setCostCenterCode(getGeneralAsset.getCostCenterCode());
            discardSellDetail.setCostCenterName(getGeneralAsset.getPersentDepartment());
            discardSellDetail.setLocation(getGeneralAsset.getLocation());
            discardSellDetail.setAssetName(getGeneralAsset.getAssetName());
            discardSellDetail.setAssetPerson(getGeneralAsset.getAssetPerson());
            discardSellDetail.setAssetPersonCode(getGeneralAsset.getAssetPersonCode());
            discardSellDetail.setCreateBy(discardSell.getCreateBy());
            discardSellDetail.setAssetType(getGeneralAsset.getAssetType());
            discardSellDetail.setUpdateBy(discardSell.getUpdateBy());
            discardSellDetail.setDiscardSellId(Integer.parseInt(String
                    .valueOf(discardSellId)));
            discardSellDetail.setDeletedFlag(ISUSABLE.AVAILABLE.isUsable());
            discardSellDetail.setGeneralAssetId(Integer
                    .parseInt(assetCardId[i]));
            if (null != money[i] && !"null".equals(money[i])) {
                discardSellDetail.setDiscardMoney(new BigDecimal(money[i]));
            } else {
                discardSellDetail.setDiscardMoney(new BigDecimal("0"));
            }
            discardSellDetail.setDiscardPercent(percent[i]);
            discardSellDetailDAO.insertDiscardSellDetail(discardSellDetail);
            GeneralAsset generalAsset = new GeneralAsset();
            generalAsset.setId(Long.parseLong(assetCardId[i]));
            if (discardSell.getCommitType().equals(
                    COMMITTYPE.SUBMIT.getCommitType())) {
                generalAsset.setIsDiscardOccupancy(ISUSABLE.UNAVAILABLE.isUsable());
                generalAsset.setUseState(USESTATE.DISCARDAPPLY.getUseState());
                generalAssetDAO.updateDiscardOccupancy(generalAsset);
                generalAssetDAO.updateCardStateById(generalAsset);
            }
            if (discardSell.getCommitType().equals(
                    COMMITTYPE.DRAFT.getCommitType())) {
                generalAsset.setIsDiscardOccupancy(ISUSABLE.AVAILABLE.isUsable());
                generalAsset.setUseState(USESTATE.INUSE.getUseState());
                generalAssetDAO.updateDiscardOccupancy(generalAsset);
            }
        }

    }

    @Override
    public void deleteDiscardSell(DiscardSell discardSell) {
        discardSellDAO.deleteDiscardSell(discardSell);
    }

    @Override
    public DiscardSell getDiscardSell(DiscardSell discardSell) {
        discardSell = discardSellDAO.getDiscardSell(discardSell);
        List<DiscardSellDetail> listDetail = discardSellDetailDAO
                .serachDiscardSellDetail(Integer.parseInt(String
                        .valueOf(discardSell.getId())));
        String ids = "";
        String assetId = "";

        for (DiscardSellDetail discardSellDetail : listDetail) {
            long id = discardSellDetail.getGeneralAssetId();

            if ("".equals(ids)) {
                assetId = Long.toString(id);
                ids = assetId;
            } else {
                assetId = Long.toString(id);
                ids = ids + "," + assetId;
            }
        }
        discardSell.setGeneralAssetIds(ids);
        discardSell.setListDiscardSellDetail(listDetail);
        return discardSell;
    }

    @Override
    public List<DiscardSell> serchDiscardExport(DiscardSell discardSell) {

        return discardSellDAO.serchDiscardExport(discardSell);
    }

    @Override
    public ExecuteResult<DiscardSell> updateDiscardell(DiscardSell discardSell, String ids,
                                                       String moneyList, String percentList) {
        DiscardSellDetail discardSellDetail = new DiscardSellDetail();
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();

        String[] money = moneyList.split(",");
        String[] percent = percentList.split(",");
        String errorMessage = "";
        discardSell.setNextHandlerCode("");
        discardSell.setNextHandlerName("");
        discardSellDetail.setDiscardSellId(Integer.parseInt(String
                .valueOf(discardSell.getId())));
        //获得此单据的所有的卡片id
        List<String> listAssetIds = discardSellDetailDAO.serachGeneralIds(discardSellDetail);
        List<String> newListAssetIds = new ArrayList<String>();
        discardSellDetailDAO.deleteDiscardSellDetail(discardSellDetail);
        discardSellDAO.updateDiscardell(discardSell);

        if (!"".equals(ids) && null != ids) {
            String[] assetId = ids.split(",");
            for (int i = 0; i < assetId.length; i++) {
                newListAssetIds.add(assetId[i]);
            }
        }
        //此次提交为submit 并且上次为submit
        if (discardSell.getCommitType().equals(
                COMMITTYPE.SUBMIT.getCommitType())
                && COMMITTYPE.SUBMIT.getCommitType().equals(
                discardSell.getOldCommitType())) {

            if (!"".equals(listAssetIds) && null != listAssetIds) {
                for (String string : listAssetIds) {
                    if (!newListAssetIds.contains(string)) {
                        GeneralAsset generalAsset = new GeneralAsset();
                        generalAsset.setId(Long.parseLong(string));
                        generalAsset.setUseState(USESTATE.INUSE.getUseState());
                        generalAsset.setIsDiscardOccupancy(ISUSABLE.AVAILABLE.isUsable());
                        generalAssetDAO.updateDiscardOccupancy(generalAsset);
                    }
                }
            }
        }
        //此次提交为submit 并且上次为draft
        if (discardSell.getCommitType().equals(
                COMMITTYPE.SUBMIT.getCommitType())
                && COMMITTYPE.DRAFT.getCommitType().equals(
                discardSell.getOldCommitType())) {
            if (!"".equals(newListAssetIds) && null != newListAssetIds && newListAssetIds.size() != 0) {
                for (String string : newListAssetIds) {
                    GeneralAsset getGeneralAsset = generalAssetDAO.getGeneralAsset(Integer.parseInt(string));
                    if (ISUSABLE.UNAVAILABLE.isUsable().equals(getGeneralAsset.getIsDiscardOccupancy())) {
                        if ("".equals(errorMessage)) {
                            errorMessage = getGeneralAsset.getAssetCode() + "[" + getGeneralAsset.getCompanyCode() + "]";
                        } else {
                            errorMessage = errorMessage + "," + getGeneralAsset.getAssetCode() + "[" + getGeneralAsset.getCompanyCode() + "]";
                        }
                    }
                }
            }
            if (!"".equals(errorMessage)) {
                result.addErrorMessage(errorMessage + "资产已被报废占用");
                return result;
            }
        }
        //此次提交为 draft 上次提交为submit
        if (discardSell.getCommitType().equals(
                COMMITTYPE.DRAFT.getCommitType())
                && COMMITTYPE.SUBMIT.getCommitType().equals(
                discardSell.getOldCommitType())) {
            if (!"".equals(listAssetIds) && null != listAssetIds && listAssetIds.size() != 0) {
                for (String string : listAssetIds) {
                    GeneralAsset generalAsset = new GeneralAsset();
                    generalAsset.setId(Long.parseLong(string));
                    generalAsset.setUseState(USESTATE.INUSE.getUseState());
                    generalAsset.setIsDiscardOccupancy(ISUSABLE.AVAILABLE.isUsable());
                    generalAssetDAO.updateDiscardOccupancy(generalAsset);
                }
            }
            if (!"".equals(newListAssetIds) && null != newListAssetIds && newListAssetIds.size() != 0) {
                for (String string : newListAssetIds) {
                    GeneralAsset getGeneralAsset = generalAssetDAO.getGeneralAsset(Integer.parseInt(string));
                    if (ISUSABLE.UNAVAILABLE.isUsable().equals(getGeneralAsset.getIsDiscardOccupancy())) {
                        if ("".equals(errorMessage)) {
                            errorMessage = getGeneralAsset.getAssetCode() + "[" + getGeneralAsset.getCompanyCode() + "]";
                        } else {
                            errorMessage = errorMessage + "," + getGeneralAsset.getAssetCode() + "[" + getGeneralAsset.getCompanyCode() + "]";
                        }
                    }
                }
            }
            if ("".equals(errorMessage)) {
                if (!"".equals(newListAssetIds) && null != newListAssetIds && newListAssetIds.size() != 0) {
                    for (String string : newListAssetIds) {
                        GeneralAsset generalAsset = new GeneralAsset();
                        generalAsset.setId(Long.parseLong(string));
                        generalAsset.setUseState(USESTATE.INUSE.getUseState());
                        generalAsset.setIsDiscardOccupancy(ISUSABLE.AVAILABLE.isUsable());
                        generalAssetDAO.updateDiscardOccupancy(generalAsset);
                    }
                }
                if (!"".equals(listAssetIds) && null != listAssetIds && listAssetIds.size() != 0) {
                    for (String string : listAssetIds) {
                        if (!newListAssetIds.contains(string)) {
                            GeneralAsset generalAsset = new GeneralAsset();
                            generalAsset.setId(Long.parseLong(string));
                            generalAsset.setUseState(USESTATE.INUSE.getUseState());
                            generalAsset.setIsDiscardOccupancy(ISUSABLE.AVAILABLE.isUsable());
                            generalAssetDAO.updateDiscardOccupancy(generalAsset);
                        }
                    }
                }

            } else {
                result.addErrorMessage(errorMessage + "资产已被报废占用");
                return result;
            }

        }
        // 此次提交为  draft 上次提交为draft
        if (discardSell.getCommitType().equals(
                COMMITTYPE.DRAFT.getCommitType())
                && COMMITTYPE.DRAFT.getCommitType().equals(
                discardSell.getOldCommitType())) {
            if (!"".equals(newListAssetIds) && null != newListAssetIds && newListAssetIds.size() != 0) {
                for (String string : newListAssetIds) {
                    GeneralAsset getGeneralAsset = generalAssetDAO.getGeneralAsset(Integer.parseInt(string));
                    if (ISUSABLE.UNAVAILABLE.isUsable().equals(getGeneralAsset.getIsDiscardOccupancy())) {
                        if ("".equals(errorMessage)) {
                            errorMessage = getGeneralAsset.getAssetCode() + "[" + getGeneralAsset.getCompanyCode() + "]";
                        } else {
                            errorMessage = errorMessage + "," + getGeneralAsset.getAssetCode() + "[" + getGeneralAsset.getCompanyCode() + "]";
                        }
                    }
                }
            }
            if ("".equals(errorMessage)) {
                if (!"".equals(newListAssetIds) && null != newListAssetIds && newListAssetIds.size() != 0) {
                    for (String string : newListAssetIds) {
                        GeneralAsset generalAsset = new GeneralAsset();
                        generalAsset.setId(Long.parseLong(string));
                        generalAsset.setUseState(USESTATE.INUSE.getUseState());
                        generalAsset.setIsDiscardOccupancy(ISUSABLE.AVAILABLE.isUsable());
                        generalAssetDAO.updateDiscardOccupancy(generalAsset);
                    }
                }
                if (!"".equals(listAssetIds) && null != listAssetIds && listAssetIds.size() != 0) {
                    for (String string : listAssetIds) {
                        if (!newListAssetIds.contains(string)) {
                            GeneralAsset generalAsset = new GeneralAsset();
                            generalAsset.setId(Long.parseLong(string));
                            generalAsset.setUseState(USESTATE.INUSE.getUseState());
                            generalAsset.setIsDiscardOccupancy(ISUSABLE.AVAILABLE.isUsable());
                            generalAssetDAO.updateDiscardOccupancy(generalAsset);
                        }
                    }
                }
            } else {
                result.addErrorMessage(errorMessage + "资产已被报废占用");
                return result;
            }
        }
        if (!"".equals(ids) && null != ids) {
            String[] assetId = ids.split(",");
            for (int i = 0; i < assetId.length; i++) {
                //将传进来的id 放进string 数组  方便比较

                GeneralAsset getGeneralAsset = generalAssetDAO.getGeneralAsset(Integer.parseInt(assetId[i]));
                discardSellDetail.setAssetCode(getGeneralAsset.getAssetCode());
                if (null != getGeneralAsset.getAssetNetValue() && !"".equals(getGeneralAsset.getAssetNetValue())) {
                    discardSellDetail.setAssetNetValue(new BigDecimal(String.valueOf(getGeneralAsset.getAssetNetValue())));
                }
                if (null != getGeneralAsset.getAssetOriginalValue() && !"".equals(getGeneralAsset.getAssetOriginalValue())) {
                    discardSellDetail.setAssetOriginalValue(new BigDecimal(String.valueOf(getGeneralAsset.getAssetOriginalValue())));
                }
                discardSellDetail.setCompanyCode(getGeneralAsset.getCompanyCode());
                discardSellDetail.setCompanyName(getGeneralAsset.getCompanyName());
                discardSellDetail.setCostCenterCode(getGeneralAsset.getCostCenterCode());
                discardSellDetail.setCostCenterName(getGeneralAsset.getPersentDepartment());
                discardSellDetail.setLocation(getGeneralAsset.getLocation());
                discardSellDetail.setAssetName(getGeneralAsset.getAssetName());
                discardSellDetail.setAssetPerson(getGeneralAsset.getAssetPerson());
                discardSellDetail.setAssetPersonCode(getGeneralAsset.getAssetPersonCode());
                discardSellDetail.setAssetType(getGeneralAsset.getAssetType());
                discardSellDetail.setDiscardSellId(Integer.parseInt(String
                        .valueOf(discardSell.getId())));
                discardSellDetail.setGeneralAssetId(Integer.parseInt(assetId[i]));
                if (null != money[i] && !"null".equals(money[i])) {
                    discardSellDetail.setDiscardMoney(new BigDecimal(String.valueOf(money[i])));
                } else {
                    discardSellDetail.setDiscardMoney(new BigDecimal("0"));
                }
                discardSellDetail.setDiscardPercent(percent[i]);
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setId(Long.parseLong(assetId[i]));
                if (discardSell.getCommitType().equals(
                        COMMITTYPE.SUBMIT.getCommitType())) {
                    generalAsset.setIsDiscardOccupancy(ISUSABLE.UNAVAILABLE.isUsable());
                    generalAsset.setUseState(USESTATE.DISCARDAPPLY.getUseState());
                    generalAssetDAO.updateDiscardOccupancy(generalAsset);
                }
                discardSellDetailDAO.insertDiscardSellDetail(discardSellDetail);
            }
        }

        return result;
    }

    @Override
    public ExecuteResult<DiscardSell> bindApplyWorkFlow(DiscardSell discardSell) {
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
            WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();
            // DiscardSell
            // discardSellBack=discardSellDAO.getDiscardSellByDocument(discardSell.getDocument());
            createNextFlowStepParam.setBillCode(discardSell.getDocument());
            createNextFlowStepParam.setBillTableName(Env.getProperty("discard.info.table"));
            createNextFlowStepParam
                    .setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            createNextFlowStepParam.setDeptCode(discardSell
                    .getSubordinateDepartmentCode());
            createNextFlowStepParam.setDeptName(discardSell
                    .getSubordinateDepartment());
            createNextFlowStepParam.setCreatorCode(discardSell.getUserCode());
            createNextFlowStepParam.setCreatorName(discardSell.getUserName());
            Calendar instance = Calendar.getInstance();
            instance.setTime(discardSell.getCreateDate());

            createNextFlowStepParam.setCreateDate(instance);
            createNextFlowStepParam.setCApprovalId(WorkFlowCommon
                    .generateUUID());
            createNextFlowStepParam.setTypeCode(Env.getProperty("discard.info.type.id"));
            createNextFlowStepParam.setIsEmail(discardSell.getIsMail());
            createNextFlowStepParam.setIsPhoneMessage(discardSell.getIsPhoneMessage());
            createNextFlowStepParam.setFeeItemCode("");
            if (discardSell.getIdea() != null && !"null".equals(discardSell.getIdea())) {
                createNextFlowStepParam.setIdea(discardSell.getIdea());
            } else {
                createNextFlowStepParam.setIdea("");
            }
            createNextFlowStepParam.setCCostCenterCode(discardSell
                    .getCostCenterCode());
            createNextFlowStepParam.setCCommitActivityId(discardSell
                    .getActivityId());
            createNextFlowStepParam.setMoney(String.valueOf(discardSell
                    .getIaMoeny()));

            createNextFlowStep.setParam(createNextFlowStepParam);

            createNextFlowStep.setUserCode(discardSell.getUserCode());
            createNextFlowStep.setUserName(discardSell.getUserName());

            WorkFlowStub.CreateNextFlowStepResponse response = stub
                    .createNextFlowStep(createNextFlowStep);

            if (StringUtils.isNotBlank(response.getErrMessage())) {
                ifRollback = true;
                result.addErrorMessage(response.getErrMessage());
                LOGGER.error("====" + response.getErrMessage() + "====");
            }
        } catch (AxisFault axisFault) {
            ifRollback = true;
            LOGGER.error("axisFault", axisFault);
        } catch (RemoteException e) {
            ifRollback = true;
            LOGGER.error("RemoteException", e);
        }
        if (ifRollback) {
            result.addErrorMessage("调用审批流失败");
            return result;
        }
        discardSell = discardSellDAO.getDiscardSellByDocument(discardSell
                .getDocument());
        List<DiscardSellDetail> listDetail = discardSellDetailDAO
                .serachDiscardSellDetail(Integer.parseInt(String
                        .valueOf(discardSell.getId())));
        for (DiscardSellDetail discardSellDetail : listDetail) {
            long id = discardSellDetail.getGeneralAssetId();
            GeneralAsset generalAsset = new GeneralAsset();
            generalAsset.setId(id);
            generalAsset.setUseState(USESTATE.DISCARDAPPLY.getUseState());
            generalAssetDAO.updateCardStateById(generalAsset);
        }
        result.setSuccessMessage("绑定成功");
        return result;
    }

    @Override
    public ExecuteResult<DiscardSell> agreeWorkFlow(DiscardSell discardSell) {
        ExecuteResult<DiscardSell> result = new ExecuteResult<DiscardSell>();
        boolean ifRollback = false;
        try {

            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(discardSell.getDocument());
            agreeFlowParam.setBillTableName(Env.getProperty("discard.info.table"));
            agreeFlowParam
                    .setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            agreeFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());
            agreeFlowParam.setDeptCode(discardSell
                    .getSubordinateDepartmentCode());
            agreeFlowParam.setDeptName(discardSell.getSubordinateDepartment());
            agreeFlowParam.setCreatorCode(discardSell.getUserCode());
            agreeFlowParam.setCreatorName(discardSell.getUserName());

            if (null == discardSell.getCreateDate()) {
                result.addErrorMessage("创建时间不能为空");
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime(discardSell.getCreateDate());
            agreeFlowParam.setCreateDate(instance);
            agreeFlowParam.setCUserCode(discardSell.getCaUserCode());
            agreeFlowParam.setCUserName(discardSell.getCaUserName());
            agreeFlowParam.setUserDepCode(discardSell.getUserDepCode());
            agreeFlowParam.setAMoney(String.valueOf(discardSell
                    .getDiscardMoneySum()));
            agreeFlowParam.setTypeCode(Env.getProperty("discard.info.type.id"));
            agreeFlowParam.setMoney(String.valueOf(discardSell
                    .getDiscardMoneySum()));
            if (discardSell.getIdea() != null && !"".equals(discardSell.getIdea())) {
                agreeFlowParam.setIdea(discardSell.getIdea());
            } else {
                agreeFlowParam.setIdea("同意");
            }
            agreeFlowParam.setIsEmail(discardSell.getIsMail());
            agreeFlowParam.setIsPhoneMessage(discardSell.getIsPhoneMessage());
            agreeFlowParam.setCCostCenterCode(discardSell.getCostCenterCode());
            agreeFlowParam.setCCommitActivityId(discardSell.getActivityId());
            agree.setUserCode(discardSell.getCaUserCode());
            agree.setUserName(discardSell.getCaUserName());

            agree.setParam(agreeFlowParam);

            WorkFlowStub.AgreeResponse agreeResponse = stub.agree(agree);
            if (StringUtils.isNotBlank(agreeResponse.getErrMessage())) {
                ifRollback = true;
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
        discardSell = discardSellDAO.getDiscardSellByDocument(discardSell
                .getDocument());
        List<DiscardSellDetail> listDetail = discardSellDetailDAO
                .serachDiscardSellDetail(Integer.parseInt(String
                        .valueOf(discardSell.getId())));

        if (APPROVESTATE.PROCESSING.getApproveState().equals(
                discardSell.getApprovalState())) {
            for (DiscardSellDetail discardSellDetail : listDetail) {
                long id = discardSellDetail.getGeneralAssetId();
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setId(id);

                if (DISCARDORSELL.SELL.getDiscardOrSell().equals(discardSell.getDiscardOrSell())) {
                    generalAsset.setUseState(USESTATE.SELLAPPLY.getUseState());
                }
                if (DISCARDORSELL.DISCARD.getDiscardOrSell().equals(discardSell.getDiscardOrSell())) {
                    generalAsset.setUseState(USESTATE.DISCARDAPPLY.getUseState());
                }
                generalAssetDAO.updateCardStateById(generalAsset);
            }
        }
        if (APPROVESTATE.DONE.getApproveState().equals(
                discardSell.getApprovalState())) {
            for (DiscardSellDetail discardSellDetail : listDetail) {
                long id = discardSellDetail.getGeneralAssetId();
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setId(id);
                if (DISCARDORSELL.SELL.getDiscardOrSell().equals(discardSell.getDiscardOrSell())) {
                    generalAsset.setUseState(USESTATE.ALREADYSOLD.getUseState());
                }
                if (DISCARDORSELL.DISCARD.getDiscardOrSell().equals(discardSell.getDiscardOrSell())) {
                    generalAsset.setUseState(USESTATE.SCRAPPED.getUseState());
                }
                generalAssetDAO.updateCardStateById(generalAsset);
            }
        }
        result.setSuccessMessage("调用审批流成功！");
        return result;
    }

    @Override
    public ExecuteResult<DiscardSell> denyWorkFlow(DiscardSell discardSell) {
        ExecuteResult<DiscardSell> executeResult = new ExecuteResult<DiscardSell>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();

            backFlowParam.setBillCode(discardSell.getDocument());
            backFlowParam.setBillTableName(Env.getProperty("discard.info.table"));
            backFlowParam.setCreatorCode(discardSell.getUserCode());
            backFlowParam.setCreatorName(discardSell.getUserName());
            backFlowParam.setIdea(discardSell.getIdea());
            backFlowParam.setCUserCode(discardSell.getCaUserCode());
            backFlowParam.setCUserName(discardSell.getCaUserName());
            backFlowParam.setUserDepCode(discardSell.getUserDepCode());
            backFlowParam.setTypeCode(Env.getProperty("discard.info.type.id"));
            backFlowParam.setActivityCode(discardSell.getActivityId());
            backFlowParam.setIsEmail(discardSell.getIsMail());
            backFlowParam.setIsPhoneMessage(discardSell.getIsPhoneMessage());
            backFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());

            backFlowParam.setIsEmail(discardSell.getIsMail());
            backFlowParam.setIsPhoneMessage(discardSell.getIsPhoneMessage());

            back.setParam(backFlowParam);

            back.setUserCode(discardSell.getCaUserCode());
            back.setUserName(discardSell.getCaUserName());

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
    public ExecuteResult<DiscardSell> dropWorkFlow(DiscardSell discardSell) {

        ExecuteResult<DiscardSell> executeResult = new ExecuteResult<DiscardSell>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();

            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(discardSell.getDocument());
            unApprovalFlowParam.setBillTableName(Env.getProperty("discard.info.table"));
            unApprovalFlowParam
                    .setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            unApprovalFlowParam.setCreatorCode(discardSell.getUserCode());
            unApprovalFlowParam.setCreatorName(discardSell.getUserName());
            unApprovalFlowParam.setIdea(discardSell.getIdea());
            unApprovalFlowParam.setCUserCode(discardSell.getCaUserCode());
            unApprovalFlowParam.setCUserName(discardSell.getCaUserName());
            unApprovalFlowParam.setUserDepCode(discardSell.getUserDepCode());
            unApprovalFlowParam.setTypeCode(Env.getProperty("discard.info.type.id"));

            unApproval.setParam(unApprovalFlowParam);

            unApproval.setUserCode(discardSell.getCaUserCode());
            unApproval.setUserName(discardSell.getCaUserName());

            WorkFlowStub.UnApprovalResponse unApprovalResponse = stub
                    .unApproval(unApproval);

            if (StringUtils.isNotBlank(unApprovalResponse.getErrMessage())) {
                executeResult.addErrorMessage(unApprovalResponse
                        .getErrMessage());
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
    public boolean getIsLastApprovalStep(DiscardSell discardSell) {
        boolean bool = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.GetIsLastApprovalStep isLast = new WorkFlowStub.GetIsLastApprovalStep();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(discardSell.getDocument());
            agreeFlowParam
                    .setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            agreeFlowParam.setBillTableName(Env.getProperty("discard.info.table"));
            agreeFlowParam.setCUserCode(discardSell.getLoginUseCode());
            agreeFlowParam.setCUserName(discardSell.getLoginUseName());
            agreeFlowParam.setIdea(discardSell.getIdea());
            agreeFlowParam.setAMoney("0");
            agreeFlowParam.setUserDepCode(discardSell.getLoginDepCode());
            agreeFlowParam.setTypeCode(Env.getProperty("discard.info.type.id"));

            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            agreeFlowParam.setCreateDate(instance);

            isLast.setParam(agreeFlowParam);

            WorkFlowStub.GetIsLastApprovalStepResponse isLastResponse = stub
                    .getIsLastApprovalStep(isLast);

            bool = isLastResponse.getGetIsLastApprovalStepResult();
        } catch (AxisFault axisFault) {
            LOGGER.error("AxisFault", axisFault);
        } catch (RemoteException e) {
            LOGGER.error("RemoteException", e);
        }
        return bool;
    }

    @Override
    public void deleteDiscardSellByDocument(String document) {
        discardSellDAO.deleteDiscardSellByDocument(document);
    }

    @Override
    public void updateDiscardellByDocument(DiscardSell discardSell) {

        discardSellDAO.updateDiscardellByDocument(discardSell);
    }

    @Override
    public DiscardSell getDiscardSellByDocument(String document) {

        return discardSellDAO.getDiscardSellByDocument(document);
    }

    @Override
    public void convertCommitTypeToOfficial(String document) {
        discardSellDAO.convertCommitTypeToOfficial(document);
    }

    @Override
    public void updateDiscardellBackByDoucment(DiscardSell discardSell) {
        discardSellDAO.updateDiscardellBackByDoucment(discardSell);

    }

    @Override
    public void updateDiscardByApprove(DiscardSell discardSell) {
        discardSellDAO.updateDiscardByApprove(discardSell);

    }

    @Override
    public void updateDiscardByListIds(String ids) {
        String[] idList = ids.split(",");
        for (String string : idList) {
            DiscardSellDetail discardSellDetail = new DiscardSellDetail();
            GeneralAsset generalAsset = new GeneralAsset();
            DiscardSell discardSell = new DiscardSell();
            DiscardSell disSell = discardSellDAO
                    .getDiscardSellByDocument(string);
            if (null != disSell) {
                discardSellDetail.setDiscardSellId(Long.parseLong(String
                        .valueOf(disSell.getId())));
            }

            List<DiscardSellDetail> listDetail = discardSellDetailDAO
                    .serachDiscardSellDetailBydiscardSellId(discardSellDetail);
            for (DiscardSellDetail discardSellDetail2 : listDetail) {
                generalAsset.setIsDiscardOccupancy(ISUSABLE.AVAILABLE.isUsable());
                generalAsset.setUseState(USESTATE.INUSE.getUseState());
                generalAsset.setId(discardSellDetail2.getGeneralAssetId());
                generalAssetDAO.updateDiscardOccupancy(generalAsset);
                discardSellDetailDAO
                        .deleteDiscardSellDetail(discardSellDetail2);
            }
            discardSell.setId(Long.parseLong(String.valueOf(disSell.getId())));
            discardSellDAO.deleteDiscardSell(discardSell);

        }
    }

    @Override
    public void updateIsSap(DiscardSell discardSell) {
        discardSellDAO.updateIsSap(discardSell);

    }

    @Override
    public void updateStampStatus(DiscardSell discardSell) {
        discardSellDAO.updateStampStatus(discardSell);

    }

}
