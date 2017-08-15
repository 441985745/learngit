package com.opple.fa.config.service;


import com.opple.fa.config.entity.BudgetAdjustment;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/3/21.
 */
public interface BudgetAdjustmentService {

    /**
     * 根据项目编码和年月获取单据号
     * @param budgetAdjustmentDetail
     * @return
     */
    String getDocumentByProjectCodeAndYear(BudgetAdjustmentDetail budgetAdjustmentDetail);
    /**
     * 根据单据号更新数据
     * @param budgetAdjustment
     */
    ExecuteResult<BudgetAdjustment> updateBudgetAdjustmentByDocument(BudgetAdjustment budgetAdjustment, List<BudgetAdjustmentDetail> budgetAdjustmentDetails);

    /**
     * 修改为草稿
     *
     * @param document
     */
    void updateBudgetAdjustmentToDraftByDocument(String document);
    /**
     * 模糊查询预算调整
     * @param pager
     * @param budgetAdjustment
     * @return
     */
    Pager<BudgetAdjustment> getBudgetAdjustmentList(Pager<BudgetAdjustment> pager, BudgetAdjustment budgetAdjustment);

    /**
     * 添加预算调整信息
     * @param budgetAdjustment
     * @param budgetAdjustmentDetails
     */
    ExecuteResult<BudgetAdjustment> addBudgetAdjustment(BudgetAdjustment budgetAdjustment, List<BudgetAdjustmentDetail> budgetAdjustmentDetails);

    /**
     * 导出
     * @param budgetAdjustment
     * @return
     */
    List<BudgetAdjustment> exportBudgetAdjustmentList(BudgetAdjustment budgetAdjustment);

    /**
     * 删除
     * @param budgetAdjustment
     */
    void deleteBudgetAdjustmentById(BudgetAdjustment budgetAdjustment);

    /**
     * 根据单据号删除单据
     * @param document
     */
    void deleteBudgetAdjustmentByDocument(String document);

    /**
     * 根据主键id查询单据
     * @param budgetId
     * @return
     */
    BudgetAdjustment getBudgetAdjustment(String budgetId);

    /**
     * 根据单据号查询
     * @param doucument
     * @return
     */
    BudgetAdjustment getBudgetAdjustmentByDocument(String doucument);

    /**
     *绑定
     *@Param budgetAdjustment
     *@return
     */
    ExecuteResult<BudgetAdjustment> bindApplyWorkFlow(BudgetAdjustment budgetAdjustment);
    /**
     *同意
     *@Param budgetAdjustment
     *@return
     */
    ExecuteResult<BudgetAdjustment> agreeWorkFlow(BudgetAdjustment budgetAdjustment);
    /**
     *退回
     *@Param budgetAdjustment
     *@return
     */
    ExecuteResult<BudgetAdjustment> denyWorkFlow(BudgetAdjustment budgetAdjustment);
    /**
     * 弃审
     * @param budgetAdjustment
     * @return
     */
    ExecuteResult<BudgetAdjustment> dropWorkFlow(BudgetAdjustment budgetAdjustment);

}
