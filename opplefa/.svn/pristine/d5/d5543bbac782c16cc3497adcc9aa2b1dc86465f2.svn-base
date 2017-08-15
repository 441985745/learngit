package com.opple.fa.config.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.config.entity.BudgetAssetstypeConfig;
import com.opple.util.Pager;

public interface BudgetAssetstypeConfigDAO {

	/**
	 * 根据预算资产类型查询
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	BudgetAssetstypeConfig searchByBudgetAssetstype(BudgetAssetstypeConfig budgetAssetstypeConfig);

	int save(@Param("budgetAssetstypeConfig") BudgetAssetstypeConfig budgetAssetstypeConfig);
	Long saveBatch(List<BudgetAssetstypeConfig> budgetAssetstypeConfigs);
	
	/**删除，保存操作人信息
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	int delete(@Param("budgetAssetstypeConfig") BudgetAssetstypeConfig budgetAssetstypeConfig);
	int update(@Param("budgetAssetstypeConfig") BudgetAssetstypeConfig budgetAssetstypeConfig);

	void updateByType(@Param("budgetAssetstypeConfig") BudgetAssetstypeConfig budgetAssetstypeConfig);

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return
	 */
	BudgetAssetstypeConfig get(String id);

	/**
	 * 分页查询
	 * @param pager
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	List<BudgetAssetstypeConfig> searchList(@Param("pager") Pager<?> pager, @Param("budgetAssetstypeConfig") BudgetAssetstypeConfig budgetAssetstypeConfig);
	Long searchListCount(@Param("budgetAssetstypeConfig") BudgetAssetstypeConfig budgetAssetstypeConfig);
	/**不带分页的查询
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	List<BudgetAssetstypeConfig> getList(@Param("budgetAssetstypeConfig") BudgetAssetstypeConfig budgetAssetstypeConfig);
	
	/**根据编码list查询其中在数据库中存在的编码，进行唯一主键重复提示
	 * @param budgetAssetstypeConfigCodes
	 * @return
	 */
	List<String> selectExistCodes(@Param("budgetAssetstypeAssetsTypes") List<String> budgetAssetstypeAssetsTypes);

	Integer checkBudgetAssetType(@Param("budgetAssetstypeConfig") BudgetAssetstypeConfig budgetAssetstypeConfig);
}
