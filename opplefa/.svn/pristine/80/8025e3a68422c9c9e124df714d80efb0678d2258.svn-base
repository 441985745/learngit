<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
	<title>预算调整</title>
	<link rel="Stylesheet" type="text/css" href="${staticURL}/assetallocation/css/allocationapplicationapply.css">
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
<style type="text/css">
	.prompt {
		color: red;;
	}
	.td_input {
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
		<input type="submit" name="" id="saveBtn" value="提交" onclick="" class="btn">
		<input type="submit" name="" id="saveDraftBtn" value="保存草稿" onclick="submitAdjust(this, 'Y')" class="btn">

		<%--<input type="submit" name="" id="agreeBtn" value="提交" onclick="" class="btn">--%>
		<%--<input type="submit" name="" id="saveDraftBtn" value="提交草稿" onclick="" class="btn">--%>
		<input type="button" name="" id="returnBtn" value="返回" onclick=""  class="btn">
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
	<form id="budgetAdjustmentForm">
        <table border="0" cellpadding="3px" cellspacing="0" style="width: 95%">
			<tr>
				<td class="td_right"><span class="span_color">*</span>流程单据号:</td>
				<td class="td_left">
					<input name="document" id="document" type="text" value="${budgetAdjustment.document}" readonly="readonly" class="td_input" />
					<input type="hidden" name="budgetId" value="${budgetAdjustment.budgetId}" />
					<input type="hidden" name="iamoney" value="${budgetAdjustment.iamoney}" id="iamoney" />
					<input type="hidden" name="" value="${budgetAdjustment.iamoney}" id="iamoneyBefore" />

					<input name="isMail" id="isMail"  type="hidden" value="" />
					<input name="isPhoneMessage" id="isPhoneMessage"  type="hidden" value="" />
					<input name="idea" id="idea"  type="hidden" value="" />
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属部门:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="departmentName" value="${budgetAdjustment.departmentName}" >
					<input type="hidden" name="departmentCode" value="${budgetAdjustment.departmentCode}">
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属公司:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="companyName" value="${budgetAdjustment.companyName}" >
					<input type="hidden" name="companyCode" value="${budgetAdjustment.companyCode}">
				</td>
			</tr>
			<tr>
				<td class="td_right">申请人:</td>
				<td class="td_left">
					<input name="userName" type="text" value="${budgetAdjustment.userName}" readonly="readonly" class="td_input"/>
					<input type="hidden" name="userCode" value="${budgetAdjustment.userCode}">
					<%--<input type="hidden" name="createBy" value="${budgetAdjustment.createBy}">--%>
					<%--<input type="hidden" name="createUserName" value="${budgetAdjustment.createUserName}">--%>
					<%--<input type="hidden" name="updateBy" value="${budgetAdjustment.updateBy}">--%>
					<%--<input type="hidden" name="updateUserName" value="${budgetAdjustment.updateUserName}">--%>
				</td>
				<td class="td_right">申请日期:</td>
				<td class="td_left">
					<input class="td_input" name="" type="text" readonly="readonly" value="<fmt:formatDate value="${budgetAdjustment.applicationDate}" pattern="yyyy-MM-dd"/> " id="applicationDate">
				</td>
				<td class="td_right">预算调整类型:</td>
				<td class="td_left" >
					<input type="text" class="td_input" readonly="readonly" name="assetsBudgetType" value="${budgetAdjustment.assetsBudgetType}" id="assetsBudgetType">
				</td>
			</tr>
			<tr>
				<td class="td_right"><span class="span_color">*</span>调整原因:</td>
				<td class="td_left" colspan="5">
					<input name="adjustReason" value="${budgetAdjustment.adjustReason }" id="adjustReason"
						   class="td_input" style="width: 99.5%"/>
				</td>
			</tr>
			<tr id="depTr">
				<td class="td_right">调入部门:</td>
				<td class="td_left" id="depIn">
					<input name="costCenterCode" type="hidden" value="${budgetAdjustment.costCenterCode }"/>
					<input name="costCenterName" type="hidden" value="${budgetAdjustment.costCenterName }"/>
					<input name="twoDepartmentHeadCode" type="hidden"
						   value="${budgetAdjustment.twoDepartmentHeadCode }"/>
					<input name="twoDepartmentHeadName" type="hidden"
						   value="${budgetAdjustment.twoDepartmentHeadName }"/>
					<input name="callInDepartmentCode" type="hidden" value="${budgetAdjustment.callInDepartmentCode }"/>
					<input name="callInDepartmentName" type="text" value="${budgetAdjustment.callInDepartmentName }" readonly="readonly" class="td_input"/>
				</td>
				<c:if test="${budgetAdjustment.assetsBudgetType == '项目间调整'}">
					<td class="td_right">调出部门:</td>
					<td class="td_left" id="depOut">

						<input type="hidden" name="outTwoDepartmentHeadCode"
							   value="${budgetAdjustment.outTwoDepartmentHeadCode }"/>
						<input type="hidden" name="outTwoDepartmentHeadName"
							   value="${budgetAdjustment.outTwoDepartmentHeadName }"/>
						<input type="hidden" name="callOutDepartmentCode" value="${budgetAdjustment.callOutDepartmentCode }" readonly="readonly"/>
						<input type="text" name="callOutDepartmentName" value="${budgetAdjustment.callOutDepartmentName }" readonly="readonly" class="td_input"/>
					</td>
				</c:if>
			</tr>
		</table>
	</form>
	<%--子表信息--%>
    <form id="detailForm">
	<c:choose>
		<c:when test="${budgetAdjustment.assetsBudgetType == '新增' || budgetAdjustment.assetsBudgetType == '追加/调减'}">
			<div id="" >
				<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
					<div class="imgtitle"></div>
					预算信息
				</div>
				<div style="margin-left: 0px; margin-top: 0px;">
					<input type="button" name="" value="导入" class="btnImport" class="btn">
					<span class="prompt">导入数据必须属于同一年度、同一成本中心</span>
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
											<input name="budgetYear" value="${detail.budgetYear}" class="td_input" readonly="readonly" style="width: 100%"/>
											<input type="hidden" name="platform" value="${detail.platform}"/>
										</td>
										<td><input name="costCenter" value="${detail.costCenter}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="costCenterName" value="${detail.costCenterName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="projectName" value="${detail.projectName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsName" value="${detail.assetsName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsType" value="${detail.assetsType}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsModel" value="${detail.assetsModel}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="unit" value="${detail.unit}" readonly="readonly" class="td_input" style="width: 100%"/></td>
										<td><input name="beforeAdjustCount" value="${detail.beforeAdjustCount}" class="td_input" onblur="addCountBlur(this)" style="width: 100%"/></td>
                                        <td><input name="beforeUnitPrice" value="${detail.beforeUnitPrice}" class="td_input"
                                                   onblur="unitBlur(this)" style="width: 100%"/></td>
										<td><input name="beforeAdjustMoney"
												   value="<fmt:formatNumber value="${detail.beforeAdjustMoney}" pattern="0.00"/>"
												   class="td_input"
												   readonly="readonly" style="width: 100%"/></td>
										<td style="width: 20%"><input type="text" name="budgetBasedOn"
																	  value="${detail.budgetBasedOn}" class="td_input"
																	  style="width: 98%"/></td>
									</tr>
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
											<input name="budgetYear" value="${detail.budgetYear}" class="td_input" readonly="readonly" style="width: 100%"/>
											<input type="hidden" name="platform" value="${detail.platform}"/>
										</td>
										<td><input name="costCenter" value="${detail.costCenter}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="costCenterName" value="${detail.costCenterName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td width="9%"><input name="projectCode" value="${detail.projectCode}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="projectName" value="${detail.projectName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsName" value="${detail.assetsName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsType" value="${detail.assetsType}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="assetsModel" value="${detail.assetsModel}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="unit" value="${detail.unit}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="beforeAdjustCount" value="${detail.beforeAdjustCount}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td>
											<input name="adjustCount" value="${detail.adjustCount}" class="td_input" onblur="countBlur(this)" style="width: 100%"/>
											<input name="usedAmount"  value="${detail.usedAmount}" type="hidden"/>
										</td>
										<td><input name="afterAdjustCount" value="${detail.afterAdjustCount}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="beforeUnitPrice" value="${detail.beforeUnitPrice}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="afterUnitPrice" value="${detail.afterUnitPrice}" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td><input name="beforeAdjustMoney" value="<fmt:formatNumber value="${detail.beforeAdjustMoney}" pattern="0.00"/>" class="td_input" readonly="readonly" style="width: 100%"/></td>
										<td>
											<input name="adjustMoney" value="<fmt:formatNumber value="${detail.adjustMoney}" pattern="0.00"/>" class="td_input" onblur="moneyBlur(this)" style="width: 100%"/>
											<input name="usedSumMoney"  value="${detail.usedSumMoney}" type="hidden"/>
										</td>
										<td><input name="afterAdjustMoney" value="<fmt:formatNumber value="${detail.afterAdjustMoney}" pattern="0.00"/>" class="td_input" readonly="readonly" style="width: 100%"/></td>
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
				<div style="margin-left: 0px; margin-top: 0px;">
					<input type="button" name="" value="导入" class="btnImport" class="btn">
					<span class="prompt">导入数据必须属于同一年度、同一成本中心</span>
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
										<input name="budgetYear" value="${detail.budgetYear}" class="td_input" readonly="readonly" style="width: 100%"/>
										<input type="hidden" name="specialAdjustMark" value="${detail.specialAdjustMark}"/>
										<input type="hidden" name="platform" value="${detail.platform}"/>
									</td>
									<td><input name="costCenter" value="${detail.costCenter}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="costCenterName" value="${detail.costCenterName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td width="9%"><input name="projectCode" value="${detail.projectCode}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="projectName" value="${detail.projectName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsName" value="${detail.assetsName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsType" value="${detail.assetsType}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsModel" value="${detail.assetsModel}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="unit" value="${detail.unit}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeAdjustCount" value="${detail.beforeAdjustCount}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td>
										<input name="adjustCount" value="${detail.adjustCount}" class="td_input" onblur="countBlur(this)" style="width: 100%"/>
										<input name="usedAmount"  value="${detail.usedAmount}" type="hidden"/>
									</td>
									<td><input name="afterAdjustCount" value="${detail.afterAdjustCount}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeUnitPrice" value="${detail.beforeUnitPrice}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="afterUnitPrice" value="${detail.afterUnitPrice}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeAdjustMoney" value="<fmt:formatNumber value="${detail.beforeAdjustMoney}" pattern="0.00"/>" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td>
										<input name="adjustMoney" value="<fmt:formatNumber value="${detail.adjustMoney}" pattern="0.00"/>" class="td_input"  onblur="moneyBlur(this)" style="width: 100%"/>
										<input name="usedSumMoney"  value="${detail.usedSumMoney}" type="hidden"/>
									</td>
									<td><input name="afterAdjustMoney" value="<fmt:formatNumber value="${detail.afterAdjustMoney}" pattern="0.00"/>" class="td_input" readonly="readonly" style="width: 100%"/></td>
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
				<div style="margin-left: 0px; margin-top: 0px;">
					<input type="button" name="" value="导入" id="outImport" class="btn">
					<span class="prompt">导入数据必须属于同一年度、同一成本中心</span>
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
										<input name="budgetYear" value="${detail.budgetYear}" class="td_input" readonly="readonly" style="width: 100%"/>
										<input type="hidden" name="specialAdjustMark" value="${detail.specialAdjustMark}"/>
										<input type="hidden" name="platform" value="${detail.platform}"/>
									</td>
									<td><input name="costCenter" value="${detail.costCenter}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="costCenterName" value="${detail.costCenterName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td width="9%"><input name="projectCode" value="${detail.projectCode}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="projectName" value="${detail.projectName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsName" value="${detail.assetsName}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsType" value="${detail.assetsType}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="assetsModel" value="${detail.assetsModel}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="unit" value="${detail.unit}" readonly="readonly" class="td_input" style="width: 100%"/></td>
									<td><input name="beforeAdjustCount" value="${detail.beforeAdjustCount}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td>
										<input name="adjustCount" value="${detail.adjustCount}" class="td_input" onblur="countBlur(this)" style="width: 100%"/>
										<input name="usedAmount"  value="${detail.usedAmount}" type="hidden"/>
									</td>
									<td><input name="afterAdjustCount" value="${detail.afterAdjustCount}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeUnitPrice" value="${detail.beforeUnitPrice}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="afterUnitPrice" value="${detail.afterUnitPrice}" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td><input name="beforeAdjustMoney" value="<fmt:formatNumber value="${detail.beforeAdjustMoney}" pattern="0.00"/>" class="td_input" readonly="readonly" style="width: 100%"/></td>
									<td>
										<input name="adjustMoney" value="<fmt:formatNumber value="${detail.adjustMoney}" pattern="0.00"/>" class="td_input" onblur="moneyBlur(this)" style="width: 100%"/>
										<input name="usedSumMoney"  value="${detail.usedSumMoney}" type="hidden"/>
									</td>
									<td><input name="afterAdjustMoney" value="<fmt:formatNumber value="${detail.afterAdjustMoney}" pattern="0.00"/>" class="td_input" readonly="readonly" style="width: 100%"/></td>
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
    </form>

	<%--附件上传--%>
	<div class="divtitlemt">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tbody>
			<tr>
				<td class="titlefontsize" align="left" style="width: 150px">
					<div class="imgtitle"></div>
					附件
				</td>
				<td>
					<input type="file" name="multipartFile" id="fileToUpload" project="Y" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
					<input type="submit" name="btnUpload" value="上传" id="btnUpload" project="Y" class="btn">
					<input type="submit" name="btnDelFile" value="删除" id="btnDelFile" project="Y" class="btn"></td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="divGrid">
						<div>
							<table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList" style="border-collapse:collapse;">
								<thead>
								<tr style="white-space:nowrap;">
									<th scope="col">
										<input  type="checkbox" name="gvDataList$ctl01$CheckAll" onclick="javascript:SelectAllCheckboxesByTableId(this, 'uploadTable2');">
									</th>
									<th scope="col"> 文件</th>
									<th scope="col">上传人</th>
									<th scope="col">上传时间</th>
									<th scope="col">附件下载</th>
								</tr>
								</thead>
								<tbody id="orderFileList">
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
    <jsp:include page="import_budget_adjustment_window.jsp"></jsp:include>

	<%--<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>--%>
	<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
	<%--<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>--%>
	<%--<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>--%>
	<%--<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>--%>
	<script type="text/javascript">
		var dg = frameElement.lhgDG;
		//解决ie报错 对象没有trim属性或方法
		String.prototype.trim = function () {
			return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
		};

		$(function () {
			var specialAdjustMark = $("#tbody_out").find("input[name=specialAdjustMark]").val();
			countMoney(specialAdjustMark); //金额合计
		});
		/*导入的数据*/
		var budgetAdjustmentDetails;
		/*控制项目间调整时导入表*/
		var btnflag = true;

		/*回调函数 将导入的数据在前台展示*/
		function importData(data) {
			var type = $("#assetsBudgetType").val();
			var detailHtml = "";  //子表信息
			var budgetHtml = "";  //主表部门信息
			budgetAdjustmentDetails = data.budgetAdjustmentDetails;
			if (budgetAdjustmentDetails.length > 0) {
				var adjustMoneyCount = 0.0;  //调整金额合计
				var beforeAdjustMoneyCount = 0.0; //调整前金额合计
				var afterAdjustMoneyCount = 0.0; //调整后金额合计
				if (type == "新增") {
					for (var i = 0; i < budgetAdjustmentDetails.length; i++) {
						if (i == 0) {
							budgetHtml = '<td class="td_right">调入部门:</td>'
								+ '<td class="td_left">'
								+ '<input name="twoDepartmentHeadCode" type="hidden" value="' + budgetAdjustmentDetails[i].twoDepartmentHeadCode + '" class="td_input" readonly="readonly"/>'
								+ '<input name="twoDepartmentHeadName" type="hidden" value="' + budgetAdjustmentDetails[i].twoDepartmentHeadName + '" class="td_input" readonly="readonly"/>'
								+ '<input name="costCenterCode" type="hidden" value="' + budgetAdjustmentDetails[i].costCenter + '" class="td_input" readonly="readonly"/>'
								+ '<input name="costCenterName" type="hidden" value="' + budgetAdjustmentDetails[i].costCenterName + '" class="td_input" readonly="readonly"/>'
								+ '<input name="callInDepartmentCode" type="hidden" value="' + budgetAdjustmentDetails[i].callInDepartmentCode + '" class="td_input" readonly="readonly"/>'
								+ '<input name="callInDepartmentName" type="text" value="' + budgetAdjustmentDetails[i].callInDepartmentName + '" class="td_input" readonly="readonly" style="width:160px;height: 20px;"/>'
								+ '</td>'
						}
						detailHtml += '<tr style="text-align: center" class="trCount">'
								+ '<td><input type="text" name="budgetYear" value="' + budgetAdjustmentDetails[i].budgetYear + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="costCenter" value="' + budgetAdjustmentDetails[i].costCenter + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="costCenterName" value="' + budgetAdjustmentDetails[i].costCenterName + '" class="td_input" readonly="readonly" style="width: 100%"/>'
								+ '<input type="hidden" name="platform" value="' + budgetAdjustmentDetails[i].platform + '"/></td>'
								+ '<td><input type="text" name="projectName" value="' + budgetAdjustmentDetails[i].projectName + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="assetsName" value="' + budgetAdjustmentDetails[i].assetsName + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="assetsType" value="' + budgetAdjustmentDetails[i].assetsType + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="assetsModel" value="' + budgetAdjustmentDetails[i].assetsModel + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="unit" value="' + budgetAdjustmentDetails[i].unit + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="beforeAdjustCount" value="' + budgetAdjustmentDetails[i].beforeAdjustCount + '" class="td_input" onblur="addCountBlur(this)" style="width: 100%"/></td>'
								+ '<td><input type="text" name="beforeUnitPrice" value="' + budgetAdjustmentDetails[i].beforeUnitPrice + '" class="td_input" onblur="unitBlur(this)" style="width: 100%"/></td>'
								+ '<td><input type="text" name="beforeAdjustMoney" value="' + budgetAdjustmentDetails[i].beforeAdjustMoney + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td style="width: 20%"><input type="text" name="budgetBasedOn" value="' + budgetAdjustmentDetails[i].budgetBasedOn + '" class="td_input" style="width: 98%"/></td><tr>'
					}
					detailHtml += '<tr>' + '<td colspan="10" style="text-align: right;font-weight: bold;">调整金额合计：</td>'
							+ '<td><input type="text" class="td_input adjustMoneyCount" value="' + adjustMoneyCount + '" /></td>' + '</tr>';
					$("#tbody_add").html(detailHtml);
					$("#depTr").html(budgetHtml);
				} else {
					for (var i = 0; i < budgetAdjustmentDetails.length; i++) {
						adjustMoneyCount = adjustMoneyCount + budgetAdjustmentDetails[i].adjustMoney;  //调整金额合计
						beforeAdjustMoneyCount = beforeAdjustMoneyCount + budgetAdjustmentDetails[i].beforeAdjustMoney; //调整前金额合计
						afterAdjustMoneyCount = afterAdjustMoneyCount + budgetAdjustmentDetails[i].afterAdjustMoney; //调整后金额合计
						var mark = "";
						if (type != "追加/调减" && btnflag) {
							//项目间调整调入
							mark = '<input type="hidden" name="specialAdjustMark" value="in" />';
							if (i == 0) {
								budgetHtml = '<input name="twoDepartmentHeadCode" type="hidden" value="' + budgetAdjustmentDetails[i].twoDepartmentHeadCode + '" class="td_input" readonly="readonly"/>'
										+ '<input name="twoDepartmentHeadName" type="hidden" value="' + budgetAdjustmentDetails[i].twoDepartmentHeadName + '" class="td_input" readonly="readonly"/>'
										+ '<input name="callInDepartmentCode" type="hidden" value="' + budgetAdjustmentDetails[i].callInDepartmentCode + '" class="td_input" readonly="readonly"/>'
										+ '<input name="callInDepartmentName" type="text" value="' + budgetAdjustmentDetails[i].callInDepartmentName + '" class="td_input" readonly="readonly" style="width:160px;height: 20px;"/>';
							}
						} else if (type != "追加/调减" && !btnflag) {
							//项目间调整调出
							mark = '<input type="hidden" name="specialAdjustMark" value="out" />';
							if (i == 0) {
								budgetHtml =  '<input name="outTwoDepartmentHeadCode" type="hidden" value="' + budgetAdjustmentDetails[i].twoDepartmentHeadCode + '" class="td_input" readonly="readonly"/>'
										+ '<input name="outTwoDepartmentHeadName" type="hidden" value="' + budgetAdjustmentDetails[i].twoDepartmentHeadName + '" class="td_input" readonly="readonly"/>'
										+ '<input name="callOutDepartmentCode" type="hidden" value="' + budgetAdjustmentDetails[i].callInDepartmentCode + '" class="td_input" readonly="readonly"/>'
										+ '<input name="callOutDepartmentName" type="text" value="' + budgetAdjustmentDetails[i].callInDepartmentName + '" class="td_input" readonly="readonly" style="width:160px;height: 20px;"/>';
							}
						} else {
							//追加调减
							if (i == 0) {
								budgetHtml ='<td class="td_right">调入部门:</td>'
										+ '<td class="td_left">'
										+ '<input name="twoDepartmentHeadCode" type="hidden" value="' + budgetAdjustmentDetails[i].twoDepartmentHeadCode + '" class="td_input" readonly="readonly"/>'
										+ '<input name="twoDepartmentHeadName" type="hidden" value="' + budgetAdjustmentDetails[i].twoDepartmentHeadName + '" class="td_input" readonly="readonly"/>'
										+ '<input name="costCenterCode" type="hidden" value="' + budgetAdjustmentDetails[i].costCenter + '" class="td_input" readonly="readonly"/>'
										+ '<input name="costCenterName" type="hidden" value="' + budgetAdjustmentDetails[i].costCenterName + '" class="td_input" readonly="readonly"/>'
										+ '<input name="callInDepartmentCode" type="hidden" value="' + budgetAdjustmentDetails[i].callInDepartmentCode + '" class="td_input" readonly="readonly"/>'
										+ '<input name="callInDepartmentName" type="text" value="' + budgetAdjustmentDetails[i].callInDepartmentName + '" class="td_input" readonly="readonly" style="width:160px;height: 20px;"/>'
										+ '</td>'
							}
						}
						detailHtml += '<tr style="text-align: center" class="trCount"><td>' + mark
								+ '<input type="text" name="budgetYear" value="' + budgetAdjustmentDetails[i].budgetYear + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="costCenter" value="' + budgetAdjustmentDetails[i].costCenter + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="costCenterName" value="' + budgetAdjustmentDetails[i].costCenterName + '" class="td_input" readonly="readonly" style="width: 100%"/>'
								+ '<input type="hidden" name="platform" value="' + budgetAdjustmentDetails[i].platform + '"/></td>'
								+ '<td width="9%"><input type="text" name="projectCode" value="' + budgetAdjustmentDetails[i].projectCode + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="projectName" value="' + budgetAdjustmentDetails[i].projectName + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="assetsName" value="' + budgetAdjustmentDetails[i].assetsName + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="assetsType" value="' + budgetAdjustmentDetails[i].assetsType + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="assetsModel" value="' + budgetAdjustmentDetails[i].assetsModel + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="unit" value="' + budgetAdjustmentDetails[i].unit + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="beforeAdjustCount" value="' + budgetAdjustmentDetails[i].beforeAdjustCount + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="adjustCount" value="' + budgetAdjustmentDetails[i].adjustCount + '" id="adjustCount" class="td_input" onblur="countBlur(this)" style="width: 100%"/>'
								+ '<input type="hidden" name="usedAmount" value="' + budgetAdjustmentDetails[i].usedAmount + '" /></td>'
								+ '<td><input type="text" name="afterAdjustCount" value="' + budgetAdjustmentDetails[i].afterAdjustCount + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="beforeUnitPrice" value="' + budgetAdjustmentDetails[i].beforeUnitPrice + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="afterUnitPrice" value="' + budgetAdjustmentDetails[i].afterUnitPrice + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="beforeAdjustMoney" value="' + budgetAdjustmentDetails[i].beforeAdjustMoney + '" class="td_input" readonly="readonly" style="width: 100%"/></td>'
								+ '<td><input type="text" name="adjustMoney" value="' + budgetAdjustmentDetails[i].adjustMoney + '" id="adjustMoney" class="td_input" onblur="moneyBlur(this)" style="width: 100%"/>'
								+ '<input type="hidden" name="usedSumMoney" value="' + budgetAdjustmentDetails[i].usedSumMoney + '"/></td>'
								+ '<td><input type="text" name="afterAdjustMoney" value="' + budgetAdjustmentDetails[i].afterAdjustMoney + '" class="td_input" readonly="readonly" style="width: 100%"/></td><tr>'
					}
					detailHtml += '<tr>' + '<td colspan="14" style="text-align: right;font-weight: bold;">调整金额合计：</td>'
							+ '<td><input type="text" class="td_input beforeAdjustMoneyCount" value="' + beforeAdjustMoneyCount + '" /></td>'
							+ '<td><input type="text" class="td_input adjustMoneyCount" value="' + adjustMoneyCount + '" /></td>'
							+ '<td><input type="text" class="td_input afterAdjustMoneyCount" value="' + afterAdjustMoneyCount + '" /></td>' + '</tr>';
					switch (type) {
						case "追加/调减" :
							$("#tbody_adjust").html(detailHtml);
							$("#depTr").html(budgetHtml);
							break;
						case "项目间调整" :
							if (btnflag) {
								$("#tbody_in").html(detailHtml);
								$("#depIn").html(budgetHtml);
							} else {
								$("#tbody_out").html(detailHtml);
								$("#depOut").html(budgetHtml);
							}
							break;
					}
				}
			}
		}

		/** 调整数量失去焦点时计算联动效果 */
		function countBlur(that) {
			var adjustCount = 0;
			if ($(that).val() != "" && $(that).val() != null) {
				adjustCount = parseInt($(that).val()); //调整数量
			} else {
				$(that).val(0);
			}
			var usedAmount = parseInt($(that).next().val() == "" ? '0' : $(that).next().val()); //已用数量
			var beforeAdjustCount = parseInt($(that).parent().parent().find("input[name=beforeAdjustCount]").val()); //调整前数量
			var afterAdjustMoney = parseFloat($(that).parent().parent().find("input[name=afterAdjustMoney]").val()).toFixed(2); //调整后总额
//			if (-adjustCount > beforeAdjustCount - usedAmount) { //时海云要求，可用数量不做控制，只需要控制总数量大于0即可
//				alert("调减数量超出剩余数量");
//				adjustCount = 0;
//				$(that).val(0);
// 			}
			if (-adjustCount > beforeAdjustCount) {
				alert("调整后总数量小于0");
				adjustCount = 0;
				$(that).val(0);
			}

			$(that).parent().parent().find("input[name=afterAdjustCount]").val(beforeAdjustCount + adjustCount);
			if (beforeAdjustCount + adjustCount == 0) {
				$(that).parent().parent().find("input[name=afterUnitPrice]").val(afterAdjustMoney);
			} else {
				$(that).parent().parent().find("input[name=afterUnitPrice]").val(parseFloat(afterAdjustMoney/(beforeAdjustCount + adjustCount)).toFixed(2));
			}
			countMoney(); //金额合计
		}
		function addCountBlur(that) {
			var adjustCount = 0;
			if ($(that).val() != "" && $(that).val() != null && $(that).val() > 0) {
				adjustCount = parseInt($(that).val()); //调整数量
			} else {
				$(that).val(0);
			}
			var unit = $(that).parent().parent().find("input[name=beforeUnitPrice]").val() - 0;
			$(that).parent().parent().find("input[name=beforeAdjustMoney]").val(parseFloat(unit * adjustCount).toFixed(2));
			countMoney(); //金额合计
		}

		/** 调整金额失去焦点时计算联动效果 */
		function moneyBlur(that) {
			var adjustMoney = 0;
			if ($(that).val() != "" && $(that).val() != null) {
				adjustMoney = $(that).val() - 0; //调整金额
			} else {
				$(that).val(0);
			}
			var usedSumMoney = $(that).next().val() - 0; //已用金额
			var afterAdjustCount = parseInt($(that).parent().parent().find("input[name=afterAdjustCount]").val()); //调整后数量
			var beforeAdjustMoney = $(that).parent().parent().find("input[name=beforeAdjustMoney]").val() - 0; //调整前总额
			if (-adjustMoney > beforeAdjustMoney - usedSumMoney) {
				alert("调减金额超出剩余金额");
				adjustMoney = 0;
				$(that).val(0.00);
			}
			$(that).parent().parent().find("input[name=afterAdjustMoney]").val(beforeAdjustMoney + adjustMoney);
			if (afterAdjustCount == 0) {
				$(that).parent().parent().find("input[name=afterUnitPrice]").val(beforeAdjustMoney + adjustMoney);
			} else {
				$(that).parent().parent().find("input[name=afterUnitPrice]").val(parseFloat((beforeAdjustMoney + adjustMoney)/afterAdjustCount).toFixed(2));
			}

			var specialAdjustMark = $(that).parent().parent().find("input[name=specialAdjustMark]").val();
			countMoney(specialAdjustMark); //金额合计
		}
		function unitBlur(that) {
			var unit = 0;
			if ($(that).val() != "" && $(that).val() != null && $(that).val() > 0) {
				unit = $(that).val() - 0; //调整金额
			} else {
				$(that).val(0);
			}
			var afterAdjustCount = $(that).parent().parent().find("input[name=beforeAdjustCount]").val(); //调整后数量
			if (afterAdjustCount == 0) {
				$(that).parent().parent().find("input[name=beforeAdjustMoney]").val(unit);
			} else {
				$(that).parent().parent().find("input[name=beforeAdjustMoney]").val(parseFloat(unit * afterAdjustCount).toFixed(2));
			}
			countMoney(); //金额合计
		}

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

		/** 计算调整金额并赋值 */
		function getMoney() {
			var iamoney = 0.0;
			if ($("#assetsBudgetType").val() == "新增") {
				$(".getMoney").find("input[name=beforeAdjustMoney]").each(function () {
					iamoney += $(this).val() - 0;
				});
			} else {
				$(".getMoney").find("input[name=adjustMoney]").each(function () {
					iamoney += $(this).val() - 0;
				});
			}
			$("#iamoney").val(iamoney);
		}

		//提交
		$("#saveBtn").click(function () {
			if ($("#adjustReason").val().trim() == "") {
				alert("调整原因不能为空！");
				return;
			}
			if ($("#adjustReason").val().trim().length > 100) {
				alert("调整原因不能大于100！");
				return;
			}
            var flag = false;
            /*判断项目间调整，调入金额等于调出金额*/
            if ($("#assetsBudgetType").val() == "项目间调整") {
                var inmoney = 0.0;
                var outmoney = 0.0;
                $("#tbody_in").find("input[name=adjustMoney]").each(function () {
                    inmoney += $(this).val() - 0;
                    if ($(this).val() - 0 == 0) {
                        flag = true;
                        return;
                    }
                });
                $("#tbody_out").find("input[name=adjustMoney]").each(function () {
                    outmoney += $(this).val() - 0;
                    if ($(this).val() - 0 == 0) {
                        flag = true;
                        return;
                    }
                });
                if (inmoney + outmoney != 0) {
                    alert("调入金额总和不等于调出金额总和,请重新调整");
                    return;
                }
            } else if ($("#assetsBudgetType").val() == "新增") {
                /*判断新增时数量、单价和总额是否合法*/
                $("#tbody_add").find("input[name=beforeUnitPrice]").each(function () {
                    if ($(this).val() - 0 == 0) {
                        flag = true;
                        return;
                    }
                });
                $("#tbody_add").find("input[name=beforeAdjustMoney]").each(function () {
                    if ($(this).val() - 0 == 0) {
                        flag = true;
                        return;
                    }
                });

            } else {
                $("#tbody_adjust").find("input[name=adjustMoney]").each(function () {
                    if ($(this).val() - 0 == 0) {
                        flag = true;
                        return;
                    }
                });
            }
            if (flag) {
                alert("新增时单价和年度预算总额必须大于0;\n追加/调减和项目间调整时调整金额不能为0\n");
                return;
            }
			/*获取审批金额*/
			getMoney();
			//判断修改后调整金额是否发生改变
			var changeAmount = "Y";
			if ($("#iamoneyBefore").val() - $("#iamoney").val() == 0) {
				changeAmount = "N";
			}
			//获取审批流程号
			var typeId = "";
			if ('${budgetAdjustment.assetsBudgetType}' == "项目间调整") {
				typeId = "77048";
			} else {
				typeId = "77047";
			}
			showAgreeDialog('${budgetAdjustment.document}', typeId, changeAmount);
		});
		//审批意见弹窗
		function showAgreeDialog(document, typeId, changeAmount) {
			var url = "${dynamicURL}/workflow/showUpdateCommitDialog?parentWindowId=adjustBudget&document=" + document + "&typeId=" + typeId + "&changeAmount=" + changeAmount;
			var dlg = new dg.curWin.$.dialog({ id: 'agreeDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
			dlg.ShowDialog();
			return dlg;
		}
		function commitAgree(isMail, isPhoneMessage, idea, activityId) {
			$('#isMail').val(isMail);
			$('#isPhoneMessage').val(isPhoneMessage);
			$('#idea').val(idea);

			var postObject = $('#budgetAdjustmentForm').serializeObject();
			/*跨步提交*/
			postObject.activityId = activityId;
			/*是否草稿为N*/
			postObject.draft = 'N';

			var budgetAdjustmentRequest;
			var detailLen = 0;
			$(".trCount").each(function () {
				detailLen++;
			});
			if (detailLen == 0) {
				alert("预算调整明细至少一条");
				return;
			} else if (detailLen == 1) {
				budgetAdjustmentRequest = JSON.stringify({"budgetAdjustment": postObject, "budgetAdjustmentDetail": $("#detailForm").serializeObject()});
			} else {
				budgetAdjustmentRequest = JSON.stringify({"budgetAdjustment": postObject, "budgetAdjustmentDetailArray": $("#detailForm").serializeObject()});
			}


			$('#saveBtn').val('提交...');
			$("#saveBtn").attr("disabled", true);
			$.ajax({
				type: "POST",
				url: "${dynamicURL}/budgetAdjustment/api/updateBudgetAdjustment",
				contentType: "application/json",
				data: budgetAdjustmentRequest,
				success: function (data) {
					/*if (data.success) {
						alert("审批成功");
						dg.curWin.location.reload();
					} else {
						$('#saveBtn').val('修改');
						$("#saveBtn").attr("disabled", false);
						alert(data.errorMessages.join());
					 }*/
					if (data.success) {
						alert("修改成功!");
						dg.curWin.location = dg.curWin.location;
					} else {
						var str = data.errorMessages;
						alert(str);
						var reg = new RegExp("^.*修改成功.*$");
						if (reg.test(str)) {
							dg.curWin.location = dg.curWin.location;
						}

						$('#saveBtn').val('修改');
						$("#saveBtn").attr("disabled", false);
					}
				}
			});
		}


		//提交
		function submitAdjust(that, draft) {
            var flag = false;
            /*判断项目间调整，调入金额等于调出金额*/
            if ($("#assetsBudgetType").val() == "项目间调整") {
                var inmoney = 0.0;
                var outmoney = 0.0;
                $("#tbody_in").find("input[name=adjustMoney]").each(function () {
                    inmoney += $(this).val() - 0;
                    if ($(this).val() - 0 == 0) {
                        flag = true;
                        return;
                    }
                });
                $("#tbody_out").find("input[name=adjustMoney]").each(function () {
                    outmoney += $(this).val() - 0;
                    if ($(this).val() - 0 == 0) {
                        flag = true;
                        return;
                    }
                });
                if (inmoney + outmoney != 0) {
                    alert("调入金额总和不等于调出金额总和,请重新调整");
                    return;
                }
            } else if ($("#assetsBudgetType").val() == "新增") {
                /*判断新增时数量、单价和总额是否合法*/
                $("#tbody_add").find("input[name=beforeUnitPrice]").each(function () {
                    if ($(this).val() - 0 == 0) {
                        flag = true;
                        return;
                    }
                });
                $("#tbody_add").find("input[name=beforeAdjustMoney]").each(function () {
                    if ($(this).val() - 0 == 0) {
                        flag = true;
                        return;
                    }
                });

            } else {
                $("#tbody_adjust").find("input[name=adjustMoney]").each(function () {
                    if ($(this).val() - 0 == 0) {
                        flag = true;
                        return;
                    }
                });
            }
            if (flag) {
                alert("新增时单价和年度预算总额必须大于0;\n追加/调减和项目间调整时调整金额不能为0\n");
                return;
            }
			/*获取调整金额*/
			getMoney();

			var budgetAdjustmentRequest;
			var detailLen = 0;
			/*添加是否草稿*/
			var postObject = $('#budgetAdjustmentForm').serializeObject();
			postObject.draft = draft;
			/*明细行数*/
			$(".trCount").each(function () {
				detailLen++;
			});
			if (detailLen == 0) {
				alert("预算调整明细至少一条");
				return;
			} else if (detailLen == 1) {
				budgetAdjustmentRequest = JSON.stringify({
					"budgetAdjustment": postObject,
					"budgetAdjustmentDetail": $("#detailForm").serializeObject()
				});
			} else {
				budgetAdjustmentRequest = JSON.stringify({
					"budgetAdjustment": postObject,
					"budgetAdjustmentDetailArray": $("#detailForm").serializeObject()
				});
			}
			$(that).val("提交ing");
			$(that).attr('disabled',true);
			$.ajax({
				url: "${dynamicURL}/budgetAdjustment/api/updateBudgetAdjustment",
				type: "POST",
				contentType:"application/json",
				data: budgetAdjustmentRequest,
				success: function (data) {
					if(data.success){
						alert("提交成功！");
						//dg.cancel();
					} else {
						alert(data.errorMessages.join());
						$(that).val("保存草稿");
						$(that).attr('disabled',false);
					}
					dg.curWin.location.reload();
				}
			});
		}


		$(function () {
			//返回关闭申请弹窗
			$("#returnBtn").click(function(){
				dg.cancel();
			});

			//导入和模版下载窗口
			var ew = new EasyWindow();
			ew.initEasyWindow("预算调整数据导入", $("#assetsBudgetType").val(), "${dynamicURL}/budgetAdjustmentDetail/api/budgetAdjustmentDetailImport", importData);
			$('.btnImport').click(function() {
				btnflag = true;
				ew.showEasyWindow();
			});
			$('#outImport').click(function() {
				btnflag = false;
				ew.showEasyWindow();
			});
			/*查询附件*/
			$.ajax({
				url:'${dynamicURL}/file/getFilesByDocument',
				data:{'document':$("#document").val()},
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
			/*附件上传下载*/
			$('#btnUpload').click(function () {
				var fileElementId =$(this).prev().attr('id');
				var file = $('#fileToUpload').val();
				if (file == "") {
					alert("请选择需要上传的文件！");
					return false;
				}

				var documentNo = $('#document').val();
				if ("" == documentNo) {
					alert('单据号不可为空');
					return false;
				}

				uploadFile('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}', documentNo, $('#uploadTable > tbody'),fileElementId);

			});
			$('#btnDelFile').click(function () {
				deleteFiles('${dynamicURL}', "uploadTable");
			});


			$("td[isMoney='Y']").each(function(){
				if($(this).html()!=""){
					$(this).html(fmoney($(this).html(),2));
				};
			});
		});
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