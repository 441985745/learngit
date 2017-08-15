package com.opple.fa.purchase.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.DemandOrderDetail;

public class DemandOrderServiceImplTest_SaveDemand extends BasePurchaseTestCase {

	@Test
	public void testAddDemand() {

		DemandOrder demandOrder = new DemandOrder();
		List<DemandOrderDetail> demandOrderDetail = new ArrayList<DemandOrderDetail>();

		demandOrder.setCdocument("CGXQ2000112233");
		demandOrder.setApplyUser("张1");

		for (int i = 0; i < 3; i++) {
			DemandOrderDetail temp = new DemandOrderDetail();
			temp.setDemandOrderId("CGXQ2000112233");
			temp.setDemandCount(20 + i);
			temp.setAssetsName("资产" + i);
			demandOrderDetail.add(temp);
		}

//		ExecuteResult<DemandOrder> addDemand = demandOrderService.saveDemand(demandOrder, demandOrderDetail);

//		assertTrue(addDemand.getErrorMessages().size() == 0);
	}

}
