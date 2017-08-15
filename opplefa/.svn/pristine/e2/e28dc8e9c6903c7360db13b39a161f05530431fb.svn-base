package com.opple.fa.purchase.service.impl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.purchase.dao.DemandOrderDetailDAO;
import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.DemandOrderDetail;
import com.opple.fa.purchase.model.DemandOrderDetailModel;
import com.opple.fa.purchase.service.DemandOrderDetailService;

public class DemandOrderDetailServiceImpl implements DemandOrderDetailService {
	@Getter @Setter
	private DemandOrderDetailDAO demandOrderDetailDAO;

	@Override
	public List<DemandOrderDetailModel> searchDemandOrderDetailByOrderId(DemandOrder demandOrder) {
		List<DemandOrderDetailModel> demandOrderDetailModels = demandOrderDetailDAO.searchDemandOrderDetailByOrderId(demandOrder);
		List<DemandOrderDetailModel> sumDemandOrderDetailModels = demandOrderDetailDAO.searchDemandOrderDetailSumReferencePrice(demandOrder);
		
		if (demandOrder != null && "N".equals(demandOrder.getDraft())) {
			if (demandOrderDetailModels.size() == 0 || sumDemandOrderDetailModels.size() == 0) {
				return demandOrderDetailModels;
			}
			for (int i = 0; i < demandOrderDetailModels.size(); i++) {
				for (int j = 0; j < sumDemandOrderDetailModels.size(); j++) {
					if (demandOrderDetailModels.get(i).getProjectCode().equals(sumDemandOrderDetailModels.get(j).getProjectCode())) {
						demandOrderDetailModels.get(i).setAvaliableSumMoney(demandOrderDetailModels.get(i).getAvaliableSumMoney().add(sumDemandOrderDetailModels.get(j).getAvaliableSumMoney()));
					}
				}
			}
		}
		return demandOrderDetailModels;
	}

	@Override
	public List<DemandOrderDetail> searchDemandOrderDetail(DemandOrderDetailModel demandOrderDetailModel) {
		
		return demandOrderDetailDAO.searchDemandOrderDetail(demandOrderDetailModel);
	}

	@Override
	public String searchDemandOrderIdByDetailId(Long id) {
		return demandOrderDetailDAO.searchDemandOrderIdByDetailId(id);
	}

}
