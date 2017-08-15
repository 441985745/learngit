<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <title>预算资产类型修改</title>
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
                    <form id = "updateConfigForm">
                        <table border="0" cellspacing="0" cellpadding="0" id="format">
                            <tbody>
                            <tr>
                                <td class="tdtitle"><span>*</span>预算资产类型:</td>
                                <td><input name="budgetAssetsType" type="text" value="${budgetAssetstypeConfig.budgetAssetsType}" class="colorblur require-red" disabled/> </td>
                            </tr>
                            <tr>
                                <td class="tdtitle"><span>*</span>资产类型:</td>
                                <td><input name="assetsType" type="text" value="${budgetAssetstypeConfig.assetsType}" disabled class="colorblur require-red"/></td>
                            </tr>
                            <tr>
                                <td class="tdtitle">数据状态 :</td>
                                <td>
                                    <select index="1" name="status" id="txtstatus" style="border: #cccccc 1px double;width: 60px;" disabled>
                                        <option value="Y" ${budgetAssetstypeConfig.status=="Y"?"selected='selected'" : ""}>有效</option>
                                        <option value="N" ${budgetAssetstypeConfig.status=="N"?"selected='selected'" : ""}>无效</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle" style="padding-top: 8px;" valign="top">备注 :</td>
                                <td>
                                    <textarea cols="50" rows="3" disabled name="memo" id="memo" style="border: #cccccc 1px double;" class="code">${budgetAssetstypeConfig.memo}</textarea>
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
