package com.opple.fa.purchase.dao;

import java.io.Serializable;
import java.util.List;

import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.model.ApplyDemandMappingModel;
import org.apache.ibatis.annotations.Param;

import com.opple.dao.CommonDAO;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;
import com.opple.fa.purchase.model.ApplyOrderModel;

public interface ApplyOrderDetailDAO extends CommonDAO<ApplyOrderDetail, Serializable> {

	
	/**
	 * 根据订单ID获取订单明细
	 * @param applyOrder
	 * @return
	 */
	List<ApplyOrderDetail> searchApplyOrderDetailByOrderId(@Param("applyOrder")ApplyOrder applyOrder);

	/**
	 * 保存
	 * @param applyOrderDetail
	 */
	void save(ApplyOrderDetail applyOrderDetail);

	/**
	 * 根据id删除表明细
	 * 
	 * @param applyOrderDetail
	 */
	void deleteOrderDetailByApplyId(ApplyOrderDetail applyOrderDetail);
	void saveDeleteBatch(ApplyOrderDetail list);
	
	/**
	 * 采购订单页面 选择明细  查询采购申请明细列表
	 * @param applyOrder
	 * @return
	 */
	List<ApplyOrderDetail> searchApplyOrderDetailList(ApplyOrderModel applyOrderModel);
	
	/**
	 * 回写数量
	 * @param purchaseOrderDetail
	 */
	void writeBack(PurchaseOrderDetail purchaseOrderDetail);
	/**
	 * 根据ID获取订单明细
	 * @param applyOrder
	 * @return
	 */
	ApplyOrderDetail searchApplyOrderDetailById(ApplyOrderDetail applyOrderDetail);

	/**
	 * 更新ApplyOrder
	 * @param applyOrder
	 * @param applyOrderDetail
	 */
	void updateApplyOrderDetail(@Param("applyOrder") ApplyOrder applyOrder, @Param("applyOrderDetail") ApplyOrderDetail applyOrderDetail);
	
	
	void updatePurchaseUnitPrice(@Param("applyOrderDetail") ApplyOrderDetail applyOrderDetail);

	/**
	 * 更新采购申请表下次拆分编码
	 * @param applyOrderDetail
	 */
    void updateNextSecondaryCoding(@Param("applyOrderDetail") ApplyOrderDetail applyOrderDetail);
    
    /**
     * 根据资产分类(F1200)获得最大主资产编码
     * @param applyOrder
     * @return
     */
    String searchMaxMainAssetCode(ApplyOrder applyOrder);
    
    /**
     * 修改单位
     * @param applyOrderDetail
     */
    void updateUnits(ApplyOrderDetail applyOrderDetail);

    void updatePurAvaMoney(ApplyOrderDetail aod);

	void updateApplyOrderDetailPurAvaMoney();

    DemandOrderDetail searchApplyDemandMapping(ApplyDemandMappingModel applyDemandMappingModel);
}
