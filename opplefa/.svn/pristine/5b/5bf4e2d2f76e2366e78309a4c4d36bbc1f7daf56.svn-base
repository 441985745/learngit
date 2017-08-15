<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>调拨申请</title>
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="Stylesheet" type="text/css" href="${staticURL}/assetcard/css/addassetcard.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
	</head>
	
<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div style="height: 25px; font-size: 15px; font-weight: bold;text-align:center; vertical-align: middle">
						固定资产调拨
					</div>
		<div></div>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<input type="submit" name="" value="提交" onclick="" id="" class="btn">
		<input type="button" name="" value="保存"  id="conservation" class="btn">
		<input type="button" name="" value="返回" onclick="" id="fanHui" class="btn">
	</div>
	<div style="margin-top: 10px;">
		
	<div >
	 <table>
		 <tr class=" " >
			 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png"> 基本信息</td>
		 </tr>
	 	<tr>
	 	
	 		<td class="td_right">申请人:</td>
	 		<td class="td_left">
				<input name="applyPeople" type="text" value="${assetAllocation.userName}" readonly="readonly" class="td_input"/>
                <input name="" type="text" value="${sessionScope._user_name}" class="controlhidden" />
			</td>
			<td class="td_right">申请日期:</td>
			<td class="td_left">
				<input class="td_input"  name="" type="text" readonly="readonly" value="${assetAllocation.applicationDate}" id="applicationDate">
			</td>
	 		<td class="td_right">调拨后责任人:</td>
	 		<td class="td_left">
				<select style="width: 173px;" class="td_select select_color"  name="postAllocationPeople"  id=""> 
					<option class="option_color" value="陈先顺">陈先顺</option> 
					<option class="option_color" value="随时帅">随时帅</option> 
					<option class="option_color" value="装亨利">装亨利</option> 
					<option class="option_color" value="奥巴马">奥巴马</option> 
					<option class="option_color" value="特朗普">特朗普</option> 
				</select>
			</td>
	 	</tr>
	 	<tr>
	 		<td class="td_right">调拨后责任人部门:</td>
	 		<td class="td_left">
				<input class="td_input"  name="postAllocationDepartment" type="text" value="" id=""/> 
			</td>
			<td class="td_right">是否费用化资产:</td>
	 		<td class="td_left">
				<select style="width: 173px;" class="td_select select_color"  name="isExpenseAssets"  id="isExpenseAssets"> 
					<option class="option_color" value="1">是</option>
					<option class="option_color" value="0">否</option> 
				</select>
			</td>
			<td class="td_right">调拨后成本中心:</td>
	 		<td class="td_left">
				<select style="width: 173px;" class="td_select select_color"  name="postAllocationCostCenter"  id=""> 
					<option class="option_color" value="成本中心1">成本中心1</option> 
					<option class="option_color" value="行政资产">成本中心3</option> 
					<option class="option_color" value="计量器具">成本中心2</option> 
					<option class="option_color" value="机器设备">成本中心4</option> 
					<option class="option_color" value="房屋建筑物">成本中心5</option> 
				</select>
			</td>
	 	</tr>
	 	<tr>
	 		<td class="td_right">资产类型:</td>
	 		<td class="td_left" >
	 			<input  name="assetType" type="hidden" id="assetHiddenType" >
				<select style="width: 173px;" class="td_select select_color" onchange="changeType()"  name="assetType"  id="assetType">
					<option value="">请选择</option>
					<option value="IT">IT资产</option>
					<option value="2">行政资产</option>
					<option value="3">计量器具</option>
					<option value="4">机器设备</option>
				</select>
			</td>
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
			<td class="td_right">调拨后归属公司:</td>
	 		<td class="td_left">
				<input class="td_input"  name="postAllocationCompany" type="text" value="" >
			</td>
	 	</tr>
	 	<tr>
	 		<td class="td_right">调配方式:</td>
	 		<td class="td_left">
				<select style="width: 173px;" class="td_select select_color"  name="allocationMethod"  id="">
					<option class="option_color" value="公司间调拨">公司间调拨</option>
					<option class="option_color" value="行政资产">部门间调拨</option>
					<option class="option_color" value="计量器具">个人间调拨</option>
				</select>
			</td>
	 		<td class="td_right">办公地点:</td>
	 		<td class="td_left">
				<select style="width: 173px;" class="td_select select_color" id="officeLocation" name="officeLocation"> 
					<option class="option_color" value="中山">中山</option> 
					<option class="option_color" value="上海">上海</option> 
					<option class="option_color" value="吴江">吴江</option> 
					<option class="option_color" value="办事处大区">办事处大区</option> 
					<option class="option_color" value="驻国外（迪拜）">驻国外（迪拜）</option>
				</select>
			</td>
			<td class="td_right">平台:</td>
			<td class="td_left">
				<select style="width: 173px;" class="td_select select_color" id="platform" name="platform"> 
					<option class="option_color" value="制造供应链">制造供应链</option> 
					<option class="option_color" value="职能">职能</option> 
					<option class="option_color" value="研发">研发</option> 
					<option class="option_color" value="内销">内销</option> 
					<option class="option_color" value="5">外销</option> 
				</select>
			</td>
	 		
	 	</tr>
	 	<tr>
	 		<td class="td_right">调拨后存放位置:</td>
	 		<td class="td_left">
				<select style="width: 173px;" class="td_select select_color" id="platform" name="postAllocationStoragePlace"> 
					<option class="option_color" value="存放位置1">存放位置1</option> 
					<option class="option_color" value="职能">存放位置2</option> 
					<option class="option_color" value="研发">存放位置3</option> 
					<option class="option_color" value="内销">存放位置4</option> 
					<option class="option_color" value="5">存放位置5</option>
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
	 	</tr>
	 	</table>
	
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
			<table  border="0" cellpadding="3px" cellspacing="0" width="120%">
			
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll"></th>
								<th>资产编码</th>
								<th>资产名称</th>
								<th>归口管理编码</th>
								<th>现责任人</th>
								<th>规格型号</th>
								<th>原值</th>
								<th>净值</th>
								<th>使用信息</th>
								<th>使用状态</th>
								<th>原部门和成本中心</th>
								<th>原存放位置</th>
								<th>历史记录</th>
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
		
	</div>
 
 	<!-- 点击増行弹出form -->
	
	<div id="addApplyWindow" name="" class="easyui-window" title="添加申请" data-options="iconCls:'icon-save'" style="width:850px;height:440px;padding:5px;" closed="true">
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
								<td class="tdtext"><input name="" type="text"
									id="" class="colorblur"></td>
								
							</tr>
							<tr>
								<td align="right" class="tdtitle">现责任人:</td>
								<td class="tdtext"><input name="txtAssetPerson" type="text"
									id="txtAssetPerson" class="colorblur"></td>
								<td align="right" class="tdtitle">管理编码:</td>
								<td class="tdtext"><input name="txtPutUnderManageCode" type="text"
									id="txtPutUnderManageCode" class="colorblur"></td>
							</tr>
						</tbody>
					</table>

				</form>
	    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
	    	<input type="button" name="btnCancel" value="查询" id="inquiry" class="btn">
	        <input type="button" name="confirm" value="确定" onclick="confirm('radioFrame')" id="confirm" class="btn">
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
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>资产编码</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>资产名称</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>归口管理编码</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>现责任人</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>规格参数</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>原值</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>净值</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>使用信息</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>使用状态</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>原部门和成本中心</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>原存放位置</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>历史记录</th>
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
	<script src="${staticURL}/js/jquery-1.11.3.min.js"></script>
	<%-- <script src="${staticURL}/js/allocationApplication.js"></script> --%>
	<script src="${staticURL}/js/jquery.tableCheckbox.js"></script>
	<script src="${staticURL}/js/browser.js"></script>
	<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
	<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script src="${staticURL}/Scripts/Purchase/DemandOrder/DemandOrder.js" type="text/javascript"></script>
	<script type="text/javascript">
		var dg = frameElement.lhgDG;
		
		var bianMa = "";
		var v=0;
		var s = "";
		var array = new Array();
	
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
		if(txtAssetsName!=''||txtAssetsCode!=''||txtAssetPerson!=''||txtPutUnderManageCode!=''){
			searchGeneralAsset(txtAssetsName,txtAssetsCode,txtAssetPerson,txtPutUnderManageCode);	
		}else{
			searchInfo();
		}
		
	}); 
	 
	function searchGeneralAsset(txtAssetsName,txtAssetsCode,txtAssetPerson,txtPutUnderManageCode){
		$("#tbody_add_detail").html();
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/assetCard/searchGeneralAsset",
            data:{txtAssetsName:txtAssetsName,txtAssetsCode:txtAssetsCode,txtAssetPerson:txtAssetPerson,txtPutUnderManageCode:txtPutUnderManageCode},
            success: function(data) {
				var divHtml="";
				for (var i = 0; i < data.length; i++) {  
					divHtml += "<tr align='center' value='"+data[i].id+"' id='tr"+data[i].id+"' class='bodyTr' style='white-space:nowrap;'>";
					divHtml += "<td id='k"+data[i].id+"' value='"+data[i].id+"' ><input type='checkbox' value='"+data[i].id+"' name='radioFrame' id='kuang"+data[i].id+"'></td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetCode+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetName+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].putUnderManageCode+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetPerson+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].specificationModel+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetOriginalValue+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetNetValue+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].usePeople+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].useState+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].useDepartment+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].persentStorageLocation+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].manufacturer+"</td>";
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
			var v = $(this).val();
			array = null;
			s = "";
			bianMa="";
			var tr = $(this).parent().parent();
			$("#netValue").val($("#netValue").val()-$("#kkk"+v).val());
			$("#tr"+v).remove();
		});
		$('#checkAll').attr("checked",false);
	});
	var number = 0;
	var zhi = 0;
	//增行   确定
	function confirm(radioFrame){
		
		array = bianMa.split(",");
		var  num = 0;
		 $("input[name='radioFrame']:checked").each(function(){
			 var  a = $(this).val();
			 for(var i =0;i<array.length;i++){
					if(array[i]==a){
						s = s+a+",";
						num = 0;
						break;
					}
					else{
						num = 1;
					}
				}
			 if(num==1&&s==""){
				 	number = number+1;
					var tr = $(this).parent().parent();
					bianMa = bianMa+a+",";
					$("#kuang"+a).remove();
					$("#k"+a).append("<input type='checkbox' value='"+a+"' name='radio'/>");
					zhi += $("#kkk"+a).val()-0;
					$("#tbody").append(tr);
				 }
		}); 
		 s=s.substring(0, s.length-1);
			if(s!=""){
				alert("编码"+s+"已存在");
				s = "";
				return;
			}
			else{
				var t = $("#netValue").val();
				var total = t*1+zhi*1;
				$("#netValue").val(total);
				zhi = 0;
				alert("添加成功");
				$('#checkAllFrame').attr("checked",false);
				
				//关闭窗口
		        $("#addApplyWindow").window('close');
			}
	}
	
	//增行弹窗
	$("#showAddDialog").click(function(){
    	var top = "10px";
    	var left = "70px";
    	$('#addApplyWindow').window('open').window('resize',{width:'850px',height:'440px',top: top,left:left});
    	searchInfo();
    });

	function searchInfo(){
		$("#tbody_add_detail").html();
		
		//是否费用资产化
		var isExpenseAssets = $("#isExpenseAssets").val();
		//资产类型
		var assetType = $("#assetHiddenType").val();
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/assetCard/searchAssetCardInfo",
            data:{isExpenseAssets:isExpenseAssets,assetType:assetType},
            success: function(data) {
				var divHtml="";
				for (var i = 0; i < data.length; i++) {  
					divHtml += "<tr align='center' value='"+data[i].id+"' id='tr"+data[i].id+"' class='bodyTr' style='white-space:nowrap;'>";
					divHtml += "<td id='k"+data[i].id+"' value='"+data[i].id+"' ><input type='checkbox' value='"+data[i].id+"' name='radioFrame' id='kuang"+data[i].id+"'></td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetCode+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetName+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].putUnderManageCode+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetPerson+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].specificationModel+"</td>";
					divHtml += "<td class='tdpadding'><input type='hidden' id='kkk"+data[i].id+"' value='"+data[i].assetNetValue+"'> "+data[i].assetOriginalValue+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].assetNetValue+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].usePeople+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].useState+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].useDepartment+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].persentStorageLocation+"</td>";
					divHtml += "<td class='tdpadding'>"+data[i].manufacturer+"</td>";
					divHtml += "</tr>";
				}
                $("#tbody_add_detail").html(divHtml);
            }
        });
    }
	
	
	//全选按钮 
	 $("#checkAll").click(function(){ 
		 if($(this).checked){
			 $("[name='radio']").removeAttr("checked");//取消全选 
		 }else{
			 $("input[name='radio']").prop("checked", $(this).prop("checked"));
		 }
		 
	}); 
	
	//增行弹窗全选按钮
	 $("#checkAllFrame").click(function(){ 
		 if($(this).checked){
			 $("[name='radioFrame']").removeAttr("checked");//取消全选 
		 }
		 else{
			 $("input[name='radioFrame']").prop("checked", $(this).prop("checked"));
		 }
		 
	}); 
	
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
	//保存提交数据
	$("#conservation").click(function(){
		var date = $("#applicationDate").val();
		var vl = "";
		$("input[name='radio']:checked").each(function(){
			var v = $(this).val();
			vl = vl+v+",";
		});
		var netValue = $("#netValue").val();//净值合计
		var personLiable = $("#personLiable").val();//原责任人
		var cause = $("#cause").val();//原因
		$.ajax({
            type: "POST",
            url: "${dynamicURL}/assetAllocation/saveApply?"+$("#formId").serialize(),
            data: {"sublist":vl,"netValue":netValue,"personLiable":personLiable,"cause":cause,"date":date},
            async:false,
            success: function(data) {

              
                alert("添加成功！");
                //关闭窗口
                dg.cancel();
                return;
            }
        }); 
	});

	</script>
</body>
</html>