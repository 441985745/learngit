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
    <title>资产收货单</title>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
    <div id="tabletitle"
         style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
        资产收货单
    </div>
</div>
<div id="Div2" style="margin-left: 10px; margin-top: 0px;">
    <input type="button" name="" onclick="btnReceiveGoods(-1)" value="提交" id="btnSaveReceiveGoods" class="btn">
   <%-- <input type="button" name="" onclick="btnReceiveGoods(-2)" value="保存草稿" id="btnSaveReceiveGoodsDraft" class="btn">--%>
    <input type="button" name="" value="返回" onclick="dg.cancel();" id="" class="btn">
</div>
<%--<div id="Div2" style="margin-left: 0px; margin-top: 0px;">--%>
<%--&lt;%&ndash;<app:approvalTag billCode="${receiveGoods.cdocument}" typeId="77077"></app:approvalTag>&ndash;%&gt;--%>
<%--</div>--%>

<div style="margin-top: 5px;">

    <div id="receiveGoodsTab" class="easyui-tabs" style="width:1000px;padding:10px;">
        <div title="收货信息" id="tabsa" style="overflow:auto; padding:10px;">
            <div>
                <form action="receiveGoods" id="receiveGoodsForm">
                    <table id="addReceiveGoodsTable" border="0" cellpadding="3px" cellspacing="0" width="90%">
                        <tbody>
                        <tr>
                            <td align="right" class="tdtitle">采购员:
                            </td>
                            <td class="tdtext">
                                <input name="buyerName" readonly value="${receiveGoods.buyerName }" type="text" id="buyerName"
                                       class="colorblur">
                                <input name="buyerCode"  readonly value="${receiveGoods.buyerCode }" type="hidden" id="buyerCode"
                                       class="colorblur">
                                <input name="draft"  readonly value="${receiveGoods.draft}" type="hidden" id="draft"
                                       class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">收货编码:
                            </td>
                            <td class="tdtext">
                                <input name="cdocument"  readonly value="${receiveGoods.cdocument}" type="text" id="cdocument"
                                       class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">供应商:
                            </td>
                            <td class="tdtext">
                                <input name="supplierName" readonly  value="${receiveGoods.supplierName }" type="text"
                                       id="supplierName" class="colorblur">
                            </td>
                        </tr>
                        <tr>
                            <td align="right" class="tdtitle">公司名称:
                            </td>
                            <td class="tdtext">
                                <input name="companyName" readonly value="${receiveGoods.companyName }" type="text"
                                       id="companyName" class="colorblur">
                                <input name="companyCode" value="${receiveGoods.companyCode }" type="hidden"
                                       id="companyCode" class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">成本中心:
                            </td>
                            <td class="tdtext">
                                <input name="costCenter"  readonly value="${receiveGoods.costCenter }" type="text" id="costCenter"
                                       class="colorblur">
                                <input name="costCenterCode"  readonly value="${receiveGoods.costCenterCode }" type="hidden"
                                       id="costCenterCode" class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">资产类型:
                            </td>
                            <td class="tdtext">
                                <input name="assetsType"  readonly value="${receiveGoods.assetsType }" type="text" id="assetsType"
                                       class="colorblur">
                            </td>
                        <tr>
                            <td align="right" class="tdtitle">申请部门所属平台:
                            </td>
                            <td class="tdtext">
                                <input name="platform"  readonly value="${receiveGoods.platform }" type="text" id="platform"
                                       class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">采购员备注:
                            </td>
                            <td class="tdtext">
                                <input name="buyerRemark"   value="${receiveGoods.buyerRemark }" type="text"
                                       id="buyerRemark" class="colorblur">
                            </td>

                            <td align="right" class="tdtitle">供应商编码:
                            </td>
                            <td class="tdtext">
                                <input name="supplierCode"  readonly value="${receiveGoods.supplierCode }" type="text"
                                       id="supplierCode" class="colorblur">
                            </td>

                        </tr>
                        <td align="right" class="tdtitle">订单编码:
                        </td>
                        <td class="tdtext">
                            <input name="purchaseId"  readonly value="${receiveGoods.purchaseId}" type="text" id="purchaseId"
                                   class="colorblur">
                        </td>

                        <td align="right" class="tdtitle">抬头文本说明:
                        </td>
                        <td class="tdtext">
                            <input name="headerTextDescription"   value="${receiveGoods.headerTextDescription }"
                                   type="text" id="headerTextDescription" class="colorblur">
                        </td>
                        <!-- 抬头文本说明 （订单头） -->
                        <td align="right" class="tdtitle">合同号:
                        </td>
                        <td class="tdtext">
                            <input name="contractNo"   value="${receiveGoods.contractNo }" type="text" id="contractNo"
                                   class="colorblur">
                        </td>
                        <!-- 合同号（手填） -->
                        </tr>
                        <tr>
                            <td align="right" class="tdtitle">申请部门:
                            </td>
                            <!-- 申请明细 抓取-->
                            <td class="tdtext">
                                <input name="applyDepartment"  readonly value="${receiveGoods.applyDepartment }" type="text"
                                       id="applyDepartment" class="colorblur">
                                <input name="applyDepartmentCode" readonly  value="${receiveGoods.applyDepartmentCode }"
                                       type="hidden" id="applyDepartmentCode" class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">办公地点:
                            </td>
                            <td class="tdtext">
                                <input name="officeLocation" readonly  value="${receiveGoods.officeLocation}" type="text"
                                       id="officeLocation" class="colorblur">
                            </td>
                            <!-- (采购申请的部门 -->
                           <%-- <td align="right" class="tdtitle">需求部门:
                            </td>
                            <td class="tdtext">
                                <input name="budgetDepartmentName" readonly  value="${receiveGoods.budgetDepartmentName }"
                                       type="text" id="budgetDepartmentName" class="colorblur">
                                <input name="budgetDepartmentCode"  readonly value="${receiveGoods.budgetDepartmentCode }"
                                       type="hidden" id="budgetDepartmentCode" class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">办公地点:
                            </td>
                            <td class="tdtext">
                                <input name="officeLocation"  readonly value="${receiveGoods.officeLocation}" type="text"
                                       id="officeLocation" class="colorblur">
                            </td>--%>
                        </tr>
                        </tbody>
                    </table>
                </form>

                </br>
                <div style="width:900px; overflow:scroll;">
                    <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" class="tablenoborder">
                        <tbody>
                        <tr>
                            <td colspan="4">
                                <form action="receiveGoodsDetail" id="receiveGoodsDetailForm">
                                    <table id="Table10" width="880px" border="0" cellpadding="0" cellspacing="0"
                                           style="table-layout: fixed;">
                                        <tbody>
                                        <tr>
                                            <td class="titlefontsize" align="left">
                                                <div class="imgtitle">
                                                </div>
                                                汇总信息
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tdtitle" style="text-align: right" colspan="2">
                                                <div id="div1" class="divGrid"
                                                     style="overflow-x: scroll; overflow-y: visible;">
                                                    <div>
                                                        <table id="table_add_detail1" class="adminlist" cellspacing="0"
                                                               rules="all" border="1" id="gridDataList1"
                                                               style="border-collapse:collapse;">
                                                            <tr style="white-space:nowrap;">
                                                                <th class='tdpadding' scope="col">收货汇总</th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>资产名称
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>单位
                                                                </th><!-- 申请行里取 -->
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>订单数量
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>已收货数量
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>本次收货数量
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>税率
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>单价（含税）
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>总价（含税）
                                                                </th>
                                                                <th class='tdpadding' style="display: none" scope="col"><font
                                                                        color="red">*</font>总账科目
                                                                </th><!-- 申请单带出 -->
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>资产分类
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>交货日期
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>计划验收日期
                                                                </th><!-- 手选的 -->
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>质保周期（月）
                                                                </th>
                                                                <%--
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>存放位置
                                                                </th>--%>
                                                            </tr>
                                                            <tbody id="tbody_add_detail">
                                                            <c:forEach items="${receiveGoodsDetail }" var="o"
                                                                       varStatus="status">
                                                                <tr class="bodyTr" style="white-space: nowrap;">
                                                                    <td style='text-align:center'>${status.index+1}
                                                                    <input type="hidden" name="receiveGoodsDetailId" value="${o.receiveGoodsDetailId}"/>
                                                                    <input type="hidden" name="purchaseDetailId" value="${o.purchaseDetailId}"/>
                                                                    <input type="hidden" name="receiveGoodsId" value="${o.receiveGoodsId}"/>
                                                                    <input type="hidden" name="applyDetailId" value="${o.applyDetailId}"/>
                                                                    <input type="hidden" name="assetsName" value="${o.assetsName}"/>
                                                                    <input type="hidden" name="units" value="${o.units}"/>
                                                                    <input type="hidden" name="applyCount" value="${o.applyCount}"/>
                                                                    <input type="hidden" name="goodsCount" value="${o.goodsCount}"/>
                                                                    <input type="hidden" name="thisGoodsCount" value="${o.thisGoodsCount}"/>
                                                                    <input type="hidden" name="tax" value="${o.tax}"/>
                                                                    <input type="hidden" name="purchaseUnitPrice" value="${o.purchaseUnitPrice}"/>
                                                                    <input type="hidden" name="purchasePrice" value="${o.purchasePrice}"/>
                                                                    <input type="hidden" name="ledgerAccount" value="${o.ledgerAccount}"/>
                                                                    <input type="hidden" name="assetsType" value="${o.assetsType}"/>
                                                                    <input type="hidden" name="deliveryDate" value="<fmt:formatDate value='${o.deliveryDate}'
                                                                                        pattern='yyyy-MM-dd'/>"/>
                                                                    <input type="hidden" name="requireCheckDate" value="<fmt:formatDate value='${o.requireCheckDate}'
                                                                                        pattern='yyyy-MM-dd'/>"/>
                                                                    <input type="hidden" name="recWarrantyPeriod" value="${o.recWarrantyPeriod}"/>
                                                                    </td>
                                                                    <td>${o.assetsName}</td>
                                                                    <td>${o.units}</td>
                                                                    <td>${o.applyCount}</td>
                                                                    <td>${o.goodsCount}</td>
                                                                    <td>${o.thisGoodsCount}</td>
                                                                    <td>${o.tax}</td>
                                                                    <td>${o.purchaseUnitPrice}</td>
                                                                    <td>${o.purchasePrice}</td>
                                                                    <td style="display: none">${o.ledgerAccount}</td>
                                                                    <td>${o.assetsType}</td>
                                                                    <td><fmt:formatDate value='${o.deliveryDate}'
                                                                                        pattern='yyyy-MM-dd'/></td>
                                                                    <td><fmt:formatDate value='${o.requireCheckDate}'
                                                                                        pattern='yyyy-MM-dd'/></td>
                                                                    <td>${o.recWarrantyPeriod}</td>
                                                                    <%--<td class='tdpadding'><input style='width: 100px;' value='${o.storageLocation}' name='storageLocation'  type='text' id='storageLocationLine' class='inputbtnRefViewGray' ><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='cleanDep(this);'><input id='storageLocationShow' class='btnRefView' title='点击打开参照文本框内容' type='button'  value='...'></td>--%>
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
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                </br>
                <div style="width:900px; overflow:scroll;">
                    <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" class="tablenoborder"  >
                        <tbody>
                        <tr>
                            <td class="titlefontsize" align="left">
                                <div class="imgtitle">
                                </div>
                                资产信息
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <form action="receiveGoodsDetail" id="receiveGoodsDetailForm1">
                                    <table id="Table10" width="880px" border="0" cellpadding="0" cellspacing="0"
                                           style="table-layout: fixed;">
                                        <tbody>
                                        <tr>
                                            <td align="center" class="tdtitle" width="50px;">
                                                全局设置存放位置：
                                            </td>
                                            <td class='tdpadding' align="left"><input style='width: 100px;'  name='storageLocation1'  type='text' id='storageLocationLine1' class='inputbtnRefViewGray' >
                                                <input id='btnRefClear1' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='cleanDepAll();'>
                                                <input id='storageLocationShow1' class='btnRefView' title='点击打开参照文本框内容' type='button'  value='...'>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td class="tdtitle" style="text-align: right" colspan="2">
                                                <div id="div1" class="divGrid"
                                                     style="overflow-x: scroll; overflow-y: visible;">
                                                    <div>
                                                        <table id="table_add_detail" class="adminlist" cellspacing="0"
                                                               rules="all" border="1" id="gridDataList"
                                                               style="border-collapse:collapse;">
                                                            <tr style="white-space:nowrap;">
                                                                <th class='tdpadding' scope="col">收货明细</th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>需求单号
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>资产名称
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>资产编号
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>型号规格/品牌
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>功能
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font color="red">*</font>需求部门编码
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font color="red">*</font>需求部门
                                                                <th class='tdpadding' scope="col"><font color="red">*</font>成本中心编码
                                                                <th class='tdpadding' scope="col"><font color="red">*</font>成本中心
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>验收人
                                                                </th>

                                                                <th class='tdpadding' scope="col">存放位置
                                                                </th>
                                                            </tr>
                                                            <tbody id="tbody_add_detail">
                                                            <c:forEach items="${purchaseCommon }" var="o"
                                                                       varStatus="status">
                                                                <tr class="bodyTr" style="white-space: nowrap;">
                                                                    <td style='text-align:center'>${status.index+1 }
                                                                    <input type="hidden" name="id" value="${o.id}"/>

                                                                    <input type="hidden" name="receiveGoodsId" value="${o.receiveGoodsId}"/>
                                                                    <input  type="hidden" name="purchaseId" value="${o.purchaseId}"/>
                                                                    <input  type="hidden" name="purchaseDetailId" value="${o.purchaseDetailId}"/>
                                                                    <input  type="hidden" name="applyDetailId" value="${o.applyDetailId}"/>
                                                                    <input  type="hidden" name="demandId" value="${o.demandId}"/>
                                                                    <input  type="hidden" name="demandDetailId" value="${o.demandDetailId}"/>
                                                                    <input  type="hidden" name="sapAssetsCode" value="${o.sapAssetsCode}"/>
                                                                    <input  type="hidden" name="sapSecAssetCode" value="${o.sapSecAssetCode}"/>
                                                                    <input  type="hidden" name="assetsName" value="${o.assetsName}"/>
                                                                    <input  type="hidden" name="brand" value="${o.brand}"/>
                                                                    <input  type="hidden" name="functions" value="${o.functions}"/>
                                                                    <input  type="hidden" name="cardId" value="${o.cardId}"/>
                                                                    <input  type="hidden" name="budgetDepartmentCode" value="${o.budgetDepartmentCode}"/>
                                                                    </td>
                                                                    <td><a href="javascript:showDemandOrder('${o.demandId}')" >${o.demandId}</a></td>
                                                                    <td>${o.assetsName}</td>

                                                                    <td>${o.sapAssetsCode}</td>
                                                                    <td>${o.brand}</td>
                                                                    <td>${o.functions}</td>
                                                                    <td>${o.budgetDepartmentCode}</td>
                                                                    <td>${o.budgetDepartmentName}</td>
                                                                    <td>${o.costCenterCode}</td>
                                                                    <td>${o.costCenter}</td>
                                                                    <td>
                                                                        <input type='text' size='10'  name='checkApplyUser' id='checkApplyUser' value='${o.checkApplyUser}' >
                                                                        <input type='hidden' name='checkApplyCode' id='checkApplyCode' value='${o.checkApplyCode}' title='checkApplyCode'>
                                                                        <input id='showUserListBtn' class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
                                                                    </td>

                                                                    <td class='tdpadding'><input style='width: 100px;' value='${o.storageLocation}' name='storageLocation'  type='text' id='storageLocationLine' class='inputbtnRefViewGray' ><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='cleanDep(this);'><input id='storageLocationShow' class='btnRefView' title='点击打开参照文本框内容' type='button'  value='...'></td>
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
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                </br>
                <table cellpadding="0" cellspacing="0" border="0"  style="width:825px;">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left" style="width: 150px">
                            <div class="imgtitle"></div>
                            附件
                        </td>
                        <td>
                            <input type="file" name="multipartFile" id="fileToUpload" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
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
        </div>
    </div>
</div>
<div id="storageLocationWindow" name="" class="easyui-window" title="选择存放位置" data-options="iconCls:'icon-save'"
     style="width:750px;height:380px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <input id="isAllOrOnly" type="hidden" value="">
            <form id="storageLocationForm">
                <table class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="0">
                    <tr style="text-align: left">
                        <!-- 				<td >办公地点:</td> -->
                        <!-- 				<td name=""><input type="text" id="searchStorageLocationCode" name="supplierCode"></td> -->
                        <td>存放位置:</td>
                        <td><input name="" id="searchStorageLocationName" type="text"></td>
                    </tr>
            </form>
            <input type="button" value="查询" id="searchStorageLocation"><input type="button" id="sureStorageLocation"
                                                                              value="确定">
            <table id="supplierBudget" class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0"
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
<div id="peopleWindow" name="" class="easyui-window" title="选择验收人员" data-options="iconCls:'icon-save'"
     style="width:750px;height:380px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">

            <form id="searchUserForm">
                <table cellpadding="1" bgcolor="#F9F9F9" class="adminlist">
                    <tbody>
                    <tr>
                        <td height="26" width="20%" style="white-space:nowrap;" align="right">编码：</td>
                        <td>
                            <input name="searchName" id="searchName" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                                   onblur="this.className='inputbtnRefView';" style="width:120px;" value="${param.searchName}">

                        </td>

                        <td height="26" width="20%" style="white-space:nowrap;" align="right">名称：</td>

                        <td>
                            <input name="searchCode" id="searchCode" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                                   onblur="this.className='inputbtnRefView';" style="width:120px;" value="${param.searchCode}">

                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <div id="buttons">
                <input type="button" value="查询" id="queryUserBtn" class="btn">&nbsp;&nbsp;&nbsp;
                <input type="button" id="sureUser" value="确定" class="btn">
            </div>
            <div>
                <table class="adminlist" cellspacing="0" rules="all" border="1" id="userTable" style="border-collapse:collapse;width:100%;">
                    <thead>
                    <th class='tdpadding'></th>
                    <th align="center" scope="col" style="width:30%;">编码</th>
                    <th align="center" scope="col" style="width:70%;">名称</th>
                    </thead>
                    <tbody id="user_body">
                    <%--<c:forEach items="${userPager.records}" var="user" varStatus="status">
                        <tr>
                            <td align="left">${user.userCode}</td>
                            <td align="left">${user.userName}</td>

                        </tr>
                    </c:forEach>--%>
                    </tbody>
                </table>
            </div>

        </div>

    </div>
</div>
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>
<script type="text/javascript">
    var dg = frameElement.lhgDG;//定义dg 关闭需要
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
    })
    function showStorageLocation(storageLocationTemp, officeLocationTemp) { //展示列表存放位置的列表
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
    function cleanDepAll(){
        $("input[name='storageLocation']").val("");
    }
    $('#searchStorageLocation').click(function () {//模糊查询存放位置
        var storageLocationTemp = $('#searchStorageLocationName').val();
        var officeLocationTemp = $('#officeLocation').val();
        showStorageLocation(storageLocationTemp, officeLocationTemp);

    })

    $("#sureStorageLocation").click(function () { //确定存放位置
        if($("#isAllOrOnly").val() == '1') { //单个修改存放位置
            var i = $("input[name='storageLocationRadio']:checked").val();
            if (!i) {
                alert("未选择任何一行数据!");
                return;
            }
            var storageLocationTemp = $.trim($("#storageLocation" + i).html());
            $('#storageLocationWindow').window('close');
            $inputStorageLocation.parent().parent().find('#storageLocationLine').val(storageLocationTemp);
        } else{
            var i = $("input[name='storageLocationRadio']:checked").val();
            if (!i) {
                alert("未选择任何一行数据!");
                return;
            }
            var storageLocationTemp1 = $.trim($("#storageLocation" + i).html());
            $('#storageLocationWindow').window('close');
            $("input[name='storageLocation']").val(storageLocationTemp1);
        }

    });
    function cleanDep(that) {
//        $("#txtYsssbm").val("");
//        $("#txtBudgetDepartment").val("");
//        $("#txtCompany").val("");
//        $("#txtCompanyCode").val("");
//        //$("#txtAssetType1").html("");
//        $("#txtPlatform").val("");
//        $("#txtRegion").val("");
//        $("#txtCostCenter").val("");
//        $("#txtCostCenterCode").val("");
//        $("#txtWorkShopType").val("");
        $(that).prev().val("");

        //清空后设置是否组装等 禁用
        // changeAssetType();
        //清空行明细
        $("#tbody_storageLocation").html("");
        // statusIndex = 0;
        //addRow();
        // sumAll();
    }
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

    $(function(){

    });
    function btnReceiveGoods(num){
        var checkUserFlag = false;
        //头信息
        var receiveGoodsWrapper={};
        // 头
        var objHeader = $('#receiveGoodsForm').serializeObject();
        /*if (-1 == num) {
            objHeader.draft = 'N'; //不是草稿
        } else if (-2 == num) {
            objHeader.draft = 'Y'; //是草稿
        }*/
        receiveGoodsWrapper.receiveGoods = objHeader;
        var objDemandDetail = {
            receiveGoodsDetailId: "",
            purchaseDetailId: "",
            receiveGoodsId: "",
            applyDetailId: "",
            assetsName: "",
            units: "",
            applyCount: "",
            goodsCount: "",
            thisGoodsCount:"",
            tax: "",
            purchaseUnitPrice: "",
            purchasePrice: "",
            ledgerAccount: "",
            assetsType: "",
            deliveryDate: "",
            requireCheckDate: "",
            recWarrantyPeriod: ""
        };
        receiveGoodsWrapper.receiveGoodsDetails=constructObjectFromTable(objDemandDetail, $('#receiveGoodsDetailForm .bodyTr'));

        // //行 对应的sap资产明细
        var objlineSapAsset = {
            id : "",
            receiveGoodsId  : "",
            cardId: "",
            demandId : "",
            demandDetailId: "",
            purchaseId : "",
            purchaseDetailId : "",
            applyDetailId : "",
            storageLocation:"",
            assetsName:"",
            brand:"",
            functions:"",
            checkApplyUser:"",
            sapSecAssetCode:"",
            checkApplyCode:""
        }
        sapAssetTemp = constructObjectFromTable(objlineSapAsset, $("#receiveGoodsDetailForm1 .bodyTr"));
        receiveGoodsWrapper.purchaseCommons = sapAssetTemp; // 传 行对应的需求明细
        //console.log(receiveGoodsWrapper);
        $.ajax({
            type : "POST",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : "${dynamicURL}/receiveGoods/api/updateSaveReceiveGoods1",
            data : JSON.stringify(receiveGoodsWrapper),
            success : function(data) {
                if (data.errorMessages.length > 0) {
                    alert(data.errorMessages);
                    return;
                }else{
                    alert("保存成功");
                    dg.curWin.location.reload();
                    dg.cancel();
                    return;
                }
            }

        })


    }


    var $inputUser = null;
    var userName= null;
    var userCode =null;
    var budgetDepartmentCode = null;
    $('#showUserListBtn').live("click", function () {
        $inputUser = $(this);
        var top = "50px";//$(this).offset().top - 350;
        var left = "70px";//$(this).offset().left-180;
        $('#peopleWindow').window('open').window('resize', {
            width: '900px',
            height: '700px',
            top: top,
            left: left
        });
        userName= $(this).parent().find("input[name='checkApplyUser']").val();
        userCode =$(this).parent().find("input[name='checkApplyCode']").val();
        budgetDepartmentCode=$(this).parent().parent().find("td input[name='budgetDepartmentCode']").val();
        showUser(userName,userCode,budgetDepartmentCode,"","");
    });
    function showUser(userName,userCode,budgetDepartmentCode,searchName,searchCode) { //展示列表存放位置的列表
        $.ajax({
            type: "POST",
            dataType:"json",
            url: "${dynamicURL}/receiveGoods/api/getUserByDepartList",
            data: {"userCode": userCode,"userName":userName,"searchCode":searchCode,"searchName":searchName,"budgetDepartmentCode":budgetDepartmentCode},
            success: function (data) {
                var html = '';
                $.each(data, function (commentIndex, comment) {
                    html += '<tr style="white-space:nowrap;"><td class="tdpadding"><input type="radio" name="userRadio" value=' + commentIndex + '>'
                        + '</td><td class="tdpadding" id=\"userCode' + commentIndex + '\">' + (comment['cuserCode'] == null ? "" : comment['cuserCode'])
                        + '</td><td class="tdpadding" id=\"userName' + commentIndex + '\">' + (comment['cuserName'] == null ? "" : comment['cuserName'])
                        + '</td></tr>';
                });
                $('#user_body').html(html);

            }
        });
    }

    $('#queryUserBtn').click(function () {//模糊查询存放位置
        var searchName = $('#searchName').val();
        var searchCode = $('#searchCode').val();
        showUser(userName,userCode,searchName,searchCode);

    });

    $("#sureUser").click(function () { //确定存放位置
        var i = $("input[name='userRadio']:checked").val();
        if (!i) {
            alert("未选择任何一行数据!");
            return;
        }
        var userCodeTemp = $.trim($("#userCode" + i).html());
        var userNameTemp = $.trim($("#userName" + i).html());
// 		debugger
        $('#peopleWindow').window('close');
        $inputUser.parent().find("input[name='checkApplyUser']").val(userNameTemp);
        $inputUser.parent().find("input[name='checkApplyCode']").val(userCodeTemp);
    });
    $('#btnUpload').click(function () {
        var fileElementId =$(this).prev().attr('id');
        var file = $('#fileToUpload').val();
        if (file == "") {
            alert("请选择需要上传的文件！");
            return false;
        }

        var documentNo = $('#cdocument').val();
//        alert(documentNo);
        if ("" == documentNo) {
            alert('单据号不可为空');
            return false;
        }

        uploadFile('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}', documentNo, $('#uploadTable > tbody'),fileElementId);

    });

    $('#btnDelFile').click(function () {
        deleteFiles('${dynamicURL}', "uploadTable");
    });
    $(function(){
        $.ajax({
            url:'${dynamicURL}/file/getFilesByDocument',
            data:{'document':$("#cdocument").val()},
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
    });
    function showDemandOrder(id){
        dg.curWin.showDemandOrder(id);
    }
    function showApplyOrder(id){
        dg.curWin.showApplyOrder(id);
    }
</script>
</body>
</html>