<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>收货单添加</title>
	<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
	<div style="margin-top: 10px">
	
	<div id="purchaseTab" class="easyui-tabs" style="width:1335px;height:480px;">
	<div>
	创建资产编码
	<form id="sap1">
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
				<tbody>
					<tr>
					<td style="text-align: right"><font color="red" >*</font>资产分类:</td>
						<td class="tdtext" >
						<input class="assetclass" value="4000" name="assetclass" type="text" id="assetType" ">
						</td>
						<td style="text-align: right">公司代码:</td>
						<td>
						<input class="searchinput"   name="companycode" type="text" value="6000" id="goodsName">
						</td>
						
					</tr>
					<tr>
					<td style="text-align: right"><font color="red" >*</font>资产次级编号:</td>
						<td class="tdtext" >
						<input class="searchinput"  name="subnumber" type="text" id="assetType" value="">
						</td>
						<td style="text-align: right"><font color="red" >*</font>descript:</td>
						<td>
							<input class="searchinput"  name="descript" value="sd" type="text" >
						</td>
						<!-- <td style="text-align: right"><font color="red" >*</font>资产名称:</td>
						<td>
							<input class="searchinput"  name="descript" value="ew" type="text" >
						</td> -->
						
					</tr>
					<tr>
					<td style="text-align: right"><font color="red" ></font>库存号:</td>
						<td>
							<input class="searchinput"  name="inventNo" value="" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>数量:</td>
						<td>
							<input class="searchinput"  name="quantity" value="3" type="text" >
						</td>
						
					</tr>
					<tr>
						
						<td style="text-align: right"><font color="red" >*</font>基本计量单位:</td>
						<td class="tdtext" >
						<input class="assetclass" value="件" name="baseUom" type="text" id="assetType" ">
					</td>
					<td style="text-align: right;">成本中心:</td>
						<td class="tdtext">
						<input  name="costcenter" type="text" value="66030401">
						</td>
				</tr>
				<tr>
						<td style="text-align: right"><font color="red" >*</font>oano:</td>
						<td>
							<input class="searchinput"  name="oano" value="sd" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>oaname:</td>
						<td>
							<input class="searchinput"  name="oaname" value="df" type="text" >
						</td>
						
				</tr>
				<tr>
				<td style="text-align: right"><font color="red" >*</font>oamodule:</td>
						<td class="tdtext" >
						<input class="searchinput" value="dfd" name="oamodule" type="text" id="assetType" ">
						</td>
						<td style="text-align: right"><font color="red" >*</font>oaid:</td>
						<td>
							<input class="searchinput"  name="oaid" value="" type="text" >
						</td>
						
				</tr>
					<tr>
						<td style="text-align: right"><font color="red" >*</font>公司:</td>
						<td class="tdtext" >
						<input class="assetclass" value="件" name="company" type="text" id="assetType" ">
						<td style="text-align: right"><font color="red" >*</font>dept:</td>
						<td>
							<input class="searchinput"  name="dept" value="" type="text" >
						</td>
						
						
						<!-- <td style="text-align: right"><font color="red" >*</font>rem:</td>
						<td class="tdtext" >
						<input class="assetclass" value="件" name=remark type="text" id="assetType" ">
					</td> -->
				</tr>
				<tr>
						<td style="text-align: right"><font color="red" >*</font>rem:</td>
						<td class="tdtext" >
						<input class="assetclass" value="" name="remark" type="text" id="assetType" ">
					</td>
					<td style="text-align: right"><font color="red" >*</font>sapno:</td>
						<td class="tdtext" >
						<input class="assetclass" value="" name="sapno" type="text" id="assetType" ">
					</td>
						
					</tr>
					<tr>
						<td style="text-align: right"><font color="red" >*</font>date:</td>
						<td class="tdtext" >
						<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="date" type="text" id="assetType" ">
					</td>
					<!-- <td style="text-align: right"><font color="red" >*</font>time:</td>
						<td class="tdtext" >
						<input class="assetclass" value="401901" name="ztime" type="text" id="assetType" ">
					</td> -->
						
					</tr>
				<tr>
				<td colspan="2">
					<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
						<input type="button" name="btnSave" value="确定" onclick="submita()" id="btnSave" class="btn" >
						<input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn"><!-- onclick="dg.cancel()" -->
					</div>
				</td>
				
				</tr>
				</tbody>
			</table>
			</form>
			<div id="aa"></div>
</div><hr>
<div>
	变更资产编码
	   		<form id="sap">
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
				<tbody>
					<tr>
						<td style="text-align: right">公司代码:</td>
						<td>
						<input class="searchinput"   name="ipBukrs" type="text" value="6000" id="goodsName">
						</td>
						<td style="text-align: right;">成本中心:</td>
						<td class="tdtext">
						<input  name="ipKostl" type="text" value="10150010">
						</td>
					</tr>
					<tr>
						<td style="text-align: right"><font color="red" >*</font>主资产号:</td>
						<td>
							<input class="searchinput"  name="anln1" value="401901" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>资产次级编号:</td>
						<td class="tdtext" >
						<input class="searchinput"  name="anln2" type="text" id="assetType" ">
						</td>
					</tr>
					
								
					<tr>
					<td colspan="2">
						<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
							<input type="button" name="btnSave" value="确定" onclick="submitb()" id="btnSave" class="btn" >
							<input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn"><!-- onclick="dg.cancel()" -->
						</div>
					</td>
					</tr>
				</tbody>
				</table>
			</form>
			<div id="bb"></div>
			</div><hr>
			<div>
			追加资产编码
	   		<form id="sapc">
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
				<tbody>
					<tr>
						<td style="text-align: right">公司代码:</td>
						<td>
						<input class="searchinput"   name="ipBukrs" type="text" value="6000" id="goodsName">
						</td>
						<td style="text-align: right;">成本中心:</td>
						<td class="tdtext">
						<input  name="ipKostl" type="text" value="10150010">
						</td>
					</tr>
					<tr>
						<td style="text-align: right"><font color="red" >*</font>主资产号:</td>
						<td>
							<input class="searchinput"  name="anln1" value="401901" type="text" >
						</td>
						<!-- <td style="text-align: right"><font color="red" >*</font>资产次级编号:</td>
						<td class="tdtext" >
						<input class="searchinput"  name="anln2" type="text" id="assetType" ">
						</td> -->
						<td style="text-align: right"><font color="red" >*</font>count:</td>
						<td>
							<input class="searchinput"  name="count" value="2" type="text" >
						</td>
					</tr>
						<tr>
						
						<!-- <td style="text-align: right"><font color="red" >*</font>资产次级编号:</td>
						<td class="tdtext" >
						<input class="searchinput"  name="anln2" type="text" id="assetType" ">
						</td> -->
					</tr>
					
								
					<tr>
					<td colspan="2">
						<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
							<input type="button" name="btnSave" value="确定" onclick="submitc()" id="btnSave" class="btn" >
							<input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn"><!-- onclick="dg.cancel()" -->
						</div>
					</td>
					</tr>
				</tbody>
				</table>
			</form>
			
			<div id="cc"></div>
			</div>
			
			<div><hr>
			生成采购订单
	   		<form id="sapd">
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
				<tbody>
				<tr>
						<td style="text-align: right">公司代码 *</td>
						<td>
						<input class="searchinput"   name="compCode" type="text" value="6000">
						</td>
						<td style="text-align: right">采购凭证类型 *</td>
						<td>
						<input class="searchinput"   name="docType" type="text" value="ZAB">
						</td>
							<td style="text-align: right">供应商帐户号 *</td>
						<td>
						<input class="searchinput"   name="vendor" type="text" value="0000010039">
						</td>
						<td style="text-align: right"> 采购组织 *</td>
						<td>
						<input class="searchinput"   name="purchOrg" type="text" value="1001">
						</td>
						<td style="text-align: right">采购组 *</td>
						<td>
						<input class="searchinput"   name="purGroup" type="text" value="113">
						</td>
						<td style="text-align: right">收付条件代码 *</td>
						<td>
						<input class="searchinput"   name="pmnttrms" type="text" value="9001">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">表头文本 *</td>
						<td>
						<input class="searchinput"   name="headrText" type="text" value="aaaa">
						</td>
						<td style="text-align: right">名称 1 *</td>
						<td>
						<input class="searchinput"   name="name1" type="text" value="欧普照明股份有限公司">
						</td>
							<td style="text-align: right">街道 *</td>
						<td>
						<input class="searchinput"   name="streetLng" type="text" value="敦化路">
						</td>
						<td style="text-align: right">门牌号</td>
						<td>
						<input class="searchinput"   name="houseNo" type="text" value="1111">
						</td>
						<td style="text-align: right">城市邮政编码</td>
						<td>
						<input class="searchinput"   name="postlCod1" type="text" value="266000">
						</td>
						<td style="text-align: right">city</td>
						<td>
						<input class="searchinput"   name="city" type="text" value="青岛市">
						</td>
					</tr>
					<tr>
						<td style="text-align: right"> 国家代码 *</td>
						<td>
						<input class="searchinput"   name="country" type="text" value="CN">
						</td>
						<td style="text-align: right"> 货币代码 *</td>
						<td>
						<input class="searchinput"   name="currency" type="text" value="CNY">
						</td>
							<td style="text-align: right">我方参考 *</td>
						<td>
						<input class="searchinput"   name="unsez" type="text" value="0">
						</td>
						
						
						<td style="text-align: right"><font color="red" >*</font>oano:</td>
						<td>
							<input class="searchinput"  name="oano" value="sd" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>客户端:</td>
						<td>
							<input class="searchinput"  name="mandt" value="200" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>oaname:</td>
						<td>
							<input class="searchinput"  name="oaname" value="df" type="text" >
						</td>
						
					</td>
				</tr>
				<tr>
				<td style="text-align: right"><font color="red" >*</font>oamodule:</td>
						<td class="tdtext" >
						<input class="searchinput" value="dfd" name="oamodule" type="text" id="assetType" ">
						</td>
						<td style="text-align: right"><font color="red" >*</font>oaid:</td>
						<td>
							<input class="searchinput"  name="oaid" value="df" type="text" >
						</td>
						
						<td style="text-align: right"><font color="red" >*</font>公司:</td>
						<td class="tdtext" >
						<input class="assetclass" value="苏州欧普照明股份有限公司" name="company" type="text" id="assetType" ">
						<td style="text-align: right"><font color="red" >*</font>dept:</td>
						<td>
							<input class="searchinput"  name="dept" value="财务办公室" type="text" >
						</td>
						
						
						<!-- <td style="text-align: right"><font color="red" >*</font>rem:</td>
						<td class="tdtext" >
						<input class="assetclass" value="件" name=remark type="text" id="assetType" ">
					</td> -->
						<td style="text-align: right"><font color="red" >*</font>说明:</td>
						<td class="tdtext" >
						<input class="assetclass" value="件" name="remark" type="text" id="assetType" ">
					</td>
					
					
						<!-- <td style="text-align: right">docType</td>
						<td>
						<input class="searchinput"   name="docType" type="text" value="4000">
						</td>
						<td style="text-align: right">purchOrg</td>
						<td>
						<input class="searchinput"   name="purchOrg" type="text" value="0110">
						</td>
						<td style="text-align: right">purGroup</td>
						<td>
						<input class="searchinput"   name="purGroup" type="text" value="113">
						</td> -->
					</tr>
					<!-- <tr>
						<td>ItZmm0004</td>		
					<tr>
					 -->
					<tr>
						<td style="text-align: right">采购凭证项目编号</td>
						<td>
						<input class="searchinput"   name="poItem" type="text" value="01">
						</td>
						<td style="text-align: right">短文本 *</td>
						<td>
						<input class="searchinput"   name="shortText" type="text" value="水电费水电费">
						</td>
						<td style="text-align: right">物料号 *</td>
						<td>
						<input class="searchinput"   name="material" type="text" value="">
						</td>
						<td style="text-align: right">工厂 *</td>
						<td>
						<input class="searchinput"   name="plant" type="text" value="6000">
						</td>
						<td style="text-align: right"> 库存地点 *</td>
						<td>
						<input class="searchinput"   name="stgeLoc" type="text" value="">
						</td>
						<td style="text-align: right">需求者/要求者名称 *</td>
						<td>
						<input class="searchinput"   name="preqName" type="text" value="赵原">
						</td>
					</tr>
					
					<tr>
						<td style="text-align: right">物料组 *</td>
						<td>
						<input class="searchinput"   name="matlGroup" type="text" value="AM000004">
						</td>
						<td style="text-align: right">采购订单数量 *</td>
						<td>
						<input class="searchinput"   name="quantity" type="text" value="1">
						</td>
							<td style="text-align: right">采购订单单位 *</td>
						<td>
						<input class="searchinput"   name="poUnit" type="text" value="套">
						</td>
						<td style="text-align: right">采购凭证中的项目类别 *</td>
						<td>
						<input class="searchinput"   name="itemCat" type="text" value="W">
						</td>
						<td style="text-align: right"> 科目设置类型 *</td>
						<td>
						<input class="searchinput"   name="acctasscat" type="text" value="A">
						</td>
						<td style="text-align: right">销售/购买税代码 *</td>
						<td>
						<input class="searchinput"   name="taxCode" type="text" value="J0">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">交货日期 *</td>
						<td>
						<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="deliveryDate" type="text" value="2017-12-09">
						</td>
						<td style="text-align: right">总帐科目编号 *</td>
						<td>
						<input class="searchinput"   name="glAccount" type="text" value="1501000701">
						</td>
							<td style="text-align: right">成本中心 *</td>
						<td>
						<input class="searchinput"   name="costcenter" type="text" value="66030401">
						</td>
						<td style="text-align: right">主资产号 *</td>
						<td>
						<input class="searchinput"   name="assetNo" type="text" value="601054">
						</td>
						<td style="text-align: right"> 资产次级编号 *</td>
						<td>
						<input class="searchinput"   name="subNumber" type="text" value="0">
						</td>
						<td style="text-align: right">条件类型 *</td>
						<td>
						<input class="searchinput"   name="condType" type="text" value="PBXX">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">条件单价 *</td>
						<td>
						<input class="searchinput"   name="condUnit" type="text" value="1">
						</td>
						<td style="text-align: right">条件定价单位 *</td>
						<td>
						<input class="searchinput"   name="condPUunt" type="text" value="1">
						</td>
							<td style="text-align: right">行项目文本 *</td>
						<td>
						<input class="searchinput"   name="itemText" type="text" value="aaaa">
						</td>
						<td style="text-align: right">定价比率 *</td>
						<td>
						<input class="searchinput"   name="condValue" type="text" value="0.33">
						</td>
						<td style="text-align: right">订单号 *</td>
						<td>
						<input class="searchinput"   name="orderId" type="text" value="">
						</td>
					</tr>
					<td colspan="2">
						<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
							<input type="button" name="btnSave" value="确定" onclick="submitd()" id="btnSave" class="btn" >
							<input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn"><!-- onclick="dg.cancel()" -->
						</div>
					</td>
					</tr>
				</tbody>
				</table>
			</form>
			<div id="ddd"></div>
			</div>
			<div>
			<hr>预付凭证
	   		<form id="sape">
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
				<tbody>
				<tr>
				<td style="text-align: right">特殊总分类帐标志</td>
						<td>
						<input class="searchinput"   name="spGlInd" type="text" value="A">
						</td>
						<td style="text-align: right">会记年度</td>
						<td>
						<input class="searchinput"   name="fiscYear" type="text" value="2016">
						</td>
				<td style="text-align: right">业务事务</td>
						<td>
						<input class="searchinput"   name="busAct" type="text" value="RFBU">
						</td>
						<td style="text-align: right">用户名</td>
						<td>
						<input class="searchinput"   name="username" type="text" value="赵原">
						</td>
						<td style="text-align: right">公司代码</td>
						<td>
						<input class="searchinput"   name="compCode1" type="text" value="6000">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">凭证中的凭证日期</td>
						<td>
						<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="docDate" type="text" value="2016-12-09">
						</td>
						<td style="text-align: right">凭证中的记帐日期</td>
						<td>
						<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="pstngDate" type="text" value="2016-12-09">
						</td>
					</tr>
					<tr>
						<!-- <td style="text-align: right">D-Netterms</td>
						<td>
						<input class="searchinput"   name="netterms" type="text" value="6">
						</td> -->
						<td style="text-align: right">凭证类型</td>
						<td>
						<input class="searchinput"   name="docType" type="text" value="SA">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">参考凭证编号（付款单据编号）:</td>
						<td>
						<input class="searchinput"   name="refDocNo" type="text" value="PUES201608051110">
						</td>
						<td style="text-align: right;">会计凭证行项目编号:</td>
						<td class="tdtext">
						<input  name="itemnoAcc" type="text" value="0000000010">
						</td>
					</tr>
					<tr>
						<td style="text-align: right"><font color="red" >*</font>供应商或债权人的帐号:</td>
						<td>
							<input class="searchinput"  name="vendorNo" value="0000010039" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>公司代码:</td>
						<td class="tdtext" >
						<input class="searchinput"  name="compCode" type="text" id="assetType" value="4000">
						</td>
					</tr>
						<tr>
						<td style="text-align: right"><font color="red" >*</font>业务范围:</td>
						<td>
							<input class="searchinput"  name="busArea" value="6001" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>到期日计算的基限日期:</td>
						<td class="tdtext" >
						<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="blineDate" type="text" value="2016-12-09">
						</td>
					
						</tr>
						<tr>
						<td style="text-align: right"><font color="red" >*</font>定位号(挂账人(申请人)):</td>
						<td>
							<input class="searchinput"  name="allocNmbr" value="" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>项目文本:</td>
						<td class="tdtext" >
						<input class="searchinput" value="9000"  name="itemText" type="text" id="assetType" ">
						</td>
					</tr>
					
					<tr>
						<td style="text-align: right"><font color="red" >*</font>会计凭证行项目编号:</td>
						<td>
							<input class="searchinput"  name="itemnoAcc" value="6001" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>货币代码:</td>
						<td class="tdtext" >
						<input class="searchinput"  name="currency" type="text" value="CNY">
						</td>
					
						</tr>
						<tr>
						<td style="text-align: right"><font color="red" >*</font>凭证货币金额 :</td>
						<td>
							<input class="searchinput"  name="amtDoccur" value="0" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>BAPI 表扩展的结构名称</td>
						<td class="tdtext" >
						<input class="searchinput" value="ZRSTRG"  name="structure" type="text" id="assetType" ">
						</td>
						<td style="text-align: right"><font color="red" >*</font>BAPI 扩展参数的数据部分:</td>
						<td class="tdtext" >
						<input class="searchinput" value="A"  name="valuepart1" type="text" id="assetType" ">
						</td>
					</tr>
					
								
					<tr>
					<td colspan="2">
						<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
							<input type="button" name="btnSave" value="确定" onclick="submite()" id="btnSave" class="btn" >
							<input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn"><!-- onclick="dg.cancel()" -->
						</div>
					</td>
					</tr>
				</tbody>
				</table>
			</form>
			<div id="eee"></div>
	</div>
	<div>
	<hr>验收款凭证
	   		<form id="sape">
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
				<tbody>
				<tr>
						<td style="text-align: right">业务事务</td>
						<td>
						<input class="searchinput"   name="busAct" type="text" value="RFBU">
						</td>
						<td style="text-align: right">用户名</td>
						<td>
						<input class="searchinput"   name="userName" type="text" value="6000">
						</td>
							<td style="text-align: right"><font color="red" >*</font>公司代码:</td>
						<td class="tdtext" >
						<input class="searchinput"  name="compCode" type="text" value="6000" id="assetType" ">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">凭证中的凭证日期</td>
						<td>
						<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="docDate" type="text" value="2016-12-09">
						</td>
						<td style="text-align: right">凭证中的记帐日期</td>
						<td>
						<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="pstngDate" type="text" value="2016-12-09">
						</td>
					</tr>
					<tr>
						<!-- <td style="text-align: right">D-Netterms</td>
						<td>
						<input class="searchinput"   name="netterms" type="text" value="6">
						</td> -->
						<td style="text-align: right">凭证类型</td>
						<td>
						<input class="searchinput"   name="docType" type="text" value="SA">
						</td>
					</tr>
					<tr>
						<td style="text-align: right">参考凭证编号（付款单据编号）:</td>
						<td>
						<input class="searchinput"   name="refDocNo" type="text" value="6000">
						</td>
						<td style="text-align: right;">会计凭证行项目编号:</td>
						<td class="tdtext">
						<input  name="itemnoAcc" type="text" value="01">
						</td>
					</tr>
					<tr>
						<td style="text-align: right"><font color="red" >*</font>供应商或债权人的帐号:</td>
						<td>
							<input class="searchinput"  name="vendorNo" value="" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>公司代码:</td>
						<td class="tdtext" >
						<input class="searchinput"  name="compCode" type="text" id="assetType" ">
						</td>
					</tr>
						<tr>
						<td style="text-align: right"><font color="red" >*</font>业务范围:</td>
						<td>
							<input class="searchinput"  name="busArea" value="6001" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>到期日计算的基限日期:</td>
						<td class="tdtext" >
						<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="blineDate" type="text" value="2016-12-09">
						</td>
					
						</tr>
						<tr>
						<td style="text-align: right"><font color="red" >*</font>定位号(挂账人(申请人)):</td>
						<td>
							<input class="searchinput"  name="allocNmbr" value="20161204" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>项目文本:</td>
						<td class="tdtext" >
						<input class="searchinput" value="9000"  name="itemText" type="text" id="assetType" ">
						</td>
					</tr>
					
					<tr>
						<td style="text-align: right"><font color="red" >*</font>会计凭证行项目编号:</td>
						<td>
							<input class="searchinput"  name="itemnoAcc" value="6001" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>货币代码:</td>
						<td class="tdtext" >
						<input class="searchinput"  name="currency" type="text" value="CNY">
						</td>
					
						</tr>
						<tr>
						<td style="text-align: right"><font color="red" >*</font>凭证货币金额 :</td>
						<td>
							<input class="searchinput"  name="amtDoccur" value="0" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>BAPI 表扩展的结构名称</td>
						<td class="tdtext" >
						<input class="searchinput" value="ZRSTRG"  name="structure" type="text" id="assetType" ">
						</td>
						<td style="text-align: right"><font color="red" >*</font>BAPI 扩展参数的数据部分:</td>
						<td class="tdtext" >
						<input class="searchinput" value="A"  name="当预付场景(一条付款数据生成两行数据29和39)同时特别总账标识为A" type="text" id="assetType" ">
						</td>
					</tr>
					
								
					<tr>
					<td colspan="2">
						<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
							<input type="button" name="btnSave" value="确定" onclick="submite()" id="btnSave" class="btn" >
							<input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn"><!-- onclick="dg.cancel()" -->
						</div>
					</td>
					</tr>
				</tbody>
				</table>
			</form>
			<div id="fff"></div>
	</div>
	<div>
			<hr>固定资产调拨
	   		<form id="sapg">
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
				<tbody>
					<tr>
					<td style="text-align: right"><font color="red" >*</font>资产主编号:</td>
						<td class="tdtext" >
						<input class="assetclass" value="401901" name="ipAsset" type="text" id="assetType" ">
						</td>
					<td style="text-align: right"><font color="red" >*</font>资产分类:</td>
						<td class="tdtext" >
						<input class="assetclass" value="" placeholder="不可填写，填写报错" name="assetclass" type="text" id="assetType" ">
						</td>
						<td style="text-align: right">公司代码:</td>
						<td>
						<input class="searchinput"   name="companycode" type="text" value="6000" id="goodsName">
						</td>
						
					</tr>
					<tr>
					<td style="text-align: right"><font color="red" >*</font>资产次级编号:</td>
						<td class="tdtext" >
						<input class="searchinput"  name="subnumber" type="text" id="assetType" value="0">
						</td>
						<td style="text-align: right"><font color="red" >*</font>descript:</td>
						<td>
							<input class="searchinput"  name="descript" value="扫描枪/PDA Intenic ck3-转卖6000" type="text" >
						</td>
						<!-- <td style="text-align: right"><font color="red" >*</font>资产名称:</td>
						<td>
							<input class="searchinput"  name="descript" value="ew" type="text" >
						</td> -->
						
					</tr>
					<tr>
					<td style="text-align: right"><font color="red" ></font>序列号:</td>
						<td>
							<input class="searchinput"  name="serialNo" value="" type="text" >
						</td>
						<td style="text-align: right"><font color="red" ></font>库存号:</td>
						<td>
							<input class="searchinput"  name="inventNo" value="" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>数量:</td>
						<td>
							<input class="searchinput"  name="quantity" value="3" type="text" >
						</td>
						
					</tr>
					<tr>
						
						<td style="text-align: right"><font color="red" >*</font>补充库存说明:</td>
						<td class="tdtext" >
						<input class="assetclass" value="件" name="note" type="text" id="assetType" ">
					</td>
					<td style="text-align: right;">成本中心:</td>
						<td class="tdtext">
						<input  name="costcenter" type="text" value="66030401">
						</td>
				</tr>
				<tr>
						<td style="text-align: right"><font color="red" >*</font>oano:</td>
						<td>
							<input class="searchinput"  name="oano" value="sd" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>oaname:</td>
						<td>
							<input class="searchinput"  name="oaname" value="df" type="text" >
						</td>
						
					</td>
				</tr>
				<tr>
				<td style="text-align: right"><font color="red" >*</font>oamodule:</td>
						<td class="tdtext" >
						<input class="searchinput" value="dfd" name="oamodule" type="text" id="assetType" ">
						</td>
						<td style="text-align: right"><font color="red" >*</font>oaid:</td>
						<td>
							<input class="searchinput"  name="oaid" value="df" type="text" >
						</td>
						
				</tr>
					<tr>
						<td style="text-align: right"><font color="red" >*</font>公司:</td>
						<td class="tdtext" >
						<input class="assetclass" value="件" name="company" type="text" id="assetType" ">
						<td style="text-align: right"><font color="red" >*</font>dept:</td>
						<td>
							<input class="searchinput"  name="dept" value="sd" type="text" >
						</td>
						
						
						<!-- <td style="text-align: right"><font color="red" >*</font>rem:</td>
						<td class="tdtext" >
						<input class="assetclass" value="件" name=remark type="text" id="assetType" ">
					</td> -->
				</tr>
				<tr>
						<td style="text-align: right"><font color="red" >*</font>rem:</td>
						<td class="tdtext" >
						<input class="assetclass" value="件" name="remark" type="text" id="assetType" ">
					</td>
					<td style="text-align: right"><font color="red" >*</font>SAP操作人ID:</td>
						<td class="tdtext" >
						<input class="assetclass" value="401901" name="sapid" type="text" id="assetType" ">
					</td>
						
					</tr>
					<tr>
						<td style="text-align: right"><font color="red" >*</font>date:</td>
						<td class="tdtext" >
						<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="date" type="text" id="assetType" ">
					</td>
					<!-- <td style="text-align: right"><font color="red" >*</font>time:</td>
						<td class="tdtext" >
						<input class="assetclass" value="401901" name="ztime" type="text" id="assetType" ">
					</td> -->
						
					</tr>
				<tr>
				<td colspan="2">
					<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
						<input type="button" name="btnSave" value="确定" onclick="submitg()" id="btnSave" class="btn" >
						<input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn"><!-- onclick="dg.cancel()" -->
					</div>
				</td>
				
				</tr>
				</tbody>
				</table>
			</form>
			<div id="ggg"></div>
	</div>
	
	<div>
			<hr>采购订单收货
	   		<form id="saph">
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
				<tbody>
					</tr>
						<tr>
						<td style="text-align: right"><font color="red" >*</font>凭证中的记帐日期:</td>
						<td>
							<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="pstngDate" type="text" value="2016-12-09">
						</td>
						<td style="text-align: right"><font color="red" >*</font>凭证中的凭证日期:</td>
						<td class="tdtext" >
						<input class="Wdate" onclick="WdatePicker({skin:'ext'});"   name="docDate" type="text" value="2016-12-09">
						</td>
						<td style="text-align: right"><font color="red" >*</font>用户名:</td>
						<td>
							<input class="searchinput"  name="uname" value="赵原" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>凭证抬头文本:</td>
						<td class="tdtext" >
						<input class="searchinput" value="aaa"  name="bktxt" type="text" id="assetType" ">
						</td>
					</tr>
				<tr>
					<td style="text-align: right"><font color="red" >*</font>OA UNID:</td>
						<td>
							<input class="searchinput"  name="oano" value="123" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>OA操作人姓名:</td>
						<td>
							<input class="searchinput"  name="oaname" value="赵原" type="text" >
						</td>
						
						<td style="text-align: right"><font color="red" >*</font>OA模块名:</td>
						<td class="tdtext" >
						<input class="searchinput" value="aaa" name="oamodule" type="text" id="assetType" ">
						</td>
						<td style="text-align: right"><font color="red" >*</font>OA操作人ID:</td>
						<td>
							<input class="searchinput"  name="oaid" value="123" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>SAP操作人ID:</td>
						<td>
							<input class="searchinput"  name="sapid" value="" type="text" >
						</td>
						
					</tr>
					
					<tr>
					<td style="text-align: right"><font color="red" >*</font>物料号:</td>
						<td>
							<input class="searchinput"  name="material" value="sd" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>工厂:</td>
						<td>
							<input class="searchinput"  name="plant" value="6000" type="text" >
						</td>
						
						<td style="text-align: right"><font color="red" >*</font>库存地点:</td>
						<td class="tdtext" >
						<input class="searchinput" value="" name="stgeLoc" type="text" id="assetType" ">
						</td>
						<td style="text-align: right"><font color="red" >*</font>条目单位的数量:</td>
						<td>
							<input class="searchinput"  name="entryQnt" value="1" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>成本中心:</td>
						<td>
							<input class="searchinput"  name="costCenter" value="66030401" type="text" >
						</td>
						
					</tr>
					
					
					<tr>
					<td style="text-align: right"><font color="red" >*</font>项目文本:</td>
						<td>
							<input class="searchinput"  name="itemText" value="" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>采购订单号:</td>
						<td>
							<input class="searchinput"  name="poNumber" placeholder="请查找有效的可进行收货的采购订单" value="" type="text" >
						</td>
						
						<td style="text-align: right"><font color="red" >*</font>采购凭证的项目编号:</td>
						<td class="tdtext" >
						<input class="searchinput" value="01" name="poItem" type="text" id="assetType" ">
						</td>
						<td style="text-align: right"><font color="red" >*</font>移动标识:</td>
						<td>
							<input class="searchinput"  name="mvtInd" value="B" type="text" >
						</td>
						<td style="text-align: right"><font color="red" >*</font>移动类型:</td>
						<td>
							<input class="searchinput"  name="moveType" value="101" type="text" >
						</td>
						
					</tr>
				<tr>
				<td colspan="2">
					<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
						<input type="button" name="btnSave" value="确定" onclick="submith()" id="btnSave" class="btn" >
						<input type="button" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn"><!-- onclick="dg.cancel()" -->
					</div>
				</td>
				
				</tr>
				</tbody>
				</table>
			</form>
			<div id="hhh"></div>
	</div>
</div>
<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/Scripts/JQuery/jquery-1.4.4.min.js"></script>
<script type="text/javascript">

function submita(){
	 $.ajax({
		 type: "POST",
		 cache:false,
       url: "${dynamicURL}/sap/createAssetsCodeFromSAP",
       data: $('#sap1').serialize(),
      	dataType: "json",
       async: false,
       error: function(request) {
           alert("Connection error");
       },
       success: function(data){
       	$("#aa").append( "<br>"+JSON.stringify(data));
       }
   });
}

function submitb(){
	 $.ajax({
		 type: "POST",
		 cache:false,
        url: "${dynamicURL}/sap/readAssetsCodeFromSAP",
        data: $('#sap').serialize(),
       	dataType: "json",
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data){
        	$("#bb").append( "<br>"+JSON.stringify(data));
        }
    });
}
function submitc(){
	 $.ajax({
		 type: "POST",
		 cache:false,
       url: "${dynamicURL}/sap/getAssetsCodeForAppendFromSAP",
       data: $('#sapc').serialize(),
      	dataType: "json",
       async: false,
       error: function(request) {
           alert("Connection error");
       },
       success: function(data){
       	$("#cc").append("<br>"+ JSON.stringify(data));
       }
   });
}
function submitd(){
	 $.ajax({
		 type: "POST",
		 cache:false,
      url: "${dynamicURL}/sap/getPurchaseOrderFromSAP", 
      data: $('#sapd').serialize(),
     	dataType: "json",
      async: false,
      error: function(request) {
          alert("Connection error");
      },
      success: function(data){
      	$("#ddd").append("<br>"+ JSON.stringify(data));
      }
  });
}	 

function submite(){
	 $.ajax({
		 type: "POST",
		 cache:false,
     url: "${dynamicURL}/sap/getPrePaymentVoucherFromSAP", 
     data: $('#sape').serialize(),
    	dataType: "json",
     async: false,
     error: function(request) {
         alert("Connection error");
     },
     success: function(data){
     	$("#eee").html( "<br>"+JSON.stringify(data));
     }
 });
}	 

function submitf(){
	 $.ajax({
		 type: "POST",
		 cache:false,
    url: "${dynamicURL}/sap/assetsChangeFromSAP", 
    data: $('#sapf').serialize(),
   	dataType: "json",
    async: false,
    error: function(request) {
        alert("Connection error");
    },
    success: function(data){
    	$("#fff").html( JSON.stringify(data));
    }
});
}	

function submitg(){
	 $.ajax({
		 type: "POST",
		 cache:false,
   url: "${dynamicURL}/sap/assetsChangeFromSAP", 
   data: $('#sapg').serialize(),
  	dataType: "json",
   async: false,
   error: function(request) {
       alert("Connection error");
   },
   success: function(data){
   	$("#ggg").html( JSON.stringify(data));
   }
});
}	
function submith(){
	 $.ajax({
		 type: "POST",
		 cache:false,
   url: "${dynamicURL}/sap/purchaseOrderReceiveGoods", 
   data: $('#saph').serialize(),
  	dataType: "json",
   async: false,
   error: function(request) {
       alert("Connection error");
   },
   success: function(data){
   	$("#fff").html( JSON.stringify(data));
   }
});
}	
	
</script>
</body>


</html>
