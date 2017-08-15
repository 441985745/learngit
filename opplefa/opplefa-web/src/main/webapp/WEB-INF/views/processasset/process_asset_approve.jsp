 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="Stylesheet" type="text/css" href="${staticURL}/assetallocation/css/allocationapplicationdetail.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
	</head>
	
<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div style="height: 25px; font-size: 15px; font-weight: bold;text-align: center; vertical-align: middle">
						流程类资产审批
					</div>
		<div></div>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
	    <input type="button" value="同意" id="agreeBtn" class="btn">
		<input type="button" value="退回" id="denyBtn" class="btn">
		   <c:choose>
                    <c:when test="${processAssets.approvalState == '等待审批'}"> </c:when>
                    <c:otherwise>
                        <input type="button" id="dropBtn" class="btn" value="弃审" />
                    </c:otherwise>
           </c:choose>
		<input type="button" value="返回" onclick="dg.cancel();" class="btn">
	</div>
	
	<app:approvalTag billCode="${processAssets.document}" typeId="77087"></app:approvalTag>
	
	<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
	<div class="imgtitle"></div>
					流程类资产信息
			</div>
	<div style="margin-top: 2px;">
	  <form id="discardForm">
	    <table>
		  <tr>
		  <td class="td_right"><span class="span_color">*</span>流程单据号:</td>
				<td class="td_left"><input name="document" type="text"
					value="${processAssets.document}" readonly="readonly" class="td_input" /> 
					<input type="hidden" name='userCode' value="${processAssets.userCode }">
					<input type="hidden" name='userName' value="${processAssets.userName }">
					<input type="hidden" name="companyCode" value="${processAssets.companyCode }">
					<input type="hidden" name="departmentCode" value="${processAssets.departmentCode }">
					
					<input name="isMail" id="isMail"  type="hidden" />
	               	<input name="isPhoneMessage" id="isPhoneMessage"  type="hidden" />
	               	<input name="idea" id="idea"  type="hidden" />
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属部门:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="departmentName" value="${processAssets.departmentName }" >
				</td>
				<td class="td_right"><span class="span_color">*</span>申请人所属公司:</td>
				<td class="td_left">
					<input type="text" class="td_input" readonly="readonly" name="companyName" value="${processAssets.companyName }" >
				</td>
	 	 </tr>
	 	 <tr>
	 	<td class="td_right">申请人:</td>
		 		<td class="td_left">
					<input name="createBy" type="text" value="${processAssets.userName}" readonly="readonly" class="td_input"/>
	                <input name="" type="text" value="${sessionScope._user_name}" class="controlhidden" />
	                <input type="hidden" name="commitType" id="commitType" />
				</td>
				<td class="td_right">申请日期:</td>
				<td class="td_left">
					<input class="td_input"  name="createDate" type="text" readonly="readonly" value="${processAssets.applicationDate}" id="applicationDate">
				</td>
		 		<td class="td_right">资产责任人:</td>
				<td class="td_left">
				<input style="width: 120px;" class="td_input" name="assetOwner" type="text"
					readonly="readonly" value="${processAssets.assetOwner}" id="assetOwner">
					<input type="hidden" id="peopleValue" >
				</td>
	 	 </tr>
	 	 <tr>
	 	 	<td class="td_right">所属公司:</td>
	 		<td class="td_left">
				<input class="td_input"  name="homeCompany" type="text" value="${processAssets.homeCompany}" id="homeCompany" readonly="readonly"/> 
			</td>
			<td class="td_right">平台:</td>
			<td class="td_left">
				<input class="td_input" name="platform" id="platform" value="${processAssets.platform}"  readonly="readonly" />
            </td>
			<td class="td_right">资产类型:</td>
			<td class="td_left">
			<c:if test="${processAssets.assetType=='0'}">
                                	<input name="lblCom" class="td_input" readonly="true" type="text" value="IT资产(电脑类)" id="lblCom">
            </c:if>
			<c:if test="${processAssets.assetType=='1'}">
                                	<input name="lblCom" class="td_input" readonly="true" type="text" value="IT资产(非电脑类)" id="lblCom">
            </c:if>
            <c:if test="${processAssets.assetType=='2'}">
                                	<input name="lblCom" class="td_input" readonly="true" type="text" value="行政资产" id="lblCom">
            </c:if>
            <c:if test="${processAssets.assetType=='3'}">
                                	<input name="lblCom" class="td_input" readonly="true" type="text" value="计量器具" id="lblCom">
            </c:if>
            <c:if test="${processAssets.assetType=='4'}">
                                	<input name="lblCom" class="td_input" readonly="true" type="text" value="机器设备" id="lblCom">
            </c:if>
			</td>
	 	  </tr>
	 	  <tr>
	 		<td class="td_right">办公地点:</td>
	 		<td class="td_left">
	 			<input class="td_inputr" name="officeLocation" id="officeLocation" value="${processAssets.officeLocation}"  readonly="readonly" />
            </td>
			<td class="td_right">存放位置:</td>
	 		<td class="td_left">
				<input class="td_inputr" name="storagePlace" id="storagePlace" value="${processAssets.storagePlace}"  readonly="readonly" />
				 
			</td>
			<td class="td_right">流程类资产名称:</td>
	 		<td class="td_left">
				<input class="td_input"  name="processAssetsName" type="text" value="${processAssets.processAssetsName}" readonly="readonly" /> 
			</td>
	 	 </tr>
	 	</table>
	  </form>
			<div class="titlefontsize" style="width:99%;height:30px;margin-top: 10px;">
			<div class="imgtitle"></div>
					资产信息
			</div>
									
		<div style="overflow-x: scroll; width: 100%;">
			<table border="0" cellpadding="3px" cellspacing="0" width="120%">
					<tbody>
							
							<tr>
								<th><input type="checkbox" id="checkAll"></th>
									<th>资产编码</th>
									<th>资产名称</th>
									<th>归口管理编码</th>
									<th>规格型号</th>
									<th>原值</th>
									<th>净值</th>
									<th>使用信息</th>
									<th>使用状态</th>
									<th>原部门和成本中心</th>
									<th>原存放位置</th>
									<th>历史记录</th>
							</tr>
							<c:forEach items="${processAssets.generalAssetList}" var="generalAsset" varStatus="status">
							<tr style="text-align: center;">
								<td><input type="checkbox" name="radio"></td>
								<td>${generalAsset.assetCode}</td>
								<td>${generalAsset.assetName}</td>
								<td>${generalAsset.putUnderManageCode}</td>
								<td>${generalAsset.specificationModel}</td>
								<td>${generalAsset.assetOriginalValue}</td>
								<td>${generalAsset.assetNetValue}</td>
								<td>7</td>
								<td>${generalAsset.useState}</td>
								<td>${generalAsset.persentDepartment}</td>
								<td>8</td>
								<td>6</td>
							</tr>
							</c:forEach>
					</tbody>	
									
			</table>
		</div>
		
	</div>
	<script src="${staticURL}/js/jquery-1.11.3.min.js"></script>
	<%-- <script src="${staticURL}/js/assetallocation/allocationView.js"></script> --%>
	<script src="${staticURL}/js/jquery.tableCheckbox.js"></script>
	<script src="${staticURL}/js/browser.js"></script>
	<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
	<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<script src="${staticURL}/Scripts/Purchase/DemandOrder/DemandOrder.js" type="text/javascript"></script>
	<script type="text/javascript">
	var dg = frameElement.lhgDG;
	 //返回关闭申请弹窗
	$("#fanHui").click(function(){
		//关闭窗口
        dg.cancel();
	});
	$("#agreeBtn").click(function (){
		showAgreeDialog();
	});
	$("#denyBtn").click(function(){
		showDenyDialog();
	});
	
	$("#dropBtn").click(function(){
		showDropDialog();
	});
	function showAgreeDialog() {
        var url = "${dynamicURL}/processAssets/showAgreeDialog?parentWindowId=approve";

        var dlg = new dg.curWin.$.dialog({ id: 'agreeDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();
        return dlg;
    }
	
	function showDenyDialog() {
        var url = "${dynamicURL}/processAssets/showDenyDialog?parentWindowId=approve";

        var dlg = new dg.curWin.$.dialog({ id: 'denyDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();
        return dlg;
    }
	
	function showDropDialog() {
        var url = "${dynamicURL}/processAssets/showDropDialog?parentWindowId=approve";

        var dlg = new dg.curWin.$.dialog({ id: 'dropDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();
        return dlg;
    }
	function commitAgree(isMail, isPhoneMessage, idea) {
        $('#isMail').val(isMail);
        $('#isPhoneMessage').val(isPhoneMessage);
		$('#idea').val(idea);
        
        $('#agreeBtn').val('提交...');
       $.ajax({
            type:"POST",
            url: "${dynamicURL}/processAssets/agreeProcessAsset?"+$('#discardForm').serialize(),
            contentType: "application/json; charset=gb2312",
            success: function (data) {
                if (data.success) {
                    alert("审批成功");
                    dg.curWin.location.reload();
                } else {
                    $('#agreeBtn').val('同意');
                    alert(data.errorMessages.join());
                }
            }
        });
    }
	
	 function commitDeny(isMail, isPhoneMessage, idea) {
	        $('#isMail').val(isMail);
	        $('#isPhoneMessage').val(isPhoneMessage);
		 $('#idea').val(idea);
	        $('#btnDeny').val('提交...');
	       
	        $.ajax({
	            type: "POST",
	            url: "${dynamicURL}/processAssets/denyProcessAsset?"+$('#discardForm').serialize(),
	            contentType: "application/json; charset=gb2312",
	            success: function (data) {
	                if (data.success) {
	                    alert("审批成功");
	                    dg.curWin.location.reload();
	                } else {
	                    $('#btnDeny').val('退回');
	                    alert(data.errorMessages.join());
	                }
	            }
	        });
	    } 
	 
	 function commitDrop(isMail, isPhoneMessage, idea) {
	        $('#isMail').val(isMail);
	        $('#isPhoneMessage').val(isPhoneMessage);
		 $('#idea').val(idea);

	        $('#btnDrop').val('提交...');
	        $.ajax({
	            type: "POST",
	            url: "${dynamicURL}/processAssets/dropProcessAsset?"+$('#discardForm').serialize(),
	            contentType: "application/json; charset=gb2312",
	            success: function (data) {
	                if (data.success) {
	                    alert("审批成功");
	                    dg.curWin.location.reload();
	                } else {
	                    $('#btnDrop').val('弃审');
	                    alert(data.errorMessages.join());
	                }
	            }
	        });
	    }
	</script>
</body>
</html>