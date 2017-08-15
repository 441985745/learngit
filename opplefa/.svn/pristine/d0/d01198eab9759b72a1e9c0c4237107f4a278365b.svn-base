package com.opple.fa.allocation.dao;

import com.opple.fa.allocation.entity.AssetAllocationDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetAllocationDetailDAO {

	/**
	 * 获得资产调拨明细
	 * 
	 * @param id
	 * @return
	 */
	List<AssetAllocationDetail> searchAssetAllocationDetail(
			@Param("id") Integer id);

	/**
	 * 保存子表的申请信息
	 * 
	 * @param assetAllocationDetail
	 */
	void saveApply(AssetAllocationDetail assetAllocationDetail);

	/**
	 * 查询出子表所有信息
	 * 
	 * @param assetAllocationId
	 * @return
	 */
	List<AssetAllocationDetail> searchAssetAllocationDetailById(
			@Param("assetAllocationId") Integer assetAllocationId);

	/**
	 * 更新子表
	 * 
	 * @param assetAllocationDetail
	 */
	void updateAssetAllocationDetail(AssetAllocationDetail assetAllocationDetail);

	/**
	 * 获得子表的id
	 * 
	 * @param id
	 * @return
	 */
	List<AssetAllocationDetail> serachAssetAllocationDetail(int id);

	void updateAllocationDetailById(AssetAllocationDetail assetAllocationDetail);

	void updateAllocationDetailNewCode(
			AssetAllocationDetail assetAllocationDetail);
	/**
	 * 查询子表中卡片的id 判断修改卡片被占用状态 
	 */
	List<String> serachAssetCardIds(int id);
}
