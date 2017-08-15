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
    <title>归口/负责人配置表</title>
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
                <form id="attachConfigListForm">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif"
                                 style="cursor: pointer;" onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                        </legend>
                        <table class="adminlist">
                            <tbody>
                            <tr>
                                <td align="right" class="tdtitle">公司编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="companyCode" value="${param.companyCode }" name="companyCode" class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">成本中心编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="costcenterCode" value="${param.costcenterCode }" name="costcenterCode" class="colorblur">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">资产类型:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="assetType" value="${param.assetType }" name="assetType" class="colorblur"/>
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
                    <input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn" onclick="queryAttachConfig()">
                </auth:authTag>
                <auth:authTag code="Add">
                    <input type="button" name="btnAdd" value="添加" id="btnAdd" class="btn" onclick="addAttachConfig()">
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
                                <th scope="col">公司编码</th>
                                <th scope="col">公司名称</th>
                                <th scope="col">成本中心编码</th>
                                <th scope="col">成本中心</th>
                                <th scope="col">平台</th>
                                <th scope="col">资产类型</th>
                                <th scope="col">车间类型</th>
                                <th scope="col">归口部门管理员编码</th>
                                <th scope="col">归口部门管理员</th>
                                <th scope="col">归口部门经理编码</th>
                                <th scope="col">归口部门经理</th>
                                <th scope="col">归口部门总监编码</th>
                                <th scope="col">归口部门总监</th>
                                <th scope="col">归口部门副总裁编码</th>
                                <th scope="col">归口部门副总裁</th>
                                <th scope="col">采购员编码1</th>
                                <th scope="col">采购员名称1</th>
                                <th scope="col">采购员编码2</th>
                                <th scope="col">采购员名称2</th>
                                <th scope="col">采购员编码3</th>
                                <th scope="col">采购员名称3</th>
                                <th scope="col">采购员编码4</th>
                                <th scope="col">采购员名称4</th>
                                <th scope="col">采购员编码5</th>
                                <th scope="col">采购员名称5</th>
                                <th scope="col">采购员编码6</th>
                                <th scope="col">采购员名称6</th>
                                <th scope="col">采购员编码7</th>
                                <th scope="col">采购员名称7</th>
                                <th scope="col">采购员编码8</th>
                                <th scope="col">采购员名称8</th>
                                <th scope="col">采购经理编码</th>
                                <th scope="col">采购经理</th>
                                <th scope="col">二级部门负责人编码</th>
                                <th scope="col">二级部门负责人</th>
                                <th scope="col">创建人</th>
                                <th scope="col">创建时间</th>
                                <th scope="col">最后更新人</th>
                                <th scope="col">最后更新时间</th>
                                <th scope="col">数据状态</th>
                            </tr>
                            <c:forEach items="${attachConfigPager.records}" var="attachConfig" varStatus="status">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input type="checkbox" name="checkboxName" class="checkbox" value="${attachConfig.id }">
                                    </td>
                                    <td>${status.index + 1}</td>
                                    <auth:authTag code="Edit">
                                        <td align="center"><a href="javascript:void(0)" onclick="updateAttachConfig('${attachConfig.id }')">修改</a></td>
                                    </auth:authTag>
                                    <auth:authTag code="View">
                                        <td align="center"><a href="javascript:void(0)" onclick="showAttachConfig('${attachConfig.id }')">查看</a></td>
                                    </auth:authTag>
                                    <td>${attachConfig.companyCode}</td>
                                    <td>${attachConfig.companyName}</td>
                                    <td>${attachConfig.costcenterCode}</td>
                                    <td>${attachConfig.costcenterName}</td>
                                    <td>${attachConfig.platform}</td>
                                    <td>${attachConfig.assetType}</td>
                                    <td>${attachConfig.workShopType}</td>
                                    <td>${attachConfig.attachDepartAdminCode}</td>
                                    <td>${attachConfig.attachDepartAdminName}</td>
                                    <td>${attachConfig.attachDepartManagerCode}</td>
                                    <td>${attachConfig.attachDepartManagerName}</td>
                                    <td>${attachConfig.attachDepartMajordomoCode}</td>
                                    <td>${attachConfig.attachDepartMajordomoName}</td>
                                    <td>${attachConfig.attachDepartVpresidentCode}</td>
                                    <td>${attachConfig.attachDepartVpresidentName}</td>
                                    <td>${attachConfig.buyerCodea}</td>
                                    <td>${attachConfig.buyerNamea}</td>
                                    <td>${attachConfig.buyerCodeb}</td>
                                    <td>${attachConfig.buyerNameb}</td>
                                    <td>${attachConfig.buyerCodec}</td>
                                    <td>${attachConfig.buyerNamec}</td>
                                    <td>${attachConfig.buyerCoded}</td>
                                    <td>${attachConfig.buyerNamed}</td>
                                    <td>${attachConfig.buyerCodee}</td>
                                    <td>${attachConfig.buyerNamee}</td>
                                    <td>${attachConfig.buyerCodef}</td>
                                    <td>${attachConfig.buyerNamef}</td>
                                    <td>${attachConfig.buyerCodeg}</td>
                                    <td>${attachConfig.buyerNameg}</td>
                                    <td>${attachConfig.buyerCodeh}</td>
                                    <td>${attachConfig.buyerNameh}</td>
                                    <td>${attachConfig.purchasingManagerCode}</td>
                                    <td>${attachConfig.purchasingManagerName}</td>
                                    <td>${attachConfig.twoDepartmentHeadCode}</td>
                                    <td>${attachConfig.twoDepartmentHeadName}</td>
                                    <td>${attachConfig.createUserName}</td>
                                    <td><fmt:formatDate value="${attachConfig.createDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${attachConfig.updateUserName}</td>
                                    <td><fmt:formatDate value="${attachConfig.updateDate}" pattern="yyyy-MM-dd"/></td>
                                    <c:choose>
                                        <c:when test="${attachConfig.status == '0'}">
                                            <td>有效</td>
                                        </c:when>
                                        <c:when test="${attachConfig.status == '1'}">
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
                        <tag:paginate offset="${attachConfigPager.offset}" total="${attachConfigPager.total}"
                                      steps="${attachConfigPager.pageSize}" uri="${attachConfigPager.uri}" formId="attachConfigListForm"/>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<jsp:include page="import_attach_config_window.jsp"></jsp:include>
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
    function queryAttachConfig() {
        window.location.href = "${dynamicURL}/attchConfig/searchAttachConfigList?" + $('#attachConfigListForm').serialize();
    }
    //添加
    function addAttachConfig() {
        var lurl = "${dynamicURL}/attchConfig/openDialogForAdd";
        var dlg = new $.dialog({ id: 'attachConfigAdd', title: "归口/负责人添加", cover: true, page:lurl, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }
    //修改
    function updateAttachConfig(id) {
        var lurl = "${dynamicURL}/attchConfig/openDialogForUpdate?id=" + id;
        var dlg = new $.dialog({ id: 'attachConfigUpdate', title: "归口/负责人修改", cover: true, page:lurl, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }
    //查看
    function showAttachConfig(id) {
        var lurl = "${dynamicURL}/attchConfig/showAttachConfig?id=" + id;
        var dlg = new $.dialog({ id: 'attachConfigShow', title: "归口/负责人查看", cover: true, page:lurl, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }

    $(function () {
        //导入和模版下载窗口
        var ew = new EasyWindow();
        ew.initEasyWindow("归口负责人表数据导入", "${dynamicURL}/attchConfig/api/importExcel");
        $('#btnImport').click(function() {
            ew.showEasyWindow();
        });
        //导出
        $("#btnExport").click(function (){
            if (window.confirm("确认要导出吗?")) {
                window.location.href = "${dynamicURL}/attchConfig/exportExcel?"+$('#attachConfigListForm').serialize();
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
                   url : "${dynamicURL}/attchConfig/api/deleteAttachConfig",
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