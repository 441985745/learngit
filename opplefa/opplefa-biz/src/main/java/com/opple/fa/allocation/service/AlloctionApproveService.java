package com.opple.fa.allocation.service;

import com.opple.fa.allocation.entity.AlloctionApprove;

import java.util.List;

public interface AlloctionApproveService {
	/**
	 * 保存
	 */
	void insertAllocationApprove(AlloctionApprove alloctionApprove);

	/**
	 * 查询条数
	 * 
	 * @param alloctionApprove
	 * @return
	 */
	long countAllocationApproveBycode(AlloctionApprove alloctionApprove);

	/**
	 * 通过用户编码查询所有的 需确认的单据
	 * 
	 * @param alloctionApprove
	 * @return
	 */
	List<AlloctionApprove> serachAlloctionApprove(
			AlloctionApprove alloctionApprove);

	/**
	 * 获得所有的 是否确认过的状态
	 * 
	 * @param document
	 * @return
	 */
	List<String> serachIsConfirm(String document);

	/**
	 * 经理确认完 修改 确认状态
	 */
	void updateIsConfirm(AlloctionApprove alloctionApprove);

	void deleteAllocationApprove(String alloctionDocument);

	/**
	 * 通过docment 查询所有有关记录
	 * 
	 * @return
	 */
	List<AlloctionApprove> serachAlloctionApproveByDocument(
			AlloctionApprove alloctionApprove);

	/**
	 * 通过docment 查询所有未确认的记录
	 * 
	 * @return
	 */
	List<AlloctionApprove> serachApproveByDocumentAndConfirm(
			AlloctionApprove alloctionApprove);

	/**
	 * 退回 修改全部的确认状态
	 */
	void updateIsConfirmByBack(AlloctionApprove alloctionApprove);

	/**
	 * 通过单据号和确认人编码 查询单条数据
	 * 
	 * @return
	 */
	AlloctionApprove getApproveByDocumentAndCode(
			AlloctionApprove alloctionApprove);
	/**
	 * 获得所有的确认历史
	 */
	List<AlloctionApprove> serachHistoryPerson(AlloctionApprove alloctionApprove);
}
