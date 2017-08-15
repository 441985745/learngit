package com.opple.fa.report.service.impl;

import com.opple.fa.report.dao.FullProcessDAO;
import com.opple.fa.report.dao.FullProcessPayDao;
import com.opple.fa.report.entity.FullProcess;
import com.opple.fa.report.entity.FullProcessDetail;
import com.opple.fa.report.entity.FullProcessPay;
import com.opple.fa.report.entity.FullProcessPayDetail;
import com.opple.fa.report.service.FullProcessService;
import com.opple.util.Pager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/5/5.
 */
public class FullProcessServiceImpl implements FullProcessService {

    @Getter
    @Setter
    private FullProcessDAO fullProcessDAO;
    @Getter
    @Setter
    private FullProcessPayDao fullProcessPayDao;

    @Override
    public Pager<FullProcess> searchReceiveFullProcess(Pager<FullProcess> pager, FullProcess fullProcess) {
        List<FullProcess> fullProcesses = fullProcessDAO.searchReceiveFullProcess(pager, fullProcess);
        Long fullProcessesCount = fullProcessDAO.searchReceiveFullProcessCount(fullProcess);
        return Pager.cloneFromPager(pager, fullProcessesCount, fullProcesses);
    }

    @Override
    public List<FullProcess> exportReceiveFullProcess(FullProcess fullProcess) {
        return fullProcessDAO.exportReceiveFullProcess(fullProcess);
    }

    @Override
    public Pager<FullProcessDetail> searchReceiveFullProcessDetail(Pager<FullProcessDetail> pager, FullProcessDetail fullProcessDetail) {
        List<FullProcessDetail> fullProcesses = fullProcessDAO.searchReceiveFullProcessDetail(pager, fullProcessDetail);
        Long fullProcessesCount = fullProcessDAO.searchReceiveFullProcessDetailCount(fullProcessDetail);
        return Pager.cloneFromPager(pager, fullProcessesCount, fullProcesses);
    }

    @Override
    public List<FullProcessDetail> exportReceiveFullProcessDetail(FullProcessDetail fullProcessDetail) {
        return fullProcessDAO.exportReceiveFullProcessDetail(fullProcessDetail);
    }

    @Override
    public Pager<FullProcessPay> searchPaymentFullProcess(Pager<FullProcessPay> pager, FullProcessPay fullProcess) {
        List<FullProcessPay> fullProcesses = fullProcessPayDao.searchAllPayment(pager, fullProcess);
        Long fullProcessesCount = fullProcessPayDao.searchAllPaymentCount(fullProcess);
        return Pager.cloneFromPager(pager, fullProcessesCount, fullProcesses);
    }

    @Override
    public List<FullProcessPay> exportPaymentFullProcess(FullProcessPay fullProcess) {
        return fullProcessPayDao.exportAllPayment(fullProcess);
    }

    @Override
    public Pager<FullProcessPayDetail> searchPaymentFullProcessDetail(Pager<FullProcessPayDetail> pager, FullProcessPayDetail fullProcess) {
        List<FullProcessPayDetail> fullProcesses = fullProcessPayDao.searchPayDetail(pager, fullProcess);
        Long fullProcessesCount = fullProcessPayDao.searchPayDetailCount(fullProcess);
        return Pager.cloneFromPager(pager, fullProcessesCount, fullProcesses);
    }

    @Override
    public List<FullProcessPayDetail> exportPayDetail(FullProcessPayDetail fullProcess) {
        return fullProcessPayDao.exportPayDetail(fullProcess);
    }


}
