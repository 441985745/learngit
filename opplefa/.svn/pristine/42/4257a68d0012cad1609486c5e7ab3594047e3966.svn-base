<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Stylesheet" type="text/css" href="${staticURL}/assetcard/css/assetcard.css">
<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
 <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">

<title>Insert title here</title>
</head>
	<body>
		<fieldset id="fset_ShipInportInfo">
   			<legend>
   				<img alt="展开/收缩" src="${staticURL}/assetcard/img/constringency.gif " onclick="qiehuan()">
   				查询条件
   			</legend>
	<div>
	<form id="formId">
	<div class="trDiv">
		<table class="table_head"  >

			<tr >
					<td class="td_text">申请人：</td>
					<td class="td_span"><input type="text" class="my-text" value="${assetAllocation.applyPeople}"  name="applyPeople"  id="applyPeople" /></td>
					<td class="td_text">申请日期：</td>
					<td class="td_span"><input type="text"  class="Wdate" value="${assetAllocation.applicationDate}" onclick="WdatePicker({skin:'ext'});"  id="applicationDate" /></td>
			</tr>
			<tr >
					<td class="td_text">原归属公司：</td>
					<td class="td_span"><input type="text" class="my-text"  value="${assetAllocation.originalCompany}" name="originalCompany" id="originalCompany" /></td>
					<td class="td_text">调拨后负责人：</td>
					<td class="td_span">
						<div class="my-text">
						<input type="text" style="width: 145px;" value="${assetAllocation.postAllocationPeople}" id="postAllocationPeople" name="postAllocationPeople" />
						<input type="hidden" id="peopleValue" name="postAllocationPeople">
						<input id="gvdMeeting_btnClearsk_0"  class="btnRefView" style="margin-left: 0px;width: 20px;height: 25px;" onclick="ClearObj();" title="点击清空参照文本框内容" value="X" type="button">
						<input id="showUserListBtn" class="btnRefView" style="margin-left: 0px;width: 20px;height: 25px;" title="点击打开参照文本框内容" value="…" type="button">
						</div>
					</td>
			</tr>
			<tr >
					<td class="td_text">是否为费用化资产：</td>
					<td class="td_span">
						<select class="my-text"  id="isExpenseAssets" name="isExpenseAssets">
							<c:choose>
								<c:when test="${assetAllocation.isExpenseAssets=='0'}">   
									<option selected="selected" value="0">否</option>
									<option value="1">是</option>
								</c:when>
								<c:when test="${assetAllocation.isExpenseAssets=='1'}">   
									<option selected="selected" value="1">是</option>
									<option value="0">否</option>
								</c:when>
	
								<c:otherwise> 
									<option value="" >请选择</option>  
									<option value="1">是</option>
									<option value="0">否</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
					<td class="td_text">调拨后负责人部门：</td>
					<td class="td_span"><input type="text" class="my-text" value="${assetAllocation.postAllocationDepartment}" id="postAllocationDepartment"  name="postAllocationDepartment" /></td>
			</tr>
			<tr >
								<td class="td_text">资产类型：</td>
								<td class="td_span">
											<input  name="assetType" type="hidden" id="assetHiddenType" value="${assetAllocation.assetType}">
											<select id="assetType" onchange="changeType()">
												<option value="">请选择</option>
												<option value="IT">IT资产</option>
												<option value="2">行政资产</option>
												<option value="3">计量器具</option>
												<option value="4">机器设备</option>
											</select>
								</td>
								<td class="td_text">调拨后成本中心：</td>
								<td class="td_span"><input type="text" class="my-text" name="postAllocationCostCenter" id="postAllocationCostCenter" /></td>
						</tr>
						<tr >
								<td class="td_text">车间类型：</td>
								<td class="td_span"><input type="text" class="my-text" id="workshopType" name="workshopType" /></td>
								<td class="td_text">调拨后归属公司：</td>
								<td class="td_span"><input type="text" class="my-text" id="postAllocationCompany" name="postAllocationCompany"  /></td>
						</tr>
						<tr >
								<td class="td_text">办公地点：</td>
								<td class="td_span">
									<select class="my-text" id="officeLocation" name="officeLocation">
										<c:choose>
											<c:when test="${assetAllocation.officeLocation=='中山'}">
												<option selected="selected" value="中山">中山</option>
												<option value="上海">上海</option>
												<option value="吴江">吴江</option>
												<option value="办事处大区">办事处大区</option>
												<option value="驻国外（迪拜）">驻国外（迪拜）</option>
											</c:when>
											<c:when test="${assetAllocation.officeLocation=='上海'}">
												<option value="中山">中山</option>
												<option selected="selected" value="上海">上海</option>
												<option value="吴江">吴江</option>
												<option value="办事处大区">办事处大区</option>
												<option value="驻国外（迪拜）">驻国外（迪拜）</option>
											</c:when>
											<c:when test="${assetAllocation.officeLocation=='吴江'}">
												<option value="中山">中山</option>
												<option value="上海">上海</option>
												<option selected="selected" value="吴江">吴江</option>
												<option value="办事处大区">办事处大区</option>
												<option value="驻国外（迪拜）">驻国外（迪拜）</option>
											</c:when>
											<c:when test="${assetAllocation.officeLocation=='办事处大区'}">
												<option value="中山">中山</option>
												<option value="上海">上海</option>
												<option value="吴江">吴江</option>
												<option selected="selected" value="办事处大区">办事处大区</option>
												<option value="驻国外（迪拜）">驻国外（迪拜）</option>
											</c:when>
											<c:when test="${assetAllocation.officeLocation=='驻国外（迪拜）'}">
												<option value="中山">中山</option>
												<option value="上海">上海</option>
												<option value="吴江">吴江</option>
												<option value="办事处大区">办事处大区</option>
												<option selected="selected" value="驻国外（迪拜）">驻国外（迪拜）</option>
											</c:when>
											
											<c:otherwise>
												<option value="" >请选择</option>
												<option value="中山">中山</option>
												<option value="上海">上海</option>
												<option value="吴江">吴江</option>
												<option value="办事处大区">办事处大区</option>
												<option value="驻国外（迪拜）">驻国外（迪拜）</option>
											</c:otherwise>
										</c:choose>
									</select>
								</td>
								<td class="td_text">调拨后存放位置：</td>																	 
								<td class="td_span"><select class="my-text" id="postAllocationStoragePlace" name="postAllocationStoragePlace"><option value="" >请选择</option> <option value="仓库">仓库</option> <option value="楼道">楼道</option> </select></td>
						</tr>
						
						<tr>
								<td class="td_text">平台：</td>
								<td class="td_span">
									<select class="my-text" id="platform" name="platform"> 
										<c:choose>
											<c:when test="${assetAllocation.platform=='制造供应链'}">
												<option selected="selected" value="制造供应链">制造供应链</option>
												<option value="职能">职能</option>
												<option value="研发">研发</option>
												<option value="内销">内销</option>
												<option value="5">外销</option> 
											</c:when>
											<c:when test="${assetAllocation.platform=='职能'}">
												<option value="制造供应链">制造供应链</option>
												<option selected="selected" value="职能">职能</option>
												<option value="研发">研发</option>
												<option value="内销">内销</option>
												<option value="5">外销</option> 
											</c:when>
											<c:when test="${assetAllocation.platform=='研发'}">
												<option value="制造供应链">制造供应链</option>
												<option value="职能">职能</option>
												<option selected="selected" value="研发">研发</option>
												<option value="内销">内销</option>
												<option value="5">外销</option> 
											</c:when>
											<c:when test="${assetAllocation.platform=='内销'}">
												<option value="制造供应链">制造供应链</option>
												<option value="职能">职能</option>
												<option value="研发">研发</option>
												<option selected="selected" value="内销">内销</option>
												<option value="5">外销</option> 
											</c:when>
											<c:when test="${assetAllocation.platform=='外销'}">
												<option value="制造供应链">制造供应链</option>
												<option value="职能">职能</option>
												<option value="研发">研发</option>
												<option value="内销">内销</option>
												<option selected="selected" value="5">外销</option> 
											</c:when>
											
											<c:otherwise>
												<option value="" >请选择</option>
												<option value="制造供应链">制造供应链</option>
												<option value="职能">职能</option>
												<option value="研发">研发</option>
												<option value="内销">内销</option>
												<option value="5">外销</option> 
											</c:otherwise>
										</c:choose>
									</select>
								</td>
								
								<td class="td_text"><div id="it1" >IT资产类：</div></td>																	 
								<td class="td_span">
								<div id="it2" >
									<select class="my-text" id="assetITType" onchange="changeITType()">
										<option value="" >请选择</option> 
										<option value="0">电脑类</option> 
										<option value="1">非电脑类</option> 
									</select>
									</div>
								</td>
						</tr>
			</table>
			</div>
			<table>
			<tr  >
			<td colspan="4" class="buttonbar">
			<input class="btn" type="button" value="查询" id="opInquiry" style="cursor:pointer;" > 
			<input class="btn" type="button" value="申请"  onclick="opApplication('1','资产调拨');" style="cursor:pointer;" > 
			<input class="btn" type="button" value="审批" onclick="" style="cursor:pointer;"> 
			<input class="btn" type="button" value="删除"  onclick="opDelete();" style="cursor:pointer;" > 
			<input class="btn" type="button" value="导出" id="btnExport"> 
			<a href="javascript:printSeal()" rel="external nofollow" target="_self"><button class="btn"  type="button"  >打印</button></a>
			<input class="btn" type="button" value="关闭" onclick="opClose()">
			</td></tr>
			</table>

		</form>
		</div>
		</fieldset>
				<div style="overflow:scroll">
							<table  class="adminlist" style="width: 1900px; " id=gvDataList > 
								<thead>
									<tr  align="center">
										<th style="width:20px;"><input type="checkbox" class="input_checkbox" id="checkAll"/></th>
										<th>操作</th>
										<th>操作</th>
										<th>打印</th>
										<th>单据号</th>
										<th>申请人</th>
										<th>申请日期</th>
										<th>原归属公司</th>
										<th>调拨后存放位置</th>
										<th>调配方式</th>
										<th>资产类型</th>
										<th>维护人</th>
										<th>维护时间</th>
										<th>审批状态</th>
										<th>是否为草稿</th>
										<th>是否打印</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${assetAllocationPager.records}" var="assetAllocation" varStatus="status">
                                <tr class="zt_center" id="tr${assetAllocation.id}">
                               		<td><input type="checkbox" class="input_checkbox" value="${assetAllocation.id}"  name="radio" id="radio" /></td>
									<td><a href="javascript:void(0)" onclick="opUpdate('${assetAllocation.id}','数据修改');">修改</a></td>
									<td><a href="javascript:void(0)" onclick="opView('${assetAllocation.id}','数据查看');" style="cursor:pointer;">查看</a></td>
									<td><a href="javascript:void(0)">打印</a></td>
                                    <td id="number${assetAllocation.id}">${assetAllocation.documentNumber}</td>
                                    <td>${assetAllocation.applyPeople}</td>
                                    <td><fmt:formatDate value="${assetAllocation.applicationDate}"  pattern="yyyy-MM-dd"/></td>
                                    <td>${assetAllocation.originalCompany}</td>
                                    <td>${assetAllocation.postAllocationStoragePlace}</td>
                                    <td>${assetAllocation.allocationMethod}</td>
                                    <td>${assetAllocation.assetType}</td>
                                    <td>${assetAllocation.postAllocationPeople}</td>
                                    <td><fmt:formatDate value="${assetAllocation.updateDate}"  pattern="yyyy-MM-dd"/></td>
                                    <td id="ratify${assetAllocation.id}" style="display:block">
	                                    <c:if test="${assetAllocation.approveState=='0'}">
	                                    	未审批
	                                    </c:if>
	                                    <c:if test="${assetAllocation.approveState=='1'}">
	                                    	审批中
	                                    </c:if>
	                                    <c:if test="${assetAllocation.approveState=='2'}">
	                                    	审批完成
	                                    </c:if>
                                    </td>
                                    
                                    <td id="draft${assetAllocation.id}" >
                                    	<c:if test="${assetAllocation.isDraft=='0'}">是</c:if>
	                                    <c:if test="${assetAllocation.isDraft=='1'}">否</c:if>
                                    </td>
                                    <td>否</td>
                                </tr>
                            </c:forEach>																	
								</tbody>
							</table>
						<tag:paginate offset="${assetAllocationPager.offset}" total="${assetAllocationPager.total}" steps="${assetAllocationPager.pageSize}" uri="${assetAllocationPager.uri}" formId="formId" />
				</div>

		<!-- 点击増行弹出form -->
		<div id="ysssbmWindow" name="" class="easyui-window" title="所有人员" data-options="iconCls:'icon-save'" style="width:600px;height:330px;padding:5px;" closed="true">

			<form id="searchUserForm">
            <table cellpadding="1" bgcolor="#F9F9F9" class="adminlist">
                <tbody>
                <tr>
                    <td height="26" width="20%" style="white-space:nowrap;" align="right">编码：</td>
                    <td>
                        <input name="userCode" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                               onblur="this.className='inputbtnRefView';" style="width:120px;" value="">
                    </td>

                    <td height="26" width="20%" style="white-space:nowrap;" align="right">名称：</td>

                    <td>
                        <input name="userName" type="text" class="inputbtnRefView" onfocus="this.className='colorfocusRefView';"
                               onblur="this.className='inputbtnRefView';" style="width:120px;" value="">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <div id="buttons">
            <input type="button" value="查询" id="queryBtn" class="btn">&nbsp;&nbsp;&nbsp;
            <input type="button" value="取消" id="cancelBtn" onclick="closeYHGXControlDialog('txtCUserName_ReferenceDialog','txtCUserName_HidShowFlag');" class="btn">
        </div>
        <div>
            <table class="adminlist" cellspacing="0" rules="all" border="1" id="userTable" style="border-collapse:collapse;width:100%;">
                <thead>
                    <th align="center" scope="col" style="width:30%;">编码</th>
                    <th align="center" scope="col" style="width:70%;">名称</th>
                </thead>
                <tbody id="supplierBody"></tbody>
            </table>
        </div>
        
		</div>
		
		<%-- <script src="${staticURL}/js/allocationIndex.js"></script> --%>
		<script src="${staticURL}/js/jquery-1.11.3.min.js"></script>
		<%-- <script src="${staticURL}/Scripts/JQuery/jquery-1.4.4.min.js" type="text/javascript"></script> --%>
		<script src="${staticURL}/js/jquery.tableCheckbox.js"></script>
		<script src="${staticURL}/js/browser.js"></script>
		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
		<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
		<script src="${staticURL}/Scripts/Purchase/DemandOrder/DemandOrder.js" type="text/javascript"></script>
		<script type="text/javascript">
		var dg = frameElement.lhgDG;
	 	var jslength=0;
	 	//页面自动加载
	 	$(function(){  
	 		$("td[id^='draft']").each(function(){
				var id = $(this).attr("id").substr(5);
				var tdobj = document.getElementById('draft'+id);
				if($.trim(tdobj.innerText)=='是'){
					 document.getElementById('ratify'+id).innerHTML="";
				}
			});
	 		
	 		$("#isExpenseAssets").val("${assetAllocation.isExpenseAssets}");
	 		$("#officeLocation").val("${assetAllocation.officeLocation}");
	 		$("#platform").val("${assetAllocation.platform}");
	 		$("#assetType").val("${assetAllocation.assetType}");
	 		if ('${param.assetType}' == "IT" || '${param.assetType}' == '0'||'${param.assetType}' == '1') {
				$("#assetType").attr("value","IT");
				$("#assetITType option[value='${param.assetType}']").attr("selected",true);
				$("#it2").show();
				$("#it1").show();
			} else {
				$("#assetType option[value='${param.assetType}']").attr("selected",true);
				$("#it1").hide();
				$("#it2").hide();
			}
	 	});

	 	//列表动态yanse
		 GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
	 	
	 	//切换查询条件
	 	function qiehuan() {
	 		
			if($(".trDiv").css("display")=="none"){
		
					$(".trDiv").show();
		
				}else{
		
					$(".trDiv").hide();
		
				}
		}
	
		//选择调拨后负责人
		$('#showUserListBtn').click(function(){
			showUserDialog();
	    });
	 	function showUserDialog() {
	        var url = "${dynamicURL}/user/getUserList";
	        new $.dialog({ id: 'listUserDialog', title: "所有人员", cover: true, page: url, width: 700, height: 400, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
	    }
	 	
	 	//清空调拨后负责人栏
	 	function ClearObj(){
	 		$("#postAllocationPeople").val("");
	 		$("#peopleValue").val("");
	 	}
		//打印
		function printSeal(){ 
	        document.body.innerHTML=document.getElementById('content').innerHTML; 
	        window.print(); 
	    }
		
		//全选
		$("#checkAll").click(function(){
			$("input[name='radio']").prop("checked", $(this).prop("checked"));
		});
		
		//查询
		$("#opInquiry").click(function() {
			var date = $("#applicationDate").val();//申请日期
			window.location.href = '${dynamicURL}/assetAllocation/searchAssetAllocations?'+ $('#formId').serialize()+'&date='+date;
		});
		
		//关闭
		function opClose(){
			var ids ="";
			var vl = "";
			$("input[name='radio']:checked").each(function(){
				var v = $(this).val();
				var tdRatify = document.getElementById('ratify'+v);
				if($.trim(tdRatify.innerText)=='审批完成'){
					vl += v+",";
				}
				else{
					ids += v+",";
				}
			});

			if(vl!=null&&vl!=''){
				var array = vl.split(",");
				
				for (var i=0 ; i< array.length-1 ; i++)
				{
					var tdNumberInfo = document.getElementById('number'+parseInt(array[i]));
					var asd = tdNumberInfo.innerText;
					alert("单据号"+asd+"审批完成,不可删除");
				}
			}else if(ids!=null&&ids!=''){
				$.ajax({
					type:"post",
					url:"${dynamicURL}/assetAllocation/deleteAssetAllocationList",
					data: {"ids":ids},
				 	success: function(data) {
			 			var array = ids.split(",");
			 			for (var i=0 ; i< array.length ; i++)
			 			{
			 			    $("#tr"+parseInt(array[i])).remove();
			 			} 
			 			alert("关闭成功");
				 	}
				});
			}else{
				alert("至少选择一条数据");
			}
		}
		//删除
		function opDelete(){
			var ids ="";
			var vl = "";
			$("input[name='radio']:checked").each(function(){
				var v = $(this).val();
				var tdRatify = document.getElementById('ratify'+v);
				if($.trim(tdRatify.innerText)=='未审批'||tdRatify.innerText==''){
					ids += v+",";
				}	
				else{
					vl += v+",";
				}
			});
			
			if(vl!=null&&vl!=''){
				var array = vl.split(",");
				
				for (var i=0 ; i< array.length-1 ; i++)
				{
					var tdNumberInfo = document.getElementById('number'+parseInt(array[i]));
					var asd = tdNumberInfo.innerText;
					alert("单据号"+asd+"已审批,不可删除");
				}
			}else if(ids!=null&&ids!=''){
				$.ajax({
					type:"post",
					url:"${dynamicURL}/assetAllocation/deleteAssetAllocationList",
					data: {"ids":ids},
				 	success: function(data) {
			 			var array = ids.split(",");
			 			for (var i=0 ; i< array.length ; i++)
			 			{
			 			    $("#tr"+parseInt(array[i])).remove();
			 			} 
			 			alert("删除成功");
				 	}
				});
			}else{
				alert("至少选择一条数据");
			}
			
		}
		//申请
		function opApplication(id,title) {
			
			var lurl = "${dynamicURL}/assetAllocation/assetAllocationApplication?id="+id;
            var dlg = new $.dialog({ id: 'application', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
        }
		
		//查看
		function opView(id,title) {
			var lurl = "${dynamicURL}/assetAllocation/assetAllocationView?id="+id;
            var dlg = new $.dialog({ id: 'view', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
        }
		//修改
		function opUpdate(id, title) {
			var lurl = "${dynamicURL}/assetAllocation/assetAllocationUpdate?id=" + id;
			var dlg = new $.dialog({ id: 'update', title: title, cover: true, page: lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
		}
		
		function changeType() {
			var s = $("#assetType").find("option:selected").val();
			if (s == "") {
				$("#assetHiddenType").val("");
				$("#it1").hide();
				$("#it2").hide();
			}
			if (s == "2") {
				$("#assetHiddenType").val("2");
				$("#it1").hide();
				$("#it2").hide();
			}
			if (s == "3") {
				$("#assetHiddenType").val("3");
				$("#it1").hide();
				$("#it2").hide();
			}
			if (s == "4") {
				$("#assetHiddenType").val("4");
				$("#it1").hide();
				$("#it2").hide();
			}
			if (s == "IT") {
				$("#it1").show();
				$("#it2").show();
				$("#assetHiddenType").val("IT");
				$("#assetITType option[value='']").attr("selected",true);
			}
		}
		function changeITType() {
			var s = $("#assetITType").find("option:selected").val();
			if(s==""){
				$("#assetHiddenType").val("IT");
			}
			if (s == "0") {
				$("#assetHiddenType").val("0");
			}
			if (s == "1") {
				$("#assetHiddenType").val("1");
			}
		}
		
		function openYHGXControlDialog(dialogId, showId, coverFlag) {
		    //弹出框定位
		    //document宽度的 1/4 x
		    //document高度的 1/5 y
		    var x = $(document).width() * 0.1;
		    var y = $(document).height() * 0.05;

//		    x = $('#' + dialogId).position().left;
//		    y = $('#' + dialogId).position().top;

		    $('#' + dialogId).css('left', x);
		    $('#' + dialogId).css('top', y);

		    $('#' + dialogId).show();
		    $('#' + showId).val('true');

		    if (coverFlag == 'true') {
		        if ($('#' + coverName).length == 0) {
		            createCoverYHGXControlDialog();
		        }
		        $('#' + coverName).show();
		    }
		    else {
		        $('#' + coverName).hide();
		    }
		}
		</script>
	</body>

</html>