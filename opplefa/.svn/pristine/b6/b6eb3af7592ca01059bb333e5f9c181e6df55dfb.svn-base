	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
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
</head>
	<body>
		<div>
    <input type="hidden" name="hfQueryStatus" id="hfQueryStatus" value="show">
    <table cellspacing="0" cellpadding="0"
           style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Query">
            <td>
                <form  id="tableHeadForm">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif" style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                       	</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="t_ShipInportInfo" class="adminlist" style="visibility: visible;">
                            <tbody>
                     <tr>
							<td class="td_text">单据号：</td>
							<td class="td_span"><input type="text" style="width: 194px;"
								value="${param.document}" name="document"/></td>
							<td class="td_text">申请日期：</td>
							<td class="td_span"><input type="text" style="width: 90px;" id="start"
								class="Wdate"  value="${param.start}"  name="start" onclick="WdatePicker({skin:'ext'});"
								id="" />~ <input type="text" style="width: 90px;" class="Wdate"
								value="${param.end}" name="end" onclick="WdatePicker({skin:'ext'});" id="end"/></td>
						</tr>
						<tr>
							<td class="td_text">申请人：</td>
							<td class="td_span"><input style="width: 194px;" type="text" class="my-text"
								value="${param.createBy}" name="createBy"/></td>
                            <td class="td_text">所属公司：</td>
							<td class="td_span"><input type="text" style="width: 194px;"
                                                       value="${param.adscriptionCompanyCode}"
                                                       name="adscriptionCompanyCode"/></td>
						</tr>
						<tr>
							<td class="td_text">归属部门：</td>
							<td class="td_span"><input type="text" style="width: 194px;"
								value="${param.adscriptionDepartment}" name="adscriptionDepartment"/></td>
							<td class="td_text">办公地点：</td>
							<td class="td_span">
							<input type="text" style="width: 194px;"
								value="${param.officeLocation}" name="officeLocation"/>
							</td>
								
						</tr>
						<tr >
							<td class="td_text">是否为草稿：</td>
								<td class="td_span">
									<select id="commitSelect" name="commitType" class="my-text" onchange="">
										<option value="">请选择</option>
										<option value="0" ${param.commitType=="0"?"selected='selected'" : ""}>是</option>
                                        <option value="1" ${param.commitType=="1"||param.commitType=='null'||param.commitType==null||param.commitType==''?"selected='selected'" : ""}>否</option>
									</select>
								</td>
							<td class="td_text">审批状态：</td>
							<td class="td_span">
								<select id="approvalState" name="approvalState" class="td_select select_color" onchange="">
									<option value="">请选择</option>
									<option value="等待审批">等待审批</option>
									<option value="正在审批">正在审批</option>				
									<option value="已完成">已完成</option>
									<option value="退回">退回</option>
								</select>
							</td>
						</tr>
                     <tr>
                         <td class="td_text">资产类型：</td>
                         <td class="td_span">
                             <select id="assetType" name="assetType" class="my-text">
                                 <option value="">请选择</option>
                                 <option value="0">IT资产</option>
                                 <option value="1">行政资产</option>
                                 <option value="2">计量器具</option>
                                 <option value="3">机器设备</option>
                             </select>
                         </td>
                         <td class="td_text">成本中心：</td>
                         <td class="td_span"><input style="width: 194px;" type="text" class="my-text"
                                                    value="${param.costCenterCode}" name="costCenterCode"/></td>
                     </tr>
                     <tr>
                         <td class="td_text">资产编码：</td>
                         <td class="td_span"><input style="width: 194px;" type="text" class="my-text"
                                                    value="${param.assetCode}" name="assetCode"/></td>
                         <td class="td_text">资产名称：</td>
                         <td class="td_span"><input type="text" style="width: 194px;"
                                                    value="${param.assetName}" name="assetName"/></td>
                     </tr>
                     <tr>
                         <td class="td_text">是否一次性费用化资产：</td>
                         <td class="td_span">
                             <select name="isExpenseAssets" id="isExpenseAssets">
                                 <option value="">请选择</option>
                                 <option value="0">否</option>
                                 <option value="1">是</option>
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
      		 <input class="btn" type="button" value="查询" id="opInquiry" style="cursor:pointer;"> 
   		   </auth:authTag>
   		   <auth:authTag code="Apply">
			<input class="btn" type="button" value="新增"  onclick="opApplication('1','数据新增');" style="cursor:pointer;" >
			</auth:authTag>
			<auth:authTag code="Check">
			<input class="btn" type="button" value="审批" id="approveBtn" onclick="discardSellApprove('数据审批');" style="cursor:pointer;">
			</auth:authTag> 
			<!-- <input class="btn" type="button" value="删除"  onclick="opDelete();" style="cursor:pointer;" >  -->
			<auth:authTag code="Export">
			<input class="btn" type="button" value="导出" id="btnExport"  style="cursor:pointer;"> 
			</auth:authTag> 
			<auth:authTag code="Delete">
			<input class="btn" type="button" value="删除" id="closeDiscardSell"  style="cursor:pointer;">
			</auth:authTag> 
            </td>
        </tr>
        <tr >
            <td valign="top">
                <div id="gridview" style="width: auto;height:auto;">
                    <div>
                        <table class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border-collapse: collapse;">
                            <tbody>
                     <tr  align="center">
										<th style="width:20px;"><input id="checkAll" type="checkbox"/></th>
										<th>操作</th>
										<th>操作</th>
										<th>打印</th>
										<th>单据号</th>
										<th>审批状态</th>
										<th>下一处理人</th>
										<th>申请人</th>
										<th>申请日期</th>
										<th>归属公司</th>
										<th>所属部门</th>
										<th>办公地点</th>
										
										<th>原值合计</th>
										<th>净值合计</th>

										<th>是否为草稿</th>
										<th>是否打印</th>
										
									</tr>
									
								<c:forEach items="${discardSellPager.records}" var="discardSell" varStatus="status">
                               			<tr >
                           					<td><input type="checkbox" class="input_checkbox" value="${discardSell.document},${discardSell.commitType}"  name="checkbox" id="checkbox" /></td>
											<td><a href="javascript:void(0)" onclick="discardSellUpdate('${discardSell.document}','数据修改');">修改</a></td>
											<td><a href="javascript:void(0)" onclick="discardSellDetail('${discardSell.document}','数据查看');" style="cursor:pointer;">查看</a></td>
											<td><a href="javascript:void(0)" onclick="discardSellStamp('${discardSell.id}')">打印</a></td>
                               				<td class="td_font">${discardSell.document}</td>
               								<td><a href="javascript:void(0)" onclick="showApproveHistory('${discardSell.document}', '77031')">${discardSell.approvalState }</a></td>
                               				<td>${discardSell.nextHandlerName }</td>
                               				<td>${discardSell.createBy }</td>
                               				<td class="td_date" style="text-align: center;"><fmt:formatDate value="${discardSell.createDate}"  pattern="yyyy-MM-dd"/></td>
                               				<td class="td_font">
                               				<c:if test="${!fn:contains(discardSell.adscriptionCompany, '(')}">(${discardSell.adscriptionCompanyCode})</c:if>${discardSell.adscriptionCompany}
                               				
                               				</td>
                               				<td class="td_font">${discardSell.subordinateDepartment }</td>
                               				<td class="td_font">${discardSell.officeLocation }</td>
                               				<td class="td_macth" style="text-align: right;" isMoney='Y'><fmt:formatNumber value="${discardSell.assetOriginalValueSum }" pattern="0.00"/></td>
                               				<td class="td_macth" style="text-align: right;" isMoney='Y'><fmt:formatNumber value="${discardSell.assetNetValueSum }" pattern="0.00"/> </td>
                               			       
                               				<td class="td_font">${discardSell.commitType=='0'?"是":"否" }</td>
                               				<td class="td_font">
                               				<c:choose>
									 		  <c:when test="${discardSell.isStamp=='Y'}"> 
								    			是
								  			 </c:when>
							  			 	  <c:when test="${discardSell.isStamp=='N'}"> 
								    			否
								  			 </c:when>
								  			 <c:otherwise>
								  			 	否
								  			 </c:otherwise>
								  			 </c:choose>
                               				
                               				</td>
                               			</tr>
                           		 </c:forEach>									
		
                            </tbody>
                        </table>
                        <tag:paginate offset="${discardSellPager.offset}" total="${discardSellPager.total}" steps="${discardSellPager.pageSize}" uri="${discardSellPager.uri}" formId="tableHeadForm" /> 
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>

	
		<script src="${staticURL}/js/jquery-1.11.3.min.js"></script>
		<%-- <script src="${staticURL}/Scripts/JQuery/jquery-1.4.4.min.js" type="text/javascript"></script> --%>
		<script src="${staticURL}/js/jquery.tableCheckbox.js"></script>
		<script src="${staticURL}/js/browser.js"></script>
		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
		<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	
		<script type="text/javascript">
		 var dg = frameElement.lhgDG;
         function autoSize() {
             $("#gridview").width($(window).width() - 4);
		        $("#gridview").height(
		                $(window).height() - $("#Query").height()
		                - $("#Button").height()
                        - $("#AspNetPager").height() - 5);
		    }
         autoSize();
         window.onresize = autoSize;
	 	//列表动态yanse
		 GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
		 $(function(){
			 /* $("#commitSelect").val("${param.commitType}"); */
			 $("#approvalState").val("${param.approvalState}");
             $("#assetType").val("${param.assetType}");
             $("#isExpenseAssets").val("${param.isExpenseAssets}");
		 });
	 	//切换查询条件
	 	function qiehuan() {
	 		
			if($(".trDiv").css("display")=="none"){
		
					$(".trDiv").show();
		
				}else{
		
					$(".trDiv").hide();
		
				}
		}
		//全选
	$("#checkAll").click(function(){
		
		$("input[name='checkbox']").each(function(){
		
			if($(this).attr("checked")) 
			{
				$(this).removeAttr("checked"); 
			} else {
				$(this).attr("checked","true"); 
			} 
			}); 
		});
	
		 $("#opInquiry").click(function() {
			//alert($('#tableHeadForm').serialize());
				var txtBeginDate = $("#start").val();
		    	var txtEndDate = $("#end").val();
		    	if(txtBeginDate>txtEndDate && txtEndDate!=""){
		    		alert("开始时间不能大于结束时间");
		    		return false;
		    	}
			window.location.href = '${dynamicURL}/discardSell/searchDiscardSell?'+$('#tableHeadForm').serialize();
		});
		$("#closeDiscardSell").click(function(){
			var boxLength=$("input:checkbox[name='checkbox']:checked").length;
			if(boxLength=='0'){
				alert("请选择一条数据");			
			}
			var documents="";
			if(boxLength>1||boxLength==1){
				$("input:checkbox[name='checkbox']:checked").each(function(){
					var ids=$(this).val();
					var id=ids.split(",")[0];
					if(documents==""){
						documents=id;
					}else{
						documents=documents+","+id;
					}
				});
			}
			if(boxLength>1||boxLength==1){
				var id=$("input:checkbox[name='checkbox']:checked").val();
				if (window.confirm("确认要删除吗?")) {
				$.ajax({
						type:"post",
						url:"${dynamicURL}/discardSell/api/deleteDiscardSell",
						data:{documents:documents},
						success:function (data){
							if (data.success) {
								alert("删除成功！");
								window.location.reload();
							}else{
								alert(data.errorMessages.join());
								}
							}
				});
				}
			}
		});
	
		 //申请
		function opApplication(id,title) {
			
			var lurl = "${dynamicURL}/discardSell/discardSellApply?id="+id; 
            var dlg = new $.dialog({ id: 'discardSellApply', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
        }
		 
		
		//查看
		function discardSellDetail(id,title) {
			
		 	var lurl = "${dynamicURL}/discardSell/discardSellDetail?id="+id; 
            var dlg = new $.dialog({ id: 'detail', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
        }
		//修改
		function discardSellUpdate(id, title) {
		    $.ajax({
				type: "POST",
				url: "${dynamicURL}/discardSell/ifUpdatePermitted?id=" + id,
				
				success: function (data) {
					
					if (data.success) {
						showDiscardSellDialog(id);
					} else {
						alert(data.errorMessages.join());
					}
				}
			});
			
		} 
		function discardSellStampApp(id){
			 $.ajax({
					type: "POST",
				 url: "${dynamicURL}/discardSell/api/discardSellStampStatus?id=" + id + "&typeId=77031",
					
					success: function (data) {
						if (data.success) {
							discardSellStamp(id);
						} else {
							alert(data.errorMessages.join());
						}
					}
				});
			
		}
		function discardSellStamp(id){
			var lurl = "${dynamicURL}/discardSell/discardSellStamp?id=" + id + "&typeId=77031";
			window.open(lurl);
//			var dlg = new $.dialog({ id: 'stamp', title: "打印", cover: true, page: lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
		}
		function showDiscardSellDialog(id){
			var lurl = "${dynamicURL}/discardSell/updateDiscardSell?id=" + id;
			var dlg = new $.dialog({ id: 'update', title: "数据修改", cover: true, page: lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
		}
		//历史审批记录
		function showApproveHistory(document, typeId) {
		    var url = '${dynamicURL}/workflowDiscardSell/showApproveHistory?document='+document+"&typeId="+typeId;
		    var dlg = new $.dialog({ id: 'approveHistory', title: "审批", cover: true, page: url, rang: true, width: 700, height: 450, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
           // showDialog('approveHistory', '申请流程', url + document +'&typeId=' + typeId, 800, 500);
		}
		$("#btnExport").click(function(){
				if (window.confirm("确认要导出吗?")) {
					window.location.href = "${dynamicURL}/discardSell/Export?"+$('#tableHeadForm').serialize();
					}
				
		});
/* 		$(document).ready(function(){
		    $('#gvDataList').find('input[type=checkbox]').bind('click', function(){
		        $('#gvDataList').find('input[type=checkbox]').not(this).attr("checked", false);
		    });
		}); */
		function discardSellApprove(title){
			
			var length=$("input:checkbox[name='checkbox']:checked").length;
			
			if(length=='1'){
				var ids=$("input:checkbox[name='checkbox']:checked").val();
				var id=ids.split(",")[0];
				if(ids.split(",")[1]=='1'){
					var lurl = "${dynamicURL}/workflowDiscardSell/discardSellApprove?id=" + id;
					var dlg = new $.dialog({ id: 'approve', title: title, cover: true, page: lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
				}else{
					alert("草稿不可审批");
				}
				
			}
			if(length=='0'){
				alert("请选择一条要审批的数据");
			}
			if(length>1){
				alert("只能选择一条数据");
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

		</script>
	</body>

</html>