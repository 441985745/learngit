package com.opple.fa.config.service;

import java.util.List;


import com.opple.fa.config.entity.BudgetAdjustmentDetail;

import com.opple.fa.config.entity.AssetsBudgetFromBUD;
import com.opple.fa.config.entity.UserInfo;

/**
 * @Description: 
 * @author lipengju
 * @date 2016年10月27日 上午10:11:01
 */
public interface UserInfoService {
	
	/**
	 * 根据部门树查询 公司 和 成本中心
	 * @param userInfo
	 * @return
	 */
	UserInfo treeDept(String ckscode);
	
	/**
	 * 根据部门树查询 公司名称 
	 * @param ckscode
	 * @return
	 */
	List<UserInfo> getCompany(String ckscode);
	
	/**
	 * 根据部门公司查询平台
	 * @param userInfo
	 * @return
	 */
	UserInfo getCostCenter(UserInfo userInfo);
	
	/**
	 * 根据公司查询对应的资产类型
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> searchAssetsTypeInfo(UserInfo userInfo);
	
	/**
	 * 查询所有的办公地点
	 * @return
	 */
	List<UserInfo> searchAddressInfo();
	
	/**
	 * 根据办公地点查询采购员
	 * @param userInfo
	 * @return 
	 */
	List<UserInfo> searchPurchaseInfo(UserInfo userInfo);
	
	/**
	 * 根据公司查询  平台
	 * @param userInfo
	 * @return 
	 */
	List<UserInfo> searchPlatformInfo(UserInfo userInfo);

	/**
	 * 查询所有的币种
	 * @return  
	 */
	List<UserInfo> searchCurrencyInfo();
	
	/**
	 * 根据平台查 区域
	 * @param userInfo
	 * @return 
	 */
	List<UserInfo> searchAreasInfo(UserInfo userInfo);
	
	/**
	 * 根据资产类型 查询 车间类型
	 * @param userInfo
	 * @return 
	 */
	List<UserInfo> searchDepartInfo(UserInfo userInfo);
	
	/**
	 * 根据申请人查询所属部门
	 * @param userInfo
	 * @return  
	 */
	List<UserInfo> searchOpdeptInfo(UserInfo userInfo);
	
	/**
	 * 查询所有的部门
	 * @return
	 */
	List<UserInfo> searchDeptAllInfo(UserInfo userInfo);
	
	/**
	 * 通过申请人编码查询部门
	 */
	UserInfo searchDepartment(UserInfo userInfo);
	
	/**
	 * 根据办公地点查询调拨后存放位置
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> getCpsition(UserInfo userInfo);
	/**
	 * 根据成本中心编码查询成本中心名称
	 * @param userInfo
	 * @return
	 */
	UserInfo searchCostcenterName(AssetsBudgetFromBUD assetsBudgetFromBUD);

	/**
	 * 根据成本中心查部门
	 * @param budgetAdjustmentDetail
	 * @return
	 */
	UserInfo searchDepInfoByCostCenter(BudgetAdjustmentDetail budgetAdjustmentDetail);

	/**
	 * 根据申请人编码查询对应供应商编码
	 * @param userInfo
	 * @return
	 */
	UserInfo searchSupplierCode(UserInfo userInfo);
	/**
	 * 查询所有的公司
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> searchAllCompany();
	/**
	 * 离职授权，查询当前登录人对应的所有授权人
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> searchCauthorizerCode(UserInfo userInfo);
	/**
	 * 根据部门查询是否是末级（0不是末级，大于等于1是末级）
	 * @param userInfo
	 * @return
	 */
	Long searchLowDeptCount(String ckscode);
	
	/**
	 * 根据员工编码查询名称和类型
	 * @param userInfo
	 * @return
	 */
	UserInfo searchUserNameAndType(UserInfo userInfo);

	/**
	 * 根据成本中心编码和公司代码查询内部订单号
	 * @param userInfo
	 * @return
	 */
	UserInfo getInternalOrderCode(UserInfo userInfo);
	
	Integer countUserByCodeAndName(String userCode, String userName);
}
