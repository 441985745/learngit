package com.opple.fa.purchase.model;

import java.util.Date;

import com.opple.fa.purchase.entity.ApplyOrderDetail;

import lombok.Data;

@Data
public class ApplyOrderDetailModel extends ApplyOrderDetail {
	
	


	//申请日期开始
	private Date applyDateStart;
	//申请日期结束
	private Date applyDateEnd;
}
