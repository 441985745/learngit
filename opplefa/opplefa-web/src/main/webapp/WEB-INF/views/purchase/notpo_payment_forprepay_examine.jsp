<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>非PO付款</title>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>

    <div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
        <div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
                非PO付款</div>
    </div>
    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
        <input type="button" id="btnAgree" class="btn" value="同意" />
     	<input type="button" id="btnDeny" class="btn" value="退回" />
        <c:choose>
            <c:when test="${notPoPaymentOrder.approvalState == '等待审批'}"> </c:when>
            <c:otherwise>
                <input type="button" id="btnDrop" class="btn" value="弃审" />
            </c:otherwise>
        </c:choose>
        <input type="submit" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
    </div>
    <div style="margin-top: 10px;">
    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<app:approvalTag billCode="${notPoPaymentOrder.cdocument}" typeId="77079"></app:approvalTag>
	</div>
    <div id="purchaseTab" class="easyui-tabs">
       <div title="订单信息" id="tabsa" style="overflow:auto;padding:20px;min-width: 800px;">
       <div width="100%">
       <form id="paymentOrderFormHeader">
        <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
            <tbody><tr>
                <td class="titlefontsize" align="left">
                <div class="imgtitle"></div>
                基本信息</td>
            </tr>
            <tr>
                <td>
                    <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
						<tbody>
						<tr>
							<td style="text-align: right; width: 90px;">单据号:</td>
							<td>
								<input class="searchinput" readonly name="cdocument" type="text" value="${notPoPaymentOrder.cdocument}" id="cdocument">
								<%-- <input class="searchinput" readonly name="officeLocation" type="hidden" value="${purchaseOrder.officeLocation}" id="officeLocation"> --%>
							</td>
							<td style="text-align: right"><font color="red" >*</font>所属部门:</td>
							<td>
		                        <input readonly class="searchinput" readonly="readonly" name="departmentName" value="${notPoPaymentOrder.departmentName}" type="text" id="txtBudgetDepartment" />
		                        <input name="departmentCode" value="" type="hidden" id="txtYsssbm" class="inputbtnRefViewGray" >
	                        </td>
							<td style="text-align: right; width: 90px;"><span style="color: red">*</span>办公地点:</td>
							<td>
							    <input class="searchinput" readonly name="officeLocation" type="text" value="${notPoPaymentOrder.officeLocation}" id="officeLocation"/>
								<%-- <select class="searchselect" name="officeLocation" id="officeLocation">
										<option value="">--请选择--</option>
									<c:forEach items="${scanLocations}" var="scanLocation">
										<option value="${scanLocation.clocationname}">${scanLocation.clocationname}</option>
									</c:forEach>
	                            </select> --%>
							</td>
							<%-- <td style="text-align: right" >税率:</td>
							<td class="tdtext">
								<input readonly class='searchinput'  type='text' value='<fmt:formatNumber type="number" value="${purchaseOrder.taxRate*100}" maxFractionDigits="0"/>%' id='cc'>
								<input readonly class='searchinput' name='taxRate' type='hidden' value='${purchaseOrder.taxRate}' id='taxRate'>
								<input readonly='readonly' class='searchinput' name='taxRateCode' type='hidden' value='${purchaseOrder.taxRateCode}' id='taxRateCode'>
							</td> --%>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;">申请人:</td>
							<td>
								<input class="searchinput" readonly name="applyUser" type="text" value="${notPoPaymentOrder.applyUser}" id="applyUser">
							</td>
							<td style="text-align: right;">所属公司:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='companyName' type='text' value='(${notPoPaymentOrder.companyCode})${notPoPaymentOrder.companyName}' id='companyName'> 
								<%-- <select id="companyName" class="searchselect" name='companyName'></select>	--%>				
	                        </td>
	                        <td style="text-align: right"><font color="red" >*</font>币种:</td>
	                        <td>
	                        	<input class="searchinput" readonly name="currencyCode" type="text" value="${notPoPaymentOrder.currencyCode}" id="currencyCode"/>
								<!-- suplc="Y" --> <%-- <select class="searchselect" name="currencyCode" id="currencyCode" onchange="selectCurrency()" >
									<c:forEach items="${currencylist}" var="o" varStatus="status">
									   <option value="${o.ccurrencycode}" ${o.ccurrencycode=="CNY" ? "selected" :"" }>${o.ccurrencycode} ${o.ccurrencyname }</option>
									</c:forEach>
								 </select> --%>
	                        </td>
							<%-- <td style="text-align: right; width: 90px;">币种:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='currencyName' type='text' value='${notPoPaymentOrder.currencyName}' id='currencyName'>
							</td> --%>
						</tr>
						<tr>
							<td style="text-align: right;">申请日期:</td>
							
							<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="applyDate" type="text" value="<fmt:formatDate value='${notPoPaymentOrder.applyDate}'  pattern='yyyy-MM-dd'/>" id="txtApplyDate">
							</td>
							<td style="text-align: right"><font color="red" >*</font>成本中心:</td>
	                        <td class="tdtext" >
								<span id="spanCostCenter">
								<input readonly="readonly" class="searchinput" style="background-color: #CCCCCC;" name="costCenter" type="text" value="${notPoPaymentOrder.costCenter}" id="txtCostCenter">
								<input readonly="readonly" class="searchinput" style="background-color: #CCCCCC;" name="costCenterCode" type="hidden" value="" id="txtCostCenterCode">
								</span>
							</td>
							<td style="text-align: right" >汇率:</td>
							<td class="tdtext"><input class='searchinput' readonly="readonly" name='exchangeRate' type='text' value="${notPoPaymentOrder.exchangeRate}"  id='exchangeRate'></td>
						</tr>
						<tr> 
							<td style="text-align: right; width: 90px;"><span style="color: red">*</span>申请事由:</td>
							<td>
								<input  class='searchinput' readonly="readonly" name='applyFor' type='text' value='${notPoPaymentOrder.applyFor}' id='applyFor'>
							</td>
							<td style="text-align: right; width: 90px;">所属利润中心:</td>
							<td>
								<input readonly="readonly" class='searchinput' name='profitCenter' type='text' value='${notPoPaymentOrder.profitCenter}' id='profitCenter'>
							</td>
							<td style="text-align: right" >备注:</td>
							<td class="tdtext"><input  class='searchinput' readonly="readonly" name='remark' type='text' value='${notPoPaymentOrder.remark}' id='remark'></td>
						</tr>
						<c:if test="${notPoPaymentOrder.paymentType=='尾款'}">
						<tr> 
							<td style="text-align: right" >验收款单号:</td>
							<td>
								<input  class='searchinput' readonly="readonly" name='acceptOrderNumber' type='text' value='${notPoPaymentOrder.acceptOrderNumber}' id='acceptOrderNumber'>
							</td>
						</tr>
						</c:if>
						</tbody>
					</table>
                </td>
            </tr>           
            </tbody>
        </table>
      	
        </br>
        <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
            <tbody><tr>
                <td class="titlefontsize" align="left">
                <div class="imgtitle"></div>
               付款明细信息</td>
            </tr>
            <tr>
                <td>
                    <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
						<tbody>
						<tr>
							<td style="text-align: right; width: 90px;">挂账人:</td>
							<td>
								<input class="searchinput" name="paymentPeople" type="text"
								readonly="readonly" value="${notPoPaymentOrder.paymentPeople}" id="paymentPeople">
								<%-- <input class="searchinput"  name="paymentPeople" type="text" value="${notPoPaymentOrder.paymentPeople}" id="paymentPeople"> --%>
								<input class="searchinput" readonly name="paymentPeopleCode" type="hidden" value="${notPoPaymentOrder.paymentPeopleCode}" id="paymentPeopleCode">
							</td>
							<td style="text-align: right; width: 90px;"><font color="red" >*</font>预计核销时间:</td>
							<td class="tdtext" >
								<input readonly='readonly' name="predictTime" value="<fmt:formatDate value='${notPoPaymentOrder.predictTime}'  pattern='yyyy-MM-dd'/>" type="text" id="predictTime" class="Wdate" style="width: 140px;" onclick="WdatePicker({skin:'ext'});">
							</td>
							<td style="text-align: right" >申请金额(原币):</td>
							<td class="tdtext">
								<input  class='searchinput' readonly='readonly'  type='text' value='${notPoPaymentOrder.applyMoney}' id='applyMoney' name='applyMoney' onkeyup="value=value.replace(/[^\d.]/g,'')">
								<%-- <input readonly class='searchinput' name='taxRate' type='hidden' value='${purchaseOrder.taxRate}' id='taxRate'>
								<input readonly='readonly' class='searchinput' name='taxRateCode' type='hidden' value='${purchaseOrder.taxRateCode}' id='taxRateCode'> --%>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;">供应商编码:</td>
							<td>
								<input style="width: 100px;" name="supplierCode" value="${notPoPaymentOrder.supplierCode}" type="text" id="supplierCode" class="inputbtnRefViewGray" ></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanSupp();"><input id="butSupplierShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
								<%-- <input  class='searchinput' name='supplierCode' type='text' value='${purchaseOrder.supplierCode}' id='supplierCode'> --%>
							</td>
							<td style="text-align: right;">联系人:</td>
							<td class="tdtext">
								<input  class='searchinput' name='contacts' type='text' value='${notPoPaymentOrder.contacts}' id='contacts'>					
	                        </td>
							<td style="text-align: right; width: 90px;">申请金额(本币):</td>
							<td>
								<input readonly='readonly' class='searchinput' name='applyMoneyLocal' type='text' value='${notPoPaymentOrder.applyMoneyLocal}' id='applyMoneyLocal'>
							</td>
						</tr>
						<tr> 
							<td style="text-align: right; width: 90px;">供应商名称:</td>
							<td>
								<input  class='searchinput' name='supplierName' type='text' value='${notPoPaymentOrder.supplierName}' id='supplierName'>
							</td>
							<td style="text-align: right"><font color="red" >*</font>收款账号:</td>
	                        <td class="tdtext" >
								<span id="spanCostCenter">
								<input  class="searchinput" style="background-color: #CCCCCC;" name="bankNumber" type="text" value="${notPoPaymentOrder.bankNumber}" id="bankNumber">
								</span>
							</td>
							<td style="text-align: right; width: 90px;">批准金额(原币):</td>
							<td>
								<input readonly='readonly' class='searchinput' name='iamoney' type='text' value='${notPoPaymentOrder.iamoney}' id='iamoney'>
							</td>
						</tr>
						<tr> 
							<td style="text-align: right; width: 90px;">国家:</td>
							<td>
								<input  class='searchinput' name='country' type='text' value='${notPoPaymentOrder.country}' id='country'>
							</td>
							<td style="text-align: right; width: 90px;">开户行名称:</td>
							<td>
								<input  class='searchinput' name='bankName' type='text' value='${notPoPaymentOrder.bankName}' id='bankName'>
							</td>
							<td style="text-align: right" >批准金额(本币):</td>
							<td class="tdtext"><input readonly='readonly' class='searchinput' name='iamoneyLocal' type='text' value='${notPoPaymentOrder.iamoneyLocal}' id='iamoneyLocal'></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;">城市:</td>
							<td>
								<input  class='searchinput' name='city' type='text' value='${notPoPaymentOrder.city}' id='city'>
							</td>
							<td style="text-align: right; width: 90px;">开户行号:</td>
							<td>
								<input  class='searchinput' name='bankCode' type='text' value='${notPoPaymentOrder.bankCode}' id='bankCode'>
							</td>
						</tr>
						</tbody>
					</table>
                </td>
            </tr>           
            </tbody>
        </table>
       </form>
		       <table width="100%" border="0" cellpadding="0" cellspacing="3px">
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
			<c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isComingSAP=='N'&&notPoPaymentOrder.paymentType=='预付款'}">
               <div class="trDiv">
			               <tr>
				               <td style="text-align:right;font-size: 12px;">是否手工凭证:</td>
				               <td style="text-align:left;">
				               <select style="width:150px;height:25px;" id="cinputVoucher" onchange="changeEvent()" >
				               		<option value="Y" >是</option>
									<option value="N" selected="selected">否</option>
				               </select>
				               </td>
				               <td style="text-align:right;font-size: 12px;">凭证号:</td>
						       <td style="text-align:left;"><input id="cvoucherId" type="text" class="my-text" disabled="disabled" /><input id="createPrepayment" style="width:100px;height:25px;" type="button"  value="生成凭证" ></td>
						    </tr>
		       </div>
		       </c:if>
		       <c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isComingSAP=='Y'&&notPoPaymentOrder.paymentType=='预付款'}">
               <div class="trDiv">
			               <tr>
				               <td style="text-align:right;font-size: 12px;">是否手工凭证:</td>
				               <td style="text-align:left;">
				               <select style="width:150px;height:25px;" id="cinputVoucher" onchange="changeEvent()" disabled="disabled">
				               		<option value="Y" >是</option>
									<option value="N" selected="selected">否</option>
				               </select>
				               </td>
				               <td style="text-align:right;font-size: 12px;">凭证号:</td>
						       <td style="text-align:left;"><input id="cvoucherId" type="text" class="my-text" value="${notPoPaymentOrder.cvoucherId}" disabled="disabled" /><input id="createPrepayment" style="width:100px;height:25px;" type="button"  value="生成凭证" disabled="disabled"></td>
						    </tr>
		       </div>
		       </c:if>
		       <tr>
                   <td colspan="4">
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
                                           <td class="tdleft"><fmt:formatDate value='${appHistory.examineDate}' type="both" /></td>
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
	<div id="ysssbmWindow" name="" class="easyui-window" title="预算所属部门" data-options="iconCls:'icon-save'" style="width:600px;height:330px;padding:5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
			  <ul id="tree1"></ul>
			</div>
		</div>
	</div>
	<div id="supplierWindow" name="" class="easyui-window" title="选择供应商" data-options="iconCls:'icon-save'" style="width:750px;height:400px;padding:5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
			    
			<form id="supplierForm">
				<table class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="0">
				<tr style="text-align: left">
				<td >供应商编码:</td>
				<td name=""><input type="text" id="searchSupplierCode" name="supplierCode"></td>
				<td>供应商名称:</td>
				<td><input name="supplierName" id="searchSupplierName" type="text" ></td>
				</tr>
				</table>
			</form>
			<input type="button" value="查询" id="searchSupplier"><input type="button" id="supplierSure" value="确定">
			<div style="width: 700px;height: 270px;overflow-x: scroll; overflow-y: visible;">
				<table id="supplierBudget" class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="1" cellpadding="6px">
				<thead>
					<tr>
						<th class='tdpadding'></th>
						<th class='tdpadding'>供应商名称</th>
						<th class='tdpadding'>供应商编码</th>
						<th class='tdpadding'>付款方式</th>
						<th class='tdpadding'>付款方式编码</th>
						<th class='tdpadding'>开户行</th>
						<th class='tdpadding'>开户行编码</th>
						<th class='tdpadding'>币种名称</th>
						<th class='tdpadding'>币种编码</th>
						<th class='tdpadding'>银行账号</th>
						<th class='tdpadding'>联行号</th>
						<th class='tdpadding'>联系人</th>
						<th class='tdpadding'>电话</th>
						<th class='tdpadding'>公司地址</th>
						<th class='tdpadding'>国家</th>
					
					</tr>
				</thead>
				<tbody id="supplierBody"></tbody>
				</table>
					
			</div>
			</div>
			
		</div>
	</div>
        </div>
        </div>
    </div>

<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
<link href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css" type="text/css" rel="stylesheet" />
<link href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css" type="text/css" rel="stylesheet" /> --%>
<script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;//定义dg 关闭需要
	
	var paymentType = $("#paymentType").val();//付款类型
	$(function(){
        $.ajax({
            url: '${dynamicURL}/file/getFilesByDocument',
            data: {'document': $("#cdocument").val()},
            dataType: 'json',
            type: 'POST',
            success: function (data) {
                if (data.length > 0) {
                    $('#uploadTable .noItems').remove();
                    $.each(data, function (i, item) {
                        var uploaduser = '${ sessionScope._user_nick_name}';
                        if (uploaduser == item.createUserName) {
                            $('#uploadTable > tbody').append('<tr id="tr" class="' + item.fileuuid + '" style="white-space:nowrap;">'
                                + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '"name=documentId   />'
                                + '</td><td scope="col">' + item.fileName
                                + '</td><td scope="col">' + item.createUserName
                                + '</td><td style="width:9%">' + item.createDate
                                + '</td><td align="center" style="width:10%"><a href="' + '${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                                + '</td></tr>');
                        } else {
                            $('#uploadTable > tbody').append('<tr id="tr" class="' + item.fileuuid + '" style="white-space:nowrap;">'
                                + '<td style="text-align:center;"><input type="checkbox" uuid="" name="documentId" />'
                                + '</td><td scope="col">' + item.fileName
                                + '</td><td scope="col">' + item.createUserName
                                + '</td><td style="width:9%">' + item.createDate
                                + '</td><td align="center" style="width:10%"><a href="' + '${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                                + '</td></tr>');
                        }

                    });
                }

            }
        });
	});
	$("#btnAgree").click(function(){
	/* 	if($("#prepaymentDocumentNumber").val()==""){
	    	alert("请先生成对应凭证！");
	    	return;
	    }
		if($("#receiptVoucherNumber").val()==""){
	    	alert("请先生成对应凭证！");
	    	return;
	    }
		if($("#paymentVoucherNumber").val()==""){
	    	alert("请先生成对应凭证！");
	    	return;
	    } */
		var cdocument = $("#cdocument").val();
		showAgreeDialog(cdocument,"77079");
	});
	$('#btnDeny').click(function () {
		var cdocument = $("#cdocument").val();
	    showDenyDialog(cdocument,"77079");
	});
	$('#btnDrop').click(function () {
/* 		var paymentType = $("#paymentType").val();
		var capprovalstate = $("#capprovalstate").val();
		if ((paymentType=="预付款"||paymentType=="验收款")&&capprovalstate=="已完成") {
			alert("单据已经入账，无法弃审！");
			return;
		} */
	    showDropDialog();
	});
	function showAgreeDialog(document, typeId) {
		if($("#cvoucherId").val()==""){
			alert("请先生成凭证！");
			return;
		}
	    var url = "${dynamicURL}/workflow/showAgreeDialog?parentWindowId=examineDialog&document=" + document + "&typeId=" + typeId;
	
	    var dlg = new dg.curWin.$.dialog({ id: 'agreeDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
	    dlg.ShowDialog();
	
	    return dlg;
	}
	function showDenyDialog(document, typeId) {
	    var url = "${dynamicURL}/workflow/showBarDenyDialog?parentWindowId=examineDialog&document=" + document + "&typeId=" + typeId;
	
	    var dlg = new dg.curWin.$.dialog({ id: 'barDenyDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
	    dlg.ShowDialog();
	
	    return dlg;
	}
	function showDropDialog() {
	    var url = "${dynamicURL}/workflow/showDropDialog?parentWindowId=examineDialog";
	
	    var dlg = new dg.curWin.$.dialog({ id: 'dropDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
	    dlg.ShowDialog();
	
	    return dlg;
	}
	function commitAgree(isMail, isPhoneMessage, idea, activityId) {
	    $('#btnAgree').val('提交...');
	    
	    var paymentOrderModel={};
	    paymentOrderModel.isManualPrepaymentDocument = $("#isManualPrepaymentDocument").val();
	    paymentOrderModel.prepaymentDocumentNumber = $("#prepaymentDocumentNumber").val();
		paymentOrderModel.cdocument = $("#cdocument").val(); 
		paymentOrderModel.idea = idea;
		paymentOrderModel.isMail = isMail;
		paymentOrderModel.isPhoneMessage = isPhoneMessage;
		paymentOrderModel.activityId = activityId;
		
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        async: false,
	        contentType: "application/json; charset=utf-8",
	        url: "${dynamicURL}/notPoPayment/api/agreePayment",
	        data: JSON.stringify(paymentOrderModel),
	        success: function(data) {
	        	if (data.success) {
	                alert("审批成功");
//	                dg.curWin.location=dg.curWin.location;;
                    dg.curWin.location=dg.curWin.location;

                    /*dg.curWin.refresh();
                    dg.cancel();*/
                   /* dg.curWin.location.href = '${dynamicURL}/payment/searchPayment';
					dg.cancel();*/
	            } else {
	            	var str = data.errorMessages;
					alert(str);
					var reg = new RegExp("^.*审批成功.*$");
					if (reg.test(str)) {
						dg.curWin.location = dg.curWin.location;
					}

	                $('#btnAgree').val('同意');
	            }
	        }
	    });
	}
	function commitDeny(isMail, isPhoneMessage, idea,isPortrait, activityId) {
	    $('#btnDeny').val('提交...');
	    
	    var paymentOrderModel={};
		paymentOrderModel.cdocument = $("#cdocument").val(); 
		paymentOrderModel.idea = idea;
		paymentOrderModel.isMail = isMail;
		paymentOrderModel.isPhoneMessage = isPhoneMessage;
		paymentOrderModel.isPortrait = isPortrait;
		paymentOrderModel.activityId = activityId;
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        async: false,
	        contentType: "application/json; charset=utf-8",
	        url: "${dynamicURL}/notPoPayment/api/denyPayment",
	        data: JSON.stringify(paymentOrderModel),
	        success: function(data) {
	        	if (data.errorMessages.length > 0) {
	        		$('#btnDeny').val('退回');
	                alert(data.errorMessages);
	                return;
	            }else{
	            	alert(data.successMessage);
	            	 //关闭窗口
                    dg.curWin.location = dg.curWin.location;
	            }
	        }
	    });
	}
	function commitDrop(isMail, isPhoneMessage, idea) {
	
	    $('#btnDrop').val('提交...');
	
	    var paymentOrderModel={};
		paymentOrderModel.cdocument = $("#cdocument").val(); 
		paymentOrderModel.idea = idea;
		paymentOrderModel.isMail = isMail;
		paymentOrderModel.isPhoneMessage = isPhoneMessage;
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        async: false,
	        contentType: "application/json; charset=utf-8",
	        url: "${dynamicURL}/notPoPayment/api/dropPayment",
	        data: JSON.stringify(paymentOrderModel),
	        success: function(data) {
	        	if (data.success) {
	                alert("审批成功");
                    dg.curWin.location = dg.curWin.location;
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
	//是否手工生成预付款凭证监听事件
	function changeEvent(){
		var changeValue = $("#cinputVoucher option:selected").val();
		if (changeValue=='Y'){
			$("#createPrepayment").attr("disabled", true);
			$("#cvoucherId").attr("disabled", false);
		}
		if (changeValue=='N'){
			$("#createPrepayment").attr("disabled", false);
			$("#cvoucherId").attr("disabled", true);
		}
	}
	 //生成凭证号
	$("#createPrepayment").click(function(){
		var notPoPaymentOrder={};
		notPoPaymentOrder.cdocument = $("#cdocument").val();
		$.ajax({
			type : "POST",
			dataType: "json",
			async : false,
			contentType: "application/json; charset=utf-8",
			url : "${dynamicURL}/notPoPayment/api/createPrePayment",
			data: JSON.stringify(notPoPaymentOrder),
			success: function(data) {
            	if (data.success) {
                    alert(data.successMessage);
                    $("#cvoucherId").val(data.result.cvoucherId);
                    $("#createPrepayment").attr("disabled", true);
                    $("#cinputVoucher").attr("disabled", true);
                } else {
                    alert(data.errorMessages.join());
                }
            }
		});
	});
	//本次付款数量
	$('#applyMoney').keyup(function (){
		var applyMoney = $('#applyMoney').val();//获得本次付款金额
		var exchangeRate = $('#exchangeRate').val();//获得汇率
		$('#applyMoneyLocal').val(applyMoney*exchangeRate);
		$('#iamoney').val(applyMoney);
		$('#iamoneyLocal').val(applyMoney*exchangeRate);
		
	});
	$('#btnUpload').click(function () {
	    var fileElementId =$(this).prev().attr('id');
	    var file = $('#fileToUpload').val();
	    if (file == "") {
	        alert("请选择需要上传的文件！");
	        return false;
	    }
	
	    var documentNo = $('#cdocument').val();
	    if ("" == documentNo) {
	        alert('单据号不可为空');
	        return false;
	    }
	
	    uploadFile('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}', documentNo, $('#uploadTable > tbody'),fileElementId);
	
	});
	$('#btnDelFile').click(function () {
	    deleteFiles('${dynamicURL}', "uploadTable");
	});
	// 文件上传
	function uploadFile(dynamicURL,userName, userCode, documentNo, $tableBody,fileElementId) {
	    var _$ = reloadJqueryExsention($);
	
	    _$.ajaxFileUpload({
	        url: dynamicURL + '/file/uploadFile',
	        secureuri: false,
	        fileElementId: fileElementId,
	        dataType: 'text/html',
	        data: {
	            'createBy': userCode,
	            'createUserName': userName,
	            'document': documentNo
	        },
	        success: function (data) {
	            data = JSON.parse(data.split("<")[0]);
	            if (data.success) {
	                $tableBody.find('.noItems').remove();
	                $tableBody.append('<tr id="tr" class="'+data.result.fileuuid + '" style="white-space:nowrap;">'
	                    + '<td style="text-align:center;"><input type="checkbox" uuid="' + data.result.fileuuid + '" name=documentId />'
	                    + '</td><td scope="col">' + data.result.fileName
	                    + '</td><td scope="col">' + data.result.createUserName
	                    + '</td><td style="width:9%">' + data.result.createDate
	                    + '</td><td align="center" style="width:10%"><a href="'+dynamicURL+'/file/downloadFile?fileuuid=' + data.result.fileuuid + '">下载</a>'
	                    + '</td></tr>');
	                alert("文件上传成功!");
	            } else {
	                alert(data.errorMessages.join());
	            }
	        },
	        error: function () {}
	    });
	}
	// 文件删除
	function deleteFiles(dynamicURL, tableId) {
	    var ids = [];
	    var flag = false;
	    $('#' + tableId + ' input[name = "documentId"]:checked').each(function () {
	        ids.push($(this).attr('uuid'));
	        if ($(this).attr('uuid') == "") {
	            flag = true;
	        }
	    });

	    if (ids.length == 0) {
	        alert("请至少选择一个要删除的文件");
	        return false;
	    }
	    if (flag) {
	        alert("只能删除自己上传的文件！");
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
	//预算所属部门 开始
	$('#btntxtYsssbmShow').click(function(){
		var top = "90px";//$(this).offset().top - 350;
		var left = "120px";//$(this).offset().left-180;
		$('#ysssbmWindow').window('open').window('resize',{width:'450px',height:'300px',top: top,left:left});

		  $("#tree1").tree({
		    	url: '${dynamicURL}/department/searchDepartmentTreeInit',
		    	lines: true,
		    	onClick: function(node) {
		    		$.ajax({
		    			type : "POST",
		    			async : false,
		    			url : "${dynamicURL}/userInfo/api/searchLowDeptCount",
		    			data : "ckscode=" + node.id,
		    			success: function(dat){
		    				if(dat==0){
		    					alert("请选择末级部门！");
		    				}
		    				else{
					    		setTree(node.id, node.text);
		    				}
		    			}
		    		});
		     	}
		    });
	});
	function setTree(ckscode,cksname){ 
		/* $("#spanCompany input").remove();
		 $("#spanCostCenter input").remove(); */
		 //var ckscode = $("#tree1").tree('getSelected').id;
		 //var cksname = $("#tree1").tree('getSelected').text;
 		 $("#txtYsssbm").val(ckscode);
		 $("#txtBudgetDepartment").val(cksname);
		$.ajax({
			type : "POST",
			async : false,
			url : "${dynamicURL}/userInfo/api/getCompany",
			data : "ckscode=" + ckscode,
			success: function(treeObj){
				var html ="";
				if(treeObj && treeObj.length>1){
					html += "<option value=''>请选择</option>";
				}
				for (var i = 0; i < treeObj.length; i++) {
					var ccompanycode = treeObj[i].ccompanycode;
					var ccompanyname = treeObj[i].ccompanyname;
					
					html += "<option value='"+ccompanycode+"'>("+ccompanycode+")"+ccompanyname+"</option>";
				}
				$("#companyName").html(html);
					
/* 					//清空行明细
					statusIndex = 0;
			    	sumAll(); */

					$('#ysssbmWindow').window('close');
					if($("#companyName").val()!=""){
						getCostCenter();
					}
				 } 
		});
	}
	 function getCostCenter(){
	  		var ckscode = $("#txtYsssbm").val();
	  		var ccompanycode = $("#companyName").val();
	  		if(!ckscode){
				//资产类型是空
				return false;
			}
			if(!ccompanycode){
				//资产类型是空
				return false;
			}
	  		$.ajax({
				type : "POST",
				async : false,
				url : "${dynamicURL}/userInfo/api/getCostCenter",
				data : {"ckscode":ckscode,"ccompanycode":ccompanycode},
				success: function(treeObj){
					if(!treeObj){
						alert("没有配置该成本中心");
						//cleanDep();//清除根据成本中心带出的信息
						return false;
					}else{
						$("#txtCostCenter").val("("+treeObj.ccostcentercode+")"+treeObj.ccostcentername);
						$("#txtCostCenterCode").val(treeObj.ccostcentercode);
						//根据成本中心带出利润中心编码
						searchProfitCenter(treeObj.ccostcentercode);
					}
				}
	  		});
	  	}
	 function searchProfitCenter(costcentercode){
		 if(!costcentercode){
				return false;
			}
		 $.ajax({
				type : "POST",
				async : false,
				url : "${dynamicURL}/notPoPayment/api/searchProfitCenter",
				data : {"costcentercode":costcentercode},
				success: function(code){
					if(!code){
						alert("没有配置该利润中心");
						//cleanDep();//清除根据成本中心带出的信息
						return false;
					}else{
						$("#profitCenter").val(code);
					}
				}
	  		});
	 }
	//选择挂账人 
	 $("#showUserListBtn").click(function(){
		  showUserDialog();
	 });
    
   function showUserDialog(departmentCode){
	  var url = "${dynamicURL}/user/getUserListInAttachConfig?parentWindowId=add";
	  var parentWindow=dg.curWin; 
	  new parentWindow.$.dialog({ id: 'list', title: "所有人员", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
	   
   }
   function fillVehicleUser(userCode, userName) {
	   $('#paymentPeopleCode').val(userCode);
       $('#paymentPeople').val(userName);

   };
   function cleanDep() {
	   $('#txtBudgetDepartment').val('');
	   $('#txtYsssbm').val('');
	   $('#companyName').html('');
	   $('#txtCostCenter').val('');
	   $('#txtCostCenterCode').val('');
	   $("#profitCenter").val('');
	}
	function ClearObj() {
	   $('#paymentPeople').val('');
	   $('#paymentPeopleCode').val('');
	}
	$("#butSupplierShow").click(function(){
		searchSupplier();
	});
	//选择供应商
	function searchSupplier(){
		var top = "90px";//$(this).offset().top - 350;
		var left = "50px";//$(this).offset().left-180;
		$('#supplierWindow').window('open').window('resize',{width:'650px',height:'330px',top: top,left:left});
	}
	function searchSupplierLists(){
		if(!$("#companyName").val()) {
			alert("请先选择公司");
			return;
		}
		/* if(!$("#searchSupplierCode").val()&&!$("#searchSupplierName").val()){
			alert("请输入供应商名称或编码");
			return;
		} */
		var companyCode = $("#companyName").val();
		alert("!!!!!!!!");
		 $.ajax({
			 type: "POST",
			 cache:false,
	         url: "${dynamicURL}/supplier/api/searchSupplier",
	         data: $('#supplierForm').serialize()+"&companyCode="+companyCode,
	         async: false,
	         error: function(request) {
	             alert("Connection error");
	         },
	         success: function(data){
                    var html = ''; 
                    $.each(data, function(commentIndex, comment){
                           html += '<tr style="white-space:nowrap;"><td class="tdpadding"><input type="radio" name="supplierRadio" value='+commentIndex+'>'
                                + '</td><td class="tdpadding" id=\"supplierName'+commentIndex+'\">' + (comment['supplierName']==null?"":comment['supplierName'])
                                + '</td><td class="tdpadding" id=\"supplierCode'+commentIndex+'\">' + (comment['supplierCode']==null?"":comment['supplierCode'])
                                + '</td><td class="tdpadding" id=\"paymentTypeName'+commentIndex+'\">' + (comment['paymentTypeName']==null?"":comment['paymentTypeName'])
                                + '</td><td class="tdpadding" id=\"paymentTypeCode'+commentIndex+'\">' + (comment['paymentTypeCode']==null?"":comment['paymentTypeCode'])
                                + '</td><td class="tdpadding" id=\"bankName'+commentIndex+'\">' + (comment['bankName']==null?"":comment['bankName'])
                                + '</td><td class="tdpadding" id=\"bankCode'+commentIndex+'\">' + (comment['bankCode']==null?"":comment['bankCode'])
                                + '</td><td class="tdpadding" id=\"currencyName'+commentIndex+'\">' + (comment['currencyName']==null?"":comment['currencyName'])
                                + '</td><td class="tdpadding" id=\"currencyCode'+commentIndex+'\">' + (comment['currencyCode']==null?"":comment['currencyCode'])
                                + '</td><td class="tdpadding" id=\"bankNumber'+commentIndex+'\">' + (comment['bankNumber']==null?"":comment['bankNumber'])
                                + '</td><td class="tdpadding" id=\"contactNumber'+commentIndex+'\">' + (comment['contactNumber']==null?"":comment['contactNumber'])
                                + '</td><td class="tdpadding" id=\"contacts'+commentIndex+'\">' + (comment['contacts']==null?"":comment['contacts'])
                                + '</td><td class="tdpadding" id=\"telephone'+commentIndex+'\">' + (comment['telephone']==null?"":comment['telephone'])
                                + '</td><td class="tdpadding" id=\"companyAddress'+commentIndex+'\">' + (comment['companyAddress']==null?"":comment['companyAddress'])
                                + '</td><td class="tdpadding" id=\"country'+commentIndex+'\">' + (comment['country']==null?"":comment['country'])
                                + '</td></tr>';
                     });
                    $('#supplierBody').html(html);hj
                    
                  }
	     });
	}
	//查询供应商
	$("#searchSupplier").click(function(){
		searchSupplierLists();
	});
	//确定供应商
	$("#supplierSure").click(function(){
		var i = $("input[name='supplierRadio']:checked").val();
		if(!i){
			alert("未选择任何一行数据!");
			return false;
		}
		var supplierName = $.trim($("#supplierName"+i).html());
		var supplierCode = $.trim($("#supplierCode"+i).html());
		var paymentTypeName = $.trim($("#paymentTypeName"+i).html());
		var paymentTypeCode = $.trim($("#paymentTypeCode"+i).html());
		var bankName = $.trim($("#bankName"+i).html());
		var bankCode = $.trim($("#bankCode"+i).html());
		var currencyName = $.trim($("#currencyName"+i).html());
		var currencyCode = $.trim($("#currencyCode"+i).html());
		var bankNumber = $.trim($("#bankNumber"+i).html());
		var contactNumber = $.trim($("#contactNumber"+i).html());
		var contacts = $.trim($("#contacts"+i).html());
		var telephone = $.trim($("#telephone"+i).html());
		var companyAddress = $.trim($("#companyAddress"+i).html());
		var country = $.trim($("#country"+i).html());
		$('#supplierWindow').window('close');
		
		$("#supplierName").val(supplierName);
		$("#supplierCode").val(supplierCode);
		$("#paymentTypeName").val(paymentTypeName);
		$("#paymentTypeCode").val(paymentTypeCode);
		$("#bankName").val(bankName);
		$("#bankCode").val(bankCode);
		$("#currencyCode").find("option[value='"+currencyCode+"']").attr("selected",true);
		$("#bankNumber").val(bankNumber);
		$("#contactNumber").val(contactNumber);
		$("#contacts").val(contacts);
		$("#telephone").val(telephone);
		$("#companyAddress").val(companyAddress);
		$("#country").val(country);
		selectCurrency();
	});
	// 根据币种查询汇率
	function selectCurrency(){
		var currency = $('#currencyCode').val();
		$.ajax({
			type : "POST",
			nsync : true,
			url : _dynamicURL+"/userInfo/api/currencyRate",
			data : "currency="+currency,
			success : function(userInfo){
				//console.log(userInfo);
				$("#exchangeRate").val(userInfo.iexchangerate);
			}
		});
	}
	//表单封装json
	function constructObjectFromTable(object, $object) {
		var javaObjArray = [];
		
		$object.each(function(i) {
			var $ck = $(this).find('input[name^=gvdata]');
			if($ck.length != 0){
				if(!$ck.attr('checked')){
					return true;
				}
			}
		     var javaObj = {};
		     
		     for (var p in object) {
		    	 var $val = $(this).find('input[name="'+p+'"]');
		    	 if ($val.length === 0) {
		    		 javaObj[p] = $(this).find('select[name="'+p+'"]').val();
		    	 } else {
		    		 javaObj[p] = $val.val();
		    	 }
		     }
		     
		     javaObjArray.push(javaObj);
		});
		
		return javaObjArray;
	}
	$(document).ready(function(){
        $(window).resize(function(){
            $('#purchaseTab').tabs('resize');
        });
 	});
</script>
</body>


</html>