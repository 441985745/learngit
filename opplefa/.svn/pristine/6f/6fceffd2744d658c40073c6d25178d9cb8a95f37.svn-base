package com.opple.fa.purchase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.model.PaymentOrderModel;
import com.opple.util.Pager;
/**.
 *<p>@Description:
 *<p>@author zhuang
 *<p>@date 2017年1月4日 下午7:34:37
 */
public interface PaymentOrderDAO {

	/**.
	 * 分页查询采购申请订单
	 * @param pager
	 * @param PaymentOrder
	 * @return
	 */
	List<PaymentOrderModel> searchPaymentOrder(
			@Param("pager") Pager pager, 
			@Param("paymentOrderModel") PaymentOrderModel paymentOrderModel);
	/**.
	 * 查询采购申请订单总数量
	 * @param PaymentOrder
	 * @return
	 */
	Long searchPaymentOrderCount(
			@Param("paymentOrderModel") PaymentOrderModel paymentOrderModel);
	/**.
	 * 根据订单ID查询订单
	 * @param paymentOrder
	 * @return
	 */
	PaymentOrderModel searchPaymentOrderByOrderId(PaymentOrder paymentOrder);
	/**.
	 * 保存
	 * @param paymentOrder
	 * @return
	 */
	void save(PaymentOrder paymentOrder);
	
	/**
	 * 查询下个序列值
	 * @return 
	 */
	int searchNextSequence();

	/*void save(PaymentOrder paymentOrder);
	void update(PaymentOrder paymentOrder);*/
	/**
	 * 删除
	 * @param paymentOrder
	 */
	void delPaymentOrderByCdocument(PaymentOrder paymentOrder);
	
	/**
	 * 根据成本中心编码 获得利润中心编码
	 * @param costCenterCode
	 * @return
	 */
	String searchProfitCenter(@Param("costCenterCode") String costCenterCode);
	/**
	 * 根据付款单据号更新付款单
	 * @return 
	 */
	void updatePaymentOrderByDocument(PaymentOrder paymentOrder);
	/**
	 * 导出付款单据
	 * @return 
	 */
	List<PaymentOrderModel> exportPaymentOrders(@Param("paymentOrderModel") PaymentOrderModel paymentOrderModel);
	/**
	 * 根据采购订单单号查询所有已完成预付款单据
	 * @return 
	 */
	List<PaymentOrder> searchPaymentOrdersByPurchaseId(@Param("paymentOrderModel") PaymentOrderModel paymentOrderModel);
	/**
	 * 创建单据成功时更新影像状态
	 * @return 
	 */
	void updatePaymentOrderByCdocument(PaymentOrder paymentOrder);
}
