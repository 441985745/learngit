package com.opple.fa.config.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.opple.fa.util.MyLimit;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper  =  false)
public class StandardComputerConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID  =  -8505891382304357271L;
	@MyLimit(description = "资产编码", MaxLength = 20, MinLength = 1, isNotNull = true) 
	private String assetsCode;    // 资产编码
	@MyLimit(description = "资产名称", MaxLength = 100, MinLength = 0, isNotNull = false) 
	private String assetsName;    // 资产名称
	@MyLimit(description = "规格参数", MaxLength = 200, MinLength = 0, isNotNull = false) 
	private String specificationParameter;    // 规格参数
	private List<String> assetsCodes;    // 资产编码--删除使用
	@MyLimit(description = "创建人", MaxLength = 30, MinLength = 0, isNotNull = false)
	private String createBy;    // 创建人
	@MyLimit(description = "创建人编码", MaxLength = 10, MinLength = 0, isNotNull = false)
	private String createByCode;    // 创建人编码
	@MyLimit(description = "更新人", MaxLength = 30, MinLength = 0, isNotNull = false)
	private String updateBy;    // 最后更新人
	@MyLimit(description = "最后更新人编码", MaxLength = 10, MinLength = 0, isNotNull = false)
	private String updateByCode;    // 最后更新人编码
	@MyLimit(description = "备注", MaxLength = 500, MinLength = 0, isNotNull = false)
	private String cmemo;    // 备注
	@MyLimit(description = "数据状态", MaxLength = 1, MinLength = 0, isNotNull = false)
	private String status;    // 数据状态, 
	private Date createDate;    // 创建时间
	private Date updateDate;    // 更新时间
	
	private String updateDateBegin;    // 更新时间开始区间
	private String updateDateEnd;    // 更新时间结束区间
	private Integer rowNum;    //行号，导入时用

}

