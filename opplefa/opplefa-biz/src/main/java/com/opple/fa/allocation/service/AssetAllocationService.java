package com.opple.fa.allocation.service;

import com.opple.fa.allocation.entity.AssetAllocation;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface AssetAllocationService {

	/**
	 * 模糊查询assetAllocation
	 * 
	 * @param pager
	 * @param assetAllocation
	 * @return
	 */
	Pager<AssetAllocation> searchAssetAllocations(Pager<AssetAllocation> pager,
			AssetAllocation assetAllocation);

	/**
	 * 资产调拨导出查询
	 * 
	 * @return
	 */
	List<AssetAllocation> export(
			@Param("assetAllocation") AssetAllocation assetAllocation);

	/**
	 * 进页面自动加载所有数据
	 * 
	 * @param pager
	 * @return
	 */
	Pager<AssetAllocation> searchAssetAllocationList(
			Pager<AssetAllocation> pager);

	/**
	 * 保存提交申请
	 * @param assetAllocation
     * @param sublist
     * @param originalValueSum
     * @param netValueSum
     * @param cause
     * @return
     */
	ExecuteResult<AssetAllocation> saveApply(AssetAllocation assetAllocation,
			String sublist, BigDecimal originalValueSum, BigDecimal netValueSum,
			String cause);

	/**
	 * 删除首页数据
	 * 
	 * @param assetAllocation
	 * @return
	 */
	ExecuteResult<AssetAllocation> deleteAssetAllocation(
			AssetAllocation assetAllocation);

	/**
	 * 保存到子表
	 * 
	 * @param sublist
	 * @param id
	 */
	void saveAllocationDetail(String sublist, long id);

	/**
	 * 根据Id查看对应的信息
	 * 
	 * @param assetAllocationId
	 * @return
	 */
	AssetAllocation searchAssetAllocationById(Integer assetAllocationId);

	/**
	 * 修改
	 * 
	 * @param assetAllocation
	 * @return
	 */
	ExecuteResult<AssetAllocation> updateAssetAllocation(
			@Param("assetAllocation") AssetAllocation assetAllocation,
			String ids);

	/**
	 * 绑定
	 * 
	 * @Param assetAllocation
	 * @return
	 */
	ExecuteResult<AssetAllocation> bindApplyWorkFlow(
			AssetAllocation assetAllocation);

	/**
	 * 删除申请单据
	 * 
	 * @param document
	 */
	void deleteAssetAllocationByDocument(String document);

	/**
	 * 同意
	 * 
	 * @Param assetAllocation
	 * @return
	 */
	ExecuteResult<AssetAllocation> agreeWorkFlow(AssetAllocation assetAllocation);

	/**
	 * 流程同意完之后更新数据库的数据
	 * 
	 * @Param assetAllocation
	 * @return
	 */
	void updateAssetAllocationByDocument(AssetAllocation assetAllocation);

	/**
	 * 查询单据号是否重复
	 * 
	 * @param document
	 */
	AssetAllocation getAssetAllocationByDocument(String document);

	/**
	 * 退回
	 * 
	 * @Param assetAllocation
	 * @return
	 */
	ExecuteResult<AssetAllocation> denyWorkFlow(AssetAllocation assetAllocation);

	/**
	 * 弃审
	 *
     * @param assetAllocation
     * @return
	 */
	ExecuteResult<AssetAllocation> dropWorkFlow(AssetAllocation assetAllocation);

	/**
	 * 获得单条记录
	 * 
	 * @param assetAllocation
	 * @return
	 */
	AssetAllocation getAssetAllocation(AssetAllocation assetAllocation);

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

	boolean getIsLastApprovalStep(AssetAllocation assetAllocation);

	AssetAllocation getAssetAllocationByTask(AssetAllocation assetAllocation);

	/**
	 * 获得单挑数据
	 * 
	 * @param assetAllocation
	 * @return
	 */
	AssetAllocation getOneAssetAllocation(AssetAllocation assetAllocation);

	/**
	 * 删除 和批量删除
	 * 
	 * @param ids
	 */
	void deleteAssetAllocation(String ids);

	/**
	 * 更新 是否更新sap状态
	 * 
	 * @param assetAllocation
	 */
	void updateSapStatus(AssetAllocation assetAllocation);

	/**
	 * 新加 单条数据查看
	 * 
	 * @param assetAllocation
	 * @return
	 */
	AssetAllocation getOneAssetAllocationByMM(AssetAllocation assetAllocation);
	/**
	 * 修改打印状态
	 */
	void updateIsStamp(AssetAllocation assetAllocation);
	/**
	 * 修改退回状态
	 * @param assetAllocation
	 */
	void updateBack(AssetAllocation assetAllocation);

}
