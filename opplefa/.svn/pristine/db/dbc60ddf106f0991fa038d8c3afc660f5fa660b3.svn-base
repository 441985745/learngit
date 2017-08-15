package com.opple.fa.purchase.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.opple.security.entity.FileUpload;
import com.opple.workflow.entity.WorkFlowCommon;

import lombok.Data;

@Data
public class NotPoPaymentOrder extends WorkFlowCommon {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String draft; //是否草稿，'N'不是草稿，'Y'是草稿
    //单据号
    private String cdocument;
    //申请人编码
    private String applyUserCode;
    //申请人名称
    private String applyUser;
    //申请日期
    private Date applyDate;
    //申请金额（原币）
    private BigDecimal applyMoney;
    //申请金额（本币）
    private BigDecimal applyMoneyLocal;
    //审批金额（原币）
    private BigDecimal iamoney;
    //审批金额（本币）
    private BigDecimal iamoneyLocal;
    //挂账人编码
    private String paymentPeopleCode;
    //挂账人姓名
    private String paymentPeople;
    //预计核销时间
    private Date predictTime;
    //验收单单号
    private String acceptOrderNumber;
    //公司名称
    private String companyName;
    //公司编码
    private String companyCode;
    //币种
    private String currencyCode;
    //币种名称
    private String currencyName;
    //汇率
    private BigDecimal exchangeRate;
    //税率
    private Double taxRate;
    //供应商名称
    private String supplierName;
    //供应商编码
    private String supplierCode;
    //开户行名称
    private String bankName;
    //开户行编码
    private String bankCode;
    //银行账号
    private String bankNumber;
    //联行号
    private String contactNumber;
    //成本中心
    private String costCenter;
    //成本中心编码
    private String costCenterCode;
    //公司具体名称
    private String companySpecificName;
    //国家
    private String country;
    //城市
    private String city;
    //税码
    private String taxRateCode;
    //是否打印
    private String print;
    //办公地点
    private String officeLocation;
    //所属利润中心
    private String profitCenter;
    //申请事由
    private String applyFor;
    //扫描地
    private String scanLocation;
    //是否有增值税
    private String isVat;
    //发票号
    private String invoiceNumber;
    //付款类型
    private String paymentType;
    //是否传SAP
    private String issap;
    //是否授权(查询时赋值登录人是否有授权)
    private String isAuthorize;
    //当前登录人编码
    private String loginUserCode;
    //管理员ID
    private String adminCode;
    //付款开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyDateStart;
    //付款结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyDateEnd;
    //备注
    private String remark;
    //是否创建影像
    private String isPortrait;
    //联系人
    private String contacts;
    //采购经理编码
    private String purchaseManagerCode;
    //采购经理名称
    private String purchaseManagerName;
    //当前登录人姓名
    private String loginUserName;
    // 是否费用化资产
    private String isExpenseAssets;
    //新增类型
    private String insertType;
    //是否最后一步审批
    private String isLastApprove;
    //是否传入SAP
    private String isComingSAP;
    //是否手工凭证
    private String cinputVoucher;
    //凭证号
    private String cvoucherId;
    //是否获取资产编码
    private String isGetEncoding;
    //公司（前台交互接收值用）
    private String company;
    private List<FileUpload> listFile;
    //影像状态
    private String cbarStatus;
}
