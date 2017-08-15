package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.AssetUnitDAO;
import com.opple.fa.config.entity.AssetUnit;
import com.opple.fa.config.service.AssetUnitService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AssetUnitServiceImpl implements AssetUnitService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AssetUnitServiceImpl.class);
	@Getter @Setter
	private AssetUnitDAO assetUnitDAO;

	@Override
	public AssetUnit getAssetUnit(AssetUnit assetUnit) {
		
		return assetUnitDAO.getAssetUnit(assetUnit);
	}

	@Override
	public List<AssetUnit> getAssetUnitList() {
		return assetUnitDAO.getAssetUnitList();
	}
	@Override
	public ExecuteResult<AssetUnit> save(AssetUnit assetUnit) {
		ExecuteResult<AssetUnit> executeResult = new ExecuteResult<AssetUnit>();
		try {
			assetUnitDAO.save(assetUnit);
			executeResult.setSuccessMessage("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<List<AssetUnit>> saveBatch(
			List<AssetUnit> assetUnits) {
		// TODO Auto-generated method stub
		ExecuteResult<List<AssetUnit>> executeResult = new ExecuteResult<List<AssetUnit>>();
		try {
			for (int i = 0; i < assetUnits.size(); i++) {
				AssetUnit assetUnit = assetUnits.get(i);
				assetUnitDAO.save(assetUnit);
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
	public ExecuteResult<AssetUnit> delete(AssetUnit assetUnit) {
		ExecuteResult<AssetUnit> executeResult = new ExecuteResult<AssetUnit>();
		try {
			assetUnitDAO.delete(assetUnit);
			executeResult.setSuccessMessage("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<AssetUnit> update(AssetUnit assetUnit) {
		ExecuteResult<AssetUnit> executeResult = new ExecuteResult<AssetUnit>();
		try {
			assetUnitDAO.update(assetUnit);
			executeResult.setSuccessMessage("修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public AssetUnit get(String unitCode) {
		// TODO Auto-generated method stub
		return assetUnitDAO.get(unitCode);
	}

	@Override
	public Pager<AssetUnit> searchPagerList(
			Pager<AssetUnit> pager,
			AssetUnit assetUnit) {
		// TODO Auto-generated method stub
		List<AssetUnit> records = assetUnitDAO.searchList(pager, assetUnit);
		Long total = assetUnitDAO.searchListCount(assetUnit);
		return Pager.cloneFromPager(pager, total, records);
	}

	@Override
	public List<String> selectExistCodes(List<String> unitCodes) {
		// TODO Auto-generated method stub
		return assetUnitDAO.selectExistCodes(unitCodes);
	}

	/* (non-Javadoc)不带分页的查询
	 * @see com.opple.fa.config.service.AssetUnitService#getList(com.opple.fa.config.entity.AssetUnit)
	 */
	@Override
	public List<AssetUnit> getList(
			AssetUnit assetUnit) {
		// TODO Auto-generated method stub
		return assetUnitDAO.getList(assetUnit);
	}

	@Override
	public Integer checkUnitCode(String unitCode) {
		return assetUnitDAO.checkUnitCode(unitCode);
	}

	@Override
	public String addOrUpdateUnit(List<AssetUnit> addList, List<AssetUnit> updateList) {
		String result = "";
		try {
			if (null != addList && addList.size() > 0) {
				for (AssetUnit assetUnit : addList) {
					assetUnitDAO.save(assetUnit);
				}
			}

			if (null != updateList && updateList.size() > 0) {
				for (AssetUnit assetUnit : updateList) {
					assetUnitDAO.update(assetUnit);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			result = "数据保存失败";
		}
		return result;
	}

    @Override
    public Long selectExistNames(String unitName) {
        return assetUnitDAO.selectExistNames(unitName);
    }
}