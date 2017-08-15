package com.opple.fa.config.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.opple.domain.BaseDomain;
import com.opple.fa.util.MyLimit;
@Data
@EqualsAndHashCode(callSuper = false)

public class AssetUnit extends BaseDomain<Long> {

	private static final long serialVersionUID  =  1L;
	@MyLimit(description = "单位编码", MaxLength = 50, MinLength = 1, isNotNull = true)
	private String unitCode;     //单位名称
	private List<String> unitCodes;     // 单位编码--删除使用
	@MyLimit(description = "单位名称", MaxLength = 50, MinLength = 0, isNotNull = false) 
	private String unitName;     //单位编码
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
