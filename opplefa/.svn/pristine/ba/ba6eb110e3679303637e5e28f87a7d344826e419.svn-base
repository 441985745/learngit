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
    <title>资产收货单</title>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
<div name="top">
    <input type="hidden" name="hfQueryStatus" id="hfQueryStatus" value="show">
    <table cellspacing="0" cellpadding="0" style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr>
            <td>
                <!-- 收货按钮打开的第一个页面 page1-->
                <form action="" id="purReceiveGoodsForm">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo"
                                 src="${staticURL}/Images/constringency.gif"
                                 style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">基本信息
                        </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0"
                               id="t_ShipInportInfo" class="adminlist"
                               style="visibility: visible;">
                            <tbody>
                            <tr>
                                <td style="text-align: right">资产类型:</td>
                                <td><input readonly id="assetType" type="text"
                                           value="${receiveGoodsMapping.assetsType }"></td>
                                <td style="text-align: right">订单头编码:</td>
                                <td><input readonly type="text" value="${receiveGoodsMapping.purchaseId}"></td>
                            </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </td>
        </tr>
        <tr id="Button">
            <td class="buttonbar">&nbsp;&nbsp;
                <input type="button" name="" value="确定" id="btnAdd" class="btn">
                <input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
            </td>
        </tr>
        <tr>
            <td valign="top">

                <form id="purReceiveGoodsFormDetail">
                    <table class="adminlist" cellspacing="0" rules="all" border="1"
                           id="gvDataList1" style="border-collapse: collapse;">
                        <tbody>
                        <tr style="white-space: nowrap;">
                            <th scope="col">

                            </th>
                            <th scope="col" class="tdpadding"><font color="red">*</font>序号</th>
                            <th scope="col" class="tdpadding"><font color="red">*</font>资产名称</th>
                            <th scope="col" class="tdpadding"><font color="red">*</font>订单数量</th>
                            <th scope="col" class="tdpadding"><font color="red">*</font>已收货数量</th>
                            <th scope="col" class="tdpadding"><font color="red">*</font>本次收货数量</th>
                        </tr>
                        <c:forEach items="${receiveGoodsMappingList }" var="o" varStatus="status">
                            <tr class="bodyTr" style="white-space: nowrap;">
                                <td align="center">
                                    <input id="gvDataList_chkBolDel_0" type="checkbox"
                                           name="gvDataList$ctl02$chkBolDel">
                                </td>
                                <td class="tdpadding">
                                    <input type="text" readonly="readonly" name="" size="1" id=""
                                           value="${status.index +1 }">
                                    <input type="hidden" name="" id="linepurchaseDetailId"
                                           value="${o.purchaseDetailId}" title="purchaseDetailId"><!-- 订单明细编码 -->
                                    <input type="hidden" name="" id="lineapplyDetailId" value="${o.applyDetailId}"
                                           title="applyDetailId"><!--  -->
                                </td>
                                <td class="tdpadding"><input type="text" name="" value="${o.assetsName }"
                                                             readonly="readonly"></td>
                                <td class="tdpadding"><input type="text" name="" value="${o.applyCount }"
                                                             readonly="readonly"></td>
                                <td class="tdpadding"><input readonly type="text" name="" value="${o.goodsCount }">
                                </td>
                                <td class="tdpadding"><input type="text" readonly name="lineThisGoodsCount"
                                                             id="lineThisGoodsCount"
                                                             value="${o.thisGoodsCount }"></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<!-- 收货按钮打开的页面点击确定后弹出的页面page3-->
<!--头     收货单新增  s-->
<div id="addReceiveGoodsWindow" name="addReceiveGoodsWindow" class="easyui-window" title="收货单新增"
     data-options="iconCls:'icon-save'" style="width:900px;height:700px;padding:5px;" closed="true">
    <div style="width:100%; margin: 0 auto;">
        <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
            <input type="button" name="" onclick="btnReceiveGoods(-1)" value="提交" id="btnSaveReceiveGoods"
                   class="btn">
            <input type="button" name="" onclick="btnReceiveGoods(-2)" value="保存草稿" id="btnSaveReceiveGoodsDraft"
                   class="btn">
            <input type="button" name="" value="返回" onclick="" id="btnReceiveGoodsCancel" class="btn">
        </div>
        <form action="receiveGoods" id="receiveGoodsForm">
            <table id="addReceiveGoodsTable" border="0" cellpadding="3px" cellspacing="0" width="90%">
                <tbody>
                <tr>
                    <td align="right" class="tdtitle">采购员:
                    </td>
                    <td class="tdtext">
                        <input name="buyerName" readonly value="${receiveGoodsMapping.buyerName }" type="text"
                               id="buyerName"
                               class="colorblur">
                        <input name="buyerCode" readonly value="${receiveGoodsMapping.buyerCode }" type="hidden"
                               id="buyerCode" class="colorblur">

                    </td>
                    <td align="right" class="tdtitle">收货编码:
                    </td>
                    <td class="tdtext">
                        <input name="receiveGoodsId" v value="${receiveGoodsMapping.receiveGoodsId }" type="text"
                               id="receiveGoodsId" class="colorblur">
                    </td>
                    <td align="right" class="tdtitle">供应商:
                    </td>
                    <td class="tdtext">
                        <input name="supplierName" readonly value="${receiveGoodsMapping.supplierName }" type="text"
                               id="supplierName" class="colorblur">
                    </td>
                </tr>
                <tr>
                    <td align="right" class="tdtitle">公司名称:
                    </td>
                    <td class="tdtext">
                        <input name="companyName" readonly value="${receiveGoodsMapping.companyName }" type="text"
                               id="companyName" class="colorblur">
                        <input name="companyCode" value="${receiveGoodsMapping.companyCode }" type="hidden"
                               id="companyCode" class="colorblur">
                    </td>
                    <td align="right" class="tdtitle">成本中心:
                    </td>
                    <td class="tdtext">
                        <input name="costCenter" readonly value="${receiveGoodsMapping.costCenter }" type="text"
                               id="costCenter" class="colorblur">
                        <input name="costCenterCode" value="${receiveGoodsMapping.costCenterCode }" type="hidden"
                               id="costCenterCode" class="colorblur">
                    </td>
                    <td align="right" class="tdtitle">资产类型:
                    </td>
                    <td class="tdtext">
                        <input name="assetsType" readonly value="${receiveGoodsMapping.assetsType }" type="text"
                               id="assetsType" class="colorblur">
                    </td>
                <tr>
                    <td align="right" class="tdtitle">申请部门所属平台:
                    </td>
                    <td class="tdtext">
                        <input name="platform" readonly value="${receiveGoodsMapping.platform }" type="text"
                               id="platform"
                               class="colorblur">
                    </td>
                    <td align="right" class="tdtitle">采购员备注:
                    </td>
                    <td class="tdtext">
                        <input name="buyerRemark" value="${receiveGoodsMapping.buyerRemark }" type="text"
                               id="buyerRemark" class="colorblur">
                    </td>

                    <td align="right" class="tdtitle">供应商编码:
                    </td>
                    <td class="tdtext">
                        <input name="supplierCode" readonly value="${receiveGoodsMapping.supplierCode }" type="text"
                               id="supplierCode" class="colorblur">
                    </td>

                </tr>
                <td align="right" class="tdtitle">订单编码:
                </td>
                <td class="tdtext">
                    <input name="purchaseId" readonly value="${receiveGoodsMapping.purchaseId}" type="text"
                           id="purchaseId"
                           class="colorblur">
                </td>

                <td align="right" class="tdtitle">抬头文本说明:
                </td>
                <td class="tdtext">
                    <input name="headerTextDescription" value="${receiveGoodsMapping.headerTextDescription }"
                           type="text" id="headerTextDescription" class="colorblur">
                </td>
                <!-- 抬头文本说明 （订单头） -->


                <!-- 合同号（手填） -->
                </tr>
                <tr>
                    <td align="right" class="tdtitle">申请部门:
                    </td>
                    <!-- 申请明细 抓取-->
                    <td class="tdtext">
                        <input name="applyDepartment" readonly value="${receiveGoodsMapping.applyDepartment }"
                               type="text"
                               id="applyDepartment" class="colorblur">
                        <input name="applyDepartmentCode" value="${receiveGoodsMapping.applyDepartmentCode }"
                               type="hidden" id="applyDepartmentCode" class="colorblur">
                    </td>
                    <!-- (采购申请的部门 -->
                    <%--<td align="right" class="tdtitle">需求部门:
                    </td>
                    <td class="tdtext">
                        <input name="budgetDepartmentName"  readonly value="${receiveGoodsMapping.budgetDepartmentName }"
                               type="text" id="budgetDepartmentName" class="colorblur">
                        <input name="budgetDepartmentCode" value="${receiveGoodsMapping.budgetDepartmentCode }"
                               type="hidden" id="budgetDepartmentCode" class="colorblur">
                    </td>
                    --%>
                    <td align="right" class="tdtitle">办公地点:
                    </td>
                    <td class="tdtext">
                        <input name="officeLocation" readonly value="${receiveGoodsMapping.officeLocation}" type="text"
                               id="officeLocation" class="colorblur">
                    </td>
                </tr>
                <tr>
                    <td align="right" class="tdtitle">合同号:
                        <select id="isHaveContractNo" onchange="haveContractNo(this);">
                            <option value="0">请选择</option>
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>

                    </td>
                    <td class="tdtext">
                        <input name="contractNo" style="display: none" size="6"
                               value="${receiveGoodsMapping.contractNo }" type="text" id="contractNo"
                               class="colorblur">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <!--头     收货单新增  e-->

        <!--行     收货单新增  s-->
        <div style="width:90%; margin: 0 auto;">

            <form action="receiveGoodsDetail" id="receiveGoodsDetailForm" >
                <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle">
                            </div>
                            汇总信息
                        </td>
                    </tr>
                    <tr>
                        <td class="tdtitle" style="text-align: left" colspan="2">
                            <div id="div1" class="divGrid"
                                 style="overflow-x: scroll; overflow-y: visible;">
                                <div>
                                    <table id="table_add_detail" class="adminlist" cellspacing="0"
                                           rules="all" border="1" id="gridDataList"
                                           style="border-collapse:collapse;">
                                        <tr style="white-space:nowrap;">
                                            <th class='tdpadding' scope="col">收货汇总</th>
                                            <th class='tdpadding' scope="col"><font color="red">*</font>资产名称
                                            </th>
                                            <th class='tdpadding' scope="col"><font color="red">*</font>单位
                                            </th><!-- 申请行里取 -->
                                            <th class='tdpadding' scope="col"><font color="red">*</font>订单数量
                                            </th>
                                            <th class='tdpadding' scope="col"><font color="red">*</font>已收货数量
                                            </th>
                                            <th class='tdpadding' scope="col"><font color="red">*</font>本次收货数量
                                            </th>
                                            <%--<th class='tdpadding' scope="col"><font color="red">*</font>税率
                                            </th>--%>
                                            <th class='tdpadding' scope="col"><font color="red">*</font>单价（含税）
                                            </th>
                                            <th class='tdpadding' scope="col"><font color="red">*</font>总价（含税）
                                            </th>
                                            <th class='tdpadding' style="display: none" scope="col"><font
                                                    color="red">*</font>总账科目
                                            </th><!-- 申请单带出 -->
                                            <th class='tdpadding' scope="col"><font color="red">*</font>资产分类
                                            </th><!-- 资产分类 -->
                                            <th class='tdpadding' scope="col"><font color="red">*</font>交货日期
                                            </th>
                                            <th class='tdpadding' scope="col"><font color="red">*</font>计划验收日期
                                            </th><!-- 手选的 -->
                                            <th class='tdpadding' scope="col"><font color="red">*</font>质保周期（月）
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
            </form>
        </div>
        <!--行     收货单新增  e-->

        <!-- 资产明细 (sap次级编码拆分)s -->
        <div style="width:90%; margin: 0 auto;">
            <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" class="tablenoborder">
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
                        <form action="receiveGoodsAssetsSap" id="receiveGoodsAssetsSapForm">
                            <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                                   style="table-layout: fixed;">
                                <tbody>
                                <tr>
                                    <td class="tdtitle" style="text-align: left" colspan="2">
                                        <div id="div1" class="divGrid"
                                             style="overflow-x: scroll; overflow-y: visible;">
                                            <div>
                                                <table id="table_add_detail" class="adminlist" cellspacing="0"
                                                       rules="all" border="1" id="gridDataList"
                                                       style="border-collapse:collapse;">
                                                    <tr style="white-space:nowrap;">
                                                        <!-- 	                                                <th class='tdpadding' scope="col">序号</th> -->
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>收货明细
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>需求单号
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>资产名称
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>资产编号
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>品牌/规格/型号
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>功能
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>需求部门编码
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>需求部门
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>成本中心编码
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>成本中心
                                                        </th>
                                                        <th class='tdpadding' scope="col"><font color="red">*</font>验收人
                                                        </th>
                                                        <%--<th class='tdpadding' scope="col"><font color="red">*</font>质保周期（月）--%>
                                                        <%--</th><!-- 手写的 -->--%>
                                                        <th class='tdpadding' scope="col">存放位置
                                                        </th><!-- 手写的 -->
                                                    </tr>
                                                    <tbody id="tbody_add_assets_detail">

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

        <!-- 资产明细 (sap次级编码拆分) e-->

        </br>
        <div style="width:90%; margin: 0 auto;">
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
    </div>

</div>
<!-- 当修改收货数量时打开的窗口 page2-->
<!-- 需求单明细 弹框-->
<div id="addDemandDetailWindow" name="addDemandDetailWindow" class="easyui-window" title="新增收货单需求详情"
     data-options="iconCls:'icon-save'" style="width:800px;height:400px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <div id="Div3" style="margin-left: 0px; margin-top: 0px;">
                <input type="button" name="" value="确定" id="savebtnDemandDetail" class="btn">
                <input type="button" id="btnReceiveGoodsDemandCancel" value="返回" class="btn">

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
                                        <td class="tdtitle" style="text-align: left" colspan="2">
                                            <div id="div1" class="divGrid"
                                                 style="overflow-x: scroll; overflow-y: visible;">
                                                <table id="table_add_detail" class="adminlist" cellspacing="0"
                                                       rules="all" border="1" id="gridDataList"
                                                       style="border-collapse:collapse;">
                                                    <tr style="white-space: nowrap;">
                                                        <!-- 													<th scope="col"> -->
                                                        <!-- 													  <input id="gvDataList_CheckAll" type="checkbox" name="gvDataList$ctl01$CheckAll" -->
                                                        <!-- 	                                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_add_detail');" disabled="disabled"/> -->
                                                        <!-- 													</th> -->

                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>需求单号&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>资产名称&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>资产类型&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>订单需求数量&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>已收货数量&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>本次收货数量&nbsp;&nbsp;
                                                        </th>
                                                        <th scope="col">&nbsp;&nbsp;<font color="red">*</font>项目编码&nbsp;&nbsp;
                                                        </th>
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
<%--<div style="width:900px;height:380px; overflow:scroll;">--%>
<div id="storageLocationWindow" name="" class="easyui-window" title="选择存放位置" data-options="iconCls:'icon-save'"
     style="width:750px;height:380px;padding:5px;" closed="true">
        <div style="width:90%; margin: 0 auto;">
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
</div>
<div id="peopleWindow" name="" class="easyui-window" title="选择验收人员" data-options="iconCls:'icon-save'"
     style="width:750px;height:380px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">

            <form id="searchUserForm">
                <table cellpadding="1" bgcolor="#F9F9F9" class="adminlist">
                    <tbody>
                    <tr>
                        <td height="26" width="20%" style="white-space:nowrap;" align="right">员工名称：</td>
                        <td>
                            <input name="searchName" id="searchName" type="text" class="inputbtnRefView"
                                   onfocus="this.className='colorfocusRefView';"
                                   onblur="this.className='inputbtnRefView';" style="width:120px;"
                                   value="${param.searchName}">

                        </td>

                        <td height="26" width="20%" style="white-space:nowrap;" align="right">员工编号：</td>

                        <td>
                            <input name="searchCode" id="searchCode" type="text" class="inputbtnRefView"
                                   onfocus="this.className='colorfocusRefView';"
                                   onblur="this.className='inputbtnRefView';" style="width:120px;"
                                   value="${param.searchCode}">

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
                <table class="adminlist" cellspacing="0" rules="all" border="1" id="userTable"
                       style="border-collapse:collapse;width:100%;">
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
<%-- 	<script src="${staticURL}/Scripts/Purchase/ReceiveGoods/ReceiveGoods.js" type="text/javascript"></script> --%>
<script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>
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
    var proveNum = 0;
    //     页面加载完后将需求明细全都获得 ---s--
    var purchaseApplyMappingList = {}; // 全部需求
    $(document).ready(function () {
        var purchaseId = $('#purchaseId').val();
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/receiveGoods/api/searchPurchaseApplyMappingByPurchaseId",
            data: {"purchaseId": purchaseId},
            success: function (data) {
                purchaseApplyMappingList = data;

            }
        });
    });
    //     页面加载完后将需求明细全都获得---e--

    var $thisInput;
    //  收货  行的数量 更改 弹框 --需求行明细显示  --s--
    $('#lineThisGoodsCount').live("click", function () {
        $thisInput = $(this);
        //alert($thisInput.val());
        var top = "50px";//$(this).offset().top - 350;
        var left = "70px";//$(this).offset().left-180;
        $('#addDemandDetailWindow').window('open').window('resize', {
            width: '800px',
            height: '400px',
            top: top,
            left: left
        });
        var lineapplyDetailId = $(this).parent().parent().find('#lineapplyDetailId').val();
        var linepurchaseDetailId = $(this).parent().parent().find("#linepurchaseDetailId").val();
        var divHtml = "";
        for (var i = 0; i < purchaseApplyMappingList.length; i++) {
            //当点击修改收货数量时，获得此行明细的申请单明细ID，当list[i].applyDetailId等于申请单明细时，予以展示。
            if (lineapplyDetailId == purchaseApplyMappingList[i].applyDetailId && linepurchaseDetailId == purchaseApplyMappingList[i].purchaseDetailId) {
                divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                /* receiveGoodsId */
                /* purchaseDetailId */
                /* applyDetailId */
                divHtml += "<td class='tdpadding'><input type='text' size='18' readonly='readonly' name='demandId' id='demandId' value='" + purchaseApplyMappingList[i].demandId + "' readonly='readonly'><input type='hidden' name='demandDetailId' id='demandDetailId' value='" + purchaseApplyMappingList[i].demandDetailId + "' title='demandDetailId'></td>";
                divHtml += "<td class='tdpadding'><input type='text' size='12' readonly='readonly' name='assetsName' value='" + purchaseApplyMappingList[i].assetsName + "'>" +
                    "<input type='hidden' name='purchaseDetailId' id='' value='" + purchaseApplyMappingList[i].purchaseDetailId + "' title='purchaseDetailId'><input type='hidden' name='applyDetailId' value='" + purchaseApplyMappingList[i].applyDetailId + "' title='applyDetailId'> </td>";
                divHtml += "<td class='tdpadding'><input type='text' size='6' readonly='readonly' name='assetsType' id='assetsType' value='" + purchaseApplyMappingList[i].assetsType + "' readonly='readonly'><input type='hidden' name='sapMainAssetCode' value='" + purchaseApplyMappingList[i].sapMainAssetCode + "'><input type='hidden' name='sapSecAssetCode' value='" + purchaseApplyMappingList[i].sapSecAssetCode + "'><input type='hidden' name='demandCount' value='" + purchaseApplyMappingList[i].demandCount + "'>  </td>";
                divHtml += "<td class='tdpadding'><input type='text'size='10' readonly='readonly' name='applyCountDemand' id='applyCountDemand' value='" + purchaseApplyMappingList[i].applyCountDemand + "' readonly='readonly'></td>";
                divHtml += "<td class='tdpadding'><input type='text' size='8' readonly='readonly' name='goodsCountDemand' id='goodsCountDemand' value='" + purchaseApplyMappingList[i].goodsCountDemand + "' ></td>";
                divHtml += "<td class='tdpadding'><input type='text' size='10' name='thisGoodsCountDemand' id='thisGoodsCountDemand' value='" + purchaseApplyMappingList[i].thisGoodsCountDemand + "' ></td>";
                divHtml += "<td class='tdpadding'><input type='text' size='18' readonly='readonly' name='projectCode' id='projectCode' value='" + purchaseApplyMappingList[i].projectCode + "' ></td>";
                divHtml += "<td class='tdpadding'><input type='text' size='10' readonly='readonly' name='brand' id='brand' value='" + purchaseApplyMappingList[i].brand + "' ></td>";
                divHtml += "<td class='tdpadding'><input type='text' size='10' readonly='readonly' name='functions' id='functions' value='" + purchaseApplyMappingList[i].functions + "' ></td>";
                divHtml += "</tr>";
            }
            //修改收货数量弹窗page2   赋值
            //并在隐藏域中存放demandDetailId、applyDetailId、purchaseDetailId、sapMainAssetCode、sapSecAssetCode、demandCount
            $("#tbody_demand_detail").html(divHtml);
        }
    });

    //收货  行的数量 更改 弹框 --需求行明细显示  --e--

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
    /*
     *彈出page3
     * /
     /* 第二个弹出窗口  新增收货单 -- 确定（对订单   行  进行收货，弹出收货 头  行   资产明细     ）*/
    $('#btnAdd').click(function (page) {

        var num = $(":input[name=gvDataList$ctl02$chkBolDel][checked]").length;
        if (num < 1) {
            alert("请选中收货行");
            return;
        }
        var count = $(":input[name=gvDataList$ctl02$chkBolDel][checked]").parent().parent().find("td input[name='lineThisGoodsCount']").val();
        if (count == 0) {
            alert("收货数量不能为0");
            return;
        }
//        if (parseInt(1) != parseInt(num)) {
//            alert("仅能选中一项!")
//            return;
//        }

        var top = "50px";//$(this).offset().top - 350;
        var left = "70px";//$(this).offset().left-180;
        $('#addReceiveGoodsWindow').window('open').window('resize', {
            width: '900px',
            height: '700px',
            top: top,
            left: left
        });
        var purchaseId = $('#purchaseId').val();
        var linepurchaseDetailIds = [];
        var lineapplyDetailIds = [];
        var lineThisGoodsCounts = [];
        $('input[name=gvDataList$ctl02$chkBolDel]:checked ').each(function () { //被选中的各种id数组
            linepurchaseDetailIds.push($(this).parent().parent().find('#linepurchaseDetailId').val());
            lineapplyDetailIds.push($(this).parent().parent().find('#lineapplyDetailId').val());
            lineThisGoodsCounts.push($(this).parent().parent().find('#lineThisGoodsCount').val());

        });
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/receiveGoods/api/addReceiveGoodsLine",
            data: {"purchaseId": purchaseId},
            success: function (data) {
                var receiveGoodsMappingLine = data[0]; //需要查质保周期
                var sapAssetsCodeETZMM = data[1];
                /* 日期  -s*/
//        		交货日期默认为当天日，可修改
// 				验收日期 = 交货日期+30天（非计量器具），可修改
// 				验收日期 = 交货日期+45天（计量器具），可修改
                var assetsTypeTemp = $('#assetsType').val();
                var requireCheckDateTemp = null;

                var myDate = new Date();  //获取当前时间
                var deliveryDateTemp = (myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate()); //交货日期

                if (assetsTypeTemp == '计量器具') { //
                    myDate.setDate(myDate.getDate() + 45);//这里的60就是你要加的天数，减也可以。年、月会相应加上去，值得注意的是date.getMonth()得到的月份比实际月份小1，所以实际月份是(date.getMonth()+1)
                    requireCheckDateTemp = (myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate()); //交货日期

                } else {
                    myDate.setDate(myDate.getDate() + 30);//这里的60就是你要加的天数，减也可以。年、月会相应加上去，值得注意的是date.getMonth()得到的月份比实际月份小1，所以实际月份是(date.getMonth()+1)
                    requireCheckDateTemp = (myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate()); //交货日期
                }
                /* 日期  -e*/

                /* 收货单新增 行 弹框  s */
                var divHtml = "";
                for (var i = 0; i < receiveGoodsMappingLine.length; i++) {
                    for (var j = 0; j < lineapplyDetailIds.length; j++) {
                        if (lineapplyDetailIds[j] == receiveGoodsMappingLine[i].applyDetailId) {
                            divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
                            divHtml += "<td style='text-align:center'>" + (i + 1) +
                                "<input type='hidden' name='purchaseId' id='purchaseId' value='" + receiveGoodsMappingLine[i].purchaseId + "' title='purchaseId'>" +
                                "<input type='hidden' name='purchaseDetailId' id='purchaseDetailId' value='" + receiveGoodsMappingLine[i].purchaseDetailId + "' title='purchaseDetailId'>" +
                                "<input type='hidden' name='applyDetailId' id='applyDetailId' value='" + receiveGoodsMappingLine[i].applyDetailId + "' title='applyDetailId' >" +
                                "<input type='hidden' name='applyOrderId' id='applyOrderId' value='" + receiveGoodsMappingLine[i].applyOrderId + "' title='applyOrderId' >" +
                                "<input type='hidden' name='demandOrderId' id='demandOrderId' value='" + receiveGoodsMappingLine[i].demandOrderId + "' title='demandOrderId' >" +
                                "<input type='hidden' name='specificationParameter' id='specificationParameter' value='" + receiveGoodsMappingLine[i].specificationParameter + "' title='specificationParameter' >" +
                                "<input type='hidden' name='periodOfDepreciation' id='periodOfDepreciation' value='" + receiveGoodsMappingLine[i].periodOfDepreciation + "' title='periodOfDepreciation' >" +
                                "<input type='hidden' name='netSalvage' id='netSalvage' value='" + receiveGoodsMappingLine[i].netSalvage + "' title='netSalvage' >" +
                                "</td>";
                            divHtml += "<td class='tdpadding'><input type='text' size='10' readonly name='assetsName' id='assetsName' value='" + receiveGoodsMappingLine[i].assetsName + "' ></td>";
                            divHtml += "<td class='tdpadding'><input type='text' size='1' readonly name='units' id='units' value='" + receiveGoodsMappingLine[i].units + "'  ></td>";
                            divHtml += "<td class='tdpadding'><input type='text' size='4' readonly  name='applyCount' id='applyCount' value='" + receiveGoodsMappingLine[i].applyCount + "' ></td>";
                            divHtml += "<td class='tdpadding'><input type='text'  size='5' readonly name='goodsCount' id='goodsCount' value='" + receiveGoodsMappingLine[i].goodsCount + "' ></td>";
                            divHtml += "<td class='tdpadding'><input type='text'  size='7' readonly name='thisGoodsCount' id='thisGoodsCount' value='" + lineThisGoodsCounts[j] + "' ></td>";
//                            divHtml += "<td class='tdpadding'><input type='text' name='tax' id='tax' value='" + receiveGoodsMappingLine[i].tax + "' ></td>";
                            divHtml += "<td class='tdpadding'><input type='text'  size='7' readonly name='purchaseUnitPrice' id='purchaseUnitPrice' value='" + receiveGoodsMappingLine[i].purchaseUnitPrice + "' ></td>";
                            divHtml += "<td class='tdpadding'><input type='text'  size='7' readonly name='purchasePrice' id='purchasePrice' value='" + receiveGoodsMappingLine[i].purchasePrice + "' ></td>";
                            divHtml += "<td style='display: none'><input type='text'  size='10'  name='ledgerAccount' id='ledgerAccount' value='" + receiveGoodsMappingLine[i].ledgerAccount + "' ></td>";
                            divHtml += "<td class='tdpadding'>" +
                                "<select id='assetsType' name='assetsType' >" +
                                "<option value = '" + receiveGoodsMappingLine[i].assetsType + "'>" + receiveGoodsMappingLine[i].assetsType + "</option>" +
                                "</select>" +
                                "</td>"
                            divHtml += "<td class='tdpadding'><input type='text' size='2' readonly name='deliveryDate' id='deliveryDate' value='" + deliveryDateTemp + "' class='Wdate'  ></td>";
                            divHtml += "<td class='tdpadding'><input type='text'  size='2'  name='requireCheckDate' id='requireCheckDate'value='" + requireCheckDateTemp + "' class='Wdate' onclick='WdatePicker({})' ></td>"; // 颜色还没确定 //要求验收日期
                            /* 存放位置，手填 */
                            /*质保周期 recWarrantyPeriod*/
                            divHtml += "<td class='tdpadding'><input type='text' size='4' readonly name='recWarrantyPeriod' id='recWarrantyPeriod' value='" + receiveGoodsMappingLine[i].recWarrantyPeriod + "' ></td>";
                            divHtml += "</tr>";
// 						<td><fmt:formatDate value='${Order.applyDate}' pattern='yyyy-MM-dd'/></td>
                        }
                    }
                }
                $("#tbody_add_detail").html(divHtml);
                /* 收货单新增 弹框  e */

                var assetType = $("#assetType").val();

                $("select[name='assetsType']").each(function () {

//                        if ("IT资产" != assetType && "电脑类" != assetType && "非电脑类" != assetType) {
                    $(this).append("<option value='" + assetType + "'>" + assetType + "</option>");
                    $(this).val(assetType);
                    $(this).attr("disabled", "disabled");

//                        }

//                        else if (null == $(this).val() || "" == $(this).val() || "null" == $(this).val()) {
//                            $(this).empty();
//
//                            $(this).append("<option value=''></option>");
//                            $(this).append("<option value='电脑类'>电脑类</option>");
//                            $(this).append("<option value='非电脑类'>非电脑类</option>");
//                        }

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
                    }

                });

                /* 存放位置弹框 e*/


                /* sap资产次级编码   s  */
                var divHtmlAssets = "";
                var serNum = 0;
                for (var i = 0; i < linepurchaseDetailIds.length; i++) { //循环是判断选定了哪个订单行
                    for (var j = 0; j < purchaseApplyMappingList.length; j++) { // 循环拿到本次收货对应的需求行和订单行
                        var numTemp = 0;
                        if (linepurchaseDetailIds[i] == purchaseApplyMappingList[j].purchaseDetailId) { //显示选定的订单行
// 					 console.log("////="+purchaseApplyMappingList[j].demandDetailId+"///"+purchaseApplyMappingList[j].thisGoodsCountDemand+"///"+purchaseApplyMappingList[j].purchaseDetailId);
                            for (var n = 0; n < sapAssetsCodeETZMM.length; n++) { // 循环所有的sap次级编码
                                if ((sapAssetsCodeETZMM[n].demandDetailId == purchaseApplyMappingList[j].demandDetailId)) { // 显示选定的需求行的
                                    numTemp += (j + 1) / (j + 1);
                                    if ((numTemp / (purchaseApplyMappingList[j].thisGoodsCountDemand)) <= 1) { // 显示的sap次级编码的个数 等于 本次收货时的对应的需求行
// 							console.log(sapAssetsCodeETZMM[n].demandDetailId+"///"+sapAssetsCodeETZMM[n].sapAssetsCode);
                                        serNum++;
                                        proveNum++;
                                        divHtmlAssets += "<tr class='bodyTr' style='white-space:nowrap;'>";
                                        divHtmlAssets += "<td style='text-align:center'>" + serNum;
                                        divHtmlAssets += "<td class='tdpadding'>" + '<a href="javascript:showDemandOrder(\'' + sapAssetsCodeETZMM[n].demandId + '\')" >' + sapAssetsCodeETZMM[n].demandId + '</a>' +
                                            "<input type='hidden' size='18' readonly name='demandId' id='demandId' value='" + sapAssetsCodeETZMM[n].demandId + "' ><input type='hidden' name='demandDetailId' id='demandDetailId' value='" + sapAssetsCodeETZMM[n].demandDetailId + "' title='demandDetailId' ><input type='hidden' name='purchaseId' id='purchaseId' value='" + sapAssetsCodeETZMM[n].purchaseId + "' title='purchaseId' >" +
                                            "<input type='hidden' name='purchaseDetailId' id='purchaseDetailId' value='" + sapAssetsCodeETZMM[n].purchaseDetailId + "' title='purchaseDetailId' ><input type='hidden' name='applyDetailId' id='applyDetailId' value='" + sapAssetsCodeETZMM[n].applyDetailId + "' title='applyDetailId' ></td>";
                                        divHtmlAssets += "<td class='tdpadding'><input type='text' size='14' readonly name='assetsName' id='assetsName' value='" + sapAssetsCodeETZMM[n].assetsName + "' ></td>";
                                        divHtmlAssets += "<td class='tdpadding'><input type='text' size='18' readonly name='sapAssetsCode' id='sapAssetsCode' value='" + sapAssetsCodeETZMM[n].sapAssetsCode + "' ><input type='hidden' name='sapMainAssetCode' id='sapMainAssetCode' value='" + sapAssetsCodeETZMM[n].sapMainAssetCode + "' title='sapMainAssetCode' ><input type='hidden' name='sapSecAssetCode' id='sapSecAssetCode' value='" + sapAssetsCodeETZMM[n].sapSecAssetCode + "' title='sapSecAssetCode' ></td>";
                                        divHtmlAssets += "<td class='tdpadding'><input type='text' size='10' readonly name='brand' id='brand' value='" + sapAssetsCodeETZMM[n].brand + "' ></td>";
                                        divHtmlAssets += "<td class='tdpadding'><input type='text' size='10' readonly name='functions' id='functions' value='" + sapAssetsCodeETZMM[i].functions + "' ></td>";
                                        divHtmlAssets += "<td class='tdpadding'><input type='text' size='12' readonly name='budgetDepartmentCode' id='budgetDepartmentCode' value='" + sapAssetsCodeETZMM[n].budgetDepartmentCode + "' >";
                                        divHtmlAssets += "<td class='tdpadding'><input type='text' size='12' readonly name='budgetDepartmentName' id='budgetDepartmentName' value='" + sapAssetsCodeETZMM[n].budgetDepartmentName + "' >";
                                        divHtmlAssets += "<td class='tdpadding'><input type='text' size='12' readonly name='costCenterCode' id='costCenterCode' value='" + sapAssetsCodeETZMM[n].costCenterCode + "' >";
                                        divHtmlAssets += "<td class='tdpadding'><input type='text' size='12' readonly name='costCenter' id='costCenter' value='" + sapAssetsCodeETZMM[n].costCenter + "' >";
                                        divHtmlAssets += "<td class='tdpadding'><input type='text' size='10' readonly name='checkApplyUser' id='checkApplyUser' value='" + sapAssetsCodeETZMM[n].checkApplyUser + "' >" +
                                            "<input type='hidden' name='checkApplyCode' id='checkApplyCode' value='" + sapAssetsCodeETZMM[n].checkApplyCode + "' title='checkApplyCode'>" +
                                                /*"<input id='gvdMeeting_btnClearsk_0' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='ClearObj(this);'>" +*/
                                            "<input id='showUserListBtn' class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'></td>";
//                                        divHtmlAssets += "<td class='tdpadding'><input type='text'  readonly name='recWarrantyPeriod' id='recWarrantyPeriod' value='' ></td>";
                                        divHtmlAssets += "<td class='tdpadding'><input style='width: 100px;'  name='storageLocation'  type='text' id='storageLocationLine' class='inputbtnRefViewGray' >" +
                                            "<input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='cleanDep(this);'>" +
                                            "<input id='storageLocationShow' class='btnRefView' title='点击打开参照文本框内容' type='button'  value='...'></td>";
                                        divHtmlAssets += "</tr>";
                                    }
                                }
                            }
                        }
                    }
                }
                $("#tbody_add_assets_detail").html(divHtmlAssets);

            }
        });
    });
    /* sap资产次级编码   e  */

    // 确定需求
    var purchaseTemp = {};
    //修改收货数量窗口page2的确定按钮绑定点击事件
    $('#savebtnDemandDetail').click(function () {
        var flag = true;
        /*var flag1= true;*/
        var flag2 = true;
        //修改收货数量不能大于订单需求数量
        $("input[name='thisGoodsCountDemand']").each(function () {
            var nowNum = $(this).val();
            if (!/^\d+$/.test($(this).val())) {
                flag2 = false;
                return;
            }
            /*if(nowNum < 0) {
             flag1 = false;
             return;
             }*/
            var applyCountDemand = $(this).parent().parent().find("input[name='applyCountDemand']").val();
            if (nowNum - applyCountDemand > 0) {
                flag = false;
                return;
            }
        });
        if (!flag2) {
            alert("请输入整数");
            return;
        }
        /*if(!flag1){
         alert("收货数量不能小于1");
         return;
         }*/
        if (!flag) {
            alert('收货数量不能大于申请数量');
            return;
        }
        var objDemandDetail = {
            receiveGoodsId: "",
            purchaseDetailId: "",
            applyDetailId: "",
            demandDetailId: "",
            assetsName: "",
            assetsType: "",
            applyCountDemand: "",
            goodsCountDemand: "",
            thisGoodsCountDemand: "",
            projectCode: "",
            brand: "",
            demandCount: "",
            sapMainAssetCode: "",
            sapSecAssetCode: ""
        };
        //将修改的行明细转为对象数组
        purchaseTemp = constructObjectFromTable(objDemandDetail, $("#addDemandDetailForm .bodyTr"));
        var thisGoodsCountTemp = 0;
        for (var i = 0; i < purchaseApplyMappingList.length; i++) {

            for (var j = 0; j < purchaseTemp.length; j++) {

                if (purchaseApplyMappingList[i].demandDetailId == purchaseTemp[j].demandDetailId) {
                    purchaseApplyMappingList[i].thisGoodsCountDemand = purchaseTemp[j].thisGoodsCountDemand;//thisGoodsCountDemand
                    thisGoodsCountTemp += parseInt(purchaseTemp[j].thisGoodsCountDemand);

                }
            }
        }
        $thisInput.val(thisGoodsCountTemp); //

        $('#addDemandDetailWindow').window('close');

    })

    function btnReceiveGoods(num) { // 提交
        if (proveNum == 0) {
            alert("没有明细行，不能提交");
            return;
        }
        if ("1" == $("#isHaveContractNo").val() && "" == $("#isHaveContractNo").next().val()) {
            alert("请输入合同号");
            return;
        }
        var flag1 = false;
        $("input[name='requireCheckDate']").each(function () {
            var requireCheckDate1 = $(this).val();
            var deliveryDate1 = $(this).parent().parent().find("td input[name='deliveryDate']").val();

            if (compareDate(deliveryDate1, requireCheckDate1)) {
                flag1 = true;
            } else {
                flag1 = false;
            }
        });
        if (flag1) {
            alert("计划验收日期不能小于收货日期");
            return;
        }
        /*var flag = 'true';
         $("input[name='storageLocation']").each(function(){
         if($(this).val()==null || $(this).val()==""){
         flag = 'false';
         return;
         }
         });
         if (flag=='false'){
         alert("请选择存放位置");
         return;
         }*/

        $('#btnSaveReceiveGoods').val("提交ing...")
        $('#btnSaveReceiveGoods').attr('disabled', true);
        $('#btnReceiveGoodsCancel').attr('disabled', true);
        $('#btnSaveReceiveGoodsDraft').attr('disabled', true);


        var receiveGoodsWrapper = {};
        // 头
        var objHeader = $('#receiveGoodsForm').serializeObject();
        if (-1 == num) {
            objHeader.draft = 'N'; //不是草稿
        } else if (-2 == num) {
            objHeader.draft = 'Y'; //是草稿
        }
        receiveGoodsWrapper.receiveGoods = objHeader; //传 头
        // 行
        var objlinedetail = {
            receiveGoodsId: "",
            purchaseId: "",
            purchaseDetailId: "",
            applyDetailId: "",
            applyOrderId: "",
            demandOrderId: "",
            assetsName: "",
            assetsType: "",
            units: "",
            applyCount: "",
            goodsCount: "",
            thisGoodsCount: "",
            tax: "",
            purchaseUnitPrice: "",
            purchasePrice: "",
            ledgerAccount: "",
            deliveryDate: "",
            requireCheckDate: "",
            recWarrantyPeriod: "",// 质保周期
            //storageLocation: "",//存放位置
            specificationParameter: "",
            periodOfDepreciation: "",
            netSalvage: ""
        }
        var objArrReceiveGoodsDetail = constructObjectFromTable(objlinedetail, $('#receiveGoodsDetailForm .bodyTr'));
        for (var i = 0; i < objArrReceiveGoodsDetail.length; i++) {//因为此处详情没有收获编码（收获单据号），所以赋值
            objArrReceiveGoodsDetail[i].receiveGoodsId = $('#receiveGoodsId').val();//有几行就赋值几个收货单据号
        }
        receiveGoodsWrapper.receiveGoodsDetails = objArrReceiveGoodsDetail; //传 行  缺少质保周期

// 			行 对应的需求明细
        for (var i = 0; i < purchaseApplyMappingList.length; i++) {
            purchaseApplyMappingList[i].receiveGoodsId = $('#receiveGoodsId').val();
        }
        receiveGoodsWrapper.receiveGoodsMappings = purchaseApplyMappingList; // 传 行对应的需求明细 (传的是全部的需求明细行，后台判断选定的订单行对应的需求明细行)
        // 行 对应的sap资产明细
        var objlineSapAsset = {
            receiveGoodsId: "",
            demandId: "",
            demandDetailId: "",
            purchaseId: "",
            purchaseDetailId: "",
            applyDetailId: "",
            applyOrderId: "",
            demandOrderId: "",
            assetsName: "",
            assetsType: "",
            sapMainAssetCode: "",
            sapSecAssetCode: "",
            sapAssetsCode: "",
            brand: "",
            functions: "",
            checkApplyUser: "",
            checkApplyCode: "",
            recWarrantyPeriod: "",
            storageLocation: "",
            costCenter: "",
            costCenterCode: "",
            budgetDepartmentCode: "",
            budgetDepartmentName: ""
        }
        sapAssetTemp = constructObjectFromTable(objlineSapAsset, $("#receiveGoodsAssetsSapForm .bodyTr"));
        for (var i = 0; i < sapAssetTemp.length; i++) {
            sapAssetTemp[i].receiveGoodsId = $('#receiveGoodsId').val();
        }
        receiveGoodsWrapper.purchaseCommons = sapAssetTemp; // 传 行对应的需求明细

        $.ajax({
            type: "POST",
            dataType: "json",
            cache: false,
            contentType: "application/json; charset=utf-8",
            url: "${dynamicURL}/receiveGoods/api/saveReceiveGoods",
            data: JSON.stringify(receiveGoodsWrapper),
            success: function (data) {
                if (data.errorMessages.length > 0) {
                    alert(data.errorMessages);
                    $('#btnSaveReceiveGoods').val("提交")
                    $('#btnSaveReceiveGoods').attr('disabled', false);
                    $('#btnReceiveGoodsCancel').attr('disabled', false);
                    $('#btnSaveReceiveGoodsDraft').attr('disabled', false);

                    return;
                } else {
                    if (objHeader.draft == 'Y') {
                        alert("保存成功");
                    } else {
                        alert(data.successMessage);
                    }
//                    dg.curWin.location.reload();
                    dg.curWin.location = dg.curWin.location;
                    return;
                }
            }

        });

// 	 		$('#addReceiveGoodsWindow').window('close');
//                dg.cancel();
    }

    /* 收货单新增 弹框  返回 s*/
    $('#btnReceiveGoodsCancel').click(function () {
        $('#addReceiveGoodsWindow').window('close');
    });

    $('#btnReceiveGoodsDemandCancel').click(function () {
        $('#addDemandDetailWindow').window('close');
    });

    //清除根据成本中心带出的信息
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
    function cleanDepAll() {
        $("input[name='storageLocation']").val("");
    }
    function ClearObj(that) {
        $(that).parent().find("input[name='checkApplyUser']").val("");
        $(that).parent().find("input[name='checkApplyCode']").val("");
    }
    //选择调拨后负责人

    var $inputUser = null;
    var userName = null;
    var userCode = null;
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
        userName = $(this).parent().find("input[name='checkApplyUser']").val();
        userCode = $(this).parent().find("input[name='checkApplyCode']").val();
        budgetDepartmentCode = $(this).parent().parent().find("td input[name='budgetDepartmentCode']").val();
        showUser(userName, userCode, budgetDepartmentCode, "", "");
    });
    function showUser(userName, userCode, budgetDepartmentCode, searchName, searchCode) { //展示列表存放位置的列表
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "${dynamicURL}/receiveGoods/api/getUserByDepartList",
            data: {
                "userCode": userCode,
                "userName": userName,
                "searchCode": searchCode,
                "searchName": searchName,
                "budgetDepartmentCode": budgetDepartmentCode
            },
            success: function (data) {
                //console.log(data);
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
        showUser(userName, userCode, budgetDepartmentCode, searchName, searchCode);

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
    //比较日期字符串大小 目前只支持年－月－日这样的格式
    function compareDate(DateOne, DateTwo) {
        var OneMonth = DateOne.substring(5, DateOne.lastIndexOf("-"));
        var OneDay = DateOne.substring(DateOne.length, DateOne.lastIndexOf("-") + 1);
        var OneYear = DateOne.substring(0, DateOne.indexOf("-"));
        var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf("-"));
        var TwoDay = DateTwo.substring(DateTwo.length, DateTwo.lastIndexOf("-") + 1);
        var TwoYear = DateTwo.substring(0, DateTwo.indexOf("-"));
        if (Date.parse(OneMonth + "/" + OneDay + "/" + OneYear) > Date.parse(TwoMonth + "/" + TwoDay + "/" + TwoYear)) {
            return true;
        } else {
            return false;
        }
    }
    function haveContractNo(that) {
        //alert($(that).val());
        var isHaveContractNo = $("#isHaveContractNo").val();
        if ('1' == isHaveContractNo) {
            $("#contractNo").attr("style", "display: inline;");
        } else {
            $("#contractNo").val("");
            $("#contractNo").attr("style", "display: none;");
        }
    }

    $('#btnUpload').click(function () {
        var fileElementId = $(this).prev().attr('id');
        var file = $('#fileToUpload').val();
        if (file == "") {
            alert("请选择需要上传的文件！");
            return false;
        }

        var documentNo = $('#receiveGoodsId').val();
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
    function showDemandOrder(id) {
        dg.curWin.showDemandOrder(id);
    }
    function showApplyOrder(id) {
        dg.curWin.showApplyOrder(id);
    }
</script>
</body>
</html>