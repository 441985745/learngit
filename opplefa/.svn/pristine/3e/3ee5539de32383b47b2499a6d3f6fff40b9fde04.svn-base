package com.opple.fa.config.service;


import com.opple.fa.config.entity.AssetsBudgetFromBUD;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.util.Pager;

import java.util.List;


public interface AttchConfigService {
	/**
	 * 根据成本中心和资产类型查询归口配置表
	 * @return
	 */
	AttchConfig searchByCostcenterCodeAndAssetType(AttchConfig attchConfig);
	/**
	 * 根据成本中心编码查询平台
	 * @return
	 */
	AttchConfig searchByCostcenterCode(AssetsBudgetFromBUD assetsBudgetFromBUD);
	/**
	 * 根据归口部门经理编码 查成本中心与类型
	 * @param attchConfig
	 * @return
	 */
	List<AttchConfig> serachByUseCode(AttchConfig attchConfig);
	/**
	 * 根据用户编码查询（如果是采购经理，则会查出数据，abcd四个采购员，若是采购员，则查出空）
	 * @param id
	 * @return
	 */
	AttchConfig searchByPurchaseManagerCode(String id);
	/**
	 * 根据采购员工号查询采购经理
	 * @param attchConfig
	 * @return
	 */
	AttchConfig searchPurchaseManager(String id);

	/**
	 * 根据归口部门管理员编码查询资产类型
	 * @param userCode
	 * @return
	 */
	List<AttchConfig> getAssetTypeListByAdmin(String userCode);




	/**
	 * 模糊查询
	 * @param pager
	 * @param attchConfig
	 * @return
	 */
	Pager<AttchConfig> searchAttchConfigList(Pager<AttchConfig> pager, AttchConfig attchConfig);

	/**
	 * 根据归口/负责人主键查询
	 * @param id
	 * @return
	 */
	AttchConfig getAttchConfigByID(String id);

	/**
	 * 导入时验证导入的配置是否存在
	 * @param attchConfig
	 * @return
	 */
	Integer getAttchConfigNumByImport(AttchConfig attchConfig);

	/**
	 * 添加归口/负责人
	 * @param attchConfig
	 */
	void addAttchConfig(AttchConfig attchConfig);
	void addAttchConfigList(List<AttchConfig> attchConfigList);

	/**
	 * 修改归口/负责人
	 * @param attchConfig
	 */
	void updateAttchConfig(AttchConfig attchConfig);
	void updateAttchConfigList(List<AttchConfig> attchConfigList);

    void updateStatusOfAttchConfigList(List<AttchConfig> attchConfigList);

	void addOrUpdateAttchConfig(List<AttchConfig> addAttchConfigList, List<AttchConfig> updateAttchConfigList);

	/**
	 * 导出
	 * @param attchConfig
	 * @return
	 */
	List<AttchConfig> exportAttchConfigList(AttchConfig attchConfig);
}
