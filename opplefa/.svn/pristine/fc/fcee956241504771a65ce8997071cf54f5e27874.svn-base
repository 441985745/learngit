<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
	<title>办公地点查看</title>
	<meta name="modulePath" content="basic/"/>
	<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
	<link href="${staticURL}/config/css/config.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
<div>
	<table cellspacing="0" cellpadding="0" style="width: 100%; height: 98%;" border="0">
		<tbody>
		<tr id="Button">
			<td class="buttonbar">
				&nbsp;&nbsp;
				<input type="button" value="返回" id="btnBack" class="btn" onclick="dg.cancel();">
			</td>
		</tr>
		<tr>
			<td valign="top">
				<div>
					<form id = "updateForm">
						<table border="0" cellspacing="0" cellpadding="0" id="format">
							<tbody>
							<tr>
								<td class="tdtitle"><span>*</span>办公地点编码:</td>
								<td><input name="caddressCode" disabled type="text" value="${address.caddressCode}" class="colorblur require-red" readonly/> </td>
							</tr>
							<tr>
								<td class="tdtitle"><span>*</span>办公地点:</td>
								<td><input name="caddressName" disabled type="text" value="${address.caddressName}" class="colorblur require-red" readonly/></td>
							</tr>

							<tr>
								<td class="tdtitle"><span>*</span>仓库收货管理员:</td>
								<td>
									<input type="hidden" value="receivegoodsName" class="mark"/>
									<input type="hidden" name="receivegoodsCode" value="${address.receivegoodsCode}" id="receivegoodsCode" class="code"/>
									<input type="text" disabled name="receivegoodsName" value="${address.receivegoodsName}" id="receivegoodsName" class="colorblur name require-red" readonly/>
								</td>
							</tr>
							<tr>
								<td class="tdtitle"><span>*</span>仓库废品管理员:</td>
								<td>
									<input type="hidden" value="retiredgoodsName" class="mark"/>
									<input type="hidden" name="retiredgoodsCode" value="${address.retiredgoodsCode}" id="retiredgoodsCode" class="code"/>
									<input type="text" disabled name="retiredgoodsName" value="${address.retiredgoodsName}" id="retiredgoodsName" class="colorblur name require-red" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td class="tdtitle"><span>*</span>采购意见处理员:</td>
								<td>
									<input type="hidden" value="purchasetipaccessName" class="mark"/>
									<input type="hidden" name="purchasetipaccessCode" value="${address.purchasetipaccessCode}" id="purchasetipaccessCode" class="code"/>
									<input type="text" disabled name="purchasetipaccessName" value="${address.purchasetipaccessName}" id="purchasetipaccessName" class="colorblur name require-red" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td class="tdtitle"><span>*</span>采购竞价/议价员:</td>
								<td>
									<input type="hidden" value="purchasebidName" class="mark"/>
									<input type="hidden" name="purchasebidCode" value="${address.purchasebidCode}" id="purchasebidCode" class="code"/>
									<input type="text" disabled name="purchasebidName" value="${address.purchasebidName}" id="purchasebidName" class="colorblur name require-red" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td class="tdtitle"><span>*</span>收款确认人姓名 :</td>
								<td>
									<input type="hidden" value="payeeName" class="mark"/>
									<input type="hidden" name="payeeCode" value="${address.payeeCode}" id="payeeCode" class="code"/>
									<input type="text" disabled name="payeeName" value="${address.payeeName}" id="payeeName" class="colorblur name require-red" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td class="tdtitle">数据状态 :</td>
								<td>
									<select index="1" name="cstatus" id="txtCstatus" style="width: 60px;" disabled class="colorblur">
										<option value="Y" ${address.cstatus=="Y"?"selected='selected'" : ""}>有效</option>
										<option value="N" ${address.cstatus=="N"?"selected='selected'" : ""}>无效</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tdtitle" style="padding-top: 8px;" valign="top">备注 :</td>
								<td>
									<textarea cols="50" disabled rows="3" name="cmemo" id="cmemo" class="colorblur code" >${address.cmemo}</textarea>
								</td>
							</tr>
							</tbody>
						</table>
					</form>
				</div>
			</td>
		</tr>
		</tbody>
	</table>
</div>
<%--<script src="${staticURL}/Scripts/config/crud.js" type="text/javascript"></script>--%>
<script type="text/javascript">
	var dg = frameElement.lhgDG;

</script>
</body>
</html>

