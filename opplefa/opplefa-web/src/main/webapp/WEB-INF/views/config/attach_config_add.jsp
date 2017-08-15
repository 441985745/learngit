<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <title>归口/负责人配置添加</title>
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
                    <form id = "attachConfigAddForm">
                    <table border="0" cellspacing="0" cellpadding="0" id="format">
                        <tbody>
                        <tr>
                            <td class="tdtitle"><span>*</span>公司:</td>
                            <td>
                                <input name="companyCode" type="hidden" class="companyCode code"  id="companyCode"/>
                                <input name="companyName" class="colorblur name" id="companyName" readonly style="width: 160px;"/><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showCompanyListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;">
                            </td>
                            <td class="tdtitle"><span>*</span>成本中心:</td>
                            <td>
                                <input name="costcenterCode" type="hidden" id="costcenterCode" class="costcenterCode code" />
                                <input name="costcenterName" id="costcenterName" class="colorblur name" readonly style="width: 160px;"/><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showCostCenterBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;">
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle"><span>*</span>资产类型:</td>
                            <td>
                                <select class="colorblur" name="assetType" style="width: 202px;" >
                                    <option>IT资产</option>
                                    <option >行政资产</option>
                                    <option >计量器具</option>
                                    <option >机器设备</option>
                                </select>
                            </td>
                            <td class="tdtitle">车间类型:</td>
                            <td>
                                <input name="workShopType" type="text" class="colorblur" style="width: 198px" />
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">归口部门管理员:</td>
                            <td>
                                <input type="hidden" value="attachDepartAdminName" class="mark"/>
                                <input type="hidden" name="attachDepartAdminCode" id="attachDepartAdminCode" class="code"/>
                                <input type="text" name="attachDepartAdminName" id="attachDepartAdminName" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                            <td class="tdtitle">归口部门经理 :</td>
                            <td>
                                <input type="hidden" value="attachDepartManagerName" class="mark"/>
                                <input type="hidden" name="attachDepartManagerCode" id="attachDepartManagerCode" class="code"/>
                                <input type="text" name="attachDepartManagerName" id="attachDepartManagerName" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">归口部门总监:</td>
                            <td>
                                <input type="hidden" value="attachDepartMajordomoName" class="mark"/>
                                <input type="hidden" name="attachDepartMajordomoCode" id="attachDepartMajordomoCode" class="code"/>
                                <input type="text" name="attachDepartMajordomoName" id="attachDepartMajordomoName" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                            <td class="tdtitle">归口部门副总裁 :</td>
                            <td>
                                <input type="hidden" value="attachDepartVpresidentName" class="mark"/>
                                <input type="hidden" name="attachDepartVpresidentCode" id="attachDepartVpresidentCode" class="code"/>
                                <input type="text" name="attachDepartVpresidentName" id="attachDepartVpresidentName" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">采购员名称1 :</td>
                            <td>
                                <input type="hidden" value="buyerNamea" class="mark"/>
                                <input type="hidden" name="buyerCodea" id="buyerCodea" class="code"/>
                                <input type="text" name="buyerNamea" id="buyerNamea" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                            <td class="tdtitle">采购员名称2 :</td>
                            <td>
                                <input type="hidden" value="buyerNameb" class="mark"/>
                                <input type="hidden" name="buyerCodeb" id="buyerCodeb" class="code"/>
                                <input type="text" name="buyerNameb" id="buyerNameb" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">采购员名称3 :</td>
                            <td>
                                <input type="hidden" value="buyerNamec" class="mark"/>
                                <input type="hidden" name="buyerCodec" id="buyerCodec" class="code"/>
                                <input type="text" name="buyerNamec" id="buyerNamec" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                            <td class="tdtitle">采购员名称4 :</td>
                            <td>
                                <input type="hidden" value="buyerNamed" class="mark"/>
                                <input type="hidden" name="buyerCoded" id="buyerCoded" class="code"/>
                                <input type="text" name="buyerNamed" id="buyerNamed" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">采购员名称5 :</td>
                            <td>
                                <input type="hidden" value="buyerNamee" class="mark"/>
                                <input type="hidden" name="buyerCodee" id="buyerCodee" class="code"/>
                                <input type="text" name="buyerNamee" id="buyerNamee" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                            <td class="tdtitle">采购员名称6 :</td>
                            <td>
                                <input type="hidden" value="buyerNamef" class="mark"/>
                                <input type="hidden" name="buyerCodef" id="buyerCodef" class="code"/>
                                <input type="text" name="buyerNamef" id="buyerNamef" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">采购员名称7 :</td>
                            <td>
                                <input type="hidden" value="buyerNameg" class="mark"/>
                                <input type="hidden" name="buyerCodeg" id="buyerCodeg" class="code"/>
                                <input type="text" name="buyerNameg" id="buyerNameg" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                            <td class="tdtitle">采购员名称8 :</td>
                            <td>
                                <input type="hidden" value="buyerNameh" class="mark"/>
                                <input type="hidden" name="buyerCodeh" id="buyerCodeh" class="code"/>
                                <input type="text" name="buyerNameh" id="buyerNameh" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">采购经理 :</td>
                            <td>
                                <input type="hidden" value="purchasingManagerName" class="mark"/>
                                <input type="hidden" name="purchasingManagerCode" id="purchasingManagerCode" class="code"/>
                                <input type="text" name="purchasingManagerName" id="purchasingManagerName" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                            <td class="tdtitle">二级部门负责人 :</td>
                            <td>
                                <input type="hidden" value="twoDepartmentHeadName" class="mark"/>
                                <input type="hidden" name="twoDepartmentHeadCode" id="twoDepartmentHeadCode" class="code"/>
                                <input type="text" name="twoDepartmentHeadName" id="twoDepartmentHeadName" class="colorblur name" readonly="readonly" style="width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showUserListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">平台:</td>
                            <td><input name="platform" id="platform" type="text" value="" class="colorblur" style="width: 198px"/></td>
                            <td class="tdtitle">数据状态:</td>
                            <td>
                                <select class="colorblur" name="status" style="width: 202px">
                                    <option value="0">有效</option>
                                    <option value="1">无效</option>
                                </select>
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

    function blur(that, content) {
        if ($(that).val() == "") {
            alert(content);
            $(that).focus();
        }
    }

    $(".showCompanyListBtn").click(function(){
        showCompanyDialog();
    });

    function showCompanyDialog(){
        var url = "${dynamicURL}/company/searchCompanies?parentWindowId=attachConfigAdd";
        var parentWindow=dg.curWin;
        new parentWindow.$.dialog({
            id: 'list',
            title: "所有公司",
            cover: true,
            page: url,
            width: 800,
            height: 400,
            btnBar: false,
            resize: false,
            maxBtn: false
        }).ShowDialog();
    }

    function fillCompany(companyCode, companyName) {
        $('#companyCode').val(companyCode);
        $('#companyName').val(companyName);
    }

    $(".showCostCenterBtn").click(function(){
        var companyCode = $("#companyCode").val();
        if(companyCode != null && companyCode != ""){
            showCostCenterDialog(companyCode);
        }else {
            alert("请先选择公司！");
        }
    });

    function showCostCenterDialog(companyCode){
        var url = "${dynamicURL}/costCenter/getCodeAndName?parentWindowId=attachConfigAdd&companyCode=" + companyCode;
        var parentWindow=dg.curWin;
        new parentWindow.$.dialog({
            id: 'list',
            title: "成本中心",
            cover: true,
            page: url,
            width: 800,
            height: 400,
            btnBar: false,
            resize: false,
            maxBtn: false
        }).ShowDialog();

    }

    function fillCostCenter(costcenterCode, costcenterName) {
        $('#costcenterCode').val(costcenterCode);
        $('#costcenterName').val(costcenterName);
        $.ajax({
            url : "${dynamicURL}/attchConfig/getPlatform",
            type : "post",
            dataType:"json",
            data:{"costCenterCode":costcenterCode},
//            contentType : "charset=utf-8",
            success : function (data) {
                alert(JSON.stringify(data));
                $("#platform").val(data);

            }

        });
    }
    <%--fillPlatform(costcenterCode){--%>
        <%--$.ajax({--%>
            <%--url : "${dynamicURL}/attchConfig/api/getPlatform?costcenterCode=" + costcenterCode,--%>
            <%--type : "post",--%>
            <%--contentType : "charset=utf-8",--%>
            <%--success : function (data) {--%>
                <%--if (data.success) {--%>
                    <%--$("#platform").val(data);--%>
                <%--} else {--%>
                    <%--alert(data.error);--%>
                <%--}--%>
            <%--}--%>

        <%--});--%>
    <%--}--%>

    $(".showUserListBtn").click(function(){
        var mark = $(this).siblings(".mark").val();
        showUserDialog(mark);
    });
    function showUserDialog(mark){
        var url = "${dynamicURL}/user/getUserListInAttachConfig?parentWindowId=attachConfigAdd&mark=" + mark;
        var parentWindow=dg.curWin;
        new parentWindow.$.dialog({
            id: 'list',
            title: "所有人员",
            cover: true,
            page: url,
            width: 800,
            height: 400,
            btnBar: false,
            resize: false,
            maxBtn: false
        }).ShowDialog();

    }
    function fillVehicleUser(userCode, userName, mark) {
        if (mark == "attachDepartAdminName") {
            $('#attachDepartAdminCode').val(userCode);
            $('#attachDepartAdminName').val(userName);
        } else if (mark == "attachDepartManagerName") {
            $('#attachDepartManagerCode').val(userCode);
            $('#attachDepartManagerName').val(userName);
        } else if (mark == "attachDepartMajordomoName") {
            $('#attachDepartMajordomoCode').val(userCode);
            $('#attachDepartMajordomoName').val(userName);
        } else if (mark == "attachDepartVpresidentName") {
            $('#attachDepartVpresidentCode').val(userCode);
            $('#attachDepartVpresidentName').val(userName);
        } else if (mark == "buyerNamea") {
            $('#buyerCodea').val(userCode);
            $('#buyerNamea').val(userName);
        } else if (mark == "buyerNameb") {
            $('#buyerCodeb').val(userCode);
            $('#buyerNameb').val(userName);
        } else if (mark == "buyerNamec") {
            $('#buyerCodec').val(userCode);
            $('#buyerNamec').val(userName);
        } else if (mark == "buyerNamed") {
            $('#buyerCoded').val(userCode);
            $('#buyerNamed').val(userName);
        } else if (mark == "buyerNamee") {
            $('#buyerCodee').val(userCode);
            $('#buyerNamee').val(userName);
        } else if (mark == "buyerNamef") {
            $('#buyerCodef').val(userCode);
            $('#buyerNamef').val(userName);
        } else if (mark == "buyerNameg") {
            $('#buyerCodeg').val(userCode);
            $('#buyerNameg').val(userName);
        } else if (mark == "buyerNameh") {
            $('#buyerCodeh').val(userCode);
            $('#buyerNameh').val(userName);
        } else if (mark == "purchasingManagerName") {
            $('#purchasingManagerCode').val(userCode);
            $('#purchasingManagerName').val(userName);
        } else if (mark == "twoDepartmentHeadName") {
            $('#twoDepartmentHeadCode').val(userCode);
            $('#twoDepartmentHeadName').val(userName);
        }

    }
    function clearObj(that){
        $(that).siblings(".name").val("");
        $(that).siblings(".code").val("");
    }

    $("#btnSave").click(function () {
        if ($("input[name=companyName]").val() == '' || $("input[name=costcenterName]").val() == '' || $("select[name=assetType]").val() == '') {
            alert("存在必填项为空！请检查");
            return;
        }
        $("#btnSave").val("保存ing");
        $("#btnSave").attr("disabled", true);
        $.ajax({
            url : "${dynamicURL}/attchConfig/api/addAttachConfig",
            type : "post",
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify($("#attachConfigAddForm").serializeObject()),
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