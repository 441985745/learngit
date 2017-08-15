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
    <title>标准电脑配置表</title>
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
           style="width: 100%; height: 98%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form action="workAddress" id="workAddressForm">
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
                                <td align="right" class="tdtitle">办公地点编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressCode" value="${param.caddressCode }" name="caddressCode"
                                           class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">办公地点名称:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressName" value="${param.caddressName }" name="caddressName"
                                           class="colorblur">
                                </td>
                            </tr>
                            
                            
                         <%--    <tr>
                                <td align="right" class="tdtitle">仓库收货管理员编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressCode" value="${param.receivegoodsCode }" name="caddressCode"
                                           class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">仓库收货管理员名称:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressName" value="${param.receivegoodsName }" name="caddressName"
                                           class="colorblur">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">仓库废品管理员编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressCode" value="${param.retiredgoodsCode }" name="caddressCode"
                                           class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">仓库废品管理员姓名:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressName" value="${param.retiredgoodsName }" name="caddressName"
                                           class="colorblur">
                                </td>
                            </tr>
                            <tr>
                            <tr>
                                <td align="right" class="tdtitle">采购意见处理员编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressCode" value="${param.purchasetipaccessCode }" name="caddressCode"
                                           class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">采购意见处理员姓名:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressName" value="${param.purchasetipaccessName }" name="caddressName"
                                           class="colorblur">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">采购竞价/议价员编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressCode" value="${param.purchasebidCode }" name="caddressCode"
                                           class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">采购竞价/议价员姓名:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressName" value="${param.purchasebidName }" name="caddressName"
                                           class="colorblur">
                                </td>
                            </tr>
                              <tr>
                                <td align="right" class="tdtitle">收款确认人编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressCode" value="${param.payeeCode }" name="caddressCode"
                                           class="colorblur">
                                </td>
                                 <td align="right" class="tdtitle">收款确认人姓名:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="caddressName" value="${param.payeeName }" name="caddressName"
                                           class="colorblur">
                                </td>
                            </tr> --%>
                            
                            
                            <tr>
                                <td align="right" class="tdtitle">最后维护人:
                                </td>
                                <td class="tdtext">
                                    <input name="cupdateBy" type="text" id="cupdateBy" value="${param.cupdateBy }"
                                           class="colorblur" onblur="this.className='colorblur';">
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
								<td align="right" class="tdtitle">状态:</td>
                                <td class="tdtext">
                                    <select name="cstatus" id="cstatus" class="colorblur">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="Y" ${param.cstatus=="Y"?"selected='selected'" : ""}>有效</option>
                                        <option value="N" ${param.cstatus=="N"?"selected='selected'" : ""}>无效</option>

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
                    <input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn">
                </auth:authTag>
                <auth:authTag code="Add">
                    <input type="button" name="" value="添加" id="btnAdd" class="btn">
                </auth:authTag>
                <auth:authTag code="Delete">
                    <input type="button" name="" value="删除" id="btnDelete" class="btn">
                </auth:authTag>
                <auth:authTag code="Import">
              	    <input type="button" name="" value="导入" id="btnImport" class="btn" >
                </auth:authTag>
                <auth:authTag code="Export">
                    <input type="button" name="" value="导出" id="btnExport" class="btn">
                </auth:authTag>

            </td>
        </tr>
        <tr>
            <td valign="top">
                <div id="gridview" style="width: auto; height: auto;">
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
                                 <th scope="col">办公地点编码</th>
                                <th scope="col">办公地点名称</th>
                                <th scope="col">仓库收货管理员编码</th>
                                <th scope="col">仓库收货管理员姓名</th>
                                <th scope="col">仓库废品管理员编码</th>
                                <th scope="col">仓库废品管理员姓名</th>
                                 <th scope="col">采购意见处理员编码</th>
                                <th scope="col">采购意见处理员姓名</th>
                                 <th scope="col">采购竞价/议价员编码</th>
                                <th scope="col">采购竞价/议价员姓名</th>
                                 <th scope="col">收款确认人编码</th>
                                <th scope="col">收款确认人姓名</th>
                                
                                <th scope="col">创建人</th>
                                <th scope="col">创建时间</th>
                                <th scope="col">最后更新人</th>
                                <th scope="col">最后更新时间</th>
                                <th scope="col">备注</th>
                                <th scope="col">数据状态</th>
                            </tr>
                            <c:forEach items="${workAddressPager.records}" var="wads" varStatus="cstatus">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input type="checkbox" name="checkboxName" value="${wads.caddressCode }">
                                    </td>
                                    <td>${cstatus.index + 1}</td>
                                    <auth:authTag code="Edit">
                                        <td align="center">
                                            <a href="javascript:void(0)" onclick="opUpdatedlg('办公地点修改','${dynamicURL}/config/workAddress/updateReady?flag=true&caddressCode=${wads.caddressCode}')">修改</a>
                                        </td>
                                    </auth:authTag>
                                    <auth:authTag code="View">
                                        <td align="center">
                                            <a href="javascript:void(0)" onclick="opUpdatedlg('办公地点查看','${dynamicURL}/config/workAddress/updateReady?caddressCode=${wads.caddressCode}')">查看</a>
                                        </td>
                                    </auth:authTag>
                                     <td>${wads.caddressCode}</td>
                                    <td>${wads.caddressName}</td>
                                    
                                    <td>${wads.receivegoodsCode}</td>
                                    <td>${wads.receivegoodsName}</td>
                                    <td>${wads.retiredgoodsCode}</td>
                                    <td>${wads.retiredgoodsName}</td>
                                    <td>${wads.purchasetipaccessCode}</td>
                                    <td>${wads.purchasetipaccessName}</td>
                                    <td>${wads.purchasebidCode}</td>
                                    <td>${wads.purchasebidName}</td>
                                    <td>${wads.payeeCode}</td>
                                    <td>${wads.payeeName}</td>
                                    
                                    <td>${wads.ccreateBy}</td>
                                    <td><fmt:formatDate value="${wads.dcreateDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${wads.cupdateBy}</td>
                                    <td><fmt:formatDate value="${wads.dupdateDate}" pattern="yyyy-MM-dd"/></td>
                                    <td>${wads.cmemo}</td>
                                    <td id="status${wads.caddressCode}">${wads.cstatus=='Y'?'有效':'无效'}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${workAddressPager.offset}"
                                      total="${workAddressPager.total}"
                                      steps="${workAddressPager.pageSize}"
                                      uri="${workAddressPager.uri}"
                                      formId="workAddressForm"/>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="import_workAddress_unit_window.jsp"></jsp:include>
<script src="${staticURL}/Scripts/config/crud.js" type="text/javascript"></script>
<script type="text/javascript">
    GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
    function autoSize() {
        $("#gridview").width($(window).width());
        $("#gridview").height(
                $(window).height() - $("#Query").height()
                - $("#Button").height()
                - $("#AspNetPager").height() - 5);
    }

    autoSize();
    window.onresize = autoSize;

    /*修改/查看*/
    function opUpdatedlg(title, url) {
        var dlg = new $.dialog({ id: 'updateWorkAddress', title: title, cover: true, page:url, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }

    $(function(){
        /*查询*/
        $("#btnQuery").click(function(){
             searchData("${dynamicURL}/config/workAddress/searchPagerList?" + $('#workAddressForm').serialize());
        });
        //导入和模版下载窗口
        var ew = new EasyWindow();
        ew.initEasyWindow("数据导入", "${dynamicURL}/config/workAddress/importExcel");
        $('#btnImport').click(function () {
            ew.showEasyWindow();
        });
        /*添加*/
        $("#btnAdd").click(function(){
            var url = "${dynamicURL}/config/workAddress/addReady";
            var boo = $("#btnAdd").attr("name");
            addReady('addworkAddress',url,"数据新增",boo);
            if(boo){
                searchData("${dynamicURL}/config/workAddress/searchPagerList?" + $('#workAddressForm').serialize());
            }
        });
        //删除功能
        $("#btnDelete").click(function(){
            if(confirm("请确认是否删除?")){
                deletes('checkboxName',"${dynamicURL}/config/workAddress/delete",'caddressCodes');
            }
            window.location.reload();
        });

        //导出功能
        $("#btnExport").click(function (){
            if (window.confirm("确认要导出吗?")) {
                window.location.href = "${dynamicURL}/config/workAddress/exportFile?"+$('#workAddressForm').serialize();
            }
        });
    });

</script>
</body>
</html>