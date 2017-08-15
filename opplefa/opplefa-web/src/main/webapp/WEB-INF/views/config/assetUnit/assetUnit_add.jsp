<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
	<title>单位添加</title>
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
				<input type="button" name="btnSave" value="保存" id="btnSave" class="btn">
				<input type="button" value="返回" id="btnBack" class="btn" onclick="dg.cancel();">
			</td>
		</tr>
		<tr>
			<td valign="top">
				<div>
					<form id = "addForm">
						<table border="0" cellspacing="0" cellpadding="0" id="format">
							<tbody>
							<tr>
								<td class="tdtitle"><span>*</span>单位编码:</td>
								<td><input name="unitCode" type="text" class="colorblur require-red" /> </td>
							</tr>
							<tr>
								<td class="tdtitle"><span>*</span>单位名称:</td>
								<td><input name="unitName" type="text" class="colorblur require-red"/></td>
							</tr>
							<tr>
								<td class="tdtitle">数据状态:</td>
								<td>
									<select index="1" name="status" class="colorblur" id="txtStatus" style="width: 60px;">
										<option value="Y" selected="selected">有效</option>
										<option value="N">无效</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tdtitle" style="padding-top: 8px;" valign="top">备注 :</td>
								<td>
									<textarea cols="50" rows="3" name="memo" id="memo" class="colorblur code"></textarea>
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
	//解决ie报错 对象没有trim属性或方法
	String.prototype.trim = function () {
		return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
	};

	/*检查必填项*/
	function checkRequire() {
		var flag = true;
		$(".require-red").each(function(){
			if($(this).val().trim() == ""){
				flag = false;
			}
		});
		return flag;
	}

	function add (dg, that, url) {
		$(that).attr("disabled","disabled");
		$(that).val("保存ing");

		if(checkRequire()) {
			$.ajax({
				cache: true,
				type: "POST",
				url: url,
				data: $("#addForm").serialize(),
				async: false,
				error: function () {
					alert("抱歉，此次添加未成功，可继续添加");
				},
				success: function (data) {
					if(data.successMessage==="保存成功"){
						alert("保存成功");
						dg.curWin.location.reload();
					}
					else{
						alert(data.successMessage);
					}
				}
			});
		} else {
			alert("请核对必填信息项");
		}
		$(that).removeAttr("disabled");
		$(that).val("保存");
	}

	$(function () {
		/*添加*/
		$("#btnSave").click(function(){//保存
			add(dg, this, '${dynamicURL}/config/assetUnit/add');
		});
	});


</script>
</body>
</html>