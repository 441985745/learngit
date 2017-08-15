package com.opple.fa.config.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.config.entity.StandardComputerConfig;
import com.opple.util.Pager;

public interface StandardComputerConfigDAO {

	int save(@Param("standardComputerConfig") StandardComputerConfig standardComputerConfig);
	Long saveBatch(List<StandardComputerConfig> standardComputerConfigs);
	int delete(@Param("standardComputerConfig") StandardComputerConfig standardComputerConfig);
	int update(@Param("standardComputerConfig") StandardComputerConfig standardComputerConfig);
	StandardComputerConfig get(String assetsCode);
	List<StandardComputerConfig> searchList(@Param("pager") Pager<?> pager, @Param("standardComputerConfig") StandardComputerConfig standardComputerConfig);
	Long searchListCount(@Param("standardComputerConfig") StandardComputerConfig standardComputerConfig);
	/**
	 * 查询所有标准电脑
	 * @return
	 */
	List<StandardComputerConfig> findAll();
	/**不带分页的查询
	 * @param standardComputerConfig
	 * @return
	 */
	List<StandardComputerConfig> getList(@Param("standardComputerConfig") StandardComputerConfig standardComputerConfig);
	List<String> selectExistCodes(@Param("assetsCodes") List<String> assetsCodes);

    Long checkUnitCode(String assetsCode);
}
