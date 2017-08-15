<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>资产打印</title>
<style>
@media print{
　　.noprint{
  　　display:none
　　}
}
</style>
    <script language=javascript>
function printsetup(){
  // 打印页面设置
    wb.execwb(8, 1);
}
function printpreview(){
  // 打印页面预览
  wb.execwb(7,1);
}
function printit(){
 if (confirm('确定打印吗？')){
  wb.ExecWB(6,1)
  //wb.execwb(1,1)//打开
  //swb.ExecWB(2,1);//关闭现在所有的IE窗口，并打开一个新窗口
  //wb.ExecWB(4,1)//;保存网页
  //wb.ExecWB(6,1)//打印
  //wb.ExecWB(7,1)//打印预览
  //wb.ExecWB(8,1)//打印页面设置
  //wb.ExecWB(10,1)//查看页面属性
  //wb.ExecWB(15,1)//好像是撤销，有待确认
  //wb.ExecWB(17,1)//全选
  //wb.ExecWB(22,1)//刷新
 /* wb.ExecWB(45,1)//关闭窗体无提示 */
 }
}
</script>
<link rel="Stylesheet" type="text/css" href="${staticURL}/discardsell/discardsell.css">
</head>
<body>
	<div align="center">
		    <input value="打印" type="button" onclick="javascript:window.print()" />
		    <OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="wb" name="wb" width="0">
		    </OBJECT>
		    <input type=button name=button_print style="display:none;" value="打印本单据" onclick="javascript:printit()">
		   <!--  <input type=button name=button_setup value="打印页面设置" onclick="javascript:printsetup();"> -->
		    <input type=button name=button_show value="打印预览" onclick="javascript:printpreview();">
		    <input type=button name=button_fh value="关闭" onclick="javascript:window.close();">
		</div>
	<div  style="width:100%;height:100%;">
		<div>
			<img src="${staticURL}/assetcard/img/logo1.png" style="height: 80px;">
		</div>
	
		<div>
			<table class="table"  >
				<tr style="text-align: center;">
					<td><th class="font_class">资产调拨单</th></td>
				</tr>
				<tr >
					<td class="td_right">申请流程单号：</td>
					<td >${assetAllocation.document}</td>
					<td class="td_right">申请人：</td>
					<td >${assetAllocation.userCode}/${assetAllocation.createBy}</td>			
				</tr>
				<tr >
					<td >申请日期:</td>
					<td ><fmt:formatDate value="${assetAllocation.createDate}"  pattern="yyyy-MM-dd"/></td>
					<td >申请人所属部门:</td>
					<td >${assetAllocation.departmentName}</td>
				</tr>
				<tr >
					<td >申请人所属公司:</td>
					<td >${assetAllocation.companyName}</td>
					<td >是否费用化资产:</td>
					<td > 
						<c:choose>
							<c:when test="${assetAllocation.isExpenseAssets=='1'}">
							是
							</c:when>
							<c:when test="${assetAllocation.isExpenseAssets=='0'}">
							否
							</c:when>
						</c:choose>
					</td>
				</tr>
					<tr >
					<td >调拨后负责人:</td>
					<td >${assetAllocation.postAllocationPeople}</td>
					<td >调拨后责任人部门:</td>
					<td >${assetAllocation.postAllocationDepartment}</td>
				</tr>
				<tr>
					<td >调拨后责任人成本中心:</td>
					<td >${assetAllocation.postAllocationCostCenter}</td>
					<td >调拨后归属公司:</td>
					<td >${assetAllocation.postAllocationCompany}</td>
				</tr>
				
			</table>
			<table class="table_list" style="margin-top: 20px;">
					<tr>
						<td>序号</td>
						<td>新资产编码</td>
						<td>资产编码</td>
						<td>资产名称</td>
						<td>现资产责任人</td>
						<td>规格型号</td>
						<td>原值</td>
						<td>净值</td>
						<td>现存放位置</td>
					
					</tr>
				<c:forEach items="${assetAllocation.assetAllocationDetail}" var="assetAllocationDetail" varStatus="status">
					<tr >
					<td >${status.index+1}</td>
					<td>${assetAllocationDetail.newAssetCode}</td>
					<td>${assetAllocationDetail.assetCode}</td>
					<td>${assetAllocationDetail.assetName}</td>
				
					<td>${assetAllocationDetail.assetPerson} </td>
					<td>${assetAllocationDetail.specificationModel} </td>
					<td>${assetAllocationDetail.assetOriginalValue} </td>
					<td>${assetAllocationDetail.assetNetValue} </td>
					<td>${assetAllocationDetail.location}</td>
					
					</tr>
				</c:forEach>
				<tr>
					<td>抬头文本说明</td>
					<td colspan="12"  style="height: 30px;">
					 
					</td>
				</tr>
				<tr>
					<td colspan="12"  style="height: 100px;">
					说明：<br/>
						 ${assetAllocation.allocationReason}
					</td>
				</tr>
			</table>

            <table class="table_list" style="margin-top: 20px;">
                <tbody>
                <tr>
                    <td colspan="6">
                        审批历史
                    </td>

                </tr>

                <tr style="white-space: nowrap;">
                    <td>审批人
                    </td>
                    <td>审批结果
                    </td>
                    <td colspan="2" border="0px;">审批意见
                    </td>

                    <td>签字
                    </td>
                </tr>
                <tr style="white-space: nowrap;">
                    <td>申请人
                    </td>
                    <td>同意
                    </td>
                    <td style="word-wrap : break-word ;" width="200px;" colspan="2" border="0px;">
                        我承诺以上业务真实，否则视为本人为违反公司规定，愿意接受公司相关规定处罚
                    </td>

                    <td><span>申请人签字:${checkAcceptance.applyCheckUserName }</span></br>
                        <span>时间:${checkAcceptance.dateStr}</span>
                    </td>
                </tr>


                <c:forEach items="${appHistoryPager}" var="appHistory" varStatus="status">

                    <tr style="white-space: nowrap;">
                        <td class="tdleft" align="left">${appHistory.cuserrole}</td>
                        <td class="tdleft" align="left">${appHistory.cstate}</td>
                        <td class="tdleft" colspan="2" align="left">${appHistory.csyslog}</td>
                        <td class="tdleft" align="left"><span>${appHistory.cuserrole}签字：${appHistory.cuser}</span></br>
                            <span>时间：<fmt:formatDate value='${appHistory.ddate}' pattern='yyyy-MM-dd HH:mm:ss'/></span>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
		</div>
	</div>
</body>
</html>