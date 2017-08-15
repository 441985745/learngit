<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>非PO验收打印</title>
    <style>
        @media print{
            　　.noprint{
                　　display:none
                　　}
        }
    </style>
    <script language=javascript>
        function printsetup(){
            // 打印页面设置
            wb.execwb(8,1);
        }
        function printpreview(){
            // 打印页面预览
            wb.execwb(7,1);
        }
        function printit(){
            if (confirm('确定打印吗？')){
                wb.ExecWB(6,1)
                //wb.execwb(1,1)//打开
                //swb.ExecWB(2,1);//关闭现在所有的IE窗口，并打开一个新窗口
                //wb.ExecWB(4,1)//;保存网页
                //wb.ExecWB(6,1)//打印
                //wb.ExecWB(7,1)//打印预览
                //wb.ExecWB(8,1)//打印页面设置
                //wb.ExecWB(10,1)//查看页面属性
                //wb.ExecWB(15,1)//好像是撤销，有待确认
                //wb.ExecWB(17,1)//全选
                //wb.ExecWB(22,1)//刷新
                /* wb.ExecWB(45,1)//关闭窗体无提示 */
            }
        }
    </script>
    <link rel="Stylesheet" type="text/css" href="${staticURL}/discardsell/discardsell.css">
</head>
<body>

<div align="center">
    <input value="打印" type="button" onclick="javascript:window.print()" />
    <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="wb" name="wb" width="0">
    </OBJECT>
    <input type=button name=button_print style="display:none;" value="打印本单据" onclick="javascript:printit()">
    <input type=button name=button_setup value="打印页面设置" onclick="javascript:printsetup();">
    <input type=button name=button_show value="打印预览" onclick="javascript:printpreview();">
    <input type=button name=button_fh value="关闭" onclick="javascript:window.close();">
</div>
<div  style="width:100%;height:100%;">
    <%--<div>--%>
        <%--<table class="table">--%>
            <%--<tr >--%>
                <%--<td style="align:left;width: 23%;" ></td>--%>
                <%--<td style="text-align: center;width: 54%;" class="font_class" ></td>--%>
                <%--<td style="text-align: center;width: 23%;font-size: 12px;" >流程单号:MRMS${paymentOrder.cdocument}</td>--%>
            <%--</tr>--%>
            <%--<tr >--%>
                <%--<td style="align:left;" ><img src="${staticURL}/assetcard/img/logo1.png" style="height: 50px;"></td>--%>
                <%--<td style="text-align: center;" class="font_class" >付款核销单</td>--%>
                <%--<td style="text-align: right;" ><img src="${url}" style="height: 50px;width:300px;"></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</div>--%>
    <div>
        <table class="table_list"  style="border-top-width: 0;width: 100%;height: 100%;">
            <tr>
                <td style="text-align: right; width: 150px;height: 28px;">单据号:</td>
                <td>
                    ${notPoPaymentOrder.cdocument}
                </td>
                <td style="text-align: right;"><font color="red" >*</font>所属部门:</td>
                <td>
                    ${notPoPaymentOrder.departmentName}
                </td>
                <td style="text-align: right; "><span style="color: red">*</span>办公地点:</td>
                <td>
                    ${notPoPaymentOrder.officeLocation}
                </td>
                <%-- <td style="text-align: right" >税率:</td>
                <td class="tdtext">
                    <input readonly class='searchinput'  type='text' value='<fmt:formatNumber type="number" value="${purchaseOrder.taxRate*100}" maxFractionDigits="0"/>%' id='cc'>
                    <input readonly class='searchinput' name='taxRate' type='hidden' value='${purchaseOrder.taxRate}' id='taxRate'>
                    <input readonly='readonly' class='searchinput' name='taxRateCode' type='hidden' value='${purchaseOrder.taxRateCode}' id='taxRateCode'>
                </td> --%>
            </tr>
            <tr>
                <td style="text-align: right;width: 150px;height: 28px;">申请人:</td>
                <td>
                    ${notPoPaymentOrder.applyUser}
                </td>
                <td style="text-align: right;">所属公司:</td>
                <td class="tdtext">
                    ${notPoPaymentOrder.companyName}
                </td>
                <td style="text-align: right;"><font color="red" >*</font>币种:</td>
                <td>
                    ${notPoPaymentOrder.currencyCode}
                </td>
                <%-- <td style="text-align: right; width: 90px;">币种:</td>
                <td>
                    <input readonly='readonly' class='searchinput' name='currencyName' type='text' value='${notPoPaymentOrder.currencyName}' id='currencyName'>
                </td> --%>
            </tr>
            <tr>
                <td style="text-align: right;width: 150px;height: 28px;">申请日期:</td>

                <td class="tdtext">
                    ${notPoPaymentOrder.applyDate}
                </td>
                <td style="text-align: right;"><font color="red" >*</font>成本中心:</td>
                <td class="tdtext" >
                    ${notPoPaymentOrder.costCenter}
                </td>
                <td style="text-align: right;" >汇率:</td>
                <td class="tdtext">${notPoPaymentOrder.exchangeRate}</td>
            </tr>
            <tr>
                <td style="text-align: right; width: 150px;height: 28px;"><span style="color: red">*</span>申请事由:</td>
                <td>
                    ${notPoPaymentOrder.applyFor}
                </td>
                <td style="text-align: right;">所属利润中心:</td>
                <td>
                    ${notPoPaymentOrder.profitCenter}
                </td>
                <td style="text-align: right" >备注:</td>
                <td class="tdtext">${notPoPaymentOrder.remark}</td>
            </tr>
        </table>
        <table class="table_list"  style="border-top-width: 0;boder-bottom:none;">
            <tr>
                <td style="text-align: right; width: 150px;height: 28px;">挂账人:</td>
                <td>
                    ${notPoPaymentOrder.paymentPeople}
                </td>
                <td style="text-align: right; width: 150px;height: 28px"><font color="red" >*</font>预计核销时间:</td>
                <td class="tdtext" >
                    ${notPoPaymentOrder.predictTime}
                </td>
                <td style="text-align: right;width: 150px;height: 28px" >申请金额(原币):</td>
                <td class="tdtext">
                    ${notPoPaymentOrder.applyMoney}
                    <%-- <input readonly class='searchinput' name='taxRate' type='hidden' value='${purchaseOrder.taxRate}' id='taxRate'>
                    <input readonly='readonly' class='searchinput' name='taxRateCode' type='hidden' value='${purchaseOrder.taxRateCode}' id='taxRateCode'> --%>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 150px;height: 28px">供应商编码:</td>
                <td>
                    ${notPoPaymentOrder.supplierCode}
                </td>
                <td style="text-align: right;width: 150px;height: 28px">联系人:</td>
                <td class="tdtext">
                    ${notPoPaymentOrder.contacts}
                </td>
                <td style="text-align: right; width: 150px;height: 28px">申请金额(本币):</td>
                <td>
                    ${notPoPaymentOrder.applyMoneyLocal}
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 150px;height: 28px">供应商名称:</td>
                <td>
                    ${notPoPaymentOrder.supplierName}
                </td>
                <td style="text-align: right;width: 150px;height: 28px"><font color="red" >*</font>收款账号:</td>
                <td class="tdtext" >
								<span id="spanCostCenter">
                                    ${notPoPaymentOrder.bankNumber}
                                </span>
                </td>
                <td style="text-align: right; width: 150px;height: 28px">批准金额(原币):</td>
                <td>
                    ${notPoPaymentOrder.iamoney}
                </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 150px;height: 28px"><span style="color: red">*</span>国家:</td>
                <td>
                    ${notPoPaymentOrder.country}
                </td>
                <td style="text-align: right; width: 150px;height: 28px">开户行名称:</td>
                <td>
                    ${notPoPaymentOrder.bankName}
                </td>
                <td style="text-align: right;width: 150px;height: 28px" >批准金额(本币):</td>
                <td class="tdtext">${notPoPaymentOrder.iamoneyLocal}</td>
            </tr>
            <tr>
                <td style="text-align: right; width: 150px;height: 28px"><span style="color: red">*</span>城市:</td>
                <td>
                    ${notPoPaymentOrder.city}
                </td>
                <td style="text-align: right; width: 150px;height: 28px">开户行号:</td>
                <td>
                    ${notPoPaymentOrder.bankCode}
                </td>
            </tr>
        </table>
        <table class="table_list" style="border-top-width: 0;">
            <tr >
                <td style="text-align: center;border-top-width: 0;"> 文件</td>
                <td style="text-align: center;border-top-width: 0;">上传人</td>
                <td style="text-align: center;border-top-width: 0;">上传时间</td>
            </tr>
            <c:choose>
                <c:when test="${not empty paymentOrder.listFile}">
                    <c:forEach items="${paymentOrder.listFile}" var="assetPic" >

                        <tr>
                            <td>${assetPic.fileName}</td>
                            <td>${assetPic.createUserName}</td>
                            <td>${assetPic.createDate}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td style="text-align: center;" colspan="3">没有数据！</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
        <c:if test="${paymentOrder.paymentType=='预付款'&&paymentOrder.capprovalstate=='已完成'}">
            <table class="table_list" style="border-top-width: 0;border-bottom-width: 0;">
                <tr>
                    <td style="text-align:right;border-top-width: 0;border-bottom-width: 0;">是否手工凭证:</td>
                    <td style="border-top-width: 0;border-bottom-width: 0;">
                            ${paymentOrder.isManualPrepaymentDocument=='Y'?"是":"否"}
                    </td>
                    <td style="text-align:right;border-top-width: 0;border-bottom-width: 0;">预付凭证号:</td>
                    <td style="border-top-width: 0;border-bottom-width: 0;">${paymentOrder.prepaymentDocumentNumber}</td>
                </tr>
            </table>
        </c:if>
        <c:if test="${paymentOrder.paymentType=='验收款'&&paymentOrder.paymentRatio==100&&paymentOrder.capprovalstate=='已完成'}">
            <table class="table_list" style="border-top-width: 0;border-bottom-width: 0;">
                <tr>
                    <td style="text-align:right;border-top-width: 0;border-bottom-width: 0;">会计凭证:</td>
                    <td style="border-top-width: 0;border-bottom-width: 0;">${paymentOrder.paymentVoucherNumber}</td>
                    <td style="text-align:right;border-top-width: 0;border-bottom-width: 0;">发票号码:</td>
                    <td style="border-top-width: 0;border-bottom-width: 0;">${paymentOrder.invoiceNo}</td>
                </tr>
            </table>
        </c:if>
        <c:if test="${paymentOrder.paymentType=='验收款'&&paymentOrder.paymentRatio!=100&&paymentOrder.capprovalstate=='已完成'}">
            <table class="table_list" style="border-top-width: 0;border-bottom-width: 0;">
                <tr>
                    <td style="text-align:right;border-top-width: 0;border-bottom-width: 0;">会计凭证:</td>
                    <td style="border-top-width: 0;border-bottom-width: 0;">${paymentOrder.paymentVoucherNumber}</td>
                    <td style="text-align:right;border-top-width: 0;border-bottom-width: 0;">发票号码:</td>
                    <td style="border-top-width: 0;border-bottom-width: 0;">${paymentOrder.invoiceNo}</td>
                    <td style="text-align:right;border-top-width: 0;border-bottom-width: 0;;">是否手工凭证:</td>
                    <td style="border-top-width: 0;border-bottom-width: 0;">
                            ${paymentOrder.isManualReceiptVoucher=='Y'?"是":"否"}
                    </td>
                    <td style="text-align:right;border-top-width: 0;border-bottom-width: 0;">验收凭证:</td>
                    <td style="border-top-width: 0;border-bottom-width: 0;">${paymentOrder.cvoucheId}</td>
                </tr>
            </table>
        </c:if>
        <table class="table_list" style="border-bottom: none">
            <tr>
                <td style="text-align: center;">审批人</td>
                <td style="text-align: center;">审批结果</td>
                <td style="text-align: center;">审批意见</td>
                <td style="text-align: center;">签字</td>
            </tr>
            <tr>
                <td >申请人</td>
                <td >同意</td>
                <td >我承诺以上业务内容、核销凭证真实，无虚假，否则视为本人违反公司规定，愿意接受公司相关规定处罚。 </td>
                <td >
                    申请人签字:${paymentOrder.applyUser}<br/>
                    时间:<fmt:formatDate value='${paymentOrder.applyDate}' type="both" />
                </td>
            </tr>
            <c:forEach items="${approveHistoryCheckPrints}" var="appHistory" varStatus="status">
                <tr >
                    <td >${appHistory.cuserrole}</td>
                    <td >${appHistory.cstate}</td>
                    <td >${appHistory.csyslog}</td>
                    <td >${appHistory.cuserrole}签字:${appHistory.cuser}<br/>
                        时间:<fmt:formatDate value='${appHistory.ddate}' type="both" />
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
<script type="application/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
<link href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css" type="text/css" rel="stylesheet" />
<link href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css" type="text/css" rel="stylesheet" /> --%>
<%-- <script src="${staticURL}/Scripts/UserInfo/UserInfo.js" type="text/javascript"></script> --%>
<%-- <script src="${staticURL}/Scripts/Purchase/PaymentOrderAdd.js" type="text/javascript"></script> --%>
<script type="text/javascript">
    var dg = frameElement.lhgDG;//定义dg 关闭需要
    $(function(){
        $("td[name='price']").each(function(){
            if($(this).html()!=""){
                $(this).html(fmoney($(this).html(),2));
            };
        });
    })
    /* 	$("#checkBar").click(function(){
     var cdocument = $("#cdocument").val();
     $.ajax({
     url: "${sessionScope.cdUrl}${dynamicURL}/payment/api/checkBar",
     type: "POST",
     // contentType: "application/json",
     data:{cdocument : cdocument},
     success: function (data) {
     if (data.success){
     window.location.href=data.successMessage;
     } else {
     alert(data.errorMessages.join());
     }

     }
     });
     }); */
    //发票检验弹窗
    /* 	$("#invoiceCheck").click(function(){

     var top = "300px";
     var left = "50px";
     $('#budgetWindow').window('open').window('resize',{width:'650px',height:'330px',top: top,left:left});
     });
     $("#back").click(function(){
     $('#budgetWindow').window('close');
     }); */
    /**
     * 数字格式转换成千分位
     */

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