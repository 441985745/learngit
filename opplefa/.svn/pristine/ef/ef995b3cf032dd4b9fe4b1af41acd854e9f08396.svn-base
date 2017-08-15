package com.opple.fa.config.dao;

import com.opple.fa.config.entity.Factory;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/4/6.
 */
public interface FactoryDAO {
	int save(@Param("factory") Factory factory);
	Long saveBatch(List<Factory> factorys);
	int delete(@Param("factory") Factory factory);
	int update(@Param("factory") Factory factory);
	Factory get(String factoryCode);
	List<Factory> searchList(@Param("pager") Pager<?> pager, @Param("factory") Factory factory);
	Long searchListCount(@Param("factory") Factory factory);
	/**
	 * 按公司编码查询所有工厂
	 * @return
	 */
	List<Factory> searchByCompanyCode(String companyCode);
	/**不带分页的查询
	 * @param factory
	 * @return
	 */
	List<Factory> getList(@Param("factory") Factory factory);
	List<String> selectExistCodes(@Param("cfactoryCodes") List<String> cfactoryCodes);

    /**
     * 根据编码统计工厂
     *
     * @param factoryCode
     * @return
     */
    Integer checkFactoryCode(String factoryCode);
}
