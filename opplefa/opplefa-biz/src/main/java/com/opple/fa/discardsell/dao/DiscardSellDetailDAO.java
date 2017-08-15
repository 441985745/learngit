package com.opple.fa.discardsell.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.discardsell.entity.DiscardSellDetail;

public interface DiscardSellDetailDAO {
	/**
	 * 保存子表信息
	 * @param discardSell
	 * @return
	 */
	void insertDiscardSellDetail(DiscardSellDetail discardSellDetail);
	/**
	 * 获得子表的id
	 * @param discardSell
	 * @return
	 */
	List<DiscardSellDetail> serachDiscardSellDetail(int id);
	/**
	 * 删除子表的信息
	 * @param discardSell
	 * @return
	 */
	void deleteDiscardSellDetail(DiscardSellDetail discardSellDetail);
	/**
	 * 
	 * 通过资产的id 获得已经申请的资产 判断是否重复申请
	 * @return
	 */
	List<DiscardSellDetail> serachDiscardSellDetailByAssetId(@Param("discardSellDetail")DiscardSellDetail discardSellDetail);
	/**
	 * 
	 * 通过资产的id 获得已经申请的资产 判断是否重复申请
	 * @return
	 */
	List<DiscardSellDetail> serachDiscardSellDetailBydiscardSellId(@Param("discardSellDetail")DiscardSellDetail discardSellDetail);
	/**
	 * 
	 * 通过资产的id 获得记录
	 * @return
	 */
	DiscardSellDetail getDiscardSellDetailByAssetId(DiscardSellDetail discardSellDetail);
	/**
	 * 修改报废凭证
	 * @param discardSellDetail
	 */
	void updateScrapCertificate(DiscardSellDetail discardSellDetail);
	/**
	 * 获得所有的卡片id
	 */
	List<String> serachGeneralIds(DiscardSellDetail discardSellDetail);
}	
