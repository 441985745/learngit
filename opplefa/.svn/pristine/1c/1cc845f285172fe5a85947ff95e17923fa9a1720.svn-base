 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>调拨申请</title>
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
	</head>
	
<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div style="height: 25px; font-size: 15px; font-weight: bold;text-align: center; vertical-align: middle">
						查看
					</div>
		<div></div>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<input type="button"  value="返回" size="25" id="fanHui" class="btn">
	</div>
	<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
	<div class="imgtitle"></div>
					资产信息
			</div>
	<div style="margin-top: 2px;">
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
				<tbody>
				<tr>
					<td>
						<table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
							<tbody>							
							<tr>
								<td style="text-align: right; background: #F6F6F6;">申请人:</td>
								<td>
									<input name="txtDemandOrderId" type="text" readonly="true" value="${assetAllocation.applyPeople}" id="">
								</td>
								<td style="text-align: right; background: #F6F6F6;">申请日期:</td>
								<td>
								<input name="lblUser" type="text" readonly="true" value="${assetAllocation.applicationDate}" id="">
								</td>
							</tr>
							<tr>
								<td style="text-align: right; background: #F6F6F6;">原归属公司:</td>
								<td>
								<input name="txtApplyUser" readonly="true" type="text" value="${assetAllocation.originalCompany}" id="">
								</td>
								<td style="text-align: right; background: #F6F6F6;">调拨后责任人:</td>
								<td class="tdtext">
								<input name="lblManager" readonly="true" type="text" value="${assetAllocation.postAllocationPeople}" id="lblManager">
								</td>
								
							</tr>
							<tr>
								<td style="text-align: right;background: #F6F6F6;">调拨后责任人部门:</td>
								<td class="tdtext">
								<input name="txtApplyDate" readonly="true" type="text" value="${assetAllocation.postAllocationDepartment}" id="txtApplyDate"  pattern="yyyy-MM-dd"/> 
								</td>
								<td style="text-align: right;background: #F6F6F6;">是否费用化资产:</td>
								<td class="tdtext">
								<c:if test="${assetAllocation.isExpenseAssets=='0'}">
	                                    	<input name="lblCom" readonly="true" type="text" value="否" id="lblCom">
	                                    </c:if>
								<c:if test="${assetAllocation.isExpenseAssets=='1'}">
	                                    	<input name="lblCom" readonly="true" type="text" value="是" id="lblCom">
	                                    </c:if>
								</td>
								
							</tr>
							<tr>
								<td style="text-align: right;background: #F6F6F6;">调拨后成本中心:</td>
								<td class="tdtext">
								<input name="lblCom" type="text" readonly="true" value="${assetAllocation.postAllocationCostCenter}" id="lblCom">
								</td>
								<td style="text-align: right;background: #F6F6F6;">资产类型:</td>
								<td class="tdtext">
								<input name="lblCom" type="text" readonly="true" value="${assetAllocation.assetType}" id="lblCom">
								</td>
								
							</tr>
							<tr>
								<td style="text-align: right;background: #F6F6F6;">车间类型:</td>
								<td>
									<input name="lblPurCode" readonly="true" type="text" value="${assetAllocation.workshopType}" >
								</td>
								<td style="text-align: right;background: #F6F6F6;">调拨后归属公司:</td>
								<td>
									<input name="lblPurCode" readonly="true" type="text" value="${assetAllocation.postAllocationCompany}" >
								</td>
							
							</tr>
							<tr>
								<td style="text-align: right;background: #F6F6F6;">调配方式:</td>
								<td>
									<input name="lblPurCode" readonly="true" type="text" value="${assetAllocation.allocationMethod}" >
								</td>
								<td style="text-align: right;background: #F6F6F6;">办公地点:</td>
								<td>
									<input name="lblPurCode" readonly="true" type="text" value="${assetAllocation.officeLocation}" >
								</td>
							
							</tr>
							<tr>
								<td style="text-align: right;background: #F6F6F6;">平台:</td>
								<td>
									<input name="lblPurCode" readonly="true" type="text" value="${assetAllocation.platform}" >
								</td>
								<td style="text-align: right;background: #F6F6F6;">调拨后存放位置:</td>
								<td>
									<input name="lblPurCode" readonly="true" type="text" value="${assetAllocation.postAllocationStoragePlace}" >
								</td>
							</tr>
							</tbody>
						</table>
					</td>
				</tr> 
			</tbody>
		</table>
			<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					资产信息
			</div>
									
		<div style="overflow-x: scroll; width: 100%;">
			<table border="1" cellpadding="3px" cellspacing="0" width="120%">
					<tbody>
							
							<tr>
								<th><input type="checkbox" id="checkAll"></th>
									<th>序号</th>
									<th>资产编码</th>
									<th>资产名称</th>
									<th>归口管理编码</th>
									<th>规格型号</th>
									<th>原值</th>
									<th>净值</th>
									<th>使用信息</th>
									<th>使用状态</th>
									<th>原部门和成本中心</th>
									<th>原存放位置</th>
									<th>历史记录</th>
							</tr>
							<c:forEach items="${assetAllocation.generalAsset}" var="generalAsset" varStatus="status">
							<tr style="text-align: center;">
								<td><input type="checkbox" name="radio"></td>
								<td>3</td>
								<td>${generalAsset.assetCode}</td>
								<td>${generalAsset.assetName}</td>
								<td>${generalAsset.putUnderManageCode}</td>
								<td>${generalAsset.specificationModel}</td>
								<td>${generalAsset.assetOriginalValue}</td>
								<td>${generalAsset.assetNetValue}</td>
								<td>7</td>
								<td>${generalAsset.useState}</td>
								<td>${generalAsset.persentDepartment}</td>
								<td>8</td>
								<td>6</td>
							</tr>
							</c:forEach>
					</tbody>	
									
			</table>
		</div>
		<div style="width:99%;height:30px;margin-top: 10px;">
					
					<div style="width:100%; float:left;text-align: left;line-height: 30px;">
					 	<span style="text-align: right;color:red; background: #F6F6F6;width:80px;">财务写入SAP</span>
						<input name="lblPurCode" type="button" value="写入SAP" >
					</div>
		</div>
		
	</div>
	<script src="${staticURL}/js/jquery-1.11.3.min.js"></script>
	<%-- <script src="${staticURL}/js/assetallocation/allocationView.js"></script> --%>
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
	
	//更换增行条件
	$("#isExpenseAssets").change(function(){
		$("#tbody").html("");
		array = null;
		s = "";
		bianMa="";
	});
	$("#assetType").change(function(){
		$("#tbody").html("");
		array = null;
		s = "";
		bianMa="";
	});
	
	//删行
	$("#opDelete").click(function(){
		$("input[name='radio']:checked").each(function(){
			var v = $(this).val();
			array = null;
			s = "";
			bianMa="";
			var tr = $(this).parent().parent();
			$("#tr"+v).remove();
		});
		$('#checkAll').attr("checked",false);
	});
	var number = 0;
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
					$("#tbody").append(tr);
				 }
		}); 
		 s=s.substring(0, s.length-1);
			if(s!=""){
				alert(s+"已存在");
				s = "";
				return;
			}
			else{
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
		//原归属公司
		var originalCompany = $("#originalCompany").val();
		//是否费用资产化
		var isExpenseAssets = $("#isExpenseAssets").val();
		//资产类型
		var assetType = $("#assetType").val();
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/assetCard/searchAssetCardInfo",
            data:{originalCompany:originalCompany,isExpenseAssets:isExpenseAssets,assetType:assetType},
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