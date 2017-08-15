package com.opple.fa.sap.dao;

import org.apache.ibatis.annotations.Param;

import com.opple.fa.sap.entity.ZrfcMmMb1a1;

public interface ReceiveGoodsSapDAO {

	/**
	 * 保存订单结果
	 * @param applyOrderModel
	 * @return
	 */
	void save(@Param("zrfcMmMb1a1")ZrfcMmMb1a1 zrfcMmMb1a1);
}
