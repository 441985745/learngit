<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" type="text/css" href="${staticURL}/assetallocation/css/assetapplicationlist.css">
<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">

<title>Insert title here</title>
</head>
	<body>
		<fieldset id="fset_ShipInportInfo">
   			<legend>
   				<img alt="展开/收缩" src="${staticURL}/assetcard/img/constringency.gif " onclick="qiehuan()">
   				查询条件
   			</legend>
	<div>
	<form id="formId">
	<div class="trDiv" id="indexId">
		<table class="table_head"  >
			<tr >
					<td class="td_text">申请人：</td>
					<td class="td_span"><input type="text" class="my-text" value="${processAsset.userName}"  name="userName"  id="userName" /></td>
					<td class="td_text">申请日期：</td>
					<%-- <td class="td_span">
						<input type="text" style="width: 90px;" class="Wdate" value="${assetAllocation.applicationDate}" onclick="WdatePicker({skin:'ext'});"  id="applicationDate" />~
						<input type="text" style="width: 90px;" class="Wdate" value="${assetAllocation.applicationDate}" onclick="WdatePicker({skin:'ext'});"  id="applicationDate" />
					</td> --%>
					<td class="td_span"><input type="text" style="width: 90px;"
								class="Wdate"  value="${param.start}"  name="start" onclick="WdatePicker({skin:'ext'});"
								id="start" />~ <input type="text" style="width: 90px;" class="Wdate"
								value="${param.end}" name="end" id="end" onclick="WdatePicker({skin:'ext'});"/></td>
			</tr>
					<tr >
								<td class="td_text">组合资产编码：</td>
								<td class="td_span"><input type="text" class="my-text" id="document" name="document" value="${processAsset.document}" /></td>
								<td class="td_text">组合资产名称：</td>
								<td class="td_span"><input type="text" class="my-text" id=processAssetsName name="processAssetsName" value="${processAsset.processAssetsName}"  /></td>
						</tr>
			<tr >
								<%-- <td class="td_text">资产类型：</td>
								<td class="td_span">
											<input  name="assetType" type="hidden" id="assetHiddenType" value="${processAsset.assetType}">
											<select id="assetType" onchange="changeType()">
												<option value="">请选择</option>
												<option value="IT">IT资产</option>
												<option value="2">行政资产</option>
												<option value="3">计量器具</option>
												<option value="4">机器设备</option>
											</select>
								</td> --%>
								<td class="td_text">组合资产责任人：</td>
								<td class="td_span"><input type="text" class="my-text" name="assetOwner" id="assetOwner" value="${processAsset.assetOwner}" /></td>
								<td class="td_text">组合资产责任人公司：</td>
								<td class="td_span"><input type="text" class="my-text" name="homeCompany" id="homeCompany" value="${processAsset.homeCompany}" /></td>
			</tr>
			<tr>
								<td class="td_text">组合资产责人成本中心：</td>
								<td class="td_span"><input type="text" class="my-text" name="assetCostcenterName" id="assetCostcenterName" value="${processAsset.assetCostcenterName}" /></td>
							 	<td class="td_text">资产编码：</td>
								<td class="td_span">
									<input class="td_input" name="assetCode" id="assetCode" value="${processAsset.assetCode}">
								</td>
			</tr>			
			</table>
			
			</div>
			</form>
			<table>
			<tr  >
			<td colspan="4" class="buttonbar">
			<input class="btn" type="button" value="查询" id="opInquiry" style="cursor:pointer;" > 
			<input class="btn" type="button" value="申请"  onclick="opApplication('1','数据新增');" style="cursor:pointer;" > 
			<!-- <input class="btn" type="button" value="审批" onclick="accraditation('2','审批');" style="cursor:pointer;">  -->
			<input class="btn" type="button" value="导出" id="btnExport"> 
			<!-- <input class="btn" type="button" value="关闭" onclick="opClose()"> -->
			</td></tr>
			</table>

		
	</div>
	</fieldset>
				<div style="overflow:scroll">
							<table  class="adminlist" style="width: 1900px; " id=gvDataList > 
								<thead>
									<tr  align="center">
										<!-- <th ><input type="checkbox" class="input_checkbox" id="checkAll"/></th> -->
										<th>操作</th>
										<th>操作</th>
										<!-- <th>打印</th> -->
										<th>组合资产编码</th>
										<th>组合资产名称</th>
										<th>申请人</th>
										<th>申请日期</th>
										<!-- <th>审批状态</th> -->
										<th>组合资产责任人</th>
										<th>成本中心</th>
										<th>公司</th>
										<!-- <th>是否为草稿</th> -->
										<!-- <th>是否打印</th> -->
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${processAssetsList.records}" var="processAssets" varStatus="status">
                                <tr class="zt_center" id="tr${processAssets.id}">
                               		<%-- <td><input type="checkbox" class="input_checkbox" value="${processAssets.id}"  name="radio" id="radio" /></td> --%>
									<td><a href="javascript:void(0)" onclick="opUpdate('${processAssets.id}','数据修改');">修改</a></td>
									<td><a href="javascript:void(0)" onclick="opView('${processAssets.id}','数据查看');" style="cursor:pointer;">查看</a></td>
									<%-- <td><a href="javascript:printSeal()"onclick="stamp('${processAssets.id}','打印');" rel="external nofollow" target="_self">打印</a></td> --%>
									<!-- <a ><button class="btn"  type="button"  >打印</button></a> -->
                                    <td id="number${processAssets.id}" style="text-align:left;">${processAssets.document}</td>
                                    <td style="text-align:left;">${processAssets.processAssetsName}</td>
                                    <td style="text-align:left;"><c:if test="${!fn:contains(processAssets.userName, '(')}">(${processAssets.userCode})</c:if>${processAssets.userName}</td>
                                    <td class="td_center"><fmt:formatDate value="${processAssets.applicationDate}"  pattern="yyyy-MM-dd"/></td>
                                    <%-- <td ><a href="javascript:void(0)" onclick="showApproveHistory('${processAssets.document}', '77087')">${processAssets.approvalState }</a></td> --%>
                                    <td style="text-align:left;">${processAssets.assetOwner}</td>
                                    <%-- <td>${assetAllocation.assetType}</td> --%>
                                    <td style="text-align:left;">
                                    	
                                    	 <c:if test="${!fn:contains(processAssets.assetCostcenterName, '(')}">(${processAssets.assetCostcenterCode})</c:if>${processAssets.assetCostcenterName}
                                  <%--   <c:choose>
			 			               <c:when test="${processAssets.assetType=='0'}">
			 				            IT资产
			 			               </c:when>
			 			               <c:when test="${processAssets.assetType=='1'}">
			 				                       行政资产
			 			               </c:when>
			 			               <c:when test="${processAssets.assetType=='2'}">
			 				                       计量器具
			 			               </c:when>
			 			               <c:when test="${processAssets.assetType=='3'}">
			 				                        机器设备
			 			             </c:when>
			 			            </c:choose> --%>
			 			            </td>
                                    <td style="text-align:left;">${processAssets.homeCompany}</td>
                                  <%--   <td><fmt:formatDate value="${processAssets.createDate}"  pattern="yyyy-MM-dd"/></td> --%>
                                    <%-- <td style="text-align:left;">${processAssets.commitType=='0'?"是":"否" }</td> --%>
                                    
                                </tr>
                            </c:forEach>																	
								</tbody>
							</table>
						<tag:paginate offset="${processAssetsList.offset}" total="${processAssetsList.total}" steps="${processAssetsList.pageSize}" uri="${processAssetsList.uri}" formId="formId" />
				</div>

		<!-- 点击増行弹出form -->
		<div id="ysssbmWindow" name="" class="easyui-window" title="所有人员" data-options="iconCls:'icon-save'" style="width:600px;height:330px;padding:5px;" closed="true">

			<form id="searchUserForm">
            <table cellpadding="1" bgcolor="#F9F9F9" class="adminlist">
                <tbody>
                <tr>
                    <td height="26" width="20%" style="white-space:nowrap;" align="right">编码：</td>
                    <td>
                        <input name="userCode" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                               onblur="this.className='inputbtnRefView';" style="width:120px;" value="">
                    </td>

                    <td height="26" width="20%" style="white-space:nowrap;" align="right">名称：</td>

                    <td>
                        <input name="userName" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                               onblur="this.className='inputbtnRefView';" style="width:120px;" value="">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div id="buttons">
            <input type="button" value="查询" id="queryBtn" class="btn">&nbsp;&nbsp;&nbsp;
            <input type="button" value="取消" id="cancelBtn" onclick="closeYHGXControlDialog('txtCUserName_ReferenceDialog','txtCUserName_HidShowFlag');" class="btn">
        </div>
        <div>
            <table class="adminlist" cellspacing="0" rules="all" border="1" id="userTable" style="border-collapse:collapse;width:100%;">
                <thead>
                    <th align="center" scope="col" style="width:30%;">编码</th>
                    <th align="center" scope="col" style="width:70%;">名称</th>
                </thead>
                <tbody id="supplierBody"></tbody>
            </table>
        </div>
        
		</div>
		
		<%-- <script src="${staticURL}/js/allocationIndex.js"></script> --%>
		<script src="${staticURL}/js/jquery-1.11.3.min.js"></script>
		<%-- <script src="${staticURL}/Scripts/JQuery/jquery-1.4.4.min.js" type="text/javascript"></script> --%>
		<script src="${staticURL}/js/jquery.tableCheckbox.js"></script>
		<script src="${staticURL}/js/browser.js"></script>
		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
		<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
		
		<script type="text/javascript">
		
		$("#btnExport").click(function (){
			
			if (window.confirm("确认要导出吗?")) {
				
				window.location.href = "${dynamicURL}/processAssets/Export?"+$('#formId').serialize();
			}
			
		});
		
		
		var dg = frameElement.lhgDG;
	 	var jslength=0;
	 	//页面自动加载
	 	$(function(){  
	 		$("td[id^='draft']").each(function(){
				var id = $(this).attr("id").substr(5);
				var tdobj = document.getElementById('draft'+id);
				if($.trim(tdobj.innerText)=='是'){
					 document.getElementById('ratify'+id).innerHTML="";
				}
			});
	 		
	 		$("#commitType option[value='${param.commitType}']").attr("selected",true);
	 	});

	 	//列表动态yanse
		 GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
	 	
	 	//切换查询条件
	 	function qiehuan() {
	 		
			if($(".trDiv").css("display")=="none"){
		
					$(".trDiv").show();
		
				}else{
		
					$(".trDiv").hide();
		
				}
		}
	    
		 //选择调拨后负责人
		$('#showUserListBtn').click(function(){
			showUserDialog();
	    }); 
	 	function showUserDialog() {
	        var url = "${dynamicURL}/user/getUserList";
	    	var dlg= new $.dialog({ id: 'listUserDialog', title: "所有人员", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
	        
	    }
	 	
	 	//清空调拨后负责人栏
	 	function ClearObj(){
	 		$("#postAllocationPeople").val("");
	 		$("#peopleValue").val("");
	 	}
		//打印
		function printSeal(){ 
	        document.body.innerHTML=document.getElementById('content').innerHTML; 
	        window.print(); 
	    }
		
		//全选
		$("#checkAll").click(function(){
			$("input[name='radio']").prop("checked", $(this).prop("checked"));
		});
		
		//查询
		$("#opInquiry").click(function() {
			/* var date = $("#applicationDate").val();//申请日期 */
		  	var txtBeginDate = $("#start").val();
	    	var txtEndDate = $("#end").val();
	    	if(txtBeginDate>txtEndDate && txtEndDate!=""){
	    		alert("开始时间不能大于结束时间");
	    		return false;
	    	}
			window.location.href = '${dynamicURL}/processAssets/searchProcessAssets?'+ $('#formId').serialize();
		});
		//关闭 
		function opClose(){
			
			var length=$("input:checkbox[name='radio']:checked").length;
			
			if(length==0){
				alert("请选择一条数据");
			}
			if(length>1){
				alert("只能选择一条数据");
			}
			
			if(length==1){
				var id=$("input:checkbox[name='radio']:checked").val();
				if(window.confirm("确认要关闭吗？")){
					$.ajax({
						type:"POST",
					    url:"${dynamicURL}/processAssets/api/deleteProcessAssets",
					    data: {"id":id},
					    success: function(data) {
					     if(data.success){
					    	 alert("已关闭");
					    	 window.location.reload();
					     }else{
					    	 alert(data.errorMessages.join());
					     }
					    }
					});
						
					
				}
			}
			
		}
		
		//申请
		function opApplication(id,title) {
			
			var lurl = "${dynamicURL}/processAssets/processAssetApplication?id="+id; 
            var dlg = new $.dialog({ id: 'application', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
        }
		//审批
	   function accraditation(id,title){
			
			var length=$("input:checkbox[name='radio']:checked").length;
			
			if(length=='1'){
				var id=$("input:checkbox[name='radio']:checked").val();
				
				var lurl = "${dynamicURL}/processAssets/processAssetApprove?id="+id;
				var dlg = new $.dialog({ id: 'approve', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
			}
			if(length=='0'){
				alert("请选择一条要审批的数据");
			}
			if(length>1){
				alert("只能选择一条数据");
			}
		}
		//查看
		function opView(id,title) {
			var lurl = "${dynamicURL}/processAssets/processAssetView?id="+id; 
            var dlg = new $.dialog({ id: 'view', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
        }
		//修改
		function showDiscardSellDialog(id, title) {
			
			var lurl = "${dynamicURL}/processAssets/processAssetUpdate?id=" + id;
			var dlg = new $.dialog({ id: 'update', title: "数据修改", cover: true, page: lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
		} 
		
	   function stamp(id, title){
		   
		   
		   var lurl = "${dynamicURL}/assetAllocation/api/printOrder?id="+id;
		   window.open(lurl); 
		   
	   }
		
		function changeType() {
			var s = $("#assetType").find("option:selected").val();
			if (s == "") {
				$("#assetHiddenType").val("");
				$("#it1").hide();
				$("#it2").hide();
			}
			if (s == "2") {
				$("#assetHiddenType").val("2");
				$("#it1").hide();
				$("#it2").hide();
			}
			if (s == "3") {
				$("#assetHiddenType").val("3");
				$("#it1").hide();
				$("#it2").hide();
			}
			if (s == "4") {
				$("#assetHiddenType").val("4");
				$("#it1").hide();
				$("#it2").hide();
			}
			if (s == "IT") {
				$("#it1").show();
				$("#it2").show();
				$("#assetHiddenType").val("IT");
				$("#assetITType option[value='']").attr("selected",true);
			}
		}
		function changeITType() {
			var s = $("#assetITType").find("option:selected").val();
			if(s==""){
				$("#assetHiddenType").val("IT");
			}
			if (s == "0") {
				$("#assetHiddenType").val("0");
			}
			if (s == "1") {
				$("#assetHiddenType").val("1");
			}
		}
		
		function openYHGXControlDialog(dialogId, showId, coverFlag) {
		    //弹出框定位
		    //document宽度的 1/4 x
		    //document高度的 1/5 y
		    var x = $(document).width() * 0.1;
		    var y = $(document).height() * 0.05;

//		    x = $('#' + dialogId).position().left;
//		    y = $('#' + dialogId).position().top;

		    $('#' + dialogId).css('left', x);
		    $('#' + dialogId).css('top', y);

		    $('#' + dialogId).show();
		    $('#' + showId).val('true');

		    if (coverFlag == 'true') {
		        if ($('#' + coverName).length == 0) {
		            createCoverYHGXControlDialog();
		        }
		        $('#' + coverName).show();
		    }
		    else {
		        $('#' + coverName).hide();
		    }
		}
		//历史审批记录
		function showApproveHistory(document, typeId) {
		    var url = '${dynamicURL}/processAssets/showApproveHistory?document='+document+"&typeId="+typeId;
		    var dlg = new $.dialog({ id: 'approveHistory', title: "审批", cover: true, page: url, rang: true, width: 700, height: 450, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
           
		}
		
		//修改
		function opUpdate(id, title) {
		    $.ajax({
				type: "POST",
				url: "${dynamicURL}/processAssets/ifUpdatePermitted?id=" + id,
				
				success: function (data) {
					if (data.success) {
						showDiscardSellDialog(id);
					} else {
						alert(data.errorMessages.join());
					}
				}
			});
			
		} 
		
		
		</script>
	</body>

</html>