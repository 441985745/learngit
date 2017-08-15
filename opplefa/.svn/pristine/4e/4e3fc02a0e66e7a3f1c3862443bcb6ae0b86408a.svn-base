package com.opple.fa.discardsell.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.discardsell.entity.DiscardSell;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

public interface DiscardSellService {
	/**
	 * 查询所有的报废变卖记录带分页
	 * 
	 * @param discardSell
	 *            pager
	 * @return
	 */
	Pager<DiscardSell> searchDiscardSell(Pager<DiscardSell> pager,
			DiscardSell discardSell);

	/**
	 * 查询所有的报废变卖记录不带分页
	 * 
	 * @param discardSell
	 * @return
	 */
	List<DiscardSell> serchDiscardExport(
			@Param("discardSell") DiscardSell discardSell);

	/**
	 * 查询所有的报废变卖记录带分页
	 * 
	 * @param discardSell
	 *            pager
	 * @return
	 */
	void insertDiscardSell(DiscardSell discardSell, String ids,
			String moneyList, String percentList);

	/**
	 * 关闭报废
	 * 
	 * @param discardSell
	 * @return
	 */
	void deleteDiscardSell(DiscardSell discardSell);

	/**
	 * 获得单挑记录
	 * 
	 * @param discardSell
	 * @return
	 */
	DiscardSell getDiscardSell(DiscardSell discardSell);

	/**
	 * 修改主表信息
	 * 
	 * @Param discardSell
	 * @return
	 */
	ExecuteResult<DiscardSell> updateDiscardell(DiscardSell discardSell, String ids,
			String moneyList, String percentList);

	/**
	 * 流程同意完之后更新数据库的数据
	 * 
	 * @Param discardSell
	 * @return
	 */
	void updateDiscardellByDocument(DiscardSell discardSell);

	/**
	 * 绑定
	 * 
	 * @Param discardSell
	 * @return
	 */
	ExecuteResult<DiscardSell> bindApplyWorkFlow(DiscardSell discardSell);

	/**
	 * 同意
	 * 
	 * @Param discardSell
	 * @return
	 */
	ExecuteResult<DiscardSell> agreeWorkFlow(DiscardSell discardSell);

	/**
	 * 退回
	 * 
	 * @Param discardSell
	 * @return
	 */
	ExecuteResult<DiscardSell> denyWorkFlow(DiscardSell discardSell);

	/**
	 * 弃审
	 * 
	 * @param vehicleInfo
	 * @return
	 */
	ExecuteResult<DiscardSell> dropWorkFlow(DiscardSell discardSell);

	/**
	 * 删除申请单据
	 * 
	 * @param document
	 */
	void deleteDiscardSellByDocument(String document);

	/**
	 * 查询单据号是否重复
	 * 
	 * @param document
	 */
	DiscardSell getDiscardSellByDocument(String document);

	/**
	 * 修改提交的状态
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
	void updateDiscardByApprove(@Param("discardSell") DiscardSell discardSell);

	/**
	 * 是否为最后一步审批
	 * 
	 * @param discardSell
	 * @return
	 */
	boolean getIsLastApprovalStep(DiscardSell discardSell);

	/**
	 * 删除功能 并更新卡片状态
	 */
	void updateDiscardByListIds(String ids);

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
