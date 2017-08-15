package com.opple.fa.assetcard.dao;

import com.opple.fa.assetcard.entity.AdministrationAsset;

public interface AdministrationAssetDAO {
	/**
	 * 添加行政类资产
	 * @param administrationAsset
	 */
	void insertAdministrationAsset(AdministrationAsset administrationAsset);
	/**
	 * 获得单条行政资产
	 * 
	 */
	AdministrationAsset getAdministrationAsset(AdministrationAsset administrationAsset);
	/**
	 * 行政资产修改
	 * @param administrationAsset
	 */
	void updateAdministrationAsset(AdministrationAsset administrationAsset);
	/**
	 * 导入如果重复 修改资产
	 * @param administrationAsset
	 */
	void updateLeadingAdministration(AdministrationAsset administrationAsset);

    void deleteAdministrationAsset(Integer id);

    void updateAdministrationAsset1(AdministrationAsset administrationAsset);

    void updateAdministrationAsset2(AdministrationAsset administrationAsset);
}
