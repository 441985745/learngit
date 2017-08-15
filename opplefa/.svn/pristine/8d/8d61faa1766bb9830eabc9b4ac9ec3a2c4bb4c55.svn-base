<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>报废变卖审批</title>
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
		<link rel="Stylesheet" type="text/css" href="${staticURL}/assetallocation/css/allocationapplicationapply.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
	</head>

<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div style="height: 25px; font-size: 15px; font-weight: bold;text-align:center; vertical-align: middle">
						报废变卖审批
					</div>
		<div></div>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<input type="button" value="更新SAP" id="updateSAPBtn" style="display: none;" class="btn">
		<input type="button" value="同意" id="agreeBtn"  class="btn">
		<input type="button" value="退回" id="denyBtn"  class="btn">
		 <c:choose>
        <c:when test="${discardSell.approvalState == '等待审批'}"> </c:when>
        <c:otherwise>
           <input type="button" id="dropBtn" class="btn" value="弃审" />    
        </c:otherwise>
    </c:choose>
      	   
		<input type="button" value="返回" onclick="dg.cancel();"  class="btn">
		<input type="button" value="打印" onclick="discardSellStamp('${discardSell.id}')"  class="btn">
	</div>
		<app:approvalTag billCode="${discardSell.document}" typeId="77031"></app:approvalTag>
	<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
				<div class="imgtitle"></div>
						基本信息
				</div>
	<form id="discardForm">
	 <table>

	 	<tr>
	 		<td class="td_right"><span class="span_color">*</span>申请流程单号:</td>
	 		<td class="td_left">
	 		<input type="hidden" name='userName' value="${discardSell.userName }">
	 			<input type="hidden" name='userCode' value="${discardSell.userCode }">
					<input type="hidden" name="companyCode" value="${discardSell.companyCode }">
					<input type="hidden" name="departmentCode" value="${discardSell.departmentCode }">
					<input type="hidden" name="departmentName" value="${discardSell.departmentName }">
					<input type="hidden" name="id" value="${discardSell.id }" id="discardId">
					<input type="hidden" id="activitiDiscardOrSell" value="${discardSell.discardOrSell }">
	 		    <input name="isMail" id="isMail"  type="hidden" />
	            <input name="isPhoneMessage" id="isPhoneMessage"  type="hidden" />
	            <input name="idea" id="idea"  type="hidden" />
	            <input name="activityId" id="activityId" type="hidden">
	            <input id="isUpdateSap" type="hidden"  value="${discardSell.isUpdateSap}">
				<input name="document" id="document" type="text" value="${discardSell.document}" readonly="readonly" class="td_input"/>
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
				
				<td class="td_right"><span class="span_color">*</span>申请人:</td>
				<td class="td_left"><input type="text" name="userName" value="${discardSell.createBy }" readonly="readonly" class="td_input"><input
					type="hidden" name="commitType" id="isDraft" />
					<input
					type="hidden" name="createBy" id="createBy" value="${discardSell.createBy }"/>
					</td>
				<td class="td_right"><span class="span_color">*</span>申请日期:</td>
				<td class="td_left"><input class="td_input" name="createDate" type="text"
					readonly="readonly"  id="applicationDate"  value="<fmt:formatDate value="${discardSell.createDate}"  pattern="yyyy-MM-dd"/>"></td>
						<td class="td_right"><span class="span_color">*</span>办公地点:</td>
				<td class="td_left">
					<input class="td_input" name="officeLocation" id="officeLocation" readonly="readonly" value="${discardSell.officeLocation}">
				</td>

			</tr>
			<tr>
				<td class="td_right"><span class="span_color">*</span>是否一次性费用化资产:</td>
				<td class="td_left"><c:if test="${discardSell.isExpenseAssets=='0'}">
                    	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="否" id="lblCom">
	            </c:if>
	            <input type="hidden" value="${discardSell.isExpenseAssets}" id="getIsExpenseAssets">
	            <c:if test="${discardSell.isExpenseAssets=='1'}">
                    	<input name="lblCom" class="td_input" readonly="readonly" type="text" value="是" id="lblCom">
	            </c:if></td>
				<td class="td_right"><span class="span_color">*</span>资产所属部门:</td>
				<td class="td_left"><input class="td_input"  name="subordinateDepartment" type="text"
					readonly="readonly"  id="subordinateDepartment" value="${discardSell.subordinateDepartment }">
					<input id="subordinateDepartmentCode" type="hidden" name="subordinateDepartmentCode" value="${discardSell.subordinateDepartmentCode }">
					</td>
					<td class="td_right"><span class="span_color">*</span>资产归属公司:</td>
				<td class="td_left">
					<input readonly='readonly' class="td_input" name='adscriptionCompany' type='text'  id='adscriptionCompany' value="${discardSell.adscriptionCompany }" >
					<input type="hidden" name="adscriptionCompanyCode" id="adscriptionCompanyCode" value="${discardSell.adscriptionCompanyCode }">
					</td>
				
			</tr>
			<tr>
			<td class="td_right"><span class="span_color">*</span>资产所属成本中心</td>
				<td class="td_left">
						<input class="td_input" id="costCenterName" name="costCenterName" type="text" value="${discardSell.costCenterName }">
						<input  class="td_input" id="costCenterCode" name="costCenterCode" type="hidden" value="${discardSell.costCenterCode }">
					 	
				</td>
		
				<td class="td_right"><span class="span_color">*</span>平台:</td>
				<td class="td_left"><input class="td_input" name="platform" id="platform" readonly="readonly" value="${discardSell.platform }"></td>
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
		
	 
	 	<tr>
	 		<tr id="trWorkshop" style="display: none;">
				<td class="td_right"><span class="span_color">*</span>车间类型:</td>
				<td class="td_left">
					<input class="td_input" name="workshopType" id="workshopType" readonly="readonly" value="${discardSell.workshopType}">	
				</td>
				

	 		<tr id="trWorkshop">
				<td class="td_right">车间类型:</td>
				<td class="td_left"> <input class="td_input" readonly="readonly" value="${discardSell.workshopType}"> </td>
		 <td class="td_right"><span class="span_color">*</span>申请原因:</td>
		 <td class="td_left" >
			 <input class="td_input" readonly="readonly" value="${discardSell.application}">
		 </td>
			</tr>

	 	</table>
	 
	  	<div id="putManage" >
	 		<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					归口部门审核填写
			</div>
			<div>
				<table>
				
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
	<!-- 	<div id="platformFinance" >
	 		<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					平台财务填写
			</div>
			<div>
				<table>
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
				<table>
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
						<input type="text" name="basePrice" value="${discardSell.basePrice}" readonly="readonly" class="td_input" id="basePrice" onkeyup= "clearNoNum(this)" >
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
				<table>
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
						<input type="text" name="actualDisposalPrice" value="${discardSell.actualDisposalPrice}" readonly="readonly" class="td_input" id="actualDisposalPrice" onkeyup= "clearNoNum(this)" >
					</td>
					</tr>
				</table>
			</div>
			</div>
		</form>
	 		<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					资产信息
			</div>

			 <div style="overflow-x: scroll; width: 100%;">
			<table  border="0" cellpadding="3px" cellspacing="0" width="150%">

				<thead>
					<tr>		<th></th>
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
								<th>公司</th>
								<th>成本中心</th>
						
						</tr>

				</thead>


				<tbody>
				<c:forEach items="${discardSell.listDiscardSellDetail}" var="gneralAsset">
							<tr style="text-align: center;">
								<td ><input type="hidden"
										value="${gneralAsset.id}" name="radio"
										id="kuang${gneralAsset.id}" />
								</td>
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
								<td>${gneralAsset.scrapCertificate} </td>
								<td> ${gneralAsset.companyName}</td>
								<td>${gneralAsset.costCenterCode} </td>
							
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
			<!-- <div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
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

		var isManager='${isManager}';//归口经理
		var	isPlatformFinance="${isPlatformFinance}";//平台才诶
		var isPurchasingManage="${isPurchasingManage}";//采购经理
		var isManagerPerson="${isManagerPerson}";//归口管理员
		var isPurchasingPerson="${isPurchasingPerson}";//采购员
		var isAssetAccounting="${isAssetAccounting}";//是否是资产会计
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

		var getIsExpenseAssets=$("#getIsExpenseAssets").val();
		if(isAssetAccounting=="Y"&&getIsExpenseAssets=="0"){
			$("#updateSAPBtn").show();
		}
		
		if(isManager=="Y"||isManagerPerson=="Y"){
			$("#putManage").show();
			$("#discardOrSell").attr("disabled",false);
			$("#putmanageIdea").attr("readonly",false);
			$("#judgmentBasis").attr("readonly",false);
			
		}
		
		if(isPlatformFinance=="Y"){
			$("#putmanageIdea").attr("readonly","readonly");//建议
			$("#judgmentBasis").attr("readonly","readonly");//判断依据
			$("#discardOrSell").attr("disabled",true);//报废类型
			$("#discardTypeFinance").attr("disabled",false);//平台财务
			$("#platformFinance").show();
			$("#putManage").show();
		}
		//采购经理
		if(isPurchasingManage=="Y"){
			
			$("#basePrice").attr("readonly",false);
			$("#purchasingJudgmentBasis").attr("readonly",false);
			$("#discardOrSell").attr("disabled",false);
		}
		if(isPurchasingPerson=="Y"){
			$("#supplierName").attr("readonly",false);
			$("#sellPattern").attr("readonly",false);
			$("#actualDisposalPrice").attr("readonly",false);
			$("#platformFinance").show();
			$("#putManage").show();
		}
		$("#agreeBtn").click(function (){
			var isAssetAccounting="${isAssetAccounting}";
			var getIsExpenseAssets=$("#getIsExpenseAssets").val();
			var isUpdateSap=$("#isUpdateSap").val();
			if(isAssetAccounting=="Y"&&isUpdateSap=="1"){
				showAgreeDialog();
			}
			if(isAssetAccounting=="Y"&&isUpdateSap=="0"&&getIsExpenseAssets=="0"){
				alert("请更新SAP!");
			}
			if(isAssetAccounting!="Y"){
				showAgreeDialog();
			}
			if(isAssetAccounting=="Y"&&getIsExpenseAssets=="1"){
				showAgreeDialog();
			}
			
		});
		$("#denyBtn").click(function(){
			var isUpdate=$("#isUpdateSap").val();
			if(isUpdate=="1"){
				alert("已更新过SAP！不允许退回！");
			}
			if(isUpdate=="0"){
				showDenyDialog();
			}
			
		});
		$("#dropBtn").click(function(){
			var isUpdate=$("#isUpdateSap").val();
			if(isUpdate=="1"){
				alert("已更新过SAP！不允许弃审！");
			}
			if(isUpdate=="0"){
				showDropDialog();
			}
			
		});
	    function showAgreeDialog() {
	    	var document =$("#document").val();
	    	var  changeAmount="";
	
    	   if($("#activitiDiscardOrSell").val()!=$("#discardOrSell").val()){
   		   	 changeAmount="Y";
   		   	 
	        }else{
        	 changeAmount="N";
        	 
	        }
    		
	        var url = "${dynamicURL}/workflow/showAgreeDialog?parentWindowId=approve&document=" + document + "&typeId="+77031+"&changeAmount="+changeAmount;

	        var dlg = new dg.curWin.$.dialog({ id: 'agreeDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
	        dlg.ShowDialog();
	        return dlg;
	    }
	    function showDenyDialog() {
	    	var document =$("#document").val();
	        var url = "${dynamicURL}/workflow/showDenyDialog?parentWindowId=approve&document=" + document + "&typeId="+77031;

	        var dlg = new dg.curWin.$.dialog({ id: 'denyDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
	        dlg.ShowDialog();
	        return dlg;
	    }
	    function showDropDialog() {
	        var url = "${dynamicURL}/workflow/showDropDialog?parentWindowId=approve";

	        var dlg = new dg.curWin.$.dialog({ id: 'dropDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
	        dlg.ShowDialog();
	        return dlg;
	    }
	    function commitAgree(isMail, isPhoneMessage, idea,activityId) {
	        $('#isMail').val(isMail);
	        $('#isPhoneMessage').val(isPhoneMessage);
	        $('#idea').val(idea);
			$("#activityId").val(activityId);
	        $('#agreeBtn').val('提交...');
	       $.ajax({
	            type: "POST",
	            url: "${dynamicURL}/workflowDiscardSell/agreeDiscardSell?"+$('#discardForm').serialize(),
	            contentType: "application/json; charset=gb2312",
	            success: function (data) {
	                if (data.success) {
	                    alert("审批成功");
	                    dg.curWin.location.reload();
	                } else {
	                	var str = data.errorMessages;
	                    alert(str);
	                    var reg = new RegExp("^.*审批成功.*$");
	                    if (reg.test(str)) {
	                        dg.curWin.location = dg.curWin.location;
	                    }
	                    $('#agreeBtn').val('同意');
	                    
	                }
	            }
	        });
	    }
	    function commitDeny(isMail, isPhoneMessage, idea,activityId,s) {
	    	
	        $('#isMail').val(isMail);
	        $('#isPhoneMessage').val(isPhoneMessage);
	        $('#idea').val(idea);
	        $("#activityId").val(activityId);
	        $('#btnDeny').val('提交...');

	        $.ajax({
	            type: "POST",
	            url: "${dynamicURL}/workflowDiscardSell/denyDiscardSell?"+$('#discardForm').serialize(),
	            contentType: "application/json; charset=gb2312",
	            success: function (data) {
	                if (data.success) {
	                    alert("审批成功");
	                    dg.curWin.location.reload();
	                } else {
	                	var str = data.errorMessages;
	                    alert(str);
	                    var reg = new RegExp("^.*审批成功.*$");
	                    if (reg.test(str)) {
	                        dg.curWin.location = dg.curWin.location;
	                    }
	                    $('#btnDeny').val('退回');
	                   
	                }
	            }
	        });
	    }
	    function commitDrop(isMail, isPhoneMessage, idea) {
	    	
	        $('#isMail').val(isMail);
	        $('#isPhoneMessage').val(isPhoneMessage);
	        $('#idea').val(idea);

	        $('#btnDrop').val('提交...');
	        $.ajax({
	            type: "POST",
	            url: "${dynamicURL}/workflowDiscardSell/dropDiscardSell?"+$('#discardForm').serialize(),
	            contentType: "application/json; charset=gb2312",
	            success: function (data) {
	                if (data.success) {
	                    alert("审批成功");
	                    dg.curWin.location.reload();
	                } else {
	                	var str = data.errorMessages;
	                    alert(str);
	                    var reg = new RegExp("^.*审批成功.*$");
	                    if (reg.test(str)) {
	                        dg.curWin.location = dg.curWin.location;
	                    }
	                    $('#btnDrop').val('弃审');
	                   
	                }
	            }
	        });
	    }
	    $("#updateSAPBtn").click(function(){
	    	 $("#updateSAPBtn").val("更新SAPing");
			 $("#updateSAPBtn").attr("disabled",true);
	    	var document=$("#document").val();
	    	var isUpdate=$("#isUpdateSap").val();
	    	if(isUpdate=="0"){
	    	$.ajax({
	    		type:"post",
	    		url:"${dynamicURL}/discardSell/updateSAP",
	    		data:{document:document},
	    		success:function(data){
	    			if(data.success){
						 alert("更新SAP成功！");
						 $('#updateSAPBtn').val('更新SAP');
						 $("#updateSAPBtn").attr("disabled",false);
						 location.reload();
					}else{
						$('#updateSAPBtn').val('更新SAP');
						$("#updateSAPBtn").attr("disabled",false);
	                    alert(data.errorMessages+"更新失败");
					}
	    		}
	    		
	    	});
	    	}else{
	    		alert("已经更新过SAP!");
	    		$('#updateSAPBtn').val('更新SAP');
				$("#updateSAPBtn").attr("disabled",false);
	    	}
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
	 									 var uploaduser = '${ sessionScope._user_nick_name}';
	 				                        if(uploaduser == item.createUserName) {
	 				                            $('#uploadTable > tbody').append('<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
	 				                                + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '"name=documentId   />'
	 				                                + '</td><td scope="col">' + item.fileName
	 				                                + '</td><td scope="col">' + item.createUserName
	 				                                + '</td><td style="width:9%">' + item.createDate
	 				                                + '</td><td align="center" style="width:10%"><a href="'+'${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
	 				                                + '</td></tr>');
	 				                        } else {
	 				                            $('#uploadTable > tbody').append('<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
	 				                                + '<td style="text-align:center;"><input type="checkbox" uuid="" name=documentId  />'
	 				                                + '</td><td scope="col">' + item.fileName
	 				                                + '</td><td scope="col">' + item.createUserName
	 				                                + '</td><td style="width:9%">' + item.createDate
	 				                                + '</td><td align="center" style="width:10%"><a href="'+'${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
	 				                                + '</td></tr>');
	 				                        }
	 								});
	 			}

	 		}
	 	});
        function discardSellStamp(id){
            var lurl = "${dynamicURL}/discardSell/discardSellStamp?id=" + id + "&typeId=77031";
            window.open(lurl);
        }
        function clearNoNum(obj){  
	            //修复第一个字符是小数点 的情况.  
	          
        	  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符   
        	  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的   
        	  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
        	  obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数   
        	  if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额  
        	   obj.value= parseFloat(obj.value);  
        	  } 
        	  var s= obj.value;
        	  if(s==""){
        		  alert("只能输入数字，并且保留两位数字！");
        	  }
        	}
	</script>
</body>
</html>