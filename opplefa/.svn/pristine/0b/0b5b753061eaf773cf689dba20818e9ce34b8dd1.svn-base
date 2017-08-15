package com.opple.fa.report.dao;

import com.opple.fa.report.entity.FullProcess;
import com.opple.fa.report.entity.FullProcessDetail;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Wangkaixuan on 2017/5/5.
 */
public interface FullProcessDAO {
    /**
     * 收货全流程
     *
     * @param pager
     * @param fullProcess
     * @return
     */
    List<FullProcess> searchReceiveFullProcess(@Param("pager") Pager<FullProcess> pager, @Param("fullProcess") FullProcess fullProcess);

    Long searchReceiveFullProcessCount(@Param("fullProcess") FullProcess fullProcess);

    /**
     * 收货导出
     *
     * @param fullProcess
     * @return
     */
    List<FullProcess> exportReceiveFullProcess(@Param("fullProcess") FullProcess fullProcess);

    /**
     * 付款全流程
     *
     * @param pager
     * @param fullProcess
     * @return
     */
    List<FullProcess> searchPaymentFullProcess(@Param("pager") Pager<FullProcess> pager, @Param("fullProcess") FullProcess fullProcess);

    Long searchPaymentFullProcessCount(@Param("fullProcess") FullProcess fullProcess);

    /**
     * 付款导出
     *
     * @param fullProcess
     * @return
     */
    List<FullProcess> exportPaymentFullProcess(@Param("fullProcess") FullProcess fullProcess);




    /**
     * 收货全流程------明细
     *
     * @param pager
     * @param fullProcessDetail
     * @return
     */
    List<FullProcessDetail> searchReceiveFullProcessDetail(@Param("pager") Pager<FullProcessDetail> pager, @Param("fullProcess") FullProcessDetail fullProcessDetail);

    Long searchReceiveFullProcessDetailCount(@Param("fullProcess") FullProcessDetail fullProcessDetail);

    /**
     * 收货导出------明细
     *
     * @param fullProcessDetail
     * @return
     */
    List<FullProcessDetail> exportReceiveFullProcessDetail(@Param("fullProcess") FullProcessDetail fullProcessDetail);

}
