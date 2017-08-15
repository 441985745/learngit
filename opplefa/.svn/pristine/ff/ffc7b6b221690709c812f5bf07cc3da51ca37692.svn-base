<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton"%>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" type="text/css" href="${staticURL}/assetcard/css/assetcardlist.css">
<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">

<title>Insert title here</title>
<style type="text/css">

</style>
</head>
	<body>
	
	<div>
    <input type="hidden" name="hfQueryStatus" id="hfQueryStatus" value="show">
    <table cellspacing="0" cellpadding="0" style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form  id="serachHeadCard">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif" style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                       	</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="t_ShipInportInfo" class="adminlist" style="visibility: visible;">
                            <tbody>
                            	<tr>
			
				<td class="td_text">系统管理编码：</td>
				<td class="td_span">
				<input type="text" style="width: 194px;" name="systeManagementCode" value="${param.systeManagementCode}">
				
				</td>
					<td class="td_text">成本中心：</td>
				<td class="td_span"><input type="text" style="width: 194px;" name="persentDepartment" value="${param.persentDepartment}"/></td>
			</tr>
			<tr>
				<td class="td_text">资产编码：</td>
				<td class="td_span"><input type="text" style="width: 194px;" name="assetCode" value="${param.assetCode}"/></td>
				<td class="td_text">资产名称：</td>
				<td class="td_span"><input type="text" style="width: 194px;" name="assetName" value="${param.assetName}"/></td>
			</tr>
			<tr>
				<td class="td_text">是否一次性费用化资产：</td>
				<td class="td_span">
					<select class="my-text"  name="isExpenseAssets" id="isExpenseAssets">
						<option value="">请选择</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
				<td class="td_text">现资产责任人：</td>
				<td class="td_span"><input type="text" style="width: 194px;" name="assetPerson" value="${param.assetPerson}"/></td>
			</tr>
			<tr>
			 <td class="td_text">品牌/规格/型号：</td>
				<td class="td_span"><input type="text" style="width: 194px;" name="specificationModel" value="${param.specificationModel}"/></td>
			
				<td class="td_text">办公地点：</td>
				<td class="td_span"><input type="text" style="width: 194px;" name="officeLocation" value="${param.officeLocation}"/></td>
			</tr>
			<tr>
				<td class="td_text">使用状态：</td>
				<td class="td_span">
					<select  class="my-text"  name="useState" id="useState">
						<option value="">请选择</option>
						<option value="0">在用</option>
			 			<option value="1">闲置中</option>
			 			<option value="2">大修理中</option>
			 			<option value="3">改造中</option>
			 			<option value="4">报废申请中</option>
			 			<option value="5">已报废</option>
			 			<option value="6">变卖申请中</option>
			 			<option value="7">已变卖</option>
					</select>
				</td>
				<td class="td_text">现归属公司：</td>
				<td class="td_span">
					<input type="text" style="width: 194px;" name="companyName" value="${param.companyName}">
				</td>
		
			<tr>
					<td class="td_text" >资产类型：</td>
				<td class="td_span">
					<input  name="assetType" type="hidden" id="assetHiddenType" value="${param.assetType}">
					<select class="my-text"  id="assetType" onchange="changeType()">
						<option value="">请选择</option>
						<option value="0">IT资产</option>
						<option value="1">行政资产</option>
						<option value="2">计量器具</option>
						<option value="3">机器设备</option>
					</select>
					
				</td>
				<td class="td_text">资产状态：</td>
				<td class="td_span">
				<select  class="my-text"  name="assetState" id="assetState">
					<option value="">请选择</option>
		 			<option value="0">预验收</option>
		 			<option value="1">已验收</option>
	 			</select>
				</td>
			</tr>
			<tr>
				<td class="td_text">是否调拨占用：</td>
				<td>
				<select class="my-text"  id="isAllocationOccupancy" name="isAllocationOccupancy">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				</td>
				<td class="td_text">资产分类：</td>
				<td>
				<input type="text" style="width: 194px;" name="assetClassification" value="${param.assetClassification}">
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
                <input class="btn" type="button" value="查询"  onclick="btnQuery()"  > 
                </auth:authTag>
             <auth:authTag code="Apply">
			<input class="btn" type="button" value="新增" onclick="addCard('1','数据新增');" > 
			</auth:authTag>
			<!-- <input class="btn" type="button" value="删除"  onclick="deleteAsset()" >  -->
				
			<auth:authTag code="GetSap">
			<input class="btn" type="button" value="获取SAP资产" id="updateSapAsset" onclick="getSAPAsset();">
			</auth:authTag> 
			<!-- 	<input class="btn" type="button" value="更新使用人部门信息" >  -->
			<auth:authTag code="Import">
			<input class="btn" type="button" value="导入" id="btnImport"> 
			</auth:authTag>
			<auth:authTag code="Export">
			<input class="btn" type="button" value="导出" id="btnExport"> 
			</auth:authTag>
			<!-- <a onclick="printSeal()" rel="external nofollow" target="_self"><button class="btn"  type="button"  >打印</button></a> -->
			  <auth:authTag code="Delete">
			<input class="btn" type="button" value="删除" onclick="closeAsset()">
			 </auth:authTag>
            </td>
        </tr>
        <tr >
            <td valign="top" style="width: 100%;">
                <div id="gridview"  style="width: 100%; height: 310px;">
                    <div>
                        <table class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border-collapse: collapse;">
                            <tbody>
                           <tr style="white-space: nowrap;">
					<th scope="col"><input  class="input_checkbox" type="checkbox" id="checkTrue" onclick="check()"/></th>
				    <th scope="col">操作</th>
				    <th scope="col">操作</th>
					<th scope="col">系统管理编码</th>
					<th scope="col">资产编号</th>
					<th scope="col">资产名称</th>
					<th scope="col">资产状态</th>
					<th scope="col">资产类型</th>	
					<th scope="col">资产分类</th>		
					<th scope="col">规格型号</th>
					<th scope="col">原值</th>
					<th scope="col">净值</th>
					<th scope="col">使用状态</th>
					<th scope="col">办公地点</th>
					<th scope="col">成本中心</th>
					<th scope="col">资产负责人</th>
					<th scope="col">入账日期</th>
					<th scope="col">归属公司</th>
					<th scope="col">是否调拨中</th>
					<th scope="col">是否一次性费用化资产</th>
					<th scope="col">现存放位置</th>
					<!-- <th >审批状态</th>
					<th >是否为草稿</th> -->
				</tr>
			 <c:forEach items="${assetCardPager.records}" var="assetCard" varStatus="status">
				<!-- <tr   style="white-space: nowrap; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"> -->
				<tr style="white-space: nowrap;">
					<td ><input class="input_checkbox" type="checkbox" name="checkboxAsset" id="checkboxAsset" value="${assetCard.id},${assetCard.isExpenseAssets}"/></td>
					<td ><a href="#" onclick="showUpdateAssetCard('${assetCard.id}','数据修改');" >修改</a></td>
					<td ><a href="#" onclick="searchCard('${assetCard.id}','数据查看');" >查看</a></td>
					<td class="td_font">${assetCard.systeManagementCode}</td>
					<td class="td_font">${assetCard.assetCode}</td>
					<td class="td_font">${assetCard.assetName}</td>
				
					<td>
					<c:choose>
				 			<c:when test="${assetCard.assetState=='0'}">
				 				预验收
				 			</c:when>
			 				<c:when test="${assetCard.assetState=='1'}">
				 				已验收
				 			</c:when>
				 	</c:choose>
					
					</td>

						<td class="td_font">	
						<c:choose>
				 			<c:when test="${assetCard.assetType=='0'}">
				 				IT资产
				 			</c:when>
				 			<c:when test="${assetCard.assetType=='1'}">
				 				行政资产
				 			</c:when>
				 			<c:when test="${assetCard.assetType=='2'}">
				 				计量器具
				 			</c:when>
				 			<c:when test="${assetCard.assetType=='3'}">
				 				机器设备
				 			</c:when>
				 		</c:choose>
					</td>
					<td class="td_font">${assetCard.assetClassification	}</td>
					<td class="td_font">${assetCard.specificationModel}</td>
				
					<td class="td_macth" isMoney='Y'>${assetCard.assetOriginalValue}</td>
					<td class="td_macth" isMoney='Y'>${assetCard.assetNetValue}</td>
					<td class="td_font">
					<c:choose>
			 			<c:when test="${assetCard.useState=='0'}">
			 				在用
			 			</c:when>
			 			<c:when test="${assetCard.useState=='1'}">
			 				闲置中
			 			</c:when>
			 			<c:when test="${assetCard.useState=='2'}">
			 				大修理中
			 			</c:when>
			 			<c:when test="${assetCard.useState=='3'}">
			 				改造中
			 			</c:when>	
			 			<c:when test="${assetCard.useState=='4'}">
			 				报废申请中
			 			</c:when>	
			 			<c:when test="${assetCard.useState=='5'}">
			 				已报废
			 			</c:when>	
			 			<c:when test="${assetCard.useState=='6'}">
			 				变卖申请中
			 			</c:when>	
			 			<c:when test="${assetCard.useState=='7'}">
			 				已变卖
			 			</c:when>
			 		</c:choose>
					
					</td>
					<td class="td_font">${assetCard.officeLocation}</td>
					<td class="td_font">
					
					<c:if test="${!fn:contains(assetCard.persentDepartment, '(')}">(${assetCard.costCenterCode})</c:if>${assetCard.persentDepartment}
					</td>
					
					<td class="td_font">(${assetCard.assetPersonCode})${assetCard.assetPerson}</td>
					<td class="td_date"><fmt:formatDate value="${assetCard.postingDate}"  pattern="yyyy-MM-dd"/></td>
					<td class="td_font">
					<c:if test="${!fn:contains(assetCard.companyName, '(')}">(${assetCard.companyCode})</c:if>${assetCard.companyName}
					</td>
					<td class="td_font">
					<c:choose>
						<c:when test="${assetCard.isAllocationOccupancy=='0'}">
			 				
			 			</c:when>
			 			<c:when test="${assetCard.isAllocationOccupancy=='1'}">
			 				调拨申请中
			 			</c:when>
			 		</c:choose>
					</td>
					<td class="td_font">${assetCard.isExpenseAssets=="0"?"否":"是"}</td>
					
					<td class="td_font">${assetCard.location}</td>
				</tr>
			 </c:forEach>
		
                            </tbody>
                        </table>
                       <tag:paginate total="${assetCardPager.total}"  offset="${assetCardPager.offset}" steps="${assetCardPager.pageSize}" uri="${assetCardPager.uri}" formId="serachHeadCard" />
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

		<jsp:include page="import_window.jsp"></jsp:include> 
	<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>  
	<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
	<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
	<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	//列表动态yanse
	 GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
  	 function autoSize() {
	        $("#gridview").width($(window).width()-2);
	        $("#gridview").height(
	                $(window).height() - $("#Query").height()
	                - $("#Button").height()
	                - $("#AspNetPager").height());
	    }
	    autoSize();
	    window.onresize = autoSize;  
	  //指定区域打印
    function printSeal(){ 
		
    	document.body.innerHTML=document.getElementById('contentId').innerHTML; 
        window.print(); 
    }
 	
	//新增
	function addCard(id,title) {
	     var lurl = "${dynamicURL}/assetCard/addAssetCard";
	     
         var dlg = new $.dialog({ id: "addCard", title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
     }
	function searchCard(id,title){
			
	 	 var lurl = "${dynamicURL}/assetCard/assetCardDetail?id="+id;
         var dlg = new $.dialog({ id: "serchCard", title: title, cover: true, page:lurl, rang: true, width: 1020, height:500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
	}
	function showUpdate(id,title){
		 var lurl = "${dynamicURL}/assetCard/assetCardUpdateDetail?id="+id;
         var dlg = new $.dialog({ id: "update", title: title, cover: true, page:lurl, rang: true, width: 1020, height:500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
	}
	//修改
	function showUpdateAssetCard(id, title) {
	    $.ajax({
			type: "POST",
			url: "${dynamicURL}/assetCard/ifUpdatePermitted?id=" + id,
			
			success: function (data) {
				if (data.success) {
					showUpdate(id,"数据修改");
				} else {
					alert(data.errorMessages.join());
				}
			}
		});
		
	} 
	//查询
 	function btnQuery(){
 		  window.location.href = '${dynamicURL}/assetCard/searchAssetCard?' + $('#serachHeadCard').serialize();
 	}
	//复选框的选中与重置
	function check(){
		var chk = document.getElementById('checkTrue');
		if(chk.checked){
			
			$("input:checkbox[name=checkboxAsset]").each(function(){
				 $(this).attr('checked', true);
			});

		}
		else{
			$("input:checkbox[name=checkboxAsset]").each(function(){
				 $(this).attr('checked', false);
			});
		}
	}
	 $(function(){
		 
		 	$("#isUpdateSap").val("${param.isUpdateSap}");
			$("#isExpenseAssets option[value='${param.isExpenseAssets}']").attr("selected",true);
			$("#useState option[value='${param.useState}']").attr("selected",true);
			$("#assetState option[value='${param.assetState}']").attr("selected",true);
			$("#assetType option[value='${param.assetType}']").attr("selected",true);
			$("#isAllocationOccupancy option[value='${param.isAllocationOccupancy}']").attr("selected",true);
		

		});
		//select改变时div的显示与隐藏
		function changeType() {
			var s = $("#assetType").find("option:selected").val();
			if(s ==""){
				$("#assetHiddenType").val("");
			}
			if (s == "0") {
				$("#assetHiddenType").val("0");

			}
			if (s == "1") {
				$("#assetHiddenType").val("1");

			}
			if (s == "2") {
				$("#assetHiddenType").val("2");

			}
			if (s == "3") {
				$("#assetHiddenType").val("3");

			}
		}

/* 		function deleteAsset() {

			var spCodesTemp = "";

			var ids = "";

			$('input:checkbox[name=checkboxAsset]:checked').each(function(i) {
				spCodesTemp = $(this).val();
				
				if (ids != null && ids != "") {

					ids = spCodesTemp + "#" + ids;
				} else {
					ids = spCodesTemp;
				}
			});
			
			if (ids != null && "" != ids) {
				if (window.confirm("确认要删除吗?")) {
					$.ajax({
						type : "get",
						contentType : "application/json",
						url : "deleteAssetCard",
						data : {
							"ids" : ids
						},
						success : function(data) {
							if (data.success) {
								alert("删除成功！");
								$("input:checkbox[name=checkboxAsset]").each(
										function() {
											$(this).attr('checked', false);
										});
								window.location.reload();
							} else {
								alert(data.errorMessages.join());
							}

						}
					});
				}
			} else {
				alert("请至少选择一条数据");
			}

		} */
		function closeAsset() {
			var spCodesTemp = "";

			var ids = "";

			$('input:checkbox[name=checkboxAsset]:checked').each(function(i) {
				spCodesTemp = $(this).val().split(",")[0];
				
				if (ids != null && ids != "") {

					ids = spCodesTemp + "," + ids;
				} else {
					ids = spCodesTemp;
				}

			});
			
			if (ids != null && "" != ids) {
				if (window.confirm("确认要删除吗?")) {
					$.ajax({
						type : "get",
						contentType : "application/json",
						url : "${dynamicURL}/assetCard/api/closeAssetCard",
						data : {
							"ids" : ids
						},
						success : function(data) {
							if (data.success) {
								alert("删除成功！");
								$("input:checkbox[name=checkboxAsset]").each(
										function() {
											$(this).attr('checked', false); 
										});
								window.location.reload();
							} else {
								alert(data.errorMessages.join());
							}

						}
					});
				}
			} else {
				alert("请至少选择一条数据");
			}

		}

		function qiehuan() {

			if ($(".trDiv").css("display") == "none") {

				$(".trDiv").show();

			} else {

				$(".trDiv").hide();

			}
		}
		//excle导出
		
		$("#btnExport").click(function() {
			if(!$("#assetHiddenType").val()){
				alert("导出功能必须选择资产类型");
				return false;
			}
			if (window.confirm("确认要导出吗?")) {
				window.location.href = "${dynamicURL}/assetCard/Export?"
						+ $('#serachHeadCard').serialize();
			}
		});
		// 导入和模版下载窗口
		var ew = new EasyWindow();
	
		ew.initEasyWindow("资产卡片信息导入",
				"${dynamicURL}/assetCard/api/uploadGeneralAssetExcel");

		$('#btnImport').click(function() {
			ew.showEasyWindow();
		});
	//打印
	function printSeal(){
	
		var boxLength=$('input:checkbox[name=checkboxAsset]:checked').length;
		if(boxLength==0){
			alert("请选择一条数据");
		}
		else{
			if(boxLength>1){
				alert("只能选择一条数据");
			}
			else{
				var id=$('input:checkbox[name=checkboxAsset]:checked').val();

				window.open("${dynamicURL}/assetCard/assetCardStamp?id="+id);
			}
		}

		
	}
	function getSAPAsset(){
		var ids="";
		$("#updateSapAsset").val("获取SAP资产ing");
		$("#updateSapAsset").attr("disabled",true);
		var assetType=$("#assetType").val();
		$("input:checkbox[name=checkboxAsset]:checked").each(function(){
			var id=$(this).val();
			isEx=id.split(",")[1];
			cardId=id.split(",")[0];
			if(isEx=='0'){
				if(ids==""){
					ids=cardId;
				}else{
					ids=ids+","+cardId;
				}
			}
		});
	 	if(ids==""){
			if (window.confirm("确定获取全部SAP资产并更新本地?")) {
				$.ajax({
					type:"post",
					url:"${dynamicURL}/assetCard/getGeneralAssetSap?ids="+ids,
					data:{assetType:assetType},
					success:function(data){
						if(data.error==""){
							alert("获得SAP成功！");
							window.location.reload();
						}
						else{
							$("#updateSapAsset").val("获取SAP资产");
							$("#updateSapAsset").attr("disabled",false);
							
							alert(data.errorList);
						}
					}
					
				});
			}else{
				$("#updateSapAsset").val("获取SAP资产");
				$("#updateSapAsset").attr("disabled",false);
			}
		}else{
			if (window.confirm("确定获取SAP资产并更新本地?")) {
				$.ajax({
					type:"post",
					url:"${dynamicURL}/assetCard/getGeneralAssetSap?ids="+ids,
					data:{assetType:assetType},
					success:function(data){
						if(data.error==""){
							alert("获得SAP成功！");
							window.location.reload();
						}
						else{
							alert(data.errorList);
							$("#updateSapAsset").val("获取SAP资产");
							$("#updateSapAsset").attr("disabled",false);
						}
					}
					
				});
			}else{
				$("#updateSapAsset").val("获取SAP资产");
				$("#updateSapAsset").attr("disabled",false);
			}
		} 
		
		}
    /**
	    * 数字格式转换成千分位
	    */
	    function fmoney(s, n){
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
	    $(function(){
	        $("td[isMoney='Y']").each(function(){
	            if($(this).html()!=""){
	                $(this).html(fmoney($(this).html(),2));
	            };
	        });
	    })
	    function showDemandOrder(id) {
	    	var lurl = "${dynamicURL}/demand/showDemandOrder?id=" + id+"&typeId=77001";
	    	var dlg = new $.dialog({ id: 'showForDemand', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
		}
	    function showApplyOrder(id) {
	    	var lurl = "${dynamicURL}/apply/showApplyOrder?id=" + id+"&typeId=77011";
	    	var dlg = new $.dialog({ id: 'showForApply', title: '数据查看', cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
	    }
	</script>

   </body>
 
</html>