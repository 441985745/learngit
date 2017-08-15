package com.opple.fa.purchase.model;


import java.util.List;

import com.opple.fa.purchase.entity.CheckMachine;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.opple.fa.purchase.entity.CheckAcceptMapping;
import com.opple.fa.purchase.entity.CheckAcceptance;
import com.opple.fa.purchase.entity.CheckAcceptanceDetail;

import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CheckAcceptanceWrapper {
	private  CheckAcceptance checkAcceptance; 
	private List<CheckAcceptanceDetail> checkAcceptanceDetails;
	private List<CheckAcceptMapping> checkAcceptMappings;
	private CheckMachine checkMachine;
}
