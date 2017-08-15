<%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>非PO付款</title>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
	<meta name="modulePath" content="basic/" />
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
						<form action="notPoPaymentOrder" id="notPoPaymentOrderForm">
 							<fieldset id="fset_ShipInportInfo">
								<legend>
		                           <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif" style="cursor: pointer;"
		                                onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
		                      	</legend>
								
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									id="t_ShipInportInfo" class="adminlist"
									style="visibility: visible;">
									<tbody>
										 <tr>
											<td align="right" class="tdtitle">付款单据号:</td>
											<td class="tdtext">
												<input name="cdocument" value="${param.cdocument}" type="text" id="" class="colorblur">
												<input id="loginUserCode" value="${loginUserCode}" type="hidden"  ></td>
											</td>
											<td align="right" class="tdtitle">付款申请日期:</td>
			                                <td class="tdtext">
			                                    <input name="applyDateStart" value="${param.applyDateStart}" type="text" id="applyDateStart" class="Wdate" onclick="WdatePicker({skin:'ext'});">~<input name="applyDateEnd" value="${param.applyDateEnd}" type="text" id="applyDateEnd" class="Wdate" onclick="WdatePicker({skin:'ext'});">
			                                </td>
										</tr> 
										 <tr>
											<td align="right" class="tdtitle">申请人:</td>
											<td class="tdtext">
												<input name="applyUser" value="${param.applyUser}" type="text" id="" class="colorblur">
											</td>
											<td align="right" class="tdtitle">是否草稿:</td>
											<td class="tdtext">
												<select name="commitType" id="" class="colorblur">
														<option selected="selected" value="">--请选择--</option>
														<option value="Y" ${param.commitType=="Y"?"selected='selected'" : ""}>是</option>
														<option value="N" ${param.commitType=="N"||param.commitType==null?"selected='selected'" : ""}>否</option>
											</select>
											</td>
										</tr>
											<tr>
											<%-- <td align="right" class="tdtitle">影像状态:</td>
											<td class="tdtext">
												<select name="cbarStatus" class="colorblur" id="cbarStatus">
														<option selected="selected" value="">--请选择--</option>
														<option value="W" ${param.cbarStatus=="W"?"selected='selected'" : ""}>等待扫描</option>
														<option value="2" ${param.cbarStatus=="2"?"selected='selected'" : ""}>已扫描</option>
														<option value="4" ${param.cbarStatus=="4"?"selected='selected'" : ""}>正在重扫</option>
														<option value="5" ${param.cbarStatus=="5"?"selected='selected'" : ""}>重扫完成</option>
												</select>
											</td>
											<td align="right" class="tdtitle">单据状态:</td>
											<td class="tdtext">
												<select name="orderStatus" class="colorblur" id="orderStatus">
														<option selected="selected" value="">--请选择--</option>
														<option value="未关闭"
															${param.orderStatus=="未关闭"?"selected='selected'" : ""}>未关闭</option>
														<option value="已关闭"
															${param.orderStatus=="已关闭"?"selected='selected'" : ""}>已关闭</option>
												</select>
											</td> --%>
											<td align="right" class="tdtitle">审批状态:</td>
											<td class="tdtext">
												<select name="approvalState" id="ckCapprovalstate" class="colorblur">
			                                        <option selected="selected" value="">--请选择--</option>
			                                        <option value="等待审批" ${param.approvalState=="等待审批"?"selected='selected'" : ""}>等待审批</option>
			                                        <option value="正在审批" ${param.approvalState=="正在审批"?"selected='selected'" : ""}>正在审批</option>
			                                        <option value="已完成" ${param.approvalState=="已完成"?"selected='selected'" : ""}>已完成</option>
			                                        <option value="退回" ${param.approvalState=="退回"?"selected='selected'" : ""}>退回</option>
			
			                                    </select>
											</td>
										</tr>
									</tbody>
								</table>
							</fieldset> 
						</form>
					</td>
				</tr>
				<tr id="Button">
					<td class="buttonbar">&nbsp;&nbsp;
						<auth:authTag code="Query">
						<input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn"> 
						</auth:authTag>
						<auth:authTag code="Apply">
						<input type="button" name="btnQuery" value="付款" id="btnAdd" class="btn" onclick="opAdddlg('付款类型选择')"> 
						</auth:authTag>
						<auth:authTag code="Check">
						<input type="button" name="checkBtn" value="审批" id="checkBtn" class="btn">
						</auth:authTag>
						<auth:authTag code="Delete">
						<input type="button" name="btnQuery" value="删除" id="deleteBtn" class="btn">
						</auth:authTag>
						<auth:authTag code="Export">
						<input type="button" name="btnQuery" value="导出" id="exportBtn" class="btn">
						</auth:authTag>
						<auth:authTag code="Pass">
						<input type="button" name="clearQuery" value="再次清账" id="clearVouchBtn" class="btn">
 						</auth:authTag>
						<input type="button" name="btnRefresh" value="刷新" id="btnRefresh"
						class="controlhidden"> <input type="hidden"
						name="hdnApprovalCode" id="hdnApprovalCode"> <input
						type="hidden" name="hdnUserName" id="hdnUserName">
					</td>
				</tr>
				<tr>
					<td valign="top">
						<div id="gridview" style="width: 1045px; height: 328px;">
							<div>
								<table class="adminlist" cellspacing="0" rules="all" border="1"
									id="gvDataList" style="border-collapse: collapse;">
									<tbody>
			                                <th scope="col">
			                                    <input id="gvDataList_CheckAll" type="checkbox" name="gvDataList$ctl01$CheckAll"
			                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'gvDataList');"/>
			                                </th>
											<auth:authTag code="Edit">
											<th scope="col">操作</th>
											</auth:authTag>
											<auth:authTag code="View">
											<th scope="col">操作</th>
											</auth:authTag>
											<auth:authTag code="Print">
											<th scope="col">操作</th>
											</auth:authTag>
											<th scope="col">付款单据号</th>
											<th scope="col">所属公司</th>
											<th scope="col">供应商</th>
											<th scope="col">付款类别</th>
											<th scope="col">付款金额</th>
											<th scope="col">申请人</th>
											<th scope="col">申请日期</th>
											<th scope="col">审批状态</th>
											<th scope="col">下级处理人</th>
											<!-- <th scope="col">影像状态</th> -->
											<th scope="col">是否为草稿</th>
											<th scope="col">是否打印</th>
										</tr>

										<c:forEach items="${paymentPager.records}" var="Order" varStatus="status">
											<tr style="white-space: nowrap;">

												 <td align="center">
			                                        <input id="gvDataList_chkBolDel_0" type="checkbox" class="paymentCheckbox" name="id" cdocument = "${Order.cdocument}" applyUserCode = "${Order.applyUserCode}" ><%-- cbarstatus="${Order.cbarStatus}" --%>
			                                    </td>
			                                    <auth:authTag code="Edit">
			                                    <td align="center">
			                                    	<c:if test="${Order.cdocument!=null}">
			                                        <a href="javascript:opUpdatedlg('${Order.cdocument}','数据修改','${Order.approvalState}','${Order.applyUserCode}','${Order.paymentType}')">修改</a>
			                                        </c:if>
			                                    </td>
			                                    </auth:authTag>
			                                    <auth:authTag code="View">
			                                    <td align="center">
			                                    	<c:if test="${Order.cdocument!=null}">
			                                        <a href="javascript:opShowdlg('${Order.cdocument}','数据查看')">查看</a>
			                                        </c:if>
			                                    </td>
			                                    </auth:authTag>
			                                    <auth:authTag code="Print">
		                                    	<td>
		                                    		<c:if test="${Order.cdocument!=null}">
		                                    		<a href="javascript:printSeal()"onclick="stamp('${Order.cdocument}','打印','${Order.approvalState}');" rel="external nofollow" target="_self">打印</a>
			                                        </c:if>
		                                    	</td>
		                                    	</auth:authTag>
												<td name="tdCdocument">${Order.cdocument}</td>
												<td>(${Order.companyCode})${Order.companyName}</td>
												<td>(${Order.supplierCode})${Order.supplierName}</td>
												<td>${Order.paymentType}</td>
												<td style="text-align: right; padding-right: 5px;" name="tdOrderMoney">${Order.applyMoney}</td>
												<td>${Order.applyUser}</td>
												<td style="text-align:center"><fmt:formatDate value='${Order.applyDate}' pattern='yyyy-MM-dd'/></td>
												<td><a href="javascript:void(0)" onclick="showApproveHistory('${Order.cdocument}', '77079')">${Order.approvalState}</a></td>
												<td>${Order.nextHandlerName}</td>
												<%-- <td>${Order.draft}222</td> --%>
												<%-- <c:choose>
													<c:when test="${Order.cbarStatus=='W'}">
														<td>等待扫描</td>
													</c:when>
													<c:when test="${Order.cbarStatus=='2'}">
														<td>已扫描</td>
													</c:when>
													<c:when test="${Order.cbarStatus=='4'}">
														<td>正在重扫</td>
													</c:when>
													<c:when test="${Order.cbarStatus=='5'}">
														<td>重扫完成</td>
													</c:when>
													<c:otherwise>
														<td></td>
													</c:otherwise>
												</c:choose> --%>
												<td><c:if test="${Order.commitType!=null}">${Order.commitType=="N"?"否":"是"}</c:if></td>
												<td><c:if test="${Order.print!=null}">${Order.print=="N"?"否":"是"}</c:if></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<tag:paginate offset="${paymentPager.offset}" total="${paymentPager.total}" steps="${paymentPager.pageSize}" uri="${paymentPager.uri}" formId="notPoPaymentOrderForm" />
								
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script> --%>
<script type="text/javascript"> 


 	GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
    QueryFieldSetStauts('t_ShipInportInfo', 'fset_ShipInportInfo', 'img_ShipInportInfo', document.getElementById("hfQueryStatus").value);
    $(function(){
    	$("td[name='tdOrderMoney']").each(function(){
    		if($(this).html()!=""){
    			$(this).html(fmoney($(this).html(),2));
    		};
    	});
    })

   /*  function opShowdlg(id, title) {
        var lurl = "${dynamicURL}/notPoPayment/showPaymentOrder?id=" + id+"&typeId=77079";
        var dlg = new $.dialog({ id: 'show', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    } */
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
     	var txtPurchaseApplyDateStart = $("#applyDateStart").val();
    	var txtPurchaseApplyDateEnd = $("#applyDateEnd").val();
    	if(txtPurchaseApplyDateStart!=""&&txtPurchaseApplyDateEnd!=""&&txtPurchaseApplyDateStart>txtPurchaseApplyDateEnd){
    		alert("采购订单开始时间不能大于结束时间");
    		return false;
    	}
    	var txtApplyDateStart = $("#txtApplyDateStart").val();
    	var txtApplyDateEnd = $("#txtApplyDateEnd").val();
    	if(txtApplyDateStart!=""&&txtApplyDateEnd!=""&&txtApplyDateStart>txtApplyDateEnd){
    		alert("付款申请开始时间不能大于结束时间");
    		return false;
    	}
        window.location.href = '${dynamicURL}/notPoPayment/searchPayment?' + $('#notPoPaymentOrderForm').serialize();
    });
    function refresh(){
        window.location.reload();//刷新当前页面.
    }
    
  //新增申请单  弹出层大小 统一为 900和600
	function opAdddlg(title) {
	  	
    	var lurl = "${dynamicURL}/notPoPayment/checkNotPoPaymentOrder";
    	var dlg = new $.dialog({ id: 'check', title: title, cover: true, page: lurl, rang: false, width: 400, height: 120, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
	   
	}
	function opShowdlg(id, title) {
		var lurl = "${dynamicURL}/notPoPayment/showPaymentOrder?id=" + id+"&typeId=77079";
    	var dlg = new $.dialog({ id: 'show', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
	}
	//修改申请单  弹出层大小 统一为  1100和650
	function opUpdatedlg(id, title,capprovalstate,applyUserCode,paymentType) {
		var loginUserCode = $("#loginUserCode").val();
		var auth = isAuthority(loginUserCode,applyUserCode);
		/*if(!auth){
			alert("您还没有操作权限，请检查是否离职授权！");
			return;
		};*/
		if ('验收款' != paymentType) {
            if(capprovalstate=="等待审批"||capprovalstate==""||capprovalstate==null||capprovalstate=="退回") {
                var lurl = "${dynamicURL}/notPoPayment/updatenotPaymentOrder?id=" + id;
                var dlg = new $.dialog({ id: 'update', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
            }

            else {
                alert("单据正在审批中或审批结束，无法修改!");
            }
		} else {
            if(capprovalstate=="等待审批"||capprovalstate==""||capprovalstate==null||capprovalstate=="退回") {
                var lurl = "${dynamicURL}/notPoPayment/updatenotPaymentOrder?id=" + id;
                var dlg = new $.dialog({ id: 'update', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
            }

            else {
                alert("单据正在审批中或审批结束，无法修改!");
            }
        }

	}
	//新增窗口
	function addPayment(strData) {
		var lurl = "${dynamicURL}/notPoPayment/addNotPoPaymentOrder?paymentType="+strData;
		var dlg = new $.dialog({ id: 'add', title: '数据新增', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
	}
    $("#exportBtn").click(function(){
        if (window.confirm("确认要导出吗?")) {
            window.location.href = "${dynamicURL}/notPoPayment/Export?"+$('#notPoPaymentOrderForm').serialize();
        }
    });
    //历史审批记录
    function showApproveHistory(document, typeId) {
        var url = '${dynamicURL}/notPoPayment/showApproveHistory?document='+document+"&typeId="+typeId;
        var dlg = new $.dialog({ id: 'approveHistory', title: "审批", cover: true, page: url, rang: true, width: 700, height: 450, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
        // showDialog('approveHistory', '申请流程', url + document +'&typeId=' + typeId, 800, 500);
    }
    //打印
    function printSeal(){
        document.body.innerHTML=document.getElementById('content').innerHTML;
        window.print();
    }
    function stamp(id, title,capprovalstate){
//        if(capprovalstate!="已完成"){
//            alert("单据还未审批结束，无法打印！");
//            return;
//        }
//        if(pamentType == "验收款"){
//
//		}

        var lurl = "${dynamicURL}/notPoPayment/api/printOrder?id=" + id + "&typeId=77079";
        window.open(lurl);

    }
    $("#checkBtn").click(function(){
    	if (!checkSingleSelect('gvDataList')) {
			alert('必须且只能选择一项');
			return;
		}
		/* var cbarstatus = $("#gvDataList td input[name = id]:checked").attr("cbarstatus");
 		if(cbarstatus!="2" && cbarstatus !="5"){
			alert("只有影像状态是已扫描或重扫完成的单据才可以审批");
			return false;
		}  */
		var id = $("#gvDataList td input[name = id]:checked").attr("cdocument");
		if(id.length<1){
			alert("请选择有付款单号项进行审批！");
			return;
		}
		var lurl = "${dynamicURL}/notPoPayment/examinePaymentOrder?id=" + id+"&typeId=77079";
    	var dlg = new $.dialog({ id: 'examineDialog', title: '数据审批', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
		
	});
	$("#deleteBtn").click(function(){
		if (!checkSingleSelect('gvDataList')) {
			alert('必须且只能选择一项');
			return;
		}
		var id = $("#gvDataList td input[name = id]:checked").attr("cdocument");
		var loginUserCode = $("#loginUserCode").val();
		var applyUserCode = $("#gvDataList td input[name = id]:checked").attr("applyUserCode");
		var auth = isAuthority(loginUserCode,applyUserCode);
		if(!auth){
			alert("您还没有操作权限，请检查是否离职授权！");
			return;
		};
		if (window.confirm("确认要删除吗?")) {
	        $.ajax({
	           url: "${dynamicURL}/notPoPayment/api/deleteNotPoPayment",
	           type: "POST",
	          // contentType: "application/json",
	           data:{id : id},
	           success: function (data) {
	        	   if (data.success){
	        		   alert(data.successMessage);
 	         		   $(".paymentCheckbox").each(
								function() {
									$(this).attr('checked', false); 
								});
	                   window.location.reload();
	        	   } else {
						alert(data.errorMessages.join());
					}

	           }
	       }); 
	      }
	});
	$("#clearVouchBtn").click(function(){
		if (!checkSingleSelect('gvDataList')) {
			alert('必须且只能选择一项');
			return;
		}
		var id = $("#gvDataList td input[name = id]:checked").attr("cdocument");
		if(id.length<1){
			alert("请选择有付款单号项进行删除！");
			return;
		}
	        $.ajax({
	           url: "${dynamicURL}/notPoPayment/api/clearVouch",
	           type: "POST",
	          // contentType: "application/json",
	           data:{id : id},
	           success: function (data) {
	        	   if (data.success){
	        		   alert(data.successMessage);
 	         		   $(".paymentCheckbox").each(
								function() {
									$(this).attr('checked', false); 
								});
	        	   } else {
						alert(data.errorMessages.join());
					}

	           }
	       }); 
	});
	//历史审批记录
    function showApproveHistory(document, typeId) {
        var url = '${dynamicURL}/payment/showApproveHistory?document='+document+"&typeId="+typeId;
        var dlg = new $.dialog({ id: 'approveHistory', title: "审批", cover: true, page: url, rang: true, width: 700, height: 450, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
        // showDialog('approveHistory', '申请流程', url + document +'&typeId=' + typeId, 800, 500);
    }
    function opAddPurchasedlg1(id, title) {
    	var lurl = "${dynamicURL}/purchase/showPurchaseOrder?id=" + id+"&typeId=77021";
    	var dlg = new $.dialog({ id: 'add', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
	}
	//判断是否有权限删除修改单据（离职授权）
	function isAuthority(loginUserCode,applyUserCode){
		var flag = null;
		$.ajax({
	           url: "${dynamicURL}/userInfo/api/isAuthority",
	           type: "POST",
	           async: false,
	           dataType:"json",
	          // contentType: "application/json",
	           data:{loginUserCode : loginUserCode,applyUserCode:applyUserCode},
	           success: function (data) {
	        	   
	        	  flag=data;
	           }
	       }); 
		return flag;
	}
	function showDemandOrder(id) {
    	var lurl = "${dynamicURL}/demand/showDemandOrder?id=" + id+"&typeId=77001";
    	var dlg = new $.dialog({ id: 'showForDemand', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
	}
    function showApplyOrder(id) {
    	var lurl = "${dynamicURL}/apply/showApplyOrder?id=" + id+"&typeId=77011";
    	var dlg = new $.dialog({ id: 'showForApply', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }
  //打印
	function printSeal(){ 
        document.body.innerHTML=document.getElementById('content').innerHTML; 
        window.print(); 
    }
	/**
	    * 数字格式转换成千分位
	    */
	    function fmoney(s, n)

	    {
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
</script>
</body>
</html>