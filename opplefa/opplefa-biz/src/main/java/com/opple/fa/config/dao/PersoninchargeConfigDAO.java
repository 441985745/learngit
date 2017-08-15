package com.opple.fa.config.dao;

import com.opple.fa.config.entity.PersoninchargeConfig;

public interface PersoninchargeConfigDAO {

	/**
	 * 根据成本中心查询负责人表
	 * @return
	 */
	PersoninchargeConfig searchByCostcenterCode(PersoninchargeConfig personinchargeConfig);
}
