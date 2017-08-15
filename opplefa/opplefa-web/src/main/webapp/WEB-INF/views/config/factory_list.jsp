<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <title>工厂表</title>
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
    <table cellspacing="0" cellpadding="0"
           style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form action="factory" id="factoryForm">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif"
                                 style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                        </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="t_ShipInportInfo"
                               class="adminlist" style="visibility: visible;">
                            <tbody>
                            <tr>
                                <td align="right" class="tdtitle">工厂编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="cfactoryCode" value="${param.cfactoryCode }" name="cfactoryCode"
                                           class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">工厂名称:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="cfactoryName" value="${param.cfactoryName }" name="cfactoryName"
                                           class="colorblur">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">公司编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="ccompanyCode" value="${param.ccompanyCode }" name="ccompanyCode"
                                           class="colorblur">
                                </td>
                                <td align="right" class="tdtitle">状态:</td>
                                <td class="tdtext">
                                    <select name="cstatus" id="cstatus" class="colorblur">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="Y" ${param.cstatus=="Y"?"selected='selected'" : ""}>有效</option>
                                        <option value="N" ${param.cstatus=="N"?"selected='selected'" : ""}>无效</option>

                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">维护人:
                                </td>
                                <td class="tdtext">
                                    <input name="cupdateBy" type="text" id="cupdateBy" value="${param.cupdateBy }"
                                           class="colorblur" onblur="this.className='colorblur';">
                                </td>
                                <td align="right" class="tdtitle">维护时间:
                                </td>
                                <td class="tdtext">
                                    <input name="updateDateBegin" value="${param.updateDateBegin }" type="text"
                                           id="updateDateBegin" style="width: 95px" class="colorblur Wdate" onclick="WdatePicker({skin:'ext'});">
                                    -<input name="updateDateEnd" value="${param.updateDateEnd }" type="text"
                                            id="updateDateEnd" style="width: 95px" class="colorblur Wdate" onclick="WdatePicker({skin:'ext'});">
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
                    <input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn">
                </auth:authTag>
                <auth:authTag code="Add">
                    <input type="button" name="" value="添加" id="btnAdd" class="btn">
                </auth:authTag>
                <auth:authTag code="Delete">
                    <input type="button" name="" value="删除" id="btnDelete" class="btn">
                </auth:authTag>
                <auth:authTag code="Import">
              	    <input type="button" name="" value="导入" id="btnImport" class="btn">
                </auth:authTag>
                <auth:authTag code="Export">
                    <input type="button" name="" value="导出" id="btnExport" class="btn">
                </auth:authTag>

            </td>
        </tr>
        <tr>
            <td valign="top">
                <div id="gridview" style="width: 1045px; height: 328px;">
                    <div>
                        <table class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border-collapse: collapse;">
                            <tbody>
                            <tr style="white-space: nowrap;">
                                <th scope="col">
                                    <input type="checkbox" name=""
                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'gvDataList');"/>
                                </th>
                                <th scope="col">序号</th>
                                <auth:authTag code="Edit">
                                    <th scope="col">操作</th>
                                </auth:authTag>
                                <auth:authTag code="View">
                                    <th scope="col">操作</th>
                                </auth:authTag>
                                <th scope="col">工厂编码</th>
                                <th scope="col">工厂名称</th>
                                <th scope="col">公司编码</th>
                                <th scope="col">公司名称</th>
                                <th scope="col">创建人</th>
                                <th scope="col">创建时间</th>
                                <th scope="col">最后更新人</th>
                                <th scope="col">最后更新时间</th>
                                <th scope="col">备注</th>
                                <th scope="col">数据状态</th>
                            </tr>
                            <c:forEach items="${factoryPager.records}" var="scc" varStatus="cstatus">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input type="checkbox" name="checkboxName" value="${scc.cfactoryCode }">
                                    </td>
                                    <td>${cstatus.index + 1}</td>
                                    <auth:authTag code="Edit">
                                        <td align="center">
                                            <a href="javascript:opUpdatedlg('updateStandardComputerConfig','工厂修改','${dynamicURL}/config/factory/updateReady?flag=true&cfactoryCode=${scc.cfactoryCode }')">修改</a>
                                        </td>
                                    </auth:authTag>
                                    <auth:authTag code="View">
                                        <td align="center">
                                            <a href="javascript:opUpdatedlg('showStandardComputerConfig','工厂查看','${dynamicURL}/config/factory/updateReady?flag=false&cfactoryCode=${scc.cfactoryCode }')">查看</a>
                                        </td>
                                    </auth:authTag>
                                    <td>${scc.cfactoryCode}</td>
                                    <td>${scc.cfactoryName}</td>
                                    <td>${scc.ccompanyCode}</td>
                                    <td>${scc.ccompanyName}</td>
                                    <td>${scc.ccreateBy}</td>
                                    <td><fmt:formatDate value="${scc.dcreateDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${scc.cupdateBy}</td>
                                    <td><fmt:formatDate value="${scc.dupdateDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${scc.cmemo}</td>
                                    <td id="cstatus${scc.cfactoryCode}">${scc.cstatus=='Y'?"有效":"无效"}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${factoryPager.offset}"
                                      total="${factoryPager.total}"
                                      steps="${factoryPager.pageSize}"
                                      uri="${factoryPager.uri}"
                                      formId="factoryForm"/>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="import_window.jsp"></jsp:include>
<script src="${staticURL}/Scripts/config/crud.js" type="text/javascript"></script>
<script type="text/javascript">
    function autoSize() {
        $("#gridview").width($(window).width());
        $("#gridview").height(
                $(window).height() - $("#Query").height()
                - $("#Button").height()
                - $("#AspNetPager").height() - 5);
    }
$(function(){

    String.prototype.trim = function () {
        return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
    };
	autoSize();
	window.onresize = autoSize;
	GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
	
	$("#btnQuery").click(function(){
	    searchData("${dynamicURL}/config/factory/searchPagerList?" + $('#factoryForm').serialize());
	});

    //导入和模版下载窗口
    var ew = new EasyWindow();
    ew.initEasyWindow("数据导入", "工厂导入模板.xlsx", "${dynamicURL}/config/factory/importExcel");
    $('#btnImport').click(function () {
        ew.showEasyWindow();
    });


    $("#btnAdd").click(function(){
    	var url = "${dynamicURL}/config/factory/addReady";
    	addReady('addStandardComputerConfig',url,"数据新增");
    });
    //删除功能
    $("#btnDelete").click(function(){
        if(confirm("确实要删除吗？")){
            deletes('checkboxName',"${dynamicURL}/config/factory/delete",'cfactoryCodes');

        }
        window.location.reload();
    });
   
    //导出功能
    $("#btnExport").click(function (){
        if (window.confirm("确认要导出吗?")) {
            window.location.href = "${dynamicURL}/config/factory/exportFile?"+$('#factoryForm').serialize();
        }
    });
});

</script>
</body>
</html>