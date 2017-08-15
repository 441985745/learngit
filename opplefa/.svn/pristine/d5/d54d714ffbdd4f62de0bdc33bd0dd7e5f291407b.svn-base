<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>付款全流程报表</title>
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
                <form action="fullProcess" id="fullProcessForm">
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
                                <td align="right" class="tdtitle">申请单据号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="aoid" value="${param.aoid}" id="aoid" class="colorblur"
                                           onblur="this.className='colorblur';" onfocus="this.className='colorfocus';"/>
                                </td>
                                <td align="right" class="tdtitle">订单单据号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="purchaseOrderId" value="${param.purchaseOrderId}"
                                           id="purchaseOrderId" class="colorblur" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';"/>
                                </td>
                                <td align="right" class="tdtitle">sap订单号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="purchaseOrderSapId" value="${param.purchaseOrderSapId}"
                                           id="purchaseOrderSapId" class="colorblur"
                                           onblur="this.className='colorblur';" onfocus="this.className='colorfocus';"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">采购申请人:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="aoapplyuser" value="${param.aoapplyuser}" id="aoapplyuser" class="colorblur"
                                           onblur="this.className='colorblur';" onfocus="this.className='colorfocus';"/>
                                </td>
                                <td align="right" class="tdtitle">采购申请日期:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="startDate" value="${param.startDate}" id="txtStartDate"
                                           class="Wdate" onclick="WdatePicker({skin:'ext'});">-<input
                                        type="text" name="endDate" value="${param.endDate}" id="txtEndDate"
                                        class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                </td>
                                <td align="right" class="tdtitle">所属公司:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="companyName" value="${param.companyName}" id="companyName" class="colorblur"
                                           onblur="this.className='colorblur';" onfocus="this.className='colorfocus';"/>
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
                <auth:authTag code="Export">
                <input type="button" name="" value="导出" id="budgetExport" class="btn">
                </auth:authTag>
            </td>
        </tr>
        <tr>
            <td valign="top">
                <div id="gridview">
                    <div>
                        <table class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border-collapse: collapse;">
                            <tbody>
                            <tr style="white-space: nowrap;">
                                <th scope="col">序号</th>
                                <th scope="col">所属公司</th>
                                <th scope="col">采购申请单号</th>
                                <th scope="col">采购申请人</th>
                                <th scope="col">采购申请日期</th>
                                <th scope="col">采购申请数量</th>
                                <th scope="col">采购申请金额</th>
                                <th scope="col">采购申请审批状态</th>

                                <th scope="col">订单单据号</th>
                                <th scope="col">SAP订单号</th>
                                <th scope="col">供应商</th>
                                <th scope="col">币种</th>
                                <th scope="col">税率</th>
                                <th scope="col">订单申请人</th>
                                <th scope="col">订单申请日期</th>
                                <th scope="col">订单数量</th>
                                <th scope="col">订单金额</th>
                                <th scope="col">订单审批状态</th>

                                <th scope="col">付款单号</th>
                                <th scope="col">付款类别</th>
                                <th scope="col">付款数量</th>
                                <th scope="col">付款金额</th>
                                <th scope="col">付款日期</th>
                                <th scope="col">审批状态</th>
                                <th scope="col">sap凭证号</th>

                            </tr>

                            <c:forEach items="${fullProcessPager.records}" var="fullProcessPager" varStatus="status">
                                <tr style="white-space: nowrap;">

                                    <td align="center"> ${status.count } </td>
                                    <td align="center"> ${fullProcessPager.companyName } </td>
                                    <td align="left">${fullProcessPager.aoid}</td>
                                    <td align="left">${fullProcessPager.aoapplyuser}</td>
                                    <td align="center"><fmt:formatDate value="${fullProcessPager.aoapplydate}"
                                                        pattern="yyyy-MM-dd"/></td>
                                    <td align="right">${fullProcessPager.aoApplyCount}</td>
                                    <td align="right" name="tdOrderMoney">${fullProcessPager.aoIamoney}</td>
                                    <td align="left">${fullProcessPager.aoCapprovalstate}</td>

                                    <td align="left">${fullProcessPager.purchaseOrderId}</td>
                                    <td align="left">${fullProcessPager.purchaseOrderSapId}</td>
                                    <td align="left">${fullProcessPager.supplierName}</td>
                                    <td align="left">${fullProcessPager.currencyName}</td>
                                    <td align="right" name="tdOrderMoney">${fullProcessPager.taxRate}</td>
                                    <td align="left">${fullProcessPager.poApplyUser}</td>
                                    <td align="center"><fmt:formatDate value="${fullProcessPager.poApplyDate}"
                                                        pattern="yyyy-MM-dd"/></td>
                                    <td align="right">${fullProcessPager.poApplyCount}</td>
                                    <td align="right" name="tdOrderMoney">${fullProcessPager.poIamoney}</td>
                                    <td align="left">${fullProcessPager.poCapprovalstate}</td>

                                    <td align="left">${fullProcessPager.payId}</td>
                                    <td align="left">${fullProcessPager.advancePayment}</td>
                                    <td align="right">${fullProcessPager.advancePayCount}</td>
                                    <td align="right" name="tdOrderMoney"><fmt:formatNumber value="${fullProcessPager.advancePayPrice}" pattern="0.00"/></td>
                                    <td align="center"><fmt:formatDate value="${fullProcessPager.advanceApplyDate}"
                                                        pattern="yyyy-MM-dd"/></td>
                                    <td align="left">${fullProcessPager.advancePoCapprovalstate}</td>
                                    <td align="left">${fullProcessPager.advancePrepayment}</td>


                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${fullProcessPager.offset}" total="${fullProcessPager.total}"
                                      steps="${fullProcessPager.pageSize}" uri="${fullProcessPager.uri}"
                                      formId="fullProcessForm"/>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
    QueryFieldSetStauts('t_ShipInportInfo', 'fset_ShipInportInfo', 'img_ShipInportInfo', document.getElementById("hfQueryStatus").value);
    function autoSize() {
        $("#gridview").width($(window).width());
        $("#gridview").height(
                $(window).height() - $("#Query").height()
                - $("#Button").height()
                - $("#AspNetPager").height())-1;
    }
    autoSize();
    window.onresize = autoSize;

    $("#btnQuery").click(function () {

        window.location.href = '${dynamicURL}/report/searchPaymentFullProcess?' + $('#fullProcessForm').serialize();
    });
    $("#budgetExport").click(function () {

        window.location.href = '${dynamicURL}/report/exportFullPay?' + $('#fullProcessForm').serialize();
    });
    $(function(){
        $("td[name='tdOrderMoney']").each(function(){
            if($(this).html()!=""){
                $(this).html(fmoney($(this).html(),2));
            };
        });
    })
    /**
     * 数字格式转换成千分位
     */
    function fmoney(s, n)

    {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
        var l = s.split('.') [0].split('').reverse(),
            r = s.split('.') [1];
        var  t = '';
        for (var i = 0; i < l.length; i++)
        {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
        }
        return t.split('').reverse().join('') + '.' + r;

    }

</script>
</body>
</html>