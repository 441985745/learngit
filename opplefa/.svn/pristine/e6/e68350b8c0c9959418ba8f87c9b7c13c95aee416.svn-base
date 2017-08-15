<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <title>资产验收单</title>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
<!-- 待验收单据 page1--s -->
<div>
    <input type="hidden" name="hfQueryStatus" id="hfQueryStatus" value="show">
    <table cellspacing="0" cellpadding="0" style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form>
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif"
                                 style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">基本信息
                        </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="t_ShipInportInfo"
                               class="adminlist" style="visibility: visible;">
                            <tbody>
                            <tr>
                                <td style="text-align: right">收货单据号:</td>
                                <td class="tdtext"><input type="text" value="${receiveGoodsTemp.receiveGoodsId }"
                                                          id="receiveGoodsIdTemp"></td>
                                <td style="text-align: right"><font color="red">*</font>资产类型:</td>
                                <td><input type="text" id="assetsTypeName" value="${receiveGoodsTemp.assetsType }"></td>
                            </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </td>
        </tr>
        <tr id="Button">
            <td class="buttonbar">&nbsp;&nbsp;
                <input type="button" name="" value="确定" id="btnAddCheckWindow" class="btn">
                <input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
            </td>
        </tr>
        <tr>
            <td valign="top">
                <div>
                    <table class="adminlist" cellspacing="0" rules="all" border="1"
                           id="gvDataList" style="border-collapse: collapse;">
                        <tbody>
                        <tr style="white-space: nowrap;">
                            <th scope="col">
                                <input id="" type="radio" name="" disabled="disabled"
                                       onclick="javascript:SelectAllCheckboxesByTableId(this, 'gvDataList');"/>
                            </th>
                            <th scope="col">&nbsp;&nbsp;<font color="red">*</font>资产名称&nbsp;&nbsp;
                            </th>
                            <th scope="col">&nbsp;&nbsp;<font color="red">*</font>收货数量&nbsp;&nbsp;
                            </th>
                            <th scope="col">&nbsp;&nbsp;<font color="red">*</font>已验收数量&nbsp;&nbsp;
                            </th>
                            <th scope="col">&nbsp;&nbsp;<font color="red">*</font>本次验收数量&nbsp;&nbsp;
                            </th>
                        </tr>
                        <c:forEach items="${receiveGoodsDetailTemp }" var="o" varStatus="status">
                            <tr style="white-space: nowrap;">
                                <td align="center">
                                    <input id="gvDataList_chkBolDel_0" type="radio"
                                           name="gvDataList$ctl02$chkBolDel">
                                </td>
                                <td>
                                    <input type="text" readonly value="${o.assetsName }">
                                    <input type="hidden" name="linereceiveGoodsId" id="linereceiveGoodsId"
                                           value="${o.receiveGoodsId}"
                                           title="receiveGoodsId">
                                    <input type="hidden" name="linereceiveGoodsDetailId" id="linereceiveGoodsDetailId"
                                           value="${o.receiveGoodsDetailId}" title="receiveGoodsDetailId">
                                    <input type="hidden" name="" id="linepurchaseDetailId"
                                           value="${o.purchaseDetailId}" title="purchaseDetailId">
                                    <input type="hidden" name="" id="lineapplyDetailId" value="${o.applyDetailId}"
                                           title="applyDetailId">
                                </td>
                                <td><input type="text" name="lineGoodsCount" readonly id="lineGoodsCount"
                                           value="${o.goodsCount }"></td>
                                    <%--已收货数量--%>
                                <td><input type="text" name="lineCheckCount" readonly id="lineCheckCount"
                                           value="${o.checkCount }"></td>
                                    <%--已验收数量--%>
                                <td><input type="text" name="lineThisAcceptCount" readonly id="lineThisAcceptCount"
                                           value="${o.thisCheckCount }"></td>
                                    <%--本次验收数量（默认为本次最大验收数量）--%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<!-- 待验收单据--e -->

<!--头   验收单page3--s-->
<div id="addCheckWindow" name="addCheckWindow" class="easyui-window" title="验收单新增" data-options="iconCls:'icon-save'"
     style="width:950px;height:500px;padding:5px;" closed="true">
    <div style="width:100%; margin: 0 auto;">
        <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
            <input type="button" name="" onclick="btnsaveCheck(-1)" value="提交" id="btnsaveCheck" class="btn">
            <%--<input type="button" name="" onclick="btnsaveCheck(-2)" value="保存草稿" id="btnsaveCheckDraft" class="btn">--%>
            <input type="button" name="" value="返回" onclick="" id="btnSaveCheckCancel" class="btn">
        </div>

        <form action="receiveGoodsHeader" id="receiveGoodsHeaderForm">
            <table id="addReceiveGoodsHeaderTable1" border="0" cellpadding="3px" cellspacing="0" width="90%">
                <tbody>

                <%--<c:if test="${receiveGoodsTemp.assetsType  != 'IT资产' }">--%>
                <!-- it资产验收单 -->
                <div id='all'>
                    <tr>
                        <%-- <td align="right" class="tdtitle">采购员:
                         </td>--%>

                        <input name="buyerName" value="" type="hidden" id="buyerName" class="colorblur">
                        <input name="buyerCode" value="" type="hidden" id="buyerCode" class="colorblur">
                        <input name="attachDepartAdminCode" value="${receiveGoodsTemp.attachDepartAdminCode}"
                               type="hidden" id="attachDepartAdminCode">
                        <input name="attachDepartAdminName" value="${receiveGoodsTemp.attachDepartAdminName}"
                               type="hidden" id="attachDepartAdminName">
                        <input name="orderType" value="" type="hidden" id="orderType">

                        <input name="isMail" id="isMail" type="hidden" value=""/>
                        <input name="isPhoneMessage" id="isPhoneMessage" type="hidden" value=""/>
                        <input name="idea" id="idea" type="hidden" value=""/>

                        <td align="right" class="tdtitle">采购订单号:
                        </td>
                        <td class="tdtext">
                            <input name="purchaseId" value="" type="text" id="purchaseId" class="colorblur"
                                   readonly>
                            <input name="purchaseDetailId" value="" type="hidden" id="purchaseDetailId"
                                   class="colorblur">
                            <input name="applyDetailId" value="" type="hidden" id="applyDetailId" class="colorblur">
                        </td>
                        <td align="right" class="tdtitle">验收单编码:
                        </td>
                        <td class="tdtext">
                            <input name="checkAcceptId" value="" readonly type="text" id="checkAcceptId"
                                   class="colorblur">
                        </td>

                        <td align="right" class="tdtitle">收货单号:
                        </td>
                        <td class="tdtext">
                            <input name="receiveGoodsId" value="" type="text" id="receiveGoodsId" class="colorblur"
                                   readonly>
                            <input name="receiveGoodsDetailId" value="" type="hidden" id="receiveGoodsDetailId"
                                   class="colorblur">
                        </td>
                    </tr>
                    <tr>
                        <td align="right" class="tdtitle"> 使用信息：
                            <!-- 在用 -->
                        </td>
                        <td class="tdtext">
                            <input name="useInfo" value="在用 " type="text" id="useInfo" class="colorblur" readonly>
                        </td>


                        <td align="right" class="tdtitle">资产类别:
                        </td>
                        <td class="tdtext">
                            <input name="assetsType" value="" type="text" id="assetsType" class="colorblur"
                                   readonly>
                        </td>
                        <td align="right" class="tdtitle">使用状态:
                        </td>
                        <td class="tdtext">
                            <input name="useStatus" value="正在使用" type="text" id="useStatus" class="colorblur"
                                   readonly>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" class="tdtitle">到货日期:
                        </td>
                        <!-- 实际收货日期 -->
                        <td class="tdtext">
                            <input name="receiveDate" value="" type="text" id="receiveDate" class="colorblur"
                                   readonly>
                        </td>
                        <td align="right" class="tdtitle">使用年限:
                        </td>
                        <!-- 注意：因为是在申请的行里，所以不能直接查询出来，显示，得通过选定收货行来确定是哪个申请行，然后填充 -->
                        <!-- 申请行里的 折旧年限 -->
                        <td class="tdtext">
                            <input name="depreciation" value="" type="text" id="depreciation" class="colorblur"
                                   readonly>
                        </td>
                        <td align="right" class="tdtitle">计量单位:
                        </td>
                        <td class="tdtext">
                            <!-- 申请行里的单位 -->
                            <input name="units" value="" type="text" id="units" class="colorblur" readonly>
                        </td>

                    </tr>
                    <%--<td align="right" class="tdtitle">合同号:
                    </td>
                    <!-- 收货头取 -->
                    <td class="tdtext">
                        <input name="contractNo" value="" type="text" id="contractNo" class="colorblur">
                    </td>
                    <td align="right" class="tdtitle">批准文号:
                    </td>
                    <!-- 手填 -->
                    <td class="tdtext">
                        <input name="approvalCode" value="" type="text" id="approvalCode" class="colorblur">
                    </td>

                    <tr>



                        <td align="right" class="tdtitle">型号规格/品牌:
                        </td>
                        <!-- 在收货行里已经保存，这里也得确定哪个收货行，然后回写 -->
                        <!-- 申请表里带出 -->

                        <td class="tdtext">
                            <input name="brand" value="" type="text" id="brand" class="colorblur">
                        </td>
                        <td align="right" class="tdtitle">供应商:
                        </td>
                        <td class="tdtext">
                            <input name="supplierName" value="" type="text" id="supplierName" class="colorblur">
                            <input name="supplierCode" value="" type="hidden" id="supplierCode" class="colorblur">
                        </td>

                        <%--<td align="right" class="tdtitle">启用日期:
                        </td>
                        <!-- 手填 -->
                        <td class="tdtext">
                            <c:if test="${receiveGoodsTemp.assetsType == '计量器具' }">
                                <input name="enableDate" value="" type="text" id="enableDate" class="colorblur">
                            </c:if>
                            <c:if test="${receiveGoodsTemp.assetsType != '计量器具' }">
                                <input type="text" readonly style="width: 200px;">
                            </c:if>
                        </td>--%>

                    </tr>
                    <tr>
                        <%-- <td align="right" class="tdtitle">采购需求部门:  放到明细列中展示，因为可能来自不同采购需求部门，造成重复数据
                         </td>
                         <td class="tdtext">
                             <input name="budgetDepartmentName" value="" type="text" id="budgetDepartmentName"
                                    class="colorblur" readonly>
                             <input name="budgetDepartmentCode" value="" type="hidden" id="budgetDepartmentCode"
                                    class="colorblur">
                         </td>--%>
                        <td align="right" class="tdtitle">成本中心:
                        </td>
                        <td class="tdtext">
                            <input name="costCenter" value="" type="text" id="costCenter" class="colorblur"
                                   readonly>
                            <input name="costCenterCode" value="" type="hidden" id="costCenterCode"
                                   class="colorblur">
                        </td>
                        <td align="right" class="tdtitle">公司名称:
                        </td>
                        <td class="tdtext">
                            <input name="computerName" value="" type="text" id="computerName" class="colorblur"
                                   readonly>
                            <input name="computerCode" value="" type="hidden" id="computerCode" class="colorblur">
                        </td>
                        <td align="right" class="tdtitle">合同号:
                        </td>
                        <!-- 收货头取 -->
                        <td class="tdtext">
                            <input name="contractNo" readonly value="" type="text" id="contractNo"
                                   class="colorblur">
                        </td>

                    </tr>
                    </tr>
                    <tr>
                        <td align="right" class="tdtitle">收货数量:
                        </td>
                        <td class="tdtext">
                            <input name="goodsCount" value="" type="text" id="goodsCount" class="colorblur"
                                   readonly>
                        </td>
                        <td align="right" class="tdtitle">已验收数量:
                        </td>
                        <td class="tdtext">
                            <input name="checkCount" readonly value="" type="text" id="checkCount" class="colorblur"
                                   readonly>
                        </td>
                        <td align="right" class="tdtitle">本次验收数量:
                        </td>
                        <td class="tdtext">
                            <input name="thisCheckCount" value="" type="text" id="thisCheckCount" class="colorblur"
                                   readonly>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" class="tdtitle">采购申请部门:
                        </td>
                        <!-- 采购申请部门(收货头里已经保存) purchaseCommon中已经保存-->
                        <td class="tdtext">
                            <input name="applyDepartment" value="" readonly type="text" id="applyDepartment"
                                   class="colorblur">
                            <input name="applyDepartmentCode" value="" type="hidden" id="applyDepartmentCode"
                                   class="colorblur">
                        </td>
                        <td align="right" class="tdtitle">型号规格/品牌:
                        </td>
                        <!-- 在收货行里已经保存，这里也得确定哪个收货行，然后回写 -->
                        <!-- 申请表里带出 -->

                        <td class="tdtext">
                            <input name="brand" value="" readonly type="text" id="brand" class="colorblur">
                        </td>
                        <td align="right" class="tdtitle">供应商:
                        </td>
                        <td class="tdtext">
                            <input name="supplierName" value="" readonly type="text" id="supplierName"
                                   class="colorblur">
                            <input name="supplierCode" value="" type="hidden" id="supplierCode" class="colorblur">
                        </td>
                        <%--<td align="right" style="display: none" class="tdtitle">总账科目:
                        </td>
                        <!-- 收货行 -->
                        <td class="tdtext">
                            <input name="ledgerAccount" style="display: none" value="" type="text" id="ledgerAccount" class="colorblur"
                                   readonly>
                        </td>--%>
                    </tr>
                    <tr>


                        <%-- <td align="right" class="tdtitle">存放位置:
                         </td>--%>

                        <!-- 根据办公地点 获取   办公地点 从申请单头抓取 -->
                        <%--<td class="tdtext">--%>
                        <%--<input name="storageLocation" value="" type="text" id="storageLocation"--%>
                        <%--class="colorblur" readonly>--%>
                        <%--</td>--%>


                    </tr>
                    <tr>
                        <td align="right" class="tdtitle">办公地点:
                        </td>
                        <td class="tdtext">
                            <input name="officeLocation" readonly value="" type="text"
                                   id="officeLocation" class="colorblur">
                        </td>
                    </tr>
                </div>
                <!-- it资产验收单 -->
                <%--</c:if>--%>

                </tbody>
            </table>
        </form>
        </br>
        <div style="width:90%; margin: 0 auto;">
            <form action="receiveGoodsDetail" id="receiveGoodsDetailForm">

                <table id="addReceiveGoodsDetailTable1" cellpadding="0px" cellspacing="0" class="tablenoborder">
                    <tbody>
                    <tr>
                        <td colspan="4">
                            <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                                   style="table-layout: fixed;">
                                <tbody>
                                <tr>
                                    <td class="titlefontsize" align="left">
                                        <div class="imgtitle">
                                        </div>
                                        资产信息
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" class="tdtitle" width="50px;">
                                        全局设置存放位置：
                                    </td>
                                    <td class='tdpadding' align="left"><input style='width: 100px;'
                                                                              name='storageLocation1' type='text'
                                                                              id='storageLocationLine1'
                                                                              class='inputbtnRefViewGray'>
                                        <input id='btnRefClear1' class='btnRefView' title='点击清空参照文本框内容'
                                               type='button' value='X' onclick='cleanDepAll();'>
                                        <input id='storageLocationShow1' class='btnRefView' title='点击打开参照文本框内容'
                                               type='button' value='...'>
                                    </td>

                                </tr>
                                <tr>
                                    <td class="tdtitle" style="text-align: right" colspan="2">
                                        <div id="div11" class="divGrid"
                                             style="overflow-x: scroll; overflow-y: visible;">
                                                <table id="table_add_detail1" class="adminlist" cellspacing="0"
                                                       rules="all" border="1"
                                                       style="border-collapse:collapse;">
                                                    <tr style="white-space:nowrap;">
                                                        <th class='tdpadding' scope="col">序号</th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>需求单号
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>资产名称
                                                        </th>
                                                        <th class='tdpadding' scope="col">
                                                            <c:if test="${receiveGoodsTemp.assetsType == '计量器具'}">
                                                                <font color="red">*</font>
                                                            </c:if>
                                                            归口管理编号
                                                        </th>
                                                        <th class='tdpadding' scope="col">
                                                            <c:if test="${isManager == 'Y'}">
                                                                <font color="red">*</font>
                                                            </c:if>
                                                            <c:choose>
                                                                <c:when test="${receiveGoodsTemp.assetsType == 'IT资产'}">
                                                                    序列号
                                                                </c:when>
                                                                <c:when test="${receiveGoodsTemp.assetsType == '计量器具'}">
                                                                    出厂编号
                                                                </c:when>
                                                                <c:when test="${receiveGoodsTemp.assetsType == '机器设备'}">
                                                                    机器编号
                                                                </c:when>
                                                                <c:when test="${receiveGoodsTemp.assetsType == '行政资产'}">
                                                                    出厂编号/序列号
                                                                </c:when>
                                                            </c:choose>
                                                        </th>
                                                        <!-- 手动填 -->
                                                        <th class='tdpadding' scope="col"><font
                                                                color="red">*</font>资产照片
                                                        </th>
                                                        <c:if test="${receiveGoodsTemp.assetsType == '计量器具'}">
                                                            <!-- 上传/查看 -->
                                                            <th class='tdpadding' scope="col"><font
                                                                    color="red">*</font>是否有第三方校验报告
                                                            </th>
                                                        </c:if>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>保修周期
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>存放位置
                                                        </th>
                                                        <th class='tdpadding' style="display: none" scope="col">
                                                            <font color="red">*</font>mm固定资产编号
                                                        </th>
                                                        <th class='tdpadding' style="display: none" scope="col">
                                                            <font color="red">*</font>上传图片document
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>资产编号
                                                        </th>
                                                        <!-- MM+sap主编码+sap次级编码 -->

                                                        <!-- 手动填 -->
                                                        <%--<th class='tdpadding' scope="col"><font color="red">*</font>是否有粘贴资产标签
                                                        </th>--%>
                                                        <c:if test="${receiveGoodsTemp.assetsType == '计量器具'}">
                                                            <th class='tdpadding' scope="col"><font
                                                                    color="red">*</font>生产厂家
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == '计量器具'}">
                                                            <th class='tdpadding' scope="col"><font
                                                                    color="red">*</font>管理级别
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == '计量器具'}">
                                                            <th class='tdpadding' scope="col"><font
                                                                    color="red">*</font>精度等级
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == '计量器具'}">
                                                            <th class='tdpadding' scope="col"><font
                                                                    color="red">*</font>检定周期（日）
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == '计量器具'}">
                                                            <th class='tdpadding' scope="col"><font
                                                                    color="red">*</font>校准日期
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == '计量器具'}">
                                                            <th class='tdpadding' scope="col"><font
                                                                    color="red">*</font>下次校准日期
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == '计量器具'}">
                                                            <th class='tdpadding' scope="col"><font
                                                                    color="red">*</font>校准单位
                                                            </th>
                                                        </c:if>

                                                        <!-- IT资产-->
                                                        <c:if test="${receiveGoodsTemp.assetsType == 'IT资产'}">
                                                            <th class='tdpadding' scope="col">CPU
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == 'IT资产'}">
                                                            <th class='tdpadding' scope="col">内存
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == 'IT资产'}">
                                                            <th class='tdpadding' scope="col">硬盘
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == 'IT资产'}">
                                                            <th class='tdpadding' scope="col">IP地址
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == 'IT资产'}">
                                                            <th class='tdpadding' scope="col">加入域
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == 'IT资产'}">
                                                            <th class='tdpadding' scope="col">操作系统
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == 'IT资产'}">
                                                            <th class='tdpadding' scope="col">应用系统
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == 'IT资产'}">
                                                            <th class='tdpadding' scope="col">功能描述
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == 'IT资产'}">
                                                            <th class='tdpadding' scope="col">数据库
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == '机器设备'}">
                                                            <th class='tdpadding' scope="col">车间类型
                                                            </th>
                                                        </c:if>
                                                        <c:if test="${receiveGoodsTemp.assetsType == '行政资产'}">
                                                            <th class='tdpadding' scope="col">是否为房屋建筑物
                                                            </th>
                                                        </c:if>


                                                        <!-- 资产原值=单价（含税） -->
                                                        <!-- 资产原值*净残值率 = 净残值 -->
                                                        <!-- 资产原值 - 净残值 = 资产净值 -->
                                                    </tr>
                                                    <tbody id="tbody_add_detail">
                                                    </tbody>
                                                </table>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>

                    </tbody>
                </table>

            </form>
        </div>
        </br>
        <div style="width:90%; margin: 0 auto;">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tbody>
                <tr>
                    <td class="titlefontsize" align="left" style="width: 150px">
                        <div class="imgtitle"></div>
                        收货附件
                    </td>
                    <%--<td>
                        <input type="file" name="multipartFile" id="fileToUpload2" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                        <input type="submit" name="btnUpload" value="上传" id="btnUpload2" class="btn">
                        <input type="submit" name="btnDelFile" value="删除" id="btnDelFile2" class="btn">
                    </td>--%>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="divGrid">
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
                    </td>
                </tr>
                </tbody>
            </table>

            <table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
                <tbody>
                <tr>
                    <td class="titlefontsize" align="left" style="width: 150px">
                        <div class="imgtitle"></div>
                        验收附件
                    </td>
                    <td>
                        <input type="file" name="multipartFile" id="fileToUpload1" class="colorblur"
                               onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                        <input type="submit" name="btnUpload" value="上传" id="btnUpload1" class="btn">
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
            <form id='machineForm'>
                <table id="addMachineBody" border="0" cellpadding="3px" cellspacing="0" width="90%">
                    <tbody id="machineBody">
                    </tbody>
                </table>
            </form>
        </div>


    </div>
</div>
<!-- 新增验收单--e-->

<!-- 需求明细展示page2--s-->
<div id="addDemandDetailWindow" name="addDemandDetailWindow" class="easyui-window" title="需求详情展示"
     data-options="iconCls:'icon-save'" style="width:900px;height:400px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <div id="Div3" style="margin-left: 0px; margin-top: 0px;">
                <input type="button" name="" value="确定" id="savebtnDemandDetail" class="btn">
                <input type="button" id="btnDemandCancel" value="返回" class="btn">
            </div>
            <div style="margin-top: 10px">
                <table id="addReceiveGoodsDetailTable2" cellpadding="0px" cellspacing="0" class="tablenoborder">
                    <tbody>
                    <tr>
                        <td colspan="4">
                            <form action="addDemandDetail" id="addDemandDetailForm">
                                <table id="Table16" width="100%" border="0" cellpadding="0" cellspacing="0"
                                       style="table-layout: fixed;">
                                    <tbody>
                                    <tr>
                                        <td class="tdtitle" style="text-align: right" colspan="2">
                                            <div id="div12" class="divGrid"
                                                 style="overflow-x: scroll; overflow-y: visible;">
                                                <table id="table_add_detail2" class="adminlist" cellspacing="0"
                                                       rules="all" border="1"
                                                       style="border-collapse:collapse;">
                                                    <tr style="white-space: nowrap;">
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>需求单号&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>资产类型&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>资产名称&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>收货数量&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>已验收数量&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>本次验收数量&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>型号规格/品牌&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>功能&nbsp;&nbsp;
                                                        </th>
                                                    </tr>
                                                    <tbody id="tbody_demand_detail">
                                                    </tbody>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<div id="storageLocationWindow" name="" class="easyui-window" title="选择存放位置" data-options="iconCls:'icon-save'"
     style="width:750px;height:380px;overflow:scroll;padding:5px;" closed="true">
        <div  style="padding:10px;">
            <input id="isAllOrOnly" type="hidden" value="">
            <form id="storageLocationForm">
                <table class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="0">
                    <tr style="text-align: left">
                        <!-- 				<td >办公地点:</td> -->
                        <!-- 				<td name=""><input type="text" id="searchStorageLocationCode" name="supplierCode"></td> -->
                        <td>存放位置:</td>
                        <td><input name="" id="searchStorageLocationName" type="text"></td>
                    </tr>
                </table>
            </form>
            <input type="button" value="查询" id="searchStorageLocation"><input type="button" id="sureStorageLocation"
                                                                              value="确定">
                <table id="supplierBudget" class="adminlist" rules="all"
                       style="border-collapse:collapse;height:380px;width:720px;" cellspacing="0"
                       border="1" cellpadding="6px">
                    <thead>
                    <tr>
                        <th class='tdpadding'></th>
                        <th class='tdpadding'>存放位置</th>
                        <th class='tdpadding'>办公地点</th>

                    </tr>
                    </thead>
                    <tbody id="tbody_storageLocation"></tbody>
                </table>
        </div>

</div>
<!-- 需求详情展示--e-->
<!-- 上传照片 s-->
<div id="photoWindow" name="photoWindow" class="easyui-window" title="上传照片" data-options="iconCls:'icon-save'"
     style="width:800px;height:400px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <div style="margin-top: 10px">
                <form action="receiveGoodsHeader" id="receiveGoodsHeaderForm1">
                    <table id="addReceiveGoodsHeaderTable" border="0" cellpadding="3px" cellspacing="0" width="90%">
                        <tbody>
                        <tr>
                            <td colspan="4">
                                <input id="fileType" type="hidden">
                                <input id="fileToUpload" type="file" name="multipartFile"/>
                                <input type="button" id="photoSubmit" value="上传"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>

                <table id="addReceiveGoodsDetailTable3" cellpadding="0px" cellspacing="0" class="tablenoborder">
                    <tbody>
                    <tr>
                        <td colspan="4">
                            <form action="" id="photoForm">
                                <table id="Table11" width="100%" border="0" cellpadding="0" cellspacing="0"
                                       style="table-layout: fixed;">
                                    <tbody>
                                    <tr>
                                        <td class="tdtitle" style="text-align: right" colspan="2">
                                            <div id="div13" class="divGrid"
                                                 style="overflow-x: scroll; overflow-y: visible;">
                                                <table id="table_add_detail3" class="adminlist" cellspacing="0"
                                                       rules="all" border="1"
                                                       style="border-collapse:collapse;">
                                                    <tr style="white-space: nowrap;">
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>文件名称&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>上传人&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>上传编码&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>上传时间&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>下载文件&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>删除&nbsp;&nbsp;
                                                        </th>
                                                    </tr>
                                                    <tbody id="tbody_photo">
                                                    </tbody>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- 上传照片 e-->
<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
<script src="${staticURL}/Scripts/Purchase/CheckAcceptance/CheckAcceptance.js" type="text/javascript"></script>
<script type="text/javascript">
    var dg = frameElement.lhgDG;
    // 	var W = dg.curWin;// 获得父窗体

    function autoSize() {
        $("#gridview").width($(window).width());
        $("#gridview").height(
            $(window).height() - $("#Query").height()
            - $("#Button").height()
            - $("#AspNetPager").height());
    }
    autoSize();
    window.onresize = autoSize;

    var photodocument = null;
    var objdataTemp = null;
    var isManager = '${isManager}';
    var receiveGoodsDetaillistLength = 0;
    $("#assetsPhoto").live("click", function () { //上传弹框
        photodocument = $(this).parent().parent().find("#systeManagementCode").val();
        var top = "70px";//$(this).offset().top - 350;
        var left = "70px";//$(this).offset().left-180;
        $('#fileType').val("0");
        $('#photoWindow').window('open').window('resize', {width: '800px', height: '400px', top: top, left: left});
        photoAllShow(photodocument);

    });

    $("#otherCalibrationReport").live("click", function () { //上传弹框
        photodocument = $(this).parent().parent().find("#systeManagementCode").val();
        var top = "70px";//$(this).offset().top - 350;
        var left = "170px";//$(this).offset().left-180;
        $('#fileType').val("1");
        $('#photoWindow').window('open').window('resize', {width: '800px', height: '400px', top: top, left: left});
        alert(1);
        photoAllShow(photodocument);

    });
    function photoAllShow(photodocument) { //获得所有的照片
        var type = $('#fileType').val();
        $.ajax({
            data: {"document": photodocument},
            url: "${dynamicURL}/file/getFilesByDocument",
            dataType: "JSON",
            type: "post",
            success: function (data) {
                var objdataTemp = JSON.parse(data);

                var divHtml = "";
                for (var i = 0; i < objdataTemp.length; i++) {
                    if (objdataTemp[i].typeName == type) {
                        divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                        divHtml += "<td class='tdpadding'><input type='text' id='fileName' value='" + objdataTemp[i].fileName + "'> </td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='' value='" + objdataTemp[i].createUserName + "'> </td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='' value='" + objdataTemp[i].createBy + "'> </td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='' value='" + objdataTemp[i].createDate + "'><input type='hidden' name='fileuuid' id='fileuuid' value='" + objdataTemp[i].fileuuid + "'> </td>";
                        divHtml += '</td><td class="tdpadding"><a href="' + '${dynamicURL}/file/downloadFile?fileuuid=' + objdataTemp[i].fileuuid + '">下载</a></td>';
                        divHtml += "<td class='tdpadding'><input type='button' name='' id='delphoto' value='删除' > </td>";
                        divHtml += "</tr>";
                    }
                }
                $("#tbody_photo").html(divHtml);
            }
        });
    }

    $("#photoSubmit").click( // 点击上传
        function () {
            var createBy = $('#buyerCode').val();
            var createUserName = '${loginUserName}';
            var typeName = $('#fileType').val();
            //console.log(photodocument);
            $.ajaxFileUpload({
                url: '${dynamicURL}/file/uploadFile',
                secureuri: false,
                fileElementId: 'fileToUpload',//file标签的id
                dataType: 'json',//返回数据的类型
                data: {
                    'createBy': createBy,
                    'createUserName': createUserName,
                    'document': photodocument,
                    'typeName': typeName
                },//一同上传的数据
                success: function (data) {
                    // console.log(data);
                    photoAllShow(photodocument);
                },
                error: function () {
                }
            });
        }
    );
    $('#delphoto').live("click", function () {
        var fileuuid = $(this).parent().parent().find('#fileuuid').val();
// 	var photodocument =   $(this).parent().parent().find('#fileName').val();
        var list = new Array();
        list.push(fileuuid);
        $.ajax({
            type: "post",
            url: "${dynamicURL}/file/deleteFile",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify({"documents": list}),
            success: function (data) {
                photoAllShow(photodocument);
                //console.log(data);
// 	      $('#photoWindow').panel('open').panel('refresh');

            }
        });
    });


    /* 上传照片 e*/
    //     页面加载完后将需求明细全部获得
    // var purchaseApplyMappinglist = {};
    var purchaseApplyMappinglist = null;
    $(document).ready(function () {
        var receiveGoodsId = $('#receiveGoodsIdTemp').val();
        //searchPurchaseApplyMappingList(receiveGoodsId);
    });
    function searchPurchaseApplyMappingList(receiveGoodsId, receiveGoodsDetailId) {
        if (null == receiveGoodsDetailId || "" == receiveGoodsDetailId) {
            $("input[name='gvDataList$ctl02$chkBolDel']:checked").each(function () {
                receiveGoodsDetailId = $(this).parent().parent().find("td input[name='linereceiveGoodsDetailId']").val();
            });
        }
        $.ajax({
            type: "POST",
            async: false,
            url: "${dynamicURL}/checkAccept/api/searchAllDemandDetailIdByReceiveGoodsId",
            data: {"receiveGoodsId": receiveGoodsId, "receiveGoodsDetailId": receiveGoodsDetailId},
            success: function (data) {
                purchaseApplyMappinglist = data;//表TB_FA_RECEIVE_CHECK_MAPPING中数据
                $("#brand").val(purchaseApplyMappinglist[0].brand);
            }
        });
    }
    var $thisInput;
    /* page2需求详情展示 -- 修改本次验收数量弹窗*/
    $('#lineThisAcceptCount').live('click', function () {
        $thisInput = $(this);
//     	alert($thisInput.val());
        searchPurchaseApplyMappingList($('#receiveGoodsIdTemp').val(), $(this).parent().parent().find('#linereceiveGoodsDetailId').val());
        var top = "50px";//$(this).offset().top - 350;
        var left = "70px";//$(this).offset().left-180;
        $('#addDemandDetailWindow').window('open').window('resize', {
            width: '900px',
            height: '400px',
            top: top,
            left: left
        });
        var lineapplyDetailId = $(this).parent().parent().find('#lineapplyDetailId').val();

        var divHtml = "";

        for (var i = 0; i < purchaseApplyMappinglist.length; i++) {
            if (lineapplyDetailId == purchaseApplyMappinglist[i].applyDetailId) { //page2  修改本次验收数量
                divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                divHtml += "<td class='tdpadding'><input type='text' size='14' name='demandId' value='" + purchaseApplyMappinglist[i].demandId + "'></td>";
                divHtml += "<td class='tdpadding'><input type='text' name='assetsType' size='6' readonly='readonly' value='" + purchaseApplyMappinglist[i].assetsType + "'>" +
                    "<input type='hidden' name='receiveGoodsDetailId' value='" + purchaseApplyMappinglist[i].receiveGoodsDetailId + "' title='receiveGoodsDetailId'>" +
                    "<input type='hidden' name='purchaseId' id='' value='" + purchaseApplyMappinglist[i].purchaseId + "' title='purchaseId'></td>";
                divHtml += "<td class='tdpadding'><input type='text' name='assetsName' size='12' readonly='readonly' value='" + purchaseApplyMappinglist[i].assetsName + "'>" +

                    "<input type='hidden' name='demandDetailId' id='demandDetailIdTemp' value='" + purchaseApplyMappinglist[i].demandDetailId + "' title='demandDetailId'> " +
                    "<input type='hidden' name='applyDetailId' id='applyDetailIdTemp'  value='" + purchaseApplyMappinglist[i].applyDetailId + "' title='applyDetailId'></td>";
                /*已经收货的数量*/
                divHtml += "<td class='tdpadding'><input type='text' size='6'  name='goodsCountDemand' readonly='readonly' id='' value='" + purchaseApplyMappinglist[i].goodsCountDemand + "' ></td>";
                /*已经验收的数量*/
                divHtml += "<td class='tdpadding'><input type='text'  size='8' name='checkCountDemand' readonly='readonly' id='' value='" + purchaseApplyMappinglist[i].checkCountDemand + "' readonly='readonly'></td>";
                /*本次验收数量*/
                divHtml += "<td class='tdpadding'><input type='text'  size='10' name='thisCheckCountDemand' id='' value='" + purchaseApplyMappinglist[i].thisCheckCountDemand + "' ></td>";
                divHtml += "<td class='tdpadding'><input type='text' name='brand'  size='8' id='brand' readonly='readonly' value='" + purchaseApplyMappinglist[i].brand + "' ></td>";
                divHtml += "<td class='tdpadding'><input type='text' name='functions'  size='6' readonly='readonly' id='functions' value='" + purchaseApplyMappinglist[i].functions + "' ></td>";
                divHtml += "</tr>";
            }
            $("#tbody_demand_detail").html(divHtml);
        }
    })

    /* page2--确定按钮 需求 确定 保存 */
    var demandTemp = {};
    $('#savebtnDemandDetail').click(function () {

        var flag = true;
        //修改收货数量不能大于订单需求数量
        $("input[name='thisCheckCountDemand']").each(function () {
            var nowNum = $(this).val();
            var applyCountDemand = $(this).parent().parent().find("input[name='goodsCountDemand']").val();
            if (nowNum - applyCountDemand > 0) {
                flag = false;
            }
        });
        if (!flag) {
            alert('收货数量不能大于申请数量');
            return;
        }
        var objDemandDetail = {
            assetsName: "",
            assetsType: "",
            goodsCountDemand: "",
            checkCountDemand: "",
            thisCheckCountDemand: "",
            projectCode: "",
            brand: "",
            receiveGoodsId: "",
            applyDetailId: "",
            demandDetailId: "",
            purchaseDetailId: "",
            receiveGoodsDetailId: ""
        }
        demandTemp = constructObjectFromTable(objDemandDetail, $('#addDemandDetailForm .bodyTr'));

        var thisCheckCountTemp = 0;
        for (var i = 0; i < purchaseApplyMappinglist.length; i++) {
            for (var j = 0; j < demandTemp.length; j++) {
                if (purchaseApplyMappinglist[i].demandDetailId == demandTemp[j].demandDetailId) {
                    purchaseApplyMappinglist[i].thisCheckCountDemand = demandTemp[j].thisCheckCountDemand;
                    thisCheckCountTemp += parseInt(demandTemp[j].thisCheckCountDemand);
                }
            }
        }

        $thisInput.val(thisCheckCountTemp);
        $('#addDemandDetailWindow').window('close');
    })

    // 表单封装Json 方法
    function constructObjectFromTable(object, $object) {
        var javaObjArray = [];
        $object.each(function (i) {
            var $ck = $(this).find('input[name^=gvdata]');
            if ($ck.length != 0) {
                if (!$ck.attr('checked')) {
                    return true;
                }
            }
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
    ;

    //	日期格式化方法
    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
    }
    /*function isEmptyObject(obj) {
     for (var key in obj) {
     return false;
     }
     return true;
     }*/
    /* page1-确定按钮---新增收货单弹框,打开page3 */
    $('#btnAddCheckWindow').click(function () {
        var linepurchaseDetailId1 = $('input[name = gvDataList$ctl02$chkBolDel]:checked').parent().parent().find('#linepurchaseDetailId').val();
        if (null == purchaseApplyMappinglist || purchaseApplyMappinglist[0].purchaseDetailId != linepurchaseDetailId1) { //如果对象是空的，就给它赋值，（当修改验收数量时，会对这个对象赋值，如果不修改，则在此处修改）
            var id = $('#receiveGoodsIdTemp').val();
            searchPurchaseApplyMappingList(id, "");
        }
//        console.log(purchaseApplyMappinglist);
        var $checkLine = $("input[name='gvDataList$ctl02$chkBolDel']:checked");
        var length = $checkLine.length;
        if (length != 1) {
            alert("仅能选中一条数据处理");
            return;
        }
        var lineGoodsCount;
        var lineCheckCount;
        var lineThisAcceptCount;
        var numFlag;
        $checkLine.each(function (index, item) {
            lineGoodsCount = $(this).parent().parent().find("td input[name='lineGoodsCount']").val(); //已收货数量
            lineCheckCount = $(this).parent().parent().find("td input[name='lineCheckCount']").val(); //已验收数量
            lineThisAcceptCount = $(this).parent().parent().find("td input[name='lineThisAcceptCount']").val(); //本次验收数量
            numFlag = lineGoodsCount - lineCheckCount - lineThisAcceptCount;
        });
        if (numFlag < 0 || lineThisAcceptCount == 0) {
            alert("本次验收不能为0并且验收总数不能超过收货总数");
            return;
        }
        var top = "50px";//$(this).offset().top - 350;
        var left = "70px";//$(this).offset().left-180;
        $('#addCheckWindow').window('open').window('resize', {width: '1000px', height: '800px', top: top, left: left});
        var receiveGoodsId = $('#receiveGoodsIdTemp').val(); //收货主表ID


        var linereceiveGoodsDetailId = $('input[name = gvDataList$ctl02$chkBolDel]:checked').parent().parent().find('#linereceiveGoodsDetailId').val();
        var lineapplyDetailId = $('input[name = gvDataList$ctl02$chkBolDel]:checked').parent().parent().find('#lineapplyDetailId').val();
        var linepurchaseDetailId = $('input[name = gvDataList$ctl02$chkBolDel]:checked').parent().parent().find('#linepurchaseDetailId').val();
        var lineThisAcceptCountTemp = $('input[name = gvDataList$ctl02$chkBolDel]:checked').parent().parent().find('#lineThisAcceptCount').val();
        var lineGoodsCountTemp = $('input[name = gvDataList$ctl02$chkBolDel]:checked').parent().parent().find('#lineGoodsCount').val();
        var lineCheckCountTemp = $('input[name = gvDataList$ctl02$chkBolDel]:checked').parent().parent().find('#lineCheckCount').val();

        $('#checkCount').val(lineCheckCountTemp);
        $('#goodsCount').val(lineGoodsCountTemp);
        $('#thisCheckCount').val(lineThisAcceptCountTemp);
        // alert(linereceiveGoodsDetailId);

        $.ajax({
            type: "POST",
            url: "${dynamicURL}/checkAccept/api/addReceiveGoods",
            data: {
                "receiveGoodsId": receiveGoodsId,
                "receiveGoodsDetailId": linereceiveGoodsDetailId,
                "applyDetailId": lineapplyDetailId
            },
            success: function (data) {
                // 获得验收头 对象
                var checkAcceptanceMapping = data[0];
                // 向验收头里填充数据
                $('#buyerName').val(checkAcceptanceMapping.buyerName);
                $('#buyerCode').val(checkAcceptanceMapping.buyerCode);
                $('#purchaseId').val(checkAcceptanceMapping.purchaseId);
                $('#purchaseDetailId').val(linepurchaseDetailId);
                $('#applyDetailId').val(lineapplyDetailId);

                $('#orderType').val(checkAcceptanceMapping.orderType);
                $('#receiveGoodsDetailId').val(linereceiveGoodsDetailId);
                $('#receiveGoodsId').val(checkAcceptanceMapping.receiveGoodsId);
                $('#supplierName').val(checkAcceptanceMapping.supplierName);
                $('#officeLocation').val(checkAcceptanceMapping.officeLocation);
                $('#supplierCode').val(checkAcceptanceMapping.supplierCode);
                $('#assetsType').val(checkAcceptanceMapping.assetsType);
                var receiveDateFormat = formatDate(checkAcceptanceMapping.receiveDate);
                $('#receiveDate').val(receiveDateFormat); // 到货日期
                $('#contractNo').val(checkAcceptanceMapping.contractNo);

                $('#applyDepartment').val(checkAcceptanceMapping.applyDepartment);
                $('#applyDepartmentCode').val(checkAcceptanceMapping.applyDepartmentCode);
                /*$('#budgetDepartmentName').val(checkAcceptanceMapping.budgetDepartmentName); 会造成重复数据，可能来自不同需求部门
                 $('#budgetDepartmentCode').val(checkAcceptanceMapping.budgetDepartmentCode);*/
                $('#costCenter').val(checkAcceptanceMapping.costCenter);
                $('#costCenterCode').val(checkAcceptanceMapping.costCenterCode);
                $('#computerCode').val(checkAcceptanceMapping.computerCode);
                $('#computerName').val(checkAcceptanceMapping.computerName);
                $('#contractNo').val(checkAcceptanceMapping.contractNo);

                $('#checkAcceptId').val(checkAcceptanceMapping.checkAcceptId);
//                $('#recWarrantyPeriod').val(checkAcceptanceMapping.recWarrantyPeriod);
                $('#goodsCount').val(checkAcceptanceMapping.goodsCount);
                $('#checkCount').val(checkAcceptanceMapping.checkCount);
//                $('#ledgerAccount').val(checkAcceptanceMapping.ledgerAccount);
                //$('#brand').val(checkAcceptanceMapping.brand);这里是从收货详情表中获取的，获取不到，需要从TB_FA_RECEIVE_CHECK_MAPPING表中去获取
                $('#depreciation').val(checkAcceptanceMapping.depreciation);
                $('#units').val(checkAcceptanceMapping.units);
//                $('#storageLocation').val(checkAcceptanceMapping.storageLocation);

                // 获得验收行 对象
                var receiveGoodsDetaillist = data[1];
                // receiveGoodsDetaillistLength = receiveGoodsDetaillist.length;
                var lineapplyDetailId = $('input[name = gvDataList$ctl02$chkBolDel]:checked').parent().parent().find('#lineapplyDetailId').val();

                var divHtml = "";
                var readonly = "readonly";
                var required2 = "";
                if ("Y" == isManager) {
                    readonly = "";
                    required2 = " required='required' "
                }
                var required = " ";
                if ("计量器具" == $('#assetsTypeName').val() && "Y" == isManager) { //计量器具并且是归口工程师时
                    required = " required='required' "
                }
                /*var required1 ="";
                 if ("计量器具" == $('#assetsTypeName').val()){
                 required1 = " required='required' "
                 }*/
                var serialNum = 0;
                for (var j = 0; j < purchaseApplyMappinglist.length; j++) { // 循环拿到本次验收对应的需求行和订单行
                    if (purchaseApplyMappinglist[j].applyDetailId == lineapplyDetailId) { //显示选定的订单行
                        var numTemp = 0;
                        for (var i = 0; i < receiveGoodsDetaillist.length; i++) {// 循环所有的sap次级编码
                            if (purchaseApplyMappinglist[j].demandDetailId == receiveGoodsDetaillist[i].demandDetailId) {
                                numTemp += (i + 1) / (i + 1);
                                if ((numTemp + 1) / (parseInt(purchaseApplyMappinglist[j].thisCheckCountDemand) + 1) <= 1) {
                                    serialNum++;
                                    receiveGoodsDetaillistLength++;
                                    divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                                    divHtml += "<td class='tdpadding'>" + serialNum +
                                        "<input type='hidden' name='receiveGoodsId' id='receiveGoodsId' value='" + receiveGoodsDetaillist[i].receiveGoodsId + "' title='receiveGoodsId' >" +
                                        "<input type='hidden' name='receiveGoodsDetailId' id='receiveGoodsDetailId' value='" + linereceiveGoodsDetailId + "' title='receiveGoodsDetailId' >" +
                                        "<input type='hidden' name='purchaseId' id='purchaseId' value='" + receiveGoodsDetaillist[i].purchaseId + "' title='purchaseId'>" +
                                        "<input type='hidden' name='purchaseDetailId' id='purchaseDetailId' value='" + receiveGoodsDetaillist[i].purchaseDetailId + "' title='purchaseDetailId' >" +
                                        "<input type='hidden' name='applyDetailId' id='applyDetailId' value='" + receiveGoodsDetaillist[i].applyDetailId + "' title='applyDetailId' >" +
                                        "<input type='hidden' name='cardId' id='cardId' value='" + receiveGoodsDetaillist[i].cardId + "' title='cardId' >" +
                                        "<input type='hidden' name='assetDetailId' id='assetDetailId' value='" + receiveGoodsDetaillist[i].assetDetailId + "' title='assetDetailId' >" +
                                        "<input type='hidden' name='applyId' id='applyId' value='" + receiveGoodsDetaillist[i].applyId + "' title='applyId' ></td>";
                                    divHtml += "<td class='tdpadding'>" + '<a href="javascript:showDemandOrder(\'' + receiveGoodsDetaillist[i].demandId + '\')" >' + receiveGoodsDetaillist[i].demandId + '</a>' +
                                        "<input type='hidden' size='13'  required='required' name='demandId' readonly id='demandId' value='" + receiveGoodsDetaillist[i].demandId + "'><input type='hidden' name='demandDetailId' id='demandDetailId' value='" + receiveGoodsDetaillist[i].demandDetailId + "'  title='demandDetailId'></td>";
                                    divHtml += "<td class='tdpadding'><input type='text' size='10'  required='required' name='assetsName' readonly id='assetsName' value='" + receiveGoodsDetaillist[i].assetsName + "'  ></td>";
                                    divHtml += "<td class='tdpadding'><input type='text'  size='12' " + required + " " + readonly + " name='putUnderManageCode' id='putUnderManageCode' ></td>";
                                    divHtml += "<td class='tdpadding'><input type='text' size='14' " + required2 + readonly + " name='serialNumber' id='serialNumber'  ></td>";
                                    divHtml += "<td class='tdpadding'><input type='button'   name='assetsPhoto' id='assetsPhoto' value='上传/查看' ></td>";
                                    if ("计量器具" == $('#assetsTypeName').val()) { //是否有第三方校验报告
                                        divHtml += "<td class='tdpadding' align = center >" +
                                            "<select value='' " + required + " name='isThirdReport' onchange='change2(this);' id='isThirdReport'><option value=''>请选择</option><option value='0'>是</option><option selected='selected'value='1'>否</option></select>" +
                                            "<input type='button' name='otherCalibrationReport' hidden='false' id='otherCalibrationReport' value='上传/查看' ></td>";
                                    }
                                    divHtml += "<td class='tdpadding'><input type='text' size='4'  required='required' name='recWarrantyPeriod' readonly id='recWarrantyPeriod' value='" + receiveGoodsDetaillist[i].recWarrantyPeriod + "'  ></td>";
                                    divHtml += "<td class='tdpadding'><input type='text'  size='12' required='required' name='storageLocation' readonly id='storageLocationLine' value='" + receiveGoodsDetaillist[i].storageLocation + "'  >" +
                                        "<input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='cleanDep(this);'>" +
                                        "<input id='storageLocationShow' class='btnRefView' title='点击打开参照文本框内容' type='button'  value='...'></td>";
                                    divHtml += "<td style='display: none'><input type='text' size='18'   name='mmAssetsCode' readonly id='mmAssetsCode' value='" + receiveGoodsDetaillist[i].mmAssetsCode + "' ></td>";
                                    divHtml += "<td style='display: none'><input type='text' size='18'   name='systeManagementCode' readonly id='systeManagementCode' value='" + receiveGoodsDetaillist[i].systeManagementCode + "' ></td>";
                                    divHtml += "<td class='tdpadding'><input type='text' size='15'  required='required' name='sapAssetsCode' readonly id='sapAssetsCode' value='" + receiveGoodsDetaillist[i].sapAssetsCode + "'  ></td>";
//                                    divHtml += "<td class='tdpadding'><input type='text' name='pasteAssetsTag' id='pasteAssetsTag' ></td>";是否粘贴标签
                                    if ("计量器具" == $('#assetsTypeName').val()) { //生产厂家
                                        divHtml += "<td class='tdpadding' align = center ><input " + required + " type='text' name='manufactureFactory' id='manufactureFactory' value='' ></td>";
                                    }
                                    if ("计量器具" == $('#assetsTypeName').val()) { //管理级别
                                        divHtml += "<td class='tdpadding' align = center >" +
                                            "<select name='managementLevel' " + required + " id='managementLevel'>" +
                                            "<option value='' >请选择</option> " +
                                            "<option value='A' >A</option> " +
                                            "<option value='B' >B</option> " +
                                            "<option value='C' >C</option> " +
                                            "</select></td>";
                                    }
                                    if ("计量器具" == $('#assetsTypeName').val()) { //精度等级
                                        divHtml += "<td class='tdpadding' align = center ><input " + required + " type='text' name='accuracyClass' id='accuracyClass' value='' ></td>";
                                    }
                                    if ("计量器具" == $('#assetsTypeName').val()) { //检定周期（日）
                                        divHtml += "<td class='tdpadding' align = center ><input " + required + " type='text' onchange='change1(this);' name='verificationCycle' id='verificationCycle' value='' ></td>";
                                    }
                                    if ("计量器具" == $('#assetsTypeName').val()) { //校准日期
                                        divHtml += "<td class='tdpadding' align = center ><input " + required + " name='calibrationDate' value=''  type='text' onchange='change(this);' id='calibrationDate' class='Wdate' onclick='WdatePicker({skin:" + '"' + "ext" + '"' + "});'></td>";
                                    }
                                    if ("计量器具" == $('#assetsTypeName').val()) { //下次校准日期
                                        divHtml += "<td class='tdpadding' align = center ><input " + required + " name='nextCalibrationDate' value='' type='text' id='nextCalibrationDate' disabled='true'></td>";
                                    }
                                    if ("计量器具" == $('#assetsTypeName').val()) { //校准单位
                                        divHtml += "<td class='tdpadding' align = center ><input " + required + " type='text' name='calibrationUnit' id='calibrationUnit' value='' ></td>";
                                    }


                                    if ("IT资产" == $('#assetsTypeName').val()) { //CPU
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' name='cpu' id='cpu' value='' ></td>";
                                    }
                                    if ("IT资产" == $('#assetsTypeName').val()) { //内容
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' name='internalMemory' id='internalMemory' value='' ></td>";
                                    }
                                    if ("IT资产" == $('#assetsTypeName').val()) { //硬盘
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' name='hardpan' id='hardpan' value='' ></td>";
                                    }
                                    if ("IT资产" == $('#assetsTypeName').val()) { //ip地址
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' name='ipAddress' id='ipAddress' value='' ></td>";
                                    }
                                    if ("IT资产" == $('#assetsTypeName').val()) { //加入域
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' name='joinDomain' id='joinDomain' value='' ></td>";
                                    }
                                    if ("IT资产" == $('#assetsTypeName').val()) { //操作系统
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' name='operatingSystem' id='operatingSystem' value='' ></td>";
                                    }
                                    if ("IT资产" == $('#assetsTypeName').val()) { //应用系统
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' name='utilitySystem' id='utilitySystem' value='' ></td>";
                                    }
                                    if ("IT资产" == $('#assetsTypeName').val()) { //功能描述
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' name='description' id='description' value='' ></td>";
                                    }
                                    if ("IT资产" == $('#assetsTypeName').val()) { //数据库
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' name='dataBase' id='dataBase' value='' ></td>";
                                    }
                                    if ("机器设备" == $('#assetsTypeName').val()) { //车间类型
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' name='shopType' id='shopType' value='" + receiveGoodsDetaillist[i].shopType + "' ></td>";
                                    }
                                    if ("行政资产" == $('#assetsTypeName').val()) { //是否为房屋建筑,采购申请中带出,不可修改
                                        divHtml += "<td class='tdpadding' align = center ><input type='text' readonly name='isBuildingContruction' id='isBuildingContruction' value='" + receiveGoodsDetaillist[i].isBuildingContruction + "' ></td>";
                                    }

                                    divHtml += "</tr>";
                                }
                            }
                        }
                    }
                }
                $("#tbody_add_detail").html(divHtml);
            }
        })

        loadingMachineBody();

    });
    //page3中非空验证
    function checkNull() {

        var $input = $("#tbody_add_detail").find("input[required='required']");
        var $select = $("#tbody_add_detail").find("select[required='required']");
        var flag = 0;
        var antiqueTypes = [];
        $input.each(function (index, item) {
            var type = $(item).val();
            antiqueTypes.push(type);
        });
        $select.each(function (index, item) {
            var type1 = $(item).val();
            antiqueTypes.push(type1);
        });
        $.each(antiqueTypes, function (index, item) {
            if (item.length == 0) {
                flag += 1;
            }
        });
        return flag;
    }

    function change(that) {
        //alert($(that).val());
        var days = $(that).parent().prev().children().val();
        var str = $(that).val(); // 2017-02-01
        var str2 = getNewDay(str, days);
        $(that).parent().next().children().val(str2);

    }
    function change1(that) {
        //alert($(that).val());

        var days = $(that).val();
        var str = $(that).parent().next().children().val(); // 2017-02-01
        var str2 = getNewDay(str, days);
        if (null != str && '' != str) {
            $(that).parent().next().next().children().val(str2);
        }
    }
    function change2(that) {
        if ($(that).val() == '1') {
            $(that).next().attr("style", "display: none;");
        }
        if ($(that).val() == '0') {
            $(that).next().attr("style", "display: inline;");
        }
    }
    function getNewDay(dateTemp, days) {
        var dateTemp = dateTemp.split("-");
        var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]);//转换为MM-DD-YYYY格式????
        var millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);
        var rDate = new Date(millSeconds);
        var year = rDate.getFullYear();
        var month = rDate.getMonth() + 1;
        if (month < 10) month = "0" + month;
        var date = rDate.getDate();
        if (date < 10) date = "0" + date;
        return (year + "-" + month + "-" + date);
    }
    function btnsaveCheck(num) { // 提交
        var locationFlag = false;
        $("input[name='storageLocation']").each(function () {
            if ($(this).val() == "") {
                locationFlag = true;
                return;
            }
        });
        if (locationFlag) {
            alert("存放位置不能为空");
            return;
        }
        if (receiveGoodsDetaillistLength == 0) {
            alert("没有明细行，不能提交");
            return;
        }
        if ("计量器具" == $('#assetsTypeName').val()) {
            if ($("#managementLevel").val() == '') {
                alert("请选择管理级别");
                return;
            }
            if ($("#isThirdReport").val() == '') {
                alert("请选择是否有第三方检验报告");
                return;
            }
        }

        var flag = checkNull();
        if (flag > 0) {
            alert("请输入必填项");
            return;
        }

        showAgreeDialog();
    }

    function showAgreeDialog() {
        var url = "${dynamicURL}/workflow/showAgreeDialog?parentWindowId=searchTempReceiveGoodsByReceiveGoodsId";
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
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
        $('#idea').val(idea);

        var checkAcceptanceWrapper = {};
        // 	头
        var objHeader = $('#receiveGoodsHeaderForm').serializeObject();
        objHeader.isMail=isMail;
        objHeader.isPhoneMessage=isPhoneMessage;
        objHeader.idea=idea;
        checkAcceptanceWrapper.checkAcceptance = objHeader;

        // 	行
        var objCheckDetail = {
            recWarrantyPeriod: "", //保修周期
            storageLocation: "", //存放位置
            receiveGoodsId: "", // 收货单号
            receiveGoodsDetailId: "",//
            purchaseId: "",//
            applyId: "",//
            purchaseDetailId: "",//
            applyDetailId: "", //
            demandId: "", //
            demandDetailId: "", //
            cardId: "",
            assetDetailId: "",
            checkAcceptId: "", // 验收单据号
            assetsName: "", //资产名称
            assetsType: "", //资产分类
            mmAssetsCode: "", //固定资产(字段编码)MM+sap主编码+sap次级编码
            systeManagementCode: "",
            sapAssetsCode: "", // SAP资产编码
//            manageapplyDepartmentCode: "",// 归口管理部门编码
            serialNumber: "", // 序列号
            manufactureFactory: "", //生产厂家
            managementLevel: "", //管理级别
            accuracyClass: "", //精度等级
            verificationCycle: "", //检定周期
            calibrationDate: "", //校准日期


            pasteAssetsTag: "", // 是否有粘贴资产标签
            assetsPhoto: "", // 资产照片
            otherCalibrationReport: "",//是否有第三方校验报告
            nextCalibrationDate: "", ////下次校准日期
            calibrationUnit: "", //校准单位
            isThirdReport: "", //是否有第三方检验报告
            reportUpload: "", //第三方检验报告上传
            putUnderManageCode: "", //归口管理编码
            cpu: "", //CPU
            internalMemory: "", //内存：internalMemory
            hardpan: "", //硬盘：HARDPAN
            ipAddress: "",//IP地址：IP_ADDRESS
            joinDomain: "",//加入域：JOIN_DOMAIN
            description: "",//功能描述：DESCRIPTION
            utilitySystem: "",//应用系统：UTILITY_SYSTEM
            dataBase: "", //数据库：DATABASE
            operatingSystem: "", //操作系统：OPERATING_SYSTEM
//            machineCode :"", //机器编码
            shopType: "", //车间类型
//            managementCode :"", //管理编码
            isBuildingContruction: "" //是否为房屋建筑
        }
        var objArrCheckAcceptanceDetail = constructObjectFromTable(objCheckDetail, $('#receiveGoodsDetailForm .bodyTr'));
        for (var i = 0; i < objArrCheckAcceptanceDetail.length; i++) {
            objArrCheckAcceptanceDetail[i].checkAcceptId = $('#checkAcceptId').val();
        }
        checkAcceptanceWrapper.checkAcceptanceDetails = objArrCheckAcceptanceDetail;

        // 需求明细,在修改验收数量时弹出的页面，就是purchaseApplyMappinglist的数据
        for (var i = 0; i < purchaseApplyMappinglist.length; i++) {
            purchaseApplyMappinglist[i].checkAcceptId = $('#checkAcceptId').val();
        }
        checkAcceptanceWrapper.checkAcceptMappings = purchaseApplyMappinglist;
        var checkMachineObj = $('#machineForm').serializeObject();
        checkAcceptanceWrapper.checkMachine = checkMachineObj;
        $("#btnsaveCheck").val("提交ying");
        $("#btnsaveCheck").attr("disabled", true);
        $("#btnSaveCheckCancel").attr("disabled", true);
        $.ajax({
            type: "POST",
            dataType: "json",
            cache: false,
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/checkAccept/api/saveCheckAccept",
            data: JSON.stringify(checkAcceptanceWrapper),
            success: function (data) {
                if (data.errorMessages.length > 0) {
                    var str = data.errorMessages;
                    alert(str);

                    var reg = new RegExp("^.*保存成功.*$");
                    if (reg.test(str)) {

                        dg.curWin.location=dg.curWin.location;
                    }
                    $("#btnsaveCheck").val("提交");
                    $("#btnsaveCheck").attr("disabled", false);
                    $("#btnSaveCheckCancel").attr("disabled", false);
                    //dg.curWin.location.reload();
                    dg.curWin.location = dg.curWin.location;
                    return;
                } else {
                    alert(data.successMessage);

                    $("#btnsaveCheck").val("提交");
                    $("#btnsaveCheck").attr("disabled", false);
                    $("#btnSaveCheckCancel").attr("disabled", false);
                    dg.cancel();
                    dg.curWin.location = dg.curWin.location;
                    return;
                }
            }
        });
    }

    $('#btnDemandCancel').click(function () {
        $('#addDemandDetailWindow').window('close');
    });
    $('#btnSaveCheckCancel').click(function () {
        $('#addCheckWindow').window('close');
    });

    $(function () {
        loadingMachineBody();
        //alert($("select[name='machine9']").val());
    });
    function loadingMachineBody() {
        if ("机器设备" == $('#assetsTypeName').val()) { //车间类型
            var divHtml = '';
            divHtml += "<tr class='bodyTr1' >";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>1&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>设备资料是否齐全</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine1' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td>";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>9&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>设备运行参数是否与使用说明书完全符合</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine9' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td></tr>";
            divHtml += "<tr class='bodyTr1' >";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>2&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>规格型号是否与合同文件一致</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine2' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td>";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>10&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>各性能是否与安装及试使用期时一致</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine10' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td></tr>";
            divHtml += "<tr class='bodyTr1' >";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>3&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>所有电气是否张贴标签</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine3' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td>";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>11&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>配件数量是否与合同一致</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine11' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td></tr>";
            divHtml += "<tr class='bodyTr1' >";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>4&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>设备铭牌是否清晰可见</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine4' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td>";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>12&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>是否有安全隐患</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine12' value=''>" +
                "<option value='0'>是</option><option value='1' selected>否</option><option value='2'>不适用</option></select></td></tr>";
            divHtml += "<tr class='bodyTr1' >";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>5&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>安全隐患处是否张贴警示标示</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine5' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td>";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>13&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>提供的零配件清单是否与机台使用型号相同</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine13' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td></tr>";
            divHtml += "<tr class='bodyTr1' >";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>6&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>整机可否可以投入试运行</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine6' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td>";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>14&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>技术指标参照合同和技术要求进行验收，必要时需要添加附件</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine14' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td></tr>";
            divHtml += "<tr class='bodyTr1' >";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>7&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>设备是否进行过现场操作和维护保养培训。</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine7' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td>";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>15&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>生产需要提供设备试作报告</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine15' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td></tr>";
            divHtml += "<tr class='bodyTr1' >";
            divHtml += "<td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
            divHtml += "<td class='tdpadding'>8&nbsp;&nbsp;&nbsp;&nbsp;</td>";
            divHtml += "<td class='tdpadding'>各性能和配置是否与投标文件和技术要求一致</td>";
            divHtml += "<td class='tdpadding'>" +
                "<select name='machine8' value=''>" +
                "<option value='0'>是</option><option value='1'>否</option><option value='2'>不适用</option></select></td></tr>";

            $("#machineBody").html(divHtml);
        }
    }
    ;


    $('#btnUpload1').click(function () {
        var fileElementId = $(this).prev().attr('id');
        var file = $('#fileToUpload1').val();
        if (file == "") {
            alert("请选择需要上传的文件！");
            return false;
        }

        var documentNo = $('#checkAcceptId').val();
        // alert(documentNo);
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
            data: {'document': $("#receiveGoodsIdTemp").val()},
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
    /* 存放位置弹框 s*/
    var $inputStorageLocation = null;
    $('#storageLocationShow1').live("click", function () { //存放位置弹框
        $inputStorageLocation = $(this);
        $("#isAllOrOnly").val("0"); //代表全局弹窗
        var top = "50px";//$(this).offset().top - 350;
        var left = "70px";//$(this).offset().left-180;
        $('#storageLocationWindow').window('open').window('resize', {
            width: '900px',
            height: '700px',
            top: top,
            left: left
        });
        var officeLocationTemp = $('#officeLocation').val();
        showStorageLocation(officeLocationTemp);
    });
    $('#storageLocationShow').live("click", function () { //存放位置弹框
        $inputStorageLocation = $(this);
        $("#isAllOrOnly").val("1"); //代表单个设置存放位置
        var top = "50px";//$(this).offset().top - 350;
        var left = "70px";//$(this).offset().left-180;
        $('#storageLocationWindow').window('open').window('resize', {
            width: '900px',
            height: '700px',
            top: top,
            left: left
        });
        var officeLocationTemp = $('#officeLocation').val();
        showStorageLocation(officeLocationTemp);
    });
    function showStorageLocation(officeLocationTemp, storageLocationTemp) { //展示列表存放位置的列表
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/receiveGoods/api/searchStorageLocationByOfficeLocation",
            data: {"storageLocation": storageLocationTemp, "officeLocation": officeLocationTemp},
            success: function (data) {
                var html = '';
                $.each(data, function (commentIndex, comment) {
                    html += '<tr style="white-space:nowrap;"><td class="tdpadding"><input type="radio" name="storageLocationRadio" value=' + commentIndex + '>'
                        + '</td><td class="tdpadding" id=\"storageLocation' + commentIndex + '\">' + (comment['storageLocation'] == null ? "" : comment['storageLocation'])
                        + '</td><td class="tdpadding" id=\"officeLocation' + commentIndex + '\">' + (comment['officeLocation'] == null ? "" : comment['officeLocation'])
                        + '</td></tr>';
                });
                $('#tbody_storageLocation').html(html);

            }
        });
    }

    $('#searchStorageLocation').click(function () {//模糊查询存放位置
        var storageLocationTemp = $('#searchStorageLocationName').val();
        var officeLocationTemp = $('#officeLocation').val();
        showStorageLocation(officeLocationTemp, storageLocationTemp);

    })

    $("#sureStorageLocation").click(function () { //确定存放位置
        if ($("#isAllOrOnly").val() == '1') { //单个修改存放位置
            var i = $("input[name='storageLocationRadio']:checked").val();
            if (!i) {
                alert("未选择任何一行数据!");
                return;
            }
            var storageLocationTemp = $.trim($("#storageLocation" + i).html());
            $('#storageLocationWindow').window('close');
            $inputStorageLocation.parent().parent().find('#storageLocationLine').val(storageLocationTemp);
        } else {
            var i = $("input[name='storageLocationRadio']:checked").val();
            if (!i) {
                alert("未选择任何一行数据!");
                return;
            }
            var storageLocationTemp1 = $.trim($("#storageLocation" + i).html());
            $('#storageLocationWindow').window('close');
            $("input[name='storageLocation']").val(storageLocationTemp1);
            $("#storageLocationLine1").val(storageLocationTemp1);
        }

    });
    function cleanDepAll() {
        $("input[name='storageLocation']").val("");
    }


    function showDemandOrder(id) {
        dg.curWin.showDemandOrder(id);
    }
    function showApplyOrder(id) {
        dg.curWin.showApplyOrder(id);
    }
    function cleanDep(that) {
        $(that).prev().val("");
    }
</script>
</body>
</html>