<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>盘点列表</title> 		
		
<link rel="Stylesheet" type="text/css" href="${staticURL}/assetscheck/assetscheck.css">
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
   			<div style="text-align: center;margin-right: auto; margin-left: 150px;">
	     <table>
	     	<tr>
	     		<td style="text-align: right;">单据号:</td>
	     		<td><input id="u8680_input" class="textInput" value="" type="text"></td>
	     		<td style="padding-left: 150px;text-align: right;">盘点人:</td>
	     		<td><input id="u8680_input"  value="" class="textInput"  type="text"></td>
	     	</tr>
	     	<tr>
	     		<td>盘点部门:</td>
	     		<td><input id="u8680_input" class="textInput" value="" type="text"></td>
	     		<td style="padding-left: 40px;text-align:right;">盘点状态:</td>
	     		<td><input id="u8680_input" class="textInput" value="" type="text"></td>
	     	</tr>
	     	<tr>
	     		<td style="text-align: right;">盘点年:</td>
	     		<td><input id="u8680_input" class="textInput" value="" type="text"></td>
	     		<td style="padding-left: 40px;text-align: right">盘点月:</td>
	     		<td><input id="u8680_input" class="textInput" value="" type="text"></td>
	     	</tr>
	     </table>
     </div>
   		</fieldset>
         <div>
           <table>
             <tr>
               <input value="查询" type="button" onclick="#"/>
               <input value="发起盘点" type="button" onclick="addCard('1','数据查看');" style="cursor:pointer;"/>
               <input value="录入盘点结果" type="button" style="cursor:pointer;" onclick="addCard('1','数据查看');"/>
               <input value="删除" type="submit"/>
               <input value="导出" type="submit"/>
               <input value="打印" type="button"/>
             </tr>
          </table> 
        </div>
         
          
        <div  style="overflow:scroll" >
			<table style="width:1700px;">
				<tr >
					<th><input class="input_checkbox" type="checkbox"/></th>
				    <th>操作</th>
					<th>操作</th>
					<th>打印</th>
					<th>盘点单据号</th>
					<th>盘点年度</th>
					<th>盘点月</th>
					<th>盘点人</th>
					<th>盘点申请日期</th>
					<th>盘点状态</th>
					<th>盘点完结日期</th>
					<th>是否打印</th>
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
			</table>
		</div>
		<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
		<script src="${staticURL}/Scripts/Purchase/DemandOrder/DemandOrder.js" type="text/javascript"></script>
	<script type="text/javascript">

	//修改
	function addCard(id,title) {
		   var lurl = "demand/xxxx";
           var dlg = new $.dialog({ id: 'add', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
       }
    </script>   
</body>
</html>