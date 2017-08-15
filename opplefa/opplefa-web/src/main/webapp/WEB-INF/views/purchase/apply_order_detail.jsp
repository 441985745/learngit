<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>采购申请单</title>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
    <div id="tabletitle"
         style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
        采购申请单${applyOrder.draft=='Y'?"(草稿)":""}</div>
</div>
<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
    <input type="submit" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
    <input type="button" class="btn" value="打印" onclick="javascript:stamp('${applyOrder.cdocument}','打印');"/>
</div>
<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
    <app:approvalTag billCode="${applyOrder.cdocument}" typeId="77011"></app:approvalTag>
</div>
<div style="margin-top: 10px;">
    <div id="purchaseTab" class="easyui-tabs" >
        <div title="申请信息" id="tabsa" style="overflow:auto; padding:20px;min-width: 800px;">
                <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle"></div>
                            基本信息
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
                                <tbody>
                                <tr>
                                    <td style="text-align: right">单据号:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" value="${applyOrder.cdocument}"
                                               name="cdocument" type="text" id="txtApplyOrderId">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>所属公司:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" value="${applyOrder.companyName}"
                                               name="" type="text" id="">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>成本中心:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" value="${applyOrder.costCenter}"
                                               name="" type="text" id="">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">申请人:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" value="${applyOrder.applyUser}"
                                               name="" type="text" id="">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>归口/预算部门:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly"
                                               value="${applyOrder.budgetDepartmentName}" name="" type="text" id="">
                                    </td>
                                    <td id="tdAssemble" style="text-align: right;">是否组装:</td>
                                    <td id="tdAssembles">
                                        <input class="searchinput" readonly="readonly"
                                               value="${applyOrder.assemble=='Y'?'是':''}${applyOrder.assemble=='N'?'否':''}"
                                               name="assemble" type="text" id="assemble">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right">申请日期:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly"
                                               value="<fmt:formatDate value='${applyOrder.applyDate}'  pattern='yyyy-MM-dd'/>"
                                               name="txtApplyDate" type="text" value="" id="txtApplyDate">
                                    </td>
                                    <td style="text-align: right;"><font color="red">*</font>办公地点:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly"
                                               value="${applyOrder.officeLocation}" name="" type="text" id="">
                                    </td>

                                    <td id="tdBuilding" style="text-align: right;">是否房屋建筑物:</td>
                                    <td id="tdBuildings">
                                        <input class="searchinput" readonly="readonly"
                                               value="${applyOrder.building=='Y'?'是':''}${applyOrder.building=='N'?'否':''}"
                                               name="" type="text" id="">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><font color="red">*</font>申请人部门:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly"
                                               value="${applyOrder.applyDepartment}" name="" type="text" id="">
                                    </td>
                                    <td style="text-align: right"><font color="red">*</font>平台:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" value="${applyOrder.platform}"
                                               name="" type="text" id="">
                                    </td>
                                    <td id="tdWorkShopType" style="text-align: right;">车间类型:</td>
                                    <td id="tdWorkShopTypes">
                                        <input class="searchinput" readonly="readonly"
                                               value="${applyOrder.workShopType}" name="" type="text" id="">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><font color="red">*</font>资产类型:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly" value="${applyOrder.assetType}"
                                               name="" type="text" id="txtAssetType1">
                                    </td>
                                    <td style="text-align: right">区域:</td>
                                    <td class="tdtext">
                                        <input class="searchinput" readonly="readonly" value="${applyOrder.region}"
                                               name="" type="text" id="">
                                    </td>
                                    <td style="text-align: right">一次性费用化资产:</td>
                                    <td>
                                        <input class="searchinput" readonly="readonly"
                                               value="${applyOrder.expensing=='N'?'否':'是'}" name="" type="text" id="">
                                    </td>
                                </tr>
                                <c:if test="${applyOrder.buyer != null}">
                                    <tr>
                                        <td style="text-align: right">采购员:</td>
                                        <td class="tdtext">
                                            <input class="searchinput" readonly="readonly" value="${applyOrder.buyer}"
                                                   name="" type="text" id="">
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left">
                            <div class="imgtitle"></div>
                            申购资产需求
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                                   style="table-layout: fixed;">
                                <tbody>
                                <tr>
                                    <td class="tdtitle" style="text-align: right" colspan="2">
                                        <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                            <div>
                                                <table class="adminlist" cellspacing="0" rules="all" border="1"
                                                       id="gridDataList" style="border-collapse:collapse;">
                                                    <tbody>
                                                    <tr style="white-space:nowrap;">
                                                        <th class='tdpadding' scope="col">合并号</th>
                                                        <th class='tdpadding' scope="col">资产名称</th>
                                                        <th class='tdpadding' scope="col">规格参数</th>
                                                        <th class='tdpadding' scope="col">功能</th>
                                                        <th class='tdpadding' scope="col">资产分类</th>
                                                        <th class='tdpadding' scope="col">验收标准</th>
                                                        <th class='tdpadding' scope="col">选配件</th>
                                                        <th class='tdpadding' scope="col">本次申请数量</th>
                                                        <th class='tdpadding' scope="col">单位</th>
                                                        <th class='tdpadding' scope="col">询价单价</th>
                                                        <th class='tdpadding' scope="col">询价总价</th>
                                                        <!-- 10 -->
                                                        <th class='tdpadding' scope="col">要求到位日期</th>
                                                        <!-- <th class='tdpadding' scope="col">采购单价</th>
                                                        <th class='tdpadding' scope="col">采购总价</th>
                                                        <th class='tdpadding' scope="col">采购回复交期</th> -->
                                                        <th class='tdpadding' scope="col">折旧年限</th>
                                                        <th class='tdpadding' scope="col">编码方式</th>
                                                        <th class='tdpadding' scope="col">主编码</th>
                                                        <th class='tdpadding' scope="col">子编码</th>
                                                        <th class='tdpadding' scope="col">总账科目</th>
                                                        <!-- 20 -->
                                                        <th class='tdpadding' scope="col">成本中心</th>
                                                    </tr>
                                                    <c:forEach items="${applyOrderDetailes}" var="applyOrderDetaile"
                                                               varStatus="i">
                                                        <tr style="white-space:nowrap;">
                                                            <td class='tdpadding'>${applyOrderDetaile.mergeNumber}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.assetsName}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.specificationParameter}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.functions}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.assetClassification}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.acceptanceCriteria}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.optionalAccessories}</td>
                                                            <td class='tdpadding' id="tdDemandCount"
                                                                v='${applyOrderDetaile.demandCount}'>${applyOrderDetaile.demandCount}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.units}</td>
                                                            <td class='tdpadding'
                                                                isMoney='Y'>${applyOrderDetaile.inquiryUnitPrice}</td>
                                                            <td class='tdpadding' isMoney='Y' id="tdInquiryPrice"
                                                                v='${applyOrderDetaile.inquiryPrice}'>${applyOrderDetaile.inquiryPrice}</td>
                                                            <!-- 10 -->
                                                            <td class='tdpadding'><fmt:formatDate
                                                                    value="${applyOrderDetaile.requirementsDate}"
                                                                    pattern="yyyy-MM-dd"/></td>
                                                                <%-- <td class='tdpadding'>${applyOrderDetaile.purchaseUnitPrice}</td>
                                                                <td class='tdpadding'>${applyOrderDetaile.purchasePrice}</td>
                                                                <td class='tdpadding'>${applyOrderDetaile.procurementReturnPeriod}</td> --%>

                                                            <td class='tdpadding'>${applyOrderDetaile.depreciation}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.codingMode}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.mainAssetCode}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.secondary}</td>
                                                            <td class='tdpadding'>${applyOrderDetaile.ledgerAccount}</td>
                                                            <!-- 20 -->
                                                            <td class='tdpadding'>${applyOrderDetaile.costCenter}</td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                    <tr style="white-space:nowrap;">
                                                        <td>合计</td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td id="tdSumDemandCount"></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td id="tdSumInquiryPrice"></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
                </br>
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left" style="width: 150px">
                            <div class="imgtitle"></div>
                            附件
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="divGrid">
                                <div>
                                    <table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1"
                                           id="gvDataList" style="border-collapse:collapse;">
                                        <thead>
                                        <tr style="white-space:nowrap;">
                                            <th scope="col">
                                                <input type="checkbox" name="gvDataList$ctl01$CheckAll"
                                                       onclick="javascript:SelectAllCheckboxesByTableId(this, 'uploadTable');">
                                            </th>
                                            <th scope="col"> 文件</th>
                                            <th scope="col">上传人</th>
                                            <th scope="col">上传时间</th>
                                            <th scope="col">附件下载</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr class="noItems" align="center">
                                            <td colspan="5">没有记录！</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tbody>
                    <tr>
                        <td class="titlefontsize" align="left" style="width: 150px">
                            <div class="imgtitle"></div>
                            审批历史
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="divGrid">
                                <div>
                                    <table class="adminlist" cellspacing="0" rules="all" border="1"
                                           style="border-collapse:collapse;">
                                        <thead>
                                        <tr style="white-space: nowrap;">
                                            <th scope="col">审批人</th>
                                            <th scope="col">审批日期</th>
                                            <th scope="col">审批结果</th>
                                            <!-- <th scope="col">审批金额</th> -->
                                            <th scope="col">审批意见</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${appHistoryPager.records}" var="appHistory"
                                                   varStatus="status">
                                            <tr style="white-space: nowrap;">
                                                <td class="tdleft">${appHistory.examineUser}</td>
                                                <td class="tdleft"><fmt:formatDate value='${appHistory.examineDate}'
                                                                                   pattern='yyyy-MM-dd HH:mm:ss'/></td>
                                                <td class="tdleft">${appHistory.examineResult}</td>
                                                    <%-- <td class="tdleft">${appHistory.examineMoney}</td> --%>
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
                        </td>
                    </tr>
                    </tbody>
                </table>
        </div>
        <div title="资产明细" data-options="closable:false" style="overflow:auto;padding:20px;">
            <table id="Table6" cellpadding="0px" cellspacing="0" class="tablenoborder">
                <tbody>
                <tr>
                    <td class="titlefontsize" align="left">
                        <div class="imgtitle"></div>
                        资产明细
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                               style="table-layout: fixed;">
                            <tbody>
                            <tr>
                                <td class="tdtitle" style="text-align: right" colspan="2">
                                    <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                        <div>
                                            <table class="adminlist" cellspacing="0" rules="all" border="1"
                                                   id="gridDataList" style="border-collapse:collapse;">
                                                <tbody>
                                                <tr style="white-space:nowrap;">
                                                    <th class='tdpadding' scope="col">合并号</th>
                                                    <th class='tdpadding' scope="col">资产名称</th>
                                                    <th class='tdpadding' scope="col">规格参数</th>
                                                    <th class='tdpadding' scope="col">功能</th>
                                                    <th class='tdpadding' scope="col">验收标准</th>
                                                    <th class='tdpadding' scope="col">选配件</th>
                                                    <th class='tdpadding' scope="col">本次申请数量</th>
                                                    <th class='tdpadding' scope="col">单位</th>
                                                    <th class='tdpadding' scope="col">询价单价</th>
                                                    <!-- 10 -->
                                                    <th class='tdpadding' scope="col">要求到位日期</th>
                                                    <th class='tdpadding' scope="col">需求单号</th>
                                                </tr>
                                                <c:forEach items="${applyDemandMappingModels}"
                                                           var="applyDemandMappingModel" varStatus="i">
                                                    <tr style="white-space:nowrap;">
                                                        <td class='tdpadding'>${applyDemandMappingModel.mergeNumber}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.assetsName}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.specificationParameter}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.functions}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.acceptanceCriteria}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.optionalAccessories}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.demandCount}</td>
                                                        <td class='tdpadding'>${applyDemandMappingModel.units}</td>
                                                        <td class='tdpadding'
                                                            isMoney='Y'>${applyDemandMappingModel.inquiryUnitPrice}</td>
                                                        <td class='tdpadding'><fmt:formatDate
                                                                value="${applyDemandMappingModel.requirementsDate}"
                                                                pattern="yyyy-MM-dd"/></td>
                                                        <td class='tdpadding'><a
                                                                href="javascript:showDialog('${applyDemandMappingModel.demandOrderId}')">${applyDemandMappingModel.demandOrderId}</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>

                </tr>
                </tbody>
            </table>
        </div>


    </div>
</div>
<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script> --%>
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>
<script type="text/javascript">
    var dg = frameElement.lhgDG;//定义dg 关闭需要
    $(function () {
        /*var assetsType = $("#txtAssetType1").val();
         if(assetsType=="行政资产"){
         $("#tdBuilding").show();
         $("#tdBuildings").show();
         }
         if(assetsType=="机器设备"){
         $("#tdAssemble").show();
         $("#tdAssembles").show();
         $("#tdWorkShopType").show();
         $("#tdWorkShopTypes").show();
         }*/
        $.ajax({
            url: '${dynamicURL}/file/getFilesByDocument',
            data: {'document': $("#txtApplyOrderId").val()},
            dataType: 'json',
            type: 'POST',
            success: function (data) {
                if (data.length > 0) {
                    $('#uploadTable .noItems').remove();
                    $.each(data, function (i, item) {
                        $('#uploadTable > tbody').append('<tr id="tr" class="' + item.fileuuid + '" style="white-space:nowrap;">'
                            + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '" name=documentId />'
                            + '</td><td scope="col">' + item.fileName
                            + '</td><td scope="col">' + item.createUserName
                            + '</td><td style="width:9%">' + item.createDate
                            + '</td><td align="center" style="width:10%"><a href="' + '${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                            + '</td></tr>');
                    });
                }

            }
        });
    });
    /**
     * 数字格式转换成千分位
     */
    function fmoney(s, n) {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
        var l = s.split('.') [0].split('').reverse(),
            r = s.split('.') [1];
        var t = '';
        for (var i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
        }
        return t.split('').reverse().join('') + '.' + r;

    }
    $(function () {
        $("td[isMoney='Y']").each(function () {
            if ($(this).html() != "") {
                $(this).html(fmoney($(this).html(), 2));
            }
            ;
        });
    })
    function showDialog(id) {
        //dg.curWin.showDemandOrder(id);
        var lurl = "${dynamicURL}/demand/showDemandOrder?id=" + id + "&typeId=77001";
        var parentWindow = dg.curWin;
        new parentWindow.$.dialog({
            id: 'showForDemand',
            title: '数据查看',
            cover: true,
            page: lurl,
            rang: true,
            width: 900,
            height: 600,
            btnBar: false,
            resize: false,
            maxBtn: false
        }).ShowDialog();
    }
    var tdSumDemandCount = 0;
    $("td[id='tdDemandCount']").each(function () {
        var tdDemandCount = $.trim($(this).attr("v"));
        tdSumDemandCount += tdDemandCount - 0;
    });
    $("#tdSumDemandCount").html(tdSumDemandCount);

    var tdSumInquiryPrice = 0;
    $("td[id='tdInquiryPrice']").each(function () {
        var tdInquiryPrice = $.trim($(this).attr("v"));
        tdSumInquiryPrice += tdInquiryPrice - 0;
    });
    $("#tdSumInquiryPrice").html(fmoney(tdSumInquiryPrice, 2));

    $(document).ready(function () {
        $(window).resize(function () {
            $('#purchaseTab').tabs('resize');
        });
    });

    function stamp(id, title){

        var lurl = "${dynamicURL}/apply/printOrder?id=" + id + "&typeId=77011";
        window.open(lurl);
    }
</script>
</body>
</html>