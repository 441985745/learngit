<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>预算调整</title>
    <meta name="modulePath" content="basic/"/>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">

</head>
<body>
<div>
    <input type="hidden" name="hfQueryStatus" id="hfQueryStatus" value="show">
     <table cellspacing="0" cellpadding="0" style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form action="budgetAdjustment" id="budgetAdjustmentForm">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif" style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                       	</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="t_ShipInportInfo" class="adminlist" style="visibility: visible;">
                            <tbody>
                            <tr>
                                <td align="right" class="tdtitle">单据号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="document" id="txtdocument" class="colorblur"
                                           value="${param.document}" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';">
                                </td>
                                <td align="right" class="tdtitle">审批状态:
                                </td>
                                <td class="tdtext">
                                    <select name="approvalState" class="colorblur">
                                        <option value="">请选择</option>
                                        <option value="等待审批" ${param.approvalState == "等待审批" ? "selected" : ""}>等待审批
                                        </option>
                                        <option value="正在审批" ${param.approvalState == "正在审批" ? "selected" : ""}>正在审批
                                        </option>
                                        <option value="已完成" ${param.approvalState == "已完成" ? "selected" : ""}>已完成
                                        </option>
                                        <option value="退回" ${param.approvalState == "退回" ? "selected" : ""}>退回</option>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td align="right" class="tdtitle">预算年度:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="budgetYear" id="txtBudgetYear" class="colorblur"
                                           value="${param.budgetYear}" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';">
                                </td>
                                <td align="right" class="tdtitle">调整类型:
                                </td>
                                <td class="tdtext">
                                    <select name="assetsBudgetType" class="colorblur">
                                        <option value="">请选择</option>
                                        <option value="新增" ${param.assetsBudgetType == "新增" ? "selected" : ""}>新增
                                        </option>
                                        <option value="追加/调减" ${param.assetsBudgetType == "追加/调减" ? "selected" : ""}>
                                            追加/调减
                                        </option>
                                        <option value="项目间调整" ${param.assetsBudgetType == "项目间调整" ? "selected" : ""}>
                                            项目间调整
                                        </option>
                                    </select>
                                </td>

                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">预算项目编码:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="projectCode" id="txtProjectCode" class="colorblur"
                                           value="${param.projectCode}" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';">
                                </td>
                                <td align="right" class="tdtitle">是否草稿:
                                </td>
                                <td class="tdtext">
                                    <select name="draft" class="colorblur">
                                        <option value="">请选择</option>
                                        <option value="Y" ${param.draft == "Y" ? "selected" : ""}>是</option>
                                        <option value="N" ${param.draft == "N" ? "selected" : ""}>否</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">申请人:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="userName" id="txtUserName" class="colorblur"
                                           value="${param.userName}" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';">
                                </td>
                                <td align="right" class="tdtitle">申请日期:
                                </td>
                                <td class="tdtext">
                                    <input name="startDate" type="text" id="txtStartDate" class="Wdate"
                                           value="${param.startDate}" onclick="WdatePicker({skin:'ext'});">-<input
                                        name="endDate" type="text" id="txtEndDate" class="Wdate"
                                        value="${param.endDate}" onclick="WdatePicker({skin:'ext'});">
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
                <auth:authTag code="Apply">
                    <input type="button" name="" value="新增" id="budgetAdjust" class="btn"
                           onclick="addBudgetAdjustment('数据新增');">
                </auth:authTag>
                <auth:authTag code="Check">
                    <input type="button" name="" value="审批" id="budgetApprove" class="btn"
                           onclick="budgetAdjustmentApprove('数据审批')">
                </auth:authTag>
                <auth:authTag code="Export">
                    <input type="button" name="" value="导出" id="budgetExport" class="btn">
                </auth:authTag>
                <auth:authTag code="Delete">
                    <input type="button" name="" value="删除" id="budgetDelete" class="btn">
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
                                    <input id="" type="checkbox" name=""/>
                                </th>
                                <th scope="col">序号</th>
                                <auth:authTag code="Edit">
                                    <th scope="col">操作</th>
                                </auth:authTag>
                                <auth:authTag code="View">
                                    <th scope="col">操作</th>
                                </auth:authTag>
                                <th scope="col">是否草稿</th>
                                <th scope="col">单据号</th>
                                <th scope="col">审批状态</th>
                                <th scope="col">申请人</th>
                                <th scope="col">申请日期</th>
                                <th scope="col">预算调整类型</th>
                                <th scope="col">当前审批人</th>
                                <th scope="col">维护人</th>
                                <th scope="col">维护时间</th>
                            </tr>

                            <c:forEach items="${budgetAdjustmentPager.records}" var="budgetAdjustment" varStatus="status">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input type="checkbox" name="budgetId" class="budgetCheckbox" value="${budgetAdjustment.budgetId}" onclick="keepOnlyOne(this)" applyUserCode = "${budgetAdjustment.userCode}">
                                        <input type="hidden" id="approvalState" value="${budgetAdjustment.approvalState}"/>
                                        <input type="hidden" id="document" value="${budgetAdjustment.document}"/>
                                        <input type="hidden" id="assetsBudgetType" value="${budgetAdjustment.assetsBudgetType}"/>
                                    </td>
                                    <td align="center">
                                       ${status.count }
                                    </td>
                                    <auth:authTag code="Edit">
                                        <td align="center">
                                            <a href="javascript:void(0)" onclick="updateBudgetAdjustment('${budgetAdjustment.document}', '${budgetAdjustment.approvalState}', '${sessionScope._user_name}', '${budgetAdjustment.userCode}')">修改</a>
                                        </td>
                                    </auth:authTag>
                                    <auth:authTag code="View">
                                        <td align="center">
                                            <a href="javascript:void(0)" onclick="getBudgetAdjustment('${budgetAdjustment.document}')">查看</a>
                                        </td>
                                    </auth:authTag>
                                    <td align="center">${budgetAdjustment.draft == 'Y' ? "是" : "否"}</td>
                                    <td>${budgetAdjustment.document}</td>
                                    <td><a href="javascript:void(0)" onclick="showApproveHistory('${budgetAdjustment.document}', '77047')">${budgetAdjustment.approvalState}</a></td>
                                    <td>${budgetAdjustment.userName}</td>
                                    <td align="center"><fmt:formatDate value="${budgetAdjustment.applicationDate}" pattern="yyyy-MM-dd"/> </td>
                                    <td>${budgetAdjustment.assetsBudgetType}</td>
                                    <td>${budgetAdjustment.nextHandlerName}</td>
                                    <td>${budgetAdjustment.updateUserName}</td>
                                    <td align="center"><fmt:formatDate value="${budgetAdjustment.updateDate}"  pattern="yyyy-MM-dd"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${budgetAdjustmentPager.offset}" total="${budgetAdjustmentPager.total}" steps="${budgetAdjustmentPager.pageSize}" uri="${budgetAdjustmentPager.uri}" formId="budgetAdjustmentForm" />
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="import_budget_window.jsp"></jsp:include>
<!-- 导入-->
<div id="budgetImportWindow" name="" class="easyui-window" title="导入预算excel" data-options="iconCls:'icon-save'" style="width:500px;height:200px;padding:5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
				    
		        <form id="fileUpload" method="post" enctype="multipart/form-data" class="form-horizontal" ><!-- onsubmit="return check()" action="${dynamicURL}/fileUpload/importFile"  -->
		              <div class="imgtitle"></div>
		                	文件信息
		                <span class="warn">*</span><input type="file" id="inputfile" name="inputfile">
		                    <input type="button" value="上传" id="uploadStart"><!--onclick="fileUpload()"  -->
		        	<!-- 	<input type="hidden" name="document" id="document">
		        		<input type="hidden" name="flag" id="flag" value="0"> -->
		        </form>
			</div>
		</div>
</div>

<%--<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>--%>
<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<%--<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>--%>
<%--<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>--%>
<%--<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>--%>



<script type="text/javascript">
    GridViewColor("gvDataLlist", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
    QueryFieldSetStauts('t_ShipInportInfo', 'fset_ShipInportInfo', 'img_ShipInportInfo', document.getElementById("hfQueryStatus").value);
    function autoSize() {
        $("#gridview").width($(window).width());
        $("#gridview").height(
                $(window).height() - $("#Query").height()
                - $("#Button").height()
                - $("#AspNetPager").height());
    }
    autoSize();
    window.onresize = autoSize;

    function keepOnlyOne(that) {
        if ($(that).attr("checked")) {
            $(".budgetCheckbox").attr("checked", false);
            $(that).attr("checked", true);
        }
    }

    function updateBudgetAdjustment(document, approveState, loginUserCode, applyUserCode) {
        var auth = isAuthority(loginUserCode,applyUserCode);
        if(!auth){
            alert("您还没有操作权限，请检查是否离职授权！");
            return;
        };

        if (approveState == '等待审批' || approveState == '退回' || approveState == '' || approveState == null) {
            var lurl = "${dynamicURL}/budgetAdjustment/getBudgetAdjustmentInfoForUpdate?document=" + document;
            var dlg = new $.dialog({
                id: "adjustBudget",
                title: "数据修改",
                cover: true,
                page: lurl,
                rang: true,
                width: 1000,
                height: 600,
                btnBar: false,
                resize: false,
                maxBtn: true
            }).ShowDialog();
        } else {
            alert("不能修改");
            return;
        }
    }
    function getBudgetAdjustment(document) {
        var lurl = "${dynamicURL}/budgetAdjustment/getBudgetAdjustmentInfoForShow?document=" + document;
        var dlg = new $.dialog({
            id: "adjustBudget",
            title: "数据查看",
            cover: true,
            page: lurl,
            rang: true,
            width: 1000,
            height: 600,
            btnBar: false,
            resize: false,
            maxBtn: true
        }).ShowDialog();
    }

    $("#btnQuery").click(function() {
        window.location.href = '${dynamicURL}/budgetAdjustment/getBudgetAdjustmentList?' + $('#budgetAdjustmentForm').serialize();
    });
    //预算调整
	function addBudgetAdjustment(title) {
	     var lurl = "${dynamicURL}/budgetAdjustment/getBudgetAdjustmentInfoForAdd";
        var dlg = new $.dialog({
            id: "adjustBudget",
            title: title,
            cover: true,
            page: lurl,
            rang: true,
            width: 1000,
            height: 600,
            btnBar: false,
            resize: false,
            maxBtn: true
        }).ShowDialog();
     }
    //导出
    $("#budgetExport").click(function (){
        if (window.confirm("确认要导出吗?")) {
            window.location.href = "${dynamicURL}/budgetAdjustment/exportBudgetAdjustment?"+$('#budgetAdjustmentForm').serialize();
        }
    });

    //删除
    $("#budgetDelete").click(function () {
        var budgetId = "";
        var document = "";
        var assetsBudgetType = "";
        var msg = "";
        $(".budgetCheckbox").each(function () {
            if ($(this).attr("checked")) {
                if ($(this).next().val() == '等待审批' || $(this).next().val() == '退回' || $(this).next().val() == '') {
                    budgetId = $(this).val();
                    document = $(this).siblings("#document").val();
                    assetsBudgetType = $(this).siblings("#assetsBudgetType").val();
                } else {
                    msg = "该单据不能删除";
                }
            }
        });
        if (msg != "") {
            alert(msg);
            return;
        }
        if (budgetId == "") {
            alert("至少选择一条数据");
            return;
        }
        var loginUserCode = '${sessionScope._user_name}';
        var applyUserCode = $("#gvDataList td input[name = budgetId]:checked").attr("applyUserCode");
        var auth = isAuthority(loginUserCode,applyUserCode);
        if(!auth){
            alert("您还没有操作权限，请检查是否离职授权！");
            return;
        };
        if (window.confirm("确认删除？")) {
            $.ajax({
                url: "${dynamicURL}/budgetAdjustment/api/deleteBudgetAdjustment",
                type: "POST",
                // contentType: "application/json",
                data:{budgetId : budgetId, assetsBudgetType : assetsBudgetType, document : document},
                success: function (data) {
                    alert(data.result);
                    window.location.reload();
                }
            });
        }
    });
    //审批
    function budgetAdjustmentApprove(title){
        var length=$(".budgetCheckbox:checked").length;
        if(length=='0'){
            alert("请选择一条要审批的数据");
        }
        if(length>1){
            alert("只能选择一条数据");
        }
        if(length=='1'){
            var document=$(".budgetCheckbox:checked").siblings("#document").val();
            var lurl = "${dynamicURL}/budgetAdjustment/budgetAdjustmentApprove?document=" + document;
            var dlg = new $.dialog({
                id: 'approve',
                title: title,
                cover: true,
                page: lurl,
                rang: true,
                width: 1000,
                height: 600,
                btnBar: false,
                resize: false,
                maxBtn: true
            }).ShowDialog();
        }
    }
    //历史审批记录
    function showApproveHistory(document, typeId) {
        var url = '${dynamicURL}/budgetAdjustment/showApproveHistory?document='+document+"&typeId="+typeId;
        var dlg = new $.dialog({
            id: 'approveHistory',
            title: "预算调整审批历史",
            cover: true,
            page: url,
            rang: true,
            width: 700,
            height: 450,
            btnBar: false,
            resize: false,
            maxBtn: true
        }).ShowDialog();
        // showDialog('approveHistory', '申请流程', url + document +'&typeId=' + typeId, 800, 500);
    }

    //判断是否有权限删除修改单据（离职授权）
    function isAuthority(loginUserCode,applyUserCode){
        var flag = null;
        $.ajax({
            url: "${dynamicURL}/userInfo/api/isAuthority",
            type: "POST",
            async: false,
            dataType:"json",
            // contentType: "application/json",
            data:{loginUserCode : loginUserCode,applyUserCode:applyUserCode},
            success: function (data) {

                flag=data;
            }
        });
        return flag;
    }
</script>
</body>
</html>