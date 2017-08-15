package com.opple.fa.processasset.service.impl;

import com.opple.fa.assetcard.dao.GeneralAssetDAO;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.processasset.dao.ProcessAssetsDAO;
import com.opple.fa.processasset.dao.ProcessAssetsDetallDAO;
import com.opple.fa.processasset.entity.ProcessAssets;
import com.opple.fa.processasset.entity.ProcessAssetsDetall;
import com.opple.fa.processasset.service.ProcessAssetsService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import com.opple.workflow.WorkFlowStub;
import com.opple.workflow.entity.COMMITTYPE;
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
import java.util.List;

public class ProcessAssetsServiceImpl implements ProcessAssetsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProcessAssetsServiceImpl.class);
    @Getter
    @Setter
    private ProcessAssetsDAO processAssetsDAO;
    @Getter
    @Setter
    private ProcessAssetsDetallDAO processAssetsDetallDAO;
    @Getter
    @Setter
    private GeneralAssetDAO generalAssetDAO;


    @Override
    public Pager<ProcessAssets> searchProcessAssets(Pager<ProcessAssets> pager,
                                                    ProcessAssets processAssets) {

        List<ProcessAssets> processAssetslist = processAssetsDAO.searchProcessAssets(pager, processAssets);
        Long count = processAssetsDAO.searchProcessAssetsCount(processAssets);
        pager.setRecords(processAssetslist); //设置数据
        pager.setTotal(count); //设置总页数
        return Pager.cloneFromPager(pager, count, processAssetslist);
    }

    @Override
    public ProcessAssets examineProcessAssetsList(ProcessAssets processAssets) {

        ProcessAssets processAssetsList = processAssetsDAO.getProcessAssets(processAssets);
        Long id = processAssetsList.getId();
        ProcessAssetsDetall processAssetsDetall = new ProcessAssetsDetall();
        processAssetsDetall.setProcessAssetsId(id);
        List<ProcessAssetsDetall> processAssetsDetallList = processAssetsDetallDAO.getProcessAssetsDetall(processAssetsDetall);
        List<GeneralAsset> generalAssetList = new ArrayList<GeneralAsset>();
        for (ProcessAssetsDetall processAssetsDetall2 : processAssetsDetallList) {
            long gid = processAssetsDetall2.getGeneralAssetId();
            GeneralAsset generalAsset = generalAssetDAO.searchGeneralAssetInfo((int) gid);
            generalAssetList.add(generalAsset);
        }
        processAssetsList.setGeneralAssetList(generalAssetList);

        return processAssetsList;
    }

    @Override
    public ExecuteResult<ProcessAssets> saveProcessAssets(
            ProcessAssets processAssets, String processAssetsDetall) {
        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();

        processAssetsDAO.saveProcessAssets(processAssets);

        if (!"".equals(processAssetsDetall) && processAssetsDetall != null) {
            String[] string = processAssetsDetall.split(",");
            for (String string2 : string) {
                ProcessAssetsDetall pad = new ProcessAssetsDetall();
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setId(Long.parseLong(string2));
                generalAsset.setIsAssembleOccupancy("1");
                long paid = Integer.parseInt(string2);
                pad.setProcessAssetsId(processAssets.getId());
                pad.setGeneralAssetId(paid);
                processAssetsDetallDAO.saveProcessAssetsDetall(pad);
                if (processAssets.getCommitType().equals(COMMITTYPE.SUBMIT.getCommitType())) {
                    generalAssetDAO.updateAssembleOccupancy(generalAsset);
                }
            }
        }

        return result;
    }

    @Override
    public void updateProcessAssets(ProcessAssets processAssets,
                                    String processAssetsDetall) {

        processAssetsDAO.updateProcessAssets(processAssets);


        String[] string = processAssetsDetall.split(",");
        List<String> listString = new ArrayList<String>();

        ProcessAssetsDetall processAssetsDetail = new ProcessAssetsDetall();
        processAssetsDetail.setProcessAssetsId(processAssets.getId());
        List<String> listProcessDetai = processAssetsDetallDAO.searchProcessAssetsDetallById(processAssetsDetail);
        processAssetsDetallDAO.updateStatus(processAssets.getId());
        for (String string2 : string) {
            listString.add(string2);

            ProcessAssetsDetall pad = new ProcessAssetsDetall();
            long paid = Integer.parseInt(string2);
            pad.setProcessAssetsId(processAssets.getId());
            pad.setGeneralAssetId(paid);
            processAssetsDetallDAO.saveProcessAssetsDetall(pad);
            if (processAssets.getCommitType().equals(COMMITTYPE.SUBMIT.getCommitType())) {
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setId(Long.parseLong(string2));
                generalAsset.setIsAssembleOccupancy("1");
                generalAssetDAO.updateAssembleOccupancy(generalAsset);

            }
        }
        for (String string1 : listProcessDetai) {
            if (!processAssetsDetall.contains(string1)) {
                GeneralAsset generalAsset = new GeneralAsset();
                generalAsset.setId(Long.parseLong(string1));
                generalAsset.setIsAssembleOccupancy("0");
                generalAssetDAO.updateAssembleOccupancy(generalAsset);
            }
        }


    }

    @Override
    public ExecuteResult<ProcessAssets> deleteProcessAssets(
            ProcessAssets processAssets) {
        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();
        ProcessAssets pa = processAssetsDAO.getProcessAssets(processAssets);

		/*if("已完成".equals(pa.getCapprovalstate())){
            processAssetsDAO.deleteProcessAssets(processAssets);
		}else{
			result.addErrorMessage("待审批数据不能删");
		}*/
        processAssetsDAO.deleteProcessAssets(processAssets);
        return result;
    }

    @Override
    public List<ProcessAssets> export(ProcessAssets processAssets) {

        return processAssetsDAO.export(processAssets);
    }

    @Override
    public ExecuteResult<ProcessAssets> bindApplyWorkFlow(
            ProcessAssets processAssets) {
        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();
        boolean ifRollback = false;
        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.CreateNextFlowStep createNextFlowStep = new WorkFlowStub.CreateNextFlowStep();
            WorkFlowStub.CreateNextFlowStepParam createNextFlowStepParam = new WorkFlowStub.CreateNextFlowStepParam();

            createNextFlowStepParam.setBillCode(processAssets.getDocument());
            createNextFlowStepParam.setBillTableName("TB_FA_PROCESS_ASSETS");
            createNextFlowStepParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            createNextFlowStepParam.setDeptCode(processAssets.getDepartmentCode());
            createNextFlowStepParam.setDeptName(processAssets.getDepartmentName());
            createNextFlowStepParam.setCreatorCode(processAssets.getUserCode());
            createNextFlowStepParam.setCreatorName(processAssets.getUserName());
            createNextFlowStepParam.setCreateDate(Calendar.getInstance());
            createNextFlowStepParam.setCApprovalId(WorkFlowCommon.generateUUID());
            createNextFlowStepParam.setTypeCode("77087");
            createNextFlowStepParam.setIsEmail("N");
            createNextFlowStepParam.setIsPhoneMessage("N");

            createNextFlowStepParam.setFeeItemCode("");
            createNextFlowStepParam.setIdea("");
            createNextFlowStepParam.setCCostCenterCode("");

            createNextFlowStepParam.setMoney("0");

            createNextFlowStep.setParam(createNextFlowStepParam);

            createNextFlowStep.setUserCode(processAssets.getUserCode());
            createNextFlowStep.setUserName(processAssets.getUserName());

            WorkFlowStub.CreateNextFlowStepResponse response = stub.createNextFlowStep(createNextFlowStep);

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
    public void deleteProcessAssetsByDocument(String document) {
        processAssetsDAO.deleteProcessAssetsByDocument(document);

    }

    @Override
    public ExecuteResult<ProcessAssets> agreeWorkFlow(
            ProcessAssets processAssets) {
        ExecuteResult<ProcessAssets> result = new ExecuteResult<ProcessAssets>();
        boolean ifRollback = false;
        try {

            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.Agree agree = new WorkFlowStub.Agree();
            WorkFlowStub.AgreeFlowParam agreeFlowParam = new WorkFlowStub.AgreeFlowParam();

            agreeFlowParam.setBillCode(processAssets.getDocument());
            agreeFlowParam.setBillTableName("TB_FA_PROCESS_ASSETS");
            agreeFlowParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            agreeFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());
            agreeFlowParam.setDeptCode(processAssets.getDepartmentCode());
            agreeFlowParam.setDeptName(processAssets.getDepartmentName());
            agreeFlowParam.setCreatorCode(processAssets.getUserCode());
            agreeFlowParam.setCreatorName(processAssets.getUserName());

            if (null == processAssets.getCreateDate()) {
                result.addErrorMessage("创建时间不能为空");
            }
            Calendar instance = Calendar.getInstance();
            instance.setTime(processAssets.getCreateDate());
            agreeFlowParam.setCreateDate(instance);
            agreeFlowParam.setCUserCode(processAssets.getCaUserCode());
            agreeFlowParam.setCUserName(processAssets.getCaUserName());
            agreeFlowParam.setUserDepCode(processAssets.getUserDepCode());
            agreeFlowParam.setAMoney("0");
            agreeFlowParam.setTypeCode("77087");
            agreeFlowParam.setMoney("0");
            agreeFlowParam.setIdea(processAssets.getIdea());
            agreeFlowParam.setIsEmail(processAssets.getIsMail());
            agreeFlowParam.setIsPhoneMessage(processAssets.getIsPhoneMessage());

            agree.setUserCode(processAssets.getCaUserCode());
            agree.setUserName(processAssets.getCaUserName());

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
        return result;
    }

    @Override
    public ProcessAssets getProcessAssetsByDocument(String document) {
        return processAssetsDAO.getProcessAssetsByDocument(document);
    }

    @Override
    public ExecuteResult<ProcessAssets> denyWorkFlow(ProcessAssets processAssets) {
        ExecuteResult<ProcessAssets> executeResult = new ExecuteResult<ProcessAssets>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();

            WorkFlowStub.Back back = new WorkFlowStub.Back();
            WorkFlowStub.BackFlowParam backFlowParam = new WorkFlowStub.BackFlowParam();

            backFlowParam.setBillCode(processAssets.getDocument());
            backFlowParam.setBillTableName("TB_FA_PROCESS_ASSETS");
            backFlowParam.setCreatorCode(processAssets.getUserCode());
            backFlowParam.setCreatorName(processAssets.getUserName());
            backFlowParam.setIdea(processAssets.getIdea());
            backFlowParam.setCUserCode(processAssets.getCaUserCode());
            backFlowParam.setCUserName(processAssets.getCaUserName());
            backFlowParam.setUserDepCode(processAssets.getUserDepCode());
            backFlowParam.setTypeCode("77087");
            backFlowParam.setActivityCode("");

            backFlowParam.setCApprovalId(WorkFlowCommon.generateUUID());

            backFlowParam.setIsEmail(processAssets.getIsMail());
            backFlowParam.setIsPhoneMessage(processAssets.getIsPhoneMessage());

            back.setParam(backFlowParam);

            back.setUserCode(processAssets.getCaUserCode());
            back.setUserName(processAssets.getCaUserName());

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
    public ExecuteResult<ProcessAssets> dropWorkFlow(ProcessAssets processAssets) {
        ExecuteResult<ProcessAssets> executeResult = new ExecuteResult<ProcessAssets>();

        boolean ifRollback = false;

        try {
            WorkFlowStub stub = new WorkFlowStub();
            WorkFlowStub.UnApproval unApproval = new WorkFlowStub.UnApproval();

            WorkFlowStub.UnApprovalFlowParam unApprovalFlowParam = new WorkFlowStub.UnApprovalFlowParam();
            unApprovalFlowParam.setBillCode(processAssets.getDocument());
            unApprovalFlowParam.setBillTableName("TB_FA_PROCESS_ASSETS");
            unApprovalFlowParam.setBillCodeColumnName(WorkFlowCommon.BILLCODECOLUMNNAME);
            unApprovalFlowParam.setCreatorCode(processAssets.getUserCode());
            unApprovalFlowParam.setCreatorName(processAssets.getUserName());
            unApprovalFlowParam.setIdea(processAssets.getIdea());
            unApprovalFlowParam.setCUserCode(processAssets.getCaUserCode());
            unApprovalFlowParam.setCUserName(processAssets.getCaUserName());
            unApprovalFlowParam.setUserDepCode(processAssets.getUserDepCode());
            unApprovalFlowParam.setTypeCode("77087");

            unApproval.setParam(unApprovalFlowParam);

            unApproval.setUserCode(processAssets.getCaUserCode());
            unApproval.setUserName(processAssets.getCaUserName());

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
    public List<String> searchProcessAssetsDetallById(
            ProcessAssetsDetall processAssetsDetall) {

        return processAssetsDetallDAO.searchProcessAssetsDetallById(processAssetsDetall);
    }


}
