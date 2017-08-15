package com.opple.fa.purchase.dao;


import com.opple.fa.purchase.entity.ReceiveGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ReceiveGoodsDAO {

    /**
     * 查询序列  生成收货单编码用
     *
     * @return
     */
    Long searchNextSequence();

    /**
     * 保存收货头
     *
     * @param receiveGoods
     * @return
     */
    Long saveReceiveGoods(ReceiveGoods receiveGoods);

    /**
     * 修改收货头
     *
     * @param receiveGoods
     * @return
     */
    Long updateReceiveGoods(ReceiveGoods receiveGoods);

    /**
     * 查询收货单的头
     *
     * @param receiveGoodsId
     * @return
     */
    ReceiveGoods searchReceiveGoodsByReceiveGoodsId(String receiveGoodsId);

    /**
     * 绑定审批流 失败 后删除收货单的表头
     *
     * @param receiveGoodsId
     */
    void delReceiveGoodsByReceiveGoodsId(String receiveGoodsId);

    ReceiveGoods getReceiveGoodsByDocument(String receiveGoodsId);

    Long updateReceiveGoods1(ReceiveGoods receiveGoods);

    void updateCloseReceiveGoods(List<String> list);

    List<ReceiveGoods> getUserListPager(@Param("userCode") String userCode, @Param("userName") String userName, @Param("budgetDepartmentCode") String budgetDepartmentCode, @Param("searchName") String searchName, @Param("searchCode") String searchCode);

    List<ReceiveGoods> searchReceiveGoodsByPurchaseId(String purchaseId);

    String searchPurchaseOrderStatus(String purchaseId);

    void saveDelete(ReceiveGoods receiveGoods);
}
