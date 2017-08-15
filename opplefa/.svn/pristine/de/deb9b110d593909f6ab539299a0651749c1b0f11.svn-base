package com.opple.fa.allocation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.allocation.entity.SapCostCenterPerson;

public interface SapCostCenterPersonDAO {
	/**
	 * 通过成本中心编码 查找成本中心负责人
	 * 
	 * @param listCostCenterCode
	 * @return
	 */
	List<SapCostCenterPerson> serachSapCostCenterPerson(
			@Param("listCostCenterCode") List<String> listCostCenterCode);
}
