<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        采购申请单
    </div>
</div>
<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
    <input type="button" name="btnSave" value="提交" onclick="" id="btnSave" class="btn">
    <c:if test="${applyOrder.draft=='Y'}">
        <input type="button" name="btnSaveDraft" value="保存草稿" onclick="" id="btnSaveDraft" class="btn">
    </c:if>
    <input type="button" name="btnCancel" value="返回" id="btnCancel" class="btn" onclick="dg.cancel()">
</div>
<div style="margin-top: 10px;width:100%;overflow :auto;">

    <div id="purchaseTab" class="easyui-tabs" >
        <div title="申请信息" id="tabsa" style="overflow:auto; padding:20px;min-width: 800px;">
            <div>
                <input type="hidden" id="deptName">
                <input type="hidden" id="deptCode">
                <form id="applyOrderFormHeader">
                    <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%">
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
                                        <input name='' type='hidden' value='${applyOrder.orderMoney}'
                                               id='oldOrderMoney'>
                                        <input name='attachDepartManagerName' type='hidden'
                                               value='${applyOrder.attachDepartManagerName}'
                                               id='attachDepartManagerName'>
                                        <input name='attachDepartManagerCode' type='hidden'
                                               value='${applyOrder.attachDepartManagerCode}'
                                               id='attachDepartManagerCode'>
                                        <input name='attachDepartAdminName' type='hidden'
                                               value='${applyOrder.attachDepartAdminName}' id='attachDepartAdminName'>
                                        <input name='attachDepartAdminCode' type='hidden'
                                               value='${applyOrder.attachDepartAdminCode}' id='attachDepartAdminCode'>

                                        <td style="text-align: right">单据号:</td>
                                        <td>
                                            <input class="searchinput" readonly name="cdocument" type="text"
                                                   value="${applyOrder.cdocument}" id="txtApplyOrderId">
                                        </td>
                                        <td style="text-align: right"><font color="red">*</font>所属公司:</td>
                                        <td>
                                            <select id="txtCompanyCode" class="searchselect" name='companyCode'>
                                                <option selected="selected" value="">--请选择--</option>
                                                <c:forEach items="${companys }" var="comp" varStatus="status">
                                                    <option value="${comp.ccompanycode}" ${comp.ccompanycode == applyOrder.companyCode ? "selected" : ""}>
                                                        (${comp.ccompanycode})${comp.ccompanyname}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td style="text-align: right"><font color="red">*</font>成本中心:</td>
                                        <td class="tdtext">
								<span id="spanCostCenter">
									<input readonly="readonly" class="searchinput" style="background-color: #CCCCCC;"
                                           name="costCenter" type="text" value="" id="txtCostCenter">
									<input readonly="readonly" class="searchinput" style="background-color: #CCCCCC;"
                                           name="costCenterCode" type="hidden" value="" id="txtCostCenterCode">
								</span>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td style="text-align: right">申请人:</td>
                                        <td>
                                            <input class="searchinput" readonly="readonly"
                                                   value='${applyOrder.applyUser}' name="applyUser" type="text" value=""
                                                   id="applyUser">
                                            <input class="searchinput" readonly="readonly"
                                                   value='${applyOrder.applyUserCode}' name="applyUserCode"
                                                   type="hidden" value="" id="applyUserCode">
                                        </td>
                                        <td style="text-align: right"><font color="red">*</font>归口/预算部门:</td>
                                        <td>
                                            <span id="spanBudgetDepartment"><input style="width: 100px;"
                                                                                   readonly="readonly"
                                                                                   name="budgetDepartmentName"
                                                                                   value="${applyOrder.budgetDepartmentName}"
                                                                                   type="text" id="txtBudgetDepartment"
                                                                                   class="inputbtnRefViewGray"></span><input
                                                id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button'
                                                value='X' onclick="cleanDep();"><input id="btntxtYsssbmShow"
                                                                                       class='btnRefView'
                                                                                       title='点击打开参照文本框内容' type='button'
                                                                                       value='…'>
                                            <input name="budgetDepartmentCode"
                                                   value="${applyOrder.budgetDepartmentCode}" type="hidden"
                                                   id="txtYsssbm" class="inputbtnRefViewGray">
                                        </td>
                                        <%-- <c:choose>
                                            <c:when test="${applyOrder.assetType=='IT资产'}">
                                                <td id="companyForOther" style="display: none">
                                                <span id="spanCompany">
                                                    <input readonly='readonly' class='searchinput' style="background-color: #CCCCCC;" name='companyName' type='text' value='${applyOrder.companyName }' id='txtCompany' onclick='ysssbmCom()'>
                                                    <input name='companyCode' type='hidden' value='${applyOrder.companyCode }' id='txtCompanyCode'>
                                                </span>
                                                </td>
                                                <td id="companyForIt" >
                                                    <select class="searchselect"  id="company">
                                                     <option selected="selected" value="">--请选择--</option>
                                                        <c:forEach items="${companys }" var="comp" varStatus="status">
                                                        <option value="${comp.ccompanycode },${comp.ccompanyname }" ${comp.ccompanycode == applyOrder.companyCode ? "selected" : ""}>${comp.ccompanyname }</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td id="companyForOther" >
                                                    <span id="spanCompany">
                                                        <input readonly='readonly' class='searchinput' style="background-color: #CCCCCC;" name='companyName' type='text' value='${applyOrder.companyName }' id='txtCompany' onclick='ysssbmCom()'>
                                                        <input name='companyCode' type='hidden' value='${applyOrder.companyCode }' id='txtCompanyCode'>
                                                    </span>
                                                    </td>
                                                    <td id="companyForIt" style="display: none">
                                                        <select class="searchselect"  id="company">
                                                         <option selected="selected" value="">--请选择--</option>
                                                            <c:forEach items="${companys }" var="comp" varStatus="status">
                                                            <option value="${comp.ccompanycode },${comp.ccompanyname }" >${comp.ccompanyname }</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                            </c:otherwise>
                                        </c:choose> --%>

                                        <td id="tdAssemble" style="text-align: right;">是否组装:</td>
                                        <td id="tdAssembles">
                                            <input type="radio" id="txtAssemble" name="assemble"
                                                   value="Y" ${applyOrder.assemble=="Y"?" checked='checked' ":""}>是
                                            <input type="radio" id="txtAssemble" name="assemble"
                                                   value="N" ${applyOrder.assemble=="N"?" checked='checked' ":""}>否

                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right;">申请日期:</td>
                                        <td class="tdtext">
                                            <input class="searchinput" readonly="readonly" name="applyDate" type="text"
                                                   value="<fmt:formatDate value='${applyOrder.applyDate}'  pattern='yyyy-MM-dd'/>"
                                                   id="txtApplyDate">
                                        </td>
                                        <td style="text-align: right;"><font color="red">*</font>办公地点:</td>
                                        <td class="tdtext">
                                            <select class="searchselect" name="officeLocation" id="txtOfficeLocation">
                                                <option selected="selected" value="">--请选择--</option>
                                                <c:forEach items="${addresslist }" var="o" varStatus="status">
                                                    <option value="${o.caddressName }">${o.caddressName }</option>
                                                </c:forEach>
                                            </select>
                                        </td>

                                        <td id="tdBuilding" style="text-align: right;">是否房屋建筑物:</td>
                                        <td id="tdBuildings">
                                            <input type="radio" id="txtBuilding" name="building"
                                                   value="Y" ${applyOrder.building=="Y"?" checked='checked' ":""}>是
                                            <input type="radio" id="txtBuilding" name="building"
                                                   value="N" ${applyOrder.building=="N"?" checked='checked' ":""}>否
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right"><font color="red">*</font>申请人部门:</td>
                                        <td class="tdtext">
                                            <input class="searchinput" readonly="readonly" name="applyDepartment"
                                                   type="text" value="${applyOrder.applyDepartment}"
                                                   id="txtApplyDepartment">
                                            <input class="searchinput" readonly="readonly" name="applyDepartmentCode"
                                                   type="hidden" value="${applyOrder.applyDepartmentCode}"
                                                   id="txtApplyDepartmentCode">
                                        </td>
                                        <td style="text-align: right"><font color="red">*</font>平台:</td>
                                        <td class="tdtext">
                                            <input readonly='readonly' class='searchinput'
                                                   style="background-color: #CCCCCC;" name='platform' type='text'
                                                   value='' id='txtPlatform'>
                                        </td>

                                        <td id="tdWorkShopType" style="text-align: right;">车间类型:</td>
                                        <td id="tdWorkShopTypes">
                                            <input type="text" class="searchselect" value='${applyOrder.workShopType}'
                                                   style="background-color: #CCCCCC;" name="workShopType"
                                                   id="txtWorkShopType" readonly="readonly"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right"><font color="red">*</font>资产类型:</td>
                                        <td>
                                            <select class="searchselect" name="assetType" id="txtAssetType1">
                                                <c:forEach items="${attachConfigList}" var="attachConfig"
                                                           varStatus="status">
                                                    <option value="${attachConfig.assetType}" ${attachConfig.assetType == applyOrder.assetType ? "selected" : ""}>${attachConfig.assetType}</option>
                                                </c:forEach>
                                                <%--<option value="行政资产">行政资产</option>--%>
                                                <%--<option value="IT资产">IT资产</option>--%>
                                                <%--<option value="计量器具">计量器具</option>--%>
                                                <%--<option value="机器设备">机器设备</option>--%>
                                            </select>
                                        </td>
                                        <td style="text-align: right">区域:</td>
                                        <td class="tdtext">
                                            <select class="searchselect" style="background-color: #CCCCCC;"
                                                    name="region" id="txtRegion" onclick="selectPlat()">
                                            </select>
                                        </td>
                                        <td style="text-align: right">一次性费用化资产:</td>
                                        <td>
                                            <input type="radio" id="rdExpensing" name="expensing"
                                                   value="Y" ${applyOrder.expensing=="Y"?" checked='checked' ":""}>是
                                            <input type="radio" id="rdExpensing" name="expensing"
                                                   value="N" ${applyOrder.expensing=="N"?" checked='checked' ":""}>否
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
                </br>
                <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle"></div>
                            资产申购需求
                        </td>
                    </tr>
                    <tr>
                        <td class="titlefontsize" align="left" colspan="3">
                            <input id="addRows" type="button" value="选择明细">
                            <input id="delRows" type="button" value="删行"> &nbsp;&nbsp;<font color="red">['本次申请数量'只能在明细中修改]</font>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <table id="Table10" border="0" cellpadding="0" cellspacing="0"
                                   style="table-layout: fixed;width:100%;">
                                <tbody>
                                <tr>
                                    <td class="tdtitle" style="text-align: right" colspan="2">
                                        <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                            <div>
                                                <table id="table_detail" style="width:825px;" class="adminlist"
                                                       cellspacing="0" rules="all" border="1" id="gridDataList"
                                                       style="border-collapse:collapse;">

                                                    <tr style="white-space:nowrap;">
                                                        <th scope="col">
                                                            <input id="gvDataList_CheckAll" type="checkbox"
                                                                   name="gvDataList$ctl01$CheckAll"
                                                                   onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_detail');"/>
                                                        </th>
                                                        <th scope="col" class="tdpadding">合并号</th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>资产名称
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>规格参数
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>功能
                                                        </th>
                                                        <th scope="col" class="tdpadding">资产分类</th>
                                                        <!-- 5 -->
                                                        <th scope="col" class="tdpadding">选配件</th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>验收标准
                                                        </th>
                                                        <th scope="col" class="tdpadding">本次申请数量</th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>单位
                                                        </th>
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>询价单价
                                                        </th>
                                                        <!-- 10 -->
                                                        <th scope="col" class="tdpadding"><font color="red">*</font>询价总价
                                                        </th>
                                                        <th scope="col" class="tdpadding">要求到位日期</th>
                                                        <!-- <th scope="col" class="tdpadding"><font color="red" >*</font>采购单价</th>
                                                        <th scope="col" class="tdpadding"><font color="red" >*</font>采购总价</th>
                                                        <th scope="col" class="tdpadding"><font color="red" >*</font>采购回复交期</th>
                                                        15
                                                        <th scope="col" class="tdpadding"><font color="red" >*</font>资产分类</th>
                                                        <th scope="col" class="tdpadding">折旧年限</th>
                                                        <th scope="col" class="tdpadding">编码方式</th>
                                                        <th scope="col" class="tdpadding"><font color="red" >*</font>主资产编码</th>
                                                        <th scope="col" class="tdpadding">次级</th>
                                                        20
                                                        <th scope="col" class="tdpadding"><font color="red" >*</font>总账科目</th> -->
                                                        <th scope="col" class="tdpadding">预算成本中心</th>
                                                    </tr>
                                                    <tbody id="tbody_detail">
                                                    <c:forEach items="${applyOrderDetailes}" var="applyOrderDetaile"
                                                               varStatus="i">
                                                        <tr class='bodyTr' style='white-space:nowrap;'>
                                                            <td class='tdpadding'><input id='gvDataList_chkBolDel_0'
                                                                                         type='checkbox'
                                                                                         name='gvDataList_chkBolDel_0'>
                                                            </td>
                                                            <td class='tdpadding'><input type='text' name='mergeNumber'
                                                                                         id='txtMergeNumber' size='2'
                                                                                         value='${applyOrderDetaile.mergeNumber}'
                                                                                         readonly='readonly'></td>
                                                            <td class='tdpadding'><input type='text' size='10'
                                                                                         name='assetsName'
                                                                                         id='txtAssetsName'
                                                                                         value='${applyOrderDetaile.assetsName}'
                                                                                         readonly='readonly'></td>
                                                            <td class='tdpadding'><input type='text' size='10'
                                                                                         name='specificationParameter'
                                                                                         id='txtSpecificationParameter'
                                                                                         value='${applyOrderDetaile.specificationParameter}'>
                                                            </td>
                                                            <td class='tdpadding'><input type='text' size='10'
                                                                                         name='functions'
                                                                                         id='txtFunctions'
                                                                                         value='${applyOrderDetaile.functions}'>
                                                            </td>
                                                            <td class='tdpadding'><input type='text' class='rightalign'
                                                                                         name='assetClassification'
                                                                                         value='${applyOrderDetaile.assetClassification}'
                                                                                         id='txtAssetClassification'
                                                                                         size='5' readonly='readonly'>
                                                            </td>
                                                            <!-- 5 -->
                                                            <td class='tdpadding'><input type='text' size='10'
                                                                                         name='optionalAccessories'
                                                                                         id='txtOptionalAccessories'
                                                                                         value='${applyOrderDetaile.optionalAccessories}'>
                                                            </td>
                                                            <td class='tdpadding'><input type='text' size='10'
                                                                                         name='acceptanceCriteria'
                                                                                         id='txtAcceptanceCriteria'
                                                                                         value='${applyOrderDetaile.acceptanceCriteria}'>
                                                            </td>
                                                            <td class='tdpadding'><input type='text' class='rightalign'
                                                                                         name='demandCount'
                                                                                         id='txtDemandCount' size='5'
                                                                                         value='${applyOrderDetaile.demandCount}'
                                                                                         readonly='readonly'></td>
                                                            <td class='tdpadding'><input type='text' name='units'
                                                                                         value='${applyOrderDetaile.units}'
                                                                                         id='txtUnits' size='2'
                                                                                         readonly='readonly'></td>
                                                            <td class='tdpadding'><input type='text' class='rightalign'
                                                                                         name='inquiryUnitPrice'
                                                                                         value='${applyOrderDetaile.inquiryUnitPrice}'
                                                                                         id='txtInquiryUnitPrice'
                                                                                         size='5'></td>
                                                            <!-- 10 -->
                                                            <td class='tdpadding'><input type='text' class='rightalign'
                                                                                         name='inquiryPrice'
                                                                                         value='${applyOrderDetaile.inquiryPrice}'
                                                                                         id='txtInquiryPrice' size='5'>
                                                            </td>
                                                            <td class='tdpadding'><input type='text'
                                                                                         name='requirementsDate'
                                                                                         value="<fmt:formatDate value='${applyOrderDetaile.requirementsDate}'  pattern='yyyy-MM-dd'/>"
                                                                                         readonly='readonly'
                                                                                         id='txtRequirementsDate'
                                                                                         size='5' class='Wdate'
                                                                                         onclick="WdatePicker({skin:'ext'});">
                                                            </td>
                                                            <!-- <td></td>
                                                            <td></td>
                                                            <td></td>
                                                            15
                                                            <td></td>
                                                            <td></td>
                                                            <td></td>
                                                            <td></td>
                                                            <td></td>
                                                            20
                                                            <td></td> -->
                                                            <td class='tdpadding'><input type='text' name='costCenter'
                                                                                         value='${applyOrderDetaile.costCenter}'
                                                                                         id='txtCostCenter'
                                                                                         readonly='readonly'><input
                                                                    type='hidden' name='costCenterCode'
                                                                    value='${applyOrderDetaile.costCenterCode}'
                                                                    id='txtCostCenter'></td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                    <tr style="white-space:nowrap;">
                                                        <td>

                                                        </td>
                                                        <td class='tdpadding'>合计</td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td class='tdpadding'><input type="text" class='rightalign'
                                                                                     readonly="readonly"
                                                                                     name="sumInquiryPrice"
                                                                                     id="sumInquiryPrice" size="5"></td>
                                                        <td></td>
                                                        <!-- <td></td>
                                                        <td class='tdpadding'><input type="text" class='rightalign' readonly="readonly" name="sumPurchasePrice" id="sumPurchasePrice" size="5"></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td> -->
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
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
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
                                                    <th class='tdpadding' scope="col">合并号</th>
                                                    <!-- <th class='tdpadding' scope="col"><font color="red" >*</font>预算编号</th>
                                                    <th class='tdpadding' scope="col"><font color="red" >*</font>采购需求单号</th> -->
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>资产名称
                                                    </th>
                                                    <!-- 5 -->
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>规格参数
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>功能</th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>选配件</th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>验收标准
                                                    </th>
                                                    <!-- <th class='tdpadding' scope="col"><font color="red" >*</font>资产类型</th>
                                                    10
                                                    <th class='tdpadding' scope="col">需求部门</th> -->
                                                    <!-- <th class='tdpadding' scope="col"><font color="red" >*</font>需求数量</th>
                                                    <th class='tdpadding' scope="col">调拨数量</th>
                                                    <th class='tdpadding' scope="col">已申请数量</th> -->
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>本次申请数量
                                                    </th>
                                                    <!-- 15 -->
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>单位</th>
                                                    <th class="tdpadding" scope="col"><font color="red">*</font>询价单价
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>要求到位日期
                                                    </th>
                                                    <th class='tdpadding' scope="col">预算成本中心</th>
                                                    <th scope="col"><font color="red">*</font>用途说明</th>
                                                </tr>
                                                <tbody id="tbody_detail_detail">
                                                <c:forEach items="${applyDemandMappingModels}" var="admm" varStatus="i">
                                                    <tr class='bodyTr' style="white-space:nowrap;">
                                                        <td class='tdpadding'><input type='text' name='mergeNumber'
                                                                                     value='${admm.mergeNumber}'
                                                                                     id='txtDetailMergeNumber' size='1'
                                                                                     readonly='readonly'></td>
                                                        <td class='tdpadding'><input type='hidden' name='demandDetailId'
                                                                                     id='txtDemandDetailId'
                                                                                     value='${admm.demandDetailId}'><input
                                                                type='text' name='assetsName' id='txtAssetsName'
                                                                value='${admm.assetsName}' readonly='readonly'></td>
                                                        <td class='tdpadding'><input type='text'
                                                                                     name='specificationParameter'
                                                                                     id='txtSpecificationParameter'
                                                                                     value='${admm.specificationParameter}'
                                                                                     readonly='readonly'></td>
                                                        <td class='tdpadding'><input type='text' name='functions'
                                                                                     id='txtFunctions'
                                                                                     value='${admm.functions}'
                                                                                     readonly='readonly'></td>
                                                        <td class='tdpadding'><input type='text'
                                                                                     name='optionalAccessories'
                                                                                     id='txtOptionalAccessories'
                                                                                     value='${admm.optionalAccessories}'
                                                                                     readonly='readonly'></td>
                                                        <td class='tdpadding'><input type='text'
                                                                                     name='acceptanceCriteria'
                                                                                     id='txtAcceptanceCriteria'
                                                                                     value='${admm.acceptanceCriteria}'
                                                                                     readonly='readonly'></td>
                                                            <%-- <td class='tdpadding'>${admm.assetType}</td>
                                                            <td class='tdpadding'>${admm.applyDepartment}</td> --%>
                                                        <td class='tdpadding'><input class='rightalign' type='text'
                                                                                     name='demandCount'
                                                                                     value='${admm.demandCount}'
                                                                                     id='txtThisApplyCount' size='5'
                                                                                     readonly='readonly'></td>
                                                        <td class='tdpadding'><input type='text' name='units'
                                                                                     value='${admm.units}' id='txtUnits'
                                                                                     size='2' readonly='readonly'></td>
                                                        <td class='tdpadding'><input type='text' name='inquiryUnitPrice'
                                                                                     value='${admm.inquiryUnitPrice}'
                                                                                     size='5' readonly='readonly'></td>
                                                        <td class='tdpadding'><input type='text' name='requirementsDate'
                                                                                     value='<fmt:formatDate value='${admm.requirementsDate}'  pattern='yyyy-MM-dd'/>'
                                                                                     id='txtRequirementsDate' size='6'
                                                                                     readonly='readonly'></td>
                                                        <td class='tdpadding'><input type='text' name='costCenter'
                                                                                     value='${admm.costCenter}'
                                                                                     id='txtCostCenter'
                                                                                     readonly='readonly'><input
                                                                type='hidden' name='costCenterCode'
                                                                value='${admm.costCenterCode}' id='txtCostCenterCode'>
                                                        </td>
                                                        <td class='tdpadding'><input type='text' name='useDescription'
                                                                                     value='${admm.useDescription}'
                                                                                     id='txtUseDescription'
                                                                                     readonly='readonly'></td>
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


<!-- 点击増行弹出form -->

<div id="addApplyWindow" name="" class="easyui-window" title="添加申请" data-options="iconCls:'icon-save'"
     style="width:800px;height:440px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">

            <form action="addPurchaseApplyOrderDetail" id="addPurchaseApplyOrderDetailForm">
                <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="70%">
                    <tbody>
                    <tr>
                        <td align="right" class="tdtitle">资产名称:
                        </td>
                        <td class="tdtext">
                            <input name="txtAssetsName" type="text" id="" class="colorblur">
                        </td>
                        <td align="right" class="tdtitle">申请日期:
                        </td>
                        <td class="tdtext">
                            <input name="txtBeginDate" type="text" id="txtBeginDate" class="Wdate"
                                   onclick="WdatePicker({skin:'ext'});">~<input name="txtEndDate" type="text"
                                                                                id="txtEndDate" class="Wdate"
                                                                                onclick="WdatePicker({skin:'ext'});">

                        </td>
                    </tr>
                    <tr>
                        <td align="right" class="tdtitle">申请人:
                        </td>
                        <td class="tdtext">
                            <input name="txtApplyUser" type="text" id="txtApplyUser" class="colorblur">
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
                                                        <th class='tdpadding' scope="col">合并号</th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>预算编号
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>采购需求单号
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>资产名称
                                                        </th>
                                                        <!-- 5 -->
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>规格参数
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>功能
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>选配件
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>验收标准
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>资产类型
                                                        </th>
                                                        <!-- 10 -->
                                                        <th class='tdpadding' scope="col">需求部门</th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>需求数量
                                                        </th>
                                                        <th class='tdpadding' scope="col">调拨数量</th>
                                                        <th class='tdpadding' scope="col">已申请数量</th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>本次申请数量
                                                        </th>
                                                        <!-- 15 -->
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>单位
                                                        </th>
                                                        <th class="tdpadding" scope="col"><font color="red">*</font>询价单价
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>要求到位日期
                                                        </th>
                                                        <th class='tdpadding' scope="col">预算成本中心</th>
                                                        <th scope="col"><font color="red">*</font>用途说明</th>
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

<div id="ysssbmWindow" name="" class="easyui-window" title="预算所属部门" data-options="iconCls:'icon-save'"
     style="width:820px;height:450px;border: 0px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="border: 0px;">
            <!-- <ul id="tree1"></ul>  -->
            <form action="" id="budgetDeptForm">
                <table width="100%">
                    <tbody>
                    <tr>
                        <td align="right" class="tdtitle" style="width:10%;">部门:</td>
                        <td class="tdtext">
                            <input name="departmentName" value="${param.departmentName}" type="text" id="dept"
                                   class="colorblur">
                        </td>
                        <td align="right" class="tdtitle" style="width:10%;">成本中心:</td>
                        <td class="tdtext">
                            <input name="costCenter" value="${param.costCenter}" type="text" id="costCenter">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn" onclick="searchDept()">
            <input type="button" value="确定" id="btnDept" class="btn">
            <div>
                <table class="adminlist" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                    <tr>
                        <td class='tdpadding' scope="col"></td>
                        <td class='tdpadding' scope="col">部门名称</td>
                        <td class='tdpadding' scope="col">部门编码</td>
                        <td class='tdpadding' scope="col">成本中心名称</td>
                        <td class='tdpadding' scope="col">成本中心编码</td>
                    </tr>
                    <tbody id="tbody_add_dept"></tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script> --%>
<!-- 改动 8 s -->
<script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>
<!-- 改动 8 e -->
<script type="text/javascript">
    var dg = frameElement.lhgDG;//定义dg 关闭需要

    var message = "${updateErrorMessage}";
    if (message != "") {
        alert(message);
        dg.cancel();
    }

    function searchDept() {
        var dept = $("#budgetDeptForm").serializeObject();
        dept.companyCode = $("#txtCompanyCode").val();
        $.ajax({
            type: "POST",
//			dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/apply/api/searchDepartment",
            data: JSON.stringify(dept),
            success: function (data) {
                var divHtml = "";
                for (var i = 0; i < data.length; i++) {
                    divHtml += "<tr>";
                    if (i == 0) {
                        divHtml += "<td><input type='radio' name='btnRadio' checked='checked'></td>";
                    } else {
                        divHtml += "<td><input type='radio' name='btnRadio'></td>";
                    }
                    divHtml += "<td id='departmentName'>" + data[i].departmentName + "</td>";
                    divHtml += "<td id='departmentCode'>" + data[i].departmentCode + "</td>";
                    divHtml += "<td id='tdcostCenter'>" + data[i].costCenter + "</td>";
                    divHtml += "<td id='tdcostCenterCode'>" + data[i].costCenterCode + "</td>";
                    divHtml += "</tr>";
                }
                $("#tbody_add_dept").html(divHtml);
            }
        });
    }
    //预算所属部门 开始
    $('#btntxtYsssbmShow').click(function () {
        var top = "45px";//$(this).offset().top - 350;
        var left = "30px";//$(this).offset().left-180;
        $('#ysssbmWindow').window('open').window('resize', {top: top, left: left});
        searchDept();
    });

    $("#btnDept").click(function () {
        clearRows();
        $('#ysssbmWindow').window('close');
        var departmentCode = $.trim($("input:radio[name='btnRadio']:checked").parent().parent().find("#departmentCode").html());
        var departmentName = $.trim($("input:radio[name='btnRadio']:checked").parent().parent().find("#departmentName").html());
        $("#deptCode").val(departmentCode);
        $("#deptName").val(departmentName);
        setTree(departmentCode, departmentName);
        getCostCenter();
    });

    function setTree(ckscode, cksname) {
        $("#txtYsssbm").val(ckscode);
        $("#txtBudgetDepartment").val(cksname);
        //$('#ysssbmWindow').window('close');
        //预算所属部门 结束
        //联动
        linkage();

    }
    $("#delRows").click(function () {//删除行
        $("#table_detail").find("input:checkbox[name=gvDataList_chkBolDel_0]:checked").each(function () {

            var delMergeNumber = $(this).parent().parent().find("#txtMergeNumber").val();
            $("#table_detail_detail").find("#txtDetailMergeNumber").each(function () {
                if (delMergeNumber == $(this).val()) {
                    $(this).parent().parent().remove();
                }
            });
            $(this).parent().parent().remove();
        });
        $("#gvDataList_CheckAll").attr("checked", false);
        sumAll();
    });

    //提交表单
    function saveOrder(strDraft, isMail, isPhoneMessage, idea, activityId) {
        //申请明细
        var obj = {
            id: "",
            applyOrderId: "",
            mergeNumber: "",
            applyUser: "",
            assetsName: "",
            demandCount: "",
            applyCount: "",
            notApplyCount: "",
            createBy: "",
            createDate: "",
            updateBy: "",
            updateDate: "",
            createByCode: "",
            updateByCode: "",
            specificationParameter: "",
            functions: "",
            acceptanceCriteria: "",
            optionalAccessories: "",
            units: "",
            inquiryUnitPrice: "",
            inquiryPrice: "",
            requirementsDate: "",
            purchaseUnitPrice: "",
            purchasePrice: "",
            procurementReturnPeriod: "",
            assetClassification: "",
            depreciation: "",
            codingMode: "",
            mainAssetCode: "",
            secondary: "",
            ledgerAccount: "",
            costCenter: "",
            costCenterCode: "",
            applyUserCode: "",
            currencyCode: "",
            exchangeRate: "",
            purchasePriceLocal: ""
        };
        //资产明细
        var objdetail = {
            demandDetailId: "",
            demandCount: "",
            mergeNumber: "",
            assetsName: ""
        };
        var applyOrderWrapper = {};
        //添加表头
        var objHeader = $("#applyOrderFormHeader").serializeObject();
        //获得公司名称
        objHeader.companyName = $("#txtCompanyCode").find("option:selected").text();

        objHeader.draft = strDraft;
        objHeader.idea = idea;
        objHeader.isMail = isMail;
        objHeader.isPhoneMessage = isPhoneMessage;
        objHeader.activityId = activityId;
        objHeader.orderMoney = $("#sumInquiryPrice").val();
        objHeader.building = $("input[name='building']:checked").val();

        //获得采购员名称
        //objHeader.buyer = $("#txtBuyer").find("option:selected").text();
        applyOrderWrapper.applyOrder = objHeader;


        ///获得表单明细信息 封装Json对象
        var objArr = constructObjectFromTable(obj, $("#tbody_detail .bodyTr"));
        applyOrderWrapper.applyOrderDetails = objArr;
        //console.log(objArr);
        ///获得资产明细信息 封装Json对象
        var objArrdetail = constructObjectFromTable(objdetail, $("#tbody_detail_detail .bodyTr"));
        applyOrderWrapper.applyDemandMappings = objArrdetail;
        //console.log(objArrdetail);
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/apply/api/saveUpdateApply",
            data: JSON.stringify(applyOrderWrapper),
            success: function (data) {
                if (data.errorMessages.length > 0) {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*修改成功.*$");
                    if (reg.test(str)) {

                        dg.curWin.location=dg.curWin.location;
                    }
                    return;
                }
                alert("修改成功！");
                //关闭窗口
                dg.curWin.refresh();
//                dg.cancel();
                return;
            }
        });
    }
    function checkAll() {
        if (!$("#txtOfficeLocation").val()) {
            alert("请选择办公地点");
            return false;
        }
        if (!$("#txtAssetType1").val()) {
            alert("请选择资产类型");
            return false;
        }
        if (!$("#txtCompanyCode").val()) {
            alert("请选择公司");
            return false;
        }
        if (!$("#txtCostCenterCode").val()) {
            alert("成本中心不能为空");
            return false;
        }
        if ($("input:radio[name='expensing']:checked").val() == null) {
            alert("请选择是否一次性费用化资产");
            return false;
        }
        if ($("input:radio[name='assemble']:checked").val() == null) {
            alert("请选择是否组装");
            return false;
        }
        if ($("#txtAssetType1").val() == "行政资产") {
            if ($("input:radio[name='building']:checked").val() == null) {
                alert("请选择是否房屋建筑物");
                return false;
            }
        }
        var f = false;
        //资产需求中的资产名称、规格参数、功能、选配件、验收标准、单位  要验证必填 不可为空
        $("input[id^='txtAssetsName']").each(function () {
            if ($(this).val() == "") {
                alert("资产名称不可为空！");
                f = true;
                return false;
            }
            if ($(this).val().length > 50) {
                alert("资产名称长度不能超过50！");
                f = true;
                return false;
            }
        });
        if (f) {
            return false;
        }
        $("input[id^='txtSpecificationParameter']").each(function () {
            if ($(this).val() == "") {
                alert("规格参数不可为空！");
                f = true;
                return false;
            }
            if ($(this).val().length > 250) {
                alert("规格参数长度不能超过250！");
                f = true;
                return false;
            }
        });
        if (f) {
            return false;
        }
        $("input[id^='txtFunctions']").each(function () {
            if ($(this).val() == "") {
                alert("功能不可为空！");
                f = true;
                return false;
            }
            if ($(this).val().length > 250) {
                alert("功能长度不能超过250！");
                f = true;
                return false;
            }
        });
        if (f) {
            return false;
        }
        $("input[id^='txtOptionalAccessories']").each(function () {
            /* if($(this).val()==""){
             alert("选配件不可为空！");
             f=true;
             return false;
             } */
            if ($(this).val().length > 250) {
                alert("选配件长度不能超过250！");
                f = true;
                return false;
            }
        });
        if (f) {
            return false;
        }
        $("#tbody_detail input[id='txtDemandCount']").each(function () {
            if (parseInt($(this).val()) > parseInt(999)) {
                alert("数量不能超过999！");
                f = true;
                return false;
            }
        });
        if (f) {
            return false;
        }
        $("input[id^='txtAcceptanceCriteria']").each(function () {
            if ($(this).val() == "") {
                alert("验收标准不可为空！");
                f = true;
                return false;
            }
            if ($(this).val().length > 250) {
                alert("验收标准长度不能超过250！");
                f = true;
                return false;
            }
        });
        if (f) {
            return false;
        }
        $("input[id^='txtUnits']").each(function () {
            if ($(this).val() == "") {
                alert("单位不可为空！");
                f = true;
                return false;
            }
        });
        if (f) {
            return false;
        }
        if (!$("#txtYsssbm").val()) {
            alert("请选择需求/申请部门");
            return false;
        }
        var sumInquiryPrice = $("#sumInquiryPrice").val();
        if (sumInquiryPrice == "" || isNaN(sumInquiryPrice)) {
            alert("金额不正确!");
            return false;
        }
        return true;
    }

    var changeAmount = "Y";
    function showAgreeDialog() {
        var document = $("#txtApplyOrderId").val();
        if (($("#oldOrderMoney").val() - 0) - ($("#sumInquiryPrice").val() - 0) == 0) {
            changeAmount = "N";
        }
        var url = "${dynamicURL}/workflow/showUpdateCommitDialog?parentWindowId=update&document=" + document + "&typeId=" + 77011 + "&changeAmount=" + changeAmount;

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
    function commitAgree(isMail, isPhoneMessage, idea, activityId) {
        $("#btnSave").val("提交...");
        $("#btnSave").attr("disabled", "disabled");//添加disabled属性
        $("#btnSaveDraft").attr("disabled", "disabled");
        $("#btnCancel").attr("disabled", "disabled");
        saveOrder("N", isMail, isPhoneMessage, idea, activityId);
        $("#btnSave").val("提交");
        $("#btnSave").removeAttr("disabled"); //移除disabled属性
        $("#btnSaveDraft").removeAttr("disabled");
        $("#btnCancel").removeAttr("disabled");
    }
    $("#btnSave").click(function () {//提交
        if (checkAll()) {
            showAgreeDialog();
        }
    });

    $("#btnSaveDraft").click(function () {//保存草稿
        if (checkAll()) {
            saveOrder("Y", "", "", "", "");
        }
    });

    //查询采购需求明细 用作合并
    function searchDemandOrderDetail() {
        var dat = $("#addPurchaseApplyOrderDetailForm").serializeObject();
        //预算所属部门编码
        dat.budgetDepartmentCode = $("#txtYsssbm").val();
        //资产类型
        dat.assetsType = $("#txtAssetType1").val();
        //公司编码
        dat.companyCode = $("#txtCompanyCode").val();
        //平台
        dat.platform = $("#txtPlatform").val();
        //区域
        if ($("#txtRegion").val() != null) {
            dat.region = $("#txtRegion").val();
        } else {
            dat.region = "";
        }
        var strids = "";
        $("input[id='txtDemandDetailId']").each(function () {
            strids += "'" + $(this).val() + "',"
        });
        if (strids.length > 0) {
            strids = strids.substr(0, strids.length - 1);
        }
        dat.ids = strids;
        isDraft = "${applyOrder.draft}";
        if (isDraft == 'Y') {
            dat.ids = "";
        }
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/demand/api/searchDemandDetail",
            data: JSON.stringify(dat),
            success: function (data) {
                var divHtml = "";
                var isDraft = "${applyOrder.draft}";
                for (var i = 0; i < data.length; i++) {
                    if (isDraft == "N") {
                        $("input[id='txtDemandDetailId']").each(function () {
                            if ($(this).val() == data[i].id) {
                                data[i].applyCount -= $(this).parent().parent().find("#txtThisApplyCount").val() - 0;
                                data[i].notApplyCount += $(this).parent().parent().find("#txtThisApplyCount").val() - 0;
                            }
                        });
                    }
                    var strDate = getLocalTime(data[i].requirementsDate);
                    divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                    divHtml += "<td class='tdpadding'><input type='text' name='mergeNumber' id='txtMergeNumber' size='1'></td>";
                    divHtml += "<td class='tdpadding'>" + data[i].projectCode + "</td>";
                    divHtml += "<td class='tdpadding'><input type='hidden' name='id' value='" + data[i].id + "'><input type='hidden' name='assetClassification' value='" + data[i].assetClassification + "'>" + data[i].demandOrderId + "</td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='assetsName' id='txtAssetsName' value='" + data[i].assetsName + "' readonly='readonly'></td>";
                    // 5
                    divHtml += "<td class='tdpadding'><input type='text' name='specificationParameter' id='txtSpecificationParameter' value='" + data[i].specificationParameter + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='functions' id='txtFunctions' value='" + data[i].functions + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='optionalAccessories' id='txtOptionalAccessories'  value='" + data[i].optionalAccessories + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='acceptanceCriteria' id='txtAcceptanceCriteria' value='" + data[i].acceptanceCriteria + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='assetType' id='txtAssetType' value='" + data[i].assetType + "' readonly='readonly' size='8' ></td>";
                    // 10
                    divHtml += "<td class='tdpadding'><input type='text' name='applyDepartment' id='txtApplyDepartment' value='" + data[i].applyDepartment + "' readonly='readonly' size='8' ></td>";
                    divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='demandCount' id='txtDemandCount' size='5' value='" + data[i].demandCount + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='allocationCount' id='txtAllocationCount' size='5' value='" + data[i].allocationCount + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='applyCount' id='txtApplyCount' size='5' value='" + data[i].applyCount + "' readonly='readonly' ></td>";
                    divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='thisApplyCount' value='" + data[i].notApplyCount + "' id='txtThisApplyCount' size='5'></td>";
                    // 15
                    divHtml += "<td class='tdpadding'><input type='text' name='units' value='" + data[i].units + "' id='txtUnits' size='2' readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='inquiryUnitPrice' value='" + data[i].inquiryUnitPrice + "' size='5' readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='requirementsDate' value='" + strDate + "' id='txtRequirementsDate' size='7' readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='costCenter' value='" + data[i].costCenter + "' id='txtCostCenter' readonly='readonly'><input type='hidden' name='costCenterCode' value='" + data[i].costCenterCode + "' id='txtCostCenterCode'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='useDescription' value='" + data[i].useDescription + "' id='txtUseDescription' readonly='readonly'></td>";
                    divHtml += "</tr>";
                }
                $("#tbody_add_detail").html(divHtml);
            }
        });
        //更新未申请数量(把当前订单的资产明细申请数量 与数据库数量合并)
        updateNotApplyCount();
    }
    function updateNotApplyCount() {
        var applyDemandMappingModels = "${applyDemandMappingModels}";
    }

    $("#btnDetailQuery").click(function () {//查询采购需求明细 用作合并
        searchDemandOrderDetail();
    });

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

    $("#btnDetailSave").click(function () {//确定新增明细 并 合并相同合并号
        $("input[id='txtThisApplyCount']").each(function (index, item) {
            if ($(this).val() == "" || isNaN($(this).val())) {
                $(this).val("0");
            }
        });
        var obj = {
            id: "",
            mergeNumber: "",
            thisApplyCount: "",
            demandOrderId: "",
            applyUser: "",
            assetsName: "",
            demandCount: "",
            applyCount: "",
            notApplyCount: "",
            allocationCount: "",
            createBy: "",
            createDate: "",
            updateBy: "",
            updateDate: "",
            createByCode: "",
            updateByCode: "",
            projectCode: "",
            specificationParameter: "",
            functions: "",
            optionalAccessories: "",
            acceptanceCriteria: "",
            standardComputer: "",
            referenceUnitPrice: "",
            referencePrice: "",
            inquiryUnitPrice: "",
            inquiryPrice: "",
            requirementsDate: "",
            costCenter: "",
            costCenterCode: "",
            useDescription: "",
            units: "",
            applyUserCode: "",
            applyDepartment: "",
            applyDepartmentCode: "",
            assetType: "",
            referencePriceLocal: "",
            currencyCode: "",
            exchangeRate: "",
            inquiryPriceLocal: "",
            reducedBudgetLocal: "",
            notReducedBudgetLocal: "",
            assetClassification: ""
        };
        var dat = constructObjectFromTable(obj, $("#tbody_add_detail .bodyTr"));
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/apply/api/mergeDetail",
            data: JSON.stringify(dat),
            success: function (data) {
                if (data.errorMessages.length > 0) {
                    alert(data.errorMessages);
                    return;
                }
                //采购申请订单明细
                var divHtml = "";
                for (var i = 0; i < data.result[0].length; i++) {
                    var strDate = getLocalTime(data.result[0][i].requirementsDate);
                    divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                    divHtml += "<td class='tdpadding'><input id='gvDataList_chkBolDel_0' type='checkbox' name='gvDataList_chkBolDel_0'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='mergeNumber' id='txtMergeNumber' size='2' value='" + data.result[0][i].mergeNumber + "' readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' size='10' name='assetsName' id='txtAssetsName' value='" + data.result[0][i].assetsName + "' readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' size='10' name='specificationParameter' id='txtSpecificationParameter' value='" + data.result[0][i].specificationParameter + "' readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' size='10' name='functions' id='txtFunctions' value='" + data.result[0][i].functions + "' readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' class='rightalign' name='assetClassification' value='" + data.result[0][i].assetClassification + "' id='txtAssetClassification' size='4' readonly='readonly'></td>";
                    // 5
                    divHtml += "<td class='tdpadding'><input type='text' size='10' name='optionalAccessories' id='txtOptionalAccessories' value='" + data.result[0][i].optionalAccessories + "'  readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' size='10' name='acceptanceCriteria' id='txtAcceptanceCriteria'  value='" + data.result[0][i].acceptanceCriteria + "'  readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' class='rightalign' name='demandCount' id='txtDemandCount' size='5' value='" + data.result[0][i].demandCount + "' readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='units' value='" + data.result[0][i].units + "' id='txtUnits' size='2' readonly='readonly'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' class='rightalign' name='inquiryUnitPrice' value='" + data.result[0][i].inquiryUnitPrice + "' id='txtInquiryUnitPrice' size='5'></td>";
                    // 10
                    divHtml += "<td class='tdpadding'><input type='text' class='rightalign' name='inquiryPrice' value='' id='txtInquiryPrice' size='5'></td>";
                    divHtml += "<td class='tdpadding'><input type='text' name='requirementsDate' value='" + strDate + "' id='txtRequirementsDate' size='5' readonly='readonly' class='Wdate' onclick=WdatePicker({skin:'ext'});></td>";

                    /*divHtml += "<td class='tdpadding'><input type='text' class='rightalign' name='purchaseUnitPrice' value='' id='txtPurchaseUnitPrice' size='5' readonly='readonly'></td>";
                     divHtml += "<td class='tdpadding'><input type='text' class='rightalign' name='purchasePrice' value='' id='txtPurchasePrice' size='5' readonly='readonly'></td>";
                     divHtml += "<td class='tdpadding'><input readonly='readonly' name='procurementReturnPeriod' value='' type='text' id='txtProcurementReturnPeriod' class='Wdate' onclick='WdatePicker({skin:'ext'});'></td>";
                     // 15
                     divHtml += "<td></td>";
                     divHtml += "<td></td>";
                     divHtml += "<td></td>";
                     divHtml += "<td></td>";
                     divHtml += "<td></td>";
                     // 20
                     divHtml += "<td></td>"; */
                    divHtml += "<td class='tdpadding'><input type='text' name='costCenter' value='" + data.result[0][i].costCenter + "' id='txtCostCenter' readonly='readonly'><input type='hidden' name='costCenterCode' value='" + data.result[0][i].costCenterCode + "' id='txtCostCenter'></td>";
                    divHtml += "</tr>";
                }
                $("#tbody_detail").html(divHtml);

                //采购申请订单资产明细
                var divDetailHtml = "";
                for (var i = 0; i < data.result[1].length; i++) {
                    var strDate = getLocalTime(data.result[1][i].requirementsDate);
                    divDetailHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                    divDetailHtml += "<td class='tdpadding'><input type='text' name='mergeNumber' value='" + data.result[1][i].mergeNumber + "' id='txtDetailMergeNumber' size='1' readonly='readonly'></td>";
                    /* divDetailHtml += "<td class='tdpadding'>"+data.result[1][i].projectCode+"</td>";
                     divDetailHtml += "<td class='tdpadding'><input type='hidden' name='id' value='"+data.result[1][i].id+"'>"+data.result[1][i].demandOrderId+"</td>"; */
                    divDetailHtml += "<td class='tdpadding'><input type='hidden' name='demandDetailId' id='txtDemandDetailId' value='" + data.result[1][i].id + "'><input type='text' name='assetsName' id='txtAssetsName' value='" + data.result[1][i].assetsName + "' readonly='readonly'></td>";
                    // 5
                    divDetailHtml += "<td class='tdpadding'><input type='text' name='specificationParameter' id='txtSpecificationParameter' value='" + data.result[1][i].specificationParameter + "' readonly='readonly' ></td>";
                    divDetailHtml += "<td class='tdpadding'><input type='text' name='functions' id='txtFunctions' value='" + data.result[1][i].functions + "' readonly='readonly' ></td>";
                    divDetailHtml += "<td class='tdpadding'><input type='text' name='optionalAccessories' id='txtOptionalAccessories' value='" + data.result[1][i].optionalAccessories + "' readonly='readonly' ></td>";
                    divDetailHtml += "<td class='tdpadding'><input type='text' name='acceptanceCriteria' id='txtAcceptanceCriteria' value='" + data.result[1][i].acceptanceCriteria + "' readonly='readonly' ></td>";
                    /* divDetailHtml += "<td class='tdpadding'>"+data.result[1][i].assetType+"</td>";
                     // 10
                     divDetailHtml += "<td class='tdpadding'>"+data.result[1][i].applyDepartment+"</td>"; */
                    /* divDetailHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='demandCount' id='txtDemandCount' size='5' value='"+data.result[1][i].demandCount+"' readonly='readonly' ></td>";
                     divDetailHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='allocationCount' id='txtAllocationCount' size='5' value='"+data.result[1][i].allocationCount+"' readonly='readonly' ></td>";
                     divDetailHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='applyCount' id='txtApplyCount' size='5' value='"+data.result[1][i].applyCount+"' readonly='readonly' ></td>";
                     */
                    divDetailHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='demandCount' value='" + data.result[1][i].thisApplyCount + "' id='txtThisApplyCount' size='5' readonly='readonly'></td>";
                    // 15
                    divDetailHtml += "<td class='tdpadding'><input type='text' name='units' value='" + data.result[1][i].units + "' id='txtUnits' size='2' readonly='readonly'></td>";
                    divDetailHtml += "<td class='tdpadding'><input type='text' name='inquiryUnitPrice' value='" + data.result[1][i].inquiryUnitPrice + "' size='5' readonly='readonly'></td>";
                    divDetailHtml += "<td class='tdpadding'><input type='text' name='requirementsDate' value='" + strDate + "' id='txtRequirementsDate' size='6' readonly='readonly'></td>";
                    divDetailHtml += "<td class='tdpadding'><input type='text' name='costCenter' value='" + data.result[1][i].costCenter + "' id='txtCostCenter'  readonly='readonly'><input type='hidden' name='costCenterCode' value='" + data.result[1][i].costCenterCode + "' id='txtCostCenterCode'></td>";
                    divDetailHtml += "<td class='tdpadding'><input type='text' name='useDescription' value='" + data.result[1][i].useDescription + "' id='txtUseDescription'  readonly='readonly'></td>";
                    divDetailHtml += "</tr>";
                }
                changeAmount = "N";
                $("#tbody_detail_detail").html(divDetailHtml);
                $('#addApplyWindow').window('close');
                sumAll();
            }
        });
    });
    function getLocalTime(nS) {
        var date = new Date(nS);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        return year + "-" + month + "-" + day;
    }
    //清除根据成本中心带出的信息
    function cleanDep() {
        $("#txtYsssbm").val("");
        $("#txtBudgetDepartment").val("");
        $("#txtCompany").val("");
        $("#txtCompanyCode").val("");
        //$("#txtAssetType1").html("");
        $("#txtPlatform").val("");
        $("#txtRegion").val("");
        $("#txtCostCenter").val("");
        $("#txtCostCenterCode").val("");
        $("#txtWorkShopType").val("");

        //清空后设置是否组装等 禁用
        changeAssetType();
    }
    //联动
    function linkage() {
        var ckscode = $("#txtYsssbm").val();
        var cksname = $("#txtBudgetDepartment").val();
        $.ajax({
            type: "POST",
            async: false,
            url: "${dynamicURL}/userInfo/api/cksDept",
            data: "ckscode=" + ckscode,
            success: function (treeObj) {
                if (!treeObj) {
                    alert("没有配置该成本中心");
                    cleanDep();//清除根据成本中心带出的信息
                    return false;
                }
                $("#txtCostCenter").val("(" + treeObj.ccostcentercode + ")" + treeObj.ccostcentername);
                $("#txtCostCenterCode").val(treeObj.ccostcentercode);
                $("#txtCompany").val(treeObj.ccompanyname);
                $("#txtCompanyCode").val(treeObj.ccompanycode);
                //根据成本中心和资产类型带出所有匹配数据
                setValues();
            }
        });
        /*
         if($('#txtCompanyCode').val()==""){
         return false;
         }
         var companyCode =  $('#txtCompanyCode').val();
         var costCenterCode =  $('#txtCostCenterCode').val();

         // 	根据公司带 资产类型
         $("#txtAssetType1 option").remove();
         $.ajax({
         type : "POST",
         async : false,
         url : "
        ${dynamicURL}/userInfo/api/comAsset",
         data : "companyCode=" + companyCode,
         success: function(assetlist){
         //$("#txtAssetType1").append("<option  value='' selected='selected'>--请选择--</option>");
         for ( var i = 0; i < assetlist.length; i++) {
         $("#txtAssetType1").append("<option value='" + assetlist[i].assetsType + "'>"+ assetlist[i].assetsType + "</option>");
         };
         }
         });
         //根据资产类型 显示
         changeAssetType();

         //	根据公司查询 平台
         $("#txtPlatform").val("");
         $.ajax({
         type : "POST",
         async:false,
         url : "
        ${dynamicURL}/userInfo/api/comPlatform",
         data : "costCenterCode=" + costCenterCode,
         success : function(platformlist) {
         $("#txtPlatform").val(platformlist.platform);
         //	 根据平台 查询区域
         $("#txtRegion option").remove();
         var cplatformdata	= $("#txtPlatform").val();  //弹出select的值
         $.ajax({
         type : "POST",
         async:false,
         url : "
        ${dynamicURL}/userInfo/api/platformRegion",
         data : "cplatformdata=" + cplatformdata,
         success : function(arealist) {
         //$("#txtRegion").append("<option  value='' selected='selected'>--请选择--</option>");
         if(arealist.length > 0){
         $("#txtRegion").css("background-color","");
         }else{
         $("#txtRegion").css("background-color","#CCCCCC");
         }
         for ( var i = 0; i < arealist.length; i++) {
         $("#txtRegion").append("<option value='" + arealist[i].careaName + "'>"+ arealist[i].careaName + "</option>");
         };

         }
         });
         }
         });*/
    }
    //根据成本中心和资产类型带出数据
    function setValues() {
        var costCenterCode = $("#txtCostCenterCode").val();
        var assetType = $("#txtAssetType1").val();
        if (!costCenterCode) {
            //资产类型是空
            return false;
        }
        if (!assetType) {
            //资产类型是空
            return false;
        }
        if (costCenterCode != '' && assetType != '') {
            $.ajax({
                type: "POST",
                cache: false,
                url: "${dynamicURL}/attchConfig/api/searchByCostcenterCodeAndAssetType",
                data: {"costcenterCode": costCenterCode, "assetType": assetType},
                // dataType: "json",
                async: false,
                success: function (data) {
                    if (!data) {
                        alert("成本中心没有配置该资产类型!");
                    }
                    $("#txtPlatform").val(data.platform);
                    $("#txtWorkShopType").val(data.workShopType);
                    $("#attachDepartManagerName").val(data.attachDepartManagerName);
                    $("#attachDepartManagerCode").val(data.attachDepartManagerCode);
                    $("#attachDepartAdminName").val(data.attachDepartAdminName);
                    $("#attachDepartAdminCode").val(data.attachDepartAdminCode);
                }
            });
            // 根据平台 查询区域
            $("#txtRegion option").remove();
            var cplatformdata = $("#txtPlatform").val();  //弹出select的值
            $.ajax({
                type: "POST",
                async: false,
                url: "${dynamicURL}/userInfo/api/platformRegion",
                data: "cplatformdata=" + cplatformdata,
                success: function (arealist) {
                    //$("#txtRegion").append("<option  value='' selected='selected'>--请选择--</option>");
                    $("#txtRegion").css("background-color", "");
                    for (var i = 0; i < arealist.length; i++) {
                        $("#txtRegion").append("<option value='" + arealist[i].careaName + "'>" + arealist[i].careaName + "</option>");
                    }
                    ;

                }
            });
        }
    }
    $("#addRows").click(function () {
        if (!$("#txtCompanyCode").val()) {
            alert("请先选择所属公司!");
            return false;
        }
        if (!$("#txtYsssbm").val()) {
            alert("请先选择预算所属部门!");
            return false;
        }
        var top = "90px";
        var left = "15px";
        $('#addApplyWindow').window('open').window('resize', {width: '800px', height: '440px', top: top, left: left});
        if ($.trim($("#tbody_add_detail").html()) == "") {
            searchDemandOrderDetail();
        }
    });
    function sumAll() {
        //数量*单价
        $("input[id^='txtDemandCount']").each(function () {
            var c = $(this).val();//本次申请数量
            var p = $(this).parent().parent().find("#txtInquiryUnitPrice").val();//询价单价
            var s = $(this).parent().parent().find("#txtInquiryPrice").val();//询价总价
            if (s != "") {//总价不是""  用总价 除以 数量  获得 单价
                if (c != "") {
                    $(this).parent().parent().find("#txtInquiryUnitPrice").val((s / c - 0).toFixed(2));//单价
                }
            }
            else {//如果总价不是数字(输入单价时会清空总价)  用数量*单价 获得 总价
                if (c != "" && p != "") {
                    $(this).parent().parent().find("#txtInquiryPrice").val((c * p - 0).toFixed(2));//总价
                }
            }
        });
        //合计参考总价
        var sumInquiryPrice = 0;
        $("input[id^='txtInquiryPrice']").each(function () {
            sumInquiryPrice += $(this).val() - 0;
        });
        $("#sumInquiryPrice").val(sumInquiryPrice.toFixed(2));
    }
    //参考单价
    $("#table_detail").delegate("input[id^='txtInquiryUnitPrice']", "keyup", function () {
        //清空总价
        $(this).parent().parent().find("input[id^='txtInquiryPrice']").val("");
        sumAll();
    });
    //参考总价
    $("#table_detail").delegate("input[id^='txtInquiryPrice']", "keyup", function () {
        sumAll();
    });
    /*tdBuilding 房屋建筑物  tdAssemble组装   tdWorkShopType 车间类型 */
    //选中资产类型时
    $("#txtAssetType1").change(function () {
        assetTypeIsIT();
        clearRows();
        setValues();
        changeAssetType();
    });
    $("#company").change(function () {
        var company = $("#company").children("option:selected").val();
        $("#txtCompany").val(company.split(",")[1]);
        $("#txtCompanyCode").val(company.split(",")[0]);
    });
    function changeAssetType() {
        var assetsType = $("#txtAssetType1").children("option:selected").val();
        if (assetsType == "行政资产") {
            //移除disabled属性
            $("input[id='txtBuilding']").each(function () {
                $(this).removeAttr("disabled");
            });
        }
        else {
            $("input[id='txtBuilding']").each(function () {
                $("input:radio[value='Y']").removeAttr("checked");
                $("input:radio[value='N']").removeAttr("checked");
                $(this).attr("disabled", "disabled");
            });
        }
    }
    function getCostCenter() {
        var ckscode = $("#txtYsssbm").val();
        var ccompanycode = $("#txtCompanyCode").val();
        if (ckscode == "" || ccompanycode == "") {
            return false;
        }
        $("#txtCostCenter").val("");
        $("#txtCostCenterCode").val("");
        $.ajax({
            type: "POST",
            async: false,
            url: "${dynamicURL}/userInfo/api/getCostCenter",
            data: {"ckscode": ckscode, "ccompanycode": ccompanycode},
            success: function (treeObj) {
                if (!treeObj) {
                    alert("没有配置该成本中心");
                    //cleanDep();//清除根据成本中心带出的信息
                    return false;
                } else {
                    $("#txtCostCenter").val("(" + treeObj.ccostcentercode + ")" + treeObj.ccostcentername);
                    $("#txtCostCenterCode").val(treeObj.ccostcentercode);
                    //根据成本中心和资产类型带出所有匹配数据
                    setValues();
                }
            }
        });
    }
    $('#txtCompanyCode').change(function () {
        getCostCenter();
        assetTypeIsIT();
    });
    function assetTypeIsIT() {
        var assetsType = $("#txtAssetType1").children("option:selected").val();
        var txtCompanyCode = $("#txtCompanyCode").val();
        if (assetsType == "IT资产" && (txtCompanyCode == "6000" || txtCompanyCode == "8000" || txtCompanyCode == "8100")) {
            setTree($("#txtApplyDepartmentCode").val(), $("#txtApplyDepartment").val());
            $("#btntxtYsssbmShow").attr('disabled', "true");
            //$("#companyForOther").hide();
            //$("#companyForIt").show();
        }
        else {
            $("#btntxtYsssbmShow").removeAttr('disabled');
            //$("#companyForIt").hide();
            //$("#companyForOther").show();
        }
    }

    //清空行明细
    function clearRows() {
        $("#tbody_detail").html("");
        $("#tbody_add_detail").html("");
        $("#tbody_detail_detail").html("");
    }

    $("#btnRefClear").click(function () {
        clearRows();
    });
    $("#txtCompanyCode").change(function () {
        clearRows();
    });

    $('#btnUpload').click(function () {
        var fileElementId = $(this).prev().attr('id');
        var file = $('#fileToUpload').val();
        if (file == "") {
            alert("请选择需要上传的文件！");
            return false;
        }

        var documentNo = $('#txtApplyOrderId').val();
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
        //联动获得基础下拉列表信息
        linkage();
        //办公地点赋值
        var officeLocation = "${applyOrder.officeLocation}";
        $("#txtOfficeLocation").val(officeLocation);
        //平台赋值
        var platform = "${applyOrder.platform}";
        $("#txtPlatform").val(platform);
        //区域赋值

        var region = "${applyOrder.region}";
        $("#txtRegion").val(region);

        //采购员赋值
        /*selectAddress();
         var buyerCode = "
        ${applyOrder.buyerCode}";
         $("#txtBuyer").val(buyerCode);*/
        //资产类型赋值
        <%--var assetType = "${applyOrder.assetType}";--%>
        <%--$("#txtAssetType1").val(assetType);--%>

        //车间类型赋值
        /*$("#txtWorkShopType option").remove();
         var companyCode =  $('#txtCompanyCode').val();
         var departType = assetType;

         var depcoms = [companyCode, departType];
         $.ajax({
         type : "POST",
         async : false,
         url : "
        ${dynamicURL}/userInfo/api/assetWork",
         data : "depcoms=" + depcoms,
         success : function(worklist) {
         //$("#txtWorkShopType").append("<option  value='' selected='selected'>--请选择--</option>");
         for ( var i = 0; i < worklist.length; i++) {
         $("#txtWorkShopType").append("<option value='" + worklist[i].departType + "'>"+ worklist[i].departType + "</option>");
         };

         }

         });
         var workShopType = "
        ${applyOrder.workShopType}";
         $("#txtWorkShopType").val(workShopType);
         */

        //根据资产类型显示车间类型、房屋、组装
        assetTypeIsIT();
        //合计
        sumAll();
        //changeAssetType();
        var assetsType = $("#txtAssetType1").children("option:selected").val();
        if (assetsType == "行政资产") {
            //移除disabled属性
            $("input[id='txtBuilding']").each(function () {
                $(this).removeAttr("disabled");
            });
        }
        else {
            $("input[id='txtBuilding']").each(function () {
                //$("input:radio[value='Y']").removeAttr("checked");
                //$("input:radio[value='N']").removeAttr("checked");
                $(this).attr("disabled", "disabled");
            });
        }
        $.ajax({
            url: '${dynamicURL}/file/getFilesByDocument',
            data: {'document': $("#txtApplyOrderId").val()},
            dataType: 'json',
            type: 'POST',
            success: function (data) {
                if (data.length > 0) {
                    $('#uploadTable .noItems').remove();
                    $.each(data, function (i, item) {
                        $('#uploadTable > tbody').append('<tr id="tr" class="' + item.fileuuid + '" style="white-space:nowrap;">'
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
    });
    $(document).ready(function () {
        $(window).resize(function () {
            $('#purchaseTab').tabs('resize');
        });
    });
</script>
</body>


</html>