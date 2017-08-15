package com.opple.fa.discardsell.service.impl;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.opple.fa.discardsell.dao.DiscardSellDAO;
import com.opple.fa.discardsell.dao.DiscardSellDetailDAO;
import com.opple.fa.discardsell.entity.DiscardSell;
import com.opple.fa.discardsell.entity.DiscardSellDetail;
import com.opple.fa.discardsell.service.DiscardSellDetailService;

public class DiscardSellDetailServiceImpl implements DiscardSellDetailService {
	@Setter
	@Getter
	private DiscardSellDetailDAO discardSellDetailDAO;
	@Setter
	@Getter
	private DiscardSellDAO discardSellDAO;

	@Override
	public List<DiscardSellDetail> serachDiscardSellDetailByAssetId(
			DiscardSellDetail discardSellDetail) {

		return discardSellDetailDAO
				.serachDiscardSellDetailByAssetId(discardSellDetail);
	}

	@Override
	public DiscardSellDetail getDiscardSellDetailByAssetId(
			DiscardSellDetail discardSellDetail) {

		return discardSellDetailDAO
				.getDiscardSellDetailByAssetId(discardSellDetail);
	}

	@Override
	public List<DiscardSellDetail> serachDiscardSellDetailBydiscardSellId(
			DiscardSellDetail discardSellDetail) {

		return discardSellDetailDAO
				.serachDiscardSellDetailBydiscardSellId(discardSellDetail);
	}

	@Override
	public void updateScrapCertificate(DiscardSellDetail discardSellDetail) {
		discardSellDetailDAO.updateScrapCertificate(discardSellDetail);
		DiscardSell discardSell = new DiscardSell();
		discardSell.setId(discardSellDetail.getDiscardSellId());
		discardSellDAO.updateIsSap(discardSell);
	}

}
