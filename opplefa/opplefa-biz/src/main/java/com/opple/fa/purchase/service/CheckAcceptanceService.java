package com.opple.fa.purchase.service;

import com.opple.fa.purchase.entity.CheckAcceptance;
import com.opple.fa.purchase.entity.CheckMachine;
import com.opple.fa.purchase.model.ApproveHistoryCheckPrint;

import java.util.List;


/**
 *<p>@Description: 验收单
 *<p>@author lipengju
 *<p>@date 2016年11月3日 下午4:47:02
 */
public interface CheckAcceptanceService {
	/**生成验收编码用
	 * @return
	 */
	Long searchNextSequence();
	
	CheckAcceptance getCheckAcceptanceByDocument(String checkAcceptId);
	
	/**绑定审批流 失败 后删除验收单的表头
	 * @param checkAcceptId
	 */
	void delCheckAcceptanceByCheckAcceptId(String checkAcceptId);

	/**
	 * 调用sap接口后,保存sap信息到验收主表中
	 * @param checkAcceptance
	 * @return
	 */
	Long updateCheckAcceptanceBySap(CheckAcceptance checkAcceptance);

    List<String> getUserCode(String userCode);


	void updateCheckAcceptanceByUpdateSap(String document);

    CheckMachine getCheckMachineByCheckId(String checkAcceptId);

    void updateIsprint(String checkId);

	List<ApproveHistoryCheckPrint> getApproveHistory(String checkAcceptId, String typeId);
}
