package com.opple.fa.config.webapp.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opple.fa.config.entity.PersoninchargeConfig;
import com.opple.fa.config.service.PersoninchargeConfigService;


@Controller
@RequestMapping("/personinchargeConfig")
public class PersoninchargeConfigController {
	@Resource
	private PersoninchargeConfigService personinchargeConfigService;
	
	@RequestMapping(value = "/api/searchByCostcenterCode")
	@ResponseBody
	public PersoninchargeConfig searchByCostcenterCode(PersoninchargeConfig personinchargeConfig) {
		PersoninchargeConfig searchByCostcenterCode = personinchargeConfigService.searchByCostcenterCode(personinchargeConfig);
		return searchByCostcenterCode;
	}

}
