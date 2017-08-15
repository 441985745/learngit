<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <title>预算体-成本中心配置</title>
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
                    <form id = "updateForm">
                        <table border="0" cellspacing="0" cellpadding="0" id="format">
                            <tbody>
                            <tr>
                                <td class="tdtitle"><span>*</span>所属公司:</td>
                                <td>
                                    <input name="id" type="hidden" id="id" value="${deptCostCenter.id}">
                                    <input name="companyCode" type="hidden" class="companyCode code"  id="companyCode"/>
                                    <input name="companyName" class="colorblur name require-red" value="${deptCostCenter.companyName}" id="companyName" readonly style="width: 160px;"/><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showCompanyListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;">
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle"><span>*</span>预算体名称:</td>
                                <td>
                                    <input name="deptCode" type="hidden" id="deptCode" class="deptCode code"/>
                                    <input name="deptName" type="text" id="deptName" value="${deptCostCenter.deptName}" class="colorblur name require-red" style="width: 160px;"/><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showDeptBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;">
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle"><span>*</span>成本中心名称:</td>
                                <td>
                                    <input name="costCenterCode" type="hidden" id="costCenterCode" class="costCenterCode code" value="${deptCostCenter.deptCode}"/>
                                    <input name="costCenterName" id="costCenterName" value="${deptCostCenter.costCenterName}" class="colorblur name require-red" readonly style="width: 160px;"/><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showCostCenterBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;">
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle">数据状态 :</td>
                                <td>
                                    <select index="1" name="status" id="txtStatus" style="width: 60px;" class="colorblur">
                                        <option value="Y" ${deptCostCenter.status=="Y"?"selected='selected'" : ""}>有效</option>
                                        <option value="N" ${deptCostCenter.status=="N"?"selected='selected'" : ""}>无效</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle" style="padding-top: 8px;" valign="top">备注 :</td>
                                <td>
                                    <textarea cols="50" rows="3" name="memo" id="memo" class="colorblur code">${deptCostCenter.memo}</textarea>
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
    //解决ie报错 对象没有trim属性或方法
    String.prototype.trim = function () {
        return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
    };

    $(".showCompanyListBtn").click(function(){
        showCompanyDialog();
    });

    function showCompanyDialog(){
        var url = "${dynamicURL}/company/searchCompanies?parentWindowId=updatePosition";
        var parentWindow=dg.curWin;
        new parentWindow.$.dialog({ id: 'list', title: "所有公司", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }

    function fillCompany(companyCode, companyName) {
        $('#companyCode').val(companyCode);
        $('#companyName').val(companyName);
    }
    $(".showDeptBtn").click(function(){
        showDeptDialog();
    });

    function showDeptDialog(){
        var url = "${dynamicURL}/dept/getCodeAndName?parentWindowId=updatePosition";
        var parentWindow=dg.curWin;
        new parentWindow.$.dialog({ id: 'list', title: "所有公司", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }

    function fillDept(deptCode, deptName) {
        $('#deptCode').val(deptCode);
        $('#deptName').val(deptName);
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
        var url = "${dynamicURL}/costCenter/getCodeAndName?parentWindowId=updatePosition&companyCode=" + companyCode;
        var parentWindow=dg.curWin;
        new parentWindow.$.dialog({ id: 'list', title: "成本中心", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();

    }

    function fillCostCenter(costcenterCode, costcenterName) {
        $('#costCenterCode').val(costcenterCode);
        $('#costCenterName').val(costcenterName);
    }
    //去除所选
    function clearObj(that){
        $(that).siblings(".name").val("");
        $(that).siblings(".code").val("");
    }

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
    /*保存*/
    function update(dg, that, url){
        $(that).attr("disabled","disabled");
        $(that).val("保存ing");
        if(checkRequire()) {
            $.ajax({
                type: "POST",
                url: url,

                data: JSON.stringify($("#updateForm").serializeObject()),
                contentType : "application/json; charset=utf-8",
                async: false,
                error: function () {
                    alert("抱歉，此次修改未成功");
                },
                success: function (data) {
                    if ("修改成功" == data.successMessage) {
                        alert("修改成功");
                    }
                    else {
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
        /*保存*/
        $("#btnSave").click(function () {//保存
            update(dg, this, '${dynamicURL}/config/deptCostCenter/update');
            dg.curWin.location.reload();
        });
    });

</script>
</body>
</html>
