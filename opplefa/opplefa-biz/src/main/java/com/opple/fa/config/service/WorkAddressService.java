package com.opple.fa.config.service;

import java.util.List;

import com.opple.fa.config.entity.WorkAddress;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;


public interface WorkAddressService {
	/**单条保存
	 * @param workAddress
	 * @return
	 */
	ExecuteResult<WorkAddress> save(WorkAddress workAddress);
	
	/**批量保存
	 * @param workAddresss
	 * @return
	 */
	ExecuteResult<List<WorkAddress>> saveBatch(List<WorkAddress> workAddresss);
	
	/**删除，根据assetsCodes的值删除  ---（private List<String> assetsCodes;// 资产编码--删除使用）
	 * @param workAddress
	 * @return
	 */
	ExecuteResult<WorkAddress> delete(WorkAddress workAddress);
	
	/**更新
	 * @param workAddress
	 * @return
	 */
	ExecuteResult<WorkAddress> update(WorkAddress workAddress);
	
	/**单条查询
	 * @param assetsCode
	 * @return
	 */
	WorkAddress get(String assetsCode);
	
	/**分页查询
	 * @param pager
	 * @param workAddress
	 * @return
	 */
	Pager<WorkAddress> searchPagerList(Pager<WorkAddress> pager, WorkAddress workAddress);
	
	/**分页查询
	 * @param workAddress
	 * @return
	 */
	List<WorkAddress> getList(WorkAddress workAddress);
	
	
	/**查询数据库中资产编码重复项
	 * @param assetsCodes
	 * @return
	 */
	List<String> selectExistCodes(List<String> assetsCodes);
	//List<String> selectExistNames(List<String> assetsNames);

    Long checkUnitCode(String caddressCode);

	String addOrUpdateUnit(List<WorkAddress> addList, List<WorkAddress> updateList);

    Long selectExistNames(WorkAddress workAddress);

    Long searchWorkAddress(String addressCode, String addressName);

	Pager<WorkAddress> searchWorkAddressCodeAndName(Pager<WorkAddress> pager, WorkAddress workAddress);

	Integer selectCodeAndName(String caddressCode, String caddressName);
}
