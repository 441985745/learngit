package com.opple.fa.report.dao;

import com.opple.fa.report.entity.FullProcessPay;
import com.opple.fa.report.entity.FullProcessPayDetail;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FullProcessPayDao {
    int insert(FullProcessPay record);

    int insertSelective(FullProcessPay record);

    List<FullProcessPay> searchAllPayment(@Param("pager") Pager<FullProcessPay> pager, @Param("fullProcess") FullProcessPay fullProcessPay);

    Long searchAllPaymentCount(@Param("fullProcess") FullProcessPay fullProcessPay);

    List<FullProcessPay> exportAllPayment(@Param("fullProcess") FullProcessPay fullProcess);


    List<FullProcessPayDetail> searchPayDetail(@Param("pager") Pager<FullProcessPayDetail> pager, @Param("fullProcess") FullProcessPayDetail fullProcessPay);

    Long searchPayDetailCount(@Param("fullProcess") FullProcessPayDetail fullProcessPay);

    List<FullProcessPayDetail> exportPayDetail(@Param("fullProcess") FullProcessPayDetail fullProcess);

}