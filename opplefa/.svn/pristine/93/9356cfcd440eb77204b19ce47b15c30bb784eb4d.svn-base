package com.opple.fa.config.service;

import java.util.List;

import com.opple.fa.config.entity.AssetUnit;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

public interface AssetUnitService {
	/**
	 * 查询单条单位
	 * @return
	 */
	AssetUnit getAssetUnit(AssetUnit assetUnit);
	/**
	 * 获得有效单位集合
	 * @return
	 */
	List<AssetUnit> getAssetUnitList();
	
	/**单条保存
	 * @param assetUnit
	 * @return
	 */
	ExecuteResult<AssetUnit> save(AssetUnit assetUnit);
	
	/**批量保存
	 * @param assetUnits
	 * @return
	 */
	ExecuteResult<List<AssetUnit>> saveBatch(List<AssetUnit> assetUnits);
	
	/**删除，根据unitCodes的值删除  ---（private List<String> unitCodes;// 资产编码--删除使用）
	 * @param assetUnit
	 * @return
	 */
	ExecuteResult<AssetUnit> delete(AssetUnit assetUnit);
	
	/**更新
	 * @param assetUnit
	 * @return
	 */
	ExecuteResult<AssetUnit> update(AssetUnit assetUnit);
	
	/**单条查询
	 * @param unitCode
	 * @return
	 */
	AssetUnit get(String unitCode);
	
	/**分页查询
	 * @param pager
	 * @param assetUnit
	 * @return
	 */
	Pager<AssetUnit> searchPagerList(Pager<AssetUnit> pager, AssetUnit assetUnit);
	
	/**分页查询
	 * @param assetUnit
	 * @return
	 */
	List<AssetUnit> getList(AssetUnit assetUnit);
	
	/**查询数据库中资产编码重复项
	 * @param unitCodes
	 * @return
	 */
	List<String> selectExistCodes(List<String> unitCodes);

	Integer checkUnitCode(String unitCode);

	String addOrUpdateUnit(List<AssetUnit> addList, List<AssetUnit> updateList);

    Long selectExistNames(String unitName);
}
