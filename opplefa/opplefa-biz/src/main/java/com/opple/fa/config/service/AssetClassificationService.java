package com.opple.fa.config.service;

import com.opple.fa.config.entity.AssetClassification;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.util.Pager;

import java.util.List;

/**
 * Created by ykz on 17/1/10.
 */
public interface AssetClassificationService {

    /**
     * 资产分类
     *
     * @param applyOrder
     * @return
     */
    List<AssetClassification> searchAssetClassification(ApplyOrder applyOrder);

    /**
     * 根据资产分类查询
     *
     * @param applyOrderDetail
     * @return
     */
    AssetClassification searchAssetClassificationByAssetClassification(ApplyOrderDetail applyOrderDetail);


    /**
     * 模糊查询
     *
     * @param pager
     * @param assetClassification
     * @return
     */
    Pager<AssetClassification> searchAssetClassificationList(Pager<AssetClassification> pager, AssetClassification assetClassification);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    AssetClassification getAssetClassificationByID(String id);

    /**
     * 根据资产分类查询数量
     *
     * @param assetClassification
     * @return
     */
    Integer getAssetClassificationNumByAssetClassification(String assetClassification);

    /**
     * 添加资产分类
     *
     * @param assetClassification
     */
    void addAssetClassification(AssetClassification assetClassification);
    void addAssetClassifications(List<AssetClassification> assetClassifications);

    /**
     * 修改资产分类
     *
     * @param assetClassification
     */
    void updateAssetClassification(AssetClassification assetClassification);
    void updateAssetClassifications(List<AssetClassification> assetClassifications);

    void updateStatusOfAssetClassifications(List<AssetClassification> assetClassifications);


    String addOrUpdateAssetClassifications(List<AssetClassification> addAssetClassifications, List<AssetClassification> updateAssetClassifications);

    List<AssetClassification> exportAssetClassificationList(AssetClassification assetClassification);

    /**
     * 根据资产分类查询
     *
     * @return
     */
    AssetClassification getAssetClassificationByClass(String assetClassification);

    /**
     * 根据资产修改最大值
     *
     * @return
     */
    void updateMaxByAssetClass(AssetClassification assetClassification);

    Integer checkExisted(AssetClassification assetClassification);
}
