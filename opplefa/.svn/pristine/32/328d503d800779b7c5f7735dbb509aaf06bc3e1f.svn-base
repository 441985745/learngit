package com.opple.fa.discardsell.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.discardsell.entity.DiscardSell;
import com.opple.util.Pager;

public interface DiscardSellDAO {
	/**
	 * 查询所有的报废变卖记录带分页
	 * 
	 * @param discardSell
	 * @return
	 */
	List<DiscardSell> searchDiscardSell(@Param("pager") Pager pager,
			@Param("discardSell") DiscardSell discardSell);

	/**
	 * 查询所有的报废变卖记录不带分页
	 * 
	 * @param discardSell
	 * @return
	 */
	List<DiscardSell> serchDiscardExport(
			@Param("discardSell") DiscardSell discardSell);

	/**
	 * 查询所有的报废变卖记录带分页数量
	 * 
	 * @param discardSell
	 * @return
	 */
	Long searchDiscardSellCount(@Param("discardSell") DiscardSell discardSell);

	/**
	 * 主表中添加数据
	 * 
	 * @param discardSell
	 * @return
	 */
	void insertDiscardSell(DiscardSell discardSell);

	/**
	 * 关闭报废
	 * 
	 * @param discardSell
	 * @return
	 */
	void deleteDiscardSell(DiscardSell discardSell);

	/**
	 * 获得报废变卖的一条记录
	 * 
	 * @param discardSell
	 * @return
	 */
	DiscardSell getDiscardSell(DiscardSell discardSell);

	/**
	 * 修改数据
	 * 
	 * @param discardSell
	 * @return
	 */
	void updateDiscardell(DiscardSell discardSell);

	/**
	 * 展示在卡片上的记录
	 * 
	 * @param discardSell
	 * @return
	 */
	DiscardSell getDiscardSellByCardId(DiscardSell discardSell);

	/**
	 * 删除申请单据
	 * 
	 * @param document
	 */
	void deleteDiscardSellByDocument(String document);

	/**
	 * 流程完毕后 修改业务主表的数据
	 * 
	 * @param document
	 */
	void updateDiscardellByDocument(DiscardSell discardSell);

	/**
	 * 获得单条对象
	 * 
	 * @param document
	 */
	DiscardSell getDiscardSellByDocument(String document);

	/**
	 * 通过单据号修改状态
	 * 
	 * @param document
	 */
	void convertCommitTypeToOfficial(String document);

	/**
	 * 回滚修改数据
	 * 
	 * @param discardSell
	 */
	void updateDiscardellBackByDoucment(DiscardSell discardSell);

	/**
	 * 报废审批修改填写意见
	 * 
	 * @param discardSell
	 */
	void updateDiscardByApprove(DiscardSell discardSell);

	/**
	 * 同步完sap 更改状态
	 * 
	 * @param discardSell
	 */
	void updateIsSap(DiscardSell discardSell);

	/**
	 * 修改打印状态
	 */
	void updateStampStatus(DiscardSell discardSell);
}
