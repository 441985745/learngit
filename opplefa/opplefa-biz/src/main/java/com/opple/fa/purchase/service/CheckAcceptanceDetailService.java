package com.opple.fa.purchase.service;

import com.opple.fa.purchase.entity.CheckAcceptanceDetail;

import java.util.List;

public interface CheckAcceptanceDetailService {
	/**查询验收单的行
	 * @param checkAccpetId
	 * @return
	 */
	List<CheckAcceptanceDetail> getCheckAcceptanceDetailByDocument(String checkAccpetId);


    Long updateCheckAcceptanceDetailSapById(CheckAcceptanceDetail checkAcceptanceDetail);

    CheckAcceptanceDetail getCheckAcceptanceDetailById(Long id);
}
