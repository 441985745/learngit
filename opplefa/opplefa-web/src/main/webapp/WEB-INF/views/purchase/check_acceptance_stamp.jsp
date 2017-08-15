<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>打印</title>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link rel="Stylesheet" type="text/css" href="${staticURL}/discardsell/discardsell.css">
    <style media="print" type="text/css">
        .noprintbutton {
            display: none;
        }

        .noprint {
            display: none;
        }
    </style>
</head>
<body>
<div align="center">
    <input value="打印" type="button"  class="noprintbutton"  onclick="javascript:window.print()" />
    <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="wb" name="wb" width="0">
    </OBJECT>
    <input type=button name=button_print class="noprintbutton"  style="display:none;" value="打印本单据" onclick="javascript:printit()">
    <!-- <input type=button name=button_setup value="打印页面设置" onclick="javascript:printsetup();"> -->
    <input type=button name=button_show  class="noprintbutton"  value="打印预览" onclick="javascript:printpreview();">
    <input type=button name=button_fh  class="noprintbutton"  value="关闭" onclick="javascript:window.close();">
</div>
<div  style="width:100%;height:100%;">
    <div>
        <img src="${staticURL}/assetcard/img/logo1.png" style="height: 80px;">
    </div>
    <table id="table_content" class="table_content"  align="center" style="width: 90%" >
        <tr style="text-align: center;">
            <th colspan="6" class="font_class">资产验收单</th>
        </tr>
        <tr>
            <td style="text-align: left;">验收单号:&nbsp;
                ${checkAcceptance.cdocument }
            </td>
            <td style="text-align: left;">SAP采购订单号:&nbsp;
                ${spaPurchaseId}
            </td>
        </tr>
        <tr>
            <td style="text-align: left;">资产类别:&nbsp;
                ${checkAcceptance.assetsType }
            </td>
            <td style="text-align: left;" >到货日期:&nbsp;
                <fmt:formatDate value='${checkAcceptance.receiveDate }' pattern='yyyy-MM-dd'/>
            </td>
        </tr>
        <tr>
            <td style="text-align: left;">资产名称:&nbsp;
                ${checkAcceptanceDetaillist[0].assetsName }
            </td>
            <td style="text-align: left;">管理责任人:&nbsp;
                ${checkAcceptance.applyCheckUserName }
            </td>
        </tr>
        <tr>
            <td style="text-align: left;">型号规格/品牌:&nbsp;
                ${checkAcceptance.brand }
            </td>
            <td style="text-align: left;">使用部门:&nbsp;
                ${checkAcceptance.budgetDepartmentName }
            </td>
        </tr>
        <tr>
            <td style="text-align: left;">计量单位:&nbsp;
                ${checkAcceptance.units }
            </td>
            <td style="text-align: left;">成本中心:&nbsp;
                ${checkAcceptance.costCenter }
            </td>
        </tr>
        <tr>
            <td style="text-align: left;">本次验收数量:&nbsp;
                ${checkAcceptance.thisCheckCount }
            </td>
            <td style="text-align: left;">使用地点:&nbsp;
                ${checkAcceptance.officeLocation }
            </td>
        </tr>
        <tr>
            <td style="text-align: left;">质量检验:&nbsp;
                合格
            </td>
            <td style="text-align: left;">使用年限（年）:&nbsp;
                ${checkAcceptance.depreciation }
            </td>
        </tr>
        <tr>
            <td style="text-align: left;">初检合格:&nbsp;
                合格
            </td>
            <td style="text-align: left;">质保期(月）:&nbsp;
                ${checkAcceptanceDetaillist[0].recWarrantyPeriod}
            </td>
        </tr>

    </table>
    <table class="table_list" style="margin-top: 20px;">
        <tr>
            <td style="text-align: right">序号</td>
            <td style="text-align: right">资产编号</td>
            <td style="text-align: right">归口管理编号</td>
            <td style="text-align: right">出厂编号</td>
            <td style="text-align: right">制造厂家/部门</td>
            <td style="text-align: right">存放位置</td>

        </tr>
        <tbody>
        <c:forEach items="${checkAcceptanceDetaillist }" var="o" varStatus="status">
            <tr style="white-space: nowrap;">
                <td align="left">${status.index+1 }</td>
                <td align="left">${o.sapAssetsCode}</td>
                <td align="left">${o.manageapplyDepartmentCode}</td>
                <td align="left">${o.serialNumber}</td>
                <td align="left">${o.manufactureFactory}</td>
                <td align="left">${o.storageLocation}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <c:if test="${checkAcceptance.assetsType == '机器设备' || 1==1}">

        <table class="table_list" style="margin-top: 20px;">
            <tr>
                <td class='tdpadding'>1&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>设备资料是否齐全</td>
                <td class='tdpadding'>
                    <select name='machine1'disabled="disabled"id="machine1" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>



                <td class='tdpadding'>9&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>设备运行参数是否与使用说明书完全符合</td>
                <td class='tdpadding'>
                    <select name='machine9' disabled="disabled"id="machine9" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class='tdpadding'>2&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>规格型号是否与合同文件一致</td>
                <td class='tdpadding'>
                    <select name='machine2'disabled="disabled" id="machine2" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>

                <td class='tdpadding'>10&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>各性能是否与安装及试使用期时一致</td>
                <td class='tdpadding'>
                    <select name='machine10'disabled="disabled" id="machine10" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class='tdpadding'>3&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>所有电气是否张贴标签</td>
                <td class='tdpadding'>
                    <select name='machine3'disabled="disabled" id="machine3" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
                <td class='tdpadding'>11&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>配件数量是否与合同一致</td>
                <td class='tdpadding'>
                    <select name='machine11' disabled="disabled"id="machine11" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class='tdpadding'>4&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>设备铭牌是否清晰可见</td>
                <td class='tdpadding'>
                    <select name='machine4' disabled="disabled"id="machine4" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
                <td class='tdpadding'>12&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>是否有安全隐患</td>
                <td class='tdpadding' vertical-align:middle>
                    <select name='machine12'disabled="disabled" id="machine12" value=''>
                        <option value='1'>否</option>
                        <option value='0'>是</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class='tdpadding'>5&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>安全隐患处是否张贴警示标示</td>
                <td class='tdpadding'>
                    <select name='machine5'disabled="disabled" id="machine5" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
                <td class='tdpadding'>13&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>提供的零配件清单是否与机台使用型号相同</td>
                <td class='tdpadding'>
                    <select name='machine13'disabled="disabled" id="machine13" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class='tdpadding'>6&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>整机可否可以投入试运行</td>
                <td class='tdpadding'>
                    <select name='machine6'disabled="disabled" id="machine6" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
                <td class='tdpadding'>14&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>技术指标参照合同和技术要求进行验收，必要时需要添加附件</td>
                <td class='tdpadding'>
                    <select name='machine14' disabled="disabled"id="machine14" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class='tdpadding'>7&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>设备是否进行过现场操作和维护保养培训</td>
                <td class='tdpadding'>
                    <select name='machine7'disabled="disabled" id="machine7" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
                <td class='tdpadding'>15&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>生产需要提供设备试作报告</td>
                <td class='tdpadding'>
                    <select name='machine15'disabled="disabled" id="machine15" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class='tdpadding'>8&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td class='tdpadding'>各性能和配置是否与投标文件和技术要求一致</td>
                <td class='tdpadding'>
                    <select name='machine8' disabled="disabled"id="machine8" value=''>
                        <option value='0'>是</option>
                        <option value='1'>否</option>
                        <option value='2'>不适用</option>
                    </select>
                </td>
            </tr>
        </table>

    </c:if>
    <table class="table_list" style="margin-top: 20px;">
        <tr>
            <td colspan="6">
                审批历史
            </td>

        </tr>

        <tr style="white-space: nowrap;">
            <td>审批人
            </td>
            <td>审批结果
            </td>
            <td colspan="2" border="0px;">审批意见
            </td>

            <td>签字
            </td>
        </tr>
        <tr style="white-space: nowrap;">
            <td>申请人
            </td>
            <td>同意
            </td>
            <td style="word-wrap : break-word ;" width="200px;"colspan="2" border="0px;">我承诺以上业务真实，否则视为本人为违反公司规定，愿意接受公司相关规定处罚
            </td>

            <td><span>申请人签字:${checkAcceptance.applyCheckUserName }</span></br>
                <span>时间:${checkAcceptance.dateStr}</span>
            </td>
        </tr>


        <c:forEach items="${appHistoryPager}" var="appHistory" varStatus="status">

            <tr style="white-space: nowrap;">
                <td class="tdleft" align="left">${appHistory.cuserrole}</td>
                <td class="tdleft" align="left">${appHistory.cstate}</td>
                <td class="tdleft" colspan="2" align="left">${appHistory.csyslog}</td>
                <td class="tdleft" align="left"><span>${appHistory.cuserrole}签字：${appHistory.cuser}</span></br>
                    <span>时间：<fmt:formatDate value='${appHistory.ddate}' pattern='yyyy-MM-dd HH:mm:ss'/></span>
                </td>

            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>



<%--<tag:paginate offset="${appHistoryPager.offset}" total="${appHistoryPager.total}"
              steps="${appHistoryPager.pageSize}" uri="${appHistoryPager.uri}"
              formId=""/>--%>


<script type="text/javascript">
    function printsetup() {
        // 打印页面设置
        wb.execwb(8, 1);
    }
    function printpreview() {
        // 打印页面预览
        wb.execwb(7, 1);
    }
    function printit(checkId) {

        if (confirm('确定打印吗？')) {
            $.ajax({
                data:{},
                type:'post',
                dataType:'json',
                url:"${dynamicURL}/checkAccept/api/updateIsprint?checkId="+checkId,
                success:function (data) {

                }
            });
            window.print();
            wb.ExecWB(6, 1)
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
    $(function () {
        loadMachine();
    });
    function loadMachine() {
        if ("机器设备" == $('#assetsType').val()) { //车间类型
            $("#machine1").val('${checkMachine.machine1}');
            $("#machine2").val('${checkMachine.machine2}');
            $("#machine3").val('${checkMachine.machine3}');
            $("#machine4").val('${checkMachine.machine4}');
            $("#machine5").val('${checkMachine.machine5}');
            $("#machine6").val('${checkMachine.machine6}');
            $("#machine7").val('${checkMachine.machine7}');
            $("#machine8").val('${checkMachine.machine8}');
            $("#machine9").val('${checkMachine.machine9}');
            $("#machine10").val('${checkMachine.machine10}');
            $("#machine11").val('${checkMachine.machine11}');
            $("#machine12").val('${checkMachine.machine12}');
            $("#machine13").val('${checkMachine.machine13}');
            $("#machine14").val('${checkMachine.machine14}');
            $("#machine15").val('${checkMachine.machine15}');
        }
    }

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