package com.opple.fa.assetcard.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.assetcard.entity.ComputerAsset;

public interface ComputerAssetDAO {

	/**
	 * 获得IT资产数据
	 * @return
	 */
	ComputerAsset searchComputerAsset(@Param("computerAsset")ComputerAsset computerAsset);
	/**
	 * 保存电脑类
	 * @return
	 */
	void insertComputerAsset(ComputerAsset computerAsset);
	
	void updateComputerAsset(ComputerAsset computerAsset);

	/**
	 * 更新IT资产数据 -- 验收
	 * @param computerAsset
	 */
    void updateComputerAssetCard(ComputerAsset computerAsset);

    void deleteComputerById(Integer id);

    void updateComputerAssetCard1(ComputerAsset computerAsset);

    void updateComputerAssetCard2(ComputerAsset computerAsset);
}
