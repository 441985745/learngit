package com.opple.fa.config.dao;

import com.opple.fa.config.entity.AssetsBudgetFromBUD;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttchConfigDAO {

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
	 * 根据人员编码 查成本中心与类型
	 * @param attchConfig
	 * @return
	 */
	List<AttchConfig> serachByUseCode(AttchConfig attchConfig);
	/**
	 * 根据采购员工号查询采购经理
	 * @param attchConfig
	 * @return
	 */
	AttchConfig searchPurchaseManager(String id);

	AttchConfig searchByPurchaseManagerCode(String id);

	/**
	 * 根据归口部门经理编码查询资产类型
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
	List<AttchConfig> searchAttchConfigList(@Param("pager") Pager<AttchConfig> pager, @Param("attchConfig") AttchConfig attchConfig);
	Long searchAttchConfigListCount(@Param("attchConfig")AttchConfig attchConfig);

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

	/**
	 * 修改归口/负责人
	 * @param attchConfig
	 */
	void updateAttchConfig(AttchConfig attchConfig);

    void updateStatusOfAttchConfig(AttchConfig attchConfig);

	/**
	 * 导出
	 * @param attchConfig
	 * @return
	 */
	List<AttchConfig> exportAttchConfigList(@Param("attchConfig")AttchConfig attchConfig);
}
