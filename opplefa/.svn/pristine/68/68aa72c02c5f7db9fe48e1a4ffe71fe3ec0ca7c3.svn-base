package com.opple.fa.purchase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.model.DemandOrderModel;
import com.opple.util.Pager;

public interface DemandOrderDAO {

	/**
	 * 分页查询采购需求订单
	 * @param pager
	 * @param demandOrder
	 * @return
	 */
	List<DemandOrder> searchDemandOrder(@Param("pager") Pager pager, @Param("demandOrderModel") DemandOrderModel demandOrderModel);
	
	/**
	 * 查询采购需求订单总数量
	 * @param demandOrder
	 * @return
	 */
	Long searchDemandOrderCount(@Param("demandOrderModel") DemandOrderModel demandOrderModel);
	
	/**
	 * 根据id获得采购需求订单
	 * @param id
	 * @return
	 */
	DemandOrder searchDemandOrderByOrderId(@Param("demandOrderModel") DemandOrder demandOrder);
	
	/**
	 * 根据id获得采购需求订单
	 * @param demandOrderModel
	 * @return
	 */
	DemandOrderModel searchDemandOrderModelByOrderId(@Param("demandOrderModel") DemandOrderModel demandOrderModel);
	
	/**
	 * 保存订单
	 * @param demandOrderModel
	 * @return
	 */
	void save(DemandOrder demandOrder);
	
	/**
	 * 保存修改订单
	 * @param demandOrderModel
	 * @return
	 */
	void update(DemandOrder demandOrder);
	
	/**
	 * 保存修改订单(审批)
	 * @param demandOrderModel
	 * @return
	 */
	void updateExamine(DemandOrder demandOrder);
	
	/**
	 * 查询下个序列值
	 * @return
	 */
	int searchNextSequence();
	
	/**
	 * 导出
	 * @param demandOrderModel
	 * @return
	 */
	List<DemandOrder> exportDemandOrder(@Param("demandOrderModel") DemandOrderModel demandOrderModel);
	
	/**
	 * 根据Cdocument删除订单
	 * @param demandOrder
	 */
	void delDemandOrderByCdocument(DemandOrder demandOrder);
	
	/**
	 * 根据需求订单查询归口部门及经理
	 * @param demandOrder
	 * @return
	 */
	DemandOrder searchAttachDepartByOrder(DemandOrder demandOrder);
	
	/**
	 * 修改订单状态
	 * @param demandOrder
	 */
	void updateOrderStatus(DemandOrder demandOrder);
	
	/**
	 * 弃审时查询采购申请使用需求单的数量 
	 * @param demandOrderModel
	 * @return
	 */
	Long searchNextApplyCount(DemandOrder demandOrder);
	
	/**
	 * 根据申请单号查询该单据对应的所有需求单号
	 * @param applyOrder
	 * @return
	 */
	List<DemandOrder> searchDemandByApplyId(ApplyOrder applyOrder);

	/**
	 * 根据需求单查询该单据存在未下申请单的数量
	 * @param demandOrder
	 * @return
	 */
	Long searchNotApplyCountByDemandId(DemandOrder demandOrder);
	
	/**
	 * 查询并且关闭未申请数量全部为0的订单
	 * @param demandOrder
	 */
	void updateSearchCloseDemand(DemandOrder demandOrder);
    /**
     * 修改打印状态'Y'
     * @param demandOrder
     */
	void updatePrint(DemandOrder demandOrder);

	void saveDelete(DemandOrder dod1);
}

