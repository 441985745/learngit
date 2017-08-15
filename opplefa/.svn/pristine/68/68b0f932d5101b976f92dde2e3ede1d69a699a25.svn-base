<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<html>
<head>
    <title>用户列表</title>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet" />
</head>
<body>
    <td style="background-color:#fff;text-align:left;">
        <form id="searchUserForm">
            <table cellpadding="1" bgcolor="#F9F9F9" class="adminlist">
                <tbody>
                <tr>
                    <td height="26" width="20%" style="white-space:nowrap;" align="right">编码：</td>
                    <td>
                        <input name="userCode" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                               onblur="this.className='inputbtnRefView';" style="width:120px;" value="${param.userCode}">
                    </td>

                    <td height="26" width="20%" style="white-space:nowrap;" align="right">名称：</td>

                    <td>
                        <input name="userName" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                               onblur="this.className='inputbtnRefView';" style="width:120px;" value="${param.userName}">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div id="buttons">
            <input type="button" value="查询" id="queryBtn" class="btn">&nbsp;&nbsp;&nbsp;
            <input type="button" value="取消" id="cancelBtn" onclick="closeYHGXControlDialog('txtCUserName_ReferenceDialog','txtCUserName_HidShowFlag');" class="btn">
        </div>
        <div>
            <table class="adminlist" cellspacing="0" rules="all" border="1" id="userTable" style="border-collapse:collapse;width:100%;">
                <thead>
                    <th align="center" scope="col" style="width:30%;">编码</th>
                    <th align="center" scope="col" style="width:70%;">名称</th>
                </thead>
                <tbody>
                <c:forEach items="${userPager.records}" var="user" varStatus="status">
                    <tr>
                        <td align="left">${user.userCode}</td>
                        <td align="left">${user.userName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <tag:paginate offset="${userPager.offset}" total="${userPager.total}"
                      steps="${userPager.pageSize}" uri="${userPager.uri}"
                      formId="searchUserForm" parentWindowId="${userPager.parentWindowId}"/>
    </td>
    <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
    <script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var dg = frameElement.lhgDG;

        GridViewColor("userTable", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");

        function autoSize() {
            $("#gridview").width($(window).width());
            $("#gridview").height($(window).height() - $("#buttons").height() - $("#searchUserForm").height() - $("#pagination_container").height());
        }

        autoSize();
        window.onresize = autoSize;

        (function () {

            $('#queryBtn').click(function () {
                window.location.href = '${dynamicURL}/user/getUserList?' + $('#searchUserForm').serialize();
            });

            $('#userTable > tbody > tr').click(function() {
                setParent(this.children[0].innerText, this.children[1].innerText);
            });

            function setParent(code, name) {
            	dg.curWin.document.getElementById('postAllocationPeople').value = name;
            	dg.curWin.document.getElementById('peopleValue').value = code;
            	dg.cancel();
            }

        })();

    </script>
</body>
</html>
