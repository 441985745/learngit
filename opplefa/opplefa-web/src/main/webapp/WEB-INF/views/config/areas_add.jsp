<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <title>区域配置添加</title>
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
                <input type="button" name="btnSave" value="保存" id="btnSave" class="btn">
                <input type="button" value="返回" id="btnBack" class="btn" onclick="dg.cancel();">
            </td>
        </tr>
        <tr>
            <td valign="top">
                <div>
                    <form id = "areasAddForm">
                    <table border="0" cellspacing="0" cellpadding="0" id="format">
                        <tbody>
                        <tr>
                            <td class="tdtitle"><span>*</span>区域编码:</td>
                            <td><input name="areaCode" type="text" class="colorblur" onblur="blur(this, '区域编码不能为空')"/> </td>
                        </tr>
                        <tr>
                            <td class="tdtitle"><span>*</span>区域名称:</td>
                            <td><input name="areaName" type="text" class="colorblur" onblur="blur(this, '区域名称不能为空')"/> </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">区域类型:</td>
                            <td><select name="areaType" class="colorblur" style="border: #cccccc 1px double;width: 60px;">
                                <option value="0">家居</option>
                                <option value="1">商用</option>
                            </select></td>
                        </tr>
                        <tr>
                            <td class="tdtitle">数据状态:</td>
                            <td><select name="status" class="colorblur" style="border: #cccccc 1px double;width: 60px;">
                                <option value="0">有效</option>
                                <option value="1">无效</option>
                            </select></td>
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

    function blur(that, content) {
        if ($(that).val() == "") {
            alert(content);
            $(that).focus();
        }
    }


    $("#btnSave").click(function () {
        if ($("input[name=areaCode]").val() == '' || $("input[name=areaName]").val() == '') {
            alert("存在必填项为空！请检查");
            return;
        }
        $("#btnSave").val("保存ing");
        $("#btnSave").attr("disabled", true);
        $.ajax({
            url : "${dynamicURL}/area/api/addArea",
            type : "post",
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify($("#areasAddForm").serializeObject()),
            success : function (data) {
                if (data.success) {
                    alert("保存成功！");
                    dg.curWin.location.reload();
                } else {
                    alert(data.error);
                    $("#btnSave").val("保存");
                    $("#btnSave").attr("disabled", false);
                }
            }

        });
    });
</script>
</body>
</html>