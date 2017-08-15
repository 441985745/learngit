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
                <input name="deptCode" type="hidden" id="deptCode" class="deptCode code"/>
            </td>
        </tr>
        <tr>
            <td valign="top">
                <div>
                    <form id = "assetClassificationAddForm">
                    <table border="0" cellspacing="0" cellpadding="0" id="format">
                        <tbody>
                        <tr>
                            <td class="tdtitle"><span>*</span>资产分类:</td>
                            <td><input name="assetClassification" type="text" value="" class="colorblur" onblur="blur(this, '资产分类不能为空')"/> </td>
                        </tr>
                        <tr>
                            <td class="tdtitle"><span>*</span>资产编码:</td>
                            <td><input name="assetsName" type="text" class="colorblur" value="" onblur="blur(this, '资产编码不能为空')"/> </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">资产分类描述:</td>
                            <td>
                                <input name="assetstypeInfo" type="text" value="" class="colorblur"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">折旧年限 (年):</td>
                            <td>
                                <input name="depreciationPeriod" value="" type="text" class="colorblur" onkeyup="value=value.match(/^-?[0-9]\d*$/)||value.match(/-?/)"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">归口管理部门 :</td>
                            <td>
                                <input name="managementDepartment" type="text" id="deptName" class="colorblur name require-red"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">总账科目 :</td>
                            <td>
                                <input name="generalLedgerAccount" type="text" class="colorblur"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">科目描述 :</td>
                            <td>
                                <input name="accountDescription" type="text" class="colorblur"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">净残值率 :</td>
                            <td>
                                <select name="netResidualRate" id="netResidualRate" class="colorblur" style="border: #cccccc 1px double;width: 70px;"></select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">是否费用类别 :</td>
                            <td>
                                <select name="assteEnable" class="colorblur" style="border: #cccccc 1px double;width: 70px;">
                                    <option value="ZAB">资本化</option>
                                    <option value="ZFB">费用化</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">数据状态:</td>
                            <td><select name="status" class="colorblur" style="border: #cccccc 1px double;width: 70px;">
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
    function netResidualRate() {
        var html = "";
        for (var i = 0; i<= 100; i++) {
            html += "<option value='" + i + "%'>" + i + "%</option>"
        }
        $("#netResidualRate").append(html);
    }
    netResidualRate();

    $(".showDeptBtn").click(function(){
        showDeptDialog();
    });

    function showDeptDialog(){
        var url = "${dynamicURL}/dept/getCodeAndName?parentWindowId=assetClassificationAdd";
        var parentWindow=dg.curWin;
        new parentWindow.$.dialog({ id: 'list', title: "归口管理部门", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }

    function fillDept(deptCode, deptName) {
        $('#deptCode').val(deptCode);
        $('#deptName').val(deptName);
    }
    function clearObj(that){
        $(that).siblings(".name").val("");
        $(that).siblings(".code").val("");
    }

    $("#btnSave").click(function () {
        if ($("input[name=assetClassification]").val() == '' || $("input[name=assetsName]").val() == '') {
            alert("存在必填项为空！请检查");
            return;
        }
        if ($("input[name=depreciationPeriod]").val() == null || $("input[name=depreciationPeriod]").val() == "") {
            alert("折旧年限 (年)应输入数字！请检查");
            return;
        }
        $("#btnSave").val("保存ing");
        $("#btnSave").attr("disabled", true);
        $.ajax({
            url : "${dynamicURL}/assetClassification/api/addAssetClassification",
            type : "post",
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify($("#assetClassificationAddForm").serializeObject()),
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