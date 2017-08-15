<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <title>发起收货单</title>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">

    <style type="text/css">
        /* css注释：默认css背景被白色 */
        table tr.alt td { background:#F2F2F2;}
        /* css 注释：默认隔行背景颜色 */
        table tr.over td {background:#EEECEB;}
    </style>
</head>
<body>
<div>
    <input type="hidden" name="hfQueryStatus" id="hfQueryStatus" value="show">
    <table cellspacing="0" cellpadding="0"
           style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form action="receiveGoodMapping" id="receiveGoodMappingForm">
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
                                <td align="right" class="tdtitle">采购员:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="buyerName" value="${param.buyerName }" name="buyerName"
                                           class="colorblur">
                                    <%--<input type="hidden" id="buyerCode" type="hidden" name="buyerCode">--%>
                                    <input type="hidden" id="isPurchaseManager" value="${isPurchaseManager}" name="isPurchaseManager">
                                </td>
                                <td align="right" class="tdtitle">收货日期:
                                </td>
                                <td class="tdtext">
                                    <input name="applyDateStart" value="${param.applyDateStart }" type="text"
                                           id="applyDateStart" class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                    -<input name="applyDateEnd" value="${param.applyDateEnd }" type="text"
                                            id="applyDateEnd" class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                </td>
                            </tr>
                            <tr>
                                <!-- 	                             <td align="right" class="tdtitle">所属部门: -->
                                <!--                                 </td> -->
                                <!--                                 <td class="tdtext"> -->
                                <%--                                     <input  name="applyId" value="${param.applyId }" type="text" id="applyId" class="colorblur" > --%>
                                <!--                                 </td>    -->
                                <td align="right" class="tdtitle">是否待收货:
                                </td>
                                <td class="tdtext">
                                    <select name="doProcess" id="doProcess" class="colorblur">
                                        <option value="ALL" ${param.doProcess=="ALL"?"selected='selected'" : ""}>
                                            --请选择--
                                        </option>
                                        <option value="Y" ${param.doProcess=="Y"||null==param.doProcess ? "selected='selected'" : ""}>
                                            待收货
                                        </option>
                                        <option value="N" ${param.doProcess=="N"?"selected='selected'" : ""}>已收货
                                        </option>
                                    </select>
                                </td>
                                <td class="tdtitle" align="right">资产类型:
                                </td>
                                <td class="tdtext">
                                    <select class="colorblur" name="assetsType" id="assetsType">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="IT资产"   ${param.assetsType=="IT资产"?"selected='selected'" : ""}>
                                            IT资产
                                        </option>
                                        <option value="行政资产" ${param.assetsType=="行政资产"?"selected='selected'" : ""}>
                                            行政资产
                                        </option>
                                        <option value="计量器具" ${param.assetsType=="计量器具"?"selected='selected'" : ""}>
                                            计量器具
                                        </option>
                                        <option value="机器设备" ${param.assetsType=="机器设备"?"selected='selected'" : ""}>
                                            机器设备
                                        </option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">供应商:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="supplierName" name="supplierName"
                                           value="${param.supplierName }" class="colorblur"
                                           onblur="this.className='colorblur';">
                                    <input type="hidden" id="supplierCode" name="supplierCode" value=""
                                           class="colorblur" onblur="this.className='colorblur';">
                                </td>
                                <td align="right" class="tdtitle">关闭状态:</td>
                                <td class="tdtext">
                                    <select name="orderStatus" id="orderStatus" class="colorblur">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="1" ${param.orderStatus=="1"?"selected='selected'" : ""}>关闭</option>
                                        <option value="0" ${param.orderStatus=="0"?"selected='selected'" : ""}>未关闭</option>

                                    </select>
                                    <!-- 这里说的关闭是已经废弃的单据，未关闭是未废弃（审批时） -->
                                </td>
                                <%--<td align="right" class="tdtitle">审批状态:--%>
                                <%--</td>--%>
                                <%--<td class="tdtext">--%>
                                <%--<select class="colorblur" name="capprovalstate" id="capprovalstate">--%>
                                <%--<option selected="selected" value="">--请选择--</option>--%>
                                <%--<option value="等待审批" ${param.capprovalstate=="等待审批"?"selected='selected'" : ""}>--%>
                                <%--等待审批--%>
                                <%--</option>--%>
                                <%--<option value="正在审批" ${param.capprovalstate=="正在审批"?"selected='selected'" : ""}>--%>
                                <%--正在审批--%>
                                <%--</option>--%>
                                <%--<option value="已完成" ${param.capprovalstate=="已完成"?"selected='selected'" : ""}>--%>
                                <%--已完成--%>
                                <%--</option>--%>
                                <%--<option value="退回" ${param.capprovalstate=="退回"?"selected='selected'" : ""}>退回--%>
                                <%--</option>--%>

                                <%--</select>--%>
                                <%--</td>--%>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">订单单据号:
                                </td>
                                <td class="tdtext">
                                    <input name="purchaseId" type="text" id="purchaseId" value="${param.purchaseId }"
                                           class="colorblur" onblur="this.className='colorblur';">
                                </td>
                                <td align="right" class="tdtitle">收货单据号:
                                </td>
                                <td class="tdtext">
                                    <input name="receiveGoodsId" type="text" id="receiveGoodsId"
                                           value="${param.receiveGoodsId }" class="colorblur"
                                           onblur="this.className='colorblur';">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">是否草稿:
                                </td>
                                <td class="tdtext">
                                    <select name="draft" id="draft" class="colorblur">
                                        <option value="" ${param.draft==""?"selected='selected'" : ""}>
                                            --请选择--
                                        </option>
                                        <option value="Y" ${param.draft=="Y" ? "selected='selected'" : ""}>
                                            是
                                        </option>
                                        <option value="N" ${param.draft=="N"?"selected='selected'" : ""}>否
                                        </option>
                                    </select>
                                </td>
                                <td align="right" class="tdtitle">所属公司：</td>
                                <td class="tdtext">
                                    <%--<input name="companyCode" value="${param.companyCode}" type="text" id="companyCode" class="colorblur">--%>
                                    <select id="companyCode" class="colorblur" name='companyCode' >
                                        <option selected="selected" value="">--请选择--</option>
                                        <c:forEach items="${companys }" var="comp" varStatus="status">
                                            <c:if test="${comp.ccompanycode == param.companyCode}">
                                                <option value="${comp.ccompanycode}" selected="selected" >(${comp.ccompanycode})${comp.ccompanyname }</option>
                                            </c:if>
                                            <c:if test="${comp.ccompanycode != param.companyCode}">

                                                <option value="${comp.ccompanycode}" >(${comp.ccompanycode})${comp.ccompanyname }</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
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
                <auth:authTag code="Verify">
                <input onclick="opAdddlg('-1','新增收货')" type="button" name="" value="发起收货" id="" class="btn">
                </auth:authTag>
                <auth:authTag code="Delete">
                <input type="button" name="" value="删除" id="btnDelete" class="btn">
                </auth:authTag>
                <%--<input onclick="opAdddlg('-3','审批收货')" type="button" name="" value="审批" id="" class="btn">--%>
                <auth:authTag code="Export">
                <input type="button" name="" value="导出" id="goodsExport" class="btn">
                </auth:authTag>
                <c:if test="${fn:length(receiveGoodsMappingPager.records) > 0}" >
                    <c:if test="${receiveGoodsMappingPager.records[0].receiveGoodsId != null && receiveGoodsMappingPager.records[0].receiveGoodsId!= ''}">
                        <auth:authTag code="Close">
                        <input type="button" name="" value="关闭" id="goodsClose" class="btn">
                        </auth:authTag>
                    </c:if>
                </c:if>

            </td>
        </tr>
        <tr>
            <td valign="top">
                <div id="gridview" >
                        <table class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border-collapse: collapse;">
                            <tbody>
                            <tr style="white-space: nowrap;">
                                <th scope="col">
                                    <input type="checkbox" name=""
                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'gvDataList');"/>
                                </th>
                                <th scope="col">序号</th>
                                <auth:authTag code="Edit">
                                <th scope="col">操作</th>
                                </auth:authTag>
                                <auth:authTag code="View">
                                <th scope="col">操作</th>
                                </auth:authTag>
                                <%--<th scope="col">操作</th>--%>
                                <th scope="col">资产类型</th>
                                <th scope="col">采购订单号(SAP)</th>
                                <th scope="col">订单编码</th>
                                <th scope="col">收货单编码</th>
                                <th scope="col">订单数量</th>
                                <th scope="col">
                                   <c:choose>
                                       <c:when test='${param.doProcess=="N"}'>
                                           本次收货数量
                                       </c:when>
                                       <c:otherwise>
                                           已收货数量
                                       </c:otherwise>
                                   </c:choose>
                                </th>
                                <th scope="col">采购员</th>
                                <th scope="col">供应商编码</th>
                                <th scope="col">供应商</th>
                                <th scope="col">公司名称</th>
                                <th scope="col">公司编码</th>
                                <th scope="col">收货单日期</th>
                                <th scope="col">是否草稿</th>
                                <!-- 								<th scope="col">代办</th> -->
                                <%--<th scope="col">审批状态</th>--%>
                            </tr>
                            <c:forEach items="${receiveGoodsMappingPager.records}" var="o" varStatus="status">
                                <tr style="white-space: nowrap;">
                                    <td >
                                        <input type="checkbox" name="checkboxName" value="${o.purchaseId }">
                                    </td>
                                    <td style="text-align:center">${status.index + 1}</td>
                                    <auth:authTag code="Edit">
                                    <td class="tdleft">
                                        <a href="javascript:opUpdatedlg('${o.receiveGoodsId }','数据修改','${o.capprovalstate}','${o.isAuthorize}')">修改</a>
                                    </td>
                                    </auth:authTag>
                                    <auth:authTag code="View">
                                    <td class="tdleft">
                                        <a href="javascript:opShowdlg('${o.receiveGoodsId }','数据查看','${o.capprovalstate}')">查看</a>
                                    </td>
                                    </auth:authTag>
                                        <%--<td>
                                            <a href="javascript:opAdddlg('${o.id}','打印')">打印</a>
                                        </td>--%>
                                    <td class="tdleft">${o.assetsType}</td>
                                    <td class="tdleft">${o.purchaseOrderSapId}</td>
                                    <td name ='purchaseId' class="tdleft"><a href="javascript:showPurchase('${o.purchaseId}','数据查看')">${o.purchaseId}</a> </td>
                                    <td class="tdleft"><span id="receiveGoodsIdonly" name="receiveGoodsId">${o.receiveGoodsId}</span></td>
                                    <td style="text-align:right">${o.applyCountAll}</td>
                                    <td style="text-align:right">${o.goodsCountAll}</td>
                                    <td class="tdleft">${o.buyerName}</td>
                                    <td class="tdleft">${o.supplierCode}</td>
                                    <td class="tdleft">${o.supplierName}</td>
                                    <td class="tdleft">${o.companyName}</td>
                                    <td class="tdleft">${o.companyCode}</td>
                                    <td name="receiveDate" align="center"><fmt:formatDate value='${o.receiveDate}' pattern='yyyy-MM-dd'/></td>
                                    <td class="tdleft"><c:if test="${o.draft == 'Y'}" >是</c:if><c:if test="${o.draft == 'N'}" >否</c:if> </td>
                                        <%--                                     <td>${o.doProcess }</td> --%>
                                        <%--<td>${o.capprovalstate}</td>--%>
                                    <input type="hidden" name="isAuthorize" value="${o.isAuthorize}" >
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${receiveGoodsMappingPager.offset}"
                                      total="${receiveGoodsMappingPager.total}"
                                      steps="${receiveGoodsMappingPager.pageSize}"
                                      uri="${receiveGoodsMappingPager.uri}"
                                      formId="receiveGoodMappingForm"/>
                    </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<%-- <script src="${staticURL}/Scripts/Purchase/ReceiveGoods/ReceiveGoods.js" type="text/javascript"></script> --%>
<script type="text/javascript">
    // OP005023
    //     GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
    //     QueryFieldSetStauts('t_ShipInportInfo', 'fset_ShipInportInfo', 'img_ShipInportInfo', document.getElementById("hfQueryStatus").value);
    function autoSize() {
        $("#gridview").width($(window).width()-2);
        $("#gridview").height(
            $(window).height() - $("#Query").height()
            - $("#Button").height()
            - $("#AspNetPager").height()-2);
    }
    autoSize();
    window.onresize = autoSize;
    var isPurchaseManager = $("#isPurchaseManager").val();
    $("#btnQuery").click(function () {

        var beginDate = $("#applyDateStart").val();
        var endDate = $("#applyDateEnd").val();
        var d1 = new Date(beginDate.replace(/\-/g, "\/"));
        var d2 = new Date(endDate.replace(/\-/g, "\/"));

        if (beginDate != "" && endDate != "" && d1 >= d2) {
            alert("开始时间不能大于结束时间！");
            return;
        }
        window.location.href = "${dynamicURL}/receiveGoods/searchReceiveGoodsMapping?" + $('#receiveGoodMappingForm').serialize();
    });
    var dg = frameElement.lhgDG;
    // 	var W = dg.curWin;// 获得父窗体

    /* 弹出层大小 统一为 800和600 */
    function opAdddlg(num, title) {

        /* -1是新增窗口 */
        if (-1 == num) {

            //
            var num = $(":input[name=checkboxName][checked]").length;

            if (parseInt(1) != parseInt(num)) {
                alert("仅能选中一项!")
                return;
            }
            var receiveGoodsId = $(":input[name=checkboxName][checked]").parent().parent().find("td span[name='receiveGoodsId']").text();
            var isAuthorize = $(":input[name=checkboxName][checked]").parent().parent().find("input[name='isAuthorize']").val();
            if("Y" != isAuthorize) {
                alert("当前账号只有查看权限");
                return;
            }
            if(null !=receiveGoodsId && "" !=receiveGoodsId){
                alert("仅能对待收货的单据收货");
                return;
            }
            var purchaseId = $('input[name = checkboxName]:checked').val();
            var havingDraft ;
            $.ajax({
                data:{"purchaseId":purchaseId},
                url:"${dynamicURL}/receiveGoods/api/searchHavingDraft",
                async:false,
                type:"post",
                dataType:"json",
                success:function (data) {
                    //alert(data);
                    if("1"==data) {
                        havingDraft = true;
                    }

                },error:function(){
                        alert("error");
                }
            });

            if (havingDraft) {
                alert("此订单还有未处理的草稿");
                return;
            }
            var lurl = "${dynamicURL}/receiveGoods/searchPurchaseOrderMappingByPurchaseId?purchaseId=" + purchaseId;
            var parentDG = new $.dialog({
                id: 'searchPurchaseOrderMappingByPurchaseId',
                page: lurl,
                cover: true,
                autoPos: {
                    left: 'center',
                    top: 'center'
                },
                width: 1050,
                height: 600,
                title: title,
                iconTitle: false,
                btnBar: false
            });
            parentDG.ShowDialog();
        }
        /* -3是收货 审批  */
        <%--else if (-3 == num) {--%>
        <%--var receiveGoodsId = $('input[name = checkboxName]:checked').parent().next().next().next().next().next().next().next().next().text();--%>
        <%--if (receiveGoodsId == null || receiveGoodsId == undefined || receiveGoodsId == "") {--%>
        <%--alert("请选择有收货单号的单据进行审批！");--%>
        <%--} else {--%>
        <%--var lurl = "${dynamicURL}/receiveGoods/bindingReceiveGoods?receiveGoodsId=" + receiveGoodsId + "&typeId=77077";--%>
        <%--var parentDG = new $.dialog({--%>
        <%--id: 'receiveGoodsApproveDialog',--%>
        <%--page: lurl,--%>
        <%--cover: true,--%>
        <%--autoPos: {--%>
        <%--left: 'center',--%>
        <%--top: 'center'--%>
        <%--},--%>
        <%--width: 1050,--%>
        <%--height: 600,--%>
        <%--title: title,--%>
        <%--iconTitle: false,--%>
        <%--btnBar: false--%>
        <%--});--%>
        <%--parentDG.ShowDialog();--%>
        <%--}--%>
        <%--}--%>
    }
    /* 修改  弹出层大小 统一为 800和600 */
    function opUpdatedlg(receiveGoodsId, title, capprovalstate,isAuthorize) {

        if("Y" != isAuthorize) { //采购经理只有查看权限
            alert("当前账号只有查看权限");
            return;
        }

        if(receiveGoodsId==''){
            alert("请修改已收货未验收的单据");
            return;
        }
        var isChecked=true;
        $.ajax({
            data:{"receiveGoodsId":receiveGoodsId},
            dataType:"json",
            type:"post",
            async:false,
            url:"${dynamicURL}/receiveGoods/api/isChecked",
            success:function (data) {
                if(data=='0') { //如果没有被验收
                    isChecked=false;
                }
            }
        });
        if (isChecked) {
            alert("该单据已经被验收，不可修改");
            return;
        }
        var lurl = "${dynamicURL}/receiveGoods/updateReceiveGoods?receiveGoodsId=" + receiveGoodsId;

        var parentDG = new $.dialog({
            id: 'updateReceiveGoods',
            page: lurl,
            cover: true,
            autoPos: {
                left: 'center',
                top: 'center'
            },
            width: 950,
            height: 600,
            title: title,
            iconTitle: false,
            btnBar: false
        });
        parentDG.ShowDialog();

    }
    /* 查看  弹出层大小 统一为 800和600 */
    function opShowdlg(receiveGoodsId, title, capprovalstate) {

        if (null == receiveGoodsId || "" == receiveGoodsId) {
            alert("请选择已收货的单据!");
            return;
        }
        var lurl = "${dynamicURL}/receiveGoods/showReceiveGoods?receiveGoodsId=" + receiveGoodsId + "&&typeId=77077";
        var parentDG = new $.dialog({
            id: 'updateReceiveGoods',
            page: lurl,
            cover: true,
            autoPos: {
                left: 'center',
                top: 'center'
            },
            width: 950,
            height: 600,
            title: title,
            iconTitle: false,
            btnBar: false
        });
        parentDG.ShowDialog();
    }
    //导出功能
    $("#goodsExport").click(function (){
        if (window.confirm("确认要导出吗?")) {
            window.location.href = "${dynamicURL}/receiveGoods/api/exportReceive?"+$('#receiveGoodMappingForm').serialize();
        }
    });
    //删除功能
    $("#btnDelete").click(function(){
        /*if("Y" == isPurchaseManager) {
            alert("当前账号只有查看权限");
            return;
        }*/
//        var ids = '';
        var list=[];
        var dateLength = 0;
        var flag = false;
        $("input[name='checkboxName']:checked").each(function () {
           var receiveId = $(this).parent().parent().find("td span[name='receiveGoodsId']").text();
           var receiveDate = $(this).parent().parent().find("td[name='receiveDate']").text();
            var isAuthorize = $(this).parent().parent().find("input[name='isAuthorize']").val();
            if("Y" != isAuthorize) {
                flag = true;
                return false;
            }
           if(null == receiveDate || "" == receiveDate) {
               dateLength+=1;
           }
//           ids+=receiveId+',';
           list.push(receiveId);
        });
        if (flag) {
            alert("只能删除有权限的单据");
            return;
        }
        if (dateLength > 0) {
            alert("只能删除已收货未验收的单据");
            return;
        }
        var canDelete;
        $.ajax({
            async:false,
            type : "post",
            dataType:"json",
            url : "${dynamicURL}/receiveGoods/api/searchCanBeDelete",
            data : {
                "list":list
            },
            success:function(data){
                canDelete=data;
            }
        });
        if (canDelete != '0') {
            alert("已验收的单据不能删除");
            return;
        }
        $.ajax({
                type : "post",
                dataType:"json",
                url : "${dynamicURL}/receiveGoods/api/deleteReceiveGoods",
                data : {
                    "list":list
                },
                success:function(data){
                    if(data.success){
                        alert("删除成功");
                    }else {
                        alert(data.errorMessage);
                    }
                    window.location.reload();
                }
            });
    });
    //关闭功能
    $("#goodsClose").click(function(){
        if("Y" == isPurchaseManager) {
            alert("当前账号只有查看权限");
            return;
        }
        var list=[];
        $("input[name='checkboxName']:checked").each(function () {
            var receiveId = $(this).parent().parent().find("td span[name='receiveGoodsId']").text();
//           ids+=receiveId+',';
            list.push(receiveId);
        });
        $.ajax({
            type:'post',
            dataType:'json',
            data:{
                "list":list
            },
            url:"${dynamicURL}/receiveGoods/api/updateCloseReceiveGoods",
            success:function (data) {
                if(data.success){
                    alert("关闭成功");
                }else {
                    alert(data.errorMessage);
                }
            },error:function(){
                alert("error");
            }
        });
    });
    function showDemandOrder(id) {
        var lurl = "${dynamicURL}/demand/showDemandOrder?id=" + id+"&typeId=77001";
        var dlg = new $.dialog({ id: 'showForDemand', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }
    function showApplyOrder(id) {
        var lurl = "${dynamicURL}/apply/showApplyOrder?id=" + id+"&typeId=77011";
        var dlg = new $.dialog({ id: 'showForApply', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }

    function showPurchase(id, title) {
        var lurl = "${dynamicURL}/purchase/showPurchaseOrder?id=" + id+"&typeId=77021";
        var dlg = new $.dialog({ id: 'add', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();

    }
    $(document).ready(function(){ //这个就是传说的ready
        $("#gvDataList tr").mouseover(function(){
            //如果鼠标移到class为stripe的表格的tr上时，执行函数
            $(this).addClass("over");}).mouseout(function(){
            //给这行添加class值为over，并且当鼠标一出该行时执行函数
            $(this).removeClass("over");}) //移除该行的class
        $("#gvDataList tr:even").addClass("alt");
        //给class为stripe的表格的偶数行添加class值为alt
        //www.divcss5.com 整理特效
    });
</script>
</body>
</html>