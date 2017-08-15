package com.opple.fa.myTasks.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.myTasks.entity.MyApplyTasks;

import com.opple.util.Pager;

public interface MyApplyTasksService {
    /**
     * 我的待办模糊查询-所有单据类型
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    Pager<MyApplyTasks> searchMyApplyTasksList(Pager<MyApplyTasks> pager, MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊查询-需求
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    Pager<MyApplyTasks> searchMyDemandOrderList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊申请-订单
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    Pager<MyApplyTasks> searchMyApplyOrderList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊订单收货
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    Pager<MyApplyTasks> searchMyPurchaseOrderList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊收货--验收
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    Pager<MyApplyTasks> searchMyReceiveGoodsList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊订单--付款
     *
     * @param pager
     * @param myApplyTasks
     * @return
     */
    Pager<MyApplyTasks> searchMyPurchaseFkList(@Param("pager") Pager<MyApplyTasks> pager, @Param("myApplyTasks") MyApplyTasks myApplyTasks);


    /**
     * 我的待办模糊查询-所有单据类型
     *
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyApplyTasksListExport(MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊查询-需求
     *
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyDemandOrderListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊申请-订单
     *
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyApplyOrderListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊订单收货
     *
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyPurchaseOrderListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊收货--验收
     *
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyReceiveGoodsListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

    /**
     * 我的待办模糊订单--付款
     *
     * @param myApplyTasks
     * @return
     */
    List<MyApplyTasks> searchMyPurchaseFkListExport(@Param("myApplyTasks") MyApplyTasks myApplyTasks);

}
