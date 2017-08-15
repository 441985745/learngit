package com.opple.fa.config.service;


import com.opple.fa.config.entity.BudgetAdjustment;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/3/21.
 */
public interface BudgetAdjustmentDetailService {

    /**
     * 给新增类型数据添加项目编码
     * @param budgetAdjustmentDetail
     */
    void addProjectCodeForDetail(BudgetAdjustmentDetail budgetAdjustmentDetail);
    /**
     * 修改详情表
     * @param budgetAdjustment
     * @param budgetAdjustmentDetails
     */
//    void updateBudgetAdjustmentDetail(BudgetAdjustment budgetAdjustment, List<BudgetAdjustmentDetail> budgetAdjustmentDetails);

    /**
     * 跟据主表id删除详细信息
     * @param budgetAdjustment
     */
    void deleteBudgetAdjsutDetialByBudgetId(BudgetAdjustment budgetAdjustment);
    /**
     * 添加数据
     * @param budgetAdjustmentDetail
     */
    void addBudgetAdjustmentDetail(BudgetAdjustmentDetail budgetAdjustmentDetail);

    /**
     * 根据主表id查询详细信息
     * @param budgetId
     * @return
     */
    List<BudgetAdjustmentDetail> getBudgetAdjustmentDetailsByBudgetId(String budgetId);

    /**
     * 根据单据号查询详细信息
     * @param document
     * @return
     */
    List<BudgetAdjustmentDetail> getBudgetAdjustmentDetailsByDocument(String document);
}
