package com.opple.fa.discardsell.entity;

import com.opple.fa.assetcard.entity.GeneralAsset;
import com.opple.security.entity.FileUpload;
import com.opple.workflow.entity.WorkFlowCommon;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class DiscardSell extends WorkFlowCommon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String isExpenseAssets; // 是否费用化资产

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate; // 创建时间
	private Date updateDate; // 修改时间
	private String status; // 删除标识
	private String applyPeople; // useName申请人不在使用applyPeople

	private String adscriptionDepartment; // 归属部门
	private String assetName;
	private String assetCode;
	private String subordinateDepartment; // 资产所属部门
	private String subordinateDepartmentCode; // 资产所属部们编码
	private String adscriptionCompany; // 资产归属公司
	private String adscriptionCompanyCode; // 资产所属公司编码
	private String costCenterCode; // 资产所属成本中心编码
	private String costCenterName; // 资产所属成本中心名称
	private String officeLocation; // 办公地点
	private String persentDepartment; // 成本中心

	private BigDecimal assetOriginalValueSum; // 原值合计
	private BigDecimal assetNetValueSum; // 净值合计
	private BigDecimal discardMoneySum; // 报废金额合计
	private String assetType; // 资产类型
	private String accountingYear; // 会计年度
	private String commitType; // 是否为草稿
	private String platform; // 平台
	private String workshopType; // 车间类型
	private String application; //申请原因
	private double originalValueTotal;
	private double netValueTotal;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start;  // 临时数据不在数据库 投诉开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end;  // 临时数据不在数据库 投诉结束时间
	private String generalAssetIds; // 资产信息的id
	private List<DiscardSellDetail> listDiscardSellDetail; // 子表 信息
	private List<GeneralAsset> listGneralAssets; // 资产数组
	private List<FileUpload> listFile;
	private String discardOrSell; // 报废或变卖的标志 0 变卖 1 报废 采购经理
	private String discardType; // 报废类型 归口
	private String discardTypeFinance; // 报废类型 平台财务填写

	private String judgmentBasis; // 判断依据
	private String purchasingJudgmentBasis; // 采购经理判断依据
	private String actualDisposalPrice; // 实际处置价值
	private String sellPattern; // 变卖模式
	private String supplierName; // 供应商名称
	private String sellValue; // 变卖价值
	private String remark; // 备注
	private String moneyList; // 报废金额集合
	private String precentList; // 报废百分比集合

	private String basePrice; // 标底价
	private String putmanageIdea; // 归口建议

	private String loginUseCode; // 当前登录人编码
	private String loginUseName; // 当前登录人名称
	private String loginDepCode; // 当前登录人部门
	private String putmanagerCode; // 归口管理员编码
	private String putmanagerDepCode; // 归口部门经理编码
	private String isUpdateSap; // 是否更新SAP
	private String twoDepartmentHeadCode; // 二级部门负责人编码

	private String receivegoodsName;  // 仓库收货管理员姓名
	private String receivegoodsCode;  // 仓库收货管理员编码
	private String retiredgoodsName;  // 仓库废品管理员姓名
	private String retiredgoodsCode;  // 仓库废品管理员编码
	private String purchasetipaccessName;  // 采购意见处理员姓名
	private String purchasetipaccessCode;  // 采购意见处理员编码
	private String purchasebidName;  // 采购竞价/议价员姓名
	private String purchasebidCode;  // 采购竞价/议价员编码
	private String payeeName; // 收款确认员姓名
	private String payeeCode; // 收款确认员编码
	private BigDecimal iaMoeny; // 审批金额
	private String activityId; // 审批流参数
	private String isStamp; //是否打印 
	private String assetAdminId; //资产管理员id
	private String oldCommitType; //订单原状态
}
