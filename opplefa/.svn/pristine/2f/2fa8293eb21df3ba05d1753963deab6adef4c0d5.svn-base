package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.PositionDAO;
import com.opple.fa.config.entity.Position;
import com.opple.fa.config.service.PositionService;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PositionServiceImpl implements PositionService {
	@Getter @Setter
	private PositionDAO positionDAO;
	
	@Override
	public ExecuteResult<Position> save(Position position) {
		ExecuteResult<Position> executeResult = new ExecuteResult<Position>();
		try {
			positionDAO.save(position);
			executeResult.setSuccessMessage("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<List<Position>> saveBatch(
			List<Position> positions) {
		// TODO Auto-generated method stub
		ExecuteResult<List<Position>> executeResult = new ExecuteResult<List<Position>>();
		try {
			for (int i = 0; i < positions.size(); i++) {
				Position position = positions.get(i);
				positionDAO.save(position);
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
	public ExecuteResult<Position> delete(Position position) {
		ExecuteResult<Position> executeResult = new ExecuteResult<Position>();
		try {
			positionDAO.delete(position);
			executeResult.setSuccessMessage("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public ExecuteResult<Position> update(Position position) {
		ExecuteResult<Position> executeResult = new ExecuteResult<Position>();
		try {
			positionDAO.update(position);
			executeResult.setSuccessMessage("修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			executeResult.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    	return executeResult;
	}

	@Override
	public Position get(String positionCode) {
		// TODO Auto-generated method stub
		return positionDAO.get(positionCode);
	}

	@Override
	public Pager<Position> searchPagerList(
			Pager<Position> pager,
			Position position) {
		// TODO Auto-generated method stub
		List<Position> records = positionDAO.searchList(pager, position);
		Long total = positionDAO.searchListCount(position);
		return Pager.cloneFromPager(pager, total, records);
	}

	@Override
	public List<String> selectExistCodes(List<String> positionCodes) {
		// TODO Auto-generated method stub
		return positionDAO.selectExistCodes(positionCodes);
	}

	/* (non-Javadoc)不带分页的查询
	 * @see com.opple.fa.config.service.PositionService#getList(com.opple.fa.config.entity.Position)
	 */
	@Override
	public List<Position> getList(
			Position position) {
		// TODO Auto-generated method stub
		return positionDAO.getList(position);
	}

	@Override
	public Integer checkPositionCode(String positionCode) {
		return positionDAO.checkPositionCode(positionCode);
	}

	@Override
	public void addOrUpdatePosition(List<Position> addList, List<Position> upateList) {
		if (null != addList) {
			for (Position position : addList) {
				positionDAO.save(position);
			}
		}
		if (null != upateList) {
			for (Position position : upateList) {
				positionDAO.update(position);
			}
		}
	}

    @Override
    public Integer checkPositionName(Position position) {
        return positionDAO.checkPositionName(position);
    }
}
