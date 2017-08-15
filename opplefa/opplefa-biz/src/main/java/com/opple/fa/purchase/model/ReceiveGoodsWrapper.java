package com.opple.fa.purchase.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.opple.fa.purchase.entity.PurchaseCommon;
import com.opple.fa.purchase.entity.ReceiveGoods;
import com.opple.fa.purchase.entity.ReceiveGoodsDetail;
import com.opple.fa.purchase.entity.ReceiveGoodsMapping;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceiveGoodsWrapper {
	private ReceiveGoods receiveGoods;
	private List<ReceiveGoodsDetail> receiveGoodsDetails;
	private List<ReceiveGoodsMapping> receiveGoodsMappings;
	private List<PurchaseCommon> purchaseCommons;
}
