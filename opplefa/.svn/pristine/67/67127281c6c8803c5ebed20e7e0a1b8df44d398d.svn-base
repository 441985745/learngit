<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <input type="button" name="btnSave" value="提交" onclick="" id="btnSave" class="btn">
        <c:if test="${notPoPaymentOrder.commitType=='Y'}">
        <input type="button" name="btnSaveDraft" value="保存草稿" onclick="" id="btnSaveDraft" class="btn">
        </c:if>
        <input type="button" name="btnCancel" value="返回" id="btnCancel" class="btn" onclick="dg.cancel()">
    </div>
    <div style="margin-top: 10px;">
    
    <div id="purchaseTab" class="easyui-tabs" >
       <div title="订单信息" id="tabsa" style="overflow:auto;padding:20px;min-width: 800px;">
       <div>
       <form id="paymentOrderFormHeader">
        <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
		<tbody>
			<tr>
				<td class="titlefontsize" align="left">
					<div class="imgtitle"></div>
					基本信息
				</td>
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
							</td>
							<td style="text-align: right; width: 90px;">供应商编码:</td>
							<td>
								<input style="width: 100px;" readonly='readonly' class='searchinput' name='supplierCode' type='text' value='${notPoPaymentOrder.supplierCode}' id='supplierCode'></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanSupp();"><input id="butSupplierShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
							</td>
							<td style="text-align: right"><font color="red" >*</font>币种:</td>
							<td>
								<select class="searchselect" name="currencyCode" id="currencyCode" onchange="selectCurrency()" >
								<c:forEach items="${currencylist}" var="o" varStatus="status">
									<c:choose>
									  <c:when test="${o.ccurrencycode==notPoPaymentOrder.currencyCode}">
									  <option value="${o.ccurrencycode}" selected="selected" >${o.ccurrencycode} ${o.ccurrencyname }</option>
									  </c:when>
									  <c:otherwise>
									  <option value="${o.ccurrencycode}">${o.ccurrencycode} ${o.ccurrencyname }</option>
									  </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;">申请人:</td>
							<td>
								<input class="searchinput" readonly name="applyUser" type="text" value="${notPoPaymentOrder.applyUser}" id="applyUser" />
							</td>
							<td style="text-align: right; width: 90px;">供应商名称:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='supplierName' type='text' value='${notPoPaymentOrder.supplierName}' id='supplierName'>
							</td>
							<td style="text-align: right" >汇率:</td>
							<td class="tdtext">
								<input class='searchinput' name='exchangeRate' readonly='readonly' type='text' value='${notPoPaymentOrder.exchangeRate}' id='exchangeRate'></td>
						</tr>
						<tr>
							<td style="text-align: right"><font color="red" >*</font>申请部门:</td>
							<td>
								<span id ="spanBudgetDepartment"><input style="width: 100px;" readonly="readonly" name="departmentName" value="${notPoPaymentOrder.departmentName}" type="text" id="txtBudgetDepartment" class="inputbtnRefViewGray" ></span><input id='btnRefClear1' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanDep();"><input id="btntxtYsssbmShow" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
								<input name="departmentCode" value="${notPoPaymentOrder.departmentCode}" type="hidden" id="txtYsssbm" class="inputbtnRefViewGray" >
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
							<td class="tdtext">
								<select id="company" class="searchselect" name='company'></select>
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
								<select class="searchselect" name="isExpenseAssets" id="isExpenseAssets" onchange="clearDetail()">
										<option value="">--请选择--</option>
										<option value="Y" ${notPoPaymentOrder.isExpenseAssets=="Y" ? "selected" :"" }>是</option>
										<option value="N" ${notPoPaymentOrder.isExpenseAssets=="N" ? "selected" :"" }>否</option>
	                            </select>
							</td>
							<td style="text-align: right; width: 90px;">城市:</td>
							<td>
								<input  class='searchinput' name='city' type='text' value='${notPoPaymentOrder.city}' id='city'>
							</td>
							<td style="text-align: right; width: 90px;">联行号:</td>
							<td>
								<input readonly='readonly' class='searchinput' name='contactNumber' type='text' value='${notPoPaymentOrder.contactNumber}' id='contactNumber'>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;"><span style="color: red">*</span>扫描地:</td>
							<td>
								<select class="searchselect" name="scanLocation" id="scanLocation">
										<option value="">--请选择--</option>
									<c:forEach items="${scanLocations}" var="scanLocation">
										<c:choose>
										  <c:when test="${scanLocation.clocationname==notPoPaymentOrder.scanLocation}">
										  <option value="${scanLocation.clocationname}" selected="selected" >${scanLocation.clocationname}</option>
										  </c:when>
										  <c:otherwise>
										  <option value="${scanLocation.clocationname}">${scanLocation.clocationname}</option>
										  </c:otherwise>
										</c:choose>
									</c:forEach>
	                            </select>
							</td>
							<td style="text-align: right" ><font color="red" >*</font>税码/税率:</td>
							<td class="tdtext">
								<select class="searchselect" name="taxRate" id="taxRate" onchange="sumAll()">
									<option value="">请选择</option>
									<option value="0.0" ${notPoPaymentOrder.taxRate=="0.0" ? "selected" :"" }>J0 (0%进项税，中国)</option>
									<option value="0.17" ${notPoPaymentOrder.taxRate=="0.17" ? "selected" :"" }>J1 (17%进项税，中国)</option>
									<option value="0.13" ${notPoPaymentOrder.taxRate=="0.13" ? "selected" :"" }>J2 (13%进项税，中国)</option>
									<option value="0.07" ${notPoPaymentOrder.taxRate=="0.07" ? "selected" :"" }>J3 (7%进项税，中国)</option>
									<option value="0.06" ${notPoPaymentOrder.taxRate=="0.06" ? "selected" :"" }>J4 (6%进项税，中国)</option>
									<option value="0.04" ${notPoPaymentOrder.taxRate=="0.04" ? "selected" :"" }>J5 (4%进项税，中国)</option>
									<option value="0.03" ${notPoPaymentOrder.taxRate=="0.03" ? "selected" :"" }>J6 (3%进项税，中国)</option>
									<option value="0.11" ${notPoPaymentOrder.taxRate=="0.11" ? "selected" :"" }>J7 (11%进项税，中国)</option>
									<option value="1" ${notPoPaymentOrder.taxRate=="1" ? "selected" :"" }>进口关税</option>
	                            </select>
							</td>
							<td style="text-align: right; width: 90px;"><span style="color: red">*</span>申请事由:</td>
							<td>
								<input  class='searchinput' name='applyFor' type='text' value='${notPoPaymentOrder.applyFor}' id='applyFor'>
							</td>
						</tr>
						<tr>
							<td style="text-align: right; width: 90px;"><span style="color: red">*</span>是否有增值税专用发票:</td>
							<td>
								<select class="searchselect" name="isVat" id="isVat">
										<option value="">--请选择--</option>
										<option value="Y" ${notPoPaymentOrder.isVat=="Y" ? "selected" :"" }>是</option>
										<option value="N" ${notPoPaymentOrder.isVat=="N" ? "selected" :"" }>否</option>
	                            </select>
							</td>
							<td style="text-align: right; width: 90px;">发票号:</td>
							<td>
								<input  class='searchinput' name='invoiceNumber' type='text' value='${notPoPaymentOrder.invoiceNumber}' id='invoiceNumber'>
							</td>
							<td style="text-align: right; width: 90px;"><span style="color: red">*</span>付款类型:</td>
							<td>
								<select class="searchselect" name="insertType" id="insertType">
										<option value="">--请选择--</option>
										<option value="资产新增" ${notPoPaymentOrder.insertType=="资产新增" ? "selected" :"" }>资产新增</option>
										<option value="价值新增" ${notPoPaymentOrder.insertType=="价值新增" ? "selected" :"" }>价值新增</option>
	                            </select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right" >备注:</td>
							<td class="tdtext"><input  class='searchinput' name='remark' type='text' value='${notPoPaymentOrder.remark}' id='remark'></td>
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
				<td class="titlefontsize" align="left">
					<input id="addRows" type="button" value="增行">
					<input id="delRows" type="button" value="删行">
				</td>
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
												<th scope="col">
				                                    <input id="gvDataList_CheckAll" type="checkbox" name="gvDataList$ctl01$CheckAll"
				                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_detail');"/>
				                                </th>
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
												<td>
			                                        <input id="jvDataList_chkBolDel_0" type="checkbox" name="jvDataList_chkBolDel_0">
			                                    </td>
												<td class="tdpadding" id='statusIndex'>${i.index+1}<input value='${notPoPaymentOrder.cdocument}' type="hidden" name="notPoPaymentOrderId" id="notPoPaymentOrderId"></td>
												<td class='tdpadding'>
                                                    <input style="width: 100px;" readonly="readonly" name="costCenter" value="${pd.costCenter}" type="text" id="costCenterName${i.index+1}" class="inputbtnRefViewGray name" ></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="ClearObj(this);"><input id="showCostCenterBtn${i.index+1}" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
                                                    <input type="hidden" id="costCenterCode${i.index+1}" name="costCenterCode" value="${pd.costCenterCode}" class="code">
                                               	</td>
												<td class="tdpadding">
													<input name="projectCode" type="text" id="projectCode${i.index+1}" style='width:100px;' value="${pd.projectCode}" class="inputbtnRefViewGray" readonly="readonly"><input id="projectCodeClear${i.index+1}" class="btnRefView" onclick="" title="点击清空参照文本框内容" type="button" value="X"><input id="btnRefShow${i.index+1}" class="btnRefView"  title="点击打开参照文本框内容" type="button" value="…">
		                                    		<input name="hdapplydepcode" type="hidden" id="hdapplydepcode">
		                                    	</td>
												<td class='budgetAssetsNameTd' style="padding-left: 10px; padding-right: 10px;"><input size='10' value="${pd.budgetAssetsName}" type="text" readonly name="budgetAssetsName" id="txtBudgetAssetsName${i.index+1}"></td>
												<td class='tdpadding' ><input style="width: 100px;" class="td_input" name="subordinateDepartment" type="text"
													readonly="readonly"  id="subordinateDepartment${i.index+1}" value="${pd.subordinateDepartment}"><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanDep1(this);"><input id="btntxtZcssbmShow${i.index+1}" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
													<input id="subordinateDepartmentCode${i.index+1}" type="hidden" name="subordinateDepartmentCode">
												</td>
												<td class='tdpadding' id='tdOfficeLocation'>
													<select id='officeLocation' name='officeLocation'>
															<option value=''>请选择</option>
														<c:forEach items="${addresslist}" var="userInfo" varStatus="status">
														<c:choose>
														  <c:when test="${userInfo.caddressCode==pd.officeLocation}">
														  <option value="${userInfo.caddressCode}" selected="selected" >${userInfo.caddressName}</option>
														  </c:when>
														  <c:otherwise>
														  <option value="${userInfo.caddressCode}">${userInfo.caddressName}</option>
														  </c:otherwise>
														</c:choose>
														</c:forEach>
													</select>
												</td>
												<td class='assetsNameTd' style="padding-left: 10px; padding-right: 10px;"><input type="text" value="${pd.assetsName}"  size='10' name="assetsName" id="txtAssetsName${i.index+1}"></td>
												<td class='tdpadding'>
													<select id='assetsType' name='assetsType'>
															<option value=''>请选择</option>
															<option value='0' ${pd.assetsType=="0" ? "selected" :"" }>IT资产</option>
															<option value='1' ${pd.assetsType=="1" ? "selected" :"" }>行政资产</option>
															<option value='2' ${pd.assetsType=="2" ? "selected" :"" }>计量器具</option>
															<option value='3' ${pd.assetsType=="3" ? "selected" :"" }>机器设备</option>
													</select>
												</td>
												<td class='specificationParameterTd' style="padding-left: 10px; padding-right: 10px;"><input size='10' type="text" value="${pd.specificationParameter}" name="specificationParameter" id="txtSpecificationParameter"></td>
												<td class='tdpadding'>
                                                    <input style="width: 100px;" readonly="readonly" name="assetClassification" value="${pd.assetClassification}" type="text" id="assetClassification${i.index+1}" class="inputbtnRefViewGray" ></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="cleanAssets(this);"><input id="btnAssetsShow${i.index+1}" class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
                                                    <input type="hidden" id="netSalvage" name="netSalvage" value="">
                                               	</td>
                                               	<td class='tdpadding'  >
                                                        <input disabled="true" id="depreciation${i.index+1}" name="depreciation" value="${pd.depreciation}" size='5'>
                                                </td>
                                                <td class='tdpadding'>
                                                 	<input value="${pd.ledgerAccount}"  readonly="readonly" name="ledgerAccount" id="ledgerAccount${i.index+1}" size='10'>
                                                </td>
                                                <td class='tdpadding'>
                                                 	<input style="width: 100px;" class="inputbtnRefViewGray name" name="assetsUserName" type="text"
													readonly="readonly" value="${pd.assetsUserName}" id="assetsUserName${i.index+1}">
													</span><input id='gvdMeeting_btnClearsk_0'  class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick="ClearObj(this);">
													<input id="showUserListBtn${i.index+1}"  class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'>
													<input type="hidden" class="inputbtnRefViewGray code" id="assetsUserCode" name="assetsUserCode${i.index+1}" >
                                                </td>
												<!-- 5 -->
												<td class="tdpadding"><input type="text" name="internalOrderNo" size='10' id="internalOrderNo${i.index+1}" value="${pd.internalOrderNo}"></td>
												<td class="tdpadding" id="tdUnits">
													<select id='units' name='units'>
															<option value=''>请选择</option>
														<c:forEach items="${assetUnitList}" var="assetUnit" varStatus="status">
														<c:choose>
														  <c:when test="${assetUnit.unitName==pd.units}">
														  <option value="${assetUnit.unitName}" selected="selected" >${assetUnit.unitName}</option>
														  </c:when>
														  <c:otherwise>
														  <option value="${assetUnit.unitName}">${assetUnit.unitName}</option>
														  </c:otherwise>
														</c:choose>
														</c:forEach>
													</select>
												</td>
												<td class="tdpadding"><input type="text" name="payCount" size='10' id="payCount" value="${pd.payCount}"></td>
												<!-- <td class="tdpadding" >编吗方式
													<select id='codingMode' name='codingMode'>
															<option value=''>请选择</option>
															<option value='forAdd'>新增</option>
															<option value='forAppend'>追加</option>
															<option value='forAppoint'>指定</option>
													</select>
												</td>
												<td class="tdpadding"><input type="text" name="mainAssetCode" size='10' id="mainAssetCode"></td>主编吗
												<td class="tdpadding"><input type="text" name="secondary" class='rightalign' id="secondary" size='7' readonly="readonly"></td>次级 -->
												
												<td class="tdpadding"><input type="text" name="taxUnit" id="taxUnit" size="10" value="${pd.taxUnit}"></td><!-- 含税单价 -->
												<!-- 10 -->
												<td class="tdpadding"><input type="text" name="netPrice" class='rightalign' id="netPrice" size="10" value="${pd.netPrice}" value="0.00"></td><!-- 净价 -->
												<!-- 15 -->
												<td class="tdpadding"><input type="text" name="avaliableSumMoney" class='rightalign' id="avaliableSumMoney${i.index+1}" size="10" readonly="readonly" value="${pd.avaliableSumMoney}"></td><!-- 可用预算金额 -->
												<td class="tdpadding"><input type="text" name="taxTotalPrice" class='rightalign' id="taxTotalPrice" size="10" value="${pd.taxTotalPrice}"></td><!-- 含税总价-->
												<td class="tdpadding"><input type="text" name="tax" class='rightalign' id="tax" size="10" value="${pd.tax}" readonly="readonly"></td><!-- 税额-->
												<td class="tdpadding"><input type="text" name="assetsIncreaseValue" class='rightalign' id="assetsIncreaseValue" size="10" value="${pd.assetsIncreaseValue}" value="0.00"></td><!-- 资产增加价值-->
												<td class="tdpadding"><input type="text" name="text" class='rightalign' id="text" size="10" value="${pd.text}"></td><!-- 资产增加价值文本-->
												<td class="tdpadding"><input type="text" name="budgetYear" class='rightalign' id="budgetYear${i.index+1}" size="5" value="${pd.budgetYear}" readonly="readonly"></td><!-- 预算年度-->
												<td class="tdpadding"><input type="text" name="payPrice" class='rightalign' id="payPrice" size="10" value="${pd.payPrice}"></td><!-- 本次申请金额(原币)-->
												<td class="tdpadding"><input type="text" name="advanceOffsetPrice" class='rightalign' id="advanceOffsetPrice" size="10" value="${pd.advanceOffsetPrice}" value="0.00"></td><!-- 本次预付冲减金额-->
												<td class="tdpadding"><input type="text" name="finalPrice" class='rightalign' id="finalPrice" size="10" value="${pd.finalPrice}" value="0.00"></td><!-- 尾款金额-->
												<td class="tdpadding"><input readonly='readonly' name="finalPaymentDate" value="<fmt:formatDate value='${pd.finalPaymentDate}'  pattern='yyyy-MM-dd'/>" type="text" id="finalPaymentDate" class="Wdate" onclick="WdatePicker({skin:'ext'});"></td><!-- 尾款付款时间-->
												<!-- 20 -->
												<td class="tdpadding"><input type="text" class='rightalign' name="payPriceLocal" id="payPriceLocal" value="${pd.payPriceLocal}" size="10"></td>
											</tr>
											</c:forEach>
										</tbody>

											<tr style="white-space:nowrap;">
												<td>

			                                    </td>
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
								<div id="addAssetsWindow" name="" class="easyui-window" title="资产分类" data-options="iconCls:'icon-save'"
									 style="width:800px;height:440px;padding:5px;" closed="true">
									<div class="easyui-layout" data-options="fit:true">
										<div data-options="region:'center'" style="padding:10px;">

											<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
												<input type="button" name="btnDetailSave" value="提交" id="btnDetailSave" class="btn">
											</div>
											<div style="margin-top: 10px">


												<table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder">
													<tbody>

													<tr>
														<td colspan="4">
															<table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
																   style="table-layout: fixed;">
																<tbody>
																<tr>
																	<td class="tdtitle" style="text-align: right" colspan="2">
																		<div id="div1" class="divGrid"
																			 style="overflow-x: scroll; overflow-y: visible; height: 310px;">
																			<div>

																				<table id="table_add_detail" class="adminlist" cellspacing="0"
																					   rules="all" border="1" id="gridDataList"
																					   style="border-collapse:collapse;">
																					<tr style="white-space:nowrap;">
																						<th class='tdpadding' scope="col"></th>
																						<th class='tdpadding' scope="col"><font color="red">*</font>资产分类
																						</th>
																						<th class='tdpadding' scope="col"><font color="red">*</font>资产编码
																						</th>
																						<th class='tdpadding' scope="col"><font color="red">*</font>资产分类描述
																						</th>
																						<th class='tdpadding' scope="col"><font color="red">*</font>折旧年限(年)
																						</th>
																						<th class='tdpadding' scope="col"><font color="red">*</font>净残值率
																						</th>
																						<th class='tdpadding' scope="col"><font color="red">*</font>归口管理部门
																						</th>
																						<th class='tdpadding' scope="col"><font color="red">*</font>总账科目
																						</th>
																						<th class='tdpadding' scope="col"><font color="red">*</font>科目描述
																						</th>
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
								<div id="budgetWindow" name="" class="easyui-window" title="选择预算" data-options="iconCls:'icon-save'" style="width:750px;height:420px;padding:5px;" closed="true">
									<div class="easyui-layout" data-options="fit:true">
										<div data-options="region:'center'" style="padding:10px; width: 700px;">
											<div id="div1" class="divGrid" >
												<form id="budgetForm">
													<table class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="0">
														<tr style="text-align: left">
															<td >公司</td>
															<td name=""><input type="text" id="companyName1" readonly="readonly" name="companyName"><input type="hidden" id="company" readonly="readonly" name="company"></td>
															<td>成本中心</td>
															<td><input name="costCenterName" id="costCenterName" type="text" readonly="readonly"><input name="costCenter" id="costCenter" type="hidden" readonly="readonly"></td>
														</tr>
														<tr style="text-align: left">
															<td>项目</td><td><input type="text" name="projectName" id="projectName"></td>
															<td>年度</td>
															<td>
															<input type="text" name="budgetYear" id="budgetYear"></td>
																<%-- <fmt:formatDate value='${demandOrder.applyDate}'  pattern='yyyy'/><input name="assetsType" id="assetsType" type='hidden' readonly="readonly"/>
																<input class="searchinput" readonly name="demandOrderId" type="hidden" value="${demandOrder.cdocument}">
																<input class="searchinput" readonly name="budgetYear" type="hidden" value="<fmt:formatDate value='${demandOrder.applyDate}'  pattern='yyyy'/>"> --%>
														</tr>
													</table>
												</form>
												<input type="button" value="查询" id="searchBudger"><input type="button" id="budgerSure" value="确定">
											</div>
											<div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;height: 240px;">
												<table id="assetsBudget" class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="1" cellpadding="6px">
													<thead><tr><th></th><th>序号</th><th>公司</th><th>成本中心
													</th><th>项目编码</th><th>项目名称</th><th>资产名称</th><th>资产类型</th>
														<th>单位</th><th>预算年度</th><th>预算数量</th><th>预算单价</th>
														<th>预算金额</th><th>已使用数量</th><th>已使用金额</th>
														<!-- <th>可用数量</th> --><th>可用金额</th></tr></thead>
													<tbody id="budgetBody"></tbody>
												</table>

											</div>
										</div>
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
										   <th scope="col">文件</th>
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
		<div>
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
        
       <c:if test="${notPoPaymentOrder.paymentType=='验收款'}">
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
                                                <th scope="col">
                                                    <input id="gvDataList_CheckAll_Add" type="checkbox" name="gvDataList$ctl01$CheckAll"
                                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_add_detail');"/>
                                                </th>
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
                                            <c:choose>
                                                <c:when test="${pd.isChecked=='Y'}">
                                                <td class='tdpadding'><input id='chkBolDel' type='checkbox' name='gvdata' checked="checked"></td>
                                                </c:when>
                                                <c:otherwise>
                                                <td class='tdpadding'><input id='chkBolDel' type='checkbox' name='gvdata' ></td>
                                                </c:otherwise>
                                            </c:choose>
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
												<td class="tdpadding" name="price"><input class="rightalign" value="${pd.payPrice}" type="text" name="payPrice" id="payAmount" size="10" onkeyup="value=value.replace(/[^\d.]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" ></td>
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


<script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;//定义dg 关闭需要
	var unitsHtml = "";
	var tdOfficeLocation = "";
	$(function(){ 
		selectCurrency();
		unitsHtml = $("#tdUnits").html();
		tdOfficeLocation = $("#tdOfficeLocation").html();
		var htmlSelect = "<option value='"+'${notPoPaymentOrder.companyCode}'+"-"+'${notPoPaymentOrder.companyName}'+"' selected='selected'>("+'${notPoPaymentOrder.companyCode}'+")"+'${notPoPaymentOrder.companyName}'+"</option>";
        $("#company").html(htmlSelect);
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
		limit(this,11);//11字节内
		sumAll();
	});
	//资产增加价值
	$("#table_detail").delegate("input[id='assetsIncreaseValue']","keyup",function(){
		limit(this,11);//11字节内
		sumAll();
	});
	//本次申请金额(原币)
	$("#table_detail").delegate("input[id='payPrice']","keyup",function(){
		limit(this,11);//11字节内
		sumAll();
	});
	//本次冲减金额
	$("#table_detail").delegate("input[id='advanceOffsetPrice']","keyup",function(){
		limit(this,11);//11字节内
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
				$(this).parent().parent().find("#finalPrice").val((parseFloat(taxTotalPrice)+parseFloat(assetsIncreaseValue)-parseFloat(payPrice)-parseFloat(advanceOffsetPrice)).toFixed(2));//尾款金额
			}
			$(this).parent().parent().find("#taxTotalPrice").val((count*price-0).toFixed(2));//含税总价
			if(count!=''){
				$(this).parent().parent().find("#netPrice").val((((price-0)/(1+parseFloat(taxRate))).toFixed(2)*count).toFixed(2));//净价
				$(this).parent().parent().find("#tax").val((((price-0)/(1+parseFloat(taxRate))*taxRate).toFixed(2)*count).toFixed(2));//税额
			}
			if(payPrice!=''){
				$(this).parent().parent().find("#payPriceLocal").val((parseFloat(payPrice)*exchangeRate).toFixed(2));//本次申请金额(本币)
			}
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
		ratioMoney = sumpayPrice;
		realMoney = sumpayPrice;
	}
	function checkAll(){
		var mess="";
		var flag = true;
		var sumadvanceOffsetPrice = $("#sumadvanceOffsetPrice").val();
		var payPriceDetail = 0;
		var flag1=false;
		if(parseFloat($("#taxRate").val())!=0&&$("#invoiceNumber").val()==''){
			alert("请填写发票号！");
			return false;
		}
		if($("#isExpenseAssets").val()==''){
			alert("请选择是否一次性费用化！");
			return false;
		}
		if($("#applyFor").val()==''){
			alert("请填写申请事由！");
			return false;
		}
		if(parseFloat($("#taxRate").val())!=0&&$("#invoiceNumber").val()==""){
			alert("请填写发票号！");
			return false;
		}
		if(parseFloat($(this).val())<0){
			alert("行项目中尾款金额不得小于0！");
			return false;
		}
		$("input[id^='finalPrice']").each(function(){
			if(parseFloat($(this).val())<0){
				alert("行项目中尾款金额不得小于0！");
				flag = false;
				return false;
			}
		});
		$("input[id^='subordinateDepartment']").each(function(){
			if($(this).val()==""){
				alert("资产所属部门不得为空！");
				flag = false;
				return false;
			}
		});
		$("input[id^='officeLocation']").each(function(){
			if($(this).val()==""){
				alert("办公地点不得为空！");
				flag = false;
				return false;
			}
		});
		$("input[id^='projectCode']").each(function(){
			if($(this).val()==""){
				alert("行项目中项目编码不得为空！");
				flag = false;
				return false;
			}
		});
		$("input[id^='assetsType']").each(function(){
			if($(this).val()==""){
				alert("行项目中资产类型不得为空！");
				flag = false;
				return false;
			}
		});
		$("input[id^='assetClassification']").each(function(){
			if($(this).val()==""){
				alert("行项目中资产分类不得为空！");
				flag = false;
				return false;
			}
		});
		$("input[id^='assetsUserName']").each(function(){
			if($(this).val()==""){
				alert("行项目中资产责任人不得为空！");
				flag = false;
				return false;
			}
		});
		$("input[id^='units']").each(function(){
			if($(this).val()==""){
				alert("行项目中单位不得为空！");
				flag = false;
				return false;
			}
		});
		$("input[id^='taxTotalPrice']").each(function(){
			if(parseFloat($(this).val())<0||parseFloat($(this).val())==0){
				alert("行项目中含税总价不得小于0或等于0！");
				flag = false;
				return false;
			}
		});
		$("input[id^='assetsIncreaseValue']").each(function(){
			if($(this).val()==""){
				alert("行项目中资产增加价值不得为空，若无请填写0！");
				flag = false;
				return false;
			}
		});
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
		var document = $("#cdocument").val();
		var changeAmount = "N";
		if(checkAll()){
			showAgreeDialog(document,"77079",changeAmount);
		};
	});
	function showAgreeDialog(document, typeId,changeAmount) {
        var url = _dynamicURL+"/workflow/showUpdateCommitDialog?parentWindowId=update&document=" + document + "&typeId=" + typeId+"&changeAmount="+changeAmount;

        var dlg = new dg.curWin.$.dialog({ id: 'submitDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();

        return dlg;
    }
	function commitAgree(isMail, isPhoneMessage, idea,activityId) {
		$("#btnSave").val("提交...");
		$("#btnSave").attr("disabled", "disabled");//添加disabled属性
		$("#btnSaveDraft").attr("disabled", "disabled");
		$("#btnCancel").attr("disabled", "disabled");
		saveOrder("N",isMail, isPhoneMessage, idea,activityId);
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
function saveOrder(strDraft,isMail, isPhoneMessage, idea,activityId){
	
	var objHeader = $("#paymentOrderFormHeader").serializeObject();
	var objDetail;
	var objDetailDetail;
	objHeader.applyMoney = $("#sumpayPrice").val();
    objHeader.draft = strDraft;
	objHeader.idea = idea;
	objHeader.isMail = isMail;
	objHeader.isPhoneMessage = isPhoneMessage;
	objHeader.activityId = activityId; 
	objDetail= {
			notPoPaymentOrderId:"",
			projectCode:"",
			subordinateDepartment:"",
			subordinateDepartmentCode:"",
			officeLocation:"",
			budgetAssetsName:"",
			assetsName:"",
			assetsType:"",
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
	if(paymentType=="验收款") {
		var paymentOrderDetail = constructObjectFromTable(objDetail, $("#table_detail .bodyTr"));
		PaymentOrderWrapper.notPoPaymentOrderDetails = paymentOrderDetail;
		var paymentPaymentMappingModels = constructObjectFromTable(objDetailDetail, $("#tbody_detail_detail .bodyTr"));
		PaymentOrderWrapper.paymentPaymentMappingModels = paymentPaymentMappingModels;
	}
	$.ajax({
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        url: _dynamicURL+"/notPoPayment/api/updatePrePayment",
        data: JSON.stringify(PaymentOrderWrapper), 
        success: function(data) {
        	if (data.errorMessages.length > 0) {
        		var str = data.errorMessages;
				alert(str);
				var reg = new RegExp("^.*修改成功.*$");
				if (reg.test(str)) {
					dg.curWin.location = dg.curWin.location;
				}
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
		$('#company').html('');
		$('#txtCostCenter').val('');
		$('#txtCostCenterCode').val('');
	}
	//查询供应商
	$("#searchSupplier").click(function(){
		searchSupplierLists();
	});
function searchSupplierLists(){
    if(!$("#company").val()) {
        alert("请先选择公司");
        return;
    }
	/* if(!$("#searchSupplierCode").val()&&!$("#searchSupplierName").val()){
	 alert("请输入供应商名称或编码");
	 return;
	 } */
    var companyCode = $("#company").val().split("-")[0];
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
		var currencyCode = $("#currencyCode").val();
		searchPaymentPaymentMappingByCode(supplierCode,currencyCode);
	});
	function searchPaymentPaymentMappingByCode(supplierCode,currencyCode){
		$.ajax({
			type : "POST",
			nsync : true,
			url : _dynamicURL+"/notPoPayment/api/searchPaymentPaymentMappingByCode",
			data: {
	            'supplierCode': supplierCode,
	            'currencyCode': currencyCode
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
		var supplierCode = $("#supplierCode").val();
		$.ajax({
			type : "POST",
			nsync : false,
			url : _dynamicURL+"/userInfo/api/currencyRate",
			data : "currency="+currency,
			success : function(userInfo){
				//console.log(userInfo);
				$("#exchangeRate").val(userInfo.iexchangerate);
				sumAll();
			}
		});
		searchPaymentPaymentMappingByCode(supplierCode,currency);
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

                html += "<option value='"+ccompanycode+"-"+ccompanyname+"'>("+ccompanycode+")"+ccompanyname+"</option>";
            }
            $("#company").html(html);

			/* 					//清空行明细
			 statusIndex = 0;
			 sumAll(); */

            $('#ysssbmWindow').window('close');
            if($("#company").val()!=""){
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
$("input[id^='projectCodeClear']").live("click",function(){
	var j = $(this).attr("id").substr(16);
	$("#txtAssetsName"+j).val('');
	$("#txtBudgetAssetsName"+j).val('');
	$("#projectCode"+j).val('');
	$("#avaliableSumMoney"+j).val('');
	$("#budgetYear"+j).val('');
    
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
            var com = $("#company").val().split("-")[0];
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
    var attc = $("#company").val();
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
        return false;
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
		var companyCode = $("#company").val();
		if(companyCode != null && companyCode != ""){
			showCostCenterDialog(companyCode.split("-")[0]);
		}else {
			alert("请先选择公司！");
		}
	});

	function showCostCenterDialog(companyCode){
		var url = "${dynamicURL}/costCenter/getCodeAndName?parentWindowId=update&&companyCode=" + companyCode;
		var parentWindow=dg.curWin;
		new parentWindow.$.dialog({ id: 'list', title: "成本中心", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();

	}
	//确定成本中心
	function fillCostCenter(costcenterCode, costcenterName) {
		var j = $("#costCenterIndex").val();
		$('#costCenterCode'+j).val(costcenterCode);
		$('#costCenterName'+j).val(costcenterName);
		$("#txtAssetsName"+j).val('');
		$("#txtBudgetAssetsName"+j).val('');
		$("#projectCode"+j).val('');
		$("#avaliableSumMoney"+j).val('');
		$("#budgetYear"+j).val('');
		getInternalOrderCode(j,costcenterCode);
	}
	function getInternalOrderCode(n,costcenterCode){
		var attc = $("#company").val().split("-")[0];
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
		$(btn).parent().find("input[id^='assetClassification']").val("");
		$(btn).parent().parent().find("input[id^='depreciation']").val("");
		$(btn).parent().parent().find("input[id^='ledgerAccount']").val("");
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
		var url = "${dynamicURL}/user/getUserListInAttachConfig?parentWindowId=update";
		var parentWindow=dg.curWin;
		new parentWindow.$.dialog({ id: 'list', title: "所有人员", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();

	}
	function fillVehicleUser(userCode, userName) {
		var j = $("#userIndex").val();
		$('#assetsUserCode'+j).val(userCode);
		$('#assetsUserName'+j).val(userName);

	};
    //预算确定
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

	var statusIndex=1;//序号


	//序号重新排序
	function resetStatusIndex(){
		statusIndex = 0;
		$("td[id='statusIndex']").each(function(){
			statusIndex++;
			$(this).html(statusIndex);
		});
	}
	
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
	function addRow(){
		var document = $("#notPoPaymentOrderId").val();
		var isDisabled = "";
		if($("#txtAssetType").val() != "IT资产"){
			isDisabled = " disabled='disabled' ";
		}
		statusIndex++;




        var tableHtml = "<tr class='bodyTr' style='white-space:nowrap;'><td><input id='jvDataList_chkBolDel_0' type='checkbox' name='jvDataList_chkBolDel_0'></td>";
        tableHtml += "<td class='tdpadding' id='statusIndex'>"+statusIndex+"<input value='"+document+"' type='hidden' name='notPoPaymentOrderId' id='notPoPaymentOrderId'></td>";
        tableHtml += "<td class='tdpadding'><input style='width: 100px;' readonly='readonly' name='costCenter' value='' type='text' id='costCenterName"+statusIndex+"' class='inputbtnRefViewGray name' ></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='clearObj(this);'><input id='showCostCenterBtn"+statusIndex+"' class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'> <input type='hidden' id='costCenterCode"+statusIndex+"' name='costCenterCode' value='' class='code'></td>";
        tableHtml += "<td class='tdpadding'><input name='projectCode' type='text' id='projectCode"+statusIndex+"' style='width:130px;' class='inputbtnRefViewGray' readonly='readonly'><input id='projectCodeClear"+statusIndex+"' class='btnRefView' onclick='' title='点击清空参照文本框内容' type='button' value='X'><input id='btnRefShow"+statusIndex+"' class='btnRefView'  title='点击打开参照文本框内容' type='button' value='…'><input name='hdapplydepcode' type='hidden' id='hdapplydepcode'></td>";
        tableHtml += "<td class='budgetAssetsNameTd' style='padding-left: 10px; padding-right: 10px;'><input size='10' type='text' readonly name='budgetAssetsName' id='txtBudgetAssetsName"+statusIndex+"'></td>";
        tableHtml += "<td class='tdpadding' ><input style='width: 110px;' class='td_input' name='subordinateDepartment' type='text' readonly='readonly'  id='subordinateDepartment"+statusIndex+"'><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='cleanDep1(this);'><input id='btntxtZcssbmShow"+statusIndex+"' class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'><input id='subordinateDepartmentCode"+statusIndex+"' type='hidden' name='subordinateDepartmentCode'></td>";
        tableHtml += "<td class='tdpadding'>"+tdOfficeLocation+"</td>";
        tableHtml += "<td class='assetsNameTd' style='padding-left: 10px; padding-right: 10px;'><input type='text'  size='10' name='assetsName' id='txtAssetsName"+statusIndex+"'></td>";
        tableHtml += "<td class='tdpadding'><select id='assetsType' name='assetsType'><option value=''>请选择</option><option value='0'>IT资产</option><option value='1'>行政资产</option><option value='2'>计量器具</option><option value='3'>机器设备</option></select></td>";
        tableHtml += "<td class='specificationParameterTd' style='padding-left: 10px; padding-right: 10px;'><input size='10' type='text' name='specificationParameter' id='txtSpecificationParameter'></td>";
        tableHtml += "<td class='tdpadding'><input style='width: 100px;' readonly='readonly' name='assetClassification' value='' type='text' id='assetClassification"+statusIndex+"' class='inputbtnRefViewGray' ></span><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='cleanAssets(this);'><input id='btnAssetsShow"+statusIndex+"' class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'><input type='hidden' id='netSalvage' name='netSalvage' value='${applyOrderDetaile.netSalvage}'></td>";
        tableHtml += "<td class='tdpadding'><input disabled='true' id='depreciation"+statusIndex+"' name='depreciation' value=''></td>";
        tableHtml += "<td class='tdpadding'><input value=''  readonly='readonly' name='ledgerAccount' id='ledgerAccount"+statusIndex+"'></td>";
        tableHtml += "<td class='tdpadding'><input style='width: 100px;' class='inputbtnRefViewGray name' name='assetsUserName' type='text' readonly='readonly' value='' id='assetsUserName"+statusIndex+"'><input type='hidden' id='assetsUserCode"+statusIndex+"' name='postPeopleCode' class='code' ></span><input id='gvdMeeting_btnClearsk_0'  class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' onclick='ClearObj(this);'> <input id='showUserListBtn"+statusIndex+"'  class='btnRefView' title='点击打开参照文本框内容' type='button' value='…'></td>";
        
            <!-- 5 -->
        tableHtml += "<td class='tdpadding'><input type='text' name='internalOrderNo' size='10' id='internalOrderNo"+statusIndex+"'></td>";
        tableHtml += "<td class='tdpadding'>"+unitsHtml+"</td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='payCount' size='10' id='payCount'></td>";

        tableHtml += "<td class='tdpadding'><input type='text' name='taxUnit' id='taxUnit' size='2' value='0'></td>";   <!-- 含税单价 -->
        <!-- 10 -->
        tableHtml += "<td class='tdpadding'><input type='text' name='netPrice' class='rightalign' id='netPrice' size='5' value='0.00'></td>";	<!-- 净价 -->
            <!-- 15 -->
        tableHtml += "<td class='tdpadding'><input type='text' name='avaliableSumMoney' class='rightalign' id='avaliableSumMoney"+statusIndex+"' size='5'></td>";  <!-- 可用预算金额 -->
        tableHtml += "<td class='tdpadding'><input type='text' name='taxTotalPrice' class='rightalign' id='taxTotalPrice' size='5'></td>";   <!-- 含税总价-->
        tableHtml += "<td class='tdpadding'><input type='text' name='tax' class='rightalign' id='tax' size='5' readonly='readonly'></td>";   <!-- 税额-->
        tableHtml += "<td class='tdpadding'><input type='text' name='assetsIncreaseValue' class='rightalign' id='assetsIncreaseValue' size='5' value='0.00'></td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='text' class='rightalign' id='text' size='5'></td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='budgetYear' class='rightalign' id='budgetYear"+statusIndex+"' size='5' readonly='readonly'></td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='payPrice' class='rightalign' id='payPrice' size='5'></td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='advanceOffsetPrice' class='rightalign' id='advanceOffsetPrice' size='5' value='0.00'></td>";
        tableHtml += "<td class='tdpadding'><input type='text' name='finalPrice' class='rightalign' id='finalPrice' size='5' value='0.00'></td>";
        tableHtml += "<td class='tdpadding'><input readonly='readonly' name='finalPaymentDate' value='' type='text' id='finalPaymentDate' class='Wdate' onclick=WdatePicker({skin:'ext'});></td>";
            <!-- 20 -->
        tableHtml += "<td class='tdpadding'><input type='text' class='rightalign' name='payPriceLocal'  id='payPriceLocal'></td></tr>";


		$("#table_detail").append(tableHtml);
		resetStatusIndex();
	}
	$("#delRows").click(function(){//删除行
		$("#table_detail").find("input:checkbox[name=jvDataList_chkBolDel_0]:checked").each(function(){
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
        var isExpenseAssets = $("#isExpenseAssets").val();
		if($("input:radio[class='btnRadio']:checked")){
		    var assetClassification = $("input:radio[class='btnRadio']:checked").parent().parent().find("input[name='assetClassification']").val();
			if(isExpenseAssets=='Y'&&assetClassification.substring(0,1)!='F'){
				alert("费用化资产分类必须为F开头！");
				return;
			}
			if(isExpenseAssets=='N'&&assetClassification.substring(0,1)=='F'){
				alert("资本化资产分类不得为F开头！");
				return;
			}
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
	function clearDetail(){
		$("input[id^='assetClassification']").each(function(){
			$(this).val("");
		});
		$("input[id^='depreciation']").each(function(){
			$(this).val("");
		});
		$("input[id^='ledgerAccount']").each(function(){
			$(this).val("");
		});
	}
	//资产所属部门 开始
   	$("#table_detail").delegate("input[id^='btntxtZcssbmShow']","click",function(){
		var i = $(this).attr("id").substr(16);
		$("#departmentIndex").val(i);
    	var companyCode = $("#company").val();
		if(companyCode == null||companyCode == ""){
			alert("请先选择公司！");
			return;
		}
		var costCenterCode = $('#costCenterCode'+i).val();
		if(costCenterCode == ""){
			alert(companyCode);
			alert("请先选择成本中心！");
			return;
		}
		showDepCostCenter(companyCode.split("-")[0],costCenterCode);
	});
    function showDepCostCenter(companyCode,costCenterCode){
    	
 		  var url = "${dynamicURL}/notPoPayment/searchPagerList?parentWindowId=add&&companyCode="+companyCode+"&&costCenterCode="+costCenterCode;
 		  var parentWindow=dg.curWin; 
 		  new parentWindow.$.dialog({ id: 'showDepDiscard', title: "所有部门", cover: true, page: url, width: 900, height: 450, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
 		   
 	   }
     function fillDepartMent(depCode, depName,costCenterCode,costCenterName) {
    	 var j = $("#departmentIndex").val();
		   $("#subordinateDepartmentCode"+j).val(depCode);
		   $("#subordinateDepartment"+j).val(depName);
     };
    
     function cleanDep1(btn){
    	 $(btn).parent().find("input[id^='subordinateDepartmentCode']").val("");
    	 $(btn).parent().find("input[id^='subordinateDepartment']").val("");
/*  		$(btn).parent().parent().find("input[id^='depreciation']").val("");
 		$(btn).parent().parent().find("input[id^='ledgerAccount']").val(""); */
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
	 $("#applyFor").keyup(function(){
	 limit(this,200);//200字节内
	 });
	 $("#remark").keyup(function(){
	 limit(this,1000);//1000字节内
 	 });
 	 $("#city").keyup(function(){
 		 limit(this,40);//40字节内
 	 });
	 $("#invoiceNumber").keyup(function(){
		 limit(this,50);//50字节内
	 });
	 $("#specificationParameter").keyup(function(){
		 limit(this,50);//50字节内
	 });
	 $(document).ready(function(){
	        $(window).resize(function(){
	            $('#purchaseTab').tabs('resize');
	        });
  	});
</script>
</body>


</html>