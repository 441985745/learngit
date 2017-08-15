package com.opple.fa.config.service.impl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.config.dao.StandardComputerConfigDAO;
import com.opple.fa.config.entity.StandardComputerConfig;
import com.opple.fa.config.service.StandardComputerConfigService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardComputerConfigServiceImpl implements StandardComputerConfigService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkAddressServiceImpl.class);
	@Getter @Setter
	private StandardComputerConfigDAO standardComputerConfigDAO;
	
	@Override
	public ExecuteResult<StandardComputerConfig> save(StandardComputerConfig standardComputerConfig) {
		ExecuteResult<StandardComputerConfig> executeResult = new ExecuteResult<StandardComputerConfig>();
		try {
			standardComputerConfigDAO.save(standardComputerConfig);
			executeResult.setSuccessMessage("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<List<StandardComputerConfig>> saveBatch(
			List<StandardComputerConfig> standardComputerConfigs) {
		// TODO Auto-generated method stub
		ExecuteResult<List<StandardComputerConfig>> executeResult = new ExecuteResult<List<StandardComputerConfig>>();
		try {
			for (int i = 0; i < standardComputerConfigs.size(); i++) {
				StandardComputerConfig standardComputerConfig = standardComputerConfigs.get(i);
				standardComputerConfigDAO.save(standardComputerConfig);
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
	public ExecuteResult<StandardComputerConfig> delete(StandardComputerConfig standardComputerConfig) {
		ExecuteResult<StandardComputerConfig> executeResult = new ExecuteResult<StandardComputerConfig>();
		try {
			standardComputerConfigDAO.delete(standardComputerConfig);
			executeResult.setSuccessMessage("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<StandardComputerConfig> update(StandardComputerConfig standardComputerConfig) {
		ExecuteResult<StandardComputerConfig> executeResult = new ExecuteResult<StandardComputerConfig>();
		try {
			standardComputerConfigDAO.update(standardComputerConfig);
			executeResult.setSuccessMessage("修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public StandardComputerConfig get(String assetsCode) {
		// TODO Auto-generated method stub
		return standardComputerConfigDAO.get(assetsCode);
	}

	@Override
	public Pager<StandardComputerConfig> searchPagerList(
			Pager<StandardComputerConfig> pager,
			StandardComputerConfig standardComputerConfig) {
		// TODO Auto-generated method stub
		List<StandardComputerConfig> records = standardComputerConfigDAO.searchList(pager, standardComputerConfig);
		Long total = standardComputerConfigDAO.searchListCount(standardComputerConfig);
		return Pager.cloneFromPager(pager, total, records);
	}

	@Override
	public List<String> selectExistCodes(List<String> assetsCodes) {
		// TODO Auto-generated method stub
		return standardComputerConfigDAO.selectExistCodes(assetsCodes);
	}

	@Override
	public Long checkUnitCode(String assetsCode) {
		return standardComputerConfigDAO.checkUnitCode(assetsCode);
	}

	@Override
	public String addOrUpdateUnit(List<StandardComputerConfig> addList, List<StandardComputerConfig> updateList) {
		String result = "";
		try {
			if (null != addList && addList.size() > 0) {
				for (StandardComputerConfig workAddress : addList) {
					standardComputerConfigDAO.save(workAddress);
				}
			}

			if (null != updateList && updateList.size() > 0) {
				for (StandardComputerConfig workAddress : updateList) {
					standardComputerConfigDAO.update(workAddress);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			result = "数据保存失败";
		}
		return result;
	}

	/* (non-Javadoc)不带分页的查询
	 * @see com.opple.fa.config.service.StandardComputerConfigService#getList(com.opple.fa.config.entity.StandardComputerConfig)
	 */
	@Override
	public List<StandardComputerConfig> getList(
			StandardComputerConfig standardComputerConfig) {
		// TODO Auto-generated method stub
		return standardComputerConfigDAO.getList(standardComputerConfig);
	}

	@Override
	public List<StandardComputerConfig> findAll() {
		return standardComputerConfigDAO.findAll();
	}

}
