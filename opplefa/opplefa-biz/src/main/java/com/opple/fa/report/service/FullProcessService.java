package com.opple.fa.report.service;

import com.opple.fa.report.entity.FullProcess;
import com.opple.fa.report.entity.FullProcessDetail;
import com.opple.fa.report.entity.FullProcessPay;
import com.opple.fa.report.entity.FullProcessPayDetail;
import com.opple.util.Pager;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/5/5.
 */
public interface FullProcessService {
    /**
     * 收货全流程
     *
     * @param pager
     * @param fullProcess
     * @return
     */
    Pager<FullProcess> searchReceiveFullProcess(Pager<FullProcess> pager, FullProcess fullProcess);

    /**
     * 收货导出
     *
     * @param fullProcess
     * @return
     */
    List<FullProcess> exportReceiveFullProcess(FullProcess fullProcess);


    /**
     * 收货全流程--明细
     *
     * @param pager
     * @param fullProcessDetail
     * @return
     */
    Pager<FullProcessDetail> searchReceiveFullProcessDetail(Pager<FullProcessDetail> pager, FullProcessDetail fullProcessDetail);

    /**
     * 收货导出--明细
     *
     * @param fullProcessDetail
     * @return
     */
    List<FullProcessDetail> exportReceiveFullProcessDetail(FullProcessDetail fullProcessDetail);



    /**
     * 付款全流程
     *
     * @param pager
     * @param fullProcess
     * @return
     */
    Pager<FullProcessPay> searchPaymentFullProcess(Pager<FullProcessPay> pager, FullProcessPay fullProcess);

    /**
     * 付款全流程导出
     *
     * @param fullProcess
     * @return
     */
    List<FullProcessPay> exportPaymentFullProcess(FullProcessPay fullProcess);
    /**
     * 付款全流程--明细
     *
     * @param pager
     * @param fullProcess
     * @return
     */
    Pager<FullProcessPayDetail> searchPaymentFullProcessDetail(Pager<FullProcessPayDetail> pager, FullProcessPayDetail fullProcess);
    /**
     * 付款全流程导出--明细
     *
     * @param fullProcess
     * @return
     */
    List<FullProcessPayDetail> exportPayDetail(FullProcessPayDetail fullProcess);

}
