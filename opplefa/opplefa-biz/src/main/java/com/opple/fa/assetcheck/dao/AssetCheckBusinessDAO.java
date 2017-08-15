package com.opple.fa.assetcheck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.assetcheck.entity.AssetCheckBusiness;
import com.opple.util.Pager;

public interface AssetCheckBusinessDAO {
	
    /**
     * 保存主表申请数据
     * @param assetCheckBusiness
     */
    void saveAssetCheckBusiness(AssetCheckBusiness assetCheckBusiness);
    
   /**
     * 模糊查询资产盘点信息
     * @param assetCheckBusiness
     * @return
     */
    List<AssetCheckBusiness> searchAssetCheckBusiness(@Param("pager") Pager<AssetCheckBusiness> pager, @Param("assetCheckBusiness") AssetCheckBusiness assetCheckBusiness);
    
    /**
     *  模糊查询资产信息数量
     * @return
     */
    Long searchAssetCheckBusinessCount(@Param("assetCheckBusiness") AssetCheckBusiness assetCheckBusiness);
	
	 /**
     * 删除资产盘点信息
     */
    void deleteAssetCheck(AssetCheckBusiness assetCheckBusiness);
    
    /**
	 * 导出查询所有的资产盘点
	 * @param generalAsset
	 * @return
	 */
    List<AssetCheckBusiness> serchAssetCheckData(@Param("assetCheckBusiness") AssetCheckBusiness assetCheckBusiness);
    
    /**
     * 根据id查询单个对象
     */
    AssetCheckBusiness getAssetCheckBusiness(AssetCheckBusiness assetCheckBusiness);
    
}