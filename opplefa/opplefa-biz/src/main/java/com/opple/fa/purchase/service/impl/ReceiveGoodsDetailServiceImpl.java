package com.opple.fa.purchase.service.impl;

import com.opple.fa.purchase.dao.ReceiveGoodsDetailDAO;
import com.opple.fa.purchase.entity.ReceiveGoodsDetail;
import com.opple.fa.purchase.service.ReceiveGoodsDetailService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ReceiveGoodsDetailServiceImpl implements ReceiveGoodsDetailService {
	
	@Getter @Setter
	private ReceiveGoodsDetailDAO receiveGoodsDetailDAO;
	
//	查询收货单的行  
	@Override
	public List<ReceiveGoodsDetail> searchReceiveGoodsDetailByReceiveGoodsId(String receiveGoodsId) {
		return receiveGoodsDetailDAO.searchReceiveGoodsDetailByReceiveGoodsId(receiveGoodsId);
	} 
	

}