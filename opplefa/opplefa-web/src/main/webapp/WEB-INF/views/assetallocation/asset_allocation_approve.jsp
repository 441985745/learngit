 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="Stylesheet" type="text/css" href="${staticURL}/assetallocation/css/allocationapplicationdetail.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
	</head>
	
<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
		        资产调拨单</div>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<input type="button" value="获取资产编码" class="btn" id="getAssetCode"  style="display: none;">
		<input style="display: none" type="button" value="更新SAP" id="updateSAPBtn" class="btn" onclick="updateSAP()">
	    <input type="button" value="同意" id="agreeBtn" class="btn">
		<input type="button" value="退回" id="denyBtn" class="btn">
    <c:choose>
        <c:when test="${assetAllocation.approvalState == '等待审批'}"> </c:when>
        <c:otherwise>
            <input type="button" id="dropBtn" class="btn" value="弃审" /> 
        </c:otherwise>
    </c:choose>
         
		<input type="button" value="返回" onclick="dg.cancel();" class="btn">
		<input type="button" value="打印" onclick="showAllocationDialog('${assetAllocation.id}','打印');" class="btn">
	</div>
	
	<app:approvalTag billCode="${assetAllocation.document}" typeId="77002"></app:approvalTag>
	
	<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
	  <div class="imgtitle"></div>
		  资产信息
	</div>
	<div style="margin-top: 2px;">
	 <form id="discardForm">
	  <table style="table-layout: fixed;width:100%;">
	    	<tr>
				
				<td class="td_right"><span class="span_color">*</span>流程单据号:</td>
				<td class="td_left"><input name="document" type="text"
					value="${assetAllocation.document}" id="document" readonly="readonly" class="td_input" /> 
			  	<input name="isMail" id="isMail"  type="hidden" />
	            <input name="isPhoneMessage" id="isPhoneMessage"  type="hidden" />
	            <input name="idea" id="idea"  type="hidden" />
	            <input name="activityId" id="activityId" type="hidden">
	            <input id="getIsExpenseAssets" value="${assetAllocation.isExpenseAssets}" type="hidden">
	            <input id="assetAllocationId" value="${assetAllocation.id}" type="hidden">
	            <input id="isUpdateSap" type="hidden" value="${assetAllocation.isUpdateSap}">
	            <input id="isGetSapCode" type="hidden" value="${assetAllocation.isGetSapCode}">
	            <input value="${isAssetAccounting}" id="isAssetAccounting" type="hidden">
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属部门:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="departmentName" value="${assetAllocation.departmentName }" >
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属公司:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="companyName" value="${assetAllocation.companyName }" >
				</td>
			</tr>
		  <tr>
	 		<td class="td_right">申请人:</td>
			<td class="td_left">
				<input name="createBy" class="td_input" type="text" readonly="readonly" value="${assetAllocation.createBy}" id="">
			</td>
			<td class="td_right">申请日期:</td>
			<td class="td_left">
			<input name="createDate" type="text" class="td_input" readonly="readonly" value="${assetAllocation.applicationDate}" id="">
			</td>
	 		<td class="td_right">资产类型:</td>
			<td class="td_left">
			<c:if test="${assetAllocation.assetType=='0'}">
                                	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="IT资产" id="lblCom">
            </c:if>

            <c:if test="${assetAllocation.assetType=='1'}">
                                	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="行政资产" id="lblCom">
            </c:if>
            <c:if test="${assetAllocation.assetType=='2'}">
                                	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="计量器具" id="lblCom">
            </c:if>
            <c:if test="${assetAllocation.assetType=='3'}">
                                	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="机器设备" id="lblCom">
            </c:if>
			</td>
	 	 </tr>
	 	 <tr>
 			<td class="td_right">调拨后责任人:</td>
			<td class="td_left">
			<input name="postAllocationPeople" class="td_input" readonly="readonly" type="text" value="${assetAllocation.postAllocationPeople}" id="lblManager">
			<input name="postPeopleCode" type="hidden" value="${assetAllocation.postPeopleCode}">
			</td>
	 		 <td class="td_right">调拨后责任人部门:</td>
			<td class="td_left">
			<input name="postAllocationDepartment" class="td_input" readonly="readonly" type="text" value="${assetAllocation.postAllocationDepartment}" id="txtApplyDate"  /> 
			<input name="postDepartmentCode" type="hidden" value="${assetAllocation.postDepartmentCode}">
			</td>
			
			<td class="td_right">调拨后归属公司:</td>
			<td class="td_left">
				<input name="postAllocationCompany" class="td_input" readonly="readonly" type="text" value="${assetAllocation.postAllocationCompany}" >
				<input id="postAllocationCompanyCode" class="td_input" readonly="readonly" type="hidden" value="${assetAllocation.postCompanyCode}" >
			</td>
			
	 	 </tr>
	 	 <tr>
	 		 <td class="td_right">调拨后成本中心:</td>
			<td class="td_left">
			<input name="postAllocationCostCenter" type="text" class="td_input" readonly="readonly" value="${assetAllocation.postAllocationCostCenter}" id="lblCom">
			<input name="postCostcenterCode" id="postCostcenterCode" type="hidden" value="${assetAllocation.postCostcenterCode}">
			</td>

			<td class="td_right">是否费用化资产:</td>
			<td class="td_left">
			<c:if test="${assetAllocation.isExpenseAssets=='0'}">
                                	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="否" id="lblCom">
                                </c:if>
			<c:if test="${assetAllocation.isExpenseAssets=='1'}">
                                	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="是" id="lblCom">
                                </c:if>
			</td>
			<%-- <td class="td_left">
			<input name="lblCom" type="text" class="td_input" readonly="readonly" value="${assetAllocation.assetType}" id="lblCom">
			</td> --%>
			<td class="td_right">平台:</td>
			<td class="td_left">
				<input name="lblPurCode" class="td_input" readonly="readonly" type="text" value="${assetAllocation.platform}" >
			</td>
	 	  </tr>
	 	  <tr>
	 		
	 		<td class="td_right">调配方式:</td>
			<td class="td_left">
				<input name="allocationMethod" id="allocationMethod" class="td_input" readonly="readonly" type="text" value="${assetAllocation.allocationMethod}" >
			</td>
			<td class="td_right">办公地点:</td>
			<td class="td_left">
				<input name="officeLocation" class="td_input" readonly="readonly" type="text" value="${assetAllocation.officeLocation}" >
			</td>
	 			
			<td class="td_right">调拨后存放位置:</td>
			<td class="td_left">
				<input name="postAllocationStoragePlace" class="td_input" readonly="readonly" type="text" value="${assetAllocation.postAllocationStoragePlace}" >
			</td>
	 	  </tr>
	 	  <tr id="trWorkshop" style="display: none;">
	 		<td class="td_right">车间类型:</td>
			<td class="td_left">
				<input name="lblPurCode" class="td_input" readonly="readonly" type="text" value="${assetAllocation.workshopType}" >
			</td>
	
	 	 </tr>
	 	</table>
	 </form>
			<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					资产信息
			</div>
									
		<div style="overflow-x: scroll; width: 100%;">
			<table border="0" cellpadding="3px" cellspacing="0" width="160%">
					<tbody>
							
							<tr>
								 <th><input type="hidden" id="checkAll"></th>
								<th id="thShow">新资产编码</th>
									<th >资产编码</th>
									<th>资产名称</th>
									<th>资产类型</th>
									<th>规格型号</th>
									<th>原值</th>
									<th>净值</th>								
									<th>使用状态</th>
									<th>成本中心</th>
									<th>原公司会计凭证号</th>
									<th>现公司会计凭证号</th>
									<th>原公司年度</th>
									<th>现公司年度</th>
							</tr>
							<c:forEach items="${assetAllocation.assetAllocationDetail}" var="assetAllocationDetail" varStatus="status">
							<tr style="text-align: center;">
								<td><input type="hidden" name="radio" value="${assetAllocationDetail.generalAssetId}"></td> 
								<td class="assetHide">${assetAllocationDetail.newAssetCode}</td>
								<td>${assetAllocationDetail.assetCode}</td>
								<td>${assetAllocationDetail.assetName}</td>
								<td>
								<c:choose>
						 			<c:when test="${assetAllocationDetail.assetType=='0'}">
						 				IT资产
						 			</c:when>
					 				<c:when test="${assetAllocationDetail.assetType=='1'}">
						 				行政资产
						 			</c:when>
						 			<c:when test="${assetAllocationDetail.assetType=='2'}">
						 				计量器具
						 			</c:when>
					 				<c:when test="${assetAllocationDetail.assetType=='3'}">
						 				机器设备
						 			</c:when>
						 			
						 		</c:choose>	 
								</td>
							
								<td>${assetAllocationDetail.specificationModel}</td>
								<td>${assetAllocationDetail.assetOriginalValue}</td>
								<td>${assetAllocationDetail.assetNetValue}</td>
								<td>
								<c:choose>	 
									<c:when test="${assetAllocationDetail.useState=='0'}">
						 				在用
						 			</c:when>
					 				<c:when test="${assetAllocationDetail.useState=='1'}">
						 				闲置中
						 			</c:when>
						 			<c:when test="${assetAllocationDetail.useState=='2'}">
						 				大修理中
						 			</c:when>
					 				<c:when test="${assetAllocationDetail.useState=='3'}">
						 				改造中
						 			</c:when>
									<c:when test="${assetAllocationDetail.useState=='4'}">
						 				报废申请中
						 			</c:when>
						 			<c:when test="${assetAllocationDetail.useState=='5'}">
						 				已报废
						 			</c:when>
					 				<c:when test="${assetAllocationDetail.useState=='6'}">
						 				变卖申请中
						 			</c:when>
						 			<c:when test="${assetAllocationDetail.useState=='7'}">
						 				已变卖
						 			</c:when>
					 			</c:choose>	 
								</td>
								<td>${assetAllocationDetail.persentDepartment}</td>
								<td>${assetAllocationDetail.oldAccountancyVouche }</td>
								<td>${assetAllocationDetail.nowAccountancyVoucher }</td>
								<td>${assetAllocationDetail.oldAccountYear }</td>
								<td>${assetAllocationDetail.nowAccountYear }</td> 
							</tr>
							</c:forEach>
					</tbody>	
									
			</table>
		</div>
		 
	</div>
	<div style="height: 50px;margin-top: 10px;">
		<table>
				<tr>
					<td align="right" class="tdtitle">原值合计:</td>
					<td class="tdtext"><input name="originalValueSum" type="text"
						id="originalValueSum" class="colorblur" readonly="readonly" value="${assetAllocation.originalValueSum}"></td>
					<td align="right" class="tdtitle">净值合计:</td>
					<td class="tdtext"><input name="allocationAssetsValueSum" type="text"
						id="allocationAssetsValueSum" class="colorblur" value="${assetAllocation.allocationAssetsValueSum}" readonly="readonly"></td>
					<td align="right" class="tdtitle">原因说明:</td>
					<td class="tdtext"><input  type="text"
						id="cause" class="colorblur" readonly="readonly" value="${assetAllocation.allocationReason}"></td>
									
				</tr>
			</table>
					<table cellpadding="0" cellspacing="0" border="0"  style="table-layout: fixed;width:100%;">
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
				<table cellpadding="0" cellspacing="0" border="0" style="table-layout: fixed;width:100%;">
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
	                                <table class="adminlist" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
	                                    <thead>
	                                    <tr style="white-space: nowrap;">
	                                        <th scope="col">审批人</th>
	                                        <th scope="col">审批日期</th>
	                                        <th scope="col">审批结果</th>
	                                       
	                                        <th scope="col">审批意见</th>
	                                    </tr>
	                                    </thead>
	                                    <tbody>                                    
	                                    <c:forEach items="${appHistoryPager.records}" var="appHistory" varStatus="status">
	                                        <tr style="white-space: nowrap;">
	                                            <td class="tdleft">${appHistory.examineUser}</td>
	                                            <td class="tdleft"><fmt:formatDate value='${appHistory.examineDate}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
	                                            <td class="tdleft">${appHistory.examineResult}</td>
	                                            
	                                            <td class="tdleft">${appHistory.examineAdvice}</td>
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
		</div>
		
	<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>  
	<%-- <script src="${staticURL}/js/assetallocation/allocationView.js"></script> --%>
	<script src="${staticURL}/js/jquery.tableCheckbox.js"></script>
	<script src="${staticURL}/js/browser.js"></script>
	<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
	<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>

	<script type="text/javascript">
	var dg = frameElement.lhgDG;
	
	var bianMa = "";
	var v=0;
	var s = "";
	var array = new Array();
	$(function(){
		var bl=$("#isAssetAccounting").val();
		var allocationMethod=$("#allocationMethod").val();
		var getIsExpenseAssets=$("#getIsExpenseAssets").val();
		var postAllocationCompanyCode=$("#postAllocationCompanyCode").val();
		var postCostcenterCode=$("#postCostcenterCode").val();
		
		if(allocationMethod=="公司间调拨"&&bl=="Y"&&getIsExpenseAssets=='0'){
			$("#getAssetCode").show();
			
		}
		if(allocationMethod!="公司间调拨"){
			$("#thShow").hide();
			$(".assetHide").hide();
		}
		if(bl=="Y"&&getIsExpenseAssets=='0'){
			$("#updateSAPBtn").show();
			
		}else{
			$("#updateSAPBtn").hide();
		}
	
		var assetType="${assetAllocation.assetType}";
		if(assetType=="3"){
			$("#trWorkshop").show();
		}
	});

	$("#agreeBtn").click(function (){
		var getIsExpenseAssets=$("#getIsExpenseAssets").val();
		var isUpdate=$("#isUpdateSap").val();
		var bl=$("#isAssetAccounting").val();
		
		if(bl=="Y"&&isUpdate=="1"){
			showAgreeDialog();
		}
		if(bl=="Y"&&isUpdate=="0"&&getIsExpenseAssets=="0"){
			alert("请更新SAP!");
		}
		if(bl!="Y"){
			showAgreeDialog();
		}
		if(bl=="Y"&&getIsExpenseAssets=="1"){
			showAgreeDialog();
		}
		
	});
	$("#denyBtn").click(function(){
		var isUpdate=$("#isUpdateSap").val();
		if(isUpdate=="1"){
			alert("已更新过SAP！不允许退回！");
		}
		if(isUpdate=="0"){
			showDenyDialog();
		}
		
	});
	
	$("#dropBtn").click(function(){
		var isUpdate=$("#isUpdateSap").val();
		if(isUpdate=="1"){
			alert("已更新过SAP！不允许弃审！");
		}
		if(isUpdate=="0"){
			showDropDialog();
		}
	});
	function showAgreeDialog() {
		var document =$("#document").val();
        var url = "${dynamicURL}/workflow/showAgreeDialog?parentWindowId=approve&document=" + document + "&typeId="+77002;

        var dlg = new dg.curWin.$.dialog({ id: 'agreeDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();
        return dlg;
    }
	
	function showDenyDialog() {
		var document =$("#document").val();
        var url = "${dynamicURL}/workflow/showDenyDialog?parentWindowId=approve&document=" + document + "&typeId="+77002;

        var dlg = new dg.curWin.$.dialog({ id: 'denyDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();
        return dlg;
    }
	
	function showDropDialog() {
        var url = "${dynamicURL}/workflow/showDropDialog?parentWindowId=approve";

        var dlg = new dg.curWin.$.dialog({ id: 'dropDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();
        return dlg;
    }
	function commitAgree(isMail, isPhoneMessage, idea,activityId) {
		
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
        $('#idea').val(idea);
        $("#activityId").val(activityId);
        $('#agreeBtn').val('提交...');
       $.ajax({
            type:"POST",
            url: "${dynamicURL}/assetAllocation/agreeAssetAllocation?"+$('#discardForm').serialize(),
            contentType: "application/json; charset=gb2312",
            success: function (data) {
                if (data.success) {
                    alert("审批成功");
                    dg.curWin.location = dg.curWin.location;
                } else {
                	
                	var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*审批成功.*$");
                    if (reg.test(str)) {
                        dg.curWin.location = dg.curWin.location;
                    }
                    $('#agreeBtn').val('同意');
                    
                }
            }
        });
    }
	
	 function commitDeny(isMail, isPhoneMessage, idea,activityId) {
	        $('#isMail').val(isMail);
	        $('#isPhoneMessage').val(isPhoneMessage);
	        $('#idea').val(idea);
	        $("#activityId").val(activityId);
	        $('#btnDeny').val('提交...');
	       
	        $.ajax({
	            type: "POST",
	            url: "${dynamicURL}/assetAllocation/denyAssetAllocation?"+$('#discardForm').serialize(),
	           // contentType: "application/json; charset=gb2312",
	            success: function (data) {
	                if (data.success) {
	                    alert("审批成功");
                        dg.curWin.location = dg.curWin.location;
	                } else {
	                	var str = data.errorMessages;
	                    alert(str);
	                    var reg = new RegExp("^.*审批成功.*$");
	                    if (reg.test(str)) {
	                        dg.curWin.location = dg.curWin.location;
	                    }
	                    $('#btnDeny').val('退回');
	                   
	                }
	            }
	        });
	    } 
	 
	 function commitDrop(isMail, isPhoneMessage, idea) {
	        $('#isMail').val(isMail);
	        $('#isPhoneMessage').val(isPhoneMessage);
	        $('#idea').val(idea);

	        $('#btnDrop').val('提交...');
	        $.ajax({
	            type: "POST",
	            url: "${dynamicURL}/assetAllocation/dropAssetAllocation?"+$('#discardForm').serialize(),
	            //contentType: "application/json; charset=gb2312",
	            success: function (data) {
	                if (data.success) {
	                    alert("审批成功");
                        dg.curWin.location = dg.curWin.location;
	                } else {
	                	var str = data.errorMessages;
	                    alert(str);
	                    var reg = new RegExp("^.*审批成功.*$");
	                    if (reg.test(str)) {
	                        dg.curWin.location = dg.curWin.location;
	                    }
	                    $('#btnDrop').val('弃审');
	                    
	                }
	            }
	        });
	    }
	 function updateSAP(){
		 $("#updateSAPBtn").val("更新SAPing");
		 $("#updateSAPBtn").attr("disabled",true);
		 if($("#isGetSapCode").val()=="0"&&$("#allocationMethod").val()=="公司间调拨"){
			 alert("请先获取资产编码");
			 $("#updateSAPBtn").val("更新SAP");
			 $("#updateSAPBtn").attr("disabled",false);
			 return false;
		 }
		 if($("#isUpdateSap").val()=="0"){
			 $.ajax({
				 type:"POST",
				 url:"${dynamicURL}/assetAllocation/updateSAP?"+$("#discardForm").serialize(),
				success:function(data){
					
					if(data.success){
						 alert("更新SAP成功！");
						 $('#updateSAPBtn').val('更新SAP');
						 $("#updateSAPBtn").attr("disabled",false);
						 window.location.reload();
						 
					}else{
						$('#updateSAPBtn').val('更新SAP');
						$("#updateSAPBtn").attr("disabled",false);
	                    alert("编码"+data.errorMessages+"更新失败");
					}
				}
			 });
		 }else{
			 alert("已更新过SAP！");
			 $('#updateSAPBtn').val('更新SAP');
			 $("#updateSAPBtn").attr("disabled",false);
		 }
	 }
	 
 	$("#getAssetCode").click(function(){
		$("#getAssetCode").val("获取资产编码ing");
		$("#getAssetCode").attr("disabled",true);
		if($("#isGetSapCode").val()=="1"){
			alert("请勿重复获取！");
			$("#getAssetCode").val("获取资产编码");
			$("#getAssetCode").attr("disabled",false);
			return false;
		}
		var ids="";
		$("input[name='radio']").each(function(){
			var value=$(this).val();
			
			if(ids==""){
				ids=value;
			}else{
				ids=ids+","+value;
			}
		});
		
		var postAllocationCompanyCode=$("#postAllocationCompanyCode").val();
		var postCostcenterCode=$("#postCostcenterCode").val();
		var assetAllocationId=$("#assetAllocationId").val();
		$.ajax({
			type:"POST",
			url:"${dynamicURL}/assetAllocation/api/getAssetCodeSap",
			data:{ids:ids,postCompanyCode:postAllocationCompanyCode,postCostCenterCode:postCostcenterCode,assetAllocationId:assetAllocationId},
			success:function(data){
				if (data.success) {
					alert("获取成功！");
					 window.location.reload();
					$("#thShow").show();
					$("#tdShow").show();
					
				}else{
					alert(data.errorMessages.join());
					$("#getAssetCode").val("获取资产编码");
					$("#getAssetCode").attr("disabled",false);
				}
			
			}
		});
	});
 	 $('#btnUpload').click(function () {
         var fileElementId =$(this).prev().attr('id');
         var file = $('#fileToUpload').val();
         if (file == "") {
             alert("请选择需要上传的文件！");
             return false;
         }

         var documentNo = $('#document').val();
         if ("" == documentNo) {
             alert('单据号不可为空');
             return false;
         }

         uploadFile('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}', documentNo, $('#uploadTable > tbody'),fileElementId);

     });

     $('#btnDelFile').click(function () {
         deleteFiles('${dynamicURL}', "uploadTable");
     });
     $.ajax({
 		url : '${dynamicURL}/file/getFilesByDocument',
 		data : {
 			'document' : $("#document").val()
 		},
 		dataType : 'json',
 		type : 'POST',
 		success : function(data) {
 			if (data.length > 0) {
 				$('#uploadTable .noItems').remove();
 				$
 						.each(
 								data,
 								function(i, item) {
 									 var uploaduser = '${ sessionScope._user_nick_name}';
 				                        if(uploaduser == item.createUserName) {
 				                            $('#uploadTable > tbody').append('<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
 				                                + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '"name=documentId   />'
 				                                + '</td><td scope="col">' + item.fileName
 				                                + '</td><td scope="col">' + item.createUserName
 				                                + '</td><td style="width:9%">' + item.createDate
 				                                + '</td><td align="center" style="width:10%"><a href="'+'${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
 				                                + '</td></tr>');
 				                        } else {
 				                            $('#uploadTable > tbody').append('<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
 				                                + '<td style="text-align:center;"><input type="checkbox" uuid="" name=documentId />'
 				                                + '</td><td scope="col">' + item.fileName
 				                                + '</td><td scope="col">' + item.createUserName
 				                                + '</td><td style="width:9%">' + item.createDate
 				                                + '</td><td align="center" style="width:10%"><a href="'+'${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
 				                                + '</td></tr>');
 				                        }
 								});
 			}

 		}
 	});
    function showAllocationDialog(id){
        var lurl = "${dynamicURL}/assetAllocation/printOrder?id=" + id + "&typeId=77002";
        window.open(lurl);
    }
	</script>
</body>
</html>