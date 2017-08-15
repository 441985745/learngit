<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<html>
<head>
    <title>公司列表</title>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet" />
</head>
<body>
    <form id="positionForm">
        <table cellpadding="1" bgcolor="#F9F9F9" class="adminlist">
            <tbody>
            <tr>
                <td height="26" width="20%" style="white-space:nowrap;" align="right">编码：</td>
                <td>
                    <input name="companyCode" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                           onblur="this.className='inputbtnRefView';" style="width:120px;" value="${param.companyCode}">

                </td>

                <td height="26" width="20%" style="white-space:nowrap;" align="right">名称：</td>

                <td>
                    <input name="companyName" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                           onblur="this.className='inputbtnRefView';" style="width:120px;" value="${param.companyName}">
                    <input name="parentWindowId" value="${param.parentWindowId}" type="hidden">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <div id="buttons">
        <input type="button" value="查询" id="queryBtn" class="btn">&nbsp;&nbsp;&nbsp;
        <input type="button" value="取消" id="cancelBtn" onclick="dg.cancel();" class="btn">
    </div>
    <div>
        <table class="adminlist" cellspacing="0" rules="all" border="1" id="userTable" style="border-collapse:collapse;width:100%;">
            <thead>
            <th align="center" scope="col" style="width:30%;">编码</th>
            <th align="center" scope="col" style="width:70%;">名称</th>
            </thead>
            <tbody>
            <c:forEach items="${companyPager.records}" var="company" varStatus="status">
                <tr>
                    <td align="left">${company.companyCode}</td>
                    <td align="left">${company.companyName}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div style="width: 100%;">
    <tag:paginate offset="${companyPager.offset}" total="${companyPager.total}"
                  steps="${companyPager.pageSize}" uri="${companyPager.uri}"
                  formId="positionForm" parentWindowId="${companyPager.parentWindowId}"/>
    </div>
<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="text/javascript">
    var dg = frameElement.lhgDG;

    GridViewColor("userTable", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");

    function autoSize() {
        $("#gridview").width($(window).width());
        $("#gridview").height($(window).height() - $("#buttons").height() - $("#positionForm").height() - $("#pagination_container").height());
    }

    autoSize();
    window.onresize = autoSize;

    (function () {

        var dialogId = "${param.parentWindowId}";

        $('#queryBtn').click(function () {
            window.location.href = "${dynamicURL}/company/searchCompanies?" + $('#positionForm').serialize();
        });

        $('#userTable > tbody > tr').click(function() {
            setParent(this.children[0].innerText, this.children[1].innerText,dialogId);
        });

        function setParent(code, name, dialogId) {
            if (dialogId != '') {
                if (dg.iDG(dialogId)) {
                    dg.iWin(dialogId).fillCompany(code, name);
                    dg.cancel();
                } else {
                    alert('请先打开父窗口');
                }
            } else {
                alert('请先打开父窗口');
            }
        }
    })();

</script>
</body>
</html>
