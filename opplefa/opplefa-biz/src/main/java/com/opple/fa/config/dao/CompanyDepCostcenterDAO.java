package com.opple.fa.config.dao;

import java.util.List;

import com.opple.fa.config.entity.CompanyDepCostcenter;

public interface CompanyDepCostcenterDAO {
	/**
	 * 
	 * @param companyDepCostcenter
	 * @return
	 */
	List<CompanyDepCostcenter> serachCompanyDepCostcenter(CompanyDepCostcenter companyDepCostcenter);
}
