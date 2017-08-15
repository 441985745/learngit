package com.opple.fa.sap.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.sap.entity.Accountpayable;

public interface Extension2SapDAO {
	/**保存预付/验收接口的凭证号生成结果
	 * @param accountpayable
	 */
	void save(@Param("accountpayable")Accountpayable accountpayable);
}
