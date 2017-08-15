<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>盘点差异</title> 		
		

<link href="${staticURL}/assetcheck/assetcheck.css" rel="stylesheet" type="text/css"/>
<link rel="Stylesheet" type="text/css" href="${staticURL}/assetcard/css/assetcardlist.css">
<link rel="Stylesheet" type="text/css" href="${staticURL}/Scripts/ExtJs/resources/css/ext-all.css"> 
<link href="${staticURL}/Scripts/DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
	.textInput{
		border: 1px double #444444;height: 20px; width: 200px;
		font-size: 12px;
	}
	.textInput {
    background-color: #ffffff;
    border: 1px double #cccccc;
    width: 200px;
    font-size: 12px;
}
th{
    background: #E8E8E8;
    border-color: #FFFFFF;
    font-size:12px;   
}
</style>
		

	
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
               <input class="btn" value="查询" type="button" onclick="" />
               <input class="btn" value="录入盘点差异" type="button" style="cursor:pointer;" onclick="addCard('1','录入盘点差异');"/>
               <input class="btn" value="删除" type="submit"/>
               <input class="btn" value="导出" type="submit"/>
               <input class="btn" value="打印" type="button"/>
               </td>
             </tr>
          </table> 
        </div>
         
          
        <div  style="overflow:scroll">
			<table id="gvDataList" style="width:1700px;" class="adminlist">
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
					<tr>
						<td><input class="input_checkbox" type="checkbox"></td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
					</tr>
					<tr>
						<td><input class="input_checkbox" type="checkbox"></td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
					</tr>
				</tbody>
			</table>
		</div>
		<script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>  
		<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
		<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	 //列表动态yanse
	 GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
	//修改
	function addCard(id,title) {
		   var lurl = "check/checkDifference";
           var dlg = new $.dialog({ id: 'add', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
       }
    </script>   
</body>
</html>