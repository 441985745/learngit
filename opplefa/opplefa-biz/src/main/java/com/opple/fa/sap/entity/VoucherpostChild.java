package com.opple.fa.sap.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VoucherpostChild extends SapDataEntity<VoucherpostChild> {
    /**
     *
     */
    private static final long serialVersionUID = -7048602431861365871L;

    private String ckey; // 主键
    private String cdocuments; // 核销流程单号
    private String cadvdocuments; // 预制凭证号
    private String ctype; // 凭证类型
    private String crowitemno; // 行项目序号
    private String cdocumentsgroup; // 凭证组号
    private String csubjectcode; // 科目编码
    private String csubjectname; // 科目名称
    private String cdebitcreditpath; // 借贷方向：借、贷
    private String caccountcode; // 记账码
    private String cspcode; // 特别总账标示
    private String crowitemtext; // 行项目文本
    private String csuppliercode; // 供应商编码
    private String csuppliername; // 供应商名称
    private String ccustomercode; // 客户编码
    private String ccustomername; // 客户名称
    private String cusercode; // 人员编码
    private String cusername; // 人员名称
    private String cdepcode; // 部门编码(成本中心)
    private String cdepname; // 部门名称(成本中心)
    private String cpassdocuments; // 过账凭证号
    private String cclearvouchcode; // 清帐（付款）凭证号
    private String cordercode; // 内部订单编码
    private String cordername; // 内部订单名称
    private String csettiementcode; // 结算方式编码
    private String csettiementname; // 结算方式名称
    private String compCode; // 记账公司码
    private Date pstngDate; // 记账日期
    private String cstatus; // 数据状态
    private String cdocumentsno; // 凭证序号
    private String iyear; // 会计年度
    private Date drepayment; // 个人借支还款日期
    private String bqingzhang; // 是否需要清帐:0不清账，1清账，2不清账
    private String bautoqz; // 清帐自动生成行
    private String cbrnch; // 分支号，费用申请在今年之前的标记x，用于预提-王建
    private String ccurrencycode; // 币种编码
    private String ccurrencyname; // 币种名称
    private BigDecimal iexchangerate; // 汇率
    private BigDecimal iamount; // 金额 （本币）-- 防止修改程序，此处本币与原币金额一致
    private BigDecimal iamountyb; // 金额 （原币）
    private String cprofitcenter; // 利润中心-手工凭证使用
    private String cisbefore; // 是否事前借支单（Y：是，N：否）
    private String creasoncode; // 原因代码
    private String bflag; // 标识是否一次性供应商、客户（一次性供应商、客户过账时需要填写姓名、城市、国家时使用）
    private String ctranstype; // 事务类型

}
