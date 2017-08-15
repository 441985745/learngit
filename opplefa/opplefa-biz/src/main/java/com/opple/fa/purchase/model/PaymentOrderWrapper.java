package com.opple.fa.purchase.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.opple.fa.purchase.entity.PaymentOrder;
import com.opple.fa.purchase.entity.PaymentOrderDetail;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentOrderWrapper {
	private PaymentOrder paymentOrder;
	private List<PaymentOrderDetail> paymentOrderDetails;
	private List<PaymentPaymentMappingModel> paymentPaymentMappingModels;
}
