package com.opple.fa.config.service;


import java.util.List;

import com.opple.fa.config.entity.StandardComputerConfig;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;


public interface StandardComputerConfigService {
	
	/**单条保存
	 * @param standardComputerConfig
	 * @return
	 */
	ExecuteResult<StandardComputerConfig> save(StandardComputerConfig standardComputerConfig);
	
	/**批量保存
	 * @param standardComputerConfigs
	 * @return
	 */
	ExecuteResult<List<StandardComputerConfig>> saveBatch(List<StandardComputerConfig> standardComputerConfigs);
	
	/**删除，根据assetsCodes的值删除  ---（private List<String> assetsCodes;// 资产编码--删除使用）
	 * @param standardComputerConfig
	 * @return
	 */
	ExecuteResult<StandardComputerConfig> delete(StandardComputerConfig standardComputerConfig);
	
	/**更新
	 * @param standardComputerConfig
	 * @return
	 */
	ExecuteResult<StandardComputerConfig> update(StandardComputerConfig standardComputerConfig);
	
	/**单条查询
	 * @param assetsCode
	 * @return
	 */
	StandardComputerConfig get(String assetsCode);
	
	/**分页查询
	 * @param pager
	 * @param standardComputerConfig
	 * @return
	 */
	Pager<StandardComputerConfig> searchPagerList(Pager<StandardComputerConfig> pager, StandardComputerConfig standardComputerConfig);
	
	/**分页查询
	 * @param pager
	 * @param standardComputerConfig
	 * @return
	 */
	List<StandardComputerConfig> getList(StandardComputerConfig standardComputerConfig);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<StandardComputerConfig> findAll();
	
	/**查询数据库中资产编码重复项
	 * @param assetsCodes
	 * @return
	 */
	List<String> selectExistCodes(List<String> assetsCodes);

    Long checkUnitCode(String assetsCode);

	String addOrUpdateUnit(List<StandardComputerConfig> addList, List<StandardComputerConfig> updateList);
}
