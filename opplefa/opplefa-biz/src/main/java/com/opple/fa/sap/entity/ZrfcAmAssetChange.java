package com.opple.fa.sap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ZrfcAmAssetChange extends SapDataEntity<ZrfcAmAssetChange> { /**
	 * 
	 */
	private static final long serialVersionUID = -16618199316687076L;

	private String epSubrc; // 0成功，其它失败
	private String ipAsset;	//主资产编号
	private String subnumber;	//资产次级编号

}
