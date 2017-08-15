<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>付款管理</title>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>

    <div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
        <div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
                付款管理${paymentOrder.draft=='Y'?"(草稿)":""}</div>
    </div>
    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
        <input type="button" name="btnCancel" value="返回" id="btnCancel" class="btn" onclick="dg.cancel()">
		<input type="button" onclick="stamp('${paymentOrder.cdocument}','打印')"  class="btn" value="打印" />
        <c:if test="${paymentOrder.cbarStatus!=null}">
        <input type="button" name="checkBar" value="影像调阅" id="checkBar" class="btn">
        </c:if>
    </div>
    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<app:approvalTag billCode="${paymentOrder.cdocument}" typeId="77071"></app:approvalTag>
	</div>
    <div style="margin-top: 10px;">
    <div id="purchaseTab" class="easyui-tabs" style="width:850px;">
       <div title="订单信息" id="tabsa" style="overflow:auto; padding:20px;">
       <div>
       <form id="paymentOrderFormHeader">
        <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:825px;">
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
								<input class="searchinput" readonly name="cdocument" type="text" value="${paymentOrder.cdocument}" id="cdocument">
							</td>
							<td style="text-align: right; width: 90px;">扫描地:</td>
							<td>
								<input class="searchinput" readonly name="scanLocation" type="text" value="${paymentOrder.scanLocation}" id="scanLocation">
							</td>
							<td style="text-align: right" >税率:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='cc' type='text' value='<fmt:formatNumber type="number" value="${paymentOrder.taxRate*100}" maxFractionDigits="0"/>%' id='cc'>
								<input readonly class='searchinput' name='taxRate' type='hidden' value='${paymentOrder.taxRate}' id='taxRate'>
								<input readonly class='searchinput' name='taxRateCode' type='hidden' value='${paymentOrder.taxRateCode}' id='taxRateCode'>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;">申请人:</td>
							<td>
								<input class="searchinput" readonly name="applyUser" type="text" value="${paymentOrder.applyUser}" id="applyUser">
							</td>
							<td style="text-align: right; width: 90px;">供应商编码:</td>
							<td>
								<input readonly class='searchinput' name='supplierCode' type='text' value='${paymentOrder.supplierCode}' id='supplierCode'>
							</td>
							<td style="text-align: right; width: 90px;">币种:</td>
							<td>
								<input readonly class='searchinput' name='currencyCode' type='text' value='${paymentOrder.currencyCode}' id='currencyCode'>
							</td>
						</tr>
						<tr> 
							<td style="text-align: right;">公司编码:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='companyCode' type='text' value='${paymentOrder.companyCode}' id='companyCode'>					
	                        </td>
							<td style="text-align: right; width: 90px;">供应商名称:</td>
							<td>
								<input readonly class='searchinput' name='supplierName' type='text' value='${paymentOrder.supplierName}' id='supplierName'>
							</td>
							<td style="text-align: right" >汇率:</td>
							<td class="tdtext"><input readonly class='searchinput' name='exchangeRate' type='text' value='${paymentOrder.exchangeRate}' id='exchangeRate'></td>
						</tr>
						<tr> 
							<td style="text-align: right;">公司名称:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='companyName' type='text' value='${paymentOrder.companyName}' id='companyName'>	
	                        </td>
							<td style="text-align: right; width: 90px;">公司具体名称:</td>
							<td>
								<input readonly class='searchinput' name='companySpecificName' type='text' value='${paymentOrder.companySpecificName}' id='companySpecificName'>
							</td>
							<td style="text-align: right" >银行账号:</td>
							<td class="tdtext"><input readonly class='searchinput' name='bankNumber' type='text' value='${paymentOrder.bankNumber}' id='bankNumber'></td>
						</tr>
					<c:choose>
					    <c:when test="${paymentOrder.paymentType=='验收款'}">
						<tr> 
							<td style="text-align: right;">付款类型:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='paymentType' type='text' value='${paymentOrder.paymentType}' id='paymentType'>	
								<input readonly class='searchinput' name='nper' type='hidden' value='${paymentOrder.nper}' id='nper'>			
	                        </td>
							<td style="text-align: right; width: 90px;">是否有专票:</td>
							<td>
								<input readonly class='searchinput' name='invoice' type='text' value='${paymentOrder.invoice=="Y"?"是":"否"}' id='invoice'>
							</td>
							<td style="text-align: right" >开户行:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='bankName' type='text' value='${paymentOrder.bankName}' id='bankName'>
								<input readonly class='searchinput' name='bankCode' type='hidden' value='${paymentOrder.bankCode}' id='bankCode'>
							</td>
						</tr>
						<tr> 
							<td style="text-align: right;">付款比例(%):</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='paymentRatio' type='text' value='${paymentOrder.paymentRatio}' id='paymentRatio'>	
	                        </td>
							<td style="text-align: right; width: 90px;">发票号码:</td>
							<td>
								<input readonly class='searchinput' name='invoiceNumber' type='text' value='${paymentOrder.invoiceNumber}' id='invoiceNumber'>
							</td>
							<td style="text-align: right" >联行号:</td>
							<td class="tdtext"><input readonly class='searchinput' name='contactNumber' type='text' value='${paymentOrder.contactNumber}' id='contactNumber'></td>
						</tr>
						<tr> 
							<td style="text-align: right;">采购订单号:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='purchaseOrderId' type='text' value='${paymentOrder.purchaseOrderId}' id='purchaseOrderId'>					
	                        </td>
	                        <td style="text-align: right;">sap采购订单号:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='sapPurchaseOrderNumber' type='text' value='${purchaseOrder.purchaseOrderSapId}' id='sapPurchaseOrderNumber'>					
	                        </td>
	                        <td style="text-align: right; width: 90px;">是否最后一次付款:</td>
							<td>
								<input readonly class='searchinput' name='isLastTime' type='text' value='${paymentOrder.isLastTime=="Y"?"是":"否"}' id='isLastTime'>
							</td>
							<td class="tdtext"></td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;">申请事由:</td>
							<td>
								<input readonly class='searchinput' name='applyFor' type='text' value='${paymentOrder.applyFor}' id='applyFor'>
							</td>
						</tr>
						</c:when>
				    	<c:otherwise>
				    	<tr> 
							<td style="text-align: right;">付款类型:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='paymentType' type='text' value='${paymentOrder.paymentType}' id='paymentType'>	
								<input readonly class='searchinput' name='nper' type='hidden' value='${paymentOrder.nper}' id='nper'>			
	                        </td>
							<td style="text-align: right; width: 90px;">申请事由:</td>
							<td>
								<input readonly class='searchinput' name='applyFor' type='text' value='${paymentOrder.applyFor}' id='applyFor'>
							</td>
							<td style="text-align: right" >开户行:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='bankName' type='text' value='${paymentOrder.bankName}' id='bankName'>
								<input readonly class='searchinput' name='bankCode' type='hidden' value='${paymentOrder.bankCode}' id='bankCode'>
							</td>
						</tr>
						<tr> 
							<td style="text-align: right;">付款比例(%):</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='paymentRatio' type='text' value='${paymentOrder.paymentRatio}' id='paymentRatio'>	
	                        </td>
							<td style="text-align: right; width: 90px;">是否最后一次付款:</td>
							<td>
								<input readonly class='searchinput' name='isLastTime' type='text' value='${paymentOrder.isLastTime=="Y"?"是":"否"}' id='isLastTime'>
							</td>
							<td style="text-align: right" >联行号:</td>
							<td class="tdtext"><input readonly class='searchinput' name='contactNumber' type='text' value='${paymentOrder.contactNumber}' id='contactNumber'></td>
						</tr>
						<tr> 
							<td style="text-align: right;">采购订单号:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='purchaseOrderId' type='text' value='${paymentOrder.purchaseOrderId}' id='purchaseOrderId'>					
	                        </td>
	                        <td style="text-align: right;">sap采购订单号:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='sapPurchaseOrderNumber' type='text' value='${purchaseOrder.purchaseOrderSapId}' id='sapPurchaseOrderNumber'>					
	                        </td>
							<td class="tdtext"></td>
						</tr>
				    	</c:otherwise>
					</c:choose>
						</tbody>
					</table>
                </td>
            </tr>           
            </tbody>
        </table>
      	
      </form>
        
        </br>
        <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:825px;">
            <tbody>
            <tr>
                <td class="titlefontsize" align="left">
                <div class="imgtitle"></div>付款明细信息
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table id="Table10"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;width:825px;">
                        <tbody>
                        <tr>
                            <td class="tdtitle" style="text-align: right" colspan="2">
                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                    <div>
                                        <table id="table_detail" style="width:800px;" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
                                           
                                            <tr style="white-space:nowrap;">
                                                <th scope="col" class="tdpadding">序号</th>
                                                <th scope="col" class="tdpadding">主资产编码</th>
                                                <th scope="col" class="tdpadding">资产名称</th>
                                                <c:if test="${paymentOrder.paymentType=='验收款'}">
												 <th scope="col" class="tdpadding">应付金额</th>
												</c:if>
                                                <th scope="col" class="tdpadding">本次付款数量</th>
                                                <c:if test="${paymentOrder.paymentType=='验收款'}">
												<th scope="col" class="tdpadding">已付预付金额</th>
												<th scope="col" class="tdpadding">未付尾款金额</th>
												</c:if>
                                                <th scope="col" class="tdpadding">本次申请金额</th>
                                                <th scope="col" class="tdpadding">本次申请金额(本币)</th>
                                               
                       							<c:if test="${paymentOrder.paymentType=='预付款'}">
                                               		 <th scope="col" class="tdpadding"> 预计核销时间</th>
                                                </c:if>
                                                <c:if test="${paymentOrder.paymentType=='验收款'&&paymentOrder.isLastTerm!='Y'}">
                                               		<th scope="col" class="tdpadding">尾款付款时间</th>
                                                </c:if>
                                                <c:if test="${paymentOrder.paymentType=='尾款'&&paymentOrder.isLastTerm!='Y'}">
                                               		<th scope="col" class="tdpadding"> 下次尾款付款时间</th>
                                                </c:if>                        
                                            	
                                            </tr>
                                            <tbody id="tbody_detail">
                                            <c:forEach items="${paymentOrderDetailModels}" var="pd" varStatus="i">	
                                            <tr class="bodyTr" style="white-space:nowrap;">
												<td class='tdpadding'>${i.index+1}<input value='${pd.id}' type="hidden" name="purchaseDetailId" id="purchaseDetailId"></td>
												<td class='tdpadding'>${pd.mainAssetCode}</td>
												<td class="tdpadding">${pd.assetsName}</td>
												<c:if test="${paymentOrder.paymentType=='验收款'}">
												<td class="tdpadding" name="price">${pd.payablePrice}</td>
												</c:if>
												<td class="tdpadding">${pd.payCount}</td><!-- 本次付款数量 -->
												<c:if test="${paymentOrder.paymentType=='验收款'}">
												<td class="tdpadding" name="price">${pd.advancePrice}</td>
												<td class="tdpadding" name="price">${pd.finalPrice}</td>
												</c:if>
												<td class="tdpadding" name="price">${pd.payPrice}</td><!-- 本次付款金额 -->
												<td class="tdpadding" name="price">${pd.payPriceLocal}</td><!-- 本次付款金额本币 -->
												<c:if test="${paymentOrder.isLastTerm!='Y'||pd.paymentType=='预付款'}">
												<td class="tdpadding"><fmt:formatDate value='${pd.nextPayment}'  pattern='yyyy-MM-dd'/></td><!-- 下次付款时间 -->
												</c:if>
											</tr>
                                            </c:forEach>
                                            </tbody>
                                            <%-- <tr style="white-space:nowrap;">
												<td class='tdpadding'>合计</td>
												<td class="tdpadding"></td>
												<td class="tdpadding"></td>
												<td class="tdpadding"></td><!-- 订单数量 -->
												<td class="tdpadding"></td><!-- 订单单价 -->
												<td class="tdpadding"><input readonly class="rightalign" type="text" name="sumpurchasePrice" id="sumpurchasePrice" size="5"></td><!-- 订单总价 -->
												<td class="tdpadding"><input class="rightalign" type="text" name="sumpayablePrice" id="sumpayablePrice" size="5" readonly></td><!-- 应付金额 -->
												<c:if test="${paymentOrder.paymentType=='验收款'}">
                                               		 <td scope="col" class="tdpadding"><input class="rightalign" type="text" name="sumadvancePrice" id="sumadvancePrice" size="5" readonly></td><!-- 已付预付金额 -->
                                               		 <td scope="col" class="tdpadding"><input class="rightalign" type="text" name="sumfinalPrice" id="sumfinalPrice" size="5" readonly></td><!-- 未付尾款金额 -->
                                                	 <td class="tdpadding"></td><!-- 已验收数量 -->
                                                </c:if>
												<td class="tdpadding"></td> <!-- 已付款数量 -->
												<td class="tdpadding"><input class="rightalign"  readonly type="text" name="sumpaidMoney" id="sumpaidMoney" size="5" ></td><!-- 已付款金额 -->
												<td class="tdpadding"></td><!-- 上期付款数量 -->
												<td class="tdpadding"></td><!-- 本次付款数量 -->
												<td class="tdpadding"><input class="rightalign"  type="text" name="sumpayPrice" id="sumpayPrice" size="5" readonly></td><!-- 本次付款金额 -->
												<td class="tdpadding"><input class="rightalign"  type="text" name="sumpayPriceLocal" id="sumpayPriceLocal" size="5" readonly></td><!-- 本次付款金额本币 -->
												<td class="tdpadding"></td><!-- 下次付款时间 -->
											</tr> --%>
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
			<table cellpadding="0" cellspacing="0" border="0"  style="width:810px;">
				   <tbody>
				   <tr>
					   <td class="titlefontsize" align="left" style="width: 150px">
						   <div class="imgtitle"></div>
						   附件
					   </td>
				   </tr>
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
								       <c:forEach items="${paymentOrder.listFile}" var="assetPic" >
									   
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
				   </tbody>
			   </table>


        <div id="budgetWindow" name="" class="easyui-window" title="发票检验" data-options="iconCls:'icon-save'" style="width:750px;height:380px;border:0px;" closed="true">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'" style="border:0px;">

			<form id="invoiceForm">
				   <div id="backDiv" >
				   		<input type="button" id="back" value="返回">
				   </div>
				<table class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="0">
				<c:choose>
				    <c:when test="${invoiceInspection.isComingSAP=='Y'}"> 
					    <tr style="text-align: left">
						<td >发票日期</td>
						<td name=""><input type="text" id="invoiceDate" name="invoiceDate" value="<fmt:formatDate value='${invoiceInspection.invoiceDate}'  pattern='yyyy-MM-dd'/>" style="width: 145px;height: 22px;" class="Wdate" onclick="WdatePicker({skin:'ext'});" disabled="disabled"></td>
						<td>记账日期</td>
						<td><input name="accountingDate" id="accountingDate" type="text" value="<fmt:formatDate value='${invoiceInspection.invoiceDate}'  pattern='yyyy-MM-dd'/>" style="width: 145px;height: 22px;" class="Wdate" onclick="WdatePicker({skin:'ext'});" disabled="disabled"></td>
						</tr>
				    </c:when>
			    </c:choose>
				
				<tr style="text-align: left">
				<td>税率</td>	
				<td><input readonly  name='taxRate' type='text' value='${paymentOrder.taxRate}' id='taxRate'></td>
				<td>币种</td>	
				<td><input readonly  name='currencyCode' type='text' value='${paymentOrder.currencyCode}' id='currencyCode'></td>
				</tr>
				<tr style="text-align: left">
				<tr style="text-align: left">
				<td>汇率</td>
				<td><input readonly type="text" name="exchangeRate" id="exchangeRate" value='${paymentOrder.exchangeRate}'></td>
				<td>金额</td>	
				<td><input type="text" name="invoiceMoney" value="${invoiceInspection.invoiceMoney}" id="invoiceMoney" readonly="readonly"></td>
				</tr>
				<td>税额</td>
				<td><input name="invoiceTax" id="invoiceTax" value="${invoiceInspection.invoiceTax}" readonly="readonly"/></td>
				<td>净价</td>	
				<td><input type="text" name="netPrice" id="netPrice" value="${invoiceInspection.netPrice}" readonly="readonly"></td>
				</tr>
				<tr style="text-align: left">
		    	<td>文本</td>	
				<td><input type="text" name="invoiceText" id="invoiceText" value="${invoiceInspection.invoiceText}" readonly="readonly"></td>

			    <c:choose>
				    <c:when test="${invoiceInspection.isManualCertificate=='Y'}"> 
				    <td>是否手工凭证</td>	
					<td>
					<select style="width:150px;height:22px;" id="isManualCertificate" name="isManualCertificate" onchange="changeType()" disabled="disabled">
						
						        <option value="Y" selected="selected">是</option>
								<option value="N" >否</option>     
					 </select></td>
					 </c:when>
					 <c:when test="${invoiceInspection.isManualCertificate=='N'}"> 
					 <td>是否手工凭证</td>
					<td>
					<select style="width:150px;height:22px;" id="isManualCertificate" name="isManualCertificate" onchange="changeType()" disabled="disabled">
						
						        <option value="Y" >是</option>
								<option value="N" selected="selected">否</option>     
					 </select></td>
					 </c:when>
				 </c:choose>	
				</tr>
				<tr style="text-align: left">
				<td>采购订单号</td>	
				<td><input type="text" name="purchaseOrderNumber" id="purchaseOrderNumber" value='${invoiceInspection.purchaseOrderNumber}' readonly="readonly"></td>
				<td>sap采购订单号</td>
				<td><input type="text" name="sapPurchaseOrderNumber" id="sapPurchaseOrderNumber" value='${invoiceInspection.sapPurchaseOrderNumber}' readonly="readonly"></td>
				</tr>
				<input type="hidden" name="isComingSAP" id="isComingSAP"  value='${invoiceInspection.isComingSAP}'/>
				<%-- <fmt:formatDate value='${demandOrder.applyDate}'  pattern='yyyy'/> --%>
				</table>
					  <table class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="0">
						<tbody>
								<tr>
									<th scope="col">主编码</th>
									<th scope="col">次编码</th>
									<th scope="col">物料凭证号</th>
									<th scope="col">会计凭证号</th>
									<th scope="col">物料凭证年度</th>
								</tr>
	
								<c:forEach items="${invoiceInspectionDetails}" var="invoiceInspectionDetail" varStatus="status">
									<tr style="white-space: nowrap;">
										<td>${invoiceInspectionDetail.sapMainCode}</td>
										<td>${invoiceInspectionDetail.sapSecCode}</td>
										<td>${invoiceInspectionDetail.epMaterialDocument}</td>
										<td>${invoiceInspectionDetail.eBelnr}</td>
										<td>${invoiceInspectionDetail.epMatDocumentYear}</td>
									</tr>
								</c:forEach>
						</tbody>
					</table>
			</form>

					</div>

			</div>
		</div>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
               <tbody>
               <tr>
                   <td class="titlefontsize" align="left" style="width: 150px">
                       <div class="imgtitle"></div>
                       		审批历史
                   </td>
                   <td></td>
               </tr>

               <c:if test="${(paymentOrder.prepaymentDocumentNumber!=''&&paymentOrder.prepaymentDocumentNumber!=null)&&paymentOrder.paymentType=='预付款'}">
               <div class="trDiv">
			               <tr>
			               	 <!--   <td colspan="1">
			               	   </td>-->
				               <td style="text-align:right;font-size: 12px;">是否手工凭证:</td>
				               <td style="text-align:left;">
				               <select style="width:150px;height:25px;" id="isManualPrepaymentDocument" onchange="changeEvent()" disabled="disabled">
			               		   <c:if test="${paymentOrder.isManualPrepaymentDocument=='Y'}">
	                               <option value="Y" selected="selected">是</option>
								   <option value="N" >否</option>
	                               </c:if>
	                               <c:if test="${paymentOrder.isManualPrepaymentDocument=='N'}">
		                                <option value="Y" >是</option>
										<option value="N" selected="selected">否</option>
	                               </c:if>
				               </select>
				               </td>
				               <td style="text-align:right;font-size: 12px;">预付凭证号:</td>
						       <td style="text-align:left;"><input id="prepaymentDocumentNumber" type="text" value="${paymentOrder.prepaymentDocumentNumber}" class="my-text" disabled="disabled" /></td>
						    </tr>
		       </div>
		       </c:if>
<%--                <c:if test="${(paymentOrder.receiptVoucherNumber!=''&&paymentOrder.prepaymentDocumentNumber!=null)&&paymentOrder.paymentType=='验收款'}">
	           <div class="trDiv" id="voucherDiv">
			               <tr>
				               <td style="text-align:right;font-size: 12px;">应付凭证号:</td>
						       <td style="text-align:left;"><input type="text" class="my-text" readonly="readonly" /><input style="width:80px;height:25px;" type="button"  value="发票校验" ></td>
				       		</tr>
				       		<tr>
			               	   <td style="text-align:right;font-size: 12px;">是否手工凭证:</td>
			               	   <td style="text-align:left;">
				               <select style="width:150px;height:25px;" >
				               		<option value="Y" >是</option>
									<option value="N" selected="selected">否</option>
				               </select>
				               </td>
				               <td style="text-align:right;font-size: 12px;">验收款凭证号:</td>
							   <td style="text-align:left;"><input type="text" class="my-text" readonly="readonly" /><input style="width:100px;height:25px;" type="button"  value="生成验收凭证" ></td>
		                     </tr>
		       </div>
		       </c:if> --%>
		       <c:if test="${paymentOrder.paymentType=='验收款'&&invoiceInspection.isComingSAP=='Y'&&paymentOrder.paymentRatio==100}">
	           <div class="trDiv">
			               	<tr>
				               <td style="text-align:right;font-size: 12px;">会计凭证:</td>
						       <td style="text-align:left;"><input type="text" class="my-text" id="paymentVoucherNumber" value="${paymentOrder.paymentVoucherNumber}" disabled="disabled" /></td>
						       <td style="text-align:right;font-size: 12px;">发票凭证:</td>
						       <td style="text-align:left;"><input type="text" class="my-text" id="invoiceNo" value="${paymentOrder.invoiceNo}" disabled="disabled" /><input style="width:100px;height:25px;" type="button" id="invoiceCheck" value="发票校验" ></td>
				       		</tr>
		       </div>
		       </c:if>
		       <c:if test="${paymentOrder.paymentType=='验收款'&&paymentOrder.isComingSAP=='Y'&&paymentOrder.paymentRatio!=100}">
	           <div class="trDiv">
			               	<tr>
				               <td style="text-align:right;font-size: 12px;">会计凭证:</td>
						       <td style="text-align:left;"><input type="text" class="my-text" id="paymentVoucherNumber" value="${paymentOrder.paymentVoucherNumber}" disabled="disabled" /></td>
						       <td style="text-align:right;font-size: 12px;">发票凭证:</td>
						       <td style="text-align:left;"><input type="text" class="my-text" id="invoiceNo" value="${paymentOrder.invoiceNo}" disabled="disabled" /><input style="width:100px;height:25px;" type="button" id="invoiceCheck" value="发票校验" ></td>
				       		</tr>
				       		<tr>
			               	   <td style="text-align:right;font-size: 12px;">是否手工凭证:</td>
			               	   <td style="text-align:left;">
				               <select style="width:150px;height:25px;" disabled="disabled" >
				               		<option value="Y" >是</option>
									<option value="N" selected="selected">否</option>
				               </select>
				               </td>
				               <td style="text-align:right;font-size: 12px;">验收凭证:</td>
							   <td style="text-align:left;"><input type="text" class="my-text" id="receiptVoucherNumber" value="${paymentOrder.cvoucheId}" disabled="disabled" /><input id="createReceipt" style="width:100px;height:25px;" type="button"  value="生成验收凭证" disabled="disabled"></td>
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
    	</div>
        </div>
       <c:if test="${paymentOrder.paymentType=='验收款'}">
	   <div title="预付冲减" data-options="closable:false" style="overflow:auto;padding:20px;">
	   	<div>
	   		 </br>
        <table id="Table18" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:825px;">
            <tbody>
            <tr>
                <td class="titlefontsize" align="left">
                <div class="imgtitle"></div>预付冲减
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table id="Table15"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;width:825px;">
                        <tbody>
                        <tr>
                            <td class="tdtitle" style="text-align: right" colspan="2">
                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                    <div>
                                        <table id="table_add_detail" style="width:800px;" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
                                           
                                            <tr style="white-space:nowrap;">
                                                <th scope="col" class="tdpadding">凭证号</th>
                                                <th scope="col" class="tdpadding">凭证日期</th>
                                                <th scope="col" class="tdpadding">公司代码</th>
                                                <th scope="col" class="tdpadding">会计年度</th>
                                                <th scope="col" class="tdpadding">记账期间</th>
                                                <th scope="col" class="tdpadding">记账码</th>
                                                <th scope="col" class="tdpadding">科目代码</th>
                                                <th scope="col" class="tdpadding">科目名称</th>
                                                <th scope="col" class="tdpadding">特别总账标识</th>
                                                <th scope="col" class="tdpadding">金额</th>
                                                <th scope="col" class="tdpadding">成本中心号</th>
                                                <th scope="col" class="tdpadding">冲减预付金额</th>
                                            </tr>
                                            <tbody id="tbody_detail_detail">
                                            <c:forEach items="${paymentPaymentMappingModels}" var="pd" varStatus="i">	
                                            <tr class="bodyTr" style="white-space:nowrap;">
                                            	<td class='tdpadding'>${pd.cpassDocuments}</td>
												<td class='tdpadding'><fmt:formatDate value='${pd.pstngDate}'  pattern='yyyy-MM-dd'/>
													<input value='${paymentOrder.cdocument}' type="hidden" name="paymentOrderId" id="paymentOrderId">
													<input value='${pd.cdocumentsNo}' type="hidden" name="purchaseDetailId" id="purchaseDetailId">
												</td>
												<td class="tdpadding">${pd.compCode}</td>
												<td class="tdpadding">${pd.iyear}</td>
												<td class="tdpadding">${pd.imonth}</td>
												<td class="tdpadding">${pd.caccountCode}</td>
												<td class="tdpadding">${pd.csubjectCode}</td>
												<td class="tdpadding">${pd.csubjectName}</td>
												<td class="tdpadding">${pd.cspCode}</td>
												<td class="tdpadding" name="price">${pd.iamount}</td>
												<td class="tdpadding">${pd.cdepCode}</td>
												<td class="tdpadding" name="price">${pd.payPrice}</td>
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
                </td>
               </tr>
            </tbody>
        </table>
        
	   	</div>
	   </div>
    
       </c:if>
    </div>

<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
<link href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css" type="text/css" rel="stylesheet" />
<link href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css" type="text/css" rel="stylesheet" /> --%>
<%-- <script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script> --%>
<%-- <script src="${staticURL}/Scripts/Purchase/PaymentOrderAdd.js" type="text/javascript"></script> --%>
<script type="text/javascript">
	var dg = frameElement.lhgDG;//定义dg 关闭需要
	 $(function(){
	    	$("td[name='price']").each(function(){
	    		if($(this).html()!=""){
	    			$(this).html(fmoney($(this).html(),2));
	    		};
	    	});
	    })
	$("#checkBar").click(function(){
		var cdocument = $("#cdocument").val();
		 $.ajax({
	           url: "${dynamicURL}/payment/api/checkBar",
	           type: "POST",
	          // contentType: "application/json",
	           data:{cdocument : cdocument},
	           success: function (data) {
	        	   if (data.success){
	        		   window.open(data.successMessage);
	        	   } else {
						alert(data.errorMessages.join());
					}

	           }
	       }); 
	});
	//发票检验弹窗
	$("#invoiceCheck").click(function(){
		
		var top = "300px";
		var left = "50px";
		$('#budgetWindow').window('open').window('resize',{width:'650px',height:'330px',top: top,left:left});
	});
	$("#back").click(function(){
		$('#budgetWindow').window('close');
	});
	/**
	    * 数字格式转换成千分位
	    */
	    function fmoney(s, n)

	    {
	        n = n > 0 && n <= 20 ? n : 2;
	        s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
	        var l = s.split('.') [0].split('').reverse(),
	            r = s.split('.') [1];
	        var  t = '';
	        for (var i = 0; i < l.length; i++)
	        {
	            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
	        }
	        return t.split('').reverse().join('') + '.' + r;

	    }
    function stamp(id, title){
        var lurl = "${dynamicURL}/payment/api/printOrder?id=" + id+"&typeId=77071";
        window.open(lurl);
        printSeal();
    }
</script>
</body>
</html>