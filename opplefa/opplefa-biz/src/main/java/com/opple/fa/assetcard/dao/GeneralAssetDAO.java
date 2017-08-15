package com.opple.fa.assetcard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.allocation.entity.AssetAllocation;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcheck.entity.AssetCheckBusiness;
import com.opple.util.Pager;

public interface GeneralAssetDAO {

	/**
	 * 查询所有的卡片带分页
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCard(@Param("pager") Pager pager,
			@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 查询卡片的数量
	 * 
	 * @param generalAsset
	 * @return
	 */
	Long serchAssetCardCount(@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 查询所有的卡片带分页 不加权限 资产会计所展示的列表
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardByAccounting(@Param("pager") Pager pager,
			@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 查询卡片的数量 不加权限 资产会计展示列表的数量
	 * 
	 * @param generalAsset
	 * @return
	 */
	Long serchAssetCardCountByAccounting(
			@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 查询所有的卡片不带分页
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardData(
			@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 查询所有的卡片不带分页 导出
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardDateByAccounting(
			@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 查看资产编码是否重复
	 * 
	 * @param GeneralAsset
	 * @return
	 */
	Long assetCodeCount(@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 保存资产主表基础数据
	 * 
	 * @return
	 */
	void insertGeneralAsset(GeneralAsset generalAsset);

	/**
	 * 删除卡片
	 * 
	 * @param generalAsset
	 * @return
	 */
	void deleteAssetCard(GeneralAsset generalAsset);

	/**
	 * 关闭
	 * 
	 * @param generalAsset
	 * @return
	 */
	void closeAssetCard(GeneralAsset generalAsset);

	/**
	 * 查询个人资产
	 * 
	 * @param pager
	 * @param userName
	 * @return
	 */
	List<GeneralAsset> searchTbFaGeneralAsset(@Param("pager") Pager pager,
			@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 查询总数 (通用表)
	 * 
	 * @param generalAsset
	 * @return
	 */
	Long searchTbFaGeneralAssetCount(
			@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 获取详情
	 * 
	 * @param generalAsset
	 * @return
	 */
	GeneralAsset searchDetails(@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 增行信息
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> searchAssetCardInfo(
			@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 增行信息(资产会计)
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> searchAssetCardInfoForBursar(
			@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 增行信息内部查询
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> searchGeneralAsset(
			@Param("generalAsset") GeneralAsset generalAsset);

	/**
	 * 更新卡片信息
	 * 
	 * @param assetAllocation
	 */
	void updateGeneralAssetInfo(
			@Param("assetAllocation") AssetAllocation assetAllocation);

	/**
	 * 根据ID查询出关联信息
	 * 
	 * @param id
	 * @return
	 */
	GeneralAsset searchGeneralAssetInfo(@Param("id") Integer id);

	/**
	 * 模糊查询发起盘点页详情信息
	 * 
	 * @param pager
	 * @param assetType
	 * @param inventoryPeople
	 * @return
	 */
	List<GeneralAsset> searchGeneralAssetDetails(
			@Param("pager") Pager<GeneralAsset> pager,
			@Param("assetCheckBusiness") AssetCheckBusiness assetCheckBusiness);

	/**
	 * 模糊查询发起盘点详情的数量
	 * 
	 * @param assetCheckBusiness
	 * @return
	 */
	Long searchDetailsCount(
			@Param("assetCheckBusiness") AssetCheckBusiness assetCheckBusiness);

	/**
	 * 流程完毕后 修改业务主表的数据
	 * 
	 * @param document
	 */
	void updateGeneralAssetByDocument(GeneralAsset generalAsset);

	/**
	 * 获得单个对象数据
	 * 
	 * @param id
	 * @return
	 */
	GeneralAsset getGeneralAsset(@Param("id") Integer id);

	/**
	 * 更新资产卡片信息
	 */
	void updateCard(GeneralAsset generalAsset);

	/**
	 * 更新sap成功后 修改sap状态
	 * 
	 * @param id
	 */
	void updateSapById(@Param("id") Integer id);

	/**
	 * 更新资产卡片
	 * 
	 * @param generalAsset
	 */
	void updateGeneralAsset(GeneralAsset generalAsset);

	/**
	 * 验收单 修改 回写
	 * 
	 * @param generalAsset
	 */
	void updateGeneralAssetByCheckAcceptance(GeneralAsset generalAsset);

	/**
	 * 报废或变卖完成后 修改资产的使用状态
	 * 
	 * @param id
	 */
	void updateCardStateById(GeneralAsset generalAsset);

	/**
	 * 资产卡片主表更新 --- 验收
	 * 
	 * @param generalAsset
	 */
	void updateGeneralAssetCard(GeneralAsset generalAsset);

	/**
	 * 
	 * @param generalAsset
	 */
	List<GeneralAsset> searchAssetBySapCode(GeneralAsset generalAsset);

	/**
	 * 从sap中获取资产信息 更新本地卡片信息
	 * 
	 * @param generalAsset
	 */
	void updateAssetBySapCode(GeneralAsset generalAsset);

	/**
	 * 获得所有的资产编码
	 * 
	 * @return
	 */
	List<String> searchAssetCode();

	/**
	 * 修改导入的卡片信息
	 * 
	 * @param generalAsset
	 */
	void updateLeadingCard(GeneralAsset generalAsset);

	/**
	 * 更新验收时间
	 * 
	 * @param generalAsset
	 */
	void updatePostingDate(GeneralAsset generalAsset);

	/**
	 * 报废完成后修改资产使用状态
	 * 
	 * @param generalAsset
	 */
	void updateAssetState(GeneralAsset generalAsset);

	void updateGeneralAssetCard_(GeneralAsset generalAsset);

	void deleteAssetCardReal(GeneralAsset generalAsset);

	void updateGeneralAssetCard2(GeneralAsset generalAsset);

	/**
	 * 修改是否被调拨占用的资产
	 * 
	 * @param generalAsset
	 */
	void updateAllocationOccupancy(GeneralAsset generalAsset);

	/**
	 * 修改是否被报废占用的资产
	 * 
	 * @param generalAsset
	 */
	void updateDiscardOccupancy(GeneralAsset generalAsset);

	List<GeneralAsset> serchAssetCardByinsert(GeneralAsset generalAsset);

	/**
	 * 资产会计查询所有更新sap
	 */
	List<GeneralAsset> serachByacctingUpdateSap(@Param("generalAsset") GeneralAsset generalAsset);
	
	
	/**
	 * 不带分页查询所有的卡片信息
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardDataIT(@Param("generalAsset") GeneralAsset generalAsset);
	
	/**
	 * 不带分页查询所有的卡片信息
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardDataXZ(@Param("generalAsset") GeneralAsset generalAsset);
	
	/**
	 * 不带分页查询所有的卡片信息
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardDataJL(@Param("generalAsset") GeneralAsset generalAsset);
	
	/**
	 * 不带分页查询所有的卡片信息
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardDataJX(@Param("generalAsset") GeneralAsset generalAsset);
	
	
	/**
	 * 不带分页查询所有的卡片信息 导出
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardDateByAccountingIT(@Param("generalAsset") GeneralAsset generalAsset);
	
	/**
	 * 不带分页查询所有的卡片信息 导出
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardDateByAccountingXZ(@Param("generalAsset") GeneralAsset generalAsset);
	
	/**
	 * 不带分页查询所有的卡片信息 导出
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardDateByAccountingJL(@Param("generalAsset") GeneralAsset generalAsset);
	
	/**
	 * 不带分页查询所有的卡片信息 导出
	 * 
	 * @param generalAsset
	 * @return
	 */
	List<GeneralAsset> serchAssetCardDateByAccountingJX(@Param("generalAsset") GeneralAsset generalAsset);
	/**
	 * 修改是否被流程类资产占用
	 */
	void  updateAssembleOccupancy(GeneralAsset generalAsset);

	GeneralAsset searchGeneralAssetOnly(GeneralAsset generalAsset);
	/**
	 * 定时任务  传集合更新
	 */
	void updateAssetFromSapByList(List<GeneralAsset> listGeneralAssets);
}
