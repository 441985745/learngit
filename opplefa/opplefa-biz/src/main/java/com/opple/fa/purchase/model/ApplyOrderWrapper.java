package com.opple.fa.purchase.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.opple.fa.purchase.entity.ApplyDemandMapping;
import com.opple.fa.purchase.entity.ApplyOrder;
import com.opple.fa.purchase.entity.ApplyOrderDetail;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplyOrderWrapper {
	private ApplyOrder applyOrder;
	private List<ApplyOrderDetail> applyOrderDetails;
	private List<ApplyDemandMapping> applyDemandMappings;

}
