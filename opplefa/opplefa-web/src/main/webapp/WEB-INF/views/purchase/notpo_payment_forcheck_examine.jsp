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
								<input class="searchinput" readonly type="hidden" value="${notPoPaymentOrder.isGetEncoding}" id="isGetEncoding">
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
							<input class="searchinput" readonly name="currencyCode" type="text" value="${notPoPaymentOrder.currencyCode}" id="currencyCode">
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
								<input  class='searchinput' name='country' type='text' value='${notPoPaymentOrder.country}' id='country'>
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
								<input readonly class='searchinput'  type='text' value='<fmt:formatNumber type="number" value="${notPoPaymentOrder.taxRate*100}" maxFractionDigits="0"/>%' id='cc'>
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
								<input  class='searchinput' name='invoiceNumber' type='text' value='${notPoPaymentOrder.invoiceNumber}' id='invoiceNumber'>
							</td>
							<td style="text-align: right; width: 90px;">付款类型:</td>
							<td>
								<input  class='searchinput' name='insertType' type='text' value='${notPoPaymentOrder.insertType}' id='insertType'>
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
			<c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isGetEncoding=='N'}">
                        <tr>
                            <td colspan="2">
                                <input type="button" name="btnGetSecondary" value="获取资产编码" onclick="getSecondary();"
                                       id="btnGetSecondary" class="btn"/>
                            </td>
                        </tr>
            </c:if>
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
												<c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isGetEncoding=='Y'}">
												<th scope="col" class="tdpadding"><font color="red" >*</font>编码方式</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>主编吗</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>次级</th> 
												</c:if>
												<c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isGetEncoding=='N'}">
												<th scope="col" class="tdpadding"><font color="red" >*</font>编码方式</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>主编吗</th>
												<th scope="col" class="tdpadding"><font color="red" >*</font>次级</th> 
												</c:if>
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
												<td class="tdpadding" >${i.index+1}1<input value='${pd.id}' type="hidden" name="id" id="id"></td>
												<td class='tdpadding'>
                                                	(${pd.costCenterCode})${pd.costCenter}<input value='${pd.costCenterCode}' type="hidden" name="costCenterCode" id="costCenterCode">
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
												<td class='tdpadding' >${pd.assetsName}<input value='${pd.assetsName}' type="hidden" name="assetsName" id="assetsName"></td>
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
													${pd.assetClassification}<input value='${pd.assetClassification}' type="hidden" name="assetClassification" id="assetClassification">
                                               	</td>
                                               	<td class='tdpadding'  >
                                               		${pd.depreciation}
                                                </td>
                                                <td class='tdpadding'>
                                                	${pd.ledgerAccount}<input value='${pd.ledgerAccount}' type="hidden" name="ledgerAccount" id="ledgerAccount">
                                                </td>
                                                <td class='tdpadding'>
                                                	${pd.assetsUserName}
                                                </td>
												<!-- 5 -->
												<td class="tdpadding">${pd.internalOrderNo}</td>
												<td class="tdpadding" id="tdUnits">
													${pd.units}<input value='${pd.units}' type="hidden" name="units" id="units">
												</td>
												<td class="tdpadding">${pd.payCount}<input value='${pd.payCount}' type="hidden" name="payCount" id="payCount"></td>
												<c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isGetEncoding=='Y'}">
												<td class="tdpadding" >
													<c:if test="${pd.codingMode=='forAdd'}">新增</c:if>
													<c:if test="${pd.codingMode=='forAppend'}">追加</c:if>
													<c:if test="${pd.codingMode=='forAppoint'}">指定</c:if>
												</td>
												<td class="tdpadding"><input type="text" name="mainAssetCode" size='10' id="mainAssetCode" readonly="readonly" value="${pd.mainAssetCode}"></td>
												<td class="tdpadding"><input type="text" name="secondary" class='rightalign' id="secondary" size='10' value="${pd.secondary}" readonly="readonly"></td>
												</c:if>
												<c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isGetEncoding=='N'&&notPoPaymentOrder.isExpenseAssets=='N'}">
												<td class="tdpadding" >
													<select id='codingMode' name='codingMode'>
															<option value=''>请选择</option>
															<option value='forAdd'>新增</option>
															<option value='forAppend'>追加</option>
															<option value='forAppoint'>指定</option>
													</select>
												</td>
												<td class="tdpadding"><input type="text" name="mainAssetCode" size='10' id="mainAssetCode"></td>
												<td class="tdpadding"><input type="text" name="secondary" class='rightalign' id="secondary" size='10' readonly="readonly"></td>
												</c:if>
												<c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isGetEncoding=='N'&&notPoPaymentOrder.isExpenseAssets=='Y'}">
												<td class="tdpadding" >
													<select id='codingMode' name='codingMode' disabled="disabled">
															<option value='forAdd'>新增</option>
													</select>
												</td>
												<td class="tdpadding"><input type="text" name="mainAssetCode" size='10' id="mainAssetCode" readonly="readonly"></td>
												<td class="tdpadding"><input type="text" name="secondary" class='rightalign' id="secondary" size='10' readonly="readonly"></td>
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
												<c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isGetEncoding=='Y'}">
												<td></td>
												<td></td>
												<td></td>
												</c:if>
												<c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isGetEncoding=='N'}">
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
			<c:if test="${notPoPaymentOrder.isLastApprove=='Y'&&notPoPaymentOrder.isComingSAP=='N'}">
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
	$(function(){ 
		sumAll();
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
	 	    if($("#cvoucherId").val()==""){
		    	alert("请先生成对应凭证！");
		    	return;
		    }
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
		    var cvoucherId = $("#cvoucherId").val();
		    var cinputVoucher = $("#cinputVoucher").val();
		    var paymentOrderModel={};
		    if(cvoucherId!="" ){
		    	paymentOrderModel.cinputVoucher = cinputVoucher;
		    	paymentOrderModel.cvoucherId = cvoucherId;
		    }
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
//		                dg.curWin.location=dg.curWin.location;;
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
			var isGetEncoding = $("#isGetEncoding").val();
			if (isGetEncoding=='N') {
				alert("请先获取资产编码！");
				return;
			}
			var notPoPaymentOrder={};
			notPoPaymentOrder.cdocument = $("#cdocument").val();
			$.ajax({
				type : "POST",
				dataType: "json",
				async : false,
				contentType: "application/json; charset=utf-8",
				url : "${dynamicURL}/notPoPayment/api/createReceiptVoucher",
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
		//获取资产编码
		function getSecondary(){
			var flag = false;
			$("select[id='codingMode']").each(function(){
				if($(this).val()==""){
					flag = true;
				};
			});
			if (flag) {
				alert("有未选择的编码类型！");
				return;
			}
			var objHeader = $("#paymentOrderFormHeader").serializeObject();
			var objDetail;
			var objDetailDetail;
			objDetail= {
					id:"",
					assetsName:"",
					assetClassification:"",
					ledgerAccount:"",
					costCenterCode:"",
					units:"",
					codingMode:"",
					mainAssetCode:"",
					secondary:"",
					payCount:""
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
			var paymentOrderDetail = constructObjectFromTable(objDetail, $("#table_detail .bodyTr"));
			PaymentOrderWrapper.notPoPaymentOrderDetails = paymentOrderDetail;
			var paymentPaymentMappingModels = constructObjectFromTable(objDetailDetail, $("#tbody_detail_detail .bodyTr"));
			PaymentOrderWrapper.paymentPaymentMappingModels = paymentPaymentMappingModels;
			$.ajax({
	            type: "POST",
	            url: "${dynamicURL}/notPoPayment/api/sapInterface",
	            contentType: "application/json;",
	            data: JSON.stringify(PaymentOrderWrapper),
	            success: function (data) {
	                if (data.success) {
	                    alert("获取资产编码成功!");
	                    location.reload();
	                } else {
	                    $('#btnGetSecondary').val('获取资产编码');
	                    $("#btnGetSecondary").attr("disabled", false);
	                    alert(data.errorMessages.join());
	                }
	            }
	        });

	        $("#btnGetSecondary").val('获取资产编码');
	        $("#btnGetSecondary").attr("disabled", false);
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
$("#codingMode").live("change", function () {
    var codingMode = $(this).children("option:selected").val();
    var $mainAssetCode = $(this).parent().parent().find("#mainAssetCode");
    var $secondary = $(this).parent().parent().find("#secondary");
    if (codingMode == "forAdd") {
        $mainAssetCode.attr("readonly", "readonly");
        $secondary.attr("readonly", "readonly");
    }
    else if (codingMode == "forAppend") {
        $mainAssetCode.removeAttr("readonly");
        $secondary.attr("readonly", "readonly");
    }
    else {
        $mainAssetCode.removeAttr("readonly");
        $secondary.removeAttr("readonly");
    }
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