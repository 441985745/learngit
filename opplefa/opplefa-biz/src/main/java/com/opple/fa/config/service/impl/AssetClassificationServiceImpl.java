package com.opple.fa.config.service.impl;

import com.opple.fa.config.dao.AssetClassificationDAO;
import com.opple.fa.config.entity.AssetClassification;
import com.opple.fa.config.service.AssetClassificationService;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by ykz on 17/1/10.
 */
public class AssetClassificationServiceImpl implements AssetClassificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssetClassificationServiceImpl.class);

    @Getter @Setter
    private AssetClassificationDAO assetClassificationDAO;

    @Override
    public List<AssetClassification> searchAssetClassification(ApplyOrder applyOrder) {
        return assetClassificationDAO.searchAssetClassification(applyOrder);
    }

	@Override
	public AssetClassification searchAssetClassificationByAssetClassification(ApplyOrderDetail applyOrderDetail) {
		return assetClassificationDAO.searchAssetClassificationByAssetClassification(applyOrderDetail);
	}

    @Override
    public Pager<AssetClassification> searchAssetClassificationList(Pager<AssetClassification> pager, AssetClassification assetClassification) {
        List<AssetClassification> assetClassifications = assetClassificationDAO.searchAssetClassificationList(pager, assetClassification);
        Long assetClassificationsCount = assetClassificationDAO.searchAssetClassificationListCount(assetClassification);
        return Pager.cloneFromPager(pager, assetClassificationsCount, assetClassifications);
    }

    @Override
    public AssetClassification getAssetClassificationByID(String id) {
        return assetClassificationDAO.getAssetClassificationByID(id);
    }

    @Override
    public Integer getAssetClassificationNumByAssetClassification(String assetClassification) {
        return assetClassificationDAO.getAssetClassificationNumByAssetClassification(assetClassification);
    }

    @Override
    public void addAssetClassification(AssetClassification assetClassification) {
        assetClassificationDAO.addAssetClassification(assetClassification);
    }

    @Override
    public void addAssetClassifications(List<AssetClassification> assetClassifications) {
        for (AssetClassification assetClassification : assetClassifications) {
            assetClassificationDAO.addAssetClassification(assetClassification);
        }
    }

    @Override
    public void updateAssetClassification(AssetClassification assetClassification) {
        assetClassificationDAO.updateAssetClassification(assetClassification);
    }

    @Override
    public void updateAssetClassifications(List<AssetClassification> assetClassifications) {
        for (AssetClassification assetClassification : assetClassifications) {
            assetClassificationDAO.updateAssetClassification(assetClassification);
        }
    }

    @Override
    public void updateStatusOfAssetClassifications(List<AssetClassification> assetClassifications) {
        for (AssetClassification assetClassification : assetClassifications) {
            assetClassificationDAO.updateStatusOfAssetClassification(assetClassification);
        }
    }

    @Override
    public String addOrUpdateAssetClassifications(List<AssetClassification> addAssetClassifications, List<AssetClassification> updateAssetClassifications) {
        String result = "";
        if (addAssetClassifications.size() > 0) {
            try {
                addAssetClassifications(addAssetClassifications);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
                result += "新增资产分类添加失败 !\n";
            }
        }
        if (updateAssetClassifications.size() > 0) {
            try {
                updateAssetClassifications(updateAssetClassifications);
            } catch (Exception e) {
                LOGGER.error("Exception", e);
                result += "更新资产分类维护失败 !\n";
            }
        }
        return result;
    }

    @Override
    public List<AssetClassification> exportAssetClassificationList(AssetClassification assetClassification) {
        return assetClassificationDAO.exportAssetClassificationList(assetClassification);
    }

	@Override
	public AssetClassification getAssetClassificationByClass(
			String assetClassification) {
		
		return assetClassificationDAO.getAssetClassificationByClass(assetClassification);
	}

	@Override
	public void updateMaxByAssetClass(AssetClassification assetClassification) {
		assetClassificationDAO.updateMaxByAssetClass(assetClassification);
		
	}

    @Override
    public Integer checkExisted(AssetClassification assetClassification) {
        return assetClassificationDAO.checkExisted(assetClassification);
    }

}
