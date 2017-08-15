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
		<input type="button"  value="返回" size="25" id="fanHui" class="btn">
		<input type="button" value="打印" onclick="showAllocationDialog('${assetAllocation.id}','打印');" class="btn">
	</div>
	<app:approvalTag billCode="${assetAllocation.document}" typeId="77002"></app:approvalTag>
	<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
	<div class="imgtitle"></div>
					基本信息
			</div>
	<div style="margin-top: 2px;">
	  <form id="discardForm">
	    <table style="table-layout: fixed;width:100%;">
	    	<tr>
				
				<td class="td_right"><span class="span_color">*</span>流程单据号:</td>
				<td class="td_left"><input name="document" type="text"
					value="${assetAllocation.document}" readonly="readonly" class="td_input" /> 
			
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
			<input name="txtApplyDate" class="td_input" readonly="readonly" type="text" value="${assetAllocation.postAllocationDepartment}" id="txtApplyDate"/> 
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
			<input name="lblCom" type="text" class="td_input" readonly="true" value="${assetAllocation.assetType}" id="lblCom">
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
	 	  <tr id="trWorkshop" >
	 		<td class="td_right">车间类型:</td>
			<td class="td_left">
				<input name="lblPurCode" class="td_input" readonly="readonly" type="text" value="${assetAllocation.workshopType}" >
			</td>
			<td class="td_right"><span class="span_color">*</span>退回意见:</td>
			<td class="td_left" colspan="2">
				<input id="backOpinion" class="td_input" type="text" readonly="readonly" value="${assetAllocation.backOpinion}">
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
									<th id="thShow">新资产编码</th>
									<th>资产编码</th>
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
									<!-- <th>存放位置</th> -->
									<th>历史记录</th>
							</tr>
							<c:forEach items="${assetAllocation.assetAllocationDetail}" var="assetAllocationDetail" varStatus="status">
							<tr style="text-align: center;">
								<td class="tdRemove">${assetAllocationDetail.newAssetCode}</td>
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
								
								<%-- <td>${assetAllocationDetail.location}</td> --%>
								<td><a href="#" onclick="generalAssetDetail('${assetAllocationDetail.generalAssetId }')">${assetAllocationDetail.assetCode}</a> </td>
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
			<table cellpadding="0" cellspacing="0" border="0"  style="width:950px;">
				<tr>
					   <td colspan="2">
						   <div class="divGrid">
							   <div>
								   <table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList" style="border-collapse:collapse;">
									   <thead>
									   <tr style="white-space:nowrap;">
									
										   <th scope="col"> 文件</th>
										   <th scope="col">上传人</th>
										   <th scope="col">上传时间</th>
										   <th scope="col">附件下载</th>
									   </tr>
									   </thead>
									   <tbody>
									   <tr class="noItems" align="center">
								       <c:forEach items="${assetAllocation.listFile}" var="assetPic" >
									   
										<tr>
											<td>${assetPic.fileName}</td>
											<td>${assetPic.createUserName}</td>
											<td>${assetPic.createDate}</td>
											<td><a href="${dynamicURL}/file/downloadFile?fileuuid=${assetPic.fileuuid}">下载</a></td>
										</tr>
										 
										</c:forEach>
									   </tr>
									   </tbody>
								   </table>
							   </div>
						   </div>
					   </td>
				   </tr>
			</table>
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
		</div>
		
	<%-- <script src="${staticURL}/js/jquery-1.11.3.min.js"></script> --%>
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
		var moeth="${assetAllocation.allocationMethod}"
			if(moeth!="公司间调拨"){
				$("#thShow").hide();
				$(".tdRemove").remove();
			}
		var assetType="${assetAllocation.assetType}";
		if(assetType=="3"){
			$("#trWorkshop").show();
		}
	});
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
            url: "${dynamicURL}/assetCard/api/searchGeneralAsset",
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
            url: "${dynamicURL}/assetCard/api/searchAssetCardInfo",
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
		var sublist = "";
		$("input[name='radio']:checked").each(function(){
			var v = $(this).val();
			sublist = sublist+v+",";
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

                alert("添加成功！");
                //关闭窗口
                dg.cancel();
                return;
            }
        }); 
	});
	function generalAssetDetail(id){
			var url = "${dynamicURL}/assetCard/assetCardDetail?id="+id;
			var parentWindow=dg.curWin; 
			new parentWindow.$.dialog({ id: 'list', title: "资产卡片查看", cover: true, page: url, width: 800, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog(); 
	}
	function showAllocationDialog(id){
		var lurl = "${dynamicURL}/assetAllocation/printOrder?id=" + id + "&typeId=77002";
		window.open(lurl);
	}
	</script>
</body>
</html>