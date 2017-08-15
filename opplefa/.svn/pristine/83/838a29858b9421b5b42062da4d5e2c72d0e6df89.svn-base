<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                付款管理</div>
    </div>
    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
        <input type="button" name="btnSave" value="提交" onclick="" id="btnSave" class="btn">
        <input type="button" name="btnSaveDraft" value="保存草稿" onclick="" id="btnSaveDraft" class="btn">
        <input type="button" name="btnCancel" value="返回" id="btnCancel" class="btn" onclick="dg.cancel()">
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
								<input class="searchinput" readonly name="officeLocation" type="hidden" value="${purchaseOrder.officeLocation}" id="officeLocation">
								<input class="searchinput" readonly name="iamoney" type="hidden" value="${paymentOrder.iamoney}" id="iamoney">
							</td>
							<td style="text-align: right; width: 90px;">扫描地:</td>
							<td>
								<select class="searchselect" name="scanLocation" id="scanLocation">
									<c:forEach items="${scanLocations}" var="scanLocation">
									<c:choose>
									  <c:when test="${scanLocation.clocationname==paymentOrder.scanLocation}">
									  <option value="${scanLocation.clocationname}" selected="selected" >${scanLocation.clocationname}</option>
									  </c:when>
									  <c:otherwise>
									  <option value="${scanLocation.clocationname}">${scanLocation.clocationname}</option>
									  </c:otherwise>
									</c:choose>
										
									</c:forEach>
	                            </select>
							</td>
							<td style="text-align: right" >税率:</td>
							<td class="tdtext">
								<input readonly='readonly' class='searchinput' name='taxRate' type='text' value='${paymentOrder.taxRate}' id='taxRate'>
								<input readonly='readonly' class='searchinput' name='taxRateCode' type='hidden' value='${paymentOrder.taxRateCode}' id='taxRateCode'>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;">申请人:</td>
							<td>
								<input class="searchinput" readonly name="applyUser" type="text" value="${paymentOrder.applyUser}" id="applyUser">
							</td>
							<td style="text-align: right; width: 90px;">供应商编码:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='supplierCode' type='text' value='${paymentOrder.supplierCode}' id='supplierCode'>
							</td>
							<td style="text-align: right; width: 90px;">币种:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='currencyCode' type='text' value='${paymentOrder.currencyCode}' id='currencyCode'>
							</td>
						</tr>
						<tr> 
							<td style="text-align: right;">公司编码:</td>
							<td class="tdtext">
								<input readonly='readonly' class='searchinput' name='companyCode' type='text' value='${paymentOrder.companyCode}' id='companyCode'>					
	                        </td>
							<td style="text-align: right; width: 90px;">供应商名称:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='supplierName' type='text' value='${paymentOrder.supplierName}' id='supplierName'>
							</td>
							<td style="text-align: right" >汇率:</td>
							<td class="tdtext"><input readonly='readonly' class='searchinput' name='exchangeRate' type='text' value='${paymentOrder.exchangeRate}' id='exchangeRate'></td>
						</tr>
						<tr> 
							<td style="text-align: right;">公司名称:</td>
							<td class="tdtext">
								<input readonly='readonly' class='searchinput' name='companyName' type='text' value='${paymentOrder.companyName}' id='companyName'>	
	                        </td>
							<td style="text-align: right; width: 90px;">公司具体名称:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='companySpecificName' type='text' value='${paymentOrder.companySpecificName}' id='companySpecificName'>
							</td>
							<td style="text-align: right" >银行账号:</td>
							<td class="tdtext"><input readonly='readonly' class='searchinput' name='bankNumber' type='text' value='${paymentOrder.bankNumber}' id='bankNumber'></td>
						</tr>
						<tr> 
							<td style="text-align: right;">付款类型:</td>
							<td class="tdtext">
								<input readonly='readonly' class='searchinput' name='paymentType' type='text' value='${paymentOrder.paymentType}' id='paymentType'>	
								<input readonly='readonly' class='searchinput' name='nper' type='hidden' value='${paymentOrder.nper}' id='nper'>			
	                        </td>
							<td style="text-align: right;">采购订单号:</td>
							<td class="tdtext">
								<input readonly='readonly' class='searchinput' name='purchaseOrderId' type='text' value='${paymentOrder.purchaseOrderId}' id='purchaseOrderId'>					
	                        </td>
							<td style="text-align: right" >开户行:</td>
							<td class="tdtext">
								<input readonly='readonly' class='searchinput' name='bankName' type='text' value='${paymentOrder.bankName}' id='bankName'>
								<input readonly='readonly' class='searchinput' name='bankCode' type='hidden' value='${paymentOrder.bankCode}' id='bankCode'>
							</td>
						</tr>
						<c:choose>
						    <c:when test="${paymentOrder.paymentType=='验收款'}">
							    <tr> 
								<td style="text-align: right;">付款比例(%):</td>
								<td class="tdtext">
									<input readonly='readonly' class='searchinput' name='paymentRatio' type='text' value='${paymentOrder.paymentRatio}' id='paymentRatio'>	
									<input readonly='readonly' class='searchinput' name='' type='hidden' value='${paymentTerms[1].paymentRatio}' id='lastPaymentRatio'>	
		                        </td>
		                        <td style="text-align: right; width: 90px;"><span style="color: red">*</span>是否有专票:</td>
								<td>
									<select class="searchselect" name="invoice" id="invoice" onchange="isInvoice()">
											<option value="">--请选择--</option>
											<option value="Y" ${paymentOrder.invoice == 'Y' ? "selected" : ""}>是</option>
											<option value="N"${paymentOrder.invoice == 'N' ? "selected" : ""}>否</option>
		                            </select>
								</td>
								<td style="text-align: right" >联行号:</td>
								<td class="tdtext"><input readonly='readonly' class='searchinput' name='contactNumber' type='text' value='${paymentOrder.contactNumber}' id='contactNumber'></td>
								</tr>
								<tr>
								<td style="text-align: right"><font color="red" >*</font>申请人部门:</td>
								<td class="tdtext">
									<input class="searchinput" readonly name="applyDepartment" type="text" value="${paymentOrder.applyDepartment}" id="txtApplyDepartment">
									<input class="searchinput" readonly name="applyDepartmentCode" type="hidden" value="${paymentOrder.applyDepartmentCode}" id="txtApplyDepartment">
								</td>
								<td style="text-align: right;">发票号码:</td>
								<td class="tdtext"><input readonly='readonly' class='searchinput' name='invoiceNumber' type='text' value='${paymentOrder.invoiceNumber}' id='invoiceNumber' onkeyup="value=value.replace(/[^\d]/g,'')"></td>
		                        <td style="text-align: right; width: 90px;"><span style="color: red">*</span>是否最后一次付款:</td>
								<td>
									<select class="searchselect" name="isLastTime" id="isLastTime">
											<option value="">--请选择--</option>
											<option value="Y" ${paymentOrder.isLastTime == 'Y' ? "selected" : ""}>是</option>
											<option value="N" ${paymentOrder.isLastTime == 'N' ? "selected" : ""}>否</option>
		                            </select>
								</td>
								</tr>
								<tr>
								<td style="text-align: right; width: 90px;"><span style="color: red">*</span>申请事由:</td>
								<td>
									<input  class='searchinput' name='applyFor' type='text' value='${paymentOrder.applyFor}' id='applyFor'>
								</td>
								</tr>
						    </c:when>
						    <c:otherwise>
						    	<tr> 
								<td style="text-align: right;">付款比例(%):</td>
								<td class="tdtext">
									<input readonly='readonly' class='searchinput' name='paymentRatio' type='text' value='${paymentOrder.paymentRatio}' id='paymentRatio'>	
									<input readonly='readonly' class='searchinput' name='' type='hidden' value='${paymentTerms[1].paymentRatio}' id='lastPaymentRatio'>	
		                        </td>
		                        <td style="text-align: right; width: 90px;"><span style="color: red">*</span>申请事由:</td>
								<td>
									<input  class='searchinput' name='applyFor' type='text' value='${paymentOrder.applyFor}' id='applyFor'>
								</td>
								<td style="text-align: right" >联行号:</td>
								<td class="tdtext"><input readonly='readonly' class='searchinput' name='contactNumber' type='text' value='${paymentOrder.contactNumber}' id='contactNumber'></td>
								</tr>
								<tr>
								<td style="text-align: right"><font color="red" >*</font>申请人部门:</td>
								<td class="tdtext">
									<input class="searchinput" readonly name="applyDepartment" type="text" value="${paymentOrder.applyDepartment}" id="txtApplyDepartment">
									<input class="searchinput" readonly name="applyDepartmentCode" type="hidden" value="${paymentOrder.applyDepartmentCode}" id="txtApplyDepartment">
								</td>
		                        <td style="text-align: right; width: 90px;"><span style="color: red">*</span>是否最后一次付款:</td>
								<td>
									<select class="searchselect" name="isLastTime" id="isLastTime">
											<option value="">--请选择--</option>
											<option value="Y" ${paymentOrder.isLastTime == 'Y' ? "selected" : ""}>是</option>
											<option value="N" ${paymentOrder.isLastTime == 'N' ? "selected" : ""}>否</option>
		                            </select>
								</td>
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
                                        <table id="table_detail" style="width:825px;" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
                                           
                                            <tr style="white-space:nowrap;">
                                                <th scope="col" class="tdpadding">序号</th>
                                                <th scope="col" class="tdpadding">主资产编码</th>
                                                <th scope="col" class="tdpadding">资产名称</th>
                                                <th scope="col" class="tdpadding">订单数量</th>
                                                <th scope="col" class="tdpadding">订单单价</th>
                                                <th scope="col" class="tdpadding">订单总价</th>
                                                <th scope="col" class="tdpadding"><span style="color: red">*</span>本次付款数量</th>
                                                <th scope="col" class="tdpadding"><span style="color: red">*</span>本次申请金额</th>
                                                <th scope="col" class="tdpadding">应付金额</th>
                                                <c:if test="${paymentTerms[0].paymentType=='验收款'}">
                                               		 <th scope="col" class="tdpadding">已付预付款金额</th>
                                               		 <th scope="col" class="tdpadding">未付尾款金额</th>
                                                	<th scope="col" class="tdpadding">
                                               		 	已验收数量
                                               		</th>
                                                </c:if>
                                                <th scope="col" class="tdpadding">
	                                                <c:if test="${paymentTerms[0].paymentType=='预付款'}">
	                                               		 已付预付款数量
	                                                </c:if>
	                                                <c:if test="${paymentTerms[0].paymentType=='验收款'}">
	                                               		 已付验收款数量
	                                                </c:if>
	                                                <c:if test="${paymentTerms[0].paymentType=='尾款'}">
	                                               		 已付尾款数量
	                                                </c:if>
                                                </th>
                                                <th scope="col" class="tdpadding">
                        							<c:if test="${paymentTerms[0].paymentType=='预付款'}">
	                                               		 已付预付款金额
	                                                </c:if>
	                                                <c:if test="${paymentTerms[0].paymentType=='验收款'}">
	                                               		 已付验收款金额
	                                                </c:if>
	                                                <c:if test="${paymentTerms[0].paymentType=='尾款'}">
	                                               		 已付尾款金额
	                                                </c:if>                        
                                            	</th>
                                                <th scope="col" class="tdpadding">上期付款数量</th>
                                                
                                                <th scope="col" class="tdpadding">本次申请金额(本币)</th>
                                                
                       							<c:if test="${paymentTerms[0].paymentType=='预付款'}">
                                               		<th scope="col" class="tdpadding"> 预计核销时间</th>
                                                </c:if>
                                                <c:if test="${paymentTerms[0].paymentType=='验收款'&&paymentOrder.isLastTerm!='Y'}">
                                               		<th scope="col" class="tdpadding">尾款付款时间</th>
                                                </c:if>
                                                <c:if test="${paymentTerms[0].paymentType=='尾款'&&paymentOrder.isLastTerm!='Y'}">
                                               		 <th scope="col" class="tdpadding">下次尾款付款时间</th>
                                                </c:if>                        
                                            	
                                            </tr>
                                            <tbody id="tbody_detail">
                                            <c:forEach items="${paymentOrderDetailModels}" var="pd" varStatus="i">	
                                            <tr class="bodyTr" style="white-space:nowrap;">
												<td class='tdpadding'>${i.index+1}<input value='${pd.id}' type="hidden" name="purchaseDetailId" id="purchaseDetailId"></td>
												<td class='tdpadding'><input value='${pd.mainAssetCode}' type="text" readonly name="mainAssetCode" id="mainAssetCode"></td>
												<td class="tdpadding"><input value='${pd.assetsName}' type="text" readonly name="assetsName" id="assetsName"></td>
												<td class="tdpadding"><input value='${pd.applyCount}' readonly class="rightalign" type="text" name="applyCount" id="applyCount" size="5"></td><!-- 订单数量 -->
												<td class="tdpadding"><input value='${pd.purchaseUnitPrice}' readonly class="rightalign" type="text" name="purchaseUnitPrice" id="purchaseUnitPrice" size="5"></td><!-- 订单单价 -->
												<td class="tdpadding"><input value='${pd.purchasePrice}' readonly class="rightalign" type="text" name="purchasePrice" id="purchasePrice" size="5"></td><!-- 订单总价 -->
												<td class="tdpadding">
												<input class="rightalign" value="${pd.payCount}" type="text" name="payCount" id="payCount" size="5" onkeyup="value=value.replace(/[^\d]/g,'')" >
												<input class="rightalign" value="${pd.payCount}" name="oldCount" id="oldCount" size="5" type="hidden">
												</td><!-- 本次付款数量 -->
												<td class="tdpadding">
												<input class="rightalign" value="${pd.payPrice}" type="text" name="payPrice" id="payPrice" size="5" onkeyup="value=value.replace(/[^\d.]/g,'')">
												<input class="rightalign" value="${pd.payPrice}" name="oldPrice" id="oldPrice" size="5" type="hidden">
												</td><!-- 本次付款金额 -->
												<td class="tdpadding"><input class="rightalign" type="text" name="payablePrice" id="payablePrice" size="5" readonly></td><!-- 应付金额 -->
												<c:if test="${paymentTerms[0].paymentType=='验收款'}">
                                               		 <td scope="col" class="tdpadding"><input class="rightalign" type="text" name="advancePrice" id="advancePrice" size="5" readonly></td><!-- 已付预付金额 -->
                                               		 <td scope="col" class="tdpadding"><input class="rightalign" type="text" name="finalPrice" id="finalPrice" size="5" readonly></td><!-- 未付尾款金额 -->
                                                	 <td class="tdpadding"><input class="rightalign" value="${pd.checkCount}" readonly type="text" name="checkCount" id="checkCount" size="5"></td><!-- 已验收数量 -->
                                                </c:if>
												<td class="tdpadding"><input class="rightalign" value="${pd.paidCount}" readonly type="text" name="paidCount" id="paidCount" size="5"></td> <!-- 已付款数量 -->
												<td class="tdpadding"><input class="rightalign" value="${pd.paidMoney}" readonly type="text" name="paidMoney" id="paidMoney" size="5"></td><!-- 已付款金额 -->
												<td class="tdpadding"><input class="rightalign" value="${pd.lastCount}" type="text" name="lastCount" id="lastCount" size="5" readonly></td><!-- 上期付款数量 -->
												<td class="tdpadding"><input class="rightalign" value="" type="text" name="payPriceLocal" id="payPriceLocal" size="5" readonly></td><!-- 本次付款金额本币 -->
												<c:if test="${paymentOrder.isLastTerm!='Y'||paymentTerms[0].paymentType=='预付款'}">
												<td class="tdpadding"><input name="nextPayment" value="<fmt:formatDate value='${pd.nextPayment}'  pattern='yyyy-MM-dd'/>" type="text" id="nextPayment" class="Wdate" onclick="WdatePicker({skin:'ext'});"></td><!-- 下次付款时间 -->
												</c:if>
											</tr>
                                            </c:forEach>
                                            </tbody>
                                            <tr style="white-space:nowrap;">
												<td class='tdpadding'>合计</td>
												<td class="tdpadding"></td>
												<td class="tdpadding"></td>
												<td class="tdpadding"></td><!-- 订单数量 -->
												<td class="tdpadding"></td><!-- 订单单价 -->
												<td class="tdpadding"><input readonly class="rightalign" type="text" name="sumpurchasePrice" id="sumpurchasePrice" size="5"></td><!-- 订单总价 -->
												<td class="tdpadding"></td><!-- 本次付款数量 -->
												<td class="tdpadding"><input class="rightalign"  type="text" name="sumpayPrice" id="sumpayPrice" size="5" readonly></td><!-- 本次付款金额 -->
												<td class="tdpadding"><input class="rightalign" type="text" name="sumpayablePrice" id="sumpayablePrice" size="5" readonly></td><!-- 应付金额 -->
												<c:if test="${paymentTerms[0].paymentType=='验收款'}">
                                               		 <td scope="col" class="tdpadding"><input class="rightalign" type="text" name="sumadvancePrice" id="sumadvancePrice" size="5" readonly></td><!-- 已付预付金额 -->
                                               		 <td scope="col" class="tdpadding"><input class="rightalign" type="text" name="sumfinalPrice" id="sumfinalPrice" size="5" readonly></td><!-- 未付尾款金额 -->
                                                	 <td class="tdpadding"></td><!-- 已验收数量 -->
                                                </c:if>
												<td class="tdpadding"></td> <!-- 已付款数量 -->
												<td class="tdpadding"><input class="rightalign"  readonly type="text" name="sumpaidMoney" id="sumpaidMoney" size="5" ></td><!-- 已付款金额 -->
												<td class="tdpadding"></td><!-- 上期付款数量 -->
												<td class="tdpadding"><input class="rightalign"  type="text" name="sumpayPriceLocal" id="sumpayPriceLocal" size="5" readonly></td><!-- 本次付款金额本币 -->
												<c:if test="${paymentOrder.isLastTerm!='Y'||paymentTerms[0].paymentType=='预付款'}">
												<td class="tdpadding"></td><!-- 下次付款时间 -->
												</c:if>
											</tr>
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
        <div style="height: 50px;margin-top: 10px;">
			<table cellpadding="0" cellspacing="0" border="0"  style="width:810px;">
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
								       <c:forEach items="${paymentOrder.listFile}" var="assetPic" >
									   
										<tr>
											<td><input type="checkbox"> </td>
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
		</div>
        </div>
        
       <c:if test="${paymentTerms[0].paymentType=='验收款'}">
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
                                        <table id="table_add_detail" style="width:825px;" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
                                           
                                            <tr style="white-space:nowrap;">
                                                <th scope="col">
                                                    <input id="gvDataList_CheckAll_Add" type="checkbox" name="gvDataList$ctl01$CheckAll"
                                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_add_detail');"/>
                                                </th>
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
                                            <c:choose>
                                                <c:when test="${pd.isChecked=='Y'}">
                                                <td class='tdpadding'><input id='chkBolDel' type='checkbox' name='gvdata' checked="checked"></td>
                                                </c:when>
                                                <c:otherwise>
                                                <td class='tdpadding'><input id='chkBolDel' type='checkbox' name='gvdata' ></td>
                                                </c:otherwise>
                                            </c:choose>
                                            	<td class='tdpadding'><input value='${pd.cpassDocuments}' type="text" readonly name="cpassDocuments" id="cpassDocuments" size='10'></td>
												<td class='tdpadding'>
													<input value="<fmt:formatDate value='${pd.pstngDate}'  pattern='yyyy-MM-dd'/>" type="text" readonly name="pstngDate" id="pstngDate" size='10'>
													<input value='${paymentOrder.cdocument}' type="hidden" name="paymentOrderId" id="paymentOrderId">
 													<input value='${pd.cdocumentsNo}' type="hidden" name="cdocumentsNo" id="cdocumentsNo">
<%--													<input value='${pd.id}' type="hidden" name="paymentDetailId" id="paymentDetailId"><!-- 上次付款ID 被冲销的ID -->
													<input value='${pd.itemnoAcc}' type="hidden" name="itemnoAcc" id="itemnoAcc"> --%>
												</td>
												<td class="tdpadding"><input class="rightalign" value='${pd.compCode}' type="text" readonly name="compCode" id="compCode" size="5"></td>
												<td class="tdpadding"><input class="rightalign" value='${pd.iyear}' readonly  type="text" name="iyear" id="iyear" size="5"></td>
												<td class="tdpadding"><input value='${pd.imonth}' class="rightalign" type="text" name="imonth" id="imonth" size="5" readonly></td>
												<td class="tdpadding"><input class="rightalign" value="${pd.caccountCode}" type="text" name="caccountCode" id="caccountCode" size="5" readonly></td>
												<td class="tdpadding"><input class="rightalign" value="${pd.csubjectCode}" type="text" name="csubjectCode" id="csubjectCode" size="8" readonly></td>
												<td class="tdpadding"><input value="${pd.csubjectName}" type="text" name="csubjectName" id="csubjectName" size="8" readonly></td>
												<td class="tdpadding"><input class="rightalign" value="${pd.cspCode}" type="text" name="cspCode" id="cspCode" size="5" readonly></td>
												<td class="tdpadding"><input class="rightalign" value="${pd.iamount}" type="text" name="iamount" id="iamount" size="5" readonly></td>
												<td class="tdpadding"><input class="rightalign" value="${pd.cdepCode}" type="text" name="cdepCode" id="cdepCode" size="8" readonly></td>
												<td class="tdpadding"><input class="rightalign" value="${pd.payPrice}" type="text" name="payPrice" id="payAmount" size="5" onkeyup="value=value.replace(/[^\d.]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" ></td>
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
<script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/Purchase/Payment/PaymentOrderUpdate.js" type="text/javascript"></script>
<script type="text/javascript">
function isInvoice(){
	var invoice = $("#invoice").val();
	if (invoice=="Y"){
		$("#invoiceNumber").attr("readonly",false);
	}
	else{
		$("#invoiceNumber").val("");
		$("#invoiceNumber").attr("readonly",true);
	}
}
//获取字符串的字节长度
function len(s) {
s = String(s);
return s.length + (s.match(/[^\x00-\xff]/g) || "").length;// 加上匹配到的全角字符长度
}

function limit(obj, limit) {
var val = obj.value;
if (len(val) > limit) {
val=val.substring(0,limit);
while (len(val) > limit){
val = val.substring(0, val.length - 1);
};
obj.value = val;
}
}

$("#invoiceNumber").keyup(function(){
limit(this,14);//14字节内
});
$("#applyFor").keyup(function(){
limit(this,24);//24字节内
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
//文件删除
function deleteFiles(dynamicURL, tableId) {
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
</script>
</body>


</html>