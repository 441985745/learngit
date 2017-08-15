<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link rel="Stylesheet" type="text/css" href="${staticURL}/assetcard/css/assetcardadd.css">
<link rel="Stylesheet" type="text/css" href="${staticURL}/Scripts/ExtJs/resources/css/ext-all.css"> 
 <link href="${staticURL}/Scripts/DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css"/>
  <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
<title>Insert title here</title>
</head>
<body>

 <form id="publicAssetForm">
	 <div >
	  	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
		        资产卡片</div>
	</div>
	 <table>
	 <!-- 	<tr><td colspan="6" style="font-size: 15px;text-align: center;background-color: white;">资产卡片</td></tr> -->
		 <tr class="tr_color" >
			 <td colspan="6" style="border: 1px solid  #B5B5B5;" class="buttonbar">
				 <input class="btn" type="button" value="提交" onclick="submitAsset(1);">
				 <!-- <input class="btn" style="text-align: center;" type="button" value="保存草稿" onclick="submitAsset(0);"> -->
				 <input class="btn" type="button" value="返回" onclick="dg.cancel();">
				 
				 <input name="differentiatePurchase" value="1" type="hidden">
			 </td>
		 </tr>
		 <tr class=" " >
			 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png"> 基本信息</td>
		 </tr>
	 	<tr>
	 		<td class="td_right"><span>*</span>系统管理编码：</td>
	 		<td class="td_left ">
	 		<input class="td_input" value="${generalAsset.systeManagementCode}" readonly="readonly"  type="text" id="systeManagementCode"  name="systeManagementCode" />
	 		<input type="hidden" id="costCenterCode" name="costCenterCode">
	 	<%-- 	<input type="hidden" id="document" name="document" value="${generalAsset.systeManagementCode}"> --%>
	 		</td>
	 		<td class="td_right"><span>*</span>公司代码</td>
	 		<td class="td_left">
	 		<select style="width: 163px;height: 24px;" name="companyCode" id="companyCode">
	 			<option value="">请选择</option>
	 			<c:forEach items="${listCompany}" var="listCompany" >
	 					<option value="${listCompany.ccompanycode}">${listCompany.ccompanycode}</option>
	 			</c:forEach>
	 		</select>
	 		</td>
	 		<td class="td_right"><span>*</span>资产编号：</td>
	 		<td class="td_left"><input class="td_input"  type="text"  id="assetCode" name="assetCode" /><input class="btn" type="button"  value="获取资产详情" id="btnGet" onclick="getSapCard()"/> </td>
	 		
	 		
	 	</tr>
	 	<tr>
	 		<td class="td_right"><span>*</span>资产名称：</td>
	 		<td class="td_left">
	 			<input class="td_input"  type="text"  name="assetName" id="assetName" readonly="readonly"/>
	 		</td>
 		 	<td class="td_right">原资产编号：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="oldAssetCode"/></td>
 			<td class="td_right">原归属公司：</td>
			<td class="td_left">
			<input class="td_input" name="oldHomeCompany" id="oldHomeCompany">
			</td>
			
	 	</tr>
	 	<tr>
	 		<td class="td_right"><span>*</span>资产类型：</td>
	 		<td class="td_left">
	 		<select  style="width: 163px;height: 24px;" name="assetType"  id="changeType" onchange="changeAssetType()" >
	 			<option  value="0">IT资产</option>
	 			<option value="1">行政资产</option>
	 			<option  value="2">计量器具</option>		
	 			<option  value="3">机器设备</option>
	 		</select>
	 		</td>
	 		<td class="td_right"><span>*</span>资产分类：</td>
			<td class="td_left">
			<input class="td_input" name="assetClassificationName"  id="assetClassName" readonly="readonly">
			<input name="assetClassification" id="assetClass" type="hidden">
			</td>
	 		<td class="td_right" ><span >*</span>是否为一次性费用化资产：</td>
	 		<td class="td_left"  >
	 			<input type="radio" value="1" disabled="disabled"/>是
	 			<input name="isExpenseAssets" type="radio" value="0" checked="checked"/>否
	 		</td>
	 		
	 	</tr>
	 	<tr>
	 		<td class="td_right">品牌/规格/型号：</td>
	 		<td class="td_left" ><input class="td_input" name="specificationModel"> </td>
	 		<td class="td_right">选配件：</td>
			<td class="td_left">
				<input class="td_input" name="selectAccessories">
			</td>
			<td class="td_right"><span>*</span>资产状态：</td>
	 		<td class="td_left">
	 		<select  style="width: 163px;height: 24px;" name="assetState">
	 			<option value="1">已验收</option>
	 		</select>
	 		</td>
	 		
	 	</tr>
	 	<tr>
	 		<td class="td_right" >采购到货日期：</td>
	 		<td class="td_left"  >
	 		<input class="td_input Wdate" style="width: 160px;height: 22px;" type="text" name="purchaseArrivalDate"  onClick="WdatePicker({skin:'ext'});"/>
	 		</td>
	 		<td class="td_right">验收周期(日)：</td>
 	 		<td class="td_left"><input class="td_input" name="acceptanceCycle"></td>
 	 		<td class="td_right" >计划验收日期：</td>
			<td class="td_left"><input class="td_input Wdate" style="width: 160px;height: 22px;" type="text" name="dateOfAcceptance"  onClick="WdatePicker({skin:'ext'});"/></td>
	 		
	 	</tr>
	 	<tr>
	 		<td class="td_right"><span>*</span>实际验收日期</td>
	 		<td class="td_left">
	 			<input class="td_input"  name="actualDateAcceptance"  id="actualDateAcceptance" readonly="readonly"/>
	 		</td>
	 		<td class="td_right">质保周期（月）：</td>
	 		<td class="td_left">
	 			<input class="td_input"  type="text"  name="warrantyPeriod"/>
	 		</td>
	 		<td class="td_right">质保到期日：</td>
	 		<td class="td_left" >
	 		<input class="td_input Wdate" style="width: 160px;height: 22px;" type="text" name="expirationDate"  onClick="WdatePicker({skin:'ext'});"/>
	 		</td>
 			
	 	</tr>
		<tr>
			<td class="td_right"><span>*</span>入账日期：</td>
	 		<td class="td_left">
 			<input class="td_input" type="text" readonly="readonly" name="postingDate" id="postingDate"/>
	 		</td>
			<td class="td_right"><span>*</span>公司名称：</td>
	 		<td class="td_left">
	 			<input name="companyName" id="persentCompany" readonly="readonly"  class="td_input">
	 		</td>
	 		<td class="td_right"><span>*</span>平台：</td>
	 		<td class="td_left">
	 			<input class="td_input" name="platform" id="platform" readonly="readonly">
	 		</td>
		</tr>
		<tr>
			<td class="td_right"><span>*</span>成本中心：</td>
	 		<td class="td_left"><input class="td_input" type="text" id="persentDepartment" readonly="readonly" name="persentDepartment"/></td>
			<td class="td_right"><span>*</span>使用部门：</td>
	 		<td class="td_left"><input class="td_input" id="useDepartment" readonly="readonly" type="text" name="useDepartment"/></td>
			<td class="td_right"><span >*</span>管理责任人：</td>
	 		<td class="td_left" >
	 		<input name="assetPersonCode" id="assetPersonCode" type="hidden">
	 		<input  class="td_input" readonly="readonly" name="assetPerson" id="assetPerson"> 
	 		</td>
	 		
		</tr>
		<tr>
			<td class="td_right"><span>*</span>办公地点：</td>
	 		<td class="td_left">
	 			<select name="officeLocation" style="width: 163px;height: 24px;" id="officeLocationDN" onchange="getOfficeLocation()">
	 				<c:forEach items="${userInfoList}" var="userInfo" varStatus="status">
	 				<option value="${userInfo.caddressName}">${userInfo.caddressName}</option>
	 				</c:forEach>
	 			</select>
	 		</td>
			<td class="td_right"><span>*</span>存放位置：</td>
	 		<td class="td_left">
 				<select id="persentStorageLocation" style="width: 163px;height: 24px;" name="location">
 				
 				</select>
	 		</td>
			<td class="td_right"><span >*</span>使用状态：</td>
	 		<td class="td_left" >
	 			<select style="width: 163px;height: 24px;" name="useState">
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
			<td class="td_left" colspan="6" >
				<input class="td_input" id="unit" name="unit" readonly="readonly">
				<input name="unitCode" id="unitCode" type="hidden">
			</td>
		</tr>
		<tr>
		 	<td class="td_right"><span>*</span> 资产图片上传：</td>
		 	<td class="td_left" colspan="6"> 
		 	 <input type="file" name="multipartFile" id="fileToUpload" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
		 	<input type="button" name="btnUpload" value="上传" id="btnUploadCP" class="btn">
			<input type="button" name="btnDelFile" value="删除" id="btnDelFileCP" class="btn"></td>
	 	</tr>
		<tr>
	 		<td class="td_right"><span>*</span> 资产图片：</td>
	  		 <td colspan="6" class="td_left">
			   <div class="divGrid">
					   <div>
						   <table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList" style="border-collapse:collapse;">
							   <thead>
							   <tr style="white-space:nowrap;">
								   <th scope="col">
									   <input   type="checkbox" name="gvDataList$ctl01$CheckAll" id="checkTrue" onclick="checkBoxXZ();">
								   </th>
								   <th scope="col">文件</th>
								   <th scope="col">上传人</th>
								   <th scope="col">上传时间</th>
								   <th scope="col">资产图片预览</th>
								   <th scope="col">下载</th>
							   </tr>
							   </thead>
							   <tbody  id="fileBody">
							   <tr class="noItems" align="center">
								   <td colspan="5">没有记录！</td>
							   </tr>
							   </tbody>
						   </table>
					   </div>
				   </div>
			   </td>
		   </tr>
			<tr>
		   		<td class="td_right">备注一：</td>
		 		<td class="td_left"><input class="td_input" name="remarkOne" type="text"/></td>
		   		<td class="td_right">备注二：</td>
		 		<td class="td_left"><input class="td_input" name="remarkTwo" type="text"/></td>
		   		<td class="td_right">备注三：</td>
		 		<td class="td_left"><input class="td_input" name="remarkThree" type="text"/></td>
			</tr>
		    
	 		<tr>
		 		<td class="td_right">备注四：</td>
		 		<td class="td_left" colspan="6"><input class="td_input" style="width: 800px;"  name="remark" type="text"/></td>
		 	</tr>
		 <tr class=" " >
				 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">价值信息</td>
		 </tr>
		<tr>
			<td class="td_right"><span>*</span>资产原值：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="assetOriginalValue" id="assetOriginalValue" readonly="readonly"/></td>
	 		<td class="td_right"><span>*</span>资产净值：</td> 
	 		<td class="td_left"><input class="td_input" type="text" name="assetNetValue" id="assetNetValue" readonly="readonly"/></td>
 			<td class="td_right"><span>*</span>折旧年限(月)：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="periodOfDepreciation"  id="periodOfDepreciation" readonly="readonly"/></td>
		</tr>
		 <tr>
			<td class="td_right"><span>*</span>折旧开始日期：</td>
	 		<td class="td_left">
	 		<input class="td_input" name="depreciationStartDate"  id="depreciationStartDate" readonly="readonly"/>
	 		</td>
			<td class="td_right"><span>*</span>月折旧额：</td>
	 		<td class="td_left">
 				<input class="td_input" type="text" name="monthCountingDepreciation" id="monthCountingDepreciation" readonly="readonly"/>
	 		</td>
 			<td class="td_right"><span>*</span>本年已计提累计折旧：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="currentYearDepreciation" id="currentYearDepreciation" readonly="readonly"/></td>
		 </tr>
		  <tr>
	 		<td class="td_right"><span>*</span>累计折旧：</td>
	 		<td class="td_left">
 				<input class="td_input" type="text" name="addDepreciation" id="addDepreciation" readonly="readonly"/>
	 		</td>
 			<td class="td_right">处理价值：</td>
	 		<td class="td_left"><input class="td_input " name="salvageValue"/></td>
	 	</tr> 
	 	 <tr class=" " >
			 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png"> 供应商信息</td>
		 </tr>
		
	 	 <tr>
	 		<td class="td_right">供应商名称：</td>
	 		<td class="td_left">
 				<input class="td_input" type="text" id="supplier" name="supplierName"/>
	 		</td>
	 		<td class="td_right">供应商联系人：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="supplierLinkman"/></td>
	 			<td class="td_right">供应商联系电话：</td>
	 		<td class="td_left"><input class="td_input " name="supplierPhone"/></td>
	 	</tr> 
		<tr>
			<td class="td_right">供应商编码：</td>
	 		<td class="td_left">
 				<input class="td_input" type="text" id="supplier" name="supplierCode"/>
			</td>
		</tr>
	 	</table>
	 	</div>
 	</form>
	<form id="computerForm">
	 <div id="computer">
	 	<table>
	 	<tr >
			 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">附加信息</td>
		 </tr>
	 	<tr>
	 		<td class="td_right">序列号：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="serialNumber" id="serialNumber"/></td>
	 		<td class="td_right">归口管理编码：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="putUnderManageCode"/></td>
	 		<td class="td_right">CPU:</td>
	 		<td class="td_left"><input class="td_input" type="text" name="cpu"/></td>

	 	</tr>
	 	<tr>
	 		<td class="td_right">内存：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="internalMemory"/></td>
 			<td class="td_right">硬盘：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="hardpan"/></td>
 			<td class="td_right">IP地址：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="ipAddress"/></td>
	 		
	 	</tr>
 	 	 <tr>
	 		<td class="td_right">加入域：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="joinDomain"/></td>
	 		<td class="td_right">操作系统：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="operatingSystem"/></td>
	 		<td class="td_right">应用系统：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="utilitySystem"/></td>
	 	</tr> 
	 	<tr>
 	 		<td class="td_right">功能描述：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="description"/></td>
	 		<td class="td_right">数据库：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="dataBase"/></td>
	 	</tr>
	 </table>
	</div>
	
	</form>
	<!-- 行政资产 -->
	<form id="administrativeForm">
	 <div style="display:none;height:;" id="administrative">
	 <table>
		 <tr class=" " >
				 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">附加信息</td>
		 </tr>
 		<tr>
	 		<td class="td_right"><span>*</span> 出厂编号/序列号：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="serialNumber" id="serialNumberXZ"/></td>
	 		<td class="td_right"><span>*</span>管理编号：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="managementCode" /></td>
	 		<td class="td_right"><span>*</span> 是否为房屋建筑：</td>
	 		<td class="td_left">
	 			<select name="isBuildingContruction" style="width: 163px;height: 24px;">
	 			<option value="Y">是</option>
	 			<option value="N">否</option>
	 			</select>
	 		</td>
	 	</tr>
	 </table>
	</div>
</form>
<!-- 计量器具 -->
<form id="measuringForm">
	 <div  style="display:none;" id="measuring_instrument">
	 <table>
		 <tr class=" " >
				 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">附加信息</td>
		 </tr>
	 	<tr>
	 		<td class="td_right"><span>*</span>出厂编号：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="facrotyNumber" id="serialNumberJL"/></td>
	 		<td class="td_right"><span>*</span> 管理编号：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="managementCode" /></td>
	 		<td class="td_right"><span>*</span> 生产厂家：</td>
	 		<td class="td_left">
	 		<input class="td_input" name="manufactureFactory"/>
	 		</td>
	 	</tr> 
	 	 <tr class=" " >
			 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">校准信息</td>
		 </tr>
		 	<tr>
 			<td class="td_right"><span>*</span> 管理级别：</td>
	 		<td class="td_left">
	 			<select name="managementLevel" style="width: 163px;height: 24px;">
	 				<option value="A">A</option>
	 				<option value="B">B</option>
	 				<option value="C">C</option>
	 			</select>
	 		</td>
 			<td class="td_right">精度等级：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="accuracyClass"/></td>
			<td class="td_right"><span>*</span> 检定周期(日)：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="verificationCycle"/></td>
	 	</tr>
		 <tr>
	 		<td class="td_right">校准日期：</td>
	 		<td class="td_left"><input class="td_input Wdate" style="width: 160px;height: 22px;" type="text" name="calibrationDate"  onClick="WdatePicker({skin:'ext'});"/></td>
	 		<td class="td_right">下次校准日期：</td>
	 		<td class="td_left"><input class="td_input Wdate" style="width: 160px;height: 22px;" type="text" name="nextCalibrationDate"  onClick="WdatePicker({skin:'ext'});"/></td>
			<td class="td_right">校准单位：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="calibrationUnit"/></td>
	 	</tr>
 	
	 	<tr>
	 	<td class="td_right"><span>*</span> 是否有第三方校验报告：</td>
	 		<td class="td_left">
	 		<input  type="radio" name="isThirdReport" value="0" checked="checked"/>是
	 		<input  type="radio" name="isThirdReport" value="1"/>否
	 		</td>
	 	</tr>
	 	
	 	<tr id="tr_isThirdReport"> 
	 	
		 	<td class="td_right"><span>*</span> 第三方检验报告上传：</td>
		 	<td class="td_left" colspan="6"> 
		 	 <input type="file" name="multipartFile" id="fileToUploadJYBG" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
		 	<input type="button" name="btnUpload" value="上传" id="btnUploadJYBG" class="btn">
			<input type="button" name="btnDelFile" value="删除" id="btnDelFileJYBG" class="btn"></td>
		  
	 	</tr>
	 	<tr id="tr_isThirdReportPic">
	 		<td class="td_right"><span>*</span> 第三方检验报告：</td>
	  		 <td colspan="6" class="td_left">
			   <div class="divGrid">
					   <div>
						   <table id="uploadTableJYBG" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList" style="border-collapse:collapse;">
							   <thead>
							   <tr style="white-space:nowrap;">
								   <th scope="col">
									   <input   type="checkbox" name="gvDataList$ctl01$CheckAll" id="checkTrue" onclick="checkBoxXZ();">
								   </th>
								   <th scope="col"> 文件</th>
								   <th scope="col">上传人</th>
								   <th scope="col">上传时间</th>
								   <th scope="col">资产图片预览</th>
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
		 
	 </table>
	</div>
	</form>
	 <!-- 机器设备-->
	 <form id="machineForm">
	 <div id="machine" style="display:none;">
	 <table>
	 	<tr class=" " >
				 <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">附加信息</td>
		</tr>
	 	<tr>
	 		<td class="td_right"><span>*</span>机器编码：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="machineCode" id="serialNumberJQ"/></td>
	 		<td class="td_right">归口管理编码：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="putUnderManageCode"/></td>
 			<td class="td_right">车间类型：</td>
	 		<td class="td_left"><input class="td_input" type="text" name="shopType"/></td>
	 	</tr>
	 </table>
	</div>
	</form>
 	
	<script src="${staticURL}/Scripts/JQuery/jquery-1.4.4.js" type="text/javascript"></script> 
	<script src="${staticURL}/js/ajaxfileupload.js" type="text/javascript"></script>
	<%--  <script src="${staticURL}/opple/Scripts/JSBase.js" type="text/javascript"></script>  --%>
	<script src="${staticURL}/opple/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		var dg = frameElement.lhgDG;//定义dg 关闭需要
  function  judgeCompanCode(){
	 var companyCode=$("#companyCode").val();
	 if(companyCode==""){
		 alert("请先选择公司编码");
	 } 
 } 
$("#assetCode").focus(function(){
	var assetCode=$("#companyCode").val();
	if(assetCode==""){
		alert("请选择公司编码！");
		$("#companyCode").focus();	
	}
});

function getSapCard(){
	 var companyCode=$("#companyCode").val();
	 var assetCode=$("#assetCode").val();
	 $("#btnGet").val("获取资产详情ing");
	 $("#btnGet").attr("disabled",true);
	 $.ajax({
	   		type:"POST",
	   		url:"${dynamicURL}/assetCard/api/getAssetBySapCode",
	   		data:{"companyCode":companyCode,"assetCode":assetCode},
	   		success:function(data){
	   			
	   			if(data.error==""){
   				 $("#assetClass").val(data.assetClass);//资产分类
   				 $("#assetClassName").val(data.assetClassName);
	   	   		 $("#postingDate").val(data.capDate);//购置日期/入账日期
	   	   		 $("#assetOriginalValue").val(data.currentApc);//资产原值
	   	   		 $("#assetNetValue").val(data.currentNbv);//资产净值
	   	   		 $("#assetName").val(data.descript);//资产名称
	   	   		 $("#persentDepartment").val(data.costCenterName);//成本中心
	   	   		 $("#costCenterCode").val(data.costcenter);//成本中心编码
	   	   		 $("#periodOfDepreciation").val( data.ulife);//折旧年限
	   	   		 $("#monthCountingDepreciation").val(data.monthlyDepreciation1);//月折旧额
	   	   		
	   	   		 $("#depreciationStartDate").val(data.deprBeginDate);//折旧开始日期
	   	   		 $("#currentYearDepreciation").val(data.deprCurrYear);//本年已计提累计折旧
	   	   		
	   	   		 $("#actualDateAcceptance").val(data.initialAcq);//实际验收日期 
	   	   		 $("#assetPersonCode").val(data.userCode);
	   	   		 $("#addDepreciation").val(data.accumDepr);//累计折旧
	   	   		 $("#assetPerson").val(data.userName);//管理责任人
	   	   		 $("#useDepartment").val(data.costCenterName);//使用部门
	   	   		 $("#useDepartmentCode").val(data.costcenter);//使用部门的编码
	   	   		 $("#persentCompany").val(data.companyName);//公司
	   	   		 $("#unit").val(data.unit);//单位名称
	   	   		 $("#unitCode").val(data.unitCode);//单位编码
	   	   		 $("#oldHomeCompany").val(data.tradeId);//原归属公司
	   	   		 $("#serialNumber").val(data.serialNo);//IT序列号
	   	   		 $("#serialNumberXZ").val(data.serialNo);//行政序列号
	   	  		 $("#serialNumberJL").val(data.serialNo);//计量序列号
	   	 		 $("#serialNumberJQ").val(data.serialNo);//机器序列号
	   	   		 getComPlatform(data.costcenter);
   	   			
		   	   	 $("#btnGet").val("获取资产详情");
		   		 $("#btnGet").attr("disabled",false);
	   			}else{
	   				alert(data.error);
	   			 $("#btnGet").val("获取资产详情");
		   		 $("#btnGet").attr("disabled",false);
	   			}
	   		}
	   });
}

//获得地点
$(function(){

	getOfficeLocation();

});

function ITChange(){
 	var ITValue=$("#ITValue option:selected").val();
 	if(ITValue=='0'){
 		
 		$("#computer").show();
		$("#administrative").hide();
		$("#measuring_instrument").hide();
		$("#machine").hide();
 	}
	if(ITValue=='1'){
		$("#administrative").show();
		$("#measuring_instrument").hide();
		$("#machine").hide();
	}
}
function changeAssetType(){
	
	var typeValue=$("#changeType option:selected").val();
	
	if(typeValue=='0'){

		$("#computer").show();
		$("#administrative").hide();
		$("#no_computer").hide();
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

}
function submitAsset(status){
	
	var fileName =$("#fileBody").text();
	var isThirdReport=$("input[name=isThirdReport]:checked").val();
	
	if(fileName=="没有记录！"){
		alert("请上传图片");
	}else{
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
        url: "${dynamicURL}/assetCard/api/insertAssetCard",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(assetCardWrapper),
        success:function(data){
        	if (data.success) {
        		alert("添加成功！");
        		 //关闭窗口
                 dg.cancel(); 
                 dg.curWin.location.reload();
        	}else{
        		alert(data.errorMessages.join());
        	}
           
          
        }
    }); 
	}
}
//
function getOfficeLocation(){
	var type=$("#HiddenType").val();
	 var officeLocation='';
	  
		officeLocation = $('#officeLocationDN').val();
	  
	  $.ajax({
    		type:"post",
				url:"${dynamicURL}/userInfo/api/getCpsition",
				data: {workAddress:officeLocation},
				async:false,
			 	success: function(data) {
			 		
			 		$("#persentStorageLocation").find("option").remove(); 
			 		for (var i = 0; i < data.length; i++) {  
			 			$("#persentStorageLocation").append("<option name='cpsition'  value='"+data[i].cpsition+"'>"+data[i].cpsition+"</option>");
			 		}
			 		
			 	}
    	});
}
$('#btnUploadCP').click(function () {
    var fileElementId =$(this).prev().attr('id');
    var file = $('#fileToUpload').val();
  	
    if (file == "") {
        alert("请选择需要上传的文件！");
        return false;
    }

    var systeManagementCode = $('#systeManagementCode').val();
    if ("" == systeManagementCode) {
        alert('单据号不可为空');
        return false;
    }

    uploadFileCard('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}',systeManagementCode,$('#uploadTable > tbody'),fileElementId,"0");

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
function uploadFileCard(dynamicURL, userName, userCode, systeManagementCode, $tableBody, fileElementId,typeName) {
	 var fileName = $("#fileToUpload").val();
	 
	 $("#btnUploadCP").attr("disabled",true);
	 $("#btnUploadCP").val("上传ing");
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
										+ '</td><td scope="col" class="tdFileName">'
										+ data.result.fileName
										+ '</td><td scope="col">'
										+ data.result.createUserName
										+ '</td><td style="width:9%">'
										+ data.result.createDate
										+ '</td><td id="tdShowPic" align="center" style="width:10%"><a href="javascript:void(0)" onclick="picPreview(\''+data.result.fileuuid+'\')">预览</a>' 
										+ '</td><td scope="col"><a href="'+dynamicURL+'/file/downloadFile?fileuuid=' + data.result.fileuuid + '">下载</a></td></tr>');
						alert("文件上传成功!");
						 $("#btnUploadCP").attr("disabled",false);
						 $("#btnUploadCP").val("上传");
					} else {
						alert(data.errorMessages.join());
						 $("#btnUploadCP").attr("disabled",false);
						 $("#btnUploadCP").val("上传");
					}
				},
				error : function() {
					
					 $("#btnUploadCP").attr("disabled",false);
					 $("#btnUploadCP").val("上传");
				}
			});
	 }else{
		alert("请上传PDF或者JGF格式的图片");
		 $("#btnUploadCP").attr("disabled",false);
		 $("#btnUploadCP").val("上传");
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
$('#btnDelFileCP').click(function () {
    deleteFilesCard('${dynamicURL}', "uploadTable");
});

$("#btnDelFileJYBG").click(function(){
	deleteFilesCard('${dynamicURL}', "uploadTableJYBG");
});
$("input[name=isThirdReport]").click(function(){
	var isThirdReport=$("input[name=isThirdReport]:checked").val();
	if(isThirdReport=="0"){
		$("#tr_isThirdReport").show();
		$("#tr_isThirdReportPic").show();
	}
	if(isThirdReport=="1"){
		$("#tr_isThirdReport").hide();
		$("#tr_isThirdReportPic").hide();
	}
});
//文件删除
function deleteFilesCard(dynamicURL, tableId) {
    var ids = [];
    $('#' + tableId + ' input[name = "documentId"]:checked').each(function () {
        ids.push($(this).attr('uuid'));
    });

    if (ids.length == 0) {
        alert("请至少选择一个要删除的文件");
        return false;
    }

    $.ajax({
        type: "POST",
        url: dynamicURL + "/file/deleteFile",
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify({"documents": ids}),
        success: function (data) {
            if (data.success) {
                alert("文件删除成功!");
                for (var i = 0; i < ids.length; i++) {
                    $("#"+tableId+" > tbody").find("."+ids[i]+"").remove();
                }
                var s = document.getElementById("tr");
                if(!s){
                    $("#"+tableId+" > tbody").append("<tr class='noItems' align='center'>"+
                        "<td colspan='5'>没有记录！</td>"+
                        "</tr>");
                }
            } else {
                alert(data.errorMessages.join());
            }
        }
    });

}
function checkBoxXZ(){
	//复选框的选中与重置
	
		var chk = document.getElementById('checkTrue');
		if(chk.checked){
			
			$("input:checkbox[name=documentId]").each(function(){
				 $(this).attr('checked', true);
			});

		}
		else{
			$("input:checkbox[name=documentId]").each(function(){
				 $(this).attr('checked', false);
			});
		}
	
}
function picPreview(picUuid){
		var url = "${dynamicURL}/assetCard/showAssetPic?parentWindowId=addCard&uuid="+picUuid;
		var parentWindow=dg.curWin; 
		new parentWindow.$.dialog({ id: 'list', title: "预览图片", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
			   
		 
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
	</script>
</body>
</html>