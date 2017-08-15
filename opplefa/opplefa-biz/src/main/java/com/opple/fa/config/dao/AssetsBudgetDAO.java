package com.opple.fa.config.dao;

import com.opple.fa.config.entity.AssetsBudget;
import com.opple.fa.config.entity.BudgetAdjustmentDetail;
import com.opple.util.Pager;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by phoenix on 9/2/16.
 */
public interface AssetsBudgetDAO {
	/**
	 * 模糊查询assetsBudget
	 * 
	 * @param pager
	 * @param assetsBudget
	 * @return
	 */
	List<AssetsBudget> searchAssetsBudgets(@Param("pager") Pager<AssetsBudget> pager,
			@Param("assetsBudget") AssetsBudget assetsBudget);

	/**
	 * 模糊查询assetsBudget数量
	 * 
	 * @param assetsBudget
	 * @return
	 */
	Long searchAssetsBudgetsCount(@Param("assetsBudget") AssetsBudget assetsBudget);

	List searchAssetsBudgetsList(@Param("assetsBudget") AssetsBudget assetsBudget);

	/**
	 * 保存采购需求订单时 回写可用预算数量及可用预算金额
	 * 
	 * @param demandOrderDetailModel
	 */
	void writeBack(AssetsBudget assetsBudget);

	/**
	 * 根据项目编码获得预算信息
	 * 
	 * @param assetsBudget
	 * @return
	 */
	AssetsBudget searchAssetsBudgetsByProjectCode(@Param("assetsBudget") AssetsBudget assetsBudget);

	Long importAssetsBudgetExcel(List<AssetsBudget> list);
	/**
	 * 新增预算
	 * 
	 * @param assetsBudget
	 * @return
	 */
	void insertAssetsBudgets(@Param("assetsBudget") AssetsBudget assetsBudget);
	/**
	 * 更新预算
	 * 
	 * @param assetsBudget
	 * @return
	 */
	void updateAssetsBudgets(@Param("assetsBudget") AssetsBudget assetsBudget);
	/**
	 * 根据项目编码查询预算是否存在
	 * 
	 * @param assetsBudget
	 * @return long
	 */
	Long getAssetsBudgetsbyProjectCode(@Param("assetsBudget") AssetsBudget assetsBudget);
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
	void confirmBudget(@Param("assetsBudget") AssetsBudget assetsBudget);
	/**
	 * 取消确认预算
	 * 
	 * @param assetsBudget
	 * @return 
	 */
	void concelConfirmBudget(@Param("assetsBudget") AssetsBudget assetsBudget);
	/**
	 * 确认所有预算数据
	 * 
	 * @param 
	 */
	void confirmAllBudget();
	/**
	 * 删除预算
	 * 
	 * @param assetsBudget
	 * @return 
	 */
	void deleteBudget(@Param("assetsBudget") AssetsBudget assetsBudget);
	/**
	 * 更新所有数据版本号
	 * @param assetsBudget
	 * @return
	 */
	void updateVersion(@Param("assetsBudget") AssetsBudget assetsBudget);

	/**
	 * 根据项目编码和年度查询预算信息（预算调整导入）
	 * @param year
	 * @param projectCode
	 * @return
	 */
	BudgetAdjustmentDetail getBudgetDetailsbyProjectCodeAndYear(@Param("year") String year, @Param("projectCode") String projectCode);

	/**
	 * 调整审批结束后更新预算表
	 * @param budgetAdjustmentDetail
	 */
	void updateAssetsBudgetAfterApprove(@Param("budgetAdjustmentDetail")BudgetAdjustmentDetail budgetAdjustmentDetail);
    void deleteAssetsBudgetAfterDrop(@Param("budgetAdjustmentDetail")BudgetAdjustmentDetail budgetAdjustmentDetail);
	void addAssetsBudgetAfterApprove(@Param("budgetAdjustmentDetail")BudgetAdjustmentDetail budgetAdjustmentDetail);

    /**
	 * 导出预算数据
	 * @param assetsBudget
	 * @return List<AssetsBudget>
	 */
	List<AssetsBudget> exportAssetsBudget(@Param("assetsBudget") AssetsBudget assetsBudget);
	/**
	 * 根据ID查询单条预算详情
	 * @param assetsBudget
	 * @return List<AssetsBudget>
	 */
	AssetsBudget getAssetsBudget(@Param("assetsBudget") AssetsBudget assetsBudget);
	/**
	 * 根据项目编码和年度查询单条预算信息
	 * @param year
	 * @param projectCode
	 * @return
	 */
	AssetsBudget getAssetsBudgetbyProjectCodeAndYear(@Param("year") String year, @Param("projectCode") String projectCode);

	/**
	 * 将预算表数据插入到历史数据表中
	 */
	void saveDataToHistory();
}
