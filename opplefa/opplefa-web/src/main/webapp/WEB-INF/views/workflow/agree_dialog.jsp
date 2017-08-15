<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 19/11/2016
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>邮件信息窗口</title>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet" />
</head>
<body>
<form>
    <div>
        <table cellspacing="0" cellpadding="0" width="100%" border="0">
            <tbody>
            <tr>
                <td class="buttonbar">
                    &nbsp;&nbsp;
                    <input type="submit" name="btnConfirm" value="确定" id="btnConfirm" class="btn">
                    <input type="submit" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
                </td>
            </tr>
            <tr>
                <td>
                    <table width="100%" cellpadding="0" cellspacing="1" class="formtable">
                        <tbody>
                        <c:if test="${not empty routeFlowToers and changeAmount!='Y'}">
                            <tr id="backTr">
                                <td class="tdtitle" align="right" width="25%">
                                    提交到:
                                </td>
                                <td class="tdtext">
                                    <select id="agreeActivitiId">
                                        <c:forEach items="${routeFlowToers}" var="routeFlowToer" varStatus="status">
                                            <option value="${routeFlowToer.activityId}">${routeFlowToer.user}（${routeFlowToer.userRole}）</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </c:if>
                        <tr id="saveTr">
                            <td class="tdtitle" align="right" width="25%">
                                意见:
                            </td>
                            <td class="tdtext">
                                <textarea name="txaApprovaIdea" rows="2" cols="20" id="txaApprovaIdea" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';" style="width: 230px; height: 45px;"></textarea>
                            </td>
                        </tr>

                        <tr id="trMail">
                            <td class="tdtitle" align="right" width="25%">
                                下一处理人邮件通知:
                            </td>
                            <td class="tdtext">
                                <input id="chkMail" type="checkbox" name="chkMail">
                            </td>
                        </tr>
                        <tr id="trPhoneMessage">
                            <td class="tdtitle" align="right" width="25%">
                                下一处理人短信通知:
                            </td>
                            <td class="tdtext">
                                <input id="chkPhoneMessage" type="checkbox" name="chkPhoneMessage">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            </tbody>
        </table>

    </div>
</form>
<script src="${staticURL}/Scripts/JQuery/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script type="text/javascript">
    var dg = frameElement.lhgDG;
	
    var dialogId = '${param.parentWindowId}';
    $(function () {
        if (dg.iWin(dialogId).canStepSubmit){
            if ($("#backTr").length > 0 ) {
                $("#backTr").remove();
            }
        }
    });
    function setParent(dialogId, isMail, isPhoneMessage, idea) {
    	var agreeActivitiId = $('#agreeActivitiId').val();
    	if(typeof(agreeActivitiId)=="undefined"){ 
    		agreeActivitiId="";
   		} 
        if (dialogId != '') {
            if (dg.iDG(dialogId)) {
            	$("#btnConfirm").val("提交中...");
            	$("#btnConfirm").attr("disabled","disabled");//添加disabled属性 
            	$("#btnCancel").attr("disabled","disabled");//添加disabled属性 
           		dg.iWin(dialogId).commitAgree(isMail, isPhoneMessage, idea, agreeActivitiId);
                dg.cancel();
            }
            else {
                alert('请先打开父窗口');
            }
        } else {
            alert('请先打开父窗口');
        }
    }

    $('#btnConfirm').click(function () {
    	var chkMail = $('#chkMail').is(':checked') == true ? "on" : "off";
    	var chkPhoneMessage = $('#chkPhoneMessage').is(':checked') == true ? "on" : "off";
        setParent(dialogId, chkMail, chkPhoneMessage, $('#txaApprovaIdea').val());
    });
</script>
</body>
</html>
