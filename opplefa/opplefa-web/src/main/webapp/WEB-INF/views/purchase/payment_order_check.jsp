<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>付款选择</title>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
    <link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>

    <div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
        <div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
                付款选择</div>
    </div>
    <div id="Div2" style="margin-left: 0px; margin-top: 0px;">
        <input type="button" name="btnSave" value="确定" onclick="" id="btnSave" class="btn">
        <input type="button" name="btnCancel" value="返回" id="btnCancel" class="btn" onclick="dg.cancel()">
    </div>
    <div style="margin-top: 10px;">
    
    <div id="purchaseTab" class="easyui-tabs" style="width:850px;">
       <div title="订单信息" id="tabsa" style="padding:20px;">
       <div>
       <form id="purchaseOrderFormHeader">
        <table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:825px;">
            <tbody><tr>
                <td class="titlefontsize" align="left">
                <div class="imgtitle"></div>
                基本信息</td>
            </tr>
            <tr>
                <td>
                    <table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
						<tbody>
						<tr>
							<td style="text-align: right; width: 90px;">采购订单号:</td>
							<td>
								<input class="searchinput" readonly name="cdocument" type="text" value="${purchaseOrder.cdocument}" id="txtPurchaseOrderId">
							</td>
							<td style="text-align: right;">付款类别:</td>
							<td class="tdtext">
								<select style="width: 150px;" name="paymentType" id="paymentType">
										<option value="">--请选择--</option>
									<c:forEach items="${paymentTerms}" var="pt">	
										<option value="${pt.nper}">${pt.paymentType}</option>
									</c:forEach>							
								</select>
	                        </td>
	                        <td></td>
	                        <td></td>
						</tr>
						</tbody>
					</table>
                </td>
            </tr>           
            </tbody>
        </table>
      	
      </form>
        
        </br>
        <table id="Table8" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:825px;">
            <tbody>
            <tr>
                <td class="titlefontsize" align="left">
                <div class="imgtitle"></div>选择付款明细
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table id="Table10" width="100%"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
                        <tbody>
                        <tr>
                            <td class="tdtitle" style="text-align: right" colspan="2">
                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
                                    <div>
                                        <table id="table_detail" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
                                           
                                            <tr style="white-space:nowrap;">
                                                <th scope="col">
                                                    <input id="gvDataList_CheckAll" type="checkbox" name="gvDataList$ctl01$CheckAll"
                                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_detail');"/>
                                                </th>
                                                <th scope="col" class="tdpadding">序号</th>
                                                <th scope="col" class="tdpadding">资产名称</th>
                                                <th scope="col" class="tdpadding">订单数量</th>
                                                <th scope="col" class="tdpadding">订单单价</th>
                                                <th scope="col" class="tdpadding">订单总价</th>
                                                <th scope="col" class="tdpadding">已付总金额</th>
                                                <th scope="col" class="tdpadding">已付数量</th>
                                            </tr>
                                            <tbody id="tbody_detail"></tbody>
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
    



<script type="text/javascript">
	var dg = frameElement.lhgDG;//定义dg 关闭需要
	function checkAll(){
		
		return true;
	}
	$("#btnSave").click(function() {//提交
		var count = 0;
		var ids = "";
		var flag = false;
		if($("#paymentType").val()==""){
			alert("请选择付款类别!");
	  		return;
		}
	  	$("input[name=chkBolDel]:checked").each(function(){
	  		/* var applyCount = $.trim($(this).parent().parent().find("td[name=applyCount]").html());
	  		var paidCount = $.trim($(this).parent().parent().find("td[name=paidCount]").html());
	  		if(paidCount >= applyCount){
	  			alert("已付款完毕");
	  			flag = true;
	  		} */
	  		ids += $(this).parent().parent().find("input[name=purchaseDetailId]").val()+","
	  		count++;
	  	});
	  	if(count == 0){
	  		alert("至少选择一项进行付款!");
	  		return;
	  	}
	  	ids = ids.substr(0,ids.length-1);
	  	
	  	var purchaseId = $("#txtPurchaseOrderId").val();
		var nper = $("#paymentType").val();
		
	  	var strData="purchaseId="+purchaseId+"&nper="+nper+"&ids="+ids;
	  	
	  	dg.curWin.addPayment(strData);
        dg.cancel();
	});
	
	$("#paymentType").change(function(){
		changePaymentType();
	});
	function changePaymentType(){
		var purchaseId = $("#txtPurchaseOrderId").val();
		var nper = $("#paymentType").val();
		var dat = {};
		dat.purchaseId = purchaseId;
		dat.nper = nper;
		$.ajax({
			type : "POST",
			nsync : true,
			url : "${dynamicURL}/payment/api/changePaymentType",
			data : JSON.stringify(dat),
			dataType: 'json',
		    contentType: "application/json",
			success : function(data){
				var htm ="";
				for (var i = 0; i < data.length; i++) {
					if(data[i].applyCount > data[i].paidCount){
						htm +="<tr style='white-space:nowrap;'>";
						htm +="<td scope='col'>";
						htm +=" <input id='gvDataList_Check' type='checkbox' name='chkBolDel'/>";
						htm +="</td>";
						htm +="<td class='tdpadding'>"+(i+1-0)+"<input type='hidden' name='purchaseDetailId' value='"+data[i].purchaseDetailId+"'></td>";
						htm +="<td class='tdpadding'>"+data[i].assetsName+"</td>";
						htm +="<td class='tdpadding' name='applyCount'>"+data[i].applyCount+"</td>";
						htm +="<td class='tdpadding'>"+data[i].purchaseUnitPrice+"</td>";
						htm +="<td class='tdpadding'>"+data[i].purchasePrice+"</td>";
						htm +="<td class='tdpadding'>"+data[i].paidMoney+"</td>";
						htm +="<td class='tdpadding' name='paidCount'>"+data[i].paidCount+"</td>";
						htm +="</tr>";
					}
				}
				$("#tbody_detail").html(htm);
			}
		});
		
	}
	
	(function(){
		changePaymentType();
	})();
	
</script>
</body>


</html>