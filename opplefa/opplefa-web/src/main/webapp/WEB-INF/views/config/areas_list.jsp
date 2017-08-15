<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <title>区域配置表</title>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">

    <style type="text/css">
        #page-selection ul li {
            display: inline;
        }
    </style>
</head>
<body>
<div>
    <input type="hidden" name="hfQueryStatus" id="hfQueryStatus" value="show">
    <table cellspacing="0" cellpadding="0" style="width: 100%; height: 98%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form id="areasListForm">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif"
                                 style="cursor: pointer;" onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                        </legend>
                        <table class="adminlist">
                            <tbody>
                            <tr>
                                <td align="right" class="tdtitle">区域编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="areaCode" value="${param.areaCode }" name="areaCode" class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">区域名称:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="areaName" value="${param.areaName }" name="areaName" class="colorblur">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">区域类型:
                                </td>
                                <td class="tdtext">
                                    <select name="areaType" id="areaType" class="colorblur">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="0" ${param.areaType=="0"?"selected='selected'" : ""}>家用</option>
                                        <option value="1" ${param.areaType=="1"?"selected='selected'" : ""}>商用</option>
                                    </select>
                                </td>
                                  <td align="right" class="tdtitle">数据状态:</td>
                                <td class="tdtext">
                                    <select name="status" id="status" class="colorblur">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="0" ${param.status=="0"?"selected='selected'" : ""}>有效</option>
                                        <option value="1" ${param.status=="1"?"selected='selected'" : ""}>无效</option>
                                    </select>
                                </td>
                            </tr>
                           <%-- <tr>
                                <td align="right" class="tdtitle">维护人:
                                </td>
                                <td class="tdtext">
                                    <input name="updateBy" type="text" id="updateBy" value="${param.updateBy }"
                                           class="colorblur" onblur="this.className='colorblur';">
                                </td>
                                <td align="right" class="tdtitle">维护时间:
                                </td>
                                <td class="tdtext">
                                    <input name="updateDateStart" value="${param.updateDateStart }" type="text"
                                           id="updateDateStart" class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                    -<input name="applyDateEnd" value="${param.applyDateEnd }" type="text"
                                            id="applyDateEnd" class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                </td>
                            </tr>--%>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </td>
        </tr>
        <tr id="Button">
            <td class="buttonbar">
                &nbsp;&nbsp;
                <auth:authTag code="Query">
                    <input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn" onclick="queryArea()">
                </auth:authTag>
                <auth:authTag code="Add">
                    <input type="button" name="btnAdd" value="添加" id="btnAdd" class="btn" onclick="addArea()">
                </auth:authTag>
                <auth:authTag code="Delete">
                    <input type="button" name="btnDelete" value="删除" id="btnDelete" class="btn">
                </auth:authTag>
                <auth:authTag code="Import">
                    <input type="button" name="btnImport" value="导入" id="btnImport" class="btn">
                </auth:authTag>
                <auth:authTag code="Export">
                    <input type="button" name="btnExport" value="导出" id="btnExport" class="btn">
                </auth:authTag>
            </td>
        </tr>
        <tr>
            <td valign="top">
                <div id="gridview" >
                    <div>
                        <table class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border-collapse: collapse;">
                            <tbody>
                            <tr style="white-space: nowrap;">
                                <th scope="col">
                                    <input type="checkbox" name="" onclick="javascript:SelectAllCheckboxesByTableId(this, 'gvDataList');"/>
                                </th>
                                <th scope="col">序号</th>
                                <auth:authTag code="Edit">
                                    <th scope="col">操作</th>
                                </auth:authTag>
                                <auth:authTag code="View">
                                    <th scope="col">操作</th>
                                </auth:authTag>
                                <th scope="col">区域编码</th>
                                <th scope="col">区域名称</th>
                                <th scope="col">区域类型</th>
                                <th scope="col">数据状态</th>
                                <th scope="col">创建人</th>
                                <th scope="col">创建时间</th>
                                <th scope="col">最后维护人</th>
                                <th scope="col">最后维护时间</th>
                            </tr>
                            <c:forEach items="${areasPager.records}" var="area" varStatus="status">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input type="checkbox" name="checkboxName" class="checkbox" value="${area.areaCode }">
                                    </td>
                                    <td>${status.index + 1}</td>
                                    <auth:authTag code="Edit">
                                        <td align="center"><a href="javascript:void(0)" onclick="updateArea('${area.areaCode }')">修改</a></td>
                                    </auth:authTag>
                                    <auth:authTag code="View">
                                        <td align="center"><a href="javascript:void(0)" onclick="showArea('${area.areaCode }')">查看</a></td>
                                    </auth:authTag>
                                    <td>${area.areaCode}</td>
                                    <td>${area.areaName}</td>
                                    <td>${area.areaType == '0' ? "家居" : "商用" }</td>
                                    <td>${area.status == '0' ? "有效" : "无效"}</td>
                                    <td>${area.createUserName}</td>
                                    <td><fmt:formatDate value="${area.createDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${area.updateUserName}</td>
                                    <td><fmt:formatDate value="${area.updateDate}" pattern="yyyy-MM-dd"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${areasPager.offset}" total="${areasPager.total}"
                                      steps="${areasPager.pageSize}" uri="${areasPager.uri}" formId="areasListForm"/>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
    <jsp:include page="import_area_window.jsp"></jsp:include>
<script src="${staticURL}/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">

    GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
    function autoSize() {
        $("#gridview").width($(window).width());
        $("#gridview").height(
            $(window).height() - $("#Query").height()
            - $("#Button").height()
            - $("#AspNetPager").height() - 1);
    }
    autoSize();
    window.onresize = autoSize;

    /*查询*/
    function queryArea() {
        window.location.href = "${dynamicURL}/area/searchAreasList?" + $('#areasListForm').serialize();
    }
    //添加
    function addArea() {
        var lurl = "${dynamicURL}/area/openDialogForAdd";
        var dlg = new $.dialog({ id: 'areaAdd', title: "区域添加", cover: true, page:lurl, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }
    //修改
    function updateArea(areaCode) {
        var lurl = "${dynamicURL}/area/openDialogForUpdate?areaCode=" + areaCode;
        var dlg = new $.dialog({ id: 'areaUpdate', title: "区域修改", cover: true, page:lurl, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }
    //查看
    function showArea(areaCode) {
        var lurl = "${dynamicURL}/area/showArea?areaCode=" + areaCode;
        var dlg = new $.dialog({ id: 'areaShow', title: "区域查看", cover: true, page:lurl, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }

    $(function () {
        //导入和模版下载窗口
        var ew = new EasyWindow();
        ew.initEasyWindow("区域数据导入", "${dynamicURL}/area/api/importExcel");
        $('#btnImport').click(function() {
            ew.showEasyWindow();
        });
        //导出
        $("#btnExport").click(function (){
            if (window.confirm("确认要导出吗?")) {
                window.location.href = "${dynamicURL}/area/exportExcel?"+$('#areasListForm').serialize();
            }
        });

        //删除
       $("#btnDelete").click(function () {
            var areaCodes = new Array();
            $(".checkbox").each(function () {
                if ($(this).attr('checked')) {
                   areaCodes.push($(this).val());
                }
            });
           if (areaCodes.length < 1) {
               alert("至少选择一条数据！");
               return;
           }
           if (window.confirm("确认删除？")) {
               $.ajax({
                   url : "${dynamicURL}/area/api/deleteAreas",
                   type : "post",
                   data : {"areaCodes" : areaCodes},
                   success : function (data) {
                       if (data.success) {
                           alert("删除成功！");
                       } else {
                           alert(data.error);
                       }
                       window.location.reload();
                   }
               })
           }
       });
    });


</script>
</body>
</html>