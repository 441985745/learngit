package com.opple.fa.config.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Wangkaixuan on 2017/4/11.
 */
@Data
public class DeptCostCenter {
    private Long id;
    private String deptCode; //预算体编码
    private String deptName; //预算体名称
    private String costCenterCode; //成本中心编码
    private String costCenterName; //成本中心名称
    private String status; //数据状态“Y\N”
    private String createByCode; //创建人编码
    private String createBy; //创建人名称
    private Date createDate; //创建时间
    private String updateByCode; //最后维护人编码
    private String updateBy; //最后维护人名称
    private Date lastDate; //最后维护时间
    private String memo; //备注
    private String companyCode; //所属公司编码
    private String companyName; //所属公司名称

    private String updateDateBegin;    // 更新时间开始区间
    private String updateDateEnd;    // 更新时间结束区间
    // public Integer rowNum;    //行号，导入时用

}
