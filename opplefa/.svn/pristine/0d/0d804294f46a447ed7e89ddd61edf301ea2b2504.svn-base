package com.opple.fa.purchase.dao;

import java.util.List;

import com.opple.fa.purchase.entity.NotPoPaymentOrderDetail;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.NotPoPaymentOrder;
import com.opple.util.Pager;

public interface NotPoPaymentOrderDAO {

	/**.
	 * 分页查询非PO付款单
	 * @param pager
	 * @param notPoPaymentOrder
	 * @return
	 */
	List<NotPoPaymentOrder> searchPaymentOrder(
			@Param("pager") Pager pager, 
			@Param("notPoPaymentOrder") NotPoPaymentOrder notPoPaymentOrder);
	/**.
	 * 查询非PO付款单总数量
	 * @param notPoPaymentOrder
	 * @return
	 */
	Long searchPaymentOrderCount(
			@Param("notPoPaymentOrder") NotPoPaymentOrder notPoPaymentOrder);
	/**.
	 * 根据订单ID查询订单
	 * @param notPoPaymentOrder
	 * @return
	 */
	NotPoPaymentOrder searchPaymentOrderByOrderId(NotPoPaymentOrder notPoPaymentOrder);
	/**.
	 * 保存
	 * @param notPoPaymentOrder
	 * @return
	 */
	void save(NotPoPaymentOrder notPoPaymentOrder);
	/**
	 * 根据付款单据号更新凭证号
	 * @param notPoPaymentOrder
	 * @return 
	 */
	void updatePaymentOrderByDocument(NotPoPaymentOrder notPoPaymentOrder);
	/**
	 * 导出非PO付款
	 * @param notPoPaymentOrder
	 * @return 
	 */
	List<NotPoPaymentOrder> exportNotPoPaymentOrders(@Param("notPoPaymentOrder") NotPoPaymentOrder notPoPaymentOrder);
	/**
	 * 根据成本中心编码 获得利润中心编码
	 * @param costCenterCode
	 * @return
	 */
	String searchProfitCenter(@Param("costCenterCode") String costCenterCode);
	/**
	 * 删除
	 * @param notPoPaymentOrder
	 */
	void delPaymentOrderByCdocument(NotPoPaymentOrder notPoPaymentOrder);
	/**
	 * 创建单据成功时更新影像状态
	 * @return 
	 */
	void updatePaymentOrderByCdocument(NotPoPaymentOrder notPoPaymentOrder);

    void deleteNoPoPaymentOrder(@Param("notPoPaymentOrder") NotPoPaymentOrder notPoPaymentOrder);

	List<NotPoPaymentOrderDetail> searchNotPoPaymentByOrderId(@Param("notPoPaymentOrder") NotPoPaymentOrder notPoPaymentOrder);

    void updateNotPoPaymentOrder(NotPoPaymentOrder notPoPaymentOrder);
    /**
	 * 调用存贮过程更新资产主编吗
	 * @param notPoPaymentOrder
	 * @return
 	 */
	void getMainAssetCodePro(NotPoPaymentOrder notPoPaymentOrder);
	/**
	 * 根据付款单号更新是否生成资产编码
	 * @param notPoPaymentOrder
	 * @return
 	 */
	void updateIsGetCodeByDocument(NotPoPaymentOrder notPoPaymentOrder);
	/**
	 * 根据当前登录人查询申请人为此人的所有已完成的验收单
	 * @param notPoPaymentOrder
	 * @return 
	 */
	List<NotPoPaymentOrder> searchPoPaymentOrders(NotPoPaymentOrder notPoPaymentOrder);
	/**
	 * 根据付款单号查询剩余尾款金额
	 * @param notPoPaymentOrder
	 * @return 
	 */
	String searchSumPriceByCdocument(NotPoPaymentOrder notPoPaymentOrder);
	/**
	 * 手工凭证存储凭证号
	 * @param notPoPaymentOrder
	 * @return 
	 */
	void updateCvoucherId(NotPoPaymentOrder notPoPaymentOrder);
}
