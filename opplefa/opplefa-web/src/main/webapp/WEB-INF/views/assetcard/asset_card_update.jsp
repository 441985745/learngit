<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" type="text/css" href="${staticURL}/assetcard/css/assetcardadd.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
<title>Insert title here</title>
</head>
<body>
 <div>
 <form id="publicAssetForm">
 	 <div>
	 	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
		        资产卡片</div>
	</div>
	 <table class="table_head">
	 	<!-- <tr><td colspan="6" style="font-size: 15px;text-align: center;background-color: white;">资产卡片</td></tr> -->
		 <tr class="tr_color" >
			 <td colspan="6" style="border: 1px solid  #B5B5B5;" class="buttonbar">
				<input class="td_input" value="${generalAsset.systeManagementCode}" readonly="readonly"  type="hidden" id="systeManagementCode"  name="systeManagementCode" />
				<input name="assetType" value="${generalAsset.assetType }" type="hidden" id="changeType">
				<input name="id" value="${generalAsset.id}" type="hidden" >
				<input class="btn" type="button" onclick="submitUpdate()" value="确认修改">
				 <input class="btn" type="button" onclick="dg.cancel();" value="返回">
			 </td>
		 </tr>
		 <tr class="tr_color " >
			 <td colspan="6" class="buttonbar"  ><img class="title_img" src="${staticURL}/assetcard/img/title.png"> 基本信息</td>
		 </tr>
	 	<tr>
	 	
	 		<td class="td_right"><span>*</span>系统管理编号：<input type="hidden" id="assetTypeDiv" value="${generalAsset.assetType}"> </td>
	 		<td class="td_left ">${generalAsset.systeManagementCode}</td>
	 		<td class="td_right"><span>*</span>公司代码：</td>
	 		<td class="td_left ">${generalAsset.companyCode}</td>
	 		<td class="td_right"><span>*</span>资产编号：</td>
	 		<td class="td_left">${generalAsset.assetCode}</td>
	 		
	 		
	 	</tr>
	 		
	 	<tr>
	 		<td class="td_right"><span>*</span>资产名称：</td>
	 		<td class="td_left">
	 			${generalAsset.assetName}
	 		</td>
	 		<td class="td_right">原资产编码：</td>
	 		<td class="td_left"><input class="td_input" value="${generalAsset.oldAssetCode}" name="oldAssetCode" id="oldAssetCode"> </td>
 			<td class="td_right">原归属公司：</td>
			<td class="td_left">
			${generalAsset.oldHomeCompany}
			</td>
			
	 	</tr>
	 	<tr>
	 	<td class="td_right"><span>*</span>资产类型：</td>
	 		<td class="td_left" id="assetType">
	 		<c:choose>
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
			</c:choose>
	 		
	 		</td>
	 		<td class="td_right"><span>*</span>资产分类：</td>
			<td class="td_left">
			${generalAsset.assetClassification}
			</td>
			<td class="td_right" ><span>*</span>是否为一次性费用化资产：</td>
	 		<td class="td_left">${generalAsset.isExpenseAssets=="0"?"否":"是"}</td>
 			
	 	</tr>
	 	<tr>
	 		<td class="td_right">品牌/规格/型号：</td>
	 		<td class="td_left" ><input class="td_input" value="${generalAsset.specificationModel}" id="specificationModel" name="specificationModel"> </td>
 			<td class="td_right">选配件：</td>
			<td class="td_left">
			${generalAsset.selectAccessories}
			</td>
			<td class="td_right"><span>*</span>资产状态：</td>
	 		<td class="td_left">
	 		<c:choose>
	 				
		 			<c:when test="${generalAsset.assetState=='0'}">
		 				预验收
		 			</c:when>
	 				<c:when test="${generalAsset.assetState=='1'}">
		 				已验收
		 			</c:when>
	 		</c:choose>	 		
	 		</td>
 		
	 	</tr>
 	 	 <tr>
 	 	 	<td class="td_right" >采购到货日期：</td>
	 		<td class="td_left"  >
	 		<fmt:formatDate value="${generalAsset.purchaseArrivalDate}"  pattern="yyyy-MM-dd"/>
	 		</td>
	 	 	<td class="td_right">验收周期(日)：</td>
	 	 	<td class="td_left">${generalAsset.acceptanceCycle}</td>
 	 		<td class="td_right" >计划验收日期：</td>
			<td class="td_left">
				 <fmt:formatDate value="${generalAsset.dateOfAcceptance}"  pattern="yyyy-MM-dd"/>
			</td>
 			
	 	
	 	 </tr>
		<tr>
			<td class="td_right"><span>*</span>实际验收日期</td>
	 		<td class="td_left">
	 		<fmt:formatDate value="${generalAsset.actualDateAcceptance}"  pattern="yyyy-MM-dd"/>
	 		</td>
	 		<td class="td_right">质保周期（月）：</td>
	 		<td class="td_left">
		 		${generalAsset.warrantyPeriod }
	 		</td>
	 		<td class="td_right">质保到期日：</td>
		 		<td class="td_left" >
		 		 <fmt:formatDate value="${generalAsset.expirationDate}"  pattern="yyyy-MM-dd"/>
	 		</td>
		 	
		</tr>
	 	<tr>
	 		<td class="td_right"><span>*</span>入账日期：</td>
	 		<td class="td_left">
	 		<fmt:formatDate value="${generalAsset.postingDate}"  pattern="yyyy-MM-dd"/>
	 		</td>	
	 		<td class="td_right"><span>*</span>归属公司：</td>
	 		<td class="td_left"><c:if test="${!fn:contains(generalAsset.companyName, '(')}">(${generalAsset.companyCode})</c:if>${generalAsset.companyName}</td>
 			<td class="td_right"><span>*</span>平台：</td>
	 		<td class="td_left">${generalAsset.platform}</td>
	 		
	 		
		</tr>
		<tr>
			<td class="td_right"><span>*</span>成本中心：</td>
	 		<td class="td_left">(${generalAsset.costCenterCode})${generalAsset.persentDepartment}</td>
			<td class="td_right"><span>*</span>使用部门：</td>
	 		<td class="td_left">(${generalAsset.costCenterCode})${generalAsset.persentDepartment}</td>
	 		<td class="td_right"><span >*</span>管理责任人：</td>
	 		<td class="td_left" >
	 			(${generalAsset.assetPersonCode})${generalAsset.assetPerson}
	 		</td>
	 		
		</tr>
		<tr>
			<td class="td_right"><span>*</span>办公地点：</td>
	 		<td class="td_left">${generalAsset.officeLocation}</td>
	 		<td class="td_right">存放位置：</td>
	 		<td class="td_left">${generalAsset.location}</td>
	 		<td class="td_right"><span >*</span>使用状态：</td>
			<td class="td_left" >
	 			<select class="td_select" style="width: 165px;" name="useState" id="useState">
		 			<option value="0">在用</option>
		 			<option value="1">闲置中</option>
		 			<option value="2">大修理中</option>
		 			<option value="3">改造中</option>
		 			<option value="4">报废申请中</option>
		 			<option value="5">已报废</option>
		 			<option value="6">变卖申请中</option>
		 			<option value="7">已变卖</option>
	 			</select>
	 		</td>
		</tr>
		<tr>
			<td class="td_right"><span >*</span>单位：</td>
			<td class="td_left" colspan="6" >${generalAsset.unit}</td>
		</tr>
		<tr>
		 	<td class="td_right"><span>*</span> 资产图片上传：</td>
		 	<td class="td_left" colspan="6"> 
		 	 <input type="file" name="multipartFile" id="fileToUpload" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
		 	<input type="button" name="btnUpload" value="上传" id="btnUploadCP" class="btn">
			</td>
	 	</tr>
		<tr>
	 		<td class="td_right"><span>*</span> 资产图片：</td>
	  		 <td colspan="6" class="td_left">
			   <div class="divGrid">
					   <div>
						   <table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList" style="border-collapse:collapse;">
							   <thead>
							   <tr style="white-space:nowrap;">
								 
								   <th scope="col"> 文件</th>
								   <th scope="col">上传人</th>
								   <th scope="col">上传时间</th>
								   <th scope="col">资产图片预览</th>
								   <th scope="col">下载</th>
							   </tr>
							   <c:forEach items="${generalAsset.listFile}" var="assetPic" >
							   <c:if test="${assetPic.typeName=='0'}">
								<tr>
									<td>${assetPic.fileName}</td>
									<td>${assetPic.createUserName}</td>
									<td>${assetPic.createDate}</td>
									<td align="center"><a href="javascript:void(0)" onclick='picPreview("${assetPic.fileuuid}")'>预览</a></td>	
									<td><a href= "${dynamicURL}/file/downloadFile?fileuuid=${assetPic.fileuuid}">下载</a></td>	
								</tr>
								 </c:if>
								</c:forEach>
							   </thead>
							   <tbody>
							   
							   </tbody>
						   </table>
					   </div>
				   </div>
			   </td>	  
		</tr>
		<tr>
	   		<td class="td_right">备注一：</td>
	 		<td class="td_left"><input class="td_input" name="remarkOne" value="${generalAsset.remarkOne}"> </td>
	   		<td class="td_right">备注二：</td>
	 		<td class="td_left"><input class="td_input" name="remarkTwo" value="${generalAsset.remarkTwo}"> </td>
	   		<td class="td_right">备注三：</td>
	 		<td class="td_left"><input class="td_input" name="remarkThree" value="${generalAsset.remarkThree}"> </td>
		</tr>
		<tr>
	 		<td class="td_right">备注四：</td>
	 		<td class="td_left" colspan="6"><input class="td_input" name="remark" value="${generalAsset.remark}"> </td>
	 	</tr>
	 	<tr class=" " >
				 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">价值信息</td>
			 </tr>
		 <tr>
 			<td class="td_right"><span>*</span>资产原值：</td>
	 		<td class="td_left">${generalAsset.assetOriginalValue}</td>
	 		<td class="td_right"><span>*</span>资产净值：</td> 
	 		<td class="td_left">${generalAsset.assetNetValue}</td>
			<td class="td_right"><span>*</span>折旧年限(月)：</td>
	 		<td class="td_left">${generalAsset.periodOfDepreciation}</td>
		 </tr>
	 	<tr>
	 		<td class="td_right"><span>*</span>折旧开始日期：</td>
	 		<td class="td_left"><fmt:formatDate value="${generalAsset.depreciationStartDate}"  pattern="yyyy-MM-dd"/></td>
	 		<td class="td_right"><span>*</span>月折旧额：</td>
	 		<td class="td_left">
	 		${generalAsset.monthCountingDepreciation}				
	 		</td>
	 		<td class="td_right"><span>*</span>本年已计提累计折旧：</td>
	 		<td class="td_left">${generalAsset.currentYearDepreciation}</td>
	 	</tr>
		<tr>
			<td class="td_right"><span>*</span>累计折旧：</td>
	 		<td class="td_left">
 				${generalAsset.addDepreciation}
	 		</td>
			<td class="td_right">处理价值：</td>
			<td class="td_left">
			${generalAsset.salvageValue}
			</td>
		</tr>
		 <tr class=" " >
			 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png"> 供应商信息</td>
		 </tr>
	 	 <tr>
	 		<td class="td_right">供应商名称：</td>
	 		<td class="td_left">
	 		<input class="td_input" name="supplierName" value="${generalAsset.supplierName}">
	 		</td>
	 		<td class="td_right">供应商联系人：</td>
	 		<td class="td_left"><input class="td_input" name="supplierLinkman" value="${generalAsset.supplierLinkman}"> </td>
	 			<td class="td_right">供应商联系电话：</td>
	 		<td class="td_left"><input class="td_input" name="supplierPhone" value="${generalAsset.supplierPhone}"> </td>
	 	</tr> 
	 	<tr>
	 		<td class="td_right">供应商编码：</td>
	 		<td class="td_left">${generalAsset.supplierCode}</td>
	 	</tr>
	 	</table>
	 	</div>
	</form>
	<form id="computerForm" >
	 <div id="computer" style="display:none;" class="table_head">
	 	<table> 
	 	 <tr class=" " >
				 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">附加信息</td>
		 </tr>
	 	<tr>
 			<td class="td_right">序列号：</td>
	 		<td class="td_left">
	 		<input class="td_input" value="${generalAsset.computerAsset.serialNumber}" name="serialNumber">
	 		<input name="id" value="${generalAsset.computerAsset.id}" type="hidden">
	 		 </td>
 			<td class="td_right">归口管理编码：</td>
	 		<td class="td_left"><input class="td_input" name="putUnderManageCode" value="${generalAsset.computerAsset.putUnderManageCode}"> </td>
	 		<td class="td_right">CPU:</td>
	 		<td class="td_left"><input class="td_input" name="cpu" value="${generalAsset.computerAsset.cpu}"> </td>
	 	</tr>
	 	<tr>
	 		<td class="td_right">内存：</td>
	 		<td class="td_left"><input class="td_input" name="internalMemory" value="${generalAsset.computerAsset.internalMemory}"> </td>
	 		<td class="td_right">硬盘：</td>
	 		<td class="td_left"><input class="td_input" name="hardpan" value="${generalAsset.computerAsset.hardpan}"> </td>
	 		<td class="td_right">IP地址：</td>
	 		<td class="td_left"><input class="td_input" name="ipAddress" value="${generalAsset.computerAsset.ipAddress}"> </td>


	 	</tr>
 		<tr>
	 		
	 		<td class="td_right">加入域：</td>
	 		<td class="td_left"><input class="td_input" name="joinDomain" value="${generalAsset.computerAsset.joinDomain}"> </td>
			<td class="td_right">操作系统：</td>
	 		<td class="td_left"><input class="td_input" name="operatingSystem" value="${generalAsset.computerAsset.operatingSystem}"> </td>
			<td class="td_right">应用系统：</td>
	 		<td class="td_left"><input class="td_input" name="utilitySystem" value="${generalAsset.computerAsset.utilitySystem}"> </td>
	 	</tr>
 		<tr>
	 		<td class="td_right">功能描述：</td>
	 		<td class="td_left"><input class="td_input" name="description" value="${generalAsset.computerAsset.description}"> </td>
	 		<td class="td_right">数据库：</td>
	 		<td class="td_left"><input class="td_input" name="dataBase" value="${generalAsset.computerAsset.dataBase}"> </td>
	 	</tr>
	 </table>
	</div>
	</form>
	
	<!-- 行政资产 -->
	<form id="administrativeForm" >
 	<div style="display:none;" id="administrative" class="table_head">
	 <table >
	  	<tr class="">
				 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">附加信息</td>
	 	<tr>
			<td class="td_right"><span>*</span>出厂编号/序列号：</td>
	 		<td class="td_left">
	 		<input class="td_input" name="serialNumber" value="${generalAsset.administrationAsset.serialNumber}"> 
	 		<input name="id" value="${generalAsset.administrationAsset.id}" type="hidden">
	 		</td>
 			<td class="td_right"><span>*</span>管理编码：</td>
	 		<td class="td_left"><input class="td_input"  name="managementCode" value="${generalAsset.administrationAsset.managementCode}"></td>
	 		<td class="td_right"><span>*</span>是否为房屋建筑：</td>
	 		<td class="td_left">
	 		<c:choose>
			   <c:when test="${generalAsset.administrationAsset.isBuildingContruction=='Y'}"> 
			    	是
			   </c:when>
			   <c:when test="${generalAsset.administrationAsset.isBuildingContruction=='N'}"> 
			    	否
			   </c:when>
			</c:choose>
	 		</td>	 	
	 	</tr>
	 </table>
	</div> 
</form>
<!-- 计量器具 -->
<form id="measuringForm">
	 <div  style="display:none;" id="measuring_instrument" class="table_head">
	 <table>
	 	<tr class=" " >
				 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">附加信息</td>
		</tr>
	 	<tr>
	 		<td class="td_right"><span>*</span>出厂编号：</td>
	 		<td class="td_left">
	 		${generalAsset.measuringInstruments.facrotyNumber}
	 		<input name="id" type="hidden" value="${generalAsset.measuringInstruments.id}">
	 		</td>
 			<td class="td_right"><span>*</span>管理编码：</td>
	 		 <td class="td_left">${generalAsset.measuringInstruments.managementCode}</td>
	 		<td class="td_right"><span>*</span>生产厂家：</td>
	 		<td class="td_left"><input class="td_input" name="manufactureFactory" value="${generalAsset.measuringInstruments.manufactureFactory}"> </td>
	 		
	 	</tr>

	 	 <tr class=" " >
			 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">校准信息</td>
		 </tr>
	 	<tr>
 			<td class="td_right"><span>*</span><span>*</span> 管理级别：</td>
		<td class="td_left"><select name="managementLevel" id="managementLevel" style="width: 163px;height: 24px;">
	 				<option value="A">A</option>
	 				<option value="B">B</option>
	 				<option value="C">C</option>
	 			</select></td>
	 		<td class="td_right">精度等级：</td>
	 		<td class="td_left"><input class="td_input" name="accuracyClass" id="accuracyClass" value="${generalAsset.measuringInstruments.accuracyClass}"> </td>
			<td class="td_right"><span>*</span> 检定周期(日)：</td>
	 		<td class="td_left"><input class="td_input" name="verificationCycle" id="verificationCycle" value="${generalAsset.measuringInstruments.verificationCycle}"> </td>

	 	</tr>
		 <tr>
	 	
	 		<td class="td_right">校准日期：</td>
	 		<td class="td_left">
	 		<input class="td_input Wdate" style="width: 161px;" id="calibrationDate" onClick="WdatePicker({skin:'ext'});" name="calibrationDate" value="<fmt:formatDate value="${generalAsset.measuringInstruments.calibrationDate}"  pattern="yyyy-MM-dd"/>">

	 		</td>
	 		<td class="td_right">下次校准日期：</td>
	 		<td class="td_left">
	 	
	 		<input class="td_input Wdate" style="width: 161px;" onClick="WdatePicker({skin:'ext'});" name="nextCalibrationDate" id="nextCalibrationDate" value="<fmt:formatDate value="${generalAsset.measuringInstruments.nextCalibrationDate}"  pattern="yyyy-MM-dd"/>">
			</td>
			<td class="td_right">校准单位：</td>
	 		<td class="td_left">
	 		<input class="td_input" name="calibrationUnit" id="calibrationUnit" value="${generalAsset.measuringInstruments.calibrationUnit}">
	 		</td>
	 	</tr>
 	
	 	<tr>
	 	<td class="td_right"><span>*</span> 是否有第三方校验报告：</td>
	 		<td class="td_left">${generalAsset.measuringInstruments.isThirdReport}</td>
	 	</tr>
	 	<tr id="tr_isThirdReport"> 
	 	
		 	<td class="td_right"><span>*</span> 第三方检验报告上传：</td>
		 	<td class="td_left" colspan="6"> 
		 	 <input type="file" name="multipartFile" id="fileToUploadJYBG" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
		 	<input type="button" name="btnUpload" value="上传" id="btnUploadJYBG" class="btn">
			</td>
		  
	 	</tr>
	 	<tr>
	 		<td class="td_right"><span>*</span> 第三方检验报告：</td>
	  		 <td colspan="6" class="td_left">
			   <div class="divGrid">
					   <div>
						   <table id="uploadTableJYBG" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList" style="border-collapse:collapse;">
							   <thead>
							   <tr style="white-space:nowrap;">
								 
								   <th scope="col"> 文件</th>
								   <th scope="col">上传人</th>
								   <th scope="col">上传时间</th>
								   <th scope="col">资产图片预览</th>
							   </tr>
							   </thead>
							   <tbody>
						      <c:forEach items="${generalAsset.listFile}" var="assetPic" >
							   <c:if test="${assetPic.typeName=='1'}">
								<tr>
									<td>${assetPic.fileName}</td>
									<td>${assetPic.createUserName}</td>
									<td>${assetPic.createDate}</td>
									<td><a href="javascript:void(0)" onclick='picPreview("${assetPic.fileuuid}")'>预览</a></td>	
								</tr>
								 </c:if>
								</c:forEach>
							   </tbody>
						   </table>
					   </div>
				   </div>
			   </td>
		   </tr>
	 </table>
	</div>
	</form>
	 <!-- 机器设备-->
	<form id="machineForm">
	 <div id="machine" style="display:none;" class="table_head">
	 <table>
		 <tr class=" " >
				 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">附加信息</td>
		</tr>
	 	<tr>
	 		<td class="td_right"><span>*</span>机器编码：</td>
	 		<td class="td_left">
	 		<input class="td_input" name="machineCode" value="${generalAsset.machineryEquipment.machineCode}">
	 		<input name="id" type="hidden" value="${generalAsset.machineryEquipment.id}">
	 		 </td>
	 		<td class="td_right">归口管理编码：</td>
	 		<td class="td_left"><input class="td_input"  name="putUnderManageCode" value="${generalAsset.machineryEquipment.putUnderManageCode}"> </td>
	 		<td class="td_right">车间类型：</td>
	 		<td class="td_left">${generalAsset.machineryEquipment.shopType}</td> 
	 	</tr>
	 </table>
	</div>
</form>
	</div>
	 <div style="overflow:scroll" >
	 	<table class="adminlist" id="gvDataListXQ">
	 		 <tr class="tr_color"  >
			 	<td   colspan="10" class="border_font buttonbar"  ><img class="title_img" src="${staticURL}/assetcard/img/title.png">相关流程</td>
			 </tr>
	
		 		<tr>
			 	 <td class="td_right">采购需求单号： </td>
		 		 <c:if test="${empty generalAsset.demandOrder}"><td  style="text-align: center;"> 没有记录!</td></c:if>
				 <c:if test="${!empty generalAsset.demandOrder}">
				 		<td class="td_left"><a href="#" onclick="demandOrderDetail('${generalAsset.demandOrder.cdocument}')">${generalAsset.demandOrder.cdocument}</a></td>	
			 	</c:if>
		
	 		<td class="td_right">采购申请单号：</td>
	 		<c:if test="${empty generalAsset.applyOrder}"><td style="text-align: center;"> 没有记录!</td></c:if>
			<c:if test="${!empty generalAsset.applyOrder}">
		 		<td><a href="#" onclick="applyOrderDetail('${generalAsset.applyOrder.cdocument}')">${generalAsset.applyOrder.cdocument}</a></td>
	
		 		</c:if>
		 	<td class="td_right">采购订单单号：</td>	
	 		<c:if test="${empty generalAsset.purchaseOrder}"><td  style="text-align: center;"> 没有记录!</td></c:if>
			<c:if test="${!empty generalAsset.purchaseOrder}">
		 		<td><a href="#" onclick="purchaseOrderDetail('${generalAsset.purchaseOrder.cdocument}')">${generalAsset.purchaseOrder.cdocument}</a></td>
		
		 		</c:if>
	 		</tr>
			
	 	</table>
	 </div>

	 <div style="overflow:scroll" >
	 	<table style="width:2100px;" class="adminlist" id="gvDataList">
	 		 <tr class="tr_color"  >
			 	<td   colspan="10" class="border_font buttonbar"  ><img class="title_img" src="${staticURL}/assetcard/img/title.png">相关调拨申请</td>
			 </tr>
	 		<tr>
	 				<th >序号</th>
				    <th >单据号</th>
					<th >申请人</th>
					<th >申请日期</th>
					<th >原归属公司</th>
					<th >调拨后责任人</th>
					<th >调拨后成本中心</th>
					<th >调拨后归属公司</th>
					<th >调拨后存放位置</th>
					<th >调配方式</th>
	 		</tr>
 		<c:if test="${empty generalAsset.assetAllocation}"><tr><td colspan="6" style="text-align: center;"> 没有记录!</td></tr></c:if>
 		<c:if test="${!empty generalAsset.assetAllocation}">
 		<c:forEach items="${generalAsset.assetAllocation}" var="assetAllocation" varStatus="status" >
 			<%-- <c:choose> --%>
			 <%--   <c:when test="${assetAllocation.id='null'}"> 
			    	电脑类    
			   </c:when> --%>
			   
	 		<tr style="text-align: center;">
			<td >${status.index+1}</td>
	 		<td>${assetAllocation.document}</td>
	 		<td>${assetAllocation.createBy}</td>
	 		<td><fmt:formatDate value="${assetAllocation.applicationDate}" pattern="yyyy-MM-dd"/></td>
	 		<td>${assetAllocation.originalCompany}</td>
	 		<td>${assetAllocation.postAllocationPeople}</td>
	 		<td>${assetAllocation.postAllocationCostCenter}</td>
	 		<td>${assetAllocation.postAllocationCompany}</td>
	 		<td>${assetAllocation.postAllocationStoragePlace}</td>
	 		<td>${assetAllocation.allocationMethod}</td>
	 			
	 		</tr>
<%-- 		</c:choose> --%>
 		</c:forEach>
 		</c:if>
	 	</table>
	 </div>
	 	 <div style="overflow:scroll" >
	 	<table style="width:1900px;"  class="adminlist" id="gvDataListBF">
	 		 <tr class="tr_color ">
			 	<td  colspan="10" class="border_font buttonbar"  ><img class="title_img" src="${staticURL}/assetcard/img/title.png">相关变卖/报废申请</td>
			 </tr>
	 		<tr>
	 				<th >序号</th>
				    <th >单据号</th>
					<th >申请人</th>
					<th >申请日期</th>
					<th >归属公司</th>
					<th >所属部门</th>
					<th >资产类型</th>
					<th >办公地点</th>
					<th >会计年度</th>
					<th >原值合计</th>
	 		</tr>
	 		<c:if test="${empty generalAsset.listDiscardSells}"><tr><td colspan="6" style="text-align: center;"> 没有记录!</td></tr></c:if>
	 		<c:if test="${!empty generalAsset.listDiscardSells}">
	 		<c:forEach items="${generalAsset.listDiscardSells}" var="listDiscardSell" varStatus="status">
	 		<tr style="text-align: center;" >
	 				<td >${status.index+1}</td>
					<td>${listDiscardSell.document }</td>
					<td>${listDiscardSell.createBy }</td>
					<td><fmt:formatDate value="${listDiscardSell.createDate }"  pattern="yyyy-MM-dd"/></td>
					<td>${listDiscardSell.adscriptionDepartment }</td>
					<td>${listDiscardSell.subordinateDepartment }</td>
					<td>${listDiscardSell.assetType}</td>
					<td>${listDiscardSell.officeLocation }</td>
					<td>${listDiscardSell.accountingYear }</td>
	 		</tr>
	 		</c:forEach>
	 		</c:if>
	 	</table>
	 </div>
	 	 <%-- <div style="overflow:scroll" >
	 	<table style="width:1600px;" class="adminlist" id="gvDataList">
	 		 <tr class="tr_color " >
			 	<td colspan="10"   class="border_font buttonbar"  ><img class="title_img" src="${staticURL}/assetcard/img/title.png">历史维修记录</td>
			 </tr>
	 		<tr >
	 				<th >序号</th>
				    <th >归口管理编码</th>
					<th >资产编码</th>
					<th >资产名称</th>
					<th >规格型号</th>
					<th >资产责任人</th>
					<th >申请维修日期</th>
					<th >故障原因</th>
					
	 		</tr>
	 		
	 		<tr style="text-align: center;">
	 		
	 			<td>1</td>
	 			<td>S232222</td>
	 			
	 			<td>未知</td>
	 			<td>admin</td>
	 			<td>2009-09-9</td>
	 			<td>坏了</td>

	 		</tr>
	 	
	 	</table>
	 </div> --%>
		<script src="${staticURL}/Scripts/JQuery/jquery-1.4.4.js"></script>
		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js" type="text/javascript" ></script>
		<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${staticURL}/js/ajaxfileupload.js" type="text/javascript"></script>
		<script src="${staticURL}/js/jquery.tableCheckbox.js"></script>
		<script src="${staticURL}/js/browser.js"></script>
 <script type="text/javascript" src="${staticURL}/Scripts/JSON-js-master/json2.js"></script>
	<script type="text/javascript">
	 GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
	 GridViewColor("gvDataListBF", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
	 GridViewColor("gvDataListCG", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
	 GridViewColor("gvDataListDD", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
	 GridViewColor("gvDataListXQ", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
		var dg = frameElement.lhgDG;//定义dg 关闭需要
		
		 $(document).ready(function(){
		/* 	var isAssetAccounting ="${isAssetAccounting}";
			var isManager ="${isManager}";
			
			if(isAssetAccounting!="Y"){
				$("#oldAssetCode").attr("readonly",true);
			}
			if(isManager!="Y"){
				$("#specificationModel").attr("readonly",true);
				$("#accuracyClass").attr("readonly",true);//精度等级
				$("#verificationCycle").attr("readonly",true);//检定周期
				$("#calibrationDate").attr("readonly",true);//校准日期
				$("#nextCalibrationDate").attr("readonly",true);
				$("#calibrationUnit").attr("readonly",true);
			}*/
			var typeValue=$("#assetTypeDiv").val(); 
			
			if(typeValue=='0'){
				
				$("#computer").show();
				$("#administrative").hide();
				$("#measuring_instrument").hide();
				$("#machine").hide();
			}
			if(typeValue=='1'){

				$("#computer").hide();
				$("#measuring_instrument").hide();
				$("#machine").hide();	
				$("#administrative").show();
			}
			if(typeValue=='2'){
				$("#computer").hide();
				$("#machine").hide();
				$("#administrative").hide();
				$("#measuring_instrument").show();
			}
			if(typeValue=='3'){
				$("#computer").hide();
				$("#administrative").hide();
				$("#measuring_instrument").hide();
				$("#machine").show();
			}
			$("#useState option[value='${generalAsset.useState}']").attr("selected",true);
			$("#managementLevel option[value='${generalAsset.measuringInstruments.managementLevel}']").attr("selected",true);
		 });
		 function submitUpdate(){

				var assetCardWrapper= {};
				//获得选中的类型
				var HiddenType=$("#changeType").val();
				
				//获得公共字段
				var generalAssetModel=$("#publicAssetForm").serializeObject();
				
				var computerAssetModel;
				var administrative;
				var measuringInstrumentsModel;
				var machineryEquipmentModel;
				if(HiddenType=='0'){
					var datComputerForm=$("#computerForm").serializeObject();
					computerAssetModel=datComputerForm; 
				}
				if(HiddenType=='1'){
					var datAdministrativeForm=$("#administrativeForm").serializeObject();
					administrative=datAdministrativeForm;
				}
				if(HiddenType=='2'){
					var datMeasuringForm=$("#measuringForm").serializeObject();
					measuringInstrumentsModel=datMeasuringForm;
				}
				if(HiddenType=='3'){
					var datMachineForm=$("#machineForm").serializeObject();
					machineryEquipmentModel=datMachineForm;
				}
				
				assetCardWrapper.generalAssetModel=generalAssetModel;
				assetCardWrapper.computerAssetModel=computerAssetModel;
				assetCardWrapper.administrativeModel=administrative;
				assetCardWrapper.measuringInstrumentsModel=measuringInstrumentsModel;
				assetCardWrapper.machineryEquipmentModel=machineryEquipmentModel;

				$.ajax({
			        type: "post",
			        url: "${dynamicURL}/assetCard/api/updateAssetCard",
			        contentType: "application/json; charset=utf-8",
			        dataType: "json",
			        data:JSON.stringify(assetCardWrapper),
			        success:function(data){
			        	if (data.success) {
			        		alert("修改成功！");
			        		 //关闭窗口
			                 dg.cancel(); 
			                 dg.curWin.location.reload();
			        	}else{
			        		alert(data.errorMessages.join());
			        	}
			           
			          
			        }
			    }); 

			}
		 function picPreview(picUuid){
				var url = "${dynamicURL}/assetCard/showAssetPic?parentWindowId=addCard&uuid="+picUuid;
				var parentWindow=dg.curWin; 
				new parentWindow.$.dialog({ id: 'list', title: "预览图片", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
					   
				 
		}
		function demandOrderDetail(orderDecoment){
			var url = "${dynamicURL}/demand/showDemandOrder?id=" + orderDecoment+"&typeId=77071";
			var parentWindow=dg.curWin; 
			new parentWindow.$.dialog({ id: 'list', title: "资产需求查看", cover: true, page: url, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
		}
		function applyOrderDetail(orderDecoment){
			var url = "${dynamicURL}/apply/showApplyOrder?id=" + orderDecoment+"&typeId=77071";
			var parentWindow=dg.curWin; 
			new parentWindow.$.dialog({ id: 'list', title: "采购申请查看", cover: true, page: url, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
		}
		function purchaseOrderDetail(orderDecoment){
			var url = "${dynamicURL}/purchase/showPurchaseOrder?id=" + orderDecoment;
			var parentWindow=dg.curWin; 
			new parentWindow.$.dialog({ id: 'list', title: "采购订单查看", cover: true, page: url, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
		}
		$('#btnUploadCP').click(function () {
		    var fileElementId =$(this).prev().attr('id');
		    var file = $('#fileToUpload').val();
		  	
		    if (file == "") {
		        alert("请选择需要上传的文件！");
		        return false;
		    }

		    var documentNo = $('#systeManagementCode').val();
		    if ("" == documentNo) {
		        alert('单据号不可为空');
		        return false;
		    }

		    uploadFileCard('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}',documentNo,$('#uploadTable > tbody'),fileElementId,"0");

		});
		$('#btnUploadJYBG').click(function () {
		    var fileElementId =$(this).prev().attr('id');
		    var file = $('#fileToUploadJYBG').val();
		   
		  	
		    if (file == "") {
		        alert("请选择需要上传的文件！");
		        return false;
		    }
		/*     var type=file.split(".")[1];
			if(type!="pdf"||type!="gif"){
				alert("请上传pdf或者gif格式的图片！");
				return false;
			} */
		    var systeManagementCode = $('#systeManagementCode').val();
		    if ("" == systeManagementCode) {
		        alert('单据号不可为空');
		        return false;
		    }

		    uploadFileJYBG('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}',systeManagementCode,$('#uploadTableJYBG > tbody'),fileElementId,"1");

		});
		function uploadFileCard(dynamicURL, userName, userCode, documentNo, $tableBody, fileElementId,typeName) {
			 var fileName = $("#fileToUpload").val();
			 
			 var flag=/[^\s]+\.(pdf|jgf)/i;
			 var bool=flag.test(fileName);
		 if(bool==true){

			$.ajaxFileUpload({
						url : dynamicURL + '/file/uploadFile',
						secureuri : false,
						fileElementId : fileElementId,
						dataType : 'text/html',
						data : {
							'createBy' : userCode,
							'createUserName' : userName,
							'document' : documentNo,
							'typeName':typeName
						},
						success : function(data) {
							data = JSON.parse(data.split("<")[0]);
							if (data.success) {
								
								$tableBody.find('.noItems').remove();
								$tableBody
										.append('<tr id="tr" class="'+data.result.fileuuid + '" style="white-space:nowrap;">'
												
												+ '</td><td scope="col">'
												+ data.result.fileName
												+ '</td><td scope="col">'
												+ data.result.createUserName
												+ '</td><td style="width:9%">'
												+ data.result.createDate
												+ '</td><td id="tdShowPic" align="center" style="width:10%"><a href="javascript:void(0)" onclick="picPreview(\''+data.result.fileuuid+'\')">预览</a>' 
												+ '</td><td scope="col"><a href="'+dynamicURL+'/file/downloadFile?fileuuid=' + data.result.fileuuid + '">下载</a></td></tr>');
								alert("文件上传成功!");
							} else {
								alert(data.errorMessages.join());
							}
						},
						error : function() {
							
						}
					});
		 }else{
			 alert("请上传PDF或者JGF格式的图片");
		 }
		}
		function uploadFileJYBG(dynamicURL, userName, userCode, systeManagementCode, $tableBody, fileElementId,typeName) {
			 var fileName = $("#fileToUploadJYBG").val();
			 $("#btnUploadJYBG").attr("disabled",true);
			 $("#btnUploadJYBG").val("上传ing");
			 var flag=/[^\s]+\.(pdf|jgf)/i;
			 var bool=flag.test(fileName);
			 if(bool==true){
				 $.ajaxFileUpload({
						url : dynamicURL + '/file/uploadFile',
						secureuri : false,
						fileElementId : fileElementId,
						dataType : 'text/html',
						data : {
							'createBy' : userCode,
							'createUserName' : userName,
							'document' : systeManagementCode,
							'typeName':typeName
						},
						success : function(data) {
							data = JSON.parse(data.split("<")[0]);
							if (data.success) {
								
								$tableBody.find('.noItems').remove();
								$tableBody
										.append('<tr id="tr" class="'+data.result.fileuuid + '" style="white-space:nowrap;">'
												+ '<td ><input type="checkbox" uuid="' + data.result.fileuuid + '" name=documentId />'
												+ '</td><td scope="col">'
												+ data.result.fileName
												+ '</td><td scope="col">'
												+ data.result.createUserName
												+ '</td><td style="width:9%">'
												+ data.result.createDate
												+ '</td><td id="tdShowPic" align="center" style="width:10%"><a href="javascript:void(0)" onclick="picPreview(\''+data.result.fileuuid+'\')">预览</a>' + '</td></tr>');
								alert("文件上传成功!");
								 $("#btnUploadJYBG").attr("disabled",false);
								 $("#btnUploadJYBG").val("上传");
							} else {
								alert(data.errorMessages.join());
								 $("#btnUploadJYBG").attr("disabled",false);
								 $("#btnUploadJYBG").val("上传");
							}
						},
						error : function() {
							
							 $("#btnUploadJYBG").attr("disabled",false);
							 $("#btnUploadJYBG").val("上传");
						}
					});
			 }else{
				alert("请上传PDF或者JGF格式的图片");
				 $("#btnUploadJYBG").attr("disabled",false);
				 $("#btnUploadJYBG").val("上传");
			 }

		}
		</script>
</body>
</html>