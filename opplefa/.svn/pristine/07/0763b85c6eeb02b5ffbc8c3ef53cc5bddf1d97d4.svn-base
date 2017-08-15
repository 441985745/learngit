package com.opple.fa.allocation.service;

import com.opple.fa.allocation.entity.AssetAllocation;
import com.opple.fa.allocation.entity.AssetAllocationDetail;

import java.util.List;

public interface AssetAllocationDetailService {
	/**
	 * 根据主表对象查看子表信息
	 *
     * @param assetAllocation
     * @return
	 */
	List<AssetAllocationDetail> searchAssetAllocationDetail(
			AssetAllocation assetAllocation);

	/**
	 * 调拨完成后 保存凭证号
	 * 
	 * @param assetAllocationDetail
	 */
	void updateAllocationDetailById(AssetAllocationDetail assetAllocationDetail);

	void updateAllocationDetailNewCode(
			AssetAllocationDetail assetAllocationDetail);

	void updateAssetCard(AssetAllocation assetAllocation);

}
