package com.opple.fa.config.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.util.Pager;

public interface CommonDAO<T> {

	int save(@Param("paramObject") Object paramObject);
	Long saveBatch(List<Object> paramObjects);
	int delete(@Param("paramObject") T paramObject);
	int update(@Param("paramObject") T paramObject);
	T get(String paramObjectCode);
	List<T> searchList(@Param("pager") Pager<?> pager, @Param("paramObject") T paramObject);
	Long searchListCount(@Param("paramObject") T paramObject);
	List<T> getList(@Param("paramObject") T paramObject);
	List<String> selectExistCodes(@Param("paramObjectCodes") List<String> paramObjectCodes);
	
}
