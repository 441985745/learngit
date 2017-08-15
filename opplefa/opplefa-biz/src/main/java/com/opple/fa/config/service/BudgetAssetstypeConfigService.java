package com.opple.fa.config.service;

import com.opple.fa.config.entity.BudgetAssetstypeConfig;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

import java.util.List;

public interface BudgetAssetstypeConfigService {
	/**
	 * 根据预算资产类型查询
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	BudgetAssetstypeConfig searchByBudgetAssetstype(BudgetAssetstypeConfig budgetAssetstypeConfig);
	
	/**单条保存
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	ExecuteResult<BudgetAssetstypeConfig> save(BudgetAssetstypeConfig budgetAssetstypeConfig);
	
	/**批量保存
	 * @param budgetAssetstypeConfigs
	 * @return
	 */
	ExecuteResult<List<BudgetAssetstypeConfig>> saveBatch(List<BudgetAssetstypeConfig> budgetAssetstypeConfigs);
	
	/**删除，根据budgetAssetstypeConfigCodes的值删除  ---（private List<String> budgetAssetstypeConfigCodes;// 资产编码--删除使用）
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	ExecuteResult<BudgetAssetstypeConfig> delete(BudgetAssetstypeConfig budgetAssetstypeConfig);
	
	/**更新
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	ExecuteResult<BudgetAssetstypeConfig> update(BudgetAssetstypeConfig budgetAssetstypeConfig);
	
	/**单条查询
	 * @param id
	 * @return
	 */
	BudgetAssetstypeConfig get(String id);
	
	/**分页查询
	 * @param pager
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	Pager<BudgetAssetstypeConfig> searchPagerList(Pager<BudgetAssetstypeConfig> pager, BudgetAssetstypeConfig budgetAssetstypeConfig);
	
	/**分页查询
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	List<BudgetAssetstypeConfig> getList(BudgetAssetstypeConfig budgetAssetstypeConfig);
	
	/**查询数据库中资产编码重复项
	 * @param budgetAssetstypeAssetsTypes
	 * @return
	 */
	List<String> selectExistCodes(List<String> budgetAssetstypeAssetsTypes);

	/**
	 * 根据预算资产类型和资产类型查询有无数据
	 *
	 * @param budgetAssetstypeConfig
	 * @return
	 */
	Integer checkBudgetAssetType(BudgetAssetstypeConfig budgetAssetstypeConfig);

	String addOrUpdateBudgetAssetType(List<BudgetAssetstypeConfig> addList, List<BudgetAssetstypeConfig> updateList);
}
