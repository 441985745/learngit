package com.opple.fa.config.service.impl;



import com.opple.fa.config.dao.CostCenterPlatformDAO;
import com.opple.fa.config.entity.CostCenterPlatform;
import com.opple.fa.config.service.CostCenterPlatformService;

import lombok.Getter;
import lombok.Setter;

public class CostCenterPlatformServiceImpl implements CostCenterPlatformService {
	@Getter @Setter
	private CostCenterPlatformDAO costCenterPlatformDAO;

	@Override
	public CostCenterPlatform searchPlatformByCostCenterCode(String costCenterCode) {
		return costCenterPlatformDAO.searchPlatformByCostCenterCode(costCenterCode);
	}
	
	
}
