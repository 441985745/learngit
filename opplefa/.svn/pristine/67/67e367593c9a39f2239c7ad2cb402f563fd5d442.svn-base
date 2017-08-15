package com.opple.fa.purchase.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.opple.fa.purchase.entity.NotPoPaymentOrder;
import com.opple.fa.purchase.entity.NotPoPaymentOrderDetail;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotPaymentOrderWrapper {
    private NotPoPaymentOrder notPoPaymentOrder;
    private List<NotPoPaymentOrderDetail> notPoPaymentOrderDetails;
    private List<PaymentPaymentMappingModel> paymentPaymentMappingModels;
}
