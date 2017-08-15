package com.opple.fa.allocation.service.impl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.allocation.dao.AlloctionApproveDAO;
import com.opple.fa.allocation.entity.AlloctionApprove;
import com.opple.fa.allocation.service.AlloctionApproveService;

public class AlloctionApproveServiceImpl implements AlloctionApproveService {
	@Getter
	@Setter
	private AlloctionApproveDAO alloctionApproveDAO;

	@Override
	public void insertAllocationApprove(AlloctionApprove alloctionApprove) {
		alloctionApproveDAO.insertAllocationApprove(alloctionApprove);

	}

	@Override
	public long countAllocationApproveBycode(AlloctionApprove alloctionApprove) {
		return alloctionApproveDAO
				.countAllocationApproveBycode(alloctionApprove);
	}

	@Override
	public List<AlloctionApprove> serachAlloctionApprove(
			AlloctionApprove alloctionApprove) {
		return alloctionApproveDAO.serachAlloctionApprove(alloctionApprove);
	}

	@Override
	public List<String> serachIsConfirm(String document) {

		return alloctionApproveDAO.serachIsConfirm(document);
	}

	@Override
	public void updateIsConfirm(AlloctionApprove alloctionApprove) {
		alloctionApproveDAO.updateIsConfirm(alloctionApprove);
	}

	@Override
	public void deleteAllocationApprove(String alloctionDocument) {
		alloctionApproveDAO.deleteAllocationApprove(alloctionDocument);
	}

	@Override
	public List<AlloctionApprove> serachAlloctionApproveByDocument(
			AlloctionApprove alloctionApprove) {

		return alloctionApproveDAO
				.serachAlloctionApproveByDocument(alloctionApprove);
	}

	@Override
	public List<AlloctionApprove> serachApproveByDocumentAndConfirm(
			AlloctionApprove alloctionApprove) {

		return alloctionApproveDAO
				.serachApproveByDocumentAndConfirm(alloctionApprove);
	}

	@Override
	public void updateIsConfirmByBack(AlloctionApprove alloctionApprove) {
		alloctionApproveDAO.updateIsConfirmByBack(alloctionApprove);

	}

	@Override
	public AlloctionApprove getApproveByDocumentAndCode(
			AlloctionApprove alloctionApprove) {

		return alloctionApproveDAO
				.getApproveByDocumentAndCode(alloctionApprove);
	}

	@Override
	public List<AlloctionApprove> serachHistoryPerson(
			AlloctionApprove alloctionApprove) {

		return alloctionApproveDAO.serachHistoryPerson(alloctionApprove);
	}
}
