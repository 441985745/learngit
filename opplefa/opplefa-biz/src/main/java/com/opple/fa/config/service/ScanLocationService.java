package com.opple.fa.config.service;


import java.util.List;

import com.opple.fa.config.entity.ScanLocation;


public interface ScanLocationService {
	/**
	 * 查询所有扫描地点
	 * @return
	 */
	List<ScanLocation> findAll();
}
