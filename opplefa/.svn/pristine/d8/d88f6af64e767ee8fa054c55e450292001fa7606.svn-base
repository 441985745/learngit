<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<html>
<head>
    <title>预算体列表</title>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet" />
</head>
<body>
<td style="background-color:#fff;text-align:left;">
    <form id="costCenterForm">
        <table cellpadding="1" bgcolor="#F9F9F9" class="adminlist">
            <tbody>
            <tr>
                <td height="26" width="20%" style="white-space:nowrap;" align="right">预算体编码：</td>
                <td>
                    <input name="deptCode" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                           onblur="this.className='inputbtnRefView';" style="width:120px;" value="${param.deptCode}">

                </td>
  	
                <td height="26" width="20%" style="white-space:nowrap;" align="right">预算体名称：</td>

                <td>
                    <input name="deptName" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                           onblur="this.className='inputbtnRefView';" style="width:120px;" value="${param.deptName}">
                    <input name="parentWindowId" value="${parentWindowId}" type="hidden">
                   <%--  <input name="companyCode" value="${param.companyCode}" type="hidden"> --%>
                </td>
           		 <td height="26" width="20%" style="white-space:nowrap;" align="right">公司编码：</td>
                <td>
                    <input name="companyCode" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                           onblur="this.className='inputbtnRefView';" style="width:120px;" value="${param.companyCode}" readonly="readonly">

                </td>
               	 <td height="26" width="20%" style="white-space:nowrap;" align="right">成本中心：</td>
                <td>
                    <input name="costCenterCode" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                           onblur="this.className='inputbtnRefView';" style="width:120px;" value="${param.costCenterCode}" readonly="readonly">

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
            <th align="center" scope="col" style="width:30%;">预算体编码</th>
            <th align="center" scope="col" style="width:70%;">预算体名称</th>
            <th align="center" scope="col" style="width:70%;">公司编码</th>
            <th align="center" scope="col" style="width:70%;">成本中心编码</th>
            <th align="center" scope="col" style="width:70%;">成本中心名称</th>
            </thead>
            <tbody>
            <c:forEach items="${listDepCostCenterPager.records}" var="costCenter" varStatus="status">
                <tr>
                    <td align="left">${costCenter.deptCode}</td>
                    <td align="left">${costCenter.deptName}</td>
                    <td align="left">${costCenter.companyCode}</td>
                    <td align="left">${costCenter.costCenterCode}</td> 
                  	<td  align="left"> ${costCenter.costCenterName}</td>  
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <tag:paginate offset="${listDepCostCenterPager.offset}" total="${listDepCostCenterPager.total}"
                  steps="${listDepCostCenterPager.pageSize}" uri="${listDepCostCenterPager.uri}"
                  formId="positionForm" parentWindowId="${listDepCostCenterPager.parentWindowId}"/>
</td>
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
        $("#gridview").height($(window).height() - $("#buttons").height() - $("#costCenterForm").height() - $("#pagination_container").height());
    }

    autoSize();
    window.onresize = autoSize;

    (function () {

        var dialogId = "${param.parentWindowId}";

        $('#queryBtn').click(function () {
            window.location.href = "${dynamicURL}/assetAllocation/searchPagerList?" + $('#costCenterForm').serialize();
        });

        $('#userTable > tbody > tr').click(function() {
            setParent(this.children[0].innerText, this.children[1].innerText,this.children[3].innerText,this.children[4].innerText,dialogId);
        });

        function setParent(depCode, depName,costCenterCode,costCenterName,dialogId) {
            if (dialogId != '') {
                if (dg.iDG(dialogId)) {
                    dg.iWin(dialogId).fillDepartMent(depCode, depName,costCenterCode,costCenterName);
                    dg.cancel();
                } else {
                    alert('请先打开父窗口');
                }
            } else {
                alert('请先打开父窗口');
            }
            dg.cancel();
        }
    })();

</script>
</body>
</html>
