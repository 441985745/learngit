<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收货单修改</title>
<%@ include file="/WEB-INF/layouts/common.jsp" %>
<meta name="modulePath" content="basic/" />
<link href="${staticURL}/Styles/FormPanel.css" type="text/css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
<style type="text/css">
#page-selection ul li {
	display: inline;
}
</style>
</head>
<body>
  <div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
        <div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
                收货单修改</div>
    </div>
    <div id="Div2" style="margin-left: 10px; margin-top: 0px;">
        <input type="button" name="" onclick="btnReceiveGoods(-1)" value="提交" id="btnSaveReceiveGoods" class="btn">
	        <input type="button" name="" onclick="btnReceiveGoods(-2)" value="保存草稿" id="btnSaveReceiveGoodsDraft" class="btn">
			<input type="button" name="" value="返回" onclick="dg.cancel();" id="" class="btn">
	 </div>

    <div style="margin-top: 5px;">
    
    <div id="receiveGoodsTab" class="easyui-tabs" style="width:1000px;padding:10px;">
       <div title="收货信息" id="tabsa" style="overflow:auto; padding:10px;">
       <div>
		<form action="receiveGoods" id="receiveGoodsForm">
	    	<table id="addReceiveGoodsTable" border="0" cellpadding="3px" cellspacing="0" width="90%">
	           <tbody>
	            <tr>
	            	<td align="right" class="tdtitle">采购员:
	                </td>
	                <td class="tdtext">
	                    <input name="buyerName" value="${receiveGoods.buyerName }" type="text" id="buyerName" class="colorblur">
	                    <input name="buyerCode" value="${receiveGoods.buyerCode }" type="hidden" id="buyerCode" class="colorblur">
	                </td>
	                <td align="right" class="tdtitle">收货编码:
	                </td>
	                <td class="tdtext">
	                    <input name="cdocument" value="${receiveGoods.cdocument}" type="text" id="cdocument" class="colorblur">
	                </td>
	                <td align="right" class="tdtitle">供应商:
	                </td>
	                <td class="tdtext">
	                    <input name="supplierName" value="${receiveGoods.supplierName }" type="text" id="supplierName" class="colorblur">
	                </td>
	            </tr>
	            <tr>
	            	<td align="right" class="tdtitle">公司名称:
	                </td>
	                <td class="tdtext">
	                    <input name="companyName" value="${receiveGoods.companyName }" type="text" id="companyName" class="colorblur">
	                    <input name="companyCode" value="${receiveGoods.companyCode }" type="hidden" id="companyCode" class="colorblur">
	                </td>
	                <td align="right" class="tdtitle">成本中心:
	                </td>
	                <td class="tdtext">
	                    <input name="costCenter" value="${receiveGoods.costCenter }" type="text" id="costCenter" class="colorblur">
	                    <input name="costCenterCode" value="${receiveGoods.costCenterCode }" type="hidden" id="costCenterCode" class="colorblur">
	                </td>
	                <td align="right" class="tdtitle">资产类型:
	                </td>
	                <td class="tdtext">
	                    <input name="assetsType" value="${receiveGoods.assetsType }" type="text" id="assetsType" class="colorblur">
	                </td>
                <tr>
	                <td align="right" class="tdtitle">申请部门所属平台:
	                </td>
	                <td class="tdtext">
	                    <input name="platform" value="${receiveGoods.platform }" type="text" id="platform" class="colorblur">
	                </td>
	                <td align="right" class="tdtitle">采购员备注:
	                </td>
	                <td class="tdtext">
	                    <input name="buyerRemark" value="${receiveGoods.buyerRemark }" type="text" id="buyerRemark" class="colorblur">
	                </td>
	                
	                <td align="right" class="tdtitle">供应商编码:
	                </td>
	                <td class="tdtext">
						<input name="supplierCode" value="${receiveGoods.supplierCode }" type="text" id="supplierCode" class="colorblur">
	                </td>
	                
                </tr>
	                <td align="right" class="tdtitle">订单编码:
	                </td>
	                <td class="tdtext">
	                    <input name="purchaseId"  value="${receiveGoods.purchaseId}" type="text" id="purchaseId" class="colorblur">
	                </td>
	                
	                <td align="right" class="tdtitle">抬头文本说明:
	                </td>
	                <td class="tdtext">
	                    <input name="headerTextDescription" value="${receiveGoods.headerTextDescription }" type="text" id="headerTextDescription" class="colorblur">
	                </td>
	                <!-- 抬头文本说明 （订单头） -->
	                <td align="right" class="tdtitle">合同号:
	                </td>
	                <td class="tdtext">
	                    <input name="contractNo" value="${receiveGoods.contractNo }"  type="text" id="contractNo" class="colorblur">
	                </td>
	                <!-- 合同号（手填） -->
	            </tr>
	            <tr>
	                 <td align="right" class="tdtitle">申请部门:
	                </td>
	                <!-- 申请明细 抓取-->
	                <td class="tdtext">
	                    <input name="applyDepartment" value="${receiveGoods.applyDepartment }"  type="text" id="applyDepartment" class="colorblur">
	                    <input name="applyDepartmentCode" value="${receiveGoods.applyDepartmentCode }"  type="hidden" id="applyDepartmentCode" class="colorblur">
	                </td>
	                <!-- (采购申请的部门 -->
	                <td align="right" class="tdtitle">需求部门:
	                </td>
	                <td class="tdtext">
	                    <input name="budgetDepartmentName" value="${receiveGoods.budgetDepartmentName }"  type="text" id="budgetDepartmentName" class="colorblur">
	                    <input name="budgetDepartmentCode" value="${receiveGoods.budgetDepartmentCode }"  type="hidden" id="budgetDepartmentCode" class="colorblur">
	                </td>
	                <td align="right" class="tdtitle">办公地点:
	                </td>
	                <td class="tdtext">
	                    <input name="officeLocation" value="${receiveGoods.officeLocation}"  type="text" id="officeLocation" class="colorblur">
	                </td>
	            </tr>
	            </tbody>
	         </table>
		</form>

        </br>
       <div style="margin-top: 10px">
	        <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" class="tablenoborder">
	            <tbody>
	            <tr>
	                <td colspan="4">
				    <form action="receiveGoodsDetail" id="receiveGoodsDetailForm">
	                    <table id="Table10" width="100%"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
	                        <tbody>
	                        <tr>
	                            <td class="tdtitle" style="text-align: right" colspan="2">
	                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
	                                    <div>
	                                        <table id="table_add_detail" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
	                                            <tr style="white-space:nowrap;">
	                                                <th class='tdpadding' scope="col">序号</th>
	                                                <th class='tdpadding' scope="col">资产名称</th>
	                                                <th class='tdpadding' scope="col">单位</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>订单数量</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>已收货数量</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>税率</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>单价（含税）</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>总价（含税）</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>总账科目</th><!-- 申请单带出 -->
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>交货日期</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>验收日期</th><!-- 手选的 -->
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>保固期</th><!-- 手写的 -->
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>存放位置</th><!-- 手写的 -->
	                                            </tr>
	                                            <tbody id="tbody_add_detail">
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
       <div style="margin-top: 10px">
	        <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" class="tablenoborder">
	            <tbody>
	            <tr>
	                <td colspan="4">
				     <form action="receiveGoodsAssetsSap" id="receiveGoodsAssetsSapForm">
	                    <table id="Table10" width="100%"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
	                        <tbody>
	                        <tr>
	                            <td class="tdtitle" style="text-align: right" colspan="2">
	                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
	                                    <div>
	                                        <table id="table_add_detail" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
	                                            <tr style="white-space:nowrap;">
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>需求单号</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>资产名称</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>sap资产编码</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>规格参数</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>功能</th>
	                                                <th class='tdpadding' scope="col"><font color="red" >*</font>验收人</th>
	                                            </tr>
	                                            <tbody id="tbody_sap_detail">
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
        </div>
        </div>
       </div>
      </div>
    
<!-- 需求单明细 弹框-->
<div id="addDemandDetailWindow" name="addDemandDetailWindow" class="easyui-window" title="新增收货单需求详情" data-options="iconCls:'icon-save'" style="width:800px;height:400px;padding:5px;" closed="true">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding:10px;">
	    <div id="Div3" style="margin-left: 0px; margin-top: 0px;">
	      			<input type="button" name="" value="确定"  id="savebtnDemandDetail" class="btn"> 
					<input type="button" name="btnCancel" value="返回"  id="" class="btn">
					
	    </div>
	    <div style="margin-top: 10px">
	        <table id="addReceiveGoodsDetailTable" cellpadding="0px" cellspacing="0" class="tablenoborder">
	            <tbody>
	            <tr>
	                <td colspan="4">
				    <form action="addDemandDetail" id="addDemandDetailForm">
	                    <table id="Table10" width="100%"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
	                        <tbody>
	                        <tr>
	                            <td class="tdtitle" style="text-align: right" colspan="2">
	                                <div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
	                                        <table id="table_add_detail" class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
	                                            <tr style="white-space: nowrap;">
<!-- 													<th scope="col"> -->
<!-- 													  <input id="gvDataList_CheckAll" type="checkbox" name="gvDataList$ctl01$CheckAll" -->
<!-- 	                                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'table_add_detail');" disabled="disabled"/> -->
<!-- 													</th> -->

													<th scope="col">&nbsp;&nbsp;<font color="red">*</font>需求单号&nbsp;&nbsp;
													</th>
													<th scope="col">&nbsp;&nbsp;<font color="red">*</font>资产名称&nbsp;&nbsp;
													</th>
													<th scope="col">&nbsp;&nbsp;<font color="red">*</font>资产类型&nbsp;&nbsp;
													</th>
													<th scope="col">&nbsp;&nbsp;<font color="red">*</font>订单需求数量&nbsp;&nbsp;
													</th>
													<th scope="col">&nbsp;&nbsp;<font color="red">*</font>已收货的订单需求数量&nbsp;&nbsp;
													</th>
<!-- 													<th scope="col">&nbsp;&nbsp;<font color="red">*</font>本次收货的订单需求数量&nbsp;&nbsp; -->
<!-- 													</th> -->
													<th scope="col">&nbsp;&nbsp;<font color="red">*</font>项目编码&nbsp;&nbsp;
													</th>
													<th scope="col">&nbsp;&nbsp;<font color="red">*</font>规格参数&nbsp;&nbsp;
													</th>
													<th scope="col">&nbsp;&nbsp;<font color="red">*</font>功能&nbsp;&nbsp;
													</th>
													
												</tr>
												<tbody id="tbody_demand_detail">
	                                            </tbody>
	                                         </table>
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
		</div>
	</div>
</div>
           	
<!-- 存放位置 s-->
<div id="storageLocationWindow" name="" class="easyui-window" title="选择存放位置" data-options="iconCls:'icon-save'" style="width:750px;height:380px;padding:5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
			    
			<form id="storageLocationForm">
				<table class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="0">
				<tr style="text-align: left">
<!-- 				<td >办公地点:</td> -->
<!-- 				<td name=""><input type="text" id="searchStorageLocationCode" name="supplierCode"></td> -->
				<td>存放位置:</td>
				<td><input name="" id="searchStorageLocationName" type="text" ></td>
				</tr>
			</form>
			<input type="button" value="查询" id="searchStorageLocation"><input type="button" id="sureStorageLocation" value="确定">
				<table id="supplierBudget" class="adminlist" rules="all" style="border-collapse:collapse;" cellspacing="0" border="1" cellpadding="6px">
				<thead>
					<tr>
						<th class='tdpadding'></th>
						<th class='tdpadding'>存放位置</th>
						<th class='tdpadding'>办公地点</th>
					
					</tr>
				</thead>
				<tbody id="tbody_storageLocation"></tbody>
				</table>
					
			</div>
			
		</div>
	</div>
<!-- 存放位置 e-->
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>	
	<script type="text/javascript">
	var dg = frameElement.lhgDG;//定义dg 关闭需要
// 	var W = dg.curWin;// 获得父窗体
	   /* 存放位置弹框 s*/
	 var $inputStorageLocation= null;
	$('#storageLocationShow').live("click",function(){ //存放位置弹框
		 $inputStorageLocation = $(this);
		
		var top = "50px";//$(this).offset().top - 350;
		var left = "70px";//$(this).offset().left-180;
		$('#storageLocationWindow').window('open').window('resize',{width:'900px',height:'700px',top: top,left:left});
		var officeLocationTemp = $('#officeLocation').val();
		showStorageLocation(officeLocationTemp);
	})
	function showStorageLocation(storageLocationTemp,officeLocationTemp){ //展示列表存放位置的列表
		$.ajax({
			type: "POST",
			url:"${dynamicURL}/receiveGoods/api/searchStorageLocationByOfficeLocation",
	    	data : {"storageLocation":storageLocationTemp,"officeLocation":officeLocationTemp},
	    	  success: function(data){
	              var html = ''; 
	              $.each(data, function(commentIndex, comment){
	                     html += '<tr style="white-space:nowrap;"><td class="tdpadding"><input type="radio" name="storageLocationRadio" value='+commentIndex+'>'
	                          + '</td><td class="tdpadding" id=\"storageLocation'+commentIndex+'\">' + (comment['storageLocation']==null?"":comment['storageLocation'])
	                          + '</td><td class="tdpadding" id=\"officeLocation'+commentIndex+'\">' + (comment['officeLocation']==null?"":comment['officeLocation'])
	                          + '</td></tr>';
	               });
	              $('#tbody_storageLocation').html(html);
	              
	            }
	   	});
	}
	$('#searchStorageLocation').click(function(){//模糊查询存放位置
		var storageLocationTemp = $('#searchStorageLocationName').val();
		var officeLocationTemp = $('#officeLocation').val();
		showStorageLocation(storageLocationTemp,officeLocationTemp);
		
	})

		$("#sureStorageLocation").click(function(){ //确定存放位置
			var i = $("input[name='storageLocationRadio']:checked").val();
			if(!i){
				alert("未选择任何一行数据!");
				return false;
			}
			var storageLocationTemp = $.trim($("#storageLocation"+i).html());

			$('#storageLocationWindow').window('close');
			$inputStorageLocation.parent().parent().find('#storageLocationLine').val(storageLocationTemp);
		});
	 
		/* 存放位置弹框 e*/
	
        
//     页面加载完后将需求明细全都获得 ---s--
var purchaseApplyMappingList = {}; // 全部需求
$(document).ready(function(){
	   	var purchaseId = $('#purchaseId').val();

	   	$.ajax({
			type: "POST",
			url:"${dynamicURL}/receiveGoods/api/searchPurchaseApplyMappingByPurchaseId",
	    	data : {"purchaseId":purchaseId},
	    	success:function(data){

	    	purchaseApplyMappingList = data;
	    	}
	    });
	});
//     页面加载完后将需求明细全都获得---e--
//  收货  行的数量 更改 弹框 --需求行明细显示  --s--
var $thisInput;
$('#goodsCountline').live("click",function(){
	$thisInput = $(this);
		var top = "50px";//$(this).offset().top - 350;
		var left = "70px";//$(this).offset().left-180;
		$('#addDemandDetailWindow').window('open').window('resize',{width:'800px',height:'400px',top: top,left:left});
		var applyDetailIdlineTemp = $(this).parent().parent().find('#applyDetailIdline').val();

   		var divHtml="";
        	for(var i = 0; i < purchaseApplyMappingList.length; i++){
        		if(applyDetailIdlineTemp == purchaseApplyMappingList[i].applyDetailId){
	            		divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";/* receiveGoodsId *//* purchaseDetailId *//* applyDetailId */
						divHtml += "<td class='tdpadding'><input type='text' name='demandId' id='demandId' value='"+purchaseApplyMappingList[i].demandId+"' readonly='readonly'><input type='hidden' name='demandDetailId' id='demandDetailId' value='"+purchaseApplyMappingList[i].demandDetailId+"' title='demandDetailId'></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='assetsName' value='"+purchaseApplyMappingList[i].assetsName+"'><input type='hidden' name='purchaseDetailId' id='' value='"+purchaseApplyMappingList[i].purchaseDetailId+"' title='purchaseDetailId'><input type='hidden' name='applyDetailId' value='"+purchaseApplyMappingList[i].applyDetailId+"' title='applyDetailId'> </td>";
						divHtml += "<td class='tdpadding'><input type='text' name='assetsType' id='assetsType' value='"+purchaseApplyMappingList[i].assetsType+"' readonly='readonly'><input type='hidden' name='sapMainAssetCode' value='"+purchaseApplyMappingList[i].sapMainAssetCode+"'><input type='hidden' name='sapSecAssetCode' value='"+purchaseApplyMappingList[i].sapSecAssetCode+"'><input type='hidden' name='demandCount' value='"+purchaseApplyMappingList[i].demandCount+"'>  </td>";
						divHtml += "<td class='tdpadding'><input type='text' name='applyCountDemand' id='applyCountDemand' value='"+purchaseApplyMappingList[i].applyCountDemand+"' readonly='readonly'></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='goodsCountDemand' id='goodsCountDemand' value='"+purchaseApplyMappingList[i].goodsCountDemand+"' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='projectCode' id='projectCode' value='"+purchaseApplyMappingList[i].projectCode+"' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='brand' id='brand' value='"+purchaseApplyMappingList[i].brand+"' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='functions' id='functions' value='"+purchaseApplyMappingList[i].functions+"' ></td>";
						divHtml += "</tr>";
        		}
        	 $("#tbody_demand_detail").html(divHtml);
        	}
});
//  收货  行的数量 更改 弹框 --需求行明细显示  --e--
	// 确定需求
 	var purchaseTemp = {};
 	$('#savebtnDemandDetail').click(function(){
 		var objDemandDetail = {
 				receiveGoodsId: "",
 				purchaseDetailId: "",
 				applyDetailId: "",
 				demandDetailId: "",
 				assetsName : "",
 				assetsType : "",
 				applyCountDemand : "",
 				goodsCountDemand : "",
 				projectCode : "",
 				brand : "",
 				demandCount : "",
 				sapMainAssetCode : "",
 				sapSecAssetCode : ""
 		};
		purchaseTemp = constructObjectFromTable(objDemandDetail, $("#addDemandDetailForm .bodyTr"));
		var goodsCountTemp = 0;
 		 for(var i = 0;i<purchaseApplyMappingList.length;i++){
			for(var j = 0; j<purchaseTemp.length; j++){
			    //console.log(purchaseApplyMappingList[i].demandDetailId +":"+ purchaseTemp[j].demandDetailId )
 			 if(purchaseApplyMappingList[i].demandDetailId == purchaseTemp[j].demandDetailId ){
 				purchaseApplyMappingList[i].goodsCountDemand = purchaseTemp[j].goodsCountDemand;
 				goodsCountTemp += parseInt(purchaseTemp[j].goodsCountDemand); 
			}
			}			
 		 }
 			$thisInput.val(goodsCountTemp); //
 			

 		$('#addDemandDetailWindow').window('close');
 		showSap(sapAssetsCodeETZMM);//刷新sap资产明细
 	})

// 表单封装Json 方法
 function constructObjectFromTable(object, $object){
	var javaObjArray = [];
		$object.each(function(i) {
			var $ck = $(this).find('input[name^=gvdata]');
			if($ck.length != 0){
				if(!$ck.attr('checked')){
					return true;
				}
			}
		     var javaObj = {};
		     for (var p in object) {
		    	 var $val = $(this).find('input[name="'+p+'"]');
		    	 if ($val.length === 0) {
		    		 javaObj[p] = $(this).find('select[name="'+p+'"]').val();
		    	 } else {
		    		 javaObj[p] = $val.val();
		    	 }
		     }
		     javaObjArray.push(javaObj);
		});
		return javaObjArray;
 	 };
 	 



//  页面加载完填充sap资产明细 --s-- 
	var receiveGoodsMappingLine = null;
	var  sapAssetsCodeETZMM = null;
	$(document).ready(function(){
	   	var purchaseId = $('#purchaseId').val();
	   	$.ajax({
			type: "POST",
			url:"${dynamicURL}/receiveGoods/api/addReceiveGoodsLine",
			data : {"purchaseId":purchaseId},
	    	success:function(data){
	    		 receiveGoodsMappingLine = data[0];
	       		  sapAssetsCodeETZMM = data[1]; // 这里只需要后台传来的所有的sap资产编码
	       		showline(receiveGoodsMappingLine);
	       		showSap(sapAssetsCodeETZMM);  		
//
	    	}
	    });
	});
//  页面加载完填充sap资产明细 --e-- 

	/* 收货单新增 行 弹框  s */
	function showline(receiveGoodsMappingLine){

			var divHtml="";
            		for(var i = 0; i < receiveGoodsMappingLine.length;i++){
            			divHtml += "<tr class='bodyTr' style='white-space:nowrap;'>";
	            		divHtml += "<td class='tdpadding'><input type='text' name='' value='"+(i+1)+"' size='1'><input type='hidden' name='purchaseId' id='purchaseId' value='"+receiveGoodsMappingLine[i].purchaseId+"' title='purchaseId'><input type='hidden' name='applyDetailId' id='applyDetailIdline' value='"+receiveGoodsMappingLine[i].applyDetailId+"' title='applyDetailId' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='assetsName' id='assetsName' value='"+receiveGoodsMappingLine[i].assetsName+"' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='units' id='units' value='"+receiveGoodsMappingLine[i].units+"'  ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='applyCount' id='applyCountline' value='"+receiveGoodsMappingLine[i].applyCount+"' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='goodsCount' id='goodsCountline' value='"+receiveGoodsMappingLine[i].goodsCount+"' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='tax' id='tax' value='"+receiveGoodsMappingLine[i].tax+"' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='purchaseUnitPrice' id='purchaseUnitPrice' value='"+receiveGoodsMappingLine[i].purchaseUnitPrice+"' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='purchasePrice' id='purchasePrice' value='"+receiveGoodsMappingLine[i].purchasePrice+"' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='ledgerAccount' id='ledgerAccount' value='"+receiveGoodsMappingLine[i].ledgerAccount+"' ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='deliveryDate' id='deliveryDate' value='"+FormatDate(receiveGoodsMappingLine[i].deliveryDate)+"' class='Wdate' onclick='WdatePicker({})'  ></td>";
						divHtml += "<td class='tdpadding'><input type='text' name='requireCheckDate' id='requireCheckDate' value='"+FormatDate(receiveGoodsMappingLine[i].requireCheckDate)+"' class='Wdate' onclick='WdatePicker({})' ></td>"; // 颜色还没确定 //要求验收日期
						divHtml += "<td class='tdpadding'><input type='text' name='recWarrantyPeriod' id='recWarrantyPeriod'  value='"+receiveGoodsMappingLine[i].recWarrantyPeriod+"' ></td>"; /* 保固期，手填 */
						divHtml += "<td class='tdpadding'><input style='width: 100px;'  name='storageLocation'  type='text' id='storageLocationLine' class='inputbtnRefViewGray' value='"+receiveGoodsMappingLine[i].storageLocation+"' ><input id='btnRefClear' class='btnRefView' title='点击清空参照文本框内容' type='button' value='X'><input id='storageLocationShow' class='btnRefView' title='点击打开参照文本框内容' type='button'  value='...'></td>"; /* 存放位置，手填 */
						divHtml += "</tr>";
// 						<td><fmt:formatDate value='${Order.applyDate}' pattern='yyyy-MM-dd'/></td>
	            	}
            	 $("#tbody_add_detail").html(divHtml);
	}
            		/* 收货单新增 弹框  e */    

	function FormatDate (strTime) {//格式化时间
		var date = new Date(strTime);
		return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	}

	/* sap资产次级编码   s  */
	function showSap(sapAssetsCodeETZMM){


	var divHtmlAssets="";
				 for(var j = 0;j<purchaseApplyMappingList.length;j++){ // 循环拿到本次收货对应的需求行和订单行
					var numTemp =0;
						for(var n = 0; n<sapAssetsCodeETZMM.length;n++){ // 循环所有的sap次级编码
							if((sapAssetsCodeETZMM[n].demandDetailId == purchaseApplyMappingList[j].demandDetailId)) { // 显示选定的需求行的
								numTemp += (j+1)/(j+1);
								if((numTemp/(purchaseApplyMappingList[j].goodsCountDemand)) <= 1){ // 显示的sap次级编码的个数 等于 本次收货时的对应的需求行
									divHtmlAssets += "<tr class='bodyTr' style='white-space:nowrap;'>";
									divHtmlAssets += "<td class='tdpadding'><input type='text' name='demandId' id='demandId' value='"+sapAssetsCodeETZMM[n].demandId+"' ><input type='hidden' name='demandDetailId' id='demandDetailId' value='"+sapAssetsCodeETZMM[n].demandDetailId+"' title='demandDetailId' ></td>";
									divHtmlAssets += "<td class='tdpadding'><input type='text' name='assetsName' id='assetsName' value='"+sapAssetsCodeETZMM[n].assetsName+"' ><input type='hidden' name='applyDetailId' id='applyDetailId' value='"+sapAssetsCodeETZMM[n].applyDetailId+"' title='applyDetailId' ><input type='hidden' name='purchaseId' id='purchaseId' value='"+sapAssetsCodeETZMM[n].purchaseId+"' title='purchaseId' ></td>";
									divHtmlAssets += "<td class='tdpadding'><input type='text' name='sapAssetsCode' id='sapAssetsCode' value='"+sapAssetsCodeETZMM[n].sapAssetsCode+"' ><input type='hidden' name='sapMainAssetCode' id='sapMainAssetCode' value='"+sapAssetsCodeETZMM[n].sapMainAssetCode+"' title='sapMainAssetCode' ><input type='hidden' name='sapSecAssetCode' id='sapSecAssetCode' value='"+sapAssetsCodeETZMM[n].sapSecAssetCode+"' title='sapSecAssetCode' ></td>";
									divHtmlAssets += "<td class='tdpadding'><input type='text' name='brand' id='brand' value='"+sapAssetsCodeETZMM[n].brand+"' ></td>";
									divHtmlAssets += "<td class='tdpadding'><input type='text' name='functions' id='functions' value='"+sapAssetsCodeETZMM[n].functions+"' ></td>";
									divHtmlAssets += "<td class='tdpadding'><input type='text' name='checkApplyUser' id='checkApplyUser' value='"+sapAssetsCodeETZMM[n].checkApplyUser+"' ><input type='hidden' name='checkApplyCode' id='checkApplyCode' value='"+sapAssetsCodeETZMM[n].checkApplyCode+"' title='checkApplyCode'></td>";
									divHtmlAssets += "</tr>";
								}
							}
						}
					 }
					 $("#tbody_sap_detail").html(divHtmlAssets);

	/* sap资产次级编码   e  */
	}

	function btnReceiveGoods(num){ // 提交
		 var receiveGoodsWrapper = {};
// 		 alert(num);
//		 头
		 var objHeader = $('#receiveGoodsForm').serializeObject();
		 if(-1 == num){
		 objHeader.draft= 'N'; //不是草稿
		 }else if(-2 == num){
		 objHeader.draft= 'Y'; //是草稿
		 }
		 receiveGoodsWrapper.receiveGoods = objHeader; //传 头

	//		 行
		var objlinedetail = {
				receiveGoodsId  : "",
				purchaseId : "",
				purchaseDetailId : "",
				applyDetailId : "",
				assetsName : "",
				units : "",
				applyCount : "",
				goodsCount : "",
				tax : "",
				purchaseUnitPrice : "",
				purchasePrice : "",
				ledgerAccount : "",
				deliveryDate : "",
				requireCheckDate : "",
				recWarrantyPeriod :"",// 保固期
				storageLocation : "" //存放位置
			}
			var objArrReceiveGoodsDetail = constructObjectFromTable(objlinedetail, $('#receiveGoodsDetailForm .bodyTr'));
			for ( var i = 0; i < objArrReceiveGoodsDetail.length; i++) {
				objArrReceiveGoodsDetail[i].receiveGoodsId = $('#cdocument').val();
			}
			receiveGoodsWrapper.receiveGoodsDetails = objArrReceiveGoodsDetail; //传 行 

//			行 对应的需求明细
			for ( var i = 0; i < purchaseApplyMappingList.length; i++) {
				purchaseApplyMappingList[i].receiveGoodsId = $('#cdocument').val();
			}
			receiveGoodsWrapper.receiveGoodsMappings = purchaseApplyMappingList; // 传 行对应的需求明细 (传的是全部的需求明细行，后台判断选定的订单行对应的需求明细行)
			// //行 对应的sap资产明细
			var objlineSapAsset = {
					receiveGoodsId  : "",
					demandId : "",
	 				demandDetailId: "",
	 				purchaseId : "",
	 				purchaseDetailId : "",
	 				applyDetailId : "",
	 				assetsName : "",
	 				sapMainAssetCode : "",
	 				sapSecAssetCode : "",
	 				sapAssetsCode : "",
	 				brand : "",
	 				functions: "",
	 				checkApplyUser: "",
	 				checkApplyCode: ""
						}
			sapAssetTemp = constructObjectFromTable(objlineSapAsset, $("#receiveGoodsAssetsSapForm .bodyTr"));
			for ( var i = 0; i < sapAssetTemp.length; i++) {
				sapAssetTemp[i].receiveGoodsId = $('#cdocument').val();
			}
			receiveGoodsWrapper.purchaseCommons = sapAssetTemp; // 传 行对应的需求明细

		$.ajax({
				type : "POST",
				dataType : "json",
				cache : false,
				contentType : "application/json; charset=utf-8",
				url : "${dynamicURL}/receiveGoods/api/updateSaveReceiveGoods",
				data : JSON.stringify(receiveGoodsWrapper),
				success : function(data) {
				if (data.errorMessages.length > 0) {
					 alert(data.errorMessages);
					 return;
				}else{
					alert(data.successMessage);
				 dg.cancel();
				 return;
				}
				}

			})
		}
</script>
</body>
</html>