package com.opple.fa.config.service;


import java.util.List;

import com.opple.fa.config.entity.Factory;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;


public interface FactoryService {
	
	/**单条保存
	 * @param factory
	 * @return
	 */
	ExecuteResult<Factory> save(Factory factory);
	
	/**批量保存
	 * @param factory
	 * @return
	 */
	ExecuteResult<List<Factory>> saveBatch(List<Factory> factorys);
	
	/**删除，根据factoryCodes的值删除  ---（private List<String> factoryCodes;// 资产编码--删除使用）
	 * @param factory
	 * @return
	 */
	ExecuteResult<Factory> delete(Factory factory);
	
	/**更新
	 * @param factory
	 * @return
	 */
	ExecuteResult<Factory> update(Factory factory);
	
	/**单条查询
	 * @param factoryCode
	 * @return
	 */
	Factory get(String factoryCode);
	
	/**分页查询
	 * @param pager
	 * @param factory
	 * @return
	 */
	Pager<Factory> searchPagerList(Pager<Factory> pager, Factory factory);
	
	/**分页查询
	 * @param pager
	 * @param factory
	 * @return
	 */
	List<Factory> getList(Factory factory);
	
	/**
	 * 查询
	 * @return
	 */
	List<Factory> searchByCompanyCode(String companyCode);
	
	/**查询数据库中资产编码重复项
	 * @param factoryCodes
	 * @return
	 */
	List<String> selectExistCodes(List<String> factoryCodes);

    /**
     * 查询工厂编码
     *
     * @param factoryCode
     * @return
     */
    Integer checkFactoryCode(String factoryCode);

    void addOrUpdateFactory(List<Factory> addList, List<Factory> updateList);
}
