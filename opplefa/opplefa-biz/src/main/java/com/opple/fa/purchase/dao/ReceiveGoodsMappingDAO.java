package com.opple.fa.purchase.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.ReceiveGoodsMapping;
import com.opple.util.Pager;

/**
 *  *<p>@Description: 收货单映射表（关联订单表，订单详情表）
 *  *<p>@author lipengju
 *  *<p>@date 2016年11月24日 下午5:59:33
 */
public interface ReceiveGoodsMappingDAO {

    /**
     * 分页查询
     *
     * @param pager
     * @param receiveGoodsMapping
     * @return
     */
    List<ReceiveGoodsMapping> searchReceiveGoodsMapping(@Param("pager") Pager pager, @Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    List<ReceiveGoodsMapping> searchAllReceiveGoodsMapping(@Param("pager") Pager pager, @Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    Long searchReceiveGoodsMappingCount(@Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    Long searchAllReceiveGoodsMappingCount(@Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    /**
     * 收货单头  查询
     *
     * @param receiveGoodsModel
     * @return
     */
    ReceiveGoodsMapping searchPurchaseOrderMappingByPurchaseId(String purchaseId);

    /**
     * 收货单行 查询
     *
     * @param receiveGoodsModel
     * @return
     */
    List<ReceiveGoodsMapping> searchPurchaseOrderDetailMappingByPurchaseId(String purchaseId);

    /**
     * 更改收货数量    需求明细   查询
     *
     * @param receiveGoodsModel
     * @return
     */
    List<ReceiveGoodsMapping> searchPurchaseApplyMappingByPurchaseId(String purchaseId);


    /**
     * 查新公共表里的sap次级编码
     *
     * @param purchaseId
     * @return
     */
    List<ReceiveGoodsMapping> searchPurchaseCommonSapAssetsCodeByPurchaseId(String purchaseId);


    /**
     * 资产编码 sap次级拆分后的查询
     *
     * @param receiveGoodsModel
     * @return
     */
    List<ReceiveGoodsMapping> searchSapAssetsCodeByPurchaseId(String purchaseId);

    /**
     * 查询需求单号 分配给对应的sap次级编码
     *
     * @param receiveGoodsModel
     * @return
     */
    List<ReceiveGoodsMapping> searchDemandIdByPurchaseId(String purchaseId);

    /**
     * 保存到需求明细表(TB_FA_RECEIVE_CHECK_MAPPING)，验收时要用
     *
     * @param receiveGoodsMapping
     * @return
     */
    Long saveReceiveCheckMapping(ReceiveGoodsMapping receiveGoodsMapping);

    /**
     * (收货单 修改)  回写TB_FA_RECEIVE_CHECK_MAPPING
     *
     * @param receiveGoodsMapping
     * @return
     */
    Long updateReceiveGoodsMapping(ReceiveGoodsMapping receiveGoodsMapping);

    /**
     * 存放位置弹框
     *
     * @param receiveGoodsMapping
     * @return
     */
    List<ReceiveGoodsMapping> searchStorageLocationByOfficeLocation(ReceiveGoodsMapping receiveGoodsMapping);

    /**
     * 获取需求单据号
     *
     * @param applyId
     * @return
     */
    ReceiveGoodsMapping searchDemandMappingByPurchaseId(@Param("applyOrderId") String applyOrderId);

    /**
     * 导出功能查询list
     *
     * @param receiveGoodsMapping
     * @return
     */
    List<ReceiveGoodsMapping> exportReceive(@Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    void deleteReceiveCheck(@Param("receiveGoodsId") String receiveGoodsId);

    List<ReceiveGoodsMapping> searchReceiveGoodsMappingList(@Param("receiveGoodsId") String receiveGoodsId);

    List<ReceiveGoodsMapping> exportReceive1(@Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    List<ReceiveGoodsMapping> searchReceiveGoodsMappingForReceive(@Param("pager") Pager<ReceiveGoodsMapping> pager, @Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    Long searchReceiveGoodsMappingForReceiveCount(@Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    List<ReceiveGoodsMapping> searchReceiveGoodsMappingForReceiveAll(@Param("pager") Pager<ReceiveGoodsMapping> pager, @Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    Long searchReceiveGoodsMappingForReceiveAllCount(@Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    List<ReceiveGoodsMapping> exportReceiveHavingReceived(@Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    List<ReceiveGoodsMapping> export1ReceiveHavingReceived(@Param("receiveGoodsMapping") ReceiveGoodsMapping receiveGoodsMapping);

    List<ReceiveGoodsMapping> searchPurchaseCommonByPurchaseId(String purchaseId);

    void saveDeleteBranch(List<ReceiveGoodsMapping> rgms);
}
