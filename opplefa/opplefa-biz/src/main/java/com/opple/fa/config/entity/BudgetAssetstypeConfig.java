package com.opple.fa.config.entity;

import java.util.Date;
import java.util.List;

import com.opple.fa.util.MyLimit;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BudgetAssetstypeConfig {
	//主键
	private String id;
	@MyLimit(description = "预算资产类型", MaxLength = 100, MinLength = 1, isNotNull = true) 
	private String budgetAssetsType;
	@MyLimit(description = "资产类型", MaxLength = 100, MinLength = 1, isNotNull = true) 
	private String assetsType;
	
	private List<String> ids;     // 单位编码 =  = ids
	@MyLimit(description = "创建人", MaxLength = 50, MinLength = 0, isNotNull = false) 
	private String createBy;     // 创建人
	private Date createDate;     // 创建时间
	@MyLimit(description = "更新人", MaxLength = 50, MinLength = 0, isNotNull = false) 
	private String updateBy;     // 更新人
	private Date updateDate;     // 更新时间
	@MyLimit(description = "创建人编码", MaxLength = 10, MinLength = 0, isNotNull = false) 
	private String createByCode;     // 创建人编码
	@MyLimit(description = "最后更新人编码", MaxLength = 10, MinLength = 0, isNotNull = false) 
	private String updateByCode;     // 最后更新人编码
	@MyLimit(description = "备注", MaxLength = 500, MinLength = 0, isNotNull = false) 
	private String memo;     // 备注
	@MyLimit(description = "数据状态", MaxLength = 1, MinLength = 0, isNotNull = false) 
	private String status;     // 数据状态
	
	private String updateDateBegin;    // 更新时间开始区间
	private String updateDateEnd;    // 更新时间结束区间
	private Integer rowNum;    //行号，导入时用
}
