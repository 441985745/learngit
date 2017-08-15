<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>盘点申请单</title>
<style type="text/css">
  
span{
   color: red;
   font-size: 12px;
}
.td_color td{
	width:100px;
	text-align: center;
	font-size: 12px;
}
</style>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<link href="${staticURL}/assetcheck/assetcheck.css" rel="stylesheet" type="text/css"/>
<link rel="Stylesheet" type="text/css" href="${staticURL}/assetcard/css/assetcardadd.css">
<link rel="Stylesheet" type="text/css" href="${staticURL}/Scripts/ExtJs/resources/css/ext-all.css"> 
<link href="${staticURL}/Scripts/DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
</head>
<body>
       <table style="width: 100%;">
          <tr ><td style="text-align: center;">盘点申请单</td></tr>
          <tr>
            <td style="border: 1px solid  #B5B5B5;" class=" buttonbar">
              <input class="btn" type="button" value="提交" id="submit"/>
              <input class="btn" style="text-align: center;" type="button" value="保存草稿" id="saveDraft"/>
              <input class="btn" type="button" value="返回" onclick="dg.cancel();"/>
           </td>
          </tr>
          <tr>
            <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">
                               基本信息
            </td>
          </tr>
       </table>
       <form id="basicInfo">
          <table style="width:100%;" class="ll">
            <input name='isDraft' id="isDraft" type="hidden">
            <input name='isDraft' id="isDraft" type="hidden">
            <input name='isDraft' id="isDraft" type="hidden">
            <tr rowspan="2">
              <td class="td_right">申请人:</td>
              <td class="td_left" id="applyPeople" name="applyPeople">${assetCheckBusiness.applyPeople}</td>
              <td class="td_right"><span>*</span>盘点层级:</td>
              <td class="td_left" id="inventoryLevels" name="inventoryLevels">
                <select class="td_select select_color"  onchange="changeLevels()">
                   <option class="option_color" selected="selected" value="">--请选择--</option>
                   <option class="option_color" value="部门" >部门</option>
                   <option class="option_color" value="平台" >平台</option>
                   <option class="option_color" value="成本中心" >成本中心</option>
                </select>
               </td>
                 <td class="td_right">盘点负责人:</td>
                <td class="td_left" id="inventoryPeople" name="inventoryPeople">
                ${assetCheckBusiness.inventoryPeople}
                </td>
               
            </tr>
            <tr>
              <td class="td_right">申请日期:</td>
              <td class="td_left" id="inventoryApplyDate" name="inventoryApplyDate">
                 <fmt:formatDate value="${assetCheckBusiness.inventoryApplyDate}"  pattern="yyyy-MM-dd"/>
              </td>
              <td class="td_right">盘点时间:</td>
              <td class="td_left" id="inventoryDate" name="inventoryDate">
                 <fmt:formatDate value="${assetCheckBusiness.inventoryDate}"  pattern="yyyy-MM-dd"/>
              </td>
              
              <td class="td_right"><div id="changeDiv" ></div></td>
	          <td class="td_left"><div id="changeDivInfo" ></div></td>  
              
	          <%-- <td class="td_right"><div id = "departmentDiv" >盘点部门:</div></td>
	          <td class="td_left"><div id = "departmentDivInfo" ><input class="td_input" type="text" id="inventoryDepartment" name="inventoryDepartment" value="${param.inventoryDepartment}"/></div></td>
              
	          <td style="text-align:right;"><span>*</span><div id = "platformDiv">平台:</div></td>
	             <td class="td_left">
	             <div id = "platformDivInfo">
	               <span>*</span>
	                 <select class="td_select select_color" id="platform" name="platform">
	                    <option class="option_color" selected="selected" value="">--请选择--</option>
	                    <option class="option_color" value="制造供应链" ${param.platform=="制造供应链"?"selected='selected'" : ""}>制造供应链</option>
	                    <option class="option_color" value="职能" ${param.platform=="职能"?"selected='selected'" : ""}>职能</option>
	                    <option class="option_color" value="研发" ${param.platform=="研发"?"selected='selected'" : ""}>研发</option>
	                    <option class="option_color" value="内销" ${param.platform=="内销"?"selected='selected'" : ""}>内销</option>
	                    <option class="option_color" value="列表项1" ${param.platform=="列表项1"?"selected='selected'" : ""}>列表项1</option>
	                    <option class="option_color" value="外销" ${param.platform=="外销"?"selected='selected'" : ""}>外销</option>
	                 </select>
	              </div>
	            </td>     --%>
           </tr>
<%--            <tr>
              <td style="text-align:right;"><span>*</span>平台:</td>
               <td>
                 <select id="platform" name="platform">
                    <option selected="selected" value="">--请选择--</option>
                    <option value="制造供应链" ${param.platform=="制造供应链"?"selected='selected'" : ""}>制造供应链</option>
                    <option value="职能" ${param.platform=="职能"?"selected='selected'" : ""}>职能</option>
                    <option value="研发" ${param.platform=="研发"?"selected='selected'" : ""}>研发</option>
                    <option value="内销" ${param.platform=="内销"?"selected='selected'" : ""}>内销</option>
                    <option value="列表项1" ${param.platform=="列表项1"?"selected='selected'" : ""}>列表项1</option>
                    <option value="外销" ${param.platform=="外销"?"selected='selected'" : ""}>外销</option>
                 </select>
               </td>
               
              <td style="text-align:right;">盘点部门:</td>
              <td><input type="text" id="inventoryDepartment" name="inventoryDepartment" value="${param.inventoryDepartment}"/></td>
              <td></td>
              <td></td>
           </tr> --%>
          </table>
        </form>
        
        
           <table style="background-color:#EDEDED; width: 100%;">
             <tr>
               <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">
                                    盘点详情信息
               </td>
             </tr>
<!--              <tr>
               <td colspan="6">
                <input type="button" name="btnQuery" value="查询" id="btnQuery" class="btn">
                <input value="删行" type="button" class="btn" onclick="deleteAsset()"/>
                <input value="导入" type="button" class="btn" onclick=""/>
                <input value="导出" type="button" class="btn" onclick=""/>
               </td>
            </tr> -->
          </table>
          
         <form id="bd">
           <div style="overflow:scroll">  
             <table class="td_color" style="width:1700px;">
               <thead>
                 <tr align="center">
                   <td>
                     <input type="checkbox"/>
                   </td>
                   <td>序号</td>
                   <td>资产编号</td>
                   <td>资产名称</td>
                   <td>资产类型</td>
                   <td>成本中心</td>
                   <td>使用部门</td>
                   <td>责任人</td>
                   <td>实际数量</td>
                   <td>盘点数量</td>
                   <td>盘点差异类型</td>
                   <td>资产原值</td>
                   <td>资产净值</td>
                   <td>购置日期</td>
                   <td>状态</td>
                   <td>公司</td>
                </tr>
              </thead>
              <tbody>
                  <%-- <c:forEach items="${generalAssetPager.records}" var="assetCard" varStatus="status">
									<tr style="text-align: center;">
										<td><input class="input_checkbox"  type="checkbox" value="${assetCard.id}" name="checkboxAsset" id="checkboxAsset" onclick="isChecked(this)" ></td>
										<td>${assetCard.serialNumber}</td>
										<td>${assetCard.assetCode}</td>
										<td>${assetCard.assetName}</td>
										<td>${assetCard.assetType}</td>
										<td>${assetCard.persentDepartment}</td>
										<td>${assetCard.useDepartment}</td>
										<td>${assetCard.assetPerson}</td>
										<td>${assetCard.realQuantity}</td>
										<td>${assetCard.countedQuantity}</td>
										<td>${assetCard.assetOriginalValue}</td>
										<td>${assetCard.assetNetValue}</td>
										<td>${assetCard.purchaseDate}</td>
										<td>${assetCard.assetState}</td>
										<td>${assetCard.persentCompany}</td>
									</tr>
									
				  </c:forEach>	 --%>
				  <tr style="text-align: center;">
										<td><input class="input_checkbox"  type="checkbox" value="${assetCard.id}" name="checkboxAsset" id="checkboxAsset" onclick="isChecked(this)" ></td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td><input class="td_input" type="text" name="description"/></td>
										<td>
											<select class="td_select select_color" name="useState">
											    <option value="">--请选择--</option>
							                     <option>使用部门不一致</option>
							                     <option>挂账人不一致</option>
							                     <option>盘亏</option>
							                     <option>盘盈</option>
							                     <option>无资产标签</option>
							                     <option>资产标签财务编码不一致</option>
								 		    </select>
								 		</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
										<td>1</td>
									</tr>
              </tbody>
            </table> 
          </div>
             <%-- <tag:paginate offset="${generalAssetPager.offset}" total="${generalAssetPager.total}" steps="${generalAssetPager.pageSize}" uri="${generalAssetPager.uri}" formId="basicInfo" /> --%>
        </form>
         <div>
           <table border="0" cellspacing="0" cellpadding="0">
            <tr >
              <td style="background-color:#EDEDED;width: 1200px; padding-left: 150px;" ><span>*</span>申请原因说明:<input type="text" value="" style="width: 230px;height: 20px; margin-left:15px; "/></td>
            </tr>
           </table>
         </div> 
       
        
		<script src="${staticURL}/js/jquery-1.11.3.min.js"></script>
		<script src="${staticURL}/js/jquery.tableCheckbox.js"></script>
		<script src="${staticURL}/js/browser.js"></script>
		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
		<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
		<script src="${staticURL}/Scripts/Purchase/DemandOrder/DemandOrder.js" type="text/javascript"></script>
		
     <script type="text/javascript">
     
         /* var dg = frameElement.lhgDG; */
         
         var checkedBox = new Array();
         $(function(){
           
           $("#departmentDiv").hide();
           $("#departmentDivInfo").hide();
  		   $("#costCentreDiv").hide();
  		   $("#costCentreDivInfo").hide();
  		   $("#platformDiv").hide();
  		   $("#platformDivInfo").hide();
         });
         //查询
         $("#btnQuery").click(function() {
        	 var inventoryPeople = $("#inventoryPeople").val();
        	 if (inventoryPeople == ""||inventoryPeople == null) {
        		 alert("请选择盘点负责人!");
        		 return;
        	 }
    	    window.location.href = '${dynamicURL}/check/searchDetails?' + $('#basicInfo').serialize();
    	    
    	    var boxes = document.getElementsByName("checkboxAsset");
            for(i=0;i<boxes.length;i++){

                for(j=0;j<checkedBox.length;j++){
                    
                    if(boxes[i].value == checkedBox[j]){

                        boxes[i].checked = true;
                    }
                }
            }
       });
         
         //提交
         $('#submit').click(function(){
    	    $("#isDraft").val(1);
    	    var ids = checkedBox.join(",");
    	    saveSponsorCheck(ids);
       });
         //保存      
         $('#saveDraft').click(function(){
    	    $("#isDraft").val(0);
    	    var ids = checkedBox.join(",");
    	    saveSponsorCheck(ids);
       });
       function changeLevels(){
    	   var levels =  $("#inventoryLevels").val();
    	   if (levels == "") {
    		   $("#changeDiv").html("");
    		   $("#changeDivInfo").html("");
    	   }
    	   if (levels == "部门") {
    		   $("#changeDiv").html("盘点部门:");
    		   $("#changeDivInfo").html("<input class='td_input' type='text' id='inventoryDepartment' name='inventoryDepartment' value='${param.inventoryDepartment}'/>")
    	   }
    	   if (levels == "成本中心") {
    		   $("#changeDiv").html("成本中心:");
    		   $("#changeDivInfo").html("<input class='td_input' type='text' id='costCenter' name='costCenter' value='${param.costCenter}'/>");
    	   }
    	   if (levels == "平台") {
    		   $("#changeDiv").html("<span>*"+"</span>"+"平台:");
    		   $("#changeDivInfo").html(
  	                  "<select class='td_select select_color' id='platform' name='platform'>"+
	                    "<option class='option_color' selected='selected' value=''>--请选择--</option>"+
	                    "<option class='option_color' value='制造供应链' >制造供应链</option>"+
	                    "<option class='option_color' value='职能' }>职能</option>"+
	                    "<option class='option_color' value='研发' }>研发</option>"+
	                    "<option class='option_color' value='内销' }>内销</option>"+
	                    "<option class='option_color' value='列表项1' }>列表项1</option>"+
	                    "<option class='option_color' value='外销' }>外销</option>"+
	                 "</select>");
    	   }
       }
       function isChecked(obj){
    	   
	    	if (obj.checked) {
	            thisId = obj.value;
	            checkedBox.push(thisId);
	        }
	        else {
	        	thisId = obj.value;
	            for (var i = 0; i < checkedBox.length; i++) {
	                if (checkedBox[i] == thisId)
	                {
	                	checkedBox.splice(i, 1);
	                }
	             }
	          }
       }
       
       function saveSponsorCheck(sublist){
    	
    	   var d = $("#inventoryApplyDate").val();
    	   var d1 = $("#inventoryDate").val();
    	   
    	  $.ajax({
    		   type:"post",
    		   url:"${dynamicURL}/check/saveSponsorCheck?"+$('#basicInfo').serialize()+'&applicationDate='+d+'&applicationDate1='+d1+'&sublist='+sublist,
    		   async:false,
               success: function(data) {
                    if(data.errorMessages.length > 0){
                	   alert(data.errorMessages);
                	   return;
                    }
                       alert("操作成功！");
                       dg.cancel();
                       return;
                    }
    	       });
           }
       
       function deleteAsset(){
   		
   		 var spCodesTemp = "";
   		
   		 var ids="";
   		
   		   $('input:checkbox[name=checkboxAsset]:checked').each(function(i){
   				spCodesTemp = $(this).val();
   				if(ids!=null&&ids!=""){
   					
   					ids=spCodesTemp+","+ids;
   				}
   				else{
   					ids=spCodesTemp;
   				}
   		  });
   	
   			if(ids!=null&&""!=ids){
   				
   				if(window.confirm("确认要删除吗?")){
   					$.ajax({
   					  	type: "post",
   			            contentType: "application/json",
   			            url: "deleteAssetCard", 
   			            data:{"ids":ids},
   			            success:function(data){
   			            	if (data.success) {
   			            		alert("删除成功！");
   			            		$("input:checkbox[name=checkboxAsset]").each(function(){
   					   				 $(this).attr('checked', false);
   					   			});
   			            		window.location.reload();
   			            	}
   			            	else{
   			            		alert(data.errorMessages.join());
   			            	}
   			            	
   			            
   			            }
   				  }); 
   				}
   			}
   			else{
   				alert("请至少选择一条数据");
   			}
   		  
   		}
     
     
</script>
</body>
</html>