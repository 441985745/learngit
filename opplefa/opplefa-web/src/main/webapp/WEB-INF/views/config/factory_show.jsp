<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
	<title>工厂修改</title>
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
				<%--<input type="button" name="btnSave" value="保存" onclick="" id="btnSave" class="btn">--%>
				<input type="button" name="btnCancel" value="返回" id="btnCancel" class="btn" onclick="dg.cancel()">
			</td>
		</tr>
		<tr>
			<td valign="top">
				<div>
					<form id = "updateConfigForm">
						<table border="0" cellspacing="0" cellpadding="0" id="format">
							<tbody>
							<tr>
								<td class="tdtitle"><span>*</span>工厂编码:</td>
								<td><input name="cfactoryCode" type="text" disabled value="${factory.cfactoryCode}" class="colorblur require-red" readonly/> </td>
							</tr>
							<tr>
								<td class="tdtitle"><span>*</span>工厂名称:</td>
								<td><input name="cfactoryName" type="text" value="${factory.cfactoryName}" disabled readonly class="colorblur require-red"/></td>
							</tr>

							<tr>
								<td class="tdtitle"><span>*</span>公司编码:</td>
								<td>
									<input type="text" name="ccompanyCode" disabled value="${factory.ccompanyCode}" id="" class="colorblur name require-red" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td class="tdtitle"><span>*</span>公司名称:</td>
								<td>
									<input type="text" disabled name="ccompanyName" class="colorblur name require-red" value="${factory.ccompanyName}" id="companyName" readonly/>
								</td>
							</tr>

							<tr>
								<td class="tdtitle">数据状态 :</td>
								<td>
									<select index="1" name="cstatus" id="txtCstatus" style="border: #cccccc 1px double;width: 60px;" disabled>
										<option value="Y" ${factory.cstatus=="Y"?"selected='selected'" : ""}>有效</option>
										<option value="N" ${factory.cstatus=="N"?"selected='selected'" : ""}>无效</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tdtitle" style="padding-top: 8px;" valign="top">备注 :</td>
								<td>
									<textarea disabled style="border: #cccccc 1px double;" cols="50" rows="3" name="cmemo" id="cmemo" class="code">${factory.cmemo}</textarea>
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
<script type="text/javascript">
    var dg = frameElement.lhgDG;

</script>
</body>


</html>
