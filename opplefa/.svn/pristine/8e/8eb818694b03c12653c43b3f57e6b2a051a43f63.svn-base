<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>采购申请</title>
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
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif" style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                       	</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="t_ShipInportInfo" class="adminlist" style="visibility: visible;">
                            <tbody><tr>
                                <td align="right" class="tdtitle">单据号:
                                </td>
                                <td class="tdtext">
                                    <input name="txtApplyOrderId" value="${param.txtApplyOrderId}" type="text" id="txtApplyOrderId" class="colorblur">
                                </td>
                                <td align="right" class="tdtitle">申请日期:
                                </td>
                                <td class="tdtext">
                                    <input name="txtBeginDate" value="${param.txtBeginDate}" type="text" id="txtBeginDate" class="Wdate" onclick="WdatePicker({skin:'ext'});">~<input name="txtEndDate" value="${param.txtEndDate}" type="text" id="txtEndDate" class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">申请人:
                                </td>
                                <td class="tdtext">
                                    <input name="txtApplyUser" value="${param.txtApplyUser}" type="text" id="txtApplyUser" class="colorblur">
                                </td>
                                <td align="right" class="tdtitle">单据状态:
                                </td>
                                <td class="tdtext">
                                    <select class="colorblur" name="ckOrderStatus" id="ckOrderStatus">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="已关闭" ${param.ckOrderStatus=="已关闭"?"selected='selected'" : ""}>已关闭</option>
                                        <option value="未关闭" ${param.ckOrderStatus=="未关闭"?"selected='selected'" : ""}>未关闭</option>
                                    </select></td>
                                </td>
                                
                            </tr>
                            <tr>
                           		<td class="tdtitle" align="right">资产类型:
                                </td>
                                <td class="tdtext">
                                    <select class="colorblur" name="txtAssetType" id="txtAssetType">
                                       <option selected="selected" value="">--请选择--</option>
                                       <option value="行政资产" ${param.txtAssetType=="行政资产"?"selected='selected'" : ""}>行政资产</option>
                                       <option value="IT资产" ${param.txtAssetType=="IT资产"?"selected='selected'" : ""}>IT资产</option>
                                       <option value="计量器具" ${param.txtAssetType=="计量器具"?"selected='selected'" : ""}>计量器具</option>
                                       <option value="机器设备" ${param.txtAssetType=="机器设备"?"selected='selected'" : ""}>机器设备</option>
                                   	</select>
								</td>
                                <td align="right" class="tdtitle">审批状态:
                                </td>
                                <td class="tdtext">
                                    <select class="colorblur" name="ckCapprovalstate" id="ckCapprovalstate">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="等待审批" ${param.ckCapprovalstate=="等待审批"?"selected='selected'" : ""}>等待审批</option>
                                        <option value="正在审批" ${param.ckCapprovalstate=="正在审批"?"selected='selected'" : ""}>正在审批</option>
                                        <option value="已完成" ${param.ckCapprovalstate=="已完成"?"selected='selected'" : ""}>已完成</option>
                                        <option value="退回" ${param.ckCapprovalstate=="退回"?"selected='selected'" : ""}>退回</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">是否为草稿:</td>
                                <td class="tdtext">
                                    <select class="colorblur" name="ckDraft" id="ckDraft">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="Y" ${param.ckDraft=="Y"?"selected='selected'" : ""}>是</option>
                                        <option value="N" ${param.ckDraft=="N"||param.ckDraft==null?"selected='selected'" : ""}>否</option>

                                    </select>
                                </td>
                                <td align="right" class="tdtitle">资产名称：</td>
                                <td class="tdtext">
                                    <input name="assetsName" value="${param.assetsName}" type="text" id="assetsName" class="colorblur">
                                </td>
                                    
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">需求单号：</td>
                                <td class="tdtext">
                                    <input name="demandId" value="${param.demandId}" type="text" id="demandId" class="colorblur">
                                </td>
                                <td align="right" class="tdtitle">所属公司：</td>
                                <td class="tdtext">
                                    <%--<input name="companyCode" value="${param.companyCode}" type="text" id="companyCode" class="colorblur">--%>
                                    <select id="companyCode" class="colorblur" name='companyCode' >
                                        <option selected="selected" value="">--请选择--</option>
                                        <c:forEach items="${companys }" var="comp" varStatus="status">
                                            <c:if test="${comp.ccompanycode == param.companyCode}">
                                                <option value="${comp.ccompanycode}" selected="selected" >(${comp.ccompanycode})${comp.ccompanyname }</option>
                                            </c:if>
                                            <c:if test="${comp.ccompanycode != param.companyCode}">

                                                <option value="${comp.ccompanycode}" >(${comp.ccompanycode})${comp.ccompanyname }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">成本中心编码：</td>
                                <td class="tdtext">
                                    <input name="costCenterCode" value="${param.costCenterCode}" type="text" id="costCenterCode" class="colorblur">
                                </td>
                            </tr>
                            </tbody></table>
                    </fieldset>
                </form>
            </td>
        </tr>
        <tr id="Button">
            <td class="buttonbar">
                &nbsp;&nbsp;
                <auth:authTag code="Query">
                <input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn">
                </auth:authTag>
                <auth:authTag code="Add">
                <input onclick="opAdddlg('-1','数据新增')" type="button" name="btnAdd" value="新增" id="btnAdd" class="btn" >
                </auth:authTag>
                <auth:authTag code="Check">
                <input type="button" name="btnQuery" value="审批" id="btnComplaint" class="btn">
                </auth:authTag>
                <auth:authTag code="Delete">
                <input type="button" name="btnQuery" value="删除" id="" class="btn" onclick="deleteApply()">
                </auth:authTag>
                <auth:authTag code="Export">
                <input type="button" name="btnExport" value="导出" id="btnExport" class="btn">
                </auth:authTag>
                <auth:authTag code="Close">
                <input type="button" name="btnQuery" value="关闭" id="" class="btn" onclick="closeApply()">
                </auth:authTag>
            </td>
        </tr>
        <tr>
            <td valign="top" style="width: 100%;">
                <div id="gridview" style="width: 1045px; height: 328px;">
                    <div>
                        <table class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border-collapse: collapse;">
                            <tbody>
                            <tr style="white-space: nowrap;">
                                <th scope="col">
                                    <input id="gvDataList_CheckAll" type="checkbox"
                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'gvDataList');"/>
                                </th>
                                <th scope="col">操作</th>
                                <th scope="col">操作</th>
                                <th scope="col">操作</th>
                                <th scope="col">单据号</th>
                                <th scope="col">归口/预算部门</th>
                                <th scope="col">成本中心</th>
                                <th scope="col">申请人</th>
                                <th scope="col">资产类型</th>
                                <th scope="col">申请日期</th>
                                <th scope="col">公司</th>
                                <th scope="col">审批状态</th>
                                <th scope="col">单据状态</th>
                                <th scope="col">是否草稿</th>
                                <th scope="col">是否打印</th>
                                <th scope="col">创建人</th>
                                <th scope="col">下级处理人</th>
                            </tr>

                            <c:forEach items="${applyOrderPager.records}" var="applyOrder" varStatus="status">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input id="gvDataList_chkBolDel_0" type="checkbox" docno="${applyOrder.cdocument}" applyUser="${applyOrder.applyUserCode}" name="id" value = "${applyOrder.cdocument}" capprovalstate = "${applyOrder.capprovalstate}" draft = "${applyOrder.draft}">
                                    </td>
                                    <td align="center">
                                        <a href="javascript:opUpdatedlg('${applyOrder.cdocument}','数据修改');" >修改</a>
                                    </td>
                                    <td align="center">
                                        <a href="javascript:opAdddlg('${applyOrder.cdocument}','数据查看');" name="${applyOrder.cdocument}" id="applyOrder${applyOrder.cdocument}" >查看</a>
                                    </td>
                                    <td align="center">
										<a href="javascript:stamp('${applyOrder.cdocument}','打印','${applyOrder.capprovalstate}');" rel="external nofollow" target="_self">打印</a>
                                    </td>
                                    <td>${applyOrder.cdocument}</td>
                                    <td>(${applyOrder.budgetDepartmentCode})${applyOrder.budgetDepartmentName}</td>
                                    <td>${applyOrder.costCenter}</td>
                                    <td>${applyOrder.applyUser}</td>
                                    <td>${applyOrder.assetType}</td>
                                    <td style="text-align: center;"><fmt:formatDate value="${applyOrder.applyDate}"  pattern="yyyy-MM-dd"/></td>
                                    <td>${applyOrder.companyName}</td>
                                    <td><a href="javascript:void(0)" id="aapprovalState" onclick="showApproveHistory('${applyOrder.cdocument}', '77011')">${applyOrder.capprovalstate}</a></td>
                                    <td>${applyOrder.orderStatus}</td>
                                    <td>${applyOrder.draft=="N"?"否":"是"}</td>
                                    <td>${applyOrder.print=="N"?"否":"是"}</td>
                                    <td>${applyOrder.createBy}</td>
                                    <td>${applyOrder.cnexthandlername}</td>
                                    
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${applyOrderPager.offset}" total="${applyOrderPager.total}" steps="${applyOrderPager.pageSize}" uri="${applyOrderPager.uri}" formId="purchaseApplyOrderForm" />
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<div id="addPurchaseWindow" name="" class="easyui-window" title="采购申请" data-options="iconCls:'icon-save'" style="width:800px;height:600px;padding:5px;" closed="true">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'"  style="padding:10px;">
		</div>
	</div>
</div>
<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script> --%>
<script type="text/javascript">
    GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
    QueryFieldSetStauts('t_ShipInportInfo', 'fset_ShipInportInfo', 'img_ShipInportInfo', document.getElementById("hfQueryStatus").value);

    function autoSize() {
        $("#gridview").width($(window).width());
        $("#gridview").height(
                $(window).height() - $("#Query").height()
                - $("#Button").height()
                - $("#AspNetPager").height());
    }

    autoSize();
    window.onresize = autoSize;

    $("#btnQuery").click(function() {
    	var txtBeginDate = $("#txtBeginDate").val();
    	var txtEndDate = $("#txtEndDate").val();
    	if(txtBeginDate>txtEndDate && txtEndDate!=""){
    		alert("开始时间不能大于结束时间");
    		return false;
    	}
        window.location.href = '${dynamicURL}/apply/searchApply?' + $('#purchaseApplyOrderForm').serialize();
    });

    function refresh(){
        window.location.reload();//刷新当前页面.
    }

    function showDialog(id, title, url, width, height) {
        new $.dialog({ id: id, title: title, cover: true, page: url, width: width, height: height, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }

    function showApproveHistory(document, typeId) {
        var url = '${dynamicURL}/workflow/showApproveHistory?document=' + document + '&typeId=' + typeId;
        showDialog('approveHistory', '申请流程', url, 800, 500);
    }

    //显示审批界面
    $("#btnComplaint").click(function(){

        var docno = $("#gvDataList td input[name = id]:checked").attr("docno");
        var url = '${dynamicURL}/apply/examineApplyOrder?id=' + docno +"&typeId=77011";
        if (!checkSingleSelect('gvDataList')) {
            alert('必须且只能选择一项');
            return;
        }


        var dlg = new $.dialog({ id: 'examineDialog', title: "采购申请审批", cover: true, page: url, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
    })

	// 导出
	$("#btnExport").click(function () {
		window.location.href = '${dynamicURL}/apply/exportApply?' + $('#purchaseApplyOrderForm').serialize();
	});
	//新增申请单  弹出层大小 统一为 800和600
	function opAdddlg(id, title) {
	    //var h = typeof (window.parent.parent.GetViewHeight()) == "undefined" ? 600 : window.parent.parent.GetViewHeight() - 20;
	   
	    if (id == -1) {//-1是新增窗口
	    	var lurl = "${dynamicURL}/apply/addApplyOrder";
	    	var dlg = new $.dialog({ id: 'add', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
	    }
	    else {
	    	var lurl = "${dynamicURL}/apply/showApplyOrder?id=" + id+"&typeId=77011";
	    	var dlg = new $.dialog({ id: 'add', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
	    	}
	}

	//修改申请单  弹出层大小 统一为  1100和650
	function opUpdatedlg(id, title) {
		var lurl = "${dynamicURL}/apply/updateApplyOrder?id=" + id;
		var dlg = new $.dialog({ id: 'update', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
	}
	
	//删除
	function deleteApply() {

		var spCodesTemp = "";

		var ids = "";
		var flag = false;
		var mess="";
		$('input:checkbox[name=id]:checked').each(function(i) {
			if($(this).attr("draft") != "Y"){
				if($(this).attr("capprovalstate")!="等待审批" && $(this).attr("capprovalstate")!="退回"){
					flag = true;
					mess = "只能删除[草稿]或[等待审批]和[退回]状态的单据!";
					return false;
				}
			}
			if($(this).attr("applyUser")!=loginUserCode && $(this).attr("isAuthorize") != "Y") {
				flag = true;
				mess="申请人不同，不能删除!";
				return false;
			} 
			spCodesTemp = $(this).val();
			
			if (ids != null && ids != "") {
				ids = spCodesTemp + "," + ids;
			} else {
				ids = spCodesTemp;
			}
		});
		if(flag){
			alert(mess);
			return false;
		}
		
		if (ids != null && "" != ids) {
			if (window.confirm("确认要删除吗?")) {
				$.ajax({
					type : "get",
					contentType : "application/json",
					url : "${dynamicURL}/apply/api/delApply",
					data : {
						"ids" : ids
					},
					success : function(data) {
						if (data.success) {
							alert("删除成功！");
							window.location.reload();
						} else {
							alert(data.errorMessages.join());
						}

					}
				});
			}
		} else {
			alert("请至少选择一条数据");
		}

	}
	
	//关闭
	
	var loginUserCode = "${loginUserCode}";
	function closeApply() {

		var spCodesTemp = "";

		var ids = "";
		var flag = false;
		var mess="";
		$('input:checkbox[name=id]:checked').each(function(i) {
			 if($(this).attr("capprovalstate")!="已完成") {
				flag = true;
				mess = "只能关闭[已完成]状态的单据!";
			}
			//德邦让去掉  2017/6/15
		 	/*if($(this).attr("applyUser")!=loginUserCode && $(this).attr("isAuthorize") != "Y") {
				flag = true;
				mess="申请人不同，不能关闭!";
			} */
			spCodesTemp = $(this).val();
			
			if (ids != null && ids != "") {
				ids = spCodesTemp + "," + ids;
			} else {
				ids = spCodesTemp;
			}
		});
		 if(flag){
			alert(mess);
			return false;
		} 
		
		if (ids != null && "" != ids) {
			if (window.confirm("确认要关闭吗?")) {
				$.ajax({
					type : "get",
					contentType : "application/json",
					url : "${dynamicURL}/apply/api/closeApply",
					data : {
						"ids" : ids
					},
					success : function(data) {
						if (data.success) {
							alert("关闭成功！");
							window.location.reload();
						} else {
							alert(data.errorMessages.join());
						}

					}
				});
			}
		} else {
			alert("请至少选择一条数据");
		}
	}
	
    function stamp(id, title,capprovalstate){
    	if(capprovalstate != "已完成") {
			alert("只能打印[已完成]状态的单据!");
			return;
		}
        var lurl = "${dynamicURL}/apply/printOrder?id=" + id + "&typeId=77011";
		window.open(lurl); 
	}

    $("#btnExport").click(function (){
		if (window.confirm("确认要导出吗?")) {
			window.location.href = "${dynamicURL}/apply/api/exportApply?"+$('#purchaseApplyOrderForm').serialize();
		}
	});
    /**
     * 数字格式转换成千分位
     */
     function fmoney(s, n){
	        n = n > 0 && n <= 20 ? n : 2;
	        s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
	        var l = s.split('.') [0].split('').reverse(),
	            r = s.split('.') [1];
	        var  t = '';
	        for (var i = 0; i < l.length; i++)
	        {
	            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
	        }
	        return t.split('').reverse().join('') + '.' + r;

	    }
	    $(function(){
	        $("td[isMoney='Y']").each(function(){
	            if($(this).html()!=""){
	                $(this).html(fmoney($(this).html(),2));
	            };
	        });
	    })
	    
	    function showDemandOrder(id) {
	    	var lurl = "${dynamicURL}/demand/showDemandOrder?id=" + id+"&typeId=77001";
	    	var dlg = new $.dialog({ id: 'showForDemand', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
		}
</script>
</body>
</html>