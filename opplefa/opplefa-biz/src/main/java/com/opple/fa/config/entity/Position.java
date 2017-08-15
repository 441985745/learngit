package com.opple.fa.config.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.opple.fa.util.MyLimit;
import com.opple.workflow.entity.WorkFlowCommon;

@Data
@EqualsAndHashCode(callSuper  =  false)
public class Position extends WorkFlowCommon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID  =  -1907430221295090265L;
	@MyLimit(description = "存放位置编码", MaxLength = 12, MinLength = 1, isNotNull = true)
	private String cpositionCode;     // 存放位置编码
	private List<String> cpositionCodes;     // 存放位置编码--删除使用
	@MyLimit(description = "存放位置名称", MaxLength = 50, MinLength = 0, isNotNull = false) 
	private String cposition;     // 存放位置
	@MyLimit(description = "办公地点编码", MaxLength = 12, MinLength = 1, isNotNull = false)
	private String workAddressCode;     // 办公地点编码
	@MyLimit(description = "办公地点名称", MaxLength = 50, MinLength = 0, isNotNull = false) 
	private String workAddress;     // 办公地点
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
	private String cstatus;     // 数据状态
	
	private String updateDateBegin;    // 更新时间开始区间
	private String updateDateEnd;    // 更新时间结束区间
	private Integer rowNum;    //行号，导入时用
	

}
