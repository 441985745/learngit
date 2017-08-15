<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>个人资产</title>
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
    <form id="asd">
     <table cellspacing="0" cellpadding="0"
           style="width: 100%; height: 100%;" border="0">
        <tbody>
        <tr id="Button">
            <td class="buttonbar">
                &nbsp;&nbsp;
                 <input type="button" name="" value="导出" id="budgetImport" class="btn">
                <input type="button" name="" value="打印" id="budgetExport" class="btn">
                
            </td>
        </tr>
        <tr>
            <td  valign="top">
                <div id="gridview" style="width: 100%; height: 328px;">
                    <div>
                    	<input type="hidden" value="">
                        <table  class="adminlist" cellspacing="0" rules="all" border="1"
                               id="gvDataList" style="border: collapse;">
                            <tbody>
                            <tr  style="white-space: nowrap;">
                                <th scope="col">序号</th>
                                <th scope="col">操作</th>
                                <th scope="col">资产编号</th>
                                <th scope="col">资产类型</th>
                                <th scope="col">资产名称</th>
                                <th scope="col">成本中心</th>
                                <th scope="col">使用部门</th>
                                <th scope="col">负责人</th>
                                <th scope="col">资产原值</th>
                                <th scope="col">资产净值</th>
                                <th scope="col">购置日期</th>
                                <th scope="col">状态</th>
                                <th scope="col">公司</th>
                                
                            </tr>

                            <c:forEach items="${personalPager.records}" var="personal" varStatus="status">
                             <tr  style="white-space: nowrap;">
                                 
                                 <td align="center">1<input type="hidden" value="${personal.id}" id="overallId" /></td>
                                 <td align="center"><span id="look" onclick="lookOvers(40,'查看');"><font style="color: blue;">查看</font></span> </td>
                                 <td>${personal.assetCode}</td>
                                 <td>
                                 	<c:if test="${personal.assetType=='0'}">IT资产(电脑类)</c:if>
                                 	<c:if test="${personal.assetType=='1'}">IT资产(非电脑类)</c:if>
                                 	<c:if test="${personal.assetType=='2'}">行政资产</c:if>
                                 	<c:if test="${personal.assetType=='3'}">计量器具</c:if>
                                 	<c:if test="${personal.assetType=='4'}">机器设备</c:if>
                                 </td>
                                 <td>${personal.assetName}</td>
                                 <td>${personal.persentDepartent}</td>
                                 <td>${personal.officeLocation}</td>
                                 <td>${personal.assetPerson}</td>
                                 <td>${personal.assetOriginalValue}</td>
                                 <td>${personal.assetNetValue}</td>
                                 <td><fmt:formatDate value="${personal.purchaseDate}" pattern="yyyy-MM-dd" /> </td>
                                 <td>${personal.useState}</td>
                                 <td>${personal.persentCompany}</td>
						 
                             </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <tag:paginate offset="${personalPager.offset}" total="${personalPager.total}" steps="${personalPager.pageSize}" uri="${personalPager.uri}" formId="asd" />
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    </form>
</div>
<!-- 导入-->
<div id="budgetImportWindow" name="" class="easyui-window" title="导入预算excel" data-options="iconCls:'icon-save'" style="width:500px;height:200px;padding:5px;" closed="true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
				    
		        <form id="fileUpload" method="post" enctype="multipart/form-data" class="form-horizontal" >onsubmit="return check()" action="${dynamicURL}/fileUpload/importFile" 
		              <div class="imgtitle"></div>
		                	文件信息
		                <span class="warn">*</span><input type="file" id="inputfile" name="inputfile">
		                    <input type="button" value="上传" id="uploadStart">onclick="fileUpload()" 
		        		<input type="hidden" name="document" id="document">
		        		<input type="hidden" name="flag" id="flag" value="0">
		        </form>
			</div>
		</div>
</div>




<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>

<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/Purchase/DemandOrder/DemandOrder.js" type="text/javascript"></script>


<script type="text/javascript">
	/*+++++++++++++++++++++++++*/
	$(function (){
		
		
	});
	
	/*+++++++++++++++++++++++++*/
	
	/*+++++++++++++++++++++++++++++*/
	function lookOvers(id,title) {
		//${dynamicURL}/property/lookOver
		var id = $("#overallId").val();
	    var lurl = "${dynamicURL}/property/lookOver?id="+id;
        var dlg = new $.dialog({ id: "1506", title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
     } 
	/*+++++++++++++++++++++++++++++*/
	
	
	/*--------------------------------------------------------------*/
    GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
   QueryFieldSetStauts('t_ShipInportInfo', 'fset_ShipInportInfo', 'img_ShipInportInfo', document.getElementById("hfQueryStatus").value);
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
    
   $("#budgetImport").click(function(){
	    $('#budgetImportWindow').window('open').window('resize',{width:'500px',height:'200px',top:"130px",left:"220px"});
   });
   /*
   $('#uploadStart').click(function(){
  	 var file=document.getElementById("inputfile").value;
       // var wid=document.getElementById("workflowId").value;
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
    url:'${dynamicURL}/assetBudget/Excel',//服务器端程序
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
  }); */
	
</script>
</body>
</html>