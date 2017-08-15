package com.opple.fa.sap.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class SapDataEntity<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1126255800573441813L;
	private String etLog; // 结果日志
	private String operateUser; // 登录人
	private Date operateDate; // 操作时间
}
