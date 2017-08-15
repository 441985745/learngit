package com.opple.fa.config.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.opple.fa.util.MyLimit;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper  =  false)
public class WorkAddress implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID  =  -7116214051276025508L;
	@MyLimit(description = "办公地点编码", MaxLength = 12, MinLength = 1, isNotNull = true)
	private String caddressCode;     // 办公地点编码
	private List<String> caddressCodes;     // 办公地点编码--
	@MyLimit(description = "办公地点名称", MaxLength = 50, MinLength = 0, isNotNull = false)
	private String caddressName;     // 办公地点
	@MyLimit(description = "创建人", MaxLength = 50, MinLength = 0, isNotNull = false)
	private String ccreateBy;     // 创建人
	private Date dcreateDate;     // 创建时间
	@MyLimit(description = "更新人", MaxLength = 50, MinLength = 0, isNotNull = false)
	private String cupdateBy;     // 更新人
	private Date dupdateDate;     // 更新时间
	@MyLimit(description = "创建人编码", MaxLength = 10, MinLength = 0, isNotNull = false)
	private String ccreateByCode;     // 创建人编码
	@MyLimit(description = "最后更新人编码", MaxLength = 10, MinLength = 0, isNotNull = false)
	private String cupdateByCode;     // 最后更新人编码
	@MyLimit(description = "备注", MaxLength = 500, MinLength = 0, isNotNull = false)
	private String cmemo;     // 备注
	@MyLimit(description = "数据状态", MaxLength = 1, MinLength = 0, isNotNull = false)
	private String cstatus;     // 数据状态,
	private String updateDateBegin; // 更新时间开始区间
	private String updateDateEnd; // 更新时间结束区间
	private Integer rowNum; // 行号，导入时用
	@MyLimit(description = "仓库收货管理员编码", MaxLength = 10, MinLength = 0, isNotNull = true)
	private String receivegoodsCode; // 仓库收货管理员编码
	@MyLimit(description = "仓库收货管理员姓名", MaxLength = 50, MinLength = 0, isNotNull = true)
	private String receivegoodsName; // 仓库收货管理员姓名
	@MyLimit(description = "仓库废品管理员编码", MaxLength = 10, MinLength = 0, isNotNull = true)
	private String retiredgoodsCode; // 仓库废品管理员编码
	@MyLimit(description = "仓库废品管理员姓名", MaxLength = 10, MinLength = 0, isNotNull = true)
	private String retiredgoodsName; // 仓库废品管理员姓名
	@MyLimit(description = "采购意见处理员编码", MaxLength = 10, MinLength = 0, isNotNull = true)
	private String purchasetipaccessCode; // 采购意见处理员编码
	@MyLimit(description = "采购意见处理员姓名", MaxLength = 50, MinLength = 0, isNotNull = true)
	private String purchasetipaccessName; // 采购意见处理员姓名
	@MyLimit(description = "采购竞价/议价员编码", MaxLength = 10, MinLength = 0, isNotNull = true)
	private String purchasebidCode; // 采购竞价/议价员编码
	@MyLimit(description = "采购竞价/议价员姓名", MaxLength = 50, MinLength = 0, isNotNull = true)
	private String purchasebidName; // 采购竞价/议价员姓名
	@MyLimit(description = "收款确认人编码", MaxLength = 50, MinLength = 0, isNotNull = true)
	private String payeeCode; // 采购竞价/议价员姓名
	@MyLimit(description = "收款确认人姓名", MaxLength = 10, MinLength = 0, isNotNull = true)
	private String payeeName; // 采购竞价/议价员编码

}
