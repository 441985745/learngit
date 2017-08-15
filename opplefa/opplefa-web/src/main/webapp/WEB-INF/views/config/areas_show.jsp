<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <title>区域配置修改</title>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">

    <style type="text/css">
        #page-selection ul li {
            display: inline;
        }
        span {
            color: red;
        }
        #format {
            width: 100%;
            height:100%;
            border-right:1px solid lightgrey;
            border-bottom:1px solid lightgrey;
        }
        #format td {
            border-left:1px solid lightgrey;
            border-top:1px solid lightgrey;
        }
        .tdtitle {
            width: 20%;
            text-align: right;
        }
    </style>
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
                    <form id = "areasUpdateForm">
                    <table border="0" cellspacing="0" cellpadding="0" id="format">
                        <tbody>
                        <tr>
                            <td class="tdtitle"><span>*</span>区域编码:</td>
                            <td><input name="areaCode" type="text" value="${area.areaCode}" class="colorblur" disabled/> </td>
                        </tr>
                        <tr>
                            <td class="tdtitle"><span>*</span>区域名称:</td>
                            <td><input name="areaName" type="text" value="${area.areaName}" class="colorblur" disabled/> </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">区域类型:</td>
                            <td><input name="areaType" type="text" value="${area.areaType == '0' ? "家居" : "商用" }" class="colorblur" disabled/> </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">数据状态:</td>
                            <td><input name="areaType" type="text" value="${area.status == '0' ? "有效" : "无效"}" class="colorblur" disabled/> </td>
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