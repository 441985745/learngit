<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess" %>
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


<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
    <div id="tabletitle"
         style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
        资产验收单
    </div>
</div>
<div id="Div2" style="margin-left: 10px; margin-top: 0px;">
    <input type="submit" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
    <input type="button" onclick="stamp('${checkAcceptance.cdocument }','打印')" class="btn" value="打印"/>
</div>
<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
    <app:approvalTag billCode="${checkAcceptance.cdocument}" typeId="77078"></app:approvalTag>
</div>

<div style="margin-top: 5px;">

    <div id="receiveGoodsTab" style="width:100%">
        <div title="验收信息" id="tabsa" style="overflow:auto; padding:10px;">
            <div>
                <form action="receiveGoodsHeader" id="receiveGoodsHeaderForm">
                    <table id="addReceiveGoodsHeaderTable" border="0" cellpadding="3px" cellspacing="0" width="90%">
                        <tbody>
                        <tr>
                            <%--<td align="right" class="tdtitle">采购员:
                            </td>
                            <td class="tdtext">--%>
                            <input name="buyerName" readonly value="${checkAcceptance.buyerName }" type="hidden"
                                   id="buyerName" class="colorblur">
                            <input name="buyerCode" value="${checkAcceptance.buyerCode }" type="hidden"
                                   id="buyerCode" class="colorblur">
                            <%--<input name="attachDepartAdminCode" value="" type="hidden" id="attachDepartAdminCode" >
                            <input name="attachDepartAdminName" value="" type="hidden" id="attachDepartAdminName" >--%>
                            <input type="hidden" id="isManager" name="isManager" value="${isManager }">
                            <input name="orderType" value="${checkAcceptance.orderType }" type="hidden" id="orderType">
                            <%--</td>--%>
                            <td align="right" class="tdtitle">采购订单号:
                            </td>
                            <td class="tdtext">
                                <input name="purchaseId" readonly value="${checkAcceptance.purchaseId }" type="text"
                                       id="purchaseId" class="colorblur">
                                <input name="purchaseDetailId" readonly value="${checkAcceptance.purchaseDetailId }"
                                       type="hidden" id="purchaseDetailId" class="colorblur">
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
                        </tr>
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

                            <td align="right" class="tdtitle">型号规格/品牌:
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
                                <input name="contractNo" value="${checkAcceptance.contractNo }" type="text"
                                       id="contractNo" class="colorblur">
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
                                <input name="officeLocation" readonly value="${checkAcceptance.officeLocation }"
                                       type="text"
                                       id="officeLocation" class="colorblur">
                            </td>

                        </tr>


                        </tbody>
                    </table>
                </form>

                </br>
                <div style="width:90%; overflow:scroll;margin: 0 auto;">

                    <table id="table_add_detail" class="adminlist" cellspacing="0"
                           rules="all" border="1" id="gridDataList"
                           style="border-collapse:collapse;">
                        <tr style="white-space:nowrap;">
                            <th class='tdpadding' scope="col">序号</th>
                            <th class='tdpadding' scope="col"><font
                                    color="red">*</font>需求单号
                            </th>
                            <th class='tdpadding' scope="col"><font
                                    color="red">*</font>物料凭证号
                            </th>
                            <th class='tdpadding' scope="col"><font
                                    color="red">*</font>会计凭证号
                            </th>
                            <th class='tdpadding' scope="col"><font
                                    color="red">*</font>物料凭证年度
                            </th>
                            <th class='tdpadding' scope="col"><font
                                    color="red">*</font>资产名称
                            </th>
                            <th class='tdpadding' scope="col"><font
                                    color="red">*</font>保修周期
                            </th>
                            <th class='tdpadding' scope="col"><font
                                    color="red">*</font>存放位置
                            </th>
                            <th class='tdpadding' style="display: none" scope="col">
                                <font color="red">*</font>固定资产编码+上传图片主键
                            </th>
                            <!-- MM+sap主编码+sap次级编码 -->
                            <th class='tdpadding' scope="col"><font
                                    color="red">*</font>资产编号
                            </th>
                            <th class='tdpadding' scope="col"><font
                                    color="red">*</font>归口管理编号
                            </th>
                            <!-- 手动填 -->
                            <th class='tdpadding' scope="col"><font
                                    color="red">*</font>
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
                                </c:choose>
                            </th>
                            <!-- 手动填 -->
                            <%--<th class='tdpadding' scope="col"><font color="red" >*</font>是否有粘贴资产标签</th>--%>
                            <!-- 手动填 -->
                            <!-- 	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>资产照片</th> -->
                            <!-- 上传/查看 -->
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
                        <c:forEach items="${checkAcceptanceDetaillist }" var="o"
                                   varStatus="status">
                            <tr style="white-space: nowrap;">
                                <td>${status.index+1 }</td>
                                <td>
                                    <a href="javascript:showDemandOrder('${o.demandId}')">${o.demandId}</a>
                                </td>
                                <td>${o.epMaterialdocu}</td>
                                <td>${o.epBelnr}</td>
                                <td>${o.epMatdocumentyear}</td>
                                <td>${o.assetsName}</td>
                                <td>${o.recWarrantyPeriod}</td>
                                <td>${o.storageLocation}</td>
                                <td style="display: none"><input type='text'
                                                                 readonly
                                                                 name='mmAssetsCode'
                                                                 id='mmAssetsCode'
                                                                 value='${o.mmAssetsCode}'>
                                    <input type='text' readonly
                                           name='systeManagementCode'
                                           id='systeManagementCode'
                                           value='${o.systeManagementCode}'></td>
                                <td>${o.sapAssetsCode}</td>
                                <td>${o.manageapplyDepartmentCode}</td>
                                <td>${o.serialNumber}</td>
                                    <%--<td>${o.pasteAssetsTag}</td>--%>
                                <!-- // 是否有粘贴资产标签-->
                                    <%-- 						                                    <td>${o.assetsPhoto}</td> --%>
                                    <%-- 						                                    <td><fmt:formatDate value='${o.deliveryDate}' pattern='yyyy-MM-dd'/></td> --%>
                                    <%-- 						                                    <td><fmt:formatDate value='${o.requireCheckDate}' pattern='yyyy-MM-dd'/></td> --%>
                                <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                    <td>${o.manufactureFactory}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                    <td>${o.managementLevel}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                    <td>${o.accuracyClass}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                    <td>${o.verificationCycle}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                    <td>${o.calibrationDate}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                    <td>${o.nextCalibrationDate}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                    <td>${o.calibrationUnit}</td>
                                </c:if>
                                <td class='tdpadding'><input type='button'
                                                             name='assetsPhoto'
                                                             id='assetsPhoto'
                                                             value='上传/查看'></td>
                                <c:if test="${checkAcceptance.assetsType == '计量器具'}">
                                    <td class='tdpadding' align=center>
                                        <select value='${o.isThirdReport}' disabled
                                                name='isThirdReport'
                                                id='isThirdReport'>
                                            <c:choose>
                                            <c:when test="${o.isThirdReport == '0'}">
                                            <option selected='selected' value='0'>
                                                是
                                            </option>
                                            <option value='1'>否</option>
                                        </select>
                                        <input type='button'
                                               name='otherCalibrationReport'
                                               id='otherCalibrationReport'
                                               value='上传/查看'>
                                        </c:when>
                                        <c:otherwise>
                                            <option value='0'>是</option>
                                            <option selected='selected' value='1'>
                                                否
                                            </option>
                                            </select>
                                            <input type='button'
                                                   name='otherCalibrationReport'
                                                   hidden='false'
                                                   id='otherCalibrationReport'
                                                   value='上传/查看'>
                                        </c:otherwise>
                                        </c:choose>


                                    </td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                    <td>${o.cpu}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                    <td>${o.internalMemory}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                    <td>${o.hardpan}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                    <td>${o.ipAddress}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                    <td>${o.joinDomain}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                    <td>${o.operatingSystem}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                    <td>${o.utilitySystem}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                    <td>${o.description}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == 'IT资产'}">
                                    <td>${o.dataBase}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == '机器设备'}">
                                    <td>${o.shopType}</td>
                                </c:if>
                                <c:if test="${checkAcceptance.assetsType == '行政资产'}">
                                    <td>${o.isBuildingContruction == 'N' ? '否' : '是'}</td>
                                </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                </div>
                </br>
                <div style="width:90%; margin: 0 auto;">
                    <table cellpadding="0" cellspacing="0" border="0" style="width:90%">
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
                                        <table id="uploadTable2" class="adminlist" cellspacing="0" rules="all"
                                               border="1" id="gvDataList2" style="border-collapse:collapse;">
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
                </div>
                <div style="width:90%; margin: 0 auto;">
                    <table cellpadding="0" cellspacing="0" border="0" style="width:90%">
                        <tbody>
                        <tr>
                            <td class="titlefontsize" align="left" style="width: 150px">
                                <div class="imgtitle"></div>
                                验收附件
                            </td>
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

                <c:if test="${checkAcceptance.assetsType == '机器设备'}">
                    <div style="width:90%; margin: 0 auto;">
                        <form id='machineForm'>
                            <table>
                                <tbody id="machineBody">
                                    <%--<c:if test="${checkAcceptance.assetsType == '机器设备'}">--%>
                                <tr>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>1&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>设备资料是否齐全</td>
                                    <td class='tdpadding'>
                                        <select name='machine1' disabled="disabled" id="machine1" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>9&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>设备运行参数是否与使用说明书完全符合</td>
                                    <td class='tdpadding'>
                                        <select name='machine9' disabled="disabled" id="machine9" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>2&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>规格型号是否与合同文件一致</td>
                                    <td class='tdpadding'>
                                        <select name='machine2' disabled="disabled" id="machine2" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>10&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>各性能是否与安装及试使用期时一致</td>
                                    <td class='tdpadding'>
                                        <select name='machine10' disabled="disabled" id="machine10" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>3&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>所有电气是否张贴标签</td>
                                    <td class='tdpadding'>
                                        <select name='machine3' disabled="disabled" id="machine3" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>11&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>配件数量是否与合同一致</td>
                                    <td class='tdpadding'>
                                        <select name='machine11' disabled="disabled" id="machine11" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>4&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>设备铭牌是否清晰可见</td>
                                    <td class='tdpadding'>
                                        <select name='machine4' disabled="disabled" id="machine4" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>12&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>是否有安全隐患</td>
                                    <td class='tdpadding'>
                                        <select name='machine12' disabled="disabled" id="machine12" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>5&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>安全隐患处是否张贴警示标示</td>
                                    <td class='tdpadding'>
                                        <select name='machine5' disabled="disabled" id="machine5" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>13&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>提供的零配件清单是否与机台使用型号相同</td>
                                    <td class='tdpadding'>
                                        <select name='machine13' disabled="disabled" id="machine13" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>6&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>整机可否可以投入试运行</td>
                                    <td class='tdpadding'>
                                        <select name='machine6' disabled="disabled" id="machine6" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>14&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>技术指标参照合同和技术要求进行验收，必要时需要添加附件</td>
                                    <td class='tdpadding'>
                                        <select name='machine14' disabled="disabled" id="machine14" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>7&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>设备是否进行过现场操作和维护保养培训</td>
                                    <td class='tdpadding'>
                                        <select name='machine7' disabled="disabled" id="machine7" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>15&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>生产需要提供设备试作报告</td>
                                    <td class='tdpadding'>
                                        <select name='machine15' disabled="disabled" id="machine15" value=''>
                                            <option value='0'>是</option>
                                            <option value='1'>否</option>
                                            <option value='2'>不适用</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class='tdpadding'>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>8&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td class='tdpadding'>各性能和配置是否与投标文件和技术要求一致</td>
                                    <td class='tdpadding'>
                                        <select name='machine8' disabled="disabled" id="machine8" value=''>
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
                <div style="width:90%; margin: 0 auto;">
                    <table cellpadding="0" cellspacing="0" border="0" width="85%">
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
                                                    <td class="tdleft">${appHistory.examineAdvice}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <%--<tag:paginate offset="${appHistoryPager.offset}" total="${appHistoryPager.total}"
                                                      steps="${appHistoryPager.pageSize}" uri="${appHistoryPager.uri}"
                                                      formId=""/>--%>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 上传照片 s-->
<div id="photoWindow" name="photoWindow" class="easyui-window" title="上传照片" data-options="iconCls:'icon-save'"
     style="width:800px;height:400px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <div style="margin-top: 10px">
                <form action="receiveGoodsHeader" id="receiveGoodsHeaderForm">
                    <table id="addReceiveGoodsHeaderTable" border="0" cellpadding="3px" cellspacing="0" width="90%">
                        <tbody>
                        <tr>

                            <td colspan="4">
                                <input id="fileType" type="hidden">
                                <%--<input id="fileToUpload" type="file" name="multipartFile"/>--%>
                                <%--<input type="button" id="photoSubmit" value="上传"/>--%>
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
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>
<script type="text/javascript">
    var dg = frameElement.lhgDG;//定义dg 关闭需要


    $("#assetsPhoto").live("click", function () { //上传弹框

        photodocument = $(this).parent().parent().find("#systeManagementCode").val();
//        alert(photodocument);
        var top = "70px";//$(this).offset().top - 350;
        var left = "170px";//$(this).offset().left-180;
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
        //alert(photodocument);
        var type = $('#fileType').val();
        //alert(type);
        $.ajax({
            data: {"document": photodocument},
            url: "${dynamicURL}/file/getFilesByDocument",
            dataType: "JSON",
            type: "post",
            success: function (data) {
                var objdataTemp = JSON.parse(data);
                //alert(0);
                var divHtml = "";
                for (var i = 0; i < objdataTemp.length; i++) {
                    if (objdataTemp[i].typeName == type) {
                        divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                        divHtml += "<td class='tdpadding'><input type='text' id='fileName' value='" + objdataTemp[i].fileName + "'> </td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='' value='" + objdataTemp[i].createUserName + "'> </td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='' value='" + objdataTemp[i].createBy + "'> </td>";
                        divHtml += "<td class='tdpadding'><input type='text' name='' value='" + objdataTemp[i].createDate + "'><input type='hidden' name='fileuuid' id='fileuuid' value='" + objdataTemp[i].fileuuid + "'> </td>";
                        divHtml += '<td class="tdpadding"><a href="' + '${dynamicURL}/file/downloadFile?fileuuid=' + objdataTemp[i].fileuuid + '">下载</a></td>';
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
                    // console.log(data);
                    photoAllShow(photodocument);
                },
                error: function () {
                }
            });
        }
    );

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

    function showDemandOrder(id) {
        dg.curWin.showDemandOrder(id);
    }
    function showApplyOrder(id) {
        dg.curWin.showApplyOrder(id);
    }
    function stamp(id, title){
        var lurl = "${dynamicURL}/checkAccept/showStamp?id="+id+"&typeId=77078";
        window.open(lurl);
    }
</script>
</body>
</html>