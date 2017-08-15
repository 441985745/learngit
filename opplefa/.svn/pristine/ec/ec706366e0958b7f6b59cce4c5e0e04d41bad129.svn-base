package com.opple.fa.purchase.dao;

import com.opple.fa.purchase.entity.CheckAcceptanceDetail;

import java.util.List;


/**
 *  *<p>@Description: 验收表（收货单保存时传递的数据，已收货未验收的）
 *  *<p>@author lipengju
 *  *<p>@date 2016年11月8日 下午5:01:36
 */
public interface CheckAcceptanceDetailDAO {

    /**
     * 保存 验收行
     *
     * @param checkAcceptanceDetail
     * @return
     */
    Long saveCheckAcceptanceDetail(CheckAcceptanceDetail checkAcceptanceDetail);

    /**
     * 查询验收单的行
     *
     * @param checkAccpetId
     * @return
     */
    List<CheckAcceptanceDetail> getCheckAcceptanceDetailByDocument(String checkAccpetId);

    /**
     * (修改 先删除)根据验收单号
     *
     * @param checkAcceptId
     */
    void delCheckAcceptanceByCheckAcceptId(String checkAcceptId);

    Long updateCheckAcceptanceDetailSapById(CheckAcceptanceDetail checkAcceptanceDetail);

    CheckAcceptanceDetail getCheckAcceptanceDetailById(Long id);

    void updateCheckAcceptanceDetailById(CheckAcceptanceDetail cad);

    void updateAssetsBudget(CheckAcceptanceDetail cad);

    void saveDeleteBranch(List<CheckAcceptanceDetail> checkAcceptanceDetailList);
}
