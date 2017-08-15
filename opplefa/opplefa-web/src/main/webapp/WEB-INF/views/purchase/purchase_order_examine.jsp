<%--
  Created by IntelliJ IDEA.
  User: ykz
  Date: 17/1/16
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>采购订单</title>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>

<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
    <div id="tabletitle"
         style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
        采购订单
    </div>
</div>
<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
    <input type="button" id="btnAgree" class="btn" value="同意"/>
    <input type="button" id="btnDeny" class="btn" value="退回"/>
    <c:choose>
        <c:when test="${purchaseOrder.approvalState == '等待审批'}"> </c:when>
        <c:otherwise>
            <input type="button" id="btnDrop" class="btn" value="弃审"/>
        </c:otherwise>
    </c:choose>
    <input type="submit" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn"/>
    <c:if test="${isLastApprovalStep=='Y'}">
        <input type="button" name="btnSendSAP" value="传SAP采购订单" onclick="sendSAP();" id="btnSendSAP" class="btn"/>
    </c:if>
    <input  type="button" value="打印" class="btn" onclick="javascript:stamp('${purchaseOrder.cdocument}','打印');"/>
</div>
<div id="" style="margin-left: 0px; margin-top: 0px;">
    <app:approvalTag billCode="${purchaseOrder.cdocument}" typeId="77021"></app:approvalTag>
</div>
<div style="width:100%;margin-top: 10px;">

    <div id="purchaseTab" class="easyui-tabs">
        <div title="订单信息" id="tabsa" style="overflow:auto; padding:20px;min-width: 800px;">
            <div>
                <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle"></div>
                            基本信息
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%--<form id="purchaseOrderFormHeader">--%>
                            <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
                                <tbody>
                                <tr>
                                    <td style="text-align: right; width: 90px;">单据号:</td>
                                    <form id="purchaseOrderFormHeader">
                                        <input name="isMail" id="isMail" class="controlhidden" type="text"/>
                                        <input name="isPhoneMessage" id="isPhoneMessage" class="controlhidden"
                                               type="text"/>
                                        <input name="idea" id="idea" class="controlhidden" type="text"/>
                                        <input name="activityId" id="activityId" class="controlhidden" type="text"/>
                                        <td>
                                            <input class="searchinput" readonly name="cdocument" type="text"
                                                   value="${purchaseOrder.cdocument}" id="cdocument">
                                            <input name="issap" id="issap" value="${purchaseOrder.issap}" type="hidden">
                                        </td>
                                    </form>
                                    <td style="text-align: right; width: 90px;"><font color="red">*</font>所属公司:</td>
                                    <td>
                                        <span id="spanCompany">
                                            <input readonly='readonly' class='searchinput' name='companyName'
                                                   type='text' value='${purchaseOrder.companyName}' id='txtCompany'>
                                            <input name='companyCode' type='hidden' value='${purchaseOrder.companyCode}'
                                                   id='txtCompanyCode'>
                                        </span>
                                    </td>
                                    <td style="text-align: right; width: 90px;">采购员:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly"
                                               value='${purchaseOrder.buyerName}' name="buyerName" type="text" value=""
                                               id="txtBuyerName">
                                        <input class="searchinput" readonly="readonly"
                                               value='${purchaseOrder.buyerCode}' name="buyerCode" type="hidden"
                                               value="" id="txtBuyerCode">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right;">申请日期:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" name="applyDate" type="text"
                                               value="<fmt:formatDate value='${purchaseOrder.applyDate}'  pattern='yyyy-MM-dd'/>"
                                               id="txtApplyDate">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>平台:</td>
                                    <td class="tdtext">
                                        <input type="text" class="searchinput" value='${purchaseOrder.platform}'
                                               readonly name="platform" id="txtPlatform"/>
                                    </td>
                                    <td style="text-align: right;"><font color="red">*</font>采购组:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly name="purchasingGroup" type="text"
                                               value="${purchaseOrder.purchasingGroup}" id="purchasingGroup">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><font color="red">*</font>归口/预算部门:</td>
                                    <td>
                                        <input class="searchinput" readonly name="budgetDepartmentName" type="text"
                                               value="${purchaseOrder.budgetDepartmentName}" id="budgetDepartmentName">
                                        <input name="budgetDepartmentCode" value="${purchaseOrder.budgetDepartmentCode}"
                                               type="hidden" id="txtYsssbm" class="inputbtnRefViewGray">
                                    </td>
                                    <td style="text-align: right;"><font color="red">*</font>资产类型:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly name="assetType" type="text"
                                               value="${purchaseOrder.assetType}" id="assetType">
                                    </td>
                                    <td style="text-align: right;"><font color="red">*</font>采购组织:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly name="procurementOrganization" type="text"
                                               value="${purchaseOrder.procurementOrganization}"
                                               id="procurementOrganization">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><font color="red">*</font>成本中心:</td>
                                    <td class="tdtext">
								<span id="spanCostCenter">
								<input readonly="readonly" class="searchinput" name="costCenter" type="text"
                                       value="${purchaseOrder.costCenter}" id="txtCostCenter">
							</span>
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>工厂代码:</td>
                                    <td class="tdtext">
                                        <input readonly="readonly" class="searchinput" name="factoryCode" type="text"
                                               value="${purchaseOrder.factoryCode}" id="factoryCode">

                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>订单类别:</td>
                                    <td class="tdtext">
                                        <input readonly class="searchinput" name="orderType" type="text"
                                               value="${purchaseOrder.orderType=='ZAB'?'固定资产(ZAB)':'费用(ZFB)'}"
                                               id="orderType">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><font color="red">*</font>订单号(SAP):</td>
                                    <td>
                                        <input class="searchinput" readonly name="purchaseOrderSapId" type="text"
                                               value="${purchaseOrder.purchaseOrderSapId}" id="purchaseOrderSapId">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>税码/税率:</td>
                                    <td class="tdtext">
                                        <input readonly class="searchinput" name="taxRate" type="text"
                                               value="${purchaseOrder.taxRateCode} (<fmt:formatNumber type="number" value="${purchaseOrder.taxRate*100}" maxFractionDigits="0"/>%进项税,中国)"
                                               id="taxRate">
                                    </td>
                                    <td style="text-align: right"></td>
                                    <td class="tdtext"></td>
                                </tr>
                                </tbody>
                            </table>
                            </form>
                        </td>
                    </tr>
                    </tbody>
                </table>

                </br>
                <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width: 100%">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle"></div>
                            供应商信息
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
                                <tbody>
                                <tr>
                                    <td style="text-align: right; width: 90px;">供应商代码:</td>
                                    <td>
                                        <input style="width: 100px;" readonly name="supplierCode"
                                               value="${purchaseOrder.supplierCode}" type="text" id="supplierCode"
                                               class="inputbtnRefViewGray"></span><input id='btnRefClear'
                                                                                         class='btnRefView'
                                                                                         title='点击清空参照文本框内容'
                                                                                         type='button' value='X'><input
                                            id="butSupplierShow" class='btnRefView' title='点击打开参照文本框内容' type='button'
                                            value='…'>
                                    </td>
                                    <td style="text-align: right; width: 90px;">供应商名称:</td>
                                    <td>
                                        <input class="searchinput" readonly name="supplierName" type="text"
                                               value="${purchaseOrder.supplierName}" id="supplierName">
                                    </td>
                                    <td style="text-align: right; width: 90px;">邮编:</td>
                                    <td>
                                        <input class="searchinput" readonly name="zipCode" type="text"
                                               value="${purchaseOrder.zipCode}" id="zipCode">
                                        <input class="searchinput" readonly name="paymentTypeName" type="hidden"
                                               value="${purchaseOrder.paymentTypeName}" id="paymentTypeName">
                                        <input class="searchinput" readonly name="paymentTypeCode" type="hidden"
                                               value="${purchaseOrder.paymentTypeCode}" id="paymentTypeCode">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">开户行:</td>
                                    <td>
                                        <input class="searchinput" readonly name="bankName" type="text"
                                               value="${purchaseOrder.bankName}" id="bankName">
                                        <input class="searchinput" readonly name="bankCode" type="hidden"
                                               value="${purchaseOrder.bankCode}" id="bankCode">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>银行账号:</td>
                                    <td>
                                        <input class="searchinput" readonly name="bankNumber" type="text"
                                               value="${purchaseOrder.bankNumber}" id="bankNumber">
                                    </td>
                                    <td style="text-align: right">联行号:</td>
                                    <td>
                                        <input class="searchinput" readonly name="contactNumber" type="text"
                                               value="${purchaseOrder.contactNumber}" id="contactNumber">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">联系人:</td>
                                    <td>
                                        <input class="searchinput" readonly name="contacts" type="text"
                                               value="${purchaseOrder.contacts}" id="contacts">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>电话:</td>
                                    <td>
                                        <input class="searchinput" readonly name="telephone" type="text"
                                               value="${purchaseOrder.telephone}" id="telephone">
                                    </td>
                                    <td style="text-align: right">公司地址:</td>
                                    <td>
                                        <input class="searchinput" readonly name="companyAddress" type="text"
                                               value="${purchaseOrder.companyAddress}" id="companyAddress">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">公司具体名称:</td>
                                    <td>
                                        <input class="searchinput" readonly name="companySpecificName" type="text"
                                               value="${purchaseOrder.companySpecificName}" id="companySpecificName">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>币种:</td>
                                    <td>
                                        <input class="searchinput" readonly name="currencyName" type="text"
                                               value="${purchaseOrder.currencyName}" id="currencyName">
                                    </td>
                                    <td style="text-align: right;"><font color="red">*</font>汇率:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly name="exchangeRate" type="text"
                                               value="${purchaseOrder.exchangeRate}" id="txtExchangeRate">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">国家:</td>
                                    <td>
                                        <input class="searchinput" readonly name="country" type="text"
                                               value="${purchaseOrder.country}" id="country">
                                    </td>
                                    <td style="text-align: right">城市:</td>
                                    <td>
                                        <input class="searchinput" readonly name="city" type="text"
                                               value="${purchaseOrder.city}" id="city">
                                    </td>
                                    <td style="text-align: right"></td>
                                    <td class="tdtext">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><font color="red">*</font>送货地址:</td><!--  -->
                                    <td colspan="5">
                                        <input size="110" name="deliveryAddress" type="text"
                                               value="${purchaseOrder.deliveryAddress}" id="deliveryAddress">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">抬头文本说明:</td>
                                    <td colspan="5">
                                        <input size="110" name="headerTextDescription" type="text"
                                               value="${purchaseOrder.headerTextDescription}"
                                               id="headerTextDescription">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">采购员备注:</td><!--  -->
                                    <td colspan="5">
                                        <input size="110" name="buyerRemark" type="text"
                                               value="${purchaseOrder.buyerRemark}" id="buyerRemark">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>

                </br>
                <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle"></div>
                            订单明细信息
                        </td>
                    </tr>
                    <!-- <tr>
                        <td class="titlefontsize" align="left">
                            <input id="addRows" type="button" value="选择明细" >
                            <input id="delRows" type="button" value="删行">
                        </td>
                    </tr> -->
                    <tr>
                        <td colspan="4">
                            <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                                   style="table-layout: fixed;">
                                <tbody>
                                <tr>
                                    <td class="tdtitle" style="text-align: right" colspan="2">
                                        <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                            <div>
                                                <table id="table_detail" class="adminlist" cellspacing="0" rules="all"
                                                       border="1" id="gridDataList" style="border-collapse:collapse;">

                                                    <tr style="white-space:nowrap;">
                                                        <!-- <th scope="col">
                                                            <input id="gvDataList_CheckAll" type="checkbox" name="gvDataList$ctl01$CheckAll"
                                                                   onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_detail');"/>
                                                        </th> -->
                                                        <!-- <th scope="col" class="tdpadding">合并号</th> -->
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>采购申请单号
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red" >*</font>主编码</th>
                                                        <th scope="col" class="tdpadding"><font color="red" >*</font>子编码</th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>资产名称
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>规格参数
                                                        </th>
                                                        <!-- 5 -->
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>功能
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>选配件
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>验收标准
                                                        </th>
                                                        <th scope="col" class="tdpadding">采购数量</th>
                                                        <th scope="col" class="tdpadding">采购单价(含税)</th>
                                                        <th scope="col" class="tdpadding">采购总价(含税)</th>
                                                        <th scope="col" class="tdpadding">采购总价(含税本币)</th>
                                                        <th scope="col" class="tdpadding">净价(原币)</th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>型号规格/品牌
                                                        </th>
                                                        <!-- 10 -->
                                                        <th scope="col" class="tdpadding">约定交货日期</th>
                                                        <th scope="col" class="tdpadding">预算成本中心</th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>质保周期(月)
                                                        </th>
                                                    </tr>
                                                    <tbody id="tbody_detail">
                                                    <c:forEach items="${purchaseOrderDetailModels}" var="pod"
                                                               varStatus="i">
                                                        <tr style="white-space:nowrap;">
                                                            <td class='tdpadding'><a
                                                                    href="javascript:showApplyOrder('${pod.applyOrderId}')">${pod.applyOrderId}</a>
                                                            </td>
                                                            <td class='tdpadding'>${pod.mainAssetCode}</td>
                                                            <td class='tdpadding'>${pod.secondary}</td>
                                                            <td class='tdpadding'>${pod.assetsName}</td>
                                                            <td class='tdpadding'>${pod.specificationParameter}</td>
                                                            <td class='tdpadding'>${pod.functions}</td>
                                                            <td class='tdpadding'>${pod.optionalAccessories}</td>
                                                            <td class='tdpadding'>${pod.acceptanceCriteria}</td>
                                                            <td class='tdpadding' id="tdApplyCount"
                                                                v='${pod.applyCount}'>${pod.applyCount}</td>
                                                            <td class='tdpadding'>${pod.purchaseUnitPrice}</td>
                                                            <td class='tdpadding' id="tdPurchasePrice"
                                                                v='${pod.purchasePrice}'>${pod.purchasePrice}</td>
                                                            <td class='tdpadding' id="tdPurchasePriceLocal"
                                                                v='${pod.purchasePriceLocal}'>${pod.purchasePriceLocal}</td>
                                                            <td class='tdpadding' id="tdNetPrice"
                                                                v='${pod.netPrice}'>${pod.netPrice}</td>
                                                            <td class='tdpadding'>${pod.brand}</td>
                                                            <td class='tdpadding'><fmt:formatDate
                                                                    value="${pod.requirementsDate}"
                                                                    pattern="yyyy-MM-dd"/></td>
                                                            <td class='tdpadding'>${pod.costCenter}</td>
                                                            <td class='tdpadding'>${pod.warrantyPeriod}</td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                    <tr style="white-space:nowrap;">
                                                        <td>合计</td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td id="tdSumApplyCount"></td>
                                                        <td></td>
                                                        <td id="tdSumPurchasePrice"></td>
                                                        <td id="tdSumPurchasePriceLocal"></td>
                                                        <td id="tdSumNetPrice"></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>

                    </tbody>
                </table>
                </br>
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
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
                                    <table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1"
                                           id="gvDataList" style="border-collapse:collapse;">
                                        <thead>
                                        <tr style="white-space:nowrap;">
                                            <th scope="col">
                                                <input type="checkbox" name="gvDataList$ctl01$CheckAll"
                                                       onclick="javascript:SelectAllCheckboxesByTableId(this, 'uploadTable');">
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
                                    <table class="adminlist" cellspacing="0" rules="all" border="1"
                                           style="width:100%;border-collapse:collapse;">
                                        <thead>
                                        <tr style="white-space: nowrap;">
                                            <th scope="col">审批人</th>
                                            <th scope="col">审批日期</th>
                                            <th scope="col">审批结果</th>
                                            <!-- <th scope="col">审批金额</th> -->
                                            <th scope="col">审批意见</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${appHistoryPager.records}" var="appHistory"
                                                   varStatus="status">
                                            <tr style="white-space: nowrap;">
                                                <td class="tdleft">${appHistory.examineUser}</td>
                                                <td class="tdleft"><fmt:formatDate value='${appHistory.examineDate}'
                                                                                   pattern='yyyy-MM-dd HH:mm:ss'/></td>
                                                <td class="tdleft">${appHistory.examineResult}</td>
                                                    <%-- <td class="tdleft">${appHistory.examineMoney}</td> --%>
                                                <td class="tdleft">${appHistory.examineAdvice}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <tag:paginate offset="${appHistoryPager.offset}" total="${appHistoryPager.total}"
                                                  steps="${appHistoryPager.pageSize}" uri="${appHistoryPager.uri}"
                                                  formId=""/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div title="资产明细" data-options="closable:false" style="overflow:auto;padding:20px;">
            <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder">
                <tbody>
                <tr>
                    <td class="titlefontsize" align="left">
                        <div class="imgtitle"></div>
                        资产明细
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                               style="table-layout: fixed;">
                            <tbody>
                            <tr>
                                <td class="tdtitle" style="text-align: right" colspan="2">
                                    <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                        <div>
                                            <table id="table_detail_detail" class="adminlist" cellspacing="0"
                                                   rules="all" border="1" id="gridDataList"
                                                   style="border-collapse:collapse;">
                                                <tr style="white-space:nowrap;">
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>需求单号
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>项目编码
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>预算年度
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>资产名称
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>规格参数
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>功能</th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>选配件</th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>验收标准
                                                    </th>
                                                    <th class='tdpadding' scope="col">需求部门</th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>本次申请数量
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>单位</th>
                                                    <th class="tdpadding" scope="col"><font color="red">*</font>询价单价
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>要求到位日期
                                                    </th>
                                                    <th class='tdpadding' scope="col">预算成本中心</th>
                                                    <th scope="col"><font color="red">*</font>用途说明</th>
                                                </tr>
                                                <tbody>
                                                <c:forEach items="${purchaseApplyMappingModels}" var="pam"
                                                           varStatus="i">
                                                    <tr style="white-space:nowrap;">
                                                        <td class='tdpadding'><a
                                                                href="javascript:showDemandOrder('${pam.demandOrderId}')">${pam.demandOrderId}</a>
                                                        </td>
                                                        <td class='tdpadding'>${pam.projectCode}</td>
                                                        <td class='tdpadding'>${pam.budgetYear}</td>
                                                        <td class='tdpadding'>${pam.assetsName}</td>
                                                        <td class='tdpadding'>${pam.specificationParameter}</td>
                                                        <td class='tdpadding'>${pam.functions}</td>
                                                        <td class='tdpadding'>${pam.optionalAccessories}</td>
                                                        <td class='tdpadding'>${pam.acceptanceCriteria}</td>
                                                        <td class='tdpadding'>${pam.budgetDepartmentName}</td>
                                                        <td class='tdpadding'>${pam.applyCount}</td>
                                                        <td class='tdpadding'>${pam.units}</td>
                                                        <td class='tdpadding'>${pam.inquiryUnitPrice}</td>
                                                        <td class='tdpadding'><fmt:formatDate
                                                                value="${pam.requirementsDate}"
                                                                pattern="yyyy-MM-dd"/></td>
                                                        <td class='tdpadding'>${pam.costCenter}</td>
                                                        <td class='tdpadding'>${pam.useDescription}</td>
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
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div title="付款条件" data-options="closable:false" style="overflow:auto;padding:20px;">
            <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder">
                <tbody>
                <!-- <tr>
                    <td class="titlefontsize" align="left" colspan="4">
                        <input id="addPayRows" type="button" value="增行">
                        <input id="delPayRows" type="button" value="删行">
                    </td>
                </tr> -->
                <tr>
                    <td colspan="4">
                        <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                               style="table-layout: fixed;">
                            <tbody>
                            <tr>
                                <td class="tdtitle" style="text-align: right" colspan="2">
                                    <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                        <div>
                                            <table id="table_pay" class="adminlist" cellspacing="0" rules="all"
                                                   border="1" id="gridDataList" style="border-collapse:collapse;">
                                                <tr style="white-space:nowrap;">
                                                    <th scope="col" class="tdpadding">付款期数</th>
                                                    <th scope="col" class="tdpadding">付款类型</th>
                                                    <th scope="col" class="tdpadding">付款比例(%)</th>
                                                    <th scope="col" class="tdpadding">付款说明</th>
                                                </tr>
                                                <tbody id="payment_tbody">
                                                <c:forEach items="${paymentTerms}" var="pt" varStatus="i">
                                                    <tr class="bodyTr" style="white-space:nowrap;">
                                                        <td class="tdpadding">${pt.nper}</td>
                                                        <td class="tdpadding">${pt.paymentType}</td>
                                                        <td class="tdpadding">${pt.paymentRatio}</td>
                                                        <td class="tdpadding">${pt.paymentRemarks}</td>
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
                    </td>
                </tr>
                </tbody>
                <tr>
                    <td class="titlefontsize" align="left" colspan="4">
                        <p style="margin-left: 10px;">提示：</p>
                        <p style="margin-left: 40px;">1、预付款：根据合同或协议约定，发货前预付或验收前支付。</p>
                        <p style="margin-left: 40px;">2、验收款：验收合格后开具全额发票，根据合同或协议支付一定比例的验收款。</p>
                        <p style="margin-left: 40px;">3、尾款：验收合格后在质保周期内的质保金。</p>
                    </td>
                </tr>
            </table>
        </div>
    </div>


</div>


<!-- 点击増行弹出form -->

<div id="addApplyWindow" name="" class="easyui-window" title="添加明细" data-options="iconCls:'icon-save'"
     style="width:850px;height:440px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">

            <form action="addPurchaseApplyOrderDetail" id="addPurchaseApplyOrderDetailForm">
                <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="70%">
                    <tbody>
                    <tr>
                        <td align="right" class="tdtitle">申请单号:
                        </td>
                        <td class="tdtext">
                            <input name="cdocument" type="text" id="txtApplyOrderId" class="colorblur">
                        </td>
                        <td align="right" class="tdtitle">申请日期:
                        </td>
                        <td class="tdtext">
                            <input name="applyDateStart" type="text" id="txtBeginDate" class="Wdate"
                                   onclick="WdatePicker({skin:'ext'});">~<input name="applyDateEnd" type="text"
                                                                                id="txtEndDate" class="Wdate"
                                                                                onclick="WdatePicker({skin:'ext'});">
                        </td>
                    </tr>
                    <tr>
                        <td align="right" class="tdtitle">申请人:
                        </td>
                        <td class="tdtext">
                            <input name="applyUser" type="text" id="txtApplyUser" class="colorblur">
                        </td>
                        <td class="tdtitle" align="right">
                        </td>
                        <td class="tdtext">
                        </td>
                    </tr>
                    </tbody>
                </table>

            </form>

            <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
                <input type="button" name="btnDetailQuery" value="查询" id="btnDetailQuery" class="btn">
                <input type="button" name="btnDetailSave" value="提交" id="btnDetailSave" class="btn">
            </div>
            <div style="margin-top: 10px">


                <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder">
                    <tbody>

                    <tr>
                        <td colspan="4">
                            <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                                   style="table-layout: fixed;">
                                <tbody>
                                <tr>
                                    <td class="tdtitle" style="text-align: right" colspan="2">
                                        <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                            <div>

                                                <table id="table_add_detail" class="adminlist" cellspacing="0"
                                                       rules="all" border="1" id="gridDataList"
                                                       style="border-collapse:collapse;">

                                                    <tr style="white-space:nowrap;">
                                                        <th scope="col">
                                                            <input id="gvDataList_CheckAll" type="checkbox"
                                                                   name="gvDataList$ctl01$CheckAll"
                                                                   onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_add_detail');"/>
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>采购申请单号
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>主编码
                                                        </th>
                                                        <!-- <th scope="col" class="tdpadding"><font color="red" >*</font>次级</th> -->
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>资产名称
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>规格参数
                                                        </th>
                                                        <!-- 5 -->
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>功能
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>选配件
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>验收标准
                                                        </th>
                                                        <th scope="col" class="tdpadding">申请数量</th>
                                                        <th scope="col" class="tdpadding">采购数量</th>
                                                        <th scope="col" class="tdpadding">询价单价(含税)</th>
                                                        <!-- 10 -->
                                                        <th scope="col" class="tdpadding">要求到位日期</th>
                                                        <th scope="col" class="tdpadding">预算成本中心</th>
                                                    </tr>
                                                    <tbody id="tbody_add_detail">
                                                    </tbody>
                                                </table>

                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>

<div id="addApplyDetailWindow" name="" class="easyui-window" title="明细" data-options="iconCls:'icon-save'"
     style="width:700px;height:280px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
                <input type="button" name="btnDetailSure" value="确定" id="btnDetailSure" class="btn">
            </div>
            <div style="margin-top: 10px">
                <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder">
                    <tbody>
                    <tr>
                        <td colspan="4">
                            <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                                   style="table-layout: fixed;">
                                <tbody>
                                <tr>
                                    <td class="tdtitle" style="text-align: right" colspan="2">
                                        <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                            <div>
                                                <table id="table_add_detail_mapping" class="adminlist" cellspacing="0"
                                                       rules="all" border="1" id="gridDataList"
                                                       style="border-collapse:collapse;">
                                                    <tr style="white-space:nowrap;">
                                                        <th scope="col" class="tdpadding">申请人</th>
                                                        <th scope="col" class="tdpadding">申请部门</th>
                                                        <th scope="col" class="tdpadding">申请数量</th>
                                                        <th scope="col" class="tdpadding">未采购数量</th>
                                                        <th scope="col" class="tdpadding">采购数量</th>
                                                        <th scope="col" class="tdpadding">预算成本中心</th>
                                                        <th scope="col" class="tdpadding">采购需求单号</th>
                                                    </tr>
                                                    <tbody id="tbody_add_detail_mapping">
                                                    </tbody>
                                                </table>

                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="supplierWindow" name="" class="easyui-window" title="选择供应商" data-options="iconCls:'icon-save'"
     style="width:750px;height:380px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">

            <form id="supplierForm">
                <table class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="0">
                    <tr style="text-align: left">
                        <td>供应商编码:</td>
                        <td name=""><input type="text" id="searchSupplierCode" name="supplierCode"></td>
                        <td>供应商名称:</td>
                        <td><input name="supplierName" id="searchSupplierName" type="text"></td>
                    </tr>
            </form>
            <input type="button" value="查询" id="searchSupplier"><input type="button" id="supplierSure" value="确定">
            <table id="supplierBudget" class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0"
                   border="1" cellpadding="6px">
                <thead>
                <tr>
                    <th class='tdpadding'></th>
                    <th class='tdpadding'>供应商名称</th>
                    <th class='tdpadding'>供应商编码</th>
                    <th class='tdpadding'>付款方式</th>
                    <th class='tdpadding'>付款方式编码</th>
                    <th class='tdpadding'>开户行</th>
                    <th class='tdpadding'>开户行编码</th>
                    <th class='tdpadding'>币种名称</th>
                    <th class='tdpadding'>币种编码</th>
                    <th class='tdpadding'>银行账号</th>
                    <th class='tdpadding'>联行号</th>
                    <th class='tdpadding'>联系人</th>
                    <th class='tdpadding'>电话</th>
                    <th class='tdpadding'>公司地址</th>

                </tr>
                </thead>
                <tbody id="supplierBody"></tbody>
            </table>

        </div>

    </div>
</div>

<div id="ysssbmWindow" name="" class="easyui-window" title="预算所属部门" data-options="iconCls:'icon-save'"
     style="width:600px;height:330px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <input type="button" id="ysssbmAdd" value="确定">
            <ul id="tree1"></ul>


        </div>

    </div>
</div>

<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
<link href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css" type="text/css" rel="stylesheet" />
<link href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script> --%>
<script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>

<script type="text/javascript">
    var dg = frameElement.lhgDG;//定义dg 关闭需要

    function showUserDialog() {
        var url = "${dynamicURL}/user/getUserList?parentWindowId=scoreDialog";
        new dg.curWin.$.dialog({
            id: 'listUserDialog',
            title: "选择用户",
            cover: true,
            page: url,
            width: 700,
            height: 400,
            btnBar: false,
            resize: false,
            maxBtn: false
        }).ShowDialog();
    }

    $('#showUserListBtn').click(function () {
        showUserDialog();
    });

    function fillVehicleUser(userCode, userName) {
        $('#userCode').val(userCode);
        $('#userName').val(userName);
    }

    function showAgreeDialog(document, typeId) {
        var url = "${dynamicURL}/workflow/showAgreeDialog?parentWindowId=examineDialog&document=" + document + "&typeId=" + typeId;
        var dlg = new dg.curWin.$.dialog({
            id: 'agreeDialog',
            parent: dg,
            title: '邮件短信提醒',
            cover: true,
            page: url,
            width: 400,
            height: 210,
            btnBar: false,
            resize: false,
            maxBtn: false
        });
        dlg.ShowDialog();

        return dlg;
    }

    function showDenyDialog(document, typeId) {

        var url = "${dynamicURL}/workflow/showDenyDialog?parentWindowId=examineDialog&document=" + document + "&typeId=" + typeId;
        var dlg = new dg.curWin.$.dialog({
            id: 'denyDialog',
            parent: dg,
            title: '邮件短信提醒',
            cover: true,
            page: url,
            width: 400,
            height: 230,
            btnBar: false,
            resize: false,
            maxBtn: false
        });
        dlg.ShowDialog();

        return dlg;
    }

    function showDropDialog() {
        var url = "${dynamicURL}/workflow/showDropDialog?parentWindowId=examineDialog";
        var dlg = new dg.curWin.$.dialog({
            id: 'dropDialog',
            parent: dg,
            title: '邮件短信提醒',
            cover: true,
            page: url,
            width: 400,
            height: 210,
            btnBar: false,
            resize: false,
            maxBtn: false
        });
        dlg.ShowDialog();

        return dlg;
    }


    function commitAgree(isMail, isPhoneMessage, idea, activityId) {
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
        $('#idea').val(idea);
        $('#activityId').val(activityId);

        $('#btnAgree').val('提交ing');
        $("#btnAgree").attr("disabled", true);
        var url = "${dynamicURL}/purchase/api/agreeWorkFlow";

        var postObject = $('#purchaseOrderFormHeader').serializeObject();
        postObject.activityId = activityId;

        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json;",
            data: JSON.stringify(postObject),
            success: function (data) {
                if (data.success) {
                    alert("审批成功!");
//                    dg.curWin.location=dg.curWin.location;;
                    dg.curWin.location=dg.curWin.location;
                } else {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*审批成功.*$");
                    if (reg.test(str)) {

                        dg.curWin.location=dg.curWin.location;
                    }
                    $('#btnAgree').val('同意');
                    $("#btnAgree").attr("disabled", false);
                }
            }
        });
    }

    function commitDeny(isMail, isPhoneMessage, idea, activityId, isWithDrawPhoto) {
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
        $('#idea').val(idea);
        $('#activityId').val(activityId);
        $('#btnDeny').val('提交ing');
        $("#btnDeny").attr("disabled", true);
        var url = "${dynamicURL}/purchase/api/denyWorkFlow";

        var postObject = $('#purchaseOrderFormHeader').serializeObject();
        postObject.activityId = activityId;
        postObject.isWithDrawPhoto = isWithDrawPhoto;

        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json;",
            data: JSON.stringify(postObject),
            success: function (data) {
                if (data.success) {
                    alert("审批成功!");
                    dg.curWin.location = dg.curWin.location;
                } else {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*审批成功.*$");
                    if (reg.test(str)) {

                        dg.curWin.location=dg.curWin.location;
                    }
                    $('#btnDeny').val('退回');
                    $("#btnDeny").attr("disabled", false);
                }
            }
        });
    }

    function commitDrop(isMail, isPhoneMessage, idea) {
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
        $('#idea').val(idea);
        $('#btnDrop').val('提交ing');
        $("#btnDrop").attr("disabled", true);
        var url = "${dynamicURL}/purchase/api/dropWorkFlow";
        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json;",
            data: JSON.stringify($('#purchaseOrderFormHeader').serializeObject()),
            success: function (data) {
                if (data.success) {
                    alert("审批成功!");
                    dg.curWin.location = dg.curWin.location;
                } else {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*审批成功.*$");
                    if (reg.test(str)) {

                        dg.curWin.location=dg.curWin.location;
                    }
                    $('#btnDrop').val('弃审');

                    $("#btnDrop").attr("disabled", false);
                }
            }
        });
    }
    $('#btnUpload').click(function () {
        var fileElementId = $(this).prev().attr('id');
        var file = $('#fileToUpload').val();
        if (file == "") {
            alert("请选择需要上传的文件！");
            return false;
        }

        var documentNo = $('#cdocument').val();
        if ("" == documentNo) {
            alert('单据号不可为空');
            return false;
        }

        uploadFile('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}', documentNo, $('#uploadTable > tbody'), fileElementId);

    });

    $('#btnDelFile').click(function () {
        deleteFiles('${dynamicURL}', "uploadTable");
    });
    $(function () {
        $('#btnAgree').click(function () {
            var isLastApprovalStep = "${isLastApprovalStep}";
            if (isLastApprovalStep == "Y") {
                if ($("#issap").val() != "是") {
                    alert("请先传SAP采购订单!");
                    return false;
                }
            }
            showAgreeDialog("${purchaseOrder.cdocument}", 77021);
        });

        $('#btnDeny').click(function () {
            if ($("#issap").val() == "是") {
                alert("已获取资产编码不可退回");
                return false;
            }
            showDenyDialog("${purchaseOrder.cdocument}", 77021);
        });

        $('#btnDrop').click(function () {
            if ($("#issap").val() == "是") {
                alert("已获取资产编码不可弃审");
                return false;
            }
            showDropDialog();
        });


        var tdSumApplyCount = 0;
        $("td[id='tdApplyCount']").each(function () {
            var tdApplyCount = $.trim($(this).attr("v"));
            tdSumApplyCount += tdApplyCount - 0;
        });
        tdSumApplyCount = tdSumApplyCount.toFixed(2);
        $("#tdSumApplyCount").html(fmoney(tdSumApplyCount,2));

        var tdSumPurchasePrice = 0;
        $("td[id='tdPurchasePrice']").each(function () {
            var tdPurchasePrice = $.trim($(this).attr("v"));
            tdSumPurchasePrice += tdPurchasePrice - 0;
        });
        tdSumPurchasePrice = tdSumPurchasePrice.toFixed(2);
        $("#tdSumPurchasePrice").html(fmoney(tdSumPurchasePrice,2));

        var tdSumPurchasePriceLocal = 0;
        $("td[id='tdPurchasePriceLocal']").each(function () {
            var tdPurchasePriceLocal = $.trim($(this).attr("v"));
            tdSumPurchasePriceLocal += tdPurchasePriceLocal - 0;
        });
        tdSumPurchasePriceLocal = tdSumPurchasePriceLocal.toFixed(2);
        $("#tdSumPurchasePriceLocal").html(fmoney(tdSumPurchasePriceLocal,2));

        var tdSumNetPrice = 0;
        $("td[id='tdNetPrice']").each(function () {
            var tdNetPrice = $.trim($(this).attr("v"));
            tdSumNetPrice += tdNetPrice - 0;
        });
        tdSumNetPrice = tdSumNetPrice.toFixed(2);
        $("#tdSumNetPrice").html(fmoney(tdSumNetPrice,2));

    });

    /* 传SAP采购订单 */
    function sendSAP() {
        $("#btnSendSAP").attr("disabled", true);
        //alert("传sap中...");
        $("#btnSendSAP").val('传SAP中...');
        if ($("#issap").val() == "是") {
            alert("该单据已成功传过SAP!");
            return false;
        }

        var url = "${dynamicURL}/purchase/api/sendSAP";

        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json;",
            data: JSON.stringify($('#purchaseOrderFormHeader').serializeObject()),
            success: function (data) {
                if (data.success) {
                    alert("传SAP采购订单成功!")
                    location.reload();
                } else {
                    $('#btnSendSAP').val('传SAP采购订单');
                    $("#btnSendSAP").attr("disabled", false);
                    alert(data.errorMessages.join());
                }
            }
        });

        $("#btnSendSAP").val('传SAP采购订单');
        $("#btnSendSAP").attr("disabled", false);
    }
    $(function () {
        $.ajax({
            url: '${dynamicURL}/file/getFilesByDocument',
            data: {'document': $("#cdocument").val()},
            dataType: 'json',
            type: 'POST',
            success: function (data) {
                if (data.length > 0) {
                    $('#uploadTable .noItems').remove();
                    $.each(data, function (i, item) {
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
                                + '<td style="text-align:center;"><input type="checkbox" uuid="" name="documentId" />'
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
        var documentLX = $("#txtDemandOrderId").val() + 'LX';//立项书对应document
        $.ajax({
            url: '${dynamicURL}/file/getFilesByDocument',
            data: {'document': documentLX},
            dataType: 'json',
            type: 'POST',
            success: function (data) {
                if (data.length > 0) {
                    $('#uploadTable2 .noItems').remove();
                    $.each(data, function (i, item) {
                        $('#uploadTable2 > tbody').append('<tr id="tr" class="' + item.fileuuid + '" style="white-space:nowrap;">'
                            + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '" name=documentId />'
                            + '</td><td scope="col">' + item.fileName
                            + '</td><td scope="col">' + item.createUserName
                            + '</td><td style="width:9%">' + item.createDate
                            + '</td><td align="center" style="width:10%"><a href="' + '${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                            + '</td></tr>');
                    });
                }

            }
        });

        //获得角色权限
        purchasingManager = "${purchasingManager}";//采购经理
        attachManager = "${attachManager}";//归口经理
        platformFinanceManager = "${platformFinanceManager}";//平台财务经理

    });
    function showDemandOrder(id) {
        //dg.curWin.showDemandOrder(id);
        var lurl = "${dynamicURL}/demand/showDemandOrder?id=" + id + "&typeId=77001";
        var parentWindow = dg.curWin;
        new parentWindow.$.dialog({
            id: 'showForDemand',
            title: '数据查看',
            cover: true,
            page: lurl,
            rang: true,
            width: 900,
            height: 600,
            btnBar: false,
            resize: false,
            maxBtn: false
        }).ShowDialog();
    }
    function showApplyOrder(id) {
        //dg.curWin.showApplyOrder(id);
        var lurl = "${dynamicURL}/apply/showApplyOrder?id=" + id + "&typeId=77011";
        var parentWindow = dg.curWin;
        new parentWindow.$.dialog({
            id: 'showForApply',
            title: '数据查看',
            cover: true,
            page: lurl,
            rang: true,
            width: 900,
            height: 600,
            btnBar: false,
            resize: false,
            maxBtn: false
        }).ShowDialog();
    }
    $(document).ready(function () {
        $(window).resize(function () {
            $('#purchaseTab').tabs('resize');
        });
    });

    function stamp(id, title){

        var lurl = "${dynamicURL}/purchase/printOrder?id=" + id + "&typeId=77021";
        window.open(lurl);
    }

    /**
     * 数字格式转换成千分位
     */
    function fmoney(s, n) {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
        var l = s.split('.') [0].split('').reverse(),
            r = s.split('.') [1];
        var t = '';
        for (var i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
        }
        return t.split('').reverse().join('') + '.' + r;

    }
</script>
</body>


</html>
