<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调拨申请</title>
<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css"
	rel="stylesheet" />

<link rel="stylesheet" type="text/css"
	href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css"> 

	<link rel="Stylesheet" type="text/css" href="${staticURL}/assetallocation/css/allocationapplicationapply.css">
</head>

<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
		        报废变卖单</div>
	</div>
			<table>
					
					<tr><td class="buttonbar" colspan="6">
					<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
						<input type="submit" name="" value="提交" onclick="submit(1)" id="submitBtn"
							class="btn" > <input type="button" name="" value="保存草稿"
							id="conservation" onclick="submitDraft(0)" class="btn"> <input
							type="button" name="" value="返回" onclick="dg.cancel()" id="fanHui"
							class="btn">
					</div>
				</td></tr>
					
			</table>
	<div class="titlefontsize"
		style="width: 99%; height: 30px; margin-top: 10px;">
		<div class="imgtitle"></div>
		基本信息  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href="${dynamicURL}/download/downLoadFile?downloadFileName=固定资产管理制度.pdf">固定资产管理制度.pdf</a>
	</div>
	<form id="discardForm">
		<table class="table_head" style="table-layout: fixed;width:100%;">
				<tr>
				
				<td class="td_right"><span class="span_color">*</span>流程单据号:</td>
				<td class="td_left"><input name="document" type="text"
					value="${discardSell.document}" id="document" readonly="readonly" class="td_input" /> 
					<input type="hidden" name='userCode' value="${discardSell.userCode }">
					<input type="hidden" name="companyCode" value="${discardSell.companyCode }">
					<input type="hidden" name="departmentCode" value="${discardSell.departmentCode }">
					<input type="hidden" id="isPhoneMessage" name="isPhoneMessage" >
					<input type="hidden" id="isMail" name="isMail">
					<input type="hidden" id="idea" name="idea" >
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属部门:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="departmentName" value="${discardSell.departmentName }" >
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属公司:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="companyName" value="(${discardSell.companyCode})${discardSell.companyName }" >
				</td>
			</tr>
			<tr>
				
				<td class="td_right"><span class="span_color">*</span>申请人:</td>
				<td class="td_left"><input type="text" name="userName" value="${discardSell.createBy }" readonly="readonly" class="td_input"><input
					type="hidden" name="commitType" id="isDraft" />
					<input
					type="hidden" name="createBy" id="createBy" value="${discardSell.createBy }"/>
					</td>
				<td class="td_right"><span class="span_color">*</span>申请日期:</td>
				<td class="td_left"><input class="td_input" name="createDate" type="text"
					readonly="readonly"  id="applicationDate"></td>
						<td class="td_right"><span class="span_color">*</span>办公地点:</td>
				<td class="td_left">
					<select name="officeLocation" style="width: 163px;height: 24px;" id="officeLocation" >
		 						<option value="">请选择</option>
		 				<c:forEach items="${addresslist}" var="userInfo" varStatus="status">
		 					<option value="${userInfo.caddressCode}">${userInfo.caddressName}</option>
		 				</c:forEach>
	 				</select>
				</td>

			</tr>
			<tr>
				<td class="td_right"><span class="span_color">*</span>是否一次性费用化资产:</td>
				<td class="td_left"><select style="width: 163px;"
					class="td_select" name="isExpenseAssets"
					id="isExpenseAssets">
					<option value="">请选择</option>
					<option  value="0">否</option>
					<option  value="1">是</option>
				</select></td>
				<td class="td_right"><span class="span_color">*</span>资产所属部门:</td>
				<td class="td_left"><input style="width: 110px;" class="td_input" name="subordinateDepartment" type="text"
					readonly="readonly"  id="subordinateDepartment"><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanDep();"><input id="btntxtYsssbmShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
					<input id="subordinateDepartmentCode" type="hidden" name="subordinateDepartmentCode">
				</td>
				<td class="td_right"><span class="span_color">*</span>资产归属公司:</td>
				<td class="td_left">
					<select id="companyName"  style="width: 163px;" class="td_select" onchange="getCostCenter()">
						
					</select>
					<input readonly='readonly'  class="td_input" name='adscriptionCompany' type="hidden"  id='adscriptionCompany' >
					<input type="hidden" name="adscriptionCompanyCode" id="adscriptionCompanyCode">
				</td>
			
			</tr>
			<tr>
				<td class="td_right"><span class="span_color">*</span>资产所属成本中心</td>
				<td class="td_left">
						<input class="td_input" id="costCenterName" name="costCenterName" type="text">
						<input   class="td_input" id="costCenterCode" name="costCenterCode" type="hidden">	
				</td>
				<td class="td_right"><span class="span_color">*</span>平台:</td>
				<td class="td_left"><input class="td_input" name="platform" id="platform" readonly="readonly"></td>
				<td class="td_right">资产类型:</td>
				<td class="td_left"><select style="width: 163px;"
					class="td_select"
					id="assetType" name="assetType"  >
						<option value="">请选择</option>
						<option value="0">IT资产</option>
						<option  value="1">行政资产</option>
						<option  value="2">计量器具</option>
						<option value="3">机器设备</option>
				</select></td>
			</tr>
			<tr id="trWorkshop">
				<td class="td_right">车间类型:</td>
				<td class="td_left">
					<input class="td_input" name="workshopType" id="workshopType" readonly="readonly">	
				</td>		
				<td class="td_right"><span class="span_color">*</span>申请原因:</td>
				<td class="td_left">
					<input class="td_input" name="application" id="application">	
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
					<th>资产类型</th>
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

			</tbody>

		</table>
	</div>
	<div>
		<table style="table-layout: fixed;width:100%;">
			<tr>
				<td class="td_right">原值合计</td>
				<td class="td_left"><input id="assetOriginalValueSum" readonly="readonly"/> </td>
				<td class="td_right">净值合计</td>
				<td class="td_left"><input id="assetNetValueSum" readonly="readonly"/></td>
				<td class="td_right">报废金额合计</td>
				<td class="td_left"><input id="discardMoneySum" readonly="readonly"/></td>
			</tr>
		</table>
		
		<table cellpadding="0" cellspacing="0" border="0"  style="table-layout: fixed;width:100%;" >
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
	<!-- <div class="titlefontsize"
		style="width: 99%; height: 30px; margin-top: 10px;">
		<div class="imgtitle"></div>
		调拨信息
	</div>
	<div style="overflow-x: scroll; width: 100%;">
		<table border="0" cellpadding="3px" cellspacing="0" width="150%">

			<thead>
				<tr>
					<th>资产编码</th>
					<th>资产名称</th>
					
					<th>申请人</th>
					<th>调拨后资产责任人</th>
					<th>调拨后部门</th>
					<th>调拨后归属公司</th>
					<th>调拨后成本中心</th>
					<th>调配方式</th>
					<th>办公地点</th>
					<th>平台</th>
				
				</tr>

			</thead>
			<tbody id="tbodyBF">

			</tbody>
		</table>
		
	</div> -->

<!-- 	<div class="titlefontsize"
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
																		color="red">*</font>资产类型</th>
																	
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>现责任人</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>规格/型号</th>
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
	<script src="${staticURL}/js/ajaxfileupload.js" type="text/javascript"></script>
	<script
		src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js"
		type="text/javascript"></script>
	<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
	<script type="text/javascript"
		src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	//获得当前时间
	$(function(){
		var myDate = new Date();
		var month=myDate.getMonth()+1;
		var  time=myDate.getFullYear()+"-"+month+"-"+myDate.getDate();
		$("#applicationDate").val(time);
		/* $("#isExpenseAssets option[value=0]").attr("selected","selected"); */
		});
		var dg = frameElement.lhgDG;
		
		var bianMa = "";
		var v=0;
		var s = "";
		var array = new Array();
		 var originalValueSum=0;//原值
		 var netValueSum=0;//净值
		 var discardSellMoneySum=0;//报废金额
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
		if(txtAssetsCode==""&&txtAssetPerson==""){
			alert("请填写资产编码或现责任人!");
			return false;
		}
		
		if(txtAssetsName!=''||txtAssetsCode!=''||txtAssetPerson!=''||txtPutUnderManageCode!=''){
			searchGeneralAsset(txtAssetsName,txtAssetsCode,txtAssetPerson,txtPutUnderManageCode,costCenterCode);	
		}else{
			//searchInfo('');
		}
		
	}); 
	 
	function searchGeneralAsset(txtAssetsName,txtAssetsCode,txtAssetPerson,txtPutUnderManageCode,costCenterCode){
		$("#tbody_add_detail").html();
		var isExpenseAssets = $("#isExpenseAssets").val();
		//资产类型
		var assetType = $("#assetType").val();
		var persentDepartment=$("#persentDepartment").val();
		var officeLocation=$("#officeLocation").val();
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/assetCard/api/searchGeneralAsset",
            data:{txtAssetsName:txtAssetsName,txtAssetsCode:txtAssetsCode,txtAssetPerson:txtAssetPerson,txtPutUnderManageCode:txtPutUnderManageCode,useState:3,isExpenseAssets:isExpenseAssets,assetType:assetType,costCenterCode:costCenterCode,officeLocation:officeLocation},
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
					if(data[i].assetType==0){
						divHtml += "<td class='tdpadding'>"+"IT资产"+"</td>";
					}
					if(data[i].assetType==1){
						divHtml += "<td class='tdpadding'>"+"行政资产"+"</td>";
					}
					if(data[i].assetType==2){
						divHtml += "<td class='tdpadding'>"+"计量器具"+"</td>";
					}
					if(data[i].assetType==3){
						divHtml += "<td class='tdpadding'>"+"机器设备"+"</td>";
					}
					if(null!=data[i].assetPerson&&""!=data[i].assetPerson){
						divHtml += "<td class='tdpadding'>"+data[i].assetPerson+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
					if(data[i].specificationModel!=null){
						divHtml += "<td class='tdpadding'>"+data[i].specificationModel+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
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
			
					/* divHtml += "<td class='tdpadding insertPercentage' >"+data[i].assetNetValue+"</td>"; */
			/* 		divHtml += "<td class='tdpadding'  style='display:none;'><input style='width:80px;' type='text'  value='100%' ></td>";
					divHtml += "<td class='tdpadding'  style='display:none;'><input style='width:80px;  type='text'   ></td>"; */
					if(data[i].useState==null){
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
					else if(data[i].useState==0){
						divHtml += "<td class='tdpadding'>"+"在用"+"</td>";
					}
					else if(data[i].useState==1){
						divHtml += "<td class='tdpadding'>"+"闲置中"+"</td>";
					}
					else if(data[i].useState==2){
						divHtml += "<td class='tdpadding'>"+"大修理中"+"</td>";			
									}
					else if(data[i].useState==3){
						divHtml += "<td class='tdpadding'>"+"改造中"+"</td>";
					}				
					else if(data[i].useState==4){
						divHtml += "<td class='tdpadding'>"+"报废申请中"+"</td>";
					}
					else if(data[i].useState==5){
						divHtml += "<td class='tdpadding'>"+"已报废"+"</td>";
					}
					else if(data[i].useState==6){
						divHtml += "<td class='tdpadding'>"+"变卖申请中"+"</td>";
					}
					else if(data[i].useState==7){
						divHtml += "<td class='tdpadding'>"+"已变卖"+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
					divHtml += "<td class='tdpadding'>"+data[i].companyCode+"</td>";
					
					divHtml += "<td class='tdpadding'>"+data[i].costCenterCode+"</td>";
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
   		/* if(assetType=="3"){ */
   			//$("#trWorkshop").show();
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
   			/* } */
   		/* }else{
   			$("#trWorkshop").hide();
   			$("#workshopType").val("");	
   		} */
   		
	}); 
	
	//删行
	$("#opDelete").click(function(){
		var assetOriginalValue=0;
		var assetNetValue=0;
		var code="";
		var assetCode="";
		$("input[name='radio']:checked").each(function(){
			var idAndCode = $(this).val();
			var id=idAndCode.split(",")[0];
			code=idAndCode.split(",")[1];
			var originalValue=idAndCode.split(",")[2];
			var netValue=idAndCode.split(",")[3];
		
			assetCode=bianMa.split(",");
			var tr = $(this).parent().parent();
		
			$("#tr"+id).remove();
			if(originalValue!='null'&&originalValue!='暂无数据'){
				originalValueSum=(originalValueSum-0)-(originalValue-0);
			}
			if(netValue!='null'&&netValue!='暂无数据'){
				netValueSum=(netValueSum-0)-(netValue-0);
			}
			
		});
		var leng=$("input[name='radio']").length;
		if(leng=='0'){
			cleanValue();
		}
		bianMa="";
		for(var i=0;i<assetCode.length;i++){
		
			if(assetCode[i]!=code){
				if(bianMa==""){
					bianMa=assetCode[i];
				}else{
					bianMa=bianMa+","+assetCode[i];
				}
				
			}
			
		}
		
		$('#checkAll').attr("checked",false);
		 sumAll(); //计算总金额
	});

	var number = 0;
	var zhi = 0;

	//增行   确定
	function confirm(radioFrame){
		var arrayCheck = new Array(); //行明细的 资产编码+公司
		var flag=false;
		var errorMessage="";
		array = bianMa.split(",");
		var codeList="";
		var  num = 0;
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
			 var  id=idAndCode.split(",")[1];
			 var  code=idAndCode.split(",")[0];
			
			 var  originalValue=idAndCode.split(",")[2];
			 var  netValue=idAndCode.split(",")[3];
			 if(originalValue!='null'&&originalValue!='暂无数据'){
				 if(originalValueSum==0){
					 originalValueSum=originalValue-0;
				 }else{
					 originalValueSum=(originalValueSum-0)+(originalValue-0);
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
			 $("td[name='zhi']").each(function(){
				 alert($(this).text());
			 });
			 if(netValue!='null'&&netValue!='暂无数据'){
				 if(netValueSum==0){
					 netValueSum=netValue-0;
				 }else{
					 netValueSum=(netValueSum-0)+(netValue-0);
				 }
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
				}
			 if(num==1&&s==""){ */
				 	number = number+1;
					var tr = $(this).parent().parent();
					if(netValue=='null'){
						netValue=0-0;
					}
					$("<td class='tdpadding'><input style='width:80px;' onblur='count("+id+","+netValue+")' id='percent"+id+"' name='percent' type='text'  value='100%' ></td><td class='tdpadding'><input style='width:80px;' id='percentMoney"+id+"' name='percentMoney'  value='"+netValue+"' disabled='disabled' type='text' re></td>").insertAfter(tr.children(".insertPercentage"));

					bianMa = bianMa+code+",";
					$("#kuang"+id).remove();
					$("#k"+id).append("<input type='checkbox' value='"+id+","+code+","+originalValue+","+netValue+"' name='radio'/>");
					
					$("#tbody").append(tr);
					$("#assetOriginalValueSum").val(originalValueSum.toFixed(2));
					$("#assetNetValueSum").val(netValueSum.toFixed(2));
					
					
				/*  } */
		}); 
		
		 s=s.substring(0, s.length-1);
		
		/* 	if(s!=""){
				alert("编码"+s+"已存在");
				s = "";
				return;
			} */
			 if(flag){
				 alert("编码"+errorMessage+"已存在");
				 return false;
			 }
			else{
				 //带出调拨的信息
				$('#checkAllFrame').attr("checked",false);
				
				//关闭窗口
		        $("#addApplyWindow").window('close');
		
		
			}
			 sumAll(); //计算总金额
	}
	function count(id,netValue){
		var value=$("#percent"+id).val();
		var reg=/\d+/g;
		if(value==''){
			alert("请输入报废比例！");
		}
		if(!/^[0-9]+([.]{1}[0-9]{1,2})?%$/.test(value)){
			alert("请输入正确的格式");
		
			return false;
		}
		var percents=value.replace(/%/g, "");
		
		if(percents>100){
			alert("请输入正确的格式");
		
			return false;
		}
		var s=percents/100;
		
		var numValue=parseFloat(s);
		var discardMoney=0;
		if(numValue!='null'&&numValue!='暂无数据'&&numValue!=""){
			
			discardMoney=numValue*netValue;
		}else{
			discardMoney=0;
		}
		$("#percentMoney"+id).val(discardMoney.toFixed(2));
		
		var discardSellMoneySum=0;
		
		$("input[name='percentMoney']").each(function(){
			var val=$(this).val();
		
			if(val!="null"&&val!="暂无数据"){
				discardSellMoneySum=(discardSellMoneySum-0)+(val-0);
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
        // searchInfo('');

    });

	function searchInfo(persentDepartment){
		$("#tbody_add_detail").html();
	
		//是否费用资产化
		var isExpenseAssets = $("#isExpenseAssets").val();
		//资产类型
		var assetType = $("#assetType").val();
		var costCenterCode=$("#costCenterCode").val();
		var officeLocation=$("#officeLocation").val();
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/assetCard/api/searchAssetCardInfo",
            data:{isExpenseAssets:isExpenseAssets,assetType:assetType,costCenterCode:costCenterCode,useState:3,officeLocation:officeLocation},
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
					if(data[i].assetType==0){
						divHtml += "<td class='tdpadding'>"+"IT资产"+"</td>";
					}
					if(data[i].assetType==1){
						divHtml += "<td class='tdpadding'>"+"行政资产"+"</td>";
					}
					if(data[i].assetType==2){
						divHtml += "<td class='tdpadding'>"+"计量器具"+"</td>";
					}
					if(data[i].assetType==3){
						divHtml += "<td class='tdpadding'>"+"机器设备"+"</td>";
					}
					if(null!=data[i].assetPerson&&""!=data[i].assetPerson){
						divHtml += "<td class='tdpadding'>"+data[i].assetPerson+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
					if(data[i].specificationModel!=null){
						divHtml += "<td class='tdpadding'>"+data[i].specificationModel+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
					divHtml += "<td class='tdpadding'><input type='hidden' id='kkk"+data[i].assetCode+"' value='"+data[i].assetNetValue+"'> "+data[i].assetOriginalValue+"</td>";
					
					if(data[i].assetNetValue==null){
						divHtml += "<td class='tdpadding insertPercentage' >"+"0"+"</td>";
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
	function submitDraft(stat){
		$("#isDraft").val(stat);
		var moneyList="";

		var percentList=''
		$("input[name='percent']").each(function(){
			var val=$(this).val();
			if(percentList==""){
				percentList=val;
			}else{
				percentList=percentList+","+val;
			}
		});
	
		
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
			var moneyList="";
			discardSellMoneySum=0;
			$("input[name='percentMoney']").each(function(){
				var val=$(this).val();
				if(moneyList==""){
					moneyList=val;
				}else{
					moneyList=moneyList+","+val;
				}
				if(val!="null"&&val!="暂无数据"){
					discardSellMoneySum=(discardSellMoneySum-0)+(val-0);
				}
			});
		if(assetIds!=null&&assetIds!=""){
			if(stat==1){
				 $("#submitBtn").val("提交ing");
				$("#submitBtn").attr("disabled",true); 
			}
		 	 $.ajax({
				type:"GET",
				url:"${dynamicURL}/discardSell/api/insertDiscardSell?"+$("#discardForm").serialize(),
				data:{assetIds:assetIds,originalValueSum:originalValueSum,assetNetValueSum:assetNetValueSum,discardMoneySum:discardSellMoneySum,moneyList:moneyList,percentList:percentList},
				success:function (data){
					if (data.success) {
						alert("添加成功！");
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
  function showAgreeDialog() {
	    	var document =$("#document").val();
	    	
	        var url = "${dynamicURL}/workflow/showUpdateCommitDialog?parentWindowId=discardSellApply";

	        var dlg = new dg.curWin.$.dialog({ id: 'agreeDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
	        dlg.ShowDialog();
	        return dlg;
}
function commitAgree(isMail, isPhoneMessage, idea,activityId) {
    $('#isMail').val(isMail);
    $('#isPhoneMessage').val(isPhoneMessage);
    $('#idea').val(idea);
    $("#activityId").val(activityId);
    $('#agreeBtn').val('提交...');
    $("#isDraft").val('1');
	var moneyList="";

	var percentList=''
	$("input[name='percent']").each(function(){
		var val=$(this).val();
		if(percentList==""){
			percentList=val;
		}else{
			percentList=percentList+","+val;
		}
	});

	
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
		var moneyList="";
		discardSellMoneySum=0;
		$("input[name='percentMoney']").each(function(){
			var val=$(this).val();
			if(moneyList==""){
				moneyList=val;
			}else{
				moneyList=moneyList+","+val;
			}
			if(val!="null"&&val!="暂无数据"){
				discardSellMoneySum=(discardSellMoneySum-0)+(val-0);
			}
		});
	if(assetIds!=null&&assetIds!=""){
	
			 $("#submitBtn").val("提交ing");
			$("#submitBtn").attr("disabled",true); 
		
	 	 $.ajax({
			type:"GET",
			url:"${dynamicURL}/discardSell/api/insertDiscardSell?"+$("#discardForm").serialize(),
			data:{assetIds:assetIds,originalValueSum:originalValueSum,assetNetValueSum:assetNetValueSum,discardMoneySum:discardSellMoneySum,moneyList:moneyList,percentList:percentList},
			success:function (data){
				if (data.success) {
					alert("添加成功！");
					 
	                 dg.curWin.location.reload();
				}else{
					var str = data.errorMessages;
                    alert(str);
                    var reg = new RegExp("^.*提交成功.*$");
                    if (reg.test(str)) {
                        dg.curWin.location = dg.curWin.location;
                    }
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
	function submit(stat){
		showAgreeDialog();
		
	}
	//预算所属部门 开始
   	$('#btntxtYsssbmShow').click(function(){
   		
   	/* 	 var top = "90px";//$(this).offset().top - 350;
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
		/* $("#spanCompany input").remove();
		 $("#spanCostCenter input").remove(); */
		 //var ckscode = $("#tree1").tree('getSelected').id;
		 //var cksname = $("#tree1").tree('getSelected').text;
		 $("#subordinateDepartment").val(cksname);
		 $("#subordinateDepartmentCode").val(ckscode);
		 getCompany(ckscode);
		$.ajax({
			type : "POST",
			async : false,
			url : "${dynamicURL}/userInfo/api/cksDept",
			data : "ckscode=" + ckscode,
			success: function(treeObj){
				if(!treeObj){
					alert("没有配置该成本中心");
					cleanDep();//清除根据成本中心带出的信息
					return false;
				}
			
		/* 		//部门树	带公司
			 	$("#adscriptionCompanyCode").val(treeObj.ccompanycode);//资产所属 公司编码
				$("#adscriptionCompany").val(treeObj.ccompanyname);//资产所属公司名称
				
				// 部门树    带成本中心
				$("#costCenterName").val(treeObj.ccostcentername);
				$("#costCenterCode").val(treeObj.ccostcentercode); */
				
				
			} 
		}); 
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
		  $("#adscriptionCompany").val(comname);
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
		$("#subordinateDepartment").val("");
		$("#subordinateDepartmentCode").val("");
		$("#adscriptionCompanyCode").val("");
		$("#adscriptionCompany").val("");
		$("#txtAssetType1").html("");
		$("#txtPlatform").html("");
		$("#costCenterName").val("");
		$("#costCenterCode").val("");
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
   function cleanValue(){
	   $("#assetOriginalValueSum").val("");
	    $("#assetNetValueSum").val("");
	    $("#discardMoneySum").val();
   }
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
	  /*  function getWorkshopType(){
   		var assetType=$("#assetType").val();
   		var costCode=$("#costCenterCode").val();
   		if(assetType=="3"){
   			//$("#trWorkshop").show();
   			
   			if(costCode!=""){
   				$.ajax({
   					type:"post",
   					url:"${dynamicURL}/assetAllocation/api/getAttchConfigWorkshop",
   					data:{costCenter:costCode,assetType:assetType},
   					success:function(data){
   						if (data.success) {
   							$("#trWorkshop").show();
   							$("#workshopType").val(data.result.workShopType);	
   							
   						}else{
   							alert(data.errorMessages.join());
   						}
   						
   					}
   				});
   			}else{
   				alert("请选择部门！");
   			}
   		}
   		
   		
   	}
	  */
		//增行弹窗全选按钮
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
					discardSellMoneySum+=(discardSellMoneySum-0)+(val-0);
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
        	
   		  var url = "${dynamicURL}/assetAllocation/searchPagerList?parentWindowId=discardSellApply";
   		  var parentWindow=dg.curWin; 
   		  new parentWindow.$.dialog({ id: 'showDepDiscard', title: "所有部门", cover: true, page: url, width: 900, height: 450, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
   		   
   	   }
       function fillCostCenter(depCode, depName,costCenterCode,costCenterName) {
 		  
		/*    $("#costCenterName").val("("+costCenterCode+")"+costCenterName);
		   $("#costCenterCode").val(costCenterCode); */
		   $("#subordinateDepartmentCode").val(depCode);
		   $("#subordinateDepartment").val(depName);
		   getCompany(depCode);
       };
	</script>
</body>
</html>