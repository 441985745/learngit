package com.opple.fa.sap.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class Eaddressdata extends SapDataEntity<Eaddressdata> { // 是否必输

	/**
	 * 
	 */
	private static final long serialVersionUID = -4190137692414711894L;
	private String acc1time; // 指示符:科目是一次性科目吗? 是-X
	private String name; // 名称-供应商名称
	private String city; // 城市
	private String langu; // 语言代码  CH或1
	private String country; // 国家
}
