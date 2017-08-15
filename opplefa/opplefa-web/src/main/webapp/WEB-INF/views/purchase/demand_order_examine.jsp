<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>资产需求单</title>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
    <div id="tabletitle"
         style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
        资产需求单${demandOrder.draft=='Y'?"(草稿)":""}</div>
</div>
<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
    <input type="button" id="btnAgree" class="btn" value="同意"/>
    <input type="button" id="btnDeny" class="btn" value="退回"/>
    <c:choose>
        <c:when test="${demandOrder.capprovalstate == '等待审批'}"> </c:when>
        <c:otherwise>
            <input type="button" id="btnDrop" class="btn" value="弃审"/>
        </c:otherwise>
    </c:choose>
    <input type="submit" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
    <input  type="button" value="打印" class="btn" onclick="javascript:stamp('${demandOrder.cdocument}');"/>
</div>
<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
    <app:approvalTag billCode="${demandOrder.cdocument}" typeId="77001"></app:approvalTag>
</div>
<div style="margin-top: 10px;">
    <div id="purchaseTab" class="easyui-tabs">
        <div title="基本信息" id="tabsa" style="overflow:auto; padding:20px;min-width: 800px;">
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
                            <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
                                <tbody>
                                <tr>
                                    <td style="text-align: right">单据号:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" name="txtDemandOrderId"
                                               type="text" value="${demandOrder.cdocument}" id="txtDemandOrderId">
                                    </td>
                                    <td style="text-align: right">所属公司:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" name="lblUser" type="text"
                                               value="${demandOrder.companyName}" id="lblUser">
                                    </td>
                                    <td style="text-align: right">资产类型:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" name="lblPurCode" type="text"
                                               value="${demandOrder.assetType}">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">申请人:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" name="txtApplyUser" type="text"
                                               value="${demandOrder.applyUser}" id="txtApplyUser">
                                    </td>
                                    <td style="text-align: right;">办公地点:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" name="lblManager" type="text"
                                               value="${demandOrder.officeLocation}" id="lblManager">
                                    </td>
                                    <td style="text-align: right">是否组装:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" name="lblUser" type="text"
                                               value="${demandOrder.assemble=='N'?'否':''}${demandOrder.assemble=='Y'?'是':''}"
                                               id="lblUser">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right;">申请日期:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" name="txtApplyDate" type="text"
                                               value="<fmt:formatDate value="${demandOrder.applyDate}"  pattern="yyyy-MM-dd"/>"
                                               id="txtApplyDate">
                                    </td>
                                    <td style="text-align: right">平台:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" name="lblCom" type="text"
                                               value="${demandOrder.platform}" id="lblCom">
                                    </td>
                                    <td style="text-align: right">是否房屋建筑物:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" name="lblUser" type="text"
                                               value="${demandOrder.building=='N'?'否':''}${demandOrder.building=='Y'?'是':''}"
                                               id="lblUser">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">申请部门:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" name="lblCom" type="text"
                                               value="${demandOrder.applyDepartment}" id="lblCom">
                                    </td>
                                    <td style="text-align: right">区域:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" name="lblCom" type="text"
                                               value="${demandOrder.region}" id="lblCom">
                                    </td>

                                    <td style="text-align: right;">车间类型:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" name="lblManager" type="text"
                                               value="${demandOrder.workShopType}" id="lblManager">
                                    </td>


                                </tr>
                                <tr>
                                    <td style="text-align: right">预算所属部门:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" name="lblPurCode" type="text"
                                               value="${demandOrder.budgetDepartmentName}">
                                    </td>
                                    <td style="text-align: right">成本中心:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" name="lblCom" type="text"
                                               value="${demandOrder.costCenter}" id="lblCom">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
                </br>
                <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle"></div>
                            申购资产需求
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
                                                    <tr style="white-space:nowrap;">
                                                        <th scope="col" class="tdpadding">序号</th>
                                                        <th scope="col" class="tdpadding">项目编码</th>
                                                        <th scope="col" class="tdpadding">预算资产名称</th>
                                                        <th scope="col" class="tdpadding">资产名称</th>
                                                        <th scope="col" class="tdpadding">规格参数</th>
                                                        <th scope="col" class="tdpadding">功能</th>
                                                        <th scope="col" class="tdpadding">资产分类</th>
                                                        <th scope="col" class="tdpadding">选配件</th>
                                                        <th scope="col" class="tdpadding">验收标准</th>
                                                        <th scope="col" class="tdpadding">单位</th>
                                                        <!-- 10 -->
                                                        <th scope="col" class="tdpadding">需求数量</th>
                                                        <th scope="col" class="tdpadding">已申请数量</th>
                                                        <th scope="col" class="tdpadding">未申请数量</th>
                                                        <th scope="col" class="tdpadding">调拨数量</th>
                                                        <th scope="col" class="tdpadding">参考单价</th>
                                                        <th scope="col" class="tdpadding">参考总价</th>
                                                        <th scope="col" class="tdpadding">询价单价</th>
                                                        <th scope="col" class="tdpadding">询价总价</th>

                                                        <th scope="col" class="tdpadding">要求到位日期</th>
                                                        <th scope="col" class="tdpadding">用途说明</th>
                                                    </tr>
                                                    <tbody id="table_detail">
                                                    <c:forEach items="${demandOrderDetailes}" var="demandOrderDetaile"
                                                               varStatus="i">
                                                        <tr class="bodyTr" style="white-space:nowrap;">
                                                            <td class='tdpadding'>
                                                                    ${i.index+1}
                                                                <input type="hidden" name="id" id="id"
                                                                       value="${demandOrderDetaile.id}"/>
                                                                <input type="hidden" name="projectCode" id="projectCode"
                                                                       value="${demandOrderDetaile.projectCode}"/>
                                                                <input type="hidden" name="budgetYear" id="budgetYear"
                                                                       value="${demandOrderDetaile.budgetYear}"/>
                                                            </td>
                                                            <td class='tdpadding'>${demandOrderDetaile.projectCode}</td>
                                                            <td class='tdpadding'>${demandOrderDetaile.budgetAssetsName}</td>
                                                            <td class='tdpadding'>${demandOrderDetaile.assetsName}</td>
                                                            <td class='tdpadding'><!-- 规格参数 -->
                                                                <c:if test="${attachManager == 'Y' || purchasingManager == 'Y'}">
                                                                    <input type="text"
                                                                           value="${demandOrderDetaile.specificationParameter}"
                                                                           name="specificationParameter"
                                                                           id="specificationParameter">
                                                                </c:if>
                                                                <c:if test="${attachManager != 'Y' && purchasingManager != 'Y'}">
                                                                    ${demandOrderDetaile.specificationParameter}
                                                                </c:if>
                                                            </td>
                                                            <td class='tdpadding'>${demandOrderDetaile.functions}</td>
                                                            <td class='tdpadding'><!-- 资产分类 -->
                                                                <c:if test="${platformFinanceManager=='Y'}">
                                                                    <input style="width: 100px;" readonly="readonly"
                                                                           name="assetClassification"
                                                                           value="${demandOrderDetaile.assetClassification}"
                                                                           type="text" id="assetClassification"
                                                                           class="inputbtnRefViewGray"></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanAssets(this);"><input id="btnAssetsShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
                                                                </c:if>
                                                                <c:if test="${platformFinanceManager!='Y'}">
                                                                    ${demandOrderDetaile.assetClassification}
                                                                </c:if>
                                                            </td>
                                                            <td class='tdpadding'><!-- 选配件 -->
                                                                <c:if test="${attachManager=='Y'}">
                                                                    <input type="text"
                                                                           value="${demandOrderDetaile.optionalAccessories}"
                                                                           name="optionalAccessories"
                                                                           id="optionalAccessories">
                                                                </c:if>
                                                                <c:if test="${attachManager!='Y'}">
                                                                    ${demandOrderDetaile.optionalAccessories}
                                                                </c:if>
                                                            </td>
                                                            <!-- td的ID被占用 只能用这些没用的td -->
                                                            <td class='tdpadding' id="tdDemandCount"
                                                                v='${demandOrderDetaile.demandCount}'>${demandOrderDetaile.acceptanceCriteria}</td>
                                                            <td class='tdpadding' id="tdReferencePrice"
                                                                v='${demandOrderDetaile.referencePrice}'>${demandOrderDetaile.units}</td>
                                                            <!-- 10 -->
                                                            <td class='tdpadding'
                                                                id="demandCount">${demandOrderDetaile.demandCount}</td>
                                                            <td class='tdpadding' id="tdInquiryPrice"
                                                                v='${demandOrderDetaile.inquiryPrice}'>${demandOrderDetaile.applyCount}</td>
                                                            <td class='tdpadding'>${demandOrderDetaile.notApplyCount}</td>
                                                            <td class='tdpadding' id="allocationCount">
                                                                <c:if test="${attachManager=='Y'}">
                                                                    <input type="text"
                                                                           value="${demandOrderDetaile.allocationCount}"
                                                                           name="allocationCount" class="rightalign"
                                                                           id="allocationCount" size="5">
                                                                    <input type="hidden"
                                                                           value="${demandOrderDetaile.demandCount}"
                                                                           name="demandCount" class="rightalign"
                                                                           id="demandCount" size="5">
                                                                    <input type="hidden"
                                                                           value="${demandOrderDetaile.inquiryUnitPrice==null?demandOrderDetaile.referenceUnitPrice:demandOrderDetaile.inquiryUnitPrice}"
                                                                           name="inquiryUnitPrice" class="rightalign"
                                                                           id="txtInquiryUnitPrice" size="5">
                                                                    <input type="hidden"
                                                                           value="${demandOrderDetaile.inquiryPrice}"
                                                                           name="inquiryPrice" class="rightalign"
                                                                           id="txtInquiryPrice" size="5">
                                                                    <input type="hidden"
                                                                           value="${demandOrderDetaile.referenceUnitPrice}"
                                                                           name="" class="rightalign"
                                                                           id="txtReferenceUnitPrice" size="5">
                                                                    <input type="hidden"
                                                                           value="${demandOrderDetaile.referencePrice}"
                                                                           name="referencePrice" class="rightalign"
                                                                           id="txtReferencePrice" size="5">
                                                                </c:if>
                                                                <c:if test="${attachManager!='Y'}">
                                                                    <input type="text"
                                                                           value="${demandOrderDetaile.allocationCount}"
                                                                           name="allocationCount" readonly="readonly" class="rightalign"
                                                                           id="allocationCount" size="5">
                                                                </c:if>
                                                            </td>
                                                            <td class='tdpadding'>${demandOrderDetaile.referenceUnitPrice}</td>
                                                            <td class='tdpadding'>${demandOrderDetaile.referencePrice}</td>
                                                            <td class='tdpadding'><!-- 询价单价 -->
                                                                <c:if test="${purchasingManager=='Y'}">
                                                                    <input type="text"
                                                                           value="${demandOrderDetaile.inquiryUnitPrice== null ?demandOrderDetaile.referenceUnitPrice:demandOrderDetaile.inquiryUnitPrice}"
                                                                           name="inquiryUnitPrice" class="rightalign"
                                                                           id="inquiryUnitPrice" size="5">
                                                                </c:if>
                                                                <c:if test="${purchasingManager!='Y'}">

                                                                    <input type="text" readonly="readonly" name="inquiryUnitPrice" readonly="readonly" value="${demandOrderDetaile.inquiryUnitPrice}" size="5">
                                                                </c:if>
                                                            </td>
                                                            <td class='tdpadding'><!-- 询价总价 -->
                                                                <c:if test="${purchasingManager=='Y'}">
                                                                    <input type="text" readonly
                                                                           value="${demandOrderDetaile.inquiryPrice== null ?demandOrderDetaile.referencePrice:demandOrderDetaile.inquiryPrice}"
                                                                           name="inquiryPrice" class="rightalign"
                                                                           id="inquiryPrice" size="5">
                                                                </c:if>
                                                                <c:if test="${purchasingManager!='Y'}">

                                                                    <input type="text" readonly="readonly" name="inquiryPrice" readonly="readonly" value="${demandOrderDetaile.inquiryPrice}" size="5">
                                                                </c:if>
                                                            </td>

                                                            <td class='tdpadding'><fmt:formatDate
                                                                    value="${demandOrderDetaile.requirementsDate}"
                                                                    pattern="yyyy-MM-dd"/></td>
                                                            <td class='tdpadding'>${demandOrderDetaile.useDescription}</td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                    <tr style="white-space: nowrap;">
                                                        <td>合计</td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td id="tdSumDemandCount"></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td id="tdSumReferencePrice"></td>
                                                        <td></td>
                                                        <td id="tdSumInquiryPrice"></td>
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
        <div title="立项书" data-options="closable:false" style="overflow:auto;padding:20px;">
            <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
                <tbody>
                <tr>
                    <td class="titlefontsize" align="left">
                        <div class="imgtitle"></div>
                        立项书 &nbsp;&nbsp;
                        <font color="red">*</font><font color="black">是否立项：
                        <input type="radio" disabled id="txtProjectProposal" name="txtProjectProposal"
                               value="Y" ${demandOrder.projectProposal=="Y"?"checked='checked'" : ""}>是
                        <input type="radio" disabled id="txtProjectProposal" name="txtProjectProposal"
                               value="N" ${demandOrder.projectProposal=="N"?"checked='checked'" : ""}>否
                    </font>
                        </font>
                    </td>
                </tr>
                <tr>
                    <td>

                        <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
                            <tbody>
                            <tr>
                                <td style="text-align: right">项目名称:</td>
                                <td>
                                    <input readonly name="txtEntryName" type="text" value="${demandOrder.entryName}"
                                           id="txtEntryName">
                                </td>
                                <td style="text-align: right">立项金额:</td>
                                <td>
                                    <input readonly name="txtProjectMoney" type="text"
                                           value="${demandOrder.projectMoney==0?'':demandOrder.projectMoney}"
                                           id="txtProjectMoney">
                                </td>
                                <td style="text-align: right">项目背景:</td>
                                <td>
                                    <input readonly name="txtProjectBackground" type="text"
                                           value="${demandOrder.projectBackground}" id="txtProjectBackground">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">收益核算方式:</td>
                                <td>
                                    <input readonly name="txtIncomeAccountingMethod" type="text"
                                           value="${demandOrder.incomeAccountingMethod}" id="txtIncomeAccountingMethod">
                                </td>
                                <td style="text-align: right">年收益金额:</td>
                                <td>
                                    <input readonly name="txtAnnualIncomeAmount" type="text"
                                           value="${demandOrder.annualIncomeAmount==0?'':demandOrder.annualIncomeAmount}"
                                           id="txtAnnualIncomeAmount">
                                </td>
                                <td style="text-align: right">投资回收期:</td>
                                <td>
                                    <input readonly name="txtPaybackPeriodOfInvestment" type="text"
                                           value="${demandOrder.paybackPeriodOfInvestment}"
                                           id="txtPaybackPeriodOfInvestment">
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right"><font color="red">*</font>项目类别:</td>
                                <td colspan="5">
                                    <input disabled type="checkbox" value="外发转自制" name="txtProjectType">外发转自制
                                    <input disabled type="checkbox" value="产能提升" name="txtProjectType">产能提升
                                    <input disabled type="checkbox" value="替代人力" name="txtProjectType">替代人力
                                    <input disabled type="checkbox" value="效率提升" name="txtProjectType">效率提升
                                    <input disabled type="checkbox" value="能力建设" name="txtProjectType">能力建设
                                    <input disabled type="checkbox" value="旧设备替换" name="txtProjectType">旧设备替换
                                    <input disabled type="checkbox" value="基建" name="txtProjectType">基建
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right"></td>
                                <td colspan="5">
                                    <input disabled type="checkbox" value="其他" name="txtProjectType">其他
                                    <input readonly id="txtProjectTypeOther" type="text" value="" name="txtProjectType"
                                           size="70" maxlength="100">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>

                </tbody>
            </table>
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
                                <table id="uploadTable2" class="adminlist" cellspacing="0" rules="all" border="1"
                                       id="gvDataList2" style="border-collapse:collapse;">
                                    <thead>
                                    <tr style="white-space:nowrap;">
                                        <th scope="col">
                                            <input type="checkbox" name="gvDataList$ctl01$CheckAll"
                                                   onclick="javascript:SelectAllCheckboxesByTableId(this, 'uploadTable2');">
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

    </div>
</div>

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
                                             style="overflow-x: scroll; overflow-y: visible; height: 310px;">
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
<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>--%>
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>
<script type="text/javascript">
    var dg = frameElement.lhgDG;//定义dg 关闭需要
    var purchasingManager = "N";//采购经理
    var attachManager = "N";//归口经理
    var platformFinanceManager = "N";//平台财务经理

    //给立项书多选框赋值
    var strProjectType = "${demandOrder.projectType}";
    var strs = strProjectType.split("-*-*-"); //字符分割
    $.each(strs, function (i, item) {
        if ($("input[name='txtProjectType'][value=" + item + "]").length > 0) {
            $("input[name='txtProjectType'][value=" + item + "]").attr("checked", "checked");
        } else {
            $("#txtProjectTypeOther").val($("#txtProjectTypeOther").val() + item + " ");
        }
    });
    $('#btnUpload').click(function () {
        var fileElementId = $(this).prev().attr('id');
        var file = $('#fileToUpload').val();
        if (file == "") {
            alert("请选择需要上传的文件！");
            return false;
        }

        var documentNo = $('#txtDemandOrderId').val();
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
            data: {'document': $("#txtDemandOrderId").val()},
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

    //键入询价单价  计算询价总价
    $("#table_detail").delegate("input[id='inquiryUnitPrice']", "keyup", function () {
        var inquiryUnitPrice = $(this).val()-0;
        var demandCount1 = $(this).parent().parent().find("td[id='demandCount']").html()-0;
        var allocationCount1 = $(this).parent().parent().find("input[id='allocationCount']").html()-0;
        var count =  demandCount1-allocationCount1;

        $(this).parent().parent().find("input[id='inquiryPrice']").val((inquiryUnitPrice * count - 0).toFixed(2));
        //sumAll();
    });

    function sumAll() {

        /* var sumInquiryPrice = 0;
         $("#inquiryPrice").each(function(){
         var inquiryPrice = $(this).val();
         sumInquiryPrice += inquiryPrice-0;
         });
         $("#tdSumInquiryPrice").html(sumInquiryPrice); */
    }

    //询价总和合计
    var sumInquiryPrice;
    function checkAll() {
        sumInquiryPrice = 0;
        var flag = false;
        $("input[id=allocationCount]").each(function () {
            var allocationCount = $.trim($(this).val());
            if (allocationCount == "" || isNaN(allocationCount)) {
                alert("调拨数量必须是数字");
                flag = true;
                return false;
            }
            var demandCount = $.trim($(this).parent().parent().find("#demandCount").html());
            if (parseInt(allocationCount) > parseInt(demandCount)) {
                alert("调拨数量不能大于需求数量!");
                flag = true;
                return false;
            }
        });
        if (flag) {
            return false;
        }
        $("input[id=inquiryPrice]").each(function () {
            var inquiryPrice = $.trim($(this).val());
            if (inquiryPrice == "" || isNaN(inquiryPrice)) {
                alert("询价价格必须是数字");
                flag = true;
                return false;
            }
            sumInquiryPrice += inquiryPrice - 0;
        });
        sumInquiryPrice = sumInquiryPrice.toFixed(2);
        if (flag) {
            return false;
        }
        return true;
    }
    $("#btnAgree").click(function () {
        if (checkAll()) {
            showAgreeDialog();
        }
    });
    $('#btnDeny').click(function () {
        showDenyDialog();
    });
    $('#btnDrop').click(function () {
        showDropDialog();
    });

    //点击的资产分类按钮
    var $inputAssets;
    //选择资产分类
    $("input[id=btnAssetsShow]").click(function () {
        $inputAssets = $(this);
        var top = "75px";
        var left = "15px";
        $('#addAssetsWindow').window('open').window('resize', {width: '450px', height: '300px', top: top, left: left});
        searchDemandOrderDetail();
    });
    //清除  "X"
    function cleanAssets(btn) {
        $(btn).parent().find("#assetClassification").val("");
    }

    function searchDemandOrderDetail() {
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/assetClassification/api/searchAssetClassificationForDemand",
            success: function (data) {
                var divHtml = "";
                for (var i = 0; i < data.length; i++) {
                    divHtml += "<tr style='white-space:nowrap;'>";
                    if (i == 0) {
                        divHtml += "<td class='tdpadding'><input type='radio' checked='checked' name='btnRadio' ></td>";
                    } else {
                        divHtml += "<td class='tdpadding'><input type='radio' name='btnRadio' ></td>";
                    }
                    divHtml += "<td class='tdpadding'><input type='text' name='assetClassification' id='assetClassification' value='" + data[i].assetClassification + "' readonly='readonly' size='5' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='assetsName' id='assetsName' value='" + data[i].assetsName + "' readonly='readonly' size='10' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='assetstypeInfo' id='assetstypeInfo' value='" + data[i].assetstypeInfo + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='depreciationPeriod' id='depreciationPeriod' value='" + data[i].depreciationPeriod + "' readonly='readonly' size='5' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='netResidualRate' id='netResidualRate'  value='" + data[i].netResidualRate + "' readonly='readonly' size='5' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='managementDepartment' id='managementDepartment' value='" + data[i].managementDepartment + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='generalLedgerAccount' id='generalLedgerAccount' value='" + data[i].generalLedgerAccount + "' readonly='readonly' size='13'  ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='accountDescription' id='accountDescription' value='" + data[i].accountDescription + "' readonly='readonly' ></td>";
                    divHtml += "</tr>";
                }
                $("#tbody_add_detail").html(divHtml);
            }
        });
    }
    $("#btnDetailSave").click(function () {
        $('#addAssetsWindow').window('close');
        var assetClassification = $('input:radio[name="btnRadio"]:checked').parent().parent().find("#assetClassification").val();
        $inputAssets.parent().find("#assetClassification").val(assetClassification);
    });

    $("#table_detail").delegate("input[id='allocationCount']", "keyup", function () {
        var demandCount = $(this).parent().find("input[id='demandCount']").val();
        var inquiryUnitPrice = $(this).parent().find("input[id='txtInquiryUnitPrice']").val();
        $(this).parent().find("input[id='txtInquiryPrice']").val(((demandCount - $(this).val() - 0) * inquiryUnitPrice - 0).toFixed(2));
        var referenceUnitPrice = $(this).parent().find("input[id='txtReferenceUnitPrice']").val();
        $(this).parent().find("input[id='txtReferencePrice']").val(((demandCount - $(this).val() - 0) * referenceUnitPrice - 0).toFixed(2));
        //sumAll();
    });
    function showAgreeDialog() {
        var document = $("#txtDemandOrderId").val();
        var url = "${dynamicURL}/workflow/showAgreeDialog?parentWindowId=examineDialog&document=" + document + "&typeId=77001";

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
        var document = $("#txtDemandOrderId").val();
        var url = "${dynamicURL}/workflow/showDenyDialog?parentWindowId=examineDialog&document=" + document + "&typeId=77001";

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
    function commitAgree(isMail, isPhoneMessage, idea, activityId) {
        $('#btnAgree').val('提交...');

        var demandOrderModel = {};
        demandOrderModel.cdocument = $("#txtDemandOrderId").val();
        demandOrderModel.idea = idea;
        demandOrderModel.isMail = isMail;
        demandOrderModel.activityId = activityId;
        //demandOrderModel.activityId = decodeURI(activityId);
        demandOrderModel.isPhoneMessage = isPhoneMessage;
        if (purchasingManager == "Y") {
            demandOrderModel.iamoney = sumInquiryPrice;
        }
        var demandOrderExamineWrapper = {};
        demandOrderExamineWrapper.demandOrderModel = demandOrderModel;

        demandOrderExamineWrapper.demandOrderDetails = null;
        var obj = null;
        if (attachManager == "Y") {
            obj = {
                id: "",
                projectCode: "",
                budgetYear: "",
                specificationParameter: "",
                optionalAccessories: "",
                allocationCount: "",
                inquiryPrice: "",
                inquiryUnitPrice: "",
                referencePrice: ""

            };
            demandOrderExamineWrapper.demandOrderDetails = constructObjectFromTable(obj, $("#table_detail .bodyTr"));
        }
        if (purchasingManager == "Y") {
            obj = {
                id: "",
                projectCode: "",
                budgetYear: "",
                specificationParameter: "",
                inquiryUnitPrice: "",
                inquiryPrice: "",
                allocationCount: ""
            };
            demandOrderExamineWrapper.demandOrderDetails = constructObjectFromTable(obj, $("#table_detail .bodyTr"));
        }
        if (platformFinanceManager == "Y") {
            obj = {
                id: "",
                assetClassification: "",
                inquiryUnitPrice: "",
                allocationCount: ""
            };
            demandOrderExamineWrapper.demandOrderDetails = constructObjectFromTable(obj, $("#table_detail .bodyTr"));
        }
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/demand/api/agreeDemand",
            data: JSON.stringify(demandOrderExamineWrapper),
            success: function (data) {
                if (data.success) {
                    alert("审批成功");
//                    dg.curWin.location=dg.curWin.location;;
                    dg.curWin.location = dg.curWin.location;
                } else {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*审批成功.*$");
                    if (reg.test(str)) {
                        dg.curWin.location = dg.curWin.location;
                    }
                    $('#btnAgree').val('同意');
                    $("#btnAgree").attr("disabled", false);
                }
            }
        });
    }
    function commitDeny(isMail, isPhoneMessage, idea, activityId) {

        $('#btnDeny').val('提交...');

        var demandOrderModel = {};
        demandOrderModel.cdocument = $("#txtDemandOrderId").val();
        demandOrderModel.idea = idea;
        demandOrderModel.isMail = isMail;
        demandOrderModel.isPhoneMessage = isPhoneMessage;
        demandOrderModel.activityId = activityId;
        //console.log(demandOrderModel);
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/demand/api/denyDemand",
            data: JSON.stringify(demandOrderModel),
            success: function (data) {
                if (data.success) {
                    alert("审批成功");
                    dg.curWin.location = dg.curWin.location;
                } else {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*审批成功.*$");
                    if (reg.test(str)) {
                        dg.curWin.location = dg.curWin.location;
                    }
                    $('#btnDeny').val('退回');
                    $("#btnDeny").attr("disabled", false);
                }
            }
        });
    }
    function commitDrop(isMail, isPhoneMessage, idea) {

        $('#btnDrop').val('提交...');

        var demandOrderModel = {};
        demandOrderModel.cdocument = $("#txtDemandOrderId").val();
        demandOrderModel.idea = idea;
        demandOrderModel.isMail = isMail;
        demandOrderModel.isPhoneMessage = isPhoneMessage;
        //console.log(demandOrderModel);
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/demand/api/dropDemand",
            data: JSON.stringify(demandOrderModel),
            success: function (data) {
                if (data.success) {
                    alert("审批成功");
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
    $(function () {
        var tdSumDemandCount = 0;
        $("td[id='tdDemandCount']").each(function () {
            var tdDemandCount = $.trim($(this).attr("v"));
            tdSumDemandCount += tdDemandCount - 0;
        });
        $("#tdSumDemandCount").html(tdSumDemandCount);

        var tdSumReferencePrice = 0;
        $("td[id='tdReferencePrice']").each(function () {
            var tdReferencePrice = $.trim($(this).attr("v"));
            tdSumReferencePrice += tdReferencePrice - 0;
        });
        $("#tdSumReferencePrice").html(tdSumReferencePrice.toFixed(2));

        var tdSumInquiryPrice = 0;
        $("td[id='tdInquiryPrice']").each(function () {
            var tdInquiryPrice = $.trim($(this).attr("v"));
            tdSumInquiryPrice += tdInquiryPrice - 0;
        });
        $("#tdSumInquiryPrice").html(tdSumInquiryPrice.toFixed(2));
    });
    $(document).ready(function () {
        $(window).resize(function () {
            $('#purchaseTab').tabs('resize');
        });
    });
    function stamp(id){

        var lurl = "${dynamicURL}/demand/printOrder?id=" + id + "&typeId=77001";
        window.open(lurl);

    }

</script>
</body>
</html>