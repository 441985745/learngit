<%--
  Created by IntelliJ IDEA.
  User: ykz
  Date: 17/1/6
  Time: 11:40
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
    <title>采购申请单</title>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
    <div id="tabletitle"
         style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
        采购申请单${applyOrder.draft=='Y'?"(草稿)":""}</div>
</div>
<td class="buttonbar">
    <%--<input type="button" id="saveOrder" class="btn" value="保存" onclick="saveOrder()" />--%>
    <input type="button" id="btnAgree" class="btn" value="同意"/>
    <input type="button" id="btnDeny" class="btn" value="退回"/>
    <c:choose>
        <c:when test="${scoresQuery.approvalState == '等待审批'}"> </c:when>
        <c:otherwise>
            <input type="button" id="btnDrop" class="btn" value="弃审"/>
        </c:otherwise>
    </c:choose>
    <input type="submit" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn"/>
    <input type="button" class="btn" value="打印" onclick="javascript:stamp('${applyOrder.cdocument}','打印');"/>
</td>

<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
    <app:approvalTag billCode="${applyOrder.cdocument}" typeId="77011"></app:approvalTag>
</div>

<div style="margin-top: 10px;width:100%;overflow :auto;">
    <div id="purchaseTab" class="easyui-tabs" >
        <div title="申请信息" id="tabsa" style="overflow:auto; padding:20px;min-width: 800px;">
            <div>


                <table  cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
                    <tbody id="tbody_detail_info">
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle"></div>
                            基本信息
                        </td>
                    </tr>
                    <tr class="bodyTr">
                        <td>
                            <form id="examineForm">
                                <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
                                    <input name="isMail" id="isMail" class="controlhidden" type="text"/>
                                    <input name="isPhoneMessage" id="isPhoneMessage" class="controlhidden" type="text"/>
                                    <input name="idea" id="idea" class="controlhidden" type="text"/>
                                    <input name="activityId" id="activityId" class="controlhidden" type="text"/>
                                    <input name="costCenterCode" id="costCenterCode" class="controlhidden"
                                           value="${applyOrder.costCenterCode}" type="text"/>
                                    <input name="companyCode" id="companyCode" class="controlhidden"
                                           value="${applyOrder.companyCode}" type="text"/>

                                    <input name="issap" id="issap" value="${applyOrder.issap}" type="hidden">
                                    <tbody>
                                    <tr>
                                        <td style="text-align: right">单据号:</td>
                                        <td>
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.cdocument}" name="cdocument" type="text"
                                                   id="cdocument">
                                        </td>

                                        <td style="text-align: right"><font color="red">*</font>所属公司:</td>
                                        <td>
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.companyName}" name="" type="text" id="">
                                            <input name='' type='hidden' value='${applyOrder.companyCode}'
                                                   id='txtCompanyCode'>
                                        </td>
                                        <td style="text-align: right;"><font color="red">*</font>办公地点:</td>
                                        <td class="tdtext">
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.officeLocation}" name="" type="text"
                                                   id="txtOfficeLocation">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">申请人:</td>
                                        <td>
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.applyUser}" name="" type="text" id="">
                                        </td>
                                        <td style="text-align: right"><font color="red">*</font>资产类型:</td>
                                        <td>
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.assetType}" name="assetType" type="text"
                                                   id="txtAssetType1">
                                        </td>
                                        <td id="tdAssemble" style="text-align: right;">是否组装:</td>
                                        <td id="tdAssembles">
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.assemble=='Y'?'是':''}${applyOrder.assemble=='N'?'否':''}"
                                                   name="" type="text" id="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right">申请日期:</td>
                                        <td>
                                            <input class="searchinput" readonly="readonly"
                                                   value="<fmt:formatDate value='${applyOrder.applyDate}'  pattern='yyyy-MM-dd'/>"
                                                   name="" type="text" value="" id="txtApplyDate">
                                        </td>
                                        <td style="text-align: right"><font color="red">*</font>平台:</td>
                                        <td class="tdtext">
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.platform}" name="" type="text" id="">
                                        </td>
                                        <td id="tdBuilding" style="text-align: right;">是否房屋建筑物:</td>
                                        <td id="tdBuildings">
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.building=='Y'?'是':''}${applyOrder.building=='N'?'否':''}"
                                                   name="" type="text" id="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right"><font color="red">*</font>申请人部门:</td>
                                        <td>
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.applyDepartment}" name="" type="text" id="">
                                        </td>
                                        <td style="text-align: right">区域:</td>
                                        <td class="tdtext">
                                            <input class="searchinput" readonly="readonly" value="${applyOrder.region}"
                                                   name="" type="text" id="">
                                        </td>
                                        <td id="tdWorkShopType" style="text-align: right;">车间类型:</td>
                                        <td id="tdWorkShopTypes">
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.workShopType}" name="" type="text" id="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right"><font color="red">*</font>归口/预算部门:</td>
                                        <td>
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.budgetDepartmentName}" name="" type="text" id="">
                                        </td>
                                        <td style="text-align: right"><font color="red">*</font>成本中心:</td>
                                        <td>
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.costCenter}" name="" type="text" id="">
                                            <input readonly="readonly" class="searchinput"
                                                   style="background-color: #CCCCCC;" name="" type="hidden"
                                                   value="${applyOrder.costCenterCode}" id="txtCostCenterCode">
                                        </td>
                                        <td style="text-align: right">一次性费用化资产:</td>
                                        <td>
                                            <input class="searchinput" readonly="readonly"
                                                   value="${applyOrder.expensing=='N'?'否':'是'}" name="expensing"
                                                   type="text" id="expensing">
                                        </td>
                                    </tr>
                                    <c:if test="${purchasingManager == 'Y' }">
                                        <tr>
                                            <td style="text-align: right">采购员:</td>
                                            <td class="tdtext">
                                                <select class="searchselect" name="buyerCode" id="txtBuyer"></select>
                                                    <%-- <input class="searchinput" readonly="readonly" value="${applyOrder.buyer}" name="" type="text" id=""> --%>
                                            </td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
                </form>
                <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle"></div>
                            申购资产需求
                        </td>
                    </tr>

                    <c:if test="${assetAccounting == 'Y' }">
                        <tr>
                            <td colspan="2">
                                <input type="button" name="btnGetSecondary" value="获取资产编码" onclick="getSecondary();"
                                       id="btnGetSecondary" class="btn"/>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="2">
                            <table id="" width="100%" border="0" cellpadding="0" cellspacing="0"
                                   style="table-layout: fixed;">
                                <tbody>
                                <tr>
                                    <td class="tdtitle" style="text-align: right" colspan="2">
                                        <div id="" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                            <div>
                                                <table class="adminlist" cellspacing="0" rules="all" border="1"
                                                       id="gridDataList" style="border-collapse:collapse;">

                                                    <tr style="white-space:nowrap;">
                                                        <th class='tdpadding' scope="col">合并号</th>
                                                        <th class='tdpadding' scope="col">资产名称</th>
                                                        <th class='tdpadding' scope="col">规格参数</th>
                                                        <th class='tdpadding' scope="col">功能</th>
                                                        <th class='tdpadding' scope="col">资产分类</th>
                                                        <th class='tdpadding' scope="col">验收标准</th>
                                                        <th class='tdpadding' scope="col">选配件</th>
                                                        <th class='tdpadding' scope="col">本次申请数量</th>
                                                        <th class='tdpadding' scope="col">单位</th>
                                                        <th class='tdpadding' scope="col">询价单价</th>
                                                        <th class='tdpadding' scope="col">询价总价</th>
                                                        <!-- 10 -->
                                                        <th class='tdpadding' scope="col">要求到位日期</th>
                                                        <!--  <th class='tdpadding' scope="col">采购单价</th>
                                                         <th class='tdpadding' scope="col">采购总价</th>
                                                         <th class='tdpadding' scope="col">采购回复交期</th> -->

                                                        <th class='tdpadding' scope="col">折旧年限</th>
                                                        <th class='tdpadding' scope="col">编码方式</th>
                                                        <th class='tdpadding' scope="col">主编码</th>
                                                        <th class='tdpadding' scope="col">子编码</th>
                                                        <th class='tdpadding' scope="col">总账科目</th>
                                                        <!-- 20 -->
                                                        <th class='tdpadding' scope="col">成本中心</th>
                                                    </tr>

                                                    <tbody id="tbody_update_detail">
                                                    <c:forEach items="${applyOrderDetailes}" var="applyOrderDetaile"
                                                               varStatus="i">

                                                        <tr style="white-space:nowrap;" class='bodyTr'>
                                                            <td class='tdpadding'>
                                                                    ${applyOrderDetaile.mergeNumber}
                                                                <input type="hidden" name="id"
                                                                       value="${applyOrderDetaile.id}">
                                                                        <input type="hidden" name="mergeNumber"
                                                                               value="${applyOrderDetaile.mergeNumber}">
                                                            </td>
                                                            <td class='tdpadding'><input
                                                                    value="${applyOrderDetaile.assetsName}"
                                                                    readonly="readonly" name="assetsName"
                                                                    id="assetsName"></td>
                                                            <td class='tdpadding'>${applyOrderDetaile.specificationParameter}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.functions}
                                                                <c:if test="${purchasingManager =='Y' }">
                                                                    <input type="hidden" id="purchaseUnitPrice"
                                                                           name="purchaseUnitPrice"
                                                                           value="${applyOrderDetaile.inquiryUnitPrice}">
                                                                    <input type="hidden" id="purchasePrice"
                                                                           name="purchasePrice"
                                                                           value="${applyOrderDetaile.inquiryPrice}"
                                                                           readonly="readonly">
                                                                    <input readonly='readonly'
                                                                           name="procurementReturnPeriod" type="hidden"
                                                                           id="procurementReturnPeriod"
                                                                           value="${applyOrderDetaile.procurementReturnPeriod}"
                                                                           class="Wdate"
                                                                           onclick="WdatePicker({skin:'ext'});">
                                                                </c:if>
                                                            </td>
                                                            <td class='tdpadding'>
                                                                <c:if test="${assetAccounting == 'Y' }">
                                                                    <input style="width: 100px;" readonly="readonly"
                                                                           name="assetClassification"
                                                                           value="${applyOrderDetaile.assetClassification}"
                                                                           type="text" id="assetClassification"
                                                                           class="inputbtnRefViewGray"></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanAssets(this);"><input id="btnAssetsShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
                                                                </c:if>
                                                                <c:if test="${assetAccounting != 'Y' }">
                                                                    ${applyOrderDetaile.assetClassification}
                                                                </c:if>
                                                                    <%--<input type="hidden" id="depreciation" name="depreciation" value="${applyOrderDetaile.depreciation}">--%>
                                                                <input type="hidden" id="netSalvage" name="netSalvage"
                                                                       value="${applyOrderDetaile.netSalvage}">
                                                            </td>
                                                            <td class='tdpadding'>${applyOrderDetaile.acceptanceCriteria}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.optionalAccessories}</td>
                                                            <td class='tdpadding' id="tdDemandCount"
                                                                v='${applyOrderDetaile.demandCount}'>
                                                                <input type="hidden" name="demandCount" id="demandCount"
                                                                       value="${applyOrderDetaile.demandCount}">
                                                                    ${applyOrderDetaile.demandCount}
                                                            </td>
                                                            <td class='tdpadding'>
                                                                <input type="text" size='2' name="units"
                                                                       value="${applyOrderDetaile.units}"
                                                                       readonly='readonly' <%-- <c:if test="${assetAccounting != 'Y' }">readonly='readonly'</c:if> --%> >
                                                            </td>
                                                            <td class='tdpadding'>${applyOrderDetaile.inquiryUnitPrice}</td>
                                                            <td class='tdpadding' id="tdInquiryPrice"
                                                                v='${applyOrderDetaile.inquiryPrice}'>${applyOrderDetaile.inquiryPrice}</td>
                                                            <!-- 10 -->
                                                            <td class='tdpadding'><fmt:formatDate
                                                                    value="${applyOrderDetaile.requirementsDate}"
                                                                    pattern="yyyy-MM-dd"/></td>
                                                                <%-- <td class='tdpadding'>
                                                                    <c:if test="${purchasingManager =='Y' }">
                                                                        <input type="text" id="purchaseUnitPrice" name="purchaseUnitPrice" value="${applyOrderDetaile.inquiryUnitPrice}">
                                                                    </c:if>
                                                                    <c:if test="${purchasingManager !='Y' }">
                                                                        ${applyOrderDetaile.purchaseUnitPrice}
                                                                    </c:if>
                                                                   </td>
                                                                <td class='tdpadding'>
                                                                    <c:if test="${purchasingManager =='Y' }">
                                                                        <input type="text" id="purchasePrice" name="purchasePrice" value="${applyOrderDetaile.inquiryPrice}" readonly="readonly">
                                                                    </c:if>
                                                                    <c:if test="${purchasingManager !='Y' }">
                                                                        ${applyOrderDetaile.purchasePrice}
                                                                    </c:if></td>
                                                                <td class='tdpadding'>
                                                                    <c:if test="${purchasingManager =='Y' }">
                                                                        <input readonly='readonly' name="procurementReturnPeriod"  type="text" id="procurementReturnPeriod" value="${applyOrderDetaile.procurementReturnPeriod}" class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                                                    </c:if>
                                                                    <c:if test="${purchasingManager !='Y' }">
                                                                    ${applyOrderDetaile.procurementReturnPeriod}
                                                                    </c:if></td>
                                                                   </td> --%>

                                                            <td class='tdpadding'>
                                                                <c:if test="${assetAccounting == 'Y' }">
                                                                    <input disabled="true" id="depreciation"
                                                                           name="depreciation"
                                                                           value="${applyOrderDetaile.depreciation}">
                                                                </c:if>
                                                                <c:if test="${assetAccounting != 'Y' }">
                                                                    <input disabled
                                                                           value="${applyOrderDetaile.depreciation}">
                                                                </c:if>
                                                            </td>
                                                            <td class='tdpadding'>
                                                                <c:if test="${assetAccounting == 'Y' }">
                                                                    <select name="codingMode" id="codingMode"
                                                                            value="${applyOrderDetaile.codingMode}">
                                                                        <option value="新增" ${applyOrderDetaile.codingMode=="新增"?" selected='selected' ":""}>
                                                                            新增
                                                                        </option>
                                                                        <option value="指定" ${applyOrderDetaile.codingMode=="指定"?" selected='selected' ":""}>
                                                                            指定
                                                                        </option>
                                                                        <option value="追加" ${applyOrderDetaile.codingMode=="追加"?" selected='selected' ":""}>
                                                                            追加
                                                                        </option>
                                                                    </select>
                                                                </c:if>
                                                                <c:if test="${assetAccounting != 'Y' }">
                                                                    ${applyOrderDetaile.codingMode}
                                                                </c:if>
                                                            </td>
                                                            <td class='tdpadding'>
                                                                <c:if test="${assetAccounting == 'Y' }">
                                                                    <input value="${applyOrderDetaile.mainAssetCode}"
                                                                           name="mainAssetCode" id="mainAssetCode">
                                                                </c:if>
                                                                <c:if test="${assetAccounting != 'Y' }">
                                                                    ${applyOrderDetaile.mainAssetCode}
                                                                </c:if>
                                                            </td>
                                                            <td class='tdpadding'>
                                                                <c:if test="${assetAccounting == 'Y' }">
                                                                    <input value="${applyOrderDetaile.secondary}"
                                                                           name="secondary" id="secondary">
                                                                </c:if>
                                                                <c:if test="${assetAccounting != 'Y' }">
                                                                    ${applyOrderDetaile.secondary}
                                                                </c:if>
                                                            </td>
                                                            <td class='tdpadding'>
                                                                <input value="${applyOrderDetaile.ledgerAccount}"
                                                                       readonly="readonly" name="ledgerAccount"
                                                                       id="ledgerAccount">
                                                            </td>
                                                            <td class='tdpadding'>${applyOrderDetaile.costCenter}</td>
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
                                                        <td id="tdSumDemandCount"></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td id="tdSumInquiryPrice"></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
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
                                           style="border-collapse:collapse;">
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
            <table id="Table6" cellpadding="0px" cellspacing="0" class="tablenoborder">
                <tbody>
                <tr>
                    <td class="titlefontsize" align="left">
                        <div class="imgtitle"></div>
                        资产明细
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                               style="table-layout: fixed;">
                            <tbody>
                            <tr>
                                <td class="tdtitle" style="text-align: right" colspan="2">
                                    <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                        <div>
                                            <table class="adminlist" cellspacing="0" rules="all" border="1"
                                                   id="gridDataList" style="border-collapse:collapse;">
                                                <tbody>
                                                <tr style="white-space:nowrap;">
                                                    <th class='tdpadding' scope="col">合并号</th>
                                                    <th class='tdpadding' scope="col">资产名称</th>
                                                    <th class='tdpadding' scope="col">规格参数</th>
                                                    <th class='tdpadding' scope="col">功能</th>
                                                    <th class='tdpadding' scope="col">验收标准</th>
                                                    <th class='tdpadding' scope="col">选配件</th>
                                                    <th class='tdpadding' scope="col">本次申请数量</th>
                                                    <th class='tdpadding' scope="col">单位</th>
                                                    <th class='tdpadding' scope="col">询价单价</th>
                                                    <!-- 10 -->
                                                    <th class='tdpadding' scope="col">要求到位日期</th>
                                                    <th class='tdpadding' scope="col">需求单号</th>
                                                </tr>
                                                <c:forEach items="${applyDemandMappingModels}"
                                                           var="applyDemandMappingModel" varStatus="i">
                                                    <tr style="white-space:nowrap;">
                                                        <td class='tdpadding'>${applyDemandMappingModel.mergeNumber}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.assetsName}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.specificationParameter}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.functions}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.acceptanceCriteria}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.optionalAccessories}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.demandCount}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.units}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.inquiryUnitPrice}</td>
                                                        <td class='tdpadding'><fmt:formatDate
                                                                value="${applyDemandMappingModel.requirementsDate}"
                                                                pattern="yyyy-MM-dd"/></td>
                                                        <td class='tdpadding'><a
                                                                href="javascript:showDialog('${applyDemandMappingModel.demandOrderId}')">${applyDemandMappingModel.demandOrderId}</a>
                                                        </td>
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
    </div>
</div>
<input type="hidden" id="assetsTypeCode">
<div id="addAssetsWindow" name="" class="easyui-window" title="资产分类" data-options="iconCls:'icon-save'"
     style="width:800px;height:440px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">

            <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
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
                                        <div id="div1" class="divGrid"
                                             style="overflow-x: scroll; overflow-y: visible;height: 310px;">
                                            <div>

                                                <table id="table_add_detail" class="adminlist" cellspacing="0"
                                                       rules="all" border="1" id="gridDataList"
                                                       style="border-collapse:collapse;">

                                                    <tr style="white-space:nowrap;">
                                                        <th class='tdpadding' scope="col"></th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>资产分类
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>资产编码
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>资产分类描述
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>折旧年限(年)
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>净残值率
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>归口管理部门
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>总账科目
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>科目描述
                                                        </th>
                                                    </tr>

                                                    <tbody id="tbody_detail">
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
<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script> --%>
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>

<script type="text/javascript">
    var dg = frameElement.lhgDG;
    var purchasingManager = "${purchasingManager}";
    var canStepSubmit = false;

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

    function showAgreeDialog() {
        var document = $("#cdocument").val();
        var url = "${dynamicURL}/workflow/showAgreeDialog?parentWindowId=examineDialog&document=" + document + "&typeId=77011";
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

    function showDenyDialog() {
        var document = $("#cdocument").val();
        var url = "${dynamicURL}/workflow/showDenyDialog?parentWindowId=examineDialog&document=" + document + "&typeId=77011";
        var dlg = new dg.curWin.$.dialog({
            id: 'denyDialog',
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

    $("#btnDetailSave").click(function () {

        $('#addAssetsWindow').window('close');
        var assetClassification = $('input:radio[name="btnRadio"]:checked').parent().parent().find("#assetClassification").val();
        var depreciationPeriod = $('input:radio[name="btnRadio"]:checked').parent().parent().find("#depreciationPeriod").val();
        var netResidualRate = $('input:radio[name="btnRadio"]:checked').parent().parent().find("#netResidualRate").val();
        var generalLedgerAccount = $('input:radio[name="btnRadio"]:checked').parent().parent().find("#generalLedgerAccount").val();
//        console.log(assetClassification)
//        console.log(depreciationPeriod+"："
//            +"："+netResidualRate
//            +"："+generalLedgerAccount )
//        alert(depreciationPeriod)
        $inputAssets.parent().find("#assetClassification").val(assetClassification);
        $inputAssets.parent().parent().find("#depreciation").val(depreciationPeriod);
        $inputAssets.parent().find("#netSalvage").val(netResidualRate);
        $inputAssets.parent().parent().find("#ledgerAccount").val(generalLedgerAccount);

//        console.log(assetClassification, depreciationPeriod, netResidualRate, generalLedgerAccount )
        //8100、8200、8300、8400、9006
        if ('8100' == assetClassification || '8200' == assetClassification || '8300' == assetClassification || '8400' == assetClassification || '9006' == assetClassification) {
            $inputAssets.parent().parent().find("#depreciation").attr("disabled", false);
//            alert($inputAssets.parent().parent().find("#depreciation").val());
        } else {
            $inputAssets.parent().parent().find("#depreciation").attr("disabled", true);
        }

    });
    /* 获取资产编码 */
    function getSecondary() {
        if ($("#issap").val() == "是") {
            alert("该单据已成功获取过资产编码!");
            return false;
        }
        $("#btnGetSecondary").val('获取中...');
        $("#btnGetSecondary").attr("disabled", true);
        var url = "${dynamicURL}/apply/api/sapInterface";

        var applyOrderWrapper = saveOrder();
        //console.log(applyOrderWrapper);
        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json;",
            data: JSON.stringify(applyOrderWrapper),
            success: function (data) {
                if (data.success) {
                    alert("获取资产编码成功!")
                    location.reload();
                } else {
                    $('#btnGetSecondary').val('获取资产编码');
                    $("#btnGetSecondary").attr("disabled", false);
                    alert(data.errorMessages.join());
                }
            }
        });

        $("#btnGetSecondary").val('获取资产编码');
        $("#btnGetSecondary").attr("disabled", false);
    }
    function commitAgree(isMail, isPhoneMessage, idea, activityId) {
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
        $('#idea').val(idea);
        $('#activityId').val(activityId);

        $('#btnAgree').val('提交ing');
        $("#btnAgree").attr("disabled", true);
        var url = "${dynamicURL}/apply/api/agreeApplyOrder";
//        console.log(JSON.stringify($('#examineForm').serializeObject()))

        var applyOrderWrapper = saveOrder();
        /* console.log(applyOrderWrapper);
         alert("11");
         return false; */
        $.ajax({
            type: "POST",
            url: url,
            async: false,
            contentType: "application/json;",
            data: JSON.stringify(applyOrderWrapper),
            success: function (data) {
                if (data.success) {
                    alert("审批成功!")
//                    dg.curWin.location=dg.curWin.location;;
                    dg.curWin.location = dg.curWin.location;
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

        $('#btnAgree').val('同意');
        $("#btnAgree").attr("disabled", false);
    }

    function commitDeny(isMail, isPhoneMessage, idea, activityId) {
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
        $('#idea').val(idea);
        $('#activityId').val(activityId);
        $('#btnDeny').val('提交ing');
        $("#btnDeny").attr("disabled", true);
        var url = "${dynamicURL}/apply/api/denyApplyOrder";

        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json; charset=gb2312",
            data: JSON.stringify($('#examineForm').serializeObject()),
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
        var url = "${dynamicURL}/apply/api/dropApplyOrder";
        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json; charset=gb2312",
            data: JSON.stringify($('#examineForm').serializeObject()),
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

    $(function () {
        $('#btnAgree').click(function () {
            showAgreeDialog();
        });

        $('#btnDeny').click(function () {
            //2017/6/8  德邦要求去掉此闸口
            /*if ($("#issap").val() == "是") {
                alert("已获取资产编码不可退回");
                return false;
             }*/
            showDenyDialog();
        });

        $('#btnDrop').click(function () {
            //2017/6/8  德邦要求去掉此闸口
            /*if ($("#issap").val() == "是") {
                alert("已获取资产编码不可弃审");
                return false;
             }*/
            showDropDialog();
        });
        if ($("#expensing").val() == "是" || $("#issap").val() == "是") {
            $("select[id=codingMode]").each(function () {
                $(this).attr("disabled", "disabled");
            });
            $("input[id=mainAssetCode]").each(function () {
                $(this).attr("readonly", "readonly");
            });
            $("input[id=secondary]").each(function () {
                $(this).attr("readonly", "readonly");
            });
        }
    });

    //清除  "X"
    function cleanAssets(btn) {
        $(btn).parent().find("#assetClassification").val("");
    }
    var $inputAssets;
    //显示资产分类弹窗
    $('#btnAssetsShow').live('click', function () {
        $inputAssets = $(this);
        var top = "90px";//$(this).offset().top - 350;
        var left = "15px";//$(this).offset().left-180;
        $('#addAssetsWindow').window('open').window('resize', {width: '450px', height: '300px', top: top, left: left});
        searchDemandOrderDetail();


    });

    function searchDemandOrderDetail() {

//        var dat = $("#addPurchaseApplyOrderDetailForm").serializeObject();
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/assetClassification/api/searchAssetClassification",
            data: JSON.stringify($('#examineForm').serializeObject()),
            success: function (data) {

                var divHtml = "";
                for (var i = 0; i < data.length; i++) {
                    divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                    if (i == 0) {
                        divHtml += "<td class='tdpadding'><input type='radio' checked='checked' name='btnRadio' ></td>";
                    } else {
                        divHtml += "<td class='tdpadding'><input type='radio' name='btnRadio' ></td>";
                    }
                    divHtml += "<td class='tdpadding'><input type='text' name='assetClassification' id='assetClassification' value='" + data[i].assetClassification + "' readonly='readonly' size='5' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='assetsName' id='assetsName' value='" + data[i].assetsName + "' readonly='readonly' size='10'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='assetstypeInfo' id='assetstypeInfo' value='" + data[i].assetstypeInfo + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='depreciationPeriod' id='depreciationPeriod' value='" + data[i].depreciationPeriod + "' readonly='readonly' size='5' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='netResidualRate' id='netResidualRate'  value='" + data[i].netResidualRate + "' readonly='readonly' size='5' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='managementDepartment' id='managementDepartment' value='" + data[i].managementDepartment + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='generalLedgerAccount' id='generalLedgerAccount' value='" + data[i].generalLedgerAccount + "' readonly='readonly' size='13'  ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='accountDescription' id='accountDescription' value='" + data[i].accountDescription + "' readonly='readonly' ></td>";
                    divHtml += "</tr>";
                }
                $("#tbody_detail").html(divHtml);
            }
        });
    }

    //提交表单
    function saveOrder() {

        //申购资产需求
        var objdetail = {
            id: "",
            applyOrderId:"",
            assetClassification: "",
            codingMode: "",
            secondary: "",
            mainAssetCode: "",
            assetsName: "",
            mergeNumber: "",
            demandCount: "",
            units: "",
            depreciation: "",
            netSalvage: "",
            ledgerAccount: "",
            purchaseUnitPrice: "",
            purchasePrice: "",
            procurementReturnPeriod: ""
        };

        var applyOrderWrapper = {};

        //基本信息  申购资产需求 封装Json对象
        var objHeader = $("#examineForm").serializeObject();
        applyOrderWrapper.applyOrder = objHeader;
        var buyer = $("#txtBuyer").find("option:selected").text();
        if (purchasingManager == "Y") {
            if (!$("#txtBuyer").val()) {
                alert("采购员不能为空!");
                return false;
            }

        }
        objHeader.buyer = buyer;
        var flag = false;
        if (purchasingManager == "Y") {
            var sumInquiryPrice = 0;
            $("input[id=purchasePrice]").each(function () {
                var purchasePrice = $.trim($(this).val());
                if (purchasePrice == "" || isNaN(purchasePrice)) {
                    alert("采购价格必须是数字");
                    flag = true;
                    return false;
                }
                sumInquiryPrice += purchasePrice - 0;
            });
            $("input[id=procurementReturnPeriod]").each(function () {
                var procurementReturnPeriod = $.trim($(this).val());
                if (null == procurementReturnPeriod || procurementReturnPeriod == "") {
                    alert("采购回复交期不能为空");
                    flag = true;
                    return false;
                }
            });
            objHeader.iamoney = sumInquiryPrice.toFixed(2);
        }
        if (flag) {
            return false;
        }

        applyOrderWrapper.applyOrder = objHeader;

//        ///基本信息 封装Json对象
        var objArr = constructObjectFromTable(objdetail, $("#tbody_update_detail .bodyTr"));
        applyOrderWrapper.applyOrderDetails = objArr;

        return applyOrderWrapper;
    }
    //表单封装json
    function constructObjectFromTable(object, $object) {
        var javaObjArray = [];

        $object.each(function (i) {
            var javaObj = {};

            for (var p in object) {
                var $val = $(this).find('input[name="' + p + '"]');
                if ($val.length === 0) {
                    javaObj[p] = $(this).find('select[name="' + p + '"]').val();
                } else {
                    javaObj[p] = $val.val();
                }
            }

            javaObjArray.push(javaObj);
        });

        return javaObjArray;
    }
    $("#codingMode").live("change", function () {
        var codingMode = $(this).children("option:selected").val();
        var $mainAssetCode = $(this).parent().parent().find("#mainAssetCode");
        var $secondary = $(this).parent().parent().find("#secondary");
        if (codingMode == "新增") {
            $mainAssetCode.attr("readonly", "readonly");
            $secondary.attr("readonly", "readonly");
        }
        else if (codingMode == "追加") {
            $mainAssetCode.removeAttr("readonly");
            $secondary.attr("readonly", "readonly");
        }
        else {
            $mainAssetCode.removeAttr("readonly");
            $secondary.removeAttr("readonly");
        }
    });

    $("#txtBuyer").change(function () {
        if ( '${applyOrder.buyerCode}' != $("#txtBuyer").val()) {
            canStepSubmit = true;
        } else{
            canStepSubmit = false;
        }
    });


    // 根据办公地点选择查询采购员
    $(function () {
        $("#txtBuyer option").remove();

        var costCenterCode = $('#txtCostCenterCode').val();
        var assetType = $("#txtAssetType1").val();

        $.ajax({
            type: "POST",
            async: false,
            url: "${dynamicURL}/attchConfig/api/searchByCostcenterCodeAndAssetType",
            data: {"costcenterCode": costCenterCode, "assetType": assetType},
            success: function (data) {
                $("#txtBuyer").append("<option value=''>请选择</option>");
                if (data.buyerCodea != null && data.buyerCodea.length > 1) {
                    $("#txtBuyer").append("<option value='" + data.buyerCodea + "'>" + data.buyerCodea + "/" + data.buyerNamea + "</option>");
                }
                if (data.buyerCodeb != null && data.buyerCodeb.length > 1) {
                    $("#txtBuyer").append("<option value='" + data.buyerCodeb + "'>" + data.buyerCodeb + "/" + data.buyerNameb + "</option>");
                }

                if (data.buyerCodec != null && data.buyerCodec.length > 1) {
                    $("#txtBuyer").append("<option value='" + data.buyerCodec + "'>" + data.buyerCodec + "/" + data.buyerNamec + "</option>");
                }

                if (data.buyerCoded != null && data.buyerCoded.length > 1) {
                    $("#txtBuyer").append("<option value='" + data.buyerCoded + "'>" + data.buyerCoded + "/" + data.buyerNamed + "</option>");
                }

                if (data.buyerCodee != null && data.buyerCodee.length > 1) {
                    $("#txtBuyer").append("<option value='" + data.buyerCodee + "'>" + data.buyerCodee + "/" + data.buyerNamee + "</option>");
                }

                if (data.buyerCodef != null && data.buyerCodef.length > 1) {
                    $("#txtBuyer").append("<option value='" + data.buyerCodef + "'>" + data.buyerCodef + "/" + data.buyerNamef + "</option>");
                }

                if (data.buyerCodeg != null && data.buyerCodeg.length > 1) {
                    $("#txtBuyer").append("<option value='" + data.buyerCodeg + "'>" + data.buyerCodeg + "/" + data.buyerNameg + "</option>");
                }

                if (data.buyerCodeh != null && data.buyerCodeh.length > 1) {
                    $("#txtBuyer").append("<option value='" + data.buyerCodeh + "'>" + data.buyerCodeh + "/" + data.buyerNameh + "</option>");
                }
            }
        });
        $("select[id=codingMode]").each(function () {
            var codingMode = $(this).children("option:selected").val();
            var $mainAssetCode = $(this).parent().parent().find("#mainAssetCode");
            var $secondary = $(this).parent().parent().find("#secondary");
            if (codingMode == "新增") {
                $mainAssetCode.attr("readonly", "readonly");
                $secondary.attr("readonly", "readonly");
            }
            else if (codingMode == "追加") {
                $mainAssetCode.removeAttr("readonly");
                $secondary.attr("readonly", "readonly");
            }
            else {
                $mainAssetCode.removeAttr("readonly");
                $secondary.removeAttr("readonly");
            }
        });
    })

    $("#purchaseUnitPrice").live("keyup", function () {
        //数量
        var demandCount = $(this).parent().parent().find("#demandCount").val();
        //总价
        $(this).parent().parent().find("#purchasePrice").val((demandCount * $(this).val() - 0).toFixed(2));
    });
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
    function showDialog(id) {
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
    var tdSumDemandCount = 0;
    $("td[id='tdDemandCount']").each(function () {
        var tdDemandCount = $.trim($(this).attr("v"));
        tdSumDemandCount += tdDemandCount - 0;
    });
    $("#tdSumDemandCount").html(tdSumDemandCount);

    var tdSumInquiryPrice = 0;
    $("td[id='tdInquiryPrice']").each(function () {
        var tdInquiryPrice = $.trim($(this).attr("v"));
        tdSumInquiryPrice += tdInquiryPrice - 0;
    });
    $("#tdSumInquiryPrice").html(tdSumInquiryPrice.toFixed(2));
    $(document).ready(function () {
        $(window).resize(function () {
            $('#purchaseTab').tabs('resize');
        });
    });
    function stamp(id, title){

        var lurl = "${dynamicURL}/apply/printOrder?id=" + id + "&typeId=77011";
        window.open(lurl);
    }
</script>
</body>
</html>