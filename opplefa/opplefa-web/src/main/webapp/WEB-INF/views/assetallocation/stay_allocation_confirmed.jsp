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
	    <input type="button" value="同意" id="agreeBtn" class="btn" onclick="agreeIt(0)">
		<input type="button" value="退回" id="denyBtn" class="btn" onclick="notAgree(1)">
		 <input type="button"  class="btn"  value="取消确认" id="cancelBtn" onclick="cancelConfirm()"> 
		<input type="button" value="返回" onclick="dg.cancel();" class="btn">
	</div>
	<%-- 	<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
	  <div class="imgtitle"></div>
		  待确认人
	</div>
		<div style="text-align: center;">
			${ass }
		</div> --%>
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
				<input name="txtDemandOrderId" class="td_input" type="text" readonly="readonly" value="${assetAllocation.createBy}" id="">
			</td>
			<td class="td_right">申请日期:</td>
			<td class="td_left">
			<input name="lblUser" type="text" class="td_input" readonly="readonly" value="${assetAllocation.applicationDate}" id="">
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
			<input name="lblManager" class="td_input" readonly="readonly" type="text" value="${assetAllocation.postAllocationPeople}" id="lblManager">
			</td>
			 <td class="td_right">调拨后责任人部门:</td>
			<td class="td_left">
			<input name="txtApplyDate" class="td_input" readonly="readonly" type="text" value="${assetAllocation.postAllocationDepartment}" id="txtApplyDate"  /> 
			</td>
			
			<td class="td_right">调拨后归属公司:</td>
			<td class="td_left">
				<input name="lblPurCode" class="td_input" readonly="readonly" type="text" value="${assetAllocation.postAllocationCompany}" >
			</td>
			
	 	 </tr>
	 	 <tr>
	 	
			 <td class="td_right">调拨后成本中心:</td>
			<td class="td_left">
			<input name="lblCom" type="text" class="td_input" readonly="readonly" value="${assetAllocation.postAllocationCostCenter}" id="lblCom">
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
				<input name="lblPurCode" class="td_input" readonly="readonly" type="text" value="${assetAllocation.allocationMethod}" >
			</td>
			<td class="td_right">办公地点:</td>
			<td class="td_left">
				<input name="lblPurCode" class="td_input" readonly="readonly" type="text" value="${assetAllocation.officeLocation}" >
			</td>
	 			
			<td class="td_right">调拨后存放位置:</td>
			<td class="td_left">
				<input name="lblPurCode" class="td_input" readonly="readonly" type="text" value="${assetAllocation.postAllocationStoragePlace}" >
			</td>
	 	  </tr>
	 	  <tr>
	 		<td class="td_right" id="tdShop" style="display: none;">车间类型:</td>
			<td class="td_left" id="tdShopInput" style="display: none;">
				<input name="lblPurCode" class="td_input" readonly="readonly" type="text" value="${assetAllocation.workshopType}" >
			</td>
			<td class="td_right"><span class="span_color">*</span>退回意见:</td>
			<td class="td_left" colspan="2">
				<input id="backOpinion" class="td_input" type="text"  >
			</td>
	 	 </tr>
	 	</table>
	 </form>
			<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					资产信息
			</div>
									
		<div style="overflow-x: scroll; width: 100%;">
			<table border="0" cellpadding="3px" cellspacing="0" width="120%">
					<tbody>
							
							<tr>
								<th><input type="checkbox" id="checkAll"></th>
									
									<th>资产编码</th>
									<th>资产名称</th>
									<th>资产类型</th>
									<th>规格型号</th>
									<th>原值</th>
									<th>净值</th>
									
									<th>使用状态</th>
									<th>成本中心</th>
									<th>原存放位置</th>
									
							</tr>
							<c:forEach items="${assetAllocation.generalAsset}" var="generalAsset" varStatus="status">
							<tr style="text-align: center;">
								<td><input type="checkbox" name="radio"></td>
								
								<td>${generalAsset.assetCode}</td>
								<td>${generalAsset.assetName}</td>
								<td><c:choose>
								   <c:when test="${generalAsset.assetType=='0'}"> 
								    	IT资产
								   </c:when>
								   <c:when test="${generalAsset.assetType=='1'}"> 
								    	行政资产
								   </c:when>
								   <c:when test="${generalAsset.assetType=='2'}"> 
								    	计量器具  
								   </c:when>
								   <c:when test="${generalAsset.assetType=='3'}"> 
								    	机器设备
								   </c:when>
								</c:choose></td>
								<td>${generalAsset.specificationModel}</td>
								<td>${generalAsset.assetOriginalValue}</td>
								<td>${generalAsset.assetNetValue}</td>
							
								<td><c:choose>	 
									<c:when test="${generalAsset.useState=='0'}">
						 				在用
						 			</c:when>
					 				<c:when test="${generalAsset.useState=='1'}">
						 				闲置中
						 			</c:when>
						 			<c:when test="${generalAsset.useState=='2'}">
						 				大修理中
						 			</c:when>
					 				<c:when test="${generalAsset.useState=='3'}">
						 				改造中
						 			</c:when>
									<c:when test="${generalAsset.useState=='4'}">
						 				报废申请中
						 			</c:when>
						 			<c:when test="${generalAsset.useState=='5'}">
						 				已报废
						 			</c:when>
					 				<c:when test="${generalAsset.useState=='6'}">
						 				变卖申请中
						 			</c:when>
						 			<c:when test="${generalAsset.useState=='7'}">
						 				已变卖
						 			</c:when>
					 			</c:choose>	 </td>
								<td>${generalAsset.costCenterCode}</td>
							
							</tr>
							</c:forEach>
					</tbody>	
									
			</table>
		</div>
		 
	</div>
		<div style="height: 50px;margin-top: 10px;">
			<table >
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
					   <td class="titlefontsize"  align="left" style="width: 150px">
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
/* 	var moeth="${assetAllocation.allocationMethod}"
		if(moeth!="公司间调拨"){
			$("#thShow").hide();
			$(".tdRemove").remove();
		} */
		var assetType="${assetAllocation.assetType}";
		if(assetType=="3"){
			$("#tdShopInput").show();
			$("#tdShop").show();
		}
	function agreeIt(sta){
		var document=$("#document").val();
		$("#agreeBtn").val("同意ing");
		$("#agreeBtn").attr("disabled",true);
		$.ajax({
			type:"post",
			url:"${dynamicURL}/assetAllocation/api/confirmAllocation",
			data:{document:document,status:sta},
			success:function(data){
				if (data.success) {
					alert("确认成功！");
					
                    dg.curWin.location = dg.curWin.location;
				}else{
					alert(data.errorMessages.join());
					$("#agreeBtn").val("同意");
					$("#agreeBtn").attr("disabled",false);
				}
			}
		});
	}
	function notAgree(sta){
		var document=$("#document").val();
		var backOpinion=$("#backOpinion").val();
		if(backOpinion!=""&&backOpinion!=null){
			$.ajax({
				type:"post",
				url:"${dynamicURL}/assetAllocation/api/confirmAllocation",
				data:{document:document,status:sta,backOpinion:backOpinion},
				success:function(data){
					if (data.success) {
						alert("退回成功！");
						 
                        dg.curWin.location = dg.curWin.location;
		                
					}else{
						alert(data.errorMessages.join());
					}
				}
			});
		}else{
			alert("退回意见不可为空！");
		}
	}
	function cancelConfirm(){
		var document=$("#document").val();
		
			$.ajax({
				type:"post",
				url:"${dynamicURL}/assetAllocation/api/cancelConfirmAllocation",
				data:{document:document},
				success:function(data){
					if (data.success) {
						alert("取消确认成功！");
						
                        dg.curWin.location = dg.curWin.location;
		                
					}else{
						alert(data.errorMessages.join());
					}
				}
			});
		
	
	}
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
	</script>
</body>
</html>