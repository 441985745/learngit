<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 21/11/2016
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gb2312" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>申请流程</title>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet" />
</head>
<body>
<div id="gridview">
    <div>
        <table class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList">
            <thead>
            <tr style="white-space: nowrap;">
                <th scope="col">审批人</th>
                <th scope="col">审批日期</th>
                <th scope="col">审批结果</th>
              
                <th scope="col">审批意见</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${appHistoryPager.records}" var="appHistory" varStatus="status">
                <tr style="white-space: nowrap;">
                    <td class="tdleft">${appHistory.examineUser}</td>
                    <td class="tdleft"><fmt:formatDate value='${appHistory.examineDate}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
                    <td class="tdleft">${appHistory.examineResult}</td>
                    
                    <td class="tdleft">${appHistory.examineAdvice}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <tag:paginate offset="${appHistoryPager.offset}" total="${appHistoryPager.total}"
                      steps="${appHistoryPager.pageSize}" uri="${appHistoryPager.uri}"
                      formId=""/>
    </div>
</div>


<script src="${staticURL}/Scripts/JQuery/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script type="text/javascript">
    GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");

    /*function autoSize() {
        $("#gridview").width($(window).width());
        $("#gridview").height($(window).height());
    }

    autoSize();
    window.onresize = autoSize;*/
</script>
</body>
</html>
