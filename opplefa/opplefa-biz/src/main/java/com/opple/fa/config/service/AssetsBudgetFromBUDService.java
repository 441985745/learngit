package com.opple.fa.config.service;

import java.util.List;

import com.opple.fa.config.entity.AssetsBudgetFromBUD;

public interface AssetsBudgetFromBUDService {

	/**
     * 查询所有预算系统推送的数据
     * @return
     */
	List<AssetsBudgetFromBUD> searchAllAssetsBudgetFromBUD();
}
