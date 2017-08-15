package com.opple.fa.allocation.service.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opple.fa.allocation.dao.AlloctionApproveDAO;
import com.opple.fa.allocation.dao.AssetAllocationDAO;
import com.opple.fa.allocation.dao.AssetAllocationDetailDAO;
import com.opple.fa.allocation.dao.SapCostCenterPersonDAO;
import com.opple.fa.allocation.entity.AlloctionApprove;
import com.opple.fa.allocation.entity.AssetAllocation;
import com.opple.fa.allocation.entity.AssetAllocationDetail;
import com.opple.fa.allocation.entity.CONFIRMSTATUS;
import com.opple.fa.allocation.entity.ISCONFIRM;
import com.opple.fa.allocation.entity.SapCostCenterPerson;
import com.opple.fa.allocation.service.AssetAllocationService;
import com.opple.fa.assetcard.dao.GeneralAssetDAO;
import com.opple.fa.assetcard.entity.ASSETTYPE;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcard.entity.ISUSABLE;
import com.opple.fa.config.dao.AttchConfigDAO;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.util.Env;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.workflow.WorkFlowStub;
import com.opple.workflow.entity.APPROVESTATE;
import com.opple.workflow.entity.COMMITTYPE;
import com.opple.workflow.entity.WorkFlowCommon;


public class AssetAllocationServiceImpl implements AssetAllocationService {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(AssetAllocationServiceImpl.class);
    @Getter
    @Setter
    private AssetAllocationDAO assetAllocationDAO;

    @Getter
    @Setter
    private AssetAllocationDetailDAO assetAllocationDetailDAO;

    @Getter
    @Setter
    private GeneralAssetDAO generalAssetDAO;
    @Getter
    @Setter
    private SapCostCenterPersonDAO sapCostCenterPersonDAO;
    @Getter
    @Setter
    private AlloctionApproveDAO alloctionApproveDAO;
    @Getter
    @Setter
    private AttchConfigDAO attchConfigDAO;

    @Override
    public Pager<AssetAllocation> searchAssetAllocations(
            Pager<AssetAllocation> pager, AssetAllocation assetAllocation) {
        List<AssetAllocation> assetAllocationList = assetAllocationDAO
                .searchAssetAllocations(pager, assetAllocation);
        Long assetAllocationCount = assetAllocationDAO
                .searchAssetAllocationCount(assetAllocation);

        pager.setRecords(assetAllocationList); // 设置数据
        pager.setTotal(assetAllocationCount); // 设置总页数

        return Pager.cloneFromPager(pager, assetAllocationCount,
                assetAllocationList);
    }

    @Override
    public Pager<AssetAllocation> searchAssetAllocationList(
            Pager<AssetAllocation> pager) {
        List<AssetAllocation> assetAllocationList = assetAllocationDAO
                .searchAssetAllocationList(pager);
        Long assetAllocationCount = assetAllocationDAO
                .searchAssetAllocationListCount();

        pager.setRecords(assetAllocationList); // 设置数据
        pager.setTotal(assetAllocationCount); // 设置总页数

        return Pager.cloneFromPager(pager, assetAllocationCount,
                assetAllocationList);
    }

    @Override
    public ExecuteResult<AssetAllocation> saveApply(
            AssetAllocation assetAllocation, String sublist,
            BigDecimal originalValueSum, BigDecimal netValueSum, String cause) {

        ExecuteResult<AssetAllocation> executeResult = new ExecuteResult<AssetAllocation>();
        String document = assetAllocation.getDocument();
        long count = assetAllocationDAO.getCountByDocument(document);
        if (count > 0) {
            executeResult.addErrorMessage("此单号已存在，请勿重复添加！");
            return executeResult;
        }
        assetAllocation.setAllocationAssetsValueSum(netValueSum);
        assetAllocation.setOriginalValueSum(originalValueSum);
        assetAllocation.setAllocationReason(cause);
        assetAllocation.setStatus(ISUSABLE.AVAILABLE.isUsable());
        String[] generalAssetId = sublist.split(",");
        if (assetAllocation.getCommitType().equals(COMMITTYPE.SUBMIT.getCommitType())) {
            List<String> listCostCenterCode = new ArrayList<String>();
            for (String string : generalAssetId) {
                GeneralAsset generalAsset = generalAssetDAO.getGeneralAsset(Integer
                        .parseInt(string));
                listCostCenterCode.add(generalAsset.getCostCenterCode());
            }
            String costCode = "";
            String costName = "";
            List<SapCostCenterPerson> listPerson = sapCostCenterPersonDAO
                    .serachSapCostCenterPerson(listCostCenterCode);
            for (SapCostCenterPerson sapCostCenterPerson : listPerson) {
                if ("".equals(costCode)) {
                    costCode = sapCostCenterPerson.getHead();
                } else {
                    costCode = costCode + "," + sapCostCenterPerson.getHead();
                }
                if ("".equals(costName)) {
                    costName = sapCostCenterPerson.getHeadName();
                } else {
                    costName = costName + "," + sapCostCenterPerson.getHeadName();
                }
                AlloctionApprove alloctionApprove = new AlloctionApprove();
                alloctionApprove.setAllocationDocument(assetAllocation
                        .getDocument());
                alloctionApprove.setManagerCode(sapCostCenterPerson.getHead());
                alloctionApprove.setManagerName(sapCostCenterPerson.getHeadName());
                alloctionApprove.setIsConfirm(ISCONFIRM.UNCONFIRMED.isConfirm());
                alloctionApproveDAO.insertAllocationApprove(alloctionApprove);

            }
            assetAllocation.setNextHandlerCode(costCode);
            assetAllocation.setNextHandlerName(costName);
            assetAllocation.setConfirmPerson(costName);

            AttchConfig attchConfig = new AttchConfig();
            attchConfig.setCostcenterCode(assetAllocation.getPostCostcenterCode());
            if (ASSETTYPE.ITASSET.getAssetType().equals(
                    assetAllocation.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTITASSET.getAssetType());
            }
            if (ASSETTYPE.ADMINISTRATIVE.getAssetType().equals(
                    assetAllocation.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTADMINISTRATIVE
                        .getAssetType());
            }
            if (ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType().equals(
                    assetAllocation.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTMEASURINGINSTRUMENTS
                        .getAssetType());
            }
            if (ASSETTYPE.MACHINERYEQUIPMENT.getAssetType().equals(
                    assetAllocation.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTMACHINERYEQUIPMENT
                        .getAssetType());
            }
            AttchConfig attchConfig2 = attchConfigDAO
                    .searchByCostcenterCodeAndAssetType(attchConfig);
            if (null != attchConfig2) {
                assetAllocation.setPutManageCode(attchConfig2
                        .getAttachDepartManagerCode());
                assetAllocation.setPutManagePersonCode(attchConfig2
                        .getAttachDepartAdminCode());
            } else {
                executeResult.addErrorMessage("查询不到此部门对应的归口负责人");
                return executeResult;
            }
        }

        assetAllocationDAO.saveApply(assetAllocation);
        if (assetAllocation.getCommitType().equals(
                COMMITTYPE.SUBMIT.getCommitType())) {
            String[] assetIds = sublist.split(",");
            GeneralAsset generalAsset = new GeneralAsset();
            for (String string : assetIds) {
                generalAsset.setId(Long.parseLong(string));
                generalAsset.setIsAllocationOccupancy(ISUSABLE.UNAVAILABLE.isUsable());
                generalAssetDAO.updateAllocationOccupancy(generalAsset);
            }
        }
        Long i = assetAllocation.getId();
        saveAllocationDetail(sublist, i);
        return executeResult;
    }

    @Override
    public ExecuteResult<AssetAllocation> deleteAssetAllocation(
            AssetAllocation assetAllocation) {

        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        long id = assetAllocation.getId();
        int ids = (int) id;
        AssetAllocation aa = assetAllocationDAO.searchAssetAllocationById(ids);
        String approvalState = aa.getApprovalState();
        if (approvalState.equals(APPROVESTATE.DENY.getApproveState())) {
            assetAllocationDAO.deleteAssetAllocation(assetAllocation);

        } else {
            result.addErrorMessage("待审批数据不能删");
        }
        return result;
    }

    @Override
    public void saveAllocationDetail(String sublist, long id) {

        String[] splitsublist = sublist.split(",");

        AssetAllocationDetail assetAllocationDetail = new AssetAllocationDetail();
        for (int i = 0; i < splitsublist.length; i++) {
            String ids = splitsublist[i];
            if (null != ids && !"".equals(ids)) {
                int n = Integer.parseInt(ids);
                GeneralAsset generalAsset = generalAssetDAO.getGeneralAsset(n);
                assetAllocationDetail.setAssetCode(generalAsset.getAssetCode());
                assetAllocationDetail.setAssetName(generalAsset.getAssetName());
                assetAllocationDetail.setPutUnderManageCode(generalAsset
                        .getPutUnderManageCode());
                assetAllocationDetail.setAssetPerson(generalAsset
                        .getAssetPerson());
                assetAllocationDetail.setCompanyName(generalAsset
                        .getCompanyName());
                assetAllocationDetail.setSpecificationModel(generalAsset
                        .getSpecificationModel());
                assetAllocationDetail.setAssetOriginalValue(generalAsset
                        .getAssetOriginalValue());
                assetAllocationDetail.setAssetNetValue(generalAsset
                        .getAssetNetValue());
                assetAllocationDetail.setAssetState(generalAsset
                        .getAssetState());
                assetAllocationDetail.setUseState(generalAsset.getUseState());
                assetAllocationDetail.setPersentDepartment(generalAsset
                        .getPersentDepartment());
                assetAllocationDetail.setCostCenterCode(generalAsset.getCostCenterCode());
                assetAllocationDetail.setCompanyCode(generalAsset.getCompanyCode());
                assetAllocationDetail.setLocation(generalAsset.getLocation());
                assetAllocationDetail.setAssetAllocationId(Integer
                        .parseInt(String.valueOf(id)));
                assetAllocationDetail.setGeneralAssetId(n);
                assetAllocationDetail.setDeletedFlag(ISUSABLE.AVAILABLE.isUsable());
                assetAllocationDetail.setAssetType(generalAsset.getAssetType());

            }
            assetAllocationDetailDAO.saveApply(assetAllocationDetail);
        }

    }

    @Override
    public AssetAllocation searchAssetAllocationById(Integer assetAllocationId) {
        AssetAllocation assetAllocation = assetAllocationDAO
                .searchAssetAllocationById(assetAllocationId);
        List<AssetAllocationDetail> assetAllocationDetailList = assetAllocationDetailDAO
                .searchAssetAllocationDetailById(assetAllocationId);
        List<GeneralAsset> generalAssetList = new ArrayList<GeneralAsset>();
        for (AssetAllocationDetail assetAllocationDetail : assetAllocationDetailList) {
            long id = assetAllocationDetail.getGeneralAssetId();
            GeneralAsset generalAsset = generalAssetDAO
                    .searchGeneralAssetInfo((int) id);
            if (null != generalAsset) {
                generalAsset.setNewAssetCode(assetAllocationDetail
                        .getNewAssetCode());
                generalAssetList.add(generalAsset);
            }
        }
        assetAllocation.setAssetAllocationDetail(assetAllocationDetailList);
        assetAllocation.setGeneralAsset(generalAssetList);
        return assetAllocation;
    }

    @Override
    public AssetAllocation getAssetAllocationByTask(
            AssetAllocation assetAllocation) {

        assetAllocation = assetAllocationDAO
                .getOneAssetAllocation(assetAllocation);
        List<AssetAllocationDetail> assetAllocationDetailList = assetAllocationDetailDAO
                .searchAssetAllocationDetailById(Integer.parseInt(String
                        .valueOf(assetAllocation.getId())));
        List<GeneralAsset> generalAssetList = new ArrayList<GeneralAsset>();
        for (AssetAllocationDetail assetAllocationDetail : assetAllocationDetailList) {
            long id = assetAllocationDetail.getGeneralAssetId();
            GeneralAsset generalAsset = generalAssetDAO
                    .searchGeneralAssetInfo((int) id);
            generalAsset.setNewAssetCode(assetAllocationDetail
                    .getNewAssetCode());
            generalAssetList.add(generalAsset);
        }
        assetAllocation.setAssetAllocationDetail(assetAllocationDetailList);
        assetAllocation.setGeneralAsset(generalAssetList);
        return assetAllocation;
    }

    @Override
    public ExecuteResult<AssetAllocation> updateAssetAllocation(
            AssetAllocation assetAllocation, String ids) {
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        String[] generalAssetId = ids.split(",");
        String errorMessage = "";
        List<String> listGeneralId = assetAllocationDetailDAO.serachAssetCardIds(Integer.parseInt(String.valueOf(assetAllocation.getId())));

        if (assetAllocation.getCommitType().equals(COMMITTYPE.SUBMIT.getCommitType())) {
            List<String> listCostCenterCode = new ArrayList<String>();

            for (String string : generalAssetId) {
                GeneralAsset generalAsset = generalAssetDAO.getGeneralAsset(Integer.parseInt(string));
                listCostCenterCode.add(generalAsset.getCostCenterCode());
            }
            String costCode = "";
            String costName = "";
            List<SapCostCenterPerson> listPerson = sapCostCenterPersonDAO.serachSapCostCenterPerson(listCostCenterCode);
            alloctionApproveDAO.deleteAllocationApprove(assetAllocation.getDocument());
            for (SapCostCenterPerson sapCostCenterPerson : listPerson) {
                if ("".equals(costCode)) {
                    costCode = sapCostCenterPerson.getHead();
                } else {
                    costCode = costCode + "," + sapCostCenterPerson.getHead();
                }
                if ("".equals(costName)) {
                    costName = sapCostCenterPerson.getHeadName();
                } else {
                    costName = costName + "," + sapCostCenterPerson.getHeadName();
                }
                AlloctionApprove alloctionApprove = new AlloctionApprove();
                alloctionApprove.setAllocationDocument(assetAllocation.getDocument());
                alloctionApprove.setManagerCode(sapCostCenterPerson.getHead());
                alloctionApprove.setManagerName(sapCostCenterPerson.getHeadName());
                alloctionApprove.setIsConfirm(ISCONFIRM.UNCONFIRMED.isConfirm());
                alloctionApproveDAO.insertAllocationApprove(alloctionApprove);

            }
            assetAllocation.setNextHandlerCode(costCode);
            assetAllocation.setNextHandlerName(costName);
            assetAllocation.setConfirmPerson(costName);
            assetAllocation.setConfirmStatus(CONFIRMSTATUS.WAITINGCONFIRMATION.getConfirmStatus());
            AttchConfig attchConfig = new AttchConfig();
            attchConfig.setCostcenterCode(assetAllocation.getPostCostcenterCode());
            if (ASSETTYPE.ITASSET.getAssetType().equals(assetAllocation.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTITASSET.getAssetType());
            }
            if (ASSETTYPE.ADMINISTRATIVE.getAssetType().equals(assetAllocation.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTADMINISTRATIVE.getAssetType());
            }
            if (ASSETTYPE.MEASURINGINSTRUMENTS.getAssetType().equals(assetAllocation.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTMEASURINGINSTRUMENTS.getAssetType());
            }
            if (ASSETTYPE.MACHINERYEQUIPMENT.getAssetType().equals(assetAllocation.getAssetType())) {
                attchConfig.setAssetType(ASSETTYPE.FONTMACHINERYEQUIPMENT.getAssetType());
            }
            attchConfig = attchConfigDAO.searchByCostcenterCodeAndAssetType(attchConfig);
            if (null != attchConfig) {
                assetAllocation.setPutManageCode(attchConfig.getAttachDepartManagerCode());
                assetAllocation.setPutManagePersonCode(attchConfig.getAttachDepartAdminCode());
            } else {
                result.addErrorMessage("此部门不存在与之相关的归口负责人");
                return result;
            }
        }


        //正式提交正式 并且修改前为正式
        List<String> newListGeneralId = new ArrayList<String>();
        if (assetAllocation.getCommitType().equals(COMMITTYPE.SUBMIT.getCommitType()) && assetAllocation.getOldCommitType().equals(COMMITTYPE.SUBMIT.getCommitType())) {

            GeneralAsset generalAsset = new GeneralAsset();
            if (!"".equals(ids) && null != ids && ids.length() != 0) {
                String[] assetIds = ids.split(",");
                for (String string : assetIds) {
                    newListGeneralId.add(string);
                    generalAsset.setId(Long.parseLong(string));
                    generalAsset.setIsAllocationOccupancy(ISUSABLE.UNAVAILABLE.isUsable());
                    generalAssetDAO.updateAllocationOccupancy(generalAsset);
                }
            } else {
                result.addErrorMessage("请选择一条资产！");
                return result;
            }
            if (!"".equals(listGeneralId) && listGeneralId.size() != 0 && listGeneralId != null) {
                //以前提交保存现在不存在的卡片 将状态还原
                for (String oldString : listGeneralId) {
                    if (!newListGeneralId.contains(oldString)) {
                        generalAsset.setId(Long.parseLong(oldString));
                        generalAsset.setIsAllocationOccupancy(ISUSABLE.AVAILABLE.isUsable());
                        generalAssetDAO.updateAllocationOccupancy(generalAsset);
                    }
                }
            }
        }

        //正式  提交  为 草稿
        if (assetAllocation.getCommitType().equals(COMMITTYPE.DRAFT.getCommitType()) && assetAllocation.getOldCommitType().equals(COMMITTYPE.SUBMIT.getCommitType())) {

            if (!"".equals(ids) && ids != null && ids.length() != 0) {
                String[] assetIds = ids.split(",");
                GeneralAsset generalAsset = new GeneralAsset();
                for (String string : assetIds) {
                    newListGeneralId.add(string);
                    generalAsset.setId(Long.parseLong(string));
                    generalAsset.setIsAllocationOccupancy(ISUSABLE.AVAILABLE.isUsable());
                    generalAssetDAO.updateAllocationOccupancy(generalAsset);
                }
            }
            //以前为正式现在为草稿,看是否存在删除的卡片，将删除的卡片状态还原，并将现在卡片状态还原
            GeneralAsset generalAsset = new GeneralAsset();
            if (!"".equals(listGeneralId) && listGeneralId != null && listGeneralId.size() != 0) {
                for (String stringOld : listGeneralId) {
                    if (!newListGeneralId.contains(stringOld)) {
                        generalAsset.setId(Long.parseLong(stringOld));
                        generalAsset.setIsAllocationOccupancy(ISUSABLE.AVAILABLE.isUsable());
                        generalAssetDAO.updateAllocationOccupancy(generalAsset);
                    }
                }
            }
        }


        if (!"".equals(generalAssetId) && generalAssetId != null && generalAssetId.length != 0) {
            //草稿 提交  为 草稿
            if (assetAllocation.getCommitType().equals(COMMITTYPE.DRAFT.getCommitType()) && assetAllocation.getOldCommitType().equals(COMMITTYPE.DRAFT.getCommitType())) {

                GeneralAsset generalAsset = new GeneralAsset();
                if (!"".equals(ids) && ids != null && ids.length() != 0) {
                    String[] assetIds = ids.split(",");
                    for (String string : assetIds) {
                        GeneralAsset generalAsset2 = generalAssetDAO.getGeneralAsset(Integer.parseInt(string));
                        if (generalAsset2.getIsAllocationOccupancy().equals(ISUSABLE.UNAVAILABLE.isUsable())) {
                            if ("".equals(errorMessage)) {
                                errorMessage = generalAsset2.getAssetCode() + "[" + generalAsset2.getCompanyCode() + "]";
                            } else {
                                errorMessage = errorMessage + "," + generalAsset2.getAssetCode() + "[" + generalAsset2.getCompanyCode() + "]";
                            }
                        }

                    }
                }
                //如果错误信息为空，则没有被占用的明细
                if ("".equals(errorMessage)) {
                    if (!"".equals(ids) && ids != null && ids.length() != 0) {
                        String[] assetIds = ids.split(",");
                        for (String string : assetIds) {
                            generalAsset.setId(Long.parseLong(string));
                            generalAsset.setIsAllocationOccupancy(ISUSABLE.AVAILABLE.isUsable());
                            generalAssetDAO.updateAllocationOccupancy(generalAsset);

                        }
                    }
                } else {
                    result.addErrorMessage(errorMessage + "已被调拨流程占用");
                    return result;
                }

            }
            //草稿 提交 正式
            if (assetAllocation.getCommitType().equals(
                    COMMITTYPE.SUBMIT.getCommitType()) && assetAllocation.getOldCommitType().equals(COMMITTYPE.DRAFT.getCommitType())) {

                GeneralAsset generalAsset = new GeneralAsset();
                if (!"".equals(ids) && ids != null && ids.length() != 0) {
                    String[] assetIds = ids.split(",");

                    for (String string : assetIds) {
                        GeneralAsset generalAsset2 = generalAssetDAO.getGeneralAsset(Integer.parseInt(string));
                        if (generalAsset2.getIsAllocationOccupancy().equals(ISUSABLE.UNAVAILABLE.isUsable())) {
                            if ("".equals(errorMessage)) {
                                errorMessage = generalAsset2.getAssetCode() + "[" + generalAsset2.getCompanyCode() + "]";
                            } else {
                                errorMessage = errorMessage + "," + generalAsset2.getAssetCode() + "[" + generalAsset2.getCompanyCode() + "]";
                            }
                        }

                    }
                    //如果错误信息为空，则没有被占用的明细
                    if ("".equals(errorMessage)) {
                        if (!"".equals(assetIds) && assetIds != null && assetIds.length != 0) {
                            for (String string : assetIds) {
                                generalAsset.setId(Long.parseLong(string));
                                generalAsset.setIsAllocationOccupancy(ISUSABLE.UNAVAILABLE.isUsable());
                                generalAssetDAO.updateAllocationOccupancy(generalAsset);
                            }
                        }
                    } else {
                        result.addErrorMessage(errorMessage + "已被调拨流程占用");
                        return result;
                    }
                } else {
                    result.addErrorMessage("请选择一条资产");
                    return result;
                }
            }
        }
        assetAllocationDAO.updateAssetAllocation(assetAllocation);
        AssetAllocationDetail assetAllocationDetail = new AssetAllocationDetail();
        assetAllocationDetail.setAssetAllocationId(Integer.parseInt(assetAllocation.getId().toString()));
        assetAllocationDetailDAO.updateAssetAllocationDetail(assetAllocationDetail);
        if (ids != null && !"".equals(ids)) {
            String[] id = ids.split(",");

            for (int i = 0; i < id.length; i++) {
                GeneralAsset generalAsset = generalAssetDAO.getGeneralAsset(Integer.parseInt(id[i]));
                assetAllocationDetail.setAssetCode(generalAsset.getAssetCode());
                assetAllocationDetail.setAssetName(generalAsset.getAssetName());
                assetAllocationDetail.setPutUnderManageCode(generalAsset.getPutUnderManageCode());
                assetAllocationDetail.setAssetPerson(generalAsset.getAssetPerson());

                assetAllocationDetail.setSpecificationModel(generalAsset.getSpecificationModel());
                assetAllocationDetail.setAssetOriginalValue(generalAsset.getAssetOriginalValue());
                assetAllocationDetail.setAssetNetValue(generalAsset.getAssetNetValue());
                assetAllocationDetail.setAssetState(generalAsset.getAssetState());
                assetAllocationDetail.setUseState(generalAsset.getUseState());
                assetAllocationDetail.setCompanyName(generalAsset.getCompanyName());
                assetAllocationDetail.setCompanyCode(generalAsset.getCompanyCode());
                assetAllocationDetail.setCostCenterCode(generalAsset.getCostCenterCode());
                assetAllocationDetail.setPersentDepartment(generalAsset.getPersentDepartment());
                assetAllocationDetail.setLocation(generalAsset.getLocation());
                assetAllocationDetail.setGeneralAssetId(Integer.parseInt(id[i]));

                assetAllocationDetail.setAssetAllocationId(Integer.parseInt(assetAllocation.getId().toString()));
                assetAllocationDetail.setDeletedFlag(ISUSABLE.AVAILABLE.isUsable());
                assetAllocationDetail.setAssetType(generalAsset.getAssetType());
                assetAllocationDetailDAO.saveApply(assetAllocationDetail);
            }
        }

        return result;

    }

    @Override
    public List<AssetAllocation> export(AssetAllocation assetAllocation) {

        return assetAllocationDAO.export(assetAllocation);
    }

    @Override
    public ExecuteResult<AssetAllocation> bindApplyWorkFlow(
            AssetAllocation assetAllocation) {
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
            WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

            createNextFlowStepParam.setBillCode(assetAllocation.getDocument());
            createNextFlowStepParam.setBillTableName(Env.getProperty("allocation.info.table"));
            createNextFlowStepParam
                    .setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            createNextFlowStepParam.setDeptCode(assetAllocation
                    .getPostDepartmentCode());
            createNextFlowStepParam.setDeptName(assetAllocation
                    .getPostAllocationDepartment());
            createNextFlowStepParam.setCreatorCode(assetAllocation
                    .getUserCode());
            createNextFlowStepParam.setCreatorName(assetAllocation
                    .getUserName());
            Calendar instance = Calendar.getInstance();
            instance.setTime(assetAllocation.getCreateDate());
            createNextFlowStepParam.setCreateDate(instance);
            createNextFlowStepParam.setCApprovalId(WorkFlowCommon
                    .generateUUID());
            createNextFlowStepParam.setTypeCode(Env.getProperty("allocation.info.type.id"));
            createNextFlowStepParam.setIsEmail(assetAllocation.getIsMail());
            createNextFlowStepParam.setIsPhoneMessage(assetAllocation.getIsPhoneMessage());

            createNextFlowStepParam.setFeeItemCode("");
            createNextFlowStepParam.setIdea(assetAllocation.getIdea());
            createNextFlowStepParam.setCCostCenterCode(assetAllocation
                    .getPostCostcenterCode());
            createNextFlowStepParam.setCCommitActivityId(assetAllocation
                    .getActivityId());
            createNextFlowStepParam.setMoney("0");

            createNextFlowStep.setParam(createNextFlowStepParam);

            createNextFlowStep.setUserCode(assetAllocation.getUserCode());
            createNextFlowStep.setUserName(assetAllocation.getUserName());

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

        result.setSuccessMessage("绑定成功");
        return result;
    }

    @Override
    public void deleteAssetAllocationByDocument(String document) {
        assetAllocationDAO.deleteAssetAllocationByDocument(document);
    }

    @Override
    public ExecuteResult<AssetAllocation> agreeWorkFlow(
            AssetAllocation assetAllocation) {
        ExecuteResult<AssetAllocation> result = new ExecuteResult<AssetAllocation>();
        boolean ifRollback = false;
        try {

            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(assetAllocation.getDocument());
            agreeFlowParam.setBillTableName(Env.getProperty("allocation.info.table"));
            agreeFlowParam
                    .setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            agreeFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());
            agreeFlowParam.setDeptCode(assetAllocation.getPostDepartmentCode());
            agreeFlowParam.setDeptName(assetAllocation
                    .getPostAllocationDepartment());
            agreeFlowParam.setCreatorCode(assetAllocation
                    .getUserCode());
            agreeFlowParam.setCreatorName(assetAllocation
                    .getUserName());

            if (null == assetAllocation.getCreateDate()) {
                result.addErrorMessage("创建时间不能为空");
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime(assetAllocation.getCreateDate());
            agreeFlowParam.setCreateDate(instance);
            agreeFlowParam.setCUserCode(assetAllocation.getCaUserCode());
            agreeFlowParam.setCUserName(assetAllocation.getCaUserName());
            agreeFlowParam.setUserDepCode(assetAllocation.getUserDepCode());
            agreeFlowParam.setAMoney("0");
            agreeFlowParam.setTypeCode(Env.getProperty("allocation.info.type.id"));
            agreeFlowParam.setMoney("0");
            if (null != assetAllocation.getIdea()) {
                agreeFlowParam.setIdea(assetAllocation.getIdea());
            } else {
                agreeFlowParam.setIdea("");
            }
            agreeFlowParam.setIsEmail(assetAllocation.getIsMail());
            agreeFlowParam.setIsPhoneMessage(assetAllocation
                    .getIsPhoneMessage());
            agreeFlowParam.setCCostCenterCode(assetAllocation
                    .getPostCostcenterCode());
            agreeFlowParam
                    .setCCommitActivityId(assetAllocation.getActivityId());
            agree.setUserCode(assetAllocation.getCaUserCode());
            agree.setUserName(assetAllocation.getCaUserName());

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
        result.setSuccessMessage("调用审批流成功！");
        AssetAllocation getAssetAllocation = assetAllocationDAO
                .getAssetAllocationByDocument(assetAllocation.getDocument());
        if (getAssetAllocation.getApprovalState().equals(
                APPROVESTATE.DONE.getApproveState())) {

            List<AssetAllocationDetail> list = assetAllocationDetailDAO
                    .searchAssetAllocationDetailById(Integer.parseInt(String
                            .valueOf(getAssetAllocation.getId())));
            if (null != list && list.size() != 0) {
                GeneralAsset generalAsset = new GeneralAsset();
                for (AssetAllocationDetail assetAllocationDetail : list) {
                    generalAsset
                            .setId(Long.parseLong(String
                                    .valueOf(assetAllocationDetail
                                            .getGeneralAssetId())));
                    generalAsset.setIsAllocationOccupancy(ISUSABLE.AVAILABLE.isUsable());
                    generalAssetDAO.updateAllocationOccupancy(generalAsset);
                }
            }
            List<AssetAllocationDetail> assetAllocationDetailList = assetAllocationDetailDAO
                    .searchAssetAllocationDetailById(Integer.parseInt(String
                            .valueOf(getAssetAllocation.getId())));
            for (AssetAllocationDetail assetAllocationDetail : assetAllocationDetailList) {
                long generalAssetId = assetAllocationDetail.getGeneralAssetId();

                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setAssetPerson(getAssetAllocation
                        .getPostAllocationPeople());
                generalAsset
                        .setAssetPersonCode(getAssetAllocation.getPostPeopleCode());
                generalAsset.setPersentDepartment(getAssetAllocation
                        .getPostAllocationDepartment());
                generalAsset.setCompanyName(getAssetAllocation
                        .getPostAllocationCompany());
                generalAsset.setPersentStorageLocation(getAssetAllocation
                        .getPostAllocationStoragePlace());
                generalAsset.setOfficeLocation(getAssetAllocation.getOfficeLocation());
                generalAsset.setLocation(getAssetAllocation.getPostAllocationStoragePlace());
                generalAsset.setCompanyCode(getAssetAllocation.getPostCompanyCode());
                generalAsset.setDepartmentCode(getAssetAllocation
                        .getPostDepartmentCode());
                generalAsset.setPlatform(getAssetAllocation.getPlatform());
                generalAsset.setCostCenterCode(getAssetAllocation
                        .getPostCostcenterCode());
                generalAsset.setUseDepartment(assetAllocation.getPostAllocationDepartment());
                if (null != assetAllocationDetail.getNewAssetCode()
                        && !"".equals(assetAllocationDetail.getNewAssetCode())) {
                    generalAsset.setAssetCode(assetAllocationDetail
                            .getNewAssetCode());
                    generalAsset.setOldAssetCode(assetAllocationDetail
                            .getAssetCode());
                }
                generalAsset.setId(generalAssetId);

                generalAssetDAO.updateCard(generalAsset);

            }
        }
        return result;
    }

    @Override
    public ExecuteResult<AssetAllocation> denyWorkFlow(
            AssetAllocation assetAllocation) {
        ExecuteResult<AssetAllocation> executeResult = new ExecuteResult<AssetAllocation>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();

            backFlowParam.setBillCode(assetAllocation.getDocument());
            backFlowParam.setBillTableName(Env.getProperty("allocation.info.table"));
            backFlowParam.setCreatorCode(assetAllocation.getUserCode());
            backFlowParam.setCreatorName(assetAllocation.getUserName());
            backFlowParam.setIdea(assetAllocation.getIdea());
            backFlowParam.setCUserCode(assetAllocation.getCaUserCode());
            backFlowParam.setCUserName(assetAllocation.getCaUserName());
            backFlowParam.setUserDepCode(assetAllocation.getUserDepCode());
            backFlowParam.setTypeCode(Env.getProperty("allocation.info.type.id"));
            backFlowParam.setActivityCode(assetAllocation.getActivityId());

            backFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());

            backFlowParam.setIsEmail(assetAllocation.getIsMail());
            backFlowParam
                    .setIsPhoneMessage(assetAllocation.getIsPhoneMessage());

            back.setParam(backFlowParam);

            back.setUserCode(assetAllocation.getCaUserCode());
            back.setUserName(assetAllocation.getCaUserName());

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
    public ExecuteResult<AssetAllocation> dropWorkFlow(
            AssetAllocation assetAllocation) {

        ExecuteResult<AssetAllocation> executeResult = new ExecuteResult<AssetAllocation>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();

            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(assetAllocation.getDocument());
            unApprovalFlowParam.setBillTableName(Env.getProperty("allocation.info.table"));
            unApprovalFlowParam
                    .setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            unApprovalFlowParam.setCreatorCode(assetAllocation
                    .getUserCode());
            unApprovalFlowParam.setCreatorName(assetAllocation
                    .getUserName());
            unApprovalFlowParam.setIdea(assetAllocation.getIdea());
            unApprovalFlowParam.setCUserCode(assetAllocation.getCaUserCode());
            unApprovalFlowParam.setCUserName(assetAllocation.getCaUserName());
            unApprovalFlowParam
                    .setUserDepCode(assetAllocation.getUserDepCode());
            unApprovalFlowParam.setTypeCode(Env.getProperty("allocation.info.type.id"));

            unApproval.setParam(unApprovalFlowParam);

            unApproval.setUserCode(assetAllocation.getCaUserCode());
            unApproval.setUserName(assetAllocation.getCaUserName());

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
    public AssetAllocation getAssetAllocationByDocument(String document) {

        return assetAllocationDAO.getAssetAllocationByDocument(document);
    }

    @Override
    public void updateAssetAllocationByDocument(AssetAllocation assetAllocation) {
        // TODO Auto-generated method stub

    }

    @Override
    public AssetAllocation getAssetAllocation(AssetAllocation assetAllocation) {
        assetAllocation = assetAllocationDAO
                .getOneAssetAllocation(assetAllocation);
        List<GeneralAsset> listGeneralAssets = new ArrayList<GeneralAsset>();
        List<AssetAllocationDetail> listDetail = assetAllocationDetailDAO
                .serachAssetAllocationDetail(Integer.parseInt(String
                        .valueOf(assetAllocation.getId())));
        String ids = "";
        String assetId = "";
        for (AssetAllocationDetail assetAllocationDetail : listDetail) {
            GeneralAsset generalAsset = generalAssetDAO.getGeneralAsset(Integer.parseInt(String.valueOf(assetAllocationDetail.getGeneralAssetId())));
            long id = assetAllocationDetail.getAssetAllocationId();
            if ("".equals(ids)) {
                assetId = Long.toString(id);
                ids = assetId;
            } else {
                assetId = Long.toString(id);
                ids = ids + "," + assetId;
            }
            listGeneralAssets.add(generalAsset);
        }
        assetAllocation.setGeneralAssetIds(ids);
        assetAllocation.setGeneralAsset(listGeneralAssets);
        assetAllocation.setListAssetAllocationDetail(listDetail);
        return assetAllocation;
    }

    @Override
    public void updateComfirPeopleAndStatus(AssetAllocation assetAllocation) {
        // 修改下一步处理人 修改审批状态为退回
        assetAllocationDAO.updateComfirPeopleAndStatus(assetAllocation);

    }

    @Override
    public void updateComfirStatus(AssetAllocation assetAllocation) {

        // 单据所确认人数
        long sumCount = alloctionApproveDAO
                .serachCountConfirmByDocument(assetAllocation.getDocument());
        // 已经确认的条数
        long count = alloctionApproveDAO.serachCountConfirm(assetAllocation
                .getDocument());

        if ((1 < sumCount || 1 == sumCount) && (count == 0)) {
            assetAllocation.setConfirmStatus(CONFIRMSTATUS.WAITINGCONFIRMATION.getConfirmStatus()); // 等待确认
            assetAllocationDAO.updateComfirStatus(assetAllocation);
        }
        if (sumCount == count) {
            assetAllocation.setConfirmStatus(CONFIRMSTATUS.CONFIRMCOMPLETION.getConfirmStatus()); // 确认完成
            assetAllocationDAO.updateComfirStatus(assetAllocation);
        }
        if ((1 < sumCount || 1 == sumCount) && (count < sumCount && count != 0)) {
            assetAllocation.setConfirmStatus(CONFIRMSTATUS.INCONFIRMATION.getConfirmStatus()); // 确认中
            assetAllocationDAO.updateComfirStatus(assetAllocation);
        }

    }

    @Override
    public boolean getIsLastApprovalStep(AssetAllocation assetAllocation) {
        boolean bool = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.GetIsLastApprovalStep isLast = new WorkFlowStub.GetIsLastApprovalStep();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(assetAllocation.getDocument());
            agreeFlowParam
                    .setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            agreeFlowParam.setBillTableName(Env.getProperty("allocation.info.table"));
            agreeFlowParam.setCUserCode(assetAllocation.getLoginUseCode());
            agreeFlowParam.setCUserName(assetAllocation.getLoginUseName());
            agreeFlowParam.setIdea("");
            agreeFlowParam.setAMoney("0");
            agreeFlowParam.setUserDepCode(assetAllocation.getLoginDepCode());
            agreeFlowParam.setTypeCode(Env.getProperty("allocation.info.type.id"));

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
    public AssetAllocation getOneAssetAllocation(AssetAllocation assetAllocation) {

        return assetAllocationDAO.getOneAssetAllocation(assetAllocation);
    }

    @Override
    public void deleteAssetAllocation(String ids) {
        String[] idArray = ids.split(",");
        GeneralAsset generalAsset = new GeneralAsset();
        AssetAllocation assetAllocation = new AssetAllocation();
        for (String string : idArray) {
            List<AssetAllocationDetail> detailList = assetAllocationDetailDAO
                    .searchAssetAllocationDetailById(Integer.parseInt(string));
            for (AssetAllocationDetail assetAllocationDetail : detailList) {
                generalAsset.setId(Long.parseLong(String
                        .valueOf(assetAllocationDetail.getGeneralAssetId())));
                generalAsset.setIsAllocationOccupancy(ISUSABLE.AVAILABLE.isUsable());
                generalAssetDAO.updateAllocationOccupancy(generalAsset);
            }
            assetAllocation.setId(Long.parseLong(string));
            AssetAllocation getAssetAllocation = assetAllocationDAO
                    .getOneAssetAllocation(assetAllocation);
            assetAllocationDAO.deleteAssetAllocation(assetAllocation);
            alloctionApproveDAO.deleteAllocationApprove(getAssetAllocation
                    .getDocument());

        }
    }

    @Override
    public void updateSapStatus(AssetAllocation assetAllocation) {
        assetAllocationDAO.updateSapStatus(assetAllocation);
    }

    @Override
    public AssetAllocation getOneAssetAllocationByMM(
            AssetAllocation assetAllocation) {
        assetAllocation = assetAllocationDAO
                .getOneAssetAllocation(assetAllocation);
        List<AssetAllocationDetail> assetAllocationDetailList = assetAllocationDetailDAO
                .searchAssetAllocationDetailById(Integer.parseInt(String
                        .valueOf(assetAllocation.getId())));
        List<GeneralAsset> generalAssetList = new ArrayList<GeneralAsset>();
        for (AssetAllocationDetail assetAllocationDetail : assetAllocationDetailList) {
            long id = assetAllocationDetail.getGeneralAssetId();
            GeneralAsset generalAsset = generalAssetDAO
                    .searchGeneralAssetInfo((int) id);
            if (null != generalAsset) {
                generalAsset.setNewAssetCode(assetAllocationDetail
                        .getNewAssetCode());
                generalAssetList.add(generalAsset);
            }
        }
        assetAllocation.setAssetAllocationDetail(assetAllocationDetailList);
        assetAllocation.setGeneralAsset(generalAssetList);
        return assetAllocation;
    }

    @Override
    public void updateIsStamp(AssetAllocation assetAllocation) {
        assetAllocationDAO.updateIsStamp(assetAllocation);

    }

    @Override
    public void updateBack(AssetAllocation assetAllocation) {
        // 退回时 修改所有的状态 为未确认
        AlloctionApprove alloctionApprove = new AlloctionApprove();
        alloctionApprove.setAllocationDocument(assetAllocation.getDocument());
        alloctionApprove.setIsConfirm(ISCONFIRM.UNCONFIRMED.isConfirm());
        alloctionApproveDAO.updateIsConfirmByBack(alloctionApprove);
        assetAllocation.setApprovalState("");
        assetAllocationDAO.updateComfirStatus(assetAllocation);
        assetAllocationDAO.updateNextUser(assetAllocation);

    }

}
