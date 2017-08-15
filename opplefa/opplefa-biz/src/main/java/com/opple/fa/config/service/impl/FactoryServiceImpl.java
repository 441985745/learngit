package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.FactoryDAO;
import com.opple.fa.config.entity.Factory;
import com.opple.fa.config.service.FactoryService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class FactoryServiceImpl implements FactoryService {
	@Getter @Setter
	private FactoryDAO factoryDAO;
	
	@Override
	public ExecuteResult<Factory> save(Factory factory) {
		ExecuteResult<Factory> executeResult = new ExecuteResult<Factory>();
		try {
			factoryDAO.save(factory);
			executeResult.setSuccessMessage("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<List<Factory>> saveBatch(
			List<Factory> factorys) {
		// TODO Auto-generated method stub
		ExecuteResult<List<Factory>> executeResult = new ExecuteResult<List<Factory>>();
		try {
			for (int i = 0; i < factorys.size(); i++) {
				Factory factory = factorys.get(i);
				factoryDAO.save(factory);
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
	public ExecuteResult<Factory> delete(Factory factory) {
		ExecuteResult<Factory> executeResult = new ExecuteResult<Factory>();
		try {
			factoryDAO.delete(factory);
			executeResult.setSuccessMessage("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<Factory> update(Factory factory) {
		ExecuteResult<Factory> executeResult = new ExecuteResult<Factory>();
		try {
			factoryDAO.update(factory);
			executeResult.setSuccessMessage("修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public Factory get(String factoryCode) {
		// TODO Auto-generated method stub
		return factoryDAO.get(factoryCode);
	}

	@Override
	public Pager<Factory> searchPagerList(
			Pager<Factory> pager,
			Factory factory) {
		// TODO Auto-generated method stub
		List<Factory> records = factoryDAO.searchList(pager, factory);
		Long total = factoryDAO.searchListCount(factory);
		return Pager.cloneFromPager(pager, total, records);
	}

	@Override
	public List<String> selectExistCodes(List<String> factoryCodes) {
		// TODO Auto-generated method stub
		return factoryDAO.selectExistCodes(factoryCodes);
	}

	/* (non-Javadoc)不带分页的查询
	 * @see com.opple.fa.config.service.FactoryService#getList(com.opple.fa.config.entity.Factory)
	 */
	@Override
	public List<Factory> getList(
			Factory factory) {
		// TODO Auto-generated method stub
		return factoryDAO.getList(factory);
	}

	@Override
	public List<Factory> searchByCompanyCode(String companyCode) {
		return factoryDAO.searchByCompanyCode(companyCode);
	}

	@Override
	public Integer checkFactoryCode(String factoryCode) {
		return factoryDAO.checkFactoryCode(factoryCode);
	}

	@Override
	public void addOrUpdateFactory(List<Factory> addList, List<Factory> updateList) {
		if (null != addList && addList.size() > 0) {
			for (Factory factory : addList) {
				factoryDAO.save(factory);
			}
		}
		if (null != updateList && updateList.size() > 0) {
			for (Factory factory : updateList) {
				factoryDAO.update(factory);
			}
		}
	}
}
