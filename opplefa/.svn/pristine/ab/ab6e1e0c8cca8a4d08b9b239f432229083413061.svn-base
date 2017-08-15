package com.opple.fa.allocation.service.impl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.allocation.dao.SapCostCenterPersonDAO;
import com.opple.fa.allocation.entity.SapCostCenterPerson;
import com.opple.fa.allocation.service.SapCostCenterPersonService;

public class SapCostCenterPersonServiceImpl implements
		SapCostCenterPersonService {
	@Setter
	@Getter
	private SapCostCenterPersonDAO sapCostCenterPersonDAO;

	@Override
	public List<SapCostCenterPerson> serachSapCostCenterPerson(
			List<String> listCostCenterCode) {

		return sapCostCenterPersonDAO
				.serachSapCostCenterPerson(listCostCenterCode);
	}
}
