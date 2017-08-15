package com.opple.fa.config.dao;

import com.opple.fa.config.entity.BudgetAdjustment;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/3/21.
 */
public interface BudgetAdjustmentDetailDAO {

    /**
     * 新增类型添加项目编码
     * @param budgetAdjustmentDetail
     */
    void addProjectCodeForDetail(@Param("budgetAdjustmentDetail") BudgetAdjustmentDetail budgetAdjustmentDetail);
    /**
     * 添加调整明细
     * @param budgetAdjustmentDetail
     */
    void addBudgetAdjustmentDetail(@Param("budgetAdjustmentDetail") BudgetAdjustmentDetail budgetAdjustmentDetail);

    /**
     * 根据主表id删除信息
     * @param budgetAdjustment
     */
    void updateBudgetAdjustmentDetailsById(@Param("budgetAdjustment") BudgetAdjustment budgetAdjustment);
    /**
     * 根据主表单据号删除详细信息
     * @param document
     */
    void deleteBudgetAdjustmentDetailByDocument(String document);

    /**
     * 根据主表id查询
     * @param budgetId
     * @return
     */
    List<BudgetAdjustmentDetail> getBudgetAdjustmentDetailsByBudgetId(String budgetId);

    /**
     * 根据单据号查询
     * @param document
     * @return
     */
    List<BudgetAdjustmentDetail> getBudgetAdjustmentDetailsByDocument(String document);
}
