<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>采购订单</title>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>

    <div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
        <div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
                采购订单</div>
    </div>
    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
        <input type="button" name="btnSave" value="提交" onclick="" id="btnSave" class="btn">
        <c:if test="${purchaseOrder.draft=='Y'}">
        	<input type="button" name="btnSaveDraft" value="保存草稿" onclick="" id="btnSaveDraft" class="btn">
        </c:if>
        <input type="button" name="btnCancel" value="返回" id="btnCancel" class="btn" onclick="dg.cancel()">
    </div>
    <div style="margin-top: 10px;">
    
    <div id="purchaseTab" class="easyui-tabs" >
       <div title="订单信息" id="tabsa" style="overflow:auto; padding:20px;min-width: 800px;">
       <div>
       <form id="purchaseOrderFormHeader">
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
							<input name='' type='hidden' value='${purchaseOrder.orderMoney}' id='oldOrderMoney'>
							<input name='attachDepartManagerName' type='hidden' value='${purchaseOrder.attachDepartManagerName}' id='attachDepartManagerName'>
							<input name='attachDepartManagerCode' type='hidden' value='${purchaseOrder.attachDepartManagerCode}' id='attachDepartManagerCode'>
							<input name='attachDepartAdminName' type='hidden' value='${purchaseOrder.attachDepartAdminName}' id='attachDepartAdminName'>
							<input name='attachDepartAdminCode' type='hidden' value='${purchaseOrder.attachDepartAdminCode}' id='attachDepartAdminCode'>
							
							<td style="text-align: right; width: 90px;">单据号:</td>
							<td>
								<input class="searchinput" readonly name="cdocument" type="text" value="${purchaseOrder.cdocument}" id="txtPurchaseOrderId">
							</td>
							<td style="text-align: right"><font color="red" >*</font>订单号(SAP):</td>
	                        <td>
								<input class="searchinput" readonly name="purchaseOrderSapId" style="background-color: #CCCCCC;" type="text" value="${purchaseOrder.purchaseOrderSapId}" id="purchaseOrderSapId">
	                        </td>
							<td style="text-align: right; width: 90px;">采购员:</td>
							<td>
								<input class="searchinput" readonly="readonly"  value='${purchaseOrder.buyerName}'  name="buyerName" type="text" value="" id="txtBuyerName">
								<input class="searchinput" readonly="readonly"  value='${purchaseOrder.buyerCode}'  name="buyerCode" type="hidden" value="" id="txtBuyerCode">
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">申请日期:</td>
							<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="applyDate" type="text" value="<fmt:formatDate value='${purchaseOrder.applyDate}'  pattern='yyyy-MM-dd'/>" id="txtApplyDate">
							</td>
							<td style="text-align: right; width: 90px;"><font color="red" >*</font>所属公司:</td>
							<td id="companyForOther" >
							<span id="spanCompany">
								<select id="txtCompanyCode" class="searchselect" name='companyCode'></select>
								<!-- <input readonly='readonly' class='searchinput' style="background-color: #CCCCCC;" name='companyName' type='text' value='' id='txtCompany' onclick='checkDepVal()'>
								<input name='companyCode' type='hidden' value='' id='txtCompanyCode'> -->
							</span>
							</td>
							<%-- <c:choose>
								<c:when test="${purchaseOrder.assetType=='IT资产'}">
									<td id="companyForOther" style="display: none">
									<span id="spanCompany">
										<input readonly='readonly' class='searchinput' style="background-color: #CCCCCC;" name='companyName' type='text' value='${purchaseOrder.companyName}' id='txtCompany' onclick='checkDepVal()'>
										<input name='companyCode' type='hidden' value='${purchaseOrder.companyCode}' id='txtCompanyCode'>
									</span>
									</td>
									<td id="companyForIt" >
										<select class="searchselect"  id="company">
										 <option selected="selected" value="">--请选择--</option>
		                                    <c:forEach items="${companys }" var="comp" varStatus="status">
		                                    <option value="${comp.ccompanycode },${comp.ccompanyname }" ${comp.ccompanycode == purchaseOrder.companyCode ? "selected" : ""}>${comp.ccompanyname }</option>
		                                    </c:forEach>
		                                </select>
									</td>
								</c:when>
								<c:otherwise>
									<td id="companyForOther" >
										<span id="spanCompany">
											<input readonly='readonly' class='searchinput' style="background-color: #CCCCCC;" name='companyName' type='text' value='${purchaseOrder.companyName }' id='txtCompany' onclick='checkDepVal()'>
											<input name='companyCode' type='hidden' value='${purchaseOrder.companyCode}' id='txtCompanyCode'>
										</span>
										</td>
										<td id="companyForIt" style="display: none">
											<select class="searchselect"  id="company">
											 <option selected="selected" value="">--请选择--</option>
			                                    <c:forEach items="${companys }" var="comp" varStatus="status">
			                                    <option value="${comp.ccompanycode },${comp.ccompanyname }" >${comp.ccompanyname }</option>
			                                    </c:forEach>
			                                </select>
										</td>
								</c:otherwise>
							</c:choose> --%>
							<!-- <td>
							<span id="spanCompany">
								<input readonly='readonly' class='searchinput' name='companyName' style="background-color: #CCCCCC;" type='text' value='' id='txtCompany' onclick='checkDepVal()'>
								<input name='companyCode' type='hidden' value='' id='txtCompanyCode'>
							</span>
							</td> -->
							<td style="text-align: right;"><font color="red" >*</font>采购组:</td>
							<td class="tdtext">
								<input class="searchinput" readonly name="purchasingGroup" type="text" value="${purchaseOrder.purchasingGroup}" id="purchasingGroup">
							</td>
						</tr>
						<tr>
							<td style="text-align: right;"><font color="red" >*</font>资产类型:</td>
							<td class="tdtext">
								<select class="searchselect" name="assetType" id="txtAssetType1">
                                    <option value="">请选择</option>
                                    <option value="行政资产">行政资产</option>
                               		<option value="IT资产">IT资产</option>
                                    <option value="计量器具">计量器具</option>
                                    <option value="机器设备">机器设备</option>
                               	</select>
							</td>
							<td style="text-align: right"><font color="red" >*</font>平台:</td>
							<td class="tdtext">
                                <input readonly='readonly' class='searchinput' style="background-color: #CCCCCC;" name='platform' type='text' value='' id='txtPlatform'>
							</td>
							<td style="text-align: right;"><font color="red" >*</font>采购组织:</td>
							<td class="tdtext">
								<input class="searchinput" readonly name="procurementOrganization" value="${purchaseOrder.procurementOrganization}" type="text" id="procurementOrganization">
							</td>
						</tr>
						<tr> 
							<td style="text-align: right"><font color="red" >*</font>归口/预算部门:</td>
							<td>
		                        <span id ="spanBudgetDepartment"><input style="width: 100px;" readonly="readonly" name="budgetDepartmentName" value="${purchaseOrder.budgetDepartmentName}" type="text" id="txtBudgetDepartment" class="inputbtnRefViewGray" ></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanDep();"><input id="btntxtYsssbmShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
		                        <input name="budgetDepartmentCode" value="${purchaseOrder.budgetDepartmentCode}" type="hidden" id="txtYsssbm" class="inputbtnRefViewGray" >
	                        </td>
							<td style="text-align: right" ><font color="red" >*</font>工厂代码:</td>
							<td>
								<%-- <input readonly="readonly" class="searchinput" style="background-color: #CCCCCC;" name="factoryCode" type="text" value="${purchaseOrder.factoryCode}" id="factoryCode"> --%>
								<select id="factoryCode" class="searchselect" name='factoryCode'>
									<option value="${purchaseOrder.factoryCode}">${purchaseOrder.factoryCode}</option>
								</select>
							</td>
							<td style="text-align: right" ><font color="red" >*</font>订单类别:</td>
							<td class="tdtext">
								<select class="searchselect" name="orderType" id="orderType">
									<option value="">请选择</option>
									<option value="ZAB" ${purchaseOrder.orderType=="ZAB"?"selected='selected'":""}>固定资产(ZAB)</option>
									<option value="ZFB" ${purchaseOrder.orderType=="ZFB"?"selected='selected'":""}>费用(ZFB)</option>
	                            </select>
							</td>
						</tr>
						<tr> 
							<td style="text-align: right"><font color="red" >*</font>成本中心:</td>
	                        <td class="tdtext" >
								<span id="spanCostCenter">
								<input readonly="readonly" class="searchinput" style="background-color: #CCCCCC;" name="costCenter" type="text" value="${purchaseOrder.costCenter}" id="txtCostCenter">
								<input readonly="readonly" class="searchinput" style="background-color: #CCCCCC;" name="costCenterCode" type="hidden" value="${purchaseOrder.costCenterCode}" id="txtCostCenterCode">
								</span>
							</td>
							<td style="text-align: right" ><font color="red" >*</font>税码/税率:</td>
							<td class="tdtext">
								<select class="searchselect" name="taxRate" id="taxRate">
									<option value="">请选择</option>
									<option value="0.0">J0 (0%进项税，中国)</option>
									<option value="0.17">J1 (17%进项税，中国)</option>
									<option value="0.13">J2 (13%进项税，中国)</option>
									<option value="0.07">J3 (7%进项税，中国)</option>
									<option value="0.06">J4 (6%进项税，中国)</option>
									<option value="0.04">J5 (4%进项税，中国)</option>
									<option value="0.03">J6 (3%进项税，中国)</option>
									<option value="0.11">J7 (11%进项税，中国)</option>
	                            </select>
							</td>
							<td style="text-align: right" ></td>
							<td class="tdtext"></td>
						</tr>
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
                供应商信息 </td>
            </tr>
            <tr>
                <td>
                    <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
						<tbody>
						<tr>
							<td style="text-align: right; width: 90px;">供应商代码:</td>
							<td>
								<input style="width: 100px;" name="supplierCode" value="${purchaseOrder.supplierCode}" type="text" id="supplierCode" class="inputbtnRefViewGray" ></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanSupp();"><input id="butSupplierShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
							</td>
							<td style="text-align: right; width: 90px;">供应商名称:</td>
							<td>
								<input suplc="Y" class="searchinput"  name="supplierName" type="text" value="${purchaseOrder.supplierName}" id="supplierName">
							</td>
							<td style="text-align: right; width: 90px;">邮编:</td>
							<td>
								<input suplc="Y" class="searchinput"  name="zipCode" type="text" value="${purchaseOrder.zipCode}" id="zipCode">
								<input suplc="Y" class="searchinput"  name="paymentTypeName" type="hidden" value="${purchaseOrder.paymentTypeName}" id="paymentTypeName">
								<input class="searchinput"  name="paymentTypeCode" type="hidden" value="${purchaseOrder.paymentTypeCode}" id="paymentTypeCode">
							</td>
						</tr>
						<tr>
							<td style="text-align: right">开户行:</td>
							<td>
								<input suplc="Y" class="searchinput"  name="bankName" type="text" value="${purchaseOrder.bankName}" id="bankName">
								<input class="searchinput"  name="bankCode" type="hidden" value="${purchaseOrder.bankCode}" id="bankCode">
							</td>
							<td style="text-align: right">银行账号:</td>
							<td>
								<input suplc="Y" class="searchinput"  name="bankNumber" type="text" value="${purchaseOrder.bankNumber}" id="bankNumber">
							</td>
							<td style="text-align: right">联行号:</td>
							<td>
								<input suplc="Y" class="searchinput"  name="contactNumber" type="text" value="${purchaseOrder.contactNumber}" id="contactNumber">
							</td>
						</tr>
						<tr>
							<td style="text-align: right">联系人:</td>
							<td>
								<input suplc="Y" class="searchinput"  name="contacts" type="text" value="${purchaseOrder.contacts}" id="contacts">
							</td>
							<td style="text-align: right">电话:</td>
							<td>
								<input suplc="Y" class="searchinput"  name="telephone" type="text" value="${purchaseOrder.telephone}" id="telephone">
							</td>
							<td style="text-align: right">公司地址:</td>
							<td>
								<input suplc="Y" class="searchinput"  name="companyAddress" type="text" value="${purchaseOrder.companyAddress}" id="companyAddress">
							</td>
						</tr>
						<tr>
							<td style="text-align: right">公司具体名称:</td>
							<td>
								<input suplc="Y" class="searchinput"  name="companySpecificName" type="text" value="${purchaseOrder.companySpecificName}" id="companySpecificName">
							</td>
							<td style="text-align: right"><font color="red" >*</font>币种:</td>
	                        <td>
								<!-- suplc="Y"  --><select class="searchselect" name="currencyCode" id="currencyCode" onchange="selectCurrency()" >
									<c:forEach items="${currencylist}" var="o" varStatus="status">
									   <option value="${o.ccurrencycode}" ${o.ccurrencycode==purchaseOrder.currencyCode ? "selected='selected'" :"" }>${o.ccurrencycode} ${o.ccurrencyname }</option>
									</c:forEach>
								 </select>
	                        </td>
							<td style="text-align: right;"><font color="red" >*</font>汇率:</td>
							<td class="tdtext">
								<input class="searchinput" name="exchangeRate" type="text" value="" id="txtExchangeRate" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td style="text-align: right">国家:</td>
							<td>
								<input  class="searchinput"  readonly="readonly" name="country" type="text" value="${purchaseOrder.country}" id="country">
							</td>
							<td style="text-align: right">城市:</td>
							<td>
								<input  class="searchinput"  name="city" type="text" value="${purchaseOrder.city}" id="city">
							</td>
							<td style="text-align: right" ></td>
							<td class="tdtext">
							</td>
						</tr>
						<tr>
							<td style="text-align: right"><font color="red" >*</font>送货地址:</td><!--  -->
							<td colspan="5">
								<input  size="110" name="deliveryAddress" type="text" value="${purchaseOrder.deliveryAddress}" id="deliveryAddress">
							</td>
						</tr>
						<tr>
							<td style="text-align: right">抬头文本说明:</td>
							<td colspan="5">
								<input   size="110" name="headerTextDescription" type="text" value="${purchaseOrder.headerTextDescription}" id="headerTextDescription">
							</td>
						</tr>
						<tr>
							<td style="text-align: right">采购员备注:</td><!--  -->
							<td colspan="5">
								<input  size="110" name="buyerRemark" type="text" value="${purchaseOrder.buyerRemark}" id="buyerRemark">
							</td>
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
                <div class="imgtitle"></div>订单明细信息
                </td>
            </tr>
            <tr>
                <td class="titlefontsize" align="left">
                    <input id="addRows" type="button" value="选择明细" >
                    <input id="delRows" type="button" value="删行">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table id="Table10"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;width:100%;">
                        <tbody>
                        <tr>
                            <td class="tdtitle" style="text-align: right" colspan="2">
                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                    <div>
                                        <table  id="table_detail" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;width:825px;">
                                           
                                            <tr style="white-space:nowrap;">
                                                <th scope="col">
                                                    <input id="gvDataList_CheckAll" type="checkbox" name="gvDataList$ctl01$CheckAll"
                                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_detail');"/>
                                                </th>
                                                <!-- <th scope="col" class="tdpadding">合并号</th> -->
                                                <th scope="col" class="tdpadding"><font color="red" >*</font>采购申请单号</th>
                                                <th scope="col" class="tdpadding"><font color="red" >*</font>主编码</th>
                                                <!-- <th scope="col" class="tdpadding"><font color="red" >*</font>次级</th> -->
                                                <th scope="col" class="tdpadding"><font color="red" >*</font>资产名称</th>
                                                <th scope="col" class="tdpadding"><font color="red" >*</font>规格参数</th>
                                                <!-- 5 -->
                                                <th scope="col" class="tdpadding"><font color="red" >*</font>功能</th>
                                                <th scope="col" class="tdpadding"><font color="red" >*</font>选配件</th>
                                                <th scope="col" class="tdpadding"><font color="red" >*</font>验收标准</th>
                                                <th scope="col" class="tdpadding">采购数量</th>
                                                <th scope="col" class="tdpadding">询价单价(含税)</th>
                                                <th scope="col" class="tdpadding">采购单价(含税)</th>
                                                <th scope="col" class="tdpadding">采购总价(含税)</th>
                                                <th scope="col" class="tdpadding">采购总价(含税本币)</th>
                                                <th scope="col" class="tdpadding">净价(原币)</th>
                                                <th scope="col" class="tdpadding"><font color="red" >*</font>型号规格/品牌</th>
                                                <!-- 10 -->
                                                <th scope="col" class="tdpadding">约定交货日期</th>
                                                <th scope="col" class="tdpadding">预算成本中心</th>
                                                <th scope="col" class="tdpadding"><font color="red" >*</font>质保周期(月)</th>
                                            </tr>
                                            <tbody id="tbody_detail">
                                            <c:forEach items="${purchaseOrderDetailModels}" var="pod" varStatus="i">
                                            	<tr class='bodyTr' style='white-space:nowrap;'>
												<td class='tdpadding'><input id='gvpurchaseOrderDetailModelsList_chkBolDel_0' type='checkbox' name='delcheck'></td>
												<td class='tdpadding'><input type='hidden' name='applyDetailId' id='applyDetailId' value='${pod.applyDetailId}'><input type='hidden' name='ledgerAccount' id='ledgerAccount' value='${pod.ledgerAccount}'><input size='14' type='text' name='applyOrderId' readonly='readonly' value='${pod.applyOrderId}'></td>
												<td class='tdpadding'><input size='10' type='text' name='mainAssetCode' id='txtMainAssetCode' value='${pod.mainAssetCode}' readonly='readonly'></td>
												<%-- <td class='tdpadding'><input size='8' type='text' name='secondary' id='txtSecondary' value='${pod.secondary}' readonly='readonly'></td> --%>
												<td class='tdpadding'><input type='text' size='10' name='assetsName' id='txtAssetsName' value='${pod.assetsName}' readonly='readonly'></td>
												<td class='tdpadding'><input type='text' size='10' name='specificationParameter' id='txtSpecificationParameter' value='${pod.specificationParameter}' readonly='readonly' ></td>
												<td class='tdpadding'><input type='text' size='10' name='functions' id='txtFunctions' value='${pod.functions}' readonly='readonly' ></td>
												<td class='tdpadding'><input type='text' size='10' name='optionalAccessories' id='txtOptionalAccessories'  value='${pod.optionalAccessories}' readonly='readonly' ></td>
												<td class='tdpadding'><input type='text' size='10' name='acceptanceCriteria' id='txtAcceptanceCriteria' value='${pod.acceptanceCriteria}' readonly='readonly' ></td>
												<td class='tdpadding'><input class='rightalign' type='text' name='applyCount' id='txtApplyCount' size='5' value='${pod.applyCount}' readonly='readonly' ></td>
												<td class='tdpadding'><input class='rightalign' type='text' id='txtInquiryUnitPrice' size='5' value='${pod.inquiryUnitPrice}' readonly='readonly'></td>
												<td class='tdpadding'><input class='rightalign' type='text' name='purchaseUnitPrice' id='purchaseUnitPrice' value='${pod.purchaseUnitPrice}'  size='5'  ><input class='rightalign' type='hidden' name='inquiryUnitPrice' value='${pod.inquiryUnitPrice}'></td>
												<td class='tdpadding'><input class='rightalign' type='text' name='purchasePrice' id='purchasePrice' value='${pod.purchasePrice}' size='5'></td>
												<td class='tdpadding'><input class='rightalign' type='text' name='purchasePriceLocal' id='purchasePriceLocal' value='${pod.purchasePriceLocal}' size='5' readonly='readonly'></td>
												<td class='tdpadding'><input class='rightalign' type='text' name='netPrice' id='netPrice' value='${pod.netPrice}' size='5' readonly='readonly'></td>
												<td class='tdpadding'><input type='text' class='rightalign' name='brand' value='${pod.brand}' id='brand' size='10'></td>
												<td class='tdpadding'><input type='text' name='requirementsDate' value='<fmt:formatDate value='${pod.requirementsDate}'  pattern='yyyy-MM-dd'/>' id='requirementsDate' size='5' readonly='readonly' class='Wdate' onclick="WdatePicker({skin:'ext'});"></td>
												<td class='tdpadding'><input type='text' name='costCenter' value='${pod.costCenter}' id='txtCostCenter' readonly='readonly'><input type='hidden' name='costCenterCode' value='${pod.costCenterCode}' id='txtCostCenterCode'></td>
												<td class='tdpadding'><input type='text' class='rightalign' name='warrantyPeriod' value='${pod.warrantyPeriod}' id='warrantyPeriod' size='8' ></td>
												</tr>
											</c:forEach>
                                            </tbody>
                                            <tr style="white-space:nowrap;">
												<td></td>
												<td class='tdpadding'>合计</td>
												<!-- <td></td> -->
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td><input class="rightalign" type="text" name="" id="sumApplyCount" size="5" readonly="readonly"></td>
												<td></td>
												<td></td>
												<td><input class="rightalign" type="text" name="" id="sumPurchasePrice" size="5" readonly="readonly"></td>
												<td><input class="rightalign" type="text" name="" id="sumPurchasePriceLocal" size="5" readonly="readonly"></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
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
       </br>
			   <table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
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
       </div>
        </div>
        <div title="资产明细" data-options="closable:false" style="overflow:auto;padding:20px;">
        <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder">
            <tbody>
            <tr>
                <td class="titlefontsize" align="left">
                <div class="imgtitle"></div>资产明细
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table id="Table10" width="100%"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
                        <tbody>
                        <tr>
                            <td class="tdtitle" style="text-align: right" colspan="2">
                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                    <div>
                                        <table id="table_detail_detail" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
                                            <tr style="white-space:nowrap;">
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>项目编码</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>预算年度</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>资产名称</th>
	                                                <!-- 5 -->
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>规格参数</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>功能</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>选配件</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>验收标准</th>
	                                                <!-- <th class='tdpadding' scope="col"><font color="red" >*</font>资产类型</th> -->
	                                                <!-- 10 -->
	                                                <th class='tdpadding' scope="col">需求部门</th>
	                                                
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>本次申请数量</th>
	                                                <!-- 15 -->
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>单位</th>
                                               	 	<th class="tdpadding" scope="col"><font color="red" >*</font>询价单价</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>要求到位日期</th>
	                                                <th class='tdpadding' scope="col">预算成本中心</th>
	                                                <th scope="col"><font color="red" >*</font>用途说明</th>
	                                            </tr>
                                            <tbody id="tbody_detail_detail">
                                            	<c:forEach items="${purchaseApplyMappingModels}" var="pam" varStatus="i">
                                            	<tr class='bodyTr' style='white-space:nowrap;'>
													<td class='tdpadding'>
														<input type='hidden' name='applyDemandId' value='${pam.applyDemandId}'>
														<input type='text' name='projectCode' id='projectCode' value='${pam.projectCode}' size='14' readonly='readonly' >
													</td>
													<td class='tdpadding'>
														<input type='text' name='budgetYear' id='budgetYear' value='${pam.budgetYear}' size='2' readonly='readonly' >
													</td>
													<td class='tdpadding'><input type='hidden' name='applyDetailId' value='${pam.applyDetailId}'>
														<input type='hidden' name='demandDetailId' value='${pam.demandDetailId}'>
														<input type='text' name='assetsName' size='10' id='txtAssetsName' value='${pam.assetsName}' readonly='readonly'>
														<input type='hidden' name='mergeNumber' value='${pam.mergeNumber}' id='txtDetailMergeNumber' size='1'>
													</td>
													
													<td class='tdpadding'><input type='text' size='10' name='specificationParameter' id='txtSpecificationParameter' value='${pam.specificationParameter}' readonly='readonly' ></td>
													<td class='tdpadding'><input type='text' size='10' name='functions' id='txtFunctions' value='${pam.functions}' readonly></td>
													<td class='tdpadding'><input type='text' size='10' name='optionalAccessories' id='txtOptionalAccessories' value='${pam.optionalAccessories}' readonly='readonly' ></td>
													<td class='tdpadding'><input type='text' size='10' name='acceptanceCriteria' id='txtAcceptanceCriteria' value='${pam.acceptanceCriteria}' readonly='readonly' ></td>
													
													<td class='tdpadding'>
														<input type='text' name='budgetDepartment' value='${pam.budgetDepartmentName}' size='10' readonly>
														<input type='hidden' name='budgetDepartmentCode' value='${pam.budgetDepartmentCode}'>
													</td>
													<td class='tdpadding'><input class='rightalign' type='text' name='applyCount' value='${pam.applyCount}' size='5' readonly></td>
													
													<td class='tdpadding'><input type='text' name='units' value='${pam.units}' id='txtUnits' size='2' readonly='readonly'></td>
													<td class='tdpadding'><input class='rightalign' type='text' name='inquiryUnitPrice' value='${pam.inquiryUnitPrice}' size='5' readonly='readonly'></td>
													<td class='tdpadding'><input type='text' name='requirementsDate' value='<fmt:formatDate value='${pam.requirementsDate}'  pattern='yyyy-MM-dd'/>'  size='6' readonly='readonly'></td>
													<td class='tdpadding'>
														<input type='text' name='costCenter' value='${pam.costCenter}'   readonly='readonly'>
														<input type='hidden' name='costCenterCode' value='${pam.costCenterCode}' id='txtCostCenterCode'>
													</td>
													<td class='tdpadding'><input type='text' name='useDescription' value='${pam.useDescription}' readonly='readonly'></td>
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
      	<div title="付款条件" data-options="closable:false" style="overflow:auto;padding:20px;">
      		<table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder">
				<tbody>
				<tr>
					<td class="titlefontsize" align="left" colspan="4">
						<input id="addPayRows" type="button" value="增行">
						<input id="delPayRows" type="button" value="删行">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="Table10" width="100%"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
							<tbody>
							<tr>
								<td class="tdtitle" style="text-align: right" colspan="2">
									<div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
										<div>
											<table id="table_pay" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
												<tr style="white-space:nowrap;">
													<th scope="col">
					                                    <input id="gvdataList_CheckAll_Pay" type="checkbox" name="gvdataList$ctl01$CheckAll"
					                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_pay');"/>
					                                </th>
													<th scope="col" class="tdpadding">付款期数</th>
													<th scope="col" class="tdpadding">付款类型</th>
													<th scope="col" class="tdpadding">付款比例(%)</th>
													<th scope="col" class="tdpadding">付款说明</th>
												</tr>
											<tbody id="payment_tbody">
												<c:forEach items="${paymentTerms}" var="pt">	
												<tr class="bodyTr" style="white-space:nowrap;">
													<td>
				                                        <input id="gvDataList_chkBolDel_0" type="checkbox" name="gvDataList_chkBolDel_0">
				                                    </td>
													<td class="tdpadding"><input size="5" type="text" name="nper" id='nper' class='rightalign' value="${pt.nper}" readonly></td>
													<td class="tdpadding">
														<select style="width: 150px;" name="paymentType">
															<%-- <option value="${pt.paymentType}"}>${pt.paymentType}</option> --%>
															<option value="预付款" ${pt.paymentType=="预付款"?"selected='selected'":""}>预付款</option>
															<option value="验收款" ${pt.paymentType=="验收款"?"selected='selected'":""}>验收款</option>
															<option value="尾款" ${pt.paymentType=="尾款"?"selected='selected'":""}>尾款</option>
															
														</select>
													</td>
													<td class="tdpadding"><input type="text" name="paymentRatio" id="paymentRatio" value="${pt.paymentRatio}" size="10" class="rightalign"></td>
													<td class="tdpadding"><input type="text" name="paymentRemarks" id="paymentRemarks" value="${pt.paymentRemarks}" size="30"></td>
												</tr>
												</c:forEach>
											</tbody>
												<tr class="bodyTr" style="white-space:nowrap;">
													<td></td>
													<td class="tdpadding">合计</td>
													<td class="tdpadding"></td>
													<td class="tdpadding"><input id="sumPaymentRatio" type="text" name="" value="100.00" size="10" class="rightalign" readonly="readonly"></td>
													<td class="tdpadding"></td>
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
				<tr>
					<td class="titlefontsize" align="left" colspan="4">
						<p style="margin-left: 10px;">提示：</p><p style="margin-left: 40px;">1、预付款：根据合同或协议约定，发货前预付或验收前支付。</p>
						<p style="margin-left: 40px;">2、验收款：验收合格后开具全额发票，根据合同或协议支付一定比例的验收款。</p>
						<p style="margin-left: 40px;">3、尾款：验收合格后在质保周期内的质保金。</p>
					</td>
				</tr>
			</table>
      	</div>
      </div>
        
        
    </div>
    

	<!-- 点击増行弹出form -->
	
	<div id="addApplyWindow" name="" class="easyui-window" title="添加明细" data-options="iconCls:'icon-save'" style="width:800px;height:440px;padding:5px;" closed="true">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'" style="padding:10px;">
			
	    <form action="addPurchasepurchaseOrderDetail" id="addPurchasepurchaseOrderDetailForm">
	    	<table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="70%">
	            <tbody><tr>
	                <td align="right" class="tdtitle">申请单号:
	                </td>
	                <td class="tdtext">
	                    <input name="cdocument"  type="text" id="txtpurchaseOrderId" class="colorblur">
	                </td>
	                <td align="right" class="tdtitle">申请日期:
	                </td>
	                <td class="tdtext">
	                    <input name="applyDateStart"  type="text" id="txtBeginDate" class="Wdate" onclick="WdatePicker({skin:'ext'});">~<input name="applyDateEnd" type="text" id="txtEndDate" class="Wdate" onclick="WdatePicker({skin:'ext'});">
	                </td>
	            </tr>
	            <tr>
	                <td align="right" class="tdtitle">申请人:
	                </td>
	                <td class="tdtext">
	                    <input name="applyUser" type="text" id="txtApplyUser" class="colorblur">
	                </td>
	                <td class="tdtitle" align="right">
	                </td>
	                <td class="tdtext">
	                </td>
	            </tr>
	            </tbody>
	         </table>
	    
		</form>
	    
	    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
	        <input type="button" name="btnDetailQuery" value="查询" id="btnDetailQuery" class="btn">
	        <input type="button" name="btnDetailSave" value="提交" id="btnDetailSave" class="btn">
	    </div>
	    <div style="margin-top: 10px">
	        
	        
	        <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder">
	            <tbody>
	            
	            <tr>
	                <td colspan="4">
	                    <table id="Table10" width="100%"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
	                        <tbody>
	                        <tr>
	                            <td class="tdtitle" style="text-align: right" colspan="2">
	                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
	                                    <div>
	                                    	
	                                        <table id="table_add_detail" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
	                                            
	                                            <tr style="white-space:nowrap;">
	                                                <th scope="col">
	                                                    <input id="gvDataList_CheckAll_Add" type="checkbox" name="gvDataList$ctl01$CheckAll"
	                                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_add_detail');"/>
	                                                </th>
	                                                <th scope="col" class="tdpadding"><font color="red" >*</font>采购申请单号</th>
	                                                <th scope="col" class="tdpadding"><font color="red" >*</font>主编码</th>
	                                                <!-- <th scope="col" class="tdpadding"><font color="red" >*</font>次级</th> -->
	                                                <th scope="col" class="tdpadding"><font color="red" >*</font>资产名称</th>
	                                                <th scope="col" class="tdpadding"><font color="red" >*</font>规格参数</th>
	                                                <!-- 5 -->
	                                                <th scope="col" class="tdpadding"><font color="red" >*</font>功能</th>
	                                                <th scope="col" class="tdpadding"><font color="red" >*</font>选配件</th>
	                                                <th scope="col" class="tdpadding"><font color="red" >*</font>验收标准</th>
	                                                <th scope="col" class="tdpadding">申请数量</th>
	                                                <th scope="col" class="tdpadding">采购数量</th>
	                                                <th scope="col" class="tdpadding">采购单价(含税)</th>
	                                                <!-- 10 -->
	                                                <th scope="col" class="tdpadding">要求到位日期</th>
	                                                <th scope="col" class="tdpadding">预算成本中心</th>
	                                            </tr>
	                                            <tbody id="tbody_add_detail">
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
	</div>
	
	<div id="addApplyDetailWindow" name="" class="easyui-window" title="明细" data-options="iconCls:'icon-save'" style="width:825px;height:280px;padding:5px;" closed="true">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'" style="padding:10px;">
					<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
				        <input type="button" name="btnDetailSure" value="确定" id="btnDetailSure" class="btn">
				    </div>
				    <div style="margin-top: 10px">
				        <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder">
				            <tbody>
				            <tr>
				                <td colspan="4">
				                    <table id="Table10" width="100%"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
				                        <tbody>
				                        <tr>
				                            <td class="tdtitle" style="text-align: right" colspan="2">
				                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
				                                    <div>
				                                        <table id="table_add_detail_mapping" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
				                                            <tr style="white-space:nowrap;">
				                                                <th scope="col" class="tdpadding">申请人</th>
				                                                <th scope="col" class="tdpadding">申请部门</th>
				                                                <th scope="col" class="tdpadding">申请数量</th>
	                                                			<th scope="col" class="tdpadding">未采购数量</th>
				                                                <th scope="col" class="tdpadding">采购数量</th>
				                                                <th scope="col" class="tdpadding">预算成本中心</th>
				                                                <th scope="col" class="tdpadding">采购需求单号</th>
				                                            </tr>
				                                            <tbody id="tbody_add_detail_mapping">
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
	</div>
	
	<div id="supplierWindow" name="" class="easyui-window" title="选择供应商" data-options="iconCls:'icon-save'" style="width:750px;height:400px;padding:5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px; ">
			 
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

	<div id="ysssbmWindow" name="" class="easyui-window" title="归口/预算部门" data-options="iconCls:'icon-save'"
		 style="width:820px;height:450px;border: 0px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="border: 0px;">
				<!-- <ul id="tree1"></ul>  -->
				<form action="" id="budgetDeptForm">
					<table width="100%">
						<tbody>
						<tr>
							<td align="right" class="tdtitle" style="width:10%;">部门:</td>
							<td class="tdtext">
								<input name="departmentName" value="${param.departmentName}" type="text" id="dept" class="colorblur">
							</td>
							<td align="right" class="tdtitle" style="width:10%;">成本中心:</td>
							<td class="tdtext">
								<input name="costCenter" value="${param.costCenter}" type="text" id="costCenter">
							</td>
						</tr>
						</tbody>
					</table>
				</form>
				<input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn" onclick="searchDept()">
				<input type="button" value="确定" id="btnDept" class="btn">
				<div>
					<table  class="adminlist" cellspacing="0" rules="all" border="1"  style="border-collapse:collapse;">
						<tr>
							<td class='tdpadding' scope="col"></td>
							<td class='tdpadding' scope="col">部门名称</td>
							<td class='tdpadding' scope="col">部门编码</td>
							<td class='tdpadding' scope="col">成本中心名称</td>
							<td class='tdpadding' scope="col">成本中心编码</td>
						</tr>
						<tbody id="tbody_add_dept"></tbody>
					</table>
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
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>

<script type="text/javascript">
	var dg = frameElement.lhgDG;//定义dg 关闭需要
	
	var message = "${updateErrorMessage}";
	if(message!=""){
		alert(message);
		dg.cancel();
	}

	function searchDept() {
		var dept = $("#budgetDeptForm").serializeObject();
		dept.companyCode = $("#txtCompanyCode").val();
		$.ajax({
			type: "POST",
//			dataType: "json",
			contentType: "application/json; charset=utf-8",
			url: "${dynamicURL}/purchase/api/searchDepartment",
			data: JSON.stringify(dept),
			success: function(data) {
				var divHtml="";
				for (var i = 0; i < data.length; i++) {
					divHtml += "<tr>";
					if(i==0){
						divHtml += "<td><input type='radio' name='btnRadio' checked='checked'></td>";
					}else{
						divHtml += "<td><input type='radio' name='btnRadio'></td>";
					}
					divHtml += "<td id='departmentName'>" + data[i].departmentName + "</td>";
					divHtml += "<td id='departmentCode'>" + data[i].departmentCode + "</td>";
					divHtml += "<td id='tdcostCenter'>" + data[i].costCenter + "</td>";
					divHtml += "<td id='tdcostCenterCode'>" + data[i].costCenterCode + "</td>";
					divHtml += "</tr>";
				}
				$("#tbody_add_dept").html(divHtml);
			}
		});
	}
	//预算所属部门 开始
	$('#btntxtYsssbmShow').click(function(){
		var top = "45px";//$(this).offset().top - 350;
		var left = "30px";//$(this).offset().left-180;
		$('#ysssbmWindow').window('open').window('resize',{top: top,left:left});
		searchDept();
	});

  	function getCostCenter(){
  		var ckscode = $("#txtYsssbm").val();
  		var ccompanycode = $("#txtCompanyCode").val();

  		if(!ckscode){
			//资产类型是空
			return false;
		}
		if(!ccompanycode){
			//资产类型是空
			return false;
		}
  		//获得工厂
  		$.ajax({
			type : "POST",
			async : false,
			url : "${dynamicURL}/purchase/api/searchFactoryService",
			data : "ccompanyCode="+ccompanycode,
			success: function(dat){
				if(dat){
					var html ="";
					if(dat && dat.length>1){
						html += "<option value=''>请选择</option>";
					}
					for (var i = 0; i < dat.length; i++) {
						var cfactoryCode = dat[i].cfactoryCode;
						
						html += "<option value='"+cfactoryCode+"'>"+cfactoryCode+"</option>";
					}
					$("#factoryCode").html(html);
				}
			}
  		});
  		
  		//获得成本中心
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
					//根据成本中心和资产类型带出所有匹配数据
					setValues();
				}
			}
  		});
  	}
	$('#txtCompanyCode').change(function(){
		getCostCenter();
	});
	
	function linkage() {
		var ckscode = $("#txtYsssbm").val();
	 	var cksname = $("#txtBudgetDepartment").val();
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
				$("#txtCompanyCode").html(html);
				$('#ysssbmWindow').window('close');
				if($("#txtCompanyCode").val()!=""){
					getCostCenter();
				}
				/* if(!treeObj){
					alert("没有配置该成本中心");
					cleanDep();//清除根据成本中心带出的信息
					return false;
				}
				$("#txtCostCenter").val(treeObj.ccostcentername);
				$("#txtCostCenterCode").val(treeObj.ccostcentercode);
				$("#txtCompany").val(treeObj.ccompanyname);
				$("#txtCompanyCode").val(treeObj.ccompanycode);
            	$("#factoryCode").val(treeObj.ccompanycode);
				//根据成本中心和资产类型带出所有匹配数据
				setValues(); */
			}
		});
		/*
		if($('#txtCompanyCode').val()==""){
			return false;
		}
		var companyCode =  $('#txtCompanyCode').val();
		var costCenterCode =  $('#txtCostCenterCode').val();

		
		//设置工厂代码
		$("#factoryCode").val(companyCode);
		
		// 	根据公司带 资产类型
		$("#txtAssetType1 option").remove();
		$.ajax({
			type : "POST",
			async : false,
			url : "${dynamicURL}/userInfo/api/comAsset",
			data : "companyCode=" + companyCode,
			success: function(assetlist){
				//$("#txtAssetType1").append("<option  value='' selected='selected'>--请选择--</option>");
				for ( var i = 0; i < assetlist.length; i++) {
					$("#txtAssetType1").append("<option value='" + assetlist[i].assetsType + "'>"+ assetlist[i].assetsType + "</option>");
				};		
			}
		});
		//	根据公司查询 平台 
		$("#txtPlatform").val("");
		$.ajax({
			type : "POST",
			async:false,
			url : "${dynamicURL}/userInfo/api/comPlatform",
			data : "costCenterCode=" + costCenterCode,
			success : function(platformlist) {
				$("#txtPlatform").val(platformlist.platform);
			}
		});*/
	}
	//根据成本中心和资产类型带出数据
	function setValues(){
		var costCenterCode = $("#txtCostCenterCode").val();
		var assetType = $("#txtAssetType1").val();
		if(!costCenterCode){
			//资产类型是空
			return false;
		}
		if(!assetType){
			//资产类型是空
			return false;
		}
		$.ajax({
			 type: "POST",
			 cache:false,
	         url: "${dynamicURL}/attchConfig/api/searchByCostcenterCodeAndAssetType",
	         data: {"costcenterCode":costCenterCode,"assetType":assetType},
	        // dataType: "json",
	         async: false,
	         success: function(data){
	        	 if(!data){
	        		 alert("成本中心没有配置该资产类型!");
	        	 }
	        	 $("#txtPlatform").val(data.platform);
	        	 $("#attachDepartManagerName").val(data.attachDepartManagerName);
	        	 $("#attachDepartManagerCode").val(data.attachDepartManagerCode);
	        	 $("#attachDepartAdminName").val(data.attachDepartAdminName);
	        	 $("#attachDepartAdminCode").val(data.attachDepartAdminCode);
	         }
		});
	}

    $("#btnDept").click(function(){
		clearRows();
        $('#ysssbmWindow').window('close');
        var departmentCode = $.trim($("input:radio[name='btnRadio']:checked").parent().parent().find("#departmentCode").html());
        var departmentName = $.trim($("input:radio[name='btnRadio']:checked").parent().parent().find("#departmentName").html());
        setTree(departmentCode, departmentName);
    });
	//清除根据成本中心带出的信息
	function cleanDep() {
		$("#txtYsssbm").val("");
		$("#txtBudgetDepartment").val("");
		$("#txtCompanyCode").html("");/////////////////////
		$("#txtPlatform").val("");
		//$("#txtAssetType1").html("");
		$("#factoryCode").val("");
		$("#txtCostCenter").val("");
		$("#txtCostCenterCode").val("");
	}
	function setTree(ckscode,cksname){
		 //var ckscode = $("#tree1").tree('getSelected').id;
		 //var cksname = $("#tree1").tree('getSelected').text;
		 $("#txtYsssbm").val(ckscode);
		 $("#txtBudgetDepartment").val(cksname);
		 $('#ysssbmWindow').window('close');
		 //预算所属部门 结束	
		 //联动 
		 linkage();
		 
	}
	
	$("#delRows").click(function(){//删除行
	    $("#tbody_detail").find("input:checkbox[name='delcheck']:checked").each(function(){
	    	
	    	var delMergeNumber = $(this).parent().parent().find("#applyDetailId").val();
	    	$("#tbody_detail_detail").find("input[name='applyDetailId']").each(function(){
	    		if ( delMergeNumber == $(this).val() ) {
	    			$(this).parent().parent().remove();
	    		}
	    	});
	        $(this).parent().parent().remove();
	    });
	    $("#gvDataList_CheckAll").attr("checked", false);
		sumAll();
	});
	//申请明细
  	var obj = {
  			id: "",
  			purchaseOrderId: "",
  			assetsName: "",
  			applyCount: "",
  			createBy: "",
  			createDate: "",
  			updateBy: "",
  			updateDate: "",
  			mergeNumber: "",
  			receivedCount: "",
  			notReceivedCount: "",
  			applyUser: "",
  			applyUserCode: "",
  			createByCode: "",
  			updateByCode: "",
  			applyDetailId: "",
  			inquiryUnitPrice: "",
  			purchaseUnitPrice: "",
  			purchasePrice: "",
  			warrantyPeriod: "",
  			currencyCode: "",
  			exchangeRate: "",
  			purchasePriceLocal: "",
  			netPrice: "",
  			brand: "",
  			requirementsDate: "",
  			tax: "",
  			applyOrderId: "",
  			ledgerAccount: "",
  			mainAssetCode:""
  	};
	//资产明细
  	var objdetail = {
  			id: "",
  			assetsName: "",
  			applyDetailId: "",
  			purchaseId: "",
  			applyCount: "",
  			createBy: "",
  			createDate: "",
  			updateBy: "",
  			updateDate: "",
  			mergeNumber: "",
  			demandDetailId: "",
  			applyDemandId: "",
  			projectCode: "",
  			budgetYear: ""
	};
	//付款条件
	var objpay={
  			id: "",
  			purchaseId: "",
  			nper: "",
  			paymentType: "",
  			paymentRatio: "",
  			paymentRemarks: ""
	}
	//提交表单
	function saveOrder(strDraft,isMail, isPhoneMessage, idea, activityId){
		var PurchaseOrderWrapper = {};
		//添加表头
		var objHeader = $("#purchaseOrderFormHeader").serializeObject();
        //申请明细
        var obj1 = {
            id: "",
            purchaseOrderId: "",
            assetsName: "",
            applyCount: "",
            createBy: "",
            createDate: "",
            updateBy: "",
            updateDate: "",
            mergeNumber: "",
            receivedCount: "",
            notReceivedCount: "",
            applyUser: "",
            applyUserCode: "",
            createByCode: "",
            updateByCode: "",
            applyDetailId: "",
            inquiryUnitPrice: "",
            purchaseUnitPrice: "",
            purchasePrice: "",
            warrantyPeriod: "",
            currencyCode: "",
            exchangeRate: "",
            purchasePriceLocal: "",
            netPrice: "",
            brand: "",
            requirementsDate: "",
            tax: "",
            applyOrderId: "",
            ledgerAccount: "",
            mainAssetCode:""
        };
        //资产明细
        var objdetail2 = {
            id: "",
            assetsName: "",
            applyDetailId: "",
            purchaseId: "",
            applyCount: "",
            createBy: "",
            createDate: "",
            updateBy: "",
            updateDate: "",
            mergeNumber: "",
            demandDetailId: "",
            applyDemandId: "",
            projectCode: "",
            budgetYear: ""
        };
        //付款条件
        var objpay1={
            id: "",
            purchaseId: "",
            nper: "",
            paymentType: "",
            paymentRatio: "",
            paymentRemarks: ""
        }
		//获得税码 (J0 J1)
		var taxRate = $("#taxRate").find("option:selected").text();
		var taxRateCode = taxRate.substr(0,2);
		objHeader.taxRateCode = taxRateCode;//税码
		
		objHeader.draft = strDraft;
		objHeader.idea = idea;
		objHeader.isMail = isMail;
		objHeader.isPhoneMessage = isPhoneMessage;
		objHeader.activityId = activityId;
		objHeader.orderMoney = $("#sumPurchasePrice").val();
		//获得币种名称
		objHeader.currencyName = $("#currencyCode").find("option:selected").text();
		objHeader.currencyCode = $("#currencyCode").val();
		//公司名称
		objHeader.companyName = $("#txtCompanyCode").find("option:selected").text();
		PurchaseOrderWrapper.purchaseOrder = objHeader;
	
	
		///获得表单明细信息 封装Json对象
		var objArr = constructObjectFromTable(obj1, $("#tbody_detail .bodyTr"));
		PurchaseOrderWrapper.purchaseOrderDetails = objArr;

		///获得资产明细信息 封装Json对象
		var objArrdetail = constructObjectFromTable(objdetail2, $("#tbody_detail_detail .bodyTr"));
		PurchaseOrderWrapper.purchaseApplyMappings = objArrdetail;
		
		///获得付款条件信息 封装Json对象
		var objPrr = constructObjectFromTable(objpay1, $("#payment_tbody .bodyTr"));
		PurchaseOrderWrapper.paymentTerms = objPrr;
		
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        contentType: "application/json; charset=utf-8",
	        url: "${dynamicURL}/purchase/api/updatePurchase",
	        data: JSON.stringify(PurchaseOrderWrapper),
	        success: function(data) {
	        	if (data.errorMessages.length > 0) {
                    var str = data.errorMessages;
                    alert(str);
                    if(str.indexOf("修改成功")) {
                        dg.curWin.refresh();
                    }
                    return;
	            }
	            alert("修改成功！");
	            //关闭窗口
	            dg.curWin.refresh();
//	            dg.cancel();
	            return;
	        }
	    });
	}
	function checkAll(){
		/*var sumInquiryPrice = $("#sumInquiryPrice").val();
		 if(sumInquiryPrice==""||isNaN(sumInquiryPrice)){
			alert("金额不正确!");
			return false;
		} */
		if(!$("#txtAssetType1").val()) {
			alert("请选择资产类型");
			return false;
		}
		if(!$("#txtCompanyCode").val()) {
			alert("请选择公司");
			return false;
		}
		if(!$("#deliveryAddress").val()) {
			alert("送货地址不能为空");
			return false;
		}
		if($("#deliveryAddress").val().length>250){
			alert("送货地址长度不能超过250！");
			return false;
		}
		if($("#buyerRemark").val().length>250){
			alert("采购员备注长度不能超过250！");
			return false;
		}
		if($("#headerTextDescription").val().length>250){
			alert("抬头文本说明长度不能超过250！");
			return false;
		}
		if(!$("#txtCostCenterCode").val()) {
			alert("成本中心不能为空");
			return false;
		}
		if(!$("#orderType").val()) {
			alert("请选择订单类别");
			return false;
		}
		if(!$("#txtYsssbm").val()) {
			alert("请选择归口/预算部门");
			return false;
		}
		if(!$("#factoryCode").val()) {
			alert("请 选择工厂代码");
			return false;
		}

		var sumApplyCount = $("#sumApplyCount").val();
		if(sumApplyCount==""||isNaN(sumApplyCount)){
			alert("采购数量不正确!");
			return false;
		}
		var sumPurchasePrice = $("#sumPurchasePrice").val();
		if(sumPurchasePrice==""||isNaN(sumPurchasePrice)){
			alert("采购总价不正确!");
			return false;
		}
		var f = false;
		$("input[id='warrantyPeriod']").each(function(){
			if($(this).val()==""||isNaN($(this).val())) {
				alert("质保周期必须是整数");
				f = true;
				return false;
			}
		});
		if(f){
			return false;
		}
		//品牌不能为空
		$("input[id='brand']").each(function(){
			if($(this).val()==""){
				alert("型号规格/品牌不可为空！");
				f=true;
				return false;
			}
			if($(this).val().length>25){
				alert("型号规格/品牌长度不能超过25！");
				f=true;
				return false;
			}
		});
		if(f){
			return false;
		}
		var pIndex=0;
		var pcount = 0;//验收款出现次数
		var pcount2 = 0;//尾款出现次数
		var pcount3 = 0;//预付款出现次数
		var pName = "预付款";
		$("select[name='paymentType']").each(function(){
			pIndex++;
			if(pName=="验收款" && $(this).val()=="预付款"){
				alert("第"+pIndex+"行,"+$(this).val()+"不能在"+pName+"之后!");
				f=true;
				return false;
			}
			if(pName=="尾款" && ($(this).val()=="预付款" || $(this).val()=="验收款")){
				alert("第"+pIndex+"行,"+$(this).val()+"不能在"+pName+"之后!");
				f=true;
				return false;
			}
			pName = $(this).val();
			if($(this).val() == "验收款"){
				pcount++;
			}
			if($(this).val() == "尾款"){
				pcount2++;
			}
			if($(this).val() == "预付款"){
				pcount3++;
			}
		});
		if(f){
			return false;
		}
		if(pcount>1){
			alert("付款条件中：验收款只能选择一行");
			return false;
		}
		if(pcount2>0){
			if(pcount!=1){
				alert("付款条件中：验收款只能选择一行");
				return false;
			}
		}
		/* //一次性供应商 并且预付款出现次数大于0
		if(($("#supplierCode").val()=="1" || $("#supplierCode").val()=="2") && (pcount2 > 0 || pcount3 > 0)){
			alert("一次性供应商不能有预付款！");
			return false;
		} */
		if($("#sumPaymentRatio").val()!="100.00"){
			alert("付款比例总和必须是100.00");
			return false;
		}
		//获得税码 (J0 J1)
		var taxRate = $("#taxRate").find("option:selected").val();
		if (taxRate == "") {
			alert("请选择税码/税率！");
			return false;
		}
		//验证超出15%
		var f = false;
		/*$("input[id='purchaseUnitPrice']").each(function(){
			var txtInquiryUnitPrice = $(this).parent().parent().find("#txtInquiryUnitPrice").val();
			if(($(this).val()-0)/(txtInquiryUnitPrice-0)>1.15) {
				alert("采购单价不能超过询价单价的115%");
				f = true;
				return false;
			}
		});*/
		if(f){
			return false;
		} 
		return true;
	}

	var changeAmount="Y";
	function showAgreeDialog() {
		var document = $("#txtPurchaseOrderId").val();
		if(($("#oldOrderMoney").val()-0)-($("#sumPurchasePriceLocal").val()-0)==0){
			changeAmount="N";
		}
        var url = "${dynamicURL}/workflow/showUpdateCommitDialog?parentWindowId=update&document=" + document + "&typeId="+77021+"&changeAmount="+changeAmount;

        var dlg = new dg.curWin.$.dialog({ id: 'agreeDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();

        return dlg;
    }
	function commitAgree(isMail, isPhoneMessage, idea, activityId) {

		saveOrder("N",isMail, isPhoneMessage, idea, activityId);
		$("#btnSave").val("提交");
		$("#btnSave").removeAttr("disabled"); //移除disabled属性
		$("#btnSaveDraft").removeAttr("disabled");
		$("#btnCancel").removeAttr("disabled");
    }
	$("#btnSave").click(function() {//提交
		if(checkAll()){
            $("#btnSave").val("提交...");
            $("#btnSave").attr("disabled", "disabled");//添加disabled属性
            $("#btnSaveDraft").attr("disabled", "disabled");
            $("#btnCancel").attr("disabled", "disabled");
			showAgreeDialog();
		}
	});
	
	$("#btnSaveDraft").click(function() {//保存草稿
		if(checkAll()){
			saveOrder("Y","","","","");
		}
	});

	$("#addRows").click(function(){
		if(!$("#txtCompanyCode").val()) {
    		alert("请先选择所属公司!");
    		return false;
    	}
		if(!$("#txtYsssbm").val()) {
    		alert("请先选择预算所属部门!");
    		return false;
    	}
		if(!$("#supplierCode").val()) {
    		alert("请先选择供应商!");
    		return false;
    	}
		if(!$("#orderType").val()) {
    		alert("请先选择订单类别!");
    		return false;
    	}
		if(!$("#txtAssetType1").val()) {
    		alert("请先选择资产类型!");
    		return false;
    	}
		var top = "90px";
		var left = "15px";
		$('#addApplyWindow').window('open').window('resize',{width:'800px',height:'440px',top: top,left:left});
		if($("#tbody_detail_detail").html()==""){
			searchApplyDetail();
		}
	});
	$("#btnDetailQuery").click(function() {
		searchApplyDetail();
	});
	//purchaseDemandMapping  申请单行明细对应的所有需求明细
	var purchaseDemandMapping;
	//oldPurchaseApplyMappingModels  修改前原订单的资产明细
	var oldPurchaseApplyMappingModels;
	//oldPurchaseApplyMappingModels  修改前原订单明细
	var oldPurchaseOrderDetailModels;
	//修改需要的特殊处理的ids
	var strids = "";
	//选择明细
	function searchApplyDetail(){
		var dat = $("#addPurchasepurchaseOrderDetailForm").serializeObject();
		//采购员
		dat.buyerCode = $("#txtBuyerCode").val();
		//预算所属部门编码
		dat.budgetDepartmentCode = $("#txtYsssbm").val();
		//资产类型
		dat.assetType = $("#txtAssetType1").val();
		//公司编码
    	dat.companyCode = $("#txtCompanyCode").val();
		//平台
		dat.platform = $("#txtPlatform").val();

		//是否一次性费用化资产 
		dat.expensing = $("#orderType").val()=="ZAB"?"N":"Y";
		//修改需要的特殊处理的ids
    	dat.ids = strids;
	    $.ajax({
	        type: "POST",
	        dataType: "json",
	        contentType: "application/json; charset=utf-8",
	        url: "${dynamicURL}/apply/api/searchApplyDetail",
	        data: JSON.stringify(dat),
	        success: function(data) {
	        	//把原订单的资产明细数量 累加到purchaseDemandMapping
	        	purchaseDemandMapping=data[1];
	        	for (var i = 0; i < purchaseDemandMapping.length; i++) {
	        		for(var j = 0; j < oldPurchaseApplyMappingModels.length; j++)
	        		if(purchaseDemandMapping[i].id == oldPurchaseApplyMappingModels[j].applyDemandId){
		        		purchaseDemandMapping[i].notApplyCount += oldPurchaseApplyMappingModels[j].applyCount-0;
	        		}
	        	}
				var divHtml="";
				for (var i = 0; i < data[0].length; i++) {
					for (var j = 0; j < oldPurchaseOrderDetailModels.length; j++) {
						if(data[0][i].id == oldPurchaseOrderDetailModels[j].applyDetailId){
							data[0][i].notApplyCount += oldPurchaseOrderDetailModels[j].applyCount-0;
						}
					}
					var strDate = getLocalTime(data[0][i].requirementsDate);
					divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
					divHtml += "<td class='tdpadding'><input id='gvdata[0]List_chkBolDel_0' type='checkbox' name='addgvdata[0]List_chkBolDel_0'></td>";
					divHtml += "<td class='tdpadding'><input type='hidden' name='applyDetailId' id='purchaseOrderDetailId' value='"+data[0][i].id+"'><input type='hidden' name='ledgerAccount' id='ledgerAccount' value='"+data[0][i].ledgerAccount+"'><input type='hidden' name='mergeNumber'  value='"+data[0][i].mergeNumber+"'><input size='14' type='text' name='applyOrderId' readonly='readonly' value='"+data[0][i].applyOrderId+"'></td>";
					divHtml += "<td class='tdpadding'><input size='10' type='text' name='mainAssetCode' id='txtMainAssetCode' value='"+data[0][i].mainAssetCode+"' readonly='readonly'></td>";
					/* divHtml += "<td class='tdpadding'><input size='8' type='text' name='secondary' id='txtSecondary' value='"+data[0][i].secondary+"' readonly='readonly'></td>"; */
					divHtml += "<td class='tdpadding'><input type='text' name='assetsName' id='txtAssetsName' value='"+data[0][i].assetsName+"' readonly='readonly'></td>";
	                // 5
					divHtml += "<td class='tdpadding'><input type='text' name='specificationParameter' id='txtSpecificationParameter' value='"+data[0][i].specificationParameter+"' readonly='readonly' ></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='functions' id='txtFunctions' value='"+data[0][i].functions+"' readonly='readonly' ></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='optionalAccessories' id='txtOptionalAccessories'  value='"+data[0][i].optionalAccessories+"' readonly='readonly' ></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='acceptanceCriteria' id='txtAcceptanceCriteria' value='"+data[0][i].acceptanceCriteria+"' readonly='readonly' ></td>";
	                // 10
					divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='applyCount' size='5' value='"+data[0][i].demandCount+"' readonly='readonly' ></td>";
					divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='thisApplyCount' value='"+data[0][i].notApplyCount+"' id='txtThisApplyCount' size='5' readonly='readonly' applyid='"+data[0][i].applyOrderId+"' mergenumber='"+data[0][i].mergeNumber+"'></td>";
	                // 15
					divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='inquiryUnitPrice' value='"+data[0][i].purchaseUnitPrice+"' size='5' readonly='readonly'></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='requirementsDate' value='"+strDate+"' id='txtRequirementsDate' size='7' readonly='readonly'></td>";
					divHtml += "<td class='tdpadding'><input type='text' name='costCenter' value='"+data[0][i].costCenter+"' id='txtCostCenter' readonly='readonly'><input type='hidden' name='costCenterCode' value='"+data[0][i].costCenterCode+"' id='txtCostCenterCode'></td>";
					divHtml += "</tr>";
				}
	            $("#tbody_add_detail").html(divHtml);
	        }
	    });
	}
	var applyid;//采购申请ID
	var mergenumber;//合并号
	//点击(修改)采购数量 弹出明细
	$("#txtThisApplyCount").live("click",function(){
		var top = "150px";
		var left = "30px";
		$('#addApplyDetailWindow').window('open').window('resize',{width:'825px',height:'280px',top: top,left:left});
		
		applyid = $(this).attr("applyid");
		mergenumber = $(this).attr("mergenumber");

    	var strhtml="";
		for (var i = 0; i < purchaseDemandMapping.length; i++) {
			//遍历申请单行明细对应的所有需求明细  如果申请单号和合并号相同，就显示
			if(purchaseDemandMapping[i].applyId==applyid&&purchaseDemandMapping[i].mergeNumber==mergenumber){
				strhtml+="<tr class='bodyTr' style='white-space:nowrap;'>"
					+"<td scope='col' class='tdpadding'>"+purchaseDemandMapping[i].applyUser+"<input type='hidden' value='"+purchaseDemandMapping[i].id+"' id='mappingId' name='id'></td>"
					+"<td scope='col' class='tdpadding'>"+purchaseDemandMapping[i].applyDepartment+"</td>"
					+"<td scope='col' class='tdpadding'><input class='rightalign' type='text' size='5' value='"+purchaseDemandMapping[i].demandCount+"' id='mappingDemandCount' name='mappingDemandCount' readonly></td>"
					+"<td scope='col' class='tdpadding'><input class='rightalign' type='text' size='5' value='"+purchaseDemandMapping[i].notApplyCount+"' id='mappingNotApplyCount'  name='mappingNotApplyCount' readonly></td>"
					+"<td scope='col' class='tdpadding'><input class='rightalign' type='text' size='5' value='"+purchaseDemandMapping[i].notApplyCount+"' id='mappingApplyCount'  name='mappingApplyCount'></td>"
					+"<td scope='col' class='tdpadding'>"+purchaseDemandMapping[i].costCenter+"</td>"
					+"<td scope='col' class='tdpadding'>"+purchaseDemandMapping[i].demandOrderId+"</td></tr>";
			}
		}
		$("#tbody_add_detail_mapping").html(strhtml);
	});
	//明细框  addApplyDetailWindow 确定按钮
	$("#btnDetailSure").click(function(){
		var obj = {
			id: "",
			mappingDemandCount: "",
			mappingNotApplyCount: "",
			mappingApplyCount: ""
		};
		//封装Json对象
		var objArr = constructObjectFromTable(obj, $("#tbody_add_detail_mapping .bodyTr"));
		
		var sumCount = 0;
		var flag = false;
		for (var i = 0; i < objArr.length; i++) {
			if(objArr[i].mappingApplyCount==""||isNaN(objArr[i].mappingApplyCount)){
				alert("采购数量必须是数字！");
				flag = true;
				break;
			}
			if(objArr[i].mappingApplyCount > objArr[i].mappingNotApplyCount){
				alert("采购数量不能超过未采购数量！");
				flag = true;
				break;
			}
			for (var j = 0; j < purchaseDemandMapping.length; j++) {
				if(purchaseDemandMapping[j].id==objArr[i].id){
					purchaseDemandMapping[j].applyCount=objArr[i].mappingApplyCount;
				}
			}
			//采购数量总和
			sumCount += objArr[i].mappingApplyCount-0;
		}
		if(flag){
			return;
		}
		$('#addApplyDetailWindow').window('close');
		$("input[applyid='"+applyid+"'][mergenumber='"+mergenumber+"']").val(sumCount);
	});
	$("#txtAssetType1").change(function(){
  		clearRows();
		/* var assetsType = $("#txtAssetType1").children("option:selected").val();
		if(assetsType=="IT资产"){
			$("#companyForOther").hide();
			$("#companyForIt").show();
		}else{
			$("#txtCompany").val("");
			$("#txtCompanyCode").val(""); 
			$("#companyForIt").hide();
			$("#companyForOther").show();
			$("#company").val("");
		} */
		setValues();
	});
	$("#company").change(function(){
		var company = $("#company").children("option:selected").val();
		$("#txtCompany").val(company.split(",")[1]);
		$("#txtCompanyCode").val(company.split(",")[0]);
	});
	//表单封装json
	function constructObjectFromTable(object, $object) {
		var javaObjArray = [];
		
		$object.each(function(i) {
			 var $ck = $(this).find('input[name^=addgvdata]');
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

	$("#btnDetailSave").click(function() {//确定新增明细
		var objj = {
				id: "",
				purchaseOrderId: "",
				assetsName: "",
				applyCount: "",
				createBy: "",
				createDate: "",
				updateBy: "",
				updateDate: "",
				mergeNumber: "",
				receivedCount: "",
				notReceivedCount: "",
				applyUser: "",
				applyUserCode: "",
				createByCode: "",
				updateByCode: "",
				applyDetailId: "",
				inquiryUnitPrice: "",
				purchaseUnitPrice: "",
				purchasePrice: "",
				warrantyPeriod: "",
				currencyCode: "",
				exchangeRate: "",
				purchasePriceLocal: "",
				netPrice: "",
				brand: "",
				requirementsDate: "",
				tax: "",
				applyOrderId: "",
				applyDateStart: "",
				applyDateEnd: "",
				specificationParameter: "",
				functions: "",
				acceptanceCriteria: "",
				optionalAccessories: "",
				mainAssetCode: "",
				secondary: "",
				thisApplyCount: "",
				assetType: "",
				requireDate: "",
				supplierName: "",
				supplierCode: "",
				currentReceiveCount: "",
				costCenter: "",
				costCenterCode: "",
	  			ledgerAccount: ""
			};
		var purchaseOrderDetailModels = constructObjectFromTable(objj, $("#tbody_add_detail .bodyTr"));
		//purchaseDemandMapping
		//采购订单明细
		
        $('#addApplyWindow').window('close');
        
		var divHtml="";
		for (var i = 0; i < purchaseOrderDetailModels.length; i++) {
			divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
			divHtml += "<td class='tdpadding'><input id='gvpurchaseOrderDetailModelsList_chkBolDel_0' type='checkbox' name='delcheck'></td>";
			divHtml += "<td class='tdpadding'><input type='hidden' name='applyDetailId' id='applyDetailId' value='"+purchaseOrderDetailModels[i].applyDetailId+"'><input type='hidden' name='ledgerAccount' id='ledgerAccount' value='"+purchaseOrderDetailModels[i].ledgerAccount+"'><input size='14' type='text' name='applyOrderId' readonly='readonly' value='"+purchaseOrderDetailModels[i].applyOrderId+"'></td>";
			divHtml += "<td class='tdpadding'><input size='10' type='text' name='mainAssetCode' id='txtMainAssetCode' value='"+purchaseOrderDetailModels[i].mainAssetCode+"' readonly='readonly'></td>";
			/* divHtml += "<td class='tdpadding'><input size='8' type='text' name='secondary' id='txtSecondary' value='"+purchaseOrderDetailModels[i].secondary+"' readonly='readonly'></td>"; */
			divHtml += "<td class='tdpadding'><input type='text' size='10' name='assetsName' id='txtAssetsName' value='"+purchaseOrderDetailModels[i].assetsName+"' readonly='readonly'></td>";
               // 5
			divHtml += "<td class='tdpadding'><input type='text' size='10' name='specificationParameter' id='txtSpecificationParameter' value='"+purchaseOrderDetailModels[i].specificationParameter+"' readonly='readonly' ></td>";
			divHtml += "<td class='tdpadding'><input type='text' size='10' name='functions' id='txtFunctions' value='"+purchaseOrderDetailModels[i].functions+"' readonly='readonly' ></td>";
			divHtml += "<td class='tdpadding'><input type='text' size='10' name='optionalAccessories' id='txtOptionalAccessories'  value='"+purchaseOrderDetailModels[i].optionalAccessories+"' readonly='readonly' ></td>";
			divHtml += "<td class='tdpadding'><input type='text' size='10' name='acceptanceCriteria' id='txtAcceptanceCriteria' value='"+purchaseOrderDetailModels[i].acceptanceCriteria+"' readonly='readonly' ></td>";
               // 10
			divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='applyCount' id='txtApplyCount' size='5' value='"+purchaseOrderDetailModels[i].thisApplyCount+"' readonly='readonly' ></td>";
			divHtml += "<td class='tdpadding'><input class='rightalign' type='text' id='txtInquiryUnitPrice' size='5' value='"+purchaseOrderDetailModels[i].inquiryUnitPrice+"' readonly='readonly'></td>";
			divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='purchaseUnitPrice' id='purchaseUnitPrice' value='"+purchaseOrderDetailModels[i].inquiryUnitPrice+"'  size='5'  ><input class='rightalign' type='hidden' name='inquiryUnitPrice' value='"+purchaseOrderDetailModels[i].inquiryUnitPrice+"'></td>";
               // 15
			divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='purchasePrice' id='purchasePrice' value='' size='5'></td>";
			divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='purchasePriceLocal' id='purchasePriceLocal' value='' size='5' readonly='readonly'></td>";
			divHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='netPrice' id='netPrice' value='' size='5' readonly='readonly'></td>";
			divHtml += "<td class='tdpadding'><input type='text' class='rightalign' name='brand' value='' id='brand' size='10'></td>";
			divHtml += "<td class='tdpadding'><input type='text' name='requirementsDate' value='"+purchaseOrderDetailModels[i].requirementsDate+"' id='requirementsDate' size='5' readonly='readonly' class='Wdate' onclick=WdatePicker({skin:'ext'});></td>";
			
			divHtml += "<td class='tdpadding'><input type='text' name='costCenter' value='"+purchaseOrderDetailModels[i].costCenter+"' id='txtCostCenter' readonly='readonly'><input type='hidden' name='costCenterCode' value='"+purchaseOrderDetailModels[i].costCenterCode+"' id='txtCostCenterCode'></td>";
			divHtml += "<td class='tdpadding'><input type='text' class='rightalign' name='warrantyPeriod' value='' id='warrantyPeriod' size='8' ></td>";
			divHtml += "</tr>";
		}
		changeAmount="N";
        $("#tbody_detail").html(divHtml);
        
        //显示资产明细  purchaseDemandMapping
        var divDetailHtml="";
        for (var i = 0; i < purchaseOrderDetailModels.length; i++) {
        	var applyDetailId = purchaseOrderDetailModels[i].applyDetailId;
        	var applyId = purchaseOrderDetailModels[i].applyOrderId;
        	var mergeNumber = purchaseOrderDetailModels[i].mergeNumber;
	        for (var j = 0; j < purchaseDemandMapping.length; j++) {
				var pdm = purchaseDemandMapping[j];
				if(pdm.applyId==applyId && pdm.mergeNumber==mergeNumber && pdm.applyCount>0){
					pdm.applyDetailId = applyDetailId;
					var strDate = getLocalTime(pdm.requirementsDate);
					divDetailHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
					divDetailHtml += "<td class='tdpadding'><input type='hidden' name='applyDemandId' value='"+pdm.id+"'><input type='text' name='projectCode' id='projectCode' value='"+pdm.projectCode+"' size='14' readonly='readonly' ></td>";
					divDetailHtml += "<td class='tdpadding'><input type='text' name='budgetYear' id='budgetYear' value='"+pdm.budgetYear+"' size='2' readonly='readonly' ></td>";
					divDetailHtml += "<td class='tdpadding'><input type='hidden' name='applyDetailId' value='"+pdm.applyDetailId+"'><input type='hidden' name='demandDetailId' value='"+pdm.demandDetailId+"'><input type='text' name='assetsName' id='txtAssetsName' value='"+pdm.assetsName+"' readonly='readonly'><input type='hidden' name='mergeNumber' value='"+pdm.mergeNumber+"' id='txtDetailMergeNumber' size='1'></td>";
                   
					divDetailHtml += "<td class='tdpadding'><input type='text' name='specificationParameter' id='txtSpecificationParameter' value='"+pdm.specificationParameter+"' readonly='readonly' ></td>";
					divDetailHtml += "<td class='tdpadding'><input type='text' name='functions' id='txtFunctions' value='"+pdm.functions+"' readonly></td>";
					divDetailHtml += "<td class='tdpadding'><input type='text' name='optionalAccessories' id='txtOptionalAccessories' value='"+pdm.optionalAccessories+"' readonly='readonly' ></td>";
					divDetailHtml += "<td class='tdpadding'><input type='text' name='acceptanceCriteria' id='txtAcceptanceCriteria' value='"+pdm.acceptanceCriteria+"' readonly='readonly' ></td>";

					divDetailHtml += "<td class='tdpadding'><input type='text' name='budgetDepartment' value='"+pdm.applyDepartment+"' size='10' readonly><input type='hidden' name='budgetDepartmentCode' value='"+pdm.applyDepartmentCode+"'></td>";
					divDetailHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='applyCount' value='"+pdm.applyCount+"' size='5' readonly></td>";
                   
					divDetailHtml += "<td class='tdpadding'><input type='text' name='units' value='"+pdm.units+"' id='txtUnits' size='2' readonly='readonly'></td>";
					divDetailHtml += "<td class='tdpadding'><input class='rightalign' type='text' name='inquiryUnitPrice' value='"+pdm.inquiryUnitPrice+"' size='5' readonly='readonly'></td>";
					divDetailHtml += "<td class='tdpadding'><input type='text' name='requirementsDate' value='"+strDate+"'  size='6' readonly='readonly'></td>";
					divDetailHtml += "<td class='tdpadding'><input type='text' name='costCenter' value='"+pdm.costCenter+"'   readonly='readonly'><input type='hidden' name='costCenterCode' value='"+pdm.costCenterCode+"' id='txtCostCenterCode'></td>";
					divDetailHtml += "<td class='tdpadding'><input type='text' name='useDescription' value='"+pdm.useDescription+"' readonly='readonly'></td>";
					divDetailHtml += "</tr>";
				}			
			}
        }
        $("#tbody_detail_detail").html(divDetailHtml);
        sumAll();
	       
	});
	function getLocalTime(nS) {  
	    var date = new Date(nS);
	    var year = date.getFullYear();
	    var month = date.getMonth()+1;
	    var day = date.getDate();
	    return year+"-"+month+"-"+day;
	} 
	// 根据币种查询汇率
	function selectCurrency(){
		var currency = $('#currencyCode').val();
		$.ajax({
			type : "POST",
			nsync : true,
			url : "${dynamicURL}/userInfo/api/currencyRate",
			data : "currency="+currency,
			success : function(userInfo){
				$("#txtExchangeRate").val(userInfo.iexchangerate);
				sumAll();
			}
		});
	}
	$("#taxRate").change(function(){
		sumAll();
	});
	function sumAll(){
		var exchangeRate = $("#txtExchangeRate").val();//汇率
		var rate = 1;
		rate += $("#taxRate").val()-0;//税率
		//数量*单价
		$("input[id^='txtApplyCount']").each(function(){
			var count = $(this).val();//本次申请数量
			var price = $(this).parent().parent().find("#purchaseUnitPrice").val();//采购单价
			var sum = $(this).parent().parent().find("#purchasePrice").val();//采购总价
			if(sum!=""){//总价不是""  用总价 除以 数量  获得 单价 
				if( count != "" ){
					$(this).parent().parent().find("#purchaseUnitPrice").val((sum/count-0).toFixed(2));//单价
					$(this).parent().parent().find("#purchasePriceLocal").val((sum*exchangeRate-0).toFixed(2));//总价(本币)
					$(this).parent().parent().find("#netPrice").val(toDecimal2(sum/rate));  //净价(本币)
				}
			}
			else{//如果总价不是数字(输入单价时会清空总价)  用数量*单价 获得 总价
				if( count != "" && price != "" ){
					$(this).parent().parent().find("#purchasePrice").val((count*price-0).toFixed(2));//总价
					$(this).parent().parent().find("#purchasePriceLocal").val(((count*price-0)*exchangeRate-0).toFixed(2));//总价(本币)
					$(this).parent().parent().find("#netPrice").val(toDecimal2((count*price-0)/rate));//净价(本币)
				}
			}
		});
		//合计采购数量
		var sumApplyCount=0;
		$("input[id^='txtApplyCount']").each(function(){
			sumApplyCount += $(this).val()-0;
		});
		$("#sumApplyCount").val(sumApplyCount);
		
		//合计采购总价
		var sumPurchasePrice=0;
		$("input[id='purchasePrice']").each(function(){
			sumPurchasePrice += $(this).val()-0;
		});
        sumPurchasePrice = sumPurchasePrice.toFixed(2);
		$("#sumPurchasePrice").val(sumPurchasePrice);
		//合计采购总价(本币)
		var sumPurchasePriceLocal=0;
		$("input[id^='purchasePriceLocal']").each(function(){
			sumPurchasePriceLocal += $(this).val()-0;
		});
        sumPurchasePriceLocal = sumPurchasePriceLocal.toFixed(2);
		$("#sumPurchasePriceLocal").val(sumPurchasePriceLocal);
		
	}
	//采购单价
	$("#table_detail").delegate("input[id^='purchaseUnitPrice']","keyup",function(){
		//清空总价
		$(this).parent().parent().find("input[id^='purchasePrice']").val("");
		sumAll();
	});
	//采购总价
	$("#table_detail").delegate("input[id^='purchasePrice']","keyup",function(){
		sumAll();
	});
	//验证归口/预算部门
	function checkDepVal(){
		if(!$("#txtYsssbm").val()){
			alert("请选择归口/预算部门");
		}
	}
	$("#butSupplierShow").click(function(){
		if(!$("#txtYsssbm").val()) {
    		alert("请先选择预算所属部门!");
    		return false;
    	}
		searchSupplier();
	});
	//选择供应商
	function searchSupplier(){
		var top = "90px";//$(this).offset().top - 350;
		var left = "50px";//$(this).offset().left-180;
		$('#supplierWindow').window('open').window('resize',{width:'650px',height:'330px',top: top,left:left});
	}
	function searchSupplierLists(){
		if(!$("#txtCompanyCode").val()) {
			alert("请先选择公司");
			return;
		}
		/* if(!$("#searchSupplierCode").val()&&!$("#searchSupplierName").val()){
			alert("请输入供应商名称或编码");
			return;
		} */
		var companyCode = $("#txtCompanyCode").val();
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
		setDisable();
	});
	//供应商编码输入1
	$("#supplierCode").blur(function(){
		if($("#supplierCode").val()=="1" || $("#supplierCode").val()=="2") {
			$("#supplierName").val("采购中心一次性供应商");
			$("#country").val("CN");
			setEnable();
		}
	});

	var strNper = ${paymentTerms.size()};
	$("#addPayRows").click(function(){
		strNper++;
		var html="<tr class='bodyTr' style='white-space:nowrap;'>"
			+"<td>"
			+"<input id='gvDataList_chkBolDel_0' type='checkbox' name='gvDataList_chkBolDel_0'>"
			+"</td>"
			+"<td class='tdpadding'><input size='5' type='text' name='nper' id='nper' class='rightalign' value='"+strNper+"' readonly></td>"
			+"<td class='tdpadding'>"
			+"<select style='width: 150px;' name='paymentType'>"
			+"<option value='预付款' selected='selected'>预付款</option>"
			+"<option value='验收款'>验收款</option>"
			+"<option value='尾款'>尾款</option>"
			+"</select>"
			+"</td>"
			+"<td class='tdpadding'><input type='text' name='paymentRatio' id='paymentRatio' value='30.00' size='10' class='rightalign'></td>"
			+"<td class='tdpadding'><input type='text' name='paymentRemarks' id='paymentRemarks' value='' size='30'></td>"
			+"</tr>";
		$("#payment_tbody").append(html);
		sumPay();
	});
	//合计付款比例
	function sumPay(){
		var sumPaymentRatio=0;
		$("input[id='paymentRatio']").each(function(){
			sumPaymentRatio += $(this).val()-0;
		});
		$("#sumPaymentRatio").val(toDecimal2(sumPaymentRatio));
	}
	$("#delPayRows").click(function(){
		$("#payment_tbody").find("input:checkbox[name=gvDataList_chkBolDel_0]:checked").each(function(){
	        $(this).parent().parent().remove();
	    });
		$("#gvDataList_CheckAll_Pay").attr("checked", false);
		resetPay();
		sumPay();
	});
	function resetPay(){
		strNper=0;
		$("input[name='nper']").each(function(){
			strNper++;
			$(this).val(strNper);
		});
	}
	$("#paymentRatio").live("keyup",function(){
		sumPay();
	});
	function  cleanSupp() {
		$("#supplierCode").val("");
		$("input[suplc='Y']").each(function(){
			 $(this).val("");
		 });
	}
	function setEnable(){
		 $("input[suplc='Y']").each(function(){
			 $(this).removeAttr("readonly");  
		 });
		 $("select[suplc='Y']").each(function(){
			 $(this).removeAttr("disabled");  
		 });
	}
	function setDisable(){
		 $("input[suplc='Y']").each(function(){
			 $(this).attr("readonly", "readonly");
		 });
		 $("select[suplc='Y']").each(function(){
			 $(this).attr("disabled", "disabled");
		 });
	}
    $('#btnUpload').click(function () {
        var fileElementId =$(this).prev().attr('id');
        var file = $('#fileToUpload').val();
        if (file == "") {
            alert("请选择需要上传的文件！");
            return false;
        }

        var documentNo = $('#txtPurchaseOrderId').val();
        if ("" == documentNo) {
            alert('单据号不可为空');
            return false;
        }

        uploadFile('${dynamicURL}', '${sessionScope._user_nick_name}', '${sessionScope._user_name}', documentNo, $('#uploadTable > tbody'),fileElementId);

    });

    $('#btnDelFile').click(function () {
        deleteFiles('${dynamicURL}', "uploadTable");
    });
	$(function(){
		//联动获得基础下拉列表信息
		linkage();
		//平台赋值
		var platform = "${purchaseOrder.platform}";
		$("#txtPlatform").val(platform);
		//资产类型赋值
		var assetType = "${purchaseOrder.assetType}";
		$("#txtAssetType1").val(assetType);
		//税码赋值
		var taxRate = "${purchaseOrder.taxRate}";
		$("#taxRate").val(taxRate);
		//订单类别赋值
		var orderType = "${purchaseOrder.orderType}";
		$("#orderType").val(orderType);
		//币种赋值
		var currencyCode = "${purchaseOrder.currencyCode}";
		$("#currencyCode").find("option[value='"+currencyCode+"']").attr("selected",true);
		//查询汇率
		selectCurrency();
		setDisable();
		sumAll();
		sumPay();
		
		
	  	///获得资产明细信息 封装Json对象
		oldPurchaseApplyMappingModels = constructObjectFromTable(objdetail, $("#tbody_detail_detail .bodyTr"));
	  	///获得订单明细信息 封装Json对象
		oldPurchaseOrderDetailModels = constructObjectFromTable(obj, $("#tbody_detail .bodyTr"));
	  	
	  	//获取ids
		$("input[id='applyDetailId']").each(function(){
    		strids += "'"+$(this).val()+"',"
    	});
    	if(strids.length>0){
	    	strids = strids.substr(0,strids.length-1);
	  	}
    	$.ajax({
            url:'${dynamicURL}/file/getFilesByDocument',
            data:{'document':$("#txtPurchaseOrderId").val()},
            dataType:'json',
            type:'POST',
            success:function(data){
                if(data.length > 0 ){
                    $('#uploadTable .noItems').remove();
                    $.each(data,function(i,item){
                        $('#uploadTable > tbody').append('<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
                            + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '" name=documentId />'
                            + '</td><td scope="col">' + item.fileName
                            + '</td><td scope="col">' + item.createUserName
                            + '</td><td style="width:9%">' + item.createDate
                            + '</td><td align="center" style="width:10%"><a href="'+'${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                            + '</td></tr>');
                    });
                }

            }
        });
	});
	
	//保留两位小数     
    //功能：将浮点数四舍五入，取小数点后2位    
    function toDecimal(x) {    
        var f = parseFloat(x);    
        if (isNaN(f)) {    
            return;    
        }    
        f = Math.round(x*100)/100;    
        return f;    
    }    
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
  	//清空行明细
  	function clearRows(){
  		$("#tbody_detail").html("");
  		$("#tbody_add_detail").html("");
  		$("#tbody_detail_detail").html("");
  	}
  	
  	$("#btnRefClear").click(function(){
  		clearRows();
  	});
  	$("#txtCompanyCode").change(function(){
  		clearRows();
  	});
  	$("#orderType").change(function(){
  		clearRows();
  	});
    $(function(){
        if($("#supplierCode").val()=="1" || $("#supplierCode").val()=="2") {

            setEnable();
        }
    });
    $(document).ready(function(){
        $(window).resize(function(){
            $('#purchaseTab').tabs('resize');
        });
    });
</script>
</body>


</html>