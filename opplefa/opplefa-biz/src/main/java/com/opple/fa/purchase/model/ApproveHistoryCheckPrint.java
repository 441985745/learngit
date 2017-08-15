package com.opple.fa.purchase.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhangshulong on 2017/4/28.
 */
@Data
public class ApproveHistoryCheckPrint {
    private String cuserrole; //角色
    private String cuser; //审批人姓名
    private Date ddate; //审批日期
    private String cstate; //审批结果
    private String csyslog; //审批意见
}
