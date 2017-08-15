package com.opple.fa.discardsell.service;

import java.util.List;


import com.opple.fa.discardsell.entity.DiscardSellDetail;

public interface DiscardSellDetailService {
	/**
	 * 
	 * 通过资产的id 获得已经申请的资产 判断是否重复申请
	 * 
	 * @return
	 */
	List<DiscardSellDetail> serachDiscardSellDetailByAssetId(
			DiscardSellDetail discardSellDetail);

	/**
	 * 
	 * 通过资产的id 获得已经申请的资产 判断是否重复申请
	 * 
	 * @return
	 */
	List<DiscardSellDetail> serachDiscardSellDetailBydiscardSellId(
			DiscardSellDetail discardSellDetail);

	/**
	 * 
	 * 通过资产的id 获得单条
	 * 
	 * @return
	 */
	DiscardSellDetail getDiscardSellDetailByAssetId(
			DiscardSellDetail discardSellDetail);

	/**
	 * 修改报废凭证
	 * 
	 * @param discardSellDetail
	 */
	void updateScrapCertificate(DiscardSellDetail discardSellDetail);
}
