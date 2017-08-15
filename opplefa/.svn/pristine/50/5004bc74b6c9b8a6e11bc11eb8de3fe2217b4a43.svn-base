package com.opple.fa.assetcheck.service.impl;


import com.opple.fa.assetcard.dao.GeneralAssetDAO;
import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.fa.assetcheck.dao.AssetCheckBusinessDAO;
import com.opple.fa.assetcheck.dao.AssetsInventoryDetailDAO;
import com.opple.fa.assetcheck.entity.AssetCheckBusiness;
import com.opple.fa.assetcheck.entity.AssetsInventoryDetail;
import com.opple.fa.assetcheck.service.AssetCheckBusinessService;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AssetCheckBusinessServiceImpl implements AssetCheckBusinessService {
    @Getter
    @Setter
    private AssetCheckBusinessDAO assetCheckBusinessDAO;
    @Getter
    @Setter
    private GeneralAssetDAO generalAssetDAO;
    @Getter
    @Setter
    private AssetsInventoryDetailDAO assetsInventoryDetailDAO;


    /**
     * 盘点申请单主表保存
     */
    @Override
    public void saveAssetCheckBusiness(AssetCheckBusiness assetCheckBusiness, String sublist) {

        assetCheckBusinessDAO.saveAssetCheckBusiness(assetCheckBusiness);
        saveAssetsInventoryDetail(sublist, assetCheckBusiness.getId());
    }

    @Override
    public void saveAssetsInventoryDetail(String sublist, Long id) {
        String[] splitsublist = sublist.split(",");
        AssetsInventoryDetail assetsInventoryDetail = new AssetsInventoryDetail();
        for (int i = 0; i < splitsublist.length; i++) {
            Long n = Long.parseLong(splitsublist[i]);
            assetsInventoryDetail.setAssetsInventoryId(id);
            assetsInventoryDetail.setGeneralAssetId(n);
            assetsInventoryDetail.setDeletedFlag("0");
            assetsInventoryDetailDAO.saveApply(assetsInventoryDetail);
        }
    }

    /**
     * 查询资产盘点信息
     */
    @Override
    public Pager<AssetCheckBusiness> searchAssetCheckBusiness(
            Pager<AssetCheckBusiness> pager, AssetCheckBusiness assetCheckBusiness) {

        List<AssetCheckBusiness> assetCheckBusinessList = assetCheckBusinessDAO.searchAssetCheckBusiness(pager, assetCheckBusiness);
        Long assetCheckBusinessCount = assetCheckBusinessDAO.searchAssetCheckBusinessCount(assetCheckBusiness);
        pager.setRecords(assetCheckBusinessList); //设置数据
        pager.setTotal(assetCheckBusinessCount); //设置总页数
        return Pager.cloneFromPager(pager, assetCheckBusinessCount, assetCheckBusinessList);
    }

    /**
     * 资产盘点信息删除
     */
    @Override
    public void deleteAssetCheck(AssetCheckBusiness assetCheckBusiness) {

        assetCheckBusinessDAO.deleteAssetCheck(assetCheckBusiness);
    }


    /**
     * 模糊查询盘点申请单的详情信息
     */
    @Override
    public Pager<GeneralAsset> searchGeneralAssetDetails(Pager<GeneralAsset> pager, AssetCheckBusiness assetCheckBusiness) {

        List<GeneralAsset> generalAssetList = generalAssetDAO.searchGeneralAssetDetails(pager, assetCheckBusiness);
        Long generalAssetCount = generalAssetDAO.searchDetailsCount(assetCheckBusiness);

        pager.setRecords(generalAssetList); //设置数据
        pager.setTotal(generalAssetCount); //设置总页数

        return Pager.cloneFromPager(pager, generalAssetCount, generalAssetList);
    }

    /**
     * 删除盘点申请单的详情信息
     */
    @Override
    public void deleteAssetCard(GeneralAsset generalAsset) {
        generalAssetDAO.deleteAssetCard(generalAsset);
    }

    /**
     * 资产盘点导出
     */
    @Override
    public List<AssetCheckBusiness> serchAssetCheckBusinessData(AssetCheckBusiness assetCheckBusiness) {

        return assetCheckBusinessDAO.serchAssetCheckData(assetCheckBusiness);
    }

    /**
     * 查询单条信息
     */
    @Override
    public AssetCheckBusiness getAssetCheckBusiness(
            AssetCheckBusiness assetCheckBusiness) {
        return assetCheckBusinessDAO.getAssetCheckBusiness(assetCheckBusiness);
    }
}
