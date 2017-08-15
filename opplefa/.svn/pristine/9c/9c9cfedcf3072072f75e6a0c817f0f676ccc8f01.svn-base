package com.opple.fa.config.service.impl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.config.dao.WorkAddressDAO;
import com.opple.fa.config.entity.WorkAddress;
import com.opple.fa.config.service.WorkAddressService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkAddressServiceImpl implements WorkAddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkAddressServiceImpl.class);

	@Getter @Setter
	private WorkAddressDAO workAddressDAO;
	
	@Override
	public ExecuteResult<WorkAddress> save(WorkAddress workAddress) {
		ExecuteResult<WorkAddress> executeResult = new ExecuteResult<WorkAddress>();
		try {
			workAddressDAO.save(workAddress);
			executeResult.setSuccessMessage("保存成功");
		} catch (Exception e) {
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<List<WorkAddress>> saveBatch(
			List<WorkAddress> workAddresss) {
		ExecuteResult<List<WorkAddress>> executeResult = new ExecuteResult<List<WorkAddress>>();
		try {
			for (int i = 0; i < workAddresss.size(); i++) {
				WorkAddress workAddress = workAddresss.get(i);
				workAddressDAO.save(workAddress);
			}
			executeResult.setSuccessMessage("保存成功");
		} catch (Exception e) {
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}
	
	@Override
	public ExecuteResult<WorkAddress> delete(WorkAddress workAddress) {
		ExecuteResult<WorkAddress> executeResult = new ExecuteResult<WorkAddress>();
		try {
			workAddressDAO.delete(workAddress);
			executeResult.setSuccessMessage("删除成功");
		} catch (Exception e) {
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<WorkAddress> update(WorkAddress workAddress) {
		ExecuteResult<WorkAddress> executeResult = new ExecuteResult<WorkAddress>();
		try {
			workAddressDAO.update(workAddress);
			executeResult.setSuccessMessage("修改成功");
		} catch (Exception e) {
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public WorkAddress get(String workAddress) {
		return workAddressDAO.get(workAddress);
	}

	@Override
	public Pager<WorkAddress> searchPagerList(
			Pager<WorkAddress> pager,
			WorkAddress workAddress) {
		List<WorkAddress> records = workAddressDAO.searchList(pager, workAddress);
		Long total = workAddressDAO.searchListCount(workAddress);
		return Pager.cloneFromPager(pager, total, records);
	}

	@Override
	public List<String> selectExistCodes(List<String> caddressCodes) {
		return workAddressDAO.selectExistCodes(caddressCodes);
	}
	public List<String> selectExistNames(List<String> caddressNames) {
			return workAddressDAO.selectExistNames(caddressNames);
		}

	@Override
	public Long checkUnitCode(String caddressCode) {
		return workAddressDAO.checkUnitCode(caddressCode);
	}

	@Override
	public String addOrUpdateUnit(List<WorkAddress> addList, List<WorkAddress> updateList) {
		String result = "";
		try {
			if (null != addList && addList.size() > 0) {
				for (WorkAddress workAddress : addList) {
					workAddressDAO.save(workAddress);
				}
			}

			if (null != updateList && updateList.size() > 0) {
				for (WorkAddress workAddress : updateList) {
					workAddressDAO.update(workAddress);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			result = "数据保存失败";
		}
		return result;
	}

	/* (non-Javadoc)不带分页的查询
	 * @see com.opple.fa.config.service.WorkAddressService#getList(com.opple.fa.config.entity.WorkAddress)
	 */
    @Override
    public Long selectExistNames(WorkAddress workAddress) {
        return workAddressDAO.selectExistName(workAddress);
    }

	@Override
	public Long searchWorkAddress(String addressCode, String addressName) {
		return workAddressDAO.searchWorkAddress(addressCode, addressName);
	}

	@Override
	public Pager<WorkAddress> searchWorkAddressCodeAndName(Pager<WorkAddress> pager, WorkAddress workAddress) {
		List<WorkAddress> records = workAddressDAO.searchCodeAndName(pager, workAddress);
		Long total = workAddressDAO.searchListCount(workAddress);
		return Pager.cloneFromPager(pager, total, records);
	}

	@Override
	public Integer selectCodeAndName(String caddressCode, String caddressName) {
		return workAddressDAO.selectCodeAndName(caddressCode, caddressName);
	}

	/* (non-Javadoc)不带分页的查询
     * @see com.opple.fa.config.service.WorkAddressService#getList(com.opple.fa.config.entity.WorkAddress)
     */
	@Override
	public List<WorkAddress> getList(
			WorkAddress workAddress) {
		return workAddressDAO.getList(workAddress);
	}

}
