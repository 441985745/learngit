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
                <input type="button" name="btnSave" value="保存" id="btnSave" class="btn">
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
                                <td>
                                    <input type="hidden" name="id" value="${budgetAssetstypeConfig.id}">
                                    <input name="budgetAssetsType" type="text"
                                           value="${budgetAssetstypeConfig.budgetAssetsType }"
                                           class="colorblur require-red"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle"><span>*</span>资产类型:</td>
                                <td>
                                    <select class="colorblur require-red" id="assetsType"  name="assetsType" style="width: 204px;" >
                                        <option ${budgetAssetstypeConfig.assetsType=='IT资产' ? "selected" : ""} value="IT资产">IT资产</option>
                                        <option ${budgetAssetstypeConfig.assetsType=='行政资产' ? "selected" : ""} value="行政资产">行政资产</option>
                                        <option ${budgetAssetstypeConfig.assetsType=='计量器具' ? "selected" : ""} value="计量器具">计量器具</option>
                                        <option ${budgetAssetstypeConfig.assetsType=='机器设备' ? "selected" : ""} value="机器设备">机器设备</option>
                                    </select>
                                </td>

                            </tr>
                            <tr>
                                <td class="tdtitle">数据状态 :</td>
                                <td>
                                    <select index="1" name="status" id="txtStatus" style="border: #cccccc 1px double;width: 60px;">
                                        <option value="Y" ${budgetAssetstypeConfig.status=="Y"?"selected='selected'" : ""}>有效</option>
                                        <option value="N" ${budgetAssetstypeConfig.status=="N"?"selected='selected'" : ""}>无效</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle" style="padding-top: 8px;" valign="top">备注 :</td>
                                <td>
                                    <textarea cols="50" rows="3" name="memo" style="border: #cccccc 1px double;" id="memo" class="code">${budgetAssetstypeConfig.memo}</textarea>
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
    }

    /*选择员工*/
    <%--function showUserDialog(mark){--%>
        <%--var url = "${dynamicURL}/user/getUserListInAttachConfig?parentWindowId=updateWorkAddress&mark=" + mark;--%>
        <%--var parentWindow=dg.curWin;--%>
        <%--new parentWindow.$.dialog({ id: 'list', title: "所有人员", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();--%>

    <%--}--%>
//    /*选择员工回调*/
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

//
//    $(function () {
//        /*选择员工*/
//        $(".showUserListBtn").click(function(){
//            var mark = $(this).siblings(".mark").val();
//            showUserDialog(mark);
//        });
//
//        /*保存*/
//
//    });


    $("#btnSave").click(function () {//保存
        update(this, '${dynamicURL}/config/budgetAssetstypeConfig/update');
        if(checkRequire()){
            dg.curWin.location.reload();
        }
    });

</script>
</body>
</html>
