package com.opple.fa.sap.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.sap.entity.ZrfcAmAssetChange;

public interface AssetChangeSapDAO {

	/**
	 * 保存订单结果
	 * @param applyOrderModel
	 * @return
	 */
	void save(@Param("zrfcAmAssetChange")ZrfcAmAssetChange zrfcAmAssetChange);
}
