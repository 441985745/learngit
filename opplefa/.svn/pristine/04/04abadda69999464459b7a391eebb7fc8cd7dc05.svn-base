package com.opple.fa.purchase.dao;

import java.util.List;

import com.opple.fa.purchase.entity.NotPoPaymentOrderDetail;

public interface NotPoPaymentOrderDetailDAO {

	/**
     * 查询下个序列值
     *
     * @return
     */
    int searchNextSequence();
	/**.
	 * 新增非标付款行明细
	 * @param notPoPaymentOrderDetail
	 * @return
	 */
    void save(NotPoPaymentOrderDetail notPoPaymentOrderDetail);
    /**
     * 删除非标付款单明细
     *
     * @param notPoPaymentOrderDetail
     */
    void delPaymentOrderDetailById(NotPoPaymentOrderDetail notPoPaymentOrderDetail);
    /**
     * 根据非标付款单号修改付款明细
     *
     * @param notPoPaymentOrderDetail
     */
    void updatePaymentOrderDetailById(NotPoPaymentOrderDetail notPoPaymentOrderDetail);
	/**
	 * 根据非标流程主表单据号查询付款明细信息
	 * @return 
	 */
	List<NotPoPaymentOrderDetail> searchPaymentOrderByDocument(NotPoPaymentOrderDetail notPoPaymentOrderDetail);
	/**
	 * 根据非标流程明细表ID更新资产编码
	 * @return 
	 */
	void updateAssetsCodeById(NotPoPaymentOrderDetail notPoPaymentOrderDetail);
}
