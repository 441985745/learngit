package com.opple.fa.config.dao;

import com.opple.fa.config.entity.BudgetAdjustment;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/3/21.
 */
public interface BudgetAdjustmentDAO {

    /**
     * 根据子表项目编码和年度查询未审批完成的流程单号
     * @param budgetAdjustmentDetail
     * @return
     */
    String getDocumentByProjectCodeAndYear(@Param("budgetAdjustmentDetail") BudgetAdjustmentDetail budgetAdjustmentDetail);
    /**
     * 查询预算调整页
     * @param pager
     * @param budgetAdjustment
     * @return
     */
    List<BudgetAdjustment> getBudgetAdjustmentList(@Param("pager")Pager pager, @Param("budgetAdjustment")BudgetAdjustment budgetAdjustment);
    Long getBudgetAdjustmentListCount(@Param("budgetAdjustment")BudgetAdjustment budgetAdjustment);

    /**
     * 添加预算调整
     * @param budgetAdjustment
     */
    void addBudgetAdjustment(BudgetAdjustment budgetAdjustment);

    /**
     * 导出
     * @param budgetAdjustment
     * @return
     */
    List<BudgetAdjustment> exportBudgetAdjustmentList(@Param("budgetAdjustment")BudgetAdjustment budgetAdjustment);

    /**
     * 删除
     * @param budgetAdjustment
     */
    void updateBudgetAdjustmentById(@Param("budgetAdjustment")BudgetAdjustment budgetAdjustment);


    /**
     * 修改为草稿
     *
     * @param document
     */
    void updateBudgetAdjustmentToDraftByDocument(String document);

    /**
     * 根据单据号删除单据
     * @param document
     */
    void deleteBudgetAdjustmentByDocument(String document);

    /**
     * 根据id查询单据信息
     * @param document
     * @return
     */
    BudgetAdjustment getBudgetAdjustment(@Param("document")String document);

    /**
     * 根据单据号查询
     * @param document
     * @return
     */
    BudgetAdjustment getBudgetAdjustmentByDocument(@Param("document")String document);


    void updateBudgetAdjustmentByDocument(BudgetAdjustment budgetAdjustment);
}
