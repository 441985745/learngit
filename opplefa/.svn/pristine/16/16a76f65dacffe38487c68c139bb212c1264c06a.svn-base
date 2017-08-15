<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <title>预算资产类型配置</title>
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
                <form action="unit" id="unitForm">
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
                                <td align="right" class="tdtitle">预算资产类型:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="budgetAssetsType" value="${param.budgetAssetsType }" name="budgetAssetsType"
                                           class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">资产类型:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="assetsType" value="${param.assetsType }" name="assetsType"
                                           class="colorblur">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">状态:</td>
                                <td class="tdtext">
                                    <select name="status" id="status" class="colorblur">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="Y" ${param.status=="Y"?"selected='selected'" : ""}>有效</option>
                                        <option value="N" ${param.status=="N"?"selected='selected'" : ""}>无效</option>

                                    </select>
                                </td>
                                <td align="right" class="tdtitle">维护人:
                                </td>
                                <td class="tdtext">
                                    <input name="updateBy" type="text" id="updateBy" value="${param.updateBy }"
                                           class="colorblur" onblur="this.className='colorblur';">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">维护时间:
                                </td>
                                <td class="tdtext">
                                    <input name="updateDateBegin" value="${param.updateDateBegin }" type="text"
                                           id="updateDateBegin" style="width: 95px" class="colorblur Wdate" onclick="WdatePicker({skin:'ext'});">
                                    -<input name="updateDateEnd" value="${param.updateDateEnd }" type="text"
                                            id="updateDateEnd" style="width: 95px" class="colorblur Wdate" onclick="WdatePicker({skin:'ext'});">
                                </td>
                            </tr>
                            <tr>
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
                                <th scope="col">预算资产类型</th>
                                <th scope="col">资产类型</th>                  
                                <th scope="col">创建人</th>
                                <th scope="col">创建时间</th>
                                <th scope="col">最后更新人</th>
                                <th scope="col">最后更新时间</th>
                                <th scope="col">备注</th>
                                <th scope="col">数据状态</th>
                            </tr>
                            <c:forEach items="${budgetAssetstypeConfigPager.records}" var="batc" varStatus="status">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input type="checkbox" name="checkboxName" value="${batc.id }">
                                    </td>
                                    <td>${status.index + 1}</td>
                                    <auth:authTag code="Edit">
                                        <td align="center">
                                            <a href="javascript:opUpdatedlg('updateAssetUnit','预算资产类型修改','${dynamicURL}/config/budgetAssetstypeConfig/updateReady?flag=true&id=${batc.id }')">修改</a>
                                        </td>
                                    </auth:authTag>
                                    <auth:authTag code="View">
                                        <td align="center">
                                            <a href="javascript:opUpdatedlg('showAssetUnit','预算资产类型查看','${dynamicURL}/config/budgetAssetstypeConfig/updateReady?flag=false&id=${batc.id }')">查看</a>
                                        </td>
                                    </auth:authTag>
                                    <td>${batc.budgetAssetsType}</td>
                                    <td>${batc.assetsType}</td>
                                    <td>${batc.createBy}</td>
                                    <td><fmt:formatDate value="${batc.createDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${batc.updateBy}</td>
                                    <td><fmt:formatDate value="${batc.updateDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${batc.memo}</td>
                                    <td id="status${batc.id}">${batc.status=='Y'?"有效":"无效"}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${budgetAssetstypeConfigPager.offset}"
                                      total="${budgetAssetstypeConfigPager.total}"
                                      steps="${budgetAssetstypeConfigPager.pageSize}"
                                      uri="${budgetAssetstypeConfigPager.uri}"
                                      formId="unitForm"/>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="import_budget_asset_type_window.jsp"></jsp:include>
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
	autoSize();
	window.onresize = autoSize;
	GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
	
	$("#btnQuery").click(function(){
	    searchData("${dynamicURL}/config/budgetAssetstypeConfig/searchPagerList?" + $('#unitForm').serialize());
	});

    //导入和模版下载窗口
    var ew = new EasyWindow();
    ew.initEasyWindow("数据导入", "${dynamicURL}/config/budgetAssetstypeConfig/importExcel");
    $('#btnImport').click(function () {
        ew.showEasyWindow();
    });
	 
    $("#btnAdd").click(function(){
    	var url = "${dynamicURL}/config/budgetAssetstypeConfig/addReady";
    	addReady('addAssetUnit',url,"数据新增");
    });
    //删除功能
    $("#btnDelete").click(function(){
        if(confirm("确认是否删除？")){
            deletes('checkboxName',"${dynamicURL}/config/budgetAssetstypeConfig/delete",'ids');
        }

        window.location.reload();
    });
   
    //导出功能
    $("#btnExport").click(function (){
        if (window.confirm("确认要导出吗?")) {
            window.location.href = "${dynamicURL}/config/budgetAssetstypeConfig/exportFile?"+$('#unitForm').serialize();
        }
    });
});

</script>
</body>
</html>