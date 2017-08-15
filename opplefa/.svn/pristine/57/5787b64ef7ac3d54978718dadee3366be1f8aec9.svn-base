package com.opple.fa.config.dao;

import com.opple.fa.config.entity.AssetUnit;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetUnitDAO {
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
	
	int save(@Param("assetUnit") AssetUnit assetUnit);
	Long saveBatch(List<AssetUnit> assetUnits);
	int delete(@Param("assetUnit") AssetUnit assetUnit);
	int update(@Param("assetUnit") AssetUnit assetUnit);
	AssetUnit get(String unitCode);
	List<AssetUnit> searchList(@Param("pager") Pager<?> pager, @Param("assetUnit") AssetUnit assetUnit);
	Long searchListCount(@Param("assetUnit") AssetUnit assetUnit);
	/**不带分页的查询
	 * @param assetUnit
	 * @return
	 */
	List<AssetUnit> getList(@Param("assetUnit") AssetUnit assetUnit);
	
	/**根据编码list查询其中在数据库中存在的编码，进行唯一主键重复提示
	 * @param unitCodes
	 * @return
	 */
	List<String> selectExistCodes(@Param("unitCodes") List<String> unitCodes);

	Integer checkUnitCode(String unitCode);

    Long selectExistNames(@Param("unitName")String unitName);
}
