package com.opple.fa.myTasks.service.impl;

import com.opple.fa.myTasks.dao.MyApplyTasksDAO;
import com.opple.fa.myTasks.entity.MyApplyTasks;
import com.opple.fa.myTasks.service.MyApplyTasksService;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MyApplyTasksServiceImpl implements MyApplyTasksService {
    @Getter
    @Setter
    private MyApplyTasksDAO myApplyTasksDAO;

    @Override
    public Pager<MyApplyTasks> searchMyApplyTasksList(
            Pager<MyApplyTasks> pager, MyApplyTasks myApplyTasks) {
        List<MyApplyTasks> auditStatisticsList = myApplyTasksDAO.searchMyApplyTasksList(pager, myApplyTasks);
        long auditStatisticsCount = myApplyTasksDAO.searchMyApplyTasksListCounts(myApplyTasks);
        return Pager.cloneFromPager(pager, auditStatisticsCount, auditStatisticsList);
    }

    @Override
    public Pager<MyApplyTasks> searchMyDemandOrderList(
            Pager<MyApplyTasks> pager, MyApplyTasks myApplyTasks) {
        List<MyApplyTasks> auditStatisticsList = myApplyTasksDAO.searchMyDemandOrderList(pager, myApplyTasks);
        long auditStatisticsCount = myApplyTasksDAO.searchMyDemandOrderListCounts(myApplyTasks);
        return Pager.cloneFromPager(pager, auditStatisticsCount, auditStatisticsList);
    }

    @Override
    public Pager<MyApplyTasks> searchMyApplyOrderList(
            Pager<MyApplyTasks> pager, MyApplyTasks myApplyTasks) {
        List<MyApplyTasks> auditStatisticsList = myApplyTasksDAO.searchMyApplyOrderList(pager, myApplyTasks);
        long auditStatisticsCount = myApplyTasksDAO.searchMyApplyOrderListCounts(myApplyTasks);
        return Pager.cloneFromPager(pager, auditStatisticsCount, auditStatisticsList);
    }

    @Override
    public Pager<MyApplyTasks> searchMyPurchaseOrderList(
            Pager<MyApplyTasks> pager, MyApplyTasks myApplyTasks) {
        List<MyApplyTasks> auditStatisticsList = myApplyTasksDAO.searchMyPurchaseOrderList(pager, myApplyTasks);
        long auditStatisticsCount = myApplyTasksDAO.searchMyPurchaseOrderListCounts(myApplyTasks);
        return Pager.cloneFromPager(pager, auditStatisticsCount, auditStatisticsList);

    }

    @Override
    public Pager<MyApplyTasks> searchMyReceiveGoodsList(
            Pager<MyApplyTasks> pager, MyApplyTasks myApplyTasks) {
        List<MyApplyTasks> auditStatisticsList = myApplyTasksDAO.searchMyReceiveGoodsList(pager, myApplyTasks);
        long auditStatisticsCount = myApplyTasksDAO.searchMyReceiveGoodsListCounts(myApplyTasks);
        return Pager.cloneFromPager(pager, auditStatisticsCount, auditStatisticsList);
    }

    @Override
    public Pager<MyApplyTasks> searchMyPurchaseFkList(
            Pager<MyApplyTasks> pager, MyApplyTasks myApplyTasks) {
        List<MyApplyTasks> auditStatisticsList = myApplyTasksDAO.searchMyPurchaseFkList(pager, myApplyTasks);
        long auditStatisticsCount = myApplyTasksDAO.searchMyPurchaseFkListCounts(myApplyTasks);
        return Pager.cloneFromPager(pager, auditStatisticsCount, auditStatisticsList);
    }

    @Override
    public List<MyApplyTasks> searchMyApplyTasksListExport(
            MyApplyTasks myApplyTasks) {
        List<MyApplyTasks> list = myApplyTasksDAO.searchMyApplyTasksListExport(myApplyTasks);
        return list;
    }

    @Override
    public List<MyApplyTasks> searchMyDemandOrderListExport(
            MyApplyTasks myApplyTasks) {

        return myApplyTasksDAO.searchMyDemandOrderListExport(myApplyTasks);
    }

    @Override
    public List<MyApplyTasks> searchMyApplyOrderListExport(
            MyApplyTasks myApplyTasks) {

        return myApplyTasksDAO.searchMyApplyOrderListExport(myApplyTasks);
    }

    @Override
    public List<MyApplyTasks> searchMyPurchaseOrderListExport(
            MyApplyTasks myApplyTasks) {

        return myApplyTasksDAO.searchMyPurchaseOrderListExport(myApplyTasks);
    }

    @Override
    public List<MyApplyTasks> searchMyReceiveGoodsListExport(
            MyApplyTasks myApplyTasks) {

        return myApplyTasksDAO.searchMyReceiveGoodsListExport(myApplyTasks);
    }

    @Override
    public List<MyApplyTasks> searchMyPurchaseFkListExport(
            MyApplyTasks myApplyTasks) {

        return myApplyTasksDAO.searchMyPurchaseFkListExport(myApplyTasks);
    }


}
