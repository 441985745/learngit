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
        <input type="button" name="btnCancel" value="返回" id="btnCancel" class="btn" onclick="dg.cancel()">
        <c:if test="${notPoPaymentOrder.cbarStatus!=null}">
        <input type="button" name="checkBar" value="影像调阅" id="checkBar" class="btn">
        </c:if>
    </div>
    <div style="margin-top: 10px;">
    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<app:approvalTag billCode="${notPoPaymentOrder.cdocument}" typeId="77079"></app:approvalTag>
	</div>
    <div id="purchaseTab" class="easyui-tabs" >
       <div title="订单信息" id="tabsa" style="overflow:auto;padding:20px;min-width: 800px;">
       <div>
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
								<input class="searchinput" readonly name="paymentType" type="hidden" value="${notPoPaymentOrder.paymentType}" id="paymentType">
								<input class="searchinput" name="" type="hidden" value="" id="userIndex">
								<input class="searchinput" name="" type="hidden" value="" id="costCenterIndex">
								<%-- <input class="searchinput" readonly name="officeLocation" type="hidden" value="${purchaseOrder.officeLocation}" id="officeLocation"> --%>
							</td>
							<td style="text-align: right; width: 90px;">供应商编码:</td>
							<td>
								<input class="searchinput" readonly='readonly' name='supplierCode' type='text' value='${notPoPaymentOrder.supplierCode}' id='supplierCode'/>
							</td>
							<%--<td style="text-align: right; width: 90px;">币种:</td>--%>
							<%--<td>--%>
								<%--<input readonly='readonly' class='searchinput' name='currencyCode' type='text' value='${purchaseOrder.currencyCode}' id='currencyCode'>--%>
							<%--</td>--%>
							<td style="text-align: right;width: 90px;">币种:</td>
							<td>
							<input class="searchinput" readonly name="cdocument" type="text" value="${notPoPaymentOrder.currencyCode}" id="currencyCode">
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
							<td style="text-align: right; width: 90px;">供应商名称:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='supplierName' type='text' value='${notPoPaymentOrder.supplierName}' id='supplierName'>
							</td>
							<td style="text-align: right" >汇率:</td>
							<td class="tdtext"><input class='searchinput' name='exchangeRate' readonly='readonly' type='text' value='${notPoPaymentOrder.exchangeRate}' id='exchangeRate'></td>
						</tr>
						<tr>
							<td style="text-align: right"><font color="red" >*</font>申请部门:</td>
							<%--<td class="tdtext">--%>
								<%--<input class="searchinput" readonly name="applyDepartment" type="text" value="${userDeptInfo.departmentName}" id="txtApplyDepartment">--%>
								<%--<input class="searchinput" readonly name="applyDepartmentCode" type="hidden" value="${userDeptInfo.departmentCode}" id="txtApplyDepartment">--%>
							<%--</td>--%>
							<td>
								<input readonly="readonly" name="departmentName" value="${notPoPaymentOrder.departmentName}" type="text" id="txtBudgetDepartment" class="searchinput" >
								<input name="departmentCode" value="" type="hidden" id="txtYsssbm" class="inputbtnRefViewGray" >
							</td>
							<td style="text-align: right;">公司具体名称:</td>
							
							<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="companySpecificName" type="text" value="${notPoPaymentOrder.companySpecificName}" id="companySpecificName">
							</td>
							<td style="text-align: right"><font color="red" >*</font>银行账号:</td>
	                        <td class="tdtext" >
								<input readonly="readonly" class="searchinput" style="background-color: #CCCCCC;" name="bankNumber" type="text" value="${notPoPaymentOrder.bankNumber}" id="bankNumber">
							</td>
						</tr>
						<tr> 
							<td style="text-align: right;">所属公司:</td>
							<%--<td class="tdtext">--%>
								<%--<input readonly='readonly' class='searchinput' name='companyCode' type='text' value='${purchaseOrder.companyCode}' id='companyCode'>					--%>
	                        <%--</td>--%>
							<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="companyName" type="text" value="(${notPoPaymentOrder.companyCode})${notPoPaymentOrder.companyName}" id="companyName">
							</td>
	                        <td style="text-align: right; width: 90px;">国家:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='country' type='text' value='${notPoPaymentOrder.country}' id='country'>
							</td>
							<td style="text-align: right; width: 90px;">开户行:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='bankName' type='text' value='${notPoPaymentOrder.bankName}' id='bankName'>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;"><span style="color: red">*</span>是否一次性费用化:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='isExpenseAssets' type='text' value='${notPoPaymentOrder.isExpenseAssets=="Y"?"是":"否"}' id='isExpenseAssets'>
							</td>
							<td style="text-align: right; width: 90px;">城市:</td>
							<td>
								<input  class='searchinput' readonly='readonly' name='city' type='text' value='${notPoPaymentOrder.city}' id='city'>
							</td>
							<td style="text-align: right; width: 90px;">联行号:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='contactNumber' type='text' value='${notPoPaymentOrder.contactNumber}' id='contactNumber'>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;"><span style="color: red">*</span>扫描地:</td>
							<td>
								<input  class='searchinput' readonly='readonly' name='scanLocation' type='text' value='${notPoPaymentOrder.scanLocation}' id='scanLocation'>
							</td>
							<td style="text-align: right" ><font color="red" >*</font>税码/税率:</td>
							<td class="tdtext">
								<input readonly class='searchinput' name='cc' type='text' value='<fmt:formatNumber type="number" value="${notPoPaymentOrder.taxRate*100}" maxFractionDigits="0"/>%' id='cc'>
								<input readonly class='searchinput' name='taxRate' type='hidden' value='${notPoPaymentOrder.taxRate}' id='taxRate'>
							</td>
							<td style="text-align: right; width: 90px;"><span style="color: red">*</span>申请事由:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='applyFor' type='text' value='${notPoPaymentOrder.applyFor}' id='applyFor'>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;"><span style="color: red">*</span>是否有增值税专用发票:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='isVat' type='text' value='${notPoPaymentOrder.isVat=="Y"?"是":"否"}' id='isVat'>
							</td>
							<td style="text-align: right; width: 90px;">发票号:</td>
							<td>
								<input readonly='readonly'  class='searchinput' name='invoiceNumber' type='text' value='${notPoPaymentOrder.invoiceNumber}' id='invoiceNumber'>
							</td>
							<td style="text-align: right; width: 90px;">付款类型:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='insertType' type='text' value='${notPoPaymentOrder.insertType}' id='insertType'>
							</td>
						</tr>
						<tr>
							<td style="text-align: right" >备注:</td>
							<td class="tdtext"><input readonly='readonly' class='searchinput' name='remark' type='text' value='${notPoPaymentOrder.remark}' id='remark'></td>
						</tr>
						</tbody>
						
					</table>
                </td>
            </tr>           
            </tbody>
        </table>
       </form>	
        </br>
        <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
			<tbody>
			<tr>
				<td class="titlefontsize" align="left">
				<div class="imgtitle"></div>
				付款明细信息</td>
			</tr>
			<tr>
				<td colspan="4">
					<table id="Table10"    border="0" cellpadding="3px" cellspacing="0" style="table-layout: fixed;width:100%;">
						<tbody>
						<tr>
							<td class="tdtitle" style="text-align: right" colspan="2">
								<div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
									<div>
										<table style="width:825px;" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
											<tr style="white-space:nowrap;">
												<th scope="col" class="tdpadding">序号</th>
												<th class='tdpadding' scope="col"><font color="red" >*</font>成本中心</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>项目编码</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>预算资产名称</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>使用部门</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>办公地点</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>资产名称</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>资产类型</th>
												<th scope="col" class="tdpadding">型号规格/品牌</th>
												<th class='tdpadding' scope="col"><font color="red" >*</font>资产分类</th>
												<th class='tdpadding' scope="col"><font color="red" >*</font>折旧年限</th>
												<th class='tdpadding' scope="col"><font color="red" >*</font>总账科目</th>
												<th class='tdpadding' scope="col"><font color="red" >*</font>资产责任人</th>
												<th class='tdpadding' scope="col"><font color="red" >*</font>内部订单号</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>单位</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>数量</th>
												<c:if test="${notPoPaymentOrder.isGetEncoding=='Y'}">
												<th scope="col" class="tdpadding"><font color="red" >*</font>编码方式</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>主编吗</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>次级</th> 
												</c:if>
								<!-- 				<th scope="col" class="tdpadding"><font color="red" >*</font>编码方式</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>主编吗</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>次级</th> -->
												<!-- 5 -->
												<th scope="col" class="tdpadding"><font color="red" >*</font>含税单价</th>
												<th scope="col" class="tdpadding">净价</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>可用预算金额</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>含税总价</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>税额</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>其他增加价值（如：关税等）</th>
												<th scope="col" class="tdpadding">资产增加价值文本</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>预算年度</th>
												<!-- 10 -->
												<th scope="col" class="tdpadding"><font color="red" >*</font>本次申请金额(原币)</th>
												<!-- 15 -->
												<th scope="col" class="tdpadding"><font color="red" >*</font>本次预付冲减金额</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>尾款金额</th>
												<th scope="col" class="tdpadding">尾款付款时间</th>
												<!-- 20 -->
												<th scope="col" class="tdpadding">本次申请金额(本币)</th>
											</tr>
											<tbody id="table_detail">
											<c:forEach items="${notPoPaymentOrderDetails}" var="pd" varStatus="i">
											<tr class="bodyTr" style="white-space:nowrap;">
												<td class="tdpadding" >${i.index+1}<input value='${notPoPaymentOrder.cdocument}' type="hidden" name="notPoPaymentOrderId" id="notPoPaymentOrderId"></td>
												<td class='tdpadding'>
                                                	(${pd.costCenterCode})${pd.costCenter}
                                               	</td>
												<td class="tdpadding">
													${pd.projectCode}
		                                    	</td>
												<td class='tdpadding'>${pd.budgetAssetsName}</td>
												<td class='tdpadding' >${pd.subordinateDepartment}</td>
												<td class='tdpadding' id='tdOfficeLocation'>
													<c:if test="${pd.officeLocation=='BGDD10000'}">
															上海
													</c:if>
													<c:if test="${pd.officeLocation=='BGDD10001'}">
															吴江
													</c:if>
													<c:if test="${pd.officeLocation=='BGDD10002'}">
															中山
													</c:if>
													<c:if test="${pd.officeLocation=='BGDD10003'}">
															驻外
													</c:if>
												</td>
												<td class='tdpadding' >${pd.assetsName}</td>
												<c:if test="${pd.assetsType=='0'}">
												<td class='tdpadding' >IT资产</td>
												</c:if>
												<c:if test="${pd.assetsType=='1'}">
												<td class='tdpadding' >行政资产</td>
												</c:if>
												<c:if test="${pd.assetsType=='2'}">
												<td class='tdpadding' >计量器具</td>
												</c:if>
												<c:if test="${pd.assetsType=='3'}">
												<td class='tdpadding' >机器设备</td>
												</c:if>
												<td class='tdpadding' >${pd.specificationParameter}</td>
												<td class='tdpadding'>
													${pd.assetClassification}
                                               	</td>
                                               	<td class='tdpadding'  >
                                               		${pd.depreciation}
                                                </td>
                                                <td class='tdpadding'>
                                                	${pd.ledgerAccount}
                                                </td>
                                                <td class='tdpadding'>
                                                	${pd.assetsUserName}
                                                </td>
												<!-- 5 -->
												<td class="tdpadding">${pd.internalOrderNo}</td>
												<td class="tdpadding" id="tdUnits">
													${pd.units}
												</td>
												<td class="tdpadding">${pd.payCount}</td>
												<c:if test="${notPoPaymentOrder.isGetEncoding=='Y'}">
												<td class="tdpadding" >
													<c:if test="${pd.codingMode=='forAdd'}">新增</c:if>
													<c:if test="${pd.codingMode=='forAppend'}">追加</c:if>
													<c:if test="${pd.codingMode=='forAppoint'}">指定</c:if>
												</td>
												<td class="tdpadding"><input type="text" name="mainAssetCode" size='10' id="mainAssetCode" readonly="readonly" value="${pd.mainAssetCode}"></td>
												<td class="tdpadding"><input type="text" name="secondary" class='rightalign' id="secondary" size='7' value="${pd.secondary}" readonly="readonly"></td>
												</c:if>
												
												<td class="tdpadding">${pd.taxUnit}</td><!-- 含税单价 -->
												<!-- 10 -->
												<td class="tdpadding">${pd.netPrice}</td><!-- 净价 -->
												<!-- 15 -->
												<td class="tdpadding">${pd.avaliableSumMoney}</td><!-- 可用预算金额 -->
												<td class="tdpadding"><input type="text" name="taxTotalPrice" class='rightalign' id="taxTotalPrice" value="${pd.taxTotalPrice}" readonly="readonly" size="10"></td><!-- 含税总价-->
												<td class="tdpadding"><input type="text" name="tax" class='rightalign' id="tax" value="${pd.tax}" readonly="readonly" size="10"></td><!-- 税额-->
												<td class="tdpadding"><input type="text" name="assetsIncreaseValue" class='rightalign' id="assetsIncreaseValue" value="${pd.assetsIncreaseValue}" readonly="readonly" size="10"></td><!-- 资产增加价值-->
												<td class="tdpadding">${pd.text}</td><!-- 资产增加价值文本-->
												<td class="tdpadding">${pd.budgetYear}</td><!-- 预算年度-->
												<td class="tdpadding"><input type="text" name="payPrice" class='rightalign' id="payPrice" value="${pd.payPrice}" readonly="readonly" size="10"></td><!-- 本次申请金额(原币)-->
												<td class="tdpadding"><input type="text" name="advanceOffsetPrice" class='rightalign' id="advanceOffsetPrice" value="${pd.advanceOffsetPrice}" readonly="readonly" size="10"></td><!-- 本次预付冲减金额-->
												<td class="tdpadding"><input type="text" name="finalPrice" class='rightalign' id="finalPrice" value="${pd.finalPrice}" readonly="readonly" size="10"></td><!-- 尾款金额-->
												<td class="tdpadding"><fmt:formatDate value='${pd.finalPaymentDate}'  pattern='yyyy-MM-dd'/></td><!-- 尾款付款时间-->
												<!-- 20 -->
												<td class="tdpadding"><input type="text" name="payPriceLocal" class='rightalign' id="payPriceLocal" value="${pd.payPriceLocal}" readonly="readonly" size="10"></td>
											</tr>
											</c:forEach>
										</tbody>

											<tr style="white-space:nowrap;">
												<td class='tdpadding'>合计</td>
												<td>
		                                    	</td>
												<td>
												</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<c:if test="${notPoPaymentOrder.isGetEncoding=='Y'}">
												<td></td>
												<td></td>
												<td></td>
												</c:if>
												<td></td>
												<td></td>
												<td></td>
												<td class='tdpadding'><input type="text" class='rightalign' readonly="readonly" name="" id="sumtaxTotalPrice" size="10"></td><!-- 含税总价合计-->
												<td class='tdpadding'><input type="text" class='rightalign' readonly="readonly" name="" id="sumtax" size="10"></td><!-- 税额合计-->
												<td class='tdpadding'><input type="text" class='rightalign' readonly="readonly" name="" id="sumassetsIncreaseValue" size="10"></td><!-- 资产 增加价值合计-->
												<td></td>
												<td></td>
												<td class='tdpadding'><input type="text" class='rightalign' readonly="readonly" name="" id="sumpayPrice" size="10"></td><!-- 本次申请金额(原币)合计-->
												<td class='tdpadding'><input type="text" class='rightalign' readonly="readonly" name="" id="sumadvanceOffsetPrice" size="10"></td><!-- 冲减预付金额合计-->
												<td class='tdpadding'><input type="text" class='rightalign' readonly="readonly" name="" id="sumfinalPrice" size="10"></td><!-- 尾款金额合计-->
												<td></td>
												<td class='tdpadding'><input type="text" class='rightalign' readonly="readonly" name="" id="sumpayPriceLocal" size="10"></td><!-- 本次申请金额(本币)合计-->
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

		       <div style="height: 50px;margin-top: 10px;">
				<table width="100%" border="0" cellpadding="0" cellspacing="3px">
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
								       <c:forEach items="${notPoPaymentOrder.listFile}" var="assetPic" >
									   
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
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
              <tbody>
              <tr>
                  <td class="titlefontsize" align="left" style="width: 150px">
                      <div class="imgtitle"></div>
                      		审批历史
                  </td>
                  <td></td>
              </tr>
              <c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isComingSAP=='Y'}">
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
			</div>
			</div>
        </div>
        
       <div title="预付冲减" data-options="closable:false" style="overflow:auto;padding:20px;">
	   	<div>
	   		 </br>
        <table id="Table18" width="100%" border="0" cellpadding="0" cellspacing="3px">
            <tbody>
            <tr>
                <td class="titlefontsize" align="left">
                <div class="imgtitle"></div>预付冲减
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table id="Table15"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;width:100%">
                        <tbody>
                        <tr>
                            <td class="tdtitle" style="text-align: right" colspan="2">
                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                    <div>
                                        <table id="table_add_detail" style="width:100%" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
                                           
                                            <tr style="white-space:nowrap;">
                                                <th scope="col" class="tdpadding">凭证号</th>
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

    </div>

<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
<link href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css" type="text/css" rel="stylesheet" />
<link href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css" type="text/css" rel="stylesheet" /> --%>
<script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script>
<%-- <script src="${staticURL}/Scripts/Purchase/Payment/PaymentOrderAdd.js" type="text/javascript"></script> --%>
<script type="text/javascript">
	var dg = frameElement.lhgDG;//定义dg 关闭需要
	var statusIndex=1;//序号
	$(function(){ 
		sumAll();
	});
	var paymentType = $("#paymentType").val();//付款类型
	//本次付款数量
	$("#table_detail").delegate("input[id='payCount']","keyup",function(){
		if ($("#taxRate").val()=="") {
			alert("请先选择税率！");
			$(this).val("");
			return
		}
		sumAll();
	});
	//含税单价
	$("#table_detail").delegate("input[id='taxUnit']","keyup",function(){
		if ($("#taxRate").val()=="") {
			alert("请先选择税率！");
			$(this).val("");
			return
		}
		sumAll();
	});
	//资产增加价值
	$("#table_detail").delegate("input[id='assetsIncreaseValue']","keyup",function(){
		sumAll();
	});
	//本次申请金额(原币)
	$("#table_detail").delegate("input[id='payPrice']","keyup",function(){
		sumAll();
	});
	//本次冲减金额
	$("#table_detail").delegate("input[id='advanceOffsetPrice']","keyup",function(){
		sumAll();
	});
	function sumAll(){
		var exchangeRate = $("#exchangeRate").val();//汇率
		var taxRate = $("#taxRate").val();//税率
		//数量*单价
		$("input[id='payCount']").each(function(){
			var count = $(this).val();//本次申请数量
			var price = $(this).parent().parent().find("#taxUnit").val();//采购单价
			var assetsIncreaseValue = $(this).parent().parent().find("#assetsIncreaseValue").val();//资产增加价值
			var payPrice = $(this).parent().parent().find("#payPrice").val();//本次申请金额(原币)
			var advanceOffsetPrice = $(this).parent().parent().find("#advanceOffsetPrice").val();//本次冲减金额
			var taxTotalPrice = $(this).parent().parent().find("#taxTotalPrice").val();//含税总价
			if(assetsIncreaseValue!=''&&payPrice!=''&&advanceOffsetPrice!=''&&taxTotalPrice!=''){
				$(this).parent().parent().find("#finalPrice").val(parseFloat(taxTotalPrice)+parseFloat(assetsIncreaseValue)-parseFloat(payPrice)-parseFloat(advanceOffsetPrice));//尾款金额
			}
			$(this).parent().parent().find("#taxTotalPrice").val(count*price-0);//含税总价
			$(this).parent().parent().find("#netPrice").val(((count*price-0)/(1+parseFloat(taxRate))).toFixed(2));//净价
			$(this).parent().parent().find("#tax").val(((count*price-0)/(1+parseFloat(taxRate))*taxRate).toFixed(2));//税额
			
			$(this).parent().parent().find("#payPriceLocal").val(parseFloat(taxTotalPrice)*exchangeRate);//本次申请金额(本币)
			/* if(paymentType=="验收款"){
				$(this).parent().parent().find("#advancePrice").val((count*price-0)*lastPaymentRatio/100);//已付预付款金额
				$(this).parent().parent().find("#finalPrice").val(((count*price-0)-((count*price-0)*lastPaymentRatio/100)-((count*price-0)*paymentRatio/100)).toFixed(2));//未付尾款金额    (应付金额-已付预付款金额-本次付款金额)
			}
			$(this).parent().parent().find("#payPrice").val((count*price-0)*paymentRatio/100);//本次付款金额
			$(this).parent().parent().find("#payPriceLocal").val((((count*price-0)*paymentRatio/100)*exchangeRate-0).toFixed(2));//本次付款金额(本币) */
		});
		//含税总价
		var sumtaxTotalPrice=0;
		$("input[id='taxTotalPrice']").each(function(){
			sumtaxTotalPrice += $(this).val()-0;
		});
		$("#sumtaxTotalPrice").val(sumtaxTotalPrice);
		
		//税额
		var sumtax=0;
		$("input[id='tax']").each(function(){
			sumtax += $(this).val()-0;
		});
		$("#sumtax").val(sumtax);
		
		//合计资产增加价值
		var sumassetsIncreaseValue=0;
		$("input[id='assetsIncreaseValue']").each(function(){
			sumassetsIncreaseValue += $(this).val()-0;
		});
		$("#sumassetsIncreaseValue").val(sumassetsIncreaseValue);
		
		//合计本次申请金额(原币)
		var sumpayPrice=0;
		$("input[id='payPrice']").each(function(){
			sumpayPrice += $(this).val()-0;
		});
		$("#sumpayPrice").val(sumpayPrice);
		
		//合计预付冲减金额
		var sumadvanceOffsetPrice=0;
		$("input[id='advanceOffsetPrice']").each(function(){
			sumadvanceOffsetPrice += $(this).val()-0;
		});
		$("#sumadvanceOffsetPrice").val(sumadvanceOffsetPrice);
		
		//合计尾款
		var sumfinalPrice=0;
		$("input[id='finalPrice']").each(function(){
			sumfinalPrice += $(this).val()-0;
		});
		$("#sumfinalPrice").val(sumfinalPrice);
		
		//本次付款金额本币
		var sumpayPriceLocal=0;
		$("input[id='payPriceLocal']").each(function(){
			sumpayPriceLocal += $(this).val()-0;
		});
		$("#sumpayPriceLocal").val(sumpayPriceLocal);
		ratioMoney = sumpayPrice;
		realMoney = sumpayPrice;
	}
	function checkAll(){
		var mess="";
		var flag = true;
		var sumadvanceOffsetPrice = $("#sumadvanceOffsetPrice").val();
		var payPriceDetail = 0;
		var flag1=false;
		if(paymentType=="验收款"){
			$("#tbody_detail_detail").find('input[name^=gvdata]').each(function(){
				if($(this).attr('checked')){
					payPriceDetail += $(this).parent().parent().find("#payAmount").val()-0;
					if(payPriceDetail == ""||isNaN(payPriceDetail)){
						/*mess += "本次冲减金额格式不正确;";
						flag = false*/;
						flag1=true;
						return ;
					}
				}
				
			});
			if(flag1){
				mess += "本次冲减金额格式不正确;";
			}
			if(parseInt(sumadvanceOffsetPrice)!=parseInt(payPriceDetail)){
				mess += "本次冲减金额与冲减预付金额不相等;";
				flag = false;
			}
/*			alert(payPriceDetail);*/
		}
		if(mess.length>0){
			alert(mess);
		}
		return flag;
	}
	$("#btnSave").click(function() {
		if(checkAll()){
			showAgreeDialog();
		};
	});
	function showAgreeDialog() {
        var url = _dynamicURL+"/workflow/showSubmitDialog?parentWindowId=add";

        var dlg = new dg.curWin.$.dialog({ id: 'submitDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();

        return dlg;
    }
	function commitAgree(isMail, isPhoneMessage, idea,isPortrait) {
		$("#btnSave").val("提交...");
		$("#btnSave").attr("disabled", "disabled");//添加disabled属性
		$("#btnSaveDraft").attr("disabled", "disabled");
		$("#btnCancel").attr("disabled", "disabled");
		saveOrder("N",isMail, isPhoneMessage, idea,isPortrait);
		$("#btnSave").val("提交");
		$("#btnSave").removeAttr("disabled"); //移除disabled属性
		$("#btnSaveDraft").removeAttr("disabled");
		$("#btnCancel").removeAttr("disabled");
    }
	$("#btnSaveDraft").click(function() {//保存草稿
			if(checkAll()){
				$("#btnSave").attr("disabled", "disabled");//添加disabled属性
				$("#btnSaveDraft").attr("disabled", "disabled");
				$("#btnSaveDraft").val("保存草稿...");
				$("#btnCancel").attr("disabled", "disabled");
				saveOrder("Y","","","");
				$("#btnSaveDraft").val("保存草稿");
				$("#btnSave").removeAttr("disabled"); //移除disabled属性
				$("#btnSaveDraft").removeAttr("disabled");
				$("#btnCancel").removeAttr("disabled");
			}
	});
//提交表单
function saveOrder(strDraft,isMail, isPhoneMessage, idea,isPortrait){
	
	var objHeader = $("#paymentOrderFormHeader").serializeObject();
	var objDetail;
	var objDetailDetail;
	objHeader.applyMoney = $("#sumpayPrice").val();
    objHeader.commitType = strDraft;
	objHeader.idea = idea;
	objHeader.isMail = isMail;
	objHeader.isPhoneMessage = isPhoneMessage;
	objHeader.isPortrait = isPortrait; 
	objDetail= {
			notPoPaymentOrderId:"",
			projectCode:"",
			budgetAssetsName:"",
			assetsName:"",
			specificationParameter:"",
			assetClassification:"",
			depreciation:"",
			ledgerAccount:"",
			assetsUserName:"",
			costCenter:"",
			costCenterCode:"",
			internalOrderNo:"",
			units:"",
			payCount:"",
			taxUnit:"",
			netPrice:"",
			avaliableSumMoney:"",
			taxTotalPrice:"",
			tax:"",
			assetsIncreaseValue:"",
			text:"",
			budgetYear:"",
			payPrice:"",
			advanceOffsetPrice:"",
			finalPrice:"",
			finalPaymentDate:"",
			payPriceLocal:""
		};
	objDetailDetail= {
			paymentOrderId:"",
			cpassDocuments:"",
			pstngDate:"",
			compCode:"",
			iyear:"",
			imonth:"",
			caccountCode:"",
			csubjectCode:"",
			csubjectName:"",
			cspCode:"",
			iamount:"",
			cdepCode:"",
			payPrice:"",
			cdocumentsNo:""
		};
	var PaymentOrderWrapper = {};
	PaymentOrderWrapper.notPoPaymentOrder = objHeader;
	alert("11111");
	if(paymentType=="验收款") {
		alert("22222");
		var paymentOrderDetail = constructObjectFromTable(objDetail, $("#table_detail .bodyTr"));
		PaymentOrderWrapper.notPoPaymentOrderDetails = paymentOrderDetail;
		var paymentPaymentMappingModels = constructObjectFromTable(objDetailDetail, $("#tbody_detail_detail .bodyTr"));
		PaymentOrderWrapper.paymentPaymentMappingModels = paymentPaymentMappingModels;
	}
	$.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: _dynamicURL+"/notPoPayment/api/savePayment",
        data: JSON.stringify(PaymentOrderWrapper), 
        success: function(data) {
        	if (data.errorMessages.length > 0) {
                alert(data.errorMessages);
                return;
            }
            alert(data.successMessage);
            //关闭窗口
            dg.curWin.refresh();
            dg.cancel();
            return;
        }
    });
}
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
	//供应商编码选择框
	$("#butSupplierShow").click(function(){
		searchSupplier();
	});
	//选择供应商
	function searchSupplier(){
		var top = "90px";//$(this).offset().top - 350;
		var left = "50px";//$(this).offset().left-180;
		$('#supplierWindow').window('open').window('resize',{width:'650px',height:'330px',top: top,left:left});
	}
	function cleanDep() {
		$('#txtBudgetDepartment').val('');
		$('#txtYsssbm').val('');
		$('#companyName').html('');
		$('#txtCostCenter').val('');
		$('#txtCostCenterCode').val('');
	}
	//查询供应商
	$("#searchSupplier").click(function(){
		searchSupplierLists();
	});
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
            $('#supplierBody').html(html);

        }
    });
}
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
		searchPaymentPaymentMappingByCode(supplierCode);
	});
	function searchPaymentPaymentMappingByCode(supplierCode){
		$.ajax({
			type : "POST",
			nsync : true,
			url : _dynamicURL+"/notPoPayment/api/searchPaymentPaymentMappingByCode",
			data: {
	            'supplierCode': supplierCode
	        },
			success : function(data){
				var divHtml = "";
				var document = $("#cdocument").val();
				for (var i = 0; i < data.length; i++) {
					divHtml += "<tr class='bodyTr' style='white-space:nowrap;''>";
					divHtml += "<td class='tdpadding'><input id='chkBolDel' type='checkbox' name='gvdata'></td>";
					divHtml += "<td class='tdpadding'><input value='" + data[i].cpassDocuments + "' type='text' readonly name='cpassDocuments' id='cpassDocuments' size='10'></td>";
					divHtml += "<td class='tdpadding'><input class='rightalign' value='" + data[i].compCode + "' type='text' readonly name='compCode' id='compCode' size='5'><input value='" + document + "' type='hidden' name='paymentOrderId' id='paymentOrderId'><input value='" + data[i].cdocumentsNo + "' type='hidden' name='cdocumentsNo' id='cdocumentsNo'></td>";
					divHtml += "<td class='tdpadding'><input class='rightalign' value='" + data[i].iyear + "' readonly  type='text' name='iyear' id='iyear' size='5'></td>";
					divHtml += "<td class='tdpadding'><input value='" + data[i].imonth + "' class='rightalign' type='text' name='imonth' id='imonth' size='5' readonly></td>";
					divHtml += "<td class='tdpadding'><input class='rightalign' value='" + data[i].caccountCode + "' type='text' name='caccountCode' id='caccountCode' size='5' readonly></td>";
					divHtml += "<td class='tdpadding'><input class='rightalign' value='" + data[i].csubjectCode + "' type='text' name='csubjectCode' id='csubjectCode' size='8' readonly></td>";
					divHtml += "<td class='tdpadding'><input class='rightalign' value='" + data[i].csubjectName + "' type='text' name='csubjectName' id='csubjectName' size='8' readonly></td>";
					divHtml += "<td class='tdpadding'><input class='rightalign' value='" + data[i].cspCode + "' type='text' name='cspCode' id='cspCode' size='5' readonly></td>";
					divHtml += "<td class='tdpadding'><input class='rightalign' value='" + data[i].iamount + "' type='text' name='iamount' id='iamount' size='5' readonly></td>";
					divHtml += "<td class='tdpadding'><input class='rightalign' value='" + data[i].cdepCode + "' type='text' name='cdepCode' id='cdepCode' size='8' readonly></td>";
					divHtml += "<td class='tdpadding'><input class='rightalign' value='' type='text' name='payPrice' id='payAmount' size='5' onkeyup='value=value.replace(/[^\d.]/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))' ></td>";
					divHtml += "</tr>";
				}
				$("#tbody_detail_detail").html(divHtml);
			}
		});
	}
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
	$("#checkBar").click(function(){
		var cdocument = $("#cdocument").val();
		 $.ajax({
	           url: "${dynamicURL}/notPoPayment/api/checkBar",
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
            }
        }
    });
}
	function cleanSupp(){
        $("#supplierName").val("");
        $("#supplierCode").val("");
        $("#paymentTypeName").val("");
        $("#paymentTypeCode").val("");
        $("#bankName").val("");
        $("#bankCode").val("");
        $("#bankNumber").val("");
        $("#contactNumber").val("");
        $("#contacts").val("");
        $("#telephone").val("");
        $("#companyAddress").val("");
        $("#country").val("");
    }
//清空项目编码
$("input[id='projectCodeClear']").live("click",function(){
    $(this).parent().find("input[id^='txtApplydep']").val("");
});
function searchBudgetLists(){

    $.ajax({
        type: "POST",
        cache:false,
        url: "${dynamicURL}/assetBudget/api/getAssetsBudgets",
        data: $('#budgetForm').serialize(),
        // dataType: "json",
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data){
            // $('#resText').empty();   //清空resText里面的所有内容
            var html = '';
            var com = $("#companyName").val();
            $('#budgetBody').html("");
            $.each(data, function(commentIndex, comment){
                //console.log(comment);
                html = '<tr style="white-space:nowrap;"><td><input type="radio" name="budgetRadio" value='+commentIndex+'></td><td>'
                    +(commentIndex+1)+'</td>'
                    +'<td>'+com+'</td><td>'
                    + comment['costCenterName']
                    + '</td><td id=\"projectCode'+commentIndex+'\">' + comment['projectCode']
                    + '</td><td id=\"projectName'+commentIndex+'\">' + comment['projectName']
                    + '</td><td id=\"assetsName'+commentIndex+'\">' + comment['assetsName']
                    + '</td><td id=\"assetsType'+commentIndex+'\">' + comment['assetsType']
                    + '</td><td id=\"unit'+commentIndex+'\">' + comment['unit']
                    + '</td><td id=\"budgetYear'+commentIndex+'\">' + comment['budgetYear']
                    + '</td><td>' + comment['amount']
                    + '</td><td id=\"unitPrice'+commentIndex+'\">' + comment['unitPrice']
                    + '</td><td>' + comment['yearBudgetTotal']
                    + '</td><td>' + comment['usedAmount']
                    + '</td><td>' + comment['usedSumMoney']
                    //+ '</td><td id=\"avaliableAmount'+commentIndex+'\">' + comment['avaliableAmount']
                    + '</td><td id=\"avaliableSumMoney'+commentIndex+'\">' + comment['avaliableSumMoney']
                    + '</td></tr>';
                $('#budgetBody').append(html);
            });

        }
    });
}
$("#searchBudger").click(function(){
    searchBudgetLists();
});
$("#table_detail").delegate("input[id^='btnRefShow']","click",function(){
	$("#budgetWindow").attr("name",($(this).attr("id").substr(10)));
	var i = $(this).attr("id").substr(10);
	/* 先判断是否选择公司和成本中心了 */
    var attc = $("#companyName").val();
    //var attcName = $("#companyName").find("option:selected").text();
    var costc = $("#costCenterCode"+i).val();
    var costcName = $("#costCenterName"+i).val();
    $("#company").val(attc);
    $("#companyName1").val(attc);
    $("#costCenter").val(costc);
    $("#costCenterName").val(costcName);
//    $("#assetsType").val($("#txtAssetType1").val());
//    //清空搜索条件(项目)
    $("#budgetYear").val("2017");
    var att = "";
    if(!attc){
        att+="[公司]";
    }if(!costc){
        att+="[成本中心]";
    }
    if(att!=""){
        alert("请选择"+att);
        return false
    }
    //.substr(10))
    var top = "90px";//$(this).offset().top - 350;
    var left = "50px";//$(this).offset().left-180;
    $('#budgetWindow').window('open').window('resize',{width:'650px',height:'330px',top: top,left:left});
    searchBudgetLists();
});


	$("#table_detail").delegate("input[id^='showCostCenterBtn']","click",function(){
		var i = $(this).attr("id").substr(17);
		$("#costCenterIndex").val(i);
		var companyCode = $("#companyName").val();
		if(companyCode != null && companyCode != ""){
			showCostCenterDialog(companyCode);
		}else {
			alert("请先选择公司！");
		}
	});

	function showCostCenterDialog(companyCode){
		var url = "${dynamicURL}/costCenter/getCodeAndName?parentWindowId=add&&companyCode=" + companyCode;
		var parentWindow=dg.curWin;
		new parentWindow.$.dialog({ id: 'list', title: "成本中心", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();

	}

	function fillCostCenter(costcenterCode, costcenterName) {
		var j = $("#costCenterIndex").val();
		$('#costCenterCode'+j).val(costcenterCode);
		$('#costCenterName'+j).val(costcenterName);
		getInternalOrderCode(j,costcenterCode);
	}
	function getInternalOrderCode(n,costcenterCode){
		var attc = $("#companyName").val();
		$.ajax({
			type : "POST",
			nsync : true,
			url : _dynamicURL+"/userInfo/api/getInternalOrderCode",
			data: {
	            'ccompanycode': attc,
	            'ccostcentercode': costcenterCode
	        },
			success : function(userInfo){
				//console.log(userInfo);
				$("#internalOrderNo"+n).val(userInfo.internalOrderCode);
			}
		});
	}

	function ClearObj(that){
		$(that).siblings(".name").val("");
		$(that).siblings(".code").val("");
	}

	//清除  "X"
	function cleanAssets(btn) {
		$(btn).parent().find("#assetClassification").val("");
	}
	//点击的资产分类按钮
	var $inputAssets;
	$("#table_detail").delegate("input[id^='btnAssetsShow']","click",function(){
		$("#addAssetsWindow").attr("name",($(this).attr("id").substr(13)));
        $inputAssets = $(this);
        var top = "75px";
        var left = "15px";
        $('#addAssetsWindow').window('open').window('resize', {width: '450px', height: '300px', top: top, left: left});
        searchDemandOrderDetail();
    });
	function searchDemandOrderDetail() {
		$.ajax({
			type: "POST",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			url: "${dynamicURL}/assetClassification/api/searchAssetClassificationForDemand",
			success: function (data) {
				var divHtml = "";
				for (var i = 0; i < data.length; i++) {
					divHtml += "<tr style='white-space:nowrap;'>";
					if (i == 0) {
						divHtml += "<td class='tdpadding'><input type='radio' checked='checked' name='btnRadio'id='btnRadio' class='btnRadio'></td>";
					} else {
						divHtml += "<td class='tdpadding'><input type='radio' name='btnRadio' id='otherBtnRadio' class='btnRadio'></td>";
					}
					divHtml += "<td class='tdpadding'><input type='text' name='assetClassification' id='assetClassification' value='" + data[i].assetClassification + "' readonly='readonly' size='5' ></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='assetsName' id='assetsName' value='" + data[i].assetsName + "' readonly='readonly' size='10' ></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='assetstypeInfo' id='assetstypeInfo' value='" + data[i].assetstypeInfo + "' readonly='readonly' ></td>";
					divHtml += "<td class='depredepre'><input type='text' name='depreciationPeriod' id='depreciationPeriod' value='" + data[i].depreciationPeriod + "' readonly='readonly' size='5' ></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='netResidualRate' id='netResidualRate'  value='" + data[i].netResidualRate + "' readonly='readonly' size='5' ></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='managementDepartment' id='managementDepartment' value='" + data[i].managementDepartment + "' readonly='readonly' ></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='generalLedgerAccount' id='generalLedgerAccount' value='" + data[i].generalLedgerAccount + "' readonly='readonly' size='13'  ></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='accountDescription' id='accountDescription' value='" + data[i].accountDescription + "' readonly='readonly' ></td>";
					divHtml += "</tr>";
				}
				$("#tbody_add_detail").html(divHtml);
			}
		});
	}

	$("#table_detail").delegate("input[id^='showUserListBtn']","click",function(){
		var i = $(this).attr("id").substr(15);
		$("#userIndex").val(i);
		showUserDialog();
	});
	function showUserDialog(){
		var url = "${dynamicURL}/user/getUserListInAttachConfig?parentWindowId=add";
		var parentWindow=dg.curWin;
		new parentWindow.$.dialog({ id: 'list', title: "所有人员", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();

	}
	function fillVehicleUser(userCode, userName) {
		var j = $("#userIndex").val();
		$('#assetsUserCode'+j).val(userCode);
		$('#assetsUserName'+j).val(userName);

	};

	$("#budgerSure").click(function(){
		var i = $("input[name='budgetRadio']:checked").val();
		if(!i){
			alert("未选择任何一行数据!");
			return false;
		}
		/* var flag = false;
		var txtAssetType1 = $("#txtAssetType1").val();
		var assetsType = $("#assetsType"+i).html();

		$.ajax({
			type : "POST",
			async : false,
			//cache:false,
			url : "${dynamicURL}/demand/api/searchAssetsType",
			data : {"budgetAssetsType":assetsType},
			success: function(data){
				if(!data){
					//没有匹配资产类型
					alert("资产类型无效");
					return true;
				}else if(txtAssetType1!=data.assetsType){
					if (!window.confirm("预算资产类型跟单据资产类型不匹配，确定选择该预算吗?")) {
						flag = true;
					}
				}
			}
		});
		if(flag){
			return false;
		} */
		var code = $("#projectCode"+i).html();//$("#assetsBudget").find("tr").eq(i+1).find("td").eq(4).text();
		var projectName = $("#projectName"+i).html();
		var name = $("#assetsName"+i).html();//$("#assetsBudget").find("tr").eq(i+1).find("td").eq(5).text();
		var aSumMoney = toDecimal2($("#avaliableSumMoney"+i).html());
		var budgetYear = $("#budgetYear"+i).html();//年度

		var j = $("#budgetWindow").attr("name");
		
		$("#txtAssetsName"+j).val(name);
		$("#txtBudgetAssetsName"+j).val(projectName);
		$("#projectCode"+j).val(code);
		$("#avaliableSumMoney"+j).val(aSumMoney);
		$("#budgetYear"+j).val(budgetYear);

		$('#budgetWindow').window('close');

	});



	//序号重新排序
	function resetStatusIndex(){
		statusIndex = 0;
		$("td[id='statusIndex']").each(function(){
			statusIndex++;
			$(this).html(statusIndex);
		});
	}
	var unitsHtml = "";
	$(function(){
		unitsHtml = $("#tdUnits").html();
	});

	$("#addRows").click(function(){//添加行
		var isAllTrue = false;
		$("input[id^='txtApplydep']").each(function(){
			if(!$(this).val()){
				isAllTrue = true;
			}
		});
		if(isAllTrue){
			alert("行明细有未选择预算的数据，请先全部选择预算后再增行");
			return false;
		}
		addRow();
	});
	$("#delRows").click(function(){//删除行
		$("#table_detail").find("input:checkbox[name=gvDataList_chkBolDel_0]:checked").each(function(){
			$(this).parent().parent().remove();
		});
		$("#gvDataList_CheckAll").attr("checked", false);
		if(($.trim($("#table_detail").html())).length==0){
			statusIndex = 0;
			addRow();
		}
		resetStatusIndex();
		sumAll();
	});
	$("#btnDetailSave").click(function () {
		var j = $("#addAssetsWindow").attr("name");

		if($("input:radio[class='btnRadio']:checked")){
		    var assetClassification = $("input:radio[class='btnRadio']:checked").parent().parent().find("input[name='assetClassification']").val();
		    $("#assetClassification"+j).val(assetClassification);
            var depreciationPeriod = $("input:radio[class='btnRadio']:checked").parent().parent().find("input[name='depreciationPeriod']").val();
            $("#depreciation"+j).val(depreciationPeriod);
            var generalLedgerAccount = $("input:radio[class='btnRadio']:checked").parent().parent().find("input[name='generalLedgerAccount']").val();
            $("#ledgerAccount"+j).val(generalLedgerAccount);
		}
		$("#addAssetsWindow").window('close');
    });
	//制保留2位小数，如：2，会在2后面补上00.即2.00
    function toDecimal2(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return false;
        }    
        var f = Math.round(x*100)/100;    
        var s = f.toString();    
        var rs = s.indexOf('.');    
        if (rs < 0) {    
            rs = s.length;    
            s += '.';    
        }    
        while (s.length <= rs + 2) {    
            s += '0';    
        }    
        return s;    
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
	function clearObj(that){
		$(that).siblings(".name").val("");
		$(that).siblings(".code").val("");
	}
	function sumAll(){
		//含税总价
		var sumtaxTotalPrice=0;
		$("input[id='taxTotalPrice']").each(function(){
			sumtaxTotalPrice += $(this).val()-0;
		});
		$("#sumtaxTotalPrice").val(sumtaxTotalPrice.toFixed(2));
		
		//税额
		var sumtax=0;
		$("input[id='tax']").each(function(){
			sumtax += $(this).val()-0;
		});
		$("#sumtax").val(sumtax.toFixed(2));
		
		//合计资产增加价值
		var sumassetsIncreaseValue=0;
		$("input[id='assetsIncreaseValue']").each(function(){
			sumassetsIncreaseValue += $(this).val()-0;
		});
		$("#sumassetsIncreaseValue").val(sumassetsIncreaseValue.toFixed(2));
		
		//合计本次申请金额(原币)
		var sumpayPrice=0;
		$("input[id='payPrice']").each(function(){
			sumpayPrice += $(this).val()-0;
		});
		$("#sumpayPrice").val(sumpayPrice.toFixed(2));
		
		//合计预付冲减金额
		var sumadvanceOffsetPrice=0;
		$("input[id='advanceOffsetPrice']").each(function(){
			sumadvanceOffsetPrice += $(this).val()-0;
		});
		$("#sumadvanceOffsetPrice").val(sumadvanceOffsetPrice.toFixed(2));
		
		//合计尾款
		var sumfinalPrice=0;
		$("input[id='finalPrice']").each(function(){
			sumfinalPrice += $(this).val()-0;
		});
		$("#sumfinalPrice").val(sumfinalPrice.toFixed(2));
		
		//本次付款金额本币
		var sumpayPriceLocal=0;
		$("input[id='payPriceLocal']").each(function(){
			sumpayPriceLocal += $(this).val()-0;
		});
		$("#sumpayPriceLocal").val(sumpayPriceLocal.toFixed(2));
	}
	$(document).ready(function(){
        $(window).resize(function(){
            $('#purchaseTab').tabs('resize');
        });
	});
</script>
</body>


</html>