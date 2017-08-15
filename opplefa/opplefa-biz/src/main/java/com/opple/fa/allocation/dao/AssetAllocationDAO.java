package com.opple.fa.allocation.dao;

import com.opple.fa.allocation.entity.AssetAllocation;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetAllocationDAO {

	/**
	 * 模糊查询assetAllocation
	 * 
	 * @param pager
	 * @param assetAllocation
	 * @return
	 */
	List<AssetAllocation> searchAssetAllocations(
			@Param("pager") Pager<AssetAllocation> pager,
			@Param("assetAllocation") AssetAllocation assetAllocation);

	/**
	 * 导出表格查询
	 * 
	 * @param assetAllocation
	 * @return
	 */
	List<AssetAllocation> export(
			@Param("assetAllocation") AssetAllocation assetAllocation);

	/**
	 * 模糊查询assetAllocation的数量
	 * 
	 * @param assetAllocation
	 * @return
	 */
	Long searchAssetAllocationCount(
			@Param("assetAllocation") AssetAllocation assetAllocation);

	/**
	 * 一进页面自动加载数据
	 * 
	 * @param pager
	 * @return
	 */
	List<AssetAllocation> searchAssetAllocationList(
			@Param("pager") Pager<AssetAllocation> pager);

	/**
	 * 一进页面自动加载数据的数量
	 * 
	 * @return
	 */
	Long searchAssetAllocationListCount();

	AssetAllocation searchAssetAllocation(
			@Param("allocationDetailId") Integer allocationDetailId);

	/**
	 * 保存到主表申请
	 * 
	 * @param assetAllocation
	 * @return
	 */
	void saveApply(AssetAllocation assetAllocation);

	/**
	 * 删除首页数据
	 * 
	 * @param assetAllocation
	 */
	void deleteAssetAllocation(AssetAllocation assetAllocation);

	/**
	 * 根据主表ID查询出主表子表信息
	 * 
	 * @param assetAllocationId
	 * @return
	 */
	AssetAllocation searchAssetAllocationById(
			@Param("assetAllocationId") Integer assetAllocationId);

	/**
	 * 修改页面获得值
	 * 
	 * @param assetAllocation
	 */
	AssetAllocation getAssetAllocation(
			@Param("assetAllocation") AssetAllocation assetAllocation);

	/**
	 * 修改
	 * 
	 * @param assetAllocation
	 */
	void updateAssetAllocation(AssetAllocation assetAllocation);

	/**
	 * 删除申请单据
	 * 
	 * @param document
	 */
	void deleteAssetAllocationByDocument(String document);

	/**
	 * 绑定
	 * 
	 * @Param discardSell
	 * @return
	 */
	ExecuteResult<AssetAllocation> bindApplyWorkFlow(
			AssetAllocation assetAllocation);

	AssetAllocation getAssetAllocationByDocument(String document);

	/**
	 * 获得资产调拨的一条记录
	 * 
	 * @param assetAllocation
	 * @return
	 */
	AssetAllocation getOneAssetAllocation(AssetAllocation assetAllocation);

	/**
	 * 修改确认状态以及审批人
	 * 
	 * @param assetAllocation
	 */
	void updateComfirPeopleAndStatus(AssetAllocation assetAllocation);

	/**
	 * 修改确认状态
	 * 
	 * @param assetAllocation
	 */
	void updateComfirStatus(AssetAllocation assetAllocation);

	/**
	 * 修改是否更新sap 状态
	 */
	void updateSapStatus(AssetAllocation assetAllocation);

	void updateGetSapStatus(AssetAllocation assetAllocation);

	/**
	 * 修改打印状态
	 */
	void updateIsStamp(AssetAllocation assetAllocation);
	/**
	 * 修改下一处理人
	 */
	void updateNextUser(AssetAllocation assetAllocation);
	/**
	 * 判断是否存在此单据号
	 */
	long getCountByDocument(String document);
}
