package com.opple.fa.config.service;

import java.util.List;

import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

public abstract class CommonConfigService {
	/**单条保存
	 * @param object
	 * @return
	 */
	abstract ExecuteResult<Object> save(Object object);
	
	/**批量保存
	 * @param object
	 * @return
	 */
	abstract ExecuteResult<List<Object>> saveBatch(List<Object> objects);
	
	/**删除，根据configCodes的值删除  ---（private List<String> configCodes;// 资产编码--删除使用）
	 * @param object
	 * @return
	 */
	abstract ExecuteResult<Object> delete(Object object);
	
	/**更新
	 * @param object
	 * @return
	 */
	abstract ExecuteResult<Object> update(Object object);
	
	/**单条查询
	 * @param configCode
	 * @return
	 */
	abstract Object get(String configCode);
	
	/**分页查询
	 * @param pager
	 * @param object
	 * @return
	 */
	abstract Pager<Object> searchPagerList(Pager<Object> pager, Object object);
	
	/**分页查询
	 * @param pager
	 * @param object
	 * @return
	 */
	abstract List<Object> getList(Object object);
	
	
	/**查询数据库中资产编码重复项
	 * @param configCodes
	 * @return
	 */
	abstract List<String> selectExistCodes(List<String> configCodes);
}
