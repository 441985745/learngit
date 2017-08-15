package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.BudgetAdjustmentDetailDAO;
import com.opple.fa.config.entity.BudgetAdjustment;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.fa.config.service.BudgetAdjustmentDetailService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/3/21.
 */
public class BudgetAdjustmentDetailServiceImpl implements BudgetAdjustmentDetailService {
    @Getter @Setter
    private BudgetAdjustmentDetailDAO budgetAdjustmentDetailDAO;

    @Override
    public void addProjectCodeForDetail(BudgetAdjustmentDetail budgetAdjustmentDetail) {
        budgetAdjustmentDetailDAO.addProjectCodeForDetail(budgetAdjustmentDetail);
    }
/*
    @Override
    public void updateBudgetAdjustmentDetail(BudgetAdjustment budgetAdjustment, List<BudgetAdjustmentDetail> budgetAdjustmentDetails) {
        budgetAdjustmentDetailDAO.deleteBudgetAdjustmentDetailByDocument(budgetAdjustment.getDocument());
        if (budgetAdjustmentDetails.size() > 0) {
            if (ASSETBUDGETTYPE.ADD.getAssetBudgetType().equals(budgetAdjustment.getAssetsBudgetType())) {
                for (BudgetAdjustmentDetail budgetAdjustmentDetail : budgetAdjustmentDetails) {
                    budgetAdjustmentDetail.setStatus("0");
                    budgetAdjustmentDetail.setBudgetAdjustId(budgetAdjustment.getBudgetId());
                    budgetAdjustmentDetailDAO.addBudgetAdjustmentDetail(budgetAdjustmentDetail);
                }
            } else {
                for (BudgetAdjustmentDetail budgetAdjustmentDetail : budgetAdjustmentDetails) {
                    budgetAdjustmentDetail.setStatus("0");
                    budgetAdjustmentDetail.setBudgetAdjustId(budgetAdjustment.getBudgetId());
                    BigDecimal df = new BigDecimal(budgetAdjustmentDetail.getAfterUnitPrice().toString());
                    budgetAdjustmentDetail.setAfterUnitPrice(df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    budgetAdjustmentDetailDAO.addBudgetAdjustmentDetail(budgetAdjustmentDetail);
                }
            }

        }
    }*/
    @Override
    public void deleteBudgetAdjsutDetialByBudgetId(BudgetAdjustment budgetAdjustment) {

    }
    @Override
    public void addBudgetAdjustmentDetail(BudgetAdjustmentDetail budgetAdjustmentDetail) {
        budgetAdjustmentDetailDAO.addBudgetAdjustmentDetail(budgetAdjustmentDetail);
    }

    @Override
    public List<BudgetAdjustmentDetail> getBudgetAdjustmentDetailsByBudgetId(String budgetId) {
        return budgetAdjustmentDetailDAO.getBudgetAdjustmentDetailsByBudgetId(budgetId);
    }

    @Override
    public List<BudgetAdjustmentDetail> getBudgetAdjustmentDetailsByDocument(String document) {
        return budgetAdjustmentDetailDAO.getBudgetAdjustmentDetailsByDocument(document);
    }
}
