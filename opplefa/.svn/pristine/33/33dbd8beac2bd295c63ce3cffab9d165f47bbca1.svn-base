<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>审批待办</title>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
    <style type="text/css">
        #page-selection ul li {
            display: inline;
        }
    </style>
</head>
<body>
<div>
    <input type="hidden" name="hfQueryStatus" id="hfQueryStatus" value="show">
    <table cellspacing="0" cellpadding="0" style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form action="purchaseApplyOrder" id="purchaseApplyOrderForm">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif"
                                 style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                        </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="t_ShipInportInfo"
                               class="adminlist" style="visibility: visible;">
                            <tbody>
                            <tr>
                                <td align="right" class="tdtitle">单据号:
                                </td>
                                <td class="tdtext">
                                    <input name="documentId" value="${param.documentId}" type="text"
                                           id="documentId" class="colorblur">
                                </td>
                                <td align="right" class="tdtitle">申请日期:
                                </td>
                                <td class="tdtext">
                                    <input name="beginDate" value="${param.beginDate}" type="text"
                                           id="beginDate" class="Wdate" onclick="WdatePicker({skin:'ext'});">~<input
                                        name="endDate" value="${param.endDate}" type="text" id="endDate"
                                        class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">申请人:
                                </td>
                                <td class="tdtext">
                                    <input name="applyUser" value="${param.applyUser}" type="text"
                                           id="applyUser" class="colorblur">
                                </td>
                                <td align="right" class="tdtitle">单据类型:
                                </td>
                                <td class="tdtext">
                                    <select class="colorblur" name="documentStatus" id="documentStatus">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="1" ${param.documentStatus=="1"?"selected='selected'" : ""}>
                                            资产需求单
                                        </option>
                                        <option value="2" ${param.documentStatus=="2"?"selected='selected'" : ""}>
                                            采购申请单
                                        </option>
                                        <option value="3" ${param.documentStatus=="3"?"selected='selected'" : ""}>
                                            采购订单
                                        </option>
                     <%--                    <option value="4" ${param.documentStatus=="4"?"selected='selected'" : ""}>
                                            采购收获单
                                        </option> --%>
                                        <option value="5" ${param.documentStatus=="5"?"selected='selected'" : ""}>
                                            资产验收单
                                        </option>
                                        <option value="6" ${param.documentStatus=="6"?"selected='selected'" : ""}>
                                            采购付款单
                                        </option>
                     <%--                    <option value="7" ${param.documentStatus=="7"?"selected='selected'" : ""}>
                                            盘点单
                                        </option> --%>
                                        <option value="8" ${param.documentStatus=="8"?"selected='selected'" : ""}>
                                            报废变卖单
                                        </option>
                                        <option value="9" ${param.documentStatus=="9"?"selected='selected'" : ""}>
                                           资产调拨单
                                        </option>
                                         <option value="10" ${param.documentStatus=="10"?"selected='selected'" : ""}>
                                          预算调整单
                                        </option>
                                         <option value="11" ${param.documentStatus=="11"?"selected='selected'" : ""}>
                                          非PO付款
                                        </option>
                                    </select></td>
                                

                            </tr>
                            <tr>
                                <td class="tdtitle" align="right">资产类型:
                                </td>
                                <td class="tdtext">
                                    <select class="colorblur" name="assetType" id="assetType">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="IT资产" ${param.assetType=="IT资产"?"selected='selected'" : ""}>
                                            IT资产
                                        </option>
                                        <option value="行政资产" ${param.assetType=="行政资产"?"selected='selected'" : ""}>
                                            行政资产
                                        </option>
                                        <option value="计量器具" ${param.assetType=="计量器具"?"selected='selected'" : ""}>
                                            计量器具
                                        </option>
                                        <option value="机器设备" ${param.assetType=="机器设备"?"selected='selected'" : ""}>
                                            机器设备
                                        </option>
                                    </select>
                                </td>
                                <td align="right" class="tdtitle">申请部门:
                                </td>
                                <td class="tdtext">
                                    <span id ="spanBudgetDepartment"><input style="width: 156px;" readonly="readonly" name="budgetDepartmentName" value="${param.budgetDepartmentName}" type="text" id="txtBudgetDepartment" class="inputbtnRefViewGray" ></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="clearDep();"><input id="btntxtYsssbmShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
                                    <input name="budgetDepartmentCode" value="${param.budgetDepartmentCode}" type="hidden" id="txtYsssbm" class="inputbtnRefViewGray" >
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </td>
        </tr>
        <tr id="Button">
            <td class="buttonbar">
                &nbsp;&nbsp;
                <input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn">
               <!--  <input type="button" name="btnExport" value="导出" id="btnExport" class="btn"> -->
            </td>
        </tr>
        <tr>
            <td valign="top">
                <div id="gridview" style="width: 1045px; height: 328px;">
                    <div>
                        <table class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border-collapse: collapse;">
                            <tbody>
                            <tr style="white-space: nowrap;">
                                <th scope="col">单据类型</th>
                                <th scope="col">单据编号</th>
                                <th scope="col">申请部门</th>
                                <th scope="col">申请人</th>
                                <th scope="col">申请日期</th>
                                <th scope="col">资产类型</th>
                          <!--       <th scope="col">申请金额</th>
                                <th scope="col">审批金额</th> -->
                                <th scope="col">审批状态</th>
                            </tr>

                            <c:forEach items="${myTasksPager.records}" var="myTasks" varStatus="status">
                                <tr style="white-space: nowrap;">
                                	<td>
                                   
                        	       	<c:choose>
                        	       		
									   <c:when test="${myTasks.documentStatus=='1'}"> 
									    	采购需求单
									   </c:when>
									   <c:when test="${myTasks.documentStatus=='2'}"> 
									    	采购申请单
									   </c:when>
									   <c:when test="${myTasks.documentStatus=='3'}"> 
									    	采购订单
									   </c:when>
									   <c:when test="${myTasks.documentStatus=='4'}"> 
									    	采购收货单
									   </c:when>
									     <c:when test="${myTasks.documentStatus=='5'}"> 
									    	采购验收单
									   </c:when>
									     <c:when test="${myTasks.documentStatus=='6'}"> 
									    	采购付款单
									   </c:when>
								        <c:when test="${myTasks.documentStatus=='8'}"> 
									    	报废/变卖单
									   </c:when>
								        <c:when test="${myTasks.documentStatus=='9'}"> 
									    	资产调拨单
									   </c:when>
									    <c:when test="${myTasks.documentStatus=='10'}"> 
									    	预算调整单
									   </c:when>
									    <c:when test="${myTasks.documentStatus=='11'}"> 
									    	非PO付款单
									   </c:when>
									 </c:choose>
									</td>
                                    <c:choose>
                                        <c:when test="${myTasks.approvalStatus == '退回'}">
                                            <td><a href="javascript:void(0);" onclick="update('${myTasks.documentId}','${myTasks.typeId}');">${myTasks.documentId}</a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><a href="javascript:void(0);" onclick="examine('${myTasks.documentId}','${myTasks.typeId}');">${myTasks.documentId}</a></td>
                                        </c:otherwise>
                                    </c:choose>

                                    <td>${myTasks.budgetDepartmentName}</td>
                                    <td>${myTasks.applyUser}</td>
                                    <td style="text-align: center;">${myTasks.applyDate}</td>
                                    <td>
                               	<c:choose>
								   <c:when test="${myTasks.assetType=='0'}"> 
								    	IT资产
								   </c:when>
								   <c:when test="${myTasks.assetType=='1'}"> 
								    	行政资产
								   </c:when>
								   <c:when test="${myTasks.assetType=='2'}"> 
								    	计量器具
								   </c:when>
								   <c:when test="${myTasks.assetType=='3'}"> 
								    	机器设备
								   </c:when>
								   <c:otherwise>
								  		 ${myTasks.assetType}
								   </c:otherwise>
								</c:choose>
                                    </td>
                               <%--      <td>${myTasks.applyAmount}</td>
                                    <td>${myTasks.approvalAmount}</td> --%>
                                    
                                    <td>${myTasks.approvalStatus}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${myTasksPager.offset}" total="${myTasksPager.total}"
                                      steps="${myTasksPager.pageSize}" uri="${myTasksPager.uri}"
                                      formId="purchaseApplyOrderForm"/>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<!-- 部门树 -->
<div id="ysssbmWindow" name="" class="easyui-window" title="预算所属部门" data-options="iconCls:'icon-save'" style="width:600px;height:330px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
            <input type="button" id="ysssbmAdd" value="确定">
            <ul id="tree1"></ul>
        </div>
    </div>
</div>
<div id="addPurchaseWindow" name="" class="easyui-window" title="采购申请" data-options="iconCls:'icon-save'"
     style="width:800px;height:600px;padding:5px;" closed="true">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding:10px;">
        </div>
    </div>
</div>
<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script> --%>
<script type="text/javascript"  src="${staticURL}/Scripts/myTasks/my_tasks_list.js"></script>
<script type="text/javascript">
var dg = frameElement.lhgDG;
function examine(id,typeId) {

    var lurl;
    var fuID='examineDialog';
    if('77001' == typeId) { //采购需求
        lurl = _dynamicURL+"/demand/examineDemandOrder?id=" + id+"&typeId=77001";
        fuID='examineDialog';
    }
    if('77011' == typeId) { //采购申请
        lurl = _dynamicURL + "/apply/examineApplyOrder?id=" + id + "&typeId=77011";
        fuID='examineDialog';
    }
    if('77021' == typeId) { //采购订单
        lurl = _dynamicURL + "/purchase/examinePurchaseOrder?id=" + id + "&typeId=77021";
        fuID='examineDialog';
    }
    if('77077' == typeId) { //采购收货
        lurl = _dynamicURL+"/receiveGoods/bindingReceiveGoods?id=" + id+"&typeId=77077";
        fuID='examineDialog';
    }
    if('77078' == typeId) { //采购验收
        lurl = _dynamicURL+"/checkAccept/bindingCheckAcceptByCheckAcceptId?checkAccpetId=" + id+"&typeId=77078";
        fuID='checkAcceptApproveDialog';
    }
    if('77031' == typeId) { // 报废/变卖
        lurl = _dynamicURL+"/workflowDiscardSell/discardSellApprove?id=" + id;
        fuID='approve';
    }
    if('77002' == typeId) { // 资产调拨
        lurl = _dynamicURL+"/assetAllocation/assetAllocationApprove?id=" + id;
        fuID='approve';
    }
    if('77071' == typeId) { // 付款
        lurl = _dynamicURL+"/payment/examinePaymentOrder?id=" + id+"&typeId=77071";
    	fuID='examineDialog';
    }
    if ('77047' == typeId || '77048' == typeId) { // 预算调整
        lurl = _dynamicURL+"/budgetAdjustment/budgetAdjustmentApprove?document=" + id;
    	fuID='approve';
    } 
    if ('77079' == typeId) { // 非PO付款
        lurl = _dynamicURL+"/notPoPayment/examinePaymentOrder?id=" + id+"&typeId=77079";
        fuID='examineDialog';
    }
    var dlg = new $.dialog({ id: fuID, title: '数据审批', cover: true, page: lurl, rang: true, width: 1000, height: 560, btnBar: false, resize: false, maxBtn: false }).ShowDialog();

}
function update(id,typeId) {

    var lurl;
    var fuID='examineDialog';
    if('77001' == typeId) { //采购需求
        lurl = _dynamicURL+"/demand/updateDemandOrder?id=" + id;
        fuID='examineDialog';
    }
    if('77011' == typeId) { //采购申请
        lurl = _dynamicURL + "/apply/updateApplyOrder?id=" + id;
        fuID='examineDialog';
    }
    if('77021' == typeId) { //采购订单
        lurl = _dynamicURL + "/purchase/updatePurchaseOrder?id=" + id;
        fuID='examineDialog';
    }
    if('77077' == typeId) { //采购收货
        lurl = _dynamicURL+"/receiveGoods/updateReceiveGoods?receiveGoodsId=" + id;
    }
    if('77078' == typeId) { //采购验收
        lurl = _dynamicURL+"/checkAccept/bindingCheckAcceptByCheckAcceptId?checkAccpetId=" + id;
        fuID='checkAcceptApproveDialog';
    }
    if('77031' == typeId) { // 报废/变卖
        lurl = _dynamicURL+"/discardSell/ifUpdatePermitted?id=" + id;
        fuID='approve';
    }
    if('77002' == typeId) { // 资产调拨
        lurl = _dynamicURL+"/assetAllocation/api/assetAllocationStamp?id=" + id + "&typeId=77002";
        fuID='approve';
    }
    if('77071' == typeId) { // 付款
        lurl = _dynamicURL+"/payment/updatePaymentOrder?id=" + id;
        fuID='examineDialog';
    }
    if ('77047' == typeId || '77048' == typeId) { // 预算调整
        lurl = _dynamicURL+"/budgetAdjustment/budgetAdjustmentApprove?document=" + id;
        fuID='approve';
    }
    if ('77079' == typeId) { // 非PO付款
        lurl = _dynamicURL+"/notPoPayment/updatenotPaymentOrder?id=" + id+"&typeId=77079";
        fuID='examineDialog';
    }
    

    var dlg = new $.dialog({ id: fuID, title: '数据审批', cover: true, page: lurl, rang: true, width: 1000, height: 560, btnBar: false, resize: false, maxBtn: false }).ShowDialog();

}
	function showDemandOrder(id) {
		var lurl = "${dynamicURL}/demand/showDemandOrder?id=" + id+"&typeId=77001";
		var dlg = new $.dialog({ id: 'showForDemand', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
	}
	function showApplyOrder(id) {
		var lurl = "${dynamicURL}/apply/showApplyOrder?id=" + id+"&typeId=77011";
		var dlg = new $.dialog({ id: 'showForApply', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
	}
</script>
</body>
</html>