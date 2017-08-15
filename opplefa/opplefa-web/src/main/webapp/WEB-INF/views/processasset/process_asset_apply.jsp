<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组合资产</title>
<%-- <link rel="Stylesheet" type="text/css" href="${staticURL}/assetallocation/css/allocationapplicationapply.css">
<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css"> --%>
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
		<!-- <input type="submit" name="" value="提交" id="conservation" class="btn"> -->
		<input type="submit" name="" value="提交" onclick="submit(1)" class="btn">
		<!-- <input type="button" name="" value="保存草稿"  onclick="submit(0)" class="btn"> -->
		<input type="button" name="" value="返回" onclick="" id="fanHui" class="btn">
	</div>
	<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
				<div class="imgtitle"></div>
						基本信息 
				</div>
	<form id="formId">			
	 <table>
	 		<tr>
				
				<td class="td_right"><span class="span_color">*</span>组合资产编码:</td>
				<td class="td_left"><input name="document" id="document" type="text"
					value="${processAssets.document}" readonly="readonly" class="td_input" /> 
					<input type="hidden" name='userCode' value="${processAssets.userCode }">
					<input type="hidden" name='userName' value="${processAssets.userName }">
					<input type="hidden" name="companyCode" value="${processAssets.companyCode }">
					<input type="hidden" name="departmentCode" value="${processAssets.departmentCode }">
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
					<input class="td_input"  name="" type="text" readonly="readonly" value="${processAssets.applicationDate}" id="applicationDate">
				</td>
		 		<td class="td_right"><span class="span_color">*</span>组合资产责任人:</td>
				<td class="td_left">
				<input name="assetOwnerCode" id="assetOwnerCode" type="hidden">
				<input  style="width: 105px;" class="td_input" name="assetOwner" type="text"
					readonly="readonly" value="${processAssets.assetOwner}" id="assetOwner">
					<input type="hidden" id="peopleValue" >
					<input id='gvdMeeting_btnClearsk_0' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="ClearObj();">
					<input id="showUserListBtn" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'></td>
			
		 	</tr>
		 <tr>
		 	<td class="td_right"><span class="span_color">*</span>组合资产责任人部门:</td>
	 		<td class="td_left">
				<input  style="width: 110px;" class="td_input" value="${processAssets.assetDepartmentName }" readonly="readonly" name="assetDepartmentName" type="text"
					readonly="readonly" id="assetDepartmentName"><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanDep();"><input id="btntxtYsssbmShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
					<input  name="assetDepartmentCode" type="hidden" id="assetDepartmentCode">
			</td>
			
			<td class="td_right"><span class="span_color">*</span>组合资产责任人归属公司:</td>
	 		<td class="td_left">
	 				<select id="company" style="width: 163px;" class="td_input" onchange="getCostCenter()">
	 					
	 				</select>
				<input class="td_input" type="hidden"  name="homeCompany" id="homeCompany" type="text" value="${processAssets.homeCompany}" readonly="readonly">
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
				<input id="platform" name="platform" class="td_input" type="text">
            </td>
			<td class="td_right"><span class="span_color">*</span>办公地点:</td>
	 		<td class="td_left">
				<select style="width: 163px;" class="td_input" name="officeLocation" id="officeLocation"  onchange="getOfficeLocation()">
					<option value="">请选择</option>
					<c:forEach items="${addresslist}" var="userInfo" varStatus="status">
					<option value="${userInfo.caddressName}">${userInfo.caddressName}</option>
					</c:forEach>
			    </select>
            </td>
			<td class="td_right"><span class="span_color">*</span>存放位置:</td>
	 		<td class="td_left">
				<select style="width: 163px;" class="td_input" name="storagePlace" id="storagePlace" >
					<option value="">请选择</option>
					<%-- <c:forEach items="${addresslist}" var="userInfo" varStatus="status">
					<option value="${userInfo.caddressName}">${userInfo.caddressName}</option>
					</c:forEach> --%>
			    </select>
				 
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
				
				<tbody id="tbody">
					
				</tbody>	
									
			</table>
		</div>
		<!-- <div style="width:99%;height:30px;margin-top: 10px;">
					<div style="width:100%; float:left;text-align: left;line-height: 30px;">
						<span style="text-align: right;background: #F6F6F6;" >净值合计&nbsp;&nbsp;&nbsp;</span>
						<input name="lblPurCode" id="netValue" type="text" value="" >
					</div>
					
					
					<div style="width:100%; float:left;text-align: left;line-height: 30px;">
						<span style="text-align: right;background: #F6F6F6;" >原归属公司</span>
						<input name="lblPurCode" type="text" value="" >
						<span style="text-align: right;background: #F6F6F6;" >原责任人</span>
						<input name="lblPurCode" id="personLiable" type="text" value="" >
					</div>
	
					<div style="width:100%; float:left;text-align: left;line-height: 30px;">
					<span style="text-align: right;background: #F6F6F6;" >原因说明&nbsp;&nbsp;&nbsp;</span>
						<input name="lblPurCode" id="cause" type="text" value="" >
					</div>
					
					<div style="width:100%; float:left;text-align: left;line-height: 30px;">
						<input readonly="readonly" type="text" value="财务写入SAP" style="text-align: right;color:red; background: #F6F6F6;width:80px;" />
						<input name="lblPurCode" type="button" value="写入SAP" >
					</div>
		</div> -->

 
 	<!-- 点击増行弹出form -->
	
	<div id="addApplyWindow" name="" class="easyui-window" title="添加申请" data-options="iconCls:'icon-save'" style="width:850px;height:440px;padding:0px;" closed="true">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'" style="padding:10px;">
	    
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
								<td align="right" class="tdtitle">成本中心:</td>
								<td class="tdtext"><input name="txtAssetsCenterCode" type="text"
									id="txtAssetsCenterCode" class="colorblur"></td>
								
							</tr>
							<tr>
								<td align="right" class="tdtitle">现责任人:</td>
								<td class="tdtext"><input name="txtAssetPerson" type="text"
									id="txtAssetPerson" class="colorblur"></td>
							<!-- 	<td align="right" class="tdtitle">管理编码:</td>
								<td class="tdtext"><input name="txtPutUnderManageCode" type="text"
									id="txtPutUnderManageCode" class="colorblur"></td> -->
									<td align="right" class="tdtitle">归属公司:</td>
								<td class="tdtext"><input name="persentCompany" type="text"
									id="persentCompany" class="colorblur"></td>
							</tr>
						</tbody>
					</table>

				</form>
	    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
	    	<input type="button" name="btnCancel" value="查询" id="inquiry" class="btn">
	        <input type="button" name="confirm" value="确定" onclick="confirm('radioFrame')" id="confirm" class="btn">
	   		 <span style="color: red;">温馨提示：查询前请填写资产编码或成本中心或归属公司</span>
	    </div>
	    
	    <div style="margin-top: 10px">
	        
	        
	        <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder">
	            <tbody>
	            
	            <tr>
	                <td colspan="4">
	                    <table id="Table10" width="100%"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
	                        <tbody>
	                        <tr>
	                            <td class="tdtitle" style="text-align: right" colspan="2">
	                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
	                                    <div>
	                                    	
	                                        <table id="table_add_detail" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
	                                            
	                                            <tr style="white-space:nowrap;">
	                                            <tr>
													<th><input type="checkbox" id="checkAllFrame"></th>
	                                                <th class='tdpadding' scope="col">资产编码</th>
	                                                <th class='tdpadding' scope="col">资产名称</th>

	                                                <th class='tdpadding' scope="col">现责任人</th>
	                                                <th class='tdpadding' scope="col">归属公司</th>
	                                              
	                                                <th class='tdpadding' scope="col">原值</th>
	                                                <th class='tdpadding' scope="col">净值</th>
	                                                
	                                                <th class='tdpadding' scope="col">使用状态</th>
	                                                <th class='tdpadding' scope="col">成本中心</th>
	                                               <!--  <th class='tdpadding' scope="col">存放位置</th> -->
	                                                
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
	<div style="height: 50px;margin-top: 10px;">
		<table>
			<tr>
				<td align="right" class="tdtitle"><span class="span_color">*</span>原值合计:</td>
				<td class="tdtext"><input name="originalValueSum" type="text"
					id="originalValueSum" class="colorblur" value="0" readonly="readonly"></td>
				<td align="right" class="tdtitle"><span class="span_color">*</span>净值合计:</td>
				<td class="tdtext"><input name="assetNetValueSum" type="text"
					id="assetNetValueSum" class="colorblur" value="0" readonly="readonly"></td>
				<td align="right" class="tdtitle"><span class="span_color">*</span>原因说明:</td>
				<td class="tdtext"><input  type="text"
					id="cause" class="colorblur"></td>
								
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
	<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
	<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
	<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		var dg = frameElement.lhgDG;
		
		var bianMa = "";
		var company = "";
		var v=0;
		var s = "";
		var array = new Array();
		var companyArray = new Array();
	
	 //增行弹窗模糊查询
	$("#inquiry").click(function(){
		$("#checkAllFrame").removeAttr("checked");
		//资产名称
		var txtAssetsName = $("#txtAssetsName").val();
		//资产编码
		var txtAssetsCode = $("#txtAssetsCode").val();
		//现责任人
		var txtAssetPerson = $("#txtAssetPerson").val();
	
		var txtAssetsCenterCode=$("#txtAssetsCenterCode").val();
		/* //管理编码
		var txtPutUnderManageCode = $("#txtPutUnderManageCode").val(); */
		//归属公司
		var persentCompany = $("#persentCompany").val();
		if(txtAssetsCode==""&&persentCompany==""&&txtAssetsCenterCode==""){
			alert("请填写资产编码或成本中心或归属公司");
			return false;
		}
		if(txtAssetsName!=''||txtAssetsCode!=''||txtAssetPerson!=''||txtAssetsCenterCode!=''||persentCompany!=''){
			searchGeneralAsset(txtAssetsName,txtAssetsCode,txtAssetPerson,txtAssetsCenterCode,persentCompany);	
		}
		
	}); 
	 
	function searchGeneralAsset(txtAssetsName,txtAssetsCode,txtAssetPerson,txtAssetsCenterCode,persentCompany){
		$("#tbody_add_detail").html();
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/assetCard/api/searchGeneralAsset",
            data:{txtAssetsName:txtAssetsName,txtAssetsCode:txtAssetsCode,txtAssetPerson:txtAssetPerson,costCenterCode:txtAssetsCenterCode,persentCompany:persentCompany,isAssembleOccupancy:0},
            success: function(data) {
				var divHtml="";
				for (var i = 0; i < data.length; i++) {  
					divHtml += "<tr align='center' value='"+data[i].id+"' id='tr"+data[i].id+"' class='bodyTr' style='white-space:nowrap;'>";
					divHtml += "<td id='k"+data[i].id+"' value='"+data[i].id+"' ><input type='checkbox' value='"+data[i].assetCode+","+data[i].id+","+data[i].companyCode+","+data[i].assetOriginalValue+","+data[i].assetNetValue+"' name='radioFrame' id='kuang"+data[i].id+"'></td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetCode+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetName+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetPerson+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].companyCode+"</td>";
					if(data[i].assetOriginalValue!=null){
						divHtml += "<td class='tdpadding'>"+data[i].assetOriginalValue+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+0+"</td>";
					}
					if(data[i].assetNetValue!=null){
						divHtml += "<td class='tdpadding'>"+data[i].assetNetValue+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+0+"</td>";
					}
					
					if(data[i].useState==null||data[i].useState==""){
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
					}else if(data[i].useState==7){
						divHtml += "<td class='tdpadding'>"+"已变卖"+"</td>";
					}else {
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					}
					divHtml += "<td class='tdpadding'>"+data[i].costCenterCode+"</td>";
					/* if(data[i].location!=null){
						divHtml += "<td class='tdpadding'>"+data[i].location+"</td>";
					}else{
						divHtml += "<td class='tdpadding'>"+""+"</td>";
					} */
				
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
	});
	
	//删行
	$("#opDelete").click(function(){
		$("input[name='radio']:checked").each(function(){
			var v = $(this).val().split(",")[0];
			array = null;
			companyArray = null;
			company = "";
			s = "";
			bianMa="";
			var tr = $(this).parent().parent();
			
			$("#tr"+v).remove();
		});
		$('#checkAll').attr("checked",false);
		sumAll();
	});
	var number = 0;
	var zhi = 0;
	//增行   确定
	function confirm(radioFrame){
		var arrayCheck = new Array(); //行明细的 资产编码+公司
		var flag = false;
		var errorMessage="";
		var yzSumBmAndGsbm="";
		array = bianMa.split(",");
		
		
		$("input[name='radio']").each(function(){
			var yzbmAndGsbm=$(this).val().split(",")[1]+ "["+$(this).val().split(",")[2]+"]";
			if(yzSumBmAndGsbm==""){
				yzSumBmAndGsbm=yzbmAndGsbm;
			}else{
				yzSumBmAndGsbm=yzSumBmAndGsbm+","+yzbmAndGsbm;
			}
		});
		arrayCheck = yzSumBmAndGsbm.split(",");
		
		var  num = 0;
		 $("input[name='radioFrame']:checked").each(function(){
		     var  idAndCode = $(this).val();
			 var  id = idAndCode.split(",")[1];
			
			 var  code=idAndCode.split(",")[0];
			 var companyCode=idAndCode.split(",")[2];
			 var originalValue=idAndCode.split(",")[3];
			 var assetNetValue=idAndCode.split(",")[4];
			var codeAndCompany = code + "["+companyCode+"]";
				
			for(var i =0;i<arrayCheck.length;i++){
				if(arrayCheck[i]==codeAndCompany){
					errorMessage = errorMessage+codeAndCompany + ",";
					
					flag = true;
					break;
				}
			}
			if(flag==true){
				return false;
			}
			 for(var i =0;i<array.length;i++){
				
					if(array[i]==code){
						
						s = s+code+",";
						num = 0;
						break;
					}
					else{
						num = 1;
					}
				}
		
			 if(num==1){
				 	number = number+1;
					var tr = $(this).parent().parent();
				
					$("#kuang"+id).remove();
					$("#k"+id).append("<input type='checkbox' value='"+id+","+code+","+companyCode+","+originalValue+","+assetNetValue+"' name='radio'/>");
					zhi += $("#kkk"+id).val()-0;
					$("#tbody").append(tr);
				 }
		}); 
		 if(flag){
			 alert("编码"+errorMessage+"已存在");
			 return false;
		 }
		 sumAll();
	/* 	 s=s.substring(0, s.length-1);
			if(s!=""){
				alert("编码"+s+"已存在");
				s = "";
				return;
			}
			else{
			
				alert("添加成功");
			    $('#checkAllFrame').attr("checked",false);
					
				//关闭窗口
			    $("#addApplyWindow").window('close');
			} */
	}
	
	//增行弹窗
	$("#showAddDialog").click(function(){
		$("#tbody_add_detail").html("");
    	var top = "10px";
    	var left = "70px";
    	$('#addApplyWindow').window('open').window('resize',{width:'850px',height:'440px',top: top,left:left});
    	
    });
	

	
	/*  function changeType() {
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
	//保存提交数据 */
	function submit(stat){
		$("#commitType").val(stat);
		company = company.substring(0,company.length-1);
		companyArray = company.split(",");
		companyArray.sort();
		var originalValueSum= $("#originalValueSum").val();
   		var assetNetValueSum= $("#assetNetValueSum").val();
   		var cause=$("#cause").val();
		for(var i =1;i<companyArray.length;i++){
			if(companyArray[i]!=companyArray[i-1]){
				alert("所选资产不为同一公司，请重新选择！");
				return;
			}
		}
		var date = $("#applicationDate").val();
		var sublist = "";
		$("input[name='radio']").each(function(){
			var id = $(this).val().split(",")[0];
			if(sublist == null && sublist== ""){
				sublist=id;
			}else{
				sublist=id+","+sublist;
			}	
		});
		  $.ajax({
            type: "POST",
            url: "${dynamicURL}/processAssets/api/saveProcessAssets?"+$("#formId").serialize(),
            data: {"processAssetsDetall":sublist,"date":date,"originalValueSum":originalValueSum,"assetNetValueSum":assetNetValueSum,"cause":cause},
            async:false,
            success: function(data) {
            	if (data.success) {
					alert("添加成功！");
					 dg.cancel(); 
	                 dg.curWin.location.reload();
				}else{
					alert(data.errorMessages.join());
					}
			}
              
              
            
        });
	}
	
	/* $("#conservation").click(function(){
	
		company = company.substring(0,company.length-1);
		
		companyArray = company.split(",");
		companyArray.sort();
		for(var i =1;i<companyArray.length;i++){
			if(companyArray[i]!=companyArray[i-1]){
				alert("所选资产不为同一公司，请重新选择！");
				return;
			}
		}
		var date = $("#applicationDate").val();
		var sublist = "";
		$("input[name='radio']").each(function(){
			var id = $(this).val();
			if(sublist == null && sublist== ""){
				sublist=id;
			}else{
				sublist=id+","+sublist;
			}
			
			
		});
		var netValue = $("#netValue").val();//净值合计
		var personLiable = $("#personLiable").val();//原责任人
		var cause = $("#cause").val();//原因
		  $.ajax({
            type: "POST",
            url: "${dynamicURL}/assetAllocation/api/saveApply?"+$("#formId").serialize(),
            data: {"sublist":sublist,"netValue":netValue,"personLiable":personLiable,"cause":cause,"date":date},
            async:false,
            success: function(data) {
            	if (data.success) {
					alert("添加成功！");
					 dg.cancel(); 
	                 dg.curWin.location.reload();
				}else{
					alert(data.errorMessages.join());
					}
			}
              
              
            
        });  
	}); */
	   //清空调拨后责任人把带出的也清空
	   function ClearObj(){
		   $("#assetOwner").val("");
		   $("#postAllocationDepartment").val("");
		   $("#postAllocationCostCenter").val("");
		   $("#homeCompany").val("");
		   $("#peopleValue").val("");
	   }
       
    //选择调拨后负责人
	   $("#showUserListBtn").click(function(){
			  showUserDialog();
		 });
	    
	   function showUserDialog(departmentCode){
		  var url = "${dynamicURL}/user/getUserListInAttachConfig?parentWindowId=application";
		  var parentWindow=dg.curWin; 
		  new parentWindow.$.dialog({ id: 'list', title: "所有人员", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
		   
	   }
	   
	   //根据办公地点查询调拨后存放位置
	   function getOfficeLocation(){
		   
		  var officeLocation = $('#officeLocation').val();
		  $.ajax({
	       		type:"post",
					url:"${dynamicURL}/userInfo/api/getCpsition",
					data: {workAddress:officeLocation},
					async:false,
				 	success: function(data) {
				 		
				 		$("#storagePlace").find("option").remove(); 
				 		$("#storagePlace").append("<option name='cpsition'  value=''>请选择</option>");
				 		for (var i = 0; i < data.length; i++) {  
				 			$("#storagePlace").append("<option name='cpsition'  value='"+data[i].cpsition+"'>"+data[i].cpsition+"</option>");
				 		}
				 		
				 	}
	       	});
	   }
	  	
	   $(document).ready(function(){  
		   getOfficeLocation();
	    });  

	   
	   function fillVehicleUser(userCode, userName) {
           $('#assetOwnerCode').val(userCode);
           $('#assetOwner').val("("+userCode+")"+userName);
          
           $.ajax({
       		type:"post",
				url:"${dynamicURL}/userInfo/api/searchRelevance",
				data: {code:userCode},
				async:false,
			 	success: function(data) {
			 	/* 	$('#postAllocationCostCenter').val(data.ccostcentername);
			 		$('#postAllocationDepartment').val(data.coppdepname); */
			 		$('#homeCompany').val("("+data.ccompanycode+")"+data.ccompanyname);
			 		$('#homeCompanyCode').val(data.ccompanycode);
			 		$("#costCenterCode").val(data.ccostcentercode);
			 		getComPlatform(data.ccostcentercode);
			 		getUserInfo(userCode);
			 	}
       	}); 

       };
/* 	   function fillCostCenter(depCode, depName,costCenterCode,costCenterName) {
		  alert("sss");
		   $("#postAllocationCostCenter").val("("+costCenterCode+")"+costCenterName);
		   $("#hideCostCenter").val(costCenterCode);
		   $("#departmentCode").val(depCode);
		   $("#subordinateDepartment").val(depName);
		   getCompany(depCode);
       }; */
	   //根据公司查平台
 	   function getComPlatform(costCenter){
	    	
		   $.ajax({
	       		type:"post",
					url:"${dynamicURL}/assetAllocation/api/getAttchConfig",
					//async : false,
					data: {costCenter:costCenter},
					async:false,
				 	success: function(data) {
				 		$("#platform").val(data.platform);
				 	}
	       	}); 
		
	   } 
     //预算所属部门 开始
    	$('#btntxtYsssbmShow').click(function(){
    		
    		 var top = "90px";//$(this).offset().top - 350;
    		var left = "120px";//$(this).offset().left-180;
    		if($("#assetOwner").val()==""){
    			alert("请先选择组合资产责任人！");
    			return;
    		}
    	/* 	$('#ysssbmWindow').window('open').window('resize',{width:'450px',height:'300px',top: top,left:left});

    		  $("#tree1").tree({
    		    	url: '${dynamicURL}/department/searchAllDepartmentTreeInit',
    		    	lines: true,
    		    	onClick: function(node) {
    		    		setTree(node.id, node.text);
    		     	}
    		    }); */
    		  
    		showDepCostCenter();
    	});
       function showDepCostCenter(){
       	
 		  var url = "${dynamicURL}/assetAllocation/searchPagerList?parentWindowId=application";
 		  var parentWindow=dg.curWin; 
 		  new parentWindow.$.dialog({ id: 'showDep', title: "所有部门", cover: true, page: url, width: 900, height: 450, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
 		   
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
    					html += "<option value=''>--请选择--</option>";
    				}
    				for (var i = 0; i < data.length; i++) {
    					html += "<option value="+data[i].ccompanycode+">("+data[i].ccompanycode+")"+data[i].ccompanyname+"</option>";
    				}
    				$("#company").html(html);
    				 getCostCenter(); 
    		   }
    	   });
       }
       function getCostCenter(){
    	 var comcode=  $("#company").val();
    	
		 var comname= $("#companyName option:selected").text();
		  $("#ccompanycode").val(comcode);
		  $("#postAllocationCompany").val(comname);
	
    	 var depcode=$("#assetDepartmentCode").val();
    	   
    	   $.ajax({
    		   type:"post",
    		   url:"${dynamicURL}/assetAllocation/api/getCostCenter",
    		   data:{companyCode:comcode,depcode:depcode},
    		   async:false,
    		   success:function(data){
    			   if(data.length!=0){
    				   $("#assetCostcenterName").val("("+data.ccostcentercode+")"+data.ccostcentername);
        			   $("#assetCostcenterCode").val(data.ccostcentercode);
   					}
    		   }
    	   });
    	   getComPlatform($("#assetCostcenterCode").val());
    	   getWorkShop($("#assetCostcenterCode").val());
    	  
		  /*  method(); */
       }
       
       function fillCostCenter(depCode, depName,costCenterCode,costCenterName) {
 		  
		   $("#assetCostcenterName").val("("+costCenterCode+")"+costCenterName);
		   $("#assetCostcenterCode").val(costCenterCode);
		   $("#assetDepartmentCode").val(depCode);
		   $("#assetDepartmentName").val(depName);
		   getCompany(depCode);
		   getComPlatform(costCenterCode);
		   getWorkShop(costCenterCode);
       }; 
       function getWorkShop(costCenterCode){
    		$.ajax({
					type:"post",
					url:"${dynamicURL}/assetAllocation/api/getAttchConfigWorkshop",
					data:{costCenter:costCenterCode,assetType:'3'},
					success:function(data){
						if (data.success) {
							/* $("#trWorkshop").show(); */
							$("#workshopType").val(data.result.workShopType);	
							
						}else{
							$("#workshopType").val("");	
						}
						
					}
				});
       }
     //全选按钮 
   	//全选按钮 
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
     function sumAll(){
    	 var originalValueSum=0;
		 var assetNetValueSum=0
   		 $("input[name='radio']").each(function(){
   			var ids = $(this).val();
   			var originalValue = ids.split(",")[3];
   			var assetNetValue = ids.split(",")[4];
   			if(originalValue!=""&&originalValue!="null"&&originalValue!=null){
   				originalValueSum += originalValue-0;
   			}
   			if(assetNetValue!=""&&assetNetValue!="null"&&assetNetValue!=null){
   				assetNetValueSum += assetNetValue-0;
   			}
   			
   		 });
   		 $("#originalValueSum").val(originalValueSum.toFixed(2));
   		 $("#assetNetValueSum").val(assetNetValueSum.toFixed(2));
     }
     function getUserInfo(userCode){
   	  
  	   $.ajax({
  		   type:"post",
  		   url:"${dynamicURL}/assetAllocation/api/getUserInfo",
  		   data:{userCode:userCode},
  		   async:false,
  		   success:function(data){
  			 
  			/*    $("#postAllocationCostCenter").val(data.result.ccostcentername);//调拨后成本中心
  			   $("#hideCostCenter").val(data.result.ccostcentercode);//调拨后成本中心编码 */
  			   $("#assetDepartmentName").val(data.result.cdepName);//调拨后部门名称
  			   $("#assetDepartmentCode").val(data.result.cdepCode);//调拨后部门编码
  			/*    $("#postAllocationCompany").val(data.result.ccompanyname);
  			   $("#ccompanycode").val(data.result.ccompanycode); */
  			
  			   getCompany(data.result.cdepCode);
  		   }
  	   });
     }
     //清空调拨后责任人把带出的也清空
	   function ClearObj(){
		   $("#assetOwner").val("");
		   $("#assetDepartmentName").val("");
		   $("#assetDepartmentCode").val("");
		   $("#company").html("");
		   $("#assetCostcenterName").val("");
		   $("#assetCostcenterCode").val("");
		   $("#platform").val("");
		   $("#workshopType").val("");
	   }
	   function cleanDep(){
	
		   $("#assetDepartmentName").val("");
		   $("#assetDepartmentCode").val("");
		   $("#company").html("");
		   $("#assetCostcenterName").val("");
		   $("#assetCostcenterCode").val("");
		   $("#platform").val("");
		   $("#workshopType").val("");
	   }
	</script>
</body>
</html>