<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调拨申请</title>
<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css"
	rel="stylesheet" />
<link rel="Stylesheet" type="text/css"
	href="${staticURL}/assetallocation/css/allocationapplicationapply.css">
<link rel="stylesheet" type="text/css"
	href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>

<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
		        报废变卖单</div>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<input type="submit" name="" value="提交" onclick="submit(1)" id="submitBtn"
			class="btn"> <input type="button" name="" value="保存草稿"
			id="conservation" class="btn" onclick="submit(0)"> <input type="button" name=""
			value="返回" onclick="" id="fanHui" class="btn">
	</div>
	<div class="titlefontsize"
		style="width: 99%; height: 30px; margin-top: 10px;">
		<div class="imgtitle"></div>
		基本信息
	</div>
	<form id="discardForm">
		<table style="table-layout: fixed;width:100%;">
			<tr>
					
				<td class="td_right"><span class="span_color">*</span>流程单据号:</td>
				<td class="td_left"><input name="document" id="document" type="text"
					value="${discardSell.document}" readonly="readonly" class="td_input" /> 
					<input type="hidden" name='userCode' value="${discardSell.userCode }">
					<input type="hidden" name="companyCode" value="${discardSell.companyCode }">
					<input type="hidden" name="departmentCode" value="${discardSell.departmentCode }">
					<input type="hidden" name="userName" value="${discardSell.userName }">
					<input type="hidden"  id="iaMoney" value="${discardSell.discardMoneySum}" >
				  	<input name="isMail" id="isMail"  type="hidden" />
		            <input name="isPhoneMessage" id="isPhoneMessage"  type="hidden" />
		            <input name="idea" id="idea"  type="hidden" />
		            <input name="activityId" id="activityId" type="hidden">
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属部门:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="departmentName" value="${discardSell.departmentName }" >
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属公司:</td>
				<td class="td_left">
					<input name="oldCommitType" value="${discardSell.commitType }" type="hidden">
					<input type="text" class="td_input" readonly="readonly" name="departmentName" value="${discardSell.companyName }" >
				</td>
			</tr>
			<tr>

				<td class="td_right">申请人:<input type="hidden" name="commitType" id="isDraft"></td>
				<td class="td_left"><input name="createBy" type="text"
					value="${discardSell.createBy}" readonly="readonly"
					class="td_input" /> <input name="id" value="${discardSell.id}"
					type="hidden" /></td>
				<td class="td_right">申请日期:</td>
				<td class="td_left"><input class="td_input" name="createDate" type="text"
					readonly="readonly" value="<fmt:formatDate value="${discardSell.createDate}"  pattern="yyyy-MM-dd"/>" id="applicationDate"></td>
				<td class="td_right"><span class="span_color">*</span>办公地点:</td>
				<td class="td_left">
					<select class="selectCheck" name="officeLocation" style="width: 163px;height: 24px;" id="officeLocation" >
							<option value="">请选择</option>
		 				<c:forEach items="${addresslist}" var="userInfo" varStatus="status">
		 					
		 					<c:choose>
									  <c:when test="${userInfo.caddressName==discardSell.officeLocation}">
									  <option value="${userInfo.caddressCode}" selected="selected" >${userInfo.caddressName}</option>
									  </c:when>
									  <c:otherwise>
									  <option value="${userInfo.caddressCode}">${userInfo.caddressName}</option>
									  </c:otherwise>
									</c:choose>
		 				</c:forEach>
	 				</select>
				</td>
				
			</tr>
			<tr>
			    <td class="td_right">是否一次性费用化资产:</td>
				<td class="td_left"><select style="width: 163px;"
					class="td_select" name="isExpenseAssets"
					id="isExpenseAssets">
						<option value="">请选择</option>
						<option  value="0">否</option>
						<option  value="1">是</option>
				</select></td>
				<td class="td_right"><span class="span_color">*</span>资产所属部门:</td>
				<td class="td_left"><input style="width: 110px;" class="td_input" value="${discardSell.subordinateDepartment }" name="subordinateDepartment" type="text"
					readonly="readonly"   id="subordinateDepartment"><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanDep();"><input id="btntxtYsssbmShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
					<input id="subordinateDepartmentCode" type="hidden" value="${discardSell.subordinateDepartmentCode}" name="subordinateDepartmentCode">
					</td>
					<td class="td_right">资产归属公司:</td>
					<td class="td_left">
					<select id="companyName"  style="width: 163px;" class="td_select" onchange="getCostCenter()">
						<option id="updateCompany" value="${discardSell.adscriptionCompanyCode}">${discardSell.adscriptionCompany}</option>
					</select>
					<input readonly='readonly' class="td_input" name='adscriptionCompany' value="${discardSell.adscriptionCompany}" type="hidden"  id='adscriptionCompany' >
					<input type="hidden" name="adscriptionCompanyCode" id="adscriptionCompanyCode" value="${discardSell.adscriptionCompanyCode }">
					</td>	
			</tr>
			<tr>
				<td class="td_right">资产所属成本中心:</td>
				<td class="td_left">
						<input class="td_input" id="costCenterName" name="costCenterName" value="${discardSell.costCenterName }" type="text">
						<input  class="td_input" id="costCenterCode" name="costCenterCode" value="${discardSell.costCenterCode }" type="hidden">
				</td>
	
				<td class="td_right"><span class="span_color">*</span>平台:</td>
				<td class="td_left"><input class="td_input" class="td_input" name="platform" id="platform" readonly="readonly" value="${discardSell.platform}"> </td>
				<td class="td_right">资产类型:</td>
				<td class="td_left"><select style="width: 163px;"
					class="td_select" name='assetType' onchange="changeType()"
					id="assetType">
						<option value="">请选择</option>
						<option  value="0">IT资产</option>
						<option  value="1">行政资产</option>
						<option  value="2">计量器具</option>
						<option  value="3">机器设备</option>
				</select></td>
	
			</tr>
			<tr  >
				<td class="td_right">车间类型:</td>
				<td class="td_left"> <input class="td_input" name="workshopType" id="workshopType" value="${discardSell.workshopType}" readonly="readonly"></td>
				<td class="td_right"><span class="span_color">*</span>申请原因:</td>
				<td class="td_left">
					<input class="td_input" name="application" id="application" value="${discardSell.application}">	
				</td>	
			</tr>
		</table>
	</form>
	
	<div class="titlefontsize"
		style="width: 99%; height: 30px; margin-top: 10px;">
		<div class="imgtitle"></div>
		资产信息
	</div>

	<div>
		<input type="button" name="addLine" value="增行"
			style="cursor: pointer;" id="showAddDialog" class="btn"> <input
			type="button" name="" value="删行" id="opDelete" class="btn">
	</div>
	<div style="overflow-x: scroll; width: 100%;">
		<table border="0" cellpadding="3px" cellspacing="0" width="150%">

			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"></th>
					<th>资产编码</th>
					<th>资产名称</th>
					
					<th>现资产责任人</th>
					<th>规格型号</th>
					<th>原值</th>
					<th>净值</th>
					<th>报废百分比</th>
					<th>报废金额</th>
					<th>使用状态</th>
					<th>归属公司</th>
					<th>成本中心</th>
					<th>现存放位置</th>
					
				</tr>
			</thead>
				
			<tbody id="tbody">
					<c:forEach items="${discardSell.listGneralAssets}" var="gneralAsset">
								<tr style="text-align: center;" id="tr${gneralAsset.id}">
									<td><input type='checkbox'
										value="${gneralAsset.id},${gneralAsset.assetCode},${gneralAsset.assetOriginalValue},${gneralAsset.assetNetValue}" name="radio"
										id="kuang${gneralAsset.id}" />
									</td>
									<td>${gneralAsset.assetCode}</td>
									<td>${gneralAsset.assetName}</td>
									<td>${gneralAsset.assetPerson}</td>
									<td>${gneralAsset.specificationModel}</td>
									<td>${gneralAsset.assetOriginalValue}</td>
									<td>${gneralAsset.assetNetValue}</td>
									<td><input name='percent' style="width: 80px;" id='percent${gneralAsset.id}' onblur='count("${gneralAsset.id}","${gneralAsset.assetNetValue}")' value="${gneralAsset.discardPrecent}"></td>
									<td><input disabled="disabled" style="width: 80px;" name="percentMoney" id='percentMoney${gneralAsset.id}' value='${gneralAsset.discardMoney}' onblur='count("${gneralAsset.id}","${gneralAsset.assetNetValue}")'> </td>
									
									<td>
										<c:choose>
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
									   	   <c:when test="${gneralAsset.useState=='3'}"> 
										    	报废申请中
										   </c:when>
									   	   <c:when test="${gneralAsset.useState=='3'}"> 
										    	已报废
										   </c:when>
									   	   <c:when test="${gneralAsset.useState=='3'}"> 
										    	变卖申请中
										   </c:when>
										    <c:when test="${gneralAsset.useState=='3'}"> 
										    	已变卖
										   </c:when>
										
										
										</c:choose>
									</td>
									<td>${gneralAsset.companyCode}</td>
									<td>${gneralAsset.costCenterCode}</td>
									<td>${gneralAsset.location}</td>
								</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
		<div>
		<table style="table-layout: fixed;width:100%;">
			<tr>
				<td class="td_right">原值合计</td>
				<td class="td_left"><input id="assetOriginalValueSum" value='${discardSell.assetOriginalValueSum}' readonly="readonly"/> </td>
				<td class="td_right">净值合计</td>
				<td class="td_left"><input id="assetNetValueSum"  value='${discardSell.assetNetValueSum }' readonly="readonly"/></td>
				<td class="td_right">报废金额合计</td>
				<td class="td_left"><input id="discardMoneySum" value='${discardSell.discardMoneySum }' readonly="readonly"/></td>
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
	</div>
	<div>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
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
	                                        <th scope="col">审批金额</th>
	                                        <th scope="col">审批意见</th>
	                                    </tr>
	                                    </thead>
	                                    <tbody>
	                                    <c:forEach items="${appHistoryPager.records}" var="appHistory" varStatus="status">
	                                        <tr style="white-space: nowrap;">
	                                            <td class="tdleft">${appHistory.examineUser}</td>
	                                            <td class="tdleft"><fmt:formatDate value='${appHistory.examineDate}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
	                                            <td class="tdleft">${appHistory.examineResult}</td>
	                                            <td class="tdleft">${appHistory.examineMoney}</td>
	                                            <td class="tdleft">${appHistory.examineAdvice}</td>
	                                        </tr>
	                                    </c:forEach>
	                                    </tbody>
	                                </table>
	                                <tag:paginate offset="${appHistoryPager.offset}" total="${appHistoryPager.total}"
	                                              steps="${appHistoryPager.pageSize}" uri="${appHistoryPager.uri}"
	                                              formId=""/>
	                            </div>
	                        </div>
	                    </td>
	                </tr>
                </tbody>
            </table>
		</div>
	<!-- <div class="titlefontsize"
		style="width: 99%; height: 30px; margin-top: 20px;">
		<div class="imgtitle"></div>
		历史维修记录
	</div>

	<div style="overflow-x: scroll; width: 100%;">
		<table border="0" cellpadding="3px" cellspacing="0" width="120%">

			<thead>
				<tr>
					<th><input type="checkbox" id=""></th>
					<th>序号</th>
					<th>归口管理编码</th>
					<th>资产编码</th>
					<th>资产名称</th>
					<th>规格型号</th>
					<th>资产责任人</th>
					<th>申请维修日期</th>
					<th>故障原因</th>
				</tr>

			</thead>


		</table>
	</div> -->


	<!-- 点击增行-->
	<div id="addApplyWindow" name="" class="easyui-window" title="添加申请"
		data-options="iconCls:'icon-save'"
		style="width: 850px; height: 440px; padding: 5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding: 10px;">

				<form action="" id="">
					<table id="Table8" border="0" cellpadding="3px" cellspacing="0"
						width="70%">
						<tbody>
							<tr>
								<td align="right" class="tdtitle">资产名称:</td>
								<td class="tdtext"><input name="txtAssetsName" type="text"
									id="txtAssetsName" class="colorblur"></td>
								<td align="right" class="tdtitle">资产编码:</td>
								<td class="tdtext"><input name="txtAssetsCode" type="text"
									id="txtAssetsCode" class="colorblur"></td>
						

							</tr>
							<tr>
								<td align="right" class="tdtitle">现责任人:</td>
								<td class="tdtext"><input name="txtAssetPerson" type="text"
									id="txtAssetPerson" class="colorblur"></td>
								<td align="right" class="tdtitle">型号规格/品牌:</td>
								<td class="tdtext"><input name="txtPutUnderManageCode"
									type="text" id="txtPutUnderManageCode" class="colorblur"></td>
								
							</tr>
						</tbody>
					</table>

				</form>
				<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
					<input type="button" name="btnCancel" value="查询" id="inquiry"
						class="btn"> <input type="button" name="confirm"
						value="确定" onclick="confirm('radioFrame')" id="confirm"
						class="btn">
				</div>

				<div style="margin-top: 10px">


					<table id="Table8" cellpadding="0px" cellspacing="0"
						class="tablenoborder">
						<tbody>

							<tr>
								<td colspan="4">
									<table id="Table10" width="100%" border="0" cellpadding="0"
										cellspacing="0" style="table-layout: fixed;">
										<tbody>
											<tr>
												<td class="tdtitle" style="text-align: right" colspan="2">
													<div id="div1" class="divGrid"
														style="overflow-x: scroll; overflow-y: visible;">
														<div>

															<table id="table_add_detail" class="adminlist"
																cellspacing="0" rules="all" border="1" id="gridDataList"
																style="border-collapse: collapse;">

																<tr style="white-space: nowrap;">
																<tr>
																	<th><input type="checkbox" id="checkAllFrame"></th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>资产编码</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>资产名称</th>
																
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>现责任人</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>规格型号/品牌</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>原值</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>净值</th>
																	
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>使用状态</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>归属公司</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>成本中心</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>存放位置</th>
																	
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
								</td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	<div id="ysssbmWindow" name="" class="easyui-window" title="需求/申请部门" data-options="iconCls:'icon-save'" style="width:600px;height:330px;padding:5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
			  <ul id="tree1"></ul>  
			</div>
			
		</div>
	</div>
	<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
	<script
		src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js"
		type="text/javascript"></script>
	<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
	<script type="text/javascript"
		src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		var dg = frameElement.lhgDG;
		//获得当前时间
		$(function(){
			var myDate = new Date();
			var month=myDate.getMonth()+1;
			var  time=myDate.getFullYear()+"-"+month+"-"+myDate.getDate();
			$("#applicationDate").val(time);
			
			   $("#assetType option[value=${discardSell.assetType}]").attr("selected", "selected");
			   $("#isExpenseAssets option[value=${discardSell.isExpenseAssets}]").attr("selected", "selected");
			   /* $("#officeLocation option[text=${discardSell.officeLocation}]").attr("selected","selected"); */
			  // $(".selectCheck").find("option[text=${discardSell.officeLocation}]").attr("selected", true);
			  /*  $(".selectCheck").find("option[text='${discardSell.officeLocation}']").attr("selected","selected");  */
			});
		
		var bianMa = "";
		var v=0;
		var s = "";
		var array = new Array();
		 var originalValueSum=$("#assetOriginalValueSum").val();//原值
		 var netValueSum=$("#assetNetValueSum").val();//净值
		 var discardSellMoneySum=0;//报废金额
		getDiscardSell();
	 //返回关闭增行弹窗
	$("#inquiry").click(function(){
		//资产名称
		var txtAssetsName = $("#txtAssetsName").val();
		//资产编码
		var txtAssetsCode = $("#txtAssetsCode").val();
		//现责任人
		var txtAssetPerson = $("#txtAssetPerson").val();
		//管理编码
		var txtPutUnderManageCode = $("#txtPutUnderManageCode").val();

		var costCenterCode=$("#costCenterCode").val();
		if(txtAssetsCode==""){
			alert("请填写资产编码");
			return false;
		}
		
		if(txtAssetsName!=''||txtAssetsCode!=''||txtAssetPerson!=''||txtPutUnderManageCode!=''||costCenterCode!=''){
			searchGeneralAsset(txtAssetsName,txtAssetsCode,txtAssetPerson,txtPutUnderManageCode,costCenterCode);	
		}else{
			//searchInfo();
		}
		
	}); 
	 
	function searchGeneralAsset(txtAssetsName,txtAssetsCode,txtAssetPerson,txtPutUnderManageCode,costCenterCode){
		$("#tbody_add_detail").html();
		
		var officeLocation=$("#officeLocation").val();
		var isExpenseAssets = $("#isExpenseAssets").val();
		/* var persentDepartment=$("#persentDepartment").val(); */
		//资产类型
		var assetType = $("#assetType").val();
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/assetCard/api/searchGeneralAsset",
            data:{txtAssetsName:txtAssetsName,txtAssetsCode:txtAssetsCode,txtAssetPerson:txtAssetPerson,txtPutUnderManageCode:txtPutUnderManageCode,officeLocation:officeLocation,costCenterCode:costCenterCode,useState:3,isExpenseAssets:isExpenseAssets,assetType:assetType},
            success: function(data) {
				var divHtml="";
				for (var i = 0; i < data.length; i++) {  
					divHtml += "<tr align='center' value='"+data[i].id+"' id='tr"+data[i].id+"' class='bodyTr' style='white-space:nowrap;'>";
					divHtml += "<td id='k"+data[i].id+"' value='"+data[i].assetCode+"' ><input type='checkbox' value='"+data[i].assetCode+","+data[i].id+","+data[i].assetOriginalValue+","+data[i].assetNetValue+"' name='radioFrame' id='kuang"+data[i].id+"'></td>";
					
					divHtml += "<td class='tdpadding'>"+data[i].assetCode+"</td>";
					if(null!=data[i].assetName&&""!=data[i].assetName){
						divHtml += "<td class='tdpadding'>"+data[i].assetName+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
					
					if(data[i].assetPerson!=null&&""!=data[i].assetPerson){
						divHtml += "<td class='tdpadding'>"+data[i].assetPerson+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
					if(null!=data[i].specificationModel&&""!=data[i].specificationModel){
						divHtml += "<td class='tdpadding'>"+data[i].specificationModel+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
				/* 	divHtml += "<td class='tdpadding'>"+data[i].assetOriginalValue+"</td>";
					divHtml += "<td class='tdpadding insertPercentage'>"+data[i].assetNetValue+"</td>"; */
					if(data[i].assetOriginalValue==null){
						divHtml += "<td class='tdpadding'>"+0+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+data[i].assetOriginalValue+"</td>";
					}
					if(data[i].assetNetValue==null){
						divHtml += "<td class='tdpadding insertPercentage' >"+0+"</td>";
					}else{
						divHtml += "<td class='tdpadding insertPercentage' >"+data[i].assetNetValue+"</td>";
					}
					if(data[i].useState==null){
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
					if(data[i].useState==0){
						divHtml += "<td class='tdpadding'>"+"在用"+"</td>";
					}
					if(data[i].useState==1){
						divHtml += "<td class='tdpadding'>"+"闲置中"+"</td>";
					}
					if(data[i].useState==2){
						divHtml += "<td class='tdpadding'>"+"大修理中"+"</td>";			
									}
					if(data[i].useState==3){
						divHtml += "<td class='tdpadding'>"+"改造中"+"</td>";
					}				
					if(data[i].useState==4){
						divHtml += "<td class='tdpadding'>"+"报废申请中"+"</td>";
					}
					if(data[i].useState==5){
						divHtml += "<td class='tdpadding'>"+"已报废"+"</td>";
					}
					if(data[i].useState==6){
						divHtml += "<td class='tdpadding'>"+"变卖申请中"+"</td>";
					}
					if(data[i].useState==7){
						divHtml += "<td class='tdpadding'>"+"已变卖"+"</td>";
					}
					divHtml += "<td class='tdpadding'>"+data[i].companyCode+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].costCenterCode+"</td>";
					if(null!=data[i].location&&""!=data[i].location){
						divHtml += "<td class='tdpadding'>"+data[i].location+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
				
					divHtml += "</tr>";
				}
                $("#tbody_add_detail").html(divHtml);
            }
        });
	}
	
	 //返回关闭申请弹窗
	$("#fanHui").click(function(){
		//关闭窗口
        dg.cancel();
	});
	
	//更换是否费用资产化，清空之前选项
	$("#isExpenseAssets").change(function(){
		$("#tbody").html("");
		array = null;
		s = "";
		bianMa="";
		$("#netValue").val("");
		$("#checkAll").attr("checked",false);
	});
	//更新类型，清空之前选项
	$("#assetType").change(function(){
		$("#tbody").html("");
		array = null;
		s = "";
		bianMa="";
		$("#netValue").val("");
		$("#checkAll").attr("checked",false);
		var assetType=$("#assetType").val();
   		var costCode=$("#costCenterCode").val();
   		if(assetType!="3"){
				$("#workshopType").val("");
				return false;
			}
			if(assetType=="3"&&costCode==""){
				$("#workshopType").val("");
				$("#assetType option[value='']").attr("selected","selected");
				alert("请选择部门");
				return false;
			}
   			//$("#trWorkshop").show();
   			
   		/* 	if(costCode!=""){ */
   				$.ajax({
   					type:"post",
   					url:"${dynamicURL}/assetAllocation/api/getAttchConfigWorkshop",
   					data:{costCenter:costCode,assetType:assetType},
   					success:function(data){
   						if (data.success) {
   							/* $("#trWorkshop").show(); */
   							$("#workshopType").val(data.result.workShopType);	
   							
   						}else{
   							alert(data.errorMessages.join());
   						}
   						
   					}
   				});
   			/* }else{
   				alert("请选择部门！");
   			} */
   	
	});
	
	//删行
	$("#opDelete").click(function(){
		$("input[name='radio']:checked").each(function(){
			var idAndCode = $(this).val();
			var id=idAndCode.split(",")[0];
			code=idAndCode.split(",")[1];
			var originalValue=idAndCode.split(",")[2];
			var netValue=idAndCode.split(",")[3];
			
			array = null;
			s = "";
			bianMa="";
			var tr = $(this).parent().parent();
			if(originalValue!='null'&&originalValue!='暂无数据'){
				originalValueSum=(originalValueSum-0)-(originalValue-0);
			}
			if(netValue!='null'&&netValue!='暂无数据'){
				netValueSum=(netValueSum-0)-(netValue-0);
			}
			
			$("#tr"+id).remove();
			$("#assetOriginalValueSum").val(originalValueSum.toFixed(2));
			$("#assetNetValueSum").val(netValueSum.toFixed(2));
		});
		discardSellMoneySum=0;
		$("input[name='percentMoney']").each(function(){
			var val=$(this).val();
		
			if(val!="null"&&val!="暂无数据"&&val!=""){
				discardSellMoneySum=(discardSellMoneySum-0)+(val-0);
			}
		});
		$("#discardMoneySum").val(discardSellMoneySum);

	/* 	for(var i=0;i<assetCode.length;i++){
		
			if(assetCode[i]!=code){
				if(bianMa==""){
					bianMa=assetCode[i];
				}else{
					bianMa=bianMa+","+assetCode[i];
				}
				
			}
			
		} */
		$("#discardMoneySum").val(discardSellMoneySum);
		$('#checkAll').attr("checked",false);
		getDiscardSell();
		sumAll();//计算总金额
	});
	var number = 0;
	var zhi = 0;
	//增行   确定
	function confirm(radioFrame){
		var arrayCheck = new Array(); //行明细的 资产编码
		array = bianMa.split(",");
		var codeList="";
		var  num = 0;	
		var flag=false;
		var errorMessage="";
		$("input:checkbox[name='radio']").each(function(){
			var assetCodes=$(this).val();
			
			var assetCode=assetCodes.split(",")[1];
			array.push(assetCode);
		});
		 $("input[name='radio']").each(function(){
			 var  code=$(this).val().split(",")[1];
			 if(codeList==""){
				 codeList=code;
			 }else{
				 codeList=codeList+","+code;
			 }
		 })
		 arrayCheck=codeList.split(",");	
		 $("input[name='radioFrame']:checked").each(function(){
			 var  idAndCode = $(this).val();
			 var  code=idAndCode.split(",")[0];
			 var  id=idAndCode.split(",")[1];
			 var  originalValue=idAndCode.split(",")[2];
			 var  netValue=idAndCode.split(",")[3];
			 
			 if(originalValue!='null'&&originalValue!='暂无数据'){
				 if(originalValueSum==0){
					 originalValueSum=originalValue-0;
				 }else{
					 originalValueSum=(originalValueSum-0)+(originalValue-0);
				 }
				
			 }
			 if(netValue!='null'&&netValue!='暂无数据'){
				 if(netValueSum==0){
					 netValueSum=netValue-0;
				 }else{
					 netValueSum=(netValueSum-0)+(netValue-0);
				 }
			 }
			 
			for(var i =0;i<arrayCheck.length;i++){
					if(arrayCheck[i]==code){
						errorMessage = errorMessage+code + ",";
						flag = true;
						break;
					}
				}
			if(flag==true){
				return false;
			}
		/* 	 for(var i =0;i<array.length;i++){
				 
					if(array[i]==code){
						
						s = s+code+",";
						num = 0;
						break;
					}
					else{
						num = 1;
					}
				} */
			 /* if(num==1&&s==""){ */
				 	number = number+1;
					var tr = $(this).parent().parent();
					if(netValue=='null'){
						netValue=0-0;
					}
				
					$("<td class='tdpadding'><input style='width:80px;' onblur='count("+id+","+netValue+")' id='percent"+id+"' name='percent' type='text'  value='100%' ></td><td class='tdpadding'><input style='width:80px;' id='percentMoney"+id+"' name='percentMoney'  value='"+netValue+"' disabled='disabled' type='text' re></td>").insertAfter(tr.children(".insertPercentage"));

					bianMa = bianMa+code+",";
					$("#kuang"+id).remove();
					$("#k"+id).append("<input type='checkbox' id='kuang"+id+"' value='"+id+","+code+","+originalValue+","+netValue+"' name='radio'/>");
					
					$("#tbody").append(tr);
					$("#assetOriginalValueSum").val(originalValueSum.toFixed(2));
					$("#assetNetValueSum").val(netValueSum.toFixed(2));
				 /* } */
		}); 
		 s=s.substring(0, s.length-1);
		
			/* if(s!=""){
				alert("编码"+s+"已存在");
				s = "";
				return;
			} */
		 if(flag){
			 alert("编码"+errorMessage+"已存在");
			 return false;
		 }else{
				var t = $("#netValue").val();
				var total = t*1+zhi*1;
				$("#netValue").val(total);
				zhi = 0;
				
				discardSellMoneySum=0;
				$("input[name='percentMoney']").each(function(){
					var val=$(this).val();
				
					if(val!="null"&&val!="暂无数据"&&val!=""){
						discardSellMoneySum=(discardSellMoneySum-0)+(val-0);
					}
				});
				$("#discardMoneySum").val(discardSellMoneySum);
				 getDiscardSell();
				$('#checkAllFrame').attr("checked",false);
				
				//关闭窗口
		        $("#addApplyWindow").window('close');
			}
			sumAll();//计算总金额
	}
	function count(id,netValue){
		var value=$("#percent"+id).val();
		var reg=/\d+/g;
		if(value==''){
			alert("请输入报废比例！");
		}
		if(!/^[0-9]+([.]{1}[0-9]{1,2})?%$/.test(value)){
			alert("请输入正确的格式");
		}
		var s=0;
		var percents=value.replace(/%/g, "");
		
		if(percents>100){
			alert("请输入正确的格式");
		
			return false;
		}
		var s=percents/100;
		
		var numValue=parseFloat(s);
		var discardMoney=0;
		if(netValue!='null'&&netValue!='暂无数据'){
			
			discardMoney=numValue*netValue;
		}else{
			discardMoney=0;
		}
		$("#percentMoney"+id).val(discardMoney.toFixed(2));
		discardSellMoneySum=0;
		$("input[name='percentMoney']").each(function(){
			var val=$(this).val();
		
			if(val!="null"&&val!="暂无数据"&&val!=""){
				discardSellMoneySum=(discardSellMoneySum-0)+(val-0);
			}else{
				discardSellMoneySum=(discardSellMoneySum-0)+(0-0);
			}
		});
	
		$("#discardMoneySum").val(discardSellMoneySum.toFixed(2));
	}
	//增行弹窗
	$("#showAddDialog").click(function(){
		$("#tbody_add_detail").html("");
		var top = "10px";
    	var left = "70px";
 		var costCenterCode=$("#costCenterCode").val();
   		var officeLocation=$("#officeLocation").val();
   		var isExpenseAssets=$("#isExpenseAssets").val();
   		var assetType=$("#assetType").val();
   		if(costCenterCode==""){
   			alert("请选择部门！");
   			return false;
   		}
   		if(officeLocation==""){
   			alert("请选择办公地点！");
   			return false;
   		}
   		if(isExpenseAssets==""){
   			alert("请选择是否一次性费用化资产！");
   			return false;
   		}
   		if(assetType==""){
   			alert("请选择资产类型！");
   			return false;
   		}
    	$('#addApplyWindow').window('open').window('resize',{width:'850px',height:'440px',top: top,left:left});
    	//searchInfo();
    });

	function searchInfo(){
		$("#tbody_add_detail").html();
		
		//是否费用资产化
		var isExpenseAssets = $("#isExpenseAssets").val();
		//资产类型
		var assetType = $("#assetType").val();
		//资产所属部门
		var subordinateDepartment=$("#subordinateDepartment").val();
		var officeLocation=$("#officeLocation").val();
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/assetCard/api/searchAssetCardInfo",
            data:{isExpenseAssets:isExpenseAssets,assetType:assetType,subordinateDepartment:subordinateDepartment,officeLocation:officeLocation},
            success: function(data) {
				var divHtml="";
				for (var i = 0; i < data.length; i++) {  
					
					divHtml += "<tr align='center' value='"+data[i].id+"' id='tr"+data[i].id+"' class='bodyTr' style='white-space:nowrap;'>";
					divHtml += "<td id='k"+data[i].id+"' value='"+data[i].assetCode+"' ><input type='checkbox' value='"+data[i].assetCode+","+data[i].id+","+data[i].assetOriginalValue+","+data[i].assetNetValue+"' name='radioFrame' id='kuang"+data[i].id+"'></td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetCode+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetName+"</td>";
					
					divHtml += "<td class='tdpadding'>"+data[i].assetPerson+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].specificationModel+"</td>";
					divHtml += "<td class='tdpadding '><input type='hidden' id='kkk"+data[i].assetCode+"' value='"+data[i].assetNetValue+"'> "+data[i].assetOriginalValue+"</td>";
					divHtml += "<td class='tdpadding insertPercentage'>"+data[i].assetNetValue+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].useState+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].companyName+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].persentDepartment+"</td>";
					if(data[i].location==null){
						divHtml += "<td class='tdpadding'>"+data[i].location+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
					
					divHtml += "</tr>";
				}
                $("#tbody_add_detail").html(divHtml);
            }
        });
    }

	function changeType(){
		var assetType=$("#assetType").val();
		
		if(assetType=="IT"){
			$("#assetHiddenType").val("IT");
			$("#fontDiv").show();
			$("#itDiv").show();
			$("#itAssetType option[value='']").attr("selected",true);
		}
		if(assetType==''){
			$("#assetHiddenType").val("");
			$("#fontDiv").hide();
			$("#itDiv").hide();
		}
		if(assetType=='2'){
			$("#assetHiddenType").val("2");
			$("#fontDiv").hide();
			$("#itDiv").hide();
		}
		if(assetType=='3'){
			$("#assetHiddenType").val("3");
			$("#fontDiv").hide();
			$("#itDiv").hide();
		}
		if(assetType=='4'){
			$("#assetHiddenType").val("3");
			$("#fontDiv").hide();
			$("#itDiv").hide();
		}
	} 
	function itChange(){
		var asserType=$("#itAssetType").val();
		
		if(asserType=='0'){
			$("#assetHiddenType").val("0");
			
		}
		if(asserType=='1'){
			$("#assetHiddenType").val("1");
			
		}
	} 

	function getDiscardSell(){
		var assetIds="";
		 $("input[name='radio']").each(function(){
				var ids=this.id;
				var id=ids.substr(5,ids.length);
				 if(assetIds==""||null==assetIds){
						assetIds=id;
					 }
					 else{
						 assetIds=id+","+assetIds;
					 }
		 });
		$.ajax({
			type:"post",
			url:"${dynamicURL}/discardSell/api/getDiscardSell",
			data:{"ids":assetIds},
			success:function(data){
				var divHtml="";
				var  list=data.result.listGneralAssets;
				for(var i=0;i<list.length;i++){
					var assetCode=list[i].assetCode;
					var assetName=list[i].assetName;
					var putUnderManageCode=list[i].putUnderManageCode;
					var asset=list[i].assetAllocation;
					for(var j=0;j<asset.length;j++){
						if(asset[j]!=null){
						divHtml += "<tr align='center' class='bodyTr' style='white-space:nowrap;'>";
						if(null!=assetCode){
							divHtml += "<td class='tdpadding'>"+assetCode+"</td>";
						}else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						if(null!=assetName){
							divHtml += "<td class='tdpadding'>"+assetName+"</td>";
						}
						else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						if(putUnderManageCode!=null){
							divHtml += "<td class='tdpadding'>"+putUnderManageCode+"</td>";
						}
						else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						if(asset[j].applyPeople!=null){
							divHtml += "<td class='tdpadding'>"+asset[j].applyPeople+"</td>";
						}
						else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						if(asset[j].postAllocationPeople!=null){
							divHtml += "<td class='tdpadding'>"+asset[j].postAllocationPeople+"</td>";
						}
						else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						if(null!=asset[j].postAllocationDepartment){
							divHtml += "<td class='tdpadding'>"+asset[j].postAllocationDepartment+"</td>";
						}else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						if(null!=asset[j].postAllocationCompany){
							divHtml += "<td class='tdpadding'>"+asset[j].postAllocationCompany+"</td>";	
						}else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						if(null!=asset[j].postAllocationCostCenter){
							divHtml += "<td class='tdpadding'>"+asset[j].postAllocationCostCenter+"</td>";
						}else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						if(null!=asset[j].allocationMethod){
							divHtml += "<td class='tdpadding'>"+asset[j].allocationMethod+"</td>";
						}else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						if(null!=asset[j].officeLocation){
							divHtml += "<td class='tdpadding'>"+asset[j].officeLocation+"</td>";
						}else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						if(null!=asset[j].platform){
							divHtml += "<td class='tdpadding'>"+asset[j].platform+"</td>";
						}else{
							divHtml += "<td class='tdpadding'>"+"暂无数据"+"</td>";
						}
						divHtml += "</tr>";
						}
					}
				}
				 $("#tbodyDB").html(divHtml);
			}
		});
	}
   function showAgreeDialog(changeAmount) {
	    	var document =$("#document").val();
	    	
	        var url = "${dynamicURL}/workflow/showUpdateCommitDialog?parentWindowId=update&document=" + document + "&typeId="+77031+"&changeAmount="+changeAmount;

	        var dlg = new dg.curWin.$.dialog({ id: 'agreeDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
	        dlg.ShowDialog();
	        return dlg;
    }
   function commitAgree(isMail, isPhoneMessage, idea,activityId) {
       $('#isMail').val(isMail);
       $('#isPhoneMessage').val(isPhoneMessage);
       $('#idea').val(idea);
	   $("#activityId").val(activityId);
	   $("#submitBtn").val('提交...');
	   $("#submitBtn").attr("disabled",true);
       $('#agreeBtn').val('提交...');
       var assetIds="";
		var moneyList="";
		discardSellMoneySum=0;
		$("input[name='percentMoney']").each(function(){
			var val=$(this).val();
			if(moneyList==""){
				moneyList=val;
			}else{
				moneyList=moneyList+","+val;
			}
			if(val!="null"){
				discardSellMoneySum=(discardSellMoneySum-0)+(val-0);
			}
			
		});
		var percentList=''
		$("input[name='percent']").each(function(){
			var val=$(this).val();
			if(percentList==""){
				percentList=val;
			}else{
				percentList=percentList+","+val;
			}
		});
		$("#discardMoneySum").val(discardSellMoneySum.toFixed(2));
		var originalValueSum=$("#assetOriginalValueSum").val();
		var assetNetValueSum=$("#assetNetValueSum").val();
		
		
		var assetIds="";
		 $("input[name='radio']").each(function(){
			 var cardId=$(this).val();
			 var id=cardId.split(",")[0];
			
			 if(assetIds==""||null==assetIds){
				assetIds=id;
			 }
			 else{
				 assetIds=assetIds+","+id;
			 }
		 });
	
		if(assetIds!=null&&assetIds!=""){
		 	$.ajax({
				type:"GET",
				url:"${dynamicURL}/discardSell/api/updateDiscardSellIds?"+$("#discardForm").serialize(),
				data:{ids:assetIds,originalValueSum:originalValueSum,assetNetValueSum:assetNetValueSum,discardMoneySum:discardSellMoneySum,moneyList:moneyList,percentList:percentList},
				success:function (data){
					if (data.success) {
						alert("修改成功！");
						  $("#submitBtn").val('提交');
						   $("#submitBtn").attr("disabled",false);
						 dg.cancel(); 
		                 dg.curWin.location.reload();
					}else{

						var str = data.errorMessages;
	                    alert(str);
	                    var reg = new RegExp("^.*提交成功.*$");
	                    if (reg.test(str)) {
	                        dg.curWin.location = dg.curWin.location;
	                    }
						$("#agreeBtn").val("提交");
						$("#agreeBtn").attr("disabled",false);
						 $("#submitBtn").val('提交');
						   $("#submitBtn").attr("disabled",false);
						}
				}
			}); 
		}
		else{
			alert("请选择一条资产");
		}
  
   }
	function submit(stat){
		$("#isDraft").val(stat);
		if(stat=="1"){
			//跨步提交判断iaMoney是否发生了变化
			var changeAmount="Y";
			if(($("#discardMoneySum").val()-0)-($("#iaMoney").val()-0)==0){
				changeAmount="N"
			}
			showAgreeDialog(changeAmount);
		}else{
			var assetIds="";
			var moneyList="";
			discardSellMoneySum=0;
			$("input[name='percentMoney']").each(function(){
				var val=$(this).val();
				if(moneyList==""){
					moneyList=val;
				}else{
					moneyList=moneyList+","+val;
				}
				if(val!="null"){
					discardSellMoneySum=(discardSellMoneySum-0)+(val-0);
				}
			});
			var percentList=''
			$("input[name='percent']").each(function(){
				var val=$(this).val();
				if(percentList==""){
					percentList=val;
				}else{
					percentList=percentList+","+val;
				}
			});
			$("#discardMoneySum").val(discardSellMoneySum.toFixed(2));
			var originalValueSum=$("#assetOriginalValueSum").val();
			var assetNetValueSum=$("#assetNetValueSum").val();
			
			
			var assetIds="";
			 $("input[name='radio']").each(function(){
				 var cardId=$(this).val();
				 var id=cardId.split(",")[0];
				
				 if(assetIds==""||null==assetIds){
					assetIds=id;
				 }
				 else{
					 assetIds=assetIds+","+id;
				 }
			 });
			if(assetIds!=null&&assetIds!=""){
			
			 	$.ajax({
					type:"GET",
					url:"${dynamicURL}/discardSell/api/updateDiscardSellIds?"+$("#discardForm").serialize(),
					data:{ids:assetIds,originalValueSum:originalValueSum,assetNetValueSum:assetNetValueSum,discardMoneySum:discardSellMoneySum,moneyList:moneyList,percentList:percentList},
					success:function (data){
						if (data.success) {
							alert("修改成功！");
							 dg.cancel(); 
			                 dg.curWin.location.reload();
						}else{
							alert(data.errorMessages.join());
							$("#submitBtn").val("提交");
							$("#submitBtn").attr("disabled",false);
							}
					}
				}); 
			}
			else{
				alert("请选择一条资产");
			}
		}
	
	}
	//预算所属部门 开始
	$('#btntxtYsssbmShow').click(function(){
		/* var top = "90px";//$(this).offset().top - 350;
		var left = "120px";//$(this).offset().left-180;
		
		$('#ysssbmWindow').window('open').window('resize',{width:'450px',height:'300px',top: top,left:left});
	
		  $("#tree1").tree({
		    	url: '${dynamicURL}/department/searchAllDepartmentTreeInit',
		    	lines: true,
		    	onClick: function(node) {
		    		setTree(node.id, node.text);
		     	}
		    }); */
		showDepCostCenter();
		
	});
	function setTree(ckscode,cksname){
		 var ckscode = $("#tree1").tree('getSelected').id;
		 var cksname = $("#tree1").tree('getSelected').text;
		 $("#subordinateDepartment").val(cksname);
		 $("#subordinateDepartmentCode").val(ckscode);
		/* $.ajax({
			type : "POST",
			async : false,
			url : "${dynamicURL}/userInfo/api/cksDept",
			data : "ckscode=" + ckscode,
			success: function(treeObj){
				if(!treeObj){
					alert("没有配置该成本中心");
					
					return false;
				}
			 	
				//部门树	带公司
				$("#adscriptionCompanyCode").val(treeObj.ccompanycode);//资产所属 公司编码
				$("#adscriptionCompany").val(treeObj.ccompanyname);//资产所属公司名称
				
				// 部门树    带成本中心
				$("#costCenterName").val(treeObj.ccostcentername);
				$("#costCenterCode").val(treeObj.ccostcentercode);
				getComPlatform(treeObj.ccostcentercode);
				getWorkshopType();
				
			}
		}); */
		getCompany(ckscode);
		 $('#ysssbmWindow').window('close');
	
	}
	function getCompany(cdepCode){
	 	   $.ajax({
	 		   type:"post",
	 		   url:"${dynamicURL}/assetAllocation/api/getCompany",
	 		   data:{ckscode:cdepCode},
	 		   async:false,
	 		   success:function(data){
	 			   var  html="";
	 				if(data.length>1){
	 					html += "<option value=''>请选择</option>";
	 				}
	 				for (var i = 0; i < data.length; i++) {
	 					html += "<option value="+data[i].ccompanycode+">"+"("+data[i].ccompanycode+")"+data[i].ccompanyname+"</option>";
	 				}
	 				$("#companyName").html(html);
	 				 var comcode=  $("#companyName").val();
	 				if(comcode!=""){
	 					getCostCenter();
	 				}
	 		   }
	 	   });
	    }
	    function getCostCenter(){
	 	 var comcode=  $("#companyName").val();
	 	
			 var comname= $("#companyName option:selected").text();
			  $("#adscriptionCompanyCode").val(comcode);
			  $("#adscriptionCompany").val("("+comcode+")"+comname);
	 	    var depcode=$("#subordinateDepartmentCode").val();
	 	   
	 	   $.ajax({
	 		   type:"post",
	 		   url:"${dynamicURL}/assetAllocation/api/getCostCenter",
	 		   data:{companyCode:comcode,depcode:depcode},
	 		   async:false,
	 		   success:function(data){
	 			   $("#costCenterName").val("("+data.ccostcentercode+")"+data.ccostcentername);
	 			   $("#costCenterCode").val(data.ccostcentercode);
	 			  getComPlatform(data.ccostcentercode);
	 		   }
	 	   });
	 	 
	    }
	//清除根据成本中心带出的信息
	function cleanDep() {
		$("#txtYsssbm").val("");
		$("#txtBudgetDepartment").val("");
		$("#txtCompany").val("");
		$("#txtPlatform").html("");
		$("#subordinateDepartment").val("");
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
    //根据公司查平台
	   function getComPlatform(postAllocationCostCenter){
	    	
		   $.ajax({
	       		type:"post",
					url:"${dynamicURL}/assetAllocation/api/getAttchConfig",
					//async : false,
					data: {costCenter:postAllocationCostCenter},
					async:false,
				 	success: function(data) {
				 		$("#platform").val(data.platform);
				 	}
	       	}); 
		
	   } 
	   $.ajax({
           url:'${dynamicURL}/file/getFilesByDocument',
           data:{'document':$("#document").val()},
           dataType:'json',
           type:'POST',
           success:function(data){
               if(data.length > 0 ){
                   $('#uploadTable .noItems').remove();
                   $.each(data,function(i,item){
                       $('#uploadTable > tbody').append('<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
                           + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '" name=documentId />'
                           + '</td><td scope="col">' + item.fileName
                           + '</td><td scope="col">' + item.createUserName
                           + '</td><td style="width:9%">' + item.createDate
                           + '</td><td align="center" style="width:10%"><a href="'+'${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                           + '</td></tr>');
                   });
               }

           }
       });
		 $("#checkAllFrame").click(function(){
			  $("[name='radioFrame']").each(function(){
				   if($(this).attr("checked"))
				   {
				    $(this).removeAttr("checked");
				   }else{
				    $(this).attr("checked",'true');
				   }
				  });	 
		});  
		  function sumAll(){
			  var originalValueSum=0;
			  var netValueSum=0;
			  $("input[name='radio']").each(function(){
				  var originalValue=$(this).val().split(",")[2];
				  var netValue=$(this).val().split(",")[3];
				  if(originalValue!=""&&originalValue!="null"&&originalValue!="暂无数据"){
					  originalValueSum+=originalValue-0;
				  }
				  if(netValue!=""&&netValue!="null"&&netValue!="暂无数据"){
					  netValueSum+=netValue-0;
				  } 
			  });
			  $("#assetOriginalValueSum").val(originalValueSum.toFixed(2));
			  $("#assetNetValueSum").val(netValueSum.toFixed(2));
		        discardSellMoneySum=0;
		   
		    	$("input[name='percentMoney']").each(function(){
					var val=$(this).val();
					
					if(val!="null"&&val!="暂无数据"&&val!=""){
						discardSellMoneySum+=val-0;
					}
				});
		    
				$("#discardMoneySum").val(discardSellMoneySum.toFixed(2));
		  }
			 $("#checkAll").click(function(){
				  $("[name='radio']").each(function(){
					   if($(this).attr("checked"))
					   {
					    $(this).removeAttr("checked");
					   }else{
					    $(this).attr("checked",'true');
					   }
					  });	 
			});  
	       function showDepCostCenter(){
	        	
	    		  var url = "${dynamicURL}/assetAllocation/searchPagerList?parentWindowId=update";
	    		  var parentWindow=dg.curWin; 
	    		  new parentWindow.$.dialog({ id: 'showDepDiscard', title: "所有部门", cover: true, page: url, width: 900, height: 450, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
	    		   
	    	   }
	        function fillCostCenter(depCode, depName,costCenterCode,costCenterName) {
	  		  
	 		 /*   $("#costCenterName").val("("+costCenterCode+")"+costCenterName);
	 		   $("#costCenterCode").val(costCenterCode); */
	 		   $("#subordinateDepartmentCode").val(depCode);
	 		   $("#subordinateDepartment").val(depName);
	 		   getCompany(depCode);
	        };
	</script>
</body>
</html>