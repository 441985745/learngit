<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>付款选择</title>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
<div >
	<input type="button" id="btnSave" value="确定" class="btn" style="margin-left: 5px;" />
  
    <input type="button" id="btnBack" value="返回" class="btn" />
    <br/>
    <label class="sr-only" style="margin-left: 5px;font-size: 14px">付款类型:</label>
    <select style="width: 150px;" name="paymentType" id="paymentType">
			<option value="">--请选择--</option>
			<option value="prepay">预付款</option>
			<option value="check">验收款</option>
			<option value="final">尾款</option>
	</select>
   <!--  <br/>
    <input type="button" id="downloadBtn" value="下载模版" class="btn" style="margin: 5px 5px;" />
  
    <input type="button" id="confirmImport" value="确定导入" class="btn" /> -->
</div>
<script type="text/javascript">
var dg = frameElement.lhgDG;//定义dg 关闭需要
$("#btnSave").click(function() {//提交
	
	if($("#paymentType").val()==""){
		alert("请选择付款类别!");
  		return;
	}
	var paymentType = $("#paymentType").val();
  	
  	dg.curWin.addPayment(paymentType);
    dg.cancel();
});
$("#btnBack").click(function() {//返回
    dg.cancel();
});
</script>
</body>
</html>