<%--
  Created by IntelliJ IDEA.
  User: Wangkaixuan
  Date: 2017/3/24
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <title>预算调整审批</title>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
    <link rel="Stylesheet" type="text/css" href="${staticURL}/assetallocation/css/allocationapplicationapply.css">
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
<div id="Div2" style="margin-left: 0px; margin-top: 0px;">

    <c:choose>
        <c:when test="${budgetAdjustment.approvalState == '等待审批'}">
            <input type="button" value="同意" id="agreeBtn"  class="btn">
            <input type="button" value="退回" id="denyBtn"  class="btn">
        </c:when>
        <c:when test="${budgetAdjustment.approvalState == '正在审批'}">
            <input type="button" value="同意" id="agreeBtn"  class="btn">
            <input type="button" value="退回" id="denyBtn"  class="btn">
            <input type="button" value="弃审" id="dropBtn" class="btn"/>
        </c:when>
        <c:when test="${budgetAdjustment.approvalState == '已完成'}">
            <input type="button" value="弃审" id="dropBtn" class="btn"/>
        </c:when>
        <c:when test="${budgetAdjustment.approvalState == '退回'}">
            <input type="button" value="弃审" id="dropBtn" class="btn"/>
        </c:when>
        <c:otherwise>
            <input type="button" id="dropBtn" class="btn" value="弃审" />
        </c:otherwise>
    </c:choose>
    <input type="button" value="返回" onclick="dg.cancel();"  class="btn">
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
<form id="budgetAdjustmentForm">
    <table border="0" cellpadding="3px" cellspacing="0" style="width: 95%;">

        <tr>
            <td class="td_right"><span class="span_color">*</span>申请流程单号:</td>
            <td class="td_left">
                <input type="hidden" name='budgetId' value="${budgetAdjustment.budgetId }">
                <input type="hidden" name='userName' value="${budgetAdjustment.userName }">
                <input type="hidden" name='userCode' value="${budgetAdjustment.userCode }">
                <input type="hidden" name="companyCode" value="${budgetAdjustment.companyCode }">
                <input type="hidden" name="departmentCode" value="${budgetAdjustment.costCenterCode }">
                <input type="hidden" name="costCenterCode" value="${budgetAdjustment.departmentCode }">
                <input type="hidden" name="callInDepartmentCode" value="${budgetAdjustment.callInDepartmentCode }">
                <%--<input type="hidden" name="callInDepartmentName" value="${budgetAdjustment.callInDepartmentName }">--%>
                <input type="hidden" name="callOutDepartmentCode" value="${budgetAdjustment.callOutDepartmentCode }">
                <%--<input type="hidden" name="callOutDepartmentName" value="${budgetAdjustment.callOutDepartmentName }">--%>
                <input type="hidden" name="iamoney" value="${budgetAdjustment.iamoney }">

                <input name="isMail" id="isMail"  type="hidden" value="" />
                <input name="isPhoneMessage" id="isPhoneMessage"  type="hidden" value="" />
                <input name="idea" id="idea"  type="hidden" value="" />
                <input name="document" type="text" value="${budgetAdjustment.document}" readonly="readonly"
                       id="document" class="td_input"/>
            </td>
            <td class="td_right">所属部门:</td>
            <td class="td_left">
                <input class="td_input" name="departmentName" type="text" readonly="readonly"
                       value="${budgetAdjustment.departmentName}"/>
            </td>
            <td class="td_right">归属公司:</td>
            <td class="td_left">
                <input class="td_input" name="companyName" type="text" readonly="readonly"
                       value="${budgetAdjustment.companyName}"/>
            </td>

        </tr>
        <tr>
            <td class="td_right">申请人:</td>
            <td class="td_left">
                <input name="" type="text" value="${budgetAdjustment.userName}" readonly="readonly" class="td_input"/>
            </td>
            <td class="td_right">申请日期:</td>
            <td class="td_left">
                <input class="td_input" type="text" readonly="readonly"
                       value="<fmt:formatDate value="${budgetAdjustment.applicationDate}"  pattern="yyyy-MM-dd"/>"/>
                <input name="" type="hidden" value="${budgetAdjustment.applicationDate}"/>
            </td>
            <td class="td_right">预算调整类型:</td>
            <td class="td_left">
                <input  type="text" name="assetsBudgetType" value="${budgetAdjustment.assetsBudgetType}" readonly="readonly" class="td_input">
            </td>
        </tr>
        <tr>
            <td class="td_right"><span class="span_color">*</span>调整原因:</td>
            <td class="td_left" colspan="5">
                <input name="adjustReason" value="${budgetAdjustment.adjustReason }" id="adjustReason" class="td_input"
                       readonly style="width: 99.5%"/>
            </td>
        </tr>
        <tr id="depTr">
            <td class="td_right">调入部门:</td>
            <td class="td_left" id="depIn">
                <input  type="text" name="callInDepartmentName" value="${budgetAdjustment.callInDepartmentName}" readonly="readonly" class="td_input">
            </td>
            <c:if test="${budgetAdjustment.assetsBudgetType == '项目间调整'}">
                <td class="td_right">调出部门:</td>
                <td class="td_left" id="depOut">
                    <input  type="text" name="callOutDepartmentName" value="${budgetAdjustment.callOutDepartmentName}" readonly="readonly" class="td_input">
                </td>
            </c:if>
        </tr>
    </table>
</form>

<c:choose>
    <c:when test="${budgetAdjustment.assetsBudgetType == '新增' || budgetAdjustment.assetsBudgetType == '追加/调减'}">
        <div id="commonDiv" >
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
                            <tbody id="tbody_add">
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
                                </c:forEach>
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
                            <tbody id="tbody_adjust" >
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
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:when>
    <c:otherwise>
        <div id="specialDiv">
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
                    <tbody id="tbody_in">
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
                    <tbody id="tbody_out">
                    <c:forEach items="${budgetAdjustment.budgetAdjustmentDetailList}" var="detail">
                        <c:if test="${detail.specialAdjustMark == 'out'}">
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
            <td>
                <input type="file" name="multipartFile" id="fileToUpload" class="colorblur"
                       onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                <input type="submit" name="btnUpload" value="上传" id="btnUpload" class="btn">
                <input type="submit" name="btnDelFile" value="删除" id="btnDelFile" class="btn"></td>
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
<%--<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>--%>
<%--<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>--%>
<%--<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>--%>
<%--<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>--%>
<script type="text/javascript">
    var dg = frameElement.lhgDG;

    $("#agreeBtn").click(function (){
        var typeId = "";
        if ('${budgetAdjustment.assetsBudgetType}' == "项目间调整") {
            typeId = "77048";
        } else {
            typeId = "77047";
        }
        showAgreeDialog('${budgetAdjustment.document}', typeId);
    });
    $("#denyBtn").click(function(){
        var typeId = "";
        if ('${budgetAdjustment.assetsBudgetType}' == "项目间调整") {
            typeId = "77048";
        } else {
            typeId = "77047";
        }
        showDenyDialog('${budgetAdjustment.document}', typeId);
    });
    $("#dropBtn").click(function(){
        showDropDialog();
    });
    function showAgreeDialog(document, typeId) {
        var url = "${dynamicURL}/workflow/showAgreeDialog?parentWindowId=approve&document=" + document + "&typeId=" + typeId;
        var dlg = new dg.curWin.$.dialog({ id: 'agreeDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();
        return dlg;
    }
    function showDenyDialog(document, typeId) {
        var url = "${dynamicURL}/workflow/showDenyDialog?parentWindowId=approve&document=" + document + "&typeId=" + typeId;

        var dlg = new dg.curWin.$.dialog({ id: 'denyDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();
        return dlg;
    }
    function showDropDialog() {
        var url = "${dynamicURL}/workflow/showDropDialog?parentWindowId=approve";

        var dlg = new dg.curWin.$.dialog({ id: 'dropDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();
        return dlg;
    }
    function commitAgree(isMail, isPhoneMessage, idea, activityId) {
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
        $('#idea').val(idea);

        var postObject = $('#budgetAdjustmentForm').serializeObject();
        postObject.activityId = activityId;
        //console.log(postObject);
        $('#agreeBtn').val('提交...');
        $("#agreeBtn").attr("disabled", true);
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/budgetAdjustment/api/agreeBudgetAdjustment",
            contentType: "application/json",
            data: JSON.stringify(postObject),
            success: function (data) {
                /*if (data.success) {
                    alert("审批成功");
                    dg.curWin.location.reload();
                } else {
                    $('#agreeBtn').val('同意');
                    $("#agreeBtn").attr("disabled", false);
                    alert(data.errorMessages.join());
                 }*/

                if (data.success) {
                    alert("审批成功!");
                    dg.curWin.location = dg.curWin.location;
                } else {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*审批成功.*$");
                    if (reg.test(str)) {
                        dg.curWin.location = dg.curWin.location;
                    }
                    $('#btnDrop').val('同意');
                    $("#btnDrop").attr("disabled", false);
                }
            }
        });
    }
    function commitDeny(isMail, isPhoneMessage, idea, activityId) {
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
        $('#idea').val(idea);
        $('#btnDeny').val('提交...');
        $("#btnDeny").attr("disabled", true);

        var postObject = $('#budgetAdjustmentForm').serializeObject();
        postObject.activityId = activityId;
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/budgetAdjustment/api/denyBudgetAdjustment",
            contentType: "application/json",
            data: JSON.stringify(postObject),
            success: function (data) {
                /*if (data.success) {
                    alert("审批成功");
                    dg.curWin.location.reload();
                } else {
                    $('#btnDeny').val('退回');
                    $("#btnDeny").attr("disabled", false);
                    alert(data.errorMessages.join());
                 }*/

                if (data.success) {
                    alert("审批成功!");
                    dg.curWin.location = dg.curWin.location;
                } else {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*审批成功.*$");
                    if (reg.test(str)) {
                        dg.curWin.location = dg.curWin.location;
                    }
                    $('#btnDrop').val('退回');
                    $("#btnDrop").attr("disabled", false);
                }
            }
        });
    }
    function commitDrop(isMail, isPhoneMessage, idea) {
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
        $('#idea').val(idea);

        $('#btnDrop').val('提交...');
        $("#btnDrop").attr("disabled", true);
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/budgetAdjustment/api/dropBudgetAdjustment?"+$('#budgetAdjustmentForm').serialize(),
            contentType: "application/json; charset=gb2312",
            success: function (data) {
                /*if (data.success) {
                    alert("审批成功");
                    dg.curWin.location.reload();
                } else {
                    $('#btnDrop').val('弃审');
                    $("#btnDrop").attr("disabled", false);
                    alert(data.errorMessages.join());
                 }*/
                if (data.success) {
                    alert("审批成功!");
                    dg.curWin.location = dg.curWin.location;
                } else {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*审批成功.*$");
                    if (reg.test(str)) {
                        dg.curWin.location = dg.curWin.location;
                    }
                    $('#btnDrop').val('弃审');
                    $("#btnDrop").attr("disabled", false);
                }
            }
        });
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

    $('#btnUpload').click(function () {
        var fileElementId = $(this).prev().attr('id');
        var file = $('#fileToUpload').val();
        if (file == "") {
            alert("请选择需要上传的文件！");
            return false;
        }

        var document = $('#document').val();
        if ("" == document) {
            alert('单据号不可为空');
            return false;
        }

        uploadFile('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}', document, $('#uploadTable > tbody'), fileElementId);

    });

    $('#btnDelFile').click(function () {
        deleteFiles('${dynamicURL}', "uploadTable");
    });

    $(function () {
        $.ajax({
            url:'${dynamicURL}/file/getFilesByDocument',
            data:{'document':'${budgetAdjustment.document}'},
            dataType:'json',
            type:'POST',
            success:function(data){
                if(data.length > 0 ){
                    $('#uploadTable .noItems').remove();
                    $.each(data,function(i,item){
                        var uploaduser = '${ sessionScope._user_nick_name}';
                        if (uploaduser == item.createUserName) {
                            $('#uploadTable > tbody').append('<tr id="tr" class="' + item.fileuuid + '" style="white-space:nowrap;">'
                                    + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '"name=documentId   />'
                                    + '</td><td scope="col">' + item.fileName
                                    + '</td><td scope="col">' + item.createUserName
                                    + '</td><td style="width:9%">' + item.createDate
                                    + '</td><td align="center" style="width:10%"><a href="' + '${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                                    + '</td></tr>');
                        } else {
                            $('#uploadTable > tbody').append('<tr id="tr" class="' + item.fileuuid + '" style="white-space:nowrap;">'
                                    + '<td style="text-align:center;"><input type="checkbox" uuid="" name=documentId   />'
                                    + '</td><td scope="col">' + item.fileName
                                    + '</td><td scope="col">' + item.createUserName
                                    + '</td><td style="width:9%">' + item.createDate
                                    + '</td><td align="center" style="width:10%"><a href="' + '${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                                    + '</td></tr>');
                        }

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

</script>
</body>
</html>