<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
	<title>预算调整查看</title>
	<link rel="Stylesheet" type="text/css" href="${staticURL}/assetallocation/css/allocationapplicationapply.css">
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
	<style type="text/css">
		input[type="text"] {
			border: #cccccc 1px double;
            width: 98%;
		}
	</style>
</head>
	
<body>
<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
	<div id="tabletitle"
		 style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
		预算调整单
	</div>
</div>
	<div id="btnDiv" style="margin-left: 0px; margin-top: 0px;">
		<input type="button" name="" id="returnBtn" value="返回" onclick="dg.cancel()"  class="btn">
	</div>
	<c:choose>
		<c:when test="${budgetAdjustment.assetsBudgetType == '项目间调整'}">
			<app:approvalTag billCode="${budgetAdjustment.document}" typeId="77048"></app:approvalTag>
		</c:when>
		<c:otherwise>
			<app:approvalTag billCode="${budgetAdjustment.document}" typeId="77047"></app:approvalTag>
		</c:otherwise>
	</c:choose>
	<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
		<div class="imgtitle"></div>
				基本信息
	</div>
	<%--主表信息--%>
<table border="0" cellpadding="3px" cellspacing="0" style="width: 95%">
			<tr>
				<td class="td_right"><span class="span_color">*</span>流程单据号:</td>
				<td class="td_left">
					<input name="document" id="document" type="text" value="${budgetAdjustment.document}" class="td_input" readonly="readonly"/>
					<input type="hidden" name="budgetId" value="${budgetAdjustment.budgetId}" />
					<input type="hidden" name="iamoney" value="${budgetAdjustment.iamoney}" id="iamoney" />
					<input type="hidden" name="" value="${budgetAdjustment.iamoney}" id="iamoneyBefore" />
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属部门:</td>
				<td class="td_left">
					<input type="text" class="td_input" name="departmentName" value="${budgetAdjustment.departmentName}"  readonly="readonly">
					<input type="hidden" name="departmentCode" value="${budgetAdjustment.departmentCode}">
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属公司:</td>
				<td class="td_left">
					<input type="text" class="td_input" name="companyName" value="${budgetAdjustment.companyName}"  readonly="readonly"/>
					<input type="hidden" name="companyCode" value="${budgetAdjustment.companyCode}">
				</td>
			</tr>
			<tr>
				<td class="td_right">申请人:</td>
				<td class="td_left">
					<input name="userName" type="text" value="${budgetAdjustment.userName}" class="td_input" readonly="readonly"/>
					<input type="hidden" name="userCode" value="${budgetAdjustment.userCode}">
					<%--<input type="hidden" name="createBy" value="${budgetAdjustment.createBy}">--%>
					<%--<input type="hidden" name="createUserName" value="${budgetAdjustment.createUserName}">--%>
					<%--<input type="hidden" name="updateBy" value="${budgetAdjustment.updateBy}">--%>
					<%--<input type="hidden" name="updateUserName" value="${budgetAdjustment.updateUserName}">--%>
				</td>
				<td class="td_right">申请日期:</td>
				<td class="td_left">
					<input class="td_input" name="" type="text" value="<fmt:formatDate value="${budgetAdjustment.applicationDate}" pattern="yyyy-MM-dd"/> " id="applicationDate" readonly="readonly"/>
				</td>
				<td class="td_right">预算调整类型:</td>
				<td class="td_left" >
					<input type="text" class="td_input" name="assetsBudgetType" value="${budgetAdjustment.assetsBudgetType}" id="assetsBudgetType"  readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td class="td_right"><span class="span_color">*</span>调整原因:</td>
				<td class="td_left" colspan="5">
					<input name="adjustReason" value="${budgetAdjustment.adjustReason }" id="adjustReason"
						   class="td_input" readonly style="width: 99.5%"/>
				</td>
			</tr>
			<tr id="depTr">
				<td class="td_right">调入部门:</td>
				<td class="td_left" id="depIn">
					<input name="costCenterCode" type="hidden" value="${budgetAdjustment.costCenterCode }"/>
					<input name="costCenterName" type="hidden" value="${budgetAdjustment.costCenterName }"/>
					<input name="callInDepartmentCode" type="hidden" value="${budgetAdjustment.callInDepartmentCode }"/>
					<input name="callInDepartmentName" type="text" value="${budgetAdjustment.callInDepartmentName }" readonly="readonly" class="td_input"/>
				</td>
				<c:if test="${budgetAdjustment.assetsBudgetType == '项目间调整'}">
					<td class="td_right">调出部门:</td>
					<td class="td_left" id="depOut">
						<input type="hidden" name="callOutDepartmentCode" value="${budgetAdjustment.callOutDepartmentCode }" readonly="readonly"/>
						<input type="text" name="callOutDepartmentName" value="${budgetAdjustment.callOutDepartmentName }" readonly="readonly" class="td_input"/>
					</td>
				</c:if>
			</tr>
		</table>
	<%--子表信息--%>
	<c:choose>
		<c:when test="${budgetAdjustment.assetsBudgetType == '新增' || budgetAdjustment.assetsBudgetType == '追加/调减'}">
			<div id="" >
				<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
					<div class="imgtitle"></div>
					预算信息
				</div>
				<c:choose>
					<c:when test="${budgetAdjustment.assetsBudgetType == '新增'}">
						<div style="overflow-x: scroll; width: 100%;" id="addDiv">
                            <table border="0" cellpadding="3px" cellspacing="0" width="100%" style="min-width:1200px;">
								<thead>
                                <tr style="white-space:nowrap;">
									<th>年度</th>
									<th>成本中心编码</th>
									<th>成本中心名称</th>
									<th>项目名称</th>
									<th>资产名称</th>
									<th>资产类型</th>
									<th>类型</th>
									<th>单位</th>
									<th>数量</th>
									<th>单价</th>
									<th>年度预算总额</th>
									<th>预算依据</th>
								</tr>
								</thead>
								<tbody id="tbody_add" class="getMoney">
								<c:forEach items="${budgetAdjustment.budgetAdjustmentDetailList}" var="detail">
									<tr style="text-align: center" class="trCount">
										<td>
											<input name="budgetYear" value="${detail.budgetYear}" readonly="readonly" style="width: 100%"/>
											<input type="hidden" name="platform" value="${detail.platform}"/>
										</td>
										<td><input name="costCenter" value="${detail.costCenter}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="costCenterName" value="${detail.costCenterName}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="projectName" value="${detail.projectName}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsName" value="${detail.assetsName}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsType" value="${detail.assetsType}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsModel" value="${detail.assetsModel}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="unit" value="${detail.unit}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="beforeAdjustCount" value="${detail.beforeAdjustCount}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="beforeUnitPrice" value="${detail.beforeUnitPrice}" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
										<td><input name="beforeAdjustMoney" value="<fmt:formatNumber value="${detail.beforeAdjustMoney}" pattern="0.00"/>" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
										<td style="width: 20%"><input type="text" name="budgetBasedOn"
                                                                      value="${detail.budgetBasedOn}" style="width: 98%"
                                                                      readonly="readonly"/></td>
									</tr>

									<%--<tr style="text-align: center" class="trCount">--%>
										<%--<td>${detail.budgetYear}</td>--%>
										<%--<td>${detail.costCenter}</td>--%>
										<%--<td>${detail.costCenterName}</td>--%>
										<%--<td>${detail.projectName}</td>--%>
										<%--<td>${detail.assetsName}</td>--%>
										<%--<td>${detail.assetsType}</td>--%>
										<%--<td>${detail.assetsModel}</td>--%>
										<%--<td>${detail.unit}</td>--%>
										<%--<td>${detail.beforeAdjustCount}</td>--%>
										<%--<td isMoney='Y'>${detail.beforeUnitPrice}</td>--%>
										<%--<td isMoney='Y'><fmt:formatNumber value="${detail.beforeAdjustMoney}" pattern="0.00"/></td>--%>
										<%--<td style="width: 20%">${detail.budgetBasedOn}</td>--%>
									<%--</tr>--%>
								</c:forEach>
								<tr>
									<td colspan="10" style="text-align: right;font-weight: bold;">调整金额合计：</td>
									<td><input type="text" class="td_input adjustMoneyCount" value=""/></td>
								</tr>
								</tbody>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div style="overflow-x: scroll; width: 100%;" id="adjustDiv">
                            <table border="0" cellpadding="3px" cellspacing="0" width="100%" style="min-width:1200px;">
								<thead>
                                <tr style="white-space:nowrap;">
									<th>年度</th>
									<th>成本中心编码</th>
									<th>成本中心名称</th>
									<th>项目编码</th>
									<th>项目名称</th>
									<th>资产名称</th>
									<th>资产类型</th>
									<th>类型</th>
									<th>单位</th>
									<th>调整前数量</th>
									<th>调整数量</th>
									<th>调整后数量</th>
									<th>调整前单价</th>
									<th>调整后单价</th>
									<th>调整前总额</th>
									<th>调整金额</th>
									<th>调整后总额</th>
								</tr>
								</thead>
								<tbody id="tbody_adjust" class="getMoney">
								<c:forEach items="${budgetAdjustment.budgetAdjustmentDetailList}" var="detail">
									<tr style="text-align: center" class="trCount">
										<td>
											<input name="budgetYear" value="${detail.budgetYear}" readonly="readonly" style="width: 100%"/>
											<input type="hidden" name="platform" value="${detail.platform}"/>
										</td>
										<td><input name="costCenter" value="${detail.costCenter}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="costCenterName" value="${detail.costCenterName}" readonly="readonly" style="width: 100%"/></td>
										<td width="9%"><input name="projectCode" value="${detail.projectCode}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="projectName" value="${detail.projectName}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsName" value="${detail.assetsName}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsType" value="${detail.assetsType}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsModel" value="${detail.assetsModel}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="unit" value="${detail.unit}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="beforeAdjustCount" value="${detail.beforeAdjustCount}" readonly="readonly" style="width: 100%"/></td>
										<td>
											<input name="adjustCount" value="${detail.adjustCount}"  readonly="readonly" style="width: 100%"/>
											<input name="usedAmount"  value="${detail.usedAmount}" type="hidden"/>
										</td>
										<td><input name="afterAdjustCount" value="${detail.afterAdjustCount}" readonly="readonly" style="width: 100%"/></td>
										<td><input name="beforeUnitPrice" value="${detail.beforeUnitPrice}" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
										<td><input name="afterUnitPrice" value="${detail.afterUnitPrice}" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
										<td><input name="beforeAdjustMoney" value="<fmt:formatNumber value="${detail.beforeAdjustMoney}" pattern="0.00"/>" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
										<td>
											<input name="adjustMoney" value="<fmt:formatNumber value="${detail.adjustMoney}" pattern="0.00"/>" class="moneyFormat" readonly="readonly" style="width: 100%"/>
											<input name="usedSumMoney"  value="${detail.usedSumMoney}" type="hidden"/>
										</td>
										<td><input name="afterAdjustMoney" value="<fmt:formatNumber value="${detail.afterAdjustMoney}" pattern="0.00"/>" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="14" style="text-align: right;font-weight: bold;">调整金额合计：</td>
									<td><input type="text" class="td_input beforeAdjustMoneyCount" value=""/></td>
									<td><input type="text" class="td_input adjustMoneyCount" value=""/></td>
									<td><input type="text" class="td_input afterAdjustMoneyCount" value=""/></td>
								</tr>
								</tbody>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</c:when>
		<c:otherwise>
			<div id="">
				<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
					<div class="imgtitle"></div>
					预算调入项目
				</div>
				<div style="overflow-x: scroll; width: 100%;" >
                    <table border="0" cellpadding="3px" cellspacing="0" width="100%" style="min-width:1200px;">
						<thead>
                        <tr style="white-space:nowrap;">
							<th>年度</th>
							<th>成本中心编码</th>
							<th>成本中心名称</th>
							<th>项目编码</th>
							<th>项目名称</th>
							<th>资产名称</th>
							<th>资产类型</th>
							<th>类型</th>
							<th>单位</th>
							<th>调整前数量</th>
							<th>调整数量</th>
							<th>调整后数量</th>
							<th>调整前单价</th>
							<th>调整后单价</th>
							<th>调整前总额</th>
							<th>调整金额</th>
							<th>调整后总额</th>
						</tr>
						</thead>
						<tbody id="tbody_in" class="getMoney">
						<c:forEach items="${budgetAdjustment.budgetAdjustmentDetailList}" var="detail">
							<c:if test="${detail.specialAdjustMark == 'in'}">
								<tr style="text-align: center" class="trCount">
									<td>
										<input name="budgetYear" value="${detail.budgetYear}" readonly="readonly" style="width: 100%"/>
										<input type="hidden" name="platform" value="${detail.platform}"/>
									</td>
									<td><input name="costCenter" value="${detail.costCenter}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="costCenterName" value="${detail.costCenterName}" readonly="readonly" style="width: 100%"/></td>
									<td width="9%"><input name="projectCode" value="${detail.projectCode}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="projectName" value="${detail.projectName}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsName" value="${detail.assetsName}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsType" value="${detail.assetsType}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsModel" value="${detail.assetsModel}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="unit" value="${detail.unit}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeAdjustCount" value="${detail.beforeAdjustCount}" readonly="readonly" style="width: 100%"/></td>
									<td>
										<input name="adjustCount" value="${detail.adjustCount}"  readonly="readonly" style="width: 100%"/>
										<input name="usedAmount"  value="${detail.usedAmount}" type="hidden"/>
									</td>
									<td><input name="afterAdjustCount" value="${detail.afterAdjustCount}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeUnitPrice" value="${detail.beforeUnitPrice}" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
									<td><input name="afterUnitPrice" value="${detail.afterUnitPrice}" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeAdjustMoney" value="<fmt:formatNumber value="${detail.beforeAdjustMoney}" pattern="0.00"/>" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
									<td>
										<input name="adjustMoney" value="<fmt:formatNumber value="${detail.adjustMoney}" pattern="0.00"/>" class="moneyFormat" readonly="readonly" style="width: 100%"/>
										<input name="usedSumMoney"  value="${detail.usedSumMoney}" type="hidden"/>
									</td>
									<td><input name="afterAdjustMoney" value="<fmt:formatNumber value="${detail.afterAdjustMoney}" pattern="0.00"/>" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="14" style="text-align: right;font-weight: bold;">调整金额合计：</td>
							<td><input type="text" class="td_input beforeAdjustMoneyCount" value=""/></td>
							<td><input type="text" class="td_input adjustMoneyCount" value=""/></td>
							<td><input type="text" class="td_input afterAdjustMoneyCount" value=""/></td>
						</tr>
						</tbody>
					</table>
				</div>
				<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
					<div class="imgtitle"></div>
					预算调出项目
				</div>
				<div style="overflow-x: scroll; width: 100%;" >
                    <table border="0" cellpadding="3px" cellspacing="0" width="100%" style="min-width:1200px;">
						<thead>
                        <tr style="white-space:nowrap;">
							<th>年度</th>
							<th>成本中心编码</th>
							<th>成本中心名称</th>
							<th>项目编码</th>
							<th>项目名称</th>
							<th>资产名称</th>
							<th>资产类型</th>
							<th>类型</th>
							<th>单位</th>
							<th>调整前数量</th>
							<th>调整数量</th>
							<th>调整后数量</th>
							<th>调整前单价</th>
							<th>调整后单价</th>
							<th>调整前总额</th>
							<th>调整金额</th>
							<th>调整后总额</th>
						</tr>
						</thead>
						<tbody id="tbody_out" class="getMoneyOut">
						<c:forEach items="${budgetAdjustment.budgetAdjustmentDetailList}" var="detail">
							<c:if test="${detail.specialAdjustMark == 'out'}">
								<tr style="text-align: center" class="trCount">
									<td>
										<input name="budgetYear" value="${detail.budgetYear}" readonly="readonly" style="width: 100%"/>
										<input type="hidden" name="specialAdjustMark"
											   value="${detail.specialAdjustMark}"/>
										<input type="hidden" name="platform" value="${detail.platform}"/>
									</td>
									<td><input name="costCenter" value="${detail.costCenter}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="costCenterName" value="${detail.costCenterName}" readonly="readonly" style="width: 100%"/></td>
									<td width="9%"><input name="projectCode" value="${detail.projectCode}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="projectName" value="${detail.projectName}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsName" value="${detail.assetsName}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsType" value="${detail.assetsType}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsModel" value="${detail.assetsModel}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="unit" value="${detail.unit}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeAdjustCount" value="${detail.beforeAdjustCount}" readonly="readonly" style="width: 100%"/></td>
									<td>
										<input name="adjustCount" value="${detail.adjustCount}"  readonly="readonly" style="width: 100%"/>
										<input name="usedAmount"  value="${detail.usedAmount}" type="hidden"/>
									</td>
									<td><input name="afterAdjustCount" value="${detail.afterAdjustCount}" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeUnitPrice" value="${detail.beforeUnitPrice}" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
									<td><input name="afterUnitPrice" value="${detail.afterUnitPrice}" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeAdjustMoney" value="<fmt:formatNumber value="${detail.beforeAdjustMoney}" pattern="0.00"/>" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
									<td>
										<input name="adjustMoney" value="<fmt:formatNumber value="${detail.adjustMoney}" pattern="0.00"/>" class="moneyFormat" readonly="readonly" style="width: 100%"/>
										<input name="usedSumMoney"  value="${detail.usedSumMoney}" type="hidden"/>
									</td>
									<td><input name="afterAdjustMoney" value="<fmt:formatNumber value="${detail.afterAdjustMoney}" pattern="0.00"/>" class="moneyFormat" readonly="readonly" style="width: 100%"/></td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td colspan="14" style="text-align: right;font-weight: bold;">调整金额合计：</td>
							<td><input type="text" class="td_input beforeAdjustMoneyCount" value=""/></td>
							<td><input type="text" class="td_input adjustMoneyCount" value=""/></td>
							<td><input type="text" class="td_input afterAdjustMoneyCount" value=""/></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<%--附件上传--%>
	<div class="divtitlemt">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tbody>
			<tr>
				<td class="titlefontsize" align="left" style="width: 150px">
					<div class="imgtitle"></div>
					附件
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="divGrid">
						<div>
							<table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList" style="border-collapse:collapse;">
								<thead>
								<tr style="white-space:nowrap;">
									<th scope="col">
										<input  type="checkbox" name="gvDataList$ctl01$CheckAll" onclick="javascript:SelectAllCheckboxesByTableId(this, 'uploadTable');">
									</th>
									<th scope="col"> 文件</th>
									<th scope="col">上传人</th>
									<th scope="col">上传时间</th>
									<th scope="col">附件下载</th>
								</tr>
								</thead>
								<tbody>
								<tr class="noItems" align="center">
									<td colspan="5">没有记录！</td>
								</tr>
								</tbody>
							</table>
						</div>
					</div>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<%--审批历史--%>
	<div>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tbody>
			<tr>
				<td class="titlefontsize" align="left" style="width: 150px">
					<div class="imgtitle"></div>
					审批历史
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="divGrid">
						<div>
							<table class="adminlist" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
								<thead>
								<tr style="white-space: nowrap;">
									<th scope="col">审批人</th>
									<th scope="col">审批日期</th>
									<th scope="col">审批结果</th>
									<th scope="col">审批意见</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${appHistoryPager.records}" var="appHistory" varStatus="status">
									<tr style="white-space: nowrap;">
										<td class="tdleft">${appHistory.examineUser}</td>
										<td class="tdleft"><fmt:formatDate value='${appHistory.examineDate}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
										<td class="tdleft">${appHistory.examineResult}</td>
										<td class="tdleft">${appHistory.examineAdvice}</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>

						</div>
					</div>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<%--<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>--%>
	<%--<script src="${staticURL}/js/ajaxfileupload.js" type="text/javascript"></script>--%>
	<%--<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>--%>
	<%--<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>--%>
	<%--<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>--%>
	<script type="text/javascript">
		var dg = frameElement.lhgDG;

		$(function () {
			var specialAdjustMark = $("#tbody_out").find("input[name=specialAdjustMark]").val();
			countMoney(specialAdjustMark); //金额合计

			$.ajax({
				url:'${dynamicURL}/file/getFilesByDocument',
				data:{'document':'${budgetAdjustment.document}'},
				dataType:'json',
				type:'POST',
				success:function(data){
					if(data.length > 0 ){
						$('#uploadTable .noItems').remove();
						$.each(data,function(i,item){
							$('#uploadTable > tbody').append('<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
									+ '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '" name=documentId />'
									+ '</td><td scope="col">' + item.fileName
									+ '</td><td scope="col">' + item.createUserName
									+ '</td><td style="width:9%">' + item.createDate
									+ '</td><td align="center" style="width:10%"><a href="'+'${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
									+ '</td></tr>');
						});
					}

				}
			});

			$(".moneyFormat").each(function(){
				if($(this).val()!=""){
					$(this).val(fmoney($(this).val(),2));
				};
			});
		});

		/** 金额合计 */
		function countMoney(mark) {
			var adjustMoneyCount = 0.0;  //调整金额合计
			var beforeAdjustMoneyCount = 0.0; //调整前金额合计
			var afterAdjustMoneyCount = 0.0; //调整后金额合计
			if (mark == 'out') {
				$(".getMoneyOut").find("input[name=beforeAdjustMoney]").each(function () {
					beforeAdjustMoneyCount += $(this).val() - 0;
				});
				$(".getMoneyOut").find("input[name=adjustMoney]").each(function () {
					adjustMoneyCount += $(this).val() - 0;
				});
				$(".getMoneyOut").find("input[name=afterAdjustMoney]").each(function () {
					afterAdjustMoneyCount += $(this).val() - 0;
				});
				$(".getMoneyOut").find(".beforeAdjustMoneyCount").val(beforeAdjustMoneyCount);
				$(".getMoneyOut").find(".adjustMoneyCount").val(adjustMoneyCount);
				$(".getMoneyOut").find(".afterAdjustMoneyCount").val(afterAdjustMoneyCount);
			}
			if ($("#assetsBudgetType").val() == "新增") {
				$(".getMoney").find("input[name=beforeAdjustMoney]").each(function () {
					adjustMoneyCount += $(this).val() - 0;
				});
				$(".adjustMoneyCount").val(adjustMoneyCount);
			} else { //追加调减  项目间调整调入
				adjustMoneyCount = 0.0;  //调整金额合计
				beforeAdjustMoneyCount = 0.0; //调整前金额合计
				afterAdjustMoneyCount = 0.0; //调整后金额合计
				$(".getMoney").find("input[name=beforeAdjustMoney]").each(function () {
					beforeAdjustMoneyCount += $(this).val() - 0;
				});
				$(".getMoney").find("input[name=adjustMoney]").each(function () {
					adjustMoneyCount += $(this).val() - 0;
				});
				$(".getMoney").find("input[name=afterAdjustMoney]").each(function () {
					afterAdjustMoneyCount += $(this).val() - 0;
				});
				$(".getMoney").find(".beforeAdjustMoneyCount").val(beforeAdjustMoneyCount);
				$(".getMoney").find(".adjustMoneyCount").val(adjustMoneyCount);
				$(".getMoney").find(".afterAdjustMoneyCount").val(afterAdjustMoneyCount);
			}
		}

		/**
		 * 数字格式转换成千分位
		 */
		function fmoney(s, n){
			n = n > 0 && n <= 20 ? n : 2;
			s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
			var l = s.split('.') [0].split('').reverse(),
					r = s.split('.') [1];
			var  t = '';
			for (var i = 0; i < l.length; i++)
			{
				t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
			}
			return t.split('').reverse().join('') + '.' + r;

		}
	</script>
</body>
</html>