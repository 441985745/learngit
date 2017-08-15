package com.opple.fa.assetcheck.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcheck.entity.AssetCheckBusiness;
import com.opple.util.Pager;

public interface AssetCheckBusinessService {
	
	
	
	/**
     * 保存主表信息
     */
    void saveAssetCheckBusiness(AssetCheckBusiness assetCheckBusiness, String sublist);
    
    /**
     * 保存子表信息
     * @param assetsInventoryDetail
     */
	void saveAssetsInventoryDetail(String sublist, Long id);
      
    /**
     * 查询资产盘点信息
     */
    Pager<AssetCheckBusiness> searchAssetCheckBusiness(Pager<AssetCheckBusiness> pager, AssetCheckBusiness assetCheckBusiness);
    
    /**
     * 删除资产盘点信息
     */
    void deleteAssetCheck(AssetCheckBusiness assetCheckBusiness);

	/**
	 * 模糊查询盘点申请单的详情信息
	 * @param pager
	 * @param assetCheckBusiness
	 * @return
	 */
    Pager<GeneralAsset> searchGeneralAssetDetails(Pager<GeneralAsset> pager, AssetCheckBusiness assetCheckBusiness);
	
    /**
	 * 删除盘点申请单详情信息
	 * @param generalAsset
	 * @return
	 */
	void deleteAssetCard(GeneralAsset generalAsset);
	
	/**
	 * 导出查询所有的资产盘点信息
	 * @param generalAsset
	 * @return
	 */
	List<AssetCheckBusiness> serchAssetCheckBusinessData(@Param("assetCheckBusiness") AssetCheckBusiness assetCheckBusiness);
	
	/**
	 * 根据Id查询单条信息
	 */
	AssetCheckBusiness getAssetCheckBusiness(AssetCheckBusiness assetCheckBusiness);
}