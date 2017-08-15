package com.opple.fa.sap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BapiAccountgl extends SapDataEntity<BapiAccountgl> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8154905225902898906L;
	private Long prId;
	private String costCenter;
	private String glAccount;
	private String vendorNum;
	private String compCode;
	private String pstngDate;
	private String itemText;
	private String allocNmbr;
	private String itemnoAcc;
	private String profitctr;
	private String busArea;
	
}
