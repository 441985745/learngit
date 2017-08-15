<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>查看</title>
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
		        查看</div>
	</div>
	<div id="Div2" style="margin-left: 0px;  margin-top: 0px;">
		<input type="submit" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
	</div>
	<div style="margin-top: 1px">
		<div id="purchaseTab"  style="width:950px;height:400px;">
			<div class="easyui-tabs" id="setting" style="background:#F8F8FF;" >
				<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
					<tbody>
					<tr>
						<td class="titlefontsize" align="left">
						<div class="imgtitle" ></div>
						基本信息</td>
					</tr>
					<tr>
						<td colspan="2">
							<table id="Table10"  width="100%" height="300px"   border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;border: solid 1px #ccc;">
								<tbody>
								<tr>
									<td class="tdtitle" style="text-align: right" colspan="2">
										<div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
											<div>
												<table class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;border: solid 1px #ccc;">
													<tbody>
													
													<tr style="white-space:nowrap;">
														<th scope="col" style="text-align: right;" >资产编号：</th>
														<td scope="col" style="text-align:left;">${generalAsset.assetCode}</td>
														<th scope="col" style="text-align: right;" >使用部门：</th>
														<td scope="col" style="text-align:left;" >${generalAsset.persentDepartent}</td>
														<th scope="col" style="text-align: right;" >资产原值：</th>
														<td scope="col" style="text-align:left;">${generalAsset.assetOriginalValue}</td>
														
													</tr>
													<tr style="white-space:nowrap;">
														<th scope="col" style="text-align: right;" >资产类型：</th>
														<td scope="col" style="text-align:left;">
															<c:if test="${generalAsset.assetType=='0'}">IT资产(电脑类)</c:if>
						                                 	<c:if test="${generalAsset.assetType=='1'}">IT资产(非电脑类)</c:if>
						                                 	<c:if test="${generalAsset.assetType=='2'}">行政资产</c:if>
						                                 	<c:if test="${generalAsset.assetType=='3'}">计量器具</c:if>
						                                 	<c:if test="${generalAsset.assetType=='4'}">机器设备</c:if>
														</td>
														<th scope="col" style="text-align: right;" >责任人：</th>
														<td scope="col" style="text-align:left;" >${generalAsset.assetPerson}</td>
														<th scope="col" style="text-align: right;" >资产净值：</th>
														<td scope="col" style="text-align:left;">${generalAsset.assetNetValue}</td>
													
													</tr>
													<tr style="white-space:nowrap;">
														<th scope="col" style="text-align: right;" >资产名称：</th>
														<td scope="col" style="text-align:left;">${generalAsset.assetName}</td>
														<th scope="col" style="text-align: right;" >状态：</th>
														<td scope="col" style="text-align:left;" >${generalAsset.useState}</td>
														<th scope="col" style="text-align: right;" >购置日期：</th>
														<td scope="col" style="text-align:left;"><fmt:formatDate value="${generalAsset.purchaseDate}" pattern="yyyy-MM-dd" /></td>
														
													</tr>
													<tr style="white-space:nowrap;">
														<th scope="col" style="text-align: right;" >成本中心：</th>
														<td scope="col" style="text-align:left;">${generalAsset.persentDepartent}</td>
														<th scope="col" style="text-align: right;" >公司：</th>
														<td scope="col" style="text-align:left;" >${generalAsset.persentCompany}</td>
														<!-- <th scope="col" style="text-align: right;" ></th>
														<th scope="col" style="text-align:left;"></th> -->
													
													</tr>
													
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
<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;//定义dg 关闭需要
</script>
</body>
</html>