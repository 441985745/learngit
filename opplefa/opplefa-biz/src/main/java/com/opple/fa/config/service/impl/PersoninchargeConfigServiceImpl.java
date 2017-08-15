package com.opple.fa.config.service.impl;


import com.opple.fa.config.dao.PersoninchargeConfigDAO;
import com.opple.fa.config.entity.PersoninchargeConfig;
import com.opple.fa.config.service.PersoninchargeConfigService;

import lombok.Getter;
import lombok.Setter;

public class PersoninchargeConfigServiceImpl implements PersoninchargeConfigService {
	@Getter @Setter
	private PersoninchargeConfigDAO personinchargeConfigDAO;

	@Override
	public PersoninchargeConfig searchByCostcenterCode(PersoninchargeConfig personinchargeConfig) {
		return personinchargeConfigDAO.searchByCostcenterCode(personinchargeConfig);
	}

	
	
}
