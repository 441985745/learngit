<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>资产分类表</title>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
    <link href="${staticURL}/config/css/assetClassification-list.css" type="text/css" rel="stylesheet"/>
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
                <form id="assetClassificationListForm">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif"
                                 style="cursor: pointer;" onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                        </legend>
                        <table class="adminlist">
                            <tbody>
                            <tr>
                                <td align="right" class="tdtitle">资产分类:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="assetClassification" value="${param.assetClassification }" name="assetClassification" class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">总账科目:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="generalLedgerAccount" value="${param.generalLedgerAccount }" name="generalLedgerAccount" class="colorblur">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">资产编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="assetsName" value="${param.assetsName }" name="assetsName" class="colorblur">
                                    <%--<select name="assetsName" id="areaType" class="colorblur">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="0" ${param.areaType=="0"?"selected='selected'" : ""}>家用</option>
                                        <option value="1" ${param.areaType=="1"?"selected='selected'" : ""}>商用</option>
                                    </select>--%>
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
                    <input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn" onclick="queryAssetClassification()">
                </auth:authTag>
                <auth:authTag code="Add">
                    <input type="button" name="btnAdd" value="添加" id="btnAdd" class="btn" onclick="addAssetClassification()">
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
                                <th scope="col">资产分类</th>
                                <th scope="col">资产编码</th>
                                <th scope="col">资产分类描述</th>
                                <th scope="col">折旧年限 (年)</th>
                                <th scope="col">净残值率 </th>
                                <th scope="col">归口管理部门</th>
                                <th scope="col">总账科目</th>
                                <th scope="col">科目描述</th>
                                <th scope="col">是否费用类别</th>
                                <th scope="col">状态</th>
                            </tr>
                            <c:forEach items="${assetClassificationPager.records}" var="assetClassification" varStatus="status">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input type="checkbox" name="checkboxName" class="checkbox" value="${assetClassification.id }">
                                    </td>
                                    <td>${status.index + 1}</td>
                                    <auth:authTag code="Edit">
                                        <td align="center"><a href="javascript:void(0)" onclick="updateAssetClassification('${assetClassification.id }')">修改</a></td>
                                    </auth:authTag>
                                    <auth:authTag code="View">
                                        <td align="center"><a href="javascript:void(0)" onclick="showAssetClassification('${assetClassification.id }')">查看</a></td>
                                    </auth:authTag>
                                    <td>${assetClassification.assetClassification}</td>
                                    <td>${assetClassification.assetsName}</td>
                                    <td>${assetClassification.assetstypeInfo}</td>
                                    <td class="td_right">${assetClassification.depreciationPeriod}</td>
                                    <td class="td_right">${assetClassification.netResidualRate}</td>
                                    <td>${assetClassification.managementDepartment}</td>
                                    <td>${assetClassification.generalLedgerAccount}</td>
                                    <td>${assetClassification.accountDescription}</td>
                                    <c:choose>
                                        <c:when test="${assetClassification.assteEnable == 'ZAB'}">
                                            <td>资本化</td>
                                        </c:when>
                                        <c:when test="${assetClassification.assteEnable == 'ZFB'}">
                                            <td>费用化</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${assetClassification.status == '0'}">
                                            <td>有效</td>
                                        </c:when>
                                        <c:when test="${assetClassification.status == '1'}">
                                            <td>无效</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${assetClassificationPager.offset}" total="${assetClassificationPager.total}"
                                      steps="${assetClassificationPager.pageSize}" uri="${assetClassificationPager.uri}" formId="assetClassificationListForm"/>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<jsp:include page="import_assetClassification_window.jsp"></jsp:include>
<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
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
    function queryAssetClassification() {
        window.location.href = "${dynamicURL}/assetClassification/searchAssetClassificationList?" + $('#assetClassificationListForm').serialize();
    }
    //添加
    function addAssetClassification() {
        var lurl = "${dynamicURL}/assetClassification/openDialogForAdd";
        var dlg = new $.dialog({ id: 'assetClassificationAdd', title: "资产分类添加", cover: true, page:lurl, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }
    //修改
    function updateAssetClassification(id) {
        var lurl = "${dynamicURL}/assetClassification/openDialogForUpdate?id=" + id;
        var dlg = new $.dialog({ id: 'assetClassificationUpdate', title: "资产分类修改", cover: true, page:lurl, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }
    //查看
    function showAssetClassification(id) {
        var lurl = "${dynamicURL}/assetClassification/showAssetClassification?id=" + id;
        var dlg = new $.dialog({ id: 'assetClassificationShow', title: "资产分类查看", cover: true, page:lurl, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }

    $(function () {
        //导入和模版下载窗口
        var ew = new EasyWindow();
        ew.initEasyWindow("资产分类数据导入", "${dynamicURL}/assetClassification/api/importExcel");
        $('#btnImport').click(function() {
            ew.showEasyWindow();
        });
        //导出
        $("#btnExport").click(function (){
            if (window.confirm("确认要导出吗?")) {
                window.location.href = "${dynamicURL}/assetClassification/exportExcel?"+$('#areasListForm').serialize();
            }
        });

        //删除
       $("#btnDelete").click(function () {
            var ids = new Array();
            $(".checkbox").each(function () {
                if ($(this).attr('checked')) {
                   ids.push($(this).val());
                }
            });
           if (ids.length < 1) {
               alert("至少选择一条数据！");
               return;
           }
           if (window.confirm("确认删除？")) {
               $.ajax({
                   url : "${dynamicURL}/assetClassification/api/deleteAssetClassification",
                   type : "post",
                   data : {"ids" : ids},
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