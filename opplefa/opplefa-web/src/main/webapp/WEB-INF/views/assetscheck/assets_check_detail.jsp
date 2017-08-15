<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看</title>

<link href="${staticURL}/assetcheck/assetcheck.css" rel="stylesheet" type="text/css"/>
<link rel="Stylesheet" type="text/css" href="${staticURL}/assetcard/css/addassetcard.css">
<link rel="Stylesheet" type="text/css" href="${staticURL}/Scripts/ExtJs/resources/css/ext-all.css"> 
<link href="${staticURL}/Scripts/DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
<link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
</head>
<body>
       <table style="width: 100%;">
          <tr ><td style="text-align: center;">查看</td></tr>
          <tr>
            <td style="border: 1px solid  #B5B5B5;" class=" buttonbar">
              <input class="btn" type="button" value="返回" id="submit"/>
            </td>
          </tr>
          <tr>
            <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">
                               基本信息
            </td>
          </tr>
       </table>
       
       <form>
             <table class="information">
               <tr>
                 <td class="td_right">申请人:</td>
                 <td class="td_left">1111</td>
                 <td class="td_right"><span>*</span>资产类型:</td>
                 <td class="td_left">1111</td>
                 <td class="td_right"><span>*</span>平台:</td>
                 <td class="td_left">1111</td>
               </tr>
               <tr>
                 <td class="td_right">申请日期:</td>
                 <td class="td_left">1111</td>
                 <td class="td_right"><span>*</span>是否为费用化资产:</td>
                 <td class="td_left">1111</td>
                 <td class="td_right"><span>*</span>会计年度:</td>
                 <td class="td_left">1111</td>
               </tr>
               <tr>
                 <td class="td_right">原归属公司:</td>
                 <td class="td_left">1111</td>
                 <td></td>
                 <td></td>
                 <td></td>
                 <td></td>
               </tr>
           </table>
      </form>
         <table style="background-color:#EDEDED; width: 100%;">
            <tr>
                <td colspan="6" class="buttonbar" ><img class="title_img" src="${staticURL}/assetcard/img/title.png">
                                     盘点差异信息
                </td>
            </tr>
         </table> 
         <form style="overflow:scroll">
            <table class="td_color" style="width:1700px;">
               <tr align="center">
                  <td>序号</td>
                  <td><span>*</span>资产编码</td>
                  <td><span>*</span>资产名称</td>
                  <td><span>*</span>归口管理编码</td>
                  <td><span>*</span>资产异常事项</td>
                  <td><span>*</span>现资产责任人</td>
                  <td><span>*</span>现使用部门</td>
                  <td><span>*</span>成本中心</td>
                  <td><span>*</span>更换资产标签</td>
                  <td><span>*</span>盘盈亏报告</td>
               </tr>
               <tr align="center">
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
                  <td style="background-color:#EDEDED;" colspan="12">&nbsp</td>
               </tr>
          </table>
        </form>   
          <div>
            <table border="0" cellspacing="0" cellpadding="0">
              <tr >
                <td style="background-color:#EDEDED;width: 1200px; padding-left: 150px;" ><span>*</span>申请原因说明:<input type="text" value="" style="width: 230px;height: 20px; margin-left:15px; "/></td>
              </tr>
            </table>
          </div> 
       
 </body>
</html>