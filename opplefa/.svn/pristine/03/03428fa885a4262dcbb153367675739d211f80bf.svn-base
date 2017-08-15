package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.CompanyDepCostcenterDAO;
import com.opple.fa.config.entity.CompanyDepCostcenter;
import com.opple.fa.config.service.CompanyDepCostcenterService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CompanyDepCostcenterServiceImpl implements CompanyDepCostcenterService {
	@Setter
	@Getter
	private CompanyDepCostcenterDAO companyDepCostcenterDAO;
	@Override
	public List<CompanyDepCostcenter> serachCompanyDepCostcenter(
			CompanyDepCostcenter companyDepCostcenter) {
		
		return companyDepCostcenterDAO.serachCompanyDepCostcenter(companyDepCostcenter);
	}
	
}
