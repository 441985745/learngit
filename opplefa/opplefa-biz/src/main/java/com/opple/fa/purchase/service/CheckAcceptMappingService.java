package com.opple.fa.purchase.service;

import com.opple.fa.purchase.entity.CheckAcceptMapping;
import com.opple.fa.purchase.entity.CheckAcceptance;
import com.opple.fa.purchase.entity.CheckAcceptanceDetail;
import com.opple.fa.purchase.entity.CheckMachine;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.model.CheckAcceptanceModel;
import com.opple.util.ExecuteResult;
import com.opple.util.Pager;

import java.text.ParseException;
import java.util.List;

/**
 *<p>@Description: 验收单--关联查询用到的表的数据
 *<p>@author lipengju
 *<p>@date 2016年11月8日 上午11:58:57
 */
public interface CheckAcceptMappingService {

	/**
	 * 分页查询--验收单主界面
	 * @param pager
	 * @param checkAcceptMapping
	 * @return
	 */
	Pager<CheckAcceptMapping> searchCheckAcceptMapping(Pager<CheckAcceptMapping> pager, CheckAcceptMapping checkAcceptMapping);

	/**
	 * 导出
	 * @param checkAcceptMapping
	 * @return
	 */
	List<CheckAcceptMapping> exportCheck(CheckAcceptMapping checkAcceptMapping);

	/**(待选择界面)  验收 头 
	 * @param receiveGoodsId
	 * @return
	 */
	CheckAcceptMapping searchTempReceiveGoodsByReceiveGoodsId(String receiveGoodsId);
	
	/**(待选择界面)  验收  行
	 * @param receiveGoodsId
	 * @return
	 */
	List<CheckAcceptMapping> searchTempReceiveGoodsDetailByReceiveGoodsId(String receiveGoodsId, String userCode);
	
	/**(验收单 待选择界面) 需求明细 所有的收货待验收的
	 * @param receiveGoodsId
	 * @return
	 */
	List<CheckAcceptMapping> searchAllDemandDetailIdByReceiveGoodsId(String receiveGoodsId, String receiveGoodsDetailId, String userCode);
	
	/**(验收单 修改) 查询待验收的需求明细 
	 * @param receiveGoodsId
	 * @return
	 */
	List<CheckAcceptMapping> searchAllDemandDetailIdByCheckAcceptId(String checkAcceptId);	
	

	/**查询 得到验收单的 头 
	 * @param receiveGoodsId
	 * @param receiveGoodsDetailId
	 * @return
	 */
	CheckAcceptMapping getCheckAcceptanceByReceiveGoodsDetailId(String receiveGoodsId, String receiveGoodsDetailId);
	
	/**查询 得到验收单的 行 
	 * @param receiveGoodsId
	 * @param purchaseDetailId
	 * @return
	 */
	List<CheckAcceptMapping> getCheckAcceptanceDetailByPurchaseDetailId(String receiveGoodsId, String applyDetailId);
	
	/**保存验收单
	 * @param checkAcceptance
	 * @param checkAcceptanceDetaillist
	 * @param checkAcceptMappinglist
	 * @param checkMachine
     * @return
	 */
	Long save(CheckAcceptance checkAcceptance, List<CheckAcceptanceDetail> checkAcceptanceDetaillist, List<CheckAcceptMapping> checkAcceptMappinglist, CheckMachine checkMachine) throws ParseException;
	
	/**保存验收单的头，保存绑定审批流用
	 * @param checkAcceptance
	 * @return
	 */
	Long saveCheckAcceptance(CheckAcceptance checkAcceptance);
	
	/**同意审批
     * @param receiveGoodsModel
     * @param isAssetAccounting
	 * @return
     */
	ExecuteResult<CheckAcceptance> agreeWorkFlow(CheckAcceptanceModel checkAcceptanceModel, String isManager, String isAssetAccounting, List<CheckAcceptanceDetail> list) throws ParseException;
	
	/**退回
	 * @param receiveGoodsModel
	 * @return
	 */
	ExecuteResult<CheckAcceptance> denyWorkFlow(CheckAcceptanceModel checkAcceptanceModel);
	 /**
     * 弃审
     * @param demandOrder
     * @return
     */
    ExecuteResult<CheckAcceptance> dropWorkFlow(CheckAcceptanceModel checkAcceptanceModel);

	/**(验收单 修改) 查询 得到验收单的 行
	 * @param checkAcceptId
	 * @return
	 */
	List<CheckAcceptMapping> getCheckAcceptanceDetailByCheckAcceptId(String checkAcceptId);
	
	
	/**(验收单 修改 ) 保存
	 * @param checkAcceptance
	 * @param checkAcceptanceDetaillist
	 * @param checkAcceptMappinglist
	 * @param checkMachine
     * @return
	 */
	Long saveUpdate(CheckAcceptance checkAcceptance, List<CheckAcceptanceDetail> checkAcceptanceDetaillist, List<CheckAcceptMapping> checkAcceptMappinglist, CheckMachine checkMachine) throws ParseException;
	List<CheckAcceptMapping> getEbeln(String purchaseId);

	/**
	 * 查询实体类
	 * @param purchaseCommon
	 * @return
	 */
    CheckAcceptMapping getEbelnOnly(PurchaseCommon purchaseCommon);

	/**
	 * 根据cardid从资产卡片表中取出信息,方便前台修改页面展示
	 * @param cam
	 */
	CheckAcceptanceDetail searchAssetFormation(CheckAcceptanceDetail cam);

    String searchDeptCode(String attachDepartManagerCode);


    Long getCountCheckAcceptanceByReceiveGoodsId(String receiveGoodsId);

    void deleteCheck(List<String> list1);

    Pager<CheckAcceptMapping> searchCheckAcceptMappingAll(Pager<CheckAcceptMapping> pager, CheckAcceptMapping checkAcceptMapping);

    List<CheckAcceptMapping> exportCheckHaveChecked(CheckAcceptMapping checkAcceptMapping);
}
