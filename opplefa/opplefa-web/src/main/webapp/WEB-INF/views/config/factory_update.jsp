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
                <input type="button" name="btnSave" value="保存" onclick="" id="btnSave" class="btn">
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
                                <td><input name="cfactoryCode" type="text"  value="${factory.cfactoryCode}" class="colorblur require-red" readonly/> </td>
                            </tr>
                            <tr>
                                <td class="tdtitle"><span>*</span>工厂名称:</td>
                                <td><input name="cfactoryName" type="text" value="${factory.cfactoryName}" class="colorblur require-red"/></td>
                            </tr>

                            <tr>
                                <td class="tdtitle"><span>*</span>公司名称:</td>
                                <td>
                                    <input name="ccompanyCode" type="hidden" class="companyCode code" value="${factory.ccompanyCode}" id="companyCode"/>
                                    <input name="ccompanyName" class="colorblur name require-red" value="${factory.ccompanyName}" id="companyName" readonly style="width: 162px;"/><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showCompanyListBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;">
                                </td>
                            </tr>

                            <tr>
                                <td class="tdtitle">数据状态 :</td>
                                <td>
                                    <select index="1" name="cstatus" id="txtCstatus" style="border: #cccccc 1px double;width: 60px;">
                                        <option value="Y" ${factory.cstatus=="Y"?"selected='selected'" : ""}>有效</option>
                                        <option value="N" ${factory.cstatus=="N"?"selected='selected'" : ""}>无效</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle" style="padding-top: 8px;" valign="top">备注 :</td>
                                <td>
                                    <textarea cols="50" rows="3" style="border: #cccccc 1px double;" name="cmemo"
                                              id="cmemo" class="code">${factory.cmemo}</textarea>
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
    }

    /*选择员工*/
    <%--function showUserDialog(mark){--%>
        <%--var url = "${dynamicURL}/user/getUserListInAttachConfig?parentWindowId=updateWorkAddress&mark=" + mark;--%>
        <%--var parentWindow=dg.curWin;--%>
        <%--new parentWindow.$.dialog({ id: 'list', title: "所有人员", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();--%>

    <%--}--%>
    /*选择员工回调*/
//    function fillVehicleUser(userCode, userName, mark) {
//        if (mark == "receivegoodsName") {
//            $('#receivegoodsCode').val(userCode);
//            $('#receivegoodsName').val(userName);
//        } else if (mark == "retiredgoodsName") {
//            $('#retiredgoodsCode').val(userCode);
//            $('#retiredgoodsName').val(userName);
//        } else if (mark == "purchasetipaccessName") {
//            $('#purchasetipaccessCode').val(userCode);
//            $('#purchasetipaccessName').val(userName);
//        } else if (mark == "purchasebidName") {
//            $('#purchasebidCode').val(userCode);
//            $('#purchasebidName').val(userName);
//        } else if (mark == "payeeName") {
//            $('#payeeCode').val(userCode);
//            $('#payeeName').val(userName);
//        }
//    }

    //添加公司名称和编码
    $(".showCompanyListBtn").click(function(){
        showCompanyDialog();
    });

    function showCompanyDialog(){
        var url = "${dynamicURL}/company/searchCompanies?parentWindowId=updateStandardComputerConfig";
        var parentWindow=dg.curWin;
        new parentWindow.$.dialog({ id: 'list', title: "所有公司", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }

    function fillCompany(companyCode, companyName) {
        $('#companyCode').val(companyCode);
        $('#companyName').val(companyName);
    }

    /*清空员工信息*/
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
    function update(that, url){
        $(that).attr("disabled","disabled");
        $(that).val("保存ing");

        if(checkRequire()) {
            $.ajax({
                cache: true,
                type: "POST",
                url: url,
                data: $("#updateConfigForm").serialize(),
                async: false,
                error: function () {
                    alert("抱歉，此次修改未成功");
                },
                success: function (data) {
                    if ("修改成功" == data.successMessage) {
                        alert("修改成功");
                        dg.curWin.location.reload();
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
        /*选择员工*/
        $(".showUserListBtn").click(function(){
            var mark = $(this).siblings(".mark").val();
            showUserDialog(mark);
        });

        /*保存*/
        $("#btnSave").click(function () {//保存
            update(this, '${dynamicURL}/config/factory/update');
        });
    });

</script>
</body>


</html>
