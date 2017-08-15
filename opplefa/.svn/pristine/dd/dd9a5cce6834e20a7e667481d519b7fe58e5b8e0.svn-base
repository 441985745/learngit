<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资产盘点</title> 		


<link rel="Stylesheet" type="text/css" href="${staticURL}/assetcard/css/assetcardlist.css">
<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css"> 

</head>
<body>
   	  <fieldset id="fset_ShipInportInfo">
   			<legend>
   				<img alt="展开/收缩"   src="http://10.10.1.51/Images/constringency.gif"   >
   				查询条件
   			</legend>
   		 <div class="trDiv">
   	        <form id="fuzzyQuery">
   	  
	           <table class="table_head">
	     	      <tr>
	     		     <td class="td_text">单据号:</td>
	     		     <td class="td_span"><input id="documentNumber" value="${param.documentNumber}" name="documentNumber" type="text"></td>
	     		     <td class="td_text">盘点人:</td>
	     		     <td class="td_span"><input id="inventoryPeople"  value="${param.inventoryPeople}" name="inventoryPeople"  type="text"></td>
	     	      </tr>
	     	      <tr>
	     		     <td class="td_text">盘点部门:</td>
	     		     <td class="td_span"><input id="inventoryDepartment" value="${param.inventoryDepartment}" name="inventoryDepartment" type="text"></td>
	     		     <td class="td_text">盘点状态:</td>
	     		     <td class="td_span"><input id="inventoryStatus" value="${param.inventoryStatus}" name="inventoryStatus" type="text"></td>
	     	      </tr>
	     	      <tr>
	     		     <td class="td_text">盘点年:</td>
	     		     <td class="td_span"><input id="inventoryYear" value="${param.inventoryYear}" type="text" name="inventoryYear"></td>
	     		     <td class="td_text">盘点月:</td>
	     		     <td class="td_span"><input id="inventoryMonth" value="${param.inventoryMonth}" type="text" name="inventoryMonth"></td>
	     	      </tr>
	           </table>
	       </form>
        </div>
   	 </fieldset>
         <div>
           <table>
             <tr>
               <td colspan="4" class="buttonbar">
	               <input class="btn" type="button" name="btnQuery" value="查询" id="btnQuery">
	               <input class="btn" value="发起盘点" type="button" onclick="addCard('1','数据查看');" style="cursor:pointer;"/>
	               <input class="btn" value="录入盘点结果" type="button" style="cursor:pointer;" onclick="inputResult('1','录入盘点结果');"/>
<!-- 	               <input class="btn" value="盘点差异" type="button" style="cursor:pointer;" onclick="checkDifference('1','数据查看');"/> -->
	               <input class="btn" value="删除" type="submit" onclick="deleteCheck()"/>
	               <input class="btn" value="导出" type="submit"/>
<!-- 	               <a href="javascript:printSeal()" rel="external nofollow" target="_self"><button class="btn"  type="button">打印</button></a> -->
               </td>
             </tr>
          </table> 
       </div>
       <div style="overflow:scroll">
                        <form id="content">
							<table  id="gvDataList" style="width:1700px;" class="adminlist">
								<thead>
									<tr align="center">
										<th class="input_checkbox"><input type="checkbox"  id="checkAll" onclick="check()"/></th>
										<th>操作</th>
									    <th>操作</th>
									    <th>打印</th>
										<th>盘点单据号</th>
										<th>盘点年度</th>
										<th>盘点月</th>
										<th>盘点人</th>
										<th>盘点申请日期</th>
										<th>盘点状态</th>
										<th>审批状态</th>
										<th>盘点完结日期</th>
										<th>是否打印</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${assetCheckBusinessPager.records}" var="assetCheckBusiness" varStatus="status">
									<tr style="text-align: center;">
										<td><input class="input_checkbox" type="checkbox" value="${assetCheckBusiness.id}" name="checkboxAsset"></td>
										<td>修改</td>
										<td><a onclick="propertyExamine('1','数据查看');" style="cursor:pointer;">查看</a></td>
										<td><a href="javascript:void(0)" onclick="daYin('${assetCheckBusiness.id}')">打印</a></td>
										<td>${assetCheckBusiness.documentNumber}</td>
										<td>${assetCheckBusiness.inventoryYear}</td>
										<td>${assetCheckBusiness.inventoryMonth}</td>
										<td>${assetCheckBusiness.inventoryPeople}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${assetCheckBusiness.inventoryApplyDate}"/></td>
										<td>
	                                      <c:choose>
			 			                    <c:when test="${assetCheckBusiness.inventoryStatus=='0'}">
			 				                                 等待盘点
			 			                    </c:when>
			 			                    <c:when test="${assetCheckBusiness.inventoryStatus=='1'}">
			 				                                  正在盘点
			 			                    </c:when>
			 			                    <c:when test="${assetCheckBusiness.inventoryStatus=='2'}">
			 				                                  盘点完成
			 			                    </c:when>
	                                       </c:choose>
	                                     </td>
	                                     <td>
	                                      <c:choose>
			 			                    <c:when test="${assetCheckBusiness.approveState=='0'}">
			 				                                  等待审批
			 			                    </c:when>
			 			                    <c:when test="${assetCheckBusiness.approveState=='1'}">
			 				                                  正在审批
			 			                    </c:when>
			 			                    <c:when test="${assetCheckBusiness.approveState=='2'}">
			 				                                  审批完成
			 			                    </c:when>
	                                       </c:choose>
	                                     </td>
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${assetCheckBusiness.inventoryEndDate}"/></td>
										<td>1</td>
									</tr>
								</c:forEach>															
								</tbody>
							</table>
						<tag:paginate offset="${assetCheckBusinessPager.offset}" total="${assetCheckBusinessPager.total}" steps="${assetCheckBusinessPager.pageSize}" uri="${assetCheckBusinessPager.uri}" formId="fuzzyQuery" />
					</form>
				</div>
        
         
<%--  		<script src="${staticURL}/js/jquery-1.11.3.min.js"></script> --%>
<%-- 		<script src="${staticURL}/js/jquery.tableCheckbox.js"></script> 
		<script src="${staticURL}/js/browser.js"></script>  --%>
<%-- 		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script> --%>
	<%-- 	<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
		<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
		<script src="${staticURL}/Scripts/Purchase/DemandOrder/DemandOrder.js" type="text/javascript"></script>  --%>
		<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>  
		<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
		<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	
	function checkMultipleSelect_(tableId) {
		var checked = $('#' + tableId + ' td input[name = checkboxAsset]:checked');
	    if (checked.length >= 1) {
	    	
	        return true;
	    }

	    return false;
	}
	limitSingleCheck('gvDataList');
	
	 //列表动态yanse
	 GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
	 //弹出资产申请单
	  function addCard(id,title) {
		   var lurl = "check/sponsorCheck";
           var dlg = new $.dialog({ id: 'add', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
       }
	 //录入盘点结果
	 function  inputResult(id,title){
		 if (!checkMultipleSelect_('gvDataList')) {
			 alert("请选择一条要录入的单据！");
		 }
		 else {
			 var checkedId = $("#gvDataList td input[name = checkboxAsset]:checked").attr("value");
			 var lurl = "check//input/result?id="+checkedId;
			 var dlg = new $.dialog({ id: 'add', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
		 }
	 }
       //弹出盘点差异信息
	   function checkDifference(id,title) {
		  /* var lurl = "check/checkDifference?id="+id; */
		  var lurl = "check/checkDifference";
          var dlg = new $.dialog({ id: 'add', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
       }
       //查看
	   function propertyExamine(id,title) {
		  var lurl = "check/propertyExamine";
          var dlg = new $.dialog({ id: 'add', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
       }
	   //模糊查询
	   $("#btnQuery").click(function() {
	        window.location.href = '${dynamicURL}/check/assetCheck?' + $('#fuzzyQuery').serialize();
	    });
	   //资产盘点指定区域信息打印 
	   function printSeal(){ 
	        document.body.innerHTML=document.getElementById('content').innerHTML; 
	        window.print(); 
	    } 
	   //单条信息打印
	   function daYin(id){
		   alert();
		   /* document.body.innerHTML=document.getElementById('content').innerHTML; 
	       window.print();  */
	   }
	  /*  //复选框选择按钮框
	   function check(){
			var chk = document.getElementById('checkAll');
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
		  } */
	   
	  /*  //删除
	   function deleteCheck() {

		   
		   var leng=$('input[name=checkboxAsset]:checked').length; 
		   var id = $('input[name=checkboxAsset]:checked').val();
		   
		   if(leng == 1){
			   if(window.confirm("确认要删除吗?")){
				   
				   $.ajax({
					 type : "get",
					 contentType : "application/json",
					 url : "${dynamicURL}/check/api/deleteCheck",
					 data : {id:id},
					 success : function(data) {
						 if(data.success){
							 alert("删除成功!");
							 window.location.reload();
						 }else{
							 alert(data.errorMessages.join());
						 }
					 }
				   });
				   
			   }
			}else{
			   alert("选择一条数据进行删除");


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
       
    </script>   
</body>
</html>