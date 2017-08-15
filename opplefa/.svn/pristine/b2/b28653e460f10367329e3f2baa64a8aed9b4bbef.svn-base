package com.opple.fa.purchase.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.opple.fa.purchase.entity.PaymentTerm;
import com.opple.fa.purchase.entity.PurchaseApplyMapping;
import com.opple.fa.purchase.entity.PurchaseOrder;
import com.opple.fa.purchase.entity.PurchaseOrderDetail;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseOrderWrapper {
	private PurchaseOrder purchaseOrder;
	private List<PurchaseOrderDetail> purchaseOrderDetails;
	private List<PurchaseApplyMapping> purchaseApplyMappings;
	private List<PaymentTerm> paymentTerms;
}
