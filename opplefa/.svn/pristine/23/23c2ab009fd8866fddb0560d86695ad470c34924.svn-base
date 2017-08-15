package com.opple.fa.config.service;


import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by phoenix on 9/2/16.
 */
public interface AssetsBudgetService {
    /**
     * 模糊查询assetsBudget
     *
     * @param pager
     * @param assetsBudget
     * @return
     */
    Pager<AssetsBudget> searchAssetsBudgets(Pager<AssetsBudget> pager, AssetsBudget assetsBudget);

    List<AssetsBudget> searchAssetsBudgetsList(AssetsBudget assetsBudget);

    void importAssetsBudgetExcel(List<AssetsBudget> listForInsert, List<AssetsBudget> listForUpdate);

    /**
     * 根据项目编码查询预算是否存在
     *
     * @param assetsBudget
     * @return long
     */
    Long getAssetsBudgetsbyProjectCode(AssetsBudget assetsBudget);

    /**
     * 根据项目编码和年度查询预算是否存在
     *
     * @param assetsBudget
     * @return long
     */
    Long getAssetsBudgetsbyProjectCodeAndYear(@Param("assetsBudget") AssetsBudget assetsBudget);

    /**
     * 确认预算
     *
     * @param assetsBudget
     * @return
     */
    void confirmBudget(AssetsBudget assetsBudget);

    /**
     * 取消确认预算
     *
     * @param assetsBudget
     * @return
     */
    void concelConfirmBudget(AssetsBudget assetsBudget);

    /**
     * 删除预算
     *
     * @param assetsBudget
     * @return
     */
    void deleteBudget(AssetsBudget assetsBudget);

    /**
     * 确认所有预算数据
     *
     * @param
     */
    void confirmAllBudget();

    /**
     * 根据项目编码和年度查询预算信息（预算调整导入）
     *
     * @param year
     * @param projectCode
     * @return
     */
    BudgetAdjustmentDetail getBudgetDetailsbyProjectCodeAndYear(String year, String projectCode);

    /**
     * 更新所有数据版本号
     *
     * @param assetsBudget
     * @return
     */
    void updateVersion(AssetsBudget assetsBudget);

    /**
     * 调整审批结束后更新预算表
     *
     * @param budgetAdjustmentDetails
     */
    void updateAssetsBudgetAfterApprove(List<BudgetAdjustmentDetail> budgetAdjustmentDetails);

    void addAssetsBudgetAfterApprove(List<BudgetAdjustmentDetail> budgetAdjustmentDetails);

    /**
     * 调整审批弃审后更新预算表
     *
     * @param budgetAdjustmentDetails
     */
    void updateAssetsBudgetAfterDrop(List<BudgetAdjustmentDetail> budgetAdjustmentDetails);

    void deleteAssetsBudgetAfterDrop(List<BudgetAdjustmentDetail> budgetAdjustmentDetails);

    /**
     * 导出预算数据
     *
     * @param assetsBudget
     * @return List<AssetsBudget>
     */
    List<AssetsBudget> exportAssetsBudget(AssetsBudget assetsBudget);

    /**
     * 根据ID查询单条预算详情
     *
     * @param assetsBudget
     * @return List<AssetsBudget>
     */
    AssetsBudget getAssetsBudget(AssetsBudget assetsBudget);
	/**
	 * 根据项目编码和年度查询单条预算信息
	 * @param year
	 * @param projectCode
	 * @return
	 */
	AssetsBudget getAssetsBudgetbyProjectCodeAndYear(String year, String projectCode);
}
