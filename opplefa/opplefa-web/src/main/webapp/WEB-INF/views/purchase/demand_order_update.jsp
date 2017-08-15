<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        资产需求单
    </div>
</div>
<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
    <input type="button" name="btnSave" value="提交" onclick="" id="btnSave" class="btn">
    <c:if test="${demandOrder.draft=='Y'}">
        <input type="button" name="btnSaveDraft" value="保存草稿" onclick="" id="btnSaveDraft" class="btn">
    </c:if>
    <input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
    <!-- onclick="dg.cancel()" -->
</div>

    <div id="purchaseTab" class="easyui-tabs" >
        <div title="基本信息" id="tabsa" style="overflow:auto; padding:20px;min-width: 800px;">
            <form id="demandOrderFormHeader">
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
                                    <input name='' type='hidden' value='${demandOrder.applyBudgetMoney}'
                                           id='oldApplyBudgetMoney'>
                                    <input name='attachDepartManagerName' type='hidden'
                                           value='${demandOrder.attachDepartManagerName}' id='attachDepartManagerName'>
                                    <input name='attachDepartManagerCode' type='hidden'
                                           value='${demandOrder.attachDepartManagerCode}' id='attachDepartManagerCode'>
                                    <input name='attachDepartAdminName' type='hidden'
                                           value='${demandOrder.attachDepartAdminName}' id='attachDepartAdminName'>
                                    <input name='attachDepartAdminCode' type='hidden'
                                           value='${demandOrder.attachDepartAdminCode}' id='attachDepartAdminCode'>
                                    <td style="text-align: right">单据号:</td>
                                    <td>
                                        <input class="searchinput" readonly name="cdocument" type="text"
                                               value="${demandOrder.cdocument}" id="txtDemandOrderId">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>所属公司:</td>
                                    <td>
							<span id="spanCompany">
								<select id="txtCompanyCode" class="searchselect" name='companyCode'></select>
                                <!-- <input readonly='readonly' class='searchinput' name='companyName' style="background-color: #CCCCCC;" type='text' value='' id='txtCompany' onclick='ysssbmCom()'>
                                <input name='companyCode' type='hidden' value='' id='txtCompanyCode'> -->
							</span>
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>资产类型:</td>
                                    <td>
                                        <select class="searchselect" name="assetType" id="txtAssetType1">
                                            <option value="">请选择</option>
                                            <option value="行政资产" ${demandOrder.assetType=="行政资产"?" selected='selected' ":""}>
                                                行政资产
                                            </option>
                                            <option value="IT资产" ${demandOrder.assetType=="IT资产"?" selected='selected' ":""}>
                                                IT资产
                                            </option>
                                            <option value="计量器具" ${demandOrder.assetType=="计量器具"?" selected='selected' ":""}>
                                                计量器具
                                            </option>
                                            <option value="机器设备" ${demandOrder.assetType=="机器设备"?" selected='selected' ":""}>
                                                机器设备
                                            </option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">申请人:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" value='${demandOrder.applyUser}'
                                               name="applyUser" type="text" value="" id="applyUser">
                                        <input class="searchinput" readonly="readonly"
                                               value='${demandOrder.applyUserCode}' name="applyUserCode" type="hidden"
                                               value="" id="applyUserCode">
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
                                    <td style="text-align: right">是否组装:</td>
                                    <td>
                                        <input type="radio" id="txtAssemble" name="assemble"
                                               value="Y" ${demandOrder.assemble=="Y"?" checked='checked' ":""}>是
                                        <input type="radio" id="txtAssemble" name="assemble"
                                               value="N" ${demandOrder.assemble=="N"?" checked='checked' ":""}>否
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right;">申请日期:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" name="applyDate" type="text"
                                               value="<fmt:formatDate value='${demandOrder.applyDate}'  pattern='yyyy-MM-dd'/>"
                                               id="txtApplyDate">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>平台:</td>
                                    <td class="tdtext">
                                        <input readonly='readonly' class='searchinput' name='platform'
                                               style="background-color: #CCCCCC;" type='text' value='' id='txtPlatform'>
                                    </td>
                                    <td style="text-align: right">是否房屋建筑物:</td>
                                    <td>
                                        <input type="radio" id="txtBuilding" name="building"
                                               value="Y" ${demandOrder.building=="Y"?" checked='checked' ":""}>是
                                        <input type="radio" id="txtBuilding" name="building"
                                               value="N" ${demandOrder.building=="N"?" checked='checked' ":""}>否
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><font color="red">*</font>申请人部门:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly name="applyDepartment" type="text"
                                               value="${demandOrder.applyDepartment}" id="txtApplyDepartment">
                                        <input class="searchinput" readonly name="applyDepartmentCode" type="hidden"
                                               value="${demandOrder.applyDepartmentCode}" id="txtApplyDepartmentCode">
                                    </td>
                                    <td style="text-align: right">区域:</td>
                                    <td class="tdtext">
                                        <select class="searchselect" style="background-color: #CCCCCC;" name="region"
                                                id="txtRegion" onclick="selectPlat()">
                                        </select>
                                    </td>
                                    <td style="text-align: right;">车间类型:</td>
                                    <td class="tdtext">
                                        <input type="text" class="searchselect" value="${demandOrder.workShopType}"
                                               style="background-color: #CCCCCC;" name="workShopType"
                                               id="txtWorkShopType" readonly="readonly"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><font color="red">*</font>预算所属部门:</td>
                                    <td>
                                        <span id="spanBudgetDepartment"><input style="width: 100px;" readonly="readonly"
                                                                               name="budgetDepartmentName"
                                                                               value="${demandOrder.budgetDepartmentName}"
                                                                               type="text" id="txtBudgetDepartment"
                                                                               class="inputbtnRefViewGray"></span><input
                                            id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button'
                                            value='X' onclick="cleanDep();"><input id="btntxtYsssbmShow"
                                                                                   class='btnRefView'
                                                                                   title='点击打开参照文本框内容' type='button'
                                                                                   value='…'>
                                        <input name="budgetDepartmentCode" value="${demandOrder.budgetDepartmentCode}"
                                               type="hidden" id="txtYsssbm" class="inputbtnRefViewGray">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>成本中心:</td>
                                    <td class="tdtext">
							<span id="spanCostCenter">
								<input readonly="readonly" class="searchinput" style="background-color: #CCCCCC;"
                                       name="costCenter" type="text" value="${demandOrder.costCenter}"
                                       id="txtCostCenter">
								<input readonly="readonly" class="searchinput" style="background-color: #CCCCCC;"
                                       name="costCenterCode" type="hidden" value="${demandOrder.costCenterCode}"
                                       id="txtCostCenterCode">
							</span>
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
            <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
                <tbody>
                <tr>
                    <td class="titlefontsize" align="left">
                        <div class="imgtitle"></div>
                        新增申购资产需求
                    </td>
                </tr>
                <tr>
                    <td class="titlefontsize" align="left">
                        <input id="addRows" type="button" value="增行">
                        <input id="delRows" type="button" value="删行">
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
                                            <table style="width:800px;" class="adminlist" cellspacing="0" rules="all"
                                                   border="1" id="gridDataList" style="border-collapse:collapse;">
                                                <tr style="white-space:nowrap;">
                                                    <th scope="col">
                                                        <input id="gvDataList_CheckAll" type="checkbox"
                                                               name="gvDataList$ctl01$CheckAll"
                                                               onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_detail');"/>
                                                    </th>
                                                    <th scope="col" class="tdpadding">序号</th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>项目编码
                                                    </th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>预算资产名称
                                                    </th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>是否电脑
                                                    </th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>资产名称
                                                    </th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>规格参数
                                                    </th>
                                                    <!-- 5 -->
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>功能</th>
                                                    <th scope="col" class="tdpadding">选配件</th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>验收标准
                                                    </th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>可用预算金额
                                                    </th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>单位</th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>预算年度
                                                    </th>
                                                    <!-- 10 -->
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>需求数量
                                                    </th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>调拨数量
                                                    </th>
                                                    <!-- 15 -->
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>参考单价
                                                    </th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>参考总价
                                                    </th>
                                                    <th scope="col" class="tdpadding"><font color="red">*</font>要求到位日期
                                                    </th>
                                                    <!-- 20 -->
                                                    <th scope="col" class="tdpadding">用途说明</th>
                                                </tr>
                                                <tbody id="table_detail">
                                                <c:forEach items="${demandOrderDetailes}" var="demandOrderDetaile"
                                                           varStatus="i">
                                                    <tr class="bodyTr" style="white-space:nowrap;">
                                                        <td><input id="gvDataList_chkBolDel_0" type="checkbox"
                                                                   name="gvDataList_chkBolDel_0"></td>
                                                        <td class="tdpadding" id='statusIndex'>${i.index+1}</td>
                                                        <td class="tdpadding">
                                                            <input value="${demandOrderDetaile.projectCode}"
                                                                   readonly="readonly" style='width:130px;'
                                                                   name="projectCode" type="text"
                                                                   id="txtApplydep${i.index+1}"
                                                                   class="inputbtnRefViewGray"><input
                                                                id="projectCodeClear" class="btnRefView" onclick=""
                                                                title="点击清空参照文本框内容" type="button" value="X"><input
                                                                id="btnRefShow${i.index+1}" class="btnRefView"
                                                                title="点击打开参照文本框内容" type="button" value="…">
                                                            <input name="hdapplydepcode" type="hidden"
                                                                   id="hdapplydepcode">
                                                        </td>
                                                        <td class='budgetAssetsNameTd'
                                                            style="padding-left: 10px; padding-right: 10px;"><input
                                                                type="text" size='10' readonly
                                                                value="${demandOrderDetaile.budgetAssetsName}"
                                                                name="budgetAssetsName"
                                                                id="txtBudgetAssetsName${i.index+1}"></td>
                                                        <td class="tdpadding">
                                                            <select index="${i.index+1}"  ${demandOrder.assetType!="IT资产" ? " disabled='disabled' " : ""}
                                                                    name="standardComputer" id="txtStandardComputer"
                                                                    style="width: 70px;">
                                                                <option value="" ${demandOrderDetaile.standardComputer=="" ? " selected='selected' " : ""} >
                                                                    请选择
                                                                </option>
                                                                <option value="Y" ${demandOrderDetaile.standardComputer=="Y" ? " selected='selected' " : ""}  >
                                                                    是
                                                                </option>
                                                                <option value="N" ${demandOrderDetaile.standardComputer=="N" ? " selected='selected' " : ""}  >
                                                                    否
                                                                </option>
                                                            </select>
                                                        </td>
                                                        <td class='assetsNameTd'
                                                            style="padding-left: 10px; padding-right: 10px;"><input
                                                                type="text" size='10'
                                                                value="${demandOrderDetaile.assetsName}"
                                                                name="assetsName" id="txtAssetsName${i.index+1}"></td>
                                                        <td class='specificationParameterTd'
                                                            style="padding-left: 10px; padding-right: 10px;"><input
                                                                size='10'
                                                                value="${demandOrderDetaile.specificationParameter}"
                                                                type="text" name="specificationParameter"
                                                                id="txtSpecificationParameter"></td>
                                                        <!-- 5 -->
                                                        <td class="tdpadding"><input
                                                                value="${demandOrderDetaile.functions}" type="text"
                                                                name="functions" id="txtFunctions" size='10'></td>
                                                        <td class="tdpadding"><input type="text"
                                                                                     value="${demandOrderDetaile.optionalAccessories}"
                                                                                     name="optionalAccessories"
                                                                                     size='10'
                                                                                     id="txtOptionalAccessories"></td>
                                                        <td class="tdpadding"><input type="text"
                                                                                     value="${demandOrderDetaile.acceptanceCriteria}"
                                                                                     name="acceptanceCriteria" size='10'
                                                                                     id="txtAcceptanceCriteria"></td>
                                                        <td class="tdpadding"><input type="text"
                                                                                     value="${demandOrderDetaile.avaliableSumMoney}"
                                                                                     name="avaliableSumMoneyOld"
                                                                                     class="rightalign"
                                                                                     id="txtAvaliableSumMoney${i.index+1}"
                                                                                     size='7' readonly="readonly"></td>
                                                            <%-- <td class="tdpadding"><input type="text" value="${demandOrder.draft=='N'?demandOrderDetaile.avaliableSumMoney+demandOrderDetaile.referencePrice:demandOrderDetaile.avaliableSumMoney}" name="avaliableSumMoney" class="rightalign" id="txtAvaliableSumMoney${i.index+1}" size='5' readonly="readonly"></td> --%>
                                                        <td class="tdpadding" id="tdUnits">
                                                            <select id='units' name='units'>
                                                                <option value=''>请选择</option>
                                                                <c:forEach items="${assetUnitList}" var="assetUnit"
                                                                           varStatus="status">
                                                                    <option value='${assetUnit.unitName}' ${assetUnit.unitName==demandOrderDetaile.units?"selected='selected'":""}>${assetUnit.unitName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        <td class="tdpadding"><input type="text"
                                                                                     value="${demandOrderDetaile.budgetYear}"
                                                                                     name="budgetYear"
                                                                                     id="txtBudgetYear1" size='2'
                                                                                     readonly></td>
                                                        <!-- 10 -->
                                                        <td class="tdpadding"><input type="text"
                                                                                     value="${demandOrderDetaile.demandCount}"
                                                                                     name="demandCount"
                                                                                     class="rightalign"
                                                                                     id="txtDemandCount${i.index+1}"
                                                                                     size="5"></td>
                                                        <td class="tdpadding"><input type="text" readonly="readonly"
                                                                                     value="${demandOrderDetaile.allocationCount}"
                                                                                     name="allocationCount"
                                                                                     class="rightalign"
                                                                                     id="txtAllocationCount${i.index+1}"
                                                                                     size="5"></td>
                                                        <!-- 15 -->
                                                        <td class="tdpadding"><input type="text"
                                                                                     value="${demandOrderDetaile.referenceUnitPrice}"
                                                                                     name="referenceUnitPrice"
                                                                                     class="rightalign"
                                                                                     id="txtReferenceUnitPrice${i.index+1}"
                                                                                     size="5"></td>
                                                        <td class="tdpadding"><input type="text"
                                                                                     value="${demandOrderDetaile.referencePrice}"
                                                                                     name="referencePrice"
                                                                                     id="txtReferencePrice${i.index+1}"
                                                                                     class="rightalign" size="5"></td>
                                                        <td class="tdpadding"><input readonly='readonly'
                                                                                     value="<fmt:formatDate value='${demandOrderDetaile.requirementsDate}'  pattern='yyyy-MM-dd'/>"
                                                                                     name="requirementsDate" value=""
                                                                                     type="text"
                                                                                     id="txtRequirementsDate"
                                                                                     class="Wdate"
                                                                                     onclick="WdatePicker({skin:'ext'});">
                                                        </td>
                                                        <!-- 20 -->
                                                        <td class="tdpadding"><input type="text"
                                                                                     value="${demandOrderDetaile.useDescription}"
                                                                                     name="useDescription"
                                                                                     id="txtUseDescription"></td>
                                                    </tr>

                                                </c:forEach>
                                                </tbody>
                                                <tr style="white-space:nowrap;">
                                                    <td>
                                                    </td>
                                                    <td class='tdpadding'>合计</td>
                                                    <td>
                                                    </td>
                                                    <td>
                                                    </td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td class='tdpadding'><input type="text" class='rightalign'
                                                                                 readonly="readonly" name=""
                                                                                 id="sumDemandCount" size="5"></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td class='tdpadding'><input type="text" readonly="readonly"
                                                                                 class="rightalign"
                                                                                 name="sumTxtReferencePrice"
                                                                                 id="sumTxtReferencePrice" size="5">
                                                    </td>
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
        </div>

        <div title="立项书" data-options="closable:false" style="overflow:auto;padding:20px;">
            <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
                <tbody>
                <form id="demandOrderFormFooter">
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle"></div>
                            立项书 &nbsp;&nbsp;
                            <font color="red">*</font>
                            <font color="black">是否立项：
                                <select id="txtProjectProposal" name="projectProposal">
                                    <option value="">请选择</option>
                                    <option value="Y" ${demandOrder.projectProposal=="Y"?"selected='selected'":""}>是
                                    </option>
                                    <option value="N" ${demandOrder.projectProposal=="N"?"selected='selected'":""}>否
                                    </option>
                                    <%-- <c:choose>
                                    <c:when test="${demandOrder.projectProposal=='Y'}">
                                        <option value="">请选择</option>
                                        <option value="Y" selected="selected">是</option>
                                        <option value="N">否</option>
                                    </c:when>
                                    <c:when test="${demandOrder.projectProposal=='N'}">
                                        <option value="">请选择</option>
                                        <option value="Y">是</option>
                                        <option value="N" selected="selected">否</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="">请选择</option>
                                        <option value="Y">是</option>
                                        <option value="N">否</option>
                                    </c:otherwise>
                                    </c:choose> --%>

                                </select>
                                <%--<input type="radio" id="txtProjectProposal" name="projectProposal" value="Y" ${demandOrder.projectProposal=="Y"?"checked='checked'" : ""}>是--%>
                                <%--<input type="radio" id="txtProjectProposal" name="projectProposal" value="N" ${demandOrder.projectProposal=="N"?"checked='checked'" : ""}>否--%>
                            </font>
                        </td>
                    </tr>
                    <tr>
                        <td>

                            <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
                                <tbody>
                                <tr>
                                    <td style="text-align: right"><font color="red">*</font>项目名称:</td>
                                    <td>
                                        <input name="entryName" type="text" project="Y" value="${demandOrder.entryName}"
                                               id="txtEntryName">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>立项金额:</td>
                                    <td>
                                        <input class="rightalign" name="projectMoney" project="Y" type="text"
                                               value="${demandOrder.projectMoney==0?'':demandOrder.projectMoney}"
                                               id="txtProjectMoney">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>项目背景:</td>
                                    <td>
                                        <input name="projectBackground" type="text" project="Y"
                                               value="${demandOrder.projectBackground}" id="txtProjectBackground">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">收益核算方式:</td>
                                    <td>
                                        <input name="incomeAccountingMethod" type="text" project="Y"
                                               value="${demandOrder.incomeAccountingMethod}"
                                               id="txtIncomeAccountingMethod">
                                    </td>
                                    <td style="text-align: right">年收益金额:</td>
                                    <td>
                                        <input class="rightalign" name="annualIncomeAmount" project="Y" type="text"
                                               value="${demandOrder.annualIncomeAmount==0?'':demandOrder.annualIncomeAmount}"
                                               id="txtAnnualIncomeAmount">
                                    </td>
                                    <td style="text-align: right">投资回收期:</td>
                                    <td>
                                        <input name="paybackPeriodOfInvestment" type="text" project="Y"
                                               value="${demandOrder.paybackPeriodOfInvestment}"
                                               id="txtPaybackPeriodOfInvestment">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><font color="red">*</font>项目类别:</td>
                                    <td colspan="5">
                                        <input type="checkbox" value="外发转自制" name="projectType" project="Y">外发转自制
                                        <input type="checkbox" value="产能提升" name="projectType" project="Y">产能提升
                                        <input type="checkbox" value="替代人力" name="projectType" project="Y">替代人力
                                        <input type="checkbox" value="效率提升" name="projectType" project="Y">效率提升
                                        <input type="checkbox" value="能力建设" name="projectType" project="Y">能力建设
                                        <input type="checkbox" value="旧设备替换" name="projectType" project="Y">旧设备替换
                                        <input type="checkbox" value="基建" name="projectType" project="Y">基建
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"></td>
                                    <td colspan="5">
                                        <input type="checkbox" value="其他" name="projectType" project="Y">其他
                                        <input type="text" name="projectType" id="txtProjectType" value="" project="Y"
                                               size="70" maxlength="100">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </form>
                </tbody>
            </table>
            </br>
            <div class="divtitlemt">
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left" style="width: 150px">
                            <div class="imgtitle"></div>
                            附件
                        </td>
                        <td>
                            <input type="file" name="multipartFile" id="fileToUpload2" class="colorblur"
                                   onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                            <input type="submit" name="btnUpload" value="上传" id="btnUpload2" class="btn" project="Y">
                            <input type="submit" name="btnDelFile" value="删除" id="btnDelFile2" class="btn" project="Y">
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
                                        <tbody id="orderFileList2">
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


<div id="budgetWindow" name="" class="easyui-window" title="选择预算" data-options="iconCls:'icon-save'"
     style="width:750px;height:420px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px; width: 700px;">
            <div id="div1" class="divGrid">
                <form id="budgetForm">
                    <table class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="0">
                        <tr style="text-align: left">
                            <td>公司</td>
                            <td name=""><input type="text" id="companyName" readonly="readonly"
                                               name="companyName"><input type="hidden" id="company" readonly="readonly"
                                                                         name="company"></td>
                            <td>成本中心</td>
                            <td><input name="costCenterName" id="costCenterName" type="text" readonly="readonly"><input
                                    name="costCenter" id="costCenter" type="hidden" readonly="readonly"></td>
                        </tr>
                        <tr style="text-align: left">
                            <td>项目</td>
                            <td><input type="text" name="projectName" id="projectName"></td>
                            <td>年度</td>
                            <td>
                                <fmt:formatDate value='${demandOrder.applyDate}' pattern='yyyy'/><input
                                    name="assetsType" id="assetsType" type='hidden' readonly="readonly"/>
                                <input class="searchinput" readonly name="demandOrderId" type="hidden"
                                       value="${demandOrder.cdocument}">
                                <input class="searchinput" readonly name="budgetYear" type="hidden"
                                       value="<fmt:formatDate value='${demandOrder.applyDate}'  pattern='yyyy'/>">
                            </td>
                        </tr>
                    </table>
                </form>
                <input type="button" value="查询" id="searchBudger"><input type="button" id="budgerSure" value="确定">
            </div>
            <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;height: 240px;">
                <table id="assetsBudget" class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0"
                       border="1" cellpadding="6px">
                    <thead>
                    <tr>
                        <th></th>
                        <th>序号</th>
                        <th>公司</th>
                        <th>成本中心
                        </th>
                        <th>项目编码</th>
                        <th>项目名称</th>
                        <th>资产名称</th>
                        <th>资产类型</th>
                        <th>单位</th>
                        <th>预算年度</th>
                        <th>预算数量</th>
                        <th>预算单价</th>
                        <th>预算金额</th>
                        <th>已使用数量</th>
                        <th>已使用金额</th>
                        <!-- <th>可用数量</th> -->
                        <th>可用金额</th>
                    </tr>
                    </thead>
                    <tbody id="budgetBody"></tbody>
                </table>

            </div>
        </div>
    </div>
</div>
<div id="ysssbmWindow" name="" class="easyui-window" title="预算所属部门" data-options="iconCls:'icon-save'"
     style="width:600px;height:330px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <ul id="tree1"></ul>
        </div>
    </div>
</div>
<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script><!-- 在被请求页（或被包含页）已经引用，可能是因为冲突导致window问题：1，闪退  update by lk -->
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script> --%>
<script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>
<script type="text/javascript">
    var dg = frameElement.lhgDG;//定义dg 关闭需要
    var regionalDirector = "${regionalDirector}";//大区总监
    var message = "${updateErrorMessage}";
    if (message != "") {
        alert(message);
        dg.cancel();
    }

    //清除根据成本中心带出的信息
    function cleanDep() {
        $("#txtYsssbm").val("");
        $("#txtBudgetDepartment").val("");
        $("#txtCompanyCode").html("");/////////////////////
        //$("#txtAssetType1").html("");
        $("#txtPlatform").val("");
        $("#txtRegion").val("");
        $("#txtCostCenter").val("");
        $("#txtCostCenterCode").val("");
        $("#txtWorkShopType").val("");

        //清空后设置是否组装等 禁用
        changeAssetType();
        //清空行明细
        $("#table_detail").html("");
        statusIndex = 0;
        addRow();
        sumAll();
    }
    //预算所属部门 开始
    $('#btntxtYsssbmShow').click(function () {
        var top = "90px";//$(this).offset().top - 350;
        var left = "120px";//$(this).offset().left-180;
        $('#ysssbmWindow').window('open').window('resize', {width: '450px', height: '300px', top: top, left: left});

        $("#tree1").tree({
            url: '${dynamicURL}/department/searchDepartmentTreeInit',
            lines: true,
            onClick: function (node) {
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${dynamicURL}/userInfo/api/searchLowDeptCount",
                    data: "ckscode=" + node.id,
                    success: function (dat) {
                        if (dat == 0) {
                            alert("请选择末级部门！");
                        }
                        else {
                            setTree(node.id, node.text);
                        }
                    }
                });
            }
        });
    });

    function getCostCenter() {
        var ckscode = $("#txtYsssbm").val();
        var ccompanycode = $("#txtCompanyCode").val();
        if (!ckscode) {
            //资产类型是空
            return false;
        }
        if (!ccompanycode) {
            //资产类型是空
            return false;
        }
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
    });
    //联动
    function linkage(isUpload) {

        var ckscode = $("#txtYsssbm").val();
        var cksname = $("#txtBudgetDepartment").val();
        $.ajax({
            type: "POST",
            async: false,
            url: "${dynamicURL}/userInfo/api/getCompany",
            data: "ckscode=" + ckscode,
            success: function (treeObj) {
                var html = "";
                if (treeObj && treeObj.length > 1) {
                    html += "<option value=''>请选择</option>";
                }
                for (var i = 0; i < treeObj.length; i++) {
                    var ccompanycode = treeObj[i].ccompanycode;
                    var ccompanyname = treeObj[i].ccompanyname;

                    html += "<option value='" + ccompanycode + "'>(" + ccompanycode + ")" + ccompanyname + "</option>";
                }
                $("#txtCompanyCode").html(html);

                if (isUpload != "Y") {
                    //清空行明细
                    $("#table_detail").html("");
                    statusIndex = 0;
                    addRow();
                    sumAll();
                }
                $('#ysssbmWindow').window('close');
                if ($("#txtCompanyCode").val() != "") {
                    getCostCenter();
                }
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
         //console.log(assetlist);
         //$("#txtAssetType1").append("<option  value='' selected='selected'>--请选择--</option>");
         for ( var i = 0; i < assetlist.length; i++) {
         $("#txtAssetType1").append("<option value='" + assetlist[i].assetsType + "'>"+ assetlist[i].assetsType + "</option>");
         };
         }
         });
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
                if (arealist.length > 0) {
                    $("#txtRegion").css("background-color", "");
                } else {
                    $("#txtRegion").css("background-color", "#CCCCCC");
                }
                for (var i = 0; i < arealist.length; i++) {
                    $("#txtRegion").append("<option value='" + arealist[i].careaName + "'>" + arealist[i].careaName + "</option>");
                }
                ;

            }
        });
    }
    function setTree(ckscode, cksname) {
        //var ckscode = $("#tree1").tree('getSelected').id;
        //var cksname = $("#tree1").tree('getSelected').text;
        $("#txtYsssbm").val(ckscode);
        $("#txtBudgetDepartment").val(cksname);
        //预算所属部门 结束
        //联动
        linkage("N");

    }

    //给立项书多选框赋值
    var strProjectType = "${demandOrder.projectType}";
    var strs = strProjectType.split("-*-*-"); //字符分割 \
    $.each(strs, function (i, item) {
        if ($("input[name='projectType'][value=" + item + "]").length > 0) {
            $("input[name='projectType'][value=" + item + "]").attr("checked", "checked");
        } else {
            $("#txtProjectType").val($("#txtProjectType").val() + item + " ");
        }
    });
    //清空项目编码
    $("input[id='projectCodeClear']").live("click", function () {
        $(this).parent().find("input[id^='txtApplydep']").val("");
    });
    function searchBudgetLists() {

        $.ajax({
            type: "POST",
            cache: false,
            url: "${dynamicURL}/assetBudget/api/searchAssetsBudgetsList",
            data: $('#budgetForm').serialize(),
            // dataType: "json",
            async: false,
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                // $('#resText').empty();   //清空resText里面的所有内容
                var html = '';
                var com = $("#company").val();
                $('#budgetBody').html("");
                $.each(data, function (commentIndex, comment) {
                    html = '<tr style="white-space:nowrap;"><td><input type="radio" name="budgetRadio" value=' + commentIndex + '></td><td>'
                        + (commentIndex + 1) + '</td>'
                        + '<td>' + com + '</td><td>'
                        + comment['costCenter']
                        + '</td><td id=\"projectCode' + commentIndex + '\">' + comment['projectCode']
                        + '</td><td id=\"projectName' + commentIndex + '\">' + comment['projectName']
                        + '</td><td id=\"assetsName' + commentIndex + '\">' + comment['assetsName']
                        + '</td><td>' + comment['assetsType']
                        + '</td><td id=\"unit' + commentIndex + '\">' + comment['unit']
                        + '</td><td id=\"budgetYear' + commentIndex + '\">' + comment['budgetYear']
                        + '</td><td>' + comment['amount']
                        + '</td><td id=\"unitPrice' + commentIndex + '\">' + comment['unitPrice']
                        + '</td><td>' + comment['yearBudgetTotal']
                        + '</td><td>' + comment['usedAmount']
                        + '</td><td>' + comment['usedSumMoney']
                        //+ '</td><td id=\"avaliableAmount'+commentIndex+'\">' + comment['avaliableAmount']
                        + '</td><td id=\"avaliableSumMoney' + commentIndex + '\">' + comment['avaliableSumMoney']
                        + '</td></tr>';
                    $('#budgetBody').append(html);
                });

            }
        });
    }
    $("#searchBudger").click(function () {
        searchBudgetLists();
    })
    $("#table_detail").delegate("input[id^='btnRefShow']", "click", function () {
        /* 先判断是否选择公司和成本中心了 */
        var attc = $("#txtCompanyCode").val();
        var attcName = $("#txtCompanyCode").find("option:selected").text();
        var costc = $("#txtCostCenterCode").val();
        var costcName = $("#txtCostCenter").val();
        $("#company").val(attc);
        $("#companyName").val(attcName);
        $("#costCenter").val(costc);
        $("#costCenterName").val(costcName);
        $("#assetsType").val($("#txtAssetType1").val());

        //清空搜索条件(项目)
        $("#projectName").val("");
        var att = "";
        if (!attc) {
            att += "[公司]";
        }
        if (!costc) {
            att += "[成本中心]";
        }
        if (att) {
            alert("请选择" + att);
            return false
        }

        $("#budgetWindow").attr("name", ($(this).attr("id").substr(10)));//.substr(10))
        var top = "90px";//$(this).offset().top - 350;
        var left = "50px";//$(this).offset().left-180;
        $('#budgetWindow').window('open').window('resize', {width: '650px', height: '330px', top: top, left: left});
        searchBudgetLists();
    })

    $("#budgerSure").click(function () {
        var i = $("input[name='budgetRadio']:checked").val();
        if (!i) {
            alert("未选择任何一行数据!");
            return false;
        }

        var flag = false;
        var txtAssetType1 = $("#txtAssetType1").val();
        var assetsType = $("#assetsType" + i).html();

        $.ajax({
            type: "POST",
            async: false,
            //cache:false,
            url: "${dynamicURL}/demand/api/searchAssetsType",
            data: {"budgetAssetsType": assetsType},
            success: function (data) {
                if (!data) {
                    //没有匹配资产类型
                    //alert("没有匹配资产类型");
                    return true;
                } else if (txtAssetType1 != data.assetsType) {
                    if (!window.confirm("预算资产类型跟单据资产类型不匹配，确定选择该预算吗?")) {
                        flag = true;
                    }
                }
            }
        });
        if (flag) {
            return false;
        }

        var code = $("#projectCode" + i).html();//$("#assetsBudget").find("tr").eq(i+1).find("td").eq(4).text();
        var name = $("#assetsName" + i).html();//$("#assetsBudget").find("tr").eq(i+1).find("td").eq(5).text();
        var aMount = $("#avaliableAmount" + i).html();
        var aSumMoney = toDecimal2($("#avaliableSumMoney" + i).html());
        var unitPrice = $("#unitPrice" + i).html();//预算单价
        //var units = $("#unit"+i).html();//单位
        var budgetYear = $("#budgetYear" + i).html();//年度
        $('#budgetWindow').window('close');
        var j = $("#budgetWindow").attr("name");
        $("#txtAssetsName" + j).val(name);
        $("#txtBudgetAssetsName" + j).val(name);
        $("#txtApplydep" + j).val(code);
        $("#txtReferenceUnitPrice" + j).val(unitPrice);
        //$("#txtAvaliableAmount"+j).val(aMount);
        $("#txtAvaliableSumMoney" + j).val(aSumMoney);
        //$("#txtUnits"+j).val(units);
        $("#txtBudgetYear" + j).val(budgetYear);

    });

    var unitsHtml = "";
    $(function () {
        unitsHtml = $("#tdUnits").html();
    });
    var statusIndex =${demandOrderDetailes.size()};//序号
    $("#addRows").click(function () {//添加行
        changeAmount = "N";
        var isAllTrue = false;
        $("input[id^='txtApplydep']").each(function () {
            if (!$(this).val()) {
                isAllTrue = true;
            }
        });
        if (isAllTrue) {
            alert("行明细有未选择预算的数据，请先全部选择预算后再增行");
            return false;
        }
        addRow();
    });
    //序号重新排序
    function resetStatusIndex() {
        statusIndex = 0;
        $("td[id='statusIndex']").each(function () {
            statusIndex++;
            $(this).html(statusIndex);
        });
    }
    function addRow() {
        var isDisabled = "";
        if ($("#txtAssetType1").val() != "IT资产") {
            isDisabled = " disabled='disabled' ";
        }
        statusIndex++;
        var tableHtml = "<tr class='bodyTr' style='white-space:nowrap;'><td><input id='gvDataList_chkBolDel_0' type='checkbox' name='gvDataList_chkBolDel_0'></td>";
        tableHtml += "<td class='tdpadding' id='statusIndex'>" + statusIndex + "</td>";
        tableHtml += "<td class='tdpadding'><input readonly='readonly' name='projectCode' style='width:130px;' type='text' id='txtApplydep" + statusIndex + "' class='inputbtnRefViewGray'><input id='projectCodeClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X'><input id='btnRefShow" + statusIndex + "' class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'></td>";
        tableHtml += "<td class='budgetAssetsNameTd' style='padding-left: 10px; padding-right: 10px;'><input size='10' readonly type='text' name='budgetAssetsName' id='txtBudgetAssetsName" + statusIndex + "'></td>";
        tableHtml += "<td class='tdpadding'><select index='" + statusIndex + "' " + isDisabled + "  name='standardComputer' id='txtStandardComputer' style='width: 70px;'><option value=''>请选择</option><option value='Y'>是</option><option value='N'>否</option></select></td>";
        tableHtml += "<td class='assetsNameTd' style='padding-left: 10px; padding-right: 10px;'><input type='text' size='10' name='assetsName' id='txtAssetsName" + statusIndex + "'></td>";
        tableHtml += "<td class='specificationParameterTd' style='padding-left: 10px; padding-right: 10px;'><input size='10' type='text' name='specificationParameter' id='txtSpecificationParameter'></td>";
        /* 5 */
        tableHtml += "<td class='tdpadding'><input type='text' name='functions' id='txtFunctions' size='10'></td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='optionalAccessories' id='txtOptionalAccessories' size='10'></td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='acceptanceCriteria' id='txtAcceptanceCriteria' size='10'></td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='avaliableSumMoneyOld' class='rightalign' id='txtAvaliableSumMoney" + statusIndex + "' size='7' readonly='readonly'></td>";
        tableHtml += "<td class='tdpadding'>" + unitsHtml + "</td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='budgetYear' id='txtBudgetYear" + statusIndex + "' size='2' readonly></td>";
        /* 10 */
        tableHtml += "<td class='tdpadding'><input type='text' name='demandCount' class='rightalign' id='txtDemandCount" + statusIndex + "' size='5'></td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='allocationCount' class='rightalign' id='txtAllocationCount" + statusIndex + "' size='5'></td>";
        /* 15 */
        tableHtml += "<td class='tdpadding'><input type='text' name='referenceUnitPrice' class='rightalign' id='txtReferenceUnitPrice" + statusIndex + "' size='5' ></td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='referencePrice' class='rightalign' id='txtReferencePrice" + statusIndex + "' size='5'></td>";
        tableHtml += "<td class='tdpadding'><input readonly='readonly' name='requirementsDate' value='' type='text' id='txtRequirementsDate' class='Wdate' onclick=WdatePicker({skin:'ext'});></td>";
        /* 20 */
        tableHtml += "<td class='tdpadding'><input type='text' name='useDescription' id='txtUseDescription'></td></tr>";
        $("#table_detail").append(tableHtml);
        resetStatusIndex();
    }

    $("#delRows").click(function () {//删除行
        changeAmount = "N";
        $("#table_detail").find("input:checkbox[name=gvDataList_chkBolDel_0]:checked").each(function () {
            $(this).parent().parent().remove();
            statusIndex--;
        });
        $("#gvDataList_CheckAll").attr("checked", false);
        if (($.trim($("#table_detail").html())).length == 0) {
            statusIndex = 0;
            addRow();
        }
        //resetStatusIndex();
        sumAll();
    });

    //提交表单
    function saveOrder(strDraft, isMail, isPhoneMessage, idea, activityId) {
        var obj = {
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
            budgetYear: "",
            budgetAssetsName: "",
            avaliableSumMoneyOld: ""
        };
        var demandOrderWrapper = {};
        //添加表头
        objHeader = $("#demandOrderFormHeader").serializeObject();
        objFooter = $("#demandOrderFormFooter").serializeObject();
        //获得订单总金额
        objHeader.applyBudgetMoney = $("#sumTxtReferencePrice").val() == "" ? "0" : $("#sumTxtReferencePrice").val();
        //获得公司名称
        //objHeader.companyName = $("#txtCompanyCode").find("option:selected").text();
        objHeader.building = $("input[name='building']:checked").val();
        //修改立项书的项目类型
        var strpt = "";
        $('input[name="projectType"]:checked').each(function () {
            strpt += $(this).val() + "-*-*-";
        });
        strpt += $("#txtProjectType").val();
        objFooter.projectType = strpt;

        var objDemandOrder = $.extend({}, objHeader, objFooter);

        objDemandOrder.draft = strDraft;
        objDemandOrder.idea = idea;
        objDemandOrder.isMail = isMail;
        objDemandOrder.isPhoneMessage = isPhoneMessage;
        objDemandOrder.activityId = activityId;
        demandOrderWrapper.demandOrder = objDemandOrder;
        //公司名称
        objDemandOrder.companyName = $("#txtCompanyCode").find("option:selected").text();
        ///获得表单明细信息 封装Json对象
        var objArr = constructObjectFromTable(obj, $("#table_detail .bodyTr"));

        demandOrderWrapper.demandOrderDetails = objArr;
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/demand/api/saveUpdateDemand",
            data: JSON.stringify(demandOrderWrapper),
            success: function (data) {
                if (data.errorMessages.length > 0) {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*修改成功.*$");
                    if (reg.test(str)) {
                        dg.curWin.location = dg.curWin.location;
                    }
                    return;
                }
                alert("修改成功！");
                //关闭窗口
                dg.curWin.refresh();
                return;
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
    //验证所有金额类型
    function checkAll() {

        if (!$("#txtOfficeLocation").val()) {
            alert("请选择办公地点");
            return false;
        }
        if (parseInt($("sumTxtReferencePrice").val()) >= 30000 && $("#txtRegion").val()) {
            if (regionalDirector == "N") {
                alert("区域类单据，总价大于等于30万时必须由大区总监申请！");
                return false;
            }
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
        if (!$("#txtYsssbm").val()) {
            alert("请选择需求/申请部门");
            return false;
        }
        if (!$("#txtPlatform").val()) {
            alert("平台不能为空![该成本中心和资产类型没有维护数据]");
            return false;
        }
        var f = false;
        //资产需求中的资产名称、规格参数、功能、选配件、验收标准、单位  要验证必填 不可为空
        if ($("#txtAssetType1").val() == 'IT资产') {
            $("select[id='txtStandardComputer']").each(function () {
                if ($(this).val() == "") {
                    alert("请选择是否电脑！");
                    f = true;
                    return false;
                }
            });
            if (f) {
                return false;
            }
        }
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
        $("input[id^='txtUseDescription']").each(function () {
            if ($(this).val().length > 100) {
                alert("用途说明长度不能超过100！");
                f = true;
                return false;
            }
        });
        if (f) {
            return false;
        }
        $("select[id='units']").each(function () {
            if ($(this).val() == "") {
                alert("单位不可为空！");
                f = true;
                return false;
            }
        });
        if (f) {
            return false;
        }
        //制造中心或供应链中心  3W
        //研发中心  5W
        var txtPlatform = $("#txtPlatform").val();
        var txtProjectProposal = $("#txtProjectProposal").val();
        if (txtProjectProposal == "") {
            alert("请选择是否立项书");
            return false;
        }
        if (txtPlatform == "制造中心" || txtPlatform == "供应链中心") {
            $("input[id^=txtReferencePrice]").each(function () {
                if ($(this).val() > 30000 && txtProjectProposal == "N") {
                    alert("[" + txtPlatform + "]金额超过30000元，请立项!");
                    f = true;
                    return false;
                }
            });
        }
        if (txtPlatform == "研发中心") {
            $("input[id^=txtReferencePrice]").each(function () {
                if ($(this).val() > 50000 && txtProjectProposal == "N") {
                    alert("[研发中心]金额超过50000元，请立项!");
                    f = true;
                    return false;
                }
            });
        }
        if (f) {
            return false;
        }
        //如果选择立项 则判断立项金额
        if (txtProjectProposal == "Y") {
            var strProjectMoney = $("#txtProjectMoney").val();				// 立项金额
            var strAnnualIncomeAmount = $("#txtAnnualIncomeAmount").val();	// 年收益金额
            if (strProjectMoney == "" || isNaN(strProjectMoney)) {
                alert("立项金额不正确!");
                return false;
            }
            if (parseInt(strProjectMoney) < parseInt($("#sumTxtReferencePrice").val())) {
                alert("资产申购金额不可超立项金额!");
                return false;
            }
            if (strAnnualIncomeAmount == "" || isNaN(strAnnualIncomeAmount)) {
                $("#txtAnnualIncomeAmount").val("0");
                return false;
            }
            if ($("#txtEntryName").val() == "") {
                alert("项目名称不能为空!");
                return false;
            }
            if ($("#txtProjectBackground").val() == "") {
                alert("项目背景不能为空!");
                return false;
            }
            if ($("#txtEntryName").val().length > 100) {
                alert("项目名称长度不能超过100！");
                f = true;
                return false;
            }
            if ($("#txtProjectBackground").val() == "") {
                alert("项目背景不能为空!");
                return false;
            }
            if ($("#txtProjectBackground").val().length > 1000) {
                alert("项目背景长度不能超过1000！");
                f = true;
                return false;
            }
            if ($("#txtIncomeAccountingMethod").val().length > 100) {
                alert("收益核算方式长度不能超过100！");
                f = true;
                return false;
            }
            if ($("#txtPaybackPeriodOfInvestment").val().length > 100) {
                alert("投资回收期长度不能超过100！");
                f = true;
                return false;
            }
            /* if($("#txtIncomeAccountingMethod").val()==""){
             alert("收益核算方式不能为空!");
             return false;
             }
             if($("#txtPaybackPeriodOfInvestment").val()==""){
             alert("投资回收期不能为空!");
             return false;
             } */
            //判断立项内容
            var strpt = "";
            $('input[name="projectType"]:checked').each(function () {
                strpt += $(this).val() + "-*-*-";
            });
            strpt += $("#txtProjectType").val();
            if (($.trim(strpt)).length == 0) {
                alert("项目类别不能为空!");
                return false;
            }
            if (($("#orderFileList2").html()).indexOf('没有记录') >= 0) {
                alert("请上传立项书文件!");
                return false;
            }
            if (!$("#txtEntryName").val()) {
                alert("项目名称不能为空!");
                return false;
            }
            if (!$("#txtProjectBackground").val()) {
                alert("项目背景不能为空!");
                return false;
            }
            /* if(!$("#txtIncomeAccountingMethod").val()) {
             alert("收益核算方式不能为空!");
             return false;
             }
             if(!$("#txtPaybackPeriodOfInvestment").val()) {
             alert("投资回收期不能为空!");
             return false;
             } */

        } else {
            $("#txtProjectMoney").val("0");			// 立项金额
            $("#txtAnnualIncomeAmount").val("0");	// 年收益金额
        }

        var flag = true;
        $("input[id^='txtDemandCount']").each(function () {
            var c = $(this).val();//数量
            var p = $(this).parent().parent().find("input[id^='txtReferenceUnitPrice']").val();//单价
            var s = $(this).parent().parent().find("input[id^='txtReferencePrice']").val();//总价
            var a = $(this).parent().parent().find("input[id^='txtAllocationCount']").val();//调拨数量
            if (c <= a) {
                alert("需求数量不能小于调拨数量");
                flag = false;
                return false;
            }
            if (c == "" || isNaN(c) || c <= 0) {
                alert("需求数量不正确");
                flag = false;
                return false;
            }
            if (p == "" || isNaN(p) || p <= 0) {
                alert("单价不正确");
                flag = false;
                return false;
            }
            if (s == "" || isNaN(s) || s <= 0) {
                alert("总价不正确");
                flag = false;
                return false;
            }
        });
        if (!flag) {
            return flag;
        }
        $("input[id='txtRequirementsDate']").each(function () {
            if ($(this).val() == "") {
                alert("要求到位日期格式不正确");
                flag = false;
                return false;
            }
        });
        return flag;
    }

    var changeAmount = "Y";
    function showAgreeDialog() {
        var document = $("#txtDemandOrderId").val();
        if (($("#oldApplyBudgetMoney").val() - 0) - ($("#sumTxtReferencePrice").val() - 0) == 0) {
            changeAmount = "N";
        }
        var url = "${dynamicURL}/workflow/showUpdateCommitDialog?parentWindowId=update&document=" + document + "&typeId=" + 77001 + "&changeAmount=" + changeAmount;

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
            $("#btnSave").attr("disabled", "disabled");//添加disabled属性
            $("#btnSaveDraft").attr("disabled", "disabled");
            $("#btnCancel").attr("disabled", "disabled");
            saveOrder("Y", "", "", "", "");
            $("#btnSave").removeAttr("disabled"); //移除disabled属性
            $("#btnSaveDraft").removeAttr("disabled");
            $("#btnCancel").removeAttr("disabled");
        }
    });

    //合计
    function sumAll() {
        var sumDemandCount = 0;
        //数量*单价
        $("input[id^='txtDemandCount']").each(function () {
            var c = $(this).val();//数量
            sumDemandCount += c - 0;
            var p = $(this).parent().parent().find("input[id^='txtReferenceUnitPrice']").val();//单价
            var s = $(this).parent().parent().find("input[id^='txtReferencePrice']").val();//总价
            if (s != "") {//总价不是""  用总价 除以 数量  获得 单价
                if (c != "") {
                    $(this).parent().parent().find("input[id^='txtReferenceUnitPrice']").val((s / c - 0).toFixed(2));//单价
                }
            }
            else {//如果总价不是数字(输入单价时会清空总价)  用数量*单价 获得 总价
                if (c != "" && p != "") {
                    $(this).parent().parent().find("input[id^='txtReferencePrice']").val((c * p - 0).toFixed(2));//总价
                }
            }
        });
        $("#sumDemandCount").val(sumDemandCount);
        //合计参考总价
        var sumReferencePrice = 0;
        $("input[id^='txtReferencePrice']").each(function () {
            sumReferencePrice += $(this).val() - 0;
        });
        $("#sumTxtReferencePrice").val(sumReferencePrice.toFixed(2));
    }

    //需求数量
    $("#table_detail").delegate("input[id^='txtDemandCount']", "keyup", function () {
        $(this).parent().parent().find("input[id^='txtReferencePrice']").val("");
        sumAll();
    });
    //参考单价
    $("#table_detail").delegate("input[id^='txtReferenceUnitPrice']", "keyup", function () {
        //清空总价
        $(this).parent().parent().find("input[id^='txtReferencePrice']").val("");
        sumAll();
    });
    //参考总价
    $("#table_detail").delegate("input[id^='txtReferencePrice']", "keyup", function () {
        sumAll();
    });
    var standardComputerArray;
    //是否标准电脑选中事件
    $("#txtStandardComputer").live('change', function () {
        if ($(this).children('option:selected').val() == "Y") {
            $.ajax({
                type: "POST",
                async: false,
                url: "${dynamicURL}/demand/api/findComputer",
                success: function (data) {
                    standardComputerArray = data;
                }
            });
            var assetsNamehtml = "<select name='assetsName' id='txtAssetsName'>";
            for (var i = 0; i < standardComputerArray.length; i++) {
                assetsNamehtml += "<option value='" + standardComputerArray[i].assetsName + "'>" + standardComputerArray[i].assetsName + "</option>";
            }
            assetsNamehtml += "</select>";
            $(this).parent().parent().find(".assetsNameTd").html(assetsNamehtml);
            $(this).parent().parent().find("#txtSpecificationParameter").attr("readonly", "readonly");
            $(this).parent().parent().find("#txtSpecificationParameter").val(standardComputerArray[0].specificationParameter);
            $(this).parent().parent().find("#txtOptionalAccessories").attr("readonly", "readonly");
            $(this).parent().parent().find("#txtOptionalAccessories").val("");
        } else {
            var assetsNamehtml = "<input type='text' name='assetsName' size = '10' id='txtAssetsName" + $(this).attr("index") + "'>";
            $(this).parent().parent().find(".assetsNameTd").html(assetsNamehtml);
            $(this).parent().parent().find("#txtSpecificationParameter").attr("readonly", "");
            $(this).parent().parent().find("#txtSpecificationParameter").val("");
            $(this).parent().parent().find("#txtOptionalAccessories").attr("readonly", "");
        }
    });
    //标准电脑 资产名称选中事件
    $("select[name='assetsName']").live('change', function () {
        var $selectAssetsName = $(this);
        for (var i = 0; i < standardComputerArray.length; i++) {
            if (standardComputerArray[i].assetsName == $selectAssetsName.val()) {
                $selectAssetsName.parent().parent().find("#txtSpecificationParameter").val(standardComputerArray[i].specificationParameter);
            }
        }
    });


    $(function () {
        //联动获得基础下拉列表信息
        linkage("Y");

        //资产类型赋值
        var assetType = "${demandOrder.assetType}";
        $("#txtAssetType1").val(assetType);

        //办公地点赋值
        var officeLocation = "${demandOrder.officeLocation}";
        $("#txtOfficeLocation").val(officeLocation);
        //公司赋值
        var companyCode = "${demandOrder.companyCode}";
        $("#txtCompanyCode").val(companyCode);
        //平台赋值
        var platform = "${demandOrder.platform}";
        $("#txtPlatform").val(platform);

        setValues();
        //区域赋值

        var region = "${demandOrder.region}";
        $("#txtRegion").val(region);

        /* //车间类型赋值
         $("#txtWorkShopType option").remove();
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
         $("#txtWorkShopType").val(workShopType); */


        //合计
        sumAll();
        //根据资产类型显示车间类型、房屋、组装
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
                $(this).attr("disabled", "disabled");
            });
        }

        selectProject();
    });

    /*tdBuilding 房屋建筑物  tdAssemble组装   tdWorkShopType 车间类型 */
    //选中资产类型时
    $("#txtAssetType1").change(function () {
        /*var assetsType = $("#txtAssetType1").children("option:selected").val();
         if (assetsType == "IT资产") {
         $("#txtBudgetDepartment").val($("#txtApplyDepartment").val());
         $("#txtYsssbm").val($("#txtApplyDepartmentCode").val());
         setTree(ckscode,cksname)
         }*/

        //清空行明细
        $("#table_detail").html("");
        statusIndex = 0;
        addRow();
        sumAll();


        setValues();
        changeAssetType();
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
        /* $("input[id='txtBuilding']").each(function(){
         $("input:radio[value='N']").attr("checked","checked");
         $(this).attr("disabled","disabled");
         });
         var assetsType = $("#txtAssetType1").children("option:selected").val();
         if(assetsType=="行政资产"){
         //移除disabled属性
         $("input[id='txtBuilding']").each(function(){
         $(this).removeAttr("disabled");
         });
         } */
    }
    $("#txtProjectProposal").change(function () {
        selectProject();
    });
    //选择是否立项
    function selectProject() {
        if ($("#txtProjectProposal").val() == "Y") {
            $("input[project='Y']").each(function () {
                $(this).removeAttr("readonly");
            });
            $("input:checkbox[project='Y']").each(function () {
                $(this).removeAttr("disabled");
            });
            $("input:submit[project='Y']").each(function () {
                $(this).removeAttr("disabled");
            });
        } else {
            $("input[project='Y']").each(function () {
                $(this).attr("readonly", "readonly");
            });
            $("input:checkbox[project='Y']").each(function () {
                $(this).attr("disabled", "disabled");
            });
            $("input:submit[project='Y']").each(function () {
                $(this).attr("disabled", "disabled");
            });
        }
    }
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


    $('#btnUpload2').click(function () {
        var fileElementId = $(this).prev().attr('id');
        var file = $('#fileToUpload2').val();
        var fileType = file.substring(file.lastIndexOf(".") + 1);
        if (fileType != "pdf" && fileType != "PDF") {
            alert("上传文件格式错误！只能为pdf格式！")
            return false;
        }
        if (file == "") {
            alert("请选择需要上传的文件！");
            return false;
        }

        var documentNo = $('#txtDemandOrderId').val() + 'LX';
        if ("" == documentNo) {
            alert('单据号不可为空');
            return false;
        }

        uploadFile('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}', documentNo, $('#uploadTable2 > tbody'), fileElementId);

    });

    $('#btnDelFile2').click(function () {
        deleteFiles('${dynamicURL}', "uploadTable2");
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
    });


    //制保留2位小数，如：2，会在2后面补上00.即2.00    
    function toDecimal2(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return false;
        }
        var f = Math.round(x * 100) / 100;
        var s = f.toString();
        var rs = s.indexOf('.');
        if (rs < 0) {
            rs = s.length;
            s += '.';
        }
        while (s.length <= rs + 2) {
            s += '0';
        }
        return s;
    }
    $(document).ready(function () {
        $(window).resize(function () {
            $('#purchaseTab').tabs('resize');
        });
    });
</script>
</body>


</html>
