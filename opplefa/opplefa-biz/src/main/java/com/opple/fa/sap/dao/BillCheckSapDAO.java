package com.opple.fa.sap.dao;

import com.opple.fa.sap.model.ItDataModel;


public interface BillCheckSapDAO {
	/**保存预付/验收接口的凭证号生成结果
	 * @param billCheck
	 */
	void save(ItDataModel itDataModel);
}
