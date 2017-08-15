package com.opple.fa.config.dao;

import com.opple.fa.config.entity.AssetClassification;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ykz on 17/1/10.
 */
public interface AssetClassificationDAO {

    /**
     * 查询
     * @return
     */
    List<AssetClassification> searchAssetClassification(@Param("applyOrder")ApplyOrder applyOrder);
    /**
     * 根据资产分类查询
     * @param applyOrderDetail
     * @return
     */
    AssetClassification searchAssetClassificationByAssetClassification(ApplyOrderDetail applyOrderDetail);


    /**
     * 模糊查询
     * @param pager
     * @param assetClassification
     * @return
     */
    List<AssetClassification> searchAssetClassificationList(@Param("pager") Pager<AssetClassification> pager, @Param("assetClassification") AssetClassification assetClassification);
    Long searchAssetClassificationListCount(@Param("assetClassification")AssetClassification assetClassification);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    AssetClassification getAssetClassificationByID(String id);

    /**
     * 导入时验证导入的编码是否存在
     * @param assetClassification
     * @return
     */
    Integer getAssetClassificationNumByAssetClassification(String assetClassification);
    /**
     * 添加资产分类
     * @param assetClassification
     */
    void addAssetClassification(AssetClassification assetClassification);

    /**
     * 修改分类
     * @param assetClassification
     */
    void updateAssetClassification(AssetClassification assetClassification);

    void updateStatusOfAssetClassification(AssetClassification assetClassification);

    /**
     * 导出
     * @param assetClassification
     * @return
     */
    List<AssetClassification> exportAssetClassificationList(@Param("assetClassification")AssetClassification assetClassification);
    /**
     * 根据资产分类查询
     * @param id
     * @return
     */
    AssetClassification getAssetClassificationByClass(String assetClassification);
    /**
     * 根据资产修改最大值
     * @param id
     * @return
     */
    void updateMaxByAssetClass(AssetClassification assetClassification);

    Integer checkExisted(@Param("assetClassification") AssetClassification assetClassification);

}

