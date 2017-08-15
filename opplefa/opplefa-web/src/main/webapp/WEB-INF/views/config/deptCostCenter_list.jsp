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
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <title>成本中心/部门配置表</title>
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
    <table cellspacing="0" cellpadding="0"
           style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form action="deptCostCenter" id="deptCostCenterForm">
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
                                <td align="right" class="tdtitle">预算体编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="deptCode" value="${param.deptCode }" name="deptCode"
                                           class="colorblur">
                                </td>
                                <td align="right" class="tdtitle">预算体名称:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="deptName" value="${param.deptName }" name="deptName"
                                           class="colorblur">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">成本中心编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="costCenterCode" value="${param.costCenterCode }" name="costCenterCode"
                                           class="colorblur">
                                </td>
                                <td align="right" class="tdtitle">成本中心名称:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="costCenterName" value="${param.costCenterName }" name="costCenterName"
                                           class="colorblur">
                                </td>
                            </tr>
                            <tr>
                            <tr>
                                <td align="right" class="tdtitle">最后维护人:
                                </td>
                                <td class="tdtext">
                                    <input name="updateBy" type="text" id="updateBy" value="${param.updateBy }"
                                           class="colorblur" onblur="this.className='colorblur';">
                                </td>
                                <td align="right" class="tdtitle">所属公司编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="companyCode" value="${param.companyCode }" name="companyCode"
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
                                <td align="right" class="tdtitle">最后维护时间:
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
                    <input type="button" name="btnQuery" value="查询" id="btnQuery"  class="btn">
                </auth:authTag>
                <auth:authTag code="Add">
                    <input type="button" name="" value="添加" id="btnAdd" class="btn">
                </auth:authTag>
                <auth:authTag code="Delete">
                    <input type="button" name="" value="删除" id="btnDelete" class="btn">
                </auth:authTag>
                <auth:authTag code="Import">
                    <input type="button" name="" value="导入" id="btnImport" class="btn" onclick="javascript:ajaxFileUpload()">
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
                                <th scope="col">公司编码</th>
                                <th scope="col">公司名称</th>
                                <th scope="col">预算体编码</th>
                                <th scope="col">预算体名称</th>
                                <th scope="col">成本中心编码</th>
                                <th scope="col">成本中心名称</th>
                                <th scope="col">创建人</th>
                                <th scope="col">创建时间</th>
                                <th scope="col">最后更新人</th>
                                <th scope="col">最后更新时间</th>
                                <th scope="col">备注</th>
                                <th scope="col">数据状态</th>
                            </tr>
                            <c:forEach items="${deptCostCenterPager.records}" var="dept" varStatus="status">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input type="checkbox" name="checkboxName" class="checkboxName" value="${dept.id }">
                                    </td>
                                    <td>${status.index + 1}</td>
                                    <auth:authTag code="Edit">
                                        <td align="center">
                                            <a href="javascript:opUpdatedlg('updatePosition','预算体-成本中心修改','${dynamicURL}/config/deptCostCenter/updateReady?flag=true&deptCode=${dept.deptCode }&companyCode=${dept.companyCode}')">修改</a>
                                        </td>
                                    </auth:authTag>
                                    <auth:authTag code="View">
                                        <td align="center">
                                            <a href="javascript:opUpdatedlg('updatePosition','预算体-成本中心查看','${dynamicURL}/config/deptCostCenter/updateReady?deptCode=${dept.deptCode }&companyCode=${dept.companyCode}')">查看</a>
                                        </td>
                                    </auth:authTag>
                                    <td>${dept.companyCode}</td>
                                    <td>${dept.companyName}</td>
                                    <td>${dept.deptCode}</td>
                                    <td>${dept.deptName}</td>
                                    <td>${dept.costCenterCode}</td>
                                    <td>${dept.costCenterName}</td>
                                    <td>${dept.createBy}</td>
                                    <td align="center"><fmt:formatDate value="${dept.createDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${dept.updateBy}</td>
                                    <td align="center"><fmt:formatDate value="${dept.lastDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${dept.memo}</td>
                                    <td id="status${dept.deptCode}" align="center">${dept.status=='Y'?'有效':'无效'}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${deptCostCenterPager.offset}"
                                      total="${deptCostCenterPager.total}"
                                      steps="${deptCostCenterPager.pageSize}"
                                      uri="${deptCostCenterPager.uri}"
                                      formId="deptCostCenterForm"/>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="import_dept_costcenter_window.jsp"></jsp:include>
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


        //查询功能
        $("#btnQuery").click(function(){
            searchData("${dynamicURL}/config/deptCostCenter/searchPagerList?" + $('#deptCostCenterForm').serialize());
        });

        //导入功能
        //导入和模版下载窗口
        var ew = new EasyWindow();
        ew.initEasyWindow("数据导入", "${dynamicURL}/config/deptCostCenter/importExcel");
        $('#btnImport').click(function () {
            ew.showEasyWindow();
        });

        //新增功能
        $("#btnAdd").click(function(){
            var url = "${dynamicURL}/config/deptCostCenter/addReady";
            addReady('adddeptCostCenter',url,"数据新增");
        });
        //删除功能
        $("#btnDelete").click(function () {

            var deptCodes = new Array();
            var companyCodes = new Array();
            $(".checkboxName").each(function () {
                if ($(this).attr('checked')) {
                    deptCodes.push($(this).val());
                    companyCodes.push($(this).next().val());
                }
            });
            if (deptCodes.length < 1) {
                alert("至少选择一条数据！");
                return;
            }
            if (window.confirm("确认删除？")) {
                $.ajax({
                    url : "${dynamicURL}/config/deptCostCenter/delete",
                    type : "post",
                    dataType:"json",
                    contentType:"application/json; charset=utf-8" ,
                    data : JSON.stringify({"deptCodes":deptCodes , "companyCodes":companyCodes}),
                    traditional:true,//这里必须设置
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


        //导出功能
        $("#btnExport").click(function (){
            if (window.confirm("确认要导出吗?")) {
                window.location.href = "${dynamicURL}/config/deptCostCenter/exportFile?"+$('#deptCostCenterForm').serialize();
            }
        });
    });

</script>

</body>
</html>
