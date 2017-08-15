package com.opple.fa.config.service.impl;


import com.opple.common.dao.WorkFlowMapper;
import com.opple.fa.config.dao.AssetsBudgetDAO;
import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.fa.config.service.AssetsBudgetService;
import com.opple.fa.config.service.BudgetAdjustmentDetailService;
import com.opple.util.Pager;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by lk on 10/24/16.
 */
public class AssetsBudgetServiceImpl implements AssetsBudgetService {
    @Getter
    @Setter
    private AssetsBudgetDAO assetsBudgetDAO;
    @Getter
    @Setter
    private WorkFlowMapper workFlowMapper;
    @Getter
    @Setter
    private BudgetAdjustmentDetailService budgetAdjustmentDetailService;

    @Override
    public Pager<AssetsBudget> searchAssetsBudgets(Pager<AssetsBudget> pager, AssetsBudget assetsBudget) {
        List<AssetsBudget> assetsBudgetList = assetsBudgetDAO.searchAssetsBudgets(pager, assetsBudget);
        Long assetsBudgetsCount = assetsBudgetDAO.searchAssetsBudgetsCount(assetsBudget);

        pager.setRecords(assetsBudgetList);
        pager.setTotal(assetsBudgetsCount);

        return Pager.cloneFromPager(pager, assetsBudgetsCount, assetsBudgetList);
    }

    @Override
    public List<AssetsBudget> searchAssetsBudgetsList(AssetsBudget assetsBudget) {
        List<AssetsBudget> assetsBudgetList = assetsBudgetDAO.searchAssetsBudgetsList(assetsBudget);
        return assetsBudgetList;
    }

    @Override
    public void importAssetsBudgetExcel(List<AssetsBudget> listForInsert, List<AssetsBudget> listForUpdate) {
        assetsBudgetDAO.saveDataToHistory();
        if (0 != listForInsert.size()) {
            for (AssetsBudget assetsBudget2 : listForInsert) {
                assetsBudgetDAO.insertAssetsBudgets(assetsBudget2);
            }
        }
        if (0 != listForUpdate.size()) {
            for (AssetsBudget assetsBudget2 : listForUpdate) {
                assetsBudgetDAO.updateAssetsBudgets(assetsBudget2);
            }
        }
    }

    @Override
    public Long getAssetsBudgetsbyProjectCode(AssetsBudget assetsBudget) {
        return assetsBudgetDAO.getAssetsBudgetsbyProjectCode(assetsBudget);
    }

    @Override
    public Long getAssetsBudgetsbyProjectCodeAndYear(AssetsBudget assetsBudget) {
        return assetsBudgetDAO.getAssetsBudgetsbyProjectCodeAndYear(assetsBudget);
    }

    @Override
    public void confirmBudget(AssetsBudget assetsBudget) {
        assetsBudgetDAO.confirmBudget(assetsBudget);

    }

    @Override
    public void concelConfirmBudget(AssetsBudget assetsBudget) {
        assetsBudgetDAO.concelConfirmBudget(assetsBudget);

    }

    @Override
    public BudgetAdjustmentDetail getBudgetDetailsbyProjectCodeAndYear(String year, String projectCode) {
        return assetsBudgetDAO.getBudgetDetailsbyProjectCodeAndYear(year, projectCode);
    }

    @Override
    public void deleteBudget(AssetsBudget assetsBudget) {
        assetsBudgetDAO.deleteBudget(assetsBudget);

    }

    @Override
    public void confirmAllBudget() {
        assetsBudgetDAO.confirmAllBudget();

    }

    @Override
    public void updateVersion(AssetsBudget assetsBudget) {
        assetsBudgetDAO.updateVersion(assetsBudget);

    }

    @Override
    public void updateAssetsBudgetAfterApprove(List<BudgetAdjustmentDetail> budgetAdjustmentDetails) {
        for (BudgetAdjustmentDetail detail : budgetAdjustmentDetails) {
            BudgetAdjustmentDetail budgetAdjustmentDetail = assetsBudgetDAO.getBudgetDetailsbyProjectCodeAndYear(detail.getBudgetYear(), detail.getProjectCode());
            detail.setUseableMoney(detail.getAfterAdjustMoney() - budgetAdjustmentDetail.getUsedSumMoney());
            assetsBudgetDAO.updateAssetsBudgetAfterApprove(detail);
        }
    }

    @Override
    public void addAssetsBudgetAfterApprove(List<BudgetAdjustmentDetail> budgetAdjustmentDetails) {
        for (BudgetAdjustmentDetail detail : budgetAdjustmentDetails) {
            detail.setUsedAmount(0);
            detail.setUsedSumMoney(0.0);
            detail.setUseableCount(detail.getBeforeAdjustCount());
            detail.setUseableMoney(detail.getBeforeAdjustMoney());
            if (StringUtils.isBlank(detail.getProjectCode())) {
                detail.setProjectCode(workFlowMapper.getDocumentByType("FA_ASBG"));
                budgetAdjustmentDetailService.addProjectCodeForDetail(detail);
            }
            detail.setCreateDate(new Date());
            detail.setUpdateDate(new Date());
            assetsBudgetDAO.addAssetsBudgetAfterApprove(detail);
        }
    }

    @Override
    public void updateAssetsBudgetAfterDrop(List<BudgetAdjustmentDetail> budgetAdjustmentDetails) {
        for (BudgetAdjustmentDetail detail : budgetAdjustmentDetails) {
            detail.setAfterAdjustCount(detail.getBeforeAdjustCount());
            detail.setAfterUnitPrice(detail.getBeforeUnitPrice());
            detail.setAfterAdjustMoney(detail.getBeforeAdjustMoney());
            BudgetAdjustmentDetail budgetAdjustmentDetail = assetsBudgetDAO.getBudgetDetailsbyProjectCodeAndYear(detail.getBudgetYear(), detail.getProjectCode());
            detail.setUseableMoney(detail.getAfterAdjustMoney() - budgetAdjustmentDetail.getUsedSumMoney());
            detail.setUseableCount(detail.getAfterAdjustCount() - budgetAdjustmentDetail.getUsedAmount());
            assetsBudgetDAO.updateAssetsBudgetAfterApprove(detail);
        }
    }

    @Override
    public void deleteAssetsBudgetAfterDrop(List<BudgetAdjustmentDetail> budgetAdjustmentDetails) {
        for (BudgetAdjustmentDetail detail : budgetAdjustmentDetails) {
            assetsBudgetDAO.deleteAssetsBudgetAfterDrop(detail);
        }
    }

    @Override
    public List<AssetsBudget> exportAssetsBudget(AssetsBudget assetsBudget) {
        return assetsBudgetDAO.exportAssetsBudget(assetsBudget);
    }

    @Override
    public AssetsBudget getAssetsBudget(AssetsBudget assetsBudget) {
        return assetsBudgetDAO.getAssetsBudget(assetsBudget);
    }

    @Override
    public AssetsBudget getAssetsBudgetbyProjectCodeAndYear(String year,
                                                            String projectCode) {
        return assetsBudgetDAO.getAssetsBudgetbyProjectCodeAndYear(year, projectCode);
    }

}
