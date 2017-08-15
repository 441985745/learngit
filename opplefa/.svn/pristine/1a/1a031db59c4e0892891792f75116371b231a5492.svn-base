<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess" %>
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
<!--头   验收单--s-->
<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
    <div id="tabletitle"
         style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
        资产验收单
    </div>
</div>
<br data-options="region:'center'" style="padding:10px;">
    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
        <input type="button" name="" onclick="" value="提交" id="btnsaveCheck" class="btn">
        <%--<input type="button" name="" onclick="btnsaveCheck(-2)" value="保存草稿" id="btnsaveCheckDraft" class="btn">--%>
        <input type="button" name="" value="返回" onclick="dg.cancel();" id="btnCheckCancel" class="btn">
    </div>
    <div id="Div7" style="margin-left: 0px; margin-top: 0px;">
        <app:approvalTag billCode="${checkAcceptance.cdocument}" typeId="77078"></app:approvalTag>
    </div>
    <form action="receiveGoodsHeader" id="receiveGoodsHeaderForm">
        <table id="addReceiveGoodsHeaderTable" border="0" cellpadding="3px" cellspacing="0" width="90%">
            <tbody>
            <tr>
                <input type="hidden" name="isMail" id="isMail" value="">
                <input type="hidden" name="isPhoneMessage" id="isPhoneMessage" value="">
                <input type="hidden" name="idea" id="idea" value="">
                <input type="hidden" name="activityId" id="activityId" value="">
                <input name="orderType" value="${checkAcceptance.orderType }" type="hidden" id="orderType">
                <input name="buyerName" value="${checkAcceptance.buyerName }" type="hidden" id="buyerName"
                       class="colorblur">
                <input name="buyerCode" value="${checkAcceptance.buyerCode }" type="hidden" id="buyerCode"
                       class="colorblur">

                <td align="right" class="tdtitle">采购订单号:
                </td>
                <td class="tdtext">
                    <input name="purchaseId" value="${checkAcceptance.purchaseId }" type="text" id="purchaseId"
                           class="colorblur">
                    <input name="purchaseDetailId" value="${checkAcceptance.purchaseDetailId }" type="hidden"
                           id="purchaseDetailId" class="colorblur">
                    <input name="applyDetailId" value="${checkAcceptance.applyDetailId}" type="hidden"
                           id="applyDetailId" class="colorblur">

                </td>
                <td align="right" class="tdtitle">收货单号:
                </td>
                <td class="tdtext">
                    <input name="receiveGoodsId" value="${checkAcceptance.receiveGoodsId }" type="text"
                           id="receiveGoodsId" class="colorblur">
                    <input name="receiveGoodsDetailId" value="${checkAcceptance.receiveGoodsDetailId }"
                           type="hidden" id="receiveGoodsDetailId" class="colorblur">
                </td>
                <td align="right" class="tdtitle">验收单编码:
                </td>
                <td class="tdtext">
                    <input name="cdocument" readonly value="${checkAcceptance.cdocument }" type="text"
                           id="cdocument" class="colorblur">
                </td>
            <tr>
                <td align="right" class="tdtitle">使用信息：
                    <!-- 在用 -->
                </td>
                <td class="tdtext">
                    <input name="useInfo" value="${checkAcceptance.useInfo }" type="text" id="useInfo"
                           class="colorblur">
                </td>
                <td align="right" class="tdtitle">资产类别:
                </td>
                <td class="tdtext">
                    <input name="assetsType" readonly value="${checkAcceptance.assetsType }" type="text"
                           id="assetsType" class="colorblur">
                </td>

                <td align="right" class="tdtitle">使用状态:
                </td>
                <td class="tdtext">
                    <input name="useStatus" readonly value="${checkAcceptance.useStatus }" type="text"
                           id="useStatus" class="colorblur">
                </td>
            </tr>
            <tr>

                <td align="right" class="tdtitle">到货日期:
                </td>
                <!-- 实际收货日期 -->
                <td class="tdtext">
                    <input name="receiveDate" readonly
                           value="<fmt:formatDate value='${checkAcceptance.receiveDate }' pattern='yyyy-MM-dd'/> "
                           type="text"
                           id="receiveDate" class="colorblur">
                </td>
                <td align="right" class="tdtitle">使用年限:
                </td>
                <!-- 注意：因为是在申请的行里，所以不能直接查询出来，显示，得通过选定收货行来确定是哪个申请行，然后填充 -->
                <!-- ? 申请行里的 折旧年限 -->
                <td class="tdtext">
                    <input name="depreciation" value="${checkAcceptance.depreciation }" type="text"
                           id="depreciation" class="colorblur">
                </td>
                <td align="right" class="tdtitle">计量单位:
                </td>
                <td class="tdtext">
                    <!-- 申请行里的单位 -->
                    <input name="units" readonly value="${checkAcceptance.units }" type="text" id="units"
                           class="colorblur">
                </td>

                <%--<td align="right" class="tdtitle">检定周期:
                    </td>
                    <!-- 手填 -->
                    <td class="tdtext">
                        <input name="checkCycle"  readonly value="${checkAcceptance.checkCycle }" type="text" id="checkCycle" class="colorblur">
                    </td>--%>
            </tr>
            <tr>
                <%--<td align="right" class="tdtitle">合同号:
                </td>
                <!-- 收货头取 -->
                <td class="tdtext">
                    <input name="contractNo" readonly value="${checkAcceptance.contractNo }" type="text"
                           id="contractNo" class="colorblur">
                </td>--%>

                <%--<td align="right" class="tdtitle">校准日期:
                </td>
                <!-- 手动输入 -->
                <td class="tdtext">
                    <input name="checkActiveDate"  readonly value="${checkAcceptance.checkActiveDate }"  type="text" id="checkActiveDate" class="colorblur">
                </td>--%>
                <%--<td align="right" class="tdtitle">批准文号:
                </td>

                <td class="tdtext">
                    <input name="approvalCode" readonly value="${checkAcceptance.approvalCode }" type="text"
                           id="approvalCode" class="colorblur">
                </td>--%>
            </tr>
            <tr>

                <%--<td align="right" class="tdtitle">启用日期:
                </td>
                <td class="tdtext">
                    <input name="enableDate" readonly value="${checkAcceptance.enableDate }" type="text"
                           id="enableDate" class="colorblur">
                </td>--%>
                <td align="right" class="tdtitle">采购申请部门:
                </td>
                <!-- 采购申请部门(收货头里已经保存) -->
                <td class="tdtext">
                    <input name="applyDepartment" readonly value="${checkAcceptance.applyDepartment }"
                           type="text" id="applyDepartment" class="colorblur">
                    <input name="applyDepartmentCode" readonly
                           value="${checkAcceptance.applyDepartmentCode }" type="hidden"
                           id="applyDepartmentCode" class="colorblur">
                </td>

                <td align="right" class="tdtitle">品牌/规格/型号:
                </td>
                <!-- 在收货行里已经保存，这里也得确定哪个收货行，然后回写 -->
                <!-- 申请表里带出 -->
                <td class="tdtext">
                    <input name="brand" readonly value="${checkAcceptance.brand }" type="text"
                           id="brand" class="colorblur">
                </td>
                <td align="right" class="tdtitle">供应商:
                </td>
                <td class="tdtext">
                    <input name="supplierName" value="${checkAcceptance.supplierName }" type="text"
                           id="supplierName" class="colorblur">
                    <input name="supplierCode" value="${checkAcceptance.supplierCode }" type="hidden"
                           id="supplierCode" class="colorblur">
                </td>
            </tr>
            <tr>

                <td align="right" class="tdtitle">成本中心:
                </td>
                <td class="tdtext">
                    <input name="costCenter" readonly value="${checkAcceptance.costCenter }" type="text"
                           id="costCenter" class="colorblur">
                    <input name="costCenterCode" readonly value="${checkAcceptance.costCenterCode }"
                           type="hidden" id="costCenterCode" class="colorblur">
                </td>
                <td align="right" class="tdtitle">公司名称:
                </td>
                <td class="tdtext">
                    <input name="computerName" readonly value="${checkAcceptance.companyName }" type="text"
                           id="computerName" class="colorblur">
                    <input name="computerCode" readonly value="${checkAcceptance.companyCode }"
                           type="hidden" id="computerCode" class="colorblur">
                </td>
                <td align="right" class="tdtitle">合同号:
                </td>
                <!-- 收货头取 -->
                <td class="tdtext">
                    <input name="contractNo" value="${checkAcceptance.contractNo }" type="text" id="contractNo"
                           class="colorblur">
                </td>

            </tr>
            </tr>
            <tr>
                <%-- <td align="right" class="tdtitle">保修周期:
                 </td>
                 <!-- 取的收货单的行 保固期 -->
                 <td class="tdtext">
                     <input name="recWarrantyPeriod" readonly   value="${checkAcceptance.recWarrantyPeriod }" type="text" id="recWarrantyPeriod" class="colorblur">
                 </td>	--%>

                <td align="right" class="tdtitle">收货数量:
                </td>
                <td class="tdtext">
                    <input name="goodsCount" readonly value="${checkAcceptance.goodsCount }" type="text"
                           id="goodsCount" class="colorblur">
                </td>
                <td align="right" class="tdtitle">已验收数量:
                </td>
                <td class="tdtext">
                    <input name="checkCount" readonly value="${checkAcceptance.checkCount }" type="text"
                           id="checkCount" class="colorblur">
                </td>
                <td align="right" class="tdtitle">本次验收数量:
                </td>
                <td class="tdtext">
                    <input name="thisCheckCount" value="${checkAcceptance.thisCheckCount }" type="text"
                           id="thisCheckCount" class="colorblur"
                           readonly>
                </td>
            </tr>
            <tr>
                <td align="right" class="tdtitle">办公地点:
                </td>
                <td class="tdtext">
                    <input name="officeLocation" readonly value="${checkAcceptance.officeLocation }" type="text"
                           id="officeLocation" class="colorblur">
                </td>
            </tr>
            <tr>


                <%--<td align="right" class="tdtitle">存放位置:
                </td>
                <td class="tdtext">
                    <input name="storageLocation" readonly value="${checkAcceptance.storageLocation }"
                           type="text" id="storageLocation" class="colorblur">
                </td>--%>
            </tr>

            </tbody>
        </table>
    </form>
</br>
    <form action="receiveGoodsDetail" id="receiveGoodsDetailForm">
        <div style="width:90%; margin: 0 auto;">
            <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" width="880px;"
                   class="tablenoborder">
                <tbody>
                <tr>
                    <td align="center" class="tdtitle" width="50px;">
                        全局设置存放位置：
                    </td>
                    <td class='tdpadding' align="left"><input style='width: 100px;' name='storageLocation1' type='text'
                                                              id='storageLocationLine1' class='inputbtnRefViewGray'>
                        <input id='btnRefClear1' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X'
                               onclick='cleanDepAll();'>
                        <input id='storageLocationShow1' class='btnRefView' title='点击打开参照文本框内容' type='button'
                               value='...'>
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
                                            <table id="table_add_detail" class="adminlist" cellspacing="0" rules="all"
                                                   border="1" id="gridDataList" style="border-collapse:collapse;">
                                                <tr style="white-space:nowrap;">
                                                    <th class='tdpadding' scope="col">序号</th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>需求单号
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>物料凭证号
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>会计凭证号
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>物料凭证年度
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>资产名称
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>保修周期
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>存放位置
                                                    </th>
                                                    <th style="display: none" scope="col"><font color="red">*</font>固定资产编码
                                                    </th>
                                                    <th style="display: none" scope="col"><font color="red">*</font>上传图片主键
                                                    </th>
                                                    <!-- MM+sap主编码+sap次级编码 -->
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>资产编号
                                                    </th>
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>归口管理编号
                                                    </th>
                                                    <!-- 手动填 -->
                                                    <th class='tdpadding' scope="col"><font color="red">*</font>
                                                        <c:choose>
                                                            <c:when test="${checkAcceptance.assetsType == 'IT资产'}">
                                                                序列号
                                                            </c:when>
                                                            <c:when test="${checkAcceptance.assetsType == '计量器具'}">
                                                                出厂编号
                                                            </c:when>
                                                            <c:when test="${checkAcceptance.assetsType == '机器设备'}">
                                                                机器编号
                                                            </c:when>
                                                            <c:when test="${checkAcceptance.assetsType == '行政资产'}">
                                                                出厂编号/序列号
                                                            </c:when>
                                                        </c:choose></th>
                                                    <!-- 手动填 -->
                                                    <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                                        <th class='tdpadding' scope="col"><font
                                                                color="red">*</font>生产厂家
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                                        <th class='tdpadding' scope="col"><font
                                                                color="red">*</font>管理级别
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                                        <th class='tdpadding' scope="col"><font
                                                                color="red">*</font>精度等级
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                                        <th class='tdpadding' scope="col"><font
                                                                color="red">*</font>检定周期（日）
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                                        <th class='tdpadding' scope="col"><font
                                                                color="red">*</font>校准日期
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                                        <th class='tdpadding' scope="col"><font
                                                                color="red">*</font>下次校准日期
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                                        <th class='tdpadding' scope="col"><font
                                                                color="red">*</font>校准单位
                                                        </th>
                                                    </c:if>

                                                    <!-- 手动填 -->
                                                    <th class='tdpadding' scope="col"><font
                                                            color="red">*</font>资产照片
                                                    </th>
                                                    <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                                        <!-- 上传/查看 -->
                                                        <th class='tdpadding' scope="col"><font
                                                                color="red">*</font>是否有第三方校验报告
                                                        </th>
                                                    </c:if>
                                                    <!-- IT资产-->
                                                    <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                                        <th class='tdpadding' scope="col">CPU
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                                        <th class='tdpadding' scope="col">内存
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                                        <th class='tdpadding' scope="col">硬盘
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                                        <th class='tdpadding' scope="col">IP地址
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                                        <th class='tdpadding' scope="col">加入域
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                                        <th class='tdpadding' scope="col">操作系统
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                                        <th class='tdpadding' scope="col">应用系统
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                                        <th class='tdpadding' scope="col">功能描述
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                                        <th class='tdpadding' scope="col">数据库
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == '机器设备'}">
                                                        <th class='tdpadding' scope="col">车间类型
                                                        </th>
                                                    </c:if>
                                                    <c:if test="${checkAcceptance.assetsType == '行政资产'}">
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
    </form>
</div>
</br>
<!-- 存放位置-->
<div id="storageLocationWindow" name="" class="easyui-window" title="选择存放位置" data-options="iconCls:'icon-save'"
     style="width:750px;height:380px;padding:5px;" closed="true">
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
            <div style="width:700px;height:280px; overflow:scroll;">
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
</div>
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

<div style="width:90%; margin: 0 auto;">
    <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
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
                        <table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList"
                               style="border-collapse:collapse;">
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

<c:if test="${checkAcceptance.assetsType == '机器设备'}">
    <div>
        <form id='machineForm'>
            <table>
                <tbody id="machineBody">
                    <%--<c:if test="${checkAcceptance.assetsType == '机器设备'}">--%>
                <tr>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>1&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>设备资料是否齐全</td>
                    <td class='tdpadding'>
                        <select name='machine1' id="machine1" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>9&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>设备运行参数是否与使用说明书完全符合</td>
                    <td class='tdpadding'>
                        <select name='machine9' id="machine9" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>2&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>规格型号是否与合同文件一致</td>
                    <td class='tdpadding'>
                        <select name='machine2' id="machine2" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>10&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>各性能是否与安装及试使用期时一致</td>
                    <td class='tdpadding'>
                        <select name='machine10' id="machine10" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>3&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>所有电气是否张贴标签</td>
                    <td class='tdpadding'>
                        <select name='machine3' id="machine3" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>11&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>配件数量是否与合同一致</td>
                    <td class='tdpadding'>
                        <select name='machine11' id="machine11" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>4&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>设备铭牌是否清晰可见</td>
                    <td class='tdpadding'>
                        <select name='machine4' id="machine4" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>12&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>是否有安全隐患</td>
                    <td class='tdpadding'>
                        <select name='machine12' id="machine12" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>5&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>安全隐患处是否张贴警示标示</td>
                    <td class='tdpadding'>
                        <select name='machine5' id="machine5" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>13&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>提供的零配件清单是否与机台使用型号相同</td>
                    <td class='tdpadding'>
                        <select name='machine13' id="machine13" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>6&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>整机可否可以投入试运行</td>
                    <td class='tdpadding'>
                        <select name='machine6' id="machine6" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>14&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>技术指标参照合同和技术要求进行验收，必要时需要添加附件</td>
                    <td class='tdpadding'>
                        <select name='machine14' id="machine14" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>7&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>设备是否进行过现场操作和维护保养培训</td>
                    <td class='tdpadding'>
                        <select name='machine7' id="machine7" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>15&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>生产需要提供设备试作报告</td>
                    <td class='tdpadding'>
                        <select name='machine15' id="machine15" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class='tdpadding'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>8&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td class='tdpadding'>各性能和配置是否与投标文件和技术要求一致</td>
                    <td class='tdpadding'>
                        <select name='machine8' id="machine8" value=''>
                            <option value='0'>是</option>
                            <option value='1'>否</option>
                            <option value='2'>不适用</option>
                        </select>
                    </td>
                </tr>
                    <%--</c:if>--%>
                </tbody>
            </table>
        </form>
    </div>
</c:if>

<!-- 新增验收单--e-->

<!-- 需求明细展示--s-->
<div id="addDemandDetailWindow" name="addDemandDetailWindow" class="easyui-window" title="需求详情展示"
     data-options="iconCls:'icon-save'" style="width:900px;height:400px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <div id="Div3" style="margin-left: 0px; margin-top: 0px;">
                <input type="button" name="" value="确定" id="savebtnDemandDetail" class="btn">
                <input type="button" name="btnCancel" value="返回" id="" class="btn">
            </div>
            <div style="margin-top: 10px">
                <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" class="tablenoborder">
                    <tbody>
                    <tr>
                        <td colspan="4">
                            <form action="addDemandDetail" id="addDemandDetailForm">
                                <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                                       style="table-layout: fixed;">
                                    <tbody>
                                    <tr>
                                        <td class="tdtitle" style="text-align: right" colspan="2">
                                            <div id="div1" class="divGrid"
                                                 style="overflow-x: scroll; overflow-y: visible;">
                                                <table id="table_add_detail" class="adminlist" cellspacing="0"
                                                       rules="all" border="1" id="gridDataList"
                                                       style="border-collapse:collapse;">
                                                    <tr style="white-space: nowrap;">
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>资产类型&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>资产名称&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>收货的需求数量&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>已验收的需求数量&nbsp;&nbsp;
                                                        </th>
                                                        <!-- 													<th scope="col">&nbsp;&nbsp;<font color="red">*</font>本次验收的需求数量&nbsp;&nbsp; -->
                                                        <!-- 													</th> -->
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>品牌/规格/型号&nbsp;&nbsp;
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
<!-- 需求详情展示--e-->
<!-- 上传照片 s-->
<div id="photoWindow" name="photoWindow" class="easyui-window" title="上传照片" data-options="iconCls:'icon-save'"
     style="width:600px;height:400px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <div style="margin-top: 10px">
                <form action="receiveGoodsHeader" id="receiveGoodsHeaderForm1">
                    <table id="addReceiveGoodsHeaderTable" border="0" cellpadding="0" cellspacing="0" width="90%">
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

                <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" class="tablenoborder">
                    <tbody>
                    <tr>
                        <td colspan="4">
                            <form action="" id="photoForm">
                                <table id="Table11" width="100%" border="0" cellpadding="0" cellspacing="0"
                                       style="table-layout: fixed;">
                                    <tbody>
                                    <tr>
                                        <td class="tdtitle" style="text-align: right" colspan="2">
                                            <div id="div1" class="divGrid"
                                                 style="overflow-x: scroll; overflow-y: visible;">
                                                <table id="table_add_detail1" class="adminlist" cellspacing="0"
                                                       rules="all" border="1" id="gridDataList"
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
<%-- 	<script src="${staticURL}/Scripts/Purchase/CheckAcceptance/CheckAcceptance.js" type="text/javascript"></script> --%>
<script type="text/javascript">
    var dg = frameElement.lhgDG;
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
    var photodocument = null;
    var checkAcceptSap = {};
    var checkAcceptDetailList = {};
    $(document).ready(function () { //页面加载时就 获得所有的sap资产编码和demandDetailId（判断更改哪个sap）
        var checkAcceptId = $('#cdocument').val();
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/checkAccept/api/getCheckAcceptanceDetailByDocument",
            data: {"checkAcceptId": checkAcceptId},
            success: function (data) {
                checkAcceptDetailList = data;//demandDetailId 循环2
                //$("#brand").val(checkAcceptDetailList.length==0?"":checkAcceptDetailList[0].brand);
                //debugger
            }
        });
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/checkAccept/api/searchCheckAcceptDetailByCheckAcceptId",
            data: {"checkAcceptId": checkAcceptId},
            success: function (data) {
                checkAcceptSap = data; //sap资产循环5
                //debugger
                showCheckAcceptDetail();
            }
        });

    });

    function showCheckAcceptDetail() {//显示sap验收行
        var divHtml = "";
        for (var j = 0; j < checkAcceptDetailList.length; j++) { // 循环拿到本次验收对应的需求行和订单行
            var numTemp = 0;
            for (var i = 0; i < checkAcceptSap.length; i++) { // 循环拿到本次验收对应的需求行和订单行
                if (checkAcceptDetailList[j].demandDetailId == checkAcceptSap[i].demandDetailId) {
                    numTemp += (i + 1) / (i + 1);
                    if ((numTemp + 1) / (parseInt(checkAcceptDetailList[j].checkCountDemand) + 1) <= 1) {
                        divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                        divHtml += "<td class='tdpadding'>" + (i + 1) +
                            "<input type='hidden' name='checkAcceptId' id='checkAcceptId' value='" + checkAcceptSap[i].checkAcceptId + "' title='checkAcceptId' >" +
                            "<input type='hidden' name='checkAcceptDetailId' id='checkAcceptDetailId' value='" + checkAcceptSap[i].checkAcceptDetailId + "' title='checkAcceptId' >" +
                            "<input type='hidden' name='receiveGoodsId' id='receiveGoodsId' value='" + checkAcceptSap[i].receiveGoodsId + "' title='receiveGoodsId' >" +
                            "<input type='hidden' name='cardId' id='cardId' value='" + checkAcceptSap[i].cardId + "' >" +
                            "<input type='hidden' name='purchaseId' id='purchaseId' value='" + checkAcceptSap[i].purchaseId + "' title='purchaseId'>" +
                            "<input type='hidden' name='purchaseDetailId' id='purchaseDetailId' value='" + checkAcceptSap[i].purchaseDetailId + "' title='purchaseDetailId' >" +
                            "<input type='hidden' name='applyDetailId' id='applyDetailId' value='" + checkAcceptSap[i].applyDetailId + "' title='applyDetailId' ></td>";
                        divHtml += "<td class='tdpadding'>" + '<a href="javascript:showDemandOrder(\'' + checkAcceptSap[i].demandId + '\')" >' + checkAcceptSap[i].demandId + '</a>' +
                            "<input type='hidden' name='demandId' readonly id='demandId' value='" + checkAcceptSap[i].demandId + "'>" +
                            "<input type='hidden' name='demandDetailId' id='demandDetailId' value='" + checkAcceptSap[i].demandDetailId + "'  title='demandDetailId'>" +
                            "</td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='epMaterialdocu'  readonly id='epMaterialdocu' value=''  ></td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='epBelnr'  readonly id='epBelnr' value=''  ></td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='epMatdocumentyear'  readonly id='epMatdocumentyear' value=''  ></td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='assetsName'  readonly id='assetsName' value='" + checkAcceptSap[i].assetsName + "'  ></td>";
                        divHtml += "<td class='tdpadding'><input type='text' required='required' name='recWarrantyPeriod' readonly id='recWarrantyPeriod' value='" + checkAcceptSap[i].recWarrantyPeriod + "'  ></td>";
                        divHtml += "<td class='tdpadding'><input type='text' required='required' name='storageLocation' readonly id='storageLocationLine' value='" + checkAcceptSap[i].storageLocation + "'  >" +
                            "<input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='cleanDep(this);'>" +
                            "<input id='storageLocationShow' class='btnRefView' title='点击打开参照文本框内容' type='button'  value='...'></td>";
                        divHtml += "<td style='display: none'><input type='hidden' name='mmAssetsCode'  id='mmAssetsCode' value='" + checkAcceptSap[i].mmAssetsCode + "' ></td>";
                        divHtml += "<td style='display: none'><input type='hidden' name='systeManagementCode'  id='systeManagementCode' value='" + checkAcceptSap[i].systeManagementCode + "' ></td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='sapAssetsCode'  readonly id='sapAssetsCode' value='" + checkAcceptSap[i].sapAssetsCode + "'  ></td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='putUnderManageCode'  readonly id='putUnderManageCode' value='" + checkAcceptSap[i].manageapplyDepartmentCode + "'></td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='serialNumber'  readonly id='serialNumber' value='" + checkAcceptSap[i].serialNumber + "' ></td>";

                        //                                    divHtml += "<td class='tdpadding'><input type='text' name='pasteAssetsTag' id='pasteAssetsTag' ></td>";是否粘贴标签
                        if ("计量器具" == $('#assetsType').val()) { //生产厂家 manufactureFactory
                            divHtml += "<td class='tdpadding' align = center ><input required='required' type='text' name='manufactureFactory' id='manufactureFactory' value='" + checkAcceptSap[i].manufactureFactory + "' ></td>";
                        }
                        if ("计量器具" == $('#assetsType').val()) { //管理级别
                            var flag1 = checkAcceptSap[i].managementLevel == 'A' ? 'selected:' : '';
                            var flag2 = checkAcceptSap[i].managementLevel == 'B' ? 'selected' : '';
                            var flag3 = checkAcceptSap[i].managementLevel == 'C' ? 'selected' : '';
                            divHtml += "<td class='tdpadding' align = center >" +
                                "<select name='managementLevel' required='required' id='managementLevel'>" +
                                "<option value='A' " + flag1 + ">A</option> " +
                                "<option value='B' " + flag2 + ">B</option> " +
                                "<option value='C' " + flag3 + ">C</option> " +
                                "</select></td>";
                        }
                        if ("计量器具" == $('#assetsType').val()) { //精度等级
                            divHtml += "<td class='tdpadding' align = center ><input required='required' type='text' name='accuracyClass' id='accuracyClass' value='" + checkAcceptSap[i].accuracyClass + "' ></td>";
                        }
                        if ("计量器具" == $('#assetsType').val()) { //检定周期（日）
                            divHtml += "<td class='tdpadding' align = center ><input required='required' type='text' onchange='change1(this);' name='verificationCycle' id='verificationCycle' value='" + checkAcceptSap[i].verificationCycle + "' ></td>";
                        }
                        if ("计量器具" == $('#assetsType').val()) { //校准日期 checkAcceptSap[i].calibrationDateStr
                            divHtml += "<td class='tdpadding' align = center ><input required='required' name='calibrationDate' value='" + checkAcceptSap[i].calibrationDate + "'  type='text' onchange='change(this);' id='calibrationDate' class='Wdate' onclick='WdatePicker({skin:" + '"' + "ext" + '"' + "});'></td>";
                        }
                        if ("计量器具" == $('#assetsType').val()) { //下次校准日期
                            divHtml += "<td class='tdpadding' align = center ><input required='required' name='nextCalibrationDate' value='" + checkAcceptSap[i].nextCalibrationDate + "' type='text' id='nextCalibrationDate' disabled='true'></td>";
                        }
                        if ("计量器具" == $('#assetsType').val()) { //校准单位
                            divHtml += "<td class='tdpadding' align = center ><input required='required' type='text' name='calibrationUnit' id='calibrationUnit' value='" + checkAcceptSap[i].calibrationUnit + "' ></td>";
                        }

                        divHtml += "<td class='tdpadding'><input type='button'   name='assetsPhoto' id='assetsPhoto' value='上传/查看' ></td>";


                        if ("计量器具" == $('#assetsType').val()) { //是否有第三方校验报告
                            divHtml += "<td class='tdpadding' align = center >" +
                                "<select value='0' name='isThirdReport' onchange='change2(this);' id='isThirdReport'><option value='0'>是</option><option selected='selected'value='1'>否</option></select>" +
                                "<input type='button' name='otherCalibrationReport' hidden='false'id='otherCalibrationReport' value='上传/查看' ></td>";
                        }
                        if ("IT资产" == $('#assetsType').val()) { //CPU
                            divHtml += "<td class='tdpadding' align = center ><input type='text' name='cpu' id='cpu' value='" + checkAcceptSap[i].cpu + "' ></td>";
                        }
                        if ("IT资产" == $('#assetsType').val()) { //内容
                            divHtml += "<td class='tdpadding' align = center ><input type='text' name='internalMemory' id='internalMemory' value='" + checkAcceptSap[i].content + "' ></td>";
                        }
                        if ("IT资产" == $('#assetsType').val()) { //硬盘
                            divHtml += "<td class='tdpadding' align = center ><input type='text' name='hardpan' id='hardpan' value='" + checkAcceptSap[i].hardpan + "' ></td>";
                        }
                        if ("IT资产" == $('#assetsType').val()) { //ip地址
                            divHtml += "<td class='tdpadding' align = center ><input type='text' name='ipAddress' id='ipAddress' value='" + checkAcceptSap[i].ipAddress + "' ></td>";
                        }
                        if ("IT资产" == $('#assetsType').val()) { //加入域
                            divHtml += "<td class='tdpadding' align = center ><input type='text' name='joinDomain' id='joinDomain' value='" + checkAcceptSap[i].joinDomain + "' ></td>";
                        }
                        if ("IT资产" == $('#assetsType').val()) { //操作系统
                            divHtml += "<td class='tdpadding' align = center ><input type='text' name='operatingSystem' id='operatingSystem' value='" + checkAcceptSap[i].operatingSystem + "' ></td>";
                        }
                        if ("IT资产" == $('#assetsType').val()) { //应用系统
                            divHtml += "<td class='tdpadding' align = center ><input type='text' name='utilitySystem' id='utilitySystem' value='" + checkAcceptSap[i].utilitySystem + "' ></td>";
                        }
                        if ("IT资产" == $('#assetsType').val()) { //功能描述
                            divHtml += "<td class='tdpadding' align = center ><input type='text' name='description' id='description' value='" + checkAcceptSap[i].description + "' ></td>";
                        }
                        if ("IT资产" == $('#assetsType').val()) { //数据库
                            divHtml += "<td class='tdpadding' align = center ><input type='text' name='dataBase' id='dataBase' value='" + checkAcceptSap[i].dataBase + "' ></td>";
                        }
                        if ("机器设备" == $('#assetsType').val()) { //车间类型
                            divHtml += "<td class='tdpadding' align = center ><input type='text' name='shopType' id='shopType' value='" + checkAcceptSap[i].shopType + "' ></td>";
                        }
                        if ("行政资产" == $('#assetsType').val()) { //是否为房屋建筑,采购申请中带出,不可修改
                            divHtml += "<td class='tdpadding' align = center ><input type='text' readonly name='isBuildingContruction' id='isBuildingContruction' value='" + checkAcceptSap[i].isBuildingContruction + "' ></td>";
                        }
                        divHtml += "</tr>";
                    }
                }
            }
        }
        $("#tbody_add_detail").html(divHtml);
    }

    /* 更改已验收的需求数量 */
    var $thisInput = null;
    /**
     * 验收数量不可修改*/
    /*$('#checkCountTXT').click(function(){ // 更改已验收的需求数量
     $thisInput = $(this);
     var top = "50px";//$(this).offset().top - 350;
     var left = "70px";//$(this).offset().left-180;
     $('#addDemandDetailWindow').window('open').window('resize',{width:'900px',height:'400px',top: top,left:left});
     // 			debugger;
     var divHtml="";
     for(var i = 0; i < checkAcceptDetailList.length; i++){ // 显示 demandDetailId 的需求数量
     divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
     divHtml += "<td class='tdpadding'><input type='text' name='assetsType' value='"+checkAcceptDetailList[i].assetsType+"'><input type='hidden' name='checkAcceptId' value='"+checkAcceptDetailList[i].checkAcceptId+"' title='checkAcceptId'><input type='hidden' name='receiveGoodsId' value='"+checkAcceptDetailList[i].receiveGoodsId+"' title='receiveGoodsId'><input type='hidden' name='receiveGoodsDetailId' value='"+checkAcceptDetailList[i].receiveGoodsDetailId+"' title='receiveGoodsDetailId'><input type='hidden' name='purchaseId' id='' value='"+checkAcceptDetailList[i].purchaseId+"' title='purchaseId'></td>";
     divHtml += "<td class='tdpadding'><input type='text' name='assetsName' value='"+checkAcceptDetailList[i].assetsName+"'><input type='hidden' name='demandDetailId' id='demandDetailIdTemp' value='"+checkAcceptDetailList[i].demandDetailId+"' title='demandDetailId'> <input type='hidden' name='applyDetailId' id='applyDetailIdTemp'  value='"+checkAcceptDetailList[i].applyDetailId+"' title='applyDetailId'></td>";
     divHtml += "<td class='tdpadding'><input type='text' name='goodsCountDemand' id='' value='"+checkAcceptDetailList[i].goodsCountDemand+"' readonly='readonly' ></td>";
     divHtml += "<td class='tdpadding'><input type='text' name='checkCountDemand' id='checkCountDemandTemp' value='"+checkAcceptDetailList[i].checkCountDemand+"' ></td>";
     divHtml += "<td class='tdpadding'><input type='text' name='brand' id='brand' value='"+checkAcceptDetailList[i].brand+"' ></td>";
     divHtml += "<td class='tdpadding'><input type='text' name='functions' id='functions' value='"+checkAcceptDetailList[i].functions+"' ></td>";
     divHtml += "</tr>";
     }
     $("#tbody_demand_detail").html(divHtml);
     })*/

    /* 需求 确定 保存 */
    var demandTemp = {};
    $('#savebtnDemandDetail').click(function () {
        var objDemandDetail = {
            assetsName: "",
            assetsType: "",
            goodsCountDemand: "",
            checkCountDemand: "",
            checkAcceptId: "",
            projectCode: "",
            brand: "",
            receiveGoodsId: "",
            applyDetailId: "",
            demandDetailId: "",
            purchaseDetailId: "",
            receiveGoodsDetailId: ""
        }
        demandTemp = constructObjectFromTable(objDemandDetail, $('#addDemandDetailForm .bodyTr'));
        var checkCountDemandTemp = 0;
        for (var i = 0; i < checkAcceptDetailList.length; i++) {
            for (var j = 0; j < demandTemp.length; j++) {
                if (checkAcceptDetailList[i].demandDetailId == demandTemp[j].demandDetailId) {
                    checkAcceptDetailList[i].checkCountDemand = demandTemp[j].checkCountDemand;
                    checkCountDemandTemp += parseInt(demandTemp[j].checkCountDemand);
                }
            }
        }
        $thisInput.val(checkCountDemandTemp);
        $('#addDemandDetailWindow').window('close');
        showCheckAcceptDetail();
    });

    $("#btnsaveCheck").click(function () {
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
        showAgreeDialog('${checkAcceptance.cdocument }', '77078', 'N');
    });
    function showAgreeDialog(document, typeId, changeAmount) {
        var url = "${dynamicURL}/workflow/showUpdateCommitDialog?parentWindowId=updateCheckAcceptance&document=${checkAcceptance.cdocument}&typeId=77078&changeAmount=" + changeAmount;
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


        var checkAcceptanceWrapper = {};
        $("#isMail").val(isMail);
        $("#isPhoneMessage").val(isPhoneMessage);
        $("#idea").val(idea);
        $("#activityId").val(activityId);
//      	 // 	头
        var objHeader = $('#receiveGoodsHeaderForm').serializeObject();
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
            isBuildingContruction: "", //是否为房屋建筑
            checkAcceptDetailId: ""
        }
        var objArrCheckAcceptanceDetail = constructObjectFromTable(objCheckDetail, $('#receiveGoodsDetailForm .bodyTr'));
//      	 		 for(var i = 0;i<objArrCheckAcceptanceDetail.length;i++){
//      	 			 objArrCheckAcceptanceDetail[i].checkAcceptId =$('#checkAcceptId').val();
//      	 		 }
        checkAcceptanceWrapper.checkAcceptanceDetails = objArrCheckAcceptanceDetail;

//      	 // 需求明细
//      	 			for ( var i = 0; i < checkAcceptDetailList.length; i++) {
//      	 				checkAcceptDetailList[i].checkAcceptId = $('#checkAcceptId').val();
//      	 			}
        checkAcceptanceWrapper.checkAcceptMappings = checkAcceptDetailList;

        var checkMachineObj = $('#machineForm').serializeObject();
        checkAcceptanceWrapper.checkMachine = checkMachineObj;
//      	 			debugger
        $.ajax({
            type: "POST",
            dataType: "json",
            cache: false,
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/checkAccept/api/updateSaveCheckAccept",
            data: JSON.stringify(checkAcceptanceWrapper),
            success: function (data) {
                //  				debugger
                if (data.errorMessages.length > 0) {
                    var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*保存成功.*$");
                    if (reg.test(str)) {

                        dg.curWin.location=dg.curWin.location;
                    }
                    return;
                } else {
                    alert(data.successMessage);
                    dg.curWin.location = dg.curWin.location;
                }
            }
        })
    }
    /*function btnsaveCheck(num){ // 提交
     var checkAcceptanceWrapper = {};
     //      	 // 	头
     var objHeader = $('#receiveGoodsHeaderForm').serializeObject();
     checkAcceptanceWrapper.checkAcceptance = objHeader;

     // 	行
     var objCheckDetail ={
     cardId:"",
     receiveGoodsId : "", // 收货单号
     receiveGoodsDetailId : "",//
     purchaseId : "",//
     purchaseDetailId : "",//
     applyDetailId : "", //
     demandId : "", //
     demandDetailId : "", //
     checkAcceptId : "", // 验收单据号
     assetsName : "", //
     mmAssetsCode : "", //固定资产(字段编码)MM+sap主编码+sap次级编码
     sapAssetsCode : "", // SAP资产编码
     manageapplyDepartmentCode : "",// 归口管理部门编码
     serialNumber : "", // 序列号
     pasteAssetsTag : "", // 是否有粘贴资产标签
     assetsPhoto : "", // 资产照片
     calibrationDate : "",//校准日期，
     nextCalibrationDate : "",//下次校准日期
     otherCalibrationReport : ""//是否有第三方校验报告
     }
     var objArrCheckAcceptanceDetail =constructObjectFromTable(objCheckDetail , $('#receiveGoodsDetailForm .bodyTr'));
     //      	 		 for(var i = 0;i<objArrCheckAcceptanceDetail.length;i++){
     //      	 			 objArrCheckAcceptanceDetail[i].checkAcceptId =$('#checkAcceptId').val();
     //      	 		 }
     checkAcceptanceWrapper.checkAcceptanceDetails = objArrCheckAcceptanceDetail;

     //      	 // 需求明细
     //      	 			for ( var i = 0; i < checkAcceptDetailList.length; i++) {
     //      	 				checkAcceptDetailList[i].checkAcceptId = $('#checkAcceptId').val();
     //      	 			}
     checkAcceptanceWrapper.checkAcceptMappings = checkAcceptDetailList;
     //      	 			debugger
     $.ajax({
     type : "POST",
     dataType : "json",
     cache : false,
     contentType : "application/json; charset=utf-8",
     url : "${dynamicURL}/checkAccept/api/updateSaveCheckAccept",
     data : JSON.stringify(checkAcceptanceWrapper),
     success : function(data) {
     //  				debugger
     if (data.errorMessages.length > 0) {
     alert(data.errorMessages);
     return;
     }else{
     alert(data.successMessage);
     dg.cancel();
     return;
     }
     }
     })
     }*/
    $("#assetsPhoto").live("click", function () { //上传弹框

        photodocument = $(this).parent().parent().find("#systeManagementCode").val();
//        alert(photodocument);
        var top = "70px";//$(this).offset().top - 350;
        var left = "0px";//$(this).offset().left-180;
        $('#fileType').val("0");
        $('#photoWindow').window('open').window('resize', {width: '800px', height: '400px', top: top, left: left});
        photoAllShow(photodocument);

    });
    $("#otherCalibrationReport").live("click", function () { //上传弹框  第三方检验报告

        photodocument = $(this).parent().parent().find("#systeManagementCode").val();
        var top = "70px";//$(this).offset().top - 350;
        var left = "170px";//$(this).offset().left-180;
        $('#fileType').val("1");
        $('#photoWindow').window('open').window('resize', {width: '800px', height: '400px', top: top, left: left});
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
                        divHtml += "<td class='tdpadding'><a href='${dynamicURL}/file/downloadFile?fileuuid=" + objdataTemp[i].fileuuid + "'>下载</a> </td>";
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
            var createUserName = $('#buyerName').val();
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
                    //console.log(data);
                    photoAllShow(photodocument);
                },
                error: function () {
                }
            });
        }
    );
    /*$('#delphoto').live("click", function () {
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
     console.log(data);
     // 	      $('#photoWindow').panel('open').panel('refresh');

     }
     });
     });*/

    function change(that) {
        //alert($(that).val());
        var days = $(that).parent().prev().children().val();
        var str = $(that).val(); // 2017-02-01
        var str2 = getNewDay(str, days);
        $(that).parent().next().children().val(str2);
        $(that).blur();

    }
    function change1(that) {
        //alert($(that).val());

        var days = $(that).val();
        var str = $(that).parent().next().children().val(); // 2017-02-01
        var str2 = getNewDay(str, days);
        if (null != str && '' != str) {
            $(that).parent().next().next().children().val(str2);
        }
        $(that).blur();
    }
    function change2(that) {
        /*if ($(that).val() == '1') {
         $(that).next().attr("hidden", true);
         }
         if ($(that).val() == '0') {
         $(that).next().attr("hidden", false);
         }*/
        if ($(that).val() == '1') {
            $(that).next().attr("style", "display: none;");
        }
        if ($(that).val() == '0') {
            $(that).next().attr("style", "display: inline;");
        }
    }
    function getNewDay(dateTemp, days) {
        var dateTemp = dateTemp.split("-");
        var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式    
        var millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);
        var rDate = new Date(millSeconds);
        var year = rDate.getFullYear();
        var month = rDate.getMonth() + 1;
        if (month < 10) month = "0" + month;
        var date = rDate.getDate();
        if (date < 10) date = "0" + date;
        return (year + "-" + month + "-" + date);
    }


    $(function () {
        loadMachine();
    });
    function loadMachine() {
        if ("机器设备" == $('#assetsType').val()) { //车间类型
            $("#machine1").val('${checkMachine.machine1}');
            $("#machine2").val('${checkMachine.machine2}');
            $("#machine3").val('${checkMachine.machine3}');
            $("#machine4").val('${checkMachine.machine4}');
            $("#machine5").val('${checkMachine.machine5}');
            $("#machine6").val('${checkMachine.machine6}');
            $("#machine7").val('${checkMachine.machine7}');
            $("#machine8").val('${checkMachine.machine8}');
            $("#machine9").val('${checkMachine.machine9}');
            $("#machine10").val('${checkMachine.machine10}');
            $("#machine11").val('${checkMachine.machine11}');
            $("#machine12").val('${checkMachine.machine12}');
            $("#machine13").val('${checkMachine.machine13}');
            $("#machine14").val('${checkMachine.machine14}');
            $("#machine15").val('${checkMachine.machine15}');
        }
    }
    $('#btnUpload1').click(function () {
        var fileElementId = $(this).prev().attr('id');
        var file = $('#fileToUpload1').val();
        if (file == "") {
            alert("请选择需要上传的文件！");
            return false;
        }

        var documentNo = $('#cdocument').val();
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
            data: {'document': $("#receiveGoodsId").val()},
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
            $("#storageLocationLine1").val(storageLocationTemp1);
            $('#storageLocationWindow').window('close');
            $("input[name='storageLocation']").val(storageLocationTemp1);
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