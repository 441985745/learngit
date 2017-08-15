<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>调拨申请</title>
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
		<link rel="Stylesheet" type="text/css" href="${staticURL}/assetallocation/css/allocationapplicationapply.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
	</head>
	
<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
		        报废变卖单</div>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">

		<input type="button" value="返回" onclick="dg.cancel();"  class="btn">
		<input type="button" value="打印" onclick="discardSellStamp('${discardSell.id}')"  class="btn">
	</div>
	<app:approvalTag billCode="${discardSell.document}" typeId="77031"></app:approvalTag>
	<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
				<div class="imgtitle"></div>
						基本信息
				</div>
	<form id="discardForm">
	 <table style="table-layout: fixed;width:100%;">
			<tr>
					
				<td class="td_right"><span class="span_color">*</span>流程单据号:</td>
				<td class="td_left"><input name="document" type="text"
					value="${discardSell.document}" id="document" readonly="readonly" class="td_input" /> 
					<input type="hidden" name='userCode' value="${discardSell.userCode }">
					<input type="hidden" name="companyCode" value="${discardSell.companyCode }">
					<input type="hidden" name="departmentCode" value="${discardSell.departmentCode }">
					<input type="hidden" name="userName" value="${discardSell.userName }">
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属部门:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="departmentName" value="${discardSell.departmentName }" >
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属公司:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="adscriptionCompany" value="${discardSell.adscriptionCompany}" >
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
				<td class="td_left"><input class="td_input" value="${discardSell.officeLocation}" readonly="readonly"></td>
				
			</tr>
			<tr>
			    <td class="td_right">是否一次性费用化资产:</td>
				<td class="td_left">	
				
				<c:if test="${discardSell.isExpenseAssets=='0'}">
                    	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="否" id="lblCom">
	            </c:if>
	            <c:if test="${discardSell.isExpenseAssets=='1'}">
                    	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="是" id="lblCom">
	            </c:if>
			</td>
				<td class="td_right"><span class="span_color">*</span>资产所属部门:</td>
				<td class="td_left"><input style="width: 110px;" class="td_input" name="subordinateDepartment" type="text"
					readonly="readonly" value="${discardSell.subordinateDepartment}" id="subordinateDepartment"><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' ><input id="btntxtYsssbmShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'></td>
				<td class="td_right">资产归属公司:</td>
				<td class="td_left"><span id="spanCompany">
						<input readonly='readonly' class='td_input' name='adscriptionCompany' type='text' value='${discardSell.adscriptionCompany}' id='txtCompany'>
				</span></td>
					
			</tr>
			<tr>
				<td class="td_right">资产所属成本中心:</td>
			<td class="td_left"><span id="spanCostCenter">
							<input readonly='readonly' class='td_input' name='persentDepartment' type='text' value='${discardSell.costCenterName}' id='txtCompany'>
					</span></td>
			
				<td class="td_right"><span class="span_color">*</span>平台:</td>
				<td class="td_left"><input readonly="readonly" class="td_input" value="${discardSell.platform}"> </td>
				<td class="td_right">资产类型:</td>
				<td class="td_left">
					<c:if test="${discardSell.assetType=='0'}">
	                   	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="IT资产" id="lblCom">
		            </c:if>
		            <c:if test="${discardSell.assetType=='1'}">
	                   	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="行政资产" id="lblCom">
		            </c:if>
                 	<c:if test="${discardSell.assetType=='2'}">
	                   	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="计量器具" id="lblCom">
		            </c:if>
	                 <c:if test="${discardSell.assetType=='3'}">
	                   	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="机器设备" id="lblCom">
		            </c:if>
	            </td>
			</tr>
			<tr id="trWorkshop">
				<td class="td_right">车间类型:</td>
				<td class="td_left"> <input class="td_input" readonly="readonly" value="${discardSell.workshopType}"> </td>
				<td class="td_right">申请原因:</td>
				<td class="td_left"> <input class="td_input" readonly="readonly" value="${discardSell.application}"> </td>
			</tr>
		</table>
	 	</form>
	 	
	 		<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					资产信息
			</div>
	 	
			 <div style="overflow-x: scroll; width: 100%;">
			<table  border="0" cellpadding="3px" cellspacing="0" width="150%">
			
				<thead>
					<tr>		
								<th>资产编码</th>
								<th>资产名称</th>
								<th>资产类型</th>
							
								<th>现资产责任人</th>
							
								<th>原值</th>
								<th>净值</th>
								<th>折旧开始日期</th>
								<th>报废百分比</th>
								<th>报废金额</th>
								<th>报废凭证号</th>
								<th>归属公司</th>
								<th>成本中心</th>
								<th>现存放位置</th>
							
						</tr>
						
				</thead>
				
				
				<tbody>
				<c:forEach items="${discardSell.listDiscardSellDetail}" var="gneralAsset">
							<tr style="text-align: center;">
							<%-- 	<td ><input type="hidden"
										value="${gneralAsset.id}" name="radio"
										id="kuang${gneralAsset.id}" />
								</td> --%>
								
								<td >${gneralAsset.assetCode}</td>
								<td>${gneralAsset.assetName} </td>
								<td>
								<c:choose>
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
								</c:choose>
								</td>
								
								<td>${gneralAsset.assetPerson} </td>
								
								<td>${gneralAsset.assetOriginalValue} </td>
								<td>${gneralAsset.assetNetValue} </td>
								<td><fmt:formatDate value="${gneralAsset.depStartDate}"  pattern="yyyy-MM-dd"/></td>
								<td>${gneralAsset.discardPercent}</td>
								<td>${gneralAsset.discardMoney} </td>
								<td>${gneralAsset.scrapCertificate}</td>
								<td> ${gneralAsset.companyName}</td>
								<td> ${gneralAsset.costCenterCode} </td>
								<td> ${gneralAsset.location}</td>
								
						</tr>
				</c:forEach>
				</tbody>	
									
			</table>
		</div>
			<div>
		<table >
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
									     <c:forEach items="${discardSell.listFile}" var="assetPic" >
									   
										<tr>
										
											<td>${assetPic.fileName}</td>
											<td>${assetPic.createUserName}</td>
											<td>${assetPic.createDate}</td>
											<td><a href="${dynamicURL}/file/downloadFile?fileuuid=${assetPic.fileuuid}">下载</a></td>
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
	  	<div id="putManage" >
	 		<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					归口部门审核填写
			</div>
			<div>
				<table style="table-layout: fixed;width:100%;">
				
					<tr>
					<td class="td_right"><span class="span_color">*</span>报废类型:</td>
					<td class="td_left">
							
							<select disabled="disabled" style="height: 26px;width: 163px;" name="discardOrSell" id="discardOrSell">
								<option value="">请选择</option>
								<option value="1">报废</option>
								<option value="0">变卖</option>
							</select>
					</td>	
					<td class="td_right"><span class="span_color">*</span>归口建议:</td>
					<td class="td_left">
						<input type="text" name="putmanageIdea"  readonly="readonly" class="td_input" value="${discardSell.putmanageIdea}" id="putmanageIdea">
					</td>
					<td class="td_right"><span class="span_color">*</span>判断依据:</td>
					<td class="td_left">
						<input type="text" name="judgmentBasis"  readonly="readonly" value="${discardSell.judgmentBasis}" class="td_input" id="judgmentBasis">
					</td>
						
					</tr>
				</table>
			</div>
		</div>
		<!-- <div id="platformFinance" >
	 		<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					平台财务填写
			</div>
			<div>
				<table style="table-layout: fixed;width:100%;">
				<tr>
					<td class="td_right"><span class="span_color">*</span>报废类型:</td>
					<td class="td_left">
							<select disabled="disabled" style="height: 26px;width: 163px;" name="discardTypeFinance" id="discardTypeFinance">
								<option value="">请选择</option>
								<option value="1">报废</option>
								<option value="0">变卖</option>
							</select>
					</td>	
					
				</tr>
				</table>
			</div>
		 </div>  -->
 		<div id="purchasingManageSuper" >
	 		<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					采购经理填写
			</div>
			<div>
				<table >
						<tr>
				<!-- 	<td class="td_right"><span class="span_color">*</span>报废类型:</td>
					<td class="td_left">
							<select style="height: 26px;width: 163px;" disabled="disabled"  name="discardOrSell" id="discardOrSell">
								<option value="">请选择</option>
								<option value="1">报废</option>
								<option value="0">变卖</option>
							</select>
					</td>	 -->
					<td class="td_right"><span class="span_color">*</span>标底价:</td>
					<td class="td_left">
						<input type="text" name="basePrice" value="${discardSell.basePrice}"  readonly="readonly" class="td_input" id="basePrice">
					</td>
					<td class="td_right">判断依据:</td>
					<td class="td_left">
						<input type="text" name="purchasingJudgmentBasis" value="${discardSell.purchasingJudgmentBasis}"  readonly="readonly" class="td_input" id="purchasingJudgmentBasis">
					</td>
				</tr>
				</table>
			</div>
		 </div> 
		 
		 <div id="purchasingManage">
			<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					采购调研填写
			</div>
			<div >
				<table style="table-layout: fixed;width:100%;">
					<tr>
					<td class="td_right"><span class="span_color">*</span>供应商名称:</td>
					<td class="td_left">
						<input type="text" name="supplierName" value="${discardSell.supplierName}" readonly="readonly" class="td_input" id="supplierName">
					</td>
					<td class="td_right"><span class="span_color">*</span>变卖模式:</td>
					<td class="td_left">
						<input type="text" name="sellPattern" value="${discardSell.sellPattern}" readonly="readonly" class="td_input" id="sellPattern">
					</td>
					<td class="td_right"><span class="span_color">*</span>实际处置价:</td>
					<td class="td_left">
						<input type="text" name="actualDisposalPrice" value="${discardSell.actualDisposalPrice}" readonly="readonly" class="td_input" id="actualDisposalPrice">
					</td>
					</tr>
				</table>
			</div>
			</div>
<!-- 			<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					调拨信息
			</div>
			<div style="overflow-x: scroll; width: 100%;">
			<table  border="0" cellpadding="3px" cellspacing="0" width="150%">
				<thead>
						<tr>
							<th>资产编码</th>
							<th>资产名称</th>
							<th>归口管理编码</th>
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
				<tbody id="tbodyDB">
				</tbody>				
			</table>
		</div> -->
		<div>
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
												<!-- <th scope="col">审批金额</th> -->
												<th scope="col">审批意见</th>
											</tr>
											</thead>
											<tbody>
											<c:forEach items="${appHistoryPager.records}" var="appHistory" varStatus="status">
												<tr style="white-space: nowrap;">
													<td class="tdleft">${appHistory.examineUser}</td>
													<td class="tdleft"><fmt:formatDate value='${appHistory.examineDate}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
													<td class="tdleft">${appHistory.examineResult}</td>
													<%-- <td class="tdleft">${appHistory.examineMoney}</td> --%>
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
		<!-- <div class="titlefontsize" style="width:99%;height:30px;margin-top: 20px;">
			<div class="imgtitle"></div>
					历史维修记录
		</div>
			
		<div style="overflow-x: scroll; width: 100%;">
			<table  border="0" cellpadding="3px" cellspacing="0" width="120%">
			
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
	<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>  
	<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
	<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
	<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		var dg = frameElement.lhgDG;
		getDiscardSell();
	
		var assetType="${discardSell.assetType}";
		if(assetType=="3"){
			$("#trWorkshop").show();
		}
		function getDiscardSell(){
			var assetIds="";
			 $("input[name='radio']").each(function(){
					var id=$(this).val();
					
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
		var lastApprove="${lastApprove}";
		var assetType="${discardSell.assetType}";
		
		if("${discardSell.discardType}"!=""){
			$("#discardType option[value='${discardSell.discardType}']").attr("selected", true);   
		}
		if('${discardSell.discardTypeFinance}'!=""){
			$("#discardTypeFinance option[value='${discardSell.discardTypeFinance}']").attr("selected", true); 
		}
		if('${discardSell.discardOrSell}'!=""){
			$("#discardOrSell option[value='${discardSell.discardOrSell}']").attr("selected", true); 
		}
        function discardSellStamp(id){
            var lurl = "${dynamicURL}/discardSell/discardSellStamp?id=" + id + "&typeId=77031";
            window.open(lurl);
        }

        function stamp(id){

            var lurl = "${dynamicURL}/demand/printOrder?id=" + id + "&typeId=77001";
            window.open(lurl);

        }
	</script>
</body>
</html>