<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调拨申请</title>
<link rel="Stylesheet" type="text/css"
	href="${staticURL}/assetallocation/css/allocationapplicationapply.css">
<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>

<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
		        资产调拨单</div>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<input type="submit" name="" value="提交" onclick="submit(1)"
			id="submitBtn" class="btn"> <input type="button" name=""
			value="保存草稿" id="" onclick="submit(0)" class="btn"> <input
			type="button" name="" value="返回" onclick="" id="fanHui" class="btn">
	</div>
	<div class="titlefontsize"
		style="width: 99%; height: 30px; margin-top: 10px;">
		<div class="imgtitle"></div>
		基本信息
	</div>
	<form id="formId">
		<table style="table-layout: fixed;width:100%;">
			<tr>
				<td class="td_right"><span class="span_color">*</span>流程单据号:</td>
				<td class="td_left"><input name="document" id="document"
					type="text" value="${assetAllocation.document}" readonly="readonly"
					class="td_input" /> <input type="hidden" name='userCode'
					value="${assetAllocation.userCode }"> <input type="hidden"
					name='userName' value="${assetAllocation.userName }"> <input
					type="hidden" name="companyCode"
					value="${assetAllocation.companyCode }"> <input
					type="hidden" name="departmentCode"
					value="${assetAllocation.departmentCode }"> <input
					type="hidden" name="commitType" id="commitType" /> <input
					name="isMail" id="isMail" type="hidden" />
					<input name="oldCommitType" value="${assetAllocation.commitType }" type="hidden">
					 <input
					name="isPhoneMessage" id="isPhoneMessage" type="hidden" /> <input
					name="idea" id="idea" type="hidden" /> <input id="iaMoney"
					value="${assetAllocation.originalValueSum}" type="hidden">
					<input name="activityId" id="activityId" type="hidden"></td>
				<td class="td_right"><span class="span_color">*</span>申请人所属部门:</td>
				<td class="td_left"><input type="text" class="td_input"
					readonly="readonly" name="departmentName"
					value="${assetAllocation.departmentName }"></td>
				<td class="td_right"><span class="span_color">*</span>申请人所属公司:</td>
				<td class="td_left"><input type="text" class="td_input"
					readonly="readonly" name="companyName"
					value="${assetAllocation.companyName }"></td>
			</tr>
			<tr>

				<td class="td_right">申请人:</td>
				<td class="td_left"><input name="createBy" type="text"
					value="${assetAllocation.createBy}" readonly="readonly"
					class="td_input" /> <input type="text"
					value="${sessionScope._user_name}" class="controlhidden" /> <input
					name="id" value="${assetAllocation.id}" type="hidden" /></td>

				<td class="td_right">申请日期:</td>
				<td class="td_left"><input class="td_input"
					name="applicationDate" type="text" readonly="readonly"
					value="${assetAllocation.applicationDate}" id="applicationDate">
				</td>
				<td class="td_right">资产类型:</td>
				<td class="td_left"><select style="width: 165px;"
					class="td_select" name="assetType" id="assetType">
						<option value="">--请选择 --</option>
						<option value="0">IT资产</option>
						<option value="1">行政资产</option>
						<option value="2">计量器具</option>
						<option value="3">机器设备</option>
				</select></td>
			</tr>
			<tr>
				<td class="td_right">调拨后责任人:</td>
				<td class="td_left"><input style="width: 100px;"
					name="postAllocationPeople" type="text" readonly="readonly"
					value="${assetAllocation.postAllocationPeople}"
					id="postAllocationPeople"> <input type="hidden"
					id="peopleValue" name="postPeopleCode"
					value="${assetAllocation.postPeopleCode }"> <input
					id='gvdMeeting_btnClearsk_0' style="width: 20px;"
					class='btnRefView' title='点击清空参照文本框内容' type='button' value='X'
					onclick="ClearObj();"> <input id="showUserListBtn"
					class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
				</td>
				<td class="td_right">调拨后责任人部门:</td>
				<td class="td_left"><input style="width: 110px; height: 20px;"
					value="${assetAllocation.postAllocationDepartment }"
					readonly="readonly" name="postAllocationDepartment" type="text"
					readonly="readonly" id="subordinateDepartment"><input
					id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容'
					type='button' value='X' onclick="cleanDep();"><input
					id="btntxtYsssbmShow" class='btnRefView' title='点击打开参照文本框内容'
					type='button' value='…'> <input name="postDepartmentCode"
					type="hidden" id="departmentCode" style="height: 24px;"
					value="${assetAllocation.postDepartmentCode }"></td>
				<td class="td_right">调拨后归属公司:</td>
				<td class="td_left"><select id="companyName"
					style="width: 163px;" class="td_select" onchange="getCostCenter()">
						<option id="updateCompany"
							value="${assetAllocation.postCompanyCode}">${assetAllocation.postAllocationCompany
							}</option>
				</select> <input class="td_input" type="hidden" name="postAllocationCompany"
					id="postAllocationCompany" type="text"
					value="${assetAllocation.postAllocationCompany}"
					readonly="readonly"> <input type="hidden"
					name="postCompanyCode" id="ccompanycode"
					value="${assetAllocation.postCompanyCode}"></td>
			</tr>
			<tr>
				<td class="td_right">调拨后成本中心:</td>
				<td class="td_left"><input class="td_input"
					name="postAllocationCostCenter" type="text"
					id="postAllocationCostCenter"
					value="${assetAllocation.postAllocationCostCenter}"> <input
					type="hidden" name="postCostcenterCode" id="hideCostCenter"
					value="${assetAllocation.postCostcenterCode}"></td>

				<td class="td_right">是否一次性费用化资产:</td>
				<td class="td_left"><select style="width: 165px;"
					class="td_select" name="isExpenseAssets" id="isExpenseAssets">
						<option class="option_color" value="">--请选择 --</option>
						<option  value="0">否</option>
						<option  value="1">是</option>
				</select></td>
				<td class="td_right">平台:</td>
				<td class="td_left"><input name="platform" id="platform"
					class="td_input" readonly="readonly"
					value="${assetAllocation.platform}"></td>
			</tr>
			<tr>
				<td class="td_right">调配方式:</td>
				<td class="td_left"><input class="td_input"
					name="allocationMethod" id="allocationMethod"
					value="${assetAllocation.allocationMethod}" readonly="readonly">
				</td>
				<td class="td_right">办公地点:</td>
				<td class="td_left"><select name="officeLocation"
					style="width: 163px; height: 24px;" id="officeLocation"
					onchange="getOfficeLocation()">
						<option value=''>--请选择--</option>
						<c:forEach items="${addresslist}" var="userInfo"
							varStatus="status">
							<option value="${userInfo.caddressName}">${userInfo.caddressName}</option>
						</c:forEach>
				</select></td>
				<td class="td_right">调拨后存放位置:</td>
				<td class="td_left"><select id="persentStorageLocation"
					style="width: 163px; height: 24px;"
					name="postAllocationStoragePlace">
						<option name='cpsition' value=''>--请选择--</option>
				</select></td>
			</tr>
			<tr id="trWorkshop">
				<td class="td_right">车间类型:</td>
				<td class="td_left"><input name="workshopType"
					id="workshopType" class="td_input" readonly="readonly"
					value="${assetAllocation.workshopType}"></td>

			</tr>
		</table>
	</form>
	<div class="titlefontsize"
		style="width: 99%; height: 30px; margin-top: 10px;">
		<div class="imgtitle"></div>
		资产信息
	</div>

	<div style="margin-left: 0px; margin-top: 0px;">
		<input type="button" name="addLine" value="增行"
			style="cursor: pointer;" id="showAddDialog" class="btn"> <input
			type="button" name="" value="删行" id="opDelete" class="btn"> <input
			type="button" value="获取资产编码" class="btn" id="getAssetCode"
			style="display: none;">
	</div>

	<div style="overflow-x: scroll;">
		<table border="0" cellpadding="3px" cellspacing="0" width="150%">

			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"></th>
					<th id="thShow" style="display: none;" class='tdpadding'
						scope="col">新资产编码</th>
					<th class='tdpadding' scope="col">资产编码</th>
					<th class='tdpadding' scope="col">资产名称</th>
					<th class='tdpadding' scope="col">资产类型</th>
					<th class='tdpadding' scope="col">现责任人</th>
					<th class='tdpadding' scope="col">归属公司</th>
					<th class='tdpadding' scope="col">规格/型号</th>
					<th class='tdpadding' scope="col">原值</th>
					<th class='tdpadding' scope="col">净值</th>

					<th class='tdpadding' scope="col">使用状态</th>
					<th class='tdpadding' scope="col">成本中心</th>
					<th class='tdpadding' scope="col">存放位置</th>

				</tr>
			</thead>
			
			<tbody id="tbody">
						<c:forEach items="${assetAllocation.generalAsset}" var="gneralAsset"
				varStatus="s">
				<tr style="text-align: center;" id="tr${gneralAsset.id}">
					<td class="insertPercentage"><input type='checkbox'
						value="${gneralAsset.assetCode},${gneralAsset.id},${gneralAsset.companyCode},${gneralAsset.assetOriginalValue},${gneralAsset.assetNetValue},${gneralAsset.costCenterCode}"
						name="radio" id="kuang${gneralAsset.id}" /></td>

					<td>${gneralAsset.assetCode}</td>
					<td>${gneralAsset.assetName}</td>
					<td><c:choose>
							<c:when test="${gneralAsset.assetType=='0'}"> 
										    	IT资产
										   </c:when>
							<c:when test="${gneralAsset.assetType=='1'}"> 
										    	行政资产
										   </c:when>
							<c:when test="${gneralAsset.assetType=='2'}"> 
										    	计量器具  
										   </c:when>
							<c:when test="${gneralAsset.assetType=='3'}"> 
										    	机器设备
										   </c:when>
						</c:choose></td>
					<td>${gneralAsset.assetPerson}</td>
					<td>${gneralAsset.companyCode}</td>
					<td>${gneralAsset.specificationModel}</td>
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
					<td>${gneralAsset.location}</td>
				</tr>

			</c:forEach>
			</tbody>

		</table>
	</div>


	<!-- 点击増行弹出form -->

	<div id="addApplyWindow" name="" class="easyui-window" title="添加申请"
		data-options="iconCls:'icon-save'"
		style="width: 850px; height: 440px; padding: 5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding: 10px;">

				<form action="" id="">
					<table id="Table8" border="0" cellpadding="3px" cellspacing="0"
						style="table-layout: fixed;">
						<tbody>
							<tr>
								<td align="right" class="tdtitle">资产名称:</td>
								<td class="tdtext"><input name="txtAssetsName" type="text"
									id="txtAssetsName" class="colorblur"></td>
								<td align="right" class="tdtitle">资产编码:</td>
								<td class="tdtext"><input name="txtAssetsCode" type="text"
									id="txtAssetsCode" class="colorblur"></td>
								<td align="right" class="tdtitle">成本中心:</td>
								<td class="tdtext"><input name="getCostCenterCode"
									type="text" id="getCostCenterCode" class="colorblur"></td>

							</tr>
							<tr>
								<td align="right" class="tdtitle">现责任人:</td>
								<td class="tdtext"><input name="txtAssetPerson" type="text"
									id="txtAssetPerson" class="colorblur"></td>
								<td align="right" class="tdtitle">型号规格/品牌:</td>
								<td class="tdtext"><input name="txtPutUnderManageCode"
									type="text" id="txtPutUnderManageCode" class="colorblur"></td>
								<td align="right" class="tdtitle">归属公司:</td>
								<td class="tdtext"><input name="" type="text"
									id="textCompanyName" class="colorblur"></td>

							</tr>
						</tbody>
					</table>

				</form>
				<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
					<input type="button" name="btnCancel" value="查询" id="inquiry"
						class="btn"> <input type="button" name="confirm"
						value="确定" onclick="confirm('radioFrame')" id="confirm"
						class="btn"><span style="color: red;">温馨提示：查询前请填写资产编码或成本中心或归属公司</span>

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
																		color="red">*</font>归属公司</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>规格参数</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>原值</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>净值</th>
																	<th class='tdpadding' scope="col"><font
																		color="red">*</font>使用状态</th>
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
	<div style="height: 50px; margin-top: 10px;">
		<table style="table-layout: fixed;">
			<tr>
				<td align="right" class="tdtitle"><span class="span_color">*</span>原值合计:</td>
				<td class="tdtext"><input name="originalValueSum" type="text"
					id="originalValueSum" class="colorblur" readonly="readonly"
					value="${assetAllocation.originalValueSum}"></td>
				<td align="right" class="tdtitle"><span class="span_color">*</span>净值合计:</td>
				<td class="tdtext"><input name="allocationAssetsValueSum"
					type="text" id="allocationAssetsValueSum" class="colorblur"
					value="${assetAllocation.allocationAssetsValueSum}"
					readonly="readonly"></td>
				<td align="right" class="tdtitle"><span class="span_color">*</span>原因说明:</td>
				<td class="tdtext"><input type="text" id="cause"
					class="colorblur" value="${assetAllocation.allocationReason}"></td>

			</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0"
			style="width: 950px;">
			<tbody>
				<tr>
					<td class="titlefontsize" align="left" style="width: 150px">
						<div class="imgtitle"></div> 附件
					</td>
					<td><input type="file" name="multipartFile" id="fileToUpload"
						class="colorblur" onblur="this.className='colorblur';"
						onfocus="this.className='colorfocus';"> <input
						type="submit" name="btnUpload" value="上传" id="btnUpload"
						class="btn"> <input type="submit" name="btnDelFile"
						value="删除" id="btnDelFile" class="btn"></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="divGrid">
							<div>
								<table id="uploadTable" class="adminlist" cellspacing="0"
									rules="all" border="1" id="gvDataList"
									style="border-collapse: collapse;">
									<thead>
										<tr style="white-space: nowrap;">
											<th scope="col"><input type="checkbox"
												name="gvDataList$ctl01$CheckAll"
												onclick="javascript:SelectAllCheckboxesByTableId(this, 'uploadTable');">
											</th>
											<th scope="col">文件</th>
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
	<div id="ysssbmWindow" name="" class="easyui-window" title="预算所属部门"
		data-options="iconCls:'icon-save'"
		style="width: 600px; height: 330px; padding: 5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding: 10px;">
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
		var dg = frameElement.lhgDG;
		$(function() {
			var myDate = new Date();
			var month = myDate.getMonth() + 1;
			var time = myDate.getFullYear() + "-" + month + "-"
					+ myDate.getDate();
			$("#assetType option[value=${assetAllocation.assetType}]").attr(
					"selected", "selected");
			$(
					"#isExpenseAssets option[value=${assetAllocation.isExpenseAssets}]")
					.attr("selected", "selected");
			$("#officeLocation option[value=${assetAllocation.officeLocation}]")
					.attr("selected", "selected");
			$("#applicationDate").val(time);
			getOfficeLocation();
			$(
					"#persentStorageLocation option[value=${assetAllocation.postAllocationStoragePlace}]")
					.attr("selected", "selected");

		});
		var bianMa = "";
		var company = "";
		var v = 0;
		var s = "";
		var array = new Array();
		var companyArray = new Array();
		var originalValueSum = $("#originalValueSum").val();//原值合计
		var netValueSum = $("#allocationAssetsValueSum").val();//净值合计
		var costCenterCode = "";//成本中心编码
		//增行弹窗模糊查询
		$("#inquiry")
				.click(
						function() {

							//资产名称
							var txtAssetsName = $("#txtAssetsName").val();
							//资产编码
							var txtAssetsCode = $("#txtAssetsCode").val();
							//现责任人
							var txtAssetPerson = $("#txtAssetPerson").val();
							//管理编码
							var txtPutUnderManageCode = $(
									"#txtPutUnderManageCode").val();
							var getCostCenterCode = $("#getCostCenterCode")
									.val();
							//归属公司
							var persentCompany = $("#textCompanyName").val();
							if (txtAssetsCode == "" && persentCompany == ""
									&& getCostCenterCode == "") {
								alert("请填写资产编码或成本中心或归属公司");
								return false;
							}
							if (txtAssetsName != '' || txtAssetsCode != ''
									|| txtAssetPerson != ''
									|| txtPutUnderManageCode != ''
									|| persentCompany != ''
									|| getCostCenterCode != '') {
								searchGeneralAsset(txtAssetsName,
										txtAssetsCode, txtAssetPerson,
										txtPutUnderManageCode, persentCompany,
										getCostCenterCode);
							} else {
								//searchInfo();
							}

						});

		function searchGeneralAsset(txtAssetsName, txtAssetsCode,
				txtAssetPerson, txtPutUnderManageCode, persentCompany,
				getCostCenterCode) {
			$("#tbody_add_detail").html();
			var isExpenseAssets = $("#isExpenseAssets").val();
			//资产类型
			var assetType = $("#assetType").val();

			$.ajax({
						type : "POST",
						url : "${dynamicURL}/assetCard/api/searchGeneralAsset",
						data : {
							txtAssetsName : txtAssetsName,
							txtAssetsCode : txtAssetsCode,
							txtAssetPerson : txtAssetPerson,
							txtPutUnderManageCode : txtPutUnderManageCode,
							txtPersentCompany : persentCompany,
							isExpenseAssets : isExpenseAssets,
							assetType : assetType,
							officeLocation : "1",
							costCenterCode : getCostCenterCode
						},
						success : function(data) {
							var divHtml = "";
							for ( var i = 0; i < data.length; i++) {
								divHtml += "<tr align='center' value='"+data[i].id+"' id='tr"+data[i].id+"' class='bodyTr' style='white-space:nowrap;'>";
								divHtml += "<td  class='insertPercentage' id='k"+data[i].id+"' value='"+data[i].id+"' ><input type='checkbox' value='"+data[i].assetCode+","+data[i].id+","+data[i].companyCode+","+data[i].assetOriginalValue+","+data[i].assetNetValue+","+data[i].costCenterCode+"' name='radioFrame' id='kuang"+data[i].id+"'></td>";
								divHtml += "<td class='tdpadding'>"
										+ data[i].assetCode + "</td>";
								if (data[i].assetName != null
										&& "" != data[i].assetName) {
									divHtml += "<td class='tdpadding'>"
											+ data[i].assetName + "</td>";
								} else {
									divHtml += "<td class='tdpadding'>" + ""
											+ "</td>";
								}
								if (data[i].assetType == 0) {
									divHtml += "<td class='tdpadding'>"
											+ "IT资产" + "</td>";
								}
								if (data[i].assetType == 1) {
									divHtml += "<td class='tdpadding'>"
											+ "行政资产" + "</td>";
								}
								if (data[i].assetType == 2) {
									divHtml += "<td class='tdpadding'>"
											+ "计量器具" + "</td>";
								}
								if (data[i].assetType == 3) {
									divHtml += "<td class='tdpadding'>"
											+ "机器设备" + "</td>";
								}
								if (data[i].assetPerson != ""
										&& null != data[i].assetPerson) {
									divHtml += "<td class='tdpadding'>"
											+ data[i].assetPerson + "</td>";
								} else {
									divHtml += "<td class='tdpadding'>" + ""
											+ "</td>";
								}
								divHtml += "<td class='tdpadding'>"
										+ data[i].companyCode + "</td>";
								if (data[i].specificationModel != null
										&& data[i].specificationModel != "") {
									divHtml += "<td class='tdpadding'>"
											+ data[i].specificationModel
											+ "</td>";
								} else {
									divHtml += "<td class='tdpadding'>" + ""
											+ "</td>";
								}
								divHtml += "<td class='tdpadding'><input type='hidden'   value='"+data[i].assetOriginalValue+"'> "
										+ data[i].assetOriginalValue + "</td>";
								divHtml += "<td class='tdpadding'><input type='hidden' 	 value='"+data[i].assetNetValue+"'> "
										+ data[i].assetNetValue + "</td>";
								if (data[i].useState == null
										|| data[i].useState == "") {
									divHtml += "<td class='tdpadding'>" + ""
											+ "</td>";
								} else if (data[i].useState == 0) {
									divHtml += "<td class='tdpadding'>" + "在用"
											+ "</td>";
								} else if (data[i].useState == 1) {
									divHtml += "<td class='tdpadding'>" + "闲置中"
											+ "</td>";
								} else if (data[i].useState == 2) {
									divHtml += "<td class='tdpadding'>"
											+ "大修理中" + "</td>";
								} else if (data[i].useState == 3) {
									divHtml += "<td class='tdpadding'>" + "改造中"
											+ "</td>";
								} else if (data[i].useState == 4) {
									divHtml += "<td class='tdpadding'>"
											+ "报废申请中" + "</td>";
								} else if (data[i].useState == 5) {
									divHtml += "<td class='tdpadding'>" + "已报废"
											+ "</td>";
								} else if (data[i].useState == 6) {
									divHtml += "<td class='tdpadding'>"
											+ "变卖申请中" + "</td>";
								} else if (data[i].useState == 7) {
									divHtml += "<td class='tdpadding'>" + "已变卖"
											+ "</td>";
								} else {
									divHtml += "<td class='tdpadding'>" + ""
											+ "</td>";
								}
								divHtml += "<td class='tdpadding'>"
										+ data[i].costCenterCode + "</td>";
								if (null != data[i].location
										&& "" != data[i].location) {
									divHtml += "<td class='tdpadding'>"
											+ data[i].location + "</td>";
								} else {
									divHtml += "<td class='tdpadding'>" + ""
											+ "</td>";
								}

								divHtml += "</tr>";
							}
							$("#tbody_add_detail").html(divHtml);
						}
					});
		}

		//返回关闭申请弹窗
		$("#fanHui").click(function() {
			//关闭窗口
			dg.cancel();
		});

		//更换是否费用资产化，清空之前选项
		$("#isExpenseAssets").change(function() {
			$("#tbody").html("");
			array = null;
			s = "";
			bianMa = "";
			$("#netValue").val("");
			$("#checkAll").attr("checked", false);
		});
		//更新类型，清空之前选项
		$("#assetType")
				.change(
						function() {
							$("#tbody").html("");
							array = null;
							s = "";
							bianMa = "";
							$("#netValue").val("");
							$("#checkAll").attr("checked", false);
							var assetType = $("#assetType").val();
							var costCode = $("#costCenterCode").val();
							if (assetType != "3") {
								$("#workshopType").val("");
								return false;
							}
							if (assetType == "3" && costCode == "") {
								$("#workshopType").val("");
								$("#assetType option[value='']").attr(
										"selected", "selected");
								alert("请选择部门");
								return false;
							}
							//$("#trWorkshop").show();

							$.ajax({
										type : "post",
										url : "${dynamicURL}/assetAllocation/api/getAttchConfigWorkshop",
										data : {
											costCenter : costCode,
											assetType : assetType
										},
										success : function(data) {
											if (data.success) {
												$("#trWorkshop").show();
												$("#workshopType")
														.val(
																data.result.workShopType);

											} else {
												alert(data.errorMessages.join());
											}

										}
									});

						});

		//删行
		$("#opDelete").click(
				function() {
					var id = "";
					var code = "";
					var companyName = "";
					var assetOriginalValue = 0;
					var assetNetValue = 0;
					var companyCode = "";
					var centerCode = "";
					var centerCD = "";
					$("input[name='radio']").each(function() {

						var idAndCode = $(this).val();
						var id = idAndCode.split(",")[1];
						var companyCode = idAndCode.split(",")[2];
						var code = idAndCode.split(",")[0];
						var assetOriginalValue = idAndCode.split(",")[3];//原值
						var assetNetValue = idAndCode.split(",")[4];//净值
						var centerCode = idAndCode.split(",")[5];
						if (bianMa == "") {
							bianMa = code;
						} else {
							bianMa = bianMa + "," + code;
						}
						/* company = company+companyCode+",";
						costCenterCode=costCenterCode+centerCode+","; */
						if (company == "") {
							company = companyCode;
						} else {
							company = company + "," + companyCode;
						}
						if (costCenterCode == "") {
							costCenterCode = centerCode;
						} else {
							costCenterCode = costCenterCode + "," + centerCode;
						}

					});
					$("input[name='radio']:checked").each(
							function() {
								var value = $(this).val();
								id = value.split(",")[0];
								code = value.split(",")[1];
								//获得本行的公司编码
								companyName = value.split(",")[2];
								assetOriginalValue = value.split(",")[3];
								assetNetValue = value.split(",")[4];
								centerCode = value.split(",")[5];
								array = null;
								companyArray = null;
								companyCode = company.split(",");
								centerCD = costCenterCode.split(",");
								assetCode = bianMa.split(",");
								var tr = $(this).parent().parent();
								originalValueSum = (originalValueSum - 0)
										- (assetOriginalValue - 0);

								netValueSum = (netValueSum - 0)
										- (assetNetValue - 0);

								$("#tr" + code).remove();
							});
					/* 	company="";
						bianMa="";
						costCenterCode="";
					
						for(var i=0;i<companyCode.length;i++){
							
							if(companyCode[i]!=companyName){
								if(company==""){
									company=companyCode[i];
								}else{
									
									company=company+","+companyCode[i];
								}
								
							}
							
						}
						for(var i=0;i<centerCD.length;i++){
							if(centerCD[i]!=centerCode){
								if(costCenterCode==""){
									costCenterCode=centerCD[i];
								}else{
									costCenterCode=costCenterCode+","+centerCD[i];
								}
								
							}
						}
						
						for(var i=0;i<assetCode.length;i++){
						
							if(assetCode[i]!=code){
								if(bianMa==""){
									bianMa=assetCode[i];
								}else{
									bianMa=bianMa+","+assetCode[i];
								}
								
							}
							
						} */

					$("#originalValueSum").val(originalValueSum.toFixed(2));
					$("#allocationAssetsValueSum").val(netValueSum.toFixed(2));
					$('#checkAll').attr("checked", false);

					judgeCompany();
					sumAll();

				});

		var number = 0;
		var zhi = 0;
		//增行   确定
		function confirm(radioFrame) {
			var arrayCheck = new Array(); //行明细的 资产编码+公司
			var bianMaCheck = "";
			var flag = false;
			var errorMessage = "";

			var yzSumBmAndGsbm = "";
			array = bianMa.split(",");

			$("input[name='radio']")
					.each(
							function() {
								var yzbmAndGsbm = $(this).val().split(",")[0]
										+ "[" + $(this).val().split(",")[2]
										+ "]";
								if (yzSumBmAndGsbm == "") {
									yzSumBmAndGsbm = yzbmAndGsbm;
								} else {
									yzSumBmAndGsbm = yzSumBmAndGsbm + ","
											+ yzbmAndGsbm;
								}

							});
			arrayCheck = yzSumBmAndGsbm.split(",")

			var num = 0;
			/* $("input[name='radio']").each(function(){
				var   idAndCode = $(this).val();
				
				 var  id = idAndCode.split(",")[1];
				 var  companyCode = idAndCode.split(",")[2];
				 var  code=idAndCode.split(",")[0];
				 var  assetOriginalValue=idAndCode.split(",")[3];//原值
				 var  assetNetValue=idAndCode.split(",")[4];//净值
				 var  centerCode=idAndCode.split(",")[5];
				
				if(bianMa==""){
					bianMa=code;
				}else{
					bianMa=bianMa+","+code;
				}
				if(bianMaCheck==""){
					bianMaCheck=code+"["+companyCode+"]";
				}else{
					bianMaCheck=bianMaCheck+","+code+"["+companyCode+"]";
				}
				array = bianMa.split(",");
				arrayCheck = bianMaCheck.split(",");
				costCenterCode=costCenterCode+centerCode+","; 
				if(company==""){
					company=companyCode;
				}else{
					company=company+","+companyCode;
				}
				
				if(costCenterCode==""){
					costCenterCode=centerCode;
				}else{
					costCenterCode=costCenterCode+","+centerCode;
				} 
				if(assetOriginalValue!='null'&&assetOriginalValue!="暂无数据"){
					originalValueSum=(originalValueSum-0)+(assetOriginalValue-0);
				}
				if(assetNetValue!="null"&&assetNetValue!='暂无数据'){
					netValueSum=(netValueSum-0)+(assetNetValue-0);
				}
				
				
			}); */

			$("input[name='radioFrame']:checked")
					.each(
							function() {
								var idAndCode = $(this).val();
								var id = idAndCode.split(",")[1];
								var companyCode = idAndCode.split(",")[2];
								var code = idAndCode.split(",")[0];
								//alert(code+"编码"+id="");
								var assetOriginalValue = idAndCode.split(",")[3];//原值
								var assetNetValue = idAndCode.split(",")[4];//净值
								var centerCode = idAndCode.split(",")[5];
								/* 	if(assetOriginalValue!='null'&&assetOriginalValue!='暂无数据'){
										 if(originalValueSum==0){
											 originalValueSum=assetOriginalValue-0;
										 }else{
											 originalValueSum=(originalValueSum-0)+(assetOriginalValue-0);
										 }
									}
									if(assetNetValue!='null'&&assetNetValue!='暂无数据'){
										 if(netValueSum==0){
											 netValueSum=assetNetValue-0;
										 }else{
											 netValueSum=(netValueSum-0)+(assetNetValue-0);
										 }
									} */
								var codeAndCompany = code + "[" + companyCode
										+ "]";

								for ( var i = 0; i < arrayCheck.length; i++) {
									if (arrayCheck[i] == codeAndCompany) {

										errorMessage = errorMessage
												+ codeAndCompany + ",";
										flag = true;
									}
								}
								if (flag) {
									/* alert(errorMessage); */
									return false;
								}

								/* 		for(var i =0;i<array.length;i++){
													alert(array[i]+"wwwwwwww"+code)
													if(array[i]==code){
														
														s = s+code+",";
														num = 0;
														break;
													}
													else{
														
														num = 1;
													}
											} */

								/*  if(num==1){ */
								number = number + 1;
								var tr = $(this).parent().parent();

								bianMa = bianMa + code + ",";

								if (company == "") {
									company = company + companyCode + ",";
								} else {
									company = company + "," + companyCode;
								}
								if (costCenterCode == "") {
									costCenterCode = costCenterCode
											+ centerCode + ",";
								} else {
									costCenterCode = costCenterCode + ","
											+ centerCode;
								}
								$("#kuang" + id).remove();
								$("#k" + id)
										.append(
												"<input type='checkbox' value='"+code+","+id+","+companyCode+","+assetOriginalValue+","+assetNetValue+","+centerCode+"' name='radio'/>");

								$("#tbody").append(tr);
								/*  } */
							});
			if (errorMessage.length > 1) {
				alert("编码" + errorMessage + "已存在");
				return false;
			}
			/* 		 s=s.substring(0, s.length-1);
			
			 s="";
			 if(s!=""){
			 alert("编码"+s+"已存在");
			 s = "";
			 return;
			 }
			 else{
			 $('#checkAllFrame').attr("checked",false);
			 $("#originalValueSum").val(originalValueSum.toFixed(2));
			 $("#allocationAssetsValueSum").val(netValueSum.toFixed(2));
			 //关闭窗口
			 $("#addApplyWindow").window('close');
			 } 
			 $("#originalValueSum").val(originalValueSum.toFixed(2));
			 $("#allocationAssetsValueSum").val(netValueSum.toFixed(2));*/
			$('#checkAllFrame').attr("checked", false);
			judgeCompany();
			sumAll();

		}
		function judgeCompany() {

			var companyFlag = true;
			var centerCodeFlag = true;

			var companyNext = $("#ccompanycode").val();
			var costCenter = $("#hideCostCenter").val();
			var company = "";
			var costCenterCode = "";
			$("input[name='radio']").each(function() {
				var companyCode = $(this).val().split(",")[2];
				var costCenter = $(this).val().split(",")[5];
				if (company == "") {
					company = companyCode;
				} else {
					company = company + "," + companyCode;
				}
				if (costCenterCode == "") {
					costCenterCode = costCenter;
				} else {
					costCenterCode = costCenterCode + "," + costCenter;
				}
			});
			companyCD = company.split(",");

			centerCode = costCenterCode.split(",");

			for ( var i = 0; i < companyCD.length; i++) {

				if (companyNext != companyCD[i]) {
					companyFlag = false;
				}
			}

			for ( var i = 0; i < centerCode.length; i++) {

				if (costCenter != centerCode[i]) {
					centerCodeFlag = false;
				}
			}

			if ((companyFlag == true && centerCodeFlag == true)) {

				$("#allocationMethod").val("个人间调拨");

			}
			if (companyFlag == true && centerCodeFlag == false) {
				$("#allocationMethod").val("部门间调拨");

			}

			if ((companyFlag == false)) {

				$("#allocationMethod").val("公司间调拨");

			}
			if (company == "" && costCenterCode == "") {
				$("#allocationMethod").val("");

			}
		}

		//增行弹窗
		$("#showAddDialog").click(function() {
			var peopleValue = $("#peopleValue").val();
			$("#tbody_add_detail").html("");
			if ($("#isExpenseAssets").val() == "") {
				alert("请选择是否一次性费用化资产！");
				return;
			}
			if ($("#assetType").val() == "") {
				alert("请选择资产类型！");
				return;
			}
			if ($("#companyName").val() == "") {
				alert("请选择调拨后归属公司！");
				return;
			}
			if ($("#officeLocation").val() == "") {
				alert("请选择办公地点！");
				return;
			}
			if ($("#persentStorageLocation").val() == "") {
				alert("请选择调拨后存放位置！");
				return;
			}
			if (peopleValue != "") {
				var top = "10px";
				var left = "70px";
				$('#addApplyWindow').window('open').window('resize', {
					width : '850px',
					height : '440px',
					top : top,
					left : left
				});
				//searchInfo();
			} else {
				alert("请先选择调拨后责任人！");
			}
		});

		function searchInfo() {
			$("#tbody_add_detail").html();
			//是否费用资产化
			var isExpenseAssets = $("#isExpenseAssets").val();

			//资产类型
			var assetType = $("#assetType").val();

			$.ajax({
						type : "POST",
						url : "${dynamicURL}/assetCard/api/searchAssetCardInfo",
						data : {
							isExpenseAssets : isExpenseAssets,
							assetType : assetType,
							officeLocation : "1"
						},
						success : function(data) {
							var divHtml = "";
							for ( var i = 0; i < data.length; i++) {
								divHtml += "<tr align='center' value='"+data[i].id+"' id='tr"+data[i].id+"' class='bodyTr' style='white-space:nowrap;'>";
								divHtml += "<td class='insertPercentage' id='k"+data[i].id+"' value='"+data[i].id+"' ><input type='checkbox' value='"+data[i].assetCode+","+data[i].id+","+data[i].companyCode+","+data[i].assetOriginalValue+","+data[i].assetNetValue+","+data[i].costCenterCode+"' name='radioFrame' id='kuang"+data[i].id+"'></td>";
								divHtml += "<td class='tdpadding'>"
										+ data[i].assetCode + "</td>";
								divHtml += "<td class='tdpadding'>"
										+ data[i].assetName + "</td>";
								if (data[i].assetType == 0) {
									divHtml += "<td class='tdpadding'>"
											+ "IT资产" + "</td>";
								}
								if (data[i].assetType == 1) {
									divHtml += "<td class='tdpadding'>"
											+ "行政资产" + "</td>";
								}
								if (data[i].assetType == 2) {
									divHtml += "<td class='tdpadding'>"
											+ "计量器具" + "</td>";
								}
								if (data[i].assetType == 3) {
									divHtml += "<td class='tdpadding'>"
											+ "机器设备" + "</td>";
								}
								if (data[i].assetPerson != null
										&& "" != data[i].assetPerson) {
									divHtml += "<td class='tdpadding'>"
											+ data[i].assetPerson + "</td>";
								}
								divHtml += "<td class='tdpadding'>"
										+ data[i].companyName + "</td>";
								if (data[i].specificationModel != null) {
									divHtml += "<td class='tdpadding'>"
											+ data[i].specificationModel
											+ "</td>";
								} else {
									divHtml += "<td class='tdpadding'>"
											+ "暂无数据" + "</td>";
								}
								divHtml += "<td class='tdpadding'><input type='hidden'   value='"+data[i].assetOriginalValue+"'> "
										+ data[i].assetOriginalValue + "</td>";
								divHtml += "<td class='tdpadding'><input type='hidden' 	 value='"+data[i].assetNetValue+"'> "
										+ data[i].assetNetValue + "</td>";
								if (data[i].useState == null
										|| data[i].useState == "") {
									divHtml += "<td class='tdpadding'>"
											+ "暂无数据" + "</td>";
								} else if (data[i].useState == 0) {
									divHtml += "<td class='tdpadding'>" + "在用"
											+ "</td>";
								} else if (data[i].useState == 1) {
									divHtml += "<td class='tdpadding'>" + "闲置中"
											+ "</td>";
								} else if (data[i].useState == 2) {
									divHtml += "<td class='tdpadding'>"
											+ "大修理中" + "</td>";
								} else if (data[i].useState == 3) {
									divHtml += "<td class='tdpadding'>" + "改造中"
											+ "</td>";
								} else if (data[i].useState == 4) {
									divHtml += "<td class='tdpadding'>"
											+ "报废申请中" + "</td>";
								} else if (data[i].useState == 5) {
									divHtml += "<td class='tdpadding'>" + "已报废"
											+ "</td>";
								} else if (data[i].useState == 6) {
									divHtml += "<td class='tdpadding'>"
											+ "变卖申请中" + "</td>";
								} else if (data[i].useState == 7) {
									divHtml += "<td class='tdpadding'>" + "已变卖"
											+ "</td>";
								} else {
									divHtml += "<td class='tdpadding'>"
											+ "暂无数据" + "</td>";
								}

								divHtml += "<td class='tdpadding'>"
										+ data[i].costCenterCode + "</td>";

								if (data[i].location != null
										&& data[i].location != "") {
									divHtml += "<td class='tdpadding'>"
											+ data[i].location + "</td>";
								} else {
									divHtml += "<td class='tdpadding'>" + ""
											+ "</td>";
								}

								divHtml += "</tr>";
							}
							$("#tbody_add_detail").html(divHtml);
						}
					});
		}

		//全选按钮 
		$("#checkAll").click(function() {
			$("[name='radio']").each(function() {
				if ($(this).attr("checked")) {
					$(this).removeAttr("checked");
				} else {
					$(this).attr("checked", 'true');
				}
			});
		});

		//增行弹窗全选按钮
		$("#checkAllFrame").click(function() {
			$("[name='radioFrame']").each(function() {
				if ($(this).attr("checked")) {
					$(this).removeAttr("checked");
				} else {
					$(this).attr("checked", 'true');
				}
			});
		});

		function showAgreeDialog(changeAmount) {
			var document = $("#document").val();

			var url = "${dynamicURL}/workflow/showUpdateCommitDialog?parentWindowId=update&document="
					+ document
					+ "&typeId="
					+ 77002
					+ "&changeAmount="
					+ changeAmount;

			var dlg = new dg.curWin.$.dialog({
				id : 'agreeDialog',
				parent : dg,
				title : '邮件短信提醒',
				cover : true,
				page : url,
				width : 400,
				height : 210,
				btnBar : false,
				resize : false,
				maxBtn : false
			});
			dlg.ShowDialog();
			return dlg;
		}
		function commitAgree(isMail, isPhoneMessage, idea, activityId) {
			$('#isMail').val(isMail);
			$('#isPhoneMessage').val(isPhoneMessage);
			$('#idea').val(idea);
			$("#activityId").val(activityId);
			$('#agreeBtn').val('提交...');
			$("#submitBtn").val("提交ing");
			var newAssetCodeList = "";
			$("#submitBtn").attr("disabled", true);
			var sublist = "";
			$("input[name='radio']").each(function() {
				var ids = $(this).val();
				var id = ids.split(",")[1];
				if (sublist == null && sublist == "") {
					sublist = id;
				} else {
					sublist = id + "," + sublist;
				}
			});

			var originalValueSum = $("#originalValueSum").val();//原值合计
			var netValueSum = $("#allocationAssetsValueSum").val();//净值合计
			var cause = $("#cause").val();
			$("#assetsValueSum").val(originalValueSum);
			$("#valueSum").val(netValueSum);
			$("#reason").val(cause);
			if (cause == "") {
				alert("请填写原因说明");
				$("#submitBtn").val("提交");
				$("#submitBtn").attr("disabled", false);
			} else {
				$.ajax({
							type : "POST",

							url : "${dynamicURL}/assetAllocation/api/updateAssetAllocation?"
									+ $("#formId").serialize(),
							data : {
								ids : sublist,
								originalValueSum : originalValueSum,
								netValueSum : netValueSum,
								cause : cause,
								newAssetCodeList : newAssetCodeList
							},
							async : false,
							success : function(data) {
								if (data.success) {
									alert("修改成功！");
									dg.cancel();
									dg.curWin.location.reload();
									$("#submitBtn").val("提交");
									$("#submitBtn").attr("disabled", false);
								} else {
									var str = data.errorMessages;
				                    alert(str);
				                    var reg = new RegExp("^.*提交成功.*$");
				                    if (reg.test(str)) {
				                        dg.curWin.location = dg.curWin.location;
				                    }
									$("#submitBtn").val("提交");
									$("#submitBtn").attr("disabled", false);
								}
							}
						});
			}

		}
		//保存提交数据
		function submit(stat) {
			$("#commitType").val(stat);
			if ($("#isExpenseAssets").val() == "") {
				alert("请选择是否一次性费用化资产！");
				return;
			}
			if ($("#assetType").val() == "") {
				alert("请选择资产类型！");
				return;
			}
			if ($("#companyName").val() == "") {
				alert("请选择调拨后归属公司！");
				return;
			}
			if ($("#officeLocation").val() == "") {
				alert("请选择办公地点！");
				return;
			}
			if ($("#persentStorageLocation").val() == "") {
				alert("请选择调拨后存放位置！");
				return;
			}
			if (stat == '1') {
				//跨步提交判断iaMoney是否发生了变化
				var changeAmount = "Y";
				if (($("#originalValueSum").val() - 0)
						- ($("#iaMoney").val() - 0) == 0) {
					changeAmount = "N"
				}
				showAgreeDialog(changeAmount);
			} else {
				
				var newAssetCodeList = "";
				
				var sublist = "";
				$("input[name='radio']").each(function() {
					var ids = $(this).val();
					var id = ids.split(",")[1];
					if (sublist == null && sublist == "") {
						sublist = id;
					} else {
						sublist = id + "," + sublist;
					}
				});

				var originalValueSum = $("#originalValueSum").val();//原值合计
				var netValueSum = $("#allocationAssetsValueSum").val();//净值合计
				var cause = $("#cause").val();
				$("#assetsValueSum").val(originalValueSum);
				$("#valueSum").val(netValueSum);
				$("#reason").val(cause);

				if (cause == ""&&stat == '1') {
					alert("请填写原因说明");
					$("#submitBtn").val("提交");
					$("#submitBtn").attr("disabled", false);
					return false;
				}

				$.ajax({
							type : "POST",
							cache : false,
							url : "${dynamicURL}/assetAllocation/api/updateAssetAllocation?"
									+ $("#formId").serialize(),
							data : {
								ids : sublist,
								originalValueSum : originalValueSum,
								netValueSum : netValueSum,
								cause : cause,
								newAssetCodeList : newAssetCodeList
							},
							async : false,
							success : function(data) {
								if (data.success) {
									alert("修改成功！");
									dg.cancel();
									dg.curWin.location.reload();
									$("#submitBtn").val("提交");
									$("#submitBtn").attr("disabled", false);
								} else {
									var str=data.errorMessages;
				                	alert(str);
				                	if(str.indexOf("提交成功")>0){
				                		 dg.curWin.location = dg.curWin.location;
				                	}
									$("#submitBtn").val("提交");
									$("#submitBtn").attr("disabled", false);
								}
							}
						});

			}
		}
		//清空调拨后责任人把带出的也清空
		function ClearObj() {
			$("#postAllocationPeople").val("");
			$("#subordinateDepartment").val("");
			$("#postAllocationCostCenter").val("");
			$("#companyName").find("option").remove();
			$("#peopleValue").val("");
			$("#platform").val("");
		}

		//选择调拨后负责人
		$("#showUserListBtn").click(function() {

			showUserDialog();
		});

		function showUserDialog() {

			var url = "${dynamicURL}/user/getUserListInAttachConfig?parentWindowId=update";
			var parentWindow = dg.curWin;
			new parentWindow.$.dialog({
				id : 'list',
				title : "所有人员",
				cover : true,
				page : url,
				width : 700,
				height : 400,
				btnBar : false,
				resize : false,
				maxBtn : false
			}).ShowDialog();

		}

		function fillVehicleUser(userCode, userName) {
			$('#peopleValue').val(userCode);
			$('#postAllocationPeople').val(userName);
			getUserInfo(userCode);

		};
		function fillCostCenter(depCode, depName, costCenterCode,
				costCenterName) {
			$("#postAllocationCostCenter").val(
					"(" + costCenterCode + ")" + costCenterName);
			$("#hideCostCenter").val(costCenterCode);
			$("#departmentCode").val(depCode);
			$("#subordinateDepartment").val(depName);
			getCompany(depCode);
		};
		//根据调拨后责任人查询所属部门公司成本中心
		function getUserInfo(userCode) {

			$.ajax({
				type : "post",
				url : "${dynamicURL}/assetAllocation/api/getUserInfo",
				data : {
					userCode : userCode
				},
				async : false,
				success : function(data) {
					/*    $("#postAllocationCostCenter").val(data.result.ccostcentername);//调拨后成本中心
					   $("#hideCostCenter").val(data.result.ccostcentercode);//调拨后成本中心编码 */
					$("#subordinateDepartment").val(data.result.cdepName);//调拨后部门名称
					$("#departmentCode").val(data.result.cdepCode);//调拨后部门编码
					/*    $("#postAllocationCompany").val(data.result.ccompanyname);
					   $("#ccompanycode").val(data.result.ccompanycode); */

					getCompany(data.result.cdepCode);
				}
			});
		}
		function getCompany(cdepCode) {
			$.ajax({
				type : "post",
				url : "${dynamicURL}/assetAllocation/api/getCompany",
				data : {
					ckscode : cdepCode
				},
				async : false,
				success : function(data) {
					var html = "";
					if (data.length > 1) {
						html += "<option value=''>请选择</option>";
					}
					for ( var i = 0; i < data.length; i++) {
						html += "<option value="+data[i].ccompanycode+">("
								+ data[i].ccompanycode + ")"
								+ data[i].ccompanyname + "</option>";
					}
					$("#companyName").html(html);
					getCostCenter();
				}
			});
		}
		function getCostCenter() {
			var comcode = $("#companyName").val();
			$("#updateCompany").remove();
			var comname = $("#companyName option:selected").text();
			$("#ccompanycode").val(comcode);
			$("#postAllocationCompany").val(comname);
			var depcode = $("#departmentCode").val();

			$.ajax({
				type : "post",
				url : "${dynamicURL}/assetAllocation/api/getCostCenter",
				data : {
					companyCode : comcode,
					depcode : depcode
				},
				async : false,
				success : function(data) {
					$("#postAllocationCostCenter").val("("+data.ccostcentercode+")"+data.ccostcentername);
					$("#hideCostCenter").val(data.ccostcentercode);
				}
			});
			getComPlatform($("#hideCostCenter").val());
			judgeCompany();

		}
		//根据办公地点查询调拨后存放位置
		function getOfficeLocation() {

			var officeLocation = officeLocation = $(
					'#officeLocation option:selected').text();

			$.ajax({
						type : "post",
						url : "${dynamicURL}/userInfo/api/getCpsition",
						data : {
							workAddress : officeLocation
						},
						async : false,
						success : function(data) {

							$("#persentStorageLocation").find("option")
									.remove();
							$("#persentStorageLocation")
									.append(
											"<option name='cpsition'  value=''>--请选择--</option>");
							for ( var i = 0; i < data.length; i++) {
								$("#persentStorageLocation").append(
										"<option name='cpsition'  value='"+data[i].cpsition+"'>"
												+ data[i].cpsition
												+ "</option>");
							}

						}
					});
		}
		$('#btnUpload').click(
				function() {
					var fileElementId = $(this).prev().attr('id');
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

					uploadFile('${dynamicURL}',
							'${sessionScope._user_nick_name}',
							'${sessionScope._user_name}', documentNo,
							$('#uploadTable > tbody'), fileElementId);

				});

		$('#btnDelFile').click(function() {
			deleteFiles('${dynamicURL}', "uploadTable");
		});
		//预算所属部门 开始
		$('#btntxtYsssbmShow').click(function() {

			var top = "90px";//$(this).offset().top - 350;
			var left = "120px";//$(this).offset().left-180;
			if ($("#postAllocationPeople").val() == "") {
				alert("请先选择调拨后责任人！");
				return;
			}
			/* 	$('#ysssbmWindow').window('open').window('resize', {
					width : '450px',
					height : '300px',
					top : top,
					left : left
				});

				$("#tree1").tree({
					url : '${dynamicURL}/department/searchAllDepartmentTreeInit',
					lines : true,
					onClick : function(node) {
						setTree(node.id, node.text);
					}
				}); */
			showDepCostCenter();
		});
		function showDepCostCenter() {

			var url = "${dynamicURL}/assetAllocation/searchPagerList?parentWindowId=update";
			var parentWindow = dg.curWin;
			new parentWindow.$.dialog({
				id : 'showDep',
				title : "所有部门",
				cover : true,
				page : url,
				width : 900,
				height : 450,
				btnBar : false,
				resize : false,
				maxBtn : false
			}).ShowDialog();

		}
		function setTree(ckscode, cksname) {
			/* $("#spanCompany input").remove();
			 $("#spanCostCenter input").remove(); */
			//var ckscode = $("#tree1").tree('getSelected').id;
			//var cksname = $("#tree1").tree('getSelected').text;
			$("#departmentCode").val(ckscode);
			$("#subordinateDepartment").val(cksname);
			$.ajax({
				type : "POST",
				async : false,
				url : "${dynamicURL}/userInfo/api/cksDept",
				data : "ckscode=" + ckscode,
				success : function(treeObj) {
					if (!treeObj) {
						alert("没有配置该成本中心");
						cleanDep();//清除根据成本中心带出的信息
						return false;
					}

					getCompany(ckscode);

					getComPlatform(treeObj.ccostcentercode);//成本中心带平台

					$('#ysssbmWindow').window('close');

				}
			});

		}
		function cleanDep() {
			$("#postAllocationCostCenter").val("");
			$("#hideCostCenter").val("");
			$("#postAllocationCompany").val("");
			$("#companyName").find("option").remove();
			$("#platform").val("");
			$("#subordinateDepartment").val("");
			$("#departmentCode").val("");
		}
		/*     	function method(){
		    		var method= $("#allocationMethod").val();
		    		if(method=="公司间调拨"){
		    			$(".allMethode").remove();
		    			var i=0;
		    			$("input[name='radio']").each(function(){
		    				var id=$(this).val().split(",")[0];
		    				
		    				 var tr = $(this).parent().parent();
		    				 $("<td class='tdpadding allMethode'><input style='width:80px;' id='red"+i+"'  name='redMethod' type='text' readonly='readonly' ></td>").insertAfter(tr.children(".insertPercentage"));
		    			i=(i-0)+1
		    			});
		    		}else{
		    			$(".allMethode").remove();
		    		}
		    	} */
		/*     	$("#getAssetCode").click(function(){
		    		$("#getAssetCode").val("获取资产编码ing");
		    		$("#getAssetCode").attr("disabled",true);
		    		var ids="";
		    		$("input[name='radio']").each(function(){
		    			var value=$(this).val();
		    			var id=value.split(",")[1];
		    			if(ids==""){
		    				ids=id;
		    			}else{
		    				ids=ids+","+id;
		    			}
		    		});
		    		var postCompanyCode=$("#ccompanycode").val();
		    		var postCostCenterCode=$("#hideCostCenter").val();
		    		$.ajax({
		    			type:"POST",
		    			url:"${dynamicURL}/assetAllocation/api/getAssetCodeSap",
		    			data:{ids:ids,postCompanyCode:postCompanyCode,postCostCenterCode:postCostCenterCode},
		    			success:function(data){
		    				var codeList=data.newCodeList;
		    				var arryCode=codeList.split(",");
		    				
		    				for(var i=0;i<arryCode.length;i++){
		    					$("#red"+i).val(arryCode[i]);
		    				}
		    				$("#getAssetCode").val("获取资产编码");
		    	    		$("#getAssetCode").attr("disabled",false);
		    			}
		    		});
		    	}); */
		//根据公司查平台
		function getComPlatform(postAllocationCostCenter) {

			$.ajax({
				type : "post",
				url : "${dynamicURL}/assetAllocation/api/getAttchConfig",
				//async : false,
				data : {
					costCenter : postAllocationCostCenter
				},
				async : false,
				success : function(data) {
					$("#platform").val(data.platform);

				}
			});

		}
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
		function sumAll() {
			var originalValueSum = 0;
			var allocationAssetsValueSum = 0;
			$("input[name='radio']").each(function() {
				var ids = $(this).val();
				var originalValue = ids.split(",")[3];
				var allocationAssetsValue = ids.split(",")[4];
				originalValueSum += originalValue - 0;
				allocationAssetsValueSum += allocationAssetsValue - 0;
			});
			$("#originalValueSum").val(originalValueSum.toFixed(2));
			$("#allocationAssetsValueSum").val(
					allocationAssetsValueSum.toFixed(2));
		}
	</script>
</body>
</html>