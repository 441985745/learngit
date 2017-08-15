<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${staticURL}/assetcheck/assetcheck.css" rel="stylesheet" type="text/css"/>
<link rel="Stylesheet" type="text/css" href="${staticURL}/assetcard/css/assetcardadd.css">
<link rel="Stylesheet" type="text/css" href="${staticURL}/Scripts/ExtJs/resources/css/ext-all.css"> 
<link href="${staticURL}/Scripts/DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
<title>盘点差异</title>

 
</head>
<body>
       <table>
           <tr>
             <td style="border: 1px solid  #B5B5B5;" class=" buttonbar" colspan="6">
               <input class="btn" type="submit" value="提交" />
               <input class="btn" type="submit" value="保存草稿"/>
               <input class="btn" type="submit" value="返回"/>
             </td>
           </tr>
           <tr>
              <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">
                            基本信息
              </td>
           </tr>
        </table>
        <form> 
           <table>
             <tr>
                <td class="td_right">申请人：</td>
                <td class="td_left"><input class="td_input" type="text"></td>
                <td class="td_right">申请日期：</td>
               <td class="td_left">
                 <input class="td_input Wdate" style="width: 160px;height: 25px;" type="text" class="Wdate" onclick="WdatePicker({skin:'ext'});">
               </td>
                
                <td class="td_right"><span>*</span>资产异常事项：</td>
                <td class="td_left">
                  <select class="td_select select_color" id="abnormalItems" name="abnormalItems" onchange="changeEvent()" >
                     <option value="使用部门不一致">使用部门不一致</option>
                     <option value="挂账人不一致">挂账人不一致</option>
                     <option value="盘亏">盘亏</option>
                     <option value="盘盈">盘盈</option>
                     <option value="无资产标签">无资产标签</option>
                     <option value="资产标签财务编码不一致">资产标签财务编码不一致</option>
                  </select>
                </td>
            </tr>
            <tr>
               <td class="td_right"><span>*</span>盘点层级:</td>
               <td class="td_left">部门</td>
               <td class="td_right">盘点部门:</td>
               <td class="td_left">财务部</td>
               <td class="td_right" ><span>*</span>申请原因说明:</td>
               <td class="td_left">
               		<input class="td_input" type="text" />
               </td>
               <!-- <td class="td_right">申请日期：</td>
               <td class="td_left">
                 <input class="td_input Wdate" style="width: 160px;height: 25px;" type="text" class="Wdate" onclick="WdatePicker({skin:'ext'});">
               </td>
               <td class="td_right"><span>*</span>是否为费用化资产：</td>
               <td class="td_left">
                  <select class="td_select select_color">
                     <option>--请选择--</option>
                     <option>是</option>
                     <option>否</option>
                  </select>
               </td>
               <td class="td_right"><span>*</span>会计年度：</td>
               <td class="td_left">
                  <select class="td_select select_color">
                     <option class="option_color">--请选择--</option>
                     <option class="option_color">1</option>
                     <option class="option_color">2</option>
                     <option class="option_color">3</option>
                     <option class="option_color">4</option>
                     <option class="option_color">5</option>
                     <option class="option_color">6</option>
                     <option class="option_color">7</option>
                     <option class="option_color">8</option>
                     <option class="option_color">9</option>
                     <option class="option_color">10</option>
                     <option class="option_color">11</option>
                     <option class="option_color">12</option>
                  </select>
              </td> -->
          </tr>
         <!--  <tr>
                <td class="td_right"><span>*</span> 资产类型：</td>
                <td class="td_left"><input class="td_input" type="text"></td>
              <td class="td_right">原归属公司：</td>
              <td class="td_left">苏州欧普照明</td>
              <td class="td_right">所属部门：</td>
              <td class="td_left">模具中心室</td>
              <td></td>
              <td></td>
          </tr> -->
        </table>
     </form>
       <table style="background-color:#EDEDED; width: 100%;">
          <tr>
             <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">
                             盘点差异信息
             </td>
          </tr>
          <tr>
             <td colspan="9" style="text-align:left;">
                <input class="btn" type="button" value="查询"/>
                <!-- <input class="btn" type="button" value="删行"/> -->
             </td>
          </tr>
        </table>
     <form>
        <div style="overflow:scroll" id="changeDiv">
            <table class="td_color" style="width:1700px;">
              <tr>
                 <td style="text-align: center;">
                    <input class="input_checkbox" type="checkbox"/>
                 </td>
                 <td style="text-align: center;">序号</td>
                 <td style="text-align: center;"><span>*</span>资产编码</td>
                 <td style="text-align: center;"><span>*</span>资产名称</td>
                 <td style="text-align: center;"><span>*</span>归口管理编码</td>
                 <td style="text-align: center;"><span>*</span>资产异常事项</td>
                 <td style="text-align: center;"><span>*</span>现使用部门</td>
                 <td style="text-align: center;"><span>*</span>成本中心</td>
                 <td style="text-align: center;"><span>*</span>历史记录</td>
             </tr>
             <tr style="text-align: center;">
                 <td><input class="input_checkbox" type="checkbox"/></td>
                 <td>1</td>
                 <td>CGXQ10001</td>
                 <td>电脑</td>
                 <td>IT-0003</td>
                 <td>使用部门不一致</td>
                 <td ><input class="td_input" style="width:120px;" type="text" readonly="readonly" id="usePeople" name="usePeople"/><input type="button" id="gvdMeeting_btnClearsk_0" style="margin-left: 0px;" class="btnRefView" onclick="clearItUse();" title="点击清空参照文本框内容" value="X"><input type="button" id="showUserListBtn" style="margin-left: 0px;" class="btnRefView" title="点击打开参照文本框内容" value="…"></td>
                 <td>1</td>
                 <td>查看</td>
           </tr>
         </table>
       </div> 
  <!--      <div>
           <table border="0" cellspacing="0" cellpadding="0">
            <tr >
              <td style="background-color:#EDEDED;width: 1200px; padding-left: 150px;" ><span>*</span>申请原因说明:<input type="text" value="" style="width: 230px;height: 20px; margin-left:15px; "/></td>
            </tr>
           </table>
       </div>  -->
       <div id="changeDiv_">
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tbody>
                        <tr>
                <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">
                             盘点差异信息
                              <input type="file" name="multipartFile" id="fileToUpload" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';">
                                <input type="submit" name="btnUpload" value="上传" id="btnUpload" class="btn">
                                <input type="submit" name="btnDelFile" value="删除" id="btnDelFile" class="btn">
             </td>
                           
                               
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="divGrid">
                                    <div>
                                        <table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList" style="border-collapse:collapse;">
                                            <thead>
                                                <tr style="white-space:nowrap;">
                                                    <th scope="col">
                                                        <input id="gvDataList_CheckAll" type="checkbox" name="gvDataList$ctl01$CheckAll" onclick="javascript:SelectAllCheckboxesByTableId(this, 'uploadTable');">
                                                    </th>
                                                    <th scope="col"> 文件</th>
                                                    <th scope="col">上传人</th>
                                                    <th scope="col">上传时间</th>
                                                    <th scope="col">附件下载</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="noItems" align="center">
                                                    <td colspan="5">没有记录！</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
       </div>
    </form> 
       
       
       <%-- <tr>
              <td style="background-color:#EDEDED;">
                <img src="${staticURL}/Images/assetscheck/title.png">
                               附件信息
              </td>
            </tr>
          <div>
              <p>
                <input type="text" disabled="disable" id="fileName"/>
                <input type="file" id="file" onchange="handleFile()"/>
                <input type="submit" value="上传"/>
			    <input type="reset" value="删除"/>
			  </p>
                 <table style="width:1200px;">
                    <tr  style="background-color: #EDEDED;text-align: center;">
                      <td>
                         <input class="input_checkbox" type="checkbox"/>
                      </td>
                      <td>序号</td>
                      <td>附件类型</td>
                      <td>附件名称</td>
                      <td>上传人</td>
                    </tr>
                    
                 </table>
            
            
          </div> 
         
     <script type="text/javascript">
        var file = document.getElementById("file");
        var fileName = document.getElementById("fileName");
        function handleFile(){
            fileName.value = file.value;
        }
     </script>
        --%>
        <script src="${staticURL}/assetcard/js/jquery-1.4.4.min.js" type="text/javascript"></script>  
		<script type="text/javascript" src="${staticURL}/assetcard/js/ajaxfileupload.js"></script>
		<script src="${staticURL}/opple/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript">
        $(function(){
        	$("#changeDiv_").hide();
        });
             function changeEvent() {
            	 var event =  $("#abnormalItems").val();
            	 if (event == "使用部门不一致") {
            		 var str = "<table class='td_color' style='width:1700px;'>"+
			                     "<tr>"+
				                     "<td style='text-align: center;'>"+
				                        "<input class='input_checkbox' type='checkbox'/>"+
				                     "</td>"+
				                     "<td style='text-align: center;'>序号</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产编码</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产名称</td>"+
				                     "<td style='text-align: center;'><span>*</span>归口管理编码</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产异常事项</td>"+
				                     "<td style='text-align: center;'><span>*</span>现使用部门</td>"+
				                     "<td style='text-align: center;'><span>*</span>成本中心</td>"+
				                     "<td style='text-align: center;'><span>*</span>历史记录</td>"+
				                     "</tr>"+
				                 "<tr style='text-align: center;'>"+
				                     "<td style='text-align: center;'><input class='input_checkbox' type='checkbox'/></td>"+
				                     "<td>1</td>"+
				                     "<td>CGXQ10001</td>"+
				                     "<td>电脑</td>"+
				                     "<td>IT-0003</td>"+
				                     "<td>使用部门不一致</td>"+
				                     "<td><input class='td_input' style='width:120px;' type='text' readonly='readonly' id='usePeople' name='usePeople'/><input type='button' id='gvdMeeting_btnClearsk_0' style='margin-left: 0px;' class='btnRefView' onclick='clearItUse();' title='点击清空参照文本框内容' value='X'><input type='button' id='showUserListBtn' style='margin-left: 0px;' class='btnRefView' title='点击打开参照文本框内容' value='…'></td>"+
				                     "<td>1</td>"+
				                     "<td>查看</td>"+
				                 "</tr>"+
			             	"</table>";
          		   $("#changeDiv").html(str);
          		   $("#changeDiv_").hide();
          	   }
            	 if (event == "挂账人不一致") {
            		 var str = "<table class='td_color' style='width:1700px;'>"+
			                     "<tr>"+
				                     "<td style='text-align: center;'>"+
				                        "<input class='input_checkbox' type='checkbox'/>"+
				                     "</td>"+
				                     "<td style='text-align: center;'>序号</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产编码</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产名称</td>"+
				                     "<td style='text-align: center;'><span>*</span>归口管理编码</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产异常事项</td>"+
				                     "<td style='text-align: center;'><span>*</span>现资产责任人</td>"+
				                     "<td style='text-align: center;'><span>*</span>成本中心</td>"+
				                     "<td style='text-align: center;'><span>*</span>历史记录</td>"+
				                     "</tr>"+
				                 "<tr style='text-align: center;'>"+
				                     "<td><input class='input_checkbox' type='checkbox'/></td>"+
				                     "<td>1</td>"+
				                     "<td>CGXQ10001</td>"+
				                     "<td>电脑</td>"+
				                     "<td>IT-0003</td>"+
				                     "<td>挂账人不一致</td>"+
				                     "<td><input class='td_input' style='width:120px;' type='text' readonly='readonly' id='usePeople' name='usePeople'/><input type='button' id='gvdMeeting_btnClearsk_0' style='margin-left: 0px;' class='btnRefView' onclick='clearItUse();' title='点击清空参照文本框内容' value='X'><input type='button' id='showUserListBtn' style='margin-left: 0px;' class='btnRefView' title='点击打开参照文本框内容' value='…'></td>"+
				                     "<td>1</td>"+
				                     "<td>查看</td>"+
				                 "</tr>"+
			             	"</table>";
          		   $("#changeDiv").html(str);
          		 $("#changeDiv_").hide();
          	   }
            	 if (event == "盘亏"||event == "盘盈") {
            		 var str = "<table class='td_color' style='width:1700px;'>"+
			                     "<tr>"+
				                     "<td style='text-align: center;'>"+
				                        "<input class='input_checkbox' type='checkbox'/>"+
				                     "</td>"+
				                     "<td style='text-align: center;'>序号</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产编码</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产名称</td>"+
				                     "<td style='text-align: center;'><span>*</span>归口管理编码</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产异常事项</td>"+
				                     "<td style='text-align: center;'><span>*</span>盘盈亏报告</td>"+
				                     "<td style='text-align: center;'><span>*</span>成本中心</td>"+
				                     "<td style='text-align: center;'><span>*</span>历史记录</td>"+
				                     "</tr>"+
				                 "<tr style='text-align: center;'>"+
				                     "<td><input class='input_checkbox' type='checkbox'/></td>"+
				                     "<td>1</td>"+
				                     "<td>CGXQ10001</td>"+
				                     "<td>电脑</td>"+
				                     "<td>IT-0003</td>"+
				                     "<td>盘盈/亏</td>"+
				                     "<td><input class='td_input' type='text'/></td>"+
				                     "<td>1</td>"+
				                     "<td>查看</td>"+
				                 "</tr>"+
			             	"</table>";
			       
          		   $("#changeDiv").html(str);
          		   $("#changeDiv_").show();
          	   }
            	 if (event == "无资产标签"||event == "资产标签财务编码不一致") {
            		 var str = "<table class='td_color' style='width:1700px;'>"+
			                     "<tr>"+
				                     "<td style='text-align: center;'>"+
				                        "<input class='input_checkbox' type='checkbox'/>"+
				                     "</td>"+
				                     "<td style='text-align: center;'>序号</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产编码</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产名称</td>"+
				                     "<td style='text-align: center;'><span>*</span>归口管理编码</td>"+
				                     "<td style='text-align: center;'><span>*</span>资产异常事项</td>"+
				                     "<td style='text-align: center;'><span>*</span>更换资产标签</td>"+
				                     "<td style='text-align: center;'><span>*</span>成本中心</td>"+
				                     "<td style='text-align: center;'><span>*</span>历史记录</td>"+
				                     "</tr>"+
				                 "<tr style='text-align: center;'>"+
				                     "<td><input class='input_checkbox' type='checkbox'/></td>"+
				                     "<td>1</td>"+
				                     "<td>CGXQ10001</td>"+
				                     "<td>电脑</td>"+
				                     "<td>IT-0003</td>"+
				                     "<td>资产标签财务编码不一致</td>"+
				                     "<td><input class='td_input' type='text'/></td>"+
				                     "<td>1</td>"+
				                     "<td>查看</td>"+
				                 "</tr>"+
			             	"</table>";
          		   $("#changeDiv").html(str);
          		   $("#changeDiv_").hide();
          	   }
             }
        </script>
 </body>
</html>
