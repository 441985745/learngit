package com.opple.fa.purchase.service.impl;


import com.opple.fa.purchase.dao.CheckAcceptanceDAO;
import com.opple.fa.purchase.entity.CheckAcceptance;
import com.opple.fa.purchase.entity.CheckMachine;
import com.opple.fa.purchase.model.ApproveHistoryCheckPrint;
import com.opple.fa.purchase.service.CheckAcceptanceService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CheckAcceptanceServiceImpl implements CheckAcceptanceService {
	@Getter @Setter
	private CheckAcceptanceDAO checkAcceptanceDAO;
//	生成验收编码用
	@Override
	public Long searchNextSequence() {
		return checkAcceptanceDAO.searchNextSequence();
	}
	@Override
	public CheckAcceptance getCheckAcceptanceByDocument(String checkAcceptId) {
		return checkAcceptanceDAO.getCheckAcceptanceByDocument(checkAcceptId);
	}
//	绑定审批流 失败 后删除验收单的表头
	@Override
	public void delCheckAcceptanceByCheckAcceptId(String checkAcceptId) {
		checkAcceptanceDAO.delCheckAcceptanceByCheckAcceptId(checkAcceptId);		
	}

	@Override
	public Long updateCheckAcceptanceBySap(CheckAcceptance checkAcceptance) {
		return checkAcceptanceDAO.updateCheckAcceptanceBySap(checkAcceptance);
	}

	@Override
	public List<String> getUserCode(String userCode) {
		return checkAcceptanceDAO.getUserCode(userCode);
	}

	@Override
	public void updateCheckAcceptanceByUpdateSap(String document) {
		checkAcceptanceDAO.updateCheckAcceptanceByUpdateSap(document);
	}

	@Override
	public CheckMachine getCheckMachineByCheckId(String checkAcceptId) {
		return checkAcceptanceDAO.getCheckMachineByCheckId(checkAcceptId);
	}

	@Override
	public void updateIsprint(String checkId) {
		checkAcceptanceDAO.updateIsprint(checkId);
	}

	@Override
	public List<ApproveHistoryCheckPrint> getApproveHistory(String checkAcceptId, String typeId) {
		return checkAcceptanceDAO.getApproveHistory(checkAcceptId, typeId);
	}


//	private CheckAcceptanceDetailDAO checkAcceptanceDetailDAO; 
//	@Getter @Setter
//	private CheckAcceptMappingDAO checkAcceptMappingDAO;
//	@Getter @Setter
//	private PurchaseCommonDAO purchaseCommonDAO;

	
	
//	@Override
//	public ExecuteResult<CheckAcceptance> saveCheckAcceptance(
//			CheckAcceptance checkAcceptance,
//			CheckAcceptanceDetail checkAcceptanceDetail) { //注意：验收单对收货单是一对一进行验收
////						
//		CheckAcceptanceModel cam = new CheckAcceptanceModel();
//		cam.setReceiveGoodsIdDetail(checkAcceptanceDetail.getReceiveGoodsIdDetail());
//		Long counts = purchaseCommonDAO.searchDemandStatusByReceiveGoodsIdDetail(cam);
//		if(0 == counts){ // 判断资产公共表里，需求状态是否更改			
//			List<PurchaseCommon> demandDetailIds = purchaseCommonDAO.searchDemandDetailIdByReceiveGoodsIdDetail(cam);
//			for(int i = 0; i < checkAcceptanceDetail.getThisAcceptQuantity().length(); i++){
//				cam.setDemandDetailId(demandDetailIds.get(i).getDemandDetailId());
////				cam.setThisAcceptQuantity(checkAcceptanceDetail.getThisAcceptQuantity());
//				cam.setThisAcceptQuantity(""); //这里不需要按照验收的数量进行更改，直接都改，因为验收和收货是一对一
//				purchaseCommonDAO.updatePurchaseCommonBydemandDetailId(cam);
//			}
//		}
//		checkAcceptanceDAO.saveCheckAcceptance(checkAcceptance);
//		checkAcceptanceDetailDAO.saveCheckAcceptanceDetail(checkAcceptanceDetail);
//		return null;
//	}
//	
			
//				收货单明细编码 ---- 验收单编码						不存在，看此次的验收单是不是 草稿
//																			不是草稿，直接保存   +  回写资产公共表 和 修改验收单中已经验收的数量
//																			    是草稿，直接保存     不回写资产公共表；
//												   存在，看原来的验收单是不是草稿（验收时不操作，修改时操作）
//																			不是草稿，为已经验收的正式数据，不能更改；
//																			    是草稿， 看此次的验收单是不是草稿？
//																									  不是草稿，修改验收单信息   +  回写资产公共表 和 修改验收单中已经验收的数量；
//																									          草稿，修改验收单信息     不回写资产公共表；
				
				/*		if (0 == searchCheckByReceiveGoodsIdDetail(caModel)) { // 判断验收单   不存在
							if ("N".equals(checkAcceptance.getDraft())) { // 不是草稿
								checkAcceptanceDAO.save(checkAcceptance);
								for (CheckAcceptanceDetail cad : checkAcceptanceDetails) {
									checkAcceptanceDetailDAO.save(cad);
									updateAssetsByAssetsCode(cad); // 同时回写到公共资产表里
								}
							} else { // 是草稿
								checkAcceptanceDAO.save(checkAcceptance);
								for (CheckAcceptanceDetail cad : checkAcceptanceDetails) {
									checkAcceptanceDetailDAO.save(cad);
								}
							}
						} else { // 判断验收单 --存在   看原来的验收单是不是草稿（草稿保存在验收表头里）
							if ("Y".equals(findCheckAcceptanceByCheckAcceptNo(caModel).getDraft())) { // 是草稿Y  看此次的验收单是不是草稿
								if ("Y".equals(checkAcceptance.getDraft())) { // 是草稿，更新验收单信息  不回写公共资产表
									updateCheckAcceptanceByCheckAcceptNo(checkAcceptance); // 更新收货单头信息
									for (CheckAcceptanceDetail cad : checkAcceptanceDetails) { // 更新收货单明细信息
										updateCheckAcceptanceDetailByCheckAcceptNoDetail(cad);
									}
								} else if ("N".equals(checkAcceptance.getDraft())) { // 不是草稿N，更新验收单信息 + 回写资产公共表（根据资产编码）
									updateCheckAcceptanceByCheckAcceptNo(checkAcceptance); // 更新收货单头信息
									for (CheckAcceptanceDetail cad : checkAcceptanceDetails) { // 更新收货单明细信息
										updateCheckAcceptanceDetailByCheckAcceptNoDetail(cad);
										updateAssetsByAssetsCode(cad);  // 同时回写到公共资产表里（根据资产编码，资产编码是唯一的）
									}
								}
							}
						}*/
	


	
}
