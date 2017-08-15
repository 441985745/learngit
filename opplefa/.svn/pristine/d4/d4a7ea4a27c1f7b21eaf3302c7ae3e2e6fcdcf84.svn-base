package com.opple.fa.purchase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.model.DemandOrderDetailModel;

public interface DemandOrderDetailDAO {

	/**
	 * 根据订单ID获取订单明细
	 * 
	 * @param demandOrder
	 * @return
	 */
	List<DemandOrderDetailModel> searchDemandOrderDetailByOrderId(@Param("demandOrder") DemandOrder demandOrder);

	/**
	 * 保存
	 * 
	 * @param demandOrderDetail
	 */
	void save(DemandOrderDetail demandOrderDetail);
	/**
	 * 修改保存
	 * 
	 * @param demandOrderDetail
	 */
	void updateExamine(DemandOrderDetail demandOrderDetail);
	/**
	 * 模糊查询
	 * 
	 * @param demandOrderDetailModel
	 * @return
	 */
	List<DemandOrderDetail> searchDemandOrderDetail(DemandOrderDetailModel demandOrderDetailModel);

	/**
	 * 保存采购申请订单时 回写更新采购需求订单的 申请数量和未申请数量
	 * 
	 * @param demandOrderDetail
	 * 需要一个采购需求明细的ID 和一个数量
	 */
	void writeBack(DemandOrderDetail demandOrderDetail);

	/**
	 * 根据id删除表明细
	 * 
	 * @param demandOrderDetail
	 */
	void deleteOrderDetailById(DemandOrderDetail demandOrderDetail);
	
	
	/**
	 * 根据ID获取表明细
	 * 
	 * @param demandOrderDetail
	 * @return
	 */
	DemandOrderDetailModel searchDemandOrderDetailById(DemandOrderDetail demandOrderDetail);

	/**
	 * 
	 * @param demandOrder
	 * @return
	 */
	List<DemandOrderDetailModel> searchDemandOrderDetailSumReferencePrice(DemandOrder demandOrder);

    void updateExamine1(DemandOrderDetail demandOrderDetail);

    String searchDemandOrderIdByDetailId(Long id);

    void saveDelete(DemandOrderDetail dod1);
}
