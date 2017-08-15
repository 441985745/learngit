<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
	<title>存放位置配置</title>
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
					<form id="updatePositionForm">
						<table border="0" cellspacing="0" cellpadding="0" style="width: 100%; height: 98%;" id="format">
							<tbody>
							<tr>
								<td class="tdtitle"><span>*</span>存放位置编码:</td>
								<td><input name="cpositionCode" type="text" value="${position.cpositionCode}" class="colorblur require-red" id="txtAssetsCode" readonly/> </td>
							</tr>
							<tr>
								<td class="tdtitle"><span>*</span>存放位置名称:</td>
								<td><input name="cposition" id="txtAssetsName" type="text" value="${position.cposition}" class="colorblur require-red"/></td>
							</tr>

							<tr>
								<td class="tdtitle"><span>*</span>办公地点名称:</td>
								<td>
									<input type="hidden" value="receivegoodsName" class="mark"/>
									<input type="hidden" name="workAddressCode" value="${position.workAddressCode}" id="workAddressCode" class="code"/>
									<input type="text" name="workAddress" value="${position.workAddress}" id="workAddress" class="td_input name require-red" readonly="readonly" style="border: #cccccc 1px double;width: 160px;"><input class='btnRefView' title='点击清空参照文本框内容' type='button' value='X' style="width: 18px;" onclick="clearObj(this);"><input class='btnRefView showLocationBtn' title='点击打开参照文本框内容' type='button' value='…' style="width: 20px;"></td>
								</td>
							</tr>
							<tr>
								<td class="tdtitle">数据状态 :</td>
								<td>
									<select index="1" name="cstatus" id="txtCstatus" style="border: #cccccc 1px double;width: 60px;">
										<option value="Y" ${position.cstatus=="Y"?"selected='selected'" : ""}>有效</option>
										<option value="N" ${position.cstatus=="N"?"selected='selected'" : ""}>无效</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tdtitle" style="padding-top: 8px;" valign="top">备注 :</td>
								<td>
									<textarea cols="50" rows="3" name="cmemo" id="cmemo" class="code" style="border: #cccccc 1px double;">${position.cmemo}</textarea>
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

</body>
<script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/config/crud.js" type="text/javascript"></script>
<!-- 改动 8 e -->
<script type="text/javascript">
    var dg = frameElement.lhgDG;//定义dg 关闭需要


    $(".showLocationBtn").click(function(){
        //选择办公地点

        //var mark = $(this).siblings(".workAddress").val();
        showWorkAdressDialog();

    });

    function showWorkAdressDialog(){
        var url = "${dynamicURL}/config/workAddress/getWorkAddressCode?parentWindowId=updatePosition";
        var parentWindow=dg.curWin;
        new parentWindow.$.dialog({ id: 'list', title: "选择地点", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();

    }

    function fillVehicleUser(workAdressCode, workAdresse) {
        $('#workAddressCode').val(workAdressCode);
        $('#workAddress').val(workAdresse);

    };

    function checkRequire() {
        var flag = true;
        $(".require-red").each(function(){
            if($(this).val().trim() == ""){
                flag = false;
            }
        });
        return flag;
    }

    function clearObj(that){
        $(that).siblings("#workAddress").val("");
        //$(that).parent().prev().prev().child("#workAddressCode").val("");
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
                data: $("#updatePositionForm").serialize(),
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

    $("#btnSave").click(function(){//保存
        update(this,'${dynamicURL}/config/position/update');
        if(checkRequire()){
            dg.curWin.location.reload();
		}

    });

</script>

</html>
