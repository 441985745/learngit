package com.opple.fa.purchase.service.impl;

import com.opple.fa.purchase.dao.CheckAcceptanceDetailDAO;
import com.opple.fa.purchase.entity.CheckAcceptanceDetail;
import com.opple.fa.purchase.service.CheckAcceptanceDetailService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CheckAcceptanceDetailServiceImpl implements CheckAcceptanceDetailService {
	@Getter @Setter
	private CheckAcceptanceDetailDAO checkAcceptanceDetailDAO;
	@Override
	public List<CheckAcceptanceDetail> getCheckAcceptanceDetailByDocument(String checkAccpetId) {
		return checkAcceptanceDetailDAO.getCheckAcceptanceDetailByDocument(checkAccpetId);
	}

	@Override
	public Long updateCheckAcceptanceDetailSapById(CheckAcceptanceDetail checkAcceptanceDetail) {
		return checkAcceptanceDetailDAO.updateCheckAcceptanceDetailSapById(checkAcceptanceDetail);
	}

	@Override
	public CheckAcceptanceDetail getCheckAcceptanceDetailById(Long id) {
		return checkAcceptanceDetailDAO.getCheckAcceptanceDetailById(id);
	}

}
