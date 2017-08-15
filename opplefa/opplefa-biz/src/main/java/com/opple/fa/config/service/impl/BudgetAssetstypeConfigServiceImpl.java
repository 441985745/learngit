package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.BudgetAssetstypeConfigDAO;
import com.opple.fa.config.entity.BudgetAssetstypeConfig;
import com.opple.fa.config.service.BudgetAssetstypeConfigService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by lk on 10/24/16.
 */
public class BudgetAssetstypeConfigServiceImpl implements BudgetAssetstypeConfigService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BudgetAssetstypeConfigServiceImpl.class);
	@Getter @Setter
    private BudgetAssetstypeConfigDAO budgetAssetstypeConfigDAO;

	@Override
	public BudgetAssetstypeConfig searchByBudgetAssetstype(BudgetAssetstypeConfig budgetAssetstypeConfig) {
		return budgetAssetstypeConfigDAO.searchByBudgetAssetstype(budgetAssetstypeConfig);
	}
	
	@Override
	public ExecuteResult<BudgetAssetstypeConfig> save(BudgetAssetstypeConfig budgetAssetstypeConfig) {
		ExecuteResult<BudgetAssetstypeConfig> executeResult = new ExecuteResult<BudgetAssetstypeConfig>();
		try {
			budgetAssetstypeConfigDAO.save(budgetAssetstypeConfig);
			executeResult.setSuccessMessage("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<List<BudgetAssetstypeConfig>> saveBatch(
			List<BudgetAssetstypeConfig> budgetAssetstypeConfigs) {
		// TODO Auto-generated method stub
		ExecuteResult<List<BudgetAssetstypeConfig>> executeResult = new ExecuteResult<List<BudgetAssetstypeConfig>>();
		try {
			for (int i = 0; i < budgetAssetstypeConfigs.size(); i++) {
				BudgetAssetstypeConfig budgetAssetstypeConfig = budgetAssetstypeConfigs.get(i);
				budgetAssetstypeConfigDAO.save(budgetAssetstypeConfig);
			}
			executeResult.setSuccessMessage("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}
	
	@Override
	public ExecuteResult<BudgetAssetstypeConfig> delete(BudgetAssetstypeConfig budgetAssetstypeConfig) {
		ExecuteResult<BudgetAssetstypeConfig> executeResult = new ExecuteResult<BudgetAssetstypeConfig>();
		try {
			budgetAssetstypeConfigDAO.delete(budgetAssetstypeConfig);
			executeResult.setSuccessMessage("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<BudgetAssetstypeConfig> update(BudgetAssetstypeConfig budgetAssetstypeConfig) {
		ExecuteResult<BudgetAssetstypeConfig> executeResult = new ExecuteResult<BudgetAssetstypeConfig>();
		try {
			budgetAssetstypeConfigDAO.update(budgetAssetstypeConfig);
			executeResult.setSuccessMessage("修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public BudgetAssetstypeConfig get(String id) {
		// TODO Auto-generated method stub
		return budgetAssetstypeConfigDAO.get(id);
	}

	@Override
	public Pager<BudgetAssetstypeConfig> searchPagerList(
			Pager<BudgetAssetstypeConfig> pager,
			BudgetAssetstypeConfig budgetAssetstypeConfig) {
		// TODO Auto-generated method stub
		List<BudgetAssetstypeConfig> records = budgetAssetstypeConfigDAO.searchList(pager, budgetAssetstypeConfig);
		Long total = budgetAssetstypeConfigDAO.searchListCount(budgetAssetstypeConfig);
		return Pager.cloneFromPager(pager, total, records);
	}

	@Override
	public List<String> selectExistCodes(List<String> budgetAssetstypeAssetsTypes) {
		// TODO Auto-generated method stub			
		return budgetAssetstypeConfigDAO.selectExistCodes(budgetAssetstypeAssetsTypes);
	}

	/* (non-Javadoc)不带分页的查询
	 * @see com.opple.fa.config.service.BudgetAssetstypeConfigService#getList(com.opple.fa.config.entity.BudgetAssetstypeConfig)
	 */
	@Override
	public List<BudgetAssetstypeConfig> getList(
			BudgetAssetstypeConfig budgetAssetstypeConfig) {
		// TODO Auto-generated method stub
		return budgetAssetstypeConfigDAO.getList(budgetAssetstypeConfig);
	}

	@Override
	public Integer checkBudgetAssetType(BudgetAssetstypeConfig budgetAssetstypeConfig) {
		return budgetAssetstypeConfigDAO.checkBudgetAssetType(budgetAssetstypeConfig);
	}

	@Override
	public String addOrUpdateBudgetAssetType(List<BudgetAssetstypeConfig> addList, List<BudgetAssetstypeConfig> updateList) {
		String result = "";
		try {
			if (null != addList && addList.size() > 0) {
				for (BudgetAssetstypeConfig budgetAssetstypeConfig : addList) {
					budgetAssetstypeConfigDAO.save(budgetAssetstypeConfig);
				}
			}
			if (null != updateList && updateList.size() > 0) {
				for (BudgetAssetstypeConfig budgetAssetstypeConfig : updateList) {
					budgetAssetstypeConfigDAO.updateByType(budgetAssetstypeConfig);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			result = "数据保存失败";
		}
		return result;
	}
}
