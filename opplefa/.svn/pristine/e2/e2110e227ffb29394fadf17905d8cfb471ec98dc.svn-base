<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
 <%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton"%>
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
		<div>
    <input type="hidden" name="hfQueryStatus" id="hfQueryStatus" value="show">
    <table cellspacing="0" cellpadding="0"
           style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form  id="formId">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif" style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                       	</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="t_ShipInportInfo" class="adminlist" style="visibility: visible;">
                            <tbody>
                    <tr >
					<td class="td_text">单据号：</td>
					<td class="td_span">
					<input type="text" style="width: 195px;" value="${assetAllocation.document}"  name="document" />
					
					</td>
					<td class="td_text">申请日期：</td>
					<%-- <td class="td_span">
						<input type="text" style="width: 90px;" class="Wdate" value="${assetAllocation.applicationDate}" onclick="WdatePicker({skin:'ext'});"  id="applicationDate" />~
						<input type="text" style="width: 90px;" class="Wdate" value="${assetAllocation.applicationDate}" onclick="WdatePicker({skin:'ext'});"  id="applicationDate" />
					</td> --%>
					<td class="td_span"><input type="text" style="width: 90px;" id="start"
								class="Wdate"  value="${param.start}"  name="start" onclick="WdatePicker({skin:'ext'});"
								id="" />~ <input type="text" style="width: 90px;" class="Wdate"
								value="${param.end}" name="end" onclick="WdatePicker({skin:'ext'});" id="end"/></td>
			</tr>
			<tr >
					<%-- <td class="td_text">原归属公司：</td>
					<td class="td_span"><input type="text" class="my-text"  value="${assetAllocation.originalCompany}" name="originalCompany" id="originalCompany" /></td> --%>
						<td class="td_text">申请人：</td>
					<td class="td_span">
					<input type="text" style="width: 195px;" value="${assetAllocation.createBy}"  name="createBy"  id="createBy" />
					
					</td>
					<td class="td_text">调拨后负责人部门：</td>
					<td class="td_span"><input type="text" style="width: 195px;" class="my-text" value="${assetAllocation.postAllocationDepartment}" id="postAllocationDepartment"  name="postAllocationDepartment"/></td>
						
			</tr>
			<tr >
				<td class="td_text">调拨前成本中心：</td>
				<td class="td_span"><input type="text" style="width: 195px;" name="beforeAllocationCostCenter"
										   id="beforeAllocationCostCenter"
										   value="${assetAllocation.beforeAllocationCostCenter}"/></td>
				<td class="td_text">调拨前归属公司：</td>
				<td class="td_span"><input type="text" style="width: 195px;" class="my-text"
										   id="beforeAllocationCompany" name="beforeAllocationCompany"
										   value="${assetAllocation.beforeAllocationCompany}"/></td>
					
			</tr>
					<tr>
						<td class="td_text">调拨后成本中心：</td>
						<td class="td_span"><input type="text" style="width: 195px;" name="postAllocationCostCenter"
												   id="postAllocationCostCenter"
												   value="${assetAllocation.postAllocationCostCenter}"/></td>
						<td class="td_text">调拨后归属公司：</td>
						<td class="td_span"><input type="text" style="width: 195px;" class="my-text"
												   id="postAllocationCompany" name="postAllocationCompany"
												   value="${assetAllocation.postAllocationCompany}"/></td>

					</tr>
					<tr>
						<td class="td_text">资产编码：</td>
						<td class="td_span"><input type="text" style="width: 195px;" name="assetCode" id="assetCode"
												   value="${assetAllocation.assetCode}"/></td>
						<td class="td_text">资产名称：</td>
						<td class="td_span"><input type="text" style="width: 195px;" class="my-text" id="assetName"
												   name="assetName" value="${assetAllocation.assetName}"/></td>

					</tr>
			<tr >
				<td class="td_text">审批状态：</td>
				<td class="td_span">
					<select class="my-text" id="approvalState" name="approvalState">
								<option value="">请选择</option>
						<option value="等待审批">等待审批</option>
						<option value="正在审批">正在审批</option>
						<option value="已完成">已完成</option>
						<option value="退回">退回</option>
					</select>
				</td>
				<td class="td_text">调配方式：</td>
				<td class="td_span">
					<select class="my-text" id="allocationMethod" name="allocationMethod">
						<option value="">请选择</option>
						<option value="公司间调拨">公司间调拨</option>
						<option value="个人间调拨">个人间调拨</option>
						<option value="部门间调拨">部门间调拨</option>
					</select>
				</td>

			</tr>
					<tr>
						<td class="td_text">资产类型：</td>
						<td class="td_span">
							<select class="my-text" id="assetType" name="assetType">
								<option value="">请选择</option>
								<option value="0">IT资产</option>
								<option value="1">行政资产</option>
								<option value="2">计量器具</option>
								<option value="3">机器设备</option>
							</select>
						</td>
						<td class="td_text">是否为一次性费用化资产：</td>
						<td class="td_span">
							<select class="my-text" id="isExpenseAssets" name="isExpenseAssets">
								<option value="">请选择</option>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</td>

					</tr>
						<tr >
						<td class="td_text">调拨后负责人：</td>
					<%-- <td class="td_span">
						<div class="my-text">
						<input type="text" style="width: 145px;" value="${assetAllocation.postAllocationPeople}" id="postAllocationPeople" name="postAllocationPeople" disabled/>
						<input type="hidden" id="peopleValue" name="postAllocationPeople">
						<input id="gvdMeeting_btnClearsk_0"  class="btnRefView" style="margin-left: 0px;width: 20px;height: 25px;" onclick="ClearObj();" title="点击清空参照文本框内容" value="X" type="button">
						<input id="showUserListBtn" class="btnRefView" style="margin-left: 0px;width: 20px;height: 25px;" title="点击打开参照文本框内容" value="…" type="button">
						</div>
					</td> --%>
					<td class="td_span"><input type="text"style="width: 195px;"value="${assetAllocation.postAllocationPeople}"  name="postAllocationPeople"  id="postAllocationPeople" /></td>
				<%-- 				<td class="td_text">办公地点：</td>
								<td class="td_span"><input type="text" style="width: 195px;" value="${assetAllocation.officeLocation}"  name="officeLocation"  id="officeLocation" /></td> --%>
								<td class="td_text">是否为草稿：</td>
								<td class="td_span">
									<select class="my-text" id="commitType" name="commitType">
										<option value="">请选择</option>
								      	<option value="0" ${param.commitType=="0"?"selected='selected'" : ""}>是</option>
                                        <option value="1" ${param.commitType=="1"||param.commitType=='null'||param.commitType==null||param.commitType==''?"selected='selected'" : ""}>否</option>
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
            <td class="buttonbar">
                &nbsp;&nbsp;
             <auth:authTag code="Query">
       		<input class="btn" type="button" value="查询" id="opInquiry" style="cursor:pointer;" > 
       		 </auth:authTag>
      		  <auth:authTag code="Apply">
      		  <input class="btn" type="button" value="新增"  onclick="opApplication('1','数据新增');" style="cursor:pointer;" >
      		  </auth:authTag>
       		  <auth:authTag code="Check">
       		  <input class="btn" type="button" value="审批" onclick="accraditation('2','数据审批');" style="cursor:pointer;"> 
       		  </auth:authTag>
       		  <auth:authTag code="Export">
       		  <input class="btn" type="button" value="导出" id="btnExport"> 
       		  </auth:authTag>
       		  <auth:authTag code="Confirm"> 
       		  <input class="btn" type="button" value="确认" id="btnSubmit" onclick="btnSubmit()">
       		  </auth:authTag> 
       		  <auth:authTag code="Delete">
       		  	<input class="btn" type="button" value="删除" onclick="opClose()"> 
       		  </auth:authTag>

            </td>
        </tr>
        <tr >
            <td valign="top">
                <div id="gridview" style="width: auto;  height:auto;">
                    <div>
                        <table class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border-collapse: collapse;">
                            <tbody>
                    <tr  align="center">
										<th style="width:20px;"><input type="checkbox" class="input_checkbox" id="checkAll"/></th>
										<th>操作</th>
										<th>操作</th>
										<th>打印</th>
										<th>单据号</th>
										<th>审批状态</th>
										<th>下一处理人</th>
										<th>确认人</th>
										<th>确认状态</th>
										<th>申请人</th>
										<th>申请日期</th>
										<th>归属公司</th>
										<th>调配方式</th>
										<th>资产类型</th>
										<th>调拨后责任人</th>
										<th>是否为草稿</th>	
										<th>是否打印</th>	
									</tr>
							
								<tbody>
								<c:forEach items="${assetAllocationPager.records}" var="assetAllocation" varStatus="status">
                                <tr id="tr${assetAllocation.id}">
                               		<td><input type="checkbox" class="input_checkbox" value="${assetAllocation.id}"  name="radio" id="radio" /></td>
									<td><a href="javascript:void(0)" onclick="opUpdate('${assetAllocation.document}','数据修改');">修改</a></td>
									<td><a href="javascript:void(0)" onclick="opView('${assetAllocation.document}','数据查看');" style="cursor:pointer;">查看</a></td>
									<td><a href="javascript:void(0)"onclick="assetAllocationStamp('${assetAllocation.id}','打印');" rel="external nofollow" target="_self">打印</a></td>
									<!-- <a ><button class="btn"  type="button"  >打印</button></a> -->
                                    <td class="td_font"id="number${assetAllocation.id}">${assetAllocation.document}</td>
                                    <td><a href="javascript:void(0)" onclick="showApproveHistory('${assetAllocation.document}', '77002')">${assetAllocation.approvalState }</a></td>
	                                <td class="td_font">${assetAllocation.nextHandlerName}</td>
	                                <td class="td_font">${assetAllocation.confirmPerson}</td>
	                                <td class="td_font">
	                                <c:choose>
			 			               <c:when test="${assetAllocation.confirmStatus=='0'}">
			 				        	    等待确认
			 			               </c:when>
			 			            
			 			               <c:when test="${assetAllocation.confirmStatus=='1'}">
 				                      	 确认中
			 			               </c:when>
			 			               <c:when test="${assetAllocation.confirmStatus=='2'}">
			 				           	 确认完毕
			 			               </c:when>
			 			         		<c:when test="${assetAllocation.confirmStatus=='3'}">
			 				           	 退回
			 			               </c:when>
			 			            </c:choose>
									</td>
	                                <td class="td_font">${assetAllocation.createBy}</td>
                                    <td class="td_date"><fmt:formatDate value="${assetAllocation.applicationDate}"  pattern="yyyy-MM-dd"/></td>
                                    
                                    <td class="td_font">
                        
                                    <c:if test="${!fn:contains(assetAllocation.postAllocationCompany, '(')}">(${assetAllocation.postCompanyCode})</c:if>${assetAllocation.postAllocationCompany}
                                    </td>
                                    
                                    <td class="td_font">${assetAllocation.allocationMethod}</td>
                                    <%-- <td>${assetAllocation.assetType}</td> --%>
                                    <td class="td_font">
                                    <c:choose>
			 			               <c:when test="${assetAllocation.assetType=='0'}">
			 				            IT资产
			 			               </c:when>
			 			            
			 			               <c:when test="${assetAllocation.assetType=='1'}">
			 				                       行政资产
			 			               </c:when>
			 			               <c:when test="${assetAllocation.assetType=='2'}">
			 				                       计量器具
			 			               </c:when>
			 			               <c:when test="${assetAllocation.assetType=='3'}">
			 				                        机器设备
			 			             </c:when>
			 			            </c:choose>
			 			            </td>
                                    <td class="td_font">${assetAllocation.postAllocationPeople}</td>
                                    <td class="td_font">${assetAllocation.commitType=='0'?"是":"否" }</td>
                                    <td class="td_font">
                                    <c:choose>
								 		  <c:when test="${assetAllocation.isStamp=='Y'}"> 
							    			是
							  			 </c:when>
						  			 	  <c:when test="${assetAllocation.isStamp=='N'}"> 
							    			否
							  			 </c:when>
							  			 <c:otherwise>
							  			 	否
							  			 </c:otherwise>
						  			 </c:choose></td>
                                </tr>
                            </c:forEach>								
                            </tbody>
                        </table>
                        <tag:paginate offset="${assetAllocationPager.offset}" total="${assetAllocationPager.total}" steps="${assetAllocationPager.pageSize}" uri="${assetAllocationPager.uri}" formId="formId" />
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

	
		<%-- <script src="${staticURL}/js/allocationIndex.js"></script> --%>
		<%-- <script src="${staticURL}/js/jquery-1.11.3.min.js"></script> --%>
		<%-- <script src="${staticURL}/Scripts/JQuery/jquery-1.4.4.min.js" type="text/javascript"></script> --%>
		<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>  
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
				
				window.location.href = "${dynamicURL}/assetAllocation/Export?"+$('#formId').serialize();
			}
			
		});
/*  		 function autoSize() {
		        $("#gridview").width($(window).width());
		        $("#gridview").height(
		                $(window).height() - $("#Query").height()
		                - $("#Button").height()
		                - $("#AspNetPager").height());
		    }
		    autoSize();
		    window.onresize = autoSize;  */
		    
		
		
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
	 		
	 		/* $("#commitType option[value='${param.commitType}']").attr("selected",true); */
	 		$("#isExpenseAssets option[value='${param.isExpenseAssets}']").attr("selected",true);
	 		$("#assetType option[value='${param.assetType}']").attr("selected",true);
	 		/* $("#isExpenseAssets").val("${assetAllocation.isExpenseAssets}"); */
	 		$("#officeLocation").val("${assetAllocation.officeLocation}");
	 		$("#platform").val("${assetAllocation.platform}");
			$("#approvalState").val('${assetAllocation.approvalState}');
			$("#allocationMethod").val('${assetAllocation.allocationMethod}');
	 /* 		$("#assetType").val("${assetAllocation.assetType}");
	 		if ('${param.assetType}' == "" || '${param.assetType}' == '0'||'${param.assetType}' == '1') {
				$("#assetType").attr("value","IT");
				$("#assetITType option[value='${param.assetType}']").attr("selected",true);
				$("#it2").show();
				$("#it1").show();
			} else {
				$("#assetType option[value='${param.assetType}']").attr("selected",true);
				$("#it1").hide();
				$("#it2").hide();
			} */
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
/* 		//打印
		function printSeal(){ 
	        document.body.innerHTML=document.getElementById('content').innerHTML; 
	        window.print(); 
	    }
		 */
		//全选

		
		//查询
		$("#opInquiry").click(function() {
			/* var date = $("#applicationDate").val();//申请日期 */
		  	var txtBeginDate = $("#start").val();
	    	var txtEndDate = $("#end").val();
	    	if(txtBeginDate>txtEndDate && txtEndDate!=""){
	    		alert("开始时间不能大于结束时间");
	    		return false;
	    	}
			window.location.href = '${dynamicURL}/assetAllocation/searchAssetAllocations?'+ $('#formId').serialize();
		});
		//关闭 
		function opClose(){
			
			var length=$("input:checkbox[name='radio']:checked").length;
			
			if(length==0){
				alert("请选择一条数据");
			}
			var ids="";
			if(length>1 || length==1){
				$("input:checkbox[name='radio']:checked").each(function(){
					var id=$(this).val();
					if(""==ids){
						ids=id;
					}else{
						ids=ids+","+id;
					}
				});
			}
			
			
				
				if(window.confirm("确认要删除吗？")){
					$.ajax({
						type:"POST",
					    url:"${dynamicURL}/assetAllocation/api/deleteAssetAllocation",
					    data: {"ids":ids},
					    success: function(data) {
					     if(data.success){
					    	 alert("删除成功！");
					    	 window.location.reload();
					     }else{
					    	 alert(data.errorMessages.join());
					     }
					    }
					});
						
					
				
			}
			
		}
		
		//申请
		function opApplication(id,title) {
			
			var lurl = "${dynamicURL}/assetAllocation/assetAllocationApplication?id="+id; 
            var dlg = new $.dialog({ id: 'application', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
        }
		//审批
	   function accraditation(id,title){
			
			var length=$("input:checkbox[name='radio']:checked").length;
		
			if(length=='1'){
				var id=$("input:checkbox[name='radio']:checked").val();
				
				var lurl = "${dynamicURL}/assetAllocation/assetAllocationApprove?id="+id;
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
			var lurl = "${dynamicURL}/assetAllocation/assetAllocationView?id="+id; 
            var dlg = new $.dialog({ id: 'view', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
        }
		//修改
		function showDiscardSellDialog(id, title) {
			var lurl = "${dynamicURL}/assetAllocation/assetAllocationUpdate?id=" + id;
			var dlg = new $.dialog({ id: 'update', title: "数据修改", cover: true, page: lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
		} 
		function assetAllocationStamp(id,title){
				$.ajax({
					type: "POST",
					url: "${dynamicURL}/assetAllocation/api/assetAllocationStamp?id=" + id + "&typeId=77002",
					
					success: function (data) {
						if (data.success) {
							showAllocationDialog(id);
						} else {
							alert(data.errorMessages.join());
						}
					}
					
				});  
	 	 }
		function showAllocationDialog(id){
			var lurl = "${dynamicURL}/assetAllocation/printOrder?id=" + id + "&typeId=77002";
			window.open(lurl);
//			var dlg = new $.dialog({ id: 'update', title: "资产调拨打印", cover: true, page: lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
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
		    var url = '${dynamicURL}/assetAllocation/showApproveHistory?document='+document+"&typeId="+typeId;
		    var dlg = new $.dialog({ id: 'approveHistory', title: "审批", cover: true, page: url, rang: true, width: 700, height: 450, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
           
		}
		
		//修改
		function opUpdate(id, title) {
		    $.ajax({
				type: "POST",
				url: "${dynamicURL}/assetAllocation/ifUpdatePermitted?id=" + id,
				
				success: function (data) {
					if (data.success) {
						showDiscardSellDialog(id);
					} else {
						alert(data.errorMessages.join());
					}
				}
			});
			
		} 
		
		function btnSubmit(){
			var length=$("input:checkbox[name='radio']:checked").length;
			
			if(length=='1'){
				var id=$("input:checkbox[name='radio']:checked").val();
				
				var url = '${dynamicURL}/assetAllocation/showAllocationConfirmed?id=' + id;
			    var dlg = new $.dialog({ id: 'allocationConfirmed', title: "待确认", cover: true, page: url, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
		    }
			if(length=='0'){
				alert("请选择一条要确认的数据");
			}
			if(length>1){
				alert("只能选择一条数据");
			}
		}
		$("#checkAll").click(function(){
			
			$("input[name='radio']").each(function(){
			
				if($(this).attr("checked")) 
				{
					$(this).removeAttr("checked"); 
				} else {
					$(this).attr("checked","true"); 
				} 
				}); 
			});
		
		</script>
	</body>

</html>