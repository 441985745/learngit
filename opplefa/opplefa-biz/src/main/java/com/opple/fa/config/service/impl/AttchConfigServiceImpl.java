package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.AttchConfigDAO;
import com.opple.fa.config.entity.AssetsBudgetFromBUD;
import com.opple.fa.config.entity.AttchConfig;
import com.opple.fa.config.service.AttchConfigService;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AttchConfigServiceImpl implements AttchConfigService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AttchConfigServiceImpl.class);

	@Getter @Setter
	private AttchConfigDAO attchConfigDAO;

	@Override
	public AttchConfig searchByCostcenterCodeAndAssetType(AttchConfig attchConfig) {
		return attchConfigDAO.searchByCostcenterCodeAndAssetType(attchConfig);
	}

	@Override
	public AttchConfig searchByCostcenterCode(AssetsBudgetFromBUD assetsBudgetFromBUD) {
		return attchConfigDAO.searchByCostcenterCode(assetsBudgetFromBUD);
	}

	@Override
	public List<AttchConfig> serachByUseCode(AttchConfig attchConfig) {
		return attchConfigDAO.serachByUseCode(attchConfig);
	}

	@Override
	public AttchConfig searchByPurchaseManagerCode(String id) {
		return attchConfigDAO.searchByPurchaseManagerCode(id);
	}

	@Override
	public AttchConfig searchPurchaseManager(String id) {
		// TODO Auto-generated method stub
		return attchConfigDAO.searchPurchaseManager(id);
	}

	@Override
	public List<AttchConfig> getAssetTypeListByAdmin(String userCode) {
		return attchConfigDAO.getAssetTypeListByAdmin(userCode);
	}

	@Override
	public Pager<AttchConfig> searchAttchConfigList(Pager<AttchConfig> pager, AttchConfig attchConfig) {
		List<AttchConfig> attchConfigList = attchConfigDAO.searchAttchConfigList(pager, attchConfig);
		Long attchConfigListCount = attchConfigDAO.searchAttchConfigListCount(attchConfig);
		return Pager.cloneFromPager(pager, attchConfigListCount, attchConfigList);
	}

	@Override
	public AttchConfig getAttchConfigByID(String id) {
		return attchConfigDAO.getAttchConfigByID(id);
	}

	@Override
	public Integer getAttchConfigNumByImport(AttchConfig attchConfig) {
		return attchConfigDAO.getAttchConfigNumByImport(attchConfig);
	}

	@Override
	public void addAttchConfig(AttchConfig attchConfig) {
		attchConfigDAO.addAttchConfig(attchConfig);
	}

	@Override
	public void addAttchConfigList(List<AttchConfig> attchConfigList) {
		for (AttchConfig attchConfig : attchConfigList) {
			attchConfigDAO.addAttchConfig(attchConfig);
		}
	}

	@Override
	public void updateAttchConfig(AttchConfig attchConfig) {
		attchConfigDAO.updateAttchConfig(attchConfig);
	}

	@Override
	public void updateAttchConfigList(List<AttchConfig> attchConfigList) {
		for (AttchConfig attchConfig : attchConfigList) {
			attchConfigDAO.updateAttchConfig(attchConfig);
		}
	}

	@Override
	public void updateStatusOfAttchConfigList(List<AttchConfig> attchConfigList) {
		for (AttchConfig attchConfig : attchConfigList) {
			attchConfigDAO.updateStatusOfAttchConfig(attchConfig);
		}
	}

	@Override
    public void addOrUpdateAttchConfig(List<AttchConfig> addAttachConfigList, List<AttchConfig> updateAttachConfigList) {
        if (addAttachConfigList.size() > 0) {
            addAttchConfigList(addAttachConfigList);
        }
        if (updateAttachConfigList.size() > 0) {
            updateAttchConfigList(updateAttachConfigList);
        }
	}

	@Override
	public List<AttchConfig> exportAttchConfigList(AttchConfig attchConfig) {
		return attchConfigDAO.exportAttchConfigList(attchConfig);
	}
}
