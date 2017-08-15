package com.opple.fa.purchase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.CheckAcceptMapping;
import com.opple.fa.purchase.entity.CheckAcceptance;
import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.util.Pager;


/**
 *  *<p>@Description: 验收单--关联查询用到的表的数据
 *  *<p>@author lipengju
 *  *<p>@date 2016年11月8日 上午11:57:34
 */
public interface CheckAcceptMappingDAO {
    List<CheckAcceptMapping> searchCheckAcceptMapping(@Param("pager") Pager pager, @Param("checkAcceptMapping") CheckAcceptMapping checkAcceptMapping);

    Long searchCheckAcceptMappingCount(@Param("checkAcceptMapping") CheckAcceptMapping checkAcceptMapping);

    /**
     * (待选择界面)  验收 头
     *
     * @param receiveGoodsId
     * @return
     */
    CheckAcceptMapping searchTempReceiveGoodsByReceiveGoodsId(String receiveGoodsId);

    /**
     * (待选择界面)  验收   行
     *
     * @param receiveGoodsId
     * @return
     */
    List<CheckAcceptMapping> searchTempReceiveGoodsDetailByReceiveGoodsId(@Param("receiveGoodsId") String receiveGoodsId, @Param("userCode") String userCode);

    /**
     * (验收单 待选择界面) 需求明细 所有的收货待验收的
     *
     * @param receiveGoodsId
     * @return
     */
    List<CheckAcceptMapping> searchAllDemandDetailIdByReceiveGoodsId(@Param("receiveGoodsId") String receiveGoodsId,
                                                                     @Param("receiveGoodsDetailId") String receiveGoodsDetailId, @Param("userCode") String userCode);

    /**
     * (验收单 修改) 待验收的需求明细
     *
     * @param checkAcceptId
     * @return
     */
    List<CheckAcceptMapping> searchAllDemandDetailIdByCheckAcceptId(String checkAcceptId);

    /**
     * 查询 得到验收单的 头
     *
     * @param receiveGoodsId
     * @param receiveGoodsDetailId
     * @return
     */
    CheckAcceptMapping getCheckAcceptanceByReceiveGoodsDetailId(@Param("receiveGoodsId") String receiveGoodsId, @Param("receiveGoodsDetailId") String receiveGoodsDetailId);

    /**
     * 查询 得到验收单的 行
     *
     * @param receiveGoodsId
     * @param applyDetailId
     * @return
     */
    List<CheckAcceptMapping> getCheckAcceptanceDetailByPurchaseDetailId(@Param("receiveGoodsId") String receiveGoodsId, @Param("applyDetailId") String applyDetailId);

    /**
     * (验收单 保存)   回写收货验收映射表tb_fa_receive_check_mapping.check_count_demand
     *
     * @param checkAcceptMapping
     * @return
     */
    Long updateReceiveCheckMappingByDemandDetailId(CheckAcceptMapping checkAcceptMapping);

    /**
     * (验收单  修改)   回写收货验收映射表tb_fa_receive_check_mapping.check_count_demand
     *
     * @param checkAcceptMapping
     * @return
     */
    Long updateReceiveCheckMappingByDemandDetailIdChange(CheckAcceptMapping checkAcceptMapping);

    /**
     * (验收单 保存) 回写收货行已经收货数量( tb_fa_receive_goods_detail.check_count)
     *
     * @param checkAcceptance
     * @return
     */
    Long updateReceiveGoodsDetailByReceiveGoodsDetailId(CheckAcceptance checkAcceptance);

    /**
     * (验收单 修改) 回写收货行已经收货数量( tb_fa_receive_goods_detail.check_count)
     *
     * @param checkAcceptance
     * @return
     */
    Long updateReceiveGoodsDetailByReceiveGoodsDetailIdChange(CheckAcceptance checkAcceptance);

    /**
     * (验收单 修改) 查询 得到验收单的 行
     *
     * @param checkAcceptId
     * @return
     */
    List<CheckAcceptMapping> getCheckAcceptanceDetailByCheckAcceptId(String checkAcceptId);

    List<CheckAcceptMapping> getEbeln(String purchaseId);


    /**
     * 查询实体类
     *
     * @param purchaseCommon
     * @return
     */
    CheckAcceptMapping getEbelnOnly(PurchaseCommon purchaseCommon);

    String searchDeptCode(@Param("attachDepartManagerCode") String attachDepartManagerCode);

    List<CheckAcceptMapping> exportCheck(@Param("checkAcceptMapping") CheckAcceptMapping checkAcceptMapping);

    Long getCountCheckAcceptanceByReceiveGoodsId(@Param("receiveGoodsId") String receiveGoodsId);

    List<CheckAcceptMapping> getCheckAcceptMapping(String acceptId);

    void updateReceiveOrderStatus(String receiveGoodsId);

    List<CheckAcceptMapping> searchCheckAcceptMappingAll(@Param("pager") Pager<CheckAcceptMapping> pager, @Param("checkAcceptMapping") CheckAcceptMapping checkAcceptMapping);

    Long searchCheckAcceptMappingAllCount(@Param("checkAcceptMapping") CheckAcceptMapping checkAcceptMapping);

    List<CheckAcceptMapping> exportCheckHaveChecked(@Param("checkAcceptMapping") CheckAcceptMapping checkAcceptMapping);
}
