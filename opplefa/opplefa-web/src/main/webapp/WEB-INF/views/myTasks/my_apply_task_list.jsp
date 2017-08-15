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
	<div>
       
     </div>
    <input type="hidden" name="hfQueryStatus" id="hfQueryStatus" value="show">
    <table cellspacing="0" cellpadding="0" style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form action="purchaseApplyOrder" id="demandApplyOrderForm">
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
                                    <input name="document" value="${param.document}" type="text"
                                           id="document" class="colorblur">
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
                                    <select class="colorblur" name="documentType" id="documentType">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="1" ${param.documentType=="1"?"selected='selected'" : ""}>
                                            资产需求-->申请
                                        </option>
                                        <option value="2" ${param.documentType=="2"?"selected='selected'" : ""}>
                                            采购申请-->订单
                                        </option>
                                        <option value="3" ${param.documentType=="3"?"selected='selected'" : ""}>
                                            采购订单-->收货
                                        </option>
                                        <option value="4" ${param.documentType=="4"?"selected='selected'" : ""}>
                                           发起收货-->验收
                                        </option>
                                        <option value="5" ${param.documentType=="5"?"selected='selected'" : ""}>
                                          采购订单-->付款
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
                <input type="button" name="btnExport" value="导出" id="btnExport" onclick="Export()" class="btn">
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
                                <!-- <th scope="col">审批金额</th>   -->     
                                
                                <th scope="col">单据状态</th>
                            </tr>

                            <c:forEach items="${myApplyTasksPager.records}" var="myTasks" varStatus="status">
                                <tr style="white-space: nowrap;">
                                <td> 
                        	       	<c:choose>
									   <c:when test="${myTasks.documentType=='1'}"> 
									    	采购需求-->申请
									   </c:when>
									   <c:when test="${myTasks.documentType=='2'}"> 
									    	采购申请-->订单
									   </c:when>
									   <c:when test="${myTasks.documentType=='3'}"> 
									    	采购订单-->收货
									   </c:when>
									   <c:when test="${myTasks.documentType=='4'}"> 
									    	发起收货-->验收
									   </c:when>
									  <c:when test="${myTasks.documentType=='5'}"> 
									    	采购订单-->付款
									   </c:when>
									 </c:choose>
                                    </td>
                                    <td><a href="javascript:void(0);" onclick="examine('${myTasks.document}','${myTasks.documentType}');">${myTasks.document}</a></td>
                                    <td>${myTasks.budgetDepartmentName}</td>
                                    <td>${myTasks.applyUser}</td>
                                    <td style="text-align: center;">
                                    	 ${myTasks.applyDate}
                                    </td>
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
                                 <%--    </td>
                                    <td style="text-align: right;">${myTasks.iamoney}</td>--%>
                                    
                                    <td>待办</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${myApplyTasksPager.offset}" total="${myApplyTasksPager.total}"
                                      steps="${myApplyTasksPager.pageSize}" uri="${myApplyTasksPager.uri}"
                                      formId="demandApplyOrderForm"/>
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
 <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript"  src="${staticURL}/Scripts/myTasks/my_apply_tasks_list.js"></script>
<script type="text/javascript">
var dg = frameElement.lhgDG;
function examine(id,documentType){
	  if('1' == documentType) { //采购需求-->申请
	        lurl = _dynamicURL+"/demand/showDemandOrder?id=" + id+"&typeId=77001";
	        fuID='show';
	    }
	  if('2' == documentType) { //采购申请-->订单
	        lurl = _dynamicURL+"/apply/showApplyOrder?id=" + id+"&typeId=77011";
	        fuID='add';
	    }
	  if('3' == documentType) { //采购订单-->收货
	        lurl = _dynamicURL+"/purchase/showPurchaseOrder?id=" + id+"&typeId=77021";
	        fuID='add';
	    }
	  if('4' == documentType) { //发起收货-->验收
	        lurl = _dynamicURL+"/receiveGoods/showReceiveGoods?receiveGoodsId=" + id+"&typeId=77077";
	        fuID='examineDialog';
	    }
	  if('5' == documentType) { //采购订单-->付款
	        lurl = _dynamicURL+"/purchase/showPurchaseOrder?id=" + id+"&typeId=77021";
	        fuID='add';
	    }
	  var dlg = new $.dialog({ id: fuID, title: '数据查看', cover: true, page: lurl, rang: true, width: 1000, height: 560, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
}
function showDemandOrder(id) {
    var lurl = "${dynamicURL}/demand/showDemandOrder?id=" + id+"&typeId=77001";
    var dlg = new $.dialog({ id: 'showForDemand', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
}
function showApplyOrder(id) {
    var lurl = "${dynamicURL}/apply/showApplyOrder?id=" + id+"&typeId=77011";
    var dlg = new $.dialog({ id: 'showForApply', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
}

function showPurchase(id, title) {
    var lurl = "${dynamicURL}/purchase/showPurchaseOrder?id=" + id+"&typeId=77021";
    var dlg = new $.dialog({ id: 'add', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();

}
function Export(){
	if (window.confirm("确认要导出吗?")) {
		window.location.href = "${dynamicURL}/myTasks/Export?"
				+ $('#demandApplyOrderForm').serialize();
	}
}
</script>
</body>
</html>