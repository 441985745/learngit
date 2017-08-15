package com.opple.fa.sap.dao;

import java.util.List;

import com.opple.fa.sap.entity.ReceiveGoodsMain;

public interface ReceiveGoodsMainSapDAO {

	/**
	 * 保存收货传入参数头和返回结果信息
	 * @param receiveGoodsMain
	 * @return
	 */
	void save(ReceiveGoodsMain receiveGoodsMain);
	/**根据采购凭证号和公司查询所有已经存在的收货凭证号
	 * @param itZmm0011Model
	 * @return
	 */
	List<ReceiveGoodsMain> selectExists(ReceiveGoodsMain receiveGoodsMain);
}
