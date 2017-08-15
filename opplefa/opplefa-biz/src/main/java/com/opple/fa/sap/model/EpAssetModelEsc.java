package com.opple.fa.sap.model;

import lombok.Data;

@Data
public class EpAssetModelEsc { // 是否必输

	private String companycode; // 公司代码
	private String asset; // 主资产号
	private String subnumber; // 资产次级编号

}
