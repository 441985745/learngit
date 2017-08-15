package com.opple.fa.myTasks.dao;

import com.opple.fa.myTasks.entity.MyApplyTasks;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyApplyTasksDAO {
    /**
     * 我的待办模糊查询-所有单据类型
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyApplyTasksList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);

    Long searchMyApplyTasksListCounts(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊查询-需求
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyDemandOrderList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);

    Long searchMyDemandOrderListCounts(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊申请-订单
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyApplyOrderList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);

    Long searchMyApplyOrderListCounts(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊订单-收货
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyPurchaseOrderList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);

    Long searchMyPurchaseOrderListCounts(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊收货-验收
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyReceiveGoodsList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);

    Long searchMyReceiveGoodsListCounts(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊订单-付款
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyPurchaseFkList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);

    Long searchMyPurchaseFkListCounts(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊查询-所有单据类型
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyApplyTasksListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊查询-需求
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyDemandOrderListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊申请-订单
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyApplyOrderListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊订单收货
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyPurchaseOrderListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊收货--验收
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyReceiveGoodsListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊订单--付款
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyPurchaseFkListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

}
