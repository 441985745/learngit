<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 19/11/2016
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gb2312" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>�ʼ���Ϣ����</title>
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
                    <input type="submit" name="btnConfirm" value="ȷ��" id="btnConfirm" class="btn">
                    <input type="submit" name="btnCancel" value="����" onclick="dg.cancel();" id="btnCancel" class="btn">
                </td>
            </tr>
            <tr>
                <td>
                    <table width="100%" cellpadding="0" cellspacing="1" class="formtable">
                        <tbody>
                        <tr id="saveTr">
                            <td class="tdtitle" align="right" width="25%">
                                ���:
                            </td>
                            <td class="tdtext">
                                <textarea name="txaApprovaIdea" rows="2" cols="20" id="txaApprovaIdea" class="colorblur" onblur="this.className='colorblur';" onfocus="this.className='colorfocus';" style="width: 230px; height: 45px;"></textarea>
                            </td>
                        </tr>
                        <tr id="trMail">
                            <td class="tdtitle" align="right" width="25%">
                                ��һ�������ʼ�֪ͨ:
                            </td>
                            <td class="tdtext">
                                <input id="chkMail" type="checkbox" name="chkMail">
                            </td>
                        </tr>
                        <tr id="trPhoneMessage">
                            <td class="tdtitle" align="right" width="25%">
                                ��һ�����˶���֪ͨ:
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

    function setParent(dialogId, isMail, isPhoneMessage, idea) {
        if (dialogId != '') {
            if (dg.iDG(dialogId)) {
                dg.iWin(dialogId).commitDeny(isMail, isPhoneMessage, idea);
                dg.cancel();
            }
            else {
                alert('���ȴ򿪸�����');
            }
        } else {
            alert('���ȴ򿪸�����');
        }
    }

    $('#btnConfirm').click(function () {
        setParent(dialogId, $('#chkMail').val(), $('#chkPhoneMessage').val(), $('#txaApprovaIdea').val());
    });
</script>
</body>
</html>
