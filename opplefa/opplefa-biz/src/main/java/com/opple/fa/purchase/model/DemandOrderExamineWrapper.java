package com.opple.fa.purchase.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.opple.fa.purchase.entity.DemandOrderDetail;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandOrderExamineWrapper {
	private DemandOrderModel demandOrderModel;
	private List<DemandOrderDetail> demandOrderDetails;
	//要会写的预算
	//private List<AssetsBudget> assetsBudgets;
}
