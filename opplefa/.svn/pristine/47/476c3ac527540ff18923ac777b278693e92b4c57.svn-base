<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
	<title>存放位置配置</title>
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
				<%--<input type="button" name="btnSave" value="保存" id="btnSave" class="btn">--%>
				<input type="button" value="返回" id="btnBack" class="btn" onclick="dg.cancel();">
			</td>
		</tr>
		<tr>
			<td valign="top">
				<div>
					<form id="updatePositionForm">
						<table border="0" cellspacing="0" cellpadding="0" id="format">
							<tbody>
							<tr>
								<td class="tdtitle"><span>*</span>存放位置编码:</td>
								<td><input name="cpositionCode" disabled type="text" value="${position.cpositionCode}" class="colorblur require-red" id="txtAssetsCode" readonly/> </td>
							</tr>
							<tr>
								<td class="tdtitle"><span>*</span>存放位置名称:</td>
								<td><input name="cposition" disabled id="txtAssetsName" type="text" value="${position.cposition}" class="colorblur require-red"/></td>
							</tr>

							<tr>
								<td class="tdtitle"><span>*</span>办公地点名称:</td>
								<td>
									<input type="hidden" value="receivegoodsName" class="mark"/>
									<input type="hidden" name="workAddressCode" value="${position.workAddressCode}" id="workAddressCode" class="code"/>
									<input type="text" name="workAddress" value="${position.workAddress}" id="workAddress" class="td_input name require-red" disabled="readonly" style="border: #cccccc 1px double;width: 200px;">
								</td>
							</tr>
							<tr>
								<td class="tdtitle">数据状态 :</td>
								<td>
									<select index="1" name="cstatus" id="txtCstatus" style="border: #cccccc 1px double;width: 60px;" disabled>
										<option value="Y" ${position.cstatus=="Y"?"selected='selected'" : ""}>有效</option>
										<option value="N" ${position.cstatus=="N"?"selected='selected'" : ""}>无效</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tdtitle" style="padding-top: 8px;" valign="top">备注 :</td>
								<td>
									<textarea cols="50" rows="3" name="cmemo" id="cmemo" class="code" style="border: #cccccc 1px double;" disabled>${position.cmemo}</textarea>
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