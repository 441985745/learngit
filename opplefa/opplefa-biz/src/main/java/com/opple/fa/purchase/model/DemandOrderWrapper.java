package com.opple.fa.purchase.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.opple.fa.purchase.entity.DemandOrder;
import com.opple.fa.purchase.entity.DemandOrderDetail;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandOrderWrapper {
	private DemandOrder demandOrder;
	private List<DemandOrderDetail> demandOrderDetails;
}
