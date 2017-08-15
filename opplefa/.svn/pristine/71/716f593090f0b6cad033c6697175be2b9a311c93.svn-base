<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
    <title>资产验收</title>
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
    <table cellspacing="0" cellpadding="0"  style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form action="checkAcceptance" id="checkAcceptanceForm">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif" style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                       	</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="t_ShipInportInfo" class="adminlist" style="visibility: visible;">
                            <tbody>

                            <tr>
<!--                                 <td align="right" class="tdtitle">所属部门: -->
<!--                                 </td> -->
<!--                                 <td class="tdtext"> -->
<!--                                     <input name="departmentCode" value="" type="text" id="departmentCode" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';"> -->
<!--                                 </td> -->
   								<td align="right" class="tdtitle">是否待验收:
	                             </td>
	                                <td class="tdtext">
	                                  <select name="doProcess" id="doProcess" class="colorblur">
                                        <%--<option selected="selected" value="ALL">--请选择--</option>
                                        <option value="Y" ${param.doProcess=="Y"||param.doProcess==ALL?"selected='selected'" : ""}>待验收</option>
                                        <option value="N" ${param.doProcess=="N"?"selected='selected'" : ""}>已验收</option>   		--%>
                                            <option value="ALL" ${param.doProcess=="ALL"?"selected='selected'" : ""}>
                                                --请选择--
                                            </option>
                                            <option value="Y" ${param.doProcess=="Y"||null==param.doProcess ? "selected='selected'" : ""}>
                                                待验收
                                            </option>
                                            <option value="N" ${param.doProcess=="N"?"selected='selected'" : ""}>已验收
                                            </option>
                                    </select>
	                                </td> 
                               <td class="tdtitle" align="right">资产类型:
                                </td>
                                <td class="tdtext">
                                	<select class="colorblur" name="assetsType" id="assetsType">
                                       <option selected="selected" value="">--请选择--</option>
                                       <option value="IT资产"   ${param.assetsType=="IT资产"?"selected='selected'" : ""}>IT资产</option>
                                       <option value="行政资产" ${param.assetsType=="行政资产"?"selected='selected'" : ""}>行政资产</option>
                                       <option value="计量器具" ${param.assetsType=="计量器具"?"selected='selected'" : ""}>计量器具</option>
                                       <option value="机器设备" ${param.assetsType=="机器设备"?"selected='selected'" : ""}>机器设备</option>
                                   	</select>
                                </td>
                            </tr>
                             <tr>
                                <td align="right" class="tdtitle">供应商:
                                </td>
                                <td class="tdtext">
                                    <input type="text" id="supplierName"  name="supplierName" value="${param.supplierName }" class="colorblur" onblur="this.className='colorblur';">
                                    <input type="hidden" id="supplierCode"  name="supplierCode" value="" class="colorblur" onblur="this.className='colorblur';" >
                                </td>
                                <td align="right" class="tdtitle">收货单据号:</td>
                                <td class="tdtext">
                                 <input name="receiveGoodsId" value="${param.receiveGoodsId}" type="text" id="receiveGoodsId" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                  </td>
                            </tr>
                            <tr>	 
                            	<td align="right" class="tdtitle">审批状态:
                                </td>
                                <td class="tdtext">
                                    <select class="colorblur" name="capprovalstate" id="capprovalstate">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="等待审批" ${param.capprovalstate=="等待审批"?"selected='selected'" : ""}>等待审批</option>
                                        <option value="正在审批" ${param.capprovalstate=="正在审批"?"selected='selected'" : ""}>正在审批</option>
                                        <option value="已完成" ${param.capprovalstate=="已完成"?"selected='selected'" : ""}>已完成</option>
                                        <option value="退回" ${param.capprovalstate=="退回"?"selected='selected'" : ""}>退回</option>

                                    </select>
                                </td>
                                <td align="right" class="tdtitle">验收单据号:
                                 </td>
                                <td class="tdtext">
                                   <input name="checkAcceptId" value="${param.checkAcceptId}" type="text" id="checkAcceptId" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                </td>
                            </tr>
                           <%-- <tr>
	                                                
	                            <td align="right" class="tdtitle">关闭状态:</td>
                                <td class="tdtext">
                                    <select name="status" id="status" class="colorblur">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="1" ${param.status=="1"?"selected='selected'" : ""}>关闭</option>
                                        <option value="0" ${param.status=="0"?"selected='selected'" : ""}>未关闭</option>
                                        
                                    </select>
                                    </td>
                            </tr>--%>
                            <tr>

                                <td align="right" class="tdtitle">SAP订单号:</td>
                                <td class="tdtext">
                                    <input name="purchaseSapNo" value="${param.purchaseSapNo}" type="text" id="purchaseSapNo" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                </td>
                                <td align="right" class="tdtitle">验收申请人:</td>
                                <td class="tdtext">
                                    <input name="applyCheckUserName" value="${param.applyCheckUserName}" type="text" id="applyCheckUserName" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                </td>
                            </tr>
                            <tr>

                                <td align="right" class="tdtitle">物料凭证号:</td>
                                <td class="tdtext">
                                    <input name="epMaterialdocu" value="${param.epMaterialdocu}" type="text" id="epMaterialdocu" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                </td>
                                <td align="right" class="tdtitle">会计凭证号:</td>
                                <td class="tdtext">
                                    <input name="epBelnr" value="${param.epBelnr}" type="text" id="epBelnr" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                </td>
                            </tr>
                            <tr>
                                <%--<td align="right" class="tdtitle">采购员:
                                </td>
                                <td class="tdtext">
                                    <input name="buyerName" value="${param.buyerName }" type="text" id="buyerName" class="colorblur" onblur="this.className='colorblur';" >
                                </td>--%>
                                    <td align="right" class="tdtitle">订单编号:
                                    </td>
                                    <td class="tdtext">
                                        <input name="purchaseId" value="${param.purchaseId}" type="text" id="purchaseId" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                    </td>
                                <td align="right" class="tdtitle">验收日期:
                                </td>
                                <td class="tdtext">
                                    <input name="checkDateStart" value="${param.checkDateStart}" type="text" id="checkDateStart" class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                    ~<input name="checkDateEnd" value="${param.checkDateEnd}" type="text" id="checkDateEnd" class="Wdate" onclick="WdatePicker({skin:'ext'});">
                                </td>
                            </tr>

                            <tr>
                                <td align="right" class="tdtitle">资产编码:
                                </td>
                                <td class="tdtext">
                                    <input name="sapSecAssetCode" value="${param.sapSecAssetCode}" type="text" id="sapSecAssetCode" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                </td>
                                <td align="right" class="tdtitle">资产名称:
                                </td>
                                <td class="tdtext">
                                    <input name="assetsName" value="${param.assetsName}" type="text" id="assetsName" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                </td>
                            </tr>
                            <tr>
                                <td align="right" class="tdtitle">所属公司：</td>
                                <td class="tdtext">
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
                                <td align="right" class="tdtitle">成本中心:
                                </td>
                                <td class="tdtext">
                                    <input name="costCenterCode" value="${param.costCenterCode}" type="text" id="costCenterCode" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                </td>
                            </tr>
                            </tbody></table>
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
                <input onclick="opAdddlg('-1','待验收单据')" type="button" name="" value="验收"  class="btn">
                </auth:authTag>
                <auth:authTag code="Check">
                <input onclick="opAdddlg('-2','验收单审批')"  type="button" name="" value="审批"  class="btn">
                </auth:authTag>
                <auth:authTag code="Delete">
                <input type="button" name="" id="btnDelete" value="删除" class="btn">
                </auth:authTag>
                <auth:authTag code="Export">
                <input type="button" name="" value="导出" id='checkExport'class="btn">
                </auth:authTag>
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
                                    <input  type="checkbox" name="" onclick="javascript:SelectAllCheckboxesByTableId(this, 'gvDataList');"/>
                                </th>
                                 <th scope="col">序号</th>
                                <auth:authTag code="Edit">
                                <th scope="col">操作</th>
                                </auth:authTag>
                                <auth:authTag code="View">
                                <th scope="col">操作</th>
                                </auth:authTag>
                                <auth:authTag code="Print">
                                <th scope="col">操作</th>
                                </auth:authTag>
                                <th scope="col">资产类型</th>
                                <th scope="col">SAP订单号</th>
                                <th scope="col">订单编号</th>
                                <th scope="col">收货单据号</th>
                                <th scope="col">验收单据号</th>

                                <th scope="col">物料凭证号</th>
                                <th scope="col">会计凭证号</th>

                                <th scope="col">收货数量</th>
                                <th scope="col">已验收数量</th>
                                <th scope="col">本次验收数量</th>
                                <th scope="col">采购员</th>
                                <th scope="col">供应商编码</th>
                                <th scope="col">供应商</th>
                                <th scope="col">公司名称</th>
                                <th scope="col">公司编码</th>
                                <th scope="col">验收日期</th>
<!-- 								<th scope="col">代办进程</th> -->
                                <th scope="col">审批状态</th>
                                <th scope="col">验收申请人</th>
                                <th scope="col">下级处理人</th>
                                <th scope="col">是否已打印</th>
                            </tr>
                            <c:forEach items="${checkAcceptMappingPager.records}" var="o" varStatus="status">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input type="checkbox" name="checkboxName" value="${o.receiveGoodsId }">
                                        <input type="hidden" value="${o.checkAcceptId}">
                                    </td>
                                    <td style="text-align:center">${status.index + 1}</td>
                                        <auth:authTag code="Edit">
                                    <td align="center">
                                        <a href="javascript:opUpdatedlg('${o.checkAcceptId }','数据修改','${o.capprovalstate}')">修改</a>
                                    </td>
                                    </auth:authTag>
                                        <auth:authTag code="View">
                                    <td align="center">
                                        <a href="javascript:opAdddlg('${o.checkAcceptId}','数据查看');"  >查看</a><!--  easyui-->
                                    </td>
                                    </auth:authTag>
                                    <auth:authTag code="Print">
                                     <td align="center">
                                         <a href="javascript:stamp('${o.checkAcceptId}','打印','${o.purchaseSapNo}','${o.capprovalstate }');" rel="external nofollow" target="_self">打印</a>
                                    </td>
                                    </auth:authTag>
                                    <td>${o.assetsType}</td>
                                    <td>${o.purchaseSapNo}</td>
                                    <td><a href="javascript:showPurchase('${o.purchaseId}','数据查看')">${o.purchaseId}</a> </td>
                                    <td><a href="javascript:showReceive('${o.receiveGoodsId}','数据查看');"  >${o.receiveGoodsId}</a></td>
                                    <td name="checkAcceptId">${o.checkAcceptId}</td>
                                    <td>${o.epMaterialdocu}</td>
                                    <td>${o.epBelnr}</td>
                                    <td style="text-align:right">${o.goodsCountAll}</td>
                                    <td style="text-align:right">${o.checkCountAll}</td>
                                    <td style="text-align:right">${o.thisCheckCount}</td>
                                    <td>${o.buyerName }</td>
                                    <td>${o.supplierCode }</td>
                                    <td>${o.supplierName }</td>
                                    <td>${o.computerName }</td>
                                    <td>${o.computerCode }</td>
                                    <td style="text-align:center"><fmt:formatDate value="${o.checkDate }"  pattern="yyyy-MM-dd"/></td>
                                    <td name="capprovalstate1"><a href="javascript:void(0)" onclick="showApproveHistory('${o.checkAcceptId}','77078');" >${o.capprovalstate }</a></td>
                                    <td>${o.applyCheckUserName}</td>
                                    <td>${o.cnexthandlername}</td>
                                    <td>
                                            ${o.isprint == 'Y' ? '是' : '否'}
                                    </td>
                                    <input type="hidden" name="isAuthorize" value="${o.isAuthorize}">
                                    <!--                                 </tr> -->
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${checkAcceptMappingPager.offset}" total="${checkAcceptMappingPager.total}"  steps="${checkAcceptMappingPager.pageSize}" uri="${checkAcceptMappingPager.uri}" formId="checkAcceptanceForm" />
                    </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script src="${staticURL}/Scripts/Purchase/CheckAcceptance/CheckAcceptance.js" type="text/javascript"></script>

<script type="text/javascript">
function autoSize() {
    $("#gridview").width($(window).width()-2);
    $("#gridview").height(
            $(window).height() - $("#Query").height()
            - $("#Button").height()
            - $("#AspNetPager").height()-2);
}
autoSize();
window.onresize = autoSize;
var dg = frameElement.lhgDG;
//解决ie报错 对象没有trim属性或方法
String.prototype.trim = function () {
    return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
}
//  模糊查询分页
    $("#btnQuery").click(function() {
        var beginDate=$("#checkDateStart").val();
        var endDate=$("#checkDateEnd").val();
        var d1 = new Date(beginDate.replace(/\-/g, "\/"));
        var d2 = new Date(endDate.replace(/\-/g, "\/"));

        if(beginDate!=""&&endDate!=""&&d1 >=d2)
        {
            alert("开始时间不能大于结束时间！");
            return;
        }
        window.location.href = '${dynamicURL}/checkAccept/searchCheckAcceptMapping?'+$('#checkAcceptanceForm').serialize();
    });

    /* 弹出层大小 统一为 800和600 */  
    function opAdddlg(num, title) {
    	if (num == -1) {// -1验收按钮

            var length = $(":input[name=checkboxName][checked]").length;

            if (parseInt(1) != parseInt(length)) {
                alert("仅能选中一项!");
                return;
            }

            var capprovalstate1 =  $('input[name=checkboxName]:checked').parent().parent().find('td[name=capprovalstate1] a').text();
            var isAuthorize =  $('input[name=checkboxName]:checked').parent().parent().find('input[name=isAuthorize]').val();
            if ("Y" != isAuthorize) {
                alert("当前账号不具备此权限");
                return;
            }
            //alert(capprovalstate1);
            if((null || '') != capprovalstate1) {
   			    alert("只有未验收的单据才能验收");
   			    return;
            }
            var receiveGoodsId = $('input[name=checkboxName]:checked').val();
    			var lurl = "${dynamicURL}/checkAccept/searchTempReceiveGoodsByReceiveGoodsId?receiveGoodsId=" + receiveGoodsId;
	    		var parentDG = new $.dialog({
	    			id : 'searchTempReceiveGoodsByReceiveGoodsId',
	    			page : lurl,
	    			cover : true,
	    			autoPos : {
	    				left : 'center',
	    				top : 'center'
	    			},
	    			width : 1100,
	    			height : 600,
	    			title : title,
	    			iconTitle : false,
	    			btnBar : false,
                    maxBtn: true
    			});
    			parentDG.ShowDialog();
    	} else if(-2 == num){ //-2审批
            var length = $(":input[name=checkboxName][checked]").length;

            if (parseInt(1) != parseInt(length)) {
                alert("仅能选中一项!")
                return;
            }
            var capprovalstate1 =  $('input[name=checkboxName]:checked').parent().parent().find('td[name=capprovalstate1]').text().trim();

            if( ( '退回' != capprovalstate1)&& ( '正在审批' != capprovalstate1)&& ( '等待审批' != capprovalstate1) && ( '已完成' != capprovalstate1)) {
                alert("只有已验收的单据才能审批");
                return ;
            }

            var checkAccpetId = $('input[name=checkboxName]:checked').next().val();
//
			var lurl = "${dynamicURL}/checkAccept/bindingCheckAcceptByCheckAcceptId?checkAccpetId=" + checkAccpetId+"&typeId=77078";
    		var parentDG = new $.dialog({
    			id : 'checkAcceptApproveDialog',
    			page : lurl,
    			cover : true,
    			autoPos : {
    				left : 'center',
    				top : 'center'
    			},
    			width : 1100,
    			height : 600,
    			title : title,
    			iconTitle : false,
    			btnBar : false,
                maxBtn: true
			});
			parentDG.ShowDialog();
    	}else{ //查看
            if(null == num || "" == num){
                alert("只能查看已验收单据");
                return;
            }
    		var lurl = "${dynamicURL}/checkAccept/showCheckAcceptance?checkAcceptId=" + num+"&typeId=77078";
	    	var dlg = new $.dialog({ id: 'show', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: true }).ShowDialog();
    	}
	}
    //修改按钮
    function opUpdatedlg(checkAcceptId, title,capprovalstate) {
       // alert(capprovalstate);
        var canUpdate = "";
        $.ajax({
            url:"${dynamicURL}/checkAccept/api/canUpdate",
            data:{"checkAcceptId":checkAcceptId},
            type:"post",
            async:false,
            dataType:"json",
            success:function (data) {
                canUpdate=data.canUpdate;
                if(null != data.errorMessage && "" != data.errorMessage) {
                    alert(data.errorMessage);
                    return;
                }
            }
        });
        if ("N" == canUpdate) {
            alert("只有验收申请人才可以修改");
            return;
        }
    	if('等待审批' == capprovalstate || '退回'  == capprovalstate ){ // 等待审批 和退回的可以修改
		var lurl = "${dynamicURL}/checkAccept/updateCheckAcceptance?checkAcceptId=" + checkAcceptId;
   		var parentDG = new $.dialog({
   			id : 'updateCheckAcceptance',
   			page : lurl,
   			cover : true,
   			autoPos : {
   				left : 'center',
   				top : 'center'
   			},
   			width : 950,
   			height : 600,
   			title : title,
   			iconTitle : false,
   			btnBar : false
  			});
		parentDG.ShowDialog();
		}
		else {
            alert("仅可以修改等待审批和退回的单据!");
        }

	}
$("#checkExport").click(function (){
    if (window.confirm("确认要导出吗?")) {
        window.location.href = "${dynamicURL}/checkAccept/api/exportCheck?"+$('#checkAcceptanceForm').serialize();
    }
});
//删除功能
$("#btnDelete").click(function(){
    var list=[];
    var flag=false;
    var canUpdate = false;
    $("input[name='checkboxName']:checked").each(function () {
        var checkAcceptId = $(this).parent().parent().find("td[name='checkAcceptId']").text();
        var capprovalstate = $(this).parent().parent().find("td[name='capprovalstate1']").text();
        $.ajax({
            data:{"checkAcceptId":checkAcceptId},
            dataType:"json",
            async:false,
            type:"post",
            url:"${dynamicURL}/checkAccept/api/canUpdate",
            success:function (data) {
                if("N"==data.canUpdate){
                    canUpdate=true;
                }
                if(null != data.errorMessage && "" != data.errorMessage) {
                    alert(data.errorMessage);
                }
            }
        });

        if("等待审批"!=capprovalstate && "退回"!=capprovalstate) {
            flag=true;
        }
        list.push(checkAcceptId);
    });
    if (canUpdate) {
        alert("只有验收申请人才可以删除");
        return;
    }
    if(flag){
        alert("只能删除等待审批和退回状态的单据");
        return;
    }
    var checkAcceptId1 = $(this).parent().parent().find("td[name='checkAcceptId']").text();
    /*var arr;//用来接收一个list，里面存放根据验收单查询到的所有的mmAssertsCode
    $.ajax({
        url:"${dynamicURL}/checkAccept/api/getMmAssertsCode",
        dataType:"json",
        async:false,
        data:{"checkAcceptId":checkAcceptId1},
        type:"post",
        success:function(data){//
            if(data.success){
                arr = data.list;
                alert("删除成功");
            }else {
                alert("获取mmAssertsCode失败");
            }
        }
    });
    //遍历删除文件*/
    $.ajax({
        type : "post",
        dataType:"json",
        url : "${dynamicURL}/checkAccept/api/deleteCheck",
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

//打印
function stamp(id, title,spaPurchaseId,capprovalstate){
    if(capprovalstate != '已完成') {
        alert('只有审批完成的单据才可以打印');
        return;
    }
    var lurl = "${dynamicURL}/checkAccept/showStamp?id="+id+"&typeId=77078&spaPurchaseId="+spaPurchaseId;
    window.open(lurl);
}

function showApproveHistory(document, typeId) {
    var url = '${dynamicURL}/workflow/showApproveHistory?document=' + document + '&typeId=' + typeId;
    showDialog('approveHistory', '申请流程', url, 800, 500);
}
function showDialog(id, title, url, width, height) {
    new $.dialog({ id: id, title: title, cover: true, page: url, width: width, height: height, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
}


function showReceive(receiveGoodsId, title, capprovalstate) {

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