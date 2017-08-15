 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
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
		       组合资产</div>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<input type="button"  value="返回" size="25" id="fanHui" class="btn">
	</div>
	<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
	<div class="imgtitle"></div>
					基本信息
			</div>
	<div style="margin-top: 2px;">
	  <form id="discardForm">
	   	 <table>
	 		<tr>
				
				<td class="td_right"><span class="span_color">*</span>组合资产编码:</td>
				<td class="td_left"><input name="document" type="text" id="document"
					value="${processAssets.document}" readonly="readonly" class="td_input" /> 
					<input type="hidden" name='userCode' value="${processAssets.userCode }">
					<input type="hidden" name='userName' value="${processAssets.userName }">
					<input type="hidden" name="companyCode" value="${processAssets.companyCode }">
					<input type="hidden" name="departmentCode" value="${processAssets.departmentCode }">
					<input type="hidden" name="id" value="${processAssets.id }">
					<input type="hidden" name="costCenterCode" id="costCenterCode">
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属部门:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="departmentName" value="${processAssets.departmentName }" >
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属公司:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="companyName" value="${processAssets.companyName }" >
				</td>
			</tr>
		 	<tr>
		 	
		 		<td class="td_right">申请人:</td>
		 		<td class="td_left">
					<input name="createBy" type="text" value="${processAssets.userName}" readonly="readonly" class="td_input"/>
	                <input name="" type="text" value="${sessionScope._user_name}" class="controlhidden" />
	                <input type="hidden" name="commitType" id="commitType" />
				</td>
				<td class="td_right">申请日期:</td>
				<td class="td_left">
					<input class="td_input"  name="applicationDate" type="text" readonly="readonly" value="${processAssets.applicationDate}" id="applicationDate">
				</td>
		 		<td class="td_right"><span class="span_color">*</span>组合资产责任人:</td>
				<td class="td_left">
				<input name="assetOwnerCode" id="assetOwnerCode" type="hidden">
				<input   class="td_input" name="assetOwner" type="text"
					readonly="readonly" value="${processAssets.assetOwner}" id="assetOwner">
					<!-- <input type="hidden" id="peopleValue" > -->
					</td>
			
		 	</tr>
		 <tr>
		 	<td class="td_right"><span class="span_color">*</span>组合资产责任人部门:</td>
	 		<td class="td_left">
				<input  class="td_input" value="${processAssets.assetDepartmentName }" readonly="readonly" name="assetDepartmentName" type="text"
					readonly="readonly" id="assetDepartmentName">
			</td>
			
			<td class="td_right"><span class="span_color">*</span>组合资产责任人归属公司:</td>
	 		<td class="td_left">
	 			
				<input class="td_input"   name="homeCompany" id="homeCompany" type="text" value="${processAssets.homeCompany}" readonly="readonly">
				<input type="hidden"  name="homeCompanyCode" id="homeCompanyCode" value="${processAssets.homeCompanyCode}">
			</td>
			 <td class="td_right"><span class="span_color">*</span>组合资产责任人成本中心:</td>
	 		<td class="td_left">
				<input class="td_input" readonly="readonly" name="assetCostcenterName" type="text" id="assetCostcenterName" value="${processAssets.assetCostcenterName}">
				<input type="hidden" name="assetCostcenterCode" id="assetCostcenterCode" value="${processAssets.assetCostcenterCode}">
			</td>
		 </tr>
	 	<tr>
	 	
			<td class="td_right"><span class="span_color">*</span>平台:</td>
			<td class="td_left">
				<input id="platform" name="platform" class="td_input" value="${processAssets.platform}" type="text">
            </td>
			<td class="td_right"><span class="span_color">*</span>办公地点:</td>
	 		<td class="td_left">
				<input class="td_input" value="${processAssets.officeLocation }" readonly="readonly">
            </td>
			<td class="td_right"><span class="span_color">*</span>存放位置:</td>
	 		<td class="td_left">
				<input class="td_input" value="${processAssets.storagePlace }" readonly="readonly">
				 
			</td>
	 	</tr>
	 	<tr>
	 		<td class="td_right">车间类型:</td>
	 		<td class="td_left">
				<input class="td_input"  name="workshopType" type="text" value="${processAssets.workshopType}" id="workshopType" /> 
			</td>
			<td class="td_right"><span class="span_color">*</span>组合资产名称:</td>
	 		<td class="td_left">
				<input class="td_input"  name="processAssetsName" type="text" value="${processAssets.processAssetsName}" id="processAssetsName" /> 
			</td>
	 	</tr>
	 	<!-- <tr>
	 		<td class="td_right">车间类型:</td>
	 		<td class="td_left">
				<select style="width: 173px;" class="td_select select_color"  name="workshopType"  id=""> 
					<option class="option_color" value="车床">车床</option> 
					<option class="option_color" value="行政资产">机床</option> 
					<option class="option_color" value="计量器具">模具</option> 
					<option class="option_color" value="机器设备">印刷</option> 
					<option class="option_color" value="房屋建筑物">最后</option> 
				</select>
			</td>
			<td class="td_right"><div style="display: none;" id="it1">IT资产类：</div></td>																	 
			<td class="td_left">
			<div style="display: none;" id="it2">
			    
				<select class="td_select select_color" id="assetITType" onchange="changeITType()">
					<option class="option_color" value="" >请选择</option> 
					<option class="option_color" value="0">电脑类</option> 
					<option class="option_color" value="1">非电脑类</option> 
				</select>
				</div>
			</td>
	 	</tr> -->
	 	</table>
	 </form>
	 	</div>
			
		
			<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					资产信息
			</div>
			
			<div style="margin-left: 0px; margin-top: 0px;">
				<input type="button" name="addLine" value="增行" style="cursor:pointer;"  id="showAddDialog" class="btn">
				<input type="button" name="" value="删行" id="opDelete" class="btn">
			</div>
									
		<div style="overflow-x: scroll; width: 100%;">
			<table  border="0" cellpadding="3px" cellspacing="0" width="150%">
			
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll"></th>
								<th>资产编码</th>
								<th>资产名称</th>
								<th>现责任人</th>
								<th>归属公司</th>
								<th>原值</th>
								<th>净值</th>
								<th>使用状态</th>
								<th>成本中心</th>
								<!-- <th>存放位置</th> -->

						</tr>
				</thead>
							<c:forEach items="${processAssets.generalAssetList}" var="gneralAsset">
								<tr style="text-align: center;" id="tr${gneralAsset.id}">
									<td><input type='checkbox'
										value="${gneralAsset.id},${gneralAsset.assetCode},${gneralAsset.companyCode},${gneralAsset.assetOriginalValue},${gneralAsset.assetNetValue}" name="radio"
										id="kuang${gneralAsset.id}" />
									</td>
									<td>${gneralAsset.assetCode}</td>
									<td>${gneralAsset.assetName}</td>
					
									<td>${gneralAsset.assetPerson}</td>
									<td>${gneralAsset.companyCode}</td>
									<td>${gneralAsset.assetOriginalValue}</td>
									<td>${gneralAsset.assetNetValue}</td>
									
									<td><c:choose>
							<c:when test="${gneralAsset.useState=='0'}"> 
									    	在用
									   </c:when>
							<c:when test="${gneralAsset.useState=='1'}"> 
									    	闲置中
									   </c:when>
							<c:when test="${gneralAsset.useState=='2'}"> 
									    	大修理中  
									   </c:when>
							<c:when test="${gneralAsset.useState=='3'}"> 
									    	改造中
									   </c:when>
							<c:when test="${gneralAsset.useState=='4'}"> 
									    	报废申请中
									   </c:when>
							<c:when test="${gneralAsset.useState=='5'}"> 
									    	已报废
									   </c:when>
							<c:when test="${gneralAsset.useState=='6'}"> 
									    	变卖申请中
									   </c:when>
							<c:when test="${gneralAsset.useState=='7'}"> 
									    	已变卖
									   </c:when>
						</c:choose></td>
									<td>${gneralAsset.costCenterCode}</td>
								<%-- 	<td>${gneralAsset.location}</td> --%>
									
								</tr>
				</c:forEach>
						<tbody id="tbody">
					
						</tbody>
								
			</table>
		</div>
	</div>
	<div style="height: 50px;margin-top: 10px;">
		<table>
			<tr>
				<td align="right" class="tdtitle"><span class="span_color">*</span>原值合计:</td>
				<td class="tdtext"><input name="originalValueSum" type="text"
					id="originalValueSum" class="colorblur" value="${processAssets.originalValueSum}" readonly="readonly"></td>
				<td align="right" class="tdtitle"><span class="span_color">*</span>净值合计:</td>
				<td class="tdtext"><input name="assetNetValueSum" type="text"
					id="assetNetValueSum" class="colorblur" value="${processAssets.assetNetValueSum }" readonly="readonly"></td>
				<td align="right" class="tdtitle"><span class="span_color">*</span>原因说明:</td>
				<td class="tdtext"><input  type="text"
					id="cause" class="colorblur" value="${processAssets.cause}"></td>
								
			</tr>
			
		</table>
			<table cellpadding="0" cellspacing="0" border="0"  style="width:950px;">
				   <tbody>
				   <tr>
					   <td class="titlefontsize" align="left" style="width: 150px">
						   <div class="imgtitle"></div>
						   附件
					   </td>
					   <td>
						   <!-- <input type="file" name="multipartFile" id="fileToUpload" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';"> -->
						   <!-- <input type="submit" name="btnUpload" value="上传" id="btnUpload" class="btn">
						   <input type="submit" name="btnDelFile" value="删除" id="btnDelFile" class="btn"></td> -->
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

	<%-- <script src="${staticURL}/js/assetallocation/allocationView.js"></script> --%>
	<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>  
	<script src="${staticURL}/js/jquery.tableCheckbox.js"></script>
	<script src="${staticURL}/js/browser.js"></script>
	<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
	<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script src="${staticURL}/Scripts/Purchase/DemandOrder/DemandOrder.js" type="text/javascript"></script>
	<script type="text/javascript">
	dg = frameElement.lhgDG;
	 //返回关闭申请弹窗
	$("#fanHui").click(function(){
		//关闭窗口
        dg.cancel();
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
										$('#uploadTable > tbody')
												.append(
														'<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
																+ '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '" name=documentId />'
																+ '</td><td scope="col">'
																+ item.fileName
																+ '</td><td scope="col">'
																+ item.createUserName
																+ '</td><td style="width:9%">'
																+ item.createDate
																+ '</td><td align="center" style="width:10%"><a href="'
																+ '${dynamicURL}/file/downloadFile?fileuuid='
																+ item.fileuuid
																+ '">下载</a>'
																+ '</td></tr>');
									});
				}

			}
		});
	</script>
</body>
</html>