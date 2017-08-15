package com.opple.fa.sap.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class AssetRetireMain extends SapDataEntity<AssetRetireMain> { /**
	 * 
	 */
	private static final long serialVersionUID = 7577118751887375970L;
/**
	 * 
	 */
	private Long id;
	private String compCode; //公司代码
	private String docno; //凭证号
	
}
