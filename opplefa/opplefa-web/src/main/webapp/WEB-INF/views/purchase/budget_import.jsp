<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="auth" uri="http://mrms.opple.com/authbutton"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>采购需求</title>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
    <style type="text/css">
        #page-selection ul li {
            display: inline;
        }
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
                <form action="assetsBudget" id="assetsBudgetForm">
                    <fieldset id="fset_ShipInportInfo">
                        <legend>
                            <img alt="展开/收缩" id="img_ShipInportInfo" src="${staticURL}/Images/constringency.gif" style="cursor: pointer;"
                                 onclick="FieldSetVisual('t_ShipInportInfo','fset_ShipInportInfo','img_ShipInportInfo'); autoSize();">查询条件
                       	</legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="t_ShipInportInfo" class="adminlist" style="visibility: visible;">
                            <tbody>
	                            <tr>
	                                
	                                 <td align="right" class="tdtitle">成本中心:
	                                </td>
	                                <td class="tdtext">
	                                    <input   type="text" id="costCenterNameOrCode" name="costCenterNameOrCode" class="colorblur" value="${param.costCenterNameOrCode}" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
	                                    <input   type="hidden" id="txtCostCenterCode" type="hidden" name="costCenter">
	                                </td>
	                               <td align="right" class="tdtitle">平台:
	                                </td>
	                                <td class="tdtext">
	                                    <input   type="text" id="platform" name="platform"  value="${param.platform}"  class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
	                                    <input  value="6000" type="hidden" id="txtCompnyCode" type="hidden" name="company">
	                                    <input  value="${version}" type="hidden" id="version"  name="version">
	                                </td>
	                            </tr>
	                            <tr>
	                                <td align="right" class="tdtitle">项目:
	                                </td>
	                                <td class="tdtext">
	                                    <input type="text" id="txtProjectName"  name="project" class="colorblur" value="${param.project}" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
	                                </td>
	                                 <td align="right" class="tdtitle">年度:
	                                </td>
	                                <td class="tdtext">
	                                    <input name="budgetYear"  type="text" id="budgetYear" class="colorblur" value="${param.budgetYear}" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
	                                </td>
	                               
	                            </tr>
	                            <tr>
	                                <td align="right" class="tdtitle">维护人:
	                                </td>
	                                <td class="tdtext">
	                                    <input  type="text" id="txtCreateBy" name="createBy" class="colorblur" value="${param.createBy}" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
	                                    <input name="createByCode"  type="hidden" id="txtCreateByCode" >
	                                </td>
	                                <td align="right" class="tdtitle">维护时间:
	                                </td>
	                                <td class="tdtext">
	                                <input type="text" style="width: 90px;"
										class="Wdate"  value="${param.fromDate}"  name="fromDate" onclick="WdatePicker({skin:'ext'});"
										id="" />~ <input type="text" style="width: 90px;" class="Wdate"
										value="${param.toDate}" name="toDate" onclick="WdatePicker({skin:'ext'});"/>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td align="right" class="tdtitle">是否确认:
	                                </td>
	                                <td class="tdtext">
	                                    <select name="isSure" id="isSure" class="colorblur">
			                                        <option selected="selected" value="">--请选择--</option>
			                                        <option value="Y" ${param.isSure=="Y"?"selected='selected'" : ""}>是</option>
			                                        <option value="N" ${param.isSure=="N"?"selected='selected'" : ""}>否</option>
                                    	</select>
	                                </td>
	                            </tr>
                            <!-- <tr>
                                <td align="right" class="tdtitle">是否为草稿:</td>
                                <td class="tdtext">
                                    <select name="ckDraft" id="ckDraft">
                                        <option selected="selected" >--请选择--</option>
                                        <option value="Y" ${param.ckDraft=="Y"?"selected='selected'" : ""}>是</option>
                                        <option value="N" ${param.ckDraft=="N"?"selected='selected'" : ""}>否</option>

                                    </select>
                                </td>
                                <td align="right" class="tdtitle">单据状态:</td>
                                <td class="tdtext">
                                    <select name="ckOrderStatus" id="ckOrderStatus">
                                        <option selected="selected" >--请选择--</option>
                                        <option value="关闭" ${param.ckOrderStatus=="关闭"?"selected='selected'" : ""}>关闭</option>
                                        <option value="未关闭" ${param.ckOrderStatus=="未关闭"?"selected='selected'" : ""}>未关闭</option>
                                    </select></td>
                            </tr> -->
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
                <!-- <input onclick="opAdddlg('-1','数据新增')" type="button" name="btnQuery" value="新增" id="btnAdd" class="btn"> -->
                <auth:authTag code="GetBud">
                <input type="button" name="" value="同步BUD" id="synchroBudgetFromBUD" class="btn">
                </auth:authTag>
<!--                 <input type="button" name="" value="取消确认" id="cancelSave" class="btn"> -->
				<auth:authTag code="Import">
                 <input type="button" name="" value="导入" id="budgetImport" class="btn">
                 </auth:authTag>
                 <auth:authTag code="Export">
                <input type="button" name="" value="导出" id="budgetExport" class="btn">
                </auth:authTag>
                 <auth:authTag code="Confirm">
                <input type="button" name="" value="确认" id="confirm" class="btn">
                </auth:authTag>
                <auth:authTag code="Confirm">
                <input type="button" name="" value="取消确认" id="cancelConfirm" class="btn">
                </auth:authTag>
                <auth:authTag code="Confirm">
                <input type="button" name="" value="全部确认" id="allConfirm" class="btn">
                </auth:authTag>
                <auth:authTag code="Delete">
                <input type="button" name="" value="删除" id="delete" class="btn">
                </auth:authTag>
              <!--   <input type="hidden" name="hdnApprovalCode" id="hdnApprovalCode">
                <input type="hidden" name="hdnUserName" id="hdnUserName"> -->
            </td>
        </tr>
        <tr>
            <td valign="top">
                <div id="gridview" style="width: 1045px; height: 328px;">
                    <div>
                        <table class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border-collapse: collapse;">
                            <tbody>
                            <tr style="white-space: nowrap;">
                                <th scope="col">
                                    <input id="gvDataList_CheckAll" type="checkbox" name="gvDataList$ctl01$CheckAll"
                                           onclick="javascript:SelectAllCheckboxesByTableId(this, 'gvDataList');"/>
                                </th>
                                <th scope="col">序号</th>
                                <th scope="col">年度</th>
                                <th scope="col">成本中心名称</th>
                                <th scope="col">成本中心</th>
                                <th scope="col">平台</th>
                                <th scope="col">项目编码</th>
                                <th scope="col">项目名称</th>
                                <th scope="col">资产名称</th>
                                <th scope="col">资产类型</th>
                                <th scope="col">类型</th>
                                <th scope="col">单位</th>
                                <th scope="col">预算数量</th>
                                <th scope="col">预算单价</th>
                                <th scope="col">已使用金额</th>
                                <th scope="col">可用金额</th>
                                <th scope="col">验收金额</th>
                                <th scope="col">年度预算总额</th>
                                <th scope="col">是否确认</th>
                                <th scope="col">维护人</th>
                                <th scope="col">维护时间</th>
                            </tr>

                            <c:forEach items="${assetsBudgetsPager.records}" var="assetsBudget" varStatus="status">
                                <tr style="white-space: nowrap;">
                                    <td align="center">
                                        <input id="checkBudget" type="checkbox" name="checkBudget" class="budgetCheckbox" value="${assetsBudget.id}">
                                    </td>
                                    <td align="center">
                                       ${status.count }
                                    </td>
                                    <td>${assetsBudget.budgetYear}</td>
                                    <td>${assetsBudget.costCenterName}</td>
                                    <td>${assetsBudget.costCenter}</td>
                                    <td>${assetsBudget.platform}</td>
                                    <td>${assetsBudget.projectCode}</td>
                                    <td>${assetsBudget.projectName}</td>
                                    <td>${assetsBudget.assetsName }</td>
                                    <td>${assetsBudget.assetsType}</td>
                                    <td>${assetsBudget.assetsModel}</td>
                                    <td >${assetsBudget.unit}</td>
                                    <td style="text-align:right">${assetsBudget.amount}</td>
                                    <td name="price" style="text-align:right">${assetsBudget.unitPrice}</td><!-- 预算单价 -->
                                    <td name="price" style="text-align:right">${assetsBudget.usedSumMoney}</td>
                                    <td name="price" style="text-align:right">${assetsBudget.avaliableSumMoney}</td><!--  预算金额-->
                                    <td name="price" style="text-align:right">${assetsBudget.checkMoney}</td><!--  预算金额-->
                                    <td name="price" style="text-align:right">${assetsBudget.yearBudgetTotal}</td>
                                    <c:choose>
	                                    <c:when test="${assetsBudget.isSure=='Y'}">
							 				<td>是</td>
							 			</c:when>	
							 			<c:when test="${assetsBudget.isSure=='N'}">
							 				<td>否</td>
							 			</c:when>
						 			</c:choose>
                                    <td>${assetsBudget.createBy}</td>
                                    <td style="text-align:center"><fmt:formatDate value="${assetsBudget.createDate}"  pattern="yyyy-MM-dd"/></td>
                                    
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${assetsBudgetsPager.offset}" total="${assetsBudgetsPager.total}" steps="${assetsBudgetsPager.pageSize}" uri="${assetsBudgetsPager.uri}" formId="assetsBudgetForm" />
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="import_budget_window.jsp"></jsp:include>
<!-- 导入-->
<div id="budgetImportWindow" name="" class="easyui-window" title="导入预算excel" data-options="iconCls:'icon-save'" style="width:500px;height:200px;padding:5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
				    
		        <form id="fileUpload" method="post" enctype="multipart/form-data" class="form-horizontal" ><!-- onsubmit="return check()" action="${dynamicURL}/fileUpload/importFile"  -->
		              <div class="imgtitle"></div>
		                	文件信息
		                <span class="warn">*</span><input type="file" id="inputfile" name="inputfile">
		                    <input type="button" value="上传" id="uploadStart"><!--onclick="fileUpload()"  -->
		        	<!-- 	<input type="hidden" name="document" id="document">
		        		<input type="hidden" name="flag" id="flag" value="0"> -->
		        </form>
			</div>
		</div>
</div>



	<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>  
	<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
	<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
	<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<%-- <script src="${staticURL}/Scripts/Purchase/DemandOrder/DemandOrder.js" type="text/javascript"></script>
 --%>


<script type="text/javascript">
    GridViewColor("gvDataLlist", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
    QueryFieldSetStauts('t_ShipInportInfo', 'fset_ShipInportInfo', 'img_ShipInportInfo', document.getElementById("hfQueryStatus").value);
    $(function(){
    	$("td[name='price']").each(function(){
    		if($(this).html()!=""){
    			$(this).html(fmoney($(this).html(),2));
    		};
    	});
    })
    function autoSize() {
        $("#gridview").width($(window).width());
        $("#gridview").height(
                $(window).height() - $("#Query").height()
                - $("#Button").height()
                - $("#AspNetPager").height());
    }
    autoSize();
    window.onresize = autoSize;

    $("#btnQuery").click(function() {
        window.location.href = '${dynamicURL}/assetBudget/searchAssetsBudgets?' + $('#assetsBudgetForm').serialize();
    });
    
 // 导入和模版下载窗口
	var ew = new EasyWindow();

	ew.initEasyWindow("预算数据导入",
            "${dynamicURL}/assetBudget/api/Excel");

	$('#budgetImport').click(function() {
		ew.showEasyWindow();
	});
   $('#uploadStart').click(function(){
  	 var file=document.getElementById("inputfile").value;
       // var wid=document.getElementById("workflowId").value;/
       var fileType = file.substring(file.lastIndexOf(".") + 1);
       if (fileType != "xlsx") {
           alert("上传文件格式错误！只能为xml格式！")
           return false;
       }
        var arr=new Array();
       // var doc = $("#txtDemandOrderId").val();
        arr=file.split('\\');
        var name=arr[arr.length-1];
        if(file==""){
           alert("请选择需要上传的文件！");
            return false;
        } else{
        	 
        } 
      $.ajaxFileUpload({
          url: '${dynamicURL}/assetBudget/Excel',//服务器端程序
            secureuri:false,
            fileElementId:'inputfile',//input框的ID
           // data:{flag:"0"},//,document:doc
            dataType: 'json',//返回数据类型
            async:false,
            beforeSend:function(){//上传前需要处理的工作，如显示Loading...
          	  
            },
            success: function (data){//上传成功
          	  alert(9);//data.success
          	 // reloadFileList();
            }
          });
      //$('#upload').submit();
  });
   //导出
   $("#budgetExport").click(function (){
		if (window.confirm("确认要导出吗?")) {
            window.location.href = "${dynamicURL}/assetBudget/Export?" + $('#assetsBudgetForm').serialize();
		}
		
	});
   //确认
   $("#confirm").click(function (){
	   var ids = "";
       $(".budgetCheckbox").each(function () {
           if ($(this).attr("checked")) {
               ids = ids+$(this).val()+",";
           }
       });
       if (ids == "") {
           alert("至少选择一条数据");
           return;
       }
       ids = ids.substring(0, ids.length-1);
        $.ajax({
            url: "${dynamicURL}/assetBudget/api/confirmBudget",
           type: "POST",
          // contentType: "application/json",
           data:{ids : ids},
           success: function (data) {
        	   if (data.success){
        		   alert("预算数据确认成功！");
        		   $(".budgetCheckbox").each(
							function() {
								$(this).attr('checked', false); 
							});
                   window.location.reload();
        	   } else {
					alert(data.errorMessages.join());
				}

           }
       }); 
   });
 //取消确认
   $("#cancelConfirm").click(function (){
	   var ids = "";
       $(".budgetCheckbox").each(function () {
           if ($(this).attr("checked")) {
               ids = ids+$(this).val()+",";
           }
       });
       if (ids == "") {
           alert("至少选择一条数据");
           return;
       }
       ids = ids.substring(0, ids.length-1);
        $.ajax({
            url: "${dynamicURL}/assetBudget/api/cancelConfirmBudget",
           type: "POST",
          // contentType: "application/json",
           data:{ids : ids},
           success: function (data) {
        	   if (data.success){
        		   alert("预算数据取消确认成功！");
        		   $(".budgetCheckbox").each(
							function() {
								$(this).attr('checked', false); 
							});
                   window.location.reload();
        	   } else {
					alert(data.errorMessages.join());
				}

           }
       }); 
   });
 //删除
   $("#delete").click(function (){
	   var ids = "";
       $(".budgetCheckbox").each(function () {
           if ($(this).attr("checked")) {
               ids = ids+$(this).val()+",";
           }
       });
       if (ids == "") {
           alert("至少选择一条数据");
           return;
       }
       ids = ids.substring(0, ids.length-1);
       if (window.confirm("确认要删除吗?")) {
        $.ajax({
            url: "${dynamicURL}/assetBudget/api/deleteBudget",
           type: "POST",
          // contentType: "application/json",
           data:{ids : ids},
           success: function (data) {
        	   if (data.success){
        		   alert("预算数据删除成功！");
         		   $(".budgetCheckbox").each(
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
   });
   //全部确认
   $("#allConfirm").click(function (){
       if (window.confirm("确定要确认所有预算数据?")) {
        $.ajax({
            url: "${dynamicURL}/assetBudget/api/confirmAllBudget",
           type: "POST",
          // contentType: "application/json",
          //data:{ids : ids},
           success: function (data) {
        	   if (data.success){
        		   alert("预算数据全部确认成功！");
                   window.location.reload();
        	   } else {
					alert(data.errorMessages.join());
				}

           }
       }); 
      }
   });
   $("#synchroBudgetFromBUD").click(function (){
	   var version = $("#version").val();
       if (window.confirm("确定要同步BUD?")) {
    	   $("#synchroBudgetFromBUD").val("同步中…");
    	   $(":button").attr("disabled",true);
            $.ajax({
                url: "${dynamicURL}/assetBudget/api/synchroBudgetFromBUD",
              type: "POST",
              data:{version : version},
              success: function (data) {
                  if (data.success) {
                      alert("同步BUD成功!");
                      $("#synchroBudgetFromBUD").val("同步BUD");
                      $(":button").attr("disabled",false);
                      document.location.reload();
                  } else {
         
                      alert(data.errorMessages.join());
                      $("#synchroBudgetFromBUD").val("同步BUD");
                      $(":button").attr("disabled",false);
                  } 

              }
          });  
         }   
   });
   /**
    * 数字格式转换成千分位
    */
    function fmoney(s, n)

    {
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
    
   /**
    * 去除千分位
    */
    
   function delcommafy(num){
     if((num+"").Trim()==""){
      return"";
     }
     num=num.replace(/,/gi,'');
     returnnum;
   }
</script>
</body>
</html>