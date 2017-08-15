package com.opple.fa.config.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.config.entity.WorkAddress;
import com.opple.util.Pager;

public interface WorkAddressDAO {
	
	int save(@Param("workAddress") WorkAddress workAddress);
	Long saveBatch(List<WorkAddress> workAddresss);
	int delete(@Param("workAddress") WorkAddress workAddress);
	int update(@Param("workAddress") WorkAddress workAddress);
	WorkAddress get(String assetsCode);
	List<WorkAddress> searchList(@Param("pager") Pager<?> pager, @Param("workAddress") WorkAddress workAddress);
	Long searchListCount(@Param("workAddress") WorkAddress workAddress);
	/**不带分页的查询
	 * @param workAddress
	 * @return
	 */
	List<WorkAddress> getList(@Param("workAddress") WorkAddress workAddress);
	List<String> selectExistCodes(@Param("caddressCodes") List<String> caddressCodes);
	List<String> selectExistNames(@Param("caddressNames") List<String> caddressNames);

    Long checkUnitCode(String caddressCode);

    Long selectExistName(@Param("workAddress") WorkAddress workAddress);

    Long searchWorkAddress(@Param("addressCode") String addressCode, @Param("addressName") String addressName);

    List<WorkAddress> searchCodeAndName(@Param("pager") Pager<WorkAddress> pager, @Param("workAddress") WorkAddress workAddress);

	int searchCodeAndNameCount();

	Integer selectCodeAndName(@Param("caddressCode") String caddressCode, @Param("caddressName") String caddressName);
}