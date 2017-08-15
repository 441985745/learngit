<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <title>资产收货单</title>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">

</head>
<body>
<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
    <div id="tabletitle"
         style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
        资产收货单
    </div>
</div>
<div id="Div2" style="margin-left: 10px; margin-top: 0px;">
    <input type="submit" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
</div>
<%--<div id="Div2" style="margin-left: 0px; margin-top: 0px;">--%>
<%--&lt;%&ndash;<app:approvalTag billCode="${receiveGoods.cdocument}" typeId="77077"></app:approvalTag>&ndash;%&gt;--%>
<%--</div>--%>

<div style="margin-top: 5px;">

    <div id="receiveGoodsTab" style="width:100%; margin: 0 auto;">
        <div title="收货信息" id="tabsa" style="overflow:auto; padding:10px;">
            <div>
                <form action="receiveGoods" id="receiveGoodsForm">
                    <table id="addReceiveGoodsTable" border="0" cellpadding="3px" cellspacing="0" width="90%">
                        <tbody>
                        <tr>
                            <td align="right" class="tdtitle">采购员:
                            </td>
                            <td class="tdtext">
                                <input name="buyerName" readonly value="${receiveGoods.buyerName }" type="text" id="buyerName"
                                       class="colorblur">
                                <input name="buyerCode"  readonly value="${receiveGoods.buyerCode }" type="hidden" id="buyerCode"
                                       class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">收货编码:
                            </td>
                            <td class="tdtext">
                                <input name="cdocument"  readonly value="${receiveGoods.cdocument}" type="text" id="cdocument"
                                       class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">供应商:
                            </td>
                            <td class="tdtext">
                                <input name="supplierName" readonly  value="${receiveGoods.supplierName }" type="text"
                                       id="supplierName" class="colorblur">
                            </td>
                        </tr>
                        <tr>
                            <td align="right" class="tdtitle">公司名称:
                            </td>
                            <td class="tdtext">
                                <input name="companyName" readonly value="${receiveGoods.companyName }" type="text"
                                       id="companyName" class="colorblur">
                                <input name="companyCode" value="${receiveGoods.companyCode }" type="hidden"
                                       id="companyCode" class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">成本中心:
                            </td>
                            <td class="tdtext">
                                <input name="costCenter"  readonly value="${receiveGoods.costCenter }" type="text" id="costCenter"
                                       class="colorblur">
                                <input name="costCenterCode"  readonly value="${receiveGoods.costCenterCode }" type="hidden"
                                       id="costCenterCode" class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">资产类型:
                            </td>
                            <td class="tdtext">
                                <input name="assetsType"  readonly value="${receiveGoods.assetsType }" type="text" id="assetsType"
                                       class="colorblur">
                            </td>
                        <tr>
                            <td align="right" class="tdtitle">申请部门所属平台:
                            </td>
                            <td class="tdtext">
                                <input name="platform"  readonly value="${receiveGoods.platform }" type="text" id="platform"
                                       class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">采购员备注:
                            </td>
                            <td class="tdtext">
                                <input name="buyerRemark" readonly  value="${receiveGoods.buyerRemark }" type="text"
                                       id="buyerRemark" class="colorblur">
                            </td>

                            <td align="right" class="tdtitle">供应商编码:
                            </td>
                            <td class="tdtext">
                                <input name="supplierCode"  readonly value="${receiveGoods.supplierCode }" type="text"
                                       id="supplierCode" class="colorblur">
                            </td>

                        </tr>
                        <td align="right" class="tdtitle">订单编码:
                        </td>
                        <td class="tdtext">
                            <input name="purchaseId"  readonly value="${receiveGoods.purchaseId}" type="text" id="purchaseId"
                                   class="colorblur">
                        </td>

                        <td align="right" class="tdtitle">抬头文本说明:
                        </td>
                        <td class="tdtext">
                            <input name="headerTextDescription" readonly  value="${receiveGoods.headerTextDescription }"
                                   type="text" id="headerTextDescription" class="colorblur">
                        </td>
                        <!-- 抬头文本说明 （订单头） -->
                        <td align="right" class="tdtitle">合同号:
                        </td>
                        <td class="tdtext">
                            <input name="contractNo"  readonly value="${receiveGoods.contractNo }" type="text" id="contractNo"
                                   class="colorblur">
                        </td>
                        <!-- 合同号（手填） -->
                        </tr>
                        <tr>
                            <td align="right" class="tdtitle">申请部门:
                            </td>
                            <!-- 申请明细 抓取-->
                            <td class="tdtext">
                                <input name="applyDepartment"  readonly value="${receiveGoods.applyDepartment }" type="text"
                                       id="applyDepartment" class="colorblur">
                                <input name="applyDepartmentCode" readonly  value="${receiveGoods.applyDepartmentCode }"
                                       type="hidden" id="applyDepartmentCode" class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">办公地点:
                            </td>
                            <td class="tdtext">
                                <input name="officeLocation" readonly  value="${receiveGoods.officeLocation}" type="text"
                                       id="officeLocation" class="colorblur">
                            </td>
                            <!-- (采购申请的部门 -->
                           <%-- <td align="right" class="tdtitle">需求部门:
                            </td>
                            <td class="tdtext">
                                <input name="budgetDepartmentName" readonly  value="${receiveGoods.budgetDepartmentName }"
                                       type="text" id="budgetDepartmentName" class="colorblur">
                                <input name="budgetDepartmentCode"  readonly value="${receiveGoods.budgetDepartmentCode }"
                                       type="hidden" id="budgetDepartmentCode" class="colorblur">
                            </td>
                            <td align="right" class="tdtitle">办公地点:
                            </td>
                            <td class="tdtext">
                                <input name="officeLocation"  readonly value="${receiveGoods.officeLocation}" type="text"
                                       id="officeLocation" class="colorblur">
                            </td>
                        </tr>--%>
                        </tbody>
                    </table>
                </form>

                </br>
                <div style="width:90%; margin: 0 auto;">
                    <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" class="tablenoborder">
                        <tbody>
                        <tr>
                            <td colspan="4">
                                <form action="receiveGoodsDetail" id="receiveGoodsDetailForm">
                                    <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                                           style="table-layout: fixed;">
                                        <tbody>
                                        <tr>
                                            <td class="titlefontsize" align="left">
                                                <div class="imgtitle">
                                                </div>
                                                汇总信息
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="tdtitle" style="text-align: right" colspan="2">
                                                <div id="div1" class="divGrid"
                                                     style="overflow-x: scroll; overflow-y: visible;">
                                                    <div>
                                                        <table id="table_add_detail1" class="adminlist" cellspacing="0"
                                                               rules="all" border="1" id="gridDataList1"
                                                               style="border-collapse:collapse;">
                                                            <tr style="white-space:nowrap;">
                                                                <th class='tdpadding' scope="col">收货汇总</th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>资产名称
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>单位
                                                                </th><!-- 申请行里取 -->
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>订单数量
                                                                </th>

                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>已收货数量
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>本次收货数量
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>税率
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>单价（含税）
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>总价（含税）
                                                                </th>
                                                                <th class='tdpadding' style="display: none" scope="col"><font
                                                                        color="red">*</font>总账科目
                                                                </th><!-- 申请单带出 -->
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>资产分类
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>交货日期
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>计划验收日期
                                                                </th><!-- 手选的 -->
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>质保周期（月）
                                                                </th>
                                                                <%--
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>存放位置
                                                                </th>--%>
                                                            </tr>
                                                            <tbody id="tbody_add_detail">
                                                            <c:forEach items="${receiveGoodsDetail }" var="o"
                                                                       varStatus="status">
                                                                <tr style="white-space: nowrap;">
                                                                    <td style='text-align:center'>${status.index+1 }</td>
                                                                    <td>${o.assetsName}</td>
                                                                    <td>${o.units}</td>
                                                                    <td>${o.applyCount}</td>
                                                                    <td>${o.goodsCount}</td>
                                                                    <td>${o.thisGoodsCount}</td>
                                                                    <td>${o.tax}</td>
                                                                    <td>${o.purchaseUnitPrice}</td>
                                                                    <td>${o.purchasePrice}</td>
                                                                    <td style="display: none">${o.ledgerAccount}</td>
                                                                    <td>${o.assetsType}</td>
                                                                    <td><fmt:formatDate value='${o.deliveryDate}'
                                                                                        pattern='yyyy-MM-dd'/></td>
                                                                    <td><fmt:formatDate value='${o.requireCheckDate}'
                                                                                        pattern='yyyy-MM-dd'/></td>
                                                                    <td>${o.recWarrantyPeriod}</td>
                                                                        <%--<td>${o.storageLocation}</td>--%>
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
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                </br>
                <div style="width:90%; margin: 0 auto;">
                    <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" class="tablenoborder"  >
                        <tbody>
                        <tr>
                            <td class="titlefontsize" align="left">
                                <div class="imgtitle">
                                </div>
                                资产信息
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <form action="receiveGoodsDetail" id="receiveGoodsDetailForm">
                                    <table id="Table10" width="100%" border="0" cellpadding="0" cellspacing="0"
                                           style="table-layout: fixed;">
                                        <tbody>
                                        <tr>
                                            <td class="tdtitle" style="text-align: right" colspan="2">
                                                <div id="div1" class="divGrid"
                                                     style="overflow-x: scroll; overflow-y: visible;">
                                                    <div>
                                                        <table id="table_add_detail" class="adminlist" cellspacing="0"
                                                               rules="all" border="1" id="gridDataList"
                                                               style="border-collapse:collapse;">
                                                            <tr style="white-space:nowrap;">
                                                                <th class='tdpadding' scope="col">收货明细</th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>需求单号
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>资产名称
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>资产编号
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>型号规格/品牌
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>功能
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font color="red">*</font>需求部门编码
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font color="red">*</font>需求部门
                                                                <th class='tdpadding' scope="col"><font color="red">*</font>成本中心编码
                                                                <th class='tdpadding' scope="col"><font color="red">*</font>成本中心
                                                                </th>
                                                                <th class='tdpadding' scope="col"><font
                                                                        color="red">*</font>验收人
                                                                </th>
                                                               <%-- <th class='tdpadding' scope="col"><font
                                                                    color="red">*</font>质保周期（月）
                                                                </th>--%>
                                                                <th class='tdpadding' scope="col">存放位置
                                                                </th>
                                                            </tr>
                                                            <tbody id="tbody_add_detail">
                                                            <c:forEach items="${purchaseCommon }" var="o"
                                                                       varStatus="status">
                                                                <tr style="white-space: nowrap;">
                                                                    <td style='text-align:center'>${status.index+1 }</td>
                                                                    <td><a href="javascript:showDemandOrder('${o.demandId}')" >${o.demandId}</a></td>
                                                                    <td>${o.assetsName}</td>
                                                                    <td>${o.sapAssetsCode}</td>
                                                                    <td>${o.brand}</td>
                                                                    <td>${o.functions}</td>
                                                                    <td>${o.budgetDepartmentCode}</td>
                                                                    <td>${o.budgetDepartmentName}</td>
                                                                    <td>${o.costCenterCode}</td>
                                                                    <td>${o.costCenter}</td>
                                                                    <td>${o.checkApplyUser}</td>
                                                              <%--      <td>${o.recWarrantyPeriod}</td>--%>
                                                                    <td>${o.storageLocation}</td>
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
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div style="width:90%; margin: 0 auto;">
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
                                        <table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList" style="border-collapse:collapse;">
                                            <thead>
                                            <tr style="white-space:nowrap;">
                                                <th scope="col">
                                                    <input  type="checkbox" name="gvDataList$ctl01$CheckAll" onclick="javascript:SelectAllCheckboxesByTableId(this, 'uploadTable');">
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
                </div>

              <%--  <div style="margin-top: 10px;display: none;">
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
                                                <th scope="col">审批金额</th>
                                                <th scope="col">审批意见</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${appHistoryPager.records}" var="appHistory"
                                                       varStatus="status">
                                                <tr style="white-space: nowrap;">
                                                    <td class="tdleft">${appHistory.examineUser}</td>
                                                    <td class="tdleft"><fmt:formatDate value='${appHistory.examineDate}'
                                                                                       pattern='yyyy-MM-dd'/></td>
                                                    <td class="tdleft">${appHistory.examineResult}</td>
                                                    <td class="tdleft">${appHistory.examineMoney}</td>
                                                    <td class="tdleft">${appHistory.examineAdvice}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <tag:paginate offset="${appHistoryPager.offset}"
                                                      total="${appHistoryPager.total}"
                                                      steps="${appHistoryPager.pageSize}" uri="${appHistoryPager.uri}"
                                                      formId=""/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>--%>
            </div>
        </div>
    </div>
</div>
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>
<script type="text/javascript">
    var dg = frameElement.lhgDG;//定义dg 关闭需要

    $(function(){
        $.ajax({
            url:'${dynamicURL}/file/getFilesByDocument',
            data:{'document':$("#cdocument").val()},
            dataType:'json',
            type:'POST',
            success:function(data){
                if(data.length > 0 ){
                    $('#uploadTable .noItems').remove();
                    $.each(data,function(i,item){
                        $('#uploadTable > tbody').append('<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
                            + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '" name=documentId />'
                            + '</td><td scope="col">' + item.fileName
                            + '</td><td scope="col">' + item.createUserName
                            + '</td><td style="width:9%">' + item.createDate
                            + '</td><td align="center" style="width:10%"><a href="'+'${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                            + '</td></tr>');
                    });
                }

            }
         });
    });
    function showDemandOrder(id){
        dg.curWin.showDemandOrder(id);
    }
    function showApplyOrder(id){
        dg.curWin.showApplyOrder(id);
    }
</script>
</body>
</html>