<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><sitemesh:title/></title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>

    <sitemesh:head/>
    <style type="text/css">
        nav > ul.pagination li {
            display: inline;
        }
    </style>
	<script src="${staticURL}/opple/Scripts/JQuery/jquery-1.4.4.js"></script>
</head>

<body>
<%@ include file="/WEB-INF/layouts/header.jsp" %>
<sitemesh:body/>
<%@ include file="/WEB-INF/layouts/footer.jsp" %>
</body>

</html>