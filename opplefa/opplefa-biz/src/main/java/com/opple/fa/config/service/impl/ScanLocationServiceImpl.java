package com.opple.fa.config.service.impl;

import java.util.List;

import com.opple.fa.config.dao.ScanLocationDAO;
import com.opple.fa.config.entity.ScanLocation;
import com.opple.fa.config.service.ScanLocationService;

import lombok.Getter;
import lombok.Setter;

public class ScanLocationServiceImpl implements ScanLocationService {
	@Getter @Setter
	private ScanLocationDAO scanLocationDAO;
	
	@Override
	public List<ScanLocation> findAll() {
		return scanLocationDAO.findAll();
	}

}
