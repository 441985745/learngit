<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>收货全流程报表</title>
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
                                <td align="right" class="tdtitle">需求单据号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="doId" value="${param.doId}" id="doId" class="colorblur"
                                           onblur="this.className='colorblur';" onfocus="this.className='colorfocus';"/>
                                </td>
                                <td align="right" class="tdtitle">申请单据号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="aoId" value="${param.aoId}" id="aoId" class="colorblur"
                                           onblur="this.className='colorblur';" onfocus="this.className='colorfocus';"/>
                                </td>
                                <td align="right" class="tdtitle">订单单据号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="purchaseOrderId" value="${param.purchaseOrderId}"
                                           id="purchaseOrderId" class="colorblur" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">sap订单号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="purchaseOrderSapId" value="${param.purchaseOrderSapId}"
                                           id="purchaseOrderSapId" class="colorblur"
                                           onblur="this.className='colorblur';" onfocus="this.className='colorfocus';"/>
                                </td>
                                <td align="right" class="tdtitle">收货单据号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="receiveGoodsId" value="${param.receiveGoodsId}"
                                           id="receiveGoodsId" class="colorblur" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';"/>
                                </td>
                                <td align="right" class="tdtitle">验收单据号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="checkAcceptId" value="${param.checkAcceptId}"
                                           id="checkAcceptId" class="colorblur" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';"/>
                                </td>
                            </tr>

                            <tr>
                                <td align="right" class="tdtitle">需求申请人:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="doApplyUser" value="${param.doApplyUser}" id="doApplyUser"
                                           class="colorblur" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';">
                                </td>
                                <td align="right" class="tdtitle">需求部门:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="doApplyDepartment" value="${param.doApplyDepartment}"
                                           id="doApplyDepartment" class="colorblur" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';">
                                </td>
                                <td align="right" class="tdtitle">需求日期:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="startDate" value="${param.startDate}" id="txtStartDate"
                                           class="Wdate" onclick="WdatePicker({skin:'ext'});">-<input
                                        type="text" name="endDate" value="${param.endDate}" id="txtEndDate"
                                        class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">所属公司:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="companyName" value="${param.companyName}" id="companyName"
                                           class="colorblur" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';">
                                </td>
                                <td align="right" class="tdtitle">成本中心号:
                                </td>
                                <td class="tdtext">
                                    <input type="text" name="costCenterCode" value="${param.costCenterCode}"
                                           id="costCenterCode" class="colorblur" onblur="this.className='colorblur';"
                                           onfocus="this.className='colorfocus';">
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
                                <th scope="col">需求单据号</th>
                                <th scope="col">需求人</th>
                                <th scope="col">需求日期</th>
                                <th scope="col">需求部门</th>
                                <th scope="col">是否有立项报告</th>
                                <th scope="col">项目名称</th>
                                <th scope="col">立项金额</th>
                                <th scope="col">收益核算方式</th>
                                <th scope="col">年收益金额</th>
                                <th scope="col">投资回收期</th>
                                <th scope="col">项目类别</th>
                                <th scope="col">需求数量</th>
                                <th scope="col">需求金额</th>
                                <th scope="col">需求审批状态</th>
                                <th scope="col">成本中心编码</th>
                                <th scope="col">成本中心</th>

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

                                <th scope="col">收货单据号</th>
                                <th scope="col">收货日期</th>
                                <th scope="col">收货数量</th>

                                <th scope="col">验收单号</th>
                                <th scope="col">验收数量</th>
                                <th scope="col">验收状态</th>
                                <th scope="col">验收日期</th>
                                <th scope="col">验收人</th>
                                <th scope="col">物料凭证号</th>
                            </tr>

                            <c:forEach items="${fullProcessPager.records}" var="fullProcessPager" varStatus="status">
                                <tr style="white-space: nowrap;">

                                    <td align="center"> ${status.count } </td>
                                    <td align="center"> ${fullProcessPager.companyName } </td>
                                    <td align="left">${fullProcessPager.doId}</td>
                                    <td align="left">${fullProcessPager.doApplyUser}</td>
                                    <td align="center"><fmt:formatDate value="${fullProcessPager.doApplyDate}"
                                                        pattern="yyyy-MM-dd"/></td>
                                    <td align="left">${fullProcessPager.doApplyDepartment}</td>
                                    <td align="center">${fullProcessPager.projectProposal == 'Y' ? "是" : "否"}</td>
                                    <td align="left">${fullProcessPager.entryName}</td>
                                    <td align="right" name="tdOrderMoney"><fmt:formatNumber value="${fullProcessPager.projectMoney}" pattern="0.00"/></td>
                                    <td align="left">${fullProcessPager.incomeAccountingMethod}</td>
                                    <td align="right" name="tdOrderMoney">${fullProcessPager.annualIncomeAmount}</td>
                                    <td align="center">${fullProcessPager.paybackPeriodOfInvestment}</td>
                                    <td align="left">${fullProcessPager.projectType}</td>
                                    <td align="right">${fullProcessPager.doDemandCount}</td>
                                    <td align="right" name="tdOrderMoney">${fullProcessPager.doIamoney}</td>
                                    <td align="left">${fullProcessPager.doCapprovalstate}</td>
                                    <td align="left">${fullProcessPager.costCenterCode}</td>
                                    <td align="left">${fullProcessPager.costCenter}</td>

                                    <td align="left">${fullProcessPager.aoId}</td>
                                    <td align="left">${fullProcessPager.aoApplyUser}</td>
                                    <td align="center"><fmt:formatDate value="${fullProcessPager.aoApplyDate}"
                                                        pattern="yyyy-MM-dd"/></td>
                                    <td align="right">${fullProcessPager.apApplyCount}</td>
                                    <td align="right" name="tdOrderMoney">${fullProcessPager.aoIamoney}</td>
                                    <td align="left">${fullProcessPager.aoCapprovalstate}</td>

                                    <td align="left">${fullProcessPager.purchaseOrderId}</td>
                                    <td align="left">${fullProcessPager.purchaseOrderSapId}</td>
                                    <td align="left">${fullProcessPager.supplierName}</td>
                                    <td align="left ">${fullProcessPager.currencyName}</td>
                                    <td align="right" name="tdOrderMoney">${fullProcessPager.taxRate}</td>
                                    <td align="left">${fullProcessPager.poApplyUser}</td>
                                    <td align="center"><fmt:formatDate value="${fullProcessPager.poApplyDate}"
                                                        pattern="yyyy-MM-dd"/></td>
                                    <td align="right">${fullProcessPager.poApplyCount}</td>
                                    <td align="right" name="tdOrderMoney">${fullProcessPager.poIamoney}</td>
                                    <td align="left">${fullProcessPager.poCapprovalstate}</td>

                                    <td align="left">${fullProcessPager.receiveGoodsId}</td>
                                    <td align="center"><fmt:formatDate value="${fullProcessPager.receiveDate}"
                                                        pattern="yyyy-MM-dd"/></td>
                                    <td align="right">${fullProcessPager.receiveGoodsCount}</td>

                                    <td align="left">${fullProcessPager.checkAcceptId}</td>
                                    <td align="right">${fullProcessPager.checkCount}</td>
                                    <td align="left">${fullProcessPager.caCapprovalstate}</td>
                                    <td align="center"><fmt:formatDate value="${fullProcessPager.caCheckDate}"
                                                        pattern="yyyy-MM-dd"/></td>
                                    <td align="left">${fullProcessPager.applyCheckUserName}</td>
                                    <td align="left">${fullProcessPager.epMaterialdocument}</td>
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
                - $("#AspNetPager").height());
    }
    autoSize();
    window.onresize = autoSize;

    $("#btnQuery").click(function () {
        var beginDate=$("#txtStartDate").val();
        var endDate=$("#txtEndDate").val();
        var d1 = new Date(beginDate.replace(/\-/g, "\/"));
        var d2 = new Date(endDate.replace(/\-/g, "\/"));

        if(beginDate!=""&&endDate!=""&&d1 >=d2)
        {
            alert("开始时间不能大于结束时间！");
            return;
        }
        window.location.href = '${dynamicURL}/report/searchReceiveFullProcess?' + $('#fullProcessForm').serialize();
    });
    $("#budgetExport").click(function () {
        var beginDate=$("#txtStartDate").val();
        var endDate=$("#txtEndDate").val();
        var d1 = new Date(beginDate.replace(/\-/g, "\/"));
        var d2 = new Date(endDate.replace(/\-/g, "\/"));

        if(beginDate!=""&&endDate!=""&&d1 >=d2)
        {
            alert("开始时间不能大于结束时间！");
            return;
        }
        window.location.href = '${dynamicURL}/report/export?' + $('#fullProcessForm').serialize();
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